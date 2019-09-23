package Model;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Model {
	
	private DatabaseDAO databaseDAO = DatabaseDAO.getInstance();

	public Model() {
		
		try {
			ArrayList<User> data = databaseDAO.getRows("SELECT * FROM users","");
			System.out.println(data);
			
			/* Exemple d'utilisation:
			 * 
			System.out.println(databaseDAO.selectIDbyLoginPassword("admin","mdp123"));
			
			System.out.println(getData(".gitignore"));
			setData("testSetData","blabla");
			*/
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	 /* Composant d’accès aux données [CAD] */

	public ArrayList<User> getRows(String rq_sql, String resultSetName) throws SQLException {
		return databaseDAO.getRows(rq_sql, resultSetName);
	}

	public void actionRows(String rq_sql) /* Optionnel */ {
		databaseDAO.actionRows(rq_sql);
	}

	/* Composant de manipulation de fichiers [Files] */

	public String getData(String path) {

		try {
		FileReader file = new FileReader(path);
		StringBuilder str = new StringBuilder();
		
		int i; 
	    while ((i=file.read()) != -1) {
	      str.append((char) i); 
	    }
	    file.close();
	    return str.toString();
	    
	    
		}catch(FileNotFoundException e) {
			return e.toString();
		}catch (IOException e) {
			return e.toString();
		}
	  
	}

	public void setData(String path, String data) {
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(path));
			writer.write(data);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/* Composant de décryptage [Decrypt] */

	public String decrypt(String data, String key) {
		return null; //TODO
	}

	/* Composant de mappage de la table personne [Map_P] */

	public String selectIDbyLoginPassword(String login, String password) throws SQLException {
		return databaseDAO.selectIDbyLoginPassword(login, password);
	}

	/* Composant de mappage de la table dictionnaire [Map_Dic] */

	public String selectWord (String word) {
		return null; // TODO
	}
	
	

}
