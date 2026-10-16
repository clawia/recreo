# [Sprint s57] Rapport de developpeur, palier 2 - `marc-dev`, postes p2-6, p2-5 et p2-3

- **Auteur** : `marc-dev`. **Date** : 2026-10-16.
- **Base commune** : `aa9d703c` (`git log -1 --format=%h` au premier message), sommet de `round-two` a l'ouverture des trois worktrees. Les trois branches en partent, `git merge-base --is-ancestor aa9d703c HEAD` sort 0 dans chacune. Aucun push, aucun rebase, aucune fusion.
- **Ou vit ce rapport** : sur la seule branche du poste p2-3, dans son dernier commit, parce qu'un SHA de commit ne peut pas figurer dans le commit qu'il nomme et que trois exemplaires du meme fichier se heurteraient a la fusion (ajout des deux cotes). La story `S57-SCRIPT-ADDITIONS-CP` demande que ce rapport nomme le script (critere 1) : ce critere ne se verifie donc qu'apres la fusion des deux branches. Voir `Q1`.
- **JDK de mesure** : 21.0.10, `java.version` lu dans les 60 rapports Surefire de `bootstrap` de chacune des trois branches (une seule valeur). Le `java` du `PATH` du poste n'est pas celui qui a mesure.
- **Ordre tenu** : un worktree a la fois, commit livre avant l'ouverture du suivant.

| Poste | Story | Branche | Commit livre | Tests Maven ajoutes |
|---|---|---|---|---|
| p2-6 | `S57-SCRIPT-ADDITIONS-CP` | `s57-marc-script-additions-cp` | `baefae1d` | 0 (un juge shell de plus dans le job `outils`) |
| p2-5 | `S57-OBS-SCI-N1-N2` | `s57-marc-sci-n1-n2` | `69dd7631` | 0 (critere 4 de la story) |
| p2-3 | `S57-BR-FRA-CE1-05` | `s57-marc-fra-ce1-05` | `b9f1c62f` | +37, `PaquetCe1PronomsTest` |

**Delta, pas un total** : mesure sur la base `aa9d703c` plus mon seul lot, branche par branche : 2597 (p2-6), 2597 (p2-5), 2634 (p2-3, adapter-content 1567 a 1604). Ce sont des totaux de mon reacteur ; celui de l'arbre fusionne est a `sofia-sm`.

## 1. p2-6 - `S57-SCRIPT-ADDITIONS-CP` (`baefae1d`)

**Livre** : `squad/conformite/releves-s57/mesurer-additions-cp.mjs` (le script, a cote de `pilote-r3.mjs`) et `outils/mesurer-additions-cp-test.sh` (son juge, balaye par le job `outils`). Aucun code produit.

- **Critere 1** : `git ls-files -- squad/conformite/releves-s57/mesurer-additions-cp.mjs` rend 1 sur la branche. Le rapport est ici, sur une autre branche (`Q1`).
- **Critere 2** : `grep -c sommeMaximale` rend 1 sur `cp-additions-jusqu-a-10.yaml` et 8 sur le script. L'ensemble est derive du paquet : 65 enonces distincts, egaux a une enumeration independante ecrite en `awk` dans le juge ; avec `sommeMaximale: 5` dans un paquet fabrique, 20 enonces (la valeur est bien lue, le juge le mord).
- **Critere 3** : lance sur le jar de la branche (code identique a la base : `git diff --stat aa9d703c HEAD -- adapters domain application bootstrap` vide), 744 x 551, apres une erreur, parcours de `TD5`. **65 enonces mesures sur 65, liste des non mesures vide**, 0 enonce hors ensemble, 0 anomalie. 40 parties, 400 manches, 400 observations (un enonce vu une fois au moins, quatorze fois au plus). **Une seule cote, 486, sur les 400 observations** ; aucun enonce n'a deux cotes differentes, l'hypothese *"item = enonce"* de `TD4` tient sur ce jar. Les trois propositions servies et la cote sont dans la sortie de chaque observation. Le 486 est la cote que `rapport-ines-r8-forme-commune.md` (ligne 135) donne avant la forme sur les 22 enonces qu'elle avait vus : meme methode, meme valeur.
- **Critere 4** : le juge lance le script sur un port de jeu ou rien n'ecoute : sortie 1, message *"rien n'ecoute sur le port de jeu"*, aucun fichier ni repertoire ecrit. Il le lance aussi sur un port de debogage deja occupe : sortie 1, le script refuse de se connecter au navigateur d'un autre.
- **Critere 5** : port de jeu, port de debogage et repertoire de sortie sont des arguments. `grep -cE '1[89][0-9]{3}'` rend 0, `grep -cE 'taskkill|pkill|killall'` rend 0 (le navigateur lance est ferme par `Browser.close`, puis par `kill` sur le PID de son processus, lu dans la sortie d'erreur).
- **Critere 6** : vide, comme ci-dessus.
- **Critere 7** : jobs en section 4. Garde : criteres 2, 4 et 5 par `outils/mesurer-additions-cp-test.sh` (mordu par trois mutations : valeur de somme figee, refus du port occupe retire, port ecrit en dur ; chacune fait rougir le juge, fichier restaure ensuite, `cmp` identique). **Non garde : critere 3** (la mesure elle-meme : navigateur, jar, 65 cotes) ; sa preuve est la sortie ci-dessus, que personne n'a rejouee.
- **Critere 8** : `grep -c "S57-SCRIPT-ADDITIONS-CP" squad/sprints/s57-*/planning.md` rend 3 ; `avis-theo-palier-2.md` (verdict de `E3`) est sur la base.

**Ce que la mesure n'est pas** : la cote de reference de `ines-design` (`PA1`, `PA2`) sur le jar de la base reelle de la forme. Mon passage valide le script ; je n'ai pas verse son resultat. **Les six ordres des propositions ne sont pas mesures** : le script releve l'ordre servi, comme la story le demande.

## 2. p2-5 - `S57-OBS-SCI-N1-N2` (`69dd7631`)

**Livre** : quatre lignes de `cm1-sciences-extraire-information.yaml`, rien d'autre. `git diff --stat aa9d703c HEAD` : 1 fichier, 4 insertions, 4 suppressions ; `catalogue.yaml` : diff vide ; aucun fichier de test touche.

- **Critere 1 (`N1`)** : l'enonce de l'item *"de produire des fruits et des graines"* est *"D'après ce texte, que permet ce transport de pollen à de nombreuses plantes ?"* ; bonne reponse et distracteurs inchanges. Rouge avant (base : faux), vert apres, par script de poste lisant les champs.
- **Critere 2 (`N2`)** : les trois indices valent les textes de `nadia-prof`, avec chevrons : 0 sur 3 avant, 3 sur 3 apres. `grep -c "«"` rend 3 (0 avant).
- **Critere 3** : sur les 12 items, aucun indice ne contient une proposition de son item (mot entier, sans casse) : 0 avant, 0 apres.
- **Critere 4** : vert, ci-dessus. **Critere 5** : `git grep -n "transport de pollen\|Relis la derni" -- '*.java'` rend 0 ; test du paquet inchange, vert.
- **Jeu au jar** : le jar de la branche a servi les trois indices et l'enonce tels qu'ecrits (lus dans le DOM rendu, 744 x 551, apres une erreur par item) ; l'espace devant le point d'interrogation de `N1` est U+00A0.
- **Espaces** : les chevrons sont ecrits avec U+00A0 a l'interieur, comme dans le depot (562 lignes contre 3 en espace ordinaire dans `contenu/`), et l'espace avant `?` reste U+00A0 comme sur les 11 autres enonces. Le texte de `nadia-prof` du fichier source porte des espaces ordinaires : **je n'ai pas decide l'orthotypographie, j'ai suivi l'usage du depot** ; elle reste au `relecteur-pedagogique`, avant le rejeu (`Q2`).
- **Aucun test ajoute** : le critere 4 borne le diff a ce fichier, le critere 5 laisse le test du paquet inchange. Un test qui epingle ces textes violerait l'un des deux (`Q3`).

## 3. p2-3 - `S57-BR-FRA-CE1-05` (`b9f1c62f`)

**Fichiers du commit (K2, 0 hors liste)** : `catalogue.yaml`, `ce1-pronoms.yaml` (corrections de `nadia-prof`), `PaquetCe1PronomsTest.java`, quatre fixtures sous `contreexemple/PaquetCe1PronomsTest/`, `TexteAccentueExposeParLePortEntrantTest.java` (trois chaines).

**Corrections recopiees** (`reponse-nadia-q-palier-2.md` 1.1 et 3.5), U+00A0 dans les chevrons et devant les deux-points comme dans le reste du paquet :

- items 10 et 13, indice : `« Elle » désigne un mot féminin : cherche-le dans la phrase d'avant.`
- item 15 : texte *"Le chat de Julie voit Elias. Il lui donne un biscuit."*, distracteurs *"le chat"* et *"Julie"*, enonce et indice inchanges.

**Textes de ma main, a juger par `nadia-prof`** : le titre *"Il, elle, ils, elles"* (sans niveau, sans signe double) ; la consigne est celle de l'entree du texte narratif du CE1 (*"Lis le petit texte, puis réponds à la question."*), donc aucune chaine de consigne neuve. **Une modification que ma lecture de son fichier (3.5, derniere phrase) m'attribue** : le commentaire de tete du paquet disait *"vingt prenoms masculins et vingt prenoms feminins"* ; il dit maintenant *"vingt prenoms masculins et vingt et un prenoms feminins"*, le compte reel (21 et 20, table du test).

**Criteres** (valeurs mesurees sur le fichier corrige, puis sur `git show HEAD:...` avant) :

- **K1** : (a) `grep -c "S57-BR-FRA-CE1-05" squad/sprints/s57-*/planning.md` rend 1 ; (b) `git show round-two:<arbitrage-nadia-palier-2.md>` sort 0. **Voir `Q4`** : la commande de `DEC-0120` A.96 rend 0 sur la base ; sa variante `lecture complete.{0,6} : ` trouve `reponse-nadia-q-palier-2.md`.
- **K3** : `grep -c "paquet: ce1-pronoms$"` rend 1 (0 avant) ; entrees du catalogue 151 sur ma branche (150 avant) ; identifiant unique et distinct ; aucune espace ordinaire devant `?:;!` dans le titre ou la consigne.
- **K4** : `PaquetCe1PronomsTest` et son dossier de fixtures ; `git ls-files` en rend 5 fichiers ; 37 tests.
- **PN1** 20 items. **PN2** recompte sur les enonces : il 5, elle 5, ils 4, elles 3, substituts 3 (les `grep -cP` de la story rendent ces cinq nombres) ; ils et elles 7 pour un seuil de 4, substituts 3 pour un plafond de 6. **PN3** 0 item ou le mot cite n'apparait pas exactement une fois, 0 il impersonnel. **PN4** 1 item avant (item 15), **0 apres**. **PN5** au plus 12 mots, 3 phrases, 8 mots par phrase. **PN6** au plus 3 mots par proposition, 10 mots par indice. **PN7** 20 enonces entre chevrons, 0 indice qui ouvre par *"Elle "* (2 avant). **PN8** : voir `Q5`.
- **K7, `TRV-CE1-5`** : **relu**. Les 20 indices lus un a un puis comptes par le test : 9 indices distincts, 10 mots au plus, aucun renvoi a l'oreille, aucun ne contient une proposition de son item. La regle de *« Elle »* (un mot feminin) est vraie sur les items 10 et 13 (*Sofia*, *Rose*, face a un mot masculin).
- **K8, un test qui mord ou "non garde"** : garde par le test, chacun avec un jeu fautif : `PRN-CE1-1`, `PRN-CE1-3` (deux gardes), `PN4`, `LON-CE1-3`, `-4` (deux), `-5`, `TRV-CE1-5`, `TRV-CE1-6`, le point d'audit des chevrons (limite : hors des chevrons, ni un indice ni un enonce ne porte il, elle, ils ou elles), `K3` (trois catalogues fautifs). **Non garde** : `PRN-CE1-2` (le sens decide), l'exactitude et l'unicite de chaque referent, ce que dit un indice au regard du monde, aucun role par genre (`TRV-CE1-6`), qu'un mot cite soit un pronom hors des chevrons.
- **K9** : `git diff aa9d703c HEAD --stat -- squad/programme/MATRICE-couverture.md` vide ; aucune fiche ouverte. **K10** : aucun fichier du garde-fou de l'accent ni `pom.xml`.
- **Etat rouge avant la correction** : le test ecrit, sur le paquet et le catalogue de la base, rougit sur quatre tests exactement : l'entree du catalogue (`K3`), `PN4` (item 15), le point d'audit des chevrons (items 10 et 13), la table des prenoms (*Julie* absent des textes). Les 33 autres sont verts, dont les 16 de jeux et de catalogues fautifs.
- **Jeu au jar** (port propre, lecture du DOM rendu a 744 x 551, apres une erreur par item) : le titre est sur la page *Français* du CE1 ; les items 10 et 13 servent l'indice avec chevrons, l'item 15 sert le texte de `nadia-prof` et les propositions *le chat*, *Elias*, *Julie*.

**Dette de duplication nommee, non payee** : `mots`, `phrases`, la regle de fin de phrase et la table d'ouvertures sont maintenant recopiees dans deux classes de test (`PaquetCe1TexteNarratifTest` et celle-ci). Je n'en fais pas un utilitaire : c'est une decision de structure, a `sofia-sm` puis `theo-archi`.

## 4. Jobs de `ci.yml` lances, avec leur sortie

Sur chaque worktree, sur l'arbre du commit livre ; le rapport est entre dans l'arbre de p2-3 apres.

- **`build`** : `bash outils/mvn-jdk21.sh -B clean verify` depuis la racine, sans `-pl`. p2-6 : `BUILD SUCCESS`, 120, 31, 492, 1567, 381, plus 6 d'integration, 2597, 0 echec. p2-5 : idem, 2597. p2-3 : `BUILD SUCCESS`, 120, 31, 492, **1604**, 381, plus 6, 2634, 0 echec. Etape *"Package du fat jar"* non lancee : `verify` a produit le jar joue.
- **`chemins`** : `verifier-chemins-test.sh` sort 0 ; `git ls-files` (moins le test du garde-fou), fichiers neufs indexes compris, passe a `verifier-chemins.sh`, sortie 0, sur les trois branches.
- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, `verifier-garde-fou-accent-test.sh` sortent 0 ; le sujet de chaque commit passe dans `verifier-prefixes.sh`, sortie 0 ; `verifier-garde-fou-accent.sh "HEAD^!"` : *"0 commit(s) le touchent dans la plage, tous [THEO]."*, sortie 0.
- **`outils`** : les trois balayages. p2-6 : 13 juges (6 en `outils/*-test.sh`, 2 en `squad/outils/*/*-test.sh`, 5 en `outils/*-test.py`), 13 sortis 0 ; **le juge de p2-6 est le treizieme, le premier a exiger `node`**. p2-5 et p2-3 : 12 juges, 12 sortis 0.
- **Pour ce fichier** : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test` filtre sur `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`, `AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest`, `ToutesLesReferencesDeDecisionResolventTest` et `ConcordanceDesTagsDAgentTest`, rapport dans l'index : 19 tests, 0 echec, `BUILD SUCCESS` (mesure partielle, `-am` present ; le `build` complet ci-dessus a ete lance avant l'ecriture de ce fichier). `verifier-chemins.sh` sur les fichiers suivis, rapport compris : sortie 0.
- La CI distante n'est pas lue.

## 5. Isolation

Repertoires `s57-p2-marc-dev-t` (p2-6, ports 18525 et 19225), `s57-p2-marc-dev-b` (p2-5, 18524 et 19224), `s57-p2-marc-dev-a` (p2-3, 18521 et 19221), copies de la table du planning, crees avant la premiere commande. Ports verifies libres par `netstat -ano` avant chaque lancement ; ligne *"Tomcat started on port"* et PID lus dans le journal du jar, arret par ce PID seul, plus aucune ligne LISTENING apres. Navigateur lance par mon script ou ma lecture, ferme par `Browser.close` puis par son PID. **Racine : propre** : racine du scratchpad listee a la fin, 9 repertoires et aucun fichier, dont mes trois ; repertoire parent des worktrees liste, 12 fichiers dates d'avant le 2026-10-16 (aucun de moi), hors les trois worktrees que la table m'imposait.

**Deux faux resultats evites** : (1) une lecture de poste de p2-5 a rendu des propositions amputees de leurs *s* (une expression reguliere perdait une barre oblique inverse a l'ecriture) ; je l'ai vu parce que *"re ter"* n'est pas un mot, corrige et relance avant de rien conclure ; le script livre en p2-6 porte la bonne ecriture (relue a l'octet). (2) Une mutation du juge de p2-6 (refus du port occupe retire) a suspendu le juge ; processus arrete par PID, fichier restaure, `cmp` identique a la copie de sauvegarde. Cette suspension est la raison du delai de 2 secondes que j'ai ajoute ensuite sur la lecture de la liste des cibles ; je n'ai pas rejoue cette mutation apres l'ajout.

## 6. Ecarts et questions

1. **`Q1` (`sofia-sm`)** : le rapport sur une seule branche vous convient-il ? Sinon, trois exemplaires et une resolution a la fusion.
2. **`Q2` (`relecteur-pedagogique`)** : les chevrons de `N2` et de l'indice des items 10 et 13, U+00A0 dedans, sont-ils la bonne typographie ? Le texte source de `nadia-prof` porte des espaces ordinaires.
3. **`Q3` (`camille-po`, `sofia-sm`)** : un test qui epingle les quatre textes de `N1` et `N2` est-il voulu ? Il faudrait amender le critere 4 ou 5.
4. **`Q4` (`camille-po`)** : la commande d'`A.96` rend 0 pour `ce1-pronoms` sur la base (la ligne de `nadia-prof` porte des `**` entre *complete* et *:*). J'ai ouvert le worktree sur la consigne du lanceur et parce que la ligne existe ; je ne tranche pas laquelle des deux, commande ou ligne, est a corriger.
5. **`Q5` (`camille-po`)** : `PN8` demande que la correction soit ajoutee par amendement a la story avant le worktree ; `grep -n "items 10 et 13" docs/backlog/backlog.md` rend 0 sur la base. J'ai suivi le texte de `nadia-prof` et la consigne du lanceur ; l'amendement reste a ecrire.
6. **`Q6` (`nadia-prof`)** : le titre *"Il, elle, ils, elles"*, le commentaire de tete corrige, et l'item 15 (le texte est le sien, son prenom *Julie* est ajoute a la table du test).
7. **Catalogue** : 151 entrees sur ma branche ; *152 apres `-24`* est le compte de `sofia-sm`. Je n'ai touche aucun fichier partage au-dela de mon ajout (une entree, trois chaines).

## 7. Ce que je n'ai pas mesure

- La cote de reference (`PA2`) et toute cote apres la forme : a `ines-design`.
- Le script sur un autre navigateur, un autre poste, une autre version de Node : lance sous Node 24 ; le mode mesure demande le `WebSocket` global (Node 22 ou plus). Le juge, lui, n'utilise que le mode ensemble et les refus : je n'ai pas verifie que `node` est present sur l'image du job `outils` distant.
- Les 65 enonces dans les six ordres de propositions ; la mesure a 720 ou 768 ; un navigateur avec barre de defilement classique (a 744 le script rapporte `clientWidth` egal a la largeur de la fenetre).
- Le rendu de `N1`, `N2` et des corrections de `ce1-pronoms` sur une capture d'ecran : lu dans le DOM, pas regarde a l'oeil.
- Aucun Done, aucun rejeu : le rejeu de `N1` et `N2` (0,25) et l'audit de `ce1-pronoms` sont a `nadia-prof`.

## 8. Ce que ce rapport ne fait pas

Ne prononce aucun Done, ne fusionne rien, ne juge ni le titre ni la typographie, ne modifie ni un critere ni une story, ne decide aucune regle d'architecture.
