package Game;

import java.util.ArrayList;
import java.util.List;

import PlayersType.Player;

public class Plateau{
	
	//attributes
	private Case[][] plate = new Case[8][8];	//plateau carré de 8 cases de côté (64 cases)
	
	//constructors
	public Plateau(Case[][] plate){
		
		//construit un plateau selon une situation donn�e
		//(seulement pour les tests)
		
		this.plate = plate;
	}
	
	public Plateau(){
		
		//cr�e un plateau de d�part de jeu 
		//(que des cases vides sauf le centre du plateau)
		
		for(int y = 0; y < 8; y++){
			for(int x = 0; x < 8; x++){
				if((x == 3 && y == 4) || (x == 4 && y == 3)){
					this.plate[x][y] = new Case(x, y, new Pion(Couleur.Noir));
				}else if((x == 3 && y == 3) || (x == 4 && y == 4)){
					this.plate[x][y] = new Case(x, y, new Pion(Couleur.Blanc));
				}else{
					this.plate[x][y] = new Case(x, y);
				}
			}
		}
	}
	
	//getters
	public Case[][] getPlate() {
		return plate;
	}
	
	//setters
	public void setPlate(Case[][] plate) {
		this.plate = plate;
	}
	
	public void setPionCase(Case square, Couleur color) {	
		
		//met un pion sur une case donnée
		
		this.plate[square.getX()][square.getY()].setP(new Pion(color));
		this.plate[square.getX()][square.getY()].changeFree();
	}
	
	//methods
	public String toString(){
		
		//retourne le plateau
		
		String result = "";
		for(int i = 0; i < 8; i++){
			result = result + "\n";

			for(int j = 0; j < 8; j++){
				result = result + this.plate[j][i].toString() + " ";
			}
			result = result + "\n";
		}
		return result;
	}
	
	public List<Case> playableCases(Player p){
		List<Case> result = new ArrayList<Case>();
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(this.isPlayable(this.plate[j][i], p.getColor())) {
					result.add(this.plate[j][i]);
				}
			}
		}
		return result;
	}
	
	public boolean isCaseFree(int x, int y){
		
		//teste si une case est vide
		//retourne VRAI si la case est vide
		
		if(x < 8 && y < 8 && x >= 0 && y >= 0){
			return this.plate[x][y].isFree();
		}else{
			return true;
		}
	}
	
	public boolean isAdjFree(Case square){
		
		//teste si les cases adjacentes � la case donn�e sont vides
		//retourne VRAI si toutes les cases adjacentes sont vides
		
		int x = square.getX();
		int y = square.getY();
		
		if(!isCaseFree(x, y+1)){
			return false;
		}else if(!isCaseFree(x+1, y+1)){
			return false;
		}else if(!isCaseFree(x+1, y)){
			return false;
		}else if(!isCaseFree(x+1, y-1)){
			return false;
		}else if(!isCaseFree(x, y-1)){
			return false;
		}else if(!isCaseFree(x-1, y-1)){
			return false;
		}else if(!isCaseFree(x-1, y)){
			return false;
		}else if(!isCaseFree(x-1, y+1)){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean testEncadre(List<Case> listCase, Couleur color){
		
		//teste si poser un pion sur la case donnée encadre au moins un pion de couleur diff�rente sur une ligne du tableau donnée en liste de cases
		//retourne VRAI si un encadrement est possible sur cette ligne
		
		int cpt = 1;
		if(listCase.get(0).getPion().getColor() == color){
			return false;
		}
		while(cpt <= listCase.size() - 1 && !listCase.get(cpt).isFree()){
			if(listCase.get(cpt).getPion().getColor() == color){
				return true;
			}
			cpt ++;
		}
		return false;
	}
	
	public List<Integer> dirColor(Case square, Couleur color){
	
		//teste si un pion plac� sur une case adjacente à la case donnée est de la couleur oppos�e
		//retourne une liste (� compter les indices 2 par 2) contenant les directions pour lesquelles un encadrement est possible (x puis y)
		
		int x = square.getX();
		int y = square.getY();
		
		List<Integer> result = new ArrayList<Integer>();
		if(y + 1 <= 7 && this.plate[x][y+1].getPion() != null && this.plate[x][y+1].getPion().getColor() != color){
			result.add(0);
			result.add(1);
		}
		if(x + 1 <= 7 && y + 1 <= 7 && this.plate[x+1][y+1].getPion() != null && this.plate[x+1][y+1].getPion().getColor() != color){
			result.add(1);
			result.add(1);
		}
		if(x + 1 <= 7 && this.plate[x+1][y].getPion() != null && this.plate[x+1][y].getPion().getColor() != color){
			result.add(1);
			result.add(0);
		}
		if(x + 1 <= 7 && y - 1 >= 0 && this.plate[x+1][y-1].getPion() != null && this.plate[x+1][y-1].getPion().getColor() != color){
			result.add(1);
			result.add(-1);
		}
		if(y - 1 >= 0 && this.plate[x][y-1].getPion() != null && this.plate[x][y-1].getPion().getColor() != color){
			result.add(0);
			result.add(-1);
		}
		if(x - 1 >= 0 && y - 1 >= 0 && this.plate[x-1][y-1].getPion() != null && this.plate[x-1][y-1].getPion().getColor() != color){
			result.add(-1);
			result.add(-1);
		}
		if(x - 1 >= 0 && this.plate[x-1][y].getPion() != null && this.plate[x-1][y].getPion().getColor() != color){
			result.add(-1);
			result.add(0);
		}
		if(x - 1 >= 0 && y + 1 <= 7 && this.plate[x-1][y+1].getPion() != null && this.plate[x-1][y+1].getPion().getColor() != color){
			result.add(-1);
			result.add(1);
		}
		return result;
	}

	public List<Case> tabCaseDir(Case square, int addX, int addY){
		
		//cr�e une liste de cases � partir d'une case donnée et d'une direction donn�e
		//retourne une liste contenant les cases d'une ligne du plateau
		
		int x = square.getX();
		int y = square.getY();
		List<Case> result = new ArrayList<Case>();
		int cpt = 1;
		while(x+(cpt*addX) <= 7 && y+(cpt*addY) <= 7 && x+(cpt*addX) >= 0 && y+(cpt*addY) >= 0){
		result.add(this.plate[x + (cpt * addX)][y + (cpt * addY)]);
			cpt ++;
		}
		return result;
	}
	
	public boolean testEncadreAllDir(Case square, Couleur color){
		
		//teste si poser un pion sur la case donnée encadre au moins un pion de couleur diff�rente sur le plateau
		//retourne VRAI si un encadrement est possible
		
		int cpt = 0;
		List<Integer> dir = this.dirColor(square, color);
		while(cpt < dir.size()){
			if(this.testEncadre(this.tabCaseDir(square, dir.get(cpt), dir.get(cpt+1)), color)){
				return true;
			}
			cpt = cpt + 2;
		}
		return false;
	}
	
	public boolean isPlayable(Case square, Couleur color){
		
		//teste si une case est jouable par une couleur
		///retourne VRAI si le joueur peut poser un pion de sa couleur sur la case donnée
		
		if(!this.isCaseFree(square.getX(), square.getY())){	//teste si la case est libre
			return false;
		}else if(this.isAdjFree(square)){	//teste si un pion est posé sur une case adjacente
			return false;
		}else if(!this.testEncadreAllDir(square, color)){	//teste si poser un pion permet un encadrement d'une case de couleur opposée
			return false;
		}else{
			return true;
		}
	}
	
	public void retourneLigne(List<Case> caseList, Couleur color){
		
		//retourne les pions (change la couleur) d'une ligne du plateau donnés dans la liste jusqu'à un pion de la couleur donnée
		
		int cpt = 0;
		if(this.testEncadre(caseList, color) == true) {
			while(cpt < caseList.size() && caseList.get(cpt).getPion().getColor() != color && !caseList.get(cpt).isFree()) {
				this.plate[caseList.get(cpt).getX()][caseList.get(cpt).getY()].getPion().changeColor();;
				cpt++;
			}
		}
	}
	
	public void retourneAllLignes(Case square, Couleur color) {
		
		//retourne (change la couleur) de toutes les lignes du plateau permettant un encadrement entre la case donnée et le prochain pion de la couleur donnée
		
		int cpt = 0;
		List<Integer> dirList = this.dirColor(square, color);
		while(cpt <= dirList.size() - 2) {
			this.retourneLigne(this.tabCaseDir(square, dirList.get(cpt), dirList.get(cpt+1)), color);
			cpt = cpt + 2;
		}
	}
	
	public int howManyReturnTab(List<Case> listCase, Player p) {
		int result = 0;
		if(this.testEncadre(listCase, p.getColor())) {

			while(result < listCase.size() && listCase.get(result).getPion().getColor() == p.getColor()){
				System.out.println("ok");
				System.out.println("ok1");
				result ++;
			}
		}
		return result;
	}
	
	public int howManyReturnTotal(Case square, Player p) {
		int result = 0;
		List<Integer> dir = this.dirColor(square, p.getColor());
		int cpt = 0;
		
		while(cpt < dir.size()){
			if(this.testEncadre(this.tabCaseDir(square, dir.get(cpt), dir.get(cpt+1)), p.getColor())){
				result = result + this.howManyReturnTab(this.tabCaseDir(square, dir.get(cpt), dir.get(cpt+1)), p);
			}
			cpt = cpt + 2;
		}
		return result;
	}
	
	public int howManyWhite(){

		//compte combien de pions blancs sont pos�s sur le plateau
		//retourne ce nombre
		
		int result = 0;
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(!this.isCaseFree(j, i) && this.plate[j][i].getPion().getColor() == Couleur.Blanc){
					result++;
				}
			}
		}
		return result;
	}
	
	public int howManyBlack(){
		
		//compte combien de pions noirs sont pos�s sur le plateau
		//retourne ce nombre
		
		int result = 0;
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(!this.isCaseFree(j, i) && this.plate[j][i].getPion().getColor() == Couleur.Noir){
					result++;
				}
			}
		}
		return result;
	}
	
	public int returnScore(Case square, Player p) {
		return this.howManyReturnTotal(square, p);
	}
}
