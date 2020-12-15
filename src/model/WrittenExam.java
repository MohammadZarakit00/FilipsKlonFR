package model;

import java.util.ArrayList;
import java.util.Arrays;

public class WrittenExam {
	
	private String examID;
	private String date;
	private String location;
	private String time;
	private double maxPoints = 100;
	private Course currentCourse;
	private ArrayList<Student> studentList = new ArrayList<>();

	private ArrayList<String> acceptedLocations = new ArrayList<>
			(Arrays.asList("Room A123", "Room A167", "Room B198", "Room B067"));


	public WrittenExam(String examID, String date, String location, String time, Course currentCourse){
		if(checkExamIdInput(examID) && acceptedLocations.contains(location)){
			this.examID = examID;
			this.date = date;
			this.location = location;
			this.time = time;
			this.currentCourse = currentCourse;
		} else {
			System.out.println("Fel format på examId");
		}
	}

	public Boolean checkExamIdInput(String examID){
		String subString = examID.substring(1);
		int examIdNumber = Integer.parseInt(subString);
		return examID.startsWith("E") && examID.length() == 6 && (examIdNumber >= 10000 && examIdNumber <= 99999);
	}

	public Course getCurrentCourse() {
		return currentCourse;
	}

	public void setCurrentCourse(Course currentCourse) {
		this.currentCourse = currentCourse;
	}

	public ArrayList<Student> getStudentList() {
		return studentList;
	}
	public void setStudentList(ArrayList<Student> studentList) {
		this.studentList = studentList;
	}
	public String getExamID() {
		return examID;
	}
	public void setExamID(String examID) {
		this.examID = examID;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public double getMaxPoints() {
		return maxPoints;
	}
	public void setMaxPoints(double maxPoints) {
		this.maxPoints = maxPoints;
	}
	
	public void addStudent(Student student) {
		student.addExam(this, null); //Dubbelkopplar och sätter betug till null
		studentList.add(student);
	}
	
	public Student findStudent(String studentId) {
		for (Student tmpStudent : studentList) {
			if (tmpStudent.getStudentId().equals(studentId)) {
				return tmpStudent;
			}
		}
		return null;
	}

	/*
	Fundera vidare kring deleteStudents returtyp, är det nödvändigt att returnera
	en student? Bättre med boolean eller void?
	 */
	public Student deleteStudent(String studentId) {
		Student tmpStudent = findStudent(studentId);
		if(studentList.contains(tmpStudent)){
			studentList.remove(tmpStudent);
			tmpStudent.removeExam(this);
			return tmpStudent;
		} else {
			System.out.println("Student finns ej på detta prov");
		}
		return null;
	}
}
