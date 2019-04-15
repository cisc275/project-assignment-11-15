package Files;

import java.awt.*;
import javax.swing.*;

public class ClapperRail extends JPanel{
	boolean hasTwig;
	boolean isHiding;
	
	public int xloc = 100;
	public int yloc = 100;
	
	public void drawing() {
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.blue);
		g.fillRect(xloc, yloc, 20, 20);
	}
	
}