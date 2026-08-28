# Relecture pedagogique - s21 (fermer le pli avant le volume)

- **Auteur** : relecteur-pedagogique. **Date** : 2026-08-28.
- **Arbre relu** : `round-two` a `8ecf44d` (tete au moment de la relecture, `git status` propre avant
  mes propres corrections).
- **Perimetre etabli par moi-meme** : `git log --oneline` et `git diff --name-only` entre `478e04d`
  (dernier commit de s20) et `8ecf44d`. 34 commits non-merge (`git log --no-merges`), 142 fichiers
  touches.

## Perimetre relu

Dans l'ordre du brief, plus ce que la comparaison de commits a fait apparaitre : l'integralite de
`squad/sprints/s21-fermer-le-pli-avant-le-volume/` (planning, avis de faisabilite, proposition
`ines-design`, cinq journaux de fusion, deux correctifs de porte rouge, mesures, deux avis de
`nadia-prof`), les trois fiches de conformite neuves (`FICHE-bandit-manchot-syllabes-cp.md`,
`FICHE-bandit-manchot-mot-manquant-cp.md`, `FICHE-bandit-manchot-comparer-cp.md`), les sections s21
ajoutees a `FICHE-bandit-manchot-reperes-cm1.md`, `ARBITRAGE-PREALABLE-s21-trois-paquets-de-volume-
cp.md` en entier (ses deux amendements compris), `DEC-0051` et `DEC-0052` avec leurs cinq
amendements cumules, `ADR-0063`, l'amendement de s21 a `ADR-0025`, les douze `confirmation-
archunit.md` amendes plus leur journal `amendement-jdk-douze-confirmations-s21-6.md`, `ADR-index.md`
et `DEC-index.md`, les sections s21 de `MATRICE-couverture.md` et de `docs/backlog/backlog.md`, et
le contenu neuf (`cp-syllabes.yaml`, `cp-mot-manquant.yaml`, `cp-comparer.yaml`, les trois entrees
neuves de `catalogue.yaml`) avec ses tests (`AuPlusTroisPropositionsParItemTest`,
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`, `PaquetCpSyllabesTest`,
`PaquetCpMotManquantTest`, `PaquetCpComparerTest`, `PaquetCm1ReperesTest`,
`TexteAccentueExposeParLePortEntrantTest`) et l'amendement de `.github/workflows/ci.yml`.

## Ce que j'ai corrige

**Deux references de section fausses, mecaniques, sans ambiguite** - le defaut nomme par le brief
("une reference qui pointe vers rien") sous une forme un peu differente : ici la reference pointe
vers une section qui existe, mais pas la bonne.

- `squad/conformite/FICHE-bandit-manchot-mot-manquant-cp.md`, table de correspondance : `PRE-1` a
  `PRE-8` cites en "section 4.3" d'`ARBITRAGE-PREALABLE-s21-trois-paquets-de-volume-cp.md`. Verifie a
  la source (`grep -n "^### " ARBITRAGE-PREALABLE-...md`) : la section 4.3 est *"Ce que le pli exige
  de ce paquet, en chiffres"* ; les conditions `PRE-1` a `PRE-8` sont en **section 4.2**. Corrige.
- `squad/conformite/FICHE-bandit-manchot-comparer-cp.md`, meme table : `GRA-1` a `GRA-10` cites en
  "section 5.3", meme source verifiee, meme decalage - la section 5.3 est le pli en chiffres, les
  conditions `GRA-1` a `GRA-10` sont en **section 5.2**. Corrige.

**Verifie que ce n'est pas un defaut systematique.** J'ai relu toutes les autres citations de
section des trois tables de correspondance neuves contre la structure reelle d'`ARBITRAGE-
PREALABLE-...md` et des fiches elles-memes (`grep -n "^## \|^### "` sur les quatre fichiers) :
`SEG-1` a `SEG-9` en section 3.3 (juste), `SEG-10` et suivants en section 6 de la fiche syllabes
(juste, section 6 est bien "Reserves ouvertes a l'audit"), `PRE-9` et suivants en section 5 de la
fiche mot-manquant (juste), `GRA-11` et suivants en section 5 de la fiche comparer (juste), `SOR-1`
a `SOR-4` renvoyes a la section 3 de `FICHE-bandit-manchot-reperes-cm1.md` (juste, verifie par la
ligne exacte ou `SOR-1` est introduite), `M21` renvoye a `FICHE-bandit-manchot-additions-cp.md`
(l'entree existe). Seules les deux citations ci-dessus, toutes deux "conditions prealables" pointant
d'un cran trop loin sur la sous-section suivante, sont fautives.

**Rien d'autre.** Aucune faute d'orthographe, aucun accent manquant ou en trop, aucun tiret
cadratin, aucune guillemet typographique, aucun emoji, aucune paraphrase de code en commentaire,
aucun chemin absolu de poste de travail :

- **Accents et style ASCII du corpus `squad/`/`docs/`** : `git diff 478e04d HEAD -- squad/ docs/ |
  grep '^+' | grep -oP '[accents]'` ne rend aucune occurrence - le corpus documentaire reste
  integralement non accentue, meme convention que s19 et s20. (Le contenu YAML servi a l'enfant,
  lui, est accentue - controle separe ci-dessous.)
- **Tiret cadratin, guillemets typographiques, emoji** : memes commandes sur le meme diff (plages
  U+2013/U+2014/U+201C/U+201D/U+2018/U+2019, plages d'emoji usuelles) : aucune occurrence dans les
  lignes ajoutees.
- **Chemins absolus de poste de travail** : `git diff --name-only 478e04d HEAD | bash
  .github/scripts/verifier-chemins.sh` -> "Aucun chemin de poste de travail ni nom de compte dans
  les fichiers verifies."

## Le point de fond central du brief : le chiffre "56 items" et sa population

Le brief demandait de verifier qu'aucune trace non amendee ne subsiste du chiffre "56 items sous le
pli" presente sans dire qu'il porte sur un echantillon de 167 items vus sur 428 (39 %), pas sur le
catalogue. J'ai recense toutes les occurrences de "56 items", "167 items" et "428/478 items" dans le
diff (`grep -n` cible) et verifie, document par document, si l'occurrence est anterieure ou
posterieure a la correction (`nadia-prof`, commit `3cfe9e3`, 2026-08-27 17:02:40, section 3 de
`FICHE-bandit-manchot-reperes-cm1.md`), et si un amendement date existe quand elle est anterieure.

**Verifie et sain :** `avis-faisabilite.md` (`theo-archi`) porte la mention non qualifiee en
premiere occurrence (section 1.3, ecrite a 14:34, avant la correction), mais le meme document
l'amende plus loin dans son propre texte (section 2.3, puis un bloc "Amendement date du 2026-08-27,
en fin de journee" complet, sections autour de la ligne 568) - la discipline "amender, pas
reecrire" est tenue a l'interieur d'un seul document ecrit en continu. `proposition-ines-rep-8.md`
est amende le 2026-08-27 a 17:37 (`2cbacbe`, apres la correction de `nadia-prof`), avec citation
explicite de sa source. `DEC-0051` porte l'amendement le plus complet, verifie a sa source primaire
par moi (voir plus bas). `garde-fou-aveugle-et-acc-1-s21-12.md` et `avis-nadia-done-pedagogique-
s21.md` (ecrits apres la correction) qualifient systematiquement la population des leurs premieres
mentions.

**Deux endroits ou le chiffre reste non qualifie dans l'etat final du depot, et que je route plutot
que de trancher ou de corriger moi-meme** (voir "Ce que je route" ci-dessous) :

1. `ADR-0063`, section Contexte : *"apres avoir mesure que 56 items du catalogue laissent l'indice
   sous le pli"*.
2. `planning.md`, quatre occurrences ("56 items" en trois endroits, "26 sur 56" repris jusque dans
   l'amendement de cloture du 2026-08-28), aucune jamais qualifiee de "sur un echantillon" alors que
   le meme document se met a jour a quatre reprises au fil du sprint.

Aucun des deux n'est une reecriture ni un chiffre invente : les deux sont fideles a ce que `ines-
design` avait mesure et communique le jour meme (56/167, le commit de creation du chiffre le dit
deja explicitement). Le defaut est d'omission, pas d'exactitude - une precision qui existait ailleurs
dans le depot au moment ou ces deux textes ont ete ecrits ou amendes en dernier n'y a pas ete
reportee.

## References croisees verifiees, et chiffres verifies a la source plutot qu'a la reference

**Toutes les references `ADR-00\d\d` et `DEC-00\d\d` du diff resolvent.** `git diff 478e04d HEAD |
grep '^+' | grep -oP 'ADR-00\d\d|DEC-00\d\d'` : chaque numero cite a un fichier qui porte ce numero
en tete de nom sous `squad/decisions/`.

**`ADR-0029` verifie a nouveau, cite a tort dans un test avant s21, corrige dans le meme commit que
son planning l'annonce.** `PaquetCm1ReperesTest.java` (lignes 73/162 avant correction) citait
`ADR-0029` pour justifier "trois propositions, jamais quatre" ; `ADR-0029` gouverne le decalage
d'animation par colonne et ne contient ni `font-size` ni `padding` (`grep -c "font-size\|padding"`
sur le fichier de l'`ADR` -> 0, verifie par moi). La correction de `S21-4` (`74e8f4a`) retire la
citation et la remplace par trois motifs pedagogiques dates de `nadia-prof` - lu en entier, aucune
mention d'`ADR-0029` ne subsiste a cet endroit, et la citation qui reste en tete de fichier (ligne
35, `ChaqueColonneQueLeContenuPeutProduirePorteSonDecalageTest`) est la bonne, verifiee separement.
Aucune autre occurrence fautive trouvee dans le reste du depot (`grep -rn "ADR-0029"` sur `*.java` et
`*.md`, chaque occurrence lue en contexte).

**Quatre chiffres verifies a leur source primaire, pas seulement a la reference qui les cite :**

1. **478 items sur 21 fichiers de contenu, a la tete de branche.** `ls
   adapters/adapter-content/src/main/resources/contenu/*.yaml | wc -l` -> **21** ; somme de
   `grep -c "enonce:"` sur les 21 fichiers -> **478** ; `grep -c "^  - id:"` sur `catalogue.yaml` ->
   **21**. Concorde exactement avec le troisieme amendement de `DEC-0051` (`camille-po`,
   2026-08-28).
2. **428 items sur 18 fichiers, a l'etat pre-s21 (commit `9e2e31a`, avant `S21-9`/`S21-10`/`S21-
   11`).** Rejoue independamment par `git ls-tree` et `git show` sur cet arbre historique plutot que
   par confiance dans la citation : **18 fichiers, 428 occurrences de `enonce:`**. Confirme que
   478 = 428 + 50 (16 + 16 + 18, les trois paquets neufs), comme `DEC-0051` l'affirme.
3. **La table de section 12 des douze `confirmation-archunit.md` amendes** (`amendement-jdk-douze-
   confirmations-s21-6.md`) : `grep -l 21.0.10 squad/sprints/*/confirmation-archunit.md` rejoue par
   moi -> **12 fichiers**, exactement ceux nommes (groupes A/B/C). `git diff caad2f0..HEAD --
   squad/sprints/*/confirmation-archunit.md | grep -E "^-[^-]"` -> **aucune ligne supprimee ni
   modifiee** sur les douze fichiers : la porte de sortie que ce lot demande explicitement a
   `relecteur-pedagogique` ("verifier qu'aucune reecriture silencieuse n'a eu lieu") est tenue,
   verifiee mecaniquement et non a l'oeil.
4. **L'arithmetique des deux tableaux recapitulatifs ajoutes a `MATRICE-couverture.md` le
   2026-08-28** : chaque ligne (mathematiques CP/CM1, francais CP/CM1) somme juste aux quatre
   colonnes de statut, le total des deux tableaux vaut **47** dans les deux cas, et 17/43 (39,5 %)
   puis 18/43 (41,9 %) correspondent aux fractions annoncees au dixieme pres.

## Verifie contre le code

`AuPlusTroisPropositionsParItemTest`, `ADR-0063` et le contrat de forme qu'il encode decrivent
exactement ce que le test verifie : perimetre derive de `contenu/` (pas de liste ecrite en dur),
chargement par le chargeur reel, cliquet exact de deux paquets et 33 items. L'amendement de
`.github/workflows/ci.yml` (`S21-12`/`ADR-0025`) correspond exactement a ce que le commentaire du
job annonce : balayage sur deux motifs (`outils/*-test.sh` et `squad/outils/*/*-test.sh`), garde de
non-vacuite sur chacun. Verifie que les deux motifs rendent chacun au moins un fichier sur l'arbre
actuel (4 et 2) : la garde n'est pas vide de sens.

## Contenu neuf servi a l'enfant : accents, ponctuation, espaces insecables

Les trois paquets neufs (`cp-syllabes.yaml` 16 items, `cp-mot-manquant.yaml` 16 items,
`cp-comparer.yaml` 18 items) et les trois entrees neuves de `catalogue.yaml` sont lus caractere par
caractere, pas a l'oeil sur un rendu.

**Accents et orthographe : aucun defaut trouve.** Toutes les capitales portent leur accent quand il
en faut un - `CHEMINÉE` (item 16 de `cp-syllabes.yaml`) est le cas exact que le brief demandait de
verifier (a la suite du `"VELO"` sans accent trouve par `nadia-prof` dans `contenu-repris/`) : verifie
correct, `É` present. `Où` (six occurrences dans `cp-comparer.yaml`) porte son accent grave. Aucun
mot du francais courant sans son diacritique trouve dans les 50 items et les six chaines de
catalogue relus integralement.

**Guillemets francais et espaces insecables : corrects sur `cp-syllabes.yaml`.** Les onze indices
qui emploient un guillemet francais (`« pa »`, `« la »`, etc.) portent chacun une espace insecable
(`U+00A0`, verifie par lecture d'octets et non a l'oeil) apres l'ouvrant et avant le fermant - 22
espaces insecables sur 22 attendues, zero espace ordinaire, meme convention que `SYN-9`/`ANT-5`
(`ADR-0060`).

**Espace insecable devant le point d'interrogation : absente sur `cp-comparer.yaml`, et c'est un
point a trancher, pas une correction que je fais moi-meme.** Les 19 occurrences de `?` dans
`cp-comparer.yaml` (les dix-huit enonces plus aucune autre) et la seule occurrence dans
`catalogue.yaml` (consigne de `bandit-manchot-syllabes-cp`, *"Combien de syllabes ?"*) portent
toutes une espace **ordinaire** (`U+0020`) devant le `?`, jamais une insecable - verifie par lecture
d'octets sur les 20 occurrences. C'est exactement la classe de defaut que `REP-6` avait corrigee a
s20 sur `cm1-reperes.yaml`, item 15. **Ce n'est pas une regression de s21** : j'ai balaye le meme
motif sur tout le catalogue servi (`grep`-style, octet avant chaque `?`) et **cm1-reperes.yaml est le
seul fichier du depot entier a employer l'insecable devant `?`** - les huit autres paquets qui
portent un `?` (`cm1-homophones`, `cm1-mesures`, `cm1-perimetre`, `cm1-synonymes`, `cm1-tables-
multiplication`, `cp-complements-10`, `cp-problemes-additifs`, `cp-soustractions`, plus `catalogue.
yaml` lui-meme pour des entrees anterieures a s21 comme *"Combien ça fait ?"*) emploient tous une
espace ordinaire. `cp-comparer.yaml` est donc conforme a l'usage dominant du catalogue, pas a
l'unique exception. Verifie a la source : `ADR-0060` (qui encode la regle pour les guillemets
francais) nomme explicitement, dans sa section "Ce qui me ferait changer d'avis", qu'"une decision
de `nadia-prof` etendant l'espace insecable aux autres signes doubles" est un evenement qui n'a pas
eu lieu - la correction de `REP-6` a s20 etait une correction ponctuelle sur un item, jamais
generalisee en regle ni en garde-fou. Je ne modifie donc pas ces 20 occurrences moi-meme : la
question de savoir si la regle doit s'etendre est nommee par `ADR-0060` comme relevant de
`nadia-prof`, pas de la redaction. Je la route.

## Ce que je route, sans le corriger moi-meme

- **A `theo-archi`** : `ADR-0063`, section Contexte, *"apres avoir mesure que 56 items du catalogue
  laissent l'indice sous le pli"*. La phrase ne porte pas la population (167 vus sur 428, 39 %)
  qu'`ADR-0063` lui-meme cite plus loin dans son propre tableau (428 items lus a la meme date). Ce
  n'est ni une reecriture a faire ni un chiffre a remplacer par moi : c'est la meme famille
  d'amendement que celui deja pose sur `avis-faisabilite.md` et sur `proposition-ines-rep-8.md`, et
  `theo-archi` est le mieux place pour dater et formuler le sien, sur le modele qu'il a deja pose
  ailleurs dans le meme sprint (`amendement-jdk-douze-confirmations-s21-6.md`, `ADR-0025`).

- **A `sofia-sm`** : `planning.md` cite "56 items" ou "26 sur 56" a quatre reprises (la phrase
  d'ouverture, le tableau des stories, deux fois dans les amendements de fusion dont celui du
  2026-08-28), sans jamais indiquer que la population est un echantillon de 167 items sur 428, alors
  que `planning.md` est le document le plus souvent remis a jour du sprint (quatre horodatages de
  fusion successifs) et que la qualification existe, datee, dans `DEC-0051` des le 2026-08-27 matin
  - avant meme la premiere des quatre mises a jour de `planning.md`. Une ligne de renvoi vers
  `DEC-0051` (sur le modele deja pose par `camille-po` dans ce meme document) fermerait le trou sans
  toucher aux chiffres ni aux verdicts qu'il rapporte.

- **A `nadia-prof`, avec `theo-archi` en copie** : l'absence d'espace insecable devant `?` sur les 20
  occurrences de `cp-comparer.yaml` et de `catalogue.yaml` (ci-dessus). Le fait est verifie a
  l'octet, la cause (aucune regle n'a jamais etendu l'insecable au-dela des guillemets francais) est
  nommee par `ADR-0060` lui-meme. La question - etendre la regle a tout le catalogue en une seule
  fois, la limiter au cas par cas comme `REP-6`, ou ne pas l'etendre - est produit/pedagogie, pas
  redaction : je ne la tranche pas.

## Collision de nommage : verifiee, aucune neuve trouvee

Les sept familles ouvertes ce sprint (`SEG-1` a `SEG-13`, `PRE-1` a `PRE-16`, `GRA-1` a `GRA-17`,
`SOR-1` a `SOR-4`, `MES-1`/`MES-2`, `GRN-1` a `GRN-3`, `ACC-1`/`ACC-2`) n'existaient dans aucun
fichier du depot avant `478e04d` (`git grep` sur chaque prefixe a ce commit -> aucune occurrence) :
aucune ne reprend un jeton deja attribue a autre chose. `REP-11` a `REP-14` sont neufs et coherents
(un seul sens, verifie par lecture de chaque premiere occurrence). `REP-9` et `REP-10` ne sont pas
neufs a s21 malgre ce que le brief laissait entendre : ils sont deja cites comme acquis dans
`DEC-0050` (s20, 2026-08-27) - fait mineur, sans consequence sur la lecture, note pour la precision.

**La collision deja connue `REP-7`/`REP-8` (s20, `camille-po` contre `nadia-prof`) n'est pas
aggravee.** Les 25 occurrences de `REP-8` ajoutees ce sprint emploient toutes le sens de
`nadia-prof` ("l'indice hors de vue", defaut de catalogue) de facon coherente ; aucune ne cree un
troisieme sens. Je n'ai pas trouve de nouvelle table de correspondance requise pour `REP-7`/`REP-8`
- la disambiguation posee par `camille-po` a s20 (`DEC-0050`, `backlog.md`) couvre toujours le
perimetre ; ce sprint ne la reouvre pas.

Les trois fiches neuves portent chacune sa table de correspondance (`squad/README.md`, section 2,
amendement s16) ; les deux erreurs de section qu'elles contenaient sont corrigees ci-dessus.

## Discipline "amender, jamais effacer" : verifiee sur les trois cas nommes par le brief

- **Les trois verdicts successifs de `nadia-prof` sur `cp-mot-manquant`** (REFUSE, REFUSE, VALIDE
  SOUS RESERVE) sont trois sections distinctes de `FICHE-bandit-manchot-mot-manquant-cp.md`
  (`## Verdict du 2026-08-28`, `## Verdict du reaudit, 2026-08-28`, `## Verdict du second reaudit,
  2026-08-28`), aucune remplacee ni retiree - verifie par lecture des en-tetes de section, pas
  suppose.
- **`camille-po` conserve la decision de retrait de `DEC-0052`.** La section 3 ("Decision de
  catalogue - retrait immediat") n'est touchee par aucune ligne supprimee dans le diff
  (`git log -p` sur le fichier, section 3 identique du premier au dernier commit qui la touche) ; le
  second amendement du 2026-08-28 explique pourquoi le retrait n'a plus d'objet sans reecrire la
  section, et verifie meme, contre `git log` sur `catalogue.yaml`, que le geste materiel n'a jamais
  ete execute (une seule addition de l'entree, aucune suppression).
- **`theo-archi` encadre ses chiffres d'origine sans les remplacer.** Verifie sur les douze
  `confirmation-archunit.md` (aucune ligne supprimee, ci-dessus) et sur `ADR-0025` (l'amendement de
  s21 s'ajoute a la fin du fichier, la section d'origine sur `outils/` reste intacte).

## Ce que j'ai trouve de faux dans le brief

Une seule imprecision, mineure et sans consequence sur la lecture : le brief presente `REP-9` et
`REP-10` comme faisant partie des jetons "ouverts ce sprint", alors qu'ils sont deja cites comme
acquis dans `DEC-0050` (s20, 2026-08-27, avant l'ouverture de s21). Le reste du brief se verifie :
le chiffre "56 items" a bien traverse plusieurs documents avant sa correction (cinq, plus les deux
que je route ci-dessus comme non encore fermes dans l'etat final) ; `ADR-0029` etait bien mal cite a
deux endroits avant s21 et les deux sont corriges ; aucune collision neuve de nommage trouvee au-dela
de celle deja connue de s20.

## Avis sur la lisibilite d'ensemble du corpus de s21

**Le plus dense et le plus auto-verifiant des sprints relus a ce jour, avec deux angles morts
precis plutot qu'un defaut diffus.** Sur 34 commits et 142 fichiers, la discipline "un chiffre faux
est amende a cote, jamais efface" tient a une echelle que je n'avais pas encore vue dans ce depot :
`camille-po` corrige sa propre `DEC-0051` deux fois de suite (157 vs 167, puis 428 vs 478) en
laissant chaque etat anterieur lisible ; `theo-archi` amende douze rapports de sprints clos en un
seul lot methodique, avec un controle qu'il route explicitement a moi et que j'ai rejoue ; `nadia-
prof` conserve trois verdicts successifs sur le meme paquet. Les deux points que je route ne sont
pas des reecritures manquees mais des **oublis de propagation** : le chiffre corrige existe, date et
source, ailleurs dans le meme sprint, au moment ou `ADR-0063` et `planning.md` sont ecrits ou
amendes pour la derniere fois - il n'a simplement pas ete reporte a ces deux endroits precis. Le
defaut d'espace insecable devant `?` n'est pas un defaut de ce sprint : c'est un gap catalogue-large
que ce sprint rend visible en ajoutant le seul paquet neuf qui utilise abondamment ce signe (19
occurrences, aucun autre paquet neuf de s21 n'en porte).

## Decisions prises

Aucune decision de fond. J'ai corrige deux references de section fausses dans les tables de
correspondance de `FICHE-bandit-manchot-mot-manquant-cp.md` et `FICHE-bandit-manchot-comparer-
cp.md` (redaction pure, sans ambiguite, verifiees contre la structure reelle du document cible). Je
route trois points a trancher, aucun ne m'appartenant : la propagation du chiffre "56 items
echantillon" a `ADR-0063` (`theo-archi`) et a `planning.md` (`sofia-sm`), et l'extension eventuelle
de l'espace insecable aux points d'interrogation du catalogue (`nadia-prof`, avec `theo-archi` en
copie sur la regle `ADR-0060`).
