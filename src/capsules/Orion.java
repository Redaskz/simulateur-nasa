package capsules;

// Modele concret de la capsule Orion.
public class Orion extends Capsule {
    public Orion() {
        super("Orion", true, 4, 10.4, 300);
    }

    @Override
    public double calculerIndiceSecurite() {
        return 9.5;
    }
}
