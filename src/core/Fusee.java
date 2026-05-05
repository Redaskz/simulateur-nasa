package core;

import capsules.Capsule;
import lanceurs.Lanceur;
import missions.Mission;
import java.util.ArrayList;
import java.util.List;

// Represente une fusee composee d'un lanceur, d'une capsule et de boosters.
public class Fusee {
    private Lanceur lanceur;
    private Capsule capsule;
    private List<Booster> boosters;

    public Fusee(Lanceur lanceur, Capsule capsule) {
        this.lanceur = lanceur;
        this.capsule = capsule;
        this.boosters = new ArrayList<>();
    }

    public Lanceur getLanceur() {
        return lanceur;
    }

    public Capsule getCapsule() {
        return capsule;
    }

    public List<Booster> getBoosters() {
        return boosters;
    }

    // Memo: surcharge 
    public void ajouterBooster(Booster booster) {
        boosters.add(booster);
    }

    public void ajouterBooster(Booster booster, int quantite) {
        for (int i = 0; i < quantite; i++) {
            boosters.add(booster);
        }
    }

    public int getNombreBoosters() {
        return boosters.size();
    }

    public double calculerMasseTotale() {
        double masseTotale = lanceur.getMasseBase() + capsule.getMasse();

        for (Booster booster : boosters) {
            masseTotale += booster.getMasse();
        }

        return masseTotale;
    }

    public double calculerPrixTotal() {
        double prixTotal = lanceur.getPrix() + capsule.getPrix();

        for (Booster booster : boosters) {
            prixTotal += booster.getPrix();
        }

        return prixTotal;
    }

    public boolean estCompatibleAvecMission(Mission mission) {
        if (mission.isHabitee() && !capsule.isHabitee()) {
            return false;
        }

        if (mission.isHabitee() && !lanceur.isHabiteAutorise()) {
            return false;
        }

        return true;
    }

    public String getResume() {
        return lanceur.getNom() + " + " + capsule.getNom() + " + " + getNombreBoosters() + " booster(s)";
    }

    @Override
    public String toString() {
        return getResume()
                + " | masse totale=" + calculerMasseTotale() + " t"
                + " | prix=" + calculerPrixTotal() + " M€";
    }
}
