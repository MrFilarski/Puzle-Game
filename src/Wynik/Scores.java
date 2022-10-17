package Wynik;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Scores extends Application {

    public Scores(Stage stage) throws FileNotFoundException {

        BorderPane borderPane = new BorderPane();
        TextArea textArea = new TextArea();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(textArea);
        borderPane.setCenter(textArea);
        borderPane.setRight(scrollPane);

        File f = new File("Wyniki.txt");
        Scanner scanner = new Scanner(f);
        while (scanner.hasNext()) {
            textArea.appendText(scanner.nextLine() + "\n");
        }
        scanner.close();

        textArea.setDisable(true);
        Scene scene1 = new Scene(borderPane);
        stage.setScene(scene1);
        stage.show();
    }

    @Override
    public void start(Stage primaryStage) {
    }
}
