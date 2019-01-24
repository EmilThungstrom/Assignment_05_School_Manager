package se.lexicon.emil.SchoolManager;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se.lexicon.emil.SchoolManager.data.Course;
import se.lexicon.emil.SchoolManager.data.Student;
import se.lexicon.emil.SchoolManager.data_access.CourseDao;
import se.lexicon.emil.SchoolManager.data_access.CourseDaoList;
import se.lexicon.emil.SchoolManager.data_access.StudentDao;
import se.lexicon.emil.SchoolManager.data_access.StudentDaoList;

public class App extends Application {
	
	private static Stage primaryStage;
	
	public static StudentDaoList studentDao = new StudentDaoList();
	public static CourseDaoList courseDao = new CourseDaoList();
	
	static final int TEST_ARRAY_LENGTH = 5;
	static String[] firstNames = {"Anna", "Emil", "Oscar", "Anneli", "Maria"};
	static String[] surnames = {"Thungstrom", "Edstrand", "Sundberg", "Anrep", "Carlson"};
	static String[] adresses = {"Studentvagen 1", "Karlskronavagen 17", "Falkenbergsvagen 22", "Johannesberg 77", "Oscarskulle 56"};
	static String[] emails = {"@gmail.com", "@outlook.com", "@lexicon.com", "@bth.se", "@yahoo.com"};

	static String[] courseNames = {"Matematik Introduktion", "Diskret Matematik", "Trigometri", "Kryption 1", "kryption 2"};
	
	static Student[] students = new Student[5];
	static Course[] courses = new Course[5];
	
	static Random random = new Random(System.currentTimeMillis());
	
	@SuppressWarnings("static-access")
	@Override
	public void start(Stage primaryStage) {
		
		//----------------------------------------------
		for(int i = 0; i < TEST_ARRAY_LENGTH; i++) {
			students[i] = new Student();
			courses[i] = new Course();
			
			students[i].setFirstName(firstNames[i]);
			students[i].setSurname(surnames[i]);
			students[i].setAdress(adresses[i]);
			students[i].setEmail(firstNames[i] + '.' + surnames[i] + emails[i]);
			
			courses[i].setCourseName(courseNames[i]);
			courses[i].setStartDate(Date.valueOf(LocalDate.of((2000 + random.nextInt(20)), (random.nextInt(11) + 1), (random.nextInt(27) + 1))));
			courses[i].setWeekDuration(random.nextInt(51) + 1);
		}
		
		courses[0].setStudents(Arrays.asList(students));
		
		for(int i = 0; i < TEST_ARRAY_LENGTH; i++) {
			System.out.println(Integer.toHexString(courses[i].getId()));
			studentDao.addStudent(students[i]);
			courseDao.addCourse(courses[i]);
		}
		//----------------------------------------------
		
		this.primaryStage = primaryStage;
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("gui/Main.fxml"));
			Scene scene = new Scene(root);
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
	
	public static Stage getPrimaryStage() {
		return primaryStage;
	}
}
