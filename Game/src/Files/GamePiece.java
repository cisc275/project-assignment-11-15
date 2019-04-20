package Files;

public class GamePiece{
	int x;
	int y;
	
	public GamePiece() {}
	
	public GamePiece(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return "GamePiece";
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
}