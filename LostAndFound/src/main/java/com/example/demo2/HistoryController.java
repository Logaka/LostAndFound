package com.example.demo2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HistoryController implements Initializable {
    private DataGetter dataGetter;
    @FXML
    private Button backButton;

    @FXML
    private ImageView icon;

    @FXML
    private Text lastNameText;

    @FXML
    private Text nameText;

    @FXML
    private GridPane postSetter;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataGetter = new DataGetter();
        User user = User.getUser();
        nameText.setText(user.getName());
        lastNameText.setText(user.getLastName());
        String path = user.getPath();
        File file = new File(path);
        icon.setImage(new Image(file.toURI().toString()));
        putPost(dataGetter.getCurrentPost(user.getEmail()));

    }

    @FXML
    void loadMainPage(ActionEvent event) throws IOException {
        Stage stage = (Stage)backButton.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        primaryStage.setScene(new Scene(root, 810,500));
        primaryStage.show();
    }

    public void putPost(List<Post> list){
        if (postSetter != null)
              postSetter.getChildren().clear();
        int column = 0;
        int row = 1;
        try{
            for (Post addedPost : list) {
                FXMLLoader fxmlLoader = new FXMLLoader();

                // set the location of fxml
                fxmlLoader.setLocation(getClass().getResource("Post.fxml"));

                // load the fxml file
                VBox vBox = fxmlLoader.load();

                // get the controller of fxml
                PostController postController = fxmlLoader.getController();

                // set the post to controller
                postController.setPost(addedPost);

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

}
