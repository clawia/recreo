# Fiche de conformite - bandit-manchot-effets-activite-eps-cm1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-eps-effets-activite.yaml`
  (13 items, type `question`).
- **Entree de catalogue** : `bandit-manchot-effets-activite-eps-cm1`, titre servi *"Les effets de
  l'activite physique"*, consigne servie *"Lis bien, puis dis la cause commune, l'effort qui se
  distingue, ou la conduite de securite."*, `nombreDeManches: 10`.
- **Ligne declaree** : `EPS-CM1-10`.
- **Statut global** : **EN ATTENTE D'AUDIT.** Fiche ouverte par `camille-po` a l'ouverture du
  bloc de construction du sprint s43 (paquet et catalogue livres et fusionnes dans `round-two`,
  commit `e11a9325`). Le bloc de validation pedagogique reste vide jusqu'a ce que `nadia-prof`
  le remplisse. Premier paquet d'education physique et sportive du produit sur le niveau CM1.
- **Story** : `S43-EPS-EFFETS-ACTIVITE` - Connaitre les effets de l'activite physique sur le corps
  et les regles de securite qui l'entourent (`docs/backlog/backlog.md`).
- **Competence programme visee** : education physique et sportive, CM1 (cycle 3), domaine
  "Apprendre a entretenir sa sante" -
  `squad/programme/education-physique-et-sportive/competences-cm1.md`, `EPS-CM1-10`. Ligne
  `non couvert` dans `squad/programme/MATRICE-couverture.md` (ligne 1941) au moment d'ouverture
  de cette fiche.
- **Arbitrage pedagogique prealable deja rendu, cite en entier dans la story, aucun arbitrage
  neuf a router pour les conditions generales de la matiere**
  (`squad/programme/education-physique-et-sportive/competences-cm1.md`, Jugement de faisabilite,
  `nadia-prof`, 2026-09-03, ligne 10) : *"10 | Effets de l'activite physique et regles de securite
  | non couvert - jouable au choix textuel, sous `EPS-2` et `BIA-4` | 'Pourquoi s'echauffe-t-on
  avant de courir ?' est une connaissance exacte, utile, du niveau du CM1. Frontiere a tenir avec
  les sciences"*, avec les conditions valables sur toute la matiere (`EPS-1`, `EPS-2`, `EPS-3`,
  `BIA-4`) et la frontiere de recouvrement avec `SCI-CM1-09` deja servi, toutes citees mot pour
  mot dans la story.
- **Arbitrage prealable neuf, ajoute le 2026-09-25 par `nadia-prof` avant l'ecriture du paquet,
  discriminant CM1/CP** : `EPS-9`, cinq clauses (trois taches absentes du CP portant ensemble au
  moins deux tiers des items et chacune au moins trois items ; interdiction de repeter les taches
  deja servies au CP ; `CYC-1` dans les deux sens entre les bonnes reponses du CP et les
  distracteurs du CM1), cite en entier dans la story. Ce bloc pose une barre avant le premier item
  et ne prononce aucun Done pedagogique.
- **Lien decision** : `DEC-0099` (section 9.5).
- **Derniere mise a jour** : 2026-09-25 (ouverture, `camille-po`).

---

## Criteres d'acceptation de la story (`docs/backlog/backlog.md`, `S43-EPS-EFFETS-ACTIVITE`)

- Etant donne un item, quand il s'affiche, alors il porte sur un effet de l'activite physique sur
  le corps (echauffement, essoufflement, recuperation, hydratation pendant l'effort) ou sur une
  regle de securite qui entoure la pratique, jamais sur le sommeil, les repas ou l'hygiene du
  corps hors effort (frontiere avec les sciences, section "Recouvrement a ne pas
  double-compter").
- Etant donne un item, quand on verifie sa bonne reponse, alors aucune performance chiffree n'y
  est presentee comme une norme et aucun item ne compare les corps, les performances ou les
  capacites de deux enfants (`EPS-2`).
- Etant donne un item, quand on relit sa bonne reponse, alors elle ne fait porter aucune attitude
  ni conduite morale (jamais une reponse qui juge l'enfant "sage" ou "prudent") (`EPS-3`).
- Etant donne un item, quand on relit son enonce, alors il n'interroge jamais l'enfant sur sa
  propre pratique physique ni sur son propre corps (`BIA-4`).
- Etant donne un item qui s'appuie sur une regle (par exemple une regle de securite liee a
  l'effort), quand on verifie l'enonce, alors la regle est rappelee dans l'enonce ou
  universellement scolaire, jamais supposee connue sans etre donnee (`EPS-1`).
- Etant donne le paquet livre, quand on le relit dans son entier, alors plusieurs effets de
  l'activite physique et plusieurs regles de securite du libelle sont chacun en bonne reponse au
  moins une fois.
- Etant donne chaque item du paquet, quand il declare sa ligne de referentiel, alors il porte
  `EPS-CM1-10` et uniquement elle.
- Etant donne le Done pedagogique de ce paquet, quand `nadia-prof` le joue, alors elle confirme
  qu'aucun item ne derive vers le sommeil, les repas ou l'hygiene du corps, et que la matrice
  porte la mention exigee d'avance par le referentiel : *"connaissances qui entourent la pratique
  physique seules ; aucune activite physique, aucun geste et aucune performance ne sont servis
  par ce produit"*.
- **`EPS-9` (ajout du 2026-09-25), opposable au meme titre que les criteres ci-dessus** : les
  trois taches du paquet (relier deux effets a une cause unique ; distinguer les effets selon le
  type d'effort ; reconnaitre un signal d'alerte du corps et la conduite qui y repond) portent
  ensemble au moins deux tiers des items et aucune n'en porte moins de trois ; aucun item ne
  reprend une tache deja servie au CP (clause 2) ; aucune bonne reponse d'un item du CP ne devient
  un distracteur d'un item du CM1, et reciproquement (clause 3, `CYC-1`).

## Ce que cette story ne fait pas

Elle ne fait jamais executer, mesurer ni observer un geste physique de l'enfant - nature
irreversible du reste de la matiere, hors de portee du produit ; elle ne derive jamais vers le
sommeil, les repas ou l'hygiene du corps - competence deja portee par sciences et technologie
CM1 (`SCI-CM1-10`), hors perimetre de cette story ; elle ne fixe aucun contenu factuel precis
(quels effets, quelles regles retenues) - reste a l'auteur du paquet puis a l'audit de
`nadia-prof`.

---

## Bloc de validation pedagogique

### Verdict du 2026-09-25 (`nadia-prof`, s43, instance unique) : **VALIDE SOUS RESERVE**

**Done pedagogique prononce.** `camille-po` peut passer `EPS-CM1-10` a `couvert-valide` dans
`squad/programme/MATRICE-couverture.md`, **avec la mention exigee d'avance par le referentiel** :
*"connaissances qui entourent la pratique physique seules ; aucune activite physique, aucun geste et
aucune performance ne sont servis par ce produit"*. Quatre reserves, `EPS-CM1-R1` a `EPS-CM1-R4`.

C'est le verdict le plus long des trois lots de s43, parce que **deux de mes propres conditions se
sont averees inexactes ou impraticables une fois le paquet ecrit**, et que je corrige ce que j'ai
ecrit avant de m'en servir pour arbitrer.

#### 1. Ce que j'ai fait

**Audit complet, pas degrade.** Application lancee, jeu joue sur deux parties, dont une entiere en
me trompant volontairement sur chaque item avant de repondre juste. Apres une erreur : *"Ce n'est
pas ca. Essaie encore !"* et l'indice. Apres deux : *"Ce n'est pas ca. / Appuie sur la bonne
reponse :"*, l'enfant bloque avance. Comportement apres erreur hors de cause.

**J'ai lu les treize items un par un contre les trente-trois items du CP** (`cp-eps-corps-effort`,
22 items ; `cp-eps-regles-de-securite`, 11 items), comme je m'y etais engagee en posant `EPS-9` :
*"Je verifierai `EPS-9` en lisant les items un par un, pas en lisant un vert."*

#### 2. Ancrage au programme, et la frontiere avec les sciences

`EPS-CM1-10` : *"Connaitre les effets de l'activite physique sur le corps (echauffement,
essoufflement, recuperation, hydratation) et les regles de securite qui l'entourent."*

**La frontiere avec les sciences est tenue sans exception**, et c'est ce qui etait le plus expose :
**aucun des treize items ne porte sur le sommeil, sur les repas ni sur l'hygiene du corps hors
effort**. Aucun ne derive vers l'horizon long (le coeur renforce, l'endurance qui progresse) - la
derive que j'avais nommee comme le piege principal, parce qu'elle serait tombee dans
`SCI-CM1-10`. Aucun item ne reprend `cm1-sciences-nutrition` item 5. **Rien a redire sur ce point,
et c'etait le risque le plus grave.**

`EPS-1`, `EPS-2`, `EPS-3` et `BIA-4`, verifies item par item :

| Condition | Tenue | Ce que j'ai verifie |
|---|---|---|
| `EPS-1` | **oui** | les quatre conduites de la tache 3 (s'arreter, s'asseoir, boire, etirer doucement, prevenir un adulte) sont du bon sens scolaire universel, jamais une regle supposee connue |
| `EPS-2` | **oui** | **aucun chiffre nulle part** : ni dans un enonce, ni dans une proposition, ni dans un indice. Les efforts se decrivent par leur nature (*"un sprint de quelques secondes"*, *"toute la seance"*), jamais par un nombre. Aucun item ne compare deux enfants |
| `EPS-3` | **oui** | aucune bonne reponse ne juge l'enfant. Les conduites de la tache 3 repondent a un signal du corps, elles ne notent pas un comportement |
| `BIA-4` | **oui** | *"un eleve"*, *"que doit-on faire"* : jamais "toi", jamais "ton corps", jamais "ta pratique" |

**Exactitude** : les treize bonnes reponses sont des faits etablis de physiologie de l'effort.
Besoin d'oxygene des muscles, chaleur produite par le travail musculaire, perte d'eau par
transpiration, retour au calme progressif, point de cote, crampe. **Aucune erreur factuelle.** Sur
l'item 12, *"arreter l'effort et etirer doucement le muscle"* est bien la conduite enseignee face a
une crampe. Sur l'item 8, ecarter *"le rythme cardiaque plus rapide"* comme effet reserve a l'effort
long est juste, et cela ne dement pas le CP : le coeur accelere aussi sur un effort bref, ce que
l'item dit precisement.

**Aucun biais** : aucun personnage nomme, aucune comparaison entre enfants, aucun milieu suppose.
Les efforts cites (course, velo, natation, sprint, lancer, saut) sont tous scolaires.

#### 3. `EPS-9`, clause 1 - les trois taches et leur poids

| Tache | Items | Absente du CP ? |
|---|---|---|
| 1. relier deux effets a une cause unique | 1, 2, 3, 4 | **oui** - le CP donne une cause par effet et ne relie jamais deux effets |
| 2. distinguer les effets selon le type d'effort | 5, 6, 7, 8, 9 | **oui** - le CP traite "un effort" comme une categorie unique et n'oppose jamais deux efforts |
| 3. signal d'alerte du corps, et la conduite qui y repond | 10, 11, 12, 13 | **oui** - les onze items de securite du CP portent tous un danger **exterieur**, aucun un signal venu du corps |

**Le plancher des deux tiers est tenu largement** (13 items sur 13). **Le plancher de trois items par
tache est tenu, mais sans marge sur la tache 1** : l'item 3 ne fait pas reellement le travail
demande (`EPS-CM1-R1`), ce qui ramene la tache 1 a trois items exactement. Elle tient, je le
constate, et je note qu'il n'y a plus rien en dessous.

**Le retour au calme actif** (item 7) et **la progressivite de l'arret** sont bien les precisions de
CM1 que j'avais admises, et elles ne dementent jamais *"d'un temps de repos"* du CP.

#### 4. Je corrige deux choses que j'ai moi-meme ecrites, avant de m'en servir

**a) `EPS-9` clause 3, telle que je l'ai ecrite, est dementie par mon propre exemple de la clause 1.**

J'ai ecrit : *"Aucune bonne reponse d'un item du CP ne devient un distracteur d'un item du CM1."*
Les items 1, 2 et 3 du paquet emploient comme distracteurs *"le corps perd de l'eau en
transpirant"* et *"les muscles produisent de la chaleur en travaillant"*, qui sont, a *"parce que"*
pres, les bonnes reponses des items 10 et 9 du CP. **Au pied de la lettre, ma clause 3 est
enfreinte trois fois.**

**Elle l'est parce que je l'ai demande.** L'exemple admis que j'ai ecrit moi-meme dans la clause 1,
le 2026-09-25, est mot pour mot l'item 1 du paquet : *"L'essoufflement et le coeur qui bat plus vite
pendant un effort ont la meme cause. Laquelle ?"*, bonne reponse *"les muscles ont besoin de plus
d'oxygene"*, distracteurs *"le corps doit se refroidir"* et *"le corps perd de l'eau en
transpirant"* - et j'ai ajoute : *"deux causes reelles, mais d'autres effets"*. **Mes deux clauses
se contredisent dans le meme texte, et le developpeur a suivi celle qui portait un exemple.** Je ne
peux pas lui opposer l'autre.

**Ce que la clause 3 voulait dire, et ce que je fixe pour la suite** : `CYC-1` interdit qu'un item
de CM1 rende **fausse** une reponse que le CP enseigne. Un enonce vrai reemploye comme distracteur,
dans un item dont la question le rend **hors sujet** et non faux, ne tombe pas sous cette
interdiction. **Verifie sur le fond** : aucun des treize items ne dement quoi que ce soit que le CP
enseigne. `CYC-1` est tenu.

**b) L'exemplaire que j'ai mis dans le referentiel le 2026-09-03 n'etait deja plus jouable quand j'ai
ecrit `EPS-9`.**

Le referentiel dit, sous ma signature : *"'Pourquoi s'echauffe-t-on avant de courir ?' est une
connaissance exacte, utile, du niveau du CM1."* **C'est faux au sens ou la story pouvait s'en
servir** : cette question exacte est l'item 18 de `cp-eps-corps-effort` (*"Pourquoi echauffe-t-on
ses muscles avant un effort intense ?"*), et ma propre clause 2 d'`EPS-9` a ferme cette porte le
2026-09-25 en interdisant *"pourquoi l'une de ces trois regles existe"*. **J'ai donc mis en avant,
comme exemple de la ligne, la seule formulation que j'ai ensuite interdite.** Le paquet ne sert
aucun item d'echauffement, et c'est la consequence directe de cette contradiction, pas un oubli du
developpeur. Cela reste un trou de couverture : c'est `EPS-CM1-R3`, et je porte le motif.

#### 5. `EPS-9`, clause 2 - ce qui ne devait pas etre repete

Onze items sur treize ne repetent rien du CP : verifie contre les quatre taches de
`cp-eps-corps-effort` et les onze items de `cp-eps-regles-de-securite`. **L'item 3 fait exception**,
et c'est `EPS-CM1-R1`.

#### 6. Les reserves

> **`EPS-CM1-R1` - item 3 : la tache "relier deux effets" n'y est pas faite, et la bonne reponse est
> celle du CP (`EPS-9` clause 2).**
> *"Avoir soif pendant un effort et avoir la bouche seche ont la meme cause. Laquelle ?"*, bonne
> reponse *"le corps perd de l'eau en transpirant"*.
>
> **"Avoir soif" et "avoir la bouche seche" ne sont pas deux effets, c'est le meme.** La bouche
> seche est la sensation de la soif. L'item n'a donc qu'un seul effet a relier, et la tache que
> j'avais posee comme le premier discriminant du CM1 n'est pas executee. **La bonne reponse creditee
> est, a "parce que" pres, celle de l'item 10 du CP** (*"Pourquoi a-t-on soif pendant un effort
> physique ?"* - *"parce que le corps perd de l'eau en transpirant"*). Un enfant qui a joue le
> paquet de CP repond sans rien faire de neuf. **C'est exactement le "CP rallonge" contre lequel
> j'ai ecrit `EPS-9`**, et c'est la seule occurrence du paquet.
>
> **Correction a faire** : remplacer le second effet par un effet reellement distinct que le CP
> traite a part (par exemple *"Avoir soif pendant un effort et transpirer ont la meme cause."*), ou
> remplacer l'item. Dans les deux cas, verifier que la tache 1 remonte au-dessus de trois items.

> **`EPS-CM1-R2` - item 4 : la bonne reponse ne nomme pas de cause, et elle se reconnait a sa
> longueur.**
> *"La respiration qui reste rapide juste apres un effort et le coeur qui met du temps a ralentir
> ont la meme cause. Laquelle ?"*, bonne reponse *"le corps a besoin de temps pour revenir au calme
> apres l'effort"*.
>
> **Deux defauts, et le second je ne l'ai vu qu'en jouant.**
>
> a) **La reponse est circulaire.** La question demande pourquoi ces deux choses prennent du temps ;
> la reponse repond que le corps a besoin de temps. Ce n'est pas une cause, c'est la reformulation
> du phenomene. **Le CP fait mieux sur le meme objet** : son item 11 credite *"le corps a encore
> besoin de beaucoup d'air pour se calmer"*, qui nomme au moins l'air. Un item de CM1 ne peut pas
> etre en dessous de l'item de CP qu'il prolonge.
>
> b) **Vu a l'ecran, l'item se gagne sans le savoir.** Les trois propositions affichees sont *"le
> corps perd de l'eau en transpirant"*, *"les muscles produisent de la chaleur en travaillant"* et
> la bonne reponse : celle-ci est **la seule qui contienne "apres l'effort"**, mot present dans
> l'enonce, et **la seule longue des trois**. Elle se designe a vue.
>
> **Correction a faire** : crediter une cause reelle et de niveau CM1 - les muscles continuent de
> consommer de l'oxygene apres l'arret, et le corps doit le leur fournir -, et aligner la longueur
> des trois propositions.

> **`EPS-CM1-R3` - l'echauffement, premier element nomme par le libelle de `EPS-CM1-10`, n'est servi
> par aucun item.**
> Le libelle enumere *"(echauffement, essoufflement, recuperation, hydratation)"*. Le paquet sert
> l'essoufflement (items 1, 9), la recuperation (items 4, 6, 7) et l'hydratation (items 5, 8, 10).
> **L'echauffement : zero item sur treize.**
>
> Le critere d'acceptation ne demandant que *"plusieurs"* elements en bonne reponse, il est
> formellement tenu, et je ne refuse pas sur ce point. Mais la ligne se ferme en laissant dehors le
> premier mot de son propre libelle et le besoin que la story annonce (*"afin de savoir pourquoi on
> s'echauffe"*).
>
> **Le motif est de moi, pas du developpeur** : voir section 4.b. La seule voie de CM1 que ma clause
> 2 laisse ouverte est celle que j'avais admise dans la clause 1 sans l'exiger, **la progressivite
> de l'echauffement** - le CP ne connait que *"des mouvements legers"*.
>
> **Correction a faire** : ajouter au moins un item sur la progressivite de l'echauffement, sur la
> tache 2 de preference (quel effort demande l'echauffement le plus progressif), ce qui le tient
> hors des quatre taches deja servies au CP.

> **`EPS-CM1-R4` - reserve neuve, datee du 2026-09-25, trouvee en jouant : sur les items 5, 6 et 7,
> la bonne reponse est la seule proposition qui contienne un mot de duree.**
> Item 5 : *"une course longue qui dure toute la seance"* contre *"un sprint de quelques secondes"*
> et *"un lancer de balle"*. Item 6 : *"une randonnee a velo qui dure toute l'apres-midi"* contre
> *"un saut en longueur"* et *"un lancer de poids"*. Item 7 : *"une longue seance de natation"*
> contre *"un plongeon"* et *"un saut en hauteur"*.
>
> **Trois fois de suite, la bonne reponse est la plus longue des trois et la seule a porter
> "longue" ou "qui dure".** Un eleve de CM1 qui joue deux de ces items apprend la regle : choisir
> celle qui parle de duree. **Il gagne alors les trois sans rien savoir de la physiologie de
> l'effort**, ce que la ligne est censee enseigner. C'est la meme famille que `CAT-1`, que j'avais
> posee le 2026-09-07 pour l'EMC, transposee du ton a la forme.
>
> **Ce n'est pas un manquement a `EPS-9`, et je le dis pour ne pas deplacer mes propres poteaux.**
> L'item 5 est **mot pour mot l'exemple admis que j'ai ecrit moi-meme** dans la clause 1 de
> `EPS-9`. Le developpeur a generalise le patron que je lui avais donne ; il ne pouvait pas savoir
> que repete trois fois il devient un tell, parce que je ne l'avais pas vu non plus avant de jouer.
> **C'est une barre nouvelle et datee, pas un durcissement de l'ancienne.**
>
> **Correction a faire** : reecrire les items 6 et 7 pour que **les trois propositions portent
> chacune une qualification de duree** (par exemple, face a *"une longue seance de natation"* :
> *"une serie de plongeons courts"* et *"un aller-retour de bassin a pleine vitesse"*), de sorte que
> la discrimination porte sur la nature de l'effort. **L'item 5 reste tel quel** : je l'ai ecrit, et
> une occurrence isolee n'est pas une heuristique.

#### 7. Une inexactitude du commentaire du paquet, signalee et non mise en reserve

Le commentaire d'en-tete affirme, sous `EPS-9` clause 3 : *"aucun item de ce paquet ne nomme un
organe."* **C'est faux** : le coeur est nomme dans les items 1, 4 et 6, le mollet dans l'item 12.
L'exigence reelle - ne pas reprendre `cm1-sciences-nutrition` item 5, qui demande quel organe se
remplit d'air - **est tenue**, aucun item ne posant cette question. C'est la justification qui est
inexacte, pas le contenu. Je la signale parce qu'un commentaire qui affirme une propriete absente
est ce qui a deja fait passer `EMC-CM1-R1` a s42 ; je ne la compte pas dans les reserves.

#### 8. Ce dont je reste la seule garde sur cette ligne

L'equipe doit le savoir explicitement, sinon elle croira couvert ce qui ne l'est pas.

- **Aucun garde-fou du depot ne compare deux paquets** (`theo-archi`, avis de faisabilite de s43,
  sections 6 et 7.2). **Les trois clauses d'`EPS-9` n'ont aucun garde executable** : la difference
  reelle avec le CP a ete verifiee ici, a la lecture, et nulle part ailleurs.
- **`NIVEAU_BORNE` vaut `"CP"`** : aucune barre de longueur ne s'applique a ce paquet de CM1, alors
  que le paquet voisin de CP, lui, est borne. Rien dans le depot ne force ce paquet a etre plus
  exigeant que celui du CP.
- **Aucun garde-fou ne mesure qu'une bonne reponse se reconnait a sa forme.** `EPS-CM1-R2` b) et
  `EPS-CM1-R4` ont traverse une CI entierement verte. **Un test qui mord sur des chaines ne garde
  pas cette propriete** - c'est ce qui etait deja arrive a `S42-EMC-SYMBOLES-CM1`, ou
  `aucunEnonceNeReprendLePatronDejaServiAuCp` etait vert pendant que cinq items refaisaient le CP.

Relance du garde-fou d'identifiants sur ce diff : voir la note de `squad/decisions/REGISTRE-identifiants.md`.
