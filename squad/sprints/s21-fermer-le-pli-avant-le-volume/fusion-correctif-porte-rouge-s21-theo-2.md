# S21 - fusion du correctif de la porte rouge sur round-two (s21-theo-2)

Une branche fusionnee sur `round-two`, `s21-theo-2` (`369e690`). Ceremonie de fusion, pas une
review : le sprint n'est clos qu'a la review, qui verse ce journal. L'obstacle corrige ici est
celui que j'avais moi-meme route a `theo-archi` dans `fusion-lots-4-a-10.md` (tableau des
obstacles, amendement du 2026-08-27, seconde vague) : `lateralDeclare`
(`LeConteneurTierGardeSonRembourrageLateralTest.java`) n'avait pas ete converti par `S21-12`, et
`mvn verify` restait rouge sur `round-two` fusionne.

## Verification de provenance

```
bash squad/outils/sofia-sm/verifier-provenance-lots.sh "s21-theo-2=369e690"
```

```
branche 's21-theo-2' : lignee confirmee, un ancetre commun avec round-two existe.
tous les lots verifies descendent de round-two avec la tete annoncee.
```

**Lignee reelle, verifiee au-dela du script** (retro s18, point 3). `git merge-base round-two
s21-theo-2` rend `9ace409` : c'est exactement la tete de `round-two` au moment de la fusion, pas un
point en retard. La branche est un unique commit repose directement sur l'etat fusionne complet -
pas un troisieme worktree parti d'un etat plus ancien.

## Fusion

`git merge --ff-only s21-theo-2` sur `round-two` : `Fast-forward`, `9ace409..369e690`, aucun
conflit possible - un seul commit, une seule branche, base = tete exacte. `round-two` local est
maintenant a `369e690`, non pousse.

## Ce que corrige le commit

Diff verifie par lecture (`git show 369e690`) : douze fichiers de test dans `adapter-web`
convertis a un `valeurDeclaree(corps, propriete)` sans parametre de position (calcule la
declaration gagnante lui-meme), deux copies locales du lecteur de position supprimees, un fichier
`correctif-porte-rouge-troisieme-appelant.md` ajoute. Aucun fichier de contenu ni de feuille de
style dans le diff.

`theo-archi` n'a pas converti le seul troisieme appelant identifie dans mon obstacle : il a retire
la piece qui permettait a un appelant de se tromper (le parametre de position), au motif nomme
dans son fichier qu'un quatrieme appelant aurait pu naitre demain, vert sur sa branche et rouge a
la fusion. Consequence verifiee par lecture du diff : 26 appels dans 12 classes, nommes et
convertis par le compilateur - un appelant oublie devient desormais une erreur de compilation sur
la branche qui l'ecrit, pas un rouge de CI a la fusion.

## Porte technique, mesuree par moi-meme sur l'etat fusionne

```
bash outils/mvn-jdk21.sh -B clean verify
```

A la racine du reacteur (six modules, aucun `-pl`), le 2026-08-27, sur `round-two` a `369e690`.
`BUILD SUCCESS`, code de sortie 0, `Total time: 01:29 h`, `Finished at: 2026-08-27T20:13:42+02:00`.
Blocs `Results:` releves dans le journal de cette commande, jamais deduits d'un total :

| Module | Bloc `Results:` |
|---|---|
| `recreo-domain` | `Tests run: 49, Failures: 0, Errors: 0, Skipped: 0` |
| `recreo-application` | `Tests run: 13, Failures: 0, Errors: 0, Skipped: 0` |
| `recreo-adapter-web` | `Tests run: 332, Failures: 0, Errors: 0, Skipped: 0` |
| `recreo-adapter-content` | `Tests run: 224, Failures: 0, Errors: 0, Skipped: 0` |
| `recreo-bootstrap` (surefire) | `Tests run: 98, Failures: 0, Errors: 0, Skipped: 0` |
| `recreo-bootstrap` (failsafe) | `Tests run: 6, Failures: 0, Errors: 0, Skipped: 0` |
| **total, somme des six blocs** | **722** |

Chiffre mesure par moi-meme sur mon propre checkout, pas recopie du fichier de `theo-archi` : les
six blocs et le total coïncident avec ceux qu'il annonce dans
`correctif-porte-rouge-troisieme-appelant.md`, section 6. C'est le premier total mesure du sprint
entier - jusqu'ici le reacteur s'arretait avant `adapter-content`, donc les trois paquets neufs de
`marc-dev`, `lea-dev` et `sofia-sm` n'avaient jamais ete mesures ensemble sur un `mvn verify`
complet.

`mvn verify` sur `round-two` etait rouge avant cette fusion (2 echecs sur 332, constate dans
`fusion-lots-4-a-10.md`) ; il est vert apres, sur les six modules, verifie ci-dessus. Le reacteur
est libre.

## Constat de produit, route par `theo-archi`, verifie par moi-meme sur le CSS servi

Le 2026-08-27, lecture de `adapters/adapter-web/src/main/resources/static/css/recreo.css` sur
`round-two` a `369e690` : `.proposition` porte deux declarations de `padding` (ligne 683, partagee
avec `.recommencer` et `.sortie-pleine`, `0.75rem 1.25rem` ; ligne 737, propre a `.proposition`,
`0.5rem 1.25rem`) - la seconde gagne la cascade par ordre de source, a specificite egale. `.lien-
accueil` (ligne 156) et `.retour-accueil` (ligne 869) portent chacun une seule declaration,
`0.5rem 1.1rem`.

L'action (`.proposition`, `0.5rem 1.25rem`) et la sortie (`.lien-accueil`/`.retour-accueil`,
`0.5rem 1.1rem`) portent le meme rembourrage vertical (`0.5rem`) ; l'action en porte plus a
l'horizontale. L'action ne declare pas moins que la sortie : `ADR-0017` tient. C'est la premiere
fois que `LActionNeDeclarePasMoinsQueLaSortieTest` le dit en ayant lu la declaration qui gagne
reellement la cascade sur `.proposition`, et non la premiere des deux (le defaut de lecture corrige
par ce meme commit).

Cela confirme, sans les rouvrir, les conditions `SOR-1` a `SOR-3` etablies par `ines-design`
(`mesure-ines-sor1-a-sor4.md`) : rembourrage de sortie au plus egal a l'action, plancher tactile de
2,75rem tenu, `.retour-accueil` et `.lien-accueil` memes boites. `SOR-4` - la sortie reste
atteignable du premier coup - n'est pas de mon ressort : elle reste a verifier par `nadia-prof` en
jouant l'increment, comme `mesure-ines-sor1-a-sor4.md` le nomme deja.

## Dette nommee, non corrigee, portee au tableau des obstacles

`ChaqueColonneQueLeContenuPeutProduirePorteSonDecalageTest` (module `bootstrap`) porte son propre
lecteur prive de feuille de style, independant de celui d'`adapter-web`. Verifie par lecture
(`ChaqueColonneQueLeContenuPeutProduirePorteSonDecalageTest.java`, ligne 313) : il trouve la
propriete `animation-delay` par premiere occurrence de sous-chaine (`texte.indexOf(propriete)`),
sans meme exiger un debut de declaration - meme famille de trou que celle que ce commit vient de
fermer dans `adapter-web`, en plus permissif. Vert aujourd'hui : aucune regle du depot ne declare
`animation-delay` deux fois. `theo-archi` ne le corrige pas au point de passage partage sans usage
reel constate ; declencheur ecrit dans son fichier.

Route au tableau des obstacles de `planning.md`, destinataire `theo-archi`, categorie technique -
garde-fou, meme ligne que le mode de defaillance qui vient d'etre corrige ailleurs dans le meme
module de test.

## Lecon de methode, proposee pour la retro (pas tranchee ici)

`theo-archi` ecrit contre lui-meme, dans `correctif-porte-rouge-troisieme-appelant.md` section 3 :
son recensement des appelants fautifs par execution de la suite - methode qu'il tient pour la
bonne - a quand meme rate ce cas, parce que le materiau qui le revele (`.proposition` avec deux
declarations de rembourrage) n'existait sur aucun des deux arbres pris separement : `s21-theo`
portait le lecteur strict sans le materiau, `round-two` d'avant sa fusion portait le materiau sans
le lecteur strict. Seul l'etat fusionne portait les deux a la fois.

Il rapproche cela du `-pl` sans `-am` de `CLAUDE.md` : un chiffre juste, mesure sur un autre arbre
que celui qu'on croit mesurer. Sa proposition, qu'il route et ne tranche pas : quand un lot durcit
un lecteur **partage** entre plusieurs garde-fous, le mesurer sur l'union des arbres vivants du
sprint, pas sur le sien seul. Je la porte telle quelle a la retro de ce sprint, ou elle sera
tranchee comme amendement eventuel a `squad/README.md` - je ne l'ecris pas en amendement moi-meme
avant que la ceremonie n'ait eu lieu.

## Ce qui reste hors du perimetre de cette fusion

- Les 4 items sans levier connu et les 122 items sur 348 encore sous le pli : hors perimetre de
  s21, routes a `camille-po` (planning, decision 4).
- `SOR-4` au sens plein (atteignabilite au premier coup) : reste a `nadia-prof`, en jouant.
- La dette du lecteur prive de `bootstrap` : nommee ci-dessus, non corrigee par choix de
  `theo-archi`, declencheur ecrit.
- La lecon de methode de la section precedente : proposee, pas tranchee - la retro le fera.

## Etat de `round-two` apres cette fusion

`round-two` local est a `369e690`, non pousse. Porte technique verifiee vraie a la racine : `mvn
verify` `BUILD SUCCESS`, 722 tests sur six blocs `Results:`, 0 echec, mesure par moi-meme le
2026-08-27. Reacteur libre. `nadia-prof` n'a prononce aucun Done pedagogique sur `cp-syllabes`,
`cp-mot-manquant` ni `cp-comparer`, et `SOR-4` reste a verifier en jouant : le sprint n'est pas
clos, la review reste a animer.
