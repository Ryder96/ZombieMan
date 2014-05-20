/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author ryder
 */
public class MainCharacter {
    
    
    BufferedImage sprite,superZombie, normalZombie;
    final private int TIME = 200;  //passi
    int timer = TIME; 
    
    Position pos;
    boolean SUPER = false;
    
    public MainCharacter(){
        
        try {
            sprite = ImageIO.read(getClass().getResource("images/main/zombie.png"));
            normalZombie = sprite;
        } catch (IOException ex) {}
        
   
    }
    
    public void setPos(Position pos){this.pos = pos;}
public void setZViolet()
    {
        try {
            sprite = ImageIO.read(getClass().getResource("images/main/violet.png"));
            SUPER = true;
               } catch (IOException ex) { }
        if(timer == 0)
                timer = TIME;
    }
    public void setZYellow()
    {
        try {
            sprite = ImageIO.read(getClass().getResource("images/main/yellow.png"));
            SUPER = true;
        } catch (IOException ex) { }
        if(timer == 0)
                timer = TIME;
    }
    public void setZRed()
    {
        try {
            sprite = ImageIO.read(getClass().getResource("images/main/red.png"));
            SUPER = true;
        } catch (IOException ex) {}
        if(timer == 0)
                timer = TIME;
    }
    
    public void returnNormal()
    {
        sprite = normalZombie;
        SUPER = false;
    }
   
    
}
