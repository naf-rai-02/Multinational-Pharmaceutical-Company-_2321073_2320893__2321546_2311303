package com.example.Shoyeb_2311303;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class MaintainDistributorRecordsController {

    // FXML components
    @FXML
    private TextField userIdField;

    @FXML
    private ComboBox<String> companyTypeComboBox;

    @FXML
    private TextField contactDetailsField;

    @FXML
    private TextField contractTermsField;

    @FXML
    private TextField certificationsField;

    @FXML
    private TextField performanceField;

    @FXML
    private Button updateBtn;

    @FXML
    private Button archiveBtn;

    @FXML
    private Button approveBtn;

    @FXML
    private TextArea eventLogsArea;

    // Initialize method to set up initial configurations
    @FXML
    public void initialize() {
        // Set ComboBox options
        ObservableList<String> companyTypes = FXCollections.observableArrayList("Supplier", "Distributor");
        companyTypeComboBox.setItems(companyTypes);

        // Set button actions
        updateBtn.setOnAction(event -> handleUpdateDetails());
        archiveBtn.setOnAction(event -> handleArchiveProfile());
        approveBtn.setOnAction(event -> handleApproveProfile());
    }

    // Method to handle updating details
    private void handleUpdateDetails() {
        String userId = userIdField.getText();
        String companyType = companyTypeComboBox.getValue();
        String contact = contactDetailsField.getText();
        String contract = contractTermsField.getText();
        String certifications = certificationsField.getText();
        String performance = performanceField.getText();

        if (userId.isEmpty() || companyType == null || contact.isEmpty()) {
            logEvent("Error: Please fill all mandatory fields (User ID, Company Type, Contact).");
            return;
        }

        // Here, you can add database or data storage logic
        logEvent("Updated details for User ID: " + userId + " (" + companyType + ")");
    }

    // Method to handle archiving profile
    private void handleArchiveProfile() {
        String userId = userIdField.getText();
        if (userId.isEmpty()) {
            logEvent("Error: Enter User ID to archive.");
            return;
        }

        // Archive logic here (e.g., mark as inactive in DB)
        logEvent("Archived profile for User ID: " + userId);
        clearFields();
    }

    // Method to handle approving profile
    private void handleApproveProfile() {
        String userId = userIdField.getText();
        if (userId.isEmpty()) {
            logEvent("Error: Enter User ID to approve.");
            return;
        }

        // Approval logic here (e.g., update status in DB)
        logEvent("Approved profile for User ID: " + userId);
    }

    // Method to log events in the TextArea
    private void logEvent(String message) {
        eventLogsArea.appendText(message + "\n");
    }

    // Method to clear input fields after certain operations
    private void clearFields() {
        userIdField.clear();
        companyTypeComboBox.getSelectionModel().clearSelection();
        contactDetailsField.clear();
        contractTermsField.clear();
        certificationsField.clear();
        performanceField.clear();
    }
}
