# DEC-0118 - Accueil par matiere : le seuil de defilement de la vue par defaut passe de 10 a 8 cartes visibles

- **Type** : decision de produit (valeur d'un critere d'acceptation)
- **Date** : 2026-10-12
- **Auteur** : `camille-po`
- **Sprint** : hors sprint (chantier ouvert par `DEC-0117`)
- **Competence programme visee** : transverse - pas de contenu pedagogique. Aucune ligne de
  `MATRICE-couverture.md` ne change.
- **Statut** : **Acceptee**. Le seuil est une valeur du PO (`DEC-0117` section 6 ; critere de `HS-ACCUEIL-V2`
  : *"Si `ines-design` montre que 10 n'est pas atteignable [...] elle me renvoie la mesure et je fixe un autre
  seuil par une `DEC` ; personne ne le baisse en silence"*). Aucun code ne porte la valeur 10 (section 4) : la
  decision n'a pas de changement de code a router.
- **Lien** : `DEC-0117` ; `DEC-0053` ; `DEC-0040` ; `docs/backlog/backlog.md`, `HS-ACCUEIL-V2` et section
  "Cloture du chantier hors sprint : accueil par matiere" ;
  `squad/sprints/hors-sprint-accueil-vues/audit-nadia.md` (P8) ;
  `squad/sprints/hors-sprint-accueil-vues/rapport-qualite.md` (E1) ; `squad/conformite/FICHE-accueil-par-matiere.md`.

## 1. Ce que je baisse

Critere de `HS-ACCUEIL-V2`, "Defilement" : *"au moins 10 cartes entierement visibles sans defiler"* a 744 x 551.
**Il devient 8**, dans les trois niveaux (CP, CE1, CM1), avec la meme methode (`DEC-0117` section 4 : une carte
est visible si son `bottom` est inferieur ou egal a `innerHeight`, `top`, `left` et `right` dans la fenetre).
La seconde branche du critere de la fiche (toutes les tuiles de matiere de l'etage 1 visibles) ne change pas,
non plus que `min(n, 6)` a l'etage 2 de `HS-ACCUEIL-V1`.

## 2. Pourquoi : ce que le depot ecrit

- **Mesure** (`rapport-qualite.md` E1, section 2.2, jar de `def23ead`) : V2 rend **8** cartes entierement
  visibles a 744 x 551 en CP, en CE1 et en CM1 ; **5** sur la liste d'avant ; **12 / 9 / 10** a 992 px ;
  **3** a 390 px. Le rapport l'ecrit : *"le seuil de 10 cartes n'est pas atteint a 744 px, il vaut 8 dans les
  trois niveaux"*. `lea-dev` retrouve les memes chiffres (R5 de sa section 16.2), `yanis-qa` confirme sans ecart.
- **Jugement pedagogique** (`audit-nadia.md` P8) : *"8 me suffit pedagogiquement. [...] Je n'ai pas de motif
  pedagogique pour 10 plutot que 8."* Elle renvoie le seuil a ce fichier : *"c'est a elle de le changer par
  une `DEC`, pas a moi de le baisser."* Je ne juge pas ce motif, je le cite.
- **Ce que le seuil de 10 devait obtenir** : que l'enfant ne se perde plus au defilement et sache la matiere.
  Les deux plaintes (`DEC-0117` section 1) sont levees par les mesures : 0 matiere nommee devient 8 sur 8 (CP,
  CM1) et 2 sur 2 (CE1) ; le document passe de 4216 a 2922 px en CP (-30,7 %).
- **Ce que je ne fais pas** : demander une retouche du gabarit pour atteindre 10. Le critere de `HS-ACCUEIL-V2`
  prevoyait ce renvoi ("elle me renvoie la mesure") ; la mesure est revenue, et le motif pedagogique de 10 n'est
  ecrit nulle part.

## 3. Ce que ce critere vaut aujourd'hui, dit sans detour

Le critere est **deja vert** sur le code livre (8 / 8 / 8). Ce n'est donc pas un critere correctif : c'est un
**plancher de non-regression** place sur la mesure du jar. La correction (5 cartes, 0 matiere nommee) est
portee par les criteres "Matiere nommee" et "Vue par defaut" de `HS-ACCUEIL-V2`, qui etaient rouges sur la liste
d'avant. Le seuil de 8 est ecrit **apres** la mesure, pas avant : un lecteur ne doit pas y voir un objectif
atteint, mais une valeur qu'on a choisi de ne pas laisser descendre sous ce qui a ete joue par `nadia-prof`.
Si une evolution du gabarit ou de la feuille le fait descendre, la commande rend un ecart et un PO decide (une
`DEC`), personne ne le baisse en silence une seconde fois.

## 4. Routage, et ce qui ne change pas

- **Aucun code ne change.** `outils/mesurer-accueil.py` ne porte aucun seuil par defaut : `--seuil-v2` est un
  argument d'invocation, absent par defaut (verifie : aucune occurrence de la valeur 10 en seuil dans
  `outils/`, `adapters/`, `bootstrap/` ni `.github/`). La commande de rejeu est donc :
  `python outils/mesurer-accueil.py --url http://localhost:<port> --seuil-v2 8`.
- **Non fixe ici** : un seuil a 390 px (3 cartes visibles ; `rapport-qualite.md` E3 : *"une valeur que personne
  n'a fixee"*). Je n'en pose pas : aucune mesure du produit ne m'y autorise.
- **A `sofia-sm`** : si le job ou la porte de sprint cite `--seuil-v2 10`, le dire a l'agent qui l'ecrit ; je
  n'ai trouve aucun appel de ce type dans le depot.
- **Pas un motif de `REFUSE`** : `audit-nadia.md` P8 et son arbitrage prealable (N8) le disent ; cette decision
  ne le change pas.

## 8. Annotation du 2026-10-12 (`camille-po`, `DEC-0119`)

Rien au-dessus n'est reecrit. `DEC-0119` fait de V1 la vue par defaut ; ce que cela change ici :

- **Inchange** : la valeur 8 cartes visibles a 744 x 551, la mesure 8 / 8 / 8, le motif de `nadia-prof` (P8), le statut de
  plancher de non-regression deja vert, l'absence de seuil en code.
- **Change** : le critere ne porte plus sur "la vue par defaut" (titre, section 1) mais sur **V2 atteinte par "Tout voir"**.
  La commande `--seuil-v2 8` (section 4) doit charger V2 par la bascule : `HS-ACCUEIL-MESURE-DEFAUT`.
- **Question a `nadia-prof`, non tranchee ici** : "8 me suffit" a ete dit pour le premier ecran vu par l'enfant
  (`DEC-0119` section 6).
- Statut `Acceptee` inchange. Aucun code a router par cette annotation.

## Impact

- `squad/decisions/produit/DEC-0118-...md` (ce fichier) : cree ; `DEC-index.md` : ligne ajoutee.
- `docs/backlog/backlog.md` : critere "Defilement" de `HS-ACCUEIL-V2` annote, section de cloture ajoutee.
- `squad/conformite/FICHE-accueil-par-matiere.md` : critere "10 cartes" annote (bloc de validation intact).
- Catalogue, paquets, matrice, `domain/`, `application/`, `adapters/`, `bootstrap/` : **inchanges**.
