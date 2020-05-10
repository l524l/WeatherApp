module com.l524l.weather {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.l524l.weather to javafx.fxml;
    exports com.l524l.weather;
}