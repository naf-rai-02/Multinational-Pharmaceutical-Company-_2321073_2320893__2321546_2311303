package com.example.Shoyeb_2311303.ClinicalTrialCoordinator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class ManageStudyProtocolsController {

    @FXML
    private VBox mainVBox;

    @FXML
    private TextField uidField;

    @FXML
    private TextArea protocolDetailsArea;

    @FXML
    private Button submitButton;

    @FXML
    private TableView<Protocol> protocolsTable;

    @FXML
    private TableColumn<Protocol, String> protocolNameColumn;

    @FXML
    private TableColumn<Protocol, String> statusColumn;

    @FXML
    private Button loginButton;

    @FXML
    private Button attachDocButton;

    // Observable list to hold Protocol data
    private ObservableList<Protocol> protocolList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Initialize TableView columns
        protocolNameColumn.setCellValueFactory(cellData -> cellData.getValue().protocolNameProperty());
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        // Set the items for the TableView
        protocolsTable.setItems(protocolList);

        // Add button actions
        submitButton.setOnAction(e -> handleSubmit());
        loginButton.setOnAction(e -> handleLogin());
        attachDocButton.setOnAction(e -> handleAttachDocument());
    }

    // Event handlers
    private void handleSubmit() {
        String uid = uidField.getText();
        String details = protocolDetailsArea.getText();
        if (!uid.isEmpty() && !details.isEmpty()) {
            protocolList.add(new Protocol(uid, details, "Pending"));
            uidField.clear();
            protocolDetailsArea.clear();
        } else {
            showAlert("Error", "Please fill all fields.");
        }
    }

    private void handleLogin() {
        showAlert("Login", "Login button clicked!");
    }

    private void handleAttachDocument() {
        showAlert("Attach Document", "Attach Document button clicked!");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Protocol model class
    public static class Protocol {
        private final javafx.beans.property.SimpleStringProperty uid;
        private final javafx.beans.property.SimpleStringProperty protocolName;
        private final javafx.beans.property.SimpleStringProperty status;

        public Protocol(String uid, String protocolName, String status) {
            this.uid = new javafx.beans.property.SimpleStringProperty(uid);
            this.protocolName = new javafx.beans.property.SimpleStringProperty(protocolName);
            this.status = new javafx.beans.property.SimpleStringProperty(status);
        }

        public String getUid() { return uid.get(); }
        public String getProtocolName() { return protocolName.get(); }
        public String getStatus() { return status.get(); }

        public javafx.beans.property.SimpleStringProperty protocolNameProperty() { return protocolName; }
        public javafx.beans.property.SimpleStringProperty statusProperty() { return status; }
    }
}
