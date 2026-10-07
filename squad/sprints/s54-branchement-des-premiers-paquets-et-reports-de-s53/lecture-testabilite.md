# [Sprint s54] Lecture de testabilite des criteres - yanis-qa - 2026-10-07

Base : `dc2870eb` (tip de `round-two`, fusion de l'avis de faisabilite), branche `s54-yanis-qualite`, arbre propre au depart
(`git status --short` vide). Sources lues en entier : `planning.md` (criteres C1 a C7 et tableau "Specifiques"),
`avis-faisabilite.md`, `DEC-0114` (statut `Proposee`), la section "Sprint 54" du backlog et les sous-sections
`S51-SCI-CORPS-CM1-RESERVES`, `S51-OUTIL-JOURNAL-TOKENS`, `S53-PASSE-TRANSVERSE`. Aucun YAML ni Java edite. Ce fichier ne
prononce aucun Done pedagogique.

## 1. La base du sprint est verte

**Commande** : `bash outils/mvn-jdk21.sh -B clean verify`, racine du worktree, reacteur complet, sans `-pl`.
**Resultat** : `BUILD SUCCESS`, code de sortie 0, 5 min 30 s (21:12:52 a 21:18:31). L'enveloppe a rendu "Maven lance sur le JDK de
la toolchain jdk 21 : 21.0.10". La version que la machine virtuelle des tests rapporte est lue dans les rapports XML : propriete
`java.version` = **21.0.10** dans les cinq modules (`ADR-0071`).

| Module | Surefire | Failsafe | Echecs, erreurs, ignores |
|---|---|---|---|
| `recreo-domain` | 120 | - | 0 |
| `recreo-application` | 17 | - | 0 |
| `recreo-adapter-web` | 401 | - | 0 |
| `recreo-adapter-content` | 1318 | - | 0 |
| `recreo-bootstrap` | 312 | 6 | 0 |
| **Reacteur** | **2168** | **6** | **0** |

**Total : 2174 tests** (2168 + 6). Methode : somme des attributs `tests`, `failures`, `errors`, `skipped` de l'element
`testsuite` de chaque `TEST-*.xml` sous `target/surefire-reports` et `target/failsafe-reports`, par module, apres la
construction ci-dessus (11, 2, 48, 163, 50 fichiers Surefire, 4 fichiers Failsafe), recoupee avec les lignes `Tests run:` de fin de
module de la sortie Maven (120, 17, 401, 1318, 312, puis 6). Les 6 tests Failsafe vivent dans `bootstrap` seul : une mesure qui
s'arrete avant le plugin d'integration rend 2168, pas 2174 (c'est le chiffre rapporte a tort comme "total" a la base de s53).
**Delta par rapport au dernier total connu (2174, cloture de s53, remesure par `theo-archi` a `4bb4dbd5`) : 0.** Aucun commit de
code n'a ete fusionne depuis ; ce 0 n'est pas une preuve que les futurs branchements n'ajoutent rien (`theo-archi` mesure que
l'entree d'un paquet au catalogue ne cree aucun test, seul le test du paquet en ajoute). **Je ne cite pas ce total comme un
total de s54** : `sofia-sm` l'etablit sur l'arbre fusionne.

Contraste avec s53 : C4 est atteignable, la base ne porte aucun des cinq rouges de la lecture de s53 (`S53-REL-10` les a soldes).

### Les quatre jobs de `.github/workflows/ci.yml`

| Job | Lance ? | Commande | Sortie |
|---|---|---|---|
| `prefixes` | etapes 1 et 2 : oui. Etape 3 (`verifier-prefixes.sh` sur la plage) : **apres le commit de ce fichier**, resultat dans le rapport de la tache | `bash .github/scripts/verifier-prefixes-test.sh` ; `bash .github/scripts/commit-atteignable-test.sh` | "Tous les cas passent : le garde-fou de prefixes mord, et sur le bon motif." ; "Tous les cas passent : commit-atteignable.sh distingue un commit reel d'un commit disparu." |
| `chemins` | oui, les deux etapes | `bash .github/scripts/verifier-chemins-test.sh` ; `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' \| bash .github/scripts/verifier-chemins.sh` | "Tous les cas passent : le garde-fou de chemins mord, et sur le bon motif." ; "Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies." (la deuxieme relancee sur les fichiers de ce commit avant de le commiter) |
| `outils` | oui, avec un ecart | boucle de l'etape, motifs `outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py`, interpreteur `python` | 9 juges lances (5 + 2 + 2), 9 sorties 0, aucun motif vide. **Ecart** : le job prend `python3` s'il existe ; sur ce poste `python3` est un faux binaire, j'ai passe `python` (3.14.0). Le juge est le meme, l'interpreteur n'est pas celui de la CI |
| `build` | etape `mvn -B verify` : oui, via l'equivalent portable `clean verify` ci-dessus. Etape `mvn -B -pl bootstrap -am package` : **non lancee** | voir ci-dessus | `BUILD SUCCESS`. L'etape de package n'a pas ete lancee ; je ne la deduis pas |

**Porte technique a `dc2870eb`, telle que mesuree ici : verte sur les trois jobs lances en entier et sur l'etape `verify` du
job `build`.** L'etape de package du job `build` n'est pas mesuree. Je ne conclus pas sur la CI distante (`gh run list` rend 404
depuis ce poste, question `Q9` du planning).

## 2. Lecture de testabilite, critere par critere

Un critere est **testable** s'il se juge par une commande et un resultat attendu ; **juge a la main** s'il demande une lecture.
"Rouge aujourd'hui" = la commande lancee par moi sur `dc2870eb` rend le contraire du resultat attendu. Aucun garde-fou existant
ne porte un critere specifique de s54 : les garde-fous generiques qui s'appliquent sont au 2.9, ils servent de filet.

### 2.1 Criteres communs C1 a C7

| Critere | Testable | Commande exacte | Aujourd'hui |
|---|---|---|---|
| C1 decision d'abord | oui, existence | `git show round-two:squad/decisions/produit/DEC-0114-ouverture-de-s54-cinq-branchements-trois-postes-de-s53-re-engages-seconds-reports-tranches-deux-instances-de-nadia-prof-a-4-0-art-cp-10-au-prix-seul.md \| head -1` | vert : le fichier existe sur `round-two`. **Il prouve l'existence, pas l'acceptation** : la `DEC` est `Proposee` (section 0.4). Un amendement du planning pour une story ajoutee en cours de sprint est a tester de la meme facon, fichier a nommer |
| C2 diff borne | oui, par liste blanche | `git diff --name-only round-two...HEAD \| grep -vE '/(catalogue\.yaml\|TexteAccentueExposeParLePortEntrantTest\.java\|Paquet[A-Za-z0-9]+Test\.java)$'` doit rendre vide, hors fichiers de rapport de la story | vide aujourd'hui (HEAD egale `round-two`) : **vide par construction, pas rouge**. Ecart a la source : C2 liste aussi "les declarations des garde-fous", `avis-faisabilite.md` T2 mesure qu'aucune n'est a modifier (seul l'inventaire d'accent bouge). La liste blanche ci-dessus suit l'avis ; si un developpeur doit toucher une declaration, c'est un ecart a nommer, pas un cas prevu. Le rapport du developpeur (un `.md` de `squad/`) sort aussi de la liste blanche : a dire |
| C3 une entree par paquet | oui | `grep -c "paquet: <nom>$" adapters/adapter-content/src/main/resources/catalogue.yaml` ; pour les 40 autres : `git diff round-two -U0 -- adapters/adapter-content/src/main/resources/catalogue.yaml \| grep -c '^+ *paquet:'` | **rouge, mesure** : 0, 0, 0, 0, 0 pour `ce1-futur`, `ce1-passe-compose`, `cm1-sciences-terre-jour-nuit-saisons`, `cm1-sciences-environnement-activite-humaine`, `cm1-emc-atteinte-autrui`. A la cloture, la seconde commande rend 5 exactement, pas "ne nomme pas les 40" qui n'a pas de commande |
| C4 suite verte | oui | mesure partielle `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content,bootstrap -am test` ; porte `bash outils/mvn-jdk21.sh -B clean verify` | vert (section 1). La mesure partielle ne couvre pas les 6 tests Failsafe ni les trois autres modules en test aval |
| C5 aucun garde-fou assoupli | **en partie** | `git diff round-two -U0 -- '*Test.java' \| grep '^-[^-]'` liste chaque ligne retiree | liste vide aujourd'hui. L'automate fournit la liste ; que chaque ligne retiree soit un resserrement ou un deplacement est une lecture (voir 2.6 : `26 -> 12` et `516 -> 502` sont des resserrements, une constante qui monte serait un assouplissement) |
| C6 jobs nommes | oui, sur la forme | dans le rapport de la livraison : `grep -c` des quatre noms `prefixes`, `chemins`, `outils`, `build` | n/a : prouve que le nom est ecrit, pas que le job a tourne. La preuve est la sortie collee |
| C7 arret au verdict | oui, pour le statut ; pour `MNC-1`, par comparaison de chaine | `git diff round-two --stat -- squad/programme/MATRICE-couverture.md` doit rendre vide avant le verdict ; `MNC-1` : `grep -F "<phrase de la matrice>" <fiche>` | vide aujourd'hui. La phrase `MNC-1` exacte est a nommer (la matrice la decrit en prose, ligne 3940, sans citation figee a comparer) |

Dans ce tableau, `\|` est une barre verticale ecrite avec l'echappement de tableau Markdown : a lancer avec `|`. Les commandes de C2, C3
et C5 ont ete lancees telles quelles sur `dc2870eb` (sorties 0 ligne, 0, 0), ce qui les rend vertes ou vides, pas rouges.

### 2.2 `S51-SCI-CORPS-CM1-RESERVES` (`CRP-CM1-R1` a `R3`)

- **Testable, rouge, mesure** :
  - `grep -n "ne fait rien d'autre" cm1-sciences-corps.yaml` rend la ligne 77 ; attendu 0. Idem pour les quatre autres chaines du
    backlog : `en grande quantité` 1, `évite de lui transmettre` 1, `à aider le ventre à digérer` 1, `à remplacer le sommeil` 1,
    `à se passer de manger` 1, chacune attendue a 0 ou a la forme decidee.
  - `grep -c '^ *- enonce:' cm1-sciences-corps.yaml` rend **12**, attendu 12 apres. Garde-fou existant :
    `PaquetCm1SciencesCorpsTest`, `hasSize(12)` ligne 138 sur les items et sur les lignes d'item.
  - `grep -c "corps-cm1" catalogue.yaml` rend 1 : le jeu est servi.
- **Limite du `grep` des anciennes chaines** : il garde la disparition de la phrase, pas sa correction. Toute edition le
  satisfait, y compris une phrase encore fausse. Il prouve un changement.
- **Filet qui mord sur la forme** : `PaquetCm1SciencesCorpsTest` `sci1ChaqueIndiceCompteUnePhraseEtNeNommeNiLaBonneReponseNiTaReponse`
  (ligne 183). Il ne fixe aucune des formulations fautives : lu, `grep` des chaines ci-dessus dans le test ne rend rien, donc la
  correction ne demande aucun edit du test.
- **Ce qu'aucun automate ne juge** : que l'indice de l'item 3 soit vrai, que la portee de l'item 6 et le role des fibres de l'item
  11 soient exacts, que les distracteurs de l'item 7 soient plausibles. `nadia-prof` seule (fiche, section 9).
- **Ce qui manque** : rien a ecrire cote automate. Une mutation n'a pas de sens ici, aucun garde-fou n'est cite comme fermant un
  refus.

### 2.3 `S54-BR-FRA-CE1-18` et `-19` (`ce1-futur`, `ce1-passe-compose`)

- **C3** : rouge, 0 et 0 (2.1). **C2, C4** : voir 2.1.
- **Mesures mecaniques sur les deux fichiers aujourd'hui**, par un script de lecture seule (analyse des lignes `enonce`, `indice`,
  `bonneReponse`, `distracteurs`), pour que le test du paquet (T1, du) ait un point de depart :

  | Mesure | `ce1-futur` | `ce1-passe-compose` |
  |---|---|---|
  | items | 24 | 24 |
  | items « être » ou « avoir » (`FUT-CE1-1` : au moins 6) | 8 | 8 (non exige) |
  | items dont un distracteur est l'infinitif du verbe (`PCO-CE1-3` (a) : au moins 8) | - | 20 |
  | indices de plus de 12 mots | 0 | 0 |
  | indices contenant la bonne reponse | 0 | 0 |
  | indices distincts sur 24 | 3 | 3 |

  Regle de comptage des mots, qui change le resultat : les 8 indices `"Le verbe change selon le moment : regarde le début de la
  phrase."` de `ce1-futur` comptent **12** mots si l'on ignore le ":" isole (c'est la regle de
  `PaquetCe1ConjugaisonPresentTest.mots`, un token compte s'il porte une lettre ou un chiffre) et **13** avec un `split` sur les
  blancs. Sans cette ligne ecrite, deux agents mesureront deux chiffres, comme en s53 (3.3).
- **`TRV-CE1-5`, ce que le texte exige et ce que chaque clause permet** (texte de `ARBITRAGE-PREALABLE-s48-francais-ce1.md`
  ligne 204) :

  | Clause | Automate ? | Etat aujourd'hui |
  |---|---|---|
  | douze mots au plus | oui | 0 depassement (table ci-dessus) |
  | jamais la reponse | oui, sous-chaine de `bonneReponse` dans `indice` | 0 |
  | pas identique sur tout le paquet | oui | 3 indices distincts sur 24 : la clause est tenue a la lettre |
  | jamais une regle fausse | non | - |
  | l'explication redonne la forme juste quand l'enonce montre une forme fautive | sans objet visible : aucun enonce des deux paquets ne montre de forme fautive (a confirmer par `nadia-prof`) | - |

- **Ce que "renvoi a l'oreille" ajoute, et d'ou il vient.** Le texte de `TRV-CE1-5` en s48 ne contient pas le mot "oreille". Le critere
  du planning ("aucun indice ne renvoie a l'oreille") reprend le **motif de refus** de la fiche de `FRA-CE1-17`
  (`FICHE-bandit-manchot-conjugaison-present-ce1.md`, section 4) : l'indice `"Lis la phrase dans ta tête avec chaque proposition."`
  sur des items dont deux propositions se prononcent de meme (*parlez*/*parler*, *dis*/*dit*). Ce refus est deja un garde-fou, pour
  ce seul paquet : `PaquetCe1ConjugaisonPresentTest.trv5AucunIndiceNeRenvoieALEcouteSurUnItemQuiADesDistracteursHomophones`
  (ligne 323), avec une table `HOMOPHONES` ecrite a la main et **un seul libelle d'indice interdit**. Il ne couvre aucun des deux
  nouveaux paquets. **Le cas de `ce1-passe-compose` est plus exposé que celui du present** : *j'ai chanté* et *j'ai chanter* se
  prononcent de meme, et 20 des 24 items ont ce distracteur. Un balayage de mots d'ecoute (`oreille|entends|écoute|sonne|prononce|dans ta tête`)
  sur les indices des deux paquets rend **0** aujourd'hui, mais c'est un filtre sur des mots, pas un juge : un indice qui renvoie a
  l'oreille sans ces mots passerait.
- **Danger propre a ces deux paquets** : trois indices qui tournent dans l'ordre du fichier sur 24 items (position `i mod 3`). La
  fiche de `FRA-CE1-17` donne cette rotation comme cause probable du refus (un indice servi qui ne va pas a l'item). `TRV-CE1-5`
  ne l'interdit pas (3 indices distincts). Qu'un indice convienne a son item ne se juge pas par un automate.
- **Ce que le critere "relu par le developpeur, resultat ecrit dans son rapport" prouve** : qu'une phrase est ecrite, pas que la
  relecture a eu lieu ni qu'elle est juste. Il n'est pas un critere au sens de ce fichier.
- **Ce qui manque pour le rendre juge par un automate (partiellement)** : le test du paquet (T1, `theo-archi` le tient pour du,
  par convention seulement), avec trois assertions ecrites par le developpeur et mordues par moi : les trois comptes de `FUT-CE1-1`,
  `PCO-CE1-3` (a) et `TRV-CE1-5` (douze mots, reponse, ecoute). Pour l'ecoute : une table de paires homophones, ou une regle
  (participe en -é / infinitif en -er) plutot qu'une liste. Je n'ecris rien de cela (pas de Java).

### 2.4 `S54-BR-SCI-CM1-15` et `-18` (reprises de sciences CM1)

- **C3** : rouge, 0 et 0. Pas de commande pour "l'en-tete nomme les motifs de la fiche" : c'est une lecture de texte libre.
  Le seul test mecanique est la presence des chaines : `grep -c "FICHE-bandit-manchot-terre-cm1" cm1-sciences-terre-jour-nuit-saisons.yaml`
  (>= 1), qui prouve la citation, pas la prise en compte.
- **Les mots des refus ne sont pas des proprietes testables telles quelles.**
  - `SCI-CM1-15` item 4, *"le distracteur de la distance est une affirmation vraie"* : l'item 4 du paquet neuf a pour distracteurs
    `"elle est cachée par la Lune"` et `"le Soleil s'est rapproché de cette région"`, indice `"La distance entre la Terre et le
    Soleil reste la même toute la journée."` Qu'un distracteur soit faux se juge par un fait scientifique : pas un automate.
  - `SCI-CM1-15` item 11, *"le printemps"* a d'aussi longs jours que *"l'ete"* : les distracteurs de l'item 11 du paquet neuf sont
    `"l'hiver"` et `"l'automne"`, sans *printemps*. Testable en negatif (aucune proposition ne vaut `le printemps`), ce qui ne dit
    rien de la validite des deux distracteurs restants.
  - `SCI-CM1-18` item 11, *"Deux propositions repondent a la question ecrite"* : plausibilite, pas un automate.
- **Garde-fou qui dort** : `PaquetCm1SciencesTerreTest` (paquet refuse, `PAQUET = "cm1-sciences-terre"`) fixe l'indice de
  l'item de la cause des saisons par `isEqualTo` (ligne 207) et interdit `distance` dans les bonnes reponses (`SCI-1`). Ces tests
  ne lisent pas le fichier neuf. Si `lea-dev` recible le test (avis, 2), un `isEqualTo` sur un indice est un test qui se casse a
  la premiere reformulation de `nadia-prof` : a recibler sur la propriete (indice d'une phrase, sans la bonne reponse), pas sur le texte.
- **Obstacle d'identifiant** (avis, section 2) : l'entree ne doit reprendre ni `bandit-manchot-terre-cm1` ni
  `bandit-manchot-activite-humaine-cm1` ni les titres retires, sous peine de rougir `LesJeuxRetiresDuCatalogueNeSontPasServisTest`.
  `theo-archi` l'a lu, non simule ; je ne l'ai pas simule non plus (editer `catalogue.yaml` m'est interdit ici).
- **Ce qu'aucun automate ne juge** : que chaque refus soit leve. Le rejeu est a `nadia-prof`. Rien dans la suite ne distingue un
  paquet refuse d'un paquet branche et valide, ce que `C7` dit deja.

### 2.5 `S54-BR-EMC-CM1-02` (`cm1-emc-atteinte-autrui`)

- **C3** : rouge, 0. **11 items**, 11 indices distincts, 0 indice de plus de 12 mots, 0 indice qui contient la bonne reponse.
- **`EMC-9`** (texte, `competences-cm1.md` ligne 97) : "aucun item ne laisse croire a l'enfant qu'il vient d'alerter quelqu'un".
  **Aucun `.java` ne cite `EMC-9`** (`git grep -l "EMC-9" -- '*.java'` rend rien). Balayage de lecture seule
  `prévenu|alerté|tu as|bravo|c'est bien` sur le fichier : **0 ligne**. C'est un filtre de mots. La clause porte sur ce que
  l'enfant comprend de l'ecran, et le texte de l'ecran qui dirait "tu as prevenu" est le retour juste/faux de la mecanique
  `bandit-manchot`, pas un champ du paquet : **ce qu'un paquet YAML ne contient pas n'est pas un perimetre de test du paquet**.
- **Ce qui manque** : (a) une liste de formulations interdites ecrite par `nadia-prof` (aucune n'existe), qui rendrait le filtre
  opposable ; (b) la confirmation que le retour de la mecanique ne dit jamais "tu as prevenu" sur cette ligne, ce qui releve de
  `theo-archi` (le retour est dans `adapter-web`) et non du paquet. Sans (a) et (b), `EMC-9` reste une lecture.

### 2.6 `S53-REPORT-GRANDS-NOMBRES`

- **Testable, rouge, mesure.** `grep -cE '^ *- enonce: ".* :' cm1-grands-nombres.yaml` rend **14** (attendu 0). Le fichier compte 28
  `enonce`, aucun n'a aujourd'hui une espace insecable avant ":" (`grep -cP '^\s*- enonce: ".*\x{00A0}:'` rend 0, lance) : les 14 autres
  sont des consignes sans deux-points ("Décompose ...", "Compare ..."). **Complement necessaire** : le `grep` a 0 est satisfait aussi par `Écris en chiffres: ` (espace
  supprimee) ou par une edition fausse ; la commande positive `grep -cP '^\s*- enonce: ".*\x{00A0}:'` doit rendre **14** apres.
- **Les deux constantes** : `PaquetCm1GrandsNombresTest` lignes 60 et 61 (`PREFIXE_ECRIRE`, `PREFIXE_LIRE`, utilises par
  `startsWith` lignes 199 et 203 et `substring` lignes 128 et 144). Garde-fou existant : `chacunDesQuatreVerbesEstRepresenteParAuMoinsUnQuartDuPaquet`
  (ligne 107), rouge si les deux ne bougent pas ensemble (mesure de `camille-po`, backlog : 28 attendus, 14 obtenus ; **je ne l'ai
  pas rejouee** : la mutation demande d'editer le YAML ou le Java).
- **Ecart a la source** : le critere dit "les 14 lignes et les deux constantes". `avis-faisabilite.md` T5 mesure un quatrieme et un
  cinquieme edit : `AucunSigneDoubleNEstDetachableDeSonMotTest` fixe `cm1-grands-nombres` a **26** (ligne 82) pour un total de **516**
  (ligne 99), en egalite ; apres correction 12 et 502. Un commit qui ne les porte pas est rouge a la fusion. Le critere "un seul
  commit" se teste par `git log --format=%h round-two..HEAD -- <yaml>` et la meme commande sur le test : **meme hash, un seul**.
- **Compte de tests** : "nombre de tests inchange" se teste (2174 sur la racine seule apres la story, 0 de delta de cette story).
- **Mutation a faire a la cloture, que je n'ai pas faite** : ne restaurer que les constantes (laisser le YAML corrige) et voir
  `chacunDesQuatreVerbes...` rouge ; ne restaurer que le YAML et voir le meme rouge ; remettre le commit. Le critere se dit
  ferme sur ses deux moities seulement apres cela.

### 2.7 `S51-OUTIL-JOURNAL-TOKENS`

- **Testable pour la forme, non pour l'effet reel.** Un test d'outil (`outils/rapport-tokens-journal-test.py`) lance par le job
  `outils` : l'existence se teste par `ls outils/rapport-tokens-journal-test.py` (rend **rien aujourd'hui**, rouge) et par le
  compte des juges du job : **9 aujourd'hui**, 10 attendus sans autre ajout (`outils/*-test.py` passe de 2 a 3).
- **Critere "ecrire_rapport_sprint conserve un rapport chiffre"** : rouge, lu : `outils/rapport-tokens.py` ligne 353 ecrit
  `"Aucune consommation mesuree sur cette fenetre."`, ligne 383 `"Aucune consommation mesuree."` pour le journal.
- **Critere "sans `--sprint`, `git status --short` ne montre aucune modification des `RAPPORT-tokens.md` de s22 a s27" : deux
  defauts de la source, mesures.**
  1. Les bornes sont fausses. `python outils/rapport-tokens.py --dry-run` (sans `--sprint`, rien n'est ecrit, `git status --short`
     vide apres, code de sortie 0) reecrirait **23** fichiers `RAPPORT-tokens.md` en "Aucune consommation mesuree sur cette
     fenetre." : s00, s01, s03, s05 a s09, s11, s13 a s19, s21, **s23 a s28**. **s22 n'en fait pas partie, s28 oui.** Les rapports
     de s22 a s27 que le critere nomme n'incluent donc pas s28 et incluent s22. Aucun des 23 rapports ne porte cette phrase
     aujourd'hui (`grep -c "Aucune consommation mesuree"` rend 0 sur s22 a s27).
  2. Le resultat depend du poste. La liste des 23 vient de l'absence de transcriptions sur ce poste ; sur un poste qui les a
     encore, l'outil les recalcule et le critere est vert sans que l'outil ait ete corrige. **Le critere doit etre juge avec
     `--projects-dir` pointe sur un repertoire vide**, sinon il mesure le poste, pas l'outil. Commande : creer un repertoire
     vide, `python outils/rapport-tokens.py --projects-dir <vide>` puis `git status --short -- 'squad/sprints/*/RAPPORT-tokens.md'` ; attendu vide.
     Le test `outils/rapport-tokens-journal-test.py` fait cela avec des entrees fabriquees : c'est lui le juge, pas le poste.
- **Non-preuve nommee par `theo-archi`** : une fenetre partiellement mesuree est "mesuree" et ecrase des chiffres plus hauts.
  Aucun critere ne la couvre ; **je la remonte** comme cas a ajouter au test.
- **Mutation a faire a la cloture** : retirer la garde de `ecrire_rapport_sprint` (ou de `ecrire_journal`) et voir le test d'outil
  rouge ; `outils/rapport-tokens-test.sh` seul reste vert, il ne dit rien de ce critere.

### 2.8 `S53-PASSE-TRANSVERSE` (`PAR-4`)

- **Testable pour la structure, jamais pour le fond.**
  - `find squad -iname 'PASSE-TRANSVERSE-s54*'` rend **0** aujourd'hui (rouge). Le fichier existant le plus recent est
    `PASSE-TRANSVERSE-s35-ecran-de-fin-de-partie.md`.
  - sha de l'arbre : `git cat-file -e <sha>` rend 0 si le sha ecrit existe. Prix reel ecrit : presence d'un nombre a cote de "0,5".
  - une ligne par propriete de la portee **D** : testable une fois la liste **enumeree** ; elle ne l'est pas, la cellule D de la
    table 13.2 de l'arbitrage de s29 (section 13.2) est de la prose a points-virgules. Compter les proprietes demande une
    lecture ; un test qui compterait les lignes du fichier compterait ce que `nadia-prof` a ecrit, pas ce qu'elle devait ecrire.
- **Ecart a la source** : le backlog, sous-section `S53-PASSE-TRANSVERSE`, ecrit encore ses criteres avec `PASSE-TRANSVERSE-s53-*.md`
  (premier critere, ligne 20124) alors que la note d'etat, le planning et `DEC-0114` disent `s54`. `find squad -iname
  'PASSE-TRANSVERSE-s53*'` restera a 0 apres livraison d'un fichier `s54` : **faux rouge**. A corriger par `camille-po`, que je ne corrige pas.
- **Ce qu'aucun automate ne juge** : que chaque propriete soit evaluee vraiment, que "tenue" soit juste. Le backlog le dit
  ("tout"). Ce qui manque : rien a ecrire cote automate au-dela du test de structure ; le fond est la porte pedagogique.

### 2.9 `SYC-CE1-R1` et item 16 de `FRA-CE1-25`

- Le paquet est `ce1-synonymes-contraires.yaml` (`lignesDeReferentiel: ["FRA-CE1-25"]`, servi : 1 entree de catalogue), items **16**
  (« arrive »), **19** (« lance »), **23** (« fort »). Le planning ne nomme pas le fichier.
- **Non automatisable pour le critere : le rejeu "a la barre ecrite"** (*"le ballon et la force sont chacun portes par un sujet
  feminin ou sans genre..."*). Ce qui manque : rien, c'est `nadia-prof` qui joue.
- **Testable pour la clause negative "Les reponses ne changent pas, sauf l'accord. Rien d'autre ne change."** :
  `git diff round-two -U0 -- adapters/adapter-content/src/main/resources/contenu/ce1-synonymes-contraires.yaml` ne doit montrer des
  hunks que dans les items 16, 19 et 23 ; une comparaison de `bonneReponse` avant et apres (script a ecrire, lecture seule)
  rend les seules differences d'accord. Le garde-fou existant `PaquetCe1SynonymesContrairesTest` s'applique au fichier, il ne
  dit pas qu'un item hors des trois n'a pas bouge. C'est un test de diff, il n'existe pas et ne se range pas dans la suite.

### 2.10 `S53-REPORT-REDACTION`

- **R3** : `grep -n "six fichiers" squad/conformite/ARBITRAGE-PREALABLE-s53-points-de-relecture-p1-a-p11.md` rend la **ligne 315**
  aujourd'hui (rouge). Testable, commande du planning juste.
- **R1** : `grep -c "0 fichier" squad/decisions/produit/DEC-0112-*.md` rend **0** aujourd'hui (vert) : l'amendement A.1 de `DEC-0112`
  est fusionne (le backlog le dit solde, ligne 20182). Le planning ecrit `grep -n` : meme commande, aucune ligne rendue.
- **R5** : "une note d'erratum" : testable par la presence du mot `erratum` dans `S53SPE-RELECTURE` du backlog ; le fond (9 contre
  11) est un chiffre a recompter, non rejoue ici.
- **A10 de la retro de s53** (mon geste, voir section 4).

### 2.11 `S54-ART-CP-10-PRIX`

- **Testable pour la forme.** (3) aucune ligne de matrice, aucun `catalogue.yaml` : `git diff round-two --stat` n'en montre aucun,
  vide aujourd'hui. (1) "avis de `theo-archi` : prix en tailles et statut d'`ADR-0104`" : le statut est ecrit dans le fichier d'`ADR`
  (`ADR-0104`, ligne 3 : "Accepte le 2026-10-07 (s54)", lu) et le prix dans `avis-faisabilite.md` section 4, T6.
  Le critere est donc **deja vert** sur ce point, alors que le backlog le dit rouge ("aucun prix n'est ecrit") : le backlog est en
  retard sur l'avis. (2) "une ligne de `nadia-prof`" : testable par existence d'une ligne dans son arbitrage prealable de s54, qui
  n'existe pas encore.
- **Ce qu'un automate ne juge pas** : que le prix soit juste. Un prix est une estimation.

### 2.12 Garde-fous generiques qui s'appliquent aux cinq paquets branches (filet, pas preuve)

- `TexteAccentueExposeParLePortEntrantTest` (`bootstrap`) : **mord au branchement** (mesure de `theo-archi`, passage 1 : 3 tests
  sur 5 rouges avec les cinq entrees seules ; je ne l'ai pas rejouee). Il demande 3 chaines par entree (identifiant, titre,
  competence), 4 si la consigne est neuve.
- `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` : declare deja les cinq paquets (seul test qui les nomme, mesure
  `git grep -l '"<nom>"' -- '*.java'`).
- `LesJeuxRetiresDuCatalogueNeSontPasServisTest` : les deux sciences, voir 2.4.
- Les garde-fous derives de `contenu/` jugent un paquet branche et un paquet non branche pareillement : la suite verte de la base
  prouve que les 45 paquets les passent, pas que les 5 branches fonctionnent.
- **Un compte de tests ne dit jamais qu'un paquet neuf a ete juge** : l'entree ne cree aucun test. Seul le test du paquet (T1) en ajoute.

## 3. Ce qu'un automate ne juge pas, et ce qui manque pour chacun

| Critere | Ce que la machine peut dire | Ce qu'elle ne peut pas | Ce qui manque |
|---|---|---|---|
| `TRV-CE1-5` sur `ce1-futur`, `ce1-passe-compose` | douze mots, bonne reponse absente de l'indice, indices non tous identiques (0, 0, 3 distincts aujourd'hui) | une regle vraie ; l'indice convient a son item (rotation sur 3) ; l'absence de renvoi a l'oreille hors table | le test du paquet (T1) avec trois assertions ; une table d'homophones ou la regle participe -é / infinitif -er ; la lecture de `nadia-prof` pour le reste |
| `EMC-9` sur `cm1-emc-atteinte-autrui` | 0 occurrence de 5 formulations dans le fichier | ce que l'enfant croit a l'ecran ; le retour juste/faux n'est pas dans le paquet | une liste de formulations interdites de `nadia-prof` ; la preuve cote `adapter-web` que le retour ne dit pas "tu as prevenu" |
| Rejeu de `SYC-CE1-R1`, items 16, 19, 23 | que les hunks du diff restent dans les trois items et que les `bonneReponse` ne changent que par l'accord | que le sujet soit feminin ou sans genre et naturel ; "peut faire douter" | rien cote automate ; le rejeu est le sien |
| Passe `PAR-4` | existence du fichier, sha atteignable, nombre de lignes | que chaque propriete soit evaluee, que "tenue" soit juste | une enumeration des proprietes de la portee D (la cellule est de la prose), puis un compte ; le fond est a `nadia-prof` |
| Reprises de sciences, mots des refus | un `grep` en negatif (aucune proposition `le printemps`) ; une citation de fiche presente | qu'un distracteur soit faux, que deux propositions ne repondent pas | rien : jugement de `nadia-prof`, a rejouer |
| Lignes `CRP`, items 3, 5, 6, 7, 11 | disparition des anciennes chaines, 12 items | verite, portee exacte, plausibilite | rien : `nadia-prof` |

## 4. A10 de la retro de s53 : "0 sur 182"

Le fichier `squad/sprints/s53-relecture-paquets-session-contenu/lecture-testabilite.md` (section 3.9 et ecart 6 de la section 5)
disait qu'une recherche d'octets `grep -lP '\xC2\xA0'` rend "0 fichier sur 182", qu'aucun fichier de `contenu/` ne porte donc
d'espace insecable, et que `grep -P '\x{00A0}'` donnerait 4 fichiers par fausse concordance avec l'octet `A0` de "à".

**Mesure de s54, 182 fichiers `*.yaml` de `adapters/adapter-content/src/main/resources/contenu/`, locale `en_US.UTF-8`** :

| Commande | Rendu |
|---|---|
| `grep -lP '\xC2\xA0' *.yaml` (celle de s53) | **0** (en mode UTF-8, la commande cherche les deux caracteres U+00C2 et U+00A0, pas les deux octets) |
| `grep -lP '\x{00A0}' *.yaml` | **161** |
| `grep -l $'\xc2\xa0' *.yaml` (sans `-P`) | **161** |
| recherche d'octets `b"\xc2\xa0"` en Python | **161** |
| `grep -lP '^\s*competence: ".*\x{00A0}:' *.yaml` | **4** (`ce1-accord-groupe-nominal`, `ce1-classes-de-mots`, `ce1-inference`, `ce1-texte-narratif`, ce que dit `DEC-0112` amendement A.1) |
| `grep -lP '^\s*competence: ".* :' *.yaml` (espace ordinaire) | **13**, intersection vide avec les 4 |

**Ce qui est exact** : 161 fichiers sur 182 portent une espace insecable. Le "0" venait d'un motif qui ne la cherchait pas. L'affirmation
"aucun fichier n'en porte" et la cause "ecartee" etaient fausses ; le "piege des 4 fichiers" n'a pas ete reproduit (la meme
commande rend 161 ici, la locale de la mesure de s53 est inconnue). L'ecart 9 contre 11 reste sans explication : je ne l'ai pas
recompte. `squad/sprints/s53-*/lecture-testabilite.md` est corrige dans ce commit (section 3.9, ecart 6).

**Ce que cela dit de la methode** : un "0" n'est une preuve que si le meme motif trouve quelque chose sur un temoin positif. Ici
le temoin existait (les 4 fichiers de `competence`, deja nommes par `DEC-0112` A.1) et n'a pas ete lance avec le motif des octets.

## 5. Ecarts a corriger dans le planning ou le backlog, sans les corriger

1. **Journal de tokens** : bornes "s22 a s27" fausses (23 a 28), et critere dependant du poste sans `--projects-dir` vide (2.7).
2. **`S53-PASSE-TRANSVERSE`** : le backlog ecrit les criteres avec `s53` ; faux rouge a la livraison d'un fichier `s54` (2.8).
3. **`TRV-CE1-5`** : le planning lui prete "aucun indice ne renvoie a l'oreille", le texte de s48 ne dit pas cela ; la clause vient
   du refus de `FRA-CE1-17` (2.3). Regle de comptage des mots a ecrire.
4. **C2** : liste "declarations des garde-fous" a reconcilier avec l'avis T2 (2.1).
5. **`S53-REPORT-GRANDS-NOMBRES`** : le commit unique porte 4 a 5 edits, pas "14 lignes et deux constantes" (2.6).
6. **`S54-ART-CP-10-PRIX`** : le critere (1) est deja vert depuis l'avis ; le backlog le dit rouge (2.11).
7. **C6** : un poste ou `python3` est un faux binaire ne lance pas le job `outils` tel quel ; le dire dans la livraison.
8. **Le paquet de `SYC-CE1-R1` n'est pas nomme** au planning : `ce1-synonymes-contraires`, items 16, 19, 23 (2.9).
9. **C1** : la `DEC` d'ouverture est `Proposee` ; le critere se juge sur l'existence, pas sur l'acceptation.

## 6. Ce que cette lecture n'a pas fait

- Aucune mutation : aucun garde-fou n'est cite comme fermant un refus dans ces stories, et editer un YAML ou un Java m'est interdit
  ici. Chaque "rouge aujourd'hui" est une commande lancee sur l'arbre non modifie, pas la preuve qu'un futur correctif fermera un critere.
  Les mutations a faire a la cloture sont nommees en 2.6 et 2.7.
- `TexteAccentueExposeParLePortEntrantTest` et `LesJeuxRetiresDuCatalogueNeSontPasServisTest` : rouges au branchement selon
  `theo-archi`, non rejoues par moi.
- Etape `mvn -B -pl bootstrap -am package` du job `build` : non lancee. Verdict de CI distante : non lisible depuis ce poste.
- Pas de relance de la suite pour juger de la stabilite : un seul passage, vert, aucun test instable observe.
- Aucun Done n'est prononce. Le Done technique est celui de la CI, tous les jobs ; le Done pedagogique est a `nadia-prof`.
