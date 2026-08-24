# DEC-0045 - "Nom ou verbe ?" quitte le catalogue jouable : reserve pedagogique bloquante non
# couverte par aucune decision ; regle generale sur l'exposition au catalogue

- **Type** : perimetre produit, retrait d'un jeu du catalogue, regle generale de gouvernance
- **Date** : 2026-08-24
- **Auteur** : camille-po
- **Sprint** : hors sprint - trou de cloture de s19, trouve par `sofia-sm` en verifiant la
  completude de la fermeture (`squad/README.md`, section 9, etape 7).
- **Competence programme visee** : francais, CP (cycle 2), "Distinguer les principaux types de
  mots (nom, verbe) dans une phrase tres simple" pour le retrait lui-meme ; transverse - pas de
  contenu pedagogique - pour la regle generale posee en section 2.
- **Statut** : Acceptee

## Contexte

`sofia-sm` a verifie, a la cloture de s19, que `bandit-manchot-nature-mot-cp` ("Nom ou verbe ?")
est servi tel quel par `adapters/adapter-content/src/main/resources/catalogue.yaml`, sans
marqueur de statut ni mecanisme de desactivation - **le schema du catalogue n'en porte aucun**,
verifie ici : `id`, `titre`, `consigne`, `mecanique`, `paquet`, `nombreDeManches`, rien de plus.
Un enfant de CP peut y jouer aujourd'hui.

`nadia-prof` a pourtant rendu, le meme jour, un verdict qui nomme deux reserves **bloquantes** sur
ce jeu (`squad/conformite/FICHE-bandit-manchot-nature-mot-cp.md`) :

- **`NAT-5`** - aucun renfort visuel ne designe le mot cible autrement que par des guillemets
  francais qu'un enfant de CP n'a pas appris.
- **`NAT-9`** - la consigne servie ("Le mot entre guillemets est-il un nom ou un verbe ?") est
  circulaire pour cet enfant : pour savoir de quel mot on parle, il doit lire un mot qui nomme un
  signe qu'il ne connait pas.

Elle l'ecrit elle-meme : **"Le Done pedagogique est ouvert"**, au sens ou il reste en attente -
la phrase suivante de son verdict precise que la ligne de matrice ne peut pas passer
`couvert-valide` tant que ces deux reserves restent ouvertes. `DEC-0044` traite le sequencement de
leur correction (routage a `ines-design` et `lea-dev`) et confirme que la ligne de matrice reste
`en cours` : c'est correct et je n'y reviens pas. **Mais aucune decision, la mienne comprise,
n'a jamais dit si le jeu devait rester jouable pendant ce temps.** C'est le trou que `sofia-sm` a
trouve.

Fait a peser, que je ne minimise pas : le contenu de ce paquet n'est pas faux. Le motif de refus
initial (`NAT-1`, seize phrases sur seize resolues par un raccourci de position sans lire le mot
cible) est leve et remesure a 12 sur 16, et c'est le seul paquet du sprint qui ne perd rien sous
le pli. Le probleme n'est pas ce que le jeu enseigne, c'est que la maniere de designer le mot
cible et de poser la question n'est pas encore lisible pour l'age vise.

## Decision

### 1. "Nom ou verbe ?" quitte le catalogue jouable, avec effet immediat

L'entree `id: bandit-manchot-nature-mot-cp` sort de
`adapters/adapter-content/src/main/resources/catalogue.yaml`. Meme mecanisme que `DEC-0009`,
`DEC-0014`, `DEC-0024` et `DEC-0031` : l'absence d'entree, sans drapeau ni chemin mort, execution
dans le meme geste que cette decision et non comme une story a planifier plus tard - c'est
exactement le mode de defaillance que `DEC-0031` refusait deja de reproduire (*"une decision de
retrait ecrite sans que le catalogue ne bouge, et un enfant qui continue de jouer un contenu
retire sur le papier"*), et c'est trait pour trait ce qui vient de se produire ici pendant tout un
sprint.

Le paquet de contenu (`adapters/adapter-content/src/main/resources/contenu/cp-nature-mot.yaml`)
reste au depot, inchange : c'est la matiere de travail de `NAT-5`/`NAT-9`, pas un contenu a
reprendre. Je ne modifie pas `catalogue.yaml` moi-meme : il vit sous `adapters/`, hors de mon
perimetre d'ecriture. Cette action est routee a `sofia-sm` dans le meme geste que cette decision.

### 2. Condition de retour, dans les mots de `nadia-prof`

Le retour au catalogue suppose que `NAT-5` et `NAT-9` soient traitees (routage deja pose par
`DEC-0044`) et que `nadia-prof` reaudite l'increment et ne nomme plus aucune reserve bloquante sur
ce jeu. Pas de troisieme condition ajoutee ici : je ne desserre ni ne resserre ce que sa fiche
demande deja.

### 3. Regle generale, pas un cas d'espece : l'exposition au catalogue suit le Done pedagogique par jeu, pas le statut de la ligne de matrice

`sofia-sm` a raison de nommer deux axes distincts : la matrice dit ce que le produit couvre, le
catalogue dit ce que l'enfant peut ouvrir. Ce depot gouverne le premier depuis le sprint 0
(`squad/README.md`, section 9, etape 6 : *"Si c'est VALIDE, Camille passe la ligne de matrice a
couvert-valide"*) et n'a jamais ecrit de regle pour le second - jusqu'a aujourd'hui, ou le silence
a laisse un enfant jouer un contenu que sa seule autorite pedagogique n'a pas encore valide.

**Regle : un jeu du catalogue reste expose tant que, et seulement tant que, la derniere fiche de
conformite de `nadia-prof` a son sujet ne porte aucune reserve nommee bloquante.** Concretement :

- `VALIDE`, ou `VALIDE SOUS RESERVE` sans reserve bloquante ouverte : le jeu reste expose. Une
  reserve non bloquante ne retire jamais un jeu du catalogue, quel que soit son nombre ou sa
  severite relative - c'est exactement ce que "non bloquante" veut dire dans le vocabulaire de
  `nadia-prof`, et je ne le redouble pas d'une seconde barre.
- Une reserve nommee **bloquante** encore ouverte, un verdict `REFUSE`, ou l'absence de tout
  audit sur l'increment servi : le jeu ne doit pas etre expose. Il en sort au meme geste que la
  decision qui l'y constate - jamais comme une action a planifier a part - sur le mecanisme deja
  etabli par `DEC-0031` (absence d'entree dans `catalogue.yaml`, sans drapeau).

**Ce que cette regle ne fait pas** : elle ne cree pas un quatrieme statut de matrice. Le statut de
matrice (`non couvert` / `en cours` / `couvert-valide` / `non couvrable en l'etat`) porte sur la
**competence**, qui peut demander plusieurs jeux a la fois (`DEC-0039`, synonymes et antonymes) ;
le Done pedagogique porte sur un **jeu**. Un jeu peut avoir son Done pedagogique acquis et son
entree de catalogue exposee alors que la competence entiere reste `en cours` faute d'un second
jeu - c'est exactement le cas de `cm1-synonymes` entre s18 et ce matin, et je ne le remets pas en
cause. Le nouvel axe ne redecoupe pas la matrice, il repond a la question distincte que `sofia-sm`
pose : *ce jeu-la, celui-ci precisement, est-il autorise a l'ecran d'un enfant en ce moment ?*

**Ce que cette regle vaut pour ce que j'ai nomme ce matin, verifie ligne par ligne avant d'ecrire
que rien ne bouge** : `SYN-12`, `ANT-8`, `AGN-1`, `AGN-2`, `AGN-3`, `ASV-1`, `ASV-2`, `ASV-3` sont
toutes explicitement **non bloquantes** dans les fiches qui les portent, sur des jeux dont le
verdict est `VALIDE SOUS RESERVE, aucune reserve bloquante`. Aucune ne retire son jeu du
catalogue. `S19-6a` (bande 576-719 px) n'est meme pas une reserve pedagogique : c'est un residu de
rendu assume par `DEC-0043`, sur des jeux dont le Done pedagogique est par ailleurs acquis a la
reference du produit - il ne declenche rien non plus par cette regle. La seule ligne que cette
regle fait bouger aujourd'hui est celle de la section 1.

### 4. Ce qui rendra le prochain cas decidable sans remonter a moi

La regle de la section 3 est generale et s'applique d'elle-meme : quiconque lit une fiche de
conformite portant une reserve nommee **BLOQUANTE** sur un jeu deja present dans
`catalogue.yaml` sait, sans arbitrage supplementaire, que l'entree doit en sortir au meme geste
que le verdict qui la constate. Je ne route pas cette verification a un seul agent : elle vaut
pour quiconque ecrit ou fusionne un verdict de `nadia-prof`, `sofia-sm` en dernier ressort a la
revue de cloture.

## Ce que je route

- **`sofia-sm`** : retirer l'entree `id: bandit-manchot-nature-mot-cp` de `catalogue.yaml` dans le
  meme geste que la fusion de cette decision, sans attendre une story ; ajouter, si elle le juge
  utile a sa ceremonie de revue, une verification systematique croisant les fiches de conformite
  portant une reserve bloquante avec les entrees vivantes de `catalogue.yaml` a chaque cloture de
  sprint - je le lui propose, je ne le lui impose pas, c'est un choix de process qui lui appartient.
- **`theo-archi`** : rien de code n'est demande ici ; je nomme, sans la trancher, la question de
  savoir si un mecanisme de statut explicite dans le schema du catalogue (plutot que l'absence
  d'entree) reduirait ce risque de trou a l'avenir - `DEC-0031` avait deliberement choisi l'absence
  d'entree pour eviter un etat intermediaire, et je ne rouvre pas ce choix seule.
- **`nadia-prof`** : reauditer "Nom ou verbe ?" une fois `NAT-5` et `NAT-9` traitees, comme
  `DEC-0044` le pose deja. Rien de nouveau ici.

## Ce que je ne fais pas

- Je ne modifie pas `catalogue.yaml` : hors de mon perimetre d'ecriture.
- Je ne fais bouger aucune ligne de `squad/programme/MATRICE-couverture.md` : la ligne "Distinguer
  les principaux types de mots" reste `en cours`, deja posee par `DEC-0044`.
- Je ne prononce aucun Done pedagogique et ne rouvre pas le verdict de `nadia-prof`.
- Je n'ajoute pas de quatrieme statut a la matrice ni de mecanisme technique de desactivation : la
  question d'un statut explicite dans le schema est nommee et routee, pas tranchee.

## Impact

- `squad/decisions/produit/DEC-0045-...md` (ce fichier) : cree.
- `squad/decisions/produit/DEC-index.md` : ligne `DEC-0045` ajoutee.
- `adapters/adapter-content/src/main/resources/catalogue.yaml` : entree
  `bandit-manchot-nature-mot-cp` a retirer, execution par `sofia-sm` dans le meme geste que la
  fusion de cette decision.
- `squad/programme/MATRICE-couverture.md` : inchangee.
- `DEC-0044` : non amendee, non contredite - le sequencement de `NAT-5`/`NAT-9` qu'elle pose reste
  la condition de retour au catalogue (section 2 ci-dessus).
