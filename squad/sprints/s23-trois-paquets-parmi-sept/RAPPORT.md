# [Sprint s23] Rapport de cloture - trois paquets parmi sept

## L'increment livre, en une phrase

Trois paquets neufs entrent au catalogue apres un cycle complet de refus puis de correction
pedagogique - `cp-ranger-nombres` (comparer et ranger jusqu'a 100, **VALIDE**),
`cm1-problemes-etapes` (problemes a plusieurs etapes, **VALIDE**) et `cm1-mots-de-liaison`
(mots de liaison entre phrases, **VALIDE**, couverture partielle nommee) - la couverture programme
passe de 21/43 (48,8 %) a 24/43 (55,8 %) et le catalogue de 24 a 27 jeux.

## Les decisions du sprint

| Numero | Titre | Statut |
|---|---|---|
| `DEC-0057` | Perimetre d'ouverture de s23 : reserve de contenu seul a zero, huit arbitrages prealables poses en priorite absolue, capacite bornee a trois paquets | Acceptee (creee s23, 2026-08-30) ; amendee le meme jour (note factuelle : 21 signes orphelins mesures corrige en 18, aucun contenu de fond change) |
| `DEC-0058` | L'outillage des trois etapes calculables de la procedure de specification de `nadia-prof` (enumeration de population, satisfaisabilite, taux de reussite d'un raccourci) est verse au backlog, hors perimetre de s23 | Acceptee (creee s23) |
| `ADR-0065` | L'accentuation du texte lu par l'enfant est jugee sur un lexique morphologique exterieur au depot ; la classe des homographes reste hors garde-fou, a la main de `nadia-prof` | Accepte (2026-08-30) |
| `ADR-0066` | Toute reference de decision doit resoudre ; une charte citee dans la section Impact d'une decision doit citer cette decision en retour | Accepte (2026-08-30) - repond a `sofia-sm` en refusant la moitie de sa demande de s22 (juger qu'une charte *contredit* une decision resterait un jugement en langue naturelle) |
| `ADR-0067` | Le moyen de `TYP-1` (choix du caractere) reste ouvert, confie a `theo-archi` et `sofia-sm` ; sa dette est arretee par un cliquet exact (540 occurrences, 17 fichiers) avant toute correction | Accepte pour le cliquet, le moyen n'est pas tranche |
| `ADR-0068` | Une lecture qui laisse un chiffre dans son reste ne rend pas de valeur ; la proposition qui ecrit plusieurs nombres sort du domaine de la lecture | Accepte (2026-08-30) - corrige le garde-fou d'egalite de valeurs, obstacle 3 |

## Etat des deux Done

- **Technique : vert.** `bash outils/mvn-jdk21.sh -B clean verify` sur le reacteur complet
  (racine, aucun `-pl`), sur `HEAD` apres les trois dernieres fusions de cloture : **BUILD
  SUCCESS, 802 tests** (methode XML : somme de l'attribut `tests` des 123 fichiers `TEST-*.xml`
  de `target/surefire-reports/` et `target/failsafe-reports/`, sur les six modules du reacteur,
  mesuree par moi a la cloture ; concorde avec la synthese `Results:` par module). **30 regles
  ArchUnit, inchange** : aucun des trois derniers lots ne touche `domain`, `application`, ni
  l'outillage de recensement des regles.

- **Pedagogique : trois Done prononces, aucun refus ouvert a la cloture.** `cp-ranger-nombres`
  (`RAN-7` levee sur reaudit) **VALIDE sans reserve** ; `cm1-problemes-etapes` (reserves A a E
  toutes fermees et confirmees) **VALIDE sans reserve** ; `cm1-mots-de-liaison` (`LIA-3` levee sur
  l'item 10, second refus) **VALIDE**, avec la mention de couverture partielle exacte de
  `nadia-prof` portee sur sa ligne de matrice ("choix du mot de liaison entre deux ou trois
  phrases, sans production de texte"). `nadia-prof` reste seule garde de `LIA-3` integralement et
  de la coherence indice/enonce sur 21 des 24 items d'`ETA` : aucun automate ne les voit.

## Le mouvement de couverture et de catalogue

**21/43 (48,8 %) devient 24/43 (55,8 %)**, verifie ligne par ligne sur la matrice fusionnee
(47 lignes de competence au total, 4 `non couvrable en l'etat` retirees du denominateur atteignable) :
18 `non couvert`, 1 `en cours`, 24 `couvert-valide`. Trois lignes passent `couvert-valide` -
"Comparer et ranger des nombres jusqu'a 100", "Resoudre des problemes a plusieurs etapes mobilisant
les 4 operations" (mathematiques) et "Utiliser a bon escient des mots de liaison simples pour
structurer un texte court" (francais, avec sa mention de couverture partielle). Le catalogue passe
de **24 a 27 entrees**, verifie par comptage direct (`grep -c "^  - id:" catalogue.yaml`).

## Obstacles resolus et obstacles encore ouverts

Tableau complet (14 lignes numerotees) dans `review.md`. **Dix obstacles sur quatorze sont clos** :
deux specifications de contenu mathematiquement viciees avant tout item ecrit (`ETA-4`/`ETA-7`,
`RAN-5`), un trou de condition sur 43 050 triplets (`RAN-4`), un garde-fou d'egalite de valeurs qui
rendait un verdict faux (`ADR-0068`), un angle mort d'espace insecable dans un test de rendu, un
conflit de fusion a trois fichiers, une story d'outillage versee au backlog (`DEC-0058`, obstacle
8), et les deux paquets reaudites (obstacle 9, `cp-ranger-nombres` et `cm1-mots-de-liaison`
`VALIDE` tous les deux ; obstacles 13 et 14, reserves D et E de `cm1-problemes-etapes` confirmees).
L'obstacle 9 restait marque `OUVERT` sur le disque bien que les deux reaudits aient eu lieu ; ferme
par moi a la cloture, dans `review.md`, section "Cloture, sofia-sm". **Quatre obstacles du tableau
restent ouverts, visiblement** :

- **Obstacle 12** : la regle de mesure de test qui sous-compte de 7 sur les rapports texte est
  reelle mais seulement sous une methode de sommation particuliere, ni celle de la synthese par
  module ni celle de la sommation XML utilisees dans ce document - reformulation de sa portee
  exacte attendue de `theo-archi`.
- **Obstacles 7 et 10** : discipline de worktree, cinq occurrences autocorrigees sur ce seul
  sprint, aucun garde-fou ne la fait rougir - traite en retro, pas ferme par ce document.
- **Obstacle 11** : ecart de consigne de `sofia-sm` sur `RAN-7` - le test reste, defendable et
  verifie ; la question de fond (`LIA-3`, plausibilite de la famille (c) d'`ETA-4b`) reste routee
  a `yanis-qa` et `theo-archi`.

**Ce que le Done ne ferme pas, hors du tableau d'obstacles, et qui doit rester visible** :

- **`LIA-3` integralement et la coherence indice/enonce sur 21 des 24 items d'`ETA`**, hors de tout
  garde-fou : `nadia-prof` en reste seule garde a chaque reaudit futur.
- **`TYP-1` (le moyen)** : le choix du caractere d'espace insecable reste a trancher entre
  `theo-archi` et `sofia-sm` (`ADR-0067`).
- **L'obstacle 25 residuel** (s21) : cinq scripts de `nadia-prof` executes a l'import restent non
  testes par un juge pur, route a `theo-archi`.
- **`DEC-0058`** (outillage des etapes calculables de la procedure de `nadia-prof`) : candidate
  d'ouverture de s24, aucune capacite prise sur s23.

Aucun de ces obstacles n'est ferme par ce document au-dela de l'obstacle 9.

## Ce que la retro instruit

Le fil rouge des sprints precedents (un vert ou un chiffre qui s'affiche sans que ce qui compte
ait ete regarde) s'est deplace : les deux premiers obstacles de ce sprint sont des specifications
de contenu mathematiquement viciees avant toute ligne ecrite, pas des instruments de mesure. Quatre
defauts de mesure trouves ce sprint, trois par leur propre auteur, un quatrieme (l'instrument de
`nadia-prof` sur `RAN-7`) absent du compte de "quatre" deja ecrit plus tot dans `review.md` - les
deux paragraphes restent chacun exacts a leur date, aucun ne totalise le sprint. Une formule
("sur le JDK 21 de la toolchain declaree") ecrite huit fois par `nadia-prof` elle-meme sans avoir
jamais ete constatee, et trois fois de plus chez deux autres agents. Deux renoncements documentes
plutot que deux garde-fous fragiles livres. Un ecart de consigne assume par `sofia-sm`. Detail
complet, ce qui est tranche et ce qui est propose avec son destinataire : `retro.md`.

## Consommation de tokens

Genere par `python outils/rapport-tokens.py --sprint s23` ; le detail complet vit dans
`RAPPORT-tokens.md` et dans `squad/rapports/JOURNAL-tokens.md`. Fenetre : 2026-08-30 12:15 a
2026-08-30 18:14 UTC (horodatages de `planning.md` et de la ligne "Horodatage de fin" ajoutee par
moi a la cloture dans `review.md`, section "Cloture, sofia-sm").

**Correction faite avant de generer le rapport** : la seule ligne "Horodatage de fin" du document
(2026-08-30 16:48) datait du premier commit de `review.md` et n'avait jamais ete mise a jour, alors
que le second tour, le troisieme tour et les trois dernieres fusions ont tous ete ecrits apres
elle. Une premiere execution du script sur cette ligne perimee rendait "aucune invocation mesuree"
pour `camille-po` et `relecteur-pedagogique` - faux : les deux ont travaille ce sprint, hors de la
fenetre tronquee. Ajoute une ligne "Horodatage de fin" datee de la cloture reelle (le script retient
la derniere lue, pas la premiere) plutot que d'editer la ligne existante ; les deux agents
apparaissent desormais dans le tableau.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---:|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 310 | 620 | 116 956 | 1 603 795 | 58 918 766 |
| `sofia-sm` | claude-sonnet-5 | 885 | 1 770 | 306 665 | 2 825 049 | 93 121 132 |
| `theo-archi` | claude-opus-5 | 110 | 220 | 70 079 | 337 395 | 12 186 852 |
| `marc-dev` | claude-sonnet-5 | 594 | 1 188 | 340 849 | 4 074 638 | 230 594 487 |
| `lea-dev` | claude-sonnet-5 | 170 | 340 | 92 381 | 568 058 | 28 133 309 |
| `yanis-qa` | claude-sonnet-5 | 188 | 376 | 70 634 | 397 458 | 22 450 386 |
| `relecteur-pedagogique` | claude-sonnet-5 | 113 | 226 | 37 631 | 460 165 | 12 474 803 |
| `nadia-prof` | claude-opus-5 | 435 | 870 | 327 431 | 1 223 203 | 42 667 507 |
| orchestrateur (fil principal) | claude-opus-5 | 80 | 160 | 222 201 | 268 320 | 40 121 289 |
| **Total** | | **2885** | **5 770** | **1 584 827** | **11 758 081** | **540 668 531** |

Aucune invocation mesuree sur cette fenetre pour `ines-design` : pas de travail de design ce
sprint, ce n'est pas une invocation a zero token.
