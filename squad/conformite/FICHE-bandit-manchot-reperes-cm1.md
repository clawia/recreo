# Fiche de conformite - bandit-manchot-reperes-cm1

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du catalogue).
- **Contenu(s)** : `adapters/adapter-content/src/main/resources/contenu/cm1-reperes.yaml`,
  livre, `type: question`, 16 items, story `S20-2` (`docs/backlog/backlog.md`). Aucune matiere
  premiere identifiee dans `contenu-repris/` sur ce repere precis : le paquet est integralement
  neuf.
- **Statut global** : **VALIDE SOUS RESERVE par `nadia-prof` le 2026-08-27** (dernier bloc de cette
  fiche), apres reaudition du paquet corrige a `77dd467` : les deux motifs du refus sont traites,
  `REP-4` est tenue et mesuree, le verdict est redevenu visible sur 14 items sur 16. Quatre reserves
  non bloquantes ouvertes (`REP-7` a `REP-10`), dont une, `REP-8`, qui n'appartient pas a ce paquet
  et est routee comme story de catalogue. **Le motif du retrait decide par `DEC-0049` est leve** ; le
  geste sur `catalogue.yaml` appartient a `camille-po` et `sofia-sm`. L'historique ci-dessous n'est
  pas efface, et le refus du 2026-08-26 reste lisible avec ses chiffres.
- **Statut anterieur** : **REFUSE par `nadia-prof` le 2026-08-26** (`hs-nadia-arbitrages`, commit
  `9c8aa7b` - fiche faisant foi, non encore fusionnee dans `round-two` a la date de cette
  correction), deux motifs : le pli (verdict et indice entierement sous le pli sur 16 items sur
  16, plus grand bouton a 161 px sur trois lignes) et `REP-4` non tenue (quatre libelles ne
  pouvaient pas porter quatre reperes plus le cas correct - il en fallait cinq). Les deux causes
  racines sont dans sa propre specification `B.3`, pas dans le travail livre ; les seize phrases ne
  sont pas en cause ("la matiere est saine"). **Correction deposee ci-dessous par `lea-dev` le
  meme jour**, sur un worktree reparti de `round-two` a jour. Le retrait du catalogue qu'impose
  `DEC-0045` section 3 sur un `REFUSE` releve de `camille-po`/`sofia-sm`, pas de ce commit :
  `catalogue.yaml` n'est pas touche ici.
- **Correction du 2026-08-26 (`lea-dev`)** : un cinquieme libelle, "Point d'interrogation",
  distinct de "Point final" - les items 4-6 et 10-12 montrent desormais les deux libelles de point
  ensemble, forcant la distinction que le refus a mesuree absente (`REP-4`). Chaque item ne montre
  plus que **trois** propositions (`bonneReponse` + deux distracteurs), jamais quatre : mesure a
  l'appui (`squad/outils/nadia-prof/auditer-paquet.py`, meme outil que `nadia-prof`), quatre
  propositions retombaient dans le repli a quatre colonnes (police 1,875rem, deux rangees), qui a
  lui seul pousse le bas du cabinet a 492 px sur 551 - hors de portee de tout raccourcissement de
  libelle (`ADR-0029`, decision de forme qui appartient a `ines-design`, pas a ce paquet). A trois
  propositions, mesure refaite : le bas des propositions descend a 453 px (14 items sur 16) ou
  501 px (les deux items aux enonces les plus longs, non modifiables sur instruction de
  `nadia-prof`) ; le **verdict** est desormais au-dessus du pli sur **14 items sur 16** (516,19 px,
  35 px de marge) et le depasse de 13,19 px sur les deux items restants (564,19 px) ; l'**indice**
  reste sous le pli sur **16 items sur 16** (595,19 px, 44,19 px sous le pli au mieux ; 643,19 px,
  92,19 px sous le pli sur les deux items aux longs enonces). **Ce residu n'est pas ferme par ce
  commit** : la police (2rem, en gras) et le rembourrage (0,75rem/1,25rem) de `.proposition` a
  moins de quatre colonnes sont une decision de forme (`ines-design`), et les deux enonces les plus
  longs ne peuvent pas etre raccourcis (instruction de `nadia-prof`, "la matiere est saine"). Route
  a `sofia-sm` avec ce chiffre. `REP-5` (deux indices nommaient "prenom", items 8 et 9) et `REP-6`
  (espace ordinaire avant le point d'interrogation de l'item 15) sont corriges dans le meme commit.
- **Repartition des seize items par repere, pour l'audit de `nadia-prof` (documentee, non
  recalculee par un test - voir la section suivante)** : items 1-3 majuscule initiale, 4-6 point
  final, 7-9 majuscule au nom propre, 10-12 point d'interrogation, 13-16 aucune erreur. Cinq
  libelles fixes existent desormais : "Majuscule initiale", "Point final", "Majuscule au prénom",
  "Point d'interrogation", "Phrase correcte" - chacun distinct d'un seul repere (ou du cas
  correct). Chaque item n'en montre que trois, jamais les cinq a la fois (voir le commentaire du
  paquet servi pour le detail des compositions retenues, coherentes avec chaque phrase) ;
  "Phrase correcte" figure sur les seize items sans exception.
- **Arbitrage prealable de `nadia-prof` deja rendu, avant tout code** (`squad/conformite/
  ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-s13.md`, section B) : forme retenue et
  quatre conditions de composition du paquet, reprises ci-dessous. Aucun arbitrage supplementaire
  n'est du avant le premier increment.
- **Arbitrage prealable de mecanique en vigueur pour tout le catalogue** (`squad/conformite/
  FICHE-bandit-manchot-additions-cp.md`, conditions M1 a M13).
- **Derniere mise a jour** : 2026-08-27 (`nadia-prof`, reaudition du paquet corrige, refus leve en
  `VALIDE SOUS RESERVE`).

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| francais | CM1 (cycle 3) | Orthographe et grammaire | Reperer et corriger la majuscule et la ponctuation dans une phrase donnee (majuscule initiale, point final, majuscule au nom propre, point d'interrogation d'une question) | `squad/programme/francais/competences-cm1.md` |

**Origine de la competence** : detachee de "Relire et corriger son texte a l'aide de reperes
simples (majuscules, ponctuation)" et amendee au referentiel par `DEC-0046`, section 2, sur
proposition de `nadia-prof`. La ligne d'origine reste `non couvrable en l'etat` : ce paquet ne la
couvre pas, il couvre uniquement la connaissance des reperes sur une phrase donnee, jamais la
relecture par l'enfant de son propre texte.

**Forme retenue, dans les mots de `nadia-prof`** (`ARBITRAGE-PREALABLE-s19-quatre-proxys-en-
attente-depuis-s13.md`, section B.3) : une phrase unique par item, quatre propositions courtes qui
nomment ce qui manque - "il manque un point", "il manque une majuscule", "il manque une majuscule
au prenom", "cette phrase est correcte". Trois autres formes ont ete examinees et ecartees par
elle : quatre versions completes de la meme phrase (charge cognitive trop haute, rangees de
propositions trop larges - dette deja rencontree trois fois sur ce produit) ; un signe seul a
choisir en fin de phrase (glyphe d'un caractere dans un bouton prevu pour un mot, question de
lisibilite qui reviendrait a `ines-design` si elle etait retenue). Seule la forme B est retenue.

**Note du 2026-08-26 (`lea-dev`)** : les quatre libelles cites ci-dessus par `nadia-prof` sont ceux
de sa specification initiale, avant son propre refus du meme jour. Le paquet servi n'en porte plus
que le principe (des libelles courts qui nomment ce qui manque) ; le detail exact (cinq libelles,
dont "Point d'interrogation" distinct de "Point final") est dans la section "Correction du
2026-08-26" ci-dessus et dans le commentaire du paquet servi.

## Criteres pedagogiques attendus - camille-po

Repris des quatre conditions prealables de `nadia-prof` (`REP-1` a `REP-4`, memes references), plus
la checklist d'ouverture habituelle de ce produit :

- [ ] Chaque phrase est courte, de vocabulaire CM1, et ne contient aucune virgule (`REP-1`).
- [ ] Au moins un quart des items ne porte aucune erreur, et leur bonne reponse est "cette phrase
  est correcte" (`REP-2`).
- [ ] Un seul repere est en jeu par item portant une erreur - jamais une phrase qui cumule deux
  erreurs a la fois (`REP-3`).
- [ ] Les quatre reperes de l'intitule (majuscule initiale, point final, majuscule au nom propre,
  point d'interrogation d'une question) sont chacun representes, aucun ne depassant la moitie des
  items (`REP-4`).
- [ ] Chaque item porte un indice non vide qui aide a reperer le signe attendu, sans jamais nommer
  la reponse.
- [ ] Le paquet compte au moins 16 items.
- [ ] Le paquet valide le schema `type: question` (`ADR-0007`) sans code Java neuf, sans gabarit ni
  feuille de style neuve.

## Ce que cette fiche ne couvre pas

`REP-1` a `REP-4` portent sur la composition du paquet (presence de virgules, part d'items sans
erreur, unicite du repere en jeu par item, equilibre des quatre reperes) : aucun test de ce depot
ne les verifie automatiquement, meme famille que `DEN-1` a `DEN-8` et `NAT-1` a `NAT-8`. `nadia-prof`
signale elle-meme que `REP-2` (le quart d'items sans erreur) est la plus facile a perdre, un paquet
ou toutes les phrases sont fautives se lisant tres bien a l'ecriture.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent. Il reste vide tant qu'aucun
> increment n'a ete livre et joue.

### Verdict du 2026-08-26 (s20) - `S20-2`, "Majuscule ou point ?"

- **Auteur** : nadia-prof. **Increment** : `round-two` a `f4e0808`, paquet `cm1-reperes`, jar
  construit depuis cet arbre, application **arretee par son PID**.
- **Audit joue, non degrade.** **3 parties, 54 lectures, 16 items sur 16 vus**, plus deux campagnes
  de mesure du pli, a la reference **744 x 551**, au repos et apres un echec.
- **Releve** : `squad/conformite/releves-s20/s20-audit-reperes-744x551.json`. Captures :
  `s20-reperes-744x551-repos.png`, `s20-reperes-744x551-echec.png`.
- **Un conflit d'interet que je declare avant de juger** : **cette competence est la mienne**. J'en
  ai propose l'intitule, la section du referentiel, la forme d'item et les quatre libelles, et
  `DEC-0046` les a inscrits sur ma seule parole. Le risque n'est pas que je sois trop dure, il est
  que je valide ma propre idee. C'est pourquoi je n'ai rien pris pour acquis de ce que j'avais
  ecrit, et pourquoi ce qui suit met en cause **ma specification** avant le travail de `lea-dev`.

## Verdict : **REFUSE**, sur deux motifs mesures. **Les deux causes racines sont les miennes.**

### 1. Ce qui est tenu, et je le dis d'abord parce que c'est la plus grande part du lot

| Condition | Etat | Verifie comment |
|---|---|---|
| **`REP-1`** aucune virgule, vocabulaire CM1 | **tenue** | **0 virgule sur 16 enonces**, compte caractere par caractere ; vocabulaire courant, phrases courtes |
| **`REP-2`** au moins un quart d'items sans erreur | **tenue** | **4 sur 16, soit 25 %** exactement |
| **`REP-3`** un seul repere en jeu par item fautif | **tenue** | verifie item par item : chaque phrase fautive est par ailleurs correcte |
| Exactitude du contenu | **tenue, 16 sur 16** | chaque enonce relu ; **item 16, "Nous allons a la piscine chaque samedi.", est un bon piege** - un jour de la semaine ne prend pas de majuscule en francais, et l'item est juste |
| Propositions distinctes, 4 par item | **tenue** | verifie sur les 16 |
| **Aucune fuite positionnelle** | **tenue** | l'ordre des quatre libelles est **brasse par item** - 13 ordres distincts observes - et la bonne reponse tombe **5 / 3 / 5 / 3** sur les positions 1 a 4. C'etait le defaut de forme le plus probable ; il n'y est pas |

`lea-dev` a fait ce que je lui avais demande, et elle l'a fait juste.

### 2. Premier motif de refus - **le pli**, et ce n'est pas une barre neuve

Mesure a 744 x 551, **defilement naturel, jamais force**, sur les **seize** items :

| Mesure | Resultat |
|---|---|
| Propositions par item | 4, sur **2 rangees, 16 items sur 16** |
| Hauteur du plus grand bouton | **161 px** - le libelle "Il manque une majuscule au prenom." tient sur **trois lignes** |
| Propositions entierement au-dessus du pli | **2 sur 16** |
| **Verdict entierement visible apres un echec** | **0 sur 16** - pire bas **662,2 px** |
| **Indice entierement visible apres un echec** | **0 sur 16** - pire bas **741,2 px**, soit **190,2 px sous le pli** |

**La capture le montre sans qu'on ait besoin de me croire** : la seconde rangee de boutons est
coupee en plein mot par le bas de la fenetre, et le retour "Ce n'est pas ca. Essaie encore !" est
entierement hors champ. **L'enfant presse un bouton et ne voit pas s'il a eu raison.**

**Je n'invente aucune exigence** : c'est la barre que j'applique depuis s17, ou j'ai refuse
`cm1-synonymes` parce que le bloc juste/faux etait sous le pli, et depuis s18, ou j'ai refuse un lot
pour un indice tronque par le pli sur 8 items sur 24. **Ici c'est 0 sur 16, pas 8 sur 24.** Refuser
moins durement que ces deux fois-la ferait de ma porte une faveur.

**Et la cause est ma specification, pas le travail de `lea-dev`.** Dans ma section `B.3` j'ai ecarte
la forme A - quatre versions completes de la phrase - en invoquant la dette de largeur de rangee de
ce depot, puis j'ai recommande la forme B au motif que ses **"propositions courtes"** l'evitaient.
**Les libelles que j'ai donnes dans la meme phrase font jusqu'a 34 caracteres.** Ils ne sont pas
courts, ils tombent dans la dette exacte contre laquelle je venais de mettre en garde. `lea-dev` a
recopie mes libelles au caractere pres ; c'est moi qui les avais mal choisis.

### 3. Second motif de refus - **`REP-4` n'est pas tenue** : le quatrieme repere n'est pas travaille

`REP-4` demande que **les quatre reperes de l'intitule soient chacun representes**, et j'avais ecrit
le motif a cote : *"Sans cela, la competence annoncee n'est pas celle qui est travaillee."* C'est
avec ce motif que je la lis.

La repartition annoncee est exacte - trois items par repere, plus quatre corrects. **Mais deux de
ces reperes partagent le meme libelle** : *"Il manque un point."* est la bonne reponse pour le point
final (items 4 a 6) **et** pour le point d'interrogation (items 10 a 12), soit **6 items sur 16**.

**J'ai mesure ce que cela permet, plutot que de le supposer.** J'ai simule un enfant qui **ignore
totalement l'existence du point d'interrogation** - il sait seulement qu'une phrase commence par une
majuscule, se termine par un signe, et qu'un prenom prend une majuscule :

> **Il obtient 16 sur 16.** Et le nombre d'items ou il faut savoir qu'une question prend un point
> d'interrogation plutot qu'un point est de **zero**.

C'est trait pour trait la famille de defaut que j'ai refusee sur `NAT-1` - une regle mecanique qui
resout tout sans la competence. Un enfant peut finir ce jeu avec un score parfait **en croyant que
le point d'interrogation n'existe pas**, et rien, ni a l'ecran ni ailleurs, ne le signale.

**La cause racine est encore la mienne, et elle est arithmetique.** Mon intitule nomme **quatre**
reperes ; ma forme B propose **quatre** libelles dont l'un est *"Cette phrase est correcte."*.
**Quatre libelles ne peuvent pas porter quatre reperes plus le cas correct : il en faut cinq.** Je ne
l'ai pas vu en ecrivant `B.3`.

### 4. La lecture de `lea-dev` sur les quatre libelles : **elle decrit exactement ce que j'avais ecrit, et ce que j'avais ecrit etait faux**

On me demande de dire si je partage sa lecture - que *"il manque un point"* sert aux deux reperes et
que *"c'est la structure de la phrase, pas le texte du bouton, qui porte le quatrieme repere"*.

**Sa description de ma specification est juste** : c'est bien ce que j'ai ecrit, elle l'a verifie
contre le backlog au lieu de le supposer, et elle l'a signale au lieu de le taire. **C'est le bon
geste et je le dis sans reserve.**

**Sa conclusion, en revanche, ne tient pas - et c'est ma phrase qui ne tient pas.** La structure
interrogative de la phrase ne **porte** rien tant que l'enfant n'a **jamais** a s'en servir pour
repondre. Un repere qu'aucun item n'oblige a mobiliser n'est pas represente : il est decoratif. Les
chiffres de la section 3 le tranchent - zero item l'exige, 16 sur 16 sans lui.

### 5. Ce qui manque, precisement, pour que le prochain increment ne soit pas un troisieme essai a l'aveugle

Les deux motifs se reparent ensemble, et **la meme correction les traite tous les deux** :

1. **Un cinquieme libelle doit distinguer le point d'interrogation du point final**, et **au moins
   trois items doivent exiger la distinction** - c'est-a-dire qu'une phrase interrogative sans signe
   final ait pour bonne reponse le libelle du point d'interrogation, et une phrase declarative sans
   signe final celui du point.
2. **Les libelles doivent tenir dans le pli.** La barre est mesurable et c'est la seule que je pose :
   a **744 x 551**, apres un echec, **le verdict et l'indice doivent etre entierement au-dessus du
   pli sur 16 items sur 16**. C'est ce que j'ai mesure ici et c'est ce que je remesurerai.

**Ces deux exigences tirent dans le meme sens** : cinq propositions au lieu de quatre aggravent la
hauteur si les libelles restent longs, et l'imposent donc courts. **Je ne dicte pas les libelles** -
je les ai deja mal choisis une fois, et ce n'est pas en les choisissant une seconde fois que je
ferai mieux. Je fixe le resultat ; la forme revient a `lea-dev`, et la mise en page des cinq
propositions a `ines-design` si elle doit y regarder.

**Ce que je ne demande pas** : ni de reduire le nombre d'items, ni de toucher aux seize phrases, qui
sont bonnes. La matiere est saine ; ce sont les boutons et un libelle manquant qui ne le sont pas.

### 6. Deux points **NON BLOQUANTS**, qui ne motivent pas ce refus

- **`REP-5` (2026-08-26)** - **Deux indices nomment la reponse.** Sur les items 8 et 9, l'indice dit
  *"Cherche le prenom cache au milieu de la phrase."* et *"Un prenom se cache dans cette phrase."*,
  alors que **le seul libelle contenant le mot *prenom* est la bonne reponse**. Un enfant peut
  apparier les deux mots sans rien savoir. L'indice n'apparait qu'apres un echec, ce qui en limite la
  portee, mais la mecanique connait deja ce defaut sous le nom `M21` - un indice qui nomme la reponse
  est une revelation deguisee. L'item 7, sur le meme repere, montre la bonne facon de faire :
  *"Un des mots de cette phrase designe quelqu'un en particulier."*
- **`REP-6` (2026-08-26)** - **Espace ordinaire avant le point d'interrogation.** L'item 15,
  *"Ou habite ton cousin Paul ?"*, emploie une espace ordinaire la ou la typographie francaise
  demande une insecable - et la ou le paquet voisin `cp-nature-mot` en emploie **32**. Aucun effet
  visible a 744 px, ou la phrase tient sur une ligne. Mais **cet item est precisement celui dont la
  bonne reponse est "Cette phrase est correcte."** : une phrase presentee a l'enfant comme
  irreprochable doit l'etre jusqu'a sa typographie.

### 7. Ce que je ne compte pas contre ce contenu

**Le fait que ce jeu ait ete expose au catalogue sans aucun audit n'entre pas dans ce verdict.**
C'est un defaut de gouvernance, `lea-dev` a suivi la definition de fini qu'on lui a donnee, aucun
garde-fou n'encode la regle de `DEC-0045`, et je l'ai deja route a `camille-po` et `sofia-sm` dans
mon verdict `NAT-5`. Il ne pese pas d'un gramme sur la qualite du paquet.

**Ce refus renvoie l'increment a `camille-po`, qui en fait du backlog. Il ne bloque pas le sprint
suivant.** La ligne de referentiel ajoutee par `DEC-0046` **ne peut pas passer `couvert-valide`**
tant que le quatrieme repere n'est pas travaille.

**Une consequence que je nomme sans la trancher** : par la regle generale de `DEC-0045` section 3, un
verdict `REFUSE` retire le jeu du catalogue jusqu'a sa reprise. Le geste appartient a `camille-po` et
`sofia-sm`, pas a moi ; je ne touche pas a `catalogue.yaml`.

### 8. Sur quelles proprietes je reste la seule garde

- **`REP-1` a `REP-4`** - aucune n'est tenue par un test, et `lea-dev` a eu **raison** de ne pas les
  encoder : elle ecrit qu'elle *"ne voulait pas inventer un classifieur heuristique fragile qui
  donnerait un faux sentiment de preuve"*. C'est exactement mon avis, je l'avais ecrit moi-meme, et
  un classifieur qui se trompe serait pire que rien. **La contrepartie est que je suis le seul
  garde-fou de ces quatre conditions, et ce verdict montre qu'il en fallait un** : `REP-4` est tombee
  sans qu'aucune CI ne bronche.
- **La suffisance d'un jeu d'etiquettes.** Rien ne verifie qu'un ensemble de propositions fixes
  couvre les reperes que la competence annonce. C'est un comptage que seul un humain fait, et je
  viens de me tromper dessus la premiere fois.
- **Le pli a 744 x 551.** Aucun test du depot ne mesure ce que l'enfant voit sans defiler.

---

## Verdict du 2026-08-27 (s20, cloture) - reaudition du paquet corrige. **VALIDE SOUS RESERVE**

- **Auteure** : nadia-prof. **Increment** : `round-two` a `77dd467`, paquet `cm1-reperes` corrige
  par `lea-dev` (`bd87e91`, fusionne en `09e1641`). **Le refus du 2026-08-26 n'est pas efface** : il
  reste au-dessus, avec ses chiffres. Ce verdict-ci s'y ajoute et le leve en partie.
- **Audit joue, non degrade.** Le jeu etant hors catalogue depuis `DEC-0049`, je l'ai joue comme
  `cp-nature-mot` le 2026-08-25 : **jar construit depuis l'arbre tel quel**, puis la seule ressource
  `catalogue.yaml` reecrite dans une **copie du jar hors du depot**, l'entree `bandit-manchot-
  reperes-cm1` restauree a l'identique de `f4e0808`. `git status --porcelain` vide avant et apres ;
  **je n'ai touche ni a `catalogue.yaml` ni a aucun fichier de code**, et **je n'ai lance aucun
  `mvn`** (`yanis-qa` tenait le reacteur). Application arretee par son PID.
- **Objet mesure verifie, pas suppose** : les ressources du jar sont identiques a l'octet a celles de
  l'arbre a `77dd467` (sept fichiers compares par `sha256`, dont `contenu/cm1-reperes.yaml`,
  `static/css/recreo.css` et `templates/fragments/manche.html`), le 2026-08-27.

## Verdict : **VALIDE SOUS RESERVE**

**Les deux motifs du refus du 2026-08-26 sont traites. Aucun des deux ne subsiste sous la forme qui
avait motive le refus.** Ce qui reste est un residu de pli que je nomme, que je chiffre, et que je
route **hors de ce paquet** - pour une raison que j'ai mesuree aujourd'hui et qui me force a
corriger une phrase de mon propre refus.

### 1. `REP-4` est tenue. Mesuree, pas admise sur la description de la correction

Commande exacte, le 2026-08-27 : `python squad/outils/nadia-prof/auditer-paquet.py <url_locale>
bandit-manchot-reperes-cm1 16 <fichier_de_releve>`, `CHROME` et `PAQUET` positionnes, viewport
744 x 551. **4 parties, 76 lectures, 16 items sur 16 vus**, chacun au repos et apres une erreur
volontaire. Releve : `squad/conformite/releves-s20/s20-reaudit-reperes-corrige-744x551.json`.

Et surtout, la mesure qui compte : **j'ai rejoue la simulation qui avait fonde le refus**, sur le
paquet servi aujourd'hui, en simulant un enfant qui **ignore totalement l'existence du point
d'interrogation** - il sait qu'une phrase commence par une majuscule, se termine par un signe, et
qu'un prenom prend une majuscule.

| Simulation "l'enfant qui ignore le point d'interrogation" | 2026-08-26 (refus) | **2026-08-27 (paquet corrige)** |
|---|---|---|
| Score obtenu sans connaitre le repere | **16 sur 16** | **13 sur 16** |
| Items qui exigent la distinction point final / point d'interrogation | **0** | **3** (items 10, 11, 12) |

Les trois items qu'il rate sont exactement *"Ou as-tu range tes affaires"*, *"Pourquoi les feuilles
tombent-elles en automne"* et *"Quel age a ton petit frere"* : il repond *"Point final"* la ou la
reponse est *"Point d'interrogation"*. **La regle mecanique qui resolvait tout le paquet ne le
resout plus.** C'est precisement ce que j'avais exige : *"un cinquieme libelle distinguant le point
d'interrogation, au moins trois items exigeant la distinction"*. **Trois sur trois. La barre que
j'avais ecrite est atteinte, je la leve sans marchander.**

Repartition verifiee dans le meme geste, le 2026-08-27, par lecture du paquet servi item par item :
`Majuscule initiale` 3, `Point final` 3, `Majuscule au prenom` 3, `Point d'interrogation` 3,
`Phrase correcte` 4 - **aucun repere au-dela du quart du paquet**, la ou `REP-4` tolerait la moitie.

### 2. Les autres conditions, remesurees sur le paquet servi

| Condition | Etat au 2026-08-27 | Verifie comment |
|---|---|---|
| **`REP-1`** aucune virgule, vocabulaire CM1 | **tenue** | **0 virgule sur 16 enonces**, comptage caractere par caractere sur le paquet servi |
| **`REP-2`** au moins un quart sans erreur | **tenue** | items 13 a 16, **4 sur 16 = 25 %** |
| **`REP-3`** un seul repere par item fautif | **tenue** | relu item par item : chaque phrase fautive est par ailleurs correcte |
| **Exactitude du contenu** | **tenue, 16 sur 16** | les seize phrases sont celles du 2026-08-26, que j'avais deja relues ; *"Nous allons a la piscine chaque samedi."* reste un bon piege, un jour de la semaine ne prend pas de majuscule |
| **`REP-5`** un indice ne nomme pas la reponse | **corrigee** | items 8 et 9 ne disent plus *"prenom"* : *"designe une personne precise"*, *"designe quelqu'un"*, sur le modele de l'item 7 |
| **`REP-6`** typographie de l'item donne pour correct | **corrigee** | l'item 15 porte bien une espace **insecable** (`U+00A0`) avant son point d'interrogation - et c'est la **seule** ponctuation double du paquet, verifie par balayage de tous les enonces le 2026-08-27 |
| Accentuation integrale du texte enfant | **tenue** | inventaire des caracteres non-ASCII du paquet servi le 2026-08-27 : `U+00A0`, et les seules lettres accentuees attendues en francais |

### 3. Le pli : ce que le refus reprochait n'y est plus, et ce qui reste n'appartient pas a ce paquet

**Le defaut que j'avais mesure et decrit etait celui-ci :** *"L'enfant presse un bouton et ne voit
pas s'il a eu raison."* Mesure du 2026-08-27, meme outil, meme reference, defilement naturel jamais
force (**0 lecture a defilement non nul sur 76**) :

| Mesure a 744 x 551, apres un echec | 2026-08-26 (refus) | **2026-08-27 (corrige)** |
|---|---|---|
| Verdict **entierement visible** | **0 sur 16** (pire bas 662,2 px) | **14 sur 16** (516,19 px) |
| Verdict sous le pli | 16 sur 16 | **2 sur 16** (564,19 px, soit 13,19 px sous le pli) |
| Indice **entierement visible** | **0 sur 16** (pire bas 741,2 px) | **0 sur 16** (595,19 px ; 643,19 px sur deux items) |
| Pire enfoncement de l'indice sous le pli | **190,2 px** | **92,19 px** |
| Plus grand bouton | 161 px, trois lignes de texte | **74 px, une seule ligne**, sur les 16 items |

Capture apres un echec : `squad/conformite/releves-s20/s20-reperes-corrige-744x551-apres-echec.png`.
**Le verdict "Ce n'est pas ca. Essaie encore !" et le bouton fautif barre sont lisibles**, la ou le
2026-08-26 la seconde rangee de boutons etait coupee en plein mot. Sur les deux items dont l'enonce
passe a deux lignes (*"Nous partons demain matin pour la montagne"*, *"Pourquoi les feuilles
tombent-elles en automne"*), le texte du verdict est **coupe en deux dans la hauteur des lettres** -
haut a 534,19 px, pli a 551 px : capture
`squad/conformite/releves-s20/s20-reperes-corrige-744x551-enonce-deux-lignes.png`.

**Une correction a la description de `lea-dev`, sur un point de fait et non sur un chiffre.** Sa
fiche et le commentaire du paquet servi ecrivent qu'a trois propositions *"la regle generale
s'applique (une seule rangee)"*. **Ce n'est pas ce qui est servi** : les trois propositions occupent
**deux rangees sur 16 items sur 16** (deux boutons en haut, le troisieme seul en pleine largeur en
dessous), mesure du 2026-08-27, champ `rangeesAuRepos` du releve, valeur 2 sur les seize.
**Ses pixels sont justes au centieme** - 453,19 / 501,19, 516,19, 595,19 / 643,19, je retrouve
exactement ses chiffres - **c'est la phrase d'explication qui est fausse, pas la mesure.** Je le dis
parce qu'un lecteur qui reprendrait cette phrase sans remesurer chercherait la cause au mauvais
endroit.

### 4. Pourquoi ce residu ne me fait pas maintenir le refus : je corrige d'abord un constat de mon propre refus

J'avais ecrit le 2026-08-26, a propos de l'exigence "verdict et indice entierement au-dessus du pli
16 sur 16" : *"Je n'invente aucune exigence : c'est la barre que j'applique depuis s17."* **J'ai
verifie cette phrase aujourd'hui au lieu de m'appuyer dessus, et elle est fausse pour l'indice.**

Balayage du catalogue servi, le 2026-08-27, sonde ecrite pour l'occasion sur le meme navigateur et
la meme reference que l'outillage `nadia-prof` (`744 x 551`, `--hide-scrollbars`, un navigateur et
une session neufs par jeu, **une partie par jeu, une erreur volontaire par item**), sur les
**17 jeux** de `catalogue.yaml` a `77dd467`, **157 items vus au total** (7 a 10 items par jeu, tirage
aleatoire) - releve `squad/conformite/releves-s20/s20-pli-du-catalogue-744x551.json` :

| Constat, catalogue servi, 2026-08-27, 157 items vus sur 17 jeux | Chiffre |
|---|---|
| Jeux ou l'**indice** passe sous le pli sur au moins un item vu | **8 sur 17** |
| Items vus dont l'indice est sous le pli | **43 sur 157** |
| Jeux ou le **verdict** passe sous le pli sur au moins un item vu | **3 sur 17** (`conjugaison-present`, `perimetre`, `grands-nombres`) |
| Items vus dont le verdict est sous le pli | **5 sur 157** |

Le detail qui tranche, meme date, meme commande :

| Jeu catalogue, tous `couvert-valide` | Indice sous le pli | Pire bas de l'indice |
|---|---|---|
| `bandit-manchot-problemes-additifs-cp` | **10 items vus sur 10** | 553,19 px |
| `bandit-manchot-perimetre-cm1` | 9 sur 10 | **685,19 px** |
| `bandit-manchot-accord-sujet-verbe-cm1` | 9 sur 10 | 583,19 px |
| `bandit-manchot-grands-nombres-cm1` | 6 sur 10 (**et 3 verdicts sur 10**) | **715,19 px** |
| `bandit-manchot-accords-groupe-nominal-cm1` | 3 sur 10 | 619,19 px |
| **`cm1-reperes` corrige, pour comparaison** | 16 sur 16 | **643,19 px** |

**Deux jeux que j'ai laisses au catalogue enfoncent l'indice plus bas que `cm1-reperes` corrige, et
un troisieme le fait sur la totalite des items vus.** Maintenir un `REFUSE` sur `cm1-reperes` pour
cette propriete reviendrait a appliquer a `lea-dev` une barre que **huit jeux sur dix-sept** ne
tiennent pas et que je n'ai jamais opposee a aucun d'eux. C'est la definition meme du poteau qu'on
deplace, et mon propre cadre me l'interdit. **Ce n'est pas ma barre qui baisse : c'est mon constat
sur l'etat du catalogue qui etait faux, et je le corrige avant d'arbitrer.**

**Second fait que j'ai mesure plutot que suppose : la barre que j'avais posee n'est pas atteignable
par ce paquet.** Balayage de la longueur de libelle, le 2026-08-27, en substituant dans le document
servi des libelles de longueur croissante et en relisant la mise en page a chaque pas (releve
`squad/conformite/releves-s20/s20-longueur-de-libelle-atteignable-744x551.json`) :

- **au plus 10 caracteres** par libelle pour que **trois** propositions tiennent sur **une** rangee a
  la largeur offerte de 680 px ; a 11 caracteres, deux rangees. Aucun des cinq libelles servis n'y
  entre : `Point final` fait 11 caracteres, `Phrase correcte` 15, `Majuscule initiale` 18,
  `Majuscule au prenom` 19, `Point d'interrogation` 21 ;
- une rangee au lieu de deux fait remonter l'indice de **90 px** : 595,19 devient **505,19 px**,
  au-dessus du pli - **mais seulement sur un enonce d'une ligne**. Sur un enonce de deux lignes,
  l'indice tombe a **553,19 px**, soit **2,19 px sous le pli**, une rangee ou pas.

Autrement dit ma barre de 16 sur 16 exigeait **simultanement** cinq libelles de dix caracteres
nommant quatre reperes plus le cas correct, **et** le raccourcissement des deux enonces de deux
lignes. **Et cette seconde moitie, ma propre phrase la fermait** : j'avais ecrit *"Ce que je ne
demande pas : ni de reduire le nombre d'items, ni de toucher aux seize phrases"*, ce que `lea-dev` a
lu - sa fiche l'ecrit deux fois - comme une **interdiction** (*"que nadia-prof interdit de toucher"*,
*"non modifiables sur instruction de nadia-prof"*). Sa lecture est defendable et ma formulation est
la cause. **Je la corrige ici, datee : je ne demandais pas de toucher aux seize phrases, je ne
l'interdisais pas.** C'est la troisieme fois sur ce paquet que la cause d'un obstacle est une phrase
de moi ; je prefere l'ecrire que la laisser couter un quatrieme aller-retour.

### 5. Ce que ce verdict ouvre, et ce qu'il n'ouvre pas

**Le Done pedagogique est ouvert.** L'increment est utilisable : au repos, sur les seize items,
l'ecran est entierement lisible, sans defilement, boutons de 74 px sur une ligne (capture
`s20-reperes-corrige-744x551-apres-echec.png`, etat au repos identique hors bloc de retour).

**Consequence sur `catalogue.yaml`, que je nomme sans y toucher.** `DEC-0049` a retire
`bandit-manchot-reperes-cm1` du catalogue au motif de mon `REFUSE`. **Ce motif est leve** : par la
regle generale de `DEC-0045` section 3, plus rien ne s'oppose au retour de l'entree. **Le geste
appartient a `camille-po` et a `sofia-sm`, pas a moi**, et je n'ai pas touche au fichier. Ce que je
leur donne pour arbitrer la priorite plutot qu'un simple feu vert : remis au catalogue en l'etat, ce
jeu y entrerait avec l'indice hors de vue sur **16 items sur 16** apres chaque erreur - une frequence
que seul `bandit-manchot-problemes-additifs-cp` egale aujourd'hui, a un enfoncement moindre.

**Ce que ce verdict n'ouvre pas** : la ligne de referentiel amendee par `DEC-0046` peut passer
`couvert-valide` du point de vue de la competence - les quatre reperes sont desormais travailles et
mesures - mais elle ne couvre toujours **que** la connaissance des reperes sur une phrase donnee,
jamais la relecture par l'enfant de son propre texte. La ligne d'origine reste `non couvrable en
l'etat`.

### 6. Reserves. Nomenclature `REP-n`, dans la continuite du refus du 2026-08-26 - aucune famille de jetons neuve

- **`REP-7` (2026-08-27), NON BLOQUANTE - la forme du jeu de propositions predit la reponse sans
  qu'on lise la phrase.** Chaque item montre trois libelles tires de cinq. Sur les seize items,
  **quand un libelle de majuscule et un libelle de point figurent ensemble, la reponse est toujours
  `Phrase correcte`** (4 items sur 16, exactement les quatre items de `REP-2`), et **quand les deux
  libelles non correct sont de la meme famille, la reponse n'est jamais `Phrase correcte`** (12 items
  sur 16). Mesure du 2026-08-27 par enumeration des seize compositions du paquet servi : signature
  `majuscule + majuscule + correct` sur 6 items, `point + point + correct` sur 6, `majuscule + point
  + correct` sur 4, **sans une seule exception**. Un enfant qui ne lit pas la phrase gagne **4 items
  a coup sur** et joue les douze autres a une chance sur deux au lieu d'une sur trois : esperance
  **10 sur 16**, contre **5,33 sur 16** au hasard pur. C'est la famille de defaut que j'ai refusee
  sous `NAT-1` puis sous `REP-4` - **mais elle ne resout pas le paquet** (elle en resout le quart),
  et l'appariement des deux libelles d'une meme famille est **exactement le mecanisme qui ferme
  `REP-4`** : je ne casse pas ce qui vient d'etre repare. **Correction actionnable sans toucher aux
  seize phrases** : donner a quelques items fautifs une composition mixte (par exemple *"le chat dort
  sur le tapis."* avec `Point final` et `Phrase correcte` comme distracteurs), pour que la signature
  `majuscule + point + correct` ne designe plus systematiquement `Phrase correcte`. `Phrase correcte`
  doit rester sur les seize items - `lea-dev` a raison sur ce point et son motif est le bon.
- **`REP-8` (2026-08-27), NON BLOQUANTE, ET ELLE N'APPARTIENT PAS A CE PAQUET - l'indice est hors de
  vue apres un echec.** 16 items sur 16, de 44,19 px a 92,19 px sous le pli, mesure du 2026-08-27
  ci-dessus. **Ce n'est pas un defaut de `cm1-reperes` : c'est le premier defaut pedagogique du
  catalogue**, 43 items sur 157 vus et 8 jeux sur 17 le meme jour, deux d'entre eux plus bas que
  celui-ci. La cause mesurable n'est pas dans le contenu : elle est dans le budget vertical du
  cabinet et dans la taille de `.proposition` (police 2rem en gras, rembourrage), qui plafonne le
  libelle utile a **10 caracteres** pour trois propositions sur une rangee. **Je route cette reserve
  a `camille-po` comme une story de catalogue**, avec `ines-design` et `theo-archi` sur la cause de
  forme, **pas a `lea-dev` comme une reprise de paquet.** Deux leviers chiffres leur sont donnes
  ci-dessus : une rangee au lieu de deux vaut 90 px, un enonce d'une ligne au lieu de deux en vaut 48.
- **`REP-9` (2026-08-27), NON BLOQUANTE - le verdict est coupe en deux sur deux items.** Les deux
  items a enonce de deux lignes montrent le verdict a 564,19 px, haut a 534,19 px : la moitie basse
  des lettres est sous le pli. **Celle-ci est corrigeable dans ce paquet**, et par le levier que ma
  propre phrase avait ferme (section 4) : ramener ces deux enonces a une ligne rend 48 px et met le
  verdict a 516,19 px sur 16 items sur 16. Deux items, deux phrases, aucun code.
- **`REP-10` (2026-08-27), NON BLOQUANTE - un indice qui en dit trop.** L'item 15, *"Ou habite ton
  cousin Paul ?"*, donne pour correct, porte l'indice *"Cette phrase pose une question et se termine
  bien."* : *"se termine bien"* nomme la reponse d'assez pres pour qu'un enfant apparie sans relire.
  Meme famille que `REP-5`, que la correction du 2026-08-26 vient de fermer sur les items 8 et 9 ;
  celui-ci n'etait pas dans mon refus, je le trouve aujourd'hui, je le nomme aujourd'hui, et il ne
  pese pas sur ce verdict.

### 7. Sur quelles proprietes je reste la seule garde, et ce que cet audit ne couvre pas

- **`REP-1`, `REP-2`, `REP-3`, `REP-7`, `REP-10`** - aucun test de ce depot ne les verifie. `REP-4`
  est devenue mecaniquement verifiable sur `bonneReponse` depuis que les cinq libelles sont atomiques
  (`PaquetCm1ReperesTest`), et c'est un vrai gain ; **les cinq autres restent a ma seule charge**, y
  compris les deux que je viens d'ouvrir. Si l'equipe croit ces proprietes couvertes par la CI, elle
  se trompe : elles ne le sont pas.
- **La simulation "l'enfant qui ignore le point d'interrogation"** est un raisonnement de maitresse
  execute a la main sur les seize items, **pas un garde-fou** : elle donne 13 sur 16 aujourd'hui, et
  rien n'empechera un paquet futur de la ramener a 16 sur 16 avec une CI verte. **Le verdict de la
  section 1 s'appuie dessus : propriete declaree degradee, dans le paragraphe qui s'en sert.**
- **Ce qu'un enfant de neuf ans comprend d'un bouton barre** et **s'il devine qu'il peut faire defiler
  la page pour aller chercher l'indice** : jugements humains, non mesurables par l'outillage du
  depot, et ils fondent en partie la section 5. **Degrades, declares ici.**
- **Limite de mesure, etat exact couvert.** Toutes mes mesures du 2026-08-27 tournent sur un
  navigateur lance avec `--hide-scrollbars`. **L'etat que je n'ai pas mesure est celui-la et pas un
  autre** : le rendu avec une barre de defilement classique peinte, qui prendrait de la largeur au
  document. Ici la question a une consequence reelle et je la nomme : apres un echec le document fait
  **627 px** pour un pli a 551, donc **76 px de defilement possible** - l'indice est *hors de vue*,
  pas *inatteignable*. Si une barre de defilement est peinte, l'enfant a un signal que ma mesure ne
  montre pas ; si elle ne l'est pas, il n'en a aucun. **Je n'ai pas tranche cet etat, je ne l'ai pas
  essaye, et je ne le fais pas couvrir par analogie.**
- **Toute largeur autre que 744 x 551** : non mesuree.
