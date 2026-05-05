package missions;

// Modele concret d'une mission en orbite terrestre.
public class OrbiteTerrestre extends Mission {
    public OrbiteTerrestre() {
        super("Orbite terrestre", false, 400, "Quelques heures", 1.0);
    }

    @Override
    public String getDescriptionMission() {
        return "Mission courte en orbite basse autour de la Terre.";
    }
}
