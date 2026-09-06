# Fiche de conformite - bandit-manchot-anglais-cm1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-anglais.yaml`.
- **Entree de catalogue** : `bandit-manchot-anglais-cm1`, titre servi *"Anglais"*,
  `nombreDeManches: 10`.
- **Statut global** : **VALIDE SOUS RESERVE** (Done pedagogique **prononce le 2026-09-04** par
  `nadia-prof`, apres avoir joue). **`LVE-CM1-04` ferme** : `non couvert` -> **`couvert-valide`**.
  **`LVE-CM1-07` s'ouvre et ne ferme pas** : `non couvert` -> **`en cours`**. Une reserve nommee,
  `ANG-R1`, non bloquante.
- **Arbitrage prealable de `nadia-prof`** :
  `ARBITRAGE-PREALABLE-s28-ce-qui-ferme-une-ligne-et-le-prix-des-quatre-lots.md`, sections 5.3 et 6 ;
  `ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md` (`REC-1` a `REC-6`, `ANG-S1`,
  `ANG-S2`, `BIA-1`, `BIA-2`).
- **Derniere mise a jour** : 2026-09-04 (**verdict de `nadia-prof` apres avoir joue**, s28).

## Competences visees

`Lire et comprendre des mots et des expressions familiers dans les champs lexicaux du cycle 3.`
(`LVE-CM1-04`) et `Decouvrir quelques aspects culturels des pays de la langue etudiee.`
(`LVE-CM1-07`) - Langues vivantes (anglais), CM1.

## **Mention exigee d'avance, a reprendre mot pour mot dans la matrice par `camille-po`**

Sur la ligne **`LVE-CM1-07`**, et **sans elle je n'aurais pas laisse cette ligne bouger** :

> *"decouverte culturelle seule, en francais ; aucune competence linguistique n'est servie"*

**Motif, inchange depuis le 2026-09-03** : une ligne verte sur cette seule base ne doit pas laisser
croire que le produit enseigne l'anglais. Les cinq items de cette ligne sont **entierement en
francais** - je les ai joues, ils portent sur des capitales, un monument, un drapeau et une fete.

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

### Conditions de l'audit

**J'ai joue.** Arbre `round-two` a `aac5d88`, jar reconstruit et lance, **deux parties completes de
dix manches par appels HTTP reels**, y compris en me trompant volontairement. **Et j'ai lu les vingt
items un par un**, distracteurs compris - une partie n'en montre que dix. **Ce n'est pas un audit
degrade.**

### 1. Le lot est **textuel**, et cela vaut ce que j'avais annonce

Aucune illustration. **`REC-1` a `REC-4` ne s'appliquent donc pas** - je le repete ici pour que
personne ne les cherche la ou elles ne sont pas. **Prix : 1,5 et non 2,0**, exactement la
composition que j'avais chiffree et la seule qui tenait dans ma borne.

### 2. `LVE-CM1-04` - `FER-1`, `FER-2`, `FER-3`, `ANG-4`

- **`ANG-4`, qui rend `FER-1` applicable** : le libelle dit *"dans les champs lexicaux du cycle 3"*
  sans les enumerer ; sans declaration, `FER-1` y serait infalsifiable. **Le paquet declare ses trois
  champs et les sert a cinq mots chacun** : la personne et la vie quotidienne (house, dog, friend,
  morning, bedroom), l'environnement geographique et urbain (street, bridge, mountain, town, field),
  l'imaginaire (witch, castle, fairy, king, ghost). **Ce sont bien les trois champs que j'avais
  nommes.** Tenue.
- **`FER-2`** : **15 items** sur cette ligne, contre un plancher de dix. Tenue.
- **`FER-3`** : deux taches, *francais -> anglais* (douze items) et *anglais -> francais* (trois
  items). La moins servie a trois, **exactement mon plancher, sans marge**. Tenue.
- **Les trois sens de traduction** : aucun *image -> anglais* (lot textuel, sans objet), douze
  *francais -> anglais*, trois *anglais -> francais*. **3 sur 20, soit 15 %**, sous mon plafond d'un
  item sur quatre, et jamais seul. Tenue.
- **`ANG-5`** : verifiee par moi sur les quinze items. **Toutes les bonnes reponses sont des noms
  communs au singulier, sans article** - aucun pluriel, aucun verbe conjugue, aucun determinant
  variable. Aucun item ne demande une forme grammaticale que le lot n'enseigne pas. Tenue.
- **`ANG-S1` et `ANG-S2`** : sans objet, aucun paquet de saisie ici. **La ligne 6 (ecrire des mots
  memorises) reste fermee jusqu'a s29 au plus tot**, par ma condition d'ordre du 2026-09-02, et non
  par un jugement sur ce lot.
- **Aucun dispositif ne demande a un adulte de prononcer un mot d'anglais.** Verifie sur les vingt
  items et sur l'entree de catalogue : aucun bloc adulte, aucun indice de prononciation. **Ma reserve
  du 2026-09-02 est tenue.**

### 3. Exactitude - les quinze traductions, verifiees une par une

house/maison, dog/chien, friend/ami, morning/matin, bedroom/chambre, street/rue, bridge/pont,
mountain/montagne, town/ville, field/champ, witch/sorciere, castle/chateau, fairy/fee, king/roi,
ghost/fantome. **Quinze sur quinze justes.**

**Les cinq faits culturels, verifies un par un** : capitale du Royaume-Uni -> Londres, **juste** ;
Big Ben, tour-horloge pres du Parlement -> **juste** au sens ou tout manuel de cycle 3 l'emploie, et
l'indice dit correctement *"une tour, avec une cloche"* ; capitale des Etats-Unis -> Washington,
**juste**, et le distracteur *New York* est **le bon distracteur**, celui de l'erreur reelle ;
drapeau a etoiles et bandes rouges et blanches -> Etats-Unis, **juste**, et l'indice donne le fait
qui l'explique (une etoile par Etat) ; Thanksgiving, fin novembre, repas de famille -> **juste**, et
l'indice ajoute le motif des recoltes.

### 4. **`ANG-R1` - la reserve : un mot transparent en bonne reponse, et c'est `REC-5`**

- **`ANG-R1` (nouvelle, datee du 2026-09-04, non bloquante)** - l'item qui demande la traduction de
  *montagne*, bonne reponse **`mountain`**, **viole `REC-5`**.

  **`REC-5`, mot pour mot** : aucun mot transparent en bonne reponse. **`mountain` et *montagne* sont
  le cas d'ecole du mot transparent** : meme racine, meme silhouette, meme debut. Un enfant qui n'a
  jamais appris un mot d'anglais repond juste par ressemblance, entre `town` et `field` qui ne
  ressemblent a rien. **L'item ne teste rien** - c'est la contrefacon que `CON-1` decrit, transposee
  au lexique.

  **Le commentaire du paquet affirme le contraire** : *"aucune paire ne se ressemble"*. **C'est faux
  sur cette paire**, et une affirmation fausse ecrite dans le depot doit etre corrigee comme le reste.

  **Un second defaut sur le meme item, qui le confirme** : `REC-6` demande trois propositions **de
  longueur voisine**. `mountain` fait huit lettres, `town` quatre, `field` cinq. **La bonne reponse
  est la plus longue des trois de trois lettres** - c'est exactement la forme que `REC-6` existe pour
  interdire.

  **Pourquoi c'est une reserve et non un refus** : la traduction est **juste**, l'enfant n'apprend
  rien de faux, et les **quatorze autres items de la ligne** suffisent tres largement au plancher de
  dix. Un item qui ne mesure pas n'est pas un item qui trompe. **Ce n'est pas comparable a un contenu
  faux, et je ne traite pas les deux de la meme facon dans le meme sprint.**

  **Correction fournie mot pour mot** (regime du mot pour mot, 0,25 a s29), qui garde le champ
  lexical a cinq mots et le triplet homogene :

  > `enonce` : *"Comment dit-on colline en anglais ?"*, **avec le mot francais entre les memes
  > guillemets que les quatorze autres items du paquet** - la forme exacte de la ponctuation
  > appartient au `relecteur-pedagogique`, pas a moi
  > `bonneReponse` : `hill`
  > `distracteurs` : `["field", "town"]`
  > `indice` : *"C'est une petite hauteur de terrain, moins haute qu'une montagne."*

  Et, dans le commentaire du paquet, remplacer *"aucune paire ne se ressemble"* par la liste reelle
  des paires jugees non transparentes, **`mountain`/`montagne` exclue**.

- **Ce que je juge et qui n'est pas dans la reserve, pour que la prochaine fois ne se rediscute
  pas** : `castle`/*chateau* et `fairy`/*fee* m'ont demande un examen. **Je les accepte tous les
  deux.** Leur parente est etymologique et non graphique : un enfant qui lit `castle` ne voit pas
  *chateau*, et `fairy` ne se devine pas depuis *fee*. **Ma barre est la ressemblance a l'oeil, pas
  la parente savante.**

### 5. `LVE-CM1-07` - ouverte, **pas fermee**, et c'est correct

**Cinq items**, contre un plancher de dix : **`FER-2` n'est pas tenue et n'avait pas a l'etre.**
J'avais ecrit d'avance qu'un lot de vingt items *"ferme la ligne 4 et ouvre la ligne 7"*. C'est
exactement ce qui est livre. **`camille-po` passe cette ligne a `en cours`, pas a
`couvert-valide`**, et la mention ci-dessus l'accompagne.

- **`BIA-1`** (aucune comparaison erigeant un pays anglophone en norme face a la France) : **tenue**.
  Aucun des cinq items ne compare quoi que ce soit a la France.
- **`BIA-2`** (aucun pays reduit a un seul trait) : **tenue**. Le Royaume-Uni est servi par sa
  capitale et un monument, les Etats-Unis par leur capitale, leur drapeau et une fete. **Deux et
  trois traits, jamais un.**

**Une observation datee du 2026-09-04, non bloquante** : le distracteur *"le drapeau de
l'Australie"* est **plus vrai qu'il n'en a l'air** - le drapeau australien porte bien des etoiles,
et son canton porte les bandes rouges et blanches de l'Union Jack. Il n'y a **pas** deux reponses
defendables (l'enonce vise clairement les bandes du drapeau entier, et l'indice tranche), mais **un
distracteur dont la plausibilite vient d'etre a moitie vrai est a surveiller** : c'est la frontiere
de ce que j'accepte.

### 6. Joue jusqu'au bout

Deux parties completes de dix manches, ecran de fin atteint. Sur erreur, l'enfant **reste sur
l'item**, recoit *"Ce n'est pas ca. Essaie encore !"* et l'indice. **Les indices ne donnent jamais le
mot anglais** : ils decrivent le mot **francais** (*"Pense a l'endroit ou on habite avec sa
famille"*), ce qui est la bonne conduite - un indice qui epellerait ou decrirait le mot anglais
donnerait la reponse.

**Un point d'experience de jeu que je signale sans en faire une reserve** : les cinq items culturels
sont melanges aux quinze items de vocabulaire dans la meme partie, sous le titre *"Anglais"* et une
consigne commune. Un enfant peut donc tirer trois questions de suite entierement en francais dans un
jeu qui s'appelle *"Anglais"*. **C'est conforme a la pratique de classe** - la decouverte culturelle
se fait en francais au cycle 3 - et je ne demande pas de separer. **Je le note parce que c'est ce
que la mention exigee sur la ligne 7 dit, du cote de la matrice, et qu'il est bon que les deux se
repondent.**

### 7. Ce dont je reste la seule garde sur cette matiere

| Propriete | Qui la tient aujourd'hui |
|---|---|
| **L'exactitude des quinze traductions et des cinq faits culturels** | **moi seule.** Aucun test ne sait qu'une traduction est juste |
| **`REC-5` - la quasi-identite** | **moi seule.** Le test garde l'**identite de graphie** ; c'est ma lecture qui a trouve `mountain`, et aucun automate ne l'aurait vu |
| **`REC-6` - la longueur voisine** | **moi**, pour le jugement de proximite ; le test garde l'appartenance au champ lexical, pas la longueur |
| **`BIA-1`** | **moi.** Le test cherche le mot *"France"*, ce qui est une garde de forme etroite : une comparaison implicite passerait |
| **`BIA-2`** | **partagee** depuis `ce3059b` : le test compte desormais les faits par pays et **echoue sur cle inconnue** si un item entre sans etre declare. **C'est la bonne forme** |

### 8. Les trois lignes `non couvrable en l'etat` et les deux non servies - inchangees

- **Lignes 1, 2, 3** (ecouter et comprendre, prendre part a une conversation, parler en continu) :
  **`non couvrable en l'etat`, confirmees sans changement.** Aucun son ; production libre jugee par
  un humain pour les lignes 2 et 3. **Nature reversible** pour la ligne 1 seulement, par une decision
  de l'utilisatrice sur le son.
- **Ligne 5** (lire et comprendre un texte court) : **refusee d'avance pour s28**, motif ecrit le
  2026-09-03. Elle s'ouvre **quand un lexique valide existe sur plus d'un paquet** - ce qui, apres ce
  Done, devient possible : **le lexique de la ligne 4 existe maintenant.** Il en faut un second.
- **Ligne 6** (ecrire des mots memorises) : **s'ouvre a s29 au plus tot**, par l'ordre qu'impose
  `ANG-S1` - un paquet de saisie ne porte que des mots deja servis en choix **dans un paquet
  valide**, et ce paquet ne l'est que d'aujourd'hui. **Ce n'est pas un jugement sur le lot.**

---

## Reverification de s29 (`S29-7`, `6b2edf1`) - **`ANG-R1` levee**, et une correction de mon propre tarif

- **Verdict** : **`ANG-R1` levee**, prononce le **2026-09-06** par `nadia-prof`, **Instance A**.
  `LVE-CM1-04` et `LVE-CM1-07` restent `couvert-valide`. **Aucune ligne de matrice ne bouge.** La
  mention exigee d'avance sur `LVE-CM1-07` reste due et inchangee.

### 1. Ce que j'ai verifie sur le contenu servi

L'item de *montagne* a disparu. A sa place :

> `enonce` : *"Comment dit-on << colline >> en anglais ?"*
> `bonneReponse` : `hill` - `distracteurs` : `field`, `town`
> `indice` : *"C'est une petite hauteur de terrain, moins haute qu'une montagne."*

- **`REC-5` tenue** : `hill` et *colline* n'ont ni racine, ni silhouette, ni debut communs. Un enfant
  qui n'a jamais appris un mot d'anglais ne peut plus repondre par ressemblance.
- **`REC-6` tenue, et mieux qu'avant** : `hill` (4), `field` (5), `town` (4) - trois longueurs
  voisines, la ou `mountain` (8) etait la plus longue des trois de trois lettres. **C'etait le second
  defaut du meme item et il est paye aussi.**
- **Exactitude** : `hill` = colline. L'indice est juste et au niveau du CM1.
- **Le champ lexical reste a cinq mots** (*street, bridge, hill, town, field*) et le triplet reste
  homogene. **`FER-2` intacte : quinze items sur `LVE-CM1-04`.**
- **L'affirmation fausse du commentaire est corrigee** : le paquet ne dit plus *"aucune paire ne se
  ressemble"* sans exception. **Une affirmation fausse ecrite dans le depot doit etre corrigee comme
  le reste** - je l'avais demande, c'est fait.

**Une position que l'equipe a prise a ma place et que je ratifie ici plutot que de la laisser en
suspens** : le commentaire retient desormais que `castle`/*chateau* et `fairy`/*fee* ont une parente
**etymologique et non graphique**, et les accepte. **Je confirme.** Un eleve de CM1 ne derive pas
`castle` de *chateau* : la ressemblance est invisible sans l'histoire de la langue. `REC-5` vise le
mot qui se devine, pas le mot qui partage un ancetre. **Ratifie, et ecrit ici pour que ce ne soit plus
a redemander.**

### 2. Une correction de mon propre tarif, que `marc-dev` a rendue visible

**Ma section 7.2 de l'arbitrage de s29 dit que les six reserves textuelles *"avaient deja leur
texte"* et les price 0,25 ensemble, au regime du mot pour mot.** **C'est inexact sur deux des six.**

| Reserve | Avais-je fourni un texte ? |
|---|---|
| `COR-R2` | **oui**, le 2026-09-05 |
| `EAU-R1` | **oui**, le 2026-09-05 |
| `HG-R4` | **oui** - deux suppressions nommees, deterministes |
| `HG-R5` | **oui**, mot pour mot |
| `ANG-R1` | **non** - j'ai nomme le defaut et cite `REC-5` et `REC-6`, je n'ai jamais ecrit `hill` |
| `VIV-R2` | **non** - je l'ai explicitement qualifie de *"piste que je nomme sans la prescrire"* |

`marc-dev` a signale la contradiction sur `VIV-R2` ; **elle vaut aussi pour `ANG-R1`, et c'est moi qui
l'etends**. Consequence, selon ma propre precision de tarif du 2026-09-05 : *"un passage de textes que
l'equipe a reformules vaut 0,5 - c'est un texte neuf, et un texte neuf se juge."* **Le choix de
`colline`/`hill` est de `marc-dev`, pas de moi : je viens de le juger ci-dessus, je ne l'ai pas
reverifie.** Le prix reel de `S29-7` est donc **0,25 (quatre reserves verbatim) + le jugement d'un
texte neuf**, et non 0,25 sec.

Le commentaire du paquet ecrit *"texte fourni dans FICHE-bandit-manchot-anglais-cm1.md, section 4,
repris mot pour mot"*. **Cette phrase est fausse** : la section 4 ne contient aucun texte de
remplacement. Elle est de la meme famille que l'affirmation *"aucune paire ne se ressemble"* que ce
meme lot vient de corriger. **A corriger dans le commentaire ; je ne modifie aucun fichier de
contenu.**

### 3. Etat des reserves de cette matiere au 2026-09-06

| Reserve | Etat |
|---|---|
| `ANG-R1` (mot transparent en bonne reponse) | **levee le 2026-09-06** |

**Aucune reserve ouverte sur cette matiere.** La **mention exigee d'avance** sur `LVE-CM1-07`
(*"decouverte culturelle seule, en francais ; aucune competence linguistique n'est servie"*) reste
due dans la matrice et n'est pas une reserve : c'est une condition de la fermeture.

### 4. Ce dont je reste la seule garde

`REC-5` - *un mot transparent en bonne reponse* - **n'est mordue par aucun test** et ne peut pas
l'etre : la transparence d'un mot pour un enfant de dix ans est un jugement. `PaquetCm1AnglaisTest`
(15 tests) garde des chaines et des comptes ; il aurait garde `mountain` aussi fidelement que `hill`,
et il l'a fait pendant un sprint.


---

# s30 - audit de `nadia-prof`, **instance A**, 2026-09-06

- **Verdicts complets du sprint** :
  `squad/sprints/s30-sciences-cm1-et-le-prix-des-reserves/verdicts-nadia-instance-A.md`.
- **Passe transverse** : `squad/conformite/PASSE-TRANSVERSE-s30-les-trois-familles-de-portee-depot.md`.
- **Arbre audite** : `round-two`, HEAD `dbbfe129`, joue depuis le worktree `s30-nadia-A`, port 8082.
- **Done technique, mesure par moi depuis mon worktree** : `bash outils/mvn-jdk21.sh -B clean verify`
  sur le reacteur complet, **BUILD SUCCESS** ; **179 rapports, 1321 tests, 0 echec, 0 erreur,
  0 ignore**, machine virtuelle rapportant **21.0.10**.
- **Audit degrade sur un seul point, et je le nomme** : j'ai joue **par requetes HTTP**, donc j'ai lu
  le HTML servi et non la page rendue. Cela ne couvre ni la couleur, ni le contraste, ni la taille,
  ni la position ; **aucun jugement de ce bloc n'y repose**.
- **Aucun verdict anterieur n'est efface par ce bloc.**

## Verdict : `CONS-1` **non levee sur ce jeu**, et `ANG-R2` ouverte - **une inexactitude de fait**

**J'ai joue** `bandit-manchot-anglais-cm1`, partie jouee volontairement mal, **et j'ai relu les vingt
items**. **Ce bloc ne rouvre aucune ligne** ; il porte sur la **consigne de catalogue** reecrite par
`S30-9`.

Consigne servie : *"Traduis le mot, ou reponds a la question de **culture anglaise**."*

### `ANG-R2` - **reserve nouvelle, datee du 2026-09-06, non bloquante** - le fait est faux

**Mesure sur les cinq items de culture de `cm1-anglais`** :

| Item | Sur quel pays il porte |
|---|---|
| *"Quelle est la capitale du Royaume-Uni ?"* -> `Londres` | Royaume-Uni |
| *"Quel monument celebre, situe a Londres, sert aussi d'horloge ?"* -> `Big Ben` | Royaume-Uni |
| *"Quelle est la capitale des Etats-Unis ?"* -> `Washington` | **Etats-Unis** |
| *"Quel drapeau porte des etoiles et des bandes rouges et blanches ?"* -> `le drapeau des Etats-Unis` | **Etats-Unis** |
| *"Quelle fete traditionnelle, celebree fin novembre aux Etats-Unis..."* -> `Thanksgiving` | **Etats-Unis** |

**Trois des cinq portent sur les Etats-Unis. Appeler *culture anglaise* la capitale des Etats-Unis, le
drapeau americain et Thanksgiving est faux.**

**Et le referentiel du produit dit lui-meme autre chose** : `LVE-CM1-07` porte sur *"quelques aspects
culturels des **pays** de la langue etudiee"*, au pluriel.

**Le paquet a raison et la consigne a tort.** Servir les Etats-Unis a cote du Royaume-Uni est
exactement ce que la ligne demande, et le paquet le fait bien. **C'est la consigne neuve qui reduit a
l'Angleterre ce que le paquet ouvre a plusieurs pays** - et elle le fait devant un enfant de CM1,
c'est-a-dire a l'age precis ou l'on apprend que l'anglais n'est pas la langue d'un seul pays. Le mot
juste est *anglophone*, ou la nomination directe de ce que l'item demande.

**Pourquoi ce n'est pas un refus, alors qu'une erreur de fait est chez moi un motif de refus a elle
seule.** **Aucune reponse attendue ne depend de cette phrase** : elle vit dans une consigne, pas dans
une question. C'est exactement la situation de `HG-R7` - le 14 juillet fete nationale des 1790 - que
j'ai traitee le 2026-09-06 en reserve non bloquante, pour ce motif precis. **Je m'applique mon propre
precedent plutot que de durcir parce que c'est le meme sprint.**

> **`ANG-R2`. Propriete a tenir** : la consigne ne restreint pas a un seul pays un contenu qui en sert
> plusieurs. **Regime : mot pour mot une fois le texte propose, 0,25.**
>
> **Elle porte son propre code et n'est pas fondue dans `CONS-1`**, parce qu'elle n'est pas de la meme
> nature : les autres entrees non levees portent un defaut de designation de tache, celle-ci porte une
> inexactitude de fait, et `camille-po` doit pouvoir la prioriser seule.

### `CONS-1` - **non levee sur cette entree**, pour un second motif distinct

**La premiere moitie de la consigne est bonne** : *"Traduis le mot"* nomme exactement la tache des
quinze items de vocabulaire, dans les deux sens (*"Comment dit-on ... en anglais ?"* et *"Que veut
dire ... en francais ?"*).

**La seconde moitie ne nomme aucune tache** : *"reponds a la question"* est ce que la forme de l'ecran
dit deja, et le complement qui la suit est en plus faux. Ce que les cinq items demandent est de
**reconnaitre un lieu, un monument, un embleme ou une fete** des pays anglophones.

> **`CONS-1`, non levee sur cette entree. Regime : rejeu borne, compte dans le reliquat (0,5 pour les
> cinq entrees restantes).** Les deux corrections se font dans le meme geste.

**Aucune ligne ne bouge**, et `ANG-R1` (l'item de *colline* / `hill`) reste levee comme le
2026-09-06 : verifie a nouveau aujourd'hui, l'item sert bien `hill` avec l'indice qui le distingue
d'une montagne.


---

# s30 - reaudit de `CONS-1` par `nadia-prof`, **instance A**, 2026-09-06 (`DEC-0080`)

- **Complement de verdicts** :
  `squad/sprints/s30-sciences-cm1-et-le-prix-des-reserves/verdicts-nadia-instance-A.md`, section
  *Complement du 2026-09-06*.
- **Arbre audite** : `round-two` a `a91070f8`, **arbre assemble**, worktree `s30-nadia-A2`, port 8082.
- **J'ai rejoue ce jeu** : partie jouee volontairement mal, **la consigne lue a l'ecran au-dessus des
  enonces reellement tires**. **Audit degrade sur le seul point visuel**, et rien ici n'en depend.
- **Aucun verdict anterieur n'est efface par ce bloc.**

## `ANG-R2` **levee**, et `CONS-1` **levee sur cette entree**

Consigne servie : *"Traduis le mot, ou reponds a la question sur les pays anglophones."* - **12
mots**.

### `ANG-R2` - **LEVEE**. L'inexactitude de fait est corrigee

*"Les pays anglophones"* est **vrai des cinq items de culture** : deux portent sur le Royaume-Uni
(Londres, Big Ben), **trois sur les Etats-Unis** (la capitale, le drapeau, Thanksgiving). Et c'est
**le mot du referentiel lui-meme** : `LVE-CM1-07` porte sur *"quelques aspects culturels des **pays**
de la langue etudiee"*, au pluriel.

**Le produit n'enseigne plus en passant que Thanksgiving et Washington relevent d'une *culture
anglaise*.** C'etait le motif de fond de la reserve, et il est paye.

### `CONS-1` - **levee**, et c'est le verdict que ma propre correction a retourne

**J'avais ecrit le 2026-09-06 un second motif distinct** : *"la seconde moitie ne nomme aucune
tache."* **Ce motif n'est pas paye** - *"reponds a la question"* reste un verbe generique - **et
j'etais partie pour le maintenir.**

**Je ne le maintiens pas, et le motif est une mesure, pas une indulgence.** J'ai verifie ma propre
classe de reference, celle que j'avais citee comme barre (*"comme le font 44 des 54 entrees"*), et
**six de ces entrees portent un verbe de reponse generique**, dont **cinq** dans exactement la forme
d'aujourd'hui - une tache reelle nommee en tete, plus un verbe generique :

| Entree que j'avais creditee | Sa consigne |
|---|---|
| `bandit-manchot-comprehension-cm1` | *"Lis le texte, puis **reponds a la question**."* |
| `bandit-manchot-texte-narratif-cp` | *"Lis le petit texte, puis **reponds a la question**."* |
| `bandit-manchot-fractions-cm1` | *"Lis bien la fraction, puis **choisis la reponse**."* |
| `bandit-manchot-problemes-etapes-cm1` | *"Lis bien, resous les deux etapes, puis **choisis la reponse**."* |
| `bandit-manchot-problemes-additifs-cp` | *"Lis bien, puis **trouve la reponse** !"* |

**Tenir cette entree a une barre plus haute que celle que j'ai appliquee a ces cinq-la serait deplacer
mon poteau sur la seule entree qui me restait sous les yeux.** Mon chiffre de 44 comptait les entrees
qui ne portaient pas une **chaine exacte**, pas celles qui nomment leur tache : c'est une correction
que je fais a ma propre barre, et elle joue contre mon inclination.

**Et le complement fait desormais un travail reel** : *"sur les pays anglophones"* **n'est pas le
titre du jeu** (*Anglais*) ; il ajoute l'information que la culture servie n'est pas celle d'un seul
pays, ce qui est precisement la ligne du referentiel. **C'est ce qui le distingue de *"d'histoire ou
de geographie"***, qui recopiait son titre et n'ajoutait rien.

**Observation, sans code et sans demande** : l'operation des cinq items de culture - reconnaitre un
lieu, un monument, un embleme, une fete - n'est toujours pas nommee. **Je ne l'ouvre pas**, pour le
motif ci-dessus.

**Aucune ligne ne bouge. `ANG-R1` reste levee.**
