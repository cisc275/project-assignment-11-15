package Files;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Files.Question;

public class Quiz {
	
	static String qPrompt;
	static String qAnswer;
	static String userAnswer = "d";
	static Question randQuestion;
	static ArrayList<Question> questions;
	
	private volatile static boolean quizFlag = false;

	
	private static KeyPress keypress;
	private static View view;
	private static Controller controller;
	
	static final int MENU = 0;
	static final int CLAPPERRAIL1 = 1;
	static final int CLAPPERRAIL2 = 2;
	static final int CLAPPERRAIL3 = 3;
	static final int REDKNOT = 4;
	static final int WINNER = 5;
	static final int LOSER = 6;
	static final int REDKNOTCTN = 7;
	
	/**
	 * Creates an array of quiz questions 
	 *
	 * @author Paul Jureidini
	 * 
	 * */
	Quiz() {
		questions = new ArrayList<Question>();
		String q1 = "What kind of animal is a Clapper Rail?\n"
				+"(a)bird\n(b)fox\n(c)fish\n";
		
		String q2 = "What does the Clapper Rail use to build its nest?\n"
				+"(a)twigs\n(b)water\n(c)worms\n";
		
		String q3 = "What animal does the Clapper Rail have to avoid?\n"
				+"(a)fish\n(b)fox\n(c)leaves\n";
		Question qu1 = new Question(q1, "a");
		Question qu2 = new Question(q2, "a");
		Question qu3 = new Question(q3, "b");
		
		questions.add(qu1);
		questions.add(qu2);
		questions.add(qu3);
	}
	
	public static String getPrompt() {
			//quiz();
			return qPrompt;	
	}
	
	public static String getAnswer() {
		return qAnswer;
	}
	
	public static void quiz() throws InterruptedException {
		takeTest(questions);
	}
	
	
    /**
	 * Allows you to answer a quiz question
	 *
	 * @author Paul Jureidini
	 * @param ArrayList<Questions>
     * @throws InterruptedException 
	 * 
	 * */
	public static void takeTest(ArrayList<Question> questions) throws InterruptedException {
		
		Scanner keyboardInput = new Scanner(System.in);
		
		int max = questions.size() -1; 
		int min = 0;
		Random rand = new Random();
		int randNum = rand.nextInt((max - min) + 1) + min;
		
		randQuestion = questions.get(randNum);  //gets a random question
		qPrompt = randQuestion.prompt;		//gets the prompt for the random question
		qAnswer = randQuestion.answer;
		
		System.out.println(qPrompt);

		
		//String answer = keyboardInput.nextLine();
		//KeyEvent e = null;
//		Model.changeGameMode(LOSER);
		
//		while(isQuizFlag() == false)
//		{
//		
//		  Thread.sleep(1000);
//		  keypress.keyReleased(null);
//		  System.out.println("in quizFlag");
//		}
		
		userAnswer = keypress.getQuizAnswer();

		
		if(qAnswer == userAnswer) {
			System.out.println("CORRECT!");
			System.out.println(userAnswer); 
			System.out.println(qAnswer);
			
		}else {
			
			System.out.println("WRONG!");
			System.out.println(userAnswer); 
			System.out.println(qAnswer);
			
			
		}	
	}

	public static boolean isQuizFlag() {
		return quizFlag;
	}

	public static void setQuizFlag(boolean quizFlag) {
		Quiz.quizFlag = quizFlag;
	}
	

	
}// end Quiz class

