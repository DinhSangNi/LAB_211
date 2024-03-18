package connectToSQLServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    
    // Hàm kết nối tới SQL Server
    public static Connection getConnection(){
        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=StudentManager;user=sa;password=123456;encrypt = false;";
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(connectionUrl);
            System.out.println("thành công");
        }
        catch(Exception ex){
            System.out.println("lỗi");
        }
        
        return conn;
    }
    
    // Hàm ngắt kết nối tới SQLSerrver
    public static void closeConnection(Connection c){
        if(c != null){
            try {
                c.close();
            } catch (SQLException ex) {
               ex.printStackTrace();
            }
        }
    }
}
