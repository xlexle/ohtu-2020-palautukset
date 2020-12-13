package ohtu.kivipaperisakset;

public class Muisti {
    private String[] alkiot;
    private int vapaaMuistiIndeksi = 0;

    public Muisti(int koko) {
        alkiot = new String[koko];
    }

    public boolean onTaynna() {
        return vapaaMuistiIndeksi == alkiot.length;
    }

    public boolean enintaanYksiMuistissa() {
        return vapaaMuistiIndeksi <= 1;
    }

    public void unohdaAikaisin() {
        for (int i = 1; i < alkiot.length; i++) {
            alkiot[i - 1] = alkiot[i];
        }

        vapaaMuistiIndeksi--;
    }

    public void muista(String siirto) {
        alkiot[vapaaMuistiIndeksi] = siirto;
        vapaaMuistiIndeksi++;
    }

    public int[] laskeLukumaarat() {
        String viimeisinSiirto = alkiot[vapaaMuistiIndeksi - 1];

        int k = 0;
        int p = 0;
        int s = 0;

        for (int i = 0; i < vapaaMuistiIndeksi - 1; i++) {
            if (viimeisinSiirto.equals(alkiot[i])) {
                String seuraava = alkiot[i + 1];

                if ("k".equals(seuraava)) {
                    k++;
                } else if ("p".equals(seuraava)) {
                    p++;
                } else {
                    s++;
                }
            }
        }

        return new int[] {k, p, s};
    }
}
