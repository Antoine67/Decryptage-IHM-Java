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
	  controller.setModelAndView(model, view);
  
  }   
  
/*

  private static int[] string2Arr(String str) {
      String[] sarr = str.split(",");
      int[] out = new int[sarr.length];
      for (int i = 0; i < out.length; i++) {
          out[i] = Integer.valueOf(sarr[i]);
      }
      return out;
  }
*/
  
  
  
  
  
  
  
  
  
  
  
}