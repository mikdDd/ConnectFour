package com.example.connectfour;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
        gameFacade.initGame();
    }
    //TODO:
    private void sendMove(int columnIndex){

    }

    @FXML
    private void mousePressed(MouseEvent event) {
        Node source = (Node)event.getSource() ;
        System.out.println(source);

        Integer colIndex = GridPane.getColumnIndex(source);
        Integer rowIndex = GridPane.getRowIndex(source);
        System.out.printf("Mouse entered cell [%d, %d]%n", colIndex, rowIndex);
        sendMove(colIndex);
    }
    @FXML
    void onRedoButtonClick(MouseEvent event) {
        //TODO:
    }

    @FXML
    void onSaveButtonClick(MouseEvent event) {
        //TODO:
    }

    @FXML
    void onUndoButtonClick(MouseEvent event) {
        //TODO:
    }
}