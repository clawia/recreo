# [Sprint s14] Planning - 2026-08-17

- **Horodatage de debut** : 2026-08-17 13:24
- **Anime par** : sofia-sm
- **Participants** : camille-po (perimetre, `DEC-0032`), theo-archi (avis de faisabilite sur
  S14-1, deux `ADR` neufs, un amendement, saisine sur deux dettes techniques), ines-design (forme
  chiffree de la barre de niveaux, deux variantes), nadia-prof (arbitrage prealable bloquant,
  rendu), marc-dev (developpement), lea-dev (developpement), sofia-sm (ceremonies, casquette dev
  sur un lot), yanis-qa (traduction des criteres en tests au fil des commits, rapport qualite de
  cloture), relecteur-pedagogique (relecture des livrables non executables).
- **Lu avant d'ecrire ce fichier** : `DEC-0032-perimetre-sprint-14-barre-entree-niveau-arbitrage-
  prealable-matiere-differee.md` ; `docs/backlog/backlog.md`, section "Sprint 14" (story `S14-1`) ;
  `squad/sprints/s14-points-d-entree-par-niveau/proposition-ines-barre-de-niveaux.md` ;
  `squad/sprints/s14-points-d-entree-par-niveau/avis-faisabilite.md` ;
  `squad/conformite/FICHE-barre-entree-niveau.md`, bloc d'arbitrage prealable ; `ADR-0022`,
  `ADR-0042` (amendes), `ADR-0043`, `ADR-0044` (neufs) ; `ADR-index.md` a jour.

## Fusion des lots

Deux lots restants de l'etape 1, chacun verifie par `outils/verifier-lignee.sh` avant fusion (les
trois premiers lots de s14 - camille-po, ines-design, nadia-prof/dettes - sont deja fusionnes,
acquis pour ce planning, pas a refaire) :

| Lot | Branche | Tete | Perimetre verifie | Lignee |
|---|---|---|---|---|
| theo-archi | `s14-theo` | `e539087` | Avis de faisabilite S14-1, `ADR-0043` et `ADR-0044` neufs avec leurs regles, amendements `ADR-0022`/`ADR-0042`, `ADR-index.md`, section 5 de cloture de `confirmation-archunit.md` de s13. 21 fichiers, aucun conflit. | Ancetre commun avec `round-two` confirme par `outils/verifier-lignee.sh`. |
| nadia-prof (dettes) | `s14-nadia-dettes` | `26c5d7d` | Bloc d'arbitrage prealable ajoute a `FICHE-barre-entree-niveau.md`, sept captures sous `squad/conformite/releves-s14/`. Les deux commits anterieurs de ce worktree (`868ef52`, `868fb54`) etaient deja fusionnes ; seul le commit d'arbitrage etait neuf. 8 fichiers, aucun conflit. | Ancetre commun avec `round-two` confirme par `outils/verifier-lignee.sh`. |

Fusionnes par `git merge --no-ff`, aucun conflit sur les deux. Tete de `round-two` apres fusion :
`b84d297` (theo : `eb908e3`, nadia-dettes : `b84d297`).

### Porte technique verifiee sur l'arbre fusionne

`bash outils/mvn-jdk21.sh -B clean verify` : **BUILD SUCCESS**, six modules en SUCCESS.

**Compte de tests, methode et resultat verifies moi-meme plutot que repris.** `theo-archi` annonce
495 tests sur son propre arbre (`confirmation-archunit.md` de s14), somme de l'attribut `tests` de
la balise `testsuite` des rapports XML de Surefire et Failsafe. J'ai rejoue la meme methode sur
l'arbre fusionne : lecture des `TEST-*.xml` sous `*/target/surefire-reports/` et
`*/target/failsafe-reports/` des cinq modules declares au pom, apres le `clean` de cette meme
commande. Repartition obtenue, identique poste par poste a celle annoncee : `adapter-web` 233,
`adapter-content` 120, `bootstrap` 88 (dont 6 en failsafe), `domain` 47, `application` 7 -
**495 au total, 0 echec**. Une premiere passe par les seuls resumes `.txt` de Surefire rendait 488
et sous-comptait pour le motif que `theo-archi` documente (un seul moteur JUnit conserve par classe
dans le `.txt` quand deux s'executent) ; je le signale pour que personne d'autre ne reproduise cette
erreur de methode.

**Compte de regles ArchUnit, deux methodes qui concordent, verifiees moi-meme.** Occurrences de
`ArchRule <nom> =` : (1) tous les `.java` sous `src/test/java` du depot, hors artefacts de
`target/` et hors le faux depot de contre-exemple de `LesGardeFousSontMisALEpreuveTest` (vit sous
`src/test/resources`) : **29** ; (2) balayage des seuls `src/test/java` des cinq modules declares
au pom : `bootstrap` 28, `adapter-web` 1 (`AssertionsWebTest`), `domain`/`application`/
`adapter-content` zero : **29**. Les deux methodes se recoupent, comme annonce.

Garde-fou de chemins rejoue comme la CI : `git ls-files -- . ':!.github/scripts/verifier-chemins-
test.sh' | bash .github/scripts/verifier-chemins.sh` -> "Aucun chemin de poste de travail ni nom de
compte dans les fichiers verifies.", code de sortie 0.

## Objectif du sprint (gele)

**Des points d'entree par niveau (CP / CM1), sous forme de deux boutons dans l'en-tete, toujours
visibles sur l'accueil et sur les cinq etats d'ecran de jeu ; un appui pendant une partie en cours
passe par l'ecran de confirmation existant plutot que de detruire la partie ; a l'accueil sans
partie en cours, l'appui filtre immediatement la liste des jeux sur le niveau choisi.** Le filtre
par matiere reste hors perimetre (`DEC-0032`, differe a s15). Ce sprint n'ouvre aucune ligne neuve
de `squad/programme/MATRICE-couverture.md` : c'est un sprint de navigation transverse, pas de
contenu.

**Gele** : les huit criteres d'acceptation developpes sont ceux **reecrits** par `theo-archi`
(section "Ce que developpe S14-1" ci-dessous), pas la forme d'origine du backlog. Aucune ouverture
de contenu pedagogique neuf. Le filtre par matiere et la question de nomenclature de la matiere
accentuee restent hors perimetre, reportes a s15.

## Un ecart entre la demande et ce que la barre livrera - a lire par l'utilisatrice

**"Toujours visible, y compris pendant une partie" ne signifie pas "visible sur tous les ecrans du
jeu".** L'arbitrage prealable de `nadia-prof` (`FICHE-barre-entree-niveau.md`, rendu le
2026-08-17) pose trois conditions bloquantes qui retirent la barre de deux ecrans que la demande
d'origine ne distinguait pas :

- **BARRE-1** : la barre n'est **pas** visible sur l'ecran de confirmation d'abandon (deux boutons
  seulement, "Continuer la partie" / "Les jeux" - deux puces de niveau de plus y creeraient quatre
  reponses a une question qui n'en attend que deux, dont une est le geste meme qui vient d'ouvrir
  l'ecran).
- **BARRE-2** : la barre n'est **pas** visible sur l'ecran d'erreur ("Recommencer" reste la seule
  chose pressable - il n'y a plus de partie a proteger sur cet ecran, donc `nadia-prof` note que
  rien n'empeche l'utilisatrice de demander la barre la aussi si elle le souhaite, mais ce n'est
  pas ce que ce sprint livre).
- **BARRE-3** : sur les trois etats de manche (manche, echec, revelation), l'enfant ne doit pas lire
  plus d'une porte de sortie avant l'enonce - avec la barre seule, il en lirait trois. La condition
  demande de descendre la porte de sortie actuelle du fragment (aujourd'hui premiere ligne de la
  colonne de contenu) sous les propositions, sur ces trois etats, sans composant neuf.

**Ce que "toujours visible" veut donc dire a l'issue de ce sprint** : la barre est permanente sur
l'accueil et sur les trois etats de manche (manche, echec, revelation, une fois BARRE-3 tenue),
mais absente de l'ecran de confirmation et de l'ecran d'erreur. Sur ces deux derniers, changer de
niveau demande d'abord de sortir de l'ecran en cours, exactement comme aujourd'hui.

**Ce qu'il faudrait pour lever cet ecart** : un arbitrage prealable neuf de `nadia-prof`, motive,
sur la confirmation et sur l'erreur specifiquement - elle ne l'a pas refuse par principe, elle
constate que ces deux ecrans posent un risque de confusion different (une question a deux reponses
qui en gagnerait deux de plus ; un ecran ou l'enfant est deja le plus perdu). Je ne le demande pas
ce sprint : je le nomme pour que l'utilisatrice sache que la promesse initiale n'est tenue qu'a
cette condition pres, et decide elle-meme si elle veut rouvrir la question.

## Ce que developpe S14-1 - criteres dans leur forme reecrite par theo-archi

`theo-archi` refuse trois des huit criteres d'acceptation de `camille-po` en l'etat et les reecrit
(`avis-faisabilite.md`, section 7) ; il signale aussi une clause de garde-fou existante a remplacer
dans le meme commit. C'est cette forme, pas celle du backlog, que le lot developpe :

| # | Critere original (`docs/backlog/backlog.md`) | Statut | Forme retenue pour le developpement |
|---|---|---|---|
| 1 | "l'en-tete affiche deux boutons de niveau, CP et CM1" | **refuse, reecrit** | Exactement un point d'entree par niveau distinct du catalogue (`data-role="entree"`), dans l'ordre du catalogue, plus exactement un element `data-role="sortie"` libelle "Les jeux". Le nombre n'est pas ecrit dans le test : c'est celui que rend `niveauxDuCatalogue()` (deux au 2026-08-17). Remplace aussi la clause `hasSize(1)` d'`EnTeteEtMenuTest.leMenuDeLEnTeteContientExactementUneEntreeVersLAccueil`, qui epinglait un effectif fixe. |
| 2 | "sur un ecran de jeu, les deux boutons restent visibles" | encodable sous sa forme declarative seule | Aucune regle de masquage servie ne nomme la classe des puces. La visibilite reellement rendue reste jugee par `nadia-prof` en jouant (BARRE-1/2/3 ci-dessus). |
| 3 | "accueil sans partie, appui -> filtre immediatement, sans confirmation" | encodable, un mot retire | "Immediatement" n'est pas testable et retire. `GET /niveaux/{niveau}` sans partie en cours rend la liste filtree dans la meme reponse, sans le fragment de confirmation. |
| 4 | "partie en cours, appui -> confirmation avant tout changement, partie non modifiee" | **encodable, seconde moitie renforcee** | Apres la requete d'une puce sur une session qui porte une partie, l'etat lu par `EtatDePartieEnSession` est identique avant et apres, `jeuId` et compte de manches resolues compris (jamais une assertion sur le balisage, doctrine `ADR-0038`). |
| 5 | "continuer -> reprend exactement la manche affichee, aucun filtre applique" | encodable | Enonce, propositions et score de la manche apres annulation identiques a avant l'ouverture de la confirmation. "Aucun filtre applique" garanti par construction (`ADR-0044` : le niveau ne vit que dans l'URL, jamais en session). |
| 6 | "confirmer -> partie abandonnee comme un abandon ordinaire, accueil filtre" | encodable, a expliciter | Apres la requete : la session ne porte plus de partie en cours et le marqueur cote navigateur est efface (les deux gestes de `POST /retour/confirmer`), plus l'assertion sur la liste filtree rendue. |
| 7 | "la liste filtree contient exactement les jeux du niveau du `ContentDescriptor`" | encodable tel quel | Seul des huit encodable sans retouche. Placement impose par `ADR-0044` : le test vit dans `application` (sur `PartieService`), pas dans `adapter-web`. |
| 8 | "au premier chargement, liste = catalogue complet des onze jeux" | **refuse, reecrit** | "Onze" est un effectif destine a pourrir (`ADR-0042` a deja porte ce defaut). Forme retenue : au premier chargement, la liste rendue est exactement `jeuxDisponibles()`, sans filtre, aucun effectif ecrit dans le test. |

**Trois garde-fous existants que les huit criteres ne mentionnent pas et que le lot doit satisfaire
sans en avoir ete prevenu par le backlog** (`avis-faisabilite.md`, section 7.9, mesure du
2026-08-17) :

- `TexteAccentueDesGabaritsTest` refuse tout texte de gabarit hors de son inventaire : "CP"/"CM1"
  doivent y entrer dans le meme commit, en citant l'arbitrage prealable qui les couvre
  (`FICHE-barre-entree-niveau.md`, deja rendu - la porte pedagogique sur les libelles est deja
  franchissable).
- `HabillageDesEcransTest.aucuneClassePoseeSurUnElementNEstOrpheline` : toute classe de structure
  posee (`bandeau-niveaux`, `chip-niveau`) doit recevoir une declaration CSS reelle.
- `EnTeteEtMenuTest.leMenuDeLEnTeteContientExactementUneEntreeVersLAccueil` : le comptage fixe est
  remplace par la propriete du critere 1 ci-dessus, dans le meme commit que le gabarit qui pose la
  barre.

**Contrat technique impose par `ADR-0044`** : `niveauxDuCatalogue()` et `jeuxDuNiveau(String)` sur
le port entrant (`PartieService`), aucun port sortant neuf, aucun changement de `domain`, le niveau
ne vit que dans l'URL (aucun attribut de session). Vocabulaire de role tranche par `ADR-0043` :
troisieme valeur `entree`, `LActionNeDeclarePasMoinsQueLaSortieTest` deja etendu (deux jeux fautifs
neufs, deja fusionne dans le lot theo).

## Ce que ce sprint developpe

| Lot | Titre | Competence visee | Affecte a | Porte de sortie |
|---|---|---|---|---|
| S14-1 | Une barre d'entree par niveau, toujours visible dans l'en-tete (sur l'accueil et les etats de manche) | transverse, aucune ligne de `squad/programme/MATRICE-couverture.md` | `marc-dev` | Les huit criteres dans leur forme reecrite (tableau ci-dessus) verts, les trois garde-fous existants nommes ci-dessus verts, `mvn verify` vert. **Et**, separement : Done pedagogique de `nadia-prof` sur `squad/conformite/FICHE-barre-entree-niveau.md`, en jouant l'increment livre - verifie BARRE-1 a BARRE-9 par capture ou par lecture du document servi, verdict ecrit sur cette meme fiche. |
| Dette technique 1 - le retrait d'un jeu du catalogue retire la methode de test dans le meme geste | technique, aucune ligne de programme (avis de `theo-archi`, `avis-faisabilite.md` section 8.1) | `lea-dev` | Les cinq methodes `leJeuDuCatalogue...ChargeBienLePaquet...` (`PaquetCm1MesuresTest`, `PaquetCm1PerimetreTest`, `PaquetCpComplements10S9Test`, `PaquetCpSoustractionsS9Test`, `PaquetCm1GrandsNombresTest`) remplacees par une seule clause qui, pour chaque entree de `catalogue.yaml`, verifie que le chargeur reel rend le paquet designe (derivation entrees -> paquets, jamais l'inverse). Preuve de morsure : une entree retiree de `catalogue.yaml` fait disparaitre le cas correspondant sans edition manuelle, et sa restitution le restitue dans le meme geste. `mvn verify` vert. |
| Dette technique 2 - le quatrieme axe de rang (position a l'ecran) devient mesure, pas seulement lu | technique, aucune ligne de programme (avis de `theo-archi`, `avis-faisabilite.md` section 8.2) | `sofia-sm` (casquette dev) | Une distribution de positions mesuree sur un grand nombre de tirages avec une `RandomSource` de graine declaree dans le test, comparee aux memes bornes que le garde-fou de rang de valeur existant. Le trou nomme d'avance (la graine mesure l'algorithme de melange, pas la qualite du generateur de production) est ecrit en commentaire a cote du test, pas decouvert plus tard. `mvn verify` vert. |

`yanis-qa` traduit chaque critere en test au fil des commits des trois lots.
`relecteur-pedagogique` intervient apres le Done technique de chaque lot, avant la review.

## Ce qui sort du perimetre, et pourquoi

1. **Le filtre par matiere.** Differe a s15 par `DEC-0032` elle-meme : la nomenclature affichable
   des matieres (accents) n'est pas tranchee. Non rouvert ici.
2. **La question de nomenclature de la matiere accentuee.** `theo-archi` a rendu un avis
   consultatif (`avis-faisabilite.md`, section 10 : la valeur interne devrait devenir elle-meme la
   valeur accentuee, cout mesure a douze fichiers YAML et une assertion de test). Ce n'est ni une
   decision produit ni une decision de redaction : elle reste a trancher par `camille-po` et
   `nadia-prof` avant que s15 n'instruise le filtre par matiere. Aucun code ce sprint.
3. **La variante 2 d'`ines-design` (bandeau a defilement horizontal).** Refusee par `nadia-prof` -
   motif principal : elle montre a un enfant qui apprend a lire un mot coupe en deux
   (`FICHE-barre-entree-niveau.md`, section 2).
4. **Le pictogramme et le disque decoratif sur les puces "CP"/"CM1".** Refuses par `nadia-prof`
   (BARRE-4) : reserver l'espace CSS pour un futur pictogramme de matiere est accepte, y afficher
   quelque chose ce sprint ne l'est pas.
5. **Un second rang de barre, ou tout ce qui grandirait la barre au-dela d'une ligne.** Verrouille
   par BARRE-8 : un second rang serait un arbitrage prealable neuf, non accorde d'avance pour s15.
6. **Le predicat `estRemplacant` d'`ADR-0042`.** Reste ouvert, non affecte ce sprint - voir
   "Decisions prises", point 1.
7. **Toute ouverture de contenu pedagogique neuf.** `camille-po` n'a affecte aucune ouverture ce
   sprint et je le gele explicitement : le plafond de trois ouvertures par sprint (`DEC-0030`) est
   un maximum, pas un minimum, et ce sprint est entierement une story de navigation transverse.
   Rien n'empeche une ouverture de contenu au sprint suivant.

## Decisions prises

1. **Le predicat `estRemplacant` d'`ADR-0042` reste ouvert, sans capacite affectee ce sprint.**
   `theo-archi` a verifie que `S14-1` ne fait apparaitre aucun arret remplacant (aucun bouton de
   demarrage n'atteint un ecran a partie en cours par ce lot) : coder le predicat maintenant serait
   l'ecrire vert sur une population vide, ce que l'amendement d'`ADR-0042` refuse lui-meme
   explicitement. Le declencheur reste celui que l'amendement nomme (le premier lot qui rend, sur
   un ecran a partie en cours, un arret dont la requete demarre une partie) : c'est une decision de
   capacite qui m'appartient, alignee sur le constat technique deja ecrit dans l'`ADR`, pas une
   reouverture de son contenu.
2. **Les deux dettes techniques routees par `DEC-0032` section 5 entrent au perimetre de ce
   sprint**, chacune un petit lot au sens de `theo-archi` (`avis-faisabilite.md`, sections 8.1 et
   8.2), affectees a `lea-dev` et `sofia-sm` : la capacite est disponible une fois `marc-dev` seul
   affecte a S14-1, et aucune des deux ne concurrence la story principale (modules et fichiers
   disjoints).
3. **Aucune ouverture de contenu pedagogique neuf n'est gelee comme un manque.** Voir "Ce qui sort
   du perimetre", point 7.
4. **Les criteres d'acceptation developpes pour S14-1 sont ceux reecrits par `theo-archi`**
   (tableau "Ce que developpe S14-1"), pas la forme d'origine du backlog - `docs/backlog/
   backlog.md` n'est pas modifie par ce planning, la forme d'origine y reste comme trace de la
   demande, la forme reecrite gouverne le developpement et la revue de code.
5. **L'ecart entre "toujours visible" et ce que BARRE-1/2/3 autorisent est remonte a l'utilisatrice
   telle qu'ecrite ci-dessus**, sans que je ne decide a sa place si elle souhaite rouvrir
   l'arbitrage sur la confirmation et sur l'erreur.
6. **Le repertoire de ce sprint reste** `s14-points-d-entree-par-niveau`, deja nomme par les trois
   premiers lots fusionnes.

## Obstacles routes

| Description | Categorie | Destinataire | Statut |
|---|---|---|---|
| Ecart entre "toujours visible" et les trois conditions bloquantes BARRE-1/2/3 (confirmation et erreur exclues) | pedagogie / produit | l'utilisatrice (via ce planning) | **remonte**, voir section dediee ci-dessus. Aucune action ce sprint au-dela du perimetre tel qu'il est. |
| Le bouton "Les jeux" de l'accueil rend un ecran identique a celui qui l'affiche (PORTE-1 du 2026-08-14), rendu du : cette story installe deux boutons voisins qui, eux, repondent (BARRE-7) | produit | `camille-po` | **ouvert** - deux issues acceptables pour `nadia-prof` (le bouton disparait de l'accueil, ou il y fait quelque chose), le choix lui appartient. A trancher avant ou pendant le developpement de S14-1. |
| Reserve J des grands nombres CM1 (proposition sous le pli) s'aggrave avec la barre, mesure par `nadia-prof` (R-BARRE-1) | pedagogie / produit | `camille-po` | **route, non bloquant** - candidat de story pour un sprint a venir, chiffre connu (aucun item ne doit placer une proposition sous le pli a 360 x 640). |
| Reserve F11 (ecoute reelle au lecteur d'ecran) s'enrichit : elle devra desormais couvrir les deux puces de niveau (R-BARRE-4) | pedagogie / accessibilite | aucun agent de la squad - action hors de sa portee, deja remontee a l'utilisatrice au sprint 13 | **ouvert, inchange dans son routage** - se protocolisera sur la fiche qui la porte une fois la barre livree. |
| Question de nomenclature de la matiere accentuee, avis consultatif rendu par `theo-archi` mais non tranche | produit + technique | `camille-po` (choix), `nadia-prof` (graphie) | **route pour instruction de s15**, non bloquant pour s14. |
| Predicat `estRemplacant` d'`ADR-0042` toujours sans objet en code | technique | `theo-archi` (dette deja actee dans l'`ADR`) | **reste ouvert, capacite non affectee ce sprint** - decision de sofia-sm, voir "Decisions prises" point 1. |

## Prochaines etapes

**Demarrent maintenant** :

- `marc-dev` ouvre un worktree dedie pour S14-1, verifie sa lignee
  (`outils/verifier-lignee.sh`), developpe selon le contrat d'`ADR-0044` et la forme reecrite des
  criteres ci-dessus, tests dans le meme commit que le code.
- `lea-dev` ouvre un worktree dedie pour la dette technique 1 (catalogue / methode de test).
- `sofia-sm`, casquette developpeuse, ouvre un worktree dedie pour la dette technique 2 (quatrieme
  axe de rang).
- `camille-po` tranche le sort du bouton "Les jeux" de l'accueil (obstacle ci-dessus), avant que ce
  choix ne bloque la revue du lot de `marc-dev`.

**Une fois chaque lot fusionne sur `round-two`** : `nadia-prof` joue l'increment de S14-1 et rend
son Done pedagogique sur `squad/conformite/FICHE-barre-entree-niveau.md`.

**Une fois l'arbre fusionne et le verdict rendu** : `yanis-qa` fait tourner `mvn verify` sur JDK 21
et ecrit le rapport qualite ; `theo-archi` confirme ArchUnit ; `relecteur-pedagogique` releve les
livrables non executables ; `sofia-sm` ecrit `review.md` puis `RAPPORT.md` et lance le rapport de
tokens (`python outils/rapport-tokens.py --sprint s14`).
