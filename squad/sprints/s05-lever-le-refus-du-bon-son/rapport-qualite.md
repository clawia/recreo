# [Sprint s05] Rapport qualite - yanis-qa

- **Date** : 2026-08-07
- **Base** : le worktree avait ete provisionne sur la tete de `main` (`aa2b356`), pas sur
  `round-two`. Reinitialise sur `d53adfc` avant tout travail, conformement a la consigne recue.
  Tout ce qui suit est mesure a partir de ce commit.
- **JDK** : le fichier de toolchains de l'utilisatrice est nomme `toochains.xml` dans son depot
  Maven local, pas `toolchains.xml` - Maven ne le lit jamais, silencieusement. Un JDK 21 existe
  bien sur le poste. `JAVA_HOME` positionne explicitement dessus pour chaque commande de ce
  rapport ; `java -version` sous ce `JAVA_HOME` : `21.0.10`. Sans ce contournement, `mvn -version`
  retombe sur un JDK 8 present par defaut sur le `PATH` - jamais un JDK 25, contrairement a s03.
- **Une instance de l'application tournait sur le port 8080 au debut de la session** (annoncee par
  `sofia-sm`) ; je ne l'ai pas touchee. Toutes mes verifications manuelles ont tourne sur le port
  8091, et j'ai arrete chacune de mes propres instances avant de rendre la main. Au moment ou j'ai
  verifie, le port 8080 n'etait deja plus occupe - je ne l'ai pas libere moi-meme.

## 1. Chantier prioritaire - le score qui ne s'incremente pas toujours

**Reproduit, sur les deux jeux, cause identifiee, sans lien avec le defaut d'accent deja corrige
au commit `d0ed8d3`.**

### Ce que ce n'est pas

Le defaut corrige au commit `d0ed8d3` (`InvalidParameterException` de Tomcat sur une reponse
accentuee) est reel et sa correction (`CorpsFormulaireEnUtf8ToleranteFilter`,
`RequeteFormulaireDecodeeEnUtf8`) est bien testee au niveau unitaire - voir section 3. Mais ce
n'est pas la seule cause du symptome signale par l'utilisatrice, et la methode qui l'a trouve
(requetes sequentielles, une `GET /manche` entre chaque) ne pouvait pas trouver l'autre.

### Ce que c'est

`PartieController.jouer` lit l'etat de partie (`BanditManchotSession`, un objet immuable) depuis
la session HTTP, calcule le nouvel etat, puis le reecrit dans la session HTTP :

```java
BanditManchotSession sessionActuelle = sessionEnCours(httpSession, jeuId);
BanditManchotSession suivante = (BanditManchotSession) partie.jouer(
        jeuId, sessionActuelle, new BanditManchotMove(propositionChoisie));
httpSession.setAttribute(ATTRIBUT_SESSION, suivante);
```

Ce cycle lire-calculer-ecrire n'est protege par aucune synchronisation, ni dans
`PartieController`, ni dans `PartieService` (`application`), ni ailleurs dans le depot (verifie
par recherche de `synchronized`, `Lock`, `Mutex` dans `adapter-web` et `application` : aucune
occurrence). Si deux requetes `POST /parties/{jeuId}/coups` arrivent assez rapprochees pour que la
seconde lise la session avant que la premiere n'ait ecrit sa reponse, les deux calculent leur
propre "suivante" a partir du **meme** etat de depart, et la derniere ecriture ecrase l'autre :
un increment de score reellement gagne disparait silencieusement de l'etat qui fait foi, alors
que l'ecran renvoye au navigateur pour cette reponse-la affichait "Bravo, c'est juste !". Aucun
message d'erreur, aucune trace : exactement la forme du symptome signale ("le score ne
s'incremente pas toujours").

C'est un defaut de `PartieController`/`PartieService`, communs aux deux jeux catalogues
(`catalogue.yaml` : `bandit-manchot-additions-cp` et `bandit-manchot-homophones-cm1` partagent la
meme mecanique et le meme controleur) - ce qui explique que l'utilisatrice le voie "sur les deux
jeux" sans qu'il ait de rapport avec le contenu de l'un ou de l'autre.

### Comment je l'ai reproduit

Deux clics rapproches (double clic, ou deux boutons de reponse differents touches avant que le
premier retour ne s'affiche - plausible chez un enfant) envoient deux `POST .../coups` presque
simultanes sur la meme session. Des requetes curl sequentielles ou envoyees depuis deux processus
distincts ne se recouvrent pas assez finement pour le montrer (l'ecart d'ordonnancement du
systeme d'exploitation entre deux processus depasse la fenetre de la course) : il faut deux
requetes emises depuis le meme processus, sur deux connexions TCP separees, avec une barriere qui
les synchronise juste avant l'envoi du corps.

Protocole (jeu `bandit-manchot-additions-cp`, mais le code de la course ne depend d'aucun
contenu) : demarrer une partie, lire l'enonce et calculer la bonne reponse ; lancer en parallele,
synchronisees par une barriere, une requete avec la bonne reponse et une requete avec une
proposition fausse presente sur la meme manche ; lire ensuite l'etat qui fait foi
(`GET /parties/{jeuId}/manche`).

**Resultat sur 40 repetitions independantes (nouvelle partie a chaque fois) : 14 pertes, soit
35 %.** A chaque perte : la reponse "juste" recoit bien un ecran "Bravo, c'est juste !" avec
`Score : 1` au moment de sa propre reponse HTTP, et pourtant l'etat qui fait foi juste apres
(`GET /manche`) retombe a `Score : 0` - le succes a existe pour l'enfant le temps d'un ecran, puis
a disparu de l'etat reel de la partie.

### Ce que je n'ai pas fait

Je n'ai pas corrige le code : le choix du mecanisme (verrou par identifiant de session, session
mutex du servlet container, deplacement de l'etat vers un magasin qui supporte l'ecriture
optimiste, autre) est une decision d'architecture qui n'est pas mon mandat. Aucun test de
non-regression n'existe aujourd'hui dans le depot pour cette course - en ecrire un sans que la
strategie de correction soit choisie figerait une solution non revue. Je remonte le symptome et sa
preuve de reproduction a `sofia-sm` pour routage vers `theo-archi` et le developpeur qui prendra
la correction.

### Autres chemins explores, sans defaut trouve

- **Aide sur une manche deja reussie** (`reussies` non vide) : sequence 1 reussite puis 3 echecs
  consecutifs sur `bandit-manchot-additions-cp`, jouee reellement (HTTP, pas en memoire) - l'aide
  se declenche a la bonne manche, le score reste correct tout du long. Contrairement au protocole
  de `sofia-sm`, qui n'avait teste qu'avec une liste de reussies vide.
- **Reprise apres rechargement** : lu dans `PartieController.accueil` - le cookie
  `recreo-partie-en-cours` distingue bien la reprise silencieuse (session HTTP encore valide) de
  la partie perdue (cookie present, session disparue). Rien de suspect a la lecture ; je ne l'ai
  pas rejoue en conditions de navigateur reel (redemarrage de processus, expiration de session),
  faute de temps dans ce passage.
- **Ecart entre le score de l'ecran de manche et celui de l'ecran de retour** : les deux lisent le
  meme champ `score.reussites()` sur le meme objet de session ; aucun ecart trouve hors du cas de
  la course ci-dessus.

## 2. `mvn verify`

Commande : `mvn -B clean verify`, sous `JAVA_HOME` sur un JDK 21 (`java -version` : `21.0.10`).

**BUILD SUCCESS. 226 tests, 0 echec, 0 test ignore.**

| Module | Tests |
|---|---|
| `recreo-domain` | 38 |
| `recreo-application` | 7 |
| `recreo-adapter-web` | 96 |
| `recreo-adapter-content` | 21 |
| `recreo-bootstrap` (unitaires) | 61 |
| `recreo-bootstrap` (integration, failsafe) | 3 |

Une ligne `ERROR` apparait dans le journal pendant `recreo-adapter-web`
(`GestionnaireDesErreurs : defaut imprevu sur POST /parties/un-jeu`) : verifiee, c'est
`UneErreurImprevueLaisseUneTraceTest` qui provoque deliberement ce defaut pour prouver `ADR-0018`
(un defaut imprevu doit laisser une trace en `ERROR`). Ce n'est pas un echec.

Aucun test instable constate sur cette execution unique. Je ne l'ai relancee qu'une fois pour
produire ce chiffre ; conformement a mon mandat, je ne relance pas un test pour "voir s'il repasse
au vert" - un rouge que je verrais disparaitre a la deuxieme tentative serait signale comme
instable, pas tu.

**Ce vert ne couvre pas la course sur le score decrite en section 1** : aucun test de ce depot ne
joue deux requetes concurrentes sur la meme session. Le Done technique que la CI juge aujourd'hui
est donc vrai sur ce qu'il verifie, incomplet sur ce que personne ne lui a encore demande de
verifier.

## 3. Verification des affirmations fortes du sprint

### `ChaquePaquetPorteUnIndiceTest.DETTE_DE_CONTENU` - vide confirme, plafond non aligne

`DETTE_DE_CONTENU = Set.of()` : confirme a la lecture, les deux paquets francais
(`cp-le-bon-son`, `cm1-homophones`) portent desormais un indice sur chacun de leurs items et ne
sont plus exemptes. L'affirmation de `marc-dev` est exacte.

**Le plafond ne correspond pas a ce que `planning.md` anticipait.** Le planning de s05 notait
explicitement : "si les deux lots ferment chacun leur part de la dette dans ce sprint,
`ADR-0016.DETTE_DE_CONTENU` devient vide et son `PLAFOND_DE_DETTE` (2 aujourd'hui) tombe a 0". Le
code montre `DETTE_DE_CONTENU` vide mais `PLAFOND_DE_DETTE` toujours a `2`, avec un commentaire
qui presente ce maintien comme la trace intentionnelle de la fermeture ("le plafond ci-dessous est
la trace de cette fermeture, et une regression future y redeviendrait visible"). Cette phrase ne
tient pas a l'examen : le seul test qui lit `PLAFOND_DE_DETTE`
(`laDetteNePeutNiGrandirNiPourrir`) verifie `DETTE_DE_CONTENU.size() <= PLAFOND_DE_DETTE`, une
inegalite large. Avec le plafond laisse a `2`, quelqu'un pourrait demain re-exempter jusqu'a deux
paquets sans indice sans que ce test-la ne rougisse - exactement la regression qu'`ADR-0016`
promet de ne jamais laisser passer. `ADR-0016` lui-meme est honnete sur ce point ("rien n'oblige
mecaniquement ce plafond a descendre") : ce n'est donc pas une affirmation fausse de `theo-archi`,
mais un ecart entre ce que `planning.md` attendait et ce que le code livre, et
`confirmation-archunit.md` ne mentionne ni cette fermeture ni ce plafond (deja releve par
`relecteur-pedagogique` dans `relecture.md`, section 4). Je le confirme de mon cote et je le
route a `sofia-sm` : soit le plafond descend a 0 dans un prochain commit, soit le commentaire du
code est corrige pour ne plus promettre une protection qu'il n'assure pas.

### `PropositionsAccentueesJouablesIT` - structurellement correct, ne mord pas

Verifie par mutation : j'ai neutralise `CorpsFormulaireEnUtf8ToleranteFilter` (retrait de
l'enregistrement Spring et de l'appel au decodeur tolerant, chaine renvoyee telle quelle), rebati
le module, et relance `PropositionsAccentueesJouablesIT` seul. **Le test reste vert avec le
correctif desactive.**

Cause : le test construit son corps de requete avec `URLEncoder.encode(proposition, UTF_8)` via
`java.net.http.HttpClient`, qui pourcent-encode systematiquement tout octet non-ASCII en une
sequence UTF-8 valide (`a` devient `%C3%A0`). Or le decodeur strict de Tomcat, celui que le
correctif contourne, ne leve `InvalidParameterException` que sur une sequence d'octets invalide en
UTF-8 - jamais sur du pourcent-encodage UTF-8 bien forme. J'ai confirme les deux sens sur le jar
reel, avec et sans le correctif :

| Requete envoyee | Sans le correctif | Avec le correctif |
|---|---|---|
| corps `propositionChoisie=à` (octets bruts UTF-8, non pourcent-encodes - constat isole par `theo-archi` dans `confirmation-archunit.md`) | ecran d'erreur | ecran de jeu normal |
| corps `propositionChoisie=%C3%A0` (pourcent-encode, ce que `URLEncoder` produit) | **ecran de jeu normal, sans le correctif** | ecran de jeu normal |

Le test derive bien du catalogue reel et joue une vraie requete HTTP contre un vrai serveur, comme
`lea-dev` l'annonce - la forme est exactement celle qu'`ADR-0013` demande. Mais la forme de
requete qu'il envoie n'est pas celle qui declenche le defaut d'origine : il prouve que des
propositions accentuees pourcent-encodees restent jouables, une propriete qui n'a jamais ete
cassee, ni avant ni apres le correctif. C'est le meme defaut de perimetre que celui deja nomme par
l'amendement de `theo-archi` a `ADR-0013` sur `PartieHttpTest` et `CatalogueChargeableIT` : les
donnees d'entree d'un test font partie de son perimetre, et celles-ci ne couvrent pas le cas qui a
casse la production.

La correction elle-meme reste bien testee : `RequeteFormulaireDecodeeEnUtf8Test`
(`neLevePasSurUneSequenceDOctetsNonValideEnUtf8`) construit directement un corps avec l'octet brut
`0xE0` - le meme octet isole que Tomcat refuse - et verifie que le decodeur maison ne leve pas.
C'est un test unitaire cible qui mord reellement sur le bon defaut ; ce qui manque est une preuve
d'integration qui envoie ce meme genre d'octets a travers une vraie requete HTTP, pas seulement a
travers le decodeur pris isolement. Je route ce trou a `sofia-sm` plutot que d'ecrire moi-meme le
correctif de test, parce que fabriquer une requete HTTP avec un corps non conforme en UTF-8 depuis
`java.net.http.HttpClient` demande de contourner son propre encodeur (envoyer les octets bruts sur
le flux du corps) - un choix de conception de test qui merite d'etre revu, pas glisse dans un
rapport.

### Le lien de menu "Accueil" face aux boutons d'action - trou confirme

Confirme a la lecture du balisage et des tests. Le lien "Accueil" (`.lien-accueil`,
`data-role="sortie"`) vit dans `accueil.html`, a l'interieur de `<header>`. Les boutons d'action
du jeu (`.proposition`, `.demarrer`, `.encore`, `data-role="action"`) vivent dans
`fragments/manche.html`, `fragments/retour.html` et `accueil.html` (le bouton "Jouer" de la
liste) - des fichiers distincts de celui qui porte le lien de menu, assembles seulement au rendu
par `th:replace`. `LActionNeDeclarePasMoinsQueLaSortieTest` (`ADR-0017`) ne confronte que des
boutons trouves dans un **meme fichier de gabarit** (ADR-0017, section "Ce que cette regle ne
couvre pas", point 5 : "Aucun couple action/sortie ne traverse aujourd'hui cette frontiere, mais
rien ne l'empeche demain"). Le couple (lien de menu, bouton "Jouer" ou bouton de reponse), pourtant
visible ensemble a l'ecran par un enfant, n'est donc confronte par aucun garde-fou automatique
aujourd'hui. `EnTeteEtMenuTest` le documente lui-meme dans son commentaire de tete ("Ce que ce
test ne prouve pas... Aucun garde-fou du depot ne confronte aujourd'hui un bouton d'accueil.html a
un bouton de fragments/manche.html"). Rien a ajouter de mon cote a ce que `theo-archi` traite deja
en parallele ; je confirme que le trou existe tel que decrit et qu'aucun test du depot ne le
referme par accident.

## 4. Tracabilite critere par critere, contre `docs/backlog/backlog.md` dans sa version courante

Le backlog n'a pas ete mis a jour depuis le commit `95b71e5`, anterieur a l'avis de `nadia-prof`
du 2026-08-06 sur le motif 3 (commit `814a286`) : le texte de S5-2 lu ci-dessous, y compris les
six criteres reecrits en cours de sprint (commit `79f2bfc`), est donc la version courante du
depot, mais elle ne reflete pas encore la recomposition que `nadia-prof` demande. Je le constate,
je ne tranche rien sur le fond - c'est a `camille-po` de mettre le backlog a jour.

| Story | Critere (resume) | Test nomme | Statut |
|---|---|---|---|
| S5-1 | 4 criteres, dont un explicitement laisse ouvert (reprise sur contenu retire) | aucun code ce sprint, par construction du planning | pas de regression a chercher : rien n'a change sur ce perimetre |
| S5-2 | 1er critere (reformule par `camille-po`) : majorite des items non resoluble par recherche visuelle, sur la reponse normalisee | **aucun** | non teste. Sans objet immediat : `nadia-prof` (814a286) a statue que ce critere, meme satisfait tel qu'ecrit, ne suffirait pas a lever son refus (motif 3 intact) ; elle demande une recomposition liee a S5-9, pas encore ecrite au backlog |
| S5-2 | 2e critere (consigne sans "entendre") | sans objet sur `round-two` (la consigne, comme l'entree de catalogue, n'existe que sur la branche jetable de l'audit) | non applicable a ce depot ce sprint |
| S5-3 | propositions sans crochets `[` `]` | `leBonSonCpNAfficheAucunCrochetDeNotationDAdulte` | couvert |
| S5-3 | six sons ecrits avec exactement leurs lettres | `leBonSonCpNePresenteQueDesSonsConnusEtCouvreLesTroisDigraphesCitesParLeReferentiel` | couvert |
| S5-4 | un seul son reellement present par item | explicitement "porte pedagogique seule", aucun automate attendu | conforme au critere (pas un manque) |
| S5-5 | indice affiche apres 3 echecs, sur `cp-le-bon-son` et `cm1-homophones` | pas de test unique nomme "S5-5" ; couvert par composition explicitement documentee dans `ADR-0016` : `ChaquePaquetPorteUnIndiceTest` (chaque item porte un indice) + `BanditManchotTest` (S3-2, la mecanique l'affiche apres 3 echecs, generique a tout contenu) | couvert par composition, pas par un test unique - conforme a ce qu'`ADR-0016` annonce lui-meme |
| S5-6 | les cinq couples ajoutes sont tous representes | `homophonesCm1NePresenteQueLesCouplesAttendusAvecUnUniqueDistracteurApparie` | couvert |
| S5-6 | les dix items existants ne changent pas | `homophonesCm1NeModifiePasLeContenuDesDixItemsExistants` | couvert |
| S5-6 | chaque couple represente dans les deux sens | verifie par le meme test (`containsExactlyInAnyOrder` sur les couples normalises dans les deux sens) | couvert |
| S5-6 | "regarder ailleurs dans la phrase" (au moins la moitie) | explicitement "porte pedagogique seule" | conforme au critere (pas un manque) |
| S5-7 | le paquet compte plus d'items que de manches par partie | `homophonesCm1CompteDavantageDItemsQueDeManchesParPartie` | couvert |
| S5-7 | deux parties a graines distinctes ne presentent pas les memes phrases | `homophonesCm1NePresentePasLesMemesPhrasesSurDeuxPartiesAGrainesDistinctes` | couvert |
| S5-8 | titre "Recreo" sans accent (`<title>`, `<h1>`) | `leTitreDuProduitSecritRecreoSansAccent` | couvert pour `accueil.html` ; le 2e critere ("tout autre gabarit") n'est pas balaye - seul `accueil.html` porte le nom du produit aujourd'hui, donc rien ne le contredit, mais une regression future sur un gabarit different ne serait pas attrapee par ce test-la (assume explicitement dans son commentaire, "ce n'est pas un balayage") |
| S5-8 | test nouveau dans `mvn verify` | le test ci-dessus existe et tourne dans `recreo-adapter-web` | couvert |
| S6-1 | en-tete permanent, icone, hors ligne | `lEnTeteNEstJamaisUnDescendantDeLaZoneDeJeu`, `lEnTeteResteVisibleQuandUnePartieEstRepriseSansEtreDuplique`, `lIconeDeLEnTeteEstServieHorsLigneDepuisLesRessourcesStatiques` | couvert |
| S6-1 | une seule entree de menu vers l'accueil | `leMenuDeLEnTeteContientExactementUneEntreeVersLAccueil` | couvert |
| S6-2 | contenu centre, largeur maximale | `leContenuDeLAccueilEstCentreDansUneLargeurMaximale` | couvert |
| hors story - `ADR-0017` (garde-fou de saillance) | l'action ne declare rien de moins que la sortie, sur un meme ecran | `LActionNeDeclarePasMoinsQueLaSortieTest`, 8 tests nommes (voir section 3 pour sa limite connue et documentee) | couvert dans son perimetre declare |
| hors story - `ADR-0018` (correctif, journalisation) | un defaut imprevu laisse une trace `ERROR`, une session perdue reste en `INFO` | `UneErreurImprevueLaisseUneTraceTest` et son homologue niveau INFO (verifie present et conforme par `relecteur-pedagogique`, `relecture.md` section 4) | couvert |
| hors story - correctif plante-manche sur accent | reponse accentuee jouable sans planter la manche | voir section 3 : unitairement mordant (`RequeteFormulaireDecodeeEnUtf8Test`), l'IT de bout en bout ne mord pas |

## 5. Autres assertions fragiles

- **`PartieHttpTest`** (deja nomme par l'amendement de `theo-archi` a `ADR-0013`, confirme a la
  lecture) : catalogue de test ecrit a la main, propositions `"1"`, `"2"`, `"3"`. Legitime pour ce
  qu'il teste reellement (les criteres d'acceptation de S0-2, sur le harnais HTTP, ecrits avant
  qu'aucun contenu reel n'existe - `ADR-0007`) ; le risque est seulement qu'il soit lu aujourd'hui
  comme une preuve sur du contenu reel, ce qu'il n'a jamais ete.
- **`CatalogueChargeableIT`** (meme amendement) : parcourt le vrai catalogue mais n'appelle jamais
  `jouer` - confirme a la lecture, verifie seulement que chaque jeu demarre et que
  `nombreDeManches` depasse `LONGUEUR_MINIMALE_POUR_AIDE`.
- **`PropositionsAccentueesJouablesIT`** : voir section 3, cas le plus concret de ce sprint - test
  qui passerait meme si le comportement qu'il pretend garantir etait casse.
- Aucune assertion HTML par expression reguliere trouvee dans les fichiers touches ce sprint ;
  tous les tests de rendu lus utilisent `jsoup` avec des selecteurs structurels
  (`page.select(...)`), y compris les nouveaux (`EnTeteEtMenuTest`,
  `LActionNeDeclarePasMoinsQueLaSortieTest`).

## 6. Ce qui reste ouvert, et a qui

- **Priorite** : la course sur le score (section 1) - `sofia-sm`, routage vers `theo-archi` pour
  la strategie de synchronisation, puis un developpeur pour la correction et son test de
  non-regression.
- `PLAFOND_DE_DETTE` non aligne sur la fermeture de `DETTE_DE_CONTENU` (section 3) - `sofia-sm`,
  routage vers `theo-archi`.
- Preuve d'integration manquante pour le decodage tolerant sur une vraie requete HTTP a corps
  malforme (section 3) - `sofia-sm`, a qui revient l'arbitrage sur la forme de ce test.
- Le lien de menu non confronte aux boutons d'action (section 3) - deja route et pris en charge
  par `theo-archi` en parallele ; je confirme seulement le constat.
- Le backlog n'integre pas encore l'avis de conception de `nadia-prof` du 2026-08-06 sur S5-2
  (section 4) - `sofia-sm`, routage vers `camille-po`.
- S5-1, second critere (reprise sur contenu retire), et S5-9 : deja portes au planning comme
  ouverts, rien de neuf a signaler de mon cote.

Aucun de ces points n'est un Done pedagogique ni son anticipation : je ne me prononce sur aucun
contenu ni sur aucun ecran, seulement sur ce que le depot prouve ou ne prouve pas.
