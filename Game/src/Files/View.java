package Files;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	BufferedImage[][] pics;
	
	int RANDMAX = 6;
	int RANDMIN = 0;
	
	Boolean withPlayer = true;
	Boolean withoutPlayer = false;
	
	public static int gamePanel = 1;
	
    JFrame frame = new JFrame("Estuary Project");
    JPanel panelCont = new JPanel();
    JPanel homeCard = new JPanel(); 
    JPanel clapperRail = new JPanel();
    JPanel redKnot = new JPanel();
    JButton clapperRailBtn = new JButton("Clapper Rail");
	JButton redKnotBtn = new JButton("Red Knot");
	JButton homeBtn = new JButton("Home");
	CardLayout cl = new CardLayout();
	
	
//	View() {
//		buildFrame();
//	}

	
	public static Direction getDirect(){
        return dir;
    }
	
	BufferedImage[] createImage() {
		return null;
	}
	
	/**
	 * Updates game
	 *
	 * @author Paul Jureidini, Amjed Hallak
	 * 
	 * */
	public void update() {

		frame.setSize(FRAMEWIDTH, FRAMEHEIGHT);
        //frameNum = (frameNum + 1) % frameCount;
        	if(gamePanel == 2) { // clapper rail
        		cl.show(panelCont, "CR");
        	}
        	if(gamePanel == 3) { // red knot
        		cl.show(panelCont, "RK");
        	}
            clapperRail.repaint();
            redKnot.repaint();
            repaint();
            frame.repaint();
            homeCard.repaint();
        
	}
	
	
	/**
	 * JFrame and JPanels that display the game
	 *
	 * @author Paul Jureidini, Amjed Hallak
	 * 
	 * */
	
	public View() {

		frame.setSize(FRAMEWIDTH, FRAMEHEIGHT);
		panelCont.setLayout(cl);
		
		clapperRail.setBackground(Color.GREEN);
		redKnot.setBackground(Color.BLUE);
		
		panelCont.add(homeCard, "HOME");
		panelCont.add(clapperRail, "CR");
		panelCont.add(redKnot, "RK");
		cl.show(panelCont, "HOME");
			
		frame.add(panelCont);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.addKeyListener(new KeyPress());
		frame.setFocusable(true);
	    frame.setVisible(true);
		
	} //end public View()
	
	
	
    
    
//	public void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		g.setColor(Color.BLUE);
//		g.drawRect(Model.clapperRail.getX(), Model.clapperRail.getY(), 50, 50); //getX(), getY()
//		g.setColor(Color.RED);
//		g.fillRect(Model.getPredatorPositionX(0), Model.getPredatorPositionY(0), 50, 50); //getX(), getY()
//		g.setColor(Color.BLACK);
//		g.drawString("Clapper Rail", 500, 20);
//		g.drawString("Number of Collisions: " + Integer.toString(Model.getCollisionCount()), 400, 40);
//	}
	
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
		super.paintComponent(g);
		System.out.println("hi");
		if(gamePanel == 2) {
			ArrayList<GamePiece> allObj = Model.getAllObjects(withPlayer);
			//Color color = randColor();
			super.paintComponent(g);
			for(GamePiece gp: allObj) {
				g.fillRect(gp.getX(), gp.getY(), 50, 50);
				g.setColor(Color.RED);
			}
			g.drawString("Clapper Rail", 500, 20);
			g.drawString("Number of Collisions: " + Integer.toString(Model.getCollisionCount()), 400, 40);
		}
	}
	
	public int getWidth() { return this.FRAMEWIDTH; }
    public int getHeight() { return this.FRAMEHEIGHT; }
	
}
