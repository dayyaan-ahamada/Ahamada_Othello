package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import Game.*;

public class Plate extends HBox{
	//attributes
	private Partie p;
	private VBox box;
	private GridPane grid;
	private Interface interf;
	private Button retour = new Button("Retour");
	private SoundPlayer mp3Player = new SoundPlayer();
	//constructor
	public Plate(Partie p, Interface interf) {
		//initialisation
		this.p = p;
		this.interf = interf;
		this.grid = new GridPane();
		this.box = new VBox();

		//grid options
		this.grid.setPrefSize(900, 900);
		this.grid.setMinSize(300, 300);
		this.grid.setHgap(10);
		this.grid.setVgap(10);
		GridPane.setColumnSpan(this, 8);
		GridPane.setRowSpan(this, 8);
		
		//box options
		this.box.setPrefSize(300, 900);
		this.box.setMinSize(100, 300);
		this.box.setStyle("-fx-alignment:center");
		this.box.setSpacing(40);

		//ajout des ButtonCase
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				Case c = this.p.getCase(j, i);
				ButtonCase squareButton = new ButtonCase(c, this);
				this.grid.add(squareButton, j, i);
			}
		}
		
		//Initialisation du nombre de pions noirs
		Text howManyBlack = new Text(" Noirs : " + this.p.getPlateP().howManyBlack());
		StackPane black = new StackPane();
		black.getChildren().add(howManyBlack);

		//Initialisation du nombre de pions blancs
		Text howManyWhite = new Text(" Blancs : " + this.p.getPlateP().howManyWhite());
		StackPane white = new StackPane();
		white.getChildren().add(howManyWhite);
		
		//Initialisation du pseudoColor 
		PseudoColor pseudoCo = new PseudoColor(this.p);
		
		//ajout du pseudoColor et des nombres de pions à la box
		this.box.getChildren().add(pseudoCo);
		this.box.getChildren().add(black);
		this.box.getChildren().add(white);
		
		//ajout du bouton de retour
		this.retour.setMaxSize(150, 75);
		VBox.setVgrow(this.retour, Priority.ALWAYS);
		this.box.getChildren().add(this.retour);
		
		this.retour.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				getInterface().setSceneB();
			}
		});
		
		//ajout de la box et de la grid au Plate
		this.getChildren().add(this.grid);
		this.getChildren().add(this.box);
		
	}
	
	//getters
	public Partie getPartie(){
		return this.p;
	}
	public Interface getInterface() {
		return this.interf;
	}
	public Button getRetour() {
		return this.retour;
	}
	public SoundPlayer getMp3Player() {
		return mp3Player;
	}
	
	//methods
	public void actualizeCases(){//actualise le plateau
		this.getChildren().clear();
		this.getChildren().add(new Plate(this.p, this.interf));
	}

}
