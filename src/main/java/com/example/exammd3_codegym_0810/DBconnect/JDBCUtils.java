package com.example.exammd3_codegym_0810.DBconnect;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

public class JDBCUtils {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/product_management?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "Khongnho1@";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            //load driver, load MySQL connect driver into memory
            Class.forName("com.mysql.cj.jdbc.Driver");//
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
