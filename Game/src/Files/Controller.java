package Files;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Controller{
	
	public static Boolean moving;
	public static Boolean running = true;
	static int LEFT = 37;	//WEST
	static int RIGHT = 39;	//EAST
	static int UP = 38;	//NORTH
	static int DOWN = 40;	//SOUTH
	
	static final int MENU = 0;
	static final int CLAPPERRAIL0 = 1; 
	static final int CLAPPERRAIL1 = 2;
	static final int CLAPPERRAIL2 = 3;
	static final int CLAPPERRAIL3 = 4;
	static final int REDKNOT0 = 5;
	static final int REDKNOT = 6;
	static final int WINNER = 7;
	static final int LOSER = 8;
	
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
		Model.changeGameMode(MENU);
		while(running) {
			if(running) {
				//increment the x and y coordinates, alter direction if necessary
				model.updateLocationAndDirection();
				
				//update the view
				view.update();
				try {
		            Thread.sleep(1);
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
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
	@Override
	public void actionPerformed(ActionEvent e) {
		Controller.running = !Controller.running;
	}
}

class KeyPress implements KeyListener {
	
	@Override
	public void keyTyped(KeyEvent e) {
	}
	@Override
	public void keyPressed(KeyEvent e) {
	}
	/**
	 * Starts the game looping and effectively powers the model and view.
	 *
	 * @author Amjed Hallak, Paul Jureidini, Amelia Abobo
	 * 
	 * */
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == Controller.UP) {
			switch(View.gameMode) {
	    	case(Controller.MENU):
	    		break;
	    	case(Controller.CLAPPERRAIL0):
	    		//tutorial
	    	case(Controller.CLAPPERRAIL1):
	    		Model.move(Direction.NORTH);
	    		break;
	    	case(Controller.CLAPPERRAIL2):
	    		Model.move(Direction.NORTH);
	    		break;
	    	case(Controller.CLAPPERRAIL3):
	    		Model.move(Direction.NORTH);
	    		break;
	    	case(Controller.REDKNOT):
	    		Model.move(Direction.NORTH);
	    		break;
	    	}
			
		}
		if (e.getKeyCode() == Controller.LEFT) {
			switch(View.gameMode) {
	    	case(Controller.MENU):
	    		Model.changeGameMode(Controller.CLAPPERRAIL1);
	    		break;
	    	case(Controller.CLAPPERRAIL0):
	    		//tutorial
	    	case(Controller.CLAPPERRAIL1):
				Model.move(Direction.WEST);
	    		break;
	    	case(Controller.CLAPPERRAIL2):
				Model.move(Direction.WEST);
	    		break;
	    	case(Controller.CLAPPERRAIL3):
				Model.move(Direction.WEST);
	    		break;
	    	case(Controller.REDKNOT):
	    		break;
	    	case(Controller.WINNER):
	    	case(Controller.LOSER):
	    		Model.changeGameMode(Controller.MENU);
	    	}
		}
		if (e.getKeyCode() == Controller.RIGHT) {
			switch(View.gameMode) {
	    	case(Controller.MENU):
	    		Model.changeGameMode(Controller.REDKNOT);
	    		break;
	    	case(Controller.CLAPPERRAIL0):
	    		//tutorial
	    	case(Controller.CLAPPERRAIL1):
				Model.move(Direction.EAST);
	    		break;
	    	case(Controller.CLAPPERRAIL2):
				Model.move(Direction.EAST);
	    		break;
	    	case(Controller.CLAPPERRAIL3):
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
	    	case(Controller.CLAPPERRAIL0):
	    		//tutorial
	    	case(Controller.CLAPPERRAIL1):
				Model.move(Direction.SOUTH);
	    		break;
	    	case(Controller.CLAPPERRAIL2):
				Model.move(Direction.SOUTH);
	    		break;
	    	case(Controller.CLAPPERRAIL3):
				Model.move(Direction.SOUTH);
	    		break;
	    	case(Controller.REDKNOT):
	    		Model.move(Direction.SOUTH);
	    		break;
	    	}
		}
		
		if (e.getKeyChar() == 'k' || e.getKeyChar() == 'K') {
			Model.changeGameMode(Controller.MENU);
		}
	}
}