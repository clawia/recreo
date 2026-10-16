# s57, palier 2, instance A - audit de `nadia-prof` : p2-3, lot `-05`, `ce1-pronoms` (`FRA-CE1-05`)

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : 2026-10-16, 16:18 (UTC+02:00), lu par `date`.
- **Arbre** : branche `s57-nadia-p2-a2`, partie de `round-two` a `e8d14ddb`, qui contient la fusion du lot (`edcd3de9`, lot
  `b9f1c62f`, rapport `cb50740a`). Fiche de `camille-po` prise par fusion de `s57-camille-p2-fiches` (`610bcba1`, docs seules).
- **Audit joue** : jar du code de `e8d14ddb`, joue a 744 x 551.

## Resume

| Poste | Jar joue | Verdict | Fiche |
|---|---|---|---|
| p2-3, lot `-05` | code de `e8d14ddb` ; paquet egal a celui de `b9f1c62f` | **REFUSE**. Motif unique : `TRV-CE1-6`, roles distribues par genre. `FRA-CE1-05` reste `non couvert` | `squad/conformite/FICHE-bandit-manchot-pronoms-ce1.md`, verdict du 2026-10-16 |

**Le motif** : sur les huit items ou un garcon et une fille sont en relation, aucun ne montre une fille qui agit envers un
garcon ; sur sept, le garcon donne, lit, parle ou ouvre, la fille recoit, remercie, ecoute, sourit, ou range pour lui. La
condition (`ARBITRAGE-PREALABLE-s48-francais-ce1.md`, section 2) interdit cette regularite.

**Ce qui le leve** : cinq items reecrits, textes exacts dans la fiche, section 5 (items 3, 9, 13 et 18 pour les roles ; item 16,
*Hamza* remplace par *Omar*, parce que seul ce prenom dit qui est *le garçon*). Rejeu a 0,5.

**Tout le reste est tenu** : ancrage a `FRA-CE1-05`, `PRN-CE1-1` a `-3` (10 items sur 20 ou le sens decide), 20 referents justes
sur 20, `PN4` a 0, longueurs, indices, items 10, 13 et 15 corriges, titre, consigne, place de l'entree, commentaire de tete
exact pour la table du test. Detail dans la fiche, section 2.

## 1. Ce que j'ai lance

| | Valeur |
|---|---|
| Jar | `bash outils/mvn-jdk21.sh -B clean package -DskipTests`, `BUILD SUCCESS` ; paquet du jar egal a `b9f1c62f` et `e8d14ddb` |
| Port 18511, libre avant | journal *"Tomcat started on port 18511"*, PID 34004, arrete par ce PID seul |
| Chrome | profil dans mon dossier de poste, port de debogage 19211, sorti seul ; aucun arret par nom d'image |
| Parties | 10 de dix manches ; 20 items sur 20 joues deux fois en erreur, les deux distracteurs presses ; 40 revelations ; 0 ecart |
| Pli, 744 x 551, apres une erreur | **20 sur 20** au plus 551 (404 a 508) ; **240 couples (item, ordre) sur 240** ; aucune reserve de pli |
| Done technique | non lance par moi ; lu dans le rapport de `marc-dev` et dans `planning.md` (amendement du 2026-10-16 15:27, section 10) |

## 2. Points poses par la demande

- **Item 19, *Nael***, route par moi au `relecteur-pedagogique`, non traite par son releve : **je le ferme, sans correction**.
  La reponse se joue sur le nombre, pas sur la lecture du prenom ; aucune regle du depot ne fixe la graphie d'un prenom. *Naël*
  reste une recommandation non bloquante (fiche, section 6).
- **Titre** *"Il, elle, ils, elles"* et **consigne** (copiee de `ce1-texte-narratif`) : acceptes.
- **Commentaire de tete** (*vingt et un prenoms feminins*) : exact pour la table du test (41 prenoms, *Sacha* compte masculin,
  *Nour* feminin, deux prenoms mixtes).
- ***Julie*** : mon prenom, dans l'equilibre a un pres ; il entre dans le motif (elle n'agit pas a l'item 15), ce que je dis comme
  correction de mon propre constat.
- **Place de l'entree** : derniere du catalogue, voisine de *"Lis et réponds"* sur la page : acceptee.

## 3. Ce que je n'ai pas joue ni mesure

- Le pli a une autre largeur que 744 ; un appareil reel, une autre police, un autre navigateur ; la lecture d'ecran.
- Les textes de correction : ecrits et relus, pas joues. Je les joue au rejeu.

## 4. Routage

| A qui | Quoi |
|---|---|
| `camille-po` | le refus en story, avec les textes de la fiche (section 5) recopies sans reformulation ; `FRA-CE1-05` reste `non couvert` ; statut de tete de la fiche a mettre a jour ; la recommandation de l'item 19 |
| `relecteur-pedagogique` | l'orthotypographie des textes de la section 5 avant le rejeu |
| `sofia-sm` | 1,0 consomme en A (lot `-05`) ; rejeu a 0,5 au palier qui l'engagera ; fusion de `s57-nadia-p2-a2` (docs seules) |

## 5. Jobs de `ci.yml` lances pour ce commit

Sur ce worktree, les fichiers du commit dans l'index, avant commit. Je ne deduis aucun job que je n'ai pas lance.

- **`chemins`** : `verifier-chemins-test.sh` sort 0 ; `git ls-files` (moins le test du garde-fou) passe a `verifier-chemins.sh`,
  sortie 0.
- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, `verifier-garde-fou-accent-test.sh` sortent 0 ; le
  sujet du commit passe dans `verifier-prefixes.sh`, sortie 0.
- **`build`, partiel** : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test` restreint aux quatre tests qui lisent `squad/`
  (`ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`, `AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest`,
  `ConcordanceDesTagsDAgentTest`, `ToutesLesReferencesDeDecisionResolventTest`), perimetre `bootstrap` et ses amonts : `BUILD
  SUCCESS`, 19 tests (10, 5, 1, 3), 0 echec. Le build a lu ce fichier avant cette ligne, qui n'est pas dans un tableau.
  **Ce n'est pas `mvn verify`** : ce commit ne touche ni code ni contenu servi.
- **`outils`** : non lance ; aucun fichier d'outil touche.

Aucun de ces resultats n'est celui de la CI distante. Rien n'est pousse.
