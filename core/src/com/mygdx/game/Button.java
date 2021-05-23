package com.mygdx.game;

public class Button {
    public float x, y;
    float width, height;
    boolean drag;

    public Button(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    boolean isHit(float x, float y){
        return x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.height;
    }
}
