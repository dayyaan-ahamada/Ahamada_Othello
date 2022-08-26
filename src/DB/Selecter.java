package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import GUI.ScoreModel;

public class Selecter {
	public Selecter() {
		
	}
	
	public List<ScoreModel> select() {
		List<ScoreModel> result = new ArrayList<ScoreModel>();
		
		try {
			Connection connexion = DriverManager.getConnection("jdbc:hsqldb:file:highscore", "sa",  "");
			Statement state = connexion.createStatement();
			ResultSet DBresults = state.executeQuery("SELECT * FROM highscore;");
			while(DBresults.next()) {
				result.add(new ScoreModel(DBresults.getString("pseudoUser"), DBresults.getInt("scoreUser")));
			}
			state.close();
        } catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
