package lanceurs;

// Modele concret du lanceur Saturne V.
public class SaturneV extends Lanceur {
    public SaturneV() {
        super("Saturne V", true, 0, 2700, 140, 1500, 130);
    }

    @Override
    public double calculerPousseeMax() {
        return 35100;
    }
}
