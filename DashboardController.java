‎package controller;
‎
‎import javafx.fxml.FXML;
‎import javafx.scene.control.*;
‎import model.DataStore;
‎
‎public class DashboardController {
‎
‎    @FXML private ListView<String> listView;
‎    @FXML private Label selectedLabel;
‎
‎    private int selectedIndex = -1;
‎
‎    @FXML
‎    public void initialize() {
‎
‎        listView.getItems().addAll(DataStore.appointments);
‎
‎        listView.getSelectionModel().selectedItemProperty().addListener((obs, o, n) -> {
‎            selectedIndex = listView.getSelectionModel().getSelectedIndex();
‎            selectedLabel.setText(n);
‎        });
‎    }
‎
‎    @FXML
‎    private void handleEdit() {
‎        if (selectedIndex >= 0) {
‎            String updated = listView.getItems().get(selectedIndex) + " (Edited)";
‎            listView.getItems().set(selectedIndex, updated);
‎            DataStore.appointments.set(selectedIndex, updated);
‎        }
‎    }
‎
‎    @FXML
‎    private void handleCancel() {
‎        if (selectedIndex >= 0) {
‎            listView.getItems().remove(selectedIndex);
‎            DataStore.appointments.remove(selectedIndex);
‎            selectedLabel.setText("Deleted");
‎        }
‎    }
‎}
‎
