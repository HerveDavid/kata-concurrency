# KATA concurrency

Cake Machine (french description)

Sujet : Vous devez développer un programme (console) en C#, de simulation d’une usine de fabrication de gâteau.

L'objectif : Faire le plus de gâteaux possible (on ne se soucie pas des réserves d'ingrédients, on les considère infinies).

Un gâteau est prêt lorsqu'il a passé les 3 étapes :

préparation : durée aléatoire entre 5 et 8 secondes
cuisson : durée 10 secondes
emballage : durée 2 secondes
Modalités de production :

Je peux préparer 3 gâteaux en même temps
Je peux cuire 5 gâteaux en même temps
Je peux emballer 2 gâteaux en même temps
Toutes les minutes, un relevé affiche le nombre de gâteaux terminés ainsi que le nombre de gâteaux à chaque étape de la confection.
Attendu :

Pas de limite ou contrainte dans l'utilisation de librairies ou package.
Ecrire un code sans bug, attention aux fuites mémoire. Tout sera lu et observé.
Attention donc également au nommage, à la qualité du code produit.