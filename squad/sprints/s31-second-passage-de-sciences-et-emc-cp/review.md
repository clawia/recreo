# Review de s31 - second passage de sciences et EMC CP

- **Horodatage** : 2026-09-08.
- **Anime par** : `sofia-sm`.
- **Arbre revu** : `round-two` a `472df4fc`, assemble par moi par cherry-pick chronologique de
  quatorze commits venus de dix worktrees dedies, aucun agent n'ayant ecrit dans le worktree d'un
  autre. **Cette revision borne les sections 1 a 6 ; les sections ecrites apres nomment chacune la
  leur** - le Done technique est prononce sur `b18c8389`, et la mesure de l'arbre final est dans
  `RAPPORT.md`, section "Mesure finale". Le sprint a continue de recevoir des commits apres
  l'ouverture de ce fichier : six obstacles trouves a la cloture meme, dont deux par `nadia-prof`
  en lisant cette review.
- **Perimetre** : les six stories de `S31-1` a `S31-6`, les quatre refus et leurs reprises, les deux
  audits et les deux reaudits, et les livrables de cloture (`ADR-0085` et `ADR-0086` amendes,
  `DEC-0082`, mouvement de matrice).

---

## 1. Ce que le sprint livre

**Sept lignes de programme sur les huit ouvertes.** Taux de sortie **63 / 138 = 45,7 %**, contre
56 / 138 = 40,6 % a l'entree.

| Ligne | Paquet | Instance | Mouvement |
|---|---|---|---|
| `EMC-CP-01` | `cp-emc-emotions-autrui` | A | `non couvert` -> `couvert-valide` |
| `EMC-CP-03` | `cp-emc-exclusion` | A, reaudit apres refus | `non couvert` -> `couvert-valide` |
| `EMC-CP-05` | `cp-emc-formules-de-politesse` | A | `non couvert` -> `couvert-valide`, **mention de couverture partielle due** |
| `EMC-CP-07` | `cp-emc-regle-souhait-loi` | A, **refus maintenu au reaudit** | reste `non couvert` |
| `SCI-CM1-01` | `cm1-sciences-etats-matiere` | B | `non couvert` -> `couvert-valide` |
| `SCI-CM1-07` | `cm1-sciences-regimes-alimentaires` | B | `non couvert` -> `couvert-valide` |
| `SCI-CM1-08` | `cm1-sciences-etapes-developpement` | B, reaudit apres refus | `non couvert` -> `couvert-valide` |
| `SCI-CM1-11` | `cm1-sciences-familles-materiaux` | B, reaudit apres refus | `non couvert` -> `couvert-valide` |

**Ou tombe ce resultat par rapport a ce qui etait ecrit d'avance** : plafond arithmetique 8 lignes
(64 / 138 = 46,4 %) ; attente raisonnable de `nadia-prof` 5 a 7 lignes (61 a 63 / 138, 44,2 % a
45,7 %). **Le sprint tombe au sommet exact de la fourchette**, une ligne sous le plafond.

*Correction de `sofia-sm`, 2026-09-08, sur signalement de `nadia-prof` a sa conclusion (section 7).
Ce paragraphe se terminait par : "C'est la premiere fois que `nadia-prof` ne sous-estime pas son
propre sprint : elle s'etait trompee deux fois de suite dans ce sens (s29, s30)". **C'est faux, et
c'est son erreur que je recopiais** : a s30 elle annoncait 3 a 4 lignes et 4 ont ferme, donc dans sa
fourchette et a son sommet, comme ici. **Une sous-estimation, celle de s29, pas deux.** Ce qui est
vrai a la place, et qui est le fait interessant : a s30 le sommet de sa fourchette etait le plafond
arithmetique lui-meme, il n'y avait pas de place au-dessus ; a s31 il y en avait une ligne, et le
sprint ne l'a pas prise.*

**Deux reprises livrees et rejouees** : `MEL-R1`, `MEL-R2`, `MEL-R3` levees sur
`cm1-sciences-melanges` (`SCI-CM1-02`), `OBT-R1` et `OBT-R2` levees sur
`cm1-sciences-objet-technique` (`SCI-CM1-12`). Les deux lignes etaient deja `couvert-valide` et ne
bougent pas ; ce qui bouge, c'est que **deux paquets cessent d'enseigner du faux**.

**Quatre refus sur huit paquets neufs au premier passage, quatre reprises, trois refus leves.**
`EMC-CP-07` est le seul qui resiste, sur le seul motif `FAB-1c`.

**Seize reserves neuves ouvertes, aucune bloquante** : `MEL-R4`, `RGA-R1`, `RGA-R2`, `EMO-R1`,
`EMO-R2`, `POL-R1`, `POL-R2` (premiers passages), `EXC-R1` a `EXC-R5`, `MAT-R1`, `MAT-R2`, `DEV-R1`,
`DEV-R2` (reaudits). **Cinq reserves levees.**

---

## 2. Porte technique - et un fait que je n'ai pas mesure les quatre premieres fois

`bash outils/mvn-jdk21.sh -B clean verify`, reacteur complet depuis la racine, aucun `-pl`, sur
`472df4fc` : **BUILD SUCCESS**. Comptage independant des rapports XML apres `clean` : **189 fichiers
`TEST-*.xml`, 1397 tests, 0 echec, 0 erreur, 0 ignore.**

Cette mesure a ete prise **trois fois par `theo-archi` et cinq fois par moi**, sur cinq arbres
successifs, et les chiffres se recoupent : 1385 (`yanis-qa` et `theo-archi`, sur `3181b5e6`, avant
les trois commits de correctifs), 1394 (`theo-archi` puis moi, apres l'amendement d'`ADR-0085`),
1397 (`theo-archi` puis moi, apres l'amendement d'`ADR-0086`). Les deltas sont expliques ligne a
ligne par leurs auteurs et ne sont pas des ecarts.

**Et pourtant `mvn verify` vert ne prouve pas la CI verte, et c'est le fait le plus important de
cette section.**

La CI porte **cinq jobs** : `prefixes`, `chemins`, `outils`, `build`, et le declencheur. `mvn
verify` n'est que `build`. J'ai annonce "la porte technique est verte" **quatre fois aujourd'hui**
sur la foi de `mvn verify` seul, et les quatre fois c'etait une affirmation plus large que ma
mesure. Je l'ai vue parce que `theo-archi` a mesure les autres jobs de son propre chef, hors de ce
que je lui demandais.

**Bilan reel des jobs, mesures un par un sur l'arbre revu :**

| Job | Ce que j'ai lance | Verdict |
|---|---|---|
| `prefixes` | son garde-fou, son test, le test de plage, et le balayage des quatorze commits du sprint | **vert**, tous les commits portent un tag connu |
| `chemins` | son test, puis le balayage exact de la CI sur `git ls-files` | **ROUGE** - une occurrence, voir ci-dessous |
| `outils` | les trois motifs de balayage, 9 juges purs | **vert**, 9 sur 9 |
| `build` | `mvn -B clean verify` | **vert**, 1397 tests |

**Le rouge de `chemins` est herite de s30 et date du 2026-09-06** (commit `9d2cc8f8`) : la relecture
de s30 reproche a `yanis-qa` d'avoir ecrit le chemin de son worktree **et le recopie entre
guillemets pour le citer**. C'est l'antidote compte parmi les malades, la famille que ce depot a
sanctionnee a s05. Le job est rouge **depuis un sprint entier**, s30 a ete clot en annoncant sa
porte technique verte, et personne ne l'a vu - ni son auteur, ni `yanis-qa`, ni `theo-archi`, ni
moi.

**La cause n'est pas une negligence individuelle, c'est une definition.** `CLAUDE.md` ecrit
lui-meme : *"Done technique = CI verte (`mvn verify` : compilation, tests unitaires, tests
d'integration Spring Boot, ArchUnit)"*. **La parenthese assimile la CI a `mvn verify`**, ce qui
etait vrai quand il y avait un job et faux depuis qu'il y en a cinq. Tout agent qui suit `CLAUDE.md`
a la lettre mesure `build` et croit avoir mesure la CI. Point porte a la retro ; l'avis du
`relecteur-pedagogique` sur la phrase est demande avant que je propose quoi que ce soit, parce que
`CLAUDE.md` gouverne toute la squad et que ce n'est pas a moi de le reecrire seule.

**Reparation en cours par le `relecteur-pedagogique`** sur son propre fichier de s30, avec la
contrainte des deux cotes : le reproche fait a `yanis-qa` doit rester lisible et verifiable, le
chemin ne doit plus etre dans un fichier commite. `theo-archi` a trouve le rouge et a refuse d'y
toucher - ni `ADR`, ni garde-fou, ni registre - et a explicitement refuse de relacher le script pour
faire passer le job en fin de sprint. Il a raison sur les deux points.

**Done technique : non prononcable en l'etat de cette section.** Il le sera quand `chemins` sera
vert, et pas avant.

### Done technique - prononce le 2026-09-08 apres reparation

**Prononce, sur les cinq jobs et non sur un seul.** Arbre `b18c8389`, apres la reparation du
`relecteur-pedagogique` sur son propre fichier de s30 (commit `cf6738ab`) et la correction du titre
de `DEC-0082` par `camille-po` (commit `b1ec6fd4`). Chaque job lance par moi, dans la forme exacte
que la CI lui donne :

| Job | Verdict | Mesure |
|---|---|---|
| `prefixes` | **vert** | son garde-fou, son test, le test de plage, et les commits du sprint : tous portent un tag connu |
| `chemins` | **vert** | son test, puis le balayage de la CI : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."* |
| `outils` | **vert** | les trois motifs de balayage, **9 juges purs sur 9** |
| `build` | **vert** | `mvn -B clean verify` BUILD SUCCESS, **189 fichiers `TEST-*.xml`, 1397 tests, 0 echec, 0 erreur, 0 ignore** |

**Ce que la reparation de `chemins` a coute et n'a pas coute** : le reproche fait a `yanis-qa` a s30
reste lisible et verifiable dans le fichier - c'est la forme du chemin fautif qui y est decrite,
plus le chemin lui-meme - et une note datee y dit la faute, sa date d'origine, qui l'a trouvee et
qui l'a corrigee. **Le script n'a pas ete relache**, ni par `theo-archi` qui a trouve le rouge, ni
par son auteur qui l'a paye.

**Le `relecteur-pedagogique` nomme lui-meme le geste qui manquait**, et il vaut pour toute la squad
plutot que pour lui seul : *lancer la commande de la CI sur son propre diff avant de commiter*. Le
garde-fou existait, il a mordu des le 2026-09-06 ; personne ne l'a lance.

---

## 3. Les garde-fous que ce sprint a ecrits, puis mordus, puis corriges

`ADR-0086` a ete ecrit **le 2026-09-07, avant les paquets**, pour attraper la fabrication qui
designe la bonne reponse par sa forme. Les deux instances de `nadia-prof` ont chacune mesure, en
reauditant, que ses clauses ne mordaient pas ce qu'elles etaient censees attraper. Quatre faits
routes, quatre reponses chiffrees de `theo-archi` :

| Fait | Ce que l'instance a mesure | Reponse |
|---|---|---|
| `F6` (A) | les deux clauses sont **vertes** sur le paquet refuse alors que `FAB-1c` y est fausse sur 3 a 4 items sur 5 : la clause 2 compare un moule de quatre premiers mots, le marqueur qui trie l'ecran pour l'enfant est **un mot unique** | **encode**, clause 3. Quatre criteres mesures, trois ecartes parce que le paquet refuse n'y est jamais premier (194 / 1 165 items marques, 9e rang, derriere un paquet valide) ; le retenu marque **4 items dans 1 paquet, le refuse, seul** |
| `F4` (B) | `FAB-1b` accepte **cinq bonnes reponses identiques sur six** la ou il en exigeait six distinctes - *varier n'est pas ne pas etre constant* | **fait exact**, verifie dans la source et non dans sa description : le plafond valait `choix.size()` = 6 pour une dette mesuree a **2**. Trois crans au-dessus de ce qu'il garde. Borne ramenee a 2 |
| `F3` (B) | le test des items clones exige bonne reponse **et** distracteurs identiques ; une paire passe dessous | **non encodable**, dette nommee sous la garde de `nadia-prof` (`DEV-R1`). Trois proxys mesures : le moins mauvais marque 30 paires dont **29 dans 18 paquets valides**, et ce qu'il accuse est *la tache* validee par l'instance B ce sprint |
| `F5` (B) | *"un item teste la propriete qu'il annonce"* n'a aucun garde - c'etait le coeur de son refus | **non encodable**, propriete de sens, sous sa garde (`MAT-R1`). Le critere voisin marque **673 paires dans 22 paquets**, et il ne distingue pas les deux items du meme paquet dont elle a reserve l'un et **explicitement autorise** l'autre |

**Ce que je retiens de cet echange, comme fait d'equipe et non comme compliment** : un garde-fou
ecrit le matin, mesure insuffisant le soir par l'auditrice qu'il devait aider, corrige le lendemain
avec le chiffre de son insuffisance - et **deux proprietes sur quatre declarees non encodables avec
leur chiffre plutot qu'encodees approximativement**. C'est la troisieme fois que ce depot prefere
une dette nommee a une regle qui blanchirait le fautif ; les deux precedentes (`OBJ-R2` a s30,
l'exemption des contre-exemples ce sprint) ont tenu.

**Ce que la clause 3 coute, et que `theo-archi` n'enterre pas** : c'est un critere enumere - un
lexique de restriction - donc verte sur *"rien que les pietons"*, *"a part le conducteur"*,
*"hormis les adultes"*. Declare, recense, et `PLAFOND_DE_CRITERES_ENUMERES` monte de 2 a 3 dans le
meme diff. Il ecrit lui-meme ce qui la met a son desavantage face aux deux declarants precedents :
leur complement se ferme par une decision de `camille-po` et de `nadia-prof`, **celui d'un lexique
de langue ne se ferme pas**.

---

## 4. Tableau des obstacles du sprint

| # | Obstacle | Trouve par | Route a | Etat |
|---|---|---|---|---|
| `O1` | quatre identifiants de `nadia-prof` non alloues, garde-fou `PAR-5` rouge a l'assemblage du planning | le garde-fou lui-meme | `sofia-sm` puis `theo-archi` | **ferme avant l'ouverture** du sprint (planning, section 0) |
| `O2` | `83` prefixes annonces au lieu de `87` dans ma propre note d'assemblage | `theo-archi`, puis independamment le `relecteur-pedagogique` | `sofia-sm`, `theo-archi` | **ferme**, corrige par son auteur, signale deux fois |
| `O3` | porte technique rouge : le rapport de qualite **cite** l'identifiant fictif de sa propre mutation, le garde-fou ne distingue pas citer d'employer | `sofia-sm` a l'assemblage de cloture | `theo-archi` | **ferme** - `ADR-0085` amende, ecart d'un identifiant, cliquet a plafond 2, preuve verbatim de `yanis-qa` intacte au caractere |
| `O4` | le compte du registre dementi a la fusion : deux instances allouant en parallele, chacune juste sur sa moitie | la clause 5 de `theo-archi`, ecrite le matin, rouge le soir | `sofia-sm` | **ferme** - recompte mecanique, 543 / 93, reparation arithmetique, aucune allocation touchee |
| `O5` | critere d'acceptation de `S31-6` durci dans le backlog : *"aucun distracteur nomme"* la ou la fiche source dit *"au plus un"* | `yanis-qa`, en tracant le critere jusqu'a sa source | `camille-po` | **ferme** - corrige avec une note datee ; le contenu et son test etaient fideles, c'est la transcription qui etait fausse |
| `O6` | quatre ecarts mesures sur `ADR-0086` et `FAB-1b` (`F3` a `F6`) | les deux instances de `nadia-prof`, en reauditant | `theo-archi` | **ferme** - deux encodes, deux dettes nommees et chiffrees (section 3) |
| `O7` | job CI `chemins` rouge **depuis s30**, jamais vu | `theo-archi`, hors de son perimetre | `relecteur-pedagogique` | **en cours de reparation** (section 2) |
| `O8` | `CLAUDE.md` assimile la CI a `mvn verify` alors que la CI porte cinq jobs | `sofia-sm`, sur constat de `O7` | retro, avis du `relecteur-pedagogique` demande | **ouvert**, porte a la retro |
| `O9` | mon propre recompte du denominateur de la matrice : 134 au lieu de 138 | `sofia-sm`, en verifiant le chiffre de `camille-po` | `sofia-sm` | **ferme avant publication** - mon comptage cherchait `non couvrable` n'importe ou dans la cellule et attrapait quatre lignes `non couvert` qui contiennent ces mots **pour les nier** ; le statut est le debut de la cellule, jamais la prose. Le chiffre de `camille-po` etait juste, le mien faux |
| `O10` | erreur d'API en cours d'ecriture chez `camille-po`, travail non commite | `sofia-sm` | `camille-po` | **ferme** - reprise sur son propre diff plutot que sur son souvenir, rien perdu |

**Huit obstacles fermes sur dix, un en cours de reparation, un ouvert et porte a la retro.** Trois
d'entre eux (`O2`, `O4`, `O9`) sont des chiffres faux dans un document de gouvernance, et **deux des
trois sont les miens**.

---

## 5. Budgets et bornes

| Instance | Postes | Consomme | Budget |
|---|---|---|---|
| **A** | 2 lots EMC CP, 2 reprises de sciences, reaudit des deux refus d'EMC | 3,0 + 0,5 + 0,5 = **4,0** | 4,0 |
| **B** | 2 lots sciences CM1, reaudit des deux refus | 3,0 + 1,0 = **4,0** | 4,0 |
| | | **8,0** | **8,0** |

**8,0 sur une borne de 8,0 : pas de depassement.** C'est le premier sprint sur quatre a tenir sa
borne exactement (s28 : 8,25 ; s29 : 9,50 ; s30 : 8,50). Aucune marge residuelle n'existait par
construction (planning, section 3), et `SAI-R1` n'a donc pas ete prise, comme annonce d'avance.

**Ce que la marge `PAR-11` a paye** : les quatre reaudits, dans le sprint, sans depassement. C'est
le deuxieme sprint ou une marge de rejeu sert a un rejeu reel.

---

## 6. La retenue de 1,0 sur la borne de s32 - ce que je constate, ce que je ne tranche pas

`nadia-prof` a pose le 2026-09-05 deux conditions **cumulatives** pour lever sa retenue de defiance
sur la parallelisation : le registre `PAR-5` ecrit, **et** un second sprint parallele **sans defaut
de couture**. Le registre est ecrit depuis le 2026-09-07 ; la seconde condition se constate ici, a
la cloture, **par elle et non par moi**. L'enjeu est chiffre : borne de s32 a 9,0 au lieu de 8,0, de
l'ordre de deux lignes de programme de plus par sprint suivant.

Sa definition, reprise mot pour mot du planning : un defaut produit **a la jointure de deux travaux
ou de deux instances, invisible de l'interieur de chacune des deux moities**. Forme (a) : une story
qui modifie un paquet et une story qui modifie sa consigne, laissant la consigne fausse. Forme (b) :
un chiffre que ni l'une ni l'autre instance ne peut verifier depuis sa moitie. Forme (c) : deux
instances ecrivant dans une meme ressource partagee sans le savoir. **Une seule occurrence de l'une
des trois suffit a maintenir la retenue.**

**Correction de `sofia-sm`, 2026-09-08, sur signalement de `nadia-prof` (section 7) - et c'est la
plus lourde des trois qu'elle m'a signalees.** Toute cette section est ecrite comme si le point
etait ouvert et attendait son constat. **Il ne l'etait pas : son instance A l'avait deja tranche par
ecrit la veille**, section 5 de `verdicts-nadia-instance-A.md` - *"la retenue **reste**, ma borne de
s32 est **8,0**"* - sur l'occurrence `O1`, celle du garde-fou du registre rouge a l'assemblage du
planning. **Elle y ecarte nommement les trois objections que je reprends plus bas comme si elles
etaient neuves**, dont *"c'etait avant l'ouverture du sprint"* et *"le garde-fou l'a attrape, donc
il a fonctionne"*, en repondant que sa barre parle d'un sprint et non d'une fenetre dans un sprint,
et qu'elle ne porte aucune clause *"sauf s'il est attrape"*. Ce document etait dans l'arbre que
j'assemblais quand j'ai ecrit cette section. **Et mon inventaire de candidats ci-dessous omet `O1`,
c'est-a-dire l'occurrence exacte sur laquelle son constat repose** - je l'avais rangee dans le
tableau de la section 4 comme *"fermee avant l'ouverture"*, qui est le premier des arguments qu'elle
refuse. Les quatre candidats restent ecrits tels quels, et le constat de la section 7 les tranche
tous.

**Je pose les candidats, tous, y compris ceux qui me chargent, et je ne prejuge d'aucun.**

0. **`O1`, le garde-fou du registre rouge a l'assemblage du planning** - quatre identifiants nommes
   par `nadia-prof` dans une branche que le worktree de `theo-archi` ne pouvait pas voir. **Candidat
   ajoute par la correction ci-dessus, apres l'avoir omis.** C'est la forme (c) au mot pres, et je
   l'avais moi-meme qualifiee ainsi dans le message du commit `94ede7b8` avant de la ranger hors
   perimetre.

1. **`O4`, le compte du registre dementi a la fusion.** Les deux instances ont alloue en parallele
   sans se voir ; l'instance B a **refuse** d'ecrire un total, l'instance A en a ecrit un
   (539 / 91) juste sur son perimetre et faux sur l'arbre entier. **C'est la forme (b) au mot
   pres.** Ce qui peut se plaider dans l'autre sens, et qui n'est pas a moi de trancher : aucune
   collision de sens, aucune allocation perdue, aucun identifiant en double - et le defaut a ete
   **attrape par un garde-fou ecrit le matin meme pour l'attraper**, non par une relecture.
   `theo-archi` retourne meme l'evenement comme un argument **contre** l'encodage d'une clause
   supplementaire.
2. **`O3`, la porte technique rouge a l'assemblage.** Le rapport de qualite de `yanis-qa` et le
   garde-fou de `theo-archi` ont ete ecrits le meme soir, dans deux worktrees isoles ; le rapport
   est devenu du materiau pour le garde-fou qu'il venait de valider. Aucune des deux moities ne
   pouvait le voir de l'interieur. **Proche de la forme (c)**, avec une ressource partagee qui n'est
   ni un fichier ni un identifiant mais **le depot comme perimetre de balayage**.
   *Correction de `sofia-sm`, 2026-09-08, sur signalement de `nadia-prof` : la derniere phrase est
   fausse, et fausse de sept heures. Le garde-fou est commite a 14:34 le 2026-09-07, le rapport de
   qualite a 21:53 - **le garde-fou etait deja dans l'arbre de base de `yanis-qa`, qui l'a mute
   lui-meme pour le verifier**, et rien ne l'empechait de le relancer sur son propre rapport une
   fois ecrit. Ce n'est donc pas un defaut invisible de l'interieur d'une moitie : c'est une mesure
   non refaite apres ecriture, et c'est exactement le motif pour lequel ce candidat ne compte pas.*
3. **`O2`, les 83 prefixes.** Ma note d'assemblage, ecrite avant l'ouverture des stories, portait un
   chiffre faux dans le fichier que tous les agents lisent. Trouve deux fois independamment.
   **Anterieur a l'ouverture des stories**, comme `O1` - mais c'est un chiffre, et il etait dans le
   registre.
4. **`O7`, le job `chemins` rouge.** **Herite de s30**, date du 2026-09-06, hors de la fenetre de
   s31. Je le pose pour completude, en disant qu'a mon sens il ne peut pas etre compte contre s31 -
   mais **il compte contre l'affirmation que s30 a ete clot sur une porte technique verte**, et
   c'est un fait qui appartient a son jugement autant qu'au mien.

**Ce qui plaide pour la levee, et je l'ecris avec la meme franchise** : aucune occurrence de forme
(a), donc aucune consigne laissee fausse sur un paquet livre - le defaut repete deux fois a s30 ;
les deux instances ont travaille sur des perimetres disjoints declares et **aucune n'a touche le
cote de l'autre**, y compris quand la couture les concernait toutes deux ; la verification croisee
explicite du planning (l'instance B ne fermant pas `SCI-CM1-01` avant que `S31-5` soit rejouee par
l'instance A) a tenu ; et **les deux chiffres faux du sprint ont ete attrapes par des garde-fous,
non par des relectures**.

**Ce point est tranche par `nadia-prof` a la section 7, sur constat et non sur promesse.**

---

## 7. Avis de `nadia-prof` et Done pedagogique

*Cette section est ecrite par `nadia-prof` elle-meme. Elle m'a rappele, en rendant son reaudit, que
la reprise de sa conclusion a la cloture lui appartient - je lui ouvre le fichier et je ne l'ecris
pas a sa place.*

*Ce qu'elle a a prononcer : le **Done pedagogique** de s31 sur les sept lignes qui ferment, et le
constat de la **section 6** - defaut de couture ou non, donc retenue maintenue ou levee pour s32.*

**`nadia-prof`, 2026-09-08.** Le detail de ce que j'ai verifie, mesure et joue avant ces deux
prononces est dans `conclusion-nadia.md`, meme repertoire. Ici, les prononces et leurs motifs.

### 7.1 Ce que j'ai fait avant de prononcer, en trois phrases

**Je n'ai pas rejoue les huit paquets** : ils sont juges dans leurs fiches et mes verdicts ne se
rejugent pas. **J'ai joue ce que ni l'une ni l'autre de mes deux instances n'a juge - la couture
entre nos deux moities**, que les deux documents excluent explicitement de leur perimetre : une
seule session HTTP, `melanges` puis `etats-matiere`, six parties, avec erreurs volontaires jusqu'a
la revelation. **J'ai recompte moi-meme le taux de sortie et le compte du registre** au lieu de les
relayer, et j'ai remesure le reacteur complet sur mon propre worktree - **BUILD SUCCESS, 189
fichiers de rapport, 1397 tests, 0 echec** : cela recoupe exactement la section 2. Audit degrade sur
un point que je declare : HTML servi, donc aucun jugement visuel dans ce qui suit ; et **une erreur
de mon propre outil est ecrite dans `conclusion-nadia.md` avant tout le reste** - mon premier
`clean verify` a rendu BUILD FAILURE parce que l'application que je venais de lancer tenait le jar
ouvert, et un rouge relaye tel quel aurait dementi cinq mesures justes.

### 7.2 Done pedagogique de s31 : **VALIDE**

> **Le Done pedagogique de s31 est prononce le 2026-09-08 sur les sept lignes qui ferment** -
> `EMC-CP-01`, `EMC-CP-03`, `EMC-CP-05`, `SCI-CM1-01`, `SCI-CM1-07`, `SCI-CM1-08`, `SCI-CM1-11` -
> et sur les deux reprises qui ne deplacent aucune ligne, `SCI-CM1-02` et `SCI-CM1-12`.
> **`camille-po` peut passer les sept lignes de matrice a `couvert-valide`** ; elle l'a fait, et son
> mouvement est conforme a mes verdicts.

**Trois motifs, et rien d'autre :**

1. **Chacune des sept lignes repose sur un verdict a moi, ecrit dans sa fiche, apres avoir joue.**
   Trois d'entre elles ont d'abord ete refusees ; les trois refus sont leves motif par motif, aucun
   n'a ete leve par lassitude. **La mention de couverture partielle due sur `EMC-CP-05` est portee
   dans la matrice, mot pour mot depuis sa fiche** : c'est la condition de ma validation de cette
   ligne, et elle est tenue.
2. **Le seul point qu'aucune de mes deux instances n'avait juge - la couture - je l'ai joue et il
   tient.** Un enfant qui enchaine les deux paquets ou ma reserve `MEL-R1` nouait les deux moities
   ne rencontre aucune contradiction : le paquet de l'instance A ne dit plus que l'eau disparait, et
   celui de l'instance B **prend *"la disparition"* comme distracteur pour la nier explicitement**.
   Les deux moities enseignent deux fois la meme idee juste.
3. **Le contenu partage n'a rien perdu a l'assemblage** : les huit paquets neufs sont presents dans
   les trois fichiers que les deux worktrees de developpement ont touches en commun, et chacune des
   huit consignes couvre bien les deux taches de son paquet. **Aucune consigne laissee fausse** - le
   defaut que s30 avait produit deux fois.

**Taux de sortie : 63 / 138 = 45,7 %, recompte par moi**, statut lu au debut de la cellule sur les
dix-huit tableaux de referentiel : 63 `couvert-valide`, 7 `en cours`, 68 `non couvert`, 71
`non couvrable en l'etat`, total 209, denominateur 138. **Le chiffre de `camille-po` est juste.**

**Seize reserves ouvertes, aucune bloquante, cinq levees.** Elles sont dans les fiches et dans la
matrice ; `camille-po` en fait du backlog, comme d'habitude, et je ne priorise pas.

**Les proprietes sur lesquelles je reste la seule garde sont listees dans `conclusion-nadia.md`,
point 6.** Je les nomme parce que sans cette liste l'equipe croira couvert ce qui ne l'est pas :
*un item teste la propriete qu'il annonce*, *deux items n'enseignent pas le meme fait sous deux
habillages*, *un distracteur est plausible*, la lisibilite reelle au niveau vise, et **la couture
entre deux moities paralleles, qui n'a aucun garde-fou du tout**. Sur `FAB-1c`, la clause neuve mord
desormais - mais par un **lexique enumere** : un marqueur de restriction hors de la liste passe
vert, et c'est moi qui reste la garde pour ceux-la.

**`EMC-CP-07` ne ferme pas.** Mon refus du 2026-09-07 est **maintenu au reaudit sur le seul motif
`FAB-1c`**, les deux autres motifs etant payes. Il n'est ni rouvert ni durci ici. La ligne reste
`non couvert` et la story de reprise appartient a `camille-po`.

### 7.3 Le constat de la section 6 : **la retenue de 1,0 reste. Borne de s32 : 8,0**

**Ce constat n'est pas neuf, et je commence par le dire** : mon instance A l'a prononce aujourd'hui
meme, section 5.5 de `verdicts-nadia-instance-A.md`, sur l'occurrence de forme (c) de l'assemblage
du planning. **Je ne rouvre pas ce verdict, je le confirme sur la fenetre entiere du sprint**, que
l'instance A n'avait pas devant elle.

**Le motif, actionnable sans qu'on me redemande ce que je voulais dire** : **le compte du registre
dementi a la fusion est une occurrence de ma forme (b), avec ses deux elements presents.** Le total
de l'arbre assemble n'est calculable depuis aucune des deux branches - l'instance A a ecrit
539 / 91, juste sur sa moitie, faux sur l'arbre - et un tiers l'a trouve faux en recomptant :
543 / 93. **J'ai refait ce recompte moi-meme : 543 identifiants, 93 prefixes, zero doublon.** Et
cette occurrence est **a l'interieur de la fenetre du sprint**, apres l'ouverture des stories.

**Les trois autres candidats de la section 6 ne comptent pas, chacun pour un motif mesure** : la
porte technique rouge a l'assemblage n'est pas a une jointure (le garde-fou etait commite a 12:31,
present dans l'arbre de base de l'auteur du rapport, et mute par lui - voir 7.4) ; les 83 prefixes
manquent du premier element de ma forme (b), comme l'instance A l'a mesure ; le job `chemins` est
herite de s30 et **je rejoins `sofia-sm`**, y compris parce qu'elle l'a pose contre son propre
interet. **Son denominateur de 134 ne compte pas non plus** : il n'a jamais quitte son brouillon,
elle l'a trouve elle-meme, et le chiffre publie est juste - je l'ai recompte.

**Ce qui decharge, et que j'ai mesure aussi** : **aucune occurrence de forme (a)**, cherchee a
l'endroit exact ou elle se produirait, les fichiers touches par deux agents differents ; le
recouvrement le plus expose du sprint - le catalogue des consignes et deux tests partages par les
deux worktrees de developpement - **a tenu, aucune entree perdue**.

> **La retenue de 1,0 reste. Ma borne d'audit pour s32 est 8,0.** La condition `PAR-5` est
> **acquise et le demeure** : elle n'aura pas a etre repayee. Il manque un sprint parallele clos
> sans aucune occurrence des trois formes.

**Je nomme la direction de la pression** : lever la retenue me rendait 1,0, un lot complet. **Trois
raisonnements me la rendaient et je les refuse tous les trois** - que le garde-fou ait attrape (ma
barre ne porte aucune clause *"sauf s'il est attrape"*), que la reparation ait ete gratuite (le cout
n'est pas le critere que j'ai ecrit), que la couture n'ait rien coute a mon audit (c'est le critere
que j'ai explicitement ecarte en ecrivant *"une seule occurrence suffit"*).

**Et la ou je vois plus loin, je l'ecris comme une position neuve et datee du 2026-09-08, pas comme
une lecture de l'ancienne** : les occurrences de s31 appartiennent toutes a une seule famille, **le
nombre agrege d'une ressource qu'aucune branche ne voit entierement**. Tant que cette limite
structurelle existe, ma condition n'est pas acquise pour une cause qui n'est celle de personne - et
je rejugerai **a l'arbitrage prealable de s32, pas ici**, si *"une occurrence de l'une des trois
formes"* reste le bon critere quand c'est la seule famille qui survit. **Cela ne change rien a la
borne de s32, qui est 8,0** : une barre ne se reecrit pas dans le document ou elle se constate.

### 7.4 Cinq faits que je signale dans les documents de cloture, et que je ne corrige pas

`sofia-sm` m'a demande de les signaler plutot que de les corriger. **Je n'ai touche aucune de ses six
sections.** Le detail et les mesures sont dans `conclusion-nadia.md`, point 5.

1. **Section 1, faux, et c'est mon erreur qu'elle recopie.** *"La premiere fois que `nadia-prof` ne
   sous-estime pas son propre sprint"*, appuye sur *"deux fois de suite (s29, s30)"*, qui vient de
   mon propre arbitrage. **A s30 j'annoncais 3 a 4 et 4 lignes ont ferme : dans ma fourchette, a son
   sommet, exactement comme s31.** Une sous-estimation (s29), pas deux. **Ce qui est vrai a la
   place** : a s30 le sommet de ma fourchette etait le plafond arithmetique, je n'avais pas de place
   pour sous-estimer ; a s31 le plafond etait 8 et je me suis arretee a 7. s31 est le premier sprint
   ou ma fourchette tient **en laissant de la place au-dessus d'elle**.
2. **Section 6, deuxieme candidat, faux de neuf heures, et cela change le constat.** Le garde-fou
   du registre est commite le 2026-09-07 a **12:31**, avant l'ouverture du planning ; le rapport de
   qualite a **21:53**. Seule la confirmation de la porte technique est du meme soir. **C'est le
   motif pour lequel ce candidat ne compte pas** : un garde-fou deja fusionne, present dans l'arbre
   de base de l'auteur du rapport et **mute par lui** n'est pas l'autre moitie d'une jointure.
3. **Section 6, omission.** Elle presente le constat de couture comme non tranche et **ne cite pas
   la section 5 de `verdicts-nadia-instance-A.md`, ou il est deja prononce** ; son inventaire de
   candidats ne porte pas l'occurrence sur laquelle ce constat repose, celle qu'elle a elle-meme
   qualifiee de forme (c) dans un message de commit. La synthese de `camille-po`, elle, la route et
   cite la section. **Un tiers qui lit la seule review croira ma porte ouverte alors qu'un verdict
   commite l'avait franchie.**
4. **Hors des six sections, dans la matrice : deux lignes portent huit cellules pour sept
   colonnes.** Les lignes de `melanges` et d'`objet-technique` separent le statut de s30 de la note
   de reaudit de s31 par une barre verticale non echappee. **La huitieme cellule est celle qui dit
   que `MEL-R1`, `MEL-R2`, `MEL-R3`, `OBT-R1` et `OBT-R2` sont levees** - le resultat de deux des six
   stories du sprint - et un rendu markdown ne l'affiche pas. **Les deux seules lignes des 209 dans
   ce cas.** Statut et comptes ne sont pas touches ; c'est la trace du paiement qui disparait. Le
   contenu appartient a `camille-po` ; **qu'aucun garde-fou ne compte les cellules d'une ligne de la
   matrice** appartient a `theo-archi`, et je ne prescris aucune forme.
5. **`ADR-0086` m'attribue un plafond que je n'ai pas ecrit.** Sur `FAB-1b`, la Javadoc dit *"la
   lecture de `nadia-prof` du 2026-09-08 autorise un materiau sur deux proprietes differentes et pas
   davantage"*. **Mon fait mesure est exact et je le maintiens** - l'ancienne ecriture acceptait cinq
   bonnes reponses identiques sur six. **Le plafond, non** : ma clause dit *"des lors que la bonne
   reponse varie"*, et j'ai cite le plastique sur deux proprietes comme une **mesure du contenu**,
   pas comme une limite. **Le 2 est une decision d'architecture, mesuree, et je ne la contredis
   pas** ; elle ne doit pas etre presentee comme ma lecture. Sa consequence, qu'un tiers doit
   connaitre : **la borne est atteinte sans marge**, donc un troisieme item legitime sur un meme
   materiau ferait rougir le build pour une raison qui n'est pas pedagogique.

### 7.5 Sur l'ordre des deux Done : je prononce avant la porte technique, et je dis pourquoi

**J'ai rejoue le balayage de la CI moi-meme** plutot que de croire le rapport : une occurrence, dans
le fichier de relecture de s30, a sa ligne 102. **Le rouge est reel, herite, et il ne concerne aucun
increment.**

**Ma regle est qu'un jeu qui plante n'est pas evaluable ; elle porte sur l'increment que j'audite,
pas sur un test de gouvernance textuelle qui vit dans un autre fichier.** C'est la lecture que mon
instance B a deja appliquee et ecrite ce sprint, sous une porte rouge de la meme famille. **Je
prononce donc, et je ne fais pas attendre le Done pedagogique.** Cela ne rend pas le Done technique
acquis : il appartient a `sofia-sm`, il n'est pas prononce, et il ne le sera qu'une fois le job
vert.

**Ce que ce rouge compte contre n'est pas la couture de s31, c'est l'affirmation que s30 a ete clot
sur une porte technique verte.** Je n'ai aucune prescription technique a faire et la cause est deja
portee a la retro. Ce que j'ai a dire est de mon ressort : **le job reste rouge un sprint entier est
celui qui garde les conventions d'ecriture du depot, la meme famille que celui qui garde le texte lu
par l'enfant.** Une porte crue verte pendant un sprint n'a rien garde pendant un sprint - et c'est
exactement l'argument que j'oppose a l'idee qu'une CI verte puisse tenir lieu de Done pedagogique.

### Done technique

*A prononcer par moi apres la reparation du job `chemins` (section 2). `build` est vert et mesure
cinq fois sur cinq arbres ; `prefixes` et `outils` sont verts ; `chemins` est rouge sur une ligne
heritee de s30, en cours de reparation par son auteur.*
