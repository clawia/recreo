# [Sprint s15] Planning - regularisation retroactive, ecrite le 2026-08-18

- **Ce que ce fichier est** : une **regularisation**, pas un planning ordinaire. Six lots sont deja
  fusionnes sur `round-two` quand ce fichier s'ecrit ; aucun objectif n'a ete gele avant le premier
  commit, aucune affectation nominative n'a precede le developpement, et `theo-archi` n'a rendu
  aucun avis de faisabilite avant coup. `squad/README.md`, section 9, etape 1, decrit l'ordre
  normal (Camille ecrit les stories, Theo donne un avis de faisabilite, Sofia gele l'objectif et
  affecte, **puis** le developpement commence) : ce sprint ne l'a pas suivi. Je l'ecris en clair
  plutot que de fabriquer, avec le recul, un objectif qui aurait ete gele - ce serait un faux au
  sens que `squad/README.md` donne a ce mot.
- **Horodatage de debut (ouverture reelle du sprint)** : **2026-08-17 20:33:26 +0200**, premier commit du sprint,
  `7bf9692` (`[CAM] s15: DEC-0034 acte le retour de Les jeux et l'apparence commune des puces,
  DEC-0033 remplacee`). Verifie sur l'historique du depot (`git log`), pas suppose.
- **Ce fichier est ecrit le 2026-08-18, apres six lots** dont cinq sont fusionnes sur `round-two`
  (tete au moment de l'ecriture : `6fad9a9`) et un sixieme ne l'est pas - voir "Ce que je decouvre
  en regularisant" ci-dessous.
- **Anime par** : sofia-sm, casquette Scrum Master.
- **Mecanisme reel qui a pilote le sprint** : cinq arbitrages successifs de l'utilisatrice, rendus
  en jouant l'application livree de s14 puis chaque increment de s15, chacun declenchant un lot de
  developpement. Aucun n'a ete precede d'une story instruite au sens normal du terme ; `camille-po`
  et `theo-archi` documentent chaque arbitrage **apres** qu'il est rendu, jamais avant.

## Les cinq arbitrages, dans l'ordre, et ce que chacun defait

| # | Arbitrage de l'utilisatrice | Ce qu'il defait | Trace ecrite |
|---|---|---|---|
| 1 | "Les jeux" revient dans l'en-tete, dans son etat d'avant s14 | `DEC-0033` (retrait du lien) ; rouvre BARRE-7/PORTE-1, condition bloquante que `nadia-prof` avait fait fermer par le retrait | `DEC-0034`, arbitrage A |
| 2 | Les puces CP/CM1 prennent la meme apparence que "Les jeux", la bande sombre distincte disparait | L'arbitrage prealable de `nadia-prof` du 2026-08-17 (deux rangs, jamais adjacents) devenu **BARRE-9** ; ferme au passage sa reserve **S14-A** (bande vide de 16 px) | `DEC-0034`, arbitrage B |
| 3 | La sortie "Les jeux" reste visible dans l'en-tete y compris pendant une manche en cours | L'arbitrage de `nadia-prof` du 2026-08-07 (S6-9, `ADR-0022`) : une seule sortie par ecran de jeu, masquee sur les ecrans de jeu ; annule le compte de **BARRE-3** et de **BARRE-9** tel qu'ecrit | Aucune `DEC` ecrite - voir plus bas |
| 4 | Un seul rang dans l'en-tete, ordre `[crayon] Recreo [CP] [CM1] ... [Les jeux]` | Rien de tranche formellement ; retour a la ligne sous 360 px accepte d'avance, plancher de 360 px maintenu | Aucune `DEC` ecrite - voir plus bas |
| 5 | Une couleur sur la puce de niveau choisie, eteinte au demarrage du jeu | La premiere phrase du point 3 d'`ADR-0044` (*aucun etat que le serveur ne peut pas rafraichir*), ecrite par `theo-archi` lui-meme le 2026-08-17 | Aucune `DEC` ecrite - voir plus bas |

Deux defauts s'ajoutent, trouves en jouant plutot qu'arbitres :

- **Le survol qui effacait tous les boutons de l'en-tete** (et, plus largement, tout pressable du
  depot dont l'etat interactif n'etait pas declare cote maison) - defaut technique, ferme par
  `ADR-0046`.
- **Une regression du lot precedent** : une puce de niveau gardait sa couleur active apres un
  retour au catalogue complet (`POST /retour` et les routes voisines) - la regle de neutralisation
  du lot 4 ne s'appliquait pas sur ces routes. Ferme par une regle positive et conditionnelle
  (`dd51316`, `5b2ccc0`).

## Tableau des lots livres

Chaque porte de sortie ci-dessous est **verifiee physiquement par moi aujourd'hui**, pas reprise
d'un chiffre annonce (`squad/README.md`, amendement de cloture de s13 ; lecons de fusion de
`round-two`).

| Lot | SHA de fusion | Contenu livre | Implemente par | Porte de sortie |
|---|---|---|---|---|
| 1 | `3f11127` | `DEC-0034` (decision produit, arbitrages A et B) | `camille-po` | Fichier present sur le disque, verifie (`squad/decisions/produit/DEC-0034-...md`). Aucun code. |
| 2 | `739a161` | Arbitrages A et B en code (retour du lien "Les jeux" a l'identique d'avant s14, apparence commune des trois boutons) ; `ADR-0046` et son garde-fou (dette de survol fermee) | `marc-dev`, `theo-archi` | `BUILD SUCCESS`, 517 tests **annonces par le commit** - je ne rejoue pas cet etat intermediaire aujourd'hui, seul l'etat final (`6fad9a9`) est reverifie par moi ci-dessous. |
| 3 | `b109f98` | Arbitrage 3 en code (la sortie reste visible pendant une manche) ; retournement de l'epinglage du lien d'en-tete (accord `theo-archi`, `685c3fa`) | `marc-dev`, `theo-archi` | `nadia-prof` juge cet increment precis : **VALIDE SOUS RESERVE** (`901dedb`), sept conditions bloquantes posees pour la suite, reserve neuve S15-C. |
| 4 | `65da40e` | Arbitrages 4 et 5 en code (rang unique, couleur d'etat actif) ; mecanisme et amendement d'`ADR-0044` (`d452213`) ; forme chiffree d'`ines-design` (`7a22881`) | `marc-dev`, `theo-archi`, `ines-design` | `BUILD SUCCESS`, 528 tests annonces par le commit (`f46aa5a`). **Aucun Done pedagogique fusionne sur cet increment** - voir "Ce que je decouvre" ci-dessous. |
| 5 | `6fad9a9` | Correctif de la regression (regle positive et conditionnelle sur `.filtre-niveau`, polarite inversee par `theo-archi`, `dd51316`) | `marc-dev`, `theo-archi` | `BUILD SUCCESS`, 528 tests annonces par le commit (`5b2ccc0`), delta net nul. **Aucun Done pedagogique rendu sur ce correctif**, l'utilisatrice ayant explicitement reporte cette verification a la fin du sprint. |
| 6 | **aucun** | Done pedagogique **VALIDE** de `nadia-prof` sur le lot 4 (les trois mesures qu'elle seule pouvait produire : debordement a zero px aux six largeurs demandees, en-tete a 143 px a 360 px, contraste 9,25:1 sous le doigt) | `nadia-prof` | **Non fusionne.** Le commit existe (`726e0c4`, branche `s15-nadia-mesures`, parent `65da40e`) mais n'est l'ancetre d'aucun autre commit du depot : `git merge-base --is-ancestor 726e0c4 6fad9a9` repond **non**. Voir ci-dessous. |

### Porte technique verifiee sur l'arbre fusionne (`6fad9a9`)

`bash outils/mvn-jdk21.sh -B verify` (racine, reacteur complet, pas de `-pl`) rejoue aujourd'hui :

- **domaine + application + adapter-web + adapter-content : 522 tests, 0 echec**, sommes des
  quatre blocs `Results:` que j'ai lus moi-meme dans la sortie de la commande.
- **bootstrap, tests unitaires seuls : 82 tests, 0 echec.**
- Le reacteur **ne peut pas aller plus loin** aujourd'hui : `spring-boot:repackage` echoue sur
  `bootstrap/target/recreo.jar` parce que ce fichier est tenu ouvert par l'instance du port 8080
  que je ne dois pas arreter. Les quatre tests d'integration de `bootstrap`
  (`JarRecreoIT`, `CatalogueChargeableIT`, `PropositionsAccentueesJouablesIT`,
  `LeLivrableNeSertQueLesRessourcesDeSesModulesIT`, six methodes `@Test` au total, compte fait sur
  le disque) n'ont donc pas pu s'executer dans cette phase du reacteur.
- **522 + 6 = 528**, exactement le chiffre annonce par le dernier lot (`5b2ccc0`). Je ne recopie pas
  ce chiffre : je le reconstitue par une mesure fraiche plus une reconciliation arithmetique
  ecrite, et je nomme la limite (les six tests d'integration ne sont pas re-executes aujourd'hui,
  pour ne pas toucher l'instance de l'utilisatrice).
- **29 regles ArchUnit** : chiffre annonce de facon constante par `theo-archi` depuis l'amendement
  d'`ADR-0044` (s14) et repris sans variation dans chaque lot de s15 ; je ne le recompte pas moi-
  meme aujourd'hui, ce n'est pas un chiffre qui a bouge ce sprint.
- `.github/scripts/verifier-chemins.sh` : **code de sortie 0**, verifie par moi (aucun chemin de
  poste de travail ni nom de compte).

## Ce que je decouvre en regularisant

Ecrit ici parce que le depot est la seule memoire partagee, et qu'un ecart tu vaut pour ce sprint
ce que le faux vert de `CLAUDE.md` vaut pour un garde-fou : invisible tant que personne ne le
cherche.

1. **Trois des cinq arbitrages n'ont aucune trace en `DEC`.** `DEC-0034` ne couvre que les
   arbitrages A et B (les deux seuls cites dans son texte, sa chronologie et son routage). Les
   arbitrages 3, 4 et 5 sont bien enonces - dans des commits de `marc-dev` et de `theo-archi`
   ("DEC-0034 troisieme arbitrage...", "Deux demandes de l'utilisatrice, deja instruites...") - mais
   **aucun fichier `DEC` n'a ete amende ni cree pour eux**, et `docs/backlog/backlog.md` ne porte
   que `S15-1` et `S15-2` (les stories des arbitrages A et B). C'est exactement le defaut que la
   fusion et la cloture de `round-two` ont deja sanctionne : une story qu'un sprint absorbe en
   cours de route doit s'acter par un amendement ecrit a la decision d'origine, dans le meme geste
   que sa fusion. Ici, trois arbitrages sont deja fusionnes et vecus par le code sans qu'aucun `DEC`
   ne les porte. Je le route a `camille-po` : soit un amendement de `DEC-0034`, soit une `DEC-0035`
   distincte, mais un fichier qui existe physiquement avant que ce sprint ne puisse cloturer.
2. **Le sixieme lot n'est pas fusionne.** Le Done pedagogique `VALIDE` de `nadia-prof` sur le lot 4
   (rang unique, couleur d'etat actif), qui reduit la reserve S15-C et corrige le chiffre du pli
   (+13 px devenant +22 px depuis s14), existe dans le commit `726e0c4` mais n'est l'ancetre d'aucun
   commit de `round-two` - verifie par `git merge-base --is-ancestor`. Consequence concrete : la
   `FICHE-barre-entree-niveau.md` que porte `round-two` aujourd'hui s'arrete au verdict **VALIDE
   SOUS RESERVE** du lot 3 (`b109f98`) ; le verdict plus complet que `nadia-prof` a ecrit sur le lot
   4 n'existe nulle part sur la branche de travail. Je le route a moi-meme (fusion a faire,
   lignee de `726e0c4` deja verifiee par `nadia-prof` elle-meme selon son propre commit).
3. **`ADR-0045` n'est pas une decision de ce sprint.** Verifie dans `squad/decisions/adr/ADR-
   index.md` (entree 42) et dans `CLAUDE.md` lui-meme (section "Prerequis de build") : il est du
   sprint 14, date du 2026-08-17, et porte sur un garde-fou de catalogue sans rapport avec l'en-
   tete. Il ne figure pas dans la liste des decisions de s15 ci-dessous.
4. **Les reserves "S15-A" et "S15-B" n'existent pas.** J'ai cherche dans `FICHE-barre-entree-
   niveau.md`, y compris dans le contenu du commit non fusionne `726e0c4` : seule **S15-C** est une
   reserve numerotee de s15. Ce que le sprint 15 ferme est **S14-A** (levee) et la premiere moitie
   de **S14-B** (reduite), toutes deux heritees de s14, pas des reserves S15-A/B distinctes.
5. **La regle ArchUnit "clause E" a change deux fois de forme dans le sprint** (neutralisation par
   `theo-archi` le 2026-08-18, puis regle positive le meme jour apres le defaut de regression), sans
   qu'aucun amendement supplementaire d'`ADR-0044` ne soit ecrit au-dela de celui deja date du
   2026-08-18. C'est un jugement legitime de `theo-archi` (le mecanisme de l'ADR ne change pas, seule
   sa traduction CSS est corrigee) et je ne le conteste pas ; je le signale pour qu'un lecteur futur
   ne cherche pas un second amendement qui n'existe pas.

## Ce qui a ete fait en regularisant ce planning

- **Le repertoire du sprint est renomme** de `s15-la-sortie-vit-dans-l-en-tete` vers
  `s15-cinq-arbitrages-de-l-en-tete` (`git mv`), parce que l'ancien slug ne decrit que le troisieme
  arbitrage sur cinq. Les quatre fichiers deja presents (exigences de `theo-archi`, proposition
  d'`ines-design`, accord sur l'epinglage) sont deplaces avec leur historique, non modifies dans
  leur contenu au-dela du chemin qu'ils citent.
- **Quatorze fichiers referencaient l'ancien chemin** dans un commentaire ou une note (`javadoc` de
  tests et de `PartieController`, commentaires CSS et HTML, `ADR-0022`, `ADR-0044`, `ADR-index.md`,
  `FICHE-barre-entree-niveau.md`, et le fichier d'accord lui-meme). Chaque occurrence est corrigee
  par une substitution mecanique du seul segment de chemin, sans autre changement - verifie par
  `mvn test` sur `adapter-web` apres coup (260 tests, inchange).

## Etat du double Done

- **Technique : vert**, verifie ci-dessus par mes propres commandes sur `6fad9a9` - 528 tests
  (522 mesures fraiches aujourd'hui + 6 tests d'integration non re-executes pour ne pas toucher le
  port 8080, reconcilies arithmetiquement), 29 regles ArchUnit (chiffre stable, non recompte),
  garde-fou de chemins en code 0.
- **Pedagogique : partiel, et c'est le point que l'utilisatrice a explicitement reporte a la fin du
  sprint.**
  - Lots 1 a 3 (arbitrages A, B et 3) : **VALIDE SOUS RESERVE**, prononce par `nadia-prof` sur
    l'increment `b109f98` (`901dedb`), sept conditions bloquantes posees pour les lots suivants.
  - Lot 4 (arbitrages 4 et 5) : un verdict **VALIDE** existe (`726e0c4`) mais **n'est pas fusionne**
    - voir point 2 de "Ce que je decouvre" ; tant qu'il ne l'est pas, l'etat pedagogique visible sur
    `round-two` reste celui du lot 3.
  - Lot 5 (correctif de regression) : **aucun verdict, ni bloquant ni provisoire**. L'utilisatrice a
    demande que cette verification soit faite en fin de sprint, avec le reste.
  - Aucun Done pedagogique n'est prononce par moi ici : je restitue ce qui existe, je n'en fabrique
    pas.

## Tableau des obstacles ouverts

| Description | Categorie | Destinataire | Ce qui manque pour clore |
|---|---|---|---|
| Le lot 6 (`726e0c4`, Done pedagogique VALIDE du lot 4) n'est pas fusionne sur `round-two` | technique / methode | sofia-sm | Fusion du commit (lignee deja verifiee par `nadia-prof`), avec verification qu'aucun conflit n'apparait avec le lot 5 |
| Trois arbitrages (3, 4, 5) de l'utilisatrice n'ont aucun fichier `DEC` qui les acte | produit / gouvernance | camille-po | Amendement de `DEC-0034` ou `DEC-0035` neuve, physiquement ecrite |
| Aucun Done pedagogique sur le correctif de regression (lot 5, `6fad9a9`) | pedagogie | nadia-prof | Verdict ecrit dans `FICHE-barre-entree-niveau.md`, reporte par l'utilisatrice a la fin du sprint |
| Sept conditions bloquantes S15-1 a S15-7 de `nadia-prof` (`901dedb`) tenues sur le lot 4, mais jamais reverifiees par un Done fusionne | pedagogie | nadia-prof | Meme fusion que le point 1 ; sinon reverification sur l'increment final |
| Trou du selecteur compose : `AucunEtatDePressableNEstAbandonneAuTiersTest` credite `.chip-niveau.est-actif:hover` a `.chip-niveau`, pas au compose lui-meme | technique | theo-archi | Arbitrage de `nadia-prof` et `ines-design` sur `.proposition.deja-essayee` (une proposition deja essayee doit-elle declarer son propre etat sous le doigt ?) prealable a toute correction de la derivation |
| Reserve S15-C : rien ne distingue a l'oeil le bouton qui montre tout ("Les jeux") des puces qui filtrent | pedagogie | camille-po (story candidate), nadia-prof | Livrer S15-2/S15-3 tel qu'ecrit dans la fiche, ou accepter la reserve comme durable |
| R-BARRE-1 (reserve J, grands nombres CM1) : +13 px mesures sur `b109f98`, une aggravation de 9 px supplementaires (+22 px depuis s14) mesuree seulement dans le lot 6 non fusionne | pedagogie / produit | camille-po | Story candidate ; chiffre definitif conditionne a la fusion du lot 6 |
| R-BARRE-4 : la reserve F11 (ecoute au lecteur d'ecran) s'enrichit de deux ecrans sur cinq et de deux premiers arrets de tabulation | pedagogie / accessibilite | hors de la portee de la squad | Ouvert depuis s13, inchange dans son routage |
| Question de nomenclature de la matiere accentuee | produit + technique | camille-po (choix), nadia-prof (graphie) | `nadia-prof` en demande la fermeture avant tout filtre par matiere ; non traite ce sprint, non bloquant puisque aucun filtre par matiere n'a ete developpe |
| Garde-fou de provenance des classes amont (mesure partielle sans `-am`) | technique | theo-archi | Ouvert depuis s14, non affecte par s15 |
| Migration du marqueur `ecran-de-jeu` vers un attribut | technique | theo-archi (mecanisme), sofia-sm (capacite) | Ouvert depuis s14, differe depuis le declencheur de resolution de rendu |
| Resolution de navigateur d'`outils/mesurer-rendu.sh` (facteur de bus sur la mesure) | technique | sofia-sm (capacite) | Ouvert depuis s14, non affecte |
| Dependance a une seule personne pour toute mesure de rendu (le seul outil du depot pilote un navigateur en mode capture, jamais en survol ; `nadia-prof` mesure seule sur son propre poste) | technique / methode | sofia-sm (capacite), camille-po | Meme obstacle que la resolution de navigateur ci-dessus, aggrave par le constat de s15 sur le survol |
| Nom des marqueurs `ecran-exclusif` et `ecran-porte-sa-sortie` a soumettre a `ines-design` | design | ines-design | Confirmation explicite, `ecran-porte-sa-sortie` etant neuf de s15 et encore non revu |
| Predicat `estRemplacant` d'`ADR-0042` toujours sans objet en code | technique | theo-archi (dette actee) | Ouvert depuis s13, capacite non affectee ce sprint |
| Reserve F11 (ecoute reelle au lecteur d'ecran) | pedagogie / accessibilite | hors de la portee de la squad | Ouvert depuis s13, inchange |

## Correction du 2026-08-19 (trouvee par le relecteur-pedagogique en s16)

Le tableau ci-dessus dit "+13 px mesures sur `b109f98`, une aggravation de 9 px supplementaires
(+22 px depuis s14) mesuree seulement dans le lot 6 non fusionne" : **le sens du mouvement est
inversé**. Lu dans `squad/conformite/FICHE-barre-entree-niveau.md`, section "S15-7", tableau
"Etat du produit / Haut du cabinet" : avant s14 y = 397, s14 y = 410 (**+13 px**, chiffre exact mais
c'est le solde de s14, pas de `b109f98`), s15 avant le lot 6 (donc sur `b109f98`, premier lot de
s15) y = 419 (**+22 px** depuis avant s14 - c'est `b109f98` qui degrade, pas le lot 6), s15 avec le
lot 6 (rang unique et etat actif) y = 411 (**+14 px** - le lot 6 **recupere 8 px**, il n'aggrave
rien). Le chiffre final, une fois le lot 6 fusionné, est donc **+14 px**, non +22. Je ne
corrige pas la ligne d'origine, ecrite dans l'incertitude legitime du moment (le lot 6 n'etait pas
encore fusionne) : je verse ici la lecture correcte de la source, avec sa methode.

## Ce qui reste a faire pour clore s15

1. **Fusionner le lot 6** (`726e0c4`, Done pedagogique VALIDE de `nadia-prof` sur le rang unique et
   l'etat actif), lignee deja verifiee, pour que `FICHE-barre-entree-niveau.md` reflete l'etat
   pedagogique reel de l'increment livre.
2. **Faire ecrire par `camille-po` la trace `DEC` manquante** des arbitrages 3, 4 et 5 (amendement
   de `DEC-0034` ou `DEC-0035` neuve), avant que le sprint ne puisse etre dit gouverne par le depot.
3. **Faire jouer et juger par `nadia-prof` le correctif de regression** (lot 5, `6fad9a9`) - c'est
   la verification que l'utilisatrice a explicitement reportee a la fin du sprint.
4. **Router le trou du selecteur compose** (`.proposition.deja-essayee`) a `nadia-prof` et
   `ines-design` pour arbitrage, avant que `theo-archi` ne puisse corriger la derivation sans
   ouvrir une regression neuve.
5. **Faire trancher `ines-design` sur les noms `ecran-exclusif` / `ecran-porte-sa-sortie`**, en
   attente depuis leur creation.
6. **Ecrire `review.md` et `retro.md`** une fois les points 1 a 3 clos, avec le tableau final des
   obstacles et le verdict pedagogique complet de `nadia-prof` sur l'ensemble du sprint - non fait
   aujourd'hui, sur consigne explicite de l'utilisatrice.

## Decisions prises

1. **Ce fichier ne fabrique aucun objectif gele qui n'a pas existe.** Le sprint a ete pilote par
   cinq arbitrages successifs de l'utilisatrice, pas par un objectif instruit au planning ; je
   l'ecris comme une regularisation, pas comme un planning ordinaire republie a l'identique.
2. **Le repertoire du sprint est renomme** avec `git mv` et les references a son ancien chemin sont
   corrigees dans les quatorze fichiers qui les portaient, pour qu'aucun commentaire ne pointe vers
   un chemin qui n'existe plus.
3. **Je n'ecris ni `review.md` ni `retro.md` ni `RAPPORT.md` aujourd'hui.** L'utilisatrice a
   explicitement reserve la verification pedagogique de fin de sprint ; produire ces fichiers
   maintenant fabriquerait une cloture qui n'a pas eu lieu.
4. **Le lot 6 non fusionne et l'absence de trace `DEC` sur les arbitrages 3 a 5 sont ecrits comme
   des decouvertes de cette regularisation**, pas comme des obstacles routes en amont que je me
   contenterais de reprendre - c'est en verifiant la lignee et le contenu de chaque fichier de
   decision que je les ai trouves.
5. **Je ne recompte pas les 29 regles ArchUnit ni les chiffres des lots intermediaires** (517 tests
   au lot 2, 523 au lot 3) : seul l'etat final de l'arbre (`6fad9a9`) est reverifie par mes propres
   commandes, avec sa methode et sa limite ecrites. Recompter chaque etat intermediaire depasserait
   le perimetre d'une regularisation de planning.
