package se.lexicon.emil.SchoolManager.gui.course;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import se.lexicon.emil.SchoolManager.App;
import se.lexicon.emil.SchoolManager.data.Course;
import se.lexicon.emil.SchoolManager.data.Student;

public class CourseController {
	@FXML
	private TextField idField;
	@FXML
	private TextField nameField;
	@FXML
	private DatePicker startDate;
	@FXML
	private TextField durationField;
	
	@FXML
	private TableView<Student> tableView;
	@FXML
	private TableColumn<Student, String> idColumn;
	@FXML
	private TableColumn<Student, String> nameColumn;
	@FXML
	private TableColumn<Student, String> surnameColumn;
	@FXML
	private TableColumn<Student, String> emailColumn;
	@FXML
	private TableColumn<Student, String> adressColumn;
	
	@FXML
	private void newButtonPressed() {
		idField.setEditable(true);
		idField.setText("");
		nameField.setText("");
		startDate.setValue(LocalDate.now());
		durationField.setText("");
		tableView.setItems(null);
	}
	@FXML
	private void searchButtonPressed() {
		presentCourse(App.courseDao.getByID(Integer.parseInt(idField.getText(), 16)));
	}
	
	private void presentCourse(Course course) {
		idField.setEditable(false);
		idField.setText(Integer.toHexString(course.getId()));
		nameField.setText(course.getName());
		startDate.setValue(course.getStartDate().toLocalDate());
		durationField.setText(String.valueOf(course.getWeekDuration()));
		
		idColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("id"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
		surnameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("surname"));
		emailColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
		adressColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("adress"));
		tableView.setItems(loadStudents(course));
	}
	
	private ObservableList<Student> loadStudents(Course course){
		return FXCollections.observableArrayList(course.getStudents());
	}
}
