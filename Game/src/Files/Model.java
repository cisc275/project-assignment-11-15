package Files;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Model{
	private final int FRAMEWIDTH;
    private final int FRAMEHEIGHT;
    private final int IMAGEWIDTH;
    private final int IMAGEHEIGHT;

    private boolean goingRight = true;
    private boolean goingDown = true;

    private int xloc = 20;
    private int yloc = 20;
    private final int xIncr = 10;
    private final int yIncr = 10;
    
    static int arrowLeft = 37;	//WEST
	static int arrowRight = 39;	//EAST
	static int arrowUp = 38;	//NORTH
	static int arrowDown = 40;	//SOUTH

    static Animal clapperRail = new ClapperRail();
    static ArrayList<Animal> predators = new ArrayList<Animal>();
    
    public static int collisionCount = 0;
    
    private Direction d = Direction.NORTH;
	
	/**
	 * Updates the location and direction of objects and characters. Will add more methods as needed that will be called by this one.
	 *
	 * @author
	 * @param
	 * @param
	 * @param   
	 * @return 
	 * 
	 * */
    
    public Model(int fw, int fh, int iw, int ih){
        this.FRAMEWIDTH = fw;
        this.FRAMEHEIGHT = fh;
        this.IMAGEWIDTH = iw;
        this.IMAGEHEIGHT = ih;
    }
    
    public static void setUpClapperRailGame() {
    	spawnObject(100,100);
    	spawnObject(300,500);
    }
    
    public static ArrayList<Object> getAllObjects(){
    	ArrayList<Object> allObjects = new ArrayList<Object>();
    	allObjects.add(clapperRail);
    	for(Object c: predators) {
    		allObjects.add(c);
    	}
    	System.out.println(allObjects.size());
    	return allObjects;
    }
    
    public static int getPredatorPositionX(int num) {
    	return predators.get(num).getX();
    }
    public static int getPredatorPositionY(int num) {
    	return predators.get(num).getY();
    }
    
    public int getX(){
        return xloc;
    }
    
    public int getY(){
        return yloc;
    }
    
    public static int getCollisionCount() {
    	return collisionCount;
    }
    
    static public void spawnObject(int x, int y) {
    	predators.add(new Animal(x,y));
    	System.out.println("spawned pred");
    }
    
    public static void chkCollision(Animal b, ArrayList<Animal> preds) {
    	for(Animal p: preds) {
	    	if (b.getX() == p.getX() && b.getY() == p.getY()) {
	    		System.out.println("Collision");
	    		collisionCount++;
	    	}
    	}
    }
    
    public Direction getDirect(){
        return d;
    }
    
    public static void move(Direction dir) {
    	clapperRail.move(dir);
    	System.out.println("X: " + clapperRail.getX());
    	System.out.println("Y: " + clapperRail.getY());
    	chkCollision(clapperRail, predators);
    	
    }
    
	void updateLocationAndDirection() {
		xloc = clapperRail.getX();
		yloc = clapperRail.getY();
		
	}
	
	public static void main(String[] args) {
		
		System.out.println("Starting point");
		Controller ctrl = new Controller();
		setUpClapperRailGame();
		ctrl.start();
	}
	
	
}