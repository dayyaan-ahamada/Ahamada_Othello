package GUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import Game.*;
import PlayersType.EasyAI;
import PlayersType.HardAI;
import PlayersType.IntermediateAI;
import PlayersType.Joueur;
import PlayersType.Player;

public class Interface extends Stage {
	//attributes
	private Partie partie;
	private MenuAI menuA;
	private GameTypeMenu menuB;
	private ChoosePseudosMenu menuC;
	private GameOver menuD;
	private Score menuE;
	private Scene sceneA;
	private Scene sceneB;
	private Scene sceneC;
	private Scene sceneD;
	private Scene sceneE;
	private Plate plate;

	//constructor
	public Interface() {
		//initialisation des menus
		this.menuA = new MenuAI(this);
		this.menuB = new GameTypeMenu();
		this.menuC = new ChoosePseudosMenu(this);
		this.menuE = new Score();
		this.sceneA = new Scene(this.menuA);
		this.sceneB = new Scene(this.menuB);
		this.sceneC = new Scene(this.menuC);
		this.sceneE = new Scene(this.menuE);

		//initialisation des tailles
		this.setMinHeight(400);
		this.setMinWidth(500);
		this.setHeight(900);
		this.setWidth(1200);
		
		// AJOUT DES ACTIONS SUR LES BOUTONS DES MENUS
		
		//actions menu du choix des pseudos
		this.menuC.getValider().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				List<Player> joueurs = new ArrayList<Player>();
				Joueur player1 = new Joueur(Couleur.Noir, menuC.getPseudo1().getText());
				Joueur player2 = new Joueur(Couleur.Blanc, menuC.getPseudo2().getText());
				joueurs.add(player1);
				joueurs.add(player2);
				Partie p = new Partie(joueurs, menuC.getInterf());
				p.getJoueurs().get(0).setpartie(p);
				p.getJoueurs().get(1).setpartie(p);
				partie = p;
				plate = new Plate(p, menuC.getInterf());
				setScene(new Scene(plate));
			}
		});
		
		//actions menu d'IA
		this.menuA.getEasy().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				List<Player> joueurs = new ArrayList<Player>();
				Joueur player1 = new Joueur(Couleur.Noir, menuA.getPseudo().getText());
				EasyAI player2 = new EasyAI(Couleur.Blanc, "IA omega");
				joueurs.add(player1);
				joueurs.add(player2);
				Partie p = new Partie(joueurs, menuA.getInterf());
				p.getJoueurs().get(0).setpartie(p);
				p.getJoueurs().get(1).setpartie(p);
				partie = p;
				plate = new Plate(p, menuA.getInterf());
				setScene(new Scene(plate));
			}
			
		});
		this.menuA.getInter().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				List<Player> joueurs = new ArrayList<Player>();
				Joueur player1 = new Joueur(Couleur.Noir, menuA.getPseudo().getText());
				IntermediateAI player2 = new IntermediateAI(Couleur.Blanc, "IA Beta");
				joueurs.add(player1);
				joueurs.add(player2);
				Partie p = new Partie(joueurs, menuA.getInterf());
				p.getJoueurs().get(0).setpartie(p);
				p.getJoueurs().get(1).setpartie(p);
				partie = p;
				plate = new Plate(p, menuA.getInterf());
				setScene(new Scene(plate));
			}
			
		});
		this.menuA.getHard().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				List<Player> joueurs = new ArrayList<Player>();
				Joueur player1 = new Joueur(Couleur.Noir, menuA.getPseudo().getText());
				HardAI player2 = new HardAI(Couleur.Blanc, "IA Alpha");
				joueurs.add(player1);
				joueurs.add(player2);
				Partie p = new Partie(joueurs, menuA.getInterf());
				p.getJoueurs().get(0).setpartie(p);
				p.getJoueurs().get(1).setpartie(p);
				partie = p;
				plate = new Plate(p, menuA.getInterf());
				setScene(new Scene(plate));
			}
			
		});
		
		//actions menu solo ou multi
		this.menuB.getSolo().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				setScene(sceneA);
			}
		});
		this.menuB.getMulti().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				setScene(sceneC);
			}
		});
		
		this.menuB.getQuitter().setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				close();
			}
			
		});
		this.menuB.getScores().setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				menuE.rechargeTab();
				setScene(sceneE);
			}
			
		});
		
		
		//boutons de retour
		this.setScene(sceneB);
		this.menuA.getRetour().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				setScene(sceneB);
			}
		});
		this.menuC.getRetour().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				setScene(sceneB);
			}
		});
		this.menuE.getRetour().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				setScene(sceneB);
			}
		});
	}
	
	//getter
	public Partie getPartie(){ // renvoie la partie en cours
		return this.partie;
	}
	
	//methods
	public void setGameOver(Partie part) {//place le menu de fin
		//initialisation d'un menu gameOver
		this.menuD = new GameOver(part, this);
		this.sceneD = new Scene(this.menuD);
		this.menuD.getMenu().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				setScene(sceneB);
			}
			
		});
		this.setScene(sceneD);
	}
	
	public void setSceneB() {
		this.setScene(sceneB);
	}
}