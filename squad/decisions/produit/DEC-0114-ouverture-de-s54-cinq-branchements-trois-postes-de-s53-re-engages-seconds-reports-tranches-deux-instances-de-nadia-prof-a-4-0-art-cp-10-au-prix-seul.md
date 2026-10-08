# DEC-0114 - Ouverture de s54 : cinq branchements au catalogue, trois postes de s53 re-engages, seconds reports
# tranches, deux instances de `nadia-prof` a 4,0 par instance, `ART-CP-10` au prix seul

- **Type** : decision d'ouverture de sprint (perimetre, instances, reports, routage)
- **Date** : 2026-10-07
- **Auteur** : `camille-po`
- **Sprint** : s54
- **Competences programme visees** : `FRA-CE1-18`, `FRA-CE1-19` (francais CE1), `SCI-CM1-15`, `SCI-CM1-18`
  (sciences et technologie CM1), `EMC-CM1-02` (EMC CM1) pour les cinq branchements ; `SCI-CM1-10` (ligne deja
  `couvert-valide`, non rouverte) pour `CRP-CM1-R1` a `R3` ; `FRA-CE1-25` (idem) pour `SYC-CE1-R1` ; `ART-CP-10`
  pour le prix seul. Les autres postes sont transverses - pas de contenu pedagogique. **Aucune ligne de matrice ne
  change de statut par cette decision** : elle n'ouvre que des audits.
- **Statut** : **Acceptee le 2026-10-08 (amendement A.27, s55)**, `Proposee` du 2026-10-07 au 2026-10-08. Une seule decision de cette `DEC` n'avait que la forme 3 de `DEC-0062` pour source :
  l'ordre des matieres au-dela de s54 (section 7) ; `DEC-0115` section 3 le reprend sous un motif de forme 1 par rang, ce que la section 0.4 (b) exige. Toutes les autres portent un motif de forme 1 ou 2, s'appliquent
  des maintenant et sont routees en section 9. Passage a `Acceptee` : voir section 0.4 et A.27.
- **Lien** : `DEC-0112` (sections 5, 6, 8 ; amendee le 2026-10-07) ; `DEC-0113` (`Refusee`, section 9) ; `DEC-0111`
  (A.13, A.14) ; `DEC-0110` (sections 14.7, 17.2) ; `DEC-0062` (formes de citation, regles 3.2 et 3.5) ; `DEC-0002` ;
  `squad/sprints/s54-branchement-des-premiers-paquets-et-reports-de-s53/planning.md` ;
  `squad/sprints/s53-relecture-paquets-session-contenu/relecture-cloture.md`, `review.md`, `retro.md`,
  `confirmation-archunit.md` ;
  `squad/conformite/ARBITRAGE-PREALABLE-s52-fra-ce1-questions-de-l-avis.md` (section 4.3 : `PAR-9`) et
  `ARBITRAGE-PREALABLE-s52-instances-ce1.md` (section 5 : refus d'amender `PAR-2`) ;
  `docs/backlog/backlog.md` (sections "Reports de s53" et "Sprint 54")

---

## 0. Provenance et methode

### 0.1 Ce que j'ai recu de MASTER, cite tel que recu, fige a la reception (`DEC-0062` regle 3.5)

Quatre messages, **recus de MASTER, sans source dans le depot, le 2026-10-07**, par l'intermediaire de l'agent qui m'a lance.
Je n'ai pas le message d'origine de l'utilisatrice. Les trois premiers me sont parvenus en un seul message, le quatrieme dans un second,
pendant la redaction. Les mots entre guillemets sont ceux du message recu ; ce qui est entre parentheses est le commentaire de l'agent
intermediaire, pas de l'utilisatrice.

1. *"valide l'ordre proposé, fusionne le planning"* (l'agent intermediaire precise : l'ordre des matieres de `Q10` du planning, soit
   francais CE1, puis sciences et EMC CM1, puis arbitrage de `nadia-prof` sur les 17 paquets sans plafond, puis mathematiques CE1).
2. A propos de **"D2" de s53** : *"reporté"* (l'agent precise : `CRP-CM1-R1` a `R3`, `PAR-4`, journal de tokens, engages a s54).
3. A propos de **"D4"** : *"est pour s54"* (l'agent precise : le branchement des paquets).
4. *"sur art-cp go pour le 1"* (l'agent precise : l'option 1 est le defaut du planning pour `ART-CP-10` : s54 n'engage que le prix de la
   mecanique par `theo-archi` et le plafond du paquet `DEGRADE` par `nadia-prof`, rien n'est construit, la decision de financer la mecanique
   se prend apres lecture des prix).

L'agent m'indique aussi que les lignes `ART-CP-10`, `SCI-CM1-15`, `SCI-CM1-18` sont reportees a s54 (`DEC-0113` `Refusee`) ; **il ne me
cite aucun mot de l'utilisatrice pour cela**. Cette phrase n'est donc pas ici une citation : le depot porte ses mots en
`DEC-0113` section 9 (de `camille-po`, forme 1 sous la regle 3.5, qui y a fige la citation).

**Les etiquettes "D2" et "D4" ne sont pas dans le depot** (`grep` des etiquettes `D1` a `D6` dans `RAPPORT.md`, `review.md`, `retro.md` de s53 :
rien ; la review de s53 numerote ses questions `U1` a `U5`). Je les rapproche, comme l'agent le fait, de `U1` (les trois postes de `DEC-0112`
section 5 non livres) et de `U2` (brancher, et dans quel ordre) ; ce rapprochement est le sien, je le signale parce que je ne peux pas le verifier.

**Borne de la forme 3 (`DEC-0062` section 3.2) : ces quatre messages fondent l'ordre dans lequel le travail se fait, jamais sa raison.** Aucun
critere d'acceptation, aucun verdict, aucune decision de cette `DEC` ne s'appuie sur eux seuls : chacune porte son motif de forme 1 ou 2.

### 0.2 Sources de forme 1, par auteur

`sofia-sm` : `planning.md` de s54 (perimetre, capacite, affectations, questions Q1 a Q10), `review.md` et `retro.md` de s53 ;
`relecteur-pedagogique` : `relecture-cloture.md` de s53 (R1 a R5) ; `theo-archi` : `confirmation-archunit.md` de s53 section 5 (prix de
l'outil de journal) ; `yanis-qa` : `rapport-qualite.md` de s53 section 3 ; `nadia-prof` : les arbitrages prealables de s52 cites en lien (`PAR-9`,
refus d'amender `PAR-2`) ; `camille-po` : `DEC-0110` a `DEC-0113`, backlog.

### 0.3 Mesures lancees pour cette decision (arbre `round-two` a `4bb4dbd5`, worktree `s54-camille-ouverture`, 2026-10-07)

| Mesure | Commande | Rendu | Ce que cela fait au critere correctif |
|---|---|---|---|
| Aucun des cinq paquets n'a d'entree de catalogue | `grep -c "paquet: <nom>$" adapters/adapter-content/src/main/resources/catalogue.yaml` pour `ce1-futur`, `ce1-passe-compose`, `cm1-sciences-terre-jour-nuit-saisons`, `cm1-sciences-environnement-activite-humaine`, `cm1-emc-atteinte-autrui` | 0, 0, 0, 0, 0 | le critere C3 du planning est rouge sur l'existant |
| Items des cinq paquets | `grep -c '^ *- enonce:'` sur chaque fichier | 24, 24, 12, 12, 11 (83) | recoupe le planning |
| Le jeu de corps est servi, avec l'indice faux | `grep -c "corps-cm1"` sur `catalogue.yaml` ; `grep -n "ne fait rien d'autre"` sur `cm1-sciences-corps.yaml` | 1 ; ligne 77 | le critere de `CRP-CM1-R1` est rouge sur l'existant |
| Espace ordinaire avant ":" dans les `enonce` de `cm1-grands-nombres` | `grep -cE '^ *- enonce: ".* :' adapters/adapter-content/src/main/resources/contenu/cm1-grands-nombres.yaml` | 14 | rouge sur l'existant |
| Fichiers a espace insecable avant ":" dans `competence` | script Python de lecture seule, voir `DEC-0112` amendement A.1 | 4 | corrige `DEC-0112` |

Ce que je n'ai pas lu : aucun avis de `theo-archi` pour s54 (il n'existe pas), aucun arbitrage prealable de `nadia-prof` pour s54 (il n'existe pas),
aucun verdict de CI (le planning dit qu'aucun n'est lisible depuis ce poste, `Q9`). Le jugement pedagogique d'aucun paquet n'est porte ici.

### 0.4 Pourquoi `Proposee`, et ce qui la fait passer a `Acceptee`

> **Amende le 2026-10-08 (A.27) :** `Acceptee` par la lecture (b), sur le motif de forme 1 de `DEC-0115` section 3 ; (a) n'est pas satisfaite, `ok` n'est pas un motif.

La seule decision qui n'a pas d'autre source que la forme 3 est l'**ordre des matieres au-dela de s54** (section 7). `DEC-0112` section 8
ecrivait *"Je n'ecris pas cet ordre sans son arbitrage"* ; son arbitrage m'arrive par un canal qui ne vaut jamais motif. Je ne le promeus pas
en motif en l'ecrivant dans une `DEC` `Acceptee` (meme geste que `DEC-0113` section 1). `Acceptee` quand l'un des deux est vrai :
(a) ses mots sont ecrits dans le depot en forme 2 (avec la date) ; (b) la `DEC` d'ouverture de s55 reprend l'ordre sous son propre motif de
forme 1. Si ses mots divergent de la section 7, la section 7 est amendee et **s54 n'est pas touche**.

---

## 1. Perimetre : ce que s54 engage

Reprend le planning de `sofia-sm`, ligne a ligne. Tarifs : **proposes par le planning, a confirmer par `nadia-prof`** (`Q3`, `Q4`) ; je n'en fixe aucun.

| Story | Competence visee | Fondement (forme) | Instance, tarif propose | Dev |
|---|---|---|---|---|
| `S51-SCI-CORPS-CM1-RESERVES` (`CRP-CM1-R1` a `R3`) | `SCI-CM1-10`, `couvert-valide`, non rouverte | `DEC-0112` 5.2 (forme 1) ; section 4 ci-dessous | B, rejeu 0,5 | `marc-dev` |
| `S54-BR-FRA-CE1-18` (`ce1-futur`, 24 items) | `FRA-CE1-18` | planning, "Pourquoi ces cinq" (forme 1) ; `DEC-0112` 8 | A, lot 1,0 | `marc-dev` |
| `S54-BR-FRA-CE1-19` (`ce1-passe-compose`, 24 items) | `FRA-CE1-19` | idem | A, lot 1,0 | `marc-dev` |
| `S54-BR-SCI-CM1-15` (reprise du paquet refuse a s51, 12 items) | `SCI-CM1-15` | `DEC-0113` section 9 (forme 1, citation de l'utilisatrice figee) ; planning | B, 1,0 | `lea-dev` |
| `S54-BR-SCI-CM1-18` (reprise du paquet refuse deux fois a s51, 12 items) | `SCI-CM1-18` | idem | B, 0,5 | `lea-dev` |
| `S54-BR-EMC-CM1-02` (`cm1-emc-atteinte-autrui`, 11 items) | `EMC-CM1-02` | planning (forme 1) ; `DEC-0112` section 4 (P9, accepte sans changement) | B, lot 1,0 | `lea-dev` |
| `S52-SYC-CE1-R1` + item 16 de `FRA-CE1-25` | `FRA-CE1-25`, `couvert-valide`, non rouverte | section 3 | A, 0,5 | `marc-dev` |
| `S53-PASSE-TRANSVERSE` (`PAR-4`) | transverse | `DEC-0112` 5.4 (forme 1) ; section 4 | A, 0,5 | `nadia-prof` |
| `S51-OUTIL-JOURNAL-TOKENS` | transverse | `DEC-0112` 5.5 ; `confirmation-archunit.md` s53 section 5 (de `theo-archi`) | aucune | `sofia-sm`, casquette developpeuse |
| `S53-REPORT-GRANDS-NOMBRES` | `cm1-grands-nombres`, ligne deja `couvert-valide`, non rouverte | `DEC-0002` amendement du 2026-08-04 ; `relecture-cloture.md` s53 section 2 | `Q5` : a `nadia-prof` | `lea-dev` |
| `S53-REPORT-REDACTION` | transverse | `relecture-cloture.md` s53 section 3 | R3 : `nadia-prof` ; le reste : section 8 | `camille-po` |
| `S54-ART-CP-10-PRIX` | `ART-CP-10` | section 6 | aucun dev | `theo-archi`, `nadia-prof` |

**Criteres d'acceptation** : ceux du planning (C1 a C7 et tableau "Specifiques"), repris dans les stories du backlog, section "Sprint 54". Chacun
est verifie contre l'existant avant d'etre repris (section 0.3) : les criteres de `CRP`, de branchement et de `GRANDS-NOMBRES` sont rouges
aujourd'hui. **Aucun n'est vert sur ce qu'il corrige.** Pour les deux reprises de sciences, le critere reprend **les mots des refus**, pas une
propriete plus commode a tester : item 4, *"le distracteur de la distance est une affirmation vraie"* ; item 11, *"le printemps"* a d'aussi
longs jours que *"l'ete"* (`FICHE-bandit-manchot-terre-cm1.md` section 7, cite par `DEC-0113` 3.2) ; item 11, *"Deux propositions repondent a
la question ecrite"* (`FICHE-bandit-manchot-activite-humaine-cm1.md`, S2-3, cite par `DEC-0113` 3.3). Je n'ai pas relu les fiches elles-memes
pour cette decision : si ma citation de leur citation differe de la fiche, la fiche gouverne.

---

## 2. Reponses aux questions du planning routees a `camille-po`

**Q1 - cette `DEC` engage-t-elle les stories, tranche-t-elle les trois lignes, designe-t-elle les proprietaires, reconnait-elle le message de
lancement comme source du perimetre ?** Elle engage les stories de la section 1. Les trois lignes : `SCI-CM1-15` et `SCI-CM1-18` sont engagees par
leur branchement ; `ART-CP-10` est engagee **au prix seul** (section 6) : aucune des trois n'est reportee en silence. Les proprietaires sont ceux des
affectations de `sofia-sm` (planning, section "Affectations nominatives"), que je ne re-decide pas : l'affectation est son role. **Le message
de lancement n'est pas la source du perimetre** : la source est le planning (forme 1) et ma decision de PO ; les messages de MASTER sont cites en
section 0.1 et ne fondent que l'ordre.

**Q2 - `SYC-CE1-R1` + item 16 : engager ; `MUL-CE1-R1` : retirer ?** Section 3.

**Q4 - les deux reprises de sciences, 1,0 et 0,5 ou tarif de lot (`PAR-14`) ; comptent-elles pour des lots sous `PAR-8` ; B a 3,5 remplit-elle la
barre de `DEC-0112` 5.1 ?** Le tarif et la lecture de `PAR-14` sont a `nadia-prof` et je ne les tranche pas. Ma part : `DEC-0112` 5.1 posait comme
condition de rouverture **une `DEC` d'ouverture qui nomme le porteur**. Cette `DEC` nomme **l'instance B, sciences et EMC CM1**, comptee a 4,0
(section 5). Que B atteigne la barre de sa condition (i) est un jugement de `nadia-prof`. **Si elle dit que non**, je ne retire pas les deux lignes
en silence et je ne les reporte pas : `DEC-0112` 5.1 prevoit son amendement ecrit de la condition (i) ou de `PAR-2`, ou la question a l'utilisatrice,
que `sofia-sm` porte.

**Q6 - `ART-CP-10` : financer la mecanique, brancher le paquet `DEGRADE` sans fermer, ou une autre issue de `DEC-0111` section 9 ?**
**Tranchee par l'arbitrage recu de MASTER le 2026-10-07 (section 0.1, point 4) : option 1 du planning**, section 6. Le planning n'est pas modifie.

**Q8 - francais CE1 : un decoupage qui ne coupe aucune frontiere de la table de s48 section 7 est-il propose ?** Oui, **propose et non engage**,
section 7.2.

**Q10 - ordre des matieres.** Section 7.1.

**Questions qui ne sont pas a moi** : `Q3` (prix reel d'un paquet, d'un arbitrage prealable, de la correction de R3, des deux passes de `PAR-4`),
`Q5` (la typographie d'un jeu servi passe-t-elle par elle : texte de l'enfant, porte pedagogique, `DEC-0002`), `Q7` (plafond atteignable par
groupe pour les 17 paquets sans plafond) : `nadia-prof`. `Q9` (verdict de la CI sur `round-two`) : l'utilisatrice, via `sofia-sm`. Je les ai
lues et ne les tranche pas.

---

## 3. Seconds reports : `SYC-CE1-R1`, item 16 de `FRA-CE1-25`, `MUL-CE1-R1`

`DEC-0112` section 6 : *"Un troisieme report n'est plus permis"*. Premier report : s52 (`DEC-0111` A.13) ; second : s53. Chaque poste est
engage ou retire ici.

**`SYC-CE1-R1` (items 19 et 23) et item 16 de `FRA-CE1-25` : engages, instance A, 0,5, `marc-dev`, un seul re-audit des trois items.**
Motifs, tous de forme 1 :

1. **Les trois motifs de `DEC-0111` A.13 pour ne pas engager ne tiennent plus a s54.** (1) A.13 ne l'engageait pas parce que A etait au plafond de
   5,0 sans marge ; le planning de s54 compte A a **3,5 avec sa marge de 0,5 nommee**, soit 4,0 (`PAR-9`) avec 0,5 non alloue. (2) A.13 notait
   que les deux instances avaient depense leur rejeu ; celles de s54 n'ont rien depense. (3) Le troisieme motif (rien n'est faux pour l'enfant)
   reste vrai, mais il n'a jamais ete un motif de retrait : c'etait un motif de priorite.
2. **Meme instance, meme matiere, meme auteur** : A porte le francais CE1 (`PAR-2`), `marc-dev` est l'auteur du paquet (`46e69b13`, planning).
   Aucun terrain neuf ajoute (`FRA-CE1-25` est `couvert-valide`).
3. **L'item 16 est une observation de `nadia-prof`, a re-auditer avec `SYC-CE1-R1`** (`DEC-0112` section 6, qui la reprend de `DEC-0111`
   A.13) ; deux passages separes coutent deux fois 0,5.

Ce que cela ne fait pas : la ligne `FRA-CE1-25` reste `couvert-valide`, rien n'est rouvert (`DEC-0112` : reserve non bloquante). Critere : celui de la
fiche, mot pour mot, *"le ballon et la force sont chacun portes par un sujet feminin ou sans genre (Elle lance le ballon. ; Cette femme est
forte. avec costaude, ou Ce cheval est fort.). Les reponses ne changent pas, sauf l'accord. Rien d'autre ne change."* (`DEC-0111` A.13, qui cite la
fiche, section 5). Non automatisable : `nadia-prof` rejoue les items 19, 23 et 16.

**`MUL-CE1-R1` : retiree comme poste de sprint, la reserve reste ecrite.** Motifs, de forme 1 :

1. **Le planning mesure que mathematiques CE1 ne porte aucun lot a s54** (groupe G4 : aucune ligne ne ferme, `MAT-CE1-01`, `-10`, `-11`, `-15`
   au plus `en cours`) ; je n'ouvre pas une instance de mathematiques pour 0,5.
2. **L'engager par l'instance A irait contre l'esprit de `PAR-2`** (une matiere et un niveau ne sont jamais partages ; A est le francais CE1)
   sans que `PAR-2` l'interdise, comme le planning le dit : ce serait faire porter a l'instance du chemin critique un poste qui n'est pas de sa
   matiere, pour 0,5 de capacite que le planning garde en reserve de rejeu.
3. **Rien n'est faux pour l'enfant** : `MUL-CE1-R1` est une phrase qui gene la lecture et ne fausse pas l'exercice (`DEC-0112` section 6) ; la ligne
   `MAT-CE1-06` est `couvert-valide`.

**Ce que "retire" n'est pas : un troisieme report.** C'est une issue que `DEC-0112` section 6 nomme. Elle est comptee : premier report s52, second
s53, **retrait s54**. La reserve reste ecrite dans `FICHE-bandit-manchot-sens-multiplication-ce1.md` section 6 et dans sa story du backlog. **Ce qui la
rouvre** : la `DEC` d'ouverture du premier sprint qui porte une instance de mathematiques CE1, qui l'engage ou la retire a son tour, avec ce compte.

---

## 4. Reports comptes, clause de `DEC-0111` A.14, et regle des engagements non livres

**La clause.** `DEC-0111` A.14 : *"les cinq postes marques troisieme ne se reportent plus sans decision"*. `DEC-0112` a engage trois de ces postes
a s53 ; **aucun n'a ete livre** (`review.md` de s53 section 3, preuves par commande : `cm1-sciences-corps.yaml` inchange, aucun fichier
`PASSE-TRANSVERSE-s53*`, `outils/` inchange). Un engagement non livre etait donc, dans les faits, un report de plus, sous un autre nom.

**Decision : un poste engage par une `DEC` d'ouverture et non livre a la cloture du sprint compte comme un report.** Applique retroactivement a
s53, ce qui donne :

| Poste | Compte avant cette decision | Issue a s54 |
|---|---|---|
| `CRP-CM1-R1` a `R3` | non engage s51, s52 ; engage s53, non livre : **trois** | **Engage**, premier a fusionner ; jeu servi, indice faux (`DEC-0112` 5.2) |
| `S53-PASSE-TRANSVERSE` (`PAR-4`) | non engagee s51, s52 ; engagee s53, non livree : **trois** | **Engagee** ; ne depend d'aucun branchement ni d'aucune decision |
| `S51-OUTIL-JOURNAL-TOKENS` | non chiffree s51, s52 ; engagee s53 sans proprietaire, non livree : **trois** | **Engagee** avec proprietaire (`sofia-sm`, casquette developpeuse) et prix (`theo-archi`, `confirmation-archunit.md` s53 section 5) |
| `SYC-CE1-R1`, item 16, `MUL-CE1-R1` | s52, s53 : deux | section 3 |
| `MAT-CE1-02` moitie designation, `MAT-CE1-14` | retire s53 (`DEC-0112` 5.3) | inchange, rouverte avec `A3` d'`ADR-0080` |
| `ART-CP-10` | s50, s51, s52, s53 : quatre | engagee au prix, section 6 |
| `SCI-CM1-15`, `SCI-CM1-18` | s51, s52, s53 : trois ; retirees puis remises a s54 par `DEC-0113` section 9 | engagees, section 1 |

**Ce que la regle impose a la cloture de s54.** Un des trois postes (`CRP`, `PAR-4`, journal) non livre a la cloture n'est pas reporte par la `DEC`
de s55 : `sofia-sm` le porte a l'utilisatrice avec le motif et la question *"engage-t-elle, retire-t-elle"*, comme `DEC-0112` 5.5 le prevoyait
pour l'outil de journal (*"ni retire en silence ni reporte une quatrieme fois"*). **Je ne la reecris pas pour moi-meme en reportant a mon tour.**

**Story absorbee en cours de sprint.** `review.md` de s53, obstacle 3 : `S53-REL-10` a ete livree sans acte d'ouverture. Regle, a ecrire dans le
meme geste que sa fusion : *une story ajoutee a un sprint apres son ouverture s'acte par un amendement a la `DEC` d'ouverture, signe par
`camille-po`, avant l'ouverture du premier worktree concerne*. C'est la lecon de la retro de s53 reprise ici ; `sofia-sm` la porte au README si elle
l'y veut, c'est son fichier. L'amendement de `DEC-0112` (A.3) solde `S53-REL-10`.

**Ce que cette regle ne decide pas** : qu'un poste non livre soit retire. Elle l'oblige a etre decide.

---

## 5. Capacite et ouverture des deux instances (`PAR-9`)

> **Complete le 2026-10-08 (A.9 a A.16) :** une troisieme instance (C, un paquet, 1,5) est demandee ; le "sans ecart" ci-dessous ne vaut que pour A et B (A.13).

`PAR-9` (`ARBITRAGE-PREALABLE-s52-fra-ce1-questions-de-l-avis.md` section 4.3, cite par `DEC-0111` A.14) : *"a partir de l'ouverture de s53, une `DEC`
qui ouvre deux instances compte 4,0 par instance marge de rejeu comprise, ou elle ecrit l'ecart et son motif."* **Cette `DEC` ouvre deux instances
de `nadia-prof` et les compte a 4,0 chacune, sans ecart.**

| | **A** (francais CE1, `PAR-7` : porte l'accumulation) | **B** (sciences CM1, EMC CM1) |
|---|---|---|
| Lots | `FRA-CE1-18` 1,0 ; `FRA-CE1-19` 1,0 | `SCI-CM1-15` 1,0 ; `SCI-CM1-18` 0,5 ; `EMC-CM1-02` 1,0 |
| Corrections et passes | `SYC-CE1-R1` + item 16 : 0,5 ; `PAR-4` : 0,5 | `CRP-CM1-R1` a `R3` : 0,5 |
| Marge de rejeu nommee, allouee a aucun lot | 0,5 | 0,5 |
| Alloue | 3,5 | 3,5 |
| **Compte par cette `DEC` (`PAR-9`)** | **4,0** : 3,5 + 0,5 non alloue, tenu comme second rejeu | **4,0** : idem |

Chiffres du planning (section "Capacite par instance"), de forme 1 ; **tarifs proposes, a confirmer par `nadia-prof`**. Engage : 7,0 sur 8,0, le
1,0 restant n'etant pas un lot. Le 0,5 non alloue de chaque instance **n'est pas une capacite pour un lot de plus** : le planning refuse la
variante a trois lots francais (`FRA-CE1-24`), qui mettrait A a 4,5, au-dessus de `PAR-9`, et trois paquets d'un terrain neuf sur une instance.
Je ne la reprends pas.

**Ce que cette `DEC` ne decide pas** : si B a 3,0 de travail et ses deux marges atteint la barre de la condition (i) (`Q4`) ; si `FUT-CE1-*` et
`PCO-CE1-*` sont des cousines de `PRS-CE1-*`, validee a s52, ou un terrain neuf au sens de `PAR-13` (`nadia-prof` le lit, planning `Q3`) ; si
l'arbitrage prealable de s54 coute 0,0 ou 0,5 (a 0,5, A est a 4,0 alloues, toujours dans `PAR-9`). `PAR-1` (un lot n'est jamais partage) et `PAR-2`
(aucune matiere n'est partagee : francais CE1 sur A ; sciences et EMC CM1 sur B) sont tenues par le tableau.

---

## 6. `ART-CP-10` : prix et plafond, rien n'est construit (Q6 tranchee)

> **Amende le 2026-10-08 (A.1, A.5, A.6) :** `ART-CP-10` est aussi engagee au branchement du paquet `DEGRADE` `cp-arts-oeuvres-familles`, sans fermer la ligne. Le point 3 ci-dessous ("aucun developpeur") ne vaut plus pour ce paquet ; il vaut toujours pour la mecanique.

**Source de l'ordre** : *"sur art-cp go pour le 1"*, recu de MASTER (section 0.1, point 4). **Ce qui fonde le contenu de l'option, lui, est de forme 1** :
le planning (section "Les trois lignes" : *"Ce que s54 engage : le prix de la mecanique (`theo-archi`) et le plafond du paquet (`nadia-prof`, une
ligne). Ce que s54 n'engage pas : la construction"*) ; `DEC-0113` 3.1 (la ligne n'est pas impossible : `nadia-prof` a repondu a `C5` clause 1
le 2026-10-06) ; le paquet `cp-arts-oeuvres-familles`, qui dit dans son en-tete *"Ce paquet ne ferme pas la ligne"* (planning).

**Decision.**

1. **`theo-archi`** ecrit le **prix de la mecanique qui montre deux oeuvres ensemble**, en tailles (le depot n'a pas de tarif de developpement,
   `DEC-0112` section 7), et le **statut d'`ADR-0104`** (`Propose` a l'ouverture ; annotation du `relecteur-pedagogique`, 2026-10-08 : `ADR-0104` est `Accepte` depuis le 2026-10-07, `theo-archi`, T6). C'est son geste, pas le mien : question `T6` du planning.
2. **`nadia-prof`** ecrit **en une ligne le plafond d'un paquet `DEGRADE` pour `ART-CP-10`** (ce que le paquet atteint au plus sur la ligne). Je
   n'ecris pas ce plafond.
3. **Rien n'est construit** : aucun developpeur, aucun `ADR` accepte par moi, aucune ligne de matrice touchee. `ART-CP-10` reste `non couvert`.
4. **La decision de financer la mecanique se prend apres lecture des deux prix**, a l'ouverture de s55 (`DEC` d'ouverture de s55, ou
   l'utilisatrice si je lui porte le choix avec les prix en main). Elle n'est pas prise ici et elle ne se reporte pas sans compte.
5. **Compte** : s50, s51, s52, s53 : quatre sprints hors perimetre. **s54 n'est pas un cinquieme report** : le poste est engage, au prix. Si le prix
   ou le plafond n'est pas rendu a la cloture de s54, la regle de la section 4 s'applique : `sofia-sm` le porte a l'utilisatrice.

**Ce qui est ecrit comme defaut du planning avant d'etre un arbitrage** : le planning proposait cette issue comme defaut ; l'arbitrage de MASTER
la confirme. Aucune autre issue de `DEC-0111` section 9 (declarer la ligne `non couvrable en l'etat`) n'est ecrite : c'est un jugement de
`nadia-prof` et de l'utilisatrice, et `DEC-0113` a ete refusee.

---

## 7. Ordre des matieres (forme 3 seule) et decoupage du francais CE1 (Q8)

### 7.1 L'ordre

> **Amende le 2026-10-08 (A.3) :** ordre retenu, `G3` au dernier rang avec `G4`, `G8` d'abord ecartee ; toujours de forme 3.

**Ordre recu de MASTER** (section 0.1, point 1) : francais CE1, puis sciences et EMC CM1, puis arbitrage de `nadia-prof` sur les 17 paquets sans
plafond, puis mathematiques CE1. Il est celui de la `Q10` du planning (forme 1), dont les mesures sont : le francais CE1 est le chemin critique
(17 paquets, une instance, trois lots au plus par sprint, six sprints au moins) ; les deux reprises de sciences et l'EMC CM1 ont des motifs
de refus ecrits, pris par le paquet ; 17 paquets n'ont aucun plafond ecrit pour un paquet `DEGRADE` texte seul (acheter des lots avant de savoir
si la ligne ferme, c'est reproduire s51).

**Ce que cette section fait** : elle consigne l'ordre. **Ce qu'elle ne fait pas** : s54 n'en depend pas (section 1 : chaque branchement a son motif de
forme 1), et l'ordre de s55 et suivants est a la `DEC` d'ouverture de chacun. **Une precision que ses mots ne tranchent pas** : le planning classe en
dernier rang les groupes G3 (deux paquets de francais CE1 plafonnes `en cours`) et G4 (cinq de mathematiques CE1), ensemble. Les mots recus
disent *"mathematiques CE1"* ; G3 est du francais CE1. Je laisse G3 au dernier rang comme le planning, **sans pretendre que ses mots le disent** ;
la `DEC` de s55 le tranche.

### 7.2 Proposition de decoupage du francais CE1 (Q8) : proposee, non engagee, a juger par `nadia-prof` et `theo-archi`

`nadia-prof` a refuse d'amender `PAR-2` pour le francais CE1 le 2026-10-06 et a ecrit ce qui la ferait reexaminer : *"une proposition ecrite de
`camille-po` qui decoupe le francais CE1 sans couper aucune frontiere de la table de s48 section 7, sur un sprint ou aucune autre instance ne porte
de terrain neuf, avec un avis de `theo-archi` qui dit comment deux instances n'amendent pas `ADR-0065` en meme temps"*
(`ARBITRAGE-PREALABLE-s52-instances-ce1.md` section 5, verdict). Voici la proposition. **Aucun sprint ne la demande a s54** : je ne l'engage pas.

Les 13 paquets de francais CE1 non engages a s54, par ligne (`lignesDeReferentiel` lue sur chaque fichier), regroupes par les frontieres de la table
de s48 section 7 :

| Groupe | Lignes (paquets) | Frontieres de la table, toutes a l'interieur du groupe |
|---|---|---|
| **X** : lecture et lexique grammatical | `-04` (`texte-narratif`), `-05` (`pronoms`), `-06` (`inference`), `-07` (`texte-documentaire`), `-11` (`negation`), `-12` (`classes-de-mots`), `-13` (`verbe-sujet`) | `-04`/`-06`, `-05`/`-04` ; `-12`/`-13` (`VSJ-CE1-1`) ; `-11` n'en porte aucune |
| **Y** : phrase, accords, famille de mots | `-08` (`ordre-des-mots`), `-09` (`relire-une-phrase`), `-10` (`phrases-types`), `-14` (`accord-sujet-verbe`), `-15` (`accord-groupe-nominal`), `-24` (`familles-de-mots`) | `-08`/`-09`/`-10` ; `-09` renvoie a `-14` et `-15` (`RLP-CE1-1`, arbitrage s52 section 5, motif 1) ; `-24`/`-25` (`-25` est valide) |

Sept paquets pour X, six pour Y ; `FRA-CE1-18` et `-19` sont a A a s54. **Limite que je nomme** : cette proposition ne satisfait pas `PAR-13` des
le premier sprint de deux instances, parce que X et Y portent chacun des conditions qui n'ont jamais rencontre de contenu (`RCT`, `PRN`, `INF`,
`DOC` pour X ; `OMP`, `RLP`, `FAM` pour Y, arbitrage de s52 section 5, motif 2). **Elle ne vaut donc qu'a partir du sprint ou le groupe porte par
la premiere instance a deja rencontre du contenu** : un sprint ou A prend X seule, puis un sprint ou A (X) et A' (Y) tournent ensemble, Y seul
portant un terrain neuf. Au rythme de trois lots par instance, les 13 paquets tiennent en trois sprints (3, puis 3 et 3, puis 1 et 3) au lieu de
cinq a sept ; ce chiffre est le mien, il suppose zero refus au-dela de la marge, et il suppose que `nadia-prof` consente a lever `PAR-2` pour ce
sprint-la, ce qui est sa decision. **Cela exige aussi l'avis de `theo-archi` sur `ADR-0065`** (deux instances, une seule amendee a la fois).

---

## 8. Relecture de cloture de s53 : R1 a R5 (`relecture-cloture.md` section 3)

| Point | A qui | Issue |
|---|---|---|
| R1 : "0 fichier" dans `DEC-0112` | `camille-po` | **Amende** : `DEC-0112` amendement A.1 ; mesure refaite, 4 fichiers ; cause du premier resultat non etablie, dit comme telle |
| R2 : "Relaye de l'utilisatrice" | `camille-po` | **Amende** : `DEC-0112` amendement A.2, les deux occurrences dites message d'agent qui ne fonde rien ; `relecture-p11.md` reste a son auteur |
| R3 : "six fichiers" de la section 11 de l'arbitrage de s53 | `nadia-prof` (planning, retro de s53 A4) | **Non amende par moi** : c'est le document d'un autre agent, je ne le reecris pas (`DEC-0062` section 5, meme motif). Le backlog et le planning la portent a `nadia-prof`, sans prix propose. Mon seul acte : le fait est le sien a corriger, un seul fichier (`cm1-grands-nombres`, 14 `enonce`), recoupe par moi (section 0.3) |
| R4 : les 14 `enonce` et les deux constantes de `PaquetCm1GrandsNombresTest` | `theo-archi`, developpeur du test | **Story `S53-REPORT-GRANDS-NOMBRES`, engagee** (section 1), `lea-dev`, un seul commit pour les 14 lignes et les deux constantes |
| R5 : "onze paquets plus anciens" du backlog | `camille-po` | **Erratum au backlog** : neuf mesures, pas onze ; ecart non explique (`relecture-p11.md` section 3) ; meme geste que "51 paquets" |
| `S53-REL-10` (obstacle 3 de `review.md`) | `camille-po` | **Amende** : `DEC-0112` amendement A.3 ; entree au backlog, statut tenue |

---

## 9. Routage (changement de code et de fichiers)

**Une decision qui implique un changement n'est pas prise sans proprietaire** : les routages sont dans la meme passe que cette decision, via
`sofia-sm`, avec les stories du backlog, section "Sprint 54".

| A qui | Quoi |
|---|---|
| `sofia-sm` | Fusionner cette `DEC` et son amendement de `DEC-0112` **avant** d'ouvrir les worktrees des developpeurs (critere C1 du planning) ; ouvrir les worktrees ; porter a l'utilisatrice `Q9` (verdict de la CI sur `round-two`) et, a la cloture, tout poste non livre de la section 4 ; porter a l'utilisatrice la promotion de la section 0.4 (ses mots en forme 2) |
| `nadia-prof` | Arbitrage prealable de s54 : `Q3` (tarifs), `Q4` (reprises, `PAR-14`, barre de B), `Q5` (typographie de `cm1-grands-nombres`), `Q7` (plafond des 17 paquets sans plafond), lecture de `PAR-13` sur `FUT`/`PCO` ; le plafond d'une ligne de `ART-CP-10` ; correction de la section 11 de son arbitrage de s53 (R3) ; audits, rejeu, re-audit `SYC`, passe `PAR-4` ; jugement de la section 7.2 |
| `theo-archi` | Avis de faisabilite `T1` a `T7` du planning ; prix de l'outil de journal avec `ecrire_rapport_sprint` et `main` sans `--sprint` (`T4`) ; prix de la mecanique d'`ART-CP-10` et statut d'`ADR-0104` (`T6`) ; avis sur `ADR-0065` pour la section 7.2 |
| `marc-dev` | `S51-SCI-CORPS-CM1-RESERVES` (premiere a fusionner), `S54-BR-FRA-CE1-18`, `-19`, `S52-SYC-CE1-R1`, dans cet ordre, un worktree a la fois |
| `lea-dev` | `S54-BR-SCI-CM1-15`, `-18`, `S54-BR-EMC-CM1-02`, `S53-REPORT-GRANDS-NOMBRES` |
| `yanis-qa` | Lecture de testabilite des criteres du backlog "Sprint 54", en disant lesquels un automate ne juge pas ; rapport qualite de cloture, jobs nommes |
| `relecteur-pedagogique` | Relecture de cloture, dont les provenances de la section 0.1 (formes 3 citees telles que recues) |
| `camille-po` | Backlog et `DEC-index.md` (ce commit) ; fiches et matrice **au fil des verdicts de `nadia-prof`** ; la matrice n'est pas touchee ici |

---

## 10. Ce que cette decision ne fait pas

- Elle ne prononce aucun Done pedagogique et ne juge la pedagogie d'aucun paquet. **Elle n'edite ni `MATRICE-couverture.md`, ni une fiche, ni un
  fichier de paquet, ni `catalogue.yaml`.** La matrice reste a 132 / 182 ; le mieux que s54 peut produire est 137 / 182, un refus restant un refus.
- Elle n'ecrit aucun code et ne tranche aucune question technique (`T1` a `T7`, mecanique d'`ART-CP-10`, `ADR-0065`, `ADR-0104`).
- Elle ne fixe aucun tarif de `nadia-prof`, ne lit pas `PAR-13` ni `PAR-14` a sa place et n'amende ni `PAR-2`, ni `PAR-8`, ni `PAR-9`.
- Elle ne modifie pas le planning de s54 : `Q6` y reste ecrite comme ouverte ; **elle est tranchee par la section 6 de cette decision**, a lire
  avec le planning.

---

## Decisions prises

1. **s54 s'ouvre sur le perimetre du planning** : cinq branchements (`FRA-CE1-18`, `-19`, `SCI-CM1-15`, `-18`, `EMC-CM1-02`, 83 items), `CRP-CM1-R1` a
   `R3`, `PAR-4`, journal de tokens (etendu a `ecrire_rapport_sprint` et a `main` sans `--sprint`), `S53-REPORT-GRANDS-NOMBRES`, `S53-REPORT-REDACTION`,
   `S54-ART-CP-10-PRIX`. Statut `Proposee` : seule la section 7 n'a que la forme 3.
2. **Les quatre messages de MASTER sont cites tels que recus et figes** ; la forme 3 fonde l'ordre, pas la raison.
3. **`SYC-CE1-R1` + item 16 de `FRA-CE1-25` : engages** (A, 0,5, `marc-dev`). **`MUL-CE1-R1` : retiree comme poste de sprint**, reserve conservee,
   rouverte par la `DEC` du premier sprint de mathematiques CE1.
4. **Clause de `DEC-0111` A.14 : un poste engage par une `DEC` d'ouverture et non livre compte comme un report.** `CRP`, `PAR-4` et journal sont a leur
   quatrieme sprint ; non livres a la cloture de s54, ils vont a l'utilisatrice, pas a la `DEC` de s55.
5. **Une story absorbee en cours de sprint s'acte par un amendement a la `DEC` d'ouverture avant l'ouverture du premier worktree concerne.**
6. **Amendement de `DEC-0112`** : "0 fichier" corrige en 4 (cause non etablie), provenance "Relaye de l'utilisatrice" corrigee, `S53-REL-10` actee
   (tenue). Erratum R5 au backlog. R3 reste a `nadia-prof`.
7. **Deux instances de `nadia-prof` ouvertes** [complete le 2026-10-08, A.10 : une troisieme, C, sous la condition de A.13] **comptees a 4,0 chacune** (3,5 alloues + 0,5 non alloue), sans ecart a `PAR-9` ; tarifs, barre de B et
   `PAR-13` restent a `nadia-prof`.
8. **`ART-CP-10` : prix et plafond seulement, rien n'est construit** [amende le 2026-10-08, A.1 : le paquet `DEGRADE` est aussi branche] ; `Q6` tranchee par l'arbitrage de MASTER (forme 3, option 1) ; la decision de
   financer se prend a s55 apres lecture des deux prix ; s54 n'est pas un cinquieme report.
9. **Ordre des matieres consigne** (forme 3) : francais CE1, sciences et EMC CM1, arbitrage de `nadia-prof` sur G7 a G11, mathematiques CE1 ;
   decoupage du francais CE1 en deux groupes X et Y propose, non engage.

---

## Amendement du 2026-10-08 (s54, `camille-po`) : `ART-CP-10` passe du prix seul au branchement du paquet `DEGRADE` ; verdict de CI ; ordre des matieres

Les passages amendes sont marques dans le texte, pas effaces (`DEC-0062`, amendement B.3). Cet amendement arrive apres l'ouverture de s54 et apres la
fusion de `s54-marc-branchement` et de `s54-lea-branchement` : je ne le date pas en arriere. Une seule `DEC`, un seul amendement, pour les trois
arbitrages recus le meme jour ; **le statut de `DEC-0114` reste `Proposee`** (A.4).

### A.0 Ce que j'ai recu, cite tel que recu et fige a la reception (`DEC-0062` regle 3.5)

Un message de l'agent qui m'a lance, le 2026-10-08, **sans source dans le depot**. Il dit que l'utilisatrice a tranche ce jour les questions `Q6`, `Q9` et
`Q10` du planning. Je n'ai pas ses mots. Les formules entre guillemets sont celles du message de l'agent, pas les siennes :

1. `Q6` : *"ART-CP-10, on BRANCHE LE PAQUET DEGRADE sans fermer la ligne (issue 'brancher sans fermer' de DEC-0111 section 9)"* ; l'agent precise qu'elle
   *"n'engage pas la construction de la mecanique 'deux oeuvres ensemble'"*.
2. `Q9` : *"la CI est verte sur GitHub pour round-two"* (l'agent : verdict donne par l'utilisatrice).
3. `Q10` : *"ordre propose par sofia-sm retenu : G1/G2, puis G6/G5, puis arbitrage de nadia-prof sur G7 a G11, puis G3/G4"* ; *"l'alternative G8 d'abord
   est ecartee"*.

**Borne de la forme 3 (`DEC-0062` section 3.2), la meme qu'en section 0.1 : ces messages fondent l'ordre dans lequel le travail se fait, jamais sa raison.**
Chaque decision ci-dessous porte son motif de forme 1.

**Une precision sur la reference de `Q6`.** `DEC-0111` section 9 ne nomme que deux issues pour `ART-CP-10` : allouer le budget, ou declarer la ligne
*non couvrable en l'etat*. L'issue *"brancher sans fermer"* est la deuxieme option de `Q6` du planning de s54, pas un texte de `DEC-0111`. Le sens du
message est clair ; je corrige la reference pour qu'un lecteur ne cherche pas une phrase qui n'y est pas.

### A.1 `Q6` : `ART-CP-10` est engagee au branchement du paquet `DEGRADE` (amende : sections 1 et 6, decision 8)

**Decision.** `cp-arts-oeuvres-familles` (12 items, auteure `lea-dev`, `lignesDeReferentiel: ["ART-CP-10"]`) est branche au catalogue par une story neuve,
`S54-BR-ART-CP-10` (backlog, section "Sprint 54"). **La ligne ne ferme pas** : `ART-CP-10` reste `non couvert`, la matrice n'est pas touchee.
**Rien de la mecanique "deux oeuvres ensemble" n'est construit** : `ADR-0104` reste `Propose` (a la date de A.1 ; `Accepte` le 2026-10-07, annotation du `relecteur-pedagogique`), aucune story de developpement ne la porte.
`S54-ART-CP-10-PRIX` reste engagee telle que la section 6 l'ecrit (prix de `theo-archi`, plafond de `nadia-prof`) : le message ne la retire pas, et je ne
retire pas sans ordre un poste que cette `DEC` a engage. La decision de financer la mecanique reste a s55 (section 6, point 4) ; elle n'est pas prise ici.

**Motifs, de forme 1.**

1. **La ligne n'est pas impossible** (`DEC-0113` 3.1 : reponse de `nadia-prof` a `C5` clause 1, le 2026-10-06), et le paquet existe. Son en-tete dit
   *"Ce paquet ne ferme pas la ligne"* et *"ne sert ni la reconnaissance ni le rapprochement d'oeuvres, seulement le vocabulaire de leurs familles"*.
   Le brancher sert l'enfant sur ce vocabulaire, pas sur la competence de la ligne.
2. **Le report se compte.** `ART-CP-10` : s50, s51, s52, s53, soit quatre sprints hors perimetre (section 4). Un paquet branche est une issue que `Q6`
   nomme ; elle sort la ligne du silence sans pretendre la couvrir.
3. **Meme regime que les cinq autres branchements** : criteres `C1` a `C7` du planning ; un branchement s'arrete au verdict.

**Ce que ce branchement ne dit pas.** Il ne dit pas que le paquet est bon, ni que la ligne est a moitie couverte. Si la ligne passe un jour a `en cours`, c'est un
plafond que `nadia-prof` ecrit (A.6), pas une consequence du branchement. Le titre et la consigne de l'entree de catalogue ne promettent pas ce que l'en-tete du
paquet dit qu'il ne fait pas (critere dans la story).

**Report.** `ART-CP-10` est engagee par cet amendement ; s54 n'est pas un cinquieme report. **Si le paquet n'est ni branche ni audite a la cloture de s54, la
regle de la section 4 s'applique** : `sofia-sm` le porte a l'utilisatrice avec la question *"engage-t-elle, retire-t-elle"*.

### A.2 `Q9` : le verdict de CI recu de l'utilisatrice, cite comme tel

Le message dit que l'utilisatrice a donne le verdict : la CI est verte sur GitHub pour `round-two`. **Ce verdict est le sien, relaye ; je ne l'ai pas lu sur la
CI**, et je n'en tire ni nom de job ni commit : le message n'en nomme aucun, et le planning posait `Q9` pour un commit precis que le verdict recu ne cite pas.
Je ne l'etends ni au sommet actuel de `round-two`, ni a un job en particulier. **Il ne dispense personne de nommer les jobs qu'il a lances** (`C6` du
planning ; `CLAUDE.md`, amendement du 2026-09-08). `Q9` est close comme question posee a l'utilisatrice ; elle ne devient pas une mesure.

### A.3 `Q10` : l'ordre de `sofia-sm` est retenu, `G8` d'abord est ecartee (amende : section 7.1)

Ordre recu : `G1`/`G2`, puis `G6`/`G5`, puis arbitrage de `nadia-prof` sur `G7` a `G11`, puis `G3`/`G4`. **Deux points qui ne sont pas evidents.**

1. **La precision que la section 7.1 laissait ouverte** (`G3` est du francais CE1, les mots recus le 2026-10-07 disaient *"mathematiques CE1"*) **est levee dans ce message** :
   `G3` est au dernier rang avec `G4`, comme le planning. Source de forme 3 : elle fonde l'ordre ; la `DEC` de s55 le reprend sous son propre motif.
2. **L'ordre et `Q6` se touchent.** `cp-arts-oeuvres-familles` est dans `G11`, que l'ordre place derriere un arbitrage de `nadia-prof` sur un plafond ecrit
   *"avant qu'un lot soit achete"* (planning, "Ce qui est reporte"). Le branchement d'`ART-CP-10` est donc le seul lot de `G7` a `G11` achete avant cet
   arbitrage, **par le choix de `Q6`, pas par oubli**. Ce qui garde la coherence : le plafond d'une ligne pour `ART-CP-10` (`S54-ART-CP-10-PRIX`) est le
   premier acte de son arbitrage sur `G11`, et il dit ce que l'audit du paquet peut juger. Qu'il precede l'audit est a elle ; je le demande, je ne l'ordonne pas.

L'ordre de s55 et suivants reste a la `DEC` d'ouverture de chaque sprint (section 7.1). **Section 0.4 inchangee** : le message de `Q10` arrive de nouveau par un
agent, sans les mots de l'utilisatrice ; il ne promeut pas la section 7 en motif.

### A.4 Statut

`Proposee`, sur le motif de la section 0.4, inchange : la seule decision qui n'a que la forme 3 pour source est toujours l'ordre au-dela de s54. A.1 a un motif de
forme 1 et s'applique des maintenant ; A.2 et A.3 sont des consignations de forme 3.

### A.5 Capacite : ce que le branchement coute, et ce que je ne decide pas

Le planning disait `ART-CP-10` *"non financee dans les 8,0 de s54 sans retirer un lot"*. **Cet amendement l'engage sans la financer** : aucune instance ne l'audite aujourd'hui.
Chiffres du planning et de la section 5 ; tarifs *proposes, a confirmer par `nadia-prof`* (`Q3`).

| | Instance A (francais CE1) | Instance B (sciences et EMC CM1) |
|---|---|---|
| Alloue avant cet amendement | 3,5 (2 lots) | 3,5 (3 lots) |
| Avec un audit de `cp-arts-oeuvres-familles` a 1,0 (tarif du depot) | **4,5** : 0,5 au-dessus de `PAR-9`, plus de marge de rejeu non allouee ; 5,0 si l'arbitrage prealable est chiffre a 0,5 (plafond dur de `PAR-8`) | **4,5**, et **quatre lots** si les deux reprises comptent pour des lots (`Q4`) : au-dessus de `PAR-8` |
| Si le paquet est refuse | +0,5 de rejeu, sans marge | idem |

Issues possibles, **que `nadia-prof` et l'utilisatrice choisissent ; je n'en retiens aucune** : (a) l'instance A prend l'audit, avec un ecart a `PAR-9` ecrit par une `DEC`
(precedent : la variante `P3` du planning, ecartee par la section 5) ; (b) une instance prend l'audit et un de ses lots sort du sprint, ce qui est un report de plus pour le poste
retire ; (c) une troisieme instance, hors des 8,0 de `PAR-9` ; (d) le paquet est branche a s54 et audite a s55. **(d) appelle d'abord une reponse de `sofia-sm` et `theo-archi`** :
un paquet au catalogue sans verdict est-il jouable par un enfant ? C'est la situation que `DEC-0009` a coutee a s04 ; les cinq autres branchements de s54 posent la meme question.

**Cout de developpement, chiffre du planning** : environ 590 lignes en six fichiers pour un branchement (une entree de catalogue, un test de paquet si `T1` le confirme, un
inventaire de chaines, des declarations de garde-fous), dont les deux tiers pour le test du paquet. L'affectation est a `sofia-sm` ; je propose `lea-dev`, auteure du paquet.
**Cout pour `nadia-prof`** : 1,0 propose pour l'audit, 0,5 de rejeu en cas de refus, plus la ligne de plafond, proposee a 0,0.

### A.6 Ce qui reste a `nadia-prof` (rien de cela n'est ecrit par moi)

1. **Le plafond d'un paquet `DEGRADE` pour `ART-CP-10`**, en une ligne : ce que le paquet atteint au plus sur la ligne. L'en-tete du paquet ecrit qu'il ne la ferme pas ;
   la matrice ecrit qu'une designation textuelle d'une oeuvre est une contrefacon (`CON-1`, `ART-4`). Que ce paquet-ci, qui ne nomme aucune oeuvre, y echappe est son jugement.
2. **Le tarif** d'audit de ce paquet (`Q3`) et l'instance qui le porte (A.5).
3. **`PAR-13`** : arts plastiques CP est-il un terrain neuf pour elle ? S'il l'est, il s'ajoute au francais CE1 de l'instance A.
4. **Le verdict**, et le bloc de validation de la fiche, que j'ouvre quand le jeu entre en construction et que je ne remplis pas.
5. **Les faits du paquet** : son en-tete les dit *"a confirmer par nadia-prof, seule autorite sur le Done pedagogique"*.
6. **`Q7` pour `G11`** : traite-t-elle `cp-arts-oeuvres-familles` avec les deux autres paquets de `G11` ou a part ?

### A.7 Routage (dans la meme passe que l'amendement)

| A qui | Quoi |
|---|---|
| `sofia-sm` | Affecter `S54-BR-ART-CP-10` (je propose `lea-dev`) ; porter a l'utilisatrice, avec la table A.5, le choix de l'instance et du financement, et la question (d) ; fusionner cet amendement avant d'ouvrir le worktree (`C1` du planning) |
| `nadia-prof` | A.6 |
| `theo-archi` | Prix de la mecanique et statut d'`ADR-0104` (`T6`), inchange ; avis sur (d) |
| `yanis-qa` | Lecture de testabilite des criteres de `S54-BR-ART-CP-10`, en disant lesquels un automate ne juge pas |
| `camille-po` | Backlog et `DEC-index.md` (ce commit). Matrice, fiche, `catalogue.yaml` : non touches |

### A.8 Ce que cet amendement ne fait pas

Il ne touche ni `catalogue.yaml`, ni un paquet, ni une fiche, ni `MATRICE-couverture.md` (qui reste a 132 / 182 ; le mieux que s54 produit reste 137 / 182, `ART-CP-10` n'y
entre pas). Il ne prononce aucun Done, ne fixe aucun plafond ni tarif, ne tranche pas l'instance et ne decide pas de financer la mecanique.

---

## Amendement du 2026-10-08 (s54, `camille-po`), suite : `Q11`, une troisieme instance de `nadia-prof` (instance C) pour auditer `cp-arts-oeuvres-familles`

Meme regime que l'amendement precedent : passages amendes marques, pas effaces ; arrive apres l'ouverture de s54 ; **le statut de `DEC-0114` reste `Proposee`** (A.16).

### A.9 Ce que j'ai recu, cite tel que recu et fige a la reception (`DEC-0062` regle 3.5)

Un message de l'agent qui m'a lance, le 2026-10-08, **sans source dans le depot**. Il dit que l'utilisatrice a tranche `Q11`, c'est-a-dire l'issue 3 de A.5 de l'amendement
precedent : *"UNE TROISIEME INSTANCE de nadia-prof est ouverte (instance C) pour auditer le paquet cp-arts-oeuvres-familles (S54-BR-ART-CP-10)"*, les instances A et B gardant
*"leurs charges inchangees (3,5 chacune, pas de depassement de PAR-9)"*. Je n'ai pas ses mots ; la formule est celle du message de l'agent. **Forme 3 de `DEC-0062` (section 3.2) :
elle fonde l'ordre dans lequel le travail se fait, jamais sa raison.** Le motif est de forme 1 et il est ecrit en A.10. Je ne promeus pas ce message en forme 2 ; qu'elle redise
ses mots est ce qui le ferait (section 0.4).

### A.10 Decision : l'instance C est ouverte, pour un paquet (retient l'issue (c) de A.5 ; ecarte (a), (b) et (d) pour s54)

**Decision.** s54 compte une troisieme instance de `nadia-prof`, **l'instance C**, dont le **seul perimetre** est l'audit de `cp-arts-oeuvres-familles` (12 items, arts plastiques CP,
`ART-CP-10`), apres son branchement par `S54-BR-ART-CP-10`. Elle ne porte aucun autre lot, aucune reprise, aucune passe. A et B gardent leurs charges de la section 5 (3,5 allouees,
4,0 comptees chacune), sans modification. **Sous la condition de A.13** : tant que l'ecart ou l'amendement qui y est decrit n'est pas ecrit, l'ouverture est le choix recu de l'utilisatrice, pas encore une decision applicable.

**Motifs, de forme 1.**

1. **A.5 a chiffre les issues.** Sur A ou sur B, l'audit portait l'instance a 4,5, au-dessus de `PAR-9`, sans marge de rejeu non allouee, ou au-dessus de `PAR-8` (quatre lots sur B).
   L'issue (c) est la seule qui ne touche ni un lot de A ni un lot de B : elle n'ajoute pas un report a un poste engage, et elle evite l'issue (d), un paquet au catalogue sans verdict
   (la situation que `DEC-0009` a coutee a s04).
2. **Le report se compte.** `ART-CP-10` : s50 a s53, quatre sprints hors perimetre (section 4). L'issue (d) en aurait fait un cinquieme sprint sans verdict sur un paquet deja branche.

**Ce que cette instance ne fait pas.** Elle ne ferme pas la ligne, ne juge pas la mecanique "deux oeuvres ensemble" (`ADR-0104` reste `Propose`, A.1 ; `Accepte` le 2026-10-07, annotation du `relecteur-pedagogique`) et ne dispense pas d'un verdict ecrit
par `nadia-prof` dans le bloc de validation de la fiche, que j'ouvre quand le jeu entre en construction.

### A.11 Tarif, a confirmer par `nadia-prof` (`Q3`)

| Instance C | Tarif propose | Source |
|---|---|---|
| Audit de `cp-arts-oeuvres-familles` | 1,0 | tarif du depot, A.5 |
| Rejeu en cas de refus | 0,5 | A.5 ; non alloue a un lot, tenu comme second rejeu |
| Ligne de plafond (A.12) | 0,0 | A.5 |
| **Compte pour l'instance C** | **1,5** : 1,0 alloue + 0,5 de rejeu | |

Tarifs *proposes* ; elle les confirme ou les change, et elle dit si arts plastiques CP est un terrain neuf (`PAR-13`, A.13).

### A.12 Le plafond d'une ligne, a obtenir avant l'audit

`S54-ART-CP-10-PRIX` porte deja le plafond d'un paquet `DEGRADE` pour `ART-CP-10` (A.3, point 2 ; backlog). **Je demande a `nadia-prof` que cette ligne soit ecrite avant que l'instance C
ouvre l'audit** : elle dit ce que l'audit peut juger, et sans elle l'instance C juge contre une borne qu'elle ecrit en meme temps. Je le demande, je ne l'ordonne pas : le moment ou
elle ecrit est le sien. Le backlog en fait un critere de `S54-BR-ART-CP-10` (la story n'est pas "livree" sans la ligne), pas un ordre donne a `nadia-prof`.

### A.13 `PAR-9` : ce que le texte dit, et ce que je ne tranche pas

**Le message dit que `PAR-9` est applique et non amende. Ce n'est pas ce que je peux ecrire apres lecture des textes.** Ce qui suit est de forme 1 ; la conclusion est a l'utilisatrice et a `nadia-prof`.

1. **`PAR-9` (s30, section 1.3)** : *"le budget opposable est le budget par instance, et la somme des budgets par instance est egale a la borne du sprint"* ; la borne est 8,0 (`PAR-8`).
   La clause de s52 (section 4.3), citee en section 5, ne parle que de *"deux instances"*. A et B restent a 4,0 chacune : **A et B, pris seuls, ne depassent rien.**
2. **Avec C, la somme comptee passe a 9,5** (4,0 + 4,0 + 1,5), contre 8,0. Ce que `PAR-9` ecrit pour ce cas, c'est *"ou elle ecrit l'ecart et son motif"* ; la borne totale, elle, est a
   `nadia-prof` (`PAR-8`). **Je n'ecris pas l'ecart a sa place** : je n'ai pas qualite pour dire que 9,5 est admissible, et le *"sans ecart"* de la section 5 ne vaut plus que pour A et B.
3. **Les conditions d'une borne a trois instances** (`ARBITRAGE-PREALABLE-s46...` section 5, reprises par s51 section 5 : *"une formule n'est pas une borne sans les conditions qui
   l'autorisent"*) : (i) vivier instruit superieur a la borne a deux instances, (ii) dernier sprint parallele clos sans occurrence des formes (a), (b) ou (c), (iii) `PAR-13`. Ces conditions
   visent une instance de pleine capacite ; **C est une instance de 1,5**. Je ne les declare ni remplies ni inapplicables. La condition (ii) se lit sur s53 et elle est a `nadia-prof`.
4. **`DEC-0112` section 5.1** a refuse une instance de sciences CM1 parce qu'aucune ne pouvait atteindre *"les 4,0 qu'elle a exiges d'une instance (condition (i))"*, et a pose la question
   a l'utilisatrice : *"accepte-t-elle de ce fait qu'un sprint porte une instance sous la barre de `nadia-prof` ?"* **C est sous cette barre (1,5 contre 4,0).** La meme question se pose ici ;
   le message recu ne dit pas si elle a ete posee sous cette forme.
5. **`PAR-13`** : au plus une instance par sprint prend un terrain neuf. Le francais CE1 est le terrain neuf de A (`PAR-7`). Si arts plastiques CP en est un aussi pour `nadia-prof`, C et A
   ne tiennent pas `PAR-13` ensemble.

**Ce que ca donne, sans que je tranche.** Soit l'utilisatrice et `nadia-prof` ecrivent l'ecart (borne de s54 portee a 9,5, motif possible : sortir `ART-CP-10` du silence, A.10 motif 2), soit
elles amendent `PAR-9`, la condition (i) ou `PAR-13` pour ce cas. **L'etat de `PAR-9` n'est donc pas "applique" : il est "ecart ou amendement a ecrire".** Si l'utilisatrice maintient que `PAR-9`
est applique tel quel, c'est a elle de dire sur quel texte, et je le consigne.

### A.14 Isolation (a faire dire par `sofia-sm`)

La derniere occurrence de la forme (c) est celle de s50 : deux instances ont ecrit le meme fichier dans le meme scratchpad, sans le savoir (`ARBITRAGE-PREALABLE-s51-borne-a-trois-instances.md`,
section 2.3). **Je demande trois isolations ; leurs noms et leur mecanique sont a `sofia-sm`** : (1) un worktree dedie a l'instance C ; (2) une branche dediee ; (3) un scratchpad dedie, distinct de
ceux de A et de B. Ce que C ecrit se limite a sa section de `FICHE-cp-arts-oeuvres-familles.md` et a son arbitrage prealable ; aucun fichier de A ou de B.
*[Annote le 2026-10-08 (A.26) : le nom reel de la fiche est `FICHE-bandit-manchot-familles-d-oeuvres-cp.md` ; `FICHE-cp-arts-oeuvres-familles.md` n'a jamais existe. Voir A.25 point 5.]*
**Ordre de fusion** : a `sofia-sm`. Une seule contrainte de produit : le branchement `S54-BR-ART-CP-10` et la ligne de plafond de A.12 sont fusionnes avant que C ouvre l'audit (rien a auditer avant).
Les fichiers partages avec A et B (`DEC-index.md`, backlog, journal de tokens) sont a elle.

### A.15 Affectation du branchement

`S54-BR-ART-CP-10` : **`lea-dev`** (proposition, auteure du paquet ; affectation a `sofia-sm`). Elle s'ajoute aux trois branchements de B qu'elle porte deja (`SCI-CM1-15`, `-18`, `EMC-CM1-02`) :
l'arbitrage de la charge est a `sofia-sm` (A.5 : environ 590 lignes en six fichiers).

### A.16 Statut, routage, ce que cet amendement ne fait pas

`Proposee`, inchange. Un motif de forme 1 (A.10) et un message de forme 3 (A.9) ; l'ouverture reste conditionnee a A.13.

| A qui | Quoi |
|---|---|
| utilisatrice, via `sofia-sm` | A.13 : ecart a `PAR-9`/`PAR-8`, ou amendement de `PAR-9`, de la condition (i) ou de `PAR-13` ; elle dit sur quel texte repose "applique" |
| `nadia-prof` (instance C, puis A) | A.11 tarif ; A.12 plafond avant l'audit ; A.13 conditions (i) a (iii), `PAR-13` |
| `sofia-sm` | A.14 isolation et ordre de fusion ; A.15 affectation ; fusion de cet amendement avant le worktree de `S54-BR-ART-CP-10` (`C1`) |
| `lea-dev` | `S54-BR-ART-CP-10`, sur affectation |
| `yanis-qa` | Lecture de testabilite des criteres ajoutes a la story |

Il ne touche ni `catalogue.yaml`, ni un paquet, ni une fiche, ni `MATRICE-couverture.md` (inchangee, 132 / 182 ; `ART-CP-10` reste `non couvert`). Il ne prononce aucun Done, ne fixe aucun
tarif ni plafond et n'ecrit ni l'ecart ni l'amendement de `PAR-9`.

---

## Amendement du 2026-10-08 (s54, `camille-po`), seconde suite : `Q12`, la borne de `PAR-9` pour s54 est ecrite a 9,5

Meme regime : passages amendes marques, pas effaces ; arrive apres l'ouverture de s54. **Cet amendement leve la condition de A.13 sur le point de l'ecart** ; il ne la leve pas sur les conditions de `nadia-prof` (A.19).

### A.17 Ce que j'ai recu, cite tel que recu et fige a la reception (`DEC-0062` regle 3.5)

Un message de l'agent qui m'a lance, le 2026-10-08, **sans source dans le depot**. Il dit que l'utilisatrice a repondu a `Q12` : elle *"accepte l'ecart"*, demande *"d'ECRIRE LA NOUVELLE BORNE de PAR-9 pour s54"*
(somme des budgets par instance de 9,5 : A 4,0, B 4,0, C 1,5 pour l'audit de `cp-arts-oeuvres-familles`) et *"autorise explicitement l'ouverture de l'instance C et cet ecart"*. Je n'ai pas ses mots ; les formules entre
guillemets sont celles du message de l'agent. **Forme 3 de `DEC-0062` (section 3.2) : elle fonde le fait que l'ecart s'ecrit, jamais sa raison.** Le motif est de forme 1, en A.18.

### A.18 Decision : borne de `PAR-9` pour s54 (amende : A.10, A.13, A.16 sur le point de l'ecart)

Format de `PAR-9` pour un ecart : *l'ecart et son motif*. Les voici.

| | Valeur |
|---|---|
| Borne de `PAR-9` pour s54 : somme des budgets par instance | **9,5** (contre 8,0, `PAR-8`) |
| Ecart a la borne de 8,0 | **+1,5** |
| Instance A (francais CE1) | 4,0 (3,5 alloues + 0,5 de rejeu), inchangee |
| Instance B (sciences et EMC CM1) | 4,0 (3,5 alloues + 0,5 de rejeu), inchangee |
| Instance C (audit de `cp-arts-oeuvres-familles` seul, A.10) | 1,5 (1,0 alloue + 0,5 de rejeu), tarif propose, a confirmer (A.11) |
| Plafond d'une instance (`PAR-8`) | 5,0, jamais franchi : A, B et C sont chacune en dessous |

**Motifs, de forme 1.**

1. **Q6 de l'utilisatrice** : brancher le paquet `DEGRADE` de `ART-CP-10` sans fermer la ligne (A.1). Un paquet branche sans verdict est la situation que `DEC-0009` a coutee a s04 ; l'audit est donc
   le cout du branchement et non une option.
2. **Q11** : A.5 a chiffre les issues. Sur A ou sur B, l'audit les portait a 4,5, au-dessus de `PAR-9` et sans marge de rejeu non allouee, ou a quatre lots sur B, au-dessus de `PAR-8`. L'instance C est la seule issue qui ne touche
   ni un lot de A ni un lot de B (A.10, motif 1).
3. **Le report se compte** : `ART-CP-10`, quatre sprints hors perimetre (s50 a s53, section 4). Un cinquieme sprint sans verdict sur un paquet branche aurait ete un report sans decision.

**Ce que l'ecart ne change pas : `PAR-9` n'est pas amendee.** Son texte reste celui de s30 ; ce paragraphe est l'ecart qu'elle prevoit, et non une nouvelle regle.

### A.19 Ce que cette autorisation couvre, et ce qu'elle ne couvre pas

**Elle couvre** : (1) l'ouverture de l'instance C pour un paquet ; (2) la somme de 9,5 pour s54, et elle seule.

**Elle ne couvre pas, et ces points restent a `nadia-prof`, qui les lit et les ecrit ; je ne les tranche pas a sa place** :

1. **`PAR-8`** : le plafond de 5,0 par instance n'est ni leve ni touche. La borne totale de 8,0 est depassee par cet ecart ; que 9,5 soit une borne qu'elle tient pour opposable est de son ressort, pas du mien.
2. **`PAR-13`** : arts plastiques CP est-il un terrain neuf pour elle ? S'il l'est, C et A (francais CE1, terrain neuf de A, `PAR-7`) ne tiennent pas `PAR-13` ensemble. L'ecart ne repond pas a cette question.
3. **Les conditions (i) a (iii)** d'une borne a trois instances (A.13, point 3) : vivier instruit, dernier sprint parallele clos sans occurrence des formes (a), (b) ou (c) (se lit sur s53), `PAR-13`. Je ne les declare ni remplies ni inapplicables a une instance de 1,5.
4. **La question de la barre de 4,0** (A.13, point 4, `DEC-0112` section 5.1) : si C, a 1,5, est sous la barre qu'elle a exigee d'une instance, le message ne dit pas si la question a ete posee sous cette forme. Elle reste ouverte tant que `nadia-prof` ne l'ecrit pas.
5. **Le tarif** (A.11), **le plafond d'une ligne** pour `ART-CP-10` (A.12, avant l'audit), le verdict et le bloc de validation de la fiche.

Si `nadia-prof` refuse l'un de ces points, l'instance C ne s'ouvre pas, ou s'ouvre sur ses mots, et cet amendement est amende ; **s54 n'est pas touche au-dela de C**.

### A.20 Duree : s54 seulement, aucune jurisprudence pour s55

La borne de 9,5 vaut pour s54 et pour aucun autre sprint. **La `DEC` d'ouverture de s55 compte ses instances selon `PAR-9` telle qu'ecrite (somme egale a la borne de 8,0), sans s'appuyer sur ce precedent.** Une troisieme instance a
un sprint ulterieur demande son propre ecart, son propre motif de forme 1 et sa propre autorisation. Je ne ferai pas de 9,5 un chiffre de depart.

### A.21 Statut : `Proposee`, inchange

La regle du depot (section 0.4) fait passer `DEC-0114` a `Acceptee` quand (a) les mots de l'utilisatrice sur l'ordre des matieres au-dela de s54 sont ecrits dans le depot en forme 2 avec leur date, ou (b) la `DEC` d'ouverture de s55
reprend cet ordre sous son propre motif. **Ni (a) ni (b) n'est vrai** : l'ordre (section 7, A.3) n'a toujours que la forme 3 pour source, et `Q12` arrive par le meme canal (message d'agent, sans ses mots). Je ne promeus pas
la section 7 en motif en passant la `DEC` a `Acceptee` : meme geste que section 0.4. L'ecart de A.18 a un motif de forme 1 et s'applique des maintenant ; il n'a pas besoin du statut pour valoir.

### A.22 Routage

| A qui | Quoi |
|---|---|
| utilisatrice, via `sofia-sm` | Redire ses mots sur `Q12` et sur l'ordre (forme 2) : c'est ce qui passe la `DEC` a `Acceptee` |
| `nadia-prof` | A.19, points 1 a 5, avant d'ouvrir l'instance C |
| `sofia-sm` | Isolation et ordre de fusion (A.14) ; fusion de cet amendement avant le worktree de `S54-BR-ART-CP-10` (`C1`) |
| `camille-po` | Backlog et `DEC-index.md` (ce commit) |

Il ne touche ni `catalogue.yaml`, ni un paquet, ni une fiche, ni `MATRICE-couverture.md` (inchangee, 132 / 182). Il ne prononce aucun Done et ne fixe ni tarif ni plafond.

---

## Amendement du 2026-10-08 (s54, `camille-po`, apres les verdicts de `nadia-prof`) : cinq lignes ferment

Motif de forme 1 : les verdicts sont ecrits dans les fiches de conformite (`FICHE-bandit-manchot-futur-ce1.md`, `-passe-compose-ce1.md`, `-jour-nuit-saisons-cm1.md`,
`-environnement-cm1.md`, `-atteinte-autrui-cm1.md`), sur `95214218`. Cet amendement ne decide rien de neuf : il consigne ce que les verdicts changent et ce qui en decoule.

### A.23 Ce que les verdicts changent

1. **L'en-tete de cette `DEC` dit *"aucune ligne de matrice ne change de statut par cette decision"*** : vrai a l'ouverture, car elle n'ouvrait que des audits. Ce n'est plus l'etat
   du depot, par les verdicts et non par cette `DEC`. Cinq lignes passent `non couvert` -> `couvert-valide` : `FRA-CE1-18` (VALIDE), `FRA-CE1-19` (VALIDE SOUS RESERVE,
   `PCO-CE1-R1`), `SCI-CM1-15` (VALIDE), `SCI-CM1-18` (VALIDE), `EMC-CM1-02` (VALIDE, avec la mention de couverture exigee d'avance). **Commande de la "Methode du compte" de la matrice,
   lancee le 2026-10-08 : `couvert-valide` 137, `en cours` 4, `non couvert` 41, `non couvrable en l'etat` 76, total 258, denominateur 182, 137 / 182 = 75,27 %** ; avant : 132, 4, 46, 76,
   132 / 182 = 72,53 %. Perimetre : les 258 lignes, aucune mesure partielle.
2. **Reserves levees, aucun statut change** : `CRP-CM1-R1` a `R3` (`SCI-CM1-10`) et `SYC-CE1-R1` (`FRA-CE1-25`). Le journal de tokens (`S51-OUTIL-JOURNAL-TOKENS`) n'est pas traite ici.
3. **`ART-CP-10` reste `non couvert`** (section A.1, appliquee) : le paquet `DEGRADE` est branche, son audit est en cours, aucun verdict. La matrice porte la regle citee. Un ecart de la
   matrice, signale par `nadia-prof`, est corrige : le bilan d'arts plastiques CP rangeait encore la ligne en `non couvrable en l'etat`, classement retire le 2026-10-07.
4. **Postes engages par cette `DEC` et livres a s54** : `S51-SCI-CORPS-CM1-RESERVES`, `S53-PASSE-TRANSVERSE` (`PAR-4`), `SYC-CE1-R1` et item 16, `S53-REPORT-GRANDS-NOMBRES` (`Q5` : hors porte de
   `nadia-prof`, relecture de ponctuation au `relecteur-pedagogique`). Un engagement non livre aurait compte comme un report (clause de `DEC-0111` A.14, section 4 de cette `DEC`) : ces quatre-la sont livres, aucun n'en est un.
5. **Stories ouvertes par les verdicts, aucune engagee** (backlog, "Sprint 54") : `PCO-CE1-R1` (0,25, mot pour mot) ; `S54-EXC-CATEGORIE` (constat de `nadia-prof` B sur `cp-emc-exclusion`,
   criteres non ecrits : ce sont les siens) ; `S54-OBS-SCI-CONSIGNE` et `S54-OBS-ART-ALT` (observations de la passe transverse). Premier report de chacune : aucun.

### A.24 Statut et routage

Statut : `Proposee`, inchange (A.21) ; la section 0.4 n'est pas touchee.

| A qui | Quoi |
|---|---|
| `sofia-sm` | Affecter `PCO-CE1-R1` (proprietaire propose : `marc-dev`) ; porter a `nadia-prof` la question de `S54-EXC-CATEGORIE` ; verifier que la relecture de ponctuation de `S53-REPORT-GRANDS-NOMBRES` est tracee |
| `nadia-prof` | Verdict sur `cp-arts-oeuvres-familles` ; reponse a `S54-EXC-CATEGORIE` ; la fiche de ce paquet |
| `camille-po` | La matrice ne bouge pour `ART-CP-10` que sur le plafond ecrit ou le verdict |

Il ne touche ni `catalogue.yaml`, ni un paquet, ni du code. Il ne prononce aucun Done.

---

## Amendement du 2026-10-08 (s54, `camille-po`, apres le verdict de l'instance C) : `cp-arts-oeuvres-familles` valide sous reserve, `ART-CP-10` inchangee

Motif de forme 1 : le verdict est ecrit dans `FICHE-bandit-manchot-familles-d-oeuvres-cp.md` (bloc de validation, `nadia-prof` instance C, 2026-10-08). Cet amendement ne decide rien de neuf.

### A.25 Verdict de C et consommation reelle des budgets

1. **Verdict** : `cp-arts-oeuvres-familles` **VALIDE SOUS RESERVE**, trois reserves non bloquantes (`R1` consigne de catalogue, `R2` indice de l'item 11, `R3` metiers au masculin). **Mouvement de ligne : aucun.**
   `ART-CP-10` reste `non couvert` : la fiche dit que le paquet *"ne travaille pas cette competence"* et que le verdict *"ne vaut pour aucune ligne de la matrice"* (section 3 et bloc de validation).
   A.1 est appliquee, pas modifiee. La matrice porte la mention du paquet branche et valide sous reserve (A.24 : elle ne bouge que sur le plafond ecrit ou le verdict) ; **les totaux ne changent pas : 137 / 182**.
2. **Reserves en backlog, non engagees, premier report de chacune : aucun** : `S54-ART-RES-R1`, `S54-ART-RES-R2`, `S54-ART-RES-R3` (criteres repris des mots de la fiche, mesures rouges sur l'existant le 2026-10-08). Engagement : `DEC` d'ouverture de s55.
3. **Consommation reelle des budgets de s54** (A.18 en posait la borne) : instance A **3,25**, instance B **3,25**, instance C **1,0** (fiche : audit 1,0, rejeu 0,5 non consomme). **Somme : 7,5**, contre la borne de **9,5** ecrite en A.18.
   Les chiffres de A et de B sont ceux que le message de `sofia-sm` m'a donnes ; je n'ai pas relu leurs rapports pour les recompter (forme 3 de `DEC-0062` : ils fondent le fait qu'ils ont ete annonces, pas leur exactitude).
4. **L'ecart de A.18 n'a pas ete consomme.** A.18 ecrivait un ecart de +1,5 a la borne de 8,0 de `PAR-8`. Avec 7,5 la somme reelle est **sous 8,0** : elle n'a pas eu besoin de l'ecart, meme en partie.
   Ce qui avait ete compte (9,5) etait un budget, pas une consommation. **A.20 vaut toujours** : cet ecart n'est pas une jurisprudence pour s55, et la `DEC` d'ouverture de s55 compte selon `PAR-9` telle qu'ecrite.
5. **Correction de reference** : la story `S54-BR-ART-CP-10` (critere 7, et A.14 de cet amendement-ci par renvoi) citait `FICHE-cp-arts-oeuvres-familles.md` ; la fiche porte le nom de la convention du depot,
   `FICHE-bandit-manchot-familles-d-oeuvres-cp.md`. Corrige dans le backlog ; le texte de A.14 n'est pas reecrit, cette phrase le corrige.

Statut : `Proposee`, inchange (A.21). Routage : `sofia-sm` affecte `S54-ART-RES-R1` a `-R3` (proprietaire propose : `lea-dev`, auteure du paquet) et propose leur prix ; `nadia-prof` rejoue.
Il ne touche ni `catalogue.yaml`, ni un paquet, ni du code. Il ne prononce aucun Done.

### A.26 Annotation de A.25 point 3 : le tarif reel de l'instance A est 3,75, la somme est 8,0

Motif de forme 1 : `ARBITRAGE-PREALABLE-s54-instance-a-francais-ce1.md` ecrit un total reel de A de **3,75 / 4,0** (tableau des complements de cloture : rejeu de `PCO-CE1-R1` 0,25 et
`S54-EXC-CATEGORIE` 0,25, sur les 3,25 d'avant complements) et dit : *"L'ecart avec A.25 de `DEC-0114` (3,25) est a `camille-po`"*. A.25 point 3 portait 3,25 pour A : c'etait le tarif d'avant les complements de cloture.
Le texte de A.25 n'est pas reecrit ; cette annotation le corrige.

1. **Somme comptee** : A **3,75** + B **3,25** + C **1,0** = **8,0** (arithmetique relue ; B est le chiffre de A.25, repris de `sofia-sm` et non recompte ici, forme 3 de `DEC-0062`). Contre la borne de **9,5** de A.18 : 1,5 de marge.
2. **Ecart de `PAR-9`** : A.18 accordait un ecart de +1,5 a la borne de 8,0 de `PAR-8`. **Il n'a pas ete consomme au-dela de `PAR-8`** : 8,0 est la borne de `PAR-8`, pas un depassement. Le poste "ecart de `PAR-9`"
   est donc **non consomme** (0,0 sur 1,5), et non "sous 8,0" comme A.25 point 4 l'ecrivait : la somme **atteint** 8,0, elle ne reste pas en dessous. Sans l'ecart accorde, la somme tenait deja a la borne exacte, sans marge.
3. **Ce qui ne change pas** : A.20 (l'ecart n'est pas une jurisprudence pour s55, la `DEC` d'ouverture de s55 compte selon `PAR-9` telle qu'ecrite) vaut toujours.
   Aucun verdict, aucune ligne de matrice, aucun statut de `DEC` ne bouge ; la matrice reste a 137 / 182.

Statut : `Proposee`, inchange. Pas de changement de code ; aucun routage.

---

## Amendement du 2026-10-08 (s55, `camille-po`) : `DEC-0114` passe a `Acceptee`, par la section 0.4 (b), sur le motif de forme 1 de `DEC-0115` section 3

Meme regime : passages amendes marques, pas effaces. Aucun fait de s54 n'est touche. Motif de forme 1 : `DEC-0115` section 3 (un motif mesure ou cite d'un auteur par rang de l'ordre).

### A.27 Statut : `Proposee` devient `Acceptee`

1. **Ce que j'ai recu** (`DEC-0115` section 0.1, cite tel que recu, forme 3) : a la question Q1 du planning de s55, l'utilisatrice repond `"ok"`, selon l'agent qui m'a lance, qui precise qu'elle valide l'ordre `G1`/`G2`, puis `G6`/`G5`, puis arbitrage de `nadia-prof` sur `G7` a `G11`, puis `G3`/`G4`. Je n'ai pas le message d'origine.
2. **Lecture de la section 0.4.** (a) *"ses mots sont ecrits dans le depot en forme 2 (avec la date)"* : **non satisfaite** ; `ok` est relaye, pas cite de sa main. (b) *"la `DEC` d'ouverture de s55 reprend l'ordre sous son propre motif de forme 1"* : **satisfaite**, parce que `DEC-0115` section 3 donne, pour chacun des quatre rangs, un motif que j'ai mesure ou que je cite d'un auteur du depot. **`ok` n'est pas ce motif** : il fonde que l'ordre est suivi (forme 3, `DEC-0062` section 3.2), pas pourquoi il est defendable.
3. **La limite que je nomme.** Le rang 4 (`G3`/`G4`) repose sur le planning de s54 et `proposition-s55.md`, que je n'ai pas remesures ; il est cite, pas mesure. Le placement de `G3` avec `G4`, que la section 7.1 laissait a la decision de s55, est tranche par ce motif seul. Si `nadia-prof` lit autrement le plafond de `G3`, ce rang est amende, pas la decision.
4. **Si ses mots divergent un jour** de la section 7 (la section 0.4 le prevoit), la section 7 est amendee ; ce qui est livre a s54 et a s55 ne l'est pas.
5. **Ce qui ne change pas** : A.20 (la borne de 9,5 de s54 n'est pas une jurisprudence ; `DEC-0115` compte selon `PAR-9` telle qu'ecrite, 8,0) ; A.21 est amende sur son seul statut, ses constats d'alors restent ecrits ; aucune ligne de matrice ne bouge (137 / 182) ; aucun verdict n'est prononce.

Statut : `Acceptee`. Routage : `DEC-index.md` (ce commit). Pas de changement de code ; aucun autre routage.
