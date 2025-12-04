package com.example.Shoyeb_2311303;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class ManageInventoryLevelsController {

    // ================ FXML COMPONENTS ================

    // Header
    @FXML private Label userLabel;

    // Tabs
    @FXML private TabPane dashboardTabPane;

    // Raw Materials Tab
    @FXML private TableView<RawMaterial> rawMaterialsTable;

    // In-Stock Tab
    @FXML private TableView<Product> inStockTable;

    // Low Stock Tab
    @FXML private TableView<LowStockItem> lowStockTable;
    @FXML private Label alertCountLabel;
    @FXML private Label criticalLabel;
    @FXML private Label warningLabel;

    // Buttons
    @FXML private Button refreshRawBtn;
    @FXML private Button refreshInStockBtn;
    @FXML private Button refreshLowBtn;
    @FXML private Button notifyBtn;
    @FXML private Button clearBtn;
    @FXML private Button updateBtn;

    // Update Panel
    @FXML private ComboBox<String> itemComboBox;
    @FXML private TextField quantityField;
    @FXML private TextField reorderField;
    @FXML private TextField storageField;

    // Status Bar
    @FXML private Label statusLabel;
    @FXML private Label timeLabel;

    // ================ DATA LISTS ================
    private ObservableList<RawMaterial> rawMaterialsList = FXCollections.observableArrayList();
    private ObservableList<Product> inStockList = FXCollections.observableArrayList();
    private ObservableList<LowStockItem> lowStockList = FXCollections.observableArrayList();
    private ObservableList<String> itemNamesList = FXCollections.observableArrayList();

    // ================ INITIALIZE METHOD ================
    @FXML
    public void initialize() {
        setupUserInfo();
        setupTables();
        loadSampleData();
        setupEventHandlers();
        updateStatusBar();
        startClock();
    }

    // ================ SETUP METHODS ================

    private void setupUserInfo() {
        userLabel.setText("User: Shoyeb (ID: 2311303)");
    }

    private void setupTables() {
        // Tables will auto-bind through FXML PropertyValueFactory
        rawMaterialsTable.setItems(rawMaterialsList);
        inStockTable.setItems(inStockList);
        lowStockTable.setItems(lowStockList);
    }

    private void setupEventHandlers() {
        // Item combo box selection listener
        itemComboBox.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        loadItemDetails(newValue);
                    }
                }
        );
    }

    private void loadSampleData() {
        // Load sample Raw Materials
        rawMaterialsList.addAll(
                new RawMaterial("RM001", "Steel Sheet", 1200, "pcs", 500, "WH-A1", "In Stock"),
                new RawMaterial("RM002", "Aluminum Rod", 450, "pcs", 200, "WH-B2", "In Stock"),
                new RawMaterial("RM003", "Copper Wire", 180, "kg", 100, "WH-C3", "Low Stock"),
                new RawMaterial("RM004", "Plastic Pellet", 3200, "kg", 1500, "WH-A2", "In Stock"),
                new RawMaterial("RM005", "Rubber Seal", 85, "pcs", 100, "WH-B1", "Critical")
        );

        // Load sample Products
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        inStockList.addAll(
                new Product("P001", "Smartphone X", 350, "Electronics", LocalDateTime.now().minusDays(1).format(formatter)),
                new Product("P002", "Laptop Pro", 120, "Electronics", LocalDateTime.now().minusDays(2).format(formatter)),
                new Product("P003", "Tablet Air", 85, "Electronics", LocalDateTime.now().format(formatter)),
                new Product("P004", "Wireless Mouse", 480, "Accessories", LocalDateTime.now().minusDays(3).format(formatter))
        );

        // Populate item combo box
        updateItemComboBox();

        // Update low stock list
        updateLowStockData();

        statusLabel.setText("Sample data loaded successfully");
    }

    private void updateItemComboBox() {
        itemNamesList.clear();

        // Add raw materials
        for (RawMaterial rm : rawMaterialsList) {
            itemNamesList.add("RM: " + rm.getName() + " (" + rm.getId() + ")");
        }

        // Add products
        for (Product p : inStockList) {
            itemNamesList.add("PD: " + p.getProductName() + " (" + p.getProductId() + ")");
        }

        itemComboBox.setItems(itemNamesList);

        // Select first item if available
        if (!itemNamesList.isEmpty()) {
            itemComboBox.getSelectionModel().selectFirst();
            loadItemDetails(itemNamesList.get(0));
        }
    }

    private void loadItemDetails(String itemString) {
        if (itemString == null || itemString.isEmpty()) return;

        try {
            // Extract ID from string (format: "TYPE: Name (ID)")
            String id = itemString.substring(itemString.lastIndexOf("(") + 1, itemString.lastIndexOf(")"));
            String type = itemString.substring(0, 2); // "RM" or "PD"

            if (type.equals("RM")) {
                // Find in raw materials
                for (RawMaterial rm : rawMaterialsList) {
                    if (rm.getId().equals(id)) {
                        quantityField.setText(String.valueOf(rm.getQuantity()));
                        reorderField.setText(String.valueOf(rm.getReorderLevel()));
                        storageField.setText(rm.getLocation());
                        return;
                    }
                }
            } else if (type.equals("PD")) {
                // Find in products
                for (Product p : inStockList) {
                    if (p.getProductId().equals(id)) {
                        quantityField.setText(String.valueOf(p.getQuantity()));
                        reorderField.setText(""); // Products might not have reorder level
                        storageField.setText("Product Warehouse");
                        return;
                    }
                }
            }
        } catch (Exception e) {
            showError("Error", "Cannot load item details: " + e.getMessage());
        }
    }

    // ================ EVENT HANDLERS ================

    @FXML
    private void handleRefreshRawMaterials() {
        rawMaterialsTable.refresh();
        updateItemComboBox();
        statusLabel.setText("Raw Materials refreshed");
        updateStatusBar();
    }

    @FXML
    private void handleRefreshInStock() {
        inStockTable.refresh();
        updateItemComboBox();
        statusLabel.setText("In-Stock products refreshed");
        updateStatusBar();
    }

    @FXML
    private void handleRefreshLowStock() {
        updateLowStockData();
        lowStockTable.refresh();
        statusLabel.setText("Low Stock alerts refreshed");
        updateStatusBar();
    }

    @FXML
    private void handleNotifyProcurement() {
        if (lowStockList.isEmpty()) {
            showInfo("Notification", "No low stock items to report.");
            return;
        }

        // Create notification message
        StringBuilder message = new StringBuilder();
        message.append("=== PROCUREMENT REQUEST ===\n");
        message.append("Date: ").append(LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).append("\n");
        message.append("Requested by: Shoyeb (2311303)\n");
        message.append("=================================\n\n");
        message.append("LOW STOCK ITEMS NEEDING REORDER:\n");

        int counter = 1;
        for (LowStockItem item : lowStockList) {
            message.append("\n").append(counter++).append(". ").append(item.getItemName())
                    .append("\n   Current Stock: ").append(item.getCurrentStock())
                    .append("\n   Reorder Level: ").append(item.getReorderLevel())
                    .append("\n   Status: ").append(item.getStatus())
                    .append("\n   Action: ").append(item.getActionNeeded());
        }

        // Show confirmation dialog
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirm Notification");
        confirmAlert.setHeaderText("Send Procurement Notification");
        confirmAlert.setContentText("Are you sure you want to notify the procurement department about " +
                lowStockList.size() + " low stock items?");

        Optional<ButtonType> result = confirmAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // In real app, send email or API call here
            showInfo("Notification Sent",
                    "Procurement department has been notified about " +
                            lowStockList.size() + " low stock items.");

            statusLabel.setText("Procurement notified - " + lowStockList.size() + " items");
            updateStatusBar();
        }
    }

    @FXML
    private void handleUpdateInventory() {
        String selectedItem = itemComboBox.getValue();
        String quantityText = quantityField.getText().trim();
        String reorderText = reorderField.getText().trim();
        String storageText = storageField.getText().trim();

        // Validation
        if (selectedItem == null || selectedItem.isEmpty()) {
            showError("Error", "Please select an item to update.");
            return;
        }

        if (quantityText.isEmpty()) {
            showError("Error", "Please enter quantity.");
            return;
        }

        try {
            // Parse inputs
            int newQuantity = Integer.parseInt(quantityText);
            int newReorderLevel = reorderText.isEmpty() ? 0 : Integer.parseInt(reorderText);

            // Extract ID from selected item
            String id = selectedItem.substring(selectedItem.lastIndexOf("(") + 1, selectedItem.lastIndexOf(")"));
            String type = selectedItem.substring(0, 2);

            boolean updated = false;

            if (type.equals("RM")) {
                // Update raw material
                for (RawMaterial rm : rawMaterialsList) {
                    if (rm.getId().equals(id)) {
                        rm.setQuantity(newQuantity);
                        if (!reorderText.isEmpty()) {
                            rm.setReorderLevel(newReorderLevel);
                        }
                        if (!storageText.isEmpty()) {
                            rm.setLocation(storageText);
                        }

                        // Update status based on quantity
                        if (newQuantity <= 0) {
                            rm.setStatus("Out of Stock");
                        } else if (newQuantity <= newReorderLevel) {
                            rm.setStatus("Low Stock");
                        } else if (newQuantity <= newReorderLevel * 2) {
                            rm.setStatus("Warning");
                        } else {
                            rm.setStatus("In Stock");
                        }

                        updated = true;
                        break;
                    }
                }
            } else if (type.equals("PD")) {
                // Update product
                for (Product p : inStockList) {
                    if (p.getProductId().equals(id)) {
                        p.setQuantity(newQuantity);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        p.setLastUpdated(LocalDateTime.now().format(formatter));
                        updated = true;
                        break;
                    }
                }
            }

            if (updated) {
                // Refresh tables
                rawMaterialsTable.refresh();
                inStockTable.refresh();

                // Update low stock data
                updateLowStockData();
                lowStockTable.refresh();

                // Show success message
                showInfo("Success", "Inventory updated successfully!");

                // Clear fields
                handleClearFields();

                // Select next item
                int currentIndex = itemComboBox.getSelectionModel().getSelectedIndex();
                if (currentIndex < itemNamesList.size() - 1) {
                    itemComboBox.getSelectionModel().selectNext();
                }

                statusLabel.setText("Inventory updated - " + selectedItem);
                updateStatusBar();
            } else {
                showError("Error", "Item not found in database.");
            }

        } catch (NumberFormatException e) {
            showError("Input Error", "Please enter valid numbers for quantity and reorder level.");
        } catch (Exception e) {
            showError("Error", "An error occurred: " + e.getMessage());
        }
    }

    @FXML
    private void handleClearFields() {
        quantityField.clear();
        reorderField.clear();
        storageField.clear();
        statusLabel.setText("Fields cleared");
        updateStatusBar();
    }

    // ================ HELPER METHODS ================

    private void updateLowStockData() {
        lowStockList.clear();

        int criticalCount = 0;
        int warningCount = 0;

        // Check raw materials
        for (RawMaterial rm : rawMaterialsList) {
            int quantity = rm.getQuantity();
            int reorderLevel = rm.getReorderLevel();

            if (quantity <= reorderLevel) {
                String status;
                String action;

                if (quantity <= reorderLevel * 0.3) {
                    status = "CRITICAL";
                    action = "URGENT - Order Now";
                    criticalCount++;
                } else if (quantity <= reorderLevel * 0.6) {
                    status = "HIGH";
                    action = "Order Required";
                    warningCount++;
                } else {
                    status = "LOW";
                    action = "Monitor Closely";
                    warningCount++;
                }

                lowStockList.add(new LowStockItem(
                        rm.getName(),
                        quantity,
                        reorderLevel,
                        status,
                        action
                ));
            }
        }

        // Update alert labels
        int totalAlerts = lowStockList.size();
        alertCountLabel.setText("Alerts: " + totalAlerts);
        criticalLabel.setText("Critical: " + criticalCount);
        warningLabel.setText("Warning: " + warningCount);

        // Update label colors based on severity
        if (criticalCount > 0) {
            alertCountLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
        } else if (warningCount > 0) {
            alertCountLabel.setStyle("-fx-text-fill: #f39c12; -fx-font-weight: bold;");
        } else {
            alertCountLabel.setStyle("-fx-text-fill: #27ae60; -fx-font-weight: bold;");
        }
    }

    private void updateStatusBar() {
        int totalItems = rawMaterialsList.size() + inStockList.size();
        timeLabel.setText("Total Items: " + totalItems + " | " +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }

    private void startClock() {
        Thread clockThread = new Thread(() -> {
            try {
                while (true) {
                    javafx.application.Platform.runLater(() -> {
                        timeLabel.setText("Total Items: " + (rawMaterialsList.size() + inStockList.size()) +
                                " | " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                    });
                    Thread.sleep(1000); // Update every second
                }
            } catch (InterruptedException e) {
                // Thread interrupted
            }
        });
        clockThread.setDaemon(true);
        clockThread.start();
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfo(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // ================ DATA MODEL CLASSES ================

    public static class RawMaterial {
        private final StringProperty id;
        private final StringProperty name;
        private final IntegerProperty quantity;
        private final StringProperty unit;
        private final IntegerProperty reorderLevel;
        private final StringProperty location;
        private final StringProperty status;

        public RawMaterial(String id, String name, int quantity, String unit,
                           int reorderLevel, String location, String status) {
            this.id = new SimpleStringProperty(id);
            this.name = new SimpleStringProperty(name);
            this.quantity = new SimpleIntegerProperty(quantity);
            this.unit = new SimpleStringProperty(unit);
            this.reorderLevel = new SimpleIntegerProperty(reorderLevel);
            this.location = new SimpleStringProperty(location);
            this.status = new SimpleStringProperty(status);
        }

        // Getters
        public String getId() { return id.get(); }
        public String getName() { return name.get(); }
        public int getQuantity() { return quantity.get(); }
        public String getUnit() { return unit.get(); }
        public int getReorderLevel() { return reorderLevel.get(); }
        public String getLocation() { return location.get(); }
        public String getStatus() { return status.get(); }

        // Setters
        public void setQuantity(int qty) { this.quantity.set(qty); }
        public void setReorderLevel(int level) { this.reorderLevel.set(level); }
        public void setLocation(String loc) { this.location.set(loc); }
        public void setStatus(String status) { this.status.set(status); }

        // Property getters (for TableView)
        public StringProperty idProperty() { return id; }
        public StringProperty nameProperty() { return name; }
        public IntegerProperty quantityProperty() { return quantity; }
        public StringProperty unitProperty() { return unit; }
        public IntegerProperty reorderLevelProperty() { return reorderLevel; }
        public StringProperty locationProperty() { return location; }
        public StringProperty statusProperty() { return status; }
    }

    public static class Product {
        private final StringProperty productId;
        private final StringProperty productName;
        private final IntegerProperty quantity;
        private final StringProperty category;
        private final StringProperty lastUpdated;

        public Product(String id, String name, int quantity, String category, String updated) {
            this.productId = new SimpleStringProperty(id);
            this.productName = new SimpleStringProperty(name);
            this.quantity = new SimpleIntegerProperty(quantity);
            this.category = new SimpleStringProperty(category);
            this.lastUpdated = new SimpleStringProperty(updated);
        }

        // Getters
        public String getProductId() { return productId.get(); }
        public String getProductName() { return productName.get(); }
        public int getQuantity() { return quantity.get(); }
        public String getCategory() { return category.get(); }
        public String getLastUpdated() { return lastUpdated.get(); }

        // Setters
        public void setQuantity(int qty) { this.quantity.set(qty); }
        public void setLastUpdated(String updated) { this.lastUpdated.set(updated); }

        // Property getters
        public StringProperty productIdProperty() { return productId; }
        public StringProperty productNameProperty() { return productName; }
        public IntegerProperty quantityProperty() { return quantity; }
        public StringProperty categoryProperty() { return category; }
        public StringProperty lastUpdatedProperty() { return lastUpdated; }
    }

    public static class LowStockItem {
        private final StringProperty itemName;
        private final IntegerProperty currentStock;
        private final IntegerProperty reorderLevel;
        private final StringProperty status;
        private final StringProperty actionNeeded;

        public LowStockItem(String name, int current, int reorder,
                            String status, String action) {
            this.itemName = new SimpleStringProperty(name);
            this.currentStock = new SimpleIntegerProperty(current);
            this.reorderLevel = new SimpleIntegerProperty(reorder);
            this.status = new SimpleStringProperty(status);
            this.actionNeeded = new SimpleStringProperty(action);
        }

        // Getters
        public String getItemName() { return itemName.get(); }
        public int getCurrentStock() { return currentStock.get(); }
        public int getReorderLevel() { return reorderLevel.get(); }
        public String getStatus() { return status.get(); }
        public String getActionNeeded() { return actionNeeded.get(); }

        // Property getters
        public StringProperty itemNameProperty() { return itemName; }
        public IntegerProperty currentStockProperty() { return currentStock; }
        public IntegerProperty reorderLevelProperty() { return reorderLevel; }
        public StringProperty statusProperty() { return status; }
        public StringProperty actionNeededProperty() { return actionNeeded; }
    }
}