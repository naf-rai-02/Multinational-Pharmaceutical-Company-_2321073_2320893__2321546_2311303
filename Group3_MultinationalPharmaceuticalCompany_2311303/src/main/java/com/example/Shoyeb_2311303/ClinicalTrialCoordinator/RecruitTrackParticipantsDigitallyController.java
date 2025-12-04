package com.example.Shoyeb_2311303.ClinicalTrialCoordinator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class RecruitTrackParticipantsDigitallyController {

    @FXML private Button confirmEnrollmentButton;
    @FXML private TableColumn<Participant, String> eligibilityColumn;
    @FXML private TextField userIdField;
    @FXML private TableColumn<Participant, String> nameColumn;
    @FXML private TableColumn<Participant, String> statusColumn;
    @FXML private Button loginButton;
    @FXML private TableView<Participant> participantTable;
    @FXML private TableColumn<Participant, String> consentColumn;
    @FXML private TextArea notificationArea;
    @FXML private Button updateConsentButton;
    @FXML private TableColumn<Participant, String> idColumn;
    @FXML private Button updateEligibilityButton;

    private final ObservableList<Participant> participants = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Initialize TableView columns
        idColumn.setCellValueFactory(data -> data.getValue().idProperty());
        nameColumn.setCellValueFactory(data -> data.getValue().nameProperty());
        statusColumn.setCellValueFactory(data -> data.getValue().statusProperty());
        eligibilityColumn.setCellValueFactory(data -> data.getValue().eligibilityProperty());
        consentColumn.setCellValueFactory(data -> data.getValue().consentProperty());

        // Sample participant data
        participants.addAll(
                new Participant("P001", "Alice", "Pending", "Eligible", "Not Signed"),
                new Participant("P002", "Bob", "Pending", "Pending", "Not Signed"),
                new Participant("P003", "Charlie", "Enrolled", "Eligible", "Signed"),
                new Participant("P004", "Diana", "Withdrawn", "N/A", "Not Signed")
        );

        participantTable.setItems(participants);

        // Row color-coding
        participantTable.setRowFactory(tv -> new TableRow<Participant>() {
            @Override
            protected void updateItem(Participant participant, boolean empty) {
                super.updateItem(participant, empty);
                if (participant == null || empty) {
                    setStyle("");
                } else {
                    String status = participant.getStatus();
                    if ("Enrolled".equals(status)) {
                        setStyle("-fx-background-color: lightgreen;");
                    } else if ("Pending".equals(status)) {
                        setStyle("-fx-background-color: lightyellow;");
                    } else if ("Withdrawn".equals(status)) {
                        setStyle("-fx-background-color: lightcoral;");
                    } else {
                        setStyle("");
                    }
                }
            }
        });

        // Button actions
        loginButton.setOnAction(e -> handleLogin());
        updateEligibilityButton.setOnAction(e -> handleUpdateEligibility());
        updateConsentButton.setOnAction(e -> handleUpdateConsent());
        confirmEnrollmentButton.setOnAction(e -> handleConfirmEnrollment());
    }

    private void handleLogin() {
        String uid = userIdField.getText();
        if (uid.isEmpty()) {
            notificationArea.appendText("Please enter a User ID.\n");
        } else {
            notificationArea.appendText("User " + uid + " logged in successfully.\n");
        }
    }

    private void handleUpdateEligibility() {
        Participant selected = participantTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setEligibility("Updated");
            participantTable.refresh();
            notificationArea.appendText("Eligibility updated for " + selected.getName() + ".\n");
        } else {
            notificationArea.appendText("Please select a participant to update eligibility.\n");
        }
    }

    private void handleUpdateConsent() {
        Participant selected = participantTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setConsent("Signed");
            participantTable.refresh();
            notificationArea.appendText("Consent form signed for " + selected.getName() + ".\n");
        } else {
            notificationArea.appendText("Please select a participant to update consent.\n");
        }
    }

    private void handleConfirmEnrollment() {
        Participant selected = participantTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setStatus("Enrolled");
            participantTable.refresh();
            notificationArea.appendText("Enrollment confirmed for " + selected.getName() + ".\n");
            notificationArea.appendText("Notification sent to investigators: Participant " + selected.getName() + " has been enrolled.\n");
        } else {
            notificationArea.appendText("Please select a participant to confirm enrollment.\n");
        }
    }

    // ----- Participant Model -----
    public static class Participant {
        private final javafx.beans.property.SimpleStringProperty id;
        private final javafx.beans.property.SimpleStringProperty name;
        private final javafx.beans.property.SimpleStringProperty status;
        private final javafx.beans.property.SimpleStringProperty eligibility;
        private final javafx.beans.property.SimpleStringProperty consent;

        public Participant(String id, String name, String status, String eligibility, String consent) {
            this.id = new javafx.beans.property.SimpleStringProperty(id);
            this.name = new javafx.beans.property.SimpleStringProperty(name);
            this.status = new javafx.beans.property.SimpleStringProperty(status);
            this.eligibility = new javafx.beans.property.SimpleStringProperty(eligibility);
            this.consent = new javafx.beans.property.SimpleStringProperty(consent);
        }

        public javafx.beans.property.StringProperty idProperty() { return id; }
        public javafx.beans.property.StringProperty nameProperty() { return name; }
        public javafx.beans.property.StringProperty statusProperty() { return status; }
        public javafx.beans.property.StringProperty eligibilityProperty() { return eligibility; }
        public javafx.beans.property.StringProperty consentProperty() { return consent; }

        public String getStatus() { return status.get(); }
        public String getName() { return name.get(); }
        public void setStatus(String status) { this.status.set(status); }
        public void setEligibility(String eligibility) { this.eligibility.set(eligibility); }
        public void setConsent(String consent) { this.consent.set(consent); }
    }
}
