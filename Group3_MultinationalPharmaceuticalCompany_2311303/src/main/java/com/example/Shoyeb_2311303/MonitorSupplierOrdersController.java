package com.example.Shoyeb_2311303;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MonitorSupplierOrdersController {

    @FXML
    private TableView<Order> orderTable;
    @FXML
    private Button refreshBtn;
    @FXML
    private Button approveBtn;
    @FXML
    private Button rejectBtn;

    private ObservableList<Order> orderList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        loadColumns();
        loadOrders();

        refreshBtn.setOnAction(e -> loadOrders());
        approveBtn.setOnAction(e -> approveOrder());
        rejectBtn.setOnAction(e -> rejectOrder());
    }


    private void loadColumns() {
        TableColumn<Order, Integer> idCol = new TableColumn<>("Order ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("orderId"));

        TableColumn<Order, String> nameCol = new TableColumn<>("Supplier");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("supplierName"));

        TableColumn<Order, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        orderTable.getColumns().clear();
        orderTable.getColumns().addAll(idCol, nameCol, statusCol);
    }


    private void loadOrders() {
        orderList.clear();
        orderList.add(new Order(1, "ABC Supplier", "Pending"));
        orderList.add(new Order(2, "XYZ Supplier", "Pending"));
        orderTable.setItems(orderList);
    }


    private void approveOrder() {
        Order selected = orderTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            alert("Please select an order!");
            return;
        }
        selected.setStatus("Approved");
        orderTable.refresh();
        alert("Order Approved!");
    }


    private void rejectOrder() {
        Order selected = orderTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            alert("Please select an order!");
            return;
        }
        selected.setStatus("Rejected");
        orderTable.refresh();
        alert("Order Rejected!");
    }

    private void alert(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).show();
    }


    public static class Order {
        private int orderId;
        private String supplierName;
        private String status;

        public Order(int orderId, String supplierName, String status) {
            this.orderId = orderId;
            this.supplierName = supplierName;
            this.status = status;
        }

        public int getOrderId() { return orderId; }
        public String getSupplierName() { return supplierName; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }
}
