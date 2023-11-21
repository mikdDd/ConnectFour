package com.example.connectfour;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Stack;

public class AppMenuController {
    @FXML
    private Label welcomeText;

    @FXML
    public void onStartGameButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("game-view.fxml")));

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root,700,700));
        stage.show();

    }

    @FXML
    public void onLoadFromFileButtonClick(ActionEvent event) throws IOException, ClassNotFoundException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("game-view.fxml"));

        FileInputStream fi = new FileInputStream(new File("save_file.txt"));
        ObjectInputStream oi = new ObjectInputStream(fi);


        Stack<Game.GameSnapshot> stack = (Stack<Game.GameSnapshot>) oi.readObject();

        oi.close();
        fi.close();
        System.out.println(stack.size());
        loader.setControllerFactory(gameViewController -> new GameViewController(stack));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }


}