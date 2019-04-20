package Files;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;


public class Controller{
	
	public static Boolean moving;
	public static Boolean running = true;
	static int LEFT = 37;	//WEST
	static int RIGHT = 39;	//EAST
	static int UP = 38;	//NORTH
	static int DOWN = 40;	//SOUTH
	
	static final int MENU = 0;
	static final int CLAPPERRAIL = 1;
	static final int REDKNOT = 2;
	
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
		model = new Model(view.getWidth(), view.getHeight());
	}
	
	/**
	 * Starts the game looping and effectively powers the model and view.
	 *
	 * @author Amjed Hallak
	 * 
	 * */
	public void start(){
		while(running) {
			if(running) {
				//increment the x and y coordinates, alter direction if necessary
				model.updateLocationAndDirection();
				
				//update the view
				view.update();
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
	/**
	 * Starts the game looping and effectively powers the model and view.
	 *
	 * @author Amjed Hallak
	 * 
	 * */
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == Controller.UP) {
			switch(View.gameMode) {
	    	case(Controller.MENU):
	    		break;
	    	case(Controller.CLAPPERRAIL):
	    		Model.move(Direction.NORTH);
	    		break;
	    	case(Controller.REDKNOT):
	    		break;
	    	}
			
		}
		if (e.getKeyCode() == Controller.LEFT) {
			switch(View.gameMode) {
	    	case(Controller.MENU):
	    		Model.changeGameMode(Controller.CLAPPERRAIL);
	    		break;
	    	case(Controller.CLAPPERRAIL):
				Model.move(Direction.WEST);
	    		break;
	    	case(Controller.REDKNOT):
	    		break;
	    	}
		}
		if (e.getKeyCode() == Controller.RIGHT) {
			switch(View.gameMode) {
	    	case(Controller.MENU):
	    		Model.changeGameMode(Controller.REDKNOT);
	    		break;
	    	case(Controller.CLAPPERRAIL):
				Model.move(Direction.EAST);
	    		break;
	    	case(Controller.REDKNOT):
	    		break;
	    	}
		}
		if (e.getKeyCode() == Controller.DOWN) {
			switch(View.gameMode) {
	    	case(Controller.MENU):
	    		break;
	    	case(Controller.CLAPPERRAIL):
				Model.move(Direction.SOUTH);
	    		break;
	    	case(Controller.REDKNOT):
	    		break;
	    	}
		}
		
		if (e.getKeyChar() == 'k') {
			Model.changeGameMode(Controller.MENU);
			//System.out.println("k pressed");
		}
	}
}