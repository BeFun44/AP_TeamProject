
package com.example.studentinformationsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.stage.Stage;
import java.io.IOException;

public class HomeController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Menu about;


    @FXML
    void handleAbout(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("AboutNotepad");
        alert.setHeaderText("MyNotepad");
        alert.setContentText("My Name is is Ephrem Habtamu I made this notepad app by using javafx. You Can "+
                "Contact Me with email address:\n ephraim0524@gmail.com");
        alert.showAndWait();
    }
    @FXML
    void handleLogin(ActionEvent event) {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
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
