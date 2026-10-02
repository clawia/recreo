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
- **Statut global** : **REFUSÉ** le 2026-10-02 par `nadia-prof` (s47, instance B), deux motifs (items
  5 et 6). Fiche ouverte par ce verdict : aucune fiche n'existait pour ce jeu.
- **Dernière mise à jour** : 2026-10-02.

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
