package se.lexicon.emil.SchoolManager.gui.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import se.lexicon.emil.SchoolManager.App;
import se.lexicon.emil.SchoolManager.data.Student;
import se.lexicon.emil.SchoolManager.gui.student.StudentController;

public class SearchStudentController {

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
	private Button searchTypeButton;

	public static Student student;
	public static boolean isPrompt;

	@FXML
	private void initialize() {
		if (isPrompt)
			searchTypeButton.setVisible(false);
		else
			searchTypeButton.setVisible(true);
	}

	@FXML
	protected void searchButtonPressed() {

		if (!idField.getText().isEmpty())
			presentSearchResult(App.studentDao.getByID(idField.getText()));
		else if (!nameField.getText().isEmpty() && !surnameField.getText().isEmpty())
			presentSearchResult(App.studentDao.getByFullName(nameField.getText(), surnameField.getText()));
		else if (!nameField.getText().isEmpty())
			presentSearchResult(App.studentDao.getByFirstName(nameField.getText()));
		else if (!surnameField.getText().isEmpty())
			presentSearchResult(App.studentDao.getBySurname(surnameField.getText()));
		else if (!emailField.getText().isEmpty())
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
			if (isPrompt) {
				student = searchView.getSelectionModel().getSelectedItem();
				((Stage) searchView.getScene().getWindow()).close();

			} else {
				openEditView();
			}
		}
	}
	
	@FXML
	private void editButtonPressed() {
		if(!searchView.getSelectionModel().isEmpty())
			openEditView();
	}
	
	private void openEditView() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../student/Student.fxml"));
		Parent root1;
		try {
			root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root1));
			stage.show();

			((StudentController) fxmlLoader.getController())
					.presentStudent(searchView.getSelectionModel().getSelectedItem());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
