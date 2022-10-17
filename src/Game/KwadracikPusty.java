package Game;

import java.awt.image.BufferedImage;

public class KwadracikPusty extends Kwadracik {

    private boolean koniecgry = false;

    public KwadracikPusty(int docelowyWiersz, int docelowaKolumna, Plansza plansza) {

        super(docelowyWiersz, docelowaKolumna, plansza);

    }

    public String toString() {

        return "pusty[" + docelowyWiersz + "|" + docelowaKolumna + "]  [" + aktualnyWiersz + "|" + aktualnaKolumna + "]";

    }

    public BufferedImage getBufferedImage() {

        if (!koniecgry) {
            return null;
        } else return super.getBufferedImage();

    }

    public void koniec() {
        koniecgry = true;
    }

}
