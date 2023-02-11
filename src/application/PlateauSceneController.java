package application;

import java.io.FileNotFoundException;
import java.io.IOException;

import core.Grille;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PlateauSceneController {

	/*** ! @Pour_la_démonstration ! ***/
	private boolean demo = true;
	// true pour le mode de démonstration
	// mettre à false pour revenir vers le mode normale

	private Stage stage;
	private Scene scene;
	private Parent root;
	private Button[] grille;
	private Font textfont = Font.font("Courier New", FontWeight.MEDIUM, 20);

	@FXML
	private Label nom;
	@FXML
	private Label score;
	@FXML
	private Label currentCase;

	@FXML
	private Button jouerbutton;
	@FXML
	private Button suspendre;
	@FXML
	private Button lancer;
	@FXML
	private ImageView blankdice;
	@FXML
	private ImageView dice1;
	@FXML
	private ImageView dice2;

	public void controler(ActionEvent event) throws IOException {
		blankdice.setVisible(true);
		dice1.setVisible(false);
		dice2.setVisible(false);

		if (!jouerbutton.isVisible()) {
			if (Main.getP().isLancer()) {
				if (event.getSource() != lancer) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.initStyle(StageStyle.UTILITY);
					alert.setTitle("Operation Impossible");
					alert.setHeaderText(null);
					alert.setContentText("Vous devez lancer les dés");
					alert.showAndWait();
				} else {
					try {
						Roll();
						Main.getP().setLancer(false);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else {
				if (Main.getP().isGagner()) {
					switchToMenu(event);
				} else {
					for (int i = 0; i < this.grille.length; i++) {
						if (this.grille[i] == event.getSource()) {
							int check = Integer.parseInt(this.grille[i].getText());
							if (check == Main.getP().getCurrentcase()
									|| (demo && Main.getP().getpGrille().getGrille()[i].caseSpecial())) {
								// Main.getP().getpGrille().getGrille()[Main.getP().getCurrentcase() -
								// 1].jouer(Main.getP());
								Main.getP().setCurrentcase(check);
								setCase();
								Main.getP().getpGrille().getGrille()[check - 1].jouer(Main.getP());
								setScore();
							} else {
								Alert alert = new Alert(Alert.AlertType.INFORMATION);
								alert.initStyle(StageStyle.UTILITY);
								alert.setTitle("Parcourire");
								alert.setHeaderText(null);
								alert.setContentText("Vous devez aller vers la case : " + Main.getP().getCurrentcase());
								alert.showAndWait();
							}
						}
					}
				}

			}
		} else {
			if (event.getSource() == jouerbutton) {
				jouerbutton.setVisible(false);
				lancer.setDefaultButton(true);
				Main.getP().setLancer(true);
			} else {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.initStyle(StageStyle.UTILITY);
				alert.setTitle("Operation Impossible");
				alert.setHeaderText(null);
				alert.setContentText("Vous devez cliquer sur jouer la partie d'abord!");
				alert.showAndWait();
				lancer.setDisable(false);
				suspendre.setDisable(false);
				;
			}
		}
		if (!Main.getP().isLancer()) {
			lancer.setDisable(true);
			suspendre.setDisable(true);
		} else {
			lancer.setDisable(false);
			suspendre.setDisable(false);
		}
	}

	public void Roll() throws IOException {
		blankdice.setVisible(false);
		int number = Main.getP().getDe().lancer();
		int result = number;
		Dice(dice1, number);
		number = Main.getP().getDe().lancer();
		result += number;
		Dice(dice2, number);
		dice1.setVisible(true);
		dice2.setVisible(true);
		diceAlert(result);
		Main.getP().setCurrentcase(Main.getP().parcourire(result));
	}

	public void Dice(ImageView dice, int number) {
		switch (number) {
		case 1:
			dice.setImage(new Image(getClass().getResourceAsStream("Dice1.png")));
			break;
		case 2:
			dice.setImage(new Image(getClass().getResourceAsStream("Dice2.png")));
			break;
		case 3:
			dice.setImage(new Image(getClass().getResourceAsStream("Dice3.png")));
			break;
		case 4:
			dice.setImage(new Image(getClass().getResourceAsStream("Dice4.png")));
			break;
		case 5:
			dice.setImage(new Image(getClass().getResourceAsStream("Dice5.png")));
			break;
		case 6:
			dice.setImage(new Image(getClass().getResourceAsStream("Dice6.png")));
			break;
		}
	}

	public void diceAlert(int number) {
		Alert t = new Alert(AlertType.INFORMATION);
		t.setContentText(
				"Vous devez avancer de " + number + " cases.\n Allez vers la case: " + Main.getP().parcourire(number));
		t.setTitle("Lancer du Dé");
		t.setGraphic(new ImageView(getClass().getResource("dicealert.png").toString()));
		t.setHeaderText("Résultat du lancer");
		t.show();
	}

	public void setButtons() {
		jouerbutton.setFont(textfont);
		jouerbutton.setStyle(
				"    -fx-background-color: linear-gradient(#ffd65b, #e68400),    linear-gradient(#ffef84, #f2ba44),linear-gradient(#ffea6a, #efaa22),linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));-fx-background-radius: 30;-fx-background-insets: 0,1,2,3,0;-fx-text-fill: #654b00;-fx-font-weight: bold;-fx-font-size: 14px;-fx-padding: 10 20 10 20;");
		suspendre.setFont(textfont);
		suspendre.setStyle("    -fx-padding: 8 15 15 15;\n"
				+ "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" + "    -fx-background-radius: 8;\n"
				+ "    -fx-background-color: \n"
				+ "        linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%),\n" + "        #9d4024,\n"
				+ "        #d86e3a,\n" + "        radial-gradient(center 50% 50%, radius 100%, #d86e3a, #c54e2c);\n"
				+ "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n"
				+ "    -fx-font-weight: bold;\n" + "    -fx-font-size: 1.1em;");

		lancer.setFont(textfont);
		lancer.setStyle("    -fx-background-color: \n" + "        #ecebe9,\n" + "        rgba(0,0,0,0.05),\n"
				+ "        linear-gradient(#dcca8a, #c7a740),\n"
				+ "        linear-gradient(#f9f2d6 0%, #f4e5bc 20%, #e6c75d 80%, #e2c045 100%),\n"
				+ "        linear-gradient(#f6ebbe, #e6c34d);\n" + "    -fx-background-insets: 0,9 9 8 9,9,10,11;\n"
				+ "    -fx-background-radius: 50;\n" + "    -fx-padding: 15 30 15 30;\n"
				+ "    -fx-font-family: \"Helvetica\";\n" + "    -fx-font-size: 18px;\n"
				+ "    -fx-text-fill: #311c09;\n"
				+ "    -fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.1) , 2, 0.0 , 0 , 1);");
	}

	public void setNom() {

		nom.setText("Joueur: " + Main.getJ().getNom());
		nom.setFont(textfont);
	}

	public void setScore() {
		score.setText("Score: " + Main.getP().getScore());
		score.setFont(textfont);
	}

	public void setCase() {
		currentCase.setText("Case courante: " + Main.getP().getCurrentcase());
		currentCase.setFont(textfont);
	}

	public void setDices() {
		blankdice.setImage(new Image(getClass().getResourceAsStream("dice.png")));
	}

	public void suspendreButton(ActionEvent event) throws IOException {

		Main.getJeu().suspendrePartie(Main.getP());

		FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));

		root = loader.load();

		MenuSceneController menuSceneController = loader.getController();

		menuSceneController.setName();

		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	public void grille(GridPane gp) {
		int number = 1;
		int column = 0;
		int row = 0;
		int columlimitright = 16;
		int lignebottom = 16;
		int columnlimitleft = 3;
		int lignehight = 3;
		double r = 26;
		Button[] button = new Button[100];
		Font font = Font.font("Courier New", FontWeight.BOLD, 15);
		while (number <= 100) {
			for (int j = column; j < columlimitright && number <= 100; j++) {
				Button b = new Button(String.valueOf(number));
				button[number - 1] = b;
				if (number == 100)
					button[number - 1].setStyle("-fx-text-fill: #ffffff ;-fx-border-color:#000000;-fx-border-width:3;");
				else
					b.setStyle("-fx-text-fill: #000000 ;-fx-border-color:#000000;-fx-border-width:3;");
				b.setShape(new Circle(r));
				b.setFont(font);
				b.setMinSize(2 * r, 2 * r);
				b.setMaxSize(2 * r, 2 * r);

				b.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						try {
							controler(e);
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				});

				number++;
				gp.add(b, j, row);
			}
			for (int i = row; i < lignebottom && number <= 100; i++) {
				Button b = new Button(String.valueOf(number));
				button[number - 1] = b;
				b.setShape(new Circle(r));
				b.setMinSize(2 * r, 2 * r);
				b.setMaxSize(2 * r, 2 * r);
				b.setMaxSize(100, 200);
				if (number == 100)
					button[number - 1].setStyle("-fx-text-fill: #ffffff ;-fx-border-color:#000000;-fx-border-width:3;");
				else
					b.setStyle("-fx-text-fill: #000000 ;-fx-border-color:#000000;-fx-border-width:3;");
				b.setFont(font);

				b.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						try {
							controler(e);
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				});

				number++;
				gp.add(b, columlimitright, i);
			}
			for (int j = columlimitright; j > columnlimitleft && number <= 100; j--) {
				Button b = new Button(String.valueOf(number));
				button[number - 1] = b;
				b.setShape(new Circle(r));
				b.setMinSize(2 * r, 2 * r);
				b.setMaxSize(2 * r, 2 * r);
				b.setMaxSize(100, 200);
				if (number == 100)
					button[number - 1].setStyle("-fx-text-fill: #ffffff ;-fx-border-color:#000000;-fx-border-width:3;");
				else
					b.setStyle("-fx-text-fill: #000000 ;-fx-border-color:#000000;-fx-border-width:3;");
				b.setFont(font);

				b.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						try {
							controler(e);
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				});

				number++;
				gp.add(b, j, lignebottom);
			}
			for (int i = lignebottom; i > lignehight && number <= 100; i--) {
				Button b = new Button(String.valueOf(number));
				button[number - 1] = b;
				b.setShape(new Circle(r));
				b.setMinSize(2 * r, 2 * r);
				b.setMaxSize(2 * r, 2 * r);
				b.setMaxSize(100, 200);
				if (number == 100)
					button[number - 1].setStyle("-fx-text-fill: #ffffff ;-fx-border-color:#000000;-fx-border-width:3;");
				else
					b.setStyle("-fx-text-fill: #000000 ;-fx-border-color:#000000;-fx-border-width:3;");
				b.setFont(font);
				b.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						try {
							controler(e);
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				});
				number++;
				gp.add(b, columnlimitleft, i);
			}
			row = lignehight;
			column = columnlimitleft;
			columlimitright -= 3;
			lignebottom -= 3;
			lignehight += 3;
			columnlimitleft += 3;
		}

		gp.setAlignment(Pos.CENTER);
		gp.setPadding(new Insets(50, 50, 50, 50));
		gp.setVgap(-8);
		(new Grille()).CreerGrille(button);
		this.grille = button;

	}

	public void switchToMenu(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));

		root = loader.load();

		MenuSceneController menuSceneController = loader.getController();

		menuSceneController.setName();

		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	// Getters and setters

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public Parent getRoot() {
		return root;
	}

	public void setRoot(Parent root) {
		this.root = root;
	}

	public Button[] getGrille() {
		return grille;
	}

	public void setGrille(Button[] grille) {
		this.grille = grille;
	}

	public Label getNom() {
		return nom;
	}

	public void setNom(Label nom) {
		this.nom = nom;
	}

	public Label getScore() {
		return score;
	}

	public void setScore(Label score) {
		this.score = score;
	}

	public Label getCurrentCase() {
		return currentCase;
	}

	public void setCurrentCase(Label currentCase) {
		this.currentCase = currentCase;
	}

	public Button getJouerbutton() {
		return jouerbutton;
	}

	public void setJouerbutton(Button jouerbutton) {
		this.jouerbutton = jouerbutton;
	}

	public ImageView getBlankdice() {
		return blankdice;
	}

	public void setBlankdice(ImageView blankdice) {
		this.blankdice = blankdice;
	}

	public ImageView getDice1() {
		return dice1;
	}

	public void setDice1(ImageView dice1) {
		this.dice1 = dice1;
	}

	public ImageView getDice2() {
		return dice2;
	}

	public void setDice2(ImageView dice2) {
		this.dice2 = dice2;
	}

	public Button getLancer() {
		return lancer;
	}

	public void setLancer(Button lancer) {
		this.lancer = lancer;
	}

	public Font getTextfont() {
		return textfont;
	}

	public void setTextfont(Font textfont) {
		this.textfont = textfont;
	}

	public Button getSuspendre() {
		return suspendre;
	}

	public void setSuspendre(Button suspendre) {
		this.suspendre = suspendre;
	}

	public boolean isDemo() {
		return demo;
	}

	public void setDemo(boolean demo) {
		this.demo = demo;
	}

}
