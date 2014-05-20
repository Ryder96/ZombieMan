/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author ryder
 */
public class Power {

    BufferedImage sprite;
    BufferedImage violet, red, yellow;
    Position pos;
    static int time = 200;

    public Power() {
        int rand = (int) (Math.random() * 3);

        try {
            sprite = ImageIO.read(getClass().getResource("images/power/pup" + rand + ".png"));
            if (rand == 0) {
                red = sprite;
            }
            if (rand == 1) {
                violet = sprite;
            }
            if (rand == 2) {
                yellow = sprite;
            }

        } catch (IOException ex) {
            Logger.getLogger(Power.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

}
