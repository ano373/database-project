package com.example.jobopening_v1;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




public class SignINController {
    @FXML
    private Button close;

    @FXML
    private TextField email;

    @FXML
    private Button loginbtn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private PasswordField password;
    // database variables
    private Connection conn;
    private PreparedStatement st;
    private ResultSet rs;

    private double x = 0;
    private double y = 0;
    public void login() throws SQLException {
        String sql = "SELECT * FROM Member WHERE Email = ? AND Password = ?";
        conn = SQLServerConnection.startConnection();

        try {
            st = conn.prepareStatement(sql);
            st.setString(1, email.getText());
            st.setString(2, password.getText());
            rs = st.executeQuery();

            Alert alert;

            if (email.getText().isEmpty() || password.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                if (rs.next()) {
                    String User_Type = rs.getString("User_Type");
                    UserData.username = rs.getString("First_Name") + " " + rs.getString("Last_Name");
                    UserData.id = rs.getInt("ID");
                    UserData.email = rs.getString("Email");
                    UserData.COMPANYID = rs.getInt("Company_ID");

                    // Determine the FXML file based on the user type
                    String fxmlFile = null;
                    if (User_Type.equals("Employer")) {
                        fxmlFile = "COMPANYForm.fxml";
                    } else if (User_Type.equals("employee") || User_Type.equals("JobSeeker")) {
                        fxmlFile = "EmployeeForm.fxml";
                    }

                    // Check if fxmlFile is not null before loading the FXML file
                    if (fxmlFile != null) {
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("info");
                        alert.setHeaderText(null);
                        alert.setContentText("Successfully logged in");
                        alert.showAndWait();

                        loginbtn.getScene().getWindow().hide();
                        Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);
                        root.setOnMousePressed((MouseEvent event) -> {
                            x = event.getSceneX();
                            y = event.getSceneY();
                        });

                        root.setOnMouseDragged((MouseEvent event) -> {
                            stage.setX(event.getScreenX() - x);
                            stage.setY(event.getScreenY() - y);
                        });

                        stage.initStyle(StageStyle.TRANSPARENT);
                        stage.setScene(scene);
                        stage.show();
                    } else {
                        // Handle the case when fxmlFile is null
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Unsupported user type");
                        alert.showAndWait();
                    }
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Email or Password");
                    alert.showAndWait();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void close(){
        System.exit(0);
    }
}