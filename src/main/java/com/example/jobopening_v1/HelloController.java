package com.example.jobopening_v1;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.event.ActionEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class HelloController implements Initializable {

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    @FXML
    private Label myLabel;

    private String myString = "Hello, World!"; // Your string variable

    @Override
    public void initialize(URL location, ResourceBundle resources) {
     //   myLabel.setText(myString);
    }


    @FXML
    private Button butoon;

    public void buttonclciked(ActionEvent e){
        butoon.setText("you fucken bitch");
        System.out.println("sfdfs");
    }


}