# Relecture pedagogique et QA de contenu - Sprint 17, seconde passe

- **Auteure** : relecteur-pedagogique
- **Date** : 2026-08-21
- **Provisionnement** : worktree dedie `s17-relecteur-2`, cree hors de l'arbre du depot depuis
  `round-two` a `3018afb` (SHA releve par `git log --oneline -1` sur le worktree, pas repris d'un
  message). Lignee verifiee : `outils/verifier-lignee.sh` rend "le worktree sur la branche
  's17-relecteur-2' descend de la lignee de round-two (ancetre commun trouve dans son historique)."
- **SHA de relecture : `3018afb`.** Perimetre de cette passe : tout ce qui a fusionne entre `ed7eb52`
  (SHA de ma premiere passe) et `3018afb` - 24 commits, 43 fichiers texte hors captures d'ecran.
- **Premiere passe** : mes cinq corrections sont au depot (commit `ecc8a1d`) et mes deux
  signalements ont ete traites par leurs destinataires - verifie a la source, pas pris sur parole
  (voir section dediee ci-dessous).
- **Perimetre lu** : `DEC-0039` en entier, ses sept amendements ; `ADR-0051`, `ADR-0052` et ses deux
  amendements, `ADR-0053`, le troisieme amendement d'`ADR-0046`, l'amendement d'`ADR-0049`,
  `ADR-index.md`, `DEC-index.md` ; `FICHE-barre-entree-niveau.md` (les trois nouvelles sections
  datees et la table de correspondance amendee), `FICHE-bandit-manchot-synonymes-cm1.md` (en entier,
  du premier arbitrage au refus du 2026-08-21), `FICHE-bandit-manchot-homophones-cm1.md` (les
  sections neuves de s17), `FICHE-bandit-manchot-additions-cp.md` (la note datee du 2026-08-19) ;
  les quatre avis neufs (`avis-garde-fous-routes-par-yanis.md`, `avis-clause-invariante-et-
  specificite.md`, `avis-couleur-de-position-et-cascade-maison.md`, `avis-population-des-garde-
  fous.md`) ; `docs/backlog/backlog.md` (les deux blocs ajoutes) ; `squad/programme/MATRICE-
  couverture.md` (les deux notes datees et les deux syntheses) ; les diffs de `planning.md` et de
  `rapport-qualite.md` depuis ma premiere passe ; `cm1-synonymes.yaml` et `cm1-homophones.yaml`
  (verifies par point de code pour les guillemets francais et les espaces insecables, pas a l'oeil) ;
  `catalogue.yaml`, entree `bandit-manchot-synonymes-cm1`.

## Premiere passe : verification que mes deux signalements ont ete traites

1. **`planning.md` (S17-2 hors perimetre)** : `sofia-sm` a ajoute la section "Correction du
   2026-08-19 (trouvee par relecteur-pedagogique, s17)" en fin de fichier, sur le meme patron que la
   note du 24/24 - elle date, cite l'amendement de `DEC-0039` qui fait foi, et ne reecrit pas la
   phrase d'origine. Traite.
2. **`FICHE-bandit-manchot-additions-cp.md` (trois occurrences de "22 sur 24")** : chacune des trois
   porte desormais une note ou un renvoi date du 2026-08-19, ecrits par `nadia-prof` elle-meme sur
   son propre texte. La note principale explique la cause (comparaison sensible a la casse) et
   corrige aussi le total de la reserve A (47/49 -> 49/49). Traite, et par la bonne main.

## Corrections directes appliquees a cette passe

**Aucune.** Recherche systematique sur les 43 fichiers touches entre `ed7eb52` et `3018afb`
(parentheses non appariees hors blocs de code, titres coupes sur deux lignes, tirets cadratins et
guillemets typographiques, emoji, doublons de mots, lignes vides manquantes avant un titre) : rien
trouve. Toutes les references vers un fichier `.md` ou une classe de test citees dans le materiau
neuf pointent vers un fichier qui existe reellement (verifie un par un, voir section dediee). Ce
n'est pas la meme situation qu'a ma premiere passe (cinq corrections trouvees) : ce lot de fusions a
ete redige avec un soin de formatage plus grand, sans artefact de concatenation.

## Signale sans corriger

### 1. `FICHE-bandit-manchot-homophones-cm1.md` - le tableau de bord n'a pas suivi la fermeture de sa propre reserve, le meme jour

Le fichier empile ses bulletins "Statut global au [date]" en ordre antichronologique, comme
`FICHE-barre-entree-niveau.md` (bonne pratique deja verifiee ailleurs dans cette meme passe - voir
section 3). Mais ici l'empilement est en defaut : la toute premiere puce du fichier, "Statut global
au 2026-08-19 (s17), sur le paquet raccourci de `S17-4`", ecrit encore que trois indices sur
vingt-quatre (items 3, 10, 21) nomment encore la reponse. Or, plus bas dans le meme fichier, la
section "Note datee du 2026-08-19 (s17) - ma reserve sur les trois indices est FERMEE" (qui relate la
correction de `lea-dev` apres le verdict, donc chronologiquement plus tardive le meme jour) etablit
zero indice sur vingt-quatre. La puce de tete - l'endroit qu'un lecteur presse consulte en priorite -
n'a pas ete mise a jour avec cette fermeture, ni marquee comme perimee par un renvoi. Un lecteur qui
s'arrete au tableau de bord croit la reserve encore ouverte.

**Consequence en cascade** : la note datee du 2026-08-19 dans `FICHE-bandit-manchot-additions-cp.md`
(celle qui ferme mon signalement de premiere passe, section precedente) renvoie explicitement a ce
meme etat perime - elle ecrit que l'etat du paquet apres S17-4, mesure ce jour, est de trois indices
sur vingt-quatre, et renvoie au verdict du meme jour. Cette phrase etait exacte au moment ou
`nadia-prof` l'a ecrite (verifie par l'historique : le commit qui l'ecrit et celui qui ouvre le
verdict a trois indices sur vingt-quatre sont un seul et meme commit, `66e2721` ; la fermeture a zero
indice vient d'un commit distinct et posterieur, `f07abca`) - ce n'est donc pas une erreur d'ecriture,
c'est un enchainement de deux verites successives du meme jour dont la seconde n'a pas ete reportee
dans les deux endroits qui citent la premiere. Je ne corrige ni l'une ni l'autre : ce sont des
verdicts de contenu en premiere personne de `nadia-prof`, et cette fiche est hors du perimetre
explicitement affecte a cette passe. Je le route a elle.

### 2. `FICHE-bandit-manchot-synonymes-cm1.md` - aucune table de correspondance pour SYN-1 a SYN-10

Le tableau de bord ("Statut global au [date]") de cette fiche est lui-meme coherent et a jour - lu de
haut en bas, la puce du 2026-08-21 est bien la plus recente et ne contredit aucune des precedentes.
Mais aucune table ne recense les dix jetons SYN-1 a SYN-10 avec leur etat courant en un regard,
contrairement a `FICHE-barre-entree-niveau.md` qui porte une table "Les conditions bloquantes" /
"Les reserves" tenue a jour a chaque remesure (verifie ligne par ligne, section 3 ci-dessous). Ici, il
faut reconstituer l'etat de chaque jeton en lisant l'integralite des sections datees (plus de 800
lignes, cinq dates differentes). C'est exactement le defaut que j'avais releve en s16 et que
l'amendement de s16 demande de fermer par une table unique portee par la fiche du jeu concerne : il
n'est pas ferme ici. Je le signale a `nadia-prof`/`camille-po` plutot que d'ecrire la table
moi-meme - une table qui resume dix verdicts de contenu touche a leur substance, ce que je ne tranche
pas a leur place.

### 3. `FICHE-barre-entree-niveau.md` - une affirmation de `nadia-prof` sur ADR-0053 n'est plus vraie au sommet de l'arbre, sans que rien ne le reoriente

Dans "Remesure du 2026-08-20 (s17)" (la section la plus recente de la fiche), `nadia-prof` ecrit
qu'elle ne peut pas crediter ADR-0053, que cette decision n'existe dans aucun fichier du depot sur
aucune branche, l'ayant cherchee par son numero sur toutes les references locales sans la trouver.
Verifie, et l'affirmation est exacte a la base qu'elle cite : ADR-0053 est effectivement absent du
commit `0dc90e9` qu'elle nomme comme sa base (`git cat-file -e
0dc90e9:squad/decisions/adr/ADR-0053-....md` echoue). Ce n'est pas une erreur d'ecriture : ADR-0053 a
ete fusionne sur `round-two` par une autre branche, en parallele, apres la base qu'elle avait
relevee. Mais au sommet de l'arbre relu ici (`3018afb`), ADR-0053 existe, et il decrit exactement le
mecanisme qu'elle disait ne pas pouvoir juger (une clause qui compare l'issue de la cascade plutot
qu'une specificite). Aucune section posterieure de cette fiche - y compris la plus recente - ne
referme cette phrase. Un lecteur qui arrive sur cette fiche aujourd'hui et verifie ADR-0053 la
trouve, en apparente contradiction avec une phrase qui n'a pourtant jamais ete fausse au moment ou
elle a ete ecrite. Je signale, je ne corrige pas : c'est un verdict de `nadia-prof` sur un mecanisme
de `theo-archi`, et la reconciliation des deux leur appartient.

## Etat des sept amendements de DEC-0039

**Lisibles de bout en bout, sans contradiction.** Le fichier porte sept sections "## Amendement",
plus un premier amendement non compte comme tel par le depot lui-meme (il relaie les arbitrages
prealables de `nadia-prof`, sans arbitrage propre de `camille-po`) : le decompte de `DEC-index.md`
("amendee ... une septieme fois") correspond exactement aux sept sections datees `camille-po, s17` du
fichier. Chaque amendement dit explicitement ce qu'il corrige et ce qu'il ne rouvre pas du precedent
(par exemple, le sixieme corrige sa propre estimation du cinquieme sans l'effacer ; le septieme
corrige le chiffre du sixieme en nommant precisement pourquoi - l'item pilote tenait au tirage
qu'elle avait sous les yeux, pas a l'item lui-meme). L'ordre de lecture du haut vers le bas du fichier
suit l'ordre chronologique (19, 19, 19, 19, 19, 20, 21), donc aucune inversion de lecture comme celle
que j'ai trouvee dans la fiche des homophones. Le dernier amendement (2026-08-21) tranche
explicitement, sans ambiguite, que l'engagement de DEC-0037 n'est pas honore en substance - ce
constat est repris a l'identique dans `MATRICE-couverture.md` ("Synthese de cloture de s17") et dans
`DEC-index.md` : les trois textes se disent la meme chose. Mes deux corrections de premiere passe sur
ce fichier (parenthese fermee, ligne vide restituee) sont toujours en place et n'ont pas ete affectees
par les fusions suivantes (verifie par `git diff ecc8a1d 3018afb` : les lignes 1 a 529 du fichier
n'ont plus bouge depuis mon commit).

## Etat des tables de correspondance de jetons

- **`FICHE-barre-entree-niveau.md`** : a jour. Les lignes `condition S15-8`, `S17-A`, `S17-B`,
  `S17-D` de la table portent chacune leur dernier etat mesure (REFERMEE le 2026-08-20 pour `S15-8`,
  FERMEE pour `S17-A`, seuil franchi pour `S17-B`, ouverte avec son motif residuel pour `S17-D`),
  coherent avec la section "Etat des jetons apres cette remesure" qui est la plus recente du fichier.
  Seul residu mineur, sans consequence de lecture : le paragraphe qui accompagne la table dit encore
  que les cinq lignes ajoutees le 2026-08-19 ne sont pas modifiees, phrase vraie ce jour-la et rendue
  inexacte par les mises a jour posterieures des cellules S15-8/S17-A/S17-D - la table elle-meme reste
  juste, seule cette phrase d'accompagnement a vieilli. Je le note pour memoire, sans le compter parmi
  mes signalements principaux : aucune ambiguite n'en resulte pour un lecteur qui lit la table plutot
  que la phrase.
- **`FICHE-bandit-manchot-synonymes-cm1.md`** : absente. Voir signalement 2 ci-dessus.

## Reference qui pointe vers rien

**Aucune trouvee.** Verifie systematiquement : les 28 noms de fichiers `.md` cites entre guillemets
techniques dans le materiau ajoute entre `ed7eb52` et `3018afb` existent tous ; les six classes de
test nommement citees dans les nouveaux ADR et avis (LaConsigneDUnJeuServiAtteintLEcranDeMancheTest,
UneCouleurDePositionSurvitATousLesEtatsTest, LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest,
UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest, ToutEcranEchangeEstUnDocumentJouableTest,
PaquetCm1SynonymesTest) existent au chemin qu'elles annoncent.

## Contenu destine a l'enfant

- **`cm1-synonymes.yaml` (16 items desormais, apres retrait de quatre items a trois rangees)** :
  francais correct et integralement accentue, verifie phrase par phrase. Les guillemets francais et
  leurs espaces insecables verifies par point de code, pas a l'oeil (SYN-9) : 17 paires de guillemets
  francais dans le fichier, dont une seule est une mention meta dans un commentaire (elle decrit la
  convention, elle ne l'applique pas). Sur les seize paires reellement appliquees aux items, les 32
  espaces attendues sont toutes des espaces insecables (U+00A0), aucune espace ordinaire (U+0020)
  glissee par erreur. Aucune faute trouvee.
- **`cm1-homophones.yaml` (trois indices reecrits, items 3, 10, 21)** : les trois nouveaux textes
  (fondes sur les substitutions "etait", "a quel endroit", "pouvait") emploient la meme technique que
  celle deja en usage aux items 2 et 9 du paquet - tester l'autre homophone et montrer que la
  substitution echoue - sans jamais nommer la bonne reponse. Francais correct, accents corrects,
  aucune faute.
- **`catalogue.yaml`, entree `bandit-manchot-synonymes-cm1`** : la consigne desormais lue par
  l'enfant a chaque manche ("Quel mot peut remplacer le mot entre guillemets sans changer le sens ?")
  est en francais correct et integralement accentue. L'espace avant le point d'interrogation y est
  ordinaire (U+0020), comme sur les neuf autres consignes du catalogue sans exception (verifie par
  point de code) : c'est la convention deja en usage partout dans ce fichier, pas un ecart introduit
  par ce lot.

Ces deux fichiers de contenu et cette consigne n'appellent aucune correction de ma part sous l'angle
qui m'est reserve. Leur justesse pedagogique (le choix des synonymes, la plausibilite des
distracteurs, le niveau de langue) reste entierement a `nadia-prof`, qui l'a d'ailleurs deja jugee
dans les deux sens ce sprint.

## Ce que je ne tranche pas

Les trois points signales touchent a la substance de verdicts et a un empilement de notes en
premiere personne : je les route a `nadia-prof` (signalements 1 et 3) et a `nadia-prof`/`camille-po`
conjointement pour la table manquante (signalement 2), plutot que de les corriger ou de les trancher
moi-meme.
