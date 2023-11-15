package com.example.connectfour;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GameViewController implements Initializable {
    private GameFacade gameFacade;
    @FXML
    private GridPane GridPane;
    @FXML
    private Circle turnIndicator;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gameFacade = GameFacade.getGameFacadeInstance();
        gameFacade.initGame();
        turnIndicator.setFill(Field.getFXColor(gameFacade.getCurrentTurn()));
    }

    //TODO:
    private void requestMove(int columnIndex){
        Field[][] board = gameFacade.sendMove(columnIndex);
        updateView(board);
    }

    @FXML
    private void mousePressed(MouseEvent event) {
        Node source = (Node)event.getSource() ;
        System.out.println(source);

        Integer colIndex = GridPane.getColumnIndex(source);
        Integer rowIndex = GridPane.getRowIndex(source);
        System.out.printf("Mouse entered cell [%d, %d]%n", colIndex, rowIndex);
        requestMove(colIndex);
    }
    private void updateView(Field[][] board){
        turnIndicator.setFill(Field.getFXColor(gameFacade.getCurrentTurn()));
        ObservableList<Node> nodeList = GridPane.getChildren();

        for (Node node : nodeList){
            Integer colIndex = GridPane.getColumnIndex(node);
            Integer rowIndex = GridPane.getRowIndex(node);
            if(board[rowIndex][colIndex].color == Field.Colors.RED){
               Circle c = (Circle) node;
               c.setFill(Color.RED);
            } else if (board[rowIndex][colIndex].color == Field.Colors.YELLOW) {
                Circle c = (Circle) node;
                c.setFill(Color.YELLOW);
            } else {
                Circle c = (Circle) node;
                c.setFill(Color.WHITE);
            }
        }


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