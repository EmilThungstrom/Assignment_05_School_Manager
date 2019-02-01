package se.lexicon.emil.SchoolManager.gui.search;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import se.lexicon.emil.SchoolManager.App;
import se.lexicon.emil.SchoolManager.data.Course;
import se.lexicon.emil.SchoolManager.gui.course.CourseController;

public class SearchCourseController {

	@FXML
	private TextField idField;
	@FXML
	private TextField nameField;
	@FXML
	private DatePicker startDate;
	@FXML
	private TextField durationField;

	@FXML
	private TableView<Course> searchView;
	@FXML
	private TableColumn<Course, String> idColumn;
	@FXML
	private TableColumn<Course, String> nameColumn;
	@FXML
	private TableColumn<Course, Date> dateColumn;
	@FXML
	private TableColumn<Course, String> durationColumn;

	@FXML
	private void switchSearchType() {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("SearchStudent.fxml"));
			Scene scene = new Scene(root);
			App.getPrimaryStage().setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void searchViewerDoubleClicked(MouseEvent event) {
		if (event.getClickCount() >= 2 && !searchView.getSelectionModel().isEmpty()) {
			openEditView();
		}
	}

	@FXML
	private void searchButtonPressed() {

		if (!idField.getText().isEmpty())
			presentSearchResult(App.courseDao.getByID(idField.getText()));
		else if (!nameField.getText().isEmpty())
			presentSearchResult(App.courseDao.getByName(nameField.getText()));
		else if (!(startDate.getValue() == null))
			presentSearchResult(App.courseDao.getByDate(startDate.getValue()));
		else
			searchView.setItems(null);
	}
	
	@FXML
	private void editButtonPressed( ) {
		if(!searchView.getSelectionModel().isEmpty())
			openEditView();
	}
	
	private void openEditView() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../course/Course.fxml"));
		Parent root1;
		try {
			root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root1));
			stage.show();

			((CourseController) fxmlLoader.getController())
					.presentCourse(searchView.getSelectionModel().getSelectedItem());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void presentSearchResult(Course course) {

		ArrayList<Course> tempList = new ArrayList<Course>();
		tempList.add(course);
		presentSearchResult(tempList);
	}

	private void presentSearchResult(List<Course> courses) {

		idColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("id"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("name"));
		dateColumn.setCellValueFactory(new PropertyValueFactory<Course, Date>("startDate"));
		durationColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("weekDuration"));
		searchView.setItems(FXCollections.observableArrayList(courses));
	}
}
