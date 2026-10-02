# Fiche de conformite - bandit-manchot-phenomenes-naturels-cm1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-sciences-phenomenes.yaml`
  (12 items, type `question`), ecrit par `sofia-sm`, casquette dev (commit `862584e0`, fusion
  `93643b61`).
- **Entree de catalogue** : `bandit-manchot-phenomenes-naturels-cm1`, titre servi *"Les risques
  naturels"*, consigne servie *"Lis bien, puis dis quel phénomène, ou quel geste protège."*,
  `nombreDeManches: 10`.
- **Ligne declaree** : `SCI-CM1-16`. **Story** : `S46-SCI-PHENOMENES-CM1` (`docs/backlog/backlog.md`,
  section "Sprint 46").
- **Conditions opposables** : `SCI-1` a `SCI-4`, `BIA-2`, `HIS-3`, `CON-1`, `FER-1` a `FER-3`, et les
  refus d'avance 6 et 7
  (`squad/conformite/ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-quinze-reserves.md`,
  sections 2.2 ligne 16, 2.5 et 6). `CYC-1` non due (planning de s46, section 3).
- **Statut global** : **REFUSE**, prononce le 2026-10-02 par `nadia-prof` (s46, instance B). Fiche
  ouverte par ce verdict : aucune fiche n'existait pour ce jeu.
- **Derniere mise a jour** : 2026-10-02.

---

## Bloc de validation pedagogique

### Verdict du 2026-10-02 (`nadia-prof`, s46, instance B) : **REFUSE**

**Done pedagogique non prononce.** `SCI-CM1-16` reste `non couvert`. Un seul motif de refus,
section 7. Le lot repasse devant moi apres correction, et je le rejoue avant de juger : une
correction se verifie a l'ecran, pas au diff (refus d'avance 15). Les quatre reserves
`PHN-CM1-R1` a `PHN-CM1-R4` ne sont pas des motifs de refus ; elles peuvent etre prises dans le meme
diff que la correction, et je les rejouerai a ce moment-la.

#### 1. Ce que j'ai fait

**Audit complet, pas degrade.** Jar construit depuis mon worktree (`round-two` a `7a507b2a`,
`bash outils/mvn-jdk21.sh -pl bootstrap -am package -DskipTests`, `BUILD SUCCESS`), lance sur le
JDK 21 de la toolchain. **Deux parties completes** de dix manches en me trompant a chaque manche
jusqu'a la revelation (une erreur, puis une seconde erreur distincte). **Les douze items ont ete
tires et joues.** L'indice s'affiche des la premiere erreur et reste affiche ; l'ecran de fin
rappelle l'item manque et sa bonne reponse.

Porte technique : je ne l'ai pas mesuree. Je reprends le rapport de `yanis-qa`
(`squad/sprints/s46-soustraction-ce1-emc-et-sciences-cm1/rapport-qualite.md`), qui la declare verte
sur les cinq lots.

#### 2. Ancrage au programme

`SCI-CM1-16` : *"Identifier quelques phénomènes géologiques et météorologiques (séisme, éruption,
tempête, inondation) et les risques associés."* Tache A (items 1 a 6) : reconnaitre le phenomene
decrit. Tache B (items 7 a 12) : dire quel geste protege du risque, ou pourquoi. **C'est bien cette
competence**, et le couple de taches est celui que j'avais ecrit d'avance.

#### 3. Conditions, verifiees une par une

| Condition | Tenue | Ce que j'ai verifie |
|---|---|---|
| `FER-1` | **oui** | Geologique en bonne reponse (items 1, 2, 5), meteorologique (items 3, 4, 6), risque associe (items 7 a 12 ; les items 11 et 12 nomment le risque lui-meme, les items 7 a 10 le geste qui en protege, tache que j'ai ecrite moi-meme). Tenue meme sans l'item 2 (items 1 et 5) |
| `FER-2` | **oui** | 12 items |
| `FER-3` | **oui** | Deux taches, 6 et 6 items |
| `SCI-1` | **oui a la lettre, avec une reserve** | Une phrase par indice, jamais *"ta réponse"*, jamais la chaine exacte de la bonne reponse. Mais cinq indices sur six de la tache B donnent la reponse par paraphrase ou par elimination : `PHN-CM1-R1` |
| `SCI-2` | **oui** | Aucun item ne s'adresse a l'enfant ni ne met en cause son foyer ; tout est a l'impersonnel (*"quel geste permet de se protéger"*, *"pourquoi s'éloigne-t-on"*). Refus d'avance 6 tenu |
| `SCI-3` | **oui** | Zero chiffre dans les 60 champs que l'enfant lit, compte sur le YAML analyse |
| `SCI-4` | **oui** | Trois propositions sur les douze items |
| `BIA-2` | **oui, sur la barre que j'ai ecrite pour cette ligne** | Quatre items situes : Bretagne et sud de la France (France), Vesuve (Europe), Japon (ailleurs). Huit non situes. Refus d'avance 7 tenu : le paquet ne situe pas ailleurs tous les seismes, toutes les eruptions et toutes les inondations. Ce que je vois en plus : `PHN-CM1-R3` |
| `HIS-3` | **oui** | Chaque ligne d'item porte son marqueur ; j'ai verifie chaque fait moi-meme (section 5) |
| `CON-1` | **oui** | Aucun item ne se repond sans son enonce |

#### 4. Niveau reel

Enonces d'une ou deux phrases, vocabulaire du CM1 (*séisme*, *éruption volcanique*, *tempête*,
*inondation*, *rafales*). Les gestes de protection sont formules comme on les enseigne. Les
distracteurs de deux items et la consigne genent : `PHN-CM1-R2` et `PHN-CM1-R4`.

#### 5. Exactitude, fait par fait

- Tache A : secousses du sol au Japon, seisme ; tempete sur les cotes bretonnes ; inondation par
  debordement d'une riviere dans le sud de la France apres de fortes pluies ; secousse par ciel
  degage, seisme ; ciel sombre et rafales, tempete : **exacts**. **L'item 2 ne l'est pas** (section
  7).
- Tache B, verifiee contre les consignes de securite civile : s'abriter sous un meuble solide loin
  des fenetres pendant un seisme et ne pas sortir pendant les secousses ; rester a l'abri loin des
  fenetres pendant une tempete ; monter a l'etage, ne pas descendre a la cave ni traverser une zone
  inondee ; rester a l'interieur portes et fenetres fermees sous des retombees de cendres ; ne pas
  prendre l'ascenseur ; s'eloigner des cours d'eau dont le niveau peut monter vite : **exacts**.

#### 6. Biais et ton

Aucune adresse a l'enfant, aucun foyer mis en cause, aucun ton alarmiste : les gestes sont decrits
comme des gestes possibles, ce que `SCI-2` demande. Ton neutre et factuel.

#### 7. Motif du refus

> **Item 2 : l'enonce fait du Vesuve un lieu distinct du volcan, et ce qu'il affirme est faux.**
>
> Enonce servi : *"En Italie, près du Vésuve, un volcan rejette de la lave, des cendres et des gaz
> brûlants. Quel phénomène décrit cette situation ?"*
>
> **Le Vesuve est le volcan.** La phrase dit qu'un autre volcan, situe pres du Vesuve, rejette de
> la lave : aucun volcan voisin du Vesuve ne le fait. Le commentaire de source de l'item dit
> lui-meme le contraire de l'enonce (*"le Vésuve est un volcan actif d'Italie"*). Un enfant de CM1
> qui n'a jamais entendu ce nom en retient, en repondant juste, que le Vesuve est un endroit pres
> duquel se trouve un volcan. **C'est un fait affirme comme vrai dans un enonce**, lu par l'enfant
> qui reussit comme par celui qui echoue - pas une formulation relachee dans un indice, distinction
> que j'ai tenue sur `cm1-sciences-melanges` (`MEL-R1`) et sur `cm1-sciences-etapes-developpement`.
> Ma regle s'applique ici sans amenagement : une erreur factuelle dans un contenu scolaire est un
> motif de refus a elle seule.
>
> Point a corriger dans le meme geste : le Vesuve n'est pas entre en eruption depuis 1944. Un
> enonce au present qui le montre en train de rejeter de la lave presente comme actuel un fait qui
> ne l'est pas.
>
> **Correction attendue** : un enonce ou le volcan nomme est bien le volcan qui entre en eruption,
> et dont le fait est exact au temps employe (par exemple un volcan dont les eruptions sont
> frequentes, ou une phrase qui dit *quand le volcan entre en éruption*). Les formulations sont a
> l'equipe. Le marqueur `# lieu:` doit rester exact apres correction, et je le reverifierai contre
> `BIA-2`.
>
> **Ce que le refus ne demande pas** : aucune autre modification n'est une condition de levee. La
> levee porte sur l'item 2 seul, a la barre ecrite ici, et je ne la releverai pas au rejeu.

#### 8. Les reserves

> **`PHN-CM1-R1` - Tache B : cinq indices sur six donnent la reponse des la premiere erreur.**
> **Constate en jouant**, sur chaque indice affiche apres une erreur :
>
> | Item | Bonne reponse | Indice servi | Ce qu'il fait |
> |---|---|---|---|
> | 7 (seisme) | *s'abriter sous une table solide, loin des fenêtres* | *"Un meuble solide protège des objets qui tombent, un escalier pendant les secousses est dangereux."* | nomme la reponse (*meuble solide*) et ecarte un distracteur |
> | 8 (tempete) | *rester à l'intérieur, loin des fenêtres ...* | *"Rester à l'abri évite d'être blessé ..."* | nomme la reponse (*rester à l'abri*) |
> | 9 (inondation) | *monter à l'étage ou sur un point haut ...* | *"L'eau qui monte rend une cave ou une rue inondée dangereuses."* | ecarte nommement les deux distracteurs |
> | 11 (ascenseur) | *parce qu'il peut rester bloqué si l'électricité ...* | *"Un ascenseur dépend d'une alimentation électrique qui peut s'arrêter brutalement."* | paraphrase la reponse |
> | 12 (cours d'eau) | *parce que le niveau de l'eau peut monter très vite ...* | *"Un cours d'eau peut déborder beaucoup plus vite qu'on ne l'imagine."* | paraphrase la reponse |
>
> C'est `M21`, et pour l'item 9 la famille d'`OBT-R2`. Le test du paquet cherche la chaine exacte de
> la bonne reponse dans l'indice ; une paraphrase passe. L'indice de l'item 10 (*"Les cendres
> volcaniques peuvent gêner la respiration si on les respire."*) est le bon modele : il donne la
> raison sans designer le geste.
>
> **Correction a faire** : sur ces cinq items, un indice qui dit pourquoi le geste faux est
> dangereux, en n'ecartant **qu'un** distracteur au plus, sans reprendre les mots de la bonne
> reponse. **Pourquoi c'est une reserve** : le defaut est dans un indice, pas dans ce que l'enfant
> apprend comme vrai quand il repond juste - meme regime que `HG-R8`, `OBT-R2` et `MEL-R1`.
>
> **`PHN-CM1-R2` - Items 11 et 12 : des distracteurs que personne ne croit.** Item 11 : *"parce
> qu'il monte trop lentement"*, *"parce qu'il fait trop de bruit pendant les secousses"*. Item 12 :
> *"parce que l'eau devient soudainement plus froide"*, *"parce que les poissons remontent le
> courant à cette période"*. Aucun n'est une raison qu'un enfant de 9 ans avancerait ; la bonne
> reponse est en outre la plus longue des trois. L'item devient une devinette. **Correction a
> faire** : des distracteurs qui sont des raisons plausibles et fausses.
>
> **`PHN-CM1-R3` - Reserve nouvelle, datee du 2026-10-02 : la France n'a que du mauvais temps,
> l'etranger n'a que des catastrophes de la Terre.** Ma barre pour cette ligne, ecrite a s30, est
> le refus d'avance 7 ; elle est atteinte et je ne la releve pas. **Je corrige d'abord mon propre
> texte** : en ecrivant la ligne 16 a s30, j'ai transpose `BIA-2` en *"ne pas situer tout
> ailleurs"*, alors que sa lettre, ecrite a s27, est *"un pays n'est jamais représenté par un seul
> trait"*. Ici le Japon n'apparait que par un seisme et l'Italie que par une eruption, et aucun
> phenomene geologique n'est situe en France, qui en connait pourtant (seismes dans les Pyrenees,
> les Alpes et aux Antilles, volcans actifs a La Reunion et en Martinique). Je ne retourne pas
> contre l'equipe une barre que je lui ai donnee plus etroite. **Correction a faire** : situer au
> moins un phenomene geologique en France ; la correction de l'item 2 en est l'occasion.
>
> **`PHN-CM1-R4` - La consigne ne couvre pas les questions *"pourquoi"*.** Consigne servie :
> *"Lis bien, puis dis quel phénomène, ou quel geste protège."* Les items 11 et 12 demandent une
> raison, pas un geste. **Correction a faire** : une consigne qui nomme aussi cette tache, ou des
> items 11 et 12 reformules en choix de geste.

#### 9. Ce dont je reste la seule garde

`PaquetCm1SciencesPhenomenesTest` tient les marqueurs `FER-1` et de tache, le nombre d'items, la
forme de `SCI-1`, `SCI-3`, `SCI-4`, le marqueur `HIS-3` et, pour `BIA-2`, la presence d'au moins un
item situe en France ou en Europe. **Aucun automate ne verifie** : l'exactitude d'un fait de
geographie ou d'une consigne de securite (le motif de ce refus a traverse une CI verte) ; qu'un
indice ne donne pas la reponse par paraphrase (`PHN-CM1-R1`) ; la plausibilite d'un distracteur
(`PHN-CM1-R2`) ; qu'un pays ne soit represente que par un trait (`PHN-CM1-R3`) ; `SCI-2`. Ces
proprietes n'ont ete verifiees qu'ici, en jouant.
