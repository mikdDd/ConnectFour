package com.example.connectfour;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Game {


    private GameLogicTemplate gameLogic = new BasicGameLogic();
    private static Game gameInstance;
    private static GameSnapshot gameSnapshot;

    private Game(){}

    public static Game getGameInstance() {
        gameInstance = new Game();
        gameSnapshot = new GameSnapshot(new Stack<Integer>(),new Stack<Integer>());
        return gameInstance;
    }

    public static Game getGameInstance(Game.GameSnapshot loadSnaphot) {
        gameInstance = new Game();
        gameSnapshot = loadSnaphot;
        return gameInstance;
    }

    public static class GameSnapshot extends ResourceBundle {
        private Stack<Integer> undoStack;
        private Stack<Integer> redoStack;
        public GameSnapshot(Stack<Integer> undoStack, Stack<Integer> redoStack) {
            this.undoStack = undoStack;
            this.redoStack = redoStack;
        }

        public Stack<Integer> getUndoStack() {
            return undoStack;
        }

        public Stack<Integer> getRedoStack() {
            return redoStack;
        }

        @Override
        protected Object handleGetObject(String key) {
            return null;
        }

        @Override
        public Enumeration<String> getKeys() {
            return null;
        }
    }
    //TODO: argument indicating of which type game we want to play
    public void resetGame(){
        gameLogic = new BasicGameLogic();
    }
    public Field[][] move(int columnIndex){
        gameLogic.tryMove(columnIndex);
        gameSnapshot.undoStack.push(columnIndex);
        gameSnapshot.redoStack.clear();
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

    public void save(){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("save_file.txt",false))) {
            for (Integer value : gameSnapshot.getUndoStack()) {
                bw.write(value.toString());
            }
            bw.newLine();
            for (Integer value : gameSnapshot.getRedoStack()) {
                bw.write(value.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Field[][] loadMoves(){
        List<Integer> moveList =new ArrayList<>(gameSnapshot.undoStack);
        for (int i = moveList.size() - 1; i >= 0; i--) {
            System.out.println(moveList.get(i));
            gameLogic.tryMove(moveList.get(i));
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
