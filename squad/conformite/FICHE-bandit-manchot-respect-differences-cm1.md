# Fiche de conformite - bandit-manchot-respect-differences-cm1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-emc-respect-differences.yaml`
  (11 items, type `question`), ecrit a la session de contenu de s53, items 4, 7 et 8 remplaces au
  branchement par `lea-dev` (commit `0839f9dc`, texte de mon arbitrage prealable de s55, instance B,
  sections 2.1 a 2.3, avec un ecart au distracteur de Tom de l'item 4, section 3 ci-dessous).
- **Entree de catalogue** : `bandit-manchot-respect-differences-cm1`, titre servi *"Ce que le respect
  interdit"*, consigne servie *"Lis bien, puis choisis la bonne réponse."*, `nombreDeManches: 10`.
- **Ligne declaree** : `EMC-CM1-03`. **Story** : `S55-BR-EMC-CM1-03` (`DEC-0115`).
- **Conditions opposables** : `EMC-1`, `EMC-4`, `EMC-5`, `EMC-6`, `EMC-8`, `BIA-1`, `BIA-2`, `FER-1` a
  `FER-3`, `CAT-1`, `PLA-1`, `HIS-3`, `CYC-1`, `DEC-0064`, et la regle de s53 section 4.1 (un indice ne
  redit pas le contenu de la bonne reponse de son item), etendue a ce paquet par mon arbitrage prealable
  de s55, instance B, section 1.2. `EMC-9` sans objet sur cette ligne (meme section).
- **Statut global** : **VALIDE SOUS RESERVE**, prononce le 2026-10-08 par `nadia-prof` (s55, instance
  B), une reserve non bloquante (`EMC-CM1-R8`). Premier passage ; fiche ouverte par ce verdict.
- **Etat au 2026-10-11** : `EMC-CM1-R8` levee au rejeu du 2026-10-11 (s56) ; plus aucune reserve ouverte. Statut global inchange.
- **Derniere mise a jour** : 2026-10-11 ; avant : 2026-10-08.

---

## Bloc de validation pedagogique

### Verdict du 2026-10-08 (`nadia-prof`, s55, instance B) : **VALIDE SOUS RESERVE**

**Done pedagogique prononce le 2026-10-08.** `camille-po` peut passer `EMC-CM1-03` au statut d'une
ligne validee sous reserve, **avec la mention exigee d'avance pour l'EMC CM1, reproduite mot pour mot** :
*"connaissances civiques et reconnaissance de situations seules ; aucun comportement, aucun engagement et
aucune participation a une decision collective ne sont servis par ce produit."* Une reserve,
`EMC-CM1-R8` (section 5), a faire en story.

#### 1. Ce que j'ai fait

**Audit complet, pas degrade.** Worktree `s55-nadia-audit-b-emc`, a `14455b56`. `bash outils/mvn-jdk21.sh
-B clean verify`, reacteur complet : `BUILD SUCCESS`, JDK 21.0.10 rapporte par l'enveloppe ; domain 120,
application 18, adapter-web 402, adapter-content 1415, bootstrap 312, bloc final 6, zero echec. Job
`build` seul ; `prefixes`, `chemins`, `outils` non lances sur ce build. Jar de ce build ; paquet, paquet
voisin et `catalogue.yaml` embarques identiques octet pour octet aux fichiers commites (`cmp`). Lance sur
la machine virtuelle 21.0.10 (rapportee par le jar au demarrage), port dedie a l'instance B, arrete par
son PID. Joue par les routes servies ; titre lu sur l'ecran de choix du niveau CM1.

**Parties jouees** : neuf parties de dix manches. Quatre avec les deux distracteurs presses a chaque
manche, deux avec la bonne reponse du premier coup a chaque manche, trois melangees (erreur unique,
deux erreurs ou reussite, tire par manche). **Les onze items ont ete tires, chacun de leurs 22 distracteurs
presse au moins une fois, chaque indice lu a l'ecran, et chaque item reussi du premier coup au moins une
fois.** Ecrans de reussite (*"Bravo !"*), d'echec (*"Ce n'est pas ça. Essaie encore !"* puis l'indice) et
de fin de partie lus.

#### 2. Ancrage au programme

`EMC-CM1-03` : *"Identifier ce qu'interdit le respect d'autrui dans ses différences (apparence, origine,
convictions, situation de handicap)."* Les quatre termes de la parenthese sont en bonne reponse (items 1 et
5 apparence, 2 origine, 3 convictions, 4 handicap) ; les items 6 a 8 disent ce que veut dire et ce que
fonde ce respect ; les items 9 a 11 font reconnaitre le seul motif valable d'ecarter un eleve. **C'est
bien cette competence, sous la forme de l'interdit, jamais sous celle de l'enumeration des differences**
(condition de la matrice).

#### 3. Point 1 de `lea-dev` (ecart `E1`) : *"cette partie"* au lieu de *"la partie"*, **accepte**

Mon texte exact faisait rougir `LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest` (`ADR-0086`,
clause 2) : le paquet n'avait pas d'entree de catalogue et ce garde-fou ne le lisait pas quand j'ai ecrit
l'item. **Le defaut etait dans mon texte, pas dans le garde-fou.** J'avais ecrit qu'un autre texte qui
atteint la meme barre est accepte, juge contre la barre.

La barre de 2.1 avait deux parties. (a) *"le motif est le handicap seul, dans une activite ou il n'a aucune
incidence"* : tenue, la bonne reponse est mot pour mot la mienne. (b) *"les trois propositions ont la meme
longueur (`CAT-1`)"* : 46, 49 et 41 caracteres, la bonne reponse au milieu. Ce que `CAT-1` defend, c'est
que la longueur ne designe pas la bonne reponse : elle ne la designe pas, et c'est desormais un distracteur
qui est le plus long. Mon texte (46, 46, 41) ne tenait pas mieux cette propriete. *"cette partie"* reprend
*"un jeu de cartes"* de l'enonce, le francais est naturel, et l'indice (*"Une raison juste parle de la
partie, pas de la personne."*) se lit de la meme facon. Le garde-fou est vert sur le build de cet audit
(`LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest`, 9 tests, 0 echec). **Aucun texte de
remplacement.** Prix : 0,25, preleve sur la marge de l'instance B (arbitrage, section 6).

#### 4. Les trois corrections de mon arbitrage prealable, lues a l'ecran

- **Item 4** : *"un jeu de cartes"* ; *"Aya, parce qu'elle utilise un fauteuil roulant"* ne dit rien de ce
  qu'elle peut faire ; aucune lecture de vitesse ni d'accessibilite n'est possible. `EMC-4` et `EMC-5`
  tenus.
- **Item 7** : l'indice sur deux amis qui n'aiment pas les memes livres est un exemple a relier, il ne
  reprend pas la reponse.
- **Item 8** : *"parce qu'on risque d'être puni si un adulte le voit"* est presse par un enfant qui
  raisonne par la sanction, et *"si un adulte le voit"* le rend faux. L'item n'est plus un choix a deux.

#### 5. La reserve `EMC-CM1-R8` : indices des items 10 et 11

**Constat.** Apres une erreur, l'indice de l'item 10 (*"Cherche le motif qui parle du jeu."*) designe la
seule proposition qui contient le mot *"jeu"* (*"le jeu se joue à deux et les deux places sont prises"*) ;
celui de l'item 11 (*"Cherche le motif qui parle de ce qui s'est déjà passé."*) designe la seule qui
contient *"déjà"* (*"il a déjà été choisi la veille"*). Lu a l'ecran : au second essai, l'enfant trouve
par le mot, sans savoir qu'un motif valable vient de la situation. C'est la regle de s53 section 4.1,
que j'ai etendue a ce paquet dans mon arbitrage de s55 (section 1.2) avant le branchement : **ce n'est pas
une barre nouvelle.** L'indice de l'item 9, lui, nomme le critere sans le mot de la reponse.

**Correction de mon propre constat.** Mon arbitrage prealable (section 2) annoncait une lecture item par
item et ne relevait que les items 4, 7 et 8. Je n'avais pas lu les indices des items 10 et 11 contre cette
regle ; `lea-dev` les a releves (`E5`). Ce n'est pas un motif de refus : les deux bonnes reponses sont
justes, le defaut ne se voit qu'apres une erreur, et il ne fait rien apprendre de faux.

**Ce qui leve la reserve** : les deux indices suivants, mot pour mot, rien d'autre ne change dans le
paquet (enonces, propositions, en-tete).

```yaml
  - enonce: "Dans la cour, un jeu commence. Lequel de ces motifs peut justifier de ne pas laisser un élève y entrer ?"
    bonneReponse: "le jeu se joue à deux et les deux places sont prises"
    distracteurs: ["il a des croyances différentes de celles des autres", "il porte des lunettes épaisses"]
    indice: "Un motif valable ne parle pas de l'élève lui-même."
  - enonce: "On choisit des élèves pour ranger le matériel. Lequel de ces motifs peut justifier de ne pas choisir un élève ?"
    bonneReponse: "il a déjà été choisi la veille"
    distracteurs: ["sa façon de parler est différente de celle des autres", "il est plus petit que les autres"]
    indice: "Un motif valable pourrait concerner n'importe quel élève."
```

Les enonces gardent l'espace insecable devant `?` (`TYP-1`) ; les indices n'en portent aucun. Aucun mot
des deux indices n'est dans la bonne reponse de son item. **Prix du rejeu : 0,25** (`PAR-10`, mot pour
mot) ; 0,5 si un autre texte est propose, juge alors contre la barre : un indice qui nomme le critere
(situation contre personne) sans contenir un mot propre a la bonne reponse.

**Ce que je ne retiens pas** (`E5`, troisieme indice) : l'indice de l'item 6 (*"Respecter ne veut pas dire
devenir semblable."*) ecarte un distracteur, mais il reste deux propositions qu'on ne departage qu'en
connaissant la notion. Le critere de s53 (section 4.3) est *"au point qu'on choisit sans connaitre la
notion"* ; l'item 6 ne l'atteint pas. Garde tel quel.

#### 6. Conditions, verifiees a l'ecran

| Condition | Tenue | Ce que j'ai verifie |
|---|---|---|
| `EMC-4` | **oui** | Chaque difference est le motif d'un traitement d'un personnage nomme ; *"plus petit que les autres"* et *"lunettes épaisses"* sont des motifs, aucune capacite n'est attribuee |
| `EMC-5` | **oui** | Une seule reponse defendable par item ; aux items 1 a 5, les deux distracteurs sont de vraies raisons d'organisation |
| `EMC-1`, `EMC-8` | **oui** | Troisieme personne partout ; aucun item ne demande ce que l'enfant ferait |
| `EMC-6`, `BIA-1`, `BIA-2` | **oui** | Aucun pays, aucune religion, aucun handicap particulier hors du fauteuil. Victimes : Hugo, Lina, Sam, Aya, Tom ; aucun partage de genre ni d'origine supposee entre ceux qu'on ecarte a tort et ceux qu'on ecarte a bon droit |
| `CAT-1`, `PLA-1` | **oui** | Meme forme *"Prenom, parce que ..."* aux items 1 a 5 ; distracteurs des items 6 a 8 qui sont des malentendus reels |
| `FER-1` a `FER-3`, `DEC-0064` | **oui** | Quatre termes en bonne reponse, trois formes, 11 items pour 10 manches |
| Regle de s53 4.1 | **non, items 10 et 11** | Reserve `EMC-CM1-R8` |

#### 7. Exactitude, niveau, ton

- **Exactitude** (`HIS-3`) : seul fait affirme, l'item 8, *"chaque personne a la même valeur et les mêmes
  droits"*. L'article 1er de la Declaration universelle des droits de l'homme de 1948 dit *"Tous les êtres
  humains naissent libres et égaux en dignité et en droits"* ; l'article 1er de 1789 dit *"égaux en
  droits"*. La *"même valeur"* est l'egale dignite : **source juste** (point 5 du message de cette
  session), verifiee contre le texte que je connais, sans source ouverte a cette session.
- **Niveau** : la formule *"le respect d'autrui n'est-il pas respecté"*, cinq fois, se lit lourdement ; a
  l'ecran, c'est la meme question cinq fois, et la negation est portee par le cadre que l'enfant a lu a la
  premiere manche. Je n'en fais pas une reserve. *"autrui"* est le mot du programme.
- **Ton** : sobre, aucune morale adressee a l'enfant.

#### 8. `CYC-1`

- Item 6 et item 10 de `cm1-emc-principes-republique` : meme reponse a un mot pres et un distracteur commun
  (*"éviter de parler aux personnes différentes de soi"*). Joues l'un apres l'autre, les enonces different
  (respecter les differences ; refuser les discriminations) : c'est la meme idee reprise, coherent, pas un
  doublon dans un meme jeu.
- `cp-emc-regle-souhait-loi` : *"une règle qui vaut pour tous"* au CP ; ici, la loi qui protege chacun de
  la meme facon (indice de l'item 8). Coherent.

#### 9. Titre (point 4 du message de cette session)

*"Ce que le respect interdit"* : **accepte.** Sans niveau, ne nomme ni un groupe ni une religion (barre de
mon arbitrage, section 3.4), dit la forme de l'interdit que la matrice exige, lisible par un enfant de CM1.
Il ne se confond pas, sur l'ecran du CM1, avec *"Ce qui blesse les autres"*.

#### 10. Ce dont je reste la seule garde

`PaquetCm1EmcRespectDifferencesTest` tient la forme de `FER-1` a `FER-3`, `EMC-1`/`EMC-8` (aucun pronom de
deuxieme personne), la forme de la proposition du fauteuil roulant, et le retour des textes que j'ai
refuses. **Aucun automate ne verifie** `EMC-4` hors de la proposition du fauteuil, `EMC-5`, `BIA-1` et
`BIA-2` sur la repartition des prenoms, ni la regle de s53 4.1 (un indice qui contient un mot propre a la
bonne reponse) : verifies ici en jouant, et c'est ainsi que la reserve a ete trouvee.

#### 11. Tarif et identifiants

Tarif reel : **1,0** (premier passage) **+ 0,25** (ecart de l'item 4, section 3) = **1,25**. Un identifiant
neuf, `EMC-CM1-R8`, alloue dans `squad/decisions/REGISTRE-identifiants.md` dans le meme commit.

---

### Rejeu du 2026-10-11 (`nadia-prof`, s56) : `EMC-CM1-R8` **LEVEE**

**`EMC-CM1-03` reste `couvert-valide`**, avec sa mention de couverture ; aucun mouvement de matrice. 0,25 (`PAR-10`, mot
pour mot).

**Diff** (`1d1d3ae6`) : deux lignes `indice`, items 10 et 11, mes textes de la section 5 a la lettre ; enonces,
propositions et en-tete inchanges. **Joue** (worktree `s56-nadia-art` a `a6ca1751`, jar et arret comme
`FICHE-bandit-manchot-rapprocher-deux-oeuvres-cp.md`, section 1) : par les routes servies, 3 parties, erreur sur chacun
des deux distracteurs des items 10 et 11 ; au navigateur a 744 x 551, une partie, les deux items joues avec deux
erreurs. Apres chaque erreur : item 10, *"Un motif valable ne parle pas de l'élève lui-même."* ; item 11, *"Un motif
valable pourrait concerner n'importe quel élève."* Ni l'un ni l'autre ne contient un mot propre a la bonne reponse
(*jeu*, *déjà*) ; chacun donne le critere qui ecarte les deux distracteurs (croyances, lunettes ; facon de parler,
taille : tous parlent de l'eleve lui-meme). Sur ces items longs, la page defile pour montrer l'indice sous le bouton
presse ; l'indice est lu a l'ecran.

**Signe** : `nadia-prof`, s56, 2026-10-11.
