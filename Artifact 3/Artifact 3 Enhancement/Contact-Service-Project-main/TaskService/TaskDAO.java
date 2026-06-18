package TaskService;

//By Jevonte Frederick

import java.sql.*;

// Data Access Object class for Tasks
public class TaskDAO {
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

    // Add a Task to Task table in the database
    public boolean addTask(Task task) {
        // boolean to return add operation result
        boolean taskAdded = false;

        // String for Create operation
        String sql = "INSERT INTO TASKS (TASK_ID,NAME,DESCRIPTION) " +
                "VALUES (" + task.getTaskID() +
                ", '" + task.getName() + "'" +
                ", '" + task.getDescription() + "'" +
                ");";

        try {
            if (SQLOperation(sql)) {
                System.out.println("Added Task ID: " + task.getTaskID());
                taskAdded = true;
            } else {
                System.out.println("Could not add Task ID: " + task.getTaskID());
                taskAdded = false;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        // return operation result when done
        return taskAdded;
    }

    // Delete a Task from the Task table in the database
    public boolean deleteTask(String ID) {
        // String for Delete operation
        String sql = "DELETE FROM TASKS WHERE TASK_ID=" + ID + ";";

        // boolean to return delete operation result
        boolean taskDeleted = false;

        try {
            if (SQLOperation(sql) == true) {
                System.out.println("Deleted task ID: " + ID);
                taskDeleted = true;
            } else {
                System.out.println("Could not delete task ID: " + ID);
                taskDeleted = false;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        // return operation result when done
        return taskDeleted;
    }

    // Edit a Task from the Task table in the database
    public boolean updateTask(String ID, String name, String description) {
        // String for update operation
        String sql = "UPDATE TASKS SET " +
                "NAME = '" + name + "', " +
                "DESCRIPTION = '" + description + "' " +
                "WHERE TASK_ID=" + ID + ";";

        // boolean to return update operation result
        boolean taskUpdated = false;

        try {
            if (SQLOperation(sql) == true) {
                System.out.println("Deleted task ID: " + ID);
                taskUpdated = true;
            } else {
                System.out.println("Could not delete task ID: " + ID);
                taskUpdated = false;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        // return operation result when done
        return taskUpdated;
    }

    // Read all tasks in the database. Then close connection
    public void readTasks() {
        try {
            connect = DriverManager.getConnection("jdbc:sqlite:contacts.db");
            connect.setAutoCommit(false);
            statement = connect.createStatement();
            String sql = "SELECT * FROM TASKS;";
            ResultSet query = statement.executeQuery(sql);

            // Loop while ResultSet has query results.
            while (query.next()) {
                // Print data of each Task returned from database.
                System.out.println("ID: " + query.getString("TASK_ID"));
                System.out.println("Name: " + query.getString("NAME"));
                System.out.println("Last Name: " + query.getString("DESCRIPTION"));
            }

            statement.close();
            connect.close();
            query.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
