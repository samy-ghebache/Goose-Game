package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import core.Question;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ImageQuestionDialog {

	private static String reponse;

	public static String display(Question[] tQ, int qstAleat) throws FileNotFoundException {
		reponse = "";
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);

		FileInputStream input1 = new FileInputStream("images_questions/"+tQ[0].getImagePath());
		Image image1 = new Image(input1);
		ImageView imageView1 = new ImageView(image1);
		Button button1 = new Button("", imageView1);

		FileInputStream input2 = new FileInputStream("images_questions/"+tQ[1].getImagePath());
		Image image2 = new Image(input2);
		ImageView imageView2 = new ImageView(image2);
		Button button2 = new Button("", imageView2);

		FileInputStream input3 = new FileInputStream("images_questions/"+tQ[2].getImagePath());
		Image image3 = new Image(input3);
		ImageView imageView3 = new ImageView(image3);
		Button button3 = new Button("", imageView3);

		FileInputStream input4 = new FileInputStream("images_questions/"+tQ[3].getImagePath());
		Image image4 = new Image(input4);
		ImageView imageView4 = new ImageView(image4);
		Button button4 = new Button("", imageView4);

		Button button = new Button("Valider la reponse");
		button.setFont(Font.font("Courier New", FontWeight.MEDIUM, 18));
		button.setOnAction(e -> {

			stage.close();
		});

		Border b = button1.getBorder();
		button1.setOnAction(e -> {
			reponse = tQ[0].getImagePath();

			button1.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
					new BorderWidths(1), Insets.EMPTY)));
			button2.setBorder(b);
			button3.setBorder(b);
			button4.setBorder(b);

		});
		button2.setOnAction(e -> {
			reponse = tQ[1].getImagePath();
			button2.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
					new BorderWidths(1), Insets.EMPTY)));
			button1.setBorder(b);
			button3.setBorder(b);
			button4.setBorder(b);

		});

		button3.setOnAction(e -> {
			reponse = tQ[2].getImagePath();
			button3.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
					new BorderWidths(1), Insets.EMPTY)));
			button1.setBorder(b);
			button2.setBorder(b);
			button4.setBorder(b);

		});
		button4.setOnAction(e -> {
			reponse = tQ[3].getImagePath();
			button4.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
					new BorderWidths(1), Insets.EMPTY)));
			button1.setBorder(b);
			button2.setBorder(b);
			button3.setBorder(b);

		});

		GridPane images = new GridPane();

		images.setPadding(new Insets(30, 30, 30, 30));
		images.setVgap(30);
		images.setHgap(30);

		images.add(button1, 0, 0);
		images.add(button2, 1, 0);
		images.add(button3, 0, 1);
		images.add(button4, 1, 1);

		Label question = new Label("Selectionner l'image du mot: " + tQ[qstAleat].getMot());
		question.setFont(Font.font("Courier New", FontWeight.MEDIUM, 18));

		VBox vb = new VBox();
		vb.setAlignment(Pos.CENTER);
		vb.setSpacing(16);
		vb.setPadding(new Insets(30, 20, 30, 20));
		vb.getChildren().add(question);
		vb.getChildren().add(images);
		vb.getChildren().add(button);

		Scene scene = new Scene(vb);
		stage.setTitle("Question: Image du mot");
		stage.setScene(scene);
		stage.showAndWait();

		return reponse;
	}
}