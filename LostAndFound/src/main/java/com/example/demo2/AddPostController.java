package com.example.demo2;

import com.example.demo2.Check.alertException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.example.demo2.Check.*;

public class AddPostController  implements Initializable {
    @FXML
    private Button backButton;
    @FXML
    private ImageView iconUser;
    @FXML
    private ComboBox<String> choiceBox;
    @FXML
    private Text nameText;
    @FXML
    private Text lastName;
    @FXML
    private ImageView imageIcon;

    @FXML
    private TextField pathArea;

    @FXML
    private TextArea commentArea;
    private ConcretePostFactory concretePostFactory;
    private User user;
    private InsertPost insertPost;
    private DataLoadingProxy dataLoadingProxy;
    private DataGetter dataGetter;

    @FXML
    void fileHandle(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        File file = fileChooser.showOpenDialog(null);
        try {
            Image image = new Image(file.getAbsolutePath());
            pathArea.setText(file.getAbsolutePath());
            imageIcon.setImage(image);
        }catch (NullPointerException ex){}
    }

    @FXML
    void backToMain(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) backButton.getScene().getWindow();
        currentStage.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 810, 500);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    void acceptPost(ActionEvent event) throws IllegalAccessException, IOException {
            isCorrect isC = new isCorrect();
            int postId = dataGetter.getCount("posts") + 1;
            if(isC.isCorrectPost(pathArea.getText(),choiceBox.getValue(), commentArea.getText())) {
                // create post object and set the Database
                insertPost.insert(concretePostFactory.createPost(postId, choiceBox.getValue(), pathArea.getText(), commentArea.getText(), user.getEmail()));

                // reload the proxy
                dataLoadingProxy.loadPost();

                // open the main page
                backToMain(event);
            }
            else{
                new alertException().setAlert("Something is not correct!");
            }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        user = User.getUser();
        choiceBox.setItems(FXCollections.observableArrayList("Lost", "Found"));
        concretePostFactory = new ConcretePostFactory();
        nameText.setText(user.getName());
        lastName.setText(user.getLastName());
        File file = new File(user.getPath());
        iconUser.setImage(new Image(file.toURI().toString()));
        insertPost = new InsertPost();
        dataLoadingProxy = DataLoadingProxy.getDataLoadingProxy();
        dataGetter = new DataGetter();

    }
}
