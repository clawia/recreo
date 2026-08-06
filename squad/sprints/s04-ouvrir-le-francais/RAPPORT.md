# [Sprint s04] Rapport de sprint - ouvrir le francais

- **Debut** : aucun horodatage de planning (voir anomalie de methode dans `review.md`)
- **Fin** : 2026-08-06 12:02 (commit `399fa5f`, meme instant que la cloture de s03)
- **Duree effective** : non mesurable au sens habituel. Repere de repli, a titre indicatif
  seulement : 2h55 entre le commit qui ouvre reellement ce sprint (`0e07fd3`, `DEC-0008`,
  09:07:22) et le dernier commit de la fenetre partagee (`399fa5f`, 12:02:07).

## Anomalie de methode - a lire avant le reste de ce rapport

**Ce sprint n'a eu ni planning, ni dossier avant cette cloture.** Il est parti d'une demande
directe de l'utilisatrice ("je ne comprends pas pourquoi on n'a pas de nouveaux jeux") plutot que
d'une ceremonie geant un objectif, affectant nominativement, ou recueillant un avis de faisabilite
ecrit de `theo-archi` avant le premier commit de code - `DEC-0008` anticipait explicitement cet
avis "au planning" ; il n'a jamais ete recueilli, et le code est arrive vingt-neuf minutes plus
tard. Le detail complet, y compris la consequence concrete que `camille-po` signale (ne pas avoir
pu relier une story ouverte a l'increment livre autrement qu'a posteriori), est dans `review.md`.

## Increment livre

Le francais entre au catalogue de `recreo` pour la premiere fois, et le CM1 sort de son etat a
zero contenu, tous jeux confondus. Deux jeux livres, deux verdicts opposes :
`bandit-manchot-homophones-cm1` (CM1, homophones grammaticaux) est accorde sous reserve ;
`bandit-manchot-le-bon-son-cp` (CP, graphemes/phonemes, "Le bon son") est refuse sur cinq motifs
et retire du catalogue jouable.

**Le fait qui vaut le plus dans cet increment n'est ni l'un ni l'autre de ces deux verdicts :
c'est la preuve, verifiee independamment par `theo-archi` et par `yanis-qa`, que le decouplage
mecanique/contenu annonce depuis `CLAUDE.md` et `ADR-0004` tient sur une matiere reellement
distante des mathematiques.** `git diff --stat` sur `domain/` entre la fin du lot `marc-dev` de
s03 (`9af191c`) et la fusion de ce lot (`4c62f60`) est **vide** : le module `domain` entier n'a
pas bouge d'une ligne. Deux jeux de francais sont entres par trois fichiers YAML et un test.
`theo-archi` refuse d'en ecrire une regle ArchUnit - un seul lot est une observation, pas un usage
constate, motif ecrit dans `confirmation-archunit.md` section 5 - mais le fait est ecrit ici parce
qu'il vaut plus que le compte de deux stories : c'est la premiere fois en quatre sprints que le
principe directeur du produit est reellement mis a l'epreuve, et il tient.

## Decisions du sprint

| Reference | Titre | Type | Statut |
|---|---|---|---|
| DEC-0008 | La couverture du programme reprend sur demande de l'utilisatrice : francais CP et CM1 entrent au backlog | Produit | Acceptee |
| DEC-0009 | Retrait de "Le bon son" du catalogue jusqu'a sa correction | Produit | Acceptee |

## Etat des deux Done

| Done | Juge par | Etat | Reference |
|---|---|---|---|
| Technique | CI (`mvn verify`) | **Vert.** `yanis-qa` : deux executions independantes sur JDK 21.0.10, BUILD SUCCESS, 180 tests, 0 echec, dont 4 neufs pour s04. Reverifie a `HEAD` (`399fa5f`, apres retrait du catalogue) par moi-meme : 183 tests, 0 echec, chiffre inchange. | `review.md`, section "Rapport qualite" |
| Pedagogique | nadia-prof | **Deux verdicts distincts, sur deux increments distincts.** `bandit-manchot-homophones-cm1` : **VALIDE SOUS RESERVE**. `bandit-manchot-le-bon-son-cp` : **REFUSE**, retire du catalogue jouable (`DEC-0009`). | `squad/conformite/FICHE-bandit-manchot-homophones-cm1.md` ; `FICHE-bandit-manchot-le-bon-son-cp.md` |

## Obstacles

**Resolus** :

| Description | Agent route | Decision | Clos le |
|---|---|---|---|
| Aucun dossier de sprint n'existait pour s04, rapport de `yanis-qa` egare dans le dossier de s03 | sofia-sm | Dossier `squad/sprints/s04-ouvrir-le-francais/` cree, fichier deplace et renomme a cette cloture | cette cloture |
| S4-2 refuse ("Le bon son") reste accessible en jeu apres son refus | camille-po | `DEC-0009` : retrait effectif du catalogue, verifie en jouant l'application relancee par `nadia-prof` | `fb7f4df` |
| Referentiel francais CP declarait une competence de syllabes couverte par un jeu qui n'existe pas sur `round-two` | camille-po | Annotation retiree ; les trois autres referentiels relus sans trouver d'autre occurrence | `MATRICE-couverture.md`, 2026-08-06 |

**Encore ouverts** :

| Description | Agent route | Depuis | Ce qui manque |
|---|---|---|---|
| Aucun avis de faisabilite de `theo-archi` n'a ete recueilli avant le code de S4-1/S4-2, contrairement a ce que `DEC-0008` anticipait | sofia-sm | 2026-08-06 | un amendement de process pour que ce garde-fou prealable tourne meme hors planning formel (propose en `retro.md`) |
| `DEC-0009` a ete redigee neuf minutes avant l'edition effective du catalogue qui l'applique | camille-po, sofia-sm | 2026-08-06 | un principe explicite sur le delai maximal entre une decision d'exclusion et son execution (propose en `retro.md`) |
| S5-1 second critere : la reprise de session ne revalide pas l'identifiant contre le catalogue | camille-po | 2026-08-06 | volontairement laisse ouvert par `nadia-prof` jusqu'a l'existence d'`adapter-persistence` |
| S5-5 : un paquet `type: question` ne peut porter d'indice, motif structurel du refus | theo-archi | 2026-08-06 | une decision de modelisation ; deja route dans `docs/backlog/backlog.md` |
| Cinq couples d'homophones manquants pour que la ligne CM1 passe a `couvert-valide` (S5-6), et absence de variete d'une partie a l'autre sur les deux jeux `type: question` (S5-7, reserve 1 de `bandit-manchot-homophones-cm1`) | camille-po | 2026-08-06 | stories deja ecrites au backlog, non affectees |

## Couverture programme

| Ligne de matrice | Avant | Apres |
|---|---|---|
| francais / CP / Lecture et comprehension de l'ecrit / Associer les graphemes aux phonemes | `non couvert` | **`en cours`** - jeu refuse, retire du catalogue jouable, condition de correction ecrite (motifs 1 a 5, stories S5-2 a S5-5) |
| francais / CM1 / Orthographe et grammaire / Distinguer et utiliser correctement les principaux homophones grammaticaux | `non couvert` | **`en cours`** - Done pedagogique accorde sous reserve, condition ecrite pour `couvert-valide` (cinq couples supplementaires, story S5-6) |

## Consommation de tokens

**Aucun `RAPPORT-tokens.md` n'existe pour ce sprint, et ce n'est pas un chiffre a zero : c'est une
mesure impossible a produire, pour la meme raison honnete et verifiee qu'en s02.**

`python outils/rapport-tokens.py --sprint s04` a ete lance depuis la racine du depot, apres avoir
cree le dossier de ce sprint. Resultat :

```
ignore s04-ouvrir-le-francais : pas d'horodatage de debut dans planning.md
Sprint inconnu : s04
```

(code de sortie 1). Le script construit la fenetre temporelle d'un sprint exclusivement a partir
de l'horodatage de debut lu dans `planning.md` (`squad/README.md`, section 6) ; ce sprint n'en a
pas, pour la raison exposee dans l'anomalie de methode de `review.md`. Je n'ai pas fabrique de
`planning.md` pour satisfaire l'outil - un fichier de ceremonie qui n'a pas eu lieu est un faux.

Consequence directe, deja nommee dans `squad/sprints/s03-completer-mathematiques-cp/RAPPORT.md` :
la consommation reelle de s04 n'est pas mesurable a part. Elle est mecaniquement absorbee dans la
fenetre de s03 (2026-08-05 19:05 a 2026-08-06 12:02), qui contient la totalite de la fenetre de
travail de s04 sans pouvoir la distinguer par sujet. Je ne relance pas de regeneration complete
pour tenter de la separer : ce serait la meme erreur que j'avais refuse de commettre en s02,
corrompre une mesure deja ecrite pour combler celle qui manque.

`squad/rapports/JOURNAL-tokens.md` n'est donc pas mis a jour par ce sprint specifiquement ; il l'a
deja ete par la cloture de s03, dans la meme session de travail.
