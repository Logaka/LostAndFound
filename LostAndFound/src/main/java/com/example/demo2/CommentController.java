package com.example.demo2;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import com.example.demo2.Check.*;

public class CommentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text email;

    @FXML
    private ImageView icon;

    @FXML
    private TextArea postText;

    @FXML
    private Button sendButton;

    @FXML
    private TextArea sendText;

    @FXML
    private VBox vBox;

    @FXML
    void initialize() {
        //getting data from posts
        SinglePost singlePost = SinglePost.getSinglePost();
        int id = singlePost.getPostid();
        DataGetter dataGetter = new DataGetter();
        String path = dataGetter.getStringData("posts", id, "imagepath");
        File file = new File(path);
        icon.setImage(new Image(file.toURI().toString()));
        String comment = dataGetter.getStringData("posts", id, "comment");
        postText.setText(comment);
        String em = dataGetter.getStringData("posts", id, "email");
        email.setText(em);
        postText.setWrapText(true);
        sendText.setWrapText(true);

        //setting commentary
        DbDataLoadingService dbDataLoadingService = new DbDataLoadingService();
        List<Comment> arr = dbDataLoadingService.loadCommentsForPost(id);
        for (Comment value : arr) {
            System.out.println(value.getAuthor());
            System.out.println(value.getText());
            Text text = new Text(value.getAuthor() + "\n" + value.getText());
            vBox.getChildren().add(text);
        }
        //send Comment
        User user = User.getUser();
        sendButton.setOnAction(event -> {
            Comment comment1 = new Comment(sendText.getText(), user.getEmail());
            String com = sendText.getText();
            System.out.println("com: " + com);
            if (!com.isEmpty()) {
                new InsertComment().saveCommentForPost(id, comment1);
                Text text = new Text(user.getEmail() + "\n" + com);
                vBox.getChildren().add(text);
                sendText.clear();
            } else {
                new alertException().setAlert("Сообщение пусто");
            }
        });

    }
}