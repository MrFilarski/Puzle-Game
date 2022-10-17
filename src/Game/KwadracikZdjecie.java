package Game;

public class KwadracikZdjecie extends Kwadracik {

    public KwadracikZdjecie(int docelowyWiersz, int docelowaKolumna, Plansza plansza) {
        super(docelowyWiersz, docelowaKolumna, plansza);
    }

    public String toString() {
        return "KwadracikZdjecie[" + docelowyWiersz + "|" + docelowaKolumna + "]  [" + aktualnyWiersz + "|" + aktualnaKolumna + "]";
    }
}
