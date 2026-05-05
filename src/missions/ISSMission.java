package missions;

// Modele concret d'une mission vers l'ISS.
public class ISSMission extends Mission {
    public ISSMission() {
        super("ISS", true, 400, "12 h - quelques jours", 1.2);
    }

    @Override
    public String getDescriptionMission() {
        return "Mission habitée vers la Station Spatiale Internationale.";
    }
}
