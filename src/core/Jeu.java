package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

public class Jeu {
	private HashMap<String, Partie> joueursParties;// Les parties suspendues
	private HashSet<Question> questions = new HashSet<Question>();// chargees du fichier

	public Jeu() {
		LoadJoueursPartie();
		LoadQuestions();
	}

	public Joueur identifier(String nom) {
		Joueur j = new Joueur(nom);
		if (joueursParties == null) {
			joueursParties = new HashMap<String, Partie>();
		}
		if (joueursParties.containsKey(nom)) {
			if (joueursParties.get(nom) != null)
				return joueursParties.get(nom).getJoueur();
		}
		joueursParties.put(nom, null);
		return j;
	}

	public void record(Partie p) {
		if (p == null || joueursParties == null || !joueursParties.containsKey(p.getJoueur().getNom())) {
			System.out.println("Vous devez s'identifier avant de commencer le jeu");
			return;
		}
		if (p.getScore() > p.getJoueur().getScore()) {
			p.getJoueur().setScore(p.getScore());
			// Alerte: Vous avez battu votre score!
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.initStyle(StageStyle.UTILITY);
			alert.setTitle("Score Batu!");
			alert.setHeaderText(null);
			alert.setContentText(
					"Vous avez battu votre score! \n Maintenant Votre Meilleur Score est: " + p.getJoueur().getScore());
			alert.showAndWait();
		} else {
			// Alerte: Vous avez battu votre score!
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.initStyle(StageStyle.UTILITY);
			alert.setTitle("Meilleur Score");
			alert.setHeaderText(null);
			alert.setContentText("Votre Meilleur Score est: " + p.getJoueur().getScore());
			alert.showAndWait();
		}

		suspendrePartie(p);

		Joueur thepro = meilleurJoueur();

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.initStyle(StageStyle.UTILITY);
		alert.setTitle("Record à Battre");
		alert.setHeaderText(null);
		alert.setContentText(
				"Meilleur Score atteint dans le jeu est: " + thepro.getScore() + "\n Par: " + thepro.getNom());
		alert.showAndWait();
	}

	public Joueur meilleurJoueur() {
		if (joueursParties == null) {
			return new Joueur("Aucun Joueur");
		}
		int max = 0;
		Joueur thepro = null;
		for (Map.Entry<String, Partie> entry : joueursParties.entrySet()) {
			if (entry.getValue() != null) {
				if (entry.getValue().getScore() >= max) {
					max = entry.getValue().getScore();
					thepro = entry.getValue().getJoueur();
				}
			}
		}
		if (thepro == null)
			thepro = new Joueur("Aucun Joueur");
		return thepro;
	}

	public void suspendrePartie(Partie p) {
		p.setSuspendre(true);
		if (joueursParties == null)
			joueursParties = new HashMap<String, Partie>();
		joueursParties.put(p.getJoueur().getNom(), p);
		SauvegarderJoueursParties();
	}

	public Partie creerNouvPartie(Joueur j) {
		return (new Partie(j, questions));
	}

	public Partie reprendrePartie(Joueur j)// on peut executer directement p.Jouer();
	{
		if (joueursParties == null)
			return new Partie(j, questions);
		if (joueursParties.containsKey(j.getNom())) {
			if (joueursParties.get(j.getNom()) != null) {
				joueursParties.get(j.getNom()).setSuspendre(false);
				return joueursParties.get(j.getNom());
			}
		}
		return new Partie(j, questions);
	}

	public void SauvegarderJoueursParties() // On doit ecrire dans le fichier PartieJoueur
	{
		if (joueursParties != null) {
			try {
				FileOutputStream fo = new FileOutputStream(new File("JoueursParties.txt"), false);
				ObjectOutputStream oo = new ObjectOutputStream(fo);
				// ecriture des parties dans le fichier
				oo.writeObject(joueursParties);
				// Fermer le flux
				oo.close();
				// Fermer le fichier
				fo.close();
			} catch (IOException e) {
				System.out.println("Error initializing stream");
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, Partie> LoadJoueursPartie() {
		try {
			FileInputStream fi = new FileInputStream(new File("JoueursParties.txt"));
			joueursParties = new HashMap<String, Partie>();
			if (fi.available() > 0) {
				ObjectInputStream oi = new ObjectInputStream(fi);
				joueursParties = (HashMap<String, Partie>) oi.readObject();
				oi.close();
			}
			fi.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error initializing stream");
			e.printStackTrace();
		}
		return joueursParties;
	}

	public HashSet<Question> LoadQuestions()
	// Elle ajoute les mots/definitions/images aux elements correspondants de la
	// grille
	{
		String line = "";
		String splitBy = "/";
		BufferedReader br = null;
		try {
			// a CSV file into BufferedReader class constructor
			br = new BufferedReader(new FileReader("CSVDictionnaire.csv"));
			while ((line = br.readLine()) != null) // returns a Boolean value
			{
				Question question = new Question();
				String[] ligneQuestion = line.split(splitBy); // use comma as separator
				question.setMot(ligneQuestion[0]);
				question.setDefinition(ligneQuestion[1]);
				question.setImagePath(ligneQuestion[0] + ".png");
				questions.add(question);
			}
			if (br != null)
				br.close();

		} catch (IOException e) {
			System.out.println("Error initializing stream");
			e.printStackTrace();
		}
		return questions;
	}

	// Getters and setters
	public HashMap<String, Partie> getJoueursParties() {
		return joueursParties;
	}

	public void setJoueursParties(HashMap<String, Partie> joueursParties) {
		this.joueursParties = joueursParties;
	}

	public HashSet<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(HashSet<Question> questions) {
		this.questions = questions;
	}
}
