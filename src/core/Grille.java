package core;

import java.io.Serializable;
import java.util.Random;

import application.Main;
import javafx.scene.control.Button;

public class Grille implements Serializable {
	private static final long serialVersionUID = -8794280710922559705L;
	private Case[] grille;

	public Grille() {
		grille = new Case[100];
	}

	public void CreerGrille(Button[] button) {
		Random myrandom = new Random();

		// creer case depart
		grille[0] = new CaseDepart();
		button[0].setStyle(button[0].getStyle() + "-fx-background-color:#FFFF00;");
		grille[0].setNumber(1);

		// creer case fin
		grille[grille.length - 1] = new CaseFin();
		button[99].setStyle(button[button.length - 1].getStyle() + "-fx-background-color:#000000;");
		grille[grille.length - 1].setNumber(100);

		// 5 case de chaque type de cases speciales
		byte maxbonus, maxmalus, maxsaut, maxdefinition, maximage;
		maxbonus = maxmalus = maxsaut = maxdefinition = maximage = 5;

		// creer les cases
		boolean stop = false;
		while (!stop) {
			int typecase = myrandom.nextInt(5);
			int i = myrandom.nextInt(100);
			switch (typecase) {
			case 0:// case bonus
				if (maxbonus > 0 && grille[i] == null) // On a atteint la limite du jeu
				{
					if (i + 2 == grille.length || i + 2 < grille.length && grille[i + 2] != null
							&& grille[i + 2].getColor().equalsIgnoreCase("rouge"))
						break;// Dans le cas ou' la case i est de type bonus et i+2 et de type malus
					// i+1==grille.length ,pour eviter le cas ou' l'avant derniere soit de case
					// bonus ,sinon on tombe sur une boucle infinie(99-->101-->99..)
					grille[i] = new CaseBonus();
					grille[i].setNumber(i + 1);
					button[i].setStyle(button[i].getStyle() + "-fx-background-color:#42FF33;");
					maxbonus--;
				}
				break;
			case 1:// case malus
				if (maxmalus > 0 && grille[i] == null) {
					if (i - 2 >= 0 && grille[i - 2] != null
							&& (grille[i - 2].getColor().equalsIgnoreCase("verte")
									|| grille[i - 2].getColor().equalsIgnoreCase("rose")
									|| grille[i - 2].getColor().equalsIgnoreCase("bleu")))
						break;// Dans le cas ou' la case i est de type malus et i-2 et de type bonus
					grille[i] = new CaseMalus();
					grille[i].setNumber(i + 1);
					button[i].setStyle(button[i].getStyle() + "-fx-background-color:#FF3333;");
					maxmalus--;
				}
				break;
			case 2:// case saut
				if (maxsaut > 0 && grille[i] == null) {
					grille[i] = new CaseSaut();
					grille[i].setNumber(i + 1);
					button[i].setStyle(button[i].getStyle() + "-fx-background-color:#FF9333;");
					maxsaut--;
				}
				break;
			case 3:// case definition
				if (maxdefinition > 0 && grille[i] == null) {
					if (i + 3 == grille.length)// Boucle infinie si on l'insere e la position 98
						break;
					else {
						grille[i] = new CaseDefinition();
						grille[i].setNumber(i + 1);
						button[i].setStyle(button[i].getStyle() + "-fx-background-color:#33DAFF;");
						maxdefinition--;
					}
				}
				break;
			case 4:// case image
				if (maximage > 0 && grille[i] == null) {
					if (i + 2 == grille.length)// Boucle infinie si on l'insere e la position 99
						break;
					else if (grille[i + 2] != null) {
						if ((i + 2 < grille.length && (grille[i + 2].getColor().equalsIgnoreCase("rouge")))) {
							break;
						} else {
							grille[i] = new CaseImage();
							grille[i].setNumber(i + 1);
							button[i].setStyle(button[i].getStyle() + "-fx-background-color:#FF579C;");
							maximage--;
						}
					}
				}
				break;
			}
			if (maxbonus + maxmalus + maxsaut + maxdefinition + maximage == 0)
				stop = true;
		}

		// creation des cases de parcours
		for (int i = 0; i < grille.length; i++) {
			if (grille[i] == null) {
				grille[i] = new CaseParcours();
				grille[i].setNumber(i + 1);
				button[i].setStyle(button[i].getStyle() + "-fx-background-color:#FFFFFF;");
			}

		}
		this.setGrille(grille);
		Main.setG(this);
	}

	// Getters and setters

	public Case[] getGrille() {
		return grille;
	}

	public void setGrille(Case[] grille) {
		this.grille = grille;
	}

}
