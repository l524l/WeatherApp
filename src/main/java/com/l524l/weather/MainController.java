package com.l524l.weather;

import com.l524l.exception.CityNotFoundException;
import com.l524l.exception.RequestExcepion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.*;
import java.util.Properties;

public class MainController {

    @FXML
    private ImageView weatherIcon;
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
    private VBox dataBord;
    private Properties properties = new Properties();
    private JSONAdapter jsonAdapter = new JSONAdapter();

    @FXML
    public void initialize() {
        try {
            properties.load(getClass().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObservableList s = FXCollections.observableArrayList(
                "RU Россия",
                "UA Украина",
                "UZ Республика Узбекистан",
                "TM Туркменистан",
                "TJ Республика Таджикистан",
                "MD Республика Молдова",
                "KG Кыргызская Республика",
                "KZ Республика Казахстан",
                "BY Республика Беларусь",
                "AM Республика Армения",
                "AZ Азербайджанская Республика");
        chouseCountry.setItems(s);
    }

    @FXML
    void changeTheme(ActionEvent event) {

    }

    @FXML
    void searchWeather(ActionEvent event) {
        Weather weather;
        if (!chouseCountry.getSelectionModel().isEmpty() & !chouseCity.getText().trim().isEmpty()){
            try {
                String s = chouseCountry.getSelectionModel().getSelectedItem().toString();
                weather = jsonAdapter.getWeather(chouseCity.getText().trim(),s.substring(0,2));
                label_city.setText("Город: " + chouseCity.getText().trim());
                label_date.setText("Погода на: " + weather.getDatetime());
                label_desck.setText(weather.getDescription());
                label_temp.setText(String.format("Температура %s °С (ощущается как %s °C)",weather.getTemp(),weather.getApp_temp()));
                label_press.setText(String.format("Атмосферное давление: %s mb",weather.getPress()));
                label_vis.setText(String.format("Видимость: %s км",weather.getVisibility()));
                label_windsp.setText(String.format("Скорость ветра: %s M/C",weather.getWind_speed()));
                label_windto.setText(String.format("Направление ветра: %s",weather.getWind_cdir_full()));
                label_hr.setText(String.format("Относительная влажность: %s%%",weather.getRh()));
                label_sunrise.setText(String.format("Восход: %s",weather.getSunrise()));
                label_sunset.setText(String.format("Закат: %s",weather.getSunset()));
                weatherIcon.setImage(new Image(getClass().getResource("images/weatherIcon/" + weather.getIcon() +".png").toExternalForm()));


                dataBord.setDisable(false);

            } catch (RequestExcepion requestExcepion) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Внимание");
                alert.setHeaderText("Ошибка подключения!");
                alert.setContentText("Проверте подключен ли пк к интернету");
                alert.showAndWait();
            } catch (CityNotFoundException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Внимание!");
                alert.setHeaderText("Город не найден!");
                alert.setContentText("Проверте правильность введённой информации");
                alert.showAndWait();
            }finally {

            }
        }
    }

    @FXML
    void setDarckMode(ActionEvent event) {
        if (darckMode.isSelected()){
            backgroundImage.getScene().getStylesheets().clear();
            backgroundImage.getScene().getStylesheets().add(getClass().getResource("styles/darckTheme.css").toExternalForm());
            backgroundImage.setImage(new Image(getClass().getResource("images/background/night.png").toExternalForm()));
            properties.put("darkMode","true");
        }else {
            backgroundImage.getScene().getStylesheets().clear();

            backgroundImage.getScene().getStylesheets().add(getClass().getResource("styles/lightTheme.css").toExternalForm());

            backgroundImage.setImage(new Image(getClass().getResource("images/background/day.png").toExternalForm()));
            properties.put("darkMode","false");
        }

    }

    @FXML
    void showAbout(ActionEvent event) {

    }

}

