package com.example.connectfour;


public class BasicGameLogic extends GameLogicTemplate  {
    private static final int BASICGAMEROWCOUNT = 6;
    private static final int BASCIGAMECOLUMNCOUNT = 7;

    public BasicGameLogic(Field[][] board) {
        super(BASICGAMEROWCOUNT, BASCIGAMECOLUMNCOUNT);
        this.board = Field.boardDeepCopy(board);

            if(countFieldsOfColour(Field.Colors.YELLOW) > countFieldsOfColour(Field.Colors.RED)){
                this.currentTurn = Field.Colors.RED;
            } else {
                this.currentTurn = Field.Colors.YELLOW;
            }

    }
    public BasicGameLogic() {
        super(BASICGAMEROWCOUNT, BASCIGAMECOLUMNCOUNT);
            clearBoard();
    }

    @Override
    public void tryMove(int columnIndex) {
        if(winner!=null)return;
        if(columnIndex < 0 || columnIndex > BASCIGAMECOLUMNCOUNT)
            return;
        int rowIndex = -1;
        for (int i = 0; i < BASICGAMEROWCOUNT; i++) {

            if((!board[i][columnIndex].color.equals(Field.Colors.TRANSPARENT)) ){

                if(i == 0) return ;  //column full we should not proceed move
                board[i-1][columnIndex].color = currentTurn;
                rowIndex = i-1;
                break;
            }
            if(i==BASICGAMEROWCOUNT - 1){
                board[BASICGAMEROWCOUNT - 1][columnIndex].color = currentTurn;
                rowIndex = BASICGAMEROWCOUNT - 1;
            }
        }

        checkIfWon(rowIndex, columnIndex);
        changeTurn();

    }

    @Override
    protected void changeTurn() {
        if(currentTurn == Field.Colors.RED){
            currentTurn = Field.Colors.YELLOW;
        } else {
            currentTurn = Field.Colors.RED;
        }
    }
    @Override
    protected Field.Colors previousTurn() {
        if(currentTurn == Field.Colors.RED){
            return Field.Colors.YELLOW;
        } else {
            return Field.Colors.RED;
        }
    }

    @Override
    protected boolean checkIfWon(int rowIndex, int colIndex) {

        winner = null;

        //row check
        Field.Colors color = board[rowIndex][colIndex].color;
        if(color.equals(Field.Colors.TRANSPARENT)) return false;
        int columnIndexRight = colIndex+1;
        int columnRightCounter = 0;
        int columnIndexLeft = colIndex-1;
        int columnLeftCounter = 0;
        while(columnIndexLeft >= 0 && board[rowIndex][columnIndexLeft].color == color){
            columnIndexLeft--;
            columnLeftCounter++;
        }
        while(columnIndexRight < BASCIGAMECOLUMNCOUNT && board[rowIndex][columnIndexRight].color == color){
            columnIndexRight++;
            columnRightCounter++;
        }

        if(columnLeftCounter + columnRightCounter + 1 == 4){
            winner = currentTurn;
            return true;
        }

        //column check
        int rowIndexDown = rowIndex+1;
        int rowDownCounter = 0;
        int rowIndexUp = rowIndex-1;
        int rowUpCounter = 0;
        while(rowIndexDown < BASICGAMEROWCOUNT && board[rowIndexDown][colIndex].color == color){
            rowIndexDown++;
            rowDownCounter++;
        }
        while(rowIndexUp >= 0 && board[rowIndexUp][colIndex].color == color){
            rowIndexUp--;
            rowUpCounter++;
        }

        if(rowDownCounter + rowUpCounter + 1 == 4){
            winner = currentTurn;
            return true;
        }


        //1diagonal check
        rowIndexDown = rowIndex + 1;
        int colIndexRight = colIndex + 1;
        int downRightCounter = 0;
        rowIndexUp = rowIndex-1;
        int colIndexLeft = colIndex - 1;
        int upLeftCounter = 0;
        while(rowIndexDown < BASICGAMEROWCOUNT && colIndexRight <BASCIGAMECOLUMNCOUNT && board[rowIndexDown][colIndexRight].color == color){
            rowIndexDown++;
            colIndexRight++;
            downRightCounter++;
        }
        while(rowIndexUp >= 0 && colIndexLeft >= 0 && board[rowIndexUp][colIndexLeft].color == color){
            rowIndexUp--;
            colIndexLeft--;
            upLeftCounter++;
        }

        if(downRightCounter + upLeftCounter + 1 == 4){
            winner = currentTurn;
            return true;
        }


        //2diagonal check
        rowIndexDown = rowIndex + 1;
        colIndexRight = colIndex + 1;
        int downLeftCounter = 0;
        rowIndexUp = rowIndex-1;
        colIndexLeft = colIndex - 1;
        int upRightCounter = 0;

        while(rowIndexUp >= 0 && colIndexRight < BASCIGAMECOLUMNCOUNT && board[rowIndexUp][colIndexRight].color == color){
            rowIndexUp--;
            colIndexRight++;
            upRightCounter++;
        }
        while(rowIndexDown < BASICGAMEROWCOUNT && colIndexLeft >= 0 && board[rowIndexDown][colIndexLeft].color == color){
            rowIndexDown++;
            colIndexLeft--;
            downLeftCounter++;
        }

        if(upRightCounter + downLeftCounter + 1 == 4) {
            winner = currentTurn;
            return true;
        }

        return false;
    }
}
