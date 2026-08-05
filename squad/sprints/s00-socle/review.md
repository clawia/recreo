# [Sprint s00] Review - 2026-08-04

- **Horodatage de debut** : 2026-08-04 15:39
- **Horodatage de fin** : 2026-08-04 23:57
- **Duree effective** : 8h18
- **Anime par** : sofia-sm

## Increment livre

Un socle applicatif Java 21 / Spring Boot / Clean Architecture a cinq modules, verifie par 21
regles ArchUnit et un garde-fou de CI teste, sert de bout en bout une manche du jeu bandit
manchot sur le contenu "additions dans la limite de 10" (mathematiques, CP) - Done technique
atteint, Done pedagogique refuse par `nadia-prof` sur trois motifs portant sur le texte lu par
l'enfant, un score illisible au CP et un ecran d'erreur sans issue.

## Rapport qualite - yanis-qa

- **Commande** : `JAVA_HOME=<JDK 21> mvn -B verify`
- **Resultat** : vert. `yanis-qa` a mesure 80 tests stables sur trois executions repetees
  (commit `2f93e80`) ; les ajouts ulterieurs de `theo-archi` (commit `f2bef10`) portent le total
  a **92 tests, 0 echec**, confirmes independamment par `confirmation-archunit.md`.
- **Couvert** : les quatre criteres numeriques du contenu additions CP a graine fixe ; le parcours
  de jeu complet a trois niveaux (domaine, controleur, HTTP bout en bout) y compris que la bonne
  reponse ne fuite jamais avant le coup de l'enfant ; le decouplage mecanique/contenu, structurel
  (ArchUnit) et desormais aussi a l'execution (une meme mecanique joue deux contenus reellement
  differents) ; l'artefact reellement empaquete (`JarRecreoIT`, fat jar lance en processus separe) ;
  le signal visuel juste/faux verifie structurellement (classes CSS) et relu a la main (forme et
  couleur distinctes).
- **Non couvert** : le vocabulaire de CP et la formulation de la consigne (Done pedagogique, pas
  un trou de test) ; la suffisance perceptive reelle du signal visuel (demanderait un navigateur
  pilote, hors stack) ; le garde-fou de prefixe de commit n'avait, au moment du rapport de
  `yanis-qa`, aucun test automatise - remonte a `sofia-sm`, depuis comble par `ADR-0009` (voir
  obstacles ci-dessous) ; quatre regles ArchUnit de garde (`ADR-0005`, `ADR-0006` x3, `ADR-0007`)
  n'avaient pas de contre-exemple au moment du rapport - egalement remonte, egalement comble par
  la suite (`f2bef10`).
- **Tests instables** : aucun. Trois executions repetees de la suite, resultat identique a chaque
  fois ; tout hasard de test recoit une graine fixe injectee, seule la production utilise un
  hasard non seede.

## Confirmation ArchUnit - theo-archi

- **Commande** : `JAVA_HOME=<JDK 21> mvn -B verify`
- **Resultat** : **BUILD SUCCESS**, code de sortie 0, 92 tests, 0 echec. Second garde-fou,
  `bash .github/scripts/verifier-prefixes-test.sh` : 14 cas, tous verts.
- **Regles ajoutees ce sprint** : aucune regle ArchUnit nouvelle (les 21 regles du depot ont
  toutes ete ecrites au premier commit de code, `e91f308`). Une regle **reecrite** parce qu'elle
  ne mordait pas : `DifficulteTest.leContratDeContenuNePorteQueDuTexte` (`ADR-0006`) laissait
  passer un `int` (paquet `java.lang` en ArchUnit) et un `Set<Operation>` (`haveRawType` ignore
  les arguments generiques) - exactement les deux formes reelles de la dette n°2 de `main`.
  Non-vacuite des regles de garde : 5 sur 21 contre-exemplifiees au premier commit du sprint, 7
  apres le passage de `yanis-qa`, **21 sur 21** apres ce passage - mesuree par mutation du montage
  de test (17 des 18 tests de `LesReglesMordentTest` rendus rouges par une fabrique d'import
  pointee sur un paquet inexistant), pas seulement affirmee.

## Relecture - relecteur-pedagogique

- **Corrections appliquees** (commit `89f7bae`) : `CLAUDE.md`, `squad/README.md` et
  `squad/EQUIPE.md` corriges pour decrire le code livre (cinq modules dont `adapter-content`) et
  non la cible documentee au depart (`adapter-persistence`, "integration H2"), conformement a ce
  qu'`ADR-0001` et `DEC-0003` avaient deja acte ; libelle de l'etape CI aligne, sa commande restant
  intacte ; la distinction des deux registres de redaction actee par `DEC-0002` rendue explicite ;
  Javadoc de `ContenuConfiguration` nettoyee d'une reference a une classe supprimee.
- **Points a trancher** : aucun point non tranche remonte par le relecteur ce sprint - l'ecart
  identifie au planning (decision n°2 de `planning.md`) est directement corrige dans le meme
  commit, sans renvoi supplementaire.

## Obstacles du sprint

| Description | Categorie | Agent route | Decision liee | Statut |
|---|---|---|---|---|
| JDK 21 absent du `PATH` (JRE 1.8 par defaut), toolchains Maven mal nommees | capacite / technique | sofia-sm | decision n°1 de `planning.md` | clos |
| Ecart entre `CLAUDE.md` (module `adapter-persistence`, "integration H2") et `ADR-0001` / `DEC-0003` | redaction | relecteur-pedagogique | - (correction directe) | clos par `89f7bae` |
| Catalogue de repli (`CatalogueDeRepliConfiguration`) contraire au chargement eager sans repli silencieux d'`ADR-0007` | technique | lea-dev | - (correction directe) | clos par `abc8495` |
| Regles de garde sans contre-exemple, dont 4 signalees explicitement par `yanis-qa` (`ADR-0005`, `ADR-0006` x3, `ADR-0007`) et 6 autres trouvees a l'inventaire | technique | theo-archi | amendements `ADR-0001` a `ADR-0008` | clos par `f2bef10` |
| Garde-fou de prefixe de commit non teste (script shell, aucun test automatise) | qualite puis technique | yanis-qa puis theo-archi | `ADR-0009` | clos par `f2bef10` |
| Refus pedagogique de `bandit-manchot-additions-cp` (trois motifs bloquants) sans traduction en story ni exigence positive pour le texte de categorie B | pedagogie | camille-po | `DEC-0002` (amendee) + `docs/backlog/backlog.md` section Sprint 1 | **clos comme obstacle de routage** par `881768a` (`camille-po` a traduit le refus en stories S1-1 a S1-9 et amende `DEC-0002`) **et** l'increment `bandit-manchot-additions-cp` reste **non valide** pedagogiquement (verdict REFUSE), trace dans `squad/validation/FILE-ATTENTE.md` - c'est l'etat normal d'un Done pedagogique refuse, pas un obstacle ouvert |
| Trou etroit dans `leContratDeContenuNePorteQueDuTexte` reecrite : un enum arithmetique place dans le paquet de contrat lui-meme (`com.recreo.domain.content`) passe encore la regle et `leContratDeContenuEstFerme` | technique | theo-archi | - | ouvert, reporte au sprint 1 |
| Aucun agent n'a mandat sur le design d'interface et l'accessibilite visuelle : `recreo.css` (story S0-5) ecrit par `marc-dev` hors de son mandat, puis juge par `nadia-prof` hors du sien (`FICHE-bandit-manchot-additions-cp.md`) ; les stories S1-6 et S1-9 du backlog n'ont aucun agent naturel | capacite | sofia-sm | amendement n°4 de `retro.md` - creation d'un neuvieme agent prevue au planning du sprint 1 | route - aucune definition d'agent n'existe encore dans `.claude/agents/` |

Rappel : un obstacle n'est `clos` que si le fichier de decision existe physiquement. Verifie pour
chaque ligne ci-dessus : `89f7bae`, `abc8495`, `f2bef10`, `ADR-0009-les-garde-fous-de-ci-sont-des-scripts-testes.md`
et `881768a` existent tous sur le disque et ont ete lus avant d'ecrire ce tableau.

## Porte pedagogique - nadia-prof

- **Verdict** : REFUSE
- **Fiche** : `squad/conformite/FICHE-bandit-manchot-additions-cp.md`
- **Motif** : trois motifs bloquants, tous actionnables. (1) le texte affiche a l'enfant sur
  l'ecran de retour faux et sur l'ecran de fin de partie comporte des fautes d'accentuation
  ("ca" pour "ça", "reponse"/"etait" sans accent) dans les gabarits `adapter-web`, alors que la
  consigne portee par `catalogue.yaml` est correctement accentuee. (2) le score s'affiche en
  notation "x / y" (barre oblique), non lisible par un enfant de CP et placee au-dessus d'une
  expression arithmetique qu'elle risque de parasiter. (3) une session HTTP perdue renvoie un
  corps JSON d'erreur 500 brut dans la zone de jeu, en anglais, sans bouton pour repartir. Le
  contenu mathematique lui-meme est juge exact et n'a besoin d'aucune correction (61 manches
  auditees, zero faute arithmetique, aucune strategie de position exploitable).
- **Reserves** : sans objet au sens strict (verdict REFUSE, pas VALIDE SOUS RESERVE). La fiche
  trace neanmoins sept reserves non bloquantes (partie sans fin ni sortie, tiers des manches a
  operande nul, aucune progression, signal juste/faux illisible sans distinguer les couleurs,
  texte trop petit, trois libelles hors vocabulaire de CP, perte de partie au rechargement) ;
  `camille-po` les a transformees en stories S1-4 a S1-9 du backlog, independamment des trois
  motifs de refus (S1-1 a S1-3).

## Decisions prises

Aucune decision prise pendant cette review au sens d'un nouvel `ADR` ou `DEC` : les decisions du
sprint (`ADR-0009`, les amendements d'`ADR-0001` a `ADR-0008`, l'amendement de `DEC-0002`) ont
toutes ete prises par leur agent proprietaire pendant les etapes de developpement et de porte qui
precedent cette ceremonie, et sont recapitulees dans le tableau des obstacles ci-dessus et dans
`RAPPORT.md`.
