package com.example.Shoyeb_2311303.ClinicalTrialCoordinator;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ManageComplianceRegulatoryDocumentationController {

    // Buttons
    @FXML private Button trackExpiryButton;
    @FXML private Button assignTaskButton;
    @FXML private Button loginButton;
    @FXML private Button refreshAlertsButton;
    @FXML private Button uploadButton;

    // TextField
    @FXML private TextField uidField;

    // TextArea
    @FXML private TextArea logsArea;

    // TableView and Columns
    @FXML private TableView<Document> documentsTable;
    @FXML private TableColumn<Document, String> docNameColumn;
    @FXML private TableColumn<Document, String> docTypeColumn;
    @FXML private TableColumn<Document, String> statusColumn;
    @FXML private TableColumn<Document, String> expiryDateColumn;

    // Sample data model
    public static class Document {
        private final SimpleStringProperty docName;
        private final SimpleStringProperty docType;
        private final SimpleStringProperty status;
        private final SimpleStringProperty expiryDate;

        public Document(String docName, String docType, String status, String expiryDate) {
            this.docName = new SimpleStringProperty(docName);
            this.docType = new SimpleStringProperty(docType);
            this.status = new SimpleStringProperty(status);
            this.expiryDate = new SimpleStringProperty(expiryDate);
        }

        public String getDocName() { return docName.get(); }
        public String getDocType() { return docType.get(); }
        public String getStatus() { return status.get(); }
        public String getExpiryDate() { return expiryDate.get(); }

        public void setDocName(String value) { docName.set(value); }
        public void setDocType(String value) { docType.set(value); }
        public void setStatus(String value) { status.set(value); }
        public void setExpiryDate(String value) { expiryDate.set(value); }
    }

    @FXML
    public void initialize() {
        // Set up table columns
        docNameColumn.setCellValueFactory(cellData -> cellData.getValue().docName);
        docTypeColumn.setCellValueFactory(cellData -> cellData.getValue().docType);
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().status);
        expiryDateColumn.setCellValueFactory(cellData -> cellData.getValue().expiryDate);

        // Sample data
        ObservableList<Document> sampleDocuments = FXCollections.observableArrayList(
                new Document("Protocol A", "Regulatory", "Valid", "2026-03-15"),
                new Document("Consent Form", "Compliance", "Expired", "2025-10-01")
        );
        documentsTable.setItems(sampleDocuments);

        // Button actions
        trackExpiryButton.setOnAction(e -> trackExpiry());
        assignTaskButton.setOnAction(e -> assignTask());
        loginButton.setOnAction(e -> login());
        refreshAlertsButton.setOnAction(e -> refreshAlerts());
        uploadButton.setOnAction(e -> uploadDocument());
    }

    private void trackExpiry() {
        logsArea.appendText("Tracking expiry dates...\n");
    }

    private void assignTask() {
        String uid = uidField.getText();
        logsArea.appendText("Assigning task to user: " + uid + "\n");
    }

    private void login() {
        logsArea.appendText("Logging in...\n");
    }

    private void refreshAlerts() {
        logsArea.appendText("Refreshing alerts...\n");
    }

    private void uploadDocument() {
        logsArea.appendText("Uploading document...\n");
    }
}
