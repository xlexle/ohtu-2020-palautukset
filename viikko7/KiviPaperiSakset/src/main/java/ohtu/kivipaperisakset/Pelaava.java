package ohtu.kivipaperisakset;

public interface Pelaava {
    public String annaSiirto();
    public default void muistaSiirto(String siirto) {}
}
