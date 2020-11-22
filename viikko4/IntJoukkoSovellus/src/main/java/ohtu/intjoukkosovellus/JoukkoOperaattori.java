package ohtu.intjoukkosovellus;

public class JoukkoOperaattori {
    private IntJoukko tulos;
    private int[] aTaulu;
    private int[] bTaulu;

    public JoukkoOperaattori(IntJoukko a, IntJoukko b) {
        tulos = new IntJoukko();
        aTaulu = a.toIntArray();
        bTaulu = b.toIntArray();
    }

    public IntJoukko yhdiste() {
        for (int i = 0; i < aTaulu.length; i++) {
            tulos.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            tulos.lisaa(bTaulu[i]);
        }
        return tulos;
    }

    public IntJoukko leikkaus() {
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    tulos.lisaa(bTaulu[j]);
                }
            }
        }
        return tulos;
    }

    public IntJoukko erotus() {
        for (int i = 0; i < aTaulu.length; i++) {
            tulos.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            tulos.poista(bTaulu[i]);
        }
        return tulos;
    }
}
