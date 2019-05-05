package Files;

public class Cloud {
	
	double speed;
	double x;
	double y;
	int type;
	
	Cloud(double x, double y, double speed, int type) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.type = type;
	}
	
	void move(int multiplier) {
		x -= .1*speed*multiplier;
		if(x < -200) {
			x = 800;
		}
	}
	
	public String getType() {
		switch(type) {
		case(1):
			return "cloud1";
		case(2):
			return "cloud2";
		}
		return null;
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
