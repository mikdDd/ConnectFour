package com.example.connectfour;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

public class GameViewController implements Initializable {
    private GameFacade gameFacade;
    @FXML
    private GridPane GridPane;
    @FXML
    private Circle turnIndicator;

    public GameViewController(Stack<Game.GameSnapshot> stack){
        gameFacade = GameFacade.getGameFacadeInstance();
        gameFacade.initGame(stack);
    }
    public GameViewController(){
        gameFacade = GameFacade.getGameFacadeInstance();
        gameFacade.initGame(null);

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        turnIndicator.setFill(Field.getFXColor(gameFacade.getCurrentTurn()));
        updateView(gameFacade.sendMove(-1));  //just to update view

    }

    private void requestMove(int columnIndex) throws IOException {
        Field[][] board = gameFacade.sendMove(columnIndex);
        updateView(board);
    }

    private void requestRedoMove(){
        Field[][] board= gameFacade.sendRedoMove();
        updateView(board);
    }
    private void requestUndoMove(){
        Field[][] board= gameFacade.sendUndoMove();
        System.out.println(board);
        updateView(board);
    }

    @FXML
    private void mousePressed(MouseEvent event) throws IOException {
        Node source = (Node)event.getSource() ;
        System.out.println(source);

        Integer colIndex = GridPane.getColumnIndex(source);
        Integer rowIndex = GridPane.getRowIndex(source);
        System.out.printf("Mouse entered cell [%d, %d]%n", colIndex, rowIndex);

        requestMove(colIndex);

        if (gameFacade.getGameWinner()!=null){
            onWinConditionMet(gameFacade.getGameWinner());
        }
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
    void onRedoButtonClick(MouseEvent event) throws IOException {
        requestRedoMove();
        if (gameFacade.getGameWinner()!=null){
            onWinConditionMet(gameFacade.getGameWinner());
        }
    }

    @FXML
    void onSaveButtonClick(MouseEvent event) throws IOException {
        gameFacade.saveStackToFile();
        Stage stage=(Stage) GridPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onUndoButtonClick(MouseEvent event) {
        requestUndoMove();
    }
    private void onWinConditionMet(Field.Colors currentTurn) throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.getNamespace().put("color",currentTurn.toString());
        loader.setLocation(getClass().getResource("win_condition_view.fxml"));
        Parent root=loader.load();

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root,250,250));
        stage.show();
    }
}