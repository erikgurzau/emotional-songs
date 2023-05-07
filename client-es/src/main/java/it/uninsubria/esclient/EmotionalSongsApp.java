package it.uninsubria.esclient;

import it.uninsubria.esclient.interfaces.Configuration;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EmotionalSongsApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Configuration.PATH_VIEW_MAIN));
        Scene scene = new Scene(fxmlLoader.load(), Configuration.WIDTH_STAGE, Configuration.HEIGHT_STAGE);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}