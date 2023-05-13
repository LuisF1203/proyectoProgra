package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeometryDashGame extends JPanel {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int PLAYER_SIZE = 50;
    private static final int GRAVITY = 2;
    private static final int JUMP_FORCE = -30;
    private static final int OBSTACLE_WIDTH = 80;
    private static final int OBSTACLE_HEIGHT = 80;
    private static final int OBSTACLE_GAP = 800;
    private static final int OBSTACLE_SPEED = 7;

    private int playerY;
    private int playerDY;
    private boolean isJumping;
    private List<Rectangle> obstacles;
    private Timer gameTimer;
    private int score;
    private Random random;
    private boolean firstObstacle = false;
    private int lastObstacleY = HEIGHT / 2 - OBSTACLE_HEIGHT / 2; // Posición inicial del primer obstáculo




    public GeometryDashGame() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);

        playerY = HEIGHT / 2;
        playerDY = 0;
        isJumping = false;

        obstacles = new ArrayList<>();
        random = new Random();

        gameTimer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
                repaint();
            }
        });

        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if ((keyCode == KeyEvent.VK_SPACE || keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W) && !isJumping) {
                    jump();
                }
            }
        });

        gameTimer.start();
    }

    private void update() {
        playerY += playerDY;
        playerDY += GRAVITY;

        if (playerY >= HEIGHT - PLAYER_SIZE) {
            playerY = HEIGHT - PLAYER_SIZE;
            playerDY = 0;
            isJumping = false;
        }

        for (Rectangle obstacle : obstacles) {
            obstacle.x -= OBSTACLE_SPEED;

            if (obstacle.x + OBSTACLE_WIDTH < 0) {
                score++;
                obstacles.remove(obstacle);
                break;
            }

            if (playerDY >= 0 && playerY + PLAYER_SIZE >= obstacle.y && playerY <= obstacle.y + OBSTACLE_HEIGHT &&
                    obstacle.x <= 100 + PLAYER_SIZE && obstacle.x + OBSTACLE_WIDTH >= 100) {
                if (playerY + PLAYER_SIZE >= obstacle.y && playerY <= obstacle.y + 10) {
                    System.out.println("Chocó por encima del obstáculo");
                    playerY = obstacle.y - PLAYER_SIZE;
                    playerDY = 0;
                    isJumping = false;
                    firstObstacle = true;
                } else if (!isJumping) {
                    System.out.println("Chocó de frente con el obstáculo");
                    gameOver();
                }
            }

            if (firstObstacle) {
                if (playerY >= HEIGHT - PLAYER_SIZE) {
                    gameOver();
                }
            }
        }

        if (obstacles.isEmpty() || obstacles.get(obstacles.size() - 1).x < WIDTH - OBSTACLE_GAP) {
            generateObstacles();
        }
    }

    private void jump() {
        playerDY = JUMP_FORCE;
        isJumping = true;
    }

    private void generateObstacles() {
        int obstacleY=0;
        if (obstacles.isEmpty()) {
            obstacleY = lastObstacleY+200; // Mantener la posición inicial para el primer obstáculo
        } else {
            int minY = Math.max(0, lastObstacleY - HEIGHT / 4); // Límite inferior para la posición vertical del obstáculo
            int maxY = Math.min(HEIGHT - OBSTACLE_HEIGHT, lastObstacleY + HEIGHT / 4); // Límite superior para la posición vertical del obstáculo
            obstacleY = random.nextInt(maxY - minY + 1) + minY; // Generar posición aleatoria dentro de los límites
        }

        obstacles.add(new Rectangle(WIDTH, obstacleY, OBSTACLE_WIDTH, OBSTACLE_HEIGHT));
        lastObstacleY = obstacleY;
        scheduleObstacleAppearance();
    }



    private void scheduleObstacleAppearance() {
        int delay = random.nextInt(1000) + 500; // Delay aleatorio entre 500 y 1500 milisegundos
        Timer obstacleTimer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateObstacles();
            }
        });
        obstacleTimer.setRepeats(false);
        obstacleTimer.start();
    }

    private void gameOver() {
        gameTimer.stop();
        JOptionPane.showMessageDialog(this, "Game Over\nScore: " + score);
        System.exit(0);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Score: " + score, 20, 40);
        g.setColor(Color.RED);
        for (Rectangle obstacle : obstacles) {
            g.fillRect(obstacle.x, obstacle.y, obstacle.width, obstacle.height);
        }

        g.setColor(Color.WHITE);
        g.fillRect(100, playerY, PLAYER_SIZE, PLAYER_SIZE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Geometry Dash");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(new GeometryDashGame());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}