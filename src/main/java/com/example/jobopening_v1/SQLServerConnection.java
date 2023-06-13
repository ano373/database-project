//package com.example.jobopening_v1;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.sql.ResultSet;
//
//public class SQLServerConnection {
//    private static Connection connection;
//
//    public static Connection  startConnection() throws SQLException {
//        String serverName = "DESKTOP-IVFNSF4";
//        String databaseName = "jobOpeningPart";
//        String url = "jdbc:sqlserver://" + serverName + ";DatabaseName=" +
//                databaseName + ";encrypt=false;integratedSecurity=true;";
//
//        connection = DriverManager.getConnection(url);
//        System.out.println("Connected to the database.");
//
//        return connection;
//    }
//
//
//    public void executeStatement(String sql) throws SQLException {
//        try (Statement statement = connection.createStatement()) {
//            statement.executeUpdate(sql);
//        }
//    }
//    public ResultSet executeQuery(String sql) throws SQLException {
//        try (Statement statement = connection.createStatement()) {
//            return statement.executeQuery(sql);
//        }
//    }
//    public static void main(String[] args) throws SQLException {
//        SQLServerConnection conn = new SQLServerConnection();
//        conn.startConnection();
//    }
//}

package com.example.jobopening_v1;

import java.sql.*;

public class SQLServerConnection {
    private static Connection connection;
    private static final String SERVER_NAME = "DESKTOP-IVFNSF4";
    private static final String DATABASE_NAME = "jobOpeningPart";

    public static Connection startConnection() throws SQLException {

  //      String serverName = "";
//        String databaseName = "";
//        String url = "jdbc:sqlserver://" + serverName + ";DatabaseName=" +
//                databaseName + ";encrypt=false;integratedSecurity=true;";
//
//        connection = DriverManager.getConnection(url);
        String url = "jdbc:sqlserver://" + SERVER_NAME + ";DatabaseName=" +
                DATABASE_NAME + ";encrypt=false;integratedSecurity=true;";

        connection = DriverManager.getConnection(url);
        System.out.println("Connected to the database.");

        return connection;
    }
    public static ResultSet DoQuery(String query, char type ) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = SQLServerConnection.startConnection();
            statement = connection.prepareStatement(query);
            switch (Character.toLowerCase(type)) {
                case 's' -> resultSet = statement.executeQuery();
                case 'd', 'u', 'i' -> statement.executeUpdate();
                default -> throw new IllegalArgumentException("Invalid query type: " + type);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return resultSet;
    }

    public static void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("Error while closing the connection: " + e.getMessage());
        }
    }
}

