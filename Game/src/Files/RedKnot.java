package Files;

public class RedKnot extends Bird{
	double health;
	boolean isFlying;
	boolean isWalking;
	
	/**	 
	 * Force position of RedKnot - for setting up the RedKnot game
	 *
	 * @author Amjed Hallak
	 * 
	 * */
	void setPosition() {
		this.setX(INCR*2);
	}
	
	@Override
	public String toString() {
		return "Red Knot";
	}
}