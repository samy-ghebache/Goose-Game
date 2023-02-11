package core;

import java.io.Serializable;

public class CaseDepart extends Case implements Serializable {

	private static final long serialVersionUID = -2858398857785768590L;

	@Override
	public void jouer(Partie p) {

		p.setLancer(true);// pour lancer le de
	}

	public CaseDepart() {
		super.setColor("jaune");
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
