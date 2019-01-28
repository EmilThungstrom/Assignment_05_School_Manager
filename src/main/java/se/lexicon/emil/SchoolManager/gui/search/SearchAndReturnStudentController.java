package se.lexicon.emil.SchoolManager.gui.search;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import se.lexicon.emil.SchoolManager.data.Student;

public class SearchAndReturnStudentController extends AbstractStudentSearchController {
	
	@FXML
	private Button searchTypeButton;
	
	public Student returnStudent;
	
	@FXML
	public void initialize() {
		searchTypeButton.setVisible(false);
    }
	
	@FXML
	private void searchViewerDoubleClicked(MouseEvent event) {
		if (event.getClickCount() >= 2 && !searchView.getSelectionModel().isEmpty()) {
			returnStudent = searchView.getSelectionModel().getSelectedItem();
		}
	}
}
