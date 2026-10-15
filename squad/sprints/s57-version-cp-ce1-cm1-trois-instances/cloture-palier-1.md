# [Sprint s57] Cloture du palier 1 - SQUELETTE, etat du 2026-10-15

- **Statut : le palier 1 n'est pas clos.** Ce fichier remplace en une seule ecriture le squelette du 2026-10-14 (`6ca84317`) : le diff garde l'etat precedent. Il constate l'etat au 2026-10-15 10:40 (UTC+02:00, lu par `date`), sur `round-two` a `c468c8dd`. Il n'a ni horodatage de fin, ni verdict de cloture, ni report constate par la cloture, et **il ne declare pas la porte technique**.
- **Horodatage de fin du palier** : non ecrit.
- **Ecrit par** : `sofia-sm`, casquette Scrum Master. Lignee : `bash outils/verifier-lignee.sh` sort 0.

## 0. Etat au 2026-10-15 : ce qui est clos, ce qui reste

**Clos, avec sa source.**

| Quoi | Source |
|---|---|
| Verdicts de `nadia-prof` des postes 3, 4, 5, 6 et 7 | section 1 |
| Poste 7 : Done pedagogique prononce (VALIDE SOUS RESERVE, 2026-10-15, jar joue sur `4dd6f298`) | `FICHE-bandit-manchot-texte-narratif-ce1.md` ; `reponse-nadia-forme2-palier-1.md` section 1 |
| Poste 8 engage (porte `P-6` complete de `P-8` et `P-9`) | `engagement-poste-8.md` |
| Reponses de `nadia-prof` a la forme du poste 8 : police, avec `L4`, phrase de `M4`, `Q-T4` (forme C pour tous les jeux), `Q-T5` (sans `L1` ni `L2`) | `reponse-nadia-forme2-palier-1.md` sections 2 et 3 |
| Reponses de `nadia-prof` a `yanis-qa` (`Q1`, `Q2` : aucune exemption `aria-hidden`, attributs de 4.2) | idem, section 4 |
| Reponses de `nadia-prof` a `camille-po` : `Q-R1` (prix confirmes, quatrieme story `RCT-CE1-R1`), `Q-R2` (non), `Q-R4` (oui, trois ajouts), avis sur `Q-T6` | idem, sections 5 et 7 |
| `RP-ACCUEIL-147` : fait, voie (b), annotation datee | section 2 |
| Relecture independante des postes 5 et 3 : fusion du poste 5 sans ecart (`F2`), comptes confirmes (`F3`) | `releve-yanis-palier-1.md` |

**Ce qui reste, par ordre de dependance. Rien n'est decide ici : chaque ligne est routee a qui en est le titulaire.**

| Code | Reste a faire | Titulaire | Etat constate |
|---|---|---|---|
| `R1` | **Fusion du poste 7** (`s57-marc-fra-ce1-04`, commits `4dd6f298` et `a41bb408`) puis comparaison de son contenu fusionne au commit joue (`nadia-prof` demande ce controle) ; `camille-po` passe `FRA-CE1-04` a `couvert-valide` apres | `sofia-sm` (fusion), `camille-po` (matrice) | `git merge-base --is-ancestor 4dd6f298 HEAD` sort 1 : **non fusionne** |
| `R2` | **Poste 8 en code** : B1 en trois commits (feuille avec `L4` ; forme C avec garde-fou de B.4 et texte de B.7 ; voie (b)), puis mesure au jar, B2, rejeu de `nadia-prof`. `coupure-poste-8.mjs` rejoue sur le jar de B2 | `lea-dev`, `theo-archi` (lecture du diff), `nadia-prof` | engage, aucun commit : `engagement-poste-8.md` |
| `R3` | `P-8` : story de la forme C | `camille-po` | absente du backlog |
| `R4` | `P-9` : annexe A.1 de `theo-archi` a re-figer sous les reponses `Q1` et `Q2` de `nadia-prof` | `theo-archi` | texte actuel contredit par le test de `D7` precise |
| `R5` | Juge pur du releve (`Q-Y4`, seconde moitie, `Q-T2`) | `yanis-qa` | affectation proposee, accord attendu |
| `R6` | `F1` de `yanis-qa` : garde du distracteur egal au verbe cite | `marc-dev` | consigne et criteres ecrits dans `tache-marc-dev-f1.md` ; story absente du backlog |
| `R7` | Stories de `camille-po` : `RCT-CE1-R1` (quatrieme reserve, meme regime que `VSJ-CE1-R2`) ; trois ajouts a la clause de non-regression de `S57-TPH-CE1-R1` (`Q-R4`) ; story de faible priorite sur `MESSAGE_PARTIE_PERDUE` (`Q-T6`) ; story de `F1` ; dire si `nombreDeManches: 10` est une exigence (`F4`) ; le pleonasme d'Ines | `camille-po` | `grep -n "RCT-CE1-R1" docs/backlog/backlog.md` ne rend rien |
| `R8` | Mesure d'une forme commune sans `L1` ni `L2` sur `ce1-phrases-types`, `ce1-accord-groupe-nominal`, `ce1-verbe-sujet` et, si fusionne, `ce1-texte-narratif`, avant la composition du palier 2 | `ines-design` | non commencee ; port de debogage 19243 |
| `R9` | Releve du `relecteur-pedagogique`, **non traites** : `N1` a `N4` (lisibilite, convention de citation, distracteurs sans determinant, une phrase de fiche) a `nadia-prof` ; `D1` a `D4` (documentation dont elle est l'auteure) a `nadia-prof` ; `D5` (en-tete *"brouillon soumis"* de trois paquets) a `marc-dev` avec `nadia-prof` ; `S1` a `S4` (provenance de forme 2 dans `planning.md` et `duree-et-paliers.md`, mes fichiers) a moi, `S5` a `theo-archi` ; en-tete de neuf autres paquets a verifier par `marc-dev` | `nadia-prof`, `marc-dev`, `sofia-sm`, `theo-archi` | aucun traite a ce jour |
| `R10` | Cloture : horodatage de fin, **porte technique** sur l'arbre fusionne avec les jobs nommes, total de tests recompte, isolation lue par `nadia-prof`, obstacles, decisions | `sofia-sm` | section 3 |

## 1. Verdict ou report de chaque poste engage

Un poste engage a un verdict ecrit de `nadia-prof` ou un report ecrit (condition 2 de 11.3).

| Poste | Instance | Verdict de `nadia-prof`, ou glissement ecrit | Fiche ou fichier du verdict |
|---|---|---|---|
| 3, `VSJ-CE1-R1` | A | `VSJ-CE1-R1` levee ; `FRA-CE1-13` VALIDE SOUS RESERVE, reserve neuve non bloquante `VSJ-CE1-R2` | `rejeu-nadia-palier-1.md` ; `FICHE-bandit-manchot-verbe-sujet-ce1.md` |
| 4, `ce1-phrases-types` | A' | VALIDE SOUS RESERVE, `TPH-CE1-R1` non bloquante ; `Q-N4` = non | `FICHE-bandit-manchot-phrases-types-ce1.md` |
| 5, `ce1-accord-groupe-nominal` | A' | VALIDE SOUS RESERVE, `GNA-CE1-R1` non bloquante | `FICHE-bandit-manchot-accord-groupe-nominal-ce1.md` |
| 6, `S54-OBS-SCI-CONSIGNE` | B | VALIDE, sans reserve ; `SCI-CM1-20` non rouverte | `rejeu-nadia-palier-1.md` ; `FICHE-bandit-manchot-sciences-extraire-information-cm1.md` |
| 7, `ce1-texte-narratif` | A | **VALIDE SOUS RESERVE, 2026-10-15**, jar joue sur `4dd6f298` ; reserve neuve non bloquante `RCT-CE1-R1` (a 744 x 551, apres une erreur, l'indice est sous le pli sur 9 items sur 20, le verdict aussi sur 2) ; titre *"Lis et réponds"* tenu ; l'intrus *"la"* / *"se"* n'est ni refus ni reserve. **Le commit joue n'est pas fusionne** (`R1`) | `FICHE-bandit-manchot-texte-narratif-ce1.md` ; `reponse-nadia-forme2-palier-1.md` section 1 |
| 8, correction et rejeu d'`ART-CP-10` | B | **engage le 2026-10-15** ; aucun verdict : le rejeu suit B1, la mesure au jar et B2 | `engagement-poste-8.md` |
| 9, branche B2 | B | non engage : elle suit le poste 8 et son verdict | `engagement-poste-8.md`, section 3 |

## 2. Glissements, reports, limite

Regle (`DEC-0120` A.72, confirmee par `planning.md`, amendement du 2026-10-14 18:20) : un poste glisse au plus une fois ; le second glissement est un report compte ; le troisieme report est interdit. A ecrire a la cloture : chaque glissement avec sa date et sa cause, dit a l'utilisatrice le jour meme.

- **Poste 7** : engage le 2026-10-14, audite le 2026-10-15 avant la cloture. **Pas de glissement.** Son premier report reste ecrit (A.83).
- **Poste 8** : engage le 2026-10-15. **Premier report de la reprise reste ecrit** (A.83) ; la ligne `ART-CP-10` garde huit evenements sans fermeture, a lire par l'utilisatrice. S'il n'est pas audite a la cloture du palier 1, il glisse une seule fois (A.72) : cause et date a ecrire ici. Poste 9 : non engage.
- **`S54-OBS-SCI-CONSIGNE`** : deux reports comptes ; poste 6 audite et VALIDE le 2026-10-14. **Plus de glissement a craindre** ; le statut de la ligne dans la matrice est a `camille-po`.
- **`RP-ACCUEIL-147`** : echeance avant la cloture du palier 1 (A.74). **Fait par `nadia-prof`, voie (b), le 2026-10-15** (`reponse-nadia-forme2-palier-1.md` section 6). Constate par moi : `grep -n "147" squad/conformite/ARBITRAGE-PREALABLE-accueil-par-matiere.md` rend les lignes 41, 236, 398, 410 et 424 ; les lignes 41 et 236 portent la mention *"faux des l'ecriture, annotation du 2026-10-15 en fin de fichier"* et la ligne 410 ouvre l'annotation. La ligne 398 n'est pas touchee. Que la voie (b) satisfasse le critere 1 amende est a `camille-po`, qui l'a ecrit ; je ne le juge pas.

## 3. Porte technique

**Non declaree.** Le palier n'est pas clos et l'arbre fusionne n'existe pas encore : le poste 7 n'est pas fusionne et le poste 8 n'a aucun commit de code. A remplir a la cloture : commit teste, jobs lances parmi `prefixes`, `chemins`, `outils`, `build` avec leur sortie, jobs non lances nommes comme tels. **Total de tests recompte par moi sur cet arbre**, avec sa methode (commande, perimetre, JDK que la machine virtuelle des tests rapporte) ; un chiffre annonce par un agent n'est pas relaye.

**Derniere mesure sur `round-two` fusionne, non celle du palier clos** : 2548 tests (`yanis-qa`, `f95736b5`, `releve-yanis-palier-1.md` 1 ; `nadia-prof`, `1a3ec5d9`, `reponse-nadia-forme2-palier-1.md` section 11). Ce total ne contient ni le poste 7 (le jeu de `PaquetCe1TexteNarratifTest` : 722 lignes sur la branche) ni le poste 8.

**Jobs de `ci.yml` lances pour le commit qui porte ce fichier, `engagement-poste-8.md` et `tache-marc-dev-f1.md`** (arbre : `round-two` a `c468c8dd`, trois fichiers de ceremonie ajoutes ou remplaces, aucun code, aucun contenu). Ce n'est pas la porte du palier.

- **`build`** : `bash outils/mvn-jdk21.sh -B clean verify`, racine, sans `-pl` : `BUILD SUCCESS`, 2548 tests (120, 31, 486, 1534, 371 et 6 d'integration), 0 echec, 0 erreur, 0 ignore ; JDK 21.0.10 (`java.version` des rapports Surefire de `bootstrap`) ; total recompte par l'attribut `tests` des fichiers `TEST-*.xml` de chaque module, meme valeur. Il egale le total de `f95736b5` : le commit ne change ni code ni contenu. Le build a lu ces fichiers avant l'ecriture de ce bloc, qui ne contient aucun tableau. Etape *"Package du fat jar"* non lancee.
- **`chemins`** : `verifier-chemins-test.sh` sort 0 ; `git ls-files` (moins le test du garde-fou), fichiers de ce commit indexes, passe a `verifier-chemins.sh` : sort 0.
- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh` et `verifier-garde-fou-accent-test.sh` (21 cas) sortent 0 ; le sujet du commit passe dans `verifier-prefixes.sh` : sort 0. Le controle du proprietaire du garde-fou de l'accent n'est pas lance : aucun de ses chemins n'est touche.
- **`outils`** : non lance ; aucun outil n'est modifie.

Aucun de ces resultats n'est celui de la CI distante, que je n'ai pas lue.

## 4. Fusions et comptage du catalogue

Une ligne par fusion (`planning.md`, section "Ordre de fusion") : `grep -c "^  - id:" adapters/adapter-content/src/main/resources/catalogue.yaml` apres la fusion vaut le compte d'avant plus le nombre d'entrees fusionnees ; `grep -c "paquet: <nom>$"` rend 1 pour chaque paquet fusionne. Compte de depart mesure sur `round-two` a `6ca84317` : 146. Un ecart arrete la fusion et va a `theo-archi`.

| Fusion | SHA de fusion | Compte d'avant | Entrees fusionnees | Compte d'apres | `grep -c "paquet: <nom>$"` | Tests du reacteur sur l'arbre fusionne | Base de la branche, ancetre de `round-two` |
|---|---|---|---|---|---|---|---|
| Poste 4, `s57-fra-ce1-10` (`1f6642ab`, `marc-dev`) | `c935d764` | 146 | 1 | 147 | `ce1-phrases-types` : 1 | 2520 | `3a3ea97e`, oui |
| Poste 5, `s57-sofia-fra-ce1-15` (`3459ddb8`). **Branche de `sofia-sm` en casquette developpeuse, fusionnee par `sofia-sm` en casquette Scrum Master : aucun autre juge que `yanis-qa` a la cloture** | `d67204de` | 147 | 1 | 148 | `ce1-accord-groupe-nominal` : 1 | 2541 | `ea6d6f92`, oui |
| Poste 6, `s57-sci-consigne` (`ac32f976`, `lea-dev`) | `14d6cf47` | 148 | 0 | 148 | sans objet, aucune entree | 2542 | `faf7e285`, oui |
| Poste 3, `s57-marc-vsj-ce1-r1` (`7dc40fb0`, `marc-dev`) | `65d00d8d` | 148 | 0 | 148 | sans objet, aucune entree | 2548 | `6ca84317`, oui |

**A venir, non fusionne** : poste 7 (`s57-marc-fra-ce1-04`, une entree) : attendu 148 puis 149 ; B2 (poste 9) : 150 apres le poste 7, a relire a l'ouverture de B2. Compte lu a `c468c8dd` : 148.

**Methode des tests** : `bash outils/mvn-jdk21.sh -B clean verify`, racine, sans `-pl`, apres chaque fusion, somme des attributs `Tests run` des cinq modules Surefire et du Failsafe lus dans la sortie Maven ; JDK 21.0.10 (`java.version` d'un rapport Surefire de `bootstrap`). Modules apres la derniere fusion : domain 120, application 31, adapter-web 486, adapter-content 1534, bootstrap 371, integration 6. Ecarts : +22 (poste 4), +21 (poste 5), +1 (poste 6), +6 (poste 3), chacun egal au lot que la branche annonce. **`yanis-qa` a remesure 2548 sur l'arbre fusionne et confirme les comptes de catalogue (`F3`) ; il n'a pas remesure les totaux intermediaires ni la base 2498.** Aucun total annonce par un agent n'est relaye.

**Conflits.** Seule la fusion du poste 5 en a eu, sur `catalogue.yaml` (une entree) et sur `TexteAccentueExposeParLePortEntrantTest.java` (trois blocs d'inventaire, aux memes ancres). **Resolution** : les ajouts des deux cotes sont gardes dans l'ordre des postes (4 puis 5), `nombreDeManches: 10` redonne a l'entree du poste 4 dont les deux entrees se partageaient la ligne. Aucune assertion n'est modifiee ni retiree : `git diff c935d764 d67204de` sur ces deux fichiers rend 6 et 9 lignes ajoutees, 0 supprimee. **`yanis-qa` a relu cette resolution : union exacte, aucune assertion retiree ni affaiblie (`F2`).**

**Ce qui est fusionne est ce qui a ete joue** : `git diff <branche> <fusion> -- <paquet>` rend 0 ligne pour les quatre paquets (`ce1-phrases-types`, `ce1-accord-groupe-nominal`, `cm1-sciences-extraire-information`, `ce1-verbe-sujet`) ; les entrees de catalogue des postes 4 et 5 sont identiques a celles de leur branche. Le poste 6 est fusionne a `ac32f976`, le commit que `nadia-prof` a joue. **Poste 7 : a faire a sa fusion**, contre `4dd6f298`, le commit joue.

**Ecarts de relecture a connaitre** : `F1` (la propriete 8 amendee laisse passer un distracteur egal au verbe cite, mutation `P5`, `R6`) et `F4` (`nombreDeManches: 10` n'est garde par aucun test, mutation `C8`, a `camille-po`). Aucun n'est un defaut de contenu ; ce sont des gardes absentes.

## 5. Ordre du critere 3 de `S57-ARB-PALIER-1`

Une ligne par paquet : le commit qui porte la ligne *"lecture complete"* de `nadia-prof` est ancetre du commit du premier verdict (`git merge-base --is-ancestor`). Les trois premieres lignes *"lecture complete"* sont dans `rejeu-nadia-palier-1.md`, section 7, commit `13d6285f` (`ce1-phrases-types` 24 items, `ce1-accord-groupe-nominal` 24, `ce1-texte-narratif` 20).

- `ce1-accord-groupe-nominal` : verdict `0688b581`, `git merge-base --is-ancestor 13d6285f 0688b581` sort 0.
- `ce1-phrases-types` : la ligne et le premier verdict sont dans le **meme commit** `13d6285f` ; un commit est son propre ancetre, l'ordre est tenu a la lettre et non demontre entre deux commits.
- `ce1-texte-narratif` : verdict dans `bdc7049b`. La fiche cite pour la lecture complete `lecture-nadia-palier-1.md` section 2.1 (`7161ab69`, 20 items, version d'avant `D1` et `D2`) et dit avoir relu les deux items corriges au jeu (section 3). `git merge-base --is-ancestor 7161ab69 bdc7049b` sort 0 et `git merge-base --is-ancestor 13d6285f bdc7049b` sort 0. **Ce que la fiche ne dit pas : une lecture complete de la version corrigee.** `nadia-prof` a joue les 20 items au jar (fiche, section 1) ; c'est a elle de dire si cela vaut la ligne du critere 3.

## 6. Isolation

Lecture de `nadia-prof` des transcriptions du palier : repertoires et ports de la table de `planning.md` respectes ou ecart nomme. Ce fichier ne la remplace pas. Ports de debogage de Chrome ajoutes pour le poste 8 par `engagement-poste-8.md` : 19231 (`lea-dev`), 19243 (`ines-design`). A remplir a la cloture.

## 7. Obstacles du palier, etat intermediaire

Tableau repris dans `review.md` a la cloture du sprint. **Un obstacle n'est clos que quand le fichier qui le tranche existe sur le disque**.

| Obstacle | Categorie | Agent | Artefact attendu | Etat au 2026-10-15 |
|---|---|---|---|---|
| Forme C sans story | produit | `camille-po` | story au backlog | ouvert, `R3` |
| Annexe A.1 contredite par `Q1` et `Q2` de `nadia-prof` | technique | `theo-archi` | texte re-fige dans `avis-theo-poste-8-art-cp-10.md` | ouvert, `R4` |
| Amendement d'`ADR-0067` (texte de B.7) | technique | `theo-archi`, verse par `lea-dev` | amendement dans `ADR-0067` | **ouvert** : `grep -c "Amendement du 2026-10-14"` sur le fichier rend 0. La question pedagogique `Q-T4` est tranchee, l'`ADR` ne l'est pas encore |
| Amendements d'`ADR-0104`, `ADR-0084`, `ADR-0024` (annexe A) | technique | `theo-archi`, verse par `lea-dev` | amendements dans les trois `ADR` | ouvert, meme motif |
| Juge pur du releve | qualite | `yanis-qa` | juge dans le job `outils` | ouvert, `R5` |
| Garde du distracteur egal au verbe cite (`F1`) | qualite | `marc-dev` | test et jeu fautif | ouvert, `R6` |
| Valeur `nombreDeManches: 10` non gardee (`F4`) | qualite | `camille-po` | dire si 10 est une exigence | ouvert |
| Forme commune des propositions longues, mesure | pedagogie et interface | `nadia-prof`, `ines-design` | releve de la mesure, puis avis | ouvert, `R8` |
| Poste 7 non fusionne | capacite | `sofia-sm` | fusion, ligne de la section 4 | ouvert, `R1` |
| Provenance de forme 2 : `S1` a `S4` | redaction | `sofia-sm` | corrections de `planning.md` et `duree-et-paliers.md` | ouvert ; `S5` a `theo-archi` |
| `N1` a `N4`, `D1` a `D5` du relecteur | pedagogie, redaction | `nadia-prof`, `marc-dev` | avis dans les fiches ou corrections | ouvert, `R9` |
| `RP-ACCUEIL-147` | produit | `nadia-prof` | annotation du fichier d'arbitrage | **clos** : annotation presente, lignes 410 a 424 (section 2) |
| `Q-T3` (procedure de versement du texte de `theo-archi`) | redaction | `sofia-sm` | reponse ecrite | **clos** : `engagement-poste-8.md`, section 3 |
| `Q-T2` (qui ecrit le juge pur) | capacite | `sofia-sm` | affectation ecrite | **affectee, accord de `yanis-qa` attendu** : `engagement-poste-8.md`, section 4 |

## Decisions prises

**Ce fichier ne cree ni n'amende aucune `ADR` ni `DEC`.** La liste complete des decisions du palier (chaque `ADR` et `DEC` cree ou modifie, avec numero, titre et statut) s'ecrit a la cloture, ou l'enonce explicite qu'aucune n'a ete prise. A ce jour, sur ce palier, `ADR-0067`, `ADR-0104`, `ADR-0084` et `ADR-0024` ont un amendement au texte fige par `theo-archi` et **ne sont pas amendes sur le disque** : l'amendement entre dans le commit du code de `lea-dev`.
