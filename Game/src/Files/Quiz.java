package Files;

import java.util.Scanner;

import Files.Question;

public class Quiz {
	//public static void main(String [] args) {
	public static void quizQuestions() {
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
		
	//}// end main
	
	public static void takeTest(Question [] questions) {
		int score = 0;
		
		Scanner keyboardInput = new Scanner(System.in);
		
		for(int i=0; i<questions.length; i++) {
			System.out.println(questions[i].prompt);
			String answer = keyboardInput.nextLine();
			if(answer.equals(questions[i].answer)) {
				score++;
			}
		}
		System.out.println("You got " + score + "/" + questions.length);
	}

}

