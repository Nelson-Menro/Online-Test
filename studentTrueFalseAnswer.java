package onlineTest;

public class studentTrueFalseAnswer extends studentAnswer<Boolean> {

	private static final long serialVersionUID = 1L;

	public studentTrueFalseAnswer(int examId, boolean answer, int questionId) {
		this.examId = examId;
		this.answer = answer;
		this.questionId = questionId;
	}
	
	
	public Boolean getAnswer() {
		return answer;
	}
	
	
	public void setAnswer(boolean answer) {
		this.answer = answer;
		//list.add(this);
	}

	@Override
	public int compareTo(Object o) {
		//other answer. I casted it because the answer is of different type
		boolean castedObject = (boolean) o;
		
		if(this.answer == castedObject) {
			return 0;
		}else {
			return -1;
		}
	}


	
	
	
}
