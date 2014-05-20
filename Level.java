/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
 *
 * @author ryder
 */
public class Level {

    Position pacPos;
    int levelCount;
    //Postions humans and soldiers
    ArrayList<Position> posSoldiers = new ArrayList<>();
    ArrayList<Position> posHumans = new ArrayList<>();
    
    
    ArrayList<String> lines = new ArrayList<>();
    
    //Position Powers up
    ArrayList<Position> posPowers = new ArrayList<>();
    //Position pacman
    int row, column;

    //Dimesion levels
    public int rows, columns;
    public int width, height;
    
    //Background image
    BufferedImage back;

    public Level(int m) throws FileNotFoundException {

        reader(m);

    }

    public void reader(int m) throws FileNotFoundException {
        Scanner s = new Scanner(new File(getClass().getResource("mazes/").getPath()+"level0.txt"));
        try {
            back = ImageIO.read(getClass().getResource("images/levels/level0.png"));
        } catch (IOException ex) { }
        
        int r = 0;
        int rs = 0;
        int rh = 0;
        int rp = 0;
        while (s.hasNextLine()) {
            String line = s.nextLine();
            lines.add(line);
            if (line.contains("5")) {
                row = r;
                column = line.indexOf("5");
                pacPos = new Position(column, row);

            }
            if (line.contains("3")) {
                Position temp = new Position();
                temp.y = rs;
                temp.x = line.indexOf("3");
                posSoldiers.add(temp);
            }
            if (line.contains("4")) {
                Position temp = new Position();
                temp.y = rh;
                temp.x = line.indexOf("4");
                posHumans.add(temp);
            }
            int i = 0;
            while(i < line.length())
            {
                if(line.charAt(i) == '6'){
                Position temp = new Position();
                temp.y = rp;
                temp.x = line.indexOf("6");
                posPowers.add(temp);
                }
                ++i;
            }
            ++r;
            ++rs;
            ++rh;
            ++rp;
        }

        rows = lines.size();
        columns = lines.get(1).length();

        width = columns *2 + 28;
        height = rows *2 + 28;
        
        

    }

    char charAt(int row, int column) {

        return lines.get(row).charAt(column);
    }

}
