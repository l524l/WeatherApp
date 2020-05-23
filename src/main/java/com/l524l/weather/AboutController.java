package com.l524l.weather;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Modality;

public class AboutController {
    @FXML
    private Label title;
    public void exit(ActionEvent actionEvent) {
        title.getScene().getWindow().hide();
    }
}
