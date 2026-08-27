# Avis de `nadia-prof` et Done pedagogique - cloture de s20

- **Auteure** : nadia-prof. **Date** : 2026-08-27. **Increment audite** : `round-two` a `77dd467`,
  arbre propre (`git status --porcelain` vide avant et apres chaque campagne).
- **Destination** : cette conclusion est a reprendre telle quelle dans la section "Avis de
  `nadia-prof` et Done pedagogique" de `review.md`, que `sofia-sm` ecrit. Les motifs complets et
  chiffres vivent dans les fiches de conformite, qui font foi.

## Les deux verdicts

| Jeu | Verdict anterieur | **Verdict du 2026-08-27** | Ligne de matrice | Consequence sur `catalogue.yaml` |
|---|---|---|---|---|
| `bandit-manchot-nature-mot-cp` | `VALIDE` le 2026-08-25 (`NAT-5` levee) | **`VALIDE` confirme** sur l'artefact reellement servi | `couvert-valide` | **rien a faire** : l'entree rendue par `DEC-0048` porte bien l'increment que j'ai valide |
| `bandit-manchot-reperes-cm1` | `REFUSE` le 2026-08-26 (pli + `REP-4`) | **`VALIDE SOUS RESERVE`** apres reaudition du paquet corrige | peut passer `couvert-valide` pour la competence detachee par `DEC-0046` | **le motif du retrait decide par `DEC-0049` est leve** ; le retour de l'entree appartient a `camille-po` et `sofia-sm`, **je n'ai pas touche au fichier** |

**Aucun refus ne reste ouvert a la cloture de s20.**

## Ce que j'ai verifie, en une phrase par jeu

**`cp-nature-mot`** : rejoue a `77dd467`, 4 parties, 76 lectures, 16 items sur 16 plus une partie
complete de 10 manches sur le marquage, a 744 x 551 ; verdict a **433,38 px** et indice a
**512,38 px**, **0 sur 16 sous le pli**, marquage present et unique **16 sur 16** a **36,8 px** contre
32 px - **identique au centieme a ma mesure du 2026-08-25**, donc mon `VALIDE` porte bien sur ce qui
est servi. La forme d'`ines-design` (`2,3rem`) et le contrat `ADR-0062` de `marc-dev` etaient deja
dans l'increment que j'avais juge ; le seul changement depuis est l'entree de catalogue, dont la
consigne est mot pour mot celle de mon releve.

**`cm1-reperes`** : rejoue a `77dd467` avec l'entree de catalogue restauree dans une **copie du jar
hors du depot**, 4 parties, 76 lectures, 16 items sur 16 ; **`REP-4` est tenue** - la simulation d'un
enfant qui ignore le point d'interrogation passe de **16 sur 16** a **13 sur 16**, et **3 items
exigent desormais la distinction**, ce que j'avais exige au caractere pres. Le verdict, invisible sur
**16 items sur 16** le 2026-08-26, est entierement lisible sur **14 sur 16** aujourd'hui.

## Le residu, et pourquoi je ne le retiens pas contre ce paquet

L'indice reste hors de vue apres un echec sur **16 items sur 16** (44,19 a 92,19 px sous le pli).
**J'ai verifie l'affirmation de mon propre refus au lieu de m'appuyer dessus, et elle etait fausse** :
j'avais ecrit que c'etait "la barre que j'applique depuis s17". Balayage du catalogue servi le
2026-08-27, une partie par jeu, 17 jeux, **157 items vus** : **8 jeux sur 17 et 43 items sur 157**
enfoncent l'indice sous le pli, dont deux **plus bas** que `cm1-reperes` corrige
(`perimetre-cm1` a 685,19 px, `grands-nombres-cm1` a 715,19 px) et un sur **la totalite** des items
vus (`problemes-additifs-cp`, 10 sur 10). Trois jeux sur 17 passent meme le **verdict** sous le pli.
Maintenir un refus sur cette propriete opposerait a `lea-dev` une barre que je n'ai jamais opposee a
aucun autre jeu : ce serait un poteau deplace.

**Ce n'est pas ma barre qui baisse, c'est sa portee qui change de main.** La cause est mesuree et
n'est pas dans le contenu : a la largeur offerte de 680 px, un libelle de plus de **10 caracteres**
fait passer trois propositions sur deux rangees, ce qui coute **90 px** ; un enonce de deux lignes en
coute **48** de plus. C'est le budget vertical du cabinet et la taille de `.proposition`.

## Ce que je route, pour que `camille-po` en fasse du backlog

| Jeton | Objet | Destinataire |
|---|---|---|
| `REP-8` | **L'indice hors de vue apres un echec, 8 jeux sur 17 du catalogue.** A mes yeux le premier defaut pedagogique du catalogue aujourd'hui. Story de catalogue, pas reprise de paquet | `camille-po`, avec `ines-design` et `theo-archi` sur la cause de forme |
| `REP-7` | Sur `cm1-reperes`, la composition des trois propositions predit la reponse sans lire la phrase (4 items gagnes a coup sur, esperance 10 sur 16 contre 5,33 au hasard) | `camille-po` puis `lea-dev` |
| `REP-9` | Sur `cm1-reperes`, le verdict coupe en deux sur les 2 items a enonce de deux lignes. **Corrigeable dans le paquet** : ma propre phrase avait ferme ce levier a tort, je le rouvre | `camille-po` puis `lea-dev` |
| `REP-10` | Sur `cm1-reperes`, l'indice de l'item 15 nomme la reponse de trop pres (meme famille que `REP-5`) | `camille-po` puis `lea-dev` |
| `NAT-12` | Le commentaire de `.mot-designe` dans `recreo.css` annonce trois canaux non colores ; j'en mesure **un** (l'agrandissement). Redaction, pas contenu enfant | `relecteur-pedagogique` et `ines-design` |

## Trois erreurs a moi, ecrites ici parce qu'elles ont coute du temps a d'autres

1. **Ma barre "verdict et indice au-dessus du pli 16 sur 16" se presentait comme une barre etablie ;
   elle ne l'etait pas** pour l'indice, et 8 jeux sur 17 du catalogue ne la tiennent pas (mesure du
   2026-08-27). Corrigee ci-dessus.
2. **Ma phrase "ce que je ne demande pas : ni de toucher aux seize phrases" a ete lue comme une
   interdiction** par `lea-dev`, qui l'ecrit deux fois dans la fiche. Sa lecture est defendable, ma
   formulation est la cause, et elle lui a ferme le levier qui reglait `REP-9`. **Je ne demandais pas
   de les toucher ; je ne l'interdisais pas.**
3. **L'en-tete de `FICHE-bandit-manchot-nature-mot-cp.md` portait "Done pedagogique non encore
   prononce"** du 2026-08-24 au 2026-08-27, soit trois verdicts apres qu'il l'a ete. Corrige dans ce
   lot.

## Ce que je n'ai pas mesure, nomme sans elargissement

- **Toutes mes campagnes du 2026-08-27 tournent avec `--hide-scrollbars`** (mode de
  `balayage-largeur-rangee.py`, donc de tout l'outillage `nadia-prof`). **L'etat non mesure est
  celui-la et pas un autre** : le rendu avec une barre de defilement classique peinte. Sur
  `cm1-reperes` la question a une consequence reelle - apres un echec le document fait 627 px pour un
  pli a 551, donc **76 px de defilement possible** : l'indice est *hors de vue*, pas *inatteignable*,
  et je ne sais pas si l'enfant en recoit un signal.
- **`REP-1`, `REP-2`, `REP-3`, `REP-7`, `REP-10`** et **`partDesignee` designant le bon mot** : aucun
  test de ce depot ne les verifie. **J'en reste la seule garde**, et je le dis pour que l'equipe ne
  les croie pas couvertes par une CI verte. `REP-4` est desormais gardee par
  `PaquetCm1ReperesTest`, et c'est un vrai gain.
- **Le balayage du catalogue voit 7 a 10 items par jeu sur un tirage aleatoire, une partie par jeu** :
  ses comptes sont un **plancher**, pas un inventaire.
- Toute largeur autre que **744 x 551** : non mesuree.
