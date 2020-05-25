package com.l524l.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.l524l.exception.CityNotFoundException;
import com.l524l.exception.RequestExcepion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.List;
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

    private JSONAdapter jsonAdapter = new JSONAdapter();
    private boolean dMod = false;
    private Settings settings;

    @FXML
    public void initialize() throws JsonProcessingException, FileNotFoundException {
        settings = jsonAdapter.getSettings();

        if (settings.isDarkMode()) {
            darckMode.setSelected(true);
            backgroundImage.setImage(new Image(getClass().getResource("images/background/night.png").toExternalForm()));
            dMod = true;
        }

        ObservableList s = FXCollections.observableArrayList(settings.getCities());
        chouseCountry.setItems(s);
    }

    @FXML
    void changeTheme() throws IOException {
        JsonNode node = null;
        ObjectMapper mapper = new ObjectMapper();
        node = mapper.readValue(new File("settings.properties"), JsonNode.class);
        if(compactTheme.isSelected()){
            App.setRoot("primaryLittle");
            App.getStage().setWidth(410);
            ((ObjectNode) node).put("compact",true);


        }else {
            App.setRoot("primary");
            App.getStage().setWidth(640);
            ((ObjectNode) node).put("compact",false);
        }
        mapper.writeValue(new File("settings.properties"), node);
    }

    @FXML
    void searchWeather() {
        Weather weather;
        if (!chouseCountry.getSelectionModel().isEmpty() & !chouseCity.getText().trim().isEmpty()){
            try {
                String s = chouseCountry.getSelectionModel().getSelectedItem().toString();
                weather = jsonAdapter.getWeather(chouseCity.getText().trim(),s.substring(0,2));
                label_city.setText("Город: " + chouseCity.getText().trim());
                label_date.setText("Погода на: " + weather.getDatetime());
                label_desck.setText(weather.getDescription());
                label_temp.setText(String.format("Температура: %s °С (ощущается как %s °C)",weather.getTemp(),weather.getApp_temp()));
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
    void setDarckMode() throws IOException {
        JsonNode node = null;
        ObjectMapper mapper = new ObjectMapper();
        node = mapper.readValue(new File("settings.properties"), JsonNode.class);
        backgroundImage.getScene().getStylesheets().clear();
        if (darckMode.isSelected()){
            backgroundImage.getScene().getStylesheets().add(getClass().getResource("styles/darkTheme.css").toExternalForm());
            backgroundImage.setImage(new Image(getClass().getResource("images/background/night.png").toExternalForm()));
            dMod = true;
            ((ObjectNode) node).put("dark_mode",true);

        }else {
            backgroundImage.getScene().getStylesheets().add(getClass().getResource("styles/lightTheme.css").toExternalForm());
            backgroundImage.setImage(new Image(getClass().getResource("images/background/day.png").toExternalForm()));
            dMod = false;
            ((ObjectNode) node).put("dark_mode",false);
        }
        mapper.writeValue(new File("settings.properties"), node);
    }

    @FXML
    void showAbout() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("about.fxml"));
        stage.setScene(new Scene(loader.load(),384,427));
        if (dMod)
            stage.getScene().getStylesheets().add(getClass().getResource("styles/aboutStyleDark.css").toExternalForm());
        else
            stage.getScene().getStylesheets().add(getClass().getResource("styles/aboutStyleLight.css").toExternalForm());

        stage.getIcons().add(new Image(getClass().getResource("images/icon.png").toExternalForm()));
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

}

