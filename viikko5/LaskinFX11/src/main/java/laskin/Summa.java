package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Summa extends Komento {
    public Summa(TextField syotekentta, Button undo, Sovelluslogiikka sovellus) {
        super(syotekentta, undo, sovellus);
    }

    @Override
    public void suorita() {
        edellinenTulos = sovellus.tulos();
        sovellus.plus(arvo());
    };

    @Override
    public void peru() {
        sovellus.setTulos(edellinenTulos);
    };

    private int arvo() {
        try {
            return Integer.parseInt(syotekentta.getText());
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }
}
