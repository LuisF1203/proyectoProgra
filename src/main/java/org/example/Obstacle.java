package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * La clase Obstacle se encarga de generar y gestionar los obstáculos en el juego.
 */
public class Obstacle {
    public static final int OBSTACLE_WIDTH = 80;
    public static final int OBSTACLE_HEIGHT = 80;
    public static int OBSTACLE_GAP = 250;
    public static int OBSTACLE_SPEED = 8;

    public static Image obstacleImage;

    public static boolean firstObstacle = false;
    public static int lastObstacleY = Game2D.HEIGHT / 2 - Obstacle.OBSTACLE_HEIGHT / 2; // Posición inicial del primer obstáculo

    public static List<Rectangle> obstacles;

    /**
     * Genera un nuevo obstáculo en el juego.
     */
    public static void generateObstacles() {
        int obstacleY;
        int obstacleX = Game2D.WIDTH;

        // Comprobar si es el primer obstáculo generado
        if (Obstacle.obstacles.isEmpty()) {
            obstacleY = Obstacle.lastObstacleY + 200; // Mantener la posición inicial para el primer obstáculo
        } else {
            // Calcular límites superior e inferior para la posición vertical del obstáculo
            int minY = Math.max(0, Obstacle.lastObstacleY - Game2D.HEIGHT / 4);
            int maxY = Math.min(Game2D.HEIGHT - Obstacle.OBSTACLE_HEIGHT, Obstacle.lastObstacleY + Game2D.HEIGHT / 4);

            // Generar una posición aleatoria dentro de los límites establecidos
            obstacleY = Game2D.random.nextInt(maxY - minY + 1) + minY;

            // Ajustar la posición horizontal del obstáculo en relación con el último obstáculo generado
            obstacleX = Obstacle.obstacles.get(Obstacle.obstacles.size() - 1).x + Obstacle.OBSTACLE_GAP;
        }

        // Crear un nuevo rectángulo para representar el obstáculo y agregarlo a la lista de obstáculos
        Obstacle.obstacles.add(new Rectangle(obstacleX, obstacleY + 20, Obstacle.OBSTACLE_WIDTH, Obstacle.OBSTACLE_HEIGHT));

        // Actualizar la posición del último obstáculo generado
        Obstacle.lastObstacleY = obstacleY;

        // Programar la aparición del próximo obstáculo
        scheduleObstacleAppearance();
    }

    /**
     * Programa la aparición del próximo obstáculo en el juego.
     */
    public static void scheduleObstacleAppearance() {
        int delay = Game2D.random.nextInt(1000) + 500; // Delay aleatorio entre 500 y 1500 milisegundos

        // Crear un temporizador para generar el siguiente obstáculo después del delay establecido
        Timer obstacleTimer = new Timer(delay, e -> generateObstacles());
        obstacleTimer.setRepeats(false); // No se repite, se genera un único obstáculo
        obstacleTimer.start();
    }
}
