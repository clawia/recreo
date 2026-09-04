# Fiche de conformite - bandit-manchot-corps-sens-cp

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-qlm-corps-sens.yaml`.
- **Entree de catalogue** : `bandit-manchot-corps-sens-cp`, titre servi *"Le corps et les sens"*, `nombreDeManches: 10` depuis s28 (`7` a s27).
- **Statut global s28** : **VALIDE SOUS RESERVE** (Done pedagogique **prononce le 2026-09-04** sur l'increment de s28). **La ligne `QLM-CP-03` ferme** : elle passe a `couvert-valide`. Une reserve nommee et datee, `COR-R4`, a corriger dans un sprint suivant (section 4 du second passage).
- **Statut s27, conserve tel quel** : **VALIDE SOUS RESERVE** (Done pedagogique **prononce**, 2026-09-03). **Une reserve nommee, a corriger dans un sprint suivant** : un item sur huit est trop long pour un CP et emploie un mot hors niveau (section 4). Ligne de matrice `non couvert` -> **`en cours`** - premier paquet d'une matiere neuve, la ligne ne se ferme pas.
- **Arbitrage prealable de `nadia-prof`** : `ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md`, sections 4 (ligne 3) et 7.1.
- **Derniere mise a jour** : 2026-09-04 (**second verdict de `nadia-prof` apres avoir rejoue**, s28).

## Competence visee

`Nommer les principales parties du corps humain et les cinq sens.`
(Questionner le monde, CP, Le vivant, la matière et les objets).

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

### Conditions de l'audit

**J'ai joue.** Arbre `round-two` a `d259b5f`, jar reconstruit et lance, parties reelles par appels
HTTP contre l'application, **y compris en me trompant volontairement**. Ce n'est pas un audit
degrade.

### 1. Exactitude et couverture - huit items sur huit

**Les cinq sens y sont tous les cinq**, ce que la ligne exige nommement : entendre -> les
oreilles, voir -> les yeux, sentir -> le nez, gouter -> la bouche, toucher -> les mains. Plus
trois parties du corps : marcher -> les jambes, bout du bras -> la main, protege le cerveau -> la
tete. **Aucune erreur de fait.**

**Les distracteurs sont bons** : ce sont toujours d'autres organes des sens ou d'autres parties du
corps, jamais un mot pris ailleurs. Un enfant qui se trompe apprend quelque chose en se trompant.

### 2. Joue jusqu'au bout

Partie complete, **7 manches**, ecran de fin atteint. Erreurs volontaires sur plusieurs items :
l'indice s'affiche et **il decrit sans nommer** (*"c'est ce qui depasse de chaque cote de la
tete"*, *"elle a cinq doigts"*). **Aucun indice ne donne la reponse.** Tres bien vu.

### 3. Ce que ce paquet fait bien et que je tiens a nommer

`BIA-4` respectee sans avoir eu a etre contournee : **aucun item ne parle du corps de l'enfant
lui-meme**, ni de son apparence, ni de ce qu'il sait faire. Sur une ligne qui porte sur le corps,
c'est le piege evident, et il est evite.

### 4. **La reserve, et elle est precise**

- **`COR-R1` (reserve nommee, a corriger)** - l'item *"Avec quoi touche-t-on pour savoir si un
  objet est doux ou rugueux ?"* est **trop long et emploie un mot hors niveau**.

  **Mesure, pas impression** : les huit enonces de ce paquet font **3, 3, 5, 5, 7, 7, 9 et 12
  mots**. **Celui-ci est le plus long des huit, a plus du double de la mediane (5).** Et
  ***rugueux* n'est pas un mot du vocabulaire de CP** - c'est un mot de CE2, employe ici dans une
  subordonnee qui allonge encore la phrase.

  **Pourquoi c'est une reserve et non un refus, et j'applique le meme test qu'a l'histoire de
  CM1** : le mot difficile **n'est pas porteur de la reponse**. Un enfant qui bute sur *rugueux*
  lit quand meme *"avec quoi touche-t-on"* et repond *les mains*. C'est le raisonnement exact par
  lequel j'ai laisse passer *ceremonial* en CM1 le 2026-09-03 ; **je dois le tenir dans les deux
  sens.** Ce qui reste, et qui est reel, c'est la **longueur** : douze mots a lire pour un enfant
  de six ans, quand les sept autres items en demandent cinq.

  **Correction proposee, qui garde l'item et la competence** : *"Avec quoi sait-on si c'est
  doux ?"* - six mots, alignes sur le reste du paquet, sans perdre le sens du toucher.

### 5. Reserves non bloquantes

- **`COR-R2` - *gouter -> la bouche*.** Ce n'est pas faux, et je ne le compte pas comme une
  erreur. Mais **l'organe du gout est la langue**, et c'est le mot que les manuels de cycle 2
  emploient pour ce sens. *La bouche* est acceptable, *la langue* serait juste. A regarder si
  l'item est retouche.
- **`COR-R3`** - meme reserve que `VIV-R1` sur `nombreDeManches: 7` sans decision ecrite.


---

## Second passage - increment de s28 (S28-1, `ad522a2`, corrige par `ce3059b`) - **VALIDE SOUS RESERVE**

- **Verdict** : **VALIDE SOUS RESERVE**, prononce le **2026-09-04** par `nadia-prof`, apres avoir
  joue. **Done pedagogique ouvert.**
- **Consequence de matrice** : **`QLM-CP-03` ferme.** `camille-po` peut passer la ligne a
  `couvert-valide`. **Aucune mention de couverture partielle n'est due sur cette ligne** - je le dis
  explicitement, parce que j'en exige sur d'autres.
- **Reserve nommee, non bloquante, a traiter a s29** : `COR-R4`, section 4. `COR-R2` de s27 reste
  ouverte, inchangee.

### Conditions de l'audit

**J'ai joue.** Arbre `round-two` a `aac5d88`, jar reconstruit et lance, **partie complete de dix
manches par appels HTTP reels**, y compris en me trompant deux fois de suite sur le meme item pour
voir ce que l'enfant recoit. **Et j'ai lu les quatorze items un par un**, parce qu'une partie de dix
manches n'en montre jamais quatre. **Ce n'est pas un audit degrade.**

### 1. `FER-1` - la moitie faible du libelle est payee, et l'item que j'avais demande est la

La ligne a deux moities. *"Les cinq sens"* etait deja servi et valide a s27. *"Les principales
parties du corps"* ne tenait que sur **trois** items ; elle en porte maintenant **neuf** : jambes,
main, tete, **bras, pieds, dos, ventre, genou** et **la peau**. Les cinq parties que j'avais nommees
comme *"n'existant qu'en distracteur"* sont desormais en **bonne reponse**. **Tenue, au mot pres de
ce que j'avais demande.**

**L'item de la peau existe et il fait ce que je voulais** : *"Elle recouvre tout le corps, pas
seulement les mains."* Un enfant ne ressort plus de ce jeu en croyant qu'on ne touche qu'avec les
mains. **C'est exactement la demande du 2026-09-03, et elle est servie.**

### 2. `FER-2` et `FER-3`

- **`FER-2`** : **14 items**, contre un plancher de dix. Tenue.
- **`FER-3`** : deux taches, et je les distingue sur l'**operation mentale**, comme ma clause
  l'exige. *Associer une fonction sensorielle a son organe* (cinq items, *"Avec quoi entend-on ?"*)
  n'est pas *retrouver le nom d'une partie du corps a partir de sa position* (neuf items, *"Quelle
  partie du corps est au bout de la jambe ?"*). La moins servie porte cinq items, au-dessus de mon
  plancher de trois. **Tenue.**

### 3. Exactitude, distracteurs, biais - quatorze items lus un par un

**Aucune erreur de fait sur les quatorze.** Les six items neufs sont justes : bras entre l'epaule et
la main, pieds au bout de la jambe, dos derriere entre epaules et jambes, ventre devant entre
poitrine et jambes, genou au milieu de la jambe, peau sur tout le corps.

**Les distracteurs restent bons** : toujours une autre partie du corps ou un autre organe, jamais un
mot venu d'ailleurs. **Le couple *cheveux / ongles* de l'item de la peau est le meilleur du
paquet** : ce sont precisement les deux parties du corps qui ne sentent rien, et l'enfant qui s'y
trompe apprend quelque chose en se trompant.

**`BIA-4` toujours respectee** : aucun item ne parle du corps de l'enfant lui-meme, de son
apparence ni de ce qu'il sait faire. Sur une ligne qui porte sur le corps, c'est le piege evident et
il reste evite a quatorze items comme il l'etait a huit.

**Joue jusqu'au bout, dix manches, ecran de fin atteint.** Deux erreurs volontaires sur le meme item
(*"Quelle partie du corps est devant, entre la poitrine et les jambes ?"*) : l'enfant reste sur
l'item, recoit *"Ce n'est pas ca. Essaie encore !"* et l'indice *"Il gargouille quand on a faim."*
**L'indice decrit sans nommer**, comme a s27. Un enfant bloque peut toujours avancer.

### 4. **`COR-R4` - la reserve, et elle porte sur l'item que j'ai moi-meme commande**

- **`COR-R4` (nouvelle, datee du 2026-09-04, non bloquante)** - l'item de la peau est **le plus long
  du paquet, a plus du double de la mediane**.

  **Mesure, pas impression** : les quatorze enonces vont de **4 a 19 mots**, mediane **9**. L'item
  *"Quelle partie du corps permet de sentir le chaud, le froid ou le doux, partout sur le corps ?"*
  en fait **19**, et il repete *"corps"* deux fois. C'est **le meme defaut que `COR-R1`**, que j'ai
  fait corriger a s27 sur un item de douze mots contre une mediane de cinq.

  **Pourquoi c'est une reserve et non un refus, et je tiens mon precedent dans les deux sens** :
  `COR-R1`, plus court que celui-ci, je l'ai declare **non bloquant** le 2026-09-03. Le motif que
  j'avais donne etait que le mot difficile ne portait pas la reponse. **Ici il n'y a aucun mot
  difficile** : *chaud*, *froid*, *doux*, *partout*, *corps* sont tous du vocabulaire de CP, et
  l'enfant qui lit la phrase la comprend. Ce qui reste est **la longueur seule**, sur un enfant qui
  lit peu et lentement.

  **Et je nomme la cause honnetement** : cet item existe parce que **je l'ai demande**, et je ne
  l'ai pas assorti d'une contrainte de longueur alors que je venais d'en imposer une sur le meme
  paquet. La reserve est donc une correction de ma commande autant qu'une correction du contenu.

  **Correction fournie mot pour mot** (regime du mot pour mot, 0,25 a s29) - meme reponse, memes
  distracteurs, meme indice :

  > `enonce` : *"Quelle partie du corps sent le chaud et le froid partout ?"*

  Onze mots, dans le profil du paquet, *"corps"* une seule fois. **Ce que la formule perd** - la
  mention du *doux* - **est deja servie par l'item des mains**, et l'indice inchange (*"Elle
  recouvre tout le corps, pas seulement les mains"*) porte toujours le point que je voulais.
  L'orthographe, l'accentuation et la ponctuation appartiennent au `relecteur-pedagogique`.

### 5. Reserves anterieures - etat au 2026-09-04

- **`COR-R1`** (item du toucher trop long, *rugueux*) : **levee le 2026-09-03**, correction reprise
  mot pour mot. Rien a rouvrir.
- **`COR-R2`** (*gouter -> la bouche*, quand l'organe du gout est la langue) : **toujours ouverte,
  inchangee, toujours non bloquante**. L'item n'a pas ete retouche a s28, ce qui est legitime : je
  l'avais ecrite *"a regarder si l'item est retouche"*. Je ne la durcis pas.
- **`COR-R3`** (`nombreDeManches: 7` sans decision ecrite) : **levee**. La valeur repasse a `10`,
  le defaut de `DEC-0064`, et le motif de `DEC-0073` ne s'applique plus a quatorze items.

### 6. Une chose que je dois a `yanis-qa`, et qui ne change pas mon verdict

Son rapport a trouve que ce paquet etait passe de 8 a 14 items **sans qu'aucun test ne suive**,
quand ses deux paquets freres du meme commit en avaient recu : `FER-1`, `FER-2` et `FER-3` y
etaient **affirmes par un message de commit et gardes par rien**. `lea-dev` l'a paye dans `ce3059b`,
avec un test qui mord specifiquement l'item de la peau.

**Ce que cela change pour moi : rien.** Mon verdict porte sur le contenu que j'ai lu et joue, pas
sur l'existence d'un test. **Ce que cela change pour l'equipe : tout** - sans ce rattrapage, la
seule chose qui aurait tenu ces trois conditions sur cette ligne aurait ete **ma lecture**, et je
ne suis pas dans la piece au sprint suivant.
