package DB;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {
	public Connect() {
		
	}
	
	public static boolean existe(Connection connection, String nomTable) throws SQLException{
		boolean existe;
		DatabaseMetaData dmd = connection.getMetaData();
		ResultSet tables = dmd.getTables(connection.getCatalog(),null,nomTable,null);
		existe = tables.next();
		tables.close();
		return existe;	
	}
	
	@SuppressWarnings("deprecation")
	public void connectDB() {
		try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
             
            Connection connexion = DriverManager.getConnection("jdbc:hsqldb:file:highscore", "sa",  "");
            Statement state = connexion.createStatement();

            if(existe(connexion, "highscore")) {
                state.executeUpdate("CREATE TABLE highscore(idUser INTEGER IDENTITY PRIMARY KEY, pseudoUser VARCHAR (20), scoreUser INT)");
            }
         
            state.close();
             
        } catch (Exception e) {
            e.printStackTrace();
        }      
    }
}
