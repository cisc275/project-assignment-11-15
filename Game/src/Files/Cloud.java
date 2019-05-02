package Files;

public class Cloud {
	
	double speed;
	double x;
	double y;
	
	Cloud(double x, double y, double speed) {
		this.x = x;
		this.y = y;
		this.speed = speed;
	}
	
	void move() {
		x -= .1*speed;
		if(x < -200) {
			x = 800;
		}
	}
	
	public int getX() {
		return (int)x;
	}
	
	public int getY() {
		return (int)y;
	}
	
	@Override
	public String toString() {
		return "Cloud";
	}
}
