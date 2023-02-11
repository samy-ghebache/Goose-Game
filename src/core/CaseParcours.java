package core;

import java.io.Serializable;

import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

public class CaseParcours extends Case implements Serializable {

	private static final long serialVersionUID = 2185501712355918018L;

	@Override
	public void jouer(Partie p) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.initStyle(StageStyle.UTILITY);
		alert.setTitle("Parcours");
		alert.setHeaderText(null);
		alert.setContentText("Vous avez tombe sur une case de parcours, vous devez lancer les dés");
		alert.showAndWait();

		p.setLancer(true);// pour lancer le de
	}

	public CaseParcours() {
		super.setColor(null);
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
