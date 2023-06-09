package com.example.jobopening_v1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
//import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;


public class HelloController implements Initializable {

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    @FXML
    private Button applications_btn;

    @FXML
    private AnchorPane applications_control;

    @FXML
    private AnchorPane applications_form;

    @FXML
    private TableView<?> applications_table;

    @FXML
    private AnchorPane centerpanel;

    @FXML
    private TableColumn<?, ?> company_id_col;

    @FXML
    private Label company_name_label;

    @FXML
    private TableColumn<?, ?> deadline_col;

    @FXML
    private Label email_label;

    @FXML
    private TableColumn<?, ?> emp_city_col;

    @FXML
    private Button emp_clear_btn;

    @FXML
    private AnchorPane emp_details_panel;

    @FXML
    private TableColumn<?, ?> emp_email_col;

    @FXML
    private TableColumn<?, ?> emp_id_col;

    @FXML
    private TextField emp_id_tf;

    @FXML
    private TableColumn<?, ?> emp_name_col;

    @FXML
    private TableColumn<?, ?> emp_phonenumber_col;

    @FXML
    private TableColumn<?, ?> emp_position_col;

    @FXML
    private TableColumn<?, ?> emp_sex_col;

    @FXML
    private TableView<?> emp_table;

    @FXML
    private Button emp_update_btn;

    @FXML
    private Button employees_btn;

    @FXML
    private AnchorPane employees_form;

    @FXML
    private Label firstname_label;

    @FXML
    private Button home_btn;

    @FXML
    private Button interviews_btn;

    @FXML
    private AnchorPane interviews_form;

    @FXML
    private TableColumn<?, ?> job_description_col;

    @FXML
    private TableColumn<?, ?> job_opening_id_col;

    @FXML
    private TableColumn<?, ?> job_title_col;

    @FXML
    private TableColumn<?, ?> job_type_col;

    @FXML
    private Button jobopening_btn;

    @FXML
    private AnchorPane jobopening_form;

    @FXML
    private Label lastname_label;

    @FXML
    private Button opening_add_btn;

    @FXML
    private Button opening_clear_btn;

    @FXML
    private DatePicker opening_date_date;

    @FXML
    private Button opening_delete_btn;

    @FXML
    private TextArea opening_job_description_ta;

    @FXML
    private TextField opening_job_title_tf;

    @FXML
    private ComboBox<?> opening_job_type_combobox;

    @FXML
    private TextField opening_salary_tf;

    @FXML
    private TableView<?> opening_table;

    @FXML
    private Button opening_update_btn;

    @FXML
    private Label phonenumber_label;

    @FXML
    private TextField position_tf;

    @FXML
    private TableColumn<?, ?> salary_col;

    @FXML
    private TextField salary_tf;

    @FXML
    private AnchorPane sidepanel;

    @FXML
    private Button signout_btn;

    @FXML
    private AnchorPane uppanel;
    @FXML
    private AnchorPane home_form;

    @FXML
    private ComboBox<?> user_type_combobox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //   myLabel.setText(myString);
    }

    private double x = 0;
    private double y = 0;


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


}