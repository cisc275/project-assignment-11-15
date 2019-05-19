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
	 * @author Amjed Hallak, Paul Jureidini, Amelia Abobo, Adheena Chacko
	 * 
	 * */
	@Override
	public void keyReleased(KeyEvent e) {
		if(Model.RKquiz) {
			if (e.getKeyCode() == Controller.UP) {
				if(Model.answered) {
					Model.changeGameMode(Controller.MENU);
				}
				//always wrong, answer never B
				Model.answered = true;
			}
			if (e.getKeyCode() == Controller.LEFT) {
				
				switch(Model.quizNum) {
				case(1): //correct
					Model.running = true;
					Model.deathToll = 0;
					Model.RKquiz = false;
					break;
				case(2): //incorrect
					Model.answered = true;
					break;
				case(3): //correct
					Model.running = true;
					Model.deathToll = 0;
					Model.RKquiz = false;
					break;
				}
			}
			if (e.getKeyCode() == Controller.RIGHT) {
				switch(Model.quizNum) {
				case(1): //incorrect
					Model.answered = true;
					break;
				case(2): //correct
					Model.running = true;
					Model.deathToll = 0;
					Model.RKquiz = false;
					break;
				case(3): //incorrect
					Model.answered = true;
					break;
				}
			}
		}
		if(Model.running) { 
			if (e.getKeyCode() == Controller.UP) {
				switch(View.gameMode) {
		    	case(Controller.MENU):
		    		break;
		    	case(Controller.CLAPPERRAIL0):
		    		Model.setMovedTutorial(true);
		    	case(Controller.CLAPPERRAIL1):
		    	case(Controller.CLAPPERRAIL2):
		    	case(Controller.CLAPPERRAIL3):
		    		Model.move(Direction.NORTH);
		    		break;
		    	case(Controller.REDKNOT0):
		    		Model.setMovedTutorial(true);
		    	case(Controller.REDKNOT):
		    		Model.move(Direction.NORTH);
		    		break;
		    	}
				
			}
			if (e.getKeyCode() == Controller.LEFT) {
				switch(View.gameMode) {
		    	case(Controller.MENU):
		    		Model.changeGameMode(Controller.CLAPPERRAIL0);
		    		break;
		    	case(Controller.CLAPPERRAIL0):
		    		Model.setMovedTutorial(true);	    	
		    	case(Controller.CLAPPERRAIL1):
		    	case(Controller.CLAPPERRAIL2):
		    	case(Controller.CLAPPERRAIL3):
					Model.move(Direction.WEST);
		    		break;
		    	case(Controller.REDKNOT0):
		    		Model.setMovedTutorial(true);
		    	case(Controller.REDKNOT):
		    		if(Model.answered) {
		    			Model.changeGameMode(Controller.MENU);
		    		}
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
		    		Model.setMovedTutorial(true);
		    	case(Controller.CLAPPERRAIL1):
		    	case(Controller.CLAPPERRAIL2):
		    	case(Controller.CLAPPERRAIL3):
					Model.move(Direction.EAST);
		    		break;
		    	case(Controller.REDKNOT0):
		    		Model.setMovedTutorial(true);
		    	case(Controller.REDKNOT):
		    		Model.enableTut = false;
		    		System.out.println("hi");
		    		break;
		    	}
			}
			if (e.getKeyCode() == Controller.DOWN) {
				switch(View.gameMode) {
		    	case(Controller.MENU):
		    		break;
		    	case(Controller.CLAPPERRAIL0):
		    		Model.setMovedTutorial(true);
		    	case(Controller.CLAPPERRAIL1):
		    	case(Controller.CLAPPERRAIL2):
		    	case(Controller.CLAPPERRAIL3):
		    	case(Controller.REDKNOT0):
		    		Model.setMovedTutorial(true);
		    	case(Controller.REDKNOT):
		    		Model.move(Direction.SOUTH);
		    		break;
		    	}
			}
			
			if (e.getKeyChar() == 'k' || e.getKeyChar() == 'K') {
				Model.changeGameMode(Controller.MENU);
			}
		} else { 
			if (e.getKeyCode() == Controller.RIGHT) {
				switch(View.gameMode) {
		    	case(Controller.REDKNOT):
		    		Model.enableTut = false;
		    		System.out.println("hi");
		    		break;
		    	}
			}
			
		}
		if (e.getKeyChar() == 'p' || e.getKeyChar() == 'P') {
			Model.running = !Model.running;
		}
		if (e.getKeyChar() == 'd' || e.getKeyChar() == 'd') {
			Model.deathToll++;
			if(Model.quizNum < 4) {	Model.quizNum++; } else { Model.quizNum = 1; }
			
			//Model.RKquiz = true;
			//Model.running = false;
		}
	}
}