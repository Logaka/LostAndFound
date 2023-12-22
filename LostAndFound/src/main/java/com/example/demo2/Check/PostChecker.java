package com.example.demo2.Check;

import javafx.scene.control.ChoiceBox;

public class PostChecker {

    public boolean pathChecker(String path){
        return (path.endsWith(".jpg") || path.endsWith(".jpeg") || path.endsWith(".png"));
    }
    public boolean boxChecker(String choice){
        return choice != null;
    }
    public boolean commentChecker(String comm){
        return (!comm.isEmpty());
    }
}
