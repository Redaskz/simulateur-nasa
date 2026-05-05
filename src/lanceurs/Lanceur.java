package lanceurs;

// Classe abstraite pour definir les caracteristiques communes a tous les lanceurs.
public abstract class Lanceur {
    private String nom;
    private boolean habiteAutorise;
    private int boostersMax;
    private double carburantMax;
    private double chargeUtileMax;
    private double prix;
    private double masseBase;

    public Lanceur(String nom, boolean habiteAutorise, int boostersMax, double carburantMax,
                   double chargeUtileMax, double prix, double masseBase) {
        this.nom = nom;
        this.habiteAutorise = habiteAutorise;
        this.boostersMax = boostersMax;
        this.carburantMax = carburantMax;
        this.chargeUtileMax = chargeUtileMax;
        this.prix = prix;
        this.masseBase = masseBase;
    }

    public String getNom() {
        return nom;
    }

    public boolean isHabiteAutorise() {
        return habiteAutorise;
    }

    public int getBoostersMax() {
        return boostersMax;
    }

    public double getCarburantMax() {
        return carburantMax;
    }

    public double getChargeUtileMax() {
        return chargeUtileMax;
    }

    public double getPrix() {
        return prix;
    }

    public double getMasseBase() {
        return masseBase;
    }

    // Memo: méthode abstraite pour montrer l'override dans chaque sous-classe.
    public abstract double calculerPousseeMax();

    @Override
    public String toString() {
        return nom + " | habité=" + (habiteAutorise ? "oui" : "non")
                + " | boosters max=" + boostersMax
                + " | carburant max=" + carburantMax + " t"
                + " | charge utile max=" + chargeUtileMax + " t"
                + " | prix=" + prix + " M€";
    }
}
