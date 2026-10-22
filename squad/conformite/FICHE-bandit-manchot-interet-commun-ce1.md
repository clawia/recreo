# Fiche de conformite - bandit-manchot-interet-commun-ce1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-emc-interet-commun.yaml` (24 items, type `question`,
  aucun texte support), paquet ecrit dans le lot.
- **Entree de catalogue** : `bandit-manchot-interet-commun-ce1`, titre servi *"Pour un seul ou pour tous"*, consigne servie *"Lis
  bien, puis choisis la bonne réponse."*, `nombreDeManches: 10`.
- **Ligne declaree** : `EMC-CE1-04`. **Story** : `S57-BR-EMC-CE1-04`.
- **Competence programme visee** : `squad/programme/enseignement-moral-et-civique/competences-ce1.md`, `EMC-CE1-04`,
  *"Distinguer, dans une situation simple, ce qui sert l'intérêt d'une seule personne de ce qui sert l'intérêt de tous."*
  Plafond `couvert-valide`.
- **Conditions opposables** : `INT-CE1-1`, `INT-CE1-2`, `NRC-CE1-1` et les transverses de la section 2
  (`ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md`, 2 et 4.4) ; `EMC-1` a `EMC-7` ; point d'audit commun
  de `FICHE-bandit-manchot-regle-sanction-ce1.md` (aucune forme ne donne la reponse, aucun distracteur absurde).
- **Statut global** : **REFUSE**, prononce le 2026-10-22 par `nadia-prof` (s57, palier 4, instance A'), sur `cbfb152e`. Fiche
  ouverte le 2026-10-22 par `nadia-prof`.
- **Derniere mise a jour** : 2026-10-22, premier passage.

---

## Lecture complete

**lecture complete : ce1-emc-interet-commun, 24 items lus** - 2026-10-22, ligne ecrite a 23:51 (UTC+02:00), lu par `date`, sur
la branche `s57-sofia-emc-ce1-04` a `cbfb152e` ; code et contenu a `e85a93fe` (`git diff --name-only e85a93fe cbfb152e` ne nomme
que le rapport du lot). `grep -c "bonneReponse:"` rend 24. Lus : les 24 enonces, bonnes reponses, 48 distracteurs, 24 indices,
l'en-tete (portees, familles), l'entree de catalogue, `rapport-sofia-dev-palier-4-lp6.md` sections 2, 6 a 8. Paquets voisins relus
sur leurs enonces et bonnes reponses : `ce1-emc-biens-communs` (24), `cp-emc-regles-de-vie` (11), `cp-emc-regle-souhait-loi` (11),
`cp-emc-droits-enfant` (12).

**Ma recommandation** de `arbitrage-nadia-palier-4.md` 3.3 (ne pas prendre ce lot au palier 4) n'entre pas dans ce verdict : le
lot a ete ouvert par l'utilisatrice, je juge le contenu tel que livre.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

### Verdict du 2026-10-22 (`nadia-prof`, s57, palier 4, instance A') : **REFUSE**

**Commit joue** : `cbfb152e` (contenu et code de `e85a93fe`), jar construit dans mon worktree detache
`recreo-s57-nadia-p4-lp6-jeu` a ce commit (`-pl bootstrap -am package -DskipTests`, mesure partielle, aucun `verify`), port
18512, *"Tomcat started on port 18512"* et PID lus dans mon journal, arrete par PID. Jeu par moi, requetes HTTP : 12 parties de
10 manches, 24 enonces servis sur 24, aucun enonce inconnu du paquet. Huit parties en me trompant d'abord a chaque manche (le
nombre d'eleves, puis une formule) : indice lu a l'ecran a chaque erreur. Trois parties jouees comme un enfant qui n'a pas
compris la question et suit une seule regle de surface (voir M1) ; une partie en repondant toujours *pour toute la classe* (4
sur 10). Dans une treizieme partie, une manche jouee a trois erreurs : revelation *"Ce n'est pas ça. Appuie sur la bonne
réponse : pour toute la classe"*, indice maintenu. **Non fait par moi** : le releve d'ecran a 744 x
551 (`ines-design`).

**Motifs du refus, chacun actionnable sans me redemander :**

**M1 - La forme donne la reponse, et la reponse est celle de `ce1-emc-biens-communs`** (point d'audit commun ; `NRC-CE1-1`,
seconde clause : *"chaque bonne reponse mobilise le contenu propre a la ligne de CE1 que sa frontiere nomme"*). Dans les 24
items, la scene porte un possessif (*son*, *sa*, *ses*) si et seulement si la bonne reponse est *pour une seule personne* :
11 sur 11 d'un cote, 0 sur 13 de l'autre. Joue : en repondant *pour une seule personne* quand l'enonce contient *son*, *sa* ou
*ses* et *pour toute la classe* sinon, sans lire la scene, **30 bonnes reponses du premier coup sur 30 manches** (parties 9 a
11). La regle est de plus celle que le test du paquet impose (portee *un* : un possessif ; portee *tous* : un objet partage,
jamais un possessif). Ce que l'enfant fait alors, c'est dire a qui est l'objet - la tache 1 de `ce1-emc-biens-communs`
(*"Quel objet est à une seule personne ?"*, *"la trousse de Lina"*), ligne `EMC-CE1-01`. L'en-tete du paquet ecrit l'inverse
(*"ici, l'action et celui qu'elle sert, jamais l'objet ni son appartenance"*) ; les 24 scenes le contredisent. La ligne
`EMC-CE1-04` n'est travaillee que si le proprietaire de l'objet et celui que l'action sert peuvent differer.
Ce qu'il faut : dans chaque portee, au moins un tiers des items ou l'appartenance de l'objet ne decide pas - un objet ou un lieu
partage qui ne sert qu'a un personnage (par exemple *"Aya prend un livre de la bibliothèque pour le lire."*, *"Enzo boit au
robinet de la cour."*), et un geste fait avec ce qui est a un personnage et qui sert toute la classe (par exemple *"Samir
apporte sa plante pour décorer la classe."*). Toujours sans mise en balance ni blame (`INT-CE1-1`) : prendre un livre pour soi
n'est pas dit mal. **Symptome technique, cause a `theo-archi` et a l'autrice** : la liste fermee du test qui lie possessif et
portee *un* rougira sur ces items ; c'est elle qui enferme le paquet dans la forme refusee ici.

**M2 - Quatre items de portee *tous* se resolvent avec le CP** (`NRC-CE1-1`, premiere clause). Items 5 (*Tom range les ballons
dans le panier de la cour*), 7 (*Omar remet les livres sur l'étagère*), 9 (*Lina remet des feutres dans la boîte*) et 19
(*Samir remet les chaises en place*) : ranger et remettre a sa place le materiel commun, et dire que cela sert a tous, est la
reponse de `cp-emc-regles-de-vie` (*"Pourquoi range-t-on le matériel après l'avoir utilisé ?"*, *"pour que chacun retrouve
ses affaires"* ; *"les remettre à leur place"*). C'est le geste que `BCO-CE1-1` avait deja rendu au CP. Ce qu'il faut : quatre
gestes que le paquet de CP n'enseigne pas (preparer, nettoyer, afficher, transmettre une information, comme les items 1, 3, 17,
21 le font deja).

**Ce qui tient** (verifie, a ne pas defaire au rejeu) :

- `INT-CE1-2` : les deux formules dans chaque item ; *intérêt général* et *bien commun* absents de tout texte lu.
- `INT-CE1-1`, blame : aucune scene ne met un personnage en balance avec le groupe, aucune ne juge celui qui fait quelque chose
  pour lui. Une seule lecture defendable sur chacune des 24 scenes telles qu'ecrites, y compris celles que `sofia-sm` lit le
  plus fragilement (J2 : *apprend sa poésie*, *relit sa dictée*, *balaie le sol de la salle*, *tient la porte ouverte*) : le
  nombre d'eleves n'est defendable sur aucune.
- Exactitude : aucune erreur dans les 24 situations.
- `TRV-CE1-6` : 15 prenoms, 7 feminins et 8 masculins ; les gestes rendus au groupe sont faits par 5 filles et 8 garcons, aucun
  role distribue par genre. `EMC-1`, `EMC-6` : aucune adresse a l'enfant, aucun lieu hors de l'ecole.
- Indices : 24 questions de methode, aucune ne contient une proposition, aucune ne mene au nombre d'eleves (J7).
- Lisibilite : *Pour qui est-ce utile ?* est lisible au CE1 et convient aux 24 scenes ; *traînent*, *essuie*, *balaie*, *lace*,
  *boutonne*, *efface* aussi (J5).

**Reserves, non bloquantes** :

1. Items 3, 5, 23 (la cour) : la cour sert toute l'ecole, et *pour toute la classe* dit moins que ce que la scene montre. Le
   distracteur n'en devient pas juste, ce n'est pas un refus. `INT-CE1-2` admet *pour tout le monde* : a preferer sur les scenes
   de la cour.
2. La troisieme proposition (*pour deux élèves*, *pour trois élèves*, *pour quelques élèves*) n'est la bonne reponse d'aucun
   item : apres quelques manches, l'enfant l'ecarte sans lire et la tache est un choix a deux (J3). C'est la forme que `INT-CE1-2`
   fixe ; elle n'est recevable qu'une fois M1 leve, sans quoi le choix a deux se fait a la forme.
3. Pli : non mesure sur ce paquet (`ines-design`) ; les seuils de 112 et 46 caracteres sont ceux de `LP4`.

**Reponses aux points de `sofia-sm`** :

- **J1** : lecture complete ci-dessus.
- **J2** : tenu, voir *Ce qui tient*.
- **J3** : reserve 2, et M1.
- **J4, `TRV-CE1-6` et l'objet possede** : recevable. Je corrige ma propre reformulation de la section 2 de l'instruction de s57
  (*"rien qui suppose ... un objet possede"*) : elle abrege la condition de s48, qui porte sur *"ce qui s'adresse a l'enfant
  (tu)"*. *Son crayon*, *ses lunettes* sont ceux d'un personnage nomme a l'ecole, le regime de `BCO-CE1-2` (*"la trousse de
  Lina"*). Ce n'est pas l'objet possede qui est refuse, c'est que le possessif decide seul de la reponse (M1).
- **J5** : tenu.
- **J6, `NRC-CE1-1` sur le fond** : M1 (le voisin de CE1) et M2 (le CP). `cp-emc-regle-souhait-loi` classe une situation en
  regle ou souhait : autre question, aucun item n'en reprend la tache.
- **J7** : tenu.
- **J8** : tenu sur les mots et sur le sens.

**Ce qui suit** : le lot revient a `sofia-sm` (developpeuse) par `camille-po`. Prix du rejeu : pas 0,25 - M1 demande au moins
huit scenes neuves, la liste fermee du test et l'en-tete a reecrire ; je l'estime a 0,5, ce n'est pas une mesure.

**Je reste la seule garde** : qu'aucune forme ne donne la reponse (le test du paquet garde aujourd'hui la forme refusee en M1,
il ne la detecte pas) ; qu'une scene n'ait qu'une lecture defendable ; qu'aucun item ne blame celui qui fait quelque chose pour
lui ; `NRC-CE1-1`, contre le CP et contre `ce1-emc-biens-communs` - aucun garde-fou ne compare deux paquets.

**Signe** : `nadia-prof`, 2026-10-22.
