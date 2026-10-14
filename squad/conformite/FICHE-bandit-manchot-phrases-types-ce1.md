# Fiche de conformite - bandit-manchot-phrases-types-ce1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-phrases-types.yaml` (24 items, type `question`),
  ecrit en s53 (`S53-FRA-PHRASES-TYPES`), non modifie par le branchement.
- **Entree de catalogue** : `bandit-manchot-phrases-types-ce1`, titre servi *"Les types de phrases"*, consigne servie
  *"Lis bien, puis choisis la bonne réponse."*, `nombreDeManches: 10`. Branchee par `marc-dev`, commit `1f6642ab`,
  branche `s57-fra-ce1-10`, **non fusionnee** au moment du verdict.
- **Ligne declaree** : `FRA-CE1-10`. **Story** : `S57-BR-FRA-CE1-10`. **Decision** : `DEC-0120` A.33, A.58.
- **Competence programme visee** : `squad/programme/francais/competences-ce1.md`, `FRA-CE1-10`, *"Reconnaître une phrase
  (majuscule, point) et identifier son type (déclarative, interrogative, exclamative) par sa ponctuation."*
- **Conditions opposables** : `TPH-CE1-1` a `TPH-CE1-3`, `TRV-CE1-1` a `TRV-CE1-7`, `LON-CE1-1`, `-2`, `-5`
  (`ARBITRAGE-PREALABLE-s48-francais-ce1.md`, sections 2 et 5.1).
- **Statut global** : **VALIDE SOUS RESERVE**, prononce le 2026-10-14 par `nadia-prof` (s57, palier 1, instance A'),
  apres avoir joue le jar de la branche. Fiche ouverte par ce verdict.
- **Derniere mise a jour** : 2026-10-14.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

### Verdict du 2026-10-14 (`nadia-prof`, s57, palier 1, instance A') : **VALIDE SOUS RESERVE**

**Done pedagogique prononce.** `camille-po` peut passer `FRA-CE1-10` a `couvert-valide`, sans mention de couverture
partielle, **apres la fusion de `1f6642ab`** (ou d'un commit dont le diff de contenu et de catalogue est identique), et
faire une story de la reserve `TPH-CE1-R1` (section 5), non bloquante.

#### 1. Ce que j'ai fait

- **Lecture complete** prealable : `lecture-nadia-palier-1.md`, section 2.2 (`7161ab69`), 24 items.
- **Build** : `bash outils/mvn-jdk21.sh -B clean verify` dans le worktree de la branche, racine, sans `-pl` : `BUILD
  SUCCESS`, 2520 tests (120, 31, 486, 1506, 371 et 6 d'integration), 0 echec, 0 erreur, 0 ignore ; JDK 21.0.10
  (`java.version` d'un rapport Surefire de `bootstrap`). Job `build` seul, lance par moi. `git merge-base --is-ancestor
  729b0d8a 1f6642ab` sort 0 (porte P-2) ; `bash outils/verifier-lignee.sh` sort 0.
- **Jar** : `java -jar bootstrap/target/recreo.jar --server.port=18512` (port de A', palier 1, libre avant) ; journal :
  *"Tomcat started on port 18512"*, PID 47420, arrete par ce PID seul. Dossier de poste `s57-p1-nadia-prof-ap`.
- **Accueil** : `GET /niveaux/CE1/matieres/francais` sert *"Les types de phrases"* en dernier des quinze jeux de Francais
  CE1, lien `/parties/bandit-manchot-phrases-types-ce1`.
- **Par les routes servies** : 15 parties de dix manches, **les 24 items tires**, 223 erreurs, 37 revelations, **0 ecart**
  (propositions servies identiques au fichier, aucun indice avant la premiere erreur, indice identique au fichier apres
  chaque erreur, revelation de la bonne reponse). Bonne reponse au premier coup, apres une, deux et trois erreurs.
- **Rendu a 744 x 551, apres une erreur**, les 24 items un par un, et deux revelations : meme methode que la fiche de
  `bandit-manchot-verbe-sujet-ce1`, rejeu du 2026-10-14, section 1 (Chrome sans tete, `getBoundingClientRect()`, pas de
  port 19222). Captures a l'appui pour deux items.

#### 2. Ancrage au programme

Le paquet travaille **cette** ligne, ses deux moities : 9 items *"Quel groupe de mots est une phrase ?"* (majuscule et
point), 15 items *"Quel est le type de cette phrase ?"* (5 par type), recompte sur les champs servis. La frontiere avec
`-08` (mettre des mots en ordre) tient : aucun groupe en desordre. Avec `-09` (phrase fautive a corriger) : ici l'enfant
reconnait, il ne corrige pas.

#### 3. Les trois points que le test ne garde pas (`1f6642ab`, message du commit)

| Point | Constat, items lus a l'ecran |
|---|---|
| **Aucune phrase imperative** (`TPH-CE1-3`) | les 15 phrases de type : 5 declaratives (*Le soleil brille.*, *Mia lit un livre.*, *Les oiseaux chantent dans l'arbre.*, *Noé a faim.*, *Le train part à midi.*), 5 interrogatives (*Où est le crayon ?*, *Tu viens jouer avec nous ?*, *Quelle heure est-il ?*, *Qui a ouvert la porte ?*, *Combien coûte ce livre ?*), 5 exclamatives (*Quelle belle journée !*, *Comme il fait chaud !*, *Quel grand gâteau !*, *Comme ce chien est rapide !*, *Comme le sable est chaud !*). Aucune n'est un ordre ; *"Tu viens jouer avec nous ?"* est une question. **Tenu** |
| **Le sens d'un groupe de mots** (`TPH-CE1-2`) | les 9 bonnes reponses ont un sens ; leurs deux distracteurs reprennent les memes mots dans le meme ordre, (a) sans majuscule, (b) sans point. Le sens ne departage donc jamais : seuls la majuscule et le point le font, ce que la ligne demande. **Tenu** |
| **Aucune regle fausse dans un indice** (`TRV-CE1-5`) | cinq indices distincts, tous vrais : *"Regarde le signe tout à la fin de la phrase."*, *"Le signe final aide à trouver le type de la phrase."*, *"Lis la phrase, puis regarde son dernier signe."*, *"Regarde comment commence et comment finit chaque groupe de mots."*, *"Une phrase a un début, une fin et un sens."* Aucun ne contient une proposition. Le dernier rappelle la definition de la classe sans nommer la majuscule ni le point : il dit moins ou regarder que le quatrieme, il ne dit rien de faux. **Tenu** |

**Exactitude** : 24 bonnes reponses justes sur 24, relues a l'ecran. *"Quelle heure est-il ?"* et *"Quelle belle journée !"*
s'ouvrent pareil : seul le signe final les separe, c'est la ligne. **Distracteurs** : sur les items de type, les deux autres
noms de type ; sur les items de reconnaissance, les deux fautes de la ligne. Aucun absurde.

**Observation de ma lecture, jouee** : sur les items dont la phrase commence par un prenom, le distracteur (a)
(*"inès range ses crayons."*) manque deux majuscules. A l'ecran, la faute montree reste une faute de majuscule et la reponse
reste unique. **Sans reserve.**

#### 4. Le titre *"Les types de phrases"*, pour l'enfant

**Tenu.** Quatre mots, tous admis par `TRV-CE1-4` (*phrase* et les noms de type sont ceux de la ligne), sans niveau
(`ADR-0078`). C'est le nom de la lecon que l'enfant entend en classe, dans le registre des titres voisins (*"Le verbe et son
sujet"*, *"Les verbes au présent"*). Il annonce la plus grande partie du paquet (15 items sur 24) ; l'autre moitie,
reconnaitre une phrase, est l'etape que la meme lecon pose d'abord, et l'enfant qui la rencontre sous ce titre n'est pas
trompe sur ce qu'on lui demande. **Pas une reserve.**

#### 5. Reserve neuve, datee du 2026-10-14, non bloquante

**`TPH-CE1-R1` - a 744 x 551, apres une erreur, l'indice n'est au-dessus du pli sur aucun des 24 items.** Cause visible :
les propositions occupent deux rangees sur les 15 items de type (*déclarative*, *interrogative*, *exclamative* ne tiennent
pas sur une rangee) et deux ou trois rangees sur les 9 items de reconnaissance (trois phrases entieres). Cotes du bloc,
mesurees item par item :

| Items | Verdict *"Ce n'est pas ça. Essaie encore !"* | Indice |
|---|---|---|
| 15 items de type | 507 - 537, visible | **566 - 616, entierement sous le pli** |
| 3 items de reconnaissance a phrase courte (*Le chat boit du lait.*, *Le vent souffle fort.*, *Noé lave les verres.*) | 459 - 489, visible | **518 - 568, coupe** |
| 6 items de reconnaissance a phrase longue | **541 - 571, coupe** (10 px visibles) | **600 - 650, entierement sous le pli** |

A la revelation, *"Appuie sur la bonne réponse :"* et la reponse sont aussi sous le pli (566 - 596 sur un item de type, 600 -
630 sur un item de reconnaissance). L'enfant voit alors deux propositions barrees et la seule qui reste, qui est la bonne :
il peut finir la manche, il ne lit pas la phrase qui la nomme.

**Pourquoi ce n'est pas un refus, dit pour qu'on puisse le contester.** C'est le regime que j'ai applique a un jeu neuf
il y a trois jours : `RDO-CP-R1` (verdict et indice sous le pli a 744 x 551, l'enfant ne voit que la croix) est une reserve
non bloquante. Ici la proposition choisie est barree au-dessus du pli, l'indice est atteint en faisant defiler, et la
cause est dans la forme des propositions longues, pas dans le texte du lot : aucun contenu ne fait tenir les trois noms de
type sur une rangee. Je ne releve pas ma barre apres coup. **Mais c'est la reserve la plus lourde de ce palier** : un enfant
bloque est celui qui a besoin de l'indice (`P3`, s25), et ici il ne le voit jamais sans defiler. `DEC-0051` (s21) avait
pose que les jeux neufs naissent sans ce defaut ; je pose la question a l'utilisatrice (`Q-N4`, `squad/sprints/s57-version-cp-ce1-cm1-trois-instances/rejeu-nadia-palier-1.md`, section 8).

**Ce qui la leve** : a 744 x 551, apres une erreur, le bas du bloc `.indice` est au-dessus de y = 551 sur les 24 items.
Le moyen (rangees des propositions, place du message) est a `theo-archi` et a `ines-design`, puis au developpeur ; je
le juge en rejouant.

#### 6. Ce dont je reste la seule garde sur cette ligne

`PaquetCe1PhrasesTypesTest` garde `TPH-CE1-1`, la forme de `TPH-CE1-2` et de `TPH-CE1-3`, `TRV-CE1-1`, `-2`, `-4` a `-7`,
`LON-CE1-5` et la consigne. **Il ne garde pas** : l'absence de phrase imperative, le sens des groupes, la verite des
indices (section 3), l'exactitude des 24 reponses, la position de l'indice par rapport au pli (`TPH-CE1-R1`). **Je les tiens
en jouant.**

**Identifiants** : un neuf, `TPH-CE1-R1`. Forme copiee de `VSJ-CE1-R1` : trois segments, niveau au milieu, marqueur `R`
dans le dernier (`ADR-0100`) ; prefixe `TPH-CE1` deja alloue (`TPH-CE1-1` a `-3`) ; ligne de la section 4 de
`REGISTRE-identifiants.md` dans le meme diff.

**Tarif consomme** : audit 1,0 (poste 4, instance A'). Marge de rejeu de A' intacte.

**Signe** : `nadia-prof`, s57, palier 1, instance A', 2026-10-14.

### Amendement du 2026-10-14 (`nadia-prof`, s57, palier 1, instance A') : reponse de l'utilisatrice a `Q-N4`

Reponse de l'utilisatrice, le 2026-10-14, forme 2 (relayee par l'agent principal, non lue de sa main) : **`Q-N4` = non.**
Le pli a 744 x 551 ne redevient pas bloquant pour un jeu neuf. **Le verdict ci-dessus ne change pas** : `FRA-CE1-10` reste
**VALIDE SOUS RESERVE**, `TPH-CE1-R1` reste une reserve non bloquante, avec son critere de levee inchange. **Ma
recommandation** (*"oui pour les lots suivants"*, `rejeu-nadia-palier-1.md` section 8) **n'est pas retenue par
l'utilisatrice** : le pli reste, pour les lots a venir aussi, une reserve que je nomme et date, pas un motif de refus. Rien
n'est efface de la section 5.

### Precision du 2026-10-14 (`nadia-prof`, s57, palier 1) : ce que *"coupe"* veut dire a 518 - 568

Dans le tableau de `TPH-CE1-R1`, la ligne des trois items de reconnaissance a phrase courte (indice 518 - 568) designe un
bloc dont le texte d'une ligne reste lisible au-dessus du pli et dont le cadre est coupe a 17 px du bas (meme geometrie,
capture faite sur le paquet de `FRA-CE1-15`, fiche `FICHE-bandit-manchot-accord-groupe-nominal-ce1.md`, section 5). Les 21
autres items gardent leur constat : indice entierement sous le pli. La reserve et son critere de levee ne changent pas.
