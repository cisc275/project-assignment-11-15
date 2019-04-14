package Files;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
	
	public void buildFrame() {
		JFrame frame = new JFrame();
        frame.getContentPane().add(this);
        this.setBackground(Color.GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // frame.setSize(frameWidth, frameHeight);
        frame.setSize(80,60);
       // btn.setBounds(btn_x, btn_y, btn_width, btn_height);
       // frame.getContentPane().add(btn,BorderLayout.SOUTH);
       // btn.addActionListener(new ButtonListener());
       //.setFocusable(false);
        this.addKeyListener(new KeyPress());
        this.setFocusable(true);
        frame.setVisible(true);
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
	
}
