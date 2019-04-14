package Files;

import java.awt.*;
import javax.swing.*;

public class ClapperRail extends JPanel{
	boolean hasTwig;
	boolean isHiding;
	
	public void drawing() {
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.BLUE);
		g.fillRect(10, 10, 20, 20);
	}
	
}