# [Sprint s57] Palier 2 - avis technique sur `PA8` (voies de `RDO-CP-R3`) et `PA7` (voies de `RCT-CE1-R1`), sur les mesures d'`ines-design`

- **Date** : 2026-10-16 16:31 (UTC+02:00), lu par `date`.
- **Auteur** : `theo-archi`. Branche `s57-theo-p2-pa78`, worktree dedie, base `e8d14ddb` (tip de `round-two`), `git status --short` vide avant ecriture.
- **Lu** : `mesure-ines-pa8-rdo-cp-r3-2026-10-16.md`, `mesure-ines-pa7-rct-ce1-r1-2026-10-16.md` et `pa8-variantes-signal-fixe.json` (`git show e7c37933:<chemin>`, branche `s57-ines-p2-pa78`, non fusionnee) ; mon `avis-theo-palier-2.md` (3.1, 3.2) ; `ADR-0054` et `ADR-0055` (sections de la police) ; `ADR-0031` et son amendement du 2026-10-16 ; `ADR-0067` et son amendement du 2026-10-15 (D.4) ; `DEC-0043` (marge de police) ; `recreo.css` de `e8d14ddb` (regle universelle 55 a 58, `.signal` 1539 a 1558, ecran a deux oeuvres 1848 a 1899, forme commune fusionnee par `aca0d90f` 1901 a 1933) ; `LaFormeCommuneDesPropositionsLonguesEstEpingleeTest` ; `LaPhraseDeRevelationDUneMancheEnImageNeSeCoupeJamaisDansUnePhraseTest` ; `fragments/manche.html` (34, 192 a 220) ; `PartieController` (77, 78, 88, 124 a 130) ; `S57-RDO-CP-R3`, `S57-RCT-CE1-R1` et l'amendement de son critere 3 (backlog) ; `cote-de-reference-forme-commune-2026-10-16.md` (resume) ; `FICHE-bandit-manchot-rapprocher-deux-oeuvres-cp.md` (rejeu du 2026-10-15, sections 2, 4, 6) ; `outils/juger-pli-deux-oeuvres.py` (en-tete).
- **Ce que ce fichier ne fait pas** : il ne choisit aucun texte (`nadia-prof`), aucune forme visuelle (`ines-design`), aucun perimetre de mesure (`camille-po`), n'engage aucun poste (`sofia-sm`), ne prononce aucun Done. Il ne modifie ni code, ni feuille, ni `ADR` : l'amendement que la voie (ii) imposerait est ecrit en 2.5 comme **proposition**.

## Resume

| Code | Avis | Section |
|---|---|---|
| `T1` | **Correction acceptee** : le plancher du signal est **219,1 px**, pas 207. Mon 207 de `avis-theo-palier-2.md` 3.1 comptait jusqu'au bord de la boite et oubliait le rembourrage de droite (12 px). Confirme par la lecture du code | 1 |
| `T2` | **La voie (i) ne touche aucun autre ecran** : ses regles ont toutes pour sujet un descendant de `.ecran-deux-oeuvres`, que seul `manche.html:34` pose. **Elle ne remet pas en cause `TPH-CE1-R1`, `GNA-CE1-R1`, `VSJ-CE1-R2`** : leurs ecrans portent la forme commune, qui exclut `.ecran-deux-oeuvres`, et le garde de la forme ne voit pas les regles de (i). La marge de 3 px ramenee a 0,3 est celle de **`RDO-CP-R1`**, sur l'ecran d'`ART-CP-10` lui-meme, pas celle d'une forme levee ailleurs | 2.1, 2.2 |
| `T3` | **Aujourd'hui, aucun test ne voit la voie (i)** : chacune des deux combinaisons mesurees, ajoutee a la feuille, passe tout `adapter-web` | 2.3 |
| `T4` | **Voie que l'architecture preserve le mieux : (iii)**, puis (i) par la combinaison `f13.25-pad8-g0`, puis (i) par `f13.75-cg1-g0`, puis (ii). (ii) n'est pas ouverte par le critere 3 de la story tel qu'il est ecrit : deux formes tiennent | 2.6 |
| `T5` | **`PA7` : la voie des textes** preserve mieux l'architecture : un paquet, aucune feuille, 73 px de marge contre 3. Le bandeau change l'habillage de 145 jeux et fait rougir le garde de la forme commune | 3 |
| `T6` | **La police du poste n'est le perimetre de garantie d'aucun `ADR` ni d'aucune `DEC`** : `ADR-0054` refuse expressement de le fixer et le route a `camille-po` et `nadia-prof`. Aucun texte ecrit ne fait non plus des polices imposees un critere. Fermer la question est une `DEC` de `camille-po`, pas un `ADR` | 3.3 |

## 1. Le plancher du signal : 219,1 px (`T1`)

Lecture du code de `e8d14ddb` :

- `*, *::before, *::after { box-sizing: border-box; }` (`recreo.css` 55 a 58) : les 15 rem (240 px) de la colonne du signal (`.ecran-deux-oeuvres > .message-jeu`, ligne 1887) **contiennent** son rembourrage.
- `.signal` (1539 a 1544) : `display: flex`, `gap: 0.75rem` (12 px), `padding: 0.5rem 0.75rem`, donc **12 px a gauche et 12 px a droite**.
- `.icone-signal` (1552 a 1558) : `width: 3rem` (48 px), `flex-shrink: 0`.
- Le verdict est un `<p>` flexible (`manche.html` 195), sans marge laterale ; sa largeur minimale est celle de sa plus longue suite insecable. Les textes servis sur cet ecran sont `MESSAGE_ECHEC` (`Ce n'est pas ça. Essaie encore !`, ligne 78), `MESSAGE_REVELATION` (`Ce n'est pas ça.`, ligne 88), `MESSAGE_BRAVO` (`Bravo !`, ligne 77) : la plus longue suite est *"Ce n'est pas ça."*, 135,1 px **mesuree par `ines-design`** (je ne l'ai pas mesuree).

Somme : 12 + 48 + 12 + 135,1 + 12 = **219,1 px**. Sous cette largeur, le verdict sort du rembourrage ; sous 207,1 il sort de la boite coloree. Mon 207 venait de 240 - 33, ou les 33 px de reste (rapport R8, 6.2) se lisaient jusqu'au bord de la boite : il omettait le rembourrage de droite. `ines-design` a raison, et son `verdictResteDansLeSignal` de 32,9 a 240 px est coherent avec 240 - 207,1.

Consequence sur mon avis precedent : j'ecrivais que (i) avait *"une marge nulle par arithmetique"* (481 - 12 - 262 = 207 pour le signal). Avec 219,1, **aucune largeur de signal ne suffit seule a 720 avec barre** (481 - 12 - 262,2 = 206,8 < 219,1, manque 12,3 px), ce qu'`ines-design` mesure (`PA8-4`). C'est pour cela que ses deux combinaisons qui tiennent rognent aussi l'ecart signal-texte ou le rembourrage.

**Marge restante du verdict dans les deux combinaisons, par arithmetique sur ces termes** (pas une mesure) : `f13.75-cg1-g0`, signal de 220 px, 0,9 px au rembourrage de droite (12,9 px a la boite, ce qu'`ines-design` mesure) ; `f13.25-pad8-g0`, signal de 212 px pour 8 + 48 + 12 + 135,1 + 8 = 211,1, 0,9 px au rembourrage (8,9 px a la boite, mesure). **Les deux mettent la forme C du verdict a moins d'un pixel de son rembourrage, sur une police.**

## 2. `PA8` : les trois voies de `RDO-CP-R3`

### 2.1 Portee de la voie (i) : un seul ecran

- `git grep -n "ecran-deux-oeuvres" -- 'adapters/*/src/main/*' 'bootstrap/src/main/*' 'domain/*' 'application/*'`, feuille exclue : **une ligne**, `fragments/manche.html:34`, `th:classappend="${propositionsEnImage} ? 'ecran-deux-oeuvres'"`. Aucun JavaScript, aucun autre gabarit ne pose la classe.
- `propositionsEnImage` (`PartieController` 127 a 130) est vrai si toutes les propositions commencent par `oeuvre-` ; un seul jeu du catalogue en sert (`LesPropositionsEnImageAtteignentLEcranSansJamaisSonIdentifiantTest`, et la lecture des paquets d'`ines-design`, `PA8-2`).
- Les sept variantes injectees (`pa8-variantes-signal-fixe.json`) ont toutes pour selecteur `.ecran-deux-oeuvres`, `.ecran-deux-oeuvres > .message-jeu` ou `.ecran-deux-oeuvres .signal`, sous `@media (min-width:720px)`. **Aucune ne peut s'appliquer a un element qui n'a pas un ancetre de classe `ecran-deux-oeuvres`** (ou qui ne l'est pas, pour la premiere).
- Dans la feuille, les 28 occurrences du nom sont soit des sujets `.ecran-deux-oeuvres ...` (lignes 1852 a 1898), soit l'exclusion `:not(.ecran-deux-oeuvres)` de la forme commune (1909 a 1932), soit un commentaire (1755, 1902).

**Ce que cette portee ne couvre pas** : une variante qui serait ecrite **sans** le prefixe, par exemple `.signal { padding-left: 0.5rem }` pour la combinaison `f13.25-pad8-g0`, changerait le signal des 145 jeux `bandit-manchot` et des ecrans de designation et de saisie. Le garde de la forme commune ne la verrait pas (2.2) ; elle doit etre ecrite sous `.ecran-deux-oeuvres`, comme `ines-design` l'a injectee.

### 2.2 `TPH-CE1-R1`, `GNA-CE1-R1`, `VSJ-CE1-R2` : non touchees

- Leurs jeux sont a propositions ecrites : l'ecran ne porte pas `.ecran-deux-oeuvres`, et les regles de (i) ne s'y appliquent pas (2.1).
- La forme commune (R3-1 a R3-5) a pour sujet `.ecran-de-jeu:has(.propositions):not(.ecran-deux-oeuvres)` : ses colonnes de 15 rem et son ecart de 0,75 rem sont des **declarations distinctes** de celles de L3 (1887, 1888). Changer L3 ne change pas R3-4.
- `LaFormeCommuneDesPropositionsLonguesEstEpingleeTest` derive sa population des regles dont le selecteur contient `:not(.ecran-deux-oeuvres)` : **une regle de (i) n'y entre pas** et ne peut pas le faire rougir. Mesure en 2.3.
- **La marge de 3 px ramenee a 0,3 px** (`PA8`, section 6, `*-cg1*`) est celle du bas de `.indice` apres une erreur **sur l'ecran d'`ART-CP-10`** a 744 (548 puis 550,7). C'est la cote de levee de **`RDO-CP-R1`** (fiche, rejeu du 2026-10-15, section 4 : *"La marge est de 3 px, sur une police"*), gardee sur releve par `J3` de `juger-pli-deux-oeuvres.py`. Elle n'a rien de commun avec le 548 de la voie du bandeau de `PA7`, qui est une cote de `ce1-texte-narratif`. **`f13.75-cg1-g0` ne remet en cause aucune levee de TPH, GNA ou VSJ ; elle consomme 2,7 px de la marge de `RDO-CP-R1`**, qui reste levee a la lettre (550,7 au plus 551) avec 0,3 px.
- Point de sequencement, hors de (i) : TPH, GNA et VSJ se rejouent sur le jar de la forme commune (`planning.md`, p2-8, p2-9, p2-11). Une regle de (i) fusionnee avant ces rejeux ne change pas leurs ecrans.

### 2.3 Ce que les tests voient aujourd'hui, mesure

Feuille de `e8d14ddb` plus la combinaison ajoutee en fin de fichier, sous `@media (min-width: 720px)`, puis restauration par `git checkout` et `git status --short` vide. Commande : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-web -am test`, **perimetre `adapter-web` et ses amonts (`domain`, `application`), pas le depot**, JDK 21.0.10 (`java.version` des rapports Surefire d'`adapter-web`).

| Ajout | Resultat |
|---|---|
| `f13.75-cg1-g0`, texte de `pa8-variantes-signal-fixe.json` | `BUILD SUCCESS` : 120, 31 et **501 tests, 0 echec** |
| `f13.25-pad8-g0`, idem | `BUILD SUCCESS` : 120, 31 et **501 tests, 0 echec** |

**Lecture** : la valeur de 15 rem de L3 (ligne 1887), son ecart de 0,75 rem (1888) et l'ecart de 1,5 rem de la grille (1867) ne sont epingles par aucun test (`git grep -n "15rem"` sur les tests ne rend que `LaFormeCommuneDesPropositionsLonguesEstEpingleeTest`, qui juge R3-4). La cote de levee de `RDO-CP-R3`, si (i) est retenue, serait mesuree sur des valeurs que rien ne fige : un lot ulterieur qui remettrait le signal a 15 rem, ou elargirait son rembourrage, rendrait le debordement sans un rouge.

### 2.4 Les trois voies, risque par risque

| Voie | Ce qu'elle touche | Risque | Ce qui n'est pas mesure |
|---|---|---|---|
| (iii) phrase plus courte | le noeud de texte de `manche.html` 214 ; l'inventaire de `TexteAccentueDesGabaritsTest` (`ADR-0039`) ; dans le garde de D.4, les constantes `PHRASE_DE_M4`, `PREMIERE_PHRASE`, `SECONDE_PHRASE` et les deux comptes litteraux de U+00A0 (`!= 5`, `!= 1`), qui sont des conditions de `defauts()` et non des lignes `assert` (critere 6 tenu). **Aucun `ADR`** : D.4 dit la forme de la phrase, pas ses mots. Aucune feuille, aucun autre ecran | la phrase neuve doit tenir dans **229 px a 720 avec barre**, sous la police du poste, avec une marge que personne n'a fixee ; un compte litteral oublie rougit le garde (visible, pas muet) | la largeur de la phrase neuve : mesure d'`ines-design` sur le texte de `nadia-prof`, aux trois largeurs et dans les deux modes |
| (i) `f13.25-pad8-g0` | deux regles sous `.ecran-deux-oeuvres` : colonne du signal 13,25 rem et ecart nul ; rembourrage lateral du signal 0,5 rem. **Meuble et cases inchanges**, donc `RDO-CP-R1` garde ses 3 px. Sous `C1'` : sujet dans le message, aucun etat nomme (point 2), tenu | phrase a 6,8 px de sa boite a 720 avec barre ; verdict a 0,9 px de son rembourrage (1) ; texte colle au signal (forme visuelle, `ines-design` et `nadia-prof`) ; le signal de cet ecran differe de celui des 145 autres jeux (rembourrage 8 px au lieu de 12, largeur 212 au lieu de 240) | la combinaison sous les neuf polices (`PA8`, section 9) ; Verdana (306,5 px) et Courier New (360,1) ne tiennent dans aucune colonne mesuree |
| (i) `f13.75-cg1-g0` | deux regles sous `.ecran-deux-oeuvres` : ecart de la grille 1 rem ; colonne du signal 13,75 rem et ecart nul. Le sujet `.ecran-deux-oeuvres` est hors du message et ne nomme aucun jeton du message : `C1'` point 1 tenu | **consomme la marge de `RDO-CP-R1`** : 548 a 550,7 (0,3 px) ; `J2` de 528 a 530,7 ; cases de 144 a 146,7 px. Verdict a 0,9 px de son rembourrage. Deux reserves du meme ecran tenues chacune a moins d'un pixel | idem, plus l'effet des 8 px de meuble sur l'ecart reference-enonce |
| (ii) coupure dans la premiere phrase | une U+00A0 du gabarit redevient une espace ordinaire ; **amendement de D.4 et de son garde dans le meme commit** (2.5) | la seconde ligne porte alors la fin de la premiere phrase, une espace et *"Appuie dessus."* (131,9 px) : si elle ne tient pas dans 229 px, `M4` passe a trois lignes, son bas de 528 a environ 558 a 744 (30 px par ligne, fiche section 2), et **`J2`, la barre du refus, rougit** : ce n'est plus une reserve, c'est le refus de s56 qui revient | la place de la coupure (`nadia-prof`), puis la tenue sur deux lignes a 720, 744 et 768 dans les deux modes (`Q-R7`, deuxieme moitie) |

### 2.5 Proposition d'amendement de D.4, seulement si (ii) est ouverte

Non verse. A verser par moi dans le commit du developpeur qui pose la coupure, quand `nadia-prof` a nomme sa place :

> **Amendement du AAAA-MM-JJ (`theo-archi`) - une coupure nommee dans la premiere phrase de `M4`.** `S57-RDO-CP-R3`, critere 3 (b) : aucune forme ne tient la premiere phrase insecable dans sa colonne (`<chiffres de la mesure>`). **La phrase de `M4`, precisee** : U+00A0 entre les mots de chaque phrase, **sauf entre `<mot>` et `<mot>` de la premiere phrase**, place choisie par `nadia-prof` (`<fichier>`) ; une espace ordinaire a cette place et une entre les deux phrases, aucune ailleurs. **Regle executable**, meme test : toute espace ordinaire suit une fin de phrase **ou se trouve a la place nommee** ; la premiere phrase porte quatre U+00A0 et une espace ordinaire a cette place. Morsures, citees dans le rapport du developpeur : une U+00A0 remise a la place nommee ; une espace ordinaire posee a une autre place de la premiere phrase. **Ce qui n'est pas couvert** : la tenue de `M4` sur deux lignes, gardee sur releve par `J2`. **Compte ArchUnit inchange.**

### 2.6 Avis, sur l'architecture seule (`T4`)

1. **(iii)** a le moins de pieces mobiles : un noeud de texte, aucune feuille, aucun `ADR`, aucun autre ecran, aucune marge d'une autre reserve consommee. Le choix du texte n'est pas le mien.
2. **(i) par `f13.25-pad8-g0`** si (iii) n'est pas retenue : elle laisse le meuble et `RDO-CP-R1` intacts. **Elle doit etre accompagnee du test de 2.7 dans le meme commit**, faute de quoi la cote de levee repose sur des valeurs que rien ne fige (2.3).
3. **(i) par `f13.75-cg1-g0`** : meme test, et elle porte deux reserves du meme ecran a moins d'un pixel chacune.
4. **(ii)** en dernier. Le critere 3 de la story l'ouvre *"si aucune forme ne tient les deux"* ; `ines-design` en mesure deux qui tiennent (`PA8-6`). **Lue a la lettre, (ii) n'est pas ouverte** ; elle le devient si `nadia-prof` refuse les deux combinaisons a son veto (le texte colle au signal en est le point visible). Sa marge de defaillance est la barre du refus, pas une reserve.

### 2.7 Le test qui accompagne la voie (i)

Pas une `ArchRule` : l'objet est une feuille de style. Un test d'`adapters/adapter-web`, ecrit par le developpeur dans le commit de la regle, sur le patron de `LaFormeCommuneDesPropositionsLonguesEstEpingleeTest` (`ADR-0013`, trois obligations). Aucun `ADR` : comme la forme commune (`TP3` d'`avis-theo-palier-2.md`), c'est une mise en page mesuree, pas une decision de structure.

- **Clause 1, epinglage** : population derivee = toute regle maison sous `@media (min-width: 720px)` dont le selecteur commence par `.ecran-deux-oeuvres` et nomme `.message-jeu` ou `.signal`, plus la regle de la grille `.ecran-deux-oeuvres` si `f13.75-cg1-g0` est retenue. Valeurs attendues : celles de la combinaison mesuree. Population vide, regle attendue absente, regle ou declaration non mesuree : echecs.
- **Clause 2, les termes du plancher du signal, sur tous les ecrans** : `.signal` `padding` et `gap`, `.icone-signal` `width`, `.signal .verdict` `font-size`, a leur valeur de `e8d14ddb`. Ces regles servent 145 jeux : un lot qui les change pour une autre raison rougira ce test. **C'est voulu** : le plancher de 219,1 px en depend, et le changement doit declencher une remesure de cet ecran.
- **Clause 3, inegalite** : largeur declaree du signal (rem convertis a 16 px) >= rembourrage gauche + largeur de l'icone + `gap` + `VERDICT_LE_PLUS_LARGE_PX` + rembourrage droit, ou `VERDICT_LE_PLUS_LARGE_PX = 135.1` est une constante nommee, **mesuree par `ines-design` le 2026-10-16 sous la police du poste**, et ecrite comme telle. Elle interdit reellement : la colonne du signal descendue a 13,5 rem (216 < 219,1), ou un rembourrage remonte a 0,75 rem avec 13,25 rem (212 < 219,1).
- **Mise a l'epreuve, sur la feuille reelle** : la colonne du signal remise a 15 rem (clause 1 rouge) ; `.signal` a `padding: 0.5rem 1rem` (clause 2 rouge) ; la colonne a 13,5 rem avec l'epinglage mis a jour (clause 3 rouge seule).
- **Ce qu'il ne garde pas, ecrit dans sa Javadoc** : la largeur de la phrase de `M4` dans sa colonne (la colonne depend de la largeur du meuble, donc de la fenetre : aucun automate du reacteur ne rend une page) ; toute police autre que celle de la mesure ; le critere 1 de la story, que `J2` et `J4` ne voient pas (story, critere 5). Etendre le juge pur est a `yanis-qa`.

## 3. `PA7` : textes de `nadia-prof` ou bandeau

### 3.1 Architecture

| | Textes de `nadia-prof` | Bandeau a 1,75 rem |
|---|---|---|
| Ce qui change | `ce1-texte-narratif.yaml`, items 13 et 17 (bonne reponse et distracteurs) et 18 (distracteurs). Aucun gabarit, aucune feuille, aucun `ADR` | une regle `.ecran-de-jeu:has(.propositions):not(.ecran-deux-oeuvres) .cabinet-bandeau { height: 1.75rem }` sous `@media (min-width: 720px)`. Aucun `ADR` |
| Ecrans touches | un jeu, `bandit-manchot-texte-narratif-ce1` | l'habillage du meuble des **145** jeux `bandit-manchot` a propositions ecrites (compte de `camille-po` sur `e7497db7`, backlog, clause `Q-R4`), dont ceux de TPH, GNA et VSJ, a rejouer |
| Tests qui doivent changer | `PaquetCe1TexteNarratifTest` vert dans le meme commit (`LON-CE1-3`, `-4`, `-5`, `RCT-CE1-2`) : **non lance sur ces textes** (`PA7`, section 6) | voir 3.2 |
| `C1'`, `C2`, `C3` | sans objet | tenues : le sujet est hors du message et ne nomme aucun etat ; rien ne masque rien |
| Marge, police du poste, mesuree | **73 px** (478), 120 couples sur 120 | **3 px** (548), 120 couples sur 120, sur un seul jeu |

### 3.2 Ce que le bandeau fait aux gardes, mesure

Meme methode qu'en 2.3, la regle du bandeau ajoutee en fin de feuille :

`BUILD FAILURE` : 501 tests d'`adapter-web`, **3 echecs** dans deux classes.

- `LaFormeCommuneDesPropositionsLonguesEstEpingleeTest.laFormeCommuneEstCelleQuiAEteMesureeSurLaProduction` : *"... .cabinet-bandeau n'est pas une regle de la forme mesuree : la cote de reference ne la contient pas"*.
- `CabinetDeLaMachineTest.chaqueSelecteurEpingleEstEprouveParLeJeuFautif` : le jeu fautif n'eprouve pas le selecteur neuf (attendu, `avis-theo-palier-2.md` 3.2).
- `CabinetDeLaMachineTest.lesDimensionsDuCabinetSontExprimeesEnRemOuEnPourcentJamaisEnPixels` : *"le selecteur ... .cabinet-bandeau doit exister dans le CSS maison"*. **Non prevu dans mon avis precedent.** Cause lue : la population vient de `reglesMaison` (qui garde les regles-`@`), la verification de `blocsMaison` (`TailleDesTextesEtBoutonsTest.blocsParSelecteur`, lignes 185 a 200), qui decoupe la feuille sur `}` et donne a la **premiere** regle d'un bloc `@media` la condition du media pour cle. Une regle du cabinet ecrite en premiere position d'un `@media` entre dans la population et n'est jamais trouvee. Le rouge est bruyant, pas muet ; mais la voie du bandeau oblige soit a corriger cette lecture dans le meme commit, soit a placer la regle apres une autre dans un bloc `@media`, ce qui la ferait passer pour une raison de position.

Le selecteur du bandeau contient `:not(.ecran-deux-oeuvres)` : il entre dans la population de `LaFormeCommuneDesPropositionsLonguesEstEpingleeTest`. **Choisir le bandeau, c'est donc changer la forme commune elle-meme** : le developpeur ajoute la declaration a `LA_FORME`, et la cote de reference d'`ines-design` (`CF10`, 40 jeux, et la clause `Q-R4`) devient la mesure d'une autre forme que celle qui sera servie. **Une hauteur reduite au-dessus du contenu ne peut que le remonter** (aucun etat ne descend par construction) ; la clause de non-regression reste donc verte par construction pour le pli, pas pour l'habillage, que `nadia-prof` garde (`L1`).

### 3.3 La police du poste n'est le perimetre d'aucune garantie ecrite (`T6`)

- **`ADR-0054`**, *"Ce que je nomme sans le resoudre"* : *"ce n'est pas une question de garde-fou, c'est une question de perimetre de mesure, donc elle appartient a `camille-po` et a `nadia-prof`, pas a moi"* ; il ne l'ecrit *"ni comme une regle, ni comme une dette technique"*. `ADR-0055` reprend la meme reserve. **Aucun `ADR` ne fixe la police du poste comme perimetre de la garantie de la forme**, et je ne peux pas l'y ecrire : la largeur d'un mot dans une police que le depot ne contient pas n'est pas une propriete du depot.
- **`DEC-0043`** (`camille-po`, s19) va dans l'autre sens pour un cas : pour `SYN-16`, elle *"n'accepte pas un plafond a marge nulle"* sur une seule pile de polices, et exige une marge de police dont la valeur est laissee a `ines-design` et `nadia-prof`. C'est un precedent produit, ecrit pour une story, pas une regle generale.
- **Les criteres de `S57-RCT-CE1-R1`** (1 et 4) disent *"rendu a 744 x 551"* sans nommer de police : ils se lisent sur la pile servie, rendue sur le poste de mesure. **Aucun texte ecrit ne fait des polices imposees un critere** ; `ines-design` les ecrit elle-meme comme une sensibilite (*"qui ne remplacent pas un appareil reel"*).
- **Fait qui pese sur la decision, sans la prendre** : la pile servie commence par `system-ui` (Reboot, `ADR-0014`). La police du poste de mesure n'est donc celle d'aucun appareil de la cible par construction ; une garantie bornee a elle ne couvre aucune tablette nommee.
- **A qui** : fermer la question est une `DEC` de `camille-po` (perimetre de mesure), la barre est a `nadia-prof`. Proposition de formulation, si elle veut la fermer : *"La cote d'une forme vaut pour la pile servie rendue sur le poste de mesure ; les polices imposees sont une sensibilite rapportee, pas un critere de levee."*

**Par arithmetique, a titre d'information** (pas une mesure) : sur `r3-polices-rct.json` (jar a la forme commune, textes du depot, item 18, 744, un tirage, cite par `PA7` section 4), le bandeau retire 4 px a chaque cote : 552 devient 548 pour la police du poste, Arial, Georgia, Trebuchet MS et Tahoma ; Times New Roman reste a 474 ; Verdana, Courier New et Comic Sans MS restent a 578. Soit **6 polices sur 9**, le meme compte que les textes mesures a 744 (6 sur 9), avec 3 px de marge au lieu de 73 et pas les memes polices en echec (Comic Sans MS au lieu de Georgia).

### 3.4 Avis, sur l'architecture seule (`T5`)

**La voie des textes.** Elle ne touche qu'un paquet, aucun garde de feuille, aucune forme partagee, et elle laisse 73 px la ou le bandeau en laisse 3. Le bandeau change la forme commune pendant que trois reserves s'apprennent a etre rejouees sur elle, pour trois items d'un seul jeu. Risque de la voie des textes : le test du paquet, non lance sur ces textes ; c'est au developpeur de le rendre vert dans le meme commit, sans assertion retiree. Aucune des deux voies ne garantit un contenu futur (B.6, point 5). Le choix est a `nadia-prof`.

## 4. Regle ArchUnit

**Aucune.** Compte sur `e8d14ddb`, methode de 3.4 de mon avis sur le poste 8 (fichiers `*.java` suivis par `git ls-files`, motifs `^\s*(public |private |protected )?static final ArchRule ` et `^\s*@ArchTest\s*$`) : **41** et **38**, dans 16 fichiers. Le test de 2.7, s'il est ecrit, est un test de feuille, pas une `ArchRule` : le compte ne change pas.

## 5. Jobs de `ci.yml` lances pour ce commit

Lances sur ce worktree, ce fichier indexe, avant le commit. Je ne deduis aucun job que je n'ai pas lance.

- **`build`** : `bash outils/mvn-jdk21.sh -B clean verify` depuis la racine, sans `-pl` : `BUILD SUCCESS`, exit 0. **2669 tests, 0 echec, 0 erreur, 0 ignore** (120, 31, 501, 1630, 381 et 6 d'integration, lignes `Tests run` de la sortie Maven). JDK **21.0.10** (`java.version` des 60 rapports Surefire de `bootstrap`, une seule valeur). Le build a lu ce fichier avant l'ajout de cette section, qui ne contient aucun tableau. Etape *"Package du fat jar"* non lancee.
- **`chemins`** : `verifier-chemins-test.sh` sort 0 ; `git ls-files` (moins le test du garde-fou) passe a `verifier-chemins.sh` : sort 0.
- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh` et `verifier-garde-fou-accent-test.sh` sortent 0 (21 cas pour le dernier) ; le sujet de ce commit passe dans `verifier-prefixes.sh` : sort 0. Le controle du proprietaire du garde-fou de l'accent n'est pas lance : aucun de ses chemins n'est touche.
- **`outils`** : les trois balayages (6, 2 et 5 fichiers), 13 juges lances avec `python`, 13 sortis 0.

Les mesures partielles de 2.3 et 3.2 (`-pl adapters/adapter-web -am`) nomment leur perimetre la ou elles sont citees. Aucun de ces resultats n'est celui de la CI distante, que je n'ai pas lue.

## Decisions prises

**Aucune `ADR` ni `DEC` n'est creee ni amendee.** Ce fichier corrige un chiffre de mon avis precedent (`T1`), propose un texte d'amendement conditionnel (2.5) et une formulation a `camille-po` (3.3).
