package lanceurs;

// Modele concret du lanceur SLS.
public class SLS extends Lanceur {
    public SLS() {
        super("SLS", true, 2, 2600, 130, 2000, 120);
    }

    @Override
    public double calculerPousseeMax() {
        return 39100;
    }
}
