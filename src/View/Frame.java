package View;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame {

	public Panel currentPanel;
	private View view;

	public Frame(View view) {

		this.view = view;
		currentPanel = new LoginPanel(view, this);
		
		//set the title of the frame
		this.setTitle("MadMax");
		
		//set the size of the frame
		this.setSize(400, 500);
		
		//shutdown the program when the frame is closed
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//default location
		this.setLocationRelativeTo(null);
		
		//background color
		this.setBackground(Color.white);
		
		//select the current panel
		this.setContentPane(currentPanel);
		
		//makes the frame visible
		this.setVisible(true);
		
		//remove the resizable propertie
		this.setResizable(false);

	}

	public Panel getCurrentPanel() {
		return currentPanel;
	}

	public void setCurrentPanel(Panel currentPanel) {
		this.currentPanel = currentPanel;
	}

	public void connectionSuccessful() {
		
		//set the size of the new frame
		this.setSize(1100, 800);
		this.currentPanel = new SelectPanel(view, this);
		setContentPane(currentPanel);
		setLocationRelativeTo(null);

	}

}
