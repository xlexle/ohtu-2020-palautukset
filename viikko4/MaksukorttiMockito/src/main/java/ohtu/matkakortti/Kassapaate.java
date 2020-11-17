package ohtu.matkakortti;

public class Kassapaate {
    private int myytyjaLounaita;
    public static final int HINTA = 5;

    public Kassapaate() {
        this.myytyjaLounaita = 0;
    }

    public void lataa(Maksukortti kortti, int summa){
        kortti.lataa(summa);
    }

    public void ostaLounas(Maksukortti kortti) {
        if (kortti.getSaldo() < HINTA) {
            System.out.println("Kortilla ei ole saldoa.");
            return; // TODO throw custom exception instead of printing and returning
        }

        kortti.osta(HINTA);
        myytyjaLounaita++;
    }

    public int getMyytyjaLounaita() {
        return myytyjaLounaita;
    }
}
