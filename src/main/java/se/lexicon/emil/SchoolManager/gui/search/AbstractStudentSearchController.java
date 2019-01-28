package se.lexicon.emil.SchoolManager.gui.search;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import se.lexicon.emil.SchoolManager.App;
import se.lexicon.emil.SchoolManager.data.Student;

public abstract class AbstractStudentSearchController {

	@FXML
	protected TextField idField;
	@FXML
	protected TextField nameField;
	@FXML
	protected TextField surnameField;
	@FXML
	protected TextField adressField;
	@FXML
	protected TextField emailField;
	
	@FXML
	protected TableView<Student> searchView;
	@FXML
	protected TableColumn<Student, String> idColumn;
	@FXML
	protected TableColumn<Student, String> nameColumn;
	@FXML
	protected TableColumn<Student, String> surnameColumn;
	@FXML
	protected TableColumn<Student, String> emailColumn;
	@FXML
	protected TableColumn<Student, String> adressColumn;
	
	@FXML
	protected void searchButtonPressed() {
		
		if(!idField.getText().isEmpty())
			presentSearchResult(App.studentDao.getByID(idField.getText()));
		else if(!nameField.getText().isEmpty() && !surnameField.getText().isEmpty())
			presentSearchResult(App.studentDao.getByFullName(nameField.getText(), surnameField.getText()));
		else if(!nameField.getText().isEmpty())
			presentSearchResult(App.studentDao.getByFirstName(nameField.getText()));
		else if(!surnameField.getText().isEmpty())
			presentSearchResult(App.studentDao.getBySurname(surnameField.getText()));
		else if(!emailField.getText().isEmpty())
			presentSearchResult(App.studentDao.getByEmail(emailField.getText()));
		else
			searchView.setItems(null);
	}
	
	protected void presentSearchResult(Student student) {
		
		ArrayList<Student> tempList = new ArrayList<Student>();
		tempList.add(student);
		presentSearchResult(tempList);
	}
	
	protected void presentSearchResult(List<Student> students) {
		
		idColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("id"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
		surnameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("surname"));
		emailColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
		adressColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("adress"));
		searchView.setItems(FXCollections.observableArrayList(students));
	}
}
