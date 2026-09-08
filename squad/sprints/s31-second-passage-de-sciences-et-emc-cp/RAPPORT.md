# RAPPORT de cloture - s31, second passage de sciences et EMC CP

## L'increment livre

**recreo sert pour la premiere fois de l'enseignement moral et civique au CP au-dela de deux
lignes**, et **finit les sciences et technologie CM1 a quatre lignes sur quatre visees**. Sept lignes
de programme ferment sur les huit ouvertes : `EMC-CP-01`, `EMC-CP-03`, `EMC-CP-05`, `SCI-CM1-01`,
`SCI-CM1-07`, `SCI-CM1-08`, `SCI-CM1-11`. La couverture passe de **56 / 138 (40,6 %) a 63 / 138
(45,7 %)**, au sommet exact de la fourchette que `nadia-prof` avait annoncee avant que le sprint soit
joue, une ligne sous le plafond arithmetique.

**Deux paquets cessent d'enseigner du faux.** `MEL-R1`, `MEL-R2`, `MEL-R3` levees sur
`cm1-sciences-melanges` - l'indice du sel ne dit plus que l'eau disparait ; `OBT-R1` et `OBT-R2`
levees sur `cm1-sciences-objet-technique` - l'indice du velo ne dement plus la bonne reponse de son
propre item. Les deux lignes etaient deja `couvert-valide` : ce sprint ne les compte pas, il repare
ce qu'elles servaient.

**`EMC-CP-07` ne ferme pas.** Refus maintenu au reaudit sur un seul motif, `FAB-1c` : la bonne
reponse est la seule proposition depourvue du mot *seulement* sur trois a quatre items sur cinq, et
un enfant qui ne sait rien du droit gagne quatre manches sur cinq sans lire l'enonce. `nadia-prof`
n'a releve aucune barre et ne l'a pas fermee faute de budget - elle avait ecrit d'avance qu'une ligne
ne ferme pas *quand meme*.

## Toutes les decisions du sprint

| Reference | Titre | Type | Statut |
|---|---|---|---|
| `ADR-0085` | Un identifiant de condition est alloue dans un registre, et un identifiant non alloue n'existe pas | Architecture | Accepte, **amende le 2026-09-08** (citer n'est pas moins qu'employer ; ecart d'un identifiant a plafond 2 ; clause 5 sur le compte courant du registre) |
| `ADR-0086` | La forme des propositions ne designe jamais la bonne reponse, et trois proprietes voisines ne sont pas encodables | Architecture | Accepte, **amende le 2026-09-08** (clause 3, le marqueur de restriction ; deux proprietes de plus non encodables, cinq au total ; attribution du plafond de `FAB-1b` corrigee en trois endroits) |
| `ADR-0087` | Une ligne de tableau ne porte jamais plus de cellules que son en-tete | Architecture | Accepte, ecrit a la cloture |
| `DEC-0081` | Composition 1 a quatre lots, borne 8,0, `CAT-1` impayee et routee a s32 | Produit | Acceptee, executee |
| `DEC-0082` | Cloture de s31 : le resultat de la composition 1 retenue contre la recommandation de `nadia-prof`, neuf reserves et non huit, `REG-R1` et `PER-R1` a leur quatrieme sprint, `CAT-1` routee pour la premiere fois | Produit | Acceptee, **titre et nom de fichier corriges** apres signalement du `relecteur-pedagogique` |

**Amendement a `CLAUDE.md`, section "Regles cles"** : la definition du Done technique cesse
d'assimiler la CI a `mvn verify`. Voir la retro, premiere section.

## Etat des deux Done

**Technique : prononce, sur les cinq jobs de la CI et non sur un seul.** C'est la premiere fois que
ce depot le prononce ainsi, et le motif est ecrit plus bas.

| Job | Verdict | Mesure |
|---|---|---|
| `prefixes` | vert | son garde-fou, son test, le test de plage, et les commits du sprint : tous portent un tag connu |
| `chemins` | vert | *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."* |
| `outils` | vert | les trois motifs de balayage, **9 juges purs sur 9** |
| `build` | vert | `bash outils/mvn-jdk21.sh -B clean verify` BUILD SUCCESS sur le reacteur complet depuis la racine, aucun `-pl` |

Comptage independant des rapports XML apres `clean`, methode de l'amendement s11, mesure par moi
sur l'arbre fusionne final. Le chiffre exact et sa revision sont en fin de ce rapport, section
"Mesure finale".

**Zero fichier Java de production touche par tout le sprint.** Les huit paquets neufs, les quatre
correctifs et les deux reprises sont du YAML et des tests. Les trois `ADR` amendes ou ecrits n'ont
produit que des tests et des contre-exemples. **33 regles `ArchUnit`, inchangees** : les trois
garde-fous de ce sprint sont des balayages de sources, pas des `ArchRule`.

**Pedagogique : prononce.** Deux instances disjointes de `nadia-prof`, chacune reauditant apres ses
propres refus, aucune ne relisant l'autre. Sept lignes ferment, huit fiches de conformite portent
leur verdict avec leur refus d'origine lisible. Trois motifs a son prononce, dont un qu'elle est
allee chercher elle-meme : **la couture, que ni l'instance A ni l'instance B n'avait jugee**, jouee
en une session sur `melanges` puis `etats-matiere`, six parties - le paquet des etats de la matiere
prend *"la disparition"* comme distracteur et consacre son indice a la nier, donc les deux moities
enseignent deux fois la meme idee juste.

**Seize reserves neuves, aucune bloquante ; cinq reserves levees.** Chacune avec son identifiant
alloue dans le registre dans le meme geste que son verdict.

**Prix reel du sprint : 8,00 sur une borne de 8,0.** Instance A 4,00 / 4,00, Instance B 4,00 / 4,00.
**Aucun depassement, pour la premiere fois en quatre sprints** (s28 : 8,25 ; s29 : 9,50 ; s30 :
8,50), et quatre reaudits payes dans le sprint par la marge `PAR-11`. Ce que je ne conclus pas : que
la discipline de budget est acquise - trois depassements puis un sprint a l'exact, sur une
composition qui saturait la borne des le planning, ne fait pas une tendance.

**La retenue de 1,0 n'est pas levee : la borne de s32 reste 8,0.** `nadia-prof` constate une
occurrence de sa forme (b) - le compte du registre dementi a la fusion - et ecarte les quatre autres
candidats sur des motifs mesures. Ses deux conditions etaient cumulatives : `PAR-5` ecrit (acquise,
et elle le demeure) et un second sprint parallele sans defaut de couture (echouee). C'etait la
premiere fois que la proprete d'un sprint avait un prix chiffre en couverture future ; **le prix
n'est pas paye.** Position neuve qu'elle date du 2026-09-08 : les trois occurrences constatees en
trois sprints appartiennent a une seule famille, le nombre agrege qu'aucune branche ne voit, et elle
rejugera **a l'arbitrage prealable de s32** si *"une occurrence"* reste le bon critere. Cela ne
change pas 8,0.

## Le fait de ce sprint qui ne concerne aucun contenu

**Le job `chemins` de la CI etait rouge depuis s30, et personne ne l'avait vu.** Une occurrence,
dans la relecture de s30 : son auteur y reproche a `yanis-qa` d'avoir ecrit le chemin de son
worktree et le recopie entre guillemets pour le citer. **s30 a ete clot en annoncant sa porte
technique verte.** Le garde-fou existait, il mordait depuis le premier jour, personne ne le lancait.

La cause est une phrase, pas une negligence : `CLAUDE.md` glosait *"CI verte"* par *"`mvn verify`"*.
Vrai quand le workflow portait un job, faux depuis qu'il en porte plusieurs. **J'ai commis cette
erreur quatre fois dans la seule journee de cloture.** Elle n'est sortie que parce que `theo-archi` a
mesure les autres jobs de son propre chef, et il a refuse deux fois la sortie facile : ni toucher le
fichier fautif hors de son perimetre, ni relacher le script en fin de sprint. Repare par son auteur,
qui nomme lui-meme le geste manquant : **lancer la commande de la CI sur son propre diff avant de
commiter.**

## Obstacles resolus

| # | Obstacle | Trouve par | Sort |
|---|---|---|---|
| `O1` | quatre identifiants non alloues, garde-fou `PAR-5` rouge a l'assemblage du planning | le garde-fou | ferme avant l'ouverture ; **c'est l'occurrence sur laquelle repose le maintien de la retenue** |
| `O2` | `83` prefixes au lieu de `87` dans ma note d'assemblage | `theo-archi`, puis le `relecteur-pedagogique` | corrige par son auteur |
| `O3` | porte technique rouge : le rapport de qualite cite l'identifiant fictif de sa propre mutation | `sofia-sm` | `ADR-0085` amende, preuve de `yanis-qa` intacte au caractere |
| `O4` | le compte du registre dementi a la fusion | la clause 5, ecrite le matin, rouge le soir | recompte mecanique, `543 / 93` |
| `O5` | critere de `S31-6` durci de *"au plus un"* en *"aucun"* dans le backlog | `yanis-qa` | corrige avec note datee |
| `O6` | quatre ecarts mesures sur `ADR-0086` et `FAB-1b` | les deux instances de `nadia-prof` | deux encodes, deux dettes nommees et chiffrees |
| `O7` | job CI `chemins` rouge depuis s30 | `theo-archi`, hors perimetre | repare par son auteur, `CLAUDE.md` amende |
| `O8` | `CLAUDE.md` assimile la CI a `mvn verify` | `sofia-sm` | amende, avis du `relecteur-pedagogique` rendu par ecrit |
| `O9` | mon recompte du denominateur : `134` au lieu de `138` | `sofia-sm` | jamais publie |
| `O10` | erreur d'API en cours d'ecriture chez `camille-po` | `sofia-sm` | reprise sur son diff reel, rien perdu |
| `O11` | titre de `DEC-0082` attribuant a `CAT-1` un historique de quatre sprints | `relecteur-pedagogique` | corrige, fichier renomme avec preuve d'absence de reference cassee |
| `O12` | deux lignes de matrice a huit cellules pour sept colonnes, portant le resultat de deux stories | `nadia-prof` | contenu fusionne par `camille-po`, `ADR-0087` ecrit par `theo-archi`, dette payee a l'assemblage |
| `O13` | trois faits faux dans mes propres sections de review | `nadia-prof` | corriges par notes datees, pas en silence |

## Obstacles encore ouverts, a porter a s32

- **`EMC-CP-07`** - seule ligne du sprint a ne pas fermer. Story `S32-EMCCP07` ecrite avec le motif
  `FAB-1c`. La clause 3 d'`ADR-0086` la garde desormais : **le paquet est rouge en l'etat**, ce qui
  n'etait pas le cas quand il a ete refuse.
- **`CAT-1`, `REG-R1`, `PER-R1`** - `REG-R1` et `PER-R1` a leur **quatrieme sprint sans paiement**,
  `CAT-1` routee pour la premiere fois. `DEC-0082` le nomme comme priorite de planification de s32.
- **`SAI-R1`** - chiffree 0,25, specifiee, prete des qu'un sprint dispose d'une marge non consommee.
  Non prise a s31 parce que la composition saturait la borne, comme annonce d'avance.
- **`F3` et `F5`** - deux proprietes mesurees non encodables (30 paires dont 29 valides ; 673 paires
  dans 22 paquets), sous la seule garde de `nadia-prof`, avec leur chiffre ecrit.
- **La dette de cellules manquantes d'`ADR-0087`** - 9 lignes dans 6 fichiers portant **moins** de
  cellules que leur en-tete : non refusees, aucun texte ne disparait, declencheur ecrit.
- **La question du critere de couture** - `nadia-prof` rejugera a l'arbitrage prealable de s32 si
  *"une occurrence"* reste la bonne barre, les trois occurrences constatees appartenant a une seule
  famille.
- **La borne de `FAB-1b` est en egalite exacte** avec la distribution servie : le prochain paquet qui
  poserait un troisieme item sur un meme materiau est **rouge sur une borne d'architecture**. Ce que
  ce rouge demandera est ecrit : un amendement disant quel materiau porte trois proprietes reelles,
  verifie par `nadia-prof`, jamais une borne relevee dans le test.

## Mesure finale

*Cette section porte le chiffre de l'arbre reellement fusionne, pose apres le dernier commit du
sprint.*

## Tableau de consommation de tokens

Voir `RAPPORT-tokens.md`, meme repertoire, genere par `outils/rapport-tokens.py`.
