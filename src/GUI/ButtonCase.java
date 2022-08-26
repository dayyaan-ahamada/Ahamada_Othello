package GUI;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import Game.*;
import PlayersType.Joueur;

public class ButtonCase extends StackPane{
	//attributes
	private Case square;

	//constructor
	public ButtonCase(Case square, Plate p) {
		this.setStyle("-fx-background-color: ForestGreen "); //Couleur du background : vert
		this.square = square;
		this.setPrefSize(100, 100); //Taille de base d'une case
		this.setMinSize(10, 10); //Taille minimale d'une case
		
		this.setOnMouseExited(new EventHandler<MouseEvent>() {//Lorsque la souris sors de la case on lui ré-assigne sa couleur verte
			@Override
			public void handle(MouseEvent arg0) {
				setStyle("-fx-background-color: ForestGreen");
			}
		});
		
		this.setOnMousePressed(new EventHandler<MouseEvent>() {//Lorsque la souris est appuyée on attribue à la case une couleur rouge(si la case n'est pas jouable)
			@Override
			public void handle(MouseEvent arg0) {
				if(!p.getPartie().getPlateP().isPlayable(square, p.getPartie().getInplay().getColor())) {
					setStyle("-fx-background-color:firebrick");
					
					
					p.getMp3Player().playNo();
				}				
			}
		});
		
		this.setOnMouseDragReleased(new EventHandler<MouseEvent>() {//Lorsque la souris est relachée, on rend sa couleur d'origine à la case
			@Override
			public void handle(MouseEvent arg0) {
				setStyle("-fx-background-color: ForestGreen");
			}
			
		});
		
		this.setOnMouseClicked(new EventHandler<MouseEvent>() {//événement principal pour joueur un pion.
			@Override
			public void handle(MouseEvent arg0) {
				if(!p.getPartie().isFinish()) { //si la partie n'est pas terminée
			        p.getPartie().playTurn(square); // joue un tour lors du clic de souris
					p.actualizeCases();//actualise le plateau
					if(p.getPartie().getInplay().getClass() != Joueur.class){ //si une IA doit joueur
				        Task<Void> sleeper = new Task<Void>() { //lancement d'un thread qui permet un délai
				            @Override
				            protected Void call() throws Exception {
				                try {
				                    Thread.sleep(300);//temps du délai
				                } catch (InterruptedException e) {}
				                return null;
				            }
				        };
				        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
				            @Override
				            public void handle(WorkerStateEvent event) {
				            	while(!p.getPartie().isFinish() && p.getPartie().getInplay().getClass() != Joueur.class) {//tant que la partie n'est pas terminée et qu'une IA doit jouer

									p.getPartie().playTurn();//joue un tour d'IA
									p.actualizeCases();//actualise le plateau
									
				            	}
				            }
				        });
				        new Thread(sleeper).start();
					}
				}
			}				
		});
		
		//change la couleur du button en fonction de la pr�sence ou non d'un pion noir ou blanc.

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