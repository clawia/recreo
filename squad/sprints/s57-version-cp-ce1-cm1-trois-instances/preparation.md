# Preparation de s57 - duree, heure de fusion d'ADR-0065, A10 de la retro de s56, developpeur de S54-OBS-SCI-CONSIGNE, items de sofia-sm

- **Auteure** : `sofia-sm`, casquette Scrum Master, 2026-10-13 16:24 (UTC+02:00), lu par `date`. Arbre : `round-two` a `9d101ea0`, worktree dedie.
- **Ce fichier n'est pas `planning.md`.** Il ne porte aucun horodatage de debut de sprint, aucun objectif gele, aucune affectation engagee : **s57 n'est pas ouvert**
  (`DEC-0120`, en-tete et section 7). Le dossier existe parce que du travail s'y rattache des maintenant ; `outils/rapport-tokens.py` lira l'heure de debut dans
  `planning.md` quand il sera ecrit, pas dans ce fichier.
- **Mots de l'utilisatrice cites ici** : ils viennent de `DEC-0120` (A.1, A.18) et de `DEC-0116` (A.32, A.34), en forme 2, relayes par l'agent qui a lance `camille-po` ;
  **je ne les ai pas lus de sa main**.

## 0. Pourquoi `planning.md` n'est pas ecrit maintenant

`squad/README.md` section 4 : `planning.md` porte l'horodatage de debut, l'objectif gele, les stories avec competence visee, les affectations nominatives, **l'avis de faisabilite de `theo-archi`**
et la verification poste/story (`PAR-16`). Un planning ecrit avant ces elements serait une ceremonie fabriquee a moitie, et son heure de debut ouvrirait la fenetre de tokens de s57 sur un sprint qui n'a pas commence.
Etat de chaque element, mesure sur `9d101ea0` (`git ls-files | grep -i s57` : trois fichiers, `DEC-0117`, `DEC-0120` et l'arbitrage de `nadia-prof` sur les criteres de la version ; `ls squad/sprints | grep -c s57` rendait 0 avant ce dossier) :

| Element requis | Etat | Proprietaire |
|---|---|---|
| Avis de faisabilite de `theo-archi` pour s57 | **absent** | `theo-archi` |
| Arbitrage prealable de `nadia-prof` sur les lots, leurs prix et leur affectation aux instances | **absent** : seul existe son arbitrage des criteres de la version (`ARBITRAGE-PREALABLE-s57-criteres-de-la-version-cp-ce1-cm1.md`) ; `Q-N1` (0,25 contre 0,75 non affectes) sans reponse | `nadia-prof` |
| Objectif gele | **non gelable** : `DEC-0120` reste `Proposee` ; `Q-U10` (matrice figee, ou en attente du referentiel d'arts plastiques CE1) sans reponse | `camille-po`, utilisatrice |
| Developpeur de chaque poste (`PAR-16`) | partiel : `VSJ-CE1-R1` a `marc-dev` (`DEC-0120` 4.3) ; `S54-OBS-SCI-CONSIGNE` proposee en section 4 ; les lots a nommer apres l'arbitrage de `nadia-prof` | `sofia-sm` |
| `lecture-testabilite.md` avant la premiere livraison de code (action de la retro de s56, section 5) | absente | `yanis-qa` |

Ce que ce fichier fait : il prepare les points que `DEC-0120` A.36 me nomme, sans trancher le produit, l'architecture ni la pedagogie. **Le planning s'ecrit a l'ouverture, en reprenant les sections 1, 2 et 4 sous reserve de ce que les avis auront change.**

## 1. Duree de s57

### 1.1 Faits et methode

Commande : `grep -n "Duree effective" squad/sprints/s5[2-6]-*/review.md`, lancee le 2026-10-13 sur `9d101ea0`, perimetre : les cinq `review.md` de s52 a s56.

| Sprint | Duree effective (horloge) | Instances de `nadia-prof` | Consomme (source) |
|---|---|---|---|
| s52 | 13 h 10 min | deux | non lu par moi |
| s53 | 3 h 05 min | sprint de relecture | sans objet |
| s54 | 17 h 33 min | trois | 8,0 : A 3,75, B 3,25, C 1,0 (`review.md` et `RAPPORT.md` de s54) |
| s55 | 7 h 38 min | deux | 7,0 en somme des tarifs, 8,0 avec les deux marges (`review.md` de s55, section de capacite) |
| s56 | 41 h 38 min | deux | 7,25 par mon recompte, 5,75 par `cloture-nadia.md` (`review.md` de s56, section 11) |

Je retiens pour comparer **s54, s55 et s56**, les trois sprints dont le consomme approche 8,0 : 7 h 38 min, 17 h 33 min, 41 h 38 min, mediane **17 h 33 min**. s52 (consomme non lu) et s53 (relecture) sont ecartes.
**Un seul sprint a trois instances est mesure (s54).** La mediane n'est pas une loi : trois points, trois niveaux.

Pourquoi s56 est le plus long : 23 h 49 min sans livraison (`retro.md` de s56, C1), deux agents arretes, un watchdog sur `lea-dev`, un `REFUSE` suivi d'une correction. L'utilisatrice donne la cause des arrets, *"la coupure de la batterie"* (2026-10-13, `DEC-0116` A.34) ; **la part de la fenetre de 23 h 49 min due aux arrets, au watchdog ou au refus n'est pas etablie** (section 5.3).

### 1.2 Ce qui contraint la duree

1. **Pas de date.** *"pas de date"* (de l'utilisatrice, 2026-10-13, `DEC-0120` A.18). La lecture *"la version sort quand le maximum est atteint selon (c)"* est celle de `camille-po` (A.18, tableau), pas un mot de l'utilisatrice.
2. **Pas de timebox** : un sprint est un increment (`squad/README.md` section 3).
3. **8,0 par sprint, trois instances** : borne de `nadia-prof`, O1 (`ARBITRAGE-PREALABLE-s57-criteres-de-la-version-cp-ce1-cm1.md`, 4.3 ; `DEC-0120` A.28). `PAR-8` : plafond dur 5,0 par instance, trois lots au plus.
4. **Allonger un sprint n'augmente pas la borne** : `nadia-prof`, meme arbitrage, 4.4 point 2 (*"un sprint plus long qui empilerait plus de lots par instance, je le refuserais au meme motif"*).
5. **La duree laisse un rejeu** : un refus, sa correction et le rejeu tiennent dans la marge de 0,5 de chaque instance (4.4 point 3). Un lot engage a, a la cloture, un verdict ecrit ou il est un report (4.4 point 1).
6. **Le chemin vers le maximum passe par le nombre de sprints.** `DEC-0120` A.31 : plancher de travail 48,0 (hors les lots sans prix), soit 10 sprints a 5,0 de travail par sprint a trois instances. Une version sans date sort a la cloture du dernier de ces sprints, pas plus tot parce qu'un sprint a ete allonge.

### 1.3 Proposition

- **P1. s57 n'a ni date ni timebox ; sa duree est celle de son increment.** Il se clot quand chaque poste engage a un verdict ecrit de `nadia-prof` ou un report ecrit, rejeu d'`ART-CP-10` compris. Les trois instances tournent dans un seul sprint, sous un seul `planning.md`.
- **P2. On ne l'allonge pas pour absorber plus de 8,0.** Cela n'ajoute rien a la borne (1.2 point 4) et retarde la cloture, donc la mesure et le sprint suivant. Un sprint clos des que son travail est audite rapproche la version.
- **P3. Attente d'horloge, a titre d'information : 17 h 33 min (mediane), de 7 h 38 min a 41 h 38 min observees.** Seuil d'alerte propose : **41 h 38 min**, le maximum observe. Passe ce seuil avec un lot engage non audite, je le dis a l'utilisatrice le jour meme, dans une ligne du dossier du sprint (`squad/README.md`, regle du glissement d'echeance). **Le seuil ne clot rien et ne limite rien** : ce n'est pas une timebox.
- **P4. La duree se reevalue a chaque retro**, sur les durees effectives mesurees, jamais sur celle d'un seul sprint.

**Ce que je ne sais pas** : aucune mesure en heures par lot (`nadia-prof` n'en a pas non plus, 4.4 point 4) ; aucune mesure de trois instances sur du francais CE1, que s54 n'a pas porte a trois.
**Question pour l'utilisatrice (Q1)** : *"on adapte les instances et la duree car il faut sortir une version"* (2026-10-13, `DEC-0120` A.1) demandait-il un sprint plus long pour porter plus que 8,0 ? Si oui, c'est une borne de `nadia-prof` (`PAR-8`), que P2 ne change pas.

## 2. Heure de fusion de l'amendement d'ADR-0065 : sequence de planning avant A'

**Ce que le depot ecrit.** `nadia-prof`, accord sur `PAR-2` pour s57, condition 2 : *"L'amendement d'`ADR-0065` est fusionne avant le premier worktree d'un lot de A'. Aucune des deux instances n'amende `ADR-0065` ni le garde-fou de l'accent : un besoin se route a `theo-archi`."* (`ARBITRAGE-PREALABLE-s57-criteres-de-la-version-cp-ce1-cm1.md`, 4.2). `theo-archi` : *"une story de `theo-archi`, une a la fois, fusionnee avant tout lot qui en depend"* ; le seul automate est le conflit de fusion sur le fichier de test (`avis-faisabilite.md` de s56, section 9). **L'ADR est de `theo-archi` : je n'en ecris aucune ligne.**

**Sequence a copier dans `planning.md`.** Elle tient sans l'avis de `theo-archi` ; l'avis ne fait que remplir l'echeance de l'etape 2.

| # | Etape | Qui | Entree | Preuve a la sortie |
|---|---|---|---|---|
| 1 | Ligne 1 du planning, hors `PAR`, 0,0 pour `nadia-prof` : `S57-ADR-0065-DEUX-INSTANCES`, avec sa story au backlog (`PAR-16`) | `sofia-sm` (ligne), `theo-archi` (story) | ouverture du planning | la ligne et la story existent avant tout worktree |
| 2 | Amendement d'`ADR-0065` et son test dans un seul commit, sur le worktree de `theo-archi`. **Echeance : l'heure ou l'arbitrage prealable de A' est ecrit.** Tant qu'elle n'est pas fixee autrement, `theo-archi` l'ecrit dans son avis | `theo-archi` | etape 1 | commit nomme |
| 3 | Fusion sur `round-two`, lignee verifiee avant (`git merge-base --is-ancestor`), heure lue par `date` au moment de la fusion | `sofia-sm` | etape 2 | SHA de fusion et heure |
| 4 | Amendement date du planning : l'heure et le SHA de l'etape 3, dans l'heure de la fusion (precedent s56 : le planning a ete amende une fois, heure inscrite 22:17, avant les worktrees concernes) | `sofia-sm` | etape 3 | amendement date, aucun mot precedent modifie |
| 5 | **Porte** : le premier worktree d'un lot de A' ne s'ouvre qu'apres l'etape 4. A l'ouverture, `git merge-base --is-ancestor <SHA de l'etape 3> HEAD` dans ce worktree sort 0 ; la commande et le commit sont cites dans le rapport du developpeur (regle de `squad/lessons.md` : un "non ecrit" ou un "tenu" cite sa commande et son commit) | developpeur de A', `sofia-sm` (lignee) | etape 4 | sortie 0, citee |
| 6 | Pendant les etapes 2 a 4 : les arbitrages prealables, A (X), B (reprise d'`ART-CP-10`, `S54-OBS-SCI-CONSIGNE`) et `VSJ-CE1-R1` tournent. **Seuls les lots de A' attendent** | toutes | - | - |
| 7 | Glissement : si l'etape 4 n'est pas faite quand le premier lot de A' est pret, **ce lot glisse** (`DEC-0120` section 3), je le dis a l'utilisatrice le jour meme et j'ecris la ligne dans le dossier du sprint | `sofia-sm` | - | ligne datee |

**Pourquoi l'echeance est celle de l'arbitrage de A'.** Un lot de A' ne s'ouvre ni sans cet arbitrage (les prealables precedent le code : W1 de la retro de s56) ni sans l'amendement. Placer l'amendement a la meme echeance evite qu'il devienne le chemin critique sans que personne l'ait decide. Precedent mesure : a s56, les deux amendements d'`ADR` etaient fusionnes 1 h 45 min (22:21) et 1 h 58 min (22:34) apres l'heure de debut du planning (20:36), avant le premier commit du paquet a 23:34 (`retro.md` de s56, W1). C'est un precedent, pas un engagement : le temps de l'amendement de s57 est a `theo-archi`.

**Verification poste/story (`PAR-16`) a la cloture du planning** : l'etape 1 a une story et un proprietaire ; la porte de l'etape 5 depend d'un artefact (la fusion) que **l'etape qu'elle precede ne produit pas** (regle de `squad/lessons.md`, cas de `Q7` a s56).

## 3. La seconde ligne A10 de la retro de s56

**Fait.** `squad/sprints/s56-paquet-art-cp-10-et-francais-ce1-13/retro.md` porte deux lignes `A10` : en section 3, l'action de la retro de s55, *"Verification de s54 (trois instances) contre les formes (a), (b), (c)"* ; en section 5, une action de s57, *"Ecrire dans le depot les deux arrets d'agent et le watchdog"*. Commande : `grep -n "^| A10" squad/sprints/s56-*/retro.md` (deux lignes).

**Regle du depot.** `squad/README.md` section 2 : *"un fichier de ceremonie close ne recoit plus rien"* ; l'exception de s22 exige, condition 3, qu'*"aucun mot du texte precedent n'est modifie ni retire"*. Renumeroter une ligne modifie un mot. `retro.md` de s56 est close (`review.md` et `RAPPORT.md` de s56 ecrits). **Je ne la renumerote pas et je n'y ajoute rien.** `nadia-prof` a pris la meme position (arbitrage de s57, 4.1 : *"Je n'edite pas une retro close"*).

**Correction, hors de la retro** : la correspondance ci-dessous fait foi pour s57 et pour toute citation ulterieure.

| Designation | Ligne de la retro de s56 | Objet | Etat |
|---|---|---|---|
| `A10` de la section 3 (action de s55) | section 3 | verification de s54 contre les formes (a), (b), (c) | **ecrite par `nadia-prof`** : condition (ii) non remplie pour s54, une occurrence de la forme (c) (arbitrage de s57, 4.1) |
| `A10` de la section 5 (action de s57) | section 5 | ecrire les arrets d'agent et le watchdog | **ecrite dans ses limites**, section 5.3 de ce fichier |

**Renvois verifies** (`grep -rn "A10"` sur `DEC-0116`, `DEC-0120`, le journal de gouvernance hors sprint, le backlog, `planning.md`, `review.md` et `RAPPORT.md` de s56, `lessons.md`, les arbitrages de `nadia-prof` de s56 et s57, le 2026-10-13) : chaque renvoi se lit sans ambiguite par son libelle. Ceux de la verification disent *"verification"* ou *"formes (a), (b), (c)"* (`DEC-0116` section 6, A.6 et A.18 point 4 ; `planning.md`, `review.md`, `RAPPORT.md` de s56 ; `DEC-0120` A.5 et A.28 ; arbitrage de s57, 4.1). Ceux des arrets disent *"`retro.md` `A10`"* a propos des arrets (`DEC-0116` A.29, A.34, A.36 ; journal de gouvernance hors sprint ; `DEC-0120` A.36 ; backlog, annotation de `DEC-0120` ; arbitrage de s57, 4.1 et tableau de routage). **Aucun renvoi ne pointe vers la mauvaise ligne** ; aucun fichier n'est corrige. A partir de maintenant, une citation de la seconde s'ecrit *"`A10` de la section 5 de `retro.md` de s56"*.

## 4. Developpeur de S54-OBS-SCI-CONSIGNE (B, 0,25)

**Le poste.** Retirer la premiere phrase des 12 enonces de `cm1-sciences-extraire-information.yaml` (chacun commence par *"D'après ce tableau,"* ou *"D'après ce texte,"*) ; la consigne du catalogue ne change pas ; `git diff` ne touche que les 12 lignes `enonce:` ; rejeu par `nadia-prof`, instance B (`DEC-0120` A.34, criteres de `nadia-prof`). Etat mesure : `grep -c "puis réponds à la question"` sur le paquet rend 12 (2026-10-13, `9d101ea0`). Tests qui lisent ce paquet : `PaquetCm1SciencesExtraireInformationTest`, `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`, `TexteAccentueExposeParLePortEntrantTest` (`grep -rl "cm1-sciences-extraire-information" --include=*.java`).

**Proposition : `sofia-sm`, sous la casquette developpeuse, dans une tache a part.** Motifs :

1. **Charge.** s57 compte six postes de developpement pour trois developpeurs : un lot de A, `VSJ-CE1-R1`, deux lots de A', la correction d'`ART-CP-10`, ce poste. Tours de s56 (`RAPPORT-tokens.md` de s56) : `lea-dev` 529, `marc-dev` 183, `sofia-sm` 192. `lea-dev` porte la correction d'`ART-CP-10` (auteur du paquet, forme avec `ines-design` et `theo-archi`), le poste le plus risque ; `marc-dev` porte `VSJ-CE1-R1` et le chemin critique, le francais CE1.
2. **Perimetre disjoint.** Un seul fichier de contenu, sans lien avec ceux d'`ART-CP-10` ni du francais CE1 : aucun conflit de fusion avec les deux autres developpeurs.
3. **Critere deja rouge.** Le critere 1 rend 12, donc le test d'acceptation existe avant le code (`DEC-0120` A.34).

**Alternative** : `lea-dev`, auteur du paquet (`5718f84d`, s40). Je ne la retiens pas tant que la correction d'`ART-CP-10` est sur lui ; si elle glissait, ce poste ne doit pas glisser avec.

**Regles de casquette.** Je declare la casquette au debut de la tache de developpement ; je n'y arbitre pas mon propre obstacle (je le note, je change de casquette, je le route) ; mon commit est prefixe `[SOF]` ; `mvn verify` vert avant de pousser, jobs nommes. **Aucun worktree ni aucun edit avant l'ouverture de s57** : `DEC-0120` A.34 dit que le placement n'est complet que quand le developpeur est designe ; il l'est ici en proposition, et devient une affectation au planning.
**Si le poste n'est pas livre et rejoue a la cloture de s57, ce serait un troisieme report, interdit** ; il est alors abandonne par un acte ecrit (`DEC-0120` A.34).

## 5. Les items de `sofia-sm` cites dans le depot

### 5.1 `squad/README.md`, section 2 bis : la formule de la forme 3 tolere l'accord au pluriel - fait

Perimetre ecrit : `DEC-0116` A.36 point 1 (dire que la formule tolere l'accord, renvoyer a `DEC-0062` amendement D, **ne pas introduire de convention structurelle**). Fait dans `squad/README.md`, section 2 bis, paragraphe date du 2026-10-13. `DEC-0062` et `DEC-0114` ne sont pas touches (pas de balayage retroactif, `DEC-0062` section 7).

### 5.2 Consignation des depassements de garde-fous `B2`, `B3`, `B5`, `B7`, `B8` (`80c06e64`) - ecrite ici, a reporter au planning

Perimetre ecrit : gabarit de `DEC-0116` A.33, une ligne par depassement, quatre champs : date, garde-fou, estimation avec son auteur et sa methode (ou *"non estimee"*), qui l'a accepte avec ses mots et leur date. Regle de l'utilisatrice : *"pas de bornes superieur c'est au besoin mais on la consigne"* (2026-10-13, `DEC-0116` A.32). **Elle ne porte pas sur `PAR-8` ni `PAR-9`** (A.33).

| Date | Garde-fou | Estimation | Accepte par |
|---|---|---|---|
| 2026-10-11, commit `22b5f888` (11:56), fusionne dans `80c06e64` (12:17) | `B2` : texte alternatif des references | `theo-archi`, `avis-faisabilite.md` de s56, amendement du 2026-10-11 : *"0,25 j pour les quatre premiers"* (`B2`, `B3`, `B7`, `B8` ensemble) ; methode non ecrite, pas ventilee par garde-fou | de l'utilisatrice, *"Lance Q2 aussi meme si on depasse la borne"* (2026-10-11, `review.md` de s56, section 4) ; *"pas de bornes superieur c'est au besoin mais on la consigne"* (2026-10-13) |
| 2026-10-11, `5021ddf0` (11:58) | `B3` : references et paires | idem, dans le meme 0,25 j | idem |
| 2026-10-11, `2eeabb58` (12:04) | `B5` : format d'une oeuvre | `theo-archi`, meme source : *"0,25 j pour `B5`"* ; methode non ecrite | idem |
| 2026-10-11, `ca6c5e8a` (11:59) | `B7` : quatre a six items | dans le 0,25 j de `B2`, `B3`, `B7`, `B8` | idem |
| 2026-10-11, `fe150188` (12:01) | `B8` : enonce unique de dix mots | idem | idem |

Total : environ 0,5 j pour l'ensemble, estimation de `theo-archi`, **pas un tarif** : 0,0 au bareme de `nadia-prof` ; ni l'engage (8,25) ni le consomme de s56 ne changent. **Reserves** : les mots d'acceptation du 2026-10-11 sont relayes, aucun fichier du depot ne les portait avant `review.md` de s56 ; l'attribution aux cinq garde-fous est celle de l'agent lanceur (`review.md`, meme section) ; le temps reellement passe n'est pas mesure.
**Ou l'ecrire** : `DEC-0116` A.36 dit *"dans la `DEC` d'ouverture ou le planning de s57"*. Ni l'un ni l'autre n'est a moi aujourd'hui (la `DEC` est de `camille-po`, le planning n'est pas ouvert) : **ces lignes sont reprises dans `planning.md` a l'ouverture**, avec une ligne par depassement neuf de s57.

### 5.3 L'ecriture des arrets d'agent (`A10` de la section 5 de la retro de s56) - faite dans ses limites

Perimetre ecrit : le contenu que `squad/README.md` (section 9, amendement du 2026-10-12) donne a l'ecriture d'un arret : le nom de l'agent, la date et l'heure, le dernier commit de sa branche, l'etat de son arbre ; un motif inconnu s'ecrit comme inconnu. `DEC-0116` A.36 point 3 : la cause et la liste explicite de ce qui reste non documente.

**Ce que le depot permet d'ecrire, avec sa source :**

- **Cause des arrets, de l'utilisatrice** : *"la cause des arrets est la coupure de la batterie"* (2026-10-13, `DEC-0116` A.32 et A.34). Ses mots portent sur les arrets ; ils ne disent pas si le watchdog sur `lea-dev` en fait partie.
- **Agents arretes** : l'instance B de `nadia-prof` (`cloture-nadia.md` de s56, en-tete : *"l'instance B arretee"*) ; `ines-design` ; un watchdog sur `lea-dev`. Seul le premier est dans un fichier ; les deux autres viennent du message de lancement de la retro de s56 et ne sont attribues a l'utilisatrice nulle part (`DEC-0116` A.29).
- **Fenetre sans livraison** : 23 h 49 min entre `f37e33dd` (2026-10-10 11:17) et `38ba3d5c` (2026-10-11 11:06), `retro.md` de s56, C1. Elle ne dit pas a quel arret ou au watchdog elle est due.

**Non documente, dit comme tel** (`DEC-0116` A.34 : l'utilisatrice n'a pas ces informations ; `git branch --list '*s56*'` ne rend aucune branche de s56 le 2026-10-13, donc le dernier commit et l'etat de l'arbre de chaque agent arrete ne sont plus lisibles) : la date et l'heure de chaque arret ; le motif detaille ; le dernier commit de la branche de chaque agent arrete ; l'etat de son arbre ; le motif et la duree du watchdog ; qui a relance chacun. Je n'ecris aucun de ces points.

### 5.4 Ce qui n'est pas fait, et pourquoi

| Item | Pourquoi pas fait | A qui |
|---|---|---|
| Sous-repertoires de scratchpad par agent et par instance, un port par agent (`nadia-prof`, 4.2 condition 5) | le perimetre est ecrit, mais les noms des instances, des agents et des worktrees n'existent qu'au planning | `sofia-sm`, au planning ; `theo-archi` pour l'isolation si `nadia-prof` la lui demande |
| `Q-N1` : correction du 0,25 de la colonne "avec un lot de `G4`" ; prix de l'instruction des referentiels de CE1 sur les 0,75 non affectes de B | question a `nadia-prof`, pas a moi | `nadia-prof` |
| `lecture-testabilite.md` | proprietaire `yanis-qa` | `yanis-qa` |
| Affectation des lots de francais CE1 aux developpeurs | depend de l'arbitrage prealable de `nadia-prof` et de l'avis de `theo-archi` | `sofia-sm`, au planning |
| Developpeur de la correction d'`ART-CP-10` | forme a `theo-archi`, `ines-design`, `lea-dev` (`DEC-0120` 4.2 et section 6) | `theo-archi`, puis `sofia-sm` |
| Question de `DEC-0120` 4.2 : comment `nadia-prof` rejoue un paquet sans entree de catalogue | `DEC-0116` A.17 ne l'ecrit pas ; `camille-po` la pose a `sofia-sm` et `theo-archi` | `theo-archi` d'abord (le moyen), puis moi pour la ligne de planning |
| `RP-ACCUEIL-147`, `APM-R2` : developpeur et prix | le moyen d'`APM-R2` est a `theo-archi`, son audit a `nadia-prof` (`DEC-0117` section 11), aucun prix n'est ecrit ; je ne designe pas avant le moyen | `theo-archi`, `nadia-prof`, puis `sofia-sm` |

## 6. Obstacles de s57 : classes et routes

| # | Obstacle | Categorie | Route a | Artefact attendu |
|---|---|---|---|---|
| 1 | Avis de faisabilite absent | Technique | `theo-archi` | `avis-faisabilite.md` de s57 |
| 2 | Arbitrage prealable des lots, prix et affectations absent ; `Q-N1` | Pedagogie | `nadia-prof` | arbitrage prealable, avis dans la fiche |
| 3 | `Q-U10` sans reponse, `DEC-0120` `Proposee` | Produit | `camille-po`, utilisatrice | mots en forme 2, amendement de `DEC-0120` |
| 4 | Amendement d'`ADR-0065` avant le premier lot de A' | Technique | `theo-archi` | amendement d'`ADR-0065` fusionne |
| 5 | Lecture de testabilite | Qualite | `yanis-qa` | `lecture-testabilite.md` |

Aucun de ces obstacles n'est clos : aucun des fichiers attendus n'existe sur le disque.

## Decisions prises

**Aucune `ADR` ni `DEC` n'est creee ou amendee par ce fichier.** Les sections 1, 2 et 4 sont des propositions, reprises ou corrigees au planning. La section 3 ne modifie aucun fichier d'une ceremonie close. Le seul fichier du depot modifie hors de ce dossier est `squad/README.md`, section 2 bis (5.1).
