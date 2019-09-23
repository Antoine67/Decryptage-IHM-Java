package View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
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

public class Panel extends JPanel {
	  private JFormattedTextField login = new JFormattedTextField(NumberFormat.getIntegerInstance());
	  private JFormattedTextField password = new JFormattedTextField(NumberFormat.getPercentInstance());
	  private JLabel loginlab = new JLabel("Login");
	  private JLabel passwordlab = new JLabel("Password");
	  private JButton signin = new JButton ("Sign In");
	  private JPanel thisainer = new JPanel();
	  
	public void paintComponent(Graphics g){
		
		try {
		      Image img = ImageIO.read(new File("D:\\eclipse-workspace\\GUI\\src\\images\\cesi.png"));
		      g.drawImage(img, 100, 50, this);
		    } 
		catch (IOException e) {
		      e.printStackTrace();
		    }
		
	    Font police = new Font("Arial", Font.BOLD, 14);
	    
	    login.setFont(police);
	    login.setPreferredSize(new Dimension(150, 30));
	    
	    password.setPreferredSize(new Dimension(150, 30));
	    
	    signin.addActionListener(new BoutonListener());
	    
	    this.add(loginlab);
	    this.add(login);
	    this.add(passwordlab);
	    this.add(password);
	    this.add(signin);
	    this.setBackground(Color.white);
	    this.setVisible(true); 
	}
	
	
	
	  class BoutonListener implements ActionListener{
		    public void actionPerformed(ActionEvent e) {
		      System.out.println("Login : " + login.getText());
		      System.out.println("Password : " + password.getText());
		    }
		  }
	  
	  
	  
	  
}


 