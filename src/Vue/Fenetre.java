package Vue;

import javax.swing.JFrame;

public class Fenetre extends JFrame {
  public Fenetre(){                
    this.setTitle("Interface de connexion utilisateur");
    this.setSize(500, 200);
    this.setLocationRelativeTo(null);               
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setContentPane(new Panneau());

    this.setVisible(true);
  }     
}