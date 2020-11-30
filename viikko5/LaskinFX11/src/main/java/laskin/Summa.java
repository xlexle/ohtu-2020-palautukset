package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Summa extends Komento {
    public Summa(TextField syotekentta, Button undo, Sovelluslogiikka sovellus) {
        super(syotekentta, undo, sovellus);
    }

    @Override
    public void suorita() {
        sovellus.plus(arvo());
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
