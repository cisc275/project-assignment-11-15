package Files;

public class Bush extends GamePiece{
	Bush(int x, int y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public String toString() { //used for coloring graphics in View
		return "Bush";
	}
}