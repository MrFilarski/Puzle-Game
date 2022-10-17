package Game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Plansza {

    private int W;
    private int K;
    private Kwadracik tablica[][];
    private KwadracikPusty pusty;

    public Plansza(int W, int K) {

        this.W = W;
        this.K = K;
        tablica = new Kwadracik[W][K];

        for (int i = 0; i < W; i++) {
            for (int j = 0; j < K; j++) {
                tablica[i][j] = new KwadracikZdjecie(i, j, this);
            }
        }
        tablica[0][0] = new KwadracikPusty(0, 0, this);
        pusty = (KwadracikPusty) tablica[0][0];
        cut();
        potasuj();
    }

    public synchronized boolean check() {

        for (int i = 0; i < W; i++) {
            for (int j = 0; j < K; j++) {

                if (!tablica[i][j].CzyNaSwoimMiejscu()) {
                    return false;
                }
            }

        }
        System.out.println("zdjecie ulozone");
        return true;
    }

    public void drukuj() {

        for (int i = 0; i < W; i++) {
            for (int j = 0; j < K; j++) {

                System.out.print(tablica[i][j] + "\t\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void cut() {

        String[] nazwyZdjec = new String[16];

        nazwyZdjec[0] = "alpha_amalfi_coast.jpg";
        nazwyZdjec[1] = "alpha_halongbay.jpg";
        nazwyZdjec[2] = "alpha_ogasawaraislands_shells.jpg";
        nazwyZdjec[3] = "alpha_sanaa.jpg";
        nazwyZdjec[4] = "alpha_stmoritz_mountain.jpg";
        nazwyZdjec[5] = "alpha_yosemite_tunnelview.jpg";
        nazwyZdjec[6] = "beach_huts.jpg";
        nazwyZdjec[7] = "colorful_embroidery.jpg";
        nazwyZdjec[8] = "colorful_houses.jpg";
        nazwyZdjec[9] = "fairy_lights.jpg";
        nazwyZdjec[10] = "ice_wpo.jpg";
        nazwyZdjec[11] = "lighthouse_wpo.jpg";
        nazwyZdjec[12] = "ocean_wpo.jpg";
        nazwyZdjec[13] = "rocks_wpo.jpg";
        nazwyZdjec[14] = "round_top_wpo.jpg";

        int numerekZdjecia = (int) (Math.random() * nazwyZdjec.length-1);
        System.out.println("Wylosowane zdjecie " + numerekZdjecia);
        File file = new File(nazwyZdjec[numerekZdjecia]);

        int BigX;
        int BigY;

        int sqrX;
        int sqrY;

        try {

            BufferedImage bigImage = ImageIO.read(file);

            BigX = bigImage.getWidth();
            BigY = bigImage.getHeight();

            sqrX = BigX / K;
            sqrY = BigY / W;

            for (int i = 0; i < W; i++) {
                for (int j = 0; j < K; j++) {

                    int lgx = j * sqrX;
                    int lgy = i * sqrY;

                    BufferedImage smallImage = bigImage.getSubimage(lgx, lgy, sqrX, sqrY);
                    tablica[i][j].setBufferedImage(smallImage);
                    File dir = new File("outputs");
                    File outputFile = new File(dir, file.getName() + " " + i + "  " + j + ".jpg");
                    ImageIO.write(smallImage, "jpg", outputFile);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getW() {
        return W;
    }

    public int getK() {
        return K;
    }

    public Kwadracik getKwadracik(int i, int j) {
        return tablica[i][j];
    }

    public KwadracikPusty getPusty() {
        return pusty;
    }

    public void ruch(int w, int k) {

        if (check()) {
            return;
        }
        int dystans = ((w - pusty.getW()) * (w - pusty.getW()) + (k - pusty.getK()) * (k - pusty.getK()));
        if (dystans == 1) {

            if ((k - pusty.getK()) == 1) {
                kliknietoPoPrawej();

            } else if ((k - pusty.getK()) == -1) {
                kliknietoPoLewej();

            } else if ((w - pusty.getW()) == 1) {
                kliknietoNaDole();

            } else if ((w - pusty.getW()) == -1) {
                kliknietoNaGorze();
            }
        }
    }

    private void kliknietoNaGorze() {

        int w = pusty.getW() - 1;
        int k = pusty.getK();

        if (pusty.getW() >= 1) {
            pusty.wGore();
            tablica[w][k].wdol();

            Kwadracik tmp = tablica[w][k];
            tablica[w][k] = tablica[w + 1][k];
            tablica[w + 1][k] = tmp;
        }
        drukuj();
    }

    private void kliknietoPoLewej() {

        int w = pusty.getW();
        int k = pusty.getK() - 1;

        if (pusty.getK() >= 1) {
            tablica[w][k + 1].wLewo();
            tablica[w][k].wPrawo();

            Kwadracik tmp = tablica[w][k];
            tablica[w][k] = tablica[w][k + 1];
            tablica[w][k + 1] = tmp;
        }
        drukuj();
    }

    private void kliknietoPoPrawej() {

        int w = pusty.getW();
        int k = pusty.getK() + 1;

        if (pusty.getK() < (K - 1)) {
            tablica[w][k - 1].wPrawo();
            tablica[w][k].wLewo();

            Kwadracik tmp = tablica[w][k];
            tablica[w][k] = tablica[w][k - 1];
            tablica[w][k - 1] = tmp;
        }
        drukuj();
    }

    private void kliknietoNaDole() {

        int w = pusty.getW() + 1;
        int k = pusty.getK();

        if (pusty.getW() < W - 1) {
            pusty.wdol();
            tablica[w][k].wGore();

            Kwadracik tmp = tablica[w][k];
            tablica[w][k] = tablica[w - 1][k];
            tablica[w - 1][k] = tmp;
        }
        drukuj();
    }

    public void potasuj() {

        for (int i = 0; i < 1000; i++) {

            int w1 = (int) (Math.random() * W);
            int k1 = (int) (Math.random() * K);
            int w2 = (int) (Math.random() * W);
            int k2 = (int) (Math.random() * K);

            Kwadracik tmp = tablica[w1][k1];
            tablica[w1][k1] = tablica[w2][k2];
            tablica[w2][k2] = tmp;

            tablica[w1][k1].zmienPolozenie(w1, k1);
            tablica[w2][k2].zmienPolozenie(w2, k2);
        }
    }
}