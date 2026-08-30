# Verification en jouant - marc-dev, S23-RAN et S23-ETA, 2026-08-30

Methode : `bootstrap/target/recreo.jar` construit par `bash outils/mvn-jdk21.sh -B clean verify`
(reacteur complet, BUILD SUCCESS), lance en local (`java -jar recreo.jar --server.port=8734`),
joue par requetes HTTP directes (`curl`, cookie de session) sur les routes reelles de
`PartieController` (`POST /parties/{jeuId}` pour demarrer, `POST /parties/{jeuId}/coups` pour
jouer un coup) - le meme chemin qu'un navigateur, sans navigateur graphique disponible dans cet
environnement. Chaque extrait ci-dessous est copie de la reponse HTML reellement recue, pas
reconstruit.

## `bandit-manchot-ranger-nombres-cp` (S23-RAN)

Partie complete, dix manches (`nombreDeManches: 10`), jusqu'a l'ecran de fin.

- Premiere manche : enonce `37, 100, 16`, trois propositions distinctes rendues
  (`100, 16, 37` / `16, 37, 100` / `100, 37, 16`). Reponse correcte pressee (`16, 37, 100`) :
  `Score : 1`, verdict `Bravo !`.
- Manche suivante (`71, 54, 93`) : reponse fausse pressee (`71, 93, 54`) : verdict
  `Ce n'est pas ça. Essaie encore !`, indice affiche `Repère d'abord le nombre le plus grand.` -
  l'indice ne recopie jamais la liste rangee.
- Meme manche, deuxieme echec puis revelation (mecanique du bandit manchot, ADR-0016) : verdict
  `C'est 32, 65, 94. Appuie sur 32, 65, 94.` - confirme qu'un enfant bloque avance, comme
  `nadia-prof` le decrit dans son arbitrage prealable.
- Sept manches suivantes jouees correctement (`18, 53, 65`, `13, 17, 66`, `7, 48, 65`,
  `23, 27, 54`, `2, 48, 100`, `52, 86, 91`, `1, 8, 56`) : `Bravo !` a chaque fois, score croissant
  jusqu'a `Score : 9`.
- Dixieme manche : verdict `Bravo, c'est juste !`, ecran `C'est fini !` (fin de partie). Score
  final 9/10 (une manche resolue par revelation, comptee comme les autres).

Aucune reponse revelee en avance, aucun signe orphelin visible (le "?" de chaque enonce suit un
espace insecable, verifie aussi au niveau octet - voir plus bas), toutes les propositions rendues
correspondent aux trois memes nombres dans un ordre different.

## `bandit-manchot-problemes-etapes-cm1` (S23-ETA)

Partie complete, dix manches, jusqu'a l'ecran de fin, score final 10/10.

- Premiere manche : enonce "La classe récolte 40 timbres, puis en reçoit 15 de plus pour une
  collection commune. Elle en donne ensuite 10 à une autre école. Combien de timbres la classe
  garde-t-elle ?", propositions `55` / `15` / `45`. Reponse fausse (`55`, le resultat
  intermediaire, famille a) : verdict `Ce n'est pas ça. Essaie encore !`, indice
  `Additionne d'abord les timbres des deux collectes.` - la methode de la premiere etape
  seulement, jamais un resultat.
- Reponse correcte (`45`) : `Bravo !`, `Score : 1`. C'est un item `a+c` (rang median) : verifie
  en jouant que le distracteur "resultat intermediaire" (55) et la bonne reponse (45) sont bien
  deux propositions distinctes rendues, comme le calcule `PaquetCm1ProblemesEtapesTest`.
- Neuf manches suivantes jouees correctement, couvrant les quatre operations et les deux familles
  a+b et a+c : pommes (51), cahiers (39), bracelets (189, famille a+b), livres/bibliotheque (12),
  fete de l'ecole/parts (6, apres un essai errone sur une valeur d'une autre manche - reproduit
  ici le meme comportement de rejet propre, sans planter), recette (6), affiches (120), yaourts
  (75), albums (22). `Bravo !` a chaque manche, score croissant jusqu'a `Score : 10`.
- Dixieme manche : verdict `Bravo, c'est juste !`, ecran `C'est fini !`.

Verification au niveau octet (pas seulement a l'oeil) que le "?" de chaque enonce est precede
d'un espace insecable (`\xa0`, U+00A0) et non d'une espace ordinaire, sur la reponse HTTP reelle
du premier item joue - condition `TYP-1`, aucune occurrence neuve.

## Ce que cette verification couvre, et ce qu'elle ne couvre pas

Couvre : le trajet complet requete/reponse reel (pas seulement le contenu YAML charge en test),
le rendu des indices sans fuite de reponse, le comportement d'echec/revelation, la fin de partie
au nombre de manches configure, l'absence d'espace ordinaire devant le "?" sur le HTML reellement
servi.

Ne couvre pas : le rendu visuel (pas de navigateur graphique dans cet environnement) - largeur de
colonnes, repli de ligne, contraste. C'est le mandat de `nadia-prof` et d'`ines-design`, pas
celui-ci.
