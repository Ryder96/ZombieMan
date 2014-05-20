/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project;

import java.awt.image.BufferedImage;

/**
 *
 * @author ryder
 */
abstract public class Secondary {
    
    BufferedImage sprite;
    Position pos;
    int curDir;
    
    public Secondary(){}
    
    abstract public void setPos(Position pos);
    abstract public void moveLeft(Level level);
    abstract public void moveRight(Level level);
    abstract public void moveUp(Level level);
    abstract public void moveDown(Level level);
    

    
    
}
