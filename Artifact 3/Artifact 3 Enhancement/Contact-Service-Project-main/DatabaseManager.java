//By Jevonte Frederick

import java.sql.*;

//Class for managing the database
public class DatabaseManager {
    private static Connection connect = null;
    private static Statement statement = null;

    //Create intial tables for database
    public void createTables() {
        try {
            Class.forName("org.sqlite.JDBC");
            connect = DriverManager.getConnection("jdbc:sqlite:contacts.db");
            statement = connect.createStatement();
            System.out.println("Creating database tables");
            
            //Create Appointment Table if none exists
            String sqlAppointment = "CREATE TABLE IF NOT EXISTS APPOINTMENTS " +
                    "( APPOINTMENT_ID TEXT PRIMARY KEY NOT NULL UNIQUE," +
                    " DATE  TEXT    NOT NULL, " +
                    " DESCRIPTION   TEXT    NOT NULL)";

            statement.executeUpdate(sqlAppointment);
            
            //Create Contact Table if none exists
            String sqlContact = "CREATE TABLE IF NOT EXISTS CONTACTS " +
                    "( CONTACT_ID TEXT PRIMARY KEY NOT NULL UNIQUE," +
                    " FIRST_NAME  TEXT    NOT NULL, " +
                    " LAST_NAME  TEXT    NOT NULL, " +
                    " PHONE_NUMBER  TEXT    NOT NULL, " +
                    " ADDRESS   TEXT    NOT NULL)";

            statement.executeUpdate(sqlContact);

            //Create Task table if none exists
            String sqlTask = "CREATE TABLE IF NOT EXISTS TASKS " +
                    "( TASK_ID TEXT PRIMARY KEY NOT NULL UNIQUE," +
                    " NAME  TEXT    NOT NULL, " +
                    " DESCRIPTION   TEXT    NOT NULL)";
            statement.executeUpdate(sqlTask);

            statement.close();
            connect.close();
            System.out.println("Tables successfully created.");
        } catch (Exception e) { //Catch any exceptions when trying to create tables
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
