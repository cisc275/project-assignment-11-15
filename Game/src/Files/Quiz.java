package Files;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Files.Question;

public class Quiz {
	
	static String qPrompt;
	static String qAnswer;
	static String userAnswer;
	static Question randQuestion;
	static ArrayList<Question> questions;
	
	private static KeyPress keypress;
	
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
		//System.out.println();
		return qPrompt;
	}
	
	public static String getAnswer() {
		return qAnswer;
	}
	
	public static void quiz() {
		takeTest(questions);
	}
		
	
    /**
	 * Allows you to answer a quiz question
	 *
	 * @author Paul Jureidini
	 * @param ArrayList<Questions>
	 * 
	 * */
	public static void takeTest(ArrayList<Question> questions) {
		int score = 0;
		userAnswer = null;
		
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
		 
		
		//System.out.println(answer);
//		if(userAnswer != null) {
//			userAnswer = null;
//			while(userAnswer == null) {
//				userAnswer = keypress.getKeyAnswer(); //WHY WONT userAnswer GO TO NULL AFTER SECOND QUIZ QUESTION
//			}
//		}
		
		while(userAnswer == null) {
			userAnswer = keypress.getKeyAnswer();
		}
		
		
		if(qAnswer.equals(userAnswer)) {
			System.out.println("CORRECT!");
			userAnswer = null;
			System.out.println(userAnswer); 
			System.out.println(qAnswer);
			
			Model.changeGameMode(Controller.REDKNOT); //ctn the red knot game
			
			//return true;
		}else {
			System.out.println("WRONG!");
			userAnswer = null;
			System.out.println(userAnswer); 
			System.out.println(qAnswer);
			
			Model.changeGameMode(Controller.MENU);
			
			//return false;
		}
		
		
		
	}
	
	

}// end Quiz class

