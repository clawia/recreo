# s57 - relecture redactionnelle de `ce1-qlm-etapes-de-developpement` (langue de l'enfant)

- **Auteur** : `relecteur-pedagogique`, 2026-10-26. Commit `[REL]`.
- **Objet** : orthographe, accents, ponctuation et lisibilite CE1 de toutes les phrases lues par l'enfant dans
  `adapters/adapter-content/src/main/resources/contenu/ce1-qlm-etapes-de-developpement.yaml` : 15 enonces, 15 indices, 45
  propositions. Le paquet n'a pas d'explication. Regle : `DEC-0002`, amendement du 2026-08-04 ; `DEC-0056`.
- **Entree** : `lecture-nadia-premier-jet-ce1-qlm-etapes-de-developpement.md`, section 2.2 (indices des items 5, 11, 14).
- **Ce fichier ne modifie ni le YAML ni un test.** Les corrections sont des propositions : `nadia-prof` arbitre, `marc-dev`
  applique. Le sens pedagogique (`nadia-prof`) et la forme des tests (`theo-archi`) ne sont pas touches.

## 1. Phrases fautives (3)

Meme faute, trois fois : *"Que vient (juste) avant ?"* est une construction fautive. Le pronom interrogatif sujet "que" n'existe
pas devant un verbe sans inversion ; la forme correcte est *"Qu'est-ce qui vient (juste) avant ?"*. Les enonces des memes items
disent deja *"quelle etape vient juste avant"* : l'indice reprend la meme idee en question directe.

| Ligne | Item | Texte actuel | Texte propose | Motif |
|---|---|---|---|---|
| 88 | 5 (grenouille, avant le tetard) | `Remonte la vie du têtard. Que vient avant ?` | `Remonte la vie du têtard. Qu'est-ce qui vient avant ?` | "Que vient avant ?" est incorrect ; "Qu'est-ce qui" est la forme sujet |
| 130 | 11 (plante, avant la jeune pousse) | `Remonte la vie de la plante. Que vient juste avant ?` | `Remonte la vie de la plante. Qu'est-ce qui vient juste avant ?` | meme motif |
| 151 | 14 (papillon, avant le papillon) | `Remonte la vie du papillon. Que vient juste avant ?` | `Remonte la vie du papillon. Qu'est-ce qui vient juste avant ?` | meme motif |

Conditions de forme a respecter a l'application :

- l'espace avant `?` reste insecable (`TYP-1`, `ADR-0067`) : le fichier en compte 21, un par phrase interrogative (15 enonces, 6
  indices), tous insecables ;
- apostrophe droite dans `Qu'est-ce` ;
- longueur : la limite declaree en tete de fichier est de douze mots par indice. Les trois phrases proposees ont 9 (ligne 88),
  11 (ligne 130) et 10 mots (ligne 151) ; si le test compte `?` isole comme un mot, 10, 12 et 11. La ligne 130 est a la limite
  haute : a mesurer par le test transverse, pas a la main. Si elle depassait, retirer "juste" (`Qu'est-ce qui vient avant ?`)
  est la correction la plus courte, et la demande de `nadia-prof` l'admet ;
- unicite : les trois indices restent distincts entre eux et des douze autres.

## 2. Autres phrases de l'enfant : aucune faute

| Element | Lignes | Resultat |
|---|---|---|
| 15 enonces | 57, 64, 71, 78, 85, 92, 99, 106, 113, 120, 127, 134, 141, 148, 155 | orthographe, accents (`étape`, `après`, `têtard`, `œuf`), ponctuation et espace insecable corrects |
| 12 autres indices | 60, 67, 74, 81, 95, 102, 109, 116, 123, 137, 144, 158 | corrects ; `Que devient-il ?` (95), `Que devient la jeune pousse ?` (102) et `Que devient-elle ?` (116) sont des constructions correctes (le verbe a un attribut, l'inversion est faite) |
| 45 propositions | 58-59, 65-66, ... | articles et genres corrects (`la jeune grenouille`, `le têtard`, `l'œuf`, `la chrysalide`) ; ligature `œ` partout |

Controles faits : aucun espace simple avant `?` ; aucun tiret cadratin, aucun guillemet typographique, aucun emoji dans le YAML.

## 3. Points signales, a ne pas corriger sans arbitrage

- **Lisibilite, a `nadia-prof`.** *"Remonte la vie du ..."* (lignes 88, 130, 144, 151) : "remonter la vie" est une image. Un
  enfant de CE1 la lit-il comme "regarde ce qui vient avant" ? Je ne la corrige pas : c'est le vocabulaire et le niveau, donc
  sa porte. Les enonces sont d'une phrase de 13 a 15 mots, toujours construits de la meme facon : rien a redire.
- **Lisibilite, a `nadia-prof`.** Lignes 60 et 67 : *"Suis la vie du ... pas à pas, vers la suite."* n'indique pas quelle etape
  chercher. Question de methode d'indice, pas de langue.
- **Coherence terminologique du depot.** Le commentaire en tete de fichier (lignes 11 a 52) est ecrit sans accents, comme le
  reste des commentaires de YAML du depot : convention du depot, pas une faute. Aucun terme n'y change de nom d'une ligne a
  l'autre (*tache voisine*, *tache ordre*, *famille a, b, c*).

## 4. Routage

| A qui | Quoi |
|---|---|
| `nadia-prof` | arbitrer les trois corrections du tableau 1 et les deux points de lisibilite du 3 |
| `marc-dev` | appliquer, apres arbitrage, les lignes 88, 130, 151 ; lancer le test transverse de longueur des indices |
| `theo-archi` | rien |
