# ADR-0029 - Faire porter un decalage declare a toute colonne que le contenu peut produire

- **Statut** : Accepte
- **Date** : 2026-08-11
- **Auteur** : theo-archi
- **Sprint** : s08 (planning)

## Contexte

Le rouleau livre en S7-1 decale le depart des colonnes. `lea-dev` a livre **0,08 s par colonne,
cumulatif** : `recreo.css` declare `animation-delay: 0.08s` sur `:nth-child(2)`, `0.16s` sur
`:nth-child(3)`, `0.24s` sur `:nth-child(4)`, la premiere colonne partant sans retard. Mesures de
rendu de s07 : 167 ms a trois colonnes, 234 ms a quatre. La borne de `nadia-prof` (arbitrage du
2026-08-10, section 2) est **250 ms entre la premiere et la derniere colonne**.

**Une cinquieme colonne porterait le decalage a 320 ms declares.** Deux faits changent la probabilite
que ce cas arrive : la story S7-2 acte que le nombre de colonnes **suit le contenu et n'est jamais
fige** (`.propositions` est une rangee flex et `th:each` rend autant de boutons que le contenu en
fournit), et le sprint 8 ouvre un chantier de cabinet autour des rouleaux qui peut en amener.

**Ce qu'aucun test ne voyait, et il faut etre precis sur quoi.** La clause 6 de
`LAnimationRetardeMaisNeDetermineRienTest` confronte deja **chaque retard declare** a la borne de
250 ms, et elle mord : sa mise a l'epreuve `retard-ecrit-a-part` est exactement la forme d'un decalage
par colonne. Ce qui n'etait collecte par **aucune population** du depot, c'est l'autre moitie : **le
nombre de colonnes que le contenu peut produire.** Rien ne reliait les trois rangs declares dans la
feuille au contenu servi. Un paquet a cinq propositions ne rougissait donc nulle part : sa cinquieme
colonne partait simplement sans retard, en meme temps que la premiere, et le decalage livre cessait
d'exister pour elle sans qu'aucun diff ne le montre.

C'est la meme famille de trou que les deux que j'ai fermes en s07 dans mon propre garde-fou - une
population que la clause ne collecte pas encore.

**Le chiffre d'aujourd'hui, avec sa methode.** Le plus grand nombre de propositions qu'un paquet du
depot peut afficher est **4**, atteint par `cm1-conjugaison-present.yaml`. Methode : compte de
`1 + distracteurs` sur chaque item des 4 paquets de `adapters/adapter-content/src/main/resources/contenu/`
et des 61 paquets de `contenu-repris/`, le decoupage des listes en ligne respectant les guillemets -
un comptage naif sur la virgule en annonce 5 et 7, par les virgules **internes aux chaines**
(`"0,05 L"`, `"Paix, Amour, Joie"`), et c'est une erreur que j'ai faite avant de la corriger. Aucun
paquet du depot, servi ou en reserve, ne depasse donc quatre propositions - la cinquieme colonne
n'existe nulle part aujourd'hui, et c'est ce qui distingue cet `ADR` d'un garde-fou ecrit contre un
defaut non etabli.

## Decision

**Toute colonne que le contenu embarque peut produire porte un `animation-delay` declare par une
feuille maison.**

Rien de plus, et c'est deliberement une clause de **couverture** et non de valeur. Le seuil de
`nadia-prof` reste ecrit a un seul endroit, dans `adapter-web`, et n'est recopie nulle part. Les deux
clauses forment une pince, et c'est la pince qui garde la propriete complete :

- **une colonne de plus sans sa regle** : le garde-fou de cet `ADR` rougit, en nommant le paquet qui
  produit cette colonne ;
- **une colonne de plus avec sa regle, obtenue en prolongeant la progression** : la clause 6
  d'`adapter-web` rougit sur la borne de 250 ms.

Le seul chemin vert d'une colonne de plus est donc de **revoir** le decalage pour qu'il tienne au
nombre de colonnes reel, jamais de le prolonger. Ce choix est de forme visuelle : il appartient a
`ines-design` sous l'arbitrage de `nadia-prof`, et ce garde-fou ne le fait pas a leur place - il refuse
seulement qu'il soit escamote.

### Ce que cette decision ne garde pas, et le dit

**Que le decalage soit croissant.** Poser `animation-delay: 0s` sur la cinquieme colonne satisferait la
lettre de la clause en cassant l'effet. Je refuse d'exiger une progression stricte, et le motif est
celui d'`ADR-0025` : la forme du mouvement appartient a `ines-design` sous veto de `nadia-prof`, et
encoder aujourd'hui une progression que son auteur n'a pas demande de rendre permanente rendrait rouge
une refonte legitime demain. Une abstraction se justifie sur un usage constate. Ce residu-la se voit en
jouant, et `nadia-prof` voit ce genre de chose - elle a mesure 13 px de residu a la derniere image du
mouvement.

**La position d'une colonne dans le document.** `:nth-child(k)` designe le k-ieme enfant de
`form.propositions`, qui ne contient aujourd'hui que des boutons `.proposition`. Le jour ou un element
frere s'y ajouterait, les rangs glisseraient et les retards seraient mal attribues. C'est un autre
defaut, garde par d'autres clauses de `adapter-web` sur le balisage, et ce garde-fou-ci ne le voit pas.

## Regles executables

### `ChaqueColonneQueLeContenuPeutProduirePorteSonDecalageTest`

`bootstrap/src/test/java/com/recreo/bootstrap/rendu/ChaqueColonneQueLeContenuPeutProduirePorteSonDecalageTest.java`

**Pourquoi dans `bootstrap`.** Il a besoin des deux sources a la fois - les paquets
(`adapter-content`) et les feuilles maison (`adapter-web`) - et aucun des deux modules ne voit l'autre
(Clean Architecture). Seul `bootstrap` voit les deux : meme motif que
`LExplicationDUnItemReelAtteintLEcranDeRetourTest`.

**Ou les deux sources sont lues, et pourquoi j'ai change d'avis en le mesurant.** La premiere ecriture
de ce garde-fou balayait le **classpath**, ce qui paraissait le choix juste - lire ce que l'application
embarque plutot qu'une arborescence de source. Mesure sur un `mvn clean verify` complet : la population
de feuilles etait **vide de tout `animation-delay`**. La resolution ramenait un artefact **perime** du
depot local - `recreo-adapter-web-0.1.0-SNAPSHOT.jar`, 12 711 octets, zero retard declare - la ou la
feuille du depot en fait 18 407 avec trois, et la ou le jar que le reacteur venait de construire les
contenait bien. Un garde-fou dont la population depend de ce qui se trouve **installe sur le poste**
n'est pas un garde-fou : il pouvait etre vert par accident tout autant que rouge, et rien dans son nom
ne l'aurait dit.

Le perimetre vient donc du **pom agregateur**, seul endroit du depot qui dise quels modules existent :
les modules sont lus dans `<modules>`, puis chacun est balaye sous `src/main/resources`. Aucun nom de
module n'est ecrit - c'est le mecanisme que `LesGardeFousSontMisALEpreuveTest` emploie deja dans ce
meme module pour balayer les modules voisins.

**Ce qui a rendu ce defaut visible est la clause de non-vacuite d'`ADR-0003`**, et c'est la seule
raison pour laquelle il n'est pas passe : sans l'assertion "aucune feuille maison ne declare de retard
sur un rang de colonne", la boucle de couverture aurait tourne sur une population vide et le garde-fou
aurait ete **vert du premier coup**, sur rien. Je l'inscris ici parce que c'est la troisieme fois dans
ce depot qu'une clause de non-vacuite attrape une population fausse, et la premiere fois qu'elle
attrape la mienne.

**Cout assume.** Le decoupage de blocs CSS est ecrit une seconde fois : celui d'`adapter-web` vit dans
les sources de test de son module, invisibles d'ici, et publier un artefact de test pour trente lignes
couterait plus que de les ecrire. Le jour ou un troisieme module en aurait besoin, ce calcul change.

**Perimetre derive (`ADR-0013` obligation 1)** : les modules viennent du pom agregateur, les paquets du
balayage de `contenu/` sous les ressources servies de ces modules, les feuilles du balayage de
`static/` hors `static/vendor/` (`ADR-0014`), les rangs de colonne des selecteurs eux-memes. Aucun nom
de module, de paquet, de feuille ni de rang n'est ecrit comme objet a controler.

Le perimetre est volontairement **plus large que le catalogue**, et c'est le choix de conception de cet
`ADR`. `ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest` (`ADR-0023`) prend l'inverse pour une
raison ecrite et juste : `nombreDeManches` n'existe que dans l'entree de catalogue. Ici, le nombre de
propositions existe des que le paquet existe, et l'appartenance au catalogue est une decision produit
qui a change quatre fois dans ce depot (`DEC-0009`, `DEC-0014`, `DEC-0015`). Le chiffre le montre : le
catalogue d'aujourd'hui ne sert que trois colonnes au plus, le paquet a quatre colonnes est hors
catalogue et **ses deux conditions de remise sont remplies**. Un perimetre catalogue aurait donc ete
aveugle a la seule colonne qui existe, dans la direction qui compte.

**Derivation totale (obligation 2)** : un paquet dont le nombre de propositions n'est lisible ni par
ses items ni par `parametres.nombreDePropositions` est un **defaut**, pas une valeur ignoree. Un rang
qui n'est pas un entier litteral (`2n+1`, `odd`) ne compte pas comme couvert. Un retard declare sous
`@keyframes` ou sous `prefers-reduced-motion` ne compte pas non plus : dans ce dernier contexte aucune
animation ne tourne, donc il ne couvre rien. Le doute joue dans le sens du controle.

**Non-vacuite (`ADR-0003`)** : deux assertions explicites, l'une exigeant un paquet capable d'au moins
deux colonnes, l'autre au moins un retard declare sur un rang. Le jour ou le rouleau de S7-1
disparaitra, c'est le second message qui le dira, plutot qu'un vert silencieux.

### Preuve de morsure - quatre mutations reelles

Chacune rejouee sur la **version livree** du garde-fou puis annulee ; l'arbre de travail ne porte
aucune de ces modifications, `git status` le confirme. Les trois premieres mutent des fichiers de
production, la quatrieme mute le garde-fou lui-meme.

| Mutation | Resultat attendu | Resultat mesure |
|---|---|---|
| la ligne `:nth-child(4)` retiree de `recreo.css` | rouge sur la colonne 4 | **rouge**, message : "colonne 4 : aucun animation-delay ne la nomme, alors que le paquet cm1-conjugaison-present.yaml peut afficher 4 colonnes. Le dernier rang couvert est le 3, a 0.16s". C'est aussi la mesure qui **etablit le plafond de 4 et sa source** : la couverture des rangs 2 a 4 n'a **aucun jeu** aujourd'hui, une seule des trois lignes retiree la fait tomber |
| un quatrieme distracteur ajoute a un item **reel** de `cm1-conjugaison-present.yaml` | rouge sur la colonne 5 | **rouge** : "colonne 5 : aucun animation-delay ne la nomme, alors que le paquet cm1-conjugaison-present.yaml peut afficher 5 colonnes". C'est le trou de s07, ferme, et prouve sur du materiau reel : une seule ligne de YAML suffit a le produire, ce que S7-2 autorise explicitement |
| la progression **prolongee** : `:nth-child(5)` a `0.32s` ajoutee a `recreo.css` | rouge sur la borne, dans `adapter-web` | **rouge** sur la clause 6 : "recreo.css : [data-arrivee=par-un-geste] .proposition:nth-child(5)::after : retard de 0.32s, au-dela du decalage maximal de 0.25s". La seconde machoire de la pince, mesuree elle aussi et non supposee |
| le filtre de contexte de mouvement reduit **supprime** du garde-fou (mutation du garde-fou, pas de la production) | le jeu fautif `cinq-colonnes` cesse de rougir | **`unPaquetAUneColonneDePlusQueLaFeuilleFaitEchouerLeGardeFou` en echec** : le retard que ce jeu fautif declare **sous `prefers-reduced-motion`** se met a compter comme couverture, et un paquet a cinq colonnes redevient vert. Le filtre est donc porteur, et ce n'est pas une precaution decorative |

**Mise a l'epreuve sur des objets neufs (obligation 3)**, trois jeux d'artefacts sous
`bootstrap/src/test/resources/contreexemple/ChaqueColonneQueLeContenuPeutProduirePorteSonDecalageTest/` :

- `cinq-colonnes` : un paquet a cinq propositions, servi avec le bloc du rouleau **recopie tel que S7-1
  l'a livre, valeurs comprises**. Il porte en outre un piege pose expres a mon propre filtre : la
  cinquieme colonne **est** nommee, mais sous `@media (prefers-reduced-motion: reduce)`, c'est-a-dire
  dans le seul contexte ou aucune animation ne tourne. Le garde-fou doit rester rouge malgre elle -
  sans ce bloc, le filtre de contexte serait vert a vide et personne ne le saurait ;
- `cinq-colonnes-corrigee` : le meme paquet, avec un decalage **revu** (pas de 0,06 s, derniere colonne
  a 0,24 s) et non prolonge. Il doit redevenir vert. Sans ce cas, la clause serait une interdiction de
  la cinquieme colonne deguisee en clause de couverture, et aucun jeu fautif ne le dirait - la lecon de
  la seconde mutation d'`ADR-0025`. Ce fichier n'est pas une proposition de design : il prouve
  seulement que la clause est satisfiable a cinq colonnes ;
- `paquet-illisible` : une troisieme forme de paquet, qui ne declare son nombre de propositions ni par
  des items ni par `parametres`. Il fait rougir la derivation totale. Il embarque aussi un paquet
  lisible, sans quoi le garde-fou s'arreterait sur sa propre non-vacuite au lieu de rougir sur la forme
  inconnue - detail note parce que c'est exactement le genre de dependance entre cas qu'une mutation
  mesuree revele et qu'une mutation racontee cache.

### Pas d'epinglage a plafond zero, et pourquoi je le dis

J'ai considere l'epinglage - une assertion figeant le plafond de colonnes a 4 pour rougir le jour ou il
bouge. Je le refuse : il rougirait sur **l'arrivee legitime d'un contenu**, c'est-a-dire sur le travail
de `camille-po` et de `nadia-prof`, alors que la clause de couverture rougit sur ce qui manque
reellement - la regle de la colonne neuve. Le motif de l'epinglage est bon quand une population est
vide ; celle-ci ne l'est pas, elle vaut 4 et la clause s'y confronte a chaque `verify`. C'est le meme
raisonnement que `lea-dev` a applique en s07 en **consommant** un epinglage devenu inutile ; ici, il
n'y a pas lieu d'en ouvrir un.

### Pourquoi ce n'est pas une `ArchRule`

ArchUnit lit du bytecode. Ni un paquet YAML ni une feuille de style n'y sont visibles. Le compte de
regles ArchUnit du depot est inchange a **28**, et cet `ADR` n'en revendique aucune.

## Consequences

- Le trou nomme a la cloture de s07 est ferme. Une cinquieme colonne ne peut plus arriver en silence :
  elle arrive avec un rouge qui nomme le paquet, le rang manquant, le dernier rang couvert et sa
  valeur, et qui route le choix de forme a `ines-design` sous arbitrage de `nadia-prof`.
- **Mauvaise consequence assumee** : ce garde-fou rougira le jour ou un paquet a cinq propositions
  entrera, meme si personne ne veut encore parler d'animation ce jour-la. C'est voulu - c'est
  exactement le moment ou la question doit se poser - mais cela veut dire qu'un lot de contenu peut
  etre bloque par une question de mouvement. Le deblocage est court : revoir le pas du decalage, ou
  ramener le paquet a quatre propositions.
- Un paquet d'une forme de schema encore inconnue fera rougir ce garde-fou au lieu de sortir de son
  perimetre. C'est le bon sens de defaillance, et c'est une contrainte reelle sur qui ajoutera un
  troisieme type de paquet : il devra dire combien de colonnes il produit.
- Trente lignes de decoupage CSS existent en double dans le depot. Le declencheur pour reprendre ce
  choix est ecrit : un troisieme module qui en aurait besoin.
- **Zero octet de ce que voit un enfant ne change par cet `ADR`** : aucun fichier de production n'est
  touche, ni feuille, ni gabarit, ni paquet.

## Alternatives considerees

**Deriver la population du catalogue plutot que des paquets embarques.** Ecartee, chiffre a l'appui :
le catalogue ne sert que trois colonnes au plus aujourd'hui, la quatrieme vit dans un paquet hors
catalogue dont les deux conditions de remise sont remplies. Ce perimetre aurait ete aveugle a la seule
colonne qui existe.

**Recopier la borne de 250 ms dans ce garde-fou pour qu'il dise toute la propriete a lui seul.**
Ecartee : deux exemplaires d'un seuil divergent un jour, et le seuil appartient a `nadia-prof`, pas a
moi. Chaque clause garde une chose ; c'est la pince qui garde la propriete, et chaque moitie nomme
l'autre dans son message et dans sa Javadoc.

**Exiger une progression strictement croissante des retards.** Ecartee, motif ecrit plus haut : ce
serait encoder une forme visuelle dont je ne suis pas l'auteur, et rendre rouge une refonte legitime.

**Rendre le decalage borne par construction** - un budget total reparti sur le nombre de colonnes
plutot qu'un pas par colonne. C'est la bonne reponse technique et elle supprimerait le probleme a la
racine. Je l'ecarte **aujourd'hui**, et non sur le principe : elle change ce qu'un enfant voit, sur une
population qui n'existe pas encore (aucun paquet a cinq propositions dans le depot, mesure ci-dessus),
et `nadia-prof` a valide le mouvement livre sous une seule reserve chiffree qui ne porte pas sur le
decalage. `ADR-0025` a deja refuse de modifier la mise en page servie a un enfant pour satisfaire un
automate ; je ne vais pas le faire dans le fichier qui le cite. **Ce qui me ferait changer d'avis** :
un premier paquet a cinq propositions retenu par `camille-po`, ou une reserve de `nadia-prof` sur le
decalage lui-meme. Le garde-fou de cet `ADR` est precisement ce qui garantit que ce jour-la, la
question sera posee.

**Ne rien ecrire et laisser l'obstacle en dette nommee.** Ecartee : il est deja reste ouvert un sprint,
et un obstacle qui traverse deux sprints sans decision est une decision prise par defaut.

## Verification

`mvn -B clean verify` : **BUILD SUCCESS**, six modules, **319 tests**, **28 `ArchRule`** inchange.
Chiffres et methode en entier dans la section Verification d'`ADR-0028`, meme commit - ils ne sont pas
recopies ici pour ne pas exister en deux exemplaires.

`ChaqueColonneQueLeContenuPeutProduirePorteSonDecalageTest` : **4 cas, tous verts**, et les quatre
mutations du tableau ci-dessus rejouees sur la version livree puis annulees.

Le plafond de colonnes mesure aujourd'hui vaut **4**, atteint par `cm1-conjugaison-present.yaml`, et
la feuille couvre exactement les rangs 2, 3 et 4 - **aucun jeu**. Ce n'est pas une lecture du code mais
la sortie de la premiere mutation, qui nomme le paquet et le chiffre.
