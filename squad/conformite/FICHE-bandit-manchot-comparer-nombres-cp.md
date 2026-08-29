# Fiche de conformite - bandit-manchot-comparer-nombres-cp

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du
  catalogue).
- **Contenu(s)** : `adapters/adapter-content/src/main/resources/contenu/cp-comparer-nombres.yaml`
  (a creer, `type: question`, au moins 20 items), story `S9-3` (`docs/backlog/backlog.md`). Contenu
  neuf, aucune matiere premiere identifiee dans `contenu-repris/` pour cette ligne (le fichier
  `contenu-repris/quiz/cp-comparer.yml` porte surtout des comparaisons de grandeurs physiques,
  hors perimetre de cette story).
- **Statut global** : **VALIDE SOUS RESERVE** (Done pedagogique accorde le 2026-08-12, second audit).
  **Le REFUSE du 2026-08-12 est leve** : son motif - un indice affirmant une faussete sur la suite des
  nombres - est corrige par la suppression du mot `juste`. Le refus n'est pas efface : il reste lisible
  ci-dessous avec sa date.
- **Ligne de matrice** : **reste `en cours`.** Le Done pedagogique est accorde, **mais il ne suffit pas
  a lui seul a faire passer la ligne a `couvert-valide`**, et c'etait ecrit dans cette fiche des son
  ouverture, avant tout audit. La competence du referentiel nomme "Comparer **et ranger** des nombres
  jusqu'a 100" ; "ranger" est differe par `DEC-0019` et n'est pas couvert par ce jeu. La ligne ne pourra
  passer a `couvert-valide` qu'apres une extension sur "ranger". C'est `camille-po` qui ecrit la matrice,
  pas moi.
- **Arbitrage prealable de mecanique du 2026-08-12** (`DEC-0023` point 6 : l'ecran intermediaire
  disparait) : ecrit dans `FICHE-bandit-manchot-additions-cp.md`, bloc de validation, section
  "Arbitrage prealable au developpement - la mecanique apres la disparition de l'ecran
  intermediaire". **Il vaut pour ce jeu comme pour les huit du catalogue**, la mecanique etant
  commune : conditions M1 a M13, dont **deux bloquantes** - M4 (le message d'erreur doit etre vu
  sans defiler a 360 x 640) et la clause 1 de M13 (apres une reponse, le focus ne va jamais sur la
  sortie, qui detruit la partie sans confirmation). Ce n'est pas un Done pedagogique, rien n'est
  code.
  **Mise a jour du 2026-08-29 (`camille-po`)** : ce compte est perime depuis le 2026-08-28. `M4` est
  sortie de la porte pedagogique (arbitrage de l'utilisatrice, `DEC-0053`) et ne bloquait deja plus
  depuis `DEC-0025` (2026-08-13) ; sa reference `360 x 640` est morte depuis `DEC-0040` (2026-08-22).
  Seule la clause 1 de `M13` reste bloquante par nature, et elle est tenue depuis le 2026-08-14.
  Detail : `squad/conformite/FICHE-bandit-manchot-additions-cp.md`, en-tete, et
  `squad/conformite/ARBITRAGE-M4-ET-BALAYAGE-DES-CONDITIONS-DE-MECANIQUE-2026-08-29.md`
  (`nadia-prof`). Le texte d'origine ci-dessus n'est pas efface.
- **Second verdict du 2026-08-13 sur le lot de cloture du sprint 10 (`643c6f4`)** : **REFUSE**,
  aucun Done pedagogique, **un seul motif bloquant** - **M13 clause 1 sur l'ecran de revelation de
  M5**. Sur ce jeu, je n'ai pas tire la configuration fautive, mais le mecanisme y est identique.
  **M4 est fermee sur ce jeu** par le resserrement de marges. Le verdict complet et l'etat de mes
  trente-huit conditions sont dans
  `squad/conformite/FICHE-bandit-manchot-additions-cp.md`, section "Second verdict du 2026-08-13". **Le
  refus du matin est corrige sur ses quatorze cas** et les clauses 2, 3, 4 et 5 de M13 sont tenues pour
  la premiere fois. **M4 ne bloque plus**, levee par `DEC-0025`, et mon constat n'est pas retire.
- **Derniere mise a jour** : 2026-08-13

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| mathematiques | CP (cycle 2) | Nombres et calculs | Comparer et ranger des nombres jusqu'a 100 (<, >, =) | `squad/programme/mathematiques/competences-cp.md` |

**Perimetre partiel, assume a l'ouverture** : cette fiche ne couvre que "comparer" (choisir le bon
signe entre deux nombres). "Ranger" (ordonner une suite de trois nombres ou plus) est differe par
`DEC-0019`, motif : risque de largeur de proposition non verifie sur le rendu existant, pas une
impossibilite de principe. Un Done pedagogique accorde ici ne peut donc pas, a lui seul, faire
passer la ligne de matrice a `couvert-valide` sans une extension ulterieure sur "ranger".

## Criteres pedagogiques attendus - camille-po

- [ ] Chaque enonce presente deux nombres entiers, chacun compris entre 0 et 100 inclus et ecrit
  separement de l'autre (les deux valeurs peuvent etre egales : c'est le cas requis par le critere
  sur "=" ci-dessous, par exemple "58 ___ 58"), separes par un espace a completer, et les trois
  propositions sont toujours exactement les trois signes "<", ">", "=" (jamais un nombre en
  proposition).
- [ ] Chacun des trois signes est la bonne reponse d'au moins un tiers des items (repartition
  equilibree, pour eviter qu'un enfant reussisse en devinant toujours le meme signe).
- [ ] Les items dont la bonne reponse est "=" representent au moins 20 % du paquet (l'egalite entre
  deux ecritures d'un meme nombre est reellement travaillee, pas un cas limite anecdotique).
- [ ] Au moins un quart des items dont la bonne reponse est "<" ou ">" oppose deux nombres separes
  de 10 ou moins (la comparaison ne se reduit pas a "le nombre le plus long gagne").
- [ ] Le paquet compte au moins 20 items.
- [ ] Chaque item porte un indice non vide, exact et comprehensible par un enfant de CP.
- [ ] Le paquet valide le schema `type: question` (`ADR-0007`) sans code Java neuf, sans gabarit ni
  feuille de style neuve ; les propositions sont des signes a un seul caractere.

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
  - **Mesure sur ce jeu** : une revelation mesuree, reponse revelee en **position 1 sur 3** - la
    tabulation reste dans les propositions et la partie survit. **Je n'ai donc pas constate la
    destruction sur ce jeu**, et je l'ecris plutot que de la supposer.
  - **Mais le defaut y est present**, et je le dis comme une deduction, pas comme une mesure : la loi
    verifiee sur 25 revelations est que la partie est detruite **si et seulement si** la reponse
    revelee occupe la derniere position de l'ordre des propositions. L'ordre est tire a chaque manche,
    donc la position 3 sur 3 arrive sur ce jeu comme sur les autres. Le remede est commun.
- **M4 sur ce jeu, mesuree apres le resserrement de marges** (pire cas de 38 etats de message et de
  5 ecrans de revelation, 360 x 640, pli a `y = 640`) :
  - echec avec indice, pire cas : **607,19 px**, soit **32,81 px au-dessus du pli** ;
  - ecran de revelation, pire cas : **579,19 px**, 60,81 px au-dessus ;
  - "Bravo !" : 495,19 px, 144,81 px au-dessus.
  - **M4 est TENUE sur ce jeu.** Elle etait a 651,19 px ce matin : **le resserrement de marges la
    ferme**, et ce jeu est l'un des trois qu'il ferme reellement.
- **Ce qui est corrige sur ce jeu et que je credite** : apres une bonne comme apres une mauvaise
  reponse, Tabulation puis Entree laisse **la partie en cours** ; le focus va sur l'**enonce** de la
  question neuve apres une bonne reponse (clause 4) et sur la **proposition pressee, barree**, apres une
  mauvaise (clause 2) ; sur l'ecran de revelation il va sur la **proposition qui porte la reponse
  revelee** (clause 5). Les quatre clauses sont tenues sur ce jeu, mesurees au clavier.
- **Ce qui est propre a ce jeu** : ses indices restent affiches et exacts - *"9 et 9, c'est le meme
  nombre : ils sont egaux"*, *"Compare les dizaines : 6 dizaines, c'est plus que 1 dizaine"*. Aucun ne
  nomme le signe attendu : M21 ne s'applique pas ici et son paquet ne declare rien, ce qui est le
  comportement voulu par defaut.
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
  - **M4, bloquante** : le bas du bloc de message est a **651,19 px**, soit **11,19 px sous le
    pli** de 640 px, dans l'etat "echec avec indice". L'enfant qui se trompe ne voit ni le
    message, ni l'indice, sans defiler.
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


### Verdict courant - second audit du 2026-08-12, sur le correctif livre : le REFUSE est leve

- **Verdict** : **VALIDE SOUS RESERVE.** Done pedagogique **prononce**. Le motif du refus du
  2026-08-12 - l'indice `56 vient juste avant 60 quand on compte` - est corrige, exactement comme
  demande : le mot `juste` est retire. Les trois reserves du premier verdict restent ouvertes ; elles ne
  conditionnaient pas la levee et je ne les transforme pas en motifs.
- **Date** : 2026-08-12 (second audit du meme jour, apres fusion du lot `lea-dev`).
- **Done technique** : acquis, verifie moi-meme dans mon worktree - `mvn -B verify` en BUILD SUCCESS,
  371 tests, aucun echec.
- **Audit joue** : **non degrade**, et plus large que le premier. Jar construit et lance depuis mon
  worktree `s09-nadia-verdicts-2`, joue par les vraies routes. **60 parties completes de 10 manches,
  soit 600 coups**, tous joues volontairement faux pour maintenir la serie d'echecs et forcer
  l'affichage de l'indice a chaque manche. Resultat : **les 24 items du paquet ont ete servis, et les
  24 indices ont ete lus a l'ecran** - dont celui de `56 ___ 60`, dans sa forme corrigee.
- **Ligne de matrice** : **elle ne bouge pas, et ce n'est pas une reserve deguisee.** Voir section 4.

#### 1. Ce que j'ai verifie du correctif

L'indice servi a l'ecran pour l'item `56 ___ 60` :

```
| Quel signe faut-il mettre ?
| 56 ___ 60
| 56 vient avant 60 quand on compte : il est plus petit.
```

**La phrase est maintenant vraie, et elle reste une methode utile.** `56 vient avant 60 quand on
compte` est exact : dans la comptine numerique, 56 precede effectivement 60. Retirer `juste` n'a pas
seulement supprime une faussete, ca a rendu la methode **generale** : "vient avant quand on compte
donc est plus petit" est la definition meme de l'ordre sur les entiers, et un enfant peut la
reappliquer partout. C'est mieux que la version fausse ne l'aurait ete meme si elle avait ete vraie.

- **Le correctif ne modifie qu'une ligne** : 1 insertion, 1 suppression dans
  `cp-comparer-nombres.yaml`, diff lu ligne par ligne. Aucun enonce, aucune bonne reponse, aucune
  proposition, aucun autre indice n'est touche.
- **Les 24 comparaisons et les 72 propositions servies a l'ecran correspondent exactement au
  fichier**, verifie sur 600 coups : **zero anomalie**. Les propositions sont toujours exactement les
  trois signes `<`, `>`, `=`, et **jamais un nombre** - le critere qui evite le pire piege de cet
  exercice, reverifie non plus sur 20 manches mais sur 600.
- **Les 24 comparaisons sont exactes**, recalculees une par une : 8 `<`, 8 `>`, 8 `=`.
- **Les 24 items sont tous atteignables** et aucun ne se repete dans une meme partie.
- **La position de la bonne reponse parmi les trois boutons est bien brassee** : 189 fois a gauche, 212
  au milieu, 199 a droite sur 600 coups. Aucun enfant ne gagne en tapant toujours le meme bouton.

#### 2. J'ai refait la relecture des 24 indices, et je confirme celle de `lea-dev`

`lea-dev` declare avoir relu les 24 indices un par un sans trouver d'autre defaut. **Je ne prends pas
cette declaration pour acquise : j'ai relu les 24 indices affiches a l'ecran et je la confirme.** Les
24 sont exacts, integralement accentues (`unités`, `même`, `égaux`) et correctement orthographies.
Aucun n'invoque la comparaison des dizaines la ou elle ne conclut pas - le piege evident de ce type de
paquet, evite sans exception.

Ce que la relecture me fait voir de mieux, et que je note comme un acquis et non comme un grief :
**le paquet enseigne quatre methodes distinctes, pas trois**, chacune employee la ou elle conclut.

- **Le nombre de chiffres**, quand ils different : `8`/`15`, `3`/`78`, `91`/`9`, `17`/`6`, `100`/`85` -
  **5 items**.
- **La comparaison des dizaines**, quand les dizaines different : `23`/`47`, `41`/`90`, `67`/`72`,
  `19`/`33`, `84`/`99`, `52`/`38`, `70`/`22`, `88`/`79`, `63`/`12` - **9 items**.
- **Le passage aux unites**, quand les dizaines sont egales : `45`/`40` - **1 item** (reserve 1).
- **La position dans la suite des nombres** : `56`/`60` - **1 item**, et c'est celui que le correctif
  vient de rendre juste.

#### 3. Correction de mon propre releve : 9 items de dizaines, pas 10

Je verifie mes constats avant de m'en servir, et celui-la etait faux d'une unite.

**J'ai ecrit dans mon premier verdict que la comparaison des dizaines etait employee sur "dix items".
Il y en a 9.** Le recompte est en section 2 : 9 items tranches par les dizaines, et j'avais
vraisemblablement compte avec eux l'item `56 ___ 60`, qui releve en realite d'une quatrieme methode -
la position dans la comptine - et non des dizaines. C'est d'ailleurs assez ironique : l'item porteur de
mon motif etait aussi celui que mon propre classement rangeait mal. Le total des inegalites, lui, etait
juste : 5 + 9 + 1 + 1 = 16.

**J'ai en revanche reverifie mon second chiffre, celui de l'ecart, et il etait exact** : 5 des 16
inegalites opposent deux nombres separes de 10 ou moins - `8`/`15` (7), `56`/`60` (4), `67`/`72` (5),
`45`/`40` (5), `88`/`79` (9) - soit 31 %, au-dessus du quart exige par `camille-po`. Les 16 ecarts ont
ete recalcules un par un.

Aucune de ces deux corrections ne change un verdict. Je les ecris parce qu'un constat inexact laisse
dans une fiche devient une fausse reference pour le prochain qui la lit.

#### 4. La ligne de matrice ne bouge pas, et c'etait ecrit avant l'audit

**Je le dis d'emblee pour qu'on ne le lise pas comme une reserve deguisee ni comme un refus
partiel** : le Done pedagogique de ce jeu est **accorde sans restriction sur son perimetre**. Ce qui
empeche la ligne de matrice de passer a `couvert-valide` n'est pas la qualite de cet increment, c'est
son perimetre, et ce perimetre etait pose **avant tout audit**.

- La competence du referentiel s'appelle "Comparer **et ranger** des nombres jusqu'a 100 (`<`, `>`,
  `=`)". Ce jeu couvre "comparer" - choisir le bon signe entre deux nombres - et il le couvre bien.
- "Ranger" - ordonner une suite de trois nombres ou plus - est **differe par `DEC-0019`**, pour un motif
  de rendu et non de principe.
- **L'entete de cette fiche l'annoncait des son ouverture** : "Un Done pedagogique accorde ici ne peut
  donc pas, a lui seul, faire passer la ligne de matrice a `couvert-valide` sans une extension
  ulterieure sur ranger." Je l'avais redit dans mon premier verdict. **Je ne deplace donc aucun
  poteau : je tiens celui qui etait plante avant que je joue.**

**Ce que `camille-po` peut ecrire** : la ligne "Comparer et ranger des nombres jusqu'a 100 (<, >, =)"
reste `en cours`, avec la mention que "comparer" est desormais **valide pedagogiquement** et que seul
"ranger" manque. Une ligne qui reste `en cours` alors que la moitie de sa competence est validee
merite de le dire, sinon la matrice perd de l'information que cette fiche detient.

### Ce qui a ete verifie - second audit du 2026-08-12

- **Ancrage au programme** : "Comparer et ranger des nombres jusqu'à 100 (`<`, `>`, `=`)",
  `squad/programme/mathematiques/competences-cp.md`, retrouvee au mot. C'est bien **cette** competence
  que l'increment travaille, sur sa moitie "comparer" : l'enfant choisit un signe, jamais un nombre, donc
  il compare et ne calcule pas. Les valeurs restent dans 0-100 bornes incluses, `0` et `100` presents et
  servis a l'ecran. **Perimetre partiel assume** (section 4).
- **Lisibilite au niveau vise** : consigne `Quel signe faut-il mettre ?` - cinq mots, et `signe` est du
  vocabulaire de CP. Les enonces font cinq a onze caracteres. Le trou est note `___`, notation scolaire
  habituelle que la consigne explicite. Rien a redire, reverifie a l'ecran sur les 24 items.
- **Vocabulaire** : les 24 indices n'emploient que `chiffre`, `dizaine`, `unité`, `nombre`, `compte`,
  `plus grand`, `plus petit`, `égaux`. Tout ce lexique est celui du CP. Aucun mot du registre abstrait
  (`inferieur`, `superieur`, `ordonner`) n'apparait, et c'est bien.
- **Charge cognitive** : la plus faible des quatre jeux du sprint, et c'est adapte. Deux nombres a
  regarder, trois signes, aucun calcul, aucun texte de situation.
- **Progression** : tirage sans remise verifie sur 60 parties - aucun item repete dans une partie - et
  **les trois signes arrivent melanges, jamais en bloc**, malgre l'ordre groupe du fichier (8 `<`, puis
  8 `>`, puis 8 `=`). Cet ordre aurait ete un defaut grave avec un tirage sequentiel ; je l'ai verifie
  plutot que suppose, sur 60 parties cette fois et non 2. **Je ne releve pas de symptome de difficulte
  constante sur ce jeu** - c'est le perimetre exact que `camille-po` a etabli en relisant mes fiches, et
  je le confirme : ici les quatre methodes de comparaison ont des exigences reellement differentes, un
  item a dizaines egales n'est pas de meme difficulte qu'un item a nombres de chiffres differents, et le
  vivier n'est donc pas plat comme il l'est sur les soustractions. Les deux manques de progression que je
  releve sont d'un autre ordre et restent en reserves 1 et 2.
- **Traitement de l'erreur** : reverifie sur 600 coups. `Non, c'est =.` puis indice apres trois echecs
  consecutifs, maintenu tant que la serie continue. **Et la seule phrase fausse du paquet, qui etait
  reservee a celui qui echoue, n'existe plus.** C'etait tout mon motif.
- **Exactitude du contenu** : **24 comparaisons reverifiees une par une, toutes exactes** ; 72
  propositions verifiees contre le fichier sur 600 coups, toujours les trois signes, jamais un nombre ;
  **24 indices reverifies un par un, et les 24 sont maintenant exacts.** Aucune fuite de la reponse dans
  l'enonce - impossible ici, la reponse etant un signe absent de l'enonce.
- **Biais et ton** : inchange. Paquet purement numerique, aucun prenom, aucun personnage, aucune
  situation, aucun stereotype possible. Les indices donnent une methode et ne commentent jamais l'enfant.

### Motif - second audit

**Le refus est leve.** Le mot `juste` est retire de l'indice de l'item `56 ___ 60`, qui dit desormais
`56 vient avant 60 quand on compte : il est plus petit.` La phrase est vraie - 56 precede bien 60 dans
la comptine numerique - et elle est meilleure que ce que je demandais : debarrassee de `juste`, la
methode devient generale et reapplicable, "vient avant quand on compte donc est plus petit" etant la
definition de l'ordre sur les entiers. J'ai lu cet indice **a l'ecran**, servi par les vraies routes,
ainsi que les 23 autres, sur 60 parties et 600 coups joues faux pour forcer leur affichage. J'ai refait
moi-meme la relecture des 24 indices que `lea-dev` declare avoir menee et je la confirme : les 24 sont
exacts, integralement accentues, et aucun n'invoque les dizaines la ou elles ne concluent pas. Le
correctif ne touche qu'une ligne : les 24 comparaisons, les 72 propositions - toujours les trois signes
et jamais un nombre - et les 23 autres indices sont intacts, verifies contre le texte servi, zero
anomalie sur 600 coups. Le Done technique est acquis (`mvn -B verify` en BUILD SUCCESS, 371 tests). Done
pedagogique **prononce**, sous les reserves ci-dessous. **La ligne "Comparer et ranger des nombres
jusqu'a 100 (<, >, =)" de `squad/programme/MATRICE-couverture.md` reste `en cours` et ne peut pas passer
a `couvert-valide`** : "ranger" est differe par `DEC-0019` et hors perimetre de ce jeu. C'etait ecrit
dans cette fiche avant tout audit, ce n'est pas une condition ajoutee apres coup, et `camille-po` gagne
a noter sur la matrice que la moitie "comparer" est desormais validee.

### Reserves - au 2026-08-12, second audit

Les trois reserves du premier verdict sont **reprises telles quelles et toujours ouvertes**. Elles sont
au backlog. Ce second audit n'en ajoute aucune.

1. **Un seul item sur les 16 inegalites demande de comparer les unites a dizaines egales** (`45 ___
   40`). Reprise sans changement, et reverifiee : le correctif ne touche aucun enonce. A ajouter trois ou
   quatre items a dizaines egales (`58 ___ 52`, `37 ___ 34`, `71 ___ 79`).
2. **Aucun item n'oppose deux nombres formes des memes chiffres inverses** (`24 ___ 42`, `35 ___ 53`).
   Reprise sans changement. C'est l'erreur la plus caracteristique du CP sur la numeration decimale.
3. **Un tiers du paquet est repondable sans comparer, et ce n'est la faute de personne** : les 8 items
   d'egalite ont tous la forme `n ___ n`. Reprise sans changement, avec la meme conclusion : c'est
   structurel dans le cadre pose, le seuil de 20 % d'egalites de `camille-po` est legitime, et la seule
   facon de rendre `=` non trivial serait de comparer une ecriture a un calcul (`3 + 2 ___ 5`), ce qui est
   une extension de perimetre a decider et non un defaut de `lea-dev`. Chiffre reverifie sur 60 parties :
   le vivier etant 8/8/8, environ 3 manches par partie de 10 se gagnent par simple appariement visuel.

Deux points de contexte que je n'ouvre pas, position inchangee depuis le sprint 8 : **le pli** (non
mesure ce sprint, capacite de design gelee, `DEC-0016` amendee le 2026-08-11 l'assume, `ADR-0028` le
declare non gardable - reserve et jamais motif) et **le rendu CSS**, connu insatisfaisant et gele par
l'utilisatrice. Je n'ai mesure aucun pixel ce sprint.

### Ce dont je reste seule garde apres ce second verdict

Le correctif est fusionne et la CI reste verte, mais **aucun garde-fou neuf ne couvre les proprietes
qui ont laisse passer ce refus.**

**Correction de fait du 2026-08-12, portant sur cette liste et sur aucun verdict.** J'avais ecrit
ici, et au meme endroit du premier verdict plus bas, que `PaquetCpComparerNombresTest` ne verifiait
pas la verite de la comparaison. **C'etait faux.**
`leSigneDeChaqueItemEstLeSigneMathematiquementCorrect` decoupe l'enonce sur le marqueur de trou,
recalcule le signe attendu depuis les deux nombres et le compare a la bonne reponse de l'item.
Etabli par la mutation de `yanis-qa` (section 4 de
`squad/sprints/s09-mathematiques-cp-nombres-et-calculs/rapport-qualite.md` : bonne reponse de
`56 ___ 60` passee de `<` a `>`, test rouge), signale par `sofia-sm`, puis relu par moi dans le
fichier de test - je ne reprends pas un fait sur parole apres m'etre deja trompee sur celui-la. Le
point 2 ci-dessous est corrige en consequence et sort de ce dont je reste seule garde ; je conserve
son numero pour ne decaler aucun renvoi deja ecrit. J'ai relu les cinq autres points a cette
occasion : aucun n'est garde, et le point 1 reste bien la propriete qui a laisse passer le refus.

**Mon constat de fond est inchange, et cette correction le rend plus exact** : ce que la suite ne
garde pas, c'est ce que l'enfant **lit** - la verite d'un indice au premier chef, ici. C'est bien la
comparaison elle-meme, et elle seule, qui etait deja tenue par un test.

1. **La verite de chaque indice. C'est la propriete qui a laisse passer ce refus, et elle n'est
   toujours tenue par rien.** `ChaquePaquetPorteUnIndiceTest` verifie qu'un indice existe et n'est pas
   vide ; `AucunIndiceNeContreditUnAutreItemDuMemePaquetTest` verifie une coherence **entre** items.
   Aucun des deux ne verifie qu'un indice dit quelque chose de **vrai**, et aucun ne pourrait le faire
   pour une affirmation comme "56 vient juste avant 60". C'est moi, et en jouant. **Precision du
   2026-08-12, dans le sens defavorable** : la coherence entre items que je credite au second est
   elle-meme vide sur le contenu servi aujourd'hui. Verifie dans le fichier de test a l'occasion de la
   correction ci-dessus : sa clause de production,
   `aucunIndiceDeProductionNeCorrespondALaFormeGeneraleAujourdHui`, epingle a **zero** le nombre
   d'indices de production qu'elle reconnait, et son message demande de la supprimer le jour ou ce
   compte change. Ce garde-fou ne juge donc aucun indice d'aucun paquet servi a un enfant, et l'indice
   faux de ce paquet ne pouvait de toute facon pas lui echapper : il n'etait pas regarde.
2. **L'exactitude de chaque comparaison : propriete gardee, je n'en suis pas le garde** (point corrige
   le 2026-08-12, voir ci-dessus). `PaquetCpComparerNombresTest` compare bien les deux nombres de
   l'enonce au signe attendu. Je l'ai aussi faite a la main, 24 fois, deux fois maintenant, mais un
   test la referait si le fichier changeait sans moi.
3. **La pertinence de la methode enseignee par un indice** - qu'elle soit applicable au cas presente,
   par exemple ne pas invoquer les dizaines quand elles sont egales. Tenue ici sur les 24 items, mais par
   l'attention de `lea-dev`, par aucun garde-fou.
4. **La couverture des erreurs caracteristiques du niveau** (reserves 1 et 2) : aucun test ne sait
   qu'un CP confond `24` et `42`.
5. **Le fait qu'un item soit repondable sans mobiliser la competence** (reserve 3).
6. **Le fait que tous les items d'un paquet soient reellement atteignables** et que les propositions
   servies soient celles du fichier : verifie moi-meme sur 600 coups, faute de garde-fou.

### Verdict du premier audit - S9-3, prononce sur l'increment livre, du 2026-08-12 (REFUSE, leve le meme jour au second audit ; conserve, jamais efface)

- **Verdict** : **REFUSE.** Done pedagogique **non** prononce. Motif en section 1 : un indice affirme
  une faussete sur la suite des nombres, dans le jeu dont la suite des nombres est precisement
  l'objet. **Le refus se leve par la suppression d'un mot** - je le dis d'emblee pour qu'on ne me
  prete pas un rejet de la conception du paquet, qui est la meilleure des quatre de ce sprint sur
  plusieurs points (section 3). `camille-po` en fait du backlog ; ce refus ne bloque pas le sprint
  suivant.
- **Date** : 2026-08-12.
- **Audit joue** : **non degrade.** Jar construit et lance depuis mon worktree
  `s09-nadia-verdicts`, joue par les vraies routes, deux parties completes de 10 manches (une en
  ratant tout, une en ratant les trois premieres manches puis en repondant juste). Les 24 items lus
  dans `adapters/adapter-content/src/main/resources/contenu/cp-comparer-nombres.yaml`.

#### 1. Motif du refus : "56 vient juste avant 60 quand on compte"

L'item `56 ___ 60` porte cet indice :

```
56 vient juste avant 60 quand on compte : il est plus petit.
```

**C'est faux.** Dans la suite des nombres, ce qui vient juste avant 60 est 59. Entre 56 et 60 il y a
57, 58 et 59. "Juste avant" designe le predecesseur immediat, et la locution `quand on compte`
interdit ici la lecture relachee ou "juste avant" voudrait dire "avant, pas loin" : elle renvoie
explicitement a la comptine numerique, ou "juste avant 60" ne designe qu'un seul nombre.

**Pourquoi c'est un refus et pas une reserve**, en trois points.

1. **La faussete porte exactement sur ce que le jeu enseigne.** Ce jeu travaille la comparaison des
   nombres jusqu'a 100, c'est-a-dire la construction de la suite ordonnee. Une erreur de vocabulaire
   ailleurs serait une maladresse ; ici, dire a un enfant de CP que 56 vient juste avant 60 attaque la
   chaine numerique qu'il est en train de construire. Un enfant qui retient cette phrase peut en
   conclure que 56 et 60 sont voisins, donc que 57, 58 et 59 n'ont pas de place entre eux - et c'est
   une erreur qu'on met des mois a defaire.
2. **La conclusion juste ne rachete pas la premisse fausse.** L'indice conclut "il est plus petit",
   ce qui est vrai. Mais un indice n'est pas la pour donner la reponse : il est la pour donner la
   **methode**. Une methode fondee sur une propriete fausse est une methode qui echouera des que
   l'enfant l'appliquera ailleurs.
3. **J'ai ecrit dans ma methode qu'une erreur factuelle dans un contenu scolaire est un motif de
   refus a elle seule, sans discussion.** Je refuse un autre paquet de ce sprint pour des fautes
   d'orthographe. Valider ici une affirmation mathematique fausse, dans le domaine meme de la story,
   ferait de ma porte un caprice plutot qu'une regle. Je m'applique la meme barre.

**Ce que la correction demande : retirer le mot "juste".**

```
56 vient avant 60 quand on compte : il est plus petit.
```

Un mot. Rien d'autre dans le paquet n'est a reprendre - **j'ai verifie les 23 autres indices un par
un et ils sont tous exacts** (section 2). Le refus est donc bon marche a lever, et c'est aussi pour
ca que je ne l'arrondis pas en reserve.

#### 2. Le critere corrige de S9-3 : je juge sur la version corrigee, et la correction est bonne

On m'a signale que le premier critere de S9-3 exigeait "deux nombres entiers distincts" tout en
imposant 20 % d'items d'egalite - contradiction reelle - et que `camille-po` a corrige la seule
formulation apres coup, en "ecrit separement de l'autre".

**Je juge sur le critere corrige, et je confirme que la correction est la bonne des deux.** C'etait
la formulation qui etait fautive, pas le contenu : deux nombres egaux **doivent** pouvoir se
rencontrer, sinon le signe `=` n'a aucun item, et `lea-dev` a lu "distincts" comme "ecrits
separement", ce qui est la seule lecture qui rende la story coherente. Les egalites prennent la forme
`58 ___ 58`, et c'est la seule forme possible d'une egalite entre deux ecritures d'entiers. **Aucun
grief de ma part**, ni sur la lecture de `lea-dev`, ni sur la correction de `camille-po`. Je note
seulement une consequence pedagogique, qui n'est la faute de personne, en reserve 3.

#### 3. Ce qui est bon dans ce paquet, et que la correction ne doit pas defaire

Je le note parce qu'un refus qui ne dit pas ce qui est acquis fait recommencer du bon travail, et
parce que ce paquet est le mieux construit des quatre sur deux points.

- **Les 24 comparaisons sont exactes**, verifiees une par une : 8 items `<`, 8 items `>`, 8 items
  `=`. Aucune erreur.
- **Les propositions sont toujours exactement les trois signes `<`, `>`, `=`, et jamais un nombre** -
  verifie sur les 24 items dans le fichier et sur les 20 manches jouees a l'ecran. C'est le critere
  qui evite le pire piege de ce type d'exercice, et il est tenu sans exception.
- **La question des distracteurs ne se pose meme pas ici**, et c'est une force de conception : les
  trois propositions etant les trois signes, l'enfant ne peut pas eliminer une reponse par son allure.
  Aucune regularite de magnitude n'est exploitable, a la difference du paquet des problemes additifs.
- **Les 23 autres indices sont exacts**, et surtout ils enseignent trois methodes reellement
  distinctes, chacune employee a bon escient : le nombre de chiffres quand ils different (`8` contre
  `15`, `91` contre `9`, `100` contre `85`), la comparaison des dizaines quand les dizaines different
  (dix items), et le passage aux unites quand les dizaines sont egales (`45` contre `40` :
  "Les deux ont 4 dizaines : regarde les unités, 5 unités c'est plus que 0 unité."). **Aucun indice
  n'applique la comparaison des dizaines a un cas ou elle ne conclut pas** - c'est le piege evident
  et il a ete evite.
- **Le critere d'ecart est tenu** : 5 des 16 items d'inegalite opposent deux nombres separes de 10 ou
  moins (`8`/`15` ecart 7, `56`/`60` ecart 4, `67`/`72` ecart 5, `45`/`40` ecart 5, `88`/`79` ecart
  9), soit 31 %, au-dessus du quart exige. La comparaison ne se reduit donc pas a "le nombre le plus
  long gagne".
- **Le francais est irreprochable** : les 24 indices sont integralement accentues et correctement
  orthographies ("unités", "même", "égaux"). Sur ce point, ce paquet est conforme la ou celui des
  soustractions ne l'est pas.

### Ce qui a ete verifie

- **Ancrage au programme** : "Comparer et ranger des nombres jusqu'à 100 (`<`, `>`, `=`)",
  `squad/programme/mathematiques/competences-cp.md`, retrouvee au mot. C'est bien **cette** competence
  que l'increment travaille, sur sa moitie "comparer" : l'enfant choisit un signe, jamais un nombre,
  donc il compare et ne calcule pas. Les valeurs restent dans 0-100 bornes incluses (`0` et `100`
  presents). **Le perimetre reste partiel** comme la fiche l'annonce a l'ouverture : "ranger" n'est
  pas couvert, et un Done accorde ici n'aurait de toute facon pas suffi a passer la ligne de matrice a
  `couvert-valide`.
- **Lisibilite au niveau vise** : consigne `Quel signe faut-il mettre ?` - cinq mots, et "signe" est
  du vocabulaire de CP (le signe plus, le signe egal). Les enonces font cinq a onze caracteres. Le
  trou est note `___`, qui est la notation scolaire habituelle et que la consigne explicite. Rien a
  redire.
- **Charge cognitive** : la plus faible des quatre jeux du sprint, et c'est adapte : deux nombres a
  regarder, trois signes a choisir, aucun calcul, aucun texte.
- **Progression** : tirage aleatoire sans remise, verifie sur deux parties completes - aucun item
  repete dans une meme partie, et **les trois signes arrivent melanges**, jamais en bloc, malgre
  l'ordre groupe du fichier (8 `<`, puis 8 `>`, puis 8 `=`). Cet ordre du fichier aurait ete un defaut
  grave avec un tirage sequentiel : l'enfant aurait repondu `<` huit fois de suite sans reflechir. Je
  l'ai verifie plutot que suppose. **Deux manques de progression restent**, en reserves 1 et 2.
- **Traitement de l'erreur** : la mecanique fait ce qu'elle doit (`Non, c'est =.` puis indice apres
  trois echecs consecutifs, verifie a l'ecran). **C'est a cet instant que l'indice fautif atteint
  l'enfant**, comme sur les soustractions : la seule phrase fausse du paquet est reservee a celui qui
  echoue.
- **Exactitude du contenu** : **24 comparaisons verifiees une par une, toutes exactes** ; 72
  propositions verifiees, toujours les trois signes, jamais un nombre ; **24 indices verifies un par
  un, 23 exacts et 1 faux** (section 1). Aucune fuite de la reponse dans l'enonce - impossible ici,
  la reponse etant un signe absent de l'enonce.
- **Biais et ton** : aucun prenom, aucun personnage, aucune situation - ce paquet est purement
  numerique et ne porte aucun stereotype. Les indices donnent une methode et ne commentent jamais
  l'enfant.

### Motif

**L'indice de l'item `56 ___ 60` affirme "56 vient juste avant 60 quand on compte", ce qui est
faux** : le predecesseur immediat de 60 est 59, et 57, 58, 59 se trouvent entre les deux. La locution
`quand on compte` renvoie explicitement a la suite des nombres, ou "juste avant" ne designe qu'un
seul nombre, et interdit donc la lecture relachee de "juste avant". Cette faussete porte exactement
sur ce que le jeu construit - la suite ordonnee des nombres jusqu'a 100 - et elle est affichee au
seul enfant qui a echoue trois fois de suite, donc a celui qui prendra la phrase pour une regle. Que
la conclusion de l'indice ("il est plus petit") soit vraie ne rachete pas la methode, puisque c'est
la methode que l'indice est charge de transmettre. **Correction attendue : retirer le mot "juste"**,
donnant "56 vient avant 60 quand on compte : il est plus petit." Rien d'autre dans le paquet n'est a
reprendre : les 24 comparaisons sont exactes, les 23 autres indices sont exacts, les propositions
sont toujours les trois signes et jamais un nombre, et le francais est irreprochable (section 3).

### Reserves

Reserves nouvelles, datees du 2026-08-12, a convertir en stories par `camille-po`. Elles ne
conditionnent pas la levee du refus, qui se leve avec la seule suppression d'un mot. La reserve 1
gagne a etre traitee dans la meme story, puisqu'elle touche le meme fichier.

1. **Un seul item sur les 16 inegalites demande de comparer les unites a dizaines egales.** C'est
   `45 ___ 40`. Les quinze autres se tranchent soit par le nombre de chiffres, soit par des dizaines
   differentes. Or comparer `58` et `52` est precisement le cas ou un CP se trompe : tant que les
   dizaines suffisent, il peut reussir sans jamais regarder les unites. **A ajouter : trois ou quatre
   items a dizaines egales** (`58 ___ 52`, `37 ___ 34`, `71 ___ 79`).
2. **Aucun item n'oppose deux nombres formes des memes chiffres inverses**, du type `24 ___ 42` ou
   `35 ___ 53`. C'est l'erreur la plus caracteristique du CP sur la numeration decimale - lire le
   nombre comme un couple de chiffres sans hierarchie - et le paquet ne la met jamais en jeu. **A
   ajouter : deux items d'inversion**, avec l'indice qui nomme la difference ("dans 24 il y a 2
   dizaines, dans 42 il y en a 4").
3. **Un tiers du paquet est repondable sans comparer, et ce n'est la faute de personne.** Les 8
   items d'egalite ont tous la forme `n ___ n` : l'enfant qui voit deux ecritures identiques repond
   `=` par simple appariement visuel, sans mobiliser aucune comparaison de grandeurs. C'est
   **structurel et non corrigeable dans le cadre pose** - entre deux entiers, une egalite ne peut
   s'ecrire qu'avec deux ecritures identiques - et le seuil de 20 % d'egalites voulu par `camille-po`
   est par ailleurs legitime : il faut que `=` existe comme reponse. Consequence chiffree : sur 10
   manches tirees dans un vivier de 8/8/8, environ 3 manches par partie se gagnent sans travailler la
   competence. **Ce que je demande n'est pas de retirer les egalites**, mais que `camille-po` sache que
   la seule facon de rendre `=` non trivial serait de comparer une ecriture a un calcul
   (`3 + 2 ___ 5`), ce qui est une extension de perimetre a decider, pas un defaut de `lea-dev`.

Deux points de contexte que je n'ouvre pas : **le pli** (non mesure ce sprint, capacite de design
gelee, `DEC-0016` amendee le 2026-08-11 l'assume, `ADR-0028` le declare non gardable - meme position
qu'au sprint 8, ce n'est pas un motif) et **le rendu CSS**, connu insatisfaisant et gele par
l'utilisatrice.

### Ce dont je reste seule garde apres ce verdict

1. **La verite de chaque indice.** **C'est la propriete qui a laisse passer ce refus.**
   `ChaquePaquetPorteUnIndiceTest` verifie qu'un indice existe et n'est pas vide ;
   `AucunIndiceNeContreditUnAutreItemDuMemePaquetTest` verifie une coherence **entre** items. Aucun
   des deux ne verifie qu'un indice dit quelque chose de **vrai**, et aucun ne pourrait le faire pour
   une affirmation comme "56 vient juste avant 60". Tant qu'aucun garde-fou n'existe, c'est moi, et
   en jouant. **Precision du 2026-08-12** : la coherence entre items que je credite au second est
   elle-meme vide sur le contenu servi aujourd'hui, sa clause de production epinglant a zero le nombre
   d'indices qu'elle reconnait. Voir le point 1 de la section du second verdict.
2. **L'exactitude de chaque comparaison - point faux, corrige le 2026-08-12.** J'avais ecrit ici
   qu'aucun test ne comparait les deux nombres de l'enonce au signe attendu et que
   `PaquetCpComparerNombresTest` ne verifiait que la structure, la repartition des signes et la forme
   des propositions. **C'est inexact** : ce test recalcule aussi le signe attendu depuis les deux
   nombres et le compare a la bonne reponse de l'item. Voir la correction datee de la section "Ce dont
   je reste seule garde apres ce second verdict" pour ce qui l'a etablie. Je laisse ce point a sa
   place et sous son numero, corrige et non efface. Je l'avais par ailleurs fait a la main, 24 fois.
3. **La pertinence de la methode enseignee par un indice** - qu'elle soit applicable au cas presente
   (ne pas invoquer les dizaines quand elles sont egales). Tenu ici sur les 24 items, mais par
   l'attention de l'auteure, par aucun garde-fou.
4. **La couverture des erreurs caracteristiques du niveau** (reserves 1 et 2) : aucun test ne sait
   qu'un CP confond `24` et `42`.
5. **Le fait qu'un item soit repondable sans mobiliser la competence** (reserve 3).
