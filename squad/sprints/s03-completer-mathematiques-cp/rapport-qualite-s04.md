# [Sprint s04] Rapport qualite - yanis-qa

## Anomalie de gouvernance, dite avant le contenu

**S04 n'a ni dossier de sprint, ni `planning.md`, ni `avis-faisabilite.md`, ni aucune ceremonie
ecrite.** C'est la deuxieme fois apres s02 qu'un sprint se deroule ainsi, sur demande directe de
l'utilisatrice - `DEC-0008` documente la decision produit, mais aucun fichier de sprint ne
documente le deroulement. Je ne fabrique pas les ceremonies manquantes : ce rapport ne pretend pas
qu'un planning ou un avis de faisabilite ont existe. Je le classe provisoirement dans le dossier de
`s03`, faute d'un dossier `s04` a ce jour - meme choix que `relecteur-pedagogique`
(`squad/sprints/s03-completer-mathematiques-cp/relecture.md`, qui couvre aussi s04 pour la meme
raison). `sofia-sm` creera le dossier `s04` a la cloture ; ce fichier est ecrit pour etre deplace
ou reference a ce moment-la, pas pour rester ici.

- **Commit audite** : `4c62f60` (`HEAD` de `round-two`).
- **Perimetre livre** : S4-1 (homophones grammaticaux CM1) et S4-2 (graphemes/phonemes CP),
  `docs/backlog/backlog.md`, section "Sprint 4". Livre par `lea-dev` (`a6c416c`), fusionne par
  `sofia-sm` (`4c62f60`) apres le lot Bootstrap d'`ines-design` (`d5451b9`).
- **Aucun Done pedagogique n'est prononce ici.** Ce rapport ne juge que le Done technique. Les deux
  paquets francais n'ont pas encore ete audites par `nadia-prof` ; `relecteur-pedagogique` en a fait
  la relecture de redaction (`relecture.md`), pas le jugement pedagogique de fond.

## 1. `mvn verify` - vert, constate deux fois, JDK 21.0.10 reel

Meme mesure que le rapport de s03 (section 5 de `rapport-qualite.md`), sur le meme commit : deux
executions completes et independantes de `mvn -B clean verify` depuis la racine, `JAVA_HOME` sur
le JDK 21.0.10 reel du poste (verifie par `java -version` avant chaque commande, pas le JDK 25
qu'un fichier de toolchains mal nomme avait fait utiliser a tort en s03). **BUILD SUCCESS, 180
tests, 0 echec, les deux fois.** Detail par module au tableau de la section 4 ci-dessous, identique
au tableau de la section 11 du rapport de s03 puisque c'est le meme arbre.

Une premiere execution, avant ces deux-la, a echoue en compilation de test sur un module etranger
a s04 (`recreo-application`) et n'a pas ete reproduite depuis - traitee comme instabilite nommee
dans la section 10 du rapport de s03, pas ici, puisqu'elle ne touche aucun code de s04.

## 2. Tracabilite S4-1, S4-2, et la preuve de decouplage exigee par `DEC-0008`

Le detail critere par critere est ecrit dans `rapport-qualite.md` de s03, section 7, pour eviter de
le dupliquer ici (les deux stories sont dans le meme commit et je ne veux pas que les deux versions
divergent silencieusement si l'une est corrigee). Le resume qui importe le plus pour ce sprint :

**`DEC-0008` demandait de prouver, pas d'affirmer, que le francais entre au catalogue sans toucher
`domain/mechanic` ni `domain/mechanics`.** Verifie au diff, pas cru sur parole :

```
git diff --stat 9af191c..4c62f60 -- domain/
```

Sortie vide - aucun fichier sous `domain/` dans tout l'intervalle qui va de la fin du lot
`marc-dev` de s03 a la fusion du lot francais de s04, Bootstrap inclus. Le commit de `lea-dev` seul
(`a6c416c`) ne touche que quatre fichiers, tous sous `adapters/adapter-content` : `catalogue.yaml`,
`contenu/cm1-homophones.yaml`, `contenu/cp-le-bon-son.yaml`, et le test
`PaquetsFrancaisS04Test.java`. Les deux paquets declarent `type: question`, le schema deja connu
d'`ADR-0007` - aucun schema neuf non plus. **La demonstration tient**, et c'est la premiere fois
qu'elle est faite sur du contenu livre en production plutot que sur un test de laboratoire (le test
`domain`, `BanditManchotTest.laMemeMecaniqueJoueDeuxContenusDifferentsSansAucunChangementDeCode`,
prouvait deja le principe depuis S0-3, mais avec un contenu fabrique pour le test).

Chaque critere testable des deux stories a un test qui la nomme (`PaquetsFrancaisS04Test`, plus la
boucle derivee de `CatalogueChargeableIT` qui demarre une partie sur chaque jeu du catalogue reel,
les deux jeux francais inclus sans etre cites nommement). Le detail complet, story par story et
critere par critere, est dans le rapport de s03, section 7.

## 3. Contenu francais - ce que j'ai verifie et ce qui reste hors de mon mandat

`PaquetsFrancaisS04Test` verifie la **forme** du contenu (couples autorises, distracteurs, variete,
couverture des couples et des sons cites par le referentiel) - pas son **fond** pedagogique. Deux
choses distinctes que je ne confonds pas :

- La relecture de redaction (accents, orthographe, ponctuation) est faite par
  `relecteur-pedagogique` dans `relecture.md`, pas par moi.
- Le jugement pedagogique (niveau de vocabulaire adapte au CM1, lisibilite de la notation `[an]`
  pour un enfant de CP qui debute en lecture, choix des mots) reste **entierement** a `nadia-prof`,
  qui n'a pas encore audite ces deux paquets. Aucun des criteres testables ci-dessus n'y pretend.

## 4. Compte de tests reel par module

Identique au tableau de la section 11 du rapport de s03 (meme commit, meme arbre) :

| Module | Tests |
|---|---|
| `recreo-domain` | 38 |
| `recreo-application` | 7 |
| `recreo-adapter-web` | 66 |
| `recreo-adapter-content` | 10 (dont 4 dans `PaquetsFrancaisS04Test`, le seul test neuf de s04) |
| `recreo-bootstrap` (unitaires) | 57 |
| `recreo-bootstrap` (integration) | 2 |
| **Total** | **180** |

Sur ces 180, **4 sont neufs pour s04** (`PaquetsFrancaisS04Test`) ; le reste appartient a s03 et
aux sprints precedents, execute sans regression.

## 5. Ce que ce rapport ne couvre pas

- Aucun planning ni avis de faisabilite n'existent pour ce sprint : voir l'anomalie en tete de ce
  rapport, je ne les fabrique pas.
- Aucune confirmation ArchUnit dediee a s04 : la preuve de decouplage (section 2) est la seule
  chose qu'une regle ArchUnit ne peut pas voir a elle seule (elle porte sur un diff temporel, pas sur
  un etat statique du code) - c'est pourquoi elle est faite ici, au diff, plutot que dans une regle.
- Aucun Done pedagogique : seule `nadia-prof` le prononce, sur les deux paquets francais comme sur
  n'importe quel autre contenu.
