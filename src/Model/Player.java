/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import ViewModel.Game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author ACER
 */
public class Player extends Object {
    private Image image;
    // Constructor
    public Player(int x, int y, ID id) {
        super(x, y, id);
        jumping = false;
        vel_x = 0;
        
        try {
            image = ImageIO.read(new File("resources/player.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Update Player per Tick
    @Override
    public void tick() {
        // When Player is Jumping
        if (jumping && vel_y < 30) {
            vel_y = -15;
            vel_x = 0;
        }

        // When Player is Moving to Right
        if (right) {
            vel_x += 5;
        }

        // When Playing is Moving to Left
        if (left) {
            vel_x -= 5;
        }

        x += vel_x;
        y += vel_y;
        
        // Gravity System
        gravity = 5;
        if (falling && vel_y < gravity) {
            vel_y += 3;
        } else if (!falling && vel_y > 0) {
            vel_y = 0;
        }

        // When Player Reach Screen Size Limit
        if (x < 0) {
            x = 0;
        }

        if (y > Game.HEIGHT) {
            y = Game.HEIGHT;
        }

        if (y < 0) {
            y = 0;
        }

        vel_x = 0;
    }
    
    // Render Player
    @Override
    public void render(Graphics g) {
        g.drawImage(image, x, y, 30, 30, null);
    }
}
