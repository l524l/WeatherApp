package com.l524l.weather;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;

public class MainController {

    @FXML
    private ImageView backgroundImage;
    @FXML
    private TextField chouseCity;
    @FXML
    private ComboBox<?> chouseCountry;
    @FXML
    private Button searchButton;
    @FXML
    private Label label_city;
    @FXML
    private Label label_date;
    @FXML
    private Label label_desck;
    @FXML
    private Label label_temp;
    @FXML
    private Label label_press;
    @FXML
    private Label label_vis;
    @FXML
    private Label label_windsp;
    @FXML
    private Label label_windto;
    @FXML
    private Label label_hr;
    @FXML
    private Label label_sunrise;
    @FXML
    private Label label_sunset;
    @FXML
    private CheckMenuItem darckMode;
    @FXML
    private RadioMenuItem classicTheme;
    @FXML
    private ToggleGroup type;
    @FXML
    private RadioMenuItem compactTheme;

    @FXML
    public void initialize() {

    }

    @FXML
    void changeTheme(ActionEvent event) {

    }

    @FXML
    void searchWeather(ActionEvent event) {

    }

    @FXML
    void setDarckMode(ActionEvent event) {

    }

    @FXML
    void showAbout(ActionEvent event) {

    }

}

