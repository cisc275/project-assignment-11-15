package Files;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Controller{
	private SideScrollModel SSmodel;
	private View view;
	public static Boolean moving;
	public static Boolean running = true;
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
		SSmodel = new SideScrollModel();//(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
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
		System.out.println("starting");
		while(running) {
			if(running) {
				//increment the x and y coordinates, alter direction if necessary
				SSmodel.updateLocationAndDirection();
				//update the view
				view.update(TopViewModel.clapperRail.getX(), TopViewModel.clapperRail.getY(), Direction.UP);
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
<<<<<<< HEAD
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
=======
			SideScrollModel.move(Direction.UP);;
		}
		if (e.getKeyCode() == Controller.arrowDown) {
			SideScrollModel.move(Direction.DOWN);;
>>>>>>> RedKnot
		}
		
		if (e.getKeyChar() == 'k') {
			System.out.println("k pressed");
		}
	}
}
