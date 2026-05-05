package lanceurs;

// Modele concret du lanceur Ariane 5.
public class Ariane5 extends Lanceur {
    public Ariane5() {
        super("Ariane 5", false, 2, 700, 20, 180, 80);
    }

    @Override
    public double calculerPousseeMax() {
        return 13300;
    }
}
