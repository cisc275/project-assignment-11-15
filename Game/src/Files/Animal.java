package Files;

public class Animal extends GamePiece{
	Direction dir;
	boolean life;
	int INCR = 50;
	
	public Animal() {}
	public Animal(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Moves the animals position based on the input direction
	 *
	 * @author Amjed Hallak
	 * @param Enumerated direction
	 * 
	 * */
	void move(Direction dir){
		switch(dir) {
		case NORTH:
			this.y -= INCR;
			break;
		case SOUTH:
			this.y += INCR;
			break;
		case EAST:
			this.x += INCR;
			break;
		case WEST:
			this.x -= INCR;
			
		}
	}

	
}