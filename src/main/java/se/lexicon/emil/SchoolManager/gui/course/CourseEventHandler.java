package se.lexicon.emil.SchoolManager.gui.course;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import se.lexicon.emil.SchoolManager.App;
import se.lexicon.emil.SchoolManager.data.Course;

public class CourseEventHandler {
	@FXML
	private TextField idField;
	@FXML
	private TextField nameField;
	@FXML
	private DatePicker startDate;
	@FXML
	private TextField durationField;
	
	
	@FXML
	private void newButtonPressed() {
		idField.setEditable(true);
		idField.setText("");
		nameField.setText("");
		startDate.setValue(LocalDate.now());;
		durationField.setText("");
	}
	@FXML
	private void searchButtonPressed() {
		presentCourse(App.courseDao.getByID(Integer.parseInt(idField.getText(), 16)));
	}
	
	private void presentCourse(Course course) {
		idField.setEditable(false);
		idField.setText(Integer.toHexString(course.getId()));
		nameField.setText(course.getCourseName());
		startDate.setValue(course.getStartDate().toLocalDate());
		durationField.setText(String.valueOf(course.getWeekDuration()));
	}
}
