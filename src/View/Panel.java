package View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Panel extends JPanel {
	
	
	protected JLabel messageDisplayer = new JLabel("Enter your creditentials :", JLabel.CENTER);
	  
	protected View view;
	protected Frame frame;

	  
	public Panel(View view,Frame frame) {
		this.view = view;
		this.frame = frame;
		
	    this.add(messageDisplayer);
	    this.setBackground(Color.white);
	    this.setVisible(true); 
	    this.setLayout(null);
	    

	    
	    messageDisplayer.setBounds(10,110,380,100 );
	}



	public void displayErrorMessage(String message) {
		messageDisplayer.setBackground(Color.red);
		messageDisplayer.setText(message);
		frame.repaint();
		frame.revalidate();
	}


	public void displaySuccessMessage(String message) {
		messageDisplayer.setBackground(Color.green);
		messageDisplayer.setText(message);
	}
	  
	  
	  
	  
	  
	  
}


 