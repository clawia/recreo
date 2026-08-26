# Fiche de conformite - bandit-manchot-reperes-cm1

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du catalogue).
- **Contenu(s)** : `adapters/adapter-content/src/main/resources/contenu/cm1-reperes.yaml`,
  livre, `type: question`, 16 items, story `S20-2` (`docs/backlog/backlog.md`). Aucune matiere
  premiere identifiee dans `contenu-repris/` sur ce repere precis : le paquet est integralement
  neuf.
- **Statut global** : **REFUSE par `nadia-prof` le 2026-08-26** (`hs-nadia-arbitrages`, commit
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
- **Derniere mise a jour** : 2026-08-26 (`lea-dev`, correction du refus REP-4/pli du meme jour).

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
