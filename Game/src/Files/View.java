package Files;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.*; 

public class View extends JPanel{
	
	static final int FRAMEWIDTH = 800;
	static final int FRAMEHEIGHT = 600;
	static final int IMGHEIGHT = 50;
	static final int IMGWIDTH = 50;
	static final int FRAMECOUNT = 2;
	private static Direction dir;
    private int frameNum = 0;
    private int frameCount = 2;
    private JFrame frame;

	int count;
	int clk = 0;
	static int gameMode = -1;
	static final int MENU = 0;
	static final int CLAPPERRAIL1 = 1;
	static final int CLAPPERRAIL2 = 2;
	static final int CLAPPERRAIL3 = 3;
	static final int REDKNOT = 4;
	static final int WINNER = 5;
	static final int LOSER = 6;
	static final int CLK_MAX = 150;
	
	//static int bushMax = 4;
	
	int RANDMAX = 6;
	int RANDMIN = 0;
	
	int bigText = 40;
	
	
	ArrayList<GamePiece> allObj;
	ArrayList<Animal> predators;
	ArrayList<Cloud> clouds;
	Map<String, BufferedImage> pics;
	
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
	 * Update method for controller. Calls method to repaint frame.
	 *
	 * @author Amjed Hallak
	 * 
	 * */
	public void update() {
		clk++;
		if(clk > CLK_MAX) {
			frameNum = (frameNum + 1) % frameCount;
			clk = 0;
		}
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
        loadImages();

        gameMode = MENU;
	}
	
	public void loadImages() {
		pics = new HashMap<>();
		String[] arrOfStr = {"mmenubkg", "test-face", "myth", "cloud1", "cloud2",
				"arrowMap", "redKnot", "falcon", "myth", "boss"};
		for(String s: arrOfStr) {
			BufferedImage newImg = createImage(s);
			if(newImg.getWidth() == IMGWIDTH) {
				pics.put(s, newImg);
			} else if(newImg.getWidth() == 2*IMGWIDTH) {
				//System.out.println(s);
				String str1 = s.concat("1");
				String str2 = s.concat("2");
				pics.put(str1, newImg.getSubimage(0*IMGWIDTH, 0, IMGWIDTH, IMGHEIGHT));
				pics.put(str2, newImg.getSubimage(1*IMGWIDTH, 0, IMGWIDTH, IMGHEIGHT));
			} else { 
				pics.put(s, newImg);
			}
		}
	}
	
	/**
	 * Returns a BufferedImage 
	 *
	 * @author Unknown
	 * @param String of file path
	 * @returns BufferedImage from source file
	 * 
	 * */
	public BufferedImage createImage(String filename){
		BufferedImage bufferedImage;
		String path = "src/images/";
    	try {
    		bufferedImage = ImageIO.read(new File(path.concat(filename).concat(".png")));
    		if(bufferedImage.getWidth() > IMGWIDTH) {
    			
    		}
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
			this.setBackground(Color.CYAN);
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, FRAMEWIDTH, 100);
			g.setColor(Color.WHITE);
			g.drawImage(pics.get("mmenubkg"), 0, 0, null, this);
			g.setFont(new Font("Helvetica", Font.PLAIN, 20)); 
			g.setColor(Color.BLACK);
			g.drawImage(pics.get("arrowMap"), 100, 400, null, this);
			g.drawString("Press k at any time to return to menu", 300, 300);
			clouds = Model.getClouds();
			g.drawImage(pics.get(clouds.get(0).getType()), clouds.get(0).getX(), clouds.get(0).getY(), null, this);
			g.drawImage(pics.get(clouds.get(1).getType()), clouds.get(1).getX(), clouds.get(1).getY(), null, this);
			g.setFont(new Font("Helvetica", Font.PLAIN, bigText)); 
			g.drawString("Estuary Birds", 325, 65);
			break;
			
		case(CLAPPERRAIL1): //Cycle through all levels
		case(CLAPPERRAIL2):
		case(CLAPPERRAIL3):// Clapper Rail Game View Logic
			allObj = Model.getAllObjects(withoutPlayer, withPreds);
			predators = Model.getPredators();
			for(GamePiece gp: allObj) {
				g.setColor(getColor(gp.toString()));
				g.fillRect(gp.getX(), gp.getY(), 50, 50);
			}

			for(Animal p: predators) {
				g.setColor(getColor(p.toString()));
				g.fillRect(p.getX(), p.getY(), 50, 50);
				g.drawImage(pics.get("myth"), p.getX(), p.getY(), 50, 50, this); //THarv Image Substituion
			}

			g.drawImage(pics.get("test-face"), Model.getX(), Model.getY(), null, this);
			g.setColor(Color.BLUE);
			g.drawString("Twig count: " + Model.twigCount, 500,25);
			g.drawString("death toll lol: " + Model.deathToll, 500,50);
			g.drawString("Bush count: " + Model.bushCount, 500,75);
			switch(gameMode) {
			case(CLAPPERRAIL1): //Cycle through all levels
				g.drawString("LEVEL 1", 10, 20);
				break;
			case(CLAPPERRAIL2):
				g.drawString("LEVEL 2", 10, 20);
				break;
			case(CLAPPERRAIL3):
				g.drawString("LEVEL 3", 10, 20);
				break;
			}
			break;
			
		case(REDKNOT): // Red Knot Game View Logic
			this.setBackground(Color.CYAN);
			allObj = Model.getAllObjects(withoutPlayer, withPreds);
			predators = Model.getPredators();
			g.drawString("REDKNOT GAME", 100, 100);
			g.drawString("death toll lol: " + Model.deathToll, 500,50);
			clouds = Model.getClouds();
			for (Cloud c: clouds) {
				g.drawImage(pics.get(c.getType()), c.getX(), c.getY(), null, this);
			}
			for(GamePiece gp: allObj) {
				g.setColor(getColor(gp.toString()));
				g.fillRect(gp.getX(), gp.getY(), 50, 50);
			}
			for(Animal p: predators) {
				switch(frameNum) {
				case(0):
					g.drawImage(pics.get("falcon1"),  p.getX(), p.getY(), null, this);
					break;
				case(1):
					g.drawImage(pics.get("falcon2"),  p.getX(), p.getY(), null, this);
					break;
				}
			}
			switch(frameNum) {
			case(0):
				g.drawImage(pics.get("redKnot1"),  Model.getX(), Model.getY(), null, this);
				break;
			case(1):
				g.drawImage(pics.get("redKnot2"),  Model.getX(), Model.getY(), null, this);
				break;
			}	
			break;
		case(WINNER): //WINNER screen 
			this.setBackground(Color.GREEN);
			g.drawString("WINNER", 200, 200);
			g.drawString("Press the LEFT arrow key to go back to the main menu", 200, 400);
			break;
			
		case(LOSER): //LOSER screen 
			this.setBackground(Color.RED);
			g.drawString("Sorry, You Lost. Try Again!", 200, 200);
			g.drawString("Press the LEFT arrow key to go back to the main menu", 200, 400);
			break;
		}
	}
	
	@Override
	public int getWidth() { return View.FRAMEWIDTH; }
    @Override
	public int getHeight() { return View.FRAMEHEIGHT; }
	
	
}
