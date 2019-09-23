package Model;

import java.sql.SQLException;
import java.util.ArrayList;

public class Model {
	
	private DatabaseDAO databaseDAO = DatabaseDAO.getInstance();

	public Model() {
		
		try {
			ArrayList<User> data = databaseDAO.getRows("SELECT * FROM users","");
			System.out.println(data);
			
			
			System.out.println(databaseDAO.selectIDbyLoginPassword("admin","mdp123"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	 /* Composant d’accès aux données [CAD] */

	ArrayList<User> getRows(String rq_sql, String resultSetName) throws SQLException {
		return databaseDAO.getRows(rq_sql, resultSetName);
	}

	void actionRows(String rq_sql) /* – Optionnel */ {
		databaseDAO.actionRows(rq_sql);
	}

	/* Composant de manipulation de fichiers [Files] */

	String getData(String path) {
		return null; //TODO
	}

	void setData(String path) {
		
	}

	/* Composant de décryptage [Decrypt] */

	String decrypt(String data, String key) {
		return null; //TODO
	}

	/* Composant de mappage de la table personne [Map_P] */

	String selectIDbyLoginPassword(String login, String password) throws SQLException {
		return databaseDAO.selectIDbyLoginPassword(login, password);
	}

	/* Composant de mappage de la table dictionnaire [Map_Dic] */

	String selectWord (String word) {
		return null; // TODO
	}
	
	

}
