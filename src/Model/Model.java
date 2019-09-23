package Model;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class Model {
	
	private DatabaseDAO databaseDAO = DatabaseDAO.getInstance();
	
	/*Dictionary as string then cut and store it as an ArrayList*/

	private ArrayList<String> dictionary = new ArrayList<String>();
	
	
	public Model() {
		
		
		
		try {
			ArrayList<User> data = databaseDAO.getRows("SELECT * FROM users","");
			System.out.println(data);

			dictionary = cutAndStoreAsArray(getData("assets/dictionnaireDeMots.txt"), "\n");
			System.out.println("^"+dictionary.get(26)+"^");
			
			System.out.println("^"+selectWord("abaisse-langue")+"^");
			
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
			return "Fichier introuvable ! ("+path+")"+ e.toString();
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
		if(dictionary.contains(word)) {
			return word;
		}else return null;
	}
	
	
	private ArrayList<String> cutAndStoreAsArray(String str, String divider) {
		String array[]; 
		str = str.replace("\r", ""); /* suppression des retours chariots */
		array = str.split(divider);
		System.out.println(array);
		

		
		ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(array));
		return arrayList;
	}
	
	

}
