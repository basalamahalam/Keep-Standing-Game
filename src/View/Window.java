/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import ViewModel.Game;
import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Acer
 */
public class Window extends Canvas {
    // FIELDS
    JFrame jFrame;

    // CONSTRUCTOR
    public Window(int width, int height, String title, Game game) {
        jFrame = new JFrame(title);

        // Setting JFrame
        jFrame.setPreferredSize(new Dimension(width, height));
        jFrame.setMaximumSize(new Dimension(width, height));
        jFrame.setMinimumSize(new Dimension(width, height));
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.add(game);
        jFrame.setVisible(true);

        // Start Game
        game.start();
    }

    // Close Window
    public void closeWindow() {
        jFrame.setVisible(false);
    }
}

