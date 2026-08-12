# Fiche de conformite - bandit-manchot-comparer-nombres-cp

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du
  catalogue).
- **Contenu(s)** : `adapters/adapter-content/src/main/resources/contenu/cp-comparer-nombres.yaml`
  (a creer, `type: question`, au moins 20 items), story `S9-3` (`docs/backlog/backlog.md`). Contenu
  neuf, aucune matiere premiere identifiee dans `contenu-repris/` pour cette ligne (le fichier
  `contenu-repris/quiz/cp-comparer.yml` porte surtout des comparaisons de grandeurs physiques,
  hors perimetre de cette story).
- **Statut global** : en construction.
- **Derniere mise a jour** : 2026-08-12

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

### Verdict courant - S9-3, prononce sur l'increment livre, du 2026-08-12

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
   en jouant.
2. **L'exactitude de chaque comparaison.** Aucun test ne compare les deux nombres de l'enonce au
   signe attendu ; `PaquetCpComparerNombresTest` verifie la structure, la repartition des signes et la
   forme des propositions, pas la verite de la comparaison. Fait a la main, 24 fois.
3. **La pertinence de la methode enseignee par un indice** - qu'elle soit applicable au cas presente
   (ne pas invoquer les dizaines quand elles sont egales). Tenu ici sur les 24 items, mais par
   l'attention de l'auteure, par aucun garde-fou.
4. **La couverture des erreurs caracteristiques du niveau** (reserves 1 et 2) : aucun test ne sait
   qu'un CP confond `24` et `42`.
5. **Le fait qu'un item soit repondable sans mobiliser la competence** (reserve 3).
