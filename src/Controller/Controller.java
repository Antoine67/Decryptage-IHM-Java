package Controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import Model.Model;
import View.SelectPanel;
import View.View;

public class Controller {
	private Model model;
	private View view;
	private Decrypter decrypter;
	private SelectPanel selectedPane;

	public static String folderToStoreFileToDecrypt = System.getProperty("user.dir") + "\\filesToDecrypt\\";

	private static String DEFAULT_MESSAGE_DECRYPTED = "Septembre 2019 - Cesi école d'ingénieurs \nDécrypté par le groupe 4 :\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n\n";
	private static String CLUE_ABOUT_KEY = "awqp";

	private int triedKeys = 0;

	public Controller() throws SQLException {

	}

	public void wantToConnect(String login, String password) throws SQLException {
		// isConnected is the returned attribute
		Boolean isConnected = false;

		// Search the login and password in the view
		isConnected = this.pcs_authentifier(login, password);

		// Tell the View if the user is connected or not
		if (isConnected) {
			view.connectionSuccessful();
		} else {
			view.connectionFailed();
		}
	}

	public void wantToDecrypt(SelectPanel selectPane) {
		// Tell to the user that the file is decrypted
		if (pcs_decrypter(view.getSourcePath(), view.getDestinationPath(), selectPane)) {
			view.displayMessage("Success", View.SUCCESS);
		} else {
			view.displayMessage("Failed", View.FAIL);
		}
	}

	public Boolean pcs_authentifier(String login, String password) throws SQLException {
		// Check if this login + password exist
		if (model.selectIDbyLoginPassword(login, password) != null) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean pcs_decrypter(String source_path, String destination_path, SelectPanel selectPane) {

		source_path = folderToStoreFileToDecrypt + "\\" + source_path;

		String textCrypted;
		String textUncrypted;

		// Get the crypted file
		textCrypted = model.getData(source_path);

		this.selectedPane = selectPane;
		triedKeys = 0;

		// launch the decrypter

		MultiThreading multiThreading = new MultiThreading(this, 4, textCrypted, CLUE_ABOUT_KEY);
		textUncrypted = multiThreading.launch();

		if (textUncrypted != null) {
			textUncrypted = DEFAULT_MESSAGE_DECRYPTED + textUncrypted;
			model.setData(destination_path, textUncrypted);
			return true;
		} else
			return false;

	}

	// We display the progress bar
	public void setProgressBarState(boolean b) {
		selectedPane.setProgressBarState(b);

	}

	// Count the number of keys that have been tested
	public void increaseTriedKey() {
		triedKeys++;
		selectedPane.setTriedKeys(triedKeys);

	}

	// We add a word when it is found
	public void addWordFound(String word, String key) {
		selectedPane.addWordFound(word, key);
	}

	public void setModelAndView(Model model, View view) throws InterruptedException {
		this.model = model;
		this.view = view;
		this.decrypter = new Decrypter(this);

	}

	// Method for loading a file
	public void loadFile(File selectedFile) {

		model.setData(folderToStoreFileToDecrypt + selectedFile.getName(), model.getData(selectedFile.getPath()));

	}

	// Loading the file to decrypt
	public ArrayList<String> loadFilesToDecrypt() {

		// we recover where to store the destination file
		try (Stream<Path> walk = Files.walk(Paths.get(folderToStoreFileToDecrypt))) {

			ArrayList<String> result = (ArrayList<String>) walk.filter(Files::isRegularFile).map(x -> x.toString())
					.collect(Collectors.toList());

			return result;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	// getter of the model
	public Model getModel() {
		return this.model;
	}

}
