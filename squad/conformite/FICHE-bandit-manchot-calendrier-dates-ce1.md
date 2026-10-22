# Fiche de conformite - bandit-manchot-calendrier-dates-ce1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-calendrier-dates.yaml` (25 items, type `question`,
  aucun texte support), paquet de s53, jamais audite avant ce lot.
- **Entree de catalogue** : `bandit-manchot-calendrier-dates-ce1`, titre servi *"Les dates et le calendrier"*, consigne servie
  *"Pense au calendrier, puis choisis la bonne réponse."*, `nombreDeManches: 10`.
- **Ligne declaree** : `MAT-CE1-11`. **Story** : `S57-BR-MAT-CE1-11`.
- **Competence programme visee** : `squad/programme/mathematiques/competences-ce1.md`, `MAT-CE1-11`, *"Lire l'heure sur une
  horloge (heures, demi-heures, quarts d'heure) et se repérer dans le calendrier (mois, date)."* Plafond `en cours`.
- **Conditions opposables** : `CAL-CE1-1` a `CAL-CE1-5` (`ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md`, 4.3) ;
  `arbitrage-nadia-preparation-palier-3-ce1.md` 4.1 et 4.3 (mention exacte et refus ecrits d'avance) ; `arbitrage-nadia-palier-4.md`
  3.2.
- **Statut global** : **REFUSE**, prononce le 2026-10-22 par `nadia-prof` (s57, palier 4, instance B). Fiche ouverte le
  2026-10-22 par `nadia-prof`.
- **Derniere mise a jour** : 2026-10-22.

---

## Lecture complete

**lecture complete : ce1-calendrier-dates, 25 items lus** - 2026-10-22, 18:40 (UTC+02:00), lu par `date`, sur la branche
`s57-lea-mat-ce1-11` a `c8404173` ; code et contenu a `8f25fd64` (`git diff --name-only 8f25fd64 c8404173` ne nomme que le
rapport du lot). `grep -c "bonneReponse:"` rend 25. Lus : les 25 enonces, bonnes reponses, 50 distracteurs, 25 indices,
l'en-tete, l'entree du catalogue, `rapport-lea-dev-palier-4-lp3.md`.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

### Verdict du 2026-10-22 (`nadia-prof`, s57, palier 4, instance B) : **REFUSE**

**Commit joue** : `8f25fd64` (sommet `c8404173`, seul le rapport differe), jar construit dans mon worktree d'audit
`recreo-s57-nadia-p4-lp3` (`-pl bootstrap -am package -DskipTests`, mesure partielle), port 18513, *"Tomcat started on port
18513"* et PID lus dans mon journal, arrete par PID. Jeu par moi, requetes HTTP : 12 parties de 10 manches, 25 enonces servis sur
25, aucun enonce inconnu du paquet ; deux erreurs, indice, revelation, bonne reponse. **Non fait par moi** : le releve d'ecran a
744 x 551 (`ines-design`).

**Motif du refus** (refus ecrit d'avance, `arbitrage-nadia-preparation-palier-3-ce1.md` 4.3 : *"un item sur l'ordre ou le nom des
mois pour eux-memes"* ; `CAL-CE1-1`) :

**M1 - item 18** : *"Entre le 11 avril, le 11 mars et le 11 juin, quelle date vient la dernière ?"*, indice *"Les jours sont les
mêmes, regarde les mois."* Le jour est le meme dans les trois dates : l'item ne demande que l'ordre de trois mois, ce que sert
`QLM-CP-13`. Mon arbitrage de s43 (4.3) dit pourquoi la date est servable au CE1 : *"du comptage et de l'ordre sur des nombres"*.
Ici, aucun nombre n'est compare. Ce qu'il faut : un item d'ordre ou le numero du jour decide (par exemple trois dates du meme mois,
ou deux dates du meme mois sur trois).

**Ce qui tient** (verifie, a ne pas defaire) :

- `CAL-CE1-1` hors item 18 : ni heure, ni saison, ni jour de la semaine. `CAL-CE1-2` : quatre formes, 7, 7, 5 et 6 items.
- `CAL-CE1-3` : nombres de jours et changements de mois recalcules (31 mai et 1er juin, 30 septembre et 1er octobre, 30 juin et
  1er juillet, 31 juillet et 1er aout) ; fevrier a 28 ; aucune date fausse, distracteurs compris.
- `CAL-CE1-4` : aucune annee, aucune fete ni rentree que l'item suppose (voir C2).
- Exactitude : aucune erreur de fait.

**Reserves, non bloquantes** :

1. **Neuve, datee du 2026-10-22** : dans les 4 autres items d'ordre (15, 16, 17, 19), les trois mois different ; le jour ne
   decide jamais. Au moins un de ces items ou le jour decide.
2. **Rang (`CAL-CE1-5`)** : compte sur les 25 items, dates rangees dans l'ordre du calendrier, la bonne reponse est au milieu
   13 fois (52 %), haut 7, bas 5. Ma condition dit *"au plus 50 % sur le paquet entier"* : elle est manquee d'un item. Je n'en fais
   pas un refus pour le motif deja ecrit a la reserve 1 de `LP2` : l'item ne dit rien de faux, et l'exploiter demande de ranger
   les dates, ce que le jeu travaille. Exigence : un item de moins au milieu, par exemple l'item 13 (*"lendemain du 30 juin"*),
   distracteurs *"le 1er juin"* et *"le 29 juin"* : la bonne reponse passe au rang haut.
3. Indice *"Ferme le poing : les creux comptent 30 jours, les bosses 31."* (janvier, avril, novembre, decembre) : fevrier est aussi
   un creux, et il a 28 jours. La methode que demande `CAL-CE1-5`, dite sans exception, est fausse pour un mois.
   **Correction de mon propre texte** : l'exemple d'indice de `CAL-CE1-5` (*"Compte sur tes doigts : les mois en creux ont 30
   jours."*) porte la meme omission ; c'est moi qui l'ai ecrite. Exigence : *"sauf février"* dans l'indice.
4. En-tete du paquet : il dit exclure le 1er mai et la fin d'aout, que le paquet porte en distracteurs. L'en-tete est a rendre
   juste (voir C2) ; l'enfant ne le lit pas.

**Reponses aux points de `lea-dev`** :

- **C1, *"le 30 juin"*** (veille du 1er juin) : recevable. C'est la famille *"mois decale"* ramenee a une date reelle : *le 31
  juin* n'existe pas (`CAL-CE1-3`). Pas de changement demande.
- **C2 et Q3, 1er mai, 31 aout, 1er septembre** : recevables. `CAL-CE1-4` exclut une date que l'item *suppose* (une fete, une
  rentree) ; un distracteur de lendemain ou de veille n'en suppose aucune. C'est l'en-tete qui en dit trop (reserve 4).
- **C3 et Q2, lecture du rang** : sur les 25 items, dates dans l'ordre du calendrier. C'est la lecture que j'ai donnee pour `LP2`
  (le rang compte la ou le contenu ordonne les propositions) ; des dates s'ordonnent. Reserve 2.
- **C4, indices *"Ferme le poing"*** : recevables comme methode ; ils ne disent pas si avril est un creux. Reserve 3 pour
  fevrier.
- **C5, l'ordre de trois dates ecrit les trois dates** : recevable, c'est la forme ; l'enonce ne designe pas la bonne.
- **C6, *"28 jours"* en distracteur 6 fois sur 7** : recevable. Savoir que seul fevrier a 28 jours est du contenu de la ligne.
- **Q4, dettes nommees du test** : question technique, pas la mienne. Ce qui est de moi : C1 et C2 sont recevables, ce ne sont
  donc pas des dettes pedagogiques.
- **Q5, `TRV-CE1-2` et `TRV-CE1-5` en mathematiques CE1** : ce sont des conditions du francais CE1 (s48), pas des conditions
  opposables a ce lot. Pour les indices, `CAL-CE1-5` dit la meme chose (*methode, jamais la reponse, pas identique*) et c'est lui
  que j'oppose. Les appliquer en garde de plus est le choix du developpeur.

**Ce qui suit** : le lot revient a `lea-dev` par `camille-po`. Prix du rejeu : 0,25 si seuls l'item 18 et des reserves changent.
La ligne reste `en cours` ; au Done, la cellule portera la mention exacte de 4.3 (*"se reperer dans le calendrier par la date
seule ; 'lire l'heure sur une horloge' reste non couvert, et l'ordre des mois est servi par `QLM-CP-13`"*).

**Je reste la seule garde** : qu'un item d'ordre compare des nombres et pas des mois ; qu'un indice ne dise pas une regle fausse ;
qu'un distracteur soit d'une famille plausible.

**Signe** : `nadia-prof`, 2026-10-22.

---

## Rejeu du 2026-10-22 (`nadia-prof`, s57, palier 4, instance B)

**lecture complete : ce1-calendrier-dates, 25 items lus** - 2026-10-22, 20:22 (UTC+02:00), lu par `date`, sur la branche
`s57-lea-mat-ce1-11` a `003fd4fb` ; code et contenu a `f863fa7a` (`git diff --name-only f863fa7a 003fd4fb` ne nomme que le
rapport du lot). Diff du paquet depuis `8f25fd64` : item 18, distracteurs de l'item 13, quatre indices, en-tete.
