# [Sprint s16] Planning - deux defauts de l'utilisatrice et fermeture des dettes de l'en-tete

- **Horodatage de debut** : 2026-08-19 10:11 +0200
- **Anime par** : sofia-sm, casquette Scrum Master.
- **Ordre du process respecte, pour la premiere fois depuis s13** : `camille-po` a propose le
  perimetre et les stories (`DEC-0036`, `DEC-0037`, fusion `879fb99`) et `theo-archi` a rendu son
  avis de faisabilite ecrit (fusion `ea4433f`) **avant tout commit de code** - verifie par
  `git diff --stat 4c7cd45 ea4433f -- '*.java'`, vide. Ce que ce respect de l'ordre a produit,
  factuellement et non comme un satisfecit : un avis rendu avant coup a **reecrit sept criteres sur
  vingt-et-un** et **renforce un huitieme gratuitement**, sur des stories qui n'avaient encore aucune
  ligne de code - aucune de ces corrections n'aurait ete gratuite si elle avait ete trouvee apres
  fusion, comme cela a ete le cas a chaque lot de s15.
- **Lots fusionnes avant ce planning** : `camille-po` (`879fb99`, embarque `f73ad41` et `7142be8`),
  `theo-archi` (`ea4433f`, embarque `de29457`), tous deux lignee verifiee (`git merge-base` avec
  `4c7cd45`), aucun conflit.
- **Avis de faisabilite reference** :
  `squad/sprints/s16-deux-defauts-et-dettes-de-len-tete/avis-faisabilite.md`.
- **Repertoire de sprint renomme** : `theo-archi` avait cree
  `s16-deux-defauts-de-l-increment-de-s15`, qui ne nommait que `S16-1`/`S16-2` ; renomme par
  `git mv` en `s16-deux-defauts-et-dettes-de-len-tete` pour couvrir les six (puis sept) stories du
  perimetre gele ci-dessous. Aucune reference a l'ancien chemin trouvee ailleurs dans le depot
  (`grep` sur `*.md` et `*.java`, vide).

## Porte technique a l'ouverture - rejouee, avec sa limite nommee

**L'instance du port 8080 est celle de l'utilisatrice, non touchee** - verifie avant et apres
(`netstat`, meme PID). Verifie moi-meme, avant de lancer une commande, que les quatre tests
d'integration de `bootstrap` prennent un port libre (`ServerSocket(0)` dans `JarRecreoIT`,
`WebEnvironment.RANDOM_PORT` dans les trois autres) - aucun n'ouvre 8080.

**`bootstrap/target/recreo.jar` est verrouille par cette instance** (`java -jar
bootstrap/target/recreo.jar`, PID confirme par la ligne de commande du processus) : `mvn clean`
sur `bootstrap` echoue en tentant de supprimer ce fichier, et `package`/`repackage` echoueraient de
la meme facon en tentant de l'ecraser. Je ne force rien et je n'arrete pas l'instance.

**Ce que j'ai mesure a la place, avec sa methode :**
- `bash outils/mvn-jdk21.sh -B clean verify -pl domain,application,adapters/adapter-web,adapters/adapter-content`
  (reacteur complet moins `bootstrap`, aucune dependance sur le jar verrouille) : **BUILD SUCCESS**,
  **440 tests** (48 + 11 + 260 + 121), 0 echec - confirme par les blocs `Results:` et par la somme de
  l'attribut `tests="..."` des rapports XML des quatre modules.
- **`bootstrap` n'a pas ete reexecute** : `git diff --stat 4c7cd45 ea4433f -- '*.java'` est vide,
  donc aucun fichier source n'a change dans les deux lots fusionnes. Le chiffre de `bootstrap`
  (**88 tests** : 82 en surefire, 6 en failsafe) est celui que `theo-archi` a mesure fraichement par
  sa propre commande sur `4c7cd45`, publie dans `confirmation-archunit.md` - un ancetre direct de
  l'arbre gele ici, a zero fichier Java d'ecart. Je ne le recopie pas sur parole : je verifie
  l'egalite des sources qui le justifie.
- **Total : 528 tests**, inchange depuis la cloture de s15.
- **29 regles ArchUnit**, deux methodes sur l'arbre fusionne : 28 champs `@ArchTest` + 1 regle
  verifiee par `.check()` explicite dans `ScanDeComposantsTest` (piege connu depuis s06) = 29 ;
  contre-verifie par 29 declarations directes de champ `ArchRule` (30 occurrences brutes du motif,
  moins une fausse correspondance dans un commentaire de `LesGardeFousSontMisALEpreuveTest`).
  Inchange, confirmant l'annonce de `theo-archi` ("ses lots etant documentaires plus des regles
  neuves proposees mais non encore codees").
- `bash .github/scripts/verifier-chemins.sh` : code 0. `bash .github/scripts/verifier-chemins-test.sh` :
  code 0, tous les cas passent.

## Objectif gele

**Fermer les deux defauts que l'utilisatrice a trouves en jouant l'increment de s15 (message de
reprise perime, adresse incoherente apres "Les jeux") et les quatre dettes que s15 a laissees
ouvertes sur l'en-tete (etat sous le pointeur, trou de garde-fou d'accentuation, phrase de filtre
manquante sur une route), sans ouvrir aucune ligne de `squad/programme/MATRICE-couverture.md` -
troisieme sprint consecutif dans ce cas, nomme et borne ci-dessous.**

## Tableau des stories

Criteres dans leur **forme reecrite** par `theo-archi` (`avis-faisabilite.md`) quand il les a
reecrits ; portes de sortie reprises de `docs/backlog/backlog.md` quand elles tiennent telles
quelles.

| Story | Competence visee | Affectation | Ce qui change dans les criteres | Porte de sortie |
|---|---|---|---|---|
| **S16-1** - le message de partie interrompue ne s'affiche que pour une interruption plausible | Transverse, pas de contenu programme (accueil generique, `S1-4`) | `marc-dev` | Critere 2 **reecrit** : plus "session expiree par inactivite" (demande a un automate de faire passer le temps), mais *"une requete qui ne porte pas le marqueur obtient le catalogue complet sans le message"* - la consequence observable de la meme propriete. Critere 1 precise : la duree doit etre **lue** sur la session de la requete jugee, jamais recopiee dans le test. | Criteres 1, 3, 4 (encodables tels quels) et le critere 2 reecrit tenus, `mvn verify` vert ; puis constat ecrit de `nadia-prof` sur les deux scenarios rejoues (`FICHE-bandit-manchot-additions-cp.md`) - **seule elle peut le rendre**. |
| **S16-2** - l'adresse affichee correspond au catalogue complet apres un retour depuis "Les jeux" | Transverse (navigation, en-tete) | `marc-dev`, **apres** S16-1 - meme fichier probable (`PartieController.java`), sequence non parallele (voir obstacles) | Critere 1 **reecrit** : plus "l'adresse affichee par le navigateur devient..." (promet une propriete de navigateur qu'aucun automate du depot ne mesure), mais *le garde-fou lit ce que le serveur declare* (en-tete de reponse), sous la forme d'`ADR-0048`. Critere 3 gagne le **sens negatif** de la regle : aucune adresse declaree sur l'ecran de confirmation. | Criteres 2, 3, 4 (encodables tels quels) et le critere 1 reecrit tenus, `mvn verify` vert ; puis constat ecrit de `nadia-prof` (`FICHE-barre-entree-niveau.md`) qu'aucune condition deja tenue (marqueur `ecran-porte-sa-sortie`, BARRE, PORTE, S15-1 a S15-9) n'est degradee. |
| **S16-3 + S16-4** - une puce de niveau active et une proposition deja essayee gardent leur apparence sous le pointeur (**un seul lot indivisible**, `theo-archi`) | Transverse (garde-fou de non-regression, `ADR-0046`) | `lea-dev` | Aucun critere testable reecrit - les deux sont deja declaratifs et encodables tels quels. Deux precisions de forme, gratuites : le `:focus-visible` de S16-3 doit passer par la constante qui enumere les etats gardes (sinon vrai a l'ecrit, garde par rien) ; les quatre positions cycliques de S16-4 restent un constat de `nadia-prof`, le garde-fou ne verifiant que des declarations. | Les deux criteres testables de chaque story tenus, verifies par le garde-fou derive d'`ADR-0046` (amende) sur mutation reelle (`.proposition.deja-essayee` est le seul cas qui rougit, chiffre par `theo-archi`), `mvn verify` vert ; puis constat de `nadia-prof` sur les quatre positions et plusieurs items (degrade a s15 a une position et un item). |
| **S16-5** - tout libelle rendu par le modele est soumis au meme garde-fou d'accentuation que le texte ecrit en clair | Transverse (garde-fou de redaction, prealable au filtre par matiere) | `sofia-sm` | Critere 1 **reecrit** : plus "une lettre qui devrait porter un accent" (non decidable sans dictionnaire), mais *toute chaine exposee par le port entrant a une vue est dans l'inventaire, avec son motif ; une chaine hors inventaire fait echouer la construction en se nommant* - meme moyen (inventaire de chaines) que le garde-fou existant. Critere 2 (exemption "CP"/"CM1") encodable tel quel, **le mieux ecrit des vingt-et-un** selon `theo-archi` ; une addition : l'exemption doit etre comptee par `LesGardeFousSontMisALEpreuveTest`. | Les deux criteres tenus, `mvn verify` vert. Aucun rendu ne changeant pour l'enfant, aucun constat de `nadia-prof` n'est attendu par construction - elle peut le demander si elle le juge necessaire. |
| **S16-6** - la phrase qui nomme le filtre en cours apparait aussi apres un changement de niveau confirme pendant une partie | Transverse (ferme la reserve **S15-D**) | `sofia-sm` | Critere 2 **renforce gratuitement** : plus une comparaison entre deux elements d'un seul ecran, mais une propriete **derivee sur tout ecran assemble** - le niveau nomme par la phrase et celui de la puce active sont toujours le meme, aucun des deux n'apparait jamais sans l'autre. Ferme S15-D **et** les routes futures, pas seulement celle-ci. | Les deux criteres (le second sous sa forme renforcee) tenus, `mvn verify` vert ; puis constat ecrit de `nadia-prof` (`FICHE-barre-entree-niveau.md`). |
| **S16-7** - le garde-fou de provenance des classes amont ferme son propre trou nomme | Transverse, dette d'outillage (pas de besoin d'enfant - voir arbitrage ci-dessous) | `lea-dev`, apres S16-3/S16-4 | Pas de critere de backlog : mecanisme deja enonce au mot dans `avis-faisabilite.md` de s14, section 12. Un test verifie que les classes des modules amont sont chargees depuis l'arbre du depot et non depuis le depot local Maven, la provenance se lisant sur l'URL du chargeur de classes. | Le garde-fou existe, vert sur `bash outils/mvn-jdk21.sh -B clean verify` (reacteur complet), rouge sur une invocation partielle du meme module sans `-am` - **la preuve de morsure est cette commande elle-meme**, deja disponible sans rien construire de plus. |

## Ce qui entre au perimetre par mon arbitrage, et pourquoi

**S16-7 entre au perimetre.** `theo-archi` la recommande (petit lot, morsure gratuite - "la commande
sans `-am` **est** le cas fautif") ; `camille-po` l'a explicitement laissee hors de son mandat
("question d'architecture et de dette de production", `DEC-0037`) et me l'a routee comme un
arbitrage de capacite. Je la retiens : son cout est le plus petit des sept, elle ferme un trou que
`CLAUDE.md` lui-meme nomme depuis s14 sans le fermer, et sa preuve de morsure ne demande aucune
mutation a fabriquer. Elle n'a pas de "besoin d'enfant" formulable et n'entre donc pas dans
`docs/backlog/backlog.md` sous le gabarit habituel - je la gele directement ici, comme `DEC-0037`
m'y autorise explicitement ("elles doivent apparaitre dans le perimetre que `sofia-sm` gele").

## Ce que je tranche sur la sortie datee d'`ADR-0042`

**Je retiens la clause de cloture que `theo-archi` propose.** Le predicat `estRemplacant` est
differe pour la troisieme fois consecutive (s13, s14, s15, maintenant s16), et aucune des six
stories de ce sprint ne cree son declencheur (verifie par `theo-archi`, confirme par moi sur la
liste des sept stories retenues - aucune n'ouvre d'ecran de catalogue pendant une partie en cours).
Un quatrieme report silencieux serait exactement le defaut que ce depot a deja sanctionne
(`ADR-0042` lui-meme le nommait a sa propre cloture de s13 comme la dette a eviter). **Clause de
cloture retenue** : si le declencheur n'a pas joue a la cloture de s16, `theo-archi` change le
statut d'`ADR-0042` (de `Accepte` a une forme qui dit l'attente reelle) dans le meme geste que la
review, plutot qu'un quatrieme report constate sans consequence. Je le route a `theo-archi` avec
cette echeance ecrite ; ce n'est pas a moi de choisir la forme du nouveau statut, seulement de fixer
qu'il change si le declencheur ne joue pas.

**La migration du marqueur d'ecran de jeu reste hors perimetre**, malgre le fait mesurable que
`theo-archi` apporte (son arbitrage du 2026-08-18 a supprime le dernier consommateur du selecteur
`.ecran-de-jeu`, rendant sa crainte de vocabulaire mixte de s14 fausse dans son sens). Motif : lot
petit a moyen dont le cout domine dans la reverification d'une quinzaine de preuves de morsure
existantes, **jamais devant les sept stories qui corrigent ce qu'un enfant voit** - recommandation
de `theo-archi` lui-meme, que je retiens telle quelle. Candidate pour s17, sans clause de cloture
cette fois : rien n'urge a la fermer avant une autre date que sa propre place dans un sprint futur.

## Trois faits portes sans les adoucir

1. **Troisieme sprint consecutif sans mouvement de `MATRICE-couverture.md`** : 23 lignes
   `couvert-valide`, 5 `en cours`, 46 non couvertes sur 74, inchange depuis s13. `camille-po` pose
   une limite explicite dans `DEC-0037`, que je reprends comme engagement de cloture au meme titre
   qu'une porte de sortie : **si s17 ne rouvre aucune ligne de la matrice, ce sera a `sofia-sm` et
   `camille-po` de l'ecrire comme un changement de direction du produit, pas comme une continuation
   silencieuse.**
2. **Le filtre par matiere est reporte une seconde fois** (premier report : `DEC-0032`, s14 vers
   s15 ; second : `DEC-0037`, s15 vers s16 - il ne fait pas partie de ce perimetre non plus).
   `camille-po` le compte explicitement comme un second report, pas comme un non-evenement. Ce qui
   le debloquerait : **S16-5 leve le prealable de nomenclature d'accentuation** (un des deux motifs
   de `DEC-0032`) ; **le second motif reste intact** - la reserve **R-BARRE-3/S14-B**, que
   `nadia-prof` a datee elle-meme comme devenant bloquante "le jour ou un second axe de filtre
   s'ajoute", et pour laquelle **aucune proposition de forme n'existe a ce jour** chez
   `ines-design`. Le filtre par matiere reste donc bloque par ce second motif meme apres s16.
3. **Ce sprint revient a l'ordre normal du process, apres s15 qui l'avait quitte entierement.** Fait
   mesurable et non un satisfecit : un avis de faisabilite rendu avant le premier commit a produit
   sept criteres reecrits et un renforce, sur six stories qui n'avaient encore aucune ligne de code
   - le cout de la correction est nul, contre le cout constate a chaque lot de s15 (corrections
   apres fusion, un lot pedagogique reste hors branche plusieurs heures, trois arbitrages sans trace
   `DEC` pendant toute leur duree de vie en production).

## Correction du 2026-08-19 (trouvee par camille-po, s17)

Le point 1 ci-dessus dit "23 lignes `couvert-valide`, 5 `en cours`, 46 non couvertes sur 74" :
**faux sur trois des quatre nombres**, recopie sans reverification depuis `DEC-0037` (section 4),
elle-meme fautive des son premier commit. Methode et etat reel verifies par `camille-po` dans
`squad/programme/MATRICE-couverture.md`, section "Synthese au sprint 17 (2026-08-19)" - une puce de
premier niveau des quatre referentiels de `squad/programme/` = une ligne de competence, verifiee
par deux chemins independants : **9 lignes `couvert-valide`, 5 `en cours`, 32 non couvertes, sur 46
lignes de competence au total** (19,6 %, non 31,1 %). Seul "5 en cours" etait juste. Je ne corrige
pas la ligne d'origine : je verse ici la lecture correcte de la source, avec sa methode. La limite
posee dans `DEC-0037` (engagement de cloture pour s17) ne depend pas de la valeur exacte de ce
chiffre et reste inchangee ; elle est plus pressante sous le taux reel que sous le taux fautif.

## Ce que je decide sur les deux documents encore dus de la cloture de s15

**`confirmation-archunit.md` de `theo-archi` est livre** (fusion `ea4433f`) - sixieme absence de
cette publication depuis l'origine, et la premiere ou l'absence est nommee a la cloture par
quelqu'un d'autre que lui (`sofia-sm`, `review.md` de s15). Ce point est clos.

**Le rapport qualite de `yanis-qa` pour s15 est declare perime, et je le dis plutot que de le
laisser trainer une troisieme fois.** Son objet - attester le Done technique de s15 avant la
cloture de s15 - n'existe plus : s15 est clos depuis deux jours, et le compte de tests et de regles
qu'il aurait porte a deja ete verifie de facon independante trois fois sur le meme arbre (par moi a
la cloture de s15, par `theo-archi` a la publication tardive de sa confirmation, par moi de nouveau
ci-dessus a l'ouverture de s16) - une quatrieme confirmation du meme chiffre n'ajouterait rien.
**Ce que je ne perds pas** : la duty distinctive de `yanis-qa`, la mutation-morsure d'un garde-fou
recemment ecrit ou modifie, n'a jamais ete faite sur `ADR-0046` (le garde-fou d'etat interactif tiers
de s15, precisement celui que `S16-3`/`S16-4` va corriger). Je l'affecte donc a `yanis-qa` **dans
s16**, sur le lot de `lea-dev` (`S16-3`/`S16-4`) des qu'il est fusionne - la cible la plus fraiche et
la plus pertinente, pas une reconstitution a vide d'un rapport perime.

**La relecture du `relecteur-pedagogique` pour s15 n'est pas perimee : une convention de redaction
ne perime pas.** Contrairement au rapport qualite, son objet (verifier tirets, guillemets, emoji,
chemins de poste, accentuation, paraphrase de code) reste identique que le sprint soit clos ou non.
Je l'affecte a `relecteur-pedagogique` **dans s16**, en un seul geste ouvrant sur les documents
encore jamais relus de s15 (`DEC-0035`, `planning.md`/`review.md`/`retro.md` de s15, l'amendement
d'`ADR-0046`, `confirmation-archunit.md`) puis, au fil de l'eau, sur les lots de s16 des que leur
Done technique est atteint - c'est l'ordre normal de l'etape 5 de `squad/README.md`, section 9.

## Affectations et sequencement

- **`marc-dev`** : `S16-1` puis `S16-2`, **dans cet ordre et par le meme auteur** - les deux lisent
  et modifient tres probablement `PartieController.java` (cookie de reprise pour l'un, en-tete de
  reponse de la route de retour pour l'autre) ; je ne les parallelise pas sur deux forks distincts
  du meme fichier. `S16-2` forke de `round-two` **apres** la fusion de `S16-1`, pas de la tete
  d'aujourd'hui.
- **`lea-dev`** : `S16-3`+`S16-4` (lot indivisible), puis `S16-7`.
- **`sofia-sm`** (casquette dev) : `S16-5`, puis `S16-6`.
- **`yanis-qa`** : traduit les criteres en tests au fil des lots, comme a chaque sprint ; mutation-
  morsure de `ADR-0046` sur le lot de `lea-dev` des qu'il est fusionne, en substitution du rapport
  qualite perime de s15.
- **`relecteur-pedagogique`** : ouvre sur le retard de s15 nomme ci-dessus, puis relit chaque lot de
  s16 apres son Done technique.
- **`nadia-prof`** : Done pedagogique du sur `S16-1`, `S16-2`, `S16-6` (rendu change) ; sur
  `S16-3`/`S16-4`/`S16-5` a sa discretion (garde-fous et etats neutres, aucun rendu ne change par
  construction, `DEC-0037` le dit deja).
- **`theo-archi`** : `ADR-0047` et `ADR-0048` passent `Propose` -> `Accepte` dans le commit du lot
  qui les code (`S16-1`, `S16-2`) ; amendement d'`ADR-0046` deja fusionne ; clause de cloture sur
  `ADR-0042` a la review si son declencheur n'a pas joue ; question d'architecture de `DEC-0032`
  point 2 (nomenclature de matiere) toujours sans reponse ecrite, routee de nouveau.

## Obstacles routes

| Description | Categorie | Destinataire | Ce qui manque pour clore |
|---|---|---|---|
| `S16-1` et `S16-2` touchent tres probablement le meme fichier | technique / methode | sofia-sm | Sequencement retenu ci-dessus (meme auteur, meme ordre) - pas d'action supplementaire tant que le fichier reel n'est pas ouvert |
| Question d'architecture de `DEC-0032` point 2 (nomenclature de matiere accentuee), sans reponse ecrite depuis le 2026-08-17 | produit + technique | theo-archi | Reponse ecrite avant tout filtre par matiere - second motif de blocage inchange par ce sprint |
| Aucune proposition de forme pour un second axe de filtre combine (R-BARRE-3/S14-B) | design | ines-design | Proposition, puis arbitrage prealable de `nadia-prof`, prealable au filtre par matiere |
| Rapport qualite de s15 perime | technique / methode | sofia-sm (decide), yanis-qa (duty reportee) | Clos par cette decision - la duty de mutation-morsure est reportee sur le lot `S16-3`/`S16-4` |
| Relecture de s15 encore due | redaction | relecteur-pedagogique | Ouverture explicite sur le retard, affectee ci-dessus |
| Migration du marqueur `ecran-de-jeu` vers un attribut, motif renforce par un fait mesurable | technique | theo-archi (mecanisme), sofia-sm (capacite) | Candidate s17, jamais devant les stories qui corrigent ce qu'un enfant voit |
| Predicat `estRemplacant` d'`ADR-0042`, troisieme report | technique | theo-archi | Clause de cloture retenue ci-dessus : declencheur ou changement de statut a la review de s16 |
| R-BARRE-1 (grands nombres CM1, +22 px) | pedagogie / produit | camille-po | Story candidate, hors chantier de l'en-tete, non ouverte ce sprint |
| Dependance a une seule personne pour toute mesure de rendu | technique / methode | sofia-sm (capacite), camille-po | Inchange, nomme de nouveau par `camille-po` dans `DEC-0037` ; aucune decision de capacite prise ce sprint |
| R-BARRE-4 (F11, lecteur d'ecran) | pedagogie / accessibilite | hors de la portee de la squad | Inchange depuis s13 |
| Reserve F11 (ecoute reelle au lecteur d'ecran) | pedagogie / accessibilite | hors de la portee de la squad | Inchange depuis s13 |

## Couverture programme

**Inchangee, decision explicite de `camille-po` dans `DEC-0037`, que je gele telle quelle.** Aucune
des sept stories ne cible une ligne de `squad/programme/MATRICE-couverture.md`. Voir la limite
posee au point 1 de la section "Trois faits portes sans les adoucir" ci-dessus.

## Decisions prises

1. **L'objectif est gele** sur les sept stories du tableau ci-dessus (six proposees par
   `camille-po`, une septieme - `S16-7` - ajoutee par mon arbitrage de capacite), avec les criteres
   dans leur forme reecrite par `theo-archi` et les affectations nominatives ci-dessus.
2. **`S16-7` entre au perimetre** par decision de `sofia-sm` : petit lot, morsure gratuite, aucun
   besoin d'enfant a formuler, gelee directement en planning plutot qu'ecrite en story de backlog.
3. **La clause de cloture de `theo-archi` sur `ADR-0042` est retenue** : changement de statut a la
   review de s16 si le declencheur n'a pas joue, plutot qu'un quatrieme report silencieux.
4. **Le rapport qualite de s15 est declare perime** ; sa duty distinctive (mutation-morsure d'un
   garde-fou recent) est reportee sur le lot `S16-3`/`S16-4` de s16, la cible la plus pertinente.
5. **La relecture de s15 n'est pas perimee** : affectee a `relecteur-pedagogique` en ouverture de
   s16, avant les lots du sprint courant.
6. **`S16-1` et `S16-2` sont sequencees sur le meme auteur** plutot que paralleles, pour eviter un
   conflit previsible sur `PartieController.java`.
7. **La migration du marqueur d'ecran de jeu reste hors perimetre**, malgre un motif renforce, sur
   la recommandation explicite de `theo-archi` (jamais devant les stories qui corrigent ce qu'un
   enfant voit).
8. **`MATRICE-couverture.md` n'est pas touchee**, decision de `camille-po` gelee telle quelle, avec
   la limite explicite posee pour s17.
