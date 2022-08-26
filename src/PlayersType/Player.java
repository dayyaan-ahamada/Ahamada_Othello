package PlayersType;

import Game.Case;
import Game.Couleur;
import Game.Partie;

public abstract class Player {
	//attributes
	private Couleur color;
	private String pseudo;
	private Partie p;
	
	//constructors
	public Player(Couleur color, String pseudo) {
		this.color = color;
		this.pseudo = pseudo;
	}
	
	//getters
	public Couleur getColor() {
		return this.color;
	}
	public String getPseudo() {
		return this.pseudo;
	}
	public Partie getPartie() {
		return this.p;
	}
	//setters
	public void setpartie(Partie p) {
		this.p = p;
	}
	
	//methods
	public boolean canPlay() {
		
		//teste si un joueur peut poser un pion
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++){
				if(this.getPartie().getPlateP().isPlayable(this.getPartie().getPlateP().getPlate()[j][i], this.getColor())) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean playPion() {
		return false;
	}
	
	public boolean playPion(Case square, Couleur color) {
		return false;
	}
}
