package core;

// Regroupe les informations renvoyees apres une simulation de lancement.
public class ResultatSimulation {
    private boolean succes;
    private String raison;
    private double carburantNecessaire;
    private double coutTotal;

    public ResultatSimulation(boolean succes, String raison, double carburantNecessaire, double coutTotal) {
        this.succes = succes;
        this.raison = raison;
        this.carburantNecessaire = carburantNecessaire;
        this.coutTotal = coutTotal;
    }

    public boolean isSucces() {
        return succes;
    }

    public String getRaison() {
        return raison;
    }

    public double getCarburantNecessaire() {
        return carburantNecessaire;
    }

    public double getCoutTotal() {
        return coutTotal;
    }
}
