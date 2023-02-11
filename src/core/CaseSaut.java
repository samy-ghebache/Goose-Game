package core;

import java.io.Serializable;
import java.util.Random;

import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

public class CaseSaut extends Case implements Serializable {

	private static final long serialVersionUID = 7581468130720793445L;

	@Override
	public void jouer(Partie p) {

		p.setLancer(false);// on arrete le lancer

		int nouvcase = this.avancerReculer();

		// Alerte Saut
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.initStyle(StageStyle.UTILITY);
		alert.setTitle("Saut");
		alert.setHeaderText(null);
		alert.setContentText("Case saut! \n Vous devez aller vers la case: " + nouvcase);
		alert.showAndWait();

		p.setCurrentcase(nouvcase);
	}

	public CaseSaut() {
		super.setColor("orange");
	}

	@Override
	public int bonusMalus() {
		return 0;
	}

	@Override
	public int avancerReculer() {
		return ((new Random()).nextInt(100) + 1);// return le numéro de la case qu'il doit aller vers
	}

}
