package PlayersType;

import Game.Case;
import Game.Couleur;
public class Joueur extends Player {
	
	//constructors
	public Joueur(Couleur color, String pseudo) {
		super(color, pseudo);
	}

	//methods

	public boolean playPion(Case square, Couleur color) {
		
		//teste si un pion de la couleur donnée peut être posé sur la case donnée
		//si oui il le pose dans la case correspondante et retourne les pion encadrés
		//si non on afficher un message indiquant que le pion ne peut pas être posé
		
		if(!this.getPartie().getPlateP().isPlayable(square, color)) {
			return false;
		}else {
			this.getPartie().getPlateP().retourneAllLignes(square, color);
			this.getPartie().getPlateP().setPionCase(square, color);
			return true;
		}
	}
}
