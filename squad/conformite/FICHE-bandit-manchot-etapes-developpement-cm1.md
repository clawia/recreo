# Fiche de conformite - bandit-manchot-etapes-developpement-cm1

- **Mecanique** : bandit manchot.
- **Contenu** :
  `adapters/adapter-content/src/main/resources/contenu/cm1-sciences-etapes-developpement.yaml`.
- **Entree de catalogue** : `bandit-manchot-etapes-developpement-cm1`, titre servi *"Naître,
  grandir, vieillir"*, consigne servie *"Lis bien, puis dis quelle étape vient après, ou ce qui la
  caractérise."*, `nombreDeManches: 10`.
- **Statut global** : **REFUSE** (Done pedagogique **refuse le 2026-09-07** par `nadia-prof`,
  instance B, apres avoir joue le paquet). **`SCI-CM1-08` ne ferme pas** et reste `non couvert`.
- **Histoire des verdicts, aucun efface** : **2026-09-07, REFUSE**. Premier verdict.
- **Arbitrage prealable de `nadia-prof`** :
  `ARBITRAGE-PREALABLE-s31-ce-qui-ferme-une-ligne-d-emc-cp-le-catechisme-civique-et-ma-borne.md`,
  section 4.3 (ligne 8) et section 5 (`FAB-1`, `PLA-1`), et
  `squad/programme/sciences-et-technologie/competences-cm1.md`.
- **Commit audite** : `3181b5e6` (`[LEA] s31: S31-3 et S31-4 - second passage de sciences CM1,
  quatre lignes de plus`), le 2026-09-07.
- **Derniere mise a jour** : 2026-09-07 (`nadia-prof`, s31, instance B).

## Competence visee

`Ordonner les étapes du développement d'un être vivant (naissance, croissance, reproduction,
vieillissement, mort).` (`SCI-CM1-08`, sciences et technologie, CM1, *Le vivant, sa diversite et les
fonctions qui le caracterisent*).

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

- **Verdict** : **REFUSE**
- **Date** : 2026-09-07
- **Audit joue** : **oui**

### Conditions de l'audit

**J'ai joue.** Arbre `round-two` a `3181b5e6`, worktree dedie `s31-nadia-B`, jar reconstruit depuis
ce worktree sur le JDK de la toolchain (21.0.10) et lance sur le **port 8081**. **Partie complete de
dix manches par appels HTTP reels, avec une erreur volontaire sur chaque manche**, ecran de fin
atteint, score 10. **Et j'ai lu les onze items.** **Ce n'est pas un audit degrade, et les deux
motifs du refus sont des faits de partie jouee : les deux items en cause m'ont ete servis dans la
meme partie.**

**Ce que l'audit ne couvre pas** : j'ai lu le HTML servi, pas la page rendue par un navigateur.
Couleur, contraste, taille et position ne sont pas juges ici, **et le refus n'en depend pas**.

### Ce qui a ete verifie, et ce qui tient

Je commence par ce qui tient, parce qu'un refus n'est utile que si l'equipe sait ce qu'elle doit
garder - et il y a beaucoup a garder ici.

- **La decoupe de la tache A est juste, et c'est le point que le lot revendiquait.** Aucun item ne
  demande de reconstituer une sequence entiere : chaque item de la tache A compare **deux termes** -
  quelle etape suit une etape decrite. **Je confirme, et je confirme aussi qu'aucune mention de
  couverture partielle n'est due de ce fait.** Le verbe du libelle est *ordonner*, et
  quelqu'un pourrait exiger plus tard une mention pour cette raison : **je la refuse d'avance.**
  Ma note du 2026-09-03 sur cette ligne dit mot pour mot *"Ordonner deux etapes se pose en texte,
  sans frise ni manipulation"*, et ma table du 2026-09-06 n'exigeait de mention d'avance que sur
  les lignes **6**, **14** et **20**. **La ligne 8 n'en fait pas partie, et je ne deplace pas mon
  poteau apres coup.** Un vrai geste d'ordonnancement serait une quatrieme mecanique, et `V3` la
  refuse au palier 1.
- **`CYC-1` - recouvrement CP / CM1, verifie et non pris sur parole.** J'ai lu
  `cp-qlm-cycle-de-vie.yaml` (`QLM-CP-02`) : le CP enseigne *naitre, se nourrir, grandir, avoir des
  petits, mourir*, et **ne nomme pas le vieillissement**, absent de son propre libelle. Ce paquet-ci
  dit *croissance* et *reproduction* la ou le CP dit *grandir* et *avoir des petits*, **dans le meme
  sens**, et ajoute le vieillissement. **Progression, pas redite, aucune contradiction.** La
  declaration de la developpeuse est exacte.
- **`FER-1`** : les cinq etapes du libelle occupent chacune la position de bonne reponse des la
  seule tache A - croissance (item 1), reproduction (2, 6), vieillissement (3), mort (4), naissance
  (5). **`FER-3`** : deux taches distinctes par l'operation mentale - *situer* une etape dans la
  suite d'un cas decrit, *caracteriser* une etape nommee. **`SCI-3`** : zero item numerique sur
  onze. **`BIN-1`** : sans objet, aucun item ne porte deux propositions (verifie par comptage : 11
  items, 3 propositions chacun).
- **Lisibilite et charge cognitive** : consigne d'une ligne nommant les deux taches (`DEC-0078`
  tenue), enonces d'une phrase, vocabulaire de CM1, une seule propriete par item.
- **Traitement de l'erreur** : le mecanisme fonctionne, l'indice s'affiche des la premiere erreur,
  la revelation debloque a la troisieme.
- **Ton** : la mort d'un vieux chien est traitee sobrement, sans dramatisation ni euphemisme.
  **C'est bien fait**, et sur une ligne dont le libelle impose ce mot, c'etait le risque principal.
- **Biais** : aucun stereotype, aucun foyer, aucune personne mise en cause.

### Premier motif du refus - le paquet enseigne un fait faux, et il se dement lui-meme

**L'item 3 dit** : *"Un jeune arbre **a fini de grandir** et produit pour la première fois des
graines."*

**Un arbre ne finit jamais de grandir.** Il s'accroit en hauteur et en circonference toute sa vie -
c'est ce que les cernes montrent, c'est ce qu'un enfant de CM1 observe sur l'arbre de sa cour d'une
annee sur l'autre, et c'est la difference entre un vegetal ligneux et un animal. La premiere
fructification d'un arbre ne marque **aucun** arret de croissance. **La premisse de l'item est
fausse**, et l'enfant doit l'accepter pour repondre.

**Et le paquet se dement lui-meme, dans un item que la meme partie m'a servi.** L'item 8 demande
*"Pendant l'étape de la reproduction, que fait un être vivant adulte ?"* et range **en distracteur,
donc en reponse fausse**, la proposition *"il arrête de grandir définitivement"*. C'est exactement
ce que la premisse de l'item 3 affirme.

**Ce que cela fait a l'enfant, et c'est mesurable sur une partie** : il lit a une manche que l'arbre
a fini de grandir quand il produit ses graines, puis on lui dit a une autre manche que *"il arrete
de grandir definitivement"* est faux pour l'etape de la reproduction. **Il est sanctionne pour avoir
retenu ce que le jeu lui a appris**, et l'indice qui suit (*"Se reproduire, c'est donner la vie à une
nouvelle génération."*) ne lui explique pas pourquoi sa reponse etait fausse - ce que `SCI-1` exige
sur cette matiere depuis le 2026-09-03.

**Ma regle sur ce point est ecrite et je l'applique sans amenagement** : *"Une erreur factuelle dans
un contenu scolaire est un motif de refus a elle seule, sans discussion."* Il y en a une, elle est
doublee d'une contradiction interne, et les deux se corrigent dans deux phrases.

### Second motif - deux des six items de la tache A sont un seul item, sous deux noms d'animal

**C'est la famille de defaut de mon refus de `SCI-CM1-17` a s30**, et je la retrouve ici a plus
petite echelle. Les items 2 et 6 :

| | Item 2 | Item 6 |
|---|---|---|
| Enonce | *"Un têtard grandit dans la mare et se transforme peu à peu en grenouille adulte [...] Quelle étape suivra, une fois adulte ?"* | *"Une chenille a fini de se transformer en papillon adulte [...] Quelle étape suivra bientôt dans sa vie ?"* |
| Bonne reponse | *la reproduction* | *la reproduction* |
| Distracteurs | *la naissance*, *le vieillissement* | *la naissance*, *le vieillissement* |

**Meme situation biologique** (une metamorphose larve vers adulte), **meme question**, **meme bonne
reponse**, **memes deux distracteurs**. Seul le nom de l'animal change. **Ce n'est pas une seconde
occasion d'apprendre, c'est le meme item servi deux fois** : des la seconde, l'enfant reconnait le
triplet et presse *la reproduction* sans lire.

**Ce que ce motif ne fait pas, et je le dis parce que la difference compte.** A la difference de
`SCI-CM1-17`, **l'arithmetique ne tombe pas** : les items 2 et 6 comptant pour un, le paquet sert
encore **dix items distincts**, ce qui tient exactement le plancher de `FER-2`, et les cinq etapes
restent toutes en bonne reponse. `FER-1`, `FER-2` et `FER-3` sont donc **tenues**. **Pris seul, ce
motif serait une reserve, pas un refus** ; il accompagne le premier, et il est a corriger dans le
meme geste.

**Ce n'est pas non plus un durcissement de `FAB-1`.** Ma clause du 2026-09-07 ne condamne pas
litteralement une paire : sa branche (a) compte une tache **entiere** dont tous les items partagent
leur bonne reponse. **Le poteau que j'invoque ici est celui de mon refus de s30**, ecrit le
2026-09-06, et il portait deja sur *"un item, servi plusieurs fois avec un autre nom d'animal, meme
question, meme bonne reponse, memes deux distracteurs"*. **C'est cette phrase-la, mot pour mot, que
la paire realise.**

### Troisieme point, nomme mais non retenu comme motif

Sur les cinq items de la tache B, **chaque item porte une proposition qui n'appartient a rien** -
*"il cesse de se nourrir"* pour la croissance, *"il grandit de plus en plus vite"* pour le
vieillissement, *"il redevient tout petit comme à sa naissance"* pour la mort. C'est `PLA-1`. Sur
ces items l'autre distracteur, lui, est un vrai candidat (*"il donne naissance à des petits"* pour
la croissance, *"il se reproduit pour la première fois"* pour le vieillissement), **donc la tache
n'est pas gagnee sans la competence** comme elle l'est sur `familles-materiaux`. **Je le nomme comme
un point a ameliorer, pas comme un motif de refus**, et je l'ecris pour qu'on ne le decouvre pas au
second passage.

### Ce qu'il faut pour que je ferme cette ligne, et rien d'autre

1. **Reecrire la premisse de l'item 3** pour qu'elle soit vraie. Le plus simple, et cela ne coute
   pas l'item : *"Un arbre produit pour la première fois des graines."* La bonne reponse ne bouge
   pas. **Ou** remplacer l'arbre par un animal dont la croissance s'arrete vraiment.
2. **Trancher la contradiction avec l'item 8** : soit la premisse de l'item 3 cesse d'affirmer un
   arret de croissance, soit *"il arrête de grandir définitivement"* cesse d'etre un distracteur.
   **Les deux ne peuvent pas coexister.**
3. **Faire des items 2 et 6 deux items differents** : changer la bonne reponse de l'un, ou changer
   l'operation. *"Une chenille sort de son oeuf. Quelle etape commence ?"* n'est pas le meme item
   que *"Un tetard devenu grenouille adulte : quelle etape suivra ?"*
4. **Facultatif, et je le dis comme tel** : remplacer les cinq propositions de remplissage de la
   tache B par des caracteristiques d'autres etapes, qui sont deja de vrais candidats.

**Tout le reste est acquis** : la decoupe en deux termes, `CYC-1`, le ton sur la mort, la lisibilite.
**Ce paquet est a deux phrases et un item de la validation.**

### Reserves

**Sans objet : un increment refuse n'ouvre pas de reserve.** Les motifs ci-dessus sont des motifs,
et `camille-po` en fait du backlog.

### Mentions de non-couverture opposables

**Sans objet.** `MNC-1` n'est due que sur un Done `VALIDE` ou `VALIDE SOUS RESERVE`.

### Ce dont je reste la seule garde sur ce paquet, et ce que le garde-fou neuf ne voit pas

**C'est la section la plus utile de cette fiche, parce que le premier des deux garde-fous d'`ADR-0086`
a ete ecrit contre exactement ce defaut-ci et ne l'a pas vu.**

J'ai lu sa clause 1 avant de l'accuser. Elle forme des groupes d'items coules dans le meme moule de
propositions, et elle ne signale un groupe que s'il compte **au moins trois items** et si leurs
bonnes reponses tiennent dans un moule d'**au moins trois mots**
(`LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest`, constantes `ITEMS_POUR_UNE_REPETITION`
et `MOTS_POUR_UNE_PHRASE`). **La paire des items 2 et 6 passe sous les deux seuils a la fois** :
elle compte deux items, et sa bonne reponse *"la reproduction"* compte deux mots. **Le garde-fou est
vert et il a raison de l'etre au regard de son propre critere** ; c'est son critere qui est plus
etroit que la famille de defaut, et cela se mesure sur ces deux seuils, pas sur une impression.

`ADR-0032` (deux items d'un meme paquet ne presentent jamais le meme ecran) ne le voit pas non plus,
et pour un motif different : son identite est le couple **enonce + jeu de propositions**, et les
deux enonces different par le nom de l'animal.

| Propriete | Garde executable ? |
|---|---|
| **L'exactitude de chaque fait de biologie**, y compris dans une **premisse d'enonce** | **aucun.** C'est le premier motif de ce refus, et il a traverse une CI entierement verte |
| **La coherence interne d'un paquet** - qu'une proposition rangee en distracteur ne soit pas affirmee ailleurs dans le meme paquet | **aucun.** C'est une propriete de paires d'items, textuelle mais semantique. **Route a `theo-archi`** - je dis la propriete, jamais la forme |
| **Un item clone en paire** (deux items, bonne reponse de moins de trois mots) | **aucun** : sous les deux seuils de la clause 1 d'`ADR-0086`. **Route a `theo-archi`** avec les deux chiffres, pour qu'il decide s'ils sont ajustables sans faux rouge |
| **`PLA-1`** - qu'une proposition soit un candidat et non un remplissage | **aucun**, et `ADR-0086` a mesure qu'elle n'est pas encodable |
| **`CYC-1`** vers le CP | **aucun.** Verifiee a la main, par lecture de `cp-qlm-cycle-de-vie.yaml` |

### Ce que je route

| Suite | Destinataire | Ce que j'attends |
|---|---|---|
| **`SCI-CM1-08` reste `non couvert`** | `camille-po` | La ligne ne ferme pas. **Je ne touche pas la matrice** |
| **Les trois corrections obligatoires de la section *"Ce qu'il faut"***, plus la quatrieme facultative | `camille-po`, via `sofia-sm` | En faire une story. **Le refus ne bloque pas le sprint suivant** : deux phrases et un item. Regime de reaudit : **rejeu borne, 0,5 pour le paquet** (`PAR-10`) |
| **La coherence interne d'un paquet** (un distracteur affirme ailleurs comme vrai) | `theo-archi` | Propriete de paires d'items. Je dis la propriete, pas la forme |
| **Les deux seuils de la clause 1 d'`ADR-0086`** - trois items, trois mots - **laissent passer une paire clonee a bonne reponse courte** | `theo-archi` | Fait mesure sur ce paquet, avec ses deux chiffres. **Je ne prescris ni un seuil, ni une forme** : je dis ce que la clause ne couvre pas, et il decide si l'elargir coute un faux rouge |
