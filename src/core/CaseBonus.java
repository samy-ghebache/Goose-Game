package core;

import java.io.Serializable;

import application.Main;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

public class CaseBonus extends Case implements Serializable {
	private static final long serialVersionUID = -2366489661252662922L;

	public CaseBonus() {
		super.setColor("verte");
	}

	@Override
	public int bonusMalus() {
		return 10;
	}

	@Override
	public int avancerReculer() {
		return 2;
	}

	@Override
	public void jouer(Partie p) {

		// alerte bonus
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.initStyle(StageStyle.UTILITY);
		alert.setTitle("Bonus");
		alert.setHeaderText(null);
		alert.setContentText(
				"Bonus! \n Vous venez de ganger 10 points! \n Vous devez avancer de 2 cases \n Allez vers la case: "
						+ Main.getP().parcourire(this.avancerReculer()));
		alert.showAndWait();

		p.setLancer(false);// on arrete le lancer
		p.setScore(p.bonusMalus(this.bonusMalus()));
		p.setCurrentcase(p.parcourire(this.avancerReculer()));
	}

}
