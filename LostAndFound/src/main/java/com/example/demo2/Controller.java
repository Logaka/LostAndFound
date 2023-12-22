package com.example.demo2;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;



public class Controller implements Initializable {

    private final DataLoadingService dataLoadingService =  DataLoadingProxy.getDataLoadingProxy();
    private List<Post> addedPosts;
    private Searching searching;

    @FXML
    private GridPane postSetter;
    @FXML
    private Button addPostButton;
    @FXML
    private Text lastNameText;
    @FXML
    private Text nameText;
    @FXML
    private ImageView icon;
    @FXML
    private TextField searchingField;
    @FXML
    private Button historyButton;

    @FXML
    private Button settingsButton;
    @FXML
    private Button logout;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searching = new Searching();
        User user = User.getUser();
        nameText.setText(user.getName());
        lastNameText.setText(user.getLastName());
        String path = user.getPath();
        File file = new File(path);
        icon.setImage(new Image(file.toURI().toString()));
        putPost(dataLoadingService.loadPost());
    }

    // create class to fix duplicate
    @FXML
    void newPostView(ActionEvent event) throws IOException {
        Stage stage = (Stage)addPostButton.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AddPost.fxml"));
        primaryStage.setScene(new Scene(root, 810,500));
        primaryStage.show();
    }
    @FXML
    void settingsView(ActionEvent event) throws IOException{
        Stage stage = (Stage)settingsButton.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("settings-view.fxml"));
        primaryStage.setScene(new Scene(root, 810,500));
        primaryStage.show();
    }

    @FXML
    void loadPage(ActionEvent event) throws IOException {
        Stage stage = (Stage)historyButton.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("HistoryPage.fxml"));
        primaryStage.setScene(new Scene(root, 810,500));
        primaryStage.show();
    }

    @FXML
    void search(Event event) {

        putPost(searching.searchPost(searchingField.getText()));
    }
    public void putPost(List<Post> lists){
        if (postSetter != null)
            postSetter.getChildren().clear();
        addedPosts = new ArrayList<>(lists);
        int column = 0;
        int row = 1;
        try{
            for (Post addedPost : addedPosts) {
                FXMLLoader fxmlLoader = new FXMLLoader();

                // set the location of fxml
                fxmlLoader.setLocation(getClass().getResource("Post.fxml"));

                // load the fxml file
                VBox vBox = fxmlLoader.load();

                // get the controller of fxml
                PostController postController = fxmlLoader.getController();

                // set the post to controller
                postController.setPost(addedPost);
                postController.setPostId(addedPost.getPostid());

                // set up position in window
                if (column == 3) {
                    column = 0;
                    row++;
                }

                // set vbox at the specified place
                postSetter.add(vBox,column++, row);

                // set space between vbox
                GridPane.setMargin(vBox, new Insets(10));

            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void setLogout(ActionEvent event) throws IOException{
        Stage stage = (Stage)logout.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("login-view.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
