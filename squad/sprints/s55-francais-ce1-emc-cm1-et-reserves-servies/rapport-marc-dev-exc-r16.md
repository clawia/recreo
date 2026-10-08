# Rapport de marc-dev - EXC-R16 (s55)

Base de mesure : `422f71a0` (round-two). Branche `s55-marc-exc-r16`. `outils/verifier-lignee.sh` : sortie 0.

## Changement

`cp-emc-exclusion.yaml`, item 4 (`un accident isolé`, Tom et Malo) : l'indice devient, mot pour mot,
`Une excuse immédiate après un geste involontaire n'est pas une mise à l'écart.` (forme de la fiche
`FICHE-bandit-manchot-exclusion-cp.md`, bloc de fin). Une ligne modifiée, aucun autre fichier de contenu touché.

## Greps sur le paquet

| Motif | Avant | Apres |
|---|---|---|
| `n'est pas de la moquerie` | 1 | 0 |
| `indice: "Une excuse immédiate après un geste involontaire n'est pas une mise à l'écart."` | 0 | 1 |

## Tests

Aucun test ni inventaire ne portait l'ancien indice : `PaquetCpEmcExclusionTest`,
`TexteAccentueExposeParLePortEntrantTest` et `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` ne
contiennent que d'autres occurrences du mot "moquerie" (titres, enonces, commentaires). Aucune modification de test,
aucune assertion affaiblie. Delta de tests : +0, mesure sur la base `422f71a0` avec ce seul lot.

## Mesures

- JDK : la toolchain jdk 21 a ete resolue par `outils/mvn-jdk21.sh` qui rapporte 21.0.10 (version annoncee par
  l'enveloppe apres interrogation du JDK resolu).
- `bash outils/mvn-jdk21.sh -B clean verify` sur la racine : vert (sortie 0), modules a 120, 18, 402, 1391, 312 tests
  unitaires puis 6 tests d'integration, 0 echec, 0 ignore. Ces chiffres sont ceux d'un arbre base + ce lot, pas un total apres fusion.

## Jobs de `.github/workflows/ci.yml`

- Lance : `build` (equivalent `mvn -B clean verify`, racine).
- Lance localement : `chemins` (les deux scripts `verifier-chemins-test.sh` et `verifier-chemins.sh`), voir ci-dessous.
- Non lances : `prefixes`, `outils`.

## Ecarts

- Aucun test ne portait l'ancien indice, contrairement a l'hypothese du prompt (adaptation conditionnelle).
- Pas de lecture a l'ecran : le jar n'a pas ete lance, l'indice n'est visible qu'apres une erreur sur l'item 4. Le texte
  vient de la fiche, non d'une relecture de l'affichage.

Pas de Done prononce.
