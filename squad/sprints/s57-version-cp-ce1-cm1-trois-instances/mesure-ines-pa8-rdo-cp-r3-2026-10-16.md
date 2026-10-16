# s57 - palier 2 : mesure de PA8, la voie (i) de RDO-CP-R3 (largeur de la colonne du texte de M4)

- **Auteure** : `ines-design`. **Date** : 2026-10-16, 16:16 (UTC+02:00), lue par `date`.
- **Arbre** : branche `s57-ines-p2-pa78`, issue de `round-two` a `e8d14ddb`, worktree dedie. Code servi identique a celui de `0c0d2142` : `git diff --stat 0c0d2142 e8d14ddb -- pom.xml domain application adapters bootstrap` ne rend aucune ligne.
- **Ce que ce fichier est** : la mesure que `Q-R7` (`avis-theo-palier-2.md`, section 3.1) demande pour la voie (i). Il compte des pixels. Il ne prononce aucun Done, ne leve pas `RDO-CP-R3` et ne choisit pas entre (i), (ii) et (iii) : le veto est a `nadia-prof`. Aucun fichier de `domain`, `application`, `adapters/*/src/main`, `bootstrap` n'est touche ; les regles mesurees sont injectees a l'execution, jamais ecrites dans `recreo.css`.

## Resume

| Code | Constat | Section |
|---|---|---|
| PA8-1 | Cote de `RDO-CP-R3` reproduite sur le jar de `e8d14ddb` aux six largeurs et modes de la fiche : premiere phrase de M4 **262,2 px**, colonne 229 px (720 avec barre) ou 244 px (720 sans barre, 744, 768) | 3 |
| PA8-2 | Un seul jeu sert des propositions en image : `bandit-manchot-rapprocher-deux-oeuvres-cp` (six items). `.ecran-deux-oeuvres` n'est pose que par `manche.html` ligne 34 | 2 |
| PA8-3 | **Colonne necessaire : 262,2 px a toute largeur.** Avec le signal a 15 rem (240 px) et l'ecart de 12 px, il manque 33,2 px a 720 avec barre et 18,2 px a 720 sans barre, 744 et 768 | 4 |
| PA8-4 | La largeur du signal ne peut pas descendre sous **219,1 px** sans que le verdict sorte de son rembourrage (douze px de rembourrage, icone de 48, ecart de 12, phrase du verdict de 135,1). Le signal garde une largeur fixe : en largeur naturelle il mesure 145,1, 219,1 ou 353,9 px selon l'etat | 4, 5 |
| PA8-5 | **A 744, 768 et 720 sans barre**, un signal fixe de 13,75 rem (220 px) suffit : colonne 264 px, **1,8 px de marge**. **A 720 avec barre, il ne suffit pas** : colonne 249 px, 13,2 px de debordement de la boite, 1 px de defilement horizontal | 6 |
| PA8-6 | A 720 avec barre, **deux combinaisons tiennent** (marge de 6,8 px) : signal 13,75 rem + ecart signal-texte nul + `column-gap` de la grille a 1 rem ; signal 13,25 rem + ecart nul + rembourrage du signal a 0,5 rem. L'ecart nul colle le texte au signal | 6 |
| PA8-7 | **La phrase reste sur deux lignes a 744** dans toutes les variantes mesurees (jamais trois : chaque phrase est insecable), bas de M4 a 528 (530,7 avec la grille a 1 rem) | 7 |

## 1. Arbre, jar, methode

- **Jar** : copie hors depot de `bootstrap/target/recreo.jar` de `e8d14ddb` (`bash outils/mvn-jdk21.sh -B clean package -DskipTests`), SHA-256 `e6da8401b754877e89937e4d9b82c5f2bccb5008acaa03c44fcf87d60873611e`, feuille servie `c312cd1b2ebbfc4a52508b250f1b328943e1e3a6384dba95c3897a59045f3ef6` (sans retours chariot, celle de `0c0d2142`). JDK 21.0.10, port 18543, PID 33548 lu dans le journal.
- **Methode** : `pilote-pa8.mjs`. Il joue `bandit-manchot-rapprocher-deux-oeuvres-cp` (`/niveaux/CP/matieres/arts-plastiques`, `htmx.ajax` sur `/parties/<jeu>`) jusqu'a avoir, pour chacune des six oeuvres de reference, deux erreurs puis la revelation (et un etat juste). A chaque etat, **sur le meme DOM**, a chaque largeur (720, 744, 768, 992, 1200 ; hauteur 551, `mobile: false`) et dans deux modes, avec barre de defilement classique et sans (`html { scrollbar-width: none }` : `Emulation.setScrollbarsHidden` ne change pas la largeur utile en mode sans tete), pour chaque variante de feuille (un `<style>` active a la fois, apres la feuille servie) : boites par `getBoundingClientRect`, mots par `Range.getClientRects` (un mot = une suite sans espace ordinaire, donc chaque phrase de M4 et du verdict, jointes par U+00A0, est un mot). Chrome 155.0.8059.40 sans tete, Node v24.12.0.
- **Critere de levee lu** : `FICHE-bandit-manchot-rapprocher-deux-oeuvres-cp.md`, section 6 : a 720, 744 et 768 x 551, a la revelation, sur les six items, avec une barre classique et sans barre peinte, le bord droit de chaque mot de `.reponse-revelee` est au plus au bord droit de sa boite et `scrollWidth` egale `clientWidth`, la coupure restant entre les deux phrases. La colonne **Tient** de la section 6 applique ce critere. Je ne le juge pas, je le compte.
- **Releves** (dans `squad/conformite/releves-s57/`) : `pa8-signal-fixe.json` (sept variantes a signal fixe, six items, quatre etats), `pa8-signal-auto.json` (variantes a signal en largeur naturelle, section 5), `pa8-polices.json` (section 8) ; regles injectees : `pa8-variantes-signal-fixe.json`, `pa8-variantes-signal-auto.json` ; resumes : `resume-pa8.mjs`, `resume-pa8-etats.mjs`, `resume-polices-pa8.mjs`.

## 2. Perimetre : un seul jeu sert des propositions en image

- `git grep -n "ecran-deux-oeuvres" -- adapters/adapter-web/src/main/resources/templates` : une seule ligne, `fragments/manche.html:34`, `th:classappend="${propositionsEnImage} ? 'ecran-deux-oeuvres'"`.
- `PartieController.propositionsEnImage` (lignes 127 a 130) : vrai si et seulement si **toutes** les propositions de la manche commencent par `oeuvre-` (`PREFIXE_DE_REPRODUCTION`, ligne 124).
- Dans le catalogue servi : lecture des 61 paquets de `adapters/adapter-content/src/main/resources/contenu/` ; les seuls items dont toutes les propositions (bonne reponse et distracteurs) commencent par `oeuvre-` sont les **six** de `cp-rapprocher-deux-oeuvres.yaml`, paquet d'un seul jeu, `bandit-manchot-rapprocher-deux-oeuvres-cp`. `git grep -l "oeuvre-"` sur ce dossier nomme cinq autres paquets (`cm1-arts-decrire-une-oeuvre-lexique`, `cm1-hda-decrire-lexique`, `cm1-hda-epoque-civilisation`, `cm1-histoire-des-arts-relier-contexte`, `cp-decrire-une-oeuvre`) : ils citent `oeuvre-` dans leur texte ou un identifiant d'illustration, aucun n'en fait une proposition (verifie par la lecture des items ci-dessus).

Toutes les regles mesurees ci-dessous ont pour sujet `.ecran-deux-oeuvres` : elles ne peuvent donc changer la largeur d'aucun autre ecran. **Je n'ai pas rendu un autre jeu pour le verifier** : c'est une consequence de la portee des selecteurs et du `git grep`, pas une mesure.

## 3. Cote de reference (jar de `e8d14ddb`, sans regle)

A la revelation, six items, `.reponse-revelee` (M4) :

| Largeur, mode | Largeur utile | Meuble | Colonne de M4 | Phrase 1 | Phrase 1 au-dela de la boite | Au-dela de l'ecran, `scrollWidth - clientWidth` | Bas de M4 |
|---|---|---|---|---|---|---|---|
| 720, barre | 705 | 481 | 229 | 262,2 | 33,2 | 21,2 ; 21 | 523 |
| 720, sans barre | 720 | 496 | 244 | 262,2 | 18,2 | 6,2 ; 6 | 528 |
| 744, barre | 729 | 496 | 244 | 262,2 | 18,2 | 1,7 ; 2 | 528 |
| 744, sans barre | 744 | 496 | 244 | 262,2 | 18,2 | non ; 0 | 528 |
| 768, barre et sans | 753 et 768 | 496 | 244 | 262,2 | 18,2 | non ; 0 | 528 |
| 992 et 1200 | 977 et 1185 | 736 et 916 | 484 et 664 | 262,2 | non (une ligne) | non | 542 et 494 |

**Ces valeurs sont celles de la fiche** (section 2 du rejeu du 2026-10-15 : 33,2 ; 21,2 ; 18,2 ; 6,2 ; 1,7 ; colonne 229 et 244) : l'instrument les reproduit au dixieme de pixel. Les six items rendent chacun la meme valeur, et la cote de 720 avec barre est rendue **identique par cinq releves successifs** (aucune valeur transitoire ; deux sont verses, `pa8-signal-fixe.json` et `pa8-signal-auto.json`). Le premier releve de `sans barre` etait faux (l'emulation de barre masquee ne change pas la largeur utile) : il est ecarte, le mode est refait par `scrollbar-width: none`, et les valeurs de `sans barre` ci-dessus viennent des releves suivants.

## 4. Largeur necessaire

- **Colonne** : la premiere phrase, *"C'est cette oeuvre qui va avec."*, est un seul mot insecable (ADR-0067, amendement du 2026-10-15) de **262,2 px**, a toute largeur. La colonne doit donc mesurer au moins 262,2 px. Aujourd'hui : 229 (720 avec barre), 244 (les autres). **Manque : 33,2 et 18,2 px.**
- **D'ou vient la colonne** : meuble moins signal (15 rem, 240 px) moins ecart (0,75 rem, 12 px). Elargir le meuble vers la droite n'est pas possible : a 744 avec barre son bord droit est a 712,5 pour une largeur utile de 729, et l'allonger de 18,2 px le ferait sortir de 1,7 px. Vers la gauche, le `column-gap` de la grille lui rend 8 px si on le ramene de 1,5 a 1 rem (mesure en section 6). Les autres leviers sont le signal, l'ecart de 12 px et le rembourrage du signal.
- **Ce que le signal ne peut pas ceder** : sa boite contient 12 px de rembourrage, l'icone (48 px), 12 px d'ecart, le verdict, 12 px de rembourrage. La phrase la plus large du verdict, tous etats confondus, est *"Ce n'est pas ca."* (135,1 px) ; l'autre, *"Essaie encore !"*, mesure 129,4. **Largeur minimale du signal : 12 + 48 + 12 + 135,1 + 12 = 219,1 px**, soit 207,1 si le texte touche le bord droit de la boite coloree (le chiffre de 207 de `avis-theo-palier-2.md`, qui ne compte pas le rembourrage de droite). Verifie : `verdictResteDansLeSignal` rend 32,9 a 240 px, soit 20,9 au-dela du rembourrage.
- **Somme** : 219,1 + 12 + 262,2 = **493,3 px** de meuble pour tenir. A 496 il reste 2,7 px. A 481 (720 avec barre), il manque 12,3 px : **aucune largeur de signal ne suffit seule a 720 avec barre**, il faut aussi rogner l'ecart signal-texte, le rembourrage du signal ou la colonne de gauche de la grille.

## 5. Premier essai, ecarte par la mesure : le signal en largeur naturelle

`grid-template-columns: max-content minmax(0, 1fr)` donnait, a la revelation, un signal de 219,1 px et une colonne de 264,9 px a 744. Il ne tient pas dans les autres etats (`pa8-signal-auto.json`, six oeuvres) : le signal mesure **145,1 px a l'etat juste** (*"Bravo !"*), **353,9 px apres une erreur** (*"Ce n'est pas ca. Essaie encore !"* sur une ligne) et 219,1 a la revelation, et la colonne de texte part de 582,4 px apres une erreur contre 447,6 a la revelation (744, barre). Le texte de l'indice et de la revelation changerait donc de place entre deux etats d'un meme ecran. **Toute variante ci-dessous donne au signal une largeur fixe**, la meme dans les quatre etats (verifie : 220 px, 212 px, selon la variante, dans `juste1`, `echec1`, `echec2` et `revelation`).

## 6. Variantes a signal fixe, a la revelation

Toutes sous `@media (min-width: 720px)`, sujet `.ecran-deux-oeuvres`. Valeur : debordement de la phrase 1 au-dela de sa boite (negatif = marge), puis `scrollWidth - clientWidth`, six items identiques.

| Variante | Ce qui change par rapport a la feuille servie | 720 barre | 720 sans | 744 barre | 744 sans, 768 | Cases et pli |
|---|---|---|---|---|---|---|
| `f13.75` | signal 13,75 rem (220 px au lieu de 240) | 13,2 ; 1 | -1,8 ; 0 | -1,8 ; 0 | -1,8 ; 0 | inchange |
| `f13.75-g8` | + ecart signal-texte 0,5 rem (8 px) | 9,2 ; 0 | -5,8 ; 0 | -5,8 ; 0 | -5,8 ; 0 | inchange |
| `f13.75-g0` | + ecart signal-texte nul | 1,2 ; 0 | -13,8 ; 0 | -13,8 ; 0 | -13,8 ; 0 | inchange |
| `f13.75-cg1` | + `column-gap` de la grille 1 rem (24 px avant) | 5,2 ; 0 | -9,8 ; 0 | -9,8 ; 0 | -9,8 ; 0 | meuble +8, cases +2,7 |
| `f13.75-cg1-g8` | les deux precedents, ecart 0,5 rem | 1,2 ; 0 | -13,8 ; 0 | -13,8 ; 0 | -13,8 ; 0 | meuble +8, cases +2,7 |
| `f13.75-cg1-g0` | `column-gap` 1 rem et ecart nul | -6,8 ; 0 | -21,8 ; 0 | -21,8 ; 0 | -21,8 ; 0 | meuble +8, cases +2,7 |
| `f13.25-pad8-g0` | signal 13,25 rem (212 px), rembourrage 0,5 rem, ecart nul | -6,8 ; 0 | -21,8 ; 0 | -21,8 ; 0 | -21,8 ; 0 | inchange |

- **Tient (critere de la fiche, mode par mode)** : `f13.75`, `f13.75-g8`, `f13.75-g0`, `f13.75-cg1` et `f13.75-cg1-g8` tiennent a 744, a 768 et a 720 sans barre, et **ne tiennent pas a 720 avec barre** (13,2, 9,2, 1,2, 5,2 et 1,2 px au-dela de la boite ; defilement horizontal de 1 px pour la premiere seulement). **`f13.75-cg1-g0` et `f13.25-pad8-g0` tiennent a tous les modes et largeurs**, marge minimale 6,8 px (720 avec barre).
- **Marge a 744 et 768 pour `f13.75`** : 1,8 px, a une police (celle du poste). Sa marge vient de 0,9 px sur la largeur du signal (220 pour 219,1 necessaires).
- **Effets lus, un seul par variante** : sur `*-cg1*`, la grille elargit le meuble de 8 px (489 a 720 avec barre, 504 ailleurs), les cases passent de 144 a 146,7 px (139 a 141,7 a 720 avec barre) et le bas de `.indice` apres une erreur a 744 passe de 548 a **550,7** (marge de 3 px sur 551 ramenee a 0,3). Sur `*-g0`, le signal rose touche le texte (capture `720-barre-f13.25-pad8-g0.png`, dans mon repertoire de travail, hors depot). Le bas du verdict apres une erreur a 744 reste a 532 (534,7 avec `*-cg1*`). A 992 et 1200, `f13.75`, `f13.75-cg1-g0` et `f13.25-pad8-g0` rendent M4 sur une ligne, bas inchange (542 et 494).
- **Etats juste, erreur, revelation** : signal a 220 px (212 pour la derniere) dans les quatre, le texte de l'indice, de l'explication et de la revelation part au meme x dans tous. Le verdict reste dans son signal : 12,9 px de reste (`f13.75`), 8,9 (`f13.25-pad8-g0`).

## 7. La phrase a 744 : deux lignes, jamais trois

M4 mesure 2 lignes a 744 dans le jar de reference et dans les sept variantes (`m4Lignes` : 2 sur les six items, aux quatre combinaisons de largeur et de mode de 744 et 768). La premiere ligne est la phrase 1 (262,2 px), la seconde la phrase 2, *"Appuie dessus."* (131,9 px). Chaque phrase etant insecable, **une troisieme ligne n'apparait pas** ; M4 tient sur une ligne des que la colonne depasse environ 400 px (262,2 + espace + 131,9), ce que rendent 992 et 1200 (colonnes de 484 et 664 px). Bas de M4 a 744 : 528 (530,7 avec `*-cg1*`), au plus 551 (`J2` de `theo-archi`).

## 8. Polices imposees (revelation, un item, 720 et 744 avec barre)

`pa8-polices.json`, signal en largeur naturelle a la revelation (la variante de la section 5, la seule mesuree sous les polices) et feuille servie. Largeur de la phrase 1 selon la police, et debordement au-dela de la boite (negatif = marge) a 744 :

| Police | Phrase 1 | Feuille servie | Signal naturel | Signal naturel, ecart nul, grille 1 rem |
|---|---|---|---|---|
| poste | 262,2 | 18,2 | -2,7 | -22,7 |
| Arial | 269,5 | 25,5 | 12,3 | -7,7 |
| Georgia | 257,1 | 13,1 | -9,9 | -29,9 |
| Times New Roman | 238,0 | -6,0 | -39,6 | -59,6 |
| Verdana | 306,5 | 62,5 | 66,3 | 46,3 |
| Courier New | 360,1 | 116,1 | 152,1 | 132,1 |
| Comic Sans MS | 282,0 | 38,0 | 27,5 | 7,5 |
| Trebuchet MS | 274,2 | 30,2 | 13,7 | -6,3 |
| Tahoma | 266,9 | 22,9 | 6,0 | -14,0 |
| Polices ou la phrase tient dans sa boite | | 1 sur 9 | 3 sur 9 | 6 sur 9 |

A 720 avec barre : la phrase tient dans 0 polices sur 9 avec la feuille servie, 1 sur 9 (Times New Roman) avec le signal naturel, 3 sur 9 (poste, Georgia, Times New Roman) avec le signal naturel, l'ecart nul et la grille a 1 rem. Les polices imposees ne remplacent pas un appareil reel, et cette mesure n'a pas ete faite avec le signal fixe.

## 9. Ce que je n'ai pas mesure

- Les voies (ii) (coupure dans la premiere phrase) et (iii) (phrase plus courte) : hors de `Q-R7` tant que (ii) n'est pas ouverte, et (iii) ne demande pas de mesure d'`ines-design`.
- Les variantes a signal fixe sous les neuf polices (section 8 : signal naturel seulement).
- Les etats juste, erreur, revelation a 992 et 1200 au-dela du bas de M4 ; le clavier, le focus, une tablette, un doigt.
- Un autre jeu a propositions en image : il n'existe pas (section 2).
- La retouche de `recreo.css` : elle est a `lea-dev` ou `marc-dev` apres le veto de `nadia-prof` ; les tests qui epinglent `.ecran-deux-oeuvres` (`CabinetDeLaMachineTest` pour les selecteurs du meuble, le garde de `C1'`) ne sont pas lances : je n'ai rien ecrit dans la feuille.

## 10. Isolation

Jar sur le port 18543, Chrome sur le 19243, repertoires de scratchpad de `ines-design` ; jar arrete par PID (lu dans son journal) ; aucun processus Chrome a mon nom ne reste (controle par ligne de commande). Aucun fichier d'un tiers deplace ou supprime.

## Decisions prises

**Aucune.** Ce fichier ne cree ni n'amende aucune `ADR` ni `DEC`, ne leve aucune reserve et ne prononce aucun Done.
