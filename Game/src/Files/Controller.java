package Files;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Controller{
	
	public static Boolean moving;
	public static Boolean running = true;
	static int arrowLeft = 37;
	static int arrowRight = 39;
	static int arrowUp = 38;
	static int arrowDown = 40;
	
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
				//view.update(view.getX(), view.getY(), view.getDirect());
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
	/*
	public void keyTyped(KeyEvent e) {
	}
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == Controller.arrowUp) {
			System.out.println("up");
			
		}
		if (e.getKeyCode() == Controller.arrowLeft) {
			System.out.println("left");
		}
		if (e.getKeyCode() == Controller.arrowRight) {
			System.out.println("right");
		}
		if (e.getKeyCode() == Controller.arrowDown) {
			System.out.println("down");
		}
		
		if (e.getKeyChar() == 'k') {
			System.out.println("k pressed");
		}
	}*/
}

class ButtonListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		Controller.running = !Controller.running;
	}
}

class KeyPress implements KeyListener {

	public void keyTyped(KeyEvent e) {
	}
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == Controller.arrowUp) {
			System.out.println("up");
			
		}
		if (e.getKeyCode() == Controller.arrowLeft) {
			System.out.println("left");
		}
		if (e.getKeyCode() == Controller.arrowRight) {
			System.out.println("right");
		}
		if (e.getKeyCode() == Controller.arrowDown) {
			System.out.println("down");
		}
		
		if (e.getKeyChar() == 'k') {
			System.out.println("k pressed");
		}
	}
}