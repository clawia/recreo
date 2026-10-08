# Audit de nadia-prof - s55, instance B : EMC CM1, lignes 3 et 6

`nadia-prof`, instance B, le 2026-10-08. Worktree `s55-nadia-audit-b-emc`, base `14455b56` (`round-two`,
branchements de `lea-dev` `0839f9dc` et `4636aa28`). **Audit joue**, pas degrade. Le detail est dans les deux
fiches ; ce fichier n'en recopie que les conclusions.

## Verdicts

| Code | Story | Jeu | Verdict | Reserve |
|---|---|---|---|---|
| V1 | `S55-BR-EMC-CM1-03` | `bandit-manchot-respect-differences-cm1` | **VALIDE SOUS RESERVE** | `EMC-CM1-R8` : indices des items 10 et 11 |
| V2 | `S55-BR-EMC-CM1-06` | `bandit-manchot-principes-republique-cm1` | **VALIDE SOUS RESERVE** | `EMC-CM1-R9` : indice de l'item 3, citation de 2018 dans l'en-tete |

Fiches : `squad/conformite/FICHE-bandit-manchot-respect-differences-cm1.md`,
`squad/conformite/FICHE-bandit-manchot-principes-republique-cm1.md`. Les textes de remplacement exacts sont
dans la section 5 de chaque fiche. Les deux Done sont ouverts, avec la mention exigee d'avance pour l'EMC CM1,
a reproduire mot pour mot par `camille-po`.

## Ce que j'ai fait

`bash outils/mvn-jdk21.sh -B clean verify`, reacteur complet : `BUILD SUCCESS` sur JDK 21.0.10 (job `build`
seul). Jar de ce build, contenu embarque identique aux fichiers commites. Neuf parties de dix manches par
jeu ; sur chaque jeu, les onze items tires, les 22 distracteurs presses, les onze indices lus a l'ecran,
chaque item reussi du premier coup au moins une fois. Serveur sur un port propre a l'instance B, arrete par
son PID.

## Les six points remontes

| Code | Point | Decision |
|---|---|---|
| P1 | `E1`, item 4 de `-03` : *"cette partie"* au lieu de *"la partie"* | **Accepte.** Le defaut etait dans mon texte. La barre (handicap seul, sans incidence ; longueur qui ne designe pas la reponse) est tenue : 46, 49, 41 caracteres, la bonne reponse n'est ni la plus longue ni la plus courte. 0,25 sur la marge. Fiche `-03`, section 3 |
| P2 | `E4`, indice de l'item 3 de `-06` | **Corrige, pour l'exactitude et non pour la fuite.** La fuite vers l'item 8 (et celle de l'item 2 vers l'item 7) est la lecon reprise apres une erreur, motif deja applique a `atteinte-autrui`. L'indice decrit mal la situation (les eleves aident d'autres enfants, ils ne se soutiennent pas entre eux) : `EMC-CM1-R9`, nouveau texte *"Ces élèves donnent pour aider d'autres enfants."* |
| P3 | `E5`, indices des items 10, 11 (et 6) de `-03` | **Items 10 et 11 corriges** (`EMC-CM1-R8`) : chacun designe la seule proposition qui contient son mot (*"jeu"*, *"déjà"*). Nouveaux textes : *"Un motif valable ne parle pas de l'élève lui-même."* et *"Un motif valable pourrait concerner n'importe quel élève."* **Item 6 garde** : il ecarte un distracteur, mais les deux restants ne se departagent qu'en connaissant la notion (critere de s53, section 4.3) |
| P4 | Titres *"Ce que le respect interdit"* et *"Les principes de la République"* | **Acceptes tous les deux** : sans niveau, ni groupe ni religion, distincts des autres titres d'EMC CM1 a l'ecran |
| P5 | Sources `HIS-3` de 1948 et 2018 | **1948 juste** (article 1er, egale dignite). **2018 juste sur le fond, citation incomplete** : l'en-tete omet *"dans un but humanitaire"* et *"sur le territoire national"*, omission venue de mon arbitrage ; texte exact dans `EMC-CM1-R9`, point (b). Verifie contre le texte que je connais, sans source ouverte a cette session |
| P6 | Q-Y14, `alt` d'une image de proposition | Ci-dessous |

**Q-Y14, en une ligne** : l'`alt` est un texte que l'enfant entend des qu'une aide technique lit l'ecran,
donc a ma porte, et je tranche `alt=""` : le nom accessible du bouton (`M5`, *"Choisir cette œuvre"*) est le
seul texte de la proposition, parce que tout texte alternatif qui decrit une oeuvre la designe (`CON-5`).

## Corrections de mes propres constats

- Mon arbitrage prealable (section 2) annoncait une lecture item par item de `-03` et n'a pas vu les indices
  des items 10 et 11 contre la regle que j'etendais moi-meme a ce paquet (section 1.2).
- A s53 (P4, section 4.3), j'ai garde l'indice de l'item 3 de `-06` comme une glose de la situation sans le
  lire contre son enonce. La fuite vers l'item 8 vient de mon texte de s55 (section 3.2).
- La citation de 2018 de mon arbitrage (section 3.2) omettait deux elements de la decision.

Aucune barre ecrite a s53 ou a s55 n'est relevee : les deux reserves appliquent des regles posees avant le
branchement, ou l'exactitude.

## Capacite de l'instance B

| Poste | Prix |
|---|---|
| `EMC-CM1-03`, premier passage | 1,0 |
| Ecart de l'item 4, juge ici (P1) | 0,25, sur la marge |
| `EMC-CM1-06`, premier passage | 1,5 |

Rien d'autre n'est consomme dans ce passage. **Aucun rejeu supplementaire n'est necessaire a s55** : les deux
Done sont ouverts. La levee de chaque reserve est un rejeu a 0,25 (`PAR-10`, mot pour mot) dans un sprint
suivant, a planifier par `camille-po` ; 0,5 si un autre texte est propose.

## Ce dont je reste la seule garde

Aucun automate ne verifie, sur ces deux jeux : `EMC-5` (une seule reponse defendable), `BIA-5` sur le sens
d'un distracteur, `BIA-1`/`BIA-2` sur la repartition des prenoms, l'exactitude des sources `HIS-3`, l'exactitude
d'un indice contre son enonce, ni un indice qui contient un mot propre a la bonne reponse (regle de s53 4.1).
C'est en jouant que les deux reserves ont ete trouvees.

## Routage

| A qui | Quoi |
|---|---|
| `camille-po` | Deux lignes validees sous reserve, avec la mention d'EMC CM1 ; deux stories de levee, `EMC-CM1-R8` et `EMC-CM1-R9`, textes exacts dans les fiches |
| `sofia-sm` | Conclusion a reprendre dans `review.md` de s55 ; instance B sans rejeu supplementaire |

## Ce que ce document ne fait pas

Il ne touche ni la matrice, ni le backlog, ni un YAML, ni `catalogue.yaml`.
