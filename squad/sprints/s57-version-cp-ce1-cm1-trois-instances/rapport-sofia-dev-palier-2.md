# [Sprint s57] Rapport de developpeuse - palier 2, poste p2-4, `S57-BR-FRA-CE1-24` avec `S57-FRA-FAMILLES-LIVRE`

- **Correction datee du 2026-10-16 (casquette developpeuse, apres le rapport qualite de `yanis-qa`, commit `54a606d8`, section 6)**. Le corps de ce rapport n'est pas reecrit ; quatre de ses phrases sont corrigees ici, sans changement de code :
  - `E1` : *"non livrable en l'etat"* et *"rouge sur `K10`"* decrivent l'arbre `e918f43e` et sont **vrais a `e918f43e`, faux a la tete `6b70d7e5`**. `K10` est leve par l'amendement `A3` d'`ADR-0065` (`theo-archi`, 2026-10-16, present dans la branche par la fusion de `edcd3de9`) ; `yanis-qa` mesure sur `6b70d7e5` un reacteur complet vert (rapport qualite, section 5). Le sujet du commit `1febd011` (*"NON LIVRE : rouge sur K10"*) reste ce qu'il etait ce jour-la.
  - `E2` : la ligne `FM5` du tableau `FM1` a `FM6` (*"sans objet"*) est fausse depuis l'amendement de `camille-po` du 2026-10-16 10:45 : **`FM5` s'applique**, ses six commandes (`FM5-1` a `FM5-6`) sont rejouees vertes par `yanis-qa` sur `6b70d7e5` (section 1 de son rapport).
  - `E3` : le texte de l'item 12 n'est garde par aucun test Java (`yanis-qa`, mutation `M5` : *chaton* remplace par *chatte*, verte dans le reacteur). **Non garde en Java : `FM5-2` et `FM5-3` le gardent**, par commande. Aucun test n'est ajoute : le perimetre de `FM5` ne demande que des commandes, et un test d'egalite copierait le texte de `nadia-prof` en un second exemplaire a tenir.
  - `E4` : l'amendement de `FM3` (le mot *préfixe* du libelle `descripteur.competence` n'est pas servi par les gabarits, mais est inventorie comme chaine exposee) est attendu de `camille-po` et de `theo-archi`. **Il n'est pas ecrit ici** ; il est route dans l'amendement de planning de `sofia-sm`.

- **Casquette** : developpeuse, declaree au debut de la tache, apres la casquette Scrum Master (fusion `4004fab7`, geste distinct). Je n'arbitre aucun obstacle ici : celui de `K10` est route a `theo-archi`.
- **Date** : 2026-10-16. **Arbre** : branche `s57-sofia-fra-ce1-24`, worktree `../recreo-s57-sofia-fra-ce1-24`, coupee de `round-two` a `4004fab7`. `bash outils/verifier-lignee.sh` sort 0.
- **Commit du lot** : `1febd011` (code, contenu et tests dans le meme commit). **Non fusionne, non pousse, et rouge sur `K10` : non livrable en l'etat.**
- **Scratchpad et port** : repertoire `s57-p2-sofia-dev-ap`, ports 18523 et 19223, libres avant le travail (`netstat`). **Aucun jar n'a ete lance** : aucun port ouvert, aucun PID.

## Ce qui bloque, route a `theo-archi` (`K10`)

Le reacteur complet est rouge sur **un seul test** : `AucunMotServiNeSEcarteDeSonAccentTest.chaqueMotServiEnProductionPorteSonAccent`.

Besoin, dans la forme que `K10` demande :

| Champ | Valeur |
|---|---|
| Paquet | `ce1-familles-de-mots`, entree de catalogue `bandit-manchot-familles-de-mots-ce1` |
| Texte | `descripteur.competence` du paquet (champ non modifie par ce lot : le libelle est celui du referentiel, copie mot pour mot) |
| Mot refuse | `re` (dans `re-`) : *"le mot "re" n'est pas une forme du francais, alors que [ré] en est une du meme squelette"* |
| Forme | un affixe cite avec son trait d'union dans un libelle de competence : *"(re-, dé-, -eur...)"* ; `dé` et `eur` passent |
| Chaine servie (mot du garde-fou) | *"Regrouper les mots d'une même famille et repérer un préfixe ou un suffixe fréquent (re-, dé-, -eur...)."* |

**Ce que je n'ai pas fait** : toucher le garde-fou, `LexiqueFrancais`, `ADR-0065` ou un `pom.xml` (`git diff-tree` du commit, `grep -E 'AucunMotServiNeSEcarteDeSonAccentTest|LexiqueFrancais|ADR-0065|pom\.xml'` : rien) ; changer le libelle du paquet (`K2`).
**Ce que je ne choisis pas** : accepter `re-` cote garde-fou (`theo-archi`), ou reecrire le libelle (`nadia-prof`, `camille-po` : il copie `squad/programme/francais/competences-ce1.md`, ligne `FRA-CE1-24`). **Fait a lui signaler** : `FM3` de la story dit que le gabarit ne sert pas `descripteur.competence` ; le garde-fou, lui, la lit comme *"chaine servie"*. Les deux peuvent etre vrais, le garde-fou etant plus large que le gabarit : je ne l'ai pas verifie dans les gabarits, c'est a lui.

Le lot attend ; ceux de l'autre instance n'attendent pas (`K10`).

## `K1` (c) et la lecture complete

| Commande, dans ce worktree | Sortie |
|---|---|
| `git merge-base --is-ancestor 729b0d8a HEAD` | 0 |
| `git merge-base --is-ancestor c62e0ad8 HEAD` (commit de la ligne *"lecture complete"* de `nadia-prof`, forme corrigee) | 0 |
| `git grep -l -E 'lecture complete : .?ce1-familles-de-mots.?, 24 items lus' HEAD -- squad docs` | `reponse-nadia-q-palier-2.md`, 1 fichier (commande de `DEC-0120` A.96 : base du worktree contient la ligne) |
| `git show round-two:.../arbitrage-nadia-palier-2.md` | sort 0 |
| `grep -c "S57-BR-FRA-CE1-24" planning.md` | 1 |

## Ce qui est livre

| Fichier | Changement |
|---|---|
| `adapters/adapter-content/src/main/resources/contenu/ce1-familles-de-mots.yaml` | item *livre* remplace par *chat* / *chaton* ; 13 indices de famille portes a la section 3.6 (`FM6`) |
| `adapters/adapter-content/src/main/resources/catalogue.yaml` | une entree : `bandit-manchot-familles-de-mots-ce1`, titre *"Les familles de mots"*, `nombreDeManches: 10` |
| `bootstrap/src/test/java/.../TexteAccentueExposeParLePortEntrantTest.java` | trois chaines d'inventaire : identifiant, titre, libelle de competence |
| `adapters/adapter-content/src/test/java/.../PaquetCe1FamillesDeMotsTest.java` | 24 tests |
| `adapters/adapter-content/src/test/resources/contreexemple/PaquetCe1FamillesDeMotsTest/` | un catalogue fautif, deux jeux fautifs |

**Correction de l'item *livre* (`Q-S8`)** : les cinq lignes (commentaire, enonce, bonne reponse, distracteurs, indice) sont celles de `reponse-nadia-q-palier-2.md` 3.6, lignes 159 a 163, copiees par script puis comparees : les cinq lignes sont presentes dans le YAML, **a un ecart pres, volontaire** : dans l'enonce, les espaces de ses chevrons et celle devant `?` sont des U+00A0, alors que son fichier porte des espaces ordinaires. Motif : sa phrase *"Espaces de l'enonce : celles des 13 autres enonces de famille"* (U+00A0, verifie a `od -c`), et `ADR-0067`. `grep -c 'bonneReponse: "librairie"'` : **0** (1 avant). `grep -c 'bonneReponse:'` : 24.

**`FM6`** (`camille-po`, `79130ca9` sur `s57-camille-p2-garde`, non fusionne ; coordinateur : *"si tu n'as pas de retrait ecrit de nadia dans le depot, FM6 s'applique"* ; je n'ai trouve aucun retrait) : `grep -c 'indice: "Le bon mot garde un morceau et parle de la même chose."'` **14** ; `grep -cE 'indice: "(Cherche le mot qui parle de la même chose\.|Un mot de la même famille garde un morceau du mot\.|Compare les trois mots avec le mot de départ\.)"'` **0** ; `git diff -U0 4004fab7 HEAD -- <yaml> | grep -cE '^[-+] *indice:'` **28**. Les trois mesures de la story sont tenues. **`Q-B2`** : recommandation de `nadia-prof`, arbitree par `camille-po`, appliquee. Rien d'autre que ces 14 indices et l'item *livre* ne change dans le YAML.

**Trois textes lus par l'enfant sont de ma main, a juger par `nadia-prof`** : le titre *"Les familles de mots"* (sans niveau, `ADR-0078`), la consigne *"Lis bien, puis choisis la bonne réponse."* (reprise des entrees `-13`, `-10`, `-15` : la question est portee par chaque enonce), et la place de l'entree (apres `synonymes-contraires`, voisine thematique).

## Criteres communs, avec les commandes

| Critere | Commande | Resultat |
|---|---|---|
| `K2` | `git diff-tree --no-commit-id --name-only -r 1febd011` suivi de `grep -vE` sur `catalogue.yaml`, `PaquetCe1FamillesDeMotsTest`, `TexteAccentueExposeParLePortEntrantTest.java`, le YAML du paquet | 7 fichiers, 0 hors liste (le YAML : correction decidee par `nadia-prof` et `FM6` de `camille-po`) |
| `K3` | `grep -c "paquet: ce1-familles-de-mots$"` sur `catalogue.yaml` ; `grep -c "  - id: bandit-manchot-familles-de-mots-ce1$"` | 1 et 1 (0 avant) ; entrees : 151 (150 avant) ; `mecanique: bandit-manchot` : 146 (145 avant) |
| `K4` | `git ls-files \| grep -c -E 'PaquetCe1(Pronoms\|FamillesDeMots)Test'` | 4 chemins (test, catalogue fautif, deux jeux fautifs) |
| `K5` | aucun test existant modifie hors trois lignes d'inventaire ajoutees | oui |
| `K9` | `git diff round-two --stat -- squad/programme/MATRICE-couverture.md` | vide ; aucune fiche ouverte |
| `K10` | `git diff-tree ... \| grep -E 'AucunMotServiNeSEcarteDeSonAccentTest\|LexiqueFrancais\|ADR-0065\|pom\.xml'` | ne rend rien ; **mais le lot est rouge sur le garde-fou, voir plus haut** |

**Compte du catalogue** : la consigne parlait de 147 apres `-05` et `-24`. Mesure : 147 est le compte de `mecanique: bandit-manchot` (145 + `-05` + `-24`), pas des entrees (150 + 2 = 152). Ce worktree, seul `-24` fusionne : 151 entrees, 146 `bandit-manchot`.

## `FM1` a `FM6`

| Point | Garde |
|---|---|
| `FM1`, plancher de vingt | `trv7fm1LePaquetGardeAuMoinsVingtItems...` (24 aujourd'hui) |
| `FM2`, `FAM-CE1-1` | `fam1LesProportionsSontRecomptees` sur le champ `enonce` : 14 de famille, 10 d'affixe, chaque item de l'une des deux formes ; deux jeux fautifs (*"1 items de famille sur 5"*, *"0 items d'affixe sur 5"*) |
| `FM3`, `FAM-CE1-3` et `TRV-CE1-4` | `fam3LeNomDeLAffixeNEstJamaisLuParLEnfant` : sous-chaines *préfixe, prefixe, suffixe, radical, affixe* sur enonce, propositions et indice (comme le `grep` de la story) ; `trv4...` pour le lexique entier ; jeu fautif (*suffixe* dans un indice) |
| `FM4`, `FAM-CE1-2` | **non garde : familles exactes et non savantes** (un test voit un mot, pas son etymologie) ; **non garde : l'ordre des distracteurs (a) fausse famille, (b) meme theme** (*poire* et *pomme* partagent deux lettres : aucune regle lexicale ne separe (a) de (b)). Garde etroit, celui de la story `S57-FRA-FAMILLES-LIVRE` : `fam2AucuneReponseSavanteRefuseeNEstAttendue` (*librairie*), jeu fautif |
| `FM5` | sans objet : la correction de `nadia-prof` est recopiee, aucune autre demandee |
| `FM6` | `fm6ChaqueItemDeFamillePorteLIndiceDeLaSection36` : egalite exacte sur les items dont l'enonce commence par *"Quel mot est de la même famille"* ; jeu fautif |

## `K7` : `TRV-CE1-5`

**Relu** : les 24 indices lus un a un, puis comptes par script de poste : 24 indices, **3 distincts** (14 de famille, 5 + 5 d'affixe), **12 mots au plus**, aucun renvoi a l'oreille ; aucun ne contient une proposition de son item. Garde par `trv5...` (quatre tests). `TRV-CE1-2` : 8 items sur 24 ou la bonne reponse est strictement la plus longue, borne du test 12. `LON-CE1-5` : 4 mots au plus par proposition. `TRV-CE1-6` : sans objet, aucun prenom.

## Mutations, avec les deux controles (motif trouve, tests lances superieur a zero)

Sur `ce1-familles-de-mots.yaml`, test lance : `PaquetCe1FamillesDeMotsTest`, 24 tests ; fichier restaure par copie de reference apres chaque mutation, `cmp` verifie.

| Mutation | Motif trouve | Tests lances | Resultat |
|---|---|---|---|
| `bonneReponse: "chaton"` devient `"librairie"` | 1 | 24 | 1 echec |
| un indice de famille remplace par *"Compare les trois mots avec le mot de départ."* | 14 | 24 | 1 echec |
| un indice d'affixe devient *"Cherche le suffixe du mot ?"* | 5 | 24 | 2 echecs |
| l'enonce *"Que veut dire « refaire » ?"* devient *"Dis ce que « refaire » veut dire."* | 1 | 24 | 1 echec |

**Deux faux verts evites, nommes** : mes premieres formulations des mutations 3 et 4 ont rendu *"MOTIF TROUVE 0"* (les espaces de ces lignes sont des U+00A0, mon motif avait des espaces ordinaires) et la suite est restee verte a 24 tests : je ne l'ai pas lue comme un vert, j'ai refait les deux avec les octets U+00A0. Premiere version de mon script de recopie : il a ecrit `\r\r\n` (le fichier de `nadia-prof` est en CRLF dans ce worktree) ; `git diff` montrait 288 lignes changees au lieu de 4, ce qui l'a revele, corrige avant tout commit.

## Jobs de `ci.yml` lances, avec leur sortie

- **`build`** : `bash outils/mvn-jdk21.sh -B clean verify` depuis la racine, sans `-pl`, sur l'arbre du commit : **`BUILD FAILURE`**, 1 echec (`chaqueMotServiEnProductionPorteSonAccent`), le reacteur s'arrete a `adapter-content`. Une seconde execution du reacteur complet avec `-Dmaven.test.failure.ignore=true`, **pour mesurer le reste et rien d'autre** : domain 120, application 31, adapter-web 492, adapter-content 1591, bootstrap 381, Failsafe 6 ; **total 2621, 1 echec, 0 erreur, 0 ignore**. 2621 = 2597 (reacteur de `round-two` a `4004fab7`, mesure par moi a la fusion) plus 24 tests de ce lot. **Le total ne vaut pas pour l'arbre fusionne.** Etape *"Package du fat jar"* non lancee.
- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh` et `verifier-garde-fou-accent-test.sh` sortent 0 ; les sujets de `4004fab7..HEAD` (hors fusions) et le sujet prevu du commit de ce rapport passent dans `verifier-prefixes.sh`, sortie 0 ; `verifier-garde-fou-accent.sh "4004fab7..HEAD"` : *"0 commit(s) le touchent dans la plage, tous [THEO]"*, sortie 0.
- **`chemins`** : `verifier-chemins-test.sh` sort 0 ; `git ls-files` (moins le test du garde-fou), ce rapport indexe, passe a `verifier-chemins.sh` : sortie 0.
- **`outils`** : les trois balayages, 12 juges lances (`python`), 12 sortis 0, aucun balayage vide.
- **Ce fichier** : `AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest` et `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` (`-pl bootstrap -am`, 5 et 10 tests, 0 echec) relances avec ce rapport dans l'index ; le `build` complet ci-dessus a ete lance avant son ecriture.

## Ce qui est ouvert, ce qui n'est pas mesure

**Ouvert** :
1. `K10` : le besoin ci-dessus, a `theo-archi`, puis (si le libelle change) a `nadia-prof` et `camille-po`. Tant qu'il n'est pas tranche par un fichier, ce commit ne peut pas etre fusionne.
2. `FM6` s'appuie sur `79130ca9`, branche `s57-camille-p2-garde` **non fusionnee** : la story que j'applique n'est pas encore sur `round-two`. A `sofia-sm` (ordre de fusion).
3. Conflit de fusion a prevoir avec `-05` : `catalogue.yaml` et `TexteAccentueExposeParLePortEntrantTest.java` portent des ajouts des deux cotes. **J'ai place mes trois chaines apres `synonymes-contraires` (et non apres `verbe-sujet` ou `texte-narratif`, ancres de `-13`, `-10`, `-15`, `-04`)** pour ne pas heurter les ajouts de `marc-dev` ; je n'ai pas lance `git merge-tree` contre sa branche, qui n'existe pas encore.
4. Titre, consigne et place de l'entree : a `nadia-prof`.
5. `yanis-qa` relit la branche avant toute fusion (precedent du palier 1).

**Non mesure** : aucun jar lance, aucun ecran joue, aucune cote de pli (a 744 x 551) pour ce paquet ; le garde-fou de l'accent n'a ete lance que dans le reacteur ; `git merge-tree` contre `-05` non lance ; la CI distante n'est pas lue ; l'orthotypographie des textes de `nadia-prof` est au `relecteur-pedagogique`.

## Ce que ce rapport ne fait pas

Ne prononce aucun Done, ne fusionne rien, ne pousse rien, ne juge ni le titre ni la consigne, ne decide pas du sort de `re-`.

## Decisions prises

**Aucune `ADR` ni `DEC` n'est creee ou amendee.**
