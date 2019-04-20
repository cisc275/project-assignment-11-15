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
	int count;
	static int FRAMECOUNT;
	private static Direction dir;
    private int frameNum = 0;
   // private static int xloc = 50;
   // private static int yloc = 50;
    private JFrame frame;
	BufferedImage[][] pics;
	
	static int gameMode;
	static final int MENU = 0;
	static final int CLAPPERRAIL = 1;
	static final int REDKNOT = 2;
	
	int RANDMAX = 6;
	int RANDMIN = 0;
	
	int bigText = 40;
	
	Boolean withPlayer = true;
	Boolean withoutPlayer = false;
	
	
	View() {
		buildFrame();
	}

	
	public static Direction getDirect(){
        return dir;
    }
	
	BufferedImage[] createImage() {
		return null;
	}
	
	public void update() {
        //frameNum = (frameNum + 1) % frameCount;
        repaint();
	}
	
	public int getWidth() { return this.FRAMEWIDTH; }
    public int getHeight() { return this.FRAMEHEIGHT; }
  //  public int getImageWidth() { return this.AnimalWidth; }
   // public int getImageHeight() { return this.AnimalHeight; }
	
	
	
	public void buildFrame() {
		JFrame frame = new JFrame();
		frame.getContentPane().add(this);
        frame.getContentPane().setBackground(Color.GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAMEWIDTH, FRAMEHEIGHT);
        frame.addKeyListener(new KeyPress());
        frame.setFocusable(true);
        frame.setVisible(true);
        gameMode = MENU;
        
//        JFrame intro = new JFrame();
//        intro.getContentPane().setBackground(Color.CYAN);
//        intro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        intro.setSize(FRAMEWIDTH, FRAMEHEIGHT);
//        intro.addKeyListener(new KeyPress());
//        intro.setFocusable(true);
//        intro.setVisible(true);
        
       
	}
	
	public Color randColor() {
		int rand = (int) (Math.random() * (RANDMAX - RANDMIN)) + RANDMIN;
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
		if(gameMode == MENU) {
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, FRAMEWIDTH, 100);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Helvetica", Font.PLAIN, bigText)); 
			g.drawString("Fly away?", 325, 65);
			g.setFont(new Font("Helvetica", Font.PLAIN, 20)); 
			g.setColor(Color.BLACK);
			g.drawString("Press left for Clapper Rail game", 100, 400);
			g.drawString("Press right for Red Knot game", 500, 400);
			g.drawString("Press k at any time to return to menu", 300, 500);
		} else if (gameMode == CLAPPERRAIL) {
			ArrayList<GamePiece> allObj = Model.getAllObjects(withPlayer);
			//Color color = randColor();
			super.paintComponent(g);
			for(GamePiece gp: allObj) {
				g.fillRect(gp.getX(), gp.getY(), 50, 50);
				g.setColor(Color.RED);
			}
		} else if (gameMode == REDKNOT) {
			g.drawString("REDKNOT GAME", 100, 100);
		}
		
		
	/*	g.fillRect(Model.clapperRail.getX(), Model.clapperRail.getY(), 50, 50); //getX(), getY()
		g.setColor(Color.BLUE);
		//g.fillRect(Model.getPredatorPositionX(0), Model.getPredatorPositionY(0), 50, 50); //getX(), getY()
		g.setColor(Color.BLACK);
		/*g.setColor(Color.BLUE);
		g.drawRect(Model.clapperRail.getX(), Model.clapperRail.getY(), 50, 50); //getX(), getY()
		g.setColor(Color.RED);
		g.fillRect(Model.getPredatorPositionX(0), Model.getPredatorPositionY(0), 50, 50); //getX(), getY()
		g.setColor(Color.BLACK);*/
		//g.drawString("Clapper Rail", 500, 20);
		//g.drawString("Number of Collisions: " + Integer.toString(Model.getCollisionCount()), 400, 40);
	}
	
	
}
