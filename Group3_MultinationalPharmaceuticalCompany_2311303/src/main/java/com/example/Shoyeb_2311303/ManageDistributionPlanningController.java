package com.example.Shoyeb_2311303;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class ManageDistributionPlanningController {

    // Event 2: Selection controls
    @FXML
    private ComboBox<String> warehouseComboBox;

    @FXML
    private ComboBox<String> regionComboBox;

    @FXML
    private ListView<String> productListView;

    // Event 3: Schedule controls
    @FXML
    private TextField quantityField;

    @FXML
    private DatePicker timelinePicker;

    @FXML
    private Button updateScheduleBtn;

    // Event 4: Save & Publish buttons
    @FXML
    private Button saveBtn;

    @FXML
    private Button publishBtn;

    // Initialize method
    @FXML
    public void initialize() {
        // Dummy data for testing
        regionComboBox.getItems().addAll("Region A", "Region B", "Region C");
        warehouseComboBox.getItems().addAll("Warehouse 1", "Warehouse 2", "Warehouse 3");
        productListView.getItems().addAll("Product 1", "Product 2", "Product 3");

        // Button actions
        updateScheduleBtn.setOnAction(e -> updateSchedule());
        saveBtn.setOnAction(e -> savePlan());
        publishBtn.setOnAction(e -> publishPlan());
    }

    // Methods for buttons
    private void updateSchedule() {
        String quantity = quantityField.getText();
        String date = (timelinePicker.getValue() != null) ? timelinePicker.getValue().toString() : "";
        System.out.println("Schedule Updated: Quantity = " + quantity + ", Date = " + date);
    }

    private void savePlan() {
        System.out.println("Plan saved successfully!");
    }

    private void publishPlan() {
        System.out.println("Plan published to all stakeholders!");
    }
}
