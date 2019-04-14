package Files;


public class TopViewModel{
	int x;
	int y;
	int xchange;
	int ychange;
	static int motionPicNum;
	static int actionPicNum;
	final int xincr = 10;
	final int yincr = 10;
	int AnimalWidth;
	int AnimalHeight;
	int frameWidth;
	int frameHeight;
	String action;
	static Animal clapperRail;
	Direction dir;
	

	
	/**
	 * Updates the location and direction of objects and characters. Will add more methods as needed that will be called by this one.
	 *
	 * @author
	 * @param
	 * @param
	 * @param   
	 * @return 
	 * 
	 * */;
	void updateLocationAndDirection() {
		//System.out.println("x: " + clapperRail.getX());
		//System.out.println("y: " + clapperRail.getY());
	}
	
	static void move(Direction dir) {
		System.out.println(dir);
		clapperRail.move(dir);
	}
	
	public static void main(String[] args) {
		System.out.println("Starting point");
		Controller ctrl = new Controller();
		ctrl.start();
		
		clapperRail = new ClapperRail();
	}
}

