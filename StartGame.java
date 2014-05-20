/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project;

import javax.swing.JFrame;

/**
 *
 * @author Andres
 */
public class StartGame {
   
    public static void start(Game game)
    {
        JFrame window = new JFrame(game.getTitle());
        window.setSize(game.getWidth(),game.getHeight());
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GameCanvas canvas = new GameCanvas(game);
        window.add(canvas);
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        window.setIconImage(game.icon);
        canvas.requestFocus();
        (new GameLoop(game,canvas)).start();
    }
}
