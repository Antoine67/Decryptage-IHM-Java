package View;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import Controller.Controller;

public class View {
	private Controller controller;
	
	public static int SUCCESS = 1;
	public static int FAIL = 2;
	
	private Frame frame;
	


	public View(Controller controller) {
		this.frame = new Frame(this);
		this.controller = controller;
	}
	
	
	public void displayMessage(String message, int state) {
		
		Panel panel = this.frame.getCurrentPanel();
		//System.out.println(message);
		if(state == SUCCESS) {
			panel.displaySuccessMessage(message);
		}else if (state == FAIL) {
			panel.displayErrorMessage(message);
		}
	}
	
	/* Auth */ 

	public void wantToConnect(String login, String password) {
		try {
			controller.wantToConnect(login, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void connectionSuccessful() {
		this.frame.connectionSuccessful();
	}
	
	public void connectionFailed() {
		displayMessage("Invalid creditentials ...", FAIL);
	}
	
	
	/* 1st window */
	public String getSourcePath() {
		return null;
	}
	
	public String getDestinationPath() {
		return null;
	}


	public void loadFile(File selectedFile) {
		controller.loadFile(selectedFile);
		
	}


	public boolean pcs_decrypter(String sourcePath, String destinationPath,SelectPanel selectPane) {
		//TODO Progress bar
		//ModalProgressBar prBar = new ModalProgressBar(this.frame);
		return controller.pcs_decrypter(sourcePath, destinationPath,selectPane);
	}
	
	
	public ArrayList<String> loadFilesToDecrypt() {
		return controller.loadFilesToDecrypt();
	}
	

	public void stopAllThreads() {
		System.out.println("sould stop threads");
		controller.stopAllThreads();
		
	}
	
}
