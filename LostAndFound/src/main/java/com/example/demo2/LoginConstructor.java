package com.example.demo2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.example.demo2.Check.*;

public class LoginConstructor {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Hyperlink SigninLink;

    @FXML
    private PasswordField passText;

    @FXML
    private TextField userText;

    @FXML
    private Button login;


    @FXML
    void initialize() {


        //link to sign in window
        SigninLink.setOnAction(actionEvent -> {
            SigninLink.getScene().getWindow().hide();
            FXMLLoader forSignUp = new FXMLLoader(SignUpConstructor.class.getResource("signup-view.fxml"));
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
        });


        login.setOnAction(actionEvent -> {
            if(!userText.getText().isEmpty() && !passText.getText().isEmpty()){

                // Class to get data from db
                DataGetter data = new DataGetter();

                // set data to user
                User user = User.getUser();

                // getting datas from db
                user.setName(data.
                        getStringData("users", data.getDataId("users", userText.getText()), "name")
                );
                user.setLastName(data.
                        getStringData("users", data.getDataId("users", userText.getText()), "lastName")
                );
                user.setEmail(data.
                        getStringData("users", data.getDataId("users", userText.getText()), "email")
                );
                user.setPath(data.
                        getStringData("users", data.getDataId("users", userText.getText()), "iconpath")
                );

                int usersPassword  = passText.getText().hashCode();
                try {
                    if (Integer.parseInt(data.getStringData("users", data.getDataId("users", userText.getText()), "password")) == usersPassword) {
                        login.getScene().getWindow().hide();
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
                }catch (Exception e) {
                    new alertException().setAlert("Wrong User Email or Password");
                }
            }
            else new alertException().setAlert("User Email or Password is not entered");
        });
    }

}