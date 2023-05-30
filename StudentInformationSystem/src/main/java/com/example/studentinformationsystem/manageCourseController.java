package com.example.studentinformationsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class manageCourseController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDrop;
    @FXML
    private Button btnSearch;
    @FXML
    private Button btnUpdate;
    @FXML
    private TextField txfDId;

    @FXML
    private Button btnView;

    @FXML
    private TextField txfCC;
    @FXML
    private Button btnBack;
    @FXML
    private TextField txfCH;

    @FXML
    private TextField txfCId;

    @FXML
    private TextField txfCN;
    @FXML
    private TextField txfSearch;

    @FXML
    void handelAddCourse(ActionEvent event) {
        try{
            connect = Database.connectDb();
            prepare = connect.prepareStatement("insert into course values(?,?,?,?,?)");
            prepare.setInt(1,Integer.parseInt(txfCId.getText()));
            prepare.setString(2,txfCN.getText());
            prepare.setString(3,txfCC.getText());
            prepare.setString(4,txfDId.getText());
            prepare.setInt(5,Integer.parseInt(txfCH.getText()));
            prepare.executeUpdate();
            connect.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Course added successfully!");
            alert.showAndWait();

        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    @FXML
    void handleDropCourse(ActionEvent event) {
        try{
            connect = Database.connectDb();
            prepare=connect.prepareStatement("delete from course where course_id='"+txfSearch.getText()+"' ");
            prepare.executeUpdate();
            connect.close();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Course deleted successfully!");
            alert.showAndWait();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    @FXML
    void handleUbdateCourse(ActionEvent event) {
        try{
            connect = Database.connectDb();
            prepare= connect.prepareStatement("update course set  course_id=?,course_name=?,course_code=?,department_id=?, credit_hours=? where course_id='"+txfSearch.getText()+"'" );
            prepare.setInt(1,Integer.parseInt(txfCId.getText()));
            prepare.setString(2,txfCN.getText());
            prepare.setString(3,txfCC.getText());
            prepare.setString(4,txfDId.getText());
            prepare.setInt(5,Integer.parseInt(txfCH.getText()));
            prepare.executeUpdate();
            connect.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Course Updated successfully!");
            alert.showAndWait();

        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    @FXML
    void handleSearchCourse(ActionEvent event) {

        try {
            connect = Database.connectDb();
            statement=connect.createStatement();
            result=statement.executeQuery("select * from course where course_id='"+txfSearch.getText()+"'");
            while (result.next()){
                txfCId.setText(result.getString(1));
                txfCN.setText(result.getString(2));
                txfCC.setText(result.getString(3));
                txfDId.setText(result.getString(4));
                txfCH.setText(result.getString(5));

            }
            connect.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    @FXML
    void handleBackPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("admin_page.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void handleCourseView(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view_course_info.fxml"));
            root = loader.load();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch ( IOException ex) {
            ex.printStackTrace();
        }
    }


}
