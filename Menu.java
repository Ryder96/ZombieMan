/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

/**
 *
 * @author ryder
 */
public class Menu extends Canvas implements MouseListener {

    JFrame frame;
    BufferedImage image, icon;
    Rectangle selection[] = {
        new Rectangle(78, 195, 117, 57), 
        new Rectangle(55, 256, 162, 52), 
        new Rectangle(72, 318, 140, 48),
    };
    boolean disapear;
    AudioInputStream audio;
    Clip clip;

    public Menu() {

        try {
            image = ImageIO.read(getClass().getResource("images/menus/menu.png"));
            icon = ImageIO.read(getClass().getResource("images/icon/icon.png"));
            audio = AudioSystem.getAudioInputStream(new File(getClass().getResource("sounds/menu.wav").getFile()));
            clip = AudioSystem.getClip();
            clip.open(audio);

        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ex) {
        }

        frame = new JFrame("Menu");
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(icon);
        frame.setVisible(true);
        frame.add(this);
        addMouseListener(this);
        frame.requestFocus();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        clip.loop(1);

    }

    @Override
    public void paint(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        
        Area area = new Area();
        for(int i = 0; i < selection.length; ++i)
            area.add(new Area(selection[i]));
        g2d.draw(area);
        g2d.drawImage(image, 0, 0, null);
    }

    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) {

        int x = e.getX();
        int y = e.getY();
        
        for (int i = 0; i < selection.length; ++i) {
            if (selection[i].contains(x, y)) {
               if(i == 0)
               {
                   frame.dispose();
                   clip.close();
                   try {
                       new StartGame().start(new Pacman());
                   } catch (FileNotFoundException ex) { }
                   break;
               }
               if ( i == 1)
                {
                   System.out.println("second");
                   break;
               }  
               if ( i == 2)
                {
                   System.exit(0);
                   break;
               } 
               
            }

        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
