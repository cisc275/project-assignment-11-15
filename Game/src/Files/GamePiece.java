package Files;

import java.io.Serializable;

public class GamePiece implements Serializable {
	int x;
	int y;
	
	public GamePiece() {}
	
	public GamePiece(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
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