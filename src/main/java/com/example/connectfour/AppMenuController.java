package com.example.connectfour;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
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
    public void onLoadFromFileButtonClick(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("game-view.fxml"));

        Game.GameSnapshot gameSnapshot = readLines();
        loader.setResources(gameSnapshot);

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    private Game.GameSnapshot readLines(){
        Game.GameSnapshot gameSnapshot= new Game.GameSnapshot(new Stack<Integer>(),new Stack<Integer>());

        try {
            List<String> allLines= Files.readAllLines(Paths.get("save_file.txt"));
            String undoString=allLines.get(0);
            for (int i=0;i<undoString.length();i++){
                gameSnapshot.getUndoStack().push(Character.getNumericValue(undoString.charAt(i)));
            }
            String redoString=allLines.get(1);
            for (int i=0;i<redoString.length();i++){
                gameSnapshot.getRedoStack().push(Character.getNumericValue(redoString.charAt(i)));
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        return gameSnapshot;
    }
}