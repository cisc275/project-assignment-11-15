package Files;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;


public class Controller{
	
	public static Boolean moving;
	public static Boolean running = true;
	static int arrowLeft = 37;	//WEST
	static int arrowRight = 39;	//EAST
	static int arrowUp = 38;	//NORTH
	static int arrowDown = 40;	//SOUTH
	
	final int MOVE = 10;
	
	
	/**
	 * Initializes all the classes
	 *
	 * @author Amjed Hallak
	 * @param
	 * @param
	 * @param   
	 * @return 
	 * 
	 * */
	
	private Model model;
	private View view;
	
	public Controller() {
		view = new View();
		model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
	}
	
	/**
	 * Starts the game looping and effectively powers the model and view.
	 *
	 * @author
	 * @param
	 * @param
	 * @param   
	 * @return 
	 * 
	 * */
	public void start(){
		while(running) {
			if(running) {
				//increment the x and y coordinates, alter direction if necessary
				model.updateLocationAndDirection();
				//update the view
				view.update(model.getX(), model.getY(), model.getDirect());
			} else {
				while(!running) {
					try {
			            Thread.sleep(1);
			        } catch (InterruptedException e) {
			            e.printStackTrace();
			        }
				}
			}
		}
		
		
	}
	
}

class ButtonListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		Controller.running = !Controller.running;
	}
}

class KeyPress implements KeyListener {

	public Direction d = Direction.NORTH;
	
	public void keyTyped(KeyEvent e) {
	}
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == Controller.arrowUp) {
			System.out.println("up");
			d = Direction.NORTH;
			System.out.println(d);
		}
		if (e.getKeyCode() == Controller.arrowLeft) {
			System.out.println("left");
			d = Direction.WEST;
			System.out.println(d);
		}
		if (e.getKeyCode() == Controller.arrowRight) {
			System.out.println("right");
			d = Direction.EAST;
			System.out.println(d);
		}
		if (e.getKeyCode() == Controller.arrowDown) {
			System.out.println("down");
			d = Direction.SOUTH;
			System.out.println(d);
		}
		
		if (e.getKeyChar() == 'k') {
			System.out.println("k pressed");
		}
	}
}