package Grid;

import Game.Okno;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class GridController implements Initializable {

    @FXML
    private ComboBox<Integer> boxLeft;
    @FXML
    private ComboBox<Integer> boxRight;
    @FXML
    Button ok;

    private ObservableList<Integer> gridSize = FXCollections.observableArrayList(2, 4, 6);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        boxLeft.setItems(gridSize);
        boxRight.setItems(gridSize);
    }

    public void goAction() {

        System.out.println("Cliknieto go");
        int wiersz = boxLeft.getValue();
        int kolumna = boxRight.getValue();
        Stage stage = new Stage();
        Okno okno = new Okno(wiersz, kolumna);
        okno.start(stage);
    }
}
