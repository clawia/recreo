# Fiche de conformite - bandit-manchot-regles-de-vie-cp

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-emc-regles-de-vie.yaml`.
- **Entree de catalogue** : `bandit-manchot-regles-de-vie-cp`, titre servi *"Les regles de l'ecole"*,
  consigne servie *"Lis bien la question, puis choisis la bonne reponse."*, `nombreDeManches: 10`.
- **Statut global** : **VALIDE SOUS RESERVE** (Done pedagogique **prononce le 2026-09-05** par
  `nadia-prof`, apres avoir joue). **`EMC-CP-06` ferme** : `non couvert` -> **`couvert-valide`**,
  **a la condition que la mention de couverture partielle ci-dessous soit ecrite dans la matrice**.
  Une reserve nommee, `REG-R1`, non bloquante.
- **Arbitrage prealable de `nadia-prof`** :
  `ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md`,
  sections 4.3 et 5, et
  `squad/programme/enseignement-moral-et-civique/competences-cp.md` (conditions `EMC-1` a `EMC-7`,
  posees le 2026-09-03).
- **Commit audite** : `5526932` (`[LEA] s29: S29-4`).
- **Derniere mise a jour** : 2026-09-05 (**verdict de `nadia-prof` apres avoir joue**, s29,
  instance B de la parallelisation `PAR-0` a `PAR-8`).

## Competence visee

`Connaitre les regles de vie de la classe et de l'ecole et dire a quoi elles servent.`
(`EMC-CP-06`, enseignement moral et civique, CP, *Acquerir et partager les valeurs de la
Republique*).

## **Mention exigee d'avance, a reprendre mot pour mot dans la matrice par `camille-po`**

> *"connaissance de la regle et de ce a quoi elle sert seule ; sa mise en oeuvre en situation n'est
> pas servie. Ce produit verifie ce qu'un enfant sait, jamais ce qu'il fait."*

**Sans cette mention ecrite dans la matrice, je ne ferme pas la ligne.**

**Et je dois dire d'ou elle sort, parce que deux textes de moi ne disaient pas la meme chose.**
Mon referentiel du 2026-09-03 range les lignes 5 et 6 parmi les **moities** et exige cette mention
si elles bougent. Mon arbitrage prealable du 2026-09-05, section 4.3, traite la ligne 6 comme une
ligne entiere et ne nomme la mention que pour la ligne 5. **C'est mon arbitrage du 2026-09-05 qui
avait oublie la clause, pas mon referentiel qui l'avait durcie** : la clause est anterieure au lot,
elle est generale a la matiere - *"aucune ligne d'EMC ne doit etre presentee comme attestant une
conduite"* - et laisser tomber une barre que j'ai ecrite avant le lot serait le symetrique exact du
deplacement de poteaux que je m'interdis. **Elle est donc due, et elle ne coute rien a l'equipe :
c'est une ligne de matrice.**

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

### Conditions de l'audit

**J'ai joue.** Arbre `round-two` a `ee3ef70`, reacteur reconstruit par `mvn -B verify`
(**BUILD SUCCESS, 1245 tests, 0 echec, 0 erreur, 169 rapports XML**), jar relance, **partie
complete de dix manches par appels HTTP reels**, plus un chemin d'erreur repete sur le paquet
voisin pour voir ce que l'enfant recoit apres une, trois puis six fautes.

**Ce que ma mesure couvre exactement, et je le declare plutot que de laisser croire.** Pendant ma
construction, `lea-dev` a depose dans ce worktree les fichiers non commites de `S29-1` (deux
paquets de questionner le monde CP, deux classes de test, une modification du catalogue et de deux
garde-fous). **Mon chiffre de 1245 tests porte donc sur un arbre qui porte `ee3ef70` plus ce
travail-la, pas sur `ee3ef70` seul.** Aucun des cinq fichiers de mon perimetre n'a bouge - ils ne
figurent pas dans les modifications de l'arbre - et les jeux que j'ai joues sont bien ceux des
commits audites, consigne et titre compris. **Le rouge eventuel sur `cp-qlm-*` n'est pas mon
perimetre et je ne l'ai pas chasse.**

**Et j'ai lu les onze items**, pas seulement les dix tires. Une partie de dix manches sur un paquet
de onze en laisse toujours un jamais vu, et c'est ce qui m'avait fait manquer un item a s27. Celui
que le tirage a laisse de cote ici est l'item 11 (*"Aya a emprunte des crayons de la classe"*).
**Ce n'est pas un audit degrade.**

### 1. La competence est bien celle que la ligne annonce

`lignesDeReferentiel: ["EMC-CP-06"]`, une seule ligne. Le libelle du descripteur est **copie
conforme** a celui du referentiel, au mot pres. Verifie.

**Et l'increment travaille bien cette competence-la, pas une competence voisine plus facile.**
C'est le point que je regarde en premier sur cette ligne, parce que je l'avais nomme d'avance :
*"attention a ne pas se contenter d'items qui n'enoncent que la regle : la moitie 'a quoi elle
sert' est ce qui distingue cette ligne d'un reglement recite"*. **Cinq items sur onze portent
exactement cette moitie**, et ils ne sont pas decoratifs : la bonne reponse y est la **raison
d'etre** de la regle, jamais la regle elle-meme.

### 2. `FER-1` - les deux moities du libelle, chacune en position de bonne reponse

| Moitie du libelle | Items | En position de bonne reponse ? |
|---|---|---|
| *connaitre les regles de vie* | 1, 3, 5, 7, 9, 11 | oui - *lever la main*, *le ranger*, *en marchant*, *attendre son tour*, *faire la queue*, *les remettre a leur place* |
| *dire a quoi elles servent* | 2, 4, 6, 8, 10 | oui - *pour que chacun parle a son tour*, *pour que chacun retrouve ses affaires*, *pour que personne ne se blesse*, *pour comprendre ce qu'il dit*, *pour que tout le monde passe sans se bousculer* |

**Tenue.** Le libelle n'enumere pas dans une parenthese : `FER-1` porte donc sur ses moities, selon
ma propre precision du 2026-09-05, et non sur une liste implicite de regles a epuiser.

### 3. `FER-2` et `FER-3`

- **`FER-2`** : **11 items** sur la ligne, au-dessus du plancher de dix, et strictement plus que les
  dix manches (`DEC-0064`). Tenue.
- **`FER-3`** : **deux taches reellement distinctes**, et je le dis apres avoir joue, pas apres
  avoir lu l'en-tete. Un item impair decrit une situation et demande **quoi faire** ; un item pair
  demande **pourquoi** la regle existe. Un enfant qui sait reciter la regle ne repond pas
  automatiquement a la seconde. Tenue.

### 4. Exactitude - onze items sur onze

Aucune erreur de fait. Les cinq raisons d'etre servies sont justes et ce sont celles qu'on enseigne :
le tour de parole, le materiel qui se retrouve, le corps qu'on ne bouscule pas, l'ecoute qui permet
de comprendre, la file qui evite l'attroupement. **Rien a reprendre sur le fond.**

### 5. `EMC-1`, `EMC-5`, `EMC-6`, `EMC-8`, `EMC-10`

- **`EMC-1`** et **`EMC-8` (precisee le 2026-09-05)** : **aucun item ne demande a l'enfant ce qu'il
  ressent, vit, croit ou fait, ni ce qu'il ferait.** Les onze enonces sont a la troisieme personne
  ou impersonnels - *"que doit faire un eleve"*, *"pourquoi range-t-on"*. Tenues, et c'est la forme
  exacte que j'avais donnee en exemple.
- **`EMC-5`** : **verifie item par item, sur les onze, jamais sur un echantillon.** Aucun item n'a
  deux reponses defendables. Tenue.
- **`EMC-6`** : aucun item ne presuppose un lieu de vie, une commune, un type d'ecole ni une
  configuration familiale. La cantine et les couloirs sont poses par l'enonce, pas supposes chez
  l'enfant. Tenue.
- **`EMC-10`** (neuve du 2026-09-05) : aucune personne reelle vivante, aucun parti, aucune
  organisation. Tenue.

### 6. `CYC-1` - la continuite cycle 2 / cycle 3, verifiee par moi et pas cochee

**C'est ma condition, c'est donc mon jugement et pas celui de qui a ecrit le lot.** J'ai fait ce que
`CYC-1` demande : lire les paquets **servis** de cycle 2 portant sur le meme objet, au perimetre du
**depot** et non du lot du jour. Recherche sur les trente paquets de CP servis : aucun autre ne
porte sur les regles de la classe (*"une regle"* dans `cp-comparer` et `cp-texte-narratif` designe
l'objet a mesurer, pas la regle de vie). **Ce paquet-ci est donc le seul contenu de cycle 2 sur
l'objet, et il ne contredit rien.** Ce qu'il produit en revanche, c'est l'ancrage que la ligne
`EMC-CM1-07` viendra reprendre : voir ma note dans `FICHE-bandit-manchot-droit-devoir-cm1.md`.

### 7. Ce que ce paquet fait bien et que je tiens a nommer

**Il evite le catechisme civique, et c'etait le risque numero un de la matiere** - je l'avais ecrit
le 2026-09-03 : *"une mecanique de choix appliquee a l'EMC produit spontanement du catechisme
civique, des items ou l'enfant coche la reponse gentille sans que rien n'ait ete travaille"*. Ici,
les cinq items *pourquoi* ne se repondent pas par la politesse : entre *"pour que chacun retrouve
ses affaires"* et *"pour empecher les autres d'y toucher"*, les deux sont des phrases d'adulte
raisonnable, et il faut avoir compris a quoi sert le rangement pour trancher. **C'est le seul des
quatre paquets d'EMC de ce sprint dont je peux ecrire cela.**

### 8. **`REG-R1` - la reserve, et elle porte sur trois distracteurs**

- **`REG-R1` (nouvelle, datee du 2026-09-05, non bloquante) - trois distracteurs sont hors registre
  ou hors categorie, et ils reduisent l'item a un choix entre deux.**

  **Mesure, pas impression** - les trois, cites :

  | Item | Distracteur | Ce qui ne va pas |
  |---|---|---|
  | 2 - *pourquoi leve-t-on la main* | *"pour montrer qu'on a chaud"* | absurde ; aucun enfant ne l'envisage |
  | 5 - *comment doit-on se deplacer dans les couloirs* | *"en criant"* | **n'est pas une facon de se deplacer** : le distracteur ne repond pas a la question posee, il est mort |
  | 8 - *pourquoi ecoute-t-on un camarade* | *"pour connaitre l'heure"* | absurde |

  **Pourquoi c'est une reserve et non un refus, et j'applique le test que j'ai deja applique deux
  fois** : sur chacun de ces trois items, **le second distracteur, lui, est plausible** - *"pour
  appeler la maitresse de loin"*, *"en courant"*, *"pour gagner une recompense"* - donc l'item reste
  un choix a faire et non une devinette. Un distracteur mort coute un tiers du pouvoir de mesure de
  l'item ; il ne l'annule pas. **Le defaut est la facilite, pas l'erreur.**

  **Ce que je demande pour la reprise** : remplacer les trois par des raisons plausibles mais
  fausses, du meme registre que la bonne reponse. Sur l'item 5, la contrainte est plus dure et plus
  simple a la fois : les trois propositions doivent etre des **facons de se deplacer**. **Je donne
  la propriete, pas les valeurs.**

### 9. Un point de biais que j'ouvre ici et que je ne fais pas peser sur ce paquet

Sur les **quatre paquets d'EMC de s29**, l'enseignant est nomme **trois fois** et il est **trois
fois une femme** (*"pour appeler la maitresse de loin"* ici ; *"sa maitresse etait malade"* et
*"ecouter la maitresse"* au CM1). Aucune occurrence masculine. **Ce n'est pas un defaut de ce
paquet-ci et je ne le lui impute pas** - une occurrence isolee ne fait pas un motif. **C'est un
constat de serie, date du 2026-09-05, que je pose pour que le prochain lot ne l'aggrave pas.**
Je ne le compte dans aucune reserve aujourd'hui.

### 10. Lisibilite au CP - mesuree, et c'est elle qui fait la difference avec le paquet voisin

**Mesure sur les onze enonces** : 8, 8, 8, 10, 10, 10, 11, 12, 12, 14, 15 mots. **Mediane 10,
maximum 15.** L'item le plus long fait une fois et demie la mediane, la ou mes deux precedents de
longueur (`COR-R1` a s27, `COR-R4` a s28) portaient sur des items a **plus du double**. **Rien a
reserver ici.**

Les onze indices vont de 5 a 13 mots, mediane 9. **Aucun indice ne donne la reponse** : ils
decrivent sans nommer, comme je l'exige depuis s27. Un enfant bloque peut avancer.

**Ce profil-la est le mien, et il sert de reference au refus que je prononce sur le paquet voisin
du meme lot** : c'est la meme developpeuse, le meme jour, le meme niveau, la meme mecanique. La
barre que j'oppose a `cp-emc-permis-interdit` n'est donc pas un chiffre que j'invente, c'est celui
que ce paquet-ci atteint.

### 11. Joue jusqu'au bout

Partie complete, **dix manches**, ecran de fin atteint, score 10. Sur erreur, l'enfant reste sur
l'item et recoit l'indice ; l'indice ne se retire jamais. **Un enfant bloque n'est pas bloque.**

### 12. Ce dont je reste la seule garde sur ce paquet

Dit explicitement pour que l'equipe ne croie pas couvert ce qui ne l'est pas :

| Propriete | Qui la tient | Automatisable ? |
|---|---|---|
| **`EMC-5`** - aucun item a deux reponses defendables | **moi seule** | non |
| **`EMC-8`** - aucun item ne fait declarer un comportement a l'enfant | **moi**, mais la forme *"que fais-tu"* se cherche | **partiellement** |
| **`FER-3`** - que les deux taches soient reellement deux | **moi seule** | non |
| **`REG-R1`** - qu'un distracteur soit du meme registre que la bonne reponse | **moi seule.** Aucun test ne lit la plausibilite d'une proposition | non |
| **La mention exigee d'avance** - qu'elle soit ecrite | **moi**, et **`camille-po`** en l'ecrivant | non |
| **`CYC-1`** - qu'aucun item de cycle 3 ne contredise celui-ci | **moi seule**, par lecture | non |

### 13. Ce que mon vert ne prouve pas

- Il ne prouve pas que l'enfant **respecte** ces regles : le produit verifie ce qu'il sait, jamais
  ce qu'il fait. C'est tout l'objet de la mention exigee.
- Il ne prouve pas que la ligne est servie **au-dela de ces onze situations** : le tour de parole,
  le rangement, le couloir, l'ecoute et la cantine. Une regle de vie que le paquet n'a pas prise
  n'est pas evaluee.
- Il ne prouve **rien du paquet voisin du meme lot**, que je refuse par ailleurs. Un lot n'est pas
  une unite de verdict ; un paquet l'est.


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

## Verdict sur `CONS-1` - **tenue sur ce jeu**

**J'ai relu les onze items** de `cp-emc-regles-de-vie` avec leur bonne reponse. **Ce bloc ne rouvre
aucune ligne** et ne touche pas a `REG-R1`, dont le bloc reste ouvert et differe a s31 par le
planning du sprint.

Consigne servie : *"Lis bien, puis dis ce qu'il faut faire, ou pourquoi."*

**Elle nomme les deux taches du paquet, et je les ai comptees** : six items de conduite (*"Avant de
parler en classe, que doit faire un eleve ?"* -> `lever la main`) et cinq items de raison d'etre
(*"Pourquoi leve-t-on la main avant de parler en classe ?"* -> `pour que chacun parle a son tour`).
**Aucune tache n'est laissee sans consigne, aucune consigne ne decrit une tache absente.**

**Onze mots**, sous le plafond de douze que j'ouvre par ailleurs sous `CONS-2` pour les consignes de
CP que l'enfant lit lui-meme. **L'ellipse *"ou pourquoi"* est le seul point que j'ai pese** : elle
oblige l'enfant a reconstruire *"ou dis pourquoi il faut le faire"*. **Je ne la retiens pas comme
defaut** - la phrase est courte, le mot *pourquoi* ouvre litteralement les cinq items concernes, et
les onze enonces portent chacun leur question entiere.

**`CONS-1` est tenue sur cette entree.** C'est une des cinq reussites de `S30-9`, et un net progres
sur la consigne generique qu'elle remplace. **`EMC-CP-05` reste `couvert-valide`, aucune ligne ne
bouge.**
