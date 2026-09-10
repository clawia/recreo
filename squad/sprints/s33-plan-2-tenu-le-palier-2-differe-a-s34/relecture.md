# Relecture pedagogique et QA de contenu - s33

- **Auteur** : `relecteur-pedagogique`. **Date** : 2026-09-10.
- **Perimetre** : les huit documents de sprint non commites de
  `squad/sprints/s33-plan-2-tenu-le-palier-2-differe-a-s34/`, les sept `FICHE-*.md` touchees par
  s33, `squad/decisions/REGISTRE-identifiants.md`, les trois stories neuves de
  `docs/backlog/backlog.md`, et le diff de contenu de s33 (`1740807e..HEAD`).
- **Ce que je verifie** : references qui pointent vers rien, chiffres sans methode ou incoherents,
  le point route par l'instance A sur l'orthographe du festival, les conventions de redaction, la
  clarte pour un developpeur junior. Je ne juge ni le produit, ni l'architecture, ni la pedagogie.

---

## 1. Correction appliquee

**`HG-CM1-12`, item 9 (le festival) - tranche.** Le paquet servi
(`adapters/adapter-content/src/main/resources/contenu/cm1-histoire-geographie.yaml`) ecrivait
*"evenement"* accentue `événement` treize fois (commentaires, champ `competence`, six enonces et
indices) et *"évènement"* (accent grave) une seule fois, dans l'enonce de l'item 9 - exactement le
point que `verdicts-nadia-instance-A.md`, section 11, route au relecteur sans le trancher elle-meme.

Les deux graphies sont orthographiquement admises en francais (l'accent grave est la graphie
rectifiee de 1990, l'accent aigu la graphie traditionnelle), donc ce n'est pas une faute en soi :
c'est un defaut de coherence terminologique - treize occurrences contre une, dans le meme fichier,
pour le meme mot. J'ai corrige l'occurrence isolee pour l'aligner sur la forme dominante du fichier.

- **Fichier** :
  `adapters/adapter-content/src/main/resources/contenu/cm1-histoire-geographie.yaml`, ligne 892
  (`enonce` de l'item du festival, `HG-CM1-12`).
- **Avant** : `"Dans une ville touristique, quel évènement culturel attire souvent des visiteurs en été ?"`
- **Apres** : `"Dans une ville touristique, quel événement culturel attire souvent des visiteurs en été ?"`
- **Verifie que la correction ne casse rien** : `PaquetCm1HistoireGeographieTest` ne fait
  correspondre `FER-1` sur `HG-CM1-12` (*"se cultiver" est servie*) qu'a un `anyMatch` sur trois
  motifs (`"œuvres d'art"`, `"monuments"`, `"évènement culturel"`) ; le premier motif est deja
  satisfait par un autre item (*"des œuvres d'art exposées"*, item du musee, ligne 862), donc le
  test ne dependait pas de la graphie de cette occurrence. Rejoue : `mvn -pl
  adapters/adapter-content -am -Dtest=PaquetCm1HistoireGeographieTest test` -> **28/28, BUILD
  SUCCESS**.
- **Ce que je ne fais pas** : je ne touche pas le motif de test lui-meme (`.contains("évènement
  culturel")`, ligne 693 du test), qui reste vrai mais ne matche plus rien apres ma correction. Ce
  n'est pas une regression - le test passe toujours par un autre motif - mais c'est une chaine de
  test qui a cesse de decrire ce que le contenu sert. Je le signale ci-dessous plutot que de
  corriger un fichier de test, hors de mon ressort.

## 2. Point a trancher - une chaine de test devenue obsolete par ma propre correction

`PaquetCm1HistoireGeographieTest.fer1SurHgCm112LaPratiqueSeCultiverEstDesormaisServie` (ligne 693)
verifie qu'au moins un enonce contient `"évènement culturel"` (accent grave). Ma correction
orthographique fait disparaitre cette sous-chaine du contenu servi ; le test reste vert parce qu'un
autre item (*"œuvres d'art"*) satisfait deja le meme `anyMatch`, mais la troisieme branche du
predicat est maintenant morte code. **Route a `theo-archi`** : mettre a jour la chaine litterale
(`"évènement culturel"` -> `"événement culturel"`) dans le meme geste qu'un prochain diff de ce
fichier, pour que le test continue de decrire le contenu qu'il pretend verifier.

## 3. References verifiees - aucune ne pointe vers rien

Tous les `ADR-XXXX`, `DEC-XXXX` et `PAR-N` cites dans les huit documents de sprint resolvent vers un
fichier ou une definition reels, verifie par lecture directe et non suppose :

- Seize `ADR-XXXX` et huit `DEC-XXXX` distincts cites : chacun a son fichier sous
  `squad/decisions/adr/` ou `squad/decisions/produit/`.
- `PAR-0`, `PAR-2`, `PAR-5`, `PAR-7` a `PAR-14` : chacun defini dans `DEC-0083`, `DEC-0085` ou un
  `ARBITRAGE-PREALABLE-*` reel.
- Tous les identifiants de condition neufs cites comme alloues (`HG-R13` a `HG-R18`, `EXC-R7` a
  `EXC-R9`, `PER-R3`, `REG-R4`, `REG-R5`, `SEC-R4`, `SEC-R5`, `EPS-8`, `JEU-R9` a `JEU-R12`) ont
  chacun exactement une ligne dans `squad/decisions/REGISTRE-identifiants.md`, section 4 - aucun
  manquant, aucun doublon.
- Rejoue par la CI elle-meme : `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`,
  `ToutesLesReferencesDeDecisionResolventTest`, `ConcordanceDesTagsDAgentTest`,
  `AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest` - **16 tests, 0 echec** (`mvn -pl
  bootstrap -am -Dtest=...`).

## 4. Chiffres verifies - tous coherents, un seul mecanisme de divergence deja documente

**Porte technique.** Le compte de **1443 tests** est cite a l'identique par `sofia-sm` (planning,
sections 13-14), `theo-archi` (avis de faisabilite, section 8, sur un etat anterieur a 1439, delta
explique), `theo-archi` (confirmation-archunit, section 1), `yanis-qa` (rapport-qualite, section 2)
et les trois instances de `nadia-prof`. **Rejoue independamment** (`mvn -B clean verify` sur la
racine, reacteur complet) : **1443 tests, 0 echec** - domain 105, application 13, adapter-web 388,
adapter-content 693, bootstrap 244, somme des attributs `tests` des rapports Surefire/Failsafe,
identique poste par poste a ce que chaque document annonce.

**33 regles ArchUnit executees.** Verifie par comptage direct des champs `@ArchTest` : 32 dans
`recreo-bootstrap` (`com.recreo.archi.*`) + 1 dans `recreo-adapter-web`
(`AssertionsWebTest.aucuneAssertionParExpressionReguliere`) = 33, exactement le chiffre de
`confirmation-archunit.md`, section 2.

**Le registre d'identifiants (582 -> 601, 97 prefixes).** Ce n'est pas une incoherence mais une
mesure de concurrence correctement racontee : les trois instances de `nadia-prof` ont ecrit dans le
meme worktree partage, non commite, le meme jour. Chaque instance rapporte le compte qu'elle a
reellement lu au moment ou elle a lu le fichier (A : 582 base + 6 = 588 ; B : 588 base [l'allocation
de A deja visible] + 6 = 594 ; C : 582 base [l'etat committe de `b45e5927`] + 7 = 589, puis
reparation apres avoir trouve 601 sur le disque). **Verifie sur le fichier final** :
`squad/decisions/REGISTRE-identifiants.md` section 4 porte exactement **601 lignes, 601
identifiants distincts, 97 prefixes distincts, zero doublon** - la ligne d'etat courant (section 1,
ligne 16) est juste. Chaque note datee porte sa propre methode et n'est pas retouchee, conformement
a la regle que le fichier s'impose lui-meme (section 1, note liminaire). Aucune correction requise.

**`PAR-14` change de perimetre en cours de sprint, et c'est deja route.** `verdicts-nadia-instance-C.md`,
section 4.4, corrige elle-meme un chiffrage anterieur (le sien, repris par le planning et par
`DEC-0085`) : *"aucune autre reprise n'atteint la moitie de son paquet"* etait faux des l'ouverture
du sprint (`S33-EPS-SEC` touche 6 items sur 11, soit plus de la moitie). Nadia le nomme, le date, et
le route explicitement a `camille-po` et `sofia-sm` (section 13, table de routage) sans corriger
retroactivement les documents historiques (planning, `DEC-0085`) qui restent des mesures datees.
**Rien a trancher ici** : c'est deja fait dans les regles de gouvernance du depot.

**Le chiffre de `EMC-CP-07` (0,5 -> perimetre revu).** Meme mecanisme : `nadia-prof` corrige
elle-meme, avec sa date, un chiffrage qu'elle avait publie le 2026-09-09 (*"aucun item neuf"*), et
route le prix corrige a `camille-po`/`sofia-sm`. Deja gouverne, rien a trancher.

## 5. Conventions de redaction - conformes

Verifie par recherche systematique sur les huit documents de sprint, les sept `FICHE-*.md`
modifiees, `REGISTRE-identifiants.md` et le diff de `docs/backlog/backlog.md` :

- **Aucun tiret cadratin, aucun guillemet typographique** (recherche des caracteres Unicode
  U+2013, U+2014, U+2018-U+201D) : zero occurrence.
- **Aucun emoji ni symbole decoratif** : zero occurrence.
- **Aucun chemin de poste de travail ni nom de compte**, y compris dans les fichiers non commites :
  `git ls-files` (suivis) + `git ls-files --others --exclude-standard` (non suivis) passes dans
  `verifier-chemins.sh` -> *"Aucun chemin de poste de travail ni nom de compte dans les fichiers
  verifies."*
- Les titres qui enjambent deux lignes en `##`/`###` dans `docs/backlog/backlog.md` (ex. `S34-ANG-CORR`,
  `S34-TRANSVERSE-1`, `S34-EPS-CORPS-EFFORT`) suivent la convention deja etablie dans ce fichier
  depuis s13 (titres longs replies sur une seconde ligne de balisage) : ce n'est pas un defaut, verifie
  contre des occurrences anterieures non liees a s33 (lignes 3847, 9422, 9944...).

## 6. Clarte pour un developpeur junior

Les huit documents de sprint sont longs mais chacun s'ouvre sur une table ou un resume qui porte le
verdict avant le detail (planning section 2, chaque verdict section 1, confirmation-archunit
section 0, rapport-qualite section 1). Aucun ne suppose un contexte non ecrit : chaque reference a
un identifiant de condition, un `ADR`/`DEC` ou une story renvoie a un fichier qui la definit. Le
seul point qui demande d'avoir suivi le sprint en direct pour se reconstituer sans effort est le
registre d'identifiants (section 4 ci-dessus) - mais chaque instance en explique la cause au moment
ou elle l'ecrit, donc l'information est presente, seulement distribuee sur trois documents plutot
que sur un seul.

## 7. Ce que je ne tranche pas

| Point | Categorie | Route a |
|---|---|---|
| Chaine de test `"évènement culturel"` devenue obsolete par ma correction orthographique (section 2) | Technique | `theo-archi` |
| Le grain de `bandit-manchot-histoire-geographie-cm1` (`S33-CONS-4`) | Produit / pedagogie | deja route par `camille-po` et `nadia-prof` a `nadia-prof` (position en attente de sa confirmation) - je ne rouvre rien |
| L'instruction 1 de `DEC-0061` sur `SCO-1` | Pedagogie | deja route a `nadia-prof` elle-meme, pour s34 |
| Le remede technique `SCO-1` a `SCO-7`, et les deux tests de `PaquetCpEmcRegleSouhaitLoiTest` (`FER-1`/`BIN-1` mal calibres sur `EMC-CP-07`) | Technique | deja route a `theo-archi` par `nadia-prof`, instance B |

## 8. Ce que ce document ne fait pas

Il ne touche pas `squad/programme/MATRICE-couverture.md` (review de `sofia-sm`, pas encore tenue).
Il ne modifie aucune ligne de code de production ni de test. Il ne prononce aucun Done technique ni
pedagogique. Rien n'est commite, rien n'est pousse.
