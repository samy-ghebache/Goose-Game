package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SidentifierSceneController {

	@FXML

	TextField nameTextField;

	private Stage stage;
	private Scene scene;
	private Parent root;

	public void sidentifier(ActionEvent event) throws IOException {

		String username = nameTextField.getText();

		Main.setJ(Main.getJeu().identifier(username));

		FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));

		root = loader.load();

		MenuSceneController menuSceneController = loader.getController();

		menuSceneController.setName();

		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
