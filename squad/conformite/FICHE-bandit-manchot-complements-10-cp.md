# Fiche de conformite - bandit-manchot-complements-10-cp

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du
  catalogue).
- **Contenu(s)** : `adapters/adapter-content/src/main/resources/contenu/cp-complements-10.yaml` (a
  creer, `type: question`, au moins 9 items), story `S9-2` (`docs/backlog/backlog.md`). Contenu
  neuf, aucune matiere premiere identifiee dans `contenu-repris/` pour cette ligne.
- **Statut global** : en construction, deblocage acte par `DEC-0020` (nombreDeManches abaisse a 7,
  criteres corriges).
- **Arbitrage prealable de mecanique du 2026-08-12** (`DEC-0023` point 6 : l'ecran intermediaire
  disparait) : ecrit dans `FICHE-bandit-manchot-additions-cp.md`, bloc de validation, section
  "Arbitrage prealable au developpement - la mecanique apres la disparition de l'ecran
  intermediaire". **Il vaut pour ce jeu comme pour les huit du catalogue**, la mecanique etant
  commune : conditions M1 a M13, dont **deux bloquantes** - M4 (le message d'erreur doit etre vu
  sans defiler a 360 x 640) et la clause 1 de M13 (apres une reponse, le focus ne va jamais sur la
  sortie, qui detruit la partie sans confirmation). Ce n'est pas un Done pedagogique, rien n'est
  code.
- **Second verdict du 2026-08-13 sur le lot de cloture du sprint 10 (`643c6f4`)** : **REFUSE**,
  aucun Done pedagogique, **un seul motif bloquant** - **M13 clause 1 sur l'ecran de revelation de
  M5**. **Constate sur ce jeu** : reponse revelee en derniere position, la partie a ete detruite en
  deux touches. **M4 est fermee sur ce jeu, avec 2,81 px de marge seulement.** Le verdict complet et
  l'etat de mes trente-huit conditions sont dans
  `squad/conformite/FICHE-bandit-manchot-additions-cp.md`, section "Second verdict du 2026-08-13". **Le
  refus du matin est corrige sur ses quatorze cas** et les clauses 2, 3, 4 et 5 de M13 sont tenues pour
  la premiere fois. **M4 ne bloque plus**, levee par `DEC-0025`, et mon constat n'est pas retire.
- **Derniere mise a jour** : 2026-08-13

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| mathematiques | CP (cycle 2) | Nombres et calculs | Complements a 10 | `squad/programme/mathematiques/competences-cp.md` |

## Criteres pedagogiques attendus - camille-po

- [ ] Chaque enonce demande le complement a 10 d'un entier compris entre 0 et 10 inclus, et la
  bonne reponse, ajoutee au nombre de l'enonce, fait exactement 10.
- [ ] Chaque valeur entiere de 0 a 10 apparait au moins une fois comme nombre de depart parmi les
  items (couverture complete de la table des complements a 10, pas seulement ses valeurs les plus
  faciles).
- [ ] Chaque item a exactement trois propositions (la bonne reponse, deux distracteurs numeriques
  distincts dans l'intervalle 0-10).
- [ ] Au moins un distracteur par item correspond a une erreur frequente et identifiable (par
  exemple le nombre de depart recopie, ou le complement a 9 plutot qu'a 10).
- [ ] Le paquet compte au moins 9 items, strictement plus que les 7 manches declarees pour ce jeu au
  catalogue (`ADR-0023`) - plancher different des autres paquets du sprint car l'espace de la forme
  canonique est fini a onze valeurs (0 a 10 inclus), voir `DEC-0020` pour le calcul.
- [ ] L'entree de ce jeu dans `catalogue.yaml` declare `nombreDeManches: 7` (`DEC-0020`).
- [ ] Chaque item porte un indice non vide, exact et comprehensible par un enfant de CP.
- [ ] Le paquet valide le schema `type: question` (`ADR-0007`) sans code Java neuf, sans gabarit ni
  feuille de style neuve.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.


### Second verdict du 2026-08-13 sur le lot de cloture du sprint 10 : REFUSE

- **Verdict** : **REFUSE.** Aucun Done pedagogique sur ce jeu.
- **Le verdict complet, ses mesures et l'etat de mes trente-huit conditions sont dans**
  `squad/conformite/FICHE-bandit-manchot-additions-cp.md`, section "Second verdict du 2026-08-13". Le
  meuble et la mecanique sont communs aux sept jeux du catalogue : je ne recopie pas le raisonnement,
  j'ecris ici ce qui est propre a ce jeu.
- **Increment audite** : `round-two` a `643c6f4`, jar **reconstruit depuis mon worktree** et correctif
  verifie dans le HTML servi. `mvn -B verify` : BUILD SUCCESS. Done technique acquis.
- **Mon motif de refus sur ce jeu, unique et bloquant - M13 clause 1 sur l'ecran de revelation** :
  - **Constate directement sur ce jeu** : revelation atteinte, reponse revelee en **position 3 sur 3**,
    focus sur cette proposition, **une seule Tabulation atteint le bouton "Les jeux"**
    (`data-role="sortie"`) et Entree **detruit la partie sans confirmation** - plus d'ecran de jeu.
  - C'est le meme defaut que celui de mon refus du matin, sur un ecran que je n'avais pas mesure.
- **M4 sur ce jeu, mesuree apres le resserrement de marges** (pire cas de 36 etats de message et de
  4 ecrans de revelation, 360 x 640, pli a `y = 640`) :
  - echec avec indice, pire cas : **637,19 px**, soit **2,81 px au-dessus du pli** ;
  - ecran de revelation, pire cas : **579,19 px**, 60,81 px au-dessus ;
  - "Bravo !" : 495,19 px, 144,81 px au-dessus.
  - **M4 est tenue sur ce jeu, et de 2,81 px.** Elle etait a 651,19 px ce matin. Je la declare tenue
    **sur les 36 etats mesures** et je ne garantis rien sur les items que je n'ai pas tires : l'indice
    le plus long que j'ai vu - *"Leve 5 doigts d'une main, puis compte ceux de l'autre main : il en
    reste 5 pour arriver a 10."* - consomme presque toute la marge. C'est le "fragile" d'`ines-design`,
    et il lui donne raison une seconde fois.
- **Ce qui est corrige sur ce jeu et que je credite** : apres une bonne comme apres une mauvaise
  reponse, Tabulation puis Entree laisse **la partie en cours** ; le focus va sur l'**enonce** de la
  question neuve apres une bonne reponse (clause 4) et sur la **proposition pressee, barree**, apres une
  mauvaise (clause 2) ; sur l'ecran de revelation il va sur la **proposition qui porte la reponse
  revelee** (clause 5). Les quatre clauses sont tenues sur ce jeu, mesurees au clavier.
- **Ce qui est propre a ce jeu** : ses indices sont exacts et ne nomment pas la reponse comme reponse -
  les deux que j'avais recomptes ce matin (`0 + ? = 10` et `5 + ? = 10`) portent le nombre en operande,
  pas en conclusion. Mon chiffre corrige du matin tient.
- **Aucune ligne de `squad/programme/MATRICE-couverture.md` ne bouge.**

### Verdict courant - chantier du bandit manchot (mecanique, forme, M10), du 2026-08-13 : REFUSE

- **Verdict** : **REFUSE.** Aucun Done pedagogique sur ce jeu.
- **Le verdict complet, ses mesures et mes trente-deux conditions une par une sont dans**
  `squad/conformite/FICHE-bandit-manchot-additions-cp.md`,
  section "Verdict courant - chantier du bandit manchot (mecanique, forme, M10) (...) du
  2026-08-13". Le meuble et la mecanique sont communs aux sept jeux du catalogue : je ne recopie pas
  le raisonnement, j'ecris ici ce qui est propre a ce jeu.
- **Increment audite** : `round-two` a `2d34a15`. Done technique acquis.
- **Mes deux motifs de refus, tous deux bloquants** :
  - **M4, bloquante** : **651,19 px** en echec avec indice (**11,19 px sous le pli**) et
    **653,19 px** sur l'ecran de revelation (**13,19 px sous le pli**). La revelation, qui dit
    a l'enfant sur quoi appuyer, est elle aussi hors de l'ecran.
  - **M13 clause 1** : apres une bonne comme apres une mauvaise reponse, le focus reste sur
    `BODY`, **une seule Tabulation atteint le bouton "Les jeux"** (`data-role="sortie"`) et
    Entree **detruit la partie sans confirmation**. Verifie au clavier sur ce jeu. Cause :
    `recreo.js` est charge dans le `<head>` et leve
    `TypeError: Cannot read properties of null (reading 'addEventListener')`, donc l'ecouteur
    qui deplace le focus n'est **jamais enregistre** et `data-focus` est un attribut mort.
- **Ce qui est tenu et que je ferme sur ce jeu** : F16 (la marque "deja essayee" est un mot barre a
  **9,55:1 au minimum** sur les quatre teintes, contre un plancher de 3:1), F9, F10 y compris sa
  clause 3 que je n'avais pas pu fermer le 2026-08-13, F13 a F15 (le levier vit dans ses 20 px et
  n'intercepte aucun appui), F17, F18, F19, M6 (le meuble est identique au pixel dans les quatre
  etats), M8, M9, M10 et M12.
- **Condition neuve M14, datee du 2026-08-13** : apres la revelation, un appui sur une autre
  proposition doit la barrer comme n'importe quelle proposition deja essayee, sans compter d'essai,
  sans changer le message, sans mouvement et sans desactivation. Motif complet en section 6 du
  verdict de reference.
- **Aucune ligne de `squad/programme/MATRICE-couverture.md` ne bouge.**


### Verdict courant - S9-2, prononce sur l'increment livre, du 2026-08-12

- **Verdict** : **VALIDE.** Done pedagogique prononce, sans reserve bloquante. `camille-po` peut
  passer la ligne de matrice a `couvert-valide`.
- **Date** : 2026-08-12.
- **Audit joue** : **non degrade.** Jar construit et lance depuis mon worktree
  `s09-nadia-verdicts`, joue par les vraies routes (`POST /parties/{jeuId}`,
  `POST /parties/{jeuId}/coups`, `GET /parties/{jeuId}/manche`), trois parties completes : une en
  repondant juste, une en ratant tout, une en ratant les trois premieres manches puis en repondant
  juste. Les 11 items lus dans
  `adapters/adapter-content/src/main/resources/contenu/cp-complements-10.yaml`.

#### 0. Ce que je corrige de mes propres constats, avant de m'appuyer dessus

Deux corrections, et elles comptent parce que j'aurais ecrit deux faussetes sans elles.

**A. La forme du second item degenere ne m'a pas ete decrite exactement.** On m'a annonce
`10 + ? = 0`. Le paquet livre porte `10 + ? = 10`, bonne reponse `0`. Ce n'est pas la meme chose :
`10 + ? = 0` n'a aucune solution entiere et aurait ete un motif de refus immediat.
`10 + ? = 10` est la forme canonique correcte du dernier couple de la table. **Je juge la forme
livree, pas celle qu'on m'a decrite**, et c'est pour ca que j'ouvre le fichier avant d'arbitrer.

**B. J'ai cru voir une repetition d'item dans une partie.** Au cours de ma premiere partie
j'ai note `10 + ? = 10` en manche 1 et en manche 7, et j'allais l'inscrire comme defaut. C'est
faux : ces deux manches viennent de **deux parties differentes**, deux lancements distincts de mon
script. Verifie sur six parties completes des quatre jeux, avec extraction de tous les enonces
servis : **aucune repetition d'item dans une meme partie**, sur aucun des quatre jeux. Le tirage
sans remise de `ListContentSource` (filtre `dejaVus`) fait ce qu'il annonce. Je n'inscris pas un
defaut que je ne reproduis pas.

#### 1. Les deux items degeneres : je les GARDE, tous les deux

C'est la question qu'on m'a posee nommement, voici ma reponse et son motif.

**`0 + ? = 10` (reponse `10`) et `10 + ? = 10` (reponse `0`) restent dans le paquet.** Le paquet
compte donc **11 items**, et aucun recalcul de plancher n'est declenche : ni `DEC-0020` (9 items) ni
`ADR-0023` (8 items pour 7 manches) n'est approche.

Trois raisons, dans l'ordre de leur poids.

1. **Ils font partie de la competence, ils ne sont pas a sa marge.** La table des complements a 10
   telle qu'elle s'enseigne au CP - les "maisons du 10" - contient le couple (0, 10) au meme titre
   que (4, 6). Retirer ces deux items ne retirerait pas deux items faciles : cela retirerait le
   **seul** endroit du paquet ou l'enfant rencontre 0 comme resultat d'un calcul et 10 comme
   complement. Le referentiel porte "Compléments à 10" sans restriction, et un enfant qui ne sait
   pas que 10 se complete par 0 ne connait pas la table.
2. **Ils sont exacts et leurs indices sont les meilleurs du paquet.** "Garde tes mains fermées : il
   manque tes 10 doigts pour arriver à 10." et "Lève tes 10 doigts : il n'en manque plus aucun pour
   arriver à 10." Les deux disent le cas limite avec le meme modele des doigts que les neuf autres
   items, sans introduire un mot neuf. C'est du bon travail, pas un remplissage.
3. **Le zero comme quantite est au programme du CP**, et c'est une notion qu'un enfant construit
   mal si on ne la lui presente jamais dans un calcul. "Il n'en manque aucun" est une reponse qu'il
   doit pouvoir donner.

**Ce que je concede, et qui ne renverse pas ma decision** : sur `0 + ? = 10`, la bonne reponse
`10` est ecrite dans l'enonce, donc recopiable sans calculer. C'est le seul item du paquet dans ce
cas, et l'enfant doit quand meme choisir entre `10`, `0` et `9`. Un item sur onze recopiable ne
justifie pas de perdre le couple (0, 10).

#### 2. Sept manches contre dix : acceptable, et je dis a quelle condition, verifiee

**Oui, l'asymetrie est acceptable pour un enfant.** Je l'ecris sans reserve, pour trois raisons que
j'ai verifiees en jouant et non deduites.

1. **L'enfant ne compare pas les longueurs de deux jeux, il joue celui qu'il a choisi.** Rien a
   l'ecran n'annonce "10 manches" ailleurs et "7" ici : le seul compteur visible est
   `Score : N`, qui monte sans plafond affiche. Verifie sur les trois parties : la partie s'arrete
   sur `C'est fini !` apres la 7e reponse, sans qu'aucun ecran ait promis autre chose. Il n'y a donc
   pas de promesse rompue, qui serait le seul vrai grief.
2. **L'aide reste atteignable, et c'est la condition qui aurait pu tout casser.** Je l'ai testee
   plutot que raisonnee : partie ou je rate volontairement les manches 1, 2 et 3 -> **l'indice
   s'affiche en manche 4** ("Lève 2 doigts, puis compte ceux qui manquent pour arriver à 10."), avec
   encore trois manches devant. Sept manches laissent donc a l'enfant en difficulte le meme secours
   qu'a dix, et il lui reste du jeu apres l'avoir recu. Si `nombreDeManches` etait descendu a 3,
   j'aurais refuse.
3. **Sept manches sur 11 items, c'est un meilleur rapport que dix sur 24.** L'enfant voit ici 64 %
   de la table en une partie, contre 42 % des soustractions. La partie est plus courte et couvre
   proportionnellement plus. Pour une table finie de onze cases, c'est le bon choix.

Et je valide explicitement l'arbitrage de `camille-po` sur le fond : **elargir les formes d'items
pour atteindre dix manches aurait change la competence exercee** (`a + ? = 10` n'est pas
`? + a = 10` ni `10 - a = ?`, ce sont trois taches cognitives differentes au CP). Ne pas le faire
sans me le demander etait la bonne decision.

### Ce qui a ete verifie

- **Ancrage au programme** : "Compléments à 10", `squad/programme/mathematiques/competences-cp.md`,
  section "Nombres et calculs", retrouvee au mot. C'est bien **cette** competence que l'increment
  travaille et pas une voisine plus facile : la forme canonique `a + ? = 10` demande le complement,
  jamais une somme a calculer. Les 11 valeurs de `a` de 0 a 10 sont couvertes, une fois chacune :
  couverture complete de la table, pas seulement ses cases faciles.
- **Lisibilite au niveau vise** : consigne `Combien manque-t-il pour faire 10 ?` - six mots, une
  question, aucun mot hors de portee d'un CP, et elle dit exactement ou est le trou. Les enonces
  sont des ecritures chiffrees de neuf caracteres. Rien a lire de long. Les indices font une phrase.
- **Charge cognitive** : une seule chose a tenir en tete (le nombre de depart), trois propositions,
  toutes des nombres de 0 a 10. Aucun mot a decoder pendant le calcul.
- **Progression** : le tirage est aleatoire sans remise, donc l'ordre du fichier (0 a 10) n'impose
  aucune sequence a l'enfant - verifie sur trois parties, les nombres de depart arrivent melanges.
  Il n'y a pas de progression croissante dans la partie, et c'est correct ici : les onze cases d'une
  table de complements ne se hierarchisent pas en difficulte de facon assez nette pour qu'un ordre
  vaille mieux que le hasard.
- **Traitement de l'erreur** : joue dans les deux sens. Sur un coup faux, `Non, c'est 5.` - la
  bonne reponse est donnee, l'enfant n'est pas juge (formulation que j'avais moi-meme acceptee,
  `FICHE-bandit-manchot-additions-cp.md`, et je ne la rouvre pas). Apres trois echecs consecutifs,
  l'indice s'affiche, verifie en manche 4. Un enfant bloque peut donc avancer.
- **Exactitude du contenu** : **les 11 additions verifiees une par une**, la reponse ajoutee au
  nombre de l'enonce fait 10 dans les 11 cas. **Les 22 distracteurs verifies un par un** : tous
  entiers de 0 a 10, tous distincts entre eux et de la bonne reponse, aucun absurde. Le schema est
  lisible et pedagogiquement juste - le nombre de depart recopie (erreur de recopie) plus le
  complement a 9 (erreur de comptage d'un rang) ; sur `5 + ? = 10` ou la recopie donnerait la bonne
  reponse, l'auteur a mis 4 et 6, ce qui est le bon reflexe. **Les 11 indices verifiees un par un** :
  tous exacts, tous non vides, tous sur le meme modele des doigts, tous integralement accentues et
  correctement orthographies. Aucune fuite de la reponse dans l'enonce, hors le cas de
  `0 + ? = 10` traite en section 1.
- **Biais et ton** : aucun prenom, aucun personnage, aucune situation - ce paquet est purement
  numerique, il ne porte donc aucun stereotype. Le seul contexte est le corps de l'enfant (ses
  doigts), accessible a tous sans supposer un objet, un logement ni une configuration familiale.
  Aucun ton condescendant : les indices donnent une methode, ils ne commentent pas l'enfant.

### Motif

Le contenu est **exact sur les 11 items, les 22 distracteurs et les 11 indices**, il couvre la
table complete des complements a 10 sans se limiter aux cases faciles, il est ecrit dans un
francais integralement accentue et irreprochable, et l'aide apres trois echecs reste atteignable
dans une partie de sept manches - verifie en jouant, pas deduit. Les deux items degeneres sont
gardes parce qu'ils portent le couple (0, 10) de la table, pas parce qu'ils remplissent le paquet.
Je ne trouve rien qui trompe l'enfant ni rien qui soit mal calibre pour un CP. Je valide.

### Reserves

**Aucune reserve bloquante, et aucune reserve nouvelle propre a ce jeu.**

Deux points de contexte, qui ne sont pas des reserves de ce jeu et que je n'ouvre pas ici :

1. **Le pli.** Je n'ai pas mesure de rendu ce sprint, la capacite de design etant gelee par
   l'utilisatrice. `DEC-0016` amendee le 2026-08-11 assume ce defaut pour l'ensemble des jeux et
   `ADR-0028` le declare non gardable. Je maintiens la position que j'ai tenue au sprint 8 : ce
   n'est pas un motif de refus, et je ne le recompte pas jeu par jeu.
2. **Le rendu CSS** est connu insatisfaisant et gele par l'utilisatrice. Je ne l'ai pas juge et je
   ne le rouvre pas.

### Ce dont je reste seule garde apres ce verdict

A dire explicitement, sinon l'equipe croira couvert ce qui ne l'est pas. Aucun garde-fou de la CI
ne tient les proprietes suivantes sur ce paquet.

**Correction de fait du 2026-08-12, portant sur cette liste et sur aucun verdict.** Deux des quatre
points ci-dessous etaient inexacts, et je les corrige sur place plutot que de les effacer.

- **Point 1, l'exactitude arithmetique : faux, cette propriete est gardee.** J'avais ecrit qu'aucun
  test ne calculait `a + reponse == 10` et que `PaquetCpComplements10S9Test` ne verifiait que la
  structure et la couverture des valeurs de depart. En realite,
  `chaqueEnonceDemandeLeComplementA10DUnNombreEntre0Et10Inclus` extrait le nombre de depart de
  l'enonce, lit la bonne reponse de l'item, et affirme que leur somme vaut exactement 10. Etabli par
  la mutation de `yanis-qa` (section 4 de
  `squad/sprints/s09-mathematiques-cp-nombres-et-calculs/rapport-qualite.md` : bonne reponse de
  `7 + ? = 10` passee de `3` a `2`, test rouge), puis relu par moi dans le fichier de test.
- **Point 4, la plausibilite d'un distracteur : partiellement gardee, ce que je n'avais pas vu.**
  Trouve par ma propre relecture du test a l'occasion de cette correction, et non signale par un
  tiers. `auMoinsUnDistracteurParItemEstUneErreurFrequenteIdentifiable` exige que **au moins un** des
  deux distracteurs de chaque item soit l'une des deux erreurs frequentes nommees : le nombre de
  depart recopie tel quel, ou le complement a 9. Ce n'est pas rien, et je ne peux plus ecrire que
  cette propriete n'est tenue par rien. Ce qui reste sur ma main est enonce dans le point 4 reecrit.

Je conserve les numeros d'origine pour ne decaler aucun renvoi deja ecrit. **Mon constat de fond ne
bouge pas** : ce que la suite ne garde toujours pas, c'est ce que l'enfant **lit** - l'orthographe,
la verite d'un indice, le niveau de vocabulaire.

1. **L'exactitude arithmetique de chaque item : propriete gardee, je n'en suis pas le garde** (point
   corrige le 2026-08-12, voir ci-dessus). `PaquetCpComplements10S9Test` calcule bien
   `a + reponse == 10` sur chaque item. Je l'ai aussi fait a la main, onze fois, mais un test le
   referait si le fichier changeait sans moi.
2. **L'exactitude de chaque indice** - qu'il decrive une methode qui mene vraiment a la reponse.
   `ChaquePaquetPorteUnIndiceTest` verifie qu'un indice existe et n'est pas vide ;
   `AucunIndiceNeContreditUnAutreItemDuMemePaquetTest` verifie une coherence interne. **Ni l'un ni
   l'autre ne verifie qu'un indice est vrai.** **Precision du 2026-08-12, dans le sens defavorable** :
   la coherence interne que je credite au second est elle-meme vide sur le contenu servi aujourd'hui.
   Verifie dans le fichier de test a l'occasion de la correction ci-dessus : sa clause de production,
   `aucunIndiceDeProductionNeCorrespondALaFormeGeneraleAujourdHui`, epingle a **zero** le nombre
   d'indices de production qu'elle reconnait, et son message demande de la supprimer le jour ou ce
   compte change. Elle ne juge donc aucun indice d'aucun paquet servi a un enfant.
3. **L'orthographe et l'accentuation du texte que l'enfant lit.**
   `PropositionsAccentueesJouablesIT` ne couvre pas cette propriete : il verifie qu'une proposition
   accentuee reste **jouable** sans planter la manche, ce qui est une robustesse d'encodage, pas une
   correction de langue. Aucun test ne lit les indices comme du francais.
4. **La plausibilite du second distracteur, et la pertinence pedagogique des deux** (point corrige et
   restreint le 2026-08-12, voir ci-dessus). Le test tient deja qu'au moins un distracteur par item
   soit le nombre de depart recopie ou le complement a 9. Il ne dit rien du second distracteur, qui
   peut rester un nombre pris au hasard dans 0-10, et il ne dit rien du fait que les deux erreurs
   qu'il liste soient bien celles qu'un enfant de CP commet devant cette table - c'est mon jugement
   qui l'a etabli, pas le test, et un autre paquet pourrait lister deux erreurs mal choisies en
   restant vert.
