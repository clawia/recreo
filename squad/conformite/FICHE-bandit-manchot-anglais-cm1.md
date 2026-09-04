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
