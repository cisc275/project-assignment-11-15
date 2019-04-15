package Files;

public class Animal{
	Direction dir;
	boolean life;
	int x;
	int y;
	
	/**
	 * Moves the animals position ***CHANGE TO SWITCH STATEMENT*****
	 *
	 * @author Amjed Hallak
	 * @param
	 * @param
	 * @param   
	 * @return 
	 * 
	 * */
	void move(Direction dir){
		if(dir == Direction.NORTH) {
			this.y += 5;
		} else if (dir == Direction.SOUTH) {
			this.y -= 5;
		} else if (dir == Direction.EAST) {
			this.x += 5;
		} else if (dir == Direction.WEST) {
			this.x -= 5;
		}
		 
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	
}