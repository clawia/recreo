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
attendus", pour que `nadia-prof` les audite comme n'importe quel contenu. Je signale des
maintenant, sans le trancher ici, que `contenu-repris/associer/cp-formes.yml` presente au moins
deux ambiguites a verifier ce jour-la : deux pictogrammes de triangle differents (l'un pointe
vers le haut, l'autre vers le bas) associes a la meme cible "triangle" (orientation et couleur
non pertinentes pour la competence mais potentiellement deroutantes), et un pictogramme de
carre rendu en bleu associe a "carre" sans que la couleur ait de rapport avec la competence
visee - a l'enfant de ne pas se tromper de critere de tri.

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
  la meme raison.
- `CLAUDE.md`, section "Conventions de redaction" : le texte actuel ("s'appliquent a tout le
  depot, code et documentation compris") ne nomme pas cette distinction et devrait etre
  complete pour la rendre explicite (ex. "a l'exception du contenu et de l'interface destines a
  l'enfant, gouvernes par la porte pedagogique de `nadia-prof`"). Cette DEC trace l'ecart ; elle
  ne modifie pas elle-meme `CLAUDE.md`.
- Aucune ligne de `squad/programme/MATRICE-couverture.md` n'est affectee : aucune competence
  n'est couverte ni decouverte par cette decision.
