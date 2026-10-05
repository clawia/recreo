# Arbitrage prealable de s51 - la borne a trois instances : condition (ii) non remplie, s50 porte
# une occurrence de la forme (c), l'instance C n'est pas engagee

- **Auteur** : `nadia-prof`, professeure des ecoles, **seule autorite sur le Done pedagogique**.
- **Date** : 2026-10-05.
- **Arbre** : `round-two` a `f68458de` (cloture de s50). L'arbre de travail porte des fichiers non
  commites d'autres agents (`DEC-0110`, `ADR-0104`, deux tests d'architecture) ; je les lis, je n'y
  touche pas.
- **Question posee** : `DEC-0110` section 8, points 1 a 5 - la condition suspensive de l'instance C
  (mathematiques CE1).
- **Reponse, avant tout le reste** : **REFUS de deriver une borne a trois instances pour s51.** La
  condition (ii) de `ARBITRAGE-PREALABLE-s46...` section 5 n'est pas remplie : **s50, dernier sprint
  parallele, porte une occurrence de la forme (c)** - deux instances de `nadia-prof` ont ecrit le meme
  fichier dans le meme scratchpad, sans le savoir, a treize secondes d'intervalle (section 2.3).
  **Issue de `DEC-0110` section 8 : la troisieme.** Les trois postes de C sont *"non engage, budget
  libere"*, 3,0, report a s52. A et B sont inchanges, 8,0.
- **Audit degrade, et je le declare** : aucun increment n'est en jeu, je n'ai rien joue. J'ai lu
  `DEC-0110`, mon arbitrage de s46, la review, la retro, le rapport et le planning de s50, l'historique
  Git de s50, et les transcriptions des quatre instances de `nadia-prof` qui ont audite en parallele
  a s50. Ces transcriptions vivent **hors du depot** ; c'est pourquoi je recopie ici ce qu'elles
  montrent, avec leurs horodatages.

---

## 1. Deux corrections avant de m'en servir

### 1.1 L'instance B de s50, c'etait moi, et j'ai vu la collision sans l'ecrire

Au second passage de `S50-ART`, a 21:33:49, j'ai lance une commande que j'ai moi-meme intitulee
*"Inspect scratchpad for collisions"*, puis j'ai deplace mon travail dans un sous-repertoire dedie a
21:34:15. **Je n'ai rien ecrit de cet incident** : ni dans la fiche, ni dans mon message de retour.
`grep -rn -i "scratchpad\|collision" squad/conformite/FICHE-bandit-manchot-decrire-une-oeuvre-cp.md
squad/sprints/s50-francais-ce1-sourcing-bloque-par-cloudflare/` ne rend aucune mention de cet
incident. La review et la retro de s50 ne pouvaient donc pas le porter. **Un incident que j'ai vu et
que je n'ai pas ecrit n'a pas eu lieu pour le depot** ; c'est ma faute, et c'est elle que ce document
repare.

### 1.2 Ma condition (i) se lit en lots, comme je l'ai appliquee a s46

En s46, j'ai cite *"8,0, plafond individuel 5,0, au plus trois lots (`PAR-8`, `PAR-9`)"*. `PAR-9`
(s30) dit *"4,0 par instance, marge de rejeu comprise"*. Mais ma propre table de s46 (section 2.3)
compte l'engage **en lots**, marge hors compte : s41 y figure a 8,0 avec une instance B a 4,5/5,0.
Depuis s41 (`DEC-0095`, `DEC-0098`), toutes les ouvertures appliquent cette lecture. **Je juge la
condition (i) dans la lecture que j'ai moi-meme appliquee en l'ecrivant**, pas dans une lecture plus
stricte trouvee apres coup. L'ecart entre `PAR-9` et la pratique est route en section 7.

---

## 2. Condition (ii) - s50 contre les formes (a), (b) et (c)

**Les trois formes, mot pour mot** (`ARBITRAGE-PREALABLE-s31-ce-qui-ferme-une-ligne-d-emc-cp...`,
section 8) :

- **(a)** une story qui modifie un paquet et une story qui modifie sa consigne dans le meme sprint,
  laissant la consigne fausse sur le paquet livre ;
- **(b)** un chiffre de sprint que ni l'une ni l'autre instance ne peut verifier depuis sa moitie, et
  qu'un tiers trouve faux en recomptant ;
- **(c)** deux instances ecrivant dans une meme ressource partagee sans le savoir - identifiants,
  fichier, scratchpad.

*"Une seule occurrence de l'une des trois formes suffit."* La barre est ecrite depuis le
2026-09-07. Je ne la touche pas.

**s50 est bien un sprint parallele.** `DEC-0109` ouvre une instance B (arts plastiques CP) a cote de
l'instance A (francais et mathematiques CE1). Les transcriptions donnent les fenetres en UTC+02:00 :
A1 de 20:06 a 20:35, B1 de 20:06 a 20:26 ; A2 (second passage de `FRA-CE1-02`) de 21:26:56 a
21:46:09, B2 (second passage de `S50-ART`) de 21:27:09 a 21:54:57.

### 2.1 Forme (a) - aucune occurrence

| Constat | Methode |
|---|---|
| Les trois consignes servies de s50 sont ecrites une seule fois, dans le commit qui cree l'entree de catalogue, et jamais modifiees ensuite | `git show --numstat --format=%h <sha> -- adapters/adapter-content/src/main/resources/catalogue.yaml` sur `fc6f9c16`, `a7af90b3`, `1db6348f` : `6 0` chacun, des ajouts purs. Aucun autre commit de s50 ne touche `catalogue.yaml` (boucle `git show --name-only` sur `git rev-list 1dc095ea^..01957d9a`) |
| Le descripteur de `cp-decrire-une-oeuvre` (*"sujet et materiaux"*, sans *formes*) change dans le meme commit que les items qu'il decrit | `371218de`, story `S50-ART` : un seul commit, une seule story |
| La seule modification du paquet d'arts par une autre story est un commentaire YAML | `git show 655b319b -- adapters/adapter-content/src/main/resources/contenu/cp-decrire-une-oeuvre.yaml` : une ligne d'en-tete, *"cinq items"* devient *"six items"* |
| La consigne servie tient sur le paquet livre | consigne *"Regarde l'œuvre, puis dis ce que tu vois."*, jouee au troisieme passage (`fd1b0096`) sur le paquet final |

### 2.2 Forme (b) - aucune occurrence trouvee

| Chiffre de sprint | Recompte | Methode |
|---|---|---|
| 123/182 a la cloture | **123 `couvert-valide`, 3 `en cours`, 56 `non couvert`, 76 `non couvrable`, 258 lignes, 182 atteignables** | commande ci-dessous, sur `git show 01957d9a:squad/programme/MATRICE-couverture.md` |
| 3 lignes fermees, 120 -> 123 | coherent | meme recompte ; les trois reserves levees ne touchent aucun statut |
| Engage A 3,5 + B 2,0 = 5,5 | coherent | `DEC-0109` section *"Total du sprint reouvert"* contre `RAPPORT.md` |
| Six verdicts, treize obstacles, 35 commits | coherents | lignes des tables de `review.md` sections 5 et 6 ; `git rev-list --count 1dc095ea..01957d9a` |

```
awk -F'|' 'NF>=8 && $3 ~ /^ *(CP|CE1|CM1) *$/ { s=$8; gsub(/\*|`/,"",s); sub(/^ +/,"",s);
  if (s ~ /^couvert-valide/) c="couvert-valide"; else if (s ~ /^en cours/) c="en cours";
  else if (s ~ /^non couvrable/) c="non couvrable"; else if (s ~ /^non couvert/) c="non couvert";
  else c="AUTRE"; n[c]++ } END { for (k in n) print n[k], k }'
```

**Piege de mesure, a connaitre pour quiconque recompte** : trois lignes de la matrice ne portent pas
de barre finale (lignes `ART-CM1` sur la materialite, `SCI-CM1-02`, `SCI-CM1-12`). Un filtre `NF>=9`
les perd et rend 121/255. Le filtre `NF>=8` ci-dessus les compte.

**Ce que je ne classe pas en (b), et pourquoi.** L'affirmation *"vivier de mathematiques CE1
epuise"* du planning de s50 (section 4) est fausse (`DEC-0110` section 1). Mais un seul document
l'a ecrite, avant l'ouverture des instances, et une seule lecture de la matrice la dement. Ce n'est
pas un chiffre qu'aucune moitie ne pouvait verifier : c'est une erreur de lecture, de la famille de
la reference qui pointe vers rien. Elle est corrigee par `DEC-0110`. Elle ne maintient ni ne leve
rien ici.

### 2.3 Forme (c) - **une occurrence**, constatee dans les transcriptions

**Le fait.** Les deux instances paralleles de s50 tournaient dans la **meme session**, donc dans le
**meme scratchpad**, et sur le **meme checkout** (`git worktree list` et `git branch -a --list
'*s50*'` ne rendent aucun worktree ni aucune branche de s50). La regle de `squad/README.md`
section 9 (amendement de s30, applicable depuis s31 : *"toute paire d'instances du meme agent tournant
en parallele recoit un scratchpad aussi dedie que son worktree"*) n'a pas ete appliquee ; `DEC-0109`
ne la cite pas.

| Heure (UTC+02:00) | Instance | Geste |
|---|---|---|
| 21:31:21 | B2 | ecrit `jouer.py` a la racine du scratchpad (script de partie de `S50-ART`) |
| 21:31:34 | A2 | ecrit `jouer.py` au meme endroit (script de partie de `FRA-CE1-02`) : **le fichier de B est ecrase** |
| 21:31:54, 21:32:39 | A2 | lance son `jouer.py` : trente parties, 24 items sur 24 - **ce sont les mesures de son verdict** |
| 21:32:49 | B2 | relit `jouer.py` pour en extraire ses fonctions : echec (`IndexError`), le fichier n'est plus le sien |
| 21:33:31 | B2 | tente de corriger `jouer.py` : echec, code de sortie 1 |
| 21:33:49 | B2 | *"Inspect scratchpad for collisions"* : liste le scratchpad et l'en-tete de `jouer.py` |
| 21:34:15 | B2 | reprend tout dans un sous-repertoire dedie, ou elle reste jusqu'a son verdict |

**Methode.** Lecture des transcriptions de sous-agent de la session de s50, conservees par l'outil
hors du depot (`$HOME/.claude/projects/<projet>/838a59ef-ca02-49ab-aa8b-d05b1f239682/subagents/`) :
`agent-ac52a2b4206c1bfdb.jsonl` (A2, *"Second passage FRA-CE1-02"*) et
`agent-aebea994fe031be95.jsonl` (B2, *"Second passage S50-ART"*), par un script qui liste chaque
appel d'outil avec son horodatage et sa commande. Les horodatages des fichiers du scratchpad
concordent.

**Consequence de contenu : aucune, et je le verifie au lieu de le supposer.** La derniere execution
de `jouer.py` par A2 (21:32:39) precede la tentative de modification de B2 (21:33:31). B2 a rendu
son verdict sur des scripts ecrits apres 21:34:15 dans son propre repertoire. Aucun verdict de s50
ne repose sur un fichier de l'autre instance. **Cela ne change pas la qualification** : la forme (c)
se constate a l'ecriture concurrente, pas a son effet. C'est la lecture que j'ai appliquee a s30,
ou la meme collision de scratchpad, elle aussi sans consequence de contenu, a ete comptee.

**Une exposition de plus, que je ne compte pas comme une seconde occurrence.** A 21:30:14, B2 lance
le serveur de jeu directement depuis `bootstrap/target/recreo.jar` du checkout partage. De 21:33:01
a 21:41:19, A2 lance `mvn -B verify` sur ce meme checkout, ce qui reconstruit ce fichier pendant que
B2 joue dessus. Les deux construisent le meme arbre (`371218de`), donc le contenu servi ne change
pas. Une seule instance ecrit ce fichier : ce n'est pas la forme (c) telle qu'elle est definie. C'est
exactement ce qu'un worktree par instance supprime.

**Registre d'identifiants : pas de collision.** `git log --format=%h 1dc095ea^..01957d9a --
squad/decisions/REGISTRE-identifiants.md` ne rend qu'un commit `[NAD]`, `b29d2680` (instance A,
`HOM-CE1-R1`). L'instance B n'a alloue aucun identifiant. Le garde-fou `PAR-5`, lance ce jour sur
l'arbre de travail
(`bash outils/mvn-jdk21.sh -pl bootstrap -am test -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`),
rend *"Tests run: 10, Failures: 0, Errors: 0"*. Les fichiers commites par les deux instances sont
disjoints (`git show --stat` sur les neuf commits `[NAD]` de s50).

### 2.4 Verdict sur la condition (ii)

> **Non remplie.** s50 est clos avec une occurrence de la forme (c), constatee le 2026-10-04 a
> 21:31:34 (UTC+02:00), non ecrite dans le depot jusqu'a ce document.

---

## 3. Condition (iii) - `PAR-13` : **confirmee**

`PAR-13` (`ARBITRAGE-PREALABLE-s32...` section 6.2) se juge au niveau du terrain : *"au plus une
instance par sprint prend un terrain dont mes conditions n'ont jamais rencontre de contenu reel"*.

| Instance | Terrain neuf ? | Preuve |
|---|---|---|
| A, francais CE1 | **non** | 3 lignes `couvert-valide` (`FRA-CE1-01`, `-02`, `-16`), deux refus suivis de corrections : les conditions transverses `TRV-CE1-*` ont mordu du contenu reel. Recompte par le meme `awk`, filtre francais/CE1 : 3 `couvert-valide`, 23 `non couvert`, 5 `non couvrable` |
| B, sciences et technologie CM1 | **non** | 16 `couvert-valide`, 3 `non couvert`, 1 `non couvrable` (meme `awk`). Je confirme la reserve de `DEC-0110` : `SCI-2` n'a ete tenue que par extension, jamais sur `SCI-CM1-18` |
| C, mathematiques CE1 | **oui** | la matiere a 9 lignes fermees, mais `SYM-CE1-1` a `-3`, `MUL-CE1-4`, `MUL-CE1-5` et la designation au CE1 n'ont jamais rencontre de contenu. Je retiens la lecture de `DEC-0110`, la plus prudente |

Une seule instance sur un terrain neuf : `PAR-13` est tenue. **Cette condition ne sauve pas la
derivation** : les trois conditions sont cumulatives.

---

## 4. Condition (i) : **remplie telle que je l'ai ecrite**, et une reserve nouvelle

**Remplie.** A (3,5) + B (4,5) = 8,0, chacune sous 5,0, trois lots chacune, un rejeu de 0,5 nomme par
instance (`DEC-0110` section 2). Restent dehors 18 lignes de francais CE1 instruites
(`ARBITRAGE-PREALABLE-s48...`) et 3 lignes de mathematiques CE1 : plus de 4,0. Je leve ce que j'avais
ecrit : la barre est atteinte.

**Reserve nouvelle, datee du 2026-10-05, qui ne s'oppose pas a s51.** J'avais motive (i) par *"la
capacite d'une instance"*. Or `PAR-2` interdit qu'une troisieme instance prenne une ligne de francais
CE1 tant que A la detient. **Ce qu'une troisieme instance pouvait reellement prendre a s51 vaut
3,0** (trois lots de mathematiques CE1, plafond de `PAR-8`), pas 21,0. A la prochaine derivation, je
compterai l'excedent **prenable par l'instance supplementaire sous `PAR-1` et `PAR-2`**, pas
l'excedent brut.

---

## 5. La borne a trois instances, et l'issue de `DEC-0110` section 8

**Je ne derive pas de borne.** Ma section 5 de s46 l'a ecrit avant ce sprint : *"Si les trois sont
reunies, la derivation se fera dans un document neuf"*. Elles ne le sont pas. La formule de s32
donnerait 12,0 (3 x 5,0 moins 1,0 par paire), mais **une formule n'est pas une borne sans les
conditions qui l'autorisent** (s46, section 1.4). Ecrire 12,0, ou 11,0, ici, serait valider par
complaisance envers la consigne *"maximum d'instances"*. Cette consigne ne fonde rien (forme 3,
`squad/README.md` section 2 bis), et `DEC-0110` section 0 le dit deja.

| Issue de `DEC-0110` section 8 | S'applique ? |
|---|---|
| Borne >= 11,0 : C ouvre | non, aucune borne |
| Borne entre 8,0 et 11,0 : retirer `MAT-CE1-02`, puis `MAT-CE1-17`, garder `MAT-CE1-06` | non, aucune borne |
| **Refus : les trois postes de C sont *"non engage, budget libere"*, 3,0, report a s52** | **oui** |

**Lignes de C engagees a s51 : aucune.** `MAT-CE1-17`, `MAT-CE1-06` et `MAT-CE1-02` (moitie
textuelle) passent a s52. A et B restent tels que `DEC-0110` les engage : 8,0.

**Pourquoi je ne propose pas de garder une ligne de C dans la borne a deux.** A + B remplissent
exactement 8,0. Une ligne de C ne tiendrait qu'en retirant un poste de A ou de B, et choisir ce poste
revient a `camille-po`, pas a moi.

---

## 6. Les questions que `DEC-0110` me pose

### 6.1 La passe transverse (`PAR-4`)

**A trois instances, la question est sans objet pour s51.** Pour une derivation future, la reponse est
deja ecrite et je la confirme : la passe transverse est due, 0,5, **dans** la borne et non retranchee
d'elle (`ARBITRAGE-PREALABLE-s32...` section 4.2). Elle va a l'instance qui porte l'accumulation
(`PAR-7`, `PAR-12`).

**Constat a deux instances, que je ne transforme pas en condition de s51.** `grep -ln "passe
transverse"` sur les `DEC` de s41 a s50 ne rend que `DEC-0095` et `DEC-0098`. Toutes deux la citent
comme poste retranche de la derivation de 8,0, et aucune ne l'engage. Je n'ai trouve aucun sprint
parallele depuis s41 qui l'ait engagee. Je ne releve pas la barre de s51 pour cela. Je le route en
section 7.

### 6.2 Le premier paquet de designation au CE1 : une seule instance

**Oui, et sans partage.** `PAR-2` y oblige deja, puisque la designation au CE1 est de la
mathematique CE1. J'ajoute ce que `PAR-2` ne dit pas : **l'instance qui audite ce paquet ne porte
aucun autre terrain neuf dans le meme sprint** (`PAR-13`). Elle audite aussi le second paquet de
designation de la matiere, quand il viendra (`MAT-CE1-02` moitie designation, `MAT-CE1-14`). Ce que
le premier m'aura appris sur les zones ne doit pas rester dans une autre tete. C'est la portee **L**
de `PAR-0`.

### 6.3 `ZON-13` contre les lignes candidates qui se croisent

**Ce qui est pedagogique, et que je tranche.**

1. **`ZON-13` ne se relache pas pour les figures de symetrie.** Deux zones ne se chevauchent pas. Le
   motif est le doigt de l'enfant : *"il croit montrer l'un et presse l'autre"*. Il vaut a plus forte
   raison quand les deux lignes sont un axe et une diagonale, puisque c'est la confusion que
   `SYM-CE1-2` existe pour tester.
2. **Une zone n'a pas a couvrir toute sa ligne.** Elle couvre une portion ou cette ligne est seule
   dessinee. Le point de croisement n'appartient a aucune zone.
3. **Prolonger les lignes candidates au-dela de la figure est recevable**, et c'est l'usage en
   classe : on trace l'axe de part en part. La zone peut donc se poser sur la partie exterieure de la
   ligne.
4. **Parmi les lignes candidates d'un item, une seule est un axe de symetrie de la figure.**
   `ZoneADesigner` ne declare qu'une `bonneZone` et `ZON-14` demande un geste. Un item ou deux lignes
   tracees seraient des axes, mais une seule comptee juste, porterait une erreur de fait. Ce n'est pas
   une condition neuve : c'est l'exactitude appliquee a ce cas.
5. **Je l'auditerai par `ZON-9`.** Zones masquees, je trouve l'axe sur le dessin a la taille servie.
   Puis je revele les zones : la bonne zone doit etre sur la ligne que j'ai trouvee, et rien dans sa
   surface ne doit appartenir a une autre ligne.

**Ce qui est architecture, et que je laisse a `theo-archi`** : la forme d'une zone. Une zone a quatre
nombres est un rectangle, et le rectangle qui contient une diagonale contient tout le rectangle
dessine. Comment poser une zone sur une ligne oblique sans chevauchement reste a sa conception.

### 6.4 Le nombre de zones sur une ligne graduee

**Ce qui est pedagogique, et que je tranche.**

1. **Les zones d'une ligne graduee sont homogenes au sens de `ZON-8`.** L'enfant n'enumere pas des
   choses differentes : il lit une echelle. Le plafond de six au CP et douze au CM1 ne s'applique
   donc pas. Le plancher de trois zones s'applique.
2. **Les zones sont posees sur des graduations**, jamais entre deux, conformement a `ORD-CE1-6`.
3. **La bonne zone ne se trouve pas par approximation.** Quand elles existent, les deux graduations
   voisines de la bonne sont aussi des zones. Sinon, *"a peu pres au milieu"* suffit, et l'item cesse
   de demander de lire le pas. C'est ma regle du distracteur plausible appliquee a une ligne, et le
   motif de `ORD-CE1-6` (*"un enonce qui dit la position fait le travail perceptif a la place de
   l'enfant"*). Je l'ecris avant tout contenu, pour qu'elle ne soit pas decouverte a l'audit.

**Ce qui est mesure, et que je laisse a `theo-archi` et `ines-design`** : le plafond numerique. Il
derive du plancher de cible tactile sur la largeur servie de la ligne. *"4 x 4"* vaut pour un
quadrillage, pas pour une ligne, et je ne fixe pas ce chiffre.

**Aucun identifiant de condition n'est cree par cette section** : les points 6.3 et 6.4 appliquent
`ZON-8`, `ZON-9`, `ZON-13`, `ZON-14`, `SYM-CE1-2` et `ORD-CE1-6`. Si `theo-archi` veut faire mordre
6.4 point 3 par un test, l'identifiant s'alloue dans le meme commit que son ecriture.

---

## 7. Ce que je route

| A qui | Quoi |
|---|---|
| `camille-po` | Amender `DEC-0110` section 8 avec l'issue retenue : C *"non engage, budget libere"*, 3,0, report a s52 (`PAR-16`). Consequences : l'objectif gele passe de huit a six fermetures au mieux, 123/182 a 129/182 (70,9 %) ; `MAT-CE1-02` ne passe pas a `en cours`. Section 9 : la ligne C sort de la table d'isolation. Section 11 : `S51-MAT-SYMETRIE`, `S51-MAT-MULTIPLICATION` et `S51-MAT-COMPARER-RANGER` sont ecrites non engagees au backlog. Le compte de report de `MAT-CE1-17` passe a neuf sprints (s43 a s51) |
| `camille-po` | Decider pour s52 si la passe transverse (`PAR-4`) est engagee a deux instances (section 6.1), et trancher l'ecart entre `PAR-9` (4,0 marge comprise) et la pratique depuis s41 (8,0 de lots, rejeu dans le plafond de 5,0) (section 1.2) |
| `sofia-sm` | Ecrire l'occurrence de la forme (c) de s50 en amendement date de la retro de s50 (section 2.3), et appliquer `squad/README.md` section 9 a s51 : un worktree, une branche et un scratchpad par instance. `DEC-0110` section 9 le prevoit ; `DEC-0109` ne le prevoyait pas |
| `theo-archi` | La forme d'une zone sur une ligne oblique (section 6.3) et le plafond de zones d'une ligne graduee (section 6.4), dans son avis de faisabilite sur le materiau de C, qui sert s52 |

Je ne touche ni a `DEC-0110`, ni a la matrice, ni au registre d'identifiants.

---

## 8. Ce qui rouvrirait la derivation, pour s52

Les trois conditions de s46 restent ma barre, sans changement de texte. Deux sont acquises sur les
lectures de ce jour. Pour (i), la reserve de la section 4 s'applique. La condition qui manque se
constate a la cloture de s51 :

> **Si s51 clot sans aucune occurrence des formes (a), (b) ou (c), verifiee par moi contre les trois
> formes, transcriptions comprises, la derivation a trois instances pour s52 se fait dans un document
> neuf.** C'est la seule voie, et je m'y tiendrai sans qu'on ait a me la redemander.

---

## 9. Ce dont je reste la seule garde

| Propriete | Qui la tient |
|---|---|
| La forme (c) sur un scratchpad ou un fichier hors depot | **moi seule**, et seulement en lisant des transcriptions qui ne sont pas dans le depot. Aucun test ne voit qui ecrit un fichier temporaire |
| La forme (c) sur les identifiants | le garde-fou `PAR-5` pour les formes qu'il reconnait, moi pour les autres |
| Les formes (a) et (b) | moi seule |
| `ZON-9` et le debordement de `ZON-13` sur les figures de C, quand elles existeront | moi seule, en jouant |

---

## 10. Ce que ce document ne fait pas

- Il ne prononce aucun Done pedagogique et ne touche a aucune fiche de jeu.
- Il ne modifie ni la matrice, ni le backlog, ni `DEC-0110`, ni le registre d'identifiants.
- Il ne change pas ma borne a deux instances (8,0) et ne touche pas a l'engagement de A et de B.
- Il ne cree aucun identifiant de condition ou de reserve.
- Il ne choisit aucun moyen technique pour les zones ou les dessins.
- Il n'efface rien de s46 : il en applique la section 5.
