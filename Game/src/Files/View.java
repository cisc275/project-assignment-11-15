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
    //private JFrame frame;
	BufferedImage[][] pics;
	
	static int arrowLeft = 37;	//WEST
	static int arrowRight = 39;	//EAST
	static int arrowUp = 38;	//NORTH
	static int arrowDown = 40;	//SOUTH
	
	public static int gamePanel = 1;
	
	
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
	public void update(int x, int y, Direction d) {
        this.xloc = x;
        this.yloc = y;
        this.dir = d;
        //frameNum = (frameNum + 1) % frameCount;
        repaint();
        
        //if(gamePanel == 1) { // home screen
        	if(gamePanel == 2) { // clapper rail
        		System.out.println("gamePanel 2 reached");
        		cl.show(panelCont, "2");
        	}
        	if(gamePanel == 3) { // red knot
        		System.out.println("gamePanel 3 reached");
        		cl.show(panelCont, "3");
        	}
        //}
        
	}
	
	public int getWidth() { return this.FRAMEWIDTH; }
    public int getHeight() { return this.FRAMEHEIGHT; }
    public int getImageWidth() { return this.AnimalWidth; }
    public int getImageHeight() { return this.AnimalHeight; }
	
	
	
	//public void buildFrame() {
//		JFrame frame = new JFrame();
//		frame.getContentPane().add(this);
//        frame.getContentPane().setBackground(Color.GRAY);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(FRAMEWIDTH, FRAMEHEIGHT);
//        frame.addKeyListener(new KeyPress());
//        frame.setFocusable(true);
//        frame.setVisible(true);
        
//        JFrame intro = new JFrame();
//        intro.getContentPane().setBackground(Color.CYAN);
//        intro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        intro.setSize(FRAMEWIDTH, FRAMEHEIGHT);
//        intro.addKeyListener(new KeyPress());
//        intro.setFocusable(true);
//        intro.setVisible(true);
        
        
	//}
    
    JFrame frame = new JFrame("Estuary Project");
    JPanel panelCont = new JPanel();
    JPanel homeCard = new JPanel(); 
    JPanel clapperRail = new JPanel();
    JPanel redKnot = new JPanel();
    JButton clapperRailBtn = new JButton("Clapper Rail");
	JButton redKnotBtn = new JButton("Red Knot");
	JButton homeBtn = new JButton("Home");
	CardLayout cl = new CardLayout();
	
	/**
	 * JFrame and JPanels that display the game
	 *
	 * @author Paul Jureidini
	 * 
	 * */
	
	public View() {
		panelCont.setLayout(cl);
		
//		homeCard.add(clapperRailBtn);
//		homeCard.add(redKnotBtn);
//		redKnot.add(homeBtn);
//		clapperRail.add(homeBtn);
		
		clapperRail.setBackground(Color.BLUE);
		redKnot.setBackground(Color.RED);
		
		panelCont.add(homeCard, "1");
		panelCont.add(clapperRail, "2");
		panelCont.add(redKnot, "3");
		cl.show(panelCont, "1");
		
		
				
		frame.add(panelCont);
		frame.setSize(FRAMEWIDTH, FRAMEHEIGHT);
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
	
	
}//end class View
