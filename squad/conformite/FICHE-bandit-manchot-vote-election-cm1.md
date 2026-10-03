# Fiche de conformité - bandit-manchot-vote-election-cm1

- **Mécanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-emc-vote-election.yaml`
  (12 items, type `question`), écrit par `sofia-sm`, casquette dev (commit `9162d64f`).
- **Entrée de catalogue** : `bandit-manchot-vote-election-cm1`, titre servi *"Le vote et la
  représentation"*, consigne servie *"Lis bien, puis dis qui vote, qui est élu, ou ce que fait un
  délégué."*, `nombreDeManches: 10`.
- **Ligne déclarée** : `EMC-CM1-10`. **Story** : `S47-EMC-VOTE-CM1` (`docs/backlog/backlog.md`,
  section "Sprint 47").
- **Compétence programme visée** : enseignement moral et civique, CM1 (cycle 3), domaine "Construire
  une culture civique" - `squad/programme/enseignement-moral-et-civique/competences-cm1.md`,
  `EMC-CM1-10` : *"Comprendre le sens du vote et de la représentation (délégué de classe, élection,
  suffrage universel)."*
- **Conditions opposables** : `EMC-5`, `EMC-6`, `EMC-8`, `HIS-2` et `HIS-3` (introduction du jugement
  de faisabilité d'EMC CM1), ma décision Q6 du 2026-10-02
  (`squad/conformite/ARBITRAGE-PREALABLE-s47-trois-questions-de-theo-archi.md`, règles 1 à 5),
  `FER-1`, `FER-2`, `FER-3`. `CYC-1` non due (planning de s47, section 3 : la ligne miroir du CP est
  `non couvrable en l'etat`).
- **Lien décision** : `DEC-0104`.
- **Statut global** : **VALIDÉ** le 2026-10-03 par `nadia-prof` (s48, instance A, quatrième
  passage), les deux réserves levées. Historique : **VALIDÉ SOUS RÉSERVE** le 2026-10-02 (s47,
  instance B, troisième passage), deux réserves ; **REFUSÉ** le 2026-10-02 (second passage, un
  motif, item 5) et **REFUSÉ** le 2026-10-02 (premier passage, deux motifs, items 5 et 6), refus
  conservés ci-dessous.
  Fiche ouverte par ce premier verdict : aucune fiche n'existait pour ce jeu.
- **Dernière mise à jour** : 2026-10-03 (quatrième passage, s48).

---

## Bloc de validation pédagogique - nadia-prof UNIQUEMENT

- **Verdict** : **REFUSÉ**
- **Date** : 2026-10-02
- **Auditrice** : `nadia-prof`, instance B (`DEC-0104`).
- **Audit joué** : **oui**, paquet entier.

**Motif, en une phrase** : deux faits de droit électoral sont faux - l'item 5 dit *"En France, toute
personne majeure peut voter"*, alors que seuls les citoyens votent, et l'item 6 dit *"Depuis 1944,
les femmes votent en France"*, alors qu'elles ont obtenu le droit de vote en 1944 et voté pour la
première fois en 1945.

### 1. Ce que j'ai fait

Worktree dédié `s47-nadia-audit-b`, fondé sur `round-two` à `eef35179`, qui porte le paquet
(`9162d64f`, fusion `3a56f9d1`). Jar construit par `bash outils/mvn-jdk21.sh -B package
-DskipTests`, lancé sur le JDK 21 du poste, sur un port dédié distinct de celui de l'instance A.
Done technique acquis par `yanis-qa` sur le job `build`
(`squad/sprints/s47-tables-ce1-decouverte-cp-et-emc/rapport-qualite.md`, conclusion). J'ai relancé
moi-même, avec `-am`, sur le module `adapter-content` seul : `PaquetCm1EmcVoteElectionTest` (8) et
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` (28), 0 échec. Je n'ai lancé aucun autre
job de la CI et je n'en déduis rien.

**Cinq parties complètes de dix manches** par appels HTTP réels : une avec trois erreurs à chaque
manche jusqu'à la révélation, quatre mélangées (juste du premier coup, après une, deux ou trois
erreurs). **Les douze items ont été tirés et joués.** Le traitement de l'erreur est celui de la
mécanique : indice après une erreur, révélation après la troisième.

### 2. Ancrage au programme

La compétence est la bonne, et c'est bien celle du cycle 3 : qui vote, qui est élu, par qui, pour
combien de temps, et ce que fait un représentant. Les trois termes du libellé sont chacun bonne
réponse (`FER-1`) : délégué de classe (items 1, 2), élection (3, 4), suffrage universel (5, 6). Deux
tâches distinctes de six items (`FER-3`), douze items (`FER-2`). La mention exigée d'avance
(*"connaissance du vote et de la représentation seule ; participer à une décision collective reste
non couvrable"*) est due dans la matrice quand la ligne bougera.

### 3. Ma décision Q6, vérifiée règle par règle

| Règle | Tenue | Ce que j'ai vérifié |
|---|---|---|
| 1. Aucune bonne réponse ne décrit ce que décide le conseil municipal | **oui** | Les douze bonnes réponses lues une à une : aucune ne parle de budget, de projet ou de nom de rue. Aucune situation de `cm1-emc-institutions.yaml` n'est reprise |
| 2. Le maire n'est pas élu par les habitants | **oui** | Item 10 : *"Qui élit le maire ?"*, bonne réponse *"le conseil municipal"*, *"les électeurs de la commune"* en distracteur. Source `CGCT, article L2122-4`, exacte. Aucun autre item ne laisse entendre le contraire |
| 3. *"les électeurs de la commune"*, jamais *"les habitants"* | **oui** | Le mot *"habitants"* n'apparaît dans aucun texte servi. Items 3 et 9 disent *"les électeurs"* |
| 4. Chaque fait institutionnel porte sa source (`HIS-3`) | **non, item 5** ; source inexacte item 12 | Section 4 et section 6 |
| 5. Délégué de classe à la troisième personne (`EMC-6`), aucune intention de vote demandée (`EMC-8`) | **oui** | *"Dans une classe"*, *"un élève"*, *"ses camarades"* ; jamais *"ta classe"*. Aucun énoncé ne demande à l'enfant pour qui il voterait ni ce qu'il ferait |

### 4. Motif 1 - l'item 5 est faux

*"En France, toute personne majeure peut voter, quels que soient sa richesse, son métier ou son sexe.
Comment appelle-t-on ce droit de vote accordé à tous ?"*

**Le fait.** *"Sont électeurs, dans les conditions déterminées par la loi, tous les nationaux
français majeurs des deux sexes, jouissant de leurs droits civils et politiques"* (Constitution du 4
octobre 1958, article 3). Une personne majeure étrangère qui vit en France ne vote pas ; une personne
d'un pays de l'Union européenne vote aux seules élections municipales et européennes (article 88-3).
C'est exactement le piège que j'ai nommé dans Q6, règle 3 : *"les habitants" ne votent pas tous.*

**Ce que l'enfant apprend.** Que tout adulte vote. Dans une classe de CM1, des enfants ont des parents
qui ne votent pas en France : l'énoncé leur dit une chose fausse sur leur propre famille.

**La source.** Le marqueur dit *"fait de droit électoral enseigné au cycle 3"*. Ce n'est pas une
source, et la formulation *"toute personne majeure"* engage une couverture : c'est la famille (b) de
ma précision du 2026-09-03, qui demande une source vérifiable (même raisonnement que les items 10 et
11 de `FICHE-bandit-manchot-institutions-cm1.md`, second passage). L'item 11 du même paquet et
l'indice de l'item 5 disent eux-mêmes *"tous les citoyens majeurs"* : c'est la formulation juste.

### 5. Motif 2 - l'item 6 est faux sur sa date

*"Depuis 1944, les femmes votent en France au même titre que les hommes."*

**Le fait.** L'ordonnance du 21 avril 1944 (article 17) rend les femmes électrices et éligibles. Elles
votent pour la première fois le 29 avril 1945, aux élections municipales. *"Depuis 1944, les femmes
votent"* est faux ; *"Depuis 1944, les femmes ont le droit de vote"* est juste. Le marqueur de l'item
dit lui-même *"droit de vote des femmes"* : c'est l'énoncé qui s'en écarte.

**Pourquoi c'est un motif et non un détail.** C'est la date que l'enfant de CM1 retient de cet item.
Une erreur factuelle dans un contenu scolaire est un motif de refus à elle seule (`HIS-3`, motif).

### 6. À reprendre dans le même passage, sans être des motifs de refus

1. **Items 1 et 2, deux distracteurs qui ne sont pas des personnes.** La question demande une
   personne (*"Comment appelle-t-on ce camarade ?"*, *"Qui est cet élève ?"*) ; *"une élection"* et
   *"le suffrage universel"* n'en sont pas. L'enfant trouve sans rien savoir. Même défaut que l'item
   11 de `cm1-emc-institutions.yaml` (s46, *"deux distracteurs absurdes"*).
2. **Item 4, l'indice pousse vers le distracteur.** *"Pour choisir le président de la République,
   tous les électeurs du pays sont appelés à voter [...]. Comment appelle-t-on ce moment ?"* Un enfant
   qui répond *"le suffrage universel"* reçoit l'indice *"C'est un vote qui concerne tout le pays"*,
   qui confirme son erreur : dans ce paquet, *"tous"* est le trait du suffrage universel (items 5, 6,
   11). L'indice doit porter sur ce qui distingue une élection : un jour, un vote, une personne
   choisie.
3. **Item 12, source inexacte.** La durée de six ans du mandat des conseillers municipaux est fixée par
   le code électoral (article L227), pas par le CGCT comme le dit le marqueur. Le fait est exact.
4. **Consigne servie.** *"dis qui vote, qui est élu, ou ce que fait un délégué"* ne couvre pas la tâche
   A (items 1 à 6), qui demande le nom d'un terme. Exemple : *"Lis bien, puis trouve le bon mot, ou
   dis qui vote et qui est élu."*

### 7. Ce qui doit changer pour que le lot repasse

| Fait | À qui |
|---|---|
| Item 5 : remplacer *"toute personne majeure"* par *"tous les citoyens majeurs"* (ou *"toute personne majeure de nationalité française"*), et porter la source au marqueur (Constitution, article 3) | `camille-po` en fait la story, développeur affecté par `sofia-sm` |
| Item 6 : *"Depuis 1944, les femmes ont le droit de vote en France"* ; garder la source (ordonnance du 21 avril 1944) | même story |
| Les quatre points de la section 6 | même story, au choix de `camille-po` |

Je rejouerai le paquet entier à son retour, pas seulement les items corrigés.

### 8. Ce dont je reste la seule garde sur cette ligne

1. **L'exactitude de chaque fait de droit électoral** : aucun test ne sait qui vote en France. La CI
   est verte sur deux faits faux.
2. **Q6, règle 1** : qu'aucune bonne réponse ne décrive le rôle du conseil municipal.
   `PaquetCm1EmcVoteElectionTest.aucuneBonneReponseNeDecritCeQueDecideLeConseilMunicipal` cherche
   cinq mots (*budget*, *projet*, *nom de la rue*, *terrain de sport*, *lampadaire*) ; une bonne
   réponse de rôle écrite avec d'autres mots passe.
3. **La qualité des sources `HIS-3`** : aucun test ne lit le commentaire du paquet.
4. **`EMC-6` et `EMC-8`** sur le délégué de classe : aucune méthode du test du paquet ne les vérifie.

**Identifiants** : aucun neuf, aucun renommé.

**Signé** : `nadia-prof`, s47, instance B, 2026-10-02.

---

## Bloc de validation pédagogique - second passage - nadia-prof UNIQUEMENT

- **Verdict** : **REFUSÉ**
- **Date** : 2026-10-02
- **Auditrice** : `nadia-prof`, instance B (`DEC-0104`), second passage.
- **Audit joué** : **oui**, paquet entier.
- **Contenu audité** : `cm1-emc-vote-election.yaml` et consigne du catalogue après `923bf998`
  (`sofia-sm`, casquette dev).

**Motif, en une phrase** : l'item 5 corrigé dit *"En France, le droit de vote est réservé aux
personnes de nationalité française et majeures"*, ce qui est faux - les citoyens de l'Union
européenne qui résident en France votent aux élections municipales et européennes (Constitution,
article 88-3) - et ce paquet parle lui-même des élections municipales (items 3 et 9).

### 1. Ce que j'ai fait

Worktree dédié `s47-nadia-audit-b2`, fondé sur `round-two` à `c400ac2a`, lignée vérifiée par
`outils/verifier-lignee.sh round-two`. Jar construit par `bash outils/mvn-jdk21.sh -B package
-DskipTests` (Maven sur le JDK 21.0.10 de la toolchain), lancé sur un port dédié. Je n'ai lancé
aucun job de la CI à ce passage et je n'en déduis rien.

**Six parties complètes de dix manches** par appels HTTP réels : une juste de bout en bout, une
avec trois erreurs à chaque manche jusqu'à la révélation, quatre mélangées (60 manches : 19 justes
du premier coup, 11 après une erreur, 8 après deux, 22 avec révélation). **Les douze items ont été
tirés et joués** ; à chaque manche, les trois boutons servis étaient exactement ceux du paquet. Les
douze indices ont été lus à l'écran. Consigne servie : *"Lis bien, puis trouve le bon mot, ou dis
qui vote, qui est élu, ou ce que fait un délégué."*

### 2. Les sources, vérifiées par moi

Je n'ai aucun outil de consultation en ligne à ce passage : je vérifie sur ma connaissance du texte
des articles, que je cite, et non sur le résumé de la correction.

- **Constitution du 4 octobre 1958, article 3, alinéa 4** : *"Sont électeurs, dans les conditions
  déterminées par la loi, tous les nationaux français majeurs des deux sexes, jouissant de leurs
  droits civils et politiques."* L'article dit qui est électeur ; il ne dit pas que le droit de
  vote leur est réservé.
- **Constitution, article 88-3** : le droit de vote et d'éligibilité aux élections municipales peut
  être accordé aux seuls citoyens de l'Union résidant en France. C'est le cas (loi organique du 25
  mai 1998). Les mêmes citoyens votent en France aux élections européennes. Je l'avais moi-même
  écrit en section 4 du premier passage.
- **Ordonnance du 21 avril 1944, article 17** : *"Les femmes sont électrices et éligibles dans les
  mêmes conditions que les hommes."* Premier vote le 29 avril 1945 (municipales).
- **Code électoral, article L227** : les conseillers municipaux sont élus pour six ans.

### 3. Motif - l'item 5 corrigé est faux

*"En France, le droit de vote est réservé aux personnes de nationalité française et majeures, quels
que soient leur richesse, leur métier ou leur sexe. Comment appelle-t-on ce droit de vote accordé à
tous les citoyens ?"*

**Le fait.** *"Réservé aux"* affirme que personne d'autre ne vote. Un adulte portugais, italien ou
belge qui vit en France vote aux élections municipales et européennes. L'item 3 et l'item 9 de ce
paquet parlent des électeurs de la commune, qui comptent ces citoyens.

**Ce que l'enfant apprend.** Des enfants de CM1 ont un parent européen qui vote aux municipales :
l'énoncé leur dit que ce parent n'a pas le droit de vote. C'est le même dommage que celui que j'ai
nommé pour la formulation du premier passage, dans l'autre sens.

**Pourquoi je ne déplace pas mon poteau.** Ma barre (section 7 du premier passage) proposait *"tous
les citoyens majeurs"* ou *"toute personne majeure de nationalité française"* : deux formulations
qui disent qui vote, sans dire que personne d'autre ne vote. La correction n'a repris aucune des
deux ; elle a ajouté une exclusivité, et c'est cette exclusivité qui est fausse. Si la correction
avait repris l'une des deux formes que j'avais écrites, je l'aurais acceptée. **Une erreur
factuelle dans un contenu scolaire est un motif de refus à elle seule** (`HIS-3`).

**Le marqueur.** *"Constitution du 4 octobre 1958, article 3"* est la bonne source pour *"sont
électeurs les nationaux français majeurs"*. Elle ne soutient pas *"réservé"*.

### 4. Motif 2 du premier passage - levé

Item 6 servi : *"Depuis 1944, les femmes ont le droit de vote en France au même titre que les
hommes."* Exact (ordonnance du 21 avril 1944, article 17). Indice *"Avant cette date, ce droit
était réservé aux hommes."* exact. Je lève.

### 5. Points non bloquants du premier passage

| Point (section 6) | Tenu | Ce que j'ai vu à l'écran |
|---|---|---|
| 1. Items 1 et 2, distracteurs qui ne sont pas des personnes | **oui** | *"le maire"*, *"le conseil municipal"* : la barre que j'avais écrite (des personnes) est atteinte, je lève |
| 2. Item 4, l'indice pousse vers le distracteur | **oui** | *"Ce jour-là, les électeurs votent, puis une seule personne est élue."* : porte sur ce qui distingue une élection |
| 3. Item 12, source | **oui** | `code electoral, article L227`, exacte |
| 4. Consigne servie | **oui** | *"trouve le bon mot"* couvre la tâche A |

Le reste du paquet (items 1 à 4, 7 à 12) est rejoué et exact ; ma décision Q6 (règles 1 à 5),
`EMC-6`, `EMC-8`, `FER-1` à `FER-3` restent tenues, avec les mêmes vérifications qu'au premier
passage.

### 6. Ce qui doit changer pour que le lot repasse

| Fait | À qui |
|---|---|
| Item 5 : retirer l'exclusivité. Forme acceptée, par exemple : *"En France, tous les citoyens majeurs ont le droit de vote, quels que soient leur richesse, leur métier ou leur sexe."* Garder la source (Constitution, article 3) | `camille-po` en fait la story, développeur affecté par `sofia-sm` |

Je rejouerai le paquet entier à son retour. Aucun autre item n'est en cause.

### 7. Réserves nouvelles, datées du 2026-10-02, sans être des motifs de refus

Elles n'étaient pas dans ma barre du premier passage ; je ne les y ajoute pas.

1. **Items 3 à 6, un distracteur hors de sa catégorie.** *"le délégué de classe"* est une personne,
   et il est distracteur de questions qui demandent un vote (*"Comment appelle-t-on ce vote ?"*,
   *"ce moment ?"*) ou un droit (*"ce droit de vote"*, *"ce principe"*). C'est le défaut que j'ai
   relevé aux items 1 et 2, dans l'autre sens, et que je n'avais pas vu sur ces quatre items.
2. **Item 11, la question est mal formée.** *"Qui le suffrage universel permet-il de faire voter ?"*
   Le suffrage universel ne fait voter personne : il donne le droit de voter. Exemple : *"Avec le
   suffrage universel, qui a le droit de voter ?"*

### 8. Ce dont je reste la seule garde sur cette ligne

Inchangé depuis le premier passage (section 8 ci-dessus). Le point 1 a encore mordu : **la CI est
verte sur un fait électoral faux pour la seconde fois**, introduit cette fois par la correction
elle-même. Aucun test ne sait qui vote en France, ni à quelle élection.

**Identifiants** : aucun neuf, aucun renommé.

**Signé** : `nadia-prof`, s47, instance B, second passage, 2026-10-02.

---

## Bloc de validation pédagogique - troisième passage - nadia-prof UNIQUEMENT

- **Verdict** : **VALIDÉ SOUS RÉSERVE**
- **Date** : 2026-10-02
- **Auditrice** : `nadia-prof`, instance B (`DEC-0104`), troisième passage.
- **Audit joué** : **oui**, paquet entier.
- **Contenu audité** : `cm1-emc-vote-election.yaml` après `5aa88a4e` (`sofia-sm`, casquette dev,
  fusion `b52e743f`).

**Conclusion, en une phrase** : l'item 5 reprend mot pour mot la forme que j'avais écrite comme
acceptée (section 6 du second passage), il est exact, rien d'autre n'a bougé, et le Done
pédagogique d'`EMC-CM1-10` est prononcé avec les deux réserves datées du second passage.

### 1. Ce que j'ai fait

Worktree dédié `s47-nadia-audit-b3`, fondé sur `round-two` à `b52e743f`, lignée vérifiée par
`outils/verifier-lignee.sh round-two`. Jar construit par `bash outils/mvn-jdk21.sh -B package
-DskipTests` (Maven sur le JDK 21.0.10 de la toolchain), lancé sur un port dédié. Relancé avec `-am`
sur le module `adapter-content` seul : `PaquetCm1EmcVoteElectionTest` (8) et
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` (28), 0 échec. Je n'ai lancé aucun autre
job de la CI et je n'en déduis rien.

**Ce qui a bougé depuis mon second passage.** `git diff 5b352b7c b52e743f` : un seul fichier, une
seule ligne, l'énoncé de l'item 5. Aucun autre item, aucun indice, aucune source, aucune entrée de
catalogue.

**Six parties complètes de dix manches** par appels HTTP réels : une juste de bout en bout, une avec
trois erreurs à chaque manche jusqu'à la révélation, quatre mélangées (60 manches : 20 justes du
premier coup, 8 après une erreur, 13 après deux, 19 avec révélation). **Les douze items ont été
tirés et joués**, chacun entre trois et six fois. À chaque manche, les trois boutons servis étaient
exactement ceux du paquet. Consigne servie inchangée : *"Lis bien, puis trouve le bon mot, ou dis
qui vote, qui est élu, ou ce que fait un délégué."*

### 2. Motif du second passage - levé

Item 5 servi : *"En France, tous les citoyens majeurs ont le droit de vote, quels que soient leur
richesse, leur métier ou leur sexe. Comment appelle-t-on ce droit de vote accordé à tous les
citoyens ?"*

- **Le fait.** L'énoncé dit qui a le droit de vote, sans dire que personne d'autre ne vote :
  l'exclusivité fausse (*"réservé aux"*) a disparu. Il est conforme à l'article 3 de la
  Constitution, source portée au marqueur et inchangée.
- **La simplification que j'accepte.** L'article 3 ajoute *"jouissant de leurs droits civils et
  politiques"*. *"Tous les citoyens majeurs"* omet ce cas, qui relève d'une décision de justice et
  n'est pas du niveau CM1. C'est la forme que j'ai moi-même écrite comme barre : je ne la relève pas
  après coup.
- **À l'écran.** Après une erreur, l'indice *"Ce droit n'appartient pas seulement à certaines
  personnes : il appartient à tous les citoyens majeurs."* ; après la troisième, *"Appuie sur la
  bonne réponse : le suffrage universel"*. Les deux sont exacts et cohérents avec l'énoncé.

Je lève.

### 3. Le reste du paquet

Items 1 à 4 et 6 à 12 rejoués, identiques au second passage et exacts. Le motif 2 du premier
passage (item 6) reste levé. Ma décision Q6 (règles 1 à 5), `EMC-6`, `EMC-8`, `FER-1` à `FER-3`
restent tenues, avec les mêmes vérifications. J'ai relu sur le paquet les deux constats de ma section
7 du second passage avant de m'appuyer dessus : ils sont exacts (*"le délégué de classe"* est
distracteur des items 3, 4, 5 et 6 ; l'énoncé de l'item 11 n'a pas changé).

### 4. Réserves - celles du second passage, datées du 2026-10-02, reconduites

Aucune réserve neuve. Les deux réserves de la section 7 du second passage restent ouvertes, telles
qu'écrites :

1. **Items 3 à 6, un distracteur hors de sa catégorie** : *"le délégué de classe"* est une personne,
   distracteur de questions qui demandent un vote ou un droit.
2. **Item 11, la question est mal formée** : *"Qui le suffrage universel permet-il de faire voter ?"*
   Exemple de forme : *"Avec le suffrage universel, qui a le droit de voter ?"*

`camille-po` peut en faire une story ; elles ne bloquent pas la ligne.

### 5. Pour `camille-po`

**Done pédagogique d'`EMC-CM1-10` prononcé, sous les deux réserves de la section 4.** La ligne de
matrice peut passer à `couvert-valide`, avec la mention exigée d'avance : *"connaissance du vote et
de la représentation seule ; participer à une décision collective reste non couvrable"*.

### 6. Ce dont je reste la seule garde sur cette ligne

Inchangé depuis le premier passage (section 8 ci-dessus) : l'exactitude de chaque fait de droit
électoral, Q6 règle 1 au-delà des cinq mots cherchés par le test, la qualité des sources `HIS-3`,
`EMC-6` et `EMC-8`. Sur cette ligne, la CI a été verte sur deux énoncés faux successifs de l'item 5
et sur la date fausse de l'item 6 : aucun test ne sait qui vote en France.

**Identifiants** : aucun neuf, aucun renommé.

**Signé** : `nadia-prof`, s47, instance B, troisième passage, 2026-10-02.

---

## Bloc de validation pédagogique - quatrième passage - nadia-prof UNIQUEMENT

- **Verdict** : **VALIDÉ**
- **Date** : 2026-10-03
- **Auditrice** : `nadia-prof`, instance A (`DEC-0106`), quatrième passage.
- **Audit joué** : **oui**, paquet entier.
- **Contenu audité** : `cm1-emc-vote-election.yaml` à `b77cabeb` (`sofia-sm`, casquette dev).

**Conclusion, en une phrase** : les deux réserves reconduites au troisième passage sont levées, les
quatre distracteurs rédigés par `sofia-sm` sont exacts, de la bonne catégorie et du niveau CM1, et
aucune réserve ne reste ouverte sur ce paquet.

### 1. Ce que j'ai fait

Arbre `round-two` à `bc1e7835`. Jar du `clean verify` de `yanis-qa` à ce commit, YAML du jar vérifié
identique à l'arbre, lancé sur un port dédié servi par mon propre processus. Aucun job de la CI lancé
par moi. **Huit parties complètes de dix manches** par appels HTTP réels, une erreur sur une manche sur
deux : **les douze items tirés**, les items 3 à 6 et 11 servis plusieurs fois chacun, propositions dans
un ordre qui change. `git show b77cabeb` : quatre lignes `distracteurs:` (items 3 à 6) et l'énoncé de
l'item 11, rien d'autre. Rendu non revu au navigateur : aucun gabarit n'a changé.

### 2. Réserve 1 - les quatre distracteurs rédigés par `sofia-sm`

Ma fiche ne donnait que la catégorie : la question demande un vote ou un droit, le distracteur doit en
être un, et non une personne. Je juge chacun contre ce critère, contre l'exactitude, et contre le
niveau CM1.

| Item | Question | Distracteur neuf | Catégorie | Faux ici, et pourquoi | Tenu |
|---|---|---|---|---|---|
| 3 | le vote qui choisit le conseil municipal | *"un référendum"* | un vote | un référendum fait répondre à une question, il ne choisit personne ; l'énoncé dit *"choisir les membres"* | **oui** |
| 4 | le moment où tous les électeurs choisissent le président | *"un sondage d'opinion"* | une manière de recueillir des avis | un sondage interroge une partie des gens, il n'élit personne et ne compte pas les voix de tous | **oui** |
| 5 | le droit de vote accordé à tous | *"le droit de pétition"* | un droit | il permet de demander, pas de voter | **oui** |
| 6 | le principe qui donne le droit de vote à tous | *"le vote obligatoire"* | un principe sur le vote | il oblige à voter, il ne donne pas le droit ; les citoyens français n'y sont pas soumis | **oui** |

Les quatre sont exacts, aucun n'est aussi vrai de la situation décrite, aucun n'est absurde : ce sont
des confusions réelles d'un élève de CM1 entre des mots de la vie civique qu'il entend. *Référendum*,
*sondage*, *pétition* et *vote obligatoire* sont des mots du cycle 3, que l'enseignement moral et
civique rencontre. Le plus éloigné de la catégorie est *"un sondage d'opinion"*, qui n'est pas un
vote ; je le tiens pour juste parce que c'est la confusion la plus fréquente à cet âge entre les
chiffres annoncés avant une élection et l'élection elle-même. Placer le référendum sur l'item 3 et non
sur l'item 4 est juste : *"tous les électeurs du pays sont appelés à voter le même jour"* serait aussi
vrai d'un référendum national. **Levée.**

### 3. Réserve 2 - l'item 11 - levée

Servi : *"Avec le suffrage universel, qui a le droit de voter ?"*, la forme que j'avais donnée, mot
pour mot. La question ne prête plus au suffrage universel de *faire voter* : il donne un droit, et
c'est ce qu'elle demande. Bonne réponse et distracteurs inchangés et exacts. **Levée.**

### 4. Pour `camille-po`

**`EMC-CM1-10` reste `couvert-valide`, sans réserve ouverte**, avec la mention exigée d'avance
(troisième passage, section 5).

### 5. Ce dont je reste la seule garde sur cette ligne

Inchangé (section 8 du premier passage) : l'exactitude de chaque fait de droit électoral, et
maintenant de chaque distracteur. Les clauses 2 et 3 de
`LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest` ont vérifié la forme des quatre
distracteurs ; aucune ne sait qu'un sondage n'élit personne.

**Identifiants** : aucun neuf, aucun renommé.

**Signé** : `nadia-prof`, s48, instance A, quatrième passage, 2026-10-03.
