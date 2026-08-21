# Review du sprint 17 - francais CM1 non couvert et la dette

- **Horodatage de debut** : 2026-08-19 18:25 +0200 (`planning.md`)
- **Horodatage de fin** : 2026-08-21 19:41 +0200 (dernier commit de fusion)
- **Duree effective** : 49 h 16 min
- **Increment audite** : `round-two`, tete `a57f972`.

## Fusion des trois derniers lots - moi, casquette Scrum Master

Trois lots restaient a fusionner a l'ouverture de cette cloture, dans cet ordre.

| Lot | Branche | Tete | Lignee verifiee | Fichiers touches | Conflit |
|---|---|---|---|---|---|
| `relecteur-pedagogique` - seconde relecture | `s17-relecteur-2` | `2fcedb6` | fork = mon HEAD exact (`3018afb`) | un fichier neuf (`relecture-seconde-passe.md`) | aucun |
| `nadia-prof` - mise en coherence de ses fiches | `s17-nadia-coherence` | `5d06e49` | fork `3018afb`, ancetre de `round-two` | quatre fiches de conformite | aucun |
| `camille-po` - note datee sur le contre-exemple de `SYN-1` | `s17-camille-syn1-contreexemple` | `1134b81` | fork `402a8e0`, ancetre de `round-two` | `FICHE-bandit-manchot-synonymes-cm1.md` (deja touchee par le lot precedent) | auto-merge Git, sans marqueur ; verifie sur le fond que la note de `camille-po` (ouverture de fiche) et le signalement independant de `nadia-prof` (plus loin dans le meme fichier) coexistent sans contradiction |

Chaque lot verifie au graphe avant fusion (`git merge-base`, `git log <base>..<HEAD actuel> -- <fichiers>`)
plutot que suppose. Deux des trois lots (`s17-nadia-coherence`, `s17-camille-syn1-contreexemple`)
n'existaient pas encore quand `camille-po` a annonce leur arrivee : verifie deux fois par comparaison
directe de `git rev-parse` contre `round-two` avant d'etre pris pour un fait, exactement la
verification que ce sprint a demandee a plusieurs reprises sur des chiffres et des mecanismes plutot
que sur des annonces.

**Aucun push, aucun worktree supprime dans ce geste.**

## Porte technique - rejouee par moi sur l'arbre fusionne final, jamais recopiee

```
bash outils/mvn-jdk21.sh -B clean verify
```

**BUILD SUCCESS**, six modules en SUCCESS (domain, application, adapter-web, adapter-content,
bootstrap unit + bootstrap failsafe).

**Chiffre de tests, par mes deux methodes, sur l'arbre fusionne (`a57f972`), apres un `clean`** :

| Methode | Resultat |
|---|---:|
| (1) Somme des blocs `[INFO] Results:` du reacteur | 48 + 13 + 294 + 144 + 89 + 6 = **594** |
| (2) Somme de l'attribut `tests="..."` des rapports `TEST-*.xml` sous `*/target/{surefire,failsafe}-reports/` (95 fichiers) | **594** |

Les deux methodes concordent. Ce chiffre est passe de 574 (`S17-1` livree, base `26c7d8b`) a 594 en
dix-neuf fusions de lot, chacune verifiee par ces deux memes methodes independamment au moment de la
fusion - jamais recopiee d'un commit.

**Regles ArchUnit, par mes deux methodes** :

- Comptage `@ArchTest` (annotation) : **28** occurrences, verifie `git grep` sur tout le depot hors
  `target/`, a chaque fusion du sprint.
- Plus **1** regle declaree par un champ `ArchRule` statique verifie manuellement via `.check()`
  (`ScanDeComposantsTest`, deux `@Test` qui l'exercent), sans annotation `@ArchTest` - le piege nomme
  par `squad/README.md` section 4.

**29 regles ArchUnit, inchange sur les dix-neuf fusions du sprint**, methode identique a chaque
mesure. Ni `ADR-0049`, ni `ADR-0050`, ni `ADR-0051`, ni `ADR-0052` (ses deux amendements), ni
`ADR-0053`, ni le troisieme amendement d'`ADR-0046` n'ajoutent de regle ArchUnit : verifie a chaque
fois qu'aucun des fichiers de test qu'ils touchent n'importe `com.tngtech.archunit`.

**Trois garde-fous de depot**, rejoues sur l'arbre final :

```
bash .github/scripts/verifier-chemins.sh        -> "Aucun chemin de poste de travail ni nom de compte...", EXIT=0
bash .github/scripts/verifier-chemins-test.sh   -> tous les cas passent (22), EXIT=0
bash .github/scripts/verifier-prefixes.sh       -> "Tous les commits portent un prefixe d'agent connu.", EXIT=0
```

## L'increment en une phrase

**Un enfant qui navigue au clavier voit desormais quel bouton est selectionne, sur les huit familles
de pressables du produit (`S17-1`, VALIDE SOUS RESERVE) ; deux dettes de garde-fou sont fermees avec
preuve de morsure (duree du marqueur de partie, fraicheur des classes compilees) ; et trois refus ou
sorties pedagogiques successifs (comprehension CM1 sortie du perimetre avant tout code, synonymes CM1
refuses deux fois sur un rendu, homophones maintenus hors du catalogue jouable) laissent la couverture
du programme inchangee - le quatrieme sprint consecutif sans mouvement reel, ecrit comme un changement
de direction plutot que tu.**

## Rapport qualite de `yanis-qa` et confirmation ArchUnit de `theo-archi`

`rapport-qualite.md` existe (fusionne dans le lot `s17-yanis`), mais **il couvre une portee
partielle et non la totalite du sprint** : mesure sur `round-two` a `150c428`, avant l'ouverture des
trois derniers `ADR` (`ADR-0051`, `ADR-0052`, `ADR-0053`) et avant le troisieme amendement
d'`ADR-0046`. Je le dis plutot que de le citer comme couvrant la totalite. Ce qu'il couvre reellement
et que je retiens :

1. Le garde-fou de rang (`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`) vide sur
   `cm1-synonymes`, verifie a la source par `yanis-qa` - devenu la matiere d'`ADR-0051`.
2. `UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest` muet sur une divergence de blancs, verifie
   sur du materiau reel mute - devenu l'amendement d'`ADR-0049`.
3. **Les deux dettes de `sofia-sm` (8.1, 8.2) rejouees par `yanis-qa` sur l'arbre fusionne a
   `150c428`**, morsure verifiee dans les deux sens pour 8.1, faux vert nomme pour 8.2. C'est la
   seule preuve de morsure de ce sprint rejouee par un tiers plutot que recopiee de l'auteur du
   correctif.

Les morsures des trois `ADR` ouverts apres ce rapport (`ADR-0051`, `ADR-0052`, `ADR-0053`) et du
troisieme amendement d'`ADR-0046` sont celles ecrites par `theo-archi` lui-meme dans ses propres
commits, jamais recopiees par `yanis-qa` puisqu'elles n'existaient pas encore quand il a ecrit.

**Aucune confirmation ArchUnit dediee de `theo-archi` n'existe pour s17** (pas de
`confirmation-archunit.md` dans `squad/sprints/s17-francais-cm1-et-dette/`, verifie sur le disque). Je
le nomme plutot que de la fabriquer ou de recycler celle d'un sprint precedent, comme la review de
s16 l'avait deja fait pour s16. Le chiffre de **29 regles pour s17** repose sur ma propre mesure,
etablie et reverifiee a chacune des dix-neuf fusions de ce sprint, jamais sur un document dedie de
`theo-archi`.

## Corrections du `relecteur-pedagogique`

**Premiere passe** (`relecture.md`, sur `39b3c42`) : cinq corrections directes, toutes typographiques
(parenthese non fermee dans mon `planning.md`, ligne vide manquante entre deux amendements de
`DEC-0039` concatenes a ma fusion, titre de section duplique, accent isole rompant la convention
deaccentuee de `DEC-index.md`, deux titres coupes sur deux lignes dans `rapport-qualite.md`). Deux
signalements, tous deux traites depuis : `planning.md` n'avait pas suivi la sortie de `S17-2` du
perimetre (corrige par moi, note datee) ; trois occurrences de "22 sur 24" sans renvoi dans
`FICHE-bandit-manchot-additions-cp.md` (corrigees par `nadia-prof`, cinq emplacements avec notes
datees, verifie par moi que les trois occurrences portent chacune un renvoi).

**Seconde passe** (`relecture-seconde-passe.md`, sur `3018afb`) : **zero correction directe** sur les
43 fichiers texte fusionnes depuis la premiere passe - recherche systematique (parentheses non
appariees, titres coupes, tirets cadratins, guillemets typographiques, emoji, doublons de mots,
lignes vides manquantes, references pointant vers rien), rien trouve. Les sept amendements de
`DEC-0039` verifies lisibles de bout en bout, sans contradiction, ordre chronologique correct,
coherents avec `DEC-index.md` et `MATRICE-couverture.md`. Contenu enfant verifie par point de code
pour les guillemets francais et les espaces insecables, aucune faute, francais integralement
accentue.

Trois signalements, tous dans des documents de `nadia-prof` a la premiere personne, non corriges par
lui : tableau de bord des homophones perime (traite par `nadia-prof`, `s17-nadia-coherence`) ; table
de correspondance des jetons `SYN-1` a `SYN-10` absente (recidive du defaut trouve en s16 sur
`BARRE`/`PORTE`/`S15-<n>` - traitee, table ecrite avec quatre regles de lecture) ; affirmation sur
`ADR-0053` en contradiction apparente avec la pointe de l'arbre (verifiee exacte par
`relecteur-pedagogique` a la base de mesure de `nadia-prof` par `git cat-file`, puis reconciliee par
elle-meme dans le meme lot).

**Deux etats perimes signales par `nadia-prof` elle-meme, dans ce meme lot, restent ouverts a cette
review** - je les verifie sur le disque plutot que de supposer leur traitement : la ligne
"Contenu(s)" de `FICHE-bandit-manchot-synonymes-cm1.md` annonce toujours "au moins 20 items" (le
paquet en porte seize depuis le 2026-08-21) et le "Statut global : en construction" de la meme fiche
cohabite sans objet avec trois statuts dates plus recents dont un refus. Le lot de `camille-po` qui a
suivi (`1134b81`) a traite le troisieme point qu'elle signalait dans le meme paragraphe (la question
citee en ouverture, contre-exemple de `SYN-1`, "l'ecart le plus couteux des trois") mais pas ces deux-
la. Obstacle ouvert, route a `camille-po` (voir tableau).

## Avis de `nadia-prof` et Done pedagogique

**`S17-1`** (indicateur de focus clavier) - **VALIDE SOUS RESERVE**. `S16-A` FERMEE (neuf familles
sur dix percoivent l'anneau interieur, mesure au clavier reel). Quatre reserves neuves : `S17-A`
(contraste de la puce de niveau active) **FERMEE** apres implementation et remesure (anneau exterieur
blanc, 9,25:1, addition mesurable) ; `S17-B` (epaississement de bordure sur trois pressables a
contour) reste **non bloquante**, seuil ecrit d'avance et confirme franchi sur l'ecran de
confirmation sans que le signal cesse de tenir ; `S17-C` (tabindex a la revelation, defaut anterieur
a s17) **differee**, sortie au backlog comme story propre ; `S17-D` (etat sous l'appui) corrige sur
ses deux motifs mesures (specificite, puis regression sur la couleur de position `S15-8`, elle-meme
**REFERMEE**), un liseré de 1 px residuel juge invisible et non retenu comme condition.

**`S17-2`** (comprehension CM1) - **sortie du perimetre avant tout code**, verifie par `camille-po` :
aucune classe de `domain`, `application` ou `adapters` ne porte de champ ou de propriete nommant un
texte-support, aucune entree de catalogue, aucun fichier de contenu. Aucun Done pedagogique n'est
prononce ni attendu.

**`S17-3`** (synonymes CM1) - **REFUSE**, deux fois. Premier refus (2026-08-19) : aucune entree de
catalogue, debordement de 63 a 159 px sur les vingt items. Mecanisme d'`ADR-0052` accepte par
`nadia-prof` (`SYN-1` a `SYN-4` tenues), item pilote mesure TIENT (2,81 px) puis corrige par elle-meme
(le comportement face au pli est une propriete du couple item/tirage, pas de l'item : sur un autre
tirage le meme item est a 87 px sous le pli). Mesure complete sur les seize items retenus (quatre
items a trois rangees retires par `camille-po` plutot que reecrits) : **`SYN-5` non tenue**, sept
items sur seize sous le pli (39 a 87 px), 80 tirages au clavier, chiffre retenu = pire tirage.
`SYN-6`, `SYN-7`, `SYN-8`, `SYN-9` tenues (`SYN-9` FERMEE). Condition neuve bloquante `SYN-10` :
`SYN-5` se mesure au pire tirage, jamais sur un tirage unique. `nadia-prof` refuse explicitement de
reduire le paquet davantage : le nombre d'items n'a jamais ete une de ses conditions.

**`S17-4`** (homophones, "Le bon petit mot") - **VALIDE SOUS RESERVE** sur `BPM-2`/`BPM-3`/`BPM-4`
(toutes trois tenues), exactement la porte partielle gelee au planning. Reserve sur les indices
**FERMEE** : 0 indice sur 24 nomme encore la reponse (24/24 avant `S17-4`, 3/24 apres un premier lot,
0/24 apres le second). **L'entree de catalogue reste tenue hors du jeu jouable**, decision explicite
de `camille-po` (`DEC-0024`, amendement) : la condition de retour exige `BPM-1` (mesure de rendu sur
la totalite des 24 manches) **et** un Done pedagogique complet, et `BPM-1` n'est produite par aucun
agent de la squad ce sprint (`DEC-0039` section 3, transfert a l'utilisatrice).

**`S17-5`** (antonymes) - non affectee, story ecrite au backlog des l'ouverture du sprint, satisfait
`SYN-4`.

**Dette 8.1 et 8.2** - techniques, aucun constat pedagogique attendu ni rendu par construction.

**Done pedagogique global du sprint** : un VALIDE SOUS RESERVE (`S17-1`), un VALIDE SOUS RESERVE
partiel avec entree tenue dehors (`S17-4`), un REFUSE (`S17-3`), une sortie de perimetre sans code
(`S17-2`). Aucune ligne de `MATRICE-couverture.md` n'atteint `couvert-valide`.

## Verification physique de chaque porte de sortie du planning (amendement s13)

| Story / dette | Porte de sortie ecrite au planning | Etat verifie sur le disque a cette review |
|---|---|---|
| `S17-1` | `mvn verify` vert sur les deux criteres testables, **puis** sonde de `nadia-prof` (FOC-4/5/6) | **Franchie, avec reserve.** Sonde rendue (`f07abca`, `33febdf`), quatre conditions neuves ouvertes (`S17-A` a `S17-D`), trois fermees (`S16-A`, `S17-A`, `S15-8`), une non bloquante (`S17-B`), une differee hors perimetre (`S17-C`). Done pedagogique VALIDE SOUS RESERVE, verdict ecrit |
| `S17-2` | `mvn verify` vert sur COMP-1/2/3 + comptage + schema, **puis** Done pedagogique sur COMP-4/COMP-5 | **Sans objet, verifie plutot que suppose.** La story sort du perimetre (`DEC-0039`, amendement) avant qu'aucune ligne de code ne soit ecrite - confirme par `camille-po` sur les trois modules, jamais par moi seule. La porte de sortie ecrite au planning n'a donc jamais eu de materiau a franchir |
| `S17-3` | `mvn verify` vert sur SYN-1/2/3 + comptage + vigilance sur trois garde-fous, **puis** Done pedagogique | **Franchie techniquement, refusee pedagogiquement.** Vigilance sur les trois garde-fous nommes tenue (rang vide connu et declare par `ADR-0051`, identite d'ecran et propositions verifiees). Done pedagogique **REFUSE**, deux fois, dernier verdict le 2026-08-21 |
| `S17-4` | Porte partielle gelee explicitement au planning : contenu + deux lignes de code tenus, `BPM-1` hors du perimetre de la squad | **Franchie exactement comme gelee**, ni plus ni moins. `BPM-2`/`BPM-3`/`BPM-4` VALIDE SOUS RESERVE, reserve des indices fermee, entree de catalogue tenue hors du jeu jouable comme prevu tant que `BPM-1` manque |
| `S17-5` | Story ecrite au backlog, non affectee | **Franchie**, deja satisfaite des la fusion de `camille-po` a l'ouverture du sprint |
| Dette 8.1 | `mvn verify` vert, correctif d'une classe, mutation prouvant la morsure decrite dans le commit | **Franchie.** `e6173ae` : `cookie.setMaxAge()` mute a une constante cablee, premiere assertion rougit (`expected: 900 but was: 1800`), sans dependre d'un voisin. Delta 0 test |
| Dette 8.2 | `mvn verify` vert, garde-fou etendu, preuve de morsure sur un `.class` perime simule | **Franchie.** `f35a218` : `GameMechanic.class` touche a une date anterieure a son `.java`, `AssertionFailedError` obtenue sur `application` seul (`-am`), reacteur complet revalide vert apres `clean`. Delta +3 tests. Un faux rouge symetrique (avance d'horodatage du `.java` sans changement de contenu) nomme par `yanis-qa`, **conserve et documente** par `ADR-0051` comme non atteignable sans supprimer la compilation - pas une porte manquee, une limite ecrite |

**Aucune des sept portes de sortie du planning n'est restee silencieusement ouverte** : chacune est
soit franchie, soit refusee avec un verdict ecrit, soit constatee sans objet par verification directe
du code de production.

### Ce que ce sprint a ajoute au-dela du tableau des sept portes, et qui n'etait pas au planning

`S17-C` (tabindex a la revelation, defaut anterieur a s17, trouve par la sonde de `S17-1`) : sortie au
backlog comme story propre, non affectee, mecanisme route a `theo-archi`. Le mandat sur la
disposition des propositions de l'ecran de manche partage : ferme apres `S17-2`, **rouvert** par le
second refus de `S17-3` (`DEC-0039`, septieme amendement), route a `ines-design` et `theo-archi`,
affectation explicitement differee au-dela de ce sprint. La story de migration matiere (execution
d'`ADR-0049`) : ecrite au backlog, perimetre etendu de douze a treize paquets puis a seize chaines
supplementaires routees par `theo-archi`, non affectee.

## Tableau des obstacles du sprint

**Resolus** :

| Description | Categorie | Destinataire | Clos par |
|---|---|---|---|
| Conflit annonce trois fois sur `FICHE-barre-entree-niveau.md` | methode | sofia-sm | Fusion propre a chaque occurrence reelle, correction datee deja bien formee par `nadia-prof` elle-meme |
| Quatre conflits reels sur `DEC-0039`/`DEC-index.md` (memes fichiers, amendements successifs de `camille-po` a des moments differents) | methode | sofia-sm | Concatenation dans l'ordre chronologique des commits a chaque fois, jamais d'ecrasement, verifie apres chaque resolution |
| Garde-fou de rang vide en silence sur du texte (4/12 puis 5/17 paquets) | technique | theo-archi | `ADR-0051` : declaration executable avec motif, quatre morsures |
| Regle de graphie muette sur les blancs | technique | theo-archi | Amendement d'`ADR-0049` : espace insecable comptee, trois jeux fautifs dont un vert obligatoire |
| `ADR-0052` non exercee sur le materiau reel (clause generale bornee a un jeu fictif) | technique | theo-archi | `ADR-0052`, second amendement : population derivee du catalogue reel, quatre morsures |
| `S17-A` (contraste de la puce active) | design | ines-design (forme), marc-dev (implementation) | Implementee et remesuree FERMEE |
| `S15-8` defaite par le correctif de `S17-D` | technique | marc-dev | Reparee sans reperdre `S17-D` (`ADR-0053` verifie la propriete dans les deux sens) |
| Reserve sur les indices d'homophones (22/24 puis 24/24) | contenu | nadia-prof, lea-dev | FERMEE, 0/24 |
| Trois occurrences de "22 sur 24" sans renvoi | redaction | nadia-prof | Cinq emplacements corriges par notes datees, verifie |
| Tableau de bord homophones perime, table SYN absente, reconciliation `ADR-0053` | redaction | nadia-prof | Traites dans `s17-nadia-coherence` |

**Ouverts a la cloture** :

| Description | Categorie | Destinataire | Ce qui manque pour clore |
|---|---|---|---|
| Dette d'ancetre partagee par deux garde-fous, en sens opposes (prefixe d'ancetre maison non verifie, regles tierces sous `@media` invisibles) | technique | theo-archi | Deux declencheurs nommes par `ADR-0053`, non traites |
| Faux rouge symetrique de la dette 8.2 | technique | theo-archi | Conserve et documente comme non atteignable sans supprimer la compilation - limite ecrite, pas un defaut a fermer |
| Deux etats perimes restants dans `FICHE-bandit-manchot-synonymes-cm1.md` ("20 items" en tete, "Statut global : en construction") | redaction | camille-po | Signales par `nadia-prof`, un des trois traite (`1134b81`), deux non |
| `S17-C` (tabindex a la revelation) | technique | theo-archi | Mecanisme du correctif JS et de son garde-fou, story au backlog |
| Mandat sur la disposition des propositions de l'ecran de manche partage | design / mecanisme | ines-design, theo-archi | Affectation quand les deux sont disponibles, pas ce sprint |
| Story de migration matiere (`ADR-0049`) | technique | theo-archi | Execution non affectee a un sprint |
| `S17-B` (epaississement de bordure) | design | - | Non bloquante, seuil de bascule deja ecrit |
| Anneau de focus par defaut du navigateur visible sur toute manche fraiche | technique / accessibilite | theo-archi | Verse au dossier `S17-C`, meme ligne de `recreo.js` |
| Liseré de 1 px sous l'appui sur boutons pleins et propositions | design | - | Non retenu comme condition, invisible mesure |

## Decisions prises

1. **Le Done technique de l'increment fusionne (`a57f972`) est atteste par ma propre execution de
   `mvn -B clean verify`**, avec deux methodes de comptage concordantes pour les tests (594) et pour
   les regles ArchUnit (29), sur l'arbre fusionne complet, apres un `clean`.
2. **Aucune confirmation ArchUnit dediee de `theo-archi` n'existe pour s17** : je le nomme plutot que
   de la fabriquer ou de recycler celle d'un sprint precedent.
3. **Le rapport qualite de `yanis-qa` est declare de portee partielle** : ecrit a mi-sprint (`150c428`),
   avant `ADR-0051`, `ADR-0052` et `ADR-0053`. Je ne le cite pas comme couvrant la totalite du sprint.
4. **Les verdicts de `nadia-prof` sont restitues sans reformulation qui en attenuerait la portee** :
   `S17-1` VALIDE SOUS RESERVE, `S17-3` REFUSE (deux fois), `S17-4` VALIDE SOUS RESERVE avec entree
   de catalogue tenue dehors, `S17-2` sans Done attendu (sortie sans code).
5. **Les sept portes de sortie du planning sont verifiees physiquement, une a une** : six franchies
   (dont une exactement comme partiellement gelee), une constatee sans objet (`S17-2`, sortie avant
   tout code).
6. **Le verdict de `DEC-0037` est porte explicitement, sur consigne de `camille-po`** : l'engagement
   n'est pas tenu en substance, s17 est le quatrieme sprint consecutif sans mouvement reel de
   couverture programme, ecrit comme un changement de direction dans `DEC-0039` (septieme amendement)
   et dans `MATRICE-couverture.md`.
7. **Deux etats perimes signales par `nadia-prof` restent ouverts** dans `FICHE-bandit-manchot-
   synonymes-cm1.md`, routes a `camille-po`, non corriges par moi ni par le relecteur (pas leur texte).
8. **Aucun worktree n'est supprime, aucun push n'est effectue dans ce geste.**
