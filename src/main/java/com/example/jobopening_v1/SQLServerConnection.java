package com.example.jobopening_v1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class SQLServerConnection {
    private Connection connection;

    public Connection startConnection() throws SQLException {
        String serverName = "DESKTOP-IVFNSF4";
        String databaseName = "jobOpeningPart";
        String url = "jdbc:sqlserver://" + serverName + ";DatabaseName=" +
                databaseName + ";encrypt=false;integratedSecurity=true;";

        connection = DriverManager.getConnection(url);
        System.out.println("Connected to the database.");

        return connection;
    }

    public void executeStatement(String sql) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }
    public ResultSet executeQuery(String sql) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            return statement.executeQuery(sql);
        }
    }
    public static void main(String[] args) {
        SQLServerConnection conn = new SQLServerConnection();
    }
}
