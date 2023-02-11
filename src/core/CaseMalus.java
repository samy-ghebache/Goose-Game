package core;

import java.io.Serializable;

import application.Main;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

public class CaseMalus extends Case implements Serializable {

	private static final long serialVersionUID = 2133343499241704087L;

	@Override
	public void jouer(Partie p) {

		// Alerte Malus
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.initStyle(StageStyle.UTILITY);
		alert.setTitle("Malus");
		alert.setHeaderText(null);
		alert.setContentText("Malus! \n Vous venez de perdre 10 points! \n  \n Allez vers la case: "
				+ Main.getP().parcourire(this.avancerReculer()));
		alert.showAndWait();

		p.setLancer(false);
		p.setScore(p.bonusMalus(this.bonusMalus()));
		p.setCurrentcase(p.parcourire(this.avancerReculer()));
	}

	public CaseMalus() {
		super.setColor("rouge");
	}

	@Override
	public int bonusMalus() {
		return -10;
	}

	@Override
	public int avancerReculer() {
		return -2;
	}

}
