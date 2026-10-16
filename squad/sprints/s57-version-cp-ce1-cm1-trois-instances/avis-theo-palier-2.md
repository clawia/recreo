# [Sprint s57] Palier 2 - lecture du diff de B1 (`E3`), avis sur la forme commune (`PA5`), voies de `RDO-CP-R3` et de `RCT-CE1-R1`

- **Date** : 2026-10-16 08:26 (UTC+02:00), lu par `date`.
- **Auteur** : `theo-archi`. Branche `s57-theo-p2-avis`, worktree dedie, base `f6134910` (tip de `round-two`), `git status --short` vide avant ecriture.
- **Lu** : `planning.md` (amendement du 2026-10-15 20:56, sections 1 a 13) ; `composition-palier-2.md` ; `cloture-palier-1.md` ; mon `avis-theo-poste-8-art-cp-10.md` (2.2, 6.2, annexes A a E) ; `avis-faisabilite.md` 2.3 ; `squad/conformite/releves-s57/rapport-ines-r8-forme-commune.md`, `candidat-r3.css`, `candidat-r3-option-bandeau.css`, `r3-cotes-par-item.md`, `releve-b2-jar.json` ; `FICHE-bandit-manchot-rapprocher-deux-oeuvres-cp.md` (rejeu du 2026-10-15) ; le backlog (`S57-FORME-COMMUNE-PROPOSITIONS-LONGUES`, `S57-RDO-CP-R3`, amendement de `S57-RCT-CE1-R1`) ; `ADR-0022`, `ADR-0031`, `ADR-0054`, `ADR-0067` ; `fragments/manche.html`.
- **Perimetre d'`E3`** : decision de l'utilisatrice du 2026-10-16, relayee par l'agent lanceur et non lue de sa main : la trace couvre tout le palier 2 (`Q-S7`), et aucun developpeur ne part avant elle.
- **Ce que ce fichier ne fait pas** : il ne choisit aucune forme visuelle (`ines-design`), ne dit rien de ce que l'enfant lit ou voit (`nadia-prof`), n'engage aucun poste (`sofia-sm`), ne decide pas le crochet du harnais (`Q4`, utilisatrice).

## 1. `E3` : verdict sur le diff de B1

**Verdict : conforme.** Les quatre commits de B1 tiennent `C1'` (points 1 a 3), `C2`, `C3`, les textes figes des `ADR` et la decision 1 d'`ADR-0067` telle que l'amendement D.4 la precise. **Aucun ecart bloquant. `E3` est levee pour tout le palier 2.** Cette lecture est faite **apres** la fusion de B1, le 2026-10-16 : elle ne remplace pas la garde qui manquait avant la fusion, elle la constate tenue a posteriori.

### 1.1 Ce que j'ai lu

| Commit | Objet | Lu |
|---|---|---|
| `25e4a823` | feuille, `L1` a `L4`, jeu fautif de `CabinetDeLaMachineTest` | diff complet de `recreo.css` et du jeu fautif |
| `472dce51` | forme C, `LeVerdictNeSeCoupeJamaisDansUnePhraseTest`, amendement B.7 d'`ADR-0067` | diff complet du controleur et du test ; texte de l'`ADR` compare |
| `6a80fe66` | voie (b), test de `D7`, amendements A.1 a A.4 | diff du controleur et de `retour.html` ; texte des trois `ADR` et de l'index compare ; assertions du test de `D7` parcourues, pas rejouees |
| `7ab7bfd7` | phrase de `M4`, `LaPhraseDeRevelationDUneMancheEnImageNeSeCoupeJamaisDansUnePhraseTest`, amendement D.4 | diff complet du gabarit et du test ; texte de l'`ADR` compare |

SHA lus par `git log -1` sur chacun ; fusions `24075b12` et `a6f64e94`. **Le code lu est celui de `round-two`** : `git diff 05598be4 a6f64e94 -- adapters domain application bootstrap` ne nomme que les fichiers de ces quatre commits et ceux de `F1` (`9eb47c34`, `marc-dev`) ; `git diff a6f64e94 f6134910 -- adapters/adapter-web/src/main` ne rend aucune ligne.

### 1.2 Constats

| Code | Constat | Methode |
|---|---|---|
| `TH1` | **Textes figes verses a l'identique** : B.7 et sa ligne d'index dans `472dce51`, A.1 a A.4 dans `6a80fe66`, D.4 et sa ligne d'index dans `7ab7bfd7` | lignes ajoutees de chaque `ADR` (`git show <commit> -- <adr>`) comparees par `diff` au bloc de l'avis lu **dans le meme commit** (`git show <commit>:<avis>`) ; seul ecart, la ligne vide qui separe l'amendement du texte precedent |
| `TH2` | **`C1'` tenue sur `25e4a823`.** Point 1 : `L1`, `L2`, `L4` ont un sujet hors de `.message-jeu` et ne nomment ni `.message-jeu`, ni `.signal`, ni un etat. Point 2 : `L3` nomme `.message-jeu` et `.signal`, aucun etat. Point 3 : grille `". msg msg"`, la rangee `msg` ne partage sa zone avec aucune autre ; `ref` couvre les rangees 2 et 3 seulement | lecture du diff ; `.signal` n'existe que dans `.message-jeu` (`manche.html` ligne 193) |
| `TH3` | **`C2` et `C3` tenues.** Aucune regle neuve sous `@media` ne vise une image de proposition ; `L1` masque `.cabinet-bandeau` et `.cabinet-socle`, jamais `.cabinet`, et le jeu fautif recoit les deux selecteurs | lecture du diff |
| `TH4` | **Decision 1 d'`ADR-0067` tenue** : U+00A0 vit la ou le texte est ecrit (constantes de `PartieController` en `\u00A0`, gabarit en `&nbsp;`) ; aucune regle de style ni aucun `th:text` transforme ne l'ajoute ; aucun octet U+00A0 tape dans les lignes ajoutees des quatre commits | lecture ; `grep -c` de l'octet sur les lignes ajoutees de chaque commit : 0 |
| `TH5` | **Les deux garde-fous de coupure mordent, mesure par moi sur materiau reel** : `va&nbsp;avec` remis en `va avec` dans `manche.html` rougit `LaPhraseDeRevelation...Test` (2 tests lances, 1 echec) ; `"Bravo !"` remis dans `MESSAGE_BRAVO` rougit `LeVerdictNeSeCoupe...Test` (4 lances, 1 echec, message *"espace ordinaire devant '!'"*) ; fichiers restaures, `git status` vide | `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-web -am test -Dtest=<classe>`, perimetre `adapter-web` et ses amonts, JDK 21.0.10 (`java.version` des rapports Surefire d'`adapter-web`) |
| `TH6` | **Couplage latent, non une infraction** : `L2` donne au score une colonne `auto`, dont la largeur fixe celle de `.consigne` (`"consigne consigne score"`). Le texte du score change avec les reussites. Le meuble n'en depend pas (`jeu` couvre les colonnes 2 et 3). Mesure : largeur du score 79,7 px dans les 30 couples (largeur, etat) de `releve-b2-jar.json`, consigne sur une ligne partout ; l'entree sert `nombreDeManches: 5`, le score n'a jamais deux chiffres. Un jeu de dix manches sur cet ecran le ferait passer a deux chiffres ; aucun n'existe (liste exacte de `LesPropositionsEnImageAtteignentLEcranSansJamaisSonIdentifiantTest` : un seul jeu) | script de lecture du releve, hors depot |
| `TH7` | **Reference de ligne qui derive** : la phrase de `M4` est ligne **214** de `manche.html` depuis `7ab7bfd7` ; le message de ce commit et `cloture-palier-1.md` disent 212 (la ligne d'avant le changement), `planning.md` et `S57-RDO-CP-R3` disent 215. La methode de la story (`grep -n 'class="reponse-revelee"'`) rend les deux lignes 214 et 215 et reste juste | `grep -n` sur `f6134910` et `7ab7bfd7` |

**Ce que je n'ai pas fait** : rejouer les cinq morsures d'A.1 (test de `D7`) ; elles restent au rapport de `lea-dev` (`E1`). `TH5` porte deux morsures de B1, pas sept.

## 2. `PA5` (`Q-P7`) : `C1'`, `C2`, `C3` sur la forme commune

Objet lu : `candidat-r3.css` tel que verse (`R3-1` a `R3-5`), et son option `candidat-r3-option-bandeau.css` (section 3.2).

### 2.1 Decisions

| Code | Decision |
|---|---|
| `TP1` | **`C1'` tenue dans sa lettre** sur les cinq regles (2.2). **Elle ne dit pas que le meuble est immobile** : l'ascenseur de la revelation le deplace dans 45 items sur 72 a 744 (2.3). Ce mouvement est `N6`, donc `PA4`, a `nadia-prof` |
| `TP2` | **`C2` et `C3` tenues** |
| `TP3` | **Aucun amendement d'`ADR` pour la forme** : elle reste une feuille de style, sans marqueur du controleur ni gabarit par forme d'item (regle de B.6). Compte ArchUnit inchange |
| `TP4` | **Dette ouverte, nommee** : `C1'` n'a aucune garde executable sur l'ecran de manche ; une regle interdite par elle passe tout le module (2.4) |
| `TP5` | **`scrollbar-gutter` n'entre pas dans la forme.** S'il est demande, il amende `ADR-0054` dans le meme commit, dont la regle ne le voit pas (2.5) |
| `TP6` | **La fusion de p2-7 attend ma trace ecrite de lecture de son diff**, fichier dans ce dossier (2.6) |

### 2.2 Lecture regle par regle

- **Le sujet ne depend d'aucun etat.** Les cinq regles portent sur `.ecran-de-jeu:has(.propositions):not(.ecran-deux-oeuvres)` (ou sur `.zone-jeu` qui le contient). `form.propositions` est rendu sans `th:if` (`manche.html` ligne 155, et le commentaire de la ligne 142 l'impose au meuble) : il est present dans tous les etats de la manche. `:not(.ecran-deux-oeuvres)` n'est pas redondant : le meme formulaire porte `propositions propositions-oeuvres` sur l'ecran d'`ART-CP-10` ; c'est lui qui en exclut cet ecran (66 mesures sur 66 identiques, rapport, 5.1).
- **Point 1** : `R3-1` (`.zone-jeu`), `R3-2` (score, consigne, enonce), `R3-3` (`.propositions`) ne nomment ni `.message-jeu`, ni `.signal`, ni `.est-juste`, ni `.est-faux`, ni `data-oeuvre-revelee`, y compris dans leurs `:has(...)`. Tenu.
- **Point 2** : `R3-4` et `R3-5` ont pour sujet `.message-jeu` ou ses enfants, nomment `.signal`, aucun etat. `R3-5` (`.signal + *`) vise l'element qui suit le signal, qui change selon l'etat (l'indice apres une erreur, la phrase de revelation ensuite) : la regle est la meme pour chacun et reste dans le message. Tenu. L'egalite des abscisses du signal et du texte d'un etat a l'autre n'est pas mesuree (rapport, section 3).
- **Point 3** : l'ecran a propositions ecrites est un flux vertical, le message est apres le meuble, aucun element ne couvre la place du message et une place au-dessus. Tenu.
- **`C2`** : aucune regle ne vise une image ; le seul ecran a propositions en image est exclu. **`C3`** : aucune regle ne masque rien.
- **`R3-1` et les `ADR` de la coquille.** Son sujet est `.zone-jeu`, element de la coquille, conditionne par l'ecran insere. C'est la forme qu'`ADR-0022` autorise (le marqueur `ecran-de-jeu` pose par le serveur, lu par `:has`), et `ADR-0031` ne la refuse pas : la coquille et un seul ecran. `R3-1` ne touche que le rembourrage haut : `ADR-0054`, qui epingle les termes lateraux, n'est pas concerne.
- **Garde-fous, mesure par moi** : `recreo.css` de `f6134910` suivi du texte de `candidat-r3.css`, `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-web -am test` : `BUILD SUCCESS`, 120, 31 et **492 tests, 0 echec** (perimetre `adapter-web` et ses amonts, pas le depot) ; fichier restaure. Meme compte que le rapport d'`ines-design` (6.7). **Ce vert ne prouve pas `C1'`** : section 2.4.

### 2.3 Ce que `C1'` ne juge pas : l'ascenseur

`C1'` se lit sur les selecteurs ; la propriete qu'elle garde est un effet (le meuble identique apres une bonne et une mauvaise reponse, `C4` de `nadia-prof`). Sur la forme, l'effet est rompu sans qu'aucune regle nomme un etat : a la revelation, le document depasse 551, la barre de 15 px apparait, les cases se decalent de 7,5 px a 744 (45 items sur 72, contre 1 sans la forme) et se retrecissent de 15 px a 720 (41 sur 72 contre 1). Mesure d'`ines-design`, rapport section 6, point 1 ; je ne l'ai pas refaite. C'est le mecanisme de l'annexe E.3 de mon avis sur le poste 8, a une autre echelle : la cause est hors de la forme (la barre de la plateforme), la forme deplace le seuil. **Je ne qualifie pas ce mouvement** : `N6` et `PA4` sont a `nadia-prof`. Je dis seulement qu'un *"`C1'` tenue"* ne doit pas etre lu comme une reponse a `PA4`.

### 2.4 Dette : `C1'` n'a aucune garde executable sur l'ecran de manche

**Mesure, sur materiau reel** : `recreo.css` de `f6134910`, plus `candidat-r3.css`, plus une regle que `C1'` interdit (le meuble se compacte a l'erreur) :

```css
@media (min-width: 720px) {
    .ecran-de-jeu:has(.est-faux):not(.ecran-deux-oeuvres) .propositions { row-gap: 0; }
}
```

`bash outils/mvn-jdk21.sh -B -pl adapters/adapter-web -am test` : **`BUILD SUCCESS`, 492 tests, 0 echec** ; fichier restaure. Aucun test du depot ne voit qu'un etat du verdict gouverne le meuble du meme ecran. `UnEcranNeGouvernePasLApparenceDUnAutreTest` le dit dans sa Javadoc (*"Le couplage du verdict a un element du meme ecran n'est pas jugeable ici"*) ; `J5` ne juge que l'ecran d'`ART-CP-10`, sur un releve.

**Ce qui la rendrait executable**, sans l'ecrire ici : `ADR-0031` tient la distinction pour impossible (*"rien ne distingue structurellement"* `.signal.est-juste .icone-signal` d'une regle qui colorerait l'explication). `C1'` point 1 en donne une : le **sujet** de la regle est-il dans le sous-arbre du message ? Une regle d'`adapter-web`, derivee du gabarit (les jetons du sous-arbre que `th:if="${message != null}"` pose) et des regles maison, interdirait la regle ci-dessus et laisserait passer `.signal.est-juste .icone-signal`. `data-oeuvre-revelee`, pose dans le meuble et non dans le message, lui echapperait et resterait a la lecture du diff ; `O1` (ligne 1817) n'en serait pas touchee, son sujet ne nomme aucun jeton du message. Ce serait un amendement d'`ADR-0031` avec sa regle, dans le meme commit.

**Pourquoi je ne l'ecris pas maintenant** : la forme ne viole pas `C1'` ; la dette existe depuis `C1'` (2026-10-14) et la forme ne la cree pas. Mais la seule garde actuelle est ma lecture du diff, et `E3` montre qu'elle peut manquer au moment de la fusion. Je propose de l'ecrire **avant la fusion de p2-7**, sans bloquer l'ouverture de son worktree : c'est une affectation de capacite, `Q-T14`.

### 2.5 `scrollbar-gutter`, propose par `ines-design`

`html { scrollbar-gutter: stable; }` reserve la place de la barre en permanence sur une plateforme a barre classique, et ne change rien sur une plateforme a barre superposee. Ce qu'il ferait : `clientWidth` ne varierait plus d'un etat a l'autre, le decalage de 7,5 px et le retrecissement de 720 disparaitraient, et l'exclusion *"hors verdict"* de `J5` deviendrait inutile. Ce qu'il couterait : 15 px de largeur en moins, dans tous les etats, sur tous les ecrans d'une plateforme a barre classique ; a 720, la zone deviendrait fluide en permanence (le cas *"720 avec barre"* de la fiche d'`ART-CP-10` deviendrait la regle, `RDO-CP-R3` y deborde de 33,2 px) ; toutes les cotes de reference de bureau changent, `PA2` compris.

**Mesure** : `recreo.css` de `f6134910` plus cette seule ligne, `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-web -am test` : **492 tests, 0 echec**, dont `LaLargeurOffreeAUneRangeeEstUneFonctionEpingleeTest` (11 tests, 0 echec) ; fichier restaure. Or `ADR-0054`, point 3, epingle *"tout terme lateral declare sur un element de la chaine"* en enumerant `box-sizing`, `width`, `min-width`, `max-width`, rembourrage, marge, bordure. `scrollbar-gutter` change la largeur offerte a la rangee et n'est pas dans la liste : la regle se tait. **Condition** : si `scrollbar-gutter` est demande (`PA4` refuse par `nadia-prof`, puis proposition d'`ines-design`), le meme commit amende `ADR-0054` et etend sa regle, morsure comprise. Je n'ecris pas cet amendement avant : il servirait un usage que personne n'a choisi.

### 2.6 Garde de la fusion de p2-7

Points 1 a 3 de `C1'`, `C2`, `C3` et `C1'` sur le diff livre, par moi, **avant la fusion**, dans un fichier de ce dossier. Pour que le defaut d'`E3` ne se repete pas, je demande a `sofia-sm` de verifier l'existence de ce fichier avant de fusionner (`Q-T15`). Le developpeur nomme dans son rapport : `CabinetDeLaMachineTest`, `UnEcranNeGouvernePasLApparenceDUnAutreTest`, `LaLargeurOffreeAUneRangeeEstUneFonctionEpingleeTest`, `LePlafondDeRenduDUneReproductionEnPropositionEstGardeTest`, et le compte ArchUnit.

## 3. `PA8` et `PA7` : avis technique

### 3.1 `RDO-CP-R3` (`Q-R7`) : les trois voies

Cotes de la fiche (rejeu du 2026-10-15, sections 2 et 6) : la premiere phrase de `M4` mesure 262 px, sa colonne 244 px de 744 a 768 et 229 px a 720 avec barre ; colonne = meuble (496, 481 a 720 avec barre) moins le signal (15 rem, 240 px) moins l'ecart (12 px). Le debordement de la boite (18,2 px) existe **dans les deux modes** de 720 a 768 : `scrollbar-gutter` ne le leve pas.

| Voie | Ce qu'elle touche en architecture | Ce qui en decide |
|---|---|---|
| (i) largeur de la colonne ou du signal | une regle de feuille sous `.ecran-deux-oeuvres`, sans `ADR`, sous `C1'`, `C2`, `C3`. **Elle ne change la largeur d'aucun autre ecran** : `.ecran-deux-oeuvres` n'est pose que si les propositions sont des images (`manche.html` ligne 34), et un seul jeu du catalogue en sert (liste exacte de `LesPropositionsEnImageAtteignentLEcranSansJamaisSonIdentifiantTest`). Si la forme commune est fusionnee, sa colonne de 15 rem reste une declaration distincte : changer l'une ne doit pas changer l'autre | **une mesure d'`ines-design` qui n'existe pas.** Arithmetique sur les cotes de la fiche : pour 262 px a 720 avec barre, le signal doit tomber a 207 px au plus (481 - 12 - 262). Le signal porte le verdict insecable a deux phrases ; sur l'ecran a propositions ecrites, `ines-design` lui mesure 33 px de reste dans 15 rem sous la police du poste (rapport R8, 6.2), soit 207 px. La marge serait nulle, sur une police. Je n'en conclus rien sans sa mesure sur cet ecran |
| (ii) coupure dans la premiere phrase | une U+00A0 du gabarit redevient une espace ordinaire, a une place nommee ; **amendement de D.4 d'`ADR-0067` et de son garde-fou dans le meme commit** (la regle exige aujourd'hui cinq U+00A0 dans la premiere phrase et une espace ordinaire seulement apres une fin de phrase). Je l'ecris quand la place est choisie, pas avant | le choix de la place (`nadia-prof`) ; puis une mesure : la phrase doit tenir sur **deux** lignes a 744. Sinon elle en prend trois, la boite de `M4` grandit d'une ligne (30 px : 60 px pour deux lignes, fiche section 2), et son bas passe de 528 a environ 558, au-dessus de la barre du refus (`J2`, bas de `M4` au plus 551 a 744). `J2` le verrait sur un releve. La seconde ligne (fin de la premiere phrase, espace, *"Appuie dessus."*) doit tenir dans 229 px |
| (iii) phrase plus courte | le noeud de texte du gabarit, l'inventaire de `TexteAccentueDesGabaritsTest` (`ADR-0039`) et la constante de la phrase dans le garde-fou de D.4. **Aucun amendement d'`ADR`** : D.4 ne cite pas les mots de la phrase, il dit sa forme. Critere 6 de la story tenu (la constante n'est pas une ligne `assert`) | le texte, a `nadia-prof` |

**Mon avis, sur l'architecture seule** : (iii) a le moins de pieces mobiles, puis (ii) ; (i) est la moins chere en code mais sa marge est nulle par arithmetique et depend d'une mesure absente. Le critere 3 de la story (*"la coupure reste entre les deux phrases, sauf si la forme ne peut pas tenir les deux"*) met (i) avant (ii) : **c'est la mesure de (i) par `ines-design` qui ouvre ou ferme (ii)**, pas moi. Le choix entre (i), (ii) et (iii) n'est pas le mien.

### 3.2 `RCT-CE1-R1` (`PA7`, `Q-P5`) : propositions plus courtes ou bandeau

Faits lus dans `r3-cotes-par-item.md` et le rapport R8 (7, et tableau des polices de 4) : les trois items a 552 sont des items **a trois rangees** (13 et 17 au pire ordre, 18 dans les six) ; a deux rangees, les memes items finissent a **478** avec la forme. Sous la forme, l'item 18 passe 551 dans huit polices sur neuf (jusqu'a 582).

| Voie | Architecture | Marge, par arithmetique sur ces cotes |
|---|---|---|
| bandeau a 1,75 rem | une regle de feuille sous le sujet de la forme ; unite en `rem`, permise ; elle nomme `.cabinet-bandeau`, donc le jeu fautif de `CabinetDeLaMachineTest` est complete dans le meme commit (`chaqueSelecteurEpingleEstEprouveParLeJeuFautif`). Pas d'`ADR`. Elle ne masque rien (`C3` sans objet) et ne nomme aucun etat (`C1'` tenue). Elle touche l'habillage des 144 jeux a propositions ecrites, celui-ci compris. Une hauteur reduite au-dessus du contenu ne peut que le remonter : aucun etat ne descend, par construction | 4 px : 548, marge de 3 px sur la police du poste ; sur les autres polices, l'item 18 reste au-dessus de 551 (582 - 4 = 578) |
| propositions plus courtes | aucun code, aucune feuille, aucun `ADR` : le paquet `ce1-texte-narratif`, ses tests de contenu (dont `LON-CE1-3` et `-4`), un rejeu. Ne touche que ce jeu | si elles tiennent sur deux rangees **dans les six ordres** : 478, marge de 73 px sur la police du poste. **Non mesure** : le repli depend de la largeur des textes et de l'ordre servi (13 et 17 valent deux ou trois rangees selon l'ordre) ; c'est une mesure d'`ines-design` sur les textes de `nadia-prof` |

**Mon avis, sur l'architecture seule** : les deux sont faisables sans `ADR`. La voie des textes retire une rangee la ou le bandeau retire 4 px : c'est la seule des deux qui a une chance de tenir hors de la police du poste. Le bandeau est un changement d'habillage sur 144 jeux pour trois items d'un seul, et l'habillage est garde par `nadia-prof` (`L1`). Aucune des deux ne garantit un contenu futur (B.6, point 5). Le choix est a `nadia-prof` pour le bandeau et a elle et au developpeur pour les textes.

## 4. `Q4` : crochet du harnais sur les redirections du shell (avis technique, sans decision)

La decision est a l'utilisatrice ; je ne touche ni la configuration du harnais ni ses crochets.

- **Un crochet qui lit la commande** (avant l'execution, sur le texte de la commande du shell : `>`, `>>`, `2>`, `tee`) est un perimetre enumere par formes d'ecriture. Il echoue par omission : un script Python ou Node qui ecrit lui-meme, une variable qui porte le chemin, `cd` puis un chemin relatif, un journal de Maven, un profil de Chrome. Je ne le recommande pas comme garde.
- **Un controle de l'effet** (liste de la racine du scratchpad et du repertoire parent des worktrees avant et apres chaque commande, ecart signale) voit toute ecriture quel que soit l'outil. Sa limite : plusieurs agents en parallele ecrivent dans la meme fenetre de temps, l'attribution a une commande n'est donc pas sure ; la detection, elle, l'est.
- **Sans crochet**, la mesure de `sofia-sm` a la cloture (section 6 de l'amendement du 2026-10-15) est le meme controle de l'effet, une fois par palier : elle detecte, elle n'empeche pas, et elle n'attribue pas.

## 5. Questions

| Code | Question | A qui |
|---|---|---|
| `Q-T14` | J'ecris la regle de `C1'` point 1 (amendement d'`ADR-0031`, test d'`adapter-web`, morsure de la section 2.4) **avant la fusion de p2-7** : l'engages-tu, et dans quel worktree ? Elle ne bloque pas l'ouverture de p2-7 | `sofia-sm` |
| `Q-T15` | Avant la fusion de p2-7 et de p2-12, verifies-tu l'existence de ma trace de lecture du diff dans ce dossier, comme une condition de fusion ? | `sofia-sm` |
| `Q-R7` | Mesure de la voie (i) de `RDO-CP-R3` (signal ou colonne a 720 avec barre, et reste du verdict dans le signal sur cet ecran) ; puis, si (ii) est ouverte, tenue de `M4` sur deux lignes a 744 | `ines-design` |
| `Q-P5` | Pour `RCT-CE1-R1` : les textes plus courts tiennent-ils sur deux rangees dans les six ordres ? | `ines-design`, sur les textes de `nadia-prof` |

`Q-T12` (perimetre `th:text` de `S57-TYP1-TEXTES-DE-GABARIT`) et `P9` de la cloture du palier 1 (sept lignes ou six hors verdict) ne sont pas traites ici : ils ne sont pas dans cette tache.

## 6. Regle ArchUnit

**Aucune.** Compte sur `f6134910`, methode de 3.4 de mon avis sur le poste 8 (fichiers `*.java` suivis par `git ls-files`, motifs `^\s*(public |private |protected )?static final ArchRule ` et `^\s*@ArchTest\s*$`) : **41** et **38**, dans 16 fichiers. Identique a `e7497db7` (`cloture-palier-1.md`, section 3).

## 7. Jobs de `ci.yml` lances pour ce commit

Lances sur ce worktree, ce fichier indexe, avant le commit. Je ne deduis aucun job que je n'ai pas lance.

- **`build`** : `bash outils/mvn-jdk21.sh -B clean verify` depuis la racine, sans `-pl` : `BUILD SUCCESS`, exit 0. **2597 tests, 0 echec, 0 erreur, 0 ignore** (120, 31, 492, 1567, 381 et 6 d'integration, lignes `Tests run` de la sortie Maven). JDK **21.0.10** (`java.version` des 60 rapports Surefire de `bootstrap`, une seule valeur). Des tests de `bootstrap` lisent `squad/` : **ils lisent ce fichier**, verifie par une morsure (une cellule de trop sur la ligne de `TP6` rend `AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest` rouge, 2 echecs sur 5, en nommant ce fichier ; fichier restaure). Le build a lu ce fichier avant l'ajout de cette section, qui ne contient aucun tableau. Etape *"Package du fat jar"* non lancee.
- **`chemins`** : `verifier-chemins-test.sh` sort 0 ; `git ls-files` (moins le test du garde-fou) passe a `verifier-chemins.sh` : sort 0.
- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh` et `verifier-garde-fou-accent-test.sh` sortent 0 (21 cas pour le dernier) ; le sujet de ce commit passe dans `verifier-prefixes.sh` : sort 0. Le controle du proprietaire du garde-fou de l'accent n'est pas lance : aucun de ses chemins n'est touche.
- **`outils`** : les trois balayages (5, 2 et 5 fichiers), 12 juges lances avec `python`, 12 sortis 0.

Les mesures partielles des sections 1 et 2 (`-pl adapters/adapter-web -am`) nomment leur perimetre la ou elles sont citees. Aucun de ces resultats n'est celui de la CI distante, que je n'ai pas lue.

## Decisions prises

**Aucune `ADR` ni `DEC` n'est creee ni amendee par ce fichier.** Il leve `E3` pour le palier 2, ouvre une dette nommee (`TP4`), pose deux conditions (`TP5`, `TP6`).
