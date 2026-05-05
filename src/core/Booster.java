package core;

// Represente un booster ajoute a la fusee pour augmenter ses capacites.
public class Booster {
    private String nom;
    private double pousseeAdditionnelle;
    private double masse;
    private double prix;

    public Booster(String nom, double pousseeAdditionnelle, double masse, double prix) {
        this.nom = nom;
        this.pousseeAdditionnelle = pousseeAdditionnelle;
        this.masse = masse;
        this.prix = prix;
    }

    public String getNom() {
        return nom;
    }

    public double getPousseeAdditionnelle() {
        return pousseeAdditionnelle;
    }

    public double getMasse() {
        return masse;
    }

    public double getPrix() {
        return prix;
    }

    @Override
    public String toString() {
        return nom + " | masse=" + masse + " t | prix=" + prix + " M€";
    }
}
