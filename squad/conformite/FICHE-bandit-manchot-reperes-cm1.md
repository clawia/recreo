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
  libelle (decision de forme qui appartient a `ines-design`, pas a ce paquet). A trois
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
  *Correction du 2026-08-27 (`nadia-prof`, s21) : cette entree citait `ADR-0029` comme la decision
  qui gouverne la police et le rembourrage de `.proposition` sous le repli a quatre colonnes.
  `ADR-0029` gouverne le decalage d'animation par colonne (`animation-delay`) et ne contient ni
  `font-size` ni `padding` - verifie a la source le 2026-08-27, le fichier ne porte aucune
  occurrence de l'un ni de l'autre. La reference est retiree ; la source reelle du plancher de
  1,875rem est mon propre arbitrage prealable du 2026-08-11
  (`FICHE-bandit-manchot-conjugaison-present-cm1.md`, section C, sept conditions), que citent deja
  `recreo.css` et `TailleDesTextesEtBoutonsTest`. Ecart signale par `ines-design`
  (`proposition-ines-rep-8.md`, section 2.3), verifie et corrige par moi : la fiche est a moi, la
  reference fausse aussi. Le chiffre de 492 px et le raisonnement ne changent pas.*
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
- **Derniere mise a jour** : 2026-08-27 (`nadia-prof`, s21 : arbitrage sur le gel de la police de
  `.proposition`, en fin de fiche - **le gel tient**, `REP-8` ne peut pas etre entierement fermee a
  s21, et ma limite `--hide-scrollbars` est levee au seul viewport 744 x 551. Le verdict du paquet
  n'est pas touche : `cm1-reperes` reste `VALIDE SOUS RESERVE` au titre de la reaudition du
  2026-08-27).

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

> **Note du 2026-08-27, ajoutee juste apres le depot de ce lot.** `round-two` a avance pendant mon audit : `9ccbb1d` (`yanis-qa`) puis `6228d77` (`theo-archi`) se sont poses au-dessus de `77dd467`. **Ils ne changent rien a ce que j'ai mesure**, et je le verifie plutot que je le suppose : `git diff --name-only 77dd467..6228d77 -- adapters domain application bootstrap`, le 2026-08-27, ne rend **aucun fichier** - les deux commits ne portent que des documents de `squad/`. L'artefact servi a la tete de branche est donc identique a celui que j'ai joue.

---

## Arbitrage du 2026-08-27 (s21, ouverture) - le gel de la police de `.proposition` (`S3-4`)

**Ce document n'est pas un Done pedagogique.** Aucun increment n'est audite ici, aucun verdict de
paquet n'est modifie : `cm1-reperes` reste `VALIDE SOUS RESERVE` au titre du 2026-08-27, et rien
de ce qui suit ne rouvre ce verdict. Ce que je rends ici, c'est l'arbitrage sur **ma propre
reserve**, saisie parce qu'elle se trouve en travers de l'arbitrage de l'utilisatrice (`DEC-0051`,
"fermer le pli d'abord, puis le volume") et de la proposition d'`ines-design`
(`proposition-ines-rep-8.md`). Trois issues m'etaient offertes ; **je prends la premiere, et je la
motive par de la mesure faite aujourd'hui, pas par ma signature.**

### 1. `S3-4` relue a sa source, avec sa date et son motif d'origine

Je suis remontee au texte, pas au souvenir.

- **Origine** : ma **reserve 1 sur `S2-1`**, verdict du **2026-08-05**
  (`FICHE-bandit-manchot-additions-cp.md`, point 2 bis). Motif ecrit ce jour-la, cite au mot :
  le bouton de reponse etait une barre pleine ligne de **836 px** portant un glyphe de **20 px**,
  *"soit l'information utile logee dans 2 % de l'objet qui la porte. Or c'est le nombre qui
  distingue les trois propositions, la barre est identique pour les trois. Ce qui doit etre le
  plus visible, c'est ce qui porte la difference."* Ce n'etait pas une reserve de lisibilite -
  j'ecrivais dans le meme paragraphe que le chiffre etait *"parfaitement lisible"* a 1,25 rem.
  **C'est une reserve de hierarchie : la reponse ne doit pas etre visuellement subordonnee a la
  question.**
- **Ce qu'elle est devenue** : la story `S3-4` (`docs/backlog/backlog.md`), qui l'operationnalise
  en **deux clauses** : `.proposition` >= 2 rem, **et** `.proposition` >= `.enonce`. Les deux sont
  encodees dans `TailleDesTextesEtBoutonsTest` : `leChiffreDeReponseEstAuMoinsAussiGrosQueLeCalcul`
  porte les deux, et `laPoliceDuRepliAQuatreColonnesEstUneExceptionAuPlancherGeneralDeDeuxRem`
  epingle `.proposition` a **exactement** 2 rem. Le gel n'est donc pas une phrase de fiche : il est
  execute par la CI, et le baisser exige que je l'ecrive d'abord.
- **La seule fois ou je l'ai entamee** : le **2026-08-11**, sur `S8-5`
  (`FICHE-bandit-manchot-conjugaison-present-cm1.md`, section C). J'ai accepte **1,875 rem
  (30 px)** pour le seul repli a quatre colonnes, en ecrivant que **les deux clauses cedaient et
  pas une**, et en posant sept conditions. La condition 2 dit, au mot : *"30 px est un plancher,
  pas le debut d'une pente. J'accepte 1,875 rem et rien en dessous. J'ai mesure 1,8125 rem (29 px) :
  il donne plus d'air, il n'est pas approuve. Toute valeur sous 30 px revient devant moi."*

**30 px est donc la plus petite police de proposition que j'aie jamais accordee, dans le cas que
j'avais moi-meme qualifie d'exceptionnel, et 29 px est une valeur que j'ai mesuree et refusee.**
C'est de la que part tout ce qui suit.

### 2. J'ai mesure avant de trancher, parce que relire ma phrase ne suffit pas

Ce que la police achete reellement, en pixels, mesure le **2026-08-27** sur le jar deja construit
(`bootstrap/target/recreo.jar`, non reconstruit, `sha256` verifie par `ines-design` contre la tete
de branche le meme jour), application servie par `java -jar bootstrap/target/recreo.jar
--server.port=<port>` sur un JDK 21 (variable `JAVA_HOME` ou toolchain declaree), viewport de
reference **744 x 551** (`DEC-0040`). Commande exacte, une fois par valeur de police :

```
CHROME=<navigateur> PAQUET=adapters/adapter-content/src/main/resources/contenu/cp-problemes-additifs.yaml \
REGLE=".proposition{font-size:<valeur>}" \
python squad/outils/nadia-prof/lire-le-pli-a-l-ecran.py http://<hote>:<port> \
    bandit-manchot-problemes-additifs-cp <sortie.png>
```

L'outil est neuf et commite avec cet arbitrage ; la regle candidate est **injectee dans le document
rendu**, jamais ecrite dans `recreo.css` - aucun fichier de production n'est touche par cet
arbitrage. Releve commite :
`squad/conformite/releves-s21/s21-nadia-police-de-proposition-744x551.json`.

| Police de `.proposition`, mesuree le 2026-08-27 | Police rendue | Hauteur d'un bouton | Gain par rangee contre 2 rem |
|---|---|---|---|
| 2 rem (aujourd'hui) | 32 px | 74,0 px | - |
| 1,875 rem (le plancher que j'ai deja accorde le 2026-08-11) | 30 px | 71,0 px | **3 px** |
| 1,75 rem | 28 px | 68,0 px | **6 px** |
| 1,5 rem | 24 px | 62,0 px | **12 px** |
| 1,25 rem | 20 px | 56,0 px | **18 px** |
| 1 rem | 16 px | 50,0 px | **24 px** |

La loi est lineaire et je l'ecris pour qu'on cesse de negocier a l'aveugle : **la hauteur d'un
bouton vaut 24 x police(rem) + 26 px**, soit **1,5 px de hauteur par pixel de police**, et une
rangee coute cette hauteur plus le `gap` de 1 rem. Elle est verifiee, pas supposee : projetee sur
`bandit-manchot-accord-sujet-verbe-cm1` avec les deux leviers d'`ines-design`, elle annonce
567,19 px, et la mesure directe du 2026-08-27 rend **567,19 px sur trois tirages independants**.

**Ce que chaque valeur ferme, projete sur la population du releve d'`ines-design` du 2026-08-27**
(17 jeux, 167 items vus, 157 portant un indice lu ; methode et limite dans le releve commite - la
projection ignore qu'une police plus petite peut aussi reduire le *nombre* de rangees, elle est
donc **pessimiste**, ce qui ne dessert pas ma conclusion) :

| Etat, mesure ou projete le 2026-08-27 | Items dont l'indice reste sous le pli |
|---|---|
| aujourd'hui, aucun levier | **56 sur 157** |
| les deux leviers de forme d'`ines-design`, police inchangee a 2 rem | **30** |
| les memes + police a **1,875 rem** | **30** |
| les memes + police a **1,75 rem** | **30** |
| les memes + police a **1,5 rem** | **21** |
| les memes + police a **1,25 rem** | **7** |
| les memes + police a **1 rem** | **4** |

### 3. Le verdict : **le gel tient**, et voici les trois faits qui le motivent

**Premier fait : la seule valeur que je pourrais accorder sans me contredire n'achete rien.**
1,875 rem est le plancher que j'ai deja ecrit et date le 2026-08-11. Applique a tout le catalogue,
il fait passer le residu de **30 items a 30 items** : zero. 1,75 rem (28 px), qui est deja sous les
29 px que j'ai mesures et refuses ce jour-la, n'achete **rien non plus**. Il n'y a pas de marge a
ceder ici : il y a un cout sans contrepartie.

**Deuxieme fait : aucune valeur de police ne ferme `REP-8`, y compris celles que personne ne
defendrait.** A 1 rem - 16 px, la taille d'un texte courant, pour le mot que l'enfant doit choisir -
il reste **4 items** sous le pli. Le pire item du catalogue,
`bandit-manchot-grands-nombres-cm1` a trois rangees, est a 715,19 px pour un pli a 551 : **164,19 px
de deficit**, quand trois rangees a 1 rem n'en rendent que 72. **La police n'est pas le verrou de
`REP-8` ; elle n'en a jamais ete la cle.** L'issue 2 qui m'etait proposee - amender le gel pour
fermer `REP-8` - n'existe pas : le gel n'est pas ce qui empeche d'y arriver.

**Troisieme fait, celui que j'ai vu en jouant et qu'aucun chiffre ne dit.** Sur les jeux de calcul,
`.enonce` porte **les memes chiffres** que les propositions. J'ai joue "Petits problemes" a
1,5 rem : *"Nino a 7 crayons et en casse 2"* affiche le 7 et le 2 a **32 px** dans la question, et
les reponses a choisir a **24 px**. La question pese plus lourd que la reponse, sur les memes
glyphes, a l'ecran, cote a cote. **C'est exactement l'inversion pour laquelle ma reserve 1 existe
depuis le 2026-08-05**, et je la refuse pour son motif d'origine, pas par attachement a un chiffre.

**Consequence, et je l'ecris pour `sofia-sm` afin qu'elle n'ecrive pas un objectif qui echouera :
`REP-8` ne peut pas etre entierement fermee a s21.** Ni par la police, ni par les deux leviers de
forme, ni par les deux ensemble. Un objectif de sprint formule "fermer `REP-8` sur tout le
catalogue" est un objectif que la mesure declare inatteignable **avant** le sprint. Ce qui est
atteignable est en section 4.

### 4. Ce que valent les 30 items residuels du point de vue de l'enfant

C'est la question que `sofia-sm` doit pouvoir porter a l'utilisatrice, et elle ne se repond pas
avec un compte d'items : un item "sous le pli" peut n'avoir rien perdu de lisible. La distinction
n'existait dans aucun outil du depot, parce qu'`auditer-paquet.py` rend le bas de la **boite**
d'indice, et que la boite descend **12 px** plus bas que le texte qu'elle porte (rembourrage et
bordure) - ecart mesure a 12,00 px, constant, sur cinq jeux le 2026-08-27. Mon outil neuf rend le
bas de la **derniere ligne de texte**, et c'est lui qui tranche.

| Ce que l'enfant perd apres un echec, apres les deux leviers de forme (2026-08-27) | Items | Jeux |
|---|---|---|
| **rien de lisible** : la derniere ligne de l'indice se lit entierement, seule la bordure basse de la boite est coupee (deficit 16,19 px, encre coupee **0 px**) | **14** | `accord-sujet-verbe-cm1` (10), `synonymes-cm1` (2), `grands-nombres-cm1` (2) |
| **le bas des lettres de la derniere ligne**, sur 3,19 px d'une bande d'encre d'environ 20 px : lisible, visiblement rogne | **9** | `reperes-cm1` (8), `conversions-unites-cm1` (1) |
| **une ligne entiere d'indice** | **3** | `accords-groupe-nominal-cm1` |
| **tout l'indice** (il tient sur une ligne) | **2** | `reperes-cm1`, les deux items aux enonces les plus longs |
| **tout l'indice et une partie du verdict** : le bloc de verdict lui-meme reste projete a 574,19 px, soit 23,19 px sous le pli - l'enfant ne voit ni *"Ce n'est pas ca"* en entier, ni le moindre mot d'aide | **2** | `grands-nombres-cm1`, les deux items a trois rangees |

J'ai regarde les deux extremes a l'ecran plutot que de les deduire. A 16,19 px de deficit
(`accord-sujet-verbe-cm1`), la phrase *"...se termine par « -e »."* se lit jusqu'au point final ;
ce qui manque est un trait de bordure ambree. A 68,19 px (`reperes-cm1`), l'ecran s'arrete au
milieu de la croix rouge : l'enfant voit qu'il s'est trompe et **rien d'autre**.

**Ce que j'en conclus pour l'objectif de sprint.** 26 items sur 56 fermes par les deux leviers de
forme, plus 14 items dont le defaut residuel ne coute a l'enfant aucun mot, cela fait **40 items
sur 56 ou l'enfant lit son indice en entier sans defiler**. Les 16 qui restent se rangent en trois
familles nettes, et **aucune ne se traite par la forme** : neuf items ou l'encre est rognee de
3,19 px, trois ou une ligne d'indice manque, quatre ou l'aide est entierement perdue. Le levier qui
les ferme est celui qu'`ines-design` cite en section 2.1 sans le revendiquer, et c'est le mien du
2026-08-27 : **un enonce sur une ligne au lieu de deux rend 48 px**, ce qui couvre largement les
deficits de 16,19 px et de 20,19 px. C'est un travail de **contenu**, item par item, pas un
reglage de feuille de style.

### 5. Les deux leviers de forme d'`ines-design` : mon avis, et une correction qui la concerne

- **Levier A, la marge de `.score` a 0,25 rem** : **accepte.** Aucun plancher pedagogique de moi ne
  porte sur cette marge - verifie dans mes fiches, pas seulement dans le garde-fou. Le score se
  rapproche du bandeau de 4 px ; regarde a l'ecran le 2026-08-27, il reste detache et lisible.
- **Levier B, le rembourrage vertical de `.proposition` a 0,5 rem** : **accepte, et je dois
  corriger `ines-design` sur un point qui compte.** Elle ecrit qu'*"aucun plancher pedagogique
  connu n'est touche (le rembourrage n'en porte pas)"*. **Il en porte un, et il est de moi** :
  c'est mon **acquis 7** du **2026-08-11** (`FICHE-bandit-manchot-additions-cp.md`, section 7),
  ecrit ainsi - *"rembourrage declare de `.proposition` (0,75 rem / 1,25 rem) : inchange. C'est
  l'air autour du mot et le socle de mon acquis 5. **Payeur refuse**. Qui le garde : **moi
  seule**"* -, et repris en condition 5 de mon arbitrage sur `S8-5` le meme jour. Elle ne l'a pas
  trouve parce qu'elle a lu `TailleDesTextesEtBoutonsTest`, qui ne l'encode pas. **C'est
  exactement la situation ou je suis le seul garde-fou, et elle vient de se produire.**

  **Ce que je decide, en amendement date et non en reecriture de l'acquis 7.** L'acquis 7 nait le
  2026-08-11 d'un budget **horizontal** : la section 3 du meme arbitrage refuse nommement *"le
  rembourrage **lateral** de `.proposition` (1,25 rem)"* comme payeur, parce qu'a 360 px l'axe
  horizontal etait epuise au pixel. Sa clause verticale n'a jamais ete mesuree par personne. Je la
  mesure aujourd'hui et je la tranche : **l'axe lateral reste a 1,25 rem, payeur refuse, inchange ;
  l'axe vertical descend a 0,5 rem et pas plus bas.** Motif chiffre : le bouton passe de 74,0 px a
  66,0 px (mesure du 2026-08-27), soit 22 px au-dessus du plancher de cible tactile de 2,75 rem, et
  l'air declare au-dessus et au-dessous du mot passe de 12 px a 8 px, auxquels s'ajoutent les 8 px
  de demi-interligne que la ligne de 48 px laisse deja de chaque cote du glyphe. Regarde a
  l'ecran : le mot n'approche aucune paroi. **0,5 rem est un plancher, pas une pente** - la meme
  formule qu'au 2026-08-11, et pour la meme raison. Toute valeur sous 0,5 rem revient devant moi.

  **Je n'efface pas l'acquis 7 et je ne pretends pas qu'il ne disait pas ce qu'il disait.** Il
  disait 0,75 rem et il le disait pour les deux axes. Je l'amende, date, sur son seul axe vertical,
  parce que la mesure qui le motivait ne portait que sur l'autre.

- **Un defaut de l'outil de mesure, que je signale sans qu'il entame ses chiffres.**
  `squad/outils/ines-design/mesurer-candidats-rep-8.py` injecte la regle candidate **une fois**,
  avant la premiere partie, et quitte chaque partie en cliquant `[data-role=sortie]` - qui est un
  `<a href="/">` ordinaire (`templates/accueil.html`, verifie par lecture le 2026-08-27), donc une
  navigation complete qui recree le `<head>` et **jette la regle injectee**. Une execution a deux
  parties melange donc, dans un meme fichier, des items mesures avec la regle et des items mesures
  sans, **sans le dire**. Constate le 2026-08-27 sur une de mes propres executions
  (`bandit-manchot-accord-sujet-verbe-cm1`, une seule execution, 16 items, `parties: 2`) : trois
  bas de verdict coexistent, dont 474,19 px qui est exactement la valeur **sans** regle.
  **Les trois releves candidats commites par `ines-design` portent tous `parties: 1` et ne sont
  donc pas touches** - je l'ai verifie avant de l'ecrire, et j'ai recalcule ses deux gains depuis
  ses fichiers bruts plutot que de les reprendre : marge de `.score` **-8 px a plat**, rembourrage
  vertical **-8 px par rangee**, additifs. Ses chiffres tiennent. C'est la prochaine execution qui
  mentira, et c'est pour cela que je l'ecris ici. Route a `ines-design` et `theo-archi`.

### 6. Le contrat de forme pour les paquets neufs : ce que j'autorise, et ce que je refuse

`ines-design` me demande si son contrat est pedagogiquement tenable. **A s20, ma phrase *"je ne
demande pas de toucher aux seize phrases"* a ete lue comme une interdiction et a ferme le levier
qui reglait `REP-9`.** Je n'ecris donc pas ce contrat en interdits : j'ecris ce qu'il autorise, et
je chiffre ce que chaque regle couterait.

Les deux budgets, mesures le 2026-08-27 avec les polices reellement rendues (et non par comptage de
caracteres, parce qu'un "1" et un "m" ne prennent pas la meme place) :
`squad/outils/nadia-prof/budget-de-forme.py`, releve commite
`squad/conformite/releves-s21/s21-nadia-budget-de-forme-744x551.json`. Commande exacte :

```
CHROME=<navigateur> python squad/outils/nadia-prof/budget-de-forme.py http://<hote>:<port> \
    bandit-manchot-problemes-additifs-cp \
    adapters/adapter-content/src/main/resources/contenu <sortie.json>
```

- un enonce a **696 px** de large, en 32 px gras : **environ 47 caracteres** sur une ligne ;
- un libelle de proposition a **176 px** de texte utile a trois colonnes, en 32 px gras : **10
  caracteres minuscules**. Ce chiffre de 10 est le mien, ecrit a s20 ; je viens de le **retrouver
  par une autre route** (mesure de la police rendue, contre la dichotomie sur la rangee) et je le
  confirme au lieu de le recopier.

Passes sur les **428 items** des 17 paquets servis, le 2026-08-27, ces deux budgets disent ceci :

| Regle proposee | Items du catalogue actuel qu'elle ecarterait | Ce qu'elle interdit d'enseigner |
|---|---|---|
| **1. Au plus trois propositions** | **33 items sur 428** : `cm1-conjugaison-present` 25 sur 25, `cm1-accords-groupe-nominal` 8 sur 24 | **Rien.** Une bonne reponse et deux distracteurs plausibles suffisent a toutes les competences des deux referentiels. Un item a quatre choix devient deux items a trois. Cout reel, que je nomme : sur la conjugaison, la confrontation des quatre personnes en un seul ecran disparait. Je l'accepte. |
| **2. Libelle de 10 caracteres au plus** | **35 items sur 428**, dont `cm1-reperes` **16 sur 16** et `cm1-grands-nombres` 13 sur 28 | **Deux competences entieres, et je la refuse pour cela.** "Lire, ecrire, decomposer et comparer les nombres entiers jusqu'au million" (`squad/programme/mathematiques/competences-cm1.md`) a pour objet meme le nombre ecrit en lettres : `quatre-vingt-seize mille deux cent trois` mesure **590,05 px**, soit 3,35 fois la colonne. "Reperer et corriger la majuscule et la ponctuation" nomme le signe : `Point d'interrogation` mesure **317,69 px**, 1,8 fois la colonne, et c'est le mot du referentiel. Un contrat de 10 caracteres ne raccourcit pas ces libelles : il supprime les competences. |
| **3. Enonce sur une seule ligne** | **80 items sur 428**, dont `cp-problemes-additifs` **22 sur 22**, `cm1-accord-sujet-verbe` 20 sur 21, `cm1-perimetre` 19 sur 22 | **La resolution de problemes en CP, en entier.** *"Resoudre des problemes simples d'addition/soustraction **en situation**"* (`squad/programme/mathematiques/competences-cp.md`) : une situation ne tient pas en 47 caracteres. Meme chose pour l'accord sujet-verbe, dont le sujet doit pouvoir etre eloigne du verbe pour que la competence existe. |
| **4. Porte de rendu : `itemsIndiceSousLePli` et `itemsVerdictSousLePli` vides a 744 x 551 avant fusion** | aucun item ecarte par avance | **Rien du tout.** C'est la seule des quatre qui parle du **resultat** au lieu de prescrire le moyen. |

**Ce que j'autorise, et c'est ainsi qu'il faut l'ecrire dans les criteres d'acceptation :**

1. **La regle 4 est la porte, et elle seule.** Un paquet neuf entre au catalogue si, joue a
   744 x 551, aucun item ne laisse l'indice ni le verdict sous le pli. Comment l'auteur y arrive
   ne regarde ni `ines-design` ni moi.
2. **La regle 1 est autorisee comme regle** : deux ou trois propositions par item, jamais quatre.
   Elle a un cout que je nomme ci-dessus et je l'assume.
3. **Les regles 2 et 3 sont autorisees comme conseils de premier recours, jamais comme veto.**
   Quand la competence laisse le choix, un libelle court et un enonce d'une ligne sont le bon
   reflexe et rendent 90 px et 48 px. Quand la competence impose un libelle long ou une situation,
   **c'est la competence qui gagne**, et l'auteur ferme la regle 4 autrement : moins de
   propositions, un indice plus court, un enonce reformule sans etre ampute.
4. **Ce qu'aucun contrat ne doit produire** : un paquet qui passe la porte de forme en n'enseignant
   plus rien. Une phrase de CP ramenee a 47 caracteres cesse d'etre un probleme ; un nombre ecrit
   en dix caracteres cesse d'etre un nombre ecrit en lettres. **Cette verification-la n'est
   mecanisable par aucun script et reste a moi.**

### 7. Ma limite `--hide-scrollbars` : **je la leve**, au seul viewport 744 x 551

Je l'avais laissee ouverte deux fois. Je l'ai mesuree le 2026-08-27, avec un navigateur lance
**sans** `--hide-scrollbars`, donc avec une barre de defilement classique peinte. Commande exacte :

```
CHROME=<navigateur> PAQUET=adapters/adapter-content/src/main/resources/contenu/cm1-reperes.yaml \
DEFILER=1 python squad/outils/nadia-prof/lire-le-pli-a-l-ecran.py http://<hote>:<port> \
    bandit-manchot-reperes-cm1 <sortie.png> --barre
```

Releve commite : `squad/conformite/releves-s21/s21-nadia-barre-de-defilement-744x551.json`.

| Configuration, `bandit-manchot-reperes-cm1`, 2026-08-27 | Mise en page | Rangee | Rangees | Bouton | Bas de la boite d'indice | Document |
|---|---|---|---|---|---|---|
| barre masquee, 744 | 744 px | 680 px | 2 | 74,0 px | 595,19 px | 627 px |
| **barre peinte**, fenetre 744 | **729 px** | 680 px | 2 | 74,0 px | **595,19 px** | 627 px |
| barre masquee, mise en page forcee a 729 | 729 px | 680 px | 2 | 74,0 px | 595,19 px | 627 px |

**Conclusion, et sa raison structurelle.** Une barre peinte prend **15 px** a la mise en page
(744 vers 729). A ce viewport, cela ne change **rien** a la geometrie : `.container` est deja
plafonne sous 729 px, la rangee de propositions reste a **680 px** dans les trois cas, le nombre de
rangees, la hauteur des boutons et le bas de l'indice sont **identiques au centieme**. **Toutes mes
mesures au pli faites avec `--hide-scrollbars` restent donc valides telles quelles**, et je n'ai
plus a les declarer degradees sur ce point.

**Ce que la barre masquee cachait, en revanche, et que je peux enfin juger** : elle est le **seul
signal a l'ecran** qui dise a l'enfant qu'il y a quelque chose dessous. Je l'ai regardee. Apres un
echec sur "Petits problemes", le document fait 585 px pour un pli a 551 : le curseur remplit
**94 %** de sa glissiere, et il ne reste qu'un lisere de deux ou trois pixels en bas. **Un enfant
de CP ne lira pas ce signal-la**, et je ne crois pas non plus qu'un CM1 le lise sur `cm1-reperes`,
ou le curseur en remplit 88 %. Le contenu est **atteignable** - defilement de 76 px, verifie, et
l'indice remonte alors a 519,19 px - mais rien a l'ecran n'invite a aller le chercher. **Je
maintiens donc `REP-8` comme un defaut reel : l'indice hors de vue est un indice perdu, meme quand
la page defile.**

**Etat exact que cette levee couvre, et rien d'autre** : navigateur sans `--hide-scrollbars`,
viewport 744 x 551. **Restent non mesures et je ne les fais pas couvrir par analogie** : toute
autre largeur, en particulier 360 px, ou la mise en page passe sous le plafond de `.container` et
ou la conclusion de geometrie ci-dessus **ne vaut plus** ; et un appareil tactile a barre
superposee, jamais essaye.

### 8. Sur quelles proprietes je reste la seule garde, apres cet arbitrage

Pour que personne ne croie couvert ce qui ne l'est pas.

- **La hierarchie de saillance entre `.enonce` et `.proposition`** est gardee dans sa lettre par
  `leChiffreDeReponseEstAuMoinsAussiGrosQueLeCalcul` (deux clauses, 2 rem et >= `.enonce`). **Sa
  raison ne l'est pas** : le garde-fou ne verrait pas qu'on abaisse `.enonce`, ce qui abaisserait
  mecaniquement la barre que `.proposition` doit franchir. Si `.enonce` bouge un jour, **le
  plancher absolu de 2 rem sur `.proposition` reste**, et cette phrase-ci est la seule chose qui
  l'ecrit.
- **Le rembourrage de `.proposition`, sur ses deux axes** : aucun test ne porte de plancher
  numerique dessus. Lateral 1,25 rem, vertical 0,5 rem depuis aujourd'hui, **moi seule**.
- **La marge de `.score`** : aucun plancher, aucun garde-fou, **moi seule**.
- **Ce qu'un contrat de forme fait perdre a une competence** : aucun script ne le voit. Un paquet
  peut passer la porte de rendu en n'enseignant plus rien. **Moi seule.**
- **Ce qu'un enfant comprend d'une barre de defilement** : jugement humain, pose en section 7,
  **moi seule**, et desormais appuye sur une mesure au lieu d'un blanc.

### 9. Ce que je route

- **`sofia-sm`** : `REP-8` **ne se ferme pas entierement a s21**, quel que soit le sprint qu'on
  ecrive - la mesure le dit avant le sprint et non apres. Objectif atteignable : les deux leviers
  de forme (26 items sur 56), plus le travail d'enonce sur les items a 16,19 px et a 20,19 px de
  deficit. La conclusion de cet arbitrage est a reprendre dans le `review.md` de s21 quand le
  dossier de sprint existera ; je ne cree pas ce dossier, il est a elle.
- **`camille-po`** : la section 6 est ecrite pour ses criteres d'acceptation. La regle 4 est la
  porte ; la regle 1 est une regle ; les regles 2 et 3 sont des conseils et **ne doivent pas etre
  ecrites en criteres bloquants**, sous peine de fermer deux competences des referentiels.
- **`ines-design`** : ses deux leviers sont acceptes ; l'acquis 7 qu'elle n'avait pas vu est en
  section 5, avec son amendement date ; la police reste gelee et la section 2 dit desormais
  **combien** elle vaut, pour qu'on cesse de la citer comme une reserve sans chiffre.
- **`theo-archi`** : le defaut de l'outil de mesure (section 5, dernier point) et la reference
  `ADR-0029` corrigee en tete de fiche.

---

## Complement du 2026-08-27 (s21), meme jour, apres l'arbitrage du matin : deux points

Ce complement ne modifie ni ne retire une ligne de l'arbitrage du matin. Il l'amende sur un point,
date, et ajoute une justification que le code reclame.

### A. Le levier de position est REFUSE - `REP-8` reste ouverte, et je dis sur quoi

`theo-archi` a mesure, apres mon arbitrage du matin, un levier que celui-ci ne couvrait pas :
epingler `.message-jeu` au bas de la fenetre (`avis-faisabilite-s21.md`, section 1.3). Il ferme le
pli, y compris sur les items a trois rangees. **Je l'ai joue et je l'ai refuse** ; le verdict
complet, ses onze parties, ses captures et ses quatre conditions de reouverture sont dans
`FICHE-bandit-manchot-additions-cp.md`, arbitrage du 2026-08-27, parce que c'est cette fiche-la qui
porte `M1` a `M13`. Motif en une phrase : le bloc epingle rend inerte le bouton que le jeu ordonne
de presser - 6 revelations sur 8 mesurees le 2026-08-27, appui reel sans effet 8 fois sur 8.

**Consequence pour ce paquet et pour l'objectif de s21** : `REP-8` **reste ouverte** sur les
16 items que la section 4 du matin classe comme perdant reellement de l'information. Deux d'entre
eux sont ici : les deux items aux enonces les plus longs de `cm1-reperes`, dont l'indice est
entierement perdu.

**Amendement date a ma section 4 de ce matin, ecrit contre moi.** J'y concluais que le levier qui
ferme ces 16 items est un levier de contenu - *"un enonce sur une ligne au lieu de deux rend
48 px"*. **C'est vrai pour 12 d'entre eux et faux pour 4.** Les deux items a trois rangees de
`grands-nombres-cm1` manquent 162 px sur ma mesure du 2026-08-27 (bloc de verdict a 713,19 px, pli a
551) et jusqu'a 192 px sur celle de `theo-archi` du meme jour : 48 px n'y suffisent pas. Pour les
deux items de `cm1-reperes`, j'ai moi-meme ecrit le 2026-08-26 que leurs enonces ne sont pas
raccourcissables - *"la matiere est saine"*. **Ces quatre items n'ont aujourd'hui aucun levier
connu**, ni de forme, ni de contenu, ni de position. Je ne l'habille pas : je le nomme, et il est a
`camille-po` d'en faire ce qu'elle veut.

### B. La justification pedagogique reelle des trois propositions de ce paquet

`theo-archi` a trouve dans un test commite la meme reference fautive que celle que j'ai corrigee en
tete de cette fiche ce matin : `adapters/adapter-content/src/test/java/com/recreo/adapter/content/PaquetCm1ReperesTest.java`,
Javadoc des lignes 73 et 162, invoque `ADR-0029` pour justifier *"trois propositions, jamais
quatre"*. `ADR-0029` gouverne le decalage d'animation par colonne et **autorise** quatre
propositions ; il n'en interdit aucune. **Je ne touche pas a ce test, il appartient a `lea-dev`.**
Voici ce qu'il faut ecrire a la place, et d'ou cela vient.

La regle "exactement trois propositions sur ce paquet" n'est pas une decision d'architecture. Elle a
**trois sources, toutes pedagogiques, toutes datees, toutes de moi ou mesurees a ma demande** :

1. **Jamais quatre - motif de lisibilite, mesure.** A quatre propositions, la rangee tombe dans le
   repli a quatre colonnes (police 1,875 rem, deux rangees) et le bas du cabinet descend a 492 px
   pour un pli a 551 : le verdict **et** l'indice passent entierement sous le pli sur 16 items sur
   16. C'est le premier motif de mon refus du 2026-08-26. A trois propositions, mesure refaite le
   meme jour, le verdict revient au-dessus du pli sur 14 items sur 16. Un enfant qui ne voit pas le
   verdict ne sait pas que sa reponse a ete prise en compte (`M4`).
2. **Jamais quatre - motif de charge cognitive, anterieur.** Ma specification `B.3`
   (`ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-s13.md`) ecarte nommement la forme a
   quatre versions completes de la meme phrase pour charge cognitive trop haute. Le pool de ce
   paquet compte **cinq** libelles depuis la correction du 2026-08-26 ; un item qui en montrerait
   quatre montrerait quatre cinquiemes du pool, et l'enfant qui reconnait deux libelles trouve le
   bon par elimination sans lire la phrase. C'est la famille de defaut que j'ai refusee sur `NAT-1`
   et que `REP-4` sanctionne ici : une regle mecanique qui resout l'item sans la competence.
3. **Jamais deux - motif de fiabilite de la reussite, et c'est un ajout date du 2026-08-27, pas une
   reecriture.** La regle generale que j'ai autorisee ce matin dit *"deux ou trois propositions par
   item, jamais quatre"*. Sur **ce** paquet, deux ne convient pas : l'item oppose le repere en jeu a
   son voisin immediat - *"Point final"* contre *"Point d'interrogation"* -, et a deux propositions
   l'enfant qui ignore la distinction a une chance sur deux d'avoir juste. C'est exactement ce que
   `REP-4` mesure et refuse : le 2026-08-26, j'ai simule un enfant ignorant totalement le point
   d'interrogation et il obtenait **16 sur 16**. Trois propositions sont le plus petit nombre qui
   conserve la discrimination et ramene le hasard a une chance sur trois.

**En une phrase pour la Javadoc, si une seule est voulue** : trois propositions par item, jamais
quatre parce qu'a quatre le verdict et l'indice passent sous le pli (verdict de `nadia-prof` du
2026-08-26) et parce que montrer quatre libelles d'un pool de cinq rend l'item soluble par
elimination (`REP-4`), jamais deux parce que la discrimination visee tomberait a une chance sur deux
(`nadia-prof`, 2026-08-27). Aucun `ADR` ne porte cette regle.
