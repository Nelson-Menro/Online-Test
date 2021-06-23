package onlineTest;


public class StudentFillInTheBlankAnswer extends studentAnswer<String[]> {

	private static final long serialVersionUID = 1L;

	//created this instance variables since partial credit can be given 
	protected double pointsRecieved = 0;
	
	//amount of points this question is worth
	protected double points;

	
	
	public StudentFillInTheBlankAnswer(int examId, String[] answer, int questionId, double points) {
		this.examId = examId;
		this.answer = answer;
		this.questionId = questionId;
		this.points = points;
	}
	
	public void setAnswer(String[] answer) {
		this.answer = answer;
	}
	
	public double getPointsRecieved() {
		return pointsRecieved;
	}

	@Override
	public int compareTo(Object o) {
		//other answer. I casted it because the answer is of different type
		String[] castedObject = (String []) o;
		
		
		int toReturn = -1;
		pointsRecieved = 0;
		
		for(int i = 0; i < this.answer.length; i++) {
			
			String studentAnswer = this.answer[i];
			
			for(int j = 0; j < castedObject.length; j++) {
				if(studentAnswer.equals(castedObject[j])) {
					toReturn = 0;
					pointsRecieved++;
				}
			}
			
		}
		
		
		return toReturn;
	}

}
