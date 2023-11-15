package com.example.connectfour;

public class Game {


    private String gameState;

    private GameLogicTemplate gameLogic = new BasicGameLogic();
    private static Game gameInstance;

    private Game(){}

    public static Game getGameInstance() {
        if(gameInstance == null){
            gameInstance = new Game();
        }
        return gameInstance;
    }

    public class GameSnapshot {
        private String gameState;
        private GameSnapshot(String gameState) {
            this.gameState = gameState;
        }
        private String getGameState(){
            return this.gameState;
        }

    }
    //TODO: argument indicating of which type game we want to play
    public void resetGame(){
        gameLogic = new BasicGameLogic();
    }
    public Field[][] move(int columnIndex){
        gameLogic.tryMove(columnIndex);
        return gameLogic.getBoard();
    }
    public Field.Colors getCurrentTurn(){
        return gameLogic.getCurrentTurn();
    }

}
