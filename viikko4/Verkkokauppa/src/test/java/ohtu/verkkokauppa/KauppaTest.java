package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {
    private Pankki pankki = mock(Pankki.class);
    private Kauppa kauppa;
    private int viitenro = 42;
    private String nimi = "pekka";
    private String tilinumero = "12345";
    private Tuote maito = new Tuote(1, "maito", 5);
    private Tuote leipa = new Tuote(2, "leipä", 2);
    private Tuote kaviaari = new Tuote(3, "kaviaari", 99);
    private String kaupanTili = "33333-44455"; // kovakoodattu Kauppa-luokassa

    @Before
    public void setUpKauppa() {
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(viitenro);

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

        verify(pankki).tilisiirto(
            eq(nimi),
            eq(viitenro),
            eq(tilinumero),
            eq(kaupanTili),
            eq(maito.getHinta())
        );
    }

    @Test
    public void kahdenEriTuotteenOstoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikein() {
        kauppa.lisaaKoriin(maito.getId());
        kauppa.lisaaKoriin(leipa.getId());
        kauppa.tilimaksu(nimi, tilinumero);

        verify(pankki).tilisiirto(
            eq(nimi),
            eq(viitenro),
            eq(tilinumero),
            eq(kaupanTili),
            eq(maito.getHinta() + leipa.getHinta())
        );
    }

    @Test
    public void kahdenSamanTuotteenOstoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikein() {
        kauppa.lisaaKoriin(maito.getId());
        kauppa.lisaaKoriin(maito.getId());
        kauppa.tilimaksu(nimi, tilinumero);

        verify(pankki).tilisiirto(
            eq(nimi),
            eq(viitenro),
            eq(tilinumero),
            eq(kaupanTili),
            eq(2 * maito.getHinta())
        );
    }

    @Test
    public void toisenTuotteenOllessaLoppuKahdenTuotteenOstoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikein() {
        kauppa.lisaaKoriin(maito.getId());
        kauppa.lisaaKoriin(kaviaari.getId());
        kauppa.tilimaksu(nimi, tilinumero);

        verify(pankki).tilisiirto(
            eq(nimi),
            eq(viitenro),
            eq(tilinumero),
            eq(kaupanTili),
            eq(maito.getHinta())
        );
    }

}
