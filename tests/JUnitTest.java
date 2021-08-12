package tests;
import onlineTest.SystemManager;


//import static org.junit.Assert.*;

//import org.junit.Test;


public class JUnitTest {
    public static void main(String[] args) {
		String testName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		
		StringBuffer answer = new StringBuffer();
		SystemManager manager = new SystemManager();
		manager.addExam(10, "Midterm");
		manager.addTrueFalseQuestion(10, 1, "Abstract classes cannot have constructors.", 2, false);
		manager.addTrueFalseQuestion(10, 2, "The equals method returns a boolean.", 4, true);
		manager.addTrueFalseQuestion(10, 3, "Identifiers can start with numbers.", 3, false);
		answer.append(manager.getKey(10));
		String studentName = "Smith,John";
		manager.addStudent(studentName);
		manager.answerTrueFalseQuestion(studentName, 10, 1, false);
		manager.answerTrueFalseQuestion(studentName, 10, 2, true);
		manager.answerTrueFalseQuestion(studentName, 10, 3, false);
		answer.append("Exam score for " + studentName + " " + manager.getExamScore(studentName, 10));
		studentName = "Peterson,Rose";
		manager.addStudent(studentName);
		manager.answerTrueFalseQuestion(studentName, 10, 1, false);
		manager.answerTrueFalseQuestion(studentName, 10, 2, false);
		manager.answerTrueFalseQuestion(studentName, 10, 3, false);
		answer.append("\nExam score for " + studentName + " " + manager.getExamScore(studentName, 10));
		
		studentName = "Sanders,Linda";
		manager.addStudent(studentName);
		manager.answerTrueFalseQuestion(studentName, 10, 1, true);
		manager.answerTrueFalseQuestion(studentName, 10, 2, false);
		manager.answerTrueFalseQuestion(studentName, 10, 3, true);
		answer.append("\nExam score for " + studentName + " " + manager.getExamScore(studentName, 10));
		System.out.println(answer);
		//assertTrue(TestingSupport.isResultCorrect(testName, answer.toString(), true));
	}
    /*
    @Test
	public void pub01TrueFalse() {
		String testName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		
		StringBuffer answer = new StringBuffer();
		SystemManager manager = new SystemManager();
		manager.addExam(10, "Midterm");
		manager.addTrueFalseQuestion(10, 1, "Abstract classes cannot have constructors.", 2, false);
		manager.addTrueFalseQuestion(10, 2, "The equals method returns a boolean.", 4, true);
		manager.addTrueFalseQuestion(10, 3, "Identifiers can start with numbers.", 3, false);
		answer.append(manager.getKey(10));
		String studentName = "Smith,John";
		manager.addStudent(studentName);
		manager.answerTrueFalseQuestion(studentName, 10, 1, false);
		manager.answerTrueFalseQuestion(studentName, 10, 2, true);
		manager.answerTrueFalseQuestion(studentName, 10, 3, false);
		answer.append("Exam score for " + studentName + " " + manager.getExamScore(studentName, 10));
		studentName = "Peterson,Rose";
		manager.addStudent(studentName);
		manager.answerTrueFalseQuestion(studentName, 10, 1, false);
		manager.answerTrueFalseQuestion(studentName, 10, 2, false);
		manager.answerTrueFalseQuestion(studentName, 10, 3, false);
		answer.append("\nExam score for " + studentName + " " + manager.getExamScore(studentName, 10));
		
		studentName = "Sanders,Linda";
		manager.addStudent(studentName);
		manager.answerTrueFalseQuestion(studentName, 10, 1, true);
		manager.answerTrueFalseQuestion(studentName, 10, 2, false);
		manager.answerTrueFalseQuestion(studentName, 10, 3, true);
		answer.append("\nExam score for " + studentName + " " + manager.getExamScore(studentName, 10));
		assertTrue(TestingSupport.isResultCorrect(testName, answer.toString(), true));
	}

*/
}
