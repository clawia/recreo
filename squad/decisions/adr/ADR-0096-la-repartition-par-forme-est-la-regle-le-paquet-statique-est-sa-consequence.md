# ADR-0096 - Encoder `ADD-CE1-2` comme un garde-fou derive de la forme des items, et servir le premier paquet CE1 par un paquet statique plutot qu'en etendant le seul generateur du depot

- **Statut** : Accepte, sa decision 1 amendee le 2026-10-01 (s45) par `ADR-0103`
- **Date** : 2026-09-21
- **Auteur** : theo-archi
- **Sprint** : s37
- **Route par** : `nadia-prof`, arbitrage du 2026-09-21
  (`squad/conformite/ARBITRAGE-s37-operande-nul-et-forme-du-tirage-a-l-echelle-100.md`), sections 6
  et 7 - *"ADD-CE1-1 et ADD-CE1-2 sont automatisables sur le couple (a, b) ... Je dis la propriete,
  la forme du test lui appartient"* et *"Choisir entre un mecanisme generatif tenant ADD-CE1-2 et un
  paquet statique de type question : theo-archi"*.

## Contexte

`nadia-prof` a tranche les deux questions que mon avis de faisabilite de s37 lui routait. `ADD-CE1-1`
plafonne l'operande nul a 5 % sans plancher ; `ADD-CE1-2` fixe une **table de repartition par
forme** en huit cases ordonnees, et **refuse explicitement le chemin a zero ligne de Java** : le
generateur inchange a `sommeMaximale = 100` echoue trois de ses huit bandes.

Elle laisse le mecanisme ouvert entre deux voies et n'en prefere aucune. Deux choses sont donc a
decider, et elles ne sont pas de meme nature :

1. **Comment `ADD-CE1-2` cesse d'etre tenue a la main.** Elle a ecrit que, tant qu'aucun garde-fou
   ne l'execute, c'est elle qui la tient en comptant. C'est la question qui compte : une clause
   pedagogique chiffree qu'un automate ne verifie pas est une clause qui tiendra le jour de
   l'audit et derivera au suivant.
2. **Par quel moyen le premier paquet la tient.** Statique ou generatif.

## Decision

**1. `ADD-CE1-2` et `ADD-CE1-1` sont encodees en un garde-fou de contenu, et son perimetre derive
de la forme des items, pas d'une liste de paquets.** Il juge tout paquet servi qui declare le
niveau `CE1` et dont **tous** les items sont une addition de deux entiers. Un second paquet
d'additions CE1 est garde le jour ou il entre ; un paquet CE1 de soustractions en sort de lui-meme,
et c'est correct - `ADD-CE1-2` porte sur les additions et le dit dans son intitule.

> **Amendement du 2026-10-01 (s45, `ADR-0103`)** : la population est restreinte aux paquets qui
> declarent `MAT-CE1-03` dans `lignesDeReferentiel`. Un paquet de `MAT-CE1-04` ne peut pas tenir la
> deuxieme bande, quel que soit son contenu ; il sort du perimetre. Le critere est inchange.

**2. Le premier paquet CE1 est un paquet statique de type `question`, et le generateur
`additions-bornees` n'est pas etendu.**

**3. La regle est la table, jamais le moyen.** Le garde-fou ne regarde pas le type du paquet : il
compte des couples. Un mecanisme generatif qui tiendrait les huit bandes passerait sans qu'une
ligne du garde-fou bouge. C'est ce qui rend la decision 2 reversible sans rien reecrire.

**Ce que cette decision ne fait pas** : elle n'interdit pas un generateur pour un paquet futur ;
elle ne touche pas `ACCEPTATION_OPERANDE_NUL_SUR`, deja conforme a `ADD-CE1-1` (`nadia-prof`,
section 8) ; elle ne prononce aucun Done pedagogique et ne dispense le paquet d'aucun des six
points que `nadia-prof` garde seule.

## Justification du choix 2, sur du materiau mesure et non sur une preference

**1. Le paquet statique est la norme du depot pour le calcul, pas l'exception.** Comptage du
2026-09-21 sur `adapters/adapter-content/src/main/resources/contenu/` : **21 paquets de
mathematiques servis, 20 de type `question`, 1 de type `additions-bornees`**. Le seul generateur du
depot est le premier paquet jamais ecrit (s00). Tous les paquets de calcul de CM1 - tables de
multiplication, multiplication posee, division posee, multiplications et divisions par 10 et 100 -
et tous les paquets de calcul de CP sauf celui-la sont statiques, servis et valides. Etendre le
generatif serait etendre l'exception ; ecrire un paquet statique est suivre ce que le depot fait.

**2. La reutilisabilite invoquee ne survit pas a la lecture de la table.** `ADD-CE1-2` s'intitule
*"dans un paquet d'additions de CE1 a `sommeMaximale = 100`"* et ses huit cases sont propres a
l'addition a cette echelle : complement a la dizaine, ajout d'un nombre rond, retenue aux unites.
Un second paquet CE1 - les soustractions de `MAT-CE1-03`, ou `MAT-CE1-04` - demanderait une autre
table. Ce qu'un mecanisme generatif rendrait reutilisable n'est donc pas *"les futurs paquets
CE1"*, c'est *"les futurs paquets qui voudraient exactement ces huit formes d'addition"*, et il y
en a un. C'est l'usage anticipe, pas l'usage constate.

**3. La conformite est exacte sur une liste, estimee sur un tirage.** `nadia-prof` l'ecrit en
posant sa clause : *"sur la liste des items elle-meme pour un paquet statique, ou la proportion est
exacte et non estimee"*. Sur un generateur, chaque verification repasse par le protocole de
`DEC-0007` - 200 000 tirages, graine fixee - et les bandes ne sont tenues que statistiquement.

**4. Le juge reste independant de ce qu'il juge, et c'est l'argument que je retiens en premier.**
La partition ordonnee doit etre encodee de toute facon, puisque le garde-fou en a besoin. Avec un
paquet statique, elle est ecrite **une fois, dans le test**, et rien en production ne la connait :
le garde-fou peut donc trouver une erreur. Avec un generateur qui tire la forme avant le couple, la
meme partition vit aussi en production, et le garde-fou soit la duplique - deux implementations qui
divergeront - soit la partage, auquel cas il verifie le tirage avec le code du tirage. Ce depot a
deja paye les deux.

**5. Le cout, cote par cote.** Statique : un fichier YAML, une entree de catalogue, l'extension
mecanique de l'inventaire du port entrant, le garde-fou. **Aucune ligne de Java de production.**
Generatif : une `ContentSource` neuve ou etendue portant la partition et un vecteur de huit poids,
les cles de paquet correspondantes et leur validation au chargement, le meme garde-fou, le
protocole statistique, et la question de la constante partagee entre paquets `additions-bornees`
rouverte - defaut que `nadia-prof` laisse explicitement endormi (section 8) et que la voie
statique laisse endormi aussi.

**Ce qui me ferait changer d'avis, ecrit maintenant plutot que decouvert plus tard** : un
**deuxieme** paquet demandant la meme table, ou un refus de `nadia-prof` portant sur la variete
d'une liste finie. La regle etant la table et non le moyen, ce changement se paierait alors en
production seule, sans toucher au garde-fou.

## Regle executable

Fichier :
`adapters/adapter-content/src/test/java/com/recreo/adapter/content/ChaquePaquetDAdditionsDeCe1TientSaRepartitionParFormeTest.java`

Ce n'est pas une regle ArchUnit, et le motif est le meme que pour la troisieme regle d'`ADR-0095` :
la population est du YAML et des couples d'entiers, pas du bytecode. Aucune propriete de cette
decision n'est encodable en ArchUnit, et rien d'autre qu'un garde-fou de contenu ne peut la tenir.

**Ce qu'elle interdit reellement.** Les huit bandes, bornes incluses, sur tout paquet du perimetre.
La partition est ordonnee - un couple appartient a la **premiere** case dont il remplit la
condition - et cet ordre est la clause : sans lui, `25 + 25` serait a la fois un double et une
somme ronde. L'`enum Forme` declare les huit cases **dans cet ordre**, chacune avec sa bande, et
`formeDe` est la traduction litterale du tableau de la section 4.3.

**Sept mises a l'epreuve**, dont la premiere est celle qui compte :

1. **Le tirage generatif inchange a l'echelle 100**, reconstitue avec les proportions que
   `nadia-prof` a mesurees - les siennes, pas des valeurs de mon invention - rougit **sur ses trois
   bandes et exactement trois** : somme <= 20, double, complement a la dizaine. C'est le chemin a
   zero ligne de Java, refuse par un automate au lieu d'etre refuse a l'audit.
2. Le paquet qui **ramene l'operande nul a la bande du CP** : la reponse la moins chere a mon
   propre constat de faisabilite, et celle que `ADD-CE1-1` interdit nommement.
3. Le paquet qui **bascule sur `MAT-CE1-04`** - l'addition avec retenue au-dela de 20 % : le
   glissement que la section 2 de l'arbitrage nomme, servir une ligne sous le nom d'une autre.
4. Le **paquet de CP reetiquete CE1** : le plafond de la deuxieme bande, que `nadia-prof` borne des
   deux cotes en disant pourquoi.
5. Une **population vide** (`ADR-0051`).
6. Le **jeu fautif commite** - deux paquets CE1, un conforme et un hors bandes - qui traverse la
   lecture disque, le recensement et l'analyse de l'enonce, et prouve que la regle **discrimine**.
7. Le **perimetre derive dans les deux sens** : un paquet CE1 de soustractions sort de la
   population et n'y est pas reduit en silence a ses additions.

Deux assertions de plus gardent le garde-fou lui-meme : le recensement trouve le paquet reellement
servi, et la partition est **exhaustive sur les 5 151 couples de l'echelle 100** par balayage
complet, pas sur un echantillon - une partition dont un cas tomberait dans le vide rendrait tous
les comptes faux en silence.

**Un defaut trouve en ecrivant cette regle, et il aurait rendu le garde-fou vert sur rien.**
`ADR-0067` (TYP-1) exige un blanc insecable devant le point d'interrogation d'un enonce francais :
tout enonce conforme en porte un. Le `\s` de Java ne couvre ni U+00A0 ni U+202F. Avec `\s` seul,
l'expression qui reconnait une addition **ne reconnaissait aucun enonce conforme** : la population
devenait vide et la regle serait passee au vert sur un paquet qu'elle n'aurait jamais lu. Mesure le
2026-09-21 sur l'arbre reel. C'est l'assertion de non-vacuite qui l'a rendu visible, et c'est
pourquoi la classe de blancs est nommee explicitement plutot que laissee a `\s`.

## Consequences

- `ADD-CE1-2` cesse d'etre tenue par le comptage de `nadia-prof`. C'est le gain principal, et il
  vaut pour tout paquet d'additions CE1 futur, pas seulement pour celui-ci.
- **Ce que le garde-fou ne dit pas**, et `nadia-prof` l'a ecrit avant moi (section 6) : que le
  paquet travaille reellement `MAT-CE1-03`, que l'ordre des manches soit bon, que l'indice aide,
  que chaque enonce soit exact. Six points restent dans sa seule main. La table rend le glissement
  plus difficile ; elle ne le rend pas impossible.
- **Ce qu'il ne garde pas non plus, et par refus explicite** : qu'une partie de dix manches
  contienne au moins une manche d'entree. Une repartition conforme laisse ce cas arriver environ
  une fois sur trois, `nadia-prof` l'a chiffre et s'est interdit d'en faire un motif de refus. Ce
  fichier ne le verifie donc pas, plutot que de durcir sa porte a sa place.
- Une liste finie de 32 items se repete sur des parties de dix manches, la ou un generateur ne se
  repete pas. `ADR-0023` et `ADR-0032` sont tenus, le troisieme critere de `S37-1` aussi ; la
  variete percue reste un jugement d'usage de `nadia-prof`.
- L'inventaire de `TexteAccentueExposeParLePortEntrantTest` s'etend de cinq chaines. La consigne du
  paquet CE1 y entre **en plus** de celle du CP, qui lui ressemble : la premiere porte le blanc
  insecable qu'`ADR-0067` exige d'un contenu neuf, la seconde est dans la table de dette, et la
  comparaison est une egalite. Le niveau `CE1` entre a l'inventaire plutot que d'elargir
  l'exemption enumeree qui couvre `CP` et `CM1` - l'inventaire est strictement plus fort et ne
  coute rien ici.

## Alternatives considerees

**1. Un mecanisme generatif qui tire la forme, puis le couple uniformement dans cette forme.**
C'est la conception que j'aurais retenue si j'avais choisi le generatif, et elle est simple : la
partition des 5 150 couples est calculee au constructeur, un vecteur de huit poids decide la case,
le couple est tire dedans, et les proportions valent les poids par construction. Ecartee sur les
cinq motifs ci-dessus, et sur le quatrieme en premier. Elle est ecrite ici pour qu'elle n'ait pas a
etre retrouvee le jour ou un second paquet la demandera.

**2. Recalibrer `ACCEPTATION_OPERANDE_NUL_SUR`.** Sans objet : `ADD-CE1-1` est deja tenue a 1,34 %,
et le plafond mesure de ce levier a l'echelle 100 est 3,86 %, sous la bande du CP. `nadia-prof`
interdit par ailleurs nommement de ramener le zero.

**3. Une table de repartition par bandes de sommes**, transposee de `DEC-0007` point 2. Ecartee par
`nadia-prof` elle-meme (section 4.1), et pour un motif technique qu'elle formule mieux que moi : a
l'echelle 100 la somme et la difficulte se decrochent, une telle table contraindrait la mauvaise
variable.

**4. Laisser `ADD-CE1-2` a l'audit.** Ecartee : c'est l'etat que `nadia-prof` decrit en section 6
et qu'elle route pour qu'il cesse. Une clause chiffree tenue par un comptage manuel tient le jour
de l'audit et derive au suivant.
