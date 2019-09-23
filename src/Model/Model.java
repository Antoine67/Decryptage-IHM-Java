package Model;

import java.sql.SQLException;
import java.util.ArrayList;

public class Model {
	
	private DatabaseDAO databaseDAO = DatabaseDAO.getInstance();

	public Model() {
		
		try {
			ArrayList<User> data = databaseDAO.getRows("SELECT * FROM users","");
			System.out.println(data);
			
			
			System.out.println(databaseDAO.selectIDbyLoginPassword("admin","mdp123"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
