package missions;

// Mission inventee mission personnelle.
public class MissionPersonnelle extends Mission {
    public MissionPersonnelle() {
        super("Station lunaire", true, 450000, "12 jours", 0.0048);
    }

    @Override
    public String getDescriptionMission() {
        return "Mission personnelle : ravitaillement d'une future station lunaire.";
    }
}
