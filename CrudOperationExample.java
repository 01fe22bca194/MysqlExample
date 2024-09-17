import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// MySQL Connection Class
public class CrudOperationExample {
    // Method to establish a connection to the MySQL database
    public static Connection getConnection() {
        Connection conn = null;
        try {
            String url = "jdbc:mysql://localhost:3306/demo";  // Replace with your database name
            String username = "root";  // Replace with your MySQL username
            String password = "Jain@2321";  // Replace with your MySQL password
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to MySQL Database.");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
        return conn;
    }

    // Main method to perform CRUD operations
    public static void main(String[] args) {
        try (Connection conn = CrudOperationExample.getConnection(); Statement stmt = conn.createStatement()) {
            
            // 1. Creating Department Table
            String createDeptTable = "CREATE TABLE IF NOT EXISTS Department (Did INT PRIMARY KEY, Dname VARCHAR(100))";
            stmt.execute(createDeptTable);
            
            // 2. Creating Employee Table
            String createEmpTable = "CREATE TABLE IF NOT EXISTS Employee (Eid INT PRIMARY KEY, Ename VARCHAR(100), Salary DOUBLE, Address VARCHAR(100), Did INT, FOREIGN KEY (Did) REFERENCES Department(Did))";
            stmt.execute(createEmpTable);
            
            // 3. Inserting 5 Rows into Department Table
            String insertDept = "INSERT INTO Department (Did, Dname) VALUES " +
                                "(1, 'IT'), " +
                                "(2, 'Operations'), " +
                                "(3, 'Research'), " +
                                "(4, 'Support'), " +
                                "(5, 'Legal')";
            stmt.executeUpdate(insertDept);
            
            // 4. Inserting 5 Rows into Employee Table
            String insertEmp = "INSERT INTO Employee (Eid, Ename, Salary, Address, Did) VALUES " +
                               "(201, 'Alice Smith', 52000, 'Chicago', 1), " +
                               "(202, 'Bob Martin', 67000, 'Boston', 2), " +
                               "(203, 'Charlie Brown', 75000, 'Dallas', 3), " +
                               "(204, 'Diana Prince', 59000, 'San Francisco', 4), " +
                               "(205, 'Ethan Hunt', 68000, 'Los Angeles', 5)";
            stmt.executeUpdate(insertEmp);
            
            // 5. Select Query: Fetching Data from Employee Table
            String selectQuery = "SELECT * FROM Employee";
            ResultSet rs = stmt.executeQuery(selectQuery);
            
            System.out.println("Employee Data before Update:");
            // Printing the fetched data
            while (rs.next()) {
                System.out.println(rs.getInt("Eid") + " | " + rs.getString("Ename") + " | " + rs.getDouble("Salary") + " | " + rs.getString("Address") + " | " + rs.getInt("Did"));
            }

            // 6. Update Operation: Updating Salary of an Employee (Eid = 203)
            String updateQuery = "UPDATE Employee SET Salary = 80000 WHERE Eid = 203";
            int rowsAffected = stmt.executeUpdate(updateQuery);
            System.out.println("Rows affected by update: " + rowsAffected);

            // 7. Select Query after Update
            ResultSet rsAfterUpdate = stmt.executeQuery(selectQuery);
            System.out.println("Employee Data after Update:");
            while (rsAfterUpdate.next()) {
                System.out.println(rsAfterUpdate.getInt("Eid") + " | " + rsAfterUpdate.getString("Ename") + " | " + rsAfterUpdate.getDouble("Salary") + " | " + rsAfterUpdate.getString("Address") + " | " + rsAfterUpdate.getInt("Did"));
            }

            // 8. Delete Operation: Deleting Employee with Eid = 205
            String deleteQuery = "DELETE FROM Employee WHERE Eid = 205";
            int rowsDeleted = stmt.executeUpdate(deleteQuery);
            System.out.println("Rows deleted: " + rowsDeleted);

            // 9. Select Query after Delete
            ResultSet rsAfterDelete = stmt.executeQuery(selectQuery);
            System.out.println("Employee Data after Delete:");
            while (rsAfterDelete.next()) {
                System.out.println(rsAfterDelete.getInt("Eid") + " | " + rsAfterDelete.getString("Ename") + " | " + rsAfterDelete.getDouble("Salary") + " | " + rsAfterDelete.getString("Address") + " | " + rsAfterDelete.getInt("Did"));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
