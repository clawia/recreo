# Rapport de marc-dev - s55, postes L1 et L2 (branchement de deux paquets de francais CE1)

Branche `s55-marc-branchement`, issue de `round-two` au commit de base `2d0d10e7`. Rien n'est pousse, rebase ni fusionne.

## 1. Livre

| Poste | Commit | Paquet | Corrections de nadia-prof posees dans le meme commit |
|---|---|---|---|
| `S55-BR-FRA-CE1-L1` | `c9c80937` | `ce1-accord-sujet-verbe` (`FRA-CE1-14`) | `K1` : 17 indices (12 de substitution aux items 1, 2, 4, 7, 10, 13, 16, 19, 20, 22, 23, 24 ; 5 de relecture aux items 6, 9, 12, 18, 21) et les deux lignes d'en-tete. `K2` : items 6 et 18, "elle" devient "il". `K3` : consigne de l'entree |
| `S55-BR-FRA-CE1-L2` | `2aa1e03a` | `ce1-classes-de-mots` (`FRA-CE1-12`) | `K4` : item 17 "Fatou", item 20 "Lina". `K5` : consigne de l'entree |

Les textes des indices, des deux lignes d'en-tete, des quatre enonces et des deux consignes sont extraits de l'arbitrage par script puis relus au diff : mot pour mot, espaces insecables comprises.

Par poste : une entree de `catalogue.yaml` (juste avant `bandit-manchot-synonymes-contraires-ce1`), les chaines de l'inventaire de `TexteAccentueExposeParLePortEntrantTest` (3 pour L1, la consigne etant deja inventoriee ; 4 pour L2), un test de paquet et ses contre-exemples (`ADR-0013` obligation 3).

Titres : `Un ou plusieurs ?` et `Quelle sorte de mot ?`, les propositions de l'arbitrage (titre libre), avec une espace insecable avant le point d'interrogation. La table de `AucunSigneDoubleNEstDetachableDeSonMotTest` n'est pas touchee et le cliquet reste vert (piege `T3`).

## 2. Mesures

Mesure faite sur `2d0d10e7` plus mes seuls lots. Ce ne sont pas des totaux apres fusion.

- Delta : L1 **+15 tests** (`PaquetCe1AccordSujetVerbeTest`), L2 **+13 tests** (`PaquetCe1ClassesDeMotsTest`). Aucun test ajoute dans `bootstrap` (l'inventaire s'etend, il ne compte pas un test de plus).
- Version : JDK 21.0.10, rapportee par `outils/mvn-jdk21.sh` (`bash outils/mvn-jdk21.sh -B clean verify`, reacteur complet, donc sans `-pl`).
- `mvn verify` : vert apres chaque poste (BUILD SUCCESS). `adapter-content` : 1375 tests apres L1, 1388 apres L2 ; `bootstrap` 312 dans les deux cas.
- Grep rouges du planning, au vert :
  - L1, sur `ce1-accord-sujet-verbe.yaml` : `indice: "Remplace le sujet par` 12 ; `indice: "Lis le sujet avec chaque verbe` 5 ; `compte-les` 1 (l'en-tete) ; `^ *indice:` 24 ; `il ___ au march` 1 ; `il ___ un g.teau` 1 ; `satisfait par la forme d.une autre personne` 1. Sur `catalogue.yaml`, `Lis bien, puis choisis la bonne forme du verbe` : 3.
  - L2, sur `ce1-classes-de-mots.yaml` : `Fatou` 1 ; `Lina` 1 ; `Tom.*range` 0 ; `Karim aime` 0. Sur `catalogue.yaml`, `dis quelle sorte de mot est entre guillemets` : 1.
- Jobs de `.github/workflows/ci.yml` lances, par poste :
  - `build` : `mvn verify` ci-dessus.
  - `prefixes` : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, et `verifier-prefixes.sh` avec entree explicite (`git log --no-merges --format=%s 2d0d10e7..HEAD`). Vert.
  - `chemins` : `verifier-chemins-test.sh` et `verifier-chemins.sh` sur `git ls-files`. Vert, deux fois.
  - `outils` : balayage des trois motifs de la CI, aucun rouge, une fois par poste. Un deuxieme balayage sous charge machine a depasse mon delai de 100 s sur trois juges (`mesurer-rendu-test.sh`, `mvn-jdk21-test.sh`, `balayage-largeur-rangee-test.sh`) : delai depasse, pas un verdict rouge.
- Jeu joue : le jar construit au commit `2aa1e03a` a ete lance, les deux jeux sont proposes au niveau CE1 et l'ecran de la premiere manche affiche la bonne consigne, l'enonce sans la reponse et trois propositions (`faire : la boulangere ___ du pain`, `Tom mange un gateau`). Ce jar a tourne sur un JDK 25, pas 21 : lecture de rendu seulement.
- `git diff round-two --stat` : `round-two` a avance depuis `2d0d10e7` (maintenant `c315c024`) et le diff contre sa pointe montre 3 fichiers de plus qui ne sont pas les miens. Contre la base : L1 6 fichiers, L2 6 fichiers, aucun hors perimetre.

## 3. `TRV-CE1-5` et `EMC-9`

- `EMC-9` : sans objet, aucun paquet d'EMC.
- `TRV-CE1-5`, regle de s54 (aucun indice ne donne un critere qu'un distracteur satisfait) : rendue executable par `k1LeNombreSeulNEstUnCritereQueSurLesItemsOuIlDepartage`. Un indice qui contient "plusieurs", "combien" ou "compte" n'est admis que si les deux distracteurs ont l'autre nombre que la bonne reponse. Les 7 items non modifies (3, 5, 8, 11, 14, 15, 17) gardent leur indice de nombre et passent ce test. Le critere mecanique est le mien, a relire par nadia-prof.

## 4. Ecarts

- **E1, a trancher par nadia-prof.** Item 6 de `ce1-accord-sujet-verbe` : la bonne reponse est "va" et l'indice de relecture, mot pour mot de l'arbitrage, est *"Lis le sujet avec chaque verbe propose : lequel va avec lui ?"*. Le mot "va" y apparait comme verbe de la phrase, et c'est aussi la bonne reponse. `TRV-CE1-5` dit "jamais la reponse". Je n'ai pas modifie le texte. Mon test de paquet ne porte pas de controle "l'indice ne cite pas la bonne reponse" : je ne l'ai pas ecrit pour ne pas rendre la suite verte par exemption, et aucun garde-fou generique ne l'a rougi.
- **E2.** `W1` (item 12 "elle") et `W4` (item 6 "gros") de l'arbitrage sont des recommandations hors de ma commande ; non appliquees.
- **E3.** L'item 18 de `ce1-classes-de-mots` porte un commentaire faux relevé par l'arbitrage ("au developpeur, pas une condition") ; non touche, hors de la commande.

## 5. Non verifie

- La justesse des 24 formes de L1 et des 24 classes de L2, la vraisemblance des distracteurs, l'ordre adjectif et nom de `CLM-CE1-3` : lecture de nadia-prof, aucun test ne les juge.
- L'affichage de l'indice apres une mauvaise reponse, et l'effet visuel du saut de ligne devant "?" : non joues.
- `A verifier en jouant` de l'arbitrage (15 items sur 24 de verbes irreguliers dont le pluriel s'entend) : non joue, c'est un jugement de nadia-prof.
- Jobs et etapes de la CI non lances : l'etape "Verifier la presence du build Maven" du job `build`.
