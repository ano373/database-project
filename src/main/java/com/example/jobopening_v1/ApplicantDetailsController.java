package com.example.jobopening_v1;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ApplicantDetailsController implements Initializable {

    @FXML
    private Label profile_Age_label;
    @FXML
    private Label profile_City_label;
    @FXML
    private Label profile_Country_label;
    @FXML
    private Label profile_CurrentCompany_label;
    @FXML
    private Label profile_CurrentPos_label;
    @FXML
    private Label profile_Email_label;
    @FXML
    private Label profile_Industry_label;
    @FXML
    private Label profile_PNumber_label;
    @FXML
    private Label profile_Sex_label;
    @FXML
    private Label profile_fn_label;
    @FXML
    private Label profile_ln_label;
    CompanyFormController HE;

    public void setApplicantID(int applicantID) throws SQLException {

        String query = "SELECT\n" +
                "  m.First_Name,\n" +
                "  m.Last_Name,\n" +
                "  m.Email,\n" +
                "  m.Sex,\n" +
                "  m.Country,\n" +
                "  m.City,\n" +
                "  DATEDIFF(YEAR, m.Birthday, GETDATE()) AS Age,\n" +
                "  m.Phone_Number,\n" +
                "  m.Postion AS Current_Position,\n" +
                "  c.Company_Name AS Current_Company,\n" +
                "  c.Industry \n" +
                "FROM\n" +
                "  Member m\n" +
                "  LEFT JOIN Company c ON m.Company_ID = c.Company_ID\n" +
                "WHERE\n" +
                "  m.ID = " + applicantID;

        ResultSet result = SQLServerConnection.DoQuery(query,'S');
        if (result.next()) {
            profile_fn_label.setText(result.getString("First_Name"));
            profile_ln_label.setText(result.getString("Last_Name"));
            profile_Email_label.setText(result.getString("Email"));
            profile_Sex_label.setText(result.getString("Sex"));
            profile_Country_label.setText(result.getString("Country"));
            profile_City_label.setText(result.getString("City"));
            profile_Age_label.setText(result.getString("Age"));
            profile_PNumber_label.setText(result.getString("Phone_Number"));
            profile_CurrentPos_label.setText(result.getString("Current_Position"));
            profile_CurrentCompany_label.setText(result.getString("Current_Company"));
            profile_Industry_label.setText(result.getString("Industry"));
        }


        result.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
