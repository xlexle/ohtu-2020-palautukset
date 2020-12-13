
package ohtu.kivipaperisakset;

// "Muistava tekoäly"

public class TekoalyParannettu implements Pelaava {
    private Muisti muisti;

    public TekoalyParannettu(int muistinKoko) {
        this.muisti = new Muisti(muistinKoko);
    }

    @Override
    public void muistaSiirto(String siirto) {
        // jos muisti täyttyy, unohdetaan viimeinen alkio
        if (muisti.onTaynna()) {
            muisti.unohdaAikaisin();
        }

        muisti.muista(siirto);
    }

    @Override
    public String annaSiirto() {
        String siirto = laskeSiirto();
        System.out.println("Tietokone valitsi: " + siirto);
        return siirto;
    }

    private String laskeSiirto() {
        if (muisti.enintaanYksiMuistissa()) {
            return "k";
        }

        int[] lukumaarat = muisti.laskeLukumaarat();
        int k = lukumaarat[0];
        int p = lukumaarat[1];
        int s = lukumaarat[2];

        // Tehdään siirron valinta esimerkiksi seuraavasti;
        // - jos kiviä eniten, annetaan aina paperi
        // - jos papereita eniten, annetaan aina sakset
        // muulloin annetaan aina kivi
        if (k > p && k > s) {
            return "p";
        } else if (p > k && p > s) {
            return "s";
        } else {
            return "k";
        }

        // Tehokkaampiakin tapoja löytyy, mutta niistä lisää
        // Johdatus Tekoälyyn kurssilla!
    }
}
