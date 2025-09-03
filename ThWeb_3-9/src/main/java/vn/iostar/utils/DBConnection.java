package vn.iostar.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private final static String serverName = "localhost";   // Server name
    private final static String dbName = "ThWeb_27_08";     // Database name
    private final static String portNumber = "1433";        // Default SQL Server port
    private final static String instance = "";              // Nếu dùng SQLExpress thì để "SQLEXPRESS"

    public static Connection getConnection() {
        Connection conn = null;
        try {
            String url;
            if (instance != null && !instance.trim().isEmpty()) {
                url = "jdbc:sqlserver://" + serverName + "\\" + instance + ":" + portNumber
                    + ";databaseName=" + dbName
                    + ";integratedSecurity=true"
                    + ";encrypt=true;trustServerCertificate=true";
            } else {
                url = "jdbc:sqlserver://" + serverName + ":" + portNumber
                    + ";databaseName=" + dbName
                    + ";integratedSecurity=true"
                    + ";encrypt=true;trustServerCertificate=true";
            }

            // Load driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn; 
    }

    public static void main(String[] args) {
        Connection conn = getConnection();
        if (conn != null) {
            System.out.println("✅ Kết nối thành công tới database!");
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("❌ Kết nối thất bại!");
        }
    }
}
