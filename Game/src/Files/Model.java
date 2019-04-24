package Files;

import java.util.ArrayList;
import java.util.*;

public class Model{
	private final int FRAMEWIDTH;
    private final int FRAMEHEIGHT;

	static Boolean withPlayer = true;
	static Boolean withoutPlayer = false;
	static Boolean movePredators;
	static Boolean slideObjects;

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
	static int M_RANDMAX = 4;
	static int M_RANDMIN = 1;
	static int P_RANDMAX = 12;
	static int P_RANDMIN = 0;
   
	static int gameMode;
	static final int MENU = 0;
	static final int CLAPPERRAIL = 1;
	static final int REDKNOT = 2;
	
	
	static int clk1Count = 0;
	static int clk2Count = 0;
	static final int CLKMAX = 10000000;
	static final int CLK2MAX = CLKMAX/512;
	static final int GRAVEYARD = 1000;
	static final int SPAWN_X = 850;
	static final int LEVEL_END = 100001;
	static final int PREDATOR_SIZE = 50;
	static final int PREDATOR_SPACE = PREDATOR_SIZE*4;
	
	static int twigCount = 0;
	static int bushCount = 0;
	static int deathToll; //temp
	static int playerHealth = 100;
	static int twigMax = 2; //bird can only hold 2 twigs at a time
	static int bushMax = 4; 
	static int bushTrans = 50;
	static int flightTime = 0;
	
	static int[] yPoints = {0, 50, 100, 150, 200, 250, 300, 350, 400, 450, 500, 550, 600};
    
    private Direction dir = Direction.NORTH;
	

    public Model(int fw, int fh){
        this.FRAMEWIDTH = fw;
        this.FRAMEHEIGHT = fh;
    }
    
    /**
	 * getter for bushCount, used in View
	 *
	 * @author Paul Jureidini
	 * 
	 * */
    public static int getBushCount() {
    	return bushCount;
    }
    public static int getBushMax() {
    	return bushMax;
    }
    public static int getTwigCount() {
    	return twigCount;
    }
    public static int getBushTrans() {
    	return bushTrans;
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
    	spawnObject(twigString, 200,400);
    	spawnObject(twigString, 100,300);
    	spawnObject(twigString, 400,200);
    	spawnObject(twigString, 600,100);
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
    	redKnot.move(Direction.EAST);
    	redKnot.move(Direction.EAST);
		gameMode = REDKNOT;
    	spawnObject(predStr, SPAWN_X, 200);
    	spawnObject(predStr, SPAWN_X, 250);
    	spawnObject(predStr, SPAWN_X, 300);
    	spawnObject(predStr, SPAWN_X, 500);
    	spawnObject(predStr, SPAWN_X, 50);
    	
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
	 * Checks for collisions between an animal object, and all game objects onto he screen
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
	    			if(twigCount < twigMax) { //makes sure that there are not more than 2 twigs collected
	    				o.x = GRAVEYARD;
	    				twigCount++;
	    				System.out.println("Twig Collected"); 
	    			}
	    		}//end "Twig"
	    		if(o.toString().equals("Animal")) {
	    			playerHealth--;
	    			deathToll++;
	    		}
	    		if(o.toString().equals("Bush")) {
	    			if(twigCount>0 && bushCount<bushMax) {
	    				bushCount += twigCount;
	    				bushTrans += 50*twigCount; //increment bush transparency 
	    				System.out.println(bushTrans);
	    				twigCount = 0;
	    			}else if(twigCount>0 && bushCount == bushMax ) {
	    				System.out.println("Reached max bush size!");
	    			}
	    		}
	    	}
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
    
    /**
	 * Method to increment all objects toward the left side of the screen
	 * in the Red Knot game. Also generates new objects
	 *
	 * @author Amjed Hallak
	 * 
	 * */
    public static void slideObjectsLeft() {
    	ArrayList<GamePiece> objs = getAllObjects(withoutPlayer);
    	if(slideObjects) {
    		for(GamePiece p: objs) {
    			p.x -= 1;
    		}
    	}
    	//System.out.println(flightTime);
    	if((flightTime < LEVEL_END) && slideObjects) {
    		if((flightTime % PREDATOR_SPACE) == 0) {
    			int[] yCoords = getRandY();
    			int i = 0;
    			while(i < 5) {
    				spawnObject(predStr, SPAWN_X, yCoords[i]);
    				i++;
    			}
    		}
    	}
    }
    
    /**
	 * Generates a random y-coordinate for the Red knot game
	 *
	 * @author Amjed Hallak
	 * 
	 * */
    public static int[] getRandY() {
    	int i = 0;
    	int[] randPts = new int[P_RANDMAX];
    	while(i < 5) {
    		int random = (int)(Math.random() * P_RANDMAX + P_RANDMIN);
    		randPts[i] = yPoints[random];
    		i++;
    	}
    	return randPts;
    }
    
    public static void updateClock() {
    	clk1Count++;
    	clk2Count++;
    	if(clk1Count > CLKMAX) {
    		movePredators = true;
    		clk1Count = 0;
    	} else {
    		movePredators = false;
    	}
    	if(clk2Count > CLK2MAX) {
    		slideObjects = true;
    		clk2Count = 0;
    		flightTime++;
    	} else {
    		slideObjects = false;
    	}
    }
    
    /**
	 * Method for View class to call to check player x and y direction
	 *
	 * @author Amjed Hallak
	 * 
	 * */
	void updateLocationAndDirection() {
		clean();
		if(clapperRail != null) { //If game is active
			xloc = clapperRail.getX();
			yloc = clapperRail.getY();
			updateClock();
			movePredators();
		} else if (redKnot != null) {
			xloc = redKnot.getX();
			yloc = redKnot.getY();
			updateClock();
			slideObjectsLeft();
			movePredators();
		}
	}
    /**  DONE FROM EHRE
	 * Cleans out all objects that are no longer necessary.
	 * Effectively backup/manual garbage collection.
	 *
	 * @author Amjed Hallak
	 * 
	 * */
	public void clean() {
		//ArrayList<GamePiece> objs = getAllObjects(withoutPlayer);
    	
    	//for(GamePiece gp: gamePieces) {
    	//	if(gp.x == GRAVEYARD) {
    			//gamePieces.remove(gp);
    	//	}
    	//}
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
	 * Method to create random movement of the predators in the games
	 *
	 * @author Amjed Hallak
	 * 
	 * */
	void movePredators() {
		if (gameMode == CLAPPERRAIL) {
			if(movePredators) {
				for(Animal p: predators) {
					int random = (int)(Math.random() * M_RANDMAX + M_RANDMIN);
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