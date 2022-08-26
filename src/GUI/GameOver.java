package GUI;

import java.io.InputStream;

import Game.Couleur;
import Game.Partie;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GameOver extends VBox{
	//attributes
	private Partie p;
	private Button menu = new Button("Menu Principal");
	private Interface interf;

	//constructor
	
	public GameOver() {
		
	}
	public GameOver(Partie p, Interface interf) {
		this.p = p;
		this.interf = interf;
		this.setStyle("-fx-alignment: center;");

		//general VBox options
		VBox box1 = new VBox();
		VBox box2 = new VBox();
		VBox box3 = new VBox();
		VBox.setVgrow(box1, Priority.ALWAYS);
		VBox.setVgrow(box2, Priority.ALWAYS);
		VBox.setVgrow(box3, Priority.ALWAYS);
		this.setStyle("-fx-alignment:center");
		
		//Image VBox options		
		Class<?> clazz = GameOver.class; 
		InputStream input = clazz.getResourceAsStream("Othello.png");
		Image image = new Image(input);
		ImageView view = new ImageView(image);
		view.setPreserveRatio(true);
		view.fitWidthProperty().bind(this.widthProperty());
		box1.getChildren().addAll(view);
		this.getChildren().add(box1);
		
		//VBox 2 Options
		box2.setStyle("-fx-alignment: center;");
		box2.setPrefHeight(300);
		PlateView viewPlate = new PlateView(this.p);
		box2.getChildren().add(viewPlate);
		this.getChildren().add(box2);
		
		//VBox 3 Options
		box3.setStyle("-fx-alignment: center;");
		box3.setPrefHeight(40);
		if(this.p.isEgality()) {
			Text t1 = new Text("La partie se termine par une egalite !");
			t1.setStyle("-fx-font-size: 30;");
			box3.getChildren().add(t1);
		}else if(this.p.win() == this.p.getJoueurs().get(0)) {
			Text t2 = new Text("Le joueur " + this.p.getJoueurs().get(0).getPseudo() + " a gagne la partie.");
			box3.getChildren().add(t2);
			t2.setStyle("-fx-font-size: 30;");
		}else {
			Text t3 = new Text("Le joueur " + this.p.getJoueurs().get(1).getPseudo() + " a gagne la partie.");
			box3.getChildren().add(t3);
			t3.setStyle("-fx-font-size: 30;");
		}
		Text t4 = null;
		Text t5 = null;
		if(this.p.getJoueurs().get(0).getColor() == Couleur.Noir) {
			if(!this.p.isEgality() && this.p.win() == this.p.getJoueurs().get(0)) {
				t4 = new Text(this.p.getJoueurs().get(0).getPseudo() + " a réalise un score de " + (this.p.getPlateP().howManyBlack() + 100) + " points.");
				t5 = new Text(this.p.getJoueurs().get(1).getPseudo() + " a réalise un score de " + this.p.getPlateP().howManyWhite() +" points.");
			}else {
				t4 = new Text(this.p.getJoueurs().get(0).getPseudo() + " a réalise un score de " + this.p.getPlateP().howManyBlack() + " points.");
				t5 = new Text(this.p.getJoueurs().get(1).getPseudo() + " a réalise un score de " + (this.p.getPlateP().howManyWhite() + 100) +" points.");
			}
		}else {
			if(!this.p.isEgality() && this.p.win() == this.p.getJoueurs().get(0)) {
				t4 = new Text(this.p.getJoueurs().get(0).getPseudo() + " a réalise un score de " + (this.p.getPlateP().howManyWhite() + 100) + " points.");
				t5 = new Text(this.p.getJoueurs().get(1).getPseudo() + " a réalise un score de " + this.p.getPlateP().howManyBlack() + " points.");
			}else {
				t4 = new Text(this.p.getJoueurs().get(0).getPseudo() + " a réalise un score de " + this.p.getPlateP().howManyWhite() + " points.");
				t5 = new Text(this.p.getJoueurs().get(1).getPseudo() + " a réalise un score de " + (this.p.getPlateP().howManyBlack() + 100) + " points.");
			}

		}

		t4.setStyle("-fx-font-size: 30;");
		t5.setStyle("-fx-font-size: 30;");
	
		box3.getChildren().add(t4);
		box3.getChildren().add(t5);

		this.getChildren().add(this.menu);
		this.getChildren().add(box3);
	}
	
	//getters
	public Button getMenu() {;
		return this.menu;
	}
	public Interface getInterf() {
		return this.interf;
	}
	
}
