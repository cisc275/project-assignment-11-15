package Files;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*; 

public class View extends JPanel{
	static int FRAMEWIDTH = 800;
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
        frame.getContentPane().setBackground(Color.GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAMEWIDTH, FRAMEHEIGHT);
        frame.addKeyListener(new KeyPress());
        frame.setFocusable(true);
        frame.setVisible(true);
        
//        JFrame intro = new JFrame();
//        intro.getContentPane().setBackground(Color.CYAN);
//        intro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        intro.setSize(FRAMEWIDTH, FRAMEHEIGHT);
//        intro.addKeyListener(new KeyPress());
//        intro.setFocusable(true);
//        intro.setVisible(true);
        
       
	}
	
	public Color randColor() {
		int rand = (int) (Math.random() * (6 - 0)) + 0;
		switch(rand) {
		case(0):
			return Color.red;
		case(1):
			return Color.blue;
		case(2):
			return Color.green;
		case(3):
			return Color.yellow;
		case(4):
			return Color.pink;
		}
		return Color.white;
	}
	
	public void paintComponent(Graphics g) {
		ArrayList<Object> allObj = Model.getAllObjects();
		//Color color = randColor();
		super.paintComponent(g);
		g.fillRect(Model.clapperRail.getX(), Model.clapperRail.getY(), 50, 50); //getX(), getY()
		g.setColor(Color.BLUE);
		g.fillRect(Model.getPredatorPositionX(0), Model.getPredatorPositionY(0), 50, 50); //getX(), getY()
		g.setColor(Color.BLACK);
		/*g.setColor(Color.BLUE);
		g.drawRect(Model.clapperRail.getX(), Model.clapperRail.getY(), 50, 50); //getX(), getY()
		g.setColor(Color.RED);
		g.fillRect(Model.getPredatorPositionX(0), Model.getPredatorPositionY(0), 50, 50); //getX(), getY()
		g.setColor(Color.BLACK);*/
		g.drawString("Clapper Rail", 500, 20);
		g.drawString("Number of Collisions: " + Integer.toString(Model.getCollisionCount()), 400, 40);
	}
	
	
}//end class View
