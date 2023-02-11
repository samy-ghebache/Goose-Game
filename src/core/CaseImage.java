package core;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Random;

import application.ImageQuestionDialog;
import application.Main;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;

public class CaseImage extends CaseQuestion implements Serializable {

	private static final long serialVersionUID = 4122074602900264876L;

	@Override
	public void jouer(Partie p) {

		p.setLancer(false);// on arrete le lancer

		Question[] tQ = p.lire4Questions();
		if (tQ == null) {// Exception handling
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erreur");
			alert.setHeaderText("Erreur!");
			alert.setContentText("Vous avez atteint la limite des questions!");
			alert.showAndWait();

		} else {

			int qstAleat = new Random().nextInt(4);// Pour gnérer une question alatoire
			this.setQuestion(tQ[qstAleat]);

			String input = "";

			try {
				input = ImageQuestionDialog.display(tQ, qstAleat);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			int steps = this.avancerReculer(input);

			if (this.avancerReculer(input) > 0) {
				// Bonne reponse
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.initStyle(StageStyle.UTILITY);
				alert.setTitle("Bonne reponse!");
				alert.setHeaderText(null);
				alert.setContentText(
						"Bonne reponse! \n Vous venez de ganger 10 points! \n Vous devez avancer de 2 cases \n Allez vers la case: "
								+ Main.getP().parcourire(steps));
				alert.showAndWait();
				p.setScore(p.bonusMalus(this.bonusMalus(input)));

				p.getQuestions().remove(tQ[qstAleat]);// Pour ne pas rpéter les mêmes questions
				p.setCurrentcase(p.parcourire(steps));

			} else {
				// Mauvaise reponse
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.initStyle(StageStyle.UTILITY);
				alert.setTitle("Mauvaise reponse!");
				alert.setHeaderText(null);
				alert.setContentText("Mauvaise reponse! \n Lancer de nouveau les des");
				alert.showAndWait();

				p.setLancer(true);// pour lancer le de
			}
		}
	}

	public CaseImage() {
		super.setColor("rose");
	}

	@Override
	public int bonusMalus() {
		return 0;
	}

	@Override
	public int avancerReculer() {
		return 0;
	}

	public int bonusMalus(String InputImagePath) {
		if (evaluate(InputImagePath)) {
			return 10;
		} else
			return bonusMalus();
	}

	public int avancerReculer(String InputImagePath) {
		if (evaluate(InputImagePath)) {
			return 2;
		} else
			return avancerReculer();
	}

	@Override
	public boolean evaluate(String inputImagePath) {
		return (inputImagePath.equalsIgnoreCase(question.getImagePath()));
	}

}
