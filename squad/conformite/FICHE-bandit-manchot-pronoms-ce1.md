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
- **Statut global** : **refuse**, verdict `REFUSE` prononce le 2026-10-16 par `nadia-prof` (s57, palier 2, instance A), motif
  unique `TRV-CE1-6` (roles distribues par genre), bloc de validation ci-dessous. **Correction engagee** (`PN8-7` a `PN8-14`,
  `marc-dev`, story `S57-BR-FRA-CE1-05`), rejeu a 0,5 engage au palier 2. Etat precedent : **en attente d'audit** (fiche
  ouverte par `camille-po` le 2026-10-16, apres le commit de branchement, `K9`, `arbitrage-nadia-palier-2.md`). La ligne
  `FRA-CE1-05` reste `non couvert` dans la matrice. **L'entree `bandit-manchot-pronoms-ce1` est encore au catalogue servi** :
  son retrait est la story `S57-RETRAIT-PRONOMS-CE1` (`DEC-0120` A.106).
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

### Verdict du 2026-10-16 (`nadia-prof`, s57, palier 2, instance A, p2-3) : **REFUSE**

**Done pedagogique non prononce.** `FRA-CE1-05` reste `non couvert`. Motif unique : `TRV-CE1-6`, condition opposable de ce
paquet, n'est pas tenue (section 4). Tout le reste est tenu (sections 2 et 3) : le refus porte sur quatre textes, que je donne
(section 5), et un prenom. Le refus renvoie le lot a `camille-po` ; il ne bloque aucun autre poste.

#### 1. Ce que j'ai fait

- **Objet joue** : le code servi par `e8d14ddb`, qui contient la fusion `edcd3de9` du lot `b9f1c62f`. Jar construit dans le
  worktree `s57-nadia-p2-a2` par `bash outils/mvn-jdk21.sh -B clean package -DskipTests` (`BUILD SUCCESS`). `ce1-pronoms.yaml`
  du jar egal octet pour octet a celui de `b9f1c62f` et de `e8d14ddb` (SHA-256 `60a1e5be...159f` sur les trois, retours chariot
  retires). Lance sur le port 18511 (libre avant, `netstat -ano` filtre) ; journal *"Tomcat started on port 18511"*, PID 34004,
  arrete par ce PID seul. Dossier de poste `s57-p2-nadia-prof-a`, hors depot.
- **Done technique** : non lance par moi. Lu : `clean verify` de `marc-dev` sur `b9f1c62f` (2634 tests) et de `sofia-sm` sur
  `056f8c88` (2669 tests, 0 echec), code identique a `e8d14ddb` (`planning.md`, amendement du 2026-10-16 15:27, section 10).
- **Lecture complete** : la ligne *"lecture complete : `ce1-pronoms`, 20 items lus"* (`reponse-nadia-q-palier-2.md`, section 1,
  forme de `c62e0ad8`) est dans un commit ancetre de celui de ce verdict (`git merge-base --is-ancestor c62e0ad8 HEAD` sort 0).
  Les items changes depuis la version lue (10, 13, 15, et le commentaire de tete) sont relus et joues ici.
- **Joue comme un enfant, par l'ecran** : Chrome sans tete, fenetre dont `innerWidth` x `innerHeight` vaut 744 x 551, verifie a
  chaque manche. Page CE1 de francais, bouton *"Il, elle, ils, elles"*. 10 parties de dix manches, 100 manches : 60 justes du
  premier coup ; sur les 40 autres, deux erreurs puis un troisieme appui jusqu'a la revelation. **Chacun des 20 items joue deux
  fois en erreur**, ses deux distracteurs presses. Captures regardees : items 1, 10, 13, 15, 16, 19, apres chaque erreur et a la
  revelation ; deux ecrans de fin.
- **Ecarts contre le fichier** : 0 (propositions servies, aucun indice avant l'erreur, indice servi apres chaque erreur).

#### 2. Criteres tenus

| Critere | Constat |
|---|---|
| Ancrage, `FRA-CE1-05` | les 20 questions citent le mot entre chevrons et demandent ce qu'il designe : c'est la competence, pas une question de personnage (`PRN-CE1-1`). Frontiere avec `-04` tenue |
| `PRN-CE1-1` | il 5, elle 5, ils 4, elles 3, substituts 3, lus a l'ecran |
| `PRN-CE1-2`, a moi seule | **10 items sur 20** ou deux candidats du texte ont le genre et le nombre du mot, et ou seul le sens decide : 1, 2, 4, 5, 7, 8, 11, 12, 15, 20, relus un par un (*les fraises* ne mettent rien dans un panier, *le ballon* n'attrape rien). **Tenu** |
| `PRN-CE1-3` | le mot cite apparait une fois dans chaque texte ; son referent est dans la meme phrase ou la precedente ; aucun *il* impersonnel |
| Exactitude | **20 referents justes sur 20**, chacun relu avec son texte ; 40 distracteurs, tous des mots du texte, tous plausibles, aucun absurde |
| `PN4`, ma propriete | 0 item ou la bonne reponse est la seule proposition a majuscule |
| Intrus du premier mot | 5 items (5, 6, 7, 8, 14) ; bonne reponse sur 1, distracteur sur 4 : pas regulier vers la reponse |
| `TRV-CE1-2` | bonne reponse strictement la plus longue sur 8 items sur 20 |
| `LON-CE1-3`, `-4`, `-5` | textes de 8 a 12 mots, 3 phrases au plus, 8 mots au plus par phrase ; 3 mots au plus par proposition |
| `TRV-CE1-4`, `-5`, `-7` | aucun mot exclu ; neuf indices distincts, dix mots au plus, aucun ne donne la reponse ni n'enseigne une regle fausse ; 20 textes distincts |
| Items 10 et 13 | indice servi *"« Elle » désigne un mot féminin : cherche-le dans la phrase d'avant."* : on lit qu'il s'agit du mot. **Tenu** |
| Item 15 | mon texte, servi tel quel ; *le chat* et *Elias*, masculins singuliers, que le sens departage (un chat ne donne pas de biscuit) ; *Julie* s'ecarte par l'accord |
| Titre *"Il, elle, ils, elles"* (`marc-dev`) | **accepte**. Il nomme ce que l'enfant va chercher, sans mot de grammaire ni niveau. Les trois items de substitut (*le garçon*, *l'animal*, *la fille*) n'y sont pas nommes : un titre d'enfant ne dit pas tout le contenu, et ces items posent la meme question |
| Consigne *"Lis le petit texte, puis réponds à la question."* | **acceptee** : neuf mots, une seule action par proposition, celle de `ce1-texte-narratif`, deja jugee |
| Place de l'entree | derniere du catalogue ; sur la page CE1 de francais, le bouton vient juste apres *"Lis et réponds"* (`texte-narratif-ce1`) : les deux jeux a texte sont voisins. **Acceptee** |
| Commentaire de tete (*"vingt prenoms masculins et vingt et un prenoms feminins"*) | **exact pour la table du test** : 41 prenoms distincts dans les textes, 20 et 21 en comptant *Sacha* masculin et *Nour* feminin, deux prenoms mixtes. L'equilibre a un pres de `TRV-CE1-6` depend de ce classement, qui est une declaration d'auteur (le test le dit) |
| *Julie* | prenom de ma correction (3.5) ; il fait passer les feminins a 21, dans l'equilibre a un pres |

#### 3. Le pli, mesure : je suis la seule garde sur ce paquet

Le paquet n'est pas dans la cote d'`ines-design`. Mesure faite sur ce jar, a 744 x 551, bas de `.indice` apres une erreur :

- ordre servi : **20 sur 20** au plus 551, de 404 a 508 ;
- six ordres : **240 couples sur 240**, maximum 508 (item 20, dont les trois propositions passent sur deux rangees) ;
- apres la seconde erreur : memes valeurs.

**Tenu**, aucune reserve de pli. **Revelation, hors levee** (`Q-R2`) : sur l'item 20 seul, l'indice descend a 594 et la barre de
defilement apparait ; la reponse revelee reste visible. **Non mesure** : une autre largeur que 744, un appareil reel, une autre
police, un autre navigateur. Aucun test ne garde cette position : une modification de `recreo.css` ou des gabarits peut la defaire
sans un rouge.

#### 4. Motif du refus : roles distribues par genre (`TRV-CE1-6`)

`TRV-CE1-6` (`ARBITRAGE-PREALABLE-s48-francais-ce1.md`, section 2) : *"aucun rôle, aucune activité, aucune émotion distribués par
genre"*. Sur les huit items ou un garcon et une fille sont en relation, l'un agissant envers l'autre :

| Item | Texte servi | Qui agit, qui recoit |
|---|---|---|
| 3 | *Zoé range le jeu de Paul.* | la fille range les affaires du garcon |
| 9 | *Bastien ouvre la porte à Maëlle. Il entre dans la classe.* | le garcon agit, la fille ne fait rien |
| 10 | *Ilyes donne un gâteau à Sofia. Elle dit merci.* | le garcon donne, la fille remercie |
| 13 | *Léo lit un livre à Rose. Elle écoute bien.* | le garcon lit, la fille ecoute |
| 15 | *Le chat de Julie voit Elias. Il lui donne un biscuit.* | le garcon donne ; *Julie* n'agit pas (mon texte) |
| 16 | *Mina et Hamza regardent un chien. Le garçon lui donne de l'eau.* | le garcon agit |
| 18 | *Adrien parle à Manon. La fille lui répond avec un sourire.* | le garcon parle, la fille sourit |
| 19 | *Nael et Eva cueillent des fleurs. Ils les offrent à Clément.* | un garcon et une fille donnent a un garcon |

**Aucun item ou une fille agit envers un garcon qui recoit, ecoute ou remercie.** Sur sept items, le garcon donne, lit, parle,
ouvre, et la fille recoit, remercie, ecoute, sourit, ou range pour lui. Chaque phrase seule est banale ; c'est la regularite
que la condition interdit, et un enfant qui joue dix manches en voit plusieurs. Ce n'est pas un fait faux ni un defaut de
pronom : c'est la seule condition opposable que le paquet ne tient pas.

**Correction de mon propre constat.** Ma lecture complete du 2026-10-16 (`reponse-nadia-q-palier-2.md`, 1.1) a ecrit *"rien"*
pour seize items : elle lisait les referents et `PRN-CE1-3`, pas `TRV-CE1-6`. Et l'item 15, que j'ai ecrit, ajoute un cas de
plus : *Julie* n'y est que la proprietaire du chat. La condition date de s48 : je l'applique, je ne la releve pas.

**Item 16, joue.** J'avais garde ce point pour le jeu (1.1). Seul le prenom dit qui est *le garçon*, et *Hamza* finit en *-a*
comme *Mina* et comme la plupart des prenoms de filles que lit un enfant de CE1. L'enfant qui ne connait pas ce prenom choisit
au hasard entre deux, et l'indice *"Cherche qui est un garçon dans la phrase d'avant."* ne l'aide pas : l'item mesure la
connaissance d'un prenom, pas le substitut. Il entre dans la correction parce qu'elle rouvre le paquet ; seul, il aurait ete une
reserve non bloquante.

#### 5. Ce qui leve le refus : textes exacts

Numeros comptes depuis 1 sur `- enonce:`. Enonces inchanges. Espaces ordinaires ici ; dans le paquet, U+00A0 dans les chevrons et
devant `?` et `:`, comme ailleurs. L'orthotypographie est au `relecteur-pedagogique`.

| Item | Champ | Texte exact |
|---|---|---|
| 3 | `texteSupport` | `Zoé prête son jeu à Paul. Elle ouvre la boîte.` |
| | `distracteurs` | `["Paul", "son jeu"]` (bonne reponse et indice inchanges) |
| 9 | `texteSupport` | `Maëlle ouvre la porte à Bastien. Il entre dans la classe.` |
| | `indice` | `« Il » désigne un mot masculin : cherche-le dans la phrase d'avant.` (bonne reponse et distracteurs inchanges) |
| 13 | `texteSupport` | `Rose lit un livre à Léo. Elle tourne les pages.` (le reste inchange) |
| 16 | `texteSupport` | `Mina et Omar regardent un chien. Le garçon lui donne de l'eau.` |
| | `bonneReponse` | `Omar` (distracteurs et indice inchanges) |
| 18 | `texteSupport` | `Manon parle à Adrien. La fille lui pose une question.` |
| | `distracteurs` | `["Adrien", "une question"]` (bonne reponse et indice inchanges) |

**Ce qui tient apres correction**, verifie a la lecture : sur les huit items en relation, la fille agit envers le garcon sur 3, 9,
13 et 18, le garcon envers la fille sur 10, 15 et 16, l'item 19 reste mixte. Item 9 : l'ancien indice (*"Cherche qui fait
l'action dans la phrase d'avant."*) designerait *Maëlle*, d'ou l'indice neuf, de la forme de 10 et 13 ; *Bastien* est le seul
masculin du texte, et le sens dit maintenant la meme chose que l'accord. Categories inchangees (3, 9, 13 par l'accord ; 16 et 18
substituts) : `PRN-CE1-2` reste a 10. Mot cite une fois par texte ; textes de 10 a 12 mots, phrases de 8 mots au plus ; aucune
bonne reponse seule a majuscule ; aucun mot exclu ; prenoms 20 et 21, *Omar* remplacant *Hamza* (origines inchangees dans leur
variete). Distracteurs, tous des mots du texte. La table de prenoms du test et le reste du code sont au developpeur.

**Rejeu** : 0,5 (des textes changent), au palier qui l'engage. Je rejoue les 20 items, le pli compris.

#### 6. Item 19, *"Nael"* : je le garde, et je le ferme

Je l'avais route au `relecteur-pedagogique` ; son releve ne le traite pas, et l'item est a moi. **Aucune correction.** *Nael* est
un prenom ; aucune regle du depot ne fixe la graphie d'un prenom, et la reponse ne demande pas de le prononcer : *"Nael et Eva"*
se distingue de *"Eva"* et de *"Clément"* par le nombre. Je n'ai verifie dans aucune source du depot que cette graphie sans trema
est donnee en France ; je ne le pretends pas. Si l'equipe prefere *Naël*, qui aide l'enfant a lire deux voyelles, c'est une
recommandation non bloquante.

**Recommandation non bloquante, item 19** : l'indice *"Demande-toi : qui peut faire cette action ?"* ne departage pas apres une
erreur sur *"Eva"*, qui peut cueillir et offrir. *"Relis la phrase d'avant pour trouver qui agit."* (items 6 et 14) le ferait. A
`camille-po` de la prendre ou non ; l'enfant avance de toute facon.

#### 7. Ce dont je reste la seule garde sur cette ligne

`PRN-CE1-2`, l'exactitude de chaque referent, la distribution des roles par genre (le test compte les prenoms, pas ce qu'ils
font), qu'un indice departage apres chaque erreur, la position de l'indice par rapport au pli. Aucun test ne les voit.

**Ce que je n'ai pas joue** : une autre largeur que 744 ; un appareil reel, une autre police, un autre navigateur ; la lecture
d'ecran.

**Identifiants** : aucun neuf, aucun renomme.

**Tarif consomme** : 1,0 (lot `-05`, instance A).

**Signe** : `nadia-prof`, s57, palier 2, instance A, 2026-10-16.
