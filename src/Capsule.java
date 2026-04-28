// Classe abstraite pour definir les caracteristiques communes a toutes les capsules.
public abstract class Capsule {
    private String nom;
    private boolean habitee;
    private int occupantsMax;
    private double masse;
    private double prix;

    public Capsule(String nom, boolean habitee, int occupantsMax, double masse, double prix) {
        this.nom = nom;
        this.habitee = habitee;
        this.occupantsMax = occupantsMax;
        this.masse = masse;
        this.prix = prix;
    }

    public String getNom() {
        return nom;
    }

    public boolean isHabitee() {
        return habitee;
    }

    public int getOccupantsMax() {
        return occupantsMax;
    }

    public double getMasse() {
        return masse;
    }

    public double getPrix() {
        return prix;
    }

    // Memo: chaque capsule peut ajuster sa sécurité / marge différemment.
    public abstract double calculerIndiceSecurite();

    @Override
    public String toString() {
        return nom + " | habitée=" + (habitee ? "oui" : "non")
                + " | occupants max=" + occupantsMax
                + " | masse=" + masse + " t"
                + " | prix=" + prix + " M€";
    }
}
