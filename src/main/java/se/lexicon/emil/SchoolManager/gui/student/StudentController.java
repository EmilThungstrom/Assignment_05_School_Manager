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

	private Student student;

	@FXML
	private void newButtonPressed() {
		idField.setText("");
		nameField.setText("");
		surnameField.setText("");
		adressField.setText("");
		emailField.setText("");
	}

	@FXML
	private void saveButtonPressed() {

		if(student != null) {
			student.setFirstName(nameField.getText());
			student.setSurname(surnameField.getText());
			student.setEmail(emailField.getText());
			student.setAdress(adressField.getText());
		}
		else if(formFilledOut()) {
			student = new Student(nameField.getText(), surnameField.getText(), emailField.getText(), adressField.getText());
			idField.setText(student.getId());
			
			App.studentDao.addStudent(student);
		}
	}

	private boolean formFilledOut() {
		return nameField.getText().replaceAll("\\s+","").isEmpty() &&
				surnameField.getText().replaceAll("\\s+","").isEmpty() &&
				emailField.getText().replaceAll("\\s+","").isEmpty() &&
				adressField.getText().replaceAll("\\s+","").isEmpty()
				? false : true;
	}
	
	public void presentStudent(Student student) {

		this.student = student;
		idField.setText(student.getId());
		nameField.setText(student.getFirstName());
		surnameField.setText(student.getSurname());
		adressField.setText(student.getAdress());
		emailField.setText(student.getEmail());
	}
}
