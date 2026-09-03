# Fiche de conformite - bandit-manchot-corps-sens-cp

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-qlm-corps-sens.yaml`.
- **Entree de catalogue** : `bandit-manchot-corps-sens-cp`, titre servi *"Le corps et les sens"*, `nombreDeManches: 7`.
- **Statut global** : **VALIDE SOUS RESERVE** (Done pedagogique **prononce**, 2026-09-03). **Une reserve nommee, a corriger dans un sprint suivant** : un item sur huit est trop long pour un CP et emploie un mot hors niveau (section 4). Ligne de matrice `non couvert` -> **`en cours`** - premier paquet d'une matiere neuve, la ligne ne se ferme pas.
- **Arbitrage prealable de `nadia-prof`** : `ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md`, sections 4 (ligne 3) et 7.1.
- **Derniere mise a jour** : 2026-09-03 (**verdict de `nadia-prof` apres avoir joue**, s27).

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

