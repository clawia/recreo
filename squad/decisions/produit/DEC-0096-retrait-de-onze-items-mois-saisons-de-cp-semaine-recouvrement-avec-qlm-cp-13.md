# DEC-0096 - Retrait de onze items (mois, saisons) de `cp-semaine` : recouvrement avec `QLM-CP-13`
# corrige, `MAT-CP-09` n'en depend pas, paquet a completer avant reaudit

- **Type** : correction de contenu suite a un constat pedagogique, effet sur la matrice instruit
- **Date** : 2026-09-24
- **Auteur** : camille-po
- **Sprint** : hors sprint - apres la cloture de s41 (`d4ff0df9`)
- **Competence programme visee** : mathematiques, CP (cycle 2), "Se reperer dans la journee, la
  semaine ; lire l'heure pleine sur une horloge" (`squad/programme/mathematiques/competences-cp.md`,
  `MAT-CP-09`) pour le retrait lui-meme ; questionner le monde, CP, "Nommer et ordonner les mois de
  l'annee et les saisons" (`squad/programme/questionner-le-monde/competences-cp.md`, `QLM-CP-13`)
  pour le motif du retrait.
- **Statut** : Acceptee

## 0. L'enonce recu, cite mot pour mot, et ce qu'il ne fonde pas

Recu de MASTER, sans source dans le depot, le 2026-09-24 :

> *"_ifaut-il retirer ces quatre items de cp-semaine pour que chaque jeu ne compte que pour sa
> propre ligne du programme, partons sur cette solution"*

Forme 3, borne 1 (`squad/README.md`, section 2 bis) : cet enonce ne vaut motif pour rien de ce qui
suit. Il m'a servi de point de depart pour aller verifier, pas de fondement. Le chiffre qu'il porte
- quatre items - est faux, et il l'etait deja avant que je l'ecrive nulle part : `nadia-prof`
l'avait elle-meme avance a tort dans sa fiche du 2026-09-24 (`squad/conformite/FICHE-bandit-manchot-
saisons-cp.md`, section "Correction d'un de mes propres constats"), en comptant les items de
`cp-qlm-saisons` en recouvrement plutot que ceux de `cp-semaine`. Elle l'a corrige elle-meme le
meme jour dans un amendement ecrit (`d3216c84`, detaille en section 2).

Ce document ne route donc pas un retrait de quatre items : il decide le retrait de **onze**, sur
la base d'une lecture verifiee des vingt-trois enonces de `cp-semaine.yaml` et de leur
recoupement avec l'amendement ecrit de `nadia-prof`.

## 1. Ce que j'ai verifie

**`cp-semaine.yaml`**
(`adapters/adapter-content/src/main/resources/contenu/cp-semaine.yaml`) porte vingt-trois items.
J'ai relu chacun et classe onze d'entre eux comme portant sur les mois ou les saisons :

| Rang | Enonce | Bonne reponse |
|---|---|---|
| 5 | "Quel mois vient juste après mars ?" | avril |
| 6 | "Quel mois vient juste avant janvier ?" | décembre |
| 7 | "Quel mois vient juste après juillet ?" | août |
| 8 | "Quelle saison vient juste après le printemps ?" | l'été |
| 9 | "Quelle saison vient juste avant le printemps ?" | l'hiver |
| 15 | "Quel est le premier mois de l'année ?" | janvier |
| 16 | "Quel est le dernier mois de l'année ?" | décembre |
| 17 | "Quel mois vient entre avril et juin ?" | mai |
| 18 | "Quel mois vient entre janvier et mars ?" | février |
| 19 | "Pendant quelle saison les feuilles tombent ?" | l'automne |
| 20 | "Pendant quelle saison les fleurs poussent ?" | le printemps |

Ce compte - sept items sur les mois (rangs 5, 6, 7, 15, 16, 17, 18), quatre sur les saisons
(rangs 8, 9, 19, 20) - est exactement celui que `nadia-prof` a ecrit dans son amendement du
2026-09-24 apres relecture independante des memes vingt-trois enonces
(`squad/conformite/ARBITRAGE-PREALABLE-s25-les-seize-lignes-instruites-la-famille-visuelle-et-ma-
borne.md`, section 2.3, dans le commit `d3216c84`). Les deux lectures, faites separement, se
recoupent rang par rang.

**Le referentiel de questionner le monde**
(`squad/programme/questionner-le-monde/competences-cp.md`, section "Recouvrements a ne pas
double-compter") reserve nommement les mois et les saisons a `QLM-CP-13` et limite
mathematiques CP / Grandeurs et mesures a "la journee ni la semaine". `QLM-CP-13` est deja servie
par `cp-qlm-saisons`, couvert-valide depuis le 2026-09-24 (`squad/programme/MATRICE-
couverture.md`, ligne 1515). Les onze items ci-dessus sont donc hors du perimetre que le
referentiel attribue a `MAT-CP-09`, quelle qu'ait ete la condition `SEM-1` qui les avait autorises
a tort.

**`SEM-1`** (condition ecrite par `nadia-prof` le 2026-08-31 pour ouvrir `cp-semaine`) autorisait
mot pour mot "mois et leur ordre, saisons" - c'est cette clause qui a permis les onze items, contre
le referentiel qu'elle applique elle-meme par ailleurs. `nadia-prof` l'a amendee dans le commit
`d3216c84` : la redaction opposable a partir du 2026-09-24 limite `SEM-1` aux "moments de la
journee (matin, midi, apres-midi, soir, nuit), les jours de la semaine et leur ordre. Rien
d'autre." Cet amendement est deja fait ; il n'est pas route par ce document, il est constate.

## 2. Effet sur la couverture de `MAT-CP-09`

La mention deja portee par `squad/programme/MATRICE-couverture.md` (ligne 213) sur la ligne
"Se reperer dans la journee, la semaine ; lire l'heure pleine sur une horloge" est :

> *"couverture partielle... moitie 'se reperer dans la journee, la semaine' seule ; la moitie
> 'lire l'heure pleine sur une horloge' reste non couverte"*

Cette mention ne cite jamais les mois ni les saisons parmi ce que le jeu couvre : elle borne deja
la couverture a "journee, la semaine". **Le retrait des onze items ne change donc rien a cette
mention.** La competence centrale de `MAT-CP-09` - jours de la semaine et moments de la journee -
ne depend d'aucun des onze items retires : les douze items qui restent portent tous sur les jours
(huit) ou les moments de la journee (quatre), verifie sur la meme lecture des vingt-trois enonces.

**Ce que je ne decide pas ici** : la ligne de matrice reste `couvert-valide` a la date de ce
document, parce que le contenu du depot n'a pas encore bouge. Section 4 pose ce que son retrait
effectif implique.

## 3. Decision

**Les onze items listes en section 1 sortent de `cp-semaine.yaml`.**

Le paquet corrige, a douze items, ne tient plus `SEM-9` ("au moins vingt items"), condition que
`nadia-prof` maintient telle quelle dans son amendement. **Ce n'est donc pas un simple retrait** :
le paquet doit regagner des items neufs, dans le perimetre desormais opposable de `SEM-1`
(moments de la journee, jours de la semaine et leur ordre - jamais les mois ni les saisons), pour
retrouver au moins vingt items avant tout reaudit. Le nombre exact d'items a ajouter n'est pas
fixe ici : c'est un choix de redaction de contenu, pas un arbitrage produit.

## 4. Ce que je route

- **`sofia-sm`** (vers un developpeur) : editer `cp-semaine.yaml` - retirer les onze items de la
  section 1, puis ajouter des items neufs dans le perimetre corrige de `SEM-1` pour retrouver au
  moins vingt items au total. Le fichier vit sous `adapters/`, hors de mon perimetre d'ecriture.
- **`nadia-prof`** : reaudit du paquet complete, avant toute confirmation du Done pedagogique sur
  la forme modifiee. Ce reaudit est du par construction, pas par prudence ajoutee : `DEC-0060`,
  point 3.1, dit que le jour ou un element bouge, il "rentre" dans la porte pedagogique. Le Done
  pedagogique du 2026-08-31 reste ecrit et n'est pas efface (`nadia-prof` le dit elle-meme dans son
  amendement) ; il porte sur la forme a vingt-trois items, pas sur la forme qui va la remplacer.
- **Moi (`camille-po`)**, a la fusion de l'edition et a la reception du reaudit :
  - si le reaudit est `VALIDE` ou `VALIDE SOUS RESERVE` sans reserve bloquante : la ligne
    `MAT-CP-09` reste `couvert-valide` avec la mention actuelle inchangee (section 2) ; la ligne
    `QLM-CP-13` n'est pas concernee par ce reaudit et reste `couvert-valide` telle qu'elle est ;
  - si le reaudit est `REFUSE` : `cp-semaine.yaml` (et l'entree de catalogue qu'il sert) suivent la
    regle generale de `DEC-0045`, section 3 - un jeu ne reste expose que tant qu'aucune reserve
    bloquante n'est ouverte a son sujet, et la ligne `MAT-CP-09` repasse `en cours` jusqu'a
    correction et nouveau reaudit VALIDE.

## 5. Ce que je ne fais pas

- Je ne modifie aucun fichier YAML sous `adapters/` : hors de mon perimetre d'ecriture.
- Je ne fais bouger aucune ligne de `squad/programme/MATRICE-couverture.md` aujourd'hui : les
  deux lignes concernees (`MAT-CP-09`, `QLM-CP-13`) sont deja a l'etat que ce document decrit, et
  aucune des deux n'a de raison de changer avant que le contenu ne bouge reellement.
- Je ne prononce aucun Done pedagogique et je ne rouvre pas le verdict du 2026-08-31 : c'est
  `nadia-prof`, et elle l'a deja dit elle-meme dans son amendement.
- Je ne fixe pas le nombre ni le contenu des items a ajouter pour retrouver le plancher de
  `SEM-9` : choix de redaction, pas de perimetre produit.

## Impact

- `squad/decisions/produit/DEC-0096-...md` (ce fichier) : cree.
- `squad/decisions/produit/DEC-index.md` : ligne `DEC-0096` ajoutee.
- `adapters/adapter-content/src/main/resources/contenu/cp-semaine.yaml` : a editer (onze items
  retires, items neufs ajoutes pour retrouver le plancher `SEM-9`), execution routee a
  `sofia-sm`/un developpeur.
- `squad/programme/MATRICE-couverture.md` : inchangee a ce jour ; consequence posee en section 4
  pour le jour ou le paquet corrige aura ete reaudite.
- `squad/conformite/FICHE-bandit-manchot-semaine-cp.md` : deja mise a jour par `nadia-prof`
  (commit `d3216c84`) avec la note sur l'amendement de `SEM-1` ; reste a completer par elle au
  reaudit du paquet corrige.
