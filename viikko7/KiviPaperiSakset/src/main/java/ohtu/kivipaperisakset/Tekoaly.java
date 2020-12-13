package ohtu.kivipaperisakset;

public class Tekoaly implements Pelaava {
    int siirtoLkm;

    public Tekoaly() {
        siirtoLkm = 0;
    }

    @Override
    public String annaSiirto() {
        siirtoLkm++;
        siirtoLkm = siirtoLkm % 3;

        String siirto = "s";
        if (siirtoLkm == 0) {
            siirto = "k";
        } else if (siirtoLkm == 1) {
            siirto = "p";
        }

        System.out.println("Tietokone valitsi: " + siirto);
        return siirto;
    }
}
