package GUI;

import java.io.InputStream;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ChoosePseudosMenu extends VBox{
	//attributes
	private TextField pseudo1 = new TextField("Joueur 1");
	private TextField pseudo2 = new TextField("Joueur 2");
	private Button valider = new Button("Valider");
	private Button retour = new Button("Retour");
	private Interface interf;
	
	//constructor
	public ChoosePseudosMenu(Interface interf) {
		//initialisation de l'interface
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
		this.pseudo2.setLayoutY(50);
		this.valider.setLayoutY(100);
		box2.setStyle("-fx-alignment: center;");
		box2.setSpacing(40);
		this.pseudo1.setMaxWidth(300);
		this.pseudo2.setMaxWidth(300);
		this.valider.setMaxSize(300, 150);
		this.retour.setMaxSize(300, 150);

		VBox.setVgrow(this.valider, Priority.ALWAYS);
		VBox.setVgrow(this.retour, Priority.ALWAYS);

		box2.setPrefHeight(600);
		box2.getChildren().add(this.pseudo1);
		box2.getChildren().add(this.pseudo2);
		box2.getChildren().add(this.valider);
		box2.getChildren().add(this.retour);

		this.getChildren().add(box2);	
	}
	
	//getters
	public Button getValider() {
		return this.valider;
	}
	public TextField getPseudo1() {
		return this.pseudo1;
	}
	public TextField getPseudo2() {
		return this.pseudo2;
	}
	public Interface getInterf() {
		return this.interf;
	}
	public Button getRetour() {
		return this.retour;
	}
}
