# Rapport qualite du palier 4, lot LP4 au sommet 1152fdc9, et mesures de LP1 et LP2

Auteur : `yanis-qa`, transverse. Date : 2026-10-22. Branche du lot : `s57-sofia-emc-ce1-02`, sommet `1152fdc9` (corrige apres le refus de `nadia-prof`), base `eb267ba7`. Ce rapport remplace, pour le sommet courant de LP4, `rapport-qualite-palier-4-lp4.md` (sommet `9f3fdd4b`). Mesures faites dans mon worktree `s57-yanis-p4`, mis sur chaque sommet nomme (`git checkout --detach`), propre avant chaque mesure. Les worktrees des lots n'ont pas ete touches.

**Ce rapport ne prononce pas le Done pedagogique** (`nadia-prof`, seule). Il nomme section 4 les jobs de la CI que j'ai lances.

## 1. Resultat

1. LP4 `1152fdc9` : `bash outils/mvn-jdk21.sh -B clean verify`, **exit 0, 2922 tests, 0 echec, 0 erreur, 0 ignore** (+14 contre 2908 de `9f3fdd4b`, mesure precedente).
2. Les gardes neufs mordent, sauf quatre trous nommes section 3 : sonde `poser`, trois sondes neuves de formulation du refus, indices `chaque enfant` et `chacune`, question de la cible faute non gardee.
3. G1 (sanction = reparation) est ancre et mord sur les sept items qu'il ne voyait pas, **sauf sur `poser`**. G2 (liste etendue) mord sur les trois sondes du premier rapport et **pas sur trois sondes neuves**. G3 : marge de 4, mesuree.
4. LP1 `24a9315b` : verify exit 0, **2895**. LP2 `c29d1d26` : verify exit 0, **2895**. Chacun +40 contre 2855 ; **ce ne sont pas des totaux apres fusion** (section 5).
5. Jobs de la CI : les quatre sont lances, au sens des etapes de leur job, section 4.

## 2. Job `build` au sommet `1152fdc9`

Commande : `bash outils/mvn-jdk21.sh -B clean verify`, racine, sans `-pl`, sous le jeton de `verify` (RF1, `mkdir` reussi au premier essai, repertoire supprime a la fin, racine du scratchpad relue sans jeton). Exit 0, `BUILD SUCCESS`, 480 s mur (Maven : 07:52).

| Module | Tests (lignes `Tests run`) |
|---|---|
| domain | 120 |
| application | 31 |
| adapter-web | 523 |
| adapter-content | 1861 |
| bootstrap | 381 |
| integration (Failsafe) | 6 |
| **Total** | **2922** |

Methode : somme des six lignes de fin de module, recomptee par l'attribut `tests` des 320 fichiers `TEST-*.xml` (2922, 0 echec, erreur ou ignore). JDK 21.0.10, propriete `java.version` des 320 rapports. Delta : **+14 contre `9f3fdd4b`, tout dans adapter-content** (1847 a 1861), perimetre : reacteur complet. Il s'accorde avec l'annonce de `sofia-sm` (2922, +14).

## 3. Mutations des gardes neufs de `PaquetCe1EmcRegleSanctionTest` (sommet `1152fdc9`)

**Methode.** Une mutation du paquet `ce1-emc-regle-sanction.yaml` (donnees reelles, pas les jeux fautifs commites), puis
`bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am -Dtest=PaquetCe1EmcRegleSanctionTest -Dsurefire.failIfNoSpecifiedTests=false test`
(perimetre : classe seule, 67 tests, `-am` present), verdict lu dans le XML Surefire, mutation annulee par `git checkout -- <fichier>` et `git status --short` vide a chaque fois. 35 executions. Les mutations M01 a M40 du premier rapport ne sont pas rejouees ici, hors les trois affirmations d'en-tete et les sondes G1 et G2 demandees.

| Id | Garde | Mutation | Resultat |
|---|---|---|---|
| N1a, N1b, N1c | jeu de cibles (FER-1) | deux items faute, regle ou sanction supprimes | rouge, motif : "FER-1 : au moins trois items de cible ..." |
| N2 | cible faute | bonne reponse d'un item faute devient "la regle" | rouge, motif : "n'est pas celle que la cible commande" |
| N3 | familles, cible faute | distracteurs d'un item faute permutes | rouge, motif : familles declarees different des familles lues |
| N4a | familles tache 2 | "a venger Samir" devient "a se venger de Samir" | rouge, motif : famille lue `?` (le pli rougit aussi, texte plus long) |
| N4c | familles tache 2 | un item rappel avec deux distracteurs de famille g | rouge, motif : "que sa cible n'admet pas" |
| N5a | "expres / sans demander" | "expres" retire (Hugo) | rouge, motif : "peut se lire comme un accident" |
| N5b | idem | "sans demander" retire (Mila) | rouge, meme motif |
| N6, M45 | adulte de l'indice = celui de l'enonce | indice de l'item 8 nomme "Le maitre" sous un enonce "La maitresse" ; M45 : toutes les "La maitresse" deviennent "Le maitre" | rouge, motif : "nomme un autre adulte". M45 rougit **par ce garde** et non par une repartition 13 et 11, que l'en-tete du paquet ne revendique plus |
| N7a | pli, propositions | somme de 58 caracteres (limite) | **vert, comme attendu** |
| N7b | pli, propositions | 59 caracteres | rouge, motif : "au plus 58 ... deux rangees" |
| N7c | pli, indice | 46 caracteres (limite) | **vert, comme attendu** |
| N7d | pli, indice | 47 caracteres | rouge, motif : "plus de 46 caracteres, une ligne au plus" |
| N8a | G1 ancre | item 2 (sanction) : "Le maitre lui fait faire cent pompes", question "Nettoyer la table" inchangee | rouge (ce cas etait vert au premier rapport, M28b), motif : la phrase de l'adulte ne porte plus de geste, et la question ne le reprend plus |
| N8b | G1 ancre | item 4 (cite "prendre") : "le rendre" devient "deux pompes" | rouge (vert au premier rapport, M28c) |
| N8c | G1 ancre | item 1 (regle) : "recoller" devient "copier" | rouge |
| N8e | G1 | l'enfant est envoye chez la directrice | rouge |
| **N8d** | G1 | item 2 : "poser une question difficile" | `rsa1LesSanctionsDesSituationsSontDesReparations` **reste vert** (seul le garde de la question rougit, par effet de bord) |
| **N8f** | G1 | item 1 (regle) : "La maitresse lui fait poser une question" | **vert, 67 sur 67. Le garde ne mord pas.** Le motif neuf `pos(er\|e)` accepte tout "poser" comme reparation |
| N9a | question reprend le geste | item 2 : "Nettoyer la table, c'est quoi" devient "Cela, c'est quoi" | rouge, motif : "ne reprend pas le geste" (verte au premier rapport, M41) |
| **N9b** | idem, cible faute | item 3 : "Salir un mur, c'est quoi" devient "Cela, c'est quoi" | **vert, 67 sur 67.** Le garde ne lit que la cible sanction ; l'en-tete du paquet ecrit "le geste ou l'acte demande est repris ... dans la question" |
| N10a, N10b, N10c | G2 etendue | "tire l'oreille", "sans dessert", "gronde tous les eleves" (les trois sondes du premier rapport) | rouge, motif : "mot refuse d'avance" (vertes au premier rapport) |
| **N10d, N10e, N10f** | G2 | trois sondes neuves : "devant les autres" (humiliation), "le maitre le tape" (punition physique), "sans diner" (privation de repas) | **vertes, 67 sur 67. Le garde ne mord pas.** |
| N11a | G3, marge | bonnes reponses strictement les plus longues portees de 8 a 12 sur 24 (limite) | `eb7` **vert**, comme attendu ; le pli rougit (les mutations rallongent le texte) |
| N11b | G3 | portees a 13 | `eb7LaBonneReponseNEstStrictementLaPlusLongueQueDansLaMoitieDesItemsAuPlus` rouge |
| N12a, N12d | indices du CP | "tout le monde", "tous" | rouge, motif : "donne le critere du CP" |
| **N12b, N12c** | indices du CP | "chaque enfant", "Chacune doit le savoir" | **verts, 67 sur 67.** La liste est `tous / tout le monde / chaque eleve / chacun` |

G3 : la marge est de **4** (8 sur 24 aujourd'hui, limite 12), mesuree par N11a et N11b ; `sofia-sm` annonce 8 sur 24, je le retrouve par calcul sur le YAML de `1152fdc9`.

### Ce qui ne mord toujours pas

1. **G1, `poser`** (N8d, N8f) : le motif `pos(er|e)` ajoute pour couvrir "la reposer" accepte n'importe quelle phrase de l'adulte contenant "poser" ou "pose". Un item de cible regle ou faute dont la phrase de sanction serait "poser une question difficile" passe.
2. **G2, formulations** (N10d a N10f) : la liste est lexicale, chaque extension ferme les sondes connues et laisse les voisines. Le test l'ecrit lui-meme ; la lecture de `nadia-prof` reste la garde du reste.
3. **Indices du CP** (N12b, N12c) : "chaque enfant" et "chacune" ne sont pas dans la liste.
4. **Question de la cible faute** (N9b) : non gardee, alors que l'en-tete le revendique.

Hors perimetre de ces mutations : la limite de lecture des indices (`methode, pas reponse`) et du registre, que le test declare ne pas lire.

## 4. Jobs de `.github/workflows/ci.yml` lances

| Job | Etapes | Commande et resultat |
|---|---|---|
| `build` | "Verify" | `bash outils/mvn-jdk21.sh -B clean verify` : exit 0 (section 2) |
| `build` | "Package du fat jar" | `bash outils/mvn-jdk21.sh -B clean -pl bootstrap -am package` (enveloppe du poste, `clean` en plus), sous le jeton : exit 0, 252 s, `bootstrap/target/recreo.jar` present. Le `package` rejoue les tests des modules de la chaine : 2916 tests, 0 echec (perimetre bootstrap et ses dependances, sans les 6 d'integration) |
| `build` | "Verifier la presence du build Maven" | **non lancee** (teste `pom.xml`) |
| `prefixes` | tester prefixes, tester plage, verifier prefixes sur `eb267ba7..1152fdc9` (sans fusions), tester controle de l'accent, verifier proprietaire de l'accent sur la meme plage | **tous lances, exit 0** (le dernier : 0 commit touche le garde-fou, tous `[THEO]`) |
| `chemins` | tester le garde-fou, verifier l'absence de chemins sur `git ls-files` | **lances, exit 0 et 0**, sur l'arbre de `1152fdc9` |
| `outils` | balayage des motifs `outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py` | **lance, exit 0, 24 juges, aucun motif vide** ; l'interpreteur Python est celui du poste, pas `python3` (le `python3` du poste est un faux executable) |

Ecarts a la CI : la plage de `prefixes` est celle du lot (`BASE..SHA` d'un push est celle que la CI verra), l'interpreteur et l'enveloppe sont ceux du poste, le reste est la commande de l'etape. **Porte technique de LP4 au sommet `1152fdc9` : les etapes de `build` (hors "Verifier la presence du build Maven", triviale), `prefixes`, `chemins` et `outils` sont vertes sur ce que j'ai lance.** Elle vaut pour ce sommet, pas pour la fusion : apres fusion, `sofia-sm` rejoue ce que le RF4 prescrit.

## 5. LP1 et LP2, mesure par `verify` complet

Commande, pour chacun : `bash outils/mvn-jdk21.sh -B clean verify`, racine, sans `-pl`, sous le jeton, un a la fois, sur mon worktree mis au sommet nomme, propre.

| Lot | Sommet | Exit | Duree | domain | application | adapter-web | adapter-content | bootstrap + integration | Total |
|---|---|---|---|---|---|---|---|---|---|
| LP1 (`ce1-mots-invariables`) | `24a9315b` | 0 | 277 s | 120 | 31 | 523 | 1834 | 387 | **2895** |
| LP2 (`ce1-masses-contenances`) | `c29d1d26` | 0 | 262 s | 120 | 31 | 523 | 1834 | 387 | **2895** |

Methode : attribut `tests` des 320 `TEST-*.xml` de chaque mesure, par module (bootstrap : 381 Surefire et 6 Failsafe), 0 echec, erreur ou ignore, JDK 21.0.10. **Delta : +40 contre 2855 pour chacun**, tout dans adapter-content (1794 a 1834), conforme aux +40 annonces. Le rapprochement de la base tient sur `git diff --stat 03de0f6d eb267ba7 -- adapters domain application bootstrap pom.xml outils .github`, vide ; je n'ai pas mesure `eb267ba7`. **Ces deux totaux sont des mesures de lots isoles, pas de l'arbre fusionne.** Le total apres fusion des lots de B revient a `sofia-sm`, celui apres les lots de A' a moi, sur l'arbre fusionne. L'addition 2855 + 14 + 40 + 40 n'est pas une mesure : les trois lots touchent des fichiers partages (`LeRang...`, `TexteAccentue...`, `catalogue.yaml`).

Je n'ai pas refait les mutations de LP1 et LP2 ici ; celles du premier rapport (LP1 : chevron sans espace insecable non garde, L10) portent sur `56e9fd1b` et `fc6ee0a1`, pas sur ces sommets.

## 6. Limites

- Une execution de chaque `verify`, sous Windows, JDK 21.0.10, sans rejeu pour juger la stabilite. Aucun test instable observe.
- Couverture chiffree non mesuree.
- Les durees (480, 277, 262 s) sont celles de ce poste a cet instant ; elles ne se comparent pas aux 699 s de `theo-archi` sans expliquer l'ecart, que je n'explique pas.
- Les mutations couvrent les gardes neufs et les sondes demandees ; elles ne prouvent pas l'absence d'autres angles morts.
- Done pedagogique : non prononce ici, a `nadia-prof`.
