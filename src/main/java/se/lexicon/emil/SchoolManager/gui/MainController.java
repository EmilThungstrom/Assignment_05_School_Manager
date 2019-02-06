package se.lexicon.emil.SchoolManager.gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se.lexicon.emil.SchoolManager.App;

public class MainController {

	@FXML
	private void studentButtonPressed() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("student/Student.fxml"));
		App.getPrimaryStage().setScene(new Scene(root));
	}

	@FXML
	private void courseButtonPressed() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("course/Course.fxml"));
		App.getPrimaryStage().setScene(new Scene(root));
	}

	@FXML
	private void searchButtonPressed() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("search/SearchStudent.fxml"));
		App.getPrimaryStage().setScene(new Scene(root));
	}
}
