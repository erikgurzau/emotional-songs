module it.uninsubria.esclient {
    requires javafx.controls;
    requires javafx.fxml;

    exports it.uninsubria.emotionalsongs.controllers;
    opens it.uninsubria.emotionalsongs.controllers to javafx.fxml;
    exports it.uninsubria.emotionalsongs;
}