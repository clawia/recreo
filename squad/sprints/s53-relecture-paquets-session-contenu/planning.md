# [Sprint s53] Planning - 2026-10-07

- **Horodatage de debut** : 2026-10-07 12:18 (UTC+02:00), lu par `date` au moment de l'ecriture. La lecture des sources
  avait commence a 12:10.
- **Anime par** : sofia-sm (casquette Scrum Master ; aucune story de developpement portee par moi a s53)
- **Participants** : aucun agent invoque pour ce planning, qui constate l'etat du depot, decoupe et affecte.
  **Sources lues, par auteur** : camille-po (backlog, section "Session hors sprint s53 special", story
  `S53SPE-RELECTURE` ; `DEC-0111` amendement A.14 et A.16), nadia-prof (`ARBITRAGE-PREALABLE-s52-fra-ce1-questions-de-l-avis.md`
  section 4.3 pour `PAR-9` ; `ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md` section 13.3 pour `PAR-1`, `PAR-2`, `PAR-8` ;
  `ARBITRAGE-PREALABLE-s33-le-plan-2-applique-a-s33-le-vivier-que-quatre-refus-ont-realimente-et-ce-que-la-borne-de-douze-ne-finance-pas.md` pour `PAR-14`), sofia-sm (`retro.md`, `review.md`, `RAPPORT.md` de s52, `squad/README.md`
  sections 4 et 9). Les paquets de contenu eux-memes ont ete lus par moi aux lignes citees plus bas.
- **Slug** : `s53-relecture-paquets-session-contenu`. Le dossier n'existait pas ; il est cree par ce planning.
- **Lignee verifiee avant ecriture** : branche `s53-sofia-planning`, `git log -1 --format=%h` rend `76a50e8b` (tip de
  `round-two`), `bash outils/verifier-lignee.sh` sort 0, `git status --short` est vide. Travail ecrit dans un worktree
  dedie, comme le README section 9 l'exige.

## Objectif du sprint

Gele : **chacun des onze points P1 a P11 de la story `S53SPE-RELECTURE` a une decision ecrite et appliquee** (corriger,
accepter avec motif, ou retirer le paquet), **aucun paquet de la session n'est branche au catalogue**, et les paquets
corriges repassent la suite de `adapter-content` et de `bootstrap` verte. Cet objectif est le critere d'acceptation de la
story de `camille-po`, repris sans le modifier ; il ne promet aucune ligne de matrice : **aucune ligne ne change de statut
a s53**, la story le dit et ce planning ne le conteste pas.

## Provenance, et ce qui manque a la cloture de ce planning

- **Le perimetre vient de deux sources, et d'aucune des deux une `DEC` d'ouverture.** (1) La story `S53SPE-RELECTURE` ecrite par
  `camille-po` au backlog (forme 1). (2) La consigne de l'orchestrateur du 2026-10-07, qui nomme ces onze points comme
  perimetre de s53 : un message d'agent, ni de l'utilisatrice (forme 2) ni de MASTER (forme 3) au sens de la section 2 bis du
  README ; je le cite comme provenance de la consigne, pas comme motif. **Aucune `DEC` d'ouverture de s53 n'existe sur le disque** : `ls squad/decisions/produit` s'arrete a `DEC-0111`, et
  `grep -n "s53" squad/decisions/produit/DEC-index.md` ne rend rien. `DEC-0111` A.14 dit son perimetre de s53 *"propose, non
  ouvert"*. Je planifie ce que la story autorise ; je ne fabrique pas la `DEC` (question Q1).
- **Aucun avis de faisabilite de `theo-archi` n'existe pour s53** (`ls squad/sprints | grep s53` : le dossier n'existait pas)
  et **aucun arbitrage prealable de `nadia-prof` pour s53** (`ls squad/conformite | grep -c ARBITRAGE-PREALABLE-s53` rend 0).
  La section "Avis de faisabilite" ci-dessous le dit story par story. Conformement a l'amendement de s48 (README section 9),
  le planning ne conditionne pas l'affectation a un avis futur : il affecte, et `review.md` dira comment l'avis l'a rattrape.
- **Les paquets de la session ne sont pas dans `catalogue.yaml`.** Mesure faite par moi sur `76a50e8b` :
  `git log --diff-filter=A --name-only --format= 9d44d510..76a50e8b -- adapters/adapter-content/src/main/resources/contenu |
  sort -u | wc -l` rend **45** fichiers de paquet ajoutes par les six commits de la session (`34fdd167`, `8561498c`,
  `d27ba381`, `8de2927f`, `10bad629`, `7854d8d3`) ; pour chacun, `grep -q "paquet: <nom>$"
  adapters/adapter-content/src/main/resources/catalogue.yaml` echoue : **0 sur 45 est branche**. Les deux autres fichiers de
  `contenu/` que le catalogue ne reference pas sont `cm1-sciences-activite-humaine` et `cm1-sciences-terre`, retires a s52
  (47 fichiers non references au total, sur 182).
- **Ecart non elucide : 45 contre 51.** Le sujet du commit `76a50e8b` et la consigne de cette tache disent *51 paquets* ; ma
  mesure par fichier rend 45, soit le nombre de lignes de matrice du meme sujet. Je ne sais pas ce que sont les six autres,
  et je ne relaie pas 51 (question Q2). Les onze points ne portent que sur treize fichiers, tous dans les 45.

## Stories retenues

Le decoupage de `S53SPE-RELECTURE` est **fait ici** : le backlog porte encore une seule story, *"a affecter au planning du
prochain sprint par `sofia-sm`"*. Le critere de decoupage est un fichier de paquet ecrit par une seule main, dans un ordre
donne. **`cm1-emc-principes-republique.yaml` porte quatre points (P3, P4, P9, P11)** : un seul ecrivain, un commit par point.
`cp-qlm-vocabulaire-de-position.yaml` en porte deux (P5, P10, sur le meme item 7).

Competence visee : **transverse, aucune ligne de matrice** pour les neuf stories. Les matieres des paquets sont donnees
pour information.

| Story | Points | Paquet(s) et ce que j'ai verifie | Decision liee | Affectee a (dev) | Decideur du point |
|---|---|---|---|---|---|
| `S53-REL-1` | P1 | `ce1-accord-groupe-nominal.yaml` (francais CE1). `bonneReponse: "des beaux chapeaux"`, ligne 30, distracteurs `des beaux chapeau`, `des beaux chapeaus`. Constate | - | marc-dev | `nadia-prof`, sur proposition de l'auteur |
| `S53-REL-2` | P2 | `ce1-pronoms.yaml` (francais CE1). L'en-tete (lignes 19 a 21) classe dix items par le sens, dix par l'accord ; les items 10, 13, 14 posent "elle", "elle", "ils". **Que le texte porte un candidat de meme genre et nombre : non reverifie, releve par le relecteur** | - | lea-dev | auteur (`lea-dev`) |
| `S53-REL-3` | P3, P4 | `cm1-emc-principes-republique.yaml` (EMC CM1). P3 : l'en-tete (lignes 26 et 27) dit que "la liberte" n'est pas proposee contre "la laicite" ; l'item 4 (`bonneReponse: "la laïcité"`, lignes 81 a 83) la porte en distracteur. Constate. P4 : les indices des items 5, 6, 7, 8, 10, 11 sont aux lignes 88, 92, 96, 100, 108, 112. **Que chacun reformule la bonne reponse est un jugement : non reverifie** | - | marc-dev | `nadia-prof` (P3 avec l'auteur) |
| `S53-REL-4` | P5, P10 | `cp-qlm-vocabulaire-de-position.yaml` item 7 (lignes 72 a 74) et `cp-qlm-situer-la-france.yaml` item 12 (ligne 93). P5 : l'enonce dit "le chien est loin" et demande qui est le plus loin ; la bonne reponse est "le chien". P10 : `distracteurs: ["le chat"]` et `["la France"]`, un distracteur chacun. Constate | - | lea-dev | P5 : `nadia-prof` avec l'auteur ; P10 : `theo-archi` |
| `S53-REL-5` | P6 | `ce1-masses-contenances.yaml` (mathematiques CE1). **L'indice "Le mot kilo veut dire mille." est sur deux items, pas un seul** : "1 kg, combien de g ?" (reponse 1000 g, ligne 74) et "1000 g, combien de kg ?" (reponse 1 kg, ligne 78). `grep -c 'bonneReponse: "1000 g"'` rend 1 | - | marc-dev | `nadia-prof` |
| `S53-REL-6` | P7 | `cm1-hda-epoque-civilisation.yaml` (histoire CM1). L'en-tete (ligne 6) dit qu'aucune oeuvre individuelle n'est nommee ; l'enonce de l'item 4 (ligne 45) dit "comme le pont du Gard". Constate | - | lea-dev | auteur (`lea-dev`) |
| `S53-REL-7` | P8 | `ce1-texte-documentaire.yaml` (francais CE1). `texteSupport: "Range ton cahier. Ferme ta trousse. ..."`, distracteurs `ta trousse`, `ton cahier` (lignes 120 a 122). Constate. **Le balayage des autres possessifs n'est pas fait** : P8 cite deux exemples | - | lea-dev | `nadia-prof` |
| `S53-REL-8` | P9 | `cm1-emc-atteinte-autrui.yaml`, `cm1-emc-respect-differences.yaml`, `cm1-emc-principes-republique.yaml`. Les trois portent la mention *"connaissances civiques et reconnaissance de situations seules ; aucun comportement, aucun engagement ..."* (lecture de `atteinte-autrui`, lignes 66 a 70). **Le marqueur `DEGRADE` est une ligne de commentaire d'en-tete** (`cm1-anglais-ecrire-mots.yaml` ligne 1) et aucun test ne le lit (`grep -rn DEGRADE adapters/adapter-content/src/test/java` ne rend rien) | `DEC` a ecrire par `camille-po` | marc-dev (application, apres la `DEC`) | `camille-po` |
| `S53-REL-9` | P11 | `cm1-arts-decrire-une-oeuvre-lexique.yaml`, `cm1-emc-principes-republique.yaml`, `cm1-hda-caracteristiques-techniques.yaml`, `cm1-hda-decrire-lexique.yaml`. Espace ordinaire avant ":" dans `competence` : constate sur les quatre. **Mesure de l'ecart avec le backlog** ci-dessous | convention a decider | marc-dev (`principes`), lea-dev (les trois autres) | **non designe : question Q4** |

**Mesure de P11** : `grep -l ' competence: ".* :' adapters/adapter-content/src/main/resources/contenu/*.yaml` rend 13
fichiers, les quatre du point et **neuf autres** (`cm1-accord-sujet-verbe`, `cm1-histoire-geographie-*` x4,
`cm1-sciences-chaine-alimentaire`, `cp-decrire-une-oeuvre`, `cp-qlm-cycle-de-vie`, `cp-texte-narratif`). Le backlog dit
*onze paquets plus anciens* : **neuf mesures, onze ecrits**, ecart non explique (le motif peut etre une espace insecable
dans certains cas, un grep different du leur). Je ne tranche pas lequel des deux chiffres est juste ; le decideur le
refait avec sa commande.

### Criteres d'acceptation

**Communs aux neuf stories** (chacun est une commande ou une lecture, aucun ne depend d'une opinion) :

- **C1 - decision sur disque d'abord.** Etant donne un point, quand un developpeur ouvre sa premiere modification de
  fichier de paquet pour ce point, alors le fichier de decision qui le tranche existe sur `round-two` (`git show
  round-two:<fichier>`), et nomme le point, l'issue (corriger, accepter avec motif, ou retirer) et le motif. Un point
  non decide n'est pas edite.
- **C2 - un point accepte ne change aucun YAML.** Etant donne l'issue *accepter avec motif*, quand on lance `git diff
  round-two -- adapters/adapter-content/src/main/resources/contenu/<paquet>.yaml`, alors la sortie est vide pour ce point, et le
  motif est ecrit dans la decision.
- **C3 - aucun branchement.** `git diff round-two --name-only | grep -c catalogue.yaml` rend 0 sur chaque branche de s53.
- **C4 - suite verte, perimetre nomme.** `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content,bootstrap -am test` est vert
  sur le worktree du developpeur (`-am` obligatoire, `bootstrap` inclus parce que la mesure touche du contenu : consigne de s52,
  `CLAUDE.md` section Prerequis de build). **Le critere du backlog, `mvn -B -am -pl adapters/adapter-content test`, est
  repris et renforce de `bootstrap` ; la commande portable est `outils/mvn-jdk21.sh`.** Sur l'arbre fusionne, `mvn -B clean
  verify` de la racine, un reacteur complet par fusion.
- **C5 - aucun garde-fou edite pour passer.** Un test des garde-fous de `70b1cdc1` n'est modifie que si le diff le dit et que
  le developpeur ecrit pourquoi ; une assertion n'est jamais affaiblie (README section 9, regle de fusion).
- **C6 - jobs nommes.** Chaque livraison nomme les jobs de `.github/workflows/ci.yml` qu'elle a lances (`prefixes`, `chemins`,
  `outils`, `build`) et ne deduit pas les autres.

**Specifiques** (formules *etant donne / quand / alors*, chacune "rouge sur l'existant" quand elle porte sur une correction) :

| Story | Critere | Rouge sur l'existant ? |
|---|---|---|
| `S53-REL-1` | Si l'issue est *corriger* : `grep -c 'bonneReponse: "des beaux chapeaux"' ce1-accord-groupe-nominal.yaml` rend 0, et la forme retenue par `nadia-prof` y figure ; aucun distracteur de l'item n'est identique a la bonne reponse ; les deux distracteurs suivent la forme retenue (decision de `nadia-prof`, pas de moi) | oui (rend 1) |
| `S53-REL-2` | Les trois items 10, 13, 14 : soit reecrits de sorte que le texte ne porte aucun candidat de meme genre et nombre que le pronom, soit reclasses. **Dans les deux cas, le compte ecrit dans l'en-tete (PRN-CE1-2, lignes 19 a 21) et la liste d'items qui l'accompagne sont egaux a la classification relue item par item**, la lecture et son resultat etant ecrits dans le fichier de decision de l'auteur | non automatisable |
| `S53-REL-3` | P3 : l'en-tete et l'item 4 se disent la meme chose, soit en retirant "la liberte" des distracteurs de l'item 4 (`grep -c '"la liberté"'` sur cet item rend 0), soit en reecrivant la phrase d'en-tete avec le motif de `nadia-prof`. P4 : pour chacun des six items, la decision ecrit *garde* ou *reecrit* avec le motif ; pour un indice reecrit, le nouvel indice a douze mots au plus (`TRV-CE1-5`, tel que cite par `ce1-pronoms.yaml`, a confirmer comme applicable a l'EMC CM1 par `nadia-prof`) | P3 oui ; P4 : le jugement n'est pas automatisable |
| `S53-REL-4` | P5 : `grep -c "le chien est loin" cp-qlm-vocabulaire-de-position.yaml` rend 0 si l'issue est *corriger* ; l'enonce de l'item ne contient plus la reponse. P10 : apres la decision de `theo-archi`, le nombre d'items a un seul distracteur est ecrit **pour les deux fichiers** (existant : 2), et, si l'issue est *au moins deux distracteurs*, `awk '/distracteurs: \["[^"]*"\]$/' <fichier>` ne rend rien | oui (P5 rend 1, P10 rend 2 lignes) |
| `S53-REL-5` | L'item "1 kg, combien de g ?" ne porte plus `indice: "Le mot kilo veut dire mille."` ou la decision accepte avec motif ; **la decision se prononce aussi sur le second item porteur** (reponse 1 kg), que le backlog ne decrit pas ; un indice reecrit a douze mots au plus et ne contient pas la bonne reponse | oui (rend 2) |
| `S53-REL-6` | Si l'issue est *retirer le nom de l'enonce* : `grep -c "pont du Gard" ` sur les lignes `enonce:` du fichier rend 0. Si l'issue est *garder le nom* : la phrase d'en-tete de la ligne 6 ne dit plus qu'aucune oeuvre n'est nommee, ou le motif de l'ecart est ecrit. La decision ecrit la commande qui a cherche d'autres noms d'oeuvre dans les `enonce` du paquet | oui (1 occurrence dans un enonce) |
| `S53-REL-7` | La decision de `nadia-prof` lit `TRV-CE1-6` sur `ton`, `ta`, `tes`, `mon`, `ma`, `mes`, `notre`, `nos`, `votre`, `vos` dans `enonce`, `texteSupport` et propositions du paquet, **et liste chaque occurrence trouvee avec sa commande de recherche** ; chaque occurrence est reecrite ou acceptee avec motif. Apres correction, la meme recherche rend la liste de la decision, ni plus ni moins | oui (au moins une occurrence, lignes 120 a 122) |
| `S53-REL-8` | La `DEC` de `camille-po` est sur le disque, numerotee, avec statut, et dit en une phrase si la mention exigee d'avance vaut une ligne `DEGRADE`. Si non equivalente : `grep -c '^# DEGRADE' <fichier>` rend 1 pour chacun des trois paquets (forme de `cm1-anglais-ecrire-mots.yaml` ligne 1) ; si equivalente : aucun diff de YAML, et la `DEC` le dit | oui, si l'issue est *non equivalente* (rend 0 aujourd'hui) |
| `S53-REL-9` | La decision de convention est sur le disque. Apres application, `grep -l ' competence: ".* :'` rend exactement la liste des fichiers que la decision declare conformes a l'espace ordinaire, ou rien si elle impose l'espace insecable ; le perimetre (quatre fichiers ou treize, 9 ou 11 anciens) est ecrit dans la decision | oui (rend 13) |

**Ce que `yanis-qa` doit dire**, avant la premiere livraison : parmi ces criteres, lesquels un automate ne peut pas juger
(mes lectures : `S53-REL-2` dans son entier, `S53-REL-3` P4, `S53-REL-4` P5 en ce qu'il demande que la reponse ne soit plus
dans l'enonce). `nadia-prof` les juge a l'audit futur, pas a s53.

## Affectations nominatives (`PAR-16`)

| Agent | Taches | Couloir |
|---|---|---|
| **nadia-prof** (une instance) | Un passage unique, ecrit dans un fichier d'arbitrage dedie : P1, P3, P4, P5, P6, P8, et le volet pedagogique de P10 si `theo-archi` le lui route. Elle lit les propositions de `marc-dev` et `lea-dev` avant de decider. **Aucun verdict, aucune fiche, aucune ligne de matrice** | decision |
| **camille-po** | `DEC` de P9 ; `DEC` d'ouverture de s53 (Q1) ; reflet du decoupage dans le backlog si elle le juge utile | decision |
| **theo-archi** | Avis de faisabilite sur les neuf stories ; decision de P10 (le chargeur) ; un `ADR` seulement si P10 en appelle un (regle ArchUnit ecrite dans le meme geste, `CLAUDE.md`) | decision |
| **relecteur-pedagogique** | Faits de P11 (liste de fichiers, nature de l'espace, commande) et relecture de fin de sprint ; une ligne meme s'il ne trouve rien (amendement s38) | decision et cloture |
| **marc-dev** | `S53-REL-1`, `S53-REL-5`, puis `S53-REL-3` (P3, P4), puis `S53-REL-9` (ligne `principes`), puis `S53-REL-8`. Auteur de P1, P3, P4, P6, P9 : il ecrit ses propositions avant la decision | un worktree |
| **lea-dev** | `S53-REL-2`, `S53-REL-6` (decisions d'auteur, disponibles tout de suite), puis `S53-REL-7`, `S53-REL-4` (P5 puis P10), puis `S53-REL-9` (trois fichiers). Auteure de P2, P5, P7, P8, P10, P11 | un worktree |
| **yanis-qa** | Lecture de testabilite des criteres ci-dessus, rapport qualite de cloture sur l'arbre fusionne, quatre jobs nommes | cloture |
| **ines-design** | aucune | - |
| sofia-sm (casquette dev) | aucune | - |

Justification :

1. **Chaque dev ecrit ses propres paquets.** Les commits d'ajout portent `[MARC]` pour `ce1-accord-groupe-nominal`,
   `ce1-masses-contenances` et les trois paquets EMC, `[LEA]` pour les huit autres fichiers touches
   (`git log --diff-filter=A --format=%s -- <fichier>` sur chacun des treize). Il connait le paquet, et le point qu'un
   relecteur a laisse non modifie est d'abord a lui.
2. **Aucune reaffectation prevue.** Si elle a lieu, elle entre au tableau d'obstacles de la review le jour meme (action A8 de
   s51).
3. **Charge, en interventions connues** : `marc-dev` P1 1 item, P6 2 items, P3 1 item et P4 6 items sur le meme fichier, une ligne
   de `competence`, et trois en-tetes possibles pour P9 ; `lea-dev` P2 3 items, P7 1 item, P8 au moins 1 item plus un balayage,
   P5 et P10 2 items, trois lignes de `competence`. **Total : 17 items distincts connus (P8 a la borne basse) plus quatre
   lignes de `competence`**, sans compter les en-tetes. `lea-dev` porte huit fichiers contre cinq pour `marc-dev` ; si elle
   devient le chemin critique, je peux deplacer `S53-REL-7` vers `marc-dev`, **sous trois conditions** : `lea-dev` n'a pas
   commence, `marc-dev` n'a pas son worktree ouvert sur un autre point, et l'entree au tableau d'obstacles est ecrite le
   jour meme. Decision de capacite, mon role ; elle n'est pas prise ici.
4. **Aucun dev sur deux worktrees en parallele** ; `nadia-prof` n'est pas dans le worktree d'un dev (ses decisions ne
   touchent aucun YAML).

## Capacite, tarif, ordre

**Tarif de `nadia-prof` (unite du depot : le lot a 1,0, la correction ou le rejeu a 0,5, `PAR-10` ; plafond 5,0 par instance,
`PAR-8`).** Aucun de ces postes n'est un lot ni une correction d'un paquet audite : aucun paquet de la session n'a de
verdict, et `PAR-10` chiffre le rejeu d'un paquet deja passe devant elle. **Je compte donc 0,0 sur son plafond de 5,0 et une
seule instance**, et je le marque comme **proposition de tarif a confirmer par `nadia-prof`** (question Q3), puisque le tarif
est a elle. Ce que je n'affirme pas : que son passage ne coute rien ; un passage de decision sur six paquets et environ
douze items lit plus qu'un audit de dix minutes. `PAR-9` (*"a partir de l'ouverture de s53, une `DEC` qui ouvre deux
instances compte 4,0 par instance marge de rejeu comprise"*) **n'est pas declenchee** par ce planning : il n'ouvre aucune
instance. Elle le serait par la `DEC` d'ouverture si elle en ouvrait deux (Q1).

**Tarif de developpement.** Le depot n'en a pas (`DEC-0009` n'en posait pas, `S52-RETRAIT-SCIENCES-CM1` non plus). Je donne
des tailles mesurees, pas une unite que j'inventerais : 9 stories, 11 points, 13 fichiers de contenu au plus, 17 items
distincts connus, quatre lignes de `competence`, aucun `src/main/java` sauf si P10 en appelle un.

**Engage** : 11 points, 9 stories, deux developpeurs, un passage de `nadia-prof`, une decision de `camille-po` (P9), une de
`theo-archi` (P10), un fait de `relecteur-pedagogique` (P11). **Capacite de `nadia-prof` consommee : 0,0 sur 5,0 (a
confirmer).**

**Ordre** (les voies decision se jouent en parallele ; un dev ne commence un point que sur C1) :

| # | Qui | Quoi | Fichier attendu | Debloque |
|---|---|---|---|---|
| U1 | `marc-dev`, `lea-dev` | Ecrire leurs propositions par point (constat avec commande, issue proposee, motif) ; **decider P2 et P7** (`lea-dev`) | `propositions-marc-dev.md`, `propositions-lea-dev.md` dans le dossier du sprint, un fichier par agent | U2 ; `S53-REL-2`, `S53-REL-6` |
| U2 | `nadia-prof` | Passage de decision P1, P3, P4, P5, P6, P8 | arbitrage dedie dans `squad/conformite/` (nom choisi par elle) | `S53-REL-1`, `-3`, `-4` (P5), `-5`, `-7` |
| U3 | `theo-archi` | Avis de faisabilite des neuf stories ; decision de P10 | `avis-faisabilite.md` du dossier, `ADR` si necessaire | `S53-REL-4` (P10) |
| U4 | `camille-po` | `DEC` de P9 ; `DEC` d'ouverture de s53 | fichiers `DEC` | `S53-REL-8` |
| U5 | `relecteur-pedagogique` | Faits de P11 ; proposition de decideur | fichier de relecture du dossier | Q4, puis `S53-REL-9` |

Ordres internes imposes par les fichiers partages :

- `cm1-emc-principes-republique.yaml` : **P3, puis P4, puis P11, puis P9** (P9 en dernier : il peut ajouter une ligne
  d'en-tete, et P3 peut en modifier une autre). Un commit par point.
- `cp-qlm-vocabulaire-de-position.yaml` : **P5 avant P10**. Si P5 reecrit l'item 7 pour porter deux distracteurs, P10 est
  deja soldee pour cet item et il ne reste que l'item 12 de `cp-qlm-situer-la-france.yaml`. Je ne tranche pas ce que P5 fera.

### Isolation et fusion

Une branche, un worktree, un scratchpad par agent, un port par agent si un serveur est lance (aucun serveur de jeu n'est
attendu : aucun paquet n'est branche, donc aucun n'est jouable).

| Agent | Branche | Worktree (dossier voisin du checkout) |
|---|---|---|
| marc-dev | `s53-marc-relecture` | `../recreo-s53-marc-relecture` |
| lea-dev | `s53-lea-relecture` | `../recreo-s53-lea-relecture` |
| nadia-prof | `s53-nadia-decisions` | `../recreo-s53-nadia-decisions` |
| camille-po | `s53-camille-p9` | `../recreo-s53-camille-p9` |
| theo-archi | `s53-theo-p10` | `../recreo-s53-theo-p10` |
| relecteur-pedagogique | `s53-rel-p11` | `../recreo-s53-rel-p11` |

**Longueur des chemins** (proposition de la retro de s52, non encore amendee au README) : le chemin suivi le plus long fait
219 caracteres (`git ls-files | awk '{ print length($0) }' | sort -n | tail -1`, a `76a50e8b`) ; les noms ci-dessus ont 18 a 26
caracteres, soit un total de 247 a 255 caracteres avec le prefixe du poste de s52 (nom + 10), donc une marge de 5 a 13 caracteres sous 260. Le plus long,
`recreo-s53-nadia-decisions`, laisse la marge la plus courte (5 caracteres). Je ne l'ai pas verifie sur un autre poste.

Ordre de fusion : **decisions d'abord** (nadia-prof, camille-po, theo-archi, relecteur-pedagogique, fichiers de propositions), puis
`marc-dev`, puis `lea-dev` ; **un `bash outils/mvn-jdk21.sh -B clean verify` sur chaque commit de fusion**. Les garde-fous de `70b1cdc1`
portent des listes (declarations de paquets, rang de la bonne reponse) : on garde les ajouts des deux cotes. **Tout compte
exact est celui du reacteur complet sur l'arbre fusionne, jamais la somme de deux branches.** Lignee verifiee avant chaque
fusion (`bash outils/verifier-lignee.sh`, `git merge-base --is-ancestor round-two <branche>`).

## Avis de faisabilite - theo-archi

**Aucun avis ecrit a la cloture de ce planning.** Je ne l'ai pas invoque et je n'ecris pas son avis pour lui. Le tableau dit
ce qu'il doit rendre ; la colonne "ma lecture" n'est pas un avis.

| Story | Avis | ADR requis | Ma lecture (non un avis) |
|---|---|---|---|
| `S53-REL-1`, `-2`, `-5`, `-6`, `-7` | a rendre | a determiner par lui | contenu seul ; point de controle : les listes de `70b1cdc1` (rang de la bonne reponse, comptes ecrits dans un motif) peuvent compter ce que ces edits deplacent |
| `S53-REL-3` | a rendre | a determiner | meme point ; six indices reecrits ne touchent aucune liste connue |
| `S53-REL-4` | a rendre, **et la decision de P10 est a lui** | a determiner : si l'issue est une regle sur le nombre de distracteurs, `CLAUDE.md` exige sa regle ArchUnit dans le meme geste | un item a un seul distracteur existe deja ailleurs : `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` cite *"24 items sur 24 portent un seul distracteur, donc deux propositions"* |
| `S53-REL-8` | a rendre | non attendu | un en-tete est un commentaire que rien ne lit |
| `S53-REL-9` | a rendre | non attendu | le champ `competence` est lu par `YamlContentPackLoader` et porte par `ContentDescriptor` ; **je n'ai trouve aucun gabarit de `adapter-web` qui l'affiche** (`grep -rln competence` sur `templates` ne rend rien), et je n'ai pas cherche plus loin |

## Verification poste/story (`PAR-16`, `squad/README.md` section 4)

Faite a la cloture de ce planning, point par point, sur la story `S53SPE-RELECTURE` (aucune `DEC` de composition n'existe pour
s53 : la table de composition est la liste P1 a P11 du backlog).

| Poste | Story | Affectation nominative | Issue |
|---|---|---|---|
| P1 | `S53-REL-1` | marc-dev ; decide `nadia-prof` | engage |
| P2 | `S53-REL-2` | lea-dev ; decide l'auteur | engage |
| P3 | `S53-REL-3` | marc-dev ; decide `nadia-prof` | engage |
| P4 | `S53-REL-3` | marc-dev ; decide `nadia-prof` | engage |
| P5 | `S53-REL-4` | lea-dev ; decide `nadia-prof` | engage |
| P6 | `S53-REL-5` | marc-dev ; decide `nadia-prof` | engage |
| P7 | `S53-REL-6` | lea-dev ; decide l'auteur | engage |
| P8 | `S53-REL-7` | lea-dev ; decide `nadia-prof` | engage |
| P9 | `S53-REL-8` | marc-dev ; decide `camille-po` | engage |
| P10 | `S53-REL-4` | lea-dev ; decide `theo-archi` | engage |
| P11 | `S53-REL-9` | marc-dev et lea-dev ; **decideur non designe (Q4)** | engage pour les faits et l'application, **decision en attente de Q4** |

**Aucun poste engage sans story, aucun sans affectation nominative.** Ecart signale sans correction : le backlog porte encore
*"a affecter au planning du prochain sprint par `sofia-sm`"* et une seule story ; ce planning est l'enregistrement des affectations
et des neuf stories (le backlog est a `camille-po`).

## Branchement des paquets au catalogue : NON INCLUS, proposition a arbitrer par l'utilisatrice

**Ce planning n'engage aucun branchement.** La story dit *"avant tout branchement"* ; son critere interdit seulement qu'un
paquet parte chez `nadia-prof` avec un defaut connu. Ce qui suit est une **proposition (P-BR), que je ne decide pas** et qu'une
`DEC` de `camille-po` ou un arbitrage de l'utilisatrice doit prendre :

- **Ce que brancher demande**, par paquet : une entree de `catalogue.yaml`, ses declarations dans les garde-fous de test,
  l'audit et la fiche de `nadia-prof`, la mention `MNC-1` dans tout Done, un passage de matrice. C'est la matiere de `PAR-9`,
  pas de ce planning.
- **Ordre de grandeur, borne basse, hypothese non verifiee** : au tarif de 1,0 par paquet, 45 paquets coutent 45,0 (51 paquets
  si le chiffre de la consigne est juste : 51,0), soit au moins 6 sprints (7) a 8,0 par sprint, avec les lots illustres a
  1,5 en plus. **Le tarif reel d'un paquet de cette session est a `nadia-prof` ; je ne l'ai pas.**
- **Pas pour s53** : les onze points d'abord, parce qu'un paquet corrige apres son branchement se paie au tarif de rejeu
  (`PAR-10`, ou lot si plus de la moitie des items change, `PAR-14`), alors que corrige avant il se paie au premier passage.
  **Lecture de `PAR-14` sur P4** : six items sur onze (54,5 %) seraient reecrits ; avant tout verdict, cette regle ne
  s'applique pas, et c'est un fait que `nadia-prof` confirme ou corrige.

## Ce qui est reporte, et que ce planning n'engage pas

**Aucun de ces postes n'est engage ni abandonne par ce planning.** `DEC-0111` A.14 dit que cinq d'entre eux *"ne se reportent
plus sans decision"* ; ce planning ne la prend pas, la `DEC` d'ouverture de s53 (Q1) le fait.

| Poste | Source | Etat |
|---|---|---|
| `S52-SCI-TERRE-CM1-REPRISE-APRES-RETRAIT` (1,0), `S52-SCI-ACTIVITE-HUMAINE-CM1-REPRISE-APRES-RETRAIT` (0,5) | backlog, "Cloture de s52" ; `DEC-0111` A.14 | troisieme report, non engage |
| `CRP-CM1-R1` a `R3` (0,5) | `DEC-0111` A.14 | troisieme report, non engage |
| `SYC-CE1-R1`, `MUL-CE1-R1` (0,5 chacune) ; item 16 de `FRA-CE1-25` | `DEC-0111` A.13 ; `retro.md` s52 A3 | premier report |
| `MAT-CE1-02` moitie designation ; `MAT-CE1-14` | `DEC-0111` A.14 | troisieme report pour la premiere |
| Passe transverse `PAR-4` | `DEC-0111` section 7 | troisieme report |
| `ART-CP-10` et statut d'`ADR-0104` | `DEC-0111` A.14 ; `retro.md` s52 A8 | hors perimetre |
| `S51-OUTIL-JOURNAL-TOKENS` | `retro.md` s52 A9 | troisieme report, non chiffree |
| Lignes de francais CE1 atteignables (15), vivier | `DEC-0111` A.14 | sans story |
| Actions de la retro de s52 A1, A2, A4, A5, A6, A7, A10 | `retro.md` s52 | ouvertes, chacune a son proprietaire |

## Risques

| Code | Risque | Ce qui le contient |
|---|---|---|
| R1 | Quatre points sur `cm1-emc-principes-republique.yaml`, deux sur `cp-qlm-vocabulaire-de-position.yaml` : deux ecrivains sur un meme fichier produiraient un conflit de fusion sur de l'YAML | un seul ecrivain par fichier, un ordre ecrit, un commit par point |
| R2 | Un edit deplace un compte que les garde-fous de `70b1cdc1` ecrivent en dur (rang de la bonne reponse, comptes dans un motif) et le rouge ne se voit qu'a la fusion, comme la symetrie a s52 | mesure du dev avec `-am` et `bootstrap` ; reacteur complet a chaque fusion ; `theo-archi` nomme les listes dans son avis |
| R3 | **Faux vert par `-pl` sans `-am`** : l'ecart muet que `CLAUDE.md` decrit | C4 ; toute mesure partielle citee nomme son perimetre |
| R4 | Les points a deux decideurs nommes (P1 : *"nadia-prof / auteur"* ; P3 et P5 : *"auteur / nadia-prof"*) ne disent pas qui l'emporte en cas de desaccord | ma lecture, a confirmer par `camille-po` : l'auteur propose, `nadia-prof` decide (table de routage : pedagogie a `nadia-prof`) |
| R5 | Le decideur de P11 n'est pas designe et les deux chiffres (9, 11) different | Q4 ; `S53-REL-9` ne s'edite qu'apres C1 |
| R6 | 45 contre 51 : si six paquets existent que je n'ai pas vus, aucun point ne les couvre et la story serait incomplete sans que personne le sache | Q2, avant toute proposition de branchement |
| R7 | `nadia-prof` est le chemin critique des cinq stories qui dependent d'elle ; son passage est un seul fichier | un seul passage, ses questions groupees ; une decision partielle debloque les stories correspondantes |
| R8 | La `DEC` d'ouverture manque : le perimetre repose sur un message d'agent et une story, et les cinq postes que `DEC-0111` A.14 voulait engager ou abandonner au troisieme report passent ce troisieme report sans la decision exigee | Q1, a `camille-po` ; ecrit ici pour que la review le constate |

## Questions ouvertes

Elles sont ecrites comme ouvertes ; aucune n'est tranchee par ce planning.

| Code | Question | Route a | Artefact attendu |
|---|---|---|---|
| Q1 | Qui ouvre s53 : une `DEC` d'ouverture existe-t-elle ou doit-elle l'etre, et que fait-elle des cinq postes au troisieme report (`DEC-0111` A.14) ? Ce planning ne les engage pas et ne les abandonne pas | `camille-po`, utilisatrice | `DEC` |
| Q2 | Quels sont les 51 paquets, si 45 fichiers seulement ont ete ajoutes ? | `camille-po`, orchestrateur | liste, ou correction du chiffre |
| Q3 | Le passage de decision de `nadia-prof` coute-t-il 0,0 sur son plafond de 5,0 et tient-il en une instance ? | `nadia-prof` | prix ecrit dans son arbitrage |
| Q4 | Qui decide P11 (espace avant ":" dans `competence`) : `relecteur-pedagogique` (redaction), `camille-po` (convention de contenu, `DEC-0002`), ou `nadia-prof` ? Et neuf anciens fichiers ou onze ? | `camille-po` | designation, puis decision |
| Q5 | P10 : un item a un seul distracteur est-il admissible dans ces deux paquets, et sa part pedagogique (deux propositions, une chance sur deux) revient-elle a `nadia-prof` ? | `theo-archi`, `nadia-prof` | decision, `ADR` si une regle en sort |
| Q6 | P9 : la mention exigee d'avance est-elle equivalente a une ligne `DEGRADE` ? | `camille-po` | `DEC` |
| Q7 | Lecture de `PAR-14` sur P4 (six items sur onze) : s'applique-t-elle avant tout verdict ? | `nadia-prof` | ligne dans son arbitrage |
| Q8 | P1, P3, P5 : l'auteur propose et `nadia-prof` decide, comme je le lis ? | `camille-po` | confirmation ou correction |

## Porte technique

- **Juge : la CI, tous les jobs de `.github/workflows/ci.yml`** : `prefixes`, `chemins`, `outils`, `build`. `mvn verify` n'est
  que le contenu du job `build`. Chaque agent nomme les jobs qu'il a lances.
- **`yanis-qa`** : rapport qualite de cloture sur l'arbre fusionne, quatre jobs nommes, commande exacte, total mesure et origine
  de l'ecart avec la base. **Je n'annonce aucun total de tests** : le dernier connu est 2174 a `509552f0` (s52), avant six
  commits de s53 qui declarent 45 paquets dans les garde-fous ; la base de s53 est a mesurer.
- **`theo-archi`** : confirmation ArchUnit a la cloture ; aucune regle n'est attendue sauf si P10 en appelle une.
- **Porte pedagogique** : **aucune a s53**. Aucun paquet n'est branche, aucun n'est joue, `nadia-prof` ne rend aucun verdict ; ses
  decisions ne sont pas un Done. Aucun Done n'est prononce par ce planning.

## Ce que ce planning ne fait pas

- Il ne decide aucun des onze points, ni la forme de P1, ni le nombre de distracteurs de P10, ni l'equivalence de P9, ni le
  decideur de P11.
- Il n'engage ni n'abandonne aucun des reports, ne cree aucune `DEC` ni aucun `ADR`, et n'amende ni `PAR-9`, ni `PAR-2`, ni `PAR-4`.
- Il n'ouvre aucun worktree ni aucune branche d'agent (hors le sien) : l'ouverture se fait une fois ce planning fusionne sur
  `round-two` (README section 9, amendement s40).
- **Il ne modifie ni le backlog, ni la matrice, ni une fiche, ni le registre.** Le process demande a `camille-po` de tenir le
  backlog et la matrice : le decoupage de `S53SPE-RELECTURE` lui est transmis, ici, sans etre ecrit a sa place. La matrice
  ne change pas, aucune ligne ne change de statut.

## Decisions prises

Aucune `ADR` ni `DEC` n'est creee ou amendee par ce planning. Cinq decisions de planning, qui relevent de mon role :

1. **Decoupage en neuf stories par fichier et par ecrivain** (`S53-REL-1` a `-9`), avec l'ordre interne de
   `cm1-emc-principes-republique.yaml` et de `cp-qlm-vocabulaire-de-position.yaml`.
2. **Affectations nominatives** : marc-dev et lea-dev, auteurs de leurs paquets ; decideurs routes d'apres le backlog.
3. **Aucun branchement au catalogue n'est engage** ; il reste une proposition a arbitrer.
4. **Un seul passage de `nadia-prof`, une seule instance**, tarif marque comme proposition.
5. **Isolation** : un worktree, une branche et un scratchpad par agent ; fusion decisions, puis marc-dev, puis lea-dev, un
   reacteur complet par fusion.

## Prochaines etapes

Ce planning est fusionne sur `round-two` par moi avant tout lancement. Ensuite, par agent :

- `marc-dev`, `lea-dev` : propositions par point (U1), puis edits sur C1.
- `nadia-prof` : passage de decision (U2) ; reponses a Q3 et Q7.
- `theo-archi` : avis de faisabilite et P10 (U3).
- `camille-po` : `DEC` de P9 et `DEC` d'ouverture (U4) ; reponses a Q1, Q2, Q4, Q6, Q8.
- `relecteur-pedagogique` : faits de P11 (U5).
- `yanis-qa` : lecture de testabilite des criteres.
- `sofia-sm` : ouverture des worktrees des agents routes, verification de lignee, fusions, `review.md` avec le tableau des
  obstacles, `RAPPORT.md`, `python outils/rapport-tokens.py --sprint s53`, `retro.md`. **Le daily n'est pas journalise.**
