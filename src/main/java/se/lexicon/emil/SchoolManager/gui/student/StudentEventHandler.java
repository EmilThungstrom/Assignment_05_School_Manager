package se.lexicon.emil.SchoolManager.gui.student;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import se.lexicon.emil.SchoolManager.App;
import se.lexicon.emil.SchoolManager.data.Student;

public class StudentEventHandler {
	
	@FXML
	private TextField idField;
	@FXML
	private TextField nameField;
	@FXML
	private TextField surnameField;
	@FXML
	private TextField adressField;
	@FXML
	private TextField emailField;
	
	
	@FXML
	private void newButtonPressed() {
		idField.setText("");
		nameField.setText("");
		surnameField.setText("");
		adressField.setText("");
		emailField.setText("");
	}
	@FXML
	private void searchButtonPressed() {
		presentStudent(App.studentDao.getByID(Integer.parseInt(idField.getText(), 16)));
	}
	
	private void presentStudent(Student student) {
		idField.setText(Integer.toHexString(student.getId()));
		nameField.setText(student.getFirstName());
		surnameField.setText(student.getSurname());
		adressField.setText(student.getAdress());
		emailField.setText(student.getEmail());
	}
}
