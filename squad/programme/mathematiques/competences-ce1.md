# Référentiel de compétences - Mathématiques - CE1 (cycle 2)

> Socle réaliste de départ, **non exhaustif et non officiel** : à compléter et faire valider par
> l'utilisatrice au fil des sprints, en lien avec `squad/programme/MATRICE-couverture.md`. Ne
> remplace pas une lecture des programmes officiels de l'Éducation nationale.

**Codes de ligne.** Chaque compétence porte un code entre crochets, alloué une fois et
jamais réutilisé : c'est par lui qu'un paquet de contenu déclare la ligne qu'il sert, et
par lui que le nombre d'items servant une ligne se compte (`ADR-0081`). Reformuler un
intitulé ne change pas son code ; insérer une ligne prend le numéro libre suivant, sans
renuméroter les autres. Le code n'est donc pas un rang, même si les premiers ont été
alloués dans l'ordre de lecture.

**Écrit par `nadia-prof` le 2026-09-21**, sur le routage de `DEC-0089` (section 3), qui ouvre le
niveau CE1 et interdit explicitement à tout autre agent de fixer une compétence CE1. Le CE1 est la
**deuxième année du cycle 2** : l'enfant y consolide ce que le CP a ouvert et y aborde le passage à
la centaine, la multiplication et la mesure instrumentée. Ce référentiel se lit donc contre
`squad/programme/mathematiques/competences-cp.md`, dont il prolonge plusieurs lignes sans les
reprendre.

**Complété le 2026-09-21** par `MAT-CE1-18` (sens de la division), ligne que j'avais signalée
absente le jour même en relisant ce document
(`squad/sprints/s36-ouverture-ce1-mathematiques-seule-cm2-differe/verdicts-nadia.md`, section 3) et
que `camille-po` a choisi de faire ajouter tout de suite plutôt que d'en faire une dette
(`DEC-0089`, note du 2026-09-21). Elle prend le numéro libre suivant : les dix-sept lignes déjà
écrites ne sont pas renumérotées, et `MAT-CE1-08` n'est pas reformulée - il manquait une ligne, il
n'y avait pas d'erreur dans ce qui était écrit.

## Nombres et calculs

- [MAT-CE1-01] Dénombrer, lire, écrire et décomposer les nombres jusqu'à 1000 (unités, dizaines,
  centaines).
- [MAT-CE1-02] Comparer, ranger et encadrer les nombres jusqu'à 1000, et les placer sur une ligne
  graduée.
- [MAT-CE1-03] Calcul mental - additions et soustractions dans la limite de 100 (compléments à la
  dizaine, doubles et moitiés).
- [MAT-CE1-04] Poser et effectuer une addition avec retenue (nombres à deux ou trois chiffres).
- [MAT-CE1-05] Poser et effectuer une soustraction (nombres à deux ou trois chiffres).
- [MAT-CE1-06] Comprendre le sens de la multiplication (addition réitérée, disposition en lignes et
  en colonnes).
- [MAT-CE1-07] Calcul mental - tables de multiplication de 2, 5 et 10, puis de 3 et 4.
- [MAT-CE1-08] Résoudre des problèmes d'addition, de soustraction et de multiplication à une ou
  deux étapes.
- [MAT-CE1-18] Comprendre le sens de la division en résolvant des problèmes de partage et de
  groupement (sans technique opératoire posée).

## Grandeurs et mesures

- [MAT-CE1-09] Mesurer et comparer des longueurs avec une règle graduée ; utiliser le centimètre et
  le mètre.
- [MAT-CE1-10] Comparer et mesurer des masses et des contenances (gramme, kilogramme, litre).
- [MAT-CE1-11] Lire l'heure sur une horloge (heures, demi-heures, quarts d'heure) et se repérer
  dans le calendrier (mois, date).
- [MAT-CE1-12] Calculer une durée simple en heures entières ou en demi-heures.
- [MAT-CE1-13] Utiliser la monnaie : composer une somme en euros et en centimes, savoir ce qui est
  rendu.

## Espace et géométrie

- [MAT-CE1-14] Se repérer, se déplacer et coder un déplacement sur un quadrillage.
- [MAT-CE1-15] Reconnaître, décrire et tracer des figures planes (carré, rectangle, triangle,
  triangle rectangle, cercle) à la règle et sur quadrillage.
- [MAT-CE1-16] Reconnaître et nommer des solides usuels (cube, pavé droit, boule, cylindre, cône).
- [MAT-CE1-17] Reconnaître un axe de symétrie et compléter une figure par symétrie sur quadrillage.

## Ce que ce référentiel ne dit pas, et où c'est écrit

**La longueur d'un énoncé de CE1 n'est pas une compétence, c'est une condition de lisibilité**, et
elle est écrite ailleurs plutôt que glissée ici :
`squad/conformite/ARBITRAGE-s36-longueur-d-enonce-au-ce1.md`, du 2026-09-21, y pose `LON-CE1-1` (au
plus vingt mots) et `LON-CE1-2` (au plus trois phrases, la dernière étant la question). Un paquet de CE1 qui sert l'une des lignes ci-dessus
doit tenir ces deux bornes en plus de la compétence qu'il déclare.

**Aucun intitulé ci-dessus ne devient un titre de jeu.** Un titre de jeu ne porte jamais le niveau
de son paquet (`ADR-0078`) ; ces intitulés nomment une compétence pour l'adulte qui pilote la
couverture, pas un jeu pour l'enfant.
