package GUI;

import Game.Case;
import Game.Partie;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PlateView extends HBox{
	//attributes
	private VBox box;
	private GridPane grid;
	private Partie p;
	
	//constructor
	public PlateView(Partie p) {
		//initialisation
		this.p = p;
		this.grid = new GridPane();
		this.box = new VBox();
		this.setStyle("-fx-alignment:center");
		
		//grid options
		this.grid.setStyle("-fx-alignment:center");
		this.grid.setPrefSize(300, 300);
		this.grid.setMinSize(100, 100);
		this.grid.setHgap(3);
		this.grid.setVgap(3);
		GridPane.setColumnSpan(this, 8);
		GridPane.setRowSpan(this, 8);
		
		//box options
		this.box.setPrefSize(300, 300);
		this.box.setMinSize(100, 100);
		this.box.setStyle("-fx-alignment:center");
		this.box.setSpacing(13);
		
		//ajout des cases
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				Case c = this.p.getCase(j, i);
				CaseView square = new CaseView(c, this);
				this.grid.add(square, j, i);
			}
		}
		
		//ajout de la box à la view
		this.box.getChildren().add(this.grid);
		this.getChildren().add(this.box);
	}
	
	
	
}
