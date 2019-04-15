package Files;

import java.awt.event.KeyEvent;

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
    
    public int getX(){
        return xloc;
    }
    
    public int getY(){
        return yloc;
    }
    
    public Direction getDirect(){
        return d;
    }
    
    public static void move(Direction dir) {
    	clapperRail.move(dir);
    	System.out.println(clapperRail.getX());
    	System.out.println(clapperRail.getY());
    	
    }
    
    private Direction getDirection(int xDir, int yDir){
        if ( xDir < 0 ){
            if ( yDir < 0 ){
                d = Direction.NORTHWEST;
            }else if (yDir > 0){
                d = Direction.SOUTHWEST;
            }else if (yDir == 0){
                d = Direction.WEST;
            }
        }else if (xDir > 0){
            if(yDir > 0){
                d = Direction.SOUTHEAST;
            }else if (yDir < 0){
                d = Direction.NORTHEAST;
            }else if(yDir == 0){
                d = Direction.EAST;
            }
        } else {
            if(yDir > 0){
                d = Direction.SOUTH;
            }else if (yDir < 0){
                d = Direction.NORTH;
            }
        }
        return d;
    }
    
    
	void updateLocationAndDirection() {
		checkBoundry();
		
//		KeyPress kp = new KeyPress();
//		KeyEvent keyE = null;
//		kp.keyReleased(keyE);
//		System.out.println(keyE.getKeyCode());
		//System.out.println(getDirect());

        int xVel = goingRight ? xIncr : -xIncr;
        int yVel = goingDown ? yIncr : -yIncr;

        //getDirection(xVel, yVel);
        

        xloc += xVel;
        yloc += yVel;

        try {
            Thread.sleep(90);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
	}
	
	
	private void checkBoundry() {
        if( xloc < 0) {
            goingRight = true;
        }else if (xloc + IMAGEWIDTH > FRAMEWIDTH){
            goingRight = false;
        }
        if( yloc < 0) {
            goingDown = true;
        }else if (yloc + IMAGEHEIGHT > FRAMEHEIGHT) {
            goingDown = false;
        }
    }
	
	
}