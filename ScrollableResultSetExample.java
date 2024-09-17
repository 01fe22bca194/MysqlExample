import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ScrollableResultSetExample {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            
            String url = "jdbc:mysql://localhost:3306/demo";  
            String username = "root";  
            String password = "Jain@2321";  
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to MySQL Database.");

         
            String selectQuery = "SELECT * FROM Employee";
            pstmt = conn.prepareStatement(selectQuery, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = pstmt.executeQuery();

            rs.last();
            System.out.println("Last Row: " + rs.getString("Ename"));

         
            rs.first();
            System.out.println("First Row: " + rs.getString("Ename"));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}