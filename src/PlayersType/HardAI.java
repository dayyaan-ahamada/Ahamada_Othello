package PlayersType;

import java.util.ArrayList;
import java.util.List;

import Game.Case;
import Game.Couleur;

public class HardAI extends Player {

	//constructeur
	public HardAI(Couleur color, String pseudo) {
		super(color, pseudo);
	}

	//methodes
	public boolean playPion() {
		List<Case> highest = new ArrayList<Case>();
		List<Case> possibilities = this.getPartie().getPlateP().playableCases(this);	//recupere les cases jouables dans une liste
		Case actual = possibilities.get(0);
		
		for(int i = 0; i < possibilities.size(); i++){		//recupere la case qui permet de retourner le plus de pions
			if(this.getPartie().note(possibilities.get(i))
			   > this.getPartie().note(actual)){
				actual = possibilities.get(i);
			}
		}
		for(int j = 0; j < possibilities.size(); j ++){		//ajoute les autres possibilites qui retournent le meme nombre de points
			if(this.getPartie().note(actual) == this.getPartie().note(possibilities.get(j))){
				highest.add(possibilities.get(j));
			}
		}
		
		int randInt = (int)(Math.random() * (highest.size()));	//si plusieurs cases retournent le meme nombre de pions on choisi la case a jouer de facon random

		if(!this.canPlay()) {
			return false;
		}else {		//pose un pion sur cette case si c'est possible 
			this.getPartie().getPlateP().retourneAllLignes(highest.get(randInt), this.getColor());
			this.getPartie().getPlateP().setPionCase(highest.get(randInt), this.getColor());
			return true;
		}
	}
}