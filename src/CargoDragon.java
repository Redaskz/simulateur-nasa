// Modele concret de la capsule Cargo Dragon.
public class CargoDragon extends Capsule {
    public CargoDragon() {
        super("Cargo Dragon", false, 0, 9.5, 100);
    }

    @Override
    public double calculerIndiceSecurite() {
        return 7.8;
    }
}
