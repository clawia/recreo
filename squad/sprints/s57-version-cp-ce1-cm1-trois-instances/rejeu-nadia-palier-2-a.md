# s57, palier 2, instance A - rejeu de `nadia-prof` : p2-11, `VSJ-CE1-R2`

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : 2026-10-16, 15:58 (UTC+02:00), lu par `date`.
- **Arbre** : branche `s57-nadia-p2-a2`, worktree dedie, partie de `round-two` a `e8d14ddb`. Code servi : celui de `0c0d2142`
  (`git diff --quiet 0c0d2142 e8d14ddb -- adapters domain application bootstrap pom.xml outils` sort 0).
- **Instance** : A (groupe X). **Poste** : p2-11, voie de la forme commune (`Q-S9`), prix 0,25.
- **Audit joue** : jar construit dans ce worktree et joue a 744 x 551.

## Resume

| Poste | Jar joue | Verdict | Fiche |
|---|---|---|---|
| p2-11, `VSJ-CE1-R2` | code de `0c0d2142`, forme commune fusionnee | **`VSJ-CE1-R2` levee.** `FRA-CE1-13` passe a **VALIDE**, aucune reserve ouverte | `squad/conformite/FICHE-bandit-manchot-verbe-sujet-ce1.md`, rejeu du 2026-10-16 |

**Le verdict vaut pour le jar joue.** Une modification de `recreo.css` ou du gabarit de manche peut defaire la levee sans
qu'aucun test ne rougisse : je reste la seule garde de la position de l'indice par rapport au pli (fiche, section 5).

## 1. Ce que j'ai lance

| | Valeur |
|---|---|
| Build | `bash outils/mvn-jdk21.sh -B clean package -DskipTests`, racine du worktree : `BUILD SUCCESS`, toolchain JDK 21.0.10 |
| Feuille servie | SHA-256 apres retrait des retours chariot `c312cd1b...3ef6`, egal a `0c0d2142` et au sommet de la cote |
| Paquet servi | `ce1-verbe-sujet.yaml` du jar egal a celui de `0c0d2142` ; par rapport a `7dc40fb0` (palier 1), deux lignes de commentaire seules |
| Port 18511, libre avant | `netstat -ano` filtre ne rendait rien ; port de debogage 19211, idem |
| Journal | *"Tomcat started on port 18511"*, PID 39296, `java` du poste 21.0.12.1 |
| Arret | `taskkill` sur ce PID seul ; Chrome sur un profil de mon dossier de poste, sorti seul, aucun processus restant sur ce profil ; aucun arret par nom d'image |
| Done technique | non lance par moi ; lu dans `planning.md`, amendement du 2026-10-16 15:27, section 10 (`clean verify` sur `056f8c88`, 2669 tests, 0 echec, code identique) |
| Parties | 10 de dix manches, par l'ecran ; 24 items sur 24 joues deux fois en erreur ; 144 appuis faux, 48 revelations ; 0 ecart contre le paquet |

## 2. Le critere de levee

Critere ecrit le 2026-10-14, inchange : a 744 x 551, apres une erreur, le bas de `.indice` au-dessus de y = 551 sur les 24 items.

- Ordre servi : **24 sur 24**, de 418 a 496.
- Six ordres : **288 couples sur 288**, maximum 496.
- Items 16 et 20, ceux de la reserve : indice 416 - 496, lu en entier sur la capture sans defiler.
- Revelation : hors levee (`Q-R2`). Sur 16 et 20, l'indice descend a 582 sous une reponse revelee visible ; observation, pas
  une reserve.

## 3. La cote de reference

`cote-de-reference-forme-commune-2026-10-16.md`, commit `96f5e101`, fusionne par `056f8c88`, ecrite avant ce rejeu. Pour ce jeu
elle dit *"418 - 496, 24 sur 24"* sur `0c0d2142` contre *"534 - 582, 22 sur 24"* sur la base `aa9d703c` ; ma mesure, prise par
une autre methode de fenetre, rend les memes bornes. Clause `Q-R4` tenue sur ses quatre elements (65 enonces sur 65
d'`additions-cp`). La seconde commande de `Q-R5` n'est pas tenue sur `0c0d2142` ; je dis pourquoi elle n'empeche pas la levee
dans la fiche, section 3 : aucun item ne perd le pli, et la comparaison est mesuree sur le jar que l'enfant aura.

## 4. Ce que je n'ai pas joue

- p2-10, `RCT-CE1-R1` : non engage (`PA7`), non joue.
- Une autre largeur que 744, un appareil reel, une autre police, un autre navigateur, la lecture d'ecran.
- Les conditions que j'ai posees pour p2-8 (score avec `R3-1`, libelles a 720) : elles sont au rejeu de `TPH-CE1-R1`, en A'.

## 5. Routage

| A qui | Quoi |
|---|---|
| `camille-po` | fermer `S57-VSJ-CE1-R2` ; `FRA-CE1-13` reste `couvert-valide`, sa fiche passe a **VALIDE** |
| `sofia-sm` | p2-11 audite, 0,25 consomme en A ; fusion de `s57-nadia-p2-a2` (fiche et ce fichier, aucun code) |

## 6. Jobs de `ci.yml` lances pour ce commit

Sur ce worktree, les deux fichiers du commit dans l'index, avant commit. Je ne deduis aucun job que je n'ai pas lance.

- **`chemins`** : `verifier-chemins-test.sh` sort 0 ; `git ls-files` (moins le test du garde-fou) passe a `verifier-chemins.sh` :
  *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*, sortie 0. Relance apres l'ecriture de cette
  section.
- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, `verifier-garde-fou-accent-test.sh` sortent 0 ; le
  sujet du commit passe dans `verifier-prefixes.sh` : *"Tous les commits portent un prefixe d'agent connu."*, sortie 0.
- **`build`, partiel** : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test` restreint a
  `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`, `AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest`,
  `ConcordanceDesTagsDAgentTest`, `ToutesLesReferencesDeDecisionResolventTest`, perimetre `bootstrap` et ses amonts : `BUILD
  SUCCESS`, 19 tests (10, 5, 1, 3), 0 echec. **Ce n'est pas `mvn verify`** : ce commit ne touche ni code ni contenu servi. Le
  build a lu ce fichier avant cette section, qui ne contient aucun tableau.
- **`outils`** : non lance ; aucun fichier de `outils/` ni de `squad/outils/` n'est touche.

Aucun de ces resultats n'est celui de la CI distante. Rien n'est pousse.
