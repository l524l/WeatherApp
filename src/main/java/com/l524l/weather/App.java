package com.l524l.weather;

import com.l524l.exception.CityNotFoundException;
import com.l524l.exception.RequestExcepion;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.*;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage stage;
    private static Settings settings;

    public static Settings getSettings() {
        return settings;
    }

    public static Stage getStage() {
        return stage;
    }


    @Override
    public void start(Stage stage) throws IOException {
        JSONAdapter jsonAdapter = new JSONAdapter();
        settings = jsonAdapter.getSettings();
        this.stage = stage;

        if (settings.isCompact()){
            scene = new Scene(loadFXML("primaryLittle"), 410, 480);
        }else scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.setTitle("WeatherMap");

        if (settings.isDarkMode()){
            stage.getScene().getStylesheets().add(getClass().getResource("styles/darkTheme.css").toExternalForm());
        }else stage.getScene().getStylesheets().add(getClass().getResource("styles/lightTheme.css").toExternalForm());

        stage.getIcons().add(new Image(getClass().getResource("images/icon.png").toExternalForm()));
        stage.setResizable(false);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}