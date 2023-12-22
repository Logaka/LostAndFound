package com.example.demo2;

import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
public class SettingsController {
    @FXML
    private Button acceptButton;

    @FXML
    private Button back;

    @FXML
    private TextField iconPath;

    @FXML
    private TextField lastName;

    @FXML
    private TextField name;

    @FXML
    private TextField password;
    @FXML
    private ImageView icon;

    @FXML
    void initialize() throws Exception{
        User user = User.getUser();
        InsertUser insertUsers = new InsertUser();

        //when click on back
        back.setOnAction(event -> {
            backTo(back);
        });

        // when click on accept
        acceptButton.setOnAction(event -> {
            if(!name.getText().isEmpty()){
                insertUsers.updateData("name",name.getText(),user.getEmail());
                user.setName(name.getText());
            }
            if(!lastName.getText().isEmpty()){
                insertUsers.updateData("lastname",lastName.getText(),user.getEmail());
                user.setLastName(lastName.getText());
            }
            if(!password.getText().isEmpty()){
                String hashText = Integer.toString(password.getText().hashCode());
                insertUsers.updateData("password",hashText,user.getEmail());
                user.setPassword(Integer.parseInt(hashText));
            }
            if(!iconPath.getText().isEmpty()) {
                insertUsers.updateData("iconpath", iconPath.getText(), user.getEmail());
                user.setPath(iconPath.getText());
            }
            backTo(acceptButton);
        });
    }
    private void backTo(Button button){
        button.getScene().getWindow().hide();
        FXMLLoader forSignUp = new FXMLLoader(Controller.class.getResource("MainPage.fxml"));
        try {
            forSignUp.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = forSignUp.getRoot();
        Stage stage = new Stage();
        stage.setTitle("LostAndFound");
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    void fileHandle(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        File file = fileChooser.showOpenDialog(null);
        try {
            Image image = new Image(file.getAbsolutePath());
            iconPath.setText(file.getAbsolutePath());
            icon.setImage(image);
        }catch (NullPointerException ex){
        }
    }
}