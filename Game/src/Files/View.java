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
	static final int CLAPPERRAIL = 1;
	static final int REDKNOT = 2;
	
	int RANDMAX = 6;
	int RANDMIN = 0;
	
	int bigText = 40;
	
	Boolean withPlayer = true;
	Boolean withoutPlayer = false;
	
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
	 * @author Amjed Hallak
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
		case("alt2"):
			return Color.pink;
		}
		return Color.white;
	}
	
	
	/**
	 * Guided tutorial for ClapperRail game
	 * 
	 * @author: Amelia Abobo & Adheena Chacko
	 * @param none
	 * 
	 */
	
	public void RedKnotTutorial() {
		System.out.println("Red Knot Tutorial");
	}
	
    /**
	 * Paints the frame based on the current game mode and model logic
	 *
	 * @author Amjed Hallak
	 * @param The view "graphic"
	 * 
	 * */
	@Override
	public void paintComponent(Graphics g) {
		if(gameMode == MENU) {
			/* Main menu Game View Logic
			 */
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, FRAMEWIDTH, 100);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Helvetica", Font.PLAIN, bigText)); 
			g.drawString("game w birds??", 325, 65);
			g.setFont(new Font("Helvetica", Font.PLAIN, 20)); 
			g.setColor(Color.BLACK);
			g.drawString("Press left for Clapper Rail game", 100, 400);
			g.drawString("Press right for Red Knot game", 500, 400);
			g.drawString("Press k at any time to return to menu", 300, 500);
		} else if (gameMode == CLAPPERRAIL) {
			/* Clapper Rail Game View Logic
			 */
			ArrayList<GamePiece> allObj = Model.getAllObjects(withoutPlayer);
			//super.paintComponent(g);
			for(GamePiece gp: allObj) {
				//g.setColor(getColor(gp.toString()));
				//g.fillRect(gp.getX(), gp.getY(), 50, 50);
				g.drawImage(createImage("src/images/myth.png"), gp.getX(), gp.getY(), null, this);
			}
			g.drawImage(createImage("src/images/test-face.png"), Model.getX(), Model.getY(), null, this);
			g.setColor(Color.BLUE);
			g.setFont(new Font("Helvetica", Font.PLAIN, 20)); 
			g.drawString("Twig count: " + Model.twigCount, 500,25);
			g.drawString("death toll lol: " + Model.deathToll, 500,50);
			if (Model.getTutorial() == true){
				g.drawString("Use up, down, left, and right arrows to move your clapper rail to avoid predators.", 100, 100);
			}
		} else if (gameMode == REDKNOT) {
			/* Red Knot Game View Logic
			 */
			g.drawString("REDKNOT GAME", 100, 100);
			g.drawImage(createImage("src/images/test-face.png"), Model.getX(), Model.getY(), Color.RED, this);

		}
	}
	
	
	@Override
	public int getWidth() { return View.FRAMEWIDTH; }
    @Override
	public int getHeight() { return View.FRAMEHEIGHT; }
	
	
}
