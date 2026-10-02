# Rapport qualite de cloture - s46

**Auteur** : `yanis-qa`. **Date** : 2026-10-02. **Arbre mesure** : `s46-yanis-qualite` a
`346274c8` (hash complet `346274c833b0871482a1a59bf4d424fb1ed3b87a`, strictement egal a
`round-two` au moment de la mesure), worktree dedie
(`git worktree add ../recreo-s46-yanis-qualite -b s46-yanis-qualite round-two`), lignee verifiee
(`bash outils/verifier-lignee.sh round-two`, lance depuis le worktree lui-meme -> *"le worktree sur
la branche 's46-yanis-qualite' descend de la lignee de round-two (ancetre commun trouve dans son
historique)"*).

Contenu du sprint : cinq lots de contenu statique, dispositif a deux instances paralleles de
`nadia-prof` (`DEC-0102`) - instance A : `S46-MAT-SOUSTRACTION` (`MAT-CE1-05`, `[MARC]`, merge
`422a391d`), `S46-EMC-EMOTION-CM1` (`EMC-CM1-01`, `[LEA]`, merge `8ab9ebf1`),
`S46-EMC-INSTITUTIONS-CM1` (`EMC-CM1-09`, `[SOF]`, merge `93643b61`) ; instance B :
`S46-SCI-MOUVEMENT-CM1` (`SCI-CM1-03`, `[LEA]`, merge `8ab9ebf1`), `S46-SCI-PHENOMENES-CM1`
(`SCI-CM1-16`, `[SOF]`, merge `93643b61`). Base de sprint : `8b0fe8b4` (dernier commit sur
`round-two` avant s46, fusion de la cloture de s45).

**Un ecart reel de methode de mesure, trouve et corrige avant la cloture.** Le dernier commit du
sprint, `346274c8` (`[SOF]`), corrige un defaut concret de la famille que `CLAUDE.md` nomme : le
lot de `marc-dev` (`MAT-CE1-05`) n'avait ete mesure que sur `adapter-content` (`-pl
adapter-content -am`), jamais sur le reacteur complet. `TexteAccentueExposeParLePortEntrantTest`,
dans `bootstrap`, n'a rougi qu'a la fusion des cinq lots - trois chaines neuves (identifiant,
titre, competence de `bandit-manchot-soustractions-posees-ce1`) manquaient a l'inventaire. Corrige
par `sofia-sm` avant que je mesure, et verifie ci-dessous par ma propre mutation (section 2) : ce
n'est pas une affirmation reprise de confiance.

`bash outils/mvn-jdk21.sh -B clean verify`, lance une seule fois sur ce worktree, a rendu
`BUILD SUCCESS` du premier coup, en 8 min 23 s. JDK constate par `outils/verifier-jdk.sh` (sortie
standard, jamais un chemin de poste) :

```
java du PATH : 21.0.12.1 | JDK de compilation : 21.0.10 | JAVA_HOME : renseignee | toolchains lu par ce script : oui | toolchains lu par Maven : oui (le meme que celui lu par ce script)
```

---

## 1. Compte de tests autoritaire

**1898 tests : 1892 unitaires + 6 tests d'integration**, sur `s46-yanis-qualite` a `346274c8`.
Methode (amendement s11) : somme de l'attribut `tests="..."` des 252 rapports XML de
surefire/failsafe apres ce `clean verify` - jamais les lignes `Tests run:` des fichiers `.txt`.
Cette somme (1898) est identique a celle des six blocs `[INFO] Results:` du meme lancement : les
deux methodes convergent sur cet arbre, aucune classe de ce depot ne melangeant ici `@ArchTest` et
`@Test` d'une facon qui ferait diverger les deux comptes.

**Resultats par module** :

| Module | Tests run |
|---|---|
| recreo-domain | 120 |
| recreo-application | 17 |
| recreo-adapter-web | 400 |
| recreo-adapter-content | 1067 |
| recreo-bootstrap (surefire, unitaires) | 288 |
| recreo-bootstrap (failsafe, integration) | 6 |
| **Total unitaires** | **1892** |
| **Total integration** | **6** |

`domain`, `application`, `adapter-web` et `bootstrap` (unitaires et integration) sont **inchanges**
depuis la cloture de s45 (120, 17, 400, 288, 6) - coherent avec `git diff --stat 8b0fe8b4..346274c8
-- '*.java'`, qui ne touche que huit fichiers de test, zero fichier de `src/main/java` sur les
cinq modules. Seul `adapter-content` bouge : **1019 -> 1067, soit +48**, exactement la somme des
`@Test` des cinq fichiers neufs, comptee directement dans chacun (`grep -c "@Test"`) :
`PaquetCe1SoustractionsPoseesTest` (13), `PaquetCm1EmcEmotionRessentirAgirTest` (7),
`PaquetCm1EmcInstitutionsTest` (7), `PaquetCm1SciencesMouvementTest` (10),
`PaquetCm1SciencesPhenomenesTest` (11) = 48. Les trois fichiers transverses modifies
(`ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest`,
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`,
`TexteAccentueExposeParLePortEntrantTest`) n'ajoutent aucun `@Test` : leurs diffs n'ajoutent que
des entrees de donnees (une entree d'enum, des entrees de `Map`, des chaines de l'inventaire),
consommees par des methodes de test deja existantes.

**Total du sprint** : 1019 (s45) + 48 (cinq paquets) = **1067**, et le total depot (1850 -> 1898)
rend bien **+48** sur `adapter-content` seul, sans mouvement dans aucun autre module.

**Couverture par module** (fichiers `*Test.java`/`*IT.java` suivis par Git) :

| Module | Fichiers de test (s45 -> s46) |
|---|---|
| domain | 11 -> 11 |
| application | 2 -> 2 |
| adapter-web | 49 -> 49 |
| adapter-content | 135 -> 140 |
| bootstrap | 51 -> 51 |

`adapter-content` passe de 135 a 140, soit exactement **+5 fichiers**, un par paquet neuf. Aucun
paquet neuf n'est reste sans test a son premier commit.

**ArchUnit** : **38** `static final ArchRule` et **35** `@ArchTest`, comptes par occurrences
litterales sur les fichiers de test suivis par Git des cinq modules (`target/` exclu par
construction, Git ne le suit pas). **Identique au chiffre verifie a la cloture de s45** : aucune
regle ArchUnit neuve n'est due a s46, confirme par le planning (section 7, "pieges communs") et
par `git diff 8b0fe8b4..346274c8` sur les fichiers de test, qui ne touche aucune ligne contenant
`static final ArchRule`. Aucune classe ArchUnit en echec dans le lancement complet.

**Catalogue** : **115 entrees** (`- id:`), contre 110 a la cloture de s45 - exactement **+5**.
Aucun identifiant duplique, aucun paquet duplique (`grep | sort | uniq -d` rend vide sur les deux
cles). Les cinq entrees du sprint sont toutes presentes et distinctes : `paquet:
ce1-soustractions-posees`, `cm1-emc-institutions`, `cm1-sciences-phenomenes`,
`cm1-emc-emotion-ressentir-agir`, `cm1-sciences-mouvement`.

## 2. Preuve de morsure - deux garde-fous mutes sur le materiau de production reel

Amendement s06/s10 : un garde-fou cite comme fermant une condition n'est declare complet qu'une
fois mute reellement, jamais sur la seule foi d'un vert. Deux morsures, sur deux lots distincts du
sprint, mutation puis restauration verifiees par `git status --short` vide.

**Morsure 1 - `SOP-CE1-5`, `PaquetCe1SoustractionsPoseesTest` (`MAT-CE1-05`).** Mutation du
materiau de production reel (`ce1-soustractions-posees.yaml`, pas une fixture de test) : sur
l'item `"52 - 27 = ?"`, le distracteur marque `non-reportee` passe de `"35"` (valeur correcte) a
`"36"`, sans toucher le marqueur. `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am
test -Dtest=PaquetCe1SoustractionsPoseesTest`. **Rouge attendu, rouge obtenu** :
`chaqueDistracteurEstUneErreurNommeeDeclareeEtCalculeeDepuisLesOperandes` echoue - *"SOP-CE1-5 :
'52 - 27 = ?', distracteur 0 ('non-reportee') expected: 35 but was: 36"*. Mutation annulee, meme
classe relancee : **13 verts sur 13**, `git status --short` vide. Le garde-fou lit bien la valeur
declaree et la compare au calcul, il ne la recopie pas.

**Morsure 2 - l'inventaire du port entrant, `TexteAccentueExposeParLePortEntrantTest`
(`EMC-CM1-01`).** Mutation du materiau de production reel (`cm1-emc-emotion-ressentir-agir.yaml`,
champ `competence`) : l'accentuation est retiree (`"l'émotion ... donnée"` ->
`"l'emotion ... donnee"`), sans toucher l'inventaire. `bash outils/mvn-jdk21.sh -B -pl bootstrap
-am test -Dtest=TexteAccentueExposeParLePortEntrantTest#touteChaineExposeeParLeCatalogueReelEstInventoriee`.
**Rouge attendu, rouge obtenu** : la chaine desaccentuee n'est pas trouvee dans l'inventaire
(assertion `to contain... but could not find`). Mutation annulee, toute la classe relancee :
**5 verts sur 5**, `git status --short` vide. C'est la meme famille de defaut que celle corrigee
par `346274c8` avant ma mesure (section d'en-tete) : je verifie ici que le garde-fou corrige mord
reellement sur le contenu neuf du sprint, pas seulement qu'il est vert.

## 3. Garde-fous dont le compte doit bouger (amendement s17)

Trois garde-fous nommes par la consigne, verifies un par un plutot que supposes avoir vu le
materiau neuf :

- **`TexteAccentueExposeParLePortEntrantTest`** : inventaire etendu de quinze chaines neuves sur ce
  sprint (cinq identifiants, cinq titres, cinq consignes, plus les cinq libelles de competence -
  `git diff 8b0fe8b4..346274c8` sur ce fichier, 61 lignes ajoutees, chacune tracee par un
  commentaire `S46-...`). **Le compte a bouge**, et la section 2 ci-dessus prouve qu'il voit
  reellement le materiau neuf (pas seulement qu'il le liste).
- **Garde-fou de rang (`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`)**. Deux effets
  distincts selon le domaine du paquet. Sur `ce1-soustractions-posees` (domaine numerique), le
  paquet entre dans la population reelle du garde-fou generique (pas d'exemption) : son compte de
  paquets numeriques evalues a donc bouge de un, et `ADR-0013` obligation 3 ainsi que la regle de
  rang **etendue au paquet entier** (items sans emprunt compris) restent en plus verifiees
  nommement par `PaquetCe1SoustractionsPoseesTest` (section 1 ci-dessus, le garde-fou generique
  n'en couvrant que la moitie, par construction assumee). Sur les quatre paquets textuels restants,
  **le compte a bouge** d'un autre cote : `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` passe de
  85 a 89 entrees (`git diff` : 37 insertions, 1 suppression, quatre `Map.entry` neufs, chacun avec
  son motif chiffre et sa date de mesure, 2026-10-01) - **ce n'est pas une liste de noms
  d'incidents** (amendement s11) mais une categorie (aucune proposition numerique), et chaque
  entree neuve porte son propre decompte ("12 items sur 12...", "aucune des 36 propositions ne
  commence par un chiffre").
- **Garde-fou de declaration de ligne (`ChaquePaquetServiDeclareLesLignesDeReferentielQuIlSertTest`,
  `ADR-0081`/`HIS-4`)** : sa population est derivee par balayage de repertoire
  (`paquetsServis`), pas d'une liste ecrite ici. Les cinq paquets neufs y entrent automatiquement
  et chacun declare exactement une ligne (`MAT-CE1-05`, `EMC-CM1-01`, `EMC-CM1-09`, `SCI-CM1-03`,
  `SCI-CM1-16`), verifie par lecture directe des cinq fichiers YAML
  (`lignesDeReferentiel: ["<code>"]`). **Le compte a bouge par construction** et je l'ai verifie
  plutot que suppose : zero code en dette (`DETTE_DE_DECLARATION` reste vide, `PLAFOND_DE_DETTE`
  reste 0), et le lancement complet (section d'en-tete) le confirme vert sur les neuf morsures de
  ce fichier.

Ce que ce point n'a pas trouve cette fois : aucun des trois garde-fous n'est reste muet sur le
materiau neuf - a la difference de `346274c8`, qui corrige un garde-fou **different**
(`TexteAccentueExposeParLePortEntrantTest`) reste muet jusqu'a la fusion complete faute d'une
mesure sur le reacteur entier, pas faute d'une regle absente.

## 4. Tracabilite - criteres d'acceptation encodes, lot par lot

Chaque story de `docs/backlog/backlog.md`, section "Sprint 46", est verifiee critere par critere
contre le fichier de test du paquet.

- **`S46-MAT-SOUSTRACTION`** (`PaquetCe1SoustractionsPoseesTest`, 13 methodes) : `SOP-CE1-1` a
  `SOP-CE1-6` toutes citees en nom de methode ou en commentaire d'assertion - y compris la regle de
  rang **etendue au paquet entier**
  (`laStrategieToujoursLaPlusPetitePropositionNeGagnePasSurPlusDeLaMoitieDuPaquetEntier`) et la
  regle d'admission de la quatrieme forme de distracteur
  (`chaqueFormeReporteeDeuxFoisTientLaRegleDAdmission`), toutes deux issues de l'amendement du
  2026-10-01. La ligne de referentiel exclusive est verifiee directement sur le YAML
  (`cePaquetDeclareMatCe105EtUniquementElle`), et le jeu fautif commite est refuse sur son marqueur
  errone (`leJeuFautifCommiteEstRefuseSurSonMarqueurErrone`, `ADR-0013` obligation 3). Seul ce qui
  reste du ressort de `nadia-prof` (la mention de couverture partielle a ecrire dans la matrice, le
  jugement global des six conditions) n'est pas automatise, et c'est explicitement ce que la story
  demande a elle seule.
- **`S46-EMC-EMOTION-CM1`** (`PaquetCm1EmcEmotionRessentirAgirTest`, 7 methodes) : `FER-2`, `FER-3`
  et la partie automatisable d'`EMC-1`/`EMC-8` (forme a la deuxieme personne) sont couvertes.
  `EMC-2`, `EMC-5` (lecture semantique d'une situation) et `CYC-1` (comparaison avec
  `bandit-manchot-emotions-autrui-cp`) sont explicitement laisses a `nadia-prof` par le
  commentaire de tete du fichier, conformement a l'avis de faisabilite cite par la story.
- **`S46-EMC-INSTITUTIONS-CM1`** (`PaquetCm1EmcInstitutionsTest`, 7 methodes) : `FER-1` (les trois
  institutions chacune en bonne reponse), `FER-2`, `FER-3` couvertes. `EMC-7` est verifie **par
  construction** (aucun chiffre nulle part dans le paquet, donc aucun numero d'urgence), plutot
  que suppose - coherent avec le commentaire de tete qui le dit explicitement.  `EMC-5`, `EMC-6`
  (lecture semantique) restent a `nadia-prof`.
- **`S46-SCI-MOUVEMENT-CM1`** (`PaquetCm1SciencesMouvementTest`, 10 methodes) : `FER-1` (marqueurs
  `# element:` par item, les trois elements de la parenthese conjonctive tous servis), `FER-2`,
  `FER-3`, `SCI-1`, `SCI-3`, `SCI-4`, `HIS-3` (et absence du marqueur `HIS-1`) toutes couvertes. Le
  refus d'avance (aucun calcul de vitesse/duree/longueur) est verifie sous une forme **plus forte**
  que la seule absence d'unite - aucun chiffre du tout, nulle part -, ce qui ferme d'un seul geste
  le piege de fabrication nomme par `theo-archi` (une proposition lue comme une duree par
  `ADR-0102`).
- **`S46-SCI-PHENOMENES-CM1`** (`PaquetCm1SciencesPhenomenesTest`, 11 methodes) : `FER-1`
  (marqueurs `# element:`), `FER-2`, `FER-3`, `BIA-2` (marqueur `# lieu:` par item, au moins un
  phenomene situe en France ou en Europe parmi les items situes), `SCI-1` a `SCI-4`, `HIS-3`
  toutes couvertes.

**Observation de tracabilite, sans consequence de couverture.** Trois des cinq fichiers portent
une methode nommee `chaqueItemDeclareUniquement<Ligne>` dont le corps ne verifie qu'un mot-cle
dans `competence()` (`"émotion"`, `"institutions"`), pas l'exclusivite de la ligne de referentiel
que leur nom annonce. Verifie que ce n'est pas un trou : l'exclusivite reelle (une ligne et une
seule, correctement allouee) est tenue transversalement par
`ChaquePaquetServiDeclareLesLignesDeReferentielQuIlSertTest` (`ADR-0081`), qui derive sa
population par balayage de repertoire et couvre donc les cinq paquets neufs sans action
supplementaire (section 3). Les deux fichiers de sciences le disent explicitement en commentaire
(*"deja tenu par ADR-0081, non repete ici"*) ; les deux fichiers d'EMC ne le disent pas, seule
incoherence de redaction trouvee entre les cinq fichiers. Aucune consequence sur ce que la CI
garde, puisque le garde-fou transverse est reellement sollicite et vert ; a corriger en
redaction, pas en couverture.

## 5. Resolution des fusions successives sur les fichiers transverses

Trois fusions sur cinq ont touche des fichiers transverses partages par plusieurs lots ;
`8ab9ebf1` (derniere fusion) porte un conflit textuel reel, trace en toutes lettres dans le message
de merge, sur trois fichiers : `catalogue.yaml`,
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.java` et
`TexteAccentueExposeParLePortEntrantTest.java`. Verifie sur l'arbre final : **aucune marque de
conflit residuelle** (`<<<<<<<`/`=======`/`>>>>>>>`, recherche sur l'arbre entier suivi par Git,
vide). `catalogue.yaml` porte ses 115 entrees sans doublon (section 1). Le fichier de rang porte
ses quatre entrees neuves en pure insertion (section 3). L'inventaire de texte accentue est corrige
une seconde fois par `346274c8`, apres la fusion, pour les trois chaines de `MAT-CE1-05` que la
premiere mesure partielle du developpeur n'avait pas vues (section d'en-tete) - ce correctif est
lui-meme verifie, pas seulement lu, par la morsure de la section 2.

## 6. Matrice de couverture - etat constate, non modifie

Les cinq lignes du perimetre de `DEC-0102` restent `non couvert`, comme attendu avant le Done
pedagogique des deux instances de `nadia-prof` :

| Ligne | Fichier, position | Etat constate |
|---|---|---|
| `MAT-CE1-05` | `squad/programme/MATRICE-couverture.md:728` | `non couvert` |
| `EMC-CM1-01` | `squad/programme/MATRICE-couverture.md:2076` | `non couvert` |
| `EMC-CM1-09` | `squad/programme/MATRICE-couverture.md:2084` | `non couvert` |
| `SCI-CM1-03` | `squad/programme/MATRICE-couverture.md:2169` | `non couvert` |
| `SCI-CM1-16` | `squad/programme/MATRICE-couverture.md:2182` | `non couvert` |

Aucune des cinq lignes n'a ete modifiee par ce rapport. Aucun fichier de conformite de ce sprint
ne porte encore de verdict `VALIDE` : le Done pedagogique n'est pas encore prononce.

## 7. Tests instables

Aucun test instable observe. Le lancement complet cite dans ce rapport n'a ete execute qu'une
fois ; un test lance une seule fois n'est pas qualifie de stable pour autant, et ce rapport ne
l'affirme pas.

## 8. Ce que ce rapport ne fait pas

Il ne prononce aucun Done pedagogique - les deux instances de `nadia-prof`, seules, le font, sur
les cinq lots. Il ne juge le niveau scolaire d'aucun des cinq lots. Il n'etablit aucun total sur
l'arbre fusionne `round-two` au-dela de ce qui est deja fusionne dans `346274c8`. Il ne modifie
aucun contenu ni aucun code de production : les deux mutations de la section 2 ont ete ecrites,
mesurees puis integralement annulees avant la cloture (`git status --short` vide apres chacune).

---

**Conclusion** : `mvn clean verify` (via `outils/mvn-jdk21.sh`) est vert sur l'arbre fusionne des
cinq lots, `BUILD SUCCESS`, 0 echec, 0 erreur, 8 min 23 s. Le compte de tests (1898, +48 depuis
s45) est explique integralement par les cinq paquets neufs, sans reste. Deux garde-fous
recemment ecrits ou corriges ont ete reellement mutes sur le materiau de production et ont rougi
puis reverdi comme attendu, l'un d'eux etant precisement celui qu'un defaut de methode de mesure
(`-am` partiel) avait laisse muet jusqu'a la fusion complete - corrige par `346274c8`, verifie ici
independamment. Les trois garde-fous dont le compte devait bouger ont tous bouge, et aucun n'est
reste muet sur le materiau neuf. Chaque critere d'acceptation des cinq stories est trace dans un
nom de methode ou un commentaire d'assertion, a l'exception explicite de ce qui revient a l'audit
semantique de `nadia-prof`. **La porte technique de s46 est verte.**
