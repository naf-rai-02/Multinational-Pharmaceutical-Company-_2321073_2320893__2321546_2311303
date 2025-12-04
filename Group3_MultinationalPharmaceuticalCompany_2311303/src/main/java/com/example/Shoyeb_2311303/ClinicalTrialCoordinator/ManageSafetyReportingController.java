package com.example.Shoyeb_2311303.ClinicalTrialCoordinator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ManageSafetyReportingController {

    @FXML
    private TableView<AdverseEvent> aeTable;

    @FXML
    private TableColumn<AdverseEvent, String> aeIdColumn;
    @FXML
    private TableColumn<AdverseEvent, String> descriptionColumn;
    @FXML
    private TableColumn<AdverseEvent, String> severityColumn;
    @FXML
    private TableColumn<AdverseEvent, String> statusColumn;
    @FXML
    private TableColumn<AdverseEvent, String> siteColumn;

    @FXML
    private TextArea statusArea;
    @FXML
    private TextArea detailsArea;
    @FXML
    private TextField uidField;

    @FXML
    private Button accessModuleButton;
    @FXML
    private Button attachDocButton;
    @FXML
    private Button submitReportButton;

    private ObservableList<AdverseEvent> aeData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Initialize table columns
        aeIdColumn.setCellValueFactory(cellData -> cellData.getValue().aeIdProperty());
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        severityColumn.setCellValueFactory(cellData -> cellData.getValue().severityProperty());
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        siteColumn.setCellValueFactory(cellData -> cellData.getValue().siteProperty());

        // Set table data
        aeTable.setItems(aeData);

        // Example: Add a sample AE record
        aeData.add(new AdverseEvent("AE001", "Headache", "Mild", "Reported", "Site A"));
    }

    @FXML
    private void handleAccessModule() {
        statusArea.appendText("Accessing module for UID: " + uidField.getText() + "\n");
    }

    @FXML
    private void handleAttachDoc() {
        statusArea.appendText("Attaching document for UID: " + uidField.getText() + "\n");
    }

    @FXML
    private void handleSubmitReport() {
        String details = detailsArea.getText();
        if (details.isEmpty()) {
            statusArea.appendText("Cannot submit report: Details are empty.\n");
        } else {
            statusArea.appendText("Report submitted for UID: " + uidField.getText() + "\nDetails: " + details + "\n");
            detailsArea.clear();
        }
    }

    // Inner class for Adverse Event data
    public static class AdverseEvent {
        private final javafx.beans.property.SimpleStringProperty aeId;
        private final javafx.beans.property.SimpleStringProperty description;
        private final javafx.beans.property.SimpleStringProperty severity;
        private final javafx.beans.property.SimpleStringProperty status;
        private final javafx.beans.property.SimpleStringProperty site;

        public AdverseEvent(String aeId, String description, String severity, String status, String site) {
            this.aeId = new javafx.beans.property.SimpleStringProperty(aeId);
            this.description = new javafx.beans.property.SimpleStringProperty(description);
            this.severity = new javafx.beans.property.SimpleStringProperty(severity);
            this.status = new javafx.beans.property.SimpleStringProperty(status);
            this.site = new javafx.beans.property.SimpleStringProperty(site);
        }

        public javafx.beans.property.StringProperty aeIdProperty() { return aeId; }
        public javafx.beans.property.StringProperty descriptionProperty() { return description; }
        public javafx.beans.property.StringProperty severityProperty() { return severity; }
        public javafx.beans.property.StringProperty statusProperty() { return status; }
        public javafx.beans.property.StringProperty siteProperty() { return site; }
    }
}
