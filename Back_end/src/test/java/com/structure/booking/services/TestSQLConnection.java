package com.structure.booking.services;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestSQLConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1:3306/booking_driver?allowPublicKeyRetrieval=true";
        String user = "root";
        String password = "root";
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Kết nối thành công!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
