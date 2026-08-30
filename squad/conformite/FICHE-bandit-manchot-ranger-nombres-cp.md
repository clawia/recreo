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

---

### Done pedagogique - 2026-08-30, reaudit apres correctif (s23) - VALIDE

**Amendement date. Le refus du 2026-08-30 ci-dessus n'est pas reecrit et reste lisible en
entier** : un refus efface n'a aucune valeur de preuve. Ce bloc s'ajoute, il ne remplace rien.

**Correction d'un fait avant de m'en servir.** La demande de reaudit me presentait mon refus comme
date du **2026-08-31**. Il est date du **2026-08-30**, dans ce fichier comme dans le commit qui le
porte, et le lot de correction est du meme jour. `cm1-mots-de-liaison.yaml` porte lui aussi
"Correction du 2026-08-31" pour un commit du 2026-08-30. Ce n'est pas un detail de forme : une date
qui ne correspond a aucun commit rend la chronologie du depot illisible, et le depot est la seule
memoire partagee. Je le signale, je ne corrige pas le fichier d'un autre agent.

#### Ordre d'audit - la regle que je me suis imposee le 2026-08-30, appliquee ici

Je m'etais impose d'**auditer en premier les conditions que je n'ai pas amendees**, apres avoir
constate que mes deux refus du sprint portaient precisement sur les deux seules conditions qu'aucun
calcul ne juge et que je n'avais jamais amendees. Applique a ce reaudit : j'ai commence par
`RAN-7`, puis `RAN-1`, `RAN-2`, `RAN-3`, `RAN-6` et `RAN-8`, et fini par `RAN-4b` et `RAN-5b`.

**Ce que cela donne ici : rien de neuf, et c'est un resultat en soi.** Le correctif ne touche que
`RAN-7` ; les sept autres conditions sont intactes par construction. L'ordre n'a donc pas deplace
le lieu du defaut - il a seulement coute moins de temps qu'au premier audit, ou j'avais commence
par verifier ce qui allait tenir. **La regle se juge sur un audit neuf, pas sur un reaudit d'une
seule condition** ; je le dis pour ne pas lui creer un credit qu'elle n'a pas encore gagne.

#### Ce que le correctif a change, verifie ligne a ligne

**Cinq lignes `indice`, et rien d'autre.** *Methode : difference du paquet entre le commit qui porte
mon refus et l'arbre reaudite ; population : le fichier entier.* Aucun enonce, aucune bonne reponse,
aucun distracteur, aucun des six textes d'indice n'est reecrit. **La declaration de `marc-dev` est
exacte, et les cinq items touches sont exactement les cinq que j'avais nommes** : 16 et 22 pour le
defaut grave, 1, 13 et 19 pour le second.

#### `RAN-7` - motif de refus leve

**Mon critere de levee, mot pour mot** : "la methode que l'indice enonce, appliquee litteralement
aux trois nombres de l'item, doit designer la bonne reponse ou un sous-ensemble strict qui la
contient - jamais une proposition fausse presente a l'ecran, jamais les trois propositions a la
fois."

**Tenu sur les 24 items.** *Methode : pour chaque item, les six permutations du triplet sont
filtrees par la methode que son indice enonce, en lisant "les dizaines" comme un enfant de CP les
lit - le chiffre ecrit dans la colonne des dizaines, celui de 100 valant 0 ; le resultat est
intersecte avec les trois propositions reellement servies. Population : les 24 items.* Vingt-deux
items designent exactement la bonne reponse ; deux items en retiennent deux, la bonne comprise -
les items 3 et 9, indice "Cherche d'abord le plus petit des trois." **C'est le sous-ensemble strict
que ma condition autorise, et ces deux items etaient deja dans cet etat le 2026-08-30, ou je ne les
ai pas comptes en defaut. Je ne les compte pas davantage aujourd'hui.**

**Vu a l'ecran, pas deduit du fichier.** Dix parties completes, jouees en pressant chaque
proposition fausse avant la bonne, les 24 items rencontres. Sur les cinq items corriges :

| Item | Enonce | Indice servi apres l'echec | Ce que la methode donne |
|---|---|---|---|
| 16 | `48, 2, 100` | "Compare le nombre de chiffres de chaque nombre." | 2 a un chiffre, 48 en a deux, 100 en a trois : `2, 48, 100`, **la bonne reponse, entierement** |
| 22 | `37, 100, 16` | "Compare le nombre de chiffres de chaque nombre." | 100 est le seul a trois chiffres, donc dernier : des trois propositions a l'ecran, seule `16, 37, 100` finit par 100 |
| 1 | `66, 54, 59` | "Compare les dizaines, puis les unites si besoin." | dizaines 6 / 5 / 5, unites 4 / 9 : `54, 59, 66` |
| 13 | `94, 11, 56` | "Compare les dizaines, puis les unites si besoin." | dizaines 9 / 1 / 5 : `11, 56, 94` |
| 19 | `52, 91, 86` | "Compare les dizaines, puis les unites si besoin." | dizaines 5 / 9 / 8 : `52, 86, 91` |

**L'erreur factuelle a disparu de l'ecran.** Sur l'item 22, la sequence qui rendait le 2026-08-30
"Compare les dizaines, puis les unites si besoin." sur un triplet contenant 100 rend aujourd'hui
"Compare le nombre de chiffres de chaque nombre.", et l'enfant qui applique litteralement ce qu'on
vient de lui dire tombe sur la bonne reponse au lieu du second distracteur.

**Ce que le correctif fait mieux que ce que je demandais** : sur les items 16 et 22, 100 est le seul
nombre du paquet a trois chiffres, et l'indice de comptage de chiffres est precisement la methode
qui le traite bien. L'appariement n'evite pas seulement le defaut, il choisit la methode utile.

#### Les sept autres conditions, recomptees et non reprises

*Population : les 24 items, recomptes sur l'arbre reaudite, jamais repris de mon bloc du
2026-08-30.*

| Condition | Verdict | Mesure |
|---|---|---|
| `RAN-1` | **tenue** | 24 bonnes reponses egales au triplet croissant ; 72 propositions permutations du triplet |
| `RAN-2` | **tenue** | Entiers de 0 a 100, trois distincts par item sur les 24 |
| `RAN-3` | **tenue** | Proposition la plus longue : **11 caracteres**, borne 12 |
| `RAN-4` / `RAN-4b` | **tenues** | 48 distracteurs recalcules, 0 ecart ; unites deux a deux distinctes sur les 24 |
| `RAN-5b` | **tenue** | **13 / 24**, plancher 12 - chiffre identique au 2026-08-30, le correctif ne touche aucune proposition |
| `RAN-6` | **tenue** | **9 / 24**, plancher 8 - idem |
| `RAN-7` | **tenue** | Voir ci-dessus. Neuf mots au plus par indice, aucun indice ne recopie sa liste |
| `RAN-8` | **tenue** | 24 items, 24 triplets deux a deux distincts |

#### Les deux raccourcis mesures le 2026-08-30 : inchanges, et toujours hors de ce verdict

*Meme methode, meme population qu'au 2026-08-30 : les 24 items, egalites entre propositions
comptees uniformement.* Raccourci "la liste dont le premier nombre est le plus petit" : **72,9 %**
(17,5 / 24). Raccourci "la liste dont le dernier nombre est le plus grand" : **93,8 %** (22,5 / 24).
Le correctif ne touche aucune proposition, les deux chiffres sont donc identiques a ceux de mon
refus ; je les refais et je les republie, parce qu'un chiffre qu'on ne refait pas n'est plus une
mesure.

**Je n'en fais pas une reserve sur ce paquet, et je dis pourquoi.** J'ai nomme moi-meme le raccourci
de fin de liste le 2026-08-30 en ecrivant "je ne la pose pas aujourd'hui". En faire aujourd'hui une
reserve de ce paquet - meme non bloquante - serait appliquer apres coup une condition a un contenu
ecrit sans elle. **Ma barre du 2026-08-30 est celle contre laquelle ce paquet a ete ecrit, et c'est
celle contre laquelle je le juge.** La condition reste nouvelle, datee, et destinee a un paquet
suivant.

#### `MNC-1`

**`MNC-1` s'applique a ce Done, et ce Done ne le couvre pas.** Rien ne garantit ici que l'enfant
voie, sans faire defiler la page, un element quelconque du jeu, y compris le retour qui suit sa
reponse - verdict, indice, proposition. Cette propriete est sortie de la porte pedagogique par
arbitrage de l'utilisatrice du 2026-08-28 (`DEC-0053`) ; sa dette est ouverte, croissante et
assumee. **Aucune mesure ne l'a instruite sur ce paquet**, ni au premier audit ni a celui-ci.

#### Ce que ce Done ne dit pas

**Le `relecteur-pedagogique` n'est pas passe sur ce sprint.** L'orthographe, l'accentuation et la
ponctuation des 24 enonces, des 72 propositions et des six indices ne sont pas jugees par moi
(`DEC-0002` amende le 2026-08-04, `DEC-0056`). Ce Done n'en dit rien.

#### Ce sur quoi je reste la seule garde, apres le correctif

- **Le jugement de niveau** de ce que l'enfant lit.
- **`RAN-7` n'est plus entierement dans ma seule main.** C'est la premiere fois qu'une de mes
  conditions de jugement recoit un garde-fou. Ce qu'il couvre et ce qu'il ne couvre pas est ecrit
  ci-dessous, parce qu'une couverture surestimee est pire qu'une absence de couverture.

#### Mon avis sur le test qui encode `RAN-7`

Test vise : `PaquetCpRangerNombresTest#chaqueIndiceEstChoisiPourSonItemJamaisDistribueParUnCycle`.

**Il dit ce que ma condition dit, sur tout le domaine que ce paquet peut prendre - et il ne le dit
pas de la maniere dont je l'ai ecrite. Les deux points comptent.**

**Ce que j'ai verifie, et par quelle methode.** J'ai enumere **tous les triplets d'entiers de 0 a
100 admissibles** par `RAN-2` (trois entiers distincts de 0 a 100), par `RAN-4b` (les trois chiffres
des unites deux a deux distincts) et par `RAN-4` (les trois propositions distinctes) : **81 180
triplets**. Pour chacun, et pour chacun des six textes d'indice du paquet - **487 080 couples au
total** - j'ai compare deux verdicts : celui des deux garde-fous du test, et celui de ma condition
telle qu'elle est ecrite dans mon refus. **Zero desaccord, dans les deux sens.** Sur le domaine que
ce paquet peut prendre, le test n'est pas une approximation de ma condition : il lui est equivalent.

*Correction de mon propre instrument, faite avant de m'en servir.* Ma premiere enumeration lisait
"les dizaines" comme le **nombre de dizaines** - 10 pour 100 - et annoncait 1 980 desaccords. C'est
faux : ma condition, et le defaut que j'ai vu en jouant le 2026-08-30, reposent sur le **chiffre
ecrit dans la colonne des dizaines**, qui vaut 0 pour 100 et qui est ce qu'un enfant de CP lit.
Instrument corrige, mesure refaite : 0 desaccord. **Je n'aurais pas du publier le premier chiffre,
et je dis lequel etait faux plutot que de ne montrer que le second.**

**Ce que le test ne dit pas, et que personne ne doit croire couvert.**

1. **Il encode mes deux symptomes, pas ma propriete.** Ma condition parle de ce que la methode
   *designe* parmi les propositions servies ; le test parle de deux formes de triplet interdites a
   deux textes d'indice. L'equivalence est un **resultat**, pas une lecture : elle repose sur
   `RAN-1`, `RAN-4` et `RAN-4b`, gardees par d'autres tests du meme fichier. **Le jour ou les
   familles de distracteurs changent, le garde-fou de `RAN-7` cesse de garder `RAN-7` sans qu'aucun
   test ne rougisse.** C'est un couplage muet, et c'est le seul point qui m'inquiete.
2. **Il n'a pas de branche par defaut.** Les deux contraintes sont posees par des branches
   conditionnelles sur le debut de six textes d'indice connus, sans branche finale qui echoue.
   **Un septieme texte d'indice, ou l'un des six reformule ("Regarde les dizaines."), traverse le
   test sans qu'aucune assertion ne s'applique**, et le test reste vert. Ma condition, elle,
   s'applique a tout indice.
3. **Sa Javadoc justifie les quatre autres textes par une raison qui n'est pas la bonne.** Elle dit
   qu'ils "ne decrivent aucune position de chiffre et restent valides sur tout triplet". Ce qui les
   rend surs n'est pas cela : c'est que sous `RAN-1` la bonne reponse commence toujours par le plus
   petit et finit toujours par le plus grand, et que la famille (a) commence par le plus grand.
   **Le fait est juste, le motif ecrit ne l'est pas** - et un motif faux dans un garde-fou est ce
   qui fait qu'on ne voit pas venir le jour ou il cesse de tenir.

**Ce que j'en conclus.** Le test **ne rend pas un verdict etranger a sa propriete** - c'etait le
risque nomme, et il n'est pas realise. La regle encodee est bien la mienne, et je n'ai pas eu a la
deplacer pour qu'elle passe. **Je le garde.** Mais il couvre `RAN-7` sur ce paquet-ci et sous ces
familles-la, pas `RAN-7` en general ; les points 1 et 2 sont a traiter avant de compter la
propriete comme fermee.

#### Ce que je route

| Suite | Destinataire | Ce que je demande |
|---|---|---|
| **`RAN-7` levee. Done pedagogique prononce, sans reserve. La ligne `mathematiques / CP / Nombres et calculs / Comparer et ranger des nombres jusqu'a 100` peut passer a `couvert-valide`** | `camille-po` | Ecrire la matrice. Ce n'est pas moi qui l'ecris. La moitie "comparer" etait deja servie ; ce paquet ferme la moitie "ranger" |
| **Le test de `RAN-7` : pas de branche par defaut, et couplage muet a `RAN-4` / `RAN-4b`** | `yanis-qa`, `theo-archi` | Deux points, pas un refus du test. Une branche finale qui echoue sur un texte d'indice inconnu ; et dire dans le test que sa suffisance repose sur `RAN-4` / `RAN-4b` |
| **La Javadoc du test justifie les quatre indices surs par un motif inexact** | `yanis-qa` | Le fait est juste, la raison ecrite ne l'est pas. Ce n'est pas de la Javadoc de style : c'est ce qui fera lire le garde-fou de travers |
| **Le raccourci de fin de liste, 93,8 % remesures** | `camille-po` | Condition **nouvelle et datee du 2026-08-30**, pour un paquet suivant. Je refuse d'en faire une reserve de ce paquet-ci |
| **Une date "2026-08-31" ecrite pour des commits du 2026-08-30** | `sofia-sm`, `relecteur-pedagogique` | Dans `cm1-mots-de-liaison.yaml` et dans la demande de reaudit. Le depot est la seule memoire partagee : sa chronologie doit etre celle des commits |
| **`MNC-1` non couvert, et le `relecteur-pedagogique` n'est pas passe** | `sofia-sm` | Ce Done ne dit rien de la hauteur utile ni de l'orthographe |
