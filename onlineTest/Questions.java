package onlineTest;

public abstract class Questions<T> implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	protected T correctAnswer;
	protected int questionNumber;
	protected String text;
	protected double points;
	
	
	public final String getText() {
		return text;
	}
	
	public final int getNumber() {
		return questionNumber;
	}
	
	public final double getPoints() {
		return points;
	}
	
	
	public abstract String getQuestionString();
	
	protected T getCorrectAnswer() {
		return correctAnswer;
	}
	
}
