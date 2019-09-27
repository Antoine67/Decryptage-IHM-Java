package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class Model {

	private DatabaseDAO databaseDAO = DatabaseDAO.getInstance();

	/* Dictionary as string then cut and store it as an ArrayList */

	private ArrayList<String> dictionary = new ArrayList<String>();
	
	private boolean useTheLarousse = false;

	public Model() {

		try {
			ArrayList<User> data = databaseDAO.getRows("SELECT * FROM users", "");
			System.out.println(data);

			dictionary = cutAndStoreAsArray(getData("assets/dictionnaireDeMots.txt"), "\n");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* Composant d'accès aux données [CAD] */

	public ArrayList<User> getRows(String rq_sql, String resultSetName) throws SQLException {
		return databaseDAO.getRows(rq_sql, resultSetName);
	}

	public void actionRows(String rq_sql) /* Optionnel */ {
		databaseDAO.actionRows(rq_sql);
	}

	/* Composant de manipulation de fichiers [Files] */

	public String getData(String path) {

		StringBuilder str = new StringBuilder();

		try (BufferedReader br = Files.newBufferedReader(Paths.get(path), Charset.forName("ISO-8859-1"))) {

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

	public int[] encrypt(String str, String key) {
		int[] output = new int[str.length()];
		for (int i = 0; i < str.length(); i++) {
			int o = (Integer.valueOf(str.charAt(i)) ^ Integer.valueOf(key.charAt(i % (key.length()))));// + '0';
			output[i] = o;
		}
		return output;
	}

	/* Composant de mappage de la table personne [Map_P] */

	public String selectIDbyLoginPassword(String login, String password) throws SQLException {
		return databaseDAO.selectIDbyLoginPassword(login, password);
	}

	public String selectWord(String word) {
		if (dictionary.contains(word)) {
			System.out.println("word : " + word);
			return word;
		} 
		else if(!selectWordWithError(word).equals(word) && useTheLarousse) {
			System.out.println("word2 : " + selectWordWithError(word));
			return selectWordWithError(word);
		} else
			return null;
	}
	
	
	public String selectWordWithError(String word) {
		int errors = 0;
		for(String n : dictionary) {
			if(n.length() == word.length()) {
				for(int y = 0; y < word.length(); y++) {
					if(n.charAt(y) != word.charAt(y)) {
						errors++;
					}
				}
				if(errors == 1) {
					return n;
				}
				else {
					errors = 0;
				}
			}
		}
		return word;
	}

	private ArrayList<String> cutAndStoreAsArray(String str, String divider) {
		String array[];
		str = str.replace("\r", ""); /* suppression des retours chariots */
		array = str.split(divider);

		ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(array));
		return arrayList;
	}
	
	public Boolean getUseTheLarousse() {
		return this.useTheLarousse;
	}
	
	public void setUseTheLarousse(Boolean useTheLarousse) {
		this.useTheLarousse = useTheLarousse;
	}

}
