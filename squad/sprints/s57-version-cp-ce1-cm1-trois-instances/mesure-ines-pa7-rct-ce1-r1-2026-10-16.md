# s57 - palier 2 : mesure de PA7, les trois items de RCT-CE1-R1 sous les textes de nadia-prof et sous le bandeau

- **Auteure** : `ines-design`. **Date** : 2026-10-16, 16:13 (UTC+02:00), lue par `date`.
- **Arbre** : branche `s57-ines-p2-pa78`, issue de `round-two` a `e8d14ddb`, worktree dedie. Le code servi est celui de `0c0d2142` : `git diff --stat 0c0d2142 e8d14ddb -- pom.xml domain application adapters bootstrap` ne rend aucune ligne.
- **Ce que ce fichier est** : la mesure que `Q-P5` (`avis-theo-palier-2.md`, section 3.2) et la condition de `reponse-nadia-q-palier-2.md` (section 3.4) demandent. Il compte des pixels. Il ne prononce aucun Done et ne choisit pas entre les deux voies : le veto est a `nadia-prof`. Aucun fichier de `domain`, `application`, `adapters/*/src/main`, `bootstrap`, aucun paquet, aucun catalogue n'est touche.

## Resume

| Code | Constat | Section |
|---|---|---|
| PA7-1 | Cote de reference reproduite sur le jar de `e8d14ddb` : items 13, 17 et 18 de `ce1-texte-narratif` a 552 ; l'item 18 dans les six ordres, 13 et 17 dans 2 ordres sur 6 chacun | 2 |
| PA7-2 | **Textes de `nadia-prof`** : les trois items passent a **deux rangees de propositions dans les six ordres**, bas de `.indice` a **478** (marge de 73 px sur 551), a 720, 744 et 768. **120 couples (item, ordre) sur 120 au plus 551** ; maximum du paquet 508 (items 5, 6 et 9, que les textes ne touchent pas) | 3 |
| PA7-3 | Hors police du poste (neuf polices imposees, deux tirages d'ordre par item et par largeur) : l'item 18 a 744 passe sous 551 dans 6 polices sur 9 (1 sur 9 avant les textes) ; Georgia (552), Verdana et Courier New (582) restent au-dessus, et un tirage a 720 laisse 5 polices sur 9 | 4 |
| PA7-4 | **Voie du bandeau** (1,75 rem, 4 px) : bas de `.indice` a **548** pour les trois items, marge de 3 px, 120 couples sur 120 au plus 551, a 720, 744 et 768. Le bas du signal reste a 574 (578 sans le bandeau) | 5 |
| PA7-5 | Les deux voies ensemble n'ont pas ete mesurees | 6 |

## 1. Arbre, jars, methode

| | Jar de reference | Jar aux textes de `nadia-prof` |
|---|---|---|
| Ce que c'est | `bootstrap/target/recreo.jar` de `e8d14ddb`, `bash outils/mvn-jdk21.sh -B clean package -DskipTests`, copie hors depot | copie du jar de reference dont le seul `contenu/ce1-texte-narratif.yaml` est remplace, hors depot, par `fabriquer-jar-textes-pa7.py` (cinq remplacements de chaine, chacun verifie unique) |
| SHA-256 | `e6da8401b754877e89937e4d9b82c5f2bccb5008acaa03c44fcf87d60873611e` | `b8aac237b66efd6e21363d1b6db59143213c976ceef76da563c8eb586caf8898` |
| Feuille servie, SHA-256 sans retours chariot | `c312cd1b2ebbfc4a52508b250f1b328943e1e3a6384dba95c3897a59045f3ef6` | idem (la meme que `git show e8d14ddb:adapters/adapter-web/src/main/resources/static/css/recreo.css`) |
| Lancement | JDK 21.0.10 (ligne `Starting RecreoApplication` du journal), port 18543, PID 33548 lu dans le journal | port 18544, PID 44948 lu dans le journal |

Les jars ne sont pas reproductibles octet pour octet : le SHA-256 du jar de reference n'est pas celui des jars de la cote du matin (`61874630...`), la feuille servie l'est (meme hash que le sommet `0c0d2142`).

**Textes remplaces** (`reponse-nadia-q-palier-2.md` 3.4, numeros comptes depuis 0 ; enonces, textes support et indices inchanges) :

| Item | Bonne reponse | Distracteurs |
|---|---|---|
| 13, Ines | `saute dans une flaque` | `enfile ses bottes`, `sort dehors` |
| 17, Ibrahim | `s'assoit près de la fenêtre` | `attend le bus`, `monte` |
| 18, Chloe | `une fleur pousse` (inchangee) | `Chloé plante une graine`, `elle l'arrose` |

**Methode** : celle de `cote-de-reference-forme-commune-2026-10-16.md`, section 3 : fenetre de 551 de haut aux largeurs 744, 720, 768, 992 et 1200 (`mobile: false`, facteur 1), Chrome 155.0.8059.40 sans tete, Node v24.12.0. `pilote-p2.mjs` sans feuille de base (`-`) joue chaque item des 20 : une erreur (la premiere proposition fausse servie), puis mesure sur le meme DOM, **dans les six ordres** des trois propositions (les boutons sont ranges dans le DOM, mesures, remis), `getBoundingClientRect().bottom + scrollY` du premier `.indice`. Rangees = nombre de valeurs distinctes de `top` des propositions. Les deux jars sont joues par la meme commande, chacun avec son `items.json` (`extraire-items-p2.py` sur le worktree ; le second est le premier avec les trois remplacements). Resumes par `resume-pa7.mjs` et `bas-pa7.mjs`. Releves : `pa7-jar-de-reference.propre.json`, `pa7-textes-de-nadia.propre.json`, dans `squad/conformite/releves-s57/`.

## 2. Reference (textes du depot)

| Largeur | Item | Rangees | Ordres a 3 rangees | Bas de `.indice` | Marge a 551 |
|---|---|---|---|---|---|
| 720, 744, 768 | 13 | 2 ou 3 | 2 sur 6 | 478 ou 552 | -1 |
| 720, 744, 768 | 17 | 2 ou 3 | 2 sur 6 | 478 ou 552 | -1 |
| 720, 744, 768 | 18 | 3 | 6 sur 6 | 552 | -1 |
| 992 | 13, 17, 18 | 2 | 0 | 478 | 73 |
| 1200 | 13 et 17 ; 18 | 1 ; 2 | 0 | 404 ; 478 | 147 ; 73 |

20 items, 120 couples (item, ordre) par largeur : **110 sur 120 au plus 551** a 720, 744 et 768 ; les dix autres sont les couples ci-dessus a 552. Meme constat que `CF9` de la cote du matin (mesure faite sur un autre jar, meme arbre de code pour ces gabarits et ce paquet).

## 3. Textes de nadia-prof

| Largeur | Item | Rangees | Ordres a 3 rangees | Bas de `.indice` | Marge a 551 | Gain |
|---|---|---|---|---|---|---|
| 720, 744, 768 | 13 | 2 | 0 sur 6 | 478 (6 ordres sur 6) | 73 | 0 ou 74 px selon l'ordre |
| 720, 744, 768 | 17 | 2 | 0 sur 6 | 478 (6 ordres sur 6) | 73 | 0 ou 74 px selon l'ordre |
| 720, 744, 768 | 18 | 2 | 0 sur 6 | 478 (6 ordres sur 6) | 73 | 74 px dans les six ordres |
| 992 | 13 et 17 ; 18 | 1 ; 2 | 0 | 404 ; 478 | 147 ; 73 | 74 px pour 13 et 17 |
| 1200 | 13, 17, 18 | 1 | 0 | 404 | 147 | 74 px pour l'item 18 |

- **Au plus 551 sur les 20 items** : 120 couples sur 120 a 720, 744 et 768, 120 sur 120 a 992 et 1200. Maximum a 720, 744 et 768 : **508**, sur les items 5, 6 et 9, dont les textes n'ont pas change (ils valaient 508 dans la reference, section 2).
- **Bas du signal** apres une erreur, items 13, 17 et 18 : 578 dans la reference, **504** avec les textes ; maximum des 20 items : 534. Bas du document : 610 puis 566.
- **Ce que la mise en page donne** : dans l'ordre servi a 744 pour l'item 13, `saute dans une flaque` occupe une rangee (680 px), `sort dehors` (296,2 px) et `enfile ses bottes` (367,8 px) occupent l'autre. Ces largeurs sont celles des cases etirees (`flex: 1 1 auto`) : 296,2 + 16 + 367,8 = 680, la largeur du conteneur. **Je n'ai pas mesure les largeurs naturelles**, donc je ne chiffre pas la marge avant une troisieme rangee.
- **Reponse a `Q-P5`** : sur la police du poste, a 720, 744 et 768, apres une erreur, dans les six ordres, **oui** : les textes de `nadia-prof` font tenir les trois items sur deux rangees et sous 551, avec 73 px de marge.

## 4. Textes de nadia-prof, hors police du poste

`polices-r3.mjs` (neuf polices imposees au `<body>`, qui ne remplacent pas un appareil reel), item reconnu par une proposition, premiere proposition fausse pressee, **deux tirages par item et par largeur** (le serveur tire l'ordre, on ne le choisit pas : les six ordres ne sont pas couverts ici). Bas de `.indice`, les deux tirages separes par `;` quand ils different. Releves : `polices-pa7/`, resume `resume-polices-pa7.mjs`.

| Police | 13 a 744 | 13 a 720 | 17 a 744 | 17 a 720 | 18 a 744 | 18 a 720 |
|---|---|---|---|---|---|---|
| poste | 478 | 478 | 478 | 478 | 478 | 478 |
| Arial | 478 | 478 | 478 | 478 | 478 | 478 |
| Georgia | 478 | 478 | 478 | 478 | 552 | 478 ; 552 |
| Times New Roman | 478 | 478 | 478 | 478 | 478 | 478 |
| Verdana | 508 | 508 ; 582 | 508 | 508 | 582 | 508 ; 582 |
| Courier New | 508 | 508 ; 582 | 582 ; 508 | 508 | 582 | 508 ; 582 |
| Comic Sans MS | 478 | 478 | 508 | 508 | 508 | 508 |
| Trebuchet MS | 478 | 478 | 478 | 508 | 478 | 508 |
| Tahoma | 478 | 478 | 478 | 478 | 478 | 478 ; 552 |
| Polices au plus 551 (tirage 1 ; tirage 2) | 9 ; 9 | 9 ; 7 | 8 ; 9 | 9 ; 9 | 6 ; 6 | 9 ; 5 |

**Terme de comparaison** : `r3-polices-rct.json` (jar a la forme commune, textes du depot, item 18, 744, un tirage) : poste 552, Arial 552, Georgia 552, Times New Roman 478, Verdana 582, Courier New 582, Comic Sans MS 582, Trebuchet 552, Tahoma 552, soit **1 police sur 9** au plus 551. Avec les textes de `nadia-prof`, a 744 : **6 sur 9** (poste, Arial, Times New Roman, Trebuchet et Tahoma a 478, Comic Sans MS a 508).

**Ce que le tableau dit** : les textes ne tiennent pas sous 551 dans toutes les polices. L'item 18 reste au-dessus dans Georgia (552), Verdana et Courier New (582) ; les items 13 et 17 y passent au-dessus dans Verdana et Courier New a 720 (13) et dans Courier New a 744 (17), pour un des deux tirages. Par arithmetique sur ces cotes (non mesure), le bandeau de la section 5 ramenerait 552 a 548 et laisserait 582 a 578.

## 5. Voie du bandeau (theo-archi, 1,75 rem)

`candidat-r3-option-bandeau.css` (une regle : `.cabinet-bandeau { height: 1.75rem }` sous `@media (min-width: 720px)`, hors `.ecran-deux-oeuvres`), inseree apres la feuille servie par le jar de reference, mesure appariee sur le meme DOM par `pilote-r3.mjs` (feuille servie seule, puis avec la regle), 720, 744 et 768, six ordres, 20 items. Releves : `pa7-bandeau.base.json` (sans la regle) et `pa7-bandeau.cand.json` (avec). Rien n'est modifie dans le depot : la regle est injectee a l'execution.

| Largeur | Item | Rangees | Ordres a 3 rangees | Bas de `.indice` sans, puis avec | Marge a 551 |
|---|---|---|---|---|---|
| 720, 744, 768 | 13 | 2 ou 3 | 2 sur 6 | 478 ou 552, puis 474 ou 548 | 3 |
| 720, 744, 768 | 17 | 2 ou 3 | 2 sur 6 | 478 ou 552, puis 474 ou 548 | 3 |
| 720, 744, 768 | 18 | 3 | 6 sur 6 | 552, puis 548 | 3 |

- Bandeau : 32 px de haut sans la regle, 28 avec ; `.cabinet` 278 puis 274. **120 couples sur 120 au plus 551** avec la regle, maximum 548, aux trois largeurs (110 sur 120 sans).
- **Le bas du signal des trois items passe de 578 a 574** : au-dessus de 551 dans les deux cas. Les trois items gardent trois rangees.
- La regle agit sur le bandeau des jeux a propositions ecrites (hors `.ecran-deux-oeuvres`). **Je ne l'ai mesuree que sur `ce1-texte-narratif`** : ni la clause de non-regression, ni un echantillon d'autres jeux (le releve que `Q-T5` point 3 demande a `nadia-prof`).

## 6. Ce que je n'ai pas mesure

- Les textes **et** le bandeau ensemble, et le bandeau sous les neuf polices : non mesures (arithmetique en section 4, pas de mesure).
- Un rendu sous 720 px (la forme commune ne s'y applique pas, `@media (min-width: 720px)`).
- Les largeurs naturelles des propositions (marge avant une troisieme rangee), un navigateur autre que Chrome 155, un appareil de la cible.
- Les tests de contenu du paquet (`LON-CE1-3`, `-4`, `-5`) sur les textes de `nadia-prof` : le jar joue un contenu modifie hors depot, le paquet du depot n'est pas change et aucun test n'a ete lance sur le contenu modifie.
- Aucune valeur transitoire : la cote de reference (552, trois items) rendue par deux mesures independantes (cette mesure et celle de `cote-de-reference-forme-commune-2026-10-16.md`) est identique, et le releve de la mesure appariee du bandeau rend les memes valeurs de base (552, 110 sur 120) que le jar de reference joue seul.

## 7. Isolation

Jars et Chrome joues sous les repertoires de scratchpad de `ines-design` (ports 18543 et 19243 pour la reference et la voie du bandeau, 18544 et 19244 pour les textes) ; les deux jars sont arretes par PID ; aucun processus Chrome a mon nom ne reste (controle par ligne de commande). Aucun fichier d'un tiers deplace ou supprime.

## Decisions prises

**Aucune.** Ce fichier ne cree ni n'amende aucune `ADR` ni `DEC`, ne leve aucune reserve et ne prononce aucun Done.
