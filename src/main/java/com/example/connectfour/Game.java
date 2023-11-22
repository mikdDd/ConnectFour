package com.example.connectfour;

import java.io.Serializable;

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

    public Field.Colors getWinner(){
        return gameLogic.winner;
    }
    public void resetWinner(){
        gameLogic.resetWinner();
    }

    public Field[][] getBoard(){
        return this.gameLogic.getBoard();
    }
    public boolean extensiveCheckIfWon(){
        return gameLogic.extensiveCheckIfWon();
    }
    public class GameSnapshot implements Serializable {

        private Field[][] gameState;

        private GameSnapshot(Field[][] gameState) {
            this.gameState = Field.boardDeepCopy(gameState);
        }
        private Field[][] getGameState(){
            return this.gameState;
        }
    }
}
