module com.l524l.weather {

    opens com.l524l.weather to javafx.fxml;
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;

    requires java.base;

    exports com.l524l.weather;
}