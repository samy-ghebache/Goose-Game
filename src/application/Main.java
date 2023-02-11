package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;

import core.Grille;
import core.Jeu;
import core.Joueur;
import core.Partie;
import core.Question;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Jeu de L'Oie: Vocabulaire de la langue anglaise");
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//Jeu, Joueur, Partie sont des attributs statiques 
	//puisque on a un seul jeu
	//un seul joueur dans un jeu
	//une seule partie pour un joueur
	private static Jeu jeu = new Jeu();
	private static Joueur j = new Joueur("Vide");
	private static Partie p = new Partie(j, new HashSet<Question>());

	public static void main(String[] args) throws FileNotFoundException, IOException {
		launch(args);
	}

	// getters and setters

	public static Jeu getJeu() {
		return jeu;
	}

	public static void setJeu(Jeu jeu) {
		Main.jeu = jeu;
	}

	public static Partie getP() {
		return p;
	}

	public static void setP(Partie p) {
		Main.p = p;
	}

	public static Joueur getJ() {
		return j;
	}

	public static void setJ(Joueur j) {
		Main.j = j;
	}

	public static void setG(Grille g) {
		getP().setpGrille(g);
	}

}
