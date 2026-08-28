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
- **Statut global** : **VALIDE SOUS RESERVE par `nadia-prof` le 2026-08-28**, au second reaudit
  (correction `4543d8d` de `lea-dev`). **Le paquet a ete REFUSE deux fois le meme jour** : d'abord
  sur deux motifs et trois items, puis sur l'item 3 seul. **Les trois items sont maintenant
  corriges et verifies proposition par proposition ; tous les motifs sont fermes.** Sept reserves
  restent ouvertes, aucune bloquante. **La cause racine des deux derniers tours etait dans ma
  propre specification**, pas dans le travail de `lea-dev` : le triplet `crayon`/`feutre`/`pinceau`
  que j'avais qualifie d'*"evident"*, et l'absence de toute condition de lisibilite sur ce paquet.
  **Les trois verdicts sont conserves ci-dessous, dates** : un refus efface n'a aucune valeur de
  preuve.
- **Derniere mise a jour** : 2026-08-28 (`nadia-prof`, second reaudit, correction `4543d8d`).

## Table de correspondance des jetons de cette fiche

Convention exigee par `squad/README.md`, section 2, amendement s16.

| Jeton | Ce qu'il designe | Ou il est ecrit |
|---|---|---|
| `PRE-1` a `PRE-8` | **Conditions prealables** a l'ecriture, posees le 2026-08-27 **avant** que le paquet existe | `ARBITRAGE-PREALABLE-s21-trois-paquets-de-volume-cp.md`, section 4.2 |
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

---

## Reaudit du 2026-08-28 (s21) - `S21-10` corrige par `lea-dev`, commit `fabd6cb`

**Ce bloc ne remplace pas le verdict ci-dessus, il s'y ajoute.** Le premier refus, ses deux motifs
et ce que j'y avais tenu pour bon restent lisibles tels quels.

### 1. Ce que j'ai fait, et sur quel artefact

**J'ai joue, puis j'ai mesure.** Je n'ai pas lu le compte rendu de `lea-dev` pour en tirer un
verdict : j'ai construit son arbre et presse ses boutons. C'est un paquet dont j'avais ecrit qu'il
serait le plus fragile des trois a l'audit, *"parce que la qualite d'un distracteur ne se mesure
avec aucun chiffre"*, et les trois formulations neuves n'avaient jamais ete devant moi.

- **Arbre audite** : commit `fabd6cb`, reparti de `round-two` a `8e21613`. Verifie moi-meme :
  **un seul fichier touche** (`cp-mot-manquant.yaml`, blob `6b3ef9c`), 22 insertions et 4
  suppressions, **aucun test Java, `catalogue.yaml` intact**. Worktree detache dedie, arbre propre.
- **Construit et servi** depuis cet arbre (`bash outils/mvn-jdk21.sh -B -q clean package
  -DskipTests`, puis `java -jar bootstrap/target/recreo.jar --server.port=<port>` sur un JDK 21).
  **Serveur arrete par son identifiant de processus**, apres avoir verifie que le processus etait
  bien le mien.
- **Parties jouees le 2026-08-28**, par l'interface HTTP, en me trompant volontairement ; puis,
  sur les trois items corriges, **j'ai presse chacune des trois propositions** pour lire ce que
  l'application repond a un enfant qui choisit une reponse defendable.

### 2. Les trois items corriges, presses proposition par proposition le 2026-08-28

| Item | Ce que l'enfant lit | Proposition pressee | Verdict rendu |
|---|---|---|---|
| **6** | *"Pour lire le soir, Tom allume sa ___."* | `lampe` | **Bravo !** |
| | | `tele` | *"Ce n'est pas ca."* |
| | | `radio` | *"Ce n'est pas ca."* |
| **4** | *"Tom ouvre son ___ au-dessus de la tete."* | `parapluie` | **Bravo !** |
| | | `capuchon` | *"Ce n'est pas ca."* |
| | | `impermeable` | *"Ce n'est pas ca."* |
| **3** | *"Lea dessine avec un ___."* | `crayon` | **Bravo !** |
| | | `feutre` | ***"Ce n'est pas ca."*** |
| | | `pinceau` | ***"Ce n'est pas ca."*** |

### 3. Motif 1 (`PRE-3`, item 6) : **FERME**

*"Pour lire le soir"* apporte exactement l'element de sens qui manquait. **Lire demande de la
lumiere**, pas un son ni une image : *"Pour lire le soir, Tom allume sa radio"* et *"...sa tele"*
ne sont plus defendables, et `lampe` l'est seule. Les trois propositions n'ont pas bouge, donc
`PRE-2` reste tenue. **C'est la correction exacte que j'avais demandee, dans la forme que j'avais
demandee** : l'enonce porte le trait discriminant, les propositions restent du meme champ.

### 4. Motif 2 (`PRE-2`), item 4 : **FERME, et bien ferme**

`impermeable` et `capuchon` sont **du champ de la bonne reponse** - trois equipements de pluie.
`PRE-2` est tenue, et mieux que par le triplet que j'avais en tete.

**Sur la question qui m'est posee - un enfant de CP connait-il le trait qui tranche ? Oui.**
Le trait est *"on l'**ouvre** au-dessus de la tete"*. Un parapluie **s'ouvre** : c'est son geste
propre, celui que tout enfant de six ans a vu et fait. Un capuchon **se met** ou **se rabat**, il
ne s'ouvre pas. Un impermeable **s'enfile** ; *"ouvrir son impermeable"* signifie le deboutonner,
jamais le tenir au-dessus de la tete. **Le trait est connu d'un enfant de CP, et il est le seul a
tenir sur les trois.** `PRE-3` est tenue.

**`lea-dev` a en outre ferme une fuite que mon refus ne comptait pas comme motif** : l'ancien
enonce contenait *pluie* et la bonne reponse etait *para-pluie*. C'etait ma reserve **`PRE-11`**,
que je **ferme ici**. Elle est allee plus loin que ce que je demandais, et dans la bonne direction.

### 5. Motif 2 (`PRE-2`), item 3 : `PRE-2` est ferme, mais l'item echoue desormais `PRE-3`

`feutre` et `pinceau` sont bien du champ *"instrument pour tracer"* : **`PRE-2` est tenue.**

**Mais l'enonce n'a pas bouge, et il ne discrimine rien.** *"Lea dessine avec un feutre"* et
*"Lea dessine avec un pinceau"* sont deux phrases francaises vraies et ordinaires. **Un enfant de
CP dessine au feutre tous les jours.** Presse a l'ecran le 2026-08-28 : `feutre` rend *"Ce n'est
pas ca. Essaie encore !"*, `pinceau` aussi.

**L'item avait deux reponses defendables sur trois avant correction ; il en a trois sur trois
apres.** L'indice (*"C'est un petit baton de bois..."*) tranche, mais il n'arrive **qu'apres un
echec** : au moment de choisir, l'enfant n'a que l'enonce.

C'est **`PRE-3`, mot pour mot**, et c'est le defaut dont j'ai fait le **motif 1** de mon premier
refus, il y a un jour, sur l'item 6. **Je ne peux pas le tenir pour un motif sur l'item 6 et pour
une reserve sur l'item 3**, surtout pas quand ce qui distingue les deux cas est que le second
m'appartient.

### 6. La cause racine est ma specification, pour la troisieme fois en deux sprints

**Le triplet `crayon` / `feutre` / `pinceau` est le mien.** Je l'ai ecrit dans cette fiche au
premier refus, en le qualifiant d'*"evident"* : *"Le triplet qui aurait travaille la competence
existe et il est evident : `crayon`/`feutre`/`pinceau`."* `lea-dev` l'a repris au mot pres, et elle
a eu raison de suivre ma specification. **C'est ma specification qui etait fausse**, exactement
comme a s20, ou elle avait recopie des libelles que j'avais dits courts et qui faisaient 34
caracteres, et comme ce matin, ou `SEG-1` clause (c) etait quatre fois trop large.

**Trois fois en deux sprints, la cause racine d'un refus est dans ce que j'ai ecrit avant, et non
dans le travail que j'encadrais.** Je l'ecris parce que c'est un motif qui doit se compter, et
parce qu'il dit ou est mon defaut : **je propose des exemples au lieu de nommer la propriete a
tenir.** Un exemple se revele faux ; une propriete se verifie.

**Ce que j'aurais du ecrire, et que j'ecris maintenant** - c'est la seule chose a retenir de ce
bloc :

> **`PRE-2` et `PRE-3` tirent en sens inverse, et c'est l'enonce qui les reconcilie.** Plus les
> distracteurs sont du champ de la bonne reponse, moins le sens general de la phrase suffit a
> trancher. **Un item de vocabulaire precis n'est complet que si l'enonce porte le trait qui
> distingue la bonne reponse de ses voisines de champ.** Les items 6 et 4 le font desormais -
> *pour lire*, *on l'ouvre au-dessus de la tete*. L'item 3 ne le fait pas.

**Ce que je demande pour l'item 3, en propriete et non en libelle** : que l'enonce nomme un trait
que **seul le crayon** possede parmi les trois - ce qu'on gomme, ce qu'on taille, ce qui est en
bois. **Je ne propose aucune formulation, et c'est delibere** : mes formulations sont ce qui a fait
echouer ce paquet deux fois. Les distracteurs `feutre` et `pinceau` sont bons et n'ont pas a bouger.

### 7. Ce que je n'ai pas rouvert

Les onze items non touches restent tels que je les ai juges le 2026-08-27 : **`PRE-1` tenue sur 16
sur 16**, `PRE-4` a `PRE-8` tenues, et les cinq items de sensations restent le meilleur travail de
vocabulaire du catalogue CP. **Les reserves `PRE-9`, `PRE-10`, `PRE-12`, `PRE-13` et `PRE-14` sont
inchangees et ne sont toujours pas des motifs.** `PRE-11` est **fermee** (section 4).

Effet de bord favorable, constate au comptage du 2026-08-28 : `marteau` n'est plus distracteur que
sur un item au lieu de deux, ce qui allege `PRE-10` sans que ce fut demande.

### 8. Le pli, remesure par moi sur le paquet entier

Mesure du **2026-08-28**, viewport de reference de `DEC-0040`, **744 x 551 lu du navigateur**,
**population : les 16 items du paquet, tous vus, en 4 parties - ce n'est pas un echantillon** :

```
CHROME=<navigateur> PAQUET=adapters/adapter-content/src/main/resources/contenu/cp-mot-manquant.yaml \
    PORT_DEV=9421 python squad/outils/nadia-prof/auditer-un-paquet-entier.py \
    http://<hote>:<port> bandit-manchot-mot-manquant-cp 16 <json_de_sortie>
```

| Grandeur lue, apres un echec | Pire valeur sur les 16 items |
|---|---|
| Rangees de propositions | **1** |
| Hauteur de l'enonce | **48 px**, soit **une ligne partout** |
| Lignes de l'indice | **1** |
| Bas de l'indice | **486 px**, **65 px au-dessus du pli de 551** |
| Defilement | **0** |
| Somme la plus lourde | **485,22 px** sur 522 (`impermeable` / `capuchon` / `parapluie`), **36,78 px de marge** |

**16 items sur 16 sont au-dessus du pli. Les chiffres de `lea-dev` sont exacts : je les ai
reproduits, je ne les ai pas recopies.** Le pli n'est pas un motif et ne l'a jamais ete sur ce
paquet.

**Sur sa methode, et cela merite d'etre dit** : sa premiere formulation de l'item 4 rendait **deux
lignes** d'enonce (`hauteurEnonce=96` contre 48 ailleurs) et poussait l'indice a 534 px. Elle l'a
trouve **en jouant**, en lisant la **hauteur** et non le nombre de rectangles - le piege etant que
`getClientRects().length` reste a 1 sur un `<p>` de deux lignes. **C'est la bonne facon de mesurer
cette propriete**, et c'est un des rares points de ce chantier ou l'outillage a evite un defaut au
lieu de le laisser passer.

### 9. Reserves neuves, **nouvelles et datees**, non bloquantes

- **`PRE-15` (2026-08-28) - `impermeable` est hors de portee d'un lecteur de CP.** Onze lettres, un
  `im` nasal, un `e` accentue et une finale `-ble` : un enfant de CP ne dechiffre pas ce mot. Il le
  **connait a l'oral**, et l'item reste decidable pour qui sait ce qu'est un impermeable - c'est
  pourquoi ce n'est pas un motif, et c'est aussi ce qui le distingue de `thermos` (`GRA-11`, sur
  `cp-comparer`), inconnu **et** illisible. **Aucune de mes conditions `PRE-1` a `PRE-8` ne porte
  sur la lisibilite des propositions**, contrairement a `SEG-2` pour les syllabes : **c'est encore
  un manque de ma specification**, et je l'ouvre donc en reserve et non en motif.
- **`PRE-16` (2026-08-28) - l'indice de l'item 4 reformule desormais l'enonce.** L'enonce dit *"Tom
  ouvre son ___ au-dessus de la tete."* et l'indice dit *"On l'ouvre au-dessus de la tete quand il
  pleut."* : ils partagent `ouvre`, `au-dessus` et `tete` (comptage du 2026-08-28), et l'indice
  n'ajoute que *"quand il pleut"*. **C'est un effet de la correction**, qui a deplace le trait
  discriminant de l'indice vers l'enonce - le bon geste - sans reecrire l'indice derriere. Meme
  famille que `GRA-16` sur `cp-comparer`. `PRE-7` n'exige que de ne pas nommer la reponse, et c'est
  tenu : reserve, pas motif.

### 10. Ce qui appartient a d'autres, et que je ne touche pas

**Pour `camille-po`, qui instruit le retrait ou le maintien conditionnel de cette entree et qui
attend ma reponse pour trancher** : **le refus est maintenu**, donc `DEC-0045` section 3
s'applique, et **le geste sur `catalogue.yaml` est le sien et celui de `sofia-sm`, pas le mien -
je n'y ai pas touche.** Ce que je peux lui donner pour peser, et qui est de mon ressort :

- **le defaut porte sur 1 item sur 16**, et sur cet item **deux propositions sur trois sont des
  reponses correctes refusees** ;
- une partie tire **10 manches sans remise** parmi 16 items : l'enfant rencontre cet item avec une
  probabilite de **10/16 = 0,625**, soit pres de deux parties sur trois ;
- **les quinze autres items sont bons**, deux des trois corrections sont exactes, et **la cause
  racine du defaut restant est ma specification, pas le travail de `lea-dev`** ;
- **la correction demandee ne touche qu'un enonce** et laisse les distracteurs en place.

Je ne recommande ni le retrait ni le maintien : **ce n'est pas ma decision**, et je ne veux pas
qu'un avis de ma part sur ce point passe pour une condition pedagogique.

---

## Verdict du reaudit, 2026-08-28 - `S21-10`, "Le mot qui manque"

## Verdict : **REFUSE**

**Le premier refus portait deux motifs sur trois items. Deux items sont corriges ; le troisieme ne
l'est pas.**

- **Motif 1 (`PRE-3`, item 6) : FERME.** *"Pour lire le soir"* rend `lampe` seule defendable.
  Verifie en pressant les trois propositions le 2026-08-28.
- **Motif 2 (`PRE-2`), item 4 : FERME**, et il ferme en plus la reserve `PRE-11`. `impermeable` et
  `capuchon` sont du champ de `parapluie`, et le trait qui tranche - *on l'ouvre au-dessus de la
  tete* - **est connu d'un enfant de CP** : un parapluie s'ouvre, un capuchon se rabat, un
  impermeable s'enfile.
- **Motif 2 (`PRE-2`), item 3 : `PRE-2` est ferme, mais l'item echoue maintenant `PRE-3`.**
  L'enonce *"Lea dessine avec un ___."* n'a pas bouge et ne discrimine rien : **`feutre` et
  `pinceau` sont deux reponses correctes, et l'application repond *"Ce n'est pas ca"* aux deux**,
  presse a l'ecran le 2026-08-28. Un enfant de CP dessine au feutre tous les jours.

**Motif unique du present refus : l'item 3 a trois reponses defendables sur trois.** C'est le
defaut exact dont j'ai fait mon motif 1 hier, sur l'item 6. Le tenir pour un motif la et pour une
reserve ici reviendrait a deplacer mes poteaux pour epargner ma propre suggestion.

**Car la cause racine est ma specification, pour la troisieme fois en deux sprints** : le triplet
`crayon`/`feutre`/`pinceau` est celui que j'avais moi-meme qualifie d'*"evident"* dans le verdict
que `lea-dev` corrigeait. Elle l'a repris au mot pres et elle a eu raison de le faire.

**Correction demandee, nommee en propriete et non en libelle** - c'est le changement de methode que
ce refus doit produire chez moi : **que l'enonce de l'item 3 porte un trait que seul le crayon
possede parmi les trois** (ce qu'on gomme, ce qu'on taille, ce qui est en bois). **Je ne propose
aucune formulation.** Les distracteurs `feutre` et `pinceau` sont bons et ne doivent pas bouger.
Rien d'autre n'est demande.

**Regle generale que j'ajoute, et qui explique les trois items d'un coup** : `PRE-2` et `PRE-3`
tirent en sens inverse, et **c'est l'enonce qui les reconcilie**. Un item de vocabulaire precis
n'est complet que si l'enonce porte le trait qui distingue la bonne reponse de ses voisines de
champ. Les items 6 et 4 le font ; l'item 3 ne le fait pas.

**Ce que j'ai verifie et qui tient** : `PRE-1` sur 16 sur 16 ; `PRE-2` desormais tenue sur les
trois items corriges ; `PRE-4` a `PRE-8` tenues ; les onze items non touches inchanges, dont les
cinq items de sensations ; **le pli tenu sur 16 items sur 16**, remesure par moi sur le paquet
entier a 744 x 551 le 2026-08-28 - indice a 486 px, 65 px de marge, zero defilement, **les chiffres
de `lea-dev` reproduits et non recopies**.

**Deux reserves neuves, non bloquantes** : `PRE-15` (`impermeable` hors de portee d'un lecteur de
CP - encore un manque de ma specification, qui ne porte aucune condition de lisibilite sur ce
paquet) et `PRE-16` (l'indice de l'item 4 reformule desormais l'enonce).

**Le retrait ou le maintien conditionnel de l'entree au catalogue appartient a `camille-po` et
`sofia-sm`** : je n'ai touche ni `catalogue.yaml`, ni la matrice, ni aucun fichier de contenu. La
ligne 496 de `MATRICE-couverture.md` reste **`non couvert`**.

- **Auditrice** : `nadia-prof`. **Reaudit et verdict prononces le** 2026-08-28. **Arbre audite** :
  `fabd6cb` (branche `s21-lea-3`, repartie de `round-two` a `8e21613`), construit, servi et **joue**
  depuis un worktree detache dedie.
- **Audit non degrade** : l'application a ete construite, servie et **jouee**, et les trois items
  corriges ont ete presses proposition par proposition.

---

## Second reaudit du 2026-08-28 (s21) - item 3 corrige par `lea-dev`, commit `4543d8d`

**Ce bloc s'ajoute aux deux precedents.** Le premier refus, le premier reaudit et leurs motifs
restent lisibles tels quels.

### 1. Ce que j'ai fait, et sur quel artefact

**J'ai joue, puis j'ai mesure.** Le compte rendu de `lea-dev` ne remplace pas ma main sur les
boutons : c'est en pressant moi-meme que j'ai trouve le defaut au tour precedent.

- **Arbre audite** : commit `4543d8d`, au-dessus de `fabd6cb`, branche `s21-lea-3`. Verifie
  moi-meme : **un seul fichier touche** (`cp-mot-manquant.yaml`), **8 insertions et 1 suppression**,
  dont **une seule ligne de contenu** - le champ `enonce` de l'item 3. Bonne reponse et distracteurs
  inchanges. Worktree detache dedie, arbre propre.
- **Construit et servi** depuis cet arbre (`bash outils/mvn-jdk21.sh -B -q clean package
  -DskipTests`, puis `java -jar bootstrap/target/recreo.jar --server.port=<port>` sur un JDK 21).
  **Serveur arrete par son identifiant de processus**, apres verification que le processus etait
  bien le mien.
- **Joue le 2026-08-28** : les trois propositions de l'item 3 pressees une par une, puis une partie
  complete de dix manches pour verifier que rien d'autre n'a bouge.

### 2. L'item 3, presse proposition par proposition le 2026-08-28

Enonce servi : ***"Avant de dessiner, Lea taille son ___."***

| Proposition pressee | Verdict rendu | Est-elle defendable ? |
|---|---|---|
| `crayon` | **Bravo !** | oui, et elle seule |
| `feutre` | *"Ce n'est pas ca."* | **non** - on ne taille pas un feutre |
| `pinceau` | *"Ce n'est pas ca."* | **non** - on ne taille pas un pinceau |

**Le motif est ferme.** Au tour precedent, `feutre` et `pinceau` etaient refuses alors qu'ils
etaient **corrects** ; ils sont maintenant refuses parce qu'ils sont **faux**. C'est toute la
difference, et c'est celle que `PRE-3` demande.

**`PRE-2` reste tenue** : les trois propositions sont toujours du champ *"instrument pour tracer"*,
puisque les distracteurs n'ont pas bouge. **L'enonce porte desormais le trait discriminant**, ce
qui est exactement la propriete que j'avais demandee - et non un libelle que j'aurais dicte.

**Partie complete jouee le 2026-08-28** : les dix manches se deroulent sans accroc, les accents
sont intacts a l'ecran (`Lea`, `diner`, `herisson`, `lumiere`, `cuillere`, `cafe`, `gout`, `sale`,
`sucre`), et les items 4 et 6 se comportent comme au tour precedent.

### 3. Le mot `taille` : mesure, et non jugement a l'oreille

Ce mot est neuf dans ce paquet et il m'est demande de le juger. **Je viens d'ouvrir `PRE-15` sur
`impermeable` pour un motif de lisibilite ; je dois donc dire pourquoi `taille` ne recoit pas le
meme traitement, et le dire sur une mesure, pas sur mon oreille** - sans quoi je deplacerais mes
poteaux d'un mot a l'autre.

**Mesure du 2026-08-28, population : le corpus CP entier du depot** - 10 paquets CP servis et les
fichiers CP de `contenu-repris`, **29 fichiers, 745 mots distincts** lus par un enfant dans les
champs qu'il voit (enonces, propositions, indices, explications). **Ce n'est pas un echantillon.**

| Mot | Occurrences ailleurs dans le corpus CP | Lettres | Syllabes |
|---|---|---|---|
| **`taille`** | **3 dans `cp-comparer.yaml`** (*"Pense a la taille de chaque animal..."*, deux fois, et *"Compare la taille des trois objets..."*), plus `cp-longueurs.yml` et `cp-geometrie.yml` | 6 | **1** |
| `impermeable` | **aucune** - absent de tout le reste du corpus CP | 11 | **5** |

**Le mot `taille` est deja servi a un enfant de CP par ce produit, dans `cp-comparer`, un paquet que
j'ai valide moi-meme ce matin - et dans le meme registre de lecture, celui d'un indice affiche a
l'ecran.** Objecter aujourd'hui a sa lisibilite reviendrait a refuser un mot que j'ai accepte il y
a quelques heures. **`taille` n'est pas un probleme de lisibilite**, et la mesure le montre sans
que j'aie a en appeler a mon jugement.

**Ce que la meme mesure fait a `PRE-15`** : elle ne la durcit pas, elle **l'appuie**.
`impermeable` est **absent de tout le reste du corpus CP**, deux fois plus long et cinq fois plus
long en syllabes. `PRE-15` reste **une reserve, au meme niveau qu'il y a une heure**, et elle
repose desormais sur un chiffre plutot que sur mon seul avis.

**Sur le sens du mot, qui est l'autre moitie de la question.** `tailler` au sens d'affuter est le
verbe propre du crayon en francais, et tout enfant de CP possede un **taille-crayon** : le sens lui
vient par l'objet avant de lui venir par le verbe. **La polysemie ne mord pas** : `taille` est un
nom apres determinant dans `cp-comparer` (*"la taille de chaque animal"*) et un verbe entre un sujet
propre et un complement ici (*"Lea taille son ___"*). La syntaxe tranche seule, et rencontrer un
homographe dans deux emplois clairs n'est pas un defaut - c'est du vocabulaire.

### 4. Le raisonnement de `lea-dev` sur le choix du trait : il tient, et il va plus loin que ce que je demandais

J'avais nomme trois traits possibles - gommer, tailler, le bois - **en propriete et sans en imposer
aucun**. Elle retient `tailler` au motif que c'est le seul qui **ne recoupe pas l'indice existant**
(*"C'est un petit baton de bois qui laisse une trace sur le papier."*).

**Ce raisonnement tient, et je le verifie trait par trait :**

- **le bois** est **litteralement dans l'indice**. Un enonce bati dessus aurait rendu l'indice
  redondant ;
- **gommer** touche *"laisse une trace sur le papier"* - effacer est l'envers de laisser une trace.
  Le recoupement est semantique, plus faible, mais reel ;
- **tailler** n'apparait ni en toutes lettres ni en substance dans l'indice. **Aucun recoupement.**

**Le trait qu'elle a retenu est le seul des trois qui laisse l'indice apporter quelque chose de
neuf apres un echec.** C'est exactement le defaut que j'ai ouvert il y a une heure sous `PRE-16`,
sur l'item 4, dont l'indice reformule desormais l'enonce - **et elle l'a evite sur l'item 3 sans
que je le lui demande, en generalisant une reserve que je venais d'ecrire sur un autre item.**
Aucun de mes criteres ne le lui imposait. **C'est du bon travail, et c'est le genre de
consideration que je devrais avoir portee moi-meme dans ma demande de correction.**

### 5. Le pli, remesure par moi sur le paquet entier

Mesure du **2026-08-28**, viewport de reference de `DEC-0040`, **744 x 551 lu du navigateur**,
**population : les 16 items du paquet, tous vus, en 4 parties - ce n'est pas un echantillon** :

```
CHROME=<navigateur> PAQUET=adapters/adapter-content/src/main/resources/contenu/cp-mot-manquant.yaml \
    PORT_DEV=9431 python squad/outils/nadia-prof/auditer-un-paquet-entier.py \
    http://<hote>:<port> bandit-manchot-mot-manquant-cp 16 <json_de_sortie>
```

| Grandeur lue, apres un echec | Pire valeur sur les 16 items |
|---|---|
| Rangees de propositions | **1** |
| Hauteur de l'enonce | **48 px**, soit **une ligne partout** |
| Lignes de l'indice | **1** |
| Bas de l'indice | **486 px**, **65 px au-dessus du pli de 551** |
| Defilement | **0** |
| Somme la plus lourde | **485,22 px** sur 522, item 4, **36,78 px de marge** |

**Item 3, celui qui a change** : l'enonce passe de 24 a 38 caracteres et **reste sur une ligne**
(`hauteurEnonce=48`) ; somme des libelles **310,00 px**, soit **212,00 px de marge** ; indice a
486 px ; zero defilement. **L'allongement de l'enonce n'a rien coute au pli.**

**16 items sur 16 sont au-dessus du pli, identique a `fabd6cb`. Les chiffres de `lea-dev` sont
exacts : je les ai reproduits, je ne les ai pas recopies.** Elle a mesure avec la methode que
j'avais documentee - la **hauteur** de l'enonce et non le nombre de rectangles - et c'est la bonne.

### 6. L'etat des reserves

- **`PRE-11` : fermee** au reaudit precedent (fuite morphologique de l'item 4).
- **`PRE-15` et `PRE-16` : inchangees, toujours des reserves.** `lea-dev` ne les a pas touchees,
  comme demande. **Rien n'a change depuis que je les ai ouvertes**, sinon que la mesure de la
  section 3 donne a `PRE-15` un appui chiffre qu'elle n'avait pas. **Je ne les transforme pas en
  motifs.**
- **`PRE-9`, `PRE-10`, `PRE-12`, `PRE-13`, `PRE-14` : inchangees**, non bloquantes.
- **Aucune reserve neuve a ce tour.** J'ai cherche : l'enonce neuf ne cree ni fuite morphologique
  (il ne contient rien de `crayon`), ni recoupement avec son indice, ni charge de lecture nouvelle,
  et il ne coute rien au pli.

### 7. Ce qui appartient a d'autres, et que je ne touche pas

**Pour `camille-po` et `sofia-sm`, explicitement, et sans que j'y touche** : **je leve le refus.
Le motif du retrait de l'entree `bandit-manchot-mot-manquant-cp` tombe.** `DEC-0052` (commit
`ee031cb`) tranchait le retrait au titre de `DEC-0045` section 3, sur le fondement d'un refus qui
n'existe plus : **la decision revient a `camille-po`**, et le geste materiel a `sofia-sm`. **Je n'ai
touche ni `catalogue.yaml`, ni la matrice, ni aucun fichier de contenu.**

**Le geste materiel ayant ete retenu, il n'y a rien a restaurer** : l'entree n'est jamais sortie du
catalogue. Retenir ce geste aura evite de retirer une entree et de la remettre dans l'heure - c'est
exactement le cout d'aller-retour que `DEC-0052` invoquait, et l'attente l'a economise.

**La ligne 496 de `MATRICE-couverture.md`** peut passer a **`couvert-valide`** : le geste est a
`camille-po`, pas a moi.

---

## Verdict du second reaudit, 2026-08-28 - `S21-10`, "Le mot qui manque"

## Verdict : **VALIDE SOUS RESERVE**

**Done pedagogique prononce.** Ce verdict leve le refus du 2026-08-28 et celui de son premier
reaudit. **Les deux refus ne sont pas effaces** : ils restent au-dessus, dates, avec leurs motifs -
un refus efface n'a aucune valeur de preuve, et l'histoire de ce paquet est ce qui rend le present
verdict relisible.

**Le motif unique restant est ferme.** L'enonce de l'item 3 porte desormais le trait discriminant :
*"Avant de dessiner, Lea taille son ___."* On taille un crayon ; on ne taille ni un feutre ni un
pinceau. **Presse a l'ecran le 2026-08-28** : `crayon` rend *"Bravo !"*, `feutre` et `pinceau` sont
refuses - **et cette fois ils sont refuses parce qu'ils sont faux, non parce qu'ils sont corrects.**
C'est toute la difference que `PRE-3` demande. `PRE-2` reste tenue, les distracteurs n'ayant pas
bouge.

**Sur le mot `taille`, juge par la mesure et non a l'oreille** (section 3) : il est **deja servi a
un enfant de CP par ce produit**, trois fois dans `cp-comparer.yaml`, un paquet que **j'ai valide
moi-meme ce matin**, et dans le meme registre de lecture. Six lettres, une syllabe, contre onze
lettres et cinq syllabes pour `impermeable`, qui est **absent de tout le reste du corpus CP**
(745 mots, 29 fichiers, population entiere). **Objecter a `taille` reviendrait a refuser un mot que
j'ai accepte il y a quelques heures.** La meme mesure **appuie `PRE-15` sans la durcir** : elle
reste une reserve, au meme niveau qu'il y a une heure.

**Le raisonnement de `lea-dev` sur le choix du trait tient** (section 4), et il va plus loin que ce
que je demandais : `tailler` est bien le seul des trois traits qui **ne recoupe pas l'indice
existant** - le bois y est en toutes lettres, gommer touche *"laisse une trace"*. **Elle a evite sur
l'item 3 le defaut que je venais d'ouvrir sous `PRE-16` sur l'item 4, sans que je le lui demande et
sans qu'aucun de mes criteres le lui impose.** C'est une consideration que j'aurais du porter
moi-meme dans ma demande de correction.

**Ce que j'ai verifie et qui tient** : `PRE-1` a `PRE-8` tenues sur 16 items sur 16 ; les trois
items corriges au fil des deux reaudits (3, 4, 6) verifies proposition par proposition ; les onze
items non touches inchanges, dont les cinq items de sensations qui restent le meilleur travail de
vocabulaire du catalogue CP ; **le pli tenu sur 16 items sur 16**, remesure par moi sur le paquet
entier a 744 x 551 le 2026-08-28 - une rangee, enonce d'une ligne partout, indice a 486 px, 65 px de
marge, zero defilement -, **les chiffres de `lea-dev` reproduits et non recopies**.

**Reserves ouvertes, aucune bloquante**, a transformer en stories par `camille-po` : `PRE-9`
(l'item 11 est au bord de `PRE-3`), `PRE-10` (trois items ou `PRE-2` est faible sans echouer),
`PRE-12` (les items 13 et 15 portent le meme triplet), `PRE-13` (`sale` est distracteur trois fois
et bonne reponse zero fois), `PRE-14` (la seule adulte du paquet est une mere qui balaie, et aucun
garcon ne fait de tache menagere), `PRE-15` (`impermeable` hors de portee d'un lecteur de CP),
`PRE-16` (l'indice de l'item 4 reformule desormais l'enonce). **`PRE-11` est fermee.** **Aucune
reserve neuve a ce tour** : j'ai cherche.

**Ce que ce paquet aura coute, et a qui l'imputer.** Deux refus et trois tours de correction, dont
**la cause racine des deux derniers etait dans ma propre specification** : le triplet
`crayon`/`feutre`/`pinceau` que j'avais qualifie d'*"evident"*, et l'absence de toute condition de
lisibilite sur ce paquet. `lea-dev` a suivi ma specification a chaque tour, et elle a eu raison de
le faire. **Le travail livre est bon ; ce qui a coute, c'est ce que j'avais ecrit avant.**

**Pour `camille-po` et `sofia-sm`** : **je leve le refus, donc le motif du retrait de `DEC-0052`
tombe**, et la decision revient a `camille-po`. **Je n'ai touche ni `catalogue.yaml`, ni la
matrice.** L'entree n'etant jamais sortie du catalogue, il n'y a rien a restaurer. La ligne 496 de
`MATRICE-couverture.md` peut passer a **`couvert-valide`** : le geste est a `camille-po`.

- **Auditrice** : `nadia-prof`. **Second reaudit et verdict prononces le** 2026-08-28. **Arbre
  audite** : `4543d8d` (branche `s21-lea-3`, au-dessus de `fabd6cb`, repartie de `round-two` a
  `8e21613`), construit, servi et **joue** depuis un worktree detache dedie.
- **Audit non degrade** : l'application a ete construite, servie et **jouee**, l'item corrige a ete
  presse proposition par proposition, et une partie complete de dix manches a ete jouee.
