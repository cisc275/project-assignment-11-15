package Files;

public class Question {
	String prompt;
	String answer;
	
	public Question(String prompt, String c) {
		this.prompt = prompt;
		this.answer = c;
	}
	
	public String getPrompt() {
		return prompt;
	}

	public String getAnswer() {
		return answer;
	}
}
