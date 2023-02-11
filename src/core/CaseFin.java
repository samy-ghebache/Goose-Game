package core;

import java.io.Serializable;

import application.Main;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

public class CaseFin extends Case implements Serializable {

	private static final long serialVersionUID = 3031538403266479916L;

	@Override
	public void jouer(Partie p) {

		p.setLancer(false);
		p.setGagner(true);

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.initStyle(StageStyle.UTILITY);
		alert.setTitle("Félicitation!");
		alert.setHeaderText(null);
		alert.setContentText("Félicitation! \n Vous avez gagné le jeu! \n Votre score: " + p.getScore());
		alert.showAndWait();

		Main.getJeu().record(Main.getP());
	}

	public CaseFin() {
		super.setColor("noire");
	}

	@Override
	public int bonusMalus() {
		return 0;
	}

	@Override
	public int avancerReculer() {
		return 0;
	}

}
