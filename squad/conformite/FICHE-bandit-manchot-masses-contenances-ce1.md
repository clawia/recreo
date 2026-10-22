# Fiche de conformite - bandit-manchot-masses-contenances-ce1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-masses-contenances.yaml` (21 items, type `question`,
  aucun texte support).
- **Entree de catalogue** : `bandit-manchot-masses-contenances-ce1`, titre servi *"Les masses et les contenances"*, consigne
  servie *"Compare les masses et les contenances, ou choisis la bonne unité."*, `nombreDeManches: 10`.
- **Ligne declaree** : `MAT-CE1-10`. **Story** : `S57-BR-MAT-CE1-10`.
- **Competence programme visee** : `squad/programme/mathematiques/competences-ce1.md`, `MAT-CE1-10`, *"Comparer et mesurer des
  masses et des contenances (gramme, kilogramme, litre)."* Plafond `en cours`.
- **Conditions opposables** : `MAS-CE1-1` a `MAS-CE1-6` (`ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md`, 3.4) ;
  `arbitrage-nadia-preparation-palier-3-ce1.md` 4.1 et 4.3 (mention exacte et refus ecrits d'avance) ;
  `arbitrage-nadia-palier-4.md` 3.2.
- **Statut global** : **VALIDE**, prononce au rejeu du 2026-10-22 par `nadia-prof` (s57, palier 4, instance B), sur `c29d1d26` ;
  VALIDE SOUS RESERVE au premier passage (`fc6ee0a1`), reserves levees. Ligne
  `en cours`. Fiche ouverte le 2026-10-22 par `nadia-prof`.
- **Derniere mise a jour** : 2026-10-22, rejeu.

---

## Lecture complete

**lecture complete : ce1-masses-contenances, 21 items lus** - 2026-10-22, 11:34 (UTC+02:00), lu par `date`, sur la branche
`s57-lea-mat-ce1-10` a `fc6ee0a1` (`grep -c "bonneReponse:"` rend 21). Lus : les 21 enonces, bonnes reponses, 42 distracteurs
et 21 indices, l'en-tete, l'entree du catalogue, la Javadoc de `PaquetCe1MassesContenancesTest` (ce qu'il ne garde pas).

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

### Verdict du 2026-10-22 (`nadia-prof`, s57, palier 4, instance B) : **VALIDE SOUS RESERVE**

**Done pedagogique prononce sur `fc6ee0a1`**, sommet de `s57-lea-mat-ce1-10`. **La ligne `MAT-CE1-10` reste `en cours`**, jamais
`couvert-valide`. `camille-po` ne la fait bouger qu'avec, dans la cellule, la mention exacte : *"comparer des masses et des
contenances et utiliser le gramme, le kilogramme et le litre, par du texte ; 'mesurer' reste non couvert, aucune balance ni aucun
recipient gradue n'est rendu"* (`arbitrage-nadia-preparation-palier-3-ce1.md` 4.3). Sans elle, ce Done ne fait pas bouger la ligne.

**Commit joue** : jar construit dans mon worktree d'audit a `fc6ee0a1` (`-pl bootstrap -am package -DskipTests`, mesure
partielle), port 18513, *"Tomcat started on port 18513"* et PID lus dans mon journal, arrete par PID. **Jeu par moi**, par
requetes HTTP sur le jar (pas de navigateur) : 8 parties de 10 manches, les 21 enonces servis (21 sur 21), aucun enonce inconnu du
paquet. Joues : deux erreurs (indice montre a chacune), revelation *"Appuie sur la bonne réponse : ..."*, un appui faux apres la
revelation (la revelation reste), la bonne reponse ; fin de partie *"Tu as trouvé, tout seul : 9"* sur 10. **Non fait par moi** :
le releve d'ecran a 744 x 551 (`ines-design`) ; le pli n'est donc pas lu (reserve non bloquante s'il coupe, 4.1 point 4).

**Verifie** :

- `MAS-CE1-1` : 12 items de masse (4 choix d'unite, 3 conversions, 5 comparaisons), 9 de contenance (4 choix, 5 comparaisons) ;
  plancher 7 tenu des deux cotes.
- `MAS-CE1-2` : *g*, *kg*, *L* seuls, le litre toujours en majuscule. Refus d'avance non atteint.
- `MAS-CE1-3` : chaque comparaison donne les trois valeurs chiffrees avec leur unite.
- `MAS-CE1-4` : plus grand nombre 1000 ; conversions 1 kg = 1000 g et un demi-kilogramme = 500 g seules ; valeurs plausibles
  (pomme 150 g, chat 4 kg, baignoire 150 L).
- `MAS-CE1-5` : une seule unite par item ; choix d'unite en trois noms sans nombre.
- `MAS-CE1-6` : aucun indice ne fait deduire la masse de la taille (refus d'avance non atteint) ; rangs recomptes a la main sur les
  13 items de comparaison et de conversion : haut 5 (12, 15, 17, 19, 9), bas 5 (13, 16, 18, 21, 10), milieu 3 (14, 20, 11),
  soit 38 %, 38 %, 23 %.
- Exactitude : les dix comparaisons et les trois conversions recalculees, aucune erreur de fait. Aucun personnage ; aucun item en
  *tu* ne suppose un objet possede.

**Reserves, non bloquantes, a faire en stories par `camille-po`** :

1. **Item 11** (*"Un demi-kilogramme, combien de g ?"*) : le distracteur *100 g* n'est d'aucune famille de `MAS-CE1-6`. *1000 g*
   en est une (le demi oublie). Exigence : remplacer *100 g* par une erreur de facteur, par exemple *50 g* ; le rang reste au
   milieu. Je n'en fais pas un refus : la valeur est fausse sans etre absurde, et l'item ne dit rien de faux a l'enfant.
2. **Neuve, datee du 2026-10-22** : sur 10 comparaisons, 5 se resolvent sans lire les nombres, par ce que l'enfant sait de la
   taille des objets : 14 (chien, chat, lapin), 15 (valise), 16 (gomme), 18 (gourde), 19 (baignoire). C'est le geste de
   `MAT-CP-08`, que `MAS-CE1-3` ecarte en exigeant des valeurs chiffrees : la condition est tenue a la lettre, l'effet qu'elle
   visait ne l'est qu'a moitie. Exigence : au moins la moitie des comparaisons ou les nombres ne suivent pas la taille habituelle
   des objets (un petit objet plus lourd qu'un grand, deux objets de taille voisine). Ce n'est pas une barre relevee sur
   `MAS-CE1-3` : c'est une reserve nouvelle.
3. Indice des items 18 et 21, *"Le plus petit nombre est celui qui contient le moins."* : un nombre ne contient rien. Exigence :
   *"L'objet qui a le plus petit nombre contient le moins."*

**Reponses aux questions routees par `lea-dev`** :

- **Q1, indices des items 1 et 2** (*"Un kilogramme, c'est mille grammes."*) : recevables. Ils disent que le kilogramme est la
  grande unite, sans rien dire de la taille de l'objet ; ils different de ceux des items 3 et 4.
- **Q2, distracteurs du demi-kilogramme** : reserve 1.
- **Q3, 21 items ou 13** : 13. Le rang d'un choix d'unite n'est pas fixe par le contenu (trois noms d'unite, aucun ordre que ma
  condition donne) ; mon arbitrage de s43, 3.5, dit qu'il *"se lit comme le garde-fou le lit"*, et le garde-fou ne lit pas les
  mots. *"Sur le paquet entier"* veut dire *pas section par section*, pas *ranger des mots sans ordre*. C'est une precision de mon
  texte, pas un changement : la condition tient sur 13.
- **Q4, titre et consigne** : recevables. Le titre ne porte pas le niveau. La consigne nomme deux des trois taches ; les trois
  conversions se lisent dans leur enonce (*"combien de g ?"*), je n'en fais pas une reserve.

**Je reste la seule garde** : la plausibilite d'une valeur pour un objet connu ; qu'une comparaison ne se resolve pas par la
taille des objets (reserve 2) ; la famille d'un distracteur de conversion ; qu'aucun item ne serve *mesurer*.

**Signe** : `nadia-prof`, 2026-10-22.

---

## Rejeu du 2026-10-22 (`nadia-prof`, s57, palier 4, instance B, `RF13`)

**lecture complete : ce1-masses-contenances, 21 items lus** - 2026-10-22, 13:23 (UTC+02:00), lu par `date`, sur la branche
`s57-lea-mat-ce1-10` a `c29d1d26` (`grep -c "bonneReponse:"` rend 21). `git diff --name-only fc6ee0a1 c29d1d26` nomme le paquet :
le rejeu est du. Lus : les 21 items, l'en-tete, le diff du paquet depuis `fc6ee0a1` (items 11, 14, 15, 16, 18, 19, 21).

### Verdict du rejeu, 2026-10-22 (`nadia-prof`) : **VALIDE**

Le verdict du premier passage reste ecrit au-dessus ; celui-ci s'y ajoute.

**Done pedagogique prononce sur `c29d1d26`**, sommet de `s57-lea-mat-ce1-10`. **La ligne `MAT-CE1-10` reste `en cours`.**

**Commit joue** : `c29d1d26`, jar construit dans un worktree detache de mon cru a ce commit (`-pl bootstrap -am package
-DskipTests`, mesure partielle), port 18513, *"Tomcat started on port 18513"* et PID lus dans mon journal, arrete par PID. Jeu par
moi, requetes HTTP : 8 parties de 10 manches, 21 enonces servis sur 21, aucun enonce inconnu du paquet ; les items changes servis
dans plusieurs ordres de propositions.

**Reserves du premier passage, levees** (verifie) :

1. Item 11 : distracteurs *50 g* (erreur de facteur 10) et *1000 g* (le demi oublie), deux familles de `MAS-CE1-6` ; rang de la
   bonne reponse au milieu, inchange.
2. Comparaisons : 14 (lapin 3 kg, chat 4 kg, chiot 2 kg), 15 (livre 600 g plus lourd qu'un oreiller de 400 g), 16 (stylo, feutre,
   crayon), 18 (pichet, cruche, theiere), 19 (saladier 5 L plus qu'une marmite de 4 L). Au moins 5 comparaisons sur 10 ne se
   resolvent plus par la taille des objets (12, 13, 14, 15, 16, 19, 20) : la barre que j'avais ecrite est atteinte. L'item 18 se
   devine encore en partie (une theiere est petite) ; je ne le demande pas, la barre etait la moitie.
3. Indices des items 18 et 21 : *"L'objet qui a le plus petit nombre contient le moins."*

**Retenu apres le rejeu** : `MAS-CE1-1` (12 masses, 9 contenances) ; `MAS-CE1-2` (*g*, *kg*, *L* majuscule) ; `MAS-CE1-4` (plus
grand nombre 1000, valeurs plausibles) ; `MAS-CE1-5` ; `MAS-CE1-6`, rangs recomptes a la main sur les 13 items : haut 5 (9, 12,
15, 17, 19), bas 5 (10, 13, 16, 18, 21), milieu 3 (11, 14, 20). Exactitude des sept items changes recalculee : aucune erreur.

**Pli** : non remesure par moi ni, a ma connaissance, sur ce commit. Au premier passage, `ines-design` mesurait 0 item sur 21
sous le pli apres une erreur (`089fb477`, sur `fc6ee0a1`) ; les enonces changes ont la meme longueur que ceux qu'ils remplacent.
Si le releve du commit fusionne montre un indice sous le pli, c'est une reserve non bloquante (4.1 point 4).

**Cellule de matrice** (question routee) : **oui**, la mention exacte est a ecrire par `camille-po` dans la cellule de `MAT-CE1-10`
avant que la ligne bouge, ou dans le meme commit : *"comparer des masses et des contenances et utiliser le gramme, le kilogramme et
le litre, par du texte ; 'mesurer' reste non couvert, aucune balance ni aucun recipient gradue n'est rendu"*
(`arbitrage-nadia-preparation-palier-3-ce1.md` 4.3, critere 5 de la story). Sans elle, ce Done ne fait pas bouger la ligne.

**Marge** : ce rejeu (`RF13`, paquet change apres mon jeu) consomme la marge de B (0,5).

**Je reste la seule garde** : la plausibilite des valeurs ; qu'une comparaison ne se resolve pas par la taille des objets ; la
famille d'un distracteur de conversion ; qu'aucun item ne serve *mesurer*.

**Signe** : `nadia-prof`, 2026-10-22.
