package Files;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Model{
	private final int FRAMEWIDTH;
    private final int FRAMEHEIGHT;

    private boolean goingRight = true;
    private boolean goingDown = true;

    private int xloc = 20;
    private int yloc = 20;
    private final int xIncr = 10;
    private final int yIncr = 10;
    
    static Animal clapperRail = new ClapperRail();
    static ArrayList<Animal> predators = new ArrayList<Animal>();
    
    public static int collisionCount = 0;
    
    private Direction d = Direction.NORTH;
	

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
    	spawnObject(100,100);
    	spawnObject(300,500);
    }
    
    /**
	 * Function to return a list of all the present objects on the screen
	 *
	 * @author Amjed Hallak
	 * @return ArrayList of all the birds, predators, and other objects on screen
	 * */
    public static ArrayList<GamePiece> getAllObjects(){
    	ArrayList<GamePiece> allObjects = new ArrayList<GamePiece>();
    	allObjects.add(clapperRail);
    	for(GamePiece p: predators) {
    		allObjects.add(p);
    	}
    	//System.out.println(allObjects.size());
    	return allObjects;
    }
    
    /*public static int getPredatorPositionX(int num) {
    	return predators.get(num).getX();
    }
    public static int getPredatorPositionY(int num) {
    	return predators.get(num).getY();
    }*/
    
    
    public static int getCollisionCount() {
    	return collisionCount;
    }
    
    /**
	 * Spawns a new object at a specified x and y position
	 *
	 * @author Amjed Hallak
	 * @param x and y coordinates of the object being spawned
	 * 
	 * */
    static public void spawnObject(int x, int y) {
    	predators.add(new Animal(x,y));
    	System.out.println("spawned pred");
    }
    
    /**
	 * Checks for collisions between an animal object, and all game objects ont he screen
	 *
	 * @author Amjed Hallak
	 * @param Animal being compared to for a grid collision
	 * 
	 * */
    public static void chkCollision(Animal b) {
    	ArrayList<GamePiece> objs = getAllObjects();
    	for(GamePiece o: objs) {
	    	if (b.getX() == o.getX() && b.getY() == o.getY()) {
	    		System.out.println("Collision");
	    		collisionCount++;
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
    	clapperRail.move(dir);
    	System.out.println("X: " + clapperRail.getX());
    	System.out.println("Y: " + clapperRail.getY());
    	chkCollision(clapperRail);
    	
    }
    
    /**
	 * Method for View class to call to check player x and y direction
	 *
	 * @author Unknown
	 * 
	 * */
	void updateLocationAndDirection() {
		xloc = clapperRail.getX();
		yloc = clapperRail.getY();
		
	}
	
	/**
	 * Main method
	 *
	 * @author Team 11-15
	 * @param Array of string arguments. Not currently used
	 * 
	 * */
	
	public static void main(String[] args) {
		
		System.out.println("Starting point");
		Controller ctrl = new Controller();
		setUpClapperRailGame();
		ctrl.start();
	}
	
	
}