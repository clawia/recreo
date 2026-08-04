# DEC-0002 - Statut des emoji et pictogrammes dans le contenu et l'interface destines a l'enfant

- **Type** : portee
- **Date** : 2026-08-04
- **Auteur** : camille-po
- **Sprint** : s0
- **Competence programme visee** : transverse (cette decision porte sur une convention, pas sur
  une competence precise ; elle s'appliquera a toute mecanique et tout contenu futurs qui
  affichent un pictogramme a l'enfant, par exemple une future mecanique d'association reprenant
  `contenu-repris/associer/cp-formes.yml`).
- **Statut** : Acceptee

## Contexte

`theo-archi` route un obstacle sur la story S0-5 (`avis-faisabilite.md`, section S0-5) : le
critere d'acceptation demandait un "icone" pour rendre le retour juste/faux distinguable sans
lecture fine. Or `CLAUDE.md`, section "Conventions de redaction", interdit "les emoji ni les
symboles Unicode decoratifs" et precise que la regle "s'applique a tout le depot, code et
documentation" - un gabarit Thymeleaf etant un fichier du depot.

La question n'est pas seulement technique. Techniquement, Theo confirme qu'un SVG ou une forme
CSS rend un service au moins equivalent, sans les inconvenients d'un caractere emoji (rendu
different selon la police et l'appareil, contraste et taille non maitrises). Mais la question de
fond depasse S0-5 : `contenu-repris/` contient des paquets entiers ou l'emoji **est** le
contenu pedagogique lui-meme. Exemple concret verifie dans
`contenu-repris/associer/cp-formes.yml` : l'enfant doit associer un pictogramme (par exemple un
triangle, un carre, un cercle ou une etoile, chacun rendu par un caractere emoji dans le fichier
source) au nom de la forme qu'il represente - l'emoji n'y est pas une decoration, c'est la
question. Si la regle de redaction du depot s'appliquait telle quelle a ce registre, aucune
mecanique d'association reprenant cette matiere premiere ne serait jamais possible, sans que
cela ait jamais ete decide comme un choix produit ou pedagogique.

Il faut donc distinguer deux registres que `CLAUDE.md` traite aujourd'hui comme un seul.

## Decision

Je distingue deux categories de texte et d'image dans le depot, avec deux regimes differents.

**Categorie A - redaction du depot (registre professionnel de la squad).** Code, commentaires,
Javadoc, messages d'erreur techniques, README, `squad/`, `docs/backlog/`, ADR, DEC, fiches de
conformite, messages de commit, noms de fichiers. La regle de `CLAUDE.md` ("pas d'emoji, pas de
symbole Unicode decoratif, tirets simples, guillemets droits") s'y applique **integralement et
sans exception**. Elle est verifiee par `relecteur-pedagogique`. Rien ne change ici.

**Categorie B - contenu et interface destines a l'enfant.** Ce que le jeu affiche a l'enfant :
enonces, propositions de reponse, pictogrammes representant une notion du programme (une forme
geometrique, un objet, un mot illustre), et signaux d'interface purs (retour juste/faux,
navigation). Ce registre **echappe** a la regle de redaction du depot en tant que telle : ce
n'est pas de la prose de la squad, c'est un support pedagogique ou une interface de jeu. Il n'est
donc **ni interdit ni autorise par principe** d'y employer un emoji ou un pictogramme. Il est
gouverne par un autre mecanisme, plus exigeant : la porte pedagogique de `nadia-prof`, qui juge
chaque pictogramme comme elle jugerait un enonce - exactitude, absence d'ambiguite, absence de
biais, lisibilite - au cas par cas, contenu par contenu.

**Consequence immediate pour S0-5.** Le retour juste/faux de ce sprint ne represente aucune
notion du programme : c'est un signal d'interface pur, pas un contenu pedagogique. Je retiens
donc la solution technique deja recommandee par `theo-archi` (une forme ou une couleur en CSS ou
SVG, pas un caractere emoji dans le gabarit), pour un motif produit et pas seulement technique :
un signal de reussite/echec doit rendre de facon fiable et identique sur tous les appareils, ce
qu'un caractere emoji ne garantit pas. Le critere d'acceptation de S0-5 est reformule dans
`docs/backlog/backlog.md` : le mot "icone" est remplace par "signal visuel (forme et/ou
couleur)", pour ne plus presupposer un emoji.

**Consequence pour la suite.** Quand une story ouvrira une mecanique qui reprend de la matiere
premiere a base de pictogrammes (association, memory), la fiche de conformite correspondante
devra nommer explicitement l'usage de ces pictogrammes dans ses "criteres pedagogiques
attendus", pour que `nadia-prof` les audite comme n'importe quel contenu. Point sur
`contenu-repris/associer/cp-formes.yml`, tranche par `nadia-prof` lors de sa relecture
pedagogique du 2026-08-04 (voir amendement ci-dessous) : les deux pictogrammes de triangle
d'orientation differente (l'un pointe vers le haut, l'autre vers le bas) associes a la meme
cible "triangle" ne sont **pas** une ambiguite mais une **qualite** - la competence CP
"reconnaitre et nommer quelques figures planes" demande justement d'abstraire l'orientation, la
taille et la couleur, et un triangle toujours pointe vers le haut installerait un prototype faux.
Les deux orientations sont a garder. Le pictogramme de carre rendu en bleu reste neutre a une
seule condition, que `nadia-prof` verifiera elle-meme le jour ou la mecanique d'association
arrivera au planning : qu'aucun item du meme lot ne fasse jamais de la couleur un critere de tri,
et que la consigne ne mentionne jamais la couleur.

## Justification pedagogique

Une regle de style d'ecriture (tirets simples, pas d'emoji decoratif) protege la clarte des
artefacts d'ingenierie de la squad - elle n'a jamais ete concue pour juger un support
pedagogique. Juger si un pictogramme est un bon support pour enseigner une notion a un enfant de
CP est une question d'exactitude, de lisibilite et d'absence de biais, exactement le type de
jugement que seule `nadia-prof` est habilitee a rendre (`squad/EQUIPE.md`, mission de Nadia).
Trancher cette question par une regle de redaction generique aurait interdit ou autorise du
contenu pedagogique sans qu'aucun agent competent en pedagogie ne se soit prononce - c'est
exactement le risque que la porte pedagogique existe pour eviter.

`nadia-prof` est invitee a relire cette decision sur le fond pedagogique, en particulier au
moment ou une premiere mecanique d'association arrivera au planning. Si elle signale que la
distinction posee ici pose un probleme pedagogique qu'elle n'anticipe pas, cette DEC sera revue
et, le cas echeant, remplacee.

## Impact

- `docs/backlog/backlog.md`, story S0-5 : critere d'acceptation reformule ("signal visuel" au
  lieu d'"icone").
- `squad/conformite/FICHE-bandit-manchot-additions-cp.md` : critere correspondant reformule pour
  la meme raison. Voir aussi l'amendement du 2026-08-04 ci-dessous : trois criteres supplementaires
  ajoutes a la meme fiche a la suite du refus pedagogique de sprint 0.
- `CLAUDE.md`, section "Conventions de redaction" : le texte actuel ("s'appliquent a tout le
  depot, code et documentation compris") ne nomme pas cette distinction et devrait etre
  complete pour la rendre explicite (ex. "a l'exception du contenu et de l'interface destines a
  l'enfant, gouvernes par la porte pedagogique de `nadia-prof`"), **et** pour porter l'exigence
  positive de l'amendement ci-dessous ("le texte destine a l'enfant est en francais correct et
  integralement accentue"). Cette DEC trace l'ecart ; elle ne modifie pas elle-meme `CLAUDE.md`.
- Aucune ligne de `squad/programme/MATRICE-couverture.md` n'est affectee : aucune competence
  n'est couverte ni decouverte par cette decision.

## Amendement du 2026-08-04 - relecture pedagogique de nadia-prof (sprint 0, refus de
`bandit-manchot-additions-cp`)

`nadia-prof` a relu cette decision a ma demande, dans le cadre du refus pedagogique de
`squad/conformite/FICHE-bandit-manchot-additions-cp.md` (section "Relecture de DEC-0002" de cette
fiche). Elle valide la distinction des deux categories et le choix technique retenu pour le
signal de S0-5 (une forme CSS/SVG plutot qu'un emoji, pour un rendu identique sur tout appareil).
Mais elle signale un manque dont ce sprint est la preuve directe, que je corrige ici.

**Le manque.** La decision **exemptait** la categorie B de la regle de redaction du depot sans y
mettre aucune exigence positive a la place. Elle retirait donc ce texte du controle du
`relecteur-pedagogique` et le confiait a la seule porte pedagogique de `nadia-prof`, qui ne
s'exerce qu'une fois par sprint. Resultat constate : l'habitude ASCII du depot (categorie A) a
fuite dans le registre de l'enfant precisement la ou plus aucune regle ne s'appliquait - le
retour faux du gabarit `retour.html` affichait "Ce n'est pas ca. La bonne reponse etait X." a un
enfant de CP en train de construire son orthographe lexicale sur ce qui lui est montre.

**La correction.** J'ajoute a la categorie B une exigence positive, verifiable et executoire,
que le `relecteur-pedagogique` controle desormais comme n'importe quelle autre regle de
redaction du depot :

> Le texte destine a l'enfant (categorie B) est redige en francais correct et integralement
> accentue, orthographe et ponctuation irreprochables, parce qu'il est lu par un enfant en cours
> d'apprentissage de la lecture et de la construction de ses correspondances graphemes-phonemes
> et de son orthographe lexicale.

Cette exigence s'applique a tout texte de categorie B present dans le depot : enonces,
consignes, retours, libelles de bouton, messages d'etat (fin de partie, absence de partie,
erreur affichee a l'enfant). Elle ne remplace pas la porte pedagogique de `nadia-prof`, qui reste
seule a juger si le vocabulaire, le ton et le fond sont adaptes au niveau vise ; elle ajoute un
filet de securite mecanique et systematique (orthographe, accentuation, ponctuation) qui
s'exerce a chaque relecture de livrable, pas une seule fois par sprint. L'exemption de la regle
de style d'ingenierie ne se lit donc plus comme une absence de regle.

Le point sur les deux orientations du triangle de `contenu-repris/associer/cp-formes.yml` est
tranche directement dans le corps de la decision ci-dessus, section "Consequence pour la suite",
selon l'avis de `nadia-prof` et non le mien : elle y voit une qualite pedagogique et non un
defaut, et je corrige le texte en ce sens plutot que de maintenir ma lecture initiale.

**Impact de cet amendement** :
- `squad/conformite/FICHE-bandit-manchot-additions-cp.md` : un critere pedagogique attendu ajoute
  ("tout texte destine a l'enfant est redige en francais correct et integralement accentue").
- `docs/backlog/backlog.md`, sprint 1, story S1-1 : corrige le manquement constate ce sprint et
  rend l'exigence executoire sur l'increment refuse.
- `CLAUDE.md` : le complement deja signale par cette DEC doit desormais porter aussi cette
  exigence positive, pas seulement la distinction des deux categories (voir Impact ci-dessus).
