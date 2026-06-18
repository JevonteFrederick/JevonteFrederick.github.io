//By: Jevonte Frederick

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Driver class to initialize database.
public class Driver {
	private static final String URL = "jdbc:sqlite:contacts.db";
    private static DatabaseManager dbManager = new DatabaseManager();

    //Returns connection to database
    public static Connection getConnect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

	public static void main(String[] args) {
        //Connect to existing Database or create a new one if none exists.
		try {
            System.out.println("Creating Database...");
            //Call method to create tables for database
            dbManager.createTables();
        } catch (Exception e) { //Catch any exceptions when running driver.
            System.err.println("Error has occured with the database...");
            e.printStackTrace();
        }
	}
}
