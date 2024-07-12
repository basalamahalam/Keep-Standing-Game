/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.awt.Graphics;

/**
 *
 * @author ACER
 */
public abstract class Object {
    // Attributes
    public int x;
    public int y;
    protected int gravity;
    protected double vel_x;
    protected double vel_y;
    protected int size;
    protected ID id;
    protected boolean jumping;
    protected boolean right;
    protected boolean left;
    protected boolean falling = true;
    
    // Constructor
    public Object(int x, int y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }
    
    // Abstract Methods
    public abstract void tick();
    public abstract void render(Graphics g);
    
    // Setter and Getter
    public void setX(int x) {
        this.x = x;
    }
    
    public int getX() {
        return this.x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public int getY() {
        return this.y;
    }
    
    public void setId(ID id) {
        this.id = id;
    }
    
    public ID getId() {
        return this.id;
    }
    
    public void setVel_x(double vel_x) {
        this.vel_x = vel_x;
    }
    
    public double getVel_x() {
        return this.vel_x;
    }
    
    public void setVel_y(double vel_y) {
        this.vel_y = vel_y;
    }
    
    public double getVel_y() {
        return this.vel_y;
    }
    
    public void setSize(int size) {
        this.size = size;
    }
    
    public int getSize() {
        return this.size;
    }

    public void setJump(boolean b) {
        this.jumping = b;
    }

    public boolean isJump() {
        return this.jumping;
    }

    public void setLeft(boolean b) {
        this.left = b;
    }

    public boolean isLeft() {
        return this.left;
    }

    public void setRight(boolean b) {
        this.right = b;
    }

    public boolean isRight() {
        return this.right;
    }

    public void setFalling(boolean b) {
        this.falling = b;
    }

    public boolean getFalling() {
        return this.falling;
    }
}
