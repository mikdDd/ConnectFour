package com.example.connectfour;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class GameViewController implements Initializable {
    private GameFacade gameFacade;
    @FXML
    private GridPane GridPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gameFacade = GameFacade.getGameFacadeInstance();

    }
    //TODO:
    private void createGame(){
        //gameFacade.createGame()
    }
    //TODO:
    private void updateView(){

    }
}