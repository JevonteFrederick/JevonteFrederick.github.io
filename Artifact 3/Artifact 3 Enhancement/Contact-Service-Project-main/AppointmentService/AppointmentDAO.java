package AppointmentService;

//By Jevonte Frederick

import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

// Data Access Object class for Appointments
public class AppointmentDAO {
    private static Connection connect = null;
    private static Statement statement = null;

    // Method to perform SQL Operation for Create or Delete
    public boolean SQLOperation(String query) throws SQLException {
        // boolean to determine SQL operation success
        boolean operationComplete = false;

        // Connect to database and execute operation. Close connection when done.
        try {
            connect = DriverManager.getConnection("jdbc:sqlite:contacts.db");
            connect.setAutoCommit(false);
            statement = connect.createStatement();
            int queryResult = statement.executeUpdate(query);

            if(queryResult == 0) {
            	// If no results are returned operation, is false
            	operationComplete = false;
            }
            else {
            	// Set to true if no exception thrown and results are returned
            	operationComplete = true;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        connect.commit();
        statement.close();
        connect.close();
        return operationComplete;
    }

    // Add an Appointment to Appointment table in the database
    public boolean addAppointment(Appointment appointment) {
        // Format appointment date
        LocalDateTime date = LocalDateTime.now();
        String dateFormated = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        
        //boolean to return add operation result
        boolean appointmentAdded = false; 

        // String for Create operation
        String sql = "INSERT INTO APPOINTMENTS (APPOINTMENT_ID,DATE,DESCRIPTION) " +
                "VALUES (" + appointment.getAppointmentID() +
                ", '" + dateFormated + "'" +
                ", '" + appointment.getDescription() + "'" +
                ");";

        try {
            if (SQLOperation(sql)){
                System.out.println("Added appointment ID: " + appointment.getAppointmentID());
                appointmentAdded =  true;
            }
            else{
                System.out.println("Could not add appointment ID: " + appointment.getAppointmentID());
                appointmentAdded = false;
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        // return operation result when done
        return appointmentAdded;
    }

    // Delete an Appointment from the Appointment table in the database
    public boolean deleteAppointment(String ID) {
        // String for Delete operation
        String sql = "DELETE FROM APPOINTMENTS WHERE APPOINTMENT_ID=" + ID + ";";
        
        //boolean to return delete operation result
        boolean appointmentDeleted = false;
        
        try {
            if(SQLOperation(sql) == true) {
            	System.out.println("Deleted appointment ID: " + ID);
                appointmentDeleted = true;
            }
            else {
            	System.out.println("Could not delete appointment ID: " + ID);
                appointmentDeleted = false;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        // return operation when done
        return appointmentDeleted;
    }

    // Read all appointments in the database. Then close connection
    public void readAppointments() {
        try {
            connect = DriverManager.getConnection("jdbc:sqlite:contacts.db");
            connect.setAutoCommit(false);
            statement = connect.createStatement();
            String sql = "SELECT * FROM APPOINTMENTS;";
            ResultSet query = statement.executeQuery(sql);

            // Loop while ResultSet has query results.
            while (query.next()) {
                // Print data of each Appointment returned from database.
                System.out.println("ID: " + query.getString("APPOINTMENT_ID"));
                System.out.println("Date: " + query.getString("DATE"));
                System.out.println("Description: " + query.getString("DESCRIPTION") + "\n");
            }

            statement.close();
            connect.close();
            query.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
