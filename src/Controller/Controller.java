package Controller;

import java.sql.SQLException;

import Model.Model;
import View.View;

public class Controller {
	private Model model;
	private View view;
	private Decrypter decrypter;

	public Controller() throws SQLException {
		this.decrypter = new Decrypter();
		this.decrypter.letsDecrypt();
	}
	
	public void wantToConnect(String login, String password) throws SQLException {
		//isConnected is the returned attribute
		Boolean isConnected = false;
		
		//Search the login and password in the view
		isConnected = this.pcs_authentifier(login, password);
		
		//Tell the View if the user is connected or not
		if(isConnected) {
			view.connectionSuccessful();
		}
		else {
			view.connectionFailed();
		}
	}
	
	public void wantToDecript() {
		//Tell to the user that the file is decrypted
		if(pcs_decrypter(view.getSourcePath(), view.getDestinationPath())) {
			view.displayMessage("Succes", view.SUCCESS);
		}
		else {
			view.displayMessage("Failed", view.FAIL);
		}
	}
	
	public Boolean pcs_authentifier(String login, String password) throws SQLException {
		//Check if this login + password exist
		if(model.selectIDbyLoginPassword(login, password) != null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public Boolean pcs_decrypter(String source_path, String destination_path) {
		String textCrypted = new String();
		String textUncrypted = new String();
		//Get the crypted file
		textCrypted = model.getData(source_path);
		
		//After the decrypter
		model.setData(destination_path, textUncrypted);
		return true;
	}

	public void setModelAndView(Model model, View view) {
		this.model = model;
		this.view = view;
		
	}

}


