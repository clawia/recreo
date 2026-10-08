# Rapport de marc-dev, s55, corrections de l'audit de nadia-prof (instance A)

Worktree `s55-marc-corrections`, base `7e32138a` (`round-two`). `bash outils/verifier-lignee.sh` : lignee de
`round-two` confirmee, avant et apres. Sources : `audit-nadia-a.md`, `FICHE-bandit-manchot-accord-sujet-verbe-ce1.md`,
`FICHE-bandit-manchot-classes-de-mots-ce1.md`. Rien rebase, fusionne ni pousse.

## Commits

| Poste | Commit | Contenu |
|---|---|---|
| 1 | `2b66a441` | indice de l'item 6 de `ce1-accord-sujet-verbe` ; controle `trv5AucunIndiceNeContientUnePropositionDeSonItem` ; jeu fautif |
| 2 | `fa3b97c0` | commentaire et en-tete de l'item 18 de `ce1-classes-de-mots` (`E3`) |

## Poste 1 - ACV-CE1-R1

Texte posé mot pour mot, espaces insecables U+00A0 devant `:` et `?` (octets `302 240` verifies) :
`Lis le sujet avec chaque verbe proposé : lequel est le bon ?`. Item 6 seulement (`il ___ au marché`, bonne reponse
`va`). Item 12 non touche.

Greps sur `ce1-accord-sujet-verbe.yaml` :

| Motif | Avant | Apres |
|---|---|---|
| `lequel va avec lui` | 5 | 4 (items 9, 12, 18, 21, qui gardent leur indice) |
| `lequel est le bon` | 0 | 1 (item 6) |

Controle ajoute dans `PaquetCe1AccordSujetVerbeTest` : aucun indice ne contient une proposition de son item (mot entier,
insensible a la casse, bornes `\p{L}\p{N}`).

Preuve de morsure (ADR-0013) :

- Mutant : `lequel est le bon` remis a `lequel va avec lui` sur l'item 6. `mvn -B -pl adapters/adapter-content -am test
  -Dtest=PaquetCe1AccordSujetVerbeTest` : 17 tests, 1 echec, `trv5AucunIndiceNeContientUnePropositionDeSonItem`, message
  `l'indice '... lequel va avec lui ?' contient la proposition 'va' (ACV-CE1-R1, TRV-CE1-5)`. Fichier restaure ensuite.
- Jeu fautif commite : troisieme item ajoute (`indice: "Cherche le verbe va avec ce sujet."`, bonne reponse `va`), avec
  le test `leJeuFautifCommiteEstRefuseSurSonIndiceQuiContientUneProposition`. L'indice du deuxieme item du jeu fautif
  (K2) contenait aussi `va` ; je l'ai remplace par le texte corrige pour que chaque item fautif ne porte qu'un defaut.

Autres items rougis par le controle : aucun. Avec le paquet reel, les 24 items passent ; les items 9, 12, 18, 21 (indice
de relecture) ne contiennent aucune de leurs propositions.

Non fait : le meme controle sur `ce1-classes-de-mots` (non demande). Non verifie si ses 24 indices le passent.

## Poste 2 - E3

Textes de la fiche, mot pour mot, sans accent comme dans la fiche :

- au-dessus de l'item 18 : `  # CLM-CE1-1: adjectif attribut, apres le verbe etre` ;
- lignes 9 et 10 de l'en-tete : `# CLM-CE1-3 : ... l'adjectif est place` (inchangee) puis
  `# avant le nom dans deux items sur cinq, apres le nom dans deux, apres le verbe etre dans un.`

Greps sur `ce1-classes-de-mots.yaml` : `apres le verbe etre` 0 -> 2 (commentaire et en-tete) ; `adjectif après le nom` 3 -> 2
(items autres que le 18, non touches).

Aucun indice touche au-dela de l'item 6 de l'autre paquet. `ACV-CE1-R2` et `CLM-CE1-R1` non levees.

## Mesure

- JDK rapporte par l'enveloppe : 21.0.10 (`outils/mvn-jdk21.sh`).
- Delta de tests : +2, perimetre `PaquetCe1AccordSujetVerbeTest` (15 -> 17 methodes `@Test`), mesure sur la base `7e32138a`
  plus ces deux commits. Aucun total fusionne annonce. Poste 2 : +0.
- Reacteur complet `bash outils/mvn-jdk21.sh -B clean verify` : `domain` 120, `application` 18, `adapter-content` 402 et le
  module suivant 1391, tous verts ; **BUILD FAILURE dans `bootstrap`** (312 tests, 2 echecs), avant et apres chaque commit.

## Ecart

**E-1 : le reacteur complet n'est pas vert, et la cause n'est pas dans mon diff.** Les deux echecs sont dans
`AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest` (`ADR-0087`) :
`squad/sprints/s55-.../rapport-sofia-outils.md:22` porte 5 cellules pour un en-tete de 3 colonnes (la ligne
`Cas neuf, correction retiree (ADR-0013)`, `attendu 0|mvn lance, obtenu 1|mvn non lance`). Le fichier vient de
`0e4b8ada` (`[SOF]`), present dans la base ; je ne l'ai pas modifie (hors perimetre). La consigne exigeait un verify vert
avant chaque commit : j'ai commite avec ces deux seuls echecs, identiques aux deux passages, et le declare. A `sofia-sm`
de corriger la ligne (barre verticale dans une cellule) avant fusion. Je n'ai pas mesure la base seule, seulement constate
que le fichier fautif n'est pas dans mon diff.

## Jobs de `ci.yml`

| Job | Lance |
|---|---|
| `build` (`mvn verify`) | oui, echec `bootstrap` ci-dessus ; `Package du fat jar` non lance |
| `chemins` | oui (`verifier-chemins.sh` sur `git ls-files`) : vert, avant et apres le poste 2 |
| `prefixes` | non |
| `outils` | non |

## Non verifie

- Rendu a l'ecran de l'indice de l'item 6 (jar non lance) : le changement touche un texte lu par l'enfant.
- Les jobs `prefixes` et `outils`, et le package du fat jar.
- Les 24 indices de `ce1-classes-de-mots` contre le controle ajoute a l'autre paquet.
