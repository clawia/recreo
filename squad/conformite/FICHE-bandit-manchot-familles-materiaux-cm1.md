# Fiche de conformite - bandit-manchot-familles-materiaux-cm1

- **Mecanique** : bandit manchot.
- **Contenu** :
  `adapters/adapter-content/src/main/resources/contenu/cm1-sciences-familles-materiaux.yaml`.
- **Entree de catalogue** : `bandit-manchot-familles-materiaux-cm1`, titre servi *"De quoi c'est
  fait ?"*, consigne servie *"Lis bien, puis dis de quelle famille de matériau il s'agit, ou quel
  matériau choisir."*, `nombreDeManches: 10`.
- **Statut global** : **REFUSE** (Done pedagogique **refuse le 2026-09-07** par `nadia-prof`,
  instance B, apres avoir joue le paquet). **`SCI-CM1-11` ne ferme pas** et reste `non couvert`.
- **Histoire des verdicts, aucun efface** : **2026-09-07, REFUSE**. Premier verdict.
- **Arbitrage prealable de `nadia-prof`** :
  `ARBITRAGE-PREALABLE-s31-ce-qui-ferme-une-ligne-d-emc-cp-le-catechisme-civique-et-ma-borne.md`,
  section 4.3 (ligne 11, parmi *"les plus sures"*) et section 5 (`PLA-1`), et
  `squad/programme/sciences-et-technologie/competences-cm1.md`.
- **Commit audite** : `3181b5e6` (`[LEA] s31: S31-3 et S31-4 - second passage de sciences CM1,
  quatre lignes de plus`), le 2026-09-07.
- **Derniere mise a jour** : 2026-09-07 (`nadia-prof`, s31, instance B).

## Competence visee

`Identifier les principales familles de matériaux et choisir un matériau d'après une propriété
(résistance, isolation, souplesse, imperméabilité).` (`SCI-CM1-11`, sciences et technologie, CM1,
*Materiaux et objets techniques*).

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
atteint, score 10. **Et j'ai lu les onze items.** **Ce n'est pas un audit degrade, et le motif du
refus est un fait de partie jouee.**

**Ce que l'audit ne couvre pas** : j'ai lu le HTML servi, pas la page rendue par un navigateur.
Couleur, contraste, taille et position ne sont pas juges ici, **et le refus n'en depend pas**.

### Ce qui a ete verifie, et ce qui tient

Je commence par ce qui tient, parce qu'un refus n'est utile que si l'equipe sait ce qu'elle doit
garder.

- **Ancrage au programme** : libelle du descripteur **copie conforme** du referentiel. **Les trois
  conditions de fermeture sont tenues a la lettre** :
  **`FER-2`** onze items ; **`FER-3`** deux taches distinctes par l'operation mentale - *nommer* la
  famille d'un objet decrit (cinq items) d'un cote, *choisir* le materiau qui convient a un usage
  (six items) de l'autre, la moins servie en portant cinq ; **`FER-1`** les cinq familles de la
  tache A occupent chacune la position de bonne reponse **une seule fois, sans repetition**, et les
  quatre proprietes de la parenthese sont chacune le critere d'au moins un item de la tache B.
  **Ce refus ne repose sur aucune de ces trois conditions**, et je l'ecris parce que
  c'est ce qui le distingue de mon refus de `SCI-CM1-17` a s30, qui etait arithmetique.
  *Ce que j'avais ecrit a leur sujet le 2026-09-03, dans l'arbitrage prealable de s28 ou je les ai
  posees, et qui vaut ici* : *"elles ne sont pas suffisantes. Un lot qui les tient toutes peut etre
  refuse pour une erreur de fait, une consigne illisible, **un distracteur absurde** ou un biais."*
- **La tache A est bonne, et elle n'est pas a refaire.** Cinq items, cinq familles, et **chaque
  enonce porte le critere qui departage** : la casserole *"sans qu'elle fonde ni brule"* ecarte le
  bois et le plastique ; le gobelet *"qui peut fondre"* ecarte le verre et le metal ; la bouteille
  *"qui se brise en morceaux tranchants"* ecarte le plastique. **Les distracteurs y sont de vraies
  familles concurrentes** - il existe des tables de jardin en metal et en plastique. C'est
  exactement la fabrication qu'il faut.
- **Exactitude du contenu** : **aucune erreur de fait** sur les onze items. Les proprietes citees
  (resistance du metal, isolation thermique du bois, souplesse du plastique et du tissu,
  impermeabilite du caoutchouc et du verre) sont justes.
- **`SCI-3`** : zero item numerique sur onze. **`BIN-1`** : sans objet, aucun item ne porte deux
  propositions (verifie par comptage : 11 items, 3 propositions chacun). **`SCI-2`, `BIA-2`,
  `BIA-3`, `BIA-4`** : sans objet ou tenues - aucune personne, aucun foyer, aucun stereotype.
- **Lisibilite** : consigne d'une ligne nommant les deux taches (`DEC-0078` tenue). Vocabulaire de
  CM1. **Une seule propriete par item, jamais deux** - c'est la note que j'avais posee d'avance sur
  cette ligne le 2026-09-03 (*"la charge cognitive double sinon"*), et **elle est tenue sur les
  onze items**.
- **Traitement de l'erreur** : le mecanisme fonctionne, l'indice s'affiche des la premiere erreur,
  la revelation debloque a la troisieme.
- **La lecture de `FER-1` que la developpeuse invoque est juste, et je la confirme.** Elle ecrit que
  *"la position de bonne reponse est celle de la propriete testee, pas une reprise litterale du mot
  dans le texte de la reponse"*, en s'appuyant sur le precedent que j'ai pose sur
  `cm1-sciences-melanges`. **J'ai relu mes propres mots avant de m'en servir** : ma fiche du
  2026-09-06 credite l'element *"un moyen de separation"* par des items dont les bonnes reponses
  sont *filtre, evaporation, aimant, decantation, passoire* - c'est-a-dire **le critere teste, pas
  le mot du libelle**. Le precedent est reel, il est de moi, et **il transpose ici encore plus
  directement** : le verbe du libelle est *choisir un materiau* d'apres une propriete, donc la bonne
  reponse **doit** etre un materiau et la propriete **doit** etre le critere.
  **Mais confirmer cette lecture ne sauve pas le paquet, et c'est precisement pourquoi elle
  compte** : si c'est le critere teste qui vaut credit, alors **il faut que l'item le teste**. Le
  motif du refus est la.

### Motif du refus - sur trois des quatre proprietes du libelle, l'item ne teste rien

**C'est le fait, et il est releve item par item sur la tache B.**

La tache B nomme la propriete dans l'enonce (*"Il faut un materiau tres resistant"*, *"souple"*,
*"impermeable"*) et demande de choisir le materiau. **La question est donc uniquement : les deux
autres propositions sont-elles des reponses qu'un enfant peut envisager ?** C'est `PLA-1`, ma clause
du 2026-09-07, posee neuf heures avant que le paquet soit ecrit.

| Item | Propriete testee | Bonne reponse | Les deux distracteurs | Un enfant les envisage-t-il ? |
|---|---|---|---|---|
| 6 | **resistance** | le metal | le tissu, le verre | **ni l'un ni l'autre.** Un pont en tissu, un pont en verre |
| 7 | **isolation** | le bois | le metal, le verre | **le metal, oui**, et c'est meme la conception fausse utile - beaucoup de manches de poele sont en metal. Le verre, non |
| 8 | **souplesse** | le plastique | le verre, le metal | le verre, non. Le metal, a la rigueur |
| 9 | **impermeabilite** | le caoutchouc | le tissu, le bois | **le tissu, oui.** Le bois, non |
| 10 | **impermeabilite** | le verre | le tissu, le bois | **ni l'un ni l'autre.** Une bouteille en tissu |
| 11 | **souplesse** | le tissu | le metal, le verre | **ni l'un ni l'autre.** Un vetement en metal, un vetement en verre |

**Trois items sur six - 6, 10 et 11 - n'offrent aucune alternative serieuse.** L'enfant y trouve la
reponse en ecartant l'absurde, sans avoir jamais appris qu'un materiau a des proprietes.

**Et voici ce qui rend ce constat bloquant plutot que reservable, parce que c'est la ou il rencontre
`FER-1`.** Les quatre proprietes de la parenthese du libelle sont portees ainsi :

| Propriete du libelle | Items qui la portent | Un de ces items teste-t-il vraiment ? |
|---|---|---|
| **resistance** | 6 seul | **non** |
| **isolation** | 7 seul | **oui** |
| **souplesse** | 8 et 11 | 11 non ; 8 a peine |
| **impermeabilite** | 9 et 10 | 9 oui ; 10 non |

**Sur les quatre proprietes que `FER-1` oblige a servir, une seule - l'isolation - est servie par un
item qui teste ce qu'il annonce.** `FER-1` est tenue a la lettre et **son objet n'est pas
atteint** ;
c'est le meme mode de defaillance que celui de `SCI-4` que j'ai nomme contre moi-meme le
2026-09-07, et je ne peux pas fermer une ligne sur une condition dont je viens d'ecrire qu'elle se
franchit sans rien proteger.

**Ce n'est pas un jugement de gout sur la difficulte.** Le paquet prouve lui-meme qu'il sait faire :
**l'item 7 est le bon item de la tache B**, parce que *le metal* y est une reponse qu'un enfant
donne vraiment et qu'il faut savoir quelque chose pour ne pas la donner. Il y en a un sur six.

### Deux motifs secondaires, nommes parce qu'ils sont a corriger dans le meme geste

- **L'indice de l'item 10 ne veut rien dire, et c'est un retour, donc `SCI-1` le regarde.** Il dit :
  *"Ce matériau ne laisse jamais passer l'eau, même liquide."* **L'eau est liquide** ; le *meme*
  concessif y suppose une eau qui le serait moins, et l'enfant qui vient de se tromper lit une
  phrase qui n'a pas de sens. Vu a l'ecran, manche 3. C'est un defaut de **fond**, pas
  d'orthographe : il est de mon perimetre et pas de celui du `relecteur-pedagogique`.
- **Le caoutchouc est servi en bonne reponse sans avoir jamais ete enseigne comme famille.** La
  tache A enseigne cinq familles - bois, metal, verre, plastique, tissu. La tache B en couronne une
  sixieme a l'item 9. **Le paquet demande a l'enfant de choisir une famille qu'il ne lui a pas
  nommee**, et un enfant qui aurait retenu les cinq de la tache A repondrait *plastique*, qui n'est
  meme pas offert. Ce n'est pas faux - le caoutchouc est une famille defendable - mais c'est
  incoherent avec la premiere moitie du paquet.

### Ce qu'il faut pour que je ferme cette ligne, et rien d'autre

1. **Sur les items 6, 10 et 11, et de preference aussi 8** : remplacer au moins un distracteur par
   un materiau qu'un enfant choisirait vraiment - le **bois** pour le pont de l'item 6 (un pont en
   bois existe, et c'est le metal qu'il faut savoir preferer pour des camions) ; le **plastique**
   pour la bouteille de l'item 10 (c'est la reponse spontanee de tout enfant, et il faut alors que
   l'enonce dise ce qui departage le verre du plastique, ou que le verre cesse d'etre la reponse) ;
   le **cuir** ou le **plastique** pour le vetement de l'item 11. **Le modele est dans le meme
   lot** :
   l'item 8 de `cm1-sciences-etats-matiere`, valide sans reserve le meme jour, et **l'item 7 de ce
   paquet-ci**.
2. **Reecrire l'indice de l'item 10** pour qu'il dise quelque chose de vrai.
3. **Traiter le caoutchouc** : soit l'enseigner en tache A comme sixieme famille, soit le remplacer
   a l'item 9 par une famille deja enseignee.

**La tache A n'est pas a toucher.** Elle est bonne.

### Reserves

**Sans objet : un increment refuse n'ouvre pas de reserve.** Les motifs ci-dessus sont des motifs,
et `camille-po` en fait du backlog.

### Mentions de non-couverture opposables

**Sans objet.** `MNC-1` n'est due que sur un Done `VALIDE` ou `VALIDE SOUS RESERVE`.

### Ce dont je reste la seule garde sur ce paquet

| Propriete | Garde executable ? |
|---|---|
| **`PLA-1`** - qu'une proposition soit un candidat et non un remplissage | **aucun.** `ADR-0086` a mesure qu'elle n'est pas encodable : le mot portant est *plausible*, qui n'est pas une chaine, et le proxy essaye blanchissait le paquet que j'avais refuse tout en accusant 16 paquets valides sur 55. **Rien n'a averti la developpeuse, et c'est ce refus-ci qui le prouve** |
| **`SCI-1`** - qu'un indice dise quelque chose de vrai | **aucun.** L'indice de l'item 10 a traverse une CI entierement verte |
| **La coherence entre les familles enseignees et les familles servies en reponse** | **aucun**, et c'est pourtant une propriete d'ensemble sur le YAML. **Route a `theo-archi`** - je dis la propriete, jamais la forme |
| **L'exactitude de chaque appariement propriete / materiau** | **aucun** |

**Ce que les garde-fous ont fait ici, et je le dis pour qu'on ne s'y trompe pas.** Les deux clauses
d'`ADR-0086` sont **vertes** sur ce paquet, et **elles ont raison de l'etre** : il n'y a ni item
clone ni amorce contrefaite. `FER-1`, `FER-2`, `FER-3` sont tenues. `SCI-4` est verifiee
structurellement - les onze items portent exactement trois propositions. **Tout ce qui pouvait etre
mesure etait vert, et le paquet est refuse.** C'est la definition meme d'une propriete dont je suis
le seul garde.

### Ce que je route

| Suite | Destinataire | Ce que j'attends |
|---|---|---|
| **`SCI-CM1-11` reste `non couvert`** | `camille-po` | La ligne ne ferme pas. **Je ne touche pas la matrice** |
| **Les trois corrections de la section *"Ce qu'il faut"*** | `camille-po`, via `sofia-sm` | En faire une story. **Le refus ne bloque pas le sprint suivant** : la tache A est acquise, seuls quatre items et un indice sont a reprendre. Regime de reaudit : **rejeu borne, 0,5 pour le paquet** (`PAR-10`) |
| **La coherence familles enseignees / familles servies en reponse** | `theo-archi` | Propriete d'ensemble sur le YAML, donc potentiellement mordable. Je dis la propriete, pas la forme |
