# DEC-0010 - Le titre du produit redevient "Recreo", sans accent : le point 7 de DEC-0007 est remplace

- **Type** : perimetre produit, nom de marque
- **Date** : 2026-08-06
- **Auteur** : camille-po
- **Sprint** : s05
- **Competence programme visee** : transverse - decision sur le nom du produit, aucun contenu
  pedagogique.
- **Statut** : Acceptee

## Contexte

`DEC-0007` point 7 tranchait que le titre devienne "Récréo" et l'executait par la story S3-9,
mise en oeuvre par `ines-design` : le titre affiche aujourd'hui "Récréo" dans `accueil.html`,
lignes 6 et 22.

L'utilisatrice corrige : **"Récréo s'ecrit Recreo sans accent."** C'est le nom de son produit,
elle en decide seule ; cette correction prime sur mon arbitrage precedent et je ne le redefends
pas ici, exactement comme `DEC-0008` a deja renverse le point 1 de `DEC-0007` sur sa demande.

## Ce que cela recoupe avec la reserve 10 de nadia-prof, et ce qui les distingue

Reserve 10 (`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, verdict s01, section 6) :
`nadia-prof` relevait que "Recreo" etait le seul mot non accentue de l'ecran d'accueil, qu'un
enfant de CP le dechiffre a chaque visite et lit "/rekreo/" plutot que la graphie voulue. Elle a
porte ce point en reserve, pas en motif de refus ("c'est un nom de produit, je ne le refuse pas"),
et me l'a explicitement renvoye comme une question de marque, pas la sienne a trancher. Apres la
correction de S3-9, elle a enregistre cette reserve comme **tenue** dans sa fiche.

Je ne pretends pas que le revirement d'aujourd'hui fait disparaitre le constat qu'elle a fait :
un enfant qui dechiffre "Recreo" lira toujours la meme chose qu'avant S3-9. Ce que je tranche,
c'est **a qui appartient la decision**, pas la question pedagogique qu'elle avait posee. Le nom
du produit est fixe par sa proprietaire, et c'est precisement le type d'arbitrage qu'elle m'avait
renvoye plutot que de le trancher elle-meme ("a trancher comme une question de marque, pas par
moi") : un nom propre de produit n'est pas un mot de vocabulaire que l'equipe redige pour
apprendre a l'enfant a lire ou a orthographier - a la difference du texte vise par la categorie B
de `DEC-0002` (consignes, retours, messages), qui reste integralement accentue et sans faute, sans
aucun changement par cette decision. Je ne fabrique pas une regle linguistique generale ("un nom
propre echappe toujours a l'accentuation") pour justifier ce choix : je constate que la decision
sur le nom exact du produit ne m'appartenait pas et que l'utilisatrice vient de l'exercer dans
l'autre sens que celui que j'avais retenu en s03.

## Decision

**Le titre du produit s'ecrit "Recreo", sans accent**, partout ou il apparait dans l'application.
Ceci remplace le point 7 de `DEC-0007` ; les autres points de cette decision restent inchanges
(le point 1 est deja remplace par `DEC-0008`, sans lien avec le present point).

Execution en story S5-8 de `docs/backlog/backlog.md`, sprint 5.

## Ce que cette decision ne fait pas

- Elle ne rouvre ni ne referme la reserve 10 de `nadia-prof` : cette reserve reste enregistree
  comme "tenue" dans sa fiche, qui lui appartient et que je ne modifie pas. Je lui signale ce
  point explicitement (elle en prend acte au moment ou elle le juge utile), plutot que d'editer
  son fichier ou de laisser le depot afficher un etat qu'elle n'a pas revu.
- Elle n'affaiblit en rien l'exigence positive de `DEC-0002` (texte francais correct et
  integralement accentue) pour le reste du texte destine a l'enfant : consignes, retours et
  messages restent integralement accentues. Seul le nom propre du produit est concerne.
- Elle ne tranche aucune question technique sur la maniere de revenir au texte non accentue.

## Impact

- `docs/backlog/backlog.md`, sprint 5 : nouvelle story S5-8, qui revert S3-9 et ajoute le test
  automatise que `yanis-qa` avait signale comme manquant en s03 (`rapport-qualite.md` du sprint 3,
  ligne 217) et qui reste absent a ce jour.
- `squad/decisions/produit/DEC-index.md` : ligne `DEC-0010` ajoutee ; la ligne `DEC-0007` est
  mise a jour pour noter que son point 7 est remplace par la presente decision.
- `squad/conformite/FICHE-bandit-manchot-additions-cp.md` : non modifiee par cette decision -
  signalee a `nadia-prof`, pas editee par `camille-po`.

## Ce qui reste non testable par construction

- Que "Recreo" sans accent n'induise pas, en pratique, la lecture ou la graphie fausse que
  `nadia-prof` a decrite reste une question ouverte sur le fond : ce n'est pas resolu par cette
  decision, c'est un arbitrage de nommage qui ne m'appartenait pas et que l'utilisatrice tranche
  desormais elle-meme.
