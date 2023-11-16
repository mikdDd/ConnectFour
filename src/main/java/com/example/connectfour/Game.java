package com.example.connectfour;

import java.util.Stack;
public class Game {


    private String gameState;

    private GameLogicTemplate gameLogic = new BasicGameLogic();
    private static Game gameInstance;
    private static GameSnapshot gameSnapshot;

    private Game(){}

    public static Game getGameInstance() {
        if(gameInstance == null){
            gameInstance = new Game();
        }
        if (gameSnapshot == null){
            gameSnapshot = new GameSnapshot(new Stack<Integer>(),new Stack<Integer>());
        }
        return gameInstance;
    }

    public static class GameSnapshot {
        private Stack<Integer> undoStack;
        private Stack<Integer> redoStack;
        public GameSnapshot(Stack<Integer> undoStack, Stack<Integer> redoStack) {
            this.undoStack = undoStack;
            this.redoStack = redoStack;
        }

    }
    //TODO: argument indicating of which type game we want to play
    public void resetGame(){
        gameLogic = new BasicGameLogic();
    }
    public Field[][] move(int columnIndex){
        gameLogic.tryMove(columnIndex);
        gameSnapshot.undoStack.push(columnIndex);
        return gameLogic.getBoard();
    }
    public Field [][] undoMove(){
        if (gameSnapshot.undoStack.size()!=0){
            int columnIndex= gameSnapshot.undoStack.pop();
            gameSnapshot.redoStack.push(columnIndex);

            gameLogic.tryUndo(columnIndex);
        }

        return gameLogic.getBoard();
    }

    public Field [][] redoMove(){
        if (gameSnapshot.redoStack.size()!=0){
            int columnIndex= gameSnapshot.redoStack.pop();
            gameSnapshot.undoStack.push(columnIndex);

            gameLogic.tryRedo(columnIndex);
        }

        return gameLogic.getBoard();
    }
    public Field.Colors getCurrentTurn(){
        return gameLogic.getCurrentTurn();
    }

}
