package View;

import java.sql.SQLException;

import javax.swing.JPanel;

import Controller.Controller;

public class View {
	private Controller controller;
	
	public static int SUCCESS = 1;
	public static int FAIL = 2;
	
	private Frame frame;
	


	public View() {
		this.frame = new Frame(this);
	}
	
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	
	public void displayMessage(String message, int state) {
		if(state == SUCCESS) {
			
		}else if (state == FAIL) {
			
		}
	}
	
	/* Auth */ 

	public void wantToConnect(String login, String password) {
		try {
			controller.wantToConnect(login, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void connectionSuccessful() {
		
	}
	
	public void connectionFailed() {
		
	}
	
	
	/* 1st window */
	public String getSourcePath() {
		return null;
	}
	
	public String getDestinationPath() {
		return null;
	}
	
	
}
