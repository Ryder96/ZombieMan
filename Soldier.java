/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author ryder
 */
public class Soldier extends Secondary{
    
    
    
    public Soldier()
    {
        curDir = Direction.LEFT;
        int rand = (int) (Math.random()*2);
        try {
            sprite = ImageIO.read(getClass().getResource("images/secondary/soldier"+rand+".png"));
        } catch (IOException ex) {
            Logger.getLogger(Soldier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setPos(Position pos) {
        this.pos = pos;
    }
    
    @Override
    public void moveLeft(project.Level level) {
        curDir = Direction.LEFT;
        if(pos.x == 0){
            pos.x = level.columns - 1;
            return;
        }
        pos.x -= 1;
    }

   @Override
    public void moveRight(project.Level level) {
        curDir = Direction.RIGHT;
        if(pos.x == level.columns - 1){
            pos.x = 0;
            return;
        }
        pos.x += 1;
    }

    @Override
    public void moveUp(project.Level level) {
        curDir = Direction.UP;
        if(pos.y == 0){
            pos.y = level.rows-1;
            return;
        }
        pos.y -= 1;
    }

    @Override
    public void moveDown(project.Level level) {
        curDir = Direction.DOWN;
        if(pos.y == level.rows-1){
            pos.y = 0;
            return;
        }
        pos.y += 1;
    }


    
    
}
