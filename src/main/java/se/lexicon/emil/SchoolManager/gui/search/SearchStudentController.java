package se.lexicon.emil.SchoolManager.gui.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.event.EventContext;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import se.lexicon.emil.SchoolManager.App;
import se.lexicon.emil.SchoolManager.data.Course;
import se.lexicon.emil.SchoolManager.data.Student;
import se.lexicon.emil.SchoolManager.gui.student.StudentController;

public class SearchStudentController {

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
	private TableView<Student> searchView;
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
	private void switchSearchType() {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("SearchCourse.fxml"));
			Scene scene = new Scene(root);
			App.getPrimaryStage().setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void searchViewerDoubleClicked(MouseEvent event) {
		if (event.getClickCount() >= 2 && !searchView.getSelectionModel().isEmpty()) {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../student/Student.fxml"));
			Parent root1;
			try {
				root1 = (Parent) fxmlLoader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(root1));
				stage.show();
				
				((StudentController)fxmlLoader.getController()).presentStudent(searchView.getSelectionModel().getSelectedItem());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@FXML
	private void searchButtonPressed() {
		
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
	
	private void presentSearchResult(Student student) {
		
		ArrayList<Student> tempList = new ArrayList<Student>();
		tempList.add(student);
		presentSearchResult(tempList);
	}
	
	private void presentSearchResult(List<Student> students) {
		
		idColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("id"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
		surnameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("surname"));
		emailColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
		adressColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("adress"));
		searchView.setItems(FXCollections.observableArrayList(students));
	}
}