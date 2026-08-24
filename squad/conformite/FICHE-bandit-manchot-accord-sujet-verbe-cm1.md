# Fiche de conformite - bandit-manchot-accord-sujet-verbe-cm1

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du catalogue).
- **Contenu(s)** : `adapters/adapter-content/src/main/resources/contenu/
  cm1-accord-sujet-verbe.yaml` (a creer, `type: question`, au moins 20 items), story `S19-3`
  (`docs/backlog/backlog.md`). **Aucune matiere premiere reutilisable** :
  `contenu-repris/textes-trous/cm1-conjugaison.yml` (6 items) teste le choix de la bonne personne
  conjuguee au present, avec un sujet pronominal immediatement devant le verbe - c'est la matiere
  qui sert deja `cm1-conjugaison-present.yaml` (`couvert-valide`), pas l'accord avec un sujet
  eloigne, compose ou nominal que vise cette ligne. Le paquet est integralement neuf.
- **Distinction a garder de la ligne conjugaison deja couverte** : chaque item doit exiger de
  reperer le sujet avant de choisir la forme - jamais un sujet pronominal isole colle au verbe, qui
  ne ferait que redemander la conjugaison deja testee ailleurs.
- **Statut global** : **ouverte, aucun increment livre a ce jour.** Rien n'a encore ete joue.
- **Aucun arbitrage prealable de `nadia-prof` demande** : cette competence est directement servable
  par le schema `type: question` existant, sur le meme patron que la conjugaison deja couverte. Les
  criteres ci-dessous sont ceux de `camille-po`, verifies a l'audit.
- **Arbitrage prealable de mecanique en vigueur pour tout le catalogue** (`squad/conformite/
  FICHE-bandit-manchot-additions-cp.md`, conditions M1 a M13). Rien n'est mesure ici a l'ouverture
  de la fiche, faute d'increment a mesurer.
- **Derniere mise a jour** : 2026-08-24 (ouverture par `camille-po`, `DEC-0042`)

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| francais | CM1 (cycle 3) | Orthographe et grammaire | Accorder le verbe avec son sujet (cas frequents, temps simples : present, futur, imparfait) | `squad/programme/francais/competences-cm1.md` |

**Perimetre propose** : le present, le futur et l'imparfait sont chacun representes dans le paquet,
la competence du referentiel nommant les trois. Si les trois ne tiennent pas dans ce paquet a cout
raisonnable, la limite doit etre nommee par ecrit dans cette fiche des l'ouverture du
developpement, sur le modele de `DEC-0013` (conjugaison, un temps sur trois nomme d'emblee).

## Criteres pedagogiques attendus - camille-po

- [ ] Le sujet de chaque item n'est jamais un pronom personnel seul immediatement suivi du verbe -
  il est soit un groupe nominal ("les enfants du voisin ___"), soit un sujet compose ("mon frere et
  moi ___"), soit separe du verbe par un groupe de mots ("les enfants, malgre la pluie, ___").
- [ ] Sur chaque item, au moins un distracteur est la forme correcte d'un autre nombre ou d'une
  autre personne que celle du sujet reel - une erreur d'accord, pas une erreur de temps ni de verbe.
- [ ] Le paquet classe ses items par temps ; le present, le futur et l'imparfait sont chacun
  representes, sauf limite nommee explicitement dans cette fiche avant le premier audit.
- [ ] Le paquet compte au moins 20 items.
- [ ] Chaque item porte un indice non vide qui donne la methode d'accord (reperer le sujet, son
  nombre et sa personne), pas le resultat.
- [ ] Le paquet valide le schema `type: question` (`ADR-0007`) sans code Java neuf, sans gabarit ni
  feuille de style neuve.
- [ ] Si les trois temps ne tiennent pas tous dans ce paquet, la limite de couverture est nommee
  explicitement dans cette fiche avant le premier audit, jamais decouverte pendant.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent. Il reste vide tant qu'aucun
> increment n'a ete livre et joue.

### Verdict du 2026-08-24 (s19) - `S19-3`, "L'accord du verbe"

- **Auteur** : nadia-prof. **Increment** : `round-two` a `51301d7`, jar reconstruit depuis cet arbre
  et servi, application **arretee par son PID**.
- **Audit joue** : oui. **10 parties, 152 lectures, 21/21 items vus**, au repos et apres un echec, a
  **744 x 551** (`DEC-0040`). **`scrollY = 0` a chaque lecture, 0 sur 152.**
- **Releve** : `squad/conformite/releves-s19/s19-audit-accord-sv-744x551.json`. Capture :
  `s19-asv-744x551-tournoi-echec-verdict-sous-le-pli.png`.
- **Aucun arbitrage prealable n'etait du de ma part sur cette ligne** - je decouvre ce paquet
  entierement a l'audit.

## Verdict : **REFUSE.**

Je commence par ce qui est bon, parce que le refus ne porte pas sur tout : **les 21 accords sont
justes**, verifies un par un ; les sujets sont des groupes nominaux, des sujets composes ou des
sujets separes du verbe, jamais un pronom colle au verbe, comme la fiche le demandait ; les trois
temps sont representes sept fois chacun. **Le squelette de ce paquet est le bon. Ce sont sa mise en
page et ses indices qui le rendent inutilisable en l'etat.**

### Motif 1, decisif - deux items mettent le verdict sous le pli, au viewport de reference

| Item | Propositions | Largeur necessaire | Rangees | Bas du verdict |
|---|---|---|---|---|
| Demain, les enfants du quartier ___ un grand tournoi de football. | organiseront / organisera / organiserons | **plus de 700 px** | **2** | **564,19 px** |
| Cet ete, mes cousins ___ une cabane au fond du jardin. | construiront / construira / construirons | **684 px** | **2** | **564,19 px** |

**La rangee offerte est de 680 px. Le pli est a 551 px. Le bas du bloc de verdict tombe a
564,19 px.** Sur la capture, un enfant qui repond faux voit "Ce n'est pas ca. Essaie encore !"
tranche horizontalement en plein milieu des lettres, au ras du bord de l'ecran.

**C'est le defaut pour lequel j'ai refuse `S17-3` trois fois de suite ce trimestre**, et le motif n'a
pas change : un enfant qui repond et ne voit pas si c'est juste ne sait pas ce qu'il a appris.

**Et cette fois, aucune circonstance ne l'excuse.** `SYN-16` vit dans la bande 576-719 px, hors de la
reference, et c'est ce qui m'avait fait la classer en observation plutot qu'en refus. **Ici nous
sommes a 744 x 551, la reference du produit fixee par `DEC-0040`** - la seule largeur contre laquelle
tout est mesure depuis quatre sprints. Le plafond que `camille-po` a renvoye a s20 ne couvre pas ce
cas : il releve la rangee dans la bande basse, pas a la reference, ou elle vaut deja 680 px.

**Ce qui le repare, et ce n'est pas cher** : deux items sur vingt-et-un. Trois formes du futur d'un
verbe long (*organiseront*, *organiserons*, *organisera* : 12, 12 et 10 lettres) ne tiennent pas sur
une rangee. Un verbe court au meme temps y tient largement. **Le reste du paquet le montre : dix-neuf
items sur vingt-et-un tiennent sur une rangee.**

### Motif 2 - les vingt-et-un indices impriment la reponse en toutes lettres

Chaque indice se termine par "comme dans 'ils jouent'", "comme dans 'vous entrerez'", ou "devient
'irons'". **La chaine exacte a presser est ecrite dans l'indice, sur 21 items sur 21.**

**Ce n'est pas moi qui invente cette barre : c'est le critere ecrit dans cette fiche par
`camille-po`** - *"chaque item porte un indice non vide qui donne la methode d'accord (reperer le
sujet, son nombre et sa personne), pas le resultat"*. La premiere moitie de chaque indice donne bien
la methode ; la derniere clause donne le resultat.

**Ce que cela produit, mesure contre `M5`** : l'indice s'affiche des le premier echec. Un enfant qui
ne sait pas accorder lit la reponse et la recopie au deuxieme essai, et **la manche compte comme une
reussite**. Sur les vingt-et-un items, aucun ne distingue plus un enfant qui sait d'un enfant qui ne
sait pas. **Le paquet ne peut rien apprendre a celui qui en a besoin, et rien mesurer chez les
autres.**

*Ce qui le repare* : supprimer la clause finale des vingt-et-un indices. La methode reste, le
resultat s'en va. C'est cinq mots par item.

### Motif 3 - quinze items sur vingt-et-un portent un distracteur que personne ne choisirait

Sur les items a sujet de troisieme personne, le troisieme distracteur est systematiquement la forme
en -ons : "Les enfants du voisin ___" propose *jouent* / *joue* / **jouons** ; "le train de Paris
___" propose *arrivera* / *arriveront* / **arriverons**. **Aucun enfant n'ecrit "les enfants du
voisin jouons"**, et aucun de ces distracteurs ne correspond a une erreur reelle.

Ces quinze items sont donc **a trois propositions en apparence et a deux en fait**. Avec trois
essais, ils se gagnent par elimination sans jamais accorder quoi que ce soit.

**Les six items a sujet compose** ("mon frere et moi", "toi et ton frere") n'ont pas ce defaut :
leurs trois formes sont toutes des erreurs plausibles. **Ce sont les six meilleurs items du paquet**,
et ils montrent que l'auteur sait faire.

*Ce qui le repare* : remplacer la forme en -ons par une erreur qu'un CM1 commet reellement. La plus
frequente sur ces phrases est deja presente - l'accord avec le nom le plus proche, *le voisin* donc
*joue* ; la troisieme peut etre un homophone de conjugaison (*joues*) ou la forme d'un autre temps.

### Motif 4 - vocabulaire au-dessus du CM1, et un enonce qui se contredit

*Deferler* et *moudre* ne sont pas du vocabulaire de CM1, et *moudre* est en outre un verbe
irregulier rare qu'on ne conjugue pas a ce niveau. **Et "Les vagues de la mer, malgre le vent,
deferlent doucement sur le sable" dit une chose et son contraire** : deferler, c'est se briser avec
force. L'enfant qui connait le mot est gene, celui qui ne le connait pas apprend un sens faux.

### Motif 5 - la citation par apostrophes droites

Les vingt-et-un indices citent mots et terminaisons entre apostrophes droites - "le verbe 'jouer' se
termine par '-ent'". L'apostrophe est en francais le signe de l'elision, pas de la citation, et le
produit a deja etabli les guillemets francais pour cela (`SYN-6`, `SYN-9`). Sur un paquet de
grammaire, ce n'est pas un detail de gout. **Non decisif seul** ; je le nomme parce que la correction
se fait dans le meme geste que le motif 2.

### Ce que je note aussi, sans en faire un motif

**Vingt indices sur vingt-et-un tombent sous le pli** (583,19 px ou 673,19 px pour un pli a 551 px).
Je ne le compte pas dans le refus : c'est le meme defaut que `SYN-12`, que j'ai classe non bloquant
sur les synonymes et que je classe non bloquant sur `S19-2` ce meme jour. **Je ne durcis pas une
barre parce qu'un autre motif me donne raison par ailleurs.** Mais le chiffre est a verser au
chantier vertical de s20 : sur ce paquet, l'indice est invisible presque partout.

### Ce que ce refus fait

- **Il renvoie `S19-3` a `camille-po`, qui en fait du backlog. Il ne bloque pas le sprint suivant.**
- **Il ne remet en cause ni le contenu grammatical, ni la structure en trois temps, ni le choix des
  sujets** : les motifs 1, 2, 3 et 5 se corrigent sans toucher a un seul accord, et le motif 4 porte
  sur deux items.
- **Il ne deplace aucune ligne de matrice** : la ligne francais CM1 "Accorder le verbe avec son
  sujet" reste ce que `camille-po` decidera, et ce geste est le sien.

**Le plus court chemin vers un Done, puisqu'un refus doit etre actionnable** : les motifs 2 et 3 sont
une passe sur les indices et les distracteurs, le motif 1 est deux items a reecrire avec un verbe
plus court, le motif 4 est deux items, le motif 5 est un remplacement mecanique. **Rien ici ne demande
de recommencer le paquet.**
