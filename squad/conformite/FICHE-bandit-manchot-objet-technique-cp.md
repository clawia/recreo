# Fiche de conformite - bandit-manchot-objet-technique-cp

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-qlm-objet-technique.yaml`.
- **Entree de catalogue** : `bandit-manchot-objet-technique-cp`, titre servi *"Fonction d'un objet"*, `nombreDeManches: 10`.
- **Statut global s29** : **VALIDE SOUS RESERVE** (Done pedagogique prononce le **2026-09-06** sur l'increment de s29, story `S29-1`, commit `d6e554f`). **La ligne `QLM-CP-07` ferme** : `non couvert` -> **`couvert-valide`**. Trois reserves nommees, datees, non bloquantes : `OBJ-R1`, `OBJ-R2`, `REV-1`.
- **Arbitrage prealable de `nadia-prof`** : `ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md`, sections 4.1 et 5 ; `ARBITRAGE-PREALABLE-s28-ce-qui-ferme-une-ligne-et-le-prix-des-quatre-lots.md`, section 2 (`FER-1`, `FER-2`, `FER-3`) ; `ARBITRAGE-s27-titre-du-jeu-d-additions-et-portee-de-ma-clause-sur-la-couleur.md`, section 1 (registre des titres).
- **Instance d'audit** : Instance A (`PAR-7`).
- **Derniere mise a jour** : 2026-09-06 (premier verdict de `nadia-prof` apres avoir joue, s29).

## Competence visee

`Identifier la fonction d'un objet technique du quotidien et nommer ses parties.`
(Questionner le monde, CP, Le vivant, la matiere et les objets - `QLM-CP-07`).

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

### Conditions de l'audit

**J'ai joue.** Arbre `round-two` a `6b2edf1`, jar reconstruit et lance, **partie complete de dix
manches par appels HTTP reels**, **avec erreur volontaire sur cinq manches, dont trois poussees
jusqu'a la revelation**, ecran de fin atteint. **Et j'ai lu les onze items un par un**, distracteurs
et indices compris. **Ce n'est pas un audit degrade.**

**Done technique acquis, mesure par moi** : `bash outils/mvn-jdk21.sh -B clean verify` sur le
reacteur complet, **BUILD SUCCESS** ; **169 rapports XML, 1259 tests, 0 echec, 0 erreur, 0 ignore**,
machine virtuelle rapportant **21.0.10**.

### 1. `FER-1` - les deux moities du libelle

Le libelle **n'enumere pas** : il porte donc sur ses **moities** (precision de `FER-1` du
2026-09-05), et non sur une liste implicite d'objets.

| Moitie du libelle | Items qui la servent en bonne reponse |
|---|---|
| *identifier la fonction* | 5 items (`a se proteger de la pluie`, `a couper`, `a ecrire`, `a balayer`, `a se laver les dents`) |
| *nommer ses parties* | 6 items (`la selle`, `le guidon`, `la pedale`, `le manche`, `la toile`, `la lame`) |

**Les deux moities sont servies. `FER-1` tenue.**

### 2. `FER-2` - densite

**Onze items**, plancher a dix. **Tenue.**

### 3. `FER-3` - deux taches

*Nommer la fonction d'un objet* et *nommer une de ses parties* sont deux operations mentales
distinctes par construction : la premiere va de l'objet a son usage, la seconde decompose l'objet.
5 et 6 items, la moins servie au-dessus du plancher de trois. **Tenue.**

### 4. Exactitude - onze items sur onze

**Aucune erreur.** Selle, guidon, pedale du velo ; manche, toile, pointe du parapluie ; lame,
manche, pointe des ciseaux : les onze bonnes reponses sont justes, et le vocabulaire technique est
celui qu'un CP peut nommer.

**Un point que j'ai instruit plutot que suppose** : *"Quelle partie des ciseaux coupe le papier ?"*
-> `la lame`, distracteurs `le manche` et `la pointe`. La pointe **est** l'extremite de la lame, et
un enfant qui commence sa decoupe par la pointe peut la juger defendable. **Je retiens `la lame`** :
c'est la reponse du manuel, la pointe n'est pas une partie coordonnee au manche et a la lame mais
un detail de la lame, et l'indice tranche (*"elles se croisent pour couper"* - seules les lames se
croisent). *Je note toutefois que la premiere moitie de cet indice, "Il y en a deux", ne discrimine
rien : des ciseaux ont deux lames, deux manches et deux pointes.* Sans consequence, la seconde
moitie faisant le travail.

### 5. Lisibilite - mesuree

Les onze enonces vont de **5 a 9 mots**, mediane **7**. Aucun outlier. Tout le lexique est du
quotidien : parapluie, ciseaux, stylo, balai, brosse a dents, velo. **C'est un profil de lecture
exemplaire pour du CP.**

### 6. Traitement de l'erreur - joue

Indice a la premiere erreur, revelation avec bouton designe a la troisieme. Les indices donnent
l'usage ou la position (*"On l'utilise pour nettoyer le sol."*, *"On le tient avec les deux mains, a
l'avant."*) et jamais le mot de la reponse. **Correct.**

### 7. Reserves nouvelles, datees du 2026-09-06, non bloquantes

- **`OBJ-R1` - le titre servi echoue sur deux points de ma propre doctrine des titres.**

  Le catalogue sert **"Fonction d'un objet"**.

  1. **Le mot *fonction* n'est pas au programme de lecture du CP.** L'enfant lit ce titre sur
     l'ecran d'accueil de son niveau, avant de choisir, et sans adulte pour le lui lire il ne le
     decode pas. Les quatre autres jeux de la matiere - *Vivant ou pas vivant*, *Le corps et les
     sens*, *L'eau solide ou liquide*, *Le cycle de la vie* - sont tous lisibles a six ans.
  2. **Le titre promet plus etroit que le contenu.** Le paquet sert deux moities, la fonction **et**
     les parties ; le titre n'en annonce qu'une. C'est le defaut de *"Calcul CP"* pris a l'envers -
     j'avais refuse un titre qui promettait plus large que son contenu, par le meme motif.

  S'y ajoute que le titre est le seul du catalogue a etre nominal **sans article**, alors que le
  registre nominal avec article est celui de la maison (29 titres sur 40 releves le 2026-09-02).

  **Ce que je prescris, et je le fournis mot pour mot** (regime du mot pour mot, **0,25**, une seule
  chaine dans `catalogue.yaml`) :

  > `titre` : *"Les objets de tous les jours"*

  Nominal avec article, chaque mot lisible au CP, et il recouvre exactement le domaine du paquet -
  des objets du quotidien, leur usage et leurs parties - sans promettre davantage. Il ne concurrence
  aucun titre existant. L'orthographe et la ponctuation appartiennent au `relecteur-pedagogique`.

  **Pourquoi ce n'est pas bloquant** : le titre n'est pas faux et il ne fausse aucun apprentissage.
  Il coute a l'enfant un choix qu'il fait moins bien.

- **`OBJ-R2` - les cinq items de fonction se repondent sans connaitre l'objet.**

  Sur les cinq items de la tache *fonction*, **les deux distracteurs sont toujours les fonctions des
  autres objets du paquet** : *"A quoi sert un stylo ? - a ecrire / a couper / a balayer."* Un enfant
  qui ne saurait pas ce qu'est un stylo eliminerait quand meme les deux autres, parce qu'aucune n'a
  le moindre rapport avec l'objet nomme. **La regle de fabrication des distracteurs se repete sur les
  cinq items**, ce qui est exactement la forme que `PAR-6` decrit : cinq lectures item par item la
  laissent passer une par une, seule la lecture du paquet entier la voit.

  Consequence : la moitie *fonction* de la ligne est servie a difficulte nulle et constante. La
  moitie *parties* (6 items), elle, enseigne reellement du vocabulaire.

  **Ce que je demande, sans prescrire de texte, et le critere est verifiable sans me relire** : sur
  les cinq items de fonction, **au moins deux doivent porter un distracteur qui soit une fonction
  plausible pour l'objet nomme**, et non la fonction d'un autre objet du paquet. Je ne fournis pas
  les textes parce que le choix d'une fonction plausible mais fausse est precisement le jugement
  qu'il faut faire item par item, et que je le rejouerai. **Regime : rejeu borne, 0,5**, et je le
  dis d'avance plutot que de le decouvrir en facturant.

  **Pourquoi ce n'est pas bloquant** : les cinq items sont **justes**, ils servent bien la moitie du
  libelle que `FER-1` exige, et ils ne trompent personne. Un item trop facile n'est pas un item faux.

- **`REV-1` - la phrase de revelation, sur les cinq items de fonction.**

  Lu a l'ecran apres trois erreurs : *"C'est a couper. Appuie sur a couper."* La tournure est
  colloquiale plutot que fautive - c'est la variante **benigne** de `REV-1`, a la difference de
  `cp-qlm-cycle-de-vie` ou la meme phrase est franchement agrammaticale. **Cause de portee depot,
  traitee dans `PASSE-TRANSVERSE-s29-les-proprietes-de-portee-depot.md`, section 1.** Je la cite ici
  pour que la ligne ne soit pas crue exempte.

### 8. Ce dont je reste la seule garde sur cette ligne

`PaquetCpQlmObjetTechniqueTest` (7 tests) garde des chaines et des comptes. **Aucun test du depot ne
mord** : l'exactitude des onze faits techniques, la plausibilite des distracteurs - la propriete
meme d'`OBJ-R2` -, l'accessibilite du vocabulaire au CP, la lisibilite d'un titre de jeu par
l'enfant qui le choisit (`OBJ-R1`), `FER-1` et `FER-3`. **Sur ces proprietes, c'est moi ou
personne.**

### 9. Allocation d'identifiants faite ici (`PAR-5`)

Le prefixe `OBJ` est **alloue par ce document** au paquet `cp-qlm-objet-technique` ; il n'existait
nulle part dans le depot avant le 2026-09-06 (verifie par extraction sur `squad/`, `docs/`,
`*.java`, `*.yaml`).


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

## Verdict : **VALIDE** - `OBJ-R1` et `OBJ-R2` levees

**J'ai joue** `bandit-manchot-objet-technique-cp`, partie jouee volontairement mal jusqu'aux
revelations, **et j'ai relu les onze items**.

### `OBJ-R1` - **LEVEE**

Titre servi : **"Les objets de tous les jours"**. **C'est mon texte, mot pour mot.**

Nominal avec article, chaque mot lisible au CP, et il recouvre exactement le domaine du paquet - la
fonction **et** les parties - sans promettre davantage.

**Une correction que je fais a mon propre motif, avant que quiconque s'en serve.** J'avais ecrit en
troisieme argument : *"le titre est le seul du catalogue a etre nominal sans article"*. **C'est
faux.** Verifie titre par titre sur les 58 entrees d'aujourd'hui, ils sont **six** dans ce
registre : *Petits problemes*, *Histoire et geographie*, *Anglais*, *Droits et devoirs*, *Signal et
information*, *Melanges et separations* - et les quatre premiers existaient deja quand je l'ai ecrit.

**Ce que cela change et ce que cela ne change pas** : mes deux autres motifs tenaient seuls -
*fonction* n'est pas au programme de lecture du CP, et le titre promettait plus etroit que le
contenu -, et le titre livre repare les deux. **La levee tient.** Mais **je n'ouvre aucune reserve
sur les six titres nominaux sans article** : un sous-registre de six entrees est un registre, pas une
derive. **Une barre que j'ai posee sur un chiffre faux, je la retire, je ne la rehabille pas.**

### `OBJ-R2` - **LEVEE**, exactement au plancher que j'avais fixe

**Ma barre, ecrite le 2026-09-06** : *"sur les cinq items de fonction, **au moins deux** doivent
porter un distracteur qui soit une fonction plausible pour l'objet nomme, et non la fonction d'un
autre objet du paquet."*

**Mesure sur les cinq items livres :**

| Item | Distracteurs | Plausible pour l'objet nomme ? |
|---|---|---|
| parapluie | *a couper*, *a ecrire* | non |
| **ciseaux** | ***a coudre***, *a balayer* | **oui** - les ciseaux appartiennent au necessaire de couture ; et *coudre* n'est la fonction d'aucun autre objet du paquet, ce que ma barre exigeait aussi |
| stylo | *a couper*, *a balayer* | non |
| balai | *a ecrire*, *a se proteger de la pluie* | non |
| **brosse a dents** | ***a se laver les cheveux***, *a ecrire* | **oui** - une brosse qui nettoie, qui ne differe que par ce qu'elle nettoie ; le plus net des deux |

**Deux sur cinq. Ma barre disait *au moins deux*. Elle est atteinte, et je la tiens telle que je l'ai
ecrite.** L'equipe a choisi le minimum que j'autorisais : c'etait son droit, et **je ne releve pas une
barre parce que je vois aujourd'hui qu'elle aurait pu etre plus haute.** Une barre relevee apres coup
fait de ma porte un caprice.

**Ce que je dis quand meme, et qui n'est pas une reserve** : les trois autres items de fonction
restent gagnables sans connaitre l'objet nomme. Si `camille-po` veut un jour les renforcer, c'est un
**enrichissement, pas une dette**, et je ne le compte nulle part.

### `REV-1` - **levee sur cet ecran**, par `REV-3`

La variante benigne que j'avais relevee ici (*"C'est a couper. Appuie sur a couper."*) n'existe plus :
la revelation donne *"Appuie sur la bonne reponse :"* puis la reponse seule dans son element.

**`QLM-CP-07` reste `couvert-valide`, aucune ligne ne bouge, aucune reserve neuve sur ce paquet.**
