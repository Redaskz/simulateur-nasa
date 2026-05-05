package missions;

// Classe abstraite pour definir les caracteristiques communes a toutes les missions.
public abstract class Mission {
    private String nom;
    private boolean habitee;
    private double distance;
    private String duree;
    private double coefficientCarburant;

    public Mission(String nom, boolean habitee, double distance, String duree, double coefficientCarburant) {
        this.nom = nom;
        this.habitee = habitee;
        this.distance = distance;
        this.duree = duree;
        this.coefficientCarburant = coefficientCarburant;
    }

    public String getNom() {
        return nom;
    }

    public boolean isHabitee() {
        return habitee;
    }

    public double getDistance() {
        return distance;
    }

    public String getDuree() {
        return duree;
    }

    public double getCoefficientCarburant() {
        return coefficientCarburant;
    }

    // Memo: utilisé pour illustrer le polymorphisme sur les missions.
    public abstract String getDescriptionMission();

    @Override
    public String toString() {
        return nom + " | habitée=" + (habitee ? "oui" : "non")
                + " | distance=" + distance + " km"
                + " | durée=" + duree
                + " | coefficient=" + coefficientCarburant;
    }
}
