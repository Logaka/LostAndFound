package com.example.demo2.Check;

import javafx.scene.control.Alert;

public class confirmException {
    public void setConfirm(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Confirm");
        alert.setContentText("Congratulations, everything worked out well");
        alert.showAndWait();
    }
}
