# s57 - palier 5 : avis de theo-archi sur les gardes de `-05` et `-08` apres la lecture de nadia-prof des premiers jets

- **Auteur** : `theo-archi`. Date : 2026-10-27 ; debut non lu par `date`, je ne l'ecris pas ; fin en section 6.
- **Arbre** : worktree `s57-p5-theo-apres-lecture0508`, base `round-two` a `a373b7d3` (`git rev-parse`). A cette base, `ce1-qlm-eau-trois-etats.yaml` et `ce1-qlm-dangers-electricite.yaml` sont les premiers jets lus par `nadia-prof` (`git diff e6694d47 HEAD` et `git diff c8609344 HEAD` sur ces deux fichiers : vides).
- **Repond a** : `lecture-nadia-premier-jet-ce1-qlm-eau-trois-etats.md` section 5 (dite *"sa lecture de -05"*, reperes notes `05-T1` a `05-T4`) et `lecture-nadia-premier-jet-ce1-qlm-dangers-electricite.md` sections 4 a 6 (dite *"sa lecture de -08"*, reperes `08-T1` a `08-T4`).
- **Lu** : ses deux lectures en entier ; mes avis `avis-theo-palier-5-ajustements-gardes-ce1-qlm-04-05.md` (*"avis 0405"*), `avis-theo-palier-5-ajustements-gardes-ce1-qlm-06-08.md` (*"avis 0608"*), `avis-theo-palier-5-dernier-ajustement-gardes-ce1-qlm-05-a-12.md` (*"dernier ajustement"*) ; dans mes avis du 2026-10-26, les passages que celui-ci touche (section 4) ; `PaquetCe1QlmEauTroisEtatsTest.java`, `PaquetCe1QlmDangersElectriciteTest.java`, `PaquetLu.java`, `eau-fautive.yaml`, `dangers-fautifs.yaml` (sondes 14, 54 a 77) ; la regle 3 de `-08` corrigee (`arbitrage-nadia-contradictions-ce1-qlm-06-08.md`, 4) et 6.4 de `arbitrage-nadia-dernier-lot-ce1-qlm-04-a-12.md`.
- **Ce que ce fichier ne fait pas** : il ne reecrit aucun de mes avis (section 4 : marqueurs) ; il n'ecrit ni test, ni jeu fautif, ni contenu ; il ne cree aucun identifiant de condition (les messages citent `TRV-CE1-2`, `TRV-CE1-5`, `NRC-CE1-1`, deja alloues) ; il ne tranche rien de pedagogique et ne prononce aucun Done. Il ne touche ni aux fichiers de l'avis de `-06`, `-10`, `-12`, ni aux stories.
- **Aucune regle ArchUnit, aucun `ADR`** : ce sont des gardes de contenu sur du YAML, dans la forme d'`ADR-0013` (jeu fautif commite), comme mes avis precedents ; aucune ne porte sur une dependance entre classes.
- **Notations** : `plat`, `egalite`, `tache`, `BOUILLIR`, `MOT_FONTE` sont ceux des deux tests tels que commites a `a373b7d3`. *Premier jet* : le paquet a cette base. *Corrige* : le paquet portant les corrections de la section 4 de chacune de ses lectures, applique sur une copie hors depot (section 3).

## 0. Verdict, une ligne par ajustement

| Repere | Forme retenue | Sonde refusee | Doit passer |
|---|---|---|---|
| `05-T1` | aucune forme neuve : le fragment de `s12` (`PaquetCe1QlmEauTroisEtatsTest.java:747`) devient *"regle 4 : rien ne change est distracteur de 3"* | - | `s12` sur le paquet corrige (mesure : message *"... distracteur de 3 item(s) ..."*) |
| `05-T2` | **encodee**, garde neuve : dans un item de `tache` vapeur dont `plat(enonce)` porte `BOUILLIR`, refus d'un distracteur qui porte `nuages?\|fumees?` | item 8 du premier jet (*elle devient un nuage*) | item 8 corrige ; item 10 reel (*de la fumée*, sans ebullition) |
| `05-T3` | **encodee**, garde neuve : dans un item de `tache` temperature dont la bonne reponse porte `MOT_FONTE`, refus d'un distracteur qui porte `dispar(?:ai\|u\|ition)\p{L}*` | item 12 du premier jet (*les glaçons disparaissent*) | item 11 reel (*le glaçon disparaît*, bonne reponse *rien ne change*) ; item 12 corrige |
| `05-T4` | aucune garde : **non garde : `TRV-CE1-2`** (le brouillard que la meteo ecarte), **non garde : `QMT-CE1-2`** (le sol de la flaque) | - | - |
| `08-T1` | `G3` revient a l'ancre de 1.3 du dernier ajustement : `^(?:s'eloigner\|attendre\|ne rien toucher\|ne pas s'approcher\|continuer a jouer ailleurs)(?!\p{L})` | sonde 71, *"attendre sans rien dire"* | sonde 70, *"attendrir le fil"* ; sondes 68, 69 |
| `08-T2` | **encodee, et remplace `g9`** : refus d'une proposition dont `plat` porte `adultes?` et ne commence pas par `ne` | *"aller voir un adulte"*, *"avertir un adulte sans attendre"*, *"attendre un adulte"* | *"ne rien dire à un adulte"*, *"ne pas déranger un adulte"* |
| `08-T3` | **encodee**, garde neuve sur l'indice : `geste` sans `touch\p{L}*\|approch\p{L}*\|branch\p{L}*\|sech\p{L}*(?!-cheveux)\|prev(?:en\|ien)\p{L}*\|adultes?` ; `danger` sans `abim\p{L}*\|cass(?:e\|es\|ee\|ees\|er\|ent)\|eaux?` | B *"Ne touche à rien."*, *"Pense à un adulte."* ; A *"Regarde ce qui est près de l'eau."* | les douze indices du paquet corrige |
| `08-T4` | **encodee**, garde neuve : item `danger`, refus si un mot est dans les deux distracteurs et pas dans la bonne reponse | Maya et Adam du premier jet (*loin*, *debranche*) | Maya et Adam corriges, Jade, Mehdi |
| `g2`, `G1` | **inchangees** : aucune correction ne touche une bonne reponse ; Hugo reste le seul item `APPROCHER` | - | le paquet corrige, mesure |

**Corrections de `nadia-prof` qui ne passent pas une garde : aucune.** Ses 13 textes de `-08` passent toutes les gardes en place et les quatre formes ajustees ; ses 5 textes de `-05` aussi, la seule assertion qui tombe etant `s12`, que `05-T1` prevoit (section 3).

## 1. `-05` (`ce1-qlm-eau-trois-etats`)

### 1.1 `05-T1` : le fragment de `s12`

Confirme par execution, pas seulement par lecture : sur la copie corrigee, `s12LePaquetReelPriveDeSesItemsARienNeChangeEstRefuse` est **la seule assertion en echec** des 2 542 tests du module (section 3), et son message est *"regle 4 : rien ne change est distracteur de 3 item(s) et la bonne réponse d'aucun"*. Le fragment devient *"regle 4 : rien ne change est distracteur de 3"*, dans le commit du point 5 de sa lecture. Aucune forme neuve, aucune autre ligne de test ne depend des cinq textes corriges (`s7`, `s8`, `s21` lisent les enonces des items 6 et 8, inchanges).

### 1.2 `05-T2` : ce que l'eau qui bout produit

**Encodable, et encode.** Sa regle 3 nomme le cas (*"la fumée blanche pour une casserole qui bout"*) et sa lecture le nomme sous l'autre mot de l'enfant (*un nuage*). Les deux reperes existent deja dans le test : `tache` et `BOUILLIR`. La liste `nuages?|fumees?` est fermee par ses deux phrases ; *buée* n'y est pas, elle la garde a sa lecture a l'ecran (2.2 de sa lecture), *brouillard* non plus, c'est son remplacement.

**Forme**, methode `defauts...` a part, un message par distracteur : si `tache(item)` vaut vapeur et que `BOUILLIR` est trouve dans `plat(enonce)`, chaque distracteur ou `(?<!\p{L})(?:nuages?|fumees?)(?!\p{L})` est trouve sur `plat` leve *"regle 3 : '%s' est ce que l'eau qui bout produit, distracteur de '%s'"*.

| Cas | Attendu | Obtenu |
|---|---|---|
| item 8 du premier jet, recopie a l'identique dans le jeu fautif, juge seul | refus | refus (*elle devient un nuage*) |
| meme enonce, *elle devient de la fumée* (de moi) | refus | refus |
| item 8 corrige, *elle devient du brouillard* | passe | passe |
| item 10 reel, brouillard sur la route, *de la fumée* | passe | passe ; **tue le mutant sans la clause `BOUILLIR`**, sur le materiau reel |
| les onze autres items, premier jet et corrige | passe | passe |

**Non garde : `TRV-CE1-2`** : ce qu'une autre situation produit (la buee d'une cuisine, d'une douche) ; ce que l'enfant nomme autrement (*la vapeur blanche*, *la brume*).

### 1.3 `05-T3` : la disparition vraie de la glace qui fond

**Encodable, et encode.** Sa lecture (2.3) separe deux disparitions : celle de l'eau, conception a deplacer, et celle des glacons qui fondent, vraie dans la langue de l'enfant. La seconde se lit par deux mots : la bonne reponse est une forme de la fonte, le distracteur une forme de *disparaître*.

**Forme**, methode a part : si `tache(item)` vaut temperature et que `MOT_FONTE` est trouve dans `plat(bonneReponse)`, chaque distracteur ou `(?<!\p{L})dispar(?:ai|u|ition)\p{L}*(?!\p{L})` est trouve leve *"TRV-CE1-2 : '%s' est vrai quand la glace fond, distracteur de '%s'"*.

| Cas | Attendu | Obtenu |
|---|---|---|
| item 12 du premier jet, recopie dans le jeu fautif, juge seul | refus | refus (*les glaçons disparaissent*) |
| *la glace fond* ; *l'eau gèle*, *la glace disparaît* (de moi) | refus | refus |
| item 11 reel : *rien ne change* ; *le glaçon fond*, *le glaçon disparaît* | passe | passe ; **tue le mutant sans la clause de la fonte**, sur le materiau reel |
| item 12 corrige, *rien ne change* | passe | passe |

**Inclusion, dite** : la garde lit toute forme de *disparaître*, pas seulement celle de la glace. *L'eau disparaît* dans un item de fonte est refuse (mesure). Un pronom (*elle disparaît*) ne se rattache pas a sa matiere par un mot ; je ne restreins pas. Le faux rouge est visible et se reecrit.

**Non garde : `TRV-CE1-2`** : une paraphrase de la disparition (*il n'y a plus de glaçons*) ; une disparition vraie dans un item dont la bonne reponse n'est pas une forme de la fonte (item 9 : glace et eau au soleil, bonne reponse *seulement de l'eau liquide* ; *les glaçons ont disparu* y passe, mesure).

### 1.4 `05-T4`

D'accord avec sa ligne : aucune liste de meteos ni de sols ne se ferme. **Non garde : `TRV-CE1-2`** pour le distracteur que la meteo de l'enonce ecarte (le brouillard au soleil) ; **non garde : `QMT-CE1-2`** pour le sol de la flaque.

## 2. `-08` (`ce1-qlm-dangers-electricite`)

### 2.1 `08-T1` : *attendre* ancre au debut

**Forme** : `ABSTENTION` = `^(?:s'eloigner|attendre|ne rien toucher|ne pas s'approcher|continuer a jouer ailleurs)(?!\p{L})` sur `plat(distracteur)`, la forme de 1.3 du dernier ajustement. Elle remplace la forme commitee (`PaquetCe1QlmDangersElectriciteTest.java:181-182`, *attendre* en egalite). Sa lecture repond a mon `P1` : elle n'admet pas *attendre* suivi d'un refus de signaler. Ma proposition d'egalite est abandonnee (section 4, `M1`).

| Sonde du jeu fautif | Attendu | Obtenu |
|---|---|---|
| 62 a 67 (les cinq abstentions, apostrophe typographique, casse, point) | refus | refus, chacune |
| **71**, *"attendre sans rien dire"* | **refus** | refus |
| 68 (*ne rien dire à personne*, *ne pas en parler*), 69 (*réparer le fil et attendre*), 70 (*attendrir le fil*) | passe | passe |

Sur tout le jeu fautif, la forme ancree ne mord que 62 a 67 et 71. Pour `marc-dev` : la sonde 71 sort de `leJeuFautifCommiteLaisseLesRefusDeSignalerLAbstentionHorsDuDebutEtAttendreSuiviDUnRefusPasser` (`:840-846`) et entre dans la liste refusee de `:830` ; le paragraphe *"Une lecture a confirmer"* (`:68-70`), la Javadoc de `:177-180` et le commentaire de la sonde 71 cessent d'etre vrais.

**Materiau reel** : 0 distracteur du paquet, premier jet comme corrige, ne commence par *attendre*. Ailleurs, les 7 distracteurs du corpus de 1.3 du dernier ajustement, aucun dans `ce1-qlm-*` : ils ne sont pas lus, la garde ne lit que `-08`.

### 2.2 `08-T2` : *adulte* sans *ne* en tete

**Encodable, et encode, a la place de `g9`.** Sa regle 2 dit *"Aucune alerte a un adulte n'est une proposition ... et toute autre forme"* : les trois formes de `g9` n'en etaient qu'une partie. La forme qu'elle demande contient `g9` : toute proposition que `ALERTE` refuse porte *adulte* et commence par *prevenir*, *appeler* ou *le dire*, jamais par *ne*. Garder les deux ferait deux gardes pour un refus ; `ALERTE` est remplace, la methode et le message (`NRC-CE1-1`) restent.

**Forme** : sur `plat` de chaque proposition, bonne reponse et distracteurs, refus si `(?<!\p{L})adultes?(?!\p{L})` est trouve et `^ne(?!\p{L})` ne l'est pas.

| Sonde | Attendu | Obtenu |
|---|---|---|
| distracteurs *"aller voir un adulte"*, *"avertir un adulte sans attendre"*, *"attendre un adulte"* (ses trois) | refus | refus, chacune ; la troisieme est aussi refusee par `08-T1`, elle se juge sur le message de `08-T2` |
| bonne reponse *"aller voir un adulte"* (de moi) | refus | refus ; tue le mutant qui ne lit que les distracteurs |
| *"demander à un adulte de ne pas toucher"* (de moi) | refus | refus ; tue le mutant qui cherche *ne* partout |
| sondes 14, 54, 55, 56 (les refus de `g9`) | refus | refus, chacune |
| *"ne rien dire à un adulte"*, *"ne pas déranger un adulte"*, *"Ne pas prévenir un adulte."* ; sondes 57, 58, 68, 75 | passe | passe |

**Inclusion, dite** : un distracteur negatif ecrit sans *ne* qui nomme un adulte (*"croire qu'un adulte a déjà vu cela"*, `cp-emc-exclusion`) est refuse. Ses quatre refus de signaler admis (6.4 du dernier lot) commencent tous par *ne* : le faux rouge se reecrit dans une forme qu'elle admet. Mesure : 40 propositions du corpus portent *adulte* sans *ne* en tete, **0 dans `ce1-qlm-*`**, 0 dans le paquet, premier jet comme corrige.

**Non garde : `NRC-CE1-1`** : une alerte apres un debut en *ne* (*"ne pas crier et prévenir un adulte"* passe, mesure) ; une alerte qui nomme quelqu'un d'autre (*"aller voir la maîtresse"* passe, mesure ; *ses parents*, *un grand*).

Pour `marc-dev` : le nom `leJeuFautifCommiteLaisseLAlerteNieePasserEtNeRefuseQueLAlerteDuDebut` (`:805`) ne dit plus ce que la garde fait ; le commentaire de `ALERTE` (`:173`) non plus.

### 2.3 `08-T3` : la partie lexicale de la regle 4 sur les indices

**Encodable, et encode.** Ses deux listes sont fermees par sa regle 4 et sa lecture (3.1, 6). **Forme**, methode a part : la tache est celle de la declaration (`TACHE_DECLAREE`, deja verifiee par `verifierLesDeclarations`) ; sur `plat(indice)`, refus si un motif de sa tache est trouve, message *"TRV-CE1-5 : l'indice '%s' de '%s' nomme %s"*.

```
geste  : (?<!\p{L})(?:touch\p{L}*|approch\p{L}*|branch\p{L}*|sech\p{L}*(?!-cheveux)|prev(?:en|ien)\p{L}*|adultes?)(?!\p{L})
danger : (?<!\p{L})(?:abim\p{L}*|cass(?:e|es|ee|ees|er|ent)|eaux?)(?!\p{L})
```

*Sèche-cheveux* est exclu : c'est un appareil de la situation `eau`, pas le geste. *Cassé* est enumere pour ne pas lire *casserole*. *Eau* n'est refuse qu'en A, comme sa regle le dit (3.1 de sa lecture : *"Eau n'est refuse que dans l'indice de A"*).

| Sonde | Attendu | Obtenu |
|---|---|---|
| B *"Ne touche à rien."*, *"Pense à un adulte."* ; A *"Regarde ce qui est près de l'eau."* (les siennes) | refus | refus, chacune |
| B *"Sèche-toi bien."* (de moi) | refus | refus ; tue le mutant qui exclut tout trait d'union apres *sech* |
| B *"Ne le branche pas."*, A *"Cherche la prise cassée."* (de moi) | refus | refus |
| B *"Regarde ce qui est à côté du sèche-cheveux."* (de moi) | passe | passe ; tue le mutant sans l'exclusion de *sèche-cheveux* |
| B *"Pense à l'eau qui reste sur sa peau."* (son indice corrige de Zoe) | passe | passe ; tue le mutant qui applique la liste de A a B |
| les douze indices du premier jet ; les douze du corrige | passe | passe |

**Ce que la garde ne mord pas, dit** : aucun indice reel, ni du premier jet ni du corrige. Les deux defauts **a** de sa lecture (Zoe, *ses mains* ; Noe, *la prise*) passent cette garde : ni *mains* ni *prise* ne sont dans ses listes. Sa preuve repose sur ses phrases. **Non garde : `TRV-CE1-5`** : un indice qui designe la bonne reponse par un autre mot que ceux des deux listes ; qu'un indice soit une methode ; *débrancher* (`branch` n'est pas lu apres une lettre, *debrancher* passe). Le nom *branche* (d'arbre) serait refuse : faux rouge visible. Dans le reste du corpus, la liste `geste` trouve 138 indices et la liste `danger` 74 (par exemple *"l'aimant qu'on approche"*, `ce1-qlm-aimant`) : c'est pourquoi la garde ne lit que `-08` et n'entre pas dans le test transverse.

### 2.4 `08-T4` : le mot commun aux deux distracteurs de A

**Encodable, et encode.** C'est la lettre de sa regle 3 en A (*"les deux distracteurs n'ont pas en commun un mot ou un trait que la bonne reponse n'a pas"*), partie *mot*. **Forme**, methode a part, sur les items `danger` : `mots(x)` = les segments de `egalite(x)` separes par un blanc, insecable compris, qui portent une lettre ou un chiffre (la segmentation de `PaquetLu.mots`) ; `C` = les mots communs a `mots(d1)` et `mots(d2)`, moins `mots(bonne)` ; refus si `C` n'est pas vide, message *"TRV-CE1-2 : les deux distracteurs de '%s' ont en commun %s, que la bonne réponse n'a pas"*.

| Sonde | Attendu | Obtenu |
|---|---|---|
| Maya du premier jet, recopie dans le jeu fautif | refus | refus, `[loin]` |
| Adam du premier jet, recopie dans le jeu fautif | refus | refus, `[debranche]` |
| Maya et Adam corriges ; Jade ; Mehdi (paquet reel) | passe | passe, chacun |
| *une prise cassée* ; *un fil rangé*, *un appareil éteint* (de moi) | refus | refus, `[un]` |

**L'article compte**, comme la lettre de sa regle ; si elle l'exclut, une liste fermee d'articles se retire de `C`, une ligne. **B n'est pas lu** : sa regle dit *En A*, et Sami corrige partage *un* dans ses deux distracteurs (sa lecture, 3.2). **Non garde : `TRV-CE1-2`** : le trait hors du mot (*débranchée* et *débranché* sont deux mots ; un etat, un usage communs).

### 2.5 `g2` et `G1` apres ses corrections

**Inchangees, et aucune forme ne change.** `g2` (`verifierLesGestes`) et `G1` (`verifierQueChaqueGesteEstLaBonneReponseDUnItemGeste`) lisent la bonne reponse et l'enonce des items `geste` ; aucune de ses corrections ne touche une bonne reponse.

- **Hugo** : l'enonce corrige porte `eviers?`, repere de `eau`, et *branchée*, qui n'est le repere d'aucune situation : `g1`, `g2` et `G2` (une seule situation) le lisent comme avant. Hugo reste **le seul item `APPROCHER`** : la clause `APPROCHER` de `G1` tient par lui seul, au premier jet comme apres correction, et la sonde `unPaquetPriveDesItemsDUnDesTroisGestesEstRefuse` le retire et mord.
- **Zoe**, declaration *b, b* : aucune garde de `-08` ne lit les familles ; le test transverse en lit le format et la presence dans l'en-tete, *b* y est.
- **Maya**, distracteur *"une lampe débranchée près de l'évier"*, et **Adam** : les distracteurs de `danger` ne sont lus que par `verifierUnSeulGeste` (aucun n'est une forme) et, desormais, par `08-T4`.
- **Sami**, **Noe** : *réparer la prise...*, *nettoyer la prise...* ne sont pas des formes par egalite. **Nina** : *l'utiliser avec les mains mouillées* n'est pas une forme et ne commence pas par *ne* (`G4`).

### 2.6 Ses textes corriges de la section 4, par toutes les gardes de `-08`

**Tous passent.** Mesure sur la copie corrigee (section 3) : les 47 tests de `PaquetCe1QlmDangersElectriciteTest`, dont toutes les gardes en place (`g1` a `g9`, `G1` a `G5`) et les sondes de paquet reel, passent ; le test transverse et l'entree de rang aussi ; puis les quatre formes de 2.1 a 2.4, item par item, ne levent rien sur les douze items corriges. Les 13 lignes ecrites (points 1 a 7) ne levent aucune garde, en place ou ajustee.

## 3. Mesure

- **Copie hors depot** : l'arbre a `a373b7d3`, sans `.git` ni `target`, copie hors du depot ; ses corrections appliquees par un script hors depot, non commite, qui verifie que chaque texte actuel est trouve une fois et une seule : **5 remplacements pour `-05`, 13 pour `-08`**, ceux de ses deux tableaux, la ligne de famille de l'item 12 de `-05` reperee par l'enonce qui la suit (*d, f* est ecrit deux fois).
- **Tests reels sur la copie** : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test -Dsurefire.failIfNoSpecifiedTests=false`, la machine virtuelle des tests rapportant `java.version=21.0.10`, `java.vm.version=21.0.10+8-LTS-217` (rapport surefire de `PaquetCe1QlmDangersElectriciteTest`). **2 542 tests, 1 echec, 0 erreur**, 201 classes de rapport ; l'echec est `s12` de `-05`, message en 1.1. Perimetre : le module `adapter-content` et ses dependances, sur la copie corrigee, gardes **non ajustees** ; ni un `verify`, ni la porte technique.
- **Programme hors depot** : un fichier Java, non commite, compile contre les classes de test de la copie et lance sur le meme JDK, **qui a rapporte `java.version=21.0.10`, `java.vm.version=21.0.10+8-LTS-217`**. Il appelle les methodes du depot (`PaquetLu.lirePaquet`, `plat`, `egalite`, `tache`, `rienNeChange`, `defautsDeLaRegleQuatre`) et applique les formes de ce fichier, recopiees ; `BOUILLIR` et `MOT_FONTE` sont recopies du test. **Ce n'est pas le test** : la preuve dans le depot sera l'execution des deux classes sur leurs jeux fautifs commites.
- **Verdicts** : **44 verdicts de sonde, 44 conformes**, apres une correction de ma main : ma premiere forme de `sech` (`(?!-)`) laissait passer *"Sèche-toi bien."* ; elle devient `(?!-cheveux)`. Les phrases de `nadia-prof` et les items des premiers jets sont recopies a l'identique ; les sondes *de moi* valent moins.
- **Paquets** : premier jet et corrige de `-05` et de `-08`, 12 items chacun. Premier jet : `05-T2` mord l'item 8 seul, `05-T3` l'item 12 seul, `08-T4` Maya et Adam seuls ; `08-T1`, `08-T2`, `08-T3` ne mordent rien. Corrige : **aucune forme ne mord**. `s12` : *"distracteur de 2"* au premier jet, *"distracteur de 3"* corrige.
- **Corpus** : tout `*.yaml` de `adapters/adapter-content/src/main/resources/contenu/` a `a373b7d3`, **196 fichiers, 3 255 items portant `bonneReponse`** (collecte recursive par `new Yaml().load`) ; exclus : les jeux fautifs. Les motifs y sont appliques sans le calcul de tache, pour leur precision : *attendre* en tete de distracteur 7, aucun dans `ce1-qlm-*` ; *adulte* sans *ne* en tete 40 propositions, aucune dans `ce1-qlm-*` ; `05-T2` 1 item, `05-T3` 1 item, l'item 8 et l'item 12 de `-05` ; listes de `08-T3` en section 2.3. Chaque garde ne lit que son paquet : **sur le contenu deja ecrit, aucun refus hors des quatre items de premier jet que ses corrections retirent.**

## 4. Passages de mes avis que j'abandonne

Mes avis ne sont pas reecrits. Chaque passage ci-dessous y reste et cesse de valoir.

| Code | Passage | Marqueur |
|---|---|---|
| `M1` | Dernier ajustement, section 8, ligne `P1`, colonne *"Ce que la forme fait"* : *"Si elle veut admettre attendre suivi d'un refus de signaler, l'alternative attendre devient une egalite (attendre seul), une ligne"* | [corrige le 2026-10-27, `theo-archi`, `avis-theo-palier-5-gardes-apres-lecture-ce1-qlm-05-08.md`, section 2.1 : `nadia-prof` n'admet pas *attendre* suivi d'un refus ; l'ancre reste, aucune egalite] |
| `M2` | Avis 0608, 2.2, *"Encodable, non propose : refuser toute proposition qui porte adulte sans commencer par ne. ... Elle mordrait par inclusion."* | [corrige le 2026-10-27, `theo-archi`, ce fichier, section 2.2 : retenue a la place de `g9` ; l'inclusion porte sur d'autres lots, que la garde ne lit pas] |
| `M3` | Avis 0608, 2.2, *"Ce que g9 ne couvre pas"* : *un autre verbe*, *l'alerte qui ne commence pas la proposition*, *la forme conjuguee* ; et section 5, *"une alerte a un adulte hors des trois formes de g9"* | [corrige le 2026-10-27, `theo-archi`, ce fichier, section 2.2 : couverts des qu'ils nomment un adulte sans *ne* en tete ; restent l'alerte apres un *ne* et un autre nom] |
| `M4` | `avis-theo-palier-5-gardes-ce1-qlm-06-08.md`, 5, *"Encodable si elle le demande, non propose : la partie lexicale de sa regle 4 de -08 ... et la partie lexicale de sa regle 3 en A de -08"* | [corrige le 2026-10-27, `theo-archi`, ce fichier, sections 2.3 et 2.4 : demandees, encodees] |
| `M5` | `avis-theo-palier-5-gardes-ce1-qlm-04-05-et-article-02.md`, *"Ce que T05-3 ne garde pas"*, *"un distracteur que la situation produit (la fumee blanche pour une casserole qui bout)"*, et la ligne *"non garde : TRV-CE1-2"* de sa section 6, *"le distracteur que la situation produit"* | [corrige le 2026-10-27, `theo-archi`, ce fichier, section 1.2 : *nuage* et *fumée* sous l'ebullition sont gardes ; le reste de ce que la situation produit reste non garde] |

## 5. Ce qui reste lu par `nadia-prof`

En plus de ce que ses deux lectures disent garder seule (section 4 de sa lecture de `-05`, section 11 de sa lecture de `-08`), et que ce fichier ne change pas :

- `-05`, *"non garde : `TRV-CE1-2`"* : ce qu'une autre situation que l'ebullition produit, et la buee de la cuisine (1.2) ; une paraphrase de la disparition, une disparition vraie hors d'un item de fonte (1.3) ; le distracteur que la meteo ecarte (1.4). *"non garde : `QMT-CE1-2`"* : le sol de la flaque (1.4).
- `-08`, *"non garde : `NRC-CE1-1`"* : une alerte apres un *ne* en tete, une alerte a quelqu'un d'autre qu'un adulte nomme (2.2). *"non garde : `TRV-CE1-5`"* : un indice qui designe la bonne reponse par un mot hors des deux listes, dont les deux defauts **a** de son premier jet ; *débrancher* (2.3). *"non garde : `TRV-CE1-2`"* : le trait commun hors du mot (2.4) ; une abstention ecrite hors de ses cinq formes ou apres le debut (inchange).

**Desaccord avec ses lectures : aucun.** Deux points de forme lui sont soumis, sans bloquer `marc-dev` : l'article compte dans `08-T4` (2.4) ; `05-T3` refuse toute forme de *disparaître* dans un item de fonte, *l'eau disparaît* compris (1.3).

## 6. Actions, duree, verifications

| Code | Quoi | Qui |
|---|---|---|
| `A1` | Dans `PaquetCe1QlmEauTroisEtatsTest` : le fragment de `s12` (1.1) ; les gardes de 1.2 et 1.3, leurs sondes refusees recopiees des items 8 et 12 du premier jet dans `eau-fautive.yaml`, chacune jugee seule, et leurs cas qui passent sur le paquet reel | `marc-dev` |
| `A2` | Dans `PaquetCe1QlmDangersElectriciteTest` : `ABSTENTION` (2.1) et la sonde 71 deplacee ; `ALERTE` remplacee par la forme de 2.2, ses sondes neuves ; les gardes de 2.3 et 2.4, leurs sondes, Maya et Adam du premier jet recopies dans `dangers-fautifs.yaml` ; la Javadoc et les noms de tests cites en 2.1 et 2.2 | `marc-dev` |
| `A3` | Les deux points de forme de la section 5 | `nadia-prof`, par `sofia-sm` |

**Duree** : debut non lu par `date`, je ne l'ecris pas ; fin lue par `date` apres les verifications ci-dessous, rapportee a l'agent lanceur et non ecrite ici, pour ne pas changer le fichier verifie.

**Verifications avant commit**, ce fichier present dans l'arbre, base `a373b7d3`. Il ne cree ni ne renomme aucun identifiant de condition.

- `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest -Dsurefire.failIfNoSpecifiedTests=false` : *"Tests run: 10, Failures: 0, Errors: 0, Skipped: 0"*, *"BUILD SUCCESS"*. Perimetre : ce seul test, module `bootstrap` et ses dependances ; ni un `verify`, ni la porte technique. Relance apres l'ecriture de ces deux lignes, meme resultat.
- `bash .github/scripts/verifier-chemins.sh < /dev/null`, ce fichier indexe : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*, sortie 0. Relance apres l'ecriture de ces deux lignes, meme resultat.
- Aucun `mvn verify`, aucun autre job de `.github/workflows/ci.yml` lance.

**Signe** : `theo-archi`, s57, palier 5, 2026-10-27.
