package com.example.studentinformationsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteMarkController {

    @FXML
    private TextField txtMark;

    @FXML
    void handleBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("manage_mark.fxml"));
        Parent root= loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void handleOk(ActionEvent event) {
        try {
            Connection connection;
            PreparedStatement preparedStatement;
            String query = "DELETE FROM mark WHERE mark_id = ?";

            int markId = Integer.parseInt(txtMark.getText());

            connection = Database.connectDb();
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, markId);

            // Execute the query
            int rowsDeleted = preparedStatement.executeUpdate();

            Alert alert;
            if (rowsDeleted > 0) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Delete StudentInfo");
                alert.setHeaderText(null);
                alert.setContentText("StudentInfo deleted successfully.");
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Delete StudentInfo");
                alert.setHeaderText(null);
                alert.setContentText("Failed to delete mark.");
            }
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}




