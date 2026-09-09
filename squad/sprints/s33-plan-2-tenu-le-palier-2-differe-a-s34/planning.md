# [Sprint s33] Planning - 2026-09-09

- **Horodatage de debut** : 2026-09-09 18:20
- **Anime par** : sofia-sm
- **Participants** : camille-po, nadia-prof, theo-archi (avis a rendre), marc-dev, lea-dev, sofia-sm

## 0. Ce qui est deja entre, avant l'ouverture - fusion de `s33-nadia`

`sofia-sm`, casquette dev, a fusionne `s33-nadia` (`a9ab05ec`, arbitrage prealable de `nadia-prof`)
dans `round-two` avant ce planning (commit `7e5a9d00`). Collision annoncee des deux cotes : `PAR-14`,
`EMC-11`, `EPS-7`, `HIS-5` etaient alloues a la fois sur `round-two` (par `camille-po`, geste
mecanique pointant vers `docs/backlog/backlog.md`) et sur `s33-nadia` (par `nadia-prof`, pointant
vers son arbitrage prealable). Une seule ligne gardee par identifiant - celle de `nadia-prof`, source
qui definit les quatre conditions. Verifie que les quatre ne divergent pas de definition entre
`docs/backlog/backlog.md`, `DEC-0085` et l'arbitrage : aucun ecart. Compte recompte sur le fichier
assemble, non additionne : **582 identifiants, 97 prefixes**, zero doublon de definition.

Quatre jobs de la CI mesures sur l'arbre assemble et nommes un par un : **prefixes** vert,
**chemins** vert, **outils** vert (9 juges purs), **build** vert (`mvn -B clean verify`, reacteur
complet a six modules, **1439 tests**, 0 echec, JDK 21.0.10 rapporte par la machine virtuelle des
tests). Le compte direct des fichiers `surefire-reports` sur le disque donne 1432 : quatre classes
de tests (`LesReglesMordentTest`, `ChargementDuContenuTest`, `AbstractionsCiteesTest` dans
`bootstrap`, `AssertionsWebTest` dans `adapter-web`) s'executent deux fois dans la meme session et
leur rapport est ecrase par la seconde execution ; le total juste est celui que Maven annonce par
module, verifie par la somme des lignes console par classe : 1439, identique a la mesure d'avant
fusion - le diff etait documentaire et n'a change aucun test.

## Objectif du sprint

Fermer autant que possible des neuf lignes que la composition de `DEC-0085` finance sous le Plan 2
(borne 12,0, plafond 9 lignes), sans rouvrir ni la strategie ni le palier 2 - reporte a s34 - et
sans reproduire les deux classes de defaut que la retro de s32 a nommees (section 11 ci-dessous).
Gele : il ne change plus pendant le sprint.

## 1. Taux d'entree du sprint

**66 / 138 = 47,8 %** (66 `couvert-valide`, 4 `en cours`, denominateur atteignable 138 - compte de
`DEC-0084`, repris et non recalcule).

## 2. La borne de s33 : 12,0, composition reprise sans recalcul (`DEC-0085`, section 3)

**La borne reste 12,0** (trois instances a 4,0, `PAR-9` ; plafond individuel dur 5,0, `PAR-8`).

| Instance | Terrain | Postes | Total | Lignes visees |
|---|---|---|---|---|
| **A** | Histoire-geographie CM1, anglais CM1, portee D (`PAR-12`) | Remplissage `HG-CM1-08`/`-12` (2,0) ; reprise du meme paquet - `HG-R8` a `HG-R12`, reliquat `CONS-1` (0,5) ; `cm1-anglais` `ANG-R3` a `R6` (0,5) ; passe transverse (0,5) ; marge `PAR-11` (0,5) | **4,0** | **+4** |
| **B** | EMC CP, en entier (`PAR-7` lui permet aussi les sciences CM1, mais 4,0 n'en paie qu'une) | `EMC-CP-07`, quatrieme passage (0,5) ; six paquets d'EMC CP portant les treize reserves neuves de s32 (3,0) ; marge non allouee (0,5) | **3,5** | **+1** |
| **C** | Education physique et sportive, CP puis CM1 (`PAR-2`) | Reprise `EPS-CP-04` (0,5) ; reprise `EPS-CP-08` au tarif de lot (`PAR-14`, 1,0) ; lot de l'effort et du corps `EPS-CP-10`/`-11` (1,5) ; reserves du lot soldees (0,5) ; marge `PAR-11` (0,5) | **4,0** | **+4** |
| **Total** | | | **11,5 sur 12,0** | **+9** |

Le sciences CM1 ne rentre pas dans le budget de l'instance B : les deux terrains qu'elle detient
(sciences CM1 depuis s30-s31, EMC CP depuis s32) coutent 6,0 ensemble, au-dessus du plafond
individuel dur de 5,0, et ni A ni C n'a de marge a transferer (toutes deux a 4,0). `nadia-prof`
recommande de finir l'EMC CP plutot - `CAT-1` a son cinquieme sprint de report, `EXC-R5` son
deuxieme, `REG-R3` porte un stereotype de genre signale et aggrave par le dernier lot. Ce choix est
repris tel quel, non rediscute.

**0,5 non alloue par `nadia-prof`** (marge de l'instance B) : peut acheter un paquet du residu du
groupe 2a sur les sciences, ou un transfert ecrit vers A ou C (`PAR-9`). Ce choix m'appartient et je
ne le tranche pas dans ce planning - aucune ligne n'en depend, et le trancher maintenant grelerait un
arbitrage qui n'a pas besoin de l'etre avant que le sprint avance.

## 3. Stories retenues

Les dix stories ecrites a la cloture de s32 (`DEC-0084`) plus `S33-HG-08-12`, seule story neuve
ouverte par `DEC-0085`. Les trois stories du palier 2 (`S33-PAL2-TROU-A`, `-TROU-B`, `S33-PAL2-1`)
sont au backlog de s34, non tirees ici.

| Story | Titre | Competence programme visee | Decision liee | Affectee a |
|---|---|---|---|---|
| `S33-HG-02` | Corriger le fait faux sur le sacre de Clovis, jamais eu lieu (`HG-R11`) | Histoire-geographie, CM1 - `HG-CM1-02` | `verdicts-nadia-instance-A.md` (s32), section 4 | `marc-dev` (contenu), instance A |
| `S33-HG-04` | Remplacer "les Sarrasins" par une designation qui nomme un peuple (`HG-R12`) | Histoire-geographie, CM1 - `HG-CM1-04` | `verdicts-nadia-instance-A.md` (s32), section 5 | `marc-dev` (contenu), instance A |
| `S33-HG-08-12` | Remplir `HG-CM1-08` et `HG-CM1-12` sans reprendre une connaissance deja servie | Histoire-geographie, CM1 - `HG-CM1-08`, `HG-CM1-12` | arbitrage prealable de s33, sections 3.1, 3.2, 5.2, 9.2, 9.3 ; `DEC-0085` | `marc-dev` (contenu), instance A |
| `S33-EMCCP07` | Quatrieme passage sur `bandit-manchot-regle-souhait-loi-cp` : le plus petit perimetre du refus | EMC, CP - `EMC-CP-07` | `verdicts-nadia-instance-B.md` (s32), sections 3, 13 ; arbitrage prealable de s33, section 3.4 | `lea-dev` (contenu), instance B |
| `S33-CAT1` | Deuxieme tentative de paiement de `CAT-1` sur `regles-de-vie-cp` et `permis-interdit-cp` | EMC, CP - `EMC-CP-02`, `EMC-CP-06` | `verdicts-nadia-instance-B.md` (s32), sections 1, 4, 10, 13 | `lea-dev` (contenu), instance B |
| `S33-EXC-R1` | La consigne de `bandit-manchot-exclusion-cp` nomme la tache reellement servie | EMC, CP - `EMC-CP-03`, correction non bloquante | `confirmation-archunit.md` (s32), section 4 ; `verdicts-nadia-instance-B.md`, sections 8, 13 | `lea-dev` (contenu), instance B |
| `S33-EXC-R5` | Aucun item ne fait d'un adulte reel une reponse fausse | EMC, CP - `EMC-CP-03`, correction non bloquante | `confirmation-archunit.md` (s32), section 4 ; `verdicts-nadia-instance-B.md`, sections 8, 13 | `lea-dev` (contenu), instance B |
| `S33-EPS-SEC` | Reecrire les huit distracteurs de la tache B de `bandit-manchot-regles-securite-eps-cp` (`SEC-R3`) | EPS, CP - `EPS-CP-04` | `verdicts-nadia-instance-C.md` (s32), sections 5, 11 ; arbitrage prealable de s33, section 3.5 | `sofia-sm` (casquette dev), instance C |
| `S33-EPS-JEU` | Corriger quatre erreurs de fait et deux items au registre de `bandit-manchot-regles-jeu-collectif-eps-cp` (`JEU-R1` a `JEU-R6`) | EPS, CP - `EPS-CP-08` | `verdicts-nadia-instance-C.md` (s32), sections 6, 11 ; arbitrage prealable de s33, sections 5.5, 6.4 | `sofia-sm` (casquette dev), instance C |
| `S33-CONS-4` | Le grain de `bandit-manchot-histoire-geographie-cm1` empeche sa consigne d'informer l'enfant | Transverse - aucune ligne de matrice | `verdicts-nadia-instance-A.md` (s32), section 8 ; `ADR-0081` | **bloquee** - decision de grain due de `camille-po` et `nadia-prof` avant ouverture (section 8) |
| `S33-SCO-1` | Preuve neuve : le score reste parfait par elimination (renvoi a `S25-SCO-1`) | Transverse - aucune ligne de matrice | `S25-SCO-1` ; `DEC-0061` ; `DEC-0083` (routage `D7`) | **bloquee** - remede technique du a `theo-archi`, non chiffre par `nadia-prof` (section 8) |

## 4. Avis de faisabilite - `theo-archi`

**Precedent de s32, repris avec une exception nommee** : a s32, j'avais juge moi-meme les stories de
contenu pur faisables en l'etat sans solliciter `theo-archi` par story - motif ecrit dans le
planning de s32, section 5. Ce sprint compte **deux reprises qui ne sont pas du contenu pur**,
verifie sur le code de test avant d'ecrire cette ligne :

| Story | Avis | ADR requis |
|---|---|---|
| `S33-HG-02`, `S33-HG-08-12` | Faisable en l'etat par moi - reecriture et ajout d'items sur un paquet existant (`cm1-histoire-geographie`), aucun code hors contenu, aucune assertion de test ne code en dur le texte touche | Non |
| `S33-HG-04` | **Pas du contenu pur.** Le remplacement de "les Sarrasins" en bonne reponse casse l'enumeration litterale `Set.of("les peuples germaniques", "les Vikings", "les Sarrasins")` de `PaquetCm1HistoireGeographieTest` (ligne 356, methode `fer3AuMoinsDeuxTachesSurHgCm104LaMoinsServieAuMoinsTroisItems`), qui doit etre mise a jour dans le meme commit. **Avis de `theo-archi` manquant** : a solliciter avant l'ouverture de la branche, je ne l'invente pas ici | A determiner par `theo-archi` |
| `S33-EMCCP07`, `S33-CAT1` | Faisable en l'etat par moi - reecriture de propositions et de distracteurs sur des paquets existants (`cp-emc-regle-souhait-loi`, `cp-emc-regles-de-vie`, `cp-emc-permis-interdit`), verifie qu'aucun des trois tests de paquet ne code en dur les chaines touchees | Non |
| `S33-EXC-R1` | **Pas du contenu pur.** La story elle-meme le porte dans ses criteres : l'inventaire de `TexteAccentueExposeParLePortEntrantTest` est mis a jour dans le meme commit. `theo-archi` a mesure a s32 que ce geste ne coute rien de plus (`ADR-0089`) mais n'a pas encore rendu d'avis sur ce diff precis de s33. **Avis manquant** : a solliciter, je ne l'invente pas | Non (le mecanisme est deja couvert par `ADR-0089`), avis sur le diff reel a rendre |
| `S33-EXC-R5` | Faisable en l'etat par moi - l'obstacle technique (`PaquetCpEmcExclusionTest`) est deja leve depuis s32 (`ADR-0089`, decision E) ; ce qui reste est du texte seul, sur un paquet existant | Non |
| `S33-EPS-SEC`, `S33-EPS-JEU` | Faisable en l'etat par moi - reecriture de distracteurs et d'indices sur des paquets existants (`cp-eps-regles-securite`, `cp-eps-regles-jeu-collectif`), aucun code hors contenu ; `PAR-14` est une regle du budget d'audit pedagogique, pas une regle technique | Non |
| `S33-CONS-4` | Non evaluable avant que la decision de grain (`camille-po` et `nadia-prof`) soit rendue - la story elle-meme l'ecrit | A determiner apres la decision |
| `S33-SCO-1` | Releve entierement de `theo-archi` (remede `D7`, `SCO-1` a `SCO-7`) : avis technique attendu, non rendu a ce planning | A determiner par `theo-archi` |

## 5. Affectation des trois instances de `nadia-prof`, par terrain

Fixee par l'arbitrage prealable de s33, section 6.1, reprise sans la rouvrir :

| Instance | Condition de terrain | Stories auditees | `PAR-13` |
|---|---|---|---|
| **A** | `PAR-12` (instance d'accumulation) | `S33-HG-02`, `S33-HG-04`, `S33-HG-08-12` | Terrain deja rencontre (histoire-geographie CM1 a s27, s28, s29, s32) |
| **B** | `PAR-7` (deux images, une seule payee) | `S33-EMCCP07`, `S33-CAT1`, `S33-EXC-R1`, `S33-EXC-R5` | Terrain deja rencontre (EMC CP a s29, s31, s32) |
| **C** | `PAR-2` (matiere reservee) | `S33-EPS-SEC`, `S33-EPS-JEU` | Premier sprint depuis s29 ou aucune instance ne prend un terrain totalement neuf (EPS a s32 seulement) - `EPS-2`/`EPS-3` restent sans contenu, nomme sans etre facture (`PAR-11` amendee) |

## 6. Affectation nominative des trois developpeurs

**Motif de s32, repris a l'identique** : un ensemble de paquets qui partagent le meme fichier
`catalogue.yaml` va a un seul developpeur, pour eviter la collision d'ecriture deja rencontree a
s30 et s31 sur ce fichier.

- **`marc-dev`** : `S33-HG-02`, `S33-HG-04`, `S33-HG-08-12` - un seul paquet (`cm1-histoire-geographie`), terrain A, continuite avec s32 ou il en detient deja l'image.
- **`lea-dev`** : `S33-EMCCP07`, `S33-CAT1`, `S33-EXC-R1`, `S33-EXC-R5` - les paquets d'EMC CP, terrain B, continuite avec s32.
- **`sofia-sm`** (casquette dev) : `S33-EPS-SEC`, `S33-EPS-JEU` - les paquets d'EPS CP, terrain C. Je la reprends moi-meme comme a s32, pour la meme raison : le terrain B porte deja quatre stories, la charge reste equilibree entre les trois developpeurs plutot que concentree sur Lea.

Trois worktrees independants. Je fusionnerai les branches moi-meme, casquette dev, dans l'ordre ou
elles arrivent - `catalogue.yaml` ne recoit que des ajouts de blocs disjoints par terrain, jamais
deux ecritures sur la meme ligne.

## 7. Plafond et attente, cote a cote, contre le taux d'entree

| | Lignes | Taux |
|---|---|---|
| Taux d'entree de s33 | 66 / 138 | **47,8 %** |
| Plafond arithmetique de s33 (+9) | 75 / 138 | **54,3 %** |
| Attente raisonnable de `nadia-prof` (+4 a +6) | 70 a 72 / 138 | **50,7 % a 52,2 %** |

Motif de l'attente (arbitrage prealable, section 7) : deux des neuf lignes portent une attente de 1
et non un intervalle (`HG-CM1-02`, `HG-CM1-04` - la formulation juste existe deja mot pour mot dans
le meme fichier) ; les sept autres portent une attente de 0 a 1 chacune. Si s33 se comporte comme
s32, il ferme 3 lignes et la couverture atteint 69/138 = 50,0 %.

## 8. `PAR-14`, clause neuve, et son prix sur une des reprises planifiees

**`PAR-14`, nouvelle, non retroactive.** Une reprise dont les corrections touchent plus de la moitie
des items d'un paquet se paie au tarif d'un lot (1,0 quand `nadia-prof` est la source des motifs,
1,5 sinon), et non au tarif d'un rejeu (0,5). Mesuree sur `EPS-CP-08` : six items sur onze corriges
par `S33-EPS-JEU`. **Elle change le prix de cette reprise** : au tarif de rejeu elle aurait coute
0,5, elle est chiffree 1,0 dans la composition de l'instance C (section 2). Aucune autre reprise de
ce sprint n'atteint la moitie de son paquet ; la clause ne s'applique donc qu'a `S33-EPS-JEU`.

## 9. Le registre de dette : 9,0 a l'ouverture de s33, et ce que la borne ne finance pas

**Le registre a grossi** : 7,5 a l'ouverture de s32, **9,0 a l'ouverture de s33** - s32 a ferme trois
lignes et laisse la dette plus grosse qu'il ne l'a trouvee, sous le plan choisi precisement pour
l'empecher. Je l'ecris sans l'adoucir, comme `nadia-prof` l'a ecrit dans son arbitrage. Si le
plafond de 9 lignes est atteint a la cloture, le registre descend a 2,5 - la premiere baisse depuis
s30 - mais **ce que la borne de 12,0 ne finance pas a s33 reste hors de ce sprint** :

- **Aucun lot de sciences CM1** - la dette la plus ancienne du depot et le terrain le plus fourni
  appartiennent a la meme instance, et son budget ne paie qu'un des deux terrains qu'elle detient.
- **L'instruction des onze lignes sans annotation de jouabilite** - portee a s34.
- **Le groupe 2b** (`CONS-2`, `SAI-R1`, `ERR-R1`, reliquat de `CONS-1` hors des lignes ouvertes ici).
- **`CONS-3`**, toujours non chiffree.
- **1,5 du residu du groupe 2a** (sur 2,5 payables) - `POS-R3`, `FOP-R4`, `DRO-R4`, sans instance
  nommee pour les porter.

## 10. Trois postes finances sans story ecrite - un ecart nomme, pas comble

La composition de la section 2 finance trois postes qu'aucune story de `docs/backlog/backlog.md` ne
porte a ce jour : `cm1-anglais` `ANG-R3` a `R6` (0,5, instance A), la passe transverse de l'instance
A (0,5), et le lot de l'effort et du corps `EPS-CP-10`/`EPS-CP-11` (1,5, instance C, +2 lignes
visees dans le total de la section 2). **Une story qui n'est pas dans `docs/backlog/` n'existe pas**
(`squad/README.md`, section 4) : je ne les tire donc pas dans la section 3 et je ne les invente pas
ici. Route a `camille-po` : soit ces trois postes recoivent une story avant que les instances
concernees ne les engagent, soit l'argent qui leur est reserve reste inemploye ce sprint.

## 11. Ce que la retro de s32 a nomme, et qui mord ici

- **La compression qui casse la langue.** `lea-dev` avait trouve a s32 que trois corrections
  independantes de `LON-1` partageaient une seule cause : retirer une subordonnee pour tenir une
  barre de longueur sans reconstruire la phrase autour. `S33-EMCCP07` (item 4, ternaire redevenant
  binaire), `S33-HG-02` et `S33-HG-04` (reformulations "mot pour mot") et `S33-EXC-R1` (reecriture
  de consigne) raccourcissent ou remplacent du texte lu par un enfant : chaque developpeur verifie
  que la phrase qui reste se lit entiere, et non qu'un mot a ete ote sans que le reste du francais
  soit repris autour.
- **Le correctif qui reproduit la classe de defaut qu'il paie.** Le correctif de `CAT-1` a s32 a
  remplace un repere de registre par un repere lexical - "maitresse" est passe de 1 a 7 occurrences
  dans les distracteurs, sans qu'aucune bonne reponse ne le porte, ce qui laisse un enfant
  reconnaitre le mot au lieu de lire la clause de registre. **`S33-CAT1` est la deuxieme tentative de
  paiement de la meme condition.** `lea-dev` ne peut pas se contenter de diversifier le vocabulaire
  des distracteurs : la clause de `CAT-1` (les trois propositions du meme registre) doit se lire
  dans le registre de chaque proposition, jamais dans la presence ou l'absence d'un mot recurrent.

## 12. Ce que je route, pas ce que je tranche

| Obstacle | Categorie | Route a | Attendu |
|---|---|---|---|
| Decision de grain de `S33-CONS-4` | Produit / pedagogie | `camille-po` et `nadia-prof`, ensemble (`ADR-0081`) | Decision ecrite avant l'ouverture de la story |
| Remede technique de `SCO-1` a `SCO-7` (`D7`) | Technique | `theo-archi` | Avis technique, puis chiffrage par `nadia-prof` |
| Avis de faisabilite sur `S33-HG-04` | Technique | `theo-archi` | Avis avant l'ouverture de la branche `marc-dev` |
| Avis de faisabilite sur `S33-EXC-R1` (diff reel de s33) | Technique | `theo-archi` | Avis avant l'ouverture de la branche `lea-dev` |
| Grain du lot de l'effort et du corps (un paquet ou deux, `EPS-CP-10`/`-11`) | Technique / produit | `camille-po` et `nadia-prof`, ensemble | Decision - si deux paquets, le poste des reserves de l'instance C passe de 0,5 a 1,0 et C depasse 4,0 |
| Trois postes finances sans story ecrite (section 10) | Produit | `camille-po` | Story ecrite, ou poste non engage |
| 0,5 non alloue de l'instance B (paquet de sciences ou transfert) | Capacite | moi-meme, en cours de sprint | Reaffectation ecrite au moment ou elle est faite |

## 13. Fusion des quatre branches livrees, casquette dev (2026-09-09)

**Lignee verifiee avant chaque fusion** : `s33-theo` (`d85cc7b0`), `s33-marc` (`4adc751f`),
`s33-lea` (`fbb370a7`) et `s33-sofia` (`556cad9f`) forkent toutes de `1740807e`, le commit qui a
ouvert ce planning. Fusionnees dans cet ordre, sur instruction de `theo-archi` (section 5 de son
avis de faisabilite) : `s33-theo` en premier, ses lignes etant a huit lignes de l'enumeration que
`marc-dev` met a jour dans le meme fichier.

**Le point de couture attendu sur `PaquetCm1HistoireGeographieTest` (theo et marc) ne s'est pas
materialise en conflit Git** : fusion automatique. Verifie apres coup que les deux lots
coexistent intacts et non que l'un a silencieusement absorbe l'autre - les six methodes
`partageDesTachesEpingle` de `theo-archi` (lignes `HG-CM1-01`, `-02`, `-03`, `-04`, `-11`, `-13`)
et les trois tests neufs de `marc-dev` sur `HG-CM1-08`/`HG-CM1-12` portent sur des
lignes disjointes du referentiel : aucune collision reelle, seulement un risque correctement
anticipe. Les quatre autres points de couture nommes ne l'etaient pas non plus : `catalogue.yaml`
n'est touche que par `s33-lea` (une ligne, consigne de `bandit-manchot-exclusion-cp`) ;
`TexteAccentueExposeParLePortEntrantTest` que par `s33-lea` ;
`UneConditionDeLotSeCompteSurLeLotDeMatiereEtNonSurUnFichierTest` que par `s33-marc` ;
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` que par `s33-sofia`. Chacun verifie
plutot que suppose.

**Quatre jobs de la CI mesures sur l'arbre fusionne, nommes un par un** : `prefixes` vert (les
quatre commits non-merge depuis `1740807e` portent tous un prefixe connu), `chemins` vert (aucun
chemin de poste de travail dans les fichiers versionnes), `outils` vert (9 juges purs executes),
`build` vert (`mvn -B clean verify`, reacteur complet a six modules, BUILD SUCCESS, JDK 21.0.10
rapporte par la machine virtuelle des tests).

**Compte de tests : 1443**, soit 1439 + 4, l'attendu exact (`marc-dev` ajoute quatre methodes
`@Test`, les trois autres branches n'en ajoutent aucune). Methode : somme de l'attribut `tests`
sur les 193 fichiers `TEST-*.xml` de `surefire-reports` et `failsafe-reports` de l'arbre fusionne
(105 `domain`, 13 `application`, 388 `adapter-web`, 693 `adapter-content`, 244 `bootstrap`),
aucune classe executee deux fois - croisee independamment avec les totaux "Tests run" que Maven
annonce par module dans sa propre sortie console, qui donnent la meme somme. Les deux methodes
convergent sur le meme chiffre.

**Etat reel des onze stories du sprint apres cette fusion.** Sept payees dans ce lot :
`S33-HG-02`, `S33-HG-08-12` (`marc-dev`), `S33-CAT1`, `S33-EXC-R1`, `S33-EXC-R5` (`lea-dev`),
`S33-EPS-SEC`, `S33-EPS-JEU` (`sofia-sm`). `S33-HG-04` : debloquee par l'avis de `theo-archi`
(faisable en l'etat, sans `ADR`) mais **non engagee** - `marc-dev` l'ecrit dans son commit,
faute de l'avis au moment ou il a ouvert sa branche. `S33-EMCCP07` : **partielle et bloquee** -
un item corrige sur trois, les deux autres refuses par `lea-dev` faute d'une troisieme
proposition constructible sous les conditions actuelles de `nadia-prof` (route en section 12).
`S33-CONS-4` et `S33-SCO-1` restent bloquees exactement comme a l'ouverture de ce planning,
aucune des quatre branches ne les touchant. Le total de sept stories payees ne correspond pas au
compte de neuf lignes de couverture que la composition de la section 2 visait au maximum
(plafond arithmetique) : deux des lignes financees (`HG-CM1-08`, `HG-CM1-12`) sont payees par une
seule story (`S33-HG-08-12`), et les deux stories `S33-EXC-R1`/`S33-EXC-R5` sont des corrections
non bloquantes sur une ligne deja `couvert-valide` (`EMC-CP-03`), pas des lignes neuves. Aucun
mouvement de `squad/programme/MATRICE-couverture.md` n'est fait ici - il appartient a la review.

Rien n'est pousse. Les trois obstacles pedagogiques (`S33-EMCCP07`, `S33-SCO-1`, `S33-CONS-4`)
restent routes tels qu'ecrits en section 12, sans reponse nouvelle a ce jour.

## Decisions prises

Aucune decision prise pendant ce planning : la strategie (`DEC-0085`) est geree en amont par
`camille-po`, et ce planning l'applique sans la rouvrir.

## Prochaines etapes

- `marc-dev` ouvre son worktree sur `S33-HG-02` en premier (aucun prealable), puis attend l'avis de
  `theo-archi` sur `S33-HG-04` avant de l'engager ; `S33-HG-08-12` en troisieme, meme paquet.
- `lea-dev` ouvre son worktree sur `S33-EMCCP07` et `S33-CAT1` (aucun prealable), puis attend l'avis
  de `theo-archi` sur `S33-EXC-R1` avant de l'engager ; `S33-EXC-R5` peut suivre sans attendre,
  aucun prealable technique.
- `sofia-sm`, casquette dev, ouvre son worktree sur `S33-EPS-SEC` puis `S33-EPS-JEU`.
- `sofia-sm`, casquette Scrum Master, route dans le meme geste que ce planning : la decision de
  grain de `S33-CONS-4` et celle du lot de l'effort et du corps a `camille-po` et `nadia-prof`, le
  remede `D7` et les deux avis de faisabilite manquants a `theo-archi`.
- Le daily suivant collecte l'avancement des trois worktrees et l'etat des routages ci-dessus ; il
  n'est pas journalise.
