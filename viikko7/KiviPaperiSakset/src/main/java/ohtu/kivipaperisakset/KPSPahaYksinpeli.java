package ohtu.kivipaperisakset;

public class KPSPahaYksinpeli extends KPS {
    public KPSPahaYksinpeli(Pelaava eka, Pelaava toka) {
        super(eka, toka);
    }

    @Override
    protected void jatka() {
        ekanSiirto = eka.annaSiirto();
        tokanSiirto = toka.annaSiirto();
        toka.muistaSiirto(ekanSiirto);
    }
}
