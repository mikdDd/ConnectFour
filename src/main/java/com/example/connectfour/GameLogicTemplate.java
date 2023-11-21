package com.example.connectfour;

import java.io.Serializable;

public abstract class GameLogicTemplate implements Serializable {
    protected Field[][] board;
    private final int boardColumnCount;  //final to force initialization in extending class
    private final int boardRowCount;
    protected Field.Colors firstTurn = Field.Colors.YELLOW;
    protected Field.Colors currentTurn = firstTurn;

    public abstract boolean tryMove(int columnIndex);
    protected abstract void changeTurn();
    protected abstract boolean checkIfWon(int r, int c);
    GameLogicTemplate(int boardRowCount, int boardColumnCount){
        this.boardRowCount = boardRowCount;
        this.boardColumnCount = boardColumnCount;
        board = new Field[boardRowCount][boardColumnCount];
    }

    public void clearBoard(){
        for (int i = 0; i < boardRowCount; i++) {
            for(int j = 0; j< boardColumnCount; j++){
                board[i][j] = new Field(i,j, Field.Colors.TRANSPARENT);
            }

        }
    }
    public Field[][] getBoard(){
        return board;
    }

    public Field.Colors getCurrentTurn() {
        return currentTurn;
    }
    protected int countFieldsOfColour(Field.Colors color){
        int counter = 0;
        for (int i = 0; i < boardRowCount; i++) {
            for(int j = 0; j < boardColumnCount; j++){
                if(board[i][j].color.equals(color))
                    counter++;
            }
        }
        return counter;
    }
}
