package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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
			
			
			
			/* Exemple d'utilisation:
			 
			 
			//Chercher en BDD la correspondance du login/password
			System.out.println(databaseDAO.selectIDbyLoginPassword("admin","mdp123"));
			
			
			//Manip fichiers
			System.out.println(getData(".gitignore"));
			setData("testSetData","blabla");
			
			//Trouver un mot dans le dico de mots
			System.out.println(selectWord("abaisse-laangue"));
			
			*/
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	
	
	
	 /* Composant d�acc�s aux donn�es [CAD] */

	public ArrayList<User> getRows(String rq_sql, String resultSetName) throws SQLException {
		return databaseDAO.getRows(rq_sql, resultSetName);
	}

	public void actionRows(String rq_sql) /* Optionnel */ {
		databaseDAO.actionRows(rq_sql);
	}

	/* Composant de manipulation de fichiers [Files] */

	public String getData(String path) {

		StringBuilder str = new StringBuilder();
		
		
        try (BufferedReader br = Files.newBufferedReader(Paths.get(path))) {

            String line;
            while ((line = br.readLine()) != null) {
                str.append(line).append("\n");
            }

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        return str.toString();
	  
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

	/* Composant de d�cryptage [Decrypt] */

	public String decrypt(String data, String key) {
		StringBuilder sb = new StringBuilder();
		   for(int i = 0; i < data.length(); i++)
		   sb.append((char)(data.charAt(i) ^ key.charAt(i % (key.length()))));
		   return(sb.toString());
	}
	
	public String decrypt(int[] data, String key) {
	      String output = "";        
	      for(int i = 0; i < data.length; i++) {
	          output += (char) ((data[i] - 48) ^ (int) key.charAt(i % (key.length() )));
	      }
	      return output;
	  }
	
	 public int[] encrypt(String str, String key) {
	      int[] output = new int[str.length()];
	      for(int i = 0; i < str.length(); i++) {
	          int o = (Integer.valueOf(str.charAt(i)) ^ Integer.valueOf(key.charAt(i % (key.length() ))));// + '0';
	          output[i] = o;
	      }
	      return output;        
	  }
	 
	

	/* Composant de mappage de la table personne [Map_P] */

	public String selectIDbyLoginPassword(String login, String password) throws SQLException {
		return databaseDAO.selectIDbyLoginPassword(login, password);
	}

	/* Composant de mappage de la table dictionnaire [Map_Dic] */

	/**
	 * Check if a given word is in the french dictionary
	 * @param word to find in the dictionary
	 * @return the word if found, null in others case
	 */
	public String selectWord (String word) {
		if(dictionary.contains(word)) {
			return word;
		}else return null;
	}
	
	
	/**
	 * Cut a string and store it as an array 
	 * @param str initial string
	 * @param divider the divider between two words
	 * @return
	 */
	private ArrayList<String> cutAndStoreAsArray(String str, String divider) {
		String array[]; 
		str = str.replace("\r", ""); /* suppression des retours chariots */
		array = str.split(divider);
		

		
		ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(array));
		return arrayList;
	}
	
	

}
