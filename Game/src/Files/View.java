package Files;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*; 

public class View extends JPanel{
	static int FRAMEWIDTH = 800;
	static int FRAMEHEIGHT = 600;
	int count;
	static int FRAMECOUNT;
	private static Direction dir;
    private int frameNum = 0;
    private JFrame frame;
	BufferedImage[][] pics;
	
	static int gameMode;
	static final int MENU = 0;
	static final int CLAPPERRAIL = 1;
	static final int REDKNOT = 2;
	
	int RANDMAX = 6;
	int RANDMIN = 0;
	
	int bigText = 40;
	
	Boolean withPlayer = true;
	Boolean withoutPlayer = false;
	
	
	View() {
		buildFrame();
	}

	
	public static Direction getDirect(){
        return dir;
    }
	
	BufferedImage[] createImage() {
		return null;
	}
	
	public void update() {
        //frameNum = (frameNum + 1) % frameCount;
        repaint();
	}
	
	public void buildFrame() {
		JFrame frame = new JFrame();
		frame.getContentPane().add(this);
        frame.getContentPane().setBackground(Color.GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAMEWIDTH, FRAMEHEIGHT);
        frame.addKeyListener(new KeyPress());
        frame.setFocusable(true);
        frame.setVisible(true);
        gameMode = MENU;
	}
	
	public Color getColor(String type) {
		switch(type) {
		case("Clapper Rail"):
			return Color.BLACK;
		case("Animal"):
			return Color.RED;
		case("GamePiece"):
			return Color.GREEN;
		case("alt1"):
			return Color.yellow;
		case("alt2"):
			return Color.pink;
		}
		return Color.white;
	}
	
	public void paintComponent(Graphics g) {
		if(gameMode == MENU) {
			/* Main menu Game View Logic
			 */
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, FRAMEWIDTH, 100);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Helvetica", Font.PLAIN, bigText)); 
			g.drawString("Fly away?", 325, 65);
			g.setFont(new Font("Helvetica", Font.PLAIN, 20)); 
			g.setColor(Color.BLACK);
			g.drawString("Press left for Clapper Rail game", 100, 400);
			g.drawString("Press right for Red Knot game", 500, 400);
			g.drawString("Press k at any time to return to menu", 300, 500);
		} else if (gameMode == CLAPPERRAIL) {
			/* Clapper Rail Game View Logic
			 */
			ArrayList<GamePiece> allObj = Model.getAllObjects(withPlayer);
			//Color color = randColor();
			super.paintComponent(g);
			for(GamePiece gp: allObj) {
				g.setColor(getColor(gp.toString()));
				g.fillRect(gp.getX(), gp.getY(), 50, 50);
			}
		} else if (gameMode == REDKNOT) {
			/* Red Knot Game View Logic
			 */
			g.drawString("REDKNOT GAME", 100, 100);
		}
	}
	
	public int getWidth() { return this.FRAMEWIDTH; }
    public int getHeight() { return this.FRAMEHEIGHT; }
	
	
}
