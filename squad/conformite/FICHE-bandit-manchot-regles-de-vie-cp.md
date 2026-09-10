# Fiche de conformite - bandit-manchot-regles-de-vie-cp

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-emc-regles-de-vie.yaml`.
- **Entree de catalogue** : `bandit-manchot-regles-de-vie-cp`, titre servi *"Les regles de l'ecole"*,
  consigne servie *"Lis bien, puis dis ce qu'il faut faire, ou pourquoi."*, `nombreDeManches: 10`.
  **Corrige le 2026-09-09 par `nadia-prof`** : cette ligne annoncait *"Lis bien la question, puis
  choisis la bonne reponse."*, qui n'est plus la consigne servie par `catalogue.yaml`. **Une fiche de
  conformite qui cite un texte que le produit ne sert plus est de la meme famille qu'`EXC-R1`**, a
  ceci pres que le fichier fautif est le mien. Verifie dans `catalogue.yaml` le jour de la
  correction ; aucun verdict n'en depend.
- **Statut global** : **VALIDE SOUS RESERVE** (Done pedagogique **prononce le 2026-09-05** par
  `nadia-prof`, apres avoir joue). **`EMC-CP-06` ferme** : `non couvert` -> **`couvert-valide`**,
  **a la condition que la mention de couverture partielle ci-dessous soit ecrite dans la matrice**.
  **Etat des reserves au 2026-09-10** : `REG-R1` **levee**, **`CAT-1` PAYEE** (bloc du 2026-09-10),
  `REG-R2` **levee**, `REG-R3` **ouverte**, `REG-R4` et `REG-R5` **ouvertes** - toutes non
  bloquantes, blocs de constat en fin de fiche. **La ligne n'est pas rouverte.**
- **Arbitrage prealable de `nadia-prof`** :
  `ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md`,
  sections 4.3 et 5, et
  `squad/programme/enseignement-moral-et-civique/competences-cp.md` (conditions `EMC-1` a `EMC-7`,
  posees le 2026-09-03).
- **Commit audite** : `5526932` (`[LEA] s29: S29-4`).
- **Derniere mise a jour** : 2026-09-10 (**verdict de `nadia-prof` apres avoir joue**, s33,
  instance B - paiement de `CAT-1` accepte au troisieme essai).

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
sur la consigne generique qu'elle remplace. **`EMC-CP-06` reste `couvert-valide`, aucune ligne ne
bouge.**

---

## `CAT-1` - reserve neuve, datee du 2026-09-07, non bloquante

**Trouvee en jouant une partie complete aujourd'hui**, arbre `2ef583ba`, worktree dedie, dix manches
avec une erreur volontaire par manche. **Elle n'etait pas visible dans mes deux audits precedents,
qui portaient sur le fichier et sur des parties ou je cherchais autre chose.**

**Le fait.** Sur la tache *"que doit-on faire"*, la bonne reponse est, a chaque item, la seule
proposition qui sonne comme l'ecole ; les deux distracteurs sont les seules qui sonnent comme la
faute.

| Manche | Bonne reponse | Distracteurs |
|---|---|---|
| 2 | *en marchant* | *en courant*, *en criant* |
| 4 | *attendre son tour* | *parler plus fort que lui*, *le couper aussitot* |
| 6 | *faire la queue* | *se bousculer*, *se servir tous ensemble* |
| 7 | *les remettre a leur place* | *les laisser tomber par terre*, *les garder pour elle* |

**Un enfant qui n'a jamais entendu la regle gagne ces quatre items en reconnaissant le registre
attendu.** C'est `CON-1` : l'item se gagne sans la competence visee, et il se gagne d'autant plus
surement que l'enfant est docile - ce qui est le contraire de ce que l'EMC construit. C'est
exactement le **catechisme civique** dont j'avais ecrit le risque le 2026-09-03, dans le referentiel
d'EMC CP, avant qu'un seul item de la matiere existe.

**Ce que je demande, sans prescrire de texte** : sur cette tache, **les trois propositions sont du
meme registre** ; ce qui les distingue est ce qu'elles disent, jamais comment elles sonnent. Une
forme qui tient, donnee pour que la propriete soit praticable : sur *"pourquoi fait-on la queue a la
cantine ?"*, trois raisons serieuses et polies, dont deux fausses (*"pour que le repas soit plus
chaud"*, *"pour que la maitresse compte les eleves"*). L'enfant doit alors savoir **a quoi la regle
sert**, ce qui est la moitie du libelle de `EMC-CP-06`.

**Regime : voyage avec `REG-R1` dans le meme rejeu borne (`PAR-10`), sans cout supplementaire.**

**Ce que cette reserve ne fait pas.** **`EMC-CP-06` reste `couvert-valide` et n'est pas rouverte.**
Le poteau est neuf et date d'aujourd'hui ; il ne se retourne pas contre une barre franchie le
2026-09-05. Mon verdict du 2026-09-05 reste lisible entier ci-dessus, sans retrait ni retouche.

**Correction faite dans le meme geste, sur mon propre texte** : la derniere phrase de la section
`CONS-1` disait *"`EMC-CP-05` reste `couvert-valide`"*. Ce paquet sert `EMC-CP-06`
(`lignesDeReferentiel: ["EMC-CP-06"]`, verifie dans le fichier aujourd'hui) ; `EMC-CP-05` est `non
couvert`. Le code est corrige. **Un code de ligne faux dans une fiche de conformite est de la meme
famille qu'une reference qui pointe vers rien** : il se recopie ensuite dans la matrice.


---

## Bloc de constat de paiement - 2026-09-09 - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent. **Il s'ajoute aux blocs du
> 2026-09-05 et du 2026-09-07 et n'en retouche aucun.** Il porte sur le correctif `S32-CAT1` de
> `lea-dev` (`b7a79e34`).

- **Verdict** : **VALIDE SOUS RESERVE - maintenu.** **`EMC-CP-06` reste `couvert-valide` et n'est pas
  rouverte.**
- **`REG-R1` : LEVEE**, y compris sa clause la plus dure.
- **`CAT-1` : NON PAYEE.** Je refuse le paiement, avec le compte item par item ci-dessous. Elle
  reste ouverte sur ce paquet, **non bloquante**, et elle ne rouvre pas la ligne.
- **`REG-R2` et `REG-R3` : ouvertes**, datees du 2026-09-09, non bloquantes.
- **Date** : 2026-09-09.
- **Arbre audite** : `round-two` a `fd19d18f`, worktree dedie `s32-nadia-b`. Commit du correctif :
  `b7a79e34` (`[LEA] s32: S32-CAT1`).
- **Audit joue** : **oui, une partie complete de dix manches**, erreurs volontaires jusqu'a
  epuisement des propositions sur chaque manche dont la premiere reponse n'etait pas la bonne - donc
  **tous les distracteurs presses et tous les indices lus**. Jar reconstruit depuis mon worktree,
  lance sur le JDK que designe la toolchain 21 (21.0.10 rapporte par la machine virtuelle), port
  local. Le tirage m'a servi **dix items sur onze** ; le onzieme, l'item 2 (*"Pourquoi leve-t-on la
  main avant de parler en classe ?"*), lu dans le fichier. **Le releve integral des dix manches est
  dans
  `squad/sprints/s32-trois-instances-et-ouverture-de-l-eps/verdicts-nadia-instance-B.md`,
  section 4** ; il est la trace ecrite d'une partie jouee dont `yanis-qa` a constate l'absence.
- **Audit degrade sur un point, declare** : lecture du **HTML servi**, pas de la page rendue par un
  navigateur. **Aucun constat de ce bloc ne repose sur un jugement visuel** : tous se recomptent sur
  le fichier de contenu.
- **Porte technique** : **les quatre jobs de la CI lances sur mon diff et nommes un par un** -
  `prefixes`, `chemins`, `outils`, `build`. Chiffres dans mon releve, section 9.

### `REG-R1` est levee, et sa clause la plus dure est tenue

Les trois distracteurs que la reserve nommait ont disparu, chacun remplace par une raison plausible
et fausse :

| Item | Ce que `REG-R1` condamnait | Ce que l'ecran sert aujourd'hui |
|---|---|---|
| 2 | *"pour montrer qu'on a chaud"* (absurde) | *"pour que la maitresse nous felicite"* |
| 5 | *"en criant"* (**n'est pas une facon de se deplacer**) | *"en sautant a cloche-pied"* |
| 8 | *"pour connaitre l'heure"* (absurde) | *"pour savoir quand ce sera son tour de parler"* |

**Sur l'item 5, j'avais pose une contrainte plus dure que sur les deux autres** - *"les trois
propositions doivent etre des facons de se deplacer"*. **Elle est tenue** : marcher, courir, sauter a
cloche-pied. **`REG-R1` est levee sans reserve residuelle**, et je le dis dans ces termes parce que
c'est la barre que j'avais ecrite et qu'elle est atteinte.

### `CAT-1` n'est pas payee - le compte item par item

**Ce que ma clause demande, dans les mots ou je l'ai ecrite le 2026-09-07** : *"Les trois
propositions sont du meme registre ; ce qui les distingue est ce qu'elles disent, jamais comment
elles sonnent."* Et la forme que j'ai donnee pour la rendre praticable montre **deux** raisons
serieuses et fausses face a la bonne, pas une.

**Ce que `lea-dev` a fait, et je le dis avant de compter** : sur six items - dont les deux que mon
releve du 2026-09-07 ne nommait pas -, **un** des deux distracteurs devient une proposition serieuse
et polie mais fausse. C'est un progres reel et c'est plus que ce que mon releve nommait. **Ce n'est
pas ce que ma clause demande.**

| Item | Distracteur 1 | Distracteur 2 | Etat de `CAT-1` |
|---|---|---|---|
| 1 - *avant de parler* | *parler plus fort* (registre de la faute) | *regarder la maitresse sans rien dire* | **a moitie** |
| 2 - *pourquoi lever la main* | *pour que la maitresse nous felicite* | *pour appeler la maitresse de loin* | **tenue** |
| 3 - *que faire du materiel* | *le laisser sur la table* | *le jeter a la poubelle* | **non tenue** |
| 4 - *pourquoi ranger* | *pour empecher les autres d'y toucher* | *pour gagner un jeu* | **non tenue** |
| 5 - *comment se deplacer* | *en courant* | *en sautant a cloche-pied* | **non tenue** |
| 6 - *pourquoi marcher* | *pour arriver en retard* | *pour arriver le premier en classe* | **non tenue** |
| 7 - *un camarade parle deja* | *parler plus fort que lui* | *aller prevenir la maitresse tout de suite* | **a moitie** |
| 8 - *pourquoi ecouter* | *pour savoir quand ce sera son tour* | *pour gagner une recompense* | **a moitie** |
| 9 - *se servir en meme temps* | *se bousculer* | *demander a la maitresse de choisir qui passe en premier* | **a moitie** |
| 10 - *pourquoi la queue* | *pour que le repas soit plus chaud* | *pour que la maitresse compte les eleves* | **tenue** |
| 11 - *les crayons empruntes* | *les garder pour elle* | *les donner a la maitresse pour qu'elle les range* | **a moitie** |

**Deux items sur onze tiennent la clause. Cinq la tiennent a moitie. Quatre ne la tiennent pas du
tout.** Sur ces quatre, un enfant qui n'a jamais entendu la regle **gagne encore en reconnaissant le
registre**, exactement comme le 2026-09-07 : la bonne reponse y est la seule proposition calme,
soigneuse ou desinteressee.

**Et l'item 5 est celui qui decide, parce qu'il etait dans mon releve.** *"En marchant"* reste la
seule facon calme de se deplacer face a deux facons agitees. La correction de l'item 5 paie `REG-R1`
- le distracteur mort devient une vraie facon de se deplacer - **et ne paie pas `CAT-1`**. **Ce n'est
pas un poteau deplace : c'est un des six items que j'avais nommes, et la propriete que j'avais
nommee n'y est pas atteinte.**

#### Ce que la correction a mis a la place du registre, et pourquoi cela compte

**Mesure, pas impression.** Sur les onze items, **six portent desormais au moins un distracteur qui
nomme la maitresse**, et **aucune bonne reponse ne la nomme**. Sur l'item 2, **les deux**
distracteurs la nomment.

**Consequence, jouee** : la strategie *"je ne prends jamais le bouton qui parle de la maitresse"*
elimine un distracteur sur cinq items **et donne la bonne reponse d'emblee sur l'item 2** - celui-la
meme ou `CAT-1` est desormais tenue. **Le repere de registre a ete remplace par un repere lexical**,
qui est de la famille de `FAB-1c` : la bonne reponse se reconnait a la forme de son texte. Le compte
etait de **1** occurrence de *maitresse* dans les propositions de ce paquet avant le correctif, il
est de **7** apres.

**Je ne l'ecris pas comme une reserve de plus** : c'est le meme geste qui doit payer `CAT-1`, et le
compter deux fois serait facturer deux fois la meme reecriture. **Je l'ecris comme une contrainte
opposable sur la prochaine correction**, pour qu'elle ne refasse pas le chemin dans l'autre sens.

#### Ce qui paie `CAT-1` sur ce paquet - la propriete et son compte, jamais le texte

> **Sur les onze items, les trois propositions sont du meme registre** : chacune est une phrase qu'un
> adulte pourrait dire serieusement a un enfant, et deux sont fausses pour la situation decrite.
> **Onze sur onze ; il n'y a pas de compte partiel** - la clause est ecrite par item, sans seuil, et
> je ne lui en donne pas un aujourd'hui, ce qui serait l'affaiblir apres coup.
>
> **Et le repere ne se deplace pas d'un canal a l'autre** : aucun mot present dans les distracteurs
> de plusieurs items ne doit etre absent de toutes les bonnes reponses du paquet. *La formulation
> appartient au developpeur.*

**Regime** : `CAT-1` garde le regime que je lui ai donne le 2026-09-07 - **elle voyage dans le rejeu
borne du paquet, sans cout supplementaire** (`PAR-10`). **Elle n'a pas ete payee ; elle n'est pas non
plus a refacturer.**

### Reserves ouvertes aujourd'hui - deux, non bloquantes, datees du 2026-09-09

#### `REG-R2` - deux distracteurs absurdes survivent, et mon compte de trois etait incomplet

**Ouverte le 2026-09-09, non bloquante.**

| Item | Distracteur | Ce qui ne va pas |
|---|---|---|
| 4 - *pourquoi range-t-on le materiel* | *"pour gagner un jeu"* | hors sujet ; ranger n'a aucun rapport avec gagner un jeu |
| 6 - *pourquoi marche-t-on au lieu de courir* | *"pour arriver en retard"* | **absurde a l'envers** : personne ne court pour arriver en retard, et l'enfant ecarte cette proposition sans rien savoir de la regle |

**C'est une correction de mon propre constat, et c'est la quatrieme de ce sprint qui va dans le meme
sens.** Ma reserve `REG-R1` du 2026-09-05 annoncait *"Mesure, pas impression - les trois, cites"*.
**Il y en avait cinq.** Les trois que j'avais cites sont payes ; ces deux-la ne l'ont jamais ete
parce que je ne les avais pas comptes. **`REG-R1` reste levee** - elle nommait trois distracteurs et
ils sont payes ; **je n'y rattache pas les deux autres apres coup**, j'ouvre une reserve neuve et
datee.

> **Propriete a tenir** : chaque distracteur est une raison qu'un enfant de CP pourrait vraiment
> donner pour la situation decrite. La formulation appartient au developpeur. **Elle se paie dans le
> meme geste que `CAT-1`, dont elle est un cas particulier sur ces deux items.**

#### `REG-R3` - l'adulte de l'ecole est une femme sept fois sur sept, et le correctif l'a aggrave

**Ouverte le 2026-09-09, non bloquante.**

**Le 2026-09-05, dans cette meme fiche, j'ai ecrit ceci** : *"Sur les quatre paquets d'EMC de s29,
l'enseignant est nomme trois fois et il est trois fois une femme. Aucune occurrence masculine. Ce
n'est pas un defaut de ce paquet-ci et je ne le lui impute pas - une occurrence isolee ne fait pas un
motif. C'est un constat de serie, date du 2026-09-05, que je pose **pour que le prochain lot ne
l'aggrave pas**."*

**Le lot suivant l'a aggrave, et c'est mesure** : ce paquet passe de **1** a **7** occurrences de
*maitresse* dans ses propositions, **toutes feminines, aucune masculine**. Sur l'ensemble du contenu
d'EMC servi au CP - enonces, propositions et indices des six paquets confondus -, le compte est de
**17** occurrences de *maitresse* contre **1** adulte d'ecole masculin (*le directeur*, sur
`formules-de-politesse`). **Ce n'est plus une occurrence isolee, c'est
une serie, et cette fois je l'impute** - a ce paquet-ci, parce que c'est lui qui l'a aggravee, et pas
aux paquets voisins que je n'ai pas rejoues pour ce motif.

**Ce que ce n'est pas** : ni une erreur factuelle, ni un obstacle a jouer, ni un motif de refus. Une
classe de CP peut parfaitement avoir une maitresse. **Ce qui est en cause est la serie** : un enfant
qui joue les six paquets d'EMC CP ne rencontre jamais un homme devant une classe.

> **Propriete a tenir** : sur l'ensemble des propositions et des enonces de ce paquet, l'adulte de
> l'ecole n'est pas systematiquement d'un seul genre. **Je ne fixe aucune proportion** - je n'ai
> compte cette population que sur l'EMC CP, et une barre chiffree se pose sur une population comptee,
> dans un arbitrage prealable, jamais dans une fiche en cours de sprint.

### Ce que j'ai verifie et qui ne fait pas reserve - dit pour qu'on ne me le redemande pas

- **Aucune bonne reponse n'a change**, aucun enonce n'a change : `FER-1`, `FER-2`, `FER-3`, `EMC-1`,
  `EMC-8`, `EMC-10` sont tenues a l'identique, recomptees sur le materiau servi.
- **`LON-1` et `LON-2`** : les onze enonces vont de **7 a 14 mots**, deux phrases au plus, la seconde
  etant la question. **Dette de longueur nulle sur ce paquet**, ce qui etait deja le cas avant le
  correctif.
- **`EMC-5`** : aucun item n'a deux reponses defendables. Les distracteurs polis ajoutes sont faux
  pour la situation - *"regarder la maitresse sans rien dire"* n'est pas ce qu'on fait avant de
  parler, *"aller prevenir la maitresse"* n'est pas ce qu'on fait quand un camarade parle, *"demander
  a la maitresse de choisir"* n'est pas la regle de la cantine. **Verifie item par item : c'est le
  risque principal de ce genre de correction, et il n'est pas realise.**
- **Les indices** : inchanges, et chacun reste plus simple que son enonce.

### Ce que je route

| Suite | Destinataire | Ce que j'attends |
|---|---|---|
| **`EMC-CP-06` reste `couvert-valide`** | `camille-po` | **Aucun mouvement de matrice.** La mention de couverture partielle exigee le 2026-09-05 reste due et inchangee |
| **`CAT-1` non payee, avec son compte de 2/11**, plus `REG-R2` et `REG-R3` | `camille-po`, via `sofia-sm` | En faire une story. **Les trois se paient dans le meme geste** : `REG-R2` est un cas particulier de `CAT-1` sur deux items, `REG-R3` est une contrainte sur les mots que ce geste emploie. Regime inchange : **rejeu borne, 0,5 pour le paquet entier** (`PAR-10`) |
| **Que la bonne reponse ne se reconnaisse pas a un mot present dans les seuls distracteurs** | `theo-archi` | **Fait mesure** : 6 items sur 11 portent *maitresse* dans un distracteur, 0 bonne reponse le porte. C'est une comparaison d'ensembles de mots sur un paquet, du meme genre que la clause 2 d'`ADR-0086`. **Je dis la propriete, jamais la forme du test, et je ne prescris aucun seuil** |
| **La ligne d'entree de catalogue de cette fiche, qui citait une consigne que le produit ne sert plus** | moi-meme, corrigee ci-dessus | **Corrigee dans ce diff.** Le meme defaut existe sur `FICHE-bandit-manchot-permis-interdit-cp.md`, corrige dans le meme geste |

---

## Bloc de verdict du 2026-09-10 - `nadia-prof`, instance B de s33, apres avoir joue - `CAT-1` PAYEE

**Arbre audite** : `round-two` a `b45e5927`, worktree dedie. **Partie complete de dix manches, avec
erreurs volontaires sur chaque manche dont la premiere proposition pressee n'etait pas la bonne.**
Releve manche par manche dans
`squad/sprints/s33-plan-2-tenu-le-palier-2-differe-a-s34/verdicts-nadia-instance-B.md`, section 4.
**Audit degrade sur un point, declare** : lecture du HTML servi, pas de la page rendue par un
navigateur - aucune conclusion de ce bloc ne repose sur un jugement visuel.

**Story auditee** : `S33-CAT1`, deuxieme tentative de paiement de `CAT-1`. **La ligne `EMC-CP-06`
reste `couvert-valide` et je ne la rouvre pas.**

### `CAT-1` est payee

**Ma clause demande les trois propositions du meme registre.** Meme grille qu'a s32 - **tenue** quand
aucune des trois n'est reconnaissable a son ton, **a moitie** quand une seule des deux fausses l'est,
**non tenue** quand la bonne reponse est la seule qui ne le soit pas.

| Item | Bonne reponse | Les deux distracteurs | 2026-09-09 | 2026-09-10 |
|---|---|---|---|---|
| 1 | *lever la main* | *attendre en silence* / *chuchoter a son voisin* | non tenue | **a moitie** |
| 2 | *pour que chacun parle a son tour* | *pour montrer qu'on a compris* / *pour demander la permission de sortir* | non tenue | **tenue** |
| 3 | *le ranger* | *le laisser secher sur la table* / *le donner a un camarade pour qu'il l'utilise* | non tenue | **tenue** |
| 4 | *pour que chacun retrouve ses affaires* | *pour que la salle soit plus jolie* / *pour qu'il dure plus longtemps* | non tenue | **tenue** |
| 5 | *en marchant* | *en marchant a reculons* / *en trainant les pieds* | a moitie | **tenue** |
| 6 | *pour que personne ne se blesse* | *pour ne pas faire de bruit* / *pour garder de l'energie pour la recreation* | a moitie | **tenue** |
| 7 | *attendre son tour* | *parler plus fort que lui* / *lui dire de se taire* | a moitie | **NON TENUE** |
| 8 | *pour comprendre ce qu'il dit* | *pour savoir quand ce sera son tour de parler* / *pour montrer qu'on est poli* | tenue | **tenue** |
| 9 | *faire la queue* | *attendre que les autres soient partis* / *laisser les plus grands passer devant* | a moitie | **tenue** |
| 10 | *pour que tout le monde passe sans se bousculer* | *pour que le service soit plus rapide* / *pour montrer qu'on est patient* | a moitie | **tenue** |
| 11 | *les remettre a leur place* | *les garder pour elle* / *les donner a un camarade qui n'en a pas* | tenue | **a moitie** |

**8 tenues, 2 a moitie, 1 non tenue** - c'etait **2 / 5 / 4** le 2026-09-09. Sur les cinq items
*"pourquoi"*, la tache ou le registre etait le plus mordant, la clause est tenue **5 fois sur 5**.

> **`CAT-1` est PAYEE sur ce paquet.** Le controle se fait sur la tache entiere, jamais item par
> item, et **aucune des deux taches ne se gagne plus au registre**. C'est ce que ma clause demande le
> 2026-09-07, et **je ne demande rien de plus apres coup.**

### Le repere lexical de s32 est parti, et je l'ai compte plutot que suppose

| | *maitresse* dans les propositions servies | dans une bonne reponse |
|---|---|---|
| Avant `S32-CAT1` | 1 | 0 |
| Apres `S32-CAT1` (etat refuse le 2026-09-09) | **7**, sur 6 items de 11 | 0 |
| Apres `S33-CAT1` | **0** | **0** |

**Compte fait sur les items du fichier servi, pas sur le fichier** : les trois occurrences restantes
de la chaine dans `cp-emc-regles-de-vie.yaml` sont toutes dans les commentaires d'en-tete et
n'atteignent aucun enfant. **Le mot n'a pas ete redistribue, il a ete retire** - c'etait le choix
couteux, et c'est celui que la clause demandait.

### `REG-R2` est levee

Les deux distracteurs absurdes des items 4 et 6, que `REG-R1` n'avait pas comptes, sont remplaces par
quatre raisons plausibles : *pour que la salle soit plus jolie*, *pour qu'il dure plus longtemps*,
*pour ne pas faire de bruit*, *pour garder de l'energie pour la recreation*. **Levee.**

### `REG-R4` - l'item 7 est plus mauvais qu'avant le correctif

| | Distracteurs de l'item 7 |
|---|---|
| Avant `S33-CAT1` | *parler plus fort que lui* / **aller prevenir la maitresse tout de suite** |
| Apres `S33-CAT1` | *parler plus fort que lui* / **lui dire de se taire** |

**Le distracteur serieux a ete remplace par un distracteur impoli**, parce qu'il portait le mot que la
story devait retirer. **La bonne reponse redevient la seule proposition polie des trois**, ce qui est
mot pour mot la premiere phrase de `CAT-1`. En jouant, j'ai presse *attendre son tour* du premier
coup sans lire les deux autres.

> **`REG-R4` - nouvelle, datee du 2026-09-10, non bloquante.** Sur l'item 7, les deux distracteurs
> sont impolis et la bonne reponse est la seule qui ne le soit pas. **Le remede du repere lexical ne
> se paie pas par un retour du repere de registre sur l'item ou le mot a ete retire.**

### `REG-R5` - un gabarit de phrase a pris la place du mot

Trois distracteurs partagent la meme amorce de cinq mots, et aucune bonne reponse ne la porte :
*pour montrer qu'on a compris* (item 2), *pour montrer qu'on est poli* (item 8), *pour montrer qu'on
est patient* (item 10) - **trois des cinq items de la tache *"pourquoi"***.

La strategie *"je ne prends jamais le bouton qui commence par pour montrer qu'on"* **elimine** un
distracteur sur ces trois items ; **elle ne donne pas la reponse**, contrairement au *maitresse* de
s32 qui la donnait d'emblee sur l'item 2 en portant les **deux** distracteurs. **C'est pourquoi c'est
une reserve et non un refus de paiement.**

> **`REG-R5` - nouvelle, datee du 2026-09-10, non bloquante.** Aucun gabarit de phrase repete ne se
> trouve dans les seuls distracteurs. **Je ne pose aucun seuil chiffre** : trois occurrences sur cinq
> items d'une tache est ce que j'ai mesure, ce n'est pas une barre.

### Correction a mon propre constat de `REG-R3`, et elle va contre moi

**Ce que j'ai ecrit le 2026-09-09 dans cette fiche** : *"17 occurrences de maitresse contre 1 adulte
d'ecole masculin"* sur l'EMC CP entier.

**Recompte le 2026-09-10** sur le materiau servi des six paquets - enonces, bonnes reponses,
distracteurs et indices, mots designant un adulte d'ecole a genre grammatical marque :

| | Feminin | Masculin |
|---|---|---|
| Avant s33 | **17** | **6** |
| Apres s33 | **12** | **8** |

**Les cinq occurrences de *un surveillant de la cour*, dans les propositions de `exclusion`, n'ont
jamais ete comptees.** Le desequilibre est reel et **plus petit que je ne l'ai ecrit**. `REG-R3`
reste ouverte, sa propriete est intacte, et **je ne pose toujours aucune barre chiffree**.

**Sur les cinq points gagnes cote masculin, quatre viennent des enonces de `exclusion` que je refuse
par ailleurs** : si ces enonces reviennent a leur etat d'avant s33, le compte redescend a **10 / 6**.
**Ce paquet-ci garde son gain propre** - le retrait des sept *maitresse* - et c'est celui-la qui est
acquis.

### Ce que j'ai verifie et qui ne fait pas reserve - dit pour qu'on ne me le redemande pas

- **Aucune bonne reponse, aucun enonce, aucun indice n'a change.** `FER-1`, `FER-2` (onze items),
  `FER-3`, `EMC-1`, `EMC-8`, `EMC-10` tenues a l'identique, recomptees sur le materiau servi.
- **`EMC-5`** : aucun item n'a deux reponses defendables. Verifie item par item, parce que **rendre
  les distracteurs serieux les rapproche du vrai** et que c'est le risque propre de ce correctif.
  Les deux qui m'ont fait m'arreter : *attendre en silence* (item 1) n'est pas ce qu'on fait avant de
  parler, on leve la main ; *le laisser secher sur la table* (item 3) n'est pas ce qu'on fait *de son
  materiel* quand on a fini. Les deux sont faux pour la situation decrite.
- **`PLA-1`** : chaque distracteur est un candidat qu'un enfant de six ans peut serieusement
  envisager. *En marchant a reculons* et *en trainant les pieds* sont deux facons de marcher que les
  enfants pratiquent.
- **`LON-1`, `LON-2`** : hors de ma garde depuis `ADR-0089`, qui les execute. **Je ne recompte pas a
  la main ce qu'un garde-fou compte mieux que moi.**
- **Le chemin d'erreur** : premiere erreur -> *"Ce n'est pas ca. Essaie encore !"* suivi de l'indice ;
  troisieme -> revelation de la reponse et l'enfant continue. **Un enfant bloque peut avancer.**

### Ce que je route

| Suite | Destinataire | Ce que j'attends |
|---|---|---|
| **`EMC-CP-06` reste `couvert-valide`** | `camille-po` | **Aucun mouvement de matrice.** La mention de couverture partielle exigee le 2026-09-05 reste due et inchangee |
| **`REG-R4` et `REG-R5`** | `camille-po`, via `sofia-sm` | Une seule story : les deux se paient dans le meme geste sur ce paquet. Regime `PAR-10` inchange - rejeu borne, 0,5 pour le paquet entier |
| **`REG-R3`, chiffre corrige a 17 / 6 puis 12 / 8** | `camille-po`, via `sofia-sm` | Reserve toujours ouverte. **Le gain repose pour moitie sur des enonces que je refuse sur `exclusion`** |
| **Que la bonne reponse ne se reconnaisse pas a un gabarit present dans les seuls distracteurs** | `theo-archi` | **Fait mesure** : 3 distracteurs de 3 items portent *pour montrer qu'on*, 0 bonne reponse le porte. Meme genre que la clause 2 d'`ADR-0086`. **Je dis la propriete, jamais la forme du test, et je ne prescris aucun seuil** |
