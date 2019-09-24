package View;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Frame extends JFrame {

	
	public Panel currentPanel;
	private View view;
	
	
	
	
	  public Frame(View view){
		  
		this.view = view;
		currentPanel = new LoginPanel(view, this);  
		  
		this.setTitle("MadMax");
		this.setSize(400, 500);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.setBackground(Color.white);
	    this.setContentPane(currentPanel);
	    this.setVisible(true);
	    this.setResizable(false);
	    
	  }


	public Panel getCurrentPanel() {
		return currentPanel;
	}

	public void setCurrentPanel(Panel currentPanel) {
		this.currentPanel = currentPanel;
	}


	public void connectionSuccessful() {
		this.setSize(1100,800);
		this.currentPanel = new SelectPanel(view, this);
		setContentPane(currentPanel);
		setLocationRelativeTo(null);
		
		
	}       
	  
	  

}
