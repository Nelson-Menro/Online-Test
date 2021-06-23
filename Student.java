package onlineTest;

import java.util.ArrayList;
import java.util.Comparator;


public class Student implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	protected String fullName; 
	
	//keeps track of answers
	protected ArrayList<studentAnswer<?>> answers = new ArrayList<studentAnswer<?>>();
	
	
	
	public Student (String fullName) {
		this.fullName = fullName;
	}


	public String getName() {
		return fullName;
	}
	
	public void setTrueFalseAnswer(int examId, boolean answer, int questionNumber) {
		//added in answer to corresponding exam and question
		answers.add(new studentTrueFalseAnswer(examId, answer, questionNumber));
	}
	
	public void setMultipleChoiceAnswer(int examId, String[] answer, int questionNumber) {
		//added in answer to corresponding exam and question
		answers.add(new StudentMultipleChoiceAnswer(examId, answer, questionNumber));
	}
	
	public void setFillInTheBlankAnswer(int examId, String[] answer, int questionNumber, Exam exam) {
		//added in answer to corresponding exam and question
		
		
		//entering in points as last parameter
		answers.add(new StudentFillInTheBlankAnswer(examId, answer, questionNumber, exam.getQuestion(questionNumber).getPoints()));
	}
	
	public studentAnswer<?> getAnswer(int questionId, int examId){
		for(studentAnswer<?> answer : answers) {
			if(answer.getExamId() == examId && answer.getQuestionId() == questionId) {
				return answer;
			}
		}
		
		return null;
	}
	
	
	public boolean equals (Object o) {
		if(o instanceof Student) {
			Student temp = (Student) o;
			
			if(this.getName().equals(temp.getName())) {
				return true;
			}else {
				return false;
			}
		}
		else {
			return false;
		}
	}
}

class sortByName implements Comparator<Student>{

	@Override
	public int compare(Student o1, Student o2) {
        return o1.getName().compareToIgnoreCase(o2.getName());
	}
	
}
