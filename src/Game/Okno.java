package Game;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Date;

public class Okno extends Application {

    private int wiersz;
    private int kolumna;
    private MyButton[][] butons;
    private Plansza plansza;

    private Date czas1;
    private long koncowyCzas;
    private Thread miernikCzasu;
    private Text showTime = new Text();

    public Okno(int wiersz, int kolumna) {

        this.wiersz = wiersz;
        this.kolumna = kolumna;
        plansza = new Plansza(wiersz, kolumna);
        butons = new MyButton[wiersz][kolumna];
    }

    @Override
    public void start(Stage primaryStage) {

        System.out.println("start");
        GridPane layout = new GridPane();
        int widith = 800;
        int height = 800;

        for (int i = 0; i < plansza.getW(); i++) {
            for (int j = 0; j < plansza.getK(); j++) {

                MyButton button = new MyButton(i, j, plansza, widith / kolumna, height / wiersz, butons, this);
                butons[i][j] = button;
                button.setMinSize(widith / kolumna, height / wiersz);
                BufferedImage bufferedImage = new BufferedImage(widith / kolumna, height / wiersz, BufferedImage.TYPE_INT_RGB);
                Graphics grafika = bufferedImage.getGraphics();
                grafika.setColor(Color.WHITE);
                grafika.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
                grafika.drawImage(plansza.getKwadracik(i, j).getBufferedImage(), 0, 0, widith / kolumna, height / wiersz, null);
                final Image maly = SwingFXUtils.toFXImage(bufferedImage, null);
                ImageView gr = new ImageView(maly);
                button.setGraphic(gr);

                layout.add(button, j, i);
                layout.setGridLinesVisible(false);

            }
        }

        BorderPane layoutmain = new BorderPane();
        layoutmain.setCenter(layout);
        showTime.setFont(Font.font("Verdana", 30));
        layoutmain.setTop(showTime);
        Scene scene1 = new Scene(layoutmain);
        primaryStage.setScene(scene1);
        primaryStage.show();
        mierzczas();
    }

    private void mierzczas() {
        czas1 = new Date();
        miernikCzasu = new Thread(() -> {

            while (!plansza.check()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

                Date aktualnyCzas = new Date();
                koncowyCzas = aktualnyCzas.getTime() - czas1.getTime();
                long tworzenieczasu = koncowyCzas;
                long sekundy = tworzenieczasu / 1000;
                long minuty = sekundy / 60;
                long pozostaleSec = sekundy - minuty * 60;
                showTime.setText(minuty + ":" + pozostaleSec);
            }
        });
        miernikCzasu.start();
    }

    public long getKoncowyCzas() {
        return koncowyCzas;
    }
}
