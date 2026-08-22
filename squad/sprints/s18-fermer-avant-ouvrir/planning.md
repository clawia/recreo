# [Sprint s18] Planning - fermer avant ouvrir

- **Horodatage de debut** : 2026-08-22 21:35 +0200
- **Anime par** : sofia-sm, casquette Scrum Master.
- **Ordre du process respecte, dans son ordre normal et complet** (`squad/README.md`, section 9,
  etape 1) : `camille-po` a instruit le perimetre (`DEC-0041`, statut Proposee, commit `2843b85`),
  **puis** `theo-archi` a rendu son avis de faisabilite ecrit avant tout commit de code de story
  (`ADR-0055`, commit `a103f91`). Je gele, affecte et ecris.
- **Lots fusionnes avant ce planning**, lignee verifiee sur chacun (`git merge-base`) :
  - `camille-po` : `2843b85`, forkee de `494da1aa7841fb8ccb752f7a42573aee8b09ff94` (`git merge-base
    494da1a s18-camille-po-perimetre` rend exactement ce SHA). Fusion sans conflit, commit de
    fusion `8bfcb44`.
  - `theo-archi` : `a103f91`, forkee du **meme** `494da1a` (`git merge-base 494da1a
    s18-theo-avis-faisabilite` rend le meme SHA que ci-dessus - `theo-archi` n'a pas fork la
    branche de `camille-po`, il en a lu le contenu par `git show s18-camille-po-perimetre:...`
    depuis sa propre base, comme son avis le dit explicitement). Fusion sans conflit, commit de
    fusion `b73deec`.
  - Les deux lots ne touchent aucun fichier en commun (`docs/backlog/`, `squad/decisions/produit/`,
    `squad/programme/` pour l'un ; tests Java, CSS, gabarits, `squad/decisions/adr/`,
    `squad/sprints/s18-fermer-avant-ouvrir/` pour l'autre) : la fusion sequentielle ne pouvait pas
    produire de conflit, et n'en a pas produit.
- **Repertoire de sprint** : `s18-fermer-avant-ouvrir`, deja cree par le lot de `theo-archi`
  (`avis-faisabilite.md`) sur le slug que `camille-po` avait propose dans `DEC-0041`. Je le garde
  tel quel, comme en s17 : il nomme le perimetre gele ci-dessous aussi bien que toute autre
  formulation, et rien ne le rendrait plus lisible.

## Porte technique rejouee, par mes deux methodes

Avant toute mesure : aucune application ne tenait le jar (seul processus `java` trouve sur le
poste, un service d'analyse statique de l'IDE ; aucun port 8080/8081 en ecoute).

`bash outils/mvn-jdk21.sh -B clean verify` sur l'arbre fusionne (les deux lots ci-dessus, racine du
depot, reacteur complet) : **BUILD SUCCESS**, six modules `SUCCESS`, `Total time: 01:30 min`
(rejoue une seconde fois pour capturer le log complet : `01:28 min`, memes chiffres).

**612 tests, 0 echec, 0 erreur, 0 ignore** - deux methodes independantes, sur le meme `clean` :

1. Somme des blocs `[INFO] Results:` du reacteur, module par module : `recreo-domain` 48,
   `recreo-application` 13, `recreo-adapter-web` 312, `recreo-adapter-content` 144,
   `recreo-bootstrap` 89 (surefire) + 6 (failsafe) = 95. Total 48+13+312+144+95 = **612**.
2. Somme de toutes les lignes `Tests run: N, ... -- in <classe>` imprimees par Surefire et Failsafe
   sur l'ensemble de l'execution (methode "rapports texte de classe" qu'`ADR-0054` cite comme
   pouvant diverger de la premiere) : **612** egalement - les deux methodes convergent sur cet
   arbre, sans l'ecart qu'`ADR-0054` a documente sur un autre lot.

**Chiffre annonce par `theo-archi` (612, +8 depuis les 604 de l'ouverture, cites par `DEC-0041`) :
confirme sur l'arbre fusionne, par mes deux methodes, pas recopie.** L'ecart de 8 est entierement
localise sur `adapter-web` (304 -> 312), comme il l'a ecrit : aucun autre module ne bouge.

**31 regles ArchUnit** (`git grep -o "ArchRule .*=" -- '*.java'`, sur les fichiers **trackes**),
**inchange** entre `494da1a` et `HEAD` - 13 fichiers avant, 13 fichiers apres, meme compte. La
regle neuve d'`ADR-0055` (`LaLargeurDemandeeParUneRangeeEstEpingleeTest`, 8 tests) n'entre pas dans
ce compte : comme celle d'`ADR-0054`, elle juge le document assemble via `jsoup`, pas via le moteur
ArchUnit - ce n'est pas une regle ArchUnit au sens strict, quel que soit le mot qui la designe dans
les `ADR`. Le meta-garde-fou `LesGardeFousSontMisALEpreuveTest` est vert, **10 tests**, **sans
exemption ajoutee ni plafond de dette releve** - reconfirme independamment du chiffre de
`theo-archi`, dans mon propre `verify`.

**Les trois garde-fous de CI hors Maven** (`.github/workflows/ci.yml`, jobs `prefixes`, `chemins`,
`outils`), rejoues localement sur l'arbre fusionne :

- `prefixes` : `verifier-prefixes-test.sh` vert, `commit-atteignable-test.sh` vert, et
  `git log --no-merges --format=%s 494da1a..HEAD | verifier-prefixes.sh` rend "tous les commits
  portent un prefixe d'agent connu".
- `chemins` : `verifier-chemins-test.sh` vert, et `git ls-files | verifier-chemins.sh` sur l'arbre
  entier rend "aucun chemin de poste de travail ni nom de compte".
- `outils` : les quatre juges purs (`outils/*-test.sh`) tous verts.

## Objectif gele

**Fermer trois dettes deja ouvertes sur le francais CM1 - la securite pedagogique de `S17-3`
(`SYN-11` a `SYN-13`), la sortie clavier absente a la revelation (`S17-C`) et la mesure manquante
des homophones (`S17-4`, `BPM-1`) - avant d'ouvrir tout contenu neuf, en faisant primer la dette la
ou elle entre en tension avec la couverture, sauf sur la seule action qui sert les deux termes de
la directive a la fois : remesurer `BPM-1` sans ecrire une ligne de contenu neuf.**

**Ce que je ne tais pas** : ce sprint arrive apres quatre sprints consecutifs (s14 a s17) sans
mouvement reel de couverture (`DEC-0037`, amendement de cloture de s17, porte dans
`RAPPORT.md` de s17). `S17-4` est la seule action de s18 qui porte un espoir de le rompre, et elle
depend entierement d'une mesure qui n'existe pas encore.

## Ce que ce sprint peut et ne peut pas rendre a la couverture

Repris de `DEC-0041`, section 6, verifie et non recopie sans verification :

- **Par remesure seule, zero contenu neuf** : au plus **une ligne** (francais CM1, homophones,
  `S17-4`) passe `en cours` -> `couvert-valide`, si `BPM-1` tient et si le Done complet est
  prononce sans reserve bloquante. Chiffre honnete : **9/46 (19,6 %) -> 10/46 (21,7 %)** au mieux,
  soit 2,1 points, **conditionne a une mesure qui n'existe pas encore**.
- **Par fermeture de dette, zero mouvement de matrice** : `SYN-11`/`SYN-12`/`SYN-13` et `S17-C` ne
  font progresser aucune ligne. `S17-3` reste `en cours` quel que soit son verdict - les antonymes
  manquent toujours (`nadia-prof`, 2026-08-19) et cette ligne n'entre jamais dans le numerateur sur
  le seul paquet synonymes.
- **Par contenu neuf, rien** : aucune ligne `non couvert` n'ouvre a s18, deliberement.
- **Si `BPM-1` ne tient pas ou si son Done n'est pas complet a la cloture**, s18 rejoint la serie
  des sprints sans mouvement reel de couverture deja nommee par `DEC-0037` - un cinquieme sprint
  consecutif. Je le dis a l'ouverture, pas a la cloture.

## Ce que s18 reprend des six lots produits apres la cloture de s17, orphelins de tout sprint

`DEC-0040`, `ADR-0054`, le Done pedagogique REFUSE de `S17-3` et ses trois conditions neuves
(`SYN-11`, `SYN-12`, `SYN-13`) ont ete produits et fusionnes **apres** la cloture de s17 (commits
marques "hors sprint"), sans qu'aucun fichier de sprint ne les porte. Conformement a l'avis que
j'ai deja donne - une ouverture de sprint plutot qu'un amendement retrospectif de `review.md` de
s17 - ce planning les reprend explicitement, un par un, pour qu'aucun ne reste orphelin dans
l'historique :

1. **`DEC-0040`** (reference d'ecran 744 x 551, retrait du telephone) : repris comme le fondement
   factuel des trois actions de s18 - c'est lui qui rend `BPM-1` remesurable, qui a produit la
   remesure de `SYN-5` sur laquelle `SYN-11` s'appuie, et qui reclasse `S17-C` en faisant du
   clavier un chemin ordinaire.
2. **`ADR-0054`** (largeur offerte a une rangee epinglee) : repris et **complete** dans ce meme
   planning par `ADR-0055`, fusionne ci-dessus - `ADR-0054` n'epinglait qu'un membre de la
   comparaison qui decide du repli d'une rangee, `ADR-0055` epingle l'autre.
3. **Le Done pedagogique REFUSE de `S17-3`** (verdict du 2026-08-22, motif `SYN-11`) : repris
   comme `S17-3` reentrant le perimetre de s18, avec `SYN-11` a `SYN-13` ajoutees a ses criteres
   au meme titre que `SYN-5` a `SYN-10` l'ont ete a s17.
4. **`SYN-11`** (bloquante) : entre au perimetre de s18 comme critere de `S17-3`, porte de
   `nadia-prof` seule, non encodable - voir le tableau des stories.
5. **`SYN-12`** (non bloquante) : reste **entierement** au jugement de `nadia-prof`, jamais
   transforme en critere de lot testable, sur avis explicite de `theo-archi`.
6. **`SYN-13`** (observation) : reste une observation datee dans la fiche de conformite,
   **conditionnee au mecanisme retenu** pour `SYN-11` - `camille-po` doit corriger sa formulation
   actuelle de critere de lot avant la cloture (voir "Obstacles routes").

## Tableau des stories

| Story | Competence programme visee | Affectation | Ce qui est confirme ou reste a corriger dans les criteres | Porte de sortie |
|---|---|---|---|---|
| **S17-4** - "Le bon petit mot" revient au catalogue, `BPM-1` reclamee | Francais, CM1, "Orthographe et grammaire", homophones grammaticaux - ligne deja `en cours` depuis `DEC-0024` (s10), avancee, pas ouverte | `lea-dev`, en premier | Code confirme trivial et independant de la formulation (`theo-archi`, 1.1) : une entree de six lignes au patron des douze deja presentes, zero ligne de Java, zero mecanique neuve. **Deux corrections encore dues par `camille-po`, qui ne bloquent pas le code** : la reference `360 x 640` (perimee, `DEC-0040` la remplace par `744 x 551`) et l'outil nomme `outils/mesurer-rendu.sh` (ne peut pas atteindre les trois etats d'echec - c'est la methode de `nadia-prof` qui s'applique). | `mvn verify` vert sur l'entree de catalogue, le comptage (24 items, deja vert), la correction du commentaire faux de `TexteAccentueExposeParLePortEntrantTest` ("treize" au lieu de "douze", a corriger dans ce meme lot). **Puis** Done complet de `nadia-prof` : `BPM-1` aux trois etats d'echec (message, indice, revelation) sur les 24 items, `BPM-2`/`BPM-3`/`BPM-4` deja tenues rejugees sans rendu. Si `BPM-1` tient et le Done est sans reserve bloquante, la ligne francais CM1 "homophones" passe `couvert-valide` dans le meme geste. |
| **S17-3** - `SYN-11` a `SYN-13` (synonymes CM1, `S17-3` reste `S17-3`) | Francais, CM1, "Vocabulaire" - ligne deja `en cours`, ne bascule pas `couvert-valide` ce sprint (antonymes manquants) | `lea-dev`, apres l'arbitrage de mecanisme (voir sequencement) | `SYN-11` **bon tel qu'ecrit**, non encodable, porte de `nadia-prof` seule (`theo-archi`, 2.1) ; ce qui devient encodable depuis `ADR-0055` est l'obligation de remesure des que l'un des deux membres de la comparaison de largeur change. `SYN-12` reste **entierement** au jugement de `nadia-prof`. `SYN-13` **a corriger par `camille-po`** : observation datee, pas critere de lot, conditionnee au mecanisme retenu. | `mvn verify` vert sur le correctif retenu (une des trois familles chiffrees par `theo-archi`, 2.2, sous le veto de `nadia-prof`, marge de robustesse superieure a l'incertitude de police qu'elle a nommee : 15 a 35 px sur cette rangee), tables `ADR-0054`/`ADR-0055` mises a jour dans le meme commit. **Puis** remesure de `nadia-prof` confirmant ou non la fermeture de `SYN-11`, et reprononcant `SYN-13` ou non selon le levier choisi. |
| **S17-C** - la sortie clavier absente a la revelation | Transverse, aucune ligne de `squad/programme/MATRICE-couverture.md` | `marc-dev` | Motif de report d'origine **faux sur un point verifiable**, corrige par `theo-archi` (4.1) : le faux DOM de `RecreoJsDeplaceLeFocusTest` modelise deja `tabindex`, ce qu'il ne modelise pas est la focalisabilite native. Mecanisme retenu (`theo-archi`, 4.3, "mecanisme B") : le serveur declare - trois attributs `tabindex="-1"` poses dans les gabarits sur les cibles non focalisables (`p.enonce`, `p.question-confirmation`, `div.signal`), le script se reduit a `focus()` sans plus muter aucun attribut. Criteres d'acceptation deja ecrits au backlog, inchanges. | `mvn verify` vert sur les trois attributs de gabarit et la reduction du script, **et** un `ADR` neuf avec sa regle, ecrits par `theo-archi` dans ce meme lot (la clause 1 devient derivable via `AucunArretDeTabulationNEstDestructeurTest` une fois le document servi porteur de l'attribut). **Puis** verification de `nadia-prof` sur l'ordre de tabulation reel au clavier dans un navigateur reel (clause 1 - reste sa verification seule, jamais automatisable au-dela de la cause declarative). |

## Sequencement - ce qui bloque quoi, dans quel ordre

1. **`S17-4` ne bloque sur rien : `lea-dev` commence immediatement.** L'entree de catalogue et la
   correction du commentaire ne dependent d'aucun arbitrage. Les deux corrections dues par
   `camille-po` (reference d'ecran, outil nomme) ne bloquent aucun code : elles ne rendent exact
   que le texte du backlog, pas le travail du dev - je les route sans en faire une dependance de
   demarrage.
2. **Une fois l'entree de catalogue posee, `BPM-1` est la mesure prioritaire de `nadia-prof`**, sur
   decision explicite de `camille-po` ("le geste que je fais primer sur tout le reste de ce
   perimetre") : c'est la campagne la plus lourde du sprint (24 items, trois etats d'echec chacun,
   un ordre superieur a `SYN-11`, qui a deja coute 9 parties et 360 lectures sur 16 items).
3. **En parallele de 1 et 2, `ines-design` propose une forme de mecanisme pour `SYN-11`** (les
   trois familles chiffrees par `theo-archi`, section 2.2 de son avis), et `nadia-prof` l'arbitre
   sous son veto pedagogique des qu'elle a un moment hors mesure lourde - c'est une decision, pas
   une campagne. **Cette etape precede tout commit de correctif sur `SYN-11`** : personne ne code
   la mise en page avant que le mecanisme ne soit choisi.
4. **`lea-dev` implemente le correctif `SYN-11` des que le mecanisme est arbitre**, sans attendre
   la fin de la campagne `BPM-1` : deux taches distinctes de la meme developpeuse, sequencees par
   l'arbitrage de design, pas par la disponibilite de `nadia-prof`.
5. **`marc-dev` implemente `S17-C` en parallele de tout ce qui precede** : le mecanisme est deja
   tranche par `theo-archi` (4.3), aucun arbitrage de design ni de pedagogie a attendre avant le
   code. `theo-archi` ecrit l'`ADR` et sa regle dans ce meme lot, une fois les trois attributs
   poses.
6. **`nadia-prof` verifie dans cet ordre de priorite explicite** : `BPM-1` d'abord (le plus lourd,
   et le seul levier de couverture du sprint), puis la remesure de `SYN-11` sur le correctif livre,
   puis la verification legere de l'ordre de tabulation reel de `S17-C` (un seul passage clavier,
   cout marginal) - dans cet ordre pour ne jamais laisser la seule action porteuse de couverture
   attendre derriere deux verifications qui n'en portent aucune.

## Ce qui reste hors perimetre, et pourquoi

- **`S17-5` (antonymes, contenu neuf)** : hors perimetre, motif de `camille-po` - ne pas melanger
  une ouverture de contenu neuf avec la fermeture de trois dettes dans le meme sprint. Reste au
  backlog, candidate du sprint suivant.
- **Famille `M4` (remesure a 744 x 551)** : nommee, non ouverte - ne bloque aucune ligne
  aujourd'hui, concurrencerait pour rien la capacite de mesure de `nadia-prof` deja sollicitee par
  `BPM-1` et par la verification du correctif de `SYN-11`.
- **Migration `ADR-0049`** : hors perimetre par decision de `camille-po`, pour un bon arbitrage
  mais un motif que `theo-archi` corrige : s18 est **le seul sprint ou la reporter est gratuite**
  (aucun paquet neuf ouvert, `DEC-0041` ferme le contenu neuf, et son cout croit d'un fichier par
  paquet neuf) et **le meilleur candidat a un ajout tardif**, puisqu'elle ne consomme aucune mesure
  de `nadia-prof`. Je la note comme **contingence explicite, non affectee d'avance** : si `lea-dev`,
  `marc-dev` ou moi-meme (casquette dev) nous liberons pendant que `nadia-prof` mesure `BPM-1`,
  cette migration est la premiere candidate a affecter - decision de capacite que je prendrai au
  daily, faute de savoir aujourd'hui qui se liberera et quand.

## Affectations et sequencement, en un coup d'oeil

- **`lea-dev`** : `S17-4` (entree de catalogue + commentaire corrige), en premier ; puis `S17-3`
  (correctif `SYN-11`) des que le mecanisme est arbitre par `ines-design`/`nadia-prof`.
- **`marc-dev`** : `S17-C` (attributs de gabarit + reduction du script), sans dependance de
  sequencement - peut commencer immediatement, en parallele de `lea-dev`.
- **`sofia-sm`** (casquette dev) : aucune affectation gelee. Capacite reservee pour la migration
  `ADR-0049` si elle se libere en cours de sprint, ou pour toute reaffectation de charge decidee au
  daily.
- **`theo-archi`** : `ADR` et regle de `S17-C` dans le lot du correctif de `marc-dev` ; disponible
  si le mecanisme de `SYN-11` souleve une question d'architecture non anticipee ; confirmation
  ArchUnit a la review.
- **`ines-design`** : proposition de mecanisme pour `SYN-11` (les trois familles chiffrees par
  `theo-archi`), sous le veto de `nadia-prof` - due avant tout commit de correctif sur `SYN-11`.
- **`nadia-prof`** : `BPM-1` en premier (trois etats d'echec, 24 items) ; puis remesure de `SYN-11`
  sur le correctif livre ; puis verification legere de l'ordre de tabulation reel de `S17-C`.
  Aucune demande de rejugement du contenu deja valide (`BPM-2` a `BPM-4`, `SYN-1` a `SYN-10`).
- **`yanis-qa`** : traduit les criteres de `SYN-11`/`S17-C` en tests au fil des lots ; verifie que
  tout correctif de `SYN-11` fait rougir au moins une des deux tables `ADR-0054`/`ADR-0055` avant
  qu'elle ne soit remise a jour, comme `theo-archi` l'a demande.
- **`relecteur-pedagogique`** : relit chaque lot au fil de l'eau, apres son Done technique.
- **`camille-po`** : corrige les deux criteres de `S17-4` (reference d'ecran, outil nomme) et
  reecrit `SYN-13` en observation plutot qu'en critere de lot, avant la cloture du sprint - route
  ci-dessous, ne bloque pas le demarrage du code.

## Obstacles routes

| Description | Categorie | Destinataire | Ce qui manque pour clore |
|---|---|---|---|
| Deux criteres de `S17-4` perimes (reference `360 x 640`, outil `outils/mesurer-rendu.sh` structurellement hors sujet pour les etats d'echec) | produit | `camille-po` | Correction ecrite dans `docs/backlog/backlog.md` : la reference en vigueur (`744 x 551`) et la methode applicable (`nadia-prof`). Ne bloque pas le demarrage du code, doit etre faite avant la cloture. |
| `SYN-13` ecrite comme critere de lot alors qu'elle est conditionnee au mecanisme retenu pour `SYN-11` | produit | `camille-po` | Reecriture en observation datee dans la fiche de conformite, pas en critere testable, avant la cloture. |
| Mecanisme de mise en page qui replace le bloc retour juste/faux au-dessus du pli pour `SYN-11` | design puis pedagogie | `ines-design`, puis `nadia-prof` (veto) | Une forme proposee, arbitree, avant tout commit de correctif - voir sequencement, point 3. |
| Charge de mesure de `nadia-prof` : `BPM-1` (24 items, trois etats) + remesure `SYN-11` + verification `S17-C`, sur la meme capacite | capacite | `sofia-sm` (moi) | Sequencement explicite fixe ci-dessus (`BPM-1`, puis `SYN-11`, puis `S17-C`), a revoir au daily si l'un des trois deborde. |
| Migration `ADR-0049` hors perimetre, meilleur candidat a un ajout tardif si un dev se libere | capacite | `sofia-sm` (moi) | Aucune affectation gelee ; decision au daily si la capacite se degage pendant la mesure de `BPM-1`. |

## Decisions prises

1. **L'objectif est gele** sur les trois actions de `DEC-0041` (`S17-4`, `S17-3` avec `SYN-11` a
   `SYN-13`, `S17-C`), avec les criteres dans leur forme confirmee par `theo-archi` ou encore a
   corriger par `camille-po` (tableau ci-dessus), et les affectations nominatives ci-dessus.
2. **Le slug `s18-fermer-avant-ouvrir` est garde tel quel**, deja pose par le lot de `theo-archi`.
3. **Le sequencement de la capacite de mesure de `nadia-prof` est fixe** dans cet ordre : `BPM-1`,
   puis `SYN-11`, puis `S17-C` - motif explicite : `BPM-1` est la seule action du sprint qui porte
   un espoir de mouvement de couverture.
4. **La migration `ADR-0049` reste hors perimetre gele**, nommee comme contingence d'ajout tardif,
   non affectee a ce jour.
5. **Les six lots produits apres la cloture de s17 et orphelins de tout sprint** (`DEC-0040`,
   `ADR-0054`, le Done pedagogique REFUSE de `S17-3`, `SYN-11`, `SYN-12`, `SYN-13`) **sont repris
   explicitement par ce planning**, un par un, comme decrit ci-dessus : aucun ne reste sans sprint
   dans l'historique.
