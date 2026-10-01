# Review - s45 - addition avec retenue CE1, instruments de musique CP, roles d'EPS CM1 (instance
A) et usages numeriques CM1 (instance B), dispositif a deux instances paralleles de `nadia-prof`

- **Horodatage de debut** (`planning.md`) : 2026-10-01 10:58 (UTC+02:00).
- **Horodatage de fin** : 2026-10-01 14:59:54 (UTC+02:00, horodatage du commit de fusion final
  `88cc3dd9`).
- **Duree effective** : 4 h 01 min 54 s. A la difference de s44, aucun ecart non documente : le
  dernier geste de contenu pedagogique (`0b74e721`, 14:42:37), la correction technique de
  `theo-archi` sur `MUS-CP-R3` (`a11d59ee`, 14:53:56) et la cloture de la matrice par `camille-po`
  (`32447c99`, 14:58:28) se suivent a moins de seize minutes d'intervalle chacun.
- **Casquette** : `sofia-sm`, Scrum Master.
- **Arbre revu** : `round-two` a `88cc3dd9`, mesure directement dans le checkout principal (deja
  sur `round-two`, a jour avec ce commit, `git status --porcelain` vide avant et apres chaque
  mesure - aucun worktree dedie n'etait necessaire, l'arbre a mesurer et le checkout courant
  etant deja le meme commit).

---

## 1. L'increment livre, en une phrase

Quatre lignes du programme passent de `non couvert` a `couvert-valide` (`MAT-CE1-04` addition
posee avec retenue CE1, `MUS-CP-05` instruments de musique CP, `EPS-CM1-09` roles d'arbitre,
d'observateur et de marqueur CM1, `SCI-CM1-14` usages numeriques CM1), apres un refus pedagogique
reel sur la quatrieme et sa correction en cours de sprint ; la couverture du programme passe de
104/156 (66,7 %) a **108/156 (69,2 %)**.

## 2. Le Done technique - quatre jobs, relances individuellement par moi, sur l'arbre final

Conformement a `squad/README.md` section 9 et a l'amendement du 2026-09-08 a `CLAUDE.md`, les
quatre jobs de `.github/workflows/ci.yml` ont ete relances par moi, individuellement, sur
`round-two` a `88cc3dd9` :

| Job | Commande exacte | Resultat |
|---|---|---|
| `build` | `bash outils/mvn-jdk21.sh -B clean verify` | **BUILD SUCCESS**, 6 modules sur 6, JDK constate **21.0.10** |
| `chemins` | `bash .github/scripts/verifier-chemins-test.sh` puis `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' \| bash .github/scripts/verifier-chemins.sh` | **vert** : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."* |
| `prefixes` | `bash .github/scripts/verifier-prefixes-test.sh`, `bash .github/scripts/commit-atteignable-test.sh`, puis `git log --no-merges --format=%s 00c9139b..88cc3dd9 \| bash .github/scripts/verifier-prefixes.sh` (perimetre du sprint, du dernier commit de s44 a la cloture de s45) | **vert** : *"Tous les commits portent un prefixe d'agent connu."* |
| `outils` | balayage de `outils/*-test.sh`, `squad/outils/*/*-test.sh` et `outils/*-test.py` | **vert**, **9 juge(s) pur(s) execute(s)**, 0 echec |

**Compte de tests, verifie par moi de facon independante** : somme des attributs `tests="..."`
des rapports XML Surefire/Failsafe presents sur l'arbre apres le lancement de `build`
(`**/target/{surefire,failsafe}-reports/TEST-*.xml`) : **246 fichiers, 1850 tests** - identique
au chiffre de `yanis-qa` (1850, methode des blocs `[INFO] Results:`) et de `theo-archi` (1850,
meme methode). **1850 tests, 0 echec, 0 erreur, 0 ignore.**

**ArchUnit, recompte par moi de facon independante** (`git grep -c -F` sur les fichiers suivis de
`domain`, `application`, `adapters`, `bootstrap`, pathspec d'exclusion `':!**/contreexemple/**'`) :
**36 `static final ArchRule`, 35 `@ArchTest`** - identique au chiffre final de `theo-archi` et au
mien sur la cloture de s44. L'ecart 36/38 signale par `yanis-qa` dans son rapport (section 1,
methode par nom de repertoire `src/test/java`, qui ne filtre pas une fixture de test qui reproduit
cette arborescence sous `src/test/resources`) est explique et tranche par `theo-archi`, section 2
de `confirmation-archunit.md` : **36 est le chiffre correct**, le rapport de s44 ne l'avait jamais
ecrit a tort. Je retiens cette methode, pas celle du rapport de s45 sur ce seul point.

**Porte technique verte dans son ensemble.**

## 3. La confirmation ArchUnit de `theo-archi`

`confirmation-archunit.md` (perimetre mesure `round-two` a `8bf88702`, bases de comparaison
`00c9139b` et `5ef0ac7f`) rend cinq verdicts : le compte de regles est inchange depuis s44
(36/35, `contreexemple` exclu) et l'ecart 36/38 de `yanis-qa` est une methode de comptage qui ne
mesure pas ce qu'elle annonce, pas une erreur du rapport de s44 (section 2) ; `mvn -B clean
verify` est vert sur sa propre mesure (1850 tests) ; aucun `ADR` n'est du au-dela d'`ADR-0103`
(aucun `src/main/java`, aucun `pom.xml`, aucune cle de schema ni mecanique ni matiere neuve) ; les
quatre paquets respectent la Clean Architecture (zero fichier de `domain`/`application` touche,
zero import `springframework` ajoute, zero chargeur neuf). `theo-archi` lance aussi les trois
autres jobs sur son propre diff (section 6 de son fichier), tous verts. Cette confirmation est
anterieure au correctif `MUS-CP-R3` (`a11d59ee`, posterieur de pres d'une heure et demie) : elle
ne porte donc pas sur ce correctif, qui ne touche que deux fichiers de test et ne change aucun des
cinq verdicts ci-dessus.

## 4. Le rapport qualite de `yanis-qa`

`rapport-qualite.md` (worktree dedie `s45-yanis` a `12fd0ae4`, lignee verifiee) : Done technique
confirme sur les quatre jobs, tous nommes individuellement. Delta de tests explique en deux temps
et test par test : `adapter-content` passe de 974 (s44) a 977 (apres fusion d'`ADR-0103`, avant le
developpement des quatre lots, +3 exactement les mises a l'epreuve neuves d'`ADR-0103`) puis a
1019 (+42, exactement la somme des `@Test` des quatre fichiers neufs : 12 + 10 + 10 + 10). Les
trois fusions conflictuelles (`fdef689b`, `16b4d799`, `12fd0ae4`) sont verifiees ligne par ligne
sur les trois fichiers transverses touches : `catalogue.yaml` passe de 106 a 110 entrees, aucun
identifiant duplique ; les quatre chaines neuves de `TexteAccentueExposeParLePortEntrantTest`
sont des insertions pures ; sur le garde-fou de rang, les trois paquets textuels entrent a bon
droit dans la liste d'exemption et `ce1-additions-posees`, seul paquet a propositions numeriques,
en reste a bon droit absent - verifie non par le vert du test seul mais en relisant les items.
`ADR-0103` est remis a l'epreuve par mutation sur le paquet reellement servi, dans les deux sens
(rouge attendu obtenu sur un paquet mute pour redeclarer `MAT-CE1-03`, vert confirme apres
restauration), pas seulement sur la fixture de `theo-archi`. Chaque critere d'acceptation des
quatre stories est verifie methode de test par methode de test, aucun reste sans test.

## 5. Le `relecteur-pedagogique`

Un commit, `76809af7` (fusion `9fcae7dc`) : un enonce de `cm1-eps-roles.yaml` reformule pour
retirer une construction qui isolait le groupe sujet ("L'arbitre accorde-t-il ce coup franc a
quelle equipe ?" devient "A quelle equipe l'arbitre accorde-t-il ce coup franc ?"). Corrige, tout
le reste confirme.

## 6. Le Done pedagogique - deux instances paralleles, rapporte par cette review, pas prononce par
elle

Dispositif a deux instances de `nadia-prof` (`DEC-0101`, section 4 et 7 ; `PAR-2` verifie par moi
en planning, section 3, et reverifie ici sans objection).

**Instance A** - `S45-MAT-ADDITION`, `S45-MUS-INSTRUMENTS`, `S45-EPS-ROLES`. Budget individuel
5,0, cout retenu 4,0 (1,0 + 1,5 + 1,5), marge 1,0. Un seul commit, `b6d3630c` (fusion `c26b8eaa`),
apres six parties jouees par jeu sur le jar servi :

| Ligne | Verdict final | Reserves | Ferme |
|---|---|---|---|
| Addition posee avec retenue (`MAT-CE1-04`) | **VALIDE**, aucune reserve | aucune | `couvert-valide`, mention de couverture : "le resultat atteste le calcul ; la technique posee elle-meme n'est ni montree ni verifiee par le produit" |
| Instruments de musique (`MUS-CP-05`) | **VALIDE SOUS RESERVE** | `MUS-CP-R2` (items 1 et 2, l'indice contient le mot de la bonne reponse "cordes"), `MUS-CP-R3` (defaut technique de deux garde-fous de test, pas un defaut de contenu - voir section 7) | `couvert-valide`, mention de couverture partielle portee mot pour mot et commune avec `MUS-CP-11` |
| Roles d'EPS (`EPS-CM1-09`) | **VALIDE SOUS RESERVE** | `EPS-CM1-R5` (items 5 et 6, deux regles de sport inexactes - volley-ball, basket-ball), `EPS-CM1-R6` (items 1, 4 et 8, un distracteur que personne ne choisit) | `couvert-valide`, mention de couverture : "connaissance et application de la regle seules ; la tenue du role en situation reste non couverte" |

**Instance B** - `S45-SCI-NUMERIQUE` seule. Budget individuel 5,0, cout retenu 1,5, marge 3,5.
**Un refus pedagogique reel, le premier depuis plusieurs sprints**, suivi d'une correction et
d'une revalidation, trois commits :

1. `8266e4fb` (fusion `033ef7bc`, 13:47:12) - **REFUSE**. Conditions ecrites (`SCI-1` a `SCI-4`,
   `HIS-3`, `CON-1`, `FER-1` a `FER-3`) tenues a la lettre, mais deux faits faux : l'item 2 donne
   l'age d'accord parental a l'envers (13 ans au lieu de 15 en France) et se contredit lui-meme
   contre son propre marqueur `HIS-3` ; l'item 6 presente un mot de passe de 8 caracteres comme
   solide, contre la recommandation CNIL de 2022. Six points non bloquants listes a cote.
2. `1c8d98d7` (14:21:41, moi, casquette dev) - correction des deux regles : item 2 reformule
   ("en dessous de 15 ans"), item 6 porte a 12 caracteres et quatre types de caracteres, les deux
   marqueurs `HIS-3` recites avec leur source exacte.
3. `0b74e721` (fusion `46f28adf`, 14:42:37) - **VALIDE SOUS RESERVE** au second passage. Les deux
   motifs de refus leves, `SCI-3` tenue a la borne (2 items sur 12). Neuf reserves non bloquantes
   `USN-CM1-R1` a `USN-CM1-R9` : les six points du premier passage, deux neuves sur les items
   corriges, et `USN-CM1-R9` - **une erreur de fait que `nadia-prof` introduit elle-meme** en
   corrigeant le marqueur `HIS-3` de l'item 2 au premier passage (elle date l'article 45 de "la
   loi du 20 juin 2018", alors que le seuil de 15 ans vient de cette loi mais sa numerotation en
   article 45 vient de l'ordonnance du 12 decembre 2018) : `camille-po` l'isole et la place en
   priorite la plus haute des six stories de reserve (section 7, 8).

`SCI-CM1-14` ferme `couvert-valide`, mention de couverture : "regles et connaissances d'usage
seules ; la pratique d'un environnement numerique reste non couverte".

**Bilan du Done pedagogique du sprint** : un refus, leve en cours de sprint par une correction de
contenu testee et revalidee, zero refus restant a la cloture. A comparer a zero refus sur trois
sprints consecutifs (s41 excepte) : voir `retro.md`, section 2.

## 7. Tableau des obstacles du sprint

Les six premieres lignes sont les questions Q1 a Q8 de l'avis de faisabilite de `theo-archi`
(`squad/rapports/avis-faisabilite-s45-cinq-lots.md`, section 12), toutes levees **avant
l'horodatage de debut du planning** (2026-10-01 10:58), le meme jour :

| Obstacle | Categorie | Route a | Statut a la cloture |
|---|---|---|---|
| Q1 - Definition calculable des trois erreurs d'`ADP-CE1-5` (retenue oubliee, reportee deux fois, mauvaise colonne) | Pedagogie / formulation d'un contenu | `nadia-prof` | **Resolu avant l'ouverture du planning** - arbitrage prealable (`a56b981a`/`e79204c4`, 10:45:02), tableau d'exemples chiffres repris tel quel par `marc-dev` |
| Q2 - Criteres d'`ADP-CE1-1`, `ADP-CE1-3`, `ADP-CE1-5` de `S45-MAT-ADDITION` a corriger contre leur source | Produit / redaction d'une story | `camille-po` | **Resolu avant l'ouverture du planning** - corrections fusionnees (`98573783`/`66930b93`, 10:26:43), verifiees testees en section 5 de `rapport-qualite.md` |
| Q3 - La forme "cordes ou vent ?" est-elle admise sur `MUS-CP-05` malgre `MUS-CP-R1` ? | Pedagogie / formulation d'un contenu | `nadia-prof` | **Resolu avant l'ouverture du planning** - meme arbitrage, section 3 : forme refusee, exemple de remplacement fourni ("De quelle famille d'instruments est la guitare ?") |
| Q4 - Discriminant CM1/CP entre `EPS-CM1-09` et `cp-eps-regles-jeu-collectif`, et lecture de `FER-1` sur les trois roles | Pedagogie / formulation d'un contenu, condition du verdict | `nadia-prof` | **Resolu avant l'ouverture du planning** - meme arbitrage, section 4 : discriminant `EPS-10` pose (quatre exigences), repris comme critere d'acceptation et verifie par `yanis-qa` |
| Q5 - Le motif de `R6` (refus de la saisie sous correction automatique de clavier) s'applique-t-il a `LVE-CM1-06` ? | Pedagogie / perimetre d'un contenu, pouvant retirer le lot | `nadia-prof` | **Resolu avant l'ouverture du planning** - meme arbitrage, section 5 : `R6` vaut sur l'anglais CM1, le motif est plus fort qu'en francais ; le lot est retire (ligne suivante) |
| Q7 - Criteres de `S45-SCI-NUMERIQUE` a corriger (`SCI-1` sur l'indice et non l'explication, `SCI-4`, `FER-1` a `FER-3`, `HIS-3`, `CON-1`) | Produit / redaction d'une story, condition du verdict | `camille-po` | **Resolu avant l'ouverture du planning** - corrections fusionnees (`98573783`/`66930b93`) ; **la correction du texte de la story n'a pas empeche un refus pedagogique different, sur deux erreurs factuelles de contenu non couvertes par ce critere-la** (section 6) |
| Q8 - Planifier l'`ADR` restreignant `ChaquePaquetDAdditionsDeCe1TientSaRepartitionParFormeTest`, et la dette `ANG-S1`/`ANG-S2` non allouee au registre d'identifiants | Technique / architecture (l'`ADR`), capacite (la dette) | `theo-archi` (`ADR`), `sofia-sm` (dette) | **`ADR` resolu avant l'ouverture du planning** - `ADR-0103` (`ccfe7df1`/`7c2b091f`, 10:39:26), mis a l'epreuve dans les deux sens par `yanis-qa`. **Dette non allouee, et sans objet pour ce sprint** - `S45-LVE-ECRITURE` n'a jamais ete ecrite (ligne suivante), donc `ANG-S1`/`ANG-S2` ne sont cites dans aucun paquet servi ; la dette reste ouverte pour le jour ou l'anglais CM1 serait repris |
| Retrait de `S45-LVE-ECRITURE` (`LVE-CM1-06`), motif `R6` (Q5) | Produit / perimetre | `camille-po` | **Resolu avant l'ouverture du planning** - `5b8ca0f8`/`5ef0ac7f`, 10:53:53, 2,5 de tarif liberes sur l'instance B ; aucune ligne de code ecrite, verifie negativement par `yanis-qa` (section 7 de son rapport) |
| Decouverte retroactive `S46-VERIF-SAISIE-MOT-DICTE-CP` - `R6` n'a jamais ete instruit sur `saisie-mot-dicte-cp` (`couvert-valide` depuis s26) | Pedagogie / Done deja prononce, a rejouer | `nadia-prof` (constat), `camille-po` (ouverture de story) | **Ouvert, hors perimetre de s45** - signale par `nadia-prof` en instruisant Q5 (arbitrage, section 5.3), story ouverte dans `docs/backlog/backlog.md` (priorite haute) au meme commit que le retrait (`5b8ca0f8`) ; aucun lot de s45 ne la traite |
| Refus pedagogique de `SCI-CM1-14` - deux erreurs factuelles (age d'accord parental a l'envers, mot de passe sous-dimensionne) | Pedagogie / exactitude d'un contenu | `nadia-prof` (constat du refus), `sofia-sm` (casquette dev, correction du contenu) | **Resolu, au second passage** - refus `8266e4fb` (13:47:12), correction `1c8d98d7` (14:21:41), revalidation `0b74e721` (14:42:37) |
| `MUS-CP-R3` - deux garde-fous lexicaux de test d'education musicale CP (vocabulaire s44, instruments s45) ne repliaient pas les accents, et l'un laissait passer une forme d'enonce deja refusee par `nadia-prof` | Technique / qualite d'un garde-fou de test | `theo-archi`, `yanis-qa` (nomme explicitement par la mention de couverture de `MUS-CP-05`) | **Resolu** - `a11d59ee` (14:53:56, fusion `88cc3dd9`), cause lue au code (repliage de casse sans repliage d'accents), corrige par repliage NFD local, prouve par mutation dans les deux sens sur le materiau servi ; **le contenu livre etait sain** - les formes qui avaient alerte `nadia-prof` venaient de ses propres mutations de test pendant l'audit, pas du depot |

## 8. La couverture du programme

**108/156 (69,2 %)**, verifie par moi sur `squad/programme/MATRICE-couverture.md`, commit
`32447c99` (fusion `6b5e981a`, *"cloture matrice - MAT-CE1-04, MUS-CP-05, EPS-CM1-09, SCI-CM1-14
passent a couvert-valide"*) : quatre fermetures, aucune ouverture partielle, aucun refus a lever -
en hausse de 2,5 points sur s44 (104/156, 66,7 %). La mention de couverture de `MUS-CP-11`
(fermee a s44) est corrigee dans le meme commit, par un bloc date qui n'efface rien : sa dernicre
clause ("les familles d'instruments restent non couvertes") devient fausse le jour meme ou
`MUS-CP-05` ferme, et une mention commune aux deux lignes est ajoutee, exigee d'avance par la
fiche de conformite de `MUS-CP-05` (section 6).

## 9. Ce que cette review ne fait pas

- Elle ne prononce aucun Done pedagogique : les quatre verdicts de `nadia-prof` (section 6) sont
  deja prononces ; cette review les rapporte, elle ne les rend pas.
- Elle ne deplace aucun statut de `squad/programme/MATRICE-couverture.md` au-dela de ce que
  `camille-po` a deja execute.
- Elle n'ouvre ni ne priorise les six stories de correction differee des reserves (`USN-CM1-R9`,
  `USN-CM1-R1` a `-R8`, `MUS-CP-R2`, `EPS-CM1-R5`, `EPS-CM1-R6`) ni le signalement technique
  `MUS-CP-R3` : `camille-po` les a deja ecrites dans `docs/backlog/backlog.md` (commit `32447c99`),
  et `MUS-CP-R3` est deja ferme par `theo-archi` (commit `a11d59ee`) ; les affecter a un planning
  futur n'appartient pas a cette ceremonie.
- Elle ne traite pas `S46-VERIF-SAISIE-MOT-DICTE-CP` : obstacle ouvert, signale en section 7,
  route a `camille-po` pour instruction dans un sprint futur.

## Decisions prises

**Aucune `DEC` ni `ADR` n'est creee ou amendee apres l'ouverture du planning (2026-10-01 10:58).**
`DEC-0101` (`14237f23`/`c15a6c4d`, 09:24) et `ADR-0103` (`ccfe7df1`/`7c2b091f`, 10:37-10:39),
ainsi que leurs amendements respectifs a `DEC-0100` et `ADR-0096`, sont tous crees **avant**
l'horodatage de debut de ce planning, le meme jour - prealables necessaires a son ouverture, pas
des decisions prises pendant la fenetre du sprint au sens strict. `git diff --name-status
00c9139b 88cc3dd9 -- squad/decisions` confirme qu'aucun fichier sous `squad/decisions/adr/` ou
`squad/decisions/produit/` ne bouge apres `5ef0ac7f` (dernier commit avant l'ouverture du
planning), hormis `REGISTRE-identifiants.md` (allocation d'identifiants par `nadia-prof` et par
`camille-po`, pas une decision).

Une seule decision m'appartient en propre dans ce sprint, deja ecrite dans `planning.md`,
section 6 : regrouper les deux lots de basse complexite mecanique (`S45-MUS-INSTRUMENTS`,
`S45-SCI-NUMERIQUE`) sur moi-meme, casquette dev, un par instance, et affecter `S45-MAT-ADDITION`
a `marc-dev`, `S45-EPS-ROLES` a `lea-dev`. Elle n'est pas reprise une seconde fois ici.
