package org.example;

import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Player {
    public static final int PLAYER_SIZE = 100;
    public static int playerY;
    public static int playerX;

    public static int playerDY;
    public static boolean isJumping;
    public static boolean movingLeft = false;
    public static boolean movingRight = false;
    public static Image walkingPlayerImage;
    public static Image playerImage;
    public static Image playerDamageImage;
    public static void resetPlayerPosition() {
        playerY = (Game2D.HEIGHT/2)-200;
        playerDY = 0;
        isJumping = false;
    }
    public static void resetVariables() {
        Player.playerDY = 0;
        Player.isJumping = false;
        Game2D.backgroundX = 0;
        Game2D.backgroundSpeed = 2;
        Obstacle.lastObstacleY = Game2D.HEIGHT / 2 - Obstacle.OBSTACLE_HEIGHT / 2;
        Obstacle.firstObstacle = false;
        Game2D.lives = Game2D.maxLives;
    }


    public static void jump() {
        Player.playerDY = Game2D.JUMP_FORCE;
        Player.isJumping = true;

        //File audioFile = new File("src/main/java/org/example/resources/salto.wav");
        File audioFile = AudioManager.jumpClipFile;
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }
    public static void moveLeft() {
        // Agrega aquí la lógica para mover hacia la izquierda
        System.out.println("left");
        Player.playerX -= 1;

        // Limita la posición del jugador para que no se salga de la pantalla hacia la izquierda
        if (Player.playerX < 0) {
            Player.playerX = 0;
        }
    }

    public static void moveRight() {
        // Agrega aquí la lógica para mover hacia la derecha
        System.out.println("Right");
        Player.playerX += 1;

        // Limita la posición del jugador para que no se salga de la pantalla hacia la derecha
        if (Player.playerX + PLAYER_SIZE > Game2D.WIDTH) {
            Player.playerX = Game2D.WIDTH - PLAYER_SIZE;
        }
    }
}
