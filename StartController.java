package controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

‎public class StartController {
‎
‎    @FXML
‎    private void handleStart() throws Exception {
‎        Stage stage = (Stage) Stage.getWindows().get(0);
‎
‎        stage.setScene(new Scene(
‎            FXMLLoader.load(getClass().getResource("/view/booking.fxml"))
‎        ));
‎    }
‎
‎    @FXML
‎    private void handleAdmin() throws Exception {
‎        Stage stage = (Stage) Stage.getWindows().get(0);
‎
‎        stage.setScene(new Scene(
‎            FXMLLoader.load(getClass().getResource("/view/admin_login.fxml"))
‎        ));
‎    }
‎}

  
  
