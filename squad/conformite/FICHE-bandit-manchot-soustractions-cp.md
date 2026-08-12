# Fiche de conformite - bandit-manchot-soustractions-cp

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du
  catalogue).
- **Contenu(s)** : `adapters/adapter-content/src/main/resources/contenu/cp-soustractions.yaml` (a
  creer, `type: question`, au moins 20 items), story `S9-1` (`docs/backlog/backlog.md`). Contenu
  neuf, aucune matiere premiere identifiee dans `contenu-repris/` pour cette ligne.
- **Statut global** : en construction.
- **Derniere mise a jour** : 2026-08-12

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| mathematiques | CP (cycle 2) | Nombres et calculs | Calcul mental - soustractions dans la limite de 10 | `squad/programme/mathematiques/competences-cp.md` |

## Criteres pedagogiques attendus - camille-po

- [ ] Chaque enonce est une soustraction ecrite en chiffres, dont les deux termes et le resultat
  sont des entiers compris entre 0 et 10 inclus, sans resultat negatif.
- [ ] Chaque valeur entiere de 0 a 10 apparait au moins une fois comme resultat parmi les items
  (aucune borne de l'intervalle absente, a la difference du defaut deja corrige sur les additions
  par `DEC-0007`).
- [ ] Chaque item a exactement trois propositions (la bonne reponse, deux distracteurs numeriques
  distincts dans le meme intervalle).
- [ ] Au moins un distracteur par item correspond a une erreur de calcul frequente et identifiable
  (par exemple additionner au lieu de soustraire, ou inverser l'ordre des termes), jamais un nombre
  choisi sans rapport avec l'item.
- [ ] Le paquet compte au moins 20 items, pour eviter le defaut de variete deja releve sur "Le bon
  son" a son ouverture.
- [ ] Chaque item porte un indice non vide, exact et comprehensible par un enfant de CP.
- [ ] Le paquet valide le schema `type: question` (`ADR-0007`) sans code Java neuf, sans gabarit ni
  feuille de style neuve.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

### Verdict courant - S9-1, prononce sur l'increment livre, du 2026-08-12

- **Verdict** : **REFUSE.** Done pedagogique **non** prononce. Le Done technique est acquis, la
  mathematique du paquet est irreprochable, et je refuse quand meme : le motif est en section 1.
  `camille-po` en fait du backlog ; ce refus ne bloque pas le sprint suivant.
- **Date** : 2026-08-12.
- **Audit joue** : **non degrade.** Jar construit et lance depuis mon worktree
  `s09-nadia-verdicts`, joue par les vraies routes, deux parties completes de 10 manches : une en
  ratant tout, une en ratant les trois premieres manches puis en repondant juste - c'est celle-la
  qui met l'indice a l'ecran, et c'est celle-la qui porte mon motif. Les 24 items lus dans
  `adapters/adapter-content/src/main/resources/contenu/cp-soustractions.yaml`.

#### 1. Motif du refus : les 24 indices de ce paquet sont ecrits dans un francais fautif

**Les 24 indices du paquet, sans exception, ecrivent "Leve" au lieu de "Lève".** L'imperatif de
*lever* porte un accent grave : "Lève 7 doigts". Le paquet livre "Leve 7 doigts" 24 fois sur 24.

**23 des 24 indices ecrivent "baisse-en" au lieu de "baisses-en".** A l'imperatif, un verbe en -er
reprend son s devant les pronoms *en* et *y* : "baisses-en 5 un par un", comme "manges-en",
"donnes-en", "parles-en". Le paquet livre "baisse-en" 23 fois sur 23 occurrences.

Vu a l'ecran, pas seulement dans le fichier - manche 4 d'une partie ou j'avais rate les trois
premieres :

```
| Combien en reste-t-il ?
| 9 - 5 = ?
| Leve 9 doigts, puis baisse-en 5 un par un.
```

**Pourquoi c'est un refus et pas une reserve**, en trois points.

1. **C'est la seule exigence de redaction qui s'applique au texte destine a l'enfant, et elle est
   explicite.** L'amendement du 2026-08-04 a `DEC-0002` sort le contenu enfant du registre de style
   du depot **sauf** sur ce point : ce texte est "redige en francais correct et integralement
   accentue, orthographe et ponctuation irreprochables, **parce qu'il est lu par un enfant en cours
   d'apprentissage de la lecture**". Un mot mal orthographie 24 fois est exactement ce que cette
   exigence interdit, et c'est la seule regle de redaction que ce paquet avait a tenir.
2. **Ce texte est montre a l'enfant qui echoue, donc a celui qui a le plus besoin d'un modele
   juste.** L'indice ne s'affiche qu'apres trois echecs consecutifs. L'enfant de CP qui le lit est
   en train d'apprendre a la fois que "è" existe et que l'imperatif se lie a "en". Lui presenter
   "Leve ... baisse-en" au moment ou il decroche, c'est lui enseigner deux fautes pendant qu'on lui
   explique une soustraction.
3. **Ce n'est pas un accident isole, c'est le standard d'ecriture du paquet.** 24 sur 24 et 23 sur
   23. Un mot oublie une fois est une coquille ; un mot faux partout est une regle appliquee de
   travers, et c'est ce qui doit etre corrige.

**Ce que la correction demande** : deux substitutions dans un seul fichier
(`cp-soustractions.yaml`), "Leve" -> "Lève" et "baisse-en" -> "baisses-en". Aucun code, aucun
gabarit, aucun item a reecrire. Le refus est donc bon marche a lever, et c'est aussi pour ca que je
ne l'arrondis pas.

#### 2. Je ne deplace pas mes poteaux, et je le montre

**Ce refus n'invente pas une barre nouvelle.** Je l'ai verifie avant d'ecrire, en relisant les
indices des quatre paquets que j'ai deja valides. `cp-le-bon-son` (15 indices), `cm1-homophones`
(24), `cm1-conjugaison-present` (25), `cp-additions-jusqu-a-10` (1) : beaucoup de leurs indices ne
portent aucun caractere accentue, mais **aucun d'eux ne contient un mot qui en exigerait un**
("Compte sur tes doigts.", "On entend aussi ce son dans le mot grand.", "Remplace par 'et puis'..."
sont du francais correct sans accent requis). Un indice sans accent n'est pas un defaut ; un mot
prive de l'accent qu'il exige en est un.

**`cp-soustractions` est donc le premier paquet du depot a livrer un mot mal orthographie a
l'enfant.** Je n'ai valide aucun precedent comparable, et il n'y a pas de barre relevee apres coup.
Meme constat sur les trois autres paquets du sprint : `cp-complements-10`, `cp-comparer-nombres` et
`cp-problemes-additifs` sont tous integralement corrects sur ce point. Ce paquet est le seul.

#### 3. Ce qui est bon dans ce paquet, et que la correction ne doit pas defaire

Je le note parce qu'un refus qui ne dit pas ce qui est acquis fait recommencer du bon travail.

- **La mathematique est irreprochable** : 24 soustractions verifiees une par une, aucune erreur,
  aucun resultat negatif, tous les termes et tous les resultats entre 0 et 10.
- **Les 11 resultats de 0 a 10 sont tous couverts**, bornes incluses : 0 (`7-7`, `5-5`), 1, 2, 3, 4,
  5, 6, 7, 8, 9 (`10-1`), 10 (`10-0`). Le defaut de borne absente corrige par `DEC-0007` sur les
  additions n'est pas reproduit.
- **Les distracteurs sont bien concus** : 48 verifies un par un, tous entiers dans l'intervalle,
  tous distincts de la bonne reponse, aucun absurde. Le schema est lisible - la somme au lieu de la
  difference (`3 - 2` -> `5`), ou le second terme recopie (`3 - 2` -> `2`). Ce sont les deux erreurs
  qu'un CP commet vraiment.
- **Les indices decrivent une methode exacte** : le modele des doigts marche sur les 24 items,
  puisque le premier terme ne depasse jamais 10. Le contenu de l'indice est juste ; c'est sa langue
  qui est fautive.

**La correction attendue est purement orthographique.** Aucun item, aucun distracteur, aucune
methode d'indice n'est a revoir.

### Ce qui a ete verifie

- **Ancrage au programme** : "Calcul mental - soustractions dans la limite de 10",
  `squad/programme/mathematiques/competences-cp.md`, retrouvee au mot. C'est bien **cette**
  competence : des soustractions ecrites en chiffres, jamais une addition deguisee ni une situation
  a interpreter. La limite de 10 est respectee sur les deux termes et sur le resultat.
- **Lisibilite au niveau vise** : les enonces sont des ecritures chiffrees de sept a huit
  caracteres, rien a decoder. Les indices font une phrase et une seule - la longueur est bonne, la
  langue non (section 1).
- **Charge cognitive** : bonne. Un calcul, trois propositions numeriques, aucun texte a tenir en
  tete pendant le calcul.
- **Progression** : le tirage est aleatoire sans remise, donc l'ordre croissant du fichier n'impose
  aucune sequence a l'enfant - verifie sur deux parties, les resultats arrivent melanges, et aucun
  item ne se repete dans une meme partie. Ce que je note en revanche : **la difficulte ne monte pas
  a l'interieur d'une partie**, chaque manche est tiree dans le meme vivier. C'est la mecanique, pas
  ce paquet ; ce n'est pas un motif ici (reserve 3).
- **Traitement de l'erreur** : la mecanique fait ce qu'elle doit. Coup faux -> `Non, c'est 4.`, la
  bonne reponse est donnee sans juger l'enfant (formulation que j'ai moi-meme acceptee dans
  `FICHE-bandit-manchot-additions-cp.md`, et je ne la rouvre pas). Trois echecs consecutifs ->
  l'indice s'affiche. **C'est justement a cet instant que le defaut de langue atteint l'enfant.**
- **Exactitude du contenu** : **24 soustractions, 48 distracteurs et 24 indices verifies un par
  un.** La mathematique et la methode des indices sont exactes ; **la langue des 24 indices ne l'est
  pas** (section 1). Aucune fuite de la reponse dans l'enonce.
- **Biais et ton** : aucun prenom, aucun personnage, aucune situation - aucun stereotype possible.
  Le seul contexte est le corps de l'enfant, accessible a tous. Le ton des indices donne une
  methode, il ne commente jamais l'enfant. Rien a reprocher ici.

### Motif

**Les 24 indices de ce paquet - la totalite - ecrivent "Leve" au lieu de "Lève", et 23 d'entre eux
ecrivent "baisse-en" au lieu de "baisses-en".** Ce texte est celui que l'application affiche a un
enfant de CP apres trois echecs consecutifs, donc a celui qui a le plus besoin d'un modele
d'orthographe juste, et l'amendement du 2026-08-04 a `DEC-0002` exige de ce texte precisement une
orthographe irreprochable et une accentuation integrale. Ce n'est pas une coquille isolee mais le
standard d'ecriture du fichier entier, et c'est le premier paquet du depot dans ce cas - les trois
autres paquets de ce sprint sont corrects sur ce point. Correction attendue : deux substitutions
dans `cp-soustractions.yaml`, "Leve" -> "Lève" et "baisse-en" -> "baisses-en", sans toucher aux
items, aux distracteurs ni aux methodes, qui sont bons et que j'ai valides en section 3.

### Reserves

Reserves nouvelles, datees du 2026-08-12, a convertir en stories par `camille-po`. Elles ne sont pas
le motif du refus et **elles ne conditionnent pas la levee** : le refus se leve avec la seule
correction orthographique de la section 1.

1. **La consigne du jeu ne colle pas a la forme des enonces.** Le catalogue annonce
   `Combien en reste-t-il ?` au-dessus d'un enonce purement chiffre (`7 - 7 = ?`). Le pronom "en" n'a
   aucun referent a l'ecran : il n'y a ni objets ni quantite nommee, seulement deux nombres. L'enfant
   doit fournir lui-meme ce dont il reste quelque chose. Le jeu des additions dit
   `Combien ça fait ?`, qui est exact pour une ecriture chiffree. A trancher : soit la consigne
   devient `Combien ça fait ?`, soit elle est assumee comme un cadrage volontaire de la soustraction
   comme retrait. Je n'en fais pas un motif - c'est comprehensible et plutot aidant - mais c'est une
   incoherence entre deux jeux du meme catalogue.
2. **Aucun distracteur n'attaque l'inversion des termes.** Le critere de `camille-po` citait
   "inverser l'ordre des termes" comme erreur frequente attendue ; le paquet livre uniquement la
   somme et la recopie. Or l'inversion (`3 - 5` traite comme `5 - 3`) est une erreur tres courante au
   CP, et elle n'est jamais mise en jeu. A ajouter sur quelques items.
3. **La difficulte est constante a l'interieur d'une partie.** Les dix manches sont tirees dans le
   meme vivier, sans ordre de difficulte : un enfant peut recevoir `10 - 0` puis `9 - 7`. La cause
   est dans la mecanique, pas dans ce paquet, et je la signale comme symptome sans prescrire de
   solution technique - `theo-archi` traitera la cause s'il y a lieu.

Deux points de contexte que je n'ouvre pas : **le pli** (non mesure ce sprint, capacite de design
gelee, `DEC-0016` amendee le 2026-08-11 l'assume et `ADR-0028` le declare non gardable - je tiens la
meme position qu'au sprint 8, ce n'est pas un motif) et **le rendu CSS**, connu insatisfaisant et
gele par l'utilisatrice.

### Ce dont je reste seule garde apres ce verdict

Aucun garde-fou de la CI ne tient les proprietes suivantes, et le refus ci-dessus en est la preuve
directe : **371 tests verts et 28 regles ArchUnit n'ont pas vu 47 fautes de francais dans le texte
que l'enfant lit.**

1. **L'orthographe et l'accentuation du texte destine a l'enfant.**
   `PropositionsAccentueesJouablesIT` ne couvre pas cette propriete : il verifie qu'une proposition
   accentuee reste **jouable** sans planter la manche - une robustesse d'encodage, pas une correction
   de langue - et il ne lit jamais les indices. **C'est la propriete qui a laisse passer ce
   defaut.** Tant qu'aucun garde-fou n'existe, c'est moi, et en jouant.
2. **L'exactitude arithmetique de chaque item.** Aucun test ne calcule `a - b == reponse` ;
   `PaquetCpSoustractionsS9Test` verifie la structure et la couverture des resultats, pas la verite
   du calcul. Fait a la main, 24 fois.
3. **L'exactitude de chaque indice** - qu'il decrive une methode menant vraiment a la reponse.
   `ChaquePaquetPorteUnIndiceTest` verifie qu'un indice existe et n'est pas vide ;
   `AucunIndiceNeContreditUnAutreItemDuMemePaquetTest` verifie une coherence interne. Ni l'un ni
   l'autre ne verifie qu'un indice est **vrai**.
4. **La plausibilite d'un distracteur** - qu'il corresponde a une erreur qu'un enfant commet, et non
   a un nombre pris au hasard.
5. **La coherence entre la consigne d'un jeu et la forme de ses enonces** (reserve 1).
