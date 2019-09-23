package View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
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
	  private TextField login = new TextField("admin");
	  private TextField password = new TextField("mdp123");
	  private JLabel loginlab = new JLabel("Login");
	  private JLabel passwordlab = new JLabel("Password");
	  private JButton signin = new JButton ("Sign In");
	  
	  
	  private View view;
	  
	public Panel(View view) {
		this.view = view;
		
		this.add(loginlab);
	    this.add(login);
	    this.add(passwordlab);
	    this.add(password);
	    this.add(signin);
	    this.setBackground(Color.white);
	    this.setVisible(true); 
	    this.setLayout(null);
	    

	    signin.setSize(350,40);
	    
	    loginlab.setBounds(100,205, 200,25);  
	    login.setBounds(100,240, 200,25);  
	    
	    passwordlab.setBounds(100,275, 200,25);  
	    password.setBounds(100,310, 200,25); 
	    password.setEchoChar('\u25CF');
	    
	    signin.setBounds(100,375, 200,30);  
	    
	    signin.addActionListener(new LoginListener());
	}


	public void paintComponent(Graphics g){
		
		try {
		      Image img = ImageIO.read(new File("assets/img/cesi.png"));
		      g.drawImage(img, 100, 50, this);
		    } 
		catch (IOException e) {
		      e.printStackTrace();
		    }

	}
	
	  
	
	
	  class LoginListener implements ActionListener{
		    public void actionPerformed(ActionEvent e) {
		    	view.wantToConnect(login.getText(),password.getText());
		    }
		  }
	  
	  
	  
	  
	  
	  
}


 