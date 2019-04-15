package Files;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*; 

public class View extends JPanel{
	static int frameWidth = 600;
	static int frameHeight = 600;
	static int AnimalWidth;
	static int AnimalHeight;
	int count;
	int x;
	int y;
	static int frameCount;
	private static Direction dir;
    private int frameNum = 0;
    private static int xloc = 50;
    private static int yloc = 50;
    private JFrame frame;
	BufferedImage[][] pics;
	
	static int arrowLeft = 37;	//WEST
	static int arrowRight = 39;	//EAST
	static int arrowUp = 38;	//NORTH
	static int arrowDown = 40;	//SOUTH
	
	public static Direction getDirect(){
        return dir;
    }
 
	
	BufferedImage[] createImage() {
		return null;
		
	}
	
	public void update(int x, int y, Direction d) {
        this.xloc = x;
        this.yloc = y;
        this.dir = d;
        //frameNum = (frameNum + 1) % frameCount;
        //frame.repaint();
	}
	
	public int getWidth() { return this.frameWidth; }
    public int getHeight() { return this.frameHeight; }
    public int getImageWidth() { return this.AnimalWidth; }
    public int getImageHeight() { return this.AnimalHeight; }
	
	
	@SuppressWarnings("serial")
    private static void buildFrame() {
        JFrame frame = new JFrame();
        JTextField component = new JTextField();
	    component.addKeyListener(new KeyPress());

	    frame.add(component);
        frame.setBackground(Color.gray);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameWidth, frameHeight);
        frame.getContentPane().add(new JPanel() {
                @Override
                public void paint(Graphics g) {
                    g.toString();
                    //g.drawImage(pics.get(direction)[frameNum], xLoc, yLoc, Color.gray, this);
                    g.setColor(Color.blue);
            		//g.fillRect(xloc, yloc, 20, 20);
            		
            		
            		if (getDirect() == Direction.NORTH) {
            			System.out.println("up");
            			g.fillRect(xloc, yloc-10, 20, 20);
            			repaint();
            		}
            		if (getDirect() == Direction.SOUTH) {
            			System.out.println("down");
            			g.fillRect(xloc, yloc+10, 20, 20);
            			repaint();
            		}
            		
					
					

                }
            });
        //button listener code
        frame.addKeyListener(new KeyPress());
        frame.setFocusable(true);
        frame.setVisible(true);
    }
	
	
	public static void main(String[] args) {
		
		buildFrame();
		
		System.out.println("Starting point");
		Controller ctrl = new Controller();
		ctrl.start();
		
		
		
    }
	
}
