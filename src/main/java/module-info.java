module anime.app.cpsc233projgui {
    requires javafx.controls;
    requires javafx.fxml;

    opens animelist to javafx.fxml;
    exports animelist.app;
    opens animelist.app to javafx.fxml;
}