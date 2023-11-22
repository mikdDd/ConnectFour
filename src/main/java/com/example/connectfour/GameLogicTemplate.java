package com.example.connectfour;

import java.io.Serializable;

public abstract class GameLogicTemplate implements Serializable {
    protected Field[][] board;
    protected Field.Colors winner;
    private final int boardColumnCount;  //final to force initialization in extending class
    private final int boardRowCount;
    protected Field.Colors firstTurn = Field.Colors.YELLOW;
    protected Field.Colors currentTurn = firstTurn;

    public abstract void tryMove(int columnIndex);
    protected abstract void changeTurn();

    protected abstract Field.Colors previousTurn();

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
    protected boolean extensiveCheckIfWon(){
        for(int i = 0; i < boardRowCount; i++){
            for(int j = 0; j < boardColumnCount; j++){
                if(checkIfWon(i,j)){
                    winner = previousTurn();
                    return true;
                }

            }
        }
        return false;
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
    public void resetWinner(){
        winner = null;
    }
}
