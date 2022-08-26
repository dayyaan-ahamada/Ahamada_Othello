package GUI;

import java.io.InputStream;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class GameTypeMenu extends VBox{
	//attributes
	private Button solo = new Button("Solo");
	private Button multi = new Button("Multijoueur");
	private Button quitter = new Button("Quitter");
	private Button scores = new Button("Scores");
	
	//constructor
	public GameTypeMenu() {
		//general VBox options
		VBox box1 = new VBox();
		VBox box2 = new VBox();

		VBox.setVgrow(box1, Priority.ALWAYS);
		VBox.setVgrow(box2, Priority.ALWAYS);
		
		//Image VBox options
		Class<?> clazz = GameTypeMenu.class; 
		box1.setStyle("-fx-alignment:center");
		InputStream input = clazz.getResourceAsStream("../images/Othello.png");
		Image image = new Image(input);
		ImageView view = new ImageView(image);
		view.setPreserveRatio(true);
		view.fitWidthProperty().bind(this.widthProperty());
		box1.getChildren().addAll(view);
		
		//Buttons VBox options
		box2.setPrefHeight(600);
		box2.setSpacing(40);
		VBox.setVgrow(this.solo, Priority.ALWAYS);
		VBox.setVgrow(this.multi, Priority.ALWAYS);
		VBox.setVgrow(this.quitter, Priority.ALWAYS);
		VBox.setVgrow(this.scores, Priority.ALWAYS);

		this.solo.setMaxSize(300, 100);
		this.multi.setMaxSize(300, 100);
		this.quitter.setMaxSize(300, 100);
		this.scores.setMaxSize(300, 100);

		box2.getChildren().add(this.solo);
		box2.getChildren().add(this.multi);
		box2.getChildren().add(this.scores);
		box2.getChildren().add(this.quitter);

		box2.setStyle("-fx-alignment: center;");
		
		//ajout des VBox
		this.getChildren().add(box1);
		this.getChildren().add(box2);	
	}
	
	//getters
	public Button getSolo() {
		return this.solo;
	}
	public Button getMulti() {
		return this.multi;
	}
	public Button getQuitter() {
		return this.quitter;
	}
	public Button getScores() {
		return this.scores;
	}
}
