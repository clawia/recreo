# S19 - fusion des lots 10 et 11 sur round-two (porte technique)

Suite de `fusion-lots-7-a-9.md`. La porte technique (Yanis + Theo) est rendue ; cette fusion la
fait entrer dans round-two. Toujours pas une review : ce fichier ferme le developpement et la
porte technique, la relecture et l'audit pedagogique restent a faire.

## L'arbitrage qui m'a ete confie

La coordination a route le meme obstacle (le defaut d'encodage d'espace insecable, invisible a
toute porte technique) a `theo-archi` et a `yanis-qa` en parallele, sans que l'un sache que
l'autre l'ecrivait. Les deux ont produit une classe de garde-fou sur la meme famille de defaut,
dans le meme module. `theo-archi` a rendu un verdict ecrit (branche `s19-theo-3`) : sa regle
reste, celle de `yanis-qa` fait doublon, mais ses epreuves par mutation sur chaine reellement
servie sont meilleures que sa propre preuve manuelle et absorbees dans `ADR-0060`.

**Je n'ai pas relaye ce verdict, je l'ai verifie sur le code avant de trancher** :

- La clause 1 de `theo-archi` (`LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest`) detecte
  toute contre-barre par un motif categoriel (`Pattern.compile("\\\\")`), sans jamais enumerer une
  graphie precise.
- La clause equivalente de `yanis-qa` (`AucunTexteServiNePorteUnLookAlikeDEspaceInsecableTest`)
  teste `champ.contains(ARTEFACT_SIX_CARACTERES)`, ou `ARTEFACT_SIX_CARACTERES` est un litteral a
  casse hexadecimale figee (majuscule). `String.contains` est sensible a la casse : la graphie
  minuscule de la meme sequence d'echappement lui echappe entierement, alors qu'elle designe le
  meme caractere. C'est la faute etablie en s11 (critere enumere sur un nom d'incident plutot que
  sur une categorie), rejouee.
- La clause sur les guillemets francais est structurellement identique dans les deux classes
  (meme predicat sur le caractere precedent/suivant un guillemet ouvrant/fermant).

Verdict confirme : la regle de `theo-archi` mord tout ce que celle de `yanis-qa` mord, et au moins
un cas de plus (la graphie minuscule). La reciproque est fausse. Le doublon n'a donc pas sa place
dans l'arbre fusionne.

## Ordre de fusion et resultat

| Ordre | Branche | Commit(s) fusionne(s) | Merge sur round-two | Traitement |
|---|---|---|---|---|
| 10 | `s19-theo-3` | `646a5e9`, `4d9d33c` | `c571d5b` | fusionne integralement |
| 11 | `s19-yanis` | `c9a5052`, `9540178` | `411a178` | `rapport-qualite.md` fusionne tel quel ; `AucunTexteServiNePorteUnLookAlikeDEspaceInsecableTest.java` et sa fixture **retires du merge**, doublon absorbe par le lot precedent |

Aucun conflit git au sens strict : les deux lots ne touchaient aucun chemin commun (la collision
etait editoriale - deux classes de garde-fou distinctes sur la meme propriete - pas textuelle).
L'arbitrage a donc consiste a retirer deux fichiers du merge de `s19-yanis` par un `git rm --cached`
explicite dans le meme commit, avec le motif ecrit ci-dessus, plutot qu'a resoudre un conflit que
git aurait signale de lui-meme.

## Porte technique

`mvn clean verify` sur le reacteur complet, six modules, `BUILD SUCCESS`.

**Compte de tests, methode de reference, mesure par moi sur l'arbre fusionne : 666.**

Base avant cette passe (fin de `fusion-lots-7-a-9.md`) : **657**. `theo-archi` (rule d'`ADR-0060`
: +5 ; absorption des quatre epreuves de `yanis-qa` : +4) porte le total a **666** sur sa seule
branche, chiffre qu'il avait lui-meme annonce apres absorption. `yanis-qa` n'ajoute aucun test au
merge final : son rapport ne porte pas de code, et sa classe de garde-fou est retiree. 657 + 5 + 4
+ 0 = 666. Conforme au chiffre annonce par `theo-archi` apres absorption ; verifie, pas relaye.

Nombre de regles ArchUnit : les deux agents annoncent 30 par leur propre methode. Je n'ai pas
reconstruit ce chiffre par une methode fiable de mon cote (un grep sur `@ArchTest` seul en rend 29,
imprecis - certains fichiers portent plusieurs `ArchRule` sous un decoupage que ce grep ne resout
pas) : je ne le releve pas comme verifie par moi, a la difference du compte de tests.

## Etat de round-two apres cette fusion

`round-two` local est a `411a178`, non pousse (`origin/round-two` reste a `c3d3d47`). Onze lots
fusionnes depuis le debut du sprint. Developpement et porte technique clos : reste la relecture du
`relecteur-pedagogique`, l'audit pedagogique de `nadia-prof` (lance en parallele par la
coordination sur les quatre paquets neufs), puis review, `RAPPORT.md`, rapport de tokens, retro.

## Retro (matiere, pas verdict)

La collision de routage vient d'une decision de coordination, pas d'un defaut de travail de
`theo-archi` ou de `yanis-qa` : aucun des deux ne pouvait savoir que l'autre ecrivait sur le meme
obstacle. Elle a produit une trouvaille reelle (la sensibilite a la casse), mais par confrontation
accidentelle de deux regles independantes, pas par une methode qui la visait. Un obstacle route a
deux agents en parallele sur le meme perimetre doit etre nomme comme tel au moment du routage, pas
decouvert a la fusion.
