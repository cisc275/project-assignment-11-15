package Files;

public class Animal extends GamePiece{
	Direction dir;
	boolean life;
	
	public Animal() {}
	public Animal(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
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
			this.y -= 50;
		} else if (dir == Direction.SOUTH) {
			this.y += 50;
		} else if (dir == Direction.EAST) {
			this.x += 50;
		} else if (dir == Direction.WEST) {
			this.x -= 50;
		}
		 
	}

	
}