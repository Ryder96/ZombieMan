/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author ryder
 */
public class Data {

    Level[] levels;

    MainCharacter zombie;
    int width, height;
    int numberOfLevels;

    //Pacman alive or death
    public boolean death = false;

    //Secondary characters
    ArrayList<Soldier> soldiers = new ArrayList<>();
    ArrayList<Human> humans = new ArrayList<>();

    //Power ups
    ArrayList<Power> powers = new ArrayList<>();

    //Active level 
    int curLevel = 0;

    //Ends Images
    BufferedImage lost,victory;
    //Pause image
    BufferedImage pause;
    //Start image
    BufferedImage start;
    
    //Win the game
    static boolean WIN = false;

    public Data() throws FileNotFoundException {

        numberOfLevels = 5;
        levels = new Level[5];
        for (int i = 0; i < numberOfLevels; ++i) {

            levels[i] = new Level(0);

        }
        setMaze(curLevel);
        try {
            lost = ImageIO.read(this.getClass().getResource("images/menus/gameover.png"));
            victory = ImageIO.read(this.getClass().getResource("images/menus/ending.png"));
            pause = ImageIO.read(getClass().getResource("images/menus/pause.png"));
            start = ImageIO.read(getClass().getResource("images/menus/start.png"));
        } catch (IOException ex) {
        }

    }

    public void update() {
        for (int i = 0; i < soldiers.size(); ++i) {
            if (touching(zombie.pos, soldiers.get(i).pos)) {
                if (!zombie.SUPER) {
                    death = true;
                } else {
                    soldiers.remove(soldiers.get(i));
                }

            }
        }

        for (int i = 0; i < humans.size(); ++i) {
            if (touching(zombie.pos, humans.get(i).pos)) {
                humans.remove(humans.get(i));
            }
        }
        

        for (int i = 0; i < powers.size(); ++i) {
            if (touching(zombie.pos, powers.get(i).pos)) {
                if (powers.get(i).sprite.equals(powers.get(i).red)) {
                    zombie.setZRed();
                }
                if (powers.get(i).sprite.equals(powers.get(i).violet)) {
                    zombie.setZViolet();
                }
                if (powers.get(i).sprite.equals(powers.get(i).yellow)) {
                    zombie.setZYellow();
                }

                powers.remove(powers.get(i));

            }
        }

        moveHuman();
        moveSoldier();
        
        if(zombie.SUPER)
        {    if(zombie.timer != 0)
                --zombie.timer;
            else 
                zombie.returnNormal();
        }
        
        if (humans.isEmpty()) {
            if (curLevel < numberOfLevels) {
                ++curLevel;
                setMaze(curLevel);
            }
            if (curLevel == numberOfLevels)
                WIN = true;
            
        }
        
    }

    private void setMaze(int curLevel) {

        //load the pacman
        zombie = new MainCharacter();
        zombie.setPos(levels[curLevel].pacPos);
        zombie.SUPER = false;

        //load the soldiers
        for (int i = 0; i < levels[curLevel].posSoldiers.size(); ++i) {
            Soldier temp = new Soldier();
            temp.setPos(levels[curLevel].posSoldiers.get(i));
            soldiers.add(temp);

        }
        //load the soldiers
        for (int i = 0; i < levels[curLevel].posHumans.size(); ++i) {
            Human temp = new Human();
            temp.setPos(levels[curLevel].posHumans.get(i));
            humans.add(temp);

        }

        // load the powers up
        for (int i = 0; i < levels[curLevel].posPowers.size(); ++i) {
            Power temp = new Power();
            temp.setPos(levels[curLevel].posPowers.get(i));
            powers.add(temp);
        }

        //Dimension Window
        width = levels[curLevel].width;
        height = levels[curLevel].height;

    }

    //Humans moviments
    private void moveHuman() {
        if (!Pacman.PAUSE) {

            for (int i = 0; i < humans.size(); ++i) {
                int column = humans.get(i).pos.x;
                int row = humans.get(i).pos.y;
                int dir = decide(humans.get(i));
                switch (dir) {
                    case Direction.LEFT:
                        if (column > 0 && levels[curLevel].charAt(row, column - 1) != '0') {

                            humans.get(i).moveLeft(levels[curLevel]);
                        }
                        if (column == 0) {
                            humans.get(i).moveLeft(levels[curLevel]);
                        }

                        break;
                    case Direction.RIGHT:
                        if (column < levels[curLevel].columns - 1 && levels[curLevel].charAt(row, column + 1) != '0') {
                            humans.get(i).moveRight(levels[curLevel]);
                        }
                        if (column == levels[curLevel].columns - 1) {
                            humans.get(i).moveRight(levels[curLevel]);
                        }
                        break;
                    case Direction.UP:
                        if (row > 0 && levels[curLevel].charAt(row - 1, column) != '0') {
                            humans.get(i).moveUp(levels[curLevel]);
                        }
                        if (row == 0) {
                            humans.get(i).moveUp(levels[curLevel]);
                        }
                        break;

                    case Direction.DOWN:
                        if (row < levels[curLevel].rows - 1 && levels[curLevel].charAt(row + 1, column) != '0') {
                            humans.get(i).moveDown(levels[curLevel]);

                        }
                        if (row == levels[curLevel].rows - 1) {
                            humans.get(i).moveDown(levels[curLevel]);
                        }
                        break;

                }
            }
        }
    }

    //Soldiers moviements
    private void moveSoldier() {
        if (!Pacman.PAUSE) {

            for (int i = 0; i < soldiers.size(); ++i) {
                int column = soldiers.get(i).pos.x;
                int row = soldiers.get(i).pos.y;
                int dir = decide(soldiers.get(i));
                switch (dir) {
                    case Direction.LEFT:
                        if (column > 0 && levels[curLevel].charAt(row, column - 1) != '0') {
                            soldiers.get(i).moveLeft(levels[curLevel]);
                        }
                        if (column == 0) {
                            soldiers.get(i).moveLeft(levels[curLevel]);
                        }

                        break;
                    case Direction.RIGHT:
                        if (column < levels[curLevel].columns - 1 && levels[curLevel].charAt(row, column + 1) != '0') {
                            soldiers.get(i).moveRight(levels[curLevel]);
                        }
                        if (column == levels[curLevel].columns - 1) {
                            soldiers.get(i).moveRight(levels[curLevel]);
                        }
                        break;
                    case Direction.UP:
                        if (row > 0 && levels[curLevel].charAt(row - 1, column) != '0') {
                            soldiers.get(i).moveUp(levels[curLevel]);
                        }
                        if (row == 0) {
                            soldiers.get(i).moveUp(levels[curLevel]);
                        }
                        break;

                    case Direction.DOWN:
                        if (row < levels[curLevel].rows - 1 && levels[curLevel].charAt(row + 1, column) != '0') {
                            soldiers.get(i).moveDown(levels[curLevel]);

                        }
                        if (row == levels[curLevel].rows - 1) {
                            soldiers.get(i).moveDown(levels[curLevel]);
                        }
                        break;

                }
            }
        }
    }

    private boolean touching(Position a, Position b) {
        return Math.sqrt(Math.pow((a.x - b.x), 2) + Math.pow((a.y - b.y), 2)) < 3;
    }

    private int distance(Position a, Position b) {
        int dx = a.x - b.x;
        int dy = a.x - b.y;

        return (int) (Math.sqrt(Math.pow((dx), 2)) + Math.pow(dy, 2));
    }

    private int decide(Secondary character) {
        int rand = (int) (Math.random() * 4);
        int column = character.pos.x;
        int row = character.pos.y;
        if (levels[curLevel].charAt(row, column) == '2') {
            return Direction.direction[rand];
        }

        return character.curDir;
    }

}
