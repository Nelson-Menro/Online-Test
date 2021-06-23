package onlineTest;

import java.util.Arrays;


public class MultipleChoiceQuestion extends Questions<String[]> {

	private static final long serialVersionUID = 1L;
	public MultipleChoiceQuestion(int questionNumber, String text, double points, String[] correctAnswer){
		this.questionNumber = questionNumber;
		this.text = text;
		this.points = points;
		this.correctAnswer = correctAnswer;
	}
	@Override
	public String getQuestionString() {
		//sort array
		Arrays.sort(correctAnswer);
		return "Question Text: " + text + "\n" + "Points: " + points + "\n" + "Correct Answer: " + Arrays.toString(correctAnswer) + "\n";
	}
	

}
