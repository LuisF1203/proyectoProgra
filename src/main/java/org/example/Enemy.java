package org.example;

import java.awt.*;
import java.util.Random;

/**
 * Clase que representa a un enemigo en el juego.
 */
public class Enemy {
    // Radio del enemigo en píxeles
    public static final int ENEMY_RADIUS = 50;
    // Velocidad del enemigo en píxeles por segundo
    public static int ENEMY_SPEED = 9;
    // Indica si hay enemigo o no
    public static boolean ENEMY = false;
    // Indica si el enemigo se está moviendo o no
    public static boolean isEnemyMoving = true;

    /**
     * Detiene el movimiento del enemigo.
     */
    public static void stopCircle() {
        isEnemyMoving = false;
    }

    /**
     * Inicia el movimiento del enemigo.
     */
    public static void playCircle() {
        isEnemyMoving = true;
    }
}