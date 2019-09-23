package Controller;

import java.sql.SQLException;

import Model.Model;
import View.View;

public class Controller {
	private Model model;
	private View view;

	public Controller(Model model, View view) throws SQLException {
		this.model = model;
		this.view = view;
	}
	
	public void wantToConnect() throws SQLException {
		//isConnected is the returned attribute
		Boolean isConnected = new Boolean(isConnected);
		
		//Search the login and password in the view
		isConnected = this.pcs_authentifier(view.getLogin(), view.getPassword());
		
		//Tell the View if the user is connected or not
		if(isConnected) {
			view.isConnected(view.getLogin());
		}
		else {
			view.isntConnected();
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

}


