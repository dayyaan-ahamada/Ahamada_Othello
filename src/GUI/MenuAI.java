package GUI;

import java.io.InputStream;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class MenuAI extends VBox{
	//attributes
	private Button easy = new Button("easy");
	private Button inter = new Button("inter");
	private Button hard = new Button("hard");
	private Button retour = new Button("Retour");
	private TextField pseudo = new TextField("Pseudo");
	private Interface interf;

	//constructor
	public MenuAI(Interface interf) {
		this.interf = interf;
		//general VBox options
		VBox box1 = new VBox();
		VBox box2 = new VBox();
		VBox.setVgrow(box1, Priority.ALWAYS);
		VBox.setVgrow(box2, Priority.ALWAYS);
		box1.setStyle("-fx-alignment:center");

		//Image VBox options
		Class<?> clazz = GameTypeMenu.class; 
		InputStream input = clazz.getResourceAsStream("../images/Othello.png");
		Image image = new Image(input);
		ImageView view = new ImageView(image);
		view.setPreserveRatio(true);
		view.fitWidthProperty().bind(this.widthProperty());
		box1.getChildren().addAll(view);
		
		this.getChildren().add(box1);
				
		//Buttons VBox options
		box2.setSpacing(40);
		box2.setPrefHeight(600);
		box2.setPrefWidth(900);
		
		VBox.setVgrow(this.easy, Priority.ALWAYS);
		VBox.setVgrow(this.inter, Priority.ALWAYS);
		VBox.setVgrow(this.hard, Priority.ALWAYS);
		VBox.setVgrow(this.retour, Priority.ALWAYS);
		VBox.setVgrow(this.pseudo, Priority.ALWAYS);

		this.easy.setMaxSize(300, 100);
		this.inter.setMaxSize(300, 100);
		this.hard.setMaxSize(300, 100);
		this.retour.setMaxSize(300, 100);
		this.pseudo.setMaxWidth(300);
		
		box2.setStyle("-fx-alignment: center;");
		this.easy.setLayoutY(50);
		this.inter.setLayoutY(75);
		this.hard.setLayoutY(100);
		this.hard.setLayoutY(125);

		box2.getChildren().add(this.pseudo);
		box2.getChildren().add(this.easy);
		box2.getChildren().add(this.inter);
		box2.getChildren().add(this.hard);
		box2.getChildren().add(this.retour);
		
		this.getChildren().add(box2);	
	}
	
	//getters
	public Button getEasy() {
		return this.easy;
	}
	public Button getInter() {
		return this.inter;
	}
	public Button getHard() {
		return this.hard;
	}
	public Button getRetour() {
		return this.retour;
	}
	public TextField getPseudo() {
		return this.pseudo;
	}
	public Interface getInterf() {
		return this.interf;
	}
}
