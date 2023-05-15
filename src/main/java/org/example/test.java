package org.example;
import org.example.Game2D;

import javax.swing.*;
import java.io.File;

public class test {
    public static void main(String[] args) {
        Game2D game = new Game2D();

        //configuraciones
        game.setSPEED(8);
        game.setOBSTACLE_GAP(270);
        game.setMaxLives(5);
        game.setGRAVITY(2);
        game.setENEMY(true);
        game.setENEMY_SPEED(12);





        //inicializamos las imagenes
        game.setBackgroundImage(new ImageIcon("src/main/java/org/example/resources/background.png").getImage());
        game.setWalkingPlayerImage(new ImageIcon("src/main/java/org/example/resources/mario.gif").getImage());
        game.setPlayerDamageImage(new ImageIcon("src/main/java/org/example/resources/marioDamage.png").getImage());
        game.setObstacleImage(new ImageIcon("src/main/java/org/example/resources/bloque.png").getImage());
        game.setHeartImage(new ImageIcon("src/main/java/org/example/resources/hearth.png").getImage());
        game.setEnemyImage(new ImageIcon("src/main/java/org/example/resources/hearth.png").getImage());
        game.setPauseImage(new ImageIcon("src/main/java/org/example/resources/pause.png").getImage());


        //inicializamos los sonidos
        game.setBackgroundClipFile(new File("src/main/java/org/example/resources/background.wav"));
        game.setJumpClipFile(new File("src/main/java/org/example/resources/salto.wav"));
        game.setGameOverClipFile(new File("src/main/java/org/example/resources/gameOver.wav"));

        //corremos el juego
        game.run("macaco");
    }
}
