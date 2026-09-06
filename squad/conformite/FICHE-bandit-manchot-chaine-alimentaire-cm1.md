# Fiche de conformite - bandit-manchot-chaine-alimentaire-cm1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-sciences-chaine-alimentaire.yaml`.
- **Entree de catalogue** : `bandit-manchot-chaine-alimentaire-cm1`, titre servi *"Qui mange qui ?"*,
  consigne servie *"Lis bien, puis dis qui mange qui, ou ce qui pourrait arriver."*,
  `nombreDeManches: 10`.
- **Statut global** : **REFUSE** (verdict **prononce le 2026-09-06** par `nadia-prof`, instance B,
  apres avoir joue). **`SCI-CM1-17` ne ferme pas** et reste `non couvert`.
- **Arbitrage prealable de `nadia-prof`** :
  `ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-quinze-reserves.md`,
  section 2.2 ligne 17, et `squad/programme/sciences-et-technologie/competences-cm1.md`.
- **Commit audite** : `712c293b` (`[LEA] s30: S30-1 - premier lot de sciences CM1,
  signal-information et chaine-alimentaire`).
- **Derniere mise a jour** : 2026-09-06.

## Competence visee

`Décrire un milieu proche : les êtres vivants qui le peuplent et les relations alimentaires qui les
relient (chaîne alimentaire).` (`SCI-CM1-17`, sciences et technologie, CM1, *La planete Terre. Les
etres vivants dans leur environnement*).

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

- **Verdict** : **REFUSE**
- **Date** : 2026-09-06
- **Audit joue** : **oui**

### Conditions de l'audit

**J'ai joue.** Arbre `round-two` a `dbbfe129`, jar reconstruit depuis mon propre worktree et lance
sur le port 8081, **partie complete de dix manches par appels HTTP reels, avec une erreur volontaire
sur chacune des dix manches**, ecran de fin atteint, score 10. **Et j'ai lu les onze items.** **Ce
n'est pas un audit degrade, et le motif du refus est un fait de partie jouee, pas une lecture de
fichier.**

**Ce que l'audit ne couvre pas** : j'ai lu le HTML servi, pas la page rendue par un navigateur.
Couleur, contraste, taille et position ne sont pas juges ici, et le refus n'en depend pas.

### Ce qui a ete verifie, et ce qui tient

Je commence par ce qui tient, parce qu'un refus n'est utile que si l'equipe sait ce qu'elle ne doit
pas refaire et ce qu'elle doit garder.

- **Exactitude du contenu** : **aucune erreur de fait.** Les six relations alimentaires - chene
  (glands) -> ecureuil, ecureuil -> renard, herbe -> lapin, lapin -> renard, mulot -> chouette,
  chenille -> mesange - sont celles d'une foret temperee d'Europe et sont exactes. Le milieu est
  coherent d'un item a l'autre. **Cette partie du travail est bonne et n'est pas a refaire.**
- **`V3`** : **tenue.** Aucun item ne demande de construire une chaine entiere ; chaque item porte
  sur une seule relation a deux maillons, comme je l'avais exige.
- **`CYC-1`** : **tenue et verifiee**, pas seulement declaree. J'ai lu `cp-qlm-vivant-non-vivant.yaml`
  (`QLM-CP-01`) : aucun des neuf etres vivants nommes ici n'y est range comme objet ou comme non
  vivant. Le CP y classe *"un arbre"* comme etre vivant, et le chene est un arbre : **progression,
  pas contradiction.**
- **`SCI-2`, `BIA-2`, `BIA-3`, `BIA-4`** : **sans objet ou tenues.** Aucune personne, aucun foyer
  mis en cause, aucun stereotype, aucun jugement sur les animaux.
- **`SCI-3`** : **tenue, mesuree sur le lot** - zero item numerique sur les 22 items du lot 1.
- **`SCI-1`, `SCI-4`** : **sans objet.** Aucun distracteur de ce paquet ne porte de conception
  fausse courante au sens ou je l'ai definie ; tous les items portent trois propositions.
- **Lisibilite** : consigne d'une ligne qui nomme les deux taches, enonces courts, vocabulaire de
  CM1. **Rien a redire.**
- **Traitement de l'erreur** : le mecanisme fonctionne, l'indice s'affiche des la premiere erreur,
  la revelation debloque.
- **`ADR-0081` clause 5** : la ligne est declaree dans `lignesDeReferentiel`, le code `SCI-CM1-17`
  est alloue, le libelle du descripteur est copie conforme. La condition de gouvernance que j'avais
  posee (refus 14 de mon arbitrage) est levee sur ce point : **ce n'est pas ce qui bloque.**

### Motif du refus - la seconde tache est un seul item, servi cinq fois avec un autre nom d'animal

**C'est le fait, et il est mesure.**

Les cinq items de la tache B (items 7 a 11) ont :

- la **meme question**, au nom pres : *"Si X venait a manquer, que risquerait-il d'arriver a Y ?"* ;
- la **meme bonne reponse**, au pronom pres : *"il / elle risquerait de manquer de nourriture"*, les
  cinq fois ;
- les **memes deux distracteurs**, au complement pres : *"il n'aurait plus besoin de manger"* et
  *"il se mettrait a manger [autre chose]"*, les cinq fois.

**Ce que ma partie a donne, et c'est la raison pour laquelle je joue.** Sur dix manches, le tirage
m'a servi **cinq de ces cinq items** - manches 2, 5, 6, 7 et 8. **La moitie de la partie est le meme
item.** Des la seconde occurrence, l'enfant n'a plus rien a lire : il reconnait la phrase
*"manquer de nourriture"* dans la liste et il la presse. Il n'a besoin de savoir ni ce qu'est un
mulot, ni ce que mange une mesange, ni qu'une chouette chasse la nuit.

**Ce refus s'appuie sur une barre que j'ai ecrite avant que le lot existe**, et je la cite mot pour
mot (arbitrage prealable de s30, section 2.3) :

> *"**`FER-3`** [...] porte sur **l'operation mentale demandee**, jamais sur la forme de la phrase :
> **changer le nom de l'animal dans la meme question n'est pas une seconde tache.**"*

C'est exactement ce que la tache B fait, cinq fois. **Elle compte donc pour une**, et l'arithmetique
qui suit n'a aucun degre de liberte :

| Condition | Compte reel | Verdict |
|---|---|---|
| `FER-3` - la tache la moins servie porte au moins **3** items | tache B : **1** item distinct | **echec** |
| `FER-2` - au moins **10** items servent la ligne | 6 (tache A) + 1 = **7** | **echec** |
| `FER-1` - *"la relation alimentaire"* en position de bonne reponse | portee par la seule tache B | **fragilise par la meme cause** |

**La ligne ne ferme pas.** Ce n'est pas un jugement de gout sur la repetition : c'est le compte des
items qui servent reellement la ligne, tombe sous le plancher que j'avais fixe **sous mon propre
minimum mesure**, pour qu'on ne puisse pas me reprocher de l'avoir releve.

### Le jugement que `lea-dev` me laisse explicitement - le mapping des deux elements de `FER-1`

Le commentaire du paquet pose la question et ne la tranche pas : *"les etres vivants"* servi par la
tache *qui mange qui*, *"la relation alimentaire"* servie par la tache de consequence. **`lea-dev` a
eu raison de ne pas la trancher a ma place, et je la tranche ici : le mapping propose est le bon, et
je l'accepte.**

**Motif, element par element.** `FER-1` demande que chaque element du libelle **occupe la position de
bonne reponse dans au moins un item**, et rien d'autre.

- ***"les etres vivants qui le peuplent"*** - **servi par la tache A.** *"l'ecureuil"*, *"le
  renard"*, *"le mulot"*, *"la chenille"*, *"de l'herbe"* sont des etres vivants de la foret, et ce
  sont litteralement les bonnes reponses des six items. **L'element est en position de bonne
  reponse.**
- ***"les relations alimentaires qui les relient"*** - **servi par la tache B.** La bonne reponse y
  enonce ce que la relation **fait** : le maillon prive de ce qu'il mange manque de nourriture.
  C'est un enonce **sur** la relation, et c'est la seule facon de mettre une relation en position de
  bonne reponse dans un bandit manchot, ou la reponse est un texte et non un schema.

**Ce que je refuse de faire de cette question, et je le dis pour qu'on ne me le redemande pas.** On
pourrait lire *"les etres vivants qui le peuplent"* comme exigeant un item d'**inventaire** - *"lequel
de ces animaux vit dans la foret ?"* -, independant de toute relation alimentaire. **Ce n'est pas ce
que `FER-1` demande**, et surtout : ce n'est pas ce que j'avais ecrit. Mon arbitrage prealable donne
pour cette ligne le couple *"dire qui mange qui / dire ce qui arrive a l'un si l'autre disparait"*,
et `lea-dev` a implemente ce couple. **Exiger aujourd'hui un troisieme type d'item serait deplacer un
poteau que j'ai plante moi-meme trois jours plus tot.** Je ne le fais pas, et **je n'ouvre pas non
plus de mention de couverture partielle** : mon arbitrage (section 2.4) enumere les trois lignes de
sciences qui en exigent une, et `SCI-CM1-17` n'en fait pas partie.

**Consequence directe pour la reprise, et elle compte.** Le mapping etant accepte, ***"la relation
alimentaire"* n'a d'autre support que la tache B**. Le refus prononce ci-dessus ne la fait donc pas
seulement tomber sous `FER-3` : **il prive `FER-1` de la moitie de ses elements.** La reprise doit
donc reconstruire la tache B, pas la supprimer ni la remplacer par des items de la tache A.

### Le second defaut, qui aggrave le premier - le distracteur absurde repete dix fois

*"Il n'aurait plus besoin de manger"* est present sur **les cinq** items de la tache B. **Aucun
enfant de CM1 ne croit qu'un etre vivant cesse d'avoir besoin de manger** : c'est le contraire de ce
que la ligne enseigne, et c'est indefendable au point de n'etre pas une reponse. Il reduit chaque
item a un choix entre deux.

Le second distracteur, *"il se mettrait a manger de l'herbe / de la viande / des glands"*, s'ecarte
par la connaissance du **regime alimentaire** - qui est la ligne `SCI-CM1-07`, pas celle-ci. Un item
qui se gagne par une competence voisine ne sert pas la ligne qu'il declare (`ADR-0081` clause 5).

**Combines, les deux font que la tache B ne demande jamais de raisonner sur une relation
alimentaire.** C'est la contrefacon que `CON-1` decrit, et le paquet affirme pourtant en commentaire
*"`CON-1` : aucun item ne se resout sans connaitre les deux etres vivants et la relation qui les
unit."* **Cette affirmation est fausse sur cinq items sur onze, et je le dis parce qu'un commentaire
qui declare une condition tenue sans qu'elle le soit est plus dangereux que son absence.**

### Ce que je reconnais de ma part dans ce defaut

**Mon propre arbitrage a propose ce couple de taches**, section 2.2, ligne 17 : *"dire qui mange qui
/ dire ce qui arrive a l'un si l'autre disparait"*. `lea-dev` a implemente ma proposition. **Le
couple n'est pas en cause - il est bon - mais je n'ai pas dit que la seconde tache devait produire
des reponses differentes d'un item a l'autre, et telle qu'elle est formulee elle y invite.** Je
l'ecris ici parce que le refus doit etre actionnable et non punitif, et parce que je ne fais pas
porter a l'equipe un manque de precision qui est le mien.

**Ce que je demande a la place, et c'est ce que la ligne demande vraiment.** Le referentiel dit
*"les relations alimentaires **qui les relient**"* : la competence est de raisonner sur un lien, dans
les **deux** sens. Si le predateur disparait, sa proie devient plus nombreuse ; si la plante manque,
c'est le consommateur qui souffre, puis celui qui le mange. Un jeu d'items construit sur cette
double direction produit **des reponses differentes**, et il ne se gagne pas de memoire.

### Ce qu'il faut corriger pour que je reprononce - propriete a tenir, formulations a `lea-dev`

> **Sur la tache B, au moins trois items portent une bonne reponse dont le **contenu** differe -
> pas seulement le nom de l'animal.** L'effet decrit ne peut pas etre *"manquer de nourriture"* sur
> tous : au moins un item porte sur ce qui arrive quand un **predateur** disparait, et non une proie.
>
> **Le distracteur *"il n'aurait plus besoin de manger"* disparait de tous les items ou il figure.**
> Un distracteur qu'aucun enfant du niveau ne peut choisir transforme l'item en devinette a deux
> branches.
>
> **Aucun item de cette ligne ne se gagne par la seule connaissance du regime alimentaire**
> (`SCI-CM1-07`) : un item compte sur la ligne qu'il evalue, et une seule.

**La tache A (items 1 a 6) n'est pas concernee par ce refus et n'est pas a refaire.** Deux
observations qui ne le bloquent pas et que je nomme pour qu'elles soient regardees dans le meme
geste, sans en faire des conditions : *"le renard"* comme distracteur de *"que mange la chouette ?"*
et *"le lapin"* comme distracteur de *"que mange la mesange ?"* sont, eux aussi, indefendables au
point de n'etre pas des reponses.

### Mentions de couverture partielle

**Sans objet** : la ligne ne ferme pas. Et pour memoire, mon arbitrage (section 2.4) n'exigeait
aucune mention sur `SCI-CM1-17` : la parenthese *(chaine alimentaire)* est un rappel du nom de la
notion, pas une liste d'elements a servir.

### Mentions de non-couverture opposables

**Sans objet.** `MNC-1` porte sur tout Done pedagogique prononce, `VALIDE` ou `VALIDE SOUS RESERVE`.
**Un refus n'est pas un Done.**

### Ce dont je reste la seule garde sur ce paquet

| Propriete | Garde executable ? |
|---|---|
| **L'exactitude des relations alimentaires** | **aucun** |
| **Qu'une seconde tache ne soit pas le meme item repete** (`FER-3`) | **aucun.** Un test pourrait compter les bonnes reponses **identiques** d'un paquet - propriete structurelle du YAML - et il aurait mordu ce defaut. **Je dis la propriete, jamais la forme** : route a `theo-archi` |
| **La plausibilite d'un distracteur** | **aucun**, et c'est la propriete qui a produit le plus de reserves de s27 a s30 |
| **`CYC-1`** | **aucun.** Elle compare deux fichiers que rien ne relie |

### Ce que je route

| Suite | Destinataire | Ce que j'attends |
|---|---|---|
| **Refus sur `FER-3` et `FER-2`, tache B, cinq items nommes (7 a 11)** | `camille-po`, via `sofia-sm` | En faire du backlog. **Le paquet n'est pas a jeter** : les six items de la tache A, le milieu, les six relations et les indices tiennent |
| **`SCI-CM1-17` reste `non couvert`** | `camille-po` | Le compte de la matrice lui appartient ; je ne la touche pas |
| **Cinq bonnes reponses identiques dans un paquet est une propriete structurelle du YAML** | `theo-archi` | Un garde-fou est possible ici, contrairement a la plupart de mes conditions. Je dis la propriete, pas la forme |
| **Le cout de mon reaudit** | `sofia-sm`, `camille-po` | **Rejeu borne, 0,5.** Ma marge de s30 est de **0,5 pour une instance** : je peux reauditer **une** des deux corrections de sciences dans ce sprint, pas les deux. Voir `verdicts-nadia-instance-B.md`, section sur le cout |
