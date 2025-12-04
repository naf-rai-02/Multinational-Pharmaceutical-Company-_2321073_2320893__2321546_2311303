package com.example.Shoyeb_2311303;


class Shipment {
    private final javafx.beans.property.StringProperty shipmentId;
    private final javafx.beans.property.StringProperty status;
    private final javafx.beans.property.StringProperty location;
    private final javafx.beans.property.StringProperty eta;

    public Shipment(String shipmentId, String status, String location, String eta) {
        this.shipmentId = new javafx.beans.property.SimpleStringProperty(shipmentId);
        this.status = new javafx.beans.property.SimpleStringProperty(status);
        this.location = new javafx.beans.property.SimpleStringProperty(location);
        this.eta = new javafx.beans.property.SimpleStringProperty(eta);
    }

    public String getShipmentId() { return shipmentId.get(); }
    public javafx.beans.property.StringProperty shipmentIdProperty() { return shipmentId; }

    public String getStatus() { return status.get(); }
    public javafx.beans.property.StringProperty statusProperty() { return status; }

    public String getLocation() { return location.get(); }
    public javafx.beans.property.StringProperty locationProperty() { return location; }

    public String getEta() { return eta.get(); }
    public javafx.beans.property.StringProperty etaProperty() { return eta; }
}
