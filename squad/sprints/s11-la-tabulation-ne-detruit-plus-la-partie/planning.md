# [Sprint s11] Planning - 2026-08-14

- **Horodatage de debut** : 2026-08-14 11:14
- **Anime par** : sofia-sm
- **Participants** : camille-po (perimetre, DEC-0026, DEC-0027), theo-archi (avis de faisabilite
  deja rendu, ADR-0038, disponible pour tout obstacle), marc-dev (developpement), lea-dev
  (disponible, aucune story affectee ce sprint), nadia-prof (arbitrage prealable de DEC-0027,
  jugement du texte de confirmation, verdict de cloture), ines-design (forme de l'ecran de
  confirmation, et forme de l'en-tete si l'arbitrage de DEC-0027 l'exige), yanis-qa (traduction des
  criteres en tests au fil des commits, rapport qualite de cloture), sofia-sm (ceremonies).
- **Lu avant d'ecrire ce fichier** : `docs/backlog/backlog.md` (sections "Dette ouverte par
  DEC-0026" et "Gain vertical - retrait du bouton de sortie du fragment de manche (DEC-0027)") ;
  `DEC-0025-levee-de-la-condition-m4-le-sprint-10-est-livre-avec-un-defaut-de-pli-assume.md`, y
  compris son amendement du 2026-08-14 ;
  `DEC-0026-levee-m13-clause-1-ecran-de-revelation-perte-de-partie-sans-confirmation.md`, y compris
  son amendement du 2026-08-14 ;
  `DEC-0027-retrait-bouton-sortie-fragment-manche-reouvre-s6-9-arbitrage-prealable-requis.md` ;
  `ADR-0038-aucun-arret-de-tabulation-destructeur-a-une-tabulation-d-un-point-de-depart.md` ;
  `ADR-0008-le-comportement-de-jeu-vit-sur-le-serveur.md` ; `ADR-0009-les-garde-fous-de-ci-sont-des-
  scripts-testes.md` ; `squad/sprints/s10-la-forme-du-bandit-manchot/retro.md` et `review.md` ;
  `squad/sprints/s11-la-tabulation-ne-detruit-plus-la-partie/avis-faisabilite-sortie-unique.md` ;
  `ADR-index.md` et `DEC-index.md` a jour.

## Fusion des lots

Deux lots d'instruction, sans code de production, verifies par `git diff --stat` avant chaque
fusion. Lignee verifiee au prealable : les deux branches descendent de `9650cff` (tete de
`round-two` a la cloture de s10) et ne descendent pas l'une de l'autre (`git merge-base` croise).
Les worktrees des deux agents vivaient hors du repertoire habituel des worktrees de sprint ;
verifie et sans consequence sur la fusion elle-meme.

| Lot | Branche | Tete | Perimetre verifie |
|---|---|---|---|
| theo-archi | `s11-theo-faisabilite-sortie` | `14d73b3` | un seul fichier, l'avis de faisabilite de la story de gain vertical. Aucun fichier de production. |
| camille-po | `s11-camille-sortie-unique` | `7375840` | `docs/backlog/backlog.md` (une story neuve), `DEC-0027` (fichier neuf), `DEC-index.md`. Aucun fichier de production. |

Fusionnes dans cet ordre (architecture puis produit, comme retenu a la cloture de s10) par deux
`git merge --no-ff` successifs, **aucun conflit**. Le fichier de `theo-archi` vivait sous
`squad/sprints/s11/`, sans slug - nommer le sprint m'appartenait et n'etait pas encore fait au
moment ou il a ecrit. Range dans ce repertoire une fois le slug choisi, avec correction de la note
du fichier qui appelait ce geste ; aucun autre fichier du depot ne renvoyait vers l'ancien chemin.
Tete de `round-two` apres fusion et rangement : **`970ed9a`**.

### Porte technique verifiee avant ce planning

Les deux lots fusionnes sont documentaires : aucun fichier de production ne bouge. Plutot que de
recopier un chiffre annonce, j'ai reconstruit et rejoue moi-meme la suite complete, dans un
**worktree detache** cree pour l'occasion (`git worktree add --detach`) : une instance de
l'application de l'utilisatrice tournait sur ce poste et verrouillait le livrable (verifie par la
ligne de commande du process), si bien qu'un `mvn clean` sur le depot principal l'aurait supprime.
Je ne touche pas ce processus ; je construis ailleurs plutot que de forcer un verrou qui n'est pas
le mien a lever. Le worktree est supprime une fois la mesure faite.

- `JAVA_HOME` positionne sur le JDK 21.0.10 le temps de la commande (`bash outils/verifier-jdk.sh` :
  `java` du `PATH` en 1.8.0_202, JDK de compilation 21.0.10, `JAVA_HOME` vide hors commande).
  `mvn -B clean verify` dans le worktree detache : **BUILD SUCCESS**, six modules en SUCCESS.
- Compte de tests, methode : somme de l'attribut `Tests run` de chaque module dans le journal du
  reacteur, apres un `clean` : 46 (domain) + 7 (application) + 206 (adapter-web) + 64
  (adapter-content) + 77 (bootstrap, surefire) + 6 (bootstrap, failsafe) = **406**, conforme au
  chiffre annonce par `theo-archi`.
- Compte de regles ArchUnit, methode : recherche de la forme `ArchRule <nom> =` dans les fichiers
  `.java` suivis par Git sous `src/test/java` des cinq modules de code, exclusion du faux module de
  contre-exemple de `LesGardeFousSontMisALEpreuveTest` (vit sous `src/test/resources`, jamais
  compile comme regle du depot) : `adapter-web` 1, `bootstrap` 27, **28** au total, inchange.

## Objectif du sprint (gele)

**A la review, une partie en cours ne se perd plus d'un seul geste sur aucun chemin de sortie -
clavier, souris ou rechargement de page - et `nadia-prof` reverifie elle-meme, au clavier reel, que
la clause 1 de M13 tient desormais sur les 25 revelations de son protocole et sur l'ecran de
reprise ; la demande de gain vertical de l'utilisatrice recoit l'arbitrage pedagogique prealable
qu'elle exige, sans qu'aucune ligne de code ne soit ecrite avant lui.**

**Gele** : aucune ligne de `squad/programme/MATRICE-couverture.md` ne bouge. Le raccourcissement
des textes de M4 (trois jeux desormais, pas six - voir plus bas), la reecriture des 25 indices de
"Le bon verbe", l'ampoule M16-M21, et les deux propositions de methode routees a `theo-archi` a la
cloture de s10 restent hors de ce sprint (voir "Ce qui sort, et pourquoi").

## L'obstacle qui commande ce sprint : la dette de DEC-0026

`DEC-0026` pose un declencheur a quatre conditions cumulatives, et son amendement du 2026-08-14
etend la levee (et le declencheur) aux **deux ecrans** : la revelation de M5 et la reprise
(`GET /` pendant une partie en cours). A defaut de livraison a la cloture de s11, la levee revient
devant l'utilisatrice comme un report a nommer explicitement. Deux stories sont versees au backlog
pour l'honorer :

1. **Story bloquante** - la premiere tabulation ne mene jamais a la sortie, sur les deux ecrans.
2. **Story** - confirmation avant d'abandonner une partie en cours, sur tout chemin de sortie.

**Seule la story 2 est planifiee, et ce n'est pas un choix parmi deux options encore ouvertes.**
`theo-archi`, en instruisant la story de gain vertical (avis de faisabilite ci-joint, section 2), a
demontre que la story 1 ne peut pas suffire seule : sur l'ecran de manche, les seuls arrets de
tabulation autres que la sortie sont les propositions elles-memes. La clause 2 d'`ADR-0038` exige
que le successeur de **toute** proposition (donc de la derniere) ne soit pas destructeur ; la
clause 1 exige que la sortie ait un predecesseur. Sur cet ensemble d'arrets, le predecesseur de la
sortie ne peut etre qu'une proposition - ce que la clause 2 refuse. **Aucune position de la sortie
ne satisfait les deux clauses a la fois, et ce raisonnement ne depend pas du nombre de sorties : il
vaut pour une comme pour dix.** La seule echappatoire serait d'ajouter un arret focalisable non
destructeur, ce qui **ajoute** un element a l'ordre de tabulation - l'inverse de ce qu'une simple
reorganisation ferait, et une question pour `nadia-prof` et `ines-design`, pas un correctif de
developpeur. **Donc la reorganisation de l'ordre de tabulation ne peut pas suffire : seule la
confirmation avant abandon repare.** Je le porte ici comme un fait acquis de ce planning, pas
comme une option a re-arbitrer.

La story de confirmation, dans ses propres criteres d'acceptation deja verses au backlog, couvre la
story 1 dans le meme geste (Tabulation depuis la revelation ou depuis l'ecran de reprise peut
toujours atteindre la sortie, mais Entree n'y detruit plus la partie sans confirmation) : planifier
la seule story 2 honore donc integralement le declencheur de `DEC-0026` sans laisser la story 1 en
suspens.

**Condition d'architecture, non negociable (`ADR-0008`)** : la confirmation vit sur le serveur,
comme un ecran rendu par le controleur. Un dialogue de navigateur declenche par du JavaScript
sortirait du perimetre d'`ADR-0008` et redeviendrait invisible a tout automate - ce n'est pas la
forme retenue.

### Ce que la CI devra garder, et ce qu'elle ne prouvera pas

`ADR-0038` pose l'invariante mais ne l'encode pas : c'est le lot de s11 qui doit ecrire le
mecanisme, dans `adapter-web`, aupres du corpus d'ecrans assembles. **Ce n'est pas une regle
ArchUnit** - le compte reste a 28, ArchUnit lisant du bytecode et non un document rendu. Les trois
obligations d'`ADR-0013` sont dues par ce lot, sans dispense, et `ADR-0038` les detaille deja :

1. **perimetre derive** - ecrans du corpus, points de focus lus dans le gabarit, arrets de
   tabulation du document et de la feuille servie, caractere destructeur de la requete rejouee.
2. **derivation totale** - zero arret de tabulation, zero point de focus declarable, un ecran a
   partie en cours qu'aucun etat du corpus ne produit : des echecs, jamais des cas ignores.
3. **mise a l'epreuve sur du materiau reel, pas une fixture imaginee** - l'etat de la production du
   jour est deja le cas fautif (`A[0]` est `button.retour-accueil`, destructeur). Sequence attendue :
   ecrire la regle, constater le rouge sur la production avant correctif et l'inscrire dans la
   revue, corriger la production, garder la forme d'avant correctif comme jeu fautif.

Ce que ce garde-fou ne prouvera pas, et qui reste enumere par `ADR-0038` (huit points) : que le
focus va reellement la ou le serveur le declare, que la premiere tabulation d'un vrai navigateur
atteint l'element calcule, que ce qui est masque est bien invisible, que la partie ne se perd pas
autrement, que "destructeur" est identifie de facon exhaustive, que l'enfant s'en sort, que M13
clause 1 est tenue, que les ecrans hors corpus tiennent l'invariante. **Le developpeur du lot ne
recopie aucune de ces huit reserves dans un commentaire de code qui les paraphraserait : elles
restent dans l'ADR.**

## Ce que ce sprint developpe

| Story / geste | Titre | Competence visee | Affecte a | Porte de sortie |
|---|---|---|---|---|
| Dette DEC-0026, story 2 | Confirmation avant d'abandonner une partie en cours, sur tout chemin de sortie (couvre la story 1 dans le meme geste) | transverse, accessibilite clavier | `marc-dev` | Tests verts (les trois obligations d'`ADR-0013` ci-dessus) **et** reverification au clavier reel de `nadia-prof`, protocole complet (25 revelations, tirages repetes sur "Calcul CP" et "Le bon verbe", plus l'ecran de reprise) - sans cette seconde porte, la dette de `DEC-0026` n'est pas fermee meme si la CI est verte. |
| DEC-0027, arbitrage prealable seul | Un enfant de CP retrouve-t-il la sortie si elle ne vit plus que dans l'en-tete ? | transverse, aucun developpement engage par ce geste | `nadia-prof` (route par `sofia-sm`), `ines-design` si une forme d'en-tete doit etre proposee | Motif ecrit dans `squad/conformite/FICHE-bandit-manchot-additions-cp.md`. Si l'arbitrage refuse la forme, la story reste au backlog sans developpement ; si elle l'autorise, elle repart au planning d'un sprint suivant - aucun code n'est engage ce sprint quel que soit le verdict. |

`marc-dev` porte cette story en continuite directe de son travail de s10 sur ce meme perimetre
(`PartieController`, `abandonner()`, `recreo.js`, le focus de M13) : c'est lui qui a corrige le
focus mort de `recreo.js` et instruit M13 au clavier ce sprint-la, et c'est le meme fichier de
session qu'`abandonner()` modifie qui est en jeu ici. `lea-dev` n'a aucune story affectee ce
sprint : le perimetre retenu tient sur un seul lot de developpement, et je ne lui invente pas un
geste pour occuper une capacite que ce sprint ne demande pas. Elle reste disponible pour tout
obstacle remonte en cours de developpement et pour toute forme que `ines-design` proposerait si
l'arbitrage de `DEC-0027` l'exige.

`yanis-qa` traduit chaque critere en test au fil des commits du worktree et fait tourner la suite.
`theo-archi` reste disponible pour tout obstacle technique, en particulier sur le respect des
trois obligations d'`ADR-0013` et de la condition d'architecture d'`ADR-0008`.

## Ce qui sort, et pourquoi

Ce planning retient un seul lot de developpement plutot qu'un catalogue, parce que le sprint 10 a
deja depense sa capacite en conception et est sorti avec un refus leve, et que s11 porte deja un
engagement contraint par un declencheur ecrit. Quatre elements identifies dans ce planning **ne**
sont **pas** retenus, chacun nomme plutot que laisse en silence :

1. **La dette de `DEC-0025` (M4)** - raccourcir les enonces et indices des trois jeux encore en
   defaut de pli ("Le bon son", "Petits problemes", "Le bon verbe" ; le chiffre a jour est trois
   jeux sur sept, corrige par l'amendement du 2026-08-14 - je n'ecris pas six) et reecrire les 25
   indices de "Le bon verbe" (M2, priorite relevee par `nadia-prof` elle-meme). **Reportee.** Ces
   deux stories demandent le jugement pedagogique de `nadia-prof` sur la reformulation elle-meme,
   en plus de son arbitrage sur `DEC-0027`, du jugement du texte de confirmation de la story
   retenue, et de son protocole complet de reverification au clavier sur les deux ecrans de
   `DEC-0026` : les empiler dans le meme sprint ferait porter a une seule personne quatre jugements
   pedagogiques distincts sur un increment deja charge. L'ampoule (M16-M21) reste elle aussi hors
   de ce sprint, deja reportee au backlog depuis s10.
2. **Les deux propositions de methode routees a `theo-archi` a la cloture de s10** (l'outil de
   cycle de vie du serveur de mesure conforme a `ADR-0009` ; la convention d'`ADR` nommant ce
   qu'une regle ne prouve pas). **Reportees a s12.** La capacite de `theo-archi` ce sprint va a la
   supervision des trois obligations d'`ADR-0013` sur le lot de `marc-dev` et a sa disponibilite
   pour tout obstacle ; ce sont deux gestes d'outillage qui n'engagent aucune livraison en attente,
   contrairement a la dette de `DEC-0026`.
3. **La faiblesse de difficulte constante, observee en jouant l'increment livre** (`Calcul CP` a
   tire "0 + 10" puis "0 + 9" coup sur coup) - **nommee, routee, non developpee ce sprint.** Le
   critere existant (`DEC-0007`/S3-1) n'exclut du tirage que le couple `0 + 0` ; rien n'empeche
   aujourd'hui deux tirages consecutifs a un seul operande nul en debut de partie, et une reserve
   analogue existe deja au backlog sur "Le bon son" (aucune progression de difficulte dans le
   tirage). Je la route a `camille-po`, pour qu'elle instruise une story avec un critere ecrit par
   `nadia-prof` sur le modele de S3-1, plutot que de la laisser sans destinataire ; elle n'entre pas
   au developpement de s11, la capacite de developpement de ce sprint etant deja integralement
   engagee par la dette de `DEC-0026`.
4. **La story de gain vertical elle-meme (au-dela de son arbitrage)** - verrouillee par `DEC-0027`,
   aucun developpement n'est possible avant que l'arbitrage prealable soit rendu ; ce planning
   n'affecte donc que l'arbitrage, jamais un lot de code, quel que soit son issue.

## Obstacles a ce planning

| Description | Categorie | Destinataire | Statut |
|---|---|---|---|
| Declencheur de reouverture de `DEC-0026` (quatre conditions cumulatives, deux ecrans) | pedagogie / produit | `nadia-prof` (reverification), `sofia-sm` (suivi) | **ouvert**, obstacle dominant du sprint - voir criteres de sortie de la story ci-dessus. |
| Verrou de `DEC-0027` : aucun developpement avant arbitrage prealable | pedagogie | `nadia-prof` | **ouvert**, tache planifiee ce sprint (arbitrage seul, pas de code). |
| Faiblesse de difficulte constante en debut de partie (`Calcul CP`, et deja "Le bon son" au backlog) | pedagogie / produit | `camille-po` | **route, non developpee ce sprint** - a formaliser en story avec un critere de `nadia-prof`. |
| Dette de `DEC-0025` (M4, trois jeux ; reecriture des indices de "Le bon verbe") | pedagogie / produit | `camille-po`, `nadia-prof` | **reportee explicitement**, capacite pedagogique du sprint deja engagee ailleurs. |
| Deux propositions de methode de `yanis-qa`, routees a `theo-archi` a la cloture de s10 | technique / outillage | `theo-archi` | **reportees explicitement a s12**, capacite de `theo-archi` engagee sur la supervision d'`ADR-0013` ce sprint. |
| Instance de l'utilisatrice sur le port 8080, verrouillant `bootstrap/target/recreo.jar` | environnement, hors depot | aucun - constate pour memoire | **non touchee** ; la mesure de porte technique de ce planning a ete faite dans un worktree detache pour cette raison. |

## Decisions prises

Aucun `ADR` ni aucune `DEC` n'est cree pendant ce planning : `DEC-0026`, `DEC-0027` et `ADR-0038`
sont anterieurs et deja sur le disque, fusionnes ci-dessus. Les decisions prises ici m'appartiennent
en tant que Scrum Master et n'ouvrent ni `ADR` ni `DEC` :

1. **Seule la story 2 de `DEC-0026` (confirmation avant abandon) est planifiee**, la story 1
   (tabulation) n'etant pas retenue separement pour le motif d'impossibilite etabli ci-dessus.
2. **`marc-dev` porte seul le developpement de ce sprint** ; `lea-dev` n'a aucune story affectee et
   reste en disponibilite.
3. **L'arbitrage prealable de `DEC-0027` est planifie, jamais son developpement.**
4. **Quatre elements sont explicitement sortis du perimetre** (dette de `DEC-0025`, deux
   propositions de methode, difficulte constante), chacun nomme et route plutot que laisse en
   silence - voir "Ce qui sort, et pourquoi".
5. **Le repertoire de ce sprint est nomme** `s11-la-tabulation-ne-detruit-plus-la-partie`, et le
   fichier de `theo-archi` y est range depuis son emplacement provisoire.

## Prochaines etapes

**Demarrent maintenant** :

- `marc-dev` ouvre un worktree dedie, verifie sa lignee (`outils/verifier-lignee.sh`), implemente
  la confirmation avant abandon (mecanisme serveur conforme a `ADR-0008`, ecran ou fragment de
  confirmation, mise a jour d'`abandonner()` et de `PartieController`) et le garde-fou d'`ADR-0038`
  dans `adapter-web` selon la sequence prescrite (rouge sur la production avant correctif, inscrit
  dans la revue, puis correctif, puis jeu fautif conserve), tests dans le meme commit.
- `ines-design` propose la forme de l'ecran ou du fragment de confirmation (lisible par un enfant
  de CP), et la forme d'en-tete pour `DEC-0027` si l'arbitrage de `nadia-prof` l'exige.
- `nadia-prof` joue l'increment courant pour rendre l'arbitrage prealable de `DEC-0027` (motif
  ecrit dans `squad/conformite/FICHE-bandit-manchot-additions-cp.md`) ; ce geste ne depend d'aucun
  livrable de `marc-dev` et peut commencer immediatement.
- `yanis-qa` traduit chaque critere en test au fil des commits.
- `theo-archi` reste disponible pour tout obstacle technique, en particulier la conformite aux
  trois obligations d'`ADR-0013`.

**Une fois le lot fusionne sur `round-two`** : `nadia-prof` reverifie au clavier reel, sur les 25
revelations de son protocole et sur l'ecran de reprise, que la clause 1 de M13 tient desormais, et
juge le texte de confirmation destine a l'enfant ; elle rend son verdict de Done pedagogique.

**Une fois l'arbre fusionne et le verdict rendu** : `yanis-qa` fait tourner `mvn verify` sur JDK 21
et ecrit le rapport qualite ; `theo-archi` confirme ArchUnit (28, inchange) et les trois
obligations d'`ADR-0013` sur le nouveau garde-fou ; `relecteur-pedagogique` releve les livrables non
executables ; `sofia-sm` ecrit `review.md` puis `RAPPORT.md` et lance le rapport de tokens.
