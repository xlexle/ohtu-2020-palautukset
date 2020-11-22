package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {
    private Pankki pankki = mock(Pankki.class);
    private Kauppa kauppa;
    private int viitenro1 = 42;
    private int viitenro2 = 43;
    private int viitenro3 = 44;
    private String nimi = "pekka";
    private String tilinumero = "12345";
    private Tuote maito = new Tuote(1, "maito", 5);
    private Tuote leipa = new Tuote(2, "leipä", 2);
    private Tuote kaviaari = new Tuote(3, "kaviaari", 99);
    private String kaupanTili = "33333-44455"; // kovakoodattu Kauppa-luokassa

    @Before
    public void setUpKauppa() {
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi())
            .thenReturn(viitenro1)
            .thenReturn(viitenro2)
            .thenReturn(viitenro3);

        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(maito.getId())).thenReturn(10);
        when(varasto.saldo(leipa.getId())).thenReturn(10);
        when(varasto.saldo(kaviaari.getId())).thenReturn(0);
        when(varasto.haeTuote(maito.getId())).thenReturn(maito);
        when(varasto.haeTuote(leipa.getId())).thenReturn(leipa);
        when(varasto.haeTuote(kaviaari.getId())).thenReturn(kaviaari);

        kauppa = new Kauppa(varasto, pankki, viite);
        kauppa.aloitaAsiointi();
    }

    @Test
    public void yhdenTuotteenOstoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikein() {
        kauppa.lisaaKoriin(maito.getId());
        kauppa.tilimaksu(nimi, tilinumero);
        verifioiTilisiirtoSummalla(maito.getHinta());
    }

    @Test
    public void kahdenEriTuotteenOstoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikein() {
        kauppa.lisaaKoriin(maito.getId());
        kauppa.lisaaKoriin(leipa.getId());
        kauppa.tilimaksu(nimi, tilinumero);
        verifioiTilisiirtoSummalla(maito.getHinta() + leipa.getHinta());
    }

    @Test
    public void kahdenSamanTuotteenOstoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikein() {
        kauppa.lisaaKoriin(maito.getId());
        kauppa.lisaaKoriin(maito.getId());
        kauppa.tilimaksu(nimi, tilinumero);
        verifioiTilisiirtoSummalla(2 * maito.getHinta());
    }

    @Test
    public void toisenTuotteenOllessaLoppuKahdenTuotteenOstoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikein() {
        kauppa.lisaaKoriin(maito.getId());
        kauppa.lisaaKoriin(kaviaari.getId());
        kauppa.tilimaksu(nimi, tilinumero);
        verifioiTilisiirtoSummalla(maito.getHinta());
    }

    @Test
    public void tuotteenPoistoKoristaVahentaaTuotteenHinnanOstoksesta() {
        kauppa.lisaaKoriin(maito.getId());
        kauppa.lisaaKoriin(leipa.getId());
        kauppa.poistaKorista(maito.getId());

        kauppa.tilimaksu(nimi, tilinumero);
        verifioiTilisiirtoSummalla(leipa.getHinta());
    }

    @Test
    public void aloitaAsiointiNollaaEdellisenOstoksenTiedot() {
        kauppa.lisaaKoriin(maito.getId());
        kauppa.tilimaksu(nimi, tilinumero);
        verifioiTilisiirtoSummalla(maito.getHinta());

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(leipa.getId());
        kauppa.tilimaksu(nimi, tilinumero);
        verifioiTilisiirtoSummalla(leipa.getHinta());
    }

    @Test
    public void kauppaPyytaaUudelleMaksutapahtumalleUudenViitteen() {
        kauppa.lisaaKoriin(maito.getId());
        kauppa.tilimaksu(nimi, tilinumero);
        verifioiTilisiirtoViitenumerolla(viitenro1);
        kauppa.tilimaksu(nimi, tilinumero);
        verifioiTilisiirtoViitenumerolla(viitenro2);
        kauppa.tilimaksu(nimi, tilinumero);
        verifioiTilisiirtoViitenumerolla(viitenro3);
    }

    private void verifioiTilisiirtoViitenumerolla(int viitenro) {
        verify(pankki).tilisiirto(
            eq(nimi),
            eq(viitenro),
            eq(tilinumero),
            eq(kaupanTili),
            anyInt()
        );
    }

    private void verifioiTilisiirtoSummalla(int summa) {
        verify(pankki).tilisiirto(
            eq(nimi),
            anyInt(),
            eq(tilinumero),
            eq(kaupanTili),
            eq(summa)
        );
    }
}
