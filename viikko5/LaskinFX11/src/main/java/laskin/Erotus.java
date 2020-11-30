package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Erotus extends Komento {
    public Erotus(TextField syotekentta, Button undo, Sovelluslogiikka sovellus) {
        super(syotekentta, undo, sovellus);
    }

    @Override
    public void suorita() {
        sovellus.miinus(arvo());
    };

    @Override
    public void peru() {
        System.out.println("undo pressed");
    };

    private int arvo() {
        try {
            return Integer.parseInt(syotekentta.getText());
        } catch (Exception e) {
            return 0;
        }
    }
}
