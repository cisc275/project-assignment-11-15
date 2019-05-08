package Files;

public class Question {
	String prompt;
	String answer;
	
	public Question(String prompt, String answer) {
		this.prompt = prompt;
		this.answer = answer;
	}
	
	public String getPrompt() {
		return prompt;
	}

	public String getAnswer() {
		return answer;
	}
}
