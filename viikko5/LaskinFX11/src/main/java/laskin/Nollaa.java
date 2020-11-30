package laskin;

import javafx.scene.control.Button;

public class Nollaa extends Komento {
    public Nollaa(Button undo, Sovelluslogiikka sovellus) {
        super(undo, sovellus);
    }

    @Override
    public void suorita() {
        edellinenTulos = sovellus.tulos();
        sovellus.nollaa();
    };

    @Override
    public void peru() {
        sovellus.setTulos(edellinenTulos);
    };
}
