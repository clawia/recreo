# [Sprint s53] Lecture de testabilite des criteres - yanis-qa - 2026-10-07

Base : `5c1353a9` (tip de `round-two`), branche `s53-yanis-testabilite`, arbre propre au depart. Source lue en entier :
`planning.md` du dossier. Aucun YAML ni Java edite. Ce fichier ne prononce aucun Done pedagogique.

## 1. La base du sprint est rouge

**Commande** : `bash outils/mvn-jdk21.sh -B clean verify`, racine du worktree, reacteur complet, sans `-pl`.
**Resultat** : `BUILD FAILURE`, code de sortie 1, 4 min 21 s. L'enveloppe a rendu "Maven lance sur le JDK de la toolchain
jdk 21 : 21.0.10". La version que la machine virtuelle des tests rapporte n'a pas ete lue (`ADR-0071`) : la mesure est
donnee sur la toolchain 21, pas sur une version verifiee cote tests.

| Module | Tests executes | Echecs |
|---|---|---|
| `recreo-domain` | 120 | 0 |
| `recreo-application` | 17 | 0 |
| `recreo-adapter-web` | 401 | 0 |
| `recreo-adapter-content` | 1318 | 0 |
| `recreo-bootstrap` | 312 | **5** |
| **Reacteur** | **2168 executes** | **5** (2163 passent) |

Methode : somme des lignes `Tests run: N, Failures: F, Errors: E, Skipped: S` de fin de module de surefire dans la sortie
de la commande ci-dessus (une ligne par module, 0 erreur, 0 ignore). Ce total est celui d'une construction **arretee a
`recreo-bootstrap`** : le plugin d'integration (failsafe, declare dans `bootstrap/pom.xml` seul) n'a pas tourne, donc ses
tests ne sont pas dans 2168. Je ne rapproche pas ce chiffre de 2174 (`509552f0`, s52) : ce dernier etait un total de
construction complete, celui-ci ne l'est pas, et six commits de s53 separent les deux arbres.

**Les cinq echecs, tous dans `bootstrap`, paquet de la session en cause, aucun branche au catalogue** :

| Test (`com.recreo.archi`) | Cause relevee dans la sortie | Paquets nommes |
|---|---|---|
| `AucunEnonceDeCe1NeDepasseVingtMotsNiTroisPhrasesTest` (2 echecs) | `LON-CE1-2`, dette 0 : l'enonce de l'item 7 compte 2 phrases et la derniere n'est pas la question | `ce1-mots-invariables` item 7 |
| `AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest` (2 echecs) | `LON-1` (plus de 15 mots) et `LON-2` (plus de 2 phrases), dette 0 | `cp-emc-personnes-differentes` (items 1, 2, 3, 4, 6, 11), `cp-qlm-modes-de-vie` item 12, **`cp-qlm-vocabulaire-de-position` items 7, 12, 13, 14** |
| `LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest` (1 echec) | `FOP-R4`, `ADR-0086` clause 2 : 11 amorces de distracteur de 14 propositions que n'ouvre aucune bonne reponse | `cp-emc-personnes-differentes` |

Chacun des quatre paquets a ete ajoute par un commit de la session (`d27ba381`, `7854d8d3`, `34fdd167`) et
`grep -c "paquet: <nom>$" adapters/adapter-content/src/main/resources/catalogue.yaml` rend 0 pour chacun. Ces garde-fous
lisent donc `contenu/` par balayage de repertoire, pas le catalogue : **un paquet non branche est juge comme un paquet
branche**, et c'est la preuve a l'execution que les 45 paquets sont couverts par les garde-fous derives du repertoire.
Je n'ai pas relance la suite sur Linux : les echecs portent sur des comptes de mots et de phrases lisibles dans le YAML, je
n'y vois pas de cause de poste, mais je ne l'ai pas mesure. `gh run list` rend 404 sur ce depot depuis ce poste : je ne
sais pas ce que la CI distante a rendu sur `5c1353a9`.

**Consequence pour le planning (a `sofia-sm`)** :

- Le critere commun **C4** (`-pl adapters/adapter-content,bootstrap -am test` vert) est **inatteignable** pour les neuf
  stories tant que ces cinq echecs durent : aucun developpeur ne peut annoncer C4 vert sans soit les corriger, soit les
  declarer. Ils ne relevent d'aucun des onze points, sauf `cp-qlm-vocabulaire-de-position` item 7 (P5), qui a
  16 mots, et dont les items 12, 13, 14 sont aussi rouges.
- Ce rouge est avant s53, et personne ne l'a nomme au planning ni a l'objectif ("les paquets corriges repassent la suite de
  `adapter-content` et de `bootstrap` verte"). Question a router : qui paie les cinq echecs, et dans quelle story. Je ne
  decide ni la dette ni la correction.
- Un developpeur qui mesure avec `-pl ... -am` verra ces cinq rouges et doit les distinguer des siens : le critere C4
  gagne a citer la liste ci-dessus comme etat de depart.

## 2. Les quatre jobs de `.github/workflows/ci.yml`

| Job | Lance ? | Commande | Sortie |
|---|---|---|---|
| `prefixes` | etapes 1 et 2 oui, etape 3 apres commit | `bash .github/scripts/verifier-prefixes-test.sh` ; `bash .github/scripts/commit-atteignable-test.sh` | "Tous les cas passent : le garde-fou de prefixes mord, et sur le bon motif." ; "Tous les cas passent : commit-atteignable.sh distingue un commit reel d'un commit disparu." Etape 3 : voir section 4 |
| `chemins` | oui | `bash .github/scripts/verifier-chemins-test.sh` ; `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' \| bash .github/scripts/verifier-chemins.sh` | "Tous les cas passent : le garde-fou de chemins mord, et sur le bon motif." ; "Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies." |
| `outils` | oui, avec un ecart | boucle de l'etape, motifs `outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py` | 9 juges lances, 9 sorties 0 : 5 + 2 + 2, aucun motif vide. **Ecart** : le job choisit `python3` s'il existe ; sur ce poste `python3` est un faux binaire, j'ai donc passe `python` (3.14.0). Le juge est le meme, l'interpreteur n'est pas celui que choisirait la CI |
| `build` | etape `mvn -B verify` oui (rouge) ; etape `mvn -B -pl bootstrap -am package` **non lancee** | `bash outils/mvn-jdk21.sh -B clean verify` (equivalent portable de `mvn -B verify`, avec `clean`) | `BUILD FAILURE`, section 1. L'etape de package n'a pas ete lancee : elle suit un `verify` rouge dans le job |

**Porte technique a `5c1353a9`, telle que mesuree ici : rouge**, par le job `build`. Les trois autres jobs sont verts sur
cet arbre. Je ne conclus pas sur la CI distante.

## 3. Lecture de testabilite, story par story

Chaque commande a ete lancee par moi sur `5c1353a9`, depuis
`adapters/adapter-content/src/main/resources/contenu/`, pour verifier la colonne "rouge sur l'existant" du planning. Un
critere est **testable** s'il se juge par une commande et un resultat ; il est **juge a la main** s'il demande une lecture.
Aucun garde-fou existant ne porte un critere specifique ci-dessous : les garde-fous de `adapter-content` et de
`bootstrap` qui s'appliquent aux paquets edites sont listes en section 3.10 et servent de filet, pas de preuve du critere.

### 3.1 `S53-REL-1` (P1, `ce1-accord-groupe-nominal`)

- **Testable** : l'absence de l'ancienne forme. `grep -c 'bonneReponse: "des beaux chapeaux"' ce1-accord-groupe-nominal.yaml`
  rend **1** aujourd'hui, **0** attendu apres correction. Mesure faite.
- **Testable apres la decision, pas avant** : "la forme retenue y figure". La commande n'existe qu'une fois la forme ecrite
  dans la decision de `nadia-prof` : `grep -c 'bonneReponse: "<forme>"'` rend 1.
- **Filet existant** : `AucunePropositionNEstEgaleAUneAutreDansUnItemTest` rend rouge un distracteur identique a la bonne
  reponse (balayage de `contenu/`).
- **Ce qu'aucun automate ne juge** : que la forme retenue soit la bonne, et que les deux distracteurs soient
  pedagogiquement bien choisis. Le `grep` de l'ancienne forme est satisfait par n'importe quelle edition, y compris
  fausse : il prouve un changement, pas une correction.

### 3.2 `S53-REL-2` (P2, `ce1-pronoms`) - non testable

- Le critere demande que le compte de l'en-tete (lignes 19 a 21 : dix par le sens, items 1, 2, 4, 5, 7, 8, 11, 12, 15, 20 ;
  dix par l'accord) soit egal a la classification relue item par item. **La classification n'existe nulle part sous forme
  lisible par une machine** : l'en-tete est un commentaire YAML, aucun champ d'item ne porte "sens" ou "accord", aucun test
  ne lit ces lignes (`grep -rln "PRN-CE1" adapters/*/src/test bootstrap/src/test` ne rend rien).
- Ce qu'il faudrait pour le rendre testable : un champ ou un marqueur par item que le chargeur ou un garde-fou lise, ce qui
  releve de `theo-archi` et de `camille-po` (nouveau champ de contenu), pas de moi. A defaut, le critere reste une lecture de
  l'auteur, ecrite dans sa decision, et la seule verification mecanique possible est que les numeros d'items cites dans
  l'en-tete existent (borne : entre 1 et 20).
- Le planning le dit deja ("non automatisable") ; je confirme.

### 3.3 `S53-REL-3` (P3, P4, `cm1-emc-principes-republique`)

- **P3, testable avec une commande qui n'est pas celle du planning.** Le planning ecrit `grep -c '"la liberté"'` "sur cet
  item" : **un `grep -c` ne peut pas viser un item**, et sur le fichier entier il rend **4** (la liberte est distracteur ou
  reponse ailleurs), donc il ne peut jamais rendre 0. Commande qui vise l'item :
  ```
  awk '/^  - enonce:/ {if(blk ~ /bonneReponse: "la laïcité"/ && blk ~ /distracteurs:.*"la liberté"/) c++; blk=""} {blk=blk"\n"$0} END{if(blk ~ /bonneReponse: "la laïcité"/ && blk ~ /distracteurs:.*"la liberté"/) c++; print c+0}' cm1-emc-principes-republique.yaml
  ```
  Rend **1** aujourd'hui (rouge sur l'existant), 0 attendu si l'issue est de retirer "la liberté" des distracteurs. Si l'issue
  est de reecrire la phrase d'en-tete (lignes 26 et 27), la verification est une lecture : aucun automate ne compare deux
  phrases de commentaire.
- **P4, juge a la main pour "reformule la bonne reponse"** (c'est le jugement de `nadia-prof`).
- **P4, testable pour la borne de douze mots** : sur le fichier courant, les indices des items nommes comptent, en mots
  separes par une espace : ligne 88 : 16, 92 : 12, 96 : 10, 100 : 14, 108 : 16, 112 : 8 (commande :
  `awk '/indice:/ {s=$0; sub(/.*indice: "/,"",s); sub(/"$/,"",s); print FNR": "split(s,a," ")}'`). **Deux conditions** avant que
  cette borne soit un critere : `TRV-CE1-5` doit etre confirmee applicable a l'EMC CM1 par `nadia-prof` (le planning le dit), et
  la regle de comptage doit etre ecrite : mon comptage garde "d'une" et "s'arrete" chacun dans un seul mot, la ligne 92 compte
  alors 12 ; si l'apostrophe separe deux mots, elle en compte 14 et sort de la borne. Sans cette ligne, deux agents
  mesureront deux chiffres.
- **Aucun garde-fou existant ne porte la borne de douze mots pour ce paquet** (13 tests `Paquet*Test` citent `douze mots`, `TRV-CE1-5` ou `TRV-CE1-6`, chacun pour son paquet ; aucun ne nomme les paquets de s53).
- Filet : `ChaquePaquetPorteUnIndiceTest` rend rouge un item sans indice. Un indice **supprime** au lieu d'etre reecrit fait
  donc passer le rouge, ce qui est voulu.

### 3.4 `S53-REL-4` (P5, P10, `cp-qlm-vocabulaire-de-position`, `cp-qlm-situer-la-france`)

- **P5, partiel.** `grep -c "le chien est loin" cp-qlm-vocabulaire-de-position.yaml` rend **1**, 0 attendu : cela garde la
  disparition de la phrase, pas le fait que la reponse ne soit plus dans l'enonce. Un test generique "la bonne reponse
  n'apparait pas dans l'enonce" **n'est pas ecrivable en l'etat** : mesure faite par un awk de sous-chaine sur les deux
  fichiers, il rend 5 lignes (item 7, le defaut : "le chien"; mais aussi "Sam", "la boîte", "la maison" dans les items de
  rangement 12 a 14 de `vocabulaire-de-position`, et "l'Europe" dans `situer-la-france`), ou la reponse nommee dans
  l'enonce est le sujet de l'exercice. Le critere reste donc un jugement de `nadia-prof` pour tout sauf la disparition de la
  phrase exacte.
- **P10, testable, et la commande du planning marche.**
  `awk '/distracteurs: \["[^"]*"\]$/ {print FILENAME": "FNR": "$0}' cp-qlm-vocabulaire-de-position.yaml cp-qlm-situer-la-france.yaml`
  rend **2 lignes** (74 et 93). Apres la decision de `theo-archi` : 0 ligne si l'issue est "au moins deux distracteurs".
  Reserve : l'expression ne voit qu'un distracteur **sur une seule ligne** ; une liste ecrite sur plusieurs lignes
  echapperait, ce que le YAML actuel ne fait pas (mesure sur ces deux fichiers seulement).
- **Pas de garde-fou existant sur "au moins deux distracteurs".** `AuPlusTroisPropositionsParItemTest` garde la borne haute
  (3), pas la borne basse. Si `theo-archi` decide une regle, `CLAUDE.md` exige sa regle ArchUnit dans le meme geste ; avant
  cela, la seule garde est le `awk` ci-dessus, lance a la main.
- **Motif obsolete sans rouge** : `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` porte, pour ces deux paquets,
  "(certains items portent un seul distracteur, donc deux propositions)" et des comptes (41 et 35 propositions) dans le
  texte de motif de `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE`. J'ai lu les tests qui exercent cette table (cles presentes,
  contribuent des items, nom fantome : lignes 1856 a 1930) et n'y ai pas trouve d'assertion sur le contenu du motif ; **je ne
  l'ai pas prouve par mutation**. Si c'est exact, ajouter un distracteur laisse la suite verte et le motif faux, et C5 ne le
  signalera pas. A faire par le developpeur : relire ce motif dans son diff.
- **Dependance de C4** : l'item 7 (P5) est dans les cinq echecs de la section 1, de meme que les items 12, 13, 14.

### 3.5 `S53-REL-5` (P6, `ce1-masses-contenances`)

- **Testable.** `grep -c 'indice: "Le mot kilo veut dire mille."' ce1-masses-contenances.yaml` rend **2** (planning : 2,
  confirme), et le critere demande que la decision se prononce sur **les deux** items. Apres correction : 1 si l'un est garde,
  0 si les deux sont reecrits.
- Un indice reecrit : douze mots au plus (meme reserve de comptage que 3.3) et ne contient pas la bonne reponse : testable par
  awk sur `indice` contre `bonneReponse` de l'item ; cette seconde condition n'a pas de garde-fou existant dans le depot, a la
  difference de ce que le planning laisse croire en la citant comme critere.
- **Piege de comptes** : `LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest` declare `ce1-masses-contenances` a **18
  items ecartes** (ligne 139). Reecrire un indice ne change pas ce compte ; **ajouter, retirer ou changer le type d'un item
  le change**, et le rouge serait alors a la fusion. A dire au developpeur.

### 3.6 `S53-REL-6` (P7, `cm1-hda-epoque-civilisation`)

- **Testable.** `grep -c "enonce:.*pont du Gard" cm1-hda-epoque-civilisation.yaml` rend **1** (ligne 45), 0 attendu si le nom
  est retire. `grep -c "pont du Gard"` sur le fichier rend **2** : la ligne 15, un commentaire d'en-tete, nomme aussi l'ouvrage.
  Le critere du planning vise les lignes `enonce:`, il est donc juste, mais **la ligne 15 contredit la ligne 6 ("aucune oeuvre
  individuelle n'est nommee")** qu'il ne mentionne pas.
- "La decision ecrit la commande qui a cherche d'autres noms d'oeuvre" : testable comme **presence** d'une commande dans le
  fichier de decision ; que la recherche soit exhaustive est un jugement. Aucun automate ne sait ce qu'est un nom d'oeuvre.

### 3.7 `S53-REL-7` (P8, `ce1-texte-documentaire`)

- **Testable.** La commande du balayage, lancee par moi :
  `grep -nwiE 'ton|ta|tes|mon|ma|mes|notre|nos|votre|vos' ce1-texte-documentaire.yaml | grep -v '^[0-9]*:#'` rend **4 lignes** :
  114 (`texteSupport` : "Ecris ton prenom"), 120 (`texteSupport` : "ton cahier", "ta trousse", "ta chaise"), 121
  (`bonneReponse: "ta chaise"`), 122 (`distracteurs: ["ta trousse", "ton cahier"]`). **La ligne 114 n'est pas dans les deux
  exemples du point P8** : c'est une troisieme occurrence, que la decision doit dire.
- Limites de la commande : elle ne voit ni les elisions (`m'`, `t'`), ni un possessif ecrit en majuscule initiale accentuee
  hors classe ASCII, et son verdict "rend la liste de la decision, ni plus ni moins" doit se comparer **par contenu de ligne,
  pas par numero** (une edition decale les numeros).
- Garde-fou existant voisin : `AucunePhraseDuTexteSupportNeSuffitAGagnerLaMajoriteTest` (une strategie a une phrase ne gagne
  pas la majorite). Il peut changer de verdict si `texteSupport` est reecrit : a lancer, pas a deduire.

### 3.8 `S53-REL-8` (P9, trois paquets EMC) - testable pour la forme, pas pour l'equivalence

- **Le juge est un `grep`, aucun test ne lit le marqueur.** `grep -rn DEGRADE adapters/adapter-content/src/test/java` rend
  rien ; je n'ai trouve aucun `.java`, `.sh`, `.py` ni `.yml` du depot qui lise `DEGRADE` (`grep -rn DEGRADE --include=*.java
  --include=*.sh --include=*.py --include=*.yml .` : rien). Le marqueur est un commentaire d'en-tete, et **25 fichiers de
  `contenu/` le portent** (`grep -l '^# DEGRADE' *.yaml | wc -l`).
- Etat : `grep -c '^# DEGRADE'` rend **0** sur chacun des trois paquets (`cm1-emc-atteinte-autrui`,
  `cm1-emc-respect-differences`, `cm1-emc-principes-republique`), 1 attendu si la `DEC` dit "non equivalent".
- **Ce qu'une suite verte ne prouve pas ici** : ajouter ou omettre la ligne laisse `mvn verify` identique. Un critere qui
  se juge par un `grep` que personne ne lance n'est pas gardien. Si la `DEC` veut un garde-fou, il faut un test qui lise le
  marqueur ; je ne l'ecris pas (pas de Java, pas de decision produit).
- Que la mention exigee d'avance equivaille a `DEGRADE` : decision de `camille-po`, hors de tout automate.

### 3.9 `S53-REL-9` (P11, `competence`)

- **Testable, et le chiffre du planning est reproduit.** `grep -l ' competence: ".* :' *.yaml | wc -l` rend **13** sur 182
  fichiers. Le motif exige une espace avant `competence`, donc une cle indentee : mesure sans cette espace
  (`'^competence: ".* :'`) : 0 ; avec `^\s*` : 13, memes fichiers.
- **Ecart "9 contre 11" : la cause "espace insecable" n'est pas ecartee.** Correction de s54 (A10 de la retro de s53). Cette
  ligne disait que la recherche d'octets `grep -lP '\xC2\xA0'` rend "0 fichier sur 182", donc qu'aucun fichier n'en porte, et que
  `grep -P '\x{00A0}'` rendrait 4 fichiers par fausse concordance avec l'octet `A0` de "à". **Les deux affirmations sont fausses
  sur le poste de s54** (locale `en_US.UTF-8`, `grep -P` actif) : en mode UTF-8, `\xC2\xA0` designe les deux caracteres U+00C2
  et U+00A0, pas les deux octets de l'espace insecable, et ne trouve rien. Le "0" etait un artefact de la commande, pas une
  propriete des fichiers. Mesures de s54 sur les 182 fichiers de `contenu/` : `grep -lP '\x{00A0}'` rend **161**,
  `grep -l $'\xc2\xa0'` rend **161**, une recherche d'octets Python (`b"\xc2\xa0" in fichier`) rend **161**. L'espace insecable
  figure donc dans 161 fichiers sur 182. Sur le seul champ `competence`, une espace insecable avant ":" figure dans **4**
  fichiers (`ce1-accord-groupe-nominal`, `ce1-classes-de-mots`, `ce1-inference`, `ce1-texte-narratif`, le nombre de `DEC-0112`
  amendement A.1) et une espace ordinaire avant ":" dans 13 autres (intersection vide). Je n'ai pas reproduit le "4" que cette
  ligne donnait pour `\x{00A0}` : la meme commande rend 161 ici, et la locale de la mesure de s53 n'est pas connue. L'ecart de
  2 entre 9 et 11 reste sans explication : je n'ai pas relu `relecture-p11.md` section 3 pour le recompter.
- Apres une convention "espace insecable" : la recherche d'octets (`grep -lP '\x{00A0}'`, 161 fichiers aujourd'hui) doit rendre
  exactement les fichiers declares ; sur le seul champ `competence`, `grep -lP '^\s*competence: ".*\x{00A0}:'` rend 4 fichiers
  aujourd'hui. Apres la meme convention, l'espace ordinaire doit rendre 0. Apres une convention "espace ordinaire" : aucun diff.
- **Aucun garde-fou existant ne lit `competence` pour sa typographie** (les tests qui citent `competence` la lisent comme
  valeur, ils ne verifient pas l'espace). Le planning note que ce champ n'est affiche par aucun gabarit : si c'est exact, le
  critere n'a aucun effet visible a tester en rendu.
- Le decideur n'est pas designe (Q4) : le critere ne peut pas etre lance avant C1.

### 3.10 Garde-fous de `adapter-content` qui s'appliquent aux paquets edites (filet, pas preuve)

Tous derivent leur perimetre de `contenu/` par balayage de repertoire, donc couvrent les paquets non branches (verifie par
les echecs de `bootstrap` en section 1 pour les trois de `bootstrap` ; pour ceux d'`adapter-content`, le fait qu'ils soient
verts sur les 45 paquets est mesure, leur lecture du repertoire est lue dans leurs Javadoc et non re-prouvee) :

- `AucunePropositionNEstEgaleAUneAutreDansUnItemTest` : P1.
- `AuPlusTroisPropositionsParItemTest` : P10 (borne haute 3).
- `ChaquePaquetPorteUnIndiceTest` : P4, P6 (un indice supprime fait rouge).
- `AucunePhraseDuTexteSupportNeSuffitAGagnerLaMajoriteTest` : P8.
- `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`, `LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest` : tables
  declaratives en dur (voir 3.4 et 3.5).
- Dans `bootstrap` : `AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest`, `AucunEnonceDeCe1NeDepasseVingtMotsNiTroisPhrasesTest`,
  `LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest`. Rouges a la base.

## 4. Verdict par story

| Story | Critere specifique | Juge | Rouge sur l'existant, mesure |
|---|---|---|---|
| `S53-REL-1` | oui, pour l'absence de l'ancienne forme | `grep -c` | oui (1) |
| `S53-REL-2` | non | lecture de l'auteur ; il faudrait un marqueur lisible par machine | - |
| `S53-REL-3` | P3 oui avec l'`awk` ci-dessus (pas le `grep -c` du planning) ; P4 douze mots oui sous deux conditions, le reste non | `awk` | P3 oui (1) ; P4 : 3 indices sur 6 au-dessus de 12 mots (lignes 88, 100, 108), selon mon comptage |
| `S53-REL-4` | P10 oui ; P5 pour la phrase exacte seulement | `awk`, `grep -c` | oui (2 lignes ; 1) |
| `S53-REL-5` | oui | `grep -c` | oui (2) |
| `S53-REL-6` | oui | `grep -c` | oui (1) |
| `S53-REL-7` | oui, la commande rend 4 lignes dont une hors du point | `grep -nwiE` | oui (4 lignes) |
| `S53-REL-8` | forme oui, equivalence non ; aucun test ne lit le marqueur | `grep -c '^# DEGRADE'` | oui si "non equivalent" (0) |
| `S53-REL-9` | oui | `grep -l`, recherche d'octets | oui (13) |

**Ce qu'un automate ne peut pas juger** (a confirmer a `sofia-sm`, qui l'a demande) : `S53-REL-2` en entier, `S53-REL-3`
P4 (reformulation de la bonne reponse), `S53-REL-4` P5 au-dela de la phrase exacte, la justesse pedagogique de la forme
retenue a `S53-REL-1`, l'equivalence de `S53-REL-8`, la completude de la recherche d'oeuvres de `S53-REL-6`. Ce sont des
lectures de `nadia-prof` ou de l'auteur, a l'audit futur.

## 5. Ecarts a corriger dans le planning, sans les corriger moi

1. **C4 inatteignable** a la base (section 1) : cinq echecs de `bootstrap`, listes avec leurs paquets.
2. **`S53-REL-3` P3** : le `grep -c '"la liberté"' ... sur cet item` ne vise pas un item ; il rend 4 sur le fichier.
3. **`S53-REL-5`** : le planning cite "ne contient pas la bonne reponse" comme critere ; aucun garde-fou ne le porte.
4. **`S53-REL-6`** : la ligne 15 du paquet nomme aussi le pont du Gard.
5. **`S53-REL-7`** : la ligne 114 est une troisieme occurrence hors des deux exemples du point.
6. **`S53-REL-9`** : la commande qui annoncait "0 fichier sur 182" ne cherchait pas l'espace insecable : corrige en section 3.9 (161 fichiers sur 182 en portent, 4 en `competence` avant ":").
7. **C6** : le job `outils` choisit `python3` ; un poste ou `python3` est un faux binaire ne peut pas le lancer tel quel.

## 6. Ce que cette lecture n'a pas fait

- Aucun garde-fou n'est cite comme fermant un refus dans ces neuf stories, je n'ai donc pas lance de mutation. Je n'ai pas
  retire de correctif pour voir le rouge ; chaque colonne "rouge sur l'existant" ci-dessus est un resultat de commande
  sur l'arbre non modifie, pas la preuve qu'un futur correctif fermera un critere.
- Pas de relance de la suite pour juger de la stabilite des cinq echecs : ils se lisent dans le YAML, et je ne les ai pas
  rejoues deux fois.
- Je n'ai pas lance l'etape de package du job `build`.
- Aucun Done n'est prononce. Le Done technique est celui de la CI, tous les jobs ; le Done pedagogique est a `nadia-prof`.
