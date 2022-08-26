package Game;

import java.util.ArrayList;
import java.util.List;

import DB.Connect;
import DB.Inserer;
import GUI.Interface;
import GUI.ScoreModel;
import PlayersType.Player;

public class Partie{

	//attributes
	private List<Player> joueurs = new ArrayList<Player>();
	private Plateau plate;
	private Player inPlay;
	private Interface interf;

	//constructors
	public Partie(List<Player> joueurs, Interface interf) {
		if(joueurs.size()==2) {
			this.interf = interf;
			this.plate = new Plateau();
			if(joueurs.get(0).getColor() == Couleur.Noir){
				this.inPlay = joueurs.get(0);
			}else{
				this.inPlay = joueurs.get(1);
			}
			this.joueurs = joueurs;
		}
	}

	//constructeur utile seulement pour les tests
	public Partie(List<Player> joueurs) {
		if(joueurs.size()==2) {
			this.plate = new Plateau();
			if(joueurs.get(0).getColor() == Couleur.Noir){
				this.inPlay = joueurs.get(0);
			}else{
				this.inPlay = joueurs.get(1);
			}
			this.joueurs = joueurs;
		}
		else {
			//ne rien faire
		}
	}

	//getters
	public List<Player> getJoueurs() {
		return this.joueurs;
	}

	public Plateau getPlateP() {
		return this.plate;
	}

	public Case getCase(int x, int y) {
		return this.plate.getPlate()[x][y];
	}

	public Player getInplay() {
		return this.inPlay;
	}

	public Interface getInterf() {
		return interf;
	}

	//setters
	public void setJoueurs(List<Player> joueurs) {
		this.joueurs = joueurs;
	}

	public void setPlate(Plateau plate) {  
		this.plate = plate;
	}

	public void setInterf(Interface interf) {
		this.interf = interf;
	}

	//methods
	public void changePlayerInPlay(){
		if(this.inPlay == this.joueurs.get(0)){
			this.inPlay = this.joueurs.get(1);
		}else{
			this.inPlay = this.joueurs.get(0);
		}
	}

	public void playTurn() {
		if(!this.isFinish()) {	
			if(this.inPlay.canPlay()){
				this.inPlay.playPion();
				this.changePlayerInPlay();
				if(!this.inPlay.canPlay()) {
					this.changePlayerInPlay();
				}
			}
			if(this.isFinish()) {
				this.interf.setGameOver(this);
			}
		}
		if(this.isFinish()){
			this.interf.setGameOver(this);
		}
	}

	public void playTurn(Case square) {
		//m�thode utilis�e par les ButtonCases pour jouer un tour
		if(!this.isFinish()) {
			if(this.inPlay.canPlay()) {
				if(this.inPlay.playPion(square, this.inPlay.getColor())) {
					this.changePlayerInPlay();
					if(!this.inPlay.canPlay()) {
						this.changePlayerInPlay();
					}
				}
			}else {
				this.changePlayerInPlay();
			}
			if(this.isFinish()) {
				this.interf.setGameOver(this);
			}
		}
		if(this.isFinish()){
			this.interf.setGameOver(this);
		}
	}

	public boolean isFull(){

		//teste si le plateau est complet

		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(this.plate.isCaseFree(i, j)){
					return false;
				}
			}
		}
		return true;
	}

	public boolean isEgality(){

		//teste si une �galit� arrive
		//verifie si le nombre de pions blancs est le m�me que le nombre de pions noirs

		if(this.isFull() || (!this.joueurs.get(0).canPlay() && !this.joueurs.get(1).canPlay())){
			if(this.plate.howManyBlack() == this.plate.howManyWhite()){
				return true;
			}
		}
		return false;
	}

	public boolean isFinish() {

		//teste si la partie est finie

		if(this.isEgality()
				|| (this.isFull() || (!this.joueurs.get(0).canPlay() && !this.joueurs.get(1).canPlay())
						|| (this.plate.howManyBlack() == 64 )
						|| (this.plate.howManyWhite() == 64 ))){

			//ajout du pseudo et du score dans la base de donn�es
				
				Connect connect = new Connect();
				connect.connectDB();
				Inserer inserer = new Inserer();

				//recuperation des pseudos et scores de chque joueur
				int scoreBlanc, scoreNoir;
				String pseudoNoir, pseudoBlanc;
				
				if(this.getJoueurs().get(0).getColor()==Couleur.Noir) {
					pseudoNoir = this.getJoueurs().get(0).getPseudo();
					scoreNoir = this.getPlateP().howManyBlack();
					
					pseudoBlanc = this.getJoueurs().get(1).getPseudo();
					scoreBlanc = this.getPlateP().howManyWhite();
				}else {
					pseudoBlanc = this.getJoueurs().get(0).getPseudo();
					scoreBlanc = this.getPlateP().howManyWhite();
					
					pseudoNoir = this.getJoueurs().get(1).getPseudo();
					scoreNoir = this.getPlateP().howManyBlack();
				}
				
				//ajout de 100 points au score du gagnant
				if(this.win() == this.getJoueurs().get(0)) {
					if(this.getJoueurs().get(0).getColor() == Couleur.Noir) {
						scoreNoir += 100;
					}else {
						scoreBlanc += 100;
					}
				}else {
					if(this.getJoueurs().get(1).getColor() == Couleur.Noir) {
						scoreNoir += 100;
					}else {
						scoreBlanc += 100;
					}
				}
				
				ScoreModel playerBlack = new ScoreModel(pseudoNoir, scoreNoir);
				ScoreModel playerWhite = new ScoreModel(pseudoBlanc, scoreBlanc);
				
				inserer.insert(playerBlack);
				inserer.insert(playerWhite);
			return true;
		}else{
			return false;
		}
	}

	public Player win(){

		//retourne le joueur ayant gagn� la partie

		if(this.isFull() || (!this.joueurs.get(0).canPlay() && !this.joueurs.get(1).canPlay())){
			if(this.plate.howManyBlack() > this.plate.howManyWhite()){
				if(this.joueurs.get(0).getColor() == Couleur.Noir){
					return this.joueurs.get(0);
				}else{
					return this.joueurs.get(1);
				}
			}else{
				if(this.joueurs.get(0).getColor() == Couleur.Blanc){
					return this.joueurs.get(0);
				}else{
					return this.joueurs.get(1);
				}
			}
		}else if(this.plate.howManyBlack() == 0 ){
			if(this.joueurs.get(0).getColor() == Couleur.Blanc){
				return this.joueurs.get(0);
			}else{
				return this.joueurs.get(1);
			} 
		}else{
			if(this.joueurs.get(0).getColor() == Couleur.Noir){
				return this.joueurs.get(0);
			}else{
				return this.joueurs.get(1);
			} 
		}
	}

	public boolean connectedToBorder(Case square){
		boolean result = true;
		if(square.isCorner()){
			result = true;
		}else if(square.isBorder()) {
			if(square.getX() == 0) {
				if(!this.getCase(0, 0).isFree() && this.getCase(0, 0).getPion().getColor() == this.getInplay().getColor()){	
					int cpt = 0;
					
					while(cpt != square.getY()) {
						if(!this.getCase(0, cpt).isFree() && this.getCase(0, cpt).getPion().getColor() != this.getInplay().getColor()) {
							result = false;
						}
						cpt++;
					}
					
				}else if(!this.getCase(0, 7).isFree() && this.getCase(0, 7).getPion().getColor() == this.getInplay().getColor()) {
					int cpt = 7;
					
					while(cpt != square.getY()) {
						if(!this.getCase(0, cpt).isFree() && this.getCase(0, cpt).getPion().getColor() != this.getInplay().getColor()) {
							result = false;
						}
						cpt--;
					}
					
				}else {
					result = false;
				}
			}else if(square.getX() == 7) {
				if(!this.getCase(7, 0).isFree() && this.getCase(7, 0).getPion().getColor() == this.getInplay().getColor()){
					int cpt = 0;
					
					while(cpt != square.getY()) {
						if(!this.getCase(7, cpt).isFree() && this.getCase(7, cpt).getPion().getColor() != this.getInplay().getColor()) {
							result = false;
						}
						cpt++;
					}
					
				}else if(!this.getCase(7, 7).isFree() && this.getCase(7, 7).getPion().getColor() == this.getInplay().getColor()) {
					int cpt = 7;

					while(cpt != square.getY()) {
						if(!this.getCase(7, cpt).isFree() && this.getCase(7, cpt).getPion().getColor() != this.getInplay().getColor()) {
							result = false;
						}
						cpt--;
					}
					
				}else {
					result = false;
				}
			}else if(square.getY() == 0) {
				if(!this.getCase(0, 0).isFree() && this.getCase(0, 0).getPion().getColor() == this.getInplay().getColor()){
					int cpt = 0;

					while(cpt != square.getY()) {
						if(!this.getCase(cpt, 0).isFree() && this.getCase(cpt, 0).getPion().getColor() != this.getInplay().getColor()) {
							result = false;
						}
						cpt++;
					}
					
				}else if(!this.getCase(7, 0).isFree() && this.getCase(7, 0).getPion().getColor() == this.getInplay().getColor()) {
					int cpt = 7;

					while(cpt != square.getY()) {
						if(!this.getCase(cpt, 0).isFree() && this.getCase(cpt, 0).getPion().getColor() != this.getInplay().getColor()) {
							result = false;
						}
						cpt--;
					}
					
				}else {
					result = false;
				}
			}else if(square.getY() == 7) {
				if(!this.getCase(0, 7).isFree() && this.getCase(0, 7).getPion().getColor() == this.getInplay().getColor()){
					int cpt = 0;

					while(cpt != square.getY()) {
						if(!this.getCase(cpt, 7).isFree() && this.getCase(cpt, 7).getPion().getColor() != this.getInplay().getColor()) {
							result = false;
						}
						cpt++;
					}
					
				}else if(!this.getCase(7, 7).isFree() && this.getCase(7, 7).getPion().getColor() == this.getInplay().getColor()) {
					int cpt = 7;

					while(cpt != square.getY()) {
						if(!this.getCase(cpt, 0).isFree() && this.getCase(cpt, 0).getPion().getColor() != this.getInplay().getColor()) {
							result = false;
						}
						cpt--;
					}
					
				}
			}else {
				result = false;
			}
		}else {
			result = false;
		}
		return result;
	}

	public int note(Case square) {
		int score = 0;
		//point positifs
		if(square.isCorner()) {
			score += 100;
		}
		if(this.connectedToBorder(square)) {
			score += 30;
		}
		if(square.isBorder() && !this.connectedToBorder(square)) {
			score += 20;
		}
		if(square.isNextNextCorner()) {
			score += 10;
		}
		
		//points negatifs
		if(square.isNextCorner()) {
			score -= 50;
		}
		if(square.isNextBorder()) {
			score -= 10;
		}
		
		return score + this.getPlateP().howManyReturnTotal(square, this.inPlay);
	}
	
	
	
}
