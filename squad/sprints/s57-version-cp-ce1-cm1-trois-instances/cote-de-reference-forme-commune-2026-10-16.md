# s57 - palier 2 : cote de reference de Q-R4 et mesure appariee de la forme commune des propositions longues

- **Auteure** : `ines-design`. **Date** : 2026-10-16, 15:02 (UTC+02:00), lue par `date`.
- **Arbre** : branche `s57-ines-p2-cote`, issue de `round-two` a `0c0d2142` (`git log -1 --format=%h`), worktree dedie `recreo-s57-ines-p2-cote`. Worktree de la base : `recreo-s57-ines-p2-base`, detache, a `aa9d703c`. `git status --short` vide avant ecriture.
- **Ce que ce fichier est** : le fichier date que `Q-R5` et le critere 4 de `S57-FORME-COMMUNE-PROPOSITIONS-LONGUES` demandent (`PA2`) : commit du jar de base, largeur, hauteur, methode, les deux commandes git, et les elements 1 a 4 de la clause `Q-R4` ecrits en entier. Il porte aussi la mesure appariee du critere 6.
- **Ce qu'il n'est pas** : un jugement. Il compte des pixels. Il ne prononce aucun Done, ne leve aucune reserve, ne dit pas si la forme est bonne pour un enfant. Le veto, et la reponse a `Q-P4`, sont a `nadia-prof`. Aucun fichier de `domain`, `application`, `adapters/*/src/main` ni `bootstrap` n'est touche ; aucun paquet, aucun catalogue.

## Resume

| Code | Point | Valeur | Section |
|---|---|---|---|
| CF1 | Base de la cote (`Q-R5`) | **`aa9d703c`**, le commit d'ou part la branche de la forme ; sommet mesure : `0c0d2142` | 1 |
| CF2 | `Q-P4` : base de code identique a celle de `6dbb855d` ? | **Non tenue** : `git diff --stat 6dbb855d aa9d703c -- pom.xml domain application adapters bootstrap` rend 10 fichiers. La cote de `6dbb855d` n'est pas reprise : tout est remesure ici | 2 |
| CF3 | Critere 4, deuxieme commande : `git diff --stat <base> <commit livre>` ne nomme aucun paquet ni `catalogue.yaml` | **Tenu pour `dc6f423d`** (5 fichiers, la forme et son test). **Non tenu pour le sommet `0c0d2142`** (22 fichiers, dont `catalogue.yaml` et 3 paquets), aucun des six paquets de la cote n'en fait partie | 2 |
| CF4 | Element 1 : 22 items de `ce1-verbe-sujet` a 534 ; `"les gateaux"` | 534 -> 418 a 448 (22 sur 22) ; `"les gateaux"` = item 16 de `ce1-accord-groupe-nominal`, 486 -> 400 | 4.1 |
| CF5 | Element 2 : 11 items de `ce1-texte-narratif` a 550 | 550 -> 434, 11 sur 11, dans les six ordres | 4.2 |
| CF6 | Element 3 : 65 enonces d'`additions-cp` | **65 sur 65 mesures** : 486 sur la base (530 observations), **370** sur le sommet (540) ; une seule cote par jar | 4.3 |
| CF7 | Element 4 : 20 items de `comprehension-cm1` | base 1 sur 20 au plus 551 (item 0, 550) ; sommet 464 pour cet item ; **aucun item ne perd le pli** ; sommet 9 sur 20 en ordre servi | 4.4 |
| CF8 | Criteres 1 et 2, trois jeux, 744 x 551 | base 23 sur 72 ; sommet **72 sur 72**, 432 couples (item, ordre) sur 432 au plus 548 | 5 |
| CF9 | `ce1-texte-narratif`, items 13, 17, 18 | **Constate** : 18 a 552 dans les six ordres ; 13 et 17 a 552 dans 2 ordres sur 6 chacun, a 478 dans les quatre autres. Ordre servi de cette partie : un seul item a 552 (le 18) | 4.2 |
| CF10 | Critere 6 : echantillon apparie, 40 jeux, 720 / 744 / 768 / 992 / 1200 | **0 etat ou l'indice ou la reponse revelee passe de visible a sous le pli, 0 debordement neuf a droite**, aux cinq largeurs ; 2248 etats par largeur | 6 |
| CF11 | Ecarts de mesure | une valeur transitoire non reproduite (355,8) ; un releve ecarte par ma faute | 7 |

## 1. Base, sommet, jars

| | Base | Sommet |
|---|---|---|
| Commit | `aa9d703ce1af7cd0c0964ae12e85c6864db0481c` | `0c0d214255fca0174d2db6b8539634430090da8d` |
| Ce que c'est | sommet de `round-two` quand `lea-dev` coupe la branche de la forme (`rapport-lea-dev-palier-2.md`, "Base"), **parent du premier commit de la branche de la forme** | tete de `round-two` dont le code est servi (la tete poussee, `2043b3f3`, ne change que `planning.md`) |
| Build | `bash outils/mvn-jdk21.sh -B clean package -DskipTests`, depuis la racine du worktree | idem |
| Jar | `bootstrap/target/recreo.jar`, SHA-256 `f4eb0d40dc53fb8b270ffa7a87a430c95334d4670b4b6e42caa02a04e5d7de89` | SHA-256 `61874630c2cf5471800ebef0f5323861c0e4e9e7158c78ad047c02b4fcbe5ff4` |
| Lancement | JDK 21.0.10 (ligne `Starting RecreoApplication ... using Java 21.0.10` du journal), `--server.port=18543` | idem, `--server.port=18544` |
| PID, lu dans le journal du jar | 50068 | 34904 |
| Feuille servie, SHA-256 apres retrait des retours chariot (le worktree est en CRLF) | `496b2368e0d8bb12ff43c4e9b28fef93894d9df0228257e9570996fcc76f40e6` = `git show aa9d703c:adapters/adapter-web/src/main/resources/static/css/recreo.css \| sha256sum` | `c312cd1b2ebbfc4a52508b250f1b328943e1e3a6384dba95c3897a59045f3ef6` = meme commande a `0c0d2142` |

**Pourquoi `aa9d703c` et pas le parent de `aca0d90f`.** Le parent de la fusion `aca0d90f` est `0e7af3ce`, qui porte deux fichiers de plus que `aa9d703c` (`git diff --stat aa9d703c 0e7af3ce -- pom.xml domain application adapters bootstrap` : `cm1-sciences-extraire-information.yaml`, `AucunMotServiNeSEcarteDeSonAccentTest.java`). `Q-R5`, lu par `nadia-prof` (`reponse-nadia-q-palier-1.md` 1) : *"le parent de son premier commit"* ; `planning.md` : *"le sommet de `round-two` au moment ou `lea-dev` coupe"*. `lea-dev` et `theo-archi` nomment tous deux `aa9d703c`. Le jar compare a la base est celui du commit livre, `dc6f423d`, que `aa9d703c` precede.

Commandes de `Q-R5` (sorties lues le 2026-10-16) :

```
git merge-base --is-ancestor aa9d703c dc6f423d        -> sortie 0
git merge-base --is-ancestor aa9d703c 0c0d2142        -> sortie 0
git merge-base --is-ancestor dc6f423d 0c0d2142        -> sortie 0
git diff --stat aa9d703c dc6f423d -- pom.xml domain application adapters bootstrap
  recreo.css (+34), LaFormeCommuneDesPropositionsLonguesEstEpingleeTest.java (+159), trois feuilles fautives sous
  contreexemple (+4, +2, +4) : 5 fichiers, 203 insertions, aucun paquet, aucun catalogue.yaml
git diff aa9d703c..dc6f423d -- domain application adapters/adapter-web bootstrap | grep '^+' | grep -c -E 'phrases-types|accord-groupe-nominal|ce1-verbe-sujet|texte-narratif'   -> 0
(meme commande, aa9d703c..0c0d2142)                                                                                                                                -> 0
```

## 2. `Q-P4` et le perimetre du sommet

`nadia-prof` (`reponse-nadia-q-palier-2.md` 3.3) : la cote de `6dbb855d` vaut pour la base reelle si `git diff --stat 6dbb855d <base> -- pom.xml domain application adapters bootstrap` ne rend rien ; sinon la cote se remesure sur la base reelle, sans exception de contenu.

```
git diff --stat 6dbb855d aa9d703c -- pom.xml domain application adapters bootstrap
  10 files changed, 10 insertions(+), 10 deletions(-)       (en-tetes de dix paquets ce1-*, D5)
git diff --stat 6dbb855d 0c0d2142 -- pom.xml domain application adapters bootstrap
  31 files changed, 2158 insertions(+), 50 deletions(-)
```

**Constat : la condition de `Q-P4` n'est tenue sur aucune des deux bases.** Je n'ai donc rien repris du rapport `rapport-ines-r8-forme-commune.md` (jar de `6dbb855d`) : chaque chiffre ci-dessous vient d'une mesure faite le 2026-10-16 sur les jars de cette section. Ou la mesure de `6dbb855d` et celle-ci se recouvrent, elles rendent les memes nombres (base : `TPH` 0 sur 24 en 568 - 650, `GNA` 1 sur 24 en 486 - 650, `VSJ` 22 sur 24 en 534 - 582 ; sommet : 24, 24, 24 sur 24, maxima 548, 548, 496).

**Ce que le sommet porte de plus que la base, classe par fichier** (`git diff --name-only aa9d703c 0c0d2142 -- pom.xml domain application adapters bootstrap`, 22 fichiers) :

| Ce que c'est | Fichiers | Change une page servie |
|---|---|---|
| La forme (`dc6f423d`) | `recreo.css` (+34) ; `LaFormeCommuneDesPropositionsLonguesEstEpingleeTest.java` et trois feuilles fautives ; une quatrieme feuille fautive (`declaration-ajoutee`) | `recreo.css` seul |
| Lot `-05` | `ce1-pronoms.yaml`, `PaquetCe1PronomsTest.java` et 4 fichiers fautifs, `catalogue.yaml`, `TexteAccentueExposeParLePortEntrantTest.java` | un jeu neuf (`bandit-manchot-pronoms-ce1`) |
| Lot `-24` | `ce1-familles-de-mots.yaml`, `PaquetCe1FamillesDeMotsTest.java` et 2 fichiers fautifs, `catalogue.yaml` | un jeu neuf (`bandit-manchot-familles-de-mots-ce1`) |
| `N1` et `N2` | `cm1-sciences-extraire-information.yaml` (4 lignes) | un enonce et trois indices de `sciences-extraire-information-cm1` |
| Garde de l'accent, garde de `C1'` | `AucunMotServiNeSEcarteDeSonAccentTest.java`, `UnEcranNeGouvernePasLApparenceDUnAutreTest.java` | aucune |

Verifications faites pour que le sommet serve de cote : `git diff --stat aa9d703c 0c0d2142 -- <ces six paquets>` est **vide** pour `ce1-phrases-types`, `ce1-accord-groupe-nominal`, `ce1-verbe-sujet`, `ce1-texte-narratif`, `cm1-comprehension` et `cp-additions-jusqu-a-10` ; `git diff --stat aa9d703c 0c0d2142 -- adapters/adapter-web/src/main/resources/templates` est vide (aucun gabarit ne change) ; le diff du `catalogue.yaml` n'ajoute que deux entrees (`familles-de-mots-ce1`, `pronoms-ce1`) et ne touche aucune entree existante. **Ma lecture, a `nadia-prof` de la trancher** : pour les six jeux de la cote, les deux jars different par `recreo.css` et par rien d'autre ; ce n'est pas une exception de contenu que je demande, c'est une mesure faite sur les deux jars. Si elle veut rejouer sur `dc6f423d` plutot que sur le sommet, la seconde commande de `Q-R5` est tenue ; sur le sommet elle ne l'est pas, et `sofia-sm` (`planning.md`, section 9) a deja dit que la remesure est forcee par toute fusion apres la coupe.

## 3. Methode

- **Fenetre** : 744 x 551, `Emulation.setDeviceMetricsOverride` (`mobile: false`, facteur 1). Mesure : `getBoundingClientRect().bottom + scrollY` du premier `.indice` du document, **apres une erreur** (la premiere proposition fausse servie). Pli : bas de `.indice` au plus 551. Chrome 155.0.8059.40 sans tete (`/json/version`), Node v24.12.0, Windows 11.
- **Cote du jar de base seul** : `pilote-p2.mjs` sans feuille de base (`-`) joue le jar de `aa9d703c` et mesure sa propre page. C'est la cote de reference.
- **Mesure appariee** (critere 6, et lecture du gain) : `pilote-p2.mjs` joue le jar du **sommet** ; a chaque etat, sur le meme DOM, la feuille du sommet (bras `cand`) puis la feuille `recreo.css` **servie par le jar de base** (`curl /css/recreo.css` sur 18543), inseree a la place du lien (bras `base`). Les gabarits des deux jars sont identiques (section 2), donc seule la feuille change. **Controle du bras `base`** : sur les couples (item, ordre) que les deux releves partagent (meme suite de textes, meme proposition barree), le bras `base` rend la cote du jar de base joue seul sur **0 ecart sur 66, 90, 78, 66 et 54 couples** (`phrases-types`, `accord-groupe-nominal`, `verbe-sujet`, `texte-narratif`, `comprehension-cm1`, 744), de meme aux quatre autres largeurs ; les couples ne sont pas apparies par numero d'ordre, qui depend de l'ordre servi de chaque partie (premier jet de mon comparateur : faux ecarts, corriges). Les deux releves ne sont pas comparables couple a couple quand la proposition barree differe (78, 54, 66, 54 et 66 couples a 744) : la comparaison ne vaut que sur les autres. Sur l'ensemble des couples, minima, maxima et comptes au plus 551 sont **identiques** entre le jar de base seul et le bras `base` (section 5).
- **Ordres** : a chaque erreur, les six permutations des trois propositions sont mesurees (boutons reranges dans le DOM puis remis). L'ordre servi est tire au hasard par le serveur : il differe d'une partie a l'autre, et la cote de 2 items de `ce1-texte-narratif` et d'1 item de `ce1-phrases-types` en depend (section 4.2).
- **Largeurs** : 744 est la largeur du critere. J'ai aussi mesure 720, 768, 992 et 1200 pour les cinq jeux de la cote et l'echantillon, parce que la forme est dans un `@media (min-width: 720px)` et que `lea-dev` ne les avait pas mesurees. Rien dans `Q-R4`, `Q-R5` ni le critere 4 n'exige une autre largeur que 744.
- **Items** : `extraire-items-p2.py` lit le YAML de chaque paquet et le catalogue (`items.json`, hors depot) ; les cinq paquets sont identiques sur les deux arbres (`cmp` sur les deux `items.json` : sortie 0). Les jeux sont joues sans lire l'item.
- **Releves versees**, compactes (`compacter-r3.mjs`) : `p2-jar-de-base.propre.json`, `p2-sommet-apparie.base.json`, `p2-sommet-apparie.cand.json`, `p2-echantillon.json`, `p2-additions-cp-jar-de-base.json`, `p2-additions-cp-sommet.json`, `p2-reprise-gna-1.cand.json` a `-3`. Cote item par item : `p2-cotes-par-item.md`. Les sorties brutes (une vingtaine de Mo) ne sont pas versees.

## 4. Les quatre elements de `Q-R4`

### 4.1 Element 1 : `ce1-verbe-sujet` et `"les gateaux"`

`node squad/conformite/releves-s57/elements-p2.mjs p2-jar-de-base.propre.json p2-sommet-apparie.cand.json items.json 744`

| Ensemble | Jar de base seul (bas de `.indice`) | Sommet | Perdu |
|---|---|---|---|
| 22 items de `ce1-verbe-sujet` a 534 sur la base | 534 | **418 a 448**, 22 sur 22 au plus 551, dans les six ordres | 0 |
| les 24 items de `ce1-verbe-sujet` | 534 - 582, 22 sur 24 | 418 - 496, **24 sur 24** | 0 |
| `"les gateaux"` | 486 | 400 | 0 |

**A quel jeu `"les gateaux"` renvoie.** `grep -n 'les gâteaux' adapters/adapter-content/src/main/resources/contenu/ce1-accord-groupe-nominal.yaml` rend la ligne 126 (`bonneReponse: "les gâteaux"`, distracteurs `"les gâteau"` et `"les gâteaus"`, enonce *"Quel groupe de mots est bien écrit ?"*). `catalogue.yaml` relie ce paquet a un seul jeu, `bandit-manchot-accord-groupe-nominal-ce1` (`paquet: ce1-accord-groupe-nominal`). Le pilote le retrouve comme **item 16 (compte depuis 0)**, le meme que le rapport du 2026-10-15 (*"`les gateaux` (item 16)"*). Aucun autre paquet n'a ce groupe de mots comme proposition (`grep -n -i 'g.teaux'` sur `contenu/*.yaml` : le meme paquet l'a une fois de plus dans *"les gros gâteaux"* ligne 36, et `cp-problemes-additifs` et `ce1-division` le citent dans un enonce, jamais comme proposition). Le critere 3 de la story dit *"sur `les gateaux` (436 - 486)"* : c'est donc le jeu **`GNA`** (`ce1-accord-groupe-nominal`), et la mesure 436 - 486 est le haut et le bas de `.indice` sur la base. Avec la forme : haut 320, bas 400 (l'indice passe sur deux lignes dans la colonne de droite).

**Ce qui a le plus d'air** : le jeu `ce1-verbe-sujet` a ses deux items les plus bas (15 et 19, enonce sur trois lignes, 582 sur la base) a 496 sur le sommet, 55 px sous la barre de 551.

### 4.2 Element 2 : `ce1-texte-narratif`, les 11 items a 550, et les items 13, 17 et 18

`node squad/conformite/releves-s57/tableau-p2.mjs ... 744 bandit-manchot-texte-narratif-ce1` ; detail dans `p2-cotes-par-item.md`.

| Ensemble | Jar de base seul | Sommet | Perdu |
|---|---|---|---|
| 11 items a 550 sur la base (0, 1, 2, 3, 4, 7, 8, 10, 11, 12, 19) | 550, dans les six ordres | **434**, 11 sur 11, dans les six ordres | 0 |
| les 20 items, ordre servi | 550 - 684, **11 sur 20** | 434 - 552, **19 sur 20** (le 18 seul a 552) | 0 |
| les 20 items, pire des six ordres | 11 sur 20 | **17 sur 20** (13, 17 et 18 a 552) | 0 |
| les 120 couples (item, ordre) | 66 sur 120 | **110 sur 120** (10 couples a 552) | 0 |

**Items 13, 17 et 18, constate.** Le backlog dit *"items 13, 17 et 18 a 552 en ordre servi"*. La mesure dit :

- **item 18** (*Que se passe-t-il a la fin ?*, `une fleur pousse` / `Chloé plante une graine` / `elle l'arrose chaque jour`) : **552 dans les six ordres**, base 684 ;
- **item 13** (*Que fait Inès en dernier ?*) : 478 dans quatre ordres, **552 dans deux** sur six (base 602 ou 684) ;
- **item 17** (*Que fait Ibrahim en dernier ?*) : 478 dans quatre ordres, **552 dans deux** sur six (base 602 ou 684).

Les trois valeurs sont donc a 552 **au pire ordre**. Dans l'ordre servi de ma partie, un seul item est a 552 (le 18) ; `lea-dev` rapporte 18 sur 20 en ordre servi dans la sienne. C'est l'ordre tire par le serveur, pas un ecart entre nos mesures. Ce qui vaut a 744, 720 et 768 ; a 992 et 1200, les 20 items sont au plus a 478. **La forme ne leve pas ces trois items d'un pixel** : `S57-RCT-CE1-R1` reste ouverte.

### 4.3 Element 3 : les 65 enonces d'`additions-cp`

`node squad/conformite/releves-s57/mesurer-additions-cp.mjs <chrome> <port de jeu> <port de debogage> <repertoire de sortie>`, un jar par lancement (18543 puis 18544), script de `marc-dev` (`baefae1d`), non modifie. Sortie : `p2-additions-cp-jar-de-base.json`, `p2-additions-cp-sommet.json` ; tableau des 65 : `p2-cotes-par-item.md`.

| | Enonces mesures | Non mesures | Hors ensemble | Observations | Parties | Anomalies | Cotes | Fenetre |
|---|---|---|---|---|---|---|---|---|
| Jar de base | **65 sur 65** | 0 | 0 | 530 (un enonce vu 1 a 16 fois) | 53 | 0 | **486**, une seule, aucun enonce a deux cotes | 744 x 551, `clientWidth` 744 |
| Sommet | **65 sur 65** | 0 | 0 | 540 (1 a 17 fois) | 54 | 0 | **370**, une seule | idem |

Les 65 enonces passent de 486 a 370, soit 116 px de gain, **tous au plus a 551 sur les deux jars** : aucun enonce n'etait sous le pli, aucun ne l'est. L'hypothese *"item = enonce"* de `TD4` tient sur les deux jars (aucune cote multiple). Le 486 est celui que `rapport-ines-r8-forme-commune.md` donnait sur 22 enonces ; le 370 y etait aussi (*"486 -> 370"*). **Le script ne mesure qu'a 744 et dans l'ordre servi** : pas de seconde largeur, pas de six ordres (section 9).

### 4.4 Element 4 : les 20 items de `comprehension-cm1`

Largeur 744, `bandit-manchot-comprehension-cm1` (`cm1-comprehension`, 20 items), six ordres.

| | Jar de base seul | Sommet |
|---|---|---|
| Bas de `.indice`, ordre servi | 550 - 792 | 464 - 690 |
| Au plus 551, ordre servi | **1 sur 20** (item 0, *Quel temps fait-il dehors ?*, 550) | **9 sur 20** |
| Au plus 551, pire des six ordres | 1 sur 20 | 8 sur 20 |
| Couples (item, ordre) au plus 551 | 6 sur 120 | 52 sur 120 |
| **Items qui perdent le pli** | - | **0** (l'item 0 passe de 550 a 464) |

`nadia-prof` ne pose *"aucune barre neuve sur un item deja sous le pli"* (`reponse-nadia-forme2-palier-1.md` 5.3). **Ce que cela laisse** : sur le sommet, 11 items sur 20 ont encore leur indice sous le pli a 744 en ordre servi (3, 4, 5, 6, 7, 9, 10, 14, 15, 17, 18) et 12 sur 20 au pire ordre (le 19 s'y ajoute). A 720 : 8 sur 20 ; a 992 : 19 sur 20 ; a 1200 : 20 sur 20. Ce n'est pas une regression, c'est un jeu que la forme laisse en partie sous le pli.

## 5. Criteres 1 et 2 : les trois jeux de la levee, sur les deux jars

`node squad/conformite/releves-s57/couples-p2.mjs ...` (tous les couples) et `cote-p2.mjs` (ordre servi). Bas de `.indice` apres une erreur, au plus 551 :

| Jeu | Largeur | Jar de base seul : ordre servi | Jar de base seul : couples | Sommet : ordre servi | Sommet : couples (bas) |
|---|---|---|---|---|---|
| `phrases-types-ce1` | 744 | 0 / 24 (568 - 650) | 0 / 144 | **24 / 24** (474 - 548) | **144 / 144** (444 - 548) |
| `accord-groupe-nominal-ce1` | 744 | 1 / 24 (486 - 650) | 6 / 144 | **24 / 24** (400 - 548)* | **144 / 144** (400 - 548)* |
| `verbe-sujet-ce1` | 744 | 22 / 24 (534 - 582) | 132 / 144 | **24 / 24** (418 - 496) | **144 / 144** (418 - 496) |
| trois jeux | 744 | **23 / 72** | 138 / 432 | **72 / 72** | **432 / 432**, maximum **548** |

\* une valeur transitoire de 355,8 pour un item, voir la section 7.

Aux autres largeurs, le sommet est a **24 / 24 et 144 / 144 pour chacun des trois jeux, a 720, 768, 992 et 1200** (maxima 548, 548, 496 a 720 et 768 ; 518, 444, 418 a 992 ; 444, 444, 418 a 1200). Le jar de base seul et le bras `base` ont les memes minima, maxima et comptes aux cinq largeurs.

Les chiffres de `lea-dev` sur `dc6f423d` (24, 24, 24 sur 24 ; maxima 548, 548, 496) sont **les memes** sur le sommet.

## 6. Critere 6 : l'echantillon apparie

`node squad/conformite/releves-s57/pilote-p2-echantillon.mjs <chrome> <profil> http://127.0.0.1:18544 <sortie.json> <recreo-base.css> 720,744,768,992,1200 <port de debogage> <jeux>`, puis `analyser-echantillon-r3.mjs` et `totaux-p2.mjs`. **40 jeux** : ceux de `rapport-ines-r8-forme-commune.md` 5.1 (cinq de francais CE1, sept de CP, quatre a texte, cinq a image, huit a propositions longues, six a propositions courtes, quatre autres dont `additions-cp`, plus `rapprocher-deux-oeuvres-cp` en temoin), **plus** `pronoms-ce1` et `familles-de-mots-ce1`, les deux jeux des lots (**42 en tout**, ceux-ci n'existent pas sur la base). Trois parties de dix manches par jeu, politique aveugle (deux appuis sur la premiere proposition non barree, un sur une barree, la reponse revelee). `sciences-extraire-information-cm1` est joue avec le texte de `N1` et `N2` **dans les deux bras** : le contenu ne differe pas entre les bras.

Totaux sur les **40 jeux** du critere (`totaux-p2.mjs ... pronoms-ce1,familles-de-mots-ce1`) :

| Largeur | Etats | Etats a comparer | Pli perdu | Pli gagne | Indice plus bas apres erreur | Debordement neuf a droite | Bloc du message plus haut | Hausse max | Bas de l'ecran gagne (hors temoin) |
|---|---|---|---|---|---|---|---|---|---|
| 720 | 2248 | 1439 | **0** | 286 | 0 | **0** | 65 | +58 px | 6 a 198 px |
| 744 | 2248 | 1439 | **0** | 292 | 0 | **0** | 62 | +58 px | 6 a 132 px |
| 768 | 2248 | 1439 | **0** | 292 | 0 | **0** | 62 | +58 px | 6 a 132 px |
| 992 | 2248 | 1439 | **0** | 250 | 0 | **0** | 50 | +12 px | 44 a 132 px |
| 1200 | 2248 | 1439 | **0** | 275 | 0 | **0** | 40 | +12 px | 44 a 132 px |

Les 42 jeux ensemble : 2365 etats par largeur, **pli perdu 0 et debordement neuf 0 aux cinq largeurs**, y compris sur `pronoms-ce1` et `familles-de-mots-ce1`. Le temoin `rapprocher-deux-oeuvres-cp` : 31 etats sur 31 **identiques** sans et avec la forme (tous champs mesures), a chaque largeur ; c'est ce que `:not(.ecran-deux-oeuvres)` doit donner. **Le bloc du message est plus haut** dans 62 etats a 744 (+58 px au plus) : meme nombre que le rapport du 2026-10-15 (62) ; ces etats gardent leur pli (colonne "pli perdu").

**Constats hors critere, sans jugement.** (1) A 744, sur les cinq jeux de la cote (112 etats par moment), le document est plus haut que 551 apres une erreur dans **34** etats avec la forme contre **111** sans, et a la revelation dans **96** contre **112** (a 720 : 36 contre 111, 97 contre 112) : la barre de defilement du navigateur reste presente a la revelation dans la plupart des etats. C'est la matiere de `PA4`. (2) A la revelation, l'indice, qui reste affiche, est coupe par le pli sur la capture de `phrases-types-ce1` (item 17) : la revelation n'est pas dans la levee (`Q-R2`). (3) Sur capture a 744, `Score : 0` tient sous la bande de l'en-tete (`R3-1`, accordee par `nadia-prof` sous condition regardee au rejeu).

## 7. Ecarts de mesure, dits

- **Valeur transitoire, `GNA` item 5, 744 : 355,8.** Dans le releve apparie, l'item de bonne reponse *"les cheveux longs"* (`Quel groupe de mots est bien écrit ?`, item 5 de `ce1-accord-groupe-nominal`) donne, a 744 et sur le seul bras du sommet, un bas d'indice de **355,8** (`.indice` large de 516 et a gauche 114, au lieu de 444 et 276) dans les six ordres. Les trois reprises (`SANS_REVELATION=1`, 744 et 720, `p2-reprise-gna-1.cand.json` a `-3`) donnent **474** a 744 et a 720 pour cet item, et 400 - 548 sur les 24. **Cause non trouvee.** Le releve garde la valeur mesuree ; elle est au-dessus du pli et ne change aucun compte. Je ne l'ai pas effacee ni remplacee dans le tableau de la section 5 (asterisque).
- **Un releve ecarte, par ma faute.** Pendant que `mesurer-additions-cp.mjs` jouait le jar de base sur le port de debogage 19243, j'ai lance sur ce meme port une reprise de `GNA` : j'avais lu *"additions exit 0"* sur le journal du jar du sommet et cru le premier lot fini. Le pilote s'est connecte au Chrome du script. Je l'ai arrete par PID au bout de quelques secondes ; la lecture du releve a montre 17 anomalies et 14 observations a `innerWidth` 720 (le pilote redimensionne la fenetre). **Ce releve n'est pas verse et n'entre dans aucun chiffre.** Les additions de la base ont ete relancees seules, sur un port libre de tout autre processus : c'est le releve de la section 4.3 (0 anomalie, `innerWidth` 744 sur les 530 observations). Verification faite avant d'ecrire : `innerW`, `innerH`, `clientW` de chaque observation.
- **Un comparateur faux, corrige.** Mon premier `cote-p2.mjs` appariait les couples (item, ordre) par numero d'ordre ; ce numero depend de l'ordre servi de chaque partie, d'ou de faux ecarts entre le jar de base seul et le bras `base` (jusqu'a 12 couples sur 120). Corrige avant de conclure (section 3).

## 8. Reponses aux questions posees par la mission

- **Quel commit, et pourquoi** : `aa9d703c`, parce que `Q-R5` (`nadia-prof`) designe le parent du premier commit de la branche de la forme et que c'est le sommet que `lea-dev` a note (section 1). Le sommet de la mesure est `0c0d2142`.
- **`Q-P4` tenue sur la base mesuree ?** **Non** (section 2) : 10 fichiers sur `aa9d703c`, 31 sur `0c0d2142`, tous des paquets et des tests. D'ou une cote remesuree, pas reprise de `6dbb855d`.
- **Cote complete ?** Elements 1, 2 et 4 mesures en entier sur les deux jars, **element 3 mesure en entier (65 sur 65) sur les deux**. Elle reste limitee par les trois points de la section 9.

## 9. Ce que je n'ai pas mesure

- **`additions-cp` aux largeurs 720, 768, 992 et 1200 et dans les six ordres** : le script de `marc-dev` fixe 744 et l'ordre servi. L'echantillon de la section 6 joue `additions-cp` aux cinq largeurs (trois parties, pas les 65 enonces) : pli perdu 0.
- **Le jar de base joue seul sur l'echantillon de 40 jeux** : son bras `base` est la feuille de la base sur le DOM du sommet. Le jar de base joue seul n'a ete mesure que sur les cinq jeux de la cote et sur `additions-cp`.
- **Les jeux `bandit-manchot` hors des 42 de l'echantillon et des cinq de la cote** : aucun item mesure (102 sur 145 dans le rapport du 2026-10-15, non recompte ; cette passe rejoue les 40 jeux de l'echantillon d'alors et ajoute les deux jeux des lots).
- **La revelation** (critere hors levee, `Q-R2`) : mesuree par le pilote, non agregee ici.
- **Sous 720 px** : la forme ne s'y applique pas (`@media`), rien mesure.
- **Polices autres que celle du poste, appareils reels, tablette de 744 x 551, lecteur d'ecran** : non mesures.
- **Un seul navigateur** (Chrome 155.0.8059.40, sans tete).
- **Rejeu de `nadia-prof`** : sur son jar, a son oeil. Ce fichier ne dit pas si un enfant lit le message a droite du signal.

## 10. Isolation

- Repertoires `s57-p2-ines-design-t` (jar de base, 18543, debogage 19243) et `s57-p2-ines-design-t2` (jar du sommet, 18544, debogage 19244), copies lettre pour lettre de la table de `planning.md`, crees avant la premiere commande. `netstat -ano` filtre sur les quatre ports : aucune ecoute avant lancement.
- Jars : lignes *"Tomcat started on port 18543"* et *"... 18544"* et PID lus dans leur journal (50068, 34904) ; **arretes par PID** (`taskkill /PID`), aucune ecoute sur les quatre ports ensuite. Chrome : profils dans mes deux repertoires, un navigateur par script, PID lus sur la sortie d'erreur du script ; aucun processus a mon nom restant ensuite (controle par ligne de commande contenant le nom de mon repertoire). **Jamais d'arret par nom d'image.** Un navigateur et un script arretes par PID dans l'incident de la section 7.
- Journaux, profils de Chrome, captures, sorties brutes, `items.json`, feuilles de base et du sommet : sous ces deux repertoires, rien ailleurs hors depot. Je n'ai deplace ni supprime aucun fichier qui n'est pas a moi.
- **Racine du scratchpad et repertoire parent des worktrees : listes en fin de session, voir le message de rendu.**

## 11. Reproduction

Prerequis : un JDK 21 declare dans `~/.m2/toolchains.xml`, un Chrome, Node 22 ou plus, Python avec PyYAML pour `extraire-items-p2.py`. Le chemin de Chrome et celui du JDK ne s'ecrivent pas ici.

```
# dans chaque worktree (aa9d703c, 0c0d2142)
bash outils/mvn-jdk21.sh -B clean package -DskipTests
java -jar bootstrap/target/recreo.jar --server.port=18543        # base ; 18544 pour le sommet
curl -s http://127.0.0.1:18543/css/recreo.css -o recreo-base.css
python squad/conformite/releves-s57/extraire-items-p2.py . items.json bandit-manchot-phrases-types-ce1,bandit-manchot-accord-groupe-nominal-ce1,bandit-manchot-verbe-sujet-ce1,bandit-manchot-texte-narratif-ce1,bandit-manchot-comprehension-cm1
node squad/conformite/releves-s57/pilote-p2.mjs <chrome> <profil> http://127.0.0.1:18543 <sortie-base> - 744,720,768,992,1200 <port-debogage> items.json <jeux>
node squad/conformite/releves-s57/pilote-p2.mjs <chrome> <profil> http://127.0.0.1:18544 <sortie-sommet> recreo-base.css 744,720,768,992,1200 <port-debogage> items.json <jeux>
node squad/conformite/releves-s57/mesurer-additions-cp.mjs <chrome> 18543 <port-debogage> <repertoire>      # puis 18544
node squad/conformite/releves-s57/pilote-p2-echantillon.mjs <chrome> <profil> http://127.0.0.1:18544 <sortie.json> recreo-base.css 720,744,768,992,1200 <port-debogage> <jeux>
node squad/conformite/releves-s57/compacter-r3.mjs <sortie.json> <compacte.json> 744,720,768,992,1200 --sans-echec2
node squad/conformite/releves-s57/cote-p2.mjs   p2-jar-de-base.propre.json p2-sommet-apparie.base.json p2-sommet-apparie.cand.json items.json 744,720,768,992,1200
node squad/conformite/releves-s57/elements-p2.mjs p2-jar-de-base.propre.json p2-sommet-apparie.cand.json items.json 744,720,768,992,1200
node squad/conformite/releves-s57/couples-p2.mjs  p2-jar-de-base.propre.json p2-sommet-apparie.base.json p2-sommet-apparie.cand.json 744,720,768,992,1200
node squad/conformite/releves-s57/totaux-p2.mjs   p2-echantillon.json bandit-manchot-pronoms-ce1,bandit-manchot-familles-de-mots-ce1
```

Le jar d'un meme commit n'est pas reproductible a l'octet (horodatages dans l'archive) : le SHA-256 de la section 1 identifie les jars joues, pas un rebuild.

## 12. Ce que ce fichier ne fait pas

- Ne prononce aucun Done, ne leve aucune reserve (`S57-TPH-CE1-R1`, `S57-GNA-CE1-R1`, `S57-VSJ-CE1-R2`, `S57-RCT-CE1-R1`), ne repond pas a `Q-P4` a la place de `nadia-prof`.
- Ne modifie ni code, ni gabarit, ni feuille, ni contenu, ni catalogue, ni matrice, ni `ci.yml`. Les seuls fichiers ajoutes sont des outils de mesure (`pilote-p2.mjs`, `pilote-p2-echantillon.mjs`, `cote-p2.mjs`, `elements-p2.mjs`, `couples-p2.mjs`, `totaux-p2.mjs`, `tableau-p2.mjs`, `tableau-additions-p2.mjs`, `extraire-items-p2.py`), des releves `p2-*` et ce fichier.
- N'ecrit et n'alloue aucun identifiant de condition ou de reserve : `CF1` a `CF11` sont des reperes de ce fichier.

## 13. Jobs de `ci.yml` lances pour ce commit

Sur ce worktree, les fichiers du commit dans l'index, avant commit. Je ne deduis aucun job que je n'ai pas lance.

- **`chemins`** : `verifier-chemins-test.sh` sort 0 (*"Tous les cas passent : le garde-fou de chemins mord, et sur le bon motif."*) ; `git ls-files` (moins le test du garde-fou), ces fichiers compris, passe a `verifier-chemins.sh` : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*, sortie 0.
- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh` et `verifier-garde-fou-accent-test.sh` sortent 0 ; le sujet du commit passe dans `verifier-prefixes.sh` : *"Tous les commits portent un prefixe d'agent connu."*, sortie 0. Le controle du proprietaire du garde-fou de l'accent n'est pas lance : aucun de ses chemins n'est touche.
- **`build`, partiel** : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test` restreint a `AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest`, `ConcordanceDesTagsDAgentTest`, `ToutesLesReferencesDeDecisionResolventTest` et `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`, perimetre `bootstrap` et ses amonts (`-am`) : `BUILD SUCCESS`, 19 tests (5, 1, 3 et 10), 0 echec. Lance parce que ces tests lisent `squad/`. **Ce n'est pas `mvn verify`** : le reacteur complet n'est pas lance, parce que ce commit ne touche ni code ni contenu servi ; l'etape *"Package du fat jar"* n'est pas lancee comme telle, mais les deux jars ont ete construits par `clean package -DskipTests` (tests sautes) pour la mesure, sur des arbres dont d'autres ont lance `clean verify` (`lea-dev` sur `dc6f423d`, `sofia-sm` sur les fusions). Le build a lu ce fichier avant l'ajout de cette section, qui ne contient aucun tableau.
- **`outils`** : non lance ; aucun fichier de `outils/` n'est modifie. **Non garde** : les huit scripts `.mjs` et le script `.py` ajoutes sous `squad/conformite/releves-s57/` n'ont pas de juge et ne tombent sous aucun des trois balayages du job (leur seule preuve est ce fichier et le releve que chacun rend) ; le premier jet de `cote-p2.mjs` etait faux (section 7).

Aucun de ces resultats n'est celui de la CI distante, que je n'ai pas lue. Rien n'est pousse.
