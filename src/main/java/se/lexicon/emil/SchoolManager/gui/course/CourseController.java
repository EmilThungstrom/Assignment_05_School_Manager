package se.lexicon.emil.SchoolManager.gui.course;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import se.lexicon.emil.SchoolManager.App;
import se.lexicon.emil.SchoolManager.data.Course;
import se.lexicon.emil.SchoolManager.data.Student;
import se.lexicon.emil.SchoolManager.gui.search.SearchStudentController;

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

	private Course course;

	@FXML
	private void newButtonPressed() {

		idField.setText("");
		nameField.setText("");
		startDate.setValue(LocalDate.now());
		durationField.setText("");
		tableView.setItems(null);
	}

	@FXML
	private void addStudentButtonPressed() {
		if (course != null) {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../search/SearchStudent.fxml"));
			SearchStudentController.student = null;
			SearchStudentController.isPrompt = true;
			Parent root1;
			try {
				root1 = (Parent) fxmlLoader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(root1));
				stage.showAndWait();

			} catch (IOException e) {
				e.printStackTrace();
			}
			Student student = SearchStudentController.student;
			SearchStudentController.isPrompt = false;
			if (student != null) {
				ArrayList<Student> list = new ArrayList<Student>(course.getStudents());
				list.add(student);
				course.setStudents(list);
				tableView.setItems(FXCollections.observableArrayList(course.getStudents()));
			} 
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle(null);
			alert.setHeaderText(null);
			alert.setContentText("You need to create a course before you can add students");

			alert.showAndWait();
		}
	}

	@FXML
	private void removeStudentButtonPressed() {
		ArrayList<Student> list = new ArrayList<Student>(course.getStudents());
		list.remove(tableView.getSelectionModel().getFocusedIndex());
		course.setStudents(list);
		tableView.setItems(FXCollections.observableArrayList(course.getStudents()));
	}

	@FXML
	private void saveButtonPressed() {

		if(course != null) {
			course.setCourseName(nameField.getText());
			course.setStartDate(Date.valueOf(startDate.getValue()));
			course.setWeekDuration(Integer.valueOf(durationField.getText()));
		}
		else if(formFilledOut()) {
			course = new Course(nameField.getText(), Date.valueOf(startDate.getValue()), Integer.valueOf(durationField.getText()));
			idField.setText(course.getId());
			
			App.courseDao.addCourse(course);
		}
	}
	
	@FXML
	private void backButtonPressed() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../Main.fxml"));
			App.getPrimaryStage().setScene(new Scene(root));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private boolean formFilledOut() {
		return nameField.getText().replaceAll("\\s+","").isEmpty() &&
				(startDate.getValue() != null) &&
				durationField.getText().replaceAll("\\s+","").isEmpty()
				? false : true;
	}

	public void presentCourse(Course course) {

		this.course = course;
		idField.setText(course.getId());
		nameField.setText(course.getName());
		startDate.setValue(course.getStartDate().toLocalDate());
		durationField.setText(String.valueOf(course.getWeekDuration()));

		idColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("id"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
		surnameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("surname"));
		emailColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
		adressColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("adress"));
		tableView.setItems(FXCollections.observableArrayList(course.getStudents()));
	}
}
