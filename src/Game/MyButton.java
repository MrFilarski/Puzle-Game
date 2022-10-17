package Game;

import Wynik.Wynik;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MyButton extends Button {
    final private int w;
    final private int k;
    private final Okno okno;
    private int widthObrazka;
    private int hightObrazka;
    private Plansza plansza;
    private MyButton[][] butons;

    public MyButton(int w, int k, Plansza plansza, int widthObrazka, int hightObrazka, MyButton[][] butons, Okno okno) {
        this.w = w;
        this.k = k;
        this.okno = okno;
        this.widthObrazka = widthObrazka;
        this.hightObrazka = hightObrazka;
        this.plansza = plansza;
        this.butons = butons;
        this.setOnAction(event -> {
            int pw = plansza.getPusty().getW();
            int pk = plansza.getPusty().getK();

            plansza.ruch(w, k);

            if (plansza.check()) {
                System.out.println("Koniec gry");
                plansza.getPusty().koniec();
                Wynik wyn = new Wynik(plansza.getW(), plansza.getK(), okno.getKoncowyCzas());
            }
            MyButton.this.setGraphic(createView(w, k));
            System.out.println(plansza.getPusty());
            butons[pw][pk].setGraphic(createView(pw, pk));
        });
    }

    ImageView createView(int zmw, int zmk) {

        System.out.println("zmw " + zmw + " " + "zmk " + zmk);
        BufferedImage bufferedImage = new BufferedImage(widthObrazka, hightObrazka, BufferedImage.TYPE_INT_RGB);
        Graphics grafika = bufferedImage.getGraphics();
        grafika.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
        grafika.drawImage(plansza.getKwadracik(zmw, zmk).getBufferedImage(), 0, 0, widthObrazka, hightObrazka, null);
        final Image maly = SwingFXUtils.toFXImage(bufferedImage, null);
        ImageView gr = new ImageView(maly);
        return gr;
        }
    }


