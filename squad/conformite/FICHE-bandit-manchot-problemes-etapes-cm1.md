# Fiche de conformite - bandit-manchot-problemes-etapes-cm1

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du catalogue).
- **Contenu** : `cm1-problemes-etapes`, 24 items, contenu neuf, story `S23-ETA`, ecrit par
  `marc-dev`.
- **Arbitrage prealable de `nadia-prof`** : rendu le 2026-08-30,
  `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md`,
  section 2 (`ETA-1` a `ETA-10`) et section 12.1 (amendement `ETA-4b` du 2026-08-30, qui remplace
  `ETA-4` ; `ETA-7` inchangee, lettre et seuil).
- **Fiche ouverte le 2026-08-30 par `nadia-prof`**, faute de fiche existante au moment de l'audit.
  La section "criteres pedagogiques attendus" appartient a `camille-po` et n'est pas ecrite ici.

## Competence visee

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| mathematiques | CM1 (cycle 3) | Nombres et calculs | Resoudre des problemes a plusieurs etapes mobilisant les 4 operations | `squad/programme/mathematiques/competences-cm1.md`, ligne 16 |

Ligne `non couvert`, differee deux fois pour capacite (`DEC-0029` a s12, `DEC-0030` a s13), jamais
pour un motif pedagogique.

## Bloc de validation

### Done pedagogique - 2026-08-30 (s23) - VALIDE SOUS RESERVE

**Done pedagogique prononce le 2026-08-30 par `nadia-prof`.** `camille-po` peut passer la ligne a
`couvert-valide`, **sans mention de couverture partielle** : "a deux etapes" est le "plusieurs" du
CM1, et c'est exactement ce que le paquet fait.

**Audit conduit en jouant**, application lancee depuis le fat jar construit sur l'arbre du
worktree, sur le JDK 21 de la toolchain declaree. Parties completes de dix manches, jouees en se
trompant volontairement sur chaque manche pour voir l'indice. Ce n'est pas un audit degrade.

#### Ce que j'ai verifie, recompte sur la population entiere des 24 items

Je n'ai repris aucun chiffre de l'auteur sans le refaire.

| Condition | Verdict | Mesure, population : les 24 items |
|---|---|---|
| `ETA-1` | **tenue** | 24 items a exactement deux etapes, lues une par une. Aucune etape habillee d'une phrase de plus : la premiere etape produit toujours une quantite que la seconde consomme |
| `ETA-2` | **tenue** | Addition **15**, soustraction **16**, multiplication **11**, division **6**. *Unite de compte : l'item.* Plancher de trois par operation. **Les quatre chiffres declares par `marc-dev` sont exacts** |
| `ETA-3` | **tenue sur 23 items, en defaut sur 1** | Voir reserve A |
| `ETA-4b` | **tenue** | **Les 48 distracteurs recalcules depuis l'enonce et les deux familles declarees : 0 ecart sur 24 items.** Seize items declarent (a) + (b), huit declarent (a) + (c). **La famille (a) figure sur les 24 items**, tres au-dessus du plancher de la moitie |
| `ETA-5` | **tenue** | Aucun operande 0 ni 1 sur les 72 operandes ; aucun resultat intermediaire egal au resultat final |
| `ETA-6` | **tenue** | Les 72 propositions sont des entiers positifs, deux a deux distinctes dans chaque item |
| `ETA-7` | **tenue** | **8 petite / 8 milieu / 8 grande**, soit 33,3 % chacun, contre un plancher de `arrondi superieur(24/4)` = 6. **Le compte declare par `marc-dev` est exact.** Les huit items de rang median sont bien les huit qui declarent (a) + (c) : ce que l'auteur annonce comme force algebriquement l'est, et je l'ai verifie sur les huit |
| `ETA-8` | **tenue** | Les 24 sujets sont des collectifs - bibliotheque, classe, ecole, club, atelier, epicerie, cantine, tri selectif, reserve, magasin scolaire. Aucun jardin, aucune piscine, aucune chambre a soi, aucun animal, aucun depart en vacances, aucun argent de poche. **Aucun role distribue par genre**, aucun enfant nomme |
| `ETA-9` | **tenue** | Les 24 indices donnent la methode de la premiere etape seule, jamais un resultat, et sont propres a leur item. Douze mots au plus. Verifie a l'ecran apres erreur |
| `ETA-10` | **tenue** | 24 items |

**Ce que la satisfaisabilite calculee le 2026-08-30 annoncait est confirme par du contenu reel** :
la construction de la section 12.1.2 prevoyait un lot de 24 items a 8 / 8 / 8 avec la famille (a)
sur les 24. C'est exactement ce que le paquet livre.

#### Les deux proprietes que j'avais annoncees garder seule, et ce que j'en dis

Je les avais nommees en section 12.1.3 pour que personne ne les croie couvertes par un garde-fou.

- **La plausibilite de la famille (c)** comme erreur reelle d'un enfant sur *cet* enonce-la. Verifiee
  item par item sur les huit items concernes. **Cinq tiennent nettement** : les items ou le premier
  enonce dit *"en recoit 15 de plus"*, *"en recoit 20 de plus"*, *"en recoit 14 de plus"*, *"en
  recoit 17 de plus"* ou *"en perd 12"* - c'est litteralement le *"de plus" lu la ou il y a "de
  moins"* que `ETA-4b` decrit. **Trois sont plus minces** : voir reserve B.
- **Le fait que les deux etapes en soient reellement deux.** Tenue sur 24 items sur 24.

#### Reserves

- **Reserve A, non bloquante, 2026-08-30 - `ETA-3` en defaut d'un jeton sur un item.** *Mesure sur
  la population entiere des 24 enonces, par le decompte de `ETA-3` - un mot est une suite de
  caracteres sans espace : de 22 a 31 mots. Vingt-trois enonces sont a 30 ou moins ; **l'item 17**
  (*"La classe recolte 40 timbres, puis en recoit 15 de plus pour une collection commune. Elle en
  donne ensuite 10 a une autre ecole. Combien de timbres la classe garde-t-elle ?"*) **est a 31**.*
  **La borne est brechee, je le dis plutot que de l'arrondir.** *J'ai reverifie ma propre mesure de
  reference avant de m'en servir : les 24 enonces de `cm1-problemes-durees` font 19 a 25 mots par ce
  decompte-ci, qui compte le point d'interrogation ; c'est bien la meme methode, la comparaison est
  legitime.*
  **Pourquoi elle ne bloque pas** : le jeton qui breche est **le point d'interrogation**. La charge
  de lecture reelle de cet enonce est de 30 mots, dans ma borne. Un enonce de 30 mots et un enonce
  de 30 mots suivis d'un signe ne se lisent pas differemment, et refuser un increment pour cela
  serait faire de ma porte un caprice. **Ce n'est pas une redefinition de mon decompte** : la breche
  est reelle, elle est mineure, et je la nomme pour qu'elle soit corrigee et non pour qu'elle soit
  oubliee.
  **Levee** : retirer un mot a l'enonce de l'item 17.
- **Reserve B, non bloquante, 2026-08-30 - la famille (c) repose sur une melecture mince sur trois
  items.** `ETA-4b` dit que la famille (c) *"n'est utilisable que si l'operation inversee correspond
  a une melecture plausible de l'enonce"*, et que cette clause reste dans ma seule main. Sur les huit
  items qui declarent (c), **trois** l'appuient sur les verbes *"distribue"* (item 22), *"retire"*
  (item 23) et *"vend"* (item 24). Ces trois verbes designent sans ambiguite une sortie de stock ; les
  lire comme une entree n'est pas l'erreur qu'un CM1 commet reellement, contrairement a *"perd"*
  (item 21) ou a *"de plus"* (items 17 a 20). Le distracteur y reste un **calcul inverse mecanique**
  plutot qu'une **erreur d'enfant**.
  **Pourquoi elle ne bloque pas** : les trois distracteurs restent recalculables, distincts et
  plausibles comme valeurs ; l'item n'enseigne rien de faux et ne devient pas une devinette. Ce qui
  manque est le supplement pedagogique qu'un distracteur qui reproduit une vraie erreur apporte -
  et sur ces trois items, la famille (a), qui est presente, le fournit deja.
  **Levee** : reformuler le premier enonce de ces trois items avec un verbe dont la melecture est
  reelle (*"perd"*, *"en donne"*), ou basculer ces items sur (a) + (b) en compensant le rang median
  ailleurs.
- **Reserve C, non bloquante, 2026-08-30 - "3 ateliers" se lit de deux facons sur deux items.**
  Items 6 et 7 : *"L'atelier de poterie fabrique 75 bols, dont 18 sont abimes. **3 ateliers**
  fabriquent chacun ce meme nombre de bols reussis."* Un enfant peut lire "3 ateliers" comme incluant
  le premier (lecture voulue, 3 x 57 = 171) ou comme trois **autres** ateliers (57 + 3 x 57 = 228).
  **Ce n'est pas un item a deux reponses justes** : 228 n'est pas propose, et l'item se resout donc
  sur 171 dans les deux cas. Mais l'enfant qui a fait la seconde lecture ne comprend pas pourquoi il
  a raison.
  L'item 7 porte la meme construction avec un glissement de sujet en plus - *"L'atelier de couture
  prepare 50 coussins... 4 **ecoles** preparent chacune ce meme nombre"*.
  **Levee** : ecrire *"l'atelier et deux autres ateliers"*, ou *"chacun des 3 ateliers de la ville,
  celui-ci compris"*.

#### `MNC-1`

**`MNC-1`** - Ce Done ne couvre pas que l'enfant voie, sans faire defiler la page, un element
quelconque du jeu, y compris le retour qui suit sa reponse - verdict, indice, proposition. Cette
propriete est sortie de la porte pedagogique par arbitrage de l'utilisatrice du 2026-08-28
(`DEC-0053`) ; sa dette est ouverte, croissante et assumee. Aucune mesure ne l'a instruite sur ce
paquet.

Le rappel n'est pas de pure forme ici : ce sont les enonces les plus longs livres depuis
`cm1-problemes-durees`, jusqu'a 31 mots.

#### Ce sur quoi je reste la seule garde sur ce paquet

- **La plausibilite de la famille (c)** sur chaque enonce (reserve B). Aucun test ne la voit : un
  automate recalcule un nombre, il ne sait pas si un enfant commet cette erreur-la.
- **Que les deux etapes en soient reellement deux** - tenue, et invisible a tout garde-fou.
- **`ETA-8`** (presupposes sociaux, roles de genre) et **`ETA-9`**.
- **L'exactitude des 24 calculs** et le jugement de niveau.

#### Ce que je route

| Suite | Destinataire | Ce que je demande |
|---|---|---|
| **Done pedagogique prononce. La ligne peut passer a `couvert-valide`, sans mention de couverture partielle** | `camille-po` | Ecrire la matrice. Ce n'est pas moi qui l'ecris |
| **Reserves A, B et C, non bloquantes** | `camille-po` | En faire des stories d'un sprint suivant. Chacune porte son critere de levee, applicable sans me le redemander |
| **`ETA-4b` et `ETA-7` sont satisfaisables ensemble, et c'est verifie sur du contenu reel** | `theo-archi`, `yanis-qa` | Le calcul de satisfaisabilite du 2026-08-30 a predit exactement ce que le contenu livre. Rien a corriger |
| **La clause de plausibilite de la famille (c) reste hors de tout garde-fou** | `yanis-qa`, `theo-archi` | Ne pas la compter comme couverte. Elle a mordu ici, sur trois items |
