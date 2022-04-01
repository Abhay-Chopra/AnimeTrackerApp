module animelist.app {
    requires javafx.controls;
    requires javafx.fxml;

    opens animelist.app to javafx.fxml;
    exports animelist.app;
}