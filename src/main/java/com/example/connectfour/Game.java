package com.example.connectfour;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.util.*;

public class Game implements Serializable{


    private GameLogicTemplate gameLogic;
    private static Game gameInstance;
    private static GameSnapshot gameSnapshot;

    private Game(){}

    public static Game getGameInstance() {
        if(gameInstance == null){
            gameInstance = new Game();
        }
        return gameInstance;
//        gameInstance = new Game();

    }



    //TODO: argument indicating of which type game we want to play
    public void resetGame(GameSnapshot gameSnapshot){
        if(gameSnapshot == null){
            gameLogic = new BasicGameLogic();
        } else {
            gameLogic = new BasicGameLogic(gameSnapshot.getGameState());
        }

    }
    public Field[][] move(int columnIndex){
        gameLogic.tryMove(columnIndex);

        return gameLogic.getBoard();
    }

    public GameSnapshot save(){
        return new GameSnapshot(gameLogic.getBoard());
    }

    public Field[][] restore(GameSnapshot gameSnapshot){

        gameLogic.board = Field.boardDeepCopy(gameSnapshot.getGameState());
        gameLogic.changeTurn();

        return gameLogic.getBoard();
    }

    public Field.Colors getCurrentTurn(){
        return gameLogic.getCurrentTurn();
    }

    public static void debug_print(Field[][] board){
        System.out.println("");
        for(int i = 0; i< board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                System.out.print("["+board[i][j].color+"]");
            }
            System.out.println("");
        }
    }
    public Field[][] getBoard(){
        return this.gameLogic.getBoard();
    }

    public class GameSnapshot implements Serializable {

        private Field[][] gameState;

        private GameSnapshot(Field[][] gameState) {
//            this.gameState = new Field[gameState.length][gameState[0].length];
//
//            for(int i = 0; i<gameState.length; i++){
//                for(int j = 0; j<gameState[0].length;j++){
//                    Field f = new Field(i,j,gameState[i][j].color);
//                    this.gameState[i][j]=f;
//                }
//            }
            this.gameState = Field.boardDeepCopy(gameState);
        }
        private Field[][] getGameState(){
            return this.gameState;
        }


    }
}
