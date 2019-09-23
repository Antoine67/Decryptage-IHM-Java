import java.sql.SQLException;

import javax.swing.JFrame;

import Controller.Controller;
import Model.Model;
import View.View;
 
public class Main {
  public static void main(String[] args) throws SQLException{
	  Controller controller = new Controller(new Model(), new View());
  }       
}