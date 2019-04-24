package Files;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*; 

public class View extends JPanel{
	static int FRAMEWIDTH = 600;
	static int FRAMEHEIGHT = 600;
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
	BufferedImage[] pics;
	
	static int arrowLeft = 37;	//WEST
	static int arrowRight = 39;	//EAST
	static int arrowUp = 38;	//NORTH
	static int arrowDown = 40;	//SOUTH
	
	final static int numImages = 14;
	
	
	
	/*
	 * Loads images
	 * 
	 * @author - Amelia Abobo
	 * @param - none
	 * @return - none
	 */
	public void loadImages() {
		String[] arr = {"eagle", "clapperRail", "bush", "nest", 
				"sideScroll", "redKnot", "worm", "twig", "topView", 
				"falcon", "fox", "crabEgg", "healthChick", "healthX"};
		BufferedImage[] img = createImage(arr);
		pics = new BufferedImage[numImages];
		addImagesToArray(img);
	}
	
	public void addImagesToArray(BufferedImage[] img) {
		for(BufferedImage curImg:img) {
			for (int i=0; i<count; i++) {
				pics[count]=curImg;
			}
		}
	}

	public void buildFrame() {
		JFrame frame = new JFrame();
        frame.getContentPane().add(this);
        this.setBackground(Color.GRAY);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(FRAMEWIDTH, FRAMEHEIGHT);
        this.addKeyListener(new KeyPress());
        this.setFocusable(true);
        frame.setVisible(true);
	}
	
	public View() {
		loadImages();
		buildFrame();
	}
	
	private BufferedImage[] createImage(String[] strArr) {
		BufferedImage[] bufferedImage = new BufferedImage[strArr.length];
		String path = "src/Images/";
		int count = 0;
		for (String str : strArr) {
			try {
				bufferedImage[count] = ImageIO.read(new File(path.concat(str).concat(".png")));
				count++;
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		return bufferedImage;
	}
	
	public static Direction getDirect(){
        return dir;
    }

	public void update(int x, int y, Direction d) {
        View.xloc = x;
        View.yloc = y;
        View.dir = d;
        //frameNum = (frameNum + 1) % frameCount;
        repaint();
	}
	

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLUE);
		g.drawRect(Model.clapperRail.getX(), Model.clapperRail.getY(), 50, 50); //getX(), getY()
		g.setColor(Color.RED);
		g.fillRect(Model.getPredatorPositionX(0), Model.getPredatorPositionY(0), 50, 50); //getX(), getY()
	}
	
	@Override
	public int getWidth() { return View.FRAMEWIDTH; }
    @Override
	public int getHeight() { return View.FRAMEHEIGHT; }
    public int getImageWidth() { return View.AnimalWidth; }
    public int getImageHeight() { return View.AnimalHeight; }

	
}//end class View
