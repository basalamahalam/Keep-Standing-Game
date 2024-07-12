/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

import Model.ID;
import Model.Object;
import View.Menu;
import ViewModel.Game;
import ViewModel.Handler;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author ACER
 */
public class KeyInput extends KeyAdapter {
    // Object Handler
    private Handler handler;

    // Game
    Game game;

    // Constructor
    public KeyInput(Handler handler, Game game) {
        this.handler = handler;
        this.game = game;
    }

    // When Key Pressed
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        // When Game State is Game
        if (game.state == Game.STATE.Play) {
            for (int i = 0; i < handler.obj.size(); i++) {
                // When Object ID is Player
                if (handler.obj.get(i).getId() == ID.Player) {
                    Object tempObj = handler.obj.get(i);
                    
                    // When Player Keyboard Input is W or Up Arrow
                    if ((key == KeyEvent.VK_W || key == KeyEvent.VK_UP) && !tempObj.getFalling()) {
                        tempObj.setJump(true);
                    }

                    // When Player Keyboard Input is A or Left Arrow
                    if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
                        tempObj.setLeft(true);
                    }

                    // When Player Keyboard Input is D or Right Arrow
                    if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
                        tempObj.setRight(true);
                    }

                    // When Player Keyboard Input is Space
                    if (key == KeyEvent.VK_SPACE) {
                        game.state = Game.STATE.GameOver;
                        game.storeData();
                        Menu menu = new Menu();
                        menu.setVisible(true);
                        game.close();
                    }
                    
                    break;
                }
            }
        }
    }

    // When Key Released
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        // When Game State is Game
        if (game.state == Game.STATE.Play) {
            for (int i = 0; i < handler.obj.size(); i++) {
                // When Object ID is Player
                if (handler.obj.get(i).getId() == ID.Player) {
                    Object tempObj = handler.obj.get(i);
                    
                    // When Player Released Keyboard Input is W or Up Arrow
                    if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
                        tempObj.setJump(false);
                    }

                    // When Player Released Keyboard Input is A or Left Arrow
                    if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
                        tempObj.setLeft(false);
                    }

                    // When Player Released Keyboard Input is D or Right Arrow
                    if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
                        tempObj.setRight(false);
                    }

                    break;
                }
            }
        }
    }
}
