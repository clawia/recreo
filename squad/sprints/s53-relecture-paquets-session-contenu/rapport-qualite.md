# Rapport qualite de cloture - s53

**Auteur** : `yanis-qa`. **Date** : 2026-10-07. **Arbre mesure** : `round-two` a `54953faa` (hash complet
`54953faa414110a6e9fc8294019309d393fdf746`), arbre fusionne des corrections de s53, mesure dans le worktree de
la branche `s53-yanis-qualite`. `git status --porcelain` vide apres les builds.

**Perimetre** : les quatre jobs de `.github/workflows/ci.yml`, relu avant la mesure, puis la commande
"rouge puis verte" de chacune des stories `S53-REL-1` a `S53-REL-9` et de `S53-REL-10` (base rouge). Ce rapport
porte la porte **technique** uniquement. Il ne prononce aucun Done pedagogique : aucun paquet n'est joue ni
branche, et le Done pedagogique est a `nadia-prof` seule.

Deux commandes du workflow sont adaptees sur ce poste, sans effet sur le verdict : `mvn -B verify` est lance par
l'enveloppe `outils/mvn-jdk21.sh -B clean verify` (JDK de la toolchain, `clean` en plus) ; dans le job `outils`,
la sortie standard des juges Python n'a pas ete conservee, seul leur code de sortie l'a ete.

## 1. Les quatre jobs, sur `54953faa`

### `prefixes` : vert

- `bash .github/scripts/verifier-prefixes-test.sh` : `Tous les cas passent`, code 0.
- `bash .github/scripts/commit-atteignable-test.sh` : `Tous les cas passent`, code 0.
- `git log --no-merges --format=%s origin/round-two..HEAD | bash .github/scripts/verifier-prefixes.sh` : `Tous les
  commits portent un prefixe d'agent connu.`, code 0. La plage compte 21 sujets hors fusions (31 commits, 10
  fusions). `origin/round-two` est `76a50e8b` sur ce poste. Ce n'est pas la plage `github.event.before..GITHUB_SHA`
  d'un push reel : la branche `BASE` du workflow n'est pas exercee, seulement ses deux scripts et le balayage
  de la plage ci-dessus (`commit-atteignable.sh origin/round-two` : code 0).

### `chemins` : vert

- `bash .github/scripts/verifier-chemins-test.sh` : `Tous les cas passent`, code 0.
- `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' | bash .github/scripts/verifier-chemins.sh` :
  `Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies.`, code 0. Mesure faite avant
  l'ajout de ce rapport ; le rapport lui-meme n'est verifie qu'apres son commit (section 6).

### `outils` : vert

Boucle du workflow rejouee a l'identique, trois motifs : `outils/*-test.sh` 5 juges, `squad/outils/*/*-test.sh`
2 juges, `outils/*-test.py` 2 juges. **9 juges purs executes, 9 codes de sortie 0**, aucun motif vide. Sur ce
poste `python3` repond (3.14.0) : la boucle a choisi `python3`, comme le ferait la CI. La lecture de testabilite
donnait `python3` pour un faux binaire sur ce poste ; il repond maintenant, je n'ai pas cherche pourquoi.

### `build` : vert

Commande : `bash outils/mvn-jdk21.sh -B clean verify`, racine du worktree, reacteur complet, sans `-pl`.
Resultat : `BUILD SUCCESS`, 6 min 18 s, code de sortie 0, les six modules `SUCCESS`.

**JDK** : l'enveloppe rend `Maven lance sur le JDK de la toolchain jdk 21 : 21.0.10`, et la machine virtuelle des
tests rapporte `Started ... using Java 21.0.10` (20 occurrences dans le journal, une seule valeur).

Le second pas du workflow (`mvn -B -pl bootstrap -am package`, fat jar) n'a pas ete lance : `verify` produit le
jar et `JarRecreoIT` l'exerce, mais le pas lui-meme n'est pas rejoue ici.

## 2. Compte de tests et ecart avec la base

Methode : lignes `Tests run: N, Failures: F, Errors: E, Skipped: S` de fin de module de surefire dans la sortie du
`clean verify` ci-dessus, recoupees par la somme de l'attribut `tests` des rapports XML de surefire et de failsafe
sous chaque `target/`. Les deux concordent.

| Module | surefire | failsafe | echecs | erreurs | ignores |
|---|---|---|---|---|---|
| domain | 120 | - | 0 | 0 | 0 |
| application | 17 | - | 0 | 0 | 0 |
| adapter-web | 401 | - | 0 | 0 | 0 |
| adapter-content | 1318 | - | 0 | 0 | 0 |
| bootstrap | 312 | 6 | 0 | 0 | 0 |

**2174 tests au total (2168 unitaires et ArchUnit, 6 d'integration), 0 echec, 0 erreur, 0 ignore.**

**Ecart avec la base** (`lecture-testabilite.md`, `5c1353a9`) : 2168 executes dont 5 echecs. Ce total est celui d'une
construction arretee a `bootstrap` : failsafe n'avait pas tourne. A perimetre egal (surefire seul), **2168 contre
2168, delta 0 test, et 5 echecs deviennent 0**. Chaque module a le meme nombre de tests qu'a la base
(120, 17, 401, 1318, 312). Origine : les corrections de s53 editent des fichiers de `contenu/` et une seule ligne de
test, et aucun test n'est ajoute ni retire. `git diff --name-only 5c1353a9 HEAD -- '*/src/test/*'` rend un seul
fichier, `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.java`, dont le diff est le seul texte d'un motif
(`35 propositions` devient `36`, la parenthese sur le distracteur unique disparait) : aucune assertion n'est
touchee, le motif est du texte. Les 6 tests failsafe ne sont pas dans la base, je ne les compare pas. Le meme total de 2174 que le
rapport de s52 (arbre `40daa419` plus un test) ne vient pas d'une decomposition faite ici : je cite l'egalite, pas
son origine. Delta annonce : **0 sur la base `5c1353a9`**. `sofia-sm` etablit le total sur l'arbre fusionne.

## 3. Les cinq echecs de la base : rouge puis vert (`S53-REL-10`)

Commande, lancee deux fois, une fois par arbre :
`bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content,bootstrap -am test -Dtest=AucunEnonceDeCe1NeDepasseVingtMotsNiTroisPhrasesTest,AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest,LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest -Dsurefire.failIfNoSpecifiedTests=false`

- **Sur `5c1353a9`** (worktree detache, retire apres la mesure) : `BUILD FAILURE`, code 1, `Tests run: 24, Failures: 5`.
  `AucunEnonceDeCe1...` 8 tests, 2 echecs (`LON-CE1-2`, ADR-0067 et ADR-0101) ; `AucunEnonceDeCp...` 7 tests,
  2 echecs (`LON-2` a plafond 0 ; ADR-0089 15 mots et 2 phrases) ; `LaFormeDesPropositions...` 9 tests, 1 echec
  (ADR-0086). Ce sont les cinq echecs de ma lecture de testabilite, les memes classes.
- **Sur `54953faa`** : `BUILD SUCCESS`, code 0, 8 + 7 + 9 = `Tests run: 24, Failures: 0`.

Aucun garde-fou n'a ete assoupli : le seul fichier de test touche entre les deux arbres est celui du motif de la
section 2, et il ne contient pas ces trois classes. **Critere tenu** : la suite redevient verte par correction du
contenu, sans exemption.

## 4. Neuf stories : rouge puis vert

Methode : chaque commande est rejouee sur la base (`git show 5c1353a9:<fichier>`) puis sur `HEAD`, depuis
`adapters/adapter-content/src/main/resources/contenu/`, fins de ligne retirees. "Tenu" dit que le critere
**automatisable** de la story est tenu ; ce qu'aucun automate ne juge est dit ligne par ligne. Les decisions lues
sont `squad/conformite/ARBITRAGE-PREALABLE-s53-points-de-relecture-p1-a-p11.md` et `DEC-0112`.

### `S53-REL-1` (P1, `ce1-accord-groupe-nominal`) : tenu

- `grep -c 'bonneReponse: "des beaux chapeaux"'` : base **1**, `HEAD` **0**.
- Forme retenue par `nadia-prof` (`les beaux chapeaux`) : `grep -c 'bonneReponse: "les beaux chapeaux"'` rend **1**.
  Distracteurs de l'item : `["les beaux chapeau", "les beaux chapeaus"]`, aucun egal a la bonne reponse, et
  `AucunePropositionNEstEgaleAUneAutreDansUnItemTest` est vert dans la section 2.
- Non juge : que la forme soit pedagogiquement la bonne et que les distracteurs soient bien choisis.

### `S53-REL-2` (P2, `ce1-pronoms`) : partiel, par nature

Aucune commande rouge n'existe (critere non automatisable, confirme dans ma lecture de testabilite). Verifie par
machine : le diff base a `HEAD` du fichier est de 8 lignes et ne touche que les items 10, 13 et 14 (textes
`un gâteau`, `un livre`, `leurs affaires`) ; l'indice des items 10 et 13 est identique a celui de l'arbitrage
(`Elle désigne un mot féminin : cherche-le dans la phrase d'avant.`, 10 mots) ; les 20 indices comptent au plus 12
mots ; l'en-tete cite dix numeros d'items "par le sens" (1, 2, 4, 5, 7, 8, 11, 12, 15, 20), tous entre 1 et 20.
**Non verifie** : que cette classification soit celle qu'on obtient en relisant item par item, et que chaque item
ait un seul candidat du genre et du nombre du pronom. C'est une lecture de l'auteur et de `nadia-prof`.

### `S53-REL-3` (P3, P4, `cm1-emc-principes-republique`) : tenu pour ce qui se mesure

- P3, `awk` de ma lecture de testabilite (le `grep -c` du planning ne vise pas un item) : base **1**, `HEAD`
  **0**. L'item 4 a `distracteurs: ["la fraternité", "la démocratie"]`, conforme a l'arbitrage ; la phrase d'en-tete
  sur l'item 4 reste vraie sans etre editee.
- P4 : les indices 1 a 5 sont identiques a la base (le diff des items 1 a 5 ne porte que la ligne 15, le
  distracteur de P3). Les indices 6 a 11 sont reecrits : 10, 12, 11, 10, 13 et 12 mots sous la regle de comptage de
  `ADR-0089` (forme elidee = un mot), contre 12, 10, 14, 13, 16 et 8 a la base. Le texte de l'item 6 est celui de
  `propositions-marc-dev.md` (ligne 205) ; les indices 7 a 11 sont ceux de l'arbitrage, **chacun trouve une fois
  dans l'arbitrage et une fois dans le fichier** (`grep -cF`). La decision dit qu'aucune borne de douze mots ne
  s'applique au CM1 : les 13 mots de l'item 10 n'en sont donc pas un ecart.
- Non juge : que les indices reecrits ne reformulent pas la bonne reponse, et que le choix *garde/reecrit* de
  chaque item soit le bon (`nadia-prof`).

### `S53-REL-4` (P5, P10) : tenu

- P5 : `grep -c "le chien est loin" cp-qlm-vocabulaire-de-position.yaml` base **1**, `HEAD` **0**. L'item 7 est
  celui de l'arbitrage (`Le chien n'est pas près de la porte. Où est le chien ?`, bonne reponse `loin de la
  porte`). L'en-tete `FER-3` dit `contraire d'un mot de position (items 7 a 11)`.
- P10, `awk '/distracteurs: \["[^"]*"\]$/'` sur les deux fichiers : base **2 lignes** (74 et 93), `HEAD` **1 ligne**
  (vocabulaire de position, ligne 74, l'item 7 pour lequel la decision admet un distracteur). `situer-la-france`
  rend 0 : l'item 12 a trois propositions (`la France`, `Paris`).
- Motifs de `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` : `situer-la-france` compte **36 propositions** (comptage par
  script : 12 items, 36), le motif dit 36 ; `vocabulaire-de-position` compte **41**, le motif dit 41 et n'a pas
  change. Je n'ai **pas prouve par mutation** que ce motif est garde par un test : un motif faux resterait vert
  tant que l'assertion n'est pas montree (reserve de ma lecture, non levee).
- Non juge : "la reponse n'est plus dans l'enonce" au-dela de la phrase exacte.

### `S53-REL-5` (P6, `ce1-masses-contenances`) : tenu

- `grep -c "Le mot kilo veut dire mille"` : base **2**, `HEAD` **0**. Les deux items sont corriges, comme le dit
  la decision : `Cherche ce que veut dire le mot kilo dans le mot kilogramme.` (item 9, 12 mots, bonne reponse
  `1000 g`) et `Pense au mot kilogramme : que veut dire kilo ?` (item 10, 8 mots, bonne reponse `1 kg`).
  Aucun des deux indices ne contient sa bonne reponse (comparaison par script).
- Observation, hors critere : les items 1 et 2 portent l'indice `Un kilogramme, c'est mille grammes.`, que
  `marc-dev` a signale et que la decision ne demande pas de changer a s53.
- Non verifie : que le compte de 18 items ecartes de `LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest` reste
  juste autrement que par la suite verte (la section 2 est verte, aucun item n'est ajoute ni retire).

### `S53-REL-6` (P7, `cm1-hda-epoque-civilisation`) : tenu

- `grep -c "enonce:.*pont du Gard"` : base **1**, `HEAD` **0**. La mention de la ligne 15 (commentaire d'en-tete)
  reste, et la decision l'accepte comme source d'un fait que l'enfant ne lit pas.
- La commande qui a cherche d'autres noms est ecrite dans `propositions-lea-dev.md` (ligne 88) ; je l'ai rejouee :
  elle rend `Zeus`, `Louis XIV`, `Paris` et `1889`, la liste que la decision cite.
- Non juge : que la recherche soit exhaustive.

### `S53-REL-7` (P8, `ce1-texte-documentaire`) : tenu

- Balayage du planning (`grep -nwiE 'ton|ta|tes|mon|ma|mes|notre|nos|votre|vos'`, lignes de commentaire exclues) :
  base **4 lignes** (114, 120, 121, 122), `HEAD` **1 ligne** (114, `Écris ton prénom en haut.`, acceptee par la
  decision).
- Balayage etendu de la decision (avec `son|sa|ses|leur|leurs`) : `HEAD` rend les lignes 41 et 42 (`ses œufs`) et la
  ligne 114, ce que dit la decision. L'item 16 devient `Range le cahier. Ferme la trousse. Mets la chaise sous la
  table.`, bonne reponse `la chaise` ; `AucunePhraseDuTexteSupportNeSuffitAGagnerLaMajoriteTest` est dans la suite
  verte de la section 2.

### `S53-REL-8` (P9, trois paquets EMC) : tenu (non-regression)

`grep -c '^# DEGRADE'` rend **0** sur `cm1-emc-atteinte-autrui`, `cm1-emc-respect-differences` et
`cm1-emc-principes-republique`, a la base et a `HEAD`. Le diff base a `HEAD` des deux premiers est vide ; celui de
`principes-republique` ne contient pas la chaine `DEGRADE`. Limite inchangee depuis ma lecture : aucun test ne lit
ce marqueur, ce critere se juge par un `grep` que seule cette ligne lance.

### `S53-REL-9` (P11, `competence`) : tenu, avec un point non verifie

`grep -l ' competence: ".* :'` rend **13 fichiers** a la base et a `HEAD`, **la meme liste** (comparee), qui
contient les quatre fichiers du point. La decision accepte l'espace ordinaire sur treize fichiers, aucun diff n'est
attendu : aucun n'a change. **Non verifie** : que les neuf fichiers plus anciens soient ceux que la decision a en
tete, elle ne les nomme pas (elle dit "neuf plus anciens"). Je confirme le nombre, pas les noms.

### `S53-REL-10` (base rouge) : tenu

Section 3 : 5 echecs sur `5c1353a9`, 0 sur `54953faa`, meme commande.

### Criteres communs

- **C3** : `git diff --name-only 5c1353a9 HEAD | grep -c catalogue.yaml` rend **0**.
- **C4** : tenu, `clean verify` de la racine vert (section 1) ; la commande partielle de la section 3 porte `-am`.
- **C5** : un seul garde-fou touche (le motif de la section 2), texte seulement, aucune assertion affaiblie.
- **C6** : les quatre jobs sont nommes et leurs commandes citees en section 1.

## 5. Ce qui n'est pas verifie

- **Le pas `package`** du job `build` n'a pas ete lance.
- **La CI distante** : `gh run list` rendait 404 sur ce depot depuis ce poste a la lecture de testabilite, je n'ai
  pas retente. Je ne dis rien du verdict de GitHub Actions. Aucune mesure sous Linux.
- **Fins de ligne** : ce worktree est en `core.autocrlf=true`, les sources sont en CRLF, la suite est verte dans
  cette condition. L'autre sens (LF partout) n'est pas mesure.
- **Stabilite** : la suite complete a tourne une fois, les trois classes de la section 3 deux fois sur `HEAD`
  (une dans la suite, une isolee). Aucune instabilite constatee sur ces trois classes ; je ne conclus rien sur les
  autres a partir d'un seul lancement.
- **Mutation** : les cinq echecs de la section 3 sont la mise a l'epreuve des trois garde-fous sur le contenu reel
  de la base. Je n'ai retire aucun correctif au-dela de ce rejeu sur l'arbre de base, et je n'ai pas modifie de
  YAML ni de Java pour muter. Le motif des tables du garde-fou de rang (section 4, `S53-REL-4`) reste non eprouve.
- **Tracabilite** : aucune story de s53 n'a de test d'acceptation qui la nomme. Les criteres sont des commandes
  `grep`/`awk` sur le contenu, pas des tests, a l'exception de `S53-REL-10` qui est portee par trois garde-fous
  existants. Aucun test n'a ete ecrit : la consigne de cette tache interdit d'editer du Java.
- Aucun module n'est sans test (domain 120, application 17, adapter-web 401, adapter-content 1318, bootstrap 318
  dont 6 d'integration).

## 6. Verdict

- **Done technique** : les quatre jobs sont verts sur `54953faa`, tels que lances ici (`prefixes`, `chemins`,
  `outils`, `build`), a l'exception du pas `package` non lance. Le jugement de la CI distante est a la CI.
- **Verdicts par story** : `S53-REL-1` tenu ; `S53-REL-2` partiel, non automatisable ; `S53-REL-3` tenu (P4,
  reformulation non jugee) ; `S53-REL-4` tenu (motif de table non eprouve) ; `S53-REL-5` tenu ; `S53-REL-6` tenu ;
  `S53-REL-7` tenu ; `S53-REL-8` tenu (non-regression) ; `S53-REL-9` tenu (noms des neuf fichiers non verifies) ;
  `S53-REL-10` tenu.
- **Done pedagogique** : non prononce. Aucun paquet n'est joue ni branche. Rien dans ce rapport ne tient lieu de
  decision de `nadia-prof`.
