# Avis de Done pedagogique - s23 - `nadia-prof`

- **Date** : 2026-08-30
- **Branche** : `s23-nadia-3`, worktree dedie depuis `round-two` a `86eea1d`
- **Perimetre** : quatre paquets - trois audits neufs (`S23-RAN`, `S23-ETA`, `S23-LIA`) et un
  reaudit (`cm1-problemes-durees`)
- **Non fusionne.** La fusion appartient a `sofia-sm`.

## 1. Les quatre verdicts

| Paquet | Story | Items | Auteur | Verdict | Ligne de matrice |
|---|---|---|---|---|---|
| `cp-ranger-nombres` | `S23-RAN` | 24 | `marc-dev` | **REFUSE** | reste `en cours` |
| `cm1-problemes-etapes` | `S23-ETA` | 24 | `marc-dev` | **VALIDE SOUS RESERVE** | peut passer `couvert-valide`, sans mention partielle |
| `cm1-mots-de-liaison` | `S23-LIA` | 20 | `sofia-sm` | **REFUSE** | reste `non couvert` |
| `cm1-problemes-durees` | reaudit | 24 | `lea-dev` | **VALIDE SOUS RESERVE (maintenu)** | reste `couvert-valide` |

**Un seul Done neuf est prononce sur trois paquets neufs.** Les motifs sont ecrits en entier, avec
leur critere de levee, dans les blocs de validation :

- `squad/conformite/FICHE-bandit-manchot-ranger-nombres-cp.md`
- `squad/conformite/FICHE-bandit-manchot-problemes-etapes-cm1.md`
- `squad/conformite/FICHE-bandit-manchot-mots-de-liaison-cm1.md`
- `squad/conformite/FICHE-bandit-manchot-problemes-durees-cm1.md` (bloc de reaudit, en fin de
  fichier ; les verdicts anterieurs restent lisibles au-dessus)

## 2. J'ai joue, je n'ai pas lu

Fat jar construit sur l'arbre du worktree, lance sur le JDK 21 de la toolchain declaree, session
HTTP reelle. Parties completes de dix manches sur chacun des quatre jeux, jouees **en se trompant
volontairement sur chaque manche** pour voir l'indice, et jusqu'a la revelation sur certaines.
**Aucun des quatre audits n'est degrade.**

**Les deux refus ont ete vus a l'ecran avant d'etre ecrits** :

- `S23-RAN` : l'item `37, 100, 16` rendu avec ses trois propositions, et sous elles l'indice
  *"Compare les dizaines, puis les unites si besoin."* ;
- `S23-LIA` : `donc` presse sur *"La fusee quitte le sol. ___, elle traverse les nuages."*, rendu
  *"Ce n'est pas ca. Essaie encore !"*.

## 3. Ce que le contenu reel a mis en defaut

**Rien de ce que les auteurs declarent. Tout ce qu'ils declarent est exact, et je l'ai recompte
plutot que de le croire.**

| Declaration | Verifiee sur | Verdict |
|---|---|---|
| `S23-RAN` : 13/24 tiennent `RAN-5b`, 9/24 tiennent `RAN-6`, propositions a 11 caracteres au plus | 24 items | **exacte** |
| `S23-ETA` : 8 petite / 8 milieu / 8 grande ; 15 add, 16 sub, 11 mul, 6 div ; (a)+(c) tombe sur le rang median par construction | 24 items | **exacte**, les 48 distracteurs recalcules depuis l'enonce donnent **0 ecart** |
| `S23-LIA` : quatre relations a cinq items chacune ; ce que le test prouve et ce qu'il ne prouve pas | 20 items, 40 distracteurs | **exacte, y compris la limite que `sofia-sm` pose elle-meme a son test** |
| `cm1-problemes-durees` : rang du paquet 7/12/5 apres le lot | 24 items | **exacte** |

**Ce que le contenu reel a mis en defaut, ce sont mes propres constats, trois fois** :

1. **Mon instrument de signes orphelins rend un faux positif** sur tout texte portant une espace
   insecable : sa replique enveloppe chaque jeton dans un `<span>` et coupe a l'insecable, creant
   une occasion de coupure que la page servie n'a pas. Il annoncait 3 orphelins restants dans
   `cm1-problemes-durees` ; sur le balisage reellement servi, au meme viewport, il y en a **0**.
   Chiffre corrige du catalogue : **14**, pas 17. Ma mesure du 2026-08-30 (18, dont 4 dans ce
   paquet) reste exacte a sa date, le paquet employant alors l'espace ordinaire.
2. **Mon critere de levee de la reserve A ne ferme pas ce que la reserve visait.** Deux ecritures
   superieures a 59 minutes au lieu d'une : ma lettre est tenue, et l'enfant elimine toujours les
   deux a vue sans calculer.
3. **Ma reserve D n'avait pas de critere de levee**, ce qui la rendait impossible a fermer sans me
   la redemander. J'en ecris un, date, opposable.

## 4. Reserves bloquantes et non bloquantes

### Bloquantes - ce sont les deux refus

- **`S23-RAN`, `RAN-7`** - les six indices sont affectes par un cycle de six, sans lien avec l'item.
  **Cinq items sur 24 en defaut.** Le grave : sur les items 16 (`48, 2, 100`) et 22
  (`37, 100, 16`), la methode que l'indice enonce, appliquee litteralement, produit exactement le
  second distracteur de l'item, present a l'ecran - le chiffre des dizaines de 100 vaut 0. **Une
  erreur factuelle dans ce que l'enfant lit apres un echec.** Le second : sur les items 1, 13 et 19,
  *"Compare le nombre de chiffres de chaque nombre."* est servi sur des triplets dont les trois
  nombres ont deux chiffres, ou la methode ne separe rien.
  **Levee** : que chaque indice soit choisi pour son item. Les six indices ecrits sont bons, c'est
  leur affectation qui ne l'est pas.
- **`S23-LIA`, `LIA-3`** - **deux distracteurs sur 40** produisent un texte moins bon et non faux :
  item 3, `puis` (*"Il a beaucoup revise ses lecons. Puis, il a reussi son controle."* - la
  succession est reelle) et item 18, `donc` (*"La fusee quitte le sol. Donc, elle traverse les
  nuages."* - la consequence est reelle). L'enfant qui les choisit est compte faux en ayant raison.
  **Levee** : remplacer ces deux distracteurs. Ma frontiere entre "faux" et "moins bon" est ecrite
  avec son cas voisin (item 20, `donc`, que je ne compte pas en defaut) pour etre reutilisable sans
  moi.

### Non bloquantes

- **`S23-ETA`, reserve A** : `ETA-3` brechee d'un jeton sur l'item 17 (31 mots contre 30). Le jeton
  est le point d'interrogation ; la charge de lecture reelle est de 30 mots. Breche reelle, mineure,
  nommee pour etre corrigee.
- **`S23-ETA`, reserve B** : sur trois des huit items qui declarent la famille (c), la melecture
  qui la fonde repose sur *"distribue"*, *"retire"*, *"vend"* - des verbes sans ambiguite. Le
  distracteur y est un calcul inverse mecanique plutot qu'une erreur d'enfant.
- **`S23-ETA`, reserve C** : *"3 ateliers fabriquent chacun ce meme nombre"* (items 6 et 7) se lit
  comme incluant ou excluant le premier atelier. L'item se resout dans les deux cas, mais l'enfant
  qui a fait la seconde lecture ne comprend pas pourquoi il a raison.
- **`cm1-problemes-durees`, reserve D** : non levee, reduite de 12/12 a 11/12 sur la forme "horaire
  d'arrivee". Critere de levee ecrit et date.
- **Constats neufs et dates, qui n'entrent dans aucun verdict** : le raccourci de fin de liste sur
  `S23-RAN` (93,8 %, voir section 5) ; l'indice de ponctuation sur les items de `S23-LIA` a `___`
  median ; l'instant `9 h 10` toujours coupe entre l'heure et les minutes ; l'enonce de Malo coupe
  a l'interieur de `dure-t-elle`.

## 5. Le raccourci mesure, et pourquoi il n'est pas un motif

**Etape 3 de la procedure que je me suis imposee le 2026-08-30 : quand le motif d'une condition
nomme un raccourci, verifier le raccourci et non la condition.** Appliquee aux trois paquets neufs.

| Paquet | Raccourci | Reussite mesuree | Population |
|---|---|---|---|
| `S23-RAN` | *"la liste dont le premier nombre est le plus petit"* - celui que `RAN-5b` vise | **72,9 %** (17,5 / 24) | les 24 items, egalites comptees uniformement |
| `S23-RAN` | *"la liste dont le dernier nombre est le plus grand"* | **93,8 %** (22,5 / 24) | idem |
| `S23-ETA` | *"toujours la plus grande / la plus petite / celle du milieu"* - celui que `ETA-7` vise | **33,3 %** chacun | les 24 items |
| `S23-LIA` | *"la relation la plus frequente"* - la protection de `LIA-5` | **25,0 %** | les 20 items, 5 / 5 / 5 / 5 |

**Deux choses a en tirer, et je separe soigneusement ce qui est un motif de ce qui n'en est pas.**

- **`ETA-7` fait exactement ce qu'elle promet**, et c'est la premiere fois que je peux le verifier
  sur du contenu reel plutot que par enumeration : le rang majoritaire tombe a 33,3 %, la ou
  `ETA-4` d'origine y laissait *"toujours la plus grande"* gagner 50,0 %. **L'amendement du
  2026-08-30 a paye.**
- **`RAN-5b` est tenue et `RAN-5b` est basse.** Sous `RAN-1`, la bonne reponse commence par le plus
  petit des trois nombres et aucun distracteur ne peut commencer par plus petit qu'elle : **le
  plancher structurel de ce raccourci est 50 %**, et le plafond que `RAN-5b` tolere a son seuil
  exact est 75 %. Le paquet est a 72,9 %, tout pres de ce que ma propre condition autorise.
  **Ce n'est pas un defaut du paquet, c'est la faiblesse de ma barre**, et je l'ecris pour que
  personne ne lise `RAN-5b` comme fermant ce raccourci.
- **Le raccourci de fin de liste gagne 93,8 %** et ne range pas : il compare, c'est-a-dire la moitie
  de ligne deja validee le 2026-08-12. **Il n'entre pas dans mon refus.** J'ai nomme ce raccourci
  moi-meme le 2026-08-30 et j'ai ecrit *"je ne la pose pas aujourd'hui"* ; en faire un motif
  aujourd'hui serait deplacer un poteau apres coup. C'est une condition nouvelle et datee, pour un
  paquet suivant.

## 6. Ce que change le fait d'auditer contre des conditions que j'ai amendees en cours de sprint

C'est un regime que je n'avais jamais eu : `ETA-4b`, `RAN-4b` et `RAN-5b` ont ete ecrites le
2026-08-30, apres que `marc-dev` a trouve que `ETA-4` et `ETA-7` s'excluaient mathematiquement, et
donc **en connaissance d'un contenu reel**. Ce que j'en dis, en trois points.

**1. Cela n'a rien change a la severite du controle, et cela a deplace le lieu du defaut.** Les
trois conditions amendees sont tenues sur les trois paquets, sans exception et sans effort visible :
0 ecart sur 48 distracteurs `ETA-4b`, 0 ecart sur 48 distracteurs `RAN-4b`, `RAN-5b` a 13/24. **Les
deux refus portent tous les deux sur des conditions que je n'ai jamais amendees** - `RAN-7` et
`LIA-3` - et qui ont en commun d'etre les deux seules conditions de leur paquet qu'**aucun calcul
ne juge**. Le contenu ecrit contre une condition amendee est le contenu le plus conforme du sprint ;
le defaut est ailleurs, dans ce qui n'est pas calculable.

**2. Le risque reel du regime n'est pas la complaisance, c'est l'angle mort.** Amender une condition
en voyant un contenu reel donne une precision qu'une condition ecrite a l'aveugle n'a pas :
`ETA-4b` prevoyait un lot de 24 items a 8/8/8 avec la famille (a) partout, et c'est exactement ce
que le paquet livre. Mais **une condition amendee sur un contenu attire toute l'attention - la
mienne comprise - sur ce qu'elle regle**, et laisse dans l'ombre les conditions voisines qu'on
croit reglees parce qu'elles n'ont pas bouge. `RAN-7` n'a pas bouge de tout le sprint ; c'est elle
qui est en defaut sur cinq items, et une CI entierement verte l'a laissee passer.

**3. Ce que je m'impose a partir d'aujourd'hui, en plus des quatre etapes du 2026-08-30.** Quand
j'amende une condition en cours de sprint, **j'audite en premier les conditions du meme paquet que
je n'ai PAS amendees**, avant celles que j'ai amendees. L'ordre inverse est celui que j'ai suivi
aujourd'hui, et il m'a fait passer un temps disproportionne a verifier ce qui allait tenir.

**Ce que le regime ne change pas** : ma barre. `ETA-7` a ete tenue telle quelle, seuil compris,
comme je l'avais annonce en amendant `ETA-4`. Aucune de mes conditions n'a ete assouplie a
l'audit pour laisser passer un contenu.

## 7. Sur ma capacite

**Quatre paquets pour une borne de trois. Je l'ai fait, et je dis ou.**

- La condition d'**au plus un paquet de type `COMP-2` par sprint est tenue** : `S23-LIA` est le
  seul, et c'est la ou j'ai mis le plus de temps - 40 distracteurs juges un par un contre leur
  relation declaree et leur motif de faussete declare.
- Le reaudit de `cm1-problemes-durees` etait bien le moins cher des quatre, parce que **cinq
  reserves nommees se recomptent au lieu de s'etablir**. Il ne pouvait pas faire avancer la
  couverture, et il ne l'a pas fait.
- **Ce que le quatrieme paquet a coute** : deux des trois refus que je me suis faits a moi-meme en
  section 3 sortent du reaudit, pas des audits neufs. Je n'aurais pas trouve le faux positif de mon
  instrument sans lui. **Je ne demande pas a relever ma borne** - un sprint n'est pas une mesure -
  mais le quatrieme paquet ne m'a pas fait deborder ici, et je le dis plutot que d'invoquer la
  borne par principe.
- **Ou mon jugement est le plus mince, et je le nomme** : la reserve B de `S23-ETA`, la plausibilite
  de la famille (c). Distinguer *"perd"* de *"distribue"* comme melecture d'enfant est un jugement
  que je rends seule, sans mesure derriere, et sur lequel un autre professeur pourrait trancher
  autrement. C'est pour cela qu'elle est une reserve non bloquante et non un motif.

## 8. Ce que je route

| Suite | Destinataire | Ce que je demande |
|---|---|---|
| **`S23-ETA` : Done prononce, la ligne peut passer `couvert-valide` sans mention partielle** | `camille-po` | Ecrire la matrice. Ce n'est pas moi qui l'ecris |
| **`S23-RAN` : refus sur `RAN-7`, cinq items nommes, critere de levee ecrit** | `camille-po`, par `sofia-sm` | Backlog. Le contenu n'est pas a reecrire, seule l'affectation des indices l'est |
| **`S23-LIA` : refus sur `LIA-3`, deux distracteurs a remplacer sur 40** | `camille-po`, par `sofia-sm` | Backlog. Neuf conditions sur dix sont tenues, le paquet n'est pas a refaire |
| **`cm1-problemes-durees` : quatre reserves levees, reserve D ouverte avec son critere** | `camille-po` | Fermer quatre stories, en garder une |
| **Trois conditions nouvelles et datees du 2026-08-30** : raccourci de fin de liste sur les listes rangees, indice de ponctuation sur les connecteurs a `___` median, critere de levee de la reserve D | `camille-po` | Elles ne font partie d'aucun refus. Pour des paquets suivants |
| **`RAN-7`, `LIA-3` et la plausibilite de la famille (c) restent hors de tout garde-fou** | `yanis-qa`, `theo-archi` | Ne pas les compter comme couvertes. Les deux refus du sprint portent sur les deux premieres, et la CI etait verte |
| **Mon instrument de signes orphelins rend un faux positif sur les textes a espace insecable** | `yanis-qa`, `theo-archi` | Je decris le symptome, je ne corrige pas l'outil. Chiffre reel du catalogue : 14, pas 17 |
| **Le `relecteur-pedagogique` n'est pas passe sur ce sprint** | `sofia-sm` | L'orthographe, l'accentuation et la ponctuation de ces trois paquets ne sont pas jugees par moi (`DEC-0056`). Mes deux Done n'en disent rien |
