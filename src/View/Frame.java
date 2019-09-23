package View;

import java.awt.Color;

import javax.swing.JFrame;


public class Frame extends JFrame {

	  public Frame(){
		this.setTitle("MadMax");
		this.setSize(400, 500);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.setBackground(Color.white);
	    this.setContentPane(new Panel());
	    this.setVisible(true);
	  }       

}
