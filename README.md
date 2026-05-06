# Simulateur de lancement spatial

Simulateur de lancement de fusée en console, développé en Java. Il permet de configurer une fusée, choisir une mission et simuler un lancement avec gestion des erreurs et historique persistant.

---

## Fonctionnalités

- Choix du lanceur parmi 4 modèles (Saturne V, SLS, Ariane 5, Falcon 9)
- Choix de la capsule (Orion, Crew Dragon, Apollo, Cargo Dragon)
- Ajout de boosters selon les contraintes du lanceur
- Sélection d'une mission (Orbite terrestre, ISS, Lune, Mars, Station lunaire)
- Vérification des contraintes avant le lancement :
  - Carburant insuffisant
  - Surcharge de charge utile dépassée
  - Incompatibilité capsule / mission habitée
  - Anomalie technique aléatoire (5 % de probabilité)
- Historique des lancements sauvegardé automatiquement en CSV
- Diagramme UML fourni (`diagramme_UML.puml`)

---

## Lancement du projet

Depuis le dossier `simulateur-nasa` :

```powershell
javac -d bin src\App.java src\core\*.java src\lanceurs\*.java src\capsules\*.java src\missions\*.java
java -cp bin App
```

---

## Structure du projet

```
simulateur-nasa/
├── src/
│   ├── App.java                        # Point d'entrée
│   ├── core/
│   │   ├── Simulateur.java             # Menu, catalogues, logique de simulation
│   │   ├── Fusee.java                  # Composition lanceur + capsule + boosters
│   │   ├── Booster.java
│   │   ├── Lancement.java              # Archive un résultat
│   │   ├── ResultatSimulation.java
│   │   └── CarburantInsuffisantException.java
│   ├── lanceurs/
│   │   ├── Lanceur.java                # Classe abstraite
│   │   ├── SaturneV.java
│   │   ├── SLS.java
│   │   ├── Ariane5.java
│   │   └── Falcon9.java
│   ├── capsules/
│   │   ├── Capsule.java                # Classe abstraite
│   │   ├── Orion.java
│   │   ├── CrewDragon.java
│   │   ├── Apollo.java
│   │   └── CargoDragon.java
│   └── missions/
│       ├── Mission.java                # Classe abstraite
│       ├── OrbiteTerrestre.java
│       ├── ISSMission.java
│       ├── LuneMission.java
│       ├── MarsMission.java
│       └── MissionPersonnelle.java
├── diagramme_UML.puml
└── historique_lancements.csv           # Généré automatiquement au premier lancement
```

---

## Concepts POO utilisés

| Concept | Application |
|---|---|
| Héritage | `SaturneV extends Lanceur`, `Orion extends Capsule`, etc. |
| Classes abstraites | `Lanceur`, `Capsule`, `Mission` |
| Polymorphisme | Listes typées `List<Lanceur>`, `List<Mission>`, etc. |
| Composition | `Fusee` contient un `Lanceur`, une `Capsule`, des `Booster` |
| Redéfinition | `calculerPousseeMax()`, `calculerIndiceSecurite()`, `getDescriptionMission()` |
| Surcharge | `ajouterBooster(Booster)` et `ajouterBooster(Booster, int)` |
| Exception personnalisée | `CarburantInsuffisantException` |
| Persistance CSV | `Lancement.toCsv()` / `Lancement.fromCsv()` |

---

## Formules utilisées

```
carburant nécessaire = (masseTotale × distance × coefficientMission) / 1000
coût total = prix de la fusée + (carburant × 0.0012 M€)
```

Les prix sont stockés en millions d'euros (M€), donc `1 200 € = 0,0012 M€`.

---

## Historique

Les résultats sont sauvegardés dans `historique_lancements.csv` et rechargés automatiquement à chaque démarrage.

---

## Mission personnelle

La mission personnelle ajoutée est le **ravitaillement d'une station lunaire** (450 000 km, 12 jours, mission habitée).

---

## Auteur

**rreda** — 2025
