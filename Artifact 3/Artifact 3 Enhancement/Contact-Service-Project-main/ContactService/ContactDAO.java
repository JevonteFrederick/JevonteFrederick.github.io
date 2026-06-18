package ContactService;

//By Jevonte Frederick

import java.sql.*;

// Data Access Object class for Contacts
public class ContactDAO {
    private static Connection connect = null;
    private static Statement statement = null;

    // Method to perform SQL Operation for Create, Update, or Delete.
    public boolean SQLOperation(String query) throws SQLException {
        // boolean to determine SQL operation success
        boolean operationComplete = false;

        // Connect to database and execute operation. Close connection when done.
        try {
            connect = DriverManager.getConnection("jdbc:sqlite:contacts.db");
            connect.setAutoCommit(false);
            statement = connect.createStatement();
            int queryResult = statement.executeUpdate(query);

            if (queryResult == 0) {
                // If no results are returned, operation is false
                operationComplete = false;
            } else {
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

    // Add a Contact to Contact table in the database
    public boolean addContact(Contact contact) {
        // boolean to return add operation result
        boolean contactAdded = false;

        // String for Create operation
        String sql = "INSERT INTO CONTACTS (CONTACT_ID,FIRST_NAME,LAST_NAME,PHONE_NUMBER,ADDRESS) " +
                "VALUES (" + contact.getContactID() +
                ", '" + contact.getFirstName() + "'" +
                ", '" + contact.getLastName() + "'" +
                ", '" + contact.getPhoneNumber() + "'" +
                ", '" + contact.getAddress() + "'" +
                ");";

        try {
            if (SQLOperation(sql)) {
                System.out.println("Added contact ID: " + contact.getContactID());
                contactAdded = true;
            } else {
                System.out.println("Could not add contact ID: " + contact.getContactID());
                contactAdded = false;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        //return operation result when done
        return contactAdded;
    }

    // Delete a Contact from the Contact table in the database
    public boolean deleteContact(String ID) {
        // String for Delete operation
        String sql = "DELETE FROM CONTACTS WHERE CONTACT_ID=" + ID + ";";

        // boolean to return delete operation result
        boolean contactDeleted = false;

        try {
            if (SQLOperation(sql) == true) {
                System.out.println("Deleted contact ID: " + ID);
                contactDeleted = true;
            } else {
                System.out.println("Could not delete appointment ID: " + ID);
                contactDeleted = false;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return contactDeleted;
    }

    // Edit a Contact from the Contact table in the database
    public boolean updateContact(String ID, String firstName, String lastName, String phoneNumber, String address) {
        // String for update operation
        String sql = "UPDATE CONTACTS SET " +
                "FIRST_NAME = '" + firstName + "', " +
                "LAST_NAME = '" + lastName + "', " +
                "PHONE_NUMBER = '" + phoneNumber + "', " +
                "ADDRESS = '" + address + "' " +
                "WHERE CONTACT_ID=" + ID + ";";

        // boolean to return update operation result
        boolean contactUpdated = false;

        try {
            if (SQLOperation(sql) == true) {
                System.out.println("Updated contact ID: " + ID);
                contactUpdated = true;
            } else {
                System.out.println("Could not update appointment ID: " + ID);
                contactUpdated = false;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        //return operation result when done
        return contactUpdated;
    }

    // Read all contacts in the database. Then close connection
    public void readContacts() {
        try {
            connect = DriverManager.getConnection("jdbc:sqlite:contacts.db");
            connect.setAutoCommit(false);
            statement = connect.createStatement();
            String sql = "SELECT * FROM CONTACTS;";
            ResultSet query = statement.executeQuery(sql);

            // Loop while ResultSet has query results.
            while (query.next()) {
                // Print data of each Contact returned from database.
                System.out.println("ID: " + query.getString("CONTACT_ID"));
                System.out.println("First Name: " + query.getString("FIRST_NAME"));
                System.out.println("Last Name: " + query.getString("LAST_NAME"));
                System.out.println("Phone Number: " + query.getString("PHONE_NUMBER"));
                System.out.println("Address: " + query.getString("ADDRESS") + "\n");
            }

            statement.close();
            connect.close();
            query.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
