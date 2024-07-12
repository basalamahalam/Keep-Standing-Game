/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author ACER
 */
public class Obstacle extends Object {
    private int val;
    private boolean collided;
    private Image image;
    
    private int[] score = {
        10,
        20,
        30,
        40,
        50,
        60,
        70,
        80,
        90
    };

    // Constructor
    public Obstacle(int x, int y, ID id, int val) {
        super(x, y, id);
        this.val = val;
        
        try {
            image = ImageIO.read(new File("resources/floor.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Getter for collided
    public boolean isCollided() {
        return collided;
    }
    
    // Setter for collided
    public void setCollided(boolean collided) {
        this.collided = collided;
    }
    
    public int getScore(){
        return this.score[this.val];
    }
    
    // Update Obstacle per Tick
    @Override
    public void tick() {
        y += vel_y;
    }

    // Render Obstacle
    @Override
    public void render(Graphics g) {
        g.drawImage(image, x, y, 600, 25, null);
        g.setColor(Color.BLACK);
        g.setFont(new Font("pixelmix", 1, 14));
        g.drawString(Integer.toString(score[val]), x + 610, y + 17);
    }
}
