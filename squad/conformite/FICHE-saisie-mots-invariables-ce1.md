# Fiche de conformite - saisie-mots-invariables-ce1

- **Mecanique** : saisie.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-mots-invariables.yaml` (24 items, type `saisie`,
  aucun texte support).
- **Entree de catalogue** : `saisie-mots-invariables-ce1`, titre servi *"Écris le petit mot"*, consigne servie *"Lis la
  phrase, puis écris le mot qui manque."*, `nombreDeManches: 10`.
- **Ligne declaree** : `FRA-CE1-21`. **Story** : `S57-BR-FRA-CE1-21` et son amendement du 2026-10-21 (criteres 9 a 14).
- **Competence programme visee** : `squad/programme/francais/competences-ce1.md`, `FRA-CE1-21`, *"Orthographier les mots
  invariables et les mots fréquents étudiés."*
- **Conditions opposables** : `arbitrage-nadia-q05-q10.md` 4.3, points 1 a 5 ; `MIV-CE1-1` et `MIV-CE1-3`
  (`ARBITRAGE-PREALABLE-s48-francais-ce1.md`, 5.12) ; `TRV-CE1-1` a `-7` ; `arbitrage-nadia-preparation-palier-3-ce1.md` 4.1 ;
  `arbitrage-nadia-palier-4.md` 3.1 et 4 (refus ecrit d'avance : *"un item ou un autre mot, bien ecrit, complete la phrase"*).
- **Statut global** : **REFUSE**, prononce le 2026-10-22 par `nadia-prof` (s57, palier 4, instance A). Fiche ouverte le
  2026-10-22 par `nadia-prof`.
- **Derniere mise a jour** : 2026-10-22.

---

## Lecture complete

**lecture complete : ce1-mots-invariables, 24 items lus** - 2026-10-22, 11:26 (UTC+02:00), lu par `date`, sur la branche
`s57-marc-fra-ce1-21` a `56e9fd1b` (`grep -c "formeDeReference:"` rend 24). Lus : les 24 enonces, les 24 formes de
reference, la seule forme acceptee non vide (item 3, `aujourd’hui`, octets `342 200 231` lus par `od -c` : U+2019), les 24
indices, l'en-tete, l'entree du catalogue ; `rapport-marc-dev-palier-4-lp1.md` (sections 1 a 5).

**`OV9` : l'amendement de `S57-BR-FRA-CE1-21` tient ma condition 5.2**, avec un ecart que le lot a ferme. Ma condition
demandait trois choses : (a) les 24 enonces reecrits pour que le sens n'admette qu'un mot : **tenu** (critere 9, refus ecrit
d'avance repris mot pour mot) ; (b) le YAML livrable du lot : **tenu** (*"ses 24 enonces sont reecrits dans le lot"*) ;
(c) *"le remede de `QP1` choisi"* : **non tenu par l'amendement**, qui laisse le choix au developpeur et a `theo-archi` et
porte la recommandation de `theo-archi` (`P1`) sans la trancher. Le lot l'a tranche : `P1`, deux apostrophes. Je n'en fais pas
un motif : ce que (c) protegeait (un lot engage sans remede) n'est pas arrive.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

### Verdict du 2026-10-22 (`nadia-prof`, s57, palier 4, instance A) : **REFUSE**

**Commit joue** : jar construit dans mon worktree d'audit a `56e9fd1b` (`-pl bootstrap -am package -DskipTests`, mesure
partielle), port 18511, *"Tomcat started on port 18511"* et PID lus dans mon journal, arrete par PID. `git diff --name-only
83035447 56e9fd1b` ne nomme qu'un test et deux rapports : le contenu et le code joues sont ceux de `83035447`. **Jeu par moi**,
par requetes HTTP sur le jar (pas de navigateur) : 8 parties de 10 manches, les 24 enonces servis (24 sur 24), plus une partie
pour l'item 3. Joues : le mot attendu en capitales entre blancs (juste), une faute (refusee, indice montre), une validation a
champ vide (refusee, comptee comme un essai), trois fautes (revelation *"Tu as écrit : ... On écrit : ..."* et bouton
*Continuer*), *aujourdhui* sans apostrophe (refuse), *aujourd’hui* avec U+2019 (juste), et un autre mot juste sur les cinq
items de M1. **Non fait par moi** : le releve d'ecran a 744 x 551 (`ines-design`) ; aucun clavier de tablette.

**Motifs du refus, chacun actionnable sans me redemander :**

**M1 - Cinq enonces admettent un autre mot juste** (condition 4.3 point 1 ; refus ecrit d'avance de
`arbitrage-nadia-palier-4.md` 3.1, critere 9 de la story). Chacun joue : le mot ci-dessous, bien ecrit, recoit *"Ce n'est pas
ça. Essaie encore !"*.

| Item | Enonce | Attendu | Autre mot juste, joue et refuse |
|---|---|---|---|
| 3 | *Hier, c'était lundi, demain ce sera mercredi : ___, c'est mardi.* | aujourd'hui | *maintenant* |
| 7 | *Les enfants sortent de la classe et jouent ___, dans la cour.* | dehors | *ensemble* (joue) ; *là* aussi |
| 12 | *Chloé lit du début à la fin de la récréation : elle lit ___ la récréation.* | pendant | *toute* |
| 15 | *Après trois heures d'attente, le train arrive ___ !* | enfin | *maintenant* |
| 16 | *Il ne manque qu'une goutte pour que le verre soit plein : il est ___ plein.* | presque | *bientôt* |

Ce qu'il faut : pour chacun, un enonce ou le seul mot qui complete le sens est le mot attendu, ou un autre mot attendu. Accepter
le second mot en `formesAcceptees` n'est pas un remede : ce champ porte des graphies du mot vise (point 3), pas un autre mot.

**M2 - *combien* (item 18) est exclu par `MIV-CE1-1`.** La condition exclut *"les mots qu'une regle d'une autre ligne decide
(... `m` devant `m`, `b`, `p` ..., `FRA-CE1-22`)"*. Dans *combien*, le son de *on* s'ecrit *om* parce que la lettre qui suit est un
*b* : c'est la regle de `FRA-CE1-22`. L'en-tete du paquet affirme *"Aucun mot decide par une regle d'une autre ligne (... m
devant m b p ...)"* : c'est faux pour ce mot. Ce qu'il faut : un autre mot sur l'item 18, et l'en-tete juste.

**A corriger dans le meme rejeu, sur une condition deja ecrite** : `MIV-CE1-1` demande une *"liste declaree en tete"* ;
l'en-tete dit *"vingt-quatre mots cibles distincts"* sans les ecrire. Ecrire la liste des mots finaux.

**Ce qui tient** (verifie, a ne pas defaire au rejeu) :

- Les 19 autres enonces : je n'ai trouve aucun autre mot juste. Examines et juges fermes, entre autres : item 1 (*bien* n'a pas
  de sens hors contexte), 5 (*avant* pour une place dans une file est maladroit, *devant* est le contraire nomme), 6 (*là* ne
  suit pas *à l'intérieur de la boîte*), 8 (*beaucoup* est agrammatical sans *de*), 9 (*près* ne dit pas *entourent*),
  19 (*où*, *comment* n'ont pas de sens pour une date).
- Point 2 : aucun mot attendu ne porte d'accent. Point 3 : la seule forme acceptee est *aujourd’hui* en U+2019, graphie juste
  du mot ; `QP1` est tenu par `P1`, joue. Point 4 : `type: saisie`, aucun `DEGRADE`. `NRC-CE1-1` contre `cp-mot-dicte` :
  aucun des 12 mots repris. `MIV-CE1-3` : chaque mot est la reponse d'un seul item.
- `TRV-CE1-6` : dix prenoms, cinq et cinq (Inès, Mia, Léa, Chloé, Sofia ; Omar, Tom, Karim, Ethan, Jules), origines variees,
  aucun role distribue par genre ; les deux items en *toi* (nom, anniversaire) ne supposent aucune configuration familiale.
- Exactitude : hier lundi, demain mercredi, aujourd'hui mardi ; mardi entre lundi et mercredi ; le jour avant mercredi est
  mardi. Aucune erreur de fait.
- Revelation apres trois essais, forme juste montree ; la casse et les blancs ne comptent pas.

**Reserves, non bloquantes, a reprendre au rejeu si le developpeur touche ces items** :

1. Indice de l'item 1 : *"aucun ne fait exception"* ; *exception* est un mot que l'enfant de CE1 lit mal seul.
2. Indice de l'item 8 : *"tous les endroits de la cour"* ; l'enonce ne nomme pas la cour.

**Constat, pas un motif** : une validation a champ vide compte comme un essai et entre dans la liste des tentatives. C'est la
mecanique servie depuis `cp-mot-dicte` (`SAI-12`, arbitrage D5) ; ce lot n'y touche pas, je ne l'oppose pas a ce lot.

**Reponses aux questions routees par `marc-dev`** (`rapport-marc-dev-palier-4-lp1.md` 5.1) :

- **Items a risque de sa section 3** : 3 et 15 (*maintenant*) sont dans M1 ; 6 (*ici*, *là*) ferme ; 7 dans M1, mais pour
  *ensemble* et *là*, pas pour *ailleurs* ou *ici* ; 8 (*beaucoup*) ferme : *"il y a des feuilles beaucoup"* n'est pas une
  phrase juste ; 19 (*où*, *comment*) ferme. Deux items qu'il disait fermes ne le sont pas : 12 (*toute*) et 16 (*bientôt*).
- **Liste de `MIV-CE1-1`** : la condition ne fixe pas vingt-quatre mots. Elle demande *"au moins quinze mots distincts"*, une
  liste declaree en tete, et des exclusions. Changer des mots est permis sans amender la condition ; *combien* tombe sous
  l'exclusion (M2), et la liste doit etre ecrite en tete.
- **Indice de l'item 3**, *"Il porte une apostrophe."* : recevable. Il dit ou regarder dans le mot, il ne l'ecrit pas, et il
  vient apres une erreur (`TRV-CE1-5`). Il reste recevable si l'item 3 garde *aujourd'hui* avec un enonce reecrit.

**Ce qui suit** : le lot revient a `marc-dev` par `camille-po` ; mon rejeu est sur la marge de A (0,5). Au rejeu, je rejoue les
items changes et les cinq de M1, sur le jar du commit qui sera fusionne.

**Je reste la seule garde** : qu'aucun enonce n'admette un autre mot juste (aucun test ne lit le sens) ; qu'un mot ne soit pas
decide par la regle d'une autre ligne (aucun test ne lit une graphie) ; ce que fait le clavier de la tablette (le produit ne le
voit pas ; reserve de l'utilisatrice, aucun appareil dans la squad).

**Signe** : `nadia-prof`, 2026-10-22.

---

## Rejeu du 2026-10-22 (`nadia-prof`, s57, palier 4, instance A, marge de rejeu)

**lecture complete : ce1-mots-invariables, 24 items lus** - 2026-10-22, 13:17 (UTC+02:00), lu par `date`, sur la branche
`s57-marc-fra-ce1-21` a `24a9315b` ; contenu et code a `ea34c750` (`git diff --name-only ea34c750 24a9315b` ne nomme que le
rapport du lot). `grep -c "formeDeReference:"` rend 24. Lus : les 24 enonces, formes, indices, l'en-tete et sa liste
`MIV-CE1-1`, le diff du paquet depuis `56e9fd1b`.
