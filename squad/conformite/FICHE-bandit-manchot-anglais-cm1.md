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
- **Statut global s32** : **VALIDE SOUS RESERVE** (Done pedagogique **prononce le 2026-09-09** sur l'increment de s32, story `S32-2`, par `nadia-prof`, **instance A**, apres trois parties completes). **`LVE-CM1-07` ferme** : `en cours` -> **`couvert-valide`**, avec la **mention exigee d'avance reprise mot pour mot**. C'est la derniere ligne `en cours` de cette matiere. Quatre reserves nouvelles, toutes non bloquantes : `ANG-R3`, `ANG-R4`, `ANG-R5`, `ANG-R6`. `CONS-1` reste **levee** sur cette entree.
- **Derniere mise a jour** : 2026-09-09 (**verdict de `nadia-prof`, instance A, apres trois parties completes et la lecture des dix items de la ligne, les cinq de s28 compris**, s32).

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

---

# s32 - audit de `nadia-prof`, **instance A**, 2026-09-09 (`S32-2`, `90b01fdd`)

- **Verdict** : **VALIDE SOUS RESERVE**, prononce le **2026-09-09** par `nadia-prof`, **instance A**,
  apres avoir joue trois parties completes.
- **`LVE-CM1-07` ferme** : `en cours` -> **`couvert-valide`**, avec la **mention exigee d'avance,
  reprise mot pour mot** (ci-dessous). C'est la derniere ligne `en cours` de cette matiere.
- **Reserves nouvelles, datees du 2026-09-09, toutes non bloquantes** : `ANG-R3`, `ANG-R4`, `ANG-R5`,
  `ANG-R6`.
- **`CONS-1` reste levee sur cette entree** : la consigne servie n'a pas change et le lot ne l'a pas
  touchee. **Je ne rouvre rien.**
- **Relevé complet, avec les mesures et les comptes** :
  `squad/sprints/s32-trois-instances-et-ouverture-de-l-eps/verdicts-nadia-instance-A.md`.

## **Mention exigee d'avance, a reprendre mot pour mot dans la matrice par `camille-po`**

> *"decouverte culturelle seule, en francais ; aucune competence linguistique n'est servie"*

*C'est le texte que j'avais exige d'avance a s28 et sans lequel je n'aurais pas laisse cette ligne
bouger. Il est reproduit ici a l'identique, non reformule.*

## `SCO-1` sur ce jeu - **le score reste parfait par elimination**

*Mention due dans toute fiche que j'ecris a partir de la cloture de s32, par la reserve que j'ai posee
le 2026-09-08.*

**Mesure du 2026-09-09, trois parties completes, score lu dans la reponse de chaque appui** : le score
atteint **10 / 10 a chaque partie** en pressant les propositions dans l'ordre servi jusqu'a la bonne,
pour **19, 20 et 21 appuis**. Exemple releve tel quel, troisieme partie, manche 10 : *"field"* faux,
*"street"* faux, *"town"* juste, **score 9 -> 10**.

> **`SCO-1` est vivante sur ce jeu.** Le score qu'un enfant rapporte de cette partie **ne distingue pas
> celui qui savait de celui qui a elimine.** Cette mention se retire le jour ou `SCO-1` est verte ; elle
> **n'est pas une condition du Done** et ne remet en cause aucun verdict deja prononce - ni celui de
> `LVE-CM1-04`, ni celui que je prononce ici.

## Conditions de l'audit

**J'ai joue.** Jar reconstruit depuis le worktree `s32-nadia-a` (base `fd19d18f`), application lancee
sur un port local.

- **3 parties completes de dix manches**, jusqu'a l'ecran de fin, avec erreurs volontaires. **Les
  cinq items neufs ont ete vus servis**, ainsi que quatre des cinq items de s28.
- **Les dix items de `LVE-CM1-07` lus un par un** dans le fichier - enonce, bonne reponse,
  distracteurs, indice - **y compris les cinq de s28**, parce qu'ils comptent dans les dix qui
  ferment la ligne aujourd'hui et sont donc dans mon perimetre.
- **Audit degrade sur un point, declare** : j'ai lu le **HTML servi**, pas la page rendue par un
  navigateur. Cela ne couvre ni la couleur, ni le contraste, ni la taille, ni la position. **Aucun
  verdict de ce bloc ne repose sur un jugement visuel.** Le paquet est textuel, donc `REC-1` a
  `REC-4` restent sans objet.

## 1. Les conditions de fermeture, mesurees

| Condition | Mesure | Verdict |
|---|---|---|
| `FER-2` | **10 items** sur `LVE-CM1-07`, cinq de s28 et cinq neufs. Plancher atteint | **tenue** |
| `FER-1` | trois aspects declares par le lot - fetes, lieux, vie quotidienne - chacun servi en bonne reponse ; *vie quotidienne* n'etait servie par aucun des cinq items de s28 | **tenue sur la declaration du lot**, section 2 |
| `FER-3` | **etablie par moi, le lot ne la revendique pas** : 4 contre 6, section 3 | **tenue** |
| `BIA-1` | aucun item ne compare un ailleurs a un ici en position de norme | **tenue** |
| `BIA-2` | Royaume-Uni **4** traits, Etats-Unis **6** ; aucun pays reduit a un seul | **tenue** |
| `FAB-1a` | dix bonnes reponses distinctes | **tenue** |
| `PLA-1` (les cinq items neufs seuls, section 4) | trois items dont au moins une proposition n'est candidate a rien | **non tenue** : `ANG-R3` |
| Exactitude | les dix items, un par un | **tenue sur les dix bonnes reponses** ; une inexactitude dans un indice : `ANG-R5` |

**Ce qui a rendu la fermeture possible et qui n'existait pas avant ce lot** : *vie quotidienne*. Les
cinq items de s28 servaient deux capitales, un monument, un drapeau et une fete - **des choses a
savoir, pas une vie a se representer**. L'uniforme scolaire et le trajet vers l'ecole donnent enfin a
l'enfant quelque chose qu'il peut comparer a sa propre journee sans qu'on le lui demande. **C'est le
bon ajout, et il est celui qui manquait.**

## 2. La parenthese que le lot invoque n'est pas dans la ligne qu'il ferme - et le defaut est le mien

Le commentaire du paquet et le Javadoc du test neuf disent *"les trois elements de la parenthese du
referentiel (fetes, lieux, vie quotidienne)"*.

**`LVE-CM1-07` ne porte aucune parenthese** : *"Decouvrir quelques aspects culturels des pays de la
langue etudiee."*, point. **La parenthese est sur `LVE-CP-06`**, la meme competence au CP.

**C'est une incoherence de mon propre referentiel** : j'ai ecrit la meme competence a deux niveaux et je
n'ai mis la parenthese qu'au CP. Consequence directe : **`FER-1` est infalsifiable sur cette ligne**,
exactement le trou que `ANG-4` a comble pour `LVE-CM1-04`.

Ce que j'en fais, et ce que je n'en fais pas :

- **Je ne corrige pas le referentiel pendant que j'audite le lot qui s'y mesure.** Ajouter la parenthese
  aujourd'hui rendrait `FER-1` plus mordante sur le lot que je juge : **ce serait un poteau deplace, et
  je n'en plante pas un sur mon propre oubli.**
- **J'accepte la declaration du lot comme tenant le role de `FER-1`** : trois aspects declares, trois
  aspects servis en bonne reponse, avec un test qui les nomme. C'est le regime d'`ANG-4`, et le lot l'a
  tenu - **la chose faite est juste, c'est sa citation qui ne l'est pas.**
- **Je le route a `camille-po` via `sofia-sm`** : la meme competence doit-elle porter la meme parenthese
  aux deux niveaux ? La ligne de referentiel est a corriger hors audit, et je ne la tranche pas dans le
  document qui juge le lot.

## 3. `FER-3` - je l'ai etablie moi-meme, et je donne le partage pour qu'il soit contestable

**Le lot ne revendique pas `FER-3` sur cette ligne, et aucun test ne l'ecrit.** Elle est neanmoins l'une
de mes trois conditions de fermeture depuis le 2026-09-03, et rien n'exempte cette ligne. **Je l'ai donc
faite a la main.**

| Tache | Items | Ce que l'enfant fait |
|---|---|---|
| **Restituer un fait que la question designe par son pays** | 16 (capitale du Royaume-Uni), 18 (capitale des Etats-Unis), 21 (uniforme scolaire), 24 (trajet vers l'ecole) - **4** | retrouver en memoire un fait qu'on lui a appris, la question ne decrivant pas la reponse |
| **Reconnaitre un objet ou une fete a partir des traits qu'on en decrit** | 17 (Big Ben), 19 (drapeau), 20 (Thanksgiving), 22 (bus a imperiale), 23 (Halloween), 25 (statue de la Liberte) - **6** | apparier une description a son referent |

**Minorite a 4, au-dessus du plancher de trois. `FER-3` est tenue sur ce partage.**

**Et je nomme la limite plutot que de la laisser croire couverte** : sans declaration de tache par item,
**`FER-3` n'est calculable sur cette ligne par aucun automate et par personne d'autre que moi.** C'est le
meme manque qu'`ADR-0081` a comble pour les lignes de referentiel, et il est ici entier. Un lot futur qui
ajouterait cinq items de la seconde tache ferait tomber la premiere sous le plancher **sans qu'aucun test
ne rougisse**.

## 4. `ANG-R3` - reserve nouvelle, datee du 2026-09-09, **non bloquante** : `PLA-1` sur trois des cinq items neufs

`PLA-1`, posee le 2026-09-07 et valant *"pour les paquets ecrits a partir d'aujourd'hui"*, s'applique aux
cinq items neufs **et a eux seuls** - les cinq items de s28 sont hors de sa portee et je ne les lui
soumets pas. **Elle n'est pas tenue sur trois des cinq.**

| Item | Ce que l'enonce donne deja | Les propositions a ecarter |
|---|---|---|
| 22 - *"Quel vehicule rouge a deux etages circule dans les rues de Londres ?"* | rouge, deux etages, **dans les rues** | *"le metro souterrain"* n'est ni rouge, ni a etages, ni dans les rues - l'enonce l'exclut trois fois ; *"le tramway"* n'a pas deux etages |
| 23 - *"Quelle fete, celebree le 31 octobre aux Etats-Unis, ou les enfants se deguisent et recoltent des bonbons, est tres populaire ?"* | la date, les deguisements, les bonbons | *"Noel"* et *"le Nouvel An"* tombent sur la seule date |
| 25 - *"Quel monument, une grande statue tenant une torche, se trouve sur une ile a l'entree du port de New York ?"* | une statue, une torche, une ile, New York | *"la tour Eiffel"* et *"le Colisee"* ne sont ni des statues ni a New York |

**Ces trois items se repondent sans rien savoir du Royaume-Uni ni des Etats-Unis : ils se repondent en
lisant.** C'est le motif meme de `PLA-1` - *"un item dont l'une des propositions n'appartient a rien
redevient binaire de fait"* - sauf qu'ici il redevient **unaire**.

**Et un second defaut se cache derriere le premier** : la bonne reponse de l'item 22 est *"le bus a
imperiale"*, ou **le mot *imperiale* est au-dessus du CM1 et n'est explique nulle part** - ni dans
l'enonce, ni dans l'indice, ni ailleurs dans le paquet. Combine a la faille ci-dessus, **l'enfant gagne
l'item sans avoir appris le mot** : il l'a designe par elimination. Un enfant qui repond juste et qui ne
sait pas ce qu'il a dit n'a rien appris de cet item.

**Pourquoi ce n'est pas un refus.** `PLA-1` a produit `SIG-R2` et `MEL-R3`, deux reserves non bloquantes
sur des lignes que j'ai fermees, et **je m'y tiens plutot que de durcir parce que le defaut est plus
large ici.** Aucune bonne reponse n'est fausse, et les sept autres items de la ligne tiennent.

**Mais je dis ce que cela coute, une fois** : `FER-2` compte dix items sur cette ligne, et **trois des
cinq que ce lot a ajoutes ne sont pas des exercices.** C'est exactement ce que ma propre clause voulait
dire en ecrivant que **`FER-2` est *"necessaire et non suffisante"***.

**Et je nomme, datee du 2026-09-09 et valant pour l'avenir seulement, la clause que je n'ecris pas
aujourd'hui** : `FAB-1a` deduit deja du compte de `FER-2` les items d'une tache a bonne reponse
invariante. Deduire de la meme facon un item dont l'enonce contient sa propre reponse serait la suite
logique - **et je ne l'ecris pas maintenant, parce que je l'appliquerais au lot que je suis en train de
juger.** Si je la pose, ce sera avant un lot, jamais pendant.

> **`ANG-R3`. Propriete a tenir** : sur les items neufs de `LVE-CM1-07`, aucune proposition ne s'ecarte
> par la seule lecture de l'enonce. Soit l'enonce cesse de decrire l'objet qu'il fait nommer, soit les
> propositions deviennent des candidats reels du meme genre - un autre monument de New York, une autre
> fete americaine d'automne, un autre transport londonien. **Et sur l'item 22, le mot *imperiale* est
> explique la ou l'enfant le rencontre, ou remplace.** **Je ne fournis pas les textes.** **Regime : rejeu
> borne, 0,5.**

## 5. `ANG-R4` - reserve nouvelle, datee du 2026-09-09, **non bloquante** : l'enonce de l'item 23

*"Quelle fete, celebree le 31 octobre aux Etats-Unis, ou les enfants se deguisent et recoltent des
bonbons, est tres populaire ?"*

**21 mots, dont 14 inseres entre le sujet et le verbe** de la question principale - *"Quelle fete ... est
tres populaire ?"*. C'est l'enonce le plus lourd de la ligne, a egalite de longueur avec l'item 25, et
**le seul dont la question principale soit coupee en deux**. Un enfant de CM1 lit peu et lentement ; il
doit ici tenir le mot *fete* en memoire pendant quatorze mots avant de trouver le verbe qui l'attend.

> **`ANG-R4`. Propriete a tenir** : sur `LVE-CM1-07`, la question principale d'un enonce n'est pas coupee
> par une incise. **Le patron existe deja dans le meme paquet**, a l'item 20 : *"Quelle fete
> traditionnelle, celebree fin novembre aux Etats-Unis, reunit la famille autour d'un grand repas ?"* -
> meme structure, mais le verbe suit immediatement l'incise. **Regime : mot pour mot, 0,25.**
>
> *Le rattachement du relatif* ***ou*** *- qui se lit comme portant sur* ***Etats-Unis*** *et non sur*
> ***fete*** *- est de la grammaire : il appartient au `relecteur-pedagogique` (`DEC-0002`, amendement du
> 2026-08-04 ; `DEC-0056`) et je le lui route sans le compter dans ma reserve.*

## 6. `ANG-R5` - reserve nouvelle, datee du 2026-09-09, **non bloquante** : l'indice de l'item 16

*"Quelle est la capitale du Royaume-Uni ?"* -> *"Londres"*, indice *"C'est aussi le nom d'une grande roue
celebre au bord de la Tamise."*

**La grande roue s'appelle le *London Eye*, pas *Londres*.** L'indice affirme que la reponse *"est aussi
le nom"* de la roue ; elle ne l'est pas. Un enfant qui cherche *"une grande roue qui s'appelle comme la
reponse"* ne trouvera rien qui s'appelle *Londres*.

**Deux choses a dire honnetement.**

1. **L'item date de s28 et le lot ne l'a pas touche** - mais **il est l'un des dix que je compte pour
   fermer la ligne aujourd'hui, donc il est dans mon perimetre.** Une ligne ne ferme pas sur des items
   que je refuse de regarder parce qu'ils sont anciens.
2. **Le paquet ecrit que *"`nadia-prof` les a confirmes bons au reaudit du 2026-09-08"*, et c'est plus
   fort que ce que j'ai ecrit.** Ce que j'avais verifie, mot pour mot : *"les cinq items de `LVE-CM1-07`
   servis, quatre d'entre eux dans une seule partie ; la consigne a deux taches, exacte sur les dix
   manches."* **Servis et exacts sur les manches jouees - pas un audit signe des cinq items.** Je corrige
   la citation avant qu'elle serve de caution a autre chose.

**Non bloquante** : l'indice n'est pas une reponse attendue, et c'est le critere que j'applique depuis
`HG-R7` et depuis `ANG-R2` sur cette fiche meme.

> **`ANG-R5`. Propriete a tenir** : l'indice n'attribue pas a la bonne reponse un nom qui n'est pas le
> sien. **Regime : mot pour mot, 0,25.**

## 7. `ANG-R6` - reserve nouvelle, datee du 2026-09-09, **non bloquante** : l'item 24 et sa majorite

*"Aux Etats-Unis, dans de nombreuses regions, comment la plupart des eleves se rendent-ils a l'ecole ?"*
-> *"en bus scolaire jaune"*, contre *"a pied"* et *"en metro"*.

**Je ne refuse pas, et je dis pourquoi plutot que de le laisser deviner.** L'item affirme une majorite -
*"la plupart des eleves"* - **que je ne peux pas sourcer cette session**, n'ayant aucun outil de
recherche en ligne. La double reserve *"dans de nombreuses regions"* la rend defendable : dans les
districts ruraux et periurbains, le ramassage scolaire est bien le mode dominant. **Une affirmation
couverte par sa propre restriction n'est pas une erreur de fait, et je ne la traite pas comme telle.**
C'est le sens exact de ma famille (b) : je ne signe pas, mais je ne refuse pas ce que la formulation ne
sur-affirme pas.

**Ce qui reste, et qui est reel** : le mode qu'un enfant francais nommerait d'abord - *"en voiture, avec
un parent"* - **est absent des propositions**, alors qu'il est, dans une grande partie des Etats-Unis,
celui qui dispute la majorite au bus. **L'item ecarte donc son seul candidat serieux et retient les deux
qui ne le sont pas.** C'est la meme famille que `ANG-R3`, sur un item que la double reserve sauve du
refus.

**Et `BIA-2` n'est pas en cause** : le paquet sert six traits des Etats-Unis, aucun pays n'est reduit a
un seul trait, et je l'ai compte.

> **`ANG-R6`. Propriete a tenir** : quand un item affirme une pratique majoritaire d'un pays, l'une de
> ses propositions est **la pratique concurrente reelle**, et non deux pratiques ecartables. **Regime :
> mot pour mot, 0,25.**

## 8. Etat des lignes et des reserves de cette matiere au 2026-09-09

| Ligne | Avant | Apres |
|---|---|---|
| `LVE-CM1-04` (lire et comprendre des mots) | `couvert-valide` | **inchangee** |
| `LVE-CM1-07` (decouverte culturelle) | `en cours` | **`couvert-valide`**, avec sa mention mot pour mot |
| `LVE-CM1-05`, `LVE-CM1-06` | `non couvert` | **inchangees**, non touchees par ce lot |
| `LVE-CM1-01`, `-02`, `-03` | `non couvrable en l'etat` | **inchangees** |

**Bilan de la matiere : 7 lignes, 2 `couvert-valide`, 2 `non couvert`, 3 `non couvrable en l'etat`,
aucune `en cours`.**

| Reserve | Etat au 2026-09-09 |
|---|---|
| `ANG-R1` | **levee** (s29) |
| `ANG-R2` | **levee** (s30) |
| `ANG-R3` | **ouverte**, non bloquante, rejeu borne 0,5 |
| `ANG-R4` | **ouverte**, non bloquante, mot pour mot 0,25 |
| `ANG-R5` | **ouverte**, non bloquante, mot pour mot 0,25 |
| `ANG-R6` | **ouverte**, non bloquante, mot pour mot 0,25 |
| `CONS-1` | **levee** sur cette entree depuis le 2026-09-06, et elle le reste |

## 9. Ce dont je reste la seule garde sur cette matiere

- **`FER-3` sur `LVE-CM1-07`** : **rien ne la mord.** Le lot ne l'a pas revendiquee, aucun test ne
  l'ecrit, et sans declaration de tache par item elle n'est calculable par personne d'autre que moi
  (section 3). **C'est la garde la plus fragile de ce bloc**, parce qu'un lot futur peut la faire tomber
  sans qu'aucun test ne rougisse.
- **`PLA-1`** : la plausibilite d'une proposition pour un enfant de neuf ans ne se calcule pas.
- **L'exactitude factuelle** des dix faits culturels, indices compris : **moi seule**, et sans outil de
  recherche. `ANG-R5` est ce que cette garde a trouve ce jour-la, sur un item que deux audits
  precedents avaient laisse passer - le mien inclus.
- **`BIA-1`** : la comparaison implicite a un *ici* norme est un jugement, pas un motif. Le test compte
  les traits par pays (`BIA-2`), il ne lit pas le ton.
- **`SCO-1`** : entierement automatisable, **et rien n'est ecrit**. Voir le bloc de mention en tete.
- La couleur, le contraste, la taille, la position a l'ecran : **hors de ma portee cette session**
  (audit degrade). Personne ne les a regardees sur ce jeu ce sprint.


---

# Correction de s34 (`S34-ANG-CORR`, `76d43606`) - **VALIDE SOUS RESERVE**, `LVE-CM1-07` reste `couvert-valide`

> Bloc de validation pedagogique - **`nadia-prof` UNIQUEMENT, instance A de s34**, 2026-09-11. Il
> n'est jamais pre-rempli ni anticipe par un autre agent. **Les blocs precedents ne sont pas
> retouches.**
>
> **Le detail complet de cet audit vit dans
> `squad/sprints/s34-dernier-sprint-du-plan-2-palier-2-engage/verdicts-nadia-instance-A.md`.**

## Conditions de l'audit

**J'ai joue.** Arbre `round-two` a `a7852398`, jar reconstruit depuis un worktree dedie, lance sur un
port local, arrete par son identifiant de processus. Les **dix items de `LVE-CM1-07`** releves sur le
fichier servi, quatre d'entre eux joues a l'ecran avec leurs propositions et leur indice.

**Degrade sur un point, declare** : HTML servi, pas page rendue. **Ni la couleur, ni le contraste, ni
la taille, ni la position ne sont couverts, et aucun verdict de ce bloc n'en depend.**

**Aucun statut de matrice ne change** : la ligne etait deja `couvert-valide` depuis s32, avec sa
mention. **Le Done pedagogique prononce ici porte sur la correction**, pas sur une fermeture.

## 1. Trois reserves levees

- **`ANG-R4` - levee.** L'enonce de Halloween devient *"Quelle fete americaine, celebree le 31
  octobre, fait deguiser les enfants pour recolter des bonbons ?"* : l'apposition fait quatre mots,
  le verbe suit. **L'objet est tenu** - l'enfant n'a plus a garder *fete* en memoire pendant quatorze
  mots. Le patron que j'avais cite a ete lu comme un patron, pas recopie.
- **`ANG-R5` - levee.** L'indice de Londres devient *"C'est la plus grande ville du Royaume-Uni,
  traversee par la Tamise."* **Exact**, il ne donne plus a la capitale le nom d'une grande roue, et
  **il separe Londres de Berlin et de Madrid** : il fait ce qu'un indice doit faire. Une correction
  de lettre aurait retire la roue sans rien mettre a sa place.
- **`ANG-R6` - levee.** *"en metro"* devient *"en voiture avec un parent"* : **la pratique
  concurrente reelle**, exactement la propriete que j'avais ecrite sans la nommer.

## 2. `ANG-R3` - **levee sur deux items des trois, elle reste ouverte**

Ma reserve portait un tableau de **trois** items. Deux sont repris :

- **le bus a imperiale** : l'enonce ne decrit plus l'objet, les distracteurs deviennent *"le metro"*
  et *"le taxi noir"*, deux transports londoniens reels, **et le fait qui enseigne - rouge, deux
  niveaux - est deplace dans l'indice**, la ou l'enfant qui s'est trompe le lit. C'est un bon geste,
  et je le dis.
- **Halloween** : les distracteurs deviennent *"Thanksgiving"* et *"le Labor Day"*, deux fetes
  americaines reelles, **et la date de l'enonce est ce qui tranche**. L'item est devenu un exercice.

**Le troisieme n'a pas ete touche** : *"Quel monument, une grande statue tenant une torche, se trouve
sur une ile a l'entree du port de New York ?"* -> *"la statue de la Liberte"*, contre *"la tour
Eiffel"* et *"le Colisee"*. **Ni l'une ni l'autre n'est une statue, ni a New York : l'enonce les
exclut quatre fois**, et l'item se repond sans rien savoir des Etats-Unis. **La seconde clause n'est
pas payee non plus** : le mot *imperiale* est toujours en bonne reponse, et il n'est explique que
dans l'indice - donc seulement pour l'enfant qui s'est trompe. Celui qui trouve du premier coup
designe un mot qu'il ne comprend pas.

> **`ANG-R3`, reexprimee au format de `RES-1` le 2026-09-11, sans etre elargie ni durcie. Son
> perimetre est reduit a ce qui reste.**
>
> - **Objet** : sur les items neufs de `LVE-CM1-07`, l'enfant ne doit pas pouvoir ecarter une
>   proposition par la seule lecture de l'enonce ; et le mot qu'il designe en repondant doit lui etre
>   accessible.
> - **Mode de defaillance** : retirer la description de l'enonce de la statue et laisser *"la tour
>   Eiffel"* et *"le Colisee"* en face. Le defaut ne vient pas seulement de l'enonce bavard : il vient
>   de deux propositions candidates a rien. Il faut les deux gestes, ou le second seul - **un autre
>   monument de New York**.
> - **Perimetre, mesure sur l'etat d'arrivee** : un enonce et deux distracteurs sur l'item de la
>   statue de la Liberte ; plus le sort du mot *imperiale*, explique la ou l'enfant le rencontre ou
>   remplace. **Conditions que l'etat d'arrivee doit encore satisfaire** : `FER-1` et `FER-2` (dix
>   items sur la ligne), qu'aucun de ces gestes ne touche. **Non compte** : les cinq items de s28,
>   hors de la portee de `PLA-1`.
> - **Regime : rejeu borne, 0,25** - la moitie du prix initial, la moitie du travail ayant ete faite.

## 3. `ANG-R7` - **reserve neuve, datee du 2026-09-11, non bloquante** : un enonce dont les trois propositions sont vraies

*"Quel moyen de transport est un symbole touristique de Londres ?"* -> *"le bus a imperiale"*, contre
*"le metro"* et *"le taxi noir"*. **Les trois en sont.** Le metro de Londres et le taxi noir sont des
symboles touristiques de la ville au meme titre que le bus rouge, et l'enonce dit *"un symbole"*, pas
*"le symbole"*. **En rendant les propositions serieuses, la correction a rendu la question ambigue.**

> **`ANG-R7`.**
> - **Objet** : sur cet item, l'enfant doit pouvoir choisir sur un fait, et non sur ce qu'il suppose
>   que le jeu prefere.
> - **Mode de defaillance** : remettre la description de l'objet dans l'enonce - rouge, deux etages -
>   ce qui rouvrirait `ANG-R3` la ou elle vient d'etre payee. **Le geste est de poser dans l'enonce un
>   critere qui distingue le bus des deux autres sans le decrire** ; l'indice garde le fait qui
>   enseigne.
> - **Perimetre, mesure sur l'etat d'arrivee** : un enonce. Les trois propositions et l'indice
>   restent ; `FER-1` et `FER-2` inchangees.
> - **Regime : mot pour mot, 0,25**, voyage avec `ANG-R3` sur le meme paquet (`PAR-10`).

## 4. Ce que je verifie et qui ne fonde aucune demande

- **Exactitude des dix items** : juste. *"le Labor Day"* est bien une fete americaine ; la question
  tranche par la date, pas par la saison, et je ne lui oppose rien.
- **`COH-1`** : Thanksgiving et Halloween se servent mutuellement de distracteur, **et chacun porte
  son propre critere** - fin novembre et le repas de famille d'un cote, le 31 octobre et les
  deguisements de l'autre. **Ce n'est pas une contradiction, c'est une discrimination**, et c'est
  bien fait.
- **`BIA-2`** : aucun pays n'est reduit a un seul trait.

## 5. Etat des lignes et des reserves de cette matiere au 2026-09-11

| Ligne | Avant | Apres |
|---|---|---|
| `LVE-CM1-04` | `couvert-valide` | **inchangee** |
| `LVE-CM1-07` | `couvert-valide` | **inchangee**, mention de s32 inchangee et non reecrite |

| Reserve | Etat au 2026-09-11 |
|---|---|
| `ANG-R1`, `ANG-R2` | **levees** (s28, s32) |
| `ANG-R3` | **ouverte**, non bloquante, **perimetre reduit**, rejeu borne 0,25 |
| `ANG-R4`, `ANG-R5`, `ANG-R6` | **levees ce jour** |
| `ANG-R7` | **neuve**, non bloquante, mot pour mot 0,25 |
| `CONS-1` | **levee** sur cette entree, inchangee |

## 6. Ce dont je reste la seule garde sur cette matiere, apres s34

- **`PLA-1` sur cette ligne** - deux items repris sur trois, **et rien dans le depot ne l'a vu** :
  aucun test n'a change, le message de commit annonce la reserve payee.
- **Qu'un distracteur serieux ne soit pas une reponse vraie** (`ANG-R7`) - **neuf a partir
  d'aujourd'hui**, meme famille que `HG-R20` sur l'autre paquet de cette instance, et **contrepartie
  directe de `PLA-1`**.
- **Qu'un mot en bonne reponse soit accessible au niveau vise** (*imperiale*) - **non mordable**, et
  non paye au bout de deux sprints.
- **La couleur, le contraste, la taille, la position a l'ecran** - hors de ma portee (audit degrade).
