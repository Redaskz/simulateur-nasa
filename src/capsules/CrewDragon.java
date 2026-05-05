package capsules;

// Modele concret de la capsule Crew Dragon.
public class CrewDragon extends Capsule {
    public CrewDragon() {
        super("Crew Dragon", true, 7, 12.0, 150);
    }

    @Override
    public double calculerIndiceSecurite() {
        return 9.0;
    }
}
