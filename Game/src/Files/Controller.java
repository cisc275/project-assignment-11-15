package Files;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Controller{
	private TopViewModel TVmodel;
	private View view;
	public static Boolean moving;
	public static Boolean running = true;
	static int arrowLeft = 37;
	static int arrowRight = 39;
	static int arrowUp = 38;
	static int arrowDown = 40;
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
	public Controller() {
		view = new View();
		TVmodel = new TopViewModel();//(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
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
				TVmodel.updateLocationAndDirection();
				//update the view
				//view.update(model.getX(), model.getY(), model.getDirect());
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

	public void keyTyped(KeyEvent e) {
	}
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == Controller.arrowUp) {
			TopViewModel.move(Direction.UP);
		}
		if (e.getKeyCode() == Controller.arrowLeft) {
			TopViewModel.move(Direction.LEFT);
		}
		if (e.getKeyCode() == Controller.arrowRight) {
			TopViewModel.move(Direction.RIGHT);
		}
		if (e.getKeyCode() == Controller.arrowDown) {
			TopViewModel.move(Direction.DOWN);
		}
		
		if (e.getKeyChar() == 'k') {
			System.out.println("k pressed");
		}
	}
}