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

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.Date;
//import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;


public class HelloController implements Initializable {

    private Connection connect;


    public Connection getConnect() {
        return connect;
    }

    private PreparedStatement prepare;
    private ResultSet result;
    private Statement stat;


    // Employees Section
    @FXML
    private Button employees_btn;
    @FXML
    private AnchorPane employees_form;

    @FXML
    private TableView<?> emp_table;
    @FXML
    private TableColumn<?, ?> emp_id_col;
    @FXML
    private TableColumn<?, ?> emp_name_col;
    @FXML
    private TableColumn<?, ?> emp_position_col;
    @FXML
    private TableColumn<?, ?> emp_email_col;
    @FXML
    private TableColumn<?, ?> emp_phonenumber_col;
    @FXML
    private TableColumn<?, ?> emp_city_col;
    @FXML
    private TableColumn<?, ?> emp_sex_col;

    @FXML
    private Button emp_update_btn;
    @FXML
    private Button emp_clear_btn;
    @FXML
    private AnchorPane emp_details_panel;

    @FXML
    private TextField emp_id_tf;
    @FXML
    private ComboBox<?> user_type_combobox;

    @FXML
    private Label firstname_label;
    @FXML
    private Label lastname_label;
    @FXML
    private Label email_label;
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
    private ComboBox<?> opening_job_type_combobox;
    @FXML
    private TextField opening_salary_tf;
    @FXML
    private DatePicker opening_date_date;

    // Applications Section
    @FXML
    private Button applications_btn;

    @FXML
    private AnchorPane applications_form;

    @FXML
    private TableView<?> applications_table;

    // side panel  Sections
    @FXML
    private AnchorPane centerpanel;
    @FXML
    private AnchorPane applications_control;
    @FXML
    private AnchorPane emp_form;
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


    private String[] JobTypesList = {"part-time", "full-time"};

    public void JobTypesList() {
        List<String> listJobType = new ArrayList<>();

        for (String data : JobTypesList) {
            listJobType.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listJobType);
        opening_job_type_combobox.setItems(listData);
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
        //      opening_job_type_combobox.getSelectionModel().select(opening_job_type_combobox.getItems().indexOf(jobOpeningD.getJobType()));
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
           // SQLServerConnection.DoQuery(query,Update);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            JobTypesList();
            ShowList_JobOpeningData();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        loadJobOpeningsData();
    }



}