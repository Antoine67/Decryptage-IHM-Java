package View;

import Controller.Controller;

public class View {
	private Controller controller;
	
	public static int SUCCESS = 1;
	public static int FAIL = 2;
	

	public View() {
		Frame frame = new Frame();
		
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
	public String getLogin() {
		return null;
	}
	
	public String getPassword() {
		return null;
	}
	
	
	public void connexionSuccessful() {
		
	}
	
	public void connexionFailed() {
		
	}
	
	
	/* 1st window */
	public String getSourcePath() {
		return null;
	}
	
	public String getDestinationPath() {
		return null;
	}
	
	
}
