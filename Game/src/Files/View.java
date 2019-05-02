package Files;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*; 

public class View extends JPanel{
	
	static int FRAMEWIDTH = 800;
	static int FRAMEHEIGHT = 600;
	int count;
	static int FRAMECOUNT;
	private static Direction dir;
    private int frameNum = 0;
    private JFrame frame;
	BufferedImage[][] pics;
	
	static int gameMode;
	
	static final int MENU = 0;
	static final int CLAPPERRAIL1 = 1;
	static final int CLAPPERRAIL2 = 2;
	static final int CLAPPERRAIL3 = 3;
	static final int REDKNOT = 4;
	
	//static int bushMax = 4;
	
	int RANDMAX = 6;
	int RANDMIN = 0;
	
	int bigText = 40;
	
	
	ArrayList<GamePiece> allObj;
	ArrayList<Animal> predators;
	
	Boolean withPlayer = true;
	Boolean withoutPlayer = false;
	Boolean withPreds = true;
	Boolean withoutPreds = false;
	
	BufferedImage img;
	
    /**
	 * Constrcutor. Calls method to build frame
	 *
	 * @author Amjed Hallak
	 * 
	 * */
	View() {
		buildFrame();
	}
	
    /**
	 * TBD - should load images into game
	 *
	 * @author TBD
	 * 
	 * */
	BufferedImage[] createImage() {
		return null;
	}
	
    /**
	 * Update method for controller. Calls method to repaint frame.
	 *
	 * @author Amjed Hallak
	 * 
	 * */
	public void update() {
        //frameNum = (frameNum + 1) % frameCount;
        repaint();
	}
	
    /**
	 *Sets up a new JFrame and adds listeners. Sets game mode to default (MENU)
	 *
	 * @author Amjed Hallak
	 * 
	 * */
	public void buildFrame() {
		JFrame frame = new JFrame();
		frame.getContentPane().add(this);
        frame.getContentPane().setBackground(Color.GRAY);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(FRAMEWIDTH, FRAMEHEIGHT);
        frame.addKeyListener(new KeyPress());
        //frame.setFocusable(true);
        frame.setVisible(true);
        gameMode = MENU;
	}
	
	/**
	 * Returns a BufferedImage 
	 *
	 * @author Unknown
	 * @param String of file path
	 * @returns BufferedImage from source file
	 * 
	 * */
	private BufferedImage createImage(String filename){
		BufferedImage bufferedImage;
    	try {
    		bufferedImage = ImageIO.read(new File(filename));
    		return bufferedImage;
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return null;
	}
	
    /**
	 * Returns a color based on the type of object. For alpha/beta use.
	 *
	 * @author Amjed Hallak, Paul Jureidini
	 * @param String from toString of class
	 * 
	 * */
	public Color getColor(String type) {
		switch(type) {
		case("Clapper Rail"):
			return Color.BLACK;
		case("Animal"):
			return Color.RED;
		case("Twig"):
			return Color.GREEN;
		case("GamePiece"):
			return Color.yellow;
		case("Bush"):
			
			if(Model.getBushCount() < Model.getBushMax()) {
				Color myColour = new Color(102, 51, 0, Model.getBushTrans()); //bushTrans
				return myColour;
			}else {
				Color color = new Color(0,102,0);
				return color;
			}
		}
		return Color.white;
	}
	
    /**
	 * Paints the frame based on the current game mode and model logic
	 *
	 * @author Amjed Hallak, Paul Jureidini
	 * @param The view "graphic"
	 * 
	 * */
	@Override
	public void paintComponent(Graphics g) {
		switch(gameMode) {
		case(MENU): // Main menu Game View Logic
			this.setBackground(Color.GRAY);
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, FRAMEWIDTH, 100);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Helvetica", Font.PLAIN, bigText)); 
			g.drawString("Estuary Birds", 325, 65);
			g.setFont(new Font("Helvetica", Font.PLAIN, 20)); 
			g.setColor(Color.BLACK);
			g.drawString("Press left for Clapper Rail game", 100, 400);
			g.drawString("Press right for Red Knot game", 500, 400);
			g.drawString("Press k at any time to return to menu", 300, 500);
			
			break;
			
		case(CLAPPERRAIL1): // Clapper Rail Game View Logic
			allObj = Model.getAllObjects(withoutPlayer, withPreds);
			predators = Model.getPredators();
			//super.paintComponent(g);
			for(GamePiece gp: allObj) {
				g.setColor(getColor(gp.toString()));
				g.fillRect(gp.getX(), gp.getY(), 50, 50);
				//g.drawImage(createImage("src/images/myth.png"), gp.getX(), gp.getY(), null, this);
			}

			for(Animal p: predators) {
				g.setColor(getColor(p.toString()));
				g.fillRect(p.getX(), p.getY(), 50, 50);
				//g.drawImage(createImage("src/images/myth.png"), gp.getX(), gp.getY(), null, this);
			}

			g.drawImage(createImage("src/images/test-face.png"), Model.getX(), Model.getY(), null, this);
			g.setColor(Color.BLUE);
			g.setFont(new Font("Helvetica", Font.PLAIN, 20)); 
			g.drawString("Twig count: " + Model.twigCount, 500,25);
			g.drawString("death toll lol: " + Model.deathToll, 500,50);
			g.drawString("Bush count: " + Model.bushCount, 500,75);
			
			break;
			
		case(CLAPPERRAIL2): // Clapper Rail Game View Logic
			
			allObj = Model.getAllObjects(withoutPlayer, withPreds);
			predators = Model.getPredators();
			//super.paintComponent(g);
			for(GamePiece gp: allObj) {
				g.setColor(getColor(gp.toString()));
				g.fillRect(gp.getX(), gp.getY(), 50, 50);
				//g.drawImage(createImage("src/images/myth.png"), gp.getX(), gp.getY(), null, this);
			}

			for(Animal p: predators) {
				g.setColor(getColor(p.toString()));
				g.fillRect(p.getX(), p.getY(), 50, 50);
				//g.drawImage(createImage("src/images/myth.png"), gp.getX(), gp.getY(), null, this);
			}

			g.drawImage(createImage("src/images/test-face.png"), Model.getX(), Model.getY(), null, this);
			g.setColor(Color.BLUE);
			g.setFont(new Font("Helvetica", Font.PLAIN, 20)); 
			g.drawString("Twig count: " + Model.twigCount, 500,25);
			g.drawString("death toll lol: " + Model.deathToll, 500,50);
			g.drawString("Bush count: " + Model.bushCount, 500,75);
			
			break;
		
		case(CLAPPERRAIL3): // Clapper Rail Game View Logic
			allObj = Model.getAllObjects(withoutPlayer, withPreds);
			predators = Model.getPredators();
			//super.paintComponent(g);
			for(GamePiece gp: allObj) {
				g.setColor(getColor(gp.toString()));
				g.fillRect(gp.getX(), gp.getY(), 50, 50);
				//g.drawImage(createImage("src/images/myth.png"), gp.getX(), gp.getY(), null, this);
			}

			for(Animal p: predators) {
				g.setColor(getColor(p.toString()));
				g.fillRect(p.getX(), p.getY(), 50, 50);
				//g.drawImage(createImage("src/images/myth.png"), gp.getX(), gp.getY(), null, this);
			}

			g.drawImage(createImage("src/images/test-face.png"), Model.getX(), Model.getY(), null, this);
			g.setColor(Color.BLUE);
			g.setFont(new Font("Helvetica", Font.PLAIN, 20)); 
			g.drawString("Twig count: " + Model.twigCount, 500,25);
			g.drawString("death toll lol: " + Model.deathToll, 500,50);
			g.drawString("Bush count: " + Model.bushCount, 500,75);
			
			break;
			
		case(REDKNOT): // Red Knot Game View Logic
			this.setBackground(Color.CYAN);
			allObj = Model.getAllObjects(withoutPlayer, withPreds);
			predators = Model.getPredators();
			g.drawString("REDKNOT GAME", 100, 100);
			for(GamePiece gp: allObj) {
				g.setColor(getColor(gp.toString()));
				g.fillRect(gp.getX(), gp.getY(), 50, 50);
				//g.drawImage(createImage("src/images/myth.png"), gp.getX(), gp.getY(), null, this);
			}
			for(Animal p: predators) {
				//g.setColor(getColor(p.toString()));
				g.setColor(Color.PINK);
				g.fillRect(p.getX(), p.getY(), 50, 50);
				//g.drawImage(createImage("src/images/myth.png"), p.getX(), p.getY(), null, this);
			}
			g.drawImage(createImage("src/images/test-face.png"), Model.getX(), Model.getY(), null, this);
			
			break;
			
		}
	}
	
	@Override
	public int getWidth() { return View.FRAMEWIDTH; }
    @Override
	public int getHeight() { return View.FRAMEHEIGHT; }
	
	
}
