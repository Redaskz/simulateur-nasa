package core;

import capsules.Apollo;
import capsules.Capsule;
import capsules.CargoDragon;
import capsules.CrewDragon;
import capsules.Orion;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import lanceurs.Ariane5;
import lanceurs.Falcon9;
import lanceurs.Lanceur;
import lanceurs.SLS;
import lanceurs.SaturneV;
import missions.ISSMission;
import missions.LuneMission;
import missions.MarsMission;
import missions.Mission;
import missions.MissionPersonnelle;
import missions.OrbiteTerrestre;

// Gere le menu, les choix utilisateur, la simulation et l'historique.
public class Simulateur {
    private static final double PRIX_KEROSENE_PAR_TONNE = 0.0012;
    private static final double PROBABILITE_ECHEC_ALEATOIRE = 0.05;
    private static final String FICHIER_HISTORIQUE = "historique_lancements.csv";

    private Scanner scanner;
    private List<Lanceur> catalogueLanceurs;
    private List<Capsule> catalogueCapsules;
    private List<Booster> catalogueBoosters;
    private List<Mission> catalogueMissions;
    private List<Lancement> historique;
    private Random random;

    public Simulateur() {
        scanner = new Scanner(System.in);
        catalogueLanceurs = creerLanceurs();
        catalogueCapsules = creerCapsules();
        catalogueBoosters = creerBoosters();
        catalogueMissions = creerMissions();
        historique = new ArrayList<>();
        random = new Random();
        chargerHistorique();
    }

    public void demarrer() {
        boolean quitter = false;

        while (!quitter) {
            afficherMenu();
            int choix = lireEntier("Votre choix : ");

            switch (choix) {
                case 1:
                    lancerSimulationComplete();
                    break;
                case 2:
                    afficherHistorique();
                    break;
                case 3:
                    afficherCatalogues();
                    break;
                case 4:
                    quitter = true;
                    System.out.println("Fermeture du simulateur.");
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }

    private void afficherMenu() {
        System.out.println();
        System.out.println("===== SIMULATEUR NASA =====");
        System.out.println("1. Configurer et lancer une mission");
        System.out.println("2. Voir l'historique");
        System.out.println("3. Voir les catalogues");
        System.out.println("4. Quitter");
    }

    private void lancerSimulationComplete() {
        System.out.println();
        System.out.println("===== CONFIGURATION DE LA FUSEE =====");

        Lanceur lanceur = choisirLanceur();
        Capsule capsule = choisirCapsule();
        Fusee fusee = new Fusee(lanceur, capsule);

        ajouterBoosters(fusee);
        afficherRecapitulatifFusee(fusee);

        System.out.println();
        System.out.println("===== CHOIX DE LA MISSION =====");
        Mission mission = choisirMission();
        System.out.println("Mission choisie : " + mission.getNom());
        System.out.println("Description : " + mission.getDescriptionMission());

        System.out.println();
        System.out.println("===== RESULTAT DE LA SIMULATION =====");
        ResultatSimulation resultat = simulerLancement(fusee, mission);
        afficherResultat(resultat);
        enregistrerLancement(fusee, mission, resultat);
    }

    private Lanceur choisirLanceur() {
        System.out.println("Liste des lanceurs :");
        afficherListe(catalogueLanceurs);
        int choix = lireEntierDansIntervalle("Choisissez un lanceur : ", 1, catalogueLanceurs.size());
        return catalogueLanceurs.get(choix - 1);
    }

    private Capsule choisirCapsule() {
        System.out.println("Liste des capsules :");
        afficherListe(catalogueCapsules);
        int choix = lireEntierDansIntervalle("Choisissez une capsule : ", 1, catalogueCapsules.size());
        return catalogueCapsules.get(choix - 1);
    }

    private Mission choisirMission() {
        System.out.println("Liste des missions :");
        afficherListe(catalogueMissions);
        int choix = lireEntierDansIntervalle("Choisissez une mission : ", 1, catalogueMissions.size());
        return catalogueMissions.get(choix - 1);
    }

    private void ajouterBoosters(Fusee fusee) {
        if (fusee.getLanceur().getBoostersMax() == 0) {
            System.out.println("Ce lanceur n'accepte aucun booster.");
            return;
        }

        System.out.println("Liste des boosters :");
        afficherListe(catalogueBoosters);
        System.out.println("Vous pouvez ajouter jusqu'à " + fusee.getLanceur().getBoostersMax() + " booster(s).");

        int quantite = lireEntierDansIntervalle("Combien de boosters voulez-vous ajouter ? ",
                0, fusee.getLanceur().getBoostersMax());

        if (quantite == 0) {
            return;
        }

        int choixBooster = lireEntierDansIntervalle("Choisissez le type de booster : ", 1, catalogueBoosters.size());
        Booster booster = catalogueBoosters.get(choixBooster - 1);
        fusee.ajouterBooster(booster, quantite);
    }

    private void afficherRecapitulatifFusee(Fusee fusee) {
        System.out.println();
        System.out.println("===== RECAPITULATIF =====");
        System.out.println("Lanceur : " + fusee.getLanceur());
        System.out.println("Capsule : " + fusee.getCapsule());
        System.out.println("Boosters : " + fusee.getNombreBoosters());
        System.out.println("Masse totale : " + String.format("%.2f", fusee.calculerMasseTotale()) + " t");
        System.out.println("Prix total matériel : " + String.format("%.2f", fusee.calculerPrixTotal()) + " M€");
    }

    private ResultatSimulation simulerLancement(Fusee fusee, Mission mission) {
        double masseTotale = fusee.calculerMasseTotale();
        double carburantNecessaire = calculerCarburantNecessaire(fusee, mission);
        double coutTotal = calculerCoutTotal(fusee, carburantNecessaire);

        try {
            verifierConditions(fusee, mission, masseTotale, carburantNecessaire);
        } catch (CarburantInsuffisantException exception) {
            return new ResultatSimulation(false, exception.getMessage(), carburantNecessaire, coutTotal);
        }

        double tirage = random.nextDouble();
        if (tirage < PROBABILITE_ECHEC_ALEATOIRE) {
            return new ResultatSimulation(false, "Anomalie technique imprévue", carburantNecessaire, coutTotal);
        }

        return new ResultatSimulation(true, "Succès", carburantNecessaire, coutTotal);
    }

    private void verifierConditions(Fusee fusee, Mission mission, double masseTotale, double carburantNecessaire)
            throws CarburantInsuffisantException {
        if (carburantNecessaire > fusee.getLanceur().getCarburantMax()) {
            throw new CarburantInsuffisantException("Carburant insuffisant");
        }

        if (fusee.getCapsule().getMasse() > fusee.getLanceur().getChargeUtileMax()) {
            throw new CarburantInsuffisantException("Surcharge dépassée");
        }

        if (fusee.getNombreBoosters() > fusee.getLanceur().getBoostersMax()) {
            throw new CarburantInsuffisantException("Trop de boosters");
        }

        if (!fusee.estCompatibleAvecMission(mission)) {
            throw new CarburantInsuffisantException("Capsule incompatible avec une mission habitée");
        }

        if (masseTotale <= 0) {
            throw new CarburantInsuffisantException("Masse totale invalide");
        }
    }

    private double calculerCarburantNecessaire(Fusee fusee, Mission mission) {
        return (fusee.calculerMasseTotale() * mission.getDistance() * mission.getCoefficientCarburant()) / 1000.0;
    }

    private double calculerCoutTotal(Fusee fusee, double carburantNecessaire) {
        return fusee.calculerPrixTotal() + (carburantNecessaire * PRIX_KEROSENE_PAR_TONNE);
    }

    private void afficherResultat(ResultatSimulation resultat) {
        System.out.println("Carburant nécessaire : " + String.format("%.2f", resultat.getCarburantNecessaire()) + " t");
        System.out.println("Coût total : " + String.format("%.2f", resultat.getCoutTotal()) + " M€");

        if (resultat.isSucces()) {
            System.out.println("Verdict : SUCCES");
            System.out.println("Raison : " + resultat.getRaison());
        } else {
            System.out.println("Verdict : ECHEC");
            System.out.println("Raison : " + resultat.getRaison());
        }
    }

    private void enregistrerLancement(Fusee fusee, Mission mission, ResultatSimulation resultat) {
        String verdict = resultat.isSucces() ? "Succès" : "Échec";
        Lancement lancement = new Lancement(
                LocalDateTime.now(),
                fusee.getResume(),
                mission.getNom(),
                verdict,
                resultat.getRaison(),
                resultat.getCoutTotal()
        );

        historique.add(lancement);
        sauvegarderHistorique();
    }

    private void afficherHistorique() {
        System.out.println();
        System.out.println("===== HISTORIQUE =====");

        if (historique.isEmpty()) {
            System.out.println("Aucun lancement enregistré.");
            return;
        }

        for (Lancement lancement : historique) {
            System.out.println(lancement);
        }
    }

    private void afficherCatalogues() {
        System.out.println();
        System.out.println("===== LANCEURS =====");
        afficherListe(catalogueLanceurs);
        System.out.println();
        System.out.println("===== CAPSULES =====");
        afficherListe(catalogueCapsules);
        System.out.println();
        System.out.println("===== BOOSTERS =====");
        afficherListe(catalogueBoosters);
        System.out.println();
        System.out.println("===== MISSIONS =====");
        afficherListe(catalogueMissions);
    }

    private void afficherListe(List<?> liste) {
        for (int i = 0; i < liste.size(); i++) {
            System.out.println((i + 1) + ". " + liste.get(i));
        }
    }

    private int lireEntier(String message) {
        while (true) {
            System.out.print(message);
            String saisie = scanner.nextLine();

            try {
                return Integer.parseInt(saisie);
            } catch (NumberFormatException exception) {
                System.out.println("Veuillez entrer un nombre entier.");
            }
        }
    }

    private int lireEntierDansIntervalle(String message, int min, int max) {
        int valeur;

        do {
            valeur = lireEntier(message);
            if (valeur < min || valeur > max) {
                System.out.println("Entrez un nombre entre " + min + " et " + max + ".");
            }
        } while (valeur < min || valeur > max);

        return valeur;
    }

    private void chargerHistorique() {
        Path chemin = Paths.get(FICHIER_HISTORIQUE);

        if (!Files.exists(chemin)) {
            return;
        }

        try {
            List<String> lignes = Files.readAllLines(chemin);
            for (String ligne : lignes) {
                if (!ligne.isBlank()) {
                    historique.add(Lancement.fromCsv(ligne));
                }
            }
        } catch (IOException exception) {
            System.out.println("Impossible de charger l'historique.");
        }
    }

    private void sauvegarderHistorique() {
        Path chemin = Paths.get(FICHIER_HISTORIQUE);

        try (BufferedWriter writer = Files.newBufferedWriter(chemin)) {
            for (Lancement lancement : historique) {
                writer.write(lancement.toCsv());
                writer.newLine();
            }
        } catch (IOException exception) {
            System.out.println("Impossible de sauvegarder l'historique.");
        }
    }

    private List<Lanceur> creerLanceurs() {
        List<Lanceur> lanceurs = new ArrayList<>();
        lanceurs.add(new SaturneV());
        lanceurs.add(new Ariane5());
        lanceurs.add(new Falcon9());
        lanceurs.add(new SLS());
        return lanceurs;
    }

    private List<Capsule> creerCapsules() {
        List<Capsule> capsules = new ArrayList<>();
        capsules.add(new Orion());
        capsules.add(new CrewDragon());
        capsules.add(new Apollo());
        capsules.add(new CargoDragon());
        return capsules;
    }

    private List<Booster> creerBoosters() {
        List<Booster> boosters = new ArrayList<>();
        boosters.add(new Booster("EAP (Ariane)", 6470, 270, 30));
        boosters.add(new Booster("SRB (Shuttle)", 12500, 590, 55));
        boosters.add(new Booster("BE-3", 490, 25, 12));
        return boosters;
    }

    private List<Mission> creerMissions() {
        List<Mission> missions = new ArrayList<>();
        missions.add(new OrbiteTerrestre());
        missions.add(new ISSMission());
        missions.add(new LuneMission());
        missions.add(new MarsMission());
        missions.add(new MissionPersonnelle());
        return missions;
    }
}
