import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.sql.SQLException;

import javax.swing.JFrame;

import Controller.Controller;
import Model.Model;
import View.View;
 
public class Main {
  public static void main(String[] args) throws SQLException{
	  
	  Controller controller = new Controller();
	  
	  Model model = new Model();
	  
	  View view = new View(controller);
	  

	  //Mot inital
	  int[]array = model.encrypt("CECI","qw");
	  
	  //Crypté
	  System.out.println(intArrayToString(array));
	  
	  //Décrypté
	  System.out.println(intArrayToString(model.encrypt(intArrayToString(array), "qw")));
          
	  
	  //controller.setModelAndView(model, view);
	  
	  
  
  }   

  /**
   * Convert int array to String (ASCII)
   * "50,50,50,62" to "222<"
   * @param array
   * @return
   */
  private static String intArrayToString(int[] array) {
	  StringBuilder str = new StringBuilder();
	  for(int i=0; i<array.length; i++) {
		  str.append((char)array[i]);
	  }
	  return str.toString();
  }
  
   
  
  
  
  
  
  
  
  
}