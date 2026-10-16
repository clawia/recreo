# [Sprint s57] Rapport qualite de `yanis-qa` - palier 2, garde de `C1'` point 1 (`S57-GARDE-C1-SUJET-HORS-MESSAGE`, criteres 1 a 5)

- **Auteur** : `yanis-qa`. **Date** : 2026-10-16. **Arbre mesure** : branche `s57-theo-garde-c1`, tete `d9c6cc69` (`b0c47c97` : garde et amendement d'`ADR-0031` ; `d9c6cc69` : `TL9`, `TL10`), base `9c271e14`. Deux copies hors worktree (`git archive`) : une de `d9c6cc69`, une de `9c271e14` (arbre sans la garde, pour le differentiel). Le worktree de `theo-archi` n'a pas ete modifie. **JDK** : 21.0.10, relu dans `java.version` du rapport Surefire et dans la premiere ligne de `outils/mvn-jdk21.sh`.
- **Ce que ce fichier ne fait pas** : il ne prononce ni Done technique ni Done pedagogique, ne fusionne rien, ne corrige rien. Il ne juge pas les criteres 6 a 8 de la story (la seule mesure de ce fichier sur eux : section 4).
- **Verdict** : criteres 1, 2, 4, 5 **tenus** ; critere 3 **non garde, dit** ; **un trou non liste par `theo-archi`** (section 3, `Z1`) : une regle dont la virgule est dans un `:has(...)` ou un `:is(...)` passe tout `adapter-web` (501 tests, 0 echec) ; **un fait sur le critere 1** (section 2.1) : la regle de `TP4` ecrite mot pour mot est deja rouge sur `9c271e14`, sans la garde.

## Resume

| Code | Constat | Etat |
|---|---|---|
| F1 | Critere 1 : la regle de `TP4` fait echouer `UnEcranNeGouvernePasLApparenceDUnAutreTest` et son message cite `.est-faux`. **Mais** la meme regle, mot pour mot, fait echouer `LaFormeCommuneDesPropositionsLonguesEstEpingleeTest` sur `9c271e14`, sans la garde : sur ce socle, la regle litterale ne prouve pas la garde | tenu, preuve a refaire par un autre motif (2.1) |
| F2 | Critere 2 : les 4 noms, plus 6 autres jetons du message, un par un : rouge a chaque fois, jeton cite. Differentiel sur la forme sans `@media` ni `:not(...)` : `9c271e14` 0 echec, `d9c6cc69` 1 echec, pour les 4 noms | tenu |
| F3 | Critere 3 : `data-oeuvre-revelee` n'est pas garde, l'`ADR` le dit (5 occurrences) et la Javadoc le dit | non garde, dit |
| F4 | Critere 4 : feuille telle quelle, 0 echec (classe 7, module 501, reacteur 2645) ; avec `candidat-r3.css`, 0 echec | tenu |
| F5 | Critere 5 : classe neuve sur le message dans `manche.html` + regle : rouge, aucun fichier de test edite ; deux temoins | tenu |
| F6 | **`Z1`** : `.ecran-de-jeu:has(.consigne, .est-faux) .propositions { row-gap: 0; }` passe : 501 tests, 0 echec. Le meme sans la virgule echoue | **trou, a router** |
| F7 | `Z2` (CSS imbrique `&:has(...)`) passe : 501, 0 echec | trou, a router |
| F8 | Reacteur complet `bash outils/mvn-jdk21.sh -B clean verify` : `BUILD SUCCESS`, 0 echec | constate |
| F9 | Pas de faux rouge : 15 regles legitimes (sujet dans le message, ou dehors sans nommer le message) ajoutees ensemble : 0 echec | constate |

## 1. Methode

- **Copie, pas worktree.** Le code de `d9c6cc69` est extrait dans un repertoire de mon espace de travail. Les fichiers de test, les gabarits et les autres feuilles ne sont jamais modifies : `diff -r` sur `adapters/` (hors `target`) entre le worktree de `theo-archi` et la copie rend vide avant et apres les mutations. Seuls `recreo.css` et `fragments/manche.html` sont mutes. Un fichier de contre-exemple differait par ses fins de ligne (extraction avec conversion CRLF) ; il a ete recopie depuis le worktree avant le reacteur.
- **Motif exige.** Chaque mutation passe par un script qui **refuse** si le motif n'est pas trouve le bon nombre de fois : ajout en fin de feuille (le texte ajoute est absent avant : 0, present apres : 1) ; remplacement dans `manche.html` (motif present **exactement 1 fois**). Aucun refus n'a ete rencontre.
- **Restauration verifiee.** Apres chaque mutation, le fichier est recopie depuis la reference puis compare octet a octet (`cmp`) ; empreintes SHA-256 de reference (16 premiers caracteres) : `recreo.css` `e1cd65c255a5142e`, `manche.html` `b50fe7ac86ab740b`. Le script affiche `RESTAURE identique` apres chaque lancement ; je l'ai lu sur les lots non filtres, et mes filtres de lecture des autres lots le laissaient passer sans le montrer. La verification qui tient est la derniere : `cmp` de `recreo.css` et de `manche.html` contre la reference, rendu identique juste avant le reacteur, puis `diff -r` des deux arbres `adapters/` vide hors `target`. 55 lancements sur la copie de `d9c6cc69` (hors `Y0` et reacteur), 8 sur la copie de base. Apres chaque mutation de feuille, le `recreo.css` de `target/classes` est compare a la source mutee : `cible==source` (la mutation a bien ete jouee, pas une classe compilee d'avant).
- **Commandes.**
  - Classe : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-web -am test -Dtest=UnEcranNeGouvernePasLApparenceDUnAutreTest -Dsurefire.failIfNoSpecifiedTests=false` - perimetre `adapter-web` et ses amonts, une classe, 7 tests.
  - Module : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-web -am test` - perimetre `adapter-web` et ses amonts : domain 120, application 31, adapter-web **501** sur `d9c6cc69` ; **496** sur `9c271e14` (delta +5 : les 4 tests de la garde et 1 de `TL9`).
  - Reacteur : `bash outils/mvn-jdk21.sh -B clean verify` depuis la racine de la copie de `d9c6cc69`, sans `-pl`.
- **Jetons des mutations.** Tous les noms viennent des gabarits (`fragments/manche.html`, lignes 192 a 218) : `.message-jeu`, `.signal`, `.est-juste`, `.est-faux` (nommes par `C1'`), puis `.verdict`, `.reponse-attendue`, `.explication`, `.icone-signal`, `.indice`, `.reponse-revelee` (choisis par moi dans le message).

## 2. Mutations des criteres 1 a 5

Colonnes : **Classe** = classe de la garde seule (7 tests) ; **Module** = `adapter-web` entier (501) ; **Base** = `9c271e14`, module entier (496). `TP4` = `.ecran-de-jeu:has(X):not(.ecran-deux-oeuvres) .propositions { row-gap: 0; }` sous `@media (min-width: 720px)`. `N` = `.ecran-de-jeu:has(X) .propositions { row-gap: 0; }`, sans `@media` ni `:not(...)`.

| Code | Mutation (ajout en fin de `recreo.css` sauf mention) | Classe | Module | Base |
|---|---|---|---|---|
| `Y0` | aucune | 0 echec | 0 echec (501) | 0 echec (496) |
| `Y1` | `TP4` avec `X = .est-faux` (critere 1) | **1 echec**, cite `est-faux` | 2 echecs : la garde (cite `est-faux`) et `LaFormeCommuneDesPropositionsLonguesEstEpingleeTest.laFormeCommuneEstCelleQuiAEteMesureeSurLaProduction` | **1 echec : le test d'epinglage, pas la garde** (le message cite le selecteur, donc `.est-faux`) |
| `Y1n` | `N` avec `X = .est-faux` | 1 echec, cite `est-faux` | **1 echec : la garde** | **0 echec** |
| `Y2a` | `TP4`, `X = .message-jeu` | 1 echec, cite `message-jeu` | 2 echecs (garde + epinglage) | non rejoue |
| `Y2b` | `TP4`, `X = .signal` | 1 echec, cite `signal` | 2 echecs | 1 echec (epinglage) |
| `Y2c` | `TP4`, `X = .est-juste` | 1 echec, cite `est-juste` | 2 echecs | non rejoue |
| `Y2d` | `TP4`, `X = .est-faux` (= `Y1`) | 1 echec | 2 echecs | 1 echec (epinglage) |
| `Y2an` a `Y2dn` | `N` avec les 4 noms, un par un | non rejoue | **1 echec chacune : la garde, cite le jeton** | **0 echec chacune** |
| `Y2e` a `Y2j` | `TP4` avec `.verdict`, `.reponse-attendue`, `.explication`, `.icone-signal`, `.indice`, `.reponse-revelee` | **1 echec chacune**, cite le jeton | non rejoue | non rejoue |
| `Y3` | `.ecran-de-jeu:has([data-oeuvre-revelee=true]):not(.ecran-deux-oeuvres) .propositions { row-gap: 0; }` (critere 3) | **0 echec** | 1 echec : l'epinglage de la forme, **pas la garde** | non rejoue |
| `Y3b` | meme regle sans `:not(...)` | non rejoue | **0 echec** (501) | non rejoue |
| `Y4` | texte de `candidat-r3.css` ajoute a la feuille (critere 4) | 0 echec | 0 echec (501) | non rejoue |
| `Y5` | `manche.html` : `class="message-jeu"` devient `class="message-jeu jeton-yanis"` (motif present 1 fois) + `TP4` avec `.jeton-yanis` (critere 5) | **1 echec**, cite `jeton-yanis` | 2 echecs (garde + epinglage) | non rejoue |
| `Y5b` | la classe va sur `<p class="verdict">` (descendant du message, motif 1 fois) + meme regle | **1 echec**, cite `jeton-yanis` | non rejoue | non rejoue |
| `Y5c` | temoin : la classe neuve dans `manche.html`, sans regle | 0 echec | non rejoue | non rejoue |
| `Y5d` | temoin : la regle `jeton-yanis` sans la classe dans le gabarit | 1 echec, **un autre test** (`aucuneRegleMaisonNeReunitDeuxEcransQuiNeSAffichentJamaisEnsemble`), pas la garde | non rejoue | non rejoue |

Aucun fichier de test n'est edite dans `Y5`, `Y5b`, `Y5c`, `Y5d`. `Y5c` et `Y5d` montrent que le rouge de `Y5` vient de la combinaison (classe posee par le gabarit et regle qui la nomme), et que c'est la garde qui le porte.

### 2.1 Critere 1 : un fait sur le socle

Le critere dit que la regle de `TP4` ecrite mot pour mot rend un rouge **que la garde apporte**. Sur `9c271e14`, qui contient `LaFormeCommuneDesPropositionsLonguesEstEpingleeTest` (fusion de p2-7) et pas la garde, cette regle est **deja rouge** : toute regle dont le selecteur nomme `:not(.ecran-deux-oeuvres)` entre dans la population de l'epinglage et n'est pas une regle attendue. La mesure `Aujourd'hui : BUILD SUCCESS` de la story date de `aa9d703c`, avant ce test. Consequences :

1. La regle litterale de `TP4` ne discrimine plus la garde au niveau du module. Elle la discrimine au niveau de la classe (`Y1`, colonne Classe).
2. La forme qui discrimine est `Y1n` / `Y2an` a `Y2dn` : 0 echec sans la garde, 1 echec avec. C'est la preuve que la garde ferme la regle, et elle vaut pour les quatre noms du critere 2.
3. La garde reste necessaire : une regle qui reprend la forme de `TP4` sans nommer l'exclusion, ou sur un autre sujet, ne passe par aucun autre test (`Y1n`).

Proposition (a `camille-po`, via `sofia-sm`) : amender la commande du critere 1 pour ajouter la forme sans `:not(...)` ; ce n'est pas mon texte.

## 3. Cas que `theo-archi` n'a pas listes

Mesures sur `d9c6cc69`, classe puis module quand la classe est verte.

| Code | Mutation | Classe | Module | Lecture |
|---|---|---|---|---|
| `Z1` | `.ecran-de-jeu:has(.consigne, .est-faux) .propositions { row-gap: 0; }` | 0 echec | **0 echec (501)** | **TROU.** Meme regle sans virgule (`Y1n`) : echec |
| `Z1a` | `:has(.cabinet, .est-faux)` sans `:not(...)` | 0 echec | 1 echec : `CabinetDeLaMachineTest.chaqueSelecteurEpingleEstEprouveParLeJeuFautif` | rouge **par hasard** : le fragment `:has(.cabinet` est lu par un test sur `.cabinet` |
| `Z1c` | `.ecran-de-jeu:is(.est-faux, .cabinet) .propositions` | 1 echec, la garde cite `est-faux` | non rejoue | rouge : le jeton est dans le premier fragment |
| `Z1d` | `TP4` avec `:has(.cabinet, .est-faux)` (avec `@media` et `:not(...)`) | 1 echec, la garde cite `est-faux` | non rejoue | rouge, mais le fragment cite est `.est-faux):not(.ecran-deux-oeuvres) .propositions` : l'accident de parentheses du decoupage fait lire un second compose, ce n'est pas la regle qui est vue |
| `Z2` | `.ecran-de-jeu { &:has(.est-faux) .propositions { row-gap: 0; } }` (CSS imbrique) | 0 echec | **0 echec (501)** | **TROU.** Un navigateur actuel applique la regle |
| `Z3` | `.score-joue-nombre:has(~ .signal.est-faux) { opacity: 0.9; }` (sujet dans `retour.html`, sans region d'annonce) | 0 echec | 0 echec (501) | limite 4.a de l'`ADR`, dite : `retour.html` pose `.signal` hors region |
| `Z4a` | `manche.html` : `role="status"` remplace par `aria-live="polite"`, aucune regle | 0 echec | non rejoue | aucun test ne voit la region disparaitre d'un seul gabarit (dit par l'`ADR`) |
| `Z4b` | `Z4a` + `N` avec `.est-faux` | non rejoue | **0 echec (501)** | la garde sort `manche.html` du controle : dit par l'`ADR`, mesure ici |
| `Z4c` | `Z4a` + `TP4` avec `.est-faux` | 0 echec | 1 echec : l'epinglage seul | meme constat, la garde ne dit rien |
| `Z5` | `.ecran-de-jeu:has([class*=faux]) .propositions { row-gap: 0; }` | 1 echec, **un autre test** (`aucuneRegleMaisonNeReunitDeuxEcransQuiNeSAffichentJamaisEnsemble`) | non rejoue | rouge, mais par la clause de 2026-08-11 : le jeton `faux` n'est pose par aucun ecran. Une sous-chaine qui serait un jeton pose par le message serait vue par la garde |
| `Z6` | `:has([class~=est-faux])`, `:not(:has(.est-faux))`, `:has([role=status])`, `@supports (...) { ... :has(.est-faux) ... }`, `@layer rien { ... }`, `.message-jeu + * { }`, `.cabinet:has(~ .message-jeu .est-faux)`, `.propositions:has(+ .message-jeu .est-faux)` | **1 echec chacune**, la garde cite le jeton | non rejoue | rouge : formes voisines tenues |
| `Z7` | 15 regles legitimes ajoutees ensemble : `.message-jeu .signal.est-faux .verdict`, `.ecran-de-jeu .message-jeu:has(.est-faux) .indice`, `.cabinet ~ .message-jeu .signal.est-juste .icone-signal`, `.ecran-de-jeu > .message-jeu`, `.zone-jeu .message-jeu .reponse-attendue`, `.propositions + .message-jeu`, `.signal.est-faux + .indice, .signal.est-juste ~ .explication`, `.ecran-de-jeu:has(.cabinet) .message-jeu .signal.est-faux`, `@media (min-width: 720px) { .ecran-de-jeu:not(.ecran-deux-oeuvres) .message-jeu .signal }`, `[role=status] .signal.est-faux`, `.message-jeu:not(:has(.est-faux))`, `.est-faux .icone-signal`, `.ecran-de-jeu:has(> .message-jeu) .message-jeu`, `.verdict`, `body .message-jeu .signal.est-faux` | **0 echec** | non rejoue | pas de faux rouge sur ces formes |

**Cause de `Z1`, lue dans le code.** `LAnimationRetardeMaisNeDetermineRienTest.regles(...)` decoupe l'en-tete de chaque regle sur toute virgule (`entete.split(",")`, ligne 217), parentheses comprises. Pour `...:has(.consigne, .est-faux) .propositions`, la garde recoit deux selecteurs : `.ecran-de-jeu:has(.consigne` et ` .est-faux) .propositions`. Le second est lu comme un seul compose, `.est-faux`, qui est un jeton propre au message : le sujet est tenu pour **dans** le message, et rien n'est refuse. La garde juge ce que `regles(...)` lui donne ; ce decoupage est partage avec cinq autres tests du module et est anterieur a la garde. Le defaut est donc a l'interface entre le decoupeur et `placeDuSujet`, ou dans le decoupeur lui-meme.

**A router (a `sofia-sm`, qui route a `theo-archi`)** : `Z1` et `Z2` passent la garde et tout `adapter-web`. Je ne propose pas de correction. Si le decoupeur est corrige, la mise a l'epreuve (`chaqueClasseDuMessage...`) ne les couvre pas aujourd'hui : elle n'ecrit que `.sujet:has(.classe)`, sans virgule ni imbrication.

## 4. Verdicts par critere

| Critere | Verdict | Preuve |
|---|---|---|
| 1. Regle de `TP4` | **tenu** pour la garde seule ; **non discriminant au module sur ce socle** | `Y1` classe : 1 echec, cite `.est-faux` ; `Y0` : 0 echec. Au module, la meme regle est rouge sur `9c271e14` par l'epinglage (2.1). `Y1n` : base 0, tete 1 |
| 2. Les 4 noms, un par un | **tenu** | `Y2a` a `Y2d` classe : 1 echec chacune, jeton cite ; `Y2an` a `Y2dn` : base 0, tete 1 ; plus 6 autres jetons (`Y2e` a `Y2j`) |
| 3. Le residu, dit | **non garde, dit** | `Y3` : 0 echec a la classe, `Y3b` : 0 echec au module. `grep -c "data-oeuvre-revelee" squad/decisions/adr/ADR-0031-*.md` rend **5** (la story exige au moins 1) ; la Javadoc de la classe de test le nomme une fois (*"Ce que cette clause ne garde pas"*, avec `TP6`) et dit que les points 2 et 3 de `C1'` ne sont pas gardes |
| 4. Ce qui ne doit pas rougir | **tenu** | `Y0` classe 0, module 0, reacteur 0 ; `Y4` module 0 ; `Z7` classe 0. Les lignes 1569 (`.signal.est-juste .icone-signal`), 1817 (`.propositions-oeuvres:has(.proposition.deja-essayee)`) et 1843 sont presentes dans la feuille mesuree. Clause : mord quand la garde sur-detecte |
| 5. Perimetre derive | **tenu** | `Y5`, `Y5b` : rouge, aucun fichier de test edite ; `Y5c` (classe sans regle) 0 echec, `Y5d` (regle sans classe) rouge par un autre test : le rouge de `Y5` tient a la classe posee par le gabarit |

Criteres 6 et 7 (seule mesure de ce fichier) : `git diff --stat 9c271e14 d9c6cc69 -- adapters/adapter-web/src/main adapters/adapter-content domain application bootstrap` est vide ; `git show --stat b0c47c97` nomme `ADR-0031-...md` et `UnEcranNeGouvernePasLApparenceDUnAutreTest.java`. Compte ArchUnit sur l'arbre mesure : `git grep -hcE "static final ArchRule"` somme 41, `git grep -hcE "@ArchTest"` somme 38 (inchange par rapport a l'`ADR`). Je ne juge pas ces criteres.

## 5. Reacteur et jobs de `ci.yml`

- **Job `build`** (contenu : `mvn -B clean verify`) : `bash outils/mvn-jdk21.sh -B clean verify`, racine de la copie de `d9c6cc69`, sans `-pl` : **`BUILD SUCCESS`**, 4 min 41. Tests par module (lignes `Tests run:` du journal, 0 echec, 0 erreur, 0 ignore) : `domain` 120, `application` 31, `adapter-web` 501, `adapter-content` 1606, `bootstrap` 381 en surefire et 6 en failsafe. Total **2645**, somme de ces lignes. `adapters/` est identique au worktree de `theo-archi` (`diff -r` vide hors `target`) ; les autres modules viennent du meme commit par `git archive`.
- **Job `outils`** : les 13 juges purs (`outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py`) lances sur la branche `s57-yanis-p2-garde` (base `round-two` `9f110983`) : vert. Pas lances sur l'arbre de `theo-archi`.
- **Job `chemins`** : `bash .github/scripts/verifier-chemins-test.sh` puis `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' | bash .github/scripts/verifier-chemins.sh`, ce fichier indexe : sortie 0 des deux.
- **Job `prefixes`** : `verifier-prefixes-test.sh` sortie 0 ; `verifier-prefixes.sh` sur le message de ce commit sortie 0. Les etapes `commit-atteignable-test.sh`, `verifier-garde-fou-accent-test.sh` et `verifier-garde-fou-accent.sh` ne sont **pas** lancees : le job `prefixes` n'est donc que partiellement rejoue, et je ne l'annonce pas vert.
- La Done technique du commit de `theo-archi` est celle de sa CI. Ce rapport constate un `build` vert sur une copie ; il ne juge pas la CI de la branche.

## 6. Ce qui est couvert, ce qui ne l'est pas

- **Couvert** : sujet dans le message ou hors de lui pour une classe nommee dans `:has(...)`, `:not(...)`, `:is(...)` (premier fragment), `[class~=...]`, `@media`, `@supports`, `@layer`, combinateurs `+` et `~` ; jeton neuf pose par un gabarit sur le sommet ou un descendant du message.
- **Non couvert, mesure** : `Z1`, `Z2`, `Z3`, `Z4b`, `Z4c` ; `data-oeuvre-revelee` (`Y3`, `Y3b`) ; points 2 et 3 de `C1'` (dit par `theo-archi`, `G9`, non rejoue ici) ; un verdict qui passe par le gabarit (limite 4.b, hors critere : la story dit "regle de feuille").
- **Non mesure** : la regle de `TP4` sur `Y2a`, `Y2c` et `Y5` a la base ; les cinq tests de la classe de `TL9` ne sont pas mutes ici.
- **Tests instables** : aucun constate. Aucun test n'a ete relance pour obtenir un resultat : chaque lancement de ce fichier est une mesure unique.
- **Done pedagogique** : aucun propre a cette story ; ce fichier n'y touche pas.
