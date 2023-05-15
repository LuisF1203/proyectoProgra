# proyectoProgra
# Game2D

This is a Java application that demonstrates a simple 2D game. The game features a player character that needs to navigate obstacles and enemies to reach the end. It utilizes the `Game2D` class to handle the game logic and rendering.

## Installation

1. Clone the repository to your local machine.
2. Open the project in your favorite Java IDE.

## Usage

1. Open the `test` class.
2. Modify the game configurations according to your preferences. You can adjust the game speed, obstacle gap, maximum lives, gravity, and enable/disable enemies.
3. Initialize the game images by specifying the image paths for the background, player, player damage, obstacle, heart, enemy, and pause icon.
4. Initialize the game sounds by providing the file paths for the background music, jump sound, and game over sound.
5. Run the game by calling the `run` method on the `game` object and passing a username as a parameter.

```java
Game2D game = new Game2D();

// Configurations
game.setSPEED(8);
game.setOBSTACLE_GAP(270);
game.setMaxLives(5);
game.setGRAVITY(2);
game.setENEMY(true);
game.setENEMY_SPEED(12);

// Initialize images
game.setBackgroundImage(new ImageIcon("src/main/java/org/example/resources/background.png").getImage());
game.setWalkingPlayerImage(new ImageIcon("src/main/java/org/example/resources/mario.gif").getImage());
game.setPlayerDamageImage(new ImageIcon("src/main/java/org/example/resources/marioDamage.png").getImage());
game.setObstacleImage(new ImageIcon("src/main/java/org/example/resources/bloque.png").getImage());
game.setHeartImage(new ImageIcon("src/main/java/org/example/resources/hearth.png").getImage());
game.setEnemyImage(new ImageIcon("src/main/java/org/example/resources/hearth.png").getImage());
game.setPauseImage(new ImageIcon("src/main/java/org/example/resources/pause.png").getImage());

// Initialize sounds
game.setBackgroundClipFile(new File("src/main/java/org/example/resources/background.wav"));
game.setJumpClipFile(new File("src/main/java/org/example/resources/salto.wav"));
game.setGameOverClipFile(new File("src/main/java/org/example/resources/gameOver.wav"));

// Run the game
game.run("macaco");
```

Replace `"macaco"` with your desired gamename. Enjoy playing the game!

## License

This project is licensed under the [MIT License](LICENSE).

