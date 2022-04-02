package animelist.app;

import animelist.util.Library;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainController {
    private Library animeList;

    /**
     * When the controller is started, boot with these params
     */
    @FXML
    public void initialize() {
        animeList = new Library();
    }


}