# DEC-0006 - Perimetre du chantier identite visuelle de s02 et statut de l'icone d'application au regard de DEC-0002

- **Type** : portee
- **Date** : 2026-08-05
- **Auteur** : camille-po
- **Sprint** : s02
- **Competence programme visee** : transverse - cette decision ne porte sur aucune competence du
  referentiel ; elle scope un ensemble de stories transverses et clarifie l'application d'une
  decision existante (`DEC-0002`) a un nouvel objet (l'icone d'application).
- **Statut** : Acceptee

## Contexte

En rejouant l'application sur `round-two`, code et rendu a l'appui, je constate un trou de
backlog. `recreo.css`
(`adapters/adapter-web/src/main/resources/static/css/recreo.css`) ne declare que 8 selecteurs :
`body`, `.consigne`, `.enonce`, `.proposition`, `.signal`, `.icone-signal`, `.signal.est-juste`,
`.signal.est-faux`. Tous les autres selecteurs utilises par les gabarits - `h1`, `.jeux`,
`.demarrer`, `.retour-accueil`, `.score`, `.fin-de-partie`, `.indice`, `.erreur`, `.pas-de-jeu`,
`.message-reprise`, `.recommencer`, et le bouton "Encore !" du fragment `retour.html` - n'ont
aucune regle et tombent au style par defaut du navigateur. `/favicon.ico` repond 404. Aucune
palette de couleurs, aucune typographie au-dela de `sans-serif` : l'application n'a pas
d'identite visuelle.

`ines-design` a ete creee pendant s01 (amendement 1, section "L'equipe", de
`squad/sprints/s00-socle/retro.md`) avec un mandat deja large ("design d'interface et
accessibilite visuelle... contraste, tailles de police et de signal, semantique des formes,
ergonomie pour un enfant de CP qui lit encore peu"), mais elle n'a ete affectee qu'aux deux
stories qui existaient au backlog a ce moment (S1-6, S1-9). Aucune story d'identite visuelle, de
charte graphique ou d'icone d'application n'a jamais ete ecrite. Ce n'est pas un manquement
d'Ines : son mandat couvrait deja ce perimetre, c'est le backlog qui ne l'avait jamais sollicitee
dessus. Une partie de ce manque etait deja nommee par `nadia-prof` en reserve non bloquante dans
`squad/conformite/FICHE-bandit-manchot-additions-cp.md` (reserves 6 et 7 du verdict du
2026-08-05).

Deux questions de perimetre se posent, distinctes d'une simple story de backlog, et que je
tranche ici pour qu'un futur agent n'ait pas a les redeviner :

1. **Combien de stories, et sur quel decoupage ?**
2. **Une icone d'application (favicon) est-elle gouvernee par la regle de redaction du depot
   (`CLAUDE.md`, qui interdit les symboles Unicode decoratifs) ou par la porte pedagogique de
   `nadia-prof` (`DEC-0002`, categorie B) ?**

## Decision

### Decoupage en trois stories, pas une seule "charte graphique"

Je decoupe le chantier en trois stories independantes plutot qu'une seule story de charte
graphique globale (`docs/backlog/backlog.md`, sprint 2, S2-1 a S2-3) :

- **S2-1** reprend telles quelles les deux reserves deja chiffrees par `nadia-prof` (tailles de
  texte et de bouton, reserves 6 et 7) : perimetre etroit, criteres deja quasi ecrits par la
  fiche de conformite, livrable independamment du reste.
- **S2-2** couvre l'habillage general (couleur, espacement, coherence) des ecrans aujourd'hui nus :
  perimetre plus large, mais qui ne depend pas de S2-1 pour etre construit ou juge.
- **S2-3** couvre l'icone d'application : objet distinct (un fichier binaire servi par une route
  HTTP, pas une regle CSS), avec sa propre question de gouvernance (voir ci-dessous), donc sa
  propre story plutot qu'un critere noye dans S2-2.

Motif : chacune de ces trois stories a un critere d'acceptation testable independant des deux
autres, et aucune n'a besoin d'attendre la livraison des deux autres pour etre revue par
`yanis-qa` ou auditee par `nadia-prof`. Les regrouper en une seule story aurait reproduit le
defaut deja releve par `yanis-qa` sur S1-6 et S1-9 (`squad/sprints/s01-lever-refus-pedagogique/
rapport-qualite.md`) : des criteres visuels multiples, sans test automatise nomme un par un, plus
difficiles a tracer individuellement dans un rapport qualite.

### L'icone d'application est de la categorie B de DEC-0002, pas de la categorie A

`DEC-0002-emoji-et-pictogrammes-contenu-enfant.md` distingue une categorie A (redaction du
depot - code, documentation, messages de commit - ou la regle de `CLAUDE.md` interdisant les
symboles Unicode decoratifs s'applique integralement) et une categorie B (contenu et interface
destines a l'enfant, y compris les "signaux d'interface purs (retour juste/faux, navigation)"),
gouvernee non par cette regle de style mais par la porte pedagogique de `nadia-prof`.

Une icone d'application vue par l'enfant a chaque lancement, dans l'onglet du navigateur ou dans
ses favoris, est de l'interface, pas de la prose du depot : elle releve donc de la categorie B,
exactement au meme titre que le signal juste/faux deja tranche par `DEC-0002` pour S0-5 et audite
par `nadia-prof` pour S1-6. Je le precise explicitement ici parce que la question est
suffisamment proche de la frontiere pour qu'un agent puisse s'y tromper dans les deux sens : soit
en appliquant a tort l'interdiction de `CLAUDE.md` sur les symboles decoratifs a un fichier
d'icone binaire (ce n'est pas un caractere Unicode dans un texte du depot), soit en pensant qu'une
icone d'application, n'etant pas un contenu pedagogique a proprement parler (elle n'enseigne
aucune notion du programme), echappe a toute porte de validation. Aucune des deux n'est le cas :
`ines-design` propose le dessin et la couleur, elle ne tranche pas ; `nadia-prof` en juge
l'adequation a un enfant de CP ou de CM1 (reconnaissable, non ambigu, sans biais), exactement
comme elle le ferait pour tout autre element de categorie B.

## Justification pedagogique

Aucune de ces deux questions ne touche a la qualite d'un contenu pedagogique : la premiere est un
decoupage de backlog, la seconde applique une distinction deja validee par `nadia-prof`
(relecture de `DEC-0002` dans le verdict de sprint s00 de
`squad/conformite/FICHE-bandit-manchot-additions-cp.md`) a un nouvel objet du meme type. Je ne
juge ici ni la qualite d'un texte ni celle d'un pictogramme : ce jugement reste, comme toujours,
la seule autorite de `nadia-prof`.

## Impact

- `docs/backlog/backlog.md`, sprint 2 : trois stories S2-1, S2-2, S2-3 ecrites en consequence de
  cette decision.
- `squad/programme/MATRICE-couverture.md` : aucun changement. Les trois stories sont transverses,
  aucune ne cible de competence du referentiel.
- Aucune fiche de conformite n'est ouverte par cette decision : les trois stories ne constituent
  pas un nouveau jeu au sens de `squad/conformite/FICHE-<jeu>.md` ; elles modifient l'habillage
  d'un jeu et de l'application existants. La question de savoir si une fiche distincte doit
  malgre tout etre ouverte pour tracer le jugement de `nadia-prof` sur l'icone et sur l'habillage
  reste ouverte et sera tranchee au planning de s02, en fonction de la forme que prendra le rapport
  qualite de `yanis-qa` sur ces stories.

## Ce qui reste hors de mon mandat

- Le choix technique du format de fichier de l'icone (`.ico`, `.png`, `.svg`) et de la route qui
  la sert : `theo-archi` / `marc-dev` / `lea-dev`.
- La valeur exacte des tailles, couleurs et du dessin retenu : `ines-design` propose, `nadia-prof`
  juge en dernier ressort le rendu vu par l'enfant.
