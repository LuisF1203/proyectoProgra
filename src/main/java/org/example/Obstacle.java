package org.example;


import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Obstacle {
    public static final int OBSTACLE_WIDTH = 80;
    public static final int OBSTACLE_HEIGHT = 80;
    public static int OBSTACLE_GAP = 250;
    public static int OBSTACLE_SPEED = 8;

    public static Image obstacleImage;



    public static boolean firstObstacle = false;
    public static int lastObstacleY = Game2D.HEIGHT / 2 -  Obstacle.OBSTACLE_HEIGHT / 2; // Posición inicial del primer obstáculo



    public static List<Rectangle> obstacles;

    public static void generateObstacles() {
        int obstacleY;
        int obstacleX = Game2D.WIDTH;
        if (Obstacle.obstacles.isEmpty()) {
            obstacleY = Obstacle.lastObstacleY + 200; // Mantener la posición inicial para el primer obstáculo
        } else {
            int minY = Math.max(0, Obstacle.lastObstacleY - Game2D.HEIGHT / 4); // Límite inferior para la posición vertical del obstáculo
            int maxY = Math.min(Game2D.HEIGHT - Obstacle.OBSTACLE_HEIGHT, Obstacle.lastObstacleY + Game2D.HEIGHT / 4); // Límite superior para la posición vertical del obstáculo
            obstacleY = Game2D.random.nextInt(maxY - minY + 1) + minY; // Generar posición aleatoria dentro de los límites
            obstacleX = Obstacle.obstacles.get(Obstacle.obstacles.size() - 1).x + Obstacle.OBSTACLE_GAP; // No ajustar la posición x del obstáculo en relación con el fondo
        }
        Obstacle.obstacles.add(new Rectangle(obstacleX, obstacleY + 20, Obstacle.OBSTACLE_WIDTH, Obstacle.OBSTACLE_HEIGHT));
        Obstacle.lastObstacleY = obstacleY;
        scheduleObstacleAppearance();
    }

    public static void scheduleObstacleAppearance() {
        int delay = Game2D.random.nextInt(1000) + 500; // Delay aleatorio entre 500 y 1500 milisegundos
        Timer obstacleTimer = new Timer(delay, e -> generateObstacles());
        obstacleTimer.setRepeats(false);
        obstacleTimer.start();
    }

}
