package core;

import java.io.Serializable;

public class Joueur implements Serializable {
	private static final long serialVersionUID = -7821570625094532724L;
	private String nom;
	private int meilleurscore;

	public Joueur(String nom) {
		this.nom = nom;
		this.meilleurscore = 0;
	}

	@Override
	public boolean equals(Object obj) {
		return nom.equals(((Joueur) obj).getNom());
	}

	@Override
	public int hashCode() {
		return nom.hashCode();
	}
	//Getters
	public String getNom() {
		return nom;
	}

	public int getScore() {
		return meilleurscore;
	}

	public void setScore(int score) {
		this.meilleurscore = score;
	}

}
