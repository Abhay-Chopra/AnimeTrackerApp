module anime.app.cpsc233projgui {
    requires javafx.controls;
    requires javafx.fxml;


    opens anime.app.cpsc233projgui to javafx.fxml;
    exports anime.app.cpsc233projgui;
}