/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

/**
 *
 * @author ryder
 */
public class GameLoop extends Thread {

    Game game;
    GameCanvas canvas;

    public GameLoop(Game game, GameCanvas canvas) {
        this.game = game;
        this.canvas = canvas;
    }

    @Override
    public void run() {

        while (!game.isEnd()) {
            if(!Pacman.PAUSE && Pacman.START)
                game.update();

            canvas.repaint();

            try {
                Thread.sleep(game.delay);
            } catch (InterruptedException ex) {
            }
        }
    }

}
