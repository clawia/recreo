# Fiche de conformite - bandit-manchot-texte-narratif-ce1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-texte-narratif.yaml` (20 items, type `question`,
  texte support), ecrit en s53 (`S53-FRA-TEXTE-NARRATIF`), items de Sara et de Nour corriges par le branchement (`D1`, `D2`
  de `lecture-nadia-palier-1.md`).
- **Entree de catalogue** : `bandit-manchot-texte-narratif-ce1`, titre servi *"Lis et réponds"*, consigne servie *"Lis le
  petit texte, puis réponds à la question."*, `nombreDeManches: 10`. Branchee par `marc-dev`, commit `4dd6f298`, branche
  `s57-marc-fra-ce1-04`, **non fusionnee** au moment du verdict.
- **Ligne declaree** : `FRA-CE1-04`. **Story** : `S57-BR-FRA-CE1-04`. **Decision** : `DEC-0120` A.33, A.58.
- **Competence programme visee** : `squad/programme/francais/competences-ce1.md`, `FRA-CE1-04`, *"Comprendre un texte
  narratif court lu seul : personnages, lieu, moment, ordre chronologique des événements."*
- **Conditions opposables** : `RCT-CE1-1` a `RCT-CE1-3`, `TRV-CE1-1` a `TRV-CE1-7`, `LON-CE1-1` a `LON-CE1-5`
  (`ARBITRAGE-PREALABLE-s48-francais-ce1.md` ; `ARBITRAGE-s47-longueur-du-texte-support-au-ce1.md`) ; `D1` et `D2`
  (`squad/sprints/s57-version-cp-ce1-cm1-trois-instances/lecture-nadia-palier-1.md`, section 1.1).
- **Statut global** : **VALIDE SOUS RESERVE**, prononce le 2026-10-15 par `nadia-prof` (s57, palier 1, instance A), apres
  avoir joue le jar de la branche. Fiche ouverte par ce verdict. Rejeu du 2026-10-16 (s57, palier 2) : `RCT-CE1-R1` levee
  sur le jar de `3eef868d` (textes de `c77560f2`) ; aucune reserve ouverte, statut **VALIDE**.
- **Derniere mise a jour** : 2026-10-16.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

### Verdict du 2026-10-15 (`nadia-prof`, s57, palier 1, instance A) : **VALIDE SOUS RESERVE**

**Done pedagogique prononce.** `camille-po` peut passer `FRA-CE1-04` a `couvert-valide`, sans mention de couverture
partielle, **apres la fusion de `4dd6f298`** (ou d'un commit dont le diff de contenu et de catalogue est identique), et faire
une story de la reserve `RCT-CE1-R1` (section 5), non bloquante.

#### 1. Ce que j'ai fait

- **Lecture complete** prealable : `lecture-nadia-palier-1.md`, section 2.1 (`7161ab69`), 20 items, version d'avant `D1` et
  `D2`. **Les deux items corriges relus** ici, au jeu (section 3).
- **Jar joue** : celui que `marc-dev` a construit dans le worktree de la branche (`bootstrap/target/recreo.jar`, 2026-10-14
  22:36), lance en lecture seule. **Ce qu'il sert est le commit** : le paquet et le catalogue extraits du jar sont
  identiques a `git show 4dd6f298:<chemin>` aux fins de ligne pres (`diff` apres retrait des `\r`, sortie vide). Lance par
  `java -jar <jar> --server.port=18511` (port de A, palier 1 ; `netstat -ano` filtre sur `:18511 ` vide avant) ; journal :
  *"Tomcat started on port 18511"*, PID 34412, arrete par ce PID seul, port rendu. Machine virtuelle du jar : celle du
  `PATH` de ce poste, 21.0.12.1, pas celle de la toolchain. Dossier de poste `s57-p1-nadia-prof-a`, hors depot.
- **Build** : `bash outils/mvn-jdk21.sh -B clean verify`, racine, sans `-pl`, sur `round-two` a `1a3ec5d9` plus le diff
  `afade53a..4dd6f298` applique dans mon worktree puis retire : resultat en section 7. Le sommet `a41bb408` n'ajoute que le
  rapport du developpeur.
- **Accueil** : `GET /niveaux/CE1/matieres/francais` sert *"Lis et réponds"* parmi 17 jeux, vue *"Tout voir"* du CE1 : 33
  jeux ; une seule fois sur chacune des deux pages, aucun titre en double. `GET /` sert *"Choisis ta classe."* et aucune
  liste de jeux.
- **Par les routes servies** (`POST /parties/bandit-manchot-texte-narratif-ce1`, puis `/coups`, script de poste) : 30
  parties de dix manches, **les 20 items tires**, bonne reponse au premier coup, apres une, deux et trois erreurs ; 20
  revelations sur 20 items. **0 ecart** : propositions servies identiques au fichier, aucun indice avant la premiere erreur,
  indice identique au fichier apres chaque erreur, revelation de la bonne reponse du fichier.
- **Rendu a 744 x 551, apres une erreur**, les 20 items un par un : methode de la fiche de `bandit-manchot-verbe-sujet-ce1`
  (rejeu du 2026-10-14, section 1) - page de matiere servie, fragment de la manche jouee insere dans `#jeu`, Chrome sans
  tete en `--dump-dom`, `getBoundingClientRect()`, aucun port de debogage. `innerWidth` x `innerHeight` lus a 744 x 551 sur
  les 20 mesures (fenetre de 760 x 646). **Etalonnage refait ce jour** sur le jeu `bandit-manchot-verbe-sujet-ce1` du meme
  jar : 22 items a 484 - 534 et 2 a 532 - 582, les cotes de cette fiche a l'unite.

#### 2. Ancrage au programme

Le paquet travaille **cette** ligne, ses quatre questions : personnage 4, lieu 4, moment 5, ordre 7, sur un texte que
l'enfant lit seul, de 22 mots au plus. La reponse est ecrite dans le texte ; l'enfant la preleve, il ne la deduit pas. La
frontiere avec `-06` (inference) tient : aucune bonne reponse n'est absente du texte. Avec `-05` (reprises) : *"Ce soir, il
la partage avec Élise."* fait lire *la*, mais la question porte sur le moment et l'indice *"Cherche le mot « partage »"* y
mene sans resoudre la reprise (`lecture-nadia-palier-1.md`, 2.1, joue et confirme).

#### 3. Les points de mon arbitrage, joues

| Point | Constat sur le jar |
|---|---|
| `D1`, la majuscule | Sur aucun des 20 items la bonne reponse n'est la seule a majuscule. Sara : *pèle une pomme* / *la coupe en morceaux* / *la mange* ; Nour : *range ses jouets* / *se lave les mains* / *se met à table*. **Tenu** |
| `D2`, l'indice de Sara | *"Relis le début du texte."*, servi apres chaque erreur : il dit ou regarder, ne contient aucune proposition, ne fait rien deduire du monde. **Tenu** |
| Limite nommee de `D1` (items *"en dernier"*, *"apres"*, *"a la fin"*) | La majuscule reste sur le premier evenement, qui y est un distracteur (Inès, Paul, Ibrahim, Chloé) : elle en elimine un, jamais la reponse. **Sans reserve**, comme ecrit |
| `LON-CE1-3`, `-4` | 22 mots, 4 phrases, 10 mots par phrase au plus ; desormais gardes par `PaquetCe1TexteNarratifTest` (`S4`, rapport du developpeur) |
| Recommandation d'Inès (*"Elle sort dehors."*) | Non prise. **Pas une reserve**, comme ecrit le 2026-10-14 ; je ne la durcis pas |

**Le signe neuf que `marc-dev` signale (*"la"*, *"se"*), mesure.** Sur Sara et sur Nour, la bonne reponse est la seule des
trois a ne pas commencer par le meme mot que les deux autres. **Correction de mon propre constat : ce signe vient de ma
forme d'exemple** (`lecture-nadia-palier-1.md`, 1.1), verifiee contre `RCT-CE1-2`, `LON-CE1-5`, `TRV-CE1-2` et `TRV-CE1-5`,
pas contre le principe de `J1`. Le developpeur l'a reprise mot pour mot ; la faute de l'avoir ecrite est la mienne.

Mesure sur les 20 items (premier mot de chaque proposition, sans casse) : **6 items** ont une proposition dont le premier mot
differe des deux autres, qui eux sont identiques. Elle est la bonne reponse sur **4** (Sara, Nour, *"au marché"*, *"au
printemps"*) et un distracteur sur **2** (*"Inès enfile ses bottes"*, *"au parc"*). **Ce n'est pas un motif de refus, ni
une reserve**, pour deux raisons que je donne pour qu'on puisse les contester :

1. **Le signe n'est pas regulier.** La majuscule de `D1` designait le premier evenement 6 fois sur 6 et portait un sens que
   l'enfant de CE1 apprend en classe (*la majuscule, c'est le debut*). L'intrus du premier mot mene a la reponse 4 fois sur 6
   et a l'erreur 2 fois ; il ne porte aucun sens enseigne.
2. **Sur Sara, il est aussi une lecture juste.** *"la coupe"* ne se comprend que si *la pomme* est deja dans le texte :
   l'enfant qui ecarte *"la coupe"* parce qu'on ne peut pas couper *la* pomme avant de l'avoir nommee lit le texte, il ne
   devine pas.

**Ce que j'en garde** : sur les lots a texte support suivants (`-05`, `-06`, `-07`), je compte l'intrus du premier mot
comme je compte la majuscule, et je le juge de nouveau s'il devient regulier sur une question.

**Exactitude** : 20 bonnes reponses justes sur 20, relues a l'ecran avec leur texte. **Distracteurs** : tous copies du texte,
tous plausibles (un autre personnage, un autre lieu, un autre moment, un autre evenement du meme texte). Aucun absurde.

**Observation, sans reserve** : l'indice de Chloé (*"Que se passe-t-il à la fin ?"*), *"Cherche ce qui arrive en
dernier."*, reformule la question au lieu de dire ou regarder ; *"Relis la dernière phrase."*, deja employe sur trois items,
dirait ou. Il ne dit rien de faux (`TRV-CE1-5`). Je l'avais lu le 2026-10-14 sans le relever ; je ne le transforme pas en
barre.

**Biais et ton** : 34 prenoms, 17 et 17, d'origines variees ; Samir casse les œufs et Jade melange la farine, Aya joue au
foot, Nour range ses jouets. Aucune activite distribuee par genre, aucun contexte que tous les enfants n'ont pas (ecole,
parc, cantine, marche, piscine). Ton neutre.

#### 4. Le titre *"Lis et réponds"*, pour l'enfant

**Tenu.** C'est le titre de l'entree du CP (`FICHE-bandit-manchot-texte-narratif-cp.md`), et c'est la meme action : lire un
texte court, repondre. **Aucun ecran ne montre les deux** : `/` demande la classe d'abord, et chaque page de niveau (par
matiere ou *"Tout voir"*) ne sert le titre qu'une fois (section 1). Un titre propre au CE1 devrait dire ce qui change, la
longueur du texte, et un enfant de CE1 n'a pas besoin de lire *"plus long"* pour jouer. Deux mots, tous deux de la consigne,
lisibles seuls en debut de CE1. Precedent : *"Un ou plusieurs ?"* sert deja le CP et le CE1. **Pas une reserve.**

#### 5. Reserve neuve, datee du 2026-10-15, non bloquante

**`RCT-CE1-R1` - a 744 x 551, apres une erreur, l'indice est sous le pli sur 9 items sur 20.** Cause visible : les
propositions passent sur deux rangees (sept items) ou trois (Ibrahim, Chloé) au lieu d'une ; sur Léa, Louis et Noé, le
texte support fait aussi deux lignes. Cotes, item par item :

| Items | Verdict *"Ce n'est pas ça. Essaie encore !"* | Indice |
|---|---|---|
| 11 items : personnage 4, moment 4 (tous sauf Louis), lieu 2 (Adam, le pain), *"Qui passe en dernier ?"* | 441 - 471, visible | 500 - 550, visible |
| Sara, Nour, Inès, la vague | 493 - 523, visible | **552 - 602, entierement sous le pli** |
| Léa, Louis, Noé | **523 - 553, coupe de 2 px** | **582 - 632, entierement sous le pli** |
| Ibrahim, Chloé | **575 - 605, entierement sous le pli** | **634 - 684, entierement sous le pli** |

**Pourquoi ce n'est pas un refus** : la reponse de l'utilisatrice a `Q-N4` (2026-10-14, *non*) fait du pli d'un jeu neuf une
reserve que je nomme et date, sur le regime de `RDO-CP-R1` et de `TPH-CE1-R1`. Je ne releve pas cette barre. **Ce qui la
rend lourde sur deux items** : sur Ibrahim et Chloé, apres une erreur, l'enfant ne voit ni le verdict ni l'indice sans faire
defiler, la situation de `RDO-CP-R1`.

**Ce qui la leve** : a 744 x 551, apres une erreur, le bas du bloc `.indice` est au-dessus de y = 551 sur les 20 items. Deux
voies, au choix du developpeur et de `theo-archi`, comme pour `VSJ-CE1-R2` : la forme commune des propositions longues
(`Q-R3`), ou des propositions plus courtes sur les items concernes, sous `RCT-CE1-2` (copie du texte, eventuellement
raccourcie), `LON-CE1-5` et `D1`, le test du paquet vert dans le meme commit. **Je ne choisis pas la voie.**

#### 6. Ce dont je reste la seule garde sur cette ligne

`PaquetCe1TexteNarratifTest` garde `D1`, la forme de `D2`, `LON-CE1-3` et `-4`, `RCT-CE1-2`, une borne de `RCT-CE1-3`,
`TRV-CE1-2`, `-5`, `-6` (equilibre des prenoms) et l'entree de catalogue. **Il ne garde pas** (liste du developpeur,
verifiee) : que la bonne reponse se lise sans inference (`RCT-CE1-1`), qu'un indice fasse relire et dise vrai (`TRV-CE1-5`),
l'absence de passe simple, ce que font les personnages selon leur genre, l'intrus du premier mot (section 3), l'exactitude
des 20 reponses, la position de l'indice par rapport au pli (`RCT-CE1-R1`). **Je les tiens en jouant.**

#### 7. Porte technique, ce que j'ai lance

`bash outils/mvn-jdk21.sh -B clean verify`, racine, sans `-pl`, sur `1a3ec5d9` plus le diff du lot : `BUILD SUCCESS`,
**2580 tests** (120, 31, 486, 1566, 371 et 6 d'integration), 0 echec, 0 erreur, 0 ignore ; JDK 21.0.10 (`java.version` des 59
rapports Surefire de `bootstrap`, une valeur). C'est le total du rapport du developpeur sur sa branche, module par module.
**Ce n'est pas la CI** : les jobs `prefixes`, `chemins` et `outils` du commit `4dd6f298` sont ceux du developpeur
(`rapport-marc-dev.md`, sur sa branche), je ne les deduis pas.

**Identifiants** : un neuf, `RCT-CE1-R1`. Forme copiee de `TPH-CE1-R1` : trois segments, niveau au milieu, marqueur `R` dans
le dernier (`ADR-0100`) ; prefixe `RCT-CE1` deja alloue (`RCT-CE1-1` a `-3`) ; `git grep "RCT-CE1-R"` ne rendait rien avant
d'ecrire ; ligne de la section 4 de `REGISTRE-identifiants.md` dans le meme diff.

**Tarif consomme** : audit 1,0 (poste 7, instance A). Marge de rejeu de A intacte.

**Signe** : `nadia-prof`, s57, palier 1, instance A, 2026-10-15.

### Rejeu du 2026-10-16 (`nadia-prof`, s57, palier 2, instance A, p2-10) : `RCT-CE1-R1` **levee** ; statut **VALIDE**

**Done pedagogique prononce, sans reserve ouverte.** `FRA-CE1-04` reste `couvert-valide` ; `camille-po` peut fermer
`S57-RCT-CE1-R1`. Le verdict du 2026-10-15 et sa reserve restent ecrits.

#### 1. Objet joue

- **Voie des textes** (`reponse-nadia-pa7-pa8.md`, 1.2) : commit `c77560f2` (`marc-dev`), fusionne par `4e5c9314`. Le diff du
  paquet entre `e8d14ddb` et `3eef868d` porte les trois jeux de propositions d'Inès, d'Ibrahim et de Chloé (items 13, 17 et 18
  comptes depuis 0), mot pour mot ceux de `reponse-nadia-q-palier-2.md` 3.4 ; textes support, enonces et indices inchanges ;
  rien d'autre.
- **Jar** : worktree `s57-nadia-p2-a3` a `3eef868d`, `bash outils/mvn-jdk21.sh -B clean package -DskipTests` (`BUILD SUCCESS`).
  `ce1-texte-narratif.yaml` du jar egal a celui de `3eef868d` et de `7c9ac220` (SHA-256 `6d8a26a2...`, retours chariot retires).
  `recreo.css` : celle de `0c0d2142`, forme commune comprise. `git diff --quiet 7c9ac220 3eef868d` sur le code sort 1 : la seule
  ligne servie qui change est la phrase de revelation des ecrans a propositions en image, qui ne s'affiche pas sur ce jeu.
- Port 18511 libre avant ; PID 42696 lu dans le journal, arrete par ce PID seul ; Chrome sur un profil de mon dossier de poste,
  sorti seul. Done technique non lance par moi.
- **Joue** a trois largeurs, fenetre verifiee a chaque manche : 744 x 551 (8 parties), 720 x 551 (11), 768 x 551 (6). A chaque
  largeur, **les 20 items joues deux fois en erreur**, les deux distracteurs presses, puis la revelation ; 0 ecart contre le
  fichier. Captures d'Inès, d'Ibrahim et de Chloé a 744, apres chaque erreur et a la revelation, regardees.

#### 2. Le critere de levee, joue

Critere du 2026-10-15 (section 5), inchange : a 744 x 551, apres une erreur, le bas de `.indice` au-dessus de y = 551 sur les 20
items. Condition de `reponse-nadia-q-palier-2.md` 3.4 : dans les six ordres.

| Point | 744 | 720 | 768 |
|---|---|---|---|
| Inès, Ibrahim, Chloé, bas de `.indice`, six ordres | **478** | 478 | 478 |
| Les 20 items, ordre servi | **20 sur 20**, 434 a 508 | 20 sur 20 | 20 sur 20 |
| Couples (item, ordre) | **240 sur 240**, maximum 508 | 240 sur 240, maximum 508 | 240 sur 240, maximum 508 |

**Tenu.** Les trois propositions passent sur deux rangees au lieu de trois ; sur la capture, l'enfant qui s'est trompe lit le
verdict et, a cote, *"Relis la dernière phrase."* (Ibrahim), *"Cherche ce qui arrive en dernier."* (Chloé), *"Relis le texte
jusqu'à la fin."* (Inès), en entier, sans faire defiler. Le maximum de 508 est sur les items 5, 6 et 9 (comptes depuis 0 :
Noé, Léa, Louis) ; sur ces trois, la page depasse 551 de 15 px apres l'erreur et la barre de defilement apparait, sous un indice
qui reste visible. C'est le mecanisme deja classe en observation (`reponse-nadia-q-palier-2.md` 3.2), pas une barre neuve.

**Revelation, hors levee** (`Q-R2`) : l'indice descend sous le pli sur 9 items, la reponse revelee restant au-dessus. Observation.

#### 3. Les trois jeux de propositions, pour l'enfant

| Item | Propositions servies | Ordre des faits dans le texte |
|---|---|---|
| Inès | **saute dans une flaque** / enfile ses bottes / sort dehors | enfile, sort, *puis* saute : la bonne reponse est le dernier fait. Juste |
| Ibrahim | **s'assoit près de la fenêtre** / attend le bus / monte | attend, monte, s'assoit : dernier fait. Juste. *monte* est une action d'Ibrahim (*il monte*), copie raccourcie du texte |
| Chloé | **une fleur pousse** / Chloé plante une graine / elle l'arrose | plante, arrose, *un jour* la fleur pousse : la fin. Juste |

**Aucun test ne garde l'ordre des faits** : je le tiens en le lisant. `RCT-CE1-2` (copie du texte, raccourcie) et `LON-CE1-5`
(six mots au plus) tenus. `D1` : aucune bonne reponse seule a majuscule sur les 20 items (recompte par script). Les trois items
d'Inès et d'Ibrahim n'ont plus de majuscule ; sur Chloé, la seule est sur un distracteur.

**L'intrus du premier mot, recompte comme je l'avais promis (section 3).** 5 items au lieu de 6 : bonne reponse sur 4 (*au
marché*, *au printemps*, Sara, Nour), distracteur sur 1 (*au parc*). Celui qui disparait est le distracteur d'Inès, retire par
**mon** texte. Le signe mene donc plus souvent a la reponse qu'au 2026-10-15 (4 sur 5 au lieu de 4 sur 6). **Je ne le classe pas
en reserve**, et je dis pourquoi plutot que de le taire : il porte sur cinq items de trois questions differentes (lieu, moment,
ordre), au plus deux par question ; il ne porte aucun sens enseigne ; sur Sara, l'ecarter est une lecture juste. S'il passe a un
troisieme item d'une meme question, je le rejuge.

**Les 17 autres items** : textes, propositions et indices inchanges depuis le verdict du 2026-10-15 ; leurs reponses restent
justes. Pli constate ci-dessus.

#### 4. Ce que je n'ai pas joue, et ce que je garde seule

**Non mesure** : un appareil reel, une autre police que celle que la feuille sert, un autre navigateur, la lecture d'ecran. Les
largeurs 720 et 768 ne sont pas dans le critere ; je les ai mesurees parce que la demande les nommait.

**Seule garde** : l'ordre des faits, l'exactitude des 20 reponses, l'intrus du premier mot, la position de l'indice par rapport
au pli. Aucun test ne les voit.

**Identifiants** : aucun neuf, aucun renomme. **Tarif consomme** : rejeu 0,5 (p2-10, instance A).

**Signe** : `nadia-prof`, s57, palier 2, instance A, 2026-10-16.
