# Relecture pedagogique - s20 (mot cible designe et conception de la saisie)

- **Auteur** : relecteur-pedagogique. **Date** : 2026-08-27.
- **Arbre relu** : `round-two` a `a266b4c` (tete au moment de la relecture, `git status` propre).
- **Perimetre etabli par moi-meme** : `git log --oneline` et `git diff --name-only` entre `d1cc948`
  (dernier commit de s19) et `a266b4c`, pas seulement la liste du brief. 33 commits non-merge, 78
  fichiers touches.

## Perimetre relu

Dans l'ordre du brief, plus ce que la comparaison de commits a fait apparaitre : l'integralite de
`squad/sprints/s20-mot-cible-designe-et-conception-de-la-saisie/` (`planning.md`,
`avis-faisabilite.md`, `confirmation-archunit.md`, `rapport-qualite.md`, `mesure-budget-vertical.md`,
`validation-ines-mot-designe-2rem.md`, `avis-nadia-cloture.md`), `FICHE-bandit-manchot-nature-mot-
cp.md` et `FICHE-bandit-manchot-reperes-cm1.md` en entier (1270 lignes a elles deux), `DEC-0046` a
`DEC-0050`, `ADR-0062`, les amendements de s20 a `ADR-0058`, `ADR-0059`, `DEC-0040`, `DEC-0043`,
`DEC-0045`, `DEC-0047`, `DEC-0049`, les deux index, les sections s20 de `MATRICE-couverture.md` et de
`docs/backlog/backlog.md`, `squad/programme/francais/competences-cm1.md`, les amendements hors sprint
verses dans `squad/README.md` et le `squad/rapports/JOURNAL-gouvernance-hors-sprint.md` neuf, et les
notes hors-sprint ajoutees a trois fichiers de s19 (`RAPPORT.md`, `retro.md`, `review.md` - hors du
brief, trouvees par la comparaison de commits, dument datees et non reecrites). Cote code :
`Question.java`, `EnonceSegment.java`, `PartieController.java`, `cp-nature-mot.yaml`,
`cm1-reperes.yaml`, `catalogue.yaml`, `recreo.css` (bloc `.mot-designe`), et un echantillon des tests
neufs (`UnePartDesigneeApparaitUneSeuleFoisDansSonEnonceTest` en entier, les autres par sondage).

## Ce que j'ai corrige

**Rien.** Aucune faute d'orthographe, aucun accent manquant ou en trop dans un texte redige sans
accent, aucun tiret cadratin, aucune guillemet typographique, aucun emoji, aucune paraphrase de code
en commentaire, aucun chemin absolu de poste de travail. Verifie et non suppose :

- **Accents et style ASCII du corpus `squad/`/`docs/`** : `git diff d1cc948 HEAD -- squad/ docs/ |
  grep '^+' | grep -oP '[accents]'` ne rend aucune occurrence - le corpus documentaire reste
  integralement non accentue, sans l'exception isolee que s19 avait trouvee (`proposition-ines-
  plafond-syn16.md`, `fusion-lots-7-a-9.md`).
- **Tiret cadratin et guillemets typographiques** : `grep -P '[U+2013 U+2014 U+201C U+201D U+2018
  U+2019]'` sur le meme diff, aucune occurrence.
- **Emoji et symboles Unicode decoratifs** : aucune occurrence dans le diff.
- **Chemins absolus de poste de travail** : `git diff --name-only d1cc948 HEAD | bash
  .github/scripts/verifier-chemins.sh` - "Aucun chemin de poste de travail ni nom de compte dans les
  fichiers verifies", rejoue par moi independamment de la commande citee dans `rapport-qualite.md`.
- **Contenu servi a l'enfant, exigence d'accent integral (`DEC-0002`)** : les seize items de
  `cp-nature-mot.yaml` et les seize items de `cm1-reperes.yaml` sont accentues correctement, phrase
  par phrase. L'espace insecable devant `?` sur l'item 15 de `cm1-reperes.yaml` (correction `REP-6`
  de `nadia-prof`) est bien presente en octets (`\xa0`, verifie par script), pas une espace ordinaire
  qui se lirait pareil sur un diff texte.

Sur 78 fichiers touches par 33 commits, cette absence totale de defaut textuel n'est pas une surprise
isolee : chaque document du sprint porte deja sa propre section d'auto-verification (chiffres
recalcules, references citees avec leur source, phrases perimees amendees et non reecrites). La
qualite redactionnelle de ce sprint est un effet de cette discipline, pas de ma seule relecture.

## References croisees verifiees, et quelques chiffres verifies a la source plutot qu'a la reference

**Toutes les references testables resolvent.** `git diff d1cc948 HEAD | grep '^+' | grep -oP
'ADR-00\d\d'` et l'equivalent pour `DEC-00\d\d` : chaque numero cite dans le diff a un fichier sous
`squad/decisions/adr/` ou `squad/decisions/produit/` qui porte ce numero en tete de nom. Aucune
reference cassee trouvee, contrairement au risque nomme dans le brief (`ADR-0045` introuvable a s14).

**Quatre chiffres verifies a leur source primaire, pas seulement a la reference qui les cite :**

1. **30 regles `ArchRule` reelles** (cite par `theo-archi` dans `confirmation-archunit.md` et
   `avis-faisabilite.md`, par `yanis-qa` dans `rapport-qualite.md`) : rejoue par moi,
   `grep -rn "static final ArchRule\|static ArchRule" --include=*.java . | grep -v "/contreexemple/"
   | grep -v "/target/" | wc -l` -> **30**. Concorde avec les deux mesures independantes.
2. **33 commits non-merge sur la plage `d1cc948..77dd467`** (cite par `yanis-qa`, `rapport-
   qualite.md`, section 4) : rejoue, `git log --no-merges --format=%s d1cc948..77dd467 | wc -l` ->
   **33**, et `verifier-prefixes.sh` sur la meme liste rend le meme verdict que celui cite.
3. **`git diff --name-only 77dd467..6228d77 -- adapters domain application bootstrap` ne rend aucun
   fichier** (cite par `nadia-prof`, `avis-nadia-cloture.md` et `FICHE-bandit-manchot-reperes-
   cm1.md`, note du 2026-08-27, pour etablir que l'artefact qu'elle a joue est identique a la tete de
   branche) : rejoue a l'identique, sortie vide confirmee.
4. **Le denominateur atteignable (43/47) et le compte `couvert-valide` (13 -> 14 -> 15) sur
   `MATRICE-couverture.md`** : relu ligne a ligne sur les quatre tableaux recapitulatifs ajoutes ce
   sprint (2026-08-25 x3, 2026-08-27), l'arithmetique de chaque ligne (total, `non couvert`, `en
   cours`, `couvert-valide`, `non couvrable en l'etat`) somme juste a chaque etape, et les
   pourcentages (30,2 % ; 32,6 % ; 34,9 %) correspondent aux fractions qu'ils annoncent au dixieme
   pres.

Je n'ai pas cherche a tout reverifier - un sprint qui verse ses propres sources a cote de chaque
chiffre rend la reverification cible plus utile qu'une reverification exhaustive.

## Verifie contre le code

`Question.java`, `EnonceSegment.java`, `PartieController.segmentsEnonce`, `cp-nature-mot.yaml`,
`cm1-reperes.yaml` et l'entree neuve de `catalogue.yaml` decrivent exactement ce que la Javadoc et
les commentaires qui les accompagnent annoncent : une composante facultative unique, aucune valeur
factice sur les paquets non marques, une derivation par valeur declaree et non par ponctuation. Rien
a corriger, rien a signaler.

**Une exception, deja instruite par `nadia-prof` elle-meme et non encore traitee.** Le commentaire de
`.mot-designe` dans `adapters/adapter-web/src/main/resources/static/css/recreo.css` (ecrit par
`marc-dev`, commit `6b8eebe`, le 2026-08-25) annonce *"Trois canaux non colores comme nadia-prof
l'exige (jamais la seule couleur) : une boite de fond, un rembourrage, une police superieure au
reste de la phrase"*. `nadia-prof` a mesure le meme jour, puis reconfirme le 2026-08-27
(`FICHE-bandit-manchot-nature-mot-cp.md`, sections 4 et "Controle du 2026-08-27", point 4) que la
boite de fond est un **canal colore** (contraste 1,04:1 contre le fond de page, 1,045:1 sous
achromatopsie emulee - il disparait avec la couleur) et que **seul l'agrandissement de police
survit** a la suppression totale de la couleur. Le commentaire compte donc trois canaux non colores
la ou la mesure en etablit un. `nadia-prof` l'a route explicitement a `relecteur-pedagogique` et
`ines-design` le 2026-08-27 (meme fiche, section 4 : *"Je route la phrase a relecteur-pedagogique et
ines-design"*) ; personne n'a encore corrige le commentaire au moment de cette relecture.

Je ne le corrige pas moi-meme : la reformulation touche a la fois une description de code
(`relecteur-pedagogique`) et une question de forme visuelle encore ouverte (`NAT-12`, non bloquante -
`ines-design` a la piste d'un liseret sombre qui rendrait la boite lisible sans couleur, ce qui
changerait a nouveau le compte exact de canaux). Corriger le texte maintenant risquerait d'ecrire un
chiffre qui redevient faux des que `NAT-12` bouge. Je le signale avec sa preuve plutot que de
trancher un nombre qui n'est pas encore stable.

## Ce que je route, sans le corriger moi-meme

- **A `marc-dev` (auteur) et `ines-design`, avec `theo-archi` en copie** : le commentaire de
  `.mot-designe` dans `recreo.css` (ci-dessus). Deja route par `nadia-prof` le 2026-08-27 ; je
  confirme le defaut et j'attends que la forme finale de `NAT-12` soit tranchee avant de fixer le
  texte, pour ne pas ecrire un second chiffre qui se perime.

- **A `sofia-sm`, sur la collision de nommage `REP-7`/`REP-8`.** Le brief demande si l'etat actuel
  est lisible pour un lecteur qui arrive, et une forme qui leve l'ambiguite sans reecrire
  l'histoire.

  **Le fait, verifie a la source.** `camille-po` a invente les labels `REP-7` (le pli) et `REP-8`
  (le cinquieme libelle) le 2026-08-25, dans `docs/backlog/backlog.md`, pour nommer les deux
  criteres correctifs du refus de `cm1-reperes` (`DEC-0049`). `nadia-prof` a reutilise `REP-7` et
  `REP-8` le 2026-08-27 pour deux reserves neuves et sans rapport (`REP-7` : la composition des
  propositions predit la reponse ; `REP-8` : l'indice hors de vue, defaut de catalogue). Ce sont
  quatre objets distincts sous deux paires de labels identiques. `camille-po` l'a deja signale,
  sans renommer, a deux endroits : `docs/backlog/backlog.md` (paragraphe *"Collision de nommage,
  signalee et non corrigee"*, apres la mise a jour du 2026-08-27 de `S20-2`) et `DEC-0050` (section
  5, *"Collision de nommage signalee, pas corrigee ici"*). Les deux paragraphes datent chaque paire
  de labels et disent explicitement qu'elle ne renomme pas pour ne pas effacer la trace de ce qui a
  motive la correction de `lea-dev`. `FICHE-bandit-manchot-reperes-cm1.md` n'emploie que la
  nomenclature du 2026-08-27, sans jamais reprendre les deux labels du 2026-08-25 - elle ne cree
  donc aucune collision supplementaire.

  **Ce qui reste illisible malgre cette double disambiguation : `rapport-qualite.md`
  (`yanis-qa`).** Ce document, date du 2026-08-27 dans son en-tete, emploie `REP-7` (*"le pli"*) et
  `REP-8` a quatre reprises (sections 3, 7, et le compte rendu final) **sans aucune date ni renvoi**
  - dans le sens du 2026-08-25, celui de `camille-po`. Ce n'est pas une erreur de `yanis-qa` : son
  rapport mesure l'arbre a `77dd467`, un commit **anterieur** a celui ou `nadia-prof` introduit sa
  propre paire `REP-7`/`REP-8` (`c7ad0b2`, verifie par `git log --oneline` : `9ccbb1d` puis
  `6228d77` puis `c7ad0b2`) - au moment ou il ecrit, une seule paire de labels existe et son usage
  est sans ambiguite. Mais le fichier repose aujourd'hui, tel quel, dans le meme dossier de sprint
  que `avis-nadia-cloture.md` et a cote de `DEC-0050`, qui emploient la meme paire de labels pour
  des objets differents et sans le renvoi que `camille-po` a pose dans ses deux propres fichiers. Un
  lecteur qui ouvre `rapport-qualite.md` isolement, ou qui `grep`e `REP-7` a travers le depot sans
  trier par date de commit, lit une contradiction apparente entre deux documents dates du meme jour
  calendaire.

  **Reponse a la question posee : partiellement lisible.** Lisible la ou `camille-po` a pose sa
  propre disambiguation (`backlog.md`, `DEC-0050`) ; pas lisible dans `rapport-qualite.md`, seul
  document du sprint a employer ces deux labels sans date ni renvoi. La collision n'est donc pas
  fermee par le travail deja fait : elle reste ouverte a l'endroit precis que la disambiguation de
  `camille-po` n'a pas touche.

  **La forme que je propose, sans trancher a la place de `sofia-sm` ni reecrire `rapport-
  qualite.md` a la place de `yanis-qa` :** un renvoi d'une ligne, ajoute par `yanis-qa` ou `sofia-sm`
  a cote des quatre occurrences (sections 3 et 7, compte rendu court), sur le modele deja pose par
  `camille-po` - par exemple *"`REP-7`/`REP-8`, labels de `camille-po` du 2026-08-25 (le pli, le
  cinquieme libelle), distincts des `REP-7`/`REP-8` que `nadia-prof` introduit le 2026-08-27, voir
  `DEC-0050`"*. Cela ne modifie aucun chiffre ni aucun verdict de `yanis-qa` : c'est une date ajoutee
  a une reference, pas une reecriture de son constat. Je ne le fais pas moi-meme parce que la
  disambiguation touche le sens d'un document produit par un autre agent sur un fait dont je ne suis
  pas la source ; `camille-po`, seule auparavant a avoir tranche de ne pas renommer, est la mieux
  placee pour dire si ce renvoi doit suivre exactement sa propre formule.

## Avis sur la lisibilite d'ensemble du corpus de s20

**Dense, cite ses propres corrections, et se relit lui-meme avant que je n'aie a le faire.** Ce
sprint porte deux constats qui pesaient sur s19 - un chiffre sans sa methode, une reference qui
pointe vers rien - et je n'en ai trouve aucune nouvelle occurrence dans le perimetre relu, alors que
c'est un sprint plus mouvemente (deux verdicts REFUSE puis leves, un trou de gouvernance reproduit
deux fois, une piste technique entiere chiffree puis abandonnee). Trois habitudes portent cette
solidite, verifiees en les lisant plutot que supposees :

- **Un chiffre faux est toujours amende a cote, jamais efface.** `theo-archi` corrige trois
  affirmations fausses de ses propres `ADR-0059` et `avis-faisabilite.md` (141/144 devenu 33/144, une
  ligne de constante devenue deux fichiers, un declencheur qui aurait produit une table perimee) sans
  reecrire une seule des phrases d'origine. `nadia-prof` fait de meme sur sa propre barre du pli
  (section "Trois erreurs a moi" de `avis-nadia-cloture.md`).
- **Un verdict retire ou refuse garde son propre document, meme apres avoir ete leve.** `DEC-0049`
  (REFUSE) et `DEC-0050` (VALIDE SOUS RESERVE) restent deux fichiers distincts avec une note datee
  qui renvoie de l'un a l'autre, plutot qu'une reecriture sur place - la meme discipline que
  `DEC-0048`/`DEC-0045`.
- **Une mesure est reproduite independamment avant d'etre citee comme un fait acquis.** `yanis-qa`
  rejoue le faux vert ArchUnit de `theo-archi` plutot que de le croire sur parole
  (`rapport-qualite.md`, section 6) ; `theo-archi` valide son propre harnais de mesure contre quatre
  chiffres deja au dossier avant de publier un chiffre neuf (`mesure-budget-vertical.md`, section 0).

**Le seul point qui reste ouvert malgre cette discipline est celui que j'ai route ci-dessus** : une
collision de labels, deja signalee par son auteure a l'endroit ou elle la decouvre, mais dont la
disambiguation ne couvre pas le seul document ecrit avant que la collision n'existe. C'est
exactement le mode de defaillance que ce depot documente ailleurs sous un autre nom - une correction
juste au moment ou elle est ecrite, qui ne suit pas le document qu'elle ne pouvait pas encore viser.

## Ce que j'ai trouve de faux dans le brief

Rien. Les deux points que le brief signale "sans prejuger" se verifient tous deux : la collision
`REP-7`/`REP-8` est reelle et deja partiellement signalee par `camille-po` (voir ci-dessus, avec la
partie non couverte que j'ajoute) ; et les instances de "chiffre sans methode" que le brief pointe
vers `squad/README.md` section 4 et vers l'historique de `CLAUDE.md` (l'amendement du 2026-08-17,
lui-meme corrige au 2026-08-19) sont bien celles que ces fichiers portent, verifiees en les lisant
plutot que recopiees.

## Decisions prises

Aucune. Cette relecture ne corrige aucun texte (rien a corriger dans le perimetre releve) et ne
tranche aucune question de produit, d'architecture ou de pedagogie. Le seul enonce que je verse a la
gouvernance est le renvoi propose dans "Ce que je route" pour `rapport-qualite.md` - une proposition
de forme, pas une decision, et je la remets a `sofia-sm` et `camille-po` plutot que de la trancher.
