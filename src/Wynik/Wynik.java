package Wynik;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Wynik extends Application {

    private int W, K;
    private long czas;

    private TextField textField = new TextField("Your XYZ");
    private Button ButtonSave = new Button("Save");
    private BorderPane borderPane = new BorderPane();

    public Wynik(int W, int K, long czas) {

        this.W = W;
        this.K = K;
        this.czas = czas;
        Stage primaryStage = new Stage();
        borderPane.setCenter(textField);
        borderPane.setBottom(ButtonSave);
        Scene scene1 = new Scene(borderPane);
        primaryStage.setScene(scene1);
        ButtonSave.setOnAction(event ->
                zapisWyniku());

        primaryStage.show();
    }

    public void start(Stage primaryStage) {}

    public void zapisWyniku() {

        System.out.println("Zapisuje twój wynik");

        Writer output = null;
        try {
            output = new BufferedWriter(new FileWriter("Wyniki.txt", true));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            String napis = textField.getText() + " Time: " + czas + " grid " + W + "x" + K;
            output.append(napis + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Wynik.this.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
