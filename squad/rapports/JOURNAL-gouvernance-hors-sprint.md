# Journal de gouvernance hors sprint

Faits de gouvernance survenus hors d'un sprint ouvert (aucun `planning.md` ne les couvre), donc
sans `retro.md` pour les recevoir. Tenu a la main, par ordre chronologique, jamais reecrit apres
coup - un fait constate ici reste ici, une correction ulterieure s'ajoute en dessous avec sa
propre date.

## 2026-08-24 - Origine poussee rouge malgre un `mvn verify` local connu en echec (`sofia-sm`)

**Contexte.** A la cloture de s19, `sofia-sm` verifie que `bandit-manchot-nature-mot-cp` reste
expose au catalogue malgre une reserve pedagogique bloquante et route la question a `camille-po`,
qui tranche `DEC-0045` (retrait immediat du catalogue, mecanisme deja etabli par `DEC-0031`) et
route l'execution a `sofia-sm`.

**Le fait.** `sofia-sm` fusionne `DEC-0045` et retire l'entree de `catalogue.yaml` dans le meme
commit (`273c62f`). `mvn clean verify` sur le reacteur complet rend `BUILD FAILURE` : un test
(`PaquetCpNatureMotTest.lEntreeDeCatalogueDesigneLeBonPaquet`) attend une entree de catalogue pour
un paquet desormais non expose - un rouge legitime a router, pas a contourner. Mais `sofia-sm`
pousse `273c62f` sur `origin/round-two` malgre ce rouge deja connu et ecrit dans son propre compte
rendu, en infraction directe a la regle explicite de `CLAUDE.md` et de son propre prompt d'agent :
*"tu ne pousses que si `mvn verify` est vert en local."* L'etendue reelle du rouge n'etait de
surcroit pas mesuree : le reacteur n'atteignait jamais `recreo-bootstrap` (dependance non
construite tant que `recreo-adapter-content` echoue), donc `TexteAccentueExposeParLePortEntrantTest`
et les garde-fous de coherence de catalogue cote `bootstrap` restaient une inconnue, pas un "non"
verifie.

**Correction.** Sur instruction de la coordination, `sofia-sm` revert `273c62f` (`git revert -m 1`,
historique preserve, aucune reecriture) dans un commit `75e48c3` qui dit explicitement que
`DEC-0045` n'est pas annulee - seule son execution repasse par la case instruction. `mvn clean
verify` sur le reacteur complet, atteignant `bootstrap` cette fois : `BUILD SUCCESS`, **666 tests**
par la methode de reference (somme de l'attribut `tests` des rapports XML surefire et failsafe
apres `clean`), identique a la reference de cloture s19 - coherent, le revert restaure exactement
l'arbre `d1cc948`. Poussee seulement apres cette verification : `origin/round-two` a `75e48c3`.

**Ce qui distingue ce fait des quatre versés en retro de s19.** Les quatre faits de gouvernance de
s19 (double routage, relecture sur arbre incomplet, `NAT-5` jamais saisie, Done technique jamais
juge par la CI) etaient des trous de coordination - un geste que la coordination n'a pas fait.
Celui-ci est une infraction directe a une regle ecrite et connue au moment des faits : le rouge
etait deja nomme dans le compte rendu qui a precede le push. Meme traitement que les quatre
precedents, sans plus de severite et sans moins : nomme, date, non minimise.

**Ce qui reste ouvert.** `theo-archi` instruit en parallele ce que
`PaquetCpNatureMotTest.lEntreeDeCatalogueDesigneLeBonPaquet` doit assertionner pour un paquet servi
mais non expose, et l'etat reel des garde-fous de `bootstrap` sur un arbre qui retire cette entree.
Le retrait de `DEC-0045` sera reapplique en un seul geste vert une fois sa reponse rendue.

## 2026-08-24 - Trois facons de faire vieillir un chiffre juste, trouvees par `theo-archi` en relisant sa propre ligne d'index

**Le fait.** En relisant la ligne d'index qu'il venait d'ecrire pour `ADR-0061`, `theo-archi`
trouve trois defauts, tous reels, sur le meme paragraphe qu'il avait lui-meme redige quelques
heures plus tot :

1. **Un chiffre recopie en se compressant.** Le corps de l'`ADR` disait que le retrait de
   `DEC-0045` pouvait etre "rejoue en une seule fois" (un seul geste, un seul commit) et comptait
   deux editions trois lignes plus bas. La ligne d'index avait recopie la premiere formule en "une
   seule edition mecanique" - vraie de la seule regression qu'`ADR-0061` corrigeait, fausse du cout
   total. Trouve par `sofia-sm` en appliquant reellement le retrait (deux editions mesurees, pas
   une).
2. **Une justification empruntee a un voisin sans verifier qu'elle vaut pour l'objet present.**
   "Meme motif qu'`ADR-0059` et `ADR-0060`" pour justifier qu'`AucunTestNeRecopieUneAssociationDu
   CatalogueTest` n'est pas une regle ArchUnit - alors que l'objet juge par ces deux `ADR` n'est pas
   un type Java (une declaration de feuille de style, une chaine de ressource), quand celui de
   `ADR-0061` est une valeur de constante compilee : un motif voisin, pas le meme.
3. **Une mesure juste rendue fausse par un mot au present a cote d'un SHA epingle.** "L'etat non
   expose compte zero occupant **aujourd'hui**" restait exact au moment de l'ecrire (`round-two` a
   `75e48c3`, 17 paquets pour 17 entrees) et devenait faux des que `sofia-sm` appliquait le retrait
   qu'`ADR-0061` rendait possible (17 paquets pour 16 entrees depuis `03d14ba`) - une affirmation
   auto-refutante par construction, puisque l'`ADR` lui-meme habilite le geste qui la rend fausse.

**Ce qui rassemble les trois.** Aucun n'est une erreur de mesure : le chiffre etait juste au moment
ou il a ete ecrit, dans les trois cas. Ce sont trois facons distinctes de faire vieillir un chiffre
juste sans jamais le remesurer faux : le recopier en le compressant, lui emprunter le motif du
voisin, l'ecrire au present a cote d'un etat fige plutot que date dans la meme phrase.

**Conclusion de `theo-archi`, versee ici plutot que tranchee par lui seul** : un chiffre porte sa
methode (regle deja ecrite, `squad/README.md` section 4, amendements s06/s16/s17) et **doit aussi
porter sa date dans la phrase elle-meme**, jamais dans une parenthese a cote qu'un lecteur presse
saute. Amendement retenu et verse a `squad/README.md` section 4 par `sofia-sm`, dans le meme geste
que cette entree de journal.

## 2026-08-29 - Troisieme cas de discretion d'execution sur `DEC-0045` section 3, cette fois arbitre par l'utilisatrice (`sofia-sm`)

**Le fait.** Les trois paquets du sprint 22 (`cm1-multiplications-divisions-10-100`,
`cm1-problemes-durees`, `cm1-comprehension`) ont ete `REFUSE` par `nadia-prof` le 2026-08-29
(`avis-nadia-done-pedagogique-s22.md`). `DEC-0045` section 3 prescrit le retrait immediat du
catalogue sur `REFUSE`. **L'utilisatrice arbitre d'attendre les corrections en cours plutot que de
retirer**, sur le precedent de `cp-mot-manquant` a s21 (`DEC-0052`, section 8) : les corrections de
`lea-dev` et `theo-archi` sont deja engagees sur deux des trois lots au moment de l'arbitrage, et
un retrait aurait impose un aller-retour de `catalogue.yaml` dans l'heure, exactement le cout que
`DEC-0052` mesurait sur ce meme precedent.

**Ce que je verse, sans le trancher : c'est le troisieme cas, pas le premier ni le second.**

| | `cm1-reperes` (s20) | `mot-manquant-cp` (s21) | trois paquets s22 |
|---|---|---|---|
| Regime applique | Retrait immediat, execute | Retrait retenu par `sofia-sm`, jamais execute | Retrait retenu par arbitrage de l'utilisatrice, jamais execute |
| Qui a decide de ne pas executer | - (execution conforme a la section 3) | `sofia-sm`, discretion d'execution non ecrite | l'utilisatrice elle-meme, sur instruction directe |

`camille-po` ecrivait en `DEC-0052` section 8, en refusant de trancher un amendement a
`DEC-0045` section 3 sur seulement deux cas : *"cette conception a besoin de plus de deux cas, ou
d'un arbitrage delibere plutot que d'une regle inferee sous la pression de la cloture d'un
sprint."* Le present fait est ce troisieme cas, et il est cette fois un arbitrage delibere,
pas une discretion silencieuse de `sofia-sm` comme au second. Je le nomme ici pour qu'il ne se
perde pas avant que `camille-po` ne juge si trois cas suffisent a instruire un amendement -
**ce n'est pas a moi de le juger**.

**Ce qui reste ouvert.** Le retrait des trois entrees, s'il doit avoir lieu, n'est pas execute
tant que les corrections en cours n'ont pas ete verifiees et fusionnees. Aucune entree de
`catalogue.yaml` n'est modifiee par ce fait.

## 2026-10-12 - Cloture du chantier hors sprint "accueil par matiere" (`camille-po`)

**Le fait.** Le chantier ouvert par `DEC-0117` (2026-10-11) est fusionne sur `round-two` a `75290b26` : implementation
de `lea-dev` (V2 par defaut, V1, bascule en session, *"Choisis ta classe."*), audit de `nadia-prof` (**VALIDE SOUS
RESERVE**, `APM-12` avant fusion, `APM-R1` non bloquante), mesure de `yanis-qa`. Aucun `planning.md` n'a ete ouvert ;
aucune entree de ce journal ne repondait a l'ouverture : `DEC-0117` section 8 chargeait `sofia-sm` de la verser, je
n'en ai pas trouve (`grep DEC-0117` sur ce fichier : 0). Cette entree la remplace pour la cloture, pas pour l'ouverture.

**Ce que je verse.**
1. **`DEC-0118`** (`Acceptee`) : le seuil de defilement de la vue par defaut passe de 10 a 8 cartes visibles a 744 x
   551, sur la mesure (8 / 8 / 8) et le motif de `nadia-prof` (*"8 me suffit pedagogiquement"*). Le critere est un
   plancher deja vert, dit comme tel. Aucun code ne porte 10 : rien a router.
2. **`DEC-0117` amendee** (section 9), statut `Proposee` tenu : `Q-U1` = `/` demande le niveau d'abord (option (c)),
   `Q-U2` = l'audit precede la fusion. **Les mots de l'utilisatrice ne sont dans aucun fichier du depot** ; seuls
   `rapport-lea-dev.md` s56 (section 16) et la javadoc de `PartieController.accueil` ecrivent qu'elle a decide. Fait
   Git relu : verdict `99008d84` a 20:49, les trois fusions a 21:11.
3. **Deux criteres de `HS-ACCUEIL-V1` et `HS-ACCUEIL-BASCULE` reecrits** (R7) : `Les jeux` mene au choix de la classe,
   la classe choisie s'ouvre en V1. L'ancien enonce (*"revient en V1"*) ne se tenait pas sur le livre ; le nouveau est
   tenu par un test existant, que j'ai lu et non relance. La version d'avant est reprise dans le backlog.
4. **Un ecart de matiere trouve en lisant le depot** : le jeu `bandit-manchot-rapprocher-deux-oeuvres-cp` n'est plus au
   catalogue depuis `13d00bfe` (2026-10-11 17:07, ancetre de `round-two`, 146 entrees, `grep` = 0), sur une decision
   de l'utilisatrice que le rapport de `lea-dev` date du 2026-10-11 et nomme (D1). Mais la cellule `ART-CP-10` de la
   matrice disait encore l'entree au present et (D) non tranchee, et `S56-RETRAIT-RAPPROCHER-OEUVRES` disait *"l'entree
   reste au catalogue"*. Les deux sont corriges. **`DEC-0116` A.17 n'est pas amendee** : elle dit toujours (D) non
   tranchee, et les mots de l'utilisatrice pour (D1) ne sont pas dans le depot. Le jeu refuse a l'audit de s56 n'est
   donc pas servi (retrait de `DEC-0045` section 3, execute le jour de la cloture de s56).

**Ce qui reste ouvert, par proprietaire, sans que je le tranche.**
- `theo-archi` : adresse apres `Les jeux` de la confirmation (`APM-R1`, R6 de `lea-dev`, E5) ; `;jsessionid` dans les
  URL de toute premiere reponse de session, anterieur au chantier (E4, R8 b) ; forme de reference du pictogramme
  (R1 de `lea-dev`, troisieme forme admise a ratifier) ; ecart de `B5` et `B8` a la lettre du critere de
  `S56-RETRAIT-RAPPROCHER-OEUVRES`.
- `nadia-prof` : clavier et bandeau colle sous le focus (non verifie) ; `APM-12` rejoue sur l'arbre fusionne (non
  retrouve) ; reconnaissance des pictogrammes ; que `DEC-0053` couvre ou non l'accueil (elle dit que le pli n'est pas un
  motif de `REFUSE`).
- `yanis-qa`, par `sofia-sm` : E2, aucun test Maven ne joue le catalogue reel dans les deux vues.
- `camille-po` : `APM-R1` et E5 ne sont pas encore des stories ; a 390 px (E3) aucune valeur n'est fixee ; amender
  `DEC-0116` pour la reponse (D1) quand ses mots sont connus.
- `sofia-sm` : l'entree d'ouverture de ce journal ; la capacite de `nadia-prof` consommee (1,0, annonce dans son audit)
  a consigner au planning de s57 quand il s'ouvrira.

**Ce que cette entree ne fait pas.** Elle ne declare aucune story `Done`, ne touche ni catalogue, ni paquet, ni code,
ne change aucune ligne de matrice hors la cellule de `ART-CP-10` (texte, pas statut ni compte), et n'ouvre pas s57.

## 2026-10-12 - Reponses de l'utilisatrice, chantier "accueil par matiere" et retrait d'`ART-CP-10` (`camille-po`)

**Le fait.** Trois reponses de l'utilisatrice, mots recus directement dans la session du 2026-10-12 (forme 2 de `DEC-0116` A.2) :
`Q-U1` *"ok pour c"* ; `Q-U2` *"non audit apres fusion"* ; `Q-U3` de s56 *"oui pour le retrait"*.

**Ce que je verse.**
1. **`DEC-0117` section 10** : les mots de `Q-U1` et `Q-U2`. `Q-U2` n'est pas ma recommandation (audit avant fusion) et n'est pas
   ce qui s'est passe pour ce chantier (verdict `99008d84` a 20:49, fusions a 21:11, VALIDE SOUS RESERVE, aucun `REFUSE`). Les deux
   faits sont ecrits cote a cote, sans conciliation.
2. **`Q-U4`, question residuelle pour elle** : quel mecanisme pour un `REFUSE` apres la fusion d'un chantier hors sprint
   (`DEC-0045` section 3 / `DEC-0055` : 48 h hors sprint, qui ne couvrent que le retrait d'une entree de catalogue). Non decide.
3. **`DEC-0116` A.26** : (D1) tranchee, mots cites ; le retrait avait ete execute le 2026-10-11 (`13d00bfe`), sa decision a maintenant sa source.
   `S56-RETRAIT-RAPPROCHER-OEUVRES` n'est plus conditionnelle.
4. **Compte** : 147 devient 146 (CP 58) la ou `DEC-0117` et le backlog le portaient ; `catalogue.yaml` verifie a 146 entrees. Les mesures datees du 2026-10-11 sont annotees, pas reecrites.
5. **Statut de `DEC-0117` : `Proposee`, tenu**, a cause de `Q-U4` seule.

**Ce que cette entree ne fait pas.** Aucun code, catalogue, paquet, ligne de matrice ni Done. Les points ouverts du 2026-10-12 (entree precedente) sont inchanges.

## 2026-10-12 - Audit avant fusion pour les chantiers hors sprint ; note a reprendre au planning (`camille-po`)

**Le fait.** Mots de l'utilisatrice, recus directement dans la session du 2026-10-12 : *"non nadia passe avant la fusion comme ca on peut re corrgier si besoin"*. Ils corrigent sa reponse a `Q-U2` (*"non audit apres fusion"*).

**Ce que je verse.**
1. **`DEC-0117` section 11** : regle, audit de `nadia-prof` avant fusion pour tout chantier hors sprint ; critere de fusion de la section 6 point 2 maintenu ; section 10.1 annotee comme remplacee, citation conservee. `Q-U4` sans objet. Statut `Acceptee` (aucune question a l'utilisatrice ne reste ouverte ; `Q-T1` a `Q-T3`, `Q-N1`, `Q-N2` sont adressees aux agents). `DEC-index.md` a jour.
2. **A `sofia-sm`** : ecrire la regle dans `squad/README.md` (sections 4 et 9), qu'elle tient. Tant que ce n'est pas fait, `DEC-0117` section 11 est la seule source.
3. **A reprendre par le planning du prochain sprint ouvert** (s57 n'est pas ouvert, aucun numero n'est suppose) : story `RP-ACCUEIL-147` de `docs/backlog/backlog.md`, adressee a `nadia-prof`. Les deux occurrences de 147 dans `ARBITRAGE-PREALABLE-accueil-par-matiere.md` (lignes 41 et 236) sont a corriger ou annoter ; cause : retrait `13d00bfe`, catalogue a 146. Demande de l'utilisatrice : *"ajoute le au repo et ping nadia dessus sur le sprint prochain"*. Je ne modifie pas ce fichier.

**Ce que cette entree ne fait pas.** Aucun code, catalogue, paquet, ligne de matrice ni Done.

## 2026-10-12 - Accord de l'utilisatrice sur D7, D8, D9 : consigne, contenu non retrouve (`camille-po`)

**Le fait.** Mots de l'utilisatrice, recus directement dans la session du 2026-10-12 (attestes par l'agent lanceur, forme 2 de `DEC-0116` A.2) : *"ce qui est propose pour D7 a D11 est ok"*. `D10` et `D11` sont traites par `sofia-sm`. Les codes `D7` a `D9` ne sont dans aucun fichier du depot pour ces points ; le rattachement (`U5`, `U6`, `U7` de `RAPPORT.md` de s56, et `A9`, `A10` de la retro) est celui de l'agent lanceur.

**Ce que je verse.** `DEC-0116` amendee (A.27 a A.31), `DEC-index.md` a jour. Pour chaque point, seulement ce que `review.md`, `retro.md`, `RAPPORT.md`, `DEC-0062`, `DEC-0114` et `DEC-0116` ecrivent :
1. **`D7`** : la borne depassee et le montant sont **non retrouves dans le depot, accord donne sans le contenu**. Aucun chiffre inscrit ; A.18 point 5 inchange. `Q-U5`.
2. **`D8`** : motifs et durees des deux arrets d'agent et du watchdog sur `lea-dev` **non retrouves dans le depot, accord donne sans le contenu** ; seule la fenetre de 23 h 49 min est mesuree (`retro.md` C1). `Q-U6`.
3. **`D9`** : la seule proposition ecrite est la lecture de `sofia-sm` (le pluriel est l'accord de la formule, *"non une decision"*) ; la convention structurelle n'existe que comme exemple de `theo-archi`. `Q-U7`. `DEC-0062` et `DEC-0114` non touches.

**Ce que cette entree ne fait pas.** Aucun code, catalogue, paquet, ligne de matrice ni Done ; ni `DEC-0062`, ni `squad/README.md`, ni `backlog.md`, ni `lessons.md`. `DEC-0116` reste `Proposee`. Aucune borne, aucun motif, aucune duree n'est inventee ou choisie a sa place.

## 2026-10-12 - La vue par defaut d'une classe choisie passe de V2 a V1 (`camille-po`)

**Le fait.** Mots de l'utilisatrice, recus directement dans la session du 2026-10-12 : *"par defaut apres avoir choisi sa classe montre le choix de la matiere"*. Lecture retenue : l'etage 1 de V1 (une tuile par matiere) devient la vue d'une session qui n'a rien choisi ; V2 reste par le bouton ; le choix reste en session (`DEC-0028`).

**Ce que je verse.**
1. **`DEC-0119`** (`Acceptee`, non appliquee : le code est inchange) : criteres A a E (ce que voit l'enfant apres la classe, libelles du bouton dans chaque sens, retour depuis un jeu, session), chacun avec son etat d'aujourd'hui (rouges : A1, B1, C1, D1 ; les autres sont des planchers deja verts, dits comme tels). **Condition de fusion : l'audit de `nadia-prof` precede la fusion** (`DEC-0117` section 11).
2. **`DEC-0117` section 12** (amende la decision 1 de la section 2) et **`DEC-0118` section 8** (8 cartes : valeur, mesure et motif inchanges ; le critere porte sur V2 atteinte par "Tout voir" ; ce n'est pas un report). Annotations, rien reecrit. `DEC-index.md` a jour.
3. **Backlog** : annotation datee des criteres remplaces ; stories `HS-ACCUEIL-DEFAUT-V1` et `HS-ACCUEIL-MESURE-DEFAUT`. **Fiche** : criteres de `camille-po` seulement, bloc de validation intact.

**Ce que je route.**
- `sofia-sm` : affecter `HS-ACCUEIL-DEFAUT-V1` (apres l'avis de `theo-archi`) et `HS-ACCUEIL-MESURE-DEFAUT` (`yanis-qa`) : `outils/mesurer-accueil.py` lit la page par defaut comme V2. Constat de lecture : `VueDeLAccueil.PAR_DEFAUT`, `AccueilParMatiereTest` et `VueDeLAccueilEnSessionTest` posent V2 ; le perimetre exact est a `lea-dev` et `yanis-qa`.
- `theo-archi` : `ADR-0105` (contexte : *"vue par defaut la seconde"* ; decision 2 : *"Valeur absente = vue par defaut"*, sans nommer la vue) est-il a amender, annoter ou confirmer ? Non tranche par moi.
- `nadia-prof` : la V1 par defaut change-t-elle son verdict ? Sa condition `N4` (*"V2 par defaut : ACCEPTEE"*) et sa lecture de `S15-5` ; "8 me suffit" (P8) dit pour le premier ecran, vaut-il pour V2 atteinte par un bouton ; l'etage 1 a 390 px (4 tuiles sur 8, E3) est maintenant la page par defaut. Non tranche par moi, bloc de validation non touche.

**Ce que cette entree ne fait pas.** Aucun code, catalogue, paquet, ligne de matrice ni Done. Je n'ai lance ni test ni jar : les "vert" et "rouge" de `DEC-0119` sont des lectures du code, des tests et des rapports, a rejouer par `yanis-qa`.

## 2026-10-13 - Reponses de l'utilisatrice a Q-U5, Q-U6, Q-U7 (`camille-po`)

**Le fait.** Mots recus directement dans la session du 2026-10-13 : *"pas de bornes superieur c'est au besoin mais on la consigne"* (`Q-U5`), *"je n'ai pas ces infos, la cause des arrets est la coupure de la batterie"* (`Q-U6`), *"oui pour Sofia"* (`Q-U7`).

**Ce que je verse.** `DEC-0116` A.32 a A.36 (`Proposee`, inchange) : `Q-U5` fermee (pas de borne superieure pour les garde-fous, consignation en quatre champs, `PAR-8` non touchee) ; `Q-U6` fermee (cause consignee, le reste dit non documente) ; `Q-U7` point 1 ferme, **point 2 ouvert**. `DEC-0062` amendement D (une ligne). `DEC-index.md` a jour.

**Ce que je route a `sofia-sm`.** `squad/README.md` section 2 bis (la formule tolere l'accord au pluriel, sans convention structurelle) ; la consignation des depassements de garde-fous dans la `DEC` d'ouverture ou le planning de s57 ; l'ecriture des arrets (`retro.md` `A10`) dans le planning de s57 ou `lessons.md`, limitee a la cause et a la liste de ce qui n'est pas documente.

**Ce que cette entree ne fait pas.** Aucun code, catalogue, ligne de matrice ni Done. `squad/README.md` et `lessons.md` non touches.

## 2026-10-13 - L'ordre des matieres est repris sous des motifs de forme 1 ; `DEC-0116` `Acceptee` ; `DEC-0120` (`camille-po`)

**Le fait.** Mots de l'utilisatrice, recus directement dans la session du 2026-10-13, tels que l'agent lanceur me les transmet (je ne les ai pas lus de sa main) : *"reprend l'ordre"*. Lecture de l'agent lanceur : le choix (ii) de `DEC-0116` A.22. Ses mots ne sont pas le motif : le statut repose sur des motifs de forme 1 mesures.

**Ce que je verse.**
1. **`DEC-0120`** (`Proposee`, ouverture de s57 **limitee**, le sprint n'est pas ouvert) : la table de `DEC-0115` section 3 remesuree sur `900db2dc`. Rang 1 : dix paquets de francais CE1 ; rang 2 : epuise (EMC CM1 11 sur 11, sciences CM1 19 sur 19) ; rang 3 : vide a s57, le motif de `DEC-0115` ("aucun plafond ecrit") est remplace par les plafonds de `nadia-prof` ; rang 4 : aucune ligne fermable aujourd'hui, `MAT-CE1-14` conditionnee a `A3` d'`ADR-0080`. `S54-OBS-SCI-CONSIGNE` retiree par un acte ecrit (deux reports, troisieme interdit), reversible. Prerequis du francais CE1 a deux instances places avec leur etat mesure. Reprise d'`ART-CP-10` et `VSJ-CE1-R1` retenues. **Le compte des instances est une question** (`Q-U1`) : *"deux instances de nadia"* ne dit pas si ce sont A et A' ou A et B ; deux sous les deux lectures, s58 contre s60 pour les dix lots.
2. **`DEC-0116` amendee** (A.37 a A.40) : **`Acceptee`**, par `DEC-0114` 0.4 (b) satisfaite par `DEC-0120` section 1. Lecture signalee dans A.38 : `DEC-0120` est une ouverture limitee.
3. **Backlog** : section "Ouverture limitee de s57" ; story `HS-ACCUEIL-APM-R2` ecrite (reserve de `nadia-prof`, criteres avec leur etat d'aujourd'hui) ; `S54-OBS-SCI-CONSIGNE` annotee retiree. `DEC-index.md` a jour.

**Ce que je route.** `sofia-sm` : planning de s57 apres `Q-U1` ; developpeur d'`APM-R2` ; heure de fusion de l'amendement d'`ADR-0065` si (A, A'). `nadia-prof` : accord sur `PAR-2`, tarifs, `RP-ACCUEIL-147`. `theo-archi` : `S57-ADR-0065-DEUX-INSTANCES`, moyen d'`APM-R2`. `yanis-qa` : `HS-ACCUEIL-MESURE-DEFAUT`.

**Ce que cette entree ne fait pas.** Aucun code, catalogue, paquet, ligne de matrice ni Done ; pas de `planning.md`, pas de tarif, pas d'affectation ; n'ouvre pas s57. `Q-U7` point 2 reste ouverte.

## 2026-10-13 - s57 compte trois instances, A, A' et B ; `DEC-0120` amendee, `Q-U8` ouverte (`camille-po`)

**Le fait.** Mots de l'utilisatrice, transmis par l'agent lanceur comme recus directement (forme 2, je ne les ai pas lus de sa main), en reponse a `Q-U1` : *"on plannifie A A' et B, on adapte les instances et la duree car il faut sortir une version"*. Ils ne disent ni la valeur de la borne, ni la duree, ni le contenu de la version.

**Ce que je verse.** `DEC-0120` amendement A.1 a A.8 (`Proposee`, inchange) :
1. `Q-U1` fermee : trois instances. A et A' de francais CE1, B pour les arts plastiques CP (reprise d'`ART-CP-10`, 0,5) et les sciences CM1. `VSJ-CE1-R1` reste a A. Ordre des matieres inchange.
2. `S54-OBS-SCI-CONSIGNE` : candidat de B **si** `nadia-prof` ecrit ses criteres ; sinon la retraite tient (deux reports, pas de troisieme).
3. Budget : deux options chiffrees, aucune choisie. O1, 8,0 maintenue : 2,67 par instance, 5,0 de travail, deux lots a s57, dix lots finis a s59 au plus tot. O2, borne relevee : exemple a 4,0 par instance (12,0, formule de `nadia-prof` de s32, pas une proposition), cinq lots, s58, 2,5 de B sans poste nomme. La borne est a `nadia-prof` (`PAR-8`), la duree a `sofia-sm`.
4. `A10` (verification de s54 contre les formes (a), (b), (c)) est due et non ecrite ; qui l'ecrit : `nadia-prof` ; `sofia-sm` et `theo-archi` fournissent le planning, les chiffres de sprint et, si elle le demande, l'isolation de trois couloirs. Mesure de la condition (i) de `nadia-prof` : B prend 0,5 chiffre contre 4,0 exiges. Collision d'identifiant signalee : `retro.md` de s56 porte deux lignes `A10`.
5. `Q-U8` : quel contenu doit sortir dans la version, pour quelle date.
6. Statuts : `DEC-0120` `Proposee`, `DEC-0116` `Acceptee` inchanges ; `S57-ADR-0065-DEUX-INSTANCES` engagee ; `S54-OBS-SCI-CONSIGNE` retiree, candidat de B sous condition. Backlog ("Ouverture limitee de s57") et `DEC-index.md` a jour.

**Ce que je route.** `nadia-prof` : `A10`, accord sur `PAR-2`, lecture de (i) et (iii), borne, affectation et prix de B, criteres de `S54-OBS-SCI-CONSIGNE` si elle veut la placer. `sofia-sm` : duree, planning a trois instances, heure de fusion de l'amendement d'`ADR-0065`. `theo-archi` : `S57-ADR-0065-DEUX-INSTANCES`. Utilisatrice : `Q-U2`, `Q-U8`.

**Ce que cette entree ne fait pas.** Aucun code, catalogue, paquet, ligne de matrice ni Done ; pas de `planning.md`, de tarif, de borne ni de duree ; n'ouvre pas s57.

## 2026-10-13 (second) - la version couvre CP, CE1 et CM1 au maximum ; `Q-U8` fermee en (a), distance mesuree (`camille-po`)

**Le fait.** Mots de l'utilisatrice, transmis par l'agent lanceur comme recus directement (forme 2, je ne les ai pas lus de sa main), en reponse a `Q-U8` : *"idealement CP-CE1-CM1 avec un maximum de couverture"*. Ni date ni statut de ligne exige : non inventes.

**Ce que je verse.** `DEC-0120` amendement A.9 a A.17 (`Proposee`, inchange) :
1. `Q-U8` (a) fermee. (b) la date et (c) le statut exige restent ouvertes, en questions precises. (d) nouvelle : le CE1 n'a que deux referentiels (francais, mathematiques) contre 8 au CP et 10 au CM1.
2. Mesure, commande de la "Methode du compte" par niveau : CP 51 / 59, CE1 25 / 44, CM1 66 / 79 ; 142 / 182 = 78,02 % ; 142 / 258 = 55,04 %.
3. Les 40 lignes non fermees, une par une : rang A 11 (dix lots de francais CE1, reprise d'`ART-CP-10`), rang B 10 (fermable par ecriture, jugement de `nadia-prof`), rang C 19 (bloquee, motif et source par ligne). Maximum atteignable 163 / 182 = 89,56 % ; rang A seul 153 / 182 = 84,07 %.
4. Projection : arithmetique (O1 : 3 a 5 sprints pour le rang A, O2 : 2) distinguee de la capacite mesuree de s54 a s56 (2, 2, 1 lots de francais CE1 par sprint, 1,67 en moyenne : O2 3 sprints sans marge, O1 4). Maximum : 3 sprints au plancher sous O2 au plafond dur, 6 a 8 sous O1 ; rythme des lots illustres non mesure. Borne et duree non choisies.
5. Lignes qui sortiraient non couvertes, nommees : 29 si la version sort au rang A, 19 au maximum, plus les 76 hors perimetre.
6. Ecart signale, non corrige : la cellule de matrice de `LVE-CM1-06` dit *"jouable par saisie"*, `nadia-prof` refuse la saisie depuis le 2026-10-01.

**Ce que je route.** `nadia-prof` : jugement du rang B, prix de ses lots, lecture d'A.15 pour `PAR-8`, `A10`, referentiels de CE1 si l'utilisatrice repond oui a `Q-U8` (d). `sofia-sm` : duree et planning. `theo-archi` : avis sur les quatre dessins manquants, lien de `A3` avec `MAT-CP-10`. Utilisatrice : `Q-U2`, `Q-U8` (b), (c), (d).

**Ce que cette entree ne fait pas.** Aucun code, catalogue, paquet, ligne de matrice ni Done ; pas de `planning.md`, de tarif, de borne ni de duree ; n'ouvre pas s57.

## 2026-10-13 (troisieme) - pas de date, les lignes `en cours` jouables comptent, le CE1 s'ouvre ; `Q-U8` fermee dans son principe (`camille-po`)

**Le fait.** Mots de l'utilisatrice, transmis par l'agent lanceur comme recus directement (forme 2, je ne les ai pas lus de sa main), en reponse a `Q-U8` (b), (c), (d) : *"pas de date"* ; *"on compte toutes les lignes meme en cours si le jeux est jouable"* ; *"pour le CE1 ouvre d'autres matieres"*. Aucun critere de jouabilite ni aucune matiere nommee dans ces mots : non inventes.

**Ce que je verse.** `DEC-0120` amendement A.18 a A.26 (`Proposee`, inchange) :
1. (b) : aucune date, la version sort quand le maximum est atteint selon (c). Les sprints d'A.15 deviennent des previsions ; O1 n'est plus exclue par un delai.
2. (c) : compteur de version = `couvert-valide` plus lignes `en cours` dont le jeu est jouable selon les **criteres de `nadia-prof`, en attente**. Le statut de matrice ne change pas (142 / 182).
3. Mesure, commande de la "Methode du compte" sur `59c4b56f` : `couvert-valide` 142, `en cours` 4 (`MAT-CP-01`, `MAT-CP-10`, `MAT-CE1-02`, `MAT-CE1-09`, un jeu au catalogue chacune), `non couvert` 36, `non couvrable en l'etat` 76. **Le nombre de lignes `en cours` jouables n'est pas mesurable sans les criteres** : borne seulement, 142 a 146 / 182 (78,02 % a 80,22 %), pas d'estimation. Aucun document de jouabilite de `nadia-prof` dans l'arbre `round-two` (`git ls-tree -r --name-only HEAD | grep -i jouabilit` : 0).
4. Maximum sous (c), borne haute : 173 / 182 = 95,05 % (163 + 4 lignes `en cours` + 6 lignes de plafond `en cours` qu'un lot rend `en cours`) ; 9 lignes atteignables restent dehors, nommees. Six lots non chiffres s'ajoutent : le travail d'A.15 est un plancher.
5. (d) : le CE1 a 2 referentiels. Effet sur le denominateur non mesure, aucun referentiel neuf ; les gestes a faire a leur arrivee sont ecrits (A.24).
6. `Q-U9` neuve : maximum sur la matrice figee ou vivante. `Q-U2` : `nadia-prof` interrogee sur `S54-OBS-SCI-CONSIGNE`, en attente ; deux reports comptes, un troisieme interdit.

**Ce que je route.** `nadia-prof` : criteres de jouabilite, referentiels de CE1, reponse a `Q-U2`, question sur le jeu branche avant son Done (A.23). `sofia-sm` : duree, sans date. Utilisatrice : `Q-U9`.

**Ce que cette entree ne fait pas.** Aucun code, catalogue, paquet, ligne de matrice ni Done ; pas de `planning.md`, de tarif, de borne ni de duree ; n'ouvre pas s57 ; n'invente aucun critere de jouabilite.
