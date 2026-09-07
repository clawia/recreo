# Verdicts de `nadia-prof` - s31, instance B : les quatre paquets de sciences CM1

- **Auteur** : `nadia-prof`, professeure des ecoles, **seule autorite sur le Done pedagogique**.
- **Date** : 2026-09-07.
- **Arbre audite** : `round-two` a `3181b5e6`, porte technique verte (1385 tests, 0 echec, confirmee
  independamment par `yanis-qa` et `theo-archi`). Worktree dedie `s31-nadia-B`, **port 8081**,
  scratchpad propre a cette instance (amendement de `squad/README.md` ecrit a la retro de s30).
- **Perimetre** : les quatre paquets neufs de sciences CM1 de `S31-3` et `S31-4`, **et rien
  d'autre**. L'EMC CP, les deux reprises `MEL-R1/R2/R3` et `OBT-R1/R2` et le paquet
  `cm1-sciences-melanges` sont du perimetre de l'instance A ; **je n'y touche pas, y compris sur la
  couture, dont je ne juge que mon cote.**
- **Ce document ne modifie ni la matrice, ni le backlog, ni aucun contenu.** Il prononce quatre
  verdicts et les route.

---

## 0. Comment j'ai audite

**J'ai joue les quatre paquets, du premier ecran a l'ecran de fin.** Jar reconstruit depuis mon
propre worktree sur le JDK de la toolchain (21.0.10, version rapportee par la machine virtuelle et
non par l'entree de toolchain - `ADR-0071`), application lancee sur le port 8081. **Quatre parties
completes de dix manches, une erreur volontaire sur chaque manche, poussee jusqu'a trois erreurs
partout ou la revelation etait atteignable**, ecran de fin atteint quatre fois. **Et j'ai lu les
quarante-quatre items**, y compris les quatre que le tirage a laisses de cote.

**Audit degrade sur un point, et je le declare** : j'ai joue par requetes HTTP reelles, donc j'ai lu
le **HTML servi**, pas la page rendue par un navigateur. Cela couvre les textes, l'ordre des ecrans,
l'enchainement apres une erreur et apres trois, et le tirage. Cela **ne** couvre ni la couleur, ni le
contraste, ni la taille, ni la position. **Aucun des quatre verdicts ne repose sur un jugement
visuel, et en particulier aucun des deux refus.**

---

## 1. Les quatre verdicts

| Ligne | Paquet | Verdict | En un motif |
|---|---|---|---|
| **`SCI-CM1-01`** - etats et changements d'etat | `cm1-sciences-etats-matiere` | **VALIDE** | Onze items exacts, et **le seul des quatre paquets ou chaque proposition est un candidat serieux**. `SCI-1` y est tenue a son point le plus exigeant |
| **`SCI-CM1-07`** - besoins et regimes alimentaires | `cm1-sciences-regimes-alimentaires` | **VALIDE SOUS RESERVE** | La tache A (sept animaux, trois regimes) est excellente et porte `FER-1` a elle seule. **Sur les quatre items de la tache B, la bonne reponse est la seule proposition serieuse** - `RGA-R1`, `RGA-R2`, non bloquantes |
| **`SCI-CM1-11`** - familles de materiaux et proprietes | `cm1-sciences-familles-materiaux` | **REFUSE** | **Sur les quatre proprietes que `FER-1` oblige a servir, une seule est servie par un item qui teste ce qu'il annonce.** Trois items sur six n'offrent aucune alternative serieuse. Plus un indice qui ne veut rien dire et une sixieme famille jamais enseignee |
| **`SCI-CM1-08`** - etapes du developpement | `cm1-sciences-etapes-developpement` | **REFUSE** | **Le paquet enseigne un fait faux** (*"un jeune arbre a fini de grandir"*) **et se dement lui-meme** : l'item 8 range en reponse fausse ce que l'item 3 affirme. Second motif : les items 2 et 6 sont un seul item sous deux noms d'animal |

**Les lignes qui ferment : deux.** `SCI-CM1-01` et `SCI-CM1-07`, toutes deux **sans mention de
couverture partielle**.

**Le motif de chaque verdict est ecrit dans la fiche du jeu**, ouverte pour l'occasion - les quatre
paquets sont neufs et n'en avaient pas :

- `squad/conformite/FICHE-bandit-manchot-etats-matiere-cm1.md`
- `squad/conformite/FICHE-bandit-manchot-regimes-alimentaires-cm1.md`
- `squad/conformite/FICHE-bandit-manchot-familles-materiaux-cm1.md`
- `squad/conformite/FICHE-bandit-manchot-etapes-developpement-cm1.md`

**Mon attente d'avant sprint etait de 3 a 4 lignes sur 4 en sciences** (arbitrage prealable, section
7.4). **Le resultat est 2.** Je m'etais trompee dans le sens de la sous-estimation deux sprints de
suite ; cette fois je me suis trompee dans l'autre sens, et le motif est nommable : les trois
conditions de fermeture `FER-1`, `FER-2` et `FER-3` etaient tenues sur les **quatre** paquets, et
les deux refus portent tous deux sur des proprietes qu'aucune de ces trois conditions ne couvre.

---

## 2. Les quatre points que le lot revendiquait - verifies, pas crus

### 2.1 `SCI-CM1-01` referme la couture ouverte par `MEL-R1` : **confirme, de mon cote**

L'item 8 porte bien *"la disparition"* en distracteur, et son indice - *"L'eau n'a pas disparu :
elle est devenue un gaz invisible, présent dans l'air."* - **s'affiche a l'ecran des la premiere
erreur** ; je l'ai declenche en repondant volontairement *"la disparition"*. J'ai lu le paquet
`melanges` tel qu'il est servi apres `S31-5` : son indice du sel s'arrete a *"Il reste caché dans
l'eau tant que celle-ci garde sa forme liquide."*, et c'est bien la question laissee ouverte que ce
paquet-ci referme. **Aucun item de `cm1-sciences-etats-matiere` n'ecrit que l'eau disparait.**

**Les deux paquets ne se contredisent sur aucun mot et peuvent vivre cote a cote.** Une nuance de
vocabulaire que j'ai regardee et qui n'en est pas une contradiction : `melanges` dit *"laisser l'eau
s'évaporer"* et *"Les grains ont disparu à l'œil"*, ce paquet-ci dit *"la vaporisation"* et refuse
*"la disparition"*. L'evaporation **est** une vaporisation, et *disparu a l'oeil* n'est pas
*disparu*. **Je ne juge pas le paquet `melanges` lui-meme** : il est a l'instance A.

*Consequence de gouvernance* : la condition que j'avais posee a s30 sur
`FICHE-bandit-manchot-melanges-cm1.md` - *"`CYC-1` vers `SCI-CM1-01`, non encore servie, elle mordra
le jour ou les etats de la matiere seront servis"* - **a morde aujourd'hui et elle est tenue.**

### 2.2 `SCI-CM1-07` sert *herbivore, carnivore, omnivore* et jamais *vegetarien* : **confirme**

Verifie sur les onze items, en bonne reponse, en distracteur, dans les indices et dans les
commentaires : **le mot *vegetarien* n'apparait nulle part.** Le libelle du descripteur est copie
conforme du referentiel corrige. **Sept animaux distincts en tache A**, denombres : lapin, lion,
sanglier, vache, loup, ours brun, chevre.

**Ce point compte plus que sa taille.** Le libelle de la matrice a porte le mauvais mot jusqu'a
s30 : un paquet ecrit depuis le document de pilotage aurait enseigne qu'un lapin est *vegetarien*,
ce qui est faux. **Le paquet a suivi le referentiel et non le document de pilotage, et c'est le bon
ordre.**

### 2.3 `SCI-CM1-08` ne demande jamais une sequence entiere : **confirme, et aucune mention partielle n'est due**

Verifie sur les six items de la tache A : chacun compare **deux termes**. Les cinq etapes sont
couvertes.

**Et je tranche d'avance la question que le verbe du libelle - *ordonner* - va faire poser.** Ma note
du 2026-09-03 sur cette ligne dit mot pour mot : *"Ordonner deux etapes se pose en texte, sans frise
ni manipulation"*. Ma table du 2026-09-06 n'exigeait de mention de couverture partielle que sur les
lignes **6** (classement du vivant), **14** (environnement numerique) et **20** (document
scientifique). **La ligne 8 n'en fait pas partie, et je n'en ajoute pas une apres coup.** C'est la
barre que j'ai ecrite ; l'equipe l'a atteinte ; je ne la releve pas.

**`CYC-1` contre `cp-qlm-cycle-de-vie` : verifie moi-meme et exact.** Le CP enseigne *naitre, se
nourrir, grandir, avoir des petits, mourir* sans nommer le vieillissement ; le CM1 dit *croissance*
et *reproduction* dans le meme sens et ajoute le vieillissement. **Progression, aucune
contradiction.** La declaration de la developpeuse tient.

**Le refus de cette ligne ne porte sur aucun de ces deux points**, et je l'ecris pour qu'on ne
reprenne pas ce qui est juste.

### 2.4 Le precedent *"un moyen de separation"* : **je le confirme, et il ne sauve pas `SCI-CM1-11`**

**J'ai relu mes propres mots avant de m'en servir**, parce que j'ai deja trouve inexact un constat
que j'avais moi-meme enregistre. `FICHE-bandit-manchot-melanges-cm1.md` credite l'element `FER-1`
*"un moyen de separation"* par des items dont les bonnes reponses sont *filtre, evaporation, aimant,
decantation, passoire*. **C'est bien le critere teste qui vaut credit, jamais le mot du libelle
recopie dans la reponse.** Le precedent est reel, il est de moi, et **il transpose ici encore plus
directement** : le verbe du libelle de `SCI-CM1-11` est *choisir un materiau* d'apres une propriete,
donc la bonne reponse **doit** etre un materiau.

**Mais confirmer cette lecture est ce qui condamne le paquet, et non ce qui le sauve.** Si c'est le
critere teste qui vaut credit, il faut que l'item **le teste**. Sur les quatre proprietes de la
parenthese, une seule - l'isolation, item 7 - est portee par un item dont les distracteurs sont des
reponses qu'un enfant donne vraiment. Les trois autres sont portees par des items ou l'enfant trouve
la reponse en ecartant l'absurde. **`FER-1` est tenue a la lettre et son objet n'est pas atteint** -
le meme mode de defaillance que celui de `SCI-4` que j'ai nomme contre moi-meme il y a deux jours.

---

## 3. Ce que rien n'a garde, et ce que je confirme des garde-fous neufs

### 3.1 La famille `OBJ-R2` / `FAB-1b` **ne mord sur aucun des quatre paquets, et c'est un fait, pas un oubli**

`theo-archi` a mesure cette famille non encodable, et j'etais donc le seul garde. **Je l'ai
cherchee, et je ne l'ai pas trouvee.** Sur les quatre paquets, les distracteurs viennent d'ensembles
**fermes** - les trois etats, les quatre changements, les trois regimes, les cinq etapes, les
familles de materiaux - ce que l'exception de `FAB-1` autorise nommement, et **cela n'ouvre aucune
elimination** : pour ecarter *liquide* et *gaz*, il faut savoir ce qu'est un solide.

**Je le dis explicitement parce que le contraire serait plus facile a ecrire.** Le defaut qui a
produit mon refus de `SCI-CM1-12` n'est pas reproduit ici. **Ce qui a mordu, c'est `PLA-1`** - une
clause voisine mais distincte : le probleme de `familles-materiaux` n'est pas que ses distracteurs
soient les bonnes reponses des autres items, c'est qu'ils **n'appartiennent a rien**.

### 3.2 Les deux garde-fous d'`ADR-0086` : ils ont mordu ailleurs, et je mesure ici ce qu'ils ne voient pas

**Ils sont verts sur les quatre paquets, et sur trois d'entre eux ils ont raison** : je confirme a la
lecture qu'il n'y a ni item clone ni amorce contrefaite sur `etats-matiere`,
`regimes-alimentaires` et `familles-materiaux`.

**Sur `etapes-developpement`, la clause 1 est verte et le defaut qu'elle vise est present.** J'ai lu
son code avant de l'accuser. Elle ne signale un groupe d'items coules dans le meme moule de
propositions que s'il compte **au moins trois items** et si leurs bonnes reponses tiennent dans un
moule d'**au moins trois mots** (`ITEMS_POUR_UNE_REPETITION = 3`, `MOTS_POUR_UNE_PHRASE = 3`, dans
`LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest`). **La paire des items 2 et 6 passe sous
les deux seuils a la fois** : deux items, et une bonne reponse - *"la reproduction"* - de deux mots.

**Le garde-fou a raison au regard de son propre critere ; c'est son critere qui est plus etroit que
la famille.** `ADR-0032` ne la voit pas non plus : son identite est le couple enonce + jeu de
propositions, et les deux enonces different par le nom de l'animal. **Route a `theo-archi` avec les
deux chiffres. Je ne prescris ni un seuil, ni une forme.**

### 3.3 `SCI-4` verifiee structurellement : **je confirme par mon propre comptage**

44 items neufs, **3 propositions chacun**, aucun a deux et aucun a quatre. Compte par extraction sur
les quatre fichiers de contenu, pas repris de la declaration. **`BIN-1` est donc sans objet sur les
quatre paquets**, et je l'ecris parce que c'etait la clause du 2026-09-07 la plus susceptible de
mordre sur cette matiere.

### 3.4 Les proprietes dont je reste la seule garde, apres ce sprint

| Propriete | Etat |
|---|---|
| **L'exactitude factuelle**, y compris dans une **premisse d'enonce** | **aucun garde-fou.** C'est le premier motif du refus de `SCI-CM1-08`, et il a traverse une CI de 1385 tests verts |
| **`PLA-1`** - une proposition est un candidat, jamais un remplissage | **aucun garde-fou**, non encodable (`ADR-0086`). C'est le motif du refus de `SCI-CM1-11` et des deux reserves de `SCI-CM1-07` |
| **`SCI-1`** - le retour dit pourquoi la reponse est fausse | **aucun garde-fou.** Un indice qui ne veut rien dire est passe (`familles-materiaux`, item 10) |
| **La coherence interne d'un paquet** - une proposition rangee en distracteur n'est pas affirmee ailleurs dans le meme paquet | **aucun garde-fou.** Trouve sur `etapes-developpement`. **Route a `theo-archi`** |
| **Un item clone en paire**, bonne reponse de moins de trois mots | **sous les deux seuils de la clause 1 d'`ADR-0086`.** Mesure ici. **Route a `theo-archi`** |
| **La coherence entre les familles enseignees et les familles servies en reponse** | **aucun garde-fou** (le caoutchouc de `familles-materiaux`). **Route a `theo-archi`** |
| **`CYC-1`** - recouvrement CP / CM1 | **aucun garde-fou.** Verifie a la main sur `cp-qlm-etats-eau` et `cp-qlm-cycle-de-vie` |

---

## 4. Les reserves neuves et leur regime

**Deux, toutes deux sur `cm1-sciences-regimes-alimentaires`, toutes deux non bloquantes.** Allouees
dans `squad/decisions/REGISTRE-identifiants.md` dans le meme commit que cette fiche (`PAR-5`,
`ADR-0085`).

| Reserve | Objet | Regime |
|---|---|---|
| **`RGA-R1`** | Sur les quatre items de la tache B, la bonne reponse est la seule proposition serieuse (`PLA-1`). Releve item par item dans la fiche | **rejeu borne, 0,5 pour le paquet entier** (`PAR-10`). Les deux reserves voyagent ensemble : elles se corrigent dans les memes quatre items |
| **`RGA-R2`** | Deux formulations hors du registre de CM1 dans la tache B : *"pour l'esthétique"*, et une discrimination qui tient au seul adverbe *seulement* | idem, **sans un centime de plus** |

**Aucune reserve n'est ouverte sur les deux paquets refuses** : un increment refuse n'ouvre pas de
reserve, ses motifs sont des motifs et `camille-po` en fait du backlog.

**Aucune reserve anterieure n'est levee ni durcie par ce document.** `SIG-R2` et `MEL-R3` restent ce
qu'elles etaient, sur des lignes que j'ai fermees et que je ne rouvre pas.

**Une correction de lecture, sur ma propre clause, dite plutot que tue.** La premiere phrase de
`FAB-1a` (*"deux items ne portent pas la meme bonne reponse"*) est **impossible a tenir** sur un
classement a categories fermees, et ma propre note d'exception le dit deja (*"c'est licite des lors
que la bonne reponse varie"*). **C'est l'exception qui porte le sens, et c'est elle que j'applique**
sur les quatre paquets. Je le corrige ici avant de m'en servir, **sans durcir ni relacher la barre**.

---

## 5. Mon cout reel, poste par poste, sur 4,0

| Poste | Prix | Consomme |
|---|---|---|
| **Lot 1 de sciences CM1** (`S31-3`) - `SCI-CM1-01` et `SCI-CM1-07`, joues, 22 items lus, deux fiches ouvertes, deux verdicts | **1,5** | **1,5** |
| **Lot 2 de sciences CM1** (`S31-4`) - `SCI-CM1-11` et `SCI-CM1-08`, joues, 22 items lus, deux fiches ouvertes, deux refus motives | **1,5** | **1,5** |
| **Marge de rejeu `PAR-11`** - 0,5 par lot neuf audite, par instance | **1,0** | **0,0** |
| **Total** | **4,0** | **3,0** |

**Je ne tire pas sur ma marge, et je le dis maintenant plutot qu'a la cloture** - c'est ce que j'ai
fait a s30, et c'est ce que trois depassements consecutifs (s28 8,25 ; s29 9,50 ; s30 8,50) rendent
non negociable.

**Pourquoi la marge est intacte, et ce que cela n'annonce pas.** `PAR-11` couvre le **reaudit d'un
paquet** a l'interieur du sprint. **Aucun reaudit n'a eu lieu de mon cote** : les deux refus
partent au backlog de `camille-po`, et leur reaudit se paiera au sprint ou la reprise est faite, a
**0,5 par paquet** (`PAR-10`). **Si `lea-dev` reprend les deux paquets dans s31, mon reaudit tient
exactement dans ma marge** - 0,5 + 0,5 = 1,0 - et pas un centime au-dela. Un troisieme paquet a
rejouer sortirait de ma borne, et il faudrait alors le dire avant, pas apres.

**Ce que ces 3,0 achetent** : **deux lignes fermees**, quatre paquets joues du premier ecran a
l'ecran de fin, quarante-quatre items lus, quatre fiches ouvertes, deux refus motives item par item
avec la liste exacte de ce qu'il faut corriger, et **trois proprietes mesurees et routees a
`theo-archi`** dont l'une est un trou chiffre dans un garde-fou de trois jours.

---

## 6. Ce que je route

| Suite | Destinataire | Ce que j'attends |
|---|---|---|
| **`SCI-CM1-01` : `non couvert` -> `couvert-valide`**, sans mention de couverture partielle, sans reserve | `camille-po` | Le compte de la matrice lui appartient. **Je ne la touche pas** |
| **`SCI-CM1-07` : `non couvert` -> `couvert-valide`**, sans mention de couverture partielle, avec `RGA-R1` et `RGA-R2` ouvertes | `camille-po` | idem |
| **`SCI-CM1-11` et `SCI-CM1-08` restent `non couvert`** | `camille-po` | Les deux refus ne bloquent pas le sprint suivant. Chacune des deux fiches porte une section *"Ce qu'il faut pour que je ferme cette ligne, et rien d'autre"*, **actionnable sans me redemander ce que je voulais dire** |
| **`RGA-R1` et `RGA-R2`** | `camille-po`, via `sofia-sm` | Une story. **Rejeu borne, 0,5 pour le paquet entier** (`PAR-10`) |
| **La coherence interne d'un paquet** (un distracteur affirme comme vrai ailleurs dans le meme paquet) | `theo-archi` | Propriete de paires d'items. **Je dis la propriete, jamais la forme** |
| **Les deux seuils de la clause 1 d'`ADR-0086`** laissent passer une paire clonee a bonne reponse courte - mesure sur `etapes-developpement`, items 2 et 6 | `theo-archi` | Les deux chiffres sont dans la fiche. Il decide si les elargir coute un faux rouge ; **ce n'est pas ma decision** |
| **La coherence entre les familles enseignees et les familles servies en reponse** (le caoutchouc) | `theo-archi` | Propriete d'ensemble sur le YAML, donc potentiellement mordable |
| **L'absence du mot *vegetarien* dans le contenu servi** | `theo-archi` | C'est une chaine, donc la seule des proprietes de ce sprint qui soit trivialement mordable, et elle protege un mot que le depot a deja porte faux pendant deux sprints |
| **L'item 8 de `cm1-sciences-etats-matiere` comme modele de fabrication** | `camille-po`, via `sofia-sm` | A citer dans les stories de reprise, pour que la correction des distracteurs ne se cherche pas une forme |
