package com.example.connectfour;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Field {
    private int x;
    private int y;
    public Colors color;

    public Field(int x, int y, Colors color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public static enum Colors {
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

}
