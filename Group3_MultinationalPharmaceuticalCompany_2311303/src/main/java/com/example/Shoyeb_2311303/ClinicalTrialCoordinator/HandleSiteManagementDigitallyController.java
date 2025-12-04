package com.example.Shoyeb_2311303.ClinicalTrialCoordinator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;

public class HandleSiteManagementDigitallyController {

    @FXML
    private TextArea statusArea;

    @FXML
    private TextField uidField;

    @FXML
    private Button enterModuleButton;

    @FXML
    private Button sendRemindersButton;

    @FXML
    private ComboBox<String> siteStatusComboBox;

    @FXML
    private Button viewStatusButton;

    @FXML
    private Button trackVisitsButton;

    @FXML
    private Button updateDocumentsButton;

    @FXML
    private Button monitorTrainingButton;

    @FXML
    public void initialize() {
        // Populate the ComboBox with some example site statuses
        siteStatusComboBox.setItems(FXCollections.observableArrayList(
                "Active",
                "Inactive",
                "Pending",
                "Closed"
        ));

        // Optionally set a default value
        siteStatusComboBox.getSelectionModel().selectFirst();

        // Initialize status area
        statusArea.setText("System ready. Select options to manage site.");
    }

    // Event handlers for each button
    @FXML
    private void handleEnterModule(ActionEvent event) {
        statusArea.appendText("\nEntering module for UID: " + uidField.getText());
    }

    @FXML
    private void handleSendReminders(ActionEvent event) {
        statusArea.appendText("\nSending reminders to site: " + uidField.getText());
    }

    @FXML
    private void handleViewStatus(ActionEvent event) {
        String status = siteStatusComboBox.getValue();
        statusArea.appendText("\nSite status for UID " + uidField.getText() + ": " + status);
    }

    @FXML
    private void handleTrackVisits(ActionEvent event) {
        statusArea.appendText("\nTracking visits for site UID: " + uidField.getText());
    }

    @FXML
    private void handleUpdateDocuments(ActionEvent event) {
        statusArea.appendText("\nUpdating documents for site UID: " + uidField.getText());
    }

    @FXML
    private void handleMonitorTraining(ActionEvent event) {
        statusArea.appendText("\nMonitoring training for site UID: " + uidField.getText());
    }
}
