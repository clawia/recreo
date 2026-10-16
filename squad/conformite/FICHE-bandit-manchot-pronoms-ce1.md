# Fiche de conformite - bandit-manchot-pronoms-ce1

- **Mecanique** : bandit manchot, `type: question`, avec texte support. Aucune modification de la mecanique.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-pronoms.yaml` (20 items), ecrit en s53
  (`S53-FRA-PRONOMS`, `lea-dev`), **corrige par le lot** selon `nadia-prof` (section 4).
- **Entree de catalogue** : `bandit-manchot-pronoms-ce1`, `paquet: ce1-pronoms`, `nombreDeManches: 10`.
  Titre servi *"Il, elle, ils, elles"*, consigne servie *"Lis le petit texte, puis réponds à la question."*
  (celle de l'entree `bandit-manchot-texte-narratif-ce1`). Place : derniere entree du catalogue. **Le titre est
  de la main de `marc-dev`, non juge** (`rapport-marc-dev-palier-2.md`, section 3 et `Q6`).
- **Commit de branchement** : `b9f1c62f` (`marc-dev`, 2026-10-16 10:25), branche `s57-marc-fra-ce1-05`, poste `p2-3`,
  **fusionne sur `round-two`** par `edcd3de9`. Non pousse.
- **Ligne declaree** : `FRA-CE1-05`. **Story** : `S57-BR-FRA-CE1-05` (`docs/backlog/backlog.md`). **Decision** :
  `DEC-0120` A.33, A.58, A.96.
- **Competence programme visee** : `squad/programme/francais/competences-ce1.md`, `FRA-CE1-05`, *"Identifier à qui
  renvoie un pronom personnel (`il`, `elle`, `ils`, `elles`) ou un substitut simple dans un texte court."*
- **Conditions opposables** : `PRN-CE1-1` a `PRN-CE1-3`, `LON-CE1-3` et `-4` (`ARBITRAGE-PREALABLE-s48-francais-ce1.md`,
  section 3.5), `TRV-CE1-1` a `-7`, `LON-CE1-1`, `-2`, `-5`, et `arbitrage-nadia-palier-2.md` section 6.1 (points 1 a 5)
  et 6.2 (ligne `-05`). Plafond de la ligne : `couvert-valide`.
- **Statut global** : **en attente d'audit**. Fiche ouverte par `camille-po` le 2026-10-16, apres le commit de
  branchement (`K9`, `arbitrage-nadia-palier-2.md`). Aucun verdict. La ligne `FRA-CE1-05` reste `non couvert` dans la
  matrice, qui ne bouge qu'au verdict.
- **Derniere mise a jour** : 2026-10-16.

---

## Competence visee

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| francais | CE1 (cycle 2) | Lecture et comprehension de l'ecrit | Identifier a qui renvoie un pronom personnel (il, elle, ils, elles) ou un substitut simple dans un texte court | `squad/programme/francais/competences-ce1.md`, `FRA-CE1-05` |

Frontiere avec `FRA-CE1-04` (texte narratif) : la question cite le pronom, sinon c'est une question de personnage
(`PRN-CE1-1`). Collision avec `FRA-CM1-03` examinee et ecartee (`ARBITRAGE-PREALABLE-s48-francais-ce1.md`, section 3.5).

## Criteres attendus - camille-po

Valeurs mesurees par `marc-dev` sur `b9f1c62f`, rapport de developpeur, section 3. Elles sont des declarations de ce
rapport, relues par aucune autre personne dans le depot (section 3 de cette fiche).

- [ ] **`PN1`** : 20 items.
- [ ] **`PN2`, `PRN-CE1-1`**, recompte sur les champs `enonce` : *il* 5, *elle* 5, *ils* 4, *elles* 3, substituts simples
  3 ; *ils* et *elles* reunis 7 (seuil 4) ; substituts 3 (plafond 6). La question cite le pronom entre chevrons.
- [ ] **`PN3`, `PRN-CE1-3`** : le mot cite n'apparait qu'une fois dans le texte support, sur les 20 items ; aucun `il`
  impersonnel pris pour cible.
- [ ] **`PN4`**, ses mots : *"la bonne reponse n'est jamais la seule proposition a majuscule (Unicode)"*. Cible 0 ; 1 avant
  correction (item 15).
- [ ] **`PN5`, `LON-CE1-3` et `-4`** : 12 mots au plus par texte, 3 phrases au plus, 8 mots au plus par phrase
  (plafonds : 30, 4 et 12).
- [ ] **`PN6`, `LON-CE1-5` et `TRV-CE1-5`** : 3 mots au plus par proposition (plafond 6), 10 mots au plus par indice
  (plafond 12).
- [ ] **`PN7`**, point d'audit : un mot cite dans un indice ou un enonce est entre chevrons. Les 20 enonces et les deux
  indices des items 10 et 13 (section 4).
- [ ] **`PN8`** : les trois corrections de `nadia-prof` (section 4) sont recopiees sans reformulation, et le seul YAML
  modifie est celui du paquet.
- [ ] **`PRN-CE1-2`** : le sens decide dans au moins un tiers des items. **Aucun test ne le garde** ; seule la lecture
  de `nadia-prof` le dit.
- [ ] Exactitude de chaque referent, relue a l'ecran : une erreur de fait est un refus a elle seule (`arbitrage-nadia-palier-2.md`
  6.1, point 5).
- [ ] Texte servi a l'enfant integralement accentue, orthographe et ponctuation irreprochables (`DEC-0002`).

**Ce qui serait un refus** (`arbitrage-nadia-palier-2.md`, 6.2) : *"un pronom cite deux fois dans le texte, ou un `il`
impersonnel pris pour cible"*.

## Etat de la porte technique

**Ce qu'un fichier du depot prouve, et rien de plus.** Chaque ligne cite son fichier.

- **Branche, `b9f1c62f`** : `rapport-marc-dev-palier-2.md`, section 4, jobs lances par `marc-dev` sur l'arbre du commit :
  `build` (`bash outils/mvn-jdk21.sh -B clean verify`, racine, sans `-pl`) `BUILD SUCCESS`, **2634 tests**, 0 echec, dont
  1604 pour `adapter-content` (+37, `PaquetCe1PronomsTest`) ; `chemins`, `prefixes` sortis 0 ; `outils` : 12 juges sur 12
  sortis 0. JDK 21.0.10, valeur de `java.version` lue dans les rapports Surefire.
- **Arbre fusionne** : `planning.md` (amendement du 2026-10-16 12:46, sections 2 et 9) : `round-two` a `9f110983`, qui
  contient ce lot, `build` `BUILD SUCCESS`, **2664 tests**, 0 echec, et `chemins`, `prefixes`, `outils` (13 juges) sortis
  0. `planning.md` (amendement du 2026-10-16 15:27, section 10) : `056f8c88`, `build` `BUILD SUCCESS`, **2669 tests**, 0
  echec, 0 erreur, 0 ignore, et les trois autres jobs sortis 0.
- **Jeu au jar** : `rapport-marc-dev-palier-2.md`, section 3 : le titre est sur la page *Français* du CE1 ; les items 10,
  13 et 15 servent les textes corriges, **lus dans le DOM rendu a 744 x 551, apres une erreur par item**. Section 7 du meme
  rapport : *"lu dans le DOM, pas regarde a l'oeil"*.
- **Garde des criteres** (`rapport-marc-dev-palier-2.md`, section 3, `K8`) : gardes par un test avec jeu fautif :
  `PRN-CE1-1`, `PRN-CE1-3`, `PN4`, `LON-CE1-3`, `-4`, `-5`, `TRV-CE1-5`, `TRV-CE1-6`, le point d'audit des chevrons, `K3`.
  **Non gardes, dit par le rapport** : `PRN-CE1-2`, l'exactitude et l'unicite de chaque referent, ce que dit un indice au
  regard du monde, les roles par genre, qu'un mot cite soit un pronom hors des chevrons.

- **Rejeu par `camille-po` pour cette fiche** (lecture des blobs git, `git diff`, aucun Maven) : sur `b9f1c62f`, `edcd3de9` et
  `e8d14ddb`, 20 items ; les deux indices sont en position 10 et 13 ; l'item 15 porte le texte de `nadia-prof` ; 0 item dont la
  bonne reponse est la seule proposition a majuscule. `git diff -U0 aa9d703c b9f1c62f` sur le YAML : 5 hunks. `git diff --name-only
  aa9d703c b9f1c62f` : 8 fichiers, tous ceux que le rapport annonce. Le YAML servi n'a pas change entre `b9f1c62f` et `e8d14ddb`.

**Ce que le depot ne prouve pas.**

- **La CI distante n'est lue dans aucun de ces fichiers.** Les quatre jobs de `ci.yml` ont ete lances par des agents, sur
  poste, pas lus sur la CI.
- **L'etape *"Package du fat jar"* du job `build` n'est lancee dans aucun de ces fichiers.**
- **Aucun rapport de `yanis-qa` ne couvre ce lot** (`ls rapport-qualite-*` : `rapport-qualite-garde-c1.md` et
  `rapport-qualite-lot-24.md` seulement). Aucune mutation par une tierce personne.
- **Aucune cote de pli (744 x 551) de ce paquet** : le fichier de cote de reference
  (`cote-de-reference-forme-commune-2026-10-16.md`) cite le lot comme difference entre deux jars, et mesure six autres
  paquets. La forme commune (`recreo.css`) est fusionnee (`aca0d90f`) : le jar de l'audit la porte. Regime du pli pour ce
  lot : reserve non bloquante, nommee et datee (`arbitrage-nadia-palier-2.md`, 6.1 point 4 ; mots de l'utilisatrice pour
  `Q-N4` : *"le pli n'est pas bloquant"*, `reponse-nadia-q-palier-2.md`, section 2).
- **Ordre des gestes, a dire** : l'amendement de `PN8` (`36766dae`, 2026-10-16 10:48) est **posterieur** au commit du lot
  (`b9f1c62f`, 10:25) ; `marc-dev` a recopie le texte de `nadia-prof` depuis son fichier (`rapport-marc-dev-palier-2.md`,
  `Q5`). Le texte est le meme, `releve-relecteur-palier-2.md` section 3.2 l'etablit a l'espace pres.
- **La ligne de lecture complete** : la forme que la commande de `DEC-0120` A.96 lit (`c62e0ad8`) **n'est pas ancetre de
  `b9f1c62f`** : `git grep -l -E 'lecture complete : .?ce1-pronoms.?, 20 items lus' b9f1c62f -- squad docs` rend 0
  fichier. Elle est ancetre de `edcd3de9` (`git merge-base --is-ancestor c62e0ad8 edcd3de9` sort 0). Le critere 2 de
  `S57-ARB-PALIER-2` se lit sur le commit du verdict, pas sur celui du lot : a verifier au verdict (`marc-dev`, `Q4`).

## Ce dont `nadia-prof` reste la seule garde sur cette ligne

`PRN-CE1-2` (le sens decide), l'exactitude de chaque referent, la lisibilite d'un item, l'intrus du premier mot et la
majuscule devenus reguliers sur une question, la position de `.indice` apres une erreur. Aucun compteur ne les voit
(`arbitrage-nadia-palier-2.md`, section 10).

## Ce qui est a juger et n'est pas ecrit par `nadia-prof`

- Le titre *"Il, elle, ils, elles"* (de `marc-dev`) et la place de l'entree (`Q6`).
- Le commentaire de tete du paquet, corrige de *"vingt prenoms masculins et vingt prenoms feminins"* en *"vingt prenoms
  masculins et vingt et un prenoms feminins"* par `marc-dev` : `reponse-nadia-q-palier-2.md` 3.5 (derniere phrase) le lui
  laisse, sans en faire un critere.
- *"Nael"* (item 19) : `reponse-nadia-q-palier-2.md` 1.1 le route au `relecteur-pedagogique` comme orthographe d'un
  prenom. `releve-relecteur-palier-2.md` ne le traite pas (`grep -c Nael` : 0).
- Item 16 : *"Mina et Hamza regardent un chien. Le garçon lui donne de l'eau."* Point d'audit que `nadia-prof` lit au
  jeu, sans refus ni reserve a la lecture (1.1).

## Corrections de `nadia-prof`, texte exact et provenance

**Provenance commune.** `squad/sprints/s57-version-cp-ce1-cm1-trois-instances/reponse-nadia-q-palier-2.md`, auteure
`nadia-prof`, 2026-10-16 08:15, apres lecture complete de `ce1-pronoms` (20 items, version de `46490d0d`), commit `5d2f34fb`
(forme des lignes de lecture complete corrigee par `c62e0ad8`). Numeros d'items comptes depuis 1 sur `- enonce:`. Le fichier
de `nadia-prof` porte des espaces ordinaires ; le paquet porte U+00A0 dans les chevrons et devant `?` et `:`
(`releve-relecteur-palier-2.md`, 3.2). Rien n'est reformule ici.

**C1. Items 10 et 13, texte de l'`indice`** (section 1.1, ligne 62, correction demandee, `RQ11`) :

```
« Elle » désigne un mot féminin : cherche-le dans la phrase d'avant.
```

Rien d'autre ne change sur ces deux items. Son motif : sans chevrons, *"Elle désigne"* se lit comme une personne qui
designe, et l'enfant cherche une fille dans le texte avant de comprendre qu'on parle du mot.
Texte present dans `b9f1c62f` : indice a l'identique en position 10 et 13 (a l'espace insecable pres), 0 indice ouvrant par *"Elle "* (`PN8-2`, `PN8-3`, rejoues par `camille-po` pour cette fiche).

**C2. Item 15** (section 3.5, lignes 143 a 147, `Q-P6`, correction avant le branchement, cible 0 sur `PN4`) :

```
  - enonce: "Que désigne « il » dans le texte ?"
    texteSupport: "Le chat de Julie voit Elias. Il lui donne un biscuit."
    bonneReponse: "Elias"
    distracteurs: ["le chat", "Julie"]
    indice: "Le sens de la phrase aide à choisir."
```

Enonce et indice inchanges ; `texteSupport` et `distracteurs` changent. Son motif : une valeur consignee et jugee au jeu
lui ferait valider un item dont la majuscule donne la reponse a l'enfant qui ne lit pas le texte. Texte present dans
`b9f1c62f` : les trois lignes changees a l'identique, `PN8-5` rend 0 (`camille-po`, pour cette fiche). `PN8-6` rend 5 contre
`aa9d703c`, la borne que `PN8` fixe : aucune edition du YAML hors des corrections.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.
