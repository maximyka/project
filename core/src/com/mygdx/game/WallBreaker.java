package com.mygdx.game;

import com.badlogic.gdx.utils.TimeUtils;

public class WallBreaker {
    float x, y;
    float width, height;
    int faza = 0;
    int state;
    float dx;
    int life, damage;
    public static final int GOSKELET = 0, ATTACKSKELET = 1, GOOUTSKELET = 2;
    long timeLastFaza, timeFasaInterval;
    boolean isAlive = true;

    public WallBreaker(float x, float y, float width, float height, int life, int damage, long timeFasaInterval) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.life = life;
        this.damage = damage;
        this.timeFasaInterval = timeFasaInterval;
        state = GOSKELET;
        dx = -50;
        life = 20;
        damage = 50;
    }
    void move(){
        if (timeLastFaza+timeFasaInterval < TimeUtils.millis()) {
            timeLastFaza = TimeUtils.millis();
            switch (state) {
                case GOSKELET:
                    x += dx;
                    if (++faza == 20) {
                        faza = 0;
                        state = GOSKELET;
                    }
                    break;
                case ATTACKSKELET:
                    if (++faza == 10) {
                        faza = 0;
                        state = GOOUTSKELET;
                    }
                    break;
                case GOOUTSKELET:
                    faza = 0;
                    break;
            }
        }
    }
}
