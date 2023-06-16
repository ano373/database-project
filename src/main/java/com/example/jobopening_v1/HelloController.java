package com.example.jobopening_v1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.Date;
//import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;
import javafx.util.Pair;


public class HelloController implements Initializable {

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement stat;
    private String loginEmail;
    private String loginPassword;


    private void login_Data(){
      //  loginEmail ;

    }

//    private int login_company_id(){
//
//
//    }

    // Employees Section
    @FXML
    private Button employees_btn;
    @FXML
    private AnchorPane employees_form;

    @FXML
    private TableView<memberDATA> emp_table;
    @FXML
    private TableColumn<memberDATA, Integer> emp_id_col;
    @FXML
    private TableColumn<memberDATA, String> emp_name_col;
    @FXML
    private TableColumn<memberDATA, String> emp_position_col;
    @FXML
    private TableColumn<memberDATA, String> emp_email_col;
    @FXML
    private TableColumn<memberDATA, Integer> emp_phonenumber_col;
    @FXML
    private TableColumn<memberDATA, String> emp_city_col;
    @FXML
    private TableColumn<memberDATA, String> emp_sex_col;

    @FXML
    private Button emp_update_btn;
    @FXML
    private Button emp_clear_btn;
    @FXML
    private AnchorPane emp_details_panel;
    @FXML
    private ComboBox<String> user_type_combobox;
    @FXML
    private  TextField position_tf;
    @FXML
    private  TextField salary_tf;
    @FXML
    private Label firstname_label;
    @FXML
    private Label lastname_label;
    @FXML
    private Label email_label;
    @FXML
    private Label empid_label;
    @FXML
    private Label phonenumber_label;



    // Job Openings Section
    @FXML
    private Button jobopening_btn;

    @FXML
    private AnchorPane jobopening_form;

    @FXML
    private TableView<JobOpeningsDATA> opening_table;

    @FXML
    private TableColumn<JobOpeningsDATA, Integer> job_opening_id_col;
    @FXML
    private TableColumn<JobOpeningsDATA, String> job_title_col;
    @FXML
    private TableColumn<JobOpeningsDATA, String> job_description_col;
    @FXML
    private TableColumn<JobOpeningsDATA, String> job_type_col;
    @FXML
    private TableColumn<JobOpeningsDATA, Integer> company_id_col;
    @FXML
    private TableColumn<JobOpeningsDATA, Date> deadline_col;
    @FXML
    private TableColumn<JobOpeningsDATA, Integer> salary_col;

    @FXML
    private Button opening_add_btn;
    @FXML
    private Button opening_update_btn;
    @FXML
    private Button opening_delete_btn;
    @FXML
    private Button opening_clear_btn;

    @FXML
    private TextField opening_job_title_tf;

    @FXML
    private TextArea opening_job_description_ta;
    @FXML
    private ComboBox<String> opening_job_type_combobox;
    @FXML
    private TextField opening_salary_tf;
    @FXML
    private DatePicker opening_date_date;

    // Applications Section
    @FXML
    private Button app_Update_Btn;
    @FXML
    private Button app_ViewProfile_Btn;
    @FXML
    private Button app_ViewResume_Btn;
    @FXML
    private ComboBox<String> app_Status_combobox;
    @FXML
    private TableView<ApplicationsViewDATA> applications_table;
    @FXML
    private TableColumn<ApplicationsViewDATA, String> App_Status_col;
    @FXML
    private TableColumn<ApplicationsViewDATA, String> app_City_col;
    @FXML
    private TableColumn<ApplicationsViewDATA, String> app_CurrentCompany_col;
    @FXML
    private TableColumn<ApplicationsViewDATA, String> app_CurrentPostion_Col;
    @FXML
    private TableColumn<ApplicationsViewDATA, String> app_Email_col;
    @FXML
    private TableColumn<ApplicationsViewDATA, Integer> app_ID_col;
    @FXML
    private TableColumn<ApplicationsViewDATA, String> app_JobTitle_col;
    @FXML
    private TableColumn<ApplicationsViewDATA, String> app_Name_col;
    @FXML
    private TableColumn<ApplicationsViewDATA, Integer> app_PhoneNumber_col;


    // side panel  Sections
    @FXML
    private AnchorPane centerpanel;
    @FXML
    private AnchorPane applications_control;
    @FXML
    private AnchorPane emp_form;
    @FXML
    private Button applications_btn;
    @FXML
    private AnchorPane applications_form;
    @FXML
    private AnchorPane interviews_form;
    @FXML
    private AnchorPane home_form;
    @FXML
    private AnchorPane sidepanel;
    @FXML
    private AnchorPane uppanel;
    @FXML
    private Button home_btn;
    @FXML
    private Button interviews_btn;
    @FXML
    private Button signout_btn;



    JobOpeningsDATA JobOpeningsList;

    private double x = 0;
    private double y = 0;


    final char Update = 'U';
    final char Delete = 'D';
    final char Select = 'S';
    final char Insert = 'I';

    private String[] JobTypesList = {"part-time", "full-time"};
    private String[] UserTypeList = {"Employee", "Employer"};
    private  String[] ApplicationStatusList = {"Rejected", "Pending","Selected"};

    public void close() {
        System.exit(0);
    }
    public void switchForm(ActionEvent event) {
        Object source = event.getSource();

        // Define the source-target mappings
        Map<Object, List<Node>> mappings = new HashMap<>();
        mappings.put(home_btn, Arrays.asList(home_form));
        mappings.put(employees_btn, Arrays.asList(employees_form));
        mappings.put(jobopening_btn, Arrays.asList(jobopening_form));
        mappings.put(applications_btn, Arrays.asList(applications_form));
        mappings.put(interviews_btn, Arrays.asList(interviews_form));

        // Iterate over the mappings to set visibility
        for (Map.Entry<Object, List<Node>> entry : mappings.entrySet()) {
            Object src = entry.getKey();
            List<Node> targets = entry.getValue();

            boolean isVisible = src == source;
            for (Node target : targets) {
                target.setVisible(isVisible);
            }


        }
    }
    public void logout() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");
        Optional<ButtonType> option = alert.showAndWait();
        try {
            if (option.get().equals(ButtonType.OK)) {

                signout_btn.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);


                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased((MouseEvent event) -> {
                    stage.setOpacity(1);
                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @FXML
    private void populateComboBox(ComboBox<String> comboBox, String[] data) {
        List<String> listData = new ArrayList<>();

        for (String item : data) {
            listData.add(item);
        }

        ObservableList<String> observableList = FXCollections.observableArrayList(listData);
        comboBox.setItems(observableList);
    }



    // employee form functions
    public ObservableList<memberDATA> list_EmployeeData() throws SQLException {
        ObservableList<memberDATA> listData = FXCollections.observableArrayList();
        String query = "SELECT ID,First_Name, Last_Name, Email, Sex, City, Phone_Number, Postion FROM Member";
        connect = SQLServerConnection.startConnection();
        try {
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();
            memberDATA memberD;
            while (result.next()) {
                memberD = new memberDATA(
                        result.getInt("ID"),
                        result.getString("First_Name"),
                        result.getString("Last_Name"),
                        result.getString("Email"),
                        result.getString("Sex"),
                        result.getString("City"),
                        result.getInt("Phone_Number"),
                        result.getString("Postion")
                );
                listData.add(memberD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public ObservableList<JobOpeningsDATA> list_JobOpeningData() throws SQLException {
        ObservableList<JobOpeningsDATA> listData = FXCollections.observableArrayList();
        String query = "SELECT * FROM JobOpening";
        connect = SQLServerConnection.startConnection();
        try {
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();
            JobOpeningsDATA JobOpeningD;
            while (result.next()) {
                JobOpeningD = new JobOpeningsDATA(
                        result.getInt("Job_Opening_ID"),
                        result.getInt("Company_ID"),
                        result.getString("Job_Tilte"),
                        result.getString("Job_Description"),
                        result.getString("Job_Type"),
                        result.getInt("Salary"),
                        result.getDate("Deadline")
                );
                listData.add(JobOpeningD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }
    private ObservableList<JobOpeningsDATA> addJobOpeningsList;
    public void ShowList_JobOpeningData() throws SQLException {
        addJobOpeningsList = list_JobOpeningData();
        loadJobOpeningsData();
        opening_table.setItems(addJobOpeningsList);

    }
    private ObservableList<memberDATA> addEmployeeList;
    public void ShowList_EmployeeData() throws SQLException {
        addEmployeeList = list_EmployeeData();
        loadEmployeeData();
        emp_table.setItems(addEmployeeList);

    }




    //application form functions
    public void loadApplicationsData() {
        Map<TableColumn<ApplicationsViewDATA, ?>, String> columnMappings = new HashMap<>();
        columnMappings.put(app_ID_col, "memberId");
        columnMappings.put(app_JobTitle_col, "jobTitle");
        columnMappings.put(app_Name_col, "name");
        columnMappings.put(app_Email_col, "email");
        columnMappings.put(app_City_col, "city");
        columnMappings.put(app_PhoneNumber_col, "phoneNumber");
        columnMappings.put(app_CurrentPostion_Col, "currentPosition");
        columnMappings.put(app_CurrentCompany_col, "currentCompany");
        columnMappings.put(App_Status_col, "applicationStatus");

        for (TableColumn<ApplicationsViewDATA, ?> column : columnMappings.keySet()) {
            column.setCellValueFactory(new PropertyValueFactory<>(columnMappings.get(column)));
        }
    }
    public ObservableList<ApplicationsViewDATA> list_ApplicationsViewData() throws SQLException {
        ObservableList<ApplicationsViewDATA> listData = FXCollections.observableArrayList();
        String query = "SELECT\n" +
                "    M.ID AS MemberID,\n" +
                "    J.Job_Tilte AS Job_Title,\n" +
                "    CONCAT(M.First_Name, ' ', M.Last_Name) AS Name,\n" +
                "    M.Email,\n" +
                "    M.City,\n" +
                "    M.Phone_Number,\n" +
                "    M.Postion AS Current_Position,\n" +
                "    C.Company_Name AS Current_Company,\n" +
                "    A.Application_Status\n" +
                "FROM\n" +
                "    Member M\n" +
                "     JOIN Application A ON M.ID = A.ID\n" +
                "     JOIN JobOpening J ON A.Job_Opening_ID = J.Job_Opening_ID\n" +
                "    LEFT JOIN Company C ON M.Company_ID = C.Company_ID;\n" +
                "   ";
        connect = SQLServerConnection.startConnection();
        try {
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();
            ApplicationsViewDATA ApplicationsViewD;
            while (result.next()) {
                ApplicationsViewD = new ApplicationsViewDATA(
                        result.getInt("MemberID"),
                        result.getString("Job_Title"),
                        result.getString("Name"),
                        result.getString("Email"),
                        result.getString("City"),
                        result.getString("Phone_Number"),
                        result.getString("Current_Position"),
                        result.getString("Current_Company"),
                        result.getString("Application_Status")
                );
                listData.add(ApplicationsViewD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }
    private ObservableList<ApplicationsViewDATA> addlist_ApplicationsViewList;
    public void ShowList_ApplicationsViewData() throws SQLException {
        addlist_ApplicationsViewList = list_ApplicationsViewData();
        loadApplicationsData();
        applications_table.setItems(addlist_ApplicationsViewList);

    }
    @FXML
    public  Pair<Integer, Integer> ApplictionsViewSelect() throws SQLException {

        ApplicationsViewDATA ApplicationsViewD = applications_table.getSelectionModel().getSelectedItem();
        int num = applications_table.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return new Pair<>(num, num);
        }
    //    app_Status_combobox.setValue(ApplicationsViewD.getApplicationStatus());
        TableColumn<ApplicationsViewDATA, Integer> firstColumn = (TableColumn<ApplicationsViewDATA, Integer>) applications_table.getColumns().get(0);
        TableColumn<ApplicationsViewDATA, Integer> secondColumn = (TableColumn<ApplicationsViewDATA, Integer>) applications_table.getColumns().get(1);

        int MemberID = firstColumn.getCellData(num);
        String Jobtitle = String.valueOf(secondColumn.getCellData(num));
        String query = "SELECT Job_Opening_ID FROM JobOpening WHERE Job_Tilte = '" + Jobtitle + "'";
        ResultSet result = SQLServerConnection.DoQuery(query, Select);
        int jobOpeningID = 0;
        if (result.next()) {
            jobOpeningID = result.getInt("Job_Opening_ID");
        }
        System.out.println("Member ID: " + MemberID);
        System.out.println("Job Opening ID: " + jobOpeningID);
        return new Pair<>(MemberID, jobOpeningID);
    }

    private void updateApplicationStatus() {
        Pair<Integer, Integer> resultPair = null;
        try {
            resultPair = ApplictionsViewSelect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int memberID = resultPair.getKey();
        int jobOpeningID = resultPair.getValue();

        String query = "UPDATE Application SET Application_Status = ? WHERE ID = ? AND Job_Opening_ID = ?";

        try {
            connect = SQLServerConnection.startConnection();
            PreparedStatement statement = connect.prepareStatement(query);
            System.out.println(app_Status_combobox.getSelectionModel().getSelectedItem());
            statement.setString(1,app_Status_combobox.getSelectionModel().getSelectedItem());

            statement.setInt(2, memberID);
            statement.setInt(3, jobOpeningID);
            statement.executeUpdate();
            statement.close();
            connect.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void openApplicantDetails(ActionEvent event) {
        // Get the selected applicant ID from the application table
        ApplicationsViewDATA selectedApplicant = applications_table.getSelectionModel().getSelectedItem();

        if(selectedApplicant!=null) {
            int applicantID = selectedApplicant.getMemberId();
        //    System.out.println(applicantID);

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("ApplicantDetails.fxml"));
                Parent root = loader.load();


                ApplicantDetailsController applicantDetailsController = loader.getController();


                try {
                    applicantDetailsController.setApplicantID(applicantID);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                Stage detailsStage = new Stage();
                detailsStage.setScene(new Scene(root));
                detailsStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
            showAlert(Alert.AlertType.ERROR, "Error Message", null, "No Applicant is selected");




    }



    public void updateSelectedApplication() throws SQLException {
            connect = SQLServerConnection.startConnection();

        ApplicationsViewDATA selectedItem = applications_table.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            showAlert(Alert.AlertType.INFORMATION, "Information Message", null, "Successfully Updated!");
            updateApplicationStatus();
            ShowList_ApplicationsViewData();
            app_Status_combobox.setValue("");

        } else
            showAlert(Alert.AlertType.ERROR, "Error Message", null, "No item selected");

    }









    //..............................................................................
    //employee form functions
    public void loadEmployeeData() {
        Map<TableColumn<memberDATA, ?>, String> columnMappings = new HashMap<>();
        columnMappings.put(emp_id_col, "id");
        columnMappings.put(emp_name_col, "FullName");
        columnMappings.put(emp_email_col, "email");
        columnMappings.put(emp_sex_col, "sex");
        columnMappings.put(emp_city_col, "city");
        columnMappings.put(emp_phonenumber_col, "phoneNumber");
        columnMappings.put(emp_position_col, "position");

        for (TableColumn<memberDATA, ?> column : columnMappings.keySet()) {
            column.setCellValueFactory(new PropertyValueFactory<>(columnMappings.get(column)));
        }
    }

    private int SelectedEmployeeID(){
        int num = emp_table.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return num;
        }

        memberDATA memberD = emp_table.getSelectionModel().getSelectedItem();
        TableColumn<memberDATA, Integer> firstColumn = (TableColumn<memberDATA, Integer>) emp_table.getColumns().get(0);
        int ID = firstColumn.getCellData(num);

        return ID;
    }

    @FXML
    public int SelectEmployee() throws SQLException {
        memberDATA memberD = emp_table.getSelectionModel().getSelectedItem();
        int num = emp_table.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return num;
        }

        empid_label.setText(String.valueOf(memberD.getId()));
        firstname_label.setText(memberD.getFirstName());
        lastname_label.setText(memberD.getLastName());
        email_label.setText(memberD.getEmail());
        phonenumber_label.setText(String.valueOf(memberD.getPhoneNumber()));
        position_tf.setText(memberD.getPosition());

        String query = "SELECT Salary,User_Type FROM Member WHERE ID = " + SelectedEmployeeID();
        ResultSet resultSet = SQLServerConnection.DoQuery(query, Select);

        String salary = null;
        String Usertype = null;
        while (resultSet.next()) {
            salary = String.valueOf(resultSet.getInt("Salary"));
            Usertype =  resultSet.getString("User_Type");
        }

        user_type_combobox.setValue(Usertype);
        salary_tf.setText(salary);
        resultSet.close();
        return num;

    }


    public void loadJobOpeningsData() {
        Map<TableColumn<JobOpeningsDATA, ?>, String> columnMappings = new HashMap<>();
        columnMappings.put(job_opening_id_col, "jobOpeningID");
        columnMappings.put(company_id_col, "companyID");
        columnMappings.put(job_title_col, "jobTitle");
        columnMappings.put(job_description_col, "jobDescription");
        columnMappings.put(job_type_col, "jobType");
        columnMappings.put(salary_col, "salary");
        columnMappings.put(deadline_col, "deadline");

        for (TableColumn<JobOpeningsDATA, ?> column : columnMappings.keySet()) {
            column.setCellValueFactory(new PropertyValueFactory<>(columnMappings.get(column)));
        }
    }
    public int JobOpeningSelect() {

        JobOpeningsDATA jobOpeningD = opening_table.getSelectionModel().getSelectedItem();
        int num = opening_table.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return num;
        }

        opening_job_title_tf.setText(jobOpeningD.getJobTitle());
        opening_salary_tf.setText(String.valueOf(jobOpeningD.getSalary()));
        opening_job_description_ta.setText(jobOpeningD.getJobDescription());
        opening_date_date.setValue(jobOpeningD.getDeadline().toLocalDate());
        String jobType = jobOpeningD.getJobType();

        opening_job_type_combobox.setValue(jobOpeningD.getJobType());
        TableColumn<JobOpeningsDATA, Integer> firstColumn = (TableColumn<JobOpeningsDATA, Integer>) opening_table.getColumns().get(0);
        int JobOpeningID = firstColumn.getCellData(num);

        return JobOpeningID;

    }




    private void showAlert(Alert.AlertType type, String title, String headerText, String contentText) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    private boolean AlertCheckFields() {
            if (opening_job_title_tf.getText().isEmpty()
                    || opening_date_date.getValue() == null
                    || opening_job_description_ta.getText().isEmpty()
                    || opening_salary_tf.getText().isEmpty()
                    || opening_job_type_combobox.getSelectionModel().isEmpty())
            {
                return false;
            }
             return true;

    }

    private boolean AlertCheckFieldsEmployee() {
        if (salary_tf.getText().isEmpty()
                || position_tf.getText().isEmpty()
                || user_type_combobox.getSelectionModel().isEmpty())
        {
            return false;
        }
        return true;

    }
    private void updateEmployee() {
        String query = "UPDATE Member SET User_Type = ?, Postion = ?,Salary = ? WHERE ID = ?";

        try {
            Connection connection = SQLServerConnection.startConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,  (String)user_type_combobox.getSelectionModel().getSelectedItem());
            statement.setString(2, position_tf.getText());
            statement.setString(3, salary_tf.getText());
            statement.setInt(4,SelectedEmployeeID());
            statement.executeUpdate();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void insertJobOpening(int companyId, String jobTitle, String jobDescription, String jobType, String salary, LocalDate deadline) {
        String query = "INSERT INTO JobOpening (Company_ID, Job_Tilte, Job_Description, Job_Type, Salary, Deadline) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connect.prepareStatement(query)) {
            statement.setInt(1, companyId);
            statement.setString(2, jobTitle);
            statement.setString(3, jobDescription);
            statement.setString(4, jobType);
            statement.setString(5, salary);
            statement.setDate(6, java.sql.Date.valueOf(deadline));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void deleteJobOpening() {
        int JobOpeningID = JobOpeningSelect();
        String query = "DELETE FROM JobOpening Where Job_Opening_ID = " + JobOpeningID;
        try {
            SQLServerConnection.DoQuery(query,'D');
            ShowList_JobOpeningData();
            showAlert(Alert.AlertType.INFORMATION, "Information Message", null, "Job Opening Have Been Deleted!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    private void updateJobOpening() {
        String query = "UPDATE JobOpening SET Job_Tilte = ?, Job_Description = ?, Job_Type = ?, Salary = ?, Deadline = ? WHERE Job_Opening_ID = ?";

        try {
            Connection connection = SQLServerConnection.startConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,  opening_job_title_tf.getText());
            statement.setString(2, opening_job_description_ta.getText());
            statement.setString(3, (String)opening_job_type_combobox.getSelectionModel().getSelectedItem());
            statement.setInt(4, Integer.parseInt(opening_salary_tf.getText()));
            statement.setDate(5, java.sql.Date.valueOf(opening_date_date.getValue()));
            statement.setInt(6, JobOpeningSelect());
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateSelectedEmployee() {
        try {
            connect = SQLServerConnection.startConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        memberDATA selectedItem = emp_table.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            try {
                if (AlertCheckFieldsEmployee()){
                    showAlert(Alert.AlertType.INFORMATION, "Information Message", null, "Successfully Updated!");
                    updateEmployee();
                    ShowList_EmployeeData();
                    ResetFields_Employee();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error Message", null, "You must fill in all fields");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Error Message", null, "No item selected");
        }
    }
    public void uploadImageToDatabase(String imagePath, int applicationID) {
        try {
            // Read the image file into bytes
            File imageFile = new File(imagePath);
            byte[] imageData = Files.readAllBytes(imageFile.toPath());

            // Prepare the SQL query with parameters for the image data and application ID
            String query = "UPDATE Application SET Resume = ? WHERE ID = ?";

            // Create a connection to the database
            Connection connection = SQLServerConnection.startConnection();

            // Create a prepared statement with the query
            PreparedStatement statement = connection.prepareStatement(query);

            // Set the parameter for the image data
            statement.setBytes(1, imageData);

            // Set the parameter for the application ID
            statement.setInt(2, applicationID);

            // Execute the query to update the image
            statement.executeUpdate();

            // Close the statement and connection
            statement.close();
            connection.close();

            System.out.println("Image uploaded successfully.");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
    public void ADD_JobOpening() {
        try {
            connect = SQLServerConnection.startConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (AlertCheckFields()) {
                insertJobOpening(1, opening_job_title_tf.getText(), opening_job_description_ta.getText(),
                        (String)opening_job_type_combobox.getSelectionModel().getSelectedItem(),
                        opening_salary_tf.getText(), opening_date_date.getValue());

                showAlert(Alert.AlertType.INFORMATION, "Information Message", null, "Successfully Added!");

                ShowList_JobOpeningData();
                ResetFields_JobOpeing();
            } else {
                showAlert(Alert.AlertType.ERROR, "Error Message", null, "You must fill in all fields");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateSelectedJobOpening() {
        try {
            connect = SQLServerConnection.startConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JobOpeningsDATA selectedItem = opening_table.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            try {
                if (AlertCheckFields()) {
                    showAlert(Alert.AlertType.INFORMATION, "Information Message", null, "Successfully Updated!");
                    updateJobOpening();
                    ShowList_JobOpeningData();
                    ResetFields_JobOpeing();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error Message", null, "You must fill in all fields");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Error Message", null, "No item selected");
        }
    }
    public void ResetFields_JobOpeing() {
        opening_job_title_tf.setText("");
        opening_salary_tf.setText("");
        opening_job_description_ta.setText("");
        opening_date_date.setValue(null);
        opening_job_type_combobox.getSelectionModel().clearSelection();


    }
    public void ResetFields_Employee() {
        String orignalvalue = "select employee";
        String Orignalvalue2 = "empty";
        empid_label.setText(orignalvalue);
        firstname_label.setText(orignalvalue);
        lastname_label.setText(orignalvalue);
        email_label.setText(Orignalvalue2);
        phonenumber_label.setText(Orignalvalue2);
        position_tf.setText("");
        salary_tf.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            populateComboBox(opening_job_type_combobox, JobTypesList);
            populateComboBox(user_type_combobox, UserTypeList);
            populateComboBox(app_Status_combobox,ApplicationStatusList);
            ShowList_JobOpeningData();
            ShowList_EmployeeData();
            ShowList_ApplicationsViewData();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        loadJobOpeningsData();
    }



}