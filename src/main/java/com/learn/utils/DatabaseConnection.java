package com.learn.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/sportFlow";
    private static final String USER = "root";  
    private static final String PASSWORD = "admin"; 
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); // Charger le driver MySQL
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Connexion réussie à la base de données !");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                System.out.println("Erreur de connexion à la base de données !");
            }
        }
        return connection;
    }
}
