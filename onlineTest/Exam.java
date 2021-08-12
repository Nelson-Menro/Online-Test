package onlineTest;


import java.util.HashMap;

public class Exam implements Comparable<Object>, java.io.Serializable {

	
	private static final long serialVersionUID = 1L;
	// every exam has an
	protected int id;
	protected String title;
	
	//hash map to access questions
	protected HashMap<Integer, Questions<?>> questionDataBase = new HashMap<Integer, Questions<?>>();
	

	public Exam(int id, String title) {
		this.id = id;
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}
	
	public void addQuestion(int questionNumber ,Questions<?> question) {
		questionDataBase.put(questionNumber, question);
	}
	
	public Questions<?> getQuestion(int questionNumber) {
		return questionDataBase.get(questionNumber);
	}
	
	//return questions method

	@Override
	public int compareTo(Object o) {
		//probably able to remove this cast later 
		Exam exam = (Exam) o;
		if (this.id > exam.id) {
			return 1;
		} else if (this.id < exam.id) {
			return -1;
		} else {
			return 0;
		}

	}
	
	
	public String questionToString() {
		String toReturn = "";
		for(int i = 1; i < questionDataBase.size() + 1; i++) {
			toReturn += questionDataBase.get(i).getQuestionString();
		}
		return toReturn;
	}
	
	public double totalExamPoints() {	
		double totalPoints = 0;
		//changed i from 0 to 1 
		for(int i = 1; i <= questionDataBase.size(); i++) {
			totalPoints += questionDataBase.get(i).getPoints();
		}
		return totalPoints;
	}
	
	public double totalStudentPoints(Student student, Exam exam) {
		double totalPoints = 0;
		double toReturn = 0;
		for (int i = 1; i < questionDataBase.size()+1; i++) {
			if(this.compareStudentAnswer(student, i, exam)) {
				if(!(student.getAnswer(i, exam.getId()) instanceof StudentFillInTheBlankAnswer)) {
					totalPoints += this.getQuestion(i).getPoints();
					toReturn = totalPoints;
				}else {
					StudentFillInTheBlankAnswer tempAnswer = (StudentFillInTheBlankAnswer) student.getAnswer(i, exam.getId());
					String[] temp = (String []) this.getQuestion(i).correctAnswer;
					double pointsToAdd = tempAnswer.points / temp.length;
					
					totalPoints = totalPoints + (pointsToAdd * tempAnswer.pointsRecieved);
					toReturn = totalPoints;
				}
				
			}
		}
	
		
		return toReturn;
	}
	
	
	public boolean compareStudentAnswer(Student student, int questionId, Exam exam) {
		Student tempStudent = student;
		
		studentAnswer<?> studentAnswer = tempStudent.getAnswer(questionId, exam.getId());
		
		Object theCorrectAnswer;
		
		
		if(questionDataBase.get(questionId).getCorrectAnswer() instanceof Boolean) {
			theCorrectAnswer = (Boolean) questionDataBase.get(questionId).getCorrectAnswer();
		} else {
			 theCorrectAnswer = (String[]) questionDataBase.get(questionId).getCorrectAnswer();
		}
		
		if(studentAnswer.compareTo(theCorrectAnswer) == 0) {
			return true;
		}

		return false;
	}
}
