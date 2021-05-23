package com.mygdx.game;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;

public class Monster {
    int numMonsters = MathUtils.random(0, 1);
    float x, y;
    float width, height;
    int faza = 0;
    int state;
    float dx;
    int life, damage;
    public static final int STAY = 0, GO = 1, ATTACK = 2, DEATH = 3, GOOUT = 4;
    long timeLastFaza, timeFasaInterval;
    boolean isAlive = true;

    public Monster(float x, float y, float width, float height, int life, int damage, long timeFasaInterval) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.life = life;
        this.damage = damage;
        this.timeFasaInterval = timeFasaInterval;
        state = GO;
        dx = -10;
        life = 100;
        damage = 50;
    }
    void move(){
        if (timeLastFaza+timeFasaInterval < TimeUtils.millis()) {
            timeLastFaza = TimeUtils.millis();
            switch (state) {
                case STAY:
                    if (++faza == 10) {
                        faza = 0;
                        state = MathUtils.random(0, 1);
                        if (x <= 50 + 330) state = ATTACK;
                    }
                    break;
                case GO:
                    x += dx;
                    if (++faza == 20) {
                        faza = 0;
                        state = STAY;
                    }
                    break;
                case ATTACK:
                    if (++faza == 10) {
                        faza = 0;
                        state = STAY;
                    }
                    break;
                case DEATH:
                    if (++faza >= 10) {
                        faza = 0;
                        state = GOOUT;
                    }
                    break;
                case GOOUT:
                    faza = 0;
                    break;
            }
        }
    }
}
