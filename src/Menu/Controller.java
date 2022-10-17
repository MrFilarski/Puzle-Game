package Menu;

import Grid.Grid;
import Wynik.Scores;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Controller {

    public void playAction() {
        System.out.println("Cliknieo play");
        Parent root;
        try {
            root = new FXMLLoader().load(Grid.class.getResourceAsStream("Grid.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert root != null;
        Scene scene = new Scene(root, 500, 500);
        Stage stage = new Stage();

        stage.setMinWidth(500);
        stage.setMinHeight(500);
        stage.setTitle("Grid");
        stage.setScene(scene);
        stage.show();
    }

    public void highScoresAction() {
        System.out.println("kliknieto High Scores");
        Stage stage = new Stage();
        try {
            new Scores(stage);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
