package com.mygdx.game;

public class Bullet {
    float x, y;
    float width, height;
    float dx = 40;
    boolean isAlive = true;

    public Bullet(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    void move(){
        x+=dx;
    }
}
