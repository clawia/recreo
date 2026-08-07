# [Sprint s06] Planning - 2026-08-07

- **Horodatage de debut** : 2026-08-07 15:35
- **Anime par** : sofia-sm
- **Participants** : theo-archi, camille-po, marc-dev, lea-dev, yanis-qa, sofia-sm. `ines-design` et
  `nadia-prof` sont citees pour les points qui les concernent, sans tache de code ce sprint.
- **Lu avant d'ecrire ce fichier** : `DEC-0013-perimetre-sprint-6.md` et l'amendement du 2026-08-07
  en fin de `DEC-0012-rhabillage-gabarit-menu-minimal-et-ouverture-s06.md` ; `docs/backlog/backlog.md`,
  section Sprint 6 (S6-1 a S6-14, S6-9 reecrite au commit `310d1b0`) ; `avis-faisabilite.md` de ce
  dossier ; `squad/conformite/FICHE-bandit-manchot-additions-cp.md` (arbitrage prealable S6-9 du
  2026-08-07) et `FICHE-bandit-manchot-conjugaison-present-cm1.md` (fiche ouverte, bloc de validation
  vide) ; `ADR-0020`, `ADR-0021`, `ADR-0022` et `ADR-index.md` ; la cloture de s05
  (`RAPPORT.md`, `review.md`, `retro.md`).
- **Lignee verifiee avant d'ecrire ce fichier** : `git rev-parse --abbrev-ref HEAD` rend `round-two` ;
  `git merge-base --is-ancestor round-two HEAD` confirme que `HEAD` (`afa0e3a`, `[THEO] ADR-0022`)
  en descend. Ce depot n'est pas un worktree secondaire.

## Objectif du sprint (gele)

**A la review, la ligne francais CM1 "conjuguer au present" est ouverte sur `cm1-conjugaison.yml`
(six personnes grammaticales, indices ecrits) et jugee par `nadia-prof`, la ligne homophones CM1
franchit ses deux derniers points chiffres vers `couvert-valide` ou reste explicitement en dessous
avec le motif ecrit, et le residuel de S6-9 (les trois gestes de gabarit decrits par l'arbitrage de
`nadia-prof`) est confirme livre et rejoue par elle. Le lot indivisible "Le bon son" (S6-6/S6-7) et
le reste de la dette de forme (S6-8, S6-10, S6-13, S6-14) avancent dans cet ordre selon la capacite
reelle du sprint - voir la section suivante, qui dit lequel cede si la fenetre ne prend pas tout.**

**Deja acquis au gel, pas du travail a faire ce sprint** :

- **S6-11** (un seul `id="jeu"` apres un echange htmx) - tranchee et livree par `ADR-0020`
  (`ToutEcranEchangeEstUnDocumentJouableTest`), commit `a5ea324`.
- **S6-12** (un contre-exemple de garde-fou n'est jamais un bean de l'application) - tranchee et
  livree par `ADR-0021` (`ScanDeComposantsTest`, `AucunContreExempleNEstUnBeanDeLApplicationTest`),
  meme commit.
- Le prealable de `DEC-0013` point 3 (`GET /parties/{jeuId}/manche` sur une partie terminee) - ferme
  par la clause 3 d'`ADR-0020`, symetrique d'`ADR-0019`.
- La part technique de S6-9 (invisibilite de l'entree de menu, couple declaratif marqueur/masquage) -
  tranchee par `ADR-0022`, commit `afa0e3a`.

**Gele** : aucune competence de plus n'entre `en cours` dans `squad/programme/MATRICE-couverture.md`
au-dela de la ligne conjugaison CM1, deja passee `en cours` par `DEC-0013`. Le futur et l'imparfait de
cette meme ligne restent hors sprint, sans date fixee (`DEC-0013`).

## Sequencement des trois chantiers et arbitrage de capacite

`DEC-0013` porte les trois chantiers a egalite de principe et me confie explicitement l'arbitrage si
la fenetre ne les prend pas tous ("c'est `sofia-sm` qui le constate au planning et priorise, comme
`DEC-0012` le prevoit deja"). Je tranche l'ordre, sous ma casquette Scrum Master, sans que cela rouvre
`DEC-0013` sur le fond :

1. **Chantier 1 (S6-3, S6-4, S6-5) - priorite la plus haute.** C'est l'engagement le plus ancien
   envers l'utilisatrice sur le rythme de couverture (`DEC-0011`, confirme par `DEC-0012` : "l'ouverture
   francais garde la priorite" en cas d'arbitrage). Il ferme aussi les deux derniers points chiffres
   par `nadia-prof` sur les homophones CM1, en attente depuis s04.
2. **Chantier 3 hors S6-9 residuel (S6-8, S6-10, S6-13, S6-14) - priorite intermediaire.** Cout
   technique faible (aucun `ADR` requis sur ces quatre stories, `theo-archi` l'a mesure), et S6-13 /
   S6-14 livrent les deux scripts que la retro de s05 a demandes et que j'acte ce planning dans
   `squad/README.md` (voir plus bas) : les laisser filer un sprint de plus ferait porter la regle
   ecrite sans l'outil qui la garde.
3. **Chantier 2 (S6-6 + S6-7, lot indivisible) - priorite la plus basse, et c'est le premier qui
   cede si la fenetre est trop courte.** Motif, chiffre par `theo-archi` : c'est le seul chantier qui
   exige un `ADR` ouvert **au moment ou le lot demarre**, avec la personne qui ecrit les textes
   presente (modelisation du champ `graphie`, choix entre `Verdict` elargi et un accesseur separe) ;
   c'est aussi celui dont le cout est le plus lourd et le moins reductible (un champ dans `Question`,
   42 sites `new Question(` a verifier, un garde-fou neuf avec son jeu fautif obligatoire puisque
   `DETTE_DE_BALAYAGE` est pleine, quinze items et quinze indices a ecrire puis a faire rejouer). Ce
   chantier a deja cede deux fois (s04, s05) sans que cela n'ait jamais ete conteste par
   `nadia-prof` ; le faire ceder une troisieme fois en cas de fenetre courte ne rouvre donc aucune
   promesse. `DEC-0009` reste tenue a la lettre tant qu'il n'a pas livre son Done pedagogique : ce
   n'est pas une regression, c'est la continuation de l'etat deja en vigueur.

**Consequence sur les audits de `nadia-prof`.** Trois audits pedagogiques distincts pourraient se
presenter a sa porte ce sprint : conjugaison CM1 (chantier 1, fiche deja ouverte), homophones CM1 pour
`couvert-valide` (chantier 1), et "Le bon son" (chantier 2). Dans l'ordre de priorite ci-dessus, les
deux premiers sont attendus quoi qu'il arrive a la capacite du sprint ; le troisieme n'a lieu que si
le chantier 2 est effectivement engage et livre.

**Ce que je ne tranche pas ici** : le contenu technique du `ADR` que `theo-archi` ecrira a l'ouverture
du lot S6-6/S6-7 (modelisation du champ, porteur du texte de retour) reste de son ressort exclusif,
au moment ou le lot demarre reellement, pas dans ce fichier.

## Stories retenues

| Story | Titre | Competence visee | Affectee a | Sequence |
|---|---|---|---|---|
| S6-3 | Choisir la bonne forme d'un verbe frequent au present (CM1) | francais CM1, conjugaison, present de l'indicatif seul | marc-dev | chantier 1, en premier |
| S6-4 | Deux items de plus hors voisinage du trou (homophones CM1) | francais CM1, meme ligne que S5-6 | marc-dev | chantier 1 |
| S6-5 | Un indice qui reste du francais (homophones CM1) | francais CM1, meme ligne | marc-dev | chantier 1 |
| S6-8 | Marge laterale sous 1200 px | transverse, accessibilite visuelle | ines-design (proposition), lea-dev (mise en oeuvre) | chantier 3 |
| S6-9 (residuel) | Confirmation des trois gestes de gabarit et verdict en jeu | transverse, navigation/lisibilite CP | lea-dev (confirmation/finalisation des gabarits selon le tableau ecran par ecran de `nadia-prof` et le mecanisme d'`ADR-0022`), nadia-prof (verdict en jouant) | chantier 3 |
| S6-10 | Le bouton d'action reste seul de sa couleur | transverse, hierarchie visuelle CP | ines-design (proposition), lea-dev (mise en oeuvre) | chantier 3 |
| S6-13 | Script de verification de lignee de worktree | transverse, outillage | sofia-sm | chantier 3 |
| S6-14 | Script de verification de JDK 21 | transverse, outillage | sofia-sm | chantier 3 |
| S6-6 | "Le bon son" recompose en 15 items, 3 nasales | francais CP, graphemes/phonemes | theo-archi (ADR et modelisation du champ `graphie`, a l'ouverture du lot), lea-dev (contenu et mise en oeuvre) | chantier 2, cede en premier si la fenetre est courte |
| S6-7 | Un retour qui nomme les lettres et le son (items a variation) | francais CP, meme ligne que S6-6 | theo-archi (meme ADR que S6-6), lea-dev (mise en oeuvre, garde-fou et jeu fautif obligatoires) | chantier 2, meme lot indivisible que S6-6 |

`yanis-qa` traduit chaque critere d'acceptation retenu en test au fil des commits de chaque worktree
et fait tourner la suite. `theo-archi` reste disponible tout le sprint pour le garde-fou nomme par lui
sur S6-3 (comparaison items/`nombreDeManches` par entree de catalogue, a ecrire "si le planning retient
S6-3" - il le retient) et pour tout obstacle technique remonte en cours de developpement.

## Le lot indivisible S6-6/S6-7 - ce qui doit se passer avant d'ecrire une ligne de contenu

`nadia-prof` a tranche que les deux stories se livrent ensemble ou pas du tout
(`squad/conformite/FICHE-bandit-manchot-le-bon-son-cp.md`, avis de conception du 2026-08-06, section
5). `theo-archi` ajoute une seconde raison, technique celle-la : le garde-fou de S6-7 n'a pas de sens
sans le champ `graphie` que S6-6 introduit. Sequence, si le lot est engage :

1. `theo-archi` ouvre un `ADR` (prochain numero libre a l'ouverture reelle du lot, `ADR-0023` en
   l'etat actuel de `ADR-index.md`) avec `lea-dev`, qui ecrira les items et les textes de retour :
   modelisation du champ `graphie`, choix entre `Verdict` elargi et un accesseur separe pour le texte
   de retour, garde-fou neuf et son jeu fautif (`DETTE_DE_BALAYAGE` pleine, aucune exemption
   possible).
2. `lea-dev` recompose le paquet a trois sons (quinze items, quinze indices), reduit
   `SONS_ATTENDUS` de `PaquetsFrancaisS04Test` a trois, ecrit les textes de retour sur les six items a
   variation.
3. `nadia-prof` audite le lot complet (recomposition + retour explicatif) en une seule passe, jamais
   S6-6 seule.

## Consigne de lignee et de JDK - transition vers l'amendement de `squad/README.md`

La retro de s05 propose de sortir la verification de lignee et la consigne JDK de la prose recopiee a
chaque `planning.md` pour les porter par deux scripts permanents, references depuis
`squad/README.md`, section 9. Je l'acte dans ce meme planning (voir plus bas, amendements). Tant que
`outils/verifier-lignee.sh` (S6-13) et `outils/verifier-jdk.sh` (S6-14) n'existent pas encore sur le
disque au moment ou un agent ouvre son worktree ce sprint, la regle reste tenue par la commande
recopiee a la main :

```
git rev-parse --abbrev-ref HEAD
git merge-base --is-ancestor round-two HEAD
```

Sur le JDK : `theo-archi` a verifie aujourd'hui qu'un JDK 21.0.10 est installe et compile sur le poste,
et que le fichier de toolchains Maven de l'utilisatrice declare bien trois JDK dont un 21 - il est
mal nomme, jamais lu par Maven, mais il existe et le JDK qu'il porte fonctionne. Les quatre agents de
s05 avaient donc tort sur le fond (l'absence d'un JDK 21), pas seulement sur la methode de le
constater. Tout agent qui rapporte un `mvn verify` ce sprint positionne `JAVA_HOME` sur un JDK 21
avant de lancer Maven et cite la version majeure obtenue, sans jamais ecrire de chemin de poste dans
un fichier commite.

## Obstacles a ce planning

| Description | Categorie | Statut |
|---|---|---|
| Arbitrage de capacite entre les trois chantiers si la fenetre du sprint ne les prend pas tous | capacite, charge (mon lot propre) | **tranche ci-dessus** : chantier 1 en premier, chantier 3 (hors S6-9 residuel) en second, chantier 2 (S6-6/S6-7) cede en premier |
| Les six actions routees a `camille-po` par `theo-archi` dans `avis-faisabilite.md` (criteres de S6-3, S6-6, S6-7, S6-9, S6-14) | produit | **deja actionnees** avant ce planning : le backlog lu porte deja les criteres corriges (S6-3 : critere sur `nombreDeManches` et sur l'indice ; S6-6/S6-7 : condition de faisabilite nommee ; S6-9 : reecrite au commit `310d1b0` ; S6-14 : deux canaux distincts). Aucune action supplementaire a router. |
| `DEC-0013` point 3 (`GET /parties/{jeuId}/manche` sur une partie terminee) | technique | **clos** par `ADR-0020`, clause 3 |
| S6-6/S6-7 : ouverture d'un `ADR` prealable au demarrage du lot, avec la personne qui ecrit les textes | technique | **sequence ci-dessus**, pas un obstacle bloquant ce planning : `theo-archi` s'y engage au moment ou le lot demarre reellement, si la fenetre le prend |

Aucun obstacle nouveau, hors ceux ci-dessus, n'a ete trouve en lisant l'ensemble des pieces citees en
tete de ce fichier.

## Decisions prises

Aucun `ADR` ni aucune `DEC` n'est cree pendant ce planning : `DEC-0013`, l'amendement de `DEC-0012`,
`ADR-0020`, `ADR-0021` et `ADR-0022` sont anterieurs et deja sur le disque. Les decisions prises ici
m'appartiennent en tant que Scrum Master et n'ouvrent ni `ADR` ni `DEC` :

1. **L'ordre des trois chantiers et la story qui cede en cas de fenetre courte** (section
   "Sequencement des trois chantiers et arbitrage de capacite" ci-dessus) - decision de capacite et de
   priorisation, prevue explicitement par `DEC-0013` point 4 et par le precedent de `DEC-0012`.
2. **Les affectations nominatives** de la section "Stories retenues" ci-dessus.
3. **L'amendement de `squad/README.md`** (sections 4, 7, 9, et l'entree d'outillage) actant les cinq
   lignes du tableau "Amendements au process" de `squad/sprints/s05-lever-le-refus-du-bon-son/retro.md`,
   ecrit dans le meme geste que ce planning plutot que renvoye a une retro future.

## Prochaines etapes

**Demarrent maintenant** :

- `marc-dev` ouvre son worktree, verifie sa lignee, enchaine S6-3 (paquet de conjugaison : formes
  manquantes, six indices, contre le critere de comptage items/manches deja ecrit au backlog), puis
  S6-4 et S6-5 (homophones CM1).
- `lea-dev` ouvre son worktree, verifie sa lignee, prend S6-8 et S6-10 des que `ines-design` a propose
  ses valeurs, confirme le residuel de S6-9 (les trois gestes de gabarit deja mecanises par
  `ADR-0022`) pour que `nadia-prof` puisse rejouer, et se tient prete a ouvrir le lot S6-6/S6-7 avec
  `theo-archi` si la capacite du sprint l'atteint.
- `sofia-sm` ouvre son worktree sous sa casquette dev, verifie sa lignee, livre S6-13 puis S6-14
  (scripts testes selon la doctrine d'`ADR-0009`, forme exacte donnee par `theo-archi` dans
  `avis-faisabilite.md`).
- `ines-design` propose les valeurs de S6-8 et S6-10 sans les trancher.
- `theo-archi` reste disponible pour le garde-fou de comptage items/manches de S6-3, et ouvre
  l'`ADR` du lot S6-6/S6-7 avec `lea-dev` des que le chantier 2 est engage.
- `yanis-qa` traduit chaque critere en test au fil des commits.

**Des que le chantier 1 et le residuel de S6-9 sont fusionnes sur `round-two`** : `nadia-prof` audite
la conjugaison CM1 (`FICHE-bandit-manchot-conjugaison-present-cm1.md`), rejoue les homophones CM1 pour
`couvert-valide`, et rejoue les cinq etats de l'ecran de jeu pour le verdict de S6-9.

**Si le chantier 2 est engage** : `nadia-prof` audite "Le bon son" comme un seul lot, jamais S6-6
seule, une fois S6-6 et S6-7 fusionnees ensemble.

**Une fois l'arbre fusionne** : `yanis-qa` fait tourner `mvn verify` sur JDK 21 et ecrit le rapport
qualite ; `theo-archi` confirme ArchUnit ; `relecteur-pedagogique` releve les livrables non
executables ; `sofia-sm` ecrit `review.md` puis `RAPPORT.md` et lance le rapport de tokens.
