# [Sprint s57] Engagement du poste 8 (correction et rejeu d'`ART-CP-10`), palier 1

- **Auteure** : `sofia-sm`, casquette Scrum Master, 2026-10-15 10:40 (UTC+02:00), lu par `date`. Arbre : `round-two` a `c468c8dd`, branche `s57-sofia-cloture1`, `bash outils/verifier-lignee.sh` sort 0.
- **Ce fichier est l'amendement date de `planning.md` que la porte `P-6` demande.** Il est ecrit a part : je n'ajoute rien a la suite d'un fichier de ceremonie existant, et `planning.md` n'est pas modifie. Il remplace ce qu'il nomme et rien d'autre : la ligne 8 du tableau des postes, la ligne `lea-dev` de la charge, la section "Capacite du palier 1" pour la ligne B, et la porte `P-6`. Si `camille-po` ou `theo-archi` lit `P-6` a la lettre (*"amendement date de ce fichier"*), une ligne de renvoi dans `planning.md` reste a ajouter : c'est un choix de forme que je leur signale, pas une decision.
- **Sources** : `reponse-nadia-forme2-palier-1.md` (`nadia-prof`, `bdc7049b`), `avis-theo-poste-8-art-cp-10.md` annexes A et B (`theo-archi`, `33f975c2`), `releve-yanis-palier-1.md` section 4, `proposition-ines-poste-8-art-cp-10.md` revision 2 (`0af762b8`), `DEC-0120` A.79 a A.86.
- **Ce que ce fichier ne fait pas** : ne code rien, ne fusionne rien, ne prononce aucun Done, ne juge ni la forme ni la coupure.

## 1. Porte `P-6`, element par element

| Element | Etat | Preuve |
|---|---|---|
| `Q1`, `Q2` (`theo-archi`), `Q-Y5` repondues | rempli | `planning.md`, amendement du 2026-10-14 18:20 (D5 a D7, voie (b), 125 px a 744) ; `avis-theo-poste-8-art-cp-10.md` section 1 |
| `C1` a `C4` de `theo-archi` | **a tenir dans le rapport du developpeur** | `avis-faisabilite.md` 2.3 ; `C1` remplacee par `C1'` (avis du poste 8, 2.2) ; `L4` conforme a `C1'` (annexe B, `TB1`) |
| Forme proposee par `ines-design` | rempli | revision 2, `git merge-base --is-ancestor 0af762b8 HEAD` sort 0 |
| Forme jugee par `nadia-prof` | rempli sur les cotes, **non joue** : aucun jar ne la sert | `reponse-nadia-forme2-palier-1.md` 2.2 : **avec `L4`**, marges a zero acceptees ; `git merge-base --is-ancestor bdc7049b HEAD` sort 0. Son verdict sur la forme est au rejeu |
| `P1` de `theo-archi` (forme commitee) | tenue | annexe B.5 |
| `P3` de `theo-archi` (perimetre de la coupure) | **levee** | `Q-T4` = forme C pour tous les jeux, `reponse-nadia-forme2-palier-1.md` 2.4 |
| Seconde moitie de `Q-Y4` (juge pur du releve) | **decision rendue, ecriture non affectee a l'accord de son auteur** | `theo-archi` : oui, dans le job `outils`, pas dans le reacteur (avis, 4, `D5`). Qui l'ecrit : `Q-T2`, section 4 |

Deux portes que ni `P-6` ni l'avis ne prevoyaient, constatees a la lecture croisee. Je les ecris parce qu'elles bloquent un commit precis :

- **`P-8`, commit 2 de B1 (forme C).** Aucune story du backlog ne porte la forme C : `grep -n "MESSAGE_ECHEC\|MESSAGE_BRAVO\|Q-T4" docs/backlog/backlog.md` ne rend aucune ligne. Elle change un texte servi sur les 148 jeux ; une story qui n'est pas dans `docs/backlog/` n'existe pas, et un developpeur n'implemente que les criteres de `camille-po`. Route : `camille-po`, section 5 (`A1`). Le commit 1 et l'ouverture de B1 n'attendent pas.
- **`P-9`, commit 3 de B1 (voie (b)).** Le texte fige de l'annexe A.1 de `theo-archi`, point 1 de la regle executable, dit *"hors sous-arbre `aria-hidden="true"`"* et ne cite que `alt`, `title` et `aria-label`. `nadia-prof` a repondu a `yanis-qa` apres lui : **aucune exemption `aria-hidden`, et les attributs de 4.2 en plus** (`reponse-nadia-forme2-palier-1.md` 4.1 et 4.2, `R7` et `R8`). Le developpeur verse A.1 *"tel quel"* : il verserait un texte que le test de `D7` contredit. Route : `theo-archi` re-fige A.1 avant le commit 3 (section 5, `A2`).

## 2. Ligne engagee : remplace la ligne 8 du tableau "Postes du palier 1"

| Champ | Valeur |
|---|---|
| Poste | 8, correction et rejeu d'`ART-CP-10` |
| Stories, titres au backlog | `S56-ART-CP-10-REPRISE`, `S56-RDO-CP-R1`, `S56-RDO-CP-R2` (`grep -n "^#### S56-" docs/backlog/backlog.md` : lignes 21263, 21274, 21283). Forme C : **aucune story**, `P-8` |
| Competence visee | `ART-CP-10` |
| Instance | B |
| Developpeur | **`lea-dev`**, nominatif (`DEC-0120` A.44). Forme : `theo-archi`, `ines-design`. Rejeu : `nadia-prof` (B) |
| Prix | 0,5 (rejeu), inchange ; aucun prix neuf. Si `nadia-prof` chiffre le premier rejeu de la coupure sur un jeu de designation ou de saisie (`reponse-nadia-forme2-palier-1.md` 2.4), le prix est le sien et se prend sur le palier qui l'engage |
| Branche B1, worktree | `s57-lea-art-cp-10-b1`, `../recreo-s57-lea-art-cp-10-b1` (noms du planning, inchanges) |
| Branche B2, worktree | `s57-lea-art-cp-10-b2`, `../recreo-s57-lea-art-cp-10-b2`, ouverte apres la fusion de B1 (poste 9, section 3) |
| Scratchpad, port de jeu | `s57-p1-lea-dev-b`, 18531 (table `I1` a `I3` inchangee) |
| Port de debogage de Chrome | **`lea-dev` : 19231. `ines-design` (mesure de la section 5, `A5`) : 19243.** Les scripts `pilote-poste-8.mjs` et `coupure-poste-8.mjs` recoivent leur port en argument ; 19222, celui de `pilote-q7.mjs`, n'est pas utilise. `git grep -n "1923[0-9]\|1924[0-9]"` ne rend aucune ligne sur `c468c8dd` |
| Etat | **engage le 2026-10-15** |

**Report.** Le premier report du poste 8 (la reprise elle-meme) **reste ecrit** : `DEC-0120` A.83, *"l'engagement tardif n'efface pas le report, il evite le suivant"*. La ligne `ART-CP-10` garde ses huit evenements sans fermeture jusqu'a un rejeu qui la ferme. Un poste engage et non audite a la cloture du palier 1 glisse au plus une fois (A.72). A lire par l'utilisatrice.

**Capacite**, recomptee par `awk` sur les prix (sortie lue le 2026-10-15) : A 2,5 ; A' 3,0 ; B 1,75 (arbitrage 0,5, marge 0,5, `S54-OBS-SCI-CONSIGNE` 0,25, rejeu 0,5) ; **engage 7,25**, non engage 0, non affecte 0,75, total 8,0. Marges de A, A' et B intactes (`reponse-nadia-forme2-palier-1.md`, section 10). Plafond de 5,0 non approche.

**Horloge.** Le palier 1 s'est ouvert le 2026-10-14 15:45 : 18 h 55 min a l'heure de ce fichier, sous le seuil d'alerte de 41 h 38 min (`duree-et-paliers.md`, `P5'`). Le seuil ne clot rien.

**Charge.** `lea-dev` n'a plus de poste engage ni ouvert hors celui-ci ; le poste 6 est fusionne (`14d6cf47`), la condition de reaffectation du planning (*"si le poste 8 s'engage avant que le poste 6 soit fusionne"*) ne se pose pas. Sa chaine est B1 en trois commits, puis B2. Elle est le chemin critique du palier : je le dis ici, je ne reaffecte pas.

## 3. Taches de `lea-dev`

### B1, trois commits, prefixe `[LEA]`, tests dans le meme commit que le code

Avant d'ouvrir : `git log -1 --format=%h` dans son premier message, `git merge-base --is-ancestor c468c8dd HEAD` sort 0. Elle ne rebase pas, ne pousse pas, ne fusionne pas.

1. **Commit 1, la feuille.** `candidat-poste-8.css` de la revision 2 d'`ines-design`, **avec `L4`** (trois marges a zero : `.score`, bas de `.consigne`, bas de `.enonce`), sous `C1'`, `C2`, `C3`. Dans le meme commit, le jeu fautif `contreexemple/CabinetDeLaMachineTest/dimension-en-pixels` recoit `.ecran-deux-oeuvres .cabinet-bandeau` et `.ecran-deux-oeuvres .cabinet-socle` (avis, 6.2 point 1).
2. **Commit 2, la forme C. Attend `P-8`.** Les trois constantes du verdict de `PartieController` ecrites avec U+00A0 entre les mots d'une phrase et devant un signe double, une espace ordinaire entre deux phrases, **en sequence d'echappement dans la source Java, jamais le caractere tape** (annexe B.4, point 5). Le texte lu par l'enfant ne change ni d'un mot ni d'un signe, seules les espaces changent (condition de `nadia-prof`, 2.4). Le garde-fou de B.4 est **dans ce meme commit** : perimetre derive du comportement (verdicts rendus sur les trois issues des trois mecaniques), lecture par la valeur du modele ou `wholeText()` et jamais `text()`, deux morsures citees avec leurs nombres, nombre de tests lances superieur a 0. **Le texte de B.7 est verse tel quel** dans `ADR-0067` et la ligne d'`ADR-index.md` de B.7 est ajoutee. Les assertions de `PartieControllerTest` qui lisent `.text()` restent ; le rapport dit qu'elles ne jugent pas la coupure.
3. **Commit 3, la voie (b). Attend `P-9`.** Les deux lignes de `retourModele` (`explication` et `questionRappeleeVisible`, sur le predicat `reponseEnImage`), la Javadoc de `retourModele` et le commentaire de `fragments/retour.html` ligne 99 corriges, le test de `D7` sous la forme de `releve-yanis-palier-1.md` section 4 **et de `reponse-nadia-forme2-palier-1.md` 4.1 et 4.2** (aucune exemption `aria-hidden`, attributs de 4.2, toute `<img>` porte un `alt`, donnees reelles de `cp-rapprocher-deux-oeuvres.yaml`, fins juste et revelee). Les blocs A.1 a A.4 de l'annexe A, **tels que `theo-archi` les aura re-figes** (`P-9`), dans `ADR-0104`, `ADR-0084`, `ADR-0024` et `ADR-index.md`. Les trois morsures de A.1 avec leurs nombres dans le rapport.

**Dans le rapport de `lea-dev`** : `C1'` points 1 a 3, `C2`, `C3` ; les six tests nommes de `C3` (`CabinetDeLaMachineTest`, `AucunTexteNeVitDansUnSousArbreMasqueTest`, `LHabillageResteImmobileTest`, `UnEcranNeGouvernePasLApparenceDUnAutreTest`, `LePlafondDeRenduDUneReproductionEnPropositionEstGardeTest`, `PartieControllerPropositionsEnImageTest`) ; les jobs `build`, `chemins`, `outils` et `prefixes`, **chacun nomme avec sa sortie** (un job non lance est dit non lance, aucun n'est deduit). Une mesure partielle porte `-am` et nomme son perimetre.

**Fusion de B1** : apres la lecture du diff par `theo-archi` (`C1'` points 1 a 3 ; texte des `ADR` identique aux blocs figes). Je fusionne, `bash outils/verifier-lignee.sh` avant, `bash outils/mvn-jdk21.sh -B clean verify` racine apres, et **je recompte les tests moi-meme**.

**`Q-T3`, ma reponse** (procedure de la section 3.2 de l'avis) : le texte est de `theo-archi`, le developpeur le verse sans le modifier, son commit porte `[LEA]`. La regle des prefixes ne juge que le sujet du commit (`.github/scripts/verifier-prefixes.sh`, lu : un sujet par ligne, un tag connu) ; elle ne voit pas l'auteur du texte. La frontiere des roles tient par la lecture du diff de `theo-archi` a la fusion. Aucun amendement a la regle des prefixes.

### Mesure au jar, entre B1 et le rejeu

Sur la tete de B2, jamais sur un jar sans l'entree (avis, 6.3) : `pilote-poste-8.mjs` sans CSS injecte (`-`), largeurs 720, 744, 768, 992, 1200, fenetre de 551 ; **`coupure-poste-8.mjs` rejoue sur le jar de B2** (avis, annexe B.5), sans reecriture du DOM puisque le jar porte la forme C : par lecture de l'en-tete du script, la variante `A` restitue le texte servi ; `lea-dev` le verifie et le dit dans son rapport, et si aucun mode ne laisse le DOM intact, elle le signale a `ines-design` avant de mesurer. Releve JSON commite sous `squad/conformite/releves-s57/` avec le commit mesure ; juge par le juge pur de `Q-Y4` des qu'il existe. Le verdict reste a `nadia-prof`.

### B2 (poste 9), forme de l'avis, section 5

Branche issue de `round-two` **apres** la fusion de B1 (`git merge-base --is-ancestor <tete de B1> HEAD`, sortie citee). **Un seul commit**, trois fichiers (`catalogue.yaml`, `TexteAccentueExposeParLePortEntrantTest.java`, `LesPropositionsEnImageAtteignentLEcranSansJamaisSonIdentifiantTest.java`), identifiant `bandit-manchot-rapprocher-deux-oeuvres-cp`, quatre chaines d'inventaire, garde-fou 4 remis en `containsExactly`. Les quatre verifications de l'avis (5) avant que `nadia-prof` joue.

**Ordre de fusion avec le poste 7** : `catalogue.yaml` et l'inventaire de `TexteAccentueExposeParLePortEntrantTest` sont touches par B2 **et** par la branche du poste 7 (`s57-marc-fra-ce1-04`, non fusionnee a `c468c8dd`). Je fusionne le poste 7 **avant l'ouverture de B2** pour que B2 parte d'une base qui le porte. Compte du catalogue : 148 a `c468c8dd` (`grep -c "^  - id:"`), 149 apres le poste 7, 150 apres B2 ; la base reelle de B2 se relit le jour de son ouverture. Un ecart arrete la fusion et va a `theo-archi`.

**Rejeu** : `nadia-prof`, instance B, repertoire `s57-p1-nadia-prof-b`, port 18513, sur la tete de B2. Fusion de B2 apres le verdict seulement, `R1` a `R3` de l'avis (3.3) cites par moi.

## 4. `Q-T2`, affectation du juge pur du releve

Le juge pur de `Q-Y4` (avis, 4) est une action de `yanis-qa` sans budget (`DEC-0116` section 9) : **je la lui affecte, a confirmer par lui**, avant le rejeu de B2. L'affectation est de capacite, donc a moi ; si `yanis-qa` la refuse ou la juge hors de son perimetre, il le dit par ecrit et je reaffecte. Sans lui, le releve de la mesure au jar n'est pas juge par un automate et le rejeu reste ce que l'avis dit : le seul garde.

## 5. Route, sans decision de ma part

| Code | Quoi | A qui |
|---|---|---|
| `A1` | Story de la forme C (trois constantes du verdict, tout jeu, garde-fou de B.4, amendement d'`ADR-0067`), criteres de `reponse-nadia-forme2-palier-1.md` 2.4 et de l'annexe B. Porte `P-8` | `camille-po` |
| `A2` | Re-figer l'annexe A.1 sous `R7` et `R8` de `nadia-prof` (aucune exemption `aria-hidden`, attributs de 4.2). Porte `P-9` | `theo-archi` |
| `A3` | Confirmer l'ecriture du juge pur du releve (section 4) | `yanis-qa` |
| `A4` | Story `RCT-CE1-R1`, stories `Q-R4` (clause de non-regression), story de `Q-T6` : voir `cloture-palier-1.md` section 2 | `camille-po` |
| `A5` | Mesure de la forme commune sans `L1` ni `L2` sur `TPH-CE1-R1`, `GNA-CE1-R1` et `VSJ-CE1-R2`, avant la composition du palier 2 (`reponse-nadia-forme2-palier-1.md` section 3, point 1) | `ines-design` |

## Jobs de `ci.yml` lances pour ce commit

`cloture-palier-1.md`, section 3 : `build` (2548 tests, `BUILD SUCCESS`), `chemins` et `prefixes` lances et sortis 0, `outils` non lance, avec leur sortie. Ces jobs couvrent les trois fichiers de ce commit.

## Decisions prises

**Aucune `ADR` ni `DEC` n'est creee ou amendee par ce fichier.** Il engage le poste 8 sous `P-6` complete de `P-8` et `P-9`, nomme les ports de debogage de `lea-dev` et d'`ines-design`, affecte le juge pur a `yanis-qa` sous reserve de son accord, repond a `Q-T3`, fixe l'ordre de fusion du poste 7 avant B2. `DEC-0120` n'est pas amendee ici : le premier report du poste 8 reste ecrit, `camille-po` tient la ligne `ART-CP-10`.
