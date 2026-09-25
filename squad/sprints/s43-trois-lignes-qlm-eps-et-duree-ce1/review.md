# Review - s43 - trois lignes : questionner le monde CP, education physique et sportive CM1,
mathematiques CE1, instance unique de `nadia-prof`

- **Horodatage de debut** (`planning.md`) : 2026-09-25 15:35 (UTC+02:00).
- **Horodatage de fin** : 2026-09-25 18:55:30 (UTC+02:00, horodatage du commit de fusion final
  `804b80eb`).
- **Duree effective** : 3 h 20 min 30 s.
- **Casquette** : `sofia-sm`, Scrum Master.
- **Arbre revu** : `round-two` a `804b80eb`, mesure dans un worktree dedie
  (`git worktree add ../recreo-s43-sofia-cloture -b s43-sofia-cloture round-two`), lignee verifiee
  (`bash outils/verifier-lignee.sh` -> *"le worktree sur la branche 's43-sofia-cloture' descend de
  la lignee de round-two"*), `git status --porcelain` vide avant toute mesure.

---

## 1. L'increment livre, en une phrase

Trois lignes du programme passent de `non couvert` a `couvert-valide` (`QLM-CP-08` objets
numeriques du quotidien CP, `EPS-CM1-10` effets de l'activite physique et regles de securite CM1 -
premier paquet d'EPS du produit sur ce niveau -, `MAT-CE1-12` calculer une duree simple CE1), sans
aucun refus pedagogique ; la couverture du programme passe de 98/156 (62,8 %) a **101/156
(64,7 %)**.

## 2. Le changement de process, verifie sur le disque et non repris de confiance

Deux faits distincts, a ne pas confondre, tous deux verifies par moi sur l'historique Git et non
sur ce que les commits affirment d'eux-memes.

### 2.1 Les deux discriminants CM1/CP et CE1/CM1, poses avant l'ecriture des paquets

L'avis de faisabilite de `theo-archi` (`avis-faisabilite.md`, commit `ea6771dc`) signale deux
collisions qu'aucun garde-fou du depot ne peut fermer : `EPS-CM1-10` reprendrait, au mot pres, ce
que deux paquets de CP servent deja (sections 6 et 7.2) ; `MAT-CE1-12` reprendrait la forme exacte
des douze premiers items de `cm1-problemes-durees` (section 6). Mon `planning.md` (sections 5 et
6) transmet ces deux conditions telles quelles, sans les trancher, aux destinataires nommes.

**Verifie sur l'historique** : `nadia-prof` a ecrit les deux discriminants (`EPS-9`, `DUR-CE1-7`)
dans un commit unique, `1abc3157`, sur une branche dediee (`s43-nadia-discriminants`), **avant**
que le premier paquet ne soit ecrit. Les trois commits de contenu qui suivent
(`e11a9325` LEA, `e980f31a` SOF, `f1dac400` MARC) ont tous les trois `1abc3157` dans leur
ascendance directe (`git log --format='%H %P'` sur chacun), et les trois fiches de conformite
citent les deux discriminants comme opposables au meme titre que les criteres d'acceptation
d'origine. **Consequence mesuree** : zero refus pedagogique sur les trois lots, contre un refus a
s42 et trois a s41 - le contraste est detaille en section 7 et repris en `retro.md`, section 1.

### 2.2 L'incident d'ecrasement de donnees - je corrige la formulation qui m'a ete transmise

**Ce qui m'a ete transmis pour cette review nommait "trois lots paralleles, chacun sur sa propre
branche" pour l'arbitrage pedagogique prealable des quatorze lignes de mathematiques CE1, comme la
lecon deja tiree d'un incident anterieur.** Verifie sur le disque, ce n'est pas ce qui s'est
passe, et je le corrige plutot que de relayer une description inexacte de ce qui a motive un
correctif.

Les trois commits d'arbitrage prealable (`0615ec0c` lot 3, `ad6507fc` lot 1, `fd28de6c` lot 2)
sont chacun **a parent unique**, empiles directement sur la meme ligne de `round-two` - trois
instances de `nadia-prof` ecrivant **le meme arbre partage**, pas trois branches distinctes.
**C'est cela meme qui a cause l'incident**, et non ce qui l'a evite : le commit de reparation
`804212e6` le dit dans son propre message, mot pour mot - *"ad6507fc annoncait ces allocations et
son diff sur le registre etait celui du lot 2 : trois instances ecrivaient le meme arbre, ma mise
a jour a ete perdue. round-two etait rouge sur `PAR-5` clause 2, dix echecs."* Trente-deux
identifiants du lot 1 (`NUM-CE1-1` a `MUL-CE1-7`) ont ete ecrases par le commit du lot 2 avant
d'etre restaures par la reparation, **hors sprint, avant l'ouverture du planning de s43** (les
quatre commits se situent entre `5f6ba3d7` et `3d8b0c56`, tous anterieurs a `5bd99d74`, le premier
commit cite par mon propre planning). La lecon est ecrite dans ce meme commit de reparation :
*"lancer le garde-fou avant le commit ne suffit pas quand l'arbre est partage."*

**Ce que le sprint proprement dit a fait de cette lecon, et que je verifie separement.** Les trois
lots de developpement (`QLM-CP-08`, `EPS-CM1-10`, `MAT-CE1-12`), eux, **ont bien ete ecrits sur
trois branches paralleles distinctes** : `git log --format='%H %P'` sur `e11a9325`, `e980f31a` et
`f1dac400` montre les trois avec le **meme parent unique**, `58faca94` (lui-meme un merge de la
branche de planning et de la branche des discriminants) - trois branches reellement divergentes
depuis la meme base, refusionnees ensuite (`6ab1ac71` fusion de `s43-marc`, puis `8ba806fe` et
`9e1622dc` fusion de `round-two` dans `s43-lea`). **C'est l'inverse de s42**, ou les trois
developpeurs avaient commite directement sur `round-two` (`review.md` de s42, en-tete). Aucune
collision de registre n'est survenue sur ce perimetre-la : les sept identifiants de reserve
(`NUQ-CP-R1/R2`, `EPS-CM1-R1` a `R4`, `DUR-CE1-R1`) ont tous ete alloues dans le commit de verdict
unique de `nadia-prof` (`07c1dcd8`), une seule instance, aucune concurrence possible sur ce fichier
a ce moment-la.

**Ce que je n'affirme pas** : que le choix de brancher les trois lots de developpement ait ete une
reponse deliberee a l'incident de l'arbitrage prealable - aucun document du depot ne l'ecrit en
ces termes, et je ne le deduis pas. Ce que je verifie et rapporte : les deux faits existent
separement, l'un a cause un incident reel et reste a corriger dans la pratique (une instance qui
lance son garde-fou avant de commiter ne se protege pas d'une autre instance qui commite entre
temps), l'autre a fonctionne sans incident sur ce sprint-ci.

## 3. Le Done technique - quatre jobs, relances individuellement par moi, sur l'arbre final

Conformement a `squad/README.md` section 9 et a l'amendement du 2026-09-08 a `CLAUDE.md` : un agent
qui annonce la porte technique nomme les jobs qu'il a lances et ne deduit pas les autres. Les
quatre jobs de `.github/workflows/ci.yml` ont ete relances par moi, individuellement, sur
`round-two` a `804b80eb` (`git status --porcelain` verifie vide avant et apres chaque mesure) :

| Job | Commande exacte | Resultat |
|---|---|---|
| `build` | `bash outils/mvn-jdk21.sh -B clean verify` | **BUILD SUCCESS**, 6 modules sur 6, JDK constate **21.0.10** (celui que la commande resout, pas la toolchain declaree) |
| `chemins` | `bash .github/scripts/verifier-chemins-test.sh` puis `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' \| bash .github/scripts/verifier-chemins.sh` | **vert** : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."* |
| `prefixes` | `bash .github/scripts/verifier-prefixes-test.sh`, `bash .github/scripts/commit-atteignable-test.sh`, puis `git log --no-merges --format=%s -20 \| bash .github/scripts/verifier-prefixes.sh` | **vert** : *"Tous les commits portent un prefixe d'agent connu."* |
| `outils` | balayage des sept juges purs bash (`outils/*-test.sh`, `squad/outils/*/*-test.sh`) et des deux juges purs Python (`outils/*-test.py`, lances avec `python`, pas `bash`) | **vert**, **9 juge(s) pur(s) execute(s)**, 0 echec |

Ma premiere tentative sur le job `outils` a lance les deux fichiers `.py` avec `bash` par erreur
(deux erreurs de syntaxe, sans rapport avec le contenu du sprint) ; relance avec `python`, les deux
passent (5 tests puis 10 tests, `unittest`, `OK`). Je le signale pour que ce ne soit pas lu comme
un doute sur le vert final.

**Compte de tests, mesure par moi sur le journal complet du lancement de `build`, deux methodes
independantes** (blocs `[INFO] Results:` par module, et somme des attributs `tests="..."` des
rapports XML Surefire/Failsafe) - **jamais la somme des lignes `Tests run:` des `.txt`** :

| Module | Tests run |
|---|---|
| recreo-domain | 120 |
| recreo-application | 17 |
| recreo-adapter-web | 400 |
| recreo-adapter-content | 944 |
| recreo-bootstrap (surefire, unitaires) | 288 |
| recreo-bootstrap (failsafe, integration) | 6 |
| **Total unitaires** | **1769** |
| **Total integration** | **6** |

**1775 tests, 0 echec, 0 erreur, 0 ignore**, les deux methodes concordant exactement module par
module. **Un ecart mineur et sans effet, nomme plutot que tu** : ma seconde methode compte
**240** fichiers de rapport XML, contre les **239** que `theo-archi` et `yanis-qa` citent tous les
deux sur `9e1622dc`. Je n'ai pas retrace la cause de cet ecart d'un fichier - aucune piste directe
dans le temps qui m'etait imparti - et je le dis plutot que d'en inventer une : il ne change aucun
total, verifie module par module (120/17/400/944/294 sur les deux methodes, `bootstrap` a
294 = 288 + 6). **1775 est le chiffre que je porte, identique a celui de `theo-archi`
(`confirmation-archunit.md`, section 1) et de `yanis-qa` (`rapport-qualite.md`, section 1)** :
aucun commit de code n'est intervenu entre leurs mesures et la mienne, seulement les trois fiches
de conformite, le verdict de `nadia-prof` et la mise a jour de la matrice.

**ArchUnit, recompte par moi de facon independante** (occurrences litterales de `static final
ArchRule` et `@ArchTest` sous les arbres de source des cinq modules du pom agregateur, `target/`
exclu) : **36 `static final ArchRule`, 35 `@ArchTest`** - inchange depuis s42 et depuis l'avis de
faisabilite, identique au compte de `theo-archi` et de `yanis-qa`.

## 4. La confirmation ArchUnit de `theo-archi`

`confirmation-archunit.md` verifie, sur l'arbre integre a `9e1622dc` puis remesure sur l'arbre
fusionne a `e50f0dd7` (chiffres identiques sur les deux), quatre questions : les tests ArchUnit
restent verts sur le code neuf des trois lots (oui, **zero ligne de Java de production** sur les
trois lots - trois paquets YAML, trois entrees de catalogue, leurs tests) ; `ADR-0101` (plafond de
dette de longueur CE1, 4) n'est pas depasse par le paquet neuf, **prouve par mutation** (huit mots
ajoutes a un enonce -> deux rouges, restaure -> vert) et non par lecture d'un vert ; `ADR-0102`
(lecture des durees) fonctionne sur le contenu neuf, **prouve par mutation** (une ecriture non
prevue -> rouge nommant le paquet, l'item et la valeur) ; aucun `ADR` ni aucune regle ArchUnit
neuve n'est du au-dela de ce qui est deja fusionne - les deux discriminants pedagogiques de
`nadia-prof` (`EPS-9`, `DUR-CE1-7`) sont encodes en tests de paquet, pas en `ArchRule`, et
`theo-archi` explique pourquoi une regle qui comparerait deux paquets sur le sens serait
decorative.

**Un fait que `theo-archi` nomme sans le trancher, et que je ne tranche pas non plus ici** : la
marge reelle sur `ADR-0101` est d'un seul mot sur cinq des 168 enonces de CE1 desormais servis
(section 3.2 de la confirmation) - le mot le plus long des 24 enonces du paquet neuf compte 19
lettres/chiffres, la barre en tolere 20. Je le reprends en `retro.md`, section 2, comme point de
fragilite plutot que comme obstacle a router : la regle tient, elle ne tient pas avec confort.

**Aucun `ADR` ni aucune regle ArchUnit neuve n'est ecrit apres l'avis de faisabilite.** `ADR-0101`
et `ADR-0102` sont tous deux fusionnes **avant** le premier paquet (`9e388a11`), verifie par
`theo-archi` (section 5.4) et par moi independamment (`squad/decisions/adr/ADR-index.md` ne porte
aucune entree posterieure a `9e388a11` sur ce sprint).

## 5. Le rapport qualite de `yanis-qa`

`rapport-qualite.md` (mesure sur worktree dedie `s43-yanis` a `9e1622dc`, lignee verifiee) : Done
technique confirme sur les quatre jobs, tous nommes individuellement. Trois garde-fous mordus par
mutation sur le materiau **reellement servi**, restauration verifiee par `git status --short` vide
apres chaque mutation :

- `ADR-0101` sur `ce1-problemes-durees.yaml` (enonce allonge de 12 a 37 mots) -> rouge, deux
  assertions (le paquet nomme, et le total CE1 qui passe a 5) ; restaure -> vert.
- `ADR-0102` sur le meme paquet (un distracteur remplace par *"1 heure et quart"*, ecriture non
  prevue) -> rouge, message nommant le paquet, l'item et la valeur ; restaure -> vert.
- `EPS-9` clause 3 sur `cm1-eps-effets-activite.yaml` (un distracteur remplace par une bonne
  reponse deja servie au CP) -> rouge ; restaure -> vert. **Perimetre honnete rappele avant le
  resultat** : cette clause est formelle, elle ne prouve pas la profondeur reelle du CM1 - le
  rapport cite lui-meme le precedent de s42 (`EMC-CM1-R1`) ou un garde-fou textuel etait vert
  pendant que le fond refaisait le CP.

Tracabilite des criteres d'acceptation des trois stories, lot par lot (section 3 du rapport) :
chaque critere est verifie encode dans une methode nommee, avec ce qui reste garde de
`nadia-prof` explicitement liste quand un motif n'est pas automatisable - en particulier, les
cinq clauses d'`EPS-9` et les cinq clauses de `DUR-CE1-7` n'ont, de l'aveu meme de `theo-archi` et
de `yanis-qa`, **aucun garde-fou qui compare deux paquets** : elles n'ont ete verifiees qu'une
fois, par `nadia-prof`, en lisant les paquets cote a cote.

## 6. Le `relecteur-pedagogique` et un point qu'il n'a pas encore vu

**Aucune intervention du `relecteur-pedagogique` dans s43** : `git log 5bd99d74..804b80eb --grep
"\[REL\]"` ne rend aucun commit. **Un point est cependant deja route vers lui, par `nadia-prof`
elle-meme, dans sa fiche de conformite** : `FICHE-bandit-manchot-objets-numeriques-cp.md`,
section 8 - l'item 7 de `cp-qlm-objets-numeriques` porte un distracteur dont la construction
*"parce que l'ecran est trop petit a lire"* n'est pas du francais correct (*"trop petit pour etre
lu"*), en tension avec l'amendement du 2026-08-04 a `DEC-0002` que `CLAUDE.md` cite. `nadia-prof`
le nomme explicitement et ne le corrige pas, hors de son mandat. **Je le reprends en obstacle
ouvert, section 8.**

## 7. Le Done pedagogique - trois verdicts, rapportes par cette review, pas prononces par elle

Instance unique de `nadia-prof` (`DEC-0099`, section 9), borne 5,0/5,0, cible 4,0/5,0, marge 1,0
non consommee au niveau du sprint.

| Ligne | Trajectoire | Verdict final | Reserves | Ferme |
|---|---|---|---|---|
| Objets numeriques du quotidien (`QLM-CP-08`) | Auditee une fois, aucun refus | **VALIDE SOUS RESERVE** | `NUQ-CP-R1`, `NUQ-CP-R2` (non bloquantes) | `couvert-valide` |
| Effets de l'activite physique et regles de securite (`EPS-CM1-10`) | Auditee une fois, aucun refus | **VALIDE SOUS RESERVE** | `EPS-CM1-R1` a `-R4` (non bloquantes) | `couvert-valide`, mention exigee d'avance portee mot pour mot |
| Calculer une duree simple (`MAT-CE1-12`) | Auditee une fois, aucun refus | **VALIDE SOUS RESERVE** | `DUR-CE1-R1` (non bloquante) | `couvert-valide` |

**Zero refus sur trois lots**, contre un refus sur cinq lots a s42 et trois refus a s41 (`retro.md`,
section 1, detaille le lien avec la section 2 ci-dessus). Deux corrections notables que
`nadia-prof` porte sur ses propres textes plutot que sur le travail des developpeurs, toutes deux
tenues dans la fiche EPS : sa clause `EPS-9` clause 3 (aucune bonne reponse du CP en distracteur
au CM1) est **dementie par son propre exemple admis** dans la clause 1, et elle fixe elle-meme
l'interpretation qui tient (interdiction de rendre **fausse** une reponse du CP, pas de la
reemployer dans un item ou elle est hors sujet) ; l'exemple qu'elle avait mis au referentiel EPS
CM1 le 2026-09-03 (*"pourquoi s'echauffe-t-on avant de courir ?"*) etait deja rendu injouable par
sa propre clause `EPS-9` clause 2 avant que le paquet ne soit ecrit - **le referentiel porte donc
toujours un exemple que sa propre regle interdit**, motif exact de la reserve `EPS-CM1-R3`
(l'echauffement, premier mot du libelle, n'est servi par aucun item). Je route ce dernier point en
obstacle ouvert, section 8 : le referentiel source n'a pas ete corrige, seule la fiche le signale.

La mention exigee d'avance sur `EPS-CM1-10` (*"connaissances qui entourent la pratique physique
seules ; aucune activite physique, aucun geste et aucune performance ne sont servis par ce
produit"*) est portee mot pour mot dans `MATRICE-couverture.md` (commit `2c3cbf42`), verifiee par
moi par comparaison caractere pour caractere avec le referentiel.

## 8. Tableau des obstacles du sprint

| Obstacle | Categorie | Route a | Statut a la cloture |
|---|---|---|---|
| Perimetre de s43/s44 non chiffrable sans arbitrage prealable sur quatorze lignes de mathematiques CE1 (`DEC-0099`, section 4) | Produit / perimetre | `camille-po`, `nadia-prof` | **Resolu** - trois lots d'arbitrage rendus le 2026-09-25, perimetre rechiffre en section 9 de `DEC-0099`, **Acceptee** |
| Incident d'ecrasement de donnees pendant l'ecriture des trois lots d'arbitrage prealable CE1 sur un arbre partage (32 identifiants du lot 1 perdus par le commit du lot 2, `round-two` rouge sur `PAR-5` clause 2) | Qualite / gouvernance des identifiants, arbre partage | `nadia-prof` (repare elle-meme) | **Resolu, hors sprint, avant l'ouverture du planning** - `804212e6`, les 32 identifiants restaures, ligne d'etat portee a 742/120 -> 774/125. Lecon ecrite dans le commit de reparation ; voir section 2.2 pour la correction de la description qui m'avait ete transmise |
| `EPS-9` clause 3, telle qu'ecrite par `nadia-prof`, dementie par son propre exemple de la clause 1 | Pedagogie / coherence d'un critere ecrit le jour meme | `nadia-prof` (corrige elle-meme, dans le meme verdict) | **Resolu** - interpretation fixee dans le bloc de validation pedagogique de la fiche EPS (section 4.a) : `CYC-1` interdit de rendre fausse une reponse du CP, pas de la reemployer hors sujet |
| Le referentiel `education-physique-et-sportive/competences-cm1.md` porte toujours, sous la signature de `nadia-prof`, un exemple (*"pourquoi s'echauffe-t-on avant de courir ?"*) que sa propre clause `EPS-9` clause 2 rend injouable depuis le 2026-09-25 | Pedagogie / redaction de referentiel | `nadia-prof` | **Ouvert, non corrige a la cloture** - signale par `nadia-prof` elle-meme dans la fiche de conformite comme motif de la reserve `EPS-CM1-R3`, mais le referentiel source n'a pas ete amende dans ce sprint |
| Item 7 de `cp-qlm-objets-numeriques` : construction fautive (*"trop petit a lire"*) dans un distracteur lu par l'enfant | Redaction / francais correct (amendement `DEC-0002`) | `relecteur-pedagogique` | **Ouvert, non tranche a la cloture** - signale et route par `nadia-prof` elle-meme (`FICHE-bandit-manchot-objets-numeriques-cp.md`, section 8), hors de son mandat de le corriger |
| Les cinq clauses d'`EPS-9` et les cinq clauses de `DUR-CE1-7` (discriminants CM1/CP et CE1/CM1) n'ont aucun garde-fou qui compare deux paquets ; elles n'ont ete verifiees qu'une fois, en lisant, par `nadia-prof` | Technique / architecture, portee des garde-fous de contenu | `theo-archi` (le compare-t-on un jour, et comment), `nadia-prof` (le fond reste sa garde) | **Ouvert, non tranche** - signale par `theo-archi` des l'avis de faisabilite (sections 6 et 7.2), confirme par `yanis-qa` et par `nadia-prof` elle-meme dans les trois fiches. Non bloquant pour ce sprint : le fond a ete verifie en jouant. Repris en `retro.md`, section 2 |
| Frontiere entre `MAT-CE1-18` (sens de la division, candidate s44) et `MAT-CE1-06` (sens de la multiplication, hors perimetre) sur le fait "groupement lu comme une multiplication a trou" - signalee par deux des trois lots d'arbitrage sans etre tranchee | Pedagogie / architecture, frontiere entre deux competences voisines | `sofia-sm`, qui route a `theo-archi` et a l'instance qui ecrira la story de `MAT-CE1-18` | **Ouvert, non tranche** - `DEC-0099`, section 9.8, me route explicitement ce point avant que l'une des deux lignes ne soit ecrite ; `MAT-CE1-06` n'est engagee dans aucun perimetre chiffre a ce jour, `MAT-CE1-18` est candidate pour s44. A poser avant l'ouverture d'un planning qui engagerait l'une des deux |
| Le materiau visuel de `MAT-CE1-17` (dessins d'axe et de quadrillage) et son cout ne sont pas chiffres | Architecture / charge | `theo-archi` | **Ouvert, non tranche, sans effet sur s43** - `DEC-0099`, section 9.8 ; `MAT-CE1-17` n'est candidate d'aucun perimetre chiffre a ce jour |

## 9. Le registre de dette - inchange a la cloture de s43

**Ouverture de s43 : 9,25** (`squad/sprints/s42-fusion-six-lignes-deux-instances/review.md`,
section 9). Verifie par moi : recherche des identifiants de ce sprint (`QLM-CP-08`, `EPS-CM1-10`,
`MAT-CE1-12`, `DEC-0099`) dans `DEC-0086` et `DEC-0087` : aucune occurrence dans les deux textes.
Ce sprint ferme trois lignes hors du vivier `DEC-0086`/`DEC-0087`, comme s39 a s42. **Registre de
dette a l'ouverture d'un sprint futur : 9,25, inchange.**

## 10. La couverture du programme

**101/156 (64,7 %)**, verifie par moi sur `squad/programme/MATRICE-couverture.md`, commit
`2c3cbf42` (*"cloture matrice - QLM-CP-08, EPS-CM1-10, MAT-CE1-12 passent a couvert-valide"*) :
trois fermetures, aucune ouverture partielle, aucun refus a lever - en hausse de 1,9 point sur s42
(98/156, 62,8 %).

## 11. L'avis de `theo-archi` et le lien vers `DEC-0099`

`avis-faisabilite.md` (`ea6771dc`, amende le meme jour a `9e388a11` apres l'ecriture d'`ADR-0101`
et d'`ADR-0102`) rend un verdict sur les trois lots retenus par `DEC-0099` : un faisable en l'etat
(`S43-QLM-NUMERIQUE`), deux sous condition (`S43-EPS-EFFETS-ACTIVITE`, frontiere verticale avec le
CP ; `S43-MAT-DUREE`, collision avec `cm1-problemes-durees` et barre de longueur CE1 non tenue par
aucun automate). Les deux conditions sont levees par mon `planning.md`, sections 5 et 6, transmises
aux destinataires nommes sans etre tranchees par le planning lui-meme. Un seul `ADR` etait du,
`ADR-0101` ; `ADR-0102` s'y ajoute en amendement du meme jour pour fermer un piege de lecture que
l'avis nomme sans le trancher (section 5.2). Les deux sont fusionnes avant le premier paquet.

`DEC-0099` (`squad/decisions/produit/DEC-0099-proposition-de-perimetre-pour-s43-et-s44.md`) est
**Acceptee (2026-09-25), amendee le meme jour** : sections 1 a 8 tracent l'etat propose avant
amendement (deux lignes tarifees 1,5 chacune, cible 3,0/5,0) ; section 9 rechiffre a trois lignes
apres l'arbitrage prealable des quatorze lignes CE1 (`MAT-CE1-12` ajoutee a 1,0, cible 4,0/5,0,
marge 1,0). L'utilisatrice a valide le perimetre rechiffre de la section 9. `DEC-0099` propose
egalement un perimetre pour s44 (`MUS-CP-11`, `SCI-CM1-13`, `MAT-CE1-18`, section 9.6) : **cette
review ne l'engage pas** - il reste une proposition, a instruire par un planning futur si
l'utilisatrice le confirme.

## 12. Ce que cette review ne fait pas

- Elle ne prononce aucun Done pedagogique : les trois verdicts de `nadia-prof` (section 7) sont
  deja prononces ; cette review les rapporte, elle ne les rend pas.
- Elle ne deplace aucun statut de `squad/programme/MATRICE-couverture.md` au-dela de ce que
  `camille-po` a deja execute.
- Elle ne corrige ni le referentiel EPS CM1, ni l'item 7 de `cp-qlm-objets-numeriques`, ni la
  frontiere entre `MAT-CE1-18` et `MAT-CE1-06` : les trois restent routes, non bloquants pour ce
  sprint (section 8).
- Elle n'engage aucun perimetre pour s44 : `DEC-0099`, section 9.6, reste une proposition.
- Elle ne retranche pas la cause de l'ecart d'un fichier de rapport XML (239 contre 240, section 3)
  faute de l'avoir retracee ; elle le signale plutot que de la deduire.

## Decisions prises

**Aucune decision de sprint neuve n'est prise dans cette review.** La decision du sprint a ete
prise ailleurs et est enregistree ici pour memoire, verifiee physiquement presente sur le disque,
avec son statut, avant d'etre citee :

- `DEC-0099` - proposition de perimetre pour s43 et s44, amendee le 2026-09-25 apres l'arbitrage
  pedagogique prealable des quatorze lignes de mathematiques CE1 - **Acceptee, amendee le
  2026-09-25** sur le perimetre rechiffre de la section 9 (trois lignes pour s43). Verifiee dans
  `squad/decisions/produit/DEC-index.md` et sur le disque.

**Deux `ADR` sont crees ce sprint, avant le developpement et non par cette ceremonie** :
`ADR-0101` (plafond de dette de longueur d'enonce au CE1) et `ADR-0102` (lecture des ecritures de
duree), tous deux **Accepte**, verifies dans `squad/decisions/adr/ADR-index.md` et sur le disque.
**Aucun `DEC` ni `ADR` n'est cree par cette ceremonie de review elle-meme.**
