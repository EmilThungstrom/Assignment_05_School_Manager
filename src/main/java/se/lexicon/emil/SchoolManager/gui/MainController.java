package se.lexicon.emil.SchoolManager.gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import se.lexicon.emil.SchoolManager.App;

public class MainController {
	
	@FXML
	private void StudentButtonPressed() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("student/Student.fxml"));
		Scene scene = new Scene(root);
		App.getPrimaryStage().setScene(scene);
	}
	@FXML
	private void CourseButtonPressed() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("course/Course.fxml"));
		Scene scene = new Scene(root);
		App.getPrimaryStage().setScene(scene);
	}
}
