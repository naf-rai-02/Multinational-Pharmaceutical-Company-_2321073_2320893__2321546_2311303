package com.example.Shoyeb_2311303.ClinicalTrialCoordinator;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ManageClinicalTrialStudiesController {

    // Planned Studies Table
    @FXML
    private TableView<Trial> plannedTrialsTable;
    @FXML
    private TableColumn<Trial, String> protocolColumn;
    @FXML
    private TableColumn<Trial, String> phaseColumn;
    @FXML
    private TableColumn<Trial, String> siteColumn;
    @FXML
    private TableColumn<Trial, String> statusColumn;

    // Active Studies Table
    @FXML
    private TableView<Trial> activeTrialsTable;
    @FXML
    private TableColumn<Trial, String> activeProtocolColumn;
    @FXML
    private TableColumn<Trial, String> activePhaseColumn;
    @FXML
    private TableColumn<Trial, String> activeSiteColumn;
    @FXML
    private TableColumn<Trial, String> activeStatusColumn;

    // Completed Studies Table
    @FXML
    private TableView<Trial> completedTrialsTable;
    @FXML
    private TableColumn<Trial, String> completedProtocolColumn;
    @FXML
    private TableColumn<Trial, String> completedPhaseColumn;
    @FXML
    private TableColumn<Trial, String> completedSiteColumn;
    @FXML
    private TableColumn<Trial, String> completedStatusColumn;

    // Form fields
    @FXML
    private TextField protocolField;
    @FXML
    private TextField phaseField;
    @FXML
    private TextField siteField;

    // Button
    @FXML
    private Button saveBtn;

    // Notification area
    @FXML
    private TextArea notificationArea;

    // Sample data lists
    private ObservableList<Trial> plannedTrials = FXCollections.observableArrayList();
    private ObservableList<Trial> activeTrials = FXCollections.observableArrayList();
    private ObservableList<Trial> completedTrials = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // Initialize table columns
        protocolColumn.setCellValueFactory(data -> data.getValue().protocolProperty());
        phaseColumn.setCellValueFactory(data -> data.getValue().phaseProperty());
        siteColumn.setCellValueFactory(data -> data.getValue().siteProperty());
        statusColumn.setCellValueFactory(data -> data.getValue().statusProperty());

        activeProtocolColumn.setCellValueFactory(data -> data.getValue().protocolProperty());
        activePhaseColumn.setCellValueFactory(data -> data.getValue().phaseProperty());
        activeSiteColumn.setCellValueFactory(data -> data.getValue().siteProperty());
        activeStatusColumn.setCellValueFactory(data -> data.getValue().statusProperty());

        completedProtocolColumn.setCellValueFactory(data -> data.getValue().protocolProperty());
        completedPhaseColumn.setCellValueFactory(data -> data.getValue().phaseProperty());
        completedSiteColumn.setCellValueFactory(data -> data.getValue().siteProperty());
        completedStatusColumn.setCellValueFactory(data -> data.getValue().statusProperty());

        // Load sample data
        loadSampleData();

        // Set data to tables
        plannedTrialsTable.setItems(plannedTrials);
        activeTrialsTable.setItems(activeTrials);
        completedTrialsTable.setItems(completedTrials);

        // Button action
        saveBtn.setOnAction(event -> saveTrial());
    }

    // Sample data loader
    private void loadSampleData() {
        plannedTrials.add(new Trial("P001", "Phase I", "Site A", "Planned"));
        plannedTrials.add(new Trial("P002", "Phase II", "Site B", "Planned"));

        activeTrials.add(new Trial("A001", "Phase II", "Site C", "Active"));
        activeTrials.add(new Trial("A002", "Phase III", "Site D", "Active"));

        completedTrials.add(new Trial("C001", "Phase I", "Site E", "Completed"));
    }

    // Save or update trial
    private void saveTrial() {
        String protocol = protocolField.getText();
        String phase = phaseField.getText();
        String site = siteField.getText();

        if (protocol.isEmpty() || phase.isEmpty() || site.isEmpty()) {
            showNotification("Please fill all fields before saving.");
            return;
        }

        // For demonstration, add to planned trials
        Trial newTrial = new Trial(protocol, phase, site, "Planned");
        plannedTrials.add(newTrial);
        showNotification("Trial " + protocol + " has been saved to Planned Studies.");

        // Clear form
        protocolField.clear();
        phaseField.clear();
        siteField.clear();
    }

    private void showNotification(String message) {
        notificationArea.appendText(message + "\n");
    }

    // --- Inner Trial class ---
    public static class Trial {
        private final StringProperty protocol;
        private final StringProperty phase;
        private final StringProperty site;
        private final StringProperty status;

        public Trial(String protocol, String phase, String site, String status) {
            this.protocol = new SimpleStringProperty(protocol);
            this.phase = new SimpleStringProperty(phase);
            this.site = new SimpleStringProperty(site);
            this.status = new SimpleStringProperty(status);
        }

        public StringProperty protocolProperty() { return protocol; }
        public StringProperty phaseProperty() { return phase; }
        public StringProperty siteProperty() { return site; }
        public StringProperty statusProperty() { return status; }
    }
}
