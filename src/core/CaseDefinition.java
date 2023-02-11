package core;

import java.io.Serializable;

import application.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.StageStyle;

public class CaseDefinition extends CaseQuestion implements Serializable {

	private static final long serialVersionUID = -7525056124208573027L;
	
	public static void addTextLimiter(final TextField tf, final int maxLength) {
	    tf.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	            if (tf.getText().length() > maxLength) {
	                String s = tf.getText().substring(0, maxLength);
	                tf.setText(s);
	            }
	        }

	    });
	}
	@Override
	public void jouer(Partie p) {

		p.setLancer(false);// on arrete le lancer

		Question q = p.lireQuestion();
		this.setQuestion(q);

		// champs mot
		String input = "";

		// boite de dialogue

		// create a text input dialog
		TextInputDialog td = new TextInputDialog();

		td.getDialogPane().setMaxWidth(500);
		td.getEditor().setText("");
		addTextLimiter(td.getEditor(),q.getMot().length());
		td.setTitle("Case Question: Definition");
		td.setHeaderText("Donner le mot de cette definition: \n \n" + q.getDefinition()+".\n\n Le mot contient "+q.getMot().length()+" charactères.");
		td.setContentText("Entrer le mot");
		td.showAndWait();
		input = td.getEditor().getText();

		p.setScore(p.bonusMalus(this.bonusMalus(input)));
		int steps = this.avancerReculer(input);
		if (steps > 0) {
			// bonne reponse
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.initStyle(StageStyle.UTILITY);
			alert.setTitle("Bonne reponse!");
			alert.setHeaderText(null);
			alert.setContentText(
					"Bonne reponse! \n Vous venez de ganger 20 points! \n Vous devez avancer de 4 cases \n Allez vers la case: "
							+ Main.getP().parcourire(steps));
			alert.showAndWait();
			p.getQuestions().remove(q);// Pour ne pas repeter les mêmes questions
			p.setCurrentcase(p.parcourire(steps));
		} else {
			// Mauvaise reponse

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.initStyle(StageStyle.UTILITY);
			alert.setTitle("Mauvaise reponse!");
			alert.setHeaderText(null);
			alert.setContentText("Mauvaise reponse! \n Vous venez de perdre 10 points! \n Lancer de nouveau les des");
			alert.showAndWait();
			q = p.lireQuestion();
			this.setQuestion(q);

			

			// lancer

			p.setLancer(true);// pour lancer le de

		}
	}

	public CaseDefinition() {
		super.setColor("bleu");
	}

	@Override
	public int bonusMalus() {
		return -10;
	}

	@Override
	public int avancerReculer() {
		return 0;
	}

	public int avancerReculer(String inputMot) {
		if (this.evaluate(inputMot)) {
			return 4;
		} else
			return avancerReculer();
	}

	public int bonusMalus(String inputMot) {
		if (this.evaluate(inputMot)) {
			return 20;
		} else
			return bonusMalus();
	}

	@Override
	public boolean evaluate(String inputMot) {
		return question.getMot().equalsIgnoreCase(inputMot);
	}

}
