package onlineTest;

public class TrueFalseQuestion extends Questions<Boolean> {

	private static final long serialVersionUID = 1L;

	public TrueFalseQuestion(int questionNumber, String text, double points, boolean correctAnswer){
		this.questionNumber = questionNumber;
		this.text = text;
		this.points = points;
		this.correctAnswer = correctAnswer;
	}

	@Override
	public String getQuestionString() {
		return "Question Text: " + text + "\n" + "Points: " + points + "\n" + "Correct Answer: " + capatalize(String.valueOf(correctAnswer)) + "\n";
	}
	
	public static String capatalize(String str) {
		if(str.isBlank() || str == null) {
			return str;
		}else {
			return str.substring(0, 1).toUpperCase() + str.substring(1, str.length());
		}
	}
}
