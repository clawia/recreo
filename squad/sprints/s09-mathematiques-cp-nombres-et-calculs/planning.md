# [Sprint s09] Planning - 2026-08-12

- **Horodatage de debut** : 2026-08-12 09:37
- **Anime par** : sofia-sm
- **Participants** : camille-po, theo-archi (avis deja rendus, cites ci-dessous), marc-dev,
  lea-dev, sofia-sm (developpement a venir), yanis-qa (traduction des criteres en tests au fil des
  commits), nadia-prof (porte pedagogique en review, et arbitrage possible en cours de sprint si
  `camille-po` le demande - voir obstacles).
- **Lu avant d'ecrire ce fichier** : `docs/backlog/backlog.md`, section "Sprint 9" (S9-1 a S9-4) ;
  `DEC-0019-perimetre-sprint-9-quatre-lignes-textuelles-de-mathematiques-cp-denombrement-et-monnaie-differes.md` ;
  l'amendement de clarification dans `DEC-0017` ; `ADR-0032-deux-items-d-un-meme-paquet-ne-presentent-jamais-le-meme-ecran.md` ;
  `ADR-index.md` et `DEC-index.md` a jour ; `squad/sprints/s09-mathematiques-cp-nombres-et-calculs/avis-faisabilite.md` ;
  les quatre fiches de conformite neuves sous `squad/conformite/` ; `squad/programme/MATRICE-couverture.md`.
- **Lignee verifiee avant d'ecrire ce fichier** : `git merge-base round-two s09-camille-po` et
  `git merge-base round-two s09-theo-archi` pointent tous deux sur `086861f`, la tete de
  `round-two` a l'ouverture du sprint. Aucune derive, aucun provisionnement sur la mauvaise branche.

## Cadre du sprint, tranche par l'utilisatrice

Mathematiques CP, domaine "Nombres et calculs". **Aucun travail de rendu** : ni mesure, ni
garde-fou CSS neuf, ni arbitrage visuel d'`ines-design` ou de `nadia-prof` sur des pixels. Les
garde-fous CSS existants restent en place et verts, on n'en produit pas de nouveau. Sprint de pur
contenu YAML sur la mecanique bandit manchot existante : zero ligne de `recreo.css`, zero gabarit,
zero mecanique neuve.

## Fusion des lots

Perimetres verifies par `git diff --name-status` avant chaque fusion, pas sur la seule parole de
chaque agent.

| Lot | Branche | Tete | Perimetre verifie |
|---|---|---|---|
| `camille-po` | `s09-camille-po` | `48c16dd` | `docs/backlog/backlog.md` (section Sprint 9), quatre fiches de `squad/conformite/`, `DEC-0017` (paragraphe de clarification), `DEC-0019` neuf, `DEC-index.md`, `squad/programme/MATRICE-couverture.md` (quatre lignes a `en cours`). Aucun fichier de code. |
| `theo-archi` | `s09-theo-archi` | `0427fad` | `ADR-0032` neuf, `ADR-index.md`, `squad/sprints/s09-mathematiques-cp-nombres-et-calculs/avis-faisabilite.md`, un test neuf dans `adapter-content` (`AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest`), cinq paquets YAML de contre-exemple. |

Aucun recouvrement de fichier entre les deux lots : `camille-po` ecrit dans `decisions/produit/`,
`conformite/`, `programme/` et `docs/backlog/` ; `theo-archi` dans `decisions/adr/`,
`sprints/s09-.../` et `adapters/adapter-content/`. Fusionnes dans cet ordre (produit, architecture)
par deux `git merge --no-ff` successifs, **aucun conflit**. Tete de `round-two` apres fusion :
**`7c6e6e5`**.

### Porte technique verifiee avant ce planning

- `outils/verifier-jdk.sh` : `java` du `PATH` en 1.8.0_202, JDK de compilation 21.0.10, `JAVA_HOME`
  vide hors commande, toolchains non lu par Maven sous son nom present sur le disque. `JAVA_HOME`
  positionne sur le JDK 21 le temps de la commande.
- `mvn -B clean verify` : **BUILD SUCCESS**, six modules en SUCCESS.
- Compte de tests, methode : somme du bloc `Results:` de chaque module apres un `clean` : 43
  (domain) + 7 (application) + 178 (adapter-web) + 45 (adapter-content) + 77 (bootstrap, surefire)
  + 3 (bootstrap, failsafe) = **353**, conforme a l'attendu (347 + les 6 tests neufs de
  `theo-archi`).
- Compte de regles ArchUnit, methode : recherche du motif `ArchRule <nom> =` dans les fichiers
  `.java` sous `src/test/java` des cinq modules de code declares au pom agregateur (exclusion du
  faux module de test-ressources de `LesGardeFousSontMisALEpreuveTest`, jamais compile comme regle
  du depot) : 1 (adapter-web) + 27 (bootstrap) = **28**, inchange. Conforme : `ADR-0032` porte une
  clause executable en test JUnit ordinaire, pas une `ArchRule` neuve - le contenu d'un YAML charge
  a l'execution n'est pas visible d'ArchUnit.

## Objectif du sprint (gele)

**A la review, quatre paquets de contenu neufs (soustractions, complements a 10, comparaison de
deux nombres par un signe, problemes additifs a une etape) sont chargeables par le schema
`type: question` existant, satisfont `ADR-0032` (aucun item ne repete l'ecran d'un autre du meme
paquet), portent au plus quatre propositions chacun, et passent devant `nadia-prof` comme tout
contenu neuf ; les quatre lignes correspondantes de `squad/programme/MATRICE-couverture.md`
passent de `non couvert` a `couvert-valide` si et seulement si `nadia-prof` leve elle-meme la
barre apres avoir joue chaque increment. Aucune ligne de rendu ne bouge.**

**Gele** : le denombrement et la monnaie restent `non couvert` ce sprint (voir obstacle leve
ci-dessous, recommandation ecrite pour un sprint ulterieur). Le "ranger" de la ligne comparaison
reste differe. `S9-2` s'ouvre en developpement : `camille-po` a tranche `nombreDeManches` par
`DEC-0020` (voir obstacle leve ci-dessous et section suivante).

## Sequencement et arbitrage de capacite

Quatre stories independantes en fichiers (chacune son propre paquet YAML sous `contenu/`) ; la
quatrieme a ete bloquee a l'ouverture du sprint, debloquee en cours de sprint par `DEC-0020` (voir
point 2) :

1. **S9-1, S9-3, S9-4 - ne dependent d'aucun arbitrage, demarrent maintenant.** Chacune ajoute un
   fichier YAML neuf et une ligne dans `catalogue.yaml`. Seul point de vigilance de sequencement :
   les trois lots touchent le meme fichier `catalogue.yaml` en ajout de ligne - risque de conflit
   de fusion mineur, pas de blocage : chaque agent ajoute sa ligne en fin de fichier dans son
   propre commit, je fusionne les trois lots l'un apres l'autre et resous un eventuel conflit
   trivial d'ajout de ligne moi-meme en review.
2. **S9-2 - etait bloquee avant tout developpement, debloquee par `DEC-0020`.** Les quatre entrees
   du catalogue declarent `nombreDeManches: 10`, donc onze items distincts exiges par `ADR-0023` ;
   la forme canonique `a + ? = 10` n'en admet que onze (neuf hors cas degeneres). `theo-archi` a
   ferme la troisieme sortie (completer par des copies, desormais rouge par `ADR-0032`). Deux
   sorties restaient ouvertes, routees a `camille-po` : baisser `nombreDeManches` pour ce jeu
   (plancher technique 4) ou elargir les formes d'items admises (decision alors pedagogique,
   `nadia-prof`). `camille-po` a tranche par `DEC-0020` : `nombreDeManches` baisse a **7**
   (marge d'un item de plus que le plancher, pour tenir si `nadia-prof` retire les deux cas
   degeneres `0 + ? = 10` et `10 + ? = 0` en jouant l'increment), sans elargir les formes d'items -
   asymetrie de duree de partie avec les autres jeux du catalogue assumee par ecrit dans la
   decision. Elle a aussi corrige le critere qu'elle avait ecrit elle-meme et qui etait
   mathematiquement impossible ("au moins 20 items" sur un espace canonique qui en offre onze au
   maximum), remplace par "au moins 9 items". **S9-2 est affectee a `marc-dev`, sur une seconde
   branche `s09-marc-dev-complements` independante de son lot S9-1** : je le dispatche sans
   attendre une prochaine ceremonie, la casquette dev et la casquette Scrum Master ne se cumulant
   jamais chez moi.

## Contrainte transverse a rappeler a chaque developpeur

**Quatre propositions au plus par item, sans exception, pour tout paquet neuf.** Une cinquieme
colonne rougit `ChaqueColonneQueLeContenuPeutProduirePorteSonDecalageTest` (`ADR-0029`), et le seul
chemin vert serait de revoir le decalage - donc du design, hors perimetre. `theo-archi` a verifie
que les 429 listes de distracteurs du depot plafonnent deja a 4 : aucun contenu existant ne sert
d'exemple a une cinquieme colonne.

## Stories et gestes retenus

| Story | Titre | Competence visee | Affecte a | Sequence |
|---|---|---|---|---|
| S9-1 | S'entrainer aux petites soustractions en jouant | mathematiques, CP, "Calcul mental - soustractions dans la limite de 10" | `marc-dev` | demarre maintenant |
| S9-3 | Comparer deux nombres avec les signes <, > et = | mathematiques, CP, "Comparer et ranger des nombres jusqu'a 100 (<, >, =)", limitee a la comparaison de deux nombres | `lea-dev` | demarre maintenant |
| S9-4 | Resoudre un petit probleme d'addition ou de soustraction | mathematiques, CP, "Resoudre des problemes simples d'addition/soustraction en situation (1 etape)" | `sofia-sm` (casquette dev) | demarre maintenant |
| S9-2 | Trouver le complement a 10 | mathematiques, CP, "Complements a 10" | `marc-dev`, sur une seconde branche `s09-marc-dev-complements` | demarre maintenant, debloquee par `DEC-0020` |

`yanis-qa` traduit chaque critere d'acceptation retenu en test au fil des commits des worktrees et
fait tourner la suite. `theo-archi` reste disponible tout le sprint pour tout
obstacle technique remonte en cours de developpement, en particulier sur `ADR-0032` et sur la
contrainte des quatre propositions.

## Obstacles a ce planning

| Description | Categorie | Destinataire | Statut |
|---|---|---|---|
| Blocage reel sur S9-2 : les quatre entrees du catalogue declarent `nombreDeManches: 10`, donc onze items distincts exiges par `ADR-0023` ; la forme canonique n'en admet que onze (neuf hors cas degeneres) | technique / produit, sorties nommees par `theo-archi` | `camille-po` | **leve** - `DEC-0020` (2026-08-12) : `nombreDeManches` baisse a 7, formes d'items non elargies, criteres corriges. S9-2 affectee a `marc-dev` sur `s09-marc-dev-complements` |
| Contradiction entre `DEC-0019` (denombrement et monnaie differes pour motif de reconnaissance visuelle) et l'avis de `theo-archi` (les deux lignes sont faisables en texte, la monnaie est prouvee par du contenu deja joue en production sur `main`) ; ce qui reste de l'objection de `camille-po` n'est pas technique mais pedagogique - reconnaitre une piece nommee plutot que montree, denombrer une quantite decrite plutot que montree | produit, avec une question pedagogique sous-jacente que `theo-archi` route explicitement a `nadia-prof` | `camille-po` (avec possibilite d'arbitrage prealable de `nadia-prof`) | **referme pour ce sprint par l'amendement du 2026-08-12 a `DEC-0019`** - la contradiction reelle ne portait que sur le denombrement (motif corrige : le pictogramme-caractere est techniquement possible en texte, sa recevabilite pedagogique et la fiabilite de son rendu restent non jugees) ; sur la monnaie, `theo-archi` **ne contredisait pas** `camille-po`, dont le motif distinguait deja "utiliser" (textuel, prouve) de "reconnaitre" (visuel) - motif confirme et formulation de `MATRICE-couverture.md` corrigee. Les deux lignes restent `non couvert` ce sprint, aucun arbitrage prealable de `nadia-prof` n'est demande maintenant faute de developpeur en attente, mais `camille-po` recommande par ecrit qu'il le soit avant qu'un sprint ulterieur n'ouvre l'une des deux lignes |
| Risque mineur de conflit de fusion sur `catalogue.yaml` entre les trois lots paralleles (S9-1, S9-3, S9-4), chacun y ajoutant une ligne | capacite / sequencement | `sofia-sm` (moi) | **non bloquant** - resolu par sequencement des fusions et resolution triviale en review, pas par une reaffectation |

## Decisions prises

Aucun `ADR` ni aucune `DEC` n'est cree pendant ce planning : `DEC-0019` et `ADR-0032` sont
anterieurs et deja sur le disque, fusionnes ci-dessus. Les decisions prises ici m'appartiennent en
tant que Scrum Master et n'ouvrent ni `ADR` ni `DEC` :

1. **Les affectations nominatives** de la section "Stories et gestes retenus" ci-dessus : `marc-dev`
   porte S9-1, `lea-dev` porte S9-3, `sofia-sm` (casquette dev) porte S9-4.
2. **L'ordre de fusion des lots de developpement** n'est pas fige d'avance : les trois lots sont
   independants en dehors de `catalogue.yaml`, je fusionne dans l'ordre de livraison et resous un
   conflit d'ajout de ligne trivial moi-meme.
3. **Le point de contradiction denombrement/monnaie n'est pas tranche ici** : il a ete route a
   `camille-po`, qui a repondu par l'amendement du 2026-08-12 a `DEC-0019` (voir obstacle
   ci-dessus). Je ne le tranche pas moi-meme, je route sa reponse telle que recue.
4. **S9-2, debloquee par `DEC-0020`, est affectee a `marc-dev`**, sur une seconde branche
   `s09-marc-dev-complements` independante de son lot S9-1 - decision de capacite qui m'appartient
   comme Scrum Master. Je la dispatche sans attendre une nouvelle ceremonie : je portais la
   casquette developpeuse au moment ou l'obstacle s'est leve, et les deux casquettes ne se
   cumulent jamais.

## Prochaines etapes

**Demarrent maintenant**, l'obstacle sur S9-2 etant leve par `DEC-0020` :

- `marc-dev` ouvre un worktree dedie, verifie sa lignee, ecrit le paquet YAML de S9-1
  (soustractions) et sa ligne de `catalogue.yaml`, tests dans le meme commit, sous `ADR-0032` et la
  contrainte des quatre propositions au plus.
- `lea-dev` ouvre un worktree dedie, verifie sa lignee, ecrit le paquet YAML de S9-3 (comparaison
  par signe) et sa ligne de `catalogue.yaml`, tests dans le meme commit, memes contraintes.
- `sofia-sm`, casquette dev, ouvre un worktree dedie, verifie sa lignee, ecrit le paquet YAML de
  S9-4 (problemes additifs) et sa ligne de `catalogue.yaml`, tests dans le meme commit, memes
  contraintes.
- `yanis-qa` traduit chaque critere en test au fil des commits des worktrees.
- `marc-dev` ouvre un second worktree dedie sur `s09-marc-dev-complements`, independant de son
  worktree S9-1, verifie sa lignee, ecrit le paquet YAML de S9-2 (complements a 10, onze items,
  `nombreDeManches: 7`) et sa ligne de `catalogue.yaml`, tests dans le meme commit, sous
  `ADR-0032`, `ADR-0023` et `DEC-0020`.
