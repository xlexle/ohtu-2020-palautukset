package ohtu.kivipaperisakset;

public abstract class KPS {
    private Tuomari tuomari = new Tuomari();
    protected String ekanSiirto;
    protected String tokanSiirto;
    protected Pelaava eka;
    protected Pelaava toka;

    public KPS(Pelaava eka, Pelaava toka) {
        this.eka = eka;
        this.toka = toka;
    }

    public void pelaa() {
        ekanSiirto = eka.annaSiirto();
        tokanSiirto = toka.annaSiirto();

        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            System.out.println(tuomari);
            System.out.println();
            jatka();
        }

        System.out.println("\nKiitos!");
        System.out.println(tuomari);
    }

    abstract protected void jatka();

    private static boolean onkoOkSiirto(String siirto) {
        return siirto.isBlank() || ("k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto));
    }
}
