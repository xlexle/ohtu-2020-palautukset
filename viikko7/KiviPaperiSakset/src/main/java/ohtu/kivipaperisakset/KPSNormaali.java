package ohtu.kivipaperisakset;

public class KPSNormaali extends KPS {
    public KPSNormaali(Pelaava eka, Pelaava toka) {
        super(eka, toka);
    }

    @Override
    protected void jatka() {
        ekanSiirto = eka.annaSiirto();
        tokanSiirto = toka.annaSiirto();
    }
}
