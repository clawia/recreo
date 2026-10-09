# DEC-0116 - Ouverture de s56 : option O3 (un lot de francais CE1, six reserves levees, le premier paquet a reproductions d'`ART-CP-10` ecrit par `lea-dev` puis audite), ecart de 0,25 accorde pour s56 seulement, second report de `S54-OBS-SCI-CONSIGNE`

- **Type** : decision d'ouverture de sprint (perimetre, capacite, ordre, reports, routage)
- **Date** : 2026-10-09
- **Auteur** : `camille-po`
- **Sprint** : s56
- **Competences programme visees** : `FRA-CE1-13` (lot `ce1-verbe-sujet`) et `ART-CP-10` (paquet a reproductions et audit) pour les deux fermetures possibles ; `FRA-CE1-14`, `FRA-CE1-12`, `EMC-CM1-03`, `EMC-CM1-06`, `EMC-CP-03` pour les six reserves (lignes deja `couvert-valide`, non rouvertes). Les autres postes (arbitrages, plafond, actions de retro) sont transverses - pas de contenu pedagogique. **Aucune ligne de matrice ne change par cette decision** : elle n'ouvre que des audits. Matrice a `c986adcf` : **141 / 182 (77,47 %)** ; au mieux **143 / 182 (78,57 %)**.
- **Statut** : **Proposee**, section 0.5. Les mots de l'utilisatrice couvrent la composition (O3), l'ecart et le proprietaire du paquet ; ils ne couvrent ni l'ordre des matieres pour s56 (Q4), ni l'arbitrage de forme de 0,25 (2.3).
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
