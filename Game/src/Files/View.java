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
	BufferedImage[][] pics;
	
	
	void addImagesToArray() {
		
	}
	
	public static void buildFrame() {
		JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // frame.setSize(frameWidth, frameHeight);
        
       // btn.setBounds(btn_x, btn_y, btn_width, btn_height);
       // frame.getContentPane().add(btn,BorderLayout.SOUTH);
       // btn.addActionListener(new ButtonListener());
       //.setFocusable(false);
        //this.addKeyListener(new KeyPress());
        //this.setFocusable(true);
        
        
        ClapperRail object = new ClapperRail();
        frame.add(object);
        object.drawing();
	}
	
	
	
	public View() { 

	//	loadImages();
		buildFrame();
		
	}
	
	BufferedImage[] createImage() {
		return null;
		
	}
	
	void update(int x, int y, Direction dir) {
		
	}
	
	public void paint(Graphics g) {
		
	}
	
	public static void main(String[] args) {
		buildFrame();
    }
	
}
