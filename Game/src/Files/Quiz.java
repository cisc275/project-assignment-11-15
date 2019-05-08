package Files;

import java.util.Random;
import java.util.Scanner;

import Files.Question;

public class Quiz {
	
	static String qPrompt;
	static Question [] questions;
	
	public static String getPrompt() {
		//quiz();
		return questions[1].prompt;
	}
	
	public static void quiz() {
		String q1 = "What kind of animal is a Clapper Rail?\n"
				+"(a)bird\n(b)fox\n(c)fish\n";
		
		String q2 = "What does the Clapper Rail use to build its nest?\n"
				+"(a)twigs\n(b)water\n(c)worms\n";
		
		String q3 = "What animal does the Clapper Rail have to avoid?\n"
				+"(a)fish\n(b)fox\n(c)leaves\n";
		
		
		Question [] questions = {
				new Question(q1, "a"),
				new Question(q2, "a"),
				new Question(q3, "b")
		};
		takeTest(questions);
	}
		
	
	public static boolean takeTest(Question [] questions) {
		int score = 0;
		
		Scanner keyboardInput = new Scanner(System.in);
		
		int max = questions.length -1; 
		int min = 0;
		Random rand = new Random();
		int randQuestion = rand.nextInt((max - min) + 1) + min;
		
		qPrompt = questions[randQuestion].prompt;
		
		System.out.println(questions[randQuestion].prompt);
		String answer = keyboardInput.nextLine();
		if(answer.equals(questions[randQuestion].answer)) {
			System.out.println("CORRECT!");
			return true;
		}else {
			System.out.println("WRONG!");
			return false;
		}
	}

}

