# s57 - relecture des corrections de `ce1-qlm-besoins-de-la-plante` (`S57-BR-QLM-CE1-03`, `P1`)

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : debut 2026-10-26 15:49:49 (UTC+01:00), lu par `date` ; fin en section 5.
- **Arbre** : branche `s57-p5-nadia-corr03`, base `round-two` a `b1fa3e1f`, lue par `git`.
- **Lu** : `git diff 7fe939a6 HEAD` sur `adapters/adapter-content/src/main/resources/contenu/ce1-qlm-besoins-de-la-plante.yaml`
  (correction de `marc-dev`, commit `37f49795`), compare a la section 5 de
  `lecture-nadia-premier-jet-ce1-qlm-besoins-de-la-plante.md`. Pour l'unicite, les quatorze lignes `indice:` du paquet, et rien
  d'autre de ses items. **Je n'ai pas relu le paquet.**
- **Type** : relecture des lignes corrigees, avant `verify`. **Aucun Done, aucun verdict de lot**, aucun identifiant neuf, aucune
  condition neuve. Je n'ai modifie ni le paquet, ni les tests.
- **Audit degrade, dit** : rien n'est joue a l'ecran (paquet hors catalogue). Chaque indice neuf est joue sur le papier.
- **Mesures lancees** :
  - comparaison caractere par caractere par script (Python, hors du depot, non commite) : chaque *texte a ecrire* de la section 5
    cherche tel quel dans le YAML, chaque *texte actuel* cherche absent, les deux blocs de commentaire cherches a l'identique ;
    compte de mots par la regle de `PaquetLu.mots` (decoupe sur les blancs, dont l'espace insecable) ;
  - `bash outils/mvn-jdk21.sh -B -q -pl adapters/adapter-content -am test -Dtest='PaquetCe1QlmBesoinsDeLaPlanteTest,ChaquePaquetDeQuestionnerLeMondeDeCe1TientLesConditionsTransversesTest'`,
    JDK 21.0.10 (propriete `java.version` du rapport) : **27 tests puis 18 tests, 0 echec, 0 erreur**. Perimetre : ces deux tests,
    module `adapter-content` et ses dependances. Ce n'est ni un `verify`, ni la porte technique.

## Resume

| Point | Reponse |
|---|---|
| Conformite a la section 5 | **les dix points tiennent, a l'octet** (section 1) |
| Perimetre du diff | **seules les lignes prescrites changent** : 6 indices, 2 distracteurs, 2 commentaires (section 2) |
| `TRV-CE1-5`, `TRV-CE1-6`, mots interdits, distracteurs | **tiennent** (section 3) |
| Ouverture | **aucune** : pas d'item ni de formulation rouverts, pas de deuxieme tour a classer (section 4) |
| Duree | section 5 |

---

## 1. Comparaison a la section 5, element par element

| Point | Item | Texte lu dans le YAML | Verdict | Motif |
|---|---|---|---|---|
| 1 | 3 | `Compare une plante en hiver et la même plante au printemps.` | **tient** | Identique a la prescription ; ancien texte absent |
| 2 | 4 | `Imagine cette plante dans un placard fermé, puis près d'une fenêtre.` | **tient** | Identique ; apostrophe droite (`0x27`) |
| 3 | 5 | `Écarte ce qu'on ne donne jamais à une graine semée.` | **tient** | Identique ; `É` majuscule accentuee, apostrophe droite |
| 4 | 8 | `Imagine la même plante un jour d'été.` | **tient** | Identique ; apostrophe droite |
| 5 | 10 | `Compare une plante au soleil et une plante à l'ombre.` | **tient** | Identique ; apostrophe droite |
| 6 | 13 | `Imagine une plante de la classe oubliée pendant les vacances.` | **tient** | Identique |
| 7a | 9 | `"elle germe plus vite"` | **tient** | Identique ; *elle donne des fruits* absent |
| 7b | 14 | `"elle germe tout de suite"` | **tient** | Identique ; *elle donne des fleurs* absent |
| 8 | commentaire `:28` a `:35` (ancien `:28` a `:34`) | paragraphe *Source (QMT-CE1-2)* | **tient** | Bloc identique, ligne pour ligne, a celui de la section 5 |
| 9 | commentaire `:44` a `:46` (ancien `:43`) | famille b | **tient** | Bloc identique |
| 10 | test du paquet | non relu (hors des lignes demandees) | **tient, par sa mesure seule** | `PaquetCe1QlmBesoinsDeLaPlanteTest` passe sur le paquet corrige, donc sa table admet les deux distracteurs neufs ; 14 items, totaux de rang inchanges (`QC10`) non relus |

**Espace insecable avant `?`** : aucun texte neuf ne porte de `?` ni de `:`, donc aucun espace a verifier. Les enonces voisins, non
touches, gardent leur espace insecable (`U+00A0`, visible dans le contexte du diff). **Precision sur ma propre phrase** : *"aucun
texte neuf ne porte d'espace avant `?` ou `:`"* (section 5 de ma lecture) visait le texte lu par l'enfant ; les deux blocs de
commentaire que j'ai ecrits portent `" : "` selon la convention du depot, et `marc-dev` les a recopies tels quels, ce qui est juste.

## 2. Perimetre du diff

`git diff --stat 7fe939a6 HEAD` sur le YAML : **1 fichier, 19 insertions, 16 suppressions**. Le detail :

- commentaire *Source* : 7 lignes retirees, 8 ajoutees ;
- commentaire *famille b* : 1 ligne retiree, 3 ajoutees ;
- six lignes `indice:` (items 3, 4, 5, 8, 10, 13), une ligne retiree et une ajoutee chacune ;
- deux lignes `distracteurs:` (items 9 et 14), une et une.

Total : 16 retirees, 19 ajoutees, compte du `--stat`. **Aucune autre ligne ne change** : ni enonce, ni bonne reponse, ni
declaration d'item, ni ordre des distracteurs dans les items 9 et 14 (le distracteur neuf prend la place de l'ancien).

## 3. Ce que les corrections doivent tenir

| Exigence | Verdict | Motif |
|---|---|---|
| `TRV-CE1-5`, aucun indice ne donne la reponse | **tient** | Joue sur le papier, aucun des six ne nomme ni ne designe l'objet de la reponse. Items 3, 10 : comparer deux situations, l'enfant cherche seul ce qui differe. Items 4, 8, 13 : imaginer une privation ou son contraire, l'enfant trouve seul ce qui manque. Item 5 : eliminer *du bruit*, *du lait*. Plus aucun *"Pense à ce que ..."* dont l'objet est la reponse |
| Une seule reponse que l'indice appuie | **tient** | Items 3 et 8 (saisons, ete) et 10 (ombre) pourraient aussi evoquer la lumiere, ou la chaleur pour l'ombre : aucune n'est proposee a cote, les deux distracteurs sont de famille a. L'indice ne rend aucun distracteur defendable |
| Six indices distincts | **tient** | 14 indices, 14 textes differents, par script. Les items 3 et 8 portent tous deux sur la saison mais leurs textes et leur geste different (comparer, imaginer) |
| Longueur | **tient** | 7 a 11 mots par la regle de `PaquetLu.mots`, sous le plafond de douze de `TRV-CE1-5` ; le test du paquet passe |
| Mots interdits (`BPL-CE1-1`) | **tient** | *air*, *terre*, *engrais*, *sels*, *mineraux* : 0 occurrence sous `items:`, par script, et garde `I1` verte |
| `BPL-CE1-2` | **tient** | L'indice de graine (item 5) ne nomme ni lumiere ni soleil, ni objet sombre ; les deux distracteurs neufs, de graine, n'en parlent pas |
| `TRV-CE1-6` | **tient** | *le jardinier* : 0 occurrence. L'item 13 dit *la classe*, un cadre que tous les enfants ont ; aucun prenom, aucun foyer, aucun personnage |
| Item 9, *elle germe plus vite* | **tient** | Contraire de *elle ne germe pas*, famille b telle que corrigee. Indefendable sans eau : une graine privee d'eau ne germe pas du tout. Plausible pour un enfant qui ne sait pas que l'eau declenche la germination |
| Item 14, *elle germe tout de suite* | **tient** | Contraire de *elle reste une graine*. Indefendable au grand froid : la graine attend. Ne se confond pas avec *elle pousse plus vite* que l'item porte deja : l'un dit le moment, l'autre la vitesse |
| `I5`, bonne reponse strictement la plus longue au plus 7 fois sur 14 | **tient** | Item 9 : 17 caracteres contre 23 et 20 ; item 14 : 21 contre 24 et 21. Aucun des deux n'entre dans le compte. Le test transverse de CE1, qui porte cette comparaison stricte, passe |
| Regle de `I2` sur la lumiere et l'etiolement | **tient** | Aucun des deux distracteurs neufs n'est dans un item de lumiere |

**Ce dont je reste la seule garde, inchange depuis ma lecture** (section 5 de celle-ci) : qu'un indice soit une methode et pas une
periphrase ; la plausibilite de chaque distracteur ; l'exactitude des consequences. Les tests passent, ils ne lisent pas cela.

## 4. Ouvertures

**Aucune.** Je ne rouvre aucun item ni aucune formulation, je n'ecris aucune reserve neuve : **pas de deuxieme tour d'arbitrage a
classer** sur ce lot. Ce que j'avais annonce regarder en jouant (`du bruit`, la reconnaissance de la liste de la tache besoin) reste
pour l'audit a l'ecran (`P2`), comme dans ma lecture.

## 5. Duree et faits pour le classement

Mesuree par `date` : debut **2026-10-26 15:49:49**, ecriture 15:53:12, fin **15:56:59**, avant le commit. **Duree : 7 min 10 s**,
dont les deux mesures de test et celle du garde-fou des identifiants
(`bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` : *"Tests run: 10,
Failures: 0, Errors: 0, Skipped: 0"*, `BUILD SUCCESS`, ce fichier present dans l'arbre ; il n'ecrit aucun identifiant neuf).

Faits pour `camille-po`, qui classe :

- **La lecture porte sur les lignes, pas sur le paquet** : le diff (35 lignes changees) et les quatorze lignes `indice:`, rien
  d'autre. Aucun enonce, aucune bonne reponse non modifies n'ont ete relus ; aucun item n'a ete rejoue en entier.
- **Elle est la verification de ma propre prescription** : texte exact ecrit par moi, applique sans ecart.
- Au bareme, ma lecture du premier jet (section 7) decrivait cette relecture comme *"une seconde lecture courte, a 0,5 hors marge
  (`DEC-0120` A.270, point 4)"*. Je donne le fait, pas le classement : c'est une lecture distincte, faite apres la correction, sur
  les seules lignes corrigees. La decision entre 0,5 hors marge et la correction comprise dans le 1,0 est a `camille-po`.

**Signe** : `nadia-prof`, s57, palier 5, 2026-10-26.
