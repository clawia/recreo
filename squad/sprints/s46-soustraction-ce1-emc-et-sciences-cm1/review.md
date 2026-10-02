# Review - s46 - soustraction posee CE1, emotion et institutions d'EMC CM1 (instance A) et
mouvement/phenomenes de sciences CM1 (instance B), dispositif a deux instances paralleles de
`nadia-prof`

- **Horodatage de debut** (`planning.md`) : 2026-10-01 16:30 (UTC+02:00).
- **Horodatage de fin** : 2026-10-02 10:31:42 (UTC+02:00, horodatage du commit de fusion final
  `8f446902`, cloture de la matrice par `camille-po`).
- **Duree effective** : 18 h 01 min 42 s.
- **Casquette** : `sofia-sm`, Scrum Master.
- **Worktree** : `../recreo-s46-sofia-cloture`, branche `s46-sofia-cloture`, fondee sur
  `round-two` a `8f446902` (dernier commit sur `round-two` au moment de l'ouverture de cette
  ceremonie). Lignee verifiee avant toute ecriture : `bash outils/verifier-lignee.sh round-two`
  -> *"le worktree sur la branche 's46-sofia-cloture' descend de la lignee de round-two (ancetre
  commun trouve dans son historique)"*.
- **Arbre revu** : `round-two` a `8f446902`.

---

## 1. L'increment livre, en une phrase

Cinq lignes du programme passent de `non couvert` a `couvert-valide` (`MAT-CE1-05` soustraction
posee CE1, `EMC-CM1-01` identifier une emotion et distinguer ressentir/agir CM1, `EMC-CM1-09` role
de quelques institutions proches CM1, `SCI-CM1-03` trajectoire et vitesse CM1, `SCI-CM1-16`
phenomenes geologiques et meteorologiques CM1), apres trois refus pedagogiques reels sur trois
lots distincts et leurs corrections en cours de sprint ; la couverture du programme passe de
108/156 (69,2 %) a **113/156 (72,4 %)**.

## 2. Le Done technique - quatre jobs, relances individuellement par moi, sur l'arbre final

Conformement a `squad/README.md` section 9 et a l'amendement du 2026-09-08 a `CLAUDE.md`
("un agent qui annonce la porte technique nomme les jobs qu'il a lances, et ne deduit pas les
autres"), les quatre jobs de `.github/workflows/ci.yml` ont ete relances par moi, individuellement,
sur `round-two` a `8f446902`, dans ce worktree :

| Job | Commande exacte | Resultat |
|---|---|---|
| `chemins` | `bash .github/scripts/verifier-chemins-test.sh` puis `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' \| bash .github/scripts/verifier-chemins.sh` | **vert** : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."* |
| `prefixes` | `bash .github/scripts/verifier-prefixes-test.sh`, `bash .github/scripts/commit-atteignable-test.sh`, puis `git log --no-merges --format=%s 8b0fe8b4..8f446902 \| bash .github/scripts/verifier-prefixes.sh` (perimetre du sprint, de la base de planning a la cloture) | **vert** : *"Tous les commits portent un prefixe d'agent connu."* |
| `outils` | balayage de `outils/*-test.sh`, `squad/outils/*/*-test.sh` et `outils/*-test.py` | **vert**, **9 juge(s) pur(s) execute(s)**, 0 echec |
| `build` | `bash outils/mvn-jdk21.sh -B clean verify` | **BUILD SUCCESS**, 6 modules sur 6, JDK constate **21.0.10** (`java du PATH : 21.0.12.1`, `JDK de compilation : 21.0.10`, toolchains lus par ce script et par Maven, identiques) |

**Compte de tests, verifie par moi de facon independante** : somme des attributs `tests="..."`
des rapports XML Surefire/Failsafe presents sur l'arbre apres mon propre `clean verify` -
**1898 tests, 0 echec, 0 erreur, 0 ignore**, identique au chiffre de `yanis-qa` (rapport-qualite,
section 1) et de `theo-archi` (confirmation-archunit, section 3). Un chiffre annonce par deux
agents se revérifie, il ne se relaie pas (`CLAUDE.md`, lecon s01) : je l'ai remesure sur l'arbre
final plutot que recopie.

**ArchUnit** : 36 `static final ArchRule`, 35 `@ArchTest` (`contreexemple` exclu) - identique au
chiffre de `theo-archi`, inchange depuis s45, coherent avec le fait qu'aucune regle neuve n'est
due sur ces cinq lots de contenu statique.

**Porte technique verte dans son ensemble.**

## 3. La confirmation ArchUnit de `theo-archi`

`confirmation-archunit.md` (worktree dedie `s46-theo-archunit`, perimetre mesure `round-two` a
`346274c8`, bases de comparaison `8b0fe8b4` et `836f3a63`) rend cinq verdicts : le compte de
regles n'a pas regresse (36/35, `contreexemple` exclu, sortie par fichier identique a `8b0fe8b4`) ;
aucune regle n'etait due (zero fichier de `domain`/`application`, zero `pom.xml`, zero mecanique ni
matiere neuve, zero import `springframework` ajoute) ; `mvn -B clean verify` vert sur sa propre
mesure (1898 tests, 209 dans `com.recreo.archi`) ; la Clean Architecture tient sur les cinq lignes ;
les trois garde-fous censes couvrir le materiau neuf (le catalogue lui-meme, l'inventaire du port
entrant, le garde-fou de rang, la declaration de sous-partie de mathematiques CE1) ont tous vu le
materiau neuf, population par population, **et l'inventaire du port entrant a ete rouge sur le
materiau reel a `8ab9ebf1`** avant d'etre complete par `346274c8` (section 7 ci-dessous). `theo-archi`
releve lui-meme ce constat de processus sans l'arbitrer : *"c'est a `sofia-sm` de le porter en
retro si elle le juge utile"* - traite dans `retro.md`, section 1.

## 4. Le rapport qualite de `yanis-qa`

`rapport-qualite.md` (worktree dedie `s46-yanis-qualite`, arbre mesure `346274c8`, lignee
verifiee) : Done technique confirme, **1898 tests (1892 unitaires + 6 integration)**, methode
autoritaire des blocs `Results:`/attributs `tests=`. Delta de tests explique sans reste : seul
`adapter-content` bouge (1019 -> 1067, +48, exactement la somme des `@Test` des cinq paquets
neufs, comptee fichier par fichier). Deux preuves de morsure reelles, mutation du materiau de
production servi (pas une fixture) : `SOP-CE1-5` sur `ce1-soustractions-posees.yaml` (rouge
attendu, rouge obtenu, puis restauration verte 13/13) ; l'inventaire du port entrant sur
`cm1-emc-emotion-ressentir-agir.yaml` (desaccentuation, rouge attendu, rouge obtenu, puis
restauration verte 5/5). Trois garde-fous dont le compte devait bouger (amendement s17) ont tous
bouge, verifie un par un, aucun reste muet sur le materiau neuf. Chaque critere d'acceptation des
cinq stories est trace methode de test par methode de test, a l'exception explicite de ce qui
revient a l'audit semantique de `nadia-prof`.

**Un ecart reel de methode de mesure, trouve et corrige avant la cloture, nomme par `yanis-qa`
lui-meme** : le lot de `marc-dev` (`MAT-CE1-05`) n'avait ete mesure que sur `adapter-content`
(`-pl adapter-content -am`), jamais sur le reacteur complet. `TexteAccentueExposeParLePortEntrantTest`,
dans `bootstrap`, n'a rougi qu'a la fusion des cinq lots - trois chaines neuves (identifiant, titre,
competence de `bandit-manchot-soustractions-posees-ce1`) manquaient a l'inventaire. Corrige par moi
(commit `346274c8`, 2026-10-02 00:33:41) avant que `yanis-qa` ne mesure, et verifie independamment
par sa propre mutation (section 2 de son rapport) et par `theo-archi` (section 5 de sa
confirmation, qui mesure le rouge reel a `8ab9ebf1` avant le correctif). Traite comme obstacle en
section 7 ci-dessous et comme lecon en `retro.md`, section 1.

## 5. Le `relecteur-pedagogique`

`relecture.md` (worktree dedie `s46-relecture`, arbre `round-two` a `cd8af48f`) : deux corrections
appliquees. Un nom de methode de test incoherent avec la grammaire francaise
(`PaquetCe1SoustractionsPoseesTest`, `aucuneLettreNiVirguleEtTouteLePropositionEstNumerique` ->
`aucuneLettreNiVirguleEtToutePropositionEstNumerique`, aucune reference externe, 13 tests
revalides) ; et l'incoherence de redaction signalee par `yanis-qa` entre les deux fichiers de
sciences (qui notent que l'exclusivite de la ligne de referentiel est *"deja tenu par ADR-0081,
non repete ici"*) et les deux fichiers d'EMC (qui ne le disaient pas) - complete a l'identique sur
les deux fichiers d'EMC. Verifie apres correction : 27 tests sur les trois fichiers touches, 0
echec (mesure partielle sur `adapter-content`, perimetre nomme). Tout le reste confirme, aucun
point signale non tranche.

## 6. Le Done pedagogique - deux instances paralleles, rapporte par cette review, pas prononce par
elle

Dispositif a deux instances de `nadia-prof` (`DEC-0102`, section 5 ; `PAR-2` verifie en planning,
section 3, reverifie ici sans objection : trois matieres distinctes, aucune partagee).

**Instance A** - `S46-MAT-SOUSTRACTION`, `S46-EMC-EMOTION-CM1`, `S46-EMC-INSTITUTIONS-CM1`. Budget
individuel 5,0, cout retenu 4,0 (1,0 + 1,5 + 1,5), marge 1,0.

| Ligne | Chronologie | Verdict final | Reserves | Ferme |
|---|---|---|---|---|
| Soustraction posee (`MAT-CE1-05`) | **REFUSE** 1er passage (`124071d8`, 08:37:13 - `SOP-CE1-4` non tenue sur deux items sans emprunt) -> criteres corriges par `camille-po` (`8a70a4b3`, 09:01:58) -> paquet corrige par `marc-dev` (`79e04615`, 09:03:02) -> **VALIDE SOUS RESERVE** au 2e passage (`94bdb5de`, 09:43:33) | **VALIDE SOUS RESERVE** | `SOP-CE1-R1` (arbitrage demande a `nadia-prof` elle-meme, non bloquante - section 7) | `couvert-valide`, mention de couverture : "le resultat atteste le calcul ; la technique posee elle-meme - disposition en colonnes, emprunts - n'est ni montree ni verifiee par le produit" |
| Identifier une emotion, ressentir et agir (`EMC-CM1-01`) | **VALIDE SOUS RESERVE** des le 1er passage (`0a865397`, 08:42:17), aucun refus | **VALIDE SOUS RESERVE** | `EMC-CM1-R5` (trois items ne tiennent `EMC-2` que parce que l'emotion la plus evidente n'est pas proposee), `EMC-CM1-R6` (deux items "fait" de la tache B se reconnaissent a leur ton) | `couvert-valide`, mention exigee d'avance sur toute ligne d'EMC CM1 : "connaissances civiques et reconnaissance de situations seules ; aucun comportement, aucun engagement et aucune participation a une decision collective ne sont servis par ce produit" |
| Role de quelques institutions proches (`EMC-CM1-09`) | **REFUSE** 1er passage (`62b3ed74`, 08:45:13 - item 4 faux, vitesse votee au lieu d'etre fixee par arrete du maire ; `HIS-3` non tenue) -> corrige par moi, casquette dev (`6f27d1e9`, 09:06:45) -> **REFUSE** au 2e passage (`7a1d1ae5`, 09:43:34 - items 10 et 11 reecrits enseignent un pouvoir exclusif sans source) -> retablis a leur version du 1er passage par moi (`f2793332`, 09:53:40) -> **VALIDE SOUS RESERVE** au 3e passage (`d516dfea`, 10:08:43) | **VALIDE SOUS RESERVE** | `EMC-CM1-R7` (items 10 et 11, redites du CP - non bloquante) | `couvert-valide`, meme mention exigee d'avance que `EMC-CM1-01` |

**Instance B** - `S46-SCI-MOUVEMENT-CM1`, `S46-SCI-PHENOMENES-CM1`. Budget individuel 5,0, cout
retenu 3,0 (1,5 + 1,5), marge 2,0.

| Ligne | Chronologie | Verdict final | Reserves | Ferme |
|---|---|---|---|---|
| Trajectoire et vitesse (`SCI-CM1-03`) | **VALIDE SOUS RESERVE** des le 1er passage (`f7e84882`, 08:44:16), aucun refus | **VALIDE SOUS RESERVE** | `MVT-CM1-R1` (indice de l'item 5 qui ecarte les deux distracteurs a la fois), `MVT-CM1-R2` (quatre items sur six de la tache B se repondent sans lire l'observation, dont deux sur la meme scene), `MVT-CM1-R3` (consigne ne couvrant pas "le moins vite"), `MVT-CM1-R4` (deux formulations, items 3 et 9) | `couvert-valide`, aucune mention de couverture partielle due |
| Phenomenes geologiques et meteorologiques (`SCI-CM1-16`) | **REFUSE** 1er passage (`340c72c2`, 08:47:52 - item 2, le Vesuve presente comme un lieu distinct du volcan, fait faux) -> corrige par moi (`e28fd355`, 09:13:06) -> **REFUSE** au 2e passage (`ed669e99`, 09:37:27 - item 11 reecrit enseigne une consigne de securite fausse, contredite par l'item 7 du meme paquet) -> corrige par moi (`1be572a6`, 09:44:21) -> **VALIDE** au 3e passage, sans reserve (`896c9bde`, 09:57:28) | **VALIDE, sans reserve** | aucune - les quatre reserves ouvertes au 1er passage (`PHN-CM1-R1` a `PHN-CM1-R4`) sont toutes levees au 3e | `couvert-valide`, aucune mention de couverture partielle due |

**Bilan du Done pedagogique du sprint** : trois refus pedagogiques reels sur trois lots distincts
(`MAT-CE1-05` une fois, `EMC-CM1-09` deux fois, `SCI-CM1-16` deux fois), tous leves en cours de
sprint par des corrections de contenu testees et revalidees ; deux lots fermes sans aucun refus
(`EMC-CM1-01`, `SCI-CM1-03`). Zero refus restant a la cloture, cinq Done pedagogiques prononces
(quatre VALIDE SOUS RESERVE, un VALIDE sans reserve).

## 7. Tableau des obstacles du sprint

| Obstacle | Categorie | Route a | Statut a la cloture |
|---|---|---|---|
| Blocage initial `SOP-CE1-5` / garde-fou de rang sur `MAT-CE1-05` avant engagement - les trois formes d'origine du distracteur donnaient toujours une valeur superieure a la bonne reponse, rendant la bonne reponse predictible (Q1 de l'avis de faisabilite de `theo-archi`) | Pedagogie / formulation d'un contenu | `nadia-prof` | **Resolu avant l'ouverture du planning** - amendement de `SOP-CE1-5` (quatrieme forme, "emprunt reporte deux fois", et regle de rang etendue au paquet entier), commit `487c2469` (2026-10-01 21:57:03), repris dans `planning.md` section 4 |
| Refus de la derivation d'une borne a trois instances paralleles de `nadia-prof` | Pedagogie / capacite d'audit, seule `nadia-prof` peut trancher | `nadia-prof` (elle-meme, question qu'aucun autre agent ne peut arbitrer) | **Clos avant le planning, non bloquant pour s46** - `ARBITRAGE-PREALABLE-s46-trois-instances-refusees-le-vivier-borne-avant-la-capacite.md`, commit `efda796b` (21:56:27). Conditions cumulatives de reouverture ecrites en section 5 du document (vivier instruit superieur a la borne a deux instances, dernier sprint parallele clos sans occurrence des formes (a)/(b)/(c), affectation des trois terrains tenant `PAR-13`) : **aucune reunie a ce jour** |
| `MAT-CE1-05`, 1er passage : `SOP-CE1-4` non tenue sur deux items sans emprunt (`85 - 23`, `76 - 34`) | Pedagogie / exactitude d'un contenu | `nadia-prof` (verdict), `camille-po` (correction des criteres de story), `marc-dev` (correction du paquet) | **Resolu** - refus `124071d8` (08:37:13), criteres corriges `8a70a4b3` (09:01:58), paquet corrige `79e04615` (09:03:02), revalide **VALIDE SOUS RESERVE** `94bdb5de` (09:43:33) |
| `EMC-CM1-09`, 1er passage : item 4 faux (limitation de vitesse votee par le conseil municipal au lieu d'un arrete du maire) et `HIS-3` non tenue sur aucun item | Pedagogie / exactitude d'un contenu | `nadia-prof` (verdict), `sofia-sm` (casquette dev, correction) | **Resolu** - refus `62b3ed74` (08:45:13), corrige `6f27d1e9` (09:06:45) |
| `EMC-CM1-09`, 2e passage : items 10 et 11 reecrits en formulation exclusive fausse ("seul ou seule", "sans vote") | Pedagogie / exactitude d'un contenu | `nadia-prof` (verdict), `sofia-sm` (correction) | **Resolu** - refus `7a1d1ae5` (09:43:34), items 10 et 11 retablis a leur version du 1er passage (`f2793332`, 09:53:40), **VALIDE SOUS RESERVE** au 3e passage `d516dfea` (10:08:43), reserve `EMC-CM1-R7` |
| `SCI-CM1-16`, 1er passage : item 2, le Vesuve presente comme un lieu distinct du volcan qui entre en eruption, fait faux | Pedagogie / exactitude d'un contenu | `nadia-prof` (verdict), `sofia-sm` (correction) | **Resolu** - refus `340c72c2` (08:47:52), corrige `e28fd355` (09:13:06) |
| `SCI-CM1-16`, 2e passage : item 11 fait descendre l'escalier pendant un seisme, contredit l'item 7 du meme paquet | Pedagogie / exactitude d'un contenu | `nadia-prof` (verdict), `sofia-sm` (correction) | **Resolu** - refus `ed669e99` (09:37:27), corrige `1be572a6` (09:44:21), **VALIDE** sans reserve au 3e passage `896c9bde` (09:57:28) |
| Trou de mesure reactorielle sur `MAT-CE1-05` - mesure seulement sur `adapter-content` (`-pl adapter-content -am`), jamais sur le reacteur complet ; `TexteAccentueExposeParLePortEntrantTest`, dans `bootstrap`, rouge seulement a la fusion complete des cinq lots | Qualite / methode de mesure (pas une regle absente) | `yanis-qa` (constat au rapport qualite), `sofia-sm` (casquette dev, correction) | **Resolu avant la mesure de cloture** - corrige par moi, commit `346274c8` (00:33:41), verifie independamment par mutation par `yanis-qa` (section 2 de son rapport) et par `theo-archi` qui mesure le rouge reel a `8ab9ebf1` avant le correctif (section 5 de sa confirmation). Traite comme lecon de process en `retro.md`, section 1 |
| `SOP-CE1-R1` - sur les deux items sans emprunt de `MAT-CE1-05`, `SOP-CE1-5` n'admet que la somme comme distracteur, toujours superieure au premier terme (se repond sans poser l'operation) | Pedagogie / arbitrage sur sa propre condition, pas une dette de contenu ordinaire | `nadia-prof` (elle-meme, arbitrage qu'elle demande a elle-meme) | **Ouvert, non bloquant** - story d'arbitrage differee ecrite par `camille-po` dans `docs/backlog/backlog.md` ; le paquet n'est pas repris avant cet arbitrage (motif ecrit par `nadia-prof` elle-meme, fiche, verdict du 2e passage, section 4). Traite en `retro.md`, section 3 |
| Reserves de correction differee - `EMC-CM1-R5`, `EMC-CM1-R6` (groupees), `MVT-CM1-R1` a `MVT-CM1-R4` (groupees) | Pedagogie / correction differee de contenu, non bloquante | `camille-po` (stories ecrites), developpeur a affecter par `sofia-sm` | **Ouvert** - stories ecrites dans `docs/backlog/backlog.md`, non engagees dans un planning a ce jour |

## 8. La couverture du programme

**113/156 (72,4 %)**, verifie par moi sur `squad/programme/MATRICE-couverture.md`, note de sprint
en tete et les cinq lignes elles-memes (`MAT-CE1-05` ligne 758, `EMC-CM1-01` ligne 2106,
`EMC-CM1-09` ligne 2114, `SCI-CM1-03` ligne 2199, `SCI-CM1-16` ligne 2212), toutes `couvert-valide`
avec leur chronologie de refus/correction et leurs reserves - en hausse de 3,2 points sur s45
(108/156, 69,2 %).

## 9. Ce que cette review ne fait pas

- Elle ne prononce aucun Done pedagogique : les cinq verdicts de `nadia-prof` (section 6) sont
  deja prononces ; cette review les rapporte, elle ne les rend pas.
- Elle ne deplace aucun statut de `squad/programme/MATRICE-couverture.md` au-dela de ce que
  `camille-po` a deja execute (commit `8495e916`).
- Elle n'ouvre ni ne priorise les stories de correction differee (`SOP-CE1-R1`, `EMC-CM1-R5`,
  `EMC-CM1-R6`, `EMC-CM1-R7`, `MVT-CM1-R1` a `MVT-CM1-R4`, `PHN-CM1-R1` a `PHN-CM1-R4` - ces
  quatre dernieres levees, pas a reprendre) : `camille-po` les a deja ecrites dans
  `docs/backlog/backlog.md` ; les affecter a un planning futur n'appartient pas a cette ceremonie.
- Elle ne tranche pas la question d'une troisieme instance, deja refusee par `nadia-prof`
  (section 7) : elle la rapporte, elle ne la rouvre pas.

## Decisions prises

**Aucune `DEC` ni `ADR` n'est creee ou amendee apres l'ouverture du planning (2026-10-01 16:30).**
`DEC-0102` (strategie du sprint) et `DEC-0103` (ouverture du perimetre francais CE1) sont creees
avant l'horodatage de debut de ce planning, le meme jour - prealables necessaires a son ouverture.
`git diff --name-status 8b0fe8b4 8f446902 -- squad/decisions/produit squad/decisions/adr` ne porte
aucune creation ni amendement apres ces deux documents, en dehors de
`squad/decisions/REGISTRE-identifiants.md` (allocation d'identifiants par `nadia-prof` et par
`camille-po`, pas une decision).

Une seule decision m'appartient en propre dans ce sprint, deja ecrite dans `planning.md`,
section 8 : regrouper deux lots de basse complexite mecanique sur moi-meme, casquette dev, un par
instance (`EMC-CM1-09` pour l'instance A, `SCI-CM1-16` pour l'instance B), `marc-dev` seul sur le
lot de complexite moderee (`MAT-CE1-05`), `lea-dev` sur les deux autres lots de basse complexite
(`EMC-CM1-01`, `SCI-CM1-03`). Elle n'est pas reprise une seconde fois ici.
