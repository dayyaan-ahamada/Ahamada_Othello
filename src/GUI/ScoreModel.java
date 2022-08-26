package GUI;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ScoreModel {
	//attributes
	private SimpleStringProperty pseudo;
	private SimpleIntegerProperty score;
	
	//constructor
	public ScoreModel(String pseudo, int score) {
		this.pseudo = new SimpleStringProperty(pseudo);
		this.score = new SimpleIntegerProperty(score);
	}

	//getters
	public String getPseudo() {
		return this.pseudo.get();
	}
	public int getScore() {
		return score.get();
	}
	
	//setters
	public void setPseudo(String pseudo) {
		this.pseudo.set(pseudo);
	}
	public void setScore(int score) {
		this.score.set(score);
	}
	
}
