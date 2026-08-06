# [Sprint s03] Review - 2026-08-06

- **Horodatage de debut** : 2026-08-05 19:05 (repris de `planning.md`)
- **Horodatage de fin** : 2026-08-06 12:02 (commit `399fa5f`, dernier commit de l'ensemble de la
  fenetre d'audit - voir la note de fenetre partagee ci-dessous)
- **Duree effective** : 16h57
- **Anime par** : sofia-sm

## Note de fenetre partagee avec s04, et pourquoi je ne la retaille pas

s03 et s04 se referment dans le meme geste : `yanis-qa`
(`rapport-qualite.md`, sections 5 a 11) audite l'arbre fusionne `4c62f60`, qui contient deja le lot
francais de s04 ; `theo-archi` (`confirmation-archunit.md`) fait de meme et y ajoute
explicitement "la part s04 qui releve de moi" ; `nadia-prof` rend ses trois verdicts (maths CP,
francais CP, francais CM1) dans un seul geste d'audit le meme jour. Je clos les deux sprints par
des fichiers distincts (cette regle n'est pas negociable), mais je ne fabrique pas deux
horodatages de fin differents pour un travail qui s'est reellement termine au meme instant : ce
serait une precision fausse. Consequence assumee pour le rapport de tokens : la fenetre de s03
couvre mecaniquement aussi les messages qui portent sur le contenu francais de s04 et sur le
chantier Bootstrap hors sprint, puisque `outils/rapport-tokens.py` decoupe par horodatage et non
par sujet. Je le dis explicitement dans la section tokens plutot que de laisser le chiffre
suggerer une precision qu'il n'a pas.

## Increment livre

La ligne `mathematiques | CP | Nombres et calculs | Calcul mental - additions dans la limite de
10` passe a `couvert-valide` : c'est la premiere ligne de toute la matrice a atteindre ce statut.
Le tirage des additions couvre desormais l'intervalle entier au lieu de son bord superieur,
l'indice de S1-5 devient reellement atteignable en partie, et les six reserves visuelles et
redactionnelles restantes de `nadia-prof` sur `bandit-manchot-additions-cp` sont levees.

## Story par story

| Story | Livre | Verdict / statut |
|---|---|---|
| S3-1 - additions qui travaillent tout l'intervalle | Tirage uniforme sur les 65 couples valides (`(0,0)` exclu), filtre d'operande nul porte a 1/3, table de cibles recalculee sur le tirage compose (amendements du 2026-08-05 a `DEC-0004` et `DEC-0007`) | Couvert techniquement (`AdditionsBorneesContentSourceTest`, 20 graines) ; **VALIDE** pedagogiquement, mesure sur 2 000 manches reelles (`FICHE-bandit-manchot-additions-cp.md`, 1.1) |
| S3-2 - indice atteignable dans la partie en cours | Declenchement sur trois echecs consecutifs quel que soit l'item ; `GameMechanic.longueurMinimale()` publiee et verifiee contre le catalogue reel | Couvert et **VALIDE**, verifie en jeu par une sequence ecrite a l'avance (fiche, 1.2) |
| S3-3 - l'indice vit dans le YAML | Constante `INDICE` retiree du domaine ; texte porte par `cp-additions-jusqu-a-10.yaml` | Couvert (preuve de bout en bout sur le paquet reel) et **VALIDE** |
| S3-4 - le nombre pese plus que le bouton | `.proposition` compare a `.enonce`, jamais au litteral | Couvert et **tenu** (fiche, 1.4) |
| S3-5 - ecart confortable entre deux boutons | Critere reecrit sur la declaration `gap` (proxy assume, `theo-archi` et `camille-po`) ; `gap: 1rem` | Couvert et **tenu** |
| S3-6 - sortie visible sur fin et erreur | Boutons de sortie en aplat sur fin de partie et ecran d'erreur, contour conserve sur jeu/retour | Couvert (deux niveaux : declaration et rendu HTTP) et **tenu** |
| S3-7 - icone qui dessine un objet | Favicon redessine (crayon), vignette 16 px non issue d'une reduction | Couvert (proxy mesurable) et **tenu**, "il se lit comme un crayon des 48 px" |
| S3-8 - jouable a largeur telephone | **Prealable seul livre** : balise viewport, aucune largeur fixee au-dela de 375 px. Le reste (absence de depassement reel, chevauchement) reste explicitement hors backlog date, faute d'outillage de rendu (route depuis s02) | Prealable couvert et **tenu** ; reste de la story non tranchee, non close |
| S3-9 - "Recreo" devient "Récréo" | Titre et `h1` accentues ; balayage des gabarits derive du repertoire (ferme au meme geste la dette orpheline de `TexteAccentueDesGabaritsTest` sur `fragments/erreur.html`) | Couvert par lecture, **non couvert par un test automatise** (trou de tracabilite signale par `yanis-qa`, section 6) ; **tenu** pedagogiquement |

## Chantier hors sprint - Bootstrap (`ADR-0014`, `ADR-0015`)

**Ce chantier n'appartient ni a s03 ni a s04, et je le dis plutot que de le glisser dans l'un des
deux pour simplifier.** Il a ete declenche hors planning par une demande directe de
l'utilisateur ("le design ne plait pas"), pendant que s03 et s04 etaient tous deux en cours de
fusion sur `round-two`. `ADR-0014` porte `Sprint: s03` dans son propre en-tete et `ADR-0015`
`Sprint: s03 (redige hors ceremonie...)` : ce sont les mots de `theo-archi`, pas une decision de
process que j'ai prise. Je ne les corrige pas - un ADR se lit tel qu'ecrit par son auteur - mais je
n'en fais pas non plus une story de l'increment livre ci-dessus, parce qu'aucune des neuf stories
S3-1 a S3-9 ne le decrit et qu'aucune story de s04 (S4-1, S4-2) n'en parle davantage.

**Ce qui s'est passe, en bref** : `ines-design` a fait entrer Bootstrap 5.3.8 par vendorisation
tracee (`ADR-0014`), en deplacant le rendu de base des boutons et de la liste de jeux. `nadia-prof`
a **refuse** ce chantier sur un seul point (le bouton de sortie de l'ecran de retour, 30 % plus
grand que le bouton "Encore !", regression tracee au commit `6c83c97`) et l'a **leve** le meme jour
sur la correction d'`ines-design` (`a810fc7`), avec un rapport action/sortie remonte de 0,77 a
1,14, mesure par elle et non annonce. `theo-archi` a ferme dans le meme mouvement deux dettes de
perimetre qu'il avait nommees sans les fermer (`ADR-0015`) et un defaut de detection trouve par
`yanis-qa` (voir plus bas). Le detail integral - refus, levee, mesures au pixel - vit dans
`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, points 2 et 2 bis, parce que c'est la que
l'historique de l'habillage transverse vit depuis s02 (`DEC-0006`).

Je le range ici, dans la review de s03, pour une seule raison technique : c'est le dossier ou vit
deja l'historique de l'habillage de ce jeu, et `theo-archi` a lui-meme date ses deux ADR sur s03.
Mais je le marque explicitement **hors sprint** dans "Increment livre" ci-dessus (qui ne le cite
pas) et dans `RAPPORT.md` (qui le liste a part). Ne pas le nommer du tout serait le masquer ; le
compter dans l'increment de s03 serait pretendre qu'une story l'a demande.

## Rapport qualite - yanis-qa

- **Commande** : `mvn -B clean verify`
- **Resultat** : vert, deux executions independantes sur JDK 21.0.10 reel, **BUILD SUCCESS, 180
  tests** (`rapport-qualite.md`, section 5 ; le chiffre de `theo-archi`, 181 unitaires + 2
  d'integration = 183, porte sur un arbre incluant ses propres commits `ADR-0015`, posterieurs a
  celui que `yanis-qa` a audite - les deux chiffres sont exacts sur deux etats differents de
  l'arbre, ni l'un ni l'autre n'est faux).
- **Reverifie par moi a la cloture, sur `HEAD` (`399fa5f`)**, casquette Scrum Master : `mvn -B
  clean verify` sur JDK 21.0.10 (`JAVA_HOME` positionne pour la duree de la commande, jamais
  ecrit dans un fichier commite) : **BUILD SUCCESS, 183 tests, 0 echec** (38 `domain`, 7
  `application`, 69 `adapter-web`, 10 `adapter-content`, 57 `bootstrap` unitaires + 2
  d'integration). Ce chiffre confirme celui de `theo-archi` et montre que les trois derniers
  commits (`a810fc7`, `7e739e5`, `fb7f4df`, `399fa5f`, dont le retrait de "Le bon son" du
  catalogue) n'ont pas fait bouger le compte de tests. **Aucun rapport qualite de `yanis-qa` ni
  confirmation ArchUnit de `theo-archi` n'existe cependant sur `HEAD` lui-meme** : le dernier
  rapport nomme s'arrete a `4c62f60` (`yanis-qa`) et a l'arbre portant `ADR-0015` (`theo-archi`,
  anterieur a `a810fc7`). Ma reverification est une observation de passage, pas la porte technique
  au sens plein - meme distinction que celle que j'avais deja posee a la cloture de s02.
- **Couvert** : tracabilite complete S3-1 a S3-9 critere par critere (`rapport-qualite.md`, section
  6), preuve de decouplage domain/mechanic (section 7, partagee avec s04), verification
  independante du lot Bootstrap (section 8), balayage des assertions fragiles (section 9).
- **Non couvert** : S3-9 (titre accentue) n'a aucun test automatise malgre un critere testable
  (trou de tracabilite nomme par `yanis-qa`, section 6) ; S3-3 n'a aucun test qui empeche la
  reintroduction d'une constante de texte dans le domaine.
- **Tests instables** : aucune reapparition de l'instabilite de `LesReglesMordentTest` signalee en
  s01/s02. Une instabilite distincte et non reproduite, observee une fois en compilation de test
  de `recreo-application` sur la toute premiere execution de la session de `yanis-qa` (section 10),
  nommee et non causee.

## Confirmation ArchUnit - theo-archi

- **Commande** : `mvn -B clean verify`
- **Resultat** : vert, `BUILD SUCCESS`, **23 regles ArchUnit**, aucune n'echappe a son
  contre-exemple (`confirmation-archunit.md`, sections 2 a 4).
- **Regles ajoutees ce sprint** : aucune regle ArchUnit nouvelle sur `domain`/`application` pour
  S3-1 a S3-9 (`GameMechanic.longueurMinimale()` reste dans le perimetre deja garde). Deux garde-fous
  hors ArchUnit corriges pour le chantier Bootstrap hors sprint : `RessourcesTiercesTest` (neuf,
  `ADR-0014`) et la correction de perimetre de `TailleDesTextesEtBoutonsTest` (`ADR-0015`).

## Relecture - relecteur-pedagogique

- **Corrections appliquees** : aucune. `relecture.md` ne releve, dans le perimetre s02/s03/s04,
  ni faute de redaction (tiret cadratin, guillemet typographique, emoji, chemin de poste), ni
  ecart entre une documentation et le code qu'elle decrit.
- **Points a trancher** : la liste des libelles destines a l'enfant introduits depuis s01 est
  remontee a `camille-po` et `nadia-prof` pour audit pedagogique (registre de categorie B, hors du
  mandat du relecteur) - deja traite par le verdict de `nadia-prof` cite ci-dessous.

## Obstacles du sprint

| Description | Categorie | Agent route | Decision liee | Statut |
|---|---|---|---|---|
| S3-1 : filtre d'operandes nuls et table de cibles se contredisaient tels qu'ecrits | produit | camille-po | Amendements du 2026-08-05 a `DEC-0004` et `DEC-0007` (seuil 1/3, table recalculee sur tirage compose) | clos |
| S3-8 : critere central non verifiable par automate sans navigateur sans tete | produit + technique | camille-po (perimetre), theo-archi / yanis-qa (outillage) | Story reecrite (prealable seul livre, reste explicitement en backlog non date) | ouvert, non bloquant |
| S3-5 : "ecart reel entre les boites" non mesurable sans moteur de rendu | produit | camille-po | Critere reecrit sur la declaration `gap`, proxy assume dans le backlog | clos |
| `TexteAccentueDesGabaritsTest` omettait `fragments/erreur.html` | qualite / technique | yanis-qa | Perimetre derive du repertoire `templates/`, jeu fautif sur objet neuf (commit `5ee8ca7`) | clos |
| Comptage divergent 22 / 21 sur `LesReglesMordentTest` | qualite / technique | yanis-qa, theo-archi | Reconciliation ecrite : deux mesures legitimes du meme fichier (deux moteurs JUnit), ni doublon ni regle fantome | clos |
| Concordance des tags d'agent a quatre emplacements, pas trois | qualite / technique | yanis-qa | `ConcordanceDesTagsDAgentTest`, perimetre derive de `verifier-prefixes.sh` | clos |
| Quatre stories portaient vers un garde-fou a perimetre enumere | technique | yanis-qa, ines-design | Verifie a la review : aucune violation constatee, perimetre reste derive | clos, non bloquant |
| Angle mort `!important` sur les variables CSS Bootstrap (`declareEnImportant` lisait une sous-chaine) | qualite / technique | theo-archi | `ADR-0015` : recherche exigeant un debut de declaration, jeu fautif a la forme reelle de Bootstrap | clos |
| `.sortie-pleine` invisible au garde-fou (classe rendue par `th:class`, jamais lue) | technique | theo-archi | `ADR-0015` : perimetre derive de chaque etat rendu, classe calculee refusee | clos |
| `HabillageDesEcransTest.aucuneRessourceExterneNiPoliceTelechargeeALExecution` faisait doublon aveugle avec un perimetre plus etroit qu'annonce | technique | theo-archi | `ADR-0015` : methode supprimee, propriete portee par `RessourcesTiercesTest` | clos |
| `HabillageDesEcransTest` reste sans jeu fautif, dans `DETTE_DE_BALAYAGE` (plafond a 4, ne baisse pas) | technique | theo-archi | Aucun fichier ne le tranche ; `theo-archi` le nomme explicitement plutot que de le laisser croire solde | **ouvert, nomme par theo-archi** |
| Bootstrap hors sprint : bouton de sortie 30 % plus grand que le bouton d'action, refus pedagogique | pedagogie | nadia-prof, ines-design | Refus (`d269cb2`) puis correction (`a810fc7`) puis levee (`FICHE-bandit-manchot-additions-cp.md`, 2 bis) | clos |
| Aucune regle ne compare deux selecteurs entre eux sur un meme ecran (saillance relative) - troisieme defaut visible traversant une CI verte | technique | theo-archi | Besoin formule par `nadia-prof` ("tant qu'il n'existe pas, c'est moi le garde-fou") ; aucun fichier ne le tranche | **ouvert, route par cette review** |
| Trois agents ont valide sur JDK 25 en s03 en croyant qu'aucun JDK 21 n'existait | qualite / methode | sofia-sm (retro) | Cause trouvee par `lea-dev` : fichier de toolchains mal nomme (`toochains.xml`), pas un fichier du depot | clos comme constat ; amendement en `retro.md` |
| Trois worktrees provisionnes sur `main` au lieu de `round-two` | technique / methode | sofia-sm (retro) | Chaque agent a reinitialise sa branche ; lignage verifie sain a la fusion | clos comme incident ; amendement en `retro.md` |
| S5-5 : un paquet `type: question` ne peut porter d'indice (motif 5 du refus de "Le bon son") | technique | theo-archi | Deja route par `camille-po` dans le backlog (S5-5) ; je confirme le routage a la cloture, aucune decision ecrite encore | **ouvert, route** |
| S5-1 second critere (reprise de session ne revalide pas l'identifiant contre le catalogue) | produit | camille-po | `nadia-prof` demande explicitement de ne pas le fermer avant que `adapter-persistence` existe ; fenetre d'exposition nulle aujourd'hui | **ouvert, garde volontairement ouvert** |

Rappel : un obstacle n'est `clos` que si le fichier qui le tranche existe physiquement. Verifie pour
chaque ligne `clos` ci-dessus avant de l'ecrire.

## Porte pedagogique - nadia-prof

- **Verdict** : **VALIDE** (increment mathematiques CP corrige, S3-1 a S3-9).
- **Fiche** : `squad/conformite/FICHE-bandit-manchot-additions-cp.md`, section 1.
- **Motif** : condition posee en s01 sur la repartition des sommes, reportee deux fois, levee -
  mesuree sur 2 000 manches reelles (34,2 % sur les sommes 9-10 contre 43,9 % avant, 18,0 % sous 5
  contre 7,6 %, les 65 couples valides tous sortis, `0 + 0` jamais sorti sur 2 600 manches).
  L'indice est verifie atteignable en jeu par une sequence ecrite a l'avance. Les cinq reserves
  visuelles de s02 sont toutes tenues et mesurees.
- **Reserves** : aucune reserve bloquante restante sur cet increment. Une reserve non bloquante
  demeure sur le rapport chiffre/barre du bouton de reponse (fiche, 2 bis) et sur l'absence de
  progression intra-partie (reserve 3 de s00, non traitee par ce sprint et non une condition de la
  ligne de matrice).
- **Consequence produit** : `squad/programme/MATRICE-couverture.md`, ligne mathematiques CP,
  passe a `couvert-valide` - premiere ligne du produit a ce statut.

Le verdict sur le chantier Bootstrap hors sprint (refus puis levee) et les deux verdicts sur le
francais de s04 (VALIDE SOUS RESERVE pour le CM1, REFUSE pour le CP) sont repris dans leurs
sections propres ci-dessus et dans `squad/sprints/s04-ouvrir-le-francais/review.md` : je ne les
reprononce pas ici, je cite ou ils sont deja ecrits.

## Decisions prises

Aucune `DEC` ni aucun `ADR` n'est cree pendant cette review : elle constate et cite les decisions
deja ecrites pendant le sprint (`DEC-0004` et `DEC-0007` amendes le 2026-08-05, `ADR-0014` et
`ADR-0015`) et route les obstacles encore ouverts vers leurs proprietaires. Aucune n'est anticipee
ici.
