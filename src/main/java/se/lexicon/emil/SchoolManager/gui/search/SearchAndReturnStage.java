package se.lexicon.emil.SchoolManager.gui.search;

import javafx.stage.Stage;
import se.lexicon.emil.SchoolManager.data.Student;

public class SearchAndReturnStage extends Stage {
	public Student showAndReturn(SearchAndReturnStudentController controll) {
		super.showAndWait();
		return controll.returnStudent;
	}
}
