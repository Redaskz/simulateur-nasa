# Memo oral

## Idee generale

Le projet simule un lancement en 4 etapes :

1. on construit une fusee
2. on choisit une mission
3. on verifie les regles metier
4. on sauvegarde le resultat dans l'historique

## Role de chaque classe

### `App`

Point d'entree du programme.
Elle cree le `Simulateur` puis lance le menu.

### `Simulateur`

C'est la classe principale.
Elle gere :

- le menu console
- les listes de lanceurs, capsules, boosters, missions
- la saisie utilisateur
- le calcul du lancement
- la sauvegarde de l'historique

### `Lanceur`

Classe abstraite.
Elle contient ce qui est commun a tous les lanceurs :

- nom
- nombre max de boosters
- carburant max
- charge utile max
- prix

Pourquoi abstraite :
on ne veut pas creer un "lanceur generique", on veut creer un `SaturneV`, `Falcon9`, etc.

### `SaturneV`, `Ariane5`, `Falcon9`, `SLS`

Sous-classes concretes de `Lanceur`.
Elles redefinissent `calculerPousseeMax()`.

### `Capsule`

Classe abstraite pour les caracteristiques communes :

- nom
- habitée ou non
- occupants max
- masse
- prix

### `Orion`, `CrewDragon`, `Apollo`, `CargoDragon`

Sous-classes concretes de `Capsule`.
Elles redefinissent `calculerIndiceSecurite()`.

### `Mission`

Classe abstraite pour les donnees communes :

- nom
- mission habitee ou non
- distance
- duree
- coefficient carburant

### `OrbiteTerrestre`, `ISSMission`, `LuneMission`, `MarsMission`, `MissionPersonnelle`

Sous-classes concretes de `Mission`.
Elles redefinissent `getDescriptionMission()`.

### `Booster`

Classe simple.
Pas abstraite.
Elle represente un propulseur d'appoint.

### `Fusee`

Classe de composition.
Une fusee a :

- un lanceur
- une capsule
- une liste de boosters

Important a dire a l'oral :
une fusee n'est pas un lanceur.
Elle utilise un lanceur.
Donc ici on fait de la composition, pas de l'heritage.

### `Lancement`

Classe qui garde le resultat d'une simulation :

- date
- fusee
- mission
- resultat
- raison
- cout total

Elle sert aussi pour la sauvegarde dans le fichier CSV.

### `ResultatSimulation`

Petite classe pratique pour retourner proprement :

- succes ou echec
- raison
- carburant necessaire
- cout total

### `CarburantInsuffisantException`

Exception metier personnalisee.
Elle est levee quand une regle de lancement n'est pas respectee.

## Ou sont les notions de POO

### Encapsulation

Les attributs sont `private`.
On passe par des getters pour lire les valeurs.

### Heritage

Exemple :
`Falcon9` herite de `Lanceur`.

### Polymorphisme

Exemple :
la liste `List<Mission>` contient plusieurs types reels de mission.
Quand on appelle `getDescriptionMission()`, c'est la bonne version qui s'execute selon l'objet.

### Composition

`Fusee` contient un `Lanceur`, une `Capsule` et des `Booster`.

### Redefinition

Exemple :
`calculerPousseeMax()` existe dans la classe abstraite `Lanceur`, puis chaque sous-classe la redefinit.

### Surcharge

Dans `Fusee` :

- `ajouterBooster(Booster booster)`
- `ajouterBooster(Booster booster, int quantite)`

Meme nom, parametres differents.

## Logique metier

### Carburant

Formule :

`(masse totale x distance x coefficient mission) / 1000`

### Cout total

Formule :

`prix materiel + carburant x 1200 EUR`

Dans le code, le prix est stocke en millions d'euros.
Donc `1200 EUR = 0.0012 M EUR`.

### Causes d'echec

- carburant insuffisant
- surcharge
- trop de boosters
- capsule non compatible avec mission habitee
- echec aleatoire a 5 %

## Pourquoi le code est simple

- pas de framework
- pas de package complique
- une classe = un role clair
- peu de logique cachee
- noms explicites

## Si le jury demande "pourquoi ce choix ?"

Tu peux repondre :

"J'ai privilegie une architecture simple pour bien montrer les bases de la POO et pour pouvoir modifier le code facilement pendant l'oral."
