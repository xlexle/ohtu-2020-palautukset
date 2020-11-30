package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public abstract class Komento {
    protected TextField syotekentta;
    protected Button undo;
    protected Sovelluslogiikka sovellus;

    public Komento(TextField syotekentta, Button undo, Sovelluslogiikka sovellus) {
        this.syotekentta = syotekentta;
        this.undo = undo;
        this.sovellus = sovellus;
    }

    public Komento(Button undo, Sovelluslogiikka sovellus) {
        this.undo = undo;
        this.sovellus = sovellus;
    }

    public abstract void suorita();
    public abstract void peru();
}
