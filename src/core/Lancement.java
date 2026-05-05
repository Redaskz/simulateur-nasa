package core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Garde en memoire le resultat d'une simulation pour l'historique.
public class Lancement {
    private LocalDateTime date;
    private String fusee;
    private String mission;
    private String resultat;
    private String raison;
    private double coutTotal;

    public Lancement(LocalDateTime date, String fusee, String mission, String resultat, String raison, double coutTotal) {
        this.date = date;
        this.fusee = fusee;
        this.mission = mission;
        this.resultat = resultat;
        this.raison = raison;
        this.coutTotal = coutTotal;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getFusee() {
        return fusee;
    }

    public String getMission() {
        return mission;
    }

    public String getResultat() {
        return resultat;
    }

    public String getRaison() {
        return raison;
    }

    public double getCoutTotal() {
        return coutTotal;
    }

    public String toCsv() {
        return date + ";" + fusee + ";" + mission + ";" + resultat + ";" + raison + ";" + coutTotal;
    }

    public static Lancement fromCsv(String ligne) {
        String[] morceaux = ligne.split(";", -1);
        return new Lancement(
                LocalDateTime.parse(morceaux[0]),
                morceaux[1],
                morceaux[2],
                morceaux[3],
                morceaux[4],
                Double.parseDouble(morceaux[5])
        );
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return date.format(format)
                + " | " + fusee
                + " | mission=" + mission
                + " | résultat=" + resultat
                + " | raison=" + raison
                + " | coût=" + String.format("%.2f", coutTotal) + " M€";
    }
}
