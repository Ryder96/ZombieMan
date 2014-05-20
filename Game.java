package project;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/**
 *
 * @author Andres
 */
 public abstract class Game implements KeyListener, MouseListener{
    
    protected boolean end;
    protected String title;
    protected int width, height;
    protected int delay = 30;
    protected Image icon;
    
    abstract public void update();
    abstract public void draw(Graphics2D g);
    
    public String getTitle() {return title;}
    
    public int getWidth(){return width;}
    
    public int getHeight(){return height;}
    
    public int getDelay(){return delay;}
    
    public boolean isEnd(){return end;}

    
    @Override
    public void keyTyped(KeyEvent e) { }
    
    @Override
    public void keyPressed(KeyEvent e) { }
    
    @Override
    public void keyReleased(KeyEvent e) { }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    

    
    
    
    
}
