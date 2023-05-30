package com.example.studentinformationsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.*;

public class LoginController {
    private Stage stage;
    private Parent root;
    private Scene scene;
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;
    @FXML
    private ComboBox cmbLoginType;
    @FXML
    private Button btn_login;

    public void initialize() {
        ObservableList<String> list = FXCollections.observableArrayList("Admin", "Student", "Teacher");
        cmbLoginType.setItems(list);
    }

    @FXML
    void handleLogin(ActionEvent event) {
        if (cmbLoginType.getValue().toString() == "Admin") {
            String sql = "SELECT * FROM admin WHERE first_name = ? and admin_id = ?";
            connect = Database.connectDb();
            try {
                Alert alert;

                prepare = connect.prepareStatement(sql);
                prepare.setString(1, username.getText());
                prepare.setString(2, password.getText());

                result = prepare.executeQuery();
//            CHECK IF FIELDS ARE EMPTY
                if (username.getText().isEmpty() || password.getText().isEmpty()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill all blank fields");
                    alert.showAndWait();
                } else {
                    if (result.next()) {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("admin_page.fxml"));
                        root = loader.load();
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();

                    } else {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Wrong Username/Password");
                        alert.showAndWait();

                    }
                }
                connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }


        } else if (cmbLoginType.getValue().toString() == "Student") {
            String sql = "SELECT * FROM student WHERE first_name = ? and student_id = ?";
            connect = Database.connectDb();
            try {
                Alert alert;

                prepare = connect.prepareStatement(sql);
                prepare.setString(1, username.getText());
                prepare.setString(2, password.getText());

                result = prepare.executeQuery();
//            CHECK IF FIELDS ARE EMPTY
                if (username.getText().isEmpty() || password.getText().isEmpty()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill all blank fields");
                    alert.showAndWait();
                } else {
                    if (result.next()) {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("student_page.fxml"));
                        root = loader.load();
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();

                    } else {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Wrong Username/Password");
                        alert.showAndWait();
                    }
                }
                connect.close();
            }catch(Exception ex){
                ex.printStackTrace();

            }

        }else if (cmbLoginType.getValue().toString() == "Teacher") {
            String sql = "SELECT * FROM teacher WHERE first_name = ? and teacher_id = ?";
            connect = Database.connectDb();
            try {
                Alert alert;

                prepare = connect.prepareStatement(sql);
                prepare.setString(1, username.getText());
                prepare.setString(2, password.getText());

                result = prepare.executeQuery();
//            CHECK IF FIELDS ARE EMPTY
                if (username.getText().isEmpty() || password.getText().isEmpty()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill all blank fields");
                    alert.showAndWait();
                } else {
                    if (result.next()) {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("teacher_page.fxml"));
                        root = loader.load();
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();

                    } else {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Wrong Username/Password");
                        alert.showAndWait();
                    }
                }
                connect.close();
            }catch(Exception ex){
                ex.printStackTrace();

            }

        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("please select the login type");
            alert.showAndWait();
        }

    }
}





