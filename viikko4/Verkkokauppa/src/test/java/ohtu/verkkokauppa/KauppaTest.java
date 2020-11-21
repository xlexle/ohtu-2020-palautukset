package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {
    private int viitenro = 42;
    private String nimi = "pekka";
    private String tilinumero = "12345";
    private Tuote maito = new Tuote(1, "maito", 5);
    private String kaupanTili = "33333-44455"; // kovakoodattu Kauppa-luokassa

    @Test
    public void yhdenTuotteenOstoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikein() {
        Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(viitenro);

        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(maito);

        Kauppa k = new Kauppa(varasto, pankki, viite);
        k.aloitaAsiointi();
        k.lisaaKoriin(1); // maito id
        k.tilimaksu(nimi, tilinumero);

        verify(pankki).tilisiirto(
            eq(nimi),
            eq(viitenro),
            eq(tilinumero),
            eq(kaupanTili),
            eq(maito.getHinta())
        );
    }

}
