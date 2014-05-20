package project;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

/**
 *
 * @author Andres
 */
public class GameCanvas extends JComponent{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Game game;
    
    public GameCanvas(Game game)
    {
        this.game = game;
        addKeyListener(game);
        addMouseListener(game);
        requestFocus();
    }
    
    @Override
    public void paint(Graphics g)
    {
        game.draw((Graphics2D) g);
    }
    
}
