package org.example;

import java.awt.*;
import java.util.Random;

public class Enemy {
    public static final int ENEMY_RADIUS = 50;
    public static int ENEMY_SPEED = 9;
    public static boolean ENEMY=false;
    public static boolean isEnemyMoving = true;
    public static void stopCircle() {
        isEnemyMoving = false;
    }
    public static void playCircle(){
        isEnemyMoving = true;
    }

}
