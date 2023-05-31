package com.example.studentinformationsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class EnterMarkController {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    Statement statement;
    ResultSet result;
    FXMLLoader loader;
    Parent root;
    Stage stage;
    Scene scene;
    @FXML
    private TableColumn<StudentInfo, Integer> colCourseID;

    @FXML
    private TableColumn<StudentInfo, String> colCourseName;

    @FXML
    private TableColumn<StudentInfo, String> colFirstName;

    @FXML
    private TableColumn<StudentInfo, String> colLastName;
    @FXML
    private TableColumn<StudentInfo, String> colMarkID;
    @FXML
    private TableColumn<StudentInfo, Double> colMark;

    @FXML
    private TableColumn<StudentInfo, String> colStudentID;

    @FXML
    private TableView<StudentInfo> tableView;
    ObservableList<StudentInfo> studentInfoList = FXCollections.observableArrayList();

    @FXML
    private TextField txtCourseID;

    @FXML
    private TextField txtMark;

    @FXML
    private TextField txtStudentID;
    @FXML
    void handleBack(ActionEvent event) throws IOException {
        loader = new FXMLLoader(getClass().getResource("manage_mark.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void handleOk(ActionEvent event) throws SQLException, SQLIntegrityConstraintViolationException {
//        connection = Database.connectDb();
//        String query = "insert into mark values(?, ?, ?)";
//        String student_id = txtStudentID.getText();
//        int course_id = Integer.parseInt(txtCourseID.getText());
//        double mark = Double.parseDouble(txtMark.getText());
//        try {
//            prepare = connection.prepareStatement(query);
//
//            prepare.setString(1,student_id);
//            prepare.setInt(2,course_id);
//            prepare.setDouble(3,mark);
//            prepare.executeUpdate();
//            connection.close();
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setHeaderText(null);
//            alert.setContentText("Student added successfully!");
//            alert.showAndWait();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        connection = Database.connectDb();

        // Prepare the SQL query with placeholders
        String query = "INSERT INTO mark (student_id, course_id, mark_obtained) VALUES (?, ?, ?)";

        String student_id = txtStudentID.getText();
        String course_id = txtCourseID.getText();
        String markk = txtMark.getText();

        String studentID = null;
        int courseID = 0;
        double mark = 0;

//        try {
        if (!isValidDataType(student_id, "varchar")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Invalid data type entered for studentID(Varchar).");
            alert.showAndWait();
        } else {
            studentID = student_id;
        }
        if (!isValidDataType(course_id, "int")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Invalid data type entered for courseID(int).");
            alert.showAndWait();
        } else {
            courseID = Integer.parseInt(course_id);
        }
        if (!isValidDataType(markk, "double")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Invalid data type entered for mark(double).");
            alert.showAndWait();
        } else {
            mark = Double.parseDouble(markk);
        }

        if (!isStudentExists(connection, studentID)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("studentID you entered does not exist in the database!");
            alert.showAndWait();
        }
        if (!isCourseExists(connection, courseID)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("courseID you entered does not exist in the database!");
            alert.showAndWait();
        }

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from mark where course_id = " + courseID + " and student_id = '" + studentID + "'");
        if (resultSet.next()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("StudentInfo is entered already!");
            alert.showAndWait();
        } else {
            // Create a PreparedStatement object
            preparedStatement = connection.prepareStatement(query);

            // Set the values for the placeholders
            preparedStatement.setString(1, studentID);
            preparedStatement.setInt(2, courseID);
            preparedStatement.setDouble(3, mark);

            // Execute the query
            int insertedRow = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

            Alert alert;
            if (insertedRow > 0) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("mark inserted successfully!");
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Failed to insert mark!.");
            }
            alert.showAndWait();

        }

    }


    // Check if a student with the given student_id exists in the database
    private static boolean isStudentExists(Connection connection, String studentId) throws SQLException {
        String query = "SELECT COUNT(*) FROM student WHERE student_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, studentId);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int count = resultSet.getInt(1);
        resultSet.close();
        preparedStatement.close();
        return count > 0;
    }

    // Check if a course with the given course_id exists in the database
    private static boolean isCourseExists(Connection connection, int courseId) throws SQLException {
        String query = "SELECT COUNT(*) FROM course WHERE course_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, courseId);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int count = resultSet.getInt(1);
        resultSet.close();
        preparedStatement.close();
        return count > 0;
    }

    // Validate the data type of the value
    private static boolean isValidDataType(String value, String dataType) {
        try {
            if (dataType.equals("varchar")) {
                // Validate varchar data type
                return true;
            } else if (dataType.equals("int")) {
                // Validate int data type
                Integer.parseInt(value);
                return true;
            } else if (dataType.equals("double")){
                Double.parseDouble(value);
                return true;
            }else {
                // Invalid data type
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void initialize() {
        getView();
    }
    public void loadTableView(){
        colStudentID.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        colCourseID.setCellValueFactory(new PropertyValueFactory<>("course_id"));
        colCourseName.setCellValueFactory(new PropertyValueFactory<>("course_name"));
        colMarkID.setCellValueFactory(new PropertyValueFactory<>("mark_id"));
        colMark.setCellValueFactory(new PropertyValueFactory<>("mark"));

        tableView.setItems(studentInfoList);

    }
    public void getView(){
        try{

            connection=Database.connectDb();

            statement = connection.createStatement();

            String query = "select * from student_view";

            result=statement.executeQuery(query);
            while (result.next()){
                studentInfoList.add(new StudentInfo(
                        result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getInt(4),
                        result.getString(5),
                        result.getInt(6),
                        result.getDouble(7)));
            }
            loadTableView();
            connection.close();

        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    @FXML
    private void refresh() {
        studentInfoList.clear();
        getView();

    }
}

