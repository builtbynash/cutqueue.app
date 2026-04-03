package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class AdminLoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    @FXML
    private void handleLogin() throws Exception {

        if (usernameField.getText().equals("admin") &&
            passwordField.getText().equals("1234")) {

            Stage stage = (Stage) usernameField.getScene().getWindow();

            stage.setScene(new Scene(
               FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"))
            ));

        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid admin login").show();
        }
    }
}