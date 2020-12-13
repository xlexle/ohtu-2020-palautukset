package ohtu.kivipaperisakset;

import java.util.Scanner;

public class Ihminen implements Pelaava {
    private Scanner scanner = new Scanner(System.in);
    private String nimi;

    public Ihminen(String nimi) {
        this.nimi = nimi;
    }

    @Override
    public String annaSiirto() {
        System.out.println(this.nimi + " - anna siirto: ");
        return scanner.nextLine();
    }

}
