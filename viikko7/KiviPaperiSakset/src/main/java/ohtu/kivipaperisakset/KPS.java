package ohtu.kivipaperisakset;

public abstract class KPS {
    private Tuomari tuomari = new Tuomari();
    protected String ekanSiirto;
    protected String tokanSiirto;
    protected Pelaava eka;
    protected Pelaava toka;

    public static KPS Kaksinpeli() {
        return new KPSNormaali(new Ihminen("Pelaaja1"), new Ihminen("Pelaaja2"));
    }

    public static KPS Yksinpeli() {
        return new KPSNormaali(new Ihminen("Pelaaja1"), new Tekoaly());
    }

    public static KPS PahaYksinpeli() {
        return new KPSPahaYksinpeli(new Ihminen("Pelaaja1"), new TekoalyParannettu(20));
    }

    protected KPS(Pelaava eka, Pelaava toka) {
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
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }
}
