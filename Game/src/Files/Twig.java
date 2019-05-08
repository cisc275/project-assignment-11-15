package Files;

public class Twig extends GamePiece{
	
	int angle;
	final int MAX_ANGLE = 360;
	
	Twig(int x, int y, int angle) {
		this.x = x;
		this.y = y;
		this.angle = angle;
	}
	
	void rotate() {
		if(angle == MAX_ANGLE) {
			angle = 0;
		} else {
			angle++;
		}
	}
	@Override
	public String toString() {
		return "Twig";
	}
}