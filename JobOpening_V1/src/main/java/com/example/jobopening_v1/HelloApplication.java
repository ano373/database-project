package com.example.jobopening_v1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;
import javafx.scene.image.Image;

public class HelloApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        scene.getStylesheets().add("jobopening_v1companydashboarddesign.css");

        stage.setTitle("Jobs");
        stage.setScene(scene);



//        String insertStatement2 = "INSERT INTO Company (Company_Name, Location, Industry) " +
//                "VALUES ('XYZ Corporation', 'London', 'Finance')";
//        conn.executeStatement(insertStatement2);


//        PreparedStatement ps = conn.prepareStatement(selectStatement);
//
//
//        ResultSet rs = ps.executeQuery();
//        String companyName = "";
//        String industry = "";
//        if (rs.next()) {
//            companyName = rs.getString("Company_Name");
//            industry = rs.getString("Industry");
//
//            System.out.println("Company Name: " + companyName);
//            System.out.println("Industry: " + industry);
//        } else {
//            System.out.println("No results found.");
//        }

// Store the data in variables or use them as needed
//        System.out.println("Stored Company Name: " + companyName);
//        System.out.println("Stored Industry: " + industry);



        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
