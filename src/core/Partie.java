package core;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class Partie implements Serializable {
	private static final long serialVersionUID = 3479573880949232524L;
	private Grille pGrille;// Plateau
	private De de;
	private Joueur joueur;
	private int score;// score du joueur dans la partie
	private int currentcase;
	private HashSet<Question> questions;
	boolean suspendre;
	boolean gagner;
	boolean lancer;

	public Partie(Joueur joueur, HashSet<Question> questions)// creer une nouvelle partie
	{
		this.joueur = joueur;
		score = 0;
		currentcase = 1;
		de = new De();
		this.questions = questions;
	}

	public int bonusMalus(int points) {
		score += points;
		if (score < 0)
			score = 0;
		return score;
	}

	public int parcourire(int pas) {
		if (currentcase + pas < 1)
			return 1;
		// si le joueur tire au de un nombre qui le fait depasser la case fin
		if (currentcase + pas > 100)
			return 200 - currentcase - pas;// 100-(currentcase+pas-100)
		return currentcase + pas;
	}

	public Question lireQuestion() {// generer la question d'une maniére aléatoire
		Iterator<Question> it = questions.iterator();
		Random rand = new Random();
		Question q = null;
		int number = rand.nextInt(questions.size()) + 1;
		while (it.hasNext() && number > 0) {
			q = it.next();
			number--;
		}
		return q;
	}

	Question[] lire4Questions() {
		// convert questions HashSet to an array
		Question[] arrayQuestions = questions.toArray(new Question[questions.size()]);
		HashSet<Question> set = new HashSet<Question>(); // desired 4questions set
		if (arrayQuestions.length<4)
			return null;
		while (set.size() < 4) {
			if (questions.size() == 0)
				break;// insufficient number of qst
			// generate a random number
			Random random = new Random();
			// this will generate a random index between 0 and arrayQuestions.length
			int randomIndex = random.nextInt(arrayQuestions.length);
			set.add(arrayQuestions[randomIndex]);
		}
		if (set.size() == 4)
			return set.toArray(new Question[4]);
		else
			return null;// insufficient number of qst

	}
	// Getters and setters

	public Grille getpGrille() {
		return pGrille;
	}

	public void setpGrille(Grille pGrille) {
		this.pGrille = pGrille;
	}

	public De getDe() {
		return de;
	}

	public void setDe(De de) {
		this.de = de;
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getCurrentcase() {
		return currentcase;
	}

	public void setCurrentcase(int currentcase) {
		this.currentcase = currentcase;
	}

	public HashSet<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(HashSet<Question> questions) {
		this.questions = questions;
	}

	public boolean isSuspendre() {
		return suspendre;
	}

	public void setSuspendre(boolean suspendre) {
		this.suspendre = suspendre;
	}

	public boolean isGagner() {
		return gagner;
	}

	public void setGagner(boolean gagner) {
		this.gagner = gagner;
	}

	public boolean isLancer() {
		return lancer;
	}

	public void setLancer(boolean lancer) {
		this.lancer = lancer;
	}

}
