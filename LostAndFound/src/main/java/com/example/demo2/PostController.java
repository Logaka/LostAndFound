package com.example.demo2;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;


public class PostController {

    @FXML
    private ImageView imageIcon;

    @FXML
    private TextArea commentArea;

    @FXML
    private TextField fullNameField;

    @FXML
    private Label statusType;
    @FXML
    private ImageView comIcon;
    private int postId;
    void setPostId(int postId) {
        this.postId = postId;
    }




    // set posts to the post.fxml
    public void setPost(Post post) {
        // fill the post
        commentArea.setWrapText(true);
        statusType.setText(post.getStatus());
        commentArea.setText(post.getComment());
        fullNameField.setText(post.getAuthor());

        //convert the image path to object
        String path = post.getImagePath();
        File file = new File(path);
        Image image = new Image(file.toURI().toString());

        // show the post
        imageIcon.setImage(image);
    }

    @FXML
    public void openComment(MouseEvent mouseEvent) {
        SinglePost singlePost = SinglePost.getSinglePost();
        singlePost.setPostid(postId);
        Scene scene = comIcon.getScene();
        Stage stage = (Stage) scene.getWindow();

        FXMLLoader commentLoader = new FXMLLoader(getClass().getResource("ComPost-view.fxml"));
        try {
            commentLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = commentLoader.getRoot();
        Stage commentStage = new Stage();
        commentStage.initModality(Modality.APPLICATION_MODAL); // Окно блокирует взаимодействие с другими окнами, пока не закроется
        commentStage.initOwner(stage);
        commentStage.setScene(new Scene(root));
        commentStage.show();
    }

}
