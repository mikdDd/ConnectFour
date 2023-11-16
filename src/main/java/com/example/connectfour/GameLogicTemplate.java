package com.example.connectfour;

public abstract class GameLogicTemplate {
    protected Field[][] board;
    private final int boardColumnCount;  //final to force initialization in extending class
    private final int boardRowCount;
    protected Field.Colors currentTurn = Field.Colors.YELLOW;

    public abstract void tryUndo(int columnIndex);
    public abstract void tryRedo(int columnIndex);
    public abstract void tryMove(int columnIndex);
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
        return  board;
    }

    public Field.Colors getCurrentTurn() {
        return currentTurn;
    }
}
