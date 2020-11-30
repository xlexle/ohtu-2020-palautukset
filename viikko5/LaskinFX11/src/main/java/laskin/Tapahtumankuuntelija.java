package laskin;

import java.util.HashMap;
import java.util.Map;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Tapahtumankuuntelija implements EventHandler {
    private TextField tuloskentta;
    private TextField syotekentta;
    private Button nollaa;
    private Button undo;
    private Sovelluslogiikka sovellus;

    private Map<Button, Komento> komennot;
    private Komento edellinen = null;

    public Tapahtumankuuntelija(TextField tuloskentta, TextField syotekentta, Button plus, Button miinus, Button nollaa, Button undo) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = new Sovelluslogiikka();
        this.komennot = new HashMap<>();
        this.komennot.put(plus, new Summa(syotekentta, undo, sovellus));
        this.komennot.put(miinus, new Erotus(syotekentta, undo, sovellus));
        this.komennot.put(nollaa, new Nollaa(undo, sovellus));
    }

    @Override
    public void handle(Event event) {
        if (event.getTarget() != undo) {
            Komento komento = this.komennot.get((Button) event.getTarget());
            komento.suorita();
            edellinen = komento;
        } else {
            edellinen.peru();
            edellinen = null;
        }

        paivitaTila();
    }

    private void paivitaTila() {
        int laskunTulos = sovellus.tulos();
        tuloskentta.setText("" + laskunTulos);
        syotekentta.setText("");
        nollaa.disableProperty().set(laskunTulos == 0);
        undo.disableProperty().set(false);
    }
}
