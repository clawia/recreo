# Fiche de conformite - bandit-manchot-familles-de-mots-ce1

- **Mecanique** : bandit manchot, `type: question`, sans texte support. Aucune modification de la mecanique.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-familles-de-mots.yaml` (24 items : 14 de
  famille, 10 d'affixe), ecrit en s53 (`S53-FRA-FAMILLES-DE-MOTS`, `marc-dev`), **corrige par le lot** selon `nadia-prof`
  (section 4).
- **Entree de catalogue** : `bandit-manchot-familles-de-mots-ce1`, `paquet: ce1-familles-de-mots`,
  `nombreDeManches: 10`. Titre servi *"Les familles de mots"*, consigne servie *"Lis bien, puis choisis la bonne
  réponse."* (celle des entrees `-13`, `-10`, `-15`). Place : apres `bandit-manchot-synonymes-contraires-ce1`. **Titre,
  consigne et place sont de la main de `sofia-sm` (casquette developpeuse), non juges** (`rapport-sofia-dev-palier-2.md`).
- **Commit de branchement** : `1febd011` (`sofia-sm`, 2026-10-16 09:57), branche `s57-sofia-fra-ce1-24`, poste `p2-4`, tete
  relue par `yanis-qa` : `6b70d7e5`. **Fusionne sur `round-two`** par `9f110983`. Non pousse.
- **Ligne declaree** : `FRA-CE1-24`. **Stories** : `S57-BR-FRA-CE1-24` avec `S57-FRA-FAMILLES-LIVRE`
  (`docs/backlog/backlog.md`). **Decision** : `DEC-0120` A.33, A.58, A.96, A.103.
- **Competence programme visee** : `squad/programme/francais/competences-ce1.md`, `FRA-CE1-24`, *"Regrouper les mots
  d'une même famille et repérer un préfixe ou un suffixe fréquent (`re-`, `dé-`, `-eur`...)."*
- **Conditions opposables** : `FAM-CE1-1` a `FAM-CE1-3` (`ARBITRAGE-PREALABLE-s48-francais-ce1.md`, section 6.2),
  `TRV-CE1-1` a `-7`, `LON-CE1-1`, `-2`, `-5`, et `arbitrage-nadia-palier-2.md` section 6.1 (points 1 a 5) et 6.2 (ligne
  `-24`). Plafond de la ligne : `couvert-valide`.
- **Statut global** : **en attente d'audit**. Fiche ouverte par `camille-po` le 2026-10-16, apres le commit de
  branchement (`K9`, `arbitrage-nadia-palier-2.md`). Aucun verdict. La ligne `FRA-CE1-24` reste `non couvert` dans la
  matrice, qui ne bouge qu'au verdict.
- **Derniere mise a jour** : 2026-10-16.

---

## Competence visee

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| francais | CE1 (cycle 2) | Vocabulaire | Regrouper les mots d'une meme famille et reperer un prefixe ou un suffixe frequent (re-, de-, -eur...) | `squad/programme/francais/competences-ce1.md`, `FRA-CE1-24` |

Frontiere avec `FRA-CE1-25` : les contraires formes par prefixe (*défaire*) appartiennent a cette ligne (`FAM-CE1-3`).

## Criteres attendus - camille-po

Valeurs mesurees par `sofia-sm` et rejouees par `yanis-qa` sur `6b70d7e5` (`rapport-qualite-lot-24.md`, sections 1 et 3).

- [ ] **`FM1`** : 24 items, plancher de 20 (`TRV-CE1-7`), trois propositions distinctes par item.
- [ ] **`FM2`, `FAM-CE1-1`**, recompte sur les champs : 14 items *"Quel mot est de la même famille"* (au moins 12), 10 items
  *"Que veut dire"* (au moins 6).
- [ ] **`FM3`, `FAM-CE1-3`, `TRV-CE1-4`** : *préfixe*, *suffixe*, *radical* et *affixe* absents de ce que l'enfant lit
  (enonce, bonne reponse, distracteurs, indice). Un item d'affixe demande ce que le mot veut dire, jamais le nom de
  l'affixe. **Voir l'ecart `E4` en section 3** : le mot *préfixe* figure dans `descripteur.competence`, que les gabarits ne
  servent pas.
- [ ] **`FM4`, `FAM-CE1-2`**, point d'audit : familles exactes et non savantes ; distracteurs dans l'ordre (a) fausse
  famille, (b) mot du meme theme sans le radical. **Aucun test ne le garde.**
- [ ] **`FM5`**, `S57-FRA-FAMILLES-LIVRE` : l'item 12 porte le texte de `nadia-prof` (section 4) ; `bonneReponse:
  "librairie"` n'apparait plus ; le diff du YAML contre la base du lot compte 34 lignes.
- [ ] **`FM6`** : les 14 items de famille portent l'indice *"Le bon mot garde un morceau et parle de la même chose."* ;
  aucune des trois formulations anterieures ne subsiste ; 28 lignes d'`indice` changees.
- [ ] **`TRV-CE1-5`** : 24 indices, 3 distincts, 12 mots au plus, aucun renvoi a l'oreille, aucun ne contient une proposition
  de son item. **`TRV-CE1-2`** : bonne reponse strictement la plus longue sur 8 items. **`LON-CE1-5`** : 4 mots au plus par
  proposition.
- [ ] Exactitude : chaque bonne reponse et chaque distracteur, relus a l'ecran. Une erreur de fait est un refus a elle seule.
- [ ] Texte servi a l'enfant integralement accentue, orthographe et ponctuation irreprochables (`DEC-0002`).

**Ce qui serait un refus** (`arbitrage-nadia-palier-2.md`, 6.2) : *"librairie encore attendu, ou une autre famille
savante"*.

## Etat de la porte technique

**Ce qu'un fichier du depot prouve, et rien de plus.**

- **Branche, `6b70d7e5`** : `rapport-qualite-lot-24.md`, sections 3 et 5, `yanis-qa` : reacteur complet `bash
  outils/mvn-jdk21.sh -B clean verify`, racine, sans `-pl`, `BUILD SUCCESS`, **2664 tests**, 0 echec, 0 erreur, 0 ignore ;
  JDK 21.0.10 (`java.version` lu dans 239 rapports Surefire). 8 mutations sur une copie, chacune avec les controles *"motif
  trouve"* et *"tests lances"* : 7 font rougir la classe concernee, **1 reste verte dans le reacteur** (`M5`, *chaton*
  remplace par *chatte* ; rouge sur la commande `FM5-2`). Verdict de `yanis-qa` : *"relecture faite, fusionnable"*.
- **Jobs lances par `yanis-qa` sur `6b70d7e5`** (meme fichier, section 5) : `build` (hors *"Package du fat jar"*), `prefixes`,
  `chemins`. **Le job `outils` n'a pas ete lance par lui.**
- **Jobs lances par `sofia-sm` sur son lot** (`rapport-sofia-dev-palier-2.md`) : le premier `build`, a `e918f43e`, etait
  `BUILD FAILURE` sur `AucunMotServiNeSEcarteDeSonAccentTest.chaqueMotServiEnProductionPorteSonAccent` (obstacle `K10`) ;
  `ADR-0065`, amendement A3 (`theo-archi`, 2026-10-16), le leve, et le `build` de `yanis-qa` a `6b70d7e5` est vert. Le sujet du
  commit `1febd011` dit *"NON LIVRE : rouge sur K10"* : il decrit l'arbre de ce jour-la.
- **Arbre fusionne** : `planning.md` (amendement du 2026-10-16 12:46, sections 2 et 9) : `9f110983`, `build` `BUILD SUCCESS`,
  **2664 tests**, 0 echec ; `chemins`, `prefixes`, `outils` (13 juges) sortis 0. `planning.md` (amendement du 15:27, section
  10) : `056f8c88`, `build` `BUILD SUCCESS`, **2669 tests**, 0 echec, 0 erreur, 0 ignore, et les trois autres jobs sortis 0.
- **Rejeu par `camille-po` pour cette fiche** (lecture des blobs git, `git diff`, aucun Maven) : sur `1febd011`, `9f110983` et
  `e8d14ddb`, 24 items ; l'item 12 porte les quatre lignes de `nadia-prof`, precede de `# FAM-CE1-2: a, b` ; 0
  `bonneReponse: "librairie"` ; 14 indices de `FM6` ; 14 enonces de famille. Contre la base du lot `4004fab7` : 34 lignes
  changees (`FM5-6`), 28 lignes d'`indice` (`FM6`), 0 ligne ouvrant par l'une des trois anciennes formulations. `git diff
  --name-only 4004fab7 1febd011` : 7 fichiers, ceux que le rapport annonce. Le YAML servi n'a pas change entre `1febd011` et
  `e8d14ddb`.

**Ce que le depot ne prouve pas.**

- **La CI distante n'est lue dans aucun de ces fichiers.** Les jobs ont ete lances par des agents, sur poste.
- **L'etape *"Package du fat jar"* n'est lancee dans aucun de ces fichiers.**
- **Aucun jar lance, aucun ecran joue pour ce paquet** (`rapport-sofia-dev-palier-2.md` : *"aucun jar n'a ete lance"* ;
  `rapport-qualite-lot-24.md`, section 6 : *"aucun jar lance, aucun ecran joue, aucune cote de pli"*). Le rendu de l'item 12 et
  des 14 indices n'a ete vu par personne avant l'audit. Regime du pli : reserve non bloquante, nommee et datee
  (`arbitrage-nadia-palier-2.md`, 6.1 point 4 ; mots de l'utilisatrice pour `Q-N4` : *"le pli n'est pas bloquant"*,
  `reponse-nadia-q-palier-2.md`, section 2). La forme commune (`recreo.css`) est fusionnee (`aca0d90f`) ; le fichier de cote
  cite ce lot comme difference entre deux jars et ne mesure pas ses 24 items.
- **`nombreDeManches: 10`** : non garde par un test, non mute (`rapport-qualite-lot-24.md`, section 6 : *"a ne pas supposer
  corrigee"*).
- **`E3`** : le texte de l'item 12 n'est garde par aucun test Java (`M5`). Seules les commandes `FM5-2` et `FM5-3` le gardent.
  Aucun test d'egalite n'a ete ajoute ; la decision est a `camille-po` ou `theo-archi` (`planning.md`, 12:46, section 1).
- **`E4`, ouvert** : l'amendement de `FM3` (le libelle `descripteur.competence`, qui porte *préfixe*, n'est pas servi par les
  gabarits mais est inventorie comme chaine exposee par le port entrant ; `fam3` ne lit que les quatre champs de l'enfant) n'est
  pas ecrit dans `docs/backlog/backlog.md`. `ADR-0065` A3 decide que le libelle du paquet n'est pas reecrit.
- **`FM5-6`** : la commande de la story porte `$(git merge-base round-two <ref>)`, qui rend 0 sur `round-two` maintenant que la
  branche est fusionnee ; la valeur 34 se mesure contre `4004fab7` (`rapport-qualite-lot-24.md`, section 1). Amendement a ecrire
  (`planning.md`, 15:27, `CR24`).
- **Ordre des gestes, a dire** : l'annotation de `FM5` (10:45) et le commit de l'amendement (`36766dae`, 10:48) sont
  **posterieurs** au commit du lot (`1febd011`, 09:57) ; `FM6` (`79130ca9`, 09:39) lui est anterieur sans etre ancetre de
  `1febd011`. La developpeuse a recopie le texte de `nadia-prof` depuis son fichier ; `releve-relecteur-palier-2.md`, section
  3.3, etablit que le texte est le sien, a l'espace pres.
- **La ligne de lecture complete** : la forme que la commande de `DEC-0120` A.96 lit (`c62e0ad8`) est ancetre de `1febd011`
  (`git merge-base --is-ancestor c62e0ad8 1febd011` sort 0). Le critere 2 de `S57-ARB-PALIER-2` se lit sur le commit du verdict.

## Ce dont `nadia-prof` reste la seule garde sur cette ligne

Les familles exactes et non savantes (*chaton* vrai, *château* faux), l'ordre des distracteurs (a) et (b), qu'un indice
departage apres chacune des deux erreurs possibles, la position de `.indice` apres une erreur
(`arbitrage-nadia-palier-2.md`, section 10 ; `reponse-nadia-q-palier-2.md`, section 4).

## Ce qui est a juger et n'est pas ecrit par `nadia-prof`

- Le titre *"Les familles de mots"*, la consigne *"Lis bien, puis choisis la bonne réponse."* et la place de l'entree
  (`rapport-sofia-dev-palier-2.md`).
- Le libelle `descripteur.competence` du paquet, copie de la ligne du referentiel (`E4`).

## Corrections de `nadia-prof`, texte exact et provenance

**Provenance commune.** `squad/sprints/s57-version-cp-ce1-cm1-trois-instances/reponse-nadia-q-palier-2.md`, auteure
`nadia-prof`, 2026-10-16 08:15, apres lecture complete de `ce1-familles-de-mots` (24 items, version de `29d02bc6`), commit
`5d2f34fb` (forme des lignes de lecture complete corrigee par `c62e0ad8`). Le fichier de `nadia-prof` porte des espaces
ordinaires ; le paquet porte U+00A0 dans les chevrons et devant `?` (`releve-relecteur-palier-2.md`, 3.3). Rien n'est
reformule ici.

**C1. Item *livre*, remplace** (section 3.6, lignes 159 a 163, `Q-S8`). Decision de `nadia-prof` : *"l'item est remplace par
un autre mot de depart"*, au meme rang dans le paquet (le 12e, compte sur `- enonce:`). Texte exact :

```
  # FAM-CE1-2: a, b
  - enonce: "Quel mot est de la même famille que « chat » ?"
    bonneReponse: "chaton"
    distracteurs: ["château", "souris"]
    indice: "Le bon mot garde un morceau et parle de la même chose."
```

Son motif : *livret* garderait *livre*, mais face a *livrer* l'item se jouerait sur une lettre finale (decodage, pas famille) ;
*chat* et *chaton* sont une famille transparente pour un enfant de CE1, et *château* est la fausse famille que `FAM-CE1-2` (a)
demande. Motif de fond, `arbitrage-nadia-palier-1.md` 6.3 : *livre* et *librairie* viennent tous deux du latin *liber*, le
premier par la voie populaire, le second par la voie savante.

**C2. Recommandation non bloquante, l'indice de C1 sur les 14 items de famille** (section 3.7, `Q-B2`). Ses mots :
*"Recommandation non bloquante, a `camille-po` de la prendre ou non : l'indice de 3.6 sur les 14 items de famille."* Texte :

```
Le bon mot garde un morceau et parle de la même chose.
```

**Prise par `camille-po`** (`DEC-0120` A.103 ; `docs/backlog/backlog.md`, annotation *"`Q-B2` repondue, critere FM6"*, commit
`79130ca9`) : critere `FM6`. Ce que sa correction de son propre constat dit : *"Cherche le mot qui parle de la même
chose."* (5 items) departage apres une erreur sur le distracteur de theme (b), pas sur la fausse famille (a) ;
*"Un mot de la même famille garde un morceau du mot."* (5 items) fait l'inverse ; *"Compare les trois mots avec le mot de
départ."* (4 items) ne departage aucune des deux. Aucun n'enseigne une regle fausse. Elle ajoute : si la recommandation n'etait
pas prise, elle jugerait au jeu, en commettant les deux erreurs sur chaque item de famille. **La recommandation est prise** :
ce qu'elle lit au jeu est le texte unique, sur les 14 items.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.
