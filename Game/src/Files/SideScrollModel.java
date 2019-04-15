package Files;


public class SideScrollModel{
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
	static Animal redKnot;;
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
	 * */
	void updateLocationAndDirection() {
		System.out.println("Red Knot x: " + redKnot.getX());
		System.out.println("Red Knot y: " + redKnot.getY());
	}

	static void move(Direction dir) {
		System.out.println(dir);
		redKnot.move(dir);
	}

	public static void main(String[] args) {
		System.out.println("Starting point");
		redKnot = new RedKnot();
		Controller ctrl = new Controller();
		ctrl.start();
		
	}
}
