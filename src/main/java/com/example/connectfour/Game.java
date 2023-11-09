package com.example.connectfour;

public class Game {
    private Circle[] Board;
    private String gameState;


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

}
