# DEC-0116 - Ouverture de s56 : option O3 (un lot de francais CE1, six reserves levees, le premier paquet a reproductions d'`ART-CP-10` ecrit par `lea-dev` puis audite), ecart de 0,25 accorde pour s56 seulement, second report de `S54-OBS-SCI-CONSIGNE`

- **Type** : decision d'ouverture de sprint (perimetre, capacite, ordre, reports, routage)
- **Date** : 2026-10-09
- **Auteur** : `camille-po`
- **Sprint** : s56
- **Competences programme visees** : `FRA-CE1-13` (lot `ce1-verbe-sujet`) et `ART-CP-10` (paquet a reproductions et audit) pour les deux fermetures possibles ; `FRA-CE1-14`, `FRA-CE1-12`, `EMC-CM1-03`, `EMC-CM1-06`, `EMC-CP-03` pour les six reserves (lignes deja `couvert-valide`, non rouvertes). Les autres postes (arbitrages, plafond, actions de retro) sont transverses - pas de contenu pedagogique. **Aucune ligne de matrice ne change par cette decision** : elle n'ouvre que des audits. Matrice a `c986adcf` : **141 / 182 (77,47 %)** ; au mieux **143 / 182 (78,57 %)**.
- **Statut** : **Acceptee le 2026-10-13 (amendement A.37)**, `Proposee` du 2026-10-09 au 2026-10-13. `DEC-0120` section 1 reprend l'ordre des matieres sous un motif de forme 1 par rang, ce que 0.5 et A.22 (ii) exigeaient. Etait `Proposee` parce que les mots de l'utilisatrice couvraient la composition (O3), l'ecart et le proprietaire du paquet, et non l'ordre des matieres (Q4).
- **Lien** : `DEC-0115` (A.4, A.10, A.15, A.18, A.22) ; `DEC-0114` (section 4, 7.2, A.20) ; `DEC-0112` section 6 ; `DEC-0062` (regles 3.1, 3.2, 3.5) ; `ADR-0104` (`Accepte`, tableau "Cout et decoupage") ; `ADR-0082` ; `ADR-0087` ;
  `squad/sprints/s55-francais-ce1-emc-cm1-et-reserves-servies/proposition-s56.md` (sections 1 a 9), `retro.md` (sections 3 et 5), `RAPPORT.md`, `cloture-nadia.md` ;
  `squad/conformite/ARBITRAGE-PREALABLE-s55-instance-a-francais-ce1.md` (sections 1.1, 2.3, 3.4, 3.5) ; `docs/backlog/backlog.md` ("Cloture de s55", "Sprint 56")

---

## 0. Provenance et methode

### 0.1 Les mots de l'utilisatrice, forme 2 de `DEC-0062` : `de l'utilisatrice`, le 2026-10-09

Reponses que l'agent qui m'a lance transmet comme directes de l'utilisatrice dans la conversation. **Je ne les ai pas lues de sa main et je ne peux pas les verifier depuis le depot** (meme reserve que `DEC-0115` A.22) : si elle les dement, les points qui en dependent redeviennent des questions, et le statut reste `Proposee`.

1. **Composition, options O1 a O3 de `proposition-s56.md` section 4** : "O3 avec ecart 0,25".
2. **Proprietaire du paquet a reproductions d'`ART-CP-10`, en tete de s56** : "lea-dev".
3. **Deja ecrits dans `proposition-s56.md` section 9, repris sans les redemander** : D2 "s56 prioritaire" (le paquet passe en tete de la composition) ; D4 "ok" (le francais CE1 continue sur l'instance A) ; D5 "ok" (les 11 lots restent decoupes en groupe X, 6, et groupe Y, 5) ; `Q5` acceptee, `DEC-0115` est `Acceptee` (A.22).

**Ce que ces mots fondent.** (a) La ligne O3 du tableau de la section 4 de `proposition-s56.md`, telle qu'elle l'ecrit : A = 1,0 + 1,75 + 1,0 + 0,25 (choix des oeuvres distractrices) = 4,0, le lot de 1,0 etant `-13`, cousin de `-12` (O1) ; B = 1,0 + 0,75 + 2,5 = 4,25 ; somme 8,25 ; *"depasse `PAR-9` de 0,25 sur B et la borne de 8,0 de 0,25"*. (b) Un ecart de 0,25 au-dessus de `PAR-9` pour cette composition. Ses mots ne disent pas la duree : `DEC-0114` A.20 la fixe, voir 2.2. (c) `lea-dev` ecrit le paquet.

**Ce que ces mots ne disent pas, et que cette decision n'ecrit donc pas comme decide** : l'ordre des matieres pour s56 (`Q4`) ; le decoupage du francais CE1 en deux instances (`Q3`) ; la mesure de pli de `LIS-2` (`Q6`, a `ines-design`) ; l'arbitrage de forme de 0,25, qui n'est pas dans la ligne O3 (2.3) ; l'instance qui porte les arts plastiques CP au regard de `PAR-2` (2.3, lecture de `nadia-prof`).

### 0.2 Sources de forme 1

`proposition-s56.md` (`camille-po`, sections 1 a 9) ; `retro.md` et `RAPPORT.md` de s55 (`sofia-sm`) ; `cloture-nadia.md`, `ARBITRAGE-PREALABLE-s55-instance-a-francais-ce1.md`, `ARBITRAGE-COMPLEMENT-s55-instance-a.md`, `ARBITRAGE-PREALABLE-s55-instance-b-emc-cm1.md` (`nadia-prof`) ; `confirmation-archunit.md` section 4, `ADR-0104` (`theo-archi`) ; `rapport-qualite.md` section 4 (`yanis-qa`) ; `DEC-0112`, `DEC-0114`, `DEC-0115` ; backlog "Cloture de s55" ; `MATRICE-couverture.md`. Les tarifs sont ceux de `nadia-prof` ; **je n'en ecris aucun**. Ses arbitrages prealables de s56 les confirment ou les changent et n'existent pas : `ls squad/conformite` suivi de `grep -c s56` rend 0 le 2026-10-09.

### 0.3 Mesures lancees pour cette decision (arbre `round-two` a `c986adcf`, worktree `s56-camille-dec`, 2026-10-09, perimetre : le worktree entier sauf mention)

- **Matrice** : commande de la "Methode du compte" de `MATRICE-couverture.md` (`awk` sur les 258 lignes de donnees) : `couvert-valide` 141, `en cours` 4, `non couvert` 37, `non couvrable en l'etat` 76, total 258, denominateur atteignable 182, 141 / 182 = 77,47 %.
- **Fichiers sans entree de catalogue** : `comm -23` des fichiers de `contenu/` et des `paquet:` de `catalogue.yaml` (commande de `proposition-s56.md` section 1) : **37** sur 182 fichiers ; meme chiffre qu'au 2026-10-08.
- **`ce1-verbe-sujet`** : `grep -c "paquet: ce1-verbe-sujet$"` sur `catalogue.yaml` rend 0 ; 24 items ; `git ls-files` suivi de `grep -c PaquetCe1VerbeSujetTest` rend 0 ; `FRA-CE1-13` est `non couvert`.
- **Paquet a reproductions** : `grep -lE '^ *bonneReponse: "oeuvre-'` sur `contenu/*.yaml` rend 0 fichier ; `ITEMS_A_PROPOSITIONS_EN_IMAGE = 0` ; 8 JPEG sous `static/oeuvres/` ; 8 entrees de registre ; `REPRODUCTIONS_SERVIES = 8`, `POIDS_TOTAL_MAXIMUM = 176_474`.
- **Plafond de 176 px** : mutation d'une ligne de `recreo.css` (`calc(11rem ...)` devient `calc(14rem ...)`, ligne 1780), `bash outils/mvn-jdk21.sh -B clean verify -pl bootstrap -am -Dmaven.test.failure.ignore=true`, perimetre : reacteur de `bootstrap` avec `-am`, toolchain `jdk` 21 : **0 echec, 0 erreur sur 2307 tests** (120, 18, 407, 1423, 333 et 6 d'integration), `BUILD SUCCESS`, en 2 min 35 s. Fichier restaure ensuite. C'est le constat de `theo-archi` (`confirmation-archunit.md` F4), refait.
- **Reserves** : les valeurs "aujourd'hui" de chaque story sont relues et ecrites dans le backlog ("Sprint 56") ; aucune n'est devenue verte depuis la cloture de s55, sauf `ACV-CE1-R1` et `EXC-R16` pour leur texte, que la cloture disait deja verts.
- **Lignee** : `bash outils/verifier-lignee.sh` sort 0 (la branche descend de `round-two`).

### 0.4 Ce que je ne reprends pas

`DEC-0115` section 3 (ordre des matieres, repris a s55 sous des motifs de forme 1 par rang) n'est **pas reprise ici** : voir 4.

### 0.5 Statut de cette decision

> **Amende le 2026-10-13 (A.37) :** `Acceptee`, par la seconde voie de cette section (reprendre l'ordre sous des motifs de forme 1), portee par `DEC-0120` section 1. Le texte ci-dessous est celui du 2026-10-09.

**`Proposee`.** Raison : `DEC-0114` 0.4 et `DEC-0115` 0.4 font passer une decision a `Acceptee` quand les mots de l'utilisatrice sur l'ordre sont ecrits en forme 2, ou quand la decision d'ouverture reprend l'ordre sous son propre motif de forme 1. Ni l'un ni l'autre ici : `Q4` est sans reponse (4) et je ne reprends pas l'ordre rang par rang. **Ce que `Proposee` ne retient pas** : rien de s56 n'attend `Q4`. La composition vient de ses mots (O3), l'ecart et le proprietaire du paquet aussi ; le reste porte des motifs de forme 1. Cette decision s'applique des maintenant, comme `DEC-0115` 0.5. Elle passe a `Acceptee` par un amendement qui cite les mots de l'utilisatrice sur `Q4`, ou qui reprend l'ordre sous des motifs de forme 1. **Un point reste ouvert qui n'est pas l'ordre : l'arbitrage de forme (2.3, `Q-U1`)**, dont l'ecart n'est pas couvert par ses mots.

---

## 1. Objectif et composition (O3)

**Objectif** : lever six reserves dont `CLM-CE1-R1` (1,0), brancher un lot de francais CE1 (`ce1-verbe-sujet`, `FRA-CE1-13`), ecrire puis auditer le premier paquet a reproductions d'`ART-CP-10` (la forme qui n'a aucune cousine). Au mieux deux lignes ferment : 141 / 182 a 143 / 182 (78,57 %), sous les verdicts de `nadia-prof` ; un Done refuse reste un refus.

**Instance A** (francais CE1) : arbitrage prealable (0,5) ; lot `-13` (1,0) ; `ACV-CE1-R1` (0,25), `ACV-CE1-R2` (0,5), `CLM-CE1-R1` (1,0, payee, `PAR-14`) ; choix des oeuvres distractrices du paquet (0,25) ; marge de rejeu (0,5). **Instance B** (EMC, arts plastiques CP) : arbitrage prealable (0,5) ; `EMC-CM1-R8`, `EMC-CM1-R9`, `EXC-R16` (0,25 chacune) ; audit du paquet (2,5) ; marge de rejeu (0,5).

**Pourquoi le lot est `-13`.** `proposition-s56.md` section 4 le lit cousin de `-12` ; l'arbitrage de `nadia-prof` de s55 (instance A, 1.1) liste sept conditions de francais CE1 qui n'ont jamais rencontre de contenu (`RCT`, `INF`, `DOC`, `PRN`, `FAM`, `OMP`, `RLP`) et les conditions de ce paquet (`VSJ-CE1-1` a `3`) n'en font pas partie ; elle juge `PAR-13` au niveau de la matiere (1.1). **La confirmer est a elle, dans `S56-ARB-A`.** Si elle remplace le paquet (candidat nomme par la proposition : `-15`, cousin de `-14`), la story change de nom, l'affectation et le tarif restent, sans amender cette decision. `-08` et `-09`, sans cousine, ne sont jamais dans le sprint ou une autre instance audite ce paquet (arbitrage A, 1.1).

**Ce que s56 n'engage pas** : les 10 autres lots de francais CE1 (11 - 1 ; arithmetique, hypothese de zero refus que le depot n'a pas verifiee : une instance, trois lots par sprint, s57 a s60 ; deux instances a partir de s57, s58, `Q3`) ; `G3` ; `G4` ; `G7` a `G11` dont `cm1-lire-et-restituer` (`FRA-CM1-01`, mesure de pli de `LIS-2` d'`ines-design` non faite, `Q6`) ; `cp-emc-personnes-differentes` (sous refus, `DEC-0081` section 7) ; `cm1-sciences-terre` et `cm1-sciences-activite-humaine` (refuses a s51, hors perimetre) ; `S54-OBS-SCI-CONSIGNE` (5).

---

## 2. Capacite et ecart

### 2.1 Arithmetique

Tarifs de `nadia-prof`, additionnes sans recompte. Aucun de ces nombres n'est mesure : ce sont des prix.

| Poste | A | B |
|---|---|---|
| Arbitrage prealable | 0,5 | 0,5 |
| Marge de rejeu, allouee a aucun lot | 0,5 | 0,5 |
| Lot `-13` | 1,0 | - |
| `ACV-CE1-R1` 0,25, `ACV-CE1-R2` 0,5, `CLM-CE1-R1` 1,0 | 1,75 | - |
| `EMC-CM1-R8`, `EMC-CM1-R9`, `EXC-R16`, 0,25 chacune | - | 0,75 |
| Choix des oeuvres distractrices | 0,25 | - |
| Audit du paquet a reproductions | - | 2,5 |
| **Total O3 (ligne de `proposition-s56.md`)** | **4,0** | **4,25** |
| Arbitrage de forme (`cloture-nadia.md` 4.4), sur B (2.3) | - | 0,25 |
| **Total avec l'arbitrage de forme** | **4,0** | **4,5** |

**Somme : 8,25 sans l'arbitrage de forme, 8,5 avec, sur la borne de 8,0 de `PAR-8`.** Plafond dur de 5,0 par instance : jamais approche (B 4,5). Lots : un par instance (`PAR-8`, au plus trois). A seule ne peut pas porter l'audit : 4,0 - 0,5 - 0,5 - 2,5 = 0,5, et ses reserves valent 1,75 ; A + audit = 5,25, au-dessus du plafond dur de 5,0 (`proposition-s56.md` section 4).

### 2.2 L'ecart accorde

**L'utilisatrice accorde, pour s56 seulement, un ecart de 0,25 au-dessus de `PAR-9` : somme de 8,25 sur la borne de 8,0, B a 4,25 sur 4,0.** C'est un ecart accorde, pas une jurisprudence (`DEC-0114` A.20) : la `DEC` d'ouverture de s57 compte selon `PAR-9` telle qu'ecrite, somme egale a 8,0, sans s'appuyer sur ce precedent ni sur celui de 9,5 de s54. Une troisieme instance demande son propre ecart.

**Motif de forme 1** (celui que `DEC-0114` A.20 exige d'un ecart) : le paquet a reproductions est le seul candidat de fermeture hors du francais CE1 (l'EMC CM1 est a 11 lignes `couvert-valide` sur 11 atteignables, `proposition-s56.md` section 1) ; son audit ne tient sur aucune instance sans depasser un plafond (A : 5,25, au-dessus du plafond dur de 5,0) ; sur B il tient a 4,25, sous le plafond dur.

### 2.3 Ce que les mots de l'utilisatrice ne couvrent pas

1. **L'arbitrage de forme de 0,25 n'est pas dans la ligne O3.** `nadia-prof` l'ecrit : *"Repondre a Q1 a Q6 est un arbitrage de forme : 0,25 a la DEC d'ouverture de s56, non consomme ici"* (`cloture-nadia.md` 4.4). C'est un poste neuf de s56, prealable au branchement du paquet (3.1), different du 0,25 de forme deja paye a s55 (`DEC-0115` A.2) et du 0,25 du choix des oeuvres. Je l'engage (sa story existe, `PAR-16`) et je le place sur B, instance qui audite le paquet : l'arbitrage et l'audit lisent le meme paquet (lecture de produit ; `PAR-2` est a `nadia-prof`). **Avec lui la somme est 8,5, soit un ecart de 0,5. Les mots de l'utilisatrice couvrent 0,25.** Les 0,25 en plus ne sont couverts par rien. Trois issues, **dont je n'ecris aucune** : (a) elle accorde 0,5 ; (b) `nadia-prof` l'absorbe dans le prix de son arbitrage prealable de B, ce qui est son chiffre ; (c) une reserve d'EMC de B (0,25 : `EMC-CM1-R8` ou `EMC-CM1-R9`) va a s57, premier report compte, et la somme revient a 8,25. **`Q-U1`.** Tant qu'elle est sans reponse, `sofia-sm` ne porte pas ces 0,25 comme alloues ; si la question est encore ouverte quand `nadia-prof` ecrit `S56-ARB-B`, ce document en dit le prix ou l'absorption, et `sofia-sm` le porte a l'utilisatrice avant l'ouverture du premier worktree du paquet. Le poste ne s'arrete pas : il est prealable au paquet, et sans lui l'audit glisse (3.3).
2. **`PAR-2` et le choix des oeuvres.** O3 met le choix des oeuvres distractrices sur A et l'audit sur B : les arts plastiques CP sont alors lus par deux instances dans le meme sprint, ce que `PAR-2` (*"une matiere et un niveau ne sont jamais partages dans un sprint"*, arbitrage A de s55, 2.3) interdit si elle le lit ainsi. A s55 toutes les arts plastiques CP etaient a A pour tenir la frontiere `ART-RES` / `ART-CP-10` ; `ART-RES` est `VALIDE`, la frontiere ne commande plus (`proposition-s56.md` section 4). **La lecture est a `nadia-prof`** (`Q2` de la proposition, sans reponse), dans `S56-ARB-A` et `S56-ARB-B`. Si elle place le choix des oeuvres sur B : A 3,75, B 4,5 (4,75 avec la forme), somme inchangee ; B reste sous le plafond dur mais depasse `PAR-9` de 0,5, au-dela de l'ecart accorde (B a 4,25) : `Q-U1` s'etend a ce cas.
3. **La duree de l'ecart** : fixee par `DEC-0114` A.20 (2.2), pas par ses mots.

### 2.4 Les autres regles, telles qu'elles s'appliquent

`PAR-14` : `CLM-CE1-R1` (24 items sur 24) est au tarif d'un lot (1,0) ; `ACV-CE1-R2` (3 items sur 24) reste au tarif de reprise (0,5, `PAR-10`). `PAR-13` : la forme d'`ART-CP-10` est le terrain neuf de s56 (B) ; le lot de A est cousin d'une condition servie (1). `PAR-11` : non tranchee, non appliquee ; l'audit de 2,5 porte deja le +0,5 que `nadia-prof` y met (arbitrage A de s55, 3.5). Un rejeu groupe de `ACV-CE1-R1` et `ACV-CE1-R2` coute-t-il moins que 0,75 ? Question de `proposition-s56.md` (`Q2`) a `nadia-prof`, dans `S56-ARB-A` ; **si oui, la difference n'est pas allouee ailleurs sans amendement**.

---

## 3. Le paquet a reproductions : prealables, ordre, ecrivain, calendrier

### 3.1 Ordre

1. **`DEC-0116` fusionnee sur `round-two`**, avant tout edit.
2. **Arbitrages prealables de `nadia-prof`** (`S56-ARB-A`, `S56-ARB-B`) et **avis de faisabilite de `theo-archi`** : prix confirmes, `PAR-2`, `PAR-13`, forme et prix du garde-fou du plafond, la question de corpus de la regle 2 de `nadia-prof` (des distracteurs qui partagent le sujet sans la propriete doivent exister en reproductions de provenance prouvee, *"question a `theo-archi` (T4)"*), la voie de la marque de l'echec.
3. **`S56-ART-CP-10-OEUVRES`** (A, 0,25) **avant la premiere ligne du paquet** : `ADR-0104` (tableau "Cout et decoupage") donne a T4 pour dependance *"le choix des paires par `nadia-prof`"* et a T5 *"T2 a T4, et l'arbitrage prealable de `nadia-prof`"*.
4. **`lea-dev`**, dans cet ordre, un seul worktree a la fois : `S55-ART-CP-10-PAQUET` (le fichier du paquet, T4, le registre : tout sauf l'entree de catalogue) ; `S55-ART-CP-10-PLAFOND` ; `S56-ART-CP-10-ECHEC` ; **l'entree de catalogue du paquet** (branchement) ; puis `S55-EMC-CM1-R8` et `S55-EMC-CM1-R9`.
5. **`S56-ART-CP-10-FORME`** (B, 0,25) : `Q1` a `Q6` de `cloture-nadia.md` 4.4, `Q7` d'`ines-design`, **avant l'entree de catalogue**, pas avant l'ecriture du fichier (*"avant le branchement"*).
6. **`S56-AUD-ART-CP-10`** (B, 2,5) apres l'entree de catalogue.

**Pourquoi `S56-ART-CP-10-ECHEC` et `S55-ART-CP-10-PLAFOND` precedent le branchement.** `nadia-prof` (`cloture-nadia.md` 4.2) : l'echec marque par la seule attenuation a 50 % (`E3`) est *"pas acceptable pour un paquet servi"* ; *"C'est une condition a remplir avant que le premier paquet a reproductions soit branche ; branche sans elle, il serait refuse sur ce motif."*. `theo-archi` (`confirmation-archunit.md` F4) : le plafond de 176 px est garde par rien, dette *"due au plus tard avec le premier paquet a propositions-images"*. **Ces deux postes n'avaient ni story ni proprietaire** ; `E3` etait routee a `lea-dev` *"rien a changer tant qu'aucun paquet n'est branche"*. Le paquet etant prioritaire, ils sont sur le chemin : cette decision les engage, avec leurs stories (backlog "Sprint 56"). **`S56-ART-CP-10-ECHEC` ne porte pas encore le critere de la marque** : il s'ajoute par amendement apres la reponse a `Q1` et avant l'ouverture de son worktree ; sans lui le worktree ne s'ouvre pas.

### 3.2 Qui ecrit le paquet

**`lea-dev`** (mots de l'utilisatrice, 0.1) : T4 (restaurer les reproductions et leurs entrees de registre, relever les deux constantes) et T5 (le fichier, l'entree de catalogue). Elle redige les items selon le document de `S56-ART-CP-10-OEUVRES` et les regles 1 a 6 de l'arbitrage de `nadia-prof` (3.4) ; les mots lus par l'enfant sont juges par `nadia-prof` (`CON-5`). La fiche de conformite est ouverte par `camille-po` apres le commit de l'entree de catalogue (`DEC-0115` A.11 : l'identifiant est celui du jeu au catalogue, choisi par le developpeur) ; `ADR-0104` la met dans T5 : c'est une frontiere de role, pas une decision technique. **Ce que je ne decide pas** : le decoupage en commits, la forme du garde-fou du plafond, la voie de la marque de l'echec, le choix des valeurs de T4 (l'avis de `theo-archi` de s54 ecrit 11 et 250 915 *"si les paires restent"*) : `theo-archi`, `lea-dev`, `ines-design`.

### 3.3 Contrainte de calendrier

**Le paquet (fichier, T4, registre) est ecrit et fusionne sur `round-two` avant toute autre livraison de `lea-dev`, puis l'entree de catalogue apres `S55-ART-CP-10-PLAFOND` et `S56-ART-CP-10-ECHEC`** (`git merge-base --is-ancestor`, backlog). `sofia-sm` inscrit a son planning l'heure a laquelle le paquet doit etre fusionne ; **au-dela, elle le dit a l'utilisatrice le jour meme**, sans attendre la cloture.

**Si l'entree de catalogue n'est pas fusionnee quand l'audit devrait commencer, l'audit (2,5) glisse : poste engage non livre, report compte** (`DEC-0114` section 4). Il serait le premier report de ce poste ; **la ligne `ART-CP-10` en porte quatre (s50 a s53) et deux engagements sans fermeture (s54, s55)** : `sofia-sm` le dit en ces termes, un report de l'audit ne repart pas de zero. Si le paquet est en retard, `S55-EMC-CM1-R8` et `S55-EMC-CM1-R9` passent a `marc-dev` par amendement du planning (precedent : `planning.md` de s55, justification 2) ; `S55-ART-CP-10-PAQUET` ne change pas de proprietaire sans l'utilisatrice, qui l'a nomme.

---

## 4. Ordre des matieres et questions restees sans reponse

- **`Q4`** (l'ordre de `DEC-0115` section 3 vaut-il pour s56 ?) : **sans reponse**. Je ne la tranche pas et je ne reprends pas l'ordre. Rien de s56 n'en depend (0.5). Pour s57, `proposition-s56.md` le dit : le rang 2 (EMC CM1) est epuise, le rang 3 (`G7` a `G11`) achete au plus une ligne (`FRA-CM1-01`).
- **`Q3`** (deux instances de francais CE1 a partir de s57) : **sans reponse**, ouverte depuis `DEC-0114` 7.2 (s54). Ce que s56 change : A prend le groupe X seule (`-13`), ce qui remplit la condition *"un sprint ou A prend X seule"* de `DEC-0114` 7.2 ; l'accord de `nadia-prof` sur `PAR-2` et l'avis de `theo-archi` sur `ADR-0065` restent a obtenir. **Je demande a `theo-archi` de dire, dans son avis de s56, si deux instances n'amendent pas `ADR-0065` en meme temps** : une demande d'avis, pas un engagement.
- **`Q6`** (mesure de pli de `LIS-2`, `ines-design`) : **sans reponse** ; non engagee, car aucun lot de `cm1-lire-et-restituer` n'est dans O3 (6, A15).
- **`Q-U1`** : 2.3. **`Q-U2`** : 5.

---

## 5. Reports comptes

| Poste | Reports avant | Issue a s56 |
|---|---|---|
| `S54-OBS-SCI-CONSIGNE` | ouverte a s54 sans report ; **premier report (s55)**, `DEC-0115` A.10 et A.15 | **non engagee : second report**, acte ci-dessous |
| `CLM-CE1-R1` | aucun (ouverte a la cloture de s55) | **engagee et payee, 1,0** : pas un report. Je ne la reporte pas par defaut (`proposition-s56.md` section 3) |
| `ACV-CE1-R1`, `ACV-CE1-R2`, `EMC-CM1-R8`, `EMC-CM1-R9`, `EXC-R16` | aucun | engagees |
| Paquet a reproductions, `S55-ART-CP-10-PAQUET` | option a s55 (`DEC-0115` A.15) : pas un report | engagee ; poste neuf |
| Audit du paquet | jamais engage | engage ; **non livre a la cloture = premier report**, avec l'historique de 3.3 |
| `S55-BR-FRA-CE1-L3` | option de s55, jamais engagee | devient `S56-BR-FRA-CE1-13` |
| 10 autres lots de francais CE1 | jamais engages | pas des reports ; s57 et suivants |

**Acte du second report de `S54-OBS-SCI-CONSIGNE`** (`DEC-0112` section 6 : un second report n'est permis qu'avec un acte ecrit ; il cite le premier). **Premier report** : s55, `DEC-0115` A.10, repris en A.15 ; ouverte a s54, sans report avant. **Second report : s56.** Motifs de forme 1 : (1) la composition de s56 est celle de ses mots (O3) a 8,25 sur 8,0 ; engager ce poste en plus demanderait un ecart que ses mots ne contiennent pas ; (2) sciences CM1 est a l'instance B (`PAR-2`), qui est a 4,25 (4,5 avec la forme) et porte l'audit du paquet ; (3) les criteres ne sont pas ecrits : *"a ecrire quand une instance l'engage, avec les mots de `nadia-prof`"* (backlog, s54), le prix n'existe pas ; (4) rien n'est faux pour l'enfant : la consigne dit deux fois *"puis reponds a la question"*, elle n'est pas fausse. **A s57, un troisieme report est interdit** (`DEC-0112` section 6) : la `DEC` d'ouverture de s57 l'engage (criteres de `nadia-prof`, proprietaire, prix) ou le retire par une issue ecrite. Je le dis maintenant pour que s57 ne le decouvre pas a son ouverture : `Q-U2`.

**`Q-U2`** (a l'utilisatrice, avant l'ouverture de s57) : `S54-OBS-SCI-CONSIGNE` est-elle engagee a s57 (une instance de sciences CM1 porte sa passe) ou retiree ? Je ne l'ecris pas comme sa decision.

---

## 6. Actions de la retro de s55 : A1 a A15

Engager une action de `theo-archi`, `yanis-qa`, `ines-design` ou du `relecteur-pedagogique` ne consomme aucun budget de `nadia-prof` : `PAR-16` ne s'y applique pas. Chacune a un proprietaire et un artefact.

| # | Action | Sort | Proprietaire, artefact |
|---|---|---|---|
| A1 | `DEC` d'ouverture de s56 | **faite** | cette decision |
| A2 | Mots de l'utilisatrice : `Q6` de `DEC-0115`, moment du paquet, ordre, deux instances | **partielle** : `Q6` et moment du paquet repondus (`DEC-0115` A.22 ; D2) ; `Q4` et `Q3` sans reponse (4) | utilisatrice |
| A3 | Pousser, lire la CI sur un commit nomme, job par job | **hors sprint, report compte** : `A2` de s54, `A3` de s55, s56 : **second report**. Motif : seule l'utilisatrice peut lire la CI distante, `gh run list` rendait `HTTP 404` depuis le poste de `sofia-sm` (`RAPPORT.md`) | utilisatrice ; verdict nomme |
| A4 | Ecrire la cause du rouge de CI que `a3606b2f` corrige | **engagee**, hors `PAR` | `theo-archi`, dans son avis de s56 ; commande ou message qui la reproduit |
| A5 | `S55-ART-CP-10-PLAFOND` poste prealable de tout paquet a reproductions | **engagee** (3.1) | `camille-po` (story, backlog), `theo-archi` (prix, forme), `lea-dev` ; ligne au planning de `sofia-sm` |
| A6 | Tout `.md` sous `squad/` ou `docs/` passe par `mvn-jdk21.sh -B clean verify` avant commit, ou par la classe d'`ADR-0087` | **engagee**, pratique de `sofia-sm` ; appliquee ici (10) | `sofia-sm` ; ligne "jobs lances" de ses rapports |
| A7 | Cinq ecarts de critere du rapport qualite | **faite pour quatre** (8) ; le cinquieme, le planning, est a `sofia-sm` et traite a la cloture de s55 | `camille-po` ; `yanis-qa` relit (A8) |
| A8 | Rejouer `Q-Y8` sur `2224e91e` ; mutation d'A7 ; morsures de T2 | **engagee**, hors `PAR` ; non livre a la cloture de s56 = report compte | `yanis-qa`, rapport qualite de s56 |
| A9 | Laquelle des lectures de la capacite de B (3,5 ou 4,0) vaut | **engagee**, ligne de `S56-ARB-B` | `nadia-prof`, `camille-po` |
| A10 | Verification de s54 (trois instances) contre les formes (a), (b), (c) | **non engagee a s56** : aucun poste de s56 ne demande trois instances (2 instances, 2.2). **Due avant toute `DEC` qui propose une troisieme instance** ; jamais engagee avant, donc pas un report | `nadia-prof` |
| A11 | En-tete de `FICHE-bandit-manchot-exclusion-cp.md` et titre du jeu | **engagee** avec `S55-EXC-R16`, sans prix propre | `nadia-prof` ; fiche, au rejeu |
| A12 | Ponctuation des 12 indices de `cm1-grands-nombres` | **engagee**, hors `PAR` | `relecteur-pedagogique` ; ligne dans `relecture-cloture.md` de s56, meme sans trouvaille |
| A13 | Lever `R4`, `F2`, `F3` quand le fichier est touche | **engagee, conditionnelle** : la citation de 2018 avec `S55-EMC-CM1-R9`, le commentaire de `manche.html` si `S56-ART-CP-10-ECHEC` le touche | `lea-dev` ; commits des rejeux |
| A14 | La formule de la forme 3 (`recu de MASTER, sans source dans le depot`) peut-elle etre gardee par un test ? | **engagee**, hors `PAR` : un avis ecrit | `theo-archi`, dans son avis de s56 ; test ou refus motive |
| A15 | Mesure de pli de `LIS-2` en tete de s56 si le lot est voulu | **non engagee** : le lot n'est pas dans O3 (`Q6`) ; jamais engagee, pas un report. A placer si s57 veut `cm1-lire-et-restituer` | `ines-design`, `camille-po` |

Les propositions de la section 6 de la retro (amendements de `squad/README.md`) sont a `sofia-sm`, `theo-archi` et a l'utilisatrice : non decidees ici.

---

## 7. Verification poste / story et affectation nominative (`PAR-16`)

`PAR-16` (arbitrage de `nadia-prof`, s34) : un poste sans story a l'ouverture du sprint n'est pas alloue contre sa borne. **Chaque poste ci-dessous a une story dans `docs/backlog/backlog.md`, section "Sprint 56", ou dans "Cloture de s55" pour les six reserves**, avec ses criteres mesures sur `c986adcf` ou renvoyes au backlog.

| Poste | Story | Instance | Agent nominatif | Prix |
|---|---|---|---|---|
| Arbitrage prealable de A | `S56-ARB-A` | A | `nadia-prof` | 0,5 |
| Lot `ce1-verbe-sujet` | `S56-BR-FRA-CE1-13` | A | `marc-dev` | 1,0 |
| `ACV-CE1-R1` | `S55-ACV-CE1-R1` | A | `nadia-prof` (rejeu) | 0,25 |
| `ACV-CE1-R2` | `S55-ACV-CE1-R2` | A | `marc-dev` | 0,5 |
| `CLM-CE1-R1` | `S55-CLM-CE1-R1` | A | `marc-dev` | 1,0 |
| Choix des oeuvres distractrices | `S56-ART-CP-10-OEUVRES` | A (sous `PAR-2`, 2.3) | `nadia-prof` | 0,25 |
| Arbitrage prealable de B | `S56-ARB-B` | B | `nadia-prof` | 0,5 |
| `EMC-CM1-R8` | `S55-EMC-CM1-R8` | B | `lea-dev` | 0,25 |
| `EMC-CM1-R9` | `S55-EMC-CM1-R9` | B | `lea-dev` | 0,25 |
| `EXC-R16` | `S55-EXC-R16` | B | `nadia-prof` (rejeu) | 0,25 |
| Audit du paquet | `S56-AUD-ART-CP-10` | B | `nadia-prof` | 2,5 |
| Arbitrage de forme | `S56-ART-CP-10-FORME` | B | `nadia-prof`, avec `ines-design` et `theo-archi` | 0,25 hors ecart (2.3) |
| Paquet, T4 et T5 | `S55-ART-CP-10-PAQUET` | hors `PAR` | `lea-dev` | 0,0 |
| Plafond de 176 px | `S55-ART-CP-10-PLAFOND` | hors `PAR` | `lea-dev` ; prix et forme `theo-archi` | 0,0 |
| Marque de l'echec | `S56-ART-CP-10-ECHEC` | hors `PAR` | `lea-dev` | 0,0 |
| Marges de rejeu, 0,5 par instance | aucune | A et B | aucun | allouee a aucun lot |

**Ordre de `marc-dev`, propose a `sofia-sm`** : `S56-BR-FRA-CE1-13` (ferme une ligne), `S55-ACV-CE1-R2`, `S55-CLM-CE1-R1`. **Ordre de `lea-dev`, contraint** : 3.1, point 4. Aucun des deux ne tient deux worktrees a la fois. **Aucun poste sans story, aucun sans affectation nominative.**

---

## 8. Criteres corriges : A7 de la retro de s55

Quatre ecarts que `rapport-qualite.md` (section 4) et la retro (A7) nomment sans ambiguite. Je les ai remesures sur `c986adcf` avant de les ecrire. Corriges dans le backlog, chacun a la place du critere concerne, sans reecrire le texte d'origine.

| Ecart | Ce que le critere disait | Mesure du 2026-10-09 | Correction |
|---|---|---|---|
| `Q-Y6` | sur tout le catalogue la commande rend 2 | `grep -cP '^ *(titre\|consigne): ".* [?:;!]'` sur `catalogue.yaml` : **23** | 23, les 23 lignes `titre` et `consigne` de l'entree `CATALOGUE` du cliquet de `TYP-1` ; la commande bornee a l'entree neuve (0) etait juste |
| `Q-Y9` | `git grep -c QC4` rend 1 fichier | `git grep -l QC4 -- squad/conformite` : **2 fichiers** | la condition est que `ARBITRAGE-QC4-s55.md` en fasse partie ; "exactement un" n'est pas le critere |
| `Q-Y10` | refus de `--sprint` ligne 495, refus du dossier ligne 483 | `grep -n` sur `outils/rapport-tokens.py` : `--sprint` ligne **463**, dossier lignes **481 a 492** | numeros d'apres `A8` ; le critere (sortie 2, `--sprint` d'abord) est inchange |
| `C7` / `EMC-9` | une ligne "relu" sur `EMC-9` pour les deux paquets d'EMC | `ARBITRAGE-PREALABLE-s55-instance-b-emc-cm1.md` 1.2 : `EMC-9` est bornee a une ligne et **sans objet** sur `-03` et `-06` | le developpeur relit `EMC-4`, `EMC-8`, la regle de s53 4.1 et, pour `-06`, `BIA-5` ; `rapport-lea-dev-emc.md` section 5 les porte |

`Q-Y8` (le sixieme ecart de la retro) a ete livre apres le rapport qualite (`2224e91e`) ; sa mutation rejouee par `yanis-qa` est `A8` de la retro (6). Le planning de s55 porte encore les anciennes commandes : il est a `sofia-sm` et la retro le dit traite. `yanis-qa` relit ces quatre corrections.

---

## 9. Routage (changement de fichiers et de code)

**Cette decision implique du code** (`S55-ART-CP-10-PAQUET`, `S55-ART-CP-10-PLAFOND`, `S56-ART-CP-10-ECHEC`, `S56-BR-FRA-CE1-13`, trois reserves de texte). **Chaque poste a un proprietaire nomme (7) et une story au backlog dans le meme commit** : la decision est routee a `sofia-sm`, pas seulement ecrite.

| A qui | Quoi |
|---|---|
| `sofia-sm` | Fusionner cette decision avant tout worktree ; planning de s56 : les affectations de 7, l'ordre de `lea-dev` de 3.1, **l'heure de fusion du paquet** (3.3), les lignes des postes sans prix de `nadia-prof` (A4, A12, A14) ; branche, worktree et scratchpad de chaque agent avant son premier edit ; aucune troisieme instance ; porter a l'utilisatrice la question `Q-U1` si elle est encore ouverte a `S56-ARB-B` |
| `nadia-prof` | `S56-ARB-A`, `S56-ARB-B` : tarifs, `PAR-2` (2.3), `PAR-13` sur `-13`, rejeu groupe, A9 ; les conditions de `ce1-verbe-sujet` ; `S56-ART-CP-10-OEUVRES` ; `S56-ART-CP-10-FORME` ; l'audit ; les rejeux. Je n'ecris aucun de ces points |
| `theo-archi` | Avis de faisabilite de s56 : prix et forme du plafond ; question de corpus de la regle 2 ; voie de la marque de l'echec ; A4 ; A14 ; `ADR-0065` et deux instances (4) |
| `ines-design` | `Q1` (proposition de marque) et `Q7` (releve a dix largeurs sur le gabarit livre) de `cloture-nadia.md` 4.4 ; propose, ne tranche pas |
| `marc-dev` | `S56-BR-FRA-CE1-13`, `S55-ACV-CE1-R2`, `S55-CLM-CE1-R1` ; `rapport-marc-dev.md` du dossier de s56 |
| `lea-dev` | 3.1, point 4 ; `S55-EMC-CM1-R8`, `S55-EMC-CM1-R9` ; A13 ; `rapport-lea-dev.md` du dossier de s56 |
| `yanis-qa` | Lecture de testabilite des criteres du backlog "Sprint 56" avant la premiere livraison ; A7 relue ; A8 |
| `relecteur-pedagogique` | A12 ; relecture de fin de sprint |
| `camille-po` | Fiches de conformite apres chaque commit de branchement (`-13`, paquet a reproductions) ; amendements des criteres de `S56-BR-FRA-CE1-13` et `S56-ART-CP-10-ECHEC` avec les mots de `nadia-prof` ; matrice au verdict seulement |
| Utilisatrice | `Q-U1`, `Q-U2`, `Q4`, `Q3`, `Q6` ; A3 |

---

## 10. Ce que cette decision ne fait pas

- Elle ne prononce aucun Done pedagogique, ne fixe aucun tarif ni plafond, ne tranche ni `Q4`, ni `Q3`, ni `Q6`, ni l'arbitrage de forme (2.3), ni la lecture de `PAR-2` ou de `PAR-13`.
- Elle ne tranche aucune question technique : decoupage en commits, forme du garde-fou, voie de la marque, valeurs de T4 (3.2).
- Elle ne touche ni `catalogue.yaml`, ni un paquet, ni une fiche, ni du code, ni la matrice (141 / 182, aucun statut ne bouge), ni le planning de s56.
- Elle n'ouvre aucun worktree d'agent, ne fusionne rien, ne pousse rien. Les fiches de conformite de `-13` et du paquet a reproductions ne sont pas ouvertes : l'identifiant du jeu n'est pas choisi (`DEC-0115` A.11).
- Elle ne dit pas que la CI distante est verte : personne ne l'a lue (`RAPPORT.md`, A3 en 6).
- **Jobs de `ci.yml` lances pour ce commit** : voir le message de livraison. La mutation de 0.3 est un `build` partiel sur le reacteur de `bootstrap` avec `-am`, sur l'arbre sans les fichiers de ce commit ; elle n'est pas la porte.

## Decisions prises

1. s56 ouvre sur l'option O3, sur les mots de l'utilisatrice (0.1) : A = `ce1-verbe-sujet` (`FRA-CE1-13`), `ACV-CE1-R1`, `ACV-CE1-R2`, `CLM-CE1-R1` (payee, pas de report), choix des oeuvres distractrices ; B = `EMC-CM1-R8`, `EMC-CM1-R9`, `EXC-R16`, audit du paquet a reproductions d'`ART-CP-10`.
2. Ecart de 0,25 au-dessus de `PAR-9` accorde pour s56 seulement, somme 8,25 sur 8,0, ecrit comme un ecart et non comme une jurisprudence (`DEC-0114` A.20). L'arbitrage de forme de 0,25 n'est pas couvert (8,5) : `Q-U1`.
3. `lea-dev` ecrit le paquet (T4, T5). Ordre contraint : paquet, plafond, marque de l'echec, entree de catalogue, puis les deux reserves d'EMC ; l'audit glisse si l'entree de catalogue n'est pas fusionnee, report compte, signale le jour meme.
4. `S55-ART-CP-10-PLAFOND` et `S56-ART-CP-10-ECHEC` sont engagees, prealables au branchement ; le critere de la marque s'ajoute par amendement avant l'ouverture du worktree.
5. `S54-OBS-SCI-CONSIGNE` : second report, acte ecrit en 5 ; troisieme interdit, `Q-U2`.
6. Actions A1 a A15 de la retro de s55 : sort dans le tableau de 6 (neuf engagees, trois faites ou partielles, deux non engagees sans report, une reportee et comptee).
7. Quatre ecarts de critere corriges au backlog (8).
8. Statut : `Proposee` (0.5). `Q4`, `Q3`, `Q6` non tranchees.

---

## Amendement du 2026-10-09 (s56, `camille-po`) : reponses a `Q-U1` et `Q-U2`, statut inchange

Annotation datee. Rien au-dessus n'est reecrit ; les lignes remplacees sont nommees ci-dessous. Forme 2 de `DEC-0062` : `de l'utilisatrice`, le 2026-10-09, mots cites. Ils me parviennent par l'agent lanceur, qui les atteste reponses directes ; je ne les ai pas lus de sa main (meme reserve que 0.1).

### A.1 `Q-U1` : "Ecart de 0,5"

Mots : **"Ecart de 0,5"**. Je les rattache a l'issue (a) de 2.3 : **ecart total de 0,5 au-dessus de `PAR-9` pour s56 seulement**, soit 0,25 pour l'audit du paquet (O3) et 0,25 pour l'arbitrage de forme. Somme **8,5 sur 8,0**, B a **4,5** sur 4,0, A a 4,0. Le rattachement est le mien ; ses mots ne citent pas la ventilation.

- C'est un ecart accorde, pas une jurisprudence (`DEC-0114` A.20) : la `DEC` d'ouverture de s57 compte selon `PAR-9` telle qu'ecrite, 8,0. Le motif de forme 1 est celui de 2.2, inchange.
- `S56-ART-CP-10-FORME` (0,25, B) est desormais couvert : ce n'est plus un poste "hors de l'ecart". Plafond dur de 5,0 : B a 4,5, non approche.
- **Ce que ses mots ne couvrent pas** : la lecture de `PAR-2` sur le choix des oeuvres (2.3, point 2). Si `nadia-prof` le place sur B, B monte a 4,75, au-dela de 4,5 : un amendement et une question de plus. La lecture reste a `nadia-prof`, dans `S56-ARB-A` et `S56-ARB-B`.
- **Remplace** : en tete, la mention "ni l'arbitrage de forme de 0,25 (2.3)" du statut ; 0.1, derniere phrase du paragraphe "ce que ces mots ne disent pas", point sur l'arbitrage de forme ; 2.3 point 1 (les trois issues, `Q-U1`) : l'issue (a) est choisie ; 2.1, ligne "Total avec l'arbitrage de forme" : 4,0 et 4,5, somme 8,5, couverte ; section 3.3 et section 9 (`sofia-sm` ne porte plus les 0,25 comme non alloues).

### A.2 `Q-U2` : "Reporter a s57, abandon ou placement ecrit alors"

`S54-OBS-SCI-CONSIGNE` **reste reportee** : le second report de la section 5 est confirme. **A s57 un troisieme report est interdit** (`DEC-0112` section 6) : la `DEC` d'ouverture de s57 la place (criteres de `nadia-prof`, proprietaire, prix) ou l'abandonne, par un acte ecrit. `Q-U2` est close. Rien n'est a faire sur ce poste a s56.

### A.3 Statut, routage, limites

- **Statut : `Proposee`, inchange.** `Q4` (ordre des matieres) reste sans reponse et n'est pas reprise sous un motif de forme 1 (0.5). Le point que 0.5 nommait comme second point ouvert, l'arbitrage de forme, est ferme par A.1.
- Routage : `sofia-sm` porte 8,5 sur 8,0 au planning, B a 4,5 ; `nadia-prof` ecrit `S56-ARB-B` avec le prix de la forme dans ce total. Aucun code ni contenu change par cet amendement ; la matrice reste a 141 / 182. Ne fixe aucun tarif, ne prononce aucun Done.

---

## Amendement du 2026-10-09 (s56, `camille-po`, troisieme) : B a 4,75, capacite en attente, criteres des postes, constat de forme 3

Annotation datee. Rien au-dessus n'est reecrit ; les lignes remplacees sont nommees. Sources lues : `ARBITRAGE-PREALABLE-s56-instance-a-francais-ce1.md` (`nadia-prof`, instance A), `ARBITRAGE-PREALABLE-s56-instance-b-art-cp-10-et-emc-cm1.md` (instance B), `avis-faisabilite.md` de s56 (`theo-archi`). Le detail des criteres est au backlog, "Sprint 56", annotation du meme jour.

### A.4 "Oui, a 4,75"

Forme 2 de `DEC-0062` : `de l'utilisatrice`, le 2026-10-09, mots recus par l'agent lanceur qui les atteste (meme reserve que 0.1 et A.1). Mots : **"Oui, a 4,75"**, en reponse a la question `U1` de l'instance A (section 9 de son arbitrage) et de l'instance B (D4, 2.3) : l'ecart de 0,5 vaut-il quelle que soit sa repartition entre les instances, B montant a 4,75 ?

- **L'ecart de 0,5 est valable quelle que soit la repartition** ; B est a **4,75**, sous le plafond dur de 5,0 (`PAR-8`). Il reste un ecart accorde pour s56 seulement, pas une jurisprudence (`DEC-0114` A.20, A.1).
- **`PAR-2` : le choix des oeuvres distractrices (`S56-ART-CP-10-OEUVRES`, 0,25) est sur B.** Les deux instances le lisent ainsi (arbitrage A 3.1, arbitrage B 2.1). Story, prix, regles et ordre inchanges. **Remplace** : 2.1 (colonne A : "Choix des oeuvres distractrices" 0,25 passe en colonne B), 2.3 point 2 (la lecture est rendue ; la question qu'il posait est close), section 7 (instance de `S56-ART-CP-10-OEUVRES` : B), et la phrase de A.3 "B a 4,5" (B a 4,75).
- Le point ouvert de A.1 ("si `nadia-prof` le place sur B, B monte a 4,75 ... un amendement et une question de plus") est ferme par ces mots. **Le document des oeuvres peut s'ecrire** : l'instance B l'attendait (arbitrage B 2.3).

### A.5 Capacite : deux lectures de `nadia-prof`, ligne en attente

| Lecture | A | B | Somme |
|---|---|---|---|
| Instance B (arbitrage B, 2.2 et 5), rejeu de `ACV-CE1-R1` et `R2` separe | 3,75 | 4,75 | 8,5 |
| Instance A (arbitrage A, 3.2 et 8), rejeu groupe 0,5 | 3,5 | 4,75 | 8,25 |

**Les deux sont ecrites, aucune n'est tranchee** : une reconciliation est en cours chez l'instance B ; l'arbitrage A la demande aussi (section 6). **Cette ligne de la decision est en attente**, et `sofia-sm` ne porte pas un total de A a la review avant qu'elle soit close. Sous les deux la somme reste sous 8,5. **Remplace** : 2.1, ligne "Total O3" colonne A (4,0) et la somme de A.1 pour A (4,0) ; les 0,25 ou 0,5 non alloues sur A ne sont alloues a rien sans amendement (2.4). **A9** : les deux instances ecrivent 3,5 consommes pour B a s55 ; ligne close, comme la cloture de s55 la comptait.

### A.6 Criteres des postes et `A10`

Ecrits au backlog, "Sprint 56", annotation du 2026-10-09 : conditions `J1` a `J5` et onze proprietes decidables de `S56-BR-FRA-CE1-13` (le worktree de `marc-dev` peut s'ouvrir) ; `P1` a `P6` de `S55-CLM-CE1-R1` ; rejeu groupe de `ACV-CE1-R1` et `ACV-CE1-R2` a 0,5 (0,25 pour `R1` seule si `R2` n'est pas livree) ; quatre constantes, decoupage en commits, ordre et `B1` a `B14` de `S55-ART-CP-10-PAQUET` ; forme du garde-fou et sept morsures de `S55-ART-CP-10-PLAFOND`, **`PLAFOND` avant `ECHEC`**. Les morsures P1 a P6 sont toutes vertes sur l'existant (0 echec sur 2307), mesure du jour. **Le critere de la marque de `S56-ART-CP-10-ECHEC` n'est pas ecrit** : `nadia-prof` le juge (`Q1`), son worktree ne s'ouvre pas, section 3.1 inchangee. **`A10`** (0,5) : non engagee ; due avant la `DEC` d'ouverture de s57 si `Q3` est acceptee ; ne tient a s56 que dans la lecture de l'instance A et par un amendement que je n'ecris pas.

### A.7 Constat pour la retro de s56 : une troisieme occurrence possible de la forme 3 sans formule

`theo-archi` (avis de s56, section 8) signale que `DEC-0114`, lignes 13, 52 et 79, etiquette des sources "forme 3" sans la formule `recu de MASTER, sans source dans le depot`. **Verifie par moi** : lignes 13 (statut : *"n'avait que la forme 3 de `DEC-0062` pour source"*), 52 (*"Borne de la forme 3"*) et 79 (*"n'a pas d'autre source que la forme 3"*) portent l'etiquette ; la formule, blancs normalises, n'apparait **0 fois** dans le fichier, qui compte 19 occurrences de "forme 3". Avec `R2` de s53 et `R1` de s55, ce serait la troisieme occurrence en trois sprints. **Je ne la corrige pas** (`DEC-0062` section 7 : pas de balayage retroactif) et je ne la classe pas : distinguer une attribution d'une mention de la forme est le jugement du `relecteur-pedagogique` (`DEC-0062` section 4). Route : retro de s56, `sofia-sm` ; verification : `relecteur-pedagogique`. `theo-archi` refuse un test (47 fichiers sur 65 rouges) ; le refus et sa condition de revision (une convention structurelle, amendement de `squad/README.md` section 2 bis et de `DEC-0062`) sont a `sofia-sm`, `camille-po` et l'utilisatrice.

### A.8 Statut, routage, limites

- **Statut : `Proposee`, inchange.** `Q4` reste sans reponse et n'est pas reprise (0.5). Aucun mot de l'utilisatrice n'est cite pour `Q4`, `Q3`, `Q6`.
- Routage : `sofia-sm` (planning : B a 4,75, A sous reserve de la reconciliation, `S56-ART-CP-10-OEUVRES` sur B) ; `nadia-prof`, instance B (document des oeuvres, critere de la marque, reconciliation) ; `marc-dev` (`S56-BR-FRA-CE1-13`, ses conditions sont ecrites) ; `yanis-qa` (quelles proprietes un test de paquet porte ; test des verbes du premier groupe) ; `relecteur-pedagogique` (A.7).
- Aucun code ni contenu change par cet amendement ; la matrice reste a 141 / 182. Ne fixe aucun tarif, ne prononce aucun Done, n'ecrit pas le critere de la marque.

---

## Amendement du 2026-10-09 (s56, `camille-po`, quatrieme) : critere de la marque, plafond, deux amendements d'`ADR`, paquet, capacite reconciliee

Annotation datee. Rien au-dessus n'est reecrit ; les lignes remplacees sont nommees. Arbre : `round-two` a `c47582f8`. Sources lues : `ARBITRAGE-s56-forme-art-cp-10.md`, `CHOIX-OEUVRES-s56-art-cp-10.md`, `ARBITRAGE-PREALABLE-s56-instance-b-art-cp-10-et-emc-cm1.md` (bloc R.1 a R.3) de `nadia-prof` ; l'amendement du 21:55 de l'avis de `theo-archi` ; la proposition d'`ines-design`. Le detail est au backlog, "Sprint 56", annotation du meme jour.

### A.9 Critere de la marque (O1) : ecrit

`nadia-prof` a juge `Q1` (bloc du 21:37) : **O1 est retenue**, une pastille portant une croix, `::before` de `.proposition.deja-essayee`, sous la case ; O2 (cadre en tirets sur l'image) est recevable en repli. `theo-archi` dit la voie faisable **moyennant l'amendement d'`ADR-0026` clause 3** (A.10). Le critere de `S56-ART-CP-10-ECHEC` est ecrit au backlog : huit proprietes recopiees mot pour mot, dont le critere 7 du bloc du 22:07, qui remplace celui du 21:37 ; neuf mutations `E1` a `E9` de `theo-archi`. **Le worktree d'`ECHEC` s'ouvre** quand `S56-ADR-0026` et `S55-ART-CP-10-PLAFOND` sont fusionnes et `S56-ART-CP-10-FORME` ecrit. **Remplace** : 3.1 et A.6, la phrase "le critere de la marque n'est pas ecrit" ; l'ordre de 3.1 ("`PLAFOND` ; `ECHEC`") se lit avec `S56-ADR-0026` entre les deux. Valeurs d'aujourd'hui : criteres 1, 3, 4, 5 rouges (aucune marque), 2, 7, 8 verts et nommes comme des bornes, 6 non mesure par moi.

### A.10 Deux stories neuves, hors `PAR`, pour `theo-archi`

**`S56-ADR-0026`** (amender `ADR-0026` clause 3 : une regle dont le sujet est un pseudo-element decoratif sort de la clause) et **`S56-ADR-0024`** (`K1` : `nadia-prof` a choisi (b), les six `explication` du paquet restent ; `ADR-0024` est amende pour qu'un paquet sans graphie la porte sur tous ses items ou sur aucun). Chacune : environ 0,25 j de `theo-archi`, amendement et test dans un seul commit, 0,0 pour `nadia-prof`, **avant les worktrees du paquet et de la marque**. Criteres mesures sur l'existant le 2026-10-09 : `LAnimationRetardeMaisNeDetermineRienTest` avec la regle de la pastille, **1 echec sur 27** ; `ChaquePaquetExpliqueSesItemsAVariationTest` avec le paquet des six items, **1 echec sur 5** ; les deux doivent rendre 0. **Ces deux postes n'avaient pas de ligne au planning** (`theo-archi` le dit) : cet amendement leur donne leur story, au sens de la regle des decisions qui impliquent du code ; `sofia-sm` les place. **Ajoute** aux postes de la section 7 : deux lignes, hors `PAR`, `theo-archi`, 0,0.

### A.11 `S55-ART-CP-10-PLAFOND` : la mutation du calc ne vaut plus

La forme d'`ines-design` porte le plafond sur l'image (`min(100%, 11rem)`) : la mutation de la ligne 1780, que le backlog posait en critere, ne rend legitimement plus aucun echec. **Remplacee** par `P1'` a `P5'` de `theo-archi` (cinq morsures, quatre clauses). Mes mesures de l'amendement precedent (`P1` a `P6`, 0 echec sur 2307 chacune) portaient sur la feuille d'avant et ne valent plus comme critere. **Remplace** : 0.3, ligne "Plafond de 176 px" (la mutation du calc comme critere) ; le critere 2 de la section 7 du backlog pour cette story.

### A.12 Paquet : `B4` tranchee, `K2`, `K3`

`B4` est tranchee par `nadia-prof` : vivier de 28 (`AV2`), le Vermeer reste, **six items, quatre reproductions restaurees**, T4 de **275 163 octets** ; compteurs 0 vers 6 (`ITEMS_A_PROPOSITIONS_EN_IMAGE`), 81 vers 87 (`ITEMS_QUI_DECLARENT_UNE_ILLUSTRATION`), 8 vers 12 (`REPRODUCTIONS_SERVIES`), 176 474 vers 275 163 (`POIDS_TOTAL_MAXIMUM`). **Remplace** 3.2 (*"l'avis de `theo-archi` de s54 ecrit 11 et 250 915"*, valeurs a remesurer). **`K2`** (une entree de `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE`, ajout et non assouplissement : 3 echecs sur 28 sans elle, mesure du jour) et **`K3`** (deux morsures de `LIllustrationDeContenuResoutEtAucuneNeDortTest` a nourrir des propositions reelles, 2 echecs mesures par `theo-archi`) entrent dans le commit du paquet. **A l'audit**, `nadia-prof` lit les six explications a l'ecran ; une explication absente est un motif de refus.

### A.13 Capacite : reconciliee a 8,25

**Constat, remplace A.5 sur son seul etat "en attente".** `nadia-prof`, instance B (bloc R.3), a reconcilie avec l'instance A : **A 3,5, B 4,75, somme 8,25**. Les deux instances ecrivent maintenant le meme total ; le 3,75 de A et la somme de 8,5 qui en decoulait prenaient les deux rejeux d'`ACV-CE1` au prix separe. **0,25 n'est alloue a aucun poste** (8,25 sur les 8,5 que l'ecart permet) et **aucun poste ne s'y engage sans amendement** (2.4). A.5 reste ecrit, sa ligne n'est plus ouverte. Remplace aussi, dans A.6, la lecture de l'instance A qui faisait tenir `A10` a s56 : elle ne vaut que par un amendement que je n'ecris pas.

### A.14 Statut, routage, limites

- **Statut : `Proposee`, inchange.** `Q4` reste sans reponse (0.5). Aucun mot de l'utilisatrice nouveau.
- Routage : `sofia-sm` (planning : les deux stories d'`ADR`, l'ordre, B a 4,75 et A a 3,5, 0,25 sans poste) ; `theo-archi` (`S56-ADR-0026`, `S56-ADR-0024`, garde de la marque, garde du plafond) ; `lea-dev` (paquet, plafond, marque, `K2`, `K3`) ; `yanis-qa` (traduction des criteres de la marque en test, rejeu des mutations) ; `nadia-prof` (audit, six explications).
- Aucun code ni contenu change par cet amendement ; la matrice reste a 141 / 182. Ne prononce aucun Done, ne fixe aucun tarif.

---

## Amendement du 2026-10-11 (s56, cloture, `camille-po`, cinquieme) : verdicts, statut tenu a `Proposee`, notes de cloture, proposition (D) sur le catalogue, candidat de s57

Annotation datee. Rien au-dessus n'est reecrit ; les lignes remplacees sont nommees. Arbre : `round-two` a `7130dec6`. Sources lues : `cloture-nadia.md` et `confirmation-archunit.md` du dossier de s56, `FICHE-bandit-manchot-verbe-sujet-ce1.md`, `FICHE-bandit-manchot-rapprocher-deux-oeuvres-cp.md`, `planning.md` (amendement du 22:17), `avis-faisabilite.md`, `DEC-0045` section 3, `DEC-0055`, `DEC-0009`, `DEC-0049`, `DEC-0111` section 3. **Aucun mot nouveau de l'utilisatrice** : ce qui suit ne cite aucune reponse d'elle, et la proposition A.17 n'est pas sa decision.

### A.15 Verdicts de s56 et matrice

| Poste | Ligne | Verdict de `nadia-prof` | Matrice |
|---|---|---|---|
| `S56-BR-FRA-CE1-13` | `FRA-CE1-13` | **VALIDE SOUS RESERVE** ; `VSJ-CE1-R1` non bloquante | `non couvert` -> **`couvert-valide`**, sans mention de couverture partielle (la fiche l'ecrit) |
| `S56-AUD-ART-CP-10` | `ART-CP-10` | **REFUSE** : a 744 x 551, a la revelation, la phrase de `M4` est entierement sous le pli (y = 626 a 656, pli a 551), il manque au moins 105 px ; reserves `RDO-CP-R1`, `RDO-CP-R2` | reste **`non couvert`**, colonne des jeux vide, aucune mention de s50 |
| six reserves de s55 (`ACV-CE1-R1`, `-R2`, `CLM-CE1-R1`, `EMC-CM1-R8`, `-R9`, `EXC-R16`) | lignes deja `couvert-valide` | **LEVEES** | aucun statut ne bouge |

**Compte, methode et perimetre** : commande de la "Methode du compte" de `MATRICE-couverture.md` (`awk` sur les lignes de donnees dont la deuxieme cellule est un niveau, statut lu dans la septieme), lancee le 2026-10-11 sur les 258 lignes, aucune mesure partielle. **Avant** le geste (matrice a `7130dec6`) : `couvert-valide` 141, `en cours` 4, `non couvert` 37, `non couvrable en l'etat` 76, total 258, denominateur atteignable 182, 141 / 182 = 77,47 %. **Apres** : `couvert-valide` 142, `non couvert` 36, autres inchanges, **142 / 182 = 78,02 %**. **Remplace** la borne "au mieux 143 / 182 (78,57 %)" de l'en-tete : une des deux fermetures possibles est refusee ; `nadia-prof` attendait 142 sans l'avoir mesure, la commande le confirme.

### A.16 Statut : `Proposee`, tenu

**Statut : `Proposee`, inchange.** `Q4` (l'ordre des matieres) reste sans reponse de l'utilisatrice et n'est pas reprise sous un motif de forme 1 (0.5). La decision passe a `Acceptee` par un amendement qui cite ses mots sur `Q4`, ou qui reprend l'ordre sous des motifs de forme 1 ; **ni l'un ni l'autre n'a eu lieu a s56**. La cloture de s56 ne la fait pas passer a `Acceptee`. La `DEC` d'ouverture de s57 ne s'appuie pas sur `DEC-0116` comme acceptee.

### A.17 Proposition (D) : que faire de l'entree de catalogue d'un paquet refuse

**Etat constate.** `grep -c 'id: bandit-manchot-rapprocher-deux-oeuvres-cp$' adapters/adapter-content/src/main/resources/catalogue.yaml` rend 1 (`f37e33dd`) : un enfant peut jouer, aujourd'hui, le paquet que `nadia-prof` a refuse. Ce que ce refus vise : la phrase de `M4` sous le pli au plus petit ecran de reference (`DEC-0040`), pas un contenu faux (la fiche tient les six explications, `B6`, la regle 2) ; a 1024 x 768, la phrase est au-dessus du pli (fiche, section 4).

**Ce que le process dit, lu sur les fichiers.**

- `DEC-0045` section 3 : *"un verdict `REFUSE` ... le jeu ne doit pas etre expose. Il en sort au meme geste que la decision qui l'y constate - jamais comme une action a planifier a part"*, par l'absence d'entree dans `catalogue.yaml`, sans drapeau. `DEC-0055` section 1 le garde comme **regime par defaut, inchange**. Precedents appliques : `DEC-0009` (`bandit-manchot-le-bon-son-cp`), `DEC-0049` (`cm1-reperes`), `DEC-0111` section 3 (deux paquets de sciences CM1).
- `DEC-0055` section 2 : une entree ne reste exposee malgre un `REFUSE` que si **trois conditions** sont vraies au moment du refus. Lecture sur ce cas :

| Condition de `DEC-0055` | Constat | Remplie ? |
|---|---|---|
| 1. Arbitrage explicite et ecrit, au moment de l'ouverture de la fenetre | aucun ; cette annotation est une proposition, pas un arbitrage | **non** |
| 2. Correction deja engagee, proprietaire nomme, travail identifiable (branche, story ou commit en cours) au moment du refus | `git worktree list` : aucun worktree ni branche de correction du paquet ; les branches `s56-lea-*` precedent le refus ; la story de reprise est ecrite par cette cloture (backlog, "Cloture de s56"), apres lui | **non** |
| 3. Motif residuel nomme et borne | 105 px, une phrase, six items, mesures par deux agents sur le meme chiffre | oui |

- `DEC-0055` section 3 : la fenetre se ferme au plus tot du prochain verdict ou d'une echeance ecrite qui **ne peut depasser la cloture du sprint en cours**, ou 48 heures hors sprint. Le refus est du 2026-10-11, jour de la cloture de s56 : **un maintien "jusqu'a la correction en s57" depasse la borne que cette regle pose** ; il ne peut venir que d'un amendement de `DEC-0055` ou d'une exception ecrite par l'utilisatrice.

**Options.**

- **(D1) Retrait du catalogue, au meme geste que cette cloture.** C'est ce que `DEC-0045` section 3 et `DEC-0055` donnent sans arbitrage de plus. Prix : l'enfant n'a plus de jeu pour `ART-CP-10` jusqu'a la reprise ; la matrice ne change pas (la ligne est `non couvert`, colonne des jeux vide, avant comme apres). **Ce que le retrait conserve** : le fichier du paquet, ses quatre JPEG et leurs entrees de registre, les garde-fous, la fiche, la reprise (comme `DEC-0111` section 3 et `ADR-0061` : l'absence d'entree reste le seul retrait, le retour est une entree neuve apres un Done non refuse). **Cout technique que je ne tranche pas et que je signale** : l'avis de `theo-archi` (section 1, ligne "Entree de catalogue du paquet", et section 6) dit que l'assertion "perimetre reel vide" du garde-fou 4 et l'inventaire de `TexteAccentueExposeParLePortEntrantTest` ont change dans le commit de l'entree ; le retrait peut les faire rougir. A mesurer par `theo-archi` avant le commit, sans exemption nouvelle (precedent : `S52-RETRAIT-SCIENCES-CM1`).
- **(D2) Maintien jusqu'a la correction en s57.** Argument pour : le defaut est de mise en page au plus petit ecran, aucun contenu n'est faux. Argument contre : c'est la discretion d'execution que `DEC-0055` a ecrite pour ne plus la laisser ; `DEC-0009` ecrit que la porte pedagogique ne protege plus rien si le contenu qu'elle bloque continue d'etre joue. **Il exige** : un arbitrage ecrit de l'utilisatrice (condition 1), une echeance datee (`DEC-0055` section 3) que la regle plafonne a 48 heures et que s57 ne tient pas, donc un amendement de `DEC-0055` par elle, et un proprietaire de la correction nomme avant s57 (condition 2).

**Ma proposition : (D1).** C'est ce que le process donne ; (D2) demande a l'utilisatrice de modifier une regle. Je ne la tranche pas contre elle : **tant qu'elle n'a pas repondu, l'entree reste au catalogue et le paquet refuse reste jouable**, ce que `DEC-0009` nomme comme le defaut a eviter. **`Q-U3` de s56** (numerotation propre a chaque sprint, comme `Q-U1` et `Q-U2` de cette decision ; a l'utilisatrice, a porter par `sofia-sm` le jour meme, avant de fermer la cloture) : retrait (D1), ou maintien (D2) avec amendement de `DEC-0055` et echeance ecrite ?

**Routage, dans le meme temps (la decision implique du code)** : story `S56-RETRAIT-RAPPROCHER-OEUVRES` ecrite au backlog ("Cloture de s56"), **s'engage sur la reponse (D1)** ; `sofia-sm` l'affecte, `theo-archi` mesure le cout technique ci-dessus. Je ne choisis ni le proprietaire ni la forme.

### A.18 Notes de cloture

1. **`Q3`** (deux instances de francais CE1 a partir de s57) : **sans reponse**, ouverte depuis `DEC-0114` 7.2 (s54). Rien de s56 ne la repond : A a pris le groupe X seule (`-13`), condition de 7.2 remplie ; `theo-archi` (avis, section 9) tient sa reponse de s55 : un amendement d'`ADR-0065` est une story, une a la fois, fusionnee avant tout lot de la seconde instance. **Sans reponse, s57 compte une instance de francais CE1** (trois lots au plus).
2. **`Q6`** (mesure de pli de `LIS-2`, `ines-design`) : **sans reponse** ; `cm1-lire-et-restituer` (`FRA-CM1-01`) reste hors de toute composition tant qu'elle n'est pas faite ; `A15` reste non engagee, jamais engagee, pas un report.
3. **`S54-OBS-SCI-CONSIGNE`** : ouverte a s54, premier report a s55 (`DEC-0115` A.10), **second report a s56** (section 5, confirme par A.2). **Compte au 2026-10-11 : deux reports.** **`DEC-0112` section 6 interdit le troisieme : la `DEC` d'ouverture de s57 la place (criteres de `nadia-prof`, proprietaire, prix) ou l'abandonne, par un acte ecrit.** Rien n'a ete fait sur ce poste a s56 (aucun commit de `git log dfcec37c..HEAD` ne la nomme) ; les criteres ne sont toujours pas ecrits. Voir A.19.
4. **`A10`** (verification de s54, trois instances) : **non engagee a s56**, jamais engagee, donc pas un report. Elle reste due avant toute `DEC` qui propose une troisieme instance, et, selon A.6, avant la `DEC` d'ouverture de s57 si `Q3` est acceptee.
5. **Capacite.** Engage a l'ouverture : A 3,5, B 4,75, somme 8,25, dont **0,25 sans poste** sur les 8,5 que l'ecart permettait (A.13) ; il est reste sans poste (aucun amendement ne l'a engage). Consomme, selon `cloture-nadia.md` section 6 : 5,75 (audit du paquet 2,5 ; `-13` 1,0 ; `CLM-CE1-R1` 1,0 ; `ACV-CE1-R1` et `-R2` 0,5 ; `EMC-CM1-R8`, `-R9`, `EXC-R16` 0,75 ; A11 0), reparti A 2,5, B 3,25 ; **aucun rejeu du paquet : la marge de 0,5 de B n'est pas consommee par `nadia-prof`**. **Garde-fous `B2`, `B3`, `B5`, `B7`, `B8`** : nes de l'amendement de `theo-archi` du 2026-10-11, **sans ligne au planning** (son amendement le dit), hors `PAR`, 0,0 pour `nadia-prof`, environ 0,5 jour de dev (estimation de `theo-archi`, pas un tarif) ; fusionnes (`80c06e64`). **Le depassement accepte pour ces garde-fous m'est transmis par l'agent lanceur** ; je n'en trouve aucun ecrit dans le depot (`grep -n -i 'depass'` sur `rapport-lea-dev.md`, `rapport-marc-dev.md` : aucune occurrence sur ce point) et je ne sais pas de quelle borne ni de combien. **Je ne l'inscris donc ni comme decision de l'utilisatrice, ni comme jurisprudence** ; l'ecart de s56 reste un ecart accorde pour s56 seulement (2.2, `DEC-0114` A.20), et `DEC-0116` ne sert pas de precedent a s57. A confirmer par `sofia-sm` dans `review.md`, avec la source du depassement.
6. **Constat sur `DEC-0114`, lignes 13, 52 et 79** (A.7) : reverifie a la cloture, `grep -c 'recu de MASTER, sans source dans le depot'` sur `DEC-0114` rend 0 ; les trois lignes portent l'etiquette "forme 3" sans la formule. **Non corrige** (`DEC-0062` section 7, pas de balayage retroactif), non classe (jugement du `relecteur-pedagogique`, `DEC-0062` section 4). **Reste a `sofia-sm` pour la retro de s56** ; le refus de test de `theo-archi` (47 fichiers sur 65 rouges) et sa condition de revision restent a `sofia-sm`, a moi et a l'utilisatrice. Je n'ai relu aucun autre fichier pour ce point.
7. **`A3`** (pousser, lire la CI sur un commit nomme) : **second report a s56** (section 6) ; aucun artefact de s56 lu par moi n'en dit la livraison. A reprendre par `sofia-sm` a la review ; un troisieme report serait a acter par ecrit.

### A.19 Candidat de s57 : sciences CM1

Decision de l'utilisatrice du 2026-10-09 (A.2) : **"Reporter a s57, abandon ou placement ecrit alors."** `S54-OBS-SCI-CONSIGNE` (ligne `SCI-CM1-20`, `couvert-valide` avec couverture partielle, non rouverte) est donc le candidat de s57 sur ses mots : **la `DEC` d'ouverture de s57 l'abandonne ou la place, par un acte ecrit, et ne la reporte pas.** Ce que le placement exige, tel que la section 5 le dit : criteres ecrits avec les mots de `nadia-prof`, proprietaire, prix. Ce que s56 ne fournit pas : aucun des trois. Pour ne pas le decouvrir a l'ouverture de s57 : la composition de s57 compte, **hors ce poste**, la reprise du paquet d'`ART-CP-10` (rejeu 0,5, `RDO-CP-R1` et `-R2` dans le meme rejeu) et celle de `VSJ-CE1-R1` (0,5), prix ecrits par `nadia-prof` (`cloture-nadia.md` section 3), soit 1,0 ; sciences CM1 est a l'instance B (`PAR-2`), qui portera aussi la reprise du paquet d'oeuvres. **Je ne fixe aucun tarif et je n'ecris pas la composition de s57** : c'est la `DEC` d'ouverture de s57. `Q4` sans reponse (A.16) : l'ordre des matieres n'est pas decide.

### A.20 Routage et limites

- `sofia-sm` : porter `Q-U3` a l'utilisatrice le jour meme (A.17) ; affecter `S56-RETRAIT-RAPPROCHER-OEUVRES` si (D1) ; `review.md` : la source du depassement des garde-fous (A.18, point 5), `A3`, `A8`, `A12` (le dossier de s56 ne porte ni `rapport-qualite.md` ni `relecture-cloture.md` a la date de cette annotation : je n'ai pas lu de preuve que ces postes sont faits) ; retro de s56 : A.18, point 6.
- `theo-archi` : le cout technique du retrait (A.17, D1). `nadia-prof` : le rejeu du paquet corrige et de `VSJ-CE1-R1`. `lea-dev`, `ines-design`, `theo-archi` : la cause des 105 px et de l'ecran de fin (forme a eux, fiche section 7). `marc-dev` : `VSJ-CE1-R1`.
- Les stories sont au backlog, "Cloture de s56", et ne sont engagees que par la `DEC` d'ouverture de s57. Aucun code, aucun contenu, aucun catalogue changes par cet amendement. Ne prononce aucun Done, ne fixe aucun tarif.
- **Jobs de `ci.yml` lances pour ce commit** : voir le message de livraison.

---

## Amendement du 2026-10-11 (s56, apres la cloture, `camille-po`, sixieme) : reponses sur `Q4`, `Q3` et `Q6`, statut inchange

Annotation datee. Rien au-dessus n'est reecrit ; les lignes remplacees sont nommees. Arbre : `round-two` a `8ac33440`. Sources lues : `DEC-0114` section 0.4, section 7.2 et A.27 ; `DEC-0115` sections 0.1, 3, A.22 ; `DEC-0062` section 3 ; `DEC-0116` 0.1, 0.5, 4, A.16, A.18 ; `proposition-s56.md` (arithmetique des lots du francais CE1, section 6) ; `avis-faisabilite.md` de s56, section 9 ; `ARBITRAGE-PREALABLE-s30-...` (`PAR-9`). Cette annotation n'ecrit pas la `DEC` d'ouverture de s57.

### A.21 Les mots de l'utilisatrice, forme 2 de `DEC-0062` : `de l'utilisatrice`, le 2026-10-11

Trois reponses, recues par l'agent lanceur, qui les atteste reponses directes. **Je ne les ai pas lues de sa main et je ne peux pas les verifier depuis le depot** (meme reserve que 0.1, A.1, A.4) : si elle les dement, les points qui en dependent redeviennent des questions. Mots cites :

> "D2 ordre ok"
> "D3 deux instances de nadia"
> "D4 non"

**Rattachement, qui est celui de l'agent lanceur et non le sien** : `D2` repond a `Q4` (l'ordre des matieres de `DEC-0115` section 3 vaut, sans modification) ; `D3` repond a `Q3` (deux instances de `nadia-prof` en francais CE1 a partir de s57) ; `D4` repond a `Q6` (pas de mesure de pli de `LIS-2`, donc `cm1-lire-et-restituer` n'entre pas en s57). **Deux de ces codes sont deja employes** pour d'autres questions le 2026-10-09 (`D2` : "s56 prioritaire" ; `D4` : "ok", francais CE1 sur l'instance A ; backlog, "Reponses de l'utilisatrice du 2026-10-09") : un code `D` n'est lisible qu'avec sa date. **Ses mots ne nomment** ni le sprint, ni le francais CE1 pour `D3`, ni `FRA-CM1-01` pour `D4` : ces precisions sont le rattachement de l'agent lanceur, que je ne cite pas comme ses mots.

### A.22 `Q4` : lecture de `DEC-0114` 0.4 (a), et le statut

**Le texte.** `DEC-0114` 0.4 : `Acceptee` quand l'un des deux est vrai, *"(a) ses mots sont ecrits dans le depot en forme 2 (avec la date) ; (b) la `DEC` d'ouverture de s55 reprend l'ordre sous son propre motif de forme 1"*. `DEC-0116` 0.5 reprend la regle pour cette decision : `Acceptee` *"par un amendement qui cite les mots de l'utilisatrice sur `Q4`, ou qui reprend l'ordre sous des motifs de forme 1"*.

**A la lettre, (a) est satisfaite** : ses mots sont ecrits ici, entre guillemets, avec la date (A.21). **Je ne passe pas le statut**, parce que la lecture n'est pas sans doute, pour trois raisons :

1. **Le depot a deux lectures de la meme situation.** `DEC-0114` A.27, point 2, juge (a) *"non satisfaite"* pour un `ok` relaye : *"relaye, pas cite de sa main"*. `DEC-0115` A.22 passe a `Acceptee` sur des mots relayes de la meme facon (*"J'accepte que la mécanique d'ART-CP-10 soit financée dans s55."*, attestes par l'agent lanceur, *"si l'utilisatrice la dement, il redevient `Proposee`"*). **Je ne vois pas, dans les deux textes, la difference qui ferait gouverner l'une plutot que l'autre.**
2. **Les mots sont `"D2 ordre ok"`.** Le rattachement a `Q4` est celui de l'agent lanceur (A.21), le code `D2` a deja designe une autre question le 2026-10-09, et `ok` est le mot que `DEC-0114` A.27 dit ne pas etre un motif : pour (b), mais aussi parce qu'il fonde *"que l'ordre est suivi (forme 3, `DEC-0062` section 3.2), pas pourquoi il est defendable"*. Ici il porte le mot `ordre`, ce qui est mieux que A.27, et ne dit toujours ni quel ordre ni pour quel sprint.
3. **`DEC-0116` 0.1 s'est donne une regle** : si elle dement les mots relayes, le statut reste `Proposee`. Passer a `Acceptee` sur une attestation qu'elle peut dementir, quand le depot hesite sur sa valeur, ferait de ce passage le geste que `DEC-0114` 0.4 voulait eviter (*"je ne le promeus pas en motif en l'ecrivant dans une `DEC` `Acceptee`"*).

**Statut : `Proposee`, inchange.** **Ce qui la ferait passer, au choix** : (i) ses mots lus de sa main, ou confirmes par elle dans une reponse que le depot peut citer sans intermediaire ; (ii) la `DEC` d'ouverture de s57 reprend l'ordre **sous des motifs de forme 1 par rang**, comme `DEC-0115` section 3 (satisfait `DEC-0114` 0.4 (b)). Pour (ii), la table de `DEC-0115` section 3 est a remesurer : le rang 2 est epuise (EMC CM1, 11 lignes sur 11 atteignables, `DEC-0116` 2.2 et 4), le rang 3 est vide pour s57 (A.24), le rang 4 etait cite sans mesure. Je ne le fais pas ici.

**Ce que ses mots fondent malgre tout**, sous la reserve de reception de A.21 : l'ordre de `DEC-0115` section 3 est suivi pour s57, sans modification. Un ordre suivi sur forme 2 relayee n'est pas une `DEC` acceptee : la `DEC` d'ouverture de s57 le dit ainsi. **Remplace** : A.16, dernier paragraphe, dans sa seule mention que `Q4` est sans reponse ; A.19, derniere phrase (*"`Q4` sans reponse (A.16) : l'ordre des matieres n'est pas decide"*) : l'ordre est suivi, la decision n'est pas acceptee.

### A.23 `Q3` : ce que la reponse engage, sans le decider a la place d'autres

**Ce qu'elle dit** (A.21) : deux instances de `nadia-prof`, lues par l'agent lanceur comme deux instances de francais CE1 a partir de s57. **Remplace** A.18 point 1 (*"Sans reponse, s57 compte une instance de francais CE1"*) : la condition n'est plus remplie.

**Prerequis de s57, que la `DEC` d'ouverture de s57 place et que je ne place pas ici** :

1. **L'accord de `nadia-prof` sur `PAR-2`.** Elle a refuse d'amender `PAR-2` pour le francais CE1 le 2026-10-06 et a ecrit ce qui la ferait reexaminer (`DEC-0114` 7.2) : un decoupage qui ne coupe aucune frontiere de la table de s48 section 7, *"sur un sprint ou aucune autre instance ne porte de terrain neuf"*, avec un avis de `theo-archi`. **La reponse de l'utilisatrice ne vaut pas accord de `nadia-prof`** : l'autorite sur `PAR-2` est a elle. Le decoupage X / Y de `DEC-0114` 7.2 est la proposition a lui soumettre ; la condition *"un sprint ou A prend X seule"* est remplie par `-13` a s56 (`DEC-0116` section 4), sa seconde moitie (A et A' ensemble) est s57.
2. **L'amendement d'`ADR-0065` par `theo-archi`.** Son avis de s56 (section 9, qui reprend celui de s55, T5) : une story, **une a la fois, fusionnee avant tout lot de la seconde instance** ; le lot de la seconde instance attend. Ce n'est pas encodable en `ArchRule` ; le seul automate est le conflit de fusion. **Consequence de calendrier** : si l'amendement n'est pas fusionne a l'ouverture du premier worktree de la seconde instance, son premier lot glisse. C'est un prerequis a placer, avec proprietaire et heure, dans la `DEC` d'ouverture et le planning de s57 (`sofia-sm`).
3. **Le budget.** `PAR-9` : la somme des budgets par instance est egale a la borne du sprint ; `PAR-8` : borne 8,0, plafond dur de 5,0 par instance. **Deux instances : 4,0 chacune, marge de rejeu comprise, somme 8,0.** Chaque instance porte un arbitrage prealable (0,5) et une marge de rejeu (0,5) : il reste **3,0 par instance** pour les lots et les reprises, soit au plus trois lots de 1,0 et rien d'autre. **L'ecart de s56 (0,5, B a 4,75) n'est pas une jurisprudence** (2.2, A.18 point 5) : s57 compte a 8,0.
4. **`A10`.** Elle n'est due que si une **troisieme** instance est proposee (`DEC-0116` section 6 : *"due avant toute `DEC` qui propose une troisieme instance"*). **Remplace** A.18 point 4, dans sa seule fin (*"et, selon A.6, avant la `DEC` d'ouverture de s57 si `Q3` est acceptee"*) : `Q3` acceptee ne la declenche pas par elle-meme.

**Le point que la reponse ne tranche pas : combien d'instances compte s57.** Deux instances de francais CE1 (A et A') sont deux. S57 porte aussi la reprise du paquet d'`ART-CP-10` (0,5) et le candidat `S54-OBS-SCI-CONSIGNE` (A.19), qui etaient a l'instance B (`PAR-2` : arts plastiques CP et sciences CM1). **Si B existe en plus de A et A', s57 a trois instances** : `A10` est due, le budget est de 8,0 pour trois (environ 2,67, pas 4,0), et le motif de forme 1 de A.19 est a refaire. **Si B n'existe pas**, ces postes sont portes par A ou A', ce que `PAR-2` et `nadia-prof` disent ou non. **Je ne le lis pas a sa place** ni a celle de l'utilisatrice : la `DEC` d'ouverture de s57 le compte, avec l'avis de `nadia-prof`.

**Le calcul "s58 pour 10 lots"** (`proposition-s56.md`, arithmetique du francais CE1 : *"deux instances a partir de s57 (section 6, Q3) = **s58**"* ; `DEC-0116` section 1 : 10 autres lots de francais CE1, 11 moins `-13`) **suppose** : (i) la seconde instance active des s57 ; (ii) trois lots par instance et par sprint, soit 6 puis 4 ; (iii) zero refus au-dela de la marge, hypothese que le depot n'a pas verifiee et que s56 n'a pas confirmee (un refus et une reserve sur un lot de francais livre, `VSJ-CE1-R1`, 0,5 a prendre sur le budget de l'instance qui la reprend) ; (iv) aucune reprise sur ces 3,0. **Si l'amendement d'`ADR-0065` decale le premier lot de la seconde instance a s58**, s57 compte trois lots (A seule), s58 six, le dixieme tombe a **s59**. Dit pour que s57 ne le decouvre pas a son ouverture ; aucune date n'est engagee.

### A.24 `Q6` : `FRA-CM1-01` hors s57

**Ce que la reponse engage** (lecture de l'agent lanceur de `D4 non`) : pas de mesure de pli de `LIS-2` par `ines-design`, donc `cm1-lire-et-restituer` (`FRA-CM1-01`) n'entre pas en s57. **`A15` reste non engagee, ni report ni dette** : jamais engagee, donc pas un report (`DEC-0116` section 6, ligne `A15`), et la reponse ne cree aucune dette. **Remplace** A.18 point 2 (*"`Q6` ... sans reponse"*). **Consequence pour l'ordre** : le rang 3 de `DEC-0115` section 3 (`G7` a `G11`, 16 paquets `DEGRADE`) est **vide pour s57**, sauf un nouvel arbitrage de `nadia-prof` sur ces paquets (`DEC-0115` section 3, rang 3 : *"aucun plafond ecrit"*) ; `proposition-s56.md` y comptait au plus une ligne achetable, `FRA-CM1-01`. La ligne reste `non couvert` ; la matrice ne change pas (142 / 182). **Ce que ses mots ne disent pas** : si `non` vaut pour s57 seulement ou au-dela ; je lis s57, comme l'agent lanceur, et je ne ferme aucun sprint suivant.

### A.25 Statut, routage, limites

- **Statut : `Proposee`, inchange** (A.22). Aucun passage a `Acceptee`.
- **Routage** : `sofia-sm` (planning et `DEC` d'ouverture de s57 : les prerequis de A.23, dont l'heure de fusion de l'amendement d'`ADR-0065` ; le compte des instances) ; `nadia-prof` (l'accord sur `PAR-2`, la lecture du compte de trois instances ou de deux, un eventuel arbitrage sur `G7` a `G11`) ; `theo-archi` (la story d'amendement d'`ADR-0065`) ; `ines-design` (rien : `Q6` close pour s57).
- **Story** : `S57-ADR-0065-DEUX-INSTANCES` ecrite au backlog ("Reponses de l'utilisatrice du 2026-10-11"), **hors `PAR`, non engagee** : elle ne s'engage que par la `DEC` d'ouverture de s57, qui lui donne son proprietaire. Cette annotation implique donc un changement de code et de `ADR` futur : elle est routee a `sofia-sm` dans le meme commit.
- Aucun code, contenu, catalogue, planning ni ligne de matrice change. Ne prononce aucun Done, ne fixe aucun tarif, n'ecrit pas la `DEC` d'ouverture de s57.
- **Jobs de `ci.yml` lances pour ce commit** : voir le message de livraison.

## Amendement du 2026-10-12 (apres la cloture de s56, `camille-po`, septieme) : `Q-U3`, reponse de l'utilisatrice

### A.26 `Q-U3` : (D1), mots recus

Mots recus directement dans la session du 2026-10-12 (forme 2 de A.2), cites : *"oui pour le retrait"*. **(D1) est tranchee** :
retrait du catalogue, au sens de A.17. **Remplace** la phrase de A.17 *"tant qu'elle n'a pas repondu, l'entree reste au
catalogue"* : le retrait avait deja ete execute (`13d00bfe`, 2026-10-11 17:07, `catalogue.yaml` a 146 entrees, `grep` = 0
verifie le 2026-10-12) sur une reponse que seuls des rapports d'agents attestaient ; ses mots sont maintenant dans le depot.
Le paquet, ses JPEG, les garde-fous et la fiche sont conserves ; le retour au catalogue passe par une entree neuve apres un Done
non refuse (A.17, `ADR-0061`). `S56-RETRAIT-RAPPROCHER-OEUVRES` n'est plus conditionnelle (backlog). `ART-CP-10` reste
`non couvert`. Statut : `Proposee`, inchange. Aucun code ne change.


## Amendement du 2026-10-12 (apres la cloture de s56, `camille-po`, huitieme) : accord de l'utilisatrice sur trois points de la cloture, contenu non retrouve

Annotation datee. Rien au-dessus n'est reecrit. Sources lues : `review.md` (sections 4, 11, 12 obstacles 6, 8, 22), `retro.md` (section 0, section 4, actions A9 et A10), `RAPPORT.md` ("Questions ouvertes", U5 a U7), `relecture-cloture.md` (P1), `avis-faisabilite.md` (section 8 et amendement du 2026-10-11), `cloture-nadia.md`, `lessons.md`, `DEC-0062` (3.1, A.3), `DEC-0114` (0.1), `DEC-0116` A.18.

### A.27 Les mots, forme 2 de `DEC-0062`, et ce qu'ils portent

Mots de l'utilisatrice recus le 2026-10-12 par l'agent qui m'a lance, qui les atteste recus directement dans la session : *"ce qui est propose pour D7 a D11 est ok"*. **Je ne les ai pas lus de sa main** (meme reserve que 0.1, A.1, A.4, A.21) : si elle les dement, les trois points ci-dessous redeviennent des questions.

**Les codes `D7` a `D11` ne sont dans aucun fichier du depot pour ces points** (`git grep` sur `docs`, `squad/sprints/s56*`, `squad/sprints/hors-sprint-accueil-vues`, `squad/rapports`, `lessons.md`, `DEC-0116`, `DEC-0117` : `D7` y designe d'autres sujets, ceux de s25 (`DEC-0083`), de la passe transverse de s54, et la cloture du chantier hors sprint dit deja ne pas retrouver ses propres `D7` a `D12`). **Le rattachement est celui de l'agent lanceur** : `D7` = `U5` de `RAPPORT.md` (le depassement des garde-fous) ; `D8` = `U6` (les arrets d'agent et le watchdog) ; `D9` = `U7` et `A9` de la retro (la formule de la forme 3). `D10` et `D11` sont traites par `sofia-sm` et ne sont pas ici. Comme en A.21, un code `D` n'est lisible qu'avec sa date et sa session.

**Ce que ces mots fondent** : l'utilisatrice a accepte ce qu'on lui a propose le 2026-10-11. **Le texte de ce qu'on lui a propose n'est pas dans le depot.** Pour chaque point, je ne reprends ci-dessous que ce que les fichiers ecrivent comme proposition ; ce qui manque est dit "non retrouve dans le depot, accord donne sans le contenu" et devient une question. Je ne comble aucun vide.

### A.28 `D7` : le depassement de borne des garde-fous `B2`, `B3`, `B5`, `B7`, `B8`

**Ecrit dans le depot.** Garde-fous nes de l'amendement de `theo-archi` du 2026-10-11, fusionnes (`80c06e64`), hors `PAR`, 0,0 au bareme de `nadia-prof`, environ 0,5 jour de developpement (0,25 j pour quatre d'entre eux, 0,25 j pour `B5` ; estimation de `theo-archi`, pas un tarif), sans ligne au planning (A.18 point 5 ; `review.md` section 11). Mots d'acceptation du depassement, relayes par l'agent lanceur : *"Lance Q2 aussi meme si on depasse la borne"*, 2026-10-11 (`review.md` section 4). Ni `review.md`, ni `retro.md`, ni `RAPPORT.md` ne proposent de chiffre : `U5` pose la question, et `review.md` obstacle 8 dit que si elle veut un amendement, il est a `camille-po`.

**Non retrouve dans le depot, accord donne sans le contenu** : **quelle borne** et **de combien**. Aucun fichier lu n'ecrit une borne en jours de developpement ; sur le bareme de `nadia-prof`, les garde-fous ne changent ni l'engage (8,25) ni le consomme (7,25, ou 5,75 selon `cloture-nadia.md`). Je ne choisis pas la borne a sa place et je n'inscris aucun chiffre.

**Effet sur cette decision** : aucun chiffre de 2.1, 2.2 ni A.13 ne change. **A.18 point 5 est inchange** : le depassement reste non inscrit comme decision tant que la borne et l'ecart ne sont pas ecrits. Il reste sans jurisprudence ; s57 compte a 8,0 (A.23 point 3). Question ouverte : **`Q-U5`**.

### A.29 `D8` : les arrets d'agent et le watchdog

**Ecrit dans le depot.** `cloture-nadia.md` (ligne 4) : *"l'instance B arretee"*, dont `nadia-prof` reprend les postes. `retro.md` C1 : 23 h 49 min sans livraison entre `f37e33dd` (2026-10-10 11:17) et `38ba3d5c` (2026-10-11 11:06), entree de catalogue 15 h 07 min apres l'echeance de 20:00 ; la cause (deux agents arretes par l'utilisatrice et remplaces, un watchdog sur `lea-dev`) y est dite **transmise par le message de lancement, sans fichier**. Proposition de la retro, action `A10` : ecrire qui, quand, ce que chaque branche portait, par `sofia-sm` (lanceur) et l'utilisatrice (les faits), *"une ligne dans le planning de s57 ou un amendement date"*. `lessons.md` : une regle (ecrire l'arret, par celui qui relance). Les noms des deux agents arretes (`ines-design`, `nadia-prof` instance B) viennent du message qui m'a lance, non attribues a l'utilisatrice, sans autre source dans le depot ; `cloture-nadia.md` ne nomme que l'instance B de `nadia-prof`.

**Non retrouve dans le depot, accord donne sans le contenu** : **le motif** de chacun des deux arrets, **la duree ou l'heure** de chacun, **le motif et la duree du watchdog** sur `lea-dev`, ce que la branche de chaque agent arrete portait, qui l'a relance. Seule la fenetre de 23 h 49 min est mesuree, et elle ne dit pas a quel arret ou au watchdog elle est due. Je n'ecris ni motif ni duree. Ces faits sont ceux de l'utilisatrice : question ouverte **`Q-U6`**.

**Effet** : l'accord autorise l'ecriture ; il ne la remplace pas. Rien n'est ecrit dans un planning ni dans `lessons.md` par cette annotation. Quand la reponse a `Q-U6` est la, l'ecriture est un amendement date de cette decision, ou une ligne du planning de s57 au choix de `sofia-sm` (`retro.md` `A10`).

### A.30 `D9` : la formule de la forme 3 et la convention structurelle

**Ecrit dans le depot.** `DEC-0062` A.3 point 2 : la formule fixe est *"recu de MASTER, sans source dans le depot"*. `DEC-0114` 0.1 (ligne 31) ecrit *"recus de MASTER, sans source dans le depot, le 2026-10-07"* pour quatre messages cites mot pour mot avec leur date. `relecture-cloture.md` P1 : les lignes 13, 52, 79 sont des mentions, la ligne 31 est une attribution dont la formule est au pluriel, **un ecart de formule et non de provenance**, question renvoyee a `sofia-sm` et `camille-po`. `retro.md` section 4 (lecture de `sofia-sm`, ecrite *"non une decision"*) : **le pluriel est l'accord de la formule, pas une quatrieme forme** ; savoir si la formule tolere l'accord est a `DEC-0062`, donc a `camille-po` et a l'utilisatrice. `avis-faisabilite.md` section 8 (`theo-archi`) : refus de test (47 fichiers sur 65 rouges) ; ce qui lui ferait changer d'avis est *"une convention qui donne a l'attribution de forme 3 une forme structurelle (par exemple un bloc de citation dont la premiere ligne est la formule)"*, amendement de `squad/README.md` section 2 bis et de `DEC-0062`, a `sofia-sm`, `camille-po` et l'utilisatrice.

**La proposition retrouvee pour la formule** est la lecture de `sofia-sm` : le pluriel de `DEC-0114` ligne 31 vaut la formule. **Pour la convention structurelle, seul un exemple est ecrit** (celui de `theo-archi`) ; aucun fichier ne propose un texte a adopter ni une forme choisie.

**Non retrouve dans le depot, accord donne sans le contenu** : (i) que *"est ok"* porte sur cette lecture de `sofia-sm` (c'est la seule proposition ecrite, je ne peux pas confirmer que c'est celle qu'on lui a posee) ; (ii) **la convention structurelle** : laquelle, et si elle doit etre ecrite. Question ouverte : **`Q-U7`**.

**Effet** : aucun fichier autre que celui-ci, `DEC-index.md` et le journal hors sprint n'est modifie. `DEC-0062` et `DEC-0114` ne sont pas touches (`DEC-0062` section 7 : pas de balayage retroactif) ; l'amendement d'une ligne de `DEC-0062` qui dirait que la formule tolere l'accord n'est pas ecrit ici, il suit la reponse a `Q-U7`. `squad/README.md` section 2 bis est a `sofia-sm` et n'est pas touchee. Le refus de test de `theo-archi` tient.

### A.31 Questions pour l'utilisatrice, statut, routage

- **`Q-U5`** : le depassement des garde-fous `B2`, `B3`, `B5`, `B7`, `B8` : depasse **quelle borne** (la borne de 8,0 de `PAR-8`, l'ecart de 0,5 de A.1, ou une borne de jours de developpement), **de combien**, et l'ecrit-on dans cette decision ?
- **`Q-U6`** : pour `ines-design`, pour `nadia-prof` (instance B) et pour le watchdog sur `lea-dev` : le motif, l'heure ou la duree, et ce que la branche portait au moment de l'arret.
- **`Q-U7`** : *"est ok"* portait-il sur la lecture *"le pluriel est l'accord de la formule"* (`DEC-0114` ligne 31 vaut `DEC-0062`) ? Et, pour lever le refus de test de `theo-archi`, quelle convention structurelle veut-elle (par exemple un bloc de citation dont la premiere ligne est la formule), ou aucune ?
- **Statut : `Proposee`, inchange.** Aucun passage a `Acceptee` (A.22 tient).
- **Routage** : `sofia-sm` (planning de s57 ou amendement pour `Q-U6` ; `squad/README.md` section 2 bis si `Q-U7` appelle une convention). Aucun code, contenu, catalogue ni ligne de matrice ne change. Ne prononce aucun Done, ne fixe aucun tarif.
- **Jobs de `ci.yml` lances pour ce commit** : voir le message de livraison.


## Amendement du 2026-10-13 (apres la cloture de s56, `camille-po`, neuvieme) : l'utilisatrice repond a `Q-U5`, `Q-U6` et `Q-U7`

Annotation datee, suite apres A.31. Rien au-dessus n'est reecrit. Statut : `Proposee`, inchange (A.22 tient).

### A.32 Les mots, forme 2 de `DEC-0062`

Mots de l'utilisatrice, recus directement dans la session du 2026-10-13, cites :

- `Q-U5` : *"pas de bornes superieur c'est au besoin mais on la consigne"*.
- `Q-U6` : *"je n'ai pas ces infos, la cause des arrets est la coupure de la batterie"*.
- `Q-U7` : *"oui pour Sofia"*.

### A.33 `Q-U5` : fermee. Pas de borne superieure, au besoin, consignee

**La regle, telle qu'elle l'a dite.** Le depassement des garde-fous `B2`, `B3`, `B5`, `B7`, `B8` n'a **pas de borne superieure**. Il se fait **au besoin**. Il est **consigne**.

**Ce que je lis, et la limite de ma lecture.** Je lis cette regle comme portant sur les cinq garde-fous nommes par `Q-U5`, qui sont hors `PAR` (A.28). **Elle ne dit rien de `PAR-8` ni de `PAR-9`** : la borne de 8,0 d'un sprint, le plafond de 5,0 par instance et la somme des budgets restent ce qu'ils sont, et s57 compte a 8,0 (A.23 point 3). Si elle voulait aussi lever `PAR-8`, ce n'est pas dans ses mots et je ne l'ecris pas.

**Gabarit minimal de la consignation** : une ligne par depassement, dans la `DEC` d'ouverture ou la cloture du sprint qui le porte, avec quatre champs :

1. la **date** ;
2. le **garde-fou** (`B2`, `B3`, `B5`, `B7`, `B8`, ou un futur) ;
3. l'**estimation**, avec son auteur et sa methode, ou la mention *"non estimee"* : aucun chiffre n'est ecrit sans source (l'estimation de `theo-archi` de A.28, environ 0,5 jour pour l'ensemble, est une estimation, pas un tarif) ;
4. **qui l'a accepte**, avec ses mots cites et leur date (forme 2), ou la mention *"non accepte"*.

**Ce que ca remplace.** A.28, dans sa seule fin (*"quelle borne et de combien"*, que je ne choisissais pas a sa place) : il n'y a pas de borne a choisir. A.18 point 5, dans sa seule clause *"le depassement reste non inscrit comme decision tant que la borne et l'ecart ne sont pas ecrits"* : la condition tombe, la consignation la remplace. A.31, premier point : close. **Ce qui ne change pas** : l'ecart de s56 (0,5, B a 4,75) n'est pas une jurisprudence pour `PAR` (A.23 point 3), et les depassements deja faits (`B2`, `B3`, `B5`, `B7`, `B8`, `80c06e64`) ne sont pas ecrits ligne par ligne par cette annotation : leur consignation est a `sofia-sm`.

### A.34 `Q-U6` : fermee comme question, cause consignee, le reste non documente

**Consigne, de l'utilisatrice** : *"la cause des arrets est la coupure de la batterie"*. Ses mots portent sur **les arrets** ; ils ne disent pas si le watchdog sur `lea-dev` en fait partie.

**Non documente, dit comme tel : elle n'a pas ces informations.** Le motif detaille de chaque arret, la duree ou l'heure de chacun, le contenu de la branche de chaque agent arrete, le motif et la duree du watchdog, qui a relance. La fenetre de 23 h 49 min de `retro.md` C1 reste sans attribution a un arret ou au watchdog. Je n'ecris ni motif detaille, ni duree, ni contenu de branche. Aucun fichier du depot ne comble ces vides, et l'utilisatrice a dit ne pas les avoir : la question est close faute de source, pas faute de reponse a chercher.

**Ce que ca remplace.** A.29, dans sa seule phrase *"Ces faits sont ceux de l'utilisatrice : question ouverte `Q-U6`"* : la question est fermee. Et `retro.md` C1, dans sa seule mention que la cause est *"transmise par le message de lancement, sans fichier"* : la cause est desormais dans le depot, en forme 2, ici. **Ce que ca ne ferme pas** : l'ecriture des arrets (`retro.md` `A10`, `lessons.md`) est a `sofia-sm`, et elle ne peut contenir que ce qui est ici.

### A.35 `Q-U7` : point 1 ferme, point 2 ouvert

**Point 1, ferme.** *"oui pour Sofia"* : son accord porte sur la lecture de `sofia-sm` (`retro.md` section 4) : **le pluriel de `DEC-0114` ligne 31 est l'accord de la formule de `DEC-0062`, pas une quatrieme forme.** Remplace A.30 (i). L'amendement d'une ligne de `DEC-0062` que A.30 disait attendre cette reponse est ecrit le meme jour (`DEC-0062`, amendement D). `DEC-0114` n'est pas touche, sans balayage retroactif (`DEC-0062` section 7).

**Point 2, reste ouvert.** Elle n'a rien dit de la convention structurelle (par exemple un bloc de citation dont la premiere ligne est la formule). **Le refus de test de `theo-archi` tient** (`avis-faisabilite.md` section 8, 47 fichiers sur 65 rouges) : aucun test, aucune convention de forme, aucun amendement de `squad/README.md` section 2 bis sur ce point. Question ouverte : **`Q-U7` point 2**. Une absence de reponse n'est pas un `non` : je ne la lis ni comme l'un ni comme l'autre.

### A.36 Statut, routage, limites

- **Statut : `Proposee`, inchange.**
- **Questions ouvertes** : `Q-U7` point 2 seulement. `Q-U5` et `Q-U6` fermees, `Q-U7` point 1 ferme.
- **Routage a `sofia-sm`**, sans que je l'ecrive : (1) `squad/README.md` section 2 bis : dire que la formule de la forme 3 tolere l'accord au pluriel, en renvoyant a `DEC-0062` amendement D ; ne pas introduire de convention structurelle ; (2) la regle de consignation du depassement des garde-fous (A.33), et les lignes des depassements deja faits, dans la `DEC` d'ouverture ou le planning de s57 ; (3) l'ecriture des arrets d'agent (`retro.md` `A10`) dans le planning de s57 ou `lessons.md` : la cause (coupure de la batterie, ses mots, 2026-10-13) et la liste explicite de ce qui reste non documente (A.34). Routage a `nadia-prof` et `theo-archi` : aucun.
- Aucun code, contenu, catalogue ni ligne de matrice ne change. Ne prononce aucun Done, ne fixe aucun tarif.
- **Jobs de `ci.yml` lances pour ce commit** : voir le message de livraison.


## Amendement du 2026-10-13 (apres la cloture de s56, `camille-po`, dixieme) : `Acceptee`, par `DEC-0120` section 1

Annotation datee, suite apres A.36. Rien au-dessus n'est reecrit, sauf la ligne "Statut" de l'en-tete et une note sous 0.5, qui portent le nouvel etat (meme geste que `DEC-0114` A.27 et `DEC-0115` A.22). Arbre : `round-two` a `900db2dc`. Sources lues : `DEC-0114` 0.4, 7.1, A.27 ; `DEC-0115` 0.4, section 3 ; `DEC-0116` 0.5, A.16, A.19, A.22 a A.25, A.32 a A.36 ; `DEC-0062` formes 1, 2, 3 ; `DEC-0120`.

### A.37 Les mots, forme 2 de `DEC-0062`, et ce qu'ils portent

Mots de l'utilisatrice, recus directement dans la session du 2026-10-13, tels que l'agent lanceur me les transmet : *"reprend l'ordre"*. Je ne les ai pas lus de sa main (meme reserve que 0.1, A.21, A.32). L'agent lanceur les rattache au choix (ii) de A.22 ; ses mots ne le nomment pas. **Ils ne passent pas le statut** : A.22 (point 2) a ecrit pourquoi un mot d'accord n'est pas un motif, et le choix (ii) est precisement la voie qui n'en a pas besoin. Le statut repose sur A.38.

### A.38 `DEC-0114` 0.4 (b) est satisfaite par `DEC-0120` section 1

`DEC-0114` 0.4 (b), lue par `DEC-0115` 0.4 : satisfaite *"si, et seulement si, chaque rang de l'ordre porte un motif de forme 1 que je peux verifier"*, le rang dont la source n'est pas remesuree etant nomme. Lu sur `DEC-0120` :

| Exigence | Constat |
|---|---|
| Un motif de forme 1 par rang (1 a 4) | oui : section 1, chaque rang avec son atteignable mesure, son motif et sa source (commande ou fichier de `nadia-prof`) |
| La table de `DEC-0115` section 3 remesuree (A.22 (ii)) | oui : matrice par matiere et niveau, `comm -23` (37 fichiers sans entree), plafonds lus ; le rang rapporte comme *"cite sans mesure"* (rang 4) est remesure ; il n'en reste aucun |
| Ce que la remesure change est dit | oui : rang 1, 13 paquets devenus 10 ; rang 2 epuise ; rang 3 vide a s57, motif remplace (plafonds ecrits) ; rang 4 precise (`MAT-CE1-14` conditionnee a `A3`) |
| Aucun rang ne repose sur un mot de l'utilisatrice seul | oui : 0.1 de `DEC-0120` |

**Lecture que je ne cache pas** : `DEC-0114` 0.4 (b) dit *"la `DEC` d'ouverture de s55"*, A.22 (ii) *"la `DEC` d'ouverture de s57"*. `DEC-0120` est une decision d'ouverture **limitee** (elle n'ouvre pas le sprint, a la demande de l'utilisatrice) : je lis (b) sur sa substance, un motif de forme 1 par rang dans la `DEC` qui prend l'ordre pour le sprint, et non sur le fait qu'elle ouvre le sprint. Si l'utilisatrice l'entend autrement, `DEC-0116` revient a `Proposee` jusqu'a la `DEC` qui ouvre s57.

### A.39 Statut

**`Acceptee` le 2026-10-13.** `DEC-0120` est elle-meme `Proposee` (une question a l'utilisatrice reste ouverte sur la composition, pas sur l'ordre) : c'est le cas de `DEC-0114`, passee a `Acceptee` par `DEC-0115` quand celle-ci etait `Proposee` (A.27). **Ce qui la ferait revenir a `Proposee`** : la section 1 de `DEC-0120` amendee parce qu'une mesure s'avere fausse, ou un dementi des mots de A.37 qui ferait amender cette section. **`Q-U7` point 2** (A.35) reste ouverte : elle porte sur une convention de `DEC-0062` et de `squad/README.md`, pas sur une decision de cette `DEC`, et aucun code n'en depend ; elle ne retient pas le statut.

**Remplace** : la ligne "Statut" de l'en-tete et 0.5 (annotes) ; A.16, dernier paragraphe (*"La decision passe a `Acceptee` par un amendement qui ..."*) ; A.22, **Statut : `Proposee`, inchange** ; A.25 premier point ; A.31 et A.36, ligne **Statut : `Proposee`, inchange**. Rien d'autre de A.16 a A.36 ne change : la lecture de (a) (A.22, points 1 a 3) reste ecrite, elle n'est simplement plus necessaire.

### A.40 Routage, limites

Routage : `sofia-sm` (`DEC-index.md` tenu ; planning de s57 selon `DEC-0120` section 6). Aucun code, contenu, catalogue ni ligne de matrice ne change (142 / 182). Ne prononce aucun Done, ne fixe aucun tarif. **Jobs de `ci.yml` lances pour ce commit** : voir le message de livraison.
