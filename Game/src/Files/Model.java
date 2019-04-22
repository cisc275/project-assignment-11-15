package Files;

import java.util.ArrayList;
import java.util.*;

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
    static String twigString = "Twig";
    static String bushString = "Bush";
    
    static Animal clapperRail;
    static Animal redKnot;
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
	static final int GRAVEYARD = 1000;
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
	 * Changes the game and resets the scene. 0 = Main menu, 1 = Clapper rail, 2 = Red knot
	 *
	 * @author Amjed Hallak, Paul Jureidini
	 * @param Game mode to change to. Either 0, 1, or 2
	 * 
	 * */
    public static void changeGameMode(int gameMode) {
    	switch(gameMode) {
    	case(MENU):
    		gameMode = MENU;
    		removeAllObjects();
    		clapperRail = null;
    		redKnot = null;
    		View.gameMode = MENU;
    		break;
    	case(CLAPPERRAIL):
    		twigCount = 0;
    		bushCount = 0;
    		deathToll = 0;
    		playerHealth = 100;
    		setUpClapperRailGame();
    		View.gameMode = CLAPPERRAIL;
    		break;
    	case(REDKNOT):
    		View.gameMode = REDKNOT;
    		setUpRedKnotGame();
    		break;
    	}
    }
    
    /**
	 * Sets up general objects for the Clapper Rail game. Currently spawns objects
	 *
	 * @author Amjed Hallak, Paul Jureidini
	 * 
	 * */
    public static void setUpClapperRailGame() {
    	clapperRail = new ClapperRail();
		gameMode = CLAPPERRAIL;
    	spawnObject(predStr, 100,100);
    	spawnObject(twigString, 300,500);
    	spawnObject(predStr, 500, 300);
    	spawnObject(bushString, 500,500);
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
	 * Sets up general testbed for the Red Knot game. Currently spawns objects
	 *
	 * @author Amjed Hallak
	 * 
	 * */
    public static void setUpRedKnotGame() {
    	redKnot = new RedKnot();
		gameMode = REDKNOT;
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
	 * @author Amjed Hallak, Paul Jureidini
	 * @param Type of object, whether it's a predator or a gamepiece
	 * @param x and y coordinates of the object being spawned
	 * 
	 * */
    static public void spawnObject(String type, int x, int y) {
    	switch(type) {
    	case("Predator"):
    		predators.add(new Animal(x,y));
    		break;
    	case("Twig"):
    		gamePieces.add(new Twig(x,y));
    		break;
	    case("Bush"):
			gamePieces.add(new Bush(x,y));
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
	    		if(o.toString().equals("Twig")) {
	    			o.x = GRAVEYARD;
	    			twigCount++;
	    		}
	    		if(o.toString().equals("Animal")) {
	    			playerHealth--;
	    			deathToll++;
	    		}
	    		if(o.toString().equals("Bush")) {
	    			bushCount++;
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
    	if(clapperRail != null) { //if game is active
	    	clapperRail.move(dir);
			chkCollision(clapperRail);
    	} else if (redKnot != null) {
			redKnot.move(dir);
			chkCollision(redKnot);
    	}
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
			movePredators();
		} else if (redKnot != null) {
			xloc = redKnot.getX();
			yloc = redKnot.getY();
			updateClock();
			movePredators();
		}
	}
	
    /**
	 * Method to create random movement of the predators in the games
	 *
	 * @author Amjed Hallak
	 * 
	 * */
	void movePredators() {
		if (gameMode == CLAPPERRAIL) {
			if(movePredators) {
				for(Animal p: predators) {
					int random = (int)(Math.random() * RANDMAX + RANDMIN);
						switch(random) {
						case(1):
							if(p.getX() < (View.FRAMEWIDTH - Animal.INCR)) {
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
							if(p.getY() < (View.FRAMEHEIGHT - Animal.INCR)) {
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
		} else if (gameMode == REDKNOT) {
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