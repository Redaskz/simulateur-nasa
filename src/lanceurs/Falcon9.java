package lanceurs;

// Modele concret du lanceur Falcon 9.
public class Falcon9 extends Lanceur {
    public Falcon9() {
        super("Falcon 9", true, 0, 500, 22, 60, 55);
    }

    @Override
    public double calculerPousseeMax() {
        return 7607;
    }
}
