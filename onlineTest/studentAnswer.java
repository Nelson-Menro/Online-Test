package onlineTest;

//made comparable of type object so that I can compare student answers and correct answers
public abstract class studentAnswer<T> implements Comparable<Object>, java.io.Serializable{

	private static final long serialVersionUID = 1L;
	protected T answer;
	protected int questionId;
	protected int examId;
			
	public final int getQuestionId() {
		return questionId;
	}
	
	public final int getExamId() {
		return examId;
	}
	
	public T getAnswer() {
		return answer;
	}

	public abstract int compareTo(Object o);
	
	


}

