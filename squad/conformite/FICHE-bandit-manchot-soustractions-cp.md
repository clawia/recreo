# Fiche de conformite - bandit-manchot-soustractions-cp

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du
  catalogue).
- **Contenu(s)** : `adapters/adapter-content/src/main/resources/contenu/cp-soustractions.yaml` (a
  creer, `type: question`, au moins 20 items), story `S9-1` (`docs/backlog/backlog.md`). Contenu
  neuf, aucune matiere premiere identifiee dans `contenu-repris/` pour cette ligne.
- **Statut global** : **VALIDE SOUS RESERVE** (Done pedagogique accorde le 2026-08-12, second audit).
  **Le REFUSE du 2026-08-12 est leve** : son motif unique - l'orthographe des 24 indices - est
  corrige et verifie en jeu. Le refus n'est pas efface : il reste lisible ci-dessous avec sa date, et
  l'histoire de ce jeu se lit dans l'ordre.
- **Ligne de matrice** : **peut passer a `couvert-valide`** (ligne "Calcul mental - soustractions
  dans la limite de 10", `squad/programme/MATRICE-couverture.md`). C'est `camille-po` qui l'ecrit, pas
  moi. Aucune reserve ouverte ne restreint la couverture de la competence nommee par le referentiel.
- **Arbitrage prealable de mecanique du 2026-08-12** (`DEC-0023` point 6 : l'ecran intermediaire
  disparait) : ecrit dans `FICHE-bandit-manchot-additions-cp.md`, bloc de validation, section
  "Arbitrage prealable au developpement - la mecanique apres la disparition de l'ecran
  intermediaire". **Il vaut pour ce jeu comme pour les huit du catalogue**, la mecanique etant
  commune : conditions M1 a M13, dont **deux bloquantes** - M4 (le message d'erreur doit etre vu
  sans defiler a 360 x 640) et la clause 1 de M13 (apres une reponse, le focus ne va jamais sur la
  sortie, qui detruit la partie sans confirmation). Ce n'est pas un Done pedagogique, rien n'est
  code.
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

### Verdict courant - second audit du 2026-08-12, sur le correctif livre : le REFUSE est leve

- **Verdict** : **VALIDE SOUS RESERVE.** Done pedagogique **prononce**. Le motif du refus du
  2026-08-12 - les 24 indices ecrits dans un francais fautif - est corrige, et je l'ai verifie a
  l'ecran sur les 24 indices, pas dans le fichier. Les trois reserves du premier verdict restent
  ouvertes et sont deja au backlog ; elles ne conditionnaient pas la levee, je l'avais ecrit, et je
  ne les transforme pas en motifs maintenant.
- **Date** : 2026-08-12 (second audit du meme jour, apres fusion du lot `marc-dev`).
- **Done technique** : acquis, verifie moi-meme dans mon worktree - `mvn -B verify` en BUILD SUCCESS,
  371 tests (43 + 7 + 178 + 63 + 77 + 3), aucun echec. Je ne juge pas un increment qui plante.
- **Audit joue** : **non degrade**, et plus large que le premier. Jar construit et lance depuis mon
  worktree `s09-nadia-verdicts-2`, joue par les vraies routes. **60 parties completes de 10 manches,
  soit 600 coups**, tous joues volontairement faux pour maintenir la serie d'echecs et forcer
  l'affichage de l'indice a chaque manche. Resultat : **les 24 items du paquet ont ete servis, et les
  24 indices ont ete lus a l'ecran**, aucun laisse au fichier.

#### 1. Ce que j'ai verifie du correctif, indice par indice

`marc-dev` declare trois choses. Je les ai verifiees a l'ecran, sur le texte servi par les routes, et
je les confirme toutes les trois.

1. **`Leve` -> `Lève`, 24 sur 24.** Les 24 indices lus a l'ecran portent l'accent grave. Aucune
   exception.
2. **`baisse-en` -> `baisses-en`, 23 sur 23 occurrences.** Verifie sur les 23 indices concernes,
   affiches.
3. **Le 24e indice, celui de `10 - 0 = ?`, est laisse tel quel, et `marc-dev` a raison de le
   laisser.** Il ecrit `Lève 10 doigts, et n'en baisse aucun : ils restent tous levés.` Le `s`
   euphonique de l'imperatif n'apparait que devant les pronoms *en* et *y* **enclitiques**, c'est-a-dire
   soudes derriere le verbe (`baisses-en`). Ici `en` est **proclitique** : il precede le verbe dans une
   negation (`n'en baisse aucun`), il n'y a aucune liaison a soutenir, et la forme correcte est
   `baisse` sans `s`. C'est ce que j'avais compte a part dans mon releve sans l'expliciter ;
   l'explication de `marc-dev` est exacte et je l'inscris ici pour qu'elle ne se reperde pas.

**Et il a trouve une faute que mon releve ne pouvait pas voir, sur ce meme 24e indice** : `leves` ->
`levés`, participe passe accorde avec les doigts. Verifie a l'ecran : `ils restent tous levés`.

#### 2. Correction de mon propre releve : il y avait 48 fautes, pas 47

Je m'appuie sur mes constats, donc je les verifie avant de m'en servir, et celui-la etait incomplet.

**J'ai ecrit dans mon premier verdict que "371 tests verts et 28 regles ArchUnit n'ont pas vu 47
fautes de francais".** Le compte etait 24 (`Leve`) + 23 (`baisse-en`) = 47. Il manquait `leves` dans
`ils restent tous leves`. **Le chiffre juste est 48.** La raison de mon angle mort est instructive et
je l'ecris pour qu'on ne me croie pas exhaustive la ou je ne l'etais pas : **j'avais cherche sur
`Leve` avec une majuscule**, parce que le mot ouvre chaque indice, et un participe passe en bas de
casse au milieu d'une phrase echappait a cette recherche. Un releve mene sur une forme capitalisee ne
couvre pas les autres formes du meme mot. `marc-dev` a lu, lui, et il a mieux vu que moi.

Je ne me servais pas de ce chiffre pour fonder le refus - le refus tenait sur 24 sur 24 - mais un
constat inexact que je laisse dans une fiche devient une fausse reference pour le prochain qui la
lit.

#### 3. Ce que le correctif n'a pas touche, et que j'ai reverifie plutot que suppose

Mon premier verdict disait "la correction attendue est purement orthographique. Aucun item, aucun
distracteur, aucune methode d'indice n'est a revoir." Il fallait donc verifier que rien d'autre n'a
bouge, et que rien n'a casse en chemin.

- **Le correctif ne modifie que les 24 lignes d'indice** : 24 insertions, 24 suppressions dans
  `cp-soustractions.yaml`, diff lu ligne par ligne. Aucun `enonce`, aucune `bonneReponse`, aucun
  `distracteurs` n'est touche.
- **Les 24 soustractions et les 48 distracteurs servis a l'ecran correspondent exactement au
  fichier**, verifie sur les 600 coups : sur chaque manche, l'ensemble des trois propositions servies
  est bien la bonne reponse plus ses deux distracteurs. **Zero anomalie.**
- **Les 24 items sont tous atteignables** : les 24 ont ete servis. Aucun item mort dans le paquet.
- **Les 11 resultats de 0 a 10 restent couverts**, bornes incluses, comme au premier audit.
- **Les indices restent stables d'une occurrence a l'autre** : le meme item affiche toujours le meme
  indice sur les 60 parties. Aucun melange d'indice entre items.
- **La position de la bonne reponse parmi les trois boutons est bien brassee** : sur 600 coups, elle
  tombe 200 fois a gauche, 201 fois au milieu, 199 fois a droite. Un enfant ne peut donc pas gagner en
  tapant toujours le meme bouton. Je le note comme un constat mesure de ce second audit, pas comme une
  exigence que j'aurais posee avant.

#### 4. Pourquoi je leve, et pourquoi je ne relève pas la barre

**La barre que j'avais ecrite est atteinte au mot** : "deux substitutions dans un seul fichier,
`Leve` -> `Lève` et `baisse-en` -> `baisses-en`". Elles sont faites, sur la totalite des occurrences,
et une troisieme que je n'avais pas vue est faite en plus. Je leve.

**Je n'ajoute aucune condition apres coup.** Mes trois reserves du premier verdict - la consigne
`Combien en reste-t-il ?` au-dessus d'un enonce chiffre, l'absence de distracteur sur l'inversion des
termes, la difficulte constante dans une partie - etaient ecrites comme des reserves, avec la mention
explicite qu'elles ne conditionnaient pas la levee. Elles sont au backlog. Les convertir en motifs
maintenant ferait de ma porte un caprice, et c'est exactement ce que je me suis interdit au sprint 8.

Je vois cependant une chose de plus depuis ce second audit. Je l'ecris comme une **reserve nouvelle
et datee**, pas comme un durcissement de l'ancienne, et **elle ne conditionne rien** : voir reserve 4.

### Ce qui a ete verifie - second audit du 2026-08-12

- **Ancrage au programme** : inchange et reverifie. "Calcul mental - soustractions dans la limite de
  10", `squad/programme/mathematiques/competences-cp.md`, retrouvee au mot. Le correctif ne deplace pas
  la competence : il ne touche aucun enonce.
- **Lisibilite au niveau vise** : les 24 indices font une phrase et une seule, et cette phrase est
  maintenant du francais correct. C'etait le seul point qui restait ouvert au premier audit ; il est
  ferme. Un point mineur nouveau en reserve 4.
- **Vocabulaire** : `Lève`, `doigts`, `baisses-en`, `un par un`, `il n'en reste plus aucun`,
  `seul`, `ils restent tous levés`. Tout est accessible a un CP. Aucun mot du registre scolaire
  abstrait (`decomposer`, `difference`, `terme`) n'est employe, et c'est bien.
- **Accentuation** : je suis passee sur les 24 indices affiches en cherchant tout mot qui exigerait un
  accent et n'en porterait pas. **Il n'en reste aucun.** Les seuls mots accentuables du corpus sont
  `Lève` et `levés`, et les deux sont accentues.
- **Charge cognitive** : inchangee, bonne.
- **Progression** : inchangee. Le tirage est sans remise dans une partie - aucune repetition observee
  sur 60 parties - et la difficulte ne monte pas a l'interieur d'une partie. Ce symptome est celui que
  je nomme sur ce jeu et sur les problemes additifs, que je juge sans objet sur les complements a 10 et
  que je ne releve pas sur la comparaison. Il est route a `theo-archi` avec ce perimetre exact ; ce
  n'est pas un motif ici, et ce n'est pas ce paquet.
- **Traitement de l'erreur** : reverifie sur 600 coups. Coup faux -> `Non, c'est 4.`, la bonne reponse
  donnee sans juger l'enfant. Trois echecs consecutifs -> l'indice s'affiche, et tant que la serie
  d'echecs continue il reste affiche a chaque manche. **C'est le comportement que je voulais voir** :
  l'enfant qui decroche recoit une methode, et cette methode est desormais ecrite dans une langue
  juste. Un enfant bloque peut donc avancer.
- **Exactitude du contenu** : 24 soustractions, 48 distracteurs et 24 indices reverifies, cette fois
  sur le texte servi. Tout est exact, langue comprise.
- **Biais et ton** : inchange, rien a reprocher. Aucun prenom, aucun personnage, aucune situation. Le
  seul contexte est le corps de l'enfant.

### Motif - second audit

**Le refus est leve.** Les 24 indices de `cp-soustractions.yaml` ecrivent desormais `Lève` avec son
accent grave (24 sur 24) et `baisses-en` avec son `s` euphonique (23 sur 23 occurrences enclitiques),
et le 24e indice, correctement laisse en `n'en baisse aucun` puisque le pronom y est proclitique,
accorde en plus son participe (`ils restent tous levés`). J'ai lu les 24 indices **a l'ecran**, servis
par les vraies routes sur 60 parties et 600 coups joues faux pour forcer leur affichage, et non dans
le fichier. Le correctif ne touche que les lignes d'indice : les 24 soustractions, les 48 distracteurs
et la couverture des 11 resultats de 0 a 10 sont intacts, verifies contre le texte servi, zero
anomalie sur 600 coups. Le Done technique est acquis (`mvn -B verify` en BUILD SUCCESS, 371 tests).
Done pedagogique **prononce**, sous les reserves ci-dessous, qui ne conditionnaient pas la levee et
que je ne convertis pas en motifs. **La ligne "Calcul mental - soustractions dans la limite de 10" de
`squad/programme/MATRICE-couverture.md` peut passer a `couvert-valide`** - c'est `camille-po` qui
l'ecrit.

### Reserves - au 2026-08-12, second audit

Les reserves 1 a 3 sont celles du premier verdict, **reprises telles quelles et toujours ouvertes**.
Elles sont deja au backlog. La reserve 4 est nouvelle et datee de ce second audit.

1. **La consigne du jeu ne colle pas a la forme des enonces** (`Combien en reste-t-il ?` au-dessus de
   `7 - 7 = ?`, ou le pronom "en" n'a aucun referent a l'ecran). Reprise sans changement du premier
   verdict. Toujours pas un motif.
2. **Aucun distracteur n'attaque l'inversion des termes.** Reprise sans changement.
3. **La difficulte est constante a l'interieur d'une partie.** Reprise sans changement, cause dans la
   mecanique et non dans ce paquet, routee a `theo-archi`.
4. **Nouvelle, 2026-08-12 : trois indices ecrivent `baisses-en 1 seul` avec le chiffre 1 la ou le mot
   se lit mieux.** Items `9 - 1`, `10 - 1` et `6 - 1` : `Lève 9 doigts, puis baisses-en 1 seul.` Ce
   n'est pas une faute, et je ne la traite pas comme telle : `1 seul` est une quantite, coherente avec
   les chiffres du reste de l'indice. Mais dans l'expression `un seul`, le mot fonctionne comme un
   determinant plus que comme un compte, et un lecteur debutant butte sur un chiffre place la ou il
   attend un mot - il lit `baisses-en 1` puis doit revenir sur `seul`. `baisses-en un seul` se dechiffre
   d'un trait. **Je le pose explicitement comme une reserve nouvelle et non comme un durcissement du
   motif d'orthographe** : je ne l'avais pas relevee au premier audit alors que j'avais lu les 24
   indices, elle n'a donc jamais fait partie de la barre, et elle ne conditionne evidemment pas une
   levee deja prononcee. A traiter quand `camille-po` rouvrira ce fichier, pas avant.

Deux points de contexte que je n'ouvre pas, position inchangee depuis le sprint 8 : **le pli** (non
mesure ce sprint, capacite de design gelee, `DEC-0016` amendee le 2026-08-11 l'assume, `ADR-0028` le
declare non gardable - ce reste une reserve et jamais un motif) et **le rendu CSS**, connu
insatisfaisant et gele par l'utilisatrice. Je n'ai mesure aucun pixel ce sprint.

### Ce dont je reste seule garde apres ce second verdict

Le correctif est fusionne et la CI reste verte, mais **aucun garde-fou neuf ne couvre les proprietes
qui ont laisse passer les 48 fautes.** Je le redis, sinon l'equipe croira couvert ce qui ne l'est pas.

**Correction de fait du 2026-08-12, portant sur cette liste et sur aucun verdict.** J'avais ecrit
ici, et au meme endroit du premier verdict plus bas, qu'aucun test ne verifiait l'exactitude
arithmetique des items de ce paquet. **C'etait faux.**
`PaquetCpSoustractionsS9Test.chaqueEnonceEstUneSoustractionDeTermesEtDeResultatEntre0Et10SansResultatNegatif`
extrait les deux termes de l'enonce par expression reguliere, lit la bonne reponse de l'item et
affirme qu'elle vaut `premierTerme - secondTerme`. Etabli par la mutation de `yanis-qa` (section 4 de
`squad/sprints/s09-mathematiques-cp-nombres-et-calculs/rapport-qualite.md` : bonne reponse de
`9 - 5 = ?` passee de `4` a `3`, test rouge), signale par `sofia-sm`, puis relu par moi dans le
fichier de test avant d'ecrire cette correction - je ne reprends pas un fait sur parole apres m'etre
deja trompee sur celui-la. Le point 2 ci-dessous est corrige en consequence et sort de ce dont je
reste seule garde ; je conserve son numero pour ne decaler aucun renvoi deja ecrit.

**Mon constat de fond est inchange, et cette correction le rend plus exact, pas plus faible** : ce
que la suite ne garde pas, c'est ce que l'enfant **lit** - l'orthographe, la verite d'un indice, la
validite semantique d'une situation, le niveau de vocabulaire. C'est bien l'arithmetique, et elle
seule, qui etait deja tenue.

1. **L'orthographe et l'accentuation du texte destine a l'enfant.** Toujours aucun test. Les 371
   tests etaient verts pendant les 48 fautes et ils sont verts apres : leur couleur n'a rien dit sur
   cette propriete, ni avant ni maintenant. `PropositionsAccentueesJouablesIT` verifie qu'une
   proposition accentuee reste **jouable**, pas qu'un mot est bien ecrit, et il ne lit jamais les
   indices. **C'est moi, et en jouant.**
2. **L'exactitude arithmetique de chaque item : propriete gardee, je n'en suis pas le garde**
   (point corrige le 2026-08-12, voir ci-dessus). `PaquetCpSoustractionsS9Test` calcule bien
   `a - b == reponse` sur chaque item du paquet. Je l'ai verifiee a la main 24 fois, deux fois
   maintenant, mais contrairement aux autres points de cette liste, un test la referait si le fichier
   changeait sans moi.
3. **L'exactitude et la pertinence de chaque indice** - qu'il decrive une methode menant vraiment a
   la reponse. **Precision du 2026-08-12, dans le sens defavorable** : j'ecris plus bas, au premier
   verdict, que `AucunIndiceNeContreditUnAutreItemDuMemePaquetTest` tient au moins une coherence
   interne. Verifie dans le fichier de test a l'occasion de la correction ci-dessus : sa clause de
   production epingle a **zero** le nombre d'indices de production qu'elle reconnait, et son message
   demande de la supprimer le jour ou ce compte change. Elle ne juge donc aujourd'hui aucun indice
   d'aucun paquet servi a un enfant. Rien du tout n'est tenu sur ce point.
4. **La plausibilite d'un distracteur.**
5. **La coherence entre la consigne d'un jeu et la forme de ses enonces** (reserve 1).
6. **Le fait que tous les items d'un paquet soient reellement atteignables** et que les propositions
   servies soient celles du fichier. Je l'ai verifie moi-meme sur 600 coups faute de garde-fou ; c'est
   la seule des cinq qui restent sur ma main qui serait facile a tenir par un test, et je la signale
   comme telle a `camille-po`
   sans prescrire la solution technique - c'est `theo-archi` qui tranche.

### Verdict du premier audit - S9-1, prononce sur l'increment livre, du 2026-08-12 (REFUSE, leve le meme jour au second audit ; conserve, jamais efface)

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
2. **L'exactitude arithmetique de chaque item - point faux, corrige le 2026-08-12.** J'avais ecrit
   ici qu'aucun test ne calculait `a - b == reponse` et que `PaquetCpSoustractionsS9Test` ne verifiait
   que la structure et la couverture des resultats. **C'est inexact** : ce test lit aussi la bonne
   reponse de l'item et affirme qu'elle vaut `premierTerme - secondTerme`. Voir la correction datee de
   la section "Ce dont je reste seule garde apres ce second verdict" pour ce qui l'a etablie. Je
   laisse ce point a sa place et sous son numero, corrige et non efface. Je l'avais par ailleurs fait
   a la main, 24 fois.
3. **L'exactitude de chaque indice** - qu'il decrive une methode menant vraiment a la reponse.
   `ChaquePaquetPorteUnIndiceTest` verifie qu'un indice existe et n'est pas vide ;
   `AucunIndiceNeContreditUnAutreItemDuMemePaquetTest` verifie une coherence interne. Ni l'un ni
   l'autre ne verifie qu'un indice est **vrai**. **Precision du 2026-08-12** : la coherence interne que
   je credite au second est elle-meme vide sur le contenu servi aujourd'hui, sa clause de production
   epinglant a zero le nombre d'indices qu'elle reconnait. Voir le point 3 de la section du second
   verdict.
4. **La plausibilite d'un distracteur** - qu'il corresponde a une erreur qu'un enfant commet, et non
   a un nombre pris au hasard.
5. **La coherence entre la consigne d'un jeu et la forme de ses enonces** (reserve 1).
