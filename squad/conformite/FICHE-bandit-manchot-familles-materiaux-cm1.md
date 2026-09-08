# Fiche de conformite - bandit-manchot-familles-materiaux-cm1

- **Mecanique** : bandit manchot.
- **Contenu** :
  `adapters/adapter-content/src/main/resources/contenu/cm1-sciences-familles-materiaux.yaml`.
- **Entree de catalogue** : `bandit-manchot-familles-materiaux-cm1`, titre servi *"De quoi c'est
  fait ?"*, consigne servie *"Lis bien, puis dis de quelle famille de matériau il s'agit, ou quel
  matériau choisir."*, `nombreDeManches: 10`.
- **Statut global** : **VALIDE SOUS RESERVE** (Done pedagogique **prononce le 2026-09-08** par
  `nadia-prof`, instance B, **au second audit**, apres avoir joue la reprise `0e162501`).
  **`SCI-CM1-11` ferme** : `non couvert` -> **`couvert-valide`**, sans mention de couverture
  partielle. Deux reserves neuves et datees, `MAT-R1` et `MAT-R2`, non bloquantes.
- **Histoire des verdicts, aucun efface** : **2026-09-07, REFUSE** - sur les quatre proprietes que
  `FER-1` oblige a servir, une seule etait servie par un item qui teste ce qu'il annonce ; le bloc
  entier de ce refus, avec ses quatre motifs et ses deux tables, reste lisible ci-dessous.
  **2026-09-08, VALIDE SOUS RESERVE** apres reprise, second bloc en fin de fiche. **Un refus efface
  n'a aucune valeur de preuve.**
- **Arbitrage prealable de `nadia-prof`** :
  `ARBITRAGE-PREALABLE-s31-ce-qui-ferme-une-ligne-d-emc-cp-le-catechisme-civique-et-ma-borne.md`,
  section 4.3 (ligne 11, parmi *"les plus sures"*) et section 5 (`PLA-1`), et
  `squad/programme/sciences-et-technologie/competences-cm1.md`.
- **Commits audites** : `3181b5e6` (`[LEA] s31: S31-3 et S31-4 - second passage de sciences CM1,
  quatre lignes de plus`) le 2026-09-07, puis `0e162501` (`[LEA] s31: reprise SCI-CM1-11 - trois
  distracteurs deviennent des candidats plausibles (PLA-1)`), sur l'arbre `60bde989` du 2026-09-08.
- **Derniere mise a jour** : 2026-09-08 (**second verdict de `nadia-prof` apres avoir joue la
  reprise**, s31, instance B).

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

---

## Second bloc de validation pedagogique - nadia-prof UNIQUEMENT, apres reprise

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent. **Le bloc de refus qui precede
> n'est pas efface** : c'est lui qui prouve que la porte s'est fermee avant de s'ouvrir.

- **Verdict** : **VALIDE SOUS RESERVE** - **le refus du 2026-09-07 est leve**
- **Date** : 2026-09-08
- **Audit joue** : **oui**
- **Ce qui a ete rejoue** : la reprise `0e162501` sur l'arbre `round-two` a `60bde989`.

### Conditions du second audit

**J'ai joue, cinq parties.** Worktree dedie `s31-nadia-B2`, jar reconstruit depuis ce worktree,
reacteur complet, **JDK 21.0.10 - version rapportee par la machine virtuelle qui execute le jar,
pas par l'entree de toolchain** (`ADR-0071`) -, lance sur le **port 8081**.

- une partie de dix manches avec **une erreur volontaire sur chaque manche**, ecran de fin atteint,
  score 10 ;
- une partie ou **je me suis trompee trois fois sur chacune des dix manches**, pour lire la
  revelation dix fois de suite ;
- **trois parties supplementaires** pour observer le tirage : onze items pour dix manches, donc un
  item ecarte par partie, et j'ai releve lequel a chaque fois ;
- deux items presses **en cible** - le tuyau d'arrosage et la combinaison de plongee - jusqu'a la
  revelation ;
- **et j'ai relu les onze items dans le fichier de contenu**, y compris celui que le tirage ecarte.

**La porte technique est rouge sur cet arbre, et j'ai verifie que ce rouge ne me concerne pas avant
de prononcer.** Le seul test en echec (`ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`)
reproche a un rapport de qualite de citer un identifiant fictif **en preuve** ; aucun paquet, aucun
item, aucune fiche n'est en cause, et `theo-archi` le repare en parallele. Le jeu se lance, se joue
et se termine : **il est evaluable, et je prononce.** Le detail de ce raisonnement est en section 0
de `squad/sprints/s31-second-passage-de-sciences-et-emc-cp/reaudit-nadia-instance-B.md`.

**Ce que l'audit ne couvre pas** : j'ai lu le HTML servi, pas la page rendue par un navigateur.
Couleur, contraste, taille et position ne sont pas juges ici, **et ni la levee du refus ni les deux
reserves n'en dependent**.

### Mes quatre motifs de refus, recomptes un par un

**Le titre du commit de reprise n'annonce qu'un de mes quatre motifs.** J'ai donc verifie les trois
autres nommement, sans me fier au titre. **Les quatre sont payes.**

**Motif 1 - les quatre proprietes de `FER-1` servies par un item qui teste ce qu'il annonce. Paye.**
Etat sur les propositions telles qu'elles m'ont ete servies a l'ecran :

| Propriete du libelle | Item | Bonne reponse | Distracteurs servis | Un enfant en envisage-t-il un ? |
|---|---|---|---|---|
| **resistance** | le pont | le metal | **le bois**, le verre | **oui, le bois** - un pont en bois existe, et il faut savoir pourquoi il ne suffit pas pour des camions |
| **isolation** | le manche de poele | le bois | **le metal**, le verre | **oui, le metal**, la conception fausse utile. Item inchange : c'etait deja le bon |
| **souplesse** | le vetement | le tissu | **le cuir**, le metal | **oui, le cuir** - un vetement en cuir existe et il est bien moins souple |
| **impermeabilite** | la combinaison de plongee | le plastique | **le tissu**, le bois | **oui, le tissu**, reponse spontanee sur un vetement |

**Quatre proprietes sur quatre, contre une sur quatre la veille.** `FER-1` n'est plus seulement
tenue a la lettre : **son objet est atteint. C'est la barre que j'avais ecrite, elle est franchie, et
le refus tombe.**

**Motif 2 - `PLA-1` sur les items 6, 10 et 11. Paye sur les trois items obligatoires.** Ma demande
etait *"au moins un distracteur qu'un enfant choisirait vraiment"* sur les items 6, 10 et 11, **et de
preference aussi 8**. Les trois obligatoires y sont. **L'item 8, le tuyau d'arrosage, ne l'est pas** -
il offre encore *le verre* et *le metal*. **C'etait facultatif dans mon refus et cela reste
facultatif** : ce point devient `MAT-R2`. **Une barre qu'on releve apres coup fait de ma porte un
caprice.**

**Motif 3 - l'indice qui ne voulait rien dire. Paye.** *"ne laisse jamais passer l'eau, meme
liquide"* est remplace par *"Ce matériau ne garde jamais aucune odeur ni aucun goût, même après des
centaines de lavages."* **C'est vrai** - le verre ne retient ni gout ni odeur, a la difference du
plastique - **et c'est lisible par un enfant de CM1**. Lu a l'ecran apres erreur volontaire, sur
trois parties.

**Motif 4 - la sixieme famille jamais enseignee. Paye.** Le caoutchouc cede la place au plastique,
enseigne en tache A par le gobelet. Mon refus offrait exactement ces deux issues et c'est la seconde
qui a ete prise.

*Point nomme, non retenu* : la reponse la plus exacte pour une combinaison de plongee serait le
**caoutchouc** (neoprene). Ranger un polymere de synthese dans les matieres plastiques est defendable
au cycle 3, ou les cinq familles enseignees sont bois, metal, verre, plastique et tissu - **et
surtout c'est l'issue que j'ai moi-meme autorisee**, je ne la reproche donc pas. Si le paquet
enseigne un jour six familles, cet item devra recuperer le caoutchouc.

*Le cuir n'est pas le meme cas* : mon motif portait sur une famille servie **en bonne reponse** sans
etre enseignee. Un distracteur n'a pas besoin d'appartenir aux familles du programme pour etre un
candidat que l'enfant envisage - et c'est moi qui ai propose le cuir.

### `MAT-R1` - ce que la reprise a introduit, reserve neuve et datee du 2026-09-08

**Fait de partie jouee, mesure sur quatre parties consecutives.** L'item de la bouteille dit
maintenant *"On veut fabriquer une bouteille réutilisable des centaines de fois, qui ne doit jamais
garder le goût du dernier liquide versé. **Il faut un matériau imperméable.** Lequel choisir ?"* -
bonne reponse *le verre*, distracteurs *le plastique* et *le bois*.

**Et l'item de la combinaison, servi dans la meme partie quatre fois sur quatre**, dit *"...ne doit
jamais laisser entrer l'eau. **Il faut un matériau imperméable.** Lequel choisir ?"* - bonne reponse
**le plastique**.

L'enfant lit deux fois la meme phrase et **le plastique est juste a l'une, faux a l'autre**. Presse
*le plastique* sur la bouteille, il recoit *"Ce n'est pas ca"* et un indice qui parle du gout,
**jamais du fait que le plastique est impermeable lui aussi**. Verifie a l'ecran jusqu'a la
revelation.

Deux consequences, separees :

1. **le critere annonce n'est plus celui qui departage** - l'impermeabilite elimine le bois, c'est le
   gout qui elimine le plastique. L'item **reste solvable** parce que l'enonce dit le gout, et c'est
   ce qui en fait une reserve et non un refus ; mais il porte desormais **deux criteres**, la ou ma
   note du 2026-09-03 sur cette ligne - verifiee tenue sur les onze items au premier audit - dit
   *"une propriete par item, jamais deux : la charge cognitive double sinon"* ;
2. **le risque d'apprentissage est nommable** : un enfant peut en repartir avec *"le plastique n'est
   pas impermeable"*, qui est faux et que l'autre item du meme paquet dement.

**Pourquoi une reserve et pas un maintien du refus, dit franchement.** Ma section *"Ce qu'il faut"*
offrait deux issues sur cet item : *que l'enonce dise ce qui departage le verre du plastique*, **ou**
*que le verre cesse d'etre la reponse*. La premiere a ete prise, a la lettre. Le resultat me montre
quelque chose que je ne voyais pas en ecrivant cette phrase : **je l'ecris donc comme une reserve
nouvelle et datee, jamais comme un durcissement de l'ancienne.**

**Pour lever `MAT-R1`** : que l'item de la bouteille cesse d'annoncer *l'impermeabilite* comme
critere - par exemple en n'annoncant que la propriete qui departage reellement -, **ou** que le verre
cesse d'y etre la bonne reponse. Une phrase.

### Reserves ouvertes par ce Done

| Reserve | Objet | Regime |
|---|---|---|
| **`MAT-R1`** | L'item de la bouteille annonce l'impermeabilite alors que c'est la retention du gout qui departage, et **le plastique y est faux sous une phrase qui le rend juste a l'item de la combinaison** | **rejeu borne, 0,5 pour le paquet entier** (`PAR-10`). Les deux reserves voyagent ensemble |
| **`MAT-R2`** | L'item du tuyau d'arrosage n'offre aucune alternative serieuse (*le verre*, *le metal*) - `PLA-1`. C'etait le *de preference* de mon refus, il reste facultatif | idem, **sans un centime de plus** |

**Les deux sont non bloquantes** : le Done est prononce, la ligne ferme, et `camille-po` en fait une
story. Allouees dans `squad/decisions/REGISTRE-identifiants.md` dans le meme commit que ce bloc
(`PAR-5`, `ADR-0085`).

### Mentions de couverture partielle

**Aucune n'est due sur cette ligne.** Ma table du 2026-09-06 n'en exigeait que sur les lignes **6**,
**14** et **20** de sciences, et `SCI-CM1-11` n'en fait pas partie. La parenthese *(resistance,
isolation, souplesse, imperméabilité)* est une liste d'elements a servir, **et les quatre sont
servis par un item qui les teste** : c'est exactement ce que ce second audit a verifie. **La ligne
ferme sans reserve de perimetre, et je n'en ajoute pas apres coup.**

### Mentions de non-couverture opposables

> **`MNC-1`** - Ce Done ne couvre pas que l'enfant voie, sans faire defiler la page, un element
> quelconque du jeu, y compris le retour qui suit sa reponse - verdict, indice, proposition. Cette
> propriete est sortie de la porte pedagogique par arbitrage de l'utilisatrice du 2026-08-28
> (`DEC-0053`) ; sa dette est ouverte, croissante et assumee. Aucune mesure ne l'a instruite sur ce
> paquet.

### Ce dont je reste la seule garde, mis a jour au second audit

| Propriete | Garde executable ? |
|---|---|
| **`PLA-1`** - une proposition est un candidat, jamais un remplissage | **aucun de portee generale** (`ADR-0086` : clause non encodable). Le test ajoute par la reprise verifie **les trois materiaux nommes dans mon refus sur les trois items nommes** : c'est un cliquet contre la regression exacte, pas un garde de la propriete. `MAT-R2` le prouve - il est vert et l'item du tuyau n'a pas d'alternative serieuse |
| **Qu'un item teste la propriete qu'il annonce** | **aucun.** C'est `MAT-R1`, et c'est le coeur de ce que mon refus faisait payer. **Route a `theo-archi`** - je dis la propriete, jamais la forme |
| **`SCI-1`** - qu'un indice dise quelque chose de vrai | **partiel, contre la chaine exacte.** Le test ajoute interdit la formule *"meme liquide"* dans l'indice d'un item nomme ; un indice neuf qui ne veut rien dire passerait |
| **La coherence familles enseignees / familles servies en bonne reponse** | **oui, desormais.** Toute bonne reponse de la tache de choix doit etre une famille enseignee en tache A. **C'est exactement ce que j'avais route a `theo-archi` le 2026-09-07, et c'est paye** |
| **`FAB-1b`** telle que le test l'encode maintenant | **plus faible que mon exception ne le dit.** Il exigeait six bonnes reponses distinctes, il exige desormais qu'aucun materiau ne domine **la totalite** de la tache - il accepterait cinq items identiques sur six. **Le contenu, lui, varie reellement** (cinq materiaux sur six items) : ce n'est donc **pas un motif**, mais *varier* n'est pas *ne pas etre constant*. **Route a `theo-archi` et `lea-dev` comme un ecart de garde-fou** |
| **L'exactitude de chaque appariement propriete / materiau** | **aucun** |

### Etat des verdicts et des reserves de ce jeu au 2026-09-08

| Date | Verdict | Etat |
|---|---|---|
| 2026-09-07 | **REFUSE** | **leve le 2026-09-08.** Son bloc reste lisible ci-dessus, non efface |
| 2026-09-08 | **VALIDE SOUS RESERVE** | en vigueur. `SCI-CM1-11` ferme |

| Reserve | Ouverte le | Etat |
|---|---|---|
| `MAT-R1` | 2026-09-08 | **ouverte**, non bloquante |
| `MAT-R2` | 2026-09-08 | **ouverte**, non bloquante |

### Ce que je route, au second audit

| Suite | Destinataire | Ce que j'attends |
|---|---|---|
| **`SCI-CM1-11` : `non couvert` -> `couvert-valide`**, sans mention de couverture partielle | `camille-po` | **Je ne touche pas la matrice** : le mouvement se fait sur la foi de ce bloc |
| **`MAT-R1` et `MAT-R2`** | `camille-po`, via `sofia-sm` | Une story. **Rejeu borne, 0,5 pour le paquet entier** (`PAR-10`) |
| **Un item teste la propriete qu'il annonce** | `theo-archi` | Propriete de contenu, probablement non encodable comme `PLA-1`. **A nommer comme telle plutot qu'a laisser croire couverte** |
| **`FAB-1b` : le test accepte cinq bonnes reponses identiques sur six** | `theo-archi`, `lea-dev` | Un garde-fou relache au-dela de ce que ma correction de lecture du 2026-09-07 demandait. Le contenu est conforme |
