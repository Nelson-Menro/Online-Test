package onlineTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;

public class SystemManager implements Manager , Serializable{
	

	private static final long serialVersionUID = 1L;
	protected HashMap<Integer, Exam> dataBase = new HashMap<Integer, Exam>();
	protected ArrayList<Student> students = new ArrayList<Student>();
	protected Hashtable<Double, String> gradeCutOffs = new Hashtable<Double, String>();

	@Override
	public boolean addExam(int examId, String title) {
		//if exam is found then return false
		if (dataBase.containsKey(examId)) {
			return false;
		} else {
			//else make new exam with the examId
			Exam newExam = new Exam(examId, title);
			
			//enter in new exam at key with that examId
			dataBase.put(examId, newExam);
			return true;
		}
		
	}

	@Override
	public void addTrueFalseQuestion(int examId, int questionNumber, String text, double points, boolean answer) {
		if(dataBase.containsKey(examId)) {
			//we found the exam and we are storing it in a temp 
			Exam temp = dataBase.get(examId);
			
			//if the question exists, it is overwritten
			Questions<?> newQuestion = new TrueFalseQuestion(questionNumber, text, points, answer);
			
			temp.addQuestion(questionNumber, newQuestion);
			
		}
	}

	@Override
	public void addMultipleChoiceQuestion(int examId, int questionNumber, String text, double points, String[] answer) {
		
		if(dataBase.containsKey(examId)) {
			//we found the exam and we are storing it in a temp 
			Exam temp = dataBase.get(examId);
			
			//if the question exists, it is overwritten
			Questions<?> newQuestion = new MultipleChoiceQuestion(questionNumber, text, points, answer);
			
			temp.addQuestion(questionNumber, newQuestion);
			
		}

	}

	@Override
	public void addFillInTheBlanksQuestion(int examId, int questionNumber, String text, double points,
			String[] answer) {
		if(dataBase.containsKey(examId)) {
			//we found the exam and we are storing it in a temp 
			Exam temp = dataBase.get(examId);
			
			//if the question exists, it is overwritten
			Questions<?> newQuestion = new FillInTheBlankQuestion(questionNumber, text, points, answer);
			
			temp.addQuestion(questionNumber, newQuestion);
			
		}

	}

	@Override
	public String getKey(int examId) {
		if(dataBase.get(examId) == null) {
			return "Exam not found";
		}else
		{
			Exam temp = dataBase.get(examId);
			return temp.questionToString();
		}
	}

	@Override
	public boolean addStudent(String name) {
		if(!students.contains(name)) {
			Student newStudent = new Student(name);
			students.add(newStudent);
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public void answerTrueFalseQuestion(String studentName, int examId, int questionNumber, boolean answer) {
		//grabbing student 
		Student tempStudent = getStudent(studentName);
		//got the exam
		
		tempStudent.setTrueFalseAnswer(examId, answer, questionNumber);
	}
	
	private Student getStudent(String name) {
		
		//compare strings 
		for(int i = 0; i < students.size(); i++) {

			if(students.get(i).getName().equals(name)) {
				return students.get(i);
			}
		}
		
		return null;
	}

	@Override
	public void answerMultipleChoiceQuestion(String studentName, int examId, int questionNumber, String[] answer) {
		//grabbing student 
		Student tempStudent = getStudent(studentName);
		//got the exam
		
		tempStudent.setMultipleChoiceAnswer(examId, answer, questionNumber);

	}

	@Override
	public void answerFillInTheBlanksQuestion(String studentName, int examId, int questionNumber, String[] answer) {
		// grabbing student
		Student tempStudent = getStudent(studentName);
		// got the exam
		Exam tempExam = this.dataBase.get(examId);
		
		tempStudent.setFillInTheBlankAnswer(examId, answer, questionNumber, tempExam);

	}

	@Override
	public double getExamScore(String studentName, int examId) {
		// got the student 
		Student tempStudent = getStudent(studentName);
		
		//got the exam
		Exam tempExam = dataBase.get(examId);
		
		
		//changed from int to exam for parameter
		return tempExam.totalStudentPoints(tempStudent, tempExam);
	}

	@Override
	public String getGradingReport(String studentName, int examId) {
		
		String toReturn = "";
		double totalPoints = 0;
		double studentPoints = 0;
		
		Student tempStudent = getStudent(studentName);
		Exam tempExam = dataBase.get(examId);
		
		
		
		for(int i = 1; i <= tempExam.questionDataBase.size(); i++) {
			//this int keeps track of points if student got it right 
			double points = 0;
			
			//grabbed answers
			//also changed correctAnswer from type boolean to object
			Object correctAnswer = tempExam.getQuestion(i).getCorrectAnswer();
			
			
			
			studentAnswer<?> studentAnswer = tempStudent.getAnswer(i, examId);
			
		
			//comparing answers using method in true false answer subclass
			if(studentAnswer.compareTo(correctAnswer) == 0) {
				if(!(studentAnswer instanceof StudentFillInTheBlankAnswer)) {
				points += tempExam.getQuestion(i).getPoints();
				}else {
					StudentFillInTheBlankAnswer tempAnswer =  (StudentFillInTheBlankAnswer) studentAnswer;
					String [] temp = (String []) correctAnswer;
					double pointsToAdd = tempAnswer.points / temp.length;
					points = points + (pointsToAdd * tempAnswer.pointsRecieved);
				}
			} 
			

			toReturn += "Question #" + i + " " + points + " points out of "
					+ tempExam.getQuestion(i).getPoints() + "\n";
			totalPoints += tempExam.getQuestion(i).getPoints();
			
			
			//add in points to total points student got right 
			studentPoints += points;
		}
		
		
		toReturn += "Final Score: " + studentPoints + " out of " + totalPoints;
		
		return toReturn;
	}
	

	@Override
	public void setLetterGradesCutoffs(String[] letterGrades, double[] cutoffs) {
		// TODO Auto-generated method stub
		
		//add first cutoff 
		double startingPoint = 100;
		int toSubtract = 0;
		//add cutoffs until reached 2nd one 
		
		
		for(int i = 0; i < letterGrades.length; i++) {
			//grabbed highest cutoff
			String letterGrade = letterGrades[i];
			double gradeValue = cutoffs[i];
			
			//loop with this cutoff and adding grades until I reach 2nd cutoff
			for(double j = startingPoint; j >= gradeValue; j--) {
				gradeCutOffs.put(j, letterGrade);
				toSubtract++;
			}
			startingPoint -= toSubtract;
			toSubtract = 0;
		}
		
		
	}

	@Override
	public double getCourseNumericGrade(String studentName) {
		// TODO Auto-generated method stub
		int numberOfExams = dataBase.size();
		int totalExamPoints = 0;
		
		for(int i = 1; i <= numberOfExams; i++) {
			
			double pointsToAdd = this.getExamScore(studentName, i)/ this.dataBase.get(i).totalExamPoints()*100;
			int points = (int) Math.ceil(pointsToAdd);
			totalExamPoints += points;
		}
		
		
		return (totalExamPoints/numberOfExams);
	}

	@Override
	public String getCourseLetterGrade(String studentName) {
		// TODO Auto-generated method stub
		double numericGrade = this.getCourseNumericGrade(studentName);
		
		return gradeCutOffs.get(numericGrade);
	}

	@Override
	public String getCourseGrades() {

		Collections.sort(students, new sortByName());
		String toReturn = "";
		for(Student student : students) {
			toReturn += student.getName() + " " + this.getCourseNumericGrade(student.getName()) + " " + this.getCourseLetterGrade(student.getName()) + "\n";
		}
		
		return toReturn;
	}

	@Override
	public double getMaxScore(int examId) {
		//get all student exam scores
		//compare to see which is largest
		ArrayList<Double> scores = new ArrayList<Double>();
		
		
		for(Student student : students) {
			scores.add(this.getExamScore(student.getName(), examId));
		}
		
		
		return Collections.max(scores);
	}

	@Override
	public double getMinScore(int examId) {
		// get all student exam scores
		// compare to see which is largest
		ArrayList<Double> scores = new ArrayList<Double>();

		for (Student student : students) {
			scores.add(this.getExamScore(student.getName(), examId));
		}

		return Collections.min(scores);
	}

	@Override
	public double getAverageScore(int examId) {
		//use this expression to compute average 
		//((examScore1/totalScoreExam1) + (examScore2/totalScoreExam2) + ...) / totalNumberOfExams
		double totalPoints = 0;
		double numberOfExams = 0;
		
		for(Student student : students) {
			
			//totalScore does not work
			double studentExamScore = this.getExamScore(student.getName(), examId);
			//double totalScoreExam = this.dataBase.get(examId).totalExamPoints();
			totalPoints += studentExamScore;
			numberOfExams++;
		}
		
		return totalPoints / numberOfExams;
	}

	@Override
	public void saveManager(Manager manager, String fileName){
		// TODO Auto-generated method stub
		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName));
			objectOutputStream.writeObject(manager);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Manager restoreManager(String fileName) {
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName));
			
			Manager manager = (Manager) objectInputStream.readObject();
			return manager;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	

}
