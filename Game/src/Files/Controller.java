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
	static int UP = 38;	    //NORTH
	static int DOWN = 40;	//SOUTH
	
	static final int MENU = 0;
	static final int CLAPPERRAIL1 = 1;
	static final int CLAPPERRAIL2 = 2;
	static final int CLAPPERRAIL3 = 3;
	static final int REDKNOT = 4;
	static final int WINNER = 5;
	static final int LOSER = 6;
	static final int REDKNOTCTN = 7;
	
	
	
	/**
	 * Initializes all the classes
	 *
	 * @author Amjed Hallak
	 * 
	 * */
	
	private Model model;
	private View view;
	private Quiz quiz;
	
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
	
	public static String keyAnswer; 
	private Quiz quiz;
	public static int kFlag = 0;
	
	
	
	@Override
	public void keyTyped(KeyEvent e) {
	}
	@Override
	public void keyPressed(KeyEvent e) {
		
	}
	
	
	/**
	 * Starts the game looping and effectively powers the model and view.
	 *
	 * @author Amjed Hallak, Paul Jureidini
	 * 
	 * */
	@Override
	public void keyReleased(KeyEvent e) {
		
		if (e.getKeyCode() == Controller.UP) {
			switch(View.gameMode) {
	    	case(Controller.MENU):
	    		break;
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
	    		//quiz.userAnswer = null;
	    		Model.move(Direction.NORTH);
	    		break;
	    	case(Controller.LOSER): //multiple choice B
	    		//quiz.quiz();
	    		System.out.println("multiple choice B");
	    		keyAnswer = "b";
	    		
	    		quiz.setQuizFlag(true);
	    		//quiz.userAnswer = keyAnswer;
	    		break;
	    	}
			
		}
		if (e.getKeyCode() == Controller.LEFT) {
			switch(View.gameMode) {
	    	case(Controller.MENU):
	    		Model.changeGameMode(Controller.CLAPPERRAIL1);
	    		break;
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
	    		Model.changeGameMode(Controller.MENU);
	    		break;
	    	case(Controller.LOSER): //multiple choice A
	    		System.out.println("multiple choice A");
	    		keyAnswer = "a";
	    		quiz.setQuizFlag(true);
	    		//quiz.userAnswer = keyAnswer;
	    		break;
	    	}
		}
		if (e.getKeyCode() == Controller.RIGHT) {
			switch(View.gameMode) {
	    	case(Controller.MENU):
	    		Model.changeGameMode(Controller.REDKNOT);
	    		break;
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
	    	case(Controller.LOSER): //multiple choice C
	    		System.out.println("multiple choice C");
	    		keyAnswer = "c";
	    		//quiz.userAnswer = keyAnswer;
	    		break;
	    	}
		}
		if (e.getKeyCode() == Controller.DOWN) {
			switch(View.gameMode) {
	    	case(Controller.MENU):
	    		break;
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
	    		//quiz.userAnswer = null;
	    		Model.move(Direction.SOUTH);
	    		break;
	    	case(Controller.LOSER):
	    		Model.changeGameMode(Controller.MENU);
	    		break;
	    	}
		}
		
		if (e.getKeyChar() == 'k' || e.getKeyChar() == 'K') {
			Model.changeGameMode(Controller.MENU);
		}
		
		
		
	}
//	while (e.getKeyCode() != Controller.LEFT ||e.getKeyCode() != Controller.UP || e.getKeyCode() != Controller.RIGHT) {
//	
//}
	public static String getQuizAnswer() {

		return keyAnswer;
	}
	
	
	public static String answerA() {return "a";}
	public static String answerB() {return "b";}
	public static String answerC() {return "c";}
}//end key press class

