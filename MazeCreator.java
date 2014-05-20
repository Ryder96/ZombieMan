package project;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class MazeCreator {

	public static void main(String[] args) throws IOException {
		for (int m=0; m<4; m++) {
			
			// load in the maze strings
			ArrayList<String> lines = new ArrayList<>();
                        File file = new File("level0.txt");
			Scanner s = new Scanner(file);
			while (s.hasNextLine()) {
				lines.add(s.nextLine());
			}
			s.close();
			
			int rows = lines.size();
			int columns = lines.get(0).length();
			int width = columns*2;
			int height = rows*2;
			
			// draw maze on an image
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = image.createGraphics();
			for (int r=0; r<rows; r++) {
				for (int c=0; c<columns; c++) {
					if (lines.get(r).charAt(c) != '0') {
						g.fillRect(c*2-14, r*2-14, 28, 28);
					}
				}
			}
			g.dispose();
			
			
			// save the image
			ImageIO.write(image, "png", new File("level0.png"));
		}
	}
}
