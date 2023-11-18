package com.example.connectfour;

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
        game.resetGame();
    }
    public void save(){
        game.save();
    }
    public Field[][] loadMoves(){
        return game.loadMoves();
    }
    public void loadGame(Game.GameSnapshot loadedSnapshot){
        game=Game.getGameInstance(loadedSnapshot);
    }
    public Field[][] sendMove(int columnIndex){
        return game.move(columnIndex);
    }
    public Field[][] sendUndoMove(){
        return game.undoMove();
    }
    public Field[][] sendRedoMove(){
        return game.redoMove();
    }

    public Field.Colors getCurrentTurn(){
        return game.getCurrentTurn();
    }
}
