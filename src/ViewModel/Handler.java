/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

import Model.Object;
import java.awt.Graphics;
import java.util.LinkedList;

/**
 *
 * @author Acer
 */
public class Handler {
    LinkedList<Object> obj = new LinkedList<>();
    
    // Update Game Object Per Tick
    public void tick() {
        for (int i = 0; i < obj.size(); i++) {
            Object tmpObj = obj.get(i);
            tmpObj.tick();
        }
    }
    
    // Render Game Object
    public void render(Graphics g) {
        for (int i = 0; i < obj.size(); i++) {
            Object tmpObj = obj.get(i);
            tmpObj.render(g);
        }
    }
    
    // Add Object To List
    public void addObject(Object obj) {
        this.obj.add(obj);
    }
    
    // Remove Object From List
    public void removeObject(Object obj) {
        this.obj.remove(obj);
    }
    
    // Count List Size
    public int countObject() {
        return this.obj.size();
    }
}