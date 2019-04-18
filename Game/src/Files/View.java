package Files;


import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*; 

public class View extends JPanel{
	static int FRAMEWIDTH = 600;
	static int FRAMEHEIGHT = 600;
	static int AnimalWidth;
	static int AnimalHeight;
	int count;
	int x;
	int y;
	static int frameCount;
	private static Direction dir;
    private int frameNum = 0;
    private static int xloc = 50;
    private static int yloc = 50;
    private JFrame frame;
	BufferedImage[][] pics;
	
	static int arrowLeft = 37;	//WEST
	static int arrowRight = 39;	//EAST
	static int arrowUp = 38;	//NORTH
	static int arrowDown = 40;	//SOUTH
	
	
	View() {
		buildFrame();
	}

	
	public static Direction getDirect(){
        return dir;
    }
	
	BufferedImage[] createImage() {
		return null;
	}
	
	public void update(int x, int y, Direction d) {
        this.xloc = x;
        this.yloc = y;
        this.dir = d;
        //frameNum = (frameNum + 1) % frameCount;
        repaint();
	}
	
	public int getWidth() { return this.FRAMEWIDTH; }
    public int getHeight() { return this.FRAMEHEIGHT; }
    public int getImageWidth() { return this.AnimalWidth; }
    public int getImageHeight() { return this.AnimalHeight; }
	
	
	
	public void buildFrame() {
		JFrame frame = new JFrame();
        frame.getContentPane().add(this);
        this.setBackground(Color.GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAMEWIDTH, FRAMEHEIGHT);
        this.addKeyListener(new KeyPress());
        this.setFocusable(true);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLUE);
		g.drawRect(Model.clapperRail.getX(), Model.clapperRail.getY(), 50, 50);//getX(), getY()
		

		
		for (int i = 0; i<250; i = i +50) {
			g.setColor(Color.CYAN);
			g.drawRect(i, 10, 25, 25);
		}

		g.setColor(Color.RED);
		g.fillRect(Model.getPredatorPositionX(0), Model.getPredatorPositionY(0), 50, 50); //getX(), getY()
	}
	


	
}//end class View
