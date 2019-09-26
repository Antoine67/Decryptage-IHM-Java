import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.sql.SQLException;
import javax.swing.JFrame;
import Controller.Controller;
import Model.Model;
import View.View;

//Start of the program
public class Main {
	public static void main(String[] args) throws SQLException, InterruptedException {

		// We instantiate the different parts of the MVC pattern

		// New instance of the controller
		Controller controller = new Controller();

		// New instance of the Model
		Model model = new Model();

		// New instance of the view
		View view = new View(controller);

		// We send the view and the model to the controller
		controller.setModelAndView(model, view);

	}

}
