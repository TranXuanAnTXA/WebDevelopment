package com.baitap.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    // Thông tin kết nối (Thay đổi theo cấu hình máy của bạn)
    private final String serverName = "localhost";
    private final String dbName = "WEB_USER";
    private final String portNumber = "1433"; // Cổng mặc định của SQL Server
    private final String userID = "sa"; // Tài khoản đăng nhập SQL
    private final String password = "123456"; 

    public Connection getConnection() throws Exception {
        // Cấu trúc chuỗi kết nối URL cho SQL Server
        String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName + ";encrypt=false;";
        
        // Nạp Driver
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        
        // Trả về đối tượng Connection
        return DriverManager.getConnection(url, userID, password);
    }
    
    // Hàm main để chạy test thử kết nối ngay trong class
    public static void main(String[] args) {
        try {
            Connection conn = new DBConnection().getConnection();
            if (conn != null) {
                System.out.println("Kết nối Cơ sở dữ liệu thành công!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Kết nối thất bại. Vui lòng kiểm tra lại cấu hình.");
        }
    }
}