# S21 - fusion finale sur round-two (nadia-2, lea-3, camille-2) - marc-3 clos sans commit

Trois branches fusionnees sur `round-two`, une branche fermee sans fusion. Ceremonie de fusion,
pas une review : le sprint n'est clos qu'a la review, qui verse ce journal.

## Verification de provenance

`round-two` etait a `8e21613` a l'ouverture de cette fusion (dernier commit avant ce lot, deja
verifie vert dans `fusion-correctif-porte-rouge-s21-theo-2.md`). Pour chacune des quatre branches
annoncees :

```
git merge-base round-two <branche>
```

rend `8e21613` pour `s21-nadia-2`, `s21-lea-3`, `s21-camille-2` et `s21-marc-3` : les quatre
partent bien de la tete exacte de `round-two`, aucune sur un point en retard. Aucune n'a besoin
d'un rebase.

## Fusion, dans l'ordre du tableau annonce

1. `git merge --no-ff s21-nadia-2` (`c0b8a66`) : treize fichiers ajoutes (trois fiches de
   conformite, un avis de Done pedagogique, cinq releves JSON, trois scripts d'outillage
   `nadia-prof`), zero conflit.
2. `git merge --no-ff s21-lea-3` (`7dec8c7`) : un seul fichier touche,
   `adapters/adapter-content/src/main/resources/contenu/cp-mot-manquant.yaml`, zero conflit.
3. `git merge --no-ff s21-camille-2` (`90273a6`) : cinq fichiers touches (`docs/backlog/backlog.md`,
   `DEC-0052` et son amendement, `DEC-index.md`, `MATRICE-couverture.md`), zero conflit.

Verification prealable a la fusion : aucun fichier n'est touche par plus d'une des trois branches
(`comm -12` sur les trois listes `git diff --name-only round-two <branche>`, deux a deux, vide dans
les trois cas). Les collisions annoncees sont donc semantiques - deux branches ecrivant sur des
familles de documents voisines (decision produit, matrice) sans jamais toucher le meme fichier -
pas des conflits Git, et aucune n'en a produit.

`s21-marc-3` : aucun commit au-dela de `8e21613` (`git log --oneline round-two..s21-marc-3` vide).
Rien a fusionner. Worktree `recreo-s21-marc-3` et branche supprimes.

## Ce que porte chaque lot

**`s21-nadia-2` - Done pedagogique complet du sprint.** L'utilisatrice tranche le 2026-08-28 :
"pour les syllabes cheminee vaut bien 3 syllabes". Le motif unique du refus de `cp-syllabes`
tombe ; `nadia-prof` reprend son verdict en `VALIDE SOUS RESERVE` (`4326f3b`) et amende la clause
(c) de `SEG-1`, ne la supprime pas : mesure sur les **650 mots francais distincts du corpus CP du
depot** (10 paquets CP servis et les fichiers CP de `contenu-repris/`, 29 fichiers, population
entiere, 2026-08-28), la clause d'origine attrapait 39 cas de schwa, 30 a tort et 9 a bon droit -
elle ecartait `petit`, `cheval`, `renard`, `semaine`, `regarde`, `premier`. Sur `cp-mot-manquant`,
deux tours de reaudit : `c9190ea` maintient le refus sur l'item 3 seul (deux motifs sur trois
fermes par le premier correctif de `lea-dev`), `2d2cea9` leve le refus apres le second correctif
et prononce `VALIDE SOUS RESERVE`. `cp-comparer` reste `VALIDE SOUS RESERVE` depuis l'audit
initial (`18d1220`).

**`s21-lea-3` - deux correctifs de `cp-mot-manquant`.** `fabd6cb` ferme les motifs 1 et 2 sur les
items 6 et 4 (deux items sur trois du refus initial) ; `4543d8d` ferme le troisieme, l'enonce de
l'item 3 seul ("Avant de dessiner, Lea taille son ___."), apres que `nadia-prof` a maintenu son
refus dessus dans `c9190ea`. Aucun autre champ touche.

**`s21-camille-2` - `DEC-0052` et son amendement, matrice a 18/43, backlog.** `ee031cb` ouvre la
decision le jour de l'arbitrage utilisatrice (matrice a 17/43) ; `ef12bd1` l'amende (ne la
reecrit pas) une fois le refus de `cp-mot-manquant` leve : le retrait instruit en section 3 n'a
plus d'objet, matrice a 18/43. Section 8 ajoutee sur les deux cas de retrait mesures
(`cm1-reperes` s20, `cp-mot-manquant` s21) : deux cas ne suffisent pas a fixer une borne de grace
sans reproduire le defaut que `nadia-prof` vient de nommer contre elle-meme - `DEC-0045` section 3
non amendee, question versee a la retro (voir plus bas).

## Le geste de retrait catalogue, verifie sur le fichier reel

`catalogue.yaml` (`adapters/adapter-content/src/main/resources/catalogue.yaml`) est identique
avant et apres cette fusion : `git diff 8e21613 HEAD -- adapters/adapter-content/src/main/resources/catalogue.yaml`
ne rend rien. Il portait deja 21 entrees a `8e21613` et en porte toujours 21 (comptage direct sur
le fichier reel, `grep -c "^  - id:"`, 2026-08-28) :

```
bandit-manchot-additions-cp, bandit-manchot-homophones-cm1, bandit-manchot-conjugaison-present-cm1,
bandit-manchot-synonymes-cm1, bandit-manchot-antonymes-cm1, bandit-manchot-accord-sujet-verbe-cm1,
bandit-manchot-le-bon-son-cp, bandit-manchot-soustractions-cp, bandit-manchot-problemes-additifs-cp,
bandit-manchot-comparer-nombres-cp, bandit-manchot-complements-10-cp,
bandit-manchot-tables-multiplication-cm1, bandit-manchot-perimetre-cm1,
bandit-manchot-conversions-unites-cm1, bandit-manchot-grands-nombres-cm1,
bandit-manchot-accords-groupe-nominal-cm1, bandit-manchot-nature-mot-cp, bandit-manchot-reperes-cm1,
bandit-manchot-syllabes-cp, bandit-manchot-mot-manquant-cp, bandit-manchot-comparer-cp
```

Le geste de retrait de `DEC-0052` section 3 n'a jamais ete execute (retenu par moi-meme le temps
de la correction) : il n'y a donc rien a restaurer et rien a retirer. `nadia-prof` et `camille-po`
notent toutes deux, chacune dans son propre commit, que c'etait le bon appel.

## Chiffre verifie contre sa source : la matrice a 18/43

Recompte independant, pas recopie de `MATRICE-couverture.md` : `grep -n "| couvert-valide"` sur
les quatre tableaux de matiere/niveau (les occurrences hors tableau, dans la prose et dans les
tableaux de synthese par matiere/niveau qui ne portent que l'en-tete de colonne, sont exclues a la
lecture) rend 18 lignes de competence a l'etat `couvert-valide` : lignes 84, 85, 86, 87, 89 (maths
CP), 223, 224, 230, 231 (maths CM1), 485, 495, 496 (francais CP), 610 a 615 (francais CM1). Le
tableau de synthese final du fichier (21 non couvert + 4 en cours + 18 couvert-valide + 4 non
couvrable en l'etat = 47) confirme le meme total et le denominateur atteignable de 43 (47 - 4).
**18/43 = 41,9 %, verifie ligne par ligne le 2026-08-28, concordant avec le document.**

## Porte technique finale

```
bash outils/mvn-jdk21.sh -B clean verify
```

sur `round-two` fusionne (`90273a6`), racine du reacteur complet, mesure le 2026-08-28 :

| Module | Tests | Failures | Errors | Skipped |
|---|---|---|---|---|
| recreo-domain | 49 | 0 | 0 | 0 |
| recreo-application | 13 | 0 | 0 | 0 |
| recreo-adapter-web | 332 | 0 | 0 | 0 |
| recreo-adapter-content | 224 | 0 | 0 | 0 |
| recreo-bootstrap (surefire) | 98 | 0 | 0 | 0 |
| recreo-bootstrap (failsafe) | 6 | 0 | 0 | 0 |
| **Total** | **722** | **0** | **0** | **0** |

`BUILD SUCCESS`, reacteur complet (six modules, `[1/6]` a `[6/6]`). Total exact a la base annoncee
(722) : les deux lots de `lea-dev` sont a delta zero (correctifs de contenu, pas de test neuf, pas
de test casse), le lot de `nadia-prof` et celui de `camille-po` sont documentaires (aucun fichier
Java, aucun fichier de test dans leurs diffs).

## Etat de `round-two`

`round-two` local est a `90273a6`, non pousse. Worktree `recreo-s21-marc-3` et branche
`s21-marc-3` supprimes (branche vide, aucun commit au-dela de la base). Les autres worktrees et
branches de s21 (lots deja fusionnes lors des vagues precedentes) sont laisses en l'etat : aucune
instruction ne demandait leur nettoyage dans ce geste, et je n'en fais pas un geste non demande.

## Trois faits verses a l'instruction de la retro, non tranches ici

1. **`nadia-prof` contre elle-meme** : sur les quatre refus du sprint, elle en compte trois avec
   leur cause racine dans sa propre specification anterieure - `SEG-1`, le triplet
   `crayon`/`feutre`/`pinceau` qu'elle avait qualifie d'"evident", l'absence de condition de
   lisibilite sur `cp-mot-manquant`. Le defaut qu'elle nomme : nommer la propriete a tenir, jamais
   l'exemple qui l'illustre. C'est un fait de capacite autant que de qualite - mon chiffrage du
   goulot pedagogique a deux ou trois paquets ne distinguait pas cette famille de cycle (causee par
   la specification amont) de celle causee par le contenu livre en aval.
2. **`camille-po` refuse d'amender `DEC-0045` section 3** sur les deux cas de retrait mesures
   (`cm1-reperes` s20, `cp-mot-manquant` s21), au motif qu'ajuster une regle sur l'unique cas ou
   elle a bien fini reproduirait le defaut que `nadia-prof` vient de nommer contre elle-meme. Verse
   a la retro avec ses deux couts mesures (section 8 de l'amendement `ef12bd1` a `DEC-0052`).
3. **Mon propre `taskkill` par nom d'image**, deja consigne dans la memoire de session, et la lecon
   de methode de `theo-archi` : mesurer sur l'union des arbres du sprint quand un lecteur partage
   (port de deverminage fixe, feuille de style lue par sous-chaine) durcit.

## Ce que je ne fais pas ici

Je n'ecris pas de `review.md` dans ce geste : aucun rapport qualite de `yanis-qa`, aucune
confirmation ArchUnit dediee de `theo-archi` au-dela de la porte technique ci-dessus, et aucune
relecture du `relecteur-pedagogique` n'ont ete versees pour ce lot final - la review reste une
ceremonie a part, pas une consequence automatique de cette fusion. Je ne prononce aucun Done
pedagogique : les trois verdicts `VALIDE SOUS RESERVE` sont ceux de `nadia-prof`, cites, pas
rendus par moi.
