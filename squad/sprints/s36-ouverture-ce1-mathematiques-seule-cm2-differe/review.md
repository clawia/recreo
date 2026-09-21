# Review - s36 - ouverture du perimetre CE1, mathematiques seule (CM2 differe)

- **Horodatage de fin** : 2026-09-21 20:36 (UTC+02:00).
- **Horodatage de debut** (`planning.md`) : 2026-09-21 16:40 (UTC+02:00).
- **Duree effective** : 3 h 56 min.
- **Casquette** : `sofia-sm`, Scrum Master.
- **Arbre revu** : `s36-sofia` a `a7f4c730`. Douze commits de preparation verifies ancetres de ce
  commit par `git merge-base --is-ancestor <commit> HEAD` avant cette review (les six worktrees de
  premiere consolidation - `s36-camille`, `s36-nadia`, `s36-ines`, `s36-theo`, `s36-lea` -, les deux
  worktrees de porte technique - `s36-yanis`, `s36-theo-porte` -, les deux correctifs - `s36-lea-fix`,
  `s36-theo-regle` -, et les trois derniers lots de cloture - `s36-camille` second commit,
  `s36-nadia-audit`, `s36-nadia-division`), aucune fusion supplementaire necessaire au-dela de celles
  deja faites par moi.

---

## 1. L'increment livre, en une phrase

Le perimetre du produit s'ouvre a un troisieme niveau, CE1 (mathematiques seule, 17 competences
plus une ligne trouvee manquante en relecture, CM2 explicitement differe), et la cinquieme reprise
de `QLM-CP-11` (le plan de la classe) est **validee sous reserve** apres quatre refus consecutifs :
la couverture passe de 76/138 (55,1 %) a 77/155 (49,7 %), un recul de taux qui tient entierement a
l'agrandissement du denominateur et non a une regression du produit.

## 2. Le Done technique - quatre jobs, verifies un par un, sur l'arbre fusionne final

Conformement a `squad/README.md`, section 9, et a l'amendement du 2026-09-08 a `CLAUDE.md` : un
agent qui annonce la porte technique nomme les jobs qu'il a lances et ne deduit pas les autres. Les
quatre jobs de `.github/workflows/ci.yml` ont ete relances par moi, individuellement, sur
`s36-sofia` a `a7f4c730` (`git status` propre avant et apres).

| Job | Commande exacte | Resultat |
|---|---|---|
| `build` | `bash outils/mvn-jdk21.sh -B clean verify` | **BUILD SUCCESS**, 6 modules sur 6 (`recreo`, `recreo-domain`, `recreo-application`, `recreo-adapter-web`, `recreo-adapter-content`, `recreo-bootstrap`) |
| `chemins` | `bash .github/scripts/verifier-chemins-test.sh` puis `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' \| bash .github/scripts/verifier-chemins.sh` | **vert** : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."* |
| `outils` | balayage `outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py`, un par un | **vert**, **9 juges purs executes**, 0 echec |
| `prefixes` | `bash .github/scripts/verifier-prefixes-test.sh` et `bash .github/scripts/commit-atteignable-test.sh`, puis `git log --no-merges --format=%s a8fcb000..HEAD \| bash .github/scripts/verifier-prefixes.sh` | **vert** : *"Tous les commits portent un prefixe d'agent connu."* |

**Compte de tests, mesure par moi sur le journal complet du lancement de `build`, blocs
`[INFO] Results:`** :

| Module | Tests run |
|---|---|
| recreo-domain | 111 |
| recreo-application | 13 |
| recreo-adapter-web | 400 |
| recreo-adapter-content | 708 |
| recreo-bootstrap (surefire) | 258 |
| recreo-bootstrap (failsafe) | 6 |
| **Total** | **1496** |

**0 echec, 0 erreur, 0 ignore.** Ce chiffre est celui de l'arbre exact fusionne a la cloture. Il
correspond exactement a la somme des deux mesures partielles independantes annoncees en cours de
sprint (1487 par `yanis-qa` sur son worktree apres consolidation des six premiers lots, +1 par
`lea-dev` sur son correctif seul, +8 par `theo-archi` sur son propre lot de regle executable :
1487 + 1 + 8 = 1496), verifiee par moi module par module avant fusion de chacun des deux derniers
correctifs, sans aucun ecart a expliquer cette fois.

**ArchUnit** : **33** occurrences de `@ArchTest`, recompte par moi sur l'arbre fusionne final
(`grep -rc "@ArchTest"` sur les arbres `src/test/java` des cinq modules, `target/` exclu par
construction), inchange depuis s35. `theo-archi` (`confirmation-archunit.md`, section 2) nomme une
divergence de methode dans ses propres `ADR` (`static final ArchRule` compterait 34, pas 33) et la
corrige dans le meme commit que sa confirmation : ce n'est pas un ecart de comptage entre deux
mesures, c'est une correction de la methode citee a cote d'un chiffre qui, lui, etait deja juste.

## 3. La confirmation ArchUnit et les decisions d'architecture

`theo-archi` (`confirmation-archunit.md`, en deux temps le meme jour) :

- `ADR-0093` (le nom accessible d'un bouton de zone est resolu sur le document servi et porte le
  libelle que le paquet declare) : `Propose` -> **`Accepte`**. Les deux clauses sont dans les deux
  fichiers nommes, les cinq morsures dues sont ecrites, la clause 2 mord sur une mutation de source
  de production (section 4 de sa confirmation).
- `ADR-0094` (le paquet declare ou se lit le libelle de ses zones, aucun gabarit ne nomme un contenu
  particulier) : `Propose` -> **reste `Propose`** au moment de la premiere confirmation (regle
  executable de la decision 6 absente de l'arbre, ecart de forme sur la decision 5) -> **`Accepte`**
  le jour meme, dans un second worktree dedie (`s36-theo-regle`) : la regle executable est ecrite (8
  tests JUnit, aucune `ArchRule` - les objets juges sont des gabarits, une feuille, un script, pas
  des classes chargees par ArchUnit), et la decision 5 est amendee en propriete gardee par la clause
  2 d'`ADR-0093` deja dans l'arbre, plutot que reecrite pour coincider avec le code apres coup.

Verifie sur le disque : les deux entrees de `squad/decisions/adr/ADR-index.md` portent le statut
`Accepte` avec leur methode citee dans la meme ligne.

**Deux defauts trouves et payes par `theo-archi` lui-meme dans la meme journee, avant de s'appuyer
dessus** : une divergence de methode sur le compte des `ArchRule` (section 2 ci-dessus) et l'absence
de la regle executable de la decision 6 (ce paragraphe) - les deux corriges dans le commit meme qui
les constate, sur le meme principe que `ADR-0092` a s35.

## 4. Le rapport qualite de `yanis-qa`

`rapport-qualite.md` (mesure sur `s36-sofia` a `1d361786`, avant les deux correctifs) : Done
technique confirme sur `build` (1487 a cette date, 0 echec) ; trois mutations de garde-fous recents
sur materiau de production reel (les deux garde-fous corriges par `ADR-0093`, l'extension de
l'enumeration auto-declaree d'`ADR-0050` clause 2 a `emplacementDuLibelleDeZone`) ; **un defaut de
tracabilite trouve** - le relais 1 de la decision 4 d'`ADR-0094` (refus de chargement sur une valeur
`emplacementDuLibelleDeZone` inconnue) n'a aucun test dedie, contrairement au patron
`exigenceDeForme` qu'`ADR-0094` cite lui-meme comme reference. **Ce defaut est ferme dans ce meme
sprint** : `s36-lea-fix` ajoute `DesignationContentSourceFactoryTest`, morsure verifiee (voir
section 6).

## 5. Le `relecteur-pedagogique`

**Aucune ceremonie de relecture n'a eu lieu ce sprint.** Aucun fichier `relecture.md` n'existe dans
le dossier de ce sprint, et aucune branche `s36-relecture*` n'a ete provisionnee. Je le constate
explicitement plutot que de laisser la section vide ou de sous-entendre une relecture qui n'a pas eu
lieu.

## 6. Tableau des obstacles du sprint

| Obstacle | Categorie | Route a | Statut a la cloture |
|---|---|---|---|
| Faisabilite de `S36-QLM11-PLANCLASSE`, forme d'un `ADR` a ouvrir (`ADR-0093`) | Technique | `theo-archi` | **Resolu** - avis rendu au planning (`Propose`), confirme `Accepte` a la confirmation de porte technique |
| Regle executable de la decision 6 d'`ADR-0094` absente de l'arbre | Technique | `theo-archi` (trouve par lui-meme a sa propre confirmation) | **Resolu**, meme jour, lot `s36-theo-regle` (8 tests), `ADR-0094` -> `Accepte` |
| Ecart entre la decision 5 d'`ADR-0094` (`aria-label` inconditionnel) et le gabarit servi (repli conditionnel) | Architecture | `theo-archi` (trouve par lui-meme) | **Resolu**, meme jour - decision 5 amendee en propriete gardee par la clause 2 d'`ADR-0093`, aucun changement de code route |
| Defaut de tracabilite : relais 1 d'`ADR-0094` (refus sur valeur `emplacementDuLibelleDeZone` inconnue) sans test dedie | Qualite | trouve par `yanis-qa`, route a `lea-dev` | **Resolu** - `s36-lea-fix`, `DesignationContentSourceFactoryTest`, morsure verifiee |
| `PAR-5` clause 2 : `PCL-R3` a `PCL-R6`, ecrites par `FICHE-designation-plan-de-la-classe.md`, non allouees dans `squad/decisions/REGISTRE-identifiants.md` | Qualite / gouvernance | trouve par `sofia-sm` a la fusion de `s36-nadia-audit` (`mvn -B clean verify` rouge sur `PAR-5`) | **Resolu** - reparation arithmetique (commit `a7f4c730`), ligne d'etat courant portee de 651/104 a 655/104, aucun sens touche |
| `F1` - l'ordre des puces de niveau depend de l'ordre d'apparition dans le catalogue | Produit | `camille-po` | **Resolu en principe** - `DEC-0090` Acceptee (l'ordre CP/CE1/CM1/CM2 devient une propriete garantie) ; story `S36-ORDRE-NIVEAUX` **non engagee**, en reserve au backlog faute de developpeur libre |
| `F2` - le repli de la barre de niveaux a trois puces sous 360 px n'avait jamais ete mesure a quatre niveaux | Design / technique / pedagogie | `ines-design` (propose), `theo-archi` (mesure), `nadia-prof` (juge) | **Ouvert** - la proposition d'`ines-design` (barre a trois puces) est **infirmee** par la mesure de `theo-archi` sur le rendu reellement servi (ecart de 12 px identifie), **non corrigee** |
| `F3` - aucune borne de longueur d'enonce ne couvrait le CE1 | Pedagogie | `nadia-prof` | **Resolu** - `ARBITRAGE-s36-longueur-d-enonce-au-ce1.md`, `LON-CE1-1`/`LON-CE1-2` alloues, aucun prefixe neuf |
| `F4` - absence de garde-fou transverse enoncant "niveau = chaine, jamais enum" comme regle | Technique | nommee au planning, aucune action requise | **Non engage**, inchange |
| `F5` - lecture perimee du statut de `DEC-0089` par `theo-archi` au moment de sa mesure | Gouvernance | deja resolu au planning | **Non porte comme obstacle ouvert**, pour memoire seulement |
| `PCL-R1`, `PCL-R2` - indices imprecis du plan de la classe | Pedagogie | `camille-po` | **Ouvertes**, inchangees depuis le 2026-09-11, **second sprint consecutif**, non bloquantes (deja classees ainsi a leur ouverture) |
| `PCL-R3`, `PCL-R4` - chiffre qui croise un trait sur trois zones sur six, vocabulaire positionnel detache de la position qu'il nomme | Pedagogie | `camille-po` | **Ouvertes**, neuves du 2026-09-21, non bloquantes |
| `PCL-R5`, `PCL-R6` - une zone deja essayee ne se distingue pas d'une zone neuve, la bonne zone n'est designee par rien apres la troisieme erreur | Pedagogie / technique | `camille-po` (backlog), `ines-design` (forme), `theo-archi` (cause) | **Ouverts**, neufs constats du 2026-09-21, dates de s34 par `git log -S` (pas une regression de s36), **transverses aux deux paquets de designation servis** |
| `MAT-CE1-18` - ligne manquante sur le sens de la division, trouvee par `nadia-prof` a sa propre relecture | Pedagogie | `nadia-prof` (route a elle-meme par `camille-po`, `DEC-0089` amendee) | **Resolu** - ajoutee le jour meme par `s36-nadia-division` |

## 7. Le registre de dette - inchange a la cloture de s36

**Ouverture de s36 : 9,25** (`squad/sprints/s35-fermer-ce-qui-n-a-pas-recu-le-go-pedagogique/review.md`,
section 7.4). **Aucune des reserves levees ou ouvertes par s36 ne porte sur l'un des seize postes de
`DEC-0086`/`DEC-0087`** : `cp-plan-de-la-classe` et `cp-designation-frise-de-la-journee`, comme
`cm1-histoire-geographie` avant scission, sont des paquets du **palier 2**, suivis dans
`squad/programme/MATRICE-couverture.md` et non dans le registre numerique - meme regime deja
constate pour `FRI-R6` a s35. Verifie par recherche des deux noms de paquet dans `DEC-0086` et
`DEC-0087` : aucune occurrence dans les deux textes. **Registre de dette a l'ouverture de s37 :
9,25, inchange.**

## 8. La couverture du programme

**76/138 (55,1 %) a l'ouverture de s36 -> 77/155 (49,7 %) a la cloture.** Deux mouvements distincts,
verifies chacun sur son diff exact :

- `s36-camille`, premier commit : le denominateur atteignable passe de 138 a 155 (les 17 lignes du
  referentiel `mathematiques-ce1.md`, toutes `non couvert`, aucune `couvert-valide`) - taux
  76/138 = 55,1 % -> 76/155 = 49,0 %, **par le seul effet du denominateur**, avant qu'aucun contenu
  CE1 n'existe.
- `s36-camille`, second commit : une seule ligne change de statut (`QLM-CP-11`, `non couvert` ->
  `couvert-valide`), diff d'une insertion et d'une suppression, verifie par moi sur `git show
  56d1ea8c` - numerateur 76 -> 77, denominateur inchange a 155, soit **77/155 = 49,7 %**.

**Constat, non bloquant** : la note d'en-tete de `squad/programme/MATRICE-couverture.md` (ligne 23)
cite encore 76/155 = 49,0 % ; elle date du premier commit de `s36-camille` et n'a pas ete reecrite
apres le second. Le chiffre qui fait foi pour cette cloture est celui verifie sur le diff exact du
second commit, 77/155, pas celui recopie de la note d'en-tete perimee. Correction de redaction
routee a `camille-po`, non bloquante pour aucune des deux portes.

## 9. Ce que cette review ne fait pas

- Elle ne prononce aucun Done pedagogique : le verdict `VALIDE SOUS RESERVE` de
  `S36-QLM11-PLANCLASSE` et le constat sur `competences-ce1.md` (aucun Done pedagogique, rien
  n'est jouable) sont de `nadia-prof`, dans `verdicts-nadia.md` et les fiches de conformite citees.
- Elle ne deplace aucun statut de `squad/programme/MATRICE-couverture.md` au-dela du mouvement deja
  execute par `camille-po` (`QLM-CP-11`).
- Elle ne tranche aucune priorite de sprint futur : `F2` (repli de la barre a trois puces, ouvert),
  les six reserves de `cp-plan-de-la-classe` et `S36-ORDRE-NIVEAUX` (non engagee) restent des
  elements de backlog, a composer par `camille-po`.
- Elle ne corrige pas la note d'en-tete perimee de `MATRICE-couverture.md` (section 8) : c'est un
  document de `camille-po`, la correction de redaction lui est routee plutot que faite a sa place.

## Decisions prises

**Aucune decision de sprint neuve n'est prise dans cette review.** Les decisions du sprint ont ete
prises ailleurs et sont enregistrees ici pour memoire, chacune verifiee physiquement presente sur le
disque avant d'etre citee :

- `DEC-0089` - ouverture du perimetre CE1, troisieme niveau, CM2 explicitement differe, perimetre de
  s36 borne a mathematiques seule (`camille-po`, 2026-09-21) - **Acceptee**, amendee le meme jour
  (referentiel rendu, note sur `MAT-CE1-18`). Verifie dans `squad/decisions/produit/DEC-index.md`.
- `DEC-0090` - l'ordre des niveaux affiches dans l'en-tete devient une propriete garantie, CP/CE1/
  CM1/CM2 (`camille-po`, 2026-09-21, reponse a `F1`) - **Acceptee**. Verifie dans
  `squad/decisions/produit/DEC-index.md`.
- `ADR-0093` - le nom accessible d'un bouton de zone est resolu sur le document servi et porte le
  libelle que le paquet declare (`theo-archi`, 2026-09-21) - **`Propose` -> `Accepte`** dans ce meme
  sprint. Verifie dans `squad/decisions/adr/ADR-index.md`.
- `ADR-0094` - le paquet declare ou se lit le libelle de ses zones, aucun gabarit ne nomme un contenu
  particulier (`theo-archi`, 2026-09-21) - **`Propose` -> `Accepte`** dans ce meme sprint, avec un
  amendement de sa propre decision 5 fait par son propre auteur avant l'acceptation. Verifie dans
  `squad/decisions/adr/ADR-index.md`.

**Aucun `DEC` ni `ADR` n'est cree par cette ceremonie de review elle-meme.** La reparation
arithmetique du registre d'identifiants (section 6, ligne `PAR-5`) est une mesure de gouvernance
faite par `sofia-sm` a l'endroit que le registre s'etait lui-meme fixe d'avance
(`REGISTRE-identifiants.md`, note de `nadia-prof` du 2026-09-21) - ce n'est ni un `DEC` ni un `ADR`,
et je ne lui en donne pas la forme.
