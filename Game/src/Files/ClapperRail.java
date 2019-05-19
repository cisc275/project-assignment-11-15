package Files;

public class ClapperRail extends Bird{
	boolean hasTwig;
	boolean isHiding;
	
	ClapperRail(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "Clapper Rail";
	}

	
}