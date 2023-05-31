package com.example.studentinformationsystem;

        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.TextField;

        import java.sql.Connection;

public class ChangePassword {

    @FXML
    private TextField txtConfirm;
    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    void handleOK(ActionEvent event) {
        Connection connection = Database.connectDb();
        String username = txtUserName.getText();
        String password = txtPassword.getText();
        String query = "update user set user_name = '"+username+"' password = '"+password+"' where ";
    }


}
