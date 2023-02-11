package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MenuSceneController {

	private Stage stage;
	private Scene scene;
	private Parent root;

	@FXML
	private Label nameLabel;

	public void setName() {

		nameLabel.setText(Main.getJ().getNom());

	}

	public void nouvellePartie(ActionEvent event) throws IOException {

		Main.setP(Main.getJeu().creerNouvPartie(Main.getJ()));

		FXMLLoader loader = new FXMLLoader(getClass().getResource("Plateau.fxml"));

		root = loader.load();

		PlateauSceneController plateauSceneController = loader.getController();

		plateauSceneController.setNom();
		plateauSceneController.setScore();
		plateauSceneController.setCase();
		plateauSceneController.setDices();
		plateauSceneController.setButtons();
		plateauSceneController.getLancer().setDisable(true);

		GridPane gp = new GridPane();
		plateauSceneController.grille(gp);

		HBox hb = new HBox();
		hb.getChildren().add(root);
		hb.getChildren().add(gp);

		hb.setAlignment(Pos.CENTER);
		hb.setSpacing(10);
		hb.setPadding(new Insets(20, 20, 20, 20));

		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene sc = new Scene(hb);
		stage.setScene(sc);
		stage.setFullScreen(true);
		stage.show();

	}

	public void reprendrePartie(ActionEvent event) throws IOException {
		Main.setP(Main.getJeu().reprendrePartie(Main.getJ()));
		if (Main.getP().getCurrentcase() == 100) {
			Main.getP().setCurrentcase(1);
			Main.getP().setScore(0);
			Main.getP().setGagner(false);
		}

		FXMLLoader loader = new FXMLLoader(getClass().getResource("Plateau.fxml"));

		root = loader.load();

		PlateauSceneController plateauSceneController = loader.getController();

		plateauSceneController.setNom();
		plateauSceneController.setScore();
		plateauSceneController.setCase();
		plateauSceneController.setDices();
		plateauSceneController.setButtons();
		plateauSceneController.getLancer().setDisable(true);

		GridPane gp = new GridPane();
		plateauSceneController.grille(gp);

		HBox hb = new HBox();
		hb.getChildren().add(root);
		hb.getChildren().add(gp);

		hb.setAlignment(Pos.CENTER);
		hb.setSpacing(10);
		hb.setPadding(new Insets(20, 20, 20, 20));

		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene sc = new Scene(hb);
		stage.setScene(sc);
		stage.setFullScreen(true);
		stage.show();

	}

	public void seDeconnecter(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Sidentifier.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}