module it.uninsubria.esclient {
    requires javafx.controls;
    requires javafx.fxml;

    exports it.uninsubria.esclient.controllers;
    opens it.uninsubria.esclient.controllers to javafx.fxml;
    exports it.uninsubria.esclient;
}