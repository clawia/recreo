# Fiche de conformite - bandit-manchot-conjugaison-present-cm1

- **Mecanique** : bandit manchot (mecanique generique, partagee avec
  `bandit-manchot-additions-cp`, `bandit-manchot-homophones-cm1` et
  `bandit-manchot-le-bon-son-cp`).
- **Contenu** : paquet a creer sous `adapters/adapter-content/src/main/resources/contenu/`
  (nom de fichier laisse a `theo-archi` / aux developpeurs, par exemple
  `cm1-conjugaison-present.yaml`), `type: question`. Matiere premiere issue de `contenu-repris/
  textes-trous/cm1-conjugaison.yml`, 6 items a l'etat de reserve, a completer et a repasser devant
  `nadia-prof` comme un contenu neuf (`contenu-repris/README.md`, `CLAUDE.md`).
- **Contenu livre et audite** :
  `adapters/adapter-content/src/main/resources/contenu/cm1-conjugaison-present.yaml`, 12 items,
  `type: question`, chacun porteur d'un indice. Entree de catalogue
  `bandit-manchot-conjugaison-present-cm1`, titre "Le bon verbe", 10 manches. Six items repris de
  `contenu-repris/textes-trous/cm1-conjugaison.yml`, six rediges en s06 ; les douze indices sont
  neufs.
- **Statut global** : **REFUSE** (Done pedagogique refuse le 2026-08-10, premier audit). Motif
  unique et suffisant : cinq des douze indices enoncent une regle de conjugaison fausse, et trois
  d'entre elles sont dementies par un autre item du meme paquet.
  **Arbitrage prealable au developpement du 2026-08-10** : la reduction de ce paquet a **trois
  propositions par item est ACCEPTEE**, sur son propre motif - la quatrieme proposition est sous le pli
  a 360 x 640 - avec les douze triplets exacts fixes nommement. **Cette reduction ne fait pas tenir ce
  paquet en colonnes** : mesure sur le rendu, neuf items sur douze debordent encore a trois colonnes, et
  deux y rendent deux propositions indiscernables. Ce n'est pas un Done pedagogique, et le refus des
  cinq indices reste entier : il faut **les deux** corrections. `DEC-0014` n'est pas leve.
  **ETEINTE le meme jour par mon second arbitrage du 2026-08-10** (bloc en fin de fiche) : deux mesures
  posterieures montrent que mon motif visait une cause fausse - le defaut de pli vient de la longueur
  de l'enonce, pas du nombre de propositions, et il frappe aussi un paquet a deux propositions du
  catalogue - et que le repli en deux rangees d'`ines-design` rend le mot de la quatrieme proposition
  entierement lisible. **Les douze triplets ne sont plus applicables, le paquet garde ses quatre
  propositions, aucun distracteur n'est retire.** Ils restent ecrits comme trace, jamais effaces. La
  seule correction attendue sur ce paquet reste celle des cinq indices fautifs.
  **Second audit, increment livre en s07 (commit `10dd74d`) : VALIDE SOUS RESERVE, Done pedagogique
  accorde le 2026-08-10. Le REFUSE du 2026-08-10 est LEVE** : les douze indices sont desormais vrais et
  aucun ne dementit un autre item, verifie un par un et vu en jeu. Le refus n'est pas efface, il reste
  lisible plus bas avec son motif. Jeu joue sur une **branche jetable, jamais fusionnee et detruite**,
  qui n'ajoutait que la ligne de catalogue : `DEC-0014` n'est pas leve par mon audit. Le **repli en
  deux rangees est reproduit sur le code livre** et remesure (160 px, ecart de 16 px sur les deux axes,
  aucune troncature, les quatre propositions au-dessus du pli sans l'indice) - c'est la mesure que
  demandait la seconde condition de `camille-po`, qui reste la seule a pouvoir la fermer. Deux
  reserves neuves : `mangeons` tient a **2,0 px** de chaque cote de sa boite, et cinq items sur douze
  mettent la seconde rangee sous le pli quand l'indice s'affiche (`DEC-0016`, pas un motif).
  **Ma reference sur les modeles d'indice etait fautive et je la corrige** : le modele "nommer le
  groupe" n'existait pas dans le paquet, `marc-dev` a eu raison de ne pas l'appliquer.
- **Ligne de matrice** : **reste `en cours`.** Elle ne pouvait de toute facon pas atteindre
  `couvert-valide` sur cet increment (un temps sur trois, `DEC-0013`) ; le Done pedagogique est
  desormais accorde, mais la barre que j'ai ecrite moi-meme pour `couvert-valide` - ouvrir le futur et
  l'imparfait, refus du 2026-08-10 section 5 - est inchangee et non atteinte. **La ligne ne bouge pas
  sur le verdict du second audit.**
- **Arbitrage prealable au developpement du cabinet visible du 2026-08-11** (bloc en fin de fiche,
  arbitrage complet dans `FICHE-bandit-manchot-additions-cp.md`) : cabinet **compatible avec mon veto
  sous six proprietes verifiables**. **Ce jeu est explicitement hors du tableau de planchers de
  largeur** : il n'est pas au catalogue et sa disposition reste une saisine de `camille-po` avec
  `theo-archi`. Ce n'est pas un Done pedagogique, il est degrade, et aucun verdict de cette fiche
  n'est touche.
- **Derniere mise a jour** : 2026-08-11

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| francais | CM1 (cycle 3) | Orthographe et grammaire | Conjuguer les verbes frequents au present, futur et imparfait de l'indicatif - **sur le present uniquement pour ce jeu** | `squad/programme/francais/competences-cm1.md` |

## Criteres pedagogiques attendus - camille-po

- [ ] Chaque manche presente une phrase a trou dont la bonne reponse est un verbe frequent
      conjugue au present de l'indicatif, jamais a un autre temps.
- [ ] La bonne reponse figure exactement une fois parmi les propositions ; chaque distracteur est
      une autre forme conjuguee du meme verbe au present (jamais un verbe different, jamais une
      forme d'un autre temps).
- [ ] Chacune des six personnes grammaticales (je, tu, il/elle, nous, vous, ils/elles) est
      representee par au moins un item du paquet.
- [ ] Le paquet demarre une partie sans erreur avec la mecanique bandit manchot existante et sans
      qu'aucune ligne de `domain/mechanic` ni `domain/mechanics` n'ait ete modifiee pour
      l'accueillir (`ADR-0007`, `CatalogueChargeableIT`).
- [ ] Sur 20 manches jouees a la suite, les phrases proposees ne sont pas toutes identiques.
- [ ] Tout texte destine a l'enfant (consigne, retours, indices s'il en existe) est redige en
      francais correct et integralement accentue, orthographe et ponctuation irreprochables
      (exigence de categorie B ajoutee par l'amendement du 2026-08-04 a `DEC-0002`).
- [ ] Les phrases sont d'un niveau de vocabulaire et de longueur adapte a un eleve de CM1 ; les
      verbes retenus sont effectivement "frequents" au sens du referentiel.
- [ ] L'accueil mentionne le niveau vise (reserve commune deja nommee sur les deux autres jeux de
      francais, `docs/backlog/backlog.md`, "Backlog - sprints suivants"), sauf si `camille-po` la
      traite ailleurs avant l'audit de ce jeu.

Voir `docs/backlog/backlog.md`, story S6-3, pour la formulation complete de ces criteres en
entree/sortie observables.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

### Arbitrage prealable au developpement - cabinet visible de la machine, du 2026-08-11

**Ce bloc n'est pas un Done pedagogique et il est degrade** : le cabinet n'est pas ecrit. Il ne
touche aucun verdict de cette fiche. L'arbitrage complet est ecrit une seule fois, dans
`FICHE-bandit-manchot-additions-cp.md`, bloc du 2026-08-11 - il vaut pour la mecanique, donc pour ce
jeu si un jour il entre au catalogue. Ce qui concerne **ce jeu-ci** :

1. **Je le laisse volontairement hors de mon tableau de planchers de largeur de colonne.** Ce tableau
   chiffre les trois jeux du catalogue ; ce jeu-ci n'y est pas, et sa disposition - quatre
   propositions, repli en deux rangees - reste une saisine de `camille-po` avec `theo-archi`. Je ne
   la rouvre pas et je ne fabrique pas un plancher pour un cas dont la disposition n'est pas arretee.
2. **Ce que je dis quand meme, parce que ca se saura au moment du dessin** : c'est le cas le plus
   contraint du depot. `mangeons` tient a **2,0 px** de chaque cote de sa boite - la marge la plus
   etroite que j'aie mesuree. **Tout cadre lateral paye sur la largeur des colonnes tronque ce mot.**
   Un cabinet et ce paquet en quatre colonnes ne tiennent pas ensemble a 360 px, et il vaut mieux le
   savoir avant qu'apres.
3. **Ma reserve du 2026-08-10 sur le cumul du decalage au-dela de quatre colonnes reste ouverte** et
   reste ma garde. Un cabinet n'y change rien.

### Verdict courant - second audit, increment livre en s07, du 2026-08-10

- **Verdict** : **VALIDE SOUS RESERVE.** Done pedagogique accorde. **Mon refus du 2026-08-10 est
  leve** : son motif unique - cinq indices enoncant une regle de conjugaison fausse - est traite, et
  je l'ai verifie en jouant. Le refus n'est pas efface : il reste lisible plus bas avec sa date et son
  motif, parce qu'un refus efface n'a aucune valeur de preuve.
- **Date** : 2026-08-10.
- **Commit audite** : `10dd74d`, `HEAD` de `round-two`.
- **Moyens** : audit **joue**, non degrade. Meme installation que le verdict transverse
  (`FICHE-bandit-manchot-additions-cp.md`, section Moyens) : worktree dedie hors de l'arbre du depot,
  lignee et JDK verifies par les outils du depot, fat jar reconstruit, lance sur un port dedie,
  `mvn -B verify` vert (315 tests). **Ce jeu est hors catalogue** (`DEC-0014`) : pour le jouer, j'ai
  ouvert une branche jetable qui n'ajoutait que la ligne de catalogue retiree par `6060d32`, reprise
  au caractere depuis l'historique plutot que reecrite. **Cette branche n'a jamais ete fusionnee et
  elle est detruite**, et le fichier `catalogue.yaml` de mon lot est identique a celui de
  `round-two`. C'est le mecanisme qui tient `DEC-0014` a la lettre : le jeu a ete joue sans rejoindre
  le catalogue. **Trois parties completes** de dix manches, ratees a chaque manche pour faire venir
  l'aide, plus un balayage jusqu'a voir les douze items, a 360 x 640.
- **Done technique** : acquis.
- **Ligne de matrice** : **reste `en cours`**, et pas a cause de mes reserves. La barre pour
  `couvert-valide` est celle que j'ai ecrite moi-meme au refus du 2026-08-10, section 5, et elle est
  inchangee : le futur et l'imparfait doivent etre ouverts. Ce paquet couvre un temps sur trois. Je ne
  la deplace ni dans un sens ni dans l'autre. Je n'ai pas edite cette ligne, elle appartient a
  `camille-po`.

#### 1. Le motif du refus est leve, et voici la verification item par item

`marc-dev` a applique la troisieme des formes que j'avais nommees - nommer le verbe - aux cinq
indices fautifs. J'ai relu les **douze** indices du paquet, pas seulement les cinq, et je les ai vus
s'afficher en jeu.

| Item | Indice servi aujourd'hui | Vrai ? | Dementi par un autre item du paquet ? |
|---|---|---|---|
| 1 `va` | "Le verbe 'aller' est irrégulier : avec 'il' ou 'elle', il devient 'va' au présent." | oui | non |
| 2 `lisons` | "Avec 'nous', le verbe se termine **presque toujours** par '-ons'..." | oui, tempere | non |
| 3 `mangez` | "Avec 'vous', le verbe se termine **presque toujours** par '-ez'..." | oui, tempere | non |
| 4 `fais` | "Le verbe 'faire' est irrégulier : avec 'je', il devient 'fais' au présent." | oui | non |
| 5 `jouent` | "Avec 'ils' ou 'elles', **le verbe 'jouer'** se termine par '-ent'..." | oui | non |
| 6 `dessines` | "Avec 'tu', **le verbe 'dessiner'** se termine par '-es'..." | oui | non - l'item 8 parle de `finir`, pas de `dessiner` |
| 7 `cherche` | "Avec 'je', **le verbe 'chercher'** se termine par '-e'..." | oui | non - l'item 4 parle de `faire` |
| 8 `finis` | "Avec 'tu', le verbe 'finir' se termine par '-is'..." | oui | non |
| 9 `range` | "Avec 'il' ou 'elle', **le verbe 'ranger'** se termine par '-e'..." | oui | non - l'item 1 parle de `aller` |
| 10 `courons` | "Avec 'nous', le verbe 'courir' se termine par '-ons'..." | oui | non |
| 11 `prenez` | "Avec 'vous', le verbe 'prendre' se termine par '-ez'..." | oui | non |
| 12 `chantent` | "Avec 'ils' ou 'elles', **le verbe 'chanter'** se termine par '-ent'..." | oui | non |

**Plus aucun indice de ce paquet n'affirme d'une terminaison qu'elle vaut pour "le verbe" sans
restriction.** C'etait mon exigence, mot pour mot, et elle est satisfaite. Les deux formulations
temperees des items 2 et 3 sont conservees, ce qui est juste : elles etaient deja correctes et il n'y
avait rien a y toucher.

**Et je l'ai vu en jeu, pas seulement dans le fichier.** J'ai fait venir l'indice de l'item 7 apres
trois echecs et lu a l'ecran : *"Avec 'je', le verbe 'chercher' se termine par '-e' au présent : c'est
'cherche'."* La contradiction que j'avais vue se produire en deux minutes le 2026-08-10 - "avec tu, le
verbe se termine par -es" a la septieme manche puis `finis` a la dixieme - **ne peut plus se produire**,
parce que chaque enonce de regle est desormais borne au verbe qu'il nomme. Un enfant qui rate trois
fois de suite recoit maintenant une aide vraie. C'est tout ce que je demandais.

#### 2. Ma reference etait fautive sur un point, et `marc-dev` a raison

Il a verifie mes trois modeles d'indice et en a trouve un inexistant. **Je ne maintiens pas ma
reference, je la corrige.** J'avais ecrit : *"La correction est petite, exacte, et ses trois modeles
sont **deja dans le paquet**."* C'est faux pour le modele 2. Verifie par moi sur le paquet
d'avant correction : les douze indices employaient exactement deux formes attestees - temperer
("presque toujours", items 2 et 3) et nommer le verbe (items 1, 4, 8, 10, 11). **Aucun indice ne
nommait un groupe.** Le modele 2 etait de mon invention, presente a tort comme un modele existant.

Trois consequences, que je pose nettement pour qu'on n'ait pas a me redemander ce que je voulais dire :

1. **`marc-dev` a bien fait de n'appliquer que les deux formes attestees.** Suivre une consigne dont
   un tiers est invente aurait ete suivre mon erreur.
2. **Je maintiens que nommer le groupe aurait ete pedagogiquement acceptable**, et je maintiens ce que
   j'en disais : les cinq items concernes sont bien tous du premier groupe, je l'ai reverifie
   (`jouer`, `dessiner`, `chercher`, `ranger`, `chanter`). Ce qui tombe est ma phrase de fait, pas mon
   jugement de valeur sur la forme.
3. **Mon exigence n'a jamais ete "prends une de ces trois formes".** Elle etait : *"qu'aucun indice de
   ce paquet n'affirme d'une terminaison qu'elle vaut pour 'le verbe' sans restriction"*, et j'avais
   ecrit explicitement que je ne choisissais pas laquelle. Le choix de `marc-dev` satisfait
   l'exigence ; les trois modeles n'etaient qu'une aide, dont un etait errone.

Je le note aussi parce que c'est la troisieme fois qu'un fait que j'avais moi-meme enregistre se
revele inexact quand quelqu'un le verifie. Verifier ce que j'ai ecrit avant de m'appuyer dessus fait
partie de ma methode, et ici c'est un autre qui l'a fait a ma place.

#### 3. Le repli en deux rangees, remesure sur le code livre

Ce n'est **pas** ma condition - c'est la seconde condition de `camille-po` (`DEC-0015`, quatrieme
amendement, point 2), et c'est elle qui la ferme. Ma part est la mesure, et je la rends.

Balayage des **douze** items, indice non affiche, a 360 x 640 :

| Ce que j'avais confirme sur maquette | Ce que je mesure sur le code livre |
|---|---|
| deux colonnes par rangee a partir de la quatrieme proposition | conforme : `4 colonnes de 160 px`, deux rangees de deux |
| 160 px par colonne a 360 px | **160,0 px**, sur les douze items |
| ecart de 16 px sur les deux axes | **16 px** : rangee 1 a 365-439, rangee 2 a 455-529 ; 336 px de conteneur pour 2 x 160 px |
| aucune troncature | **aucune**, sur les douze items : aucun mot ne sort de sa boite, aucun sur deux lignes, police a 32 px partout |
| la quatrieme proposition entierement lisible au-dessus du pli | **oui, sur les douze items** : le glyphe le plus bas du paquet est a 561 px, soit 79 px au-dessus du pli |
| les cas a deux et trois colonnes inchanges | **inchanges** : les trois jeux du catalogue mesures separement, aucune regression |

Les cinq mots les plus larges du paquet, mesures sur le rendu :

| Mot | Largeur du mot | Air de chaque cote dans 160 px |
|---|---|---|
| `mangeons` | **156,0 px** | **2,0 px** |
| `cherchons` | 152,2 px | 3,9 px |
| `cherchent` | 148,3 px | 5,8 px |
| `dessinent` | 142,9 px | 8,6 px |
| `rangeons` | 139,4 px | 10,3 px |

**Aucun n'est tronque, et les six conditions de ma section 3 sont satisfaites sur le rendu.** Je
corrige au passage un de mes propres chiffres, a mon desavantage : j'avais ecrit qu'il restait "3 px
d'air a droite de mangeons" ; il en reste **2,0**. Le mot tient, mais il tient a 2 px, et c'est la
marge la plus etroite du depot. Voir la reserve 1.

**Et ce que ce repli demontre au-dela de sa propre mesure** : `cherche` et `cherches` sont a l'ecran
en meme temps, dans des colonnes de 160 px, **parfaitement distinguables** - je les ai lus. C'etait
le defaut que ma reduction a trois propositions creait, et son extinction du 2026-08-10 est confirmee
par le rendu. Les quatre propositions homophones du premier groupe sont bien la, et elles travaillent
la difficulte que cette competence doit faire travailler. Ce que j'avais ecrit au credit du paquet le
matin tient.

#### 4. Constat neuf, mesure, qui ne fonde PAS ce Done et ne le retire pas

**Cinq items sur douze mettent la seconde rangee de propositions entierement sous le pli quand
l'indice s'affiche.** Mesure sur le code livre, a 360 x 640, indice affiche :

| Enonce | Lignes d'enonce | Bas de l'indice | Rangee 1 | Rangee 2 | Etat |
|---|---|---|---|---|---|
| "Elle ___ toujours ses affaires avant de partir en récréation." | 3 | 531 | 543-617 | **633-707** | rangee 2 sous le pli |
| "Je ___ mon cahier dans mon sac." | 2 | 483 | 495-569 | **585-659** | rangee 2 sous le pli |
| "Ils ___ une chanson en rentrant de l'école." | 2 | 483 | 495-569 | **585-659** | rangee 2 sous le pli |
| "Tous les jours, il ___ à l'école." | 2 | 483 | 495-569 | **585-659** | rangee 2 sous le pli |
| "Vous ___ vos cahiers avant de commencer." | 2 | 483 | 495-569 | **585-659** | rangee 2 sous le pli |
| les sept autres, indice affiche | 1 | 435 | 447-521 | 537-611 | au-dessus du pli |
| les douze, **sans** indice | 1 a 3 | - | - | - | **tous** au-dessus du pli |

**Pourquoi ce n'est pas un motif, et pourquoi je m'y tiens.** C'est exactement le defaut de
`DEC-0016`, dont j'ai etabli moi-meme le 2026-08-10 que la cause est la longueur de l'enonce a 2 rem
et non le nombre de propositions ni la mise en page. Je l'ai mesure le meme jour sur "Le bon petit
mot", jeu du catalogue dont le Done est accorde, et j'ai **refuse** d'en faire un motif la-bas. Je ne
peux pas l'opposer ici sans appliquer deux barres differentes a deux paquets pour un seul defaut - ce
qui est precisement l'incoherence que j'ai refusee ce matin-la. Le traitement est donc le meme :
constat neuf et date, destinataire `camille-po`, sous `DEC-0016`, et le Done reste accorde.

**Ce que j'ajoute quand meme, parce que c'est le meme mecanisme et qu'il faut le compter une fois pour
toutes** : le defaut ne se declenche que quand l'enfant vient d'echouer trois fois et recoit son
aide. Sur ce paquet il touche **cinq items sur douze**, soit plus de la moitie des items a enonce de
deux lignes ou plus. Sur "Le bon petit mot" il touche cinq items sur vingt-quatre. Ce n'est pas une
question de paquet, c'est une question de hauteur d'ecran, et `DEC-0016` a raison de la traiter comme
transverse.

#### 5. Reserves - a convertir en stories par `camille-po`, non bloquantes

Les six reserves de mon refus du 2026-08-10 (section 6 de ce bloc) **restent ouvertes et inchangees**,
et je ne les reformule pas : sujet toujours pronominal et colle au trou, indice qui donne la reponse
en plus de la methode, douze items pour dix manches, la seule fille sujet qui range ses affaires,
"recreation" dans trois items, et le niveau non annonce sur l'accueil. Aucune n'a ete traitee par ce
sprint, aucune n'etait censee l'etre.

J'en ajoute deux, datees du 2026-08-10 :

1. **`mangeons` tient a 2,0 px de chaque cote de sa boite.** Ce n'est pas une troncature et je ne le
   traite pas comme telle. C'est un plancher atteint : **tout mot plus large que 156 px sortira de sa
   colonne**, et aucun test ne le verra jamais - `ADR-0025` a etabli que le budget est une largeur de
   glyphes rendus. Le jour ou ce paquet gagne un `finissaient` ou un `apprendraient` - ce qui est
   exactement ce que demande l'ouverture du futur et de l'imparfait de ma section 5 - la mise en page
   ne tiendra plus. Je le dis maintenant plutot qu'au sprint ou cela arrivera.
2. **La seconde rangee sous le pli avec l'indice**, section 4 ci-dessus, sous `DEC-0016`.

#### 6. Ce dont je reste seule garde sur ce jeu

- **La verite de ce qui est enseigne dans un indice.** Inchange, et ce sprint le confirme dans les
  deux sens : la CI etait verte sur les cinq indices faux, et elle est verte sur les cinq corriges.
  `AucunIndiceNeContreditUnAutreItemDuMemePaquetTest` epingle desormais qu'un indice ne dementit pas un
  autre item du meme paquet, et c'est un vrai gain sur la **coherence interne** - c'etait la moitie de
  mon motif. L'autre moitie, "cette regle de grammaire est-elle vraie dans la langue", reste hors de
  portee d'un automate et reste ma charge.
- **Qu'un mot tienne dans sa colonne** (reserve 1 ci-dessus), et qu'il ne soit ni tronque, ni coupe, ni
  comprime.
- **Tout ce que garde le verdict transverse** de `FICHE-bandit-manchot-additions-cp.md`, section 12, y
  compris ma reserve sur la fin du mouvement, qui vaut sur ce jeu comme sur les trois autres.

#### 7. Suites hors de ma main

- **Pour `camille-po`** : ma condition 1 de `DEC-0015` (quatrieme amendement) est **remplie** - les
  cinq indices sont corriges et j'accorde un nouveau Done pedagogique. Sa condition 2, le repli, est
  **reproduite sur le code livre** et je rends la mesure en section 3 ; c'est elle qui la ferme, pas
  moi. La remise au catalogue est sa decision et je n'en dis pas plus : je ne repriorise pas et je
  n'ecris pas le backlog.
- **Pour `camille-po`** : le constat de la section 4 et les deux reserves de la section 5 sont des
  motifs, pas des stories.
- **`squad/programme/MATRICE-couverture.md`** : la ligne `francais | CM1 | Orthographe et grammaire |
  Conjuguer les verbes frequents au present, futur et imparfait de l'indicatif` **reste `en cours`**,
  pour la raison ecrite au verdict "Ligne de matrice" ci-dessus. Elle ne bouge pas sur ce verdict.
- **Je n'efface rien** : le refus du 2026-08-10 et ses deux arbitrages du meme jour, dont la reduction
  a trois propositions que j'ai eteinte moi-meme, restent lisibles plus bas dans l'ordre.

---

### Verdict - premier audit, increment livre en s06

- **Verdict** : **REFUSE.** Done pedagogique non accorde. Ce jeu ne peut pas etre mis devant une
  classe de CM1 en l'etat.
- **Date** : 2026-08-10.
- **Commit audite** : `be60b10` (`HEAD` de `round-two`).
- **Moyens** : audit **joue**, non degrade. Worktree dedie hors de l'arbre du depot, lignee
  verifiee (`outils/verifier-lignee.sh` : "le worktree sur la branche '<ma branche>' descend de
  round-two"). JDK constate par `outils/verifier-jdk.sh` : `java du PATH : 1.8.0_202 | JDK de
  compilation : 21.0.10 | JAVA_HOME : vide | toolchains lu par Maven : non (fichier present sous un
  nom non lu)` ; `JAVA_HOME` pointe sur ce JDK 21 le temps de la commande, jamais ecrit dans un
  fichier commite. Fat jar reconstruit, lance sur un port dedie. Deux parties completes de dix
  manches jouees : une juste de bout en bout (score 10 sur 10), une fausse a chaque manche pour
  faire venir l'aide. Ecrans mesures au navigateur sans tete en 1280 x 700 et a une largeur de
  viewport reellement de 360 px (methode du rapport qualite de `yanis-qa`, voir la section 4).

#### 1. Ce qui est juste, et je le dis d'abord parce que le refus ne porte pas la-dessus

**Les douze enonces sont exacts, verifies un par un.** Pour chaque item, une seule des quatre
propositions est grammaticalement possible : aucun item n'admet deux reponses vraies.

| Enonce | Attendu | Les trois distracteurs | Verdict |
|---|---|---|---|
| "Tous les jours, il ___ à l'école." | `va` | vais, vont, allez | juste |
| "Nous ___ une histoire." | `lisons` | lit, lisent, lis | juste |
| "Vous ___ du chocolat." | `mangez` | mange, mangeons, mangent | juste |
| "Je ___ mes devoirs." | `fais` | fait, faisons, font | juste |
| "Elles ___ dans le jardin." | `jouent` | joue, jouons, jouez | juste |
| "Tu ___ un dessin." | `dessines` | dessine, dessinent, dessinez | juste |
| "Je ___ mon cahier dans mon sac." | `cherche` | cherches, cherchons, cherchent | juste |
| "Tu ___ ton exercice avant la récréation." | `finis` | finit, finissons, finissent | juste |
| "Elle ___ toujours ses affaires avant de partir en récréation." | `range` | ranges, rangeons, rangent | juste |
| "Nous ___ dans la cour pendant la récréation." | `courons` | cours, court, courent | juste |
| "Vous ___ vos cahiers avant de commencer." | `prenez` | prend, prenons, prennent | juste |
| "Ils ___ une chanson en rentrant de l'école." | `chantent` | chante, chantons, chantez | juste |

Trois qualites reelles, que je porte au credit du paquet :

- **Les six personnes grammaticales sont chacune representee deux fois exactement** : `je` (items 4
  et 7), `tu` (6 et 8), `il`/`elle` (1 et 9), `nous` (2 et 10), `vous` (3 et 11), `ils`/`elles`
  (5 et 12). C'est le bon equilibre, et il n'est pas frequent qu'un paquet l'atteigne du premier
  coup.
- **Les trois groupes sont travailles** : six verbes du premier groupe, `finir` pour le deuxieme,
  et cinq irreguliers frequents (`aller`, `lire`, `faire`, `courir`, `prendre`). Le referentiel dit
  "verbes frequents" ; ce sont bien ceux-la.
- **Les distracteurs sont tous des formes du meme verbe au meme temps**, donc aucun distracteur
  absurde et aucune devinette. Mieux : sur les items du premier groupe, les quatre propositions
  sont homophones entre elles (`dessine`, `dessines`, `dessinent`), ce qui est exactement la
  difficulte que cette competence doit faire travailler - on ne peut pas la resoudre a l'oreille.

Le contenu **travaille bien la competence annoncee**, et non une competence voisine plus facile :
c'est l'accord de la forme verbale avec la personne, au present, et rien d'autre. Sur le perimetre
d'un temps sur trois, je n'ai rien a ajouter a ce que `DEC-0013` assume deja : voir la section 5.

#### 2. Motif du refus - cinq indices sur douze enoncent une regle fausse

C'est le motif, il est unique, et il suffit.

| Item | Texte de l'indice | Ce qui cloche |
|---|---|---|
| 5 (`jouent`) | "Avec 'ils' ou 'elles', le verbe se termine par '-ent' au présent : c'est 'jouent'." | faux : `ils vont`, `ils ont`, `ils sont`, `ils font` |
| 6 (`dessines`) | "Avec 'tu', le verbe se termine par '-es' au présent : c'est 'dessines'." | faux, et **dementi par l'item 8 du meme paquet** : "Tu ___ ton exercice" -> `finis` |
| 7 (`cherche`) | "Avec 'je', le verbe se termine par '-e' au présent : c'est 'cherche'." | faux, et **dementi par l'item 4** : "Je ___ mes devoirs" -> `fais` |
| 9 (`range`) | "Avec 'il' ou 'elle', le verbe se termine par '-e' au présent : c'est 'range'." | faux, et **dementi par l'item 1** : "Tous les jours, il ___" -> `va` |
| 12 (`chantent`) | "Avec 'ils' ou 'elles', le verbe se termine par '-ent' au présent : c'est 'chantent'." | meme defaut que l'item 5 |

**On ne peut pas lire "le verbe" comme "le verbe de cette phrase-ci", et c'est le paquet lui-meme
qui l'interdit.** Il emploie deux formulations distinctes, et il les distingue :

- quand il veut parler d'un verbe precis, il le nomme : *"le verbe 'finir' se termine par '-is'"*
  (item 8), *"le verbe 'courir'"* (item 10), *"le verbe 'prendre'"* (item 11), *"Le verbe 'aller'
  est irrégulier"* (item 1), *"Le verbe 'faire' est irrégulier"* (item 4) ;
- quand il veut enoncer une regle generale, il ecrit "le verbe" tout court **et il la tempere** :
  *"le verbe se termine **presque toujours** par '-ons'"* (item 2), *"**presque toujours** par
  '-ez'"* (item 3).

Les cinq indices du tableau ci-dessus ecrivent "le verbe" tout court **sans temperer**. Sous la
convention du paquet, ce sont donc des regles generales, et elles sont fausses.

**Pourquoi cela ne peut pas etre une reserve.** L'indice est **le seul endroit de ce jeu ou l'on
enseigne quelque chose** : tout le reste est un choix entre quatre boutons. Et je l'ai verifie en
jouant : il n'apparait qu'a partir de la quatrieme manche, apres trois echecs consecutifs, donc il
est servi a l'enfant qui vient de se tromper trois fois de suite - celui qui est le moins en etat de
reperer que l'aide est fausse. Ce que cet enfant emporte de la partie, c'est "avec je, le verbe se
termine par -e", et il ecrira *je prende*, *il alle*, *je faie*. La faute que le jeu lui apprend est
exactement celle que le jeu existe pour prevenir.

**Et je l'ai vu se produire dans une seule partie**, ce n'est pas une deduction sur le fichier : a
la septieme manche j'ai lu "Avec 'tu', le verbe se termine par '-es' au présent", et a la dixieme
manche de la meme partie le jeu m'a demande "Tu ___ ton exercice avant la récréation." dont la
reponse est `finis`. Le jeu se contredit devant l'enfant, en deux minutes.

**La correction est petite, exacte, et ses trois modeles sont deja dans le paquet.** Pour chacun des
cinq indices, une seule des trois formes suffit :

1. temperer, comme les indices 2 et 3 : "le verbe se termine **presque toujours** par '-ent'" ;
2. nommer le groupe : "**les verbes en -er** se terminent par '-e' avec 'je'" - c'est la forme la
   plus juste des trois, parce que les cinq items concernes sont tous du premier groupe ;
3. nommer le verbe, comme les indices 8, 10 et 11 : "le verbe 'chanter' se termine par '-ent'".

Je ne choisis pas laquelle : les trois sont acceptables et le choix appartient a l'auteur du
contenu. Ce que j'exige, c'est qu'aucun indice de ce paquet n'affirme d'une terminaison qu'elle vaut
pour "le verbe" sans restriction.

#### 3. Ce dont je reste seule garde sur ce jeu

Pour qu'on ne croie pas couvert ce qui ne l'est pas. `PaquetsFrancaisS04Test` epingle desormais que
les propositions sont toutes des formes du meme verbe au present, que les six personnes sont
representees, et que le paquet varie sur vingt manches. **Aucun de ces garde-fous ne regarde le
contenu des indices, et aucun ne peut le faire** : "cette regle de grammaire est-elle vraie" n'est
pas une propriete qu'un test peut trancher. La verite de ce qui est enseigne a l'enfant est ma
charge, et ce refus en est la demonstration : la CI est verte sur ce paquet.

#### 4. Constat neuf, mesure, qui ne fonde PAS ce refus

Je le separe explicitement du motif, parce qu'il ne doit pas etre lu comme une barre que j'aurais
posee apres coup.

**Sur un telephone de 360 x 640, la quatrieme proposition est sous le pli.** "Le bon verbe" est le
premier jeu du produit a quatre propositions ; les deux autres en ont trois. Mesure a une largeur et
une hauteur de viewport reellement de 360 x 640 - la page reelle chargee dans un cadre de largeur
et de hauteur CSS fixees, dans une fenetre Chrome de 900 px, pour contourner le plancher de 500 px
etabli par `yanis-qa` :

| Element | Position verticale | Sous le pli a 640 px ? |
|---|---|---|
| bandeau | 0 - 76 | non |
| proposition 1 | 368 - 437 | non |
| proposition 2 | 458 - 527 | non |
| proposition 3 | 548 - 617 | non |
| **proposition 4** | **638 - 707** | **oui, 2 px visibles sur 69** |
| hauteur totale du document | 753 px | |

L'enfant voit trois propositions et un liseré. Comme l'ordre des propositions est tire a chaque
manche, la bonne reponse sera parfois la quatrieme : l'enfant repondra alors en ignorant qu'elle
existait. Sur le meme viewport, "Le bon son" (trois propositions) tient entierement au-dessus du
pli, verifie par la meme mesure - le defaut est bien lie au passage a quatre propositions et non a
l'habillage en general.

**Pourquoi ce n'est pas un motif.** Aucune decision du depot ne fixe de hauteur de viewport
minimale : `DEC-0007` ne parle que de largeur. Je ne vais pas fabriquer une barre en cours d'audit
pour la faire porter sur un increment qui n'a pas pu s'y preparer. C'est donc un **constat neuf et
date du 2026-08-10**, a instruire par `camille-po` comme une story, et il concernera tout futur
paquet a plus de trois propositions. Il ne durcit aucune reserve anterieure.

#### 5. Ou est la barre pour `couvert-valide`, puisque personne ne me demande de la prononcer

`DEC-0013` assume que la ligne du referentiel nomme trois temps et que ce paquet n'en couvre qu'un.
Je confirme que ce choix de perimetre est defendable - on n'ouvre pas trois temps a la fois - et je
n'en fais pas un motif. Mais je dis ou est la barre, comme je l'ai fait pour les homophones, pour
qu'elle ne se decouvre pas dans deux sprints :

1. **le futur de l'indicatif et l'imparfait de l'indicatif sont ajoutes**, avec les six personnes
   chacun, sur des verbes frequents des trois groupes comme le present les a deja ;
2. **au moins un item par temps oppose deux temps entre eux** et pas seulement deux personnes d'un
   meme temps. Le motif est de couverture : la competence dit "conjuguer au present, futur et
   imparfait", et un enfant qui n'a jamais eu a choisir *entre* deux temps n'a jamais eu a
   identifier le temps demande. Aujourd'hui les quatre propositions sont toujours au present, donc
   le temps est donne, jamais choisi ;
3. **la marque du temps est reperable dans la phrase** (un complement comme "hier", "demain",
   "tous les jours") plutot que devinable : c'est ce qui rend le point 2 jouable sans ambiguite ;
4. **aucun item n'admet deux lectures correctes**, comme aujourd'hui - et le risque augmente des
   qu'on melange les temps ("hier" avec un present de narration, par exemple), donc j'auditerai ces
   items un par un ;
5. **les indices de ces items nomment la marque du temps** et ne se contentent pas de nommer la
   personne ;
6. et **j'aurai joue l'increment** avant de le dire.

Rien d'autre. Ce qui n'est pas dans cette liste ne bloquera pas `couvert-valide` et ne pourra pas
m'etre oppose comme l'ayant bloque. Le motif de refus de la section 2 est independant de cette
liste : il doit etre corrige quel que soit le sort du futur et de l'imparfait.

#### 6. Reserves - a convertir en stories par camille-po, non bloquantes

Elles ne fondent pas le refus et elles ne le grossissent pas.

1. **Le sujet est toujours un pronom, colle au trou, dans les douze items.** Le meme constat que ma
   reserve 2 sur les homophones : la discrimination se fait sur le mot immediatement a gauche, donc
   sans jamais avoir a lire la phrase. Au CM1, la vraie difficulte est un sujet en groupe nominal
   ("Les élèves de la classe ___") ou un sujet eloigne du verbe ("Les enfants, quand la cloche
   sonne, ___"). En l'etat l'exercice est juste mais il est de consolidation CE2 plutot que
   d'apprentissage CM1. C'est le premier point que je traiterais apres le motif de la section 2, et
   c'est aussi ce qui donnerait sa profondeur au point 2 de la section 5.
2. **L'indice donne la reponse en plus de la methode** ("...: c'est 'lisons'."). Meme remarque que ma
   reserve 10 sur les homophones : apres trois echecs c'est defendable, mais la manche portant
   l'indice devient un point gratuit. Le jour ou le score mesurera quelque chose, l'indice devra
   s'arreter a la manipulation.
3. **Douze items pour dix manches : une partie montre dix items sur douze.** Deux items de
   difference entre deux parties, c'est peu ; l'enfant a pratiquement tout vu des la premiere. Le
   paquet des homophones est a vingt-quatre items pour dix manches et c'est la bonne proportion.
4. **"Elle ___ toujours ses affaires avant de partir en récréation."** est le seul item ou une fille
   est sujet d'une action, et cette action est de ranger. Ce n'est pas grave, ce n'est pas faux, et
   `Elles ___ dans le jardin.` equilibre en partie ; je le signale parce que le remplacement est
   gratuit et qu'on n'ecrit pas douze phrases en laissant celle-la seule.
5. **"récréation" apparait dans trois items sur douze** (8, 9, 10), et "école" dans deux. Sur un
   paquet aussi court, cela donne une impression de decor unique. Sans consequence
   d'apprentissage.
6. **L'accueil ne dit pas a quel niveau s'adresse le jeu.** "Le bon verbe" n'annonce pas le CM1
   alors que "Calcul CP" annonce le CP. Reserve deja ouverte sur les deux autres jeux de francais
   (critere de `camille-po` dans cette fiche) : elle n'est **pas** traitee, et elle empire, puisque
   l'accueil propose desormais deux jeux de CM1 non marques a cote d'un jeu de CP marque. Un enfant
   de CP peut ouvrir celui-ci. Question produit pour `camille-po`, pas un motif.

#### 7. Suites hors de ma main

- `squad/programme/MATRICE-couverture.md`, ligne `francais | CM1 | Orthographe et grammaire |
  Conjuguer les verbes frequents au present, futur et imparfait de l'indicatif` : **reste
  `en cours`**, et le Done pedagogique n'est pas accorde. Je n'ai pas edite cette ligne, elle
  appartient a `camille-po`.
- Un refus renvoie l'increment a `camille-po`, qui en fait du backlog. Il ne bloque pas le sprint
  suivant, et le contenu deja fusionne n'a pas besoin d'etre defait : les douze enonces, les douze
  couples personne/forme et les sept indices justes sont acquis et servent de base a la correction.
- Rien de ce refus ne concerne l'habillage commun aux jeux : je le tranche dans
  `FICHE-bandit-manchot-additions-cp.md`, qui est depuis s02 le lieu ou je l'ecris.

---

### Arbitrage prealable au developpement - ramener ce paquet a trois propositions, du 2026-08-10

**Ce bloc n'est pas un Done pedagogique**, et il ne modifie pas le refus du 2026-08-10, qui reste
entier : les cinq indices fautifs doivent etre corriges, quel que soit le sort de cette question.
`DEC-0014` reste en vigueur, ce jeu reste hors du catalogue, et il n'y rentrera pas sans un nouveau
Done pedagogique que je prononcerai en jouant.

**Saisine.** `theo-archi` (`ADR-0026`) et `camille-po` (`DEC-0015`) me routent une contradiction entre
deux stories du sprint 7 : le nombre de colonnes de la machine a sous suit le contenu, et ce paquet
est le seul du depot a quatre propositions. Une des sorties possibles est de le ramener a trois, ce
qui est un changement de contenu, donc ma main. L'habillage commun est tranche dans
`FICHE-bandit-manchot-additions-cp.md`, arbitrage du 2026-08-10.

#### Le verdict, en deux phrases parce qu'il en faut deux

**J'accepte de ramener ce paquet a trois propositions par item, et je l'accepte pour son propre motif -
la quatrieme proposition est deja sous le pli aujourd'hui, je l'ai mesure moi-meme le 2026-08-10 - et
non pour faire tenir une mise en page.** Et je constate, mesure sur le rendu, que **cette reduction ne
suffit pas a faire tenir ce paquet en colonnes** : neuf des douze items debordent encore a trois
colonnes, donc personne ne doit la vendre comme la reponse au probleme de largeur.

#### La mesure qui fonde la seconde phrase

Maquette jetable hors du depot, non commitee, sur les deux feuilles reellement servies et la structure
reelle de `fragments/manche.html`, seul delta `.propositions { flex-direction: row }` et
`.proposition { flex: 1 1 0 }`. Capture a une largeur de viewport reellement de 360 px par
`outils/mesurer-rendu.sh 360 <url> <png>`, navigateur designe par la variable `CHROME`. Les douze items
sont rendus avec leur bonne reponse et deux de leurs distracteurs, en trois colonnes de 101 px.

| Item | Trois propositions rendues | A 3 colonnes, 360 px |
|---|---|---|
| 1 `va` | va, vais, vont | tient |
| 2 `lisons` | lisons, lisent, lis | tient |
| 3 `mangez` | mangez, mangeons, mangent | **deborde** - et `mangez` est la bonne reponse, donc irretirable |
| 4 `fais` | fais, fait, font | tient |
| 5 `jouent` | jouent, joue, jouons | **deborde**, `jouent` et `jouons` touchent les bords |
| 6 `dessines` | dessines, dessine, dessinent | **deborde** ; `dessines` et `dessinent` s'affichent tous deux tronques |
| 7 `cherche` | cherche, cherches, cherchent | **deborde** ; `cherche` et `cherches` s'affichent **identiques** |
| 8 `finis` | finis, finit, finissent | **deborde** sur `finissent` |
| 9 `range` | range, ranges, rangent | **deborde** sur `rangent` |
| 10 `courons` | courons, courent, cours | **deborde** sur `courons` et `courent` |
| 11 `prenez` | prenez, prenons, prennent | **deborde** sur `prenez` et `prennent` |
| 12 `chantent` | chantent, chante, chantons | **deborde** sur `chantent` |

**Trois items sur douze tiennent. Neuf ne tiennent pas.** Et les items 6 et 7 sont pires que
disgracieux : deux propositions differentes s'y affichent avec les memes lettres visibles, donc l'item
n'a plus de reponse identifiable. **Un item dont deux propositions sont indiscernables a l'ecran est un
motif de refus a lui seul**, et il le sera si l'increment est livre ainsi.

La reduction a trois n'est donc pas la reponse au probleme de largeur. Ce qu'elle change, c'est
qu'elle en retire une cause aggravante ; la cause principale est la longueur des formes verbales, et
elle ne bougera pas : `dessinent`, `chantent`, `courons`, `prennent` sont le contenu de la competence.
Le choix de disposition reste la saisine de `camille-po` et de `theo-archi`, et je n'empiete pas.

#### Pourquoi j'accepte quand meme la reduction, sur son propre motif

Mon constat du 2026-08-10, section 4 de mon refus ci-dessus, mesure a 360 x 640 : **la quatrieme
proposition est sous le pli, 2 pixels visibles sur 69.** Comme l'ordre des propositions est tire a
chaque manche, la bonne reponse est parfois cette quatrieme, et l'enfant repond alors sans savoir
qu'elle existait. C'est un defaut deja etabli, deja ecrit, deja date, et anterieur au chantier machine
a sous. La reduction a trois le ferme.

Je repete ce que j'avais ecrit alors, pour ne pas etre lue comme deplacant mes poteaux : ce constat
**n'etait pas et ne devient pas un motif de refus**, parce qu'aucune decision du depot ne fixe de
hauteur de viewport minimale. Il est ce qu'il etait : un constat a instruire. Ce qui change
aujourd'hui, c'est qu'on me demande explicitement si j'accepte la reduction, et je reponds oui, en
citant ce constat comme la raison qui la rend bonne independamment de toute mise en page.

#### Le prix pedagogique, chiffre, et pourquoi je l'accepte

**La probabilite de reussir au hasard passe de 25 % a 33 %.** C'est reel et je ne le minimise pas.
Trois raisons pour lesquelles je l'accepte a ce niveau et sur ce paquet :

1. **trois propositions est la norme des deux autres paquets du depot** et une forme parfaitement
   courante au cycle 3. Ce n'est pas une derogation, c'est l'alignement ;
2. **le distracteur que je fais retirer est le moins discriminant des trois**, voir le tableau
   ci-dessous. Le pouvoir de discrimination de l'item **augmente** pendant que la chance augmente :
   l'enfant qui ne sait pas garde trois formes homophones ou quasi homophones entre lesquelles il ne
   peut pas trancher a l'oreille, ce qui est exactement la difficulte de la competence. Je juge le
   solde neutre a positif ;
3. **le score de ce jeu ne mesure encore rien** - c'est ma reserve 2 de la section 6 ci-dessus, non
   levee : l'indice donne la reponse. Ce n'est donc pas un instrument d'evaluation dont je degraderais
   la fiabilite.

**Ce que je n'accepte pas** : que cette reduction devienne une regle generale. Elle vaut pour **ce
paquet et ces douze items**. Un futur paquet a quatre propositions repassera devant moi comme un
contenu neuf, et je ne raboterai pas d'avance.

#### La consigne executable : les douze triplets exacts, aucune question a me reposer

La regle que j'applique : **on garde la bonne reponse et les deux formes les plus proches d'elle a
l'oreille ; on retire celle qui s'en distingue le plus nettement.** Le motif est celui du point 2
ci-dessus. Voici le resultat item par item ; ce sont ces valeurs-la que je valide, et pas un choix
libre de l'auteur.

| Item | Enonce | Bonne reponse | Distracteurs **conserves** | Distracteur **retire** |
|---|---|---|---|---|
| 1 | "Tous les jours, il ___ à l'école." | `va` | `vais`, `vont` | `allez` |
| 2 | "Nous ___ une histoire." | `lisons` | `lisent`, `lis` | `lit` (homophone de `lis`, donc redondant) |
| 3 | "Vous ___ du chocolat." | `mangez` | `mangeons`, `mangent` | `mange` (homophone de `mangent`) |
| 4 | "Je ___ mes devoirs." | `fais` | `fait`, `font` | `faisons` |
| 5 | "Elles ___ dans le jardin." | `jouent` | `joue`, `jouons` | `jouez` |
| 6 | "Tu ___ un dessin." | `dessines` | `dessine`, `dessinent` | `dessinez` |
| 7 | "Je ___ mon cahier dans mon sac." | `cherche` | `cherches`, `cherchent` | `cherchons` |
| 8 | "Tu ___ ton exercice avant la récréation." | `finis` | `finit`, `finissent` | `finissons` |
| 9 | "Elle ___ toujours ses affaires avant de partir en récréation." | `range` | `ranges`, `rangent` | `rangeons` |
| 10 | "Nous ___ dans la cour pendant la récréation." | `courons` | `courent`, `cours` | `court` (homophone de `cours`, donc redondant) |
| 11 | "Vous ___ vos cahiers avant de commencer." | `prenez` | `prenons`, `prennent` | `prend` |
| 12 | "Ils ___ une chanson en rentrant de l'école." | `chantent` | `chante`, `chantons` | `chantez` |

**Ce que j'ai verifie sur ces douze triplets, un par un :**

- **aucun item n'admet deux reponses correctes** apres le retrait. Verifie sur les douze ;
- **les douze enonces ne changent pas**, ni leur ponctuation, ni leurs accents. Le refus du 2026-08-10
  ne portait pas sur eux et ne les rouvre pas ;
- **les six personnes grammaticales restent representees deux fois chacune** : retirer un distracteur
  ne touche pas le sujet de l'enonce ;
- **les trois groupes restent travailles**, et les cinq verbes irreguliers frequents restent ;
- **la difficulte homophonique reste sur les items du premier groupe** : `dessines`/`dessine`/
  `dessinent` (6), `cherche`/`cherches`/`cherchent` (7), `range`/`ranges`/`rangent` (9),
  `chantent`/`chante` (12), `jouent`/`joue` (5). C'est la propriete que je portais au credit du paquet
  le 2026-08-10 et elle est intacte ;
- **les douze indices ne sont pas touches par cette reduction.** Le refus du 2026-08-10 les concerne,
  cette reduction non : ce sont deux corrections independantes du meme paquet, et il faut les deux.

#### Ce dont je reste seule garde sur ce point

1. **Que ces douze triplets soient ceux-la.** `PaquetsFrancaisS04Test` epingle que les propositions
   sont des formes du meme verbe au present ; il ne dira jamais **lesquelles** ni combien. Le choix du
   distracteur retire est un jugement pedagogique et il n'a aucun automate.
2. **Qu'aucune proposition ne soit tronquee a l'ecran.** Non gardable par un automate de ce depot
   (`ADR-0025`, `ADR-0026` section 6), et sur ce paquet ce n'est pas theorique : neuf items sur douze
   debordent aujourd'hui a trois colonnes, deux rendent deux propositions indiscernables.
3. **Que la reduction ne soit pas etendue a un autre paquet sans repasser devant moi.**

#### Suites hors de ma main

- **`DEC-0014` n'est pas leve par cet arbitrage.** Ce jeu reste hors du catalogue. Il y rentrera quand
  j'aurai joue un increment portant **les deux** corrections - les cinq indices, et les trois
  propositions - et prononce un Done pedagogique.
- **Pour `camille-po`** : la disposition qui fait tenir ce paquet en colonnes reste sa saisine avec
  `theo-archi`, et ma mesure ci-dessus est la piece qui montre que la reduction a trois n'y suffit pas.
  Je ne choisis pas entre repli des colonnes, report du jeu et recul sur la cible de largeur.
- **`squad/programme/MATRICE-couverture.md`** : la ligne `francais | CM1 | Orthographe et grammaire |
  Conjuguer les verbes frequents au present, futur et imparfait de l'indicatif` **reste `en cours`**.
  Cet arbitrage ne la fait pas bouger et je ne l'ai pas editee.
- **La barre pour `couvert-valide` reste celle de la section 5 de mon refus du 2026-08-10**, mot pour
  mot. Rien de cet arbitrage ne s'y ajoute.

---

### Second arbitrage du 2026-08-10 - j'eteins ma propre reduction a trois propositions

**Ce bloc n'est pas un Done pedagogique**, et il ne touche pas au refus du 2026-08-10 : les cinq
indices fautifs doivent toujours etre corriges, `DEC-0014` reste en vigueur, ce jeu reste hors du
catalogue. Il revient sur **mon acceptation** de la reduction a trois propositions, ecrite plus haut
ce meme jour, apres deux mesures posterieures.

#### Le verdict, en une phrase nette

**Mon acceptation du 2026-08-10 de ramener ce paquet a trois propositions est ETEINTE par deux mesures
posterieures du meme jour : les douze triplets ne sont plus applicables, les quatre propositions
restent, et personne ne doit executer cette reduction en croyant appliquer ma decision.**

#### Les deux mesures qui l'eteignent

**Premiere mesure : mon motif reposait sur une cause que j'avais mal identifiee.** J'avais accepte la
reduction "sur son propre motif - la quatrieme proposition est deja sous le pli aujourd'hui", et
j'avais ecrit le 2026-08-10, section 4 de mon refus ci-dessus :

> *"le defaut est bien lie au passage a quatre propositions et non a l'habillage en general."*

**C'est faux, et l'erreur est de moi.** J'ai mesure les vingt-quatre items de "Le bon petit mot",
paquet a **deux** propositions et jeu du catalogue : trois d'entre eux mettent leurs **deux** boutons
de reponse sous le pli a 360 x 640 quand l'indice s'affiche, et sur l'item 24 il n'en reste rien du
tout. Mesure et methode dans `FICHE-bandit-manchot-homophones-cm1.md`, constat du 2026-08-10. La
cause du defaut est **la longueur de l'enonce a 2 rem**, pas le nombre de propositions. J'avais
generalise depuis "Le bon son", dont les enonces sont des mots seuls en capitales.

**Retirer une proposition n'a donc jamais adresse la cause de ce que je voulais corriger.** C'est le
point qui decide, et il ne depend pas de la proposition d'`ines-design`.

**Seconde mesure : la mise en page, elle, adresse le symptome.** J'ai remesure le repli en deux
rangees d'`ines-design` (`squad/sprints/s07-machine-a-sous/proposition-ines-machine-a-sous.md`) et je
l'ai confirme - detail dans `FICHE-bandit-manchot-additions-cp.md`, second arbitrage du 2026-08-10.
Sur un ecran de manche complet a 360 px, avec indice, sur l'item 8 de ce paquet :

| Etat | Ou tombe la quatrieme proposition | Ce que l'enfant voit |
|---|---|---|
| ma mesure du 2026-08-10, quatre propositions empilees | 638 - 707 | **2 px sur 69**, aucun mot |
| repli en deux rangees, remesure par moi | 585 - 658, glyphes a 611 - 634 | **le mot est entierement lisible**, 6 px de garde sous le pli |

Le defaut que j'avais nomme - *"l'enfant voit trois propositions et un lisere (...) il repondra en
ignorant qu'elle existait"* - est donc **eteint** : le mot se lit, et les 55 px de bouton visibles
au-dessus du pli depassent encore la cible tactile de 44 px. Que la boite ne soit pas entierement
au-dessus du pli ne me gene pas : mon motif etait qu'une proposition soit **invisible**, pas qu'une
boite soit geometriquement entiere.

#### Pourquoi je n'invente pas un motif de remplacement

C'est la partie ou je devrais me surveiller, et je la traite explicitement.

On m'offre la lecture "la reduction reste justifiee pour elle-meme, sur un motif pedagogique que tu
nommerais - par exemple qu'un enfant de CM1 discrimine mieux sur trois propositions que sur quatre".
**Je ne la prends pas, parce que je ne le crois pas, et parce que j'ai ecrit le contraire ce matin
dans cette meme fiche**, au credit de ce paquet :

> *"sur les items du premier groupe, les quatre propositions sont homophones entre elles (`dessine`,
> `dessines`, `dessinent`), ce qui est exactement la difficulte que cette competence doit faire
> travailler - on ne peut pas la resoudre a l'oreille."*

Quatre propositions homophones travaillent cette competence **mieux** que trois. Quatre est une forme
courante et adaptee au cycle 3. Chercher aujourd'hui un motif pedagogique a une reduction dont le
motif reel vient de tomber serait fabriquer une raison apres coup pour sauver une decision - et une
barre qu'on rehabille apres coup fait de ma porte un caprice. Je m'y refuse ici comme je m'y refuse
quand c'est une equipe qui me le demande.

#### Le solde honnete de la reduction, maintenant que son benefice est tombe

| | |
|---|---|
| Benefice | **aucun.** Le pli est adresse par la mise en page, et la largeur aussi |
| Cout mesure | la chance de reussir au hasard passe de **25 % a 33 %** |
| Cout mesure | la difficulte homophonique de la competence est **amputee** sur les items du premier groupe |
| Defaut cree, mesure | sur les items 6 et 7, **deux propositions s'affichent avec les memes lettres visibles** a trois colonnes de 101 px (`cherche` et `cherches` rendant tous deux `cherche`), donc l'item n'a plus de reponse identifiable |

Un changement sans benefice, avec deux couts et un defaut, n'est pas un changement a faire. **Et le
probleme `cherche` / `cherches` n'a pas a etre resolu "autrement" : il disparait avec la reduction qui
le creait.** A quatre propositions dans le repli en deux rangees, aucun des douze items ne tronque,
verifie sur capture.

#### Ce que deviennent mes douze triplets

**Ils ne sont plus applicables. Ils restent ecrits.**

- Le tableau des douze triplets, plus haut dans cette fiche, **n'est pas efface** : un verdict n'est
  jamais efface de ces fiches, meme eteint, et l'histoire de ce paquet doit se lire dans l'ordre. Il
  est desormais a lire comme **la trace d'une decision eteinte le 2026-08-10 par ce bloc**, pas comme
  une consigne.
- **Aucun developpeur ne les applique.** Le paquet
  `adapters/adapter-content/src/main/resources/contenu/cm1-conjugaison-present.yaml` garde ses
  **quatre propositions par item** : une bonne reponse et trois distracteurs, exactement comme
  aujourd'hui. Aucun distracteur n'est retire.
- **La seule correction attendue sur ce paquet reste celle du refus du 2026-08-10** : les cinq indices
  qui enoncent une regle de conjugaison fausse. Elle est inchangee, entiere, et ses criteres sont deja
  ecrits.
- **La porte est fermee derriere cette reduction.** Si un increment futur fait reapparaitre une
  proposition invisible ou tronquee sur ce paquet, la reponse n'est **pas** de ressortir ces triplets :
  c'est de traiter la mise en page, parce qu'une reponse de mise en page existe desormais et qu'elle
  est mesuree. Je l'ecris pour qu'on ne me redemande pas cette reduction comme un raccourci dans deux
  sprints.

#### Ce que cela change au perimetre du sprint, puisque sofia-sm n'a pas gele

**Une sortie de moins, et elle n'en etait pas une.** `camille-po` avait trois sorties possibles pour
faire tenir ce paquet ; il lui en reste deux, le repli de mise en page ou le report du jeu hors du
sprint, **et je ne choisis pas entre elles.** Ce que ma decision lui apporte :

- la reduction du contenu n'est plus une option, et elle n'aurait de toute facon pas suffi - mesure du
  premier arbitrage : neuf items sur douze debordaient encore a trois colonnes ;
- **le repli de mise en page est valide de mon cote**, sous les deux conditions du second arbitrage
  (contraste de 4,5:1 pendant l'animation, voile confine dans la boite sans `overflow: hidden`) et
  avec la reserve chiffree des 3 px d'air sur `mangeons` ;
- **la correction des cinq indices reste independante des quatre stories du chantier** et prete a
  developper, comme `DEC-0015` le dit deja. Rien de ce bloc ne la retarde.

#### Ce que je declare degrade

- **Le chantier n'est pas ecrit.** Le repli en deux rangees n'existe dans aucun fichier du depot :
  j'ai mesure une maquette jetable, non commitee, portant les deux regles `:has()` d'`ines-design` sur
  les deux feuilles reellement servies. Si ce qui est livre differe, mes chiffres ne valent plus et
  cette extinction est a reexaminer - **mais le premier motif, l'erreur de cause, tient
  independamment de toute mise en page.**
- **Les 6 px de garde sous le pli sur l'item 8 dependent de la police de ma machine**, et l'item 9
  (l'enonce le plus long de ce paquet) reste sous le pli avec son indice, repli ou non - meme queue de
  defaut que sur "Le bon petit mot", meme constat, meme destinataire.
- **Je n'ai pas rejoue ce paquet.** Il est hors catalogue et son refus n'est pas leve ; il n'y a rien a
  jouer. Le Done pedagogique de ce jeu se prononcera quand j'aurai joue un increment portant la
  correction des cinq indices, et pas avant.

#### Suites hors de ma main

- **`DEC-0014` n'est pas leve.** Ce jeu reste hors du catalogue jusqu'a un nouveau Done pedagogique.
- **Pour `camille-po`** : sa decision de perimetre est intacte et lui appartient entierement. Le seul
  changement est qu'une des sorties qu'elle avait a peser est retiree par moi, avec son motif.
- **Pour `sofia-sm`** : si un developpeur a deja commence a retirer des distracteurs de ce paquet sur
  la foi de mon arbitrage precedent, ce travail est a defaire. C'est le risque exact que ce bloc existe
  pour fermer.
- **`squad/programme/MATRICE-couverture.md`** : la ligne `francais | CM1 | Orthographe et grammaire |
  Conjuguer les verbes frequents au present, futur et imparfait de l'indicatif` **reste `en cours`**.
  Je ne l'ai pas editee.
- **La barre pour `couvert-valide` reste celle de la section 5 de mon refus du 2026-08-10**, mot pour
  mot. Ce bloc n'y ajoute rien et n'en retire rien.
