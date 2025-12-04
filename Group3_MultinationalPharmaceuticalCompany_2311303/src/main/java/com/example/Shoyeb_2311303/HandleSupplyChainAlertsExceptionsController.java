package com.example.Shoyeb_2311303;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class HandleSupplyChainAlertsExceptionsController {

    // TableView and columns with proper generic types
    @FXML
    private TableView<Alert> alertsTable;

    @FXML
    private TableColumn<Alert, Integer> idColumn;
    @FXML
    private TableColumn<Alert, String> typeColumn;
    @FXML
    private TableColumn<Alert, String> statusColumn;
    @FXML
    private TableColumn<Alert, String> riskColumn;
    @FXML
    private TableColumn<Alert, String> notesColumn;

    // Buttons
    @FXML
    private Button addNoteBtn;
    @FXML
    private Button categorizeBtn;
    @FXML
    private Button updateRiskBtn;
    @FXML
    private Button escalateBtn;
    @FXML
    private Button resolveBtn;

    // TextArea for notes
    @FXML
    private TextArea notesArea;

    @FXML
    public void initialize() {
        // Initialize TableView columns with Alert properties
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        riskColumn.setCellValueFactory(new PropertyValueFactory<>("riskLevel"));
        notesColumn.setCellValueFactory(new PropertyValueFactory<>("notes"));
    }

    // TODO: Add event handling methods for buttons
    // Example:
    // @FXML
    // private void handleAddNote() { ... }
}
