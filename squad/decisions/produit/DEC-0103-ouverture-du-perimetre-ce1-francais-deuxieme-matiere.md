# DEC-0103 - Ouverture du perimetre CE1 : francais, deuxieme matiere du niveau, referentiel route a
`nadia-prof`

- **Statut** : Acceptee. Aucune competence CE1 de francais n'est fixee par ce document, et rien n'est
  developpable avant que le referentiel qu'il route n'existe.
- **Date** : 2026-10-01
- **Auteur** : `camille-po`
- **Sprint** : hors-sprint (ouverture de perimetre, avant tout chiffrage de s46 ou d'un sprint
  ulterieur sur cette matiere)
- **Retour qui la fonde** : instruction directe de l'utilisatrice, recue le 2026-10-01, relayee par
  `sofia-sm` : ouvrir une deuxieme matiere au niveau CE1, le CE1 ne portant aujourd'hui que
  mathematiques (`DEC-0089`).
- **Prealable** : `DEC-0089` (ouverture du perimetre CE1, mathematiques seule, modele de routage
  repris a l'identique ici) ; `DEC-0071` (le principe : une competence-programme n'est jamais fixee
  par `camille-po`, elle est routee a `nadia-prof`).
- **Lien** : `squad/programme/mathematiques/competences-ce1.md` (seul referentiel CE1 existant a ce
  jour) ; `squad/programme/MATRICE-couverture.md`, section "Mathematiques - CE1 (cycle 2)" (dix-neuf
  tableaux de competence au total, un seul portant CE1) ; `squad/programme/francais/competences-
  cp.md` et `...-cm1.md` (francais deja ouvert aux deux autres niveaux du produit).
- **Competence programme visee** : transverse - ce document porte sur le perimetre des referentiels
  eux-memes, pas sur le contenu d'une ligne.

---

## 0. Ce que ce document fait, dans l'ordre

1. Constate l'ecart entre ce que la matrice couvre aujourd'hui au CE1 (une seule matiere,
   mathematiques) et la demande (une deuxieme matiere).
2. Choisit la matiere, contre une priorite de programme verifiable, pas contre une preference.
3. Route l'ecriture du referentiel `squad/programme/francais/competences-ce1.md` a `nadia-prof`,
   exactement comme `DEC-0089` l'a fait pour les mathematiques CE1.
4. Annonce d'avance l'effet attendu sur le denominateur atteignable de la matrice.
5. Dit ce que ce document ne tranche pas.

## 1. Le fait, verifie sur le depot tel qu'il est aujourd'hui

`squad/programme/MATRICE-couverture.md` porte dix-neuf tableaux de competence, un seul au niveau
CE1 ("Mathematiques - CE1 (cycle 2)", ouvert par `DEC-0089`). `squad/programme/` contient onze
dossiers de matiere ; un seul porte un fichier `competences-ce1.md`
(`squad/programme/mathematiques/`). Les sept autres matieres de cycle 2 deja ouvertes a un autre
niveau (francais, questionner le monde, langues vivantes, arts plastiques, education musicale, EPS,
EMC) n'existent au CE1 dans aucun fichier et dans aucune section de la matrice. Aucun `DEC` existant
n'ouvre de deuxieme matiere au CE1 : `DEC-index.md` ne cite le CE1 que dans `DEC-0089` et dans les
decisions de perimetre de sprint qui en reprennent le chiffrage (`DEC-0097` et suivantes).

## 2. Le choix de la matiere : francais, pas une autre

**Sept matieres de cycle 2 sont candidates** : francais, questionner le monde, langues vivantes,
arts plastiques, education musicale, EPS, enseignement moral et civique. Le choix ne se fait pas sur
une preference, mais sur une priorite de programme verifiable dans ce depot.

**Francais est la seule de ces sept matieres deja ouverte aux deux autres niveaux du produit**
(`squad/programme/francais/competences-cp.md`, `squad/programme/francais/competences-cm1.md`) : CP
et CM1 portent chacun un referentiel de francais et des lignes dans la matrice depuis respectivement
s0/s1 et s17 (`DEC-0008`). Aucune des six autres matieres candidates ne porte cette double antecedence
- questionner le monde et les quatre matieres d'eveil n'existent qu'a un seul niveau pour l'instant
(CP pour questionner le monde, l'EMC et les langues vivantes ; CP et CM1 separement pour les arts et
l'EPS, sans continuite de referentiel entre eux). Ouvrir francais au CE1 ferme la seule matiere ou
le produit couvrirait alors ses trois niveaux a la fois, exactement comme mathematiques l'a deja
fait avec l'ouverture du CE1 par `DEC-0089`.

**Ce que le programme de l'Education nationale lui-meme place en priorite.** Francais et
mathematiques sont, au cycle 2, les deux seuls enseignements dont l'horaire hebdomadaire officiel
depasse largement celui de toute autre matiere (francais porte le volume horaire le plus eleve de
tout le cycle). Mathematiques a deja ouvert le CE1 (`DEC-0089`) ; francais est donc la matiere dont
le poids horaire dans le programme reel justifie le mieux de suivre immediatement, avant les
matieres d'eveil dont l'horaire est substantiellement moindre. C'est un critere de priorite de
programme, verifiable independamment de ce depot, pas un choix arbitraire parmi sept options
equivalentes.

**Ce que ce choix n'empeche pas** : rien n'interdit a un sprint ulterieur d'ouvrir une troisieme
matiere CE1 (questionner le monde en tete de liste, pour le meme motif de continuite CP/CE1/CM1 une
fois la geographie du reste du cycle 2 mieux connue). Ce document pose le second pas, pas la
sequence complete.

## 3. Ce que je route, et a qui - le referentiel francais CE1

**Je ne fabrique aucune competence CE1 de francais.** Ecrire moi-meme un referentiel reproduirait le
defaut que `DEC-0071` et `DEC-0089` ont deja nomme : poser un arbitrage de contenu programme sans
passer par `nadia-prof` d'abord.

- **Un referentiel de competences francais CE1**
  (`squad/programme/francais/competences-ce1.md`), sur le meme modele que les referentiels existants
  (socle realiste, non exhaustif, non officiel, meme reserve de fiabilite que les deux referentiels
  francais deja ecrits et que `squad/programme/mathematiques/competences-ce1.md`) : route a
  **`nadia-prof`**, a lancer dans un geste distinct de celui-ci.
- **Une ligne par competence, ajoutee a `MATRICE-couverture.md`** une fois ce referentiel ecrit, sous
  une nouvelle section `## Francais - CE1 (cycle 2)` : `camille-po`, des que le referentiel existe -
  jamais avant.
- **Le chiffrage d'un premier lot de francais CE1** reste hors de ce document : comme pour
  mathematiques CE1 a s36, aucune story de developpement n'est engageable avant que le referentiel
  n'existe.

## 4. Impact sur `squad/programme/MATRICE-couverture.md`

Le denominateur de la matrice **augmentera** des que le referentiel francais CE1 existera et que ses
lignes y seront copiees - a `non couvert`, comme toute ligne neuve. **Aujourd'hui, ce document
n'ajoute encore aucune ligne** : aucun referentiel francais CE1 n'existe, et en ecrire un moi-meme
anticiperait le travail de `nadia-prof`.

Suivant la regle deja posee par `DEC-0071` et reprise par `DEC-0089` section 5 : **le prochain recul
du taux affiche, quand le referentiel francais CE1 entrera, sera ecrit d'avance comme un effet de
perimetre - ancien denominateur, nouveau denominateur, ancien taux, nouveau taux - jamais decouvert
et lu comme une regression du produit.** Etat de reference a cette date, pour que la comparaison
future soit exacte : `squad/programme/MATRICE-couverture.md`, etat de sortie de s45 (quatre lignes
fermees le 2026-10-01 - `MAT-CE1-04`, `MUS-CP-05`, `EPS-CM1-09`, `SCI-CM1-14`).

## 5. Ce que ce document ne fait pas

- Il ne fixe aucune competence francais CE1.
- Il n'ouvre aucune autre matiere CE1 que francais : les six autres restent explicitement hors
  perimetre de ce document, candidates pour un sprint ulterieur.
- Il ne cree aucune ligne de matrice : aucune ligne francais CE1 n'existe tant qu'aucun referentiel
  n'existe pour elle.
- Il ne prononce aucun Done, technique ou pedagogique.
- Il n'engage aucun developpeur ni aucune story.

## 6. Ce que ce document demande, et a qui

| Ce qui est du | Proprietaire | Etat |
|---|---|---|
| Referentiel de competences francais CE1 (`squad/programme/francais/competences-ce1.md`) | `nadia-prof` | Route, section 3 - a lancer dans un geste distinct |
| Ligne(s) neuve(s) de `MATRICE-couverture.md` une fois ce referentiel ecrit | `camille-po` | A faire des que le referentiel existe |
| Chiffrage d'un premier lot de francais CE1 | `camille-po` puis `sofia-sm` | Non engageable avant le referentiel |
