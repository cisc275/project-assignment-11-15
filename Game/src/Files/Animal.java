package Files;

public class Animal{
	Direction dir;
	int x = 0;
	int y = 0;
	boolean life;

	/**
	 * Moves the animals position
	 *
	 * @author
	 * @param
	 * @param
	 * @param   
	 * @return 
	 * 
	 * */
	void move(Direction dir){
		switch(dir){
			case UP:
				y+=5;
				break;
			case DOWN:
				y-=5;
				break;
		default:
			break;
		
		}
	}
	
	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}
}
