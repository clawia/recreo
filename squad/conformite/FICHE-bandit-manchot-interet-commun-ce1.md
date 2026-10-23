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
  ouverte le 2026-10-22 par `nadia-prof`. **Rejeu du 2026-10-23 sur `ebbb2136` : REFUSE** (M1 et M2 leves, motif neuf M3 sur
  l'item 7), voir le second verdict plus bas. **Second rejeu du 2026-10-23 sur `c975a7b8` : VALIDE SOUS RESERVE** (M1 et M3
  leves, reserves 4 et 5 levees, reserves 3, 6, 7 ouvertes), voir le troisieme verdict plus bas. **Amendement du 2026-10-23,
  11:07 : reserve 3 levee** sur la mesure de `ines-design` ; reserves 6 et 7 ouvertes, verdict inchange.
- **Derniere mise a jour** : 2026-10-23, second rejeu ; amendement du 2026-10-23, 11:07 (reserve 3).

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

---

## Rejeu du 2026-10-23

Le verdict du 2026-10-22 ci-dessus, ses motifs et ses reserves restent tels qu'ecrits. Ce qui suit s'y ajoute.

**lecture complete : ce1-emc-interet-commun, 24 items lus** - 2026-10-23, ligne ecrite a 09:14 (UTC+02:00), lu par `date`, sur
la branche `s57-sofia-emc-ce1-04` a `ebbb2136` ; contenu et code a `ec2d572a` (`ebbb2136` n'ajoute que le rapport). Lus : les 24
enonces, bonnes reponses, 48 distracteurs, 24 indices (dont les 10 neufs ou deplaces), l'en-tete, `rapport-sofia-dev-palier-4-lp6.md`
sections 1 a 6. Relus pour `NRC-CE1-1` : les 11 items de `cp-emc-regles-de-vie`, les 24 de `ce1-emc-biens-communs`. Les items
sont numerotes ci-dessous dans l'ordre du fichier a `ebbb2136`, et non plus dans celui de `cbfb152e`.

### Verdict du 2026-10-23 (`nadia-prof`, s57, palier 4, rejeu) : **REFUSE**

**Commit joue** : `ebbb2136`. Jar construit dans mon worktree detache `recreo-s57-nadia-p4-lp6-jeu` place a ce commit
(`bash outils/mvn-jdk21.sh -B -pl bootstrap -am package -DskipTests`, mesure partielle, aucun `verify`), JDK 21.0.10 lu dans le
journal ; le YAML embarque dans le jar est identique octet pour octet a celui du commit (`cmp`). Port 18513, *"Tomcat started on
port 18513"* et PID lus dans mon journal, arrete par ce PID. Jeu par moi, requetes HTTP : 18 parties de 10 manches.

- Huit parties en me trompant d'abord a chaque manche, sur les deux mauvaises propositions : 24 enonces servis sur 24, aucun
  inconnu du paquet ; apres chaque erreur, *"Ce n'est pas ça. Essaie encore !"* et l'indice de l'item, conforme au fichier.
- Une partie a trois erreurs par manche : revelation *"Ce n'est pas ça. Appuie sur la bonne réponse : ..."*, indice maintenu, fin
  de partie atteinte.
- Trois parties en suivant le possessif sans lire la scene (*une seule personne* sur *son*, *sa*, *ses*, *toute la classe*
  sinon) : **20 sur 30** du premier coup. Sur le fichier, la meme regle a bon sur 15 items sur 24.
- Trois parties en repondant toujours *pour toute la classe* : 11 sur 30.
- Trois parties en suivant les mots *chacun*, *tableau*, *salle*, *mur* (*toute la classe* s'il y en a un, *une seule personne*
  sinon) : **30 sur 30**. Sur le fichier : 24 sur 24. Voir reserve 4.

**Non fait par moi** : le releve d'ecran a 744 x 551 (`ines-design`). Le plus long enonce compte 90 caracteres, le plus long
indice 34 (mesures par moi sur le fichier, pas a l'ecran).

**M1 - leve sur la portee *un*, suspendu a M3 sur la portee *tous*.** La barre que j'avais ecrite : dans chaque portee, au moins
un tiers d'items ou l'appartenance de l'objet ne decide pas. Comptee par moi sur le texte : portee *un*, 4 sur 11 (items 4
*bibliothèque*, 10 *ciseaux du coin dessin*, 14 *robinet de la cour*, 18 *dictionnaire du coin lecture*) ; portee *tous*, 5 sur
13 (items 3 *sa plante*, 7 *son affiche*, 11 *son annonce*, 15 *son chiffon*, 19 *sa carte*). La regle du possessif tombe de 30
sur 30 a 20 sur 30 joues. Les quatre scenes de portee *un* a objet partage sont le croisement que demandait M1 : *le livre de la
bibliothèque* est la bonne reponse *a tous* de `ce1-emc-biens-communs`, et ici la bonne reponse est *une seule personne*. L'enfant
qui repond par l'appartenance s'y trompe ; celui qui cherche a qui sert le geste trouve. C'est la ligne `EMC-CE1-04`, et non plus
`EMC-CE1-01`. **Mais** la portee *tous* n'atteint 5 sur 13 qu'en comptant l'item 7, que M3 ne laisse pas compter : sans lui, 4
sur 13, sous la barre. Sur la portee *tous*, M1 sera leve quand M3 le sera, sans rien d'autre.

**M2 - leve.** Les items 5, 7, 9, 19 du premier passage sont retires ; aucune scene ne dit plus *range*, *ranger*, *remet* ou
*remettre* (cherche par moi dans les 24 scenes). Les gestes de portee *tous* sont essuyer, decorer, ramasser, remplir, afficher,
annoncer, proposer un jeu, distribuer, accrocher, ecrire la date, apporter, tenir la porte : aucun n'est une reponse de
`cp-emc-regles-de-vie`.

**Motif du refus, actionnable sans me redemander :**

**M3 - L'item 7 a deux lectures defendables** (`INT-CE1-1` : *"La situation n'a qu'une lecture defendable (`EMC-5`)"* ; c'est ce
que j'avais ecrit *a ne pas defaire au rejeu*). *"Lina colle son affiche des horaires au mur."* La scene ne dit ni ou (aucun lieu
de l'ecole), ni pour quoi (aucun but). Il reste *son affiche* et *au mur* : un enfant de CE1 peut voir une affiche a Lina collee
au mur pour qu'elle se rappelle ses horaires, et repondre *pour une seule personne* - il a une raison defendable et l'ecran lui
dit *"Ce n'est pas ça"*. *Horaires* n'est pas toujours compris a 7 ans, ce qui laisse *Lina colle son affiche au mur*, plus
personnel encore. Les quatre autres scenes de la meme case nomment ce qui fait le groupe : *pour décorer la salle* (3), *au
tableau* et *la sortie* (11), *les tables de la salle* (15), *au tableau pour la leçon* (19). L'item 7 est le seul de la case ou
le possessif est la seule information, alors que c'est la case ou il doit etre faux. Ce qu'il faut : que la scene 7 nomme un lieu
ou un but de l'ecole, comme les quatre autres, sans le mot *classe* que le garde d'echo refuse - par exemple *"Lina colle son
affiche des horaires au mur de la salle."* L'item reste alors de portee *tous* a objet personnel et la barre de M1 est tenue a 5
sur 13. Rien d'autre n'est demande pour lever ce refus.

**Ce qui tient** (verifie au rejeu, a ne pas defaire) :

- `INT-CE1-1`, une seule lecture, sur les 23 autres scenes, y compris celles que `sofia-sm` lit fragiles. Portee *tous* a objet
  personnel, items 3, 11, 15, 19 : le lieu ou le but nomme le groupe, et aucune ne se lit *pour une seule personne*. Portee *un*
  a objet partage, items 4, 10, 14, 18 : prendre un livre, prendre des ciseaux, boire, chercher un mot servent celui qui le fait ;
  *pour quelques élèves* n'est defendable sur aucune (rien dans l'item 18 ne dit que Hugo cherche pour d'autres).
- `INT-CE1-1`, blame : aucune mise en balance, aucun jugement sur celui qui fait quelque chose pour lui.
- `INT-CE1-2` : les deux formules dans chaque item ; *intérêt général*, *bien commun* absents.
- `NRC-CE1-1` contre `ce1-emc-biens-communs` : la bibliotheque, la cour, le coin lecture servent ici a montrer que l'objet de
  tous peut ne servir qu'a un ; la question n'est jamais *a qui est l'objet*.
- Exactitude : aucune erreur dans les 24 situations.
- Indices : 24, distincts, chacun une question de methode, aucun ne contient une proposition ni ne mene au nombre d'eleves ; les
  10 neufs ou deplaces (items 3, 4, 5, 7, 10, 11, 14, 15, 18, 19) designent celui qui se sert de la chose.
- Lisibilite : enonces de 17 mots au plus ; *chiffon*, *dictionnaire*, *robinet*, *décorer*, *accroche*, *annonce*, *sortie* sont
  accessibles au CE1. *Horaires* l'est moins : M3.
- `TRV-CE1-6`, `EMC-1`, `EMC-6` : aucun role distribue par genre, aucune adresse a l'enfant, aucun lieu hors de l'ecole nomme.

**Reserves du 2026-10-22, au rejeu :**

1. La cour (items 3, 5, 23 du premier passage) : **levee**. Aucune scene de portee *tous* ne se passe plus dans la cour ; la cour
   ne reste qu'a l'item 14, de portee *un*.
2. La troisieme proposition jamais bonne (choix a deux) : **levee**, sur la condition que j'avais posee (*"recevable une fois M1
   leve"*). Elle reste la forme de `INT-CE1-2`. Le choix a deux ne se fait plus au possessif ; il peut encore se faire aux mots du
   lieu, reserve 4.
3. Pli : **maintenue**, non mesure (`ines-design`).

**Reserves nouvelles du 2026-10-23, non bloquantes** - vues au rejeu, je ne les ajoute pas a la barre de M1 :

4. **Le lieu suit encore la portee.** Les lieux partages qui servent un seul enfant sont *bibliothèque*, *coin dessin*, *cour*,
   *coin lecture* ; *la salle*, *le tableau*, *le mur* n'apparaissent que dans des scenes de portee *tous*. Joue : la regle
   *chacun, tableau, salle, mur* donne 30 sur 30. Ce n'est pas M1 recommence : un enfant qui se dit *"c'est au tableau, tout le
   monde le voit"* fait la competence, ce que le possessif ne faisait pas faire. Mais la portee *un* ne croise jamais *salle* ni
   *tableau*. A corriger dans un sprint suivant : deux scenes de portee *un* dans la salle ou devant le tableau (par exemple
   *"Jade s'assoit à une table de la salle pour dessiner."*).
5. ***Chacun* dit la reponse dans quatre scenes** de portee *tous* : items 13 (*où chacun peut jouer*), 17 (*à chacun*), 21 (*pour
   que chacun la voie*), 24 (*pour chacun*). L'enfant n'a pas a juger a qui sert le geste, la scene le lui dit. **Correction de mon
   propre constat** : ces quatre tournures etaient deja dans le paquet du 2026-10-22 et j'ai ecrit que la forme ne donnait la
   reponse que par le possessif, sans les avoir examinees. C'est un manque de ma premiere lecture, pas un motif neuf contre
   l'equipe : reserve, non refus. A corriger : retirer *chacun* d'au moins deux de ces scenes en gardant une seule lecture
   (*"Zoé distribue les feuilles."*, *"Hugo tient la porte ouverte."*).

**Ce qui suit** : le lot revient a `sofia-sm` par `camille-po`, pour la seule scene 7. Je ne l'estime pas en prix : une phrase,
a relire par moi sur l'item 7 et le compte de M1, sans rejeu complet. Les reserves 3, 4, 5 sont pour `camille-po`.

**Je reste la seule garde** : qu'une scene n'ait qu'une lecture defendable (le test garde la presence d'un possessif ou d'un mot
partage, pas le sens - il est vert sur l'item 7) ; que le lieu ne decide pas seul de la reponse (le test ne garde que le compte du
possessif) ; qu'aucun item ne blame ; `NRC-CE1-1` contre le CP et contre `ce1-emc-biens-communs`, qu'aucun garde-fou ne compare
sur le sujet.

**Signe** : `nadia-prof`, 2026-10-23.

---

## Second rejeu du 2026-10-23

Les verdicts du 2026-10-22 et du 2026-10-23 ci-dessus, leurs motifs et leurs reserves restent tels qu'ecrits. Ce qui suit s'y
ajoute.

**lecture complete : ce1-emc-interet-commun, 24 items lus** - 2026-10-23, ligne ecrite a 10:25 (UTC+02:00), lu par `date`, sur
la branche `s57-sofia-emc-ce1-04` a `c975a7b8` ; contenu et code a `53f98a74` (`git diff --name-only 53f98a74 c975a7b8` ne nomme
que le rapport). Lus : les 24 enonces, bonnes reponses, 48 distracteurs, 24 indices, l'en-tete, `rapport-sofia-dev-palier-4-lp6.md`
section 11. Relus pour `NRC-CE1-1` : les 24 items de `ce1-emc-biens-communs`, les 11 de `cp-emc-regles-de-vie`. Les numeros
d'items sont ceux de l'ordre du fichier, inchange depuis `ebbb2136` : 10 scenes changees en place (3, 6, 7, 11, 13, 17, 19, 21,
22, 24), 5 indices changes (6, 11, 13, 17, 22).

### Verdict du 2026-10-23 (`nadia-prof`, s57, palier 4, second rejeu) : **VALIDE SOUS RESERVE**

**Commit joue** : `c975a7b8`. Jar construit dans mon worktree detache `recreo-s57-nadia-p4-lp6-jeu` place a ce commit
(`bash outils/mvn-jdk21.sh -B -pl bootstrap -am package -DskipTests`, mesure partielle, aucun `verify`), `BUILD SUCCESS`, JDK
21.0.10 lu dans le journal. Le YAML embarque dans le jar ne differe du blob de `c975a7b8` que par les fins de ligne
(`diff --strip-trailing-cr` vide) et il est identique octet pour octet au fichier du worktree (`cmp`). Port 18514, *"Tomcat
started on port 18514"* et PID 36580 lus dans mon journal, arrete par ce PID. Jeu par moi, requetes HTTP :

- Huit parties en me trompant d'abord a chaque manche (le nombre d'eleves, puis la mauvaise formule) : 24 enonces servis sur
  24, aucun inconnu du paquet. Huit autres parties ou l'indice affiche apres une erreur est compare a celui du fichier : 0 ecart
  sur 80 manches, la bonne reponse figure parmi les trois propositions a chaque manche.
- Manches a trois erreurs : *"Ce n'est pas ça. Appuie sur la bonne réponse : ..."*, indice maintenu. Fin de partie *"C'est
  fini !"* atteinte.
- Regles de surface jouees sans lire la scene, trois parties chacune : possessif (*une seule personne* sur *son*, *sa*, *ses*,
  *toute la classe* sinon) **12 sur 30** ; mots *tableau*, *salle*, *mur* (*toute la classe* s'il y en a un, *une seule personne*
  sinon) **20 sur 30** ; toujours *pour toute la classe* **13 sur 30**.

Comptees par moi sur le texte des 24 scenes (script de poste, sans lire les declarations ni le rapport) : possessif **11 sur
24** ; mots *tableau*, *salle*, *mur* **17 sur 24** (fausse sur 6 et 22 en portee *un*, sur 3, 11, 13, 17, 24 en portee *tous*) ;
constante *toute la classe* **13 sur 24**. Croisees, les deux marques ne font pas mieux que 17 sur 24. Mes chiffres sont ceux du
rapport de `sofia-sm` (11.3).

**Non fait par moi** : le releve d'ecran a 744 x 551 (`ines-design`). Plus long enonce 90 caracteres, plus long indice 34, 17
mots au plus (mesures sur le fichier).

**M3 - leve.** L'item 7 est *"Lina colle son affiche des horaires au mur de la salle."*, la phrase que j'avais donnee comme
suffisante, sans le mot *classe*. Une seule lecture : l'affiche est dans la salle, elle sert ceux qui y sont. *Horaires* reste
moins accessible, je l'avais ecrit ; ce n'etait pas la condition et je ne l'y ajoute pas.

**M1 - leve dans les deux portees.** Barre : au moins un tiers d'items ou l'appartenance de l'objet ne decide pas. Portee *tous*,
objet personnel : **7 sur 13** (3, 7, 11, 13, 15, 17, 19), il en fallait 5. Portee *un*, objet partage : **6 sur 11** (4, 6, 10,
14, 18, 22), il en fallait 4. La regle du possessif tombe a 11 sur 24 sur le fichier et 12 sur 30 jouee, sous la reponse
constante : le possessif ne dit plus rien de la portee.

**Reserves anterieures, au second rejeu :**

- Reserve 4 : **levee.** Ma barre etait *"deux scenes de portee un dans la salle ou devant le tableau"* : items 6 (*une table de
  la salle*) et 22 (*la phrase du tableau*). La regle des mots passe de 24 sur 24 a 17 sur 24.
- Reserve 5 : **levee.** *Chacun* n'est plus dans aucune scene (cherche par moi dans les enonces et les indices, avec *tous*,
  *toute*, *classe*, *range*, *remet* : aucune occurrence). Items 13, 17, 21, 24 relus ci-dessous.
- Reserve 3, pli : **maintenue**, non mesure (`ines-design`).

**La question de `sofia-sm` : 17 sur 24 suffit-il ?** Oui pour ce Done : c'est la barre que j'avais ecrite, elle est atteinte,
et un enfant qui se dit *"c'est au tableau, tout le monde le voit"* fait encore la competence. Je ne la releve pas. **Correction
de l'affirmation du rapport** (11.3) : abaisser la regle n'oblige pas a reecrire 5, 9, 15 ou 23. Ces quatre items font gagner la
regle en portee *tous* ; on peut la faire perdre en portee *un*, ou elle ne se trompe que sur 2 items sur 11. Voir reserve 6.

**Reserves nouvelles du 2026-10-23, non bloquantes :**

6. **La regle des mots *tableau*, *salle*, *mur* a encore raison sur 9 items sur 11 en portee *un*.** Mesuree contre la barre de
   M1 (un tiers par portee), elle passe en portee *tous* (fausse sur 5 sur 13) et non en portee *un* (fausse sur 2 sur 11).
   Changement precis, sans toucher 5, 9, 15, 23, ni aucune portee ni appartenance : item 10, *"du coin dessin"* devient *"de la
   salle"* (*"Mila prend des ciseaux dans la boîte de la salle pour découper."*) ; item 18, *"du coin lecture"* devient *"de la
   salle"* (*"Hugo cherche un mot dans le dictionnaire de la salle."*). La regle tombe a 15 sur 24, fausse sur 4 sur 11 en portee
   *un*. Les deux phrases gardent une seule lecture.
7. **Item 13, la recreation.** *"Inès agite sa clochette pour la fin de la récréation."* La fin de la recreation sert toute la
   cour, souvent toute l'ecole : *pour toute la classe* dit moins que la scene, comme la cour en reserve 1 du 2026-10-22. Le
   distracteur n'en devient pas juste. A corriger : un but propre a la classe (par exemple *"pour la fin de l'atelier"*), ou la
   formule *pour tout le monde*, que `INT-CE1-2` admet, sur cet item.

**Une seule lecture defendable, scenes neuves** (`INT-CE1-1`) : 3 (*sa plante pour la leçon de sciences* : la lecon est celle du
groupe), 6 (s'asseoir pour dessiner sert Zoe), 11 (*son annonce de la sortie à voix haute* : une annonce lue tout haut est faite
pour etre entendue, et la sortie nomme le groupe), 13 (sonner la fin ne sert pas celle qui sonne ; voir reserve 7), 17
(*distribue* dit que les fiches vont aux autres, *la dictée* que ce sont tous), 22 (recopier sert celle qui recopie ; le tableau
est a tous, la copie a Maya : c'est le croisement voulu), 24 (*la file* est le rang de la classe ; *pour quelques élèves* n'y est
pas defendable). 19 (*au mur pour la leçon*) et 21 (*la date au tableau*) gardent leur lecture. Le nombre d'eleves n'est
defendable sur aucune des 24.

**Ce qui tient** (verifie au second rejeu) : `INT-CE1-1`, blame (aucune mise en balance, aucun jugement) ; `INT-CE1-2` (les deux
formules a chaque item, *intérêt général* et *bien commun* absents) ; `NRC-CE1-1` contre `cp-emc-regles-de-vie` (aucun geste de
rangement ; tenir la porte n'est pas *faire la queue*) et contre `ce1-emc-biens-communs` (6 et 22, comme 4, 10, 14, 18, montrent
un objet de tous qui ne sert qu'a un : la question reste *a qui sert le geste*, jamais *a qui est l'objet*) ; exactitude, aucune
erreur dans les 24 situations ; indices, 24 distincts, questions de methode, aucun ne contient une proposition ; `TRV-CE1-6`,
gestes rendus au groupe par 6 filles et 7 garcons, aucun role par genre ; `EMC-1`, `EMC-6`.

**Done pedagogique prononce** sur `c975a7b8` (contenu `53f98a74`). `camille-po` peut passer `EMC-CE1-04` a `couvert-valide` une
fois la lignee fusionnee sans changement du paquet, et faire des stories des reserves 3, 6, 7.

**Je reste la seule garde** : qu'une scene n'ait qu'une lecture defendable (le test garde des mots, pas le sens) ; que les mots
*tableau*, *salle*, *mur* ne decident pas seuls (le garde du lieu compte la liste fermee du test, pas cette regle, et ne rougirait
pas si la reserve 6 s'aggravait) ; qu'aucun item ne blame ; `NRC-CE1-1` sur le sujet, contre le CP et contre
`ce1-emc-biens-communs`.

**Signe** : `nadia-prof`, 2026-10-23.

---

## Amendement du 2026-10-23, 11:07 (UTC+02:00, lu par `date`) - reserve 3

Les verdicts ci-dessus, leurs motifs et leurs reserves restent tels qu'ecrits. Ce qui suit s'y ajoute.

**Reserve 3, pli : levee.** Ma barre, ecrite le 2026-10-22 et maintenue aux deux rejeux, etait que le pli soit mesure sur ce
paquet par `ines-design` ; le critere est celui du palier 3, bas de `.indice` au plus a 551 a 744 x 551. Mesure lue :
`squad/sprints/s57-version-cp-ce1-cm1-trois-instances/mesure-ines-palier-4-lp6-pli-744x551-c975a7b8.md` (commit `c4cd299a`,
fusionne sur `round-two`), a `c975a7b8`, le commit de mon Done. Ce qu'elle rend : 0 element sous le pli, 24 items sur 24 par une
premiere erreur, dans les six ordres des propositions ; bas de `.indice` 510 au plus (41 px de marge), bas du signal 536 au plus
(15 px) ; item 7, le plus long apres le changement, sur 2 lignes. La barre est atteinte, je la leve sans la relever.

Verifie par moi avant de m'appuyer dessus : `git diff --stat c975a7b8 c08e49ab -- adapters/ bootstrap/src/main domain application`
est vide (le paquet, le catalogue, la feuille et les gabarits mesures sont ceux de `round-two` a `c08e49ab`) ; le SHA-256 du
paquet dans l'arbre commence par `5efdf02b`, celui que la mesure lit dans le jar. Je n'ai pas refait la mesure a l'ecran.

Ce que la mesure ne couvre pas, et que je n'ajoute pas a la barre : un seul navigateur sans tete, pas d'appareil reel, pas de
zoom ni de police de remplacement (section 7 de la mesure). La page fait 568 px de haut apres une erreur : 17 px de rembourrage
sous le signal, aucun element a lire.

**Reserves 6 et 7 : ouvertes, non bloquantes, inchangees.** Aucune n'invalide le Done du second rejeu : la 6 mesure une regle de
surface qui reste sous la barre de M1 en portee *tous* et ne donne pas la reponse sans que l'enfant juge a qui sert le geste ; la 7
est une bonne reponse incomplete, pas fausse, et aucun distracteur n'en devient juste. Elles peuvent etre reportees. Un lot qui
les corrige change le paquet : je relis alors les items touches (10 et 18, ou 13) et le compte de la regle des mots avant que la
ligne reste `couvert-valide`, sans rejeu complet.

**Je reste la seule garde** sur les memes proprietes qu'au second rejeu ; aucun garde-fou ne mesure le pli de ce paquet a chaque
changement - la mesure vaut pour `c975a7b8` et le contenu qu'il porte.

**Signe** : `nadia-prof`, 2026-10-23.
