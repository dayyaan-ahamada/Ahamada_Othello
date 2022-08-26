package Game;

public class Pion {
	
	//attributes
	private Couleur color;
	
	//constructors
	public Pion(Couleur color){
		this.color = color;
	}
	
	//getters
	public Couleur getColor(){
		return this.color;
	}
	
	//setters
	public void setColor(Couleur color){
		this.color = color;
	}
	
	//methods
	
	public String toString(){
		
		//utilisee seulement au debut du projet
		//a enlever
		
		if(this.color == Couleur.Blanc) {
			return "B";
		}else {
			return "N";
		}
	}
	
	public Couleur changeColor(){
		
		//change la couleur d'un pion
		//retourne la nouvelle couleur du pion
		
		if(this.color == Couleur.Noir){
			this.color = Couleur.Blanc;
			return this.color;
		}else{
			this.color = Couleur.Noir;
			return this.color;
		}
	}
}