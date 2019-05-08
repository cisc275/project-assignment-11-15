package Files;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Files.Question;

public class Quiz {
	
	static String qPrompt;
	static ArrayList<Question> questions;
	
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
		
	
	public static boolean takeTest(ArrayList<Question> questions2) {
		int score = 0;
		
		Scanner keyboardInput = new Scanner(System.in);
		
		int max = questions2.size() -1; 
		int min = 0;
		Random rand = new Random();
		int randQuestion = rand.nextInt((max - min) + 1) + min;
		
		qPrompt = questions2.get(randQuestion).getPrompt();
		
		System.out.println(questions2.get(randQuestion).getPrompt());
		String answer = keyboardInput.nextLine();
		if(answer.equals(questions2.get(randQuestion).getPrompt())) {
			System.out.println("CORRECT!");
			return true;
		}else {
			System.out.println("WRONG!");
			return false;
		}
	}

}

