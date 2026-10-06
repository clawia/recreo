# DEC-0111 - Ouverture de s52 : deux instances de `nadia-prof` pour le CE1 (A francais, C mathematiques),
# retrait du catalogue des deux paquets de sciences CM1 refuses, instance C sous condition suspensive ecrite

- **Type** : decision d'ouverture de sprint (objectif, perimetre, chiffrage, instances, routage).
- **Date** : 2026-10-06
- **Auteur** : `camille-po`
- **Sprint** : s52
- **Competences programme visees** : `FRA-CE1-17`, `FRA-CE1-25`, `FRA-CE1-26`
  (`squad/programme/francais/competences-ce1.md`) ; `MAT-CE1-17`, `MAT-CE1-06`, `MAT-CE1-02` moitie
  textuelle (`squad/programme/mathematiques/competences-ce1.md`) ; corrections differees sur `FRA-CE1-20`
  et `FRA-CE1-23` (lignes deja `couvert-valide`, non rouvertes). Le retrait est transverse - pas de contenu
  pedagogique.
- **Statut** : Acceptee
- **Lien** : `DEC-0110` (sections 14.5 a 14.7, 16.3, 16.9, 16.10 ; non editee ici, citee) ; `DEC-0109` ;
  `DEC-0108` ; `DEC-0107` (section 4) ; `DEC-0098` (section 3) ; `DEC-0009` (precedent de retrait) ;
  `squad/conformite/ARBITRAGE-PREALABLE-s52-instances-ce1.md` (verdict, section 7) ;
  `squad/conformite/ARBITRAGE-PREALABLE-s51-borne-a-trois-instances.md` (sections 6.1, 6.3, 6.4) ;
  `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` (sections 1.3, 2, 5.8, 6.3, 6.4, 7, 9) ;
  `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md` (sections 3 et 6) et `...-lot-3.md`
  (section 3) ; `squad/conformite/ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md`
  (`PAR-1`, `PAR-2`, `PAR-4`, `PAR-8`, `PAR-10`) ;
  `squad/conformite/ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-quinze-reserves.md`
  (`PAR-9`) ; `squad/sprints/s51-trois-instances-francais-sciences-maths/avis-faisabilite.md` (sections 5 a 7) ;
  `squad/README.md` (section 2 bis ; section 4, `PAR-16` ; section 9).

---

## 0. Provenance et methode

**Instruction recue, citee mot pour mot** (forme 3, `squad/README.md` section 2 bis) : *recue de MASTER,
sans source dans le depot*, le 2026-10-06 : *"créée plusieurs instances de nadia pour couvrir le CE1"*.
**Cet enonce ne fonde rien** (premiere borne de la forme 3) : ni le nombre d'instances, ni une ligne, ni un
tarif. Le nombre vient de `nadia-prof` (section 6) ; cette decision ecrit chaque motif en entier.

**Decisions de l'utilisatrice du 2026-10-06** (forme 2, `de l'utilisatrice`, ses mots cites, la date).

1. Question : *"Les deux paquets de sciences refusés (activite-humaine-cm1 et terre-cm1) sont encore
   servis. Que décide la DEC d'ouverture de s52 ?"* Reponse : *"Retirer du catalogue (Recommandé)"*.
2. Question : *"Lecture du plafond de capacité pour chiffrer les deux instances CE1 de s52 ?"* Reponse :
   *"Pratique depuis s41 (Recommandé)"*.

Ces deux reponses fondent la section 3 et la lecture de la section 2 ; elles ne fondent pas le reste.

**Methode des comptes.** Arbre `round-two` a `4aad468b`. Statuts lus par la commande ecrite en tete de
`squad/programme/MATRICE-couverture.md` (note du 2026-10-06), lancee aujourd'hui : `couvert-valide` 127,
`en cours` 3, `non couvert` 52, `non couvrable en l'etat` 76, total 258, denominateur 182 ; 127 / 182 =
69,8 %. `catalogue.yaml` : `grep -n "activite-humaine-cm1\|terre-cm1"` rend deux entrees (lignes 777 et 789).

**Ce que je n'ai pas lu pour cette decision** : les transcriptions de sous-agents de s51 (que
`nadia-prof` cite, hors depot), la table 13.2 de l'arbitrage de s29 (portee des proprietes), le texte de
`ADR-0080` au-dela de la liste de ses amendements. Aucun avis de faisabilite de `theo-archi` n'existe pour
les trois lignes de francais choisies ci-dessous.

---

## 1. Objectif gele

**Porter a `couvert-valide` jusqu'a cinq lignes** - `FRA-CE1-17`, `FRA-CE1-25`, `FRA-CE1-26`,
`MAT-CE1-17`, `MAT-CE1-06` - **faire passer `MAT-CE1-02` a `en cours`** (moitie textuelle), **lever
`TPS-CE1-R1` et `ALP-CE1-R1`**, et **retirer du catalogue servi les deux paquets de sciences CM1 refuses**,
sans qu'aucune instance de `nadia-prof` depasse 5,0.

Mesure : 127 / 182 au depart, **132 / 182 au mieux (72,5 %)**. `MAT-CE1-02` ne compte pas au numerateur.
Ce n'est pas une promesse : un Done pedagogique refuse reste un refus. Je ne chiffre pas de plancher.

---

## 2. Perimetre chiffre

**Lecture du plafond (decision de l'utilisatrice, section 0, point 2).** *Pratique depuis s41* : 8,0 de
lots engages (`DEC-0095`, `DEC-0098`, `DEC-0110`), un rejeu de 0,5 nomme par instance, plafond individuel
5,0 (`PAR-8`). **`PAR-11` amendee n'est pas appliquee a s52** : `nadia-prof` la chiffre dans son arbitrage
(section 1.1 et tableaux 4.1, 4.2) et me route le choix ; cette reponse le tranche. Le risque que ce choix
accepte, ecrit pour qu'on ne le decouvre pas : `PAR-11` demande 0,5 de marge par lot neuf, soit 1,5 pour A
et 1,5 pour C (0,5 de plus pour C, terrain neuf). A et C portent 0,5. Deux refus au premier passage sur une
meme instance n'y laissent qu'un rejeu, ce qui est arrive a l'instance B de s51 (`SCI-CM1-15` sans rejeu,
`DEC-0110` 15.2). Ce risque pese plus sur C que sur A : A n'a aucun terrain neuf (section 4).

| Instance | Poste | Tarif | Cumul |
|---|---|---|---|
| A (francais CE1) | `S52-FRA-PRESENT` (`FRA-CE1-17`), un lot | 1,0 | 1,0 |
| A | `S52-FRA-SYNONYMES-CONTRAIRES` (`FRA-CE1-25`), un lot | 1,0 | 2,0 |
| A | `S52-FRA-CATEGORIES` (`FRA-CE1-26`), un lot | 1,0 | 3,0 |
| A | `TPS-CE1-R1` (`FRA-CE1-20`), correction, pas un lot (`DEC-0107` section 4) | 0,5 | 3,5 |
| A | `ALP-CE1-R1` (`FRA-CE1-23`), correction | 0,5 | **4,0** |
| A | rejeu nomme (`PAR-10`) | 0,5 | **4,5 / 5,0** |
| C (mathematiques CE1) | `S51-MAT-SYMETRIE` (`MAT-CE1-17`) : 1,0 + 0,5 lot illustre (`REC-1`) | 1,5 | 1,5 |
| C | `S51-MAT-MULTIPLICATION` (`MAT-CE1-06`) : 1,0 + 0,5 lot illustre (`REC-1`) | 1,5 | 3,0 |
| C | `S51-MAT-COMPARER-RANGER` (`MAT-CE1-02`, moitie textuelle), ne ferme pas | 1,0 | **4,0** |
| C | rejeu nomme | 0,5 | **4,5 / 5,0** |
| Retrait | `S52-RETRAIT-SCIENCES-CM1` | **non chiffre ici** (section 3) | hors capacite de `nadia-prof` |

**Total du sprint : 8,0 engages**, la borne a deux instances que `DEC-0098` section 3 a verifiee et que s51
a tenue. Le tarif de 1,5 de `S51-MAT-SYMETRIE` et `S51-MAT-MULTIPLICATION` est celui de `nadia-prof`
(arbitrage de s52, section 1.2, application de son tarif de s28 section 4.3) ; le backlog est mis a jour
(stories `S51-MAT-*` et table de la section "Sprint 51").

`PAR-1` (aucun lot partage), `PAR-2` (un couple matiere/niveau par instance : francais CE1 a A,
mathematiques CE1 a C) et `PAR-8` (trois lots au plus par instance) : verifies ligne par ligne ci-dessus.
**Passe transverse (`PAR-4`)** : section 7.

---

## 3. Les deux paquets de sciences refuses : retrait du catalogue servi

**Ce que cette section tranche en premier, comme `DEC-0110` 16.10 l'exige** : *"avant tout autre poste
touchant `bandit-manchot-activite-humaine-cm1` ou `bandit-manchot-terre-cm1`"*. Aucun autre poste de s52 ne
les touche.

**Decision : les deux entrees sont retirees de `adapters/adapter-content/src/main/resources/catalogue.yaml`**
(`bandit-manchot-activite-humaine-cm1`, paquet `cm1-sciences-activite-humaine` ; `bandit-manchot-terre-cm1`,
paquet `cm1-sciences-terre`). Sur la decision de l'utilisatrice (section 0, point 1) et sur la position de
`nadia-prof` (arbitrage de s52, section 6 : *"un paquet refuse pour une cle de reponse fausse dit a l'enfant
"Ce n'est pas ça." sur une reponse vraie ; je l'ai fait a l'ecran. Sans reprise engagee a s52, je demande son
retrait du catalogue servi"*).

- **Pourquoi pas la reprise** : aucune instance de sciences n'est ouvrable a s52. `PAR-2` interdit une
  troisieme instance sur le CE1 sans amender la regle (arbitrage de s52, section 5, refuse), et la condition
  (i) de la borne a trois n'est pas remplie : les trois reprises de sciences pesent 2,0 contre les 4,0
  requis (section 6 de ce meme arbitrage). Remplacer un poste de A ou de C par une reprise de sciences
  romprait `PAR-2` pour l'instance concernee.
- **Ce qui est conserve** : les fichiers `contenu/cm1-sciences-activite-humaine.yaml` et
  `contenu/cm1-sciences-terre.yaml`, leurs tests de paquet (`PaquetCm1SciencesActiviteHumaineTest`,
  `PaquetCm1SciencesTerreTest`), leurs fiches de conformite et les stories de reprise
  (`S51-SCI-ACTIVITE-HUMAINE-CM1-REPRISE`, `S51-SCI-TERRE-CM1-REPRISE`). Dans `ADR-0061`, *l'absence d'entree
  reste le seul retrait* : un paquet qui revient au catalogue y revient par une entree neuve, apres un Done
  pedagogique de `nadia-prof`, comme `DEC-0009` l'a fait pour "Le bon son".
- **Statut de la matrice** : `SCI-CM1-15` et `SCI-CM1-18` restent `non couvert`. Le statut suit le verdict
  pedagogique, pas la presence du jeu au catalogue (meme regle que `DEC-0009`, section "Ce que cette
  decision ne fait pas").
- **Execution** : story `S52-RETRAIT-SCIENCES-CM1` (`docs/backlog/backlog.md`, section "Sprint 52"), affectee
  par `sofia-sm`, **premiere a fusionner de s52** : tant qu'elle n'est pas fusionnee, un enfant peut encore
  jouer ces deux paquets dans l'etat refuse (`DEC-0110` 16.9, risque que `nadia-prof` a observe a l'ecran).
  Une decision de retrait sans proprietaire fait croire que le probleme est traite (`DEC-0009`) : le
  proprietaire est ecrit en section 10, dans le meme geste que cette decision.
- **Tarif** : **non pose ici, a chiffrer par `theo-archi` via `sofia-sm`**. `DEC-0009` n'a pas pose de tarif
  a `S5-1` (story lue : aucun champ de tarif), je n'ai donc pas de precedent a recopier, et je ne l'invente
  pas. Le poste est hors capacite de `nadia-prof` : il ne passe pas devant elle et n'entre dans aucun
  des deux plafonds de 5,0.
- **Report compte.** Premier report du retrait : s51, *"Garder jusqu'a s52"* (`DEC-0110` 16.9). **Il n'y a
  pas de second report** : cette decision le tranche. La decision de l'utilisatrice du 2026-10-06 sur
  `DEC-0110` 16.9 est remplacee par celle de la section 0, point 1 ; elle n'est pas reconduite par defaut.

---

## 4. Instance A - francais CE1 : `FRA-CE1-17`, `FRA-CE1-25`, `FRA-CE1-26`

**Vivier** (arbitrage de s52, section 4.1) : 18 lignes instruites et atteignables a `couvert-valide`
(`FRA-CE1-04` a `-15`, `-17` a `-19`, `-24` a `-26`), toutes **ACCEPTEES SOUS CONDITIONS** a s48 ; `FRA-CE1-03`
et `-21` plafonnees `en cours`. `PAR-13` tient : le francais CE1 n'est pas un terrain neuf (six lignes
`couvert-valide`, six paquets audites a s51).

**Critere de choix, ecrit avant les lignes** : la ligne qui ajoute le moins aux sept conditions transverses
(`TRV-CE1-1` a `-7`) et aux bornes de lisibilite deja mordues a s51. Deux d'entre elles ont donne les trois
reserves de francais CE1 de s50 et s51 : `TRV-CE1-6` (prenoms, `HOM-CE1-R1` et `TPS-CE1-R1`) et `TRV-CE1-5`
(indice, `ALP-CE1-R1`). Une ligne qui montre des personnages par leur prenom reexpose `TRV-CE1-6`.

| Ligne | Ce qu'elle ajoute aux conditions transverses (lu dans l'arbitrage de s48) | Retenue |
|---|---|---|
| `FRA-CE1-17` conjuguer au present | rien : sujet toujours un pronom personnel (`PRS-CE1-2`), donc aucun prenom ; aucun `texteSupport` ; aucune part designee. Ses trois conditions sont des proportions decidables sur l'item (`PRS-CE1-1` a `-3`) | **oui** |
| `FRA-CE1-25` synonyme ou contraire | un mot cible frequent dans une phrase courte (`SYC-CE1-1`) : peu de prenoms a distribuer ; frontiere avec `FRA-CE1-24` ecrite (`SYC-CE1-3`) | **oui** |
| `FRA-CE1-26` categories | une liste fermee declaree en tete (`CTG-CE1-2`) ; mots seuls, aucun prenom ; frontiere avec questionner le monde et les sciences ecrite (`CTG-CE1-2`) | **oui** |
| `FRA-CE1-04` a `-07` | un `texteSupport` et le texte masque (`NAR-6` transpose), *"la propriété la plus exposée de ce référentiel"*, que `nadia-prof` tient seule et qui n'a jamais rencontre de contenu au CE1 (s48 section 9, point 1 ; s52 section 3). La borne de longueur du texte support (`LON-CE1-3`, `-4`) n'est pas rodee | non |
| `FRA-CE1-08`, `-09`, `-10` | frontiere de lignes qui traverse deux domaines, `RLP-CE1-1` fait reperer le contenu de `FRA-CE1-14` et `-15` (s52 section 5, motif 1) | non |
| `FRA-CE1-11` negation | le biais de longueur *"par construction"* : *"Il ne mange pas"* est toujours plus long que *"Il mange pas"* (`TRV-CE1-2`, s48) | non |
| `FRA-CE1-12`, `-13` | part designee dans la phrase (`CLM-CE1-1`) et frontiere de l'une a l'autre (`VSJ-CE1-1`) ; phrases a prenoms | non |
| `FRA-CE1-14`, `-15` | phrases a prenoms (`TRV-CE1-6`), frontiere de `-14` avec `-17` | non |
| `FRA-CE1-18`, `-19` | meme famille que `-17` : une seule ligne de conjugaison a s52, parce qu'aucune ligne de conjugaison n'a encore ete jouee devant `nadia-prof` au CE1 ; `-19` porte en plus les exclusions de `PCO-CE1-1` | non |
| `FRA-CE1-24` | liste d'affixes declaree en tete, familles verifiees au dictionnaire (`FAM-CE1-1`, `-2`) ; frontiere avec `-25`, qu'on ne sert pas des deux cotes dans un meme sprint | non |

**Ce que ce choix ne garantit pas.** Je ne juge pas la difficulte pedagogique d'une ligne : ce jugement est
celui de `nadia-prof`. Le critere est le nombre de conditions transverses neuves, lu dans son arbitrage. Les
trois lignes retenues sont les memes lignes que `nadia-prof` declare instruites ; aucune n'est contestee. Pas
de `texteSupport` a s52 : les quatre lignes qui le portent restent en vivier pour un sprint ou `LON-CE1-3` et
`-4` pourront etre rodees seules. L'avis de faisabilite de `theo-archi` sur ces trois lignes est a rendre
avant la cloture du planning (section 10) ; je ne le presume pas.

**Corrections** : `TPS-CE1-R1` et `ALP-CE1-R1` sont engagees (premier report s51, `DEC-0110` 16.3 ; elles
tiennent dans le plafond : 4,0 engage, 4,5 avec rejeu). Elles se levent par le YAML seul (`avis-faisabilite.md`
de s51, V2 et V3 pour la nature des paquets) ; `PAR-14` ne s'applique pas (quatre items sur vingt-quatre,
trois sur vingt-quatre).

**Mentions de couverture** : aucune pour `FRA-CE1-17`, `-25`, `-26` (plafond `couvert-valide`, s48 sections
5.8, 6.3, 6.4 et 12). Les deux mentions dues d'avance au referentiel de francais CE1 portent sur `FRA-CE1-03` et
`FRA-CE1-21`, hors s52.

---

## 5. Instance C - mathematiques CE1 : `MAT-CE1-17`, `MAT-CE1-06`, `MAT-CE1-02` (moitie textuelle)

**Retablissement de la condition de `DEC-0110` 16.10.** La ligne de C y est ouverte *"sous la condition de
`nadia-prof`"* : le document de derivation et la verification des formes (a), (b), (c) a la cloture de s51.
Il existe : `ARBITRAGE-PREALABLE-s52-instances-ce1.md` constate la condition (ii) **remplie** (section 2.4) et
**refuse la borne a trois**, `PAR-13` **confirmee** (section 3). Cette partie de la condition est levee. Le
materiau de dessin n'est pas leve ; `ADR-0080` A2 est ecrit mais non commite (section 5.2).

### 5.1 Mentions de couverture, mot pour mot des arbitrages de s43

- **`MAT-CE1-17`**, si elle ferme (`ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-3.md`, section 3,
  *"Mention de couverture, fixee d'avance"*) : *"reconnaitre un axe de symetrie, et completer une figure par
  symetrie a raison d'une case par item ; completer une figure a plusieurs cases reste non couvert, aucune
  mecanique ne recoit une suite de gestes dans une meme manche"*.
- **`MAT-CE1-02`**, ce sprint, sur paquet textuel (`ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md`,
  condition `ORD-CE1-5`) : *"comparer, ranger et encadrer seuls ; le verbe 'placer sur une ligne graduee'
  n'est servi par aucun jeu et ne peut pas l'etre par du texte"*. **La ligne reste `en cours`**, jamais
  `couvert-valide`.
- **`MAT-CE1-06`** (lot 1, section 6, `MUL-CE1-7`) : si les dispositions sont dessinees et que `MUL-CE1-4` et
  `MUL-CE1-5` sont tenues, aucune mention n'est due ; si le paquet livre ne porte que de l'addition reiteree
  ecrite : *"addition reiteree seule ; la disposition en lignes et en colonnes n'est servie par aucun jeu et ne
  peut pas l'etre par du texte"*, et **la ligne reste `en cours`**.

### 5.2 Condition suspensive

Les postes de C ne sont engages qu'a ces conditions. Elles sont ecrites avant le planning, pour qu'il les
verifie, et non decouvertes apres.

| Poste | Conditions, toutes requises | Source |
|---|---|---|
| `MAT-CE1-17` | (1) l'amendement A2 d'`ADR-0080` est ecrit : `ZON-13` en garde-fou, rang de la bonne zone en troisieme clause du garde-fou 4, et le message de `AucunPaquetDeDesignationNEntreEnProductionSansEtendreLesTroisBalayagesTest` corrige dans le meme diff, *"avant le premier paquet de designation de CE1"* ; (2) les contraintes de dessin d'`ines-design` (figure a lignes candidates, quadrillage 6 x 6, legende a 36 libelles) sont ecrites ; (3) un developpeur est affecte au dessin | avis de s51, sections 5.2, 5.3, 6 (A2), 7 (Q3, Q5) ; arbitrage de s52, section 4.2 |
| `MAT-CE1-06` | (2) et (3) pour la famille *reseau de points* ; A2 n'est pas requis | avis de s51, sections 5.2, 5.5 (V9), 7 |
| `MAT-CE1-02`, moitie textuelle | aucune : *"faisable en l'etat"* (V10) | avis de s51, section 2 |

**Etat a l'ecriture de cette decision (arbre de travail du checkout principal, 2026-10-06).**
(1) **A2 est ecrit** : `squad/decisions/adr/ADR-0080-*.md`, section *"Amendement du 2026-10-06 (s52, `theo-archi`) - A2 :
`ZON-13` et le rang de declaration de la bonne zone deviennent des garde-fous"* (decisions 1 et 2, garde-fous de
contenu JUnit dans `adapter-content`). Il est present dans l'arbre de travail et **non commite par `theo-archi` au
moment ou j'ecris** : je le cite pour l'avoir lu, pas pour l'avoir vu fusionne. Je n'ai pas verifie qu'il corrige le
message de `AucunPaquetDeDesignationNEntreEnProductionSansEtendreLesTroisBalayagesTest`, que l'avis de s51 demande dans
le meme diff : `theo-archi` le confirme au planning. (2) **Les contraintes de dessin d'`ines-design` n'existent pas** :
`squad/design/` ne contient que les trois fichiers de s26. (3) Aucun developpeur de dessin n'est affecte. Sur trois
conditions, `MAT-CE1-17` en remplit une (A2) et `MAT-CE1-06` aucune des deux qui la concernent. Quand les contraintes existent,
leur chemin remplace cette phrase dans le planning de `sofia-sm`, et n'est pas cite ici avant d'avoir ete lu.

**Si une condition manque a la cloture du planning**, le poste concerne est ecrit *"non engage, budget
libere"* (`PAR-16`), montant libere 1,5 par poste, destination s53, et seul `MAT-CE1-02` bouge. **`MAT-CE1-17` : neuf
sprints de report (s43 a s51, `DEC-0110` 14.5). Engagee a s52, le compte s'arrete a neuf ; non engagee faute
de condition, il passe a dix.** Les deux postes dessines ne sont pas relies : `MAT-CE1-06` peut s'ouvrir sans
A2.

### 5.3 Le developpeur de dessin et le materiau

Ordre de grandeur de l'avis de `theo-archi` de s51 (section 5.2), pas une mesure :

- `MAT-CE1-17` : **au moins dix dessins de symetrie, carres de 352 px** (figures a lignes candidates, 5 a 6 ;
  quadrillages 6 x 6 avec axe et figure, 5 a 6), **un dessin par item** (section 5.1 de l'avis : deux items
  sur le meme dessin sont le meme ecran) ;
- `MAT-CE1-06` : **6 a 8 reseaux de points a 176 px** ;
- poids : environ 25 a 30 Ko, `POIDS_TOTAL_MAXIMUM` de 51 307 vers environ 80 000, deplace dans le commit
  qui installe les dessins avec sa valeur mesuree et son motif (`ADR-0036` clause 5).

Convention du depot depuis s25 : les dessins sont commites par **un developpeur affecte par `sofia-sm`**,
`ines-design` ecrivant les contraintes et les mesurant au rendu sans dessiner les dessins livres. Le choix du
developpeur est a `sofia-sm` (Q5 de l'avis). La capacite de dessin n'est pas chiffree ici : elle ne passe
pas par `nadia-prof`.

### 5.4 Decisions de `camille-po` demandees par l'avis de s51 (Q4)

1. **`nombreDeManches` de la symetrie : 8.** Un dessin par item, au moins dix items (`FER-2`), et le
   paquet porte strictement plus d'items que de manches (`DEC-0064`) : 8 laisse deux items de marge, et les dix
   dessins du minimum suffisent. Le chiffre n'est pas pedagogique : si `theo-archi` ou `ines-design` y voient
   un obstacle, il se corrige sans rouvrir cette decision.
2. **Libelle de sous-partie de `MAT-CE1-02`** : aucun libelle n'est invente. La mention de `ORD-CE1-5`, ci-
   dessus, mot pour mot, est l'unique libelle de la moitie textuelle.

---

## 6. Nombre d'instances : deux, pas trois

Instruction citee en section 0, ne fonde pas la borne. `nadia-prof` a refuse la borne a trois instances sur
la condition (i) (arbitrage de s52, section 6 : 2,0 prenables par une troisieme instance contre 4,0, aucun lot
neuf) et refuse d'amender `PAR-2` pour partager le francais CE1 par domaine (section 5 : frontiere
`FRA-CE1-08` / `-09` / `-10`, second terrain neuf a cote de C, `ADR-0065` amende en parallele, condition (i)
non remplie meme amende). **Je ne conteste aucun des deux refus** et je ne les reformule pas. Ce qui la
ferait reexaminer est ecrit dans sa section 5, dernier alinea : une proposition de decoupage du francais CE1
qui ne coupe aucune frontiere de la table de s48 section 7, sur un sprint sans autre terrain neuf, avec un avis
de `theo-archi`. **Je n'ecris pas cette proposition ici** : aucun sprint ne la demande, et s52 porte un terrain
neuf (C).

---

## 7. Deux points ouverts de `DEC-0110` 14.7, traites

**1. Passe transverse (`PAR-4`) a deux instances : non engagee a s52.** Texte (arbitrage de s29,
`PAR-4`) : tache nommee, affectee, payee, 0,5, *"affectee a l'instance qui porte l'accumulation"* (`PAR-7`) ;
`nadia-prof` confirme qu'elle est *"due, 0,5, dans la borne"* (arbitrage de s51, section 6.1) mais ne releve
pas la barre. Trois motifs :

1. **`PAR-16` : aucune story ne l'ecrit.** Son objet (les proprietes de portee **D**) est une table que je
   n'ai pas relue pour cette decision (section 0) ; l'engager sans story serait un poste sans proprietaire.
2. **Marge.** Sur A, l'engager porte l'engage a 4,5 et laisse 0,5 : un rejeu, rien de plus. s51 a consomme la
   marge de B entiere (5,0) sur deux refus.
3. **Elle ne ferme aucune ligne**, et `nadia-prof` ne l'a pas posee en condition de s52 (arbitrage de s52,
   section 10 : *"ni `PAR-9`, ni `PAR-11`, ni `PAR-4` a deux instances"*, elle les route).

**Report compte.** `DEC-0095` (s41) et `DEC-0098` (s42-s43) la retranchent de leur derivation de 8,0 sans
l'engager ; `nadia-prof` n'a trouve aucun sprint parallele depuis s41 qui l'ait engagee (arbitrage de s51,
section 6.1) ; posee en question a s51 (`DEC-0110` 14.7), non tranchee ; **non engagee a s52 : deuxieme fois
qu'elle est portee a une `DEC` d'ouverture sans engagement.** Un troisieme report serait une direction que
personne n'a choisie : la `DEC` de s53 l'engage, a l'instance qui porte l'accumulation, ou ecrit qu'elle est
abandonnee, auquel cas `PAR-4` est amendee par `nadia-prof`, qui en est l'autrice.

**2. Ecart `PAR-9` / pratique.** Pour s52 : la pratique fait foi (section 0, point 2 ; section 2). **Le texte
de `PAR-9` n'est pas amende par cette decision** : la regle est de `nadia-prof` (arbitrage de s30, section
1.3), et la reponse de l'utilisatrice porte sur le chiffrage de s52, pas sur l'amendement. **Report compte :
l'ecart est ouvert depuis `DEC-0110` 14.7 (s51), s52 le laisse ouvert.** Route a `nadia-prof` : amender le
texte de `PAR-9` pour qu'il dise ce que les ouvertures appliquent depuis s41, ou le maintenir et dire pourquoi.

---

## 8. Isolation

Trois branches, trois worktrees, un scratchpad **par agent** (reserve de `nadia-prof` datee du 2026-10-06,
arbitrage de s52, section 2.3 : un sous-repertoire par agent et non par couloir, aucune instance a la racine,
un port de serveur par agent). `squad/README.md` section 9 : une branche dediee implique un worktree dedie ;
`sofia-sm` ouvre le worktree de l'agent qu'elle route.

| Couloir | Branche | Worktree (dossier voisin du checkout) | Repertoire temporaire, un par agent |
|---|---|---|---|
| A | `s52-nadia-instance-a` | `../recreo-s52-nadia-instance-a` | `<scratchpad>/instance-a/<agent>` : `nadia`, le developpeur de A |
| C | `s52-nadia-instance-c` | `../recreo-s52-nadia-instance-c` | `<scratchpad>/instance-c/<agent>` : `nadia`, le developpeur du paquet, le developpeur de dessin |
| Retrait | `s52-retrait-sciences-cm1` | `../recreo-s52-retrait-sciences-cm1` | `<scratchpad>/retrait/<agent>` : le developpeur du retrait |

`<scratchpad>` designe le repertoire temporaire de la session de s52 ; aucun chemin de poste n'est ecrit ici
(`CLAUDE.md`, conventions de redaction).

Trois points que je route a `sofia-sm` sans les trancher :

1. **Deux ecrivains dans le couloir C.** Le developpeur de paquet et celui de dessin : ou ils partagent un
   worktree avec des fichiers disjoints (SVG d'un cote, YAML et tests de l'autre), ou chacun a le sien. Le
   choix est a `sofia-sm` ; la forme (c) se constate a l'ecriture concurrente d'une meme ressource physique
   (arbitrage de s52, section 2).
2. **Fichiers partages entre les trois couloirs** : `catalogue.yaml`, `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`,
   `TexteAccentueExposeParLePortEntrantTest`. A s51, A et B les ont fusionnes sans perte ; le retrait les
   touche a son tour (retrait d'entrees, ajustement des tests). A la fusion : verifier qu'aucune ligne ajoutee
   par un couloir n'est perdue (methode de l'arbitrage de s52, section 2.3).
3. **Forme (a).** Aucune story de s52 ne modifie un paquet et sa consigne dans le meme sprint : le retrait
   ne touche ni fichier de paquet ni consigne, il retire une entree.

`REGISTRE-identifiants.md` : aucun identifiant neuf n'est attendu (section 11). Une instance qui en alloue
un (reserve neuve) le fait depuis son propre worktree (README, amendement du 2026-09-10).

---

## 9. Ce qui n'est pas engage, et le compte des reports

| Poste | Motif | Report compte |
|---|---|---|
| `S51-SCI-ACTIVITE-HUMAINE-CM1-REPRISE` (0,5), `S51-SCI-TERRE-CM1-REPRISE` (1,0 ou 0,5) | perimetre CE1 ; `PAR-2` ; paquets retires (section 3) | premier report : s51 (B a 5,0) ; **second : s52** |
| `S51-SCI-CORPS-CM1-RESERVES` (`CRP-CM1-R1` a `R3`, 0,5) | perimetre CE1 ; aucune condition, mais aucune instance de sciences | premier report : s51 ; **second : s52** |
| `MAT-CE1-02`, moitie designation (ligne graduee) | A3 d'`ADR-0080` non due (arbitrage de s52, section 8) ; hors capacite | premier report : s51 (`DEC-0110` section 5) ; **second : s52** |
| `MAT-CE1-14` | quatrieme lot de C et A3 | n'a jamais ete engagee ; non reportee de moi |
| 15 lignes de francais CE1 atteignables (18 moins 3) et `FRA-CE1-03`, `-21` | `PAR-2`, plafond de A | vivier, non un report : aucune n'a de story |
| Passe transverse `PAR-4` | section 7 | **deuxieme fois sans engagement** |
| Ecart "palier 2" de la matrice (`DEC-0110` section 6, R10 du planning de s51) | la matrice est a moi et cette tache ne l'edite pas | non traite a s51 (`DEC-0110` section 6 : *"Ecart a signaler, non traite ici"*) ; **second : s52** |
| `ART-CP-10` | hors perimetre de s52. `ADR-0104` est `Propose`, suspendu a `C5` clause 1 de `nadia-prof` ; aucun budget avant sa reponse (`DEC-0110` 16.10) | exclue de s50 (`DEC-0109` 10.1), de s51 (`DEC-0110` section 6) et de s52 : **troisieme sprint de suite** |
| `S51-OUTIL-JOURNAL-TOKENS` | non chiffree (`DEC-0110` 17.2) ; proprietaire `theo-archi` | premier report : s51 ; **second : s52** |

**`ART-CP-10` : trois reports successifs sont une direction que personne n'a choisie** (principe des
decisions de ce depot). Le motif est le meme depuis s50 et il est exterieur a la squad : la reponse de
`nadia-prof` a `C5` clause 1. Ce n'est pas un choix de ma part, et je le dis comme tel plutot que de le
presenter comme un arbitrage. **A l'ouverture de s53, une seule des deux issues s'ecrit** : le budget est
alloue ou `ART-CP-10` est declaree *non couvrable en l'etat* avec le motif, par `nadia-prof`.

---

## 10. Postes, stories et routage (`PAR-16`)

| Poste | Story (`docs/backlog/backlog.md`, section "Sprint 52" sauf mention) | Affectation |
|---|---|---|
| `FRA-CE1-17` | `S52-FRA-PRESENT` | a affecter par `sofia-sm` (instance A de `nadia-prof`) |
| `FRA-CE1-25` | `S52-FRA-SYNONYMES-CONTRAIRES` | a affecter par `sofia-sm` (instance A) |
| `FRA-CE1-26` | `S52-FRA-CATEGORIES` | a affecter par `sofia-sm` (instance A) |
| `TPS-CE1-R1` | "Story de correction differee - reserve `TPS-CE1-R1`" (fin de la section "Sprint 51") | a affecter par `sofia-sm` (instance A) |
| `ALP-CE1-R1` | "Story de correction differee - reserve `ALP-CE1-R1`" (fin de la section "Sprint 51") | a affecter par `sofia-sm` (instance A) |
| `MAT-CE1-17` | `S51-MAT-SYMETRIE`, tarif porte a 1,5 | a affecter par `sofia-sm` (instance C ; dessin : Q5) |
| `MAT-CE1-06` | `S51-MAT-MULTIPLICATION`, tarif porte a 1,5 | a affecter par `sofia-sm` (instance C ; dessin : Q5) |
| `MAT-CE1-02` (moitie textuelle) | `S51-MAT-COMPARER-RANGER` | a affecter par `sofia-sm` (instance C) |
| Retrait des deux paquets | `S52-RETRAIT-SCIENCES-CM1` | a affecter par `sofia-sm`, premiere a fusionner |

Controle `PAR-16` a la cloture du planning, par `sofia-sm` : chaque poste ci-dessus porte une story et une
affectation nominative, ou est ecrit *"non engage, budget libere"* avec le montant et la destination
(section 5.2 pour C).

| A qui | Quoi |
|---|---|
| `sofia-sm` | Ouvrir s52 a trois branches et trois worktrees, un repertoire temporaire et un port par agent (section 8) ; affecter les neuf postes ; **affecter `S52-RETRAIT-SCIENCES-CM1` en premier** ; affecter le developpeur de dessin (Q5) ; verifier la condition suspensive de C a la cloture du planning (5.2) ; controle `PAR-16` |
| `theo-archi` | Commiter **A2 d'`ADR-0080`** (ecrit, non commite) avant le premier paquet de `MAT-CE1-17`, et confirmer qu'il corrige le message du test de plafond (5.2) ; avis de faisabilite sur les trois lignes de francais, sur le materiau de C (V8, V9 de s51, qui valent pour s52) et **tarif du retrait** (section 3) ; test du retrait : lecture des tests qui comptent ou inventorient les entrees de catalogue |
| `ines-design` | Contraintes de dessin des figures a lignes candidates, des quadrillages 6 x 6, des reseaux de points, de la legende a 36 libelles (Q3 de l'avis de s51), sous `squad/design/`, avant tout dessin de C |
| `yanis-qa` | Criteres des neuf stories testables ; ce qu'aucun automate ne voit est liste dans chacune pour `nadia-prof` |
| `nadia-prof` | Instances A et C. Fixer le texte de `PAR-9` ou le maintenir (section 7) ; `PAR-4` a s53 (section 7) ; `ART-CP-10` : sa reponse a `C5` clause 1 (section 9) |

---

## 11. Identifiants

**Aucun identifiant neuf.** Les conditions des trois lignes de francais (`PRS-CE1-1` a `-3`, `SYC-CE1-1` a
`-3`, `CTG-CE1-1` a `-3`) sont allouees depuis s48 (`ARBITRAGE-PREALABLE-s48...`, section 11) ; celles de C le
sont depuis s43. Les stories portent des identifiants de story, qui ne sont pas des identifiants de condition.
`REGISTRE-identifiants.md` n'est pas edite.

---

## 12. Ce que cette decision ne fait pas

- Elle ne prononce aucun Done pedagogique et ne juge la pedagogie d'aucun contenu.
- Elle n'edite ni `MATRICE-couverture.md`, ni aucune fiche de conformite, ni `REGISTRE-identifiants.md`, ni
  `DEC-0110`. La matrice ne bouge qu'au Done de `nadia-prof`.
- Elle n'ecrit aucun code et ne tranche aucune question technique : le developpeur, le tarif du retrait, la
  forme du test, A2, la forme d'une zone sur une ligne oblique et le plafond de zones d'une ligne graduee
  (`DEC-0110` 14.6) sont a `sofia-sm` et `theo-archi`.
- Elle ne cree pas de troisieme instance et n'amende pas `PAR-2`, `PAR-4`, `PAR-9` ni `PAR-11`.
- Elle ne rouvre aucune ligne `couvert-valide` : les deux corrections se levent sans changer de statut.

---

## Decisions prises

1. s52 ouvre a **deux instances de `nadia-prof`**, A (francais CE1) et C (mathematiques CE1), plus un couloir
   de developpement sans instance (retrait). Pas de troisieme instance ; `PAR-2` non amende (section 6).
2. **Lecture du plafond : pratique depuis s41** (decision de l'utilisatrice) ; `PAR-11` non appliquee, risque
   ecrit (section 2). Engage 8,0 : A 4,0 (4,5 / 5,0 avec rejeu), C 4,0 (4,5 / 5,0 avec rejeu).
3. **Retrait des deux paquets de sciences CM1 refuses du catalogue servi** (decision de l'utilisatrice) ;
   fichiers de contenu, tests de paquet et fiches conserves ; story `S52-RETRAIT-SCIENCES-CM1`, premiere a
   fusionner ; tarif a chiffrer par `theo-archi` (section 3).
4. Instance A : `FRA-CE1-17`, `FRA-CE1-25`, `FRA-CE1-26`, plus `TPS-CE1-R1` et `ALP-CE1-R1` ; aucune ligne a
   `texteSupport` (section 4).
5. Instance C : `MAT-CE1-17` (1,5), `MAT-CE1-06` (1,5), `MAT-CE1-02` moitie textuelle (1,0, ne ferme pas),
   mentions de couverture mot pour mot des arbitrages de s43 (section 5.1).
6. **Condition suspensive de C** : A2 d'`ADR-0080` (ecrit, non commite), contraintes de dessin d'`ines-design`, developpeur de
   dessin affecte ; poste concerne non engage et budget libere si elle manque a la cloture du planning
   (section 5.2). `MAT-CE1-17` : neuf sprints si engagee, dix sinon.
7. `nombreDeManches` de la symetrie : 8 ; libelle de `MAT-CE1-02` : la mention de `ORD-CE1-5` (section 5.4).
8. Passe transverse `PAR-4` : non engagee, deuxieme fois sans engagement, la `DEC` de s53 l'engage ou
   l'abandonne (section 7). Ecart `PAR-9` / pratique : s52 chiffre en pratique, texte de `PAR-9` route a
   `nadia-prof`.
9. Reprises de sciences CM1, reserves `CRP-CM1-R1` a `R3`, moitie designation de `MAT-CE1-02` : non engagees,
   seconds reports (section 9). `ART-CP-10` : hors perimetre, troisieme sprint de suite, issue a ecrire a s53.
10. Trois branches, trois worktrees, un repertoire temporaire et un port par agent (section 8).
11. Fin de la decision de l'utilisatrice sur `DEC-0110` 16.9 (*"Garder jusqu'a s52"*) : remplacee par la
    decision 3, non reconduite par defaut.

---

## Amendement du 2026-10-06 (s52, `camille-po`, consigne B8) : ecart F2 du retrait et condition (1) de `MAT-CE1-17`

**Methode.** Les sections 1 a 12 ci-dessus ne sont pas reecrites : cet amendement dit ce qu'elles n'ont pas
su et ce qui les remplace. Sources lues sur le disque de la branche `s52-camille-amendements` (lignee de
`round-two` a `b4ce7d93`) : `squad/sprints/s52-deux-instances-ce1-francais-maths/avis-faisabilite.md`
(sections 3.1 a 3.3, 4.1, 5), `squad/sprints/s52-deux-instances-ce1-francais-maths/planning.md` (section
"Points routes", R1 et R3), `ADR-0080` (amendement A2, section 3).

### A.1 Section 8 point 2 : le retrait ne touche pas `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` (R1)

**Ce que la section 8 point 2 disait.** Que `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` est un
des fichiers partages que le retrait *"touche a son tour (retrait d'entrees, ajustement des tests)"*. Je l'ai
ecrit sans avoir mesure le retrait.

**Ce que l'avis de `theo-archi` a mesure** (sections 3.1 a 3.3) :

| Fichier | Sous le retrait seul | Edition due |
|---|---|---|
| `catalogue.yaml` | - | retirer les deux entrees (12 lignes) |
| `TexteAccentueExposeParLePortEntrantTest` | **rouge** | retirer 8 chaines : les deux identifiants, les deux titres, les deux consignes, les deux competences |
| `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` | **vert** | **aucune : ne pas le toucher** |

Le garde-fou de rang balaie `contenu/`, pas le catalogue. Les deux fichiers de paquet restent ; retirer
leurs deux entrees de `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` les rendrait *"ni juges ni declares"*, le mecanisme
mesure sur les sondes de l'avis (section 1.1) a l'envers. Le mot *"servi"* de leurs deux motifs devient
inexact : l'avis ne demande pas de le corriger (un commentaire, dans un fichier que A et C editent aussi) et
je ne le demande pas non plus.

**Cet amendement remplace le point 2 de la section 8 par** : *le retrait touche deux fichiers,
`catalogue.yaml` et `TexteAccentueExposeParLePortEntrantTest` ; il ne touche pas
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`, qui reste partage entre les couloirs pour les
paquets neufs de A et de C.* La verification a la fusion de la section 8 point 2 (aucune ligne ajoutee par un
couloir n'est perdue) reste d'application aux fichiers partages.

Le critere de la story `S52-RETRAIT-SCIENCES-CM1` est amende au backlog dans le meme geste.

### A.2 Condition (1) de la section 5.2, lue contre `ADR-0080` A2 section 3 (R3)

**Les trois textes.**

- `DEC-0111` 5.2 (1), que j'ai ecrit : A2 ecrit, **et** le message de
  `AucunPaquetDeDesignationNEntreEnProductionSansEtendreLesTroisBalayagesTest` corrige *"dans le meme diff"*.
- `ADR-0080` A2 section 3 : le message *"reste due par `theo-archi`, au plus tard dans le diff qui monte ce
  cliquet de 2 a 3 avec le premier paquet de `MAT-CE1-17`"*. A2 (`edf15763`) est commite sans la correction,
  par une borne ecrite par son auteur (lot limite aux garde-fous).
- Avis, section 5, A1 : correction bloquante *"pour le premier commit du paquet de `MAT-CE1-17`, pas pour
  son engagement"*.

**Lecture.** La lettre *"dans le meme diff"* **ne peut plus etre remplie** : `edf15763` existe et ne porte
pas le message. Ce n'est pas un defaut de `theo-archi`, dont l'ADR ecrit la borne : j'ai formule la
condition comme un moyen (un diff) alors que ce qu'elle protege est un etat. Aucun paquet de designation de
CE1 n'entre tant que le message du cliquet demande d'*"etendre le balayage de production"*, alors que les
balayages lisent la production depuis s34. Le motif est dans ma citation d'origine, *"avant le premier
paquet de designation de CE1"*, et c'est celui d'`ADR-0080` et de l'avis.

**La condition (1) devient** : A2 est ecrit (rempli : `edf15763`, avis 4.1, verts a `7350fb07`) **et** le
message du cliquet est corrige sur le disque de `round-two`, dans un commit qui precede le premier commit du
paquet de symetrie ou qui est le diff meme qui monte le cliquet de 2 a 3. Cela se verifie en lisant le
fichier de test a la ligne du message, pas en lisant un rapport.

**Etat de la condition (1) a l'ecriture de cet amendement.** Moitie A2 : remplie. Moitie message :
**non remplie**, le fichier dit encore *"etendre le balayage de production"* (avis, section 4.1 ; planning,
section "Conditions de l'instance C"). **U1 de `theo-archi` la remplit, a deux conditions** : (a) son commit
existe sur `round-two` et non sur une branche non fusionnee ; (b) le message qu'il porte ne demande plus
d'etendre un balayage de production. Je ne declare ni (a) ni (b) acquises : U1 n'est pas sur mon disque et
la confirmation de `sofia-sm` que U1, U2 et U3 existent n'est pas arrivee. Verifier (a) et (b) est le
premier geste de U4.

**Ce que cet amendement ne fait pas.** Il ne re-engage pas `MAT-CE1-17` et ne dit pas si le report s'arrete
a neuf sprints : le decompte reste celui de la section 5.2 (non engagee : dix), tel que le planning l'a
applique (1,5 libere, destination s53). Ces deux points sont U4, qui attend la confirmation de `sofia-sm`.
Il ne touche ni la condition (2) ni la condition (3) de la section 5.2.

### A.3 Fiches de conformite (R4)

Six fiches ouvertes dans ce geste, avant la premiere livraison de chacune, sans bloc de validation rempli :
`squad/conformite/FICHE-bandit-manchot-conjugaison-present-ce1.md`,
`FICHE-bandit-manchot-synonymes-contraires-ce1.md`, `FICHE-bandit-manchot-categories-ce1.md`,
`FICHE-bandit-manchot-sens-multiplication-ce1.md`, `FICHE-bandit-manchot-comparer-ranger-ce1.md`,
`FICHE-designation-symetrie-ce1.md`. Ouvrir la fiche de la symetrie ne la re-engage pas.
**Les noms sont ceux que je propose** : le nom de jeu est l'identifiant d'entree de catalogue, que le
developpeur fixe au premier commit du paquet. S'il en choisit un autre, je renomme la fiche dans le commit
qui suit et je le dis.

### A.4 Changement de code

Aucun. L'amendement A.1 corrige une consigne (ce que le retrait touche) ; l'avis de `theo-archi` est deja
celui que suit `marc-dev` (B1). Proprietaire du retrait : `marc-dev`, story `S52-RETRAIT-SCIENCES-CM1`.

---

## Amendement du 2026-10-06 (s52, `camille-po`, consigne U4) : `MAT-CE1-17` re-engagee, plancher de zone

**Methode.** Les sections 1 a 12 et les amendements A.1 a A.4 ne sont pas reecrits. Sources lues sur le disque
de la branche `s52-camille-amendements`, lignee de `round-two` a `bb0d6f94` : `ADR-0080` (A2, A2bis), le
test `AucunPaquetDeDesignationNEntreEnProductionSansEtendreLesTroisBalayagesTest`, les contraintes
d'`ines-design` (section 4), l'avis de faisabilite (sections 4.1, 5), le planning de s52 (sections
"Conditions de l'instance C", "Affectations nominatives"), et, par `git show`, les reponses de `nadia-prof`
(U3) sur la branche `s52-nadia-instance-c`.

### A.5 Les trois conditions de la section 5.2, lues avec la condition (1) de A.2

| Condition | Verification (commande) | Etat |
|---|---|---|
| (1) moitie A2 | `edf15763`, `ZON-13` et rang de la bonne zone : avis 4.1, verts a `7350fb07` | remplie |
| (1) moitie message du cliquet, etat demande par A.2 : commit sur `round-two`, et message qui ne demande plus d'etendre un balayage de production | (a) `git branch --contains ceffde66` rend `round-two` ; (b) `grep -rni "etendre le balayage de production" bootstrap/src/test/java/com/recreo/archi/` ne rend aucune ligne ; le message du test cite `BALAYAGES_DE_PRODUCTION` (trois noms de tests, lignes 67 a 70) et dit de monter `PLAFOND_DE_DESIGNATION_EN_PRODUCTION` *"dans le diff qui sert ce paquet"* ; la ligne 201 du test asserte `hasMessageNotContaining("Etendre le balayage")` | **remplie** |
| (2) contraintes d'`ines-design`, quadrillage inclus | `git branch --contains 44ac6c02` rend `round-two` ; la section 4 est ecrite (4.1 mesures et commandes, 4.2 grille, 4.3 `SYM-CE1-3`, 4.5 poids) ; `git show s52-nadia-instance-c:squad/conformite/ARBITRAGE-PREALABLE-s52-instance-c-reponses.md` : section 2, Q4 *"oui, pour la partie pedagogique"*, section 3.2 cases candidates en sous-ensemble | **remplie** |
| (3) developpeur de dessin affecte | planning, section "Affectations nominatives" : `lea-dev`, seule ecrivaine de C | **remplie** |

**Deux ecarts de lettre, dits.** (i) La condition (2) de la section 5.2 nommait une *"legende a 36 libelles"*.
`ines-design` la remplace par des zones sur les seules cases candidates (4.3) et `nadia-prof` l'admet (3.2) :
il n'y a plus de legende a 36 libelles a ecrire, et la condition est lue comme remplie sur ce remplacement,
pas sur l'ecriture d'une legende. Le texte des trois a cinq libelles qui reste (`ines-design`, section 5
"Pour `nadia-prof`", point 8) et le choix de `n` par item (point 9) sont du contenu : `lea-dev` les ecrit,
`nadia-prof` les juge a l'audit ; ses reponses ne les traitent pas et je ne les lui prete pas. Les points 6 et
7 de la meme section portent sur `DEP-CE1-2`, qui est `MAT-CE1-14`, non engagee. (ii) La reponse de
`nadia-prof` vit sur la branche `s52-nadia-instance-c`, non fusionnee dans `round-two` : elle **existe** et se
lit par `git show`, ce que la condition demande. Sa fusion est un geste de `sofia-sm`, a faire avant le
premier commit du paquet de symetrie.

### A.6 `MAT-CE1-17` est re-engagee a 1,5

Les trois conditions sont remplies : `MAT-CE1-17` est **engagee**, `S51-MAT-SYMETRIE`, tarif 1,5 (1,0 plus 0,5
de lot illustre, `REC-1`), `lea-dev`, apres `S51-MAT-MULTIPLICATION` (planning, section "Affectations").
Le budget de 1,5 que le planning avait libere vers s53 **n'est plus libere** : la destination s53 est annulee.

**Compte des reports.** Neuf sprints de report (s43 a s51, `DEC-0110` 14.5). Le planning a ecrit *"non
engage, budget libere"* a sa cloture, ce qui portait le compte a dix ; cet amendement engage le poste dans
s52, et **le compte s'arrete a neuf**, comme la section 5.2 le prevoyait pour un poste engage a s52. Le
delai entre la cloture du planning et cet engagement est celui de U1 a U3, et il est ecrit ici pour que le
compte ne soit pas lu comme l'absence de tout report a s52.

**Arithmetique de capacite (`PAR-8` : plafond individuel 5,0, trois lots au plus par instance).**

| Instance C | Tarif | Cumul |
|---|---|---|
| `S51-MAT-MULTIPLICATION` (`MAT-CE1-06`), lot | 1,5 | 1,5 |
| `S51-MAT-COMPARER-RANGER` (`MAT-CE1-02`, moitie textuelle), lot, ne ferme pas | 1,0 | 2,5 |
| `S51-MAT-SYMETRIE` (`MAT-CE1-17`), lot | 1,5 | **4,0** |
| rejeu nomme (`PAR-10`) | 0,5 | **4,5** |

Trois lots, le maximum de `PAR-8`. **Correction d'un chiffre repris par la consigne U4 : 4,0 est le total de C
avec `MAT-CE1-17`** (section 2 et decision 2 de cette decision : 1,5 + 1,5 + 1,0), pas un total auquel ajouter
1,5. A la cloture du planning, C engageait 2,5 sans elle ; re-engagee, C revient a 4,0. Le plafond tient :
4,0 engages, 4,5 avec le rejeu, 0,5 de marge sous 5,0 (deux rejeux : 5,0, au plafond, pas au-dela). Total du
sprint : A 4,0 plus C 4,0 = **8,0 engages**, la borne de `DEC-0098` section 3 ; avec un rejeu par instance,
9,0 au plus. Le risque ecrit en section 2 (C porte 0,5 de rejeu au lieu des 1,5 que `PAR-11` demanderait) est
inchange ; il pese sur `MAT-CE1-17`, que `lea-dev` ecrit en dernier.

### A.7 Plancher de zone : la barre de refus est celle de `nadia-prof`, 16 unites est une marge de dessin

Trois textes disent trois choses. `ADR-0080` A2bis (section 4) et les contraintes d'`ines-design` (2.7
point 5) retiennent 16 unites ; `nadia-prof` (U3, section 6) **ne retient pas 16 comme exigence
pedagogique** : sa barre est un rendu d'**au moins 44 px a 296 px de large** (la largeur servie a 320 px de
fenetre), soit 15 unites sur un dessin carre de 100 unites (44,4 px). A2bis mesure 48 cadres a 15 unites dans
les deux paquets servis et aucun sous 15 ; une exigence a 16 les rendrait non conformes apres coup.

**Arbitrage de `camille-po`.** Pour `MAT-CE1-17` et `MAT-CE1-06` :

1. **La regle de refus d'une zone est la barre de `nadia-prof`** : une zone rendue sous 44 px a 296 px est
   refusee ; une zone qui s'y tient ne l'est pas, quel que soit son chiffre en unites (U3, section 6,
   motif 2 : *"une zone qui se rate au doigt est refusee, quel que soit son chiffre"*).
2. **16 unites est la marge de dessin** : `lea-dev` dessine a 16 unites et plus (47,4 px a 296 sur un carre
   de 100 unites), et un cadre de 15 unites n'est pas refuse pour cela.
3. Un cadre sous 15 unites sur un carre de 100 est sous la barre ; sur un dessin non carre, la taille rendue
   se mesure (la frise 640 x 320 n'a pas ete mesuree, A2bis point 2).

**Cet amendement ne change pas `ADR-0080`**, qui est a `theo-archi`. **Ecart route.** A2bis ecrit *"le plancher
se tient a la redaction (refus 5 de la section 2.7 des contraintes d'`ines-design`)"*, et ce refus 5 dit
*"un cadre de zone sous 16 unites (sous 44 px a 296 px de large)"* : les deux textes presentent la marge
comme le plancher de refus. Les remettre en accord avec l'arbitrage ci-dessus est a `theo-archi` (`ADR-0080`)
et a `ines-design` (contraintes), par `sofia-sm` ; je ne reecris ni l'un ni l'autre.

### A.8 Changement de code et routage

Aucun changement de code dans cet amendement. Proprietaire de ce qu'il declenche : `lea-dev`, story
`S51-MAT-SYMETRIE`, apres `S51-MAT-MULTIPLICATION`. Routage a `sofia-sm` dans le message de retour de
`camille-po` : mise a jour du planning, fusion de la reponse de `nadia-prof`, ecart de A.7 a `theo-archi`.

---

## Amendement du 2026-10-06 (s52, `camille-po`, cloture produit) : etat final de s52, reports, perimetre de s53 propose

**Methode.** Les sections 1 a 12 et les amendements A.1 a A.8 ne sont pas reecrits : cet amendement fait foi
sur eux pour l'etat final (meme geste que `DEC-0110` section 16). Sources lues sur le disque de la branche
`s52-camille-amendements`, lignee de `round-two` a `40daa419` (retrait, instance A et instance C fusionnes) : les
huit fiches de conformite citees en A.10, relues une a une pour leur verdict et non reprises de la consigne ;
`squad/conformite/ARBITRAGE-PREALABLE-s52-fra-ce1-questions-de-l-avis.md` (sections 4.2 et 4.3) ;
`squad/conformite/ARBITRAGE-PREALABLE-s30-...` section 3.2 (`PAR-10`) ; `PAR-14` dans l'arbitrage de s33
(ligne 596). Comptes de la matrice par la commande ecrite en tete de `MATRICE-couverture.md`, lancee apres
mon edit : `couvert-valide` 132, `en cours` 4, `non couvert` 46, `non couvrable en l'etat` 76, total 258,
denominateur 182 ; la meme commande a `40daa419` rendait 127, 3, 52, 76, 258. Perimetre : la matrice entiere.
**Ce que je n'ai pas lu** : aucun rapport de CI, aucun journal de mesure de `yanis-qa`, ni l'etat des jobs de
`ci.yml` au sha de fusion ; les fiches disent que `nadia-prof` n'a pas mesure la porte technique, et cet
amendement ne la declare pas verte.

### A.9 Objectif gele de la section 1 : atteint

Cinq lignes fermees sur cinq, `MAT-CE1-02` a `en cours`, `TPS-CE1-R1` et `ALP-CE1-R1` levees, les deux paquets
de sciences retires (`grep -c 'activite-humaine-cm1\|terre-cm1' adapters/adapter-content/src/main/resources/catalogue.yaml`
rend 0 ; il en rendait 2 entrees a la section 0). **127 / 182 = 69,8 % a 132 / 182 = 72,5 %** (72,53 %), le
meilleur cas de la section 1. Je n'ai pas ecrit de plancher et je n'en ecris pas apres coup.

### A.10 Les lignes, verdict par verdict

| Ligne | Fiche | Verdict de `nadia-prof` | Matrice | Reserve restante |
|---|---|---|---|---|
| `FRA-CE1-17` | `FICHE-bandit-manchot-conjugaison-present-ce1.md` | premier passage **REFUSE** (`TRV-CE1-5`, indices des items 9 et 24), second passage **VALIDE** | `couvert-valide`, sans mention | aucune |
| `FRA-CE1-25` | `FICHE-bandit-manchot-synonymes-contraires-ce1.md` | **VALIDE SOUS RESERVE** | `couvert-valide`, sans mention | **`SYC-CE1-R1`** (ouverte) |
| `FRA-CE1-26` | `FICHE-bandit-manchot-categories-ce1.md` | **VALIDE** | `couvert-valide`, sans mention | aucune |
| `MAT-CE1-06` | `FICHE-bandit-manchot-sens-multiplication-ce1.md` | **VALIDE SOUS RESERVE** ; dispositions dessinees, `MUL-CE1-4` et `MUL-CE1-5` tenues, aucune mention due (`MUL-CE1-7`) | `couvert-valide`, sans mention | **`MUL-CE1-R1`** (ouverte) |
| `MAT-CE1-17` | `FICHE-designation-symetrie-ce1.md` | premier passage **VALIDE SOUS RESERVE** (`SYM-CE1-R1`), second passage **VALIDE**, `SYM-CE1-R1` levee | `couvert-valide`, mention de l'arbitrage de s43 (lot 3, section 3) mot pour mot | aucune ; `ZON-11` non encodee, `nadia-prof` seule garde |
| `MAT-CE1-02` | `FICHE-bandit-manchot-comparer-ranger-ce1.md` | **VALIDE** sur la moitie textuelle | **`en cours`**, mention d'`ORD-CE1-5` mot pour mot | aucune |
| `FRA-CE1-20` | `FICHE-bandit-manchot-temps-ce1.md` | levee de `TPS-CE1-R1` | inchangee, deja `couvert-valide` | aucune |
| `FRA-CE1-23` | `FICHE-bandit-manchot-ordre-alphabetique-ce1.md` | levee de `ALP-CE1-R1` | inchangee, deja `couvert-valide` | aucune |

Un refus en s52, sur `FRA-CE1-17`, un seul motif, ecrit dans la fiche et corrige dans le meme sprint (rejeu
`40989ba9`, second passage `VALIDE`) : le refus n'est pas classe sans suite.

### A.11 Budget reel par instance

| Instance | Engage | Rejeu paye | Total | Plafond (`PAR-8`) |
|---|---|---|---|---|
| A (francais CE1) | 4,0 : `FRA-CE1-17`, `-25`, `-26` a 1,0, `TPS-CE1-R1` et `ALP-CE1-R1` a 0,5 | 0,5 : rejeu de `FRA-CE1-17` (refus `4965fe7a`, rejeu `40989ba9`, second passage `ed90a523`) | **4,5** | 5,0 |
| C (mathematiques CE1) | 4,0 : `MAT-CE1-17` 1,5, `MAT-CE1-06` 1,5, `MAT-CE1-02` 1,0 | 0,5 : second passage de `MAT-CE1-17` sur `SYM-CE1-R1` (correction `bc7cf6a0`, verdict `3c97a87a`) | **4,5** | 5,0 |

**Total du sprint : 8,0 engages, 9,0 consommes**, soit le "9,0 au plus" de A.6. Aucune instance ne depasse 5,0 ;
chacune a consomme son seul rejeu nomme et garde 0,5 de marge non depensee.

**Question posee par la consigne : le rejeu de la symetrie est-il un rejeu pedagogique paye ou une correction de
reserve ?** Les deux, et la comptabilite ne les separe pas. `SYM-CE1-R1` est une reserve, non un refus
(`MAT-CE1-17` est passee `couvert-valide` au premier passage). Sa correction est un travail de `lea-dev` que le
tarif de `MAT-CE1-17` (1,5) ne couvre pas, et `nadia-prof` a rejoue le paquet pour la lever : sept parties
completes de huit manches, comparaison item par item du YAML avant et apres, onze items sur onze. Selon `PAR-10`
(arbitrage de s30, section 3.2), *"un rejeu borne se paie par paquet rejoue"*, a 0,5 quand la propriete a tenir
est nommee par ecrit dans la reserve, ce qu'est `SYM-CE1-R1` (*"declarer les zones de haut en bas puis de
gauche a droite"*). **Je le compte donc sur le rejeu nomme de C, a 0,5.** Deux reserves a dire : (1) `nadia-prof`
n'a ecrit aucun prix dans la fiche ; ce 0,5 est mon application de `PAR-10`, pas son tarif, et elle fixe le
prix ; (2) la lettre de `PAR-14` (arbitrage de s33, ligne 596) paie au tarif d'un lot une reprise qui touche plus
de la moitie des items, et ici l'ordre de declaration change sur onze items sur onze. Je ne l'applique pas : son
motif est que *"l'audit precedent ne couvre plus rien"*, et elle a montre que le meme ensemble de zones, de
cadres, de libelles et de dessins est servi. Si elle lit `PAR-14` autrement, C est a 5,5 et le plafond de 5,0
est franchi ; elle le dit, et j'amende. Le rejeu de `FRA-CE1-17`, lui, est un refus rejoue : 0,5, sans ambiguite.

**Le risque ecrit en section 2 s'est realise sans dommage.** `PAR-11` aurait demande 1,5 de marge ; A et C
portaient 0,5. Chacune a eu un seul evenement a payer (un refus pour A, une correction rejouee pour C). Un
second evenement sur la meme instance n'aurait trouve aucun rejeu.

### A.12 Report de `MAT-CE1-17` : clos

**Neuf sprints de report** (s43 a s51, `DEC-0110` 14.5), comme A.6 l'avait arrete en re-engageant la ligne.
**Fermee a s52, au dixieme sprint depuis son instruction** (s43 a s52 inclus). Le planning avait ecrit *"non
engage, budget libere"* a sa cloture, ce qui portait le compte a dix ; A.6 l'a ramene a neuf, et la fermeture le
confirme. Le compte ne se lit pas "dix reports" : la ligne a attendu neuf sprints et s'est fermee dans le
dixieme.

### A.13 Reserves restantes, non engagees a s52

Les deux reserves ne bloquent aucune ligne : `MAT-CE1-06` et `FRA-CE1-25` sont `couvert-valide`.

- **`SYC-CE1-R1`** (`FRA-CE1-25`). Critere de levee, mot pour mot de la fiche, section 5 : *"le ballon et la
  force sont chacun portes par un sujet feminin ou sans genre (Elle lance le ballon. ; Cette femme est forte.
  avec costaude, ou Ce cheval est fort.). Les reponses ne changent pas, sauf l'accord. Rien d'autre ne
  change."* Items 19 et 23. Tarif 0,5 (`PAR-10`).
- **`MUL-CE1-R1`** (`MAT-CE1-06`). Critere de levee, mot pour mot de la fiche, section 6 : *"sur les six items,
  nommer l'autre facon dans la phrase et la placer apres la consigne de comptage. Par exemple : Compte les
  points ligne par ligne. Colonne par colonne, le total est le meme. Quelle multiplication ? (16 mots, trois
  phrases, la derniere est la question), et symetriquement sur les items colonne par colonne."* Six items sur
  seize. Tarif 0,5 (`PAR-10`).

Stories au backlog, section "Cloture de s52" : "Story de correction differee - reserve `SYC-CE1-R1`" et "Story
de correction differee - reserve `MUL-CE1-R1`".

**Une correction de 0,5 tient-elle sous 5,0 ? Arithmetiquement, oui, a pile : A 4,5 + 0,5 = 5,0, C 4,5 + 0,5 =
5,0. Je ne l'engage pas.** Trois motifs. (1) A 5,0 est le plafond, marge zero : une correction refusee se
rejoue a 0,5 de plus, soit 5,5, et le rejeu est impossible. La regle que `nadia-prof` a maintenue le jour meme
(`PAR-9`, A.14) dit, pour s53, que la marge de rejeu est dans le budget, pas apres lui ; s52 reste dans la
pratique, mais je n'ouvre pas une correction que la regle de s53 refuserait. (2) Les deux instances ont
chacune depense leur rejeu nomme (A.11) : engager une correction a 5,0, c'est l'engager sans rejeu. (3) Aucune
ligne ne ferme dessus et rien n'est faux pour l'enfant : `SYC-CE1-R1` est un partage de genre sur deux items,
`MUL-CE1-R1` une phrase qui gene la lecture sans fausser l'exercice. **Report compte : premier report des deux, a
s53.** Un second serait a la charge de la `DEC` de s53.

### A.14 Perimetre de s53 : propose, non ouvert

Ouvrir s53 est une decision a part, qui attend l'utilisatrice. Ce qui suit est ce que je lui proposerai, avec
le compte des reports tel qu'il serait si s53 n'engage rien. **Une regle nouvelle s'y applique** :
`nadia-prof` a maintenu `PAR-9` le 2026-10-06 (`ARBITRAGE-PREALABLE-s52-fra-ce1-questions-de-l-avis.md`,
section 4.3, commit `2205270c`) : *"a partir de l'ouverture de s53, une `DEC` qui ouvre deux instances compte 4,0
par instance marge de rejeu comprise, ou elle ecrit l'ecart et son motif."* La lecture "pratique depuis s41"
(8,0 de lots, rejeu en plus) ne vaut plus pour s53, et `PAR-11` reste non tranchee.

| Poste | Etat | Report s53 si non engage |
|---|---|---|
| Reprises `SCI-CM1-18` et `SCI-CM1-15` (paquets retires, entree a remettre apres un Done) : stories `S52-SCI-ACTIVITE-HUMAINE-CM1-REPRISE-APRES-RETRAIT` (0,5) et `S52-SCI-TERRE-CM1-REPRISE-APRES-RETRAIT` (1,0) | `PAR-2` : instance de sciences CM1 | **troisieme** (s51, s52) |
| `CRP-CM1-R1` a `R3` (0,5) | meme instance | **troisieme** |
| `SYC-CE1-R1` et `MUL-CE1-R1` (0,5 chacune) | A.13 | premier |
| Lignes de francais CE1 : 15 instruites et atteignables (`FRA-CE1-04` a `-15`, `-18`, `-19`, `-24`) ; `FRA-CE1-03` et `-21` plafonnees `en cours` | vivier, une instance A ; `PAR-13` | sans report (aucune story) |
| `MAT-CE1-14` (deux paquets, deux lots, `DEP-CE1-1` a `-4`) et moitie designation de `MAT-CE1-02` (`A3` d'`ADR-0080`) | instance C ; `MAT-CE1-02` moitie designation : s51, s52 | `MAT-CE1-02` : **troisieme** |
| `ART-CP-10` | `ADR-0104` (statut `Propose` sur le disque) ; `nadia-prof` a repondu a `C5` clause 1 le 2026-10-06 : *"C5 clause 1 ne mord pas sur une reproduction d'oeuvre qui constitue la proposition entiere, dans un item d'`ART-CP-10`"*, decision 1 tenue, repli non appele ; ses points 2 a 5 *"se tranchent sur un paquet, en jouant"* | s50, s51, s52 : **trois** deja ; la section 9 impose une issue a l'ouverture de s53 |
| Passe transverse `PAR-4` | non engagee | s51 puis s52 : **troisieme** ; la section 7 impose engagement ou abandon |
| `S51-OUTIL-JOURNAL-TOKENS` | non chiffree, `theo-archi` | **troisieme** |

**Ce que je propose de ne pas faire : trois reports d'affilee de plus.** Les cinq postes marques *troisieme*
(sciences, `CRP`, `MAT-CE1-02` designation, `PAR-4`, journal de tokens) et `ART-CP-10` ne se reportent plus
sans decision : a l'ouverture de s53, chacun est engage ou ecrit abandonne, avec son motif. **`ART-CP-10` : le
motif exterieur de sa suspension est leve** (reponse de `nadia-prof` a `C5`), il ne reste que le statut
d'`ADR-0104`, qui est a `theo-archi`. La seconde issue de la section 9 (*non couvrable en l'etat*) ne tient
plus sur ce motif ; je ne la reecris pas, et je ne chiffre pas ligne sans avis de faisabilite. Reprises de
sciences : deux stories, **2,0 au total avec `CRP`**, a mettre en regard de la condition (i) que `nadia-prof` a
opposee a une troisieme instance (4,0 requis) : une instance de sciences a 2,0 est sous la barre, ce qui veut
dire que les reprises sont soit portees par une des deux instances CE1 au prix de `PAR-2`, soit abandonnees. Je
ne tranche pas entre les deux ici ; c'est la question que je pose a l'ouverture.

### A.15 Ecarts a mon nom : traite, et ce qui reste

**Ecart "palier 2" de la matrice (`DEC-0110` section 6, `R10` du planning de s51, section 9 de cette decision) :
traite dans `MATRICE-couverture.md`.** Preuve par commande, ecrite dans les cellules : (1)
`grep -c 'mecanique: designation' adapters/adapter-content/src/main/resources/catalogue.yaml` rend 3
(`designation-frise-de-la-journee`, `designation-plan-de-la-classe`, `designation-symetrie-ce1`) : le palier 2
existe ; (2) `find adapters/adapter-web/src/main/resources/static/illustrations -iname '*carte*' -o -iname '*globe*' -o -iname '*planisph*' -o -iname '*france*' -o -iname '*monde*'`
ne rend aucun fichier sur 56 illustrations : **la limite reelle est le materiau, pas la mecanique**. Cellules modifiees :
`QLM-CP-12` et `HG-CM1-09` (*"palier 2 requis, non construit"* devient *"le palier 2 existe, la carte manque"*),
`HG-CM1-10` (meme limite de materiau, plus `DEC-0028`), `QLM-CP-10` (aucun motif auparavant : jouable, palier 1
illustre requis, `ADR-0073`, cout 2,0, `DEC-0099` section 4 ter). **Aucun statut ne change.** Je n'ai pas verifie
qu'un avis de `theo-archi` chiffre aujourd'hui une carte ou un globe : `DEC-0099` section 4 ter disait l'avis du a
`theo-archi` ; je n'en ai pas trouve de reponse. La huitieme cellule du meme ecart, `HDA-CM1-09`, dit *"palier 2 ... constructible"*
et n'affirme pas qu'il manque : non modifiee.

**Ecarts que je vois et ne traite pas**, par la consigne (je n'edite aucune autre ligne) : (a) les cellules de
`FRA-CE1-20` et `FRA-CE1-23` decrivent encore `TPS-CE1-R1` et `ALP-CE1-R1` comme ouvertes ; elles sont levees
(A.10). La note de tete l'ecrit, la cellule non. (b) L'introduction du tableau de mathematiques CE1 dit
*"Aucune ligne n'est encore `couvert-valide` ni `en cours`"*, fausse depuis s38. (c) A.7 : l'accord de
`ADR-0080` A2bis et des contraintes d'`ines-design` avec la barre de refus de `nadia-prof` n'a pas ete relu sur
le disque par moi.

### A.16 Changement de code et routage

**Aucun changement de code dans cet amendement.** Ce qu'il declenche, avec son proprietaire :

| A qui | Quoi |
|---|---|
| `sofia-sm` | Rendre compte de la cloture ; porter A.14 a l'utilisatrice pour l'ouverture de s53 ; affecter les quatre stories de la section "Cloture de s52" du backlog quand s53 s'ouvre |
| `nadia-prof` | Confirmer ou corriger le prix de 0,5 du rejeu de `SYM-CE1-R1` et la lecture de `PAR-14` (A.11) ; fixer le prix des deux reprises de sciences |
| `theo-archi` | Statut d'`ADR-0104` apres la reponse a `C5` ; comment `nadia-prof` joue un paquet sans entree de catalogue (stories de reprise) ; avis de faisabilite d'une carte ou d'un globe, si une ligne de la matrice doit s'y appuyer |
| `yanis-qa` | Criteres testables des quatre stories de la section "Cloture de s52" |

Statuts : la matrice est a jour pour les cinq fermetures et `MAT-CE1-02` (A.10) ; elle ne l'est pas pour les
deux cellules citees en A.15 (a).
