# Review du sprint 16 - deux defauts de l'utilisatrice et dettes de l'en-tete

- **Horodatage de debut** : 2026-08-19 10:11 +0200 (`planning.md`)
- **Horodatage de fin** : 2026-08-19 15:20 +0200
- **Duree effective** : 5 h 09
- **Increment audite** : `round-two`, tete `750ef9d`.

## Fusion des deux derniers lots - moi, casquette Scrum Master

Deux lots restaient a fusionner a l'ouverture de cette cloture.

| Lot | Branche | Tete | Lignee verifiee | Fichiers touches |
|---|---|---|---|---|
| `nadia-prof` - table de correspondance et quatre constats | `s16-nadia` | `179940f` | `git merge-base --is-ancestor round-two HEAD` : OK | fiches de conformite et releves uniquement |
| `camille-po` - story `S17-1` et correction de `DEC-0037` | `s16-camille-cloture` | `d8855cc` | idem : OK | `docs/backlog/backlog.md`, `DEC-0037`, `DEC-index.md` |

Aucun chevauchement de fichier entre les deux lots (verifie par `git diff --stat` de chacun contre
la base commune `b0c98fd`). Fusionnes par `git merge --no-ff`, dans cet ordre :

```
4208769 [SOF] s16: fusion lot nadia-prof - table de correspondance et quatre constats (S16-1/S16-2 VALIDE, S16-6 reserve S15-D fermee, S16-3+S16-4 VALIDE SOUS RESERVE, reserve S16-A)
750ef9d [SOF] s16: fusion lot camille-po - story S17-1 sur la reserve S16-A et correction du chiffre de DEC-0037
```

**Aucun conflit sur les deux fusions, aucun push, aucun worktree supprime.**

## Porte technique - rejouee par moi sur l'arbre fusionne final, jamais recopiee

```
bash outils/mvn-jdk21.sh -B clean verify
```

**BUILD SUCCESS**, six modules en SUCCESS (domain, application, adapter-web, adapter-content,
bootstrap unit + bootstrap failsafe). Aucune instance de l'application ne tournait avant cette
mesure.

**Chiffre de tests, par mes deux methodes, sur l'arbre fusionne (`750ef9d`), apres un `clean`** :

| Methode | Resultat |
|---|---:|
| (1) Somme des blocs `[INFO] Results:` du reacteur, sortie complete de `clean verify` | 48 + 12 + 269 + 123 + 87 + 6 = **545** |
| (2) Somme de l'attribut `tests="..."` des 97 rapports `TEST-*.xml` sous `*/target/{surefire,failsafe}-reports/` | **545** |

Les deux methodes concordent, sans mutation active. Ce chiffre confirme celui deja porte par
`yanis-qa` dans `rapport-qualite.md` sur le meme arbre.

**Regles ArchUnit, par mes deux methodes** :

- Comptage `@ArchTest` (annotation) : 28 occurrences dans 11 fichiers de test compiles par le
  reacteur.
- Comptage `git ls-files '*.java' | grep 'src/test/java'` filtre sur le motif litteral
  `ArchRule <nom> =` : **31** occurrences brutes dans 13 fichiers, dont **2** vivent dans
  `RegleContreExemple.java` (le faux module de contre-exemple de
  `LesGardeFousSontMisALEpreuveTest`, un chemin qui contient `src/test/java` sans etre un module
  source compile par le pom agregateur). **31 - 2 = 29.**

Les deux methodes different par construction (une regle executee par `.check()` explicite plutot
que par le mecanisme `@ArchTest` n'apparait que dans la seconde), exactement le piege nomme par
`squad/README.md` section 4. **29 regles ArchUnit**, methode identique aux sprints precedents,
confirme le chiffre deja porte par `yanis-qa`.

**Garde-fou de chemins**, auto-test puis rejeu sur l'arbre fusionne :

```
bash .github/scripts/verifier-chemins-test.sh
-> tous les cas passent, EXIT=0

git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' | bash .github/scripts/verifier-chemins.sh
-> "Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies.", EXIT=0
```

Rejoue une seconde fois apres mes quatre amendements a `squad/README.md` (voir `retro.md`) : meme
resultat.

## L'increment en une phrase

**Le message de partie interrompue ne s'affiche plus que pour une interruption reelle, le retour
au catalogue complet affiche toujours la bonne adresse, la puce de niveau active et la proposition
deja essayee gardent leur apparence sous le pointeur et au clavier, et la phrase de filtre survit
desormais a un changement de niveau confirme en cours de partie.**

## Rapport qualite de `yanis-qa` et confirmation ArchUnit de `theo-archi`

`rapport-qualite.md` (fusionne dans les lots `yanis-qa`) couvre : le Done technique (545 tests, 29
regles, methode citee dans chaque paragraphe), trois preuves de morsure sur `ADR-0046`
(`AucunEtatDePressableNEstAbandonneAuTiersTest`), la preuve de morsure du garde-fou de provenance
`S16-7` (rouge sans `-am`, vert avec, vert sur `clean verify` complet), et l'angle mort etabli par
mutation adversariale de ce meme garde-fou (une provenance juste peut coexister avec un `target/`
perime que la compilation incrementale de Maven croit a jour).

**Aucune confirmation ArchUnit dediee de `theo-archi` n'a ete ecrite pour ce sprint precis** (pas
de `confirmation-archunit.md` dans `squad/sprints/s16-deux-defauts-et-dettes-de-len-tete/`,
verifie sur le disque). Je le nomme plutot que de le fabriquer, dans le meme esprit que la review
de s15 : `theo-archi` a confirme le chiffre de **s15** (528 tests, 29 regles) au tout debut de son
avis de faisabilite de s16, et a produit deux amendements motives et mesures d'`ADR-0046`
(`899bb48`), mais aucun des deux ne constitue une confirmation ArchUnit du sprint 16 lui-meme. Le
chiffre de 29 regles pour s16 repose sur ma propre mesure ci-dessus et sur celle, concordante, de
`yanis-qa` - pas sur un document de `theo-archi` dedie a s16.

## Corrections du `relecteur-pedagogique`

`relecture-du-retard-de-s15.md` (`bbff292`) : **zero correction directe** - orthographe,
ponctuation, tiret cadratin, guillemet typographique, emoji, chemin de poste, titre casse, section
vide, bloc duplique : aucun cas trouve. Le relecteur le nomme lui-meme comme le sprint le plus
propre redactionnellement qu'il ait relu. **Quatre points signales, tous traites depuis** :

1. Le chiffre du pli des grands nombres CM1 (`R-BARRE-1`) cite "+22 px" apres le lot qui l'a ramene
   a "+14 px", dans trois documents de ma main et un de `camille-po`. **Corrige** : par moi
   (`73ae3b0`, note datee dans `RAPPORT.md`, `planning.md` et `review.md` de s15) et par
   `camille-po` (`ca3e7b5`, note datee dans `DEC-0037`).
2. Un commentaire d'`accueil.html` decrivait un masquage retire depuis l'arbitrage C de s15. **Corrige**
   par `theo-archi` (`899bb48`).
3. Une affirmation de son propre `avis-faisabilite.md` de s16 ("aucun critere n'epingle un effectif
   de catalogue") contredite par le texte de `backlog.md`. **Corrige** par `theo-archi` (`899bb48`,
   note datee dans `avis-faisabilite.md`), qui en tire lui-meme la lecon methodologique reprise en
   amendement dans `retro.md`.
4. `ADR-index.md`, section "Lecture rapide", ne couvrait pas `ADR-0046`, `ADR-0047` ni `ADR-0048`.
   **Corrige** par `theo-archi` (`899bb48`).

Un cinquieme point, de lisibilite et non de correction (la nomenclature des conditions
pedagogiques, quatre conventions non reliees) : traite au fond par la table de correspondance de
`nadia-prof` (voir plus bas) et par un amendement de process, decide en `retro.md`.

## Avis de `nadia-prof` et Done pedagogique

Quatre constats, tous fusionnes (`3966764`, `179940f`), sur `FICHE-bandit-manchot-additions-cp.md`
et `FICHE-barre-entree-niveau.md`.

**S16-1 - VALIDE.** Le message de partie interrompue ne s'affiche plus apres une pause ordinaire
et s'affiche toujours apres une interruption reelle - verifie par `nadia-prof` en redemarrant elle-
meme le serveur en cours de partie, texte identique a celui deja valide, duree de vie de **1 800 s**
lue sur le cookie envoye par le serveur.

**S16-2 - VALIDE.** L'adresse est poussee a `/` par la seule branche qui rend le catalogue complet,
jamais sur la branche de confirmation, mesure sur l'en-tete de reponse des deux branches ; aucune
condition deja tenue n'est degradee (les neuf `BARRE` et les neuf `S15` remesurees).

**S16-6 - VALIDE, et la reserve S15-D est FERMEE.** La phrase de filtre apparait desormais aussi
apres un changement de niveau confirme en cours de partie, sur la route ou elle manquait.

**S16-3 + S16-4 - VALIDE SOUS RESERVE.** `S15-8` (couleur de position preservee) et `S15-9` (trait
de M9) sont tenues aux quatre positions cycliques, sur deux jeux et sept items - y compris a l'etat
de focus clavier, jamais atteint avant ce constat. **Une reserve neuve, S16-A** : sur les sept
pressables d'une manche, aucun indicateur de focus clavier n'est visible (contour retire a 3 px,
ombre de remplacement invalide) - "un enfant qui navigue au clavier ne voit nulle part ou il est".

**Retrait d'une limite declaree cinq fois, par `nadia-prof` elle-meme.** Depuis cinq sprints elle
declarait ne pouvoir mesurer aucun etat interactif, cause : l'outil de mesure du depot pilote un
navigateur en mode capture, rien n'y conduit un pointeur. En s16 elle distingue enfin le survol
(reste hors de portee, cause inchangee) du **focus clavier**, qui est un etat que le document peut
prendre lui-meme : elle ecrit une sonde, mesure, et leve la limite pour cet etat seul. Cette mesure
neuve a immediatement rendu verifiable le defaut que `S16-3`/`S16-4` corrige (jusque-la seulement
declaratif) et en a trouve un troisieme, `S16-A`, jusque-la invisible faute d'avoir jamais ete
mesure.

**Le constat coute a theo-archi une contradiction, qu'il tranche contre lui-meme.** La phrase du
point 5 de son amendement `ADR-0046` ("l'anneau de focus clavier tiers reste intact") est fausse
sur ce produit : `nadia-prof` a mesure que `.btn:focus-visible` de la feuille vendorisee ecrase
aussi les trois couleurs, pas seulement une ombre portee. `theo-archi` etablit que son propre
`ADR` avait exclu un etat entier la ou il fallait exclure une seule propriete de cet etat
(`box-shadow`), corrige dans le meme commit (`899bb48`) : `:focus-visible` entre desormais dans les
etats gardes, seul `box-shadow` en sort a cet etat.

**Done pedagogique** : `nadia-prof` prononce VALIDE sur `S16-1`, `S16-2` et `S16-6`, VALIDE SOUS
RESERVE sur `S16-3`/`S16-4`. `S16-5` et `S16-7` ne rendent aucun compte pedagogique different pour
l'enfant et n'appelaient aucun constat de sa part, conformement a `DEC-0037`.

## Verification physique de chaque porte de sortie des sept stories (amendement s13)

| Story | Porte de sortie ecrite au planning | Etat verifie sur le disque |
|---|---|---|
| S16-1 | Criteres 1, 3, 4 et le critere 2 reecrit tenus, `mvn verify` vert, puis constat ecrit de `nadia-prof` sur `FICHE-bandit-manchot-additions-cp.md` | **Franchie.** Code fusionne (`6e9a706`), 545 tests verts, constat VALIDE ecrit et date |
| S16-2 | Criteres 2, 3, 4 et le critere 1 reecrit tenus, `mvn verify` vert, puis constat de `nadia-prof` qu'aucune condition deja tenue n'est degradee | **Franchie.** Meme commit, constat VALIDE ecrit, neuf `BARRE` et neuf `S15` remesures |
| S16-3 | Critere testable tenu, verifie par le garde-fou derive d'`ADR-0046` amende sur mutation reelle, `mvn verify` vert, puis constat de `nadia-prof` | **Franchie, apres avoir ete explicitement tenue partiellement ouverte.** `theo-archi` avait ecrit lui-meme, dans `ADR-index.md` (entree du 2026-08-19, second amendement) : "la porte de sortie de `S16-3` reste partiellement ouverte sur ce seul point" (la regle d'epinglage restait a reformuler). `lea-dev` ferme ce point exact dans son commit suivant, intitule lui-meme "S16-3 porte de sortie" (`4fc6569`) : `box-shadow` exclue a l'etat `:focus-visible` par la regle corrigee, test reformule et jamais supprime. **Fermee avec ce lot**, confirmee par le constat VALIDE SOUS RESERVE de `nadia-prof` sur les sept captures |
| S16-4 | Constat de `nadia-prof` sur les quatre positions et plusieurs items, degrade a s15 a une position et un item | **Franchie.** `nadia-prof` atteint les quatre positions sur deux jeux et sept items, la porte qu'elle avait elle-meme posee a la cloture de s15 |
| S16-5 | Les deux criteres tenus, `mvn verify` vert. Aucun constat de `nadia-prof` attendu par construction | **Franchie.** `f6b2474`, garde-fou d'accentuation etendu, 545 tests verts, `nadia-prof` n'a pas demande a juger |
| S16-6 | Les deux criteres (le second sous sa forme renforcee) tenus, `mvn verify` vert, puis constat ecrit de `nadia-prof` | **Franchie.** `34cb956`, constat VALIDE, reserve S15-D fermee |
| S16-7 | Le garde-fou existe, vert sur `clean verify` (reacteur complet), rouge sur une invocation partielle sans `-am` - la preuve de morsure est cette commande elle-meme | **Franchie.** `8c944ae`, preuve de morsure ecrite avec restauration integrale du depot local Maven, confirmee une seconde fois par `yanis-qa` (`rapport-qualite.md`, section 3) |

**Aucune des sept portes de sortie ecrites au planning n'est restee ouverte a cette review.**

### Un engagement ecrit au planning, non tenu, trouve en verifiant au-dela des sept stories

`theo-archi` avait ecrit, dans son propre avis de faisabilite (section 6.3), un engagement daté et
sans ambiguite : **"si le declencheur n'a pas joue a la cloture de s16, `ADR-0042` doit changer de
statut"** - de `Accepte` a une forme qui dit que sa regle n'a toujours pas d'objet. Repris par
`sofia-sm` dans `planning.md` comme decision de cloture retenue (decision 3).

**Le declencheur n'a pas joue** : aucun gabarit modifie en s16 n'insere de `hx-post` vers
`/parties/{jeuId}` sur un ecran rendu en partie en cours (verifie : seul `accueil.html` a change,
pour un commentaire, `899bb48`). **Le fichier `ADR-0042` porte toujours `Statut : Accepte`** à la
date de cette review - verifie sur le disque, aucun commit ne l'a touche depuis le 2026-08-17
(s14). L'engagement ecrit par `theo-archi` n'est donc pas tenu physiquement, malgre son intention
ecrite et datee. Je ne l'ecris pas a sa place : c'est une decision d'architecture, pas une
ceremonie de process. **Obstacle ouvert, route a `theo-archi`**, voir tableau ci-dessous.

## Tableau des obstacles du sprint

| Description | Categorie | Destinataire | Etat a cette review |
|---|---|---|---|
| `S16-1`/`S16-2` sur le meme fichier probable | technique / methode | sofia-sm | **Clos** - sequencement retenu au planning, aucun conflit constate |
| Rapport qualite de s15 perime | technique / methode | sofia-sm (decide), yanis-qa (duty reportee) | **Clos** - duty de mutation-morsure executee sur `ADR-0046` (`ebe36dc`, `ff0b6b7`) |
| Relecture de s15 encore due | redaction | relecteur-pedagogique | **Clos** - `bbff292`, quatre points signales, tous traites (voir plus haut) |
| Chiffre R-BARRE-1 errone (+22 px au lieu de +14) dans trois documents de ma main et un de `camille-po` | redaction / produit | sofia-sm, camille-po | **Clos** - `73ae3b0`, `ca3e7b5`, notes datees, pas de reecriture silencieuse |
| Commentaire perime dans `accueil.html`, affirmation contredite dans `avis-faisabilite.md`, `ADR-index.md` incomplet | redaction | theo-archi | **Clos** - `899bb48` |
| Nomenclature des conditions pedagogiques (quatre conventions non reliees) | redaction / process | nadia-prof (table), sofia-sm, theo-archi (garde-fou) | **Table livree** (`FICHE-barre-entree-niveau.md`). Amendement de process decide en `retro.md` ; garde-fou candidat de s17, non ecrit ce sprint |
| Contradiction entre `ADR-0046` et l'avis de faisabilite de s16 sur `:focus-visible` | technique / architecture | theo-archi | **Clos** - tranche par `theo-archi` contre lui-meme, `899bb48` |
| `S16-3` partiellement ouverte (regle d'epinglage a reformuler) | technique | lea-dev | **Clos** - `4fc6569` |
| `ADR-0042` : engagement ecrit de changer de statut si le declencheur n'a pas joue a la cloture de s16 | architecture | theo-archi | **Ouvert** - declencheur non joue, fichier non modifie, verifie sur le disque a cette review |
| `dureeInactiviteMaximale()` protegee seulement par la divergence fortuite de deux fixtures (1800 vs 900) | technique / test | theo-archi (conception du garde-fou), camille-po (priorite) | **Ouvert** - signale par `yanis-qa`, non traite ce sprint, amendement de process ecrit en `retro.md` |
| Garde-fou de provenance `S16-7` : verifie un emplacement, jamais une fraicheur (mutation adversariale, target perime) | technique / architecture | theo-archi | **Ouvert** - non bloquant, `yanis-qa` le route explicitement ; fermer ce trou est un choix d'architecture |
| Javadoc du trait de M9 formulee comme une mesure figee alors qu'elle est reverifiee a chaque execution | redaction / technique | sofia-sm | **Ouvert** - non bloquant, reformulation cosmetique candidate a un futur lot |
| Migration du marqueur `ecran-de-jeu` vers un attribut | technique | theo-archi (mecanisme), sofia-sm (capacite) | **Ouvert** - candidate s17, jamais devant les stories qui corrigent ce qu'un enfant voit |
| Question d'architecture de `DEC-0032` point 2 (nomenclature de matiere accentuee) | produit + technique | theo-archi | **Ouvert** - inchange depuis le 2026-08-17, second motif de blocage du filtre par matiere |
| Aucune proposition de forme pour un second axe de filtre combine (R-BARRE-3/S14-B) | design | ines-design | **Ouvert** - inchange |
| Dependance a une seule personne pour toute mesure de rendu | technique / methode | sofia-sm (capacite), camille-po | **Ouvert** - inchange, nomme de nouveau dans `DEC-0037`, aucune decision de capacite prise |
| Couplage entre `S15-8` (focus clavier) et la composition du catalogue ("Le bon verbe" seul a servir quatre positions) | pedagogie / produit | camille-po | **Ferme par une note ecrite**, pas par un traitement : `DEC-0037` pose un declencheur explicite pour le jour ou un retrait de contenu serait propose, sans ouvrir de story aujourd'hui |
| Story `S17-1` (indicateur de focus clavier visible, reserve S16-A) | produit | camille-po | **Ecrite** dans `docs/backlog/backlog.md`, arbitrage prealable de forme requis avant tout code |
| R-BARRE-4 / reserve F11 (ecoute reelle au lecteur d'ecran) | pedagogie / accessibilite | hors de la portee de la squad | **Ouvert**, inchange depuis s13 |
| `estRemplacant` d'`ADR-0042` (troisieme report) | technique | theo-archi | Voir ligne `ADR-0042` ci-dessus - traite comme un seul obstacle |

## Couverture programme

**Troisieme sprint consecutif sans mouvement de `squad/programme/MATRICE-couverture.md`** : 23
lignes `couvert-valide`, 5 `en cours`, 46 `non couvert`, sur 74 lignes de competence au total,
inchange depuis s13 (`DEC-0037`, section 4). Aucune des sept stories de ce sprint ne cible une
competence. `camille-po` pose une limite explicite dans `DEC-0037`, reprise par `sofia-sm` au
planning comme un engagement de cloture : **si s17 ne rouvre aucune ligne de la matrice, ce sera a
`sofia-sm` et `camille-po` de l'ecrire comme un changement de direction du produit, pas comme une
continuation silencieuse.** Cet engagement est reporte tel quel a l'ouverture de s17.

## Decisions prises

1. **Le Done technique de l'increment fusionne (`750ef9d`) est atteste par ma propre execution de
   `mvn -B clean verify`**, avec deux methodes de comptage concordantes pour les tests (545) et
   pour les regles ArchUnit (29), sur l'arbre fusionne complet, apres un `clean`.
2. **Aucune confirmation ArchUnit dediee de `theo-archi` n'existe pour s16** : je le nomme plutot
   que de la fabriquer ou de recycler celle de s15 comme si elle couvrait s16.
3. **Les quatre constats de `nadia-prof` sont restitues sans reformulation qui en attenuerait la
   portee.** Aucun Done pedagogique n'est prononce par moi ; `S16-1`, `S16-2`, `S16-6` sont VALIDE,
   `S16-3`+`S16-4` VALIDE SOUS RESERVE (reserve neuve `S16-A`).
4. **Les sept portes de sortie du planning sont verifiees physiquement, une a une, et toutes sont
   franchies** - y compris `S16-3`, tenue explicitement partiellement ouverte par `theo-archi`
   jusqu'au lot suivant de `lea-dev`.
5. **Un engagement ecrit de `theo-archi` (changement de statut d'`ADR-0042` si son declencheur n'a
   pas joue) n'est pas tenu physiquement a cette review** : `ADR-0042` porte toujours `Accepte`.
   Obstacle ouvert, route a `theo-archi`, non tranche par moi.
6. **Aucune ligne de `MATRICE-couverture.md` n'est mise a jour**, decision gelee de `camille-po`,
   avec l'engagement de cloture reporte a s17 tel quel.
7. **Aucun worktree n'est supprime, aucun push n'est effectue dans ce geste.**
