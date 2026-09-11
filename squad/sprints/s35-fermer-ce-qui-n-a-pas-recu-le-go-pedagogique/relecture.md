# Relecture - s35 - fermer ce qui n'a pas recu le go pedagogique

- **Auteur** : `relecteur-pedagogique`.
- **Date** : 2026-09-11.
- **Sprint** : s35.
- **Arbre relu** : `s35-integration` a `7787d9e4`, base `round-two` a `d22d24d1`, worktree dedie
  `s35-relecture` (branche `s35-relecture`).
- **Perimetre** : le defaut CI reel signale sur le job `chemins`, la relecture standard de fin de
  sprint des livrables non executables produits ce sprint, et l'audit de langue des six textes
  candidats a etre servis a l'enfant.

## 1. Defaut CI reel - job `chemins` - corrige

`squad/sprints/s35-fermer-ce-qui-n-a-pas-recu-le-go-pedagogique/planning.md`, section 1, portait
trois chemins absolus de poste de travail (worktrees `s35-camille`, `s35-theo`, `s35-sofia`), en
violation de `CLAUDE.md`, section "Conventions de redaction". Corriges sous leur forme portable,
relative au depot :

- `../recreo-s35-camille`
- `../recreo-s35-theo`
- `../recreo-s35-sofia`

**Reverification, methode exacte du job CI** :

```
bash .github/scripts/verifier-chemins.sh
```

sur l'ensemble du depot suivi par git, worktree `s35-relecture` -> **vert** : *"Aucun chemin de
poste de travail ni nom de compte dans les fichiers verifies."*

## 2. Corrections de redaction appliquees

- **`squad/decisions/produit/DEC-0088-...md`**, section 2 : *"Contrediction directe et
  documentee"* -> *"Contradiction directe et documentee"* (faute d'orthographe).
- **`squad/decisions/produit/DEC-0088-...md`**, section 3 : espace manquante dans
  `` `matiere` /`niveau` `` -> `` `matiere` / `niveau` ``, pour la coherence avec la meme
  expression telle qu'elle apparait dans `docs/backlog/backlog.md`, story `S35-CONS-4`.
- **`squad/decisions/adr/ADR-0091-...md`**, section "Consequences", "Mauvaises" : un caractere
  accentue isole (`gardé`) s'etait glisse dans un document par ailleurs integralement ASCII, comme
  le reste des documents `squad/` non destines a l'enfant -> corrige en `garde`.
- **`squad/sprints/.../avis-faisabilite-theo.md`**, section 4.2 : l'extrait de la regle CSS
  `.zone` omettait `solid ...` sur la propriete `border`, alors que la regle reelle
  (`adapters/adapter-web/.../recreo.css`, ligne 518) porte `border: 0.125rem solid
  var(--recreo-accent);` et que le meme extrait, correct, figure dans `ADR-0092`, section 3.
  Corrige pour que la doc decrive ce que le code fait reellement (l'ecart ne changeait aucun des
  calculs qui suivent, la couleur n'entrant dans aucun d'eux).

## 3. Correction de langue sur un texte candidat a l'enfant

**`adapters/adapter-content/src/main/resources/contenu/cp-emc-exclusion.yaml`**, les cinq indices
de la tache B refondus pour `S35-EXC-INDICES` (`EXC-R10`) : trois des cinq (items 7, 9, 10)
laissaient le verbe "voir" sans complement d'objet -*"croit qu'un adulte a deja vu,"* suivi
directement d'une virgule - alors que le distracteur qu'ils commentent porte explicitement l'objet
(*"croire qu'un adulte a deja vu **cela**"*). Phrase grammaticalement incomplete pour un texte lu
par un enfant de CP. Corrige en ajoutant "cela" dans les trois indices, sur le patron du
distracteur qu'ils paraphrasent :

- item 7 : *"Si Tom attend ou croit qu'un adulte a deja vu cela, Hugo continuera..."*
- item 9 : *"Si Hugo garde cela pour lui ou croit qu'un adulte a deja vu cela, Aya continuera..."*
- item 10 : *"Si Malo fait comme si de rien n'etait ou croit qu'un adulte a deja vu cela, Tom
  continuera..."*

Les deux autres indices de la tache B (items 8 et 11) n'ont pas ce defaut : ils emploient *"est
deja au courant"*, une construction qui n'a pas besoin de complement.

**Verifie apres correction** : `bash outils/mvn-jdk21.sh -B -q -pl adapters/adapter-content -am
test -Dtest='PaquetCpEmcExclusionTest,LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest'
-Dsurefire.failIfNoSpecifiedTests=false` (mesure partielle, `-am` porte) -> vert, 7 et 23 tests, 0
echec (rapports Surefire de `adapters/adapter-content`). L'ajout de "cela" ne touche ni les
`enonce`, ni les `bonneReponse`, ni les `distracteurs` : `LON-1`/`LON-2` (qui portent sur les
enonces) et le motif d'exemption chiffre de `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`
ne sont pas concernes.

## 4. Verifications sans defaut trouve, fichier par fichier

- **`docs/backlog/backlog.md`, section "Backlog ouvert pour s35"** : coherence terminologique
  tenue (`S35-*`, `EXC-R*`, `FRI-R*`, `ZON-*`, `ANG-R*` employes a l'identique partout) ; chaque
  citation de verdict porte son fichier et sa section (`verdicts-nadia-instance-A.md`,
  `verdicts-nadia-instance-B.md`) - forme 1 de `DEC-0062` respectee ; aucun enonce recu de MASTER.
- **`DEC-0088`** : la derogation et son calcul (marge de 2, alternatives a 8 et 9 ecartees)
  portent leur methode a cote de chaque chiffre ; l'auteure et la date sont en tete du document -
  aucune forme de `DEC-0062` en defaut au-dela des deux corrections de la section 2.
- **`ADR-0091`, `ADR-0092`** : chaque mesure ("4 zones sur 6", "44 px", "16 a 23 px", "56 x 77 px")
  porte sa methode dans le meme paragraphe et son auteur (`theo-archi`, arbre et date en tete) ;
  les citations de `nadia-prof` sont attribuees a `verdicts-nadia-instance-B.md`, section 4 - forme
  1. Aucune paraphrase de code : les extraits Java cites correspondent aux garde-fous qu'ils
  decrivent.
- **`avis-faisabilite-theo.md`** : au-dela de la correction de la section 2, les chiffres cites
  (comptage `HG-CM1-*`, "70 fichiers", "23 restitution sur 110, soit 20,9 %") sont verifiables par
  la methode donnee dans le meme paragraphe ; la section 9 nomme la commande, son perimetre et le
  `-am` porte, conformement a l'exigence de mesure de `CLAUDE.md`.
- **`planning.md`** : au-dela du defaut de chemins corrige en section 1, aucune autre occurrence
  de chemin de poste de travail ; les six affectations nominatives et la table `PAR-16` sont
  coherentes avec `docs/backlog/backlog.md` et l'avis de `theo-archi`.
- **`proposition-ines-s35-frise-et-plan-de-classe.md`** : la geometrie chiffree des cadres
  `armoire` et `porte` (table section 2.3) a ete verifiee contre `plan-classe.svg` du meme
  repertoire - coordonnees identiques. L'arithmetique des boites de contenu (105,60 - 44 = 61,60 ;
  112,64 - 44 = 68,64) est exacte.
- **`cp-plan-de-la-classe.yaml`, les six libelles `ZON-16`** : *"en haut, au centre"*, *"en haut, a
  gauche"*, *"au centre, a droite"*, *"au centre, a gauche"*, *"en bas, a droite"*, *"en bas, a
  gauche"* - francais correct, integralement accentue, aucune faute d'orthographe ni de
  ponctuation. Aucune des six n'a ete modifiee : correction de langue non necessaire.
- **`cp-frise-de-la-journee.yaml`, les deux textes alternatifs corriges** (*"Frise de la journee
  d'ecole, six moments de gauche a droite."* et *"Frise de la routine du soir, quatre moments de
  gauche a droite."*) et **l'indice corrige de l'item 1** (*"C'est le moment ou l'on se reveille,
  avant tout le reste."*) : francais correct et integralement accentue, aucune faute trouvee.
- **`cm1-anglais.yaml`, les deux items reformules** (le bus a imperiale, la statue de la Liberte) :
  francais correct, integralement accentue, aucune faute trouvee.

## 5. Points a trancher

Aucun. Toutes les corrections trouvees sont des corrections de langue ou de redaction sans
ambiguite, appliquees directement.

## Commits

Prefixes `[REL]`, sur la branche `s35-relecture` (worktree dedie, base `s35-integration` a
`7787d9e4`).
