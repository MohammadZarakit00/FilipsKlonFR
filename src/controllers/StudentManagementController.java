package controllers;

import application.Main;
import javafx.scene.control.TextField;
import model.Course;
import model.CourseRegister;
import model.Student;
import model.StudentRegister;
import model.WrittenExam;
import model.WrittenExamRegister;

import java.util.ArrayList;
import java.util.Random;

import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;

public class StudentManagementController extends Controller {

	/*
	
	private ArrayList<Student> studentRegister = new ArrayList<Student>();
	private ArrayList<Course> courseRegister = new ArrayList<Course>();
	private ArrayList<WrittenExam> examRegister = new ArrayList<>();
	private Main main;
	*/
	
	private CourseRegister courseRegister = CourseRegister.getCourseRegInstance();
	private StudentRegister studentRegister = StudentRegister.getStudentRegInstance();
	private WrittenExamRegister examRegister = WrittenExamRegister.getExamRegInstance();
	
	

	@FXML
	Button btnAddStudent = new Button();

	@FXML
	Button btnFindStudent = new Button();
	@FXML
	Button btnDeleteStudent = new Button();
	@FXML
	Button btnAddCourse = new Button();
	@FXML
	Button btnFindCourse = new Button();
	@FXML
	Button btnRemoveCourse = new Button();
	@FXML
	Button btnAddExam = new Button();
	@FXML
	TextArea ta = new TextArea();

	@FXML
	TextField tfId = new TextField();
	@FXML
	TextField tfIdExam = new TextField();
	@FXML
	TextField tfName = new TextField();
	@FXML
	TextField tfCredits = new TextField();
	@FXML
	TextField tfDate = new TextField();
	@FXML
	TextField tfLocation = new TextField();
	@FXML
	TextField tfTime = new TextField();
	@FXML
	MenuButton menuButton = new MenuButton();
	@FXML
	Button btnScene2;
	@FXML
	Button btnGenerateStudentId = new Button();


	//i have no idea what this does. 
	public void handleBtn1() throws Exception {
		Parent root = FXMLLoader.load(this.getClass().getResource("/Homepage.fxml"));
		Stage window = (Stage) btnScene2.getScene().getWindow();
		window.setScene(new Scene(root, 800, 600));
	}

	@FXML
	public void btnAddStudent(ActionEvent event) {
	String tmpId = tfId.getText();
	String tmpName = tfName.getText();
	Student student = new Student(tmpId, tmpName);
	if (!student.studentValidCheck(tmpId)) {
		ta.setText("You must enter a valid Student ID and name to add a student.");
	} else if (studentRegister.containsStudent(tmpId)) {
		ta.setText("Student already exists.");
	} else {
		studentRegister.add(student);
		ta.setText(tmpName + " was added to the register. ");
		}
	}
	
	//Generates a student ID in accordance to the constructor rules, i.e. capital 's' and 5 numbers.
	@FXML
	public void btnGenerateStudentId(ActionEvent event) {
		Random studentGen = new Random();
		int studentId = studentGen.nextInt(99999 + 1 - 10000)+10000;
		tfId.clear();
		tfId.setText("S" + studentId);
		
		String tmpId = tfId.getText();
		for (Student s : studentRegister.getStudentRegister()) {
			if (studentRegister.containsStudent(tmpId)) {
				tfId.clear();
				tfId.setText("S" + studentGen.nextInt(studentId));
			}
		}
	}

	@FXML
	public String btnFindStudent(ActionEvent event) {
		String tmpId = tfId.getText();
		for (Student s : studentRegister.getStudentRegister()) {
			if (s.getStudentId().equals(tmpId)) {
				ta.setText("Found student " + s.getName() + " with ID " + s.getStudentId() + ". ");
			}
			else {
				ta.setText("No student found with given identification. ");
			}
		}
		return tmpId;
	}

		@FXML
		public void btnDeleteStudent (ActionEvent event){
			String tmpId = tfId.getText();
			if(studentRegister.containsStudent(tmpId)) {
				studentRegister.remove(tmpId);
				ta.setText("Student " + tmpId + " was removed from the system. ");
			} else {
				ta.setText("Student " + tmpId + " does not exist in the register. ");
			}			
		}
	}



