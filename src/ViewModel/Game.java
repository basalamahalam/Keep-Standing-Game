/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

import Model.Object;
import Model.ID;
import Model.Obstacle;
import Model.Player;
import View.Menu;
import View.Window;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

/**
 *
 * @author ACER
 */

public class Game extends Canvas implements Runnable {
    // Window
    private Window window;

    // Screen Size
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    // Thread
    private Thread thread;
    private boolean running = false;

    // Player Data
    private String username;
    private int score = 0;
    private int scoreTemp;
    private int standing;
    private int collideWithObstacles = 0;

    // Game Var
    private int FPS;
    private int elapsed = 0;

    // BGM Clip
    private Clip clip;

    // Object Handler
    private Handler handler;

    // Background
    private BufferedImage bg;

    // Enum State in This Game
    public enum STATE {
        Play,
        GameOver
    }

    // Initial State
    public STATE state = STATE.Play;

    // Constructor
    public Game() {
        // Call Window
        window = new Window(Game.WIDTH, Game.HEIGHT, "Keep Standing Game", this);

        // Call Handler
        handler = new Handler();

        // KeyListener For Player Keyboard Input
        this.addKeyListener(new KeyInput(handler, this));

        // Read Background Image
        try {
            bg =  ImageIO.read(new File("resources/asd.jpg").getAbsoluteFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (state == STATE.Play) {
            // Add Player Object
            handler.addObject(new Player(25, 25, ID.Player));
            
        }
    }

    // Username Setter
    public void setUsername(String username) {
        this.username = username;
    }

    // Score Setter
    public void setScore(int score) {
        this.score = score;
    }

    // Standing Setter
    public void setStanding(int standing) {
        this.standing = standing;
    }

    // Thread Start
    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    // Thread Stop
    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int frames = 0;
        BGM bgm = new BGM();
        int tempY = 0;
        
        // Play BGM
        clip = bgm.playSound(this.clip, "ingame.wav");
        
        // Game Loop
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1) {
                tick();
                render();
                frames++;
                delta--;
            }

            if (state == STATE.GameOver) {
                bgm.stopSound(this.clip);
            }

            if (System.currentTimeMillis() - timer > 1000) {
                elapsed++;
                timer += 1000;
                FPS = frames;
                
                // Generate Obstacles
                for (int i = 0; i < 13; i++) {
                    if (i % 2 != 1) {
                        int obstacleX = RNG(-500, 0);
                        scoreTemp = RNG(0, 8);
                        handler.addObject(new Obstacle(obstacleX, tempY, ID.Obstacle, scoreTemp));
                    }
                    tempY += 50;
                }
                
                // Set Obstacle Velocity to moving obstacle from bottom into the top side
                Object obs = null;
                for (int i = 0; i < handler.countObject(); i++) {
                    if (handler.obj.get(i).getId() == ID.Obstacle) {
                        obs = handler.obj.get(i);
                        double velocity = 1;
                        obs.setVel_y(-velocity);
                    }
                }
                frames = 0;
            }
        }
        stop();
    }

    // Updating Object Every Tick
    private void tick() {
        handler.tick();

        if (state == STATE.Play) {
            Object player = null;
            Object obs = null;

            // Fetching Player Objects
            for (int i = 0; i < handler.countObject(); i++) {
                if (handler.obj.get(i).getId() == ID.Player) {
                    player = handler.obj.get(i);
                }
               
                if (player != null) {
                    break;
                }
            }

            // When Player are not null
            // Then Checking Player Collision with Obstacles
            if (player != null) {
                for (int i = 0; i < handler.countObject(); i++) {
                    if (handler.obj.get(i).getId() == ID.Obstacle) {
                        player.setFalling(true);
                        collideWithObstacles = 0;
                        // When Player is Colliding with Obstacles
                        if (isCollide(player, (Obstacle)handler.obj.get(i))) {
                            // When Player Touch Top Side of Obstacle
                            // Then Increase Adapt Value by 1
                            if (collideWithObstacles == 1) {
                                standing++;
                                Obstacle obstacle = (Obstacle) handler.obj.get(i);
                                score += obstacle.getScore();
                            }
                            break;
                        }
                    }
                }
            }

            // When Obstacles Already Out of Screen (Top Side)
            // Then Remove Obstacles From List of Objects
            for (int i = 0; i < handler.countObject(); i++) {
                if (handler.obj.get(i).getId() == ID.Obstacle) {
                    obs = handler.obj.get(i);
                   if (obs.getY() <= 0) {
                        handler.removeObject(obs);
                        i--;
                    }
                }
            }
        }
    }

    // Checking Collision
    public boolean isCollide(Object player, Obstacle obstacle) {
        boolean result = false;

        // GameObject Var
        int playerSize = 30;
        int obstacleWidth = 600;
        int obstacleHeight = 25;

        // Player Sides
        int playerLeft = player.x;
        int playerRight = player.x + playerSize;
        int playerTop = player.y;
        int playerBottom = player.y + playerSize;

        // Obstacle Sides
        int obstacleLeft = obstacle.x;
        int obstacleRight = obstacle.x + obstacleWidth;
        int obstacleTop = obstacle.y;
        int obstacleBottom = obstacle.y + obstacleHeight;

        // When Player Intersects with Obstacle
        if (new Rectangle(player.x, player.y, playerSize, playerSize).intersects(obstacle.x, obstacle.y, obstacleWidth, obstacleHeight)) {
            // When Player Fall on Top of Obstacle
            // Then Set Falling Value to False
            // And When Player Y Axis Velocity Still Positive (AKA Falling Because of Gravity)
            // Then Will Set Y Axis Velocity to Zero
            if (playerBottom <= obstacleTop + 10) {
                player.setFalling(false);
                if (player.getVel_y() > 0) {
                    player.setVel_y(0);
                    //When Player is Not Falling Anymore
                    //Then Add collideWithObstacles Value and setCollided true
                    //To indicate that an obstacle has been touched or stepped on
                    if (!player.getFalling() && !obstacle.isCollided()) {
                        collideWithObstacles++;
                        obstacle.setCollided(true);
                    }
                    // If Player Fall to Bottom Side of Screen
                    // Then Will Change Game State to Game Over and Store the Score
                    if (playerBottom >= Game.HEIGHT) {
                        state = STATE.GameOver;
                        storeData();
                        new Menu().setVisible(true);
                        close();
                    }
                }

                // If Player Y Axis Velocity is Zero When Player on Top of Obstacle
                // Then Set Player Y Axis Velocity Same as Obstacle Y Axis Velocity (So Player Will Also Move to Top)
                if (player.getVel_y() == 0) {
                    player.setVel_y(obstacle.getVel_y());
                }
            }

            // Player-Obstacle collision handling: Stop player's vertical movement
            // and align their position with the top of the obstacle.
            if (playerBottom >= obstacleBottom) {
                player.setY(obstacleBottom);
                player.setVel_y(0);
                player.setFalling(false);
                
            }

            // When Player Jump
            // Then Set Player Falling Value to true and Reset collideWithObstacles
            if (player.getVel_y() < 0 && playerBottom < obstacleTop) {
                player.setFalling(true);
                collideWithObstacles = 0;
            }

            result = true;
        }
        return result;
    }

    // Random Number Generator
    public static int RNG(int min, int max) {
        Random random = new Random();
        int result = random.nextInt((max - min) + 1) + min;

        return result;
    }

    // All Object Rendered Here
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        // Draw Background Image to The Screen
        g.drawImage(bg, 0, 0, Game.WIDTH, Game.HEIGHT, null);

        // Render Game Objects
        if (state == STATE.Play) {
            handler.render(g);

            g.setColor(Color.BLACK);
            g.setFont(new Font("pixelmix", 1, 14));
            g.drawString("FPS: " + Integer.toString(FPS), 10, 20);
            g.drawString("Elapsed Time: " + Integer.toString(elapsed) + "s", 10, 40);
            g.drawString("Score: " + Integer.toString(score), 10, 60);
            g.drawString("Standing: " + Integer.toString(standing), 10, 80);
        }
        
        g.dispose();
        bs.show();  
    }
    
    // Insert Data to Database
    public void storeData() {
        try {
            DataProcessing process = new DataProcessing();
            process.storeData(username, score, standing);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "Username: " + this.username + "\nScore: " + this.score + "\nStanding: " + this.standing, "Game Over!", JOptionPane.INFORMATION_MESSAGE);
    }

    // Close Game
    public void close() {
        window.closeWindow();
    }
}