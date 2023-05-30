
package com.example.studentinformationsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HomeController {
    private Stage stage;
    private Scene scene;
    private Parent root;


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
