# Bilan de cloture - chantier hors sprint "accueil par matiere" (DEC-0117)

- **Auteure** : `sofia-sm`, casquette Scrum Master. **Date** : 2026-10-12 09:46 (UTC+02:00), lue par `date`.
- **Ce que ce fichier n'est pas** : ni `planning.md`, ni `review.md`. Aucun sprint n'a ete ouvert (`DEC-0117` section 3), aucune ceremonie n'a eu lieu. C'est un bilan ecrit apres coup, par une tache qui n'a pas assiste au chantier : chaque ligne cite l'ecrit dont elle vient.
- **Fenetre** (horodatages de commit) : 2026-10-11 17:05 (`7341efb0`, `DEC-0117`) a 21:11 (`75290b26`, derniere fusion), soit 4 h 06 min.
- **Base et sommet** : `8ac33440` a `75290b26`, 15 fusions et 23 commits hors fusion (`git rev-list --merges --count` et `--no-merges --count`), dont deux qui ne sont pas du chantier : `13d00bfe` (retrait du jeu refuse de s56) et `d6789099` (`DEC-0116` `A.21` a `A.25`).

## 1. Livre

L'accueil d'un niveau presente ses jeux par matiere : `/` demande d'abord la classe (*"Choisis ta classe."*), `/niveaux/{niveau}` rend par defaut V2 (un bandeau colle par matiere, ses jeux dessous), un bouton de l'accueil (*"Par matière"* / *"Tout voir"*) bascule vers V1 (etage 1 : une tuile par matiere ; etage 2 : les jeux de la matiere). Le choix de vue vit dans la session HTTP. Onze libelles courts et onze pictogrammes de matiere viennent d'une donnee, pas du code.

Mesure de `yanis-qa` (744 x 551, jar de `def23ead`, `rapport-qualite.md` 2.2) : cartes entierement visibles 8 / 8 / 8 en V2 (CP, CE1, CM1) contre 5 / 5 / 5 sur la liste d'avant ; V1 etage 1 : toutes les tuiles (8 / 2 / 8) ; 146 jeux atteints dans chaque vue, 0 inatteignable. Matieres nommees a l'ecran : 8 sur 8, 2 sur 2, 8 sur 8 (0 avant).

Decisions : `ADR-0105` et `ADR-0106` (`Accepte`, `theo-archi`) ; `DEC-0117` (`Proposee`, `camille-po`) ; `DEC-0116` amendee (`A.21` a `A.25`, hors chantier). Rien n'est persiste (`DEC-0028`). Matrice, catalogue et paquets : inchanges par ce chantier (le retrait du jeu refuse, `13d00bfe`, est anterieur et releve de s56).

Ajoute par cette tache (casquette dev, E2) : `ChaqueJeuDuCatalogueReelEstAtteignableDansLesDeuxVuesDeLAccueilTest` (`bootstrap`, 4 tests). Il fait traverser le catalogue reel, par niveau, a V2 et a V1 (contexte Spring entier), perimetre derive du port, non vide, avec 6 mutations sur le document reel. Mordu aussi sur le gabarit : un jeu retire par groupe en V2 rougit 2 tests sur 4, une tuile retiree en V1 en rougit 2 sur 4, 4 tests lances a chaque fois, fichier restaure. Il ferme `E2` du rapport qualite. Mesure partielle : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest=<la classe> -Dsurefire.failIfNoSpecifiedTests=false`, perimetre `domain`, `application`, `adapter-web`, `adapter-content`, `bootstrap`.

## 2. Verdicts

| Porte | Verdict | Source |
|---|---|---|
| Pedagogique | **VALIDE SOUS RESERVE**, prononce par `nadia-prof` (2026-10-11 20:45), sous la condition `APM-12` ; reserve non bloquante `APM-R1`. Je ne prononce rien. La condition `APM-12` est appliquee par `3187d7f2` (`accueil.html` ligne 115 et inventaire de `TexteAccentueDesGabaritsTest`) ; **je n'ai pas lu de rejugement de `nadia-prof` apres ce commit** : elle l'a declaree verifiable sans elle | `FICHE-accueil-par-matiere.md`, `audit-nadia.md` |
| Technique, local | les quatre jobs de `ci.yml` (`build`, `prefixes`, `chemins`, `outils`) lances **en local, sous Windows**, verts sur l'arbre de cette tache. Ce n'est pas le verdict de la CI | section 3 |
| Technique, CI distante | **non declare.** La CI distante n'a ete lue par personne : rien n'est pousse | - |

## 3. Jobs de `ci.yml` lances, sur l'arbre de cette tache

Arbre : `75290b26` plus les fichiers de cette tache (3 fichiers de s56, `cloture.md`, le test de `bootstrap`), avant commit. JDK 21.0.10 (`java.version` dans les 304 rapports XML).

| Job | Lance | Commande | Resultat |
|---|---|---|---|
| `build`, etape verify | oui | `bash outils/mvn-jdk21.sh -B clean verify`, racine, sans `-pl`, 4 min 19 s | **BUILD SUCCESS**, **2497 tests, 0 echec, 0 erreur, 0 ignore** : 120, 31, 485, 1484, 371, 6 (`domain`, `application`, `adapter-web`, `adapter-content`, `bootstrap`, integration), somme des attributs `tests` des 304 `TEST-*.xml` identique. Total etabli par moi ; `yanis-qa` annonce 2493 sur `def23ead`, l'ecart est mes 4 tests |
| `build`, etape package | oui, par l'enveloppe | `bash outils/mvn-jdk21.sh -B -pl bootstrap -am package` (la CI ecrit `mvn -B ...`) | **BUILD SUCCESS**, 3 min 55 s, `Replacing main artifact ... with repackaged archive` |
| `prefixes` | oui, localement | `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, puis les 23 sujets de `8ac33440..HEAD` et le sujet de mon commit dans `verifier-prefixes.sh` | trois exit 0. La plage `BASE..SHA` d'un push reel n'existe pas : non lancee |
| `chemins` | oui | `verifier-chemins-test.sh`, puis `git ls-files -co --exclude-standard` (hors le test du garde-fou) dans `verifier-chemins.sh` | deux exit 0 : *"Aucun chemin de poste de travail ni nom de compte"* |
| `outils` | oui | les trois motifs de la CI, avec `python` | **11 juges, 11 exit 0** (5, 2, 4), aucun motif vide ; sorties lues : chaque juge conclut *"Tous les cas passent"* ou `OK` |

Non lance : le workflow sous GitHub Actions, Linux, la version de Maven du runner. Ce fichier est ecrit apres le `verify`, et `build` lit les `.md` de `squad/` : les trois garde-fous qui les lisent sont relances sur le fichier final (derniere ligne de cette section).

Relance sur le fichier final (`-pl bootstrap -am`, perimetre : les modules amont et `bootstrap`, pas le depot) : `ToutesLesReferencesDeDecisionResolventTest` 3 tests, `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` 10 tests, `AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest` 5 tests, 0 echec ; `chemins` exit 0.

## 4. Ecarts constates

| # | Ecart | Source | Route |
|---|---|---|---|
| E1 | Seuil de `HS-ACCUEIL-V2` : 10 cartes visibles, 8 mesurees a 744 px (12, 9, 10 a 992 px ; 3 a 390 px). `nadia-prof` : 8 suffit pedagogiquement, le seuil est un critere de produit | `rapport-qualite.md` E1 ; `audit-nadia.md` P8 | `camille-po` |
| E2 | Aucun test Maven ne jouait le catalogue reel dans les deux vues | `rapport-qualite.md` E2 | **ferme par cette tache** (section 1), sous reserve du `verify` ci-dessus |
| E3 | `;jsessionid` dans toute URL de la premiere reponse d'une session (76 occurrences sur `/niveaux/CP`), anterieur au chantier | `rapport-qualite.md` E4, section 3 | `theo-archi` |
| E4 | `APM-R1` : apres *"Les jeux"* de la confirmation, l'adresse reste celle de la page quittee, un rechargement rend l'etage 2 de la matiere jouee ; la puce active reste posee dans le DOM (eteinte a l'oeil) | `audit-nadia.md` P7 ; `rapport-qualite.md` E5 | `theo-archi` (moyen), `camille-po` (story) |
| E5 | Le critere *"Tenu en session"* de `HS-ACCUEIL-BASCULE` ne se lit plus a la lettre (*"Les jeux"* mene au choix de la classe) | `audit-nadia.md` P7 (R7) | `camille-po` |
| E6 | **`DEC-0117` est restee `Proposee` et dit `Q-U1` et `Q-U2` sans reponse.** Leurs reponses (`/` demande la classe ; audit avant fusion) ne sont ecrites que dans `rapport-lea-dev.md` section 16 (*"Decisions de l'utilisatrice appliquees"*), de seconde main, sans les mots | `DEC-0117` statut ; `rapport-lea-dev.md` 16 | `camille-po` |
| E7 | Le catalogue servi compte 146 jeux (CP 58), `DEC-0117` section 4 et le backlog en comptent 147 (CP 59) : le retrait de s56 est posterieur a leur mesure | `rapport-qualite.md` 2.4 | `camille-po` |
| E8 | **Rapport egare** : les sections 14, 15 et 16 de `lea-dev` (retrait, `P2`, `P4`) sont ecrites a la suite de `squad/sprints/s56-paquet-art-cp-10-et-francais-ce1-13/rapport-lea-dev.md`, dossier d'un sprint clos ; `3187d7f2` l'a modifie encore. Je n'ai ni deplace ni reecrit ce fichier | `git log` du fichier | `sofia-sm`, a decider |
| E9 | Aucune entree du journal hors sprint pour ce chantier alors que `DEC-0117` section 8 (a) la demande | `JOURNAL-gouvernance-hors-sprint.md` : dernieres entrees 2026-08 | `sofia-sm`, a decider |
| E10 | **Trailers** : 5 commits hors fusion portent `Claude Opus 5.5` au lieu de `Claude Sonnet 5.5` : `[NAD]` 3 (dont `99008d84`, le verdict), `[THEO]` 2 (`git show -s --format=%B` sur `8ac33440..75290b26`). `nadia-prof` et `theo-archi` sont declares `model: opus` dans `.claude/agents/`. `DEC-0110` 17.4 et son amendement de s54 se contredisent sur la regle, question laissee a l'utilisatrice. Aucun commit n'est reecrit | `DEC-0110` 17.4 | utilisatrice |
| E11 | Les fichiers de s56 (`review.md`, `retro.md`, `RAPPORT.md`) disaient `Q3`, `Q4`, `Q6` sans reponse : corriges par cette tache (2026-10-12). **`Q-U3` y est encore dit sans reponse** alors que le retrait est livre (`13d00bfe`, `rapport-lea-dev.md` 14 : *"Decision de l'utilisatrice du 2026-10-11 (D1)"*) ; non corrige, hors de ma consigne | `review.md` obstacle 10 | `sofia-sm`, a decider |
| E12 | Pas de rapport de tokens pour ce chantier : `outils/rapport-tokens.py` prend `--sprint sNN`, et aucun sprint n'est ouvert. Non lance | `squad/README.md` | `sofia-sm`, a decider |

## 5. Reports

- **`camille-po`** : une `DEC` sur le seuil (8 contre 10, E1), `DEC-0117` (statut, `Q-U1`, `Q-U2`, comptes, E6 et E7), la matrice (la cellule d'`ART-CP-10` cite encore l'entree retiree, `rapport-lea-dev.md` 14.4). **En cours a la date de ce bilan** ; je n'en connais pas le resultat.
- **`theo-archi`** : la troisieme forme de `RessourcesTiercesTest` (R1 de `lea-dev` : le prefixe litteral `'/images/matieres/' + `, a ratifier), `;jsessionid` (E3), l'adresse apres *"Les jeux"* (E4). **En cours a la date de ce bilan** ; je n'en connais pas le resultat.
- **Non verifie par personne** : le clavier et le focus sous le bandeau colle (`position: sticky`, aucun garde-fou), le lecteur d'ecran, une tablette reelle, Safari, le daltonisme, la revelation apres erreurs, un enfant. Hauteur de telephone reelle (844 px) non mesuree. `nadia-prof` reste seule garde de la reconnaissance des pictogrammes, du magenta, de la confusion entre le bouton de vue et *"Les jeux"*, et du bandeau colle sous le focus.

## 6. Questions pour l'utilisatrice

- **D6** : pousser et lire la CI distante sur un commit nomme, job par job. Rien n'est pousse ; sans cela le Done technique reste non declare.
- **D7 a D12** : ces six codes viennent du message de lancement de cette tache ; **aucun fichier du depot ne les porte** (`grep` de `D7` a `D12` sous `squad/` et `docs/` : aucun resultat). Leur contenu n'est pas retrouve, je ne le devine pas.
- **`Q4`** : repondue (*"D2 ordre ok"*, `DEC-0116` A.21) mais `DEC-0116` reste `Proposee` (A.22) : confirmer ses mots de sa main, ou laisser la `DEC` d'ouverture de s57 reprendre l'ordre en forme 1.
- **E10** : quel trailer pour les agents `opus`, et les commits non reecrits restent-ils tels quels ?
- **E8, E9, E11, E12** : veut-elle que `sofia-sm` deplace le rapport de `lea-dev`, ecrive l'entree de journal, corrige `Q-U3` dans les fichiers de s56 ?

## Decisions prises

Aucune `ADR` ni `DEC` n'est creee ou amendee par ce bilan. Decisions du chantier, rapportees : `ADR-0105`, `ADR-0106` (`Accepte`), `DEC-0117` (`Proposee`) ; `DEC-0116` amendee (`A.21` a `A.25`) hors chantier.
