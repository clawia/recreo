# DEC-0030 - Perimetre du sprint 13 : trois lignes de mathematiques CM1 ouvertes, deux differees, et
# le plafond de la mecanique unique mesure

- **Type** : portee, arbitrage de trajectoire
- **Date** : 2026-08-15
- **Auteur** : camille-po
- **Sprint** : s13
- **Competence programme visee** : mathematiques, CM1 (cycle 3) - trois lignes du referentiel
  (`squad/programme/mathematiques/competences-cm1.md`), toutes `non couvert` a ce jour. Section 4 de
  cette decision est transverse - pas de contenu pedagogique.
- **Statut** : Acceptee

## Contexte

Le sprint 12 ferme avec huit lignes `couvert-valide` sur 46, trois `en cours`, 35 `non couvert`
(`squad/programme/MATRICE-couverture.md`, "Synthese apres les seconds verdicts du sprint 12"). Il
laisse aussi une dette nommee dans `squad/sprints/s12-mathematiques-cm1/RAPPORT.md` et `retro.md` :
`confirmation-archunit.md` absent pour la quatrieme fois (route a `theo-archi`/`sofia-sm`, pas mon
fichier), la reserve F11 de `nadia-prof` (aucune ecoute reelle au lecteur d'ecran), la seconde moitie
de la reserve O, deux reserves des tables de multiplication non converties en story, la story S12-5
non affectee, et le libelle "x10" contre "×10" duplique.

Cette decision traite les trois lignes de mathematiques CM1 differees pour capacite au sprint 12
(dont deux sont maintenant sans obstacle d'arbitrage - voir plus bas), verifie et corrige l'estimation
du plafond de la mecanique unique, et instruit le reste de la dette nommee ci-dessus.

## 1. Ce qui est deja instruit et pret a partir, verifie avant d'ecrire une ligne

Les cinq lignes de mathematiques CM1 differees ou en attente au sprint 12 sont reverifiees ligne par
ligne, contre `DEC-0029` et `ARBITRAGE-PREALABLE-s12-mathematiques-cm1.md` :

- **Lire, ecrire, decomposer et comparer les nombres entiers jusqu'au million.** `nadia-prof` a
  **accepte** le proxy de transcription chiffres/lettres, sous deux conditions ecrites (GN-1, GN-2 -
  `ARBITRAGE-PREALABLE-s12-mathematiques-cm1.md`, section 1). Elle l'ecrit elle-meme : "la ligne peut
  ouvrir en entier... le jour ou `camille-po` en fait une story." Aucune arbitrage neuf a demander.
- **Multiplications et divisions simples (multiples de 10, 100).** Differee pour capacite par
  `DEC-0029`, recommandee en premier par moi-meme dans cette meme decision. Aucun arbitrage prealable
  n'a ete rendu sur ce contenu precis - il n'a jamais ete instruit au niveau de detail des trois lignes
  de s12 - je le demande dans cette decision (section 5).
- **Resoudre des problemes de durees.** Differee pour capacite par `DEC-0029`. Matiere premiere
  partielle deja identifiee (`contenu-repris/quiz/cm1-heure.yml`, deux items seulement transposables
  tels quels - voir S13-3 ci-dessous). Aucun arbitrage prealable rendu ; demande en section 5.
- **Resoudre des problemes a plusieurs etapes mobilisant les 4 operations.** Differee pour capacite
  par `DEC-0029`, avec un motif explicite de risque ("le paquet le plus risque a bien ecrire du
  premier coup"). Je la differe une seconde fois - voir section 3.
- **Notion de fraction simple... et reperage sur une droite numerique.** `nadia-prof` a **refuse** le
  proxy d'encadrement pour "reperage sur une droite numerique" (motif arithmetique : toute fraction du
  paquet serait inferieure a 1, donc encadree entre 0 et 1 sur tout item - un exercice vide). Elle a en
  revanche valide que la moitie "notion de fraction simple" ouvre seule, **sans aucun proxy**, en
  couverture partielle nommee. Je la differe pour capacite - voir section 3.

## 2. Trois lignes entrent au sprint 13

- **Story S13-1** - Lire, ecrire, decomposer et comparer les nombres entiers jusqu'au million (ligne
  entiere, sur le proxy accepte GN-1/GN-2).
- **Story S13-2** - Calcul mental, multiplications et divisions simples par 10 et 100.
- **Story S13-3** - Resoudre des problemes de durees (calcul d'une duree, horaire d'arrivee).

Les trois stories sont ecrites dans `docs/backlog/backlog.md`, section "Sprint 13", avec leurs
criteres d'acceptation. Chacune ouvre une fiche de conformite sous `squad/conformite/`, remplie a
l'exception du bloc de validation, reserve a `nadia-prof`.

Meme dimensionnement que le sprint 12 (trois lignes de contenu neuf) : le sprint 12 a montre qu'un
increment techniquement simple peut consommer autant de temps sur ses defauts que sur son contenu (la
fabrique d'additions, les distracteurs des tables). Je ne charge donc pas ce sprint au-dela de trois
ouvertures de contenu, meme si la capacite semble disponible - `sofia-sm` tranche au planning si elle
juge la capacite reelle superieure.

## 3. Deux lignes restent differees pour capacite, nommement

- **Resoudre des problemes a plusieurs etapes mobilisant les 4 operations.** **Second report sur cette
  meme ligne** : `DEC-0029` l'avait deja differee au sprint 12 pour capacite. Le motif reste identique
  et je ne l'affaiblis pas : aucune matiere premiere n'existe dans `contenu-repris/`, et l'historique de
  ce produit (`DEC-0021`, reserve de regularite de magnitude sur les problemes additifs CP) montre que
  `nadia-prof` est exigeante sur la variete et la coherence de ce type d'enonce. C'est le paquet le
  plus couteux a bien ecrire du premier coup parmi les cinq candidates restantes ; je prefere le caler
  sur un sprint qui ne porte pas deja trois ouvertures neuves plus deux corrections de reserve. Si ce
  report devait se reproduire une troisieme fois, ce ne serait plus un arbitrage mais une direction non
  choisie - je le nomme pour que ce compte ne se perde pas.
- **Notion de fraction simple (couverture partielle, moitie "notion" seule).** Premier report pour
  capacite - l'arbitrage qui la debloquait n'a ete rendu que le 2026-08-14, elle n'avait donc jamais ete
  disponible a ouvrir avant ce sprint. Candidate naturelle du sprint 14 : couverture partielle nommee
  d'emblee, sans proxy, sur la seule reconnaissance/denomination d'une fraction simple (un quart, trois
  cinquiemes...). La moitie "reperage sur une droite numerique" reste hors de portee de la mecanique
  actuelle et route a `theo-archi` le jour ou un mecanisme de droite numerique se pose (voir section 6).

Je ne differe aucune ligne de francais ce sprint - je n'en ouvre pas non plus. Le sprint 12 et ce
sprint concentrent l'effort sur les mathematiques CM1 sur demande explicite de l'utilisatrice ;
plusieurs lignes de francais CP et CM1 sont pourtant directement servables sans aucun arbitrage (voir
section 4) et devraient etre la priorite du sprint 14 si le second sujet de cette decision (section 6)
ne l'emporte pas sur elles.

## 4. Le plafond de la mecanique unique, verifie ligne par ligne

L'utilisatrice demandait une verification de l'estimation faite "a la lecture des intitules" (18 lignes
de contenu seul, 12 de mecanique visuelle neuve, 5 de saisie, 1 d'audio, sur les 35 `non couvert`).
J'ai relu les 35 lignes une par une contre ce que le schema `type: question` peut porter aujourd'hui -
un enonce et un choix parmi trois ou quatre propositions textuelles ou en pictogramme-caractere - et
non contre ce qu'un enfant devrait idealement faire. Le decoupage change sur plusieurs points.

### Methode

Quatre besoins mecaniques distincts, definis par ce qu'aucun texte seul ne peut porter :

- **Contenu seul** : la mecanique actuelle suffit, y compris par un pictogramme-caractere (glyphe
  Unicode ferme dans une chaine, comme `DEC-0019` l'a etabli pour le denombrement) ou un proxy textuel
  deja valide par `nadia-prof` sur ce produit (transcription chiffres/lettres). Certaines de ces lignes
  restent sous reserve d'un arbitrage pedagogique prealable - la validite du proxy, pas le mecanisme -
  nommees ci-dessous.
- **Visuel** : demande un rendu graphique qu'aucun glyphe de texte ne peut porter de facon fiable
  (une horloge, un quadrillage, une droite graduee, une figure a tracer, une image reelle de piece ou
  de billet).
- **Saisie** : demande que l'enfant produise une reponse plutot que d'en choisir une parmi trois ou
  quatre - un mot ecrit, un texte libre, ou une operation posee a plusieurs etapes (multiplication,
  division euclidienne).
- **Audio** : demande d'emettre ou de faire produire du son. Je distingue une sous-categorie plus
  couteuse - la production orale de l'enfant (reconnaissance vocale), distincte de la simple emission
  d'un son par l'application.

### Resultat verifie

| Besoin mecanique | Lignes (sur 35 `non couvert`) | Ecart avec l'estimation initiale |
|---|---|---|
| Contenu seul | **19 lignes entieres + 1 partielle** (fraction, moitie "notion") | Proche de l'estimation (18), legerement au-dessus : la ligne grands nombres, non comptee dans le brouillon initial, y entre desormais en entier grace a l'arbitrage rendu. Quatre de ces lignes restent sous reserve d'un jugement pedagogique prealable (voir liste ci-dessous), pas d'un obstacle mecanique. |
| Visuel | **6 lignes entieres + 1 partielle** (fraction, moitie "reperage") | **Beaucoup plus bas que l'estimation (12).** La monnaie, les grandeurs sans instrument, l'heure et le quadrillage en CP, plus les figures a tracer, la symetrie et le reperage sur droite numerique en CM1. |
| Saisie | **5 lignes** | Conforme a l'estimation. Deux sont des operations posees (multiplication, division euclidienne) plutot que du texte libre, une distinction technique qui appartient a `theo-archi` de trancher si elle change le cout. |
| Audio | **3 lignes**, plus **1 ligne a part**, structurellement plus couteuse | **Beaucoup plus haut que l'estimation (1).** Les trois lignes de conscience phonologique orale du CP (denombrer les syllabes, les localiser, identifier un son) forment un domaine entier ("Langage oral et conscience phonologique") qui ne peut, par construction, recevoir aucun contenu tant qu'aucune emission de son n'existe - `nadia-prof` l'a deja ecrit pour un cas voisin ("sans son, un digraphe transparent ne peut donner lieu qu'a une recherche de lettres", audit du 2026-08-06). La quatrieme ligne, "lire a voix haute un texte court avec fluidite", demande en plus que l'application **recoive** la voix de l'enfant (reconnaissance vocale), pas seulement qu'elle **emette** un son : je la sors du compte audio et la nomme a part, structurellement hors de portee tant qu'aucune decision separee n'est prise sur cette capacite.

**Quatre lignes du bucket "contenu seul" restent sous reserve d'un jugement pedagogique que je ne
rends pas moi-meme** (memes principes que le proxy des grands nombres et des fractions au sprint 12-
voir mes questions a `nadia-prof`, section 5) :

- Denombrer, lire, ecrire et decomposer les nombres jusqu'a 100 (CP) - la validite d'un
  pictogramme-caractere pour "denombrer" une quantite, deja nommee non tranchee par `DEC-0019`.
- Reconnaitre et nommer des figures planes (CP) - un glyphe geometrique Unicode (carre, triangle,
  cercle) suffit-il a "reconnaitre" une figure, ou faut-il un rendu reel ?
- Decoder un mot regulier inconnu par assemblage syllabique (CP) - un choix a trois propositions entre
  des decoupages syllabiques ecrits satisfait-il le verbe "decoder", ou cette competence exige-t-elle
  que l'enfant assemble lui-meme les syllabes plutot que de reconnaitre un decoupage deja fait ?
- Relire et corriger son texte a l'aide de reperes simples (CM1) - un choix entre plusieurs versions
  d'une phrase satisfait-il "relire et corriger **son** texte", ou cette competence suppose-t-elle que
  l'enfant corrige un texte qu'il a lui-meme produit, ce qui la deplace vers la saisie ?

**Ce que je ne fais pas** : je n'ouvre aucune de ces quatre lignes ce sprint, meme partiellement,
avant la reponse de `nadia-prof` - meme discipline que `DEC-0029` sur les grands nombres et les
fractions.

## 5. Ce que je demande a `nadia-prof` avant le code

Quatre questions de validite de proxy (section 4, liste ci-dessus), posees pour qu'un developpeur ne
commence jamais un contenu sur un perimetre qu'elle refuserait ensuite.

Une cinquieme demande, de forme differente : je sollicite un **arbitrage prealable** sur S13-2
(multiplications et divisions simples) et S13-3 (problemes de durees), sur le meme modele que
`ARBITRAGE-PREALABLE-s12-mathematiques-cm1.md` - ces deux lignes n'ont jamais ete instruites a ce
niveau de detail, a la difference du perimetre, des conversions et des tables au sprint 12, qui
avaient chacun leurs conditions ecrites avant tout code. Je pose mes propres criteres dans les stories
ci-dessous (`docs/backlog/backlog.md`), mais ils n'ont pas la valeur d'un jugement pedagogique -
seule elle peut dire si mes bornes (multiples de 10 et 100 seulement, aucune seconde dans les
problemes de duree, heures rondes toutes les 5 minutes) sont justes pour un CM1.

Une sixieme demande, distincte des cinq premieres et qui ne bloque aucun code : je lui demande son
avis, en tant que professeure des ecoles et non en tant que juge d'un increment, sur l'ordre de
priorite pedagogique entre les trois besoins mecaniques non couverts (visuel, saisie, audio) - voir
section 6. Cet avis complete le mien, il ne le remplace pas : je reste seule a decider quelle mecanique
est due et quand, mais je ne veux pas le faire sans avoir entendu si un des trois manques lui parait
plus urgent pour l'apprentissage que les deux autres.

## 6. Ma position sur une seconde mecanique

**Je ne demande pas qu'une seconde mecanique soit construite ce sprint.** Le contenu seul ("contenu
seul", section 4) tient encore une reserve utile : environ 19 lignes entieres, dont 4 seulement
attendent un jugement de `nadia-prof` et non un obstacle technique. Au rythme observe sur ce produit
(quatre lignes au sprint 9, trois au sprint 12, trois proposees ici), et en supposant que les
questions de la section 5 soient tranchees rapidement, **j'estime la reserve de contenu seul a environ
quatre a six sprints** avant qu'elle ne soit epuisee - pas une urgence immediate, mais un horizon
visible, pas lointain.

**Je demande neanmoins que le choix de cette seconde mecanique soit instruit en parallele, des ce
sprint, sans lui affecter de capacite de developpement.** Trois motifs, aucun ne relevant de la
technique :

1. **Le principe directeur du produit n'a jamais ete mis a l'epreuve.** Une mecanique reutilisable qui
   sert n'importe quelle matiere est la promesse fondatrice de `recreo` (`CLAUDE.md`) ; elle n'a jamais
   ete verifiee, faute d'une seconde mecanique a comparer a la premiere. Attendre l'epuisement complet
   du contenu seul pour commencer a l'instruire revient a decouvrir le cout de cette promesse au pire
   moment, quand plus aucune ligne ne peut avancer.
2. **Le besoin le plus rentable pour la couverture n'est pas necessairement le premier a construire.**
   Le besoin **visuel** (6 lignes entieres + 1 partielle) est le plus large des trois manques ; mais le
   besoin **audio** ferme a lui seul un domaine complet du referentiel francais CP ("langage oral et
   conscience phonologique", 3 lignes sur 3 aujourd'hui a zero), fondation de la lecture avant meme le
   grapheme/phoneme deja engage. Je n'ai pas d'avis pedagogique legitime pour trancher entre ces deux
   priorites - c'est la sixieme demande de la section 5.
3. **Le cout de construire une mecanique n'est pas le mien a estimer**, et je ne veux pas le decouvrir
   au moment ou il devient bloquant. Je demande a `sofia-sm` de router a `theo-archi` un avis de
   faisabilite et d'ordre de grandeur (pas un chiffrage detaille, pas une architecture) sur chacun des
   trois besoins, pour que la decision de sequencement, le moment venu, se prenne avec un cout connu et
   non a l'aveugle.

**Ce que je ne fais pas** : je n'ouvre aucune story de mecanique neuve ce sprint, je ne priorise pas
entre visuel/saisie/audio avant d'avoir l'avis de `nadia-prof` et l'avis de faisabilite de
`theo-archi`, et je ne fixe pas de sprint precis pour l'ouverture de la seconde mecanique - seulement
un horizon (quatre a six sprints de marge de contenu seul, a reevaluer a chaque cloture).

## 7. Le reste de la dette du sprint 12, instruit

- **Story S12-5** (`aria-label="Menu principal"` refuse) est **affectee au sprint 13** -
  `docs/backlog/backlog.md` est amende en consequence : elle etait entierement redigee et sans
  obstacle, seule son affectation manquait.
- **Deux reserves non bloquantes des tables de multiplication** (reserve A - un enonce revient une
  partie sur cinq et non sur sept comme initialement estime ; reserve B - deux distracteurs hors de
  l'espace des tables, 11 et 110) deviennent les **stories S13-4 et S13-5**, reprises au mot et au
  chiffre pres du verdict de `nadia-prof` (`squad/conformite/FICHE-bandit-manchot-tables-
  multiplication-cm1.md`, "Synthese apres les seconds verdicts du sprint 12").
- **La reserve O, seconde moitie** (aucun chemin vers le catalogue ne garde la partie en cours),
  ouverte le 2026-08-13 et jamais affectee depuis, devient la **story S13-6**. C'est l'arbitrage
  produit que je rendais devoir a l'utilisatrice ou a moi-meme : je tranche qu'un tel chemin est
  souhaitable (l'enfant doit pouvoir consulter le catalogue sans que ce soit necessairement synonyme
  d'abandon de sa partie, question distincte de la persistance entre deux sessions fermee par
  `DEC-0028` - ceci reste dans la meme session, la meme partie, le meme onglet) et je l'ouvre comme
  story, le moyen technique restant a `theo-archi`.
- **Le libelle "×10" contre "x10"** : le referentiel `squad/programme/mathematiques/competences-
  cm1.md` porte deja le vrai signe multiplie depuis le 2026-08-15 (commit du sprint 12). Trois des six
  occurrences signalees vivaient dans `squad/programme/MATRICE-couverture.md`, qui est un document
  **vivant** cite tel quel du referentiel (regle de la matrice elle-meme, "Comment la lire") et non un
  verdict date a laisser en l'etat : je les corrige dans cette decision (voir "Impact"). Les
  occurrences restantes vivent dans `DEC-0029`, deux fiches de conformite datees et
  `ARBITRAGE-PREALABLE-s12-mathematiques-cm1.md` : ce sont des citations d'un texte ecrit avant la
  correction du referentiel, a la date ou elles ont ete redigees - je ne les reecris pas, au meme titre
  qu'aucun verdict date de `nadia-prof` n'est jamais recrit apres coup sur ce produit.
- **`confirmation-archunit.md` absent pour la quatrieme fois** et **la reserve F11** (aucune ecoute
  reelle au lecteur d'ecran) ne sont **pas** traites par cette decision : le premier est un fichier de
  `theo-archi` que je ne peux pas fabriquer a sa place (meme raisonnement que `sofia-sm` a la cloture
  de s12), et la seconde demande une ecoute reelle, une action, pas une decision de perimetre. Je les
  nomme ici pour que `sofia-sm` les route explicitement au planning de s13 plutot que de les laisser
  glisser une cinquieme fois.

## 8. Un point de process qui conditionne l'utilite de cette decision

`squad/sprints/s10` et `s12` n'ont pas de rapport de tokens, faute de `planning.md` ecrit a
l'ouverture. Cette decision est ecrite pour que `sofia-sm` puisse rediger `squad/sprints/
s13-.../planning.md`, avec un horodatage de debut, **avant que le premier developpeur ne commence** -
les stories S13-1 a S13-6 sont dimensionnees et pretes a etre planifiees des la lecture de cette
decision, rien n'y manque pour ouvrir la ceremonie.

## Justification pedagogique

Aucune sur les criteres eux-memes - ils seront verifies par `nadia-prof` sur les increments livres,
comme pour toute story de ce produit. La section 4 (classification des besoins mecaniques) n'est pas
un jugement pedagogique : c'est une lecture technique de ce que le schema `type: question` peut porter
sans code neuf, sujette a la verification de `theo-archi` si elle en rend un avis.

## Impact

- `docs/backlog/backlog.md`, nouvelle section "Sprint 13" : six stories (S13-1 a S13-6). La story
  S12-5, deja ecrite, est amendee pour noter son affectation a ce sprint.
- Six fiches de conformite ouvertes ou reutilisees sous `squad/conformite/` : trois nouvelles pour
  S13-1 a S13-3, les fiches existantes de "Le bon verbe" (non concernee ici),
  `FICHE-bandit-manchot-tables-multiplication-cm1.md` (S13-4, S13-5) et
  `FICHE-bandit-manchot-additions-cp.md` (S13-6) reutilisees sans nouvelle fiche.
- `squad/programme/MATRICE-couverture.md` : les trois lignes S13-1 a S13-3 passent `en cours` (jeu et
  mecanique renseignes), aucune ne passe `couvert-valide` par ce planning. Les deux lignes differees
  (problemes a plusieurs etapes, fractions) restent `non couvert` avec une note renvoyant a cette
  decision. Correction du libelle "×10" dans les trois occurrences vivantes de ce fichier. Synthese
  chiffree ajoutee en bas de fichier.
- `squad/decisions/produit/DEC-index.md` : ligne DEC-0030 ajoutee.

## Ce que cette decision ne fait pas

- Elle ne tranche aucune question technique : ni le moyen d'une seconde mecanique, ni celui du
  chemin de reserve O, ni la distinction saisie/operation posee de la section 4 - toutes routees a
  `theo-archi`.
- Elle ne prejuge d'aucun Done pedagogique sur S13-1 a S13-6 : ces jugements restent entierement ceux
  de `nadia-prof`, sur les fiches ouvertes.
- Elle ne fait passer aucune ligne de `squad/programme/MATRICE-couverture.md` a `couvert-valide`.
- Elle ne decide pas quelle sera la seconde mecanique, ni quand elle sera construite : elle nomme
  l'horizon (quatre a six sprints de marge de contenu seul) et instruit les avis necessaires pour que
  cette decision se prenne informee, le moment venu.
