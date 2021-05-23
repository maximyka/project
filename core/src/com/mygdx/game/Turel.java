package com.mygdx.game;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;

public class Turel {
    float x, y;
    float width, height;
    int faza = 0;
    int state;
    float dx;
    int life, damage;
    public static final int STAYTUREL = 0, ATTACKTUREL = 1, DEATHTUREL = 2;
    long timeLastFaza, timeFasaInterval = 40;
    boolean isAlive = true;

    public Turel(float x, float y, float width, float height, int life, int damage) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.life = life;
        this.damage = damage;
        dx = -10;
        life = 100;
        damage = 50;
    }
    void move(){
        if (timeLastFaza+timeFasaInterval < TimeUtils.millis()) {
            timeLastFaza = TimeUtils.millis();
            switch (state) {
                case STAYTUREL:
                    if (++faza == 1) {
                        faza = 0;
                        if (Gameproject1.fazaTurelShoot == true) state = ATTACKTUREL;
                    }
                    break;
                case ATTACKTUREL:
                    if (++faza == 20) {
                        faza = 0;
                        state = STAYTUREL;
                        Gameproject1.fazaTurelShoot = false;
                    }
                    break;
                case DEATHTUREL:
                    if (++faza == 10) {
                        faza = 0;
                    }
                    break;
            }
        }
    }
}
