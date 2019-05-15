package Files;

public class Animal extends GamePiece{
	Direction dir;
	boolean life;
	static int INCR = 50;


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
			if(this.y == 0) {
				break;
			} else {
				this.y -= INCR;
				break;
			}
		case SOUTH:
			if(this.y > (View.frameHeight - View.IMGHEIGHT*2)) {
				break;
			} else {
				this.y += INCR;
				break;
			}
		case EAST:
			if(this.x > (View.frameWidth - View.IMGWIDTH*2)) {
				break;
			} else {
				this.x += INCR;
				break;
			}
		case WEST:
			if(this.x == 0) {
				break;
			} else {
				this.x -= INCR;
				break;
			}
			
		}
	}

	public void setX(int x) {
		this.x = x;
	}
	
	@Override
	public String toString() {
		return "Animal";
	}
	
}