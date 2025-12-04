package com.example.Shoyeb_2311303.ClinicalTrialCoordinator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MonitorTrialProgressDigitalDataControlclass {

    @FXML
    private TextArea statusArea;
    @FXML
    private Button enrollmentRateButton;
    @FXML
    private TextField uidField;
    @FXML
    private Button openTrialButton;
    @FXML
    private TableView<EDCLog> edcLogsTable;
    @FXML
    private Button sendAlertsButton;
    @FXML
    private Button dropoutsButton;
    @FXML
    private Button visitCompletionButton;
    @FXML
    private Button deviationsButton;
    @FXML
    private TableColumn<EDCLog, String> logIdColumn;
    @FXML
    private Button reviewLogsButton;
    @FXML
    private TableColumn<EDCLog, String> statusColumn;
    @FXML
    private TableColumn<EDCLog, String> commentsColumn;
    @FXML
    private Button generateReportButton;
    @FXML
    private TableColumn<EDCLog, String> dateColumn;

    // Sample data class for EDC logs
    public static class EDCLog {
        private String logId;
        private String date;
        private String status;
        private String comments;

        public EDCLog(String logId, String date, String status, String comments) {
            this.logId = logId;
            this.date = date;
            this.status = status;
            this.comments = comments;
        }

        public String getLogId() { return logId; }
        public String getDate() { return date; }
        public String getStatus() { return status; }
        public String getComments() { return comments; }

        public void setLogId(String logId) { this.logId = logId; }
        public void setDate(String date) { this.date = date; }
        public void setStatus(String status) { this.status = status; }
        public void setComments(String comments) { this.comments = comments; }
    }

    @FXML
    public void initialize() {
        // Initialize TableView columns
        logIdColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getLogId()));
        dateColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getDate()));
        statusColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getStatus()));
        commentsColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getComments()));

        // Load sample data
        ObservableList<EDCLog> logs = FXCollections.observableArrayList(
                new EDCLog("L001", "2025-12-01", "Complete", "No issues"),
                new EDCLog("L002", "2025-12-02", "Pending", "Missing signature")
        );
        edcLogsTable.setItems(logs);

        // Button actions
        openTrialButton.setOnAction(e -> openTrial());
        enrollmentRateButton.setOnAction(e -> viewEnrollmentRate());
        visitCompletionButton.setOnAction(e -> viewVisitCompletion());
        dropoutsButton.setOnAction(e -> viewDropouts());
        deviationsButton.setOnAction(e -> viewDeviations());
        reviewLogsButton.setOnAction(e -> reviewEDCLogs());
        generateReportButton.setOnAction(e -> generateProgressReport());
        sendAlertsButton.setOnAction(e -> sendAlerts());
    }

    // Event Methods
    private void openTrial() {
        String uid = uidField.getText();
        statusArea.appendText("Opening trial with UID: " + uid + "\n");
    }

    private void viewEnrollmentRate() {
        statusArea.appendText("Displaying Enrollment Rate dashboard...\n");
    }

    private void viewVisitCompletion() {
        statusArea.appendText("Displaying Visit Completion dashboard...\n");
    }

    private void viewDropouts() {
        statusArea.appendText("Displaying Dropouts dashboard...\n");
    }

    private void viewDeviations() {
        statusArea.appendText("Displaying Deviations dashboard...\n");
    }

    private void reviewEDCLogs() {
        statusArea.appendText("Reviewing EDC logs...\n");
    }

    private void generateProgressReport() {
        statusArea.appendText("Generating Progress Report...\n");
    }

    private void sendAlerts() {
        statusArea.appendText("Sending alerts to sites/investigators...\n");
    }
}