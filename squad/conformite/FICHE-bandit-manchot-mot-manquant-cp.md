# Fiche de conformite - bandit-manchot-mot-manquant-cp

- **Mecanique** : bandit manchot (mecanique generique du catalogue, conditions `M1` a `M13`,
  `FICHE-bandit-manchot-additions-cp.md`). Cette fiche n'audite pas la mecanique : elle audite le
  paquet servi au travers d'elle.
- **Contenu servi** : `adapters/adapter-content/src/main/resources/contenu/cp-mot-manquant.yaml`,
  `type: question`, **16 items** (6 objets, 5 actions, 5 sensations), story `S21-10`, ecrit par
  `lea-dev`.
- **Entree de catalogue** : `bandit-manchot-mot-manquant-cp`, titre *"Le mot qui manque"*, consigne
  *"Trouve le mot qui complete la phrase."*, 10 manches.
- **Competence visee** : Francais CP, domaine "Vocabulaire", *"Utiliser des mots precis pour
  designer des objets, actions, sensations du quotidien"*
  (`squad/programme/francais/competences-cp.md`, ligne 34 ; verifie le 2026-08-27).
- **Ecriture neuve, pas une reprise.** Conformement a mon constat du 2026-08-27 (0 distracteur sur
  15 reutilisable dans `contenu-repris/textes-trous/cp-mot-manquant.yml`), `lea-dev` n'a repris que
  la forme. **Elle a suivi ma specification, et c'est le bon reflexe.**
- **Conditions prealables** : `PRE-1` a `PRE-8`,
  `squad/conformite/ARBITRAGE-PREALABLE-s21-trois-paquets-de-volume-cp.md`, section 4.
- **Statut global** : **REFUSE par `nadia-prof` le 2026-08-28**, sur **deux motifs**. Onze items
  sur seize tiennent `PRE-2` et cinq d'entre eux sont le meilleur travail de vocabulaire du
  catalogue CP ; le detail est ecrit item par item pour que la reprise ne jette pas ce qui est bon.
- **Derniere mise a jour** : 2026-08-28 (`nadia-prof`, audit de s21).

## Table de correspondance des jetons de cette fiche

Convention exigee par `squad/README.md`, section 2, amendement s16.

| Jeton | Ce qu'il designe | Ou il est ecrit |
|---|---|---|
| `PRE-1` a `PRE-8` | **Conditions prealables** a l'ecriture, posees le 2026-08-27 **avant** que le paquet existe | `ARBITRAGE-PREALABLE-s21-trois-paquets-de-volume-cp.md`, section 4.3 |
| `PRE-9` et suivants | **Reserves ouvertes a l'audit** du 2026-08-28 | cette fiche, section 5 |
| `M21` | Defaut deja nomme dans ce depot : un indice qui nomme la reponse est une revelation deguisee | `FICHE-bandit-manchot-additions-cp.md` |
| `M1` a `M13` | Conditions de la **mecanique**, hors du perimetre de cette fiche | `FICHE-bandit-manchot-additions-cp.md` |

**Regle de lecture** : `PRE-1` a `PRE-8` n'ont pas ete durcis. Une reserve numerotee `PRE-9` ou
au-dela est **nouvelle et datee**, jamais un resserrement retroactif.

## 1. Ce que j'ai fait, et ce que ma methode ne couvre pas

**J'ai joue, puis j'ai mesure.** Application construite depuis cet arbre
(`bash outils/mvn-jdk21.sh clean package -DskipTests`), servie par
`java -jar bootstrap/target/recreo.jar --server.port=<port>` sur un JDK 21, arretee **par son
identifiant de processus** et jamais par un nom d'image.

**Parties jouees le 2026-08-27**, par l'interface HTTP, en me trompant volontairement : parties
completes de dix manches, en repondant juste, en me trompant, et en brulant les trois essais.
**Puis j'ai relu les seize items un par un dans le fichier servi**, en essayant de defendre chaque
distracteur - c'est la verification que `PRE-3` demande, et elle ne se fait qu'a la main.

**Mesure de rendu**, meme date, **744 x 551** (`DEC-0040`), navigateur avec `--hide-scrollbars`, sur
le **paquet entier** :

```
CHROME=<navigateur> PAQUET=adapters/adapter-content/src/main/resources/contenu/cp-mot-manquant.yaml \
    PORT_DEV=9383 python squad/outils/nadia-prof/auditer-un-paquet-entier.py \
    http://<hote>:<port> bandit-manchot-mot-manquant-cp 16 \
    squad/conformite/releves-s21/s21-nadia2-mot-manquant-paquet-entier-744x551.json
```

Le viewport du releve (`[744, 551]`) est **lu du navigateur**, pas recopie de l'argument.

**Limites, avec l'etat exact qu'elles couvrent :**

- **Un seul viewport, 744 x 551. 360 px n'est pas couvert** et rien de ce que je dis sur la
  geometrie n'y vaut.
- **Aucun enfant n'a joue ce paquet.** Le jugement sur la plausibilite d'un distracteur est un
  **jugement de professeure**, pas une observation. Je l'avais annonce avant l'ecriture : *"la
  qualite d'un distracteur ne se mesure avec aucun chiffre"*. Elle ne se mesure toujours pas ; ce
  qui suit est un comptage de mes propres jugements, item par item, pour qu'ils soient
  contestables un par un.
- **L'outillage s'attache au premier navigateur qui repond sur un port de deverminage fixe** sans
  verifier que c'est le sien (`theo-archi`, `S21-1`, 2026-08-27). Ports distincts par execution :
  mitigation, pas garantie.
- **Aucun garde-fou du depot ne verifie `PRE-1` a `PRE-5` ni `PRE-7`.** Voir section 6.

## 2. Ce qui est bon dans ce paquet, et que la reprise ne doit pas jeter

Je l'ecris **avant** les motifs, parce que le risque d'un refus est qu'on rejette le tout.

- **`PRE-1` est tenue sur 16 items sur 16.** Verifie proposition par proposition : genre, nombre,
  classe de mot et compatibilite avec le determinant, l'elision ou la preposition. `balai`/
  `marteau`/`tournevis` avec `un` ; `savon`/`sable`/`sel` avec `du` ; `cuillere`/`fourchette`/
  `brosse` avec `une` ; `lampe`/`radio`/`tele` avec `sa` ; les cinq verbes tous a la troisieme
  personne du singulier ; les cinq adjectifs tous au masculin singulier. **Aucun item ne s'elimine
  par la grammaire** - c'etait le defaut de `Le poisson nage dans l'___.` du fichier repris, et il
  est ferme.
- **`PRE-4` est tenue.** Objets 6, actions 5, sensations 5 sur 16 : les trois familles nommees par
  la ligne du referentiel sont representees, aucune sous le quart (4).
- **La famille "sensations" est le meilleur travail de vocabulaire du catalogue CP.** Cinq items ou
  les trois propositions sont du meme champ et ou le choix se fait **entierement** sur la
  precision : `chaud`/`froid`/`tiede` sur le cafe, `acide`/`sucre`/`sale` sur le citron,
  `piquant`/`doux`/`mou` sur le herisson, `sucre`/`acide`/`sale` sur le miel,
  `piquant`/`sucre`/`sale` sur le poivre. **C'est exactement ce que `PRE-2` demandait**, et
  c'etait la partie difficile.
- **La famille "actions" tient aussi**, sur ses cinq items : `aboie`/`chante`/`parle` (trois
  emissions vocales), `nage`/`saute`/`marche` (trois deplacements),
  `eteint`/`allume`/`casse` (la paire d'antonymes sur le meme objet, la meilleure construction du
  paquet).
- **`PRE-7` est tenue sur 16 items sur 16.** Les seize indices lus a l'ecran apres un echec :
  **aucun ne nomme la reponse ni ne la donne par un synonyme evident**. Le defaut `M21`, que
  j'avais retrouve a s20 sur deux items de `cm1-reperes`, n'est pas ici.
- **`PRE-8` est tenue.** Le trou est marque `___` sur les seize items, il se distingue nettement
  d'un mot a l'ecran et ne se confond avec aucun trait du gabarit - verifie en jouant.
- **`PRE-5`** : 16 items. **`PRE-6`** : trois propositions sur 16 items sur 16.
- **Le pli est tenu sur 16 items sur 16** (section 4).

## 3. Les motifs du refus

### Motif 1 - l'item 6 (`La nuit, Tom allume sa ___.`) a plus d'une reponse defendable

Propositions : `lampe` (attendue), `radio`, `tele`. **Rien dans l'enonce ne discrimine.** *"La nuit,
Tom allume sa radio"* et *"La nuit, Tom allume sa tele"* sont deux phrases francaises vraies,
idiomatiques et parfaitement plausibles pour un enfant de six ans. L'enfant qui les choisit a
raison, et l'application lui repond *"Ce n'est pas ca. Essaie encore !"* - verifie en jouant le
2026-08-27, manche 1 d'une partie, appui sur `radio`.

**C'est `PRE-3`, mot pour mot** : *"Aucun item n'a de reponse defendable autre que celle
attendue."* Ici, l'excellence de `PRE-2` se retourne contre l'item : les trois propositions sont si
bien du meme champ (trois appareils qu'on allume) que **plus rien ne tranche**. L'indice
(*"Elle donne de la lumiere quand on appuie sur le bouton."*) tranche, mais il n'arrive **qu'apres
un echec** : au moment ou l'enfant choisit, il n'a que l'enonce.

**Pourquoi je traite cet item comme un motif et non comme une reserve, et pourquoi c'est symetrique
avec l'autre lot du meme sprint.** J'ai refuse `cp-syllabes` aujourd'hui pour **un** item admettant
deux reponses defendables (`CHEMINEE`). Le defaut est ici de la meme famille : un item qui dit a un
enfant qui a raison qu'il a tort. **Trancher autrement entre le lot de `marc-dev` et celui de
`lea-dev`, le meme jour, serait deplacer mes poteaux entre deux lots du meme sprint.** Je ne le fais
pas.

**Ce qui repare** : ajouter a l'enonce le seul indice de sens qui manque, sans toucher aux
propositions - par exemple ce que Tom fait ensuite, ou pourquoi. **Je ne dicte pas la formule** :
la barre est qu'un seul des trois mots reste defendable.

**Item voisin, et je le distingue expressement plutot que de l'englober** : l'item 11
(`Tom ___ la lumiere avant de dormir.`, `eteint`/`allume`/`casse`) porte le meme risque a un degre
moindre - *"allume la lumiere avant de dormir"* se defend (une veilleuse), mais *"avant de dormir"*
oriente reellement vers `eteint` pour la plupart des lecteurs, la ou *"La nuit"* n'oriente vers
rien. **Item 11 = reserve `PRE-9`, item 6 = motif.** La distinction est celle-ci et pas une autre.

### Motif 2 - `PRE-2` echoue sur les items 3 et 4, qui sont le defaut meme pour lequel ce paquet a ete ecrit

`PRE-2` : *"Au moins deux des trois propositions appartiennent au meme champ que la bonne reponse,
de sorte que le choix se fasse sur la precision et non sur l'absurdite."*

| Item | Propositions | Mon jugement |
|---|---|---|
| 3 - `Lea dessine avec un ___.` | **crayon** / marteau / couteau | **ECHEC.** Le champ de la reponse est *"instrument pour tracer"* ; **zero** distracteur y appartient. Un marteau et un couteau s'eliminent par incompatibilite grossiere, sans aucun vocabulaire. Le triplet qui aurait travaille la competence existe et il est evident : `crayon`/`feutre`/`pinceau` |
| 4 - `Sous la pluie, Tom prend son ___.` | **parapluie** / ballon / tapis | **ECHEC.** Aucun champ commun. Et l'enonce **donne la reponse par la forme du mot** : *pluie* est dans l'enonce, *para-pluie* dans la proposition. L'enfant n'a besoin ni de vocabulaire ni de sens |

**Ce n'est pas un detail de gout : c'est exactement le defaut que j'ai chiffre le 2026-08-27 sur le
fichier repris**, ou j'ai juge **0 distracteur sur 15** reutilisable au motif qu'ils *"s'eliminent
tous soit par incompatibilite grossiere de sens, soit par la grammaire"*. Le paquet a ete
entierement reecrit pour fermer ce defaut, et **il rouvre sur deux items**. Un distracteur absurde
transforme un exercice en devinette ; c'est un principe que je tiens depuis le debut de ce produit,
et je ne peux pas le tenir contre `main` et pas contre `round-two`.

**Compte, avec sa methode et sa date.** Les seize items relus a la main le 2026-08-27, un par un, en
essayant de defendre chaque distracteur. **`PRE-2` tient sur 11 items sur 16**, echoue sur 2 (items
3 et 4) et est faible sur 3 (items 1, 2 et 9, detaillees en `PRE-10`). **Ce n'est pas un chiffre
mesure : c'est un comptage de mes propres jugements**, et chacun est contestable separement -
c'est pourquoi je les ecris un par un plutot que de rendre un total.

**Ce qui repare les items 3 et 4** : remplacer les deux distracteurs de l'item 3 par deux
instruments pour tracer ; pour l'item 4, soit changer les distracteurs pour deux objets qu'on prend
en sortant, soit changer l'enonce pour qu'il ne contienne pas *pluie*. **Je ne dicte ni l'un ni
l'autre.**

## 4. Le pli : mesure sur le paquet entier, pas sur un echantillon

Mesure du **2026-08-27**, a **744 x 551**, commande citee en section 1, releve
`squad/conformite/releves-s21/s21-nadia2-mot-manquant-paquet-entier-744x551.json`. **Population :
les 16 items du paquet, tous vus, en 5 parties. Ce n'est pas un echantillon.**

| Grandeur lue, apres un echec | Pire valeur sur les 16 items |
|---|---|
| Rangees de propositions | **1** |
| Lignes de l'enonce | **1** |
| Lignes de l'indice | **1** |
| Somme des trois libelles | **364,54 px** sur un budget de 522 px, soit **70 %** (`cuillere`/`fourchette`/`brosse`) |
| Bas de l'indice | **486 px**, soit **65 px au-dessus du pli de 551** |
| Defilement | **0** |

**16 items sur 16 sont entierement au-dessus du pli, verdict et indice compris.** Le travail de
`lea-dev` sur ce point a porte : elle avait trouve et corrige un item a deux lignes puis remesure a
zero, et **je le confirme moi-meme sur le paquet entier**.

**Deux estimations a moi qui etaient fausses, et je les corrige parce qu'elles ont ete ecrites.**

1. **J'avais projete que l'indice de l'item 1** (*"C'est un objet a long manche, avec des poils au
   bout, pour nettoyer le sol."*, 74 caracteres) **tomberait sur deux lignes.** **Il tient sur une**
   (mesure du 2026-08-27, `lignesIndice: 1` sur les 16 items). Mon extrapolation en caracteres etait
   trop pessimiste - ce qui redit ce que j'avais moi-meme ecrit en section 2.3 de l'arbitrage :
   **c'est le chiffre en pixels qui fait foi, et le comptage de caracteres n'est qu'un ordre de
   grandeur.** Je m'etais appuyee sur mon propre ordre de grandeur au lieu de mesurer.
2. **L'enonce le plus long du paquet** (*"Le cafe est trop ___ pour le boire tout de suite."*,
   49 caracteres) que j'avais annonce a la limite de la borne **tient sur une ligne**, comme les
   quinze autres.

## 5. Reserves ouvertes a l'audit - **nouvelles et datees**, pas un durcissement

Aucune n'entre dans les motifs du refus. Elles sont pour `camille-po`, au moment de faire du
backlog.

- **`PRE-9` (2026-08-28) - l'item 11 est au bord de `PRE-3`.** *"Tom ___ la lumiere avant de
  dormir."* : *"allume"* se defend pour une veilleuse. `avant de dormir` oriente reellement, la ou
  l'item 6 n'oriente pas - c'est pourquoi celui-ci est une reserve et l'autre un motif. A verifier
  au moment de la reprise, pendant qu'on y est.
- **`PRE-10` (2026-08-28) - trois items ou `PRE-2` est faible sans echouer.** Item 1
  (`balai`/`marteau`/`tournevis` : trois outils a manche, mais on ne balaie ni avec un marteau ni
  avec un tournevis) ; item 2 (`savon`/`sable`/`sel` : trois matieres qu'on prend avec `du`, mais on
  ne se lave ni au sable ni au sel - l'alliteration en `s` est jolie et n'est pas un champ
  semantique) ; item 9 (`range`/`lave`/`coupe` : *"coupe sa chambre"* n'est pas du francais). Ces
  trois-la fonctionnent, mais le choix s'y fait plus par elimination que par precision.
- **`PRE-11` (2026-08-28) - l'enonce de l'item 1 donne la reponse par la forme du mot.**
  *"Pour **balayer** la cuisine, Maman prend un **balai**."* Meme mecanisme que l'item 4, moins
  grave parce qu'il reste un lien de sens a faire. **Aucune de mes conditions ne couvrait la fuite
  morphologique dans l'enonce** : `PRE-7` ne parle que de l'indice. **C'est un manque de ma
  specification, pas une faute de `lea-dev`**, et c'est pourquoi seul l'item 4 - ou la fuite est
  totale et se cumule avec un echec de `PRE-2` - entre dans les motifs.
- **`PRE-12` (2026-08-28) - deux items portent le meme triplet.** Les items 13 (`Le citron a un gout
  tres ___.`) et 15 (`Le miel a un gout tres ___.`) presentent **exactement** les trois memes
  boutons `acide`/`sucre`/`sale`, sur le meme patron de phrase. Une partie tire 10 manches sans
  remise parmi 16 items : la probabilite que **les deux** sortent dans la meme partie est
  (10 x 9) / (16 x 15) = **0,375**, soit plus d'une partie sur trois - et j'ai vu le cas en jouant
  (manches 5 et 9). Le second des deux n'est alors plus un exercice de vocabulaire. **Aucune de mes
  conditions ne l'interdisait** (`GRA-5` existe pour `cp-comparer`, je n'avais pas pose
  d'equivalent ici) : reserve nouvelle, pas un motif.
- **`PRE-13` (2026-08-28) - `sale` est distracteur trois fois et bonne reponse zero fois.**
  Comptage sur le fichier servi, 2026-08-27 : `sale` apparait dans les items 13, 15 et 16, toujours
  comme distracteur. Un enfant qui joue plusieurs parties peut apprendre a l'ecarter sans le lire.
  Meme famille que `SEG-6` et `REP-4`, que je ne deplace pas : je la **signale** ici, je ne la
  transforme pas en condition retroactive.
- **`PRE-14` (2026-08-28) - la seule adulte du paquet est une mere qui balaie, et aucun garcon ne
  fait de tache menagere.** Comptage sur les seize items : `Lea` porte 4 items (se laver les mains,
  dessiner, manger la soupe, **ranger sa chambre**), `Tom` en porte 3 (parapluie, lampe, lumiere),
  `Maman` 1 (**balayer la cuisine**). **Aucun `Papa`, aucun personnage adulte masculin, et les deux
  seules taches menageres du paquet sont faites par les deux personnages feminins.** Pris item par
  item, aucun n'est fautif ; pris ensemble sur seize, le motif est visible. C'est une verification
  de mon ressort (biais de genre et de configuration familiale) et elle n'etait couverte par aucune
  de mes conditions prealables - **c'est un manque de ma specification**. Reserve, corrigeable en
  changeant deux prenoms.

## 6. Sur quelles proprietes je reste la seule garde

- **Huit conditions, dont une seule partiellement tenue par un test.** La regle 1 du contrat de
  forme (`S21-5`) couvre la **clause de nombre** de `PRE-6`, et rien d'autre. **`PRE-1` a `PRE-5`,
  `PRE-7` et `PRE-8` n'ont aucun garde-fou et ne doivent pas en avoir** : un classifieur qui
  deciderait tout seul si un distracteur est plausible se tromperait, et une regle qui passe pour
  une mauvaise raison est pire qu'une regle absente. **La contrepartie est que j'en suis le seul
  garde-fou**, et ce refus le montre : la porte technique de s21 est verte a 722 tests, 0 echec, et
  elle n'a rien vu des deux motifs de la section 3.
- **La plausibilite d'un distracteur et l'unicite de la reponse defendable.** Personne d'autre ne
  les relit. Ce sont les deux proprietes sur lesquelles ce paquet echoue aujourd'hui.
- **Les biais de genre, d'origine, de milieu et de configuration familiale** (`PRE-14`). Rien dans
  le depot ne les regarde.
- **Le pli a 744 x 551**, hors `mvn verify`.

## 7. Ce qui appartient a d'autres, et que je ne touche pas

- **Le retrait de l'entree `bandit-manchot-mot-manquant-cp` du catalogue**, attache a un `REFUSE`
  par `DEC-0045` section 3 : **le geste appartient a `camille-po` et `sofia-sm`, pas a moi.** Je n'ai
  touche ni `catalogue.yaml`, ni aucun fichier de contenu, et je n'ai rien pousse ni fusionne.
- **La reecriture des items.** A `lea-dev`, par une story de `camille-po`.
- **La ligne 496 de `MATRICE-couverture.md`** reste **`non couvert`** et ne bouge pas aujourd'hui.

---

## Verdict du 2026-08-28 (s21) - `S21-10`, "Le mot qui manque"

## Verdict : **REFUSE**

**Motif 1 - `PRE-3`.** L'item 6, *"La nuit, Tom allume sa ___."* (`lampe` attendu, `radio` et
`tele` en distracteurs), **a plus d'une reponse defendable** : rien dans l'enonce ne discrimine, et
*"La nuit, Tom allume sa radio"* est une phrase vraie et idiomatique. Verifie en jouant le
2026-08-27 : l'appui sur `radio` rend *"Ce n'est pas ca. Essaie encore !"*. **Correction demandee**
: ajouter a l'enonce le seul element de sens qui manque, sans toucher aux propositions - la barre
est qu'un seul des trois mots reste defendable.

**Motif 2 - `PRE-2`.** Les items 3 (*"Lea dessine avec un ___."*, `crayon`/marteau/couteau) et 4
(*"Sous la pluie, Tom prend son ___."*, `parapluie`/ballon/tapis) **ne portent aucun distracteur du
champ de la bonne reponse** : les deux s'eliminent par incompatibilite grossiere, sans vocabulaire.
C'est exactement le defaut pour lequel j'ai juge **0 distracteur sur 15** non reutilisable dans le
fichier repris, et pour lequel ce paquet a ete entierement reecrit. L'item 4 y ajoute une fuite
morphologique : *pluie* est dans l'enonce, *para-pluie* dans la proposition. **Correction demandee**
: deux distracteurs du champ *"instrument pour tracer"* sur l'item 3 ; sur l'item 4, deux
distracteurs du champ de la reponse **ou** un enonce qui ne contient pas *pluie*.

**Aucune condition nouvelle n'est posee.** `PRE-1` a `PRE-8` restent la specification ; les reserves
`PRE-9` a `PRE-14` sont nouvelles, datees, et **ne sont pas des motifs**.

**Ce que j'ai verifie et qui tient**, pour que la reprise ne le jette pas : `PRE-1` sur 16 items sur
16 (aucun item ne s'elimine par la grammaire - le defaut du fichier repris est ferme) ; `PRE-4`
(6 objets, 5 actions, 5 sensations, aucune famille sous le quart) ; `PRE-5`, `PRE-6`, `PRE-7` et
`PRE-8` sur 16 sur 16, dont **aucun indice qui nomme la reponse** (`M21` absent) ; **les cinq items
de sensations sont le meilleur travail de vocabulaire du catalogue CP** et les cinq items d'actions
tiennent ; **les 16 items sur 16 sont entierement au-dessus du pli** a 744 x 551, indice compris,
65 px de marge, zero defilement.

**Ce que je dis a `sofia-sm` et a `camille-po` sur la severite de ce verdict** : j'avais annonce
avant l'ecriture que ce paquet serait *"le plus fragile a l'audit des trois"*, parce que la qualite
d'un distracteur ne se mesure avec aucun chiffre. **Elle ne se mesure toujours pas** : les deux
motifs ci-dessus sont des jugements, item par item, et chacun est contestable separement. Ce que je
peux dire avec certitude, c'est que **onze items sur seize tiennent `PRE-2`**, ce qui est bien plus
que ce que le fichier repris offrait, et que **deux items le rouvrent**.

**Ce que ce refus ne fait pas** : il ne bloque pas le sprint suivant. Il renvoie l'increment a
`camille-po`, qui en fait du backlog.

- **Auditrice** : `nadia-prof`. **Verdict prononce le** 2026-08-28. **Mesures et parties** :
  2026-08-27, entre 22:02 et 22:35. **Arbre audite** : `round-two` a `8e21613`, reconstruit, servi
  et joue depuis le worktree `s21-nadia-2`.
- **Audit non degrade** : l'application a ete construite, servie et **jouee**.
