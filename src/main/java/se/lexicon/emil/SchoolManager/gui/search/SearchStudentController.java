package se.lexicon.emil.SchoolManager.gui.search;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import se.lexicon.emil.SchoolManager.App;
import se.lexicon.emil.SchoolManager.gui.student.StudentController;

public class SearchStudentController extends AbstractStudentSearchController {

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
				e.printStackTrace();
			}
		}
	}
}
