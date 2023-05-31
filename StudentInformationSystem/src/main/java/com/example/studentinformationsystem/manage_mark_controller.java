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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Optional;


public class manage_mark_controller {

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet result;
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

//    @FXML
//    void updateMark(ActionEvent event) {
//        // Get the selected mark from the TableView
//        StudentInfo selectedMark = tableView.getSelectionModel().getSelectedItem();
//        if (selectedMark != null) {
//            // Show a dialog or input field to enter the new mark value
//            TextInputDialog dialog = new TextInputDialog(Double.toString(selectedMark.getMark()));
//            dialog.setTitle("Enter StudentInfo");
//            dialog.setHeaderText("Enter the new mark for " + selectedMark.getFirst_name() + " " + selectedMark.getLast_name());
//            dialog.setContentText("StudentInfo:");
//
//            Optional<String> result = dialog.showAndWait();
//            result.ifPresent(markValue -> {
//                // Validate and parse the entered mark value
//                try {
//                    double mark = Double.parseDouble(markValue);
//                    // Update the mark value for the selected mark
//                    selectedMark.setMark(mark);
//                    // Update the mark in the database (you need to implement the database update logic)
//
//                    // Refresh the TableView to reflect the updated mark value
//                    tableView.refresh();
//                } catch (NumberFormatException e) {
//                    // Handle invalid mark value
//                    Alert alert = new Alert(Alert.AlertType.ERROR);
//                    alert.setTitle("Invalid StudentInfo");
//                    alert.setHeaderText("Invalid mark value");
//                    alert.setContentText("Please enter a valid numeric mark value.");
//                    alert.showAndWait();
//                }
//            });
//        } else {
//            // No mark selected, show an error message
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("No StudentInfo Selected");
//            alert.setHeaderText("No mark selected");
//            alert.setContentText("Please select a mark from the table.");
//            alert.showAndWait();
//

    @FXML
    void updateMark(ActionEvent event) {
        // Get the selected mark from the TableView
        StudentInfo selectedStudentInfo = tableView.getSelectionModel().getSelectedItem();
        if (selectedStudentInfo != null) {
            // Show a TextInputDialog to enter the new mark value
            TextInputDialog dialog = new TextInputDialog(Double.toString(selectedStudentInfo.getMark()));
            dialog.setTitle("Enter StudentInfo");
            dialog.setHeaderText("Enter the new mark for " + selectedStudentInfo.getFirst_name() + " " + selectedStudentInfo.getLast_name());
            dialog.setContentText("StudentInfo:");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(markValue -> {
                // Validate and parse the entered mark value
                try {
                    double mark = Double.parseDouble(markValue);
                    // Update the mark value for the selected mark
                    selectedStudentInfo.setMark(mark);

                    // Perform the database update
                    try {
                        connection = Database.connectDb();

                        String query = "UPDATE mark SET mark_obtained = ? WHERE mark_id = ?";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setDouble(1, mark);
                        preparedStatement.setInt(2, selectedStudentInfo.getMark_id());
                        preparedStatement.executeUpdate();

                        connection.close();

                        // Show a success message
                        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                        successAlert.setTitle("StudentInfo Updated");
                        successAlert.setHeaderText("StudentInfo successfully updated");
                        successAlert.setContentText("The mark for " + selectedStudentInfo.getFirst_name() + " " + selectedStudentInfo.getLast_name() + " has been updated.");
                        successAlert.showAndWait();

                        // Refresh the TableView to reflect the updated mark value
                        tableView.refresh();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        // Show an error message
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setTitle("Database Error");
                        errorAlert.setHeaderText("Failed to update mark");
                        errorAlert.setContentText("An error occurred while updating the mark in the database. Please try again.");
                        errorAlert.showAndWait();
                    }
                } catch (NumberFormatException e) {
                    // Handle invalid mark value
                    Alert invalidAlert = new Alert(Alert.AlertType.WARNING);
                    invalidAlert.setTitle("Invalid StudentInfo");
                    invalidAlert.setHeaderText("Invalid mark value");
                    invalidAlert.setContentText("Please enter a valid numeric mark value.");
                    invalidAlert.showAndWait();
                }
            });
        } else {
            // No mark selected, show an error message
            Alert selectionAlert = new Alert(Alert.AlertType.WARNING);
            selectionAlert.setTitle("No StudentInfo Selected");
            selectionAlert.setHeaderText("No mark selected");
            selectionAlert.setContentText("Please select a mark from the table.");
            selectionAlert.showAndWait();
        }
    }



    @FXML
    void handleBack(ActionEvent event) throws IOException {
        loader = new FXMLLoader(getClass().getResource("teacher_page.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void handleEnterMark(ActionEvent event) throws IOException {
        loader = new FXMLLoader(getClass().getResource("enter_mark.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void deleteMark(ActionEvent event) {
        StudentInfo selectedStudentInfo = tableView.getSelectionModel().getSelectedItem();
        if (selectedStudentInfo != null) {
            // Show a confirmation dialog before deleting the mark
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Delete StudentInfo");
            confirmationAlert.setHeaderText("Confirm Deletion");
            confirmationAlert.setContentText("Are you sure you want to delete the mark for " + selectedStudentInfo.getFirst_name() + " " + selectedStudentInfo.getLast_name() + "?");

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Perform the mark deletion from the TableView and database
                try {
                    connection = Database.connectDb();

                    String query = "DELETE FROM mark WHERE mark_id = ?";
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, selectedStudentInfo.getMark_id());
                    preparedStatement.executeUpdate();

                    connection.close();

                    // Show a success message
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("StudentInfo Deleted");
                    successAlert.setHeaderText("StudentInfo successfully deleted");
                    successAlert.setContentText("The mark for " + selectedStudentInfo.getFirst_name() + " " + selectedStudentInfo.getLast_name() + " has been deleted.");
                    successAlert.showAndWait();

                    // Remove the deleted mark from the TableView
                    studentInfoList.remove(selectedStudentInfo);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    // Show an error message
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Database Error");
                    errorAlert.setHeaderText("Failed to delete mark");
                    errorAlert.setContentText("An error occurred while deleting the mark from the database. Please try again.");
                    errorAlert.showAndWait();
                }
            }
        } else {
            // No mark selected, show an error message
            Alert selectionAlert = new Alert(Alert.AlertType.WARNING);
            selectionAlert.setTitle("No StudentInfo Selected");
            selectionAlert.setHeaderText("No mark selected");
            selectionAlert.setContentText("Please select a mark from the table.");
            selectionAlert.showAndWait();
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
//            String view = "CREATE VIEW student_view AS SELECT s.student_id, s.first_name, s.last_name, c.course_id, c.course_name, mark_id, m.mark_obtained" +
//            "FROM student s" +
//            "JOIN enrollment e ON s.student_id = e.student_id"+
//            "JOIN course c ON e.course_id = c.course_id"+
//            "JOIN mark m ON e.enrollment_id = m.enrollment_id";

            String query = "select * from student_view";

//            String query = "SELECT s.student_id, s.first_name, s.last_name, c.course_id, c.course_name, m.mark_id, m.mark_obtained " +
//                    "FROM student s " +
//                    "JOIN enrollment e ON s.student_id = e.student_id " +
//                    "JOIN course c ON e.course_id = c.course_id " +
//                    "JOIN mark m ON e.student_id = m.student_id AND e.course_id = m.course_id";

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
}

