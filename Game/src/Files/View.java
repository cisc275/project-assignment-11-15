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
	static int AnimalWidth = 20;
	static int AnimalHeight = 20;
	int count;
	int x;
	int y;
	static int frameCount = 1;
	BufferedImage[][] pics;
	int numImages = 1;
	Direction dir;
	
	public void loadImages() {
		String[] arrOfStr = {"clapperRailSq"};
        BufferedImage[] imgs = createImage(arrOfStr);
        pics = new BufferedImage[frameCount][numImages];

        addImagesToArray(imgs, frameCount);
	}
	
	public void addImagesToArray(BufferedImage[] img, int frameCount) {
		for(BufferedImage curImg : img) {
		//	for(int i = 0; i < 1; i++) {
               pics[1][1] = curImg;
          //  }
            count ++;
		}
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

		loadImages();
		buildFrame();
		
	}
	
    private BufferedImage[] createImage(String[] strArr){
        BufferedImage[] bufferedImage = new BufferedImage[strArr.length];
        String path = "src/images/";
        int count = 0;
        for (String str : strArr) {
            try {
                bufferedImage[count] = ImageIO.read(new File(path.concat(str).concat(".png")));
                
                count ++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bufferedImage;
    }
	
    public void update(int x, int y, Direction dir) {
		//System.out.println("view update");
		this.x = x;
		this.y = y;
		this.dir = dir;
		repaint();
		try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
	
	public void paint(Graphics g) {
		g.drawImage(pics[1][1], x, y, Color.GRAY, this);
	}
	
}
