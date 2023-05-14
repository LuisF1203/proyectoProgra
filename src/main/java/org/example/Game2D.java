package org.example;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Game2D extends JPanel {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private int GRAVITY = 2;
    private boolean canMove;
    public static final int JUMP_FORCE = -30;






    private boolean isGamePause=false;
    private int circleX;
    private int circleY;
    private int circleRadius;









    private Image backgroundImage;
    private Image heartImage;
    private Image enemyImage;
    private Image pauseImage;
    public static int backgroundX;
    public static int backgroundY;
    public static int backgroundSpeed;
    public static int backgroundSpeedUp;

    private Timer gameTimer;
    private int score;
    public static Random random;

    public static int lives = 3;
    public static int maxLives=3;



    public void setPauseImage(Image pauseImage) {
        this.pauseImage = pauseImage;
    }

    public void setENEMY(boolean ENEMY) {
        Enemy.ENEMY = ENEMY;
    }

    public void setENEMY_SPEED(int ENEMY_SPEED) {
        Enemy.ENEMY_SPEED = ENEMY_SPEED;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public void setGRAVITY(int GRAVITY) {
        this.GRAVITY = GRAVITY;
    }

    public void setOBSTACLE_GAP(int OBSTACLE_GAP) {
        Obstacle.OBSTACLE_GAP = OBSTACLE_GAP;
    }

    public void setSPEED(int OBSTACLE_SPEED) {
        Obstacle.OBSTACLE_SPEED = OBSTACLE_SPEED;
    }

    public void setWalkingPlayerImage(Image walkingPlayerImage) {
        Player.walkingPlayerImage = walkingPlayerImage;
        Player.playerImage=walkingPlayerImage;
    }

    public void setPlayerDamageImage(Image playerDamageImage) {
        Player.playerDamageImage = playerDamageImage;
    }

    public void setEnemyImage(Image enemyImage){
        this.enemyImage = enemyImage;
    }

    public void setGameOverClipFile(File filePath){
        AudioManager.gameOverClipFile = filePath;
    }

    public void setBackgroundClipFile(File filePath) {
        AudioManager.backgroundClipFile = filePath;
    }

    public void setJumpClipFile(File jumpClipFile) {
        AudioManager.jumpClipFile = jumpClipFile;
    }

    public void setMaxLives(int lives) {
        this.maxLives = lives;
        this.lives=lives;
    }

    public void setHeartImage(Image heartImage) {
        this.heartImage = heartImage;
    }

    public void setObstacleImage(Image obstacleImage) {
        Obstacle.obstacleImage = obstacleImage;
    }

    public void setBackgroundImage(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public Game2D(boolean canMove) {
        System.out.println(canMove);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(new Color(85,170,255));
        Player.playerY = HEIGHT / 2;
        Player.playerDY = 0;
        Player.isJumping = false;

        Obstacle.obstacles = new ArrayList<>();
        random = new Random();

        backgroundX = 0;
        backgroundSpeed = 2;

        backgroundY = 0;
        backgroundSpeedUp = 2;

        gameTimer = new Timer(10, e -> {
            update();
            repaint();
        });

        setFocusable(true);
        System.out.println(canMove);
        if(canMove){
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    int keyCode = e.getKeyCode();
                    if ((keyCode == KeyEvent.VK_SPACE || keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W) && !Player.isJumping) {
                        Player.jump();
                    } else if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A) {
                        Player.movingLeft = true;
                    } else if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D) {
                        Player.movingRight = true;
                    }
                }
            });
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    int keyCode = e.getKeyCode();
                    if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A) {
                        Player.movingLeft = false;
                    } else if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D) {
                        Player.movingRight = false;
                    } else if (keyCode == KeyEvent.VK_ESCAPE) {
                        // Agrega aquí el código que deseas ejecutar al presionar "Esc"
                        // Por ejemplo, puedes llamar a un método para pausar el juego
                        pauseGame();
                    }
                }
            });

        }else {
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    int keyCode = e.getKeyCode();
                    if ((keyCode == KeyEvent.VK_SPACE || keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W) && !Player.isJumping) {
                        Player.jump();
                    }
                }
            });

        }

        gameTimer.start();
        System.out.println(canMove);
    }
    public Game2D() {
        System.out.println(canMove);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(new Color(85,170,255));
        Player.playerY = HEIGHT / 2;
        Player.playerDY = 0;
        Player.isJumping = false;

        Obstacle.obstacles = new ArrayList<>();
        random = new Random();

        backgroundX = 0;
        backgroundSpeed = 2;

        backgroundY = 0;
        backgroundSpeedUp = 2;

        gameTimer = new Timer(10, e -> {
            update();
            repaint();
        });

        setFocusable(true);
        System.out.println(canMove);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if ((keyCode == KeyEvent.VK_SPACE || keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W) && !Player.isJumping) {
                    Player.jump();
                }
            }});
        gameTimer.start();
        System.out.println(canMove);
    }




    private void update() {
        // Actualiza la posición vertical del jugador sumando la velocidad vertical
        Player.playerY += Player.playerDY;

        // Incrementa la velocidad vertical del jugador debido a la gravedad
        Player.playerDY += GRAVITY;

        // Ajusta la velocidad horizontal del jugador
        int playerSpeed = 5; // Ajusta este valor según la velocidad de movimiento horizontal deseada

        // Mueve el jugador hacia la izquierda
        if (Player.movingLeft) {
            Player.playerX -= playerSpeed;
        }

        // Mueve el jugador hacia la derecha
        if (Player.movingRight) {
            Player.playerX += playerSpeed;
        }

        // Limita la posición del jugador para que no se salga de la pantalla
        if (Player.playerX < 0) {
            Player.playerX = 0;
        } else if (Player.playerX + Player.PLAYER_SIZE > WIDTH) {
            Player.playerX = WIDTH - Player.PLAYER_SIZE;
        }

        // Comprueba si el jugador ha alcanzado el suelo o una plataforma
        if (Player.playerY >= HEIGHT - Player.PLAYER_SIZE) {
            // Establece la posición vertical del jugador en el suelo
            Player.playerY = HEIGHT - Player.PLAYER_SIZE;
            // Establece la velocidad vertical del jugador a cero
            Player.playerDY = 0;
            // Marca que el jugador no está saltando
            Player.isJumping = false;
        }
        // Itera a través de la lista de obstáculos
        for (Rectangle obstacle : Obstacle.obstacles) {
            // Calcula la posición del jugador considerando su movimiento
            int playerRight = Player.playerX + Player.PLAYER_SIZE;
            int playerBottom = Player.playerY + Player.PLAYER_SIZE;
            // Actualiza la posición horizontal del obstáculo moviéndolo hacia la izquierda
            obstacle.x -= Obstacle.OBSTACLE_SPEED;
            // Comprueba si el obstáculo ha salido completamente de la pantalla
            if (obstacle.x + Obstacle.OBSTACLE_WIDTH < 0) {
                // Incrementa el score del jugador
                score++;
                // Elimina el obstáculo de la lista
                Obstacle.obstacles.remove(obstacle);
                // Finaliza el bucle, ya que solo se debe eliminar un obstáculo por iteración
                break;
            }

            if (playerRight >= obstacle.x && Player.playerX <= obstacle.x + Obstacle.OBSTACLE_WIDTH &&
                    playerBottom >= obstacle.y && Player.playerY <= obstacle.y + Obstacle.OBSTACLE_HEIGHT) {
                if (Player.playerY + Player.PLAYER_SIZE >= obstacle.y && Player.playerY <= obstacle.y + 10) {
                    //detecta si estás por encima del obstaculo
                    System.out.println("Chocó por encima del obstáculo");
                    Player.playerY = obstacle.y - Player.PLAYER_SIZE;
                    Player.playerDY = 0;
                    Player.isJumping = false;
                    Obstacle.firstObstacle = true;
                }
                else if (!Player.isJumping) {
                    if(!Obstacle.firstObstacle){
                        // si no chocaste por encima, quiere decir que fue por enfrente
                        System.out.println("Chocó de frente con el obstáculo");
                        Player.playerY = obstacle.y - Player.PLAYER_SIZE;
                        Player.playerDY = 0;
                        Player.isJumping = false;
                        Obstacle.firstObstacle = true;
                        if (lives > 1) {
                            // si tienes vidas
                            // bajar 1 vida y regresar a la posición de inicio
                            Thread thread = new Thread(() -> {
                                try {
                                    Thread.sleep(300);
                                    // Cambiar la imagen del personaje a la imagen original
                                    Player.playerImage = Player.walkingPlayerImage;
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            });
                            thread.start();
                            // Cambiar la imagen del personaje después de tocar el suelo
                            Player.playerImage = Player.playerDamageImage;
                        } else {
                            // si no tienes vidas, se termina el juego
                            gameOver();
                        }
                    }
                }

            }
            if (Obstacle.firstObstacle) {
                // si el jugador pasó el primer obstáculo
                if (Player.playerY >= HEIGHT - Player.PLAYER_SIZE) {
                    // detectar si tocó el suelo
                    if (lives > 1) {
                        // si tienes vidas
                        // bajar 1 vida y regresar a la posición de inicio
                        lives--;
                        Player.resetPlayerPosition();
                        Thread thread = new Thread(() -> {
                            try {
                                Thread.sleep(300);
                                // Cambiar la imagen del personaje a la imagen original
                                Player.playerImage = Player.walkingPlayerImage;
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        });
                        thread.start();
                        // Cambiar la imagen del personaje después de tocar el suelo
                        Player.playerImage = Player.playerDamageImage;
                    } else {
                        // si no tienes vidas, se termina el juego
                        gameOver();
                    }
                }
            }



        }
        // Comprueba si la lista de obstáculos está vacía o si el obstáculo más a la derecha está lo suficientemente cerca del borde derecho
        if (Obstacle.obstacles.isEmpty() || Obstacle.obstacles.get(Obstacle.obstacles.size() - 1).x < WIDTH - Obstacle.OBSTACLE_GAP) {
            // Genera nuevos obstáculos
            Obstacle.generateObstacles();
        }


        // Actualizar la posición del fondo
        backgroundX -= backgroundSpeed;
        backgroundY -= backgroundSpeedUp; // Nueva variable para el avance hacia arriba

        if (backgroundX <= -WIDTH) {
            backgroundX = 0;
        }

        if (backgroundY <= -HEIGHT) {
            backgroundY = 0;
        }

    }




    public void MovingCircle() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        generateCircle();
        startAnimation();
    }

    private void generateCircle() {
        Random random = new Random();
        circleX = WIDTH - circleRadius; // Inicializamos la posición del círculo al extremo derecho de la ventana
        circleY = random.nextInt(HEIGHT - circleRadius); // Generamos una posición "y" aleatoria dentro de los límites de la ventana
    }

    private void startAnimation() {
        Thread animationThread = new Thread(() -> {
            while (true) {
                moveCircle();
                checkCollision();
                repaint();

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        animationThread.start();
    }

    private void moveCircle() {
        if (Enemy.isEnemyMoving) {
            circleX -= Enemy.ENEMY_SPEED;
        }

        if (circleX + Enemy.ENEMY_RADIUS < 0) {
            generateCircle();
        }
    }


    private void checkCollision() {
        // Calcula el centro del círculo
        int circleCenterX = circleX + (circleRadius / 2);
        int circleCenterY = circleY + (circleRadius / 2);

        // Calcula la distancia entre el centro del círculo y el centro del jugador
        double distance = Math.sqrt(Math.pow(Player.playerX + (Player.PLAYER_SIZE / 2) - circleCenterX, 2) + Math.pow(Player.playerY + (Player.PLAYER_SIZE / 2) - circleCenterY, 2));

        // Comprueba si hay colisión entre el jugador y el círculo
        if (distance <= (Player.PLAYER_SIZE / 2) + (circleRadius / 2)) {
            System.out.println("chocaste con el circulo");

            // Se produjo una colisión
            if (lives > 1) {
                // si tienes vidas
                // bajar 1 vida y regresar a la posición de inicio
                lives=lives-1;
                Player.resetPlayerPosition();
                Thread thread = new Thread(() -> {
                    try {
                        Thread.sleep(300);
                        // Cambiar la imagen del personaje a la imagen original
                        Player.playerImage = Player.walkingPlayerImage;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                thread.start();
                // Cambiar la imagen del personaje después de tocar el suelo
                Player.playerImage = Player.playerDamageImage;

            } else {
                // si no tienes vidas, se termina el juego

                gameOver();
            }
        }
    }









    private  void pauseGame(){
        isGamePause = true;
        Enemy.isEnemyMoving = false;
        gameTimer.stop();
        requestFocus(); // Establecer el enfoque en el componente
        repaint(); // Volver a dibujar la pantalla para mostrar "pause"
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_R) {
                    // Agrega aquí el código que deseas ejecutar al presionar "R"
                    // Por ejemplo, puedes llamar a un método para reiniciar el juego
                    restartGame();
                }
            }
        });

    }
    private  void restartGame(){
        // Agrega aquí el código para reiniciar el juego
        isGamePause = false; // Restablecer el estado de pausa
        Enemy.isEnemyMoving = true; // Restablecer el movimiento del enemigo
        gameTimer.start(); // Reiniciar el temporizador del juego
        requestFocus(); // Establecer el enfoque en el componente
        repaint(); // Volver a dibujar la pantalla para quitar "pause"

    }
    private void gameOver() {
        gameTimer.stop();
        AudioManager.stopBackgroundAudio();
        Enemy.stopCircle();
        File audioFile = AudioManager.gameOverClipFile;

        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

            // Crear un hilo para esperar un tiempo y luego detener la reproducción
            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(3000); // Esperar 3 segundos (ajusta el tiempo según tus necesidades)
                    clip.stop(); // Detener la reproducción después del tiempo especificado
                    clip.close();
                    audioStream.close();
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            });
            thread.start();

        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }

        int choice = JOptionPane.showOptionDialog(
                this,
                "Game Over\nScore: " + score,
                "Game Over",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new String[]{"Restart", "Exit"},
                "Restart");



        if (choice == JOptionPane.YES_OPTION) {
            Player.resetVariables();
            Obstacle.obstacles.clear();
            Player.playerY = HEIGHT / 2;
            score = 0;
            gameTimer.start();
            AudioManager.initializeBackgroundAudio();
            Enemy.playCircle();
        } else {
            System.exit(0);
        }

    }



    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Calcular la posición del fondo relativa a la posición de los obstáculos
        int adjustedBackgroundX = (backgroundX % WIDTH);
        int adjustedBackgroundY = (backgroundY % HEIGHT)*-1; // Nueva variable para el avance hacia arriba

        // Dibujar el fondo en la posición actual
        //g.drawImage(backgroundImage, adjustedBackgroundX*2, -500, WIDTH, HEIGHT, null);

        // Dibujar el fondo en la posición siguiente
        g.drawImage(backgroundImage, (adjustedBackgroundX*2 + WIDTH), adjustedBackgroundY, WIDTH, HEIGHT, null);
        g.drawImage(backgroundImage, (adjustedBackgroundX*2), adjustedBackgroundY + HEIGHT, WIDTH, HEIGHT, null);
        g.drawImage(backgroundImage, (adjustedBackgroundX*2 + WIDTH), adjustedBackgroundY + HEIGHT, WIDTH, HEIGHT, null);

        for (Rectangle obstacle : Obstacle.obstacles) {
            // Ajustar la posición del obstáculo en relación con la posición del fondo
            int adjustedObstacleX = obstacle.x - backgroundX + adjustedBackgroundX;
            int adjustedObstacleY = obstacle.y - backgroundY + adjustedBackgroundY*-1; // Nueva variable para el avance hacia arriba

            g.drawImage(Obstacle.obstacleImage, adjustedObstacleX, adjustedObstacleY, Obstacle.OBSTACLE_WIDTH, Obstacle.OBSTACLE_HEIGHT, null);
        }

        // Dibujar al jugador
        g.drawImage(Player.playerImage, Player.playerX, Player.playerY, Player.PLAYER_SIZE, Player.PLAYER_SIZE, null);
        //Dibujar la bola
        if (Enemy.ENEMY){
            System.out.println(circleX+"  "+circleY);
            g.drawImage(enemyImage,circleX, circleY, Enemy.ENEMY_RADIUS, Enemy.ENEMY_RADIUS,null);
        }



        // Mostrar las vidas restantes
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        for (int i = 0; i < lives; i++) {
            int x = 10 + i * 30; // Espacio entre los círculos
            int y = 20;
            g.setColor(Color.RED);
            g.drawImage(heartImage, x, y, 30, 30, null);
        }

        // Mostrar la puntuación
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 10, 70);

        //poner pausa
        System.out.println(isGamePause);
        if (isGamePause) {
            g.drawImage(pauseImage, getWidth() - 50, 10, 30, 30, null);
            g.setColor(Color.WHITE);

            String text = "Pause";
            Font font = new Font("Arial", Font.BOLD, 40);
            FontMetrics fontMetrics = g.getFontMetrics(font);
            int textWidth = fontMetrics.stringWidth(text);
            int textHeight = fontMetrics.getHeight();
            int centerX = getWidth() / 2 - textWidth / 2;
            int centerY = getHeight() / 2 - textHeight / 2;

            g.setFont(font);
            g.drawString(text, centerX, centerY);


            String text2 = "press 'R' to continue";
            Font font2 = new Font("Arial", Font.BOLD, 20);
            FontMetrics fontMetrics2 = g.getFontMetrics(font2);
            int textWidth2 = fontMetrics2.stringWidth(text2);
            int textHeight2 = fontMetrics2.getHeight();
            int centerX2 = getWidth() / 2 - textWidth2 / 2;
            int centerY2 = centerY + textHeight + 10;

            g.setFont(font2);
            g.drawString(text2, centerX2, centerY2);



        } else {
            g.drawImage(Player.playerImage, getWidth() - 50, 10, 30, 30, null);
        }

    }






    public void run(String name){
        JFrame frame = new JFrame(name);
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        AudioManager.initializeBackgroundAudio();
        MovingCircle();
    }
}