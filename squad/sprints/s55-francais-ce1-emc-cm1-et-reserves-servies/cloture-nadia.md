# Cloture de s55 - bilan de `nadia-prof` (instance A)

`nadia-prof`, instance A, le 2026-10-08. Worktree `s55-nadia-cloture`, base `af8b326e` (`round-two`, arbre fusionne,
qui contient tous les commits de s55 jusqu'a la fusion de `s55-lea-mecanique`).

**Ce document ne prononce aucun Done, ne leve aucune reserve et n'en ouvre aucune.** Il relit les verdicts des deux
instances **dans les fiches elles-memes** (pas dans les resumes `audit-nadia-*.md`), dit ce qui reste ouvert et a quel
prix, juge la lecture du gabarit de T3 que mon complement annoncait, et donne a `camille-po` le statut de matrice a
retenir. Il ne touche ni la matrice, ni le backlog, ni une fiche, ni un paquet, ni le registre. Aucun identifiant
neuf.

---

## 1. Le Done pedagogique, story par story

Relu dans le bloc de validation de chaque fiche de `squad/conformite/`.

| Code | Story | Ligne | Fiche | Verdict ecrit dans la fiche | Instance |
|---|---|---|---|---|---|
| V1 | `S55-BR-FRA-CE1-L1` | `FRA-CE1-14` | `FICHE-bandit-manchot-accord-sujet-verbe-ce1.md` | **VALIDE SOUS RESERVE**, joue, 40 parties ; reserves `ACV-CE1-R1`, `ACV-CE1-R2` | A |
| V2 | `S55-BR-FRA-CE1-L2` | `FRA-CE1-12` | `FICHE-bandit-manchot-classes-de-mots-ce1.md` | **VALIDE SOUS RESERVE**, joue, 40 parties ; reserve `CLM-CE1-R1` | A |
| V3 | `S55-BR-EMC-CM1-03` | `EMC-CM1-03` | `FICHE-bandit-manchot-respect-differences-cm1.md` | **VALIDE SOUS RESERVE**, joue, 9 parties ; reserve `EMC-CM1-R8` ; mention d'EMC CM1 exigee | B |
| V4 | `S55-BR-EMC-CM1-06` | `EMC-CM1-06` | `FICHE-bandit-manchot-principes-republique-cm1.md` | **VALIDE SOUS RESERVE**, joue, 9 parties ; reserve `EMC-CM1-R9` ; mention d'EMC CM1 exigee | B |
| V5 | `S54-ART-RES-R1` a `R3` | `ART-CP-10` | `FICHE-bandit-manchot-familles-d-oeuvres-cp.md` | **VALIDE**, rejoue, 30 parties ; reserves 1 a 3 levees ; aucun mouvement de ligne | A |
| V6 | `S54-EXC-CATEGORIE` | `EMC-CP-03` | `FICHE-bandit-manchot-exclusion-cp.md` | **VALIDE SOUS RESERVE**, rejoue, 30 parties ; `EXC-R15` levee, `EXC-R16` neuve ; aucun mouvement de ligne | B |

Les six verdicts sont des audits joues sur un jar dont les ressources ont ete comparees a l'arbre (`cmp`), rendu lu
en HTML et non vu au navigateur. Aucun n'est un REFUSE ; aucune reserve n'est bloquante.

**`EXC-R16`** : servie apres le verdict par `marc-dev` (`e6262ac2`). La fiche dit *"VALIDE SOUS RESERVE"*, et la reserve
reste ouverte tant qu'elle n'est pas rejouee (section 2).

**Ecart de fiche trouve en relisant** : l'en-tete de `FICHE-bandit-manchot-exclusion-cp.md` (*"Statut global"*,
*"Derniere mise a jour"*) s'arrete a s54 et dit encore `EXC-R15` ouverte ; le bloc de s55 de l'instance B, en fin de
fiche, la dit levee. Le bloc fait foi. L'en-tete est a completer par `nadia-prof` au prochain passage sur cette fiche ;
je ne le touche pas ici.

## 2. Reserves ouvertes et cout de rejeu

| Code | Reserve | Fiche | Etat a la cloture | Regime | Cout du rejeu |
|---|---|---|---|---|---|
| O1 | `ACV-CE1-R1` | accord sujet-verbe CE1 | **servie** par `2b66a441` : l'indice de l'item 6 est ma ligne a la lettre (diff lu). **Non rejouee** : ouverte | `PAR-10`, mot pour mot | **0,25** |
| O2 | `ACV-CE1-R2` | accord sujet-verbe CE1 | non servie ; au moins 8 des 12 items a sujet pluriel en *-ent* du premier groupe, soit au moins trois items changes | rejeu (`PAR-10`) | **0,5** |
| O3 | `CLM-CE1-R1` | classes de mots CE1 | non servie ; les 24 indices changent | lot (`PAR-14`) | **1,0** |
| O4 | `EMC-CM1-R8` | respect des differences CM1 | non servie ; deux indices (items 10 et 11), texte exact dans la fiche, section 5 | `PAR-10` | **0,25** (0,5 si un autre texte) |
| O5 | `EMC-CM1-R9` | principes de la Republique CM1 | non servie ; indice de l'item 3 et quatre lignes d'en-tete, textes exacts dans la fiche, section 5 | `PAR-10` | **0,25** (0,5 si un autre indice) |
| O6 | `EXC-R16` | exclusion CP | **servie** par `e6262ac2` : l'indice de l'item 4 est ma ligne a la lettre (diff lu). **Non rejouee** : ouverte | `PAR-10` | **0,25** |

**Somme : 2,5.** Par instance d'origine : A 1,75 (O1 a O3), B 0,75 (O4 a O6). Les deux instances sont a 4,0 sur 4,0
a s55 (`PAR-9`) : aucune ne se rejoue ici. O1 et O6 sont les seules dont le texte est deja au catalogue ; leur rejeu
ne demande aucun developpement. Lire un diff n'est pas rejouer : je n'en leve aucune.

**Autres points des fiches, qui ne sont pas des reserves** :

- `E3` (commentaire et en-tete de l'item 18 de `ce1-classes-de-mots`) : servi par `fa3b97c0`, mes deux textes a la
  lettre (diff et en-tete lus). Rien a rejouer : ce n'est pas un texte lu par l'enfant.
- Items 3 et 15 de `ce1-accord-sujet-verbe` (*"combien en voit-on ?"* sur un sujet pronom) : note *"a reprendre, sans
  reserve"* dans la fiche, section 5. A reprendre au passage qui sert `ACV-CE1-R2`, sans prix propre.

## 3. Les stories techniques : y a-t-il un Done pedagogique a prononcer ?

| Code | Story | L'enfant voit-il un changement ? | Done pedagogique |
|---|---|---|---|
| T1 | `S55-ART-CP-10-MECANIQUE`, T2 (`dfd2f107`) | non : garde-fous et jeux fautifs de test, aucune ligne de production | **aucun a prononcer** |
| T2 | `S55-ART-CP-10-MECANIQUE`, T3 (`363793e3`) | non a s55 : la branche image du gabarit ne s'ouvre que si toutes les propositions d'une manche portent le prefixe `oeuvre-`, et aucun paquet servi n'en porte (`grep` des `bonneReponse` et `distracteurs` en `oeuvre-` sur les paquets : aucun resultat). La branche texte est retouchee pour tous les jeux : section 3.1 | **aucun a prononcer** ; il se prononcera sur le premier paquet a reproductions, en jouant |
| T3 | `S54-PONCT-GN-INDICES` (`08cb05fd`) | oui, l'espace devant le signe double de 12 indices de `cm1-grands-nombres`. Les mots ne changent pas (`diff` vide apres normalisation de l'espace, rapport de `lea-dev`) | **aucun** : la ponctuation du texte lu par l'enfant est au `relecteur-pedagogique` (`DEC-0002`, amendement du 2026-08-04 ; `DEC-0056`). Sa relecture n'est pas faite selon le rapport de `lea-dev` |
| T4 | `S54-ASCII-APOSTROPHES-TESTS` (`51c81763`) | non : echappement dans deux tests | **aucun** |
| T5 | A7 (`8541cffc`), A8 (`69d9d6a2`) | non : outils | **aucun** |

### 3.1 Ce que j'ai lu de T3 (lecture annoncee par mon complement, section 1.1 ; audit degrade par nature)

Lu sur `af8b326e` : `manche.html`, le bloc `ADR-0104` de `recreo.css`, le diff de `PartieController`.

| Exigence | Constat |
|---|---|
| `M4` | *"C'est cette œuvre qui va avec. Appuie dessus."* : identique octet pour octet a mon arbitrage (`grep -c` rend 1 dans les deux fichiers). Posee seulement quand `propositionsEnImage` et `mancheRevelee` sont vrais ; `reponseRevelee` est alors nul, donc *"Appuie sur la bonne réponse :"* et la reponse en texte ne sont pas rendus. **Tenu** |
| `M5` | `aria-label` *"Choisir cette œuvre"*, identique octet pour octet, le meme sur les trois boutons et sur tout item. **Tenu** |
| `M6` | `explication` posee dans la seule branche de revelation du controleur, comme avant. **Tenu** |
| `X1` | le bouton image ne porte que `<img alt="">` ; aucun `title`. Le juge de la page rendue est le garde-fou 4. **Tenu a la lecture** |
| `X2` | aucun numero, aucune lettre, aucune legende dans la branche image. **Tenu** |
| `X7` | ecran de retour : `bonneReponse` nul et `reponseAttendueVisible` faux quand la bonne reponse est une reproduction. **Tenu** |
| Branche texte | le texte du bouton passe de `th:text` sur le bouton a un `th:block` dans le bouton ; `aria-label` et `data-oeuvre-revelee` valent nul hors images. Resultat de ma sonde : section 3.2 |

### 3.2 Sonde de non-regression de la branche texte

T3 retouche le gabarit de toute manche de bandit manchot. Aucun joueur n'avait rejoue un jeu en texte apres T3 sur
le jar : je l'ai fait, en sonde et non en audit.

- `bash outils/mvn-jdk21.sh -B clean verify` sur la racine de `af8b326e` : **BUILD SUCCESS**, Maven sur le JDK 21.0.10
  de la toolchain ; domain 120, application 18, adapter-web 407, adapter-content 1419, bootstrap 333, bloc final 6,
  zero echec. **Job `build` seul lance par moi** ; `prefixes`, `chemins` et `outils` non lances sur ce build.
- Jar de ce build servi par mon processus sur un port dedie, sur Java 21.0.10, arrete par son PID.
- Une partie par jeu, par les routes servies, sur sept jeux : `accord-sujet-verbe-ce1`, `classes-de-mots-ce1`,
  `exclusion-cp`, `respect-differences-cm1`, `principes-republique-cm1`, `familles-d-oeuvres-cp`,
  `decrire-une-oeuvre-cp`. Conduite : presser la premiere proposition jusqu'a la revelation quand elle est fausse,
  puis la bonne reponse revelee. 67 manches, 45 revelations.
- **Constat** : sur chaque manche, le texte de chaque bouton est sa proposition ; aucun `aria-label`, aucun
  `data-oeuvre-revelee`, aucune image de proposition, jamais la phrase de `M4`. A chaque revelation, *"Appuie sur la
  bonne réponse :"* et la reponse en texte sont rendus. Sur `decrire-une-oeuvre-cp`, la reproduction de reference et
  son texte alternatif sont rendus comme avant. **Aucune regression vue.** Rendu lu en HTML, pas vu au navigateur.
- Le commentaire de gabarit d'`ADR-0104` part dans le HTML servi, comme les autres commentaires du gabarit ; l'enfant
  ne le voit pas.

## 4. `ART-CP-10`, T3 : les ecarts de `lea-dev` a la proposition d'`ines-design`

Je juge pour un enfant de CP accompagne. **La premisse de l'adulte ne change rien a ce dispositif** : la voix n'est
pas la competence de cette ligne (mon arbitrage, section 3), donc rien ne peut reposer sur ce que l'adulte dirait.

### 4.1 `E2` - l'anneau de la revelation pose sur l'image : **acceptable**

Livre : un anneau d'encre de 0,25 rem colle a l'image, dans le rembourrage de la case, autour du cadre de 2 px que
toute image porte. Propose : un anneau blanc de 4 px separe de la case par un jour de 4 px.

- La marque reste une **forme** (un cadre epais et sombre, sur une seule des trois), posee **hors de l'image**, sur le
  fond clair de la case : elle ne depend pas de ce que la reproduction montre. C'est ce que `M4` demande.
- Elle n'est pas seule : la phrase de `M4` (*"cette œuvre"*) et le focus arrivent sur la meme case, et les
  propositions deja essayees sont attenuees.
- Ce que je perds : le double cadre distinguait mieux la marque du cadre ordinaire que ne le fait un cadre plus
  epais. Je l'accepte : la difference est de 2 px a 6 px de cadre, encre contre accent.
- **A verifier a l'ecran, a l'audit du premier paquet** : que l'anneau se voit autour d'une reproduction sombre dans
  son bord, et qu'il ne se confond pas avec l'anneau de focus clavier qui arrive sur la meme case. C'est le point
  `M4` a l'ecran que mon complement (section 1.2) laissait deja a cet audit : pas une barre neuve.

### 4.2 `E3` - l'echec marque par la seule attenuation a 50 % : **pas acceptable pour un paquet servi**

Livre : l'oeuvre deja essayee passe a demi-opacite, sans cadre en tirets.

- **Correction de mon propre constat d'abord.** `M3` demandait *"l'etat d'echec existant sur l'oeuvre choisie"*. L'etat
  existant est un mot **barre** (`.proposition.deja-essayee`, `text-decoration: line-through`) : il ne se transpose
  pas a une image, qui n'a pas de texte a barrer. J'ai ecrit `M3` sans le voir ; `ines-design` l'a vu (question 2 de sa
  proposition). Ce que `M3` voulait dire, je le precise ici, a la date de ce document.
- **La propriete, qui n'est pas neuve.** La marque *"deja essayee"* (`M9`) doit se detacher de ce qui est sous elle :
  c'est ma condition bloquante `F16` du 2026-08-13 (`FICHE-bandit-manchot-additions-cp.md`), ou une marque blanche
  tombait a 1,54:1 sur les fonds des propositions et devenait un *"fantome"* ; elle a ete tenue par un mot barre a
  l'encre, mesure au-dessus de 9:1. Une demi-opacite sur une image n'a pas de contraste mesurable hors de ce que
  l'image montre : une reproduction pale (une aquarelle, un ciel, un papier clair) a pleine opacite ressemble a une
  reproduction attenuee. **Le signal depend du contenu de l'image** : c'est le fantome de `F16`, et l'enfant ne sait
  plus laquelle il a deja pressee.
- **Precision de `M3`, datee du 2026-10-08** : l'oeuvre deja essayee porte une marque de forme qui ne depend pas de
  ce que la reproduction montre ; l'attenuation peut s'y ajouter, jamais la remplacer ; rien n'est ecrit ni dessine
  dans l'image (`X3`). C'est la propriete de `F16` appliquee a une image, pas un durcissement de `M3`.
- **Pourquoi ce n'est pas un refus** : aucun ecran n'est servi, il n'y a rien a refuser. C'est une condition a
  remplir **avant que le premier paquet a reproductions soit branche** ; branche sans elle, il serait refuse sur ce
  motif.
- **Symptome pour le technique, sans en decider la forme** : `lea-dev` a pose le cadre ordinaire et l'anneau de `E2`
  sur l'image et non sur le bouton, pour ne pas toucher la dette d'etats d'`ADR-0046`. Si la meme voie porte un cadre
  en tirets sur l'image d'une proposition deja essayee, la proposition d'`ines-design` tient ; si un garde-fou la
  refuse aussi, c'est a `theo-archi` de le dire. Je ne tranche pas la voie.

### 4.3 `E1`, `E4`, `E5`, `E6`

`E1`, `E4` et `E5` changent des selecteurs, pas ce que l'enfant voit : rien a juger de ma part. `E6` (le meuble
conserve) est une question ouverte, ligne Q5 ci-dessous.

### 4.4 Ce qui reste a trancher par `ines-design` et moi avant le branchement du premier paquet a reproductions

`ART-CP-10` reste `non couvert`. Avant qu'un paquet a reproductions entre au catalogue :

| Code | Question | Qui | Quand |
|---|---|---|---|
| Q1 | Le canal de forme de l'echec (`M3`, section 4.2) : quelle marque, par quelle voie | `ines-design` propose, `theo-archi` dit la voie, je juge | avant le branchement |
| Q2 | Case egale ou image egale (`M1`, question 1 d'`ines-design`) : une reproduction large rend plus petite qu'une haute dans une case carree | moi, sur la mesure d'`ines-design` | avant le branchement |
| Q3 | Le pli de l'etat d'echec et de la revelation a 551 px de haut (question 4) | moi | avant le branchement |
| Q4 | Le fond clair identique derriere chaque reproduction (question 6, `C5` clause 1) | moi | avant le branchement |
| Q5 | Le meuble conserve ou retire pour ce type d'item (`E6`, question 5) | `theo-archi` et moi | avant le branchement |
| Q6 | Le rang annonce par une liste, un nom de groupe, la taille de la phrase de revelation (question 8) | `theo-archi` pour la structure, moi pour tout mot | avant le branchement |
| Q7 | Le releve a dix largeurs refait **sur le gabarit livre** et non sur le prototype : les ecarts `E1` a `E5` changent ce qui a ete mesure | `ines-design` | avant le branchement, apres Q1 |
| Q8 | La visibilite de chaque propriete a 132-137 px et a 84 px (question 3, regle 6 de ma section 3.4) ; la disposition "cote" (question 7) | moi | **a l'audit**, a l'ecran, sur des paires reelles |

Repondre a Q1 a Q6 est un arbitrage de forme : **0,25** a la `DEC` d'ouverture de s56, non consomme ici. L'audit du
paquet lui-meme reste a **2,5** (mon arbitrage, section 3.5). La mention de s50 reste due si la ligne ferme :
*"moitie 'rapprocher deux oeuvres montrees ensemble' seule ; la moitie 'reconnaitre des oeuvres deja rencontrees'
reste non couverte"*.

## 5. Pour `camille-po` : le statut de matrice a retenir

La legende de la matrice (section "Comment la lire") connait `non couvert`, `en cours`, `couvert-valide` et `non
couvrable en l'etat`. **`couvert-sous-reserve` n'y existe pas, et je n'en cree pas un.** Un VALIDE SOUS RESERVE dont les
reserves ne bloquent pas ferme la ligne en `couvert-valide`, reserves nommees dans la cellule : c'est ce que disent mes
fiches et la regle que la matrice applique deja (note du 2026-10-08 de s54 sur `FRA-CE1-19` ; `MAT-CE1-03`).

| Ligne | Statut a l'ouverture | Statut a retenir | Motif |
|---|---|---|---|
| `FRA-CE1-14` | `non couvert` | **`couvert-valide`** | V1 ; sans mention de couverture partielle ; reserves `ACV-CE1-R1`, `ACV-CE1-R2` a nommer dans la cellule |
| `FRA-CE1-12` | `non couvert` | **`couvert-valide`** | V2 ; sans mention de couverture partielle ; reserve `CLM-CE1-R1` |
| `EMC-CM1-03` | `non couvert` | **`couvert-valide`** | V3 ; reserve `EMC-CM1-R8` ; **mention a reproduire mot pour mot** : *"connaissances civiques et reconnaissance de situations seules ; aucun comportement, aucun engagement et aucune participation a une decision collective ne sont servis par ce produit."* |
| `EMC-CM1-06` | `non couvert` | **`couvert-valide`** | V4 ; reserve `EMC-CM1-R9` ; meme mention, mot pour mot |
| `EMC-CP-03` | `couvert-valide` | **`couvert-valide`**, inchange | V6 ; `EXC-R15` levee, `EXC-R16` ouverte (servie, non rejouee) |
| `ART-CP-10` | `non couvert` | **`non couvert`**, inchange | V5 vaut pour le paquet des familles d'oeuvres, qui ne travaille pas cette competence ; le dispositif de T3 ne sert aucun paquet |

T1 a T5 (section 3) ne touchent aucune ligne.

## 6. Le compte de la matrice

Commande de la "Methode du compte" de `MATRICE-couverture.md`, reprise telle quelle et lancee sur `af8b326e` :
`couvert-valide` 137, `en cours` 4, `non couvert` 41, `non couvrable en l'etat` 76, total 258, denominateur
atteignable 182. **137 / 182 = 75,27 % : le compte d'ouverture est exact.** Perimetre : les 258 lignes de donnees.

Les quatre lignes de V1 a V4 sont aujourd'hui `non couvert` dans la matrice (cellules lues). **Quatre lignes bougent,
`FRA-CE1-14`, `FRA-CE1-12`, `EMC-CM1-03`, `EMC-CM1-06`, et aucune autre** : `couvert-valide` 141, `non couvert` 37,
**141 / 182 = 77,47 %**. C'est le haut de la fourchette du planning (*"au mieux quatre lignes"*). `ART-CP-10` et
`EMC-CP-03` ne bougent pas. Ce chiffre est le mien, calcule depuis la commande ; celui qui fait foi sera celui de la
meme commande relancee par `camille-po` apres son geste.

## 7. Ce dont je reste la seule garde a la fin de s55

Aucun automate ne verifie, et je les tiens en jouant :

- **Francais CE1** : qu'un indice ne contient pas une proposition de son item ; qu'un indice departage ; la justesse
  des formes et des classes ; la place de l'adjectif ; la part des pluriels muets ; les roles par genre.
- **EMC CM1** : une seule reponse defendable (`EMC-5`) ; le sens d'un distracteur (`BIA-5`) ; la repartition des
  prenoms ; l'exactitude des sources `HIS-3` ; l'exactitude d'un indice contre son enonce ; un indice qui contient un
  mot propre a la bonne reponse ; la fuite d'un indice vers un autre item.
- **Exclusion CP** : qu'une bonne reponse de classement nomme ce que la situation est ; qu'un indice ecarte un choix
  qui est a l'ecran.
- **Familles d'oeuvres CP** : la consigne generique, le pouvoir de l'indice de l'item 11, le genre des metiers.
- **Dispositif d'`ART-CP-10`** : `M1`, `M2`, `M3`, `X2`, `X3`, le second cas de `X5` (recadrage, detail ou miroir de la
  reference), `X6`, et les mots de `M4` et `M5` (le garde-fou 4 exige un nom accessible non vide, pas ces mots ; je
  n'ai pas verifie que l'inventaire de `TexteAccentueDesGabaritsTest`, qui porte la phrase de `M4`, rougit si elle
  change).

## 8. Capacite

Ce document : **0,0**. La lecture de T3 etait comprise dans les 0,25 de la forme (mon complement, section 4) ; la sonde
de la section 3.2 n'est ni un audit ni un rejeu et ne leve rien. **A 4,0 sur 4,0, B 4,0 sur 4,0 : aucun rejeu a s55,
pas de troisieme instance.**

## 9. Routage

| A qui | Quoi |
|---|---|
| `camille-po` | Le tableau de la section 5 ; six stories de levee (section 2), textes exacts dans les fiches ; l'arbitrage de forme Q1 a Q6 (0,25) et l'audit du paquet (2,5) a la `DEC` d'ouverture de s56 |
| `ines-design` | Q1 et Q7 de la section 4.4 ; la precision de `M3` (section 4.2) |
| `theo-archi` | La voie d'une marque de forme sur l'image d'une proposition deja essayee (section 4.2) ; Q5, Q6 |
| `lea-dev` | `E2` accepte, `E3` non accepte pour un paquet servi : rien a changer tant qu'aucun paquet n'est branche |
| `relecteur-pedagogique` | La ponctuation de `S54-PONCT-GN-INDICES` (section 3, T3) |
| `sofia-sm` | La conclusion a reprendre dans `review.md` de s55 : six verdicts, aucun refus, quatre lignes fermables, 141 / 182 au mieux, rejeux ouverts 2,5 |

**Signe** : `nadia-prof`, s55, instance A, 2026-10-08.
