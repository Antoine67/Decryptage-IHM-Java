import javax.swing.JFrame;

import Controller.Controller;
import Model.Model;
import Vue.Fenetre;
 
public class Main {
  public static void main(String[] args){
	  Controller controller = new Controller(new Model(), new Fenetre());
  }       
}