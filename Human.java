/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project;

import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author ryder
 */
public class Human extends Secondary {

    
    
    public Human()
    {
        curDir = Direction.LEFT;
        int rand = (int) (Math.random()*3);
        try {
            sprite = ImageIO.read(getClass().getResource("images/secondary/human"+rand+".png"));
        } catch (IOException ex) {}
    }
    
    @Override
    public void setPos(Position pos){ this.pos = pos; }

    @Override
    public void moveLeft(Level level) {
        curDir = Direction.LEFT;
        if(pos.x == 0){
            pos.x = level.columns - 1;
            return;
        }
        pos.x -= 1;
    }

    @Override
    public void moveRight(Level level) {
        curDir = Direction.RIGHT;
        if(pos.x == level.columns - 1){
            pos.x = 0;
            return;
        }
        pos.x += 1;
    }

    @Override
    public void moveUp(Level level) {
        curDir = Direction.UP;
        if(pos.y == 0){
            pos.y = level.rows-1;
            return;
        }
        pos.y -= 1;
    }

    @Override
    public void moveDown(Level level) {
        curDir = Direction.DOWN;
        if(pos.y == level.rows-1){
            pos.y = 0;
            return;
        }
        pos.y += 1;
    }

    

    
    
}
