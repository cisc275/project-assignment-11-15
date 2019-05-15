package Files;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Files.Question;

public class Quiz {
	
	static String qPrompt;
	static Question randQuestion;
	static ArrayList<Question> questions;
	
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
		//System.out.println(questions.get(1).getPrompt());
		return questions.get(1).getPrompt();
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
	public static boolean takeTest(ArrayList<Question> questions) {
		int score = 0;
		
		Scanner keyboardInput = new Scanner(System.in);
		
		int max = questions.size() -1; 
		int min = 0;
		Random rand = new Random();
		int randNum = rand.nextInt((max - min) + 1) + min;
		
		randQuestion = questions.get(randNum);  //gets a random question
		qPrompt = randQuestion.getPrompt();		//gets the prompt for the random question
		
		System.out.println(questions.get(randNum).getPrompt());
		String answer = keyboardInput.nextLine();
		if(answer.equals(randQuestion.answer)) {
			System.out.println("CORRECT!");
			return true;
		}else {
			System.out.println("WRONG!");
			return false;
		}
	}

}

