package com.example.Shoyeb_2311303.ClinicalTrialCoordinator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class CoordinateCommunicationAmongTeamsController {

    @FXML
    private Button meetingButton;
    @FXML
    private VBox mainVBox;
    @FXML
    private TextField uidField;
    @FXML
    private Button accessHubButton;
    @FXML
    private Button announcementButton;
    @FXML
    private Button assignTaskButton;
    @FXML
    private TextArea reviewArea;
    @FXML
    private TextArea notificationArea;

    @FXML
    public void initialize() {
        // Event 1: Access CTMS Hub
        accessHubButton.setOnAction(e -> {
            String uid = uidField.getText().trim();
            if (uid.isEmpty()) {
                notificationArea.appendText("Please enter a UID to access the CTMS hub.\n");
            } else {
                notificationArea.appendText("Accessed CTMS Communication Hub with UID: " + uid + "\n");
            }
        });

        // Event 2: Display tasks/messages (simulated)
        reviewArea.setText("Task 1: Review protocol documents\n"
                + "Task 2: Approve participant enrollment\n"
                + "Message: Reminder - Team meeting at 3 PM\n");

        // Event 3: Buttons for announcements, meetings, and tasks
        announcementButton.setOnAction(e -> {
            notificationArea.appendText("Announcement created and sent to team members.\n");
        });

        meetingButton.setOnAction(e -> {
            notificationArea.appendText("Meeting scheduled and notifications sent.\n");
        });

        assignTaskButton.setOnAction(e -> {
            notificationArea.appendText("Task assigned to team members.\n");
        });
    }
}
