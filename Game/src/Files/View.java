package Files;

import java.awt.Color;
import java.awt.Graphics;
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
	static int frameWidth;
	static int frameHeight;
	static int AnimalWidth;
	static int AnimalHeight;
	int count;
	int x;
	int y;
	static int frameCount;
	private Direction direction;;
    private int frameNum = 0;
    private int xLoc;
    private int yLoc;
    private JFrame frame;
	BufferedImage[][] pics;
	
	
	void addImagesToArray() {
		
	}
	
	public static void buildFrame() {
		JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(800,600);
        //frame.setBackground(Color.blue);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // frame.setSize(frameWidth, frameHeight);
        
       // btn.setBounds(btn_x, btn_y, btn_width, btn_height);
       // frame.getContentPane().add(btn,BorderLayout.SOUTH);
       // btn.addActionListener(new ButtonListener());
       //.setFocusable(false);
        //this.addKeyListener(new KeyPress());
        //this.setFocusable(true);
        
        
        ClapperRail square = new ClapperRail();
        frame.add(square);
        square.drawing();
        
     
	}
	
	
	
	public View() { 

	//	loadImages();
		buildFrame();
		
	}
	
	BufferedImage[] createImage() {
		return null;
		
	}
	
	public void update(int x, int y, Direction d) {
        this.xLoc = x;
        this.yLoc = y;
        this.direction = d;
        frameNum = (frameNum + 1) % frameCount;
        frame.repaint();
	}
	
	
	
	
	
	public static void main(String[] args) {
		buildFrame();
		
		
    }
	
}
