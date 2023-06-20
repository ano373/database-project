package com.example.jobopening_v1;


import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ResumeViewController {


    @FXML
    private ImageView iv;



     void retrieveImageDataFromDatabase(int ID, int JobOpeningID) {
        try {
            Connection connect = SQLServerConnection.startConnection();
            String query = "SELECT Resume " +
                            "FROM Application " +
                            "WHERE ID = " + ID;

            ResultSet result =  SQLServerConnection.DoQuery(query,'S');


            byte[] imageData = null;
            if (result.next()) {
                imageData = result.getBytes("Resume");
            }
            result.close();
            connect.close();

            if (imageData != null) {
                Image image = new Image(new ByteArrayInputStream(imageData));
                iv.setImage(image);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void initialize() {}



}

