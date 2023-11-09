package com.example.connectfour;

public class Circle {
    private int x;
    private int y;
    private Colors color;

    public Circle(int x, int y, Colors color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public static enum Colors {
        TRANSPARENT,
        RED,
        YELLOW
    }

}
