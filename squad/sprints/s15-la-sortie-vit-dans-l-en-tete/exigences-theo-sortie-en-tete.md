# Exigences d'architecture - la sortie vit dans l'en-tete (arbitrage de l'utilisatrice, 2026-08-18)

- **Auteur** : theo-archi. **Date** : 2026-08-18.
- **Branche** : `s15-theo-sortie`, worktree dedie, forkee de `round-two` a `739a161` ; lignee verifiee
  par `outils/verifier-lignee.sh`.
- **Destinataire** : `marc-dev`, qui implemente en parallele. Les sections 1 a 3 sont ce qu'il attend ;
  elles passent donc avant tout le reste de ce document.
- **Ce que je ne fais pas** : rediscuter l'arbitrage. L'utilisatrice a tranche que la sortie vit dans
  l'en-tete y compris pendant une partie ; c'est au-dessus de la squad, le refus de `nadia-prof` du
  2026-08-07 reste lisible dans sa fiche, et je n'ecris pas une ligne pour ou contre.
- **Aucune mesure de rendu n'est revendiquee ici** : je n'ai pas de navigateur, et aucune des
  proprietes ci-dessous n'en demande un. Les seuls chiffres cites sont des lectures de source, avec
  leur commande.

## 1. Le marqueur : un second, et son critere est verifiable

**Etat cible : je valide celui que `sofia-sm` derive de la demande.** La sortie d'en-tete est visible
sur l'accueil et sur la manche ; elle disparait sur la fin de partie, sur la confirmation et sur
l'erreur. Trois ecrans sur quatre masques, la manche laissee.

**`ecran-exclusif` ne couvre pas ce sous-ensemble, et il ne doit pas etre etire pour le couvrir.** Il
est porte par `confirmation` et `erreur` ; il manque la fin de partie. Le lui ajouter masquerait aussi
les puces de niveau sur la fin de partie, ce que l'arbitrage de s14 refuse - les puces y restent
visibles. Un marqueur qui gagne un ecran pour une raison etrangere a ce qu'il declare est le debut de
la derive que la reforme de la clause 2 vient de fermer.

**Il faut donc un second marqueur, et le critere juste est bien celui que `sofia-sm` propose : "cet
ecran porte sa propre sortie".** C'est un etat de l'ecran, jamais l'effet attendu sur l'en-tete, et il
a une qualite que `ecran-exclusif` n'a pas : **il est verifiable contre le document**. `ecran-exclusif`
porte un jugement pedagogique que seule `nadia-prof` peut rendre ; celui-ci enonce un fait structurel
que le gabarit contient ou ne contient pas. C'est ce qui permet la clause C ci-dessous.

**Trois regles de masquage au total, et la forme contractuelle est inchangee** (`body:has(.<marqueur>)
.<habillage> { display: none }`) :

| Regle | Ecrans concernes | Motif |
|---|---|---|
| `body:has(.ecran-exclusif) .chip-niveau` | confirmation, erreur | **existe deja**, inchangee |
| `body:has(.ecran-exclusif) .lien-accueil` | confirmation, erreur | **neuve** : sur un ecran exclusif, aucun element permanent actionnable ne survit - c'est la clause B de s14, et la sortie d'en-tete y entre comme les puces |
| `body:has(.<marqueur-de-sortie-propre>) .lien-accueil` | fin de partie, confirmation | **neuve** : BARRE-5 plafonne le mot "Les jeux" a une occurrence par ecran |

**Et la regle `body:has(.ecran-de-jeu) .lien-accueil` est retiree** : c'est elle qui masque la sortie
sur la manche, et c'est exactement ce que l'arbitrage annule.

Deux regles masquent donc `.lien-accueil` par deux marqueurs differents, et la confirmation porte les
deux marqueurs. **Ce n'est pas une redondance a supprimer** : chaque regle est vraie pour son propre
motif, et retirer l'une parce que l'autre couvre le meme ecran ferait dependre une propriete de
l'autre. La forme contractuelle et son garde-fou traitent chaque masquage independamment ; c'est
prevu.

**Le nom du second marqueur est du balisage, donc le mandat d'`ines-design`** - il vit dans une
constante de garde-fou et dans deux attributs de classe. Je propose `ecran-porte-sa-sortie`. Ce qui
n'est pas negociable est qu'il nomme le **fait** ("cet ecran porte sa propre sortie") et non l'effet
("ici l'en-tete se taite").

**Le piege de s14 vaut a l'identique et il est deja documente dans la feuille** : le masquage nomme la
classe portee par l'element pressable - `.lien-accueil` - jamais celle du `<nav class="menu">` qui
l'enveloppe. `AucunArretDeTabulationNEstDestructeurTest` evalue le masquage element par element ;
nommer le conteneur laisserait la sortie dans l'ordre de tabulation en la rendant invisible a l'ecran.

## 2. Ce que `marc-dev` doit livrer

**Production**

1. `fragments/manche.html` perd son bouton `.retour-accueil` (`data-role="sortie"`, `hx-post="/retour"`).
   Le releve de ce que ce retrait coute est en section 4 ; il est court.
2. `fragments/retour.html` et `fragments/confirmation.html` portent le marqueur neuf. La confirmation
   garde `ecran-exclusif` en plus.
3. Les trois regles de masquage de la section 1, et le retrait de la quatrieme.

**Garde-fous**

- **Clause C, neuve - le marqueur ne peut pas mentir.** Un gabarit porte le marqueur de sortie propre
  **si et seulement si** son document contient un `[data-role=sortie]`. Population derivee du
  repertoire de gabarits, jamais une liste de noms d'ecrans. Elle ferme les deux fautes symetriques, et
  chacune est un defaut reel : un ecran qui gagne une sortie sans le marqueur ferait lire "Les jeux"
  deux fois a l'enfant ; un ecran qui porte le marqueur sans sortie masquerait la sortie d'en-tete sans
  rien mettre a la place, donc laisserait un cul-de-sac - le motif exact du refus de `nadia-prof` en
  s06.
- **Clause D, neuve - BARRE-5, sous sa forme derivee et sans aucun nombre.** Sur **tout** ecran
  assemble, une fois les masquages servis appliques, **au plus un** element visible porte
  `data-role="sortie"`. Population derivee du corpus d'ecrans assembles, masquages lus dans la feuille
  servie (les deux derivations existent et sont partagees dans le paquet : `masquages`, `masqueSur`,
  et l'assemblage de `ToutEcranEchangeEstUnDocumentJouableTest`). **Non-vacuite exigee** : au moins un
  ecran porte une sortie visible. C'est la mesure `0/1/1/0/1` de `nadia-prof` rendue executable sans
  epingler ni un ecran ni un compte.
- **Rien d'autre.** Les clauses A et B de s14 couvrent deja le reste, et je ne demande pas une
  troisieme clause pour une propriete deja gardee - ce serait decoratif.

**Preuve de morsure, sur du materiau reel et non sur une fixture ecrite pour la circonstance** : le
marqueur retire de `fragments/retour.html` doit faire rougir la clause D en nommant l'ecran de fin de
partie, et la clause C en nommant le gabarit. C'est une mutation d'un objet existant, elle ne demande
aucun fichier neuf.

**Effet attendu sur le compte** : deux clauses de propriete plus au moins une methode de morsure. La
decoupe en methodes est la sienne, mais **un compte qui n'augmenterait pas serait un refus** : une
clause livree sans sa preuve de morsure est ce qu'`ADR-0013` obligation 3 interdit - c'est l'erreur que
j'ai faite en comptant 506 au lieu de 507, et je ne la refais pas dans l'autre sens.

## 3. Ce qu'il n'y a pas a faire, verifie plutot que suppose

**`ADR-0017` et l'appariement de saillance : le garde-fou ne change pas.** C'est le resultat le plus
utile de cette section. `LActionNeDeclarePasMoinsQueLaSortieTest` derive les ecrans ou un habillage
permanent reste visible **depuis les masquages servis** (`ecransOuIlResteVisible`, `ADR-0022` point 5).
Retirer une regle de masquage et en ajouter deux recompose l'appariement **sans qu'une ligne de test
change** : la sortie d'en-tete redevient confrontee aux propositions de la manche - le couple que
`nadia-prof` avait fait supprimer le 2026-08-07 et qui revient par le geste inverse - et cesse de
l'etre sur les trois ecrans masques.

Le couple neuf passe, et je le verifie composante par composante contre la feuille plutot que de
l'esperer : `.lien-accueil` declare `font-size: 1.25rem`, `min-height: 2.75rem`, `padding: 0.6rem
1.1rem` ; `.proposition` declare `2rem`, `2.75rem`, `0.75rem 1.25rem`. Les quatre composantes comparees
sont inferieures ou egales sur la sortie. **Rien a corriger, rien a exempter.**

**`ADR-0022` point 3 n'est pas touche.** Toutes les sorties portent toujours "Les jeux" - celle de
l'en-tete, `.sortie-pleine`, `.retour-accueil` - donc
`leLienDeMenuEtLaSortieDeLaZoneDeContenuPortentLeMemeMot` reste vert et non vacuant. La clause du
libelle unique survit intacte a un renversement complet de l'arbitrage qui l'avait motivee : elle
portait sur le **mot**, pas sur l'ecran qui le porte, et c'est ce qui la rend robuste.

**`ADR-0038` : le chemin existant suffit, aucune route ni aucun controleur a toucher.** La sortie
d'en-tete declare `hx-post="/retour"`, et `PartieController.retour()` rend l'ecran de confirmation
quand une partie est en cours, sans jamais appeler `abandonner()`. L'arret n'est donc **pas
destructeur** au sens mesure d'`ADR-0038` point 3. Ce que les quatre clauses voient une fois la sortie
demasquee sur la manche :

- **clause 1** : `A[0]` d'un ecran de manche cesse d'etre une puce de niveau et devient la sortie
  d'en-tete - le `<nav class="menu">` precede `<nav class="bandeau-niveaux">` dans le document. Verte,
  la requete ne detruisant pas ;
- **clause 2** : le rebouclage depuis la derniere proposition en revelation atteint ce meme `A[0]`.
  Verte pour la meme raison. **C'est la position exacte du defaut que `nadia-prof` avait mesure en s10**
  - la sortie atteinte en une tabulation depuis la reponse revelee - et ce qui l'a ferme n'etait pas le
  masquage mais la confirmation. Le masquage ne faisait que le cacher ; il est retire sans le rouvrir,
  et c'est verifiable ;
- **clause 3** : inchangee, le point de focus d'une manche n'est jamais la sortie ;
- **clause 4** : inchangee, la manche declare toujours un point de focus.

Aucune route neuve, donc `chaqueRouteDuControleurEstExerceeParCeGardeFou` n'a rien a recevoir.

**Aucun amendement d'`ADR-0022` n'est necessaire pour ce changement de population, et c'est la mesure
de la reforme d'hier.** La population de masquage change pour la cinquieme fois ; c'est la premiere ou
elle coute **zero fichier de garde-fou et zero clause d'`ADR`** - trois regles CSS et deux attributs de
classe. La reforme annoncait cette mesure ; la voici prise sur le geste suivant.

## 4. Le releve exige par le correctif de methode de s14

**Methode**, publiee avec le resultat comme une mesure de rendu publie sa commande :
`grep -rn "retour-accueil" --include=*.java --include=*.html --include=*.css .` hors `target/` et hors
`squad/`, sur l'arbre `739a161`. L'element retire est le bouton `.retour-accueil` **de
`fragments/manche.html`** ; la classe elle-meme survit, la confirmation la portant toujours. Le releve
distingue donc ce qui perd son sujet de ce qui n'en perd pas.

**Perdent leur sujet - deux assertions, dans la meme classe, et c'est tout :**

| Source | Ce qui tombe |
|---|---|
| `PartieControllerTest.leBoutonDeRetourAlAccueilEstVisibleSurLaManche` (l. 196) | `select("button.retour-accueil")).isNotEmpty()` sur la manche : faux des le retrait |
| `PartieControllerTest.leBoutonDeRetourAlAccueilEstVisibleApresUneReponse` (l. 209) | la meme assertion apres une reponse |

Les deux se **retournent** plutot que de disparaitre - la manche ne porte plus de sortie dans sa zone,
la sortie vit dans l'en-tete permanent - donc leur nom change et le compte ne bouge pas de ce fait.
`ADR-0013` exige le motif ecrit du remplacement ; le voici pour les deux : leur sujet a change d'ecran
par l'arbitrage de l'utilisatrice du 2026-08-18, la propriete "l'enfant peut sortir depuis une manche"
n'est pas affaiblie mais deplacee, et c'est desormais le masquage - donc les clauses A, C et D - qui la
garde.

**Ne perdent pas leur sujet, et pourquoi :**

| Source | Motif |
|---|---|
| `PartieControllerTest.lEcranDeFinDePartieRendLaSortieCommeUnBoutonPlein` (l. 305) | son `isEmpty()` porte sur la fin de partie, qui rend `.sortie-pleine` et n'a jamais rendu `.retour-accueil`. Inchange |
| `AucunArretDeTabulationNEstDestructeurTest.laFormeDeLaProductionAvantCeLotFaitEchouerLaClause1` | lit le document **et** la feuille figes du fixture `manche-revelation-avant-correctif`. **C'est le geste 4 de s14 qui paie ici** : sans le decouplage d'hier, cette preuve rougirait aujourd'hui pour un defaut qui n'est pas le sien |
| `LActionNeDeclarePasMoinsQueLaSortieTest.leRejeuDuRefusDeSDeuxFaitEchouerLeGardeFou` | rejoue un depot fictif fige, gabarits compris |
| `HabillageDesEcransTest` (`SELECTEURS_HABILLES_PAR_S2_2`, et l'existence de `.retour-accueil` dans la feuille) | la classe reste posee par `fragments/confirmation.html` et reste declaree par la feuille |
| `AucunEtatDePressableNEstAbandonneAuTiersTest`, `LHabillageResteImmobileTest` | mentions en Javadoc et citation du ternaire de `retour.html` ; aucune assertion sur la manche |

**Un point a ne pas "corriger" par zele** : quatre jeux fautifs de
`AucunTexteNeVitDansUnSousArbreMasqueTest` embarquent une copie de `fragments/manche.html` qui garde son
`.retour-accueil`. Ils vont donc **diverger** de la production, et c'est correct : ce sont des fixtures
pour une autre propriete, figees a dessein. Les resynchroniser sur la production serait leur faire dire
autre chose que ce qu'elles prouvent.

**Ce que le releve n'attrape pas, et je le dis parce que c'est la moitie que j'avais manquee en s14** :
un garde-fou dont la **derivation** lit une source dont l'element retire est la derniere instance. Ici
il n'y en a aucun - la classe survit sur la confirmation - mais la commande ne pouvait pas l'etablir,
c'est une lecture. Le releve couvre les litteraux ; le reste reste une relecture, et je ne pretends pas
qu'une commande la remplace.

## 5. BARRE-3 : ce que l'arbitrage annule, et ce qui reste

**Le compte est annule mecaniquement, et pas par une seule decision mais par deux.** BARRE-3 exigeait
au plus une porte lue avant l'enonce sur un ecran de manche. Avec la sortie dans l'en-tete, l'enfant
rencontre trois elements actionnables avant l'enonce : "Les jeux", puis les deux puces. Les puces y
etaient deja par l'arbitrage de s14, que `nadia-prof` a rendu elle-meme ; la sortie s'y ajoute par
l'arbitrage de l'utilisatrice. **Aucune des deux n'est reinstruisible ici, et leur conjonction rend
BARRE-3 intenable sous sa forme chiffree.** Je le constate, je ne l'arbitre pas.

**Ce qui n'est pas annule, parce que ce n'etait pas le compte** : le constat que `nadia-prof` a fait le
2026-08-07 et redit le 2026-08-14 porte sur **ou l'attention de l'enfant est dirigee**, et cela ne se
mesure pas en nombre de portes. Le depot a deja un mecanisme pour cela, et il est executable : le
serveur declare un point de focus (`data-focus`, `data-focus-cible`), et la clause 4 d'`ADR-0040` exige
qu'un ecran a partie en cours en declare toujours un. **Sur une manche fraiche, ce point est l'enonce**
(`PartieController`, `FOCUS_ENONCE` par defaut).

**Une clause derivee reste disponible si `nadia-prof` la veut**, et je la nomme sans la demander -
c'est un jugement pedagogique, pas une exigence d'architecture : *sur une manche qui ne signale ni
echec ni revelation, le point de focus declare est l'enonce*. Elle est encodable en une methode, sur
le corpus d'ecrans assembles qui existe deja, et elle garderait la substance de BARRE-3 sans compter
aucune porte. Ce que je ne ferai pas : la presenter comme un equivalent du critere qu'elle remplace.
Un ordre de lecture et un point de focus ne sont pas la meme chose, et c'est `nadia-prof` qui dit si
le second suffit.
