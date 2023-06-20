package com.example.jobopening_v1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;
import javafx.scene.image.Image;

public class HelloApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SignIN.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        File cssFile = new File("E:/1- College/2___2nd term/db_project/JobOpening_V1/src/main/java/com/example/jobopening_v1/companydashboarddesign.css");
        scene.getStylesheets().add(cssFile.toURI().toURL().toExternalForm());

        stage.setTitle("Jobs");
        stage.setScene(scene);







        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
