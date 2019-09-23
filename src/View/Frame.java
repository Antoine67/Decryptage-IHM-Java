package View;
import javax.swing.JFrame;

public class Frame extends JFrame {

	  public Frame(){                
	    this.setTitle("Interface de connexion utilisateur");
	    this.setSize(500, 200);
	    this.setLocationRelativeTo(null);               
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setContentPane(new Panel());
	    this.setVisible(true);
	  }     
	
}
