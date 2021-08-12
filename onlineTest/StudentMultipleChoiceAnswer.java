package onlineTest;

public class StudentMultipleChoiceAnswer extends studentAnswer<String[]> {

	private static final long serialVersionUID = 1L;


	public StudentMultipleChoiceAnswer(int examId, String[] answer, int questionId) {
		this.examId = examId;
		this.answer = answer;
		this.questionId = questionId;
	}
	
	
	public void setAnswer(String[] answer) {
		this.answer = answer;
	}

	
	@Override
	public int compareTo(Object o) {
		//other answer. I casted it because the answer is of different type
		String[] castedObject = (String []) o;
		
		int toReturn = 0;
		
		if(answer.length != castedObject.length) {
			return -1;
		}
		
		for(int i = 0; i < castedObject.length; i++) {
			//becomes -1 if one element are not equal
			if(!answer[i].equals(castedObject[i])) {
				toReturn = -1;
			}
		}
		
		return toReturn;
	}
}
