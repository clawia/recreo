# Review du sprint 20 - mot cible designe et conception de la saisie

- **Horodatage de debut** : 2026-08-25 17:00 +0200 (`planning.md`)
- **Horodatage de fin** : 2026-08-27 11:15 +0200 (cette ceremonie)
- **Duree effective** : environ 42 h 15 min
- **Increment audite** : `round-two` a `1c9c313` (tete au moment de cette review, `git status`
  propre)

## Increment livre

Le mot cible de "Nom ou verbe ?" (`cp-nature-mot`) est marque a l'ecran par un contrat de contenu
declaratif (`ADR-0062`, `Question.partDesignee`), plutot que devine dans les guillemets francais du
texte ; une competence neuve du referentiel CM1 (reperer et corriger la majuscule et la
ponctuation) entre en contenu avec `cm1-reperes`, refusee puis corrigee puis reauditee dans le
meme sprint ; la conception de la mecanique de saisie avance sans code (`SAI-2a`/`2b`/`2c`) ; et la
bande de viewport 576-719 px sort du perimetre produit sur arbitrage de l'utilisatrice, amendant
`DEC-0040` et reversant `DEC-0041`. La couverture passe de 13/43 (30,2 %) a 15/43 (34,9 %).

## Lots fusionnes

Six lots decrits et verifies en provenance dans `planning.md` (`camille-po`, `theo-archi` x2,
`nadia-prof`, `ines-design`, `camille-po`), puis la suite du sprint apres le gel du planning :
`marc-dev` (contrat `ADR-0062`), `ines-design` (forme `.mot-designe`), `lea-dev` (`cm1-reperes`),
`nadia-prof` (deux verdicts, `6f03de8`/`9c8aa7b`), `camille-po` (`f9594fb`), `lea-dev` (correction
`REP-4`, `bd87e91`), quatre fusions de `sofia-sm` (`d8deca0`, `255e169`, `86a1abd`, `302d22d`,
`09e1641`, `77dd467`), le rapport qualite (`9ccbb1d`), la confirmation ArchUnit de cloture
(`6228d77`), les deux verdicts finaux de `nadia-prof` (`c7ad0b2`, `c694f94`), la cloture de
`camille-po` (`d1800d0`, `DEC-0050`), l'execution catalogue de `sofia-sm` (`a266b4c`) et la
relecture pedagogique (`1c9c313`).

## Porte technique - rapport de `yanis-qa`

`rapport-qualite.md` : `bash outils/mvn-jdk21.sh -B clean verify` sur `77dd467`, reacteur complet,
JDK 21.0.10 (toolchain) - **BUILD SUCCESS, 694 tests, 0 echec** (methode : somme des blocs
`Results:`, module par module : 49 + 13 + 330 + 198 + 98 + 6). Delta cite (pas recompose) contre la
mesure de `theo-archi` du 2026-08-25 sur `c48c0e6` (668 tests) : **+26**, repartition verifiee ligne
a ligne. **30 regles ArchUnit reelles**, inchange par le sprint. Deux mutations sur du contenu de
production reel (clause 2 d'`ADR-0062` sur `cp-nature-mot.yaml`, motif du refus `DEC-0049` sur
`cm1-reperes.yaml`) : rouge constate, fichier restaure et verifie identique, vert reconstate. Un
faux vert de conduite de mesure instruit (section 6, voir tableau des obstacles, ligne 11).

**Ma propre remesure, sur l'arbre de cloture** (`1c9c313`, le 2026-08-27, apres la relecture
pedagogique qui ne touche aucun fichier de code) : `bash outils/mvn-jdk21.sh -B clean verify` -
**BUILD SUCCESS**, six modules `SUCCESS`. Blocs `Results:` : `recreo-domain` 49, `recreo-
application` 13, `recreo-adapter-web` 330, `recreo-adapter-content` 198, `recreo-bootstrap` 98
(surefire) + 6 (failsafe). 49+13+330+198+98+6 = **694**, identique au chiffre de `yanis-qa` sur
`77dd467` et de `theo-archi` sur `9ccbb1d` : les trois commits qui suivent `77dd467` (`9ccbb1d`,
`6228d77`, `c7ad0b2`, `c694f94`, `d1800d0`, `a266b4c`, `1c9c313`) ne touchent que de la
documentation ou une entree de catalogue deja couverte par `TexteAccentueExposeParLePortEntrantTest`
dans le meme commit (`a266b4c`) - la concordance est attendue et verifiee, pas supposee.

## Confirmation ArchUnit - `theo-archi`

`confirmation-archunit.md` : deux mesures datees (`c48c0e6` le 2026-08-25, 668 tests sur son seul
lot documentaire ; `9ccbb1d` le 2026-08-27, 694 tests sur l'arbre de cloture), ecart **+26** sur les
tests et **0** sur les 30 regles ArchUnit et sur la suite ciblee `com.recreo.archi` (71 tests).
`ADR-0062` est declare **complet** au sens de `CLAUDE.md` : ses deux clauses sont des garde-fous de
balayage sur du document rendu et du contenu charge (pas des regles ArchUnit, motif ecrit et
justifie), toutes deux vertes sur l'arbre de cloture, mordant dans les deux sens (marque presente
et unique, marque absente ailleurs). La derive documentaire de la ligne d'index et du statut
d'`ADR-0062` (qui decrivaient encore une regle rouge apres la fusion de `S20-1`) est amendee avec sa
date, pas reecrite. Le faux vert ArchUnit du 2026-08-25 (`-Dtest='com.recreo.archi.*'` sans le
suffixe `Test`, `BUILD SUCCESS` sur zero test) est reproduit et instruit independamment par
`theo-archi` lui-meme, en plus de la reproduction de `yanis-qa` (voir tableau des obstacles,
ligne 11).

## Corrections du `relecteur-pedagogique`

`relecture-s20.md` : 33 commits non-merge, 78 fichiers relus, **aucun defaut textuel trouve**
(accents, tirets cadratins, guillemets typographiques, emoji, chemins absolus, exigence d'accent
integral du contenu enfant - tout verifie par script ou par lecture, rien a corriger). Quatre
chiffres verifies a leur source primaire plutot qu'a une reference (30 regles ArchUnit, 33 commits,
`git diff` vide entre `77dd467` et `6228d77`, arithmetique de la matrice sur ses quatre tableaux) :
tous concordent. Deux points routes, non fermes par le relecteur lui-meme, traites ci-dessous
(tableau des obstacles, lignes 14 et 15).

## Tableau des obstacles

| # | Obstacle | Categorie | Destinataire | Etat |
|---|---|---|---|---|
| 1 | 95 items sur 296 (32 %), sur dix jeux tous `couvert-valide`, deja sous le pli a la reference (744 x 551) | Pedagogie | `nadia-prof` puis `camille-po` | **OUVERT.** Confronte chiffre a chiffre par `DEC-0050` a `REP-8` de `nadia-prof` (8 jeux sur 17, 43 items sur 157) : meme famille de defaut (trois jeux communs), perimetres et populations distincts, non assimilables. Aucun statut de couverture prononce. `REP-8` devient story de backlog non affectee, routee a `camille-po`/`ines-design`/`theo-archi` sur la cause de forme (budget vertical du cabinet, taille de `.proposition`) |
| 2 | Piste verticale de `S19-6a`, a chiffrer | Technique | `theo-archi` | **CLOS** (planning) : chiffree, elle ne ferme pas la bande (19/134 au budget honnete, 114/134 au plafond absolu, 0/10 items a trois rangees) |
| 3 | Arbitrage de l'utilisatrice sur la bande 576-719 px | Produit | `camille-po` | **CLOS** : *"amende `DEC-0040`, la bande passe hors cible"*, troisieme amendement de `DEC-0040`, reversant `DEC-0041`. `S19-6a`/`S19-6b` retirees du backlog |
| 4 | `DEC-0043` avait scinde `S19-6` au motif de "mecanismes differents" | Technique | `theo-archi` | **CLOS** : etabli faux (meme ecran, meme cause, meme population de dix jeux), note versee sous `DEC-0043`, section d'origine non reecrite |
| 5 | Chiffre de 43,19 px (puis 68,19 px) utilise trois sprints pour comparer des pistes de correctif | Technique | `theo-archi` | **CLOS** : le deficit reel de la population vaut 308,19 px, pas 43,19 - c'etait le bas du texte du verdict, pas du bloc de message entier. Corrige dans le second amendement d'`ADR-0059` |
| 6 | Trois arbitrages routes a l'utilisatrice par `DEC-0047` (saisie en conception seule, clavier avec correction acceptee, produit qui ne s'adresse jamais a l'adulte) | Produit | `camille-po` | **CLOS** : les trois reponses rendues et tracees, amendement du 2026-08-25 a `DEC-0047` |
| 7 | Issue de `SAI-2` face au clavier correcteur (la condition tient, est amendee, ou disparait) | Pedagogie | `nadia-prof` | **CLOS pour ce sprint** (`SAI-2a`/`2b`/`2c` tranchees, la chaine jugee voyage sur le verdict). **OUVERT** pour sa consequence complete sur la conception de la saisie : aucun artefact de cloture ne l'instruit, reste au chantier documentaire |
| 8 | Deux affirmations chiffrees fausses dans `ADR-0059` ("141 sur 144" recopie sans sa condition ; "elles changent une ligne de constante chacune") | Technique | `theo-archi` | **CLOS** : trouvees et corrigees par `theo-archi` lui-meme, `ADR-0059` et `ADR-index` amendes |
| 9 | `camille-po` avait tranche le residu de `S19-6a` (3 px sur 144) sur un chiffre deja perime par son propre amendement du meme jour | Produit | `camille-po` | **CLOS** : corrige le meme jour, avant le retrait de la bande |
| 10 | `camille-po` avait cite deux jeux `couvert-valide` (`cm1-synonymes`, `cm1-antonymes`) sur les dix touches par la mesure a la reference | Produit | `camille-po` | **CLOS** : verifiee ligne par ligne contre `MATRICE-couverture.md`, les dix jeux sont `couvert-valide`. Un second chiffre invente ("295 sur 296") retire dans le meme commit |
| 11 | Premiere commande de mesure ArchUnit ciblee de `theo-archi` a rendu `BUILD SUCCESS` en executant zero test (motif sans le suffixe `Test`) | Qualite, mesure | `yanis-qa` | **CLOS** : reproduit independamment par `yanis-qa` (`rapport-qualite.md` section 6) et par `theo-archi` lui-meme (`confirmation-archunit.md`) dans les deux sens (fautive -> vert sans bloc `Results:`, corrigee -> 71 tests). Regle de conduite versee aux deux documents : le compte de tests se lit avant que le vert soit cite. Verse en retro |
| 12 | `cm1-reperes` `REFUSE` le 2026-08-26 (pli 0/16, `REP-4` non tenue) | Pedagogie | `nadia-prof` puis `lea-dev` | **CLOS** : corrige par `lea-dev` (`bd87e91`), reaudite le 2026-08-27 - `REP-4` tenue (13/16, trois items exigent la distinction), pli lisible sur 14 items sur 16. `VALIDE SOUS RESERVE`, `DEC-0050` |
| 13 | Quatre reserves non bloquantes issues de la reaudition de `cm1-reperes` (`REP-7` : composition predictible sans lecture ; `REP-8` : indice hors de vue, 8 jeux sur 17 du catalogue ; `REP-9` : verdict coupe sur les items a deux lignes ; `REP-10` : indice de l'item 15 trop proche) | Pedagogie / Produit | `camille-po` (backlog non affecte) | **OUVERT** : `REP-7`, `REP-9`, `REP-10` routees a `lea-dev` (reprise du paquet) ; `REP-8` routee a `camille-po`/`ines-design`/`theo-archi` (defaut de catalogue, pas de ce paquet). Aucune affectee a un sprint (planning de s21 non ecrit) |
| 14 | Collision de nommage `REP-7`/`REP-8` : labels de `camille-po` du 2026-08-25 (le pli, le cinquieme libelle) contre labels de `nadia-prof` du 2026-08-27 (prediction sans lecture, indice hors de vue) - non couverte dans `rapport-qualite.md`, seul document a employer les deux labels sans date ni renvoi | Redaction | `sofia-sm` (route par `relecteur-pedagogique`) | **CLOS a cette review** : renvoi date ajoute par `sofia-sm` aux trois emplacements concernes de `rapport-qualite.md` (section 3, section 7, compte rendu court), sur le modele deja pose par `camille-po` (`docs/backlog/backlog.md`, `DEC-0050`). Aucun chiffre ni verdict de `yanis-qa` modifie - une date ajoutee a une reference, pas une reecriture de son constat |
| 15 | Le commentaire de `.mot-designe` dans `recreo.css` annonce "trois canaux non colores" ; `nadia-prof` mesure que la boite de fond est un canal colore (contraste 1,04:1, disparait en achromatopsie simulee) et que seul l'agrandissement de police survit | Redaction / forme visuelle | `marc-dev` (auteur, commit `6b8eebe`) et `ines-design`, `theo-archi` en copie | **OUVERT, non ferme par cette review.** Deja route par `nadia-prof` (`FICHE-bandit-manchot-nature-mot-cp.md`, section 4) et confirme par le relecteur. Suspendu a la reserve non bloquante `NAT-12` (`ines-design` explore un liseret sombre) : corriger le texte maintenant ecrirait un chiffre de canaux qui redeviendrait faux des que `NAT-12` bouge |
| 16 | Trois erreurs de `nadia-prof` contre elle-meme (barre du pli presentee comme etablie sans l'etre pour l'indice ; une phrase lue comme une interdiction par `lea-dev` ; en-tete de fiche "Done pedagogique non encore prononce" perime trois verdicts) | Pedagogie / conduite | `nadia-prof` (auto-route) | **CLOS** : les trois corrigees dans son propre lot de cloture (`c7ad0b2`). Versees en retro pour la lecon qu'elles portent |
| 17 | La tete de branche a avance pendant l'audit de `nadia-prof` (`9ccbb1d`, `6228d77` fusionnes au-dessus de `77dd467`) | Gouvernance / coordination | `nadia-prof` (auto-verifie) | **CLOS** : verifie par elle-meme, pas suppose - `git diff --name-only 77dd467..6228d77 -- adapters domain application bootstrap` rend une sortie vide, l'artefact audite reste identique a la tete (`c694f94`) |

## Avis de `nadia-prof` et Done pedagogique

`avis-nadia-cloture.md`, increment audite `77dd467`, verifie identique a la tete par elle-meme
(obstacle 17 ci-dessus) :

| Jeu | Verdict anterieur | Verdict du 2026-08-27 | Ligne de matrice | `catalogue.yaml` |
|---|---|---|---|---|
| `bandit-manchot-nature-mot-cp` | `VALIDE` le 2026-08-25 (`NAT-5` levee) | **`VALIDE` confirme** sur l'artefact reellement servi (mesure identique au centieme a celle du 2026-08-25) | `couvert-valide` | rien a faire, l'entree de `DEC-0048` porte l'increment valide |
| `bandit-manchot-reperes-cm1` | `REFUSE` le 2026-08-26 (pli, `REP-4`) | **`VALIDE SOUS RESERVE`** apres reaudition du paquet corrige | passe `couvert-valide` (`DEC-0050`) | entree restauree par `sofia-sm` (`a266b4c`) |

**Aucun refus ne reste ouvert a la cloture de s20.**

**Quatre reserves non bloquantes ouvertes**, aucune affectee a un sprint : `REP-7`, `REP-9`,
`REP-10` (propres a `cm1-reperes`, routees a `lea-dev`) et `REP-8` (indice hors de vue sur 8 jeux
sur 17 du catalogue servi, 43 items sur 157 - premier defaut pedagogique du catalogue selon
`nadia-prof`, routee a `camille-po`/`ines-design`/`theo-archi` comme story de catalogue, jamais
comme une reprise de `cm1-reperes`). Une cinquieme reserve de redaction, `NAT-12`, reste ouverte
(tableau des obstacles, ligne 15).

## Les deux Done

- **Technique : vert.** `bash outils/mvn-jdk21.sh -B clean verify` sur `1c9c313`, reacteur complet,
  le 2026-08-27 : **BUILD SUCCESS, 694 tests, 0 echec** (methode et repartition ci-dessus, mesuree
  par moi independamment de `yanis-qa` et `theo-archi`, les trois concordent). **30 regles
  ArchUnit**, inchange par le sprint.

  **Note sur l'etat de poussee.** `origin/round-two` reste a `a7ca85b` (pousse en cours de
  planning, avant le lot `S20-1`) ; `HEAD` (`1c9c313`) la depasse de 22 commits
  (`git rev-list --count a7ca85b..HEAD`). Comme etabli a la cloture de s19
  (`squad/README.md` section 7 : *"c'est la CI qui juge, pas un agent"*), ce Done technique est une
  mesure locale, concordante sur trois executions independantes, et non un verdict de la CI sur cet
  arbre. Je ne pousse rien : la poussee et la lecture du workflow restent la main de
  l'utilisatrice.

- **Pedagogique : deux verdicts, aucun refus ouvert.** `bandit-manchot-nature-mot-cp` `VALIDE`
  confirme ; `bandit-manchot-reperes-cm1` `VALIDE SOUS RESERVE` (quatre reserves non bloquantes,
  aucune bloquante). Couverture : 13/43 (30,2 %) en debut de sprint -> 15/43 (34,9 %) a la
  cloture, deux lignes gagnees et auditees dans le meme sprint.

## Decisions prises

Aucune decision d'architecture, de produit ou de pedagogie n'est prise par cette ceremonie : elle
agrege les decisions deja tranchees pendant le sprint (`DEC-0047` a `DEC-0050`, `ADR-0058`,
`ADR-0059`, `ADR-0062`, detaillees dans `RAPPORT.md`). Le seul geste que je prends moi-meme est
redactionnel : le renvoi ajoute a `rapport-qualite.md` pour desambiguer `REP-7`/`REP-8` (tableau des
obstacles, ligne 14) - une date ajoutee a une reference, pas une decision.
