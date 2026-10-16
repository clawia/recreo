# s57, palier 2, instance A' - audit de `nadia-prof` : lot p2-4, `ce1-familles-de-mots` (`FRA-CE1-24`)

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : 2026-10-16, 16:05 (UTC+02:00), lu par `date`.
- **Arbre** : branche `s57-nadia-p2-ap`, partie de `round-two` a `e8d14ddb`, plus mon commit `4097ac13` (rejeux p2-8 et p2-9) et
  la fusion de `s57-camille-p2-fiches` (`610bcba1`, deux fiches, aucun code) par `43e05a0e`. Code servi : celui de `e8d14ddb`
  (`git diff --quiet e8d14ddb HEAD -- adapters domain application bootstrap pom.xml outils` sort 0), qui contient la fusion du
  lot `9f110983` (`git merge-base --is-ancestor 9f110983 e8d14ddb` sort 0).
- **Type** : audit d'un lot neuf, joue. Fiche : `squad/conformite/FICHE-bandit-manchot-familles-de-mots-ce1.md`.

## Verdict

**VALIDE.** Done pedagogique de `FRA-CE1-24` prononce, sans reserve. `camille-po` peut passer la ligne a `couvert-valide`.

| Point | Valeur | Section |
|---|---|---|
| Item 12, *chat* / *chaton* (`FM5`, `Q-S8`) | mon texte, servi tel quel ; famille juste, distracteurs dans l'ordre | 2.1 |
| Les 14 indices de famille (`FM6`, `Q-B2`) | departagent apres chacune des deux erreurs, sur les 14 | 2.2 |
| Les 10 items d'affixe | justes ; aucun nom d'affixe lu ; intrus du premier mot compte, pas une reserve | 2.3 |
| Position de l'indice a 744 x 551 | **24 sur 24** au-dessus du pli, six ordres, bas le plus haut 518 | 3 |
| Titre, consigne, place au catalogue (`sofia-sm`) | **tenus** | 4 |
| Refus ecrits d'avance (`arbitrage-nadia-palier-2.md` 6.2) | *librairie* absent ; aucune famille savante | 2.1, 2.2 |

**Prix** : 1,0 (lot neuf, bareme), sur A'.

## 1. Ce que j'ai lance

| Mesure | Commande ou geste | Rendu |
|---|---|---|
| Jar | celui de mes rejeux p2-8 et p2-9, non reconstruit | SHA-256 `db419e94b685ba3d37591b853bfd7decdf9fdf55e8516defe0f7b5e9785c48d7` (lu de nouveau) |
| Lancement | `java -jar bootstrap/target/recreo.jar --server.port=18512`, ports 18512 et 19212 libres avant (`netstat -ano`) | *"Tomcat started on port 18512"*, PID 48168 lu dans le journal ; arrete par ce PID ; aucune ecoute ensuite |
| Navigateur | Chrome 155.0.8059.40 sans tete, profil dans mon dossier de poste, port 19212 | arrete par son PID ; 0 processus dont la ligne de commande nomme mon dossier |
| Parties | par le bouton *"Les familles de mots"* de `/niveaux/CE1/matieres/francais`, comme un enfant | 9 parties, **24 items sur 24**, 106 erreurs, 24 revelations, 32 bonnes reponses au premier coup, **0 ecart au fichier** |
| Item 12 au fichier | `awk '/^  - enonce:/{n++} n==12'` sur le paquet | les quatre lignes de `reponse-nadia-q-palier-2.md` 3.6 |
| *librairie* | `grep -c 'bonneReponse: "librairie"'` | 0 |
| Noms d'affixe | `grep` de *préfixe*, *prefixe*, *suffixe*, *radical*, *affixe* sur les lignes `enonce`, `bonneReponse`, `distracteurs`, `indice` | 0 ; sur le HTML servi de l'accueil et d'une manche : seulement dans deux commentaires HTML du gabarit, jamais affiches |

**Methode du jeu** : premiere erreur, seconde erreur, troisieme appui sur une proposition barree (revelation), puis la bonne
reponse ; sur un item deja vu, la bonne au premier coup une manche sur deux, sinon une erreur puis la bonne. *Ecart* : la meme
definition que `rejeu-nadia-palier-2-ap.md`, section 1. Mesure de `.indice` par `getBoundingClientRect()` apres la premiere
erreur, a 720, 744 et 768 x 551, ordre servi et six ordres. 24 captures a 744 apres la premiere erreur ; j'en ai regarde trois
(*chat*, *décoller*, le plus bas, et une revelation, *peur*).

**Lecture complete** : la ligne datee *"lecture complete : `ce1-familles-de-mots`, 24 items lus"* est dans
`reponse-nadia-q-palier-2.md` section 1 (`c62e0ad8`, ancetre de ce commit). Elle lit la version de `29d02bc6` ; depuis, l'item
*livre* et 13 autres indices de famille ont change (`FM5`, `FM6`). **Les 24 items, dont tous ceux qui ont change, sont relus au
jeu ici**, comme je l'avais ecrit.

## 2. Contenu, joue

### 2.1 L'item 12 : *chat*

Servi : *"Quel mot est de la même famille que « chat » ?"*, propositions *chaton*, *château*, *souris*, indice *"Le bon mot garde
un morceau et parle de la même chose."* L'enonce passe sur deux lignes a 744, *« chat » ?* reste groupe sur la seconde.
**Juste** : *chaton* est le petit du chat, famille transparente. *château* est une fausse famille de lettres (a), *souris* un mot
du meme theme sans le radical (b). *librairie* n'est plus attendu nulle part.

### 2.2 Les 14 items de famille et leur indice unique

| Mot de depart | Bonne reponse | (a) fausse famille | (b) meme theme |
|---|---|---|---|
| dent | dentiste | danse | bouche |
| lait | laitier | laid | fromage |
| fleur | fleuriste | fleuve | jardin |
| terre | terrain | terrible | planète |
| danse | danseur | dense | musique |
| chant | chanter | champ | oiseau |
| savon | savonner | savoir | mousse |
| chat | chaton | château | souris |
| peur | peureux | peu | cauchemar |
| porte | portail | portrait | fenêtre |
| pomme | pommier | pompe | poire |
| école | écolier | écoute | classe |
| nuage | nuageux | nage | pluie |
| sable | sablier | table | plage |

**Familles exactes et non savantes** (`FAM-CE1-2`, `FM4`) : les 14 bonnes reponses sont des mots derives du mot de depart, qu'un
enfant de CE1 reconnait par le sens et la forme. Aucune n'est savante. **Ordre des distracteurs** : (a) toujours de meme son ou
de memes lettres sans le sens, (b) toujours du meme theme sans le radical, sur les 14. *dense* est un mot peu connu en CE1 ; il
est la par le son, ce que (a) demande, et ne se choisit pas par le sens. Ce n'est pas un motif.

**L'indice departage apres chacune des deux erreurs**, ce que j'avais promis de juger (`reponse-nadia-q-palier-2.md` 3.7), en
commettant les deux erreurs sur chaque item. Apres une erreur sur (a), il reste la bonne reponse et (b) : *"garde un morceau"* les
separe (*souris* ne garde rien de *chat*). Apres une erreur sur (b), il reste la bonne reponse et (a) : *"parle de la même chose"*
les separe (*château* ne parle pas du chat). Vrai sur les 14 lignes du tableau. L'indice ne contient aucune proposition et ne dit
rien de faux (`TRV-CE1-5`).

**Observation sans reserve** : a 744, l'indice passe sur deux lignes, *"... et parle de la"* / *"même chose."* La coupure tombe
dans le groupe *la même chose*, pas dans un mot. Un enfant de CE1 lit deux lignes. Ce n'est pas un motif.

### 2.3 Les 10 items d'affixe

| Mot | Bonne reponse | Distracteurs |
|---|---|---|
| refaire | faire encore | ne jamais faire ; faire très vite |
| relire | lire de nouveau | ne pas lire ; lire à voix haute |
| défaire | le contraire de faire | faire une deuxième fois ; faire plus vite |
| décoller | le contraire de coller | coller une deuxième fois ; coller très fort |
| découvrir | le contraire de couvrir | couvrir une deuxième fois ; couvrir très vite |
| chanteur | celui qui chante | celui qui écoute ; la chanson |
| nageur | celui qui nage | celui qui plonge ; l'eau de la piscine |
| cerisier | l'arbre à cerises | la tarte aux cerises ; un jus de cerise |
| maisonnette | une petite maison | une grande maison ; une vieille maison |
| fillette | une petite fille | une grande fille ; une fille très sage |

**Exactitude** : les 10 bonnes reponses sont justes ; *découvrir* est pris dans son sens d'*enlever ce qui couvre*, seul sens
propose, reponse unique (deja juge a la lecture). Le distracteur *"... une deuxième fois"* des trois items en *dé-* est le sens de
*re-* : il fait distinguer les deux. **Aucun nom d'affixe** n'est lu (section 1) ; chaque item demande ce que le mot veut dire
(`FAM-CE1-3`).

**Intrus du premier mot, compte et juge** (`arbitrage-nadia-palier-2.md` 6.1, point 3). Sur les trois items en *dé-*, la bonne
reponse est la seule proposition qui ne commence pas par le verbe (*le contraire de...*). Sur *refaire* et *relire*, l'intrus est
un distracteur (*ne jamais faire*, *ne pas lire*) ; sur les cinq autres, aucune proposition ne se distingue par son premier mot.
**L'intrus n'est donc pas une strategie payante dans le jeu** : il donne la bonne reponse 3 fois et une mauvaise 2 fois. Sur les
items en *dé-*, la forme *le contraire de* est aussi exactement ce que la ligne enseigne. Pas une reserve.

**Majuscule** : aucune proposition ne porte de majuscule. **Chevrons** : chaque mot cite dans un enonce est entre chevrons ;
aucun indice ne cite de mot.

**Observation sans reserve, et je ne releve pas ma barre** : apres une erreur sur un item d'affixe, les deux propositions qui
restent contiennent souvent le meme verbe (*faire encore* et *ne jamais faire*). L'indice *"Reconnais-tu un mot connu à la fin du
mot ?"* fait reperer *faire* dans *refaire* sans dire ce que *re-* change : il dit ou regarder, ce que mon critere demande, et
l'enfant avance par la revelation. Je l'avais lu sans le relever le 2026-10-16 (`reponse-nadia-q-palier-2.md` 1.2) ; je l'ecris
ici pour `camille-po`, comme recommandation, pas comme reserve.

## 3. La position de l'indice : mesuree par moi

Le paquet n'est pas dans la cote d'`ines-design` (le fichier la nomme comme une difference entre ses deux jars et ne mesure pas
ses items).

| Largeur | `.indice` au plus 551, ordre servi | Six ordres | Bas le plus haut |
|---|---|---|---|
| 720 | 24 / 24 | 24 / 24 | 518 |
| 744 | **24 / 24** | 24 / 24 | 518 |
| 768 | 24 / 24 | 24 / 24 | 518 |

Les mots de famille tiennent sur une rangee (bas de l'indice 400 a 448, dans les six ordres). Les items d'affixe sur deux rangees
finissent a 444 ; *décoller* et *découvrir* passent a trois rangees dans deux ordres sur six chacun (*décoller* dans l'ordre servi
de ma partie), et finissent alors a 518. Verdict *"Ce n'est pas ça."* / *"Essaie encore !"*, coupe entre les deux phrases sur les
24. Score : aucune lettre couverte par l'en-tete (`elementFromPoint`, 15 points sur 15, a 720, 744 et 768). A 720, aucun libelle ne
change de nombre de lignes a la revelation (72 libelles). Aucun debordement a droite. **A la revelation, hors levee** (`Q-R2`) : la
reponse est visible sur les 24 (bas au plus 550) ; l'indice reste au-dessus du pli sur 23.

**Non mesure** : 992 et 1200 de large ; sous 720 (la forme ne s'y applique pas) ; d'autres polices ; un appareil reel ; un lecteur
d'ecran ; un autre navigateur.

## 4. Les textes de `sofia-sm`

- **Titre *"Les familles de mots"*** : **tenu.** Quatre mots, le nom de la lecon de classe ; *famille* est deja dans 14 enonces.
  Il annonce 14 items sur 24 ; les 10 items d'affixe sont la seconde moitie de la meme ligne (*regrouper les mots d'une même
  famille et repérer un préfixe ou un suffixe*), et l'enfant qui y arrive sous ce titre n'est pas trompe sur ce qu'on lui demande.
  Meme raisonnement que pour *"Les types de phrases"*. Aucun niveau dans le titre (`ADR-0078`).
- **Consigne *"Lis bien, puis choisis la bonne réponse."*** : **tenue.** Chaque enonce porte sa question ; la consigne generique
  ne la contredit pas et ne la redouble pas, comme pour `-13`, `-10`, `-15`.
- **Place au catalogue** : **tenue.** Servie quinzieme des 19 jeux de Francais CE1, juste apres *"Même sens ou sens contraire"*.
  C'est la voisine de theme (le vocabulaire), et *défaire* y prolonge les contraires.

## 5. Ce dont je reste la seule garde

| Propriete | Pourquoi aucun test ne la tient |
|---|---|
| Familles exactes et non savantes, ordre (a) puis (b) | un test voit un mot, pas son etymologie ni son theme |
| Que l'indice unique departage apres chacune des deux erreurs | un test lit un indice, pas ce qu'il laisse apres une erreur donnee |
| Le texte de l'item 12 | aucun test Java : la mutation *chatte* reste verte (`rapport-qualite-lot-24.md`, `E3`) ; seules `FM5-2` et `FM5-3` le gardent, par commande |
| La position de `.indice` sur ce jeu | aucun automate du reacteur ne rend une page |
| `nombreDeManches: 10` | non garde par un test ; constate au jeu : 10 manches par partie |

## 6. Routage

| A qui | Quoi |
|---|---|
| `camille-po` | `FRA-CE1-24` a `couvert-valide` ; la recommandation de 2.3, a prendre ou non ; `E3` reste a elle ou a `theo-archi` |
| `sofia-sm` | ce verdict pour la cloture du palier 2 ; prix 1,0 sur A' |
| `relecteur-pedagogique` | l'orthotypographie des 24 items servis, dont mes textes |

## 7. Ce que je n'ai pas joue

`ce1-pronoms` (`-05`, instance A), et les largeurs, polices, appareils et navigateurs de la section 3. Je n'ai regarde que trois
captures sur 24 ; les autres sont mesurees, pas regardees.

## 8. Ce que ce document ne fait pas

Il ne modifie ni code, ni paquet, ni catalogue, ni matrice. Il n'ecrit et n'alloue **aucun identifiant de condition ou de
reserve**.

**Signe** : `nadia-prof`, s57, palier 2, instance A', 2026-10-16.

## 9. Jobs de `ci.yml` lances pour ce commit

Sur ce worktree, les deux fichiers du commit dans l'index, avant commit. Je ne deduis aucun job que je n'ai pas lance.

- **`chemins`** : `verifier-chemins-test.sh` sort 0 ; `git ls-files` (moins le test du garde-fou) passe a `verifier-chemins.sh` :
  *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*, sortie 0.
- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh` et `verifier-garde-fou-accent-test.sh` sortent 0 ; le
  sujet du commit passe dans `verifier-prefixes.sh` : *"Tous les commits portent un prefixe d'agent connu."*, sortie 0.
- **`build`, partiel** : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test` restreint a
  `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`, `AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest`,
  `ToutesLesReferencesDeDecisionResolventTest` et `ConcordanceDesTagsDAgentTest`, perimetre `bootstrap` et ses amonts :
  `BUILD SUCCESS`, 19 tests (10, 5, 3 et 1), 0 echec. Aucun identifiant ecrit ni alloue. Le reste du `build` n'est pas lance : ce
  commit ne touche ni code ni contenu servi. Le build a lu ce fichier avant l'ajout de cette section, qui ne contient aucun tableau.
- **`outils`** : non lance ; aucun outil n'est modifie.

Aucun de ces resultats n'est celui de la CI distante, que je n'ai pas lue. Rien n'est pousse.
