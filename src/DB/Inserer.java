package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import GUI.ScoreModel;

public class Inserer {
	public Inserer() {
		
	}
	
	public void insert(ScoreModel model) {
		
		String pseudo = model.getPseudo();
		int score = model.getScore();
		
        try {
			Connection connexion = DriverManager.getConnection("jdbc:hsqldb:file:highscore", "sa",  "");
			Statement state = connexion.createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM highscore WHERE pseudoUser = '" + pseudo + "';");
			if(result.next() == false) {
				state.executeUpdate("INSERT INTO highscore (pseudoUser, scoreUser) VALUES ('" + pseudo + "', '" + score +"');");
			}else {
				if(result.getInt(3) < score) {
					state.executeUpdate("UPDATE highscore SET scoreUser = '" + score +"' WHERE pseudoUser = '" + pseudo +"';"); 
				}
			}
			state.executeQuery("SHUTDOWN");
			state.close();
        } catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
