package PlayersType;

import java.util.List;

import Game.Case;
import Game.Couleur;

public class EasyAI extends Player {

	//constructeur
	public EasyAI(Couleur color, String pseudo) {
		super(color, pseudo);
	}

	
	//methodes
	public boolean playPion() {
		
		//pose un pion sur le plateau
		//retourne true si le pion a pu �tre pose
		
		List<Case> possibilities = this.getPartie().getPlateP().playableCases(this);	//recupere les cases jouables dans une liste
		int randInt =(int)(Math.random() * (possibilities.size()));		//choisi une case parmi ce tableau
		if(!this.canPlay()) {
			return false;
		}else {		//pose un pion sur cette case si c'est possible
			this.getPartie().getPlateP().retourneAllLignes(possibilities.get(randInt), this.getColor());
			this.getPartie().getPlateP().setPionCase(possibilities.get(randInt), this.getColor());
			return true;
		}
	}
}
