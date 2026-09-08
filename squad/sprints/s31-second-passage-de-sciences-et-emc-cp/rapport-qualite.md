# Rapport qualite - Sprint 31

**Auteur** : `yanis-qa`. **Depot mesure** : mon worktree dedie (`git worktree add
../recreo-s31-yanis -b s31-yanis 3181b5e6`), lignee verifiee - `git log` de ce worktree pointe sur
`3181b5e6`, meme commit que la branche `round-two` fournie, arbre propre confirme par `git status`
avant toute mesure. Toute mesure de ce rapport est lancee depuis ce worktree, jamais depuis le
worktree principal ni celui d'un autre agent (amendement de `squad/README.md` a la retro de s30).

**JDK mesure** : 21.0.10, rapporte par la JVM des tests elle-meme (log Spring Boot des tests
d'integration), pas par la toolchain declarative.

---

## 1. Porte technique : verdict

**VERT.** `bash outils/mvn-jdk21.sh -B clean verify` sur le reacteur complet (racine, pas de `-pl`) :
**BUILD SUCCESS**, 2 min 20 s.

**Compte de tests, mesure independamment, methode de l'amendement s11** : somme des attributs
`tests`/`failures`/`errors`/`skipped` de la balise `<testsuite>` de chaque rapport XML de
`surefire-reports` et `failsafe-reports` de tous les modules du reacteur, apres le `clean verify`
ci-dessus (script Python, exclut les copies sous `target/test-classes`, qui ne sont pas des
rapports mais des fixtures de test recopiees) :

- **189 fichiers `TEST-*.xml`**.
- **1385 tests, 0 echec, 0 erreur, 0 ignore.**

Ce chiffre **confirme exactement** celui annonce par `sofia-sm` (189 rapports, 1385 tests, 0/0/0).
Catalogue : **66 entrees** (`grep -c "^  - id:" catalogue.yaml`), confirme.

**Tous les modules portent des tests** (`find <module>/src/test -iname "*.java" | wc -l`) : `domain`
14, `application` 3, `adapters/adapter-web` 49, `adapters/adapter-content` 96, `bootstrap` 80. Aucun
module sans test.

**33 regles `ArchRule`, inchange** (`grep -rn "static final ArchRule " --include=*.java` sur les
cinq arbres de test de production, hors le faux depot de test-ressources qui declare un type
`ArchRule` factice pour sa propre mise a l'epreuve, ecarte a la main : 35 declarations brutes, 2
factices, 33 reelles) - confirme le compte de `theo-archi` (`confirmation-archunit.md`).

**Commits du sprint** (`git log dbbfe129..3181b5e6 --oneline`) : 15 commits, tous prefixes par un
tag d'agent connu (`[SOF]`, `[LEA]`, `[MARC]`, `[CAM]`, `[THEO]`, `[NAD]`) - aucun rejet CI attendu
sur ce point.

---

## 2. Le defaut cherche une quatrieme fois : paquet neuf sans test de matiere, declaration inexacte

**Test de matiere present sur les huit paquets neufs**, dans le meme commit que le contenu
(`ls adapters/adapter-content/src/test/java/com/recreo/adapter/content/`, un fichier par paquet) :

| Paquet | Ligne | Test de matiere |
|---|---|---|
| `cp-emc-emotions-autrui.yaml` | `EMC-CP-01` | `PaquetCpEmcEmotionsAutruiTest` |
| `cp-emc-formules-de-politesse.yaml` | `EMC-CP-05` | `PaquetCpEmcFormulesDePolitesseTest` |
| `cp-emc-exclusion.yaml` | `EMC-CP-03` | `PaquetCpEmcExclusionTest` |
| `cp-emc-regle-souhait-loi.yaml` | `EMC-CP-07` | `PaquetCpEmcRegleSouhaitLoiTest` |
| `cm1-sciences-etats-matiere.yaml` | `SCI-CM1-01` | `PaquetCm1SciencesEtatsMatiereTest` |
| `cm1-sciences-regimes-alimentaires.yaml` | `SCI-CM1-07` | `PaquetCm1SciencesRegimesAlimentairesTest` |
| `cm1-sciences-familles-materiaux.yaml` | `SCI-CM1-11` | `PaquetCm1SciencesFamillesMateriauxTest` |
| `cm1-sciences-etapes-developpement.yaml` | `SCI-CM1-08` | `PaquetCm1SciencesEtapesDeveloppementTest` |

**Le defaut de s28/s29 n'a pas recidive une quatrieme fois.** Tous les huit portent onze items
(`grep -c "^  - enonce:"` sur chaque fichier), tous declarent `lignesDeReferentiel`, tous ont leur
test.

### La declaration de couture de `cm1-sciences-etats-matiere`, verifiee comme un fait

Le commentaire (lignes 32-37 du paquet) affirme : *"l'indice de l'item du sel [de
`cm1-sciences-melanges`] ('Il reste caché dans l'eau tant que celle-ci garde sa forme liquide.',
corrige a S31-5, MEL-R1) laissait volontairement ouverte la question de ce que devient l'eau."*

**Verifie mot pour mot dans `cm1-sciences-melanges.yaml`** (ligne 91, apres la reprise `S31-5`) :
l'indice de l'item du sel se termine exactement par *"Il reste caché dans l'eau tant que celle-ci
garde sa forme liquide."* - citation exacte, caractere pour caractere. La phrase ne dit effectivement
ni que l'eau disparait ni ce qu'elle devient : la declaration de couture est **exacte**.

### Les trois reprises de `S31-5` (`MEL-R1/R2/R3`), verifiees contre le contenu reel

- **`MEL-R1`** : indice du sel confirme ci-dessus ; correction a cout nul associee ("carafe" contre
  "bol") verifiee - l'item du sirop de menthe dit desormais "bol" a la fois dans l'enonce (ligne 80)
  et dans l'indice (ligne 83, "Tout le bol a la même couleur").
- **`MEL-R2`** : l'enonce de l'item de decantation (ligne 96) decrit desormais le resultat observe
  ("l'eau est redevenue claire et la terre s'est déposée au fond") sans nommer le geste "laisser
  reposer" - conforme a la declaration.
- **`MEL-R3`** : le troisieme choix de l'item eau/huile est passe de "un mélange qui n'existe pas" a
  "un mélange qui n'est ni homogène ni hétérogène" - un candidat que l'aspect trompeur du melange
  (uniforme juste apres avoir remue) rend plausible, conforme a la declaration meme si son caractere
  "serieusement envisageable" au sens de `PLA-1` reste un jugement de `nadia-prof`, pas une mesure.

### `S31-6` (`OBT-R1/R2`) - la declaration du paquet est exacte, mais le critere du backlog qu'elle sert ne l'est pas

**`OBT-R1` : verifie, exact.** L'indice de l'item du velo (ligne 61) ne dit plus "fatigue proche" et
reste dans le cadre de la distance ("Pour un même trajet, pédaler fatigue moins que marcher à
pied.") - conforme.

**`OBT-R2` : la declaration du paquet et le test qui l'accompagne sont fideles a la source, mais le
backlog restitue mal cette source - trouve en remontant du paquet au commentaire, puis du
commentaire a la fiche de `nadia-prof`.**

- Le commentaire du paquet dit : *"Chaque indice n'ecarte plus qu'un seul distracteur nomme, sur le
  modele de l'item du ventilateur qui n'en ecartait deja qu'un seul."* Verifie sur le contenu reel :
  l'indice de la lampe (ligne 68) dit *"elle ne sert jamais à réchauffer les mains"* - nomme le
  premier distracteur, tait le second ("recharger un téléphone") ; l'indice du taille-crayon (ligne
  71) dit *"il ne sert jamais à effacer ce qu'on a écrit"* - nomme le premier, tait le second
  ("ranger les crayons"). **La declaration du paquet est exacte : chaque indice ecarte bien un seul
  distracteur, pas deux.**
- **Le critere du backlog (`docs/backlog/backlog.md`, story `S31-6`)** dit : *"aucun ne nomme l'un
  des deux distracteurs de son item pour l'ecarter [...] un indice ne nomme aucun des distracteurs
  de son item, plausibles ou non."* Litteralement : **zero** distracteur nomme. Le contenu livre en
  nomme **un**. Lu au pied de la lettre, le critere du backlog n'est pas tenu.
- **Mais `squad/conformite/FICHE-bandit-manchot-objet-technique-cm1.md`, section 6** - le document
  ou `nadia-prof` a elle-meme ouvert `OBT-R2` le 2026-09-06, et que la story cite comme lien - ecrit
  la propriete qu'elle demande, mot pour mot : *"un indice ecarte **au plus un** distracteur nomme.
  Il donne le critere ou tue la conception fausse ; il ne fait pas le tour de la liste."* Elle donne
  meme l'item du ventilateur comme *"la bonne mesure, sert de modele"* - exactement le modele que le
  paquet dit avoir suivi.
- **Verdict de cette verification** : le contenu et son test (`obtR2LesIndicesDeLaLampeEtDuTaille
  CrayonNEcartentPlusLesDeuxDistracteursALaFois`, qui verifie precisement l'absence du second
  distracteur nomme dans chaque indice, jamais l'absence des deux) sont fideles a la source
  autorisee, `nadia-prof`. **C'est la formulation du critere d'acceptation dans le backlog qui a
  durci "au plus un" en "aucun" en le recopiant** - un ecart de transcription entre le document
  source et sa reprise, pas un defaut de contenu ni de test. Je le signale pour que la restitution
  du critere de `S31-6` soit corrigee dans le backlog, et pour qu'aucune lecture future de la story
  seule (sans remonter a la fiche) ne conclue a tort que la reprise a echoue sa propre condition.

---

## 3. Les garde-fous ecrits ce sprint, mordus par moi sur le materiau reel

### `ADR-0086`, clause 2 (`FOP-R4`, cliquet a plafond 1)

Mutation : `DETTE_D_AMORCE` mis a `Set.of()` dans
`bootstrap/src/test/java/com/recreo/archi/LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest.java`,
puis `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test
-Dtest=LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest#leContenuServiEstConforme`. **Resultat :
ROUGE** - "cm1-emc-fait-opinion.yaml : 8 distracteurs sur 10 commencent par 'en la croyant parce'"
et la meme mesure pour "en le croyant parce". Confirme que l'exemption protege une condition
**encore reellement fautive** (le chiffre exact de `nadia-prof`, 8 sur 10) - le cliquet a plafond 1
n'est pas decoratif. Restauration (`git checkout --`), tree propre confirme, reexecution : **VERT**,
7/7 tests de la classe.

### `ADR-0086`, clause 1 (item servi plusieurs fois sous plusieurs noms)

Mutation sur du materiau reel neuf de ce sprint : deux items ajoutes a `cm1-sciences-regimes-
alimentaires.yaml`, dupliquant integralement l'item 8 existant (memes propositions, meme bonne
reponse de six mots "à construire son corps pendant qu'il grandit"). **Resultat : ROUGE** - "les
items [8, 11, 12] presentent le meme moule de propositions et portent tous la meme bonne reponse
[...] Ce sont 3 ecrans pour un seul item". La clause a aussi declenche, en effet de bord non
recherche, la clause 2 sur les distracteurs dupliques - preuve que les deux clauses restent actives
independamment sur le meme materiau. Restauration (`git checkout --`), tree propre confirme,
reexecution : **VERT**.

### `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` (`ADR-0085`, `PAR-5`)

Mutation : un commentaire portant un identifiant fictif jamais alloue (`ZZZ-9`) ajoute a la fin de
`cm1-sciences-etats-matiere.yaml`. **Resultat : ROUGE** - "[...] ecrit l'identifiant ZZZ-9, que
squad/decisions/REGISTRE-identifiants.md n'alloue pas". Confirme que le garde-fou mord encore sur
le depot reel, apres avoir deja mordu une fois pour de vrai a l'assemblage du planning (quatre
identifiants de `nadia-prof` non visibles du worktree de `theo-archi`, corriges par `sofia-sm` avant
l'ouverture, planning section 0). Restauration, tree propre confirme, reexecution : **VERT**, 4/4
tests.

*Note de `theo-archi`, 2026-09-08, cloture de s31 : cette mutation est juste et son message est
reproduit ici au caractere, mais la citation elle-meme a fait rougir la clause 2 a l'assemblage -
le garde-fou ne distingue pas un identifiant employe d'un identifiant cite en preuve, et
l'amendement d'`ADR-0085` du 2026-09-08 tranche qu'il ne le distinguera pas. Rien de ce rapport
n'est modifie ; l'identifiant fictif est desormais nomme dans la section 3 du registre, sous un
cliquet a plafond exact. Le "4/4" ci-dessus reste juste a sa date : la classe porte six tests
depuis cet amendement.*

**Les trois mutations ont chacune ete restaurees par `git checkout --` et verifiees par
`git status --porcelain` vide avant la mesure suivante.**

---

## 4. Deux points de contenu verifies comme des faits

### `SCI-CM1-07` sert bien herbivore/carnivore/omnivore, jamais vegetarien

`grep -rin "vegetarien" adapters/adapter-content/src/main/resources/contenu/` : la seule occurrence
du mot est dans le commentaire de `cm1-sciences-regimes-alimentaires.yaml`, qui explique pourquoi ce
mot est refuse. **Aucune occurrence en `bonneReponse` ni en `distracteurs`** sur les onze items du
paquet : les sept items de la tache de regime servent uniquement "herbivore" (3x), "carnivore" (2x),
"omnivore" (2x). Le libelle juste est servi, le mot refuse n'apparait dans aucune reponse.

### `SCI-4` : trois propositions au moins sur tout item a conception fausse

Comptage des `distracteurs` sur les quatre paquets neufs de sciences (`grep -o 'distracteurs:
[[^]]*]' | awk -F',' '{print NF}' | sort | uniq -c`) : **les 44 items des quatre paquets portent
chacun exactement deux distracteurs, donc trois propositions.** Aucun item binaire dans ce lot :
`SCI-4` ne peut structurellement pas etre violee ici, que l'item porte ou non une conception fausse.
Un seul item est explicitement flatte comme portant une conception fausse dans les commentaires
(`cm1-sciences-etats-matiere`, item de la vaporisation, distracteurs "la disparition" et "la
condensation") : il porte bien trois propositions.

---

## 5. Les criteres d'acceptation traduits en tests

**Le point signale par `marc-dev` sur `cp-emc-exclusion`** : son test verifie-t-il bien sa propre
declaration ? **Oui.** La javadoc de `PaquetCpEmcExclusionTest` annonce : *"la tache A porte
l'invariant litteral, et [...] la tache B ne porte jamais une reponse de type 'regler seul' ou 'ne
rien dire' en position de bonne reponse."*

- `emc3LaTacheDeReconnaissanceRepondToujoursEnParlerAUnAdulte` verifie l'egalite stricte a "en parler
  à un adulte" sur les six items de la tache A - tient l'invariant litteral, comme annonce.
- `emc3LaTacheDAdulteNeRepondJamaisParUneSolutionSeul` verifie que la bonne reponse de la tache B
  appartient a l'ensemble ferme des trois adultes reels du paquet (surveillant de la cour, sa
  maîtresse, l'adulte qui surveille la cantine). Verifie sur le contenu (lignes 87, 91, 95, 99, 103) :
  ces trois chaines sont bien les seules valeurs de `bonneReponse` de la tache B, et les distracteurs
  de la tache B ne sont jamais que des adultes du mauvais lieu ou des refus ("à personne, ..."). Une
  appartenance a un ensemble ferme d'adultes reels **exclut mecaniquement** toute reponse de type
  "regler seul" ou "ne rien dire" en position de bonne reponse : le test tient bien ce qu'il declare
  tenir, par une forme fermee plutot que par une liste de motifs bannis, ce qui est une methode
  equivalente et non plus faible.
- **La lecture "categorie d'action" elle-meme reste correctement routee**, non tranchee en silence :
  le commentaire du paquet (lignes 21-27) et la javadoc du test signalent tous deux explicitement
  que cette lecture de l'invariant du backlog est proposee pour verification a l'audit de
  `nadia-prof`, pas imposee comme une resolution definitive.

**Autre ecart trouve en tracant les criteres jusqu'a leur source** : voir section 2, `OBT-R2` -
le test et le contenu sont fideles a la fiche de `nadia-prof`, c'est la restitution du critere dans
le backlog qui durcit "au plus un" en "aucun".

**Non couvert par un test, nomme plutot que suppose** :

- Sur les huit paquets neufs, `CAT-1`, `PLA-1` et les clauses semantiques d'`EMC-1`/`EMC-2`/`EMC-8`/
  `EMC-10` sont **explicitement hors du perimetre** de chaque test de matiere - chaque javadoc le dit
  ("Ce que ce test ne juge pas"). Ce n'est pas un trou de ce sprint : c'est la frontiere assumee
  entre le structurel (teste) et le semantique (`nadia-prof`, en jouant), deja pratiquee depuis s30.
- `FAB-1` (les trois clauses de fabrication) n'est pas testee paquet par paquet : elle est deleguee
  au depot entier par `LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest`, comme chaque
  commentaire de paquet le declare explicitement - verifie coherent avec la section 3 ci-dessus.
- Aucune ligne de `squad/programme/MATRICE-couverture.md` ne porte encore les huit lignes de ce
  sprint ni les huit referentiels neufs (section 6) : le Done pedagogique n'est pas prononce au
  moment de cette mesure, ce qui est attendu et n'est pas confondu avec le Done technique (section
  7).

---

## 6. Declaration des lignes de referentiel, et les huit referentiels vierges

**Les huit paquets neufs declarent tous leur ligne** (`grep "^lignesDeReferentiel:"` sur chacun) :
`EMC-CP-01`, `EMC-CP-05`, `EMC-CP-03`, `EMC-CP-07`, `SCI-CM1-01`, `SCI-CM1-07`, `SCI-CM1-11`,
`SCI-CM1-08` - les huit codes sont confirmes presents dans leurs referentiels respectifs
(`squad/programme/enseignement-moral-et-civique/competences-cp.md`,
`squad/programme/sciences-et-technologie/competences-cm1.md`).

**Les huit referentiels jusque-la vierges ont recu leurs codes, sans etre servis** : verifie
(`grep -cE '^- \[[A-Z]{2,5}-[A-Z0-9]{2,4}-[0-9]{2}\] '` contre `grep -cE '^- '` sur chacun des huit
fichiers) que sept des huit referentiels ont autant de codes que de puces, et qu'aucun paquet servi
ne cite un code `ART-*`, `MUS-*`, `EPS-*`, `HDA-*` ou `LVE-*`
(`grep -rl "ART-CP\|ART-CM1\|MUS-CP\|MUS-CM1\|EPS-CP\|EPS-CM1\|HDA-CM1\|LVE-CP"
adapters/adapter-content/src/main/resources/contenu/*.yaml` : aucune occurrence).

**Le huitieme, `education-musicale/competences-cm1.md`, porte 10 codes pour 12 puces au comptage
naif** - verifie a la main : les deux puces sans code (lignes 70 et 72) sont deux lignes de prose
d'une section sur la reversibilite de la ligne du son, pas des lignes de competence. Aucun code
manquant reel. C'est exactement l'ecart deja route par `camille-po` (`DEC-0081`, section 6, "sixieme
ecart de ce type") comme dette de garde-fou de coherence, non engagee ce sprint : je confirme qu'il
ne s'agit pas d'un defaut nouveau, seulement d'un bilan de referentiel qui compte les puces plutot
que les codes.

**Forme des codes verifiee sans collision** : les nouveaux codes de ligne (`ART-CP-nn`, trois
segments) et les identifiants de condition deja alloues au meme prefixe (`ART-1` a `ART-5`, `EPS-1`
a `EPS-3`, `MUS-1` et `MUS-2`, deux segments) ont des formes distinctes - confirme par lecture directe
des fichiers, aucune des deux familles ne peut matcher le motif de l'autre.

---

## 7. Ce que je ne confonds pas

**Done technique** (ce rapport) : vert, sur la base de la section 1. **Done pedagogique** : non
prononce sur cet increment au moment de cette mesure - aucune fiche de conformite neuve, aucun
verdict d'instance, aucune mise a jour de matrice ne portent encore les huit lignes de ce sprint dans
le depot que j'ai mesure. Les deux jugements sont independants ; je ne laisse aucun des deux se lire
comme une consequence de l'autre.

---

## 8. Fichiers touches par cette review (tous restaures, tree propre a la fin)

Mutations temporaires, chacune restauree par `git checkout --` et verifiee par
`git status --porcelain` vide avant la mesure suivante :

- `bootstrap/src/test/java/com/recreo/archi/LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest.java`
  (section 3, clause 2).
- `adapters/adapter-content/src/main/resources/contenu/cm1-sciences-regimes-alimentaires.yaml`
  (section 3, clause 1).
- `adapters/adapter-content/src/main/resources/contenu/cm1-sciences-etats-matiere.yaml` (section 3,
  registre `PAR-5`).

Aucune autre modification n'est laissee dans l'arbre par cette review.
