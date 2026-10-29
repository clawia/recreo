# Rapport de marc-dev - palier 5 de s57, correction de l'accord de `ce1-qlm-generations` (QLM-CE1-12)

Date : 2026-10-29. Story : `S57-QLM-CE1-12-ACCORD`, critere 3. Base : `round-two` a `3b32a74c`. Commit de
correction : `f7f9f443` (`[MARC]`). Aucune entree de catalogue n'est ecrite.

## 1. Ce qui a change

- `ce1-qlm-generations.yaml` : les enonces des items 3 et 8 deviennent les textes du releve, section 3.
  Item 11, propositions, bonnes reponses et indices inchanges. Commentaire d'en-tete, `L3` et `L4` de
  l'avis de `theo-archi` (lignes 25 et 53), seules ces citations.
- `PaquetCe1QlmGenerationsTest` : table fermee `SENS_DU_GROUPE` (`gen` -> *de la plus agee a la plus jeune*,
  `pre` -> *du plus age au plus jeune*, `evt` -> *du premier au dernier*), lue par `contains` sur le texte
  mis a plat ; premiere lecture "un des trois sens" et seconde lecture "le sens du groupe" ; Javadoc
  (`L8`), message et fragment de la suite des generations (`L7`), fragment de la sonde 17 (`L6`) ; deux
  appels `refuse` pour les sondes 74 et 75 dans la methode existante de la sonde 17.
- `generations-fautives.yaml` : sondes 74 (items 3 et 8 avant correction, groupe `gen`, fragment
  `pas celui de son groupe (gen)`) et 75 (prenoms au feminin, fragment `pas celui de son groupe (pre)`) ;
  13 enonces `gen` passes au feminin ; en-tete du jeu complete. Le jeu reste execute
  (`assertThatThrownBy`, `ADR-0013`).

`git diff --stat` du commit `f7f9f443` :

```
 .../resources/contenu/ce1-qlm-generations.yaml     | 10 +++---
 .../content/PaquetCe1QlmGenerationsTest.java       | 29 ++++++++++-------
 .../contenu/generations-fautives.yaml              | 38 ++++++++++++++--------
 3 files changed, 47 insertions(+), 30 deletions(-)
```

## 2. Egalite a l'octet avec le releve

Les deux enonces du paquet ont ete compares, par script, au texte extrait de la section 3 du releve :
egalite vraie pour les items 3 et 8 (ligne `  - enonce: "..."` retrouvee a l'identique dans le paquet).

**Ecart a connaitre** : dans le fichier du releve, l'espace avant `?` est une espace ordinaire (`U+0020`),
alors que le releve annonce `U+00A0` et que le paquet porte `U+00A0`. J'ai ecrit `U+00A0` (octets
`c2 a0`), comme dans les dix autres enonces du paquet et comme la consigne de la demande. Hors cette
espace, les caracteres sont identiques. `nadia-prof` compare donc a l'octet apres cette normalisation.

## 3. Mesures

JDK : "Maven lance sur le JDK de la toolchain jdk 21 : 21.0.10", ligne imprimee par
`outils/mvn-jdk21.sh`. Base de mesure : `3b32a74c` plus ce seul lot.

| # | Perimetre | Resultat |
|---|---|---|
| 1 | classe `PaquetCe1QlmGenerationsTest`, `-pl adapters/adapter-content -am` | 40 tests, 0 echec (delta : +0 test, les sondes 74 et 75 sont dans une methode existante) |
| 2 | module `adapter-content` entier, `-am` | 2569 tests, 0 echec, `BUILD SUCCESS` (delta : +0) |
| 3 | `bootstrap`, `-am`, deux classes : `AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest` (5) et `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` (10) | 15 tests, 0 echec (aucun identifiant alloue) |
| 4 | `verifier-chemins.sh` en forme CI (`git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' \| bash .github/scripts/verifier-chemins.sh`) | aucun chemin de poste ni nom de compte |
| 5 | `verifier-prefixes.sh` sur le sujet du commit | prefixe connu |

Les deux sondes rougissent pour la raison visee : leur appel `refuse` porte le fragment nommant le groupe
(`gen` pour 74, `pre` pour 75), et la classe est verte.

## 4. Ce que je n'ai pas lance

`mvn -B verify` complet, les jobs de CI autres que ceux du tableau 3 (`chemins` mesure ci-dessus,
`prefixes` sur le seul sujet du commit, pas la plage), `bootstrap` entier, le jar. Le paquet n'est pas au
catalogue : rien n'est visible a l'ecran, aucune lecture du jar n'etait due pour ce lot.

Duree brute : 343 s entre la lecture de la specification (`date +%s` a 1793263107) et la fin de la mesure du
module `adapter-content` (1793263450), edition et premiere mesure de classe comprises ; reacteur du module :
28,8 s. Les deux classes de `bootstrap` et le commit : non chronometres.

## 5. Proposition de titre pour l'entree de catalogue (a lire par `nadia-prof` avant l'entree)

Proposition : **"Les générations d'une famille"**. Consigne inchangee : "Lis bien, puis choisis la bonne
réponse.", 10 manches.

- Distinct des neuf titres pris (`catalogue.yaml`, lus) et de celui de `QLM-CE1-01`
  (*"Grandir chez les animaux et les plantes"*).
- Quatre mots courts, lisibles par un CE1.
- Limite : il ne nomme pas les moments d'une vie (naissance, ecole, premier emploi), que le paquet aussi
  porte. Si `nadia-prof` veut les nommer, l'alternative est **"La famille et les âges de la vie"**.
  Le choix lui appartient.

Aucune entree n'est ecrite.

## Questions

Aucune decision a ma charge. Point d'information : l'espace avant `?` du releve (section 2).
