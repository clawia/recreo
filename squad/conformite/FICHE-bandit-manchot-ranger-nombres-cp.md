# Fiche de conformite - bandit-manchot-ranger-nombres-cp

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du catalogue).
- **Contenu** : `cp-ranger-nombres`, 24 items, contenu neuf, story `S23-RAN`, ecrit par `marc-dev`.
- **Arbitrage prealable de `nadia-prof`** : rendu le 2026-08-30,
  `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md`,
  section 1 (`RAN-1` a `RAN-8`) et section 12.2 (amendements `RAN-4b` et `RAN-5b` du 2026-08-30,
  qui remplacent respectivement une clause de `RAN-4` et l'integralite de `RAN-5`).
- **Fiche ouverte le 2026-08-30 par `nadia-prof`**, faute de fiche existante au moment de l'audit.
  La section "criteres pedagogiques attendus" appartient a `camille-po` et n'est pas ecrite ici.

## Competence visee

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| mathematiques | CP (cycle 2) | Nombres et calculs | Comparer et ranger des nombres jusqu'a 100 (`<`, `>`, `=`) - moitie "ranger" | `squad/programme/mathematiques/competences-cp.md`, ligne 10 |

La moitie "comparer" est validee depuis le 2026-08-12
(`FICHE-bandit-manchot-comparer-nombres-cp.md`). Ce paquet vise la moitie manquante, "ordonner une
suite de trois nombres ou plus" (`DEC-0019`).

## Bloc de validation

### Done pedagogique - 2026-08-30 (s23) - REFUSE

**Audit conduit en jouant**, application lancee depuis le fat jar construit sur l'arbre du
worktree, sur le JDK 21 de la toolchain declaree. Six parties completes de dix manches, jouees en
se trompant volontairement sur chaque manche pour voir l'indice, puis en allant jusqu'a la
revelation sur certaines. Ce n'est pas un audit degrade.

#### Ce que j'ai verifie, et qui tient

Recompte par moi-meme sur la population entiere des 24 items du paquet, jamais sur un echantillon
et jamais en recopiant le compte de l'auteur :

| Condition | Verdict | Mesure, population : les 24 items |
|---|---|---|
| `RAN-1` | **tenue** | Les 24 bonnes reponses sont le triplet range en ordre croissant ; les 72 propositions sont des permutations des trois nombres de leur enonce. La consigne du catalogue nomme un seul sens et ne change jamais |
| `RAN-2` | **tenue** | Entiers de 0 a 100, deux a deux distincts sur les 24 items |
| `RAN-3` | **tenue** | Proposition la plus longue du paquet : **11 caracteres**, sous la borne de 12 |
| `RAN-4` / `RAN-4b` | **tenues** | Les 48 distracteurs recalcules depuis les trois nombres : **0 ecart**. Chaque item declare exactement (a) et (b). Aucun triplet ne partage un chiffre des unites (clause `RAN-4b`), aucune collision entre (a), (b) et la bonne reponse |
| `RAN-5b` | **tenue** | **13 items sur 24** ont au moins un distracteur qui commence par le meme nombre que la bonne reponse ; le plancher est `arrondi superieur(24/2)` = 12 |
| `RAN-6` | **tenue** | **9 items sur 24** opposent deux nombres de la meme dizaine ; le plancher est `arrondi inferieur(24/3)` = 8 |
| `RAN-8` | **tenue** | 24 items, 24 triplets deux a deux distincts |

**Les trois chiffres declares par `marc-dev` sont exacts** : 13 sur 24 pour `RAN-5b`, 9 sur 24 pour
`RAN-6`, 11 caracteres au plus. Je les ai recomptes, je ne les ai pas repris.

**Ce que je dis du regime de cet audit**, parce qu'il est neuf : j'auditais pour la premiere fois
du contenu ecrit contre des conditions que j'avais amendees en cours de sprint, en connaissance
d'un contenu reel (`ETA-4b`, `RAN-4b`, `RAN-5b`, tous trois du 2026-08-30). **Cela n'a rien change
a la severite du controle et cela a deplace le lieu du defaut.** Les conditions amendees sont
toutes tenues, sans exception et sans effort visible ; le motif de refus ci-dessous porte sur
`RAN-7`, la seule condition de ce paquet que je n'ai jamais amendee et la seule qu'aucun calcul ne
juge.

#### Motif de refus - `RAN-7` : l'indice ne donne pas la methode de son item

`RAN-7` dit : *"Indice. Il donne la methode, jamais la liste."* Le paquet sert **six indices
distincts affectes par un cycle de six, independamment du contenu de l'item**. Aucun indice n'a
ete choisi pour le triplet qu'il accompagne, et cela se voit a l'ecran.

**Defaut 1, le grave - l'indice conduit l'enfant sur une proposition fausse presente a l'ecran.
Deux items sur 24.**

L'indice *"Compare les dizaines, puis les unites si besoin."* est servi sur les items dont l'enonce
contient **100**. Le chiffre des dizaines de 100 est **0**.

| Item | Enonce | Bonne reponse | La methode de l'indice donne | Cette liste est... |
|---|---|---|---|---|
| 16 | `48, 2, 100` | `2, 48, 100` | dizaines 4 / 0 / 0, puis unites 8 / 2 / 0 : `100, 2, 48` | **le second distracteur de l'item, present a l'ecran** |
| 22 | `37, 100, 16` | `16, 37, 100` | dizaines 3 / 0 / 1 : `100, 16, 37` | **le second distracteur de l'item, present a l'ecran** |

**Vu en jouant, pas deduit du fichier.** Sur l'item 22, apres une erreur, l'ecran a rendu les trois
propositions `100, 37, 16`, `16, 37, 100`, `100, 16, 37` et, sous elles, l'indice *"Compare les
dizaines, puis les unites si besoin."* Un enfant de CP qui applique exactement ce qu'on vient de
lui dire de faire tombe sur `100, 16, 37` et se trompe une seconde fois.

C'est une **erreur factuelle dans un contenu scolaire** : l'indice est servi apres un echec, il est
ce que l'enfant bloque recoit pour avancer, et il fabrique ici l'erreur qu'il devait empecher. Ce
motif suffit seul.

**Defaut 2 - l'indice ne dit rien sur son item. Trois items sur 24.**

L'indice *"Compare le nombre de chiffres de chaque nombre."* est servi sur des triplets dont les
trois nombres ont le meme nombre de chiffres, ou la methode ne separe donc rien :

| Item | Enonce | Nombres de chiffres |
|---|---|---|
| 1 | `66, 54, 59` | 2, 2, 2 |
| 13 | `94, 11, 56` | 2, 2, 2 |
| 19 | `52, 91, 86` | 2, 2, 2 |

**Vu en jouant** : sur l'item 1, je me suis trompee deux fois de suite et j'ai recu deux fois le
meme indice, qui ne m'apprenait rien des deux fois. Un enfant bloque doit pouvoir avancer ; ici il
recoit une methode qui ne mene nulle part.

Le quatrieme item du cycle, l'item 7 (`8, 3, 84`), n'est pas compte dans ce defaut : la methode y
separe 84 des deux autres, meme si elle ne separe pas 8 de 3.

#### Ce qui rendrait ce refus levable, sans avoir a me le redemander

Que **chaque indice soit choisi pour son item** et non par un rang dans un cycle. Critere
verifiable par un tiers, sur les 24 items : la methode que l'indice enonce, appliquee litteralement
aux trois nombres de l'item, doit designer la bonne reponse ou un sous-ensemble strict qui la
contient - jamais une proposition fausse presente a l'ecran, jamais les trois propositions a la
fois. Les six indices deja ecrits sont bons ; c'est leur affectation qui ne l'est pas. Les cinq
items en defaut sont nommes ci-dessus.

#### Ce sur quoi je reste la seule garde sur ce paquet, et qu'aucun garde-fou ne voit

- **`RAN-7`**, integralement. C'est la condition sur laquelle ce refus porte, et aucun test du
  depot ne l'aurait vue : les six indices sont bien formes, courts, et ne contiennent aucune liste.
  Ce qui manque n'est pas dans l'indice, il est dans **l'appariement** entre l'indice et son item.
- **Le jugement de niveau** de ce que l'enfant lit.

#### Constat neuf et date du 2026-08-30, non bloquant, qui n'entre pas dans ce refus

**Le raccourci "prendre la liste dont le dernier nombre est le plus grand" gagne 93,8 % de ce
paquet.** *Mesure sur la population entiere des 24 items, egalites entre propositions comptees
uniformement : 22,5 / 24.* Ce raccourci ne range pas, il compare - c'est-a-dire la moitie de ligne
deja validee le 2026-08-12.

**Il n'entre pas dans mon motif de refus et je dis pourquoi.** J'ai nomme ce raccourci moi-meme le
2026-08-30 (section 12.2.1 de mon arbitrage) et j'ai ecrit noir sur blanc : *"une condition sur la
fin des listes serait un ajout legitime en plus de `RAN-5b` ; je ne la pose pas aujourd'hui."* En
faire aujourd'hui un motif serait deplacer un poteau apres coup, et je me l'interdis. C'est une
**condition nouvelle et datee**, a poser sur un paquet suivant, pas un durcissement de `RAN-5b`.

**Second constat de la meme famille, sur ma propre barre.** Le raccourci que `RAN-5b` vise, lui -
*"prendre la liste dont le premier nombre est le plus petit"* - gagne **72,9 % de ce paquet**
*(meme population, memes egalites uniformes : 17,5 / 24)*. Ce n'est pas un defaut du paquet : sous
`RAN-1`, la bonne reponse commence par le plus petit des trois nombres et aucun distracteur ne peut
commencer par plus petit qu'elle, donc **le plancher structurel de ce raccourci est 50 %** et le
plafond que `RAN-5b` autorise a son seuil exact est 75 %. Le paquet est a 72,9 %, c'est-a-dire tres
pres du maximum que ma propre condition tolere. **Ma barre est tenue et ma barre est basse** ; je
l'ecris pour que personne ne lise `RAN-5b` comme fermant ce raccourci, alors qu'elle ne fait au
mieux que le ramener a un pile ou face sur la moitie des items.

#### `MNC-1`

**Sans objet ici** : `MNC-1` porte sur tout Done pedagogique prononce, `VALIDE` ou `VALIDE SOUS
RESERVE`. **Un refus n'est pas un Done.** Aucune ligne de `MATRICE-couverture.md` ne bouge sur ce
paquet ; la ligne `mathematiques | CP | Nombres et calculs | Comparer et ranger des nombres jusqu'a
100` reste `en cours`.

#### Ce que je route

| Suite | Destinataire | Ce que je demande |
|---|---|---|
| **Refus sur `RAN-7`, cinq items nommes (16 et 22 pour le defaut grave, 1, 13 et 19 pour le second)** | `camille-po`, par `sofia-sm` | En faire du backlog. Le critere de levee est ecrit ci-dessus et applicable sans moi |
| **`RAN-1` a `RAN-6` et `RAN-8` sont tenues, recomptees sur les 24 items** | `camille-po` | Le contenu n'est pas a reecrire : seule l'affectation des indices l'est |
| **Le raccourci de fin de liste, 93,8 % mesures** | `camille-po` | Condition nouvelle et datee du 2026-08-30, pour un paquet suivant. Elle ne fait pas partie de ce refus |
| **`RAN-5b` tenue, et son plancher structurel est 50 %** | `camille-po`, `theo-archi` | Signalement. Ne pas lire `RAN-5b` comme fermant le raccourci du premier nombre |
| **`RAN-7` n'est couverte par aucun garde-fou et ne l'etait pas** | `yanis-qa`, `theo-archi` | Ne pas la compter comme couverte. Une CI verte a laisse passer les cinq items |
