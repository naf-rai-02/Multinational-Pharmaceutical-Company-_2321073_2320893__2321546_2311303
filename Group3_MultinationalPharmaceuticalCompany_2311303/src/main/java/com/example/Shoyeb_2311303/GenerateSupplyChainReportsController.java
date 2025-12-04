package com.example.Shoyeb_2311303;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TabPane;

import java.time.LocalDate;

public class GenerateSupplyChainReportsController {

    // FXML UI Components
    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private ComboBox<String> productComboBox;

    @FXML
    private ComboBox<String> regionComboBox;

    @FXML
    private Button generateReportBtn;

    @FXML
    private TabPane reportsTabPane;

    @FXML
    private LineChart<String, Number> leadTimesChart;

    @FXML
    private BarChart<String, Number> shortagesChart;

    @FXML
    private LineChart<String, Number> delaysChart;

    @FXML
    private Button exportBtn;

    @FXML
    private Button archiveBtn;

    @FXML
    public void initialize() {
        // Populate ComboBoxes with sample data
        productComboBox.setItems(FXCollections.observableArrayList("Product A", "Product B", "Product C"));
        regionComboBox.setItems(FXCollections.observableArrayList("North", "South", "East", "West"));

        // Set button actions
        generateReportBtn.setOnAction(e -> generateReport());
        exportBtn.setOnAction(e -> exportReport());
        archiveBtn.setOnAction(e -> archiveReport());
    }

    // Generate charts and report
    private void generateReport() {
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();
        String product = productComboBox.getValue();
        String region = regionComboBox.getValue();

        // TODO: Replace with real data fetching logic
        System.out.println("Generating report for: " + product + " in " + region + " from " + startDate + " to " + endDate);

        populateLeadTimesChart();
        populateShortagesChart();
        populateDelaysChart();
    }

    private void populateLeadTimesChart() {
        leadTimesChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Lead Times (days)");

        // Sample Data
        series.getData().add(new XYChart.Data<>("2025-11-01", 5));
        series.getData().add(new XYChart.Data<>("2025-11-02", 7));
        series.getData().add(new XYChart.Data<>("2025-11-03", 6));

        leadTimesChart.getData().add(series);
    }

    private void populateShortagesChart() {
        shortagesChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Shortages");

        // Sample Data
        series.getData().add(new XYChart.Data<>("Product A", 10));
        series.getData().add(new XYChart.Data<>("Product B", 5));
        series.getData().add(new XYChart.Data<>("Product C", 7));

        shortagesChart.getData().add(series);
    }

    private void populateDelaysChart() {
        delaysChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Delays (days)");

        // Sample Data
        series.getData().add(new XYChart.Data<>("2025-11-01", 2));
        series.getData().add(new XYChart.Data<>("2025-11-02", 3));
        series.getData().add(new XYChart.Data<>("2025-11-03", 1));

        delaysChart.getData().add(series);
    }

    private void exportReport() {
        // TODO: Implement export logic (e.g., PDF, Excel)
        System.out.println("Report exported!");
    }

    private void archiveReport() {
        // TODO: Implement archiving logic (e.g., save to DB)
        System.out.println("Report archived!");
    }
}
