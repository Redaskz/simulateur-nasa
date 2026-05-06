# Simulateur NASA

## Objectif

Le programme permet de :

- choisir un lanceur
- choisir une capsule
- ajouter des boosters
- choisir une mission
- simuler le lancement
- enregistrer le resultat dans un historique

## Lancement du projet

Depuis le dossier `simulateur-nasa` :

```powershell
javac -d bin src\App.java src\core\*.java src\lanceurs\*.java src\capsules\*.java src\missions\*.java
java -cp bin App
```

## Structure simple du projet

- `App` : point d'entree
- `Simulateur` : gere le menu, les catalogues et la simulation
- `Lanceur` : classe abstraite + sous-classes `SaturneV`, `Ariane5`, `Falcon9`, `SLS`
- `Capsule` : classe abstraite + sous-classes `Orion`, `CrewDragon`, `Apollo`, `CargoDragon`
- `Mission` : classe abstraite + sous-classes `OrbiteTerrestre`, `ISSMission`, `LuneMission`, `MarsMission`, `MissionPersonnelle`
- `Fusee` : composition entre un lanceur, une capsule et une liste de boosters
- `Lancement` : archive un resultat de simulation
- `CarburantInsuffisantException` : exception metier personnalisee

## Formules utilisees

- `carburant = (masseTotale x distance x coefficientMission) / 1000`
- `cout total = prixFusee + (carburant x 1200 EUR)`

Dans le code, les prix sont stockes en `MEUR`.
Donc `1200 EUR = 0.0012 MEUR`.

## la POO

- heritage : `SaturneV` est un `Lanceur`, `Orion` est une `Capsule`, etc.
- composition : une `Fusee` a un `Lanceur`, une `Capsule` et des `Booster`
- polymorphisme : les listes utilisent les types parents (`List<Lanceur>`, `List<Mission>`, etc.)
- redefinition : `calculerPousseeMax`, `calculerIndiceSecurite`, `getDescriptionMission`
- surcharge : `ajouterBooster(Booster)` et `ajouterBooster(Booster, int)`

## Historique

L'historique est sauvegarde dans :

- `historique_lancements.csv`

Le fichier est relu au demarrage.

## Mission personnelle choisie

- `Station lunaire`
