package com.example.demo2.Check;

import javafx.scene.control.ChoiceBox;

public class isCorrect {
    public boolean isCorrectAll(String name,String lastname,String password,String email){
        System.out.println(new emailChecker().isValid(email)+"1\n"+new NameChecker().isValid(name)+"2\n"+new NameChecker().isValid(lastname)+"3\n"+new passwordChecker().isValid(password)+"4");
        return (new emailChecker().isValid(email) && new NameChecker().isValid(name) && new NameChecker().isValid(lastname) && new passwordChecker().isValid(password));
    }
    public boolean isCorrectPost(String path, String choiceBox,String comm){
        PostChecker postChecker = new PostChecker();
        System.out.println(postChecker.pathChecker(path)+" 1 "+postChecker.boxChecker(choiceBox)+" 2 "+ postChecker.commentChecker(comm)+" 3");
        return (postChecker.pathChecker(path) && postChecker.boxChecker(choiceBox) && postChecker.commentChecker(comm));
    }
}
