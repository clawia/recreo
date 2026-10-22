# Fiche de conformite - bandit-manchot-regle-sanction-ce1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-emc-regle-sanction.yaml` (24 items, type `question`,
  aucun texte support), paquet ecrit dans le lot.
- **Entree de catalogue** : `bandit-manchot-regle-sanction-ce1`, titre servi *"La règle et la sanction"*, consigne servie *"Lis
  bien, puis choisis la bonne réponse."*, `nombreDeManches: 10`.
- **Ligne declaree** : `EMC-CE1-02`. **Story** : `S57-BR-EMC-CE1-02`.
- **Competence programme visee** : `squad/programme/enseignement-moral-et-civique/competences-ce1.md`, `EMC-CE1-02`,
  *"Distinguer une règle d'une sanction, et dire à quoi sert une sanction (rappeler la règle, réparer le tort fait)."* Plafond
  `couvert-valide`.
- **Conditions opposables** : `RSA-CE1-1`, `RSA-CE1-2`, `NRC-CE1-1` et les transverses de la section 2
  (`ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md`, 2 et 4.2) ; `EMC-1` a `EMC-7` ;
  `arbitrage-nadia-preparation-palier-3-ce1.md` 4.1 et 4.4 ; points d'audit de `lecture-nadia-palier-3.md`, section 5 (la forme
  qui donne la reponse, le distracteur absurde, deux reponses defendables).
- **Statut global** : **VALIDE SOUS RESERVE**, prononce au rejeu 4 du 2026-10-22 par `nadia-prof` (s57, palier 4,
  instance A'), sur `f36d83bc`. REFUSE au premier passage, aux rejeux 2 et 3 (verdicts conserves ci-dessous). Fiche ouverte le
  2026-10-22 par `nadia-prof`.
- **Derniere mise a jour** : 2026-10-22, rejeu 4.

---

## Lecture complete

**lecture complete : ce1-emc-regle-sanction, 24 items lus** - 2026-10-22, 11:46 (UTC+02:00), lu par `date`, sur la branche
`s57-sofia-emc-ce1-02` a `9f3fdd4b` (`grep -c "bonneReponse:"` rend 24). Lus : les 24 enonces, bonnes reponses, 48
distracteurs, 24 indices et les familles declarees a cote de chaque item, l'en-tete, l'entree du catalogue. Paquets voisins lus
pour `NRC-CE1-1` et `CYC-1`, enonces et bonnes reponses : `cp-emc-regles-de-vie` (11), `cp-emc-regle-souhait-loi` (11),
`cp-emc-droits-enfant` (12), `cm1-emc-regle-loi` (11), `cm1-emc-droit-devoir` (11), `cm1-emc-regles-vie-collective` (11).

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

### Verdict du 2026-10-22 (`nadia-prof`, s57, palier 4, instance A') : **REFUSE**

**Commit joue** : jar construit dans mon worktree d'audit a `9f3fdd4b`, sommet de `s57-sofia-emc-ce1-02`
(`-pl bootstrap -am package -DskipTests`, mesure partielle), port 18512, *"Tomcat started on port 18512"* et PID lus dans mon
journal, arrete par PID. **Jeu par moi**, par requetes HTTP sur le jar (pas de navigateur) : 9 parties de 10 manches, les 24
enonces servis (24 sur 24), aucun enonce inconnu du paquet ; deux erreurs puis revelation sur la premiere manche de chaque
partie ; une erreur jouee sur les items 6 et 10 pour lire leur indice a l'ecran. **Non fait par moi** : le releve d'ecran a
744 x 551 (`ines-design`).

**Motifs du refus, chacun actionnable sans me redemander :**

**M1 - Tache 1 : la forme donne la reponse, et les items de regle reprennent le CP** (point d'audit de `lecture-nadia-palier-3.md`
section 5 ; `NRC-CE1-1`). Dans les 12 items, la regle est toujours la phrase entre chevrons (*« On ne déchire pas les livres »,
c'est quoi ?*) et la sanction toujours le geste repris sans chevrons (*Nettoyer la table, c'est quoi ?*) : 12 sur 12, l'enfant
repond en regardant les chevrons. Les six items de regle font de plus la tache de `cp-emc-regle-souhait-loi` (*"Est-ce une
règle ?"*, *"une règle qui vaut pour tous"*), et les indices des items 3, 5, 9 et 11 donnent ce critere du CP (*"vaut pour tous
les élèves"*). Ce qu'il faut : dans chaque item de la tache 1, la regle et la sanction se presentent sous la meme forme (par
exemple les deux comme paroles de l'adulte entre chevrons, ou aucune) ; l'enfant les departage par ce qui vient avant ou apres
l'acte, et par qui est concerne, pas par la typographie.

**M2 - Distracteurs que personne ne croit** (point d'audit de section 5 : un distracteur absurde fait une devinette). *"à garder le
secret"* (16, 19, 23), *"à ne rien dire à personne"* (15, 20, 24) et *"à cacher ce qui s'est passé"* (17) : une sanction donnee
par l'adulte devant la classe ne sert a rien cacher, aucun enfant de CE1 ne le pense. *"à changer la règle"* et *"à supprimer la
règle"* ne sont pas plus credibles. Dans quatre items de reparation (15, 17, 19, 23), les deux distracteurs viennent de ces
familles : la seule proposition vraisemblable est la bonne. Ce qu'il faut : les trois formules citees remplacees partout ou
elles sont ; dans chaque item de la tache 2, au moins un distracteur qu'un enfant peut croire et que la ligne ecarte, par exemple *"à venger Samir"* ou *"à faire plaisir au maître"* ;
toujours rien de ce qu'ecarte `RSA-CE1-1` (humiliation, punition physique, sanction collective, privation de repas).
*"à oublier ce qui s'est passé"* reste recevable.

**M3 - Tache 2 : la bonne reponse reprend le mot de l'enonce** (meme famille que M1). 7 items sur 12 : 13 et 19 (*cassé*), 14
(*crié*, *crie*), 17 (*taché*), 20 (*nom*), 21 (*mouillé*), 22 (*parlé*, *parle*). Aucun distracteur ne reprend
un mot de l'enonce. Ce qu'il faut : des bonnes reponses qui ne repetent pas le verbe de l'enonce ; les formes neutres deja
servies dans 15, 16, 23 et 24 (*"à réparer le tort fait"*, *"à rappeler la règle"*) conviennent.

**M4 - Item 20 n'est pas une sanction** (`EMC-5`, exactitude). *"Jade a rendu sa feuille sans son nom"* est un oubli, pas une
regle enfreinte ; lui faire ecrire son nom est une correction. Le paquet a lui-meme ajoute *exprès* aux actes qui pouvaient se
lire comme des accidents (`28457159`) : l'item 20 est le cas que ce commit voulait ecarter. Ce qu'il faut : un acte qui enfreint
une regle en connaissance de cause.

**M5 - Deux indices nomment le mauvais adulte**, lus a l'ecran apres une erreur. Item 6 : l'enonce dit *"La maîtresse lui fait
essuyer le sol"*, l'indice *"Le maître le demande après ce qu'Enzo a fait."* Item 10 : l'enonce dit *"La maîtresse lui fait la
replanter"*, l'indice *"Pense à ce que le maître demande à Mila ensuite."* Ce qu'il faut : le meme adulte dans l'enonce et
l'indice.

**Ce qui tient** (verifie, a ne pas defaire au rejeu) :

- `RSA-CE1-1` : deux taches de 12 ; *rappeler* et *reparer* chacun six fois en bonne reponse ; toutes les sanctions sont des
  reparations ou des gestes refaits ; aucune humiliation, punition physique, sanction collective ni privation de repas, distracteurs
  compris (refus d'avance non atteint).
- `RSA-CE1-2` : chaque acte est celui d'un personnage nomme ; aucune proposition ne qualifie un personnage ; aucun item en *tu*
  (`EMC-1`).
- `TRV-CE1-6` : 15 prenoms, 7 feminins et 8 masculins, varies ; 12 actes de filles et 12 de garcons, sans distribution du type
  d'acte par genre ; maitre 13 fois, maitresse 11 fois. `EMC-6` : aucun lieu de vie, commune ou type d'ecole.
- `NRC-CE1-1` hors M1 : aucun item ne reprend `cp-emc-regles-de-vie` ni `cp-emc-droits-enfant`. `CYC-1` : la sanction par une
  amende de `cm1-emc-regle-loi` n'est contredite par aucun item ; `cm1-emc-droit-devoir` et `cm1-emc-regles-vie-collective` ne
  portent pas sur la sanction.
- Les indices des items de rappel (14, 16, 18, 20, 22, 24) donnent une methode : *"est-ce que quelque chose est abîmé ?"*,
  *"la prochaine fois"*.

**Reserves, non bloquantes, a reprendre au rejeu si le lot touche ces items** :

1. *"la faute"* n'est jamais la bonne reponse sur les 12 items de la tache 1, ou les trois propositions sont toujours les memes :
   l'enfant l'ecarte des le deuxieme item. Si les propositions restent fixes, *la faute* est la bonne reponse de quelques items
   (*"Déchirer la page, c'est quoi ?"*).
2. Items 14 (*"lui fait reprendre la lecture"*) et 24 (*"lui fait reprendre son travail"*) : le geste ne dit pas ce qu'il
   rappelle ; un geste qui refait correctement ce qui a ete mal fait, comme a l'item 16.
3. Indice de l'item 24 : *"en leçon"* ; *"pendant la leçon"*.

**Reponses aux questions routees par `sofia-sm`** :

- **N1, *la faute*** : le mot est recevable au CE1 pour nommer l'acte. Ce qui ne l'est pas, c'est qu'il ne soit jamais juste
  (reserve 1) avec des propositions identiques (M1).
- **N2, *garder le secret* et *ne rien dire à personne*** : refuses, M2.
- **N3, items de rappel et leurs indices** : indices recevables ; l'item 20 est refuse (M4) ; 14 et 24 en reserve 2.
- **N4, `NRC-CE1-1`** avec `cp-emc-droits-enfant` et `cm1-emc-droit-devoir` : aucune reprise, aucune contradiction. La reprise
  est avec `cp-emc-regle-souhait-loi` (M1).
- **N5, `TRV-CE1-6`** : tenu.

**Ce qui suit** : le lot revient a `sofia-sm` (developpeuse) par `camille-po` ; mon rejeu est sur la marge de A' (0,5). `LP6`
reste sans worktree : la condition *"un verdict de `LP4` ou `LP5` sans refus"* n'est pas remplie par ce verdict.

**Je reste la seule garde** : qu'aucune forme (typographie, mot repris, article) ne donne la reponse ; qu'un distracteur soit
croyable ; qu'un acte sanctionne soit une regle enfreinte et non un oubli ; `NRC-CE1-1` (aucun garde-fou ne compare deux
paquets).

**Signe** : `nadia-prof`, 2026-10-22.

---

## Rejeu du 2026-10-22 (`nadia-prof`, s57, palier 4, instance A', marge de rejeu)

**lecture complete : ce1-emc-regle-sanction, 24 items lus** - 2026-10-22, 13:29 (UTC+02:00), lu par `date`, sur la branche
`s57-sofia-emc-ce1-02` a `1152fdc9` (`grep -c "bonneReponse:"` rend 24 ; `grep -c "«"` rend 4). Lus : les 24 enonces, bonnes
reponses, 48 distracteurs, 24 indices, les familles (a) a (g) et l'en-tete. Paquets voisins de `NRC-CE1-1` et `CYC-1` relus sur
leurs enonces et bonnes reponses (inchanges depuis le premier passage).

### Verdict du rejeu, 2026-10-22 (`nadia-prof`) : **REFUSE**, maintenu sur M1 seul

Le verdict du premier passage reste ecrit au-dessus ; celui-ci s'y ajoute.

**Commit joue** : `1152fdc9`, jar construit dans un worktree detache de mon cru a ce commit (`-pl bootstrap -am package
-DskipTests`, mesure partielle), port 18512, *"Tomcat started on port 18512"* et PID lus dans mon journal, arrete par PID. Jeu par
moi, requetes HTTP : 9 parties de 10 manches, 24 enonces servis sur 24, aucun enonce inconnu du paquet ; deux erreurs puis
revelation sur la premiere manche de chaque partie, indices lus a l'ecran.

**Motif maintenu - M1, tache 1 : la forme donne encore la reponse pour la regle.** La decision de l'utilisatrice du 2026-10-22
(*"1 oui regle entre chevrons"*) est appliquee ; je ne la discute pas. Ce que je constate sur le paquet tel qu'il l'applique :
les chevrons ne figurent que dans les 4 items dont la cible est la regle (1, 4, 7, 10 ; `grep -c "«"` rend 4). Presents, ils
donnent *la regle* ; absents, ils l'ecartent. Sur 12 items sur 12, *la regle* se decide par la typographie, jamais par le
contenu. Les 8 autres items font bien travailler la difference entre la sanction et la faute (avant ou apres, ce que le
personnage a fait ou ce qu'on lui demande) ; mais la ligne demande de **distinguer une regle d'une sanction**, et aucun item ne
fait cette distinction-la par le sens.

**Ce qui serait acceptable**, sans toucher a la regle entre chevrons :

- (a) la regle de la classe citee entre chevrons dans l'enonce des **12** items, quelle que soit la cible ; la question designe
  l'element par une forme identique pour les trois cibles, sans chevrons (*"Ne pas salir les murs, c'est quoi ?"*, *"Laver le
  mur, c'est quoi ?"*, *"Salir un mur, c'est quoi ?"*), avec des regles affirmatives et negatives melangees, pour que la negation
  ne designe pas la regle a son tour ; `LON-CE1-1` et `-2` a tenir avec la phrase de plus ;
- (b) ou la sanction donnee, elle aussi, comme paroles de l'adulte entre chevrons (*« Lave le mur. »*), pour que les chevrons
  soient presents pour la regle et pour la sanction.

Si la decision de l'utilisatrice veut dire que **seule** la regle porte des chevrons, aucune forme de la tache 1 ne cesse de la
designer, et M1 reste refuse : c'est a `camille-po` de lui poser la question avec ces deux formes, pas a moi de la trancher.

**Leve** (verifie) :

- M1, partie `NRC-CE1-1` : les indices des items de regle ne donnent plus le critere du CP (*"pour tous"*) mais celui du CE1
  (*"avant ou après"*) ; la question n'est plus *"Est-ce une règle ?"*.
- M2 : distracteurs (d) *à venger* la victime, (e) *à plaire au maître*, (g) *à oublier l'histoire*, *à occuper* le personnage :
  chacun est un but qu'un enfant peut croire et que la ligne ecarte ; chaque item de la tache 2 en porte au moins un. Aucun
  distracteur absurde.
- M3 : aucune bonne reponse ne reprend le verbe de l'enonce (*à réparer le tort fait*, *le dégât*, *le mal fait*, *les dégâts* ;
  *à rappeler la règle*, *les règles*, *l'interdit*, *la consigne*).
- M4 : l'item 20 est une regle enfreinte expres (*"a bavardé exprès pendant la dictée"*), reprise en silence.
- M5 : l'adulte de chaque indice est celui de l'enonce (items 6, 8, 9, 11, 12 relus dans le paquet ; celui de 8 lu a l ecran, celui de 11 non vu a l ecran, aucune erreur jouee sur cet item).
- Reserve 1 : *la faute* est la bonne reponse de 4 items (3, 6, 9, 12). Reserve 2 : l'item 14 refait correctement
  (*relire doucement*). Reserve 3 : sans objet, l'item 24 est remplace.

**Retenu** : `RSA-CE1-1` (rappeler et reparer chacun six fois en bonne reponse ; aucune humiliation, punition physique, sanction
collective ni privation de repas, distracteurs compris) ; `RSA-CE1-2` ; `TRV-CE1-6` (les memes 15 prenoms, 7 et 8) ; `EMC-1`,
`EMC-6` ; `CYC-1`. *Venger* en distracteur n'est pas une humiliation ; il est ecarte, jamais propose comme but.

**Reserves nouvelles, datees du 2026-10-22, non bloquantes** :

1. Item 24 : *"Aya a tapé exprès sa règle sur la table"* ; dans un paquet ou *la règle* est une reponse, *sa règle* (l'objet)
   trouble la lecture ; et *"la poser"* arrete le geste sans le refaire correctement. Un autre objet, un geste refait.
2. Item 6, indice : *"avant que le maître intervienne"* ; *intervienne* est un mot que l'enfant de CE1 lit mal seul.
3. **Pli** : non remesure sur ce commit. Au premier passage, 8 items sur 24 avaient l'indice sous le pli apres une erreur
   (`ines-design`, `089fb477`, sur `9f3fdd4b`) ; l'en-tete dit des seuils a remesurer. Reserve non bloquante (4.1 point 4) tant
   que le releve du commit fusionne n'est pas fait.

**Marge** : ce rejeu consomme la marge de A' (0,5). Un troisieme passage n'a plus de marge : a `camille-po` et `sofia-sm`. Je le
fais sur la tache 1 seule si elle est seule changee. `LP6` reste sans worktree.

**Je reste la seule garde** : qu'aucune forme ne donne la reponse ; qu'un distracteur soit croyable ; qu'un acte sanctionne soit
une regle enfreinte ; `NRC-CE1-1`.

**Signe** : `nadia-prof`, 2026-10-22.

---

## Rejeu 3 du 2026-10-22 (`nadia-prof`, s57, palier 4, instance A' ; `DEC-0120` A.155)

**lecture complete : ce1-emc-regle-sanction, 24 items lus** - 2026-10-22, 14:29 (UTC+02:00), lu par `date`, sur la branche
`s57-sofia-emc-ce1-02` a `41203188` (`grep -c "bonneReponse:"` rend 24 ; `grep -c "«"` rend 12). Diff du paquet depuis
`1152fdc9` : les 12 items de la tache 1, l'item 24, l'indice de l'item 6. Mots des 12 enonces de la tache 1 recomptes : 18 a 20.

### Verdict du rejeu 3, 2026-10-22 (`nadia-prof`) : **REFUSE**, sur l'item 9 seul

Les verdicts precedents restent ecrits au-dessus ; celui-ci s'y ajoute.

**Commit joue** : `41203188`, jar construit dans mon worktree detache `recreo-s57-nadia-p4-ap-jeu` a ce commit (`-pl bootstrap
-am package -DskipTests`, mesure partielle), port 18512, *"Tomcat started on port 18512"* et PID lus dans mon journal, arrete par
PID. Jeu par moi, requetes HTTP : 15 parties de 10 manches en deux series ; les 24 enonces servis sur l'ensemble (l'item 11 dans
la seconde serie seulement) ; deux erreurs et l'indice lus sur l'item 6.

**M1 leve.** La forme (a) que j'avais ecrite est appliquee : la regle entre chevrons dans les 12 enonces, la question *"<element>,
c'est quoi ?"* sans chevrons pour les trois cibles, 6 regles affirmatives et 6 negatives. Les chevrons ne designent plus la cible.
La barre est celle que j'avais ecrite ; elle est atteinte, je la leve.

**Motif du refus, item 9 seul** (exactitude ; `EMC-5`) : *"« Ne pas effacer ». Zoé a effacé un calcul : la maîtresse lui fait le
réécrire. Effacer un calcul, c'est quoi ?"*, bonne reponse *la faute*. Un enfant de CE1 efface ses calculs tous les jours : ce
n'est ni une faute ni une regle de la classe. L'item enseigne le contraire de ce qui est vrai. Aux passages precedents, l'acte
etait *"a effacé exprès le calcul de Léo"* : le travail d'un autre, efface volontairement. Ce qu'il faut : un acte qui est une
faute (le travail d'un autre, ou un geste interdit), et une regle que cet acte enfreint, dans les 20 mots.

**Leve, verifie** : item 24 (*"Aya a lancé exprès sa trousse. La maîtresse lui fait la reposer doucement."* : un autre objet, un
geste refait doucement) ; indice de l'item 6 (*"Cela est arrivé avant que le maître parle."*, lu a l'ecran). Tache 2 inchangee
depuis le rejeu 2 : ce qui y tenait tient. `NRC-CE1-1` : aucune des 12 regles n'est une regle de `cp-emc-regles-de-vie` ni de
`cp-emc-regle-souhait-loi`. `TRV-CE1-6`, `RSA-CE1-1`, `RSA-CE1-2` : tenus.

**Correction de mes propres constats** : aux deux premiers passages, j'ai accepte *"Noé a sali un mur"* et *"Lina a déchiré une
page"* sans *exprès*. Je ne fais donc pas de l'intention un motif sur la tache 1 : ce serait relever ma barre apres coup. L'item 9
n'est pas refuse pour une intention absente, mais parce qu'effacer un calcul n'est pas une faute.

**Reserves nouvelles, datees du 2026-10-22, non bloquantes** :

1. Items 6 (*"Jade a taché un dessin"*), 10 (*"Hugo a renversé la tour d'Aya"*) et 12 (*"Enzo a mouillé le sol"*) portaient
   *exprès* au rejeu 2 et l'ont perdu pour tenir 20 mots : chacun peut se lire comme un accident. Un acte voulu, ou la mention
   qu'il l'est.
2. La question commence par *"Ne pas"* dans 2 items (7, 10), et c'est toujours la regle : aucune question de sanction ni de faute
   n'est negative. La lettre de ma forme (a) est tenue (6 et 6) ; son but, que la negation ne designe pas la regle, l'est sur 10
   items sur 12.
3. Item 10 : *"« Ne pas casser »"* pour une tour renversee ; une regle qui dit le geste fait.
4. Item 4 : *"Jouer franc jeu"* est une expression que l'enfant de CE1 lit mal seul.
5. Pli : non remesure sur ce commit (8 items sur 24 sous le pli au premier passage, `ines-design`, `089fb477`).

**Points de `sofia-sm`** :

- **La reponse est-elle redite ?** Non : l'enonce cite la regle entre chevrons sans le mot *règle*, et la question reprend la
  phrase sans la nommer. Le mot de la bonne reponse n'est ecrit nulle part avant la reponse.
- ***Être soigneux*, *Faire attention*, *Garder propre*** : recevables. Une regle de classe peut etre generale ; ce que j'exige,
  c'est qu'elle soit celle que l'acte enfreint. Elle l'est pour ces trois items (2, 6, 8).

**Questions de `camille-po`** :

- **Q1, la forme (a) tient-elle dans 20 mots et 3 phrases, la derniere etant la question ?** Oui : j'ai recompte 18 a 20 mots
  et 3 phrases dans les 12 enonces. Elle n'y tient qu'en retirant *exprès* (reserve 1) et le proprietaire du calcul (motif).
- **Q2, le rejeu 3 prend-il les reserves non bloquantes ?** Il a pris l'item 24 et l'indice de l'item 6 : levees. Le pli reste
  en reserve, a remesurer par `ines-design`.
- **Q3, la forme (a) vaut-elle pour `EMC-CE1-03` et `-04` ?** Non comme forme : c'est la reponse a M1 de cette ligne, la ou il
  faut distinguer une regle d'une sanction. Pour `-03` et `-04` valent `DDE-CE1-1`, `-2`, `INT-CE1-1`, `-2`, et le point
  d'audit commun : aucune forme (typographie, mot repris, article, negation) ne donne la reponse.
- **Q4, le prix** : 0,5 pour ce rejeu (12 items reecrits sur 24, l'item 24 et un indice : la moitie du paquet relue et
  rejouee) ; 0,25 pour le rejeu 3 de `LP1` (un item et un indice).

**Ce qui suit** : un quatrieme passage n'a pas de prix dans `DEC-0120` A.155 ; il est a `camille-po` et `sofia-sm`. Je ne
rejoue que l'item 9 s'il est le seul change, et son prix est 0,25. `LP6` reste sans worktree.

**Je reste la seule garde** : qu'un acte appele *faute* en soit une ; qu'aucune forme ne donne la reponse ; `NRC-CE1-1`.

**Signe** : `nadia-prof`, 2026-10-22.

---

## Rejeu 4 du 2026-10-22 (`nadia-prof`, s57, palier 4, instance A' ; `DEC-0120` A.159 a A.163)

**lecture complete : ce1-emc-regle-sanction, 24 items lus** - 2026-10-22, 15:15 (UTC+02:00), lu par `date`, sur la branche
`s57-sofia-emc-ce1-02` a `f36d83bc` (`grep -c "bonneReponse:"` rend 24). Diff du paquet depuis `41203188` : items 4, 6, 9, 10,
12 de la tache 1 et l'indice de l'item 12 ; tache 2 inchangee. Mots des 12 enonces de la tache 1 recomptes : 18 a 20.

### Verdict du rejeu 4, 2026-10-22 (`nadia-prof`) : **VALIDE SOUS RESERVE**

Les verdicts precedents restent ecrits au-dessus ; celui-ci s'y ajoute.

**Done pedagogique prononce sur `f36d83bc`**, sommet de `s57-sofia-emc-ce1-02`. `camille-po` peut passer `EMC-CE1-02` a
`couvert-valide` et faire des stories des reserves ci-dessous, non bloquantes.

**Commit joue** : `f36d83bc`, jar construit dans mon worktree detache `recreo-s57-nadia-p4-ap-jeu` a ce commit (`-pl bootstrap
-am package -DskipTests`, mesure partielle), port 18512, *"Tomcat started on port 18512"* et PID lus dans mon journal, arrete par
PID. Jeu par moi, requetes HTTP : 15 parties de 10 manches, 24 enonces servis sur 24 ; deux erreurs et l'indice lus sur l'item 9 ;
les cinq items changes joues jusqu'a la bonne reponse.

**Motif du rejeu 3 leve, item 9** : *"« Rendre les affaires ». Zoé garde la gomme de Léo : le maître la lui fait rendre. Ne pas
rendre, c'est quoi ?"*, bonne reponse *la faute*. Garder la gomme d'un camarade, sans la lui rendre, est une faute ; la regle
citee, rendre les affaires, est celle que l'acte enfreint ; rendre la gomme est une reparation (`RSA-CE1-1`).

**Reserves du rejeu 3** :

1. Items 6 et 12 : *exprès* restaure, **levee**. Item 10 : *"a démoli"* dit un geste voulu, **levee**.
2. Negation : la question commence par *"Ne pas"* dans 3 items, deux de regle (4, 7) et un de faute (9). *"Ne pas"* ne designe
   plus la regle a lui seul ; qu'aucune question de sanction ne soit negative tient a ce qu'une sanction est un geste. **Suffisant,
   levee.**
3. Item 10, regle *"« Laisser jouer »"* : c'est la regle que l'acte enfreint (demolir la tour d'Aya l'empeche de jouer).
   **Levee**, avec la reserve 2 ci-dessous sur sa forme.
4. Item 4, *"Ne pas tricher"* remplace *"Jouer franc jeu"* : **levee**.

**Garantie de `theo-archi`, verifiee a la lecture** : dans les 4 items de cible sanction (2, 5, 8, 11), la question reprend le mot
entier du geste de l'adulte (*recoller*, *s'excuser*, *ramasser*, *nettoyer*).

**Retenu sur ce commit** : `RSA-CE1-1`, `RSA-CE1-2`, `NRC-CE1-1`, `TRV-CE1-6`, `EMC-1`, `EMC-6` ; tache 1 a trois cibles de 4
items, la regle entre chevrons dans les 12 enonces, 18 a 20 mots ; tache 2 inchangee depuis le rejeu 2.

**Reserves, non bloquantes** :

1. Item 6 : *"Jade a taché exprès un dessin"*, sans dire a qui il est ; tacher son propre dessin n'est pas une faute envers un
   autre. Le dessin d'un camarade, nomme.
2. Item 10 : *"Laisser jouer"* ne dit pas qui ; l'enfant de CE1 lit mieux *"Laisser jouer les autres"*, si la ligne le tient.
3. Pli : non remesure sur ce commit (`ines-design`).

**`LP6`** : la condition de sa composition (*"un verdict de `LP4` ou `LP5` sans refus"*) est remplie par ce verdict. `LP6` peut
ouvrir, cote pedagogique ; le reste de son ouverture (fusion de `LP5`, `RF5`) n'est pas a moi.

**Prix** : 0,25. Cinq items courts et un indice, relus et rejoues ; le reste du paquet est inchange et deja verifie.

**Je reste la seule garde** : qu'un acte appele *faute* en soit une et enfreigne la regle citee ; qu'aucune forme ne donne la
reponse ; `NRC-CE1-1`.

**Signe** : `nadia-prof`, 2026-10-22.
