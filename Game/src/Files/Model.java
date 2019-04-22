package Files;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;
import java.util.Map;

public class Model{
	private final int FRAMEWIDTH;
    private final int FRAMEHEIGHT;

	static Boolean withPlayer = true;
	static Boolean withoutPlayer = false;
	static Boolean movePredators;

    private static int xloc;
    private static int yloc;
    
    static String predStr = "Predator";
    static String gamePcString = "GamePiece";
    
    static Animal clapperRail;
    static ArrayList<Animal> predators = new ArrayList<Animal>();
    static ArrayList<GamePiece> gamePieces = new ArrayList<GamePiece>();
    
    public static int collisionCount = 0;
	int RANDMAX = 4;
	int RANDMIN = 1;
   
	static int gameMode;
	static final int MENU = 0;
	static final int CLAPPERRAIL = 1;
	static final int REDKNOT = 2;
	
	static int clkCount = 0;
	static final int CLKMAX = 10000000;
	static final int OBLIVION = 1000;
	static int twigCount = 0;
	static int bushCount = 0;
	static int deathToll; //temp
	static int playerHealth = 100;
    
    private Direction dir = Direction.NORTH;
	

    public Model(int fw, int fh){
        this.FRAMEWIDTH = fw;
        this.FRAMEHEIGHT = fh;
    }
    
    /**
	 * Sets up general testbed for the Clapper Rail game. Currently spawns objects
	 *
	 * @author Amjed Hallak
	 * 
	 * */
    public static void setUpClapperRailGame() {
    	clapperRail = new ClapperRail();
    	spawnObject(predStr, 100,100);
    	spawnObject(gamePcString, 300,500);
    	spawnObject(predStr, 500, 300);
    	
    	spawnObject(predStr, 600, 500);
    	spawnObject(predStr, 150, 250);
    	spawnObject(predStr, 250, 100);
    	spawnObject(predStr, 400, 200);
    	spawnObject(predStr, 200, 400);
    	spawnObject(predStr, 300, 300);
    	spawnObject(predStr, 350, 350);
    	spawnObject(predStr, 700, 300);
    	spawnObject(predStr, 700, 200);
    	spawnObject(predStr, 700, 550);
    	
    }
    
    /**
	 * Changes the game and resets the scene. 0 = Main menu, 1 = Clapper rail, 2 = Red knot
	 *
	 * @author Amjed Hallak
	 * @param Game mode to change to. Either 0, 1, or 2
	 * 
	 * */
    public static void changeGameMode(int gameMode) {
    	switch(gameMode) {
    	case(MENU):
    		removeAllObjects();
    		clapperRail = null;
    		View.gameMode = MENU;
    		break;
    	case(CLAPPERRAIL):
    		bushCount = 0;
    		twigCount = 0;
    		deathToll = 0;
    		playerHealth = 100;
    		setUpClapperRailGame();
    		View.gameMode = CLAPPERRAIL;
    		break;
    	case(REDKNOT):
    		View.gameMode = REDKNOT;
    		break;
    	}
    }
    
    /**
	 * Function to return a list of all the present objects on the screen
	 *
	 * @author Amjed Hallak
	 * @return ArrayList of all the birds, predators, and other objects on screen
	 * 
	 * */
    public static ArrayList<GamePiece> getAllObjects(Boolean inclPlayer){
    	ArrayList<GamePiece> allObjects = new ArrayList<GamePiece>();
    	if(inclPlayer) {
    		allObjects.add(clapperRail);
    	}
    	for(GamePiece p: predators) {
    		allObjects.add(p);
    	}
    	for(GamePiece p: gamePieces) {
    		allObjects.add(p);
    	}
    	//System.out.println(allObjects.size());
    	return allObjects;
    }
    
    /**
	 * Spawns a new object at a specified x and y position
	 *
	 * @author Amjed Hallak
	 * @param Type of object, whether it's a predator or a gamepiece
	 * @param x and y coordinates of the object being spawned
	 * 
	 * */
    static public void spawnObject(String type, int x, int y) {
    	switch(type) {
    	case("Predator"):
    		predators.add(new Animal(x,y));
    		break;
    	case("GamePiece"):
    		gamePieces.add(new GamePiece(x,y));
    		break;
    	}
    }
    
    /**
	 * Checks for collisions between an animal object, and all game objects ont he screen
	 *
	 * @author Amjed Hallak, Paul Jureidini
	 * @param Animal being compared to for a grid collision
	 * 
	 * */
    public static void chkCollision(Animal b) {
    	ArrayList<GamePiece> objs = getAllObjects(withoutPlayer);
    	for(GamePiece o: objs) {
	    	if (b.getX() == o.getX() && b.getY() == o.getY()) {
	    		if(o.toString().equals( "GamePiece") ){
	    			System.out.println("twig");
	    			o.x += OBLIVION;
	    			twigCount++;
	    		}
	    		if(o.toString().equals("Animal")) {
	    			playerHealth--;
	    			deathToll++;
	    		}
	    	}
    	}
    }
    
    /**
	 * Removes all objects for game reset
	 *
	 * @author Amjed Hallak
	 * 
	 * */
    public static void removeAllObjects() {
    	Iterator preds = predators.iterator();
    	Iterator gps = gamePieces.iterator();
    	System.out.println(preds);
    	System.out.println("start");
    	while(preds.hasNext()) {
    		preds.next();
	    	preds.remove();
    	}
    	while(gps.hasNext()) {
    		gps.next();
    		gps.remove();
    	}
    }
    
    /**
	 * Moves the animals position based on the input direction
	 *
	 * @author Amjed Hallak
	 * @param Enumerated direction
	 * 
	 * */
    public static void move(Direction dir) {
    	clapperRail.move(dir);
		chkCollision(clapperRail);
    	
    }
    
    public static void updateClock() {
    	clkCount++;
    	if(clkCount > CLKMAX) {
    		movePredators = true;
    		clkCount = 0;
    	} else {
    		movePredators = false;
    	}
    }
    
    /**
	 * Method for View class to call to check player x and y direction
	 *
	 * @author Amjed Hallak
	 * 
	 * */
	void updateLocationAndDirection() {
		if(clapperRail != null) { //If game is active
			xloc = clapperRail.getX();
			yloc = clapperRail.getY();
			updateClock();
			if(movePredators) {
				for(Animal p: predators) {
					int random = (int)(Math.random() * RANDMAX + RANDMIN);
						switch(random) {
						case(1):
							if(p.getX() < (View.FRAMEWIDTH - p.INCR)) {
								p.move(Direction.EAST);
							} else {
								p.move(Direction.WEST);
							}
							break;
						case(2):
							if(p.getX() > 0) {
								p.move(Direction.WEST);
							} else {
								p.move(Direction.EAST);
							}
							break;
						case(3):
							if(p.getY() < (View.FRAMEHEIGHT - p.INCR)) {
								p.move(Direction.SOUTH);
							} else {
								p.move(Direction.NORTH);
							}
							break;
						case(4):
							if(p.getY() > 0) {
								p.move(Direction.NORTH);
							} else {
								p.move(Direction.SOUTH);
							}
							break;
						}
				}
				chkCollision(clapperRail);
			}
		}
	}
	
	/**
	 * Main method
	 *
	 * @author Team 11-15
	 * @param Array of string arguments. Parameter not currently used
	 * 
	 * */
	
	public static void main(String[] args) {
		
		System.out.println("Starting point");
		Controller ctrl = new Controller();
		ctrl.start();
	}
	
	public static int getX() { return xloc; }
	public static int getY() { return yloc; }
	
}