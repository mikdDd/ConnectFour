package com.example.connectfour;

import javafx.scene.paint.Color;

import java.io.Serializable;

public class Field implements Serializable {
    private final int x;
    private final int y;
    public Colors color;

    public Field(int x, int y, Colors color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public enum Colors {
        TRANSPARENT,
        RED,
        YELLOW
    }
    public static javafx.scene.paint.Color getFXColor(Colors color){
        if(color.equals(Colors.RED))
            return javafx.scene.paint.Color.RED;
        if(color.equals(Colors.YELLOW))
            return javafx.scene.paint.Color.YELLOW;
        return Color.WHITE;
    }
    public static Field[][] boardDeepCopy(Field[][] copyBoard){
        Field[][] board = new Field[copyBoard.length][copyBoard[0].length];
        for(int i = 0; i<board.length; i++){
            for(int j = 0; j<board[0].length;j++){
                Field f = new Field(i,j,copyBoard[i][j].color);
                board[i][j]=f;
            }
        }
        return board;
    }
    public static boolean boardDeepEqual(Field[][] board1, Field[][] board2){
        for(int i = 0; i<board1.length; i++){
            for(int j = 0; j<board1[0].length;j++){
                if(!board1[i][j].color.equals(board2[i][j].color))
                    return false;
            }
        }
        return true;
    }

}
