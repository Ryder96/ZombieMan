/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;

/**
 *
 * @author ryder
 */
public class Pacman extends Game {

    //Fine gioco
    static protected boolean END = false;

    //Inizio gioco
    static protected boolean START = false;

    Data data;

    //
    private int frame = 0;

    //Moviments pacman 
    int curDir, reqDir;
    final int SUCCESS = 1, FAIL = 0;

    //Current level
    int curLevel;
    //Sounds
    AudioInputStream groundSound;
    static Clip clipGS;
    AudioInputStream endSound;
    static Clip clipEndSound;

    //Menu pausa
    static boolean PAUSE = false;

    public Pacman() throws FileNotFoundException {
        title = "Zombie";
        frame = 0;
        data = new Data();
        width = data.width;
        height = data.height;
        curDir = reqDir = KeyEvent.VK_LEFT;
        curLevel = data.curLevel;
        delay = 30;
        try {
            icon = ImageIO.read(getClass().getResource("images/icon/icon.png"));
        } catch (IOException ex) {
        }
    }

    @Override
    public void update() {

        if (!data.death) {
            if (!PAUSE) {
                frame++;
                if (frame > 5) {
                    frame = 0;
                }

                if (move(reqDir) == SUCCESS) {
                    curDir = reqDir;
                    move(reqDir);

                } else {
                    move(curDir);
                }

            }

            data.update();
        }
    }

    @Override
    public void draw(Graphics2D g) {

        if (!data.death && !data.WIN) {
            g.setColor(Color.BLACK);
            //Draw the maze
            g.drawImage(data.levels[curLevel].back, 0, 0, null);

            //Draw the powers up
            for (int i = 0; i < data.powers.size(); ++i) {
                g.drawImage(data.powers.get(i).sprite.getSubimage(frame * 30, 0, 28, 28),
                        data.powers.get(i).pos.x * 2 - 14, data.powers.get(i).pos.y * 2 - 14, null);
            }
            //Draw the humans
            for (int i = 0; i < data.humans.size(); ++i) {
                g.drawImage(data.humans.get(i).sprite.getSubimage((frame / 2) * 30, (data.humans.get(i).curDir - 37) * 30, 28, 28),
                        data.humans.get(i).pos.x * 2 - 14, data.humans.get(i).pos.y * 2 - 14, null);
            }

            //Draw the soldiers
            for (int i = 0; i < data.soldiers.size(); ++i) {
                g.drawImage(data.soldiers.get(i).sprite.getSubimage((frame / 2) * 30, (data.soldiers.get(i).curDir - 37) * 30, 28, 28),
                        data.soldiers.get(i).pos.x * 2 - 14, data.soldiers.get(i).pos.y * 2 - 14, null);
            }

            //Draw the pacman
            g.drawImage(data.zombie.sprite.getSubimage((frame / 2) * 30, (curDir - 37) * 30, 28, 28),
                    data.zombie.pos.x * 2 - 14, data.zombie.pos.y * 2 - 14, null);

            if (!START) {
                g.drawImage(data.start, 0, 0, null);
            }
            if (PAUSE) {
                g.drawImage(data.pause, 0, 0, null);
            }
        }

        if (data.WIN) {
            g.drawImage(data.victory, 0, 0, null);
            end = true;
        }
        if (data.death) {
            g.drawImage(data.lost, 0, 0, null);
            end = true;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        if (key >= 37 && key <= 40) {
            reqDir = key;

        }
        if (START) {
            if (key == KeyEvent.VK_P) {
                if (PAUSE) {
                    PAUSE = false;
                } else {
                    PAUSE = true;
                }
            }
        }
        if (key == KeyEvent.VK_S) {
            START = true;
        }

    }

    private int move(int reqDir) {
        int column = data.zombie.pos.x;
        int row = data.zombie.pos.y;
        switch (reqDir) {

            case KeyEvent.VK_LEFT:
                if (column > 0 && data.levels[curLevel].charAt(row, column - 1) != '0') {
                    data.zombie.pos.x -= 1;

                    return SUCCESS;
                }
                if (column == 0) {
                    data.zombie.pos.x = data.levels[curLevel].columns - 1;
                    return SUCCESS;

                }

                break;
            case KeyEvent.VK_RIGHT:
                if (column < data.levels[data.curLevel].columns - 1 && data.levels[curLevel].charAt(row, column + 1) != '0') {
                    data.zombie.pos.x += 1;
                    return SUCCESS;
                }
                if (column == data.levels[curLevel].columns - 1) {
                    data.zombie.pos.x = 0;
                    return SUCCESS;
                }

                break;
            case KeyEvent.VK_UP:
                if (row > 0 && data.levels[curLevel].charAt(row - 1, column) != '0') {
                    data.zombie.pos.y -= 1;
                    return SUCCESS;
                }
                if (row == 0) {
                    data.zombie.pos.y = data.levels[curLevel].rows - 1;
                    return SUCCESS;

                }

                break;
            case KeyEvent.VK_DOWN:
                if (row < data.levels[curLevel].rows - 1 && data.levels[curLevel].charAt(row + 1, column) != '0') {
                    data.zombie.pos.y += 1;
                    return SUCCESS;

                }
                if (row == data.levels[curLevel].rows - 1) {
                    data.zombie.pos.y = 0;
                    return SUCCESS;

                }
                break;

        }

        return FAIL;

    }

}
