# ADR-0010 - Refuser les chemins de poste de travail et les comptes utilisateur dans un fichier suivi, par un script teste

- **Statut** : Accepte
- **Date** : 2026-08-05
- **Auteur** : theo-archi
- **Sprint** : s00

## Contexte

Les artefacts de ceremonie du sprint 0 ont cite des chemins qui n'appartiennent qu'au poste de
travail ou l'agent tournait : le chemin du JDK 21 dans les commandes `JAVA_HOME=... mvn -B
verify` de `review.md`, `rapport-qualite.md` et `confirmation-archunit.md` ; le chemin de
l'interpreteur Python dans `squad/README.md`, `RAPPORT.md` et la definition d'agent de
`sofia-sm` ; le chemin encode du depot local dans `squad/README.md` et dans la docstring de
`outils/rapport-tokens.py`. Le depot est pousse sur GitHub. Tout cela a ete retire, et l'historique
de `round-two` a ete reecrit pour que les anciens commits n'en portent plus non plus.

Une regle a ete ajoutee aux "Conventions de redaction" de `CLAUDE.md` : aucun chemin absolu de
poste de travail, aucun nom d'utilisateur, aucune credential dans un fichier commite. Mais le
process demande a chaque agent de citer la commande exacte qu'il a lancee (`squad/README.md`,
`squad/TEMPLATES.md`, et l'etape 4 de la section 9 pour ma propre confirmation ArchUnit). Le
prochain rapport de ceremonie reintroduira un chemin, pour la meme raison structurelle qu'ADR-0009
a deja nommee une fois : une regle ecrite dans un document n'est pas un garde-fou.

Cette decision applique donc le principe general de l'ADR-0009 ("un garde-fou de CI qui rend un
verdict est extrait dans un script versionne, et ce script a ses contre-exemples") a une nouvelle
propriete : ce n'est pas la meme regle generalisee (les sujets de commit contre le contenu de
fichiers sont deux objets differents, et le motif refuse est neuf), donc un nouvel ADR plutot
qu'un amendement d'ADR-0009. ADR-0009 reste la reference pour la forme du garde-fou ; ADR-0010
tranche son contenu.

Trois difficultes propres a cette regle-ci, distinctes de celles d'ADR-0009 :

1. **La regle de `CLAUDE.md` cite elle-meme les motifs interdits** (`` `C:\...` ``, `` `/c/...` ``)
   pour les nommer. Un garde-fou naif refuserait le fichier qui enonce l'interdit.
2. **Les fixtures d'un garde-fou qui juge du contenu de fichier sont, par construction, du contenu
   de fichier.** Le garde-fou de prefixes (ADR-0009) juge des sujets de commit transitoires : ses
   contre-exemples n'existent jamais comme contenu commite qu'il pourrait lui-meme juger. Ici,
   l'objet juge est le contenu des fichiers suivis, donc les contre-exemples de refus - qui
   doivent contenir un vrai `C:\<compte>\...` pour prouver que le garde-fou mord - sont eux-memes
   du contenu suivi. Un garde-fou qui balaierait tout le depot, fixtures comprises, se refuserait
   lui-meme.
3. **`contenu-repris/` contient 61 paquets YAML repris de `main`**, jamais relus ligne a ligne :
   verifie avant de decider s'ils entrent dans le perimetre.

Sur ce troisieme point : `git grep` sur `contenu-repris/` avec le motif de detection ne trouve
aucune occurrence. Le paquet entre donc dans le perimetre du garde-fou sans exclusion, plutot que
d'etre ecarte par precaution sur la base d'une supposition.

## Decision

**Un script verdict, `.github/scripts/verifier-chemins.sh`, lit une liste de chemins de fichiers
sur l'entree standard et refuse toute ligne qui contient une forme non portable :**

- chemin Windows a lettre de lecteur : `C:\<compte>\...`, `C:/<compte>/...` (n'importe quelle
  lettre, pas seulement `C`) ;
- la meme chose sous sa forme Git Bash : `/c/<compte>/...` ;
- repertoire personnel Unix nomme : `/home/<compte>/...` ;
- repertoire personnel macOS nomme : `/Users/<compte>/...`.

Une occurrence n'est retenue que si la lettre de lecteur ou le repertoire est suivi d'un **vrai
segment de chemin** (un caractere alphanumerique ou underscore). C'est le traitement du piege 1 :
plutot qu'une exception codee en dur sur le nom du fichier `CLAUDE.md`, la regle de detection
elle-meme n'accroche que sur un chemin qui continue reellement. Citer le motif sans le faire
suivre d'un segment reel (`` `C:\...` ``, `` `/c/...` ``, exactement la forme que `CLAUDE.md`
emploie) n'est pas une occurrence : c'est nommer l'interdit, pas le commettre. Un chemin
authentique, meme tronque pour la lisibilite (`C:\<compte>\...`), reste refuse parce que son
premier segment reel est present.

`$HOME`, `$JAVA_HOME`, `~/.m2/settings.xml`, `%USERPROFILE%` passent : aucun ne contient une lettre
de lecteur suivie de `:` et d'un separateur, ni un repertoire personnel nomme en dur. C'est la
variable qui est bonne, sa valeur figee qui est mauvaise, et c'est exactement ce que le script
distingue.

**Le choix des fichiers examines vit dans le workflow, pas dans le script**, sur le meme partage
des responsabilites qu'ADR-0009 : `.github/workflows/ci.yml` fournit `git ls-files` (tous les
fichiers suivis) au script, en excluant nommement
`.github/scripts/verifier-chemins-test.sh`. C'est le traitement du piege 2 : les contre-exemples de
refus du script de test ne sont jamais du contenu que le garde-fou juge en production, parce qu'ils
sont ecrits dans des fichiers temporaires crees a l'execution du test (`mktemp`) et non dans le
contenu suivi que `git ls-files` renvoie - a l'exception du fichier de test lui-meme, dont le
**code source** contient necessairement ces fixtures en clair pour les ecrire, et qui doit donc
etre exclu du perimetre juge. C'est une exemption nommee, motivee (un fichier dont l'unique
raison d'etre est de citer le motif interdit pour prouver que le garde-fou le refuse, jamais de le
commettre), et etroite (un seul fichier, pas un motif ni un repertoire).

`contenu-repris/` n'est **pas** exclu : verifie sans occurrence (contexte ci-dessus), il reste dans
le perimetre pour que l'ajout futur d'un paquet contenant un chemin de poste de travail soit
refuse comme n'importe quel autre fichier.

## Regle executable ArchUnit

Comme pour ADR-0009, la decision n'est pas encodable en ArchUnit : ArchUnit lit du bytecode Java,
pas du contenu de fichier arbitraire. Le mecanisme automatique qui la garde est le script de
contre-exemples, invoque par la CI avant le garde-fou lui-meme.

Fichiers :
- `.github/scripts/verifier-chemins.sh` - le verdict, ligne par ligne, sur une liste de fichiers
  reçue sur l'entree standard.
- `.github/scripts/verifier-chemins-test.sh` - vingt cas, dont huit refus et douze acceptations.
- `.github/workflows/ci.yml`, job `chemins` - teste le garde-fou, puis l'applique a
  `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh'`.

Repartition des cas :

| Categorie | Refus | Acceptations |
|---|---|---|
| Chemin Windows (lettre de lecteur, antislash et slash, autre lettre que `C`) | 3 | - |
| Forme Git Bash (`/c/...`) | 1 | - |
| `/home/<compte>/...` | 1 | - |
| `/Users/<compte>/...` | 1 | - |
| Deux occurrences dans un meme fichier, comptees toutes les deux | 1 | - |
| Dernier motif sans retour a la ligne final | 1 | - |
| Citation du motif sans segment reel (`C:\...`, `/c/...`, `/home/...`) | - | 3 |
| Formes portables (`$HOME`, `$JAVA_HOME`, `~/.m2/...`, `%USERPROFILE%`) | - | 4 |
| URL avec segment d'une lettre ou schema `://` non pris pour un chemin | - | 2 |
| Fichier sans occurrence, aucun fichier a juger, fichier disparu de la liste | - | 3 |

Un cas attendu en echec verifie, comme pour ADR-0009, le code de sortie **et** le motif de sortie
(le prefixe `chemin de poste de travail ou compte utilisateur dans` ou le compteur
`N occurrence(s)`) : un `AssertionError` gratuit ne suffirait pas plus ici qu'ailleurs.

**Preuve de non-vacuite par mutation**, sur le modele d'ADR-0003 et d'ADR-0009 :

| Mutation du garde-fou | Cas qui devient rouge |
|---|---|
| le segment reel n'est plus exige apres la lettre de lecteur Windows | citer `C:\...` sans segment reel |
| le contexte n'est plus exige devant la forme Git Bash | l'URL avec un segment d'une lettre |
| le segment reel n'est plus exige apres la forme Git Bash | citer `/c/...` sans segment reel |
| le segment reel n'est plus exige apres `/home/` | citer `/home/...` sans segment reel |
| `/home/` devient `/Home/` | repertoire personnel Unix nomme est refuse |
| `/Users/` devient `/users/` | repertoire personnel macOS nomme est refuse |
| la forme Git Bash retiree de l'alternative jointe | forme Git Bash est refusee |
| le compteur de fautifs n'est jamais incremente | huit cas |
| `exit 1` retire | les huit memes cas |

Une mutation testee **n'a pas mordu** et je le dis plutot que de l'affirmer : retirer le contexte
devant la forme Windows (`[A-Za-z]:[\/]`) ne fait rougir aucun cas. La raison, verifiee et non
supposee : un chemin Windows authentique exige un separateur `:` suivi **directement** d'un slash
ou antislash, et je n'ai trouve aucun exemple naturel de prose ou de code du depot ou une lettre
isolee, precedee d'un caractere alphanumerique, serait suivie de `:` puis d'un unique slash puis
d'un caractere reel sans qu'il s'agisse deja d'un vrai chemin. Le contexte reste dans le script par
coherence (une seule variable `CTX` partagee par les quatre motifs, plus simple a lire que quatre
regles ad hoc) et par defense en profondeur, mais je ne pretends pas qu'il morde ici comme il morde
demontrablement sur la forme Git Bash (ou une URL `http://a/b` en fournit un contre-exemple reel).

**Une defaillance trouvee et corrigee en cours de route, non anticipee** : sous la locale
`en_US.UTF-8` (courante sur un poste de developpeur, differente de la locale `C`/`POSIX` habituelle
d'un conteneur CI), GNU grep 3.0 rate silencieusement des occurrences pourtant reelles pour un motif
court a une seule alternative, alors que le motif complet a quatre alternatives les trouve. Ce
comportement disparait sous `LC_ALL=C`. Le script l'exporte au demarrage. Je ne peux pas prouver par
un cas de test qui rougit que ce correctif est necessaire sur le motif complet actuellement en
usage (il ne l'est pas, empiriquement, sur cette version de grep et ce motif precis) ; je le garde
neanmoins par prudence, documente comme tel, plutot que de laisser un comportement dont j'ai
constate qu'il varie avec la longueur du motif.

**Amendement du 2026-08-05 : le garde-fou a mordu son propre auteur, et c'est le piege que la
verification initiale n'avait pas couvert.** `verifier-chemins.sh` n'etait pas encore suivi par
git au moment ou j'ai lance `git ls-files | bash .github/scripts/verifier-chemins.sh` : le fichier
etait absent de la liste qu'il jugeait, donc son propre commentaire d'en-tete - deux exemples
Windows et Git Bash qui reprenaient le meme mot de repertoire reel employe dans les cas de test -
n'a jamais ete soumis a son propre motif. Une fois le fichier suivi (commit `e68a8e9`), le
garde-fou s'est refuse lui-meme, sur exactement le mecanisme qu'il implemente : un segment reel
apres le separateur. La logique de segment reel n'a pas ete assouplie pour laisser passer ce cas -
c'est le cas d'usage exact pour lequel elle a ete concue. Le commentaire a ete corrige en reprenant
le placeholder `<compte>` deja employe pour `/home/<compte>/...` et `/Users/<compte>/...` juste en
dessous, et que ce meme ADR emploie depuis sa version initiale.

La lecon depasse ce seul fichier : **un garde-fou de contenu de fichier ne peut etre verifie
correctement qu'apres que ses propres fichiers sont suivis par git.** Verifier son verdict sur
`git ls-files` avant son propre commit initial est un faux negatif structurel, pas une preuve. La
verification de non-regression de ce garde-fou doit desormais se relire une fois commise, pas
seulement sur l'arbre de travail.

## Consequences

- Le garde-fou de chemins est desormais executable et testable hors CI, comme celui de prefixes :
  `bash .github/scripts/verifier-chemins-test.sh` tourne en une seconde sur le poste d'un
  developpeur.
- Le garde-fou passe sur le depot suivi tel qu'il est aujourd'hui, verifie **apres** que ses propres
  fichiers y sont commis (voir l'amendement ci-dessus) : `git ls-files -- . ':!...test.sh' | bash
  .github/scripts/verifier-chemins.sh` rend "Aucun chemin de poste de travail ni nom de compte dans
  les fichiers verifies." Le nettoyage prealable de la ceremonie n'a rien laisse passer ; c'est le
  garde-fou lui-meme qui, une fois suivi, a d'abord trouve une occurrence dans son propre
  commentaire.
- Un seul fichier est exclu du perimetre, nommement et pour une raison ecrite : le script de test
  lui-meme. Toute nouvelle exclusion future devrait justifier la meme etroitesse - un repertoire ou
  un motif de nom de fichier plus large serait un signal d'alarme, pas un raccourci a prendre.
- La detection est syntaxique, pas semantique : elle ne sait pas qu'un compte s'appelle
  effectivement "alice" ou "clara" plutot que reconnaitre un nom precis - elle refuse la **forme**
  d'un chemin qui nomme une machine (lettre de lecteur, `/home/`, `/Users/`), quel que soit le nom
  qui suit. Un chemin de poste de travail qui n'emprunte aucune de ces quatre formes (par exemple
  un partage reseau `\\serveur\partage\...`) ne serait pas refuse ; aucune occurrence de ce genre
  n'a ete rencontree dans ce depot, donc pas de cinquieme motif ajoute par anticipation.
- Toute occurrence future de plus de deux caracteres reels apres `C:\` ou `/c/` reste refusee ; seul
  le cas exact de citation sans segment (`C:\...`, `/c/...`, `/home/...`) passe. Un redacteur qui
  citerait le motif interdit en faisant suivre la lettre de lecteur d'un vrai premier segment avant
  les points de suspension, plutot que des points de suspension nus, serait refuse - c'est voulu :
  ce n'est plus une citation du motif dans sa forme la plus depouillee, c'est un exemple avec un
  vrai segment, et la distinction entre "nommer l'interdit" et "l'illustrer avec un chemin qui
  ressemble a un vrai" n'a pas a etre tranchee par ce garde-fou.
- Optimisation deja necessaire, pas anticipee : un appel a `grep` par fichier (209 fichiers suivis
  au moment de l'ecriture) prend plus de vingt secondes sur ce poste Windows, cout de lancement de
  processus compris ; un seul appel a `grep -H` sur la liste complete des fichiers descend a moins
  d'une seconde. Corrige avant merge, pas laisse comme dette.
- `contenu-repris/` reste dans le perimetre. Si un paquet YAML repris contenait un jour un chemin
  de poste de travail, ce serait desormais visible en CI plutot que decouvert a la relecture.

## Alternatives considerees

- **Etendre `verifier-prefixes.sh` pour qu'il juge aussi les chemins.** Ecarte explicitement par la
  consigne : ce sont deux proprietes differentes (l'une sur des sujets de commit, l'autre sur du
  contenu de fichier), donc deux scripts et deux tests, comme pour toute paire de regles distinctes
  d'ADR-0009.
- **Exempter `CLAUDE.md` par son nom de fichier plutot que par la logique de segment reel.** Plus
  simple a ecrire, mais plus fragile : la prochaine fois qu'un autre fichier citerait le meme motif
  pour le documenter (ce README, un futur ADR qui reprend cet exemple), il faudrait ajouter une
  exemption de plus, et la liste des fichiers autorises a "mentir" grandirait sans lien avec une
  regle qu'on peut enoncer une fois. Ecarte pour la raison que la consigne donne explicitement.
- **Faire porter au script de test ses fixtures comme du contenu commite ordinaire, sans exclusion
  de fichier.** Testerait un cercle vicieux : le garde-fou en production se refuserait lui-meme des
  qu'il rencontrerait ses propres contre-exemples. Ecarte : c'est le meme type d'erreur que
  confondre les deux proprietes d'ADR-0009 (qui est juge / quel est le verdict), applique ici a
  contenu de test contre contenu de production.
- **Ne scanner que certains repertoires (exclure `contenu-repris/`, ou les repertoires
  `squad/sprints/`).** Ecarte : verifie d'abord, `contenu-repris/` est deja propre ; l'exclure par
  precaution aurait retire une protection future sans gain present.
- **Detecter aussi les noms de compte hors chemin (une liste de prenoms connus).** Ecarte : une
  liste de noms est indefiniment incomplete et se perime a la premiere nouvelle recrue ; la forme
  du chemin (`/home/<quoi que ce soit>/`) est un signal stable, un nom isole dans une phrase n'en
  est pas un.

## Ce qui me ferait changer d'avis

- Si un chemin de poste de travail apparaissait sous une forme que les quatre motifs actuels ne
  couvrent pas (par exemple un partage UNC Windows `\\serveur\partage\...`), j'ajouterais un
  cinquieme motif avec son propre cas de refus et sa propre preuve de mutation, plutot que
  d'elargir un motif existant pour le faire correspondre approximativement.
- Si le nombre de fichiers suivis grossissait au point que l'appel unique a `grep -H` redevienne
  lui-meme un cout notable, je regarderais `git grep` (qui evite meme la lecture manuelle de la
  liste de fichiers) avant d'ajouter du parallelisme.
