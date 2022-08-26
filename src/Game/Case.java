package Game;


public class Case {
	
	//attributes
	private boolean free;
	private Pion p;
	private int x;
	private int y;
	
	//constructors
	public Case(int x, int y){	//crée une case vide
		if(0 <= x && x < 8 && 0 <= y && y < 8){
			this.free = true;
			this.setX(x);
			this.setY(y);
		}else{
			//ne rien faire;
		}
	}
	public Case(int x, int y, Pion p){	//crée une case avec un pion
		if(0 <= x && x < 8 && 0 <= y && y < 8){
			this.free = false;
			this.p = p;
			this.setX(x);
			this.setY(y);
		}else{
			//ne rien faire
		}
	}
	
	//getters
	public boolean isFree(){
		return this.free;
	}
	
	public Pion getPion(){
		return this.p;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	//setters
	public void setFree(boolean free){
		this.free = free;
	}
	public void setP(Pion p){
		this.p = p; 
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	//methods
	public String toString() {
		
		//utilisee au debut du projet
		//a enlever
		
		if(this.p != null) {
			return "[" + this.p.toString() + "]";
		}else {
			return "[ ]";

		}
	}
	
	public Boolean changeFree(){
		
		//change l'êtat de remplissage de la case
		//retourne le nouvel êtat
		
		if(this.free == true){
			this.free = false;
			return this.free;
		}else{
			this.free = true;
			return this.free;
		}
	}
	
	public boolean isCorner() {
		if((this.x == 0 && this.y == 0)
			||(this.x == 7 && this.y == 7)
			||(this.x == 0 && this.y == 7)
			||(this.x == 7 && this.y == 0)) {
			return true;
		}else {
			return false;
		}
		
	}
	
	public boolean isNextCorner() {
		if((this.x == 1  && this.y == 0)
			||(this.x == 6 && this.y == 0)
			||(this.x == 0 && this.y == 1)
			||(this.x == 1 && this.y == 1)
			||(this.x == 6 && this.y == 1)
			||(this.x == 7 && this.y == 1)
			||(this.x == 0 && this.y == 6)
			||(this.x == 1 && this.y == 6)
			||(this.x == 6 && this.y == 6)
			||(this.x == 7 && this.y == 6)
			||(this.x == 1 && this.y == 7)
			||(this.x == 6 && this.y == 7)){
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isNextNextCorner() {
		if((this.x == 2 && this.y == 0)
			||(this.x == 5 && this.y == 0)
			||(this.x == 2 && this.y == 1)
			||(this.x == 5 && this.y == 1)
			||(this.x == 0 && this.y == 2)
			||(this.x == 1 && this.y == 2)
			||(this.x == 2 && this.y == 2)
			||(this.x == 5 && this.y == 2)
			||(this.x == 6 && this.y == 2)
			||(this.x == 7 && this.y == 2)
			||(this.x == 0 && this.y == 5)
			||(this.x == 1 && this.y == 5)
			||(this.x == 2 && this.y == 5)
			||(this.x == 5 && this.y == 5)
			||(this.x == 6 && this.y == 5)
			||(this.x == 7 && this.y == 5)
			||(this.x == 2 && this.y == 6)
			||(this.x == 5 && this.y == 6)
			||(this.x == 2 && this.y == 7)
			||(this.x == 5 && this.y == 7)) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isBorder() {
		if((this.x == 0 && this.y >= 0 && this.y <= 7)
			||(this.x == 7 && this.y >= 0 && this.y <= 7)
			||(this.y == 0 && this.x >= 0 && this.x <= 7)
			||(this.y == 7 && this.x >= 0 && this.x <= 7)) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isNextBorder() {
		if((x == 1 && y < 7 && y > 0)
			||(x == 6 && y < 7 && y > 0)
			||(y == 1 && x < 7 && x > 0)
			||(y == 6 && x < 7 && x > 0)) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isCommon() {
		if(this.isBorder()
			||this.isCorner()
			||this.isNextBorder()
			||this.isNextCorner()
			||this.isNextNextCorner()) {
			return false;
		}else {
			return true;
		}
	}
}
