package Model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author G4
 * @version 1.0
 */
final class DatabaseDAO {

    /** The instance. */
    private static DatabaseDAO instance;

    /** The login. */
    private static String                  user     = "root";

    /** The password. */
    private static String                  password = "";

    /** The url. */
    private static String                  url      = "jdbc:mysql://localhost:3306/decryptage?useSSL=false&serverTimezone=UTC";

    /** The connection. */
    private Connection                     connection;

    /** The statement. */
    private Statement                      statement;

    private DatabaseDAO() {
        this.open();
    }

    /**
     * Gets the single instance
     *
     * @return single instance
     */
    public static DatabaseDAO getInstance() {
        if (instance == null) {
            setInstance(new DatabaseDAO());
        }
        return instance;
    }

    /**
     * Sets the instance.
     *
     * @param instance
     *            the new instance
     */
    private static void setInstance(final DatabaseDAO instance) {
    	DatabaseDAO.instance = instance;
    }

    /**
     * Open.
     *
     * @return true, if successful
     */
    private boolean open() {
        try {
            this.connection = DriverManager.getConnection(DatabaseDAO.url, DatabaseDAO.user,
            		DatabaseDAO.password);
            this.statement = this.connection.createStatement();
            return true;
        } catch (final SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    /**
     * Execute query.
     *
     * @param query
     *            the query
     * @return the result set
     */
    public ResultSet executeQuery(final String query) {
        try {
            return this.getStatement().executeQuery(query);
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Prepare call.
     *
     * @param query
     *            the query
     * @return the java.sql. callable statement
     */
    public java.sql.CallableStatement prepareCall(final String query) {
        try {
            return this.getConnection().prepareCall(query);
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Execute update.
     *
     * @param query
     *            the query
     * @return the int
     */
    public int executeUpdate(final String query) {
        try {
            return this.statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void setConnection(final Connection connection) {
        this.connection = connection;
    }

    public Statement getStatement() {
        return this.statement;
    }

    /**
     * Sets the statement.
     *
     * @param statement
     *            the new statement
     */
    public void setStatement(final Statement statement) {
        this.statement = statement;
    }
    
    
    
    
    
    
    ArrayList<User> getRows(String rq_sql, String resultSetName) throws SQLException {
    	ResultSet rs = executeQuery(rq_sql);
    	ArrayList<User> rows = new ArrayList<User>();
    	while(rs.next()) {
    		rows.add(new User(rs.getInt("id"), rs.getString("user"), rs.getString("password")));
    	}
    	return rows;
    }
    
    void actionRows(String rq_sql) {
    	
    }
    
    String selectIDbyLoginPassword(String login, String password) throws SQLException {
    	ResultSet rs =  executeQuery("SELECT id FROM users WHERE user='"+login+"'AND password='"+password+"'");
    	if(rs.next()) {
    		 return rs.getString(1);
    	}else return null;
    }
    
    
    
    

}