# [Sprint s21] Rapport de cloture - fermer le pli avant le volume

## L'increment livre, en une phrase

Le pli se ferme sur la part atteignable du catalogue existant (26 des 56 items de l'echantillon
initial fermes par la forme, sur une population reelle de 478 items ou 114 etaient sous le pli
avant s21 et 64 le restent), trois paquets neufs de volume CP entrent au catalogue apres arbitrage
pedagogique prealable (`cp-syllabes`, `cp-mot-manquant`, `cp-comparer`, tous trois `VALIDE SOUS
RESERVE`), et un garde-fou CSS qui violait une regle d'architecture a travers trois CI vertes est
corrige avec son propre correctif casse - la couverture programme passe de 15/43 (34,9 %) a 18/43
(41,9 %) et le catalogue de 18 a 21 jeux.

## Les decisions du sprint

| Numero | Titre | Statut |
|---|---|---|
| `DEC-0051` | Arbitrage de l'utilisatrice pour s21 : le pli se ferme sur tout le catalogue avant tout ajout de jeu, la matiere des jeux neufs vient de `contenu-repris/` avant redaction neuve | Acceptee (2026-08-27, ouverture) ; amendee deux fois (2026-08-27 : la population citee est un echantillon de 167 sur 428, pas le catalogue ; 2026-08-28, troisieme : population reelle du catalogue etablie a 478) |
| `DEC-0052` | Arbitrage de l'utilisatrice : "cheminee" vaut trois syllabes, `cp-syllabes` passe `VALIDE SOUS RESERVE` (`SEG-1` amendee par `nadia-prof`) ; `bandit-manchot-mot-manquant-cp` retiree du catalogue pour un motif de contenu qui reste, lui, entier | Acceptee (2026-08-28, cloture) ; amendee une fois le meme jour (le refus fondant le retrait de `mot-manquant-cp` est leve, le retrait n'a plus d'objet ; section 8 : refus d'amender `DEC-0045` section 3 ; section 11 : cout de capacite verse a la retro) |
| `ADR-0063` | Un item ne presente jamais plus de trois propositions, et les deux autres regles de forme restent des conseils | Accepte (2026-08-27, `S21-5`) ; amende le 2026-08-28 (chiffre de contexte requalifie en echantillon date ; cliquet reverifie exact sur 478 items ; defaut reel corrige : la borne d'echantillonnage du garde-fou, ecrite a la main a 60, est desormais derivee du paquet) |

Aucune decision de ce sprint n'a ete refusee ni retiree. `camille-po` a explicitement refuse
d'amender `DEC-0045` section 3 (regime de retrait du catalogue sur `REFUSE`) sur les deux cas
mesures ce cycle : ce n'est pas une decision prise, c'est une decision de ne pas en prendre une
sans matiere suffisante, versee a `retro.md`. Le detail de chacune vit dans `review.md` et dans les
fichiers cites.

## Etat des deux Done

- **Technique : vert.** `bash outils/mvn-jdk21.sh -B clean verify` sur l'arbre de cloture
  (`round-two` a `9f2dc5a`, le 2026-08-28) : **BUILD SUCCESS, 722 tests, 0 echec** (49 `domain` +
  13 `application` + 332 `adapter-web` + 224 `adapter-content` + 98 `bootstrap` surefire + 6
  `bootstrap` failsafe), mesure par moi et concordante avec `yanis-qa` (`8ecf44d`) et `theo-archi`
  (`c613ee2`). **30 regles ArchUnit, inchange par le sprint.**

  **Note sur la poussee.** `origin/round-two` reste 76 commits derriere `HEAD`. Ce Done technique
  est une mesure locale concordante sur trois executions independantes, pas un verdict de la CI sur
  cet arbre - `squad/README.md` section 7 designe la CI comme seul juge. Je ne pousse rien : la
  poussee reste la main de l'utilisatrice.

- **Pedagogique : trois verdicts, aucun refus ouvert a la cloture.** `bandit-manchot-syllabes-cp`,
  `bandit-manchot-mot-manquant-cp` et `bandit-manchot-comparer-cp` sont **VALIDE SOUS RESERVE**,
  chacun apres au moins un refus corrige dans le meme sprint. Couverture : 15/43 (34,9 %) en debut
  de sprint -> 18/43 (41,9 %) a la cloture, trois lignes gagnees et auditees dans le meme sprint.

## Le mouvement de couverture et de catalogue

**15/43 (34,9 %) devient 18/43 (41,9 %)** : "Denombrer les syllabes orales d'un mot" et "Utiliser
des mots precis pour designer des objets, actions, sensations du quotidien" (francais CP), "Comparer
des longueurs, des masses, des contenances sans instrument gradue" (mathematiques CP). Le catalogue
passe de **18 a 21 entrees**. Le pli passe de **114 a 64 items sous le pli sur une population de
478**, tous desormais concentres sur quatre jeux CM1 (`accord-sujet-verbe-cm1`, `reperes-cm1`,
`grands-nombres-cm1`, `accords-groupe-nominal-cm1`) : le pli est ferme sur les neuf jeux CP du
catalogue. Les tests passent de **694 a 722** (+28), sans regression et sans mutation non prouvee.

## Obstacles resolus et obstacles encore ouverts

Tableau complet (24 lignes, avec destinataire et etat) dans `review.md`. En resume : **18
obstacles sur 24 sont clos**, dont sept de la meme famille - un garde-fou vert qui lisait la
mauvaise declaration d'une propriete cascadee, son propre correctif casse a la fusion complete, un
script de campagne ignorant des codes de sortie, deux outils de mesure perdant silencieusement leur
condition d'injection, une population d'echantillon lue comme un inventaire dans cinq documents, et
le cliquet de `theo-archi` sur le nombre de propositions par item, borne a 60 alors qu'un paquet en
declare 99. **Six restent ouverts** :

- `ChaqueColonneQueLeContenuPeutProduirePorteSonDecalageTest` (module `bootstrap`) porte le meme
  angle mort de lecture qu'`ADR-0017`, non corrige par choix (vert aujourd'hui, aucun usage reel qui
  le ferait mordre).
- `AucunePropositionNEstEgaleAUneAutreDansUnItemTest` porte la meme borne d'echantillonnage fixe de
  60 que le cliquet corrige ce sprint - dette voisine nommee par `theo-archi`, non corrigee.
- 4 items du catalogue (`bandit-manchot-grands-nombres-cm1` x2, `bandit-manchot-reperes-cm1` x2)
  n'ont aucun levier connu contre le pli - route a `camille-po`, hors perimetre de s21.
- `REP-8` reste ouverte sur CM1 : 64 items sous le pli, quatre jeux concentres, volume de contenu a
  retravailler plutot que garde-fou manquant - route a `camille-po`.
- `outils/mvn-jdk21.sh` pose `JAVA_HOME` depuis un fichier de toolchains different de celui que
  Maven lit reellement, sans consequence constatee sur la CI ni sur le bytecode livre - route a
  `theo-archi`, hors perimetre de s21.
- Divergence non reconciliee entre deux releves de `nadia-prof` sur `cm1-reperes` (14/16 contre
  16/16 items au-dessus du pli, outils differents) - pour memoire, ne bouge aucun verdict.

## Ce que `nadia-prof` releve, et qui merite d'etre porte

Deux des trois refus pedagogiques de ce sprint, et trois des quatre depuis s20, ont leur cause
racine dans ses propres specifications ecrites avant que le contenu n'existe, pas dans le travail
qu'elles encadraient. Le defaut qu'elle nomme est constant : elle ecrit l'exemple qui illustre une
propriete plutot que la propriete elle-meme. Un arbitrage de l'utilisatrice sur un seul mot
("cheminee" vaut trois syllabes) a fait tomber une clause qui, mesuree ensuite sur la population
entiere du corpus CP, excluait a tort trente mots dont `petit`, `cheval`, `renard`, `semaine`,
`regarde` et `premier` - le coeur du lexique de CP. Ce que cela dit de la methode de specification
pedagogique, et pas seulement de sa rigueur, est instruit sans complaisance dans `retro.md`.

## Consommation de tokens

Genere par `python outils/rapport-tokens.py --sprint s21` ; le detail complet vit dans
`RAPPORT-tokens.md` et dans `squad/rapports/JOURNAL-tokens.md`. Fenetre : 2026-08-27 13:45 a
2026-08-28 10:30 UTC (horodatages de `planning.md` et de `review.md`).

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---:|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 359 | 718 | 189 917 | 1 835 576 | 62 955 290 |
| `sofia-sm` | claude-sonnet-5 | 815 | 1 630 | 247 530 | 2 095 685 | 83 525 725 |
| `theo-archi` | claude-opus-5 | 371 | 742 | 232 212 | 3 790 941 | 115 602 611 |
| `marc-dev` | claude-sonnet-5 | 305 | 610 | 99 796 | 717 291 | 51 065 617 |
| `lea-dev` | claude-sonnet-5 | 373 | 746 | 152 263 | 1 179 161 | 51 370 568 |
| `yanis-qa` | claude-sonnet-5 | 100 | 200 | 37 667 | 269 694 | 8 654 587 |
| `relecteur-pedagogique` | claude-sonnet-5 | 234 | 468 | 65 677 | 503 717 | 27 503 381 |
| `nadia-prof` | claude-opus-5 | 694 | 1 388 | 511 133 | 8 524 957 | 176 583 704 |
| `ines-design` | claude-sonnet-5 | 162 | 324 | 37 729 | 364 681 | 19 592 689 |
| orchestrateur (fil principal) | claude-opus-5 | 121 | 242 | 269 669 | 1 850 806 | 30 829 635 |
| **Total** | | **3 534** | **7 068** | **1 843 593** | **21 132 509** | **627 683 807** |

Tous les agents apparaissent sur cette fenetre : aucune invocation absente a signaler ce sprint.
