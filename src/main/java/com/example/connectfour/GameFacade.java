package com.example.connectfour;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class GameFacade {
    private static GameFacade instance;
    private GameFacade(){}
    private Game game;
    private Stack<Game.GameSnapshot> gameSnapshots;
    private Stack<Game.GameSnapshot> undoneSnapshots;
    public static GameFacade getGameFacadeInstance(){
        if(instance == null){
            instance = new GameFacade();
        }
        return instance;
    }
    public void initGame(Stack<Game.GameSnapshot> stack){
        game = Game.getGameInstance();

        if(stack == null){
            gameSnapshots = new Stack<>();
            game.resetGame(null);
            System.out.println("CREATING NEW STACK");
        } else {
            gameSnapshots = stack;
            game.resetGame(stack.peek());
            System.out.println("STACK NOT NULL");
            gameSnapshots.pop();
        }
        undoneSnapshots = new Stack<>();

    }
    public void saveStackToFile() throws IOException {
        FileOutputStream f = new FileOutputStream(new File("save_file.txt"));
        ObjectOutputStream o = new ObjectOutputStream(f);

        o.writeObject(gameSnapshots);

        o.close();
        f.close();
    }

    public Field[][] sendMove(int columnIndex){
        Field[][] boardBefore = Field.boardDeepCopy(game.getBoard());
        Field[][] boardAfterMove = game.move(columnIndex);

        if(Field.boardDeepEqual(boardBefore, boardAfterMove) && columnIndex >= 0){  //move request sent but move is not possible
            return boardAfterMove;
        }

        gameSnapshots.push(game.save());

        undoneSnapshots.clear(); //breaking undo chain

        return boardAfterMove;
    }
    public Field[][] sendUndoMove(){
        if(gameSnapshots.size() <= 1) {
            return game.getBoard();
        }


        System.out.println("SIZE AFTER POP"+gameSnapshots.size());

        Game.GameSnapshot lastMove = gameSnapshots.pop();
        undoneSnapshots.push(lastMove);
        return game.restore(gameSnapshots.peek());
    }
    public Field[][] sendRedoMove(){
        System.out.println("RESTORING");
        if(undoneSnapshots.isEmpty()){
            return game.getBoard();
        }
        Game.GameSnapshot gameSnapshot = undoneSnapshots.pop();
        gameSnapshots.push(gameSnapshot);
        return game.restore(gameSnapshot);
    }

    public Field.Colors getCurrentTurn(){
        return game.getCurrentTurn();
    }
}
