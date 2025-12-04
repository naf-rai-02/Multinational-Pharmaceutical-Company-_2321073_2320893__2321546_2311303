module com.example.Shoyeb_2311303 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.Shoyeb_2311303 to javafx.fxml;
    exports com.example.Shoyeb_2311303;
}