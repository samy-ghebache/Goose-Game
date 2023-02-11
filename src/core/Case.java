package core;

import java.io.Serializable;

public abstract class Case implements Serializable {
	private static final long serialVersionUID = 2241791581543196645L;
	private int number;
	private String color;

	public abstract int bonusMalus();

	public abstract int avancerReculer();

	public abstract void jouer(Partie p);

	/** Pour @Démonstration **/
	public boolean caseSpecial() {
		String coul = this.getColor();
		if (coul != "noir" && coul != "jaune" && coul != null) {
			return true;
		} else
			return false;
	}

	// getters and setters

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

}
