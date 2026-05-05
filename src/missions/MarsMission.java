package missions;

// Modele concret d'une mission vers Mars.
public class MarsMission extends Mission {
    public MarsMission() {
        super("Mars", true, 225000000, "12-18 mois", 0.000015);
    }

    @Override
    public String getDescriptionMission() {
        return "Mission longue durée vers Mars.";
    }
}
