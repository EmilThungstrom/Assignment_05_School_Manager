package se.lexicon.emil.SchoolManager.gui.student;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import se.lexicon.emil.SchoolManager.App;
import se.lexicon.emil.SchoolManager.data.Student;

public class StudentController {
	
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
		idField.setEditable(true);
		idField.setText("");
		nameField.setText("");
		surnameField.setText("");
		adressField.setText("");
		emailField.setText("");
	}
	@FXML
	private void searchButtonPressed() {
		presentStudent(App.studentDao.getByID(idField.getText()));
	}
	
	private void presentStudent(Student student) {
		idField.setEditable(false);
		idField.setText(student.getId());
		nameField.setText(student.getFirstName());
		surnameField.setText(student.getSurname());
		adressField.setText(student.getAdress());
		emailField.setText(student.getEmail());
	}
}
