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
- **Statut** : **Proposee**. Une seule decision de cette `DEC` n'a que la forme 3 de `DEC-0062` pour source :
  l'ordre des matieres au-dela de s54 (section 7). Toutes les autres portent un motif de forme 1 ou 2, s'appliquent
  des maintenant et sont routees en section 9. Passage a `Acceptee` : voir section 0.4.
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

**Source de l'ordre** : *"sur art-cp go pour le 1"*, recu de MASTER (section 0.1, point 4). **Ce qui fonde le contenu de l'option, lui, est de forme 1** :
le planning (section "Les trois lignes" : *"Ce que s54 engage : le prix de la mecanique (`theo-archi`) et le plafond du paquet (`nadia-prof`, une
ligne). Ce que s54 n'engage pas : la construction"*) ; `DEC-0113` 3.1 (la ligne n'est pas impossible : `nadia-prof` a repondu a `C5` clause 1
le 2026-10-06) ; le paquet `cp-arts-oeuvres-familles`, qui dit dans son en-tete *"Ce paquet ne ferme pas la ligne"* (planning).

**Decision.**

1. **`theo-archi`** ecrit le **prix de la mecanique qui montre deux oeuvres ensemble**, en tailles (le depot n'a pas de tarif de developpement,
   `DEC-0112` section 7), et le **statut d'`ADR-0104`** (`Propose`). C'est son geste, pas le mien : question `T6` du planning.
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
7. **Deux instances de `nadia-prof` ouvertes, comptees a 4,0 chacune** (3,5 alloues + 0,5 non alloue), sans ecart a `PAR-9` ; tarifs, barre de B et
   `PAR-13` restent a `nadia-prof`.
8. **`ART-CP-10` : prix et plafond seulement, rien n'est construit** ; `Q6` tranchee par l'arbitrage de MASTER (forme 3, option 1) ; la decision de
   financer se prend a s55 apres lecture des deux prix ; s54 n'est pas un cinquieme report.
9. **Ordre des matieres consigne** (forme 3) : francais CE1, sciences et EMC CM1, arbitrage de `nadia-prof` sur G7 a G11, mathematiques CE1 ;
   decoupage du francais CE1 en deux groupes X et Y propose, non engage.
