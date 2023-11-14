package com.example.connectfour;

import javafx.scene.layout.GridPane;

public class GameFacade {
    private static GameFacade instance;
    private GameFacade(){}
    private Game game;
    private Game.GameSnapshot[] gameSnapshots;
    public static GameFacade getGameFacadeInstance(){
        if(instance == null){
            instance = new GameFacade();
        }
        return instance;
    }
    public void initGame(){
        game = Game.getGameInstance();
    }
    public void sendMove(){
        //game.move() or something
    }
}
