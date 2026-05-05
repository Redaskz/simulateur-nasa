
package capsules;

// Modele concret de la capsule Apollo.
public class Apollo extends Capsule {
    public Apollo() {
        super("Apollo", true, 3, 5.6, 200);
    }

    @Override
    public double calculerIndiceSecurite() {
        return 8.7;
    }
}
