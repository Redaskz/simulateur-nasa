package missions;

// Modele concret d'une mission vers la Lune.
public class LuneMission extends Mission {
    public LuneMission() {
        super("Lune", true, 400000, "8-10 jours", 0.005);
    }

    @Override
    public String getDescriptionMission() {
        return "Mission habitée autour de la Lune ou vers sa surface.";
    }
}
