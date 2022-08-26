package GUI;

import Game.Case;
import Game.Couleur;
import javafx.scene.layout.StackPane;

public class CaseView extends StackPane{
	private Case square;
	
	public CaseView(Case square, PlateView p) {
		this.setStyle("-fx-background-color: ForestGreen "); //Couleur du background : vert
		this.square = square;
		this.setPrefSize(33, 33); //Taille de base d'une case
		this.setMinSize(3, 3); //Taille minimale d'une case
		
		if(!this.square.isFree()) {
			if(this.square.getPion().getColor() == Couleur.Noir) {
				PionView pion = new PionView(Couleur.Noir);
				pion.fitWidthProperty().bind(this.widthProperty());
				pion.fitHeightProperty().bind(this.heightProperty());
				pion.setPreserveRatio(true);
				this.getChildren().add(pion);
			}else {
				PionView pion = new PionView(Couleur.Blanc);
				pion.fitWidthProperty().bind(this.widthProperty());
				pion.fitHeightProperty().bind(this.heightProperty());
				pion.setPreserveRatio(true);
				this.getChildren().add(pion);			
			}
		}
		
	}
}
