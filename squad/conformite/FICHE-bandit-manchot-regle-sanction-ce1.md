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
- **Statut global** : **REFUSE**, prononce le 2026-10-22 par `nadia-prof` (s57, palier 4, instance A'). Fiche ouverte le
  2026-10-22 par `nadia-prof`.
- **Derniere mise a jour** : 2026-10-22.

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
