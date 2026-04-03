package controller;
‎
‎import javafx.fxml.FXML;
‎import javafx.fxml.FXMLLoader;
‎import javafx.scene.Scene;
‎import javafx.stage.Stage;
‎import javafx.scene.control.*;
‎import javafx.scene.Node;
‎import javafx.scene.Parent;
‎import javafx.scene.layout.VBox;
‎import java.time.LocalDate;
‎import model.DataStore;
‎
‎public class BookingController {
‎
‎    @FXML private VBox rootBox;
‎    @FXML private Label priceLabel;
‎    @FXML private TextField nameField;
‎    @FXML private TextField addressField;
‎    @FXML private TextField contactField;
‎    @FXML private TextField emailField;
‎    @FXML private DatePicker datePicker;
‎    @FXML private ComboBox<String> timeBox;
‎
‎    @FXML
‎    public void initialize() {
‎
‎        timeBox.getItems().addAll(
‎            "09:00 AM","09:30 AM",
‎            "10:00 AM","10:30 AM",
‎            "11:00 AM","11:30 AM",
‎            "01:00 PM","01:30 PM",
‎            "02:00 PM","02:30 PM",
‎            "03:00 PM","03:30 PM",
‎            "04:00 PM","04:30 PM",
‎            "05:00 PM"
‎        );
‎
‎        // Add listener to all CheckBoxes (recursive)
‎        addListenersRecursively(rootBox);
‎    }
‎
‎    private void addListenersRecursively(Node node) {
‎        if (node instanceof CheckBox) {
‎            ((CheckBox) node).setOnAction(e -> computeTotal());
‎        } else if (node instanceof Parent) {
‎            for (Node child : ((Parent) node).getChildrenUnmodifiable()) {
‎                addListenersRecursively(child);
‎            }
‎        }
‎    }
‎
‎    private void computeFromNode(Node node, StringBuilder services, int[] total) {
‎
‎        if (node instanceof CheckBox) {
‎            CheckBox cb = (CheckBox) node;
‎
‎            if (cb.isSelected()) {
‎                String text = cb.getText();
‎                services.append(text).append(" | ");
‎
‎                int price = Integer.parseInt(text.split("₱")[1]);
‎                total[0] += price;
‎            }
‎
‎        } else if (node instanceof Parent) {
‎            for (Node child : ((Parent) node).getChildrenUnmodifiable()) {
‎                computeFromNode(child, services, total);
‎            }
‎        }
‎    }
‎
‎    private String computeTotal() {
‎
‎        StringBuilder services = new StringBuilder();
‎        int[] total = {0};
‎
‎        computeFromNode(rootBox, services, total);
‎
‎        priceLabel.setText("Total: ₱" + total[0]);
‎
‎        return services.toString();
‎    }
‎
‎    @FXML
‎    private void handleBook() throws Exception {
‎
‎        String name = nameField.getText();
‎        String address = addressField.getText();
‎        String contact = contactField.getText();
‎        String email = emailField.getText();
‎        String services = computeTotal();
‎        LocalDate date = datePicker.getValue();
‎        String time = timeBox.getValue();
‎
‎        if (name.isEmpty() || address.isEmpty() || contact.isEmpty() ||
‎            email.isEmpty() || services.isEmpty() || date == null || time == null) {
‎
‎            new Alert(Alert.AlertType.WARNING, "Please fill all fields!").show();
‎            return;
‎        }
‎
‎        String record = name + " | " + address + " | " + contact + " | " + email +
‎                " | " + services + date + " | " + time;
‎
‎        DataStore.appointments.add(record);
‎
‎        Stage stage = (Stage) nameField.getScene().getWindow();
‎        stage.setScene(new Scene(
‎            FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"))
‎        ));
‎    }
‎}
