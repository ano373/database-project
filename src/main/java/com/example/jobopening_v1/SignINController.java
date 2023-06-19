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
        String sql = "SELECT * FROM Member WHERE (User_Type = 'employer') AND Email = ? AND Password = ?";

        conn=SQLServerConnection.startConnection();
        try{

            st=conn.prepareStatement(sql);
            st.setString(1,email.getText());
            st.setString(2,password.getText());
            rs=st.executeQuery();

            Alert alert;

            if(email.getText().isEmpty() || password.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }
            else{
                if(rs.next()){
                    UserData.username=rs.getString(4)+ " "+rs.getString(5);
                    UserData.id=rs.getInt(1);
                    UserData.email=rs.getString(6);
                    UserData.COMPANYID = rs.getInt(2);

                    //System.out.println(UserData.username);
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("info");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully loged in");
                    alert.showAndWait();

                    loginbtn.getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("COMPANYForm.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    root.setOnMousePressed((MouseEvent event) ->{
                        x = event.getSceneX();
                        y = event.getSceneY();
                    });

                    root.setOnMouseDragged((MouseEvent event) ->{
                        stage.setX(event.getScreenX() - x);
                        stage.setY(event.getScreenY() - y);
                    });

                    stage.initStyle(StageStyle.TRANSPARENT);


                    stage.setScene(scene);
                    stage.show();
                }
                else{
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("wrong Email or passowrd");
                    alert.showAndWait();
                }
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void close(){
        System.exit(0);
    }
}