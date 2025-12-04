package com.example.Shoyeb_2311303;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class CollaborateProcurementProduction_QAController {

    // Event 1: Access Cross-Department Platform
    @FXML
    private Button accessPlatformBtn;

    // Event 2: Open Shared Tasks / Communication Threads
    @FXML
    private ListView<String> tasksListView;

    // Event 3: Upload Documents / Add Comments / Post Updates
    @FXML
    private TextArea updateTextArea;
    @FXML
    private Button uploadBtn;

    // Event 4: Close / Archive Tasks
    @FXML
    private Button closeTaskBtn;

    // Status / Logs area
    @FXML
    private TextArea statusArea;

    // Initialize method (called automatically after FXML is loaded)
    @FXML
    public void initialize() {
        // Set up button actions
        accessPlatformBtn.setOnAction(event -> handleAccessPlatform());
        uploadBtn.setOnAction(event -> handleUploadUpdate());
        closeTaskBtn.setOnAction(event -> handleCloseTask());

        // Example: populate tasks list with dummy data
        tasksListView.getItems().addAll("Task 1: Review documents", "Task 2: Approve procurement", "Task 3: QA check");
    }

    // Event 1 handler
    private void handleAccessPlatform() {
        statusArea.appendText("Accessed cross-department collaboration platform.\n");
    }

    // Event 3 handler
    private void handleUploadUpdate() {
        String update = updateTextArea.getText();
        if (!update.isEmpty()) {
            statusArea.appendText("Posted update: " + update + "\n");
            updateTextArea.clear();
        } else {
            statusArea.appendText("No update entered.\n");
        }
    }

    // Event 4 handler
    private void handleCloseTask() {
        String selectedTask = tasksListView.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            statusArea.appendText("Closed/Archived task: " + selectedTask + "\n");
            tasksListView.getItems().remove(selectedTask);
        } else {
            statusArea.appendText("No task selected to close.\n");
        }
    }
}
