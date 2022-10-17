package Game;

import java.awt.image.BufferedImage;

public abstract class Kwadracik {

    final protected int docelowyWiersz;
    final protected int docelowaKolumna;
    protected int aktualnyWiersz;
    protected int aktualnaKolumna;
    protected Plansza plansza;
    private BufferedImage bufferedImage;

    public Kwadracik(int docelowyWiersz, int docelowaKolumna, Plansza plansza) {

        this.docelowyWiersz = docelowyWiersz;
        this.docelowaKolumna = docelowaKolumna;
        this.aktualnyWiersz = docelowyWiersz;
        this.aktualnaKolumna = docelowaKolumna;
        this.plansza = plansza;

    }

    public void wGore() {
        if (aktualnyWiersz > 0)
            aktualnyWiersz--;
    }

    public void wdol() {
        if (aktualnyWiersz < (plansza.getW() - 1))
            aktualnyWiersz++;

    }

    public void wLewo() {

        System.out.println("kwadrack idzie  w lewo ");
        if (aktualnaKolumna > 0) {
            aktualnaKolumna--;
            System.out.println("zmiana  kolumny w lewo" + this);
        }
    }

    public void wPrawo() {
        System.out.println("kwadrack idzie  w prawo ");
        if (aktualnaKolumna < (plansza.getK() - 1)) {
            aktualnaKolumna++;
            System.out.println("zmiana  kolumny w prawo" + this);
        }
    }


    public boolean CzyNaSwoimMiejscu() {
        return (aktualnyWiersz == docelowyWiersz) && (aktualnaKolumna == docelowaKolumna);
    }

    public int getW() {
        return aktualnyWiersz;
    }

    public int getK() {
        return aktualnaKolumna;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public String toString() {
        return "Kwadracik:" + getW() + " " + getK();
    }

    public void zmienPolozenie(int nowyWiersz, int nowaKolumna) {

        aktualnyWiersz = nowyWiersz;
        aktualnaKolumna = nowaKolumna;
    }

    public void koniec() {}
}
