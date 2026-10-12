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
