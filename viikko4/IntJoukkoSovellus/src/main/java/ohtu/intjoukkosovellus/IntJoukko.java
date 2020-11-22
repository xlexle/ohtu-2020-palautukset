
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] ljono;      // Joukon luvut säilytetään taulukon alkupäässä.
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla.

    public IntJoukko() {
        this(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti väärin");//heitin vaan jotain :D
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("kapasiteetti2");//heitin vaan jotain :D
        }
        ljono = new int[kapasiteetti];
        for (int i = 0; i < ljono.length; i++) {
            ljono[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public boolean lisaa(int luku) {
        if (kuuluu(luku)) {
            return false;
        }

        ljono[alkioidenLkm] = luku;
        alkioidenLkm++;

        if (alkioidenLkm == ljono.length) {
            kasvataKapasiteettia();
        }
        return true;
    }

    private void kasvataKapasiteettia() {
        int[] taulukkoOld = ljono;
        ljono = new int[ljono.length + kasvatuskoko];
        for (int i = 0; i < taulukkoOld.length; i++) {
            ljono[i] = taulukkoOld[i];
        }
    }

    private int indeksi(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                return i;
            }
        }
        return -1;
    }

    public boolean kuuluu(int luku) {
        return indeksi(luku) != -1;
    }

    public boolean poista(int luku) {
        int luvunIndeksi = indeksi(luku);
        if (luvunIndeksi == -1) {
            return false;
        }

        int lukuNyt;
        for (int j = luvunIndeksi; j < alkioidenLkm - 1; j++) {
            lukuNyt = ljono[j];
            ljono[j] = ljono[j + 1];
            ljono[j + 1] = lukuNyt;
        }
        alkioidenLkm--;
        return true;
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        String alkiotMerkkijono = "";
        if (alkioidenLkm > 0) {
            for (int i = 0; i < alkioidenLkm - 1; i++) {
                alkiotMerkkijono += ljono[i] + ", ";
            }
            alkiotMerkkijono += ljono[alkioidenLkm - 1];
        }
        return "{" + alkiotMerkkijono + "}";
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = ljono[i];
        }
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        return new JoukkoOperaattori(a, b).yhdiste();
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        return new JoukkoOperaattori(a, b).leikkaus();
    }

    public static IntJoukko erotus (IntJoukko a, IntJoukko b) {
        return new JoukkoOperaattori(a, b).erotus();
    }
}
