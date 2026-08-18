# Index des ADR

Une ligne par decision d'architecture. Numeros jamais reutilises.

| Reference | Titre | Sprint | Date | Statut |
|---|---|---|---|---|
| [ADR-0001](ADR-0001-decoupage-en-modules-maven.md) | Decouper le build en cinq modules Maven et orienter les dependances vers l'interieur | s00 | 2026-08-04 | Accepte, amende le 2026-08-14 (s12) : le declencheur de `adapter-persistence` est ferme par `DEC-0028` |
| [ADR-0002](ADR-0002-purete-du-domaine-et-de-application.md) | Interdire toute dependance externe dans `domain` et dans `application` | s00 | 2026-08-04 | Accepte |
| [ADR-0003](ADR-0003-prouver-que-chaque-regle-archunit-mord.md) | Prouver que chaque regle ArchUnit mord, par un contre-exemple qui doit la faire echouer | s00 | 2026-08-04 | Accepte |
| [ADR-0004](ADR-0004-decoupler-la-mecanique-du-contenu.md) | Decoupler la mecanique du contenu par deux contrats fermes et une regle bidirectionnelle | s00 | 2026-08-04 | Accepte |
| [ADR-0005](ADR-0005-abandonner-ruleset.md) | Abandonner `RuleSet` et interdire les abstractions fantomes | s00 | 2026-08-04 | Accepte |
| [ADR-0006](ADR-0006-la-difficulte-est-portee-par-le-paquet-de-contenu.md) | Supprimer la configuration de difficulte du domaine et la porter par le paquet de contenu | s00 | 2026-08-04 | Accepte |
| [ADR-0007](ADR-0007-un-seul-chargeur-de-contenu-pilote-par-un-schema.md) | Charger tout le contenu avec un seul chargeur pilote par un schema | s00 | 2026-08-04 | Accepte |
| [ADR-0008](ADR-0008-le-comportement-de-jeu-vit-sur-le-serveur.md) | Loger tout le comportement de jeu sur le serveur et brider le JavaScript par un budget | s00 | 2026-08-04 | Accepte |
| [ADR-0009](ADR-0009-les-garde-fous-de-ci-sont-des-scripts-testes.md) | Extraire les garde-fous de CI dans des scripts versionnes, et leur donner des contre-exemples | s00 | 2026-08-04 | Accepte |
| [ADR-0010](ADR-0010-refuser-les-chemins-de-poste-de-travail.md) | Refuser les chemins de poste de travail et les comptes utilisateur dans un fichier suivi, par un script teste | s00 | 2026-08-05 | Accepte |
| [ADR-0011](ADR-0011-rendre-toute-erreur-imprevue-en-html-francais.md) | Rendre toute erreur imprevue en HTML francais, jamais un corps technique brut | s01 | 2026-08-05 | Accepte, complete par ADR-0018 |
| [ADR-0012](ADR-0012-la-longueur-de-partie-vit-dans-le-catalogue.md) | Porter la longueur d'une partie par l'entree de catalogue, jamais par le contenu | s01 | 2026-08-05 | Accepte |
| [ADR-0013](ADR-0013-un-garde-fou-derive-son-perimetre-et-se-prouve-mordant.md) | Deriver le perimetre de tout garde-fou de la source, et le prouver mordant sur un objet neuf | s02 | 2026-08-05 | Accepte |
| [ADR-0014](ADR-0014-vendoriser-une-bibliotheque-css-tierce-et-deriver-les-garde-fous-du-rendu.md) | Faire entrer une bibliotheque CSS tierce par vendorisation tracee, et faire deriver un garde-fou pedagogique du rendu plutot que du fichier | s03 | 2026-08-06 | Accepte, amende trois fois : deux limites nommees fermees le 2026-08-06 (`ADR-0015`) et le 2026-08-14 (`ADR-0039`), et **la troisieme le 2026-08-17 (s15, `ADR-0046`)** : son point 5 n'est plus une garantie mais une condition partielle, une pseudo-classe tierce suffisant a emporter la specificite |
| [ADR-0015](ADR-0015-deriver-le-perimetre-de-la-source-qui-gouverne-le-rendu.md) | Deriver le perimetre d'un garde-fou de la source qui gouverne le rendu, et supprimer celui dont le nom promet plus que son perimetre | s03 | 2026-08-06 | Accepte |
| [ADR-0016](ADR-0016-le-contenu-porte-l-aide-que-la-mecanique-promet.md) | Faire porter par le contenu l'aide que la mecanique promet, et deriver le perimetre du garde-fou du repertoire de contenu | s05 | 2026-08-06 | Accepte, amende le 2026-08-07 |
| [ADR-0017](ADR-0017-comparer-deux-boutons-dun-meme-ecran.md) | Comparer deux boutons d'un meme ecran, et dire ce que cette comparaison ne voit pas | s05 | 2026-08-06 | Accepte, amende deux fois le 2026-08-07 (second amendement par ADR-0022) |
| [ADR-0018](ADR-0018-un-defaut-ne-traverse-pas-le-serveur-en-silence.md) | Empecher qu'un defaut traverse le serveur en silence, et qu'il emprunte le canal reserve a l'attendu | s05 | 2026-08-06 | Accepte |
| [ADR-0019](ADR-0019-un-cycle-lire-calculer-ecrire-sur-la-session-est-indivisible.md) | Rendre indivisible tout cycle lire-calculer-ecrire sur l'etat de partie, et cesser de demander un coup a une partie terminee | s05 | 2026-08-07 | Accepte |
| [ADR-0020](ADR-0020-l-unite-de-verification-d-un-ecran-est-le-document-assemble.md) | Juger un ecran sur le document assemble apres l'echange htmx, et non sur le fragment ni sur la page entiere | s06 | 2026-08-07 | Accepte |
| [ADR-0021](ADR-0021-un-contre-exemple-de-garde-fou-n-est-jamais-un-bean-de-l-application.md) | Interdire a un contre-exemple de garde-fou de porter un stereotype Spring, et verifier qu'aucun n'est un bean du contexte assemble | s06 | 2026-08-07 | Accepte |
| [ADR-0022](ADR-0022-un-habillage-permanent-declare-son-masquage-et-l-ecran-son-marqueur.md) | Rendre la visibilite d'un habillage permanent dependante d'un marqueur d'etat pose par le serveur, et retirer d'un appariement les boutons qui ne se montrent plus ensemble | s06 | 2026-08-07 | Accepte, son contexte amende le 2026-08-14 (chiffre perime, decision inchangee) ; amende **trois fois** le 2026-08-17 (s14) : ce que `S14-1` fait tomber - rien de cet ADR - puis, apres `S14-2`, le point 2 perd toute instance en production et le point 1 perd le garde-fou qui l'exigeait (constat), puis, apres le refus pedagogique du soir, **la clause 2 change de forme** - un marqueur quelconque, un sous-ensemble d'ecrans, et plus aucune exigence que le mecanisme soit employe ; reforme **verifiee par mutation** le 2026-08-17 (s15) sur le retour de `.lien-accueil` : zero fichier de garde-fou de cet ADR ; **note du 2026-08-18** (s15) : premisse renversee par l'utilisatrice, la sortie vit desormais dans l'en-tete pendant une partie, cinquieme bascule de population, cout zero clause et zero garde-fou |
| [ADR-0023](ADR-0023-un-paquet-offre-strictement-plus-d-items-que-sa-partie-n-a-de-manches.md) | Exiger de tout jeu du catalogue qu'il offre strictement plus d'items que sa partie n'a de manches, et deriver ce controle du catalogue | s06 | 2026-08-07 | Accepte |
| [ADR-0024](ADR-0024-le-verdict-porte-l-explication-de-l-item-joue-la-graphie-reste-au-paquet.md) | Faire porter l'explication d'un item par le verdict de la manche jouee, et laisser la graphie qui la justifie dans le paquet qui la declare | s06 | 2026-08-07 | Accepte |
| [ADR-0025](ADR-0025-une-mesure-de-rendu-porte-sa-commande-le-debordement-reste-non-gardable.md) | Rendre toute mesure de rendu rejouable par une commande versionnee, et declarer non gardable le debordement de mise en page | s06 | 2026-08-10 | Accepte |
| [ADR-0026](ADR-0026-une-animation-retarde-mais-ne-determine-rien-le-levier-n-est-pas-un-bouton-de-plus.md) | Autoriser une animation qui retarde et interdire une animation qui determine ; faire du levier l'apparence du bouton qui ouvre deja une manche | s07 | 2026-08-10 | Accepte |
| [ADR-0027](ADR-0027-un-ecran-de-jeu-declare-comment-il-est-arrive-le-mouvement-suit-le-geste.md) | Faire declarer par le serveur comment un ecran de jeu est arrive, et n'autoriser un mouvement que sur un geste de l'enfant | s07 | 2026-08-10 | Accepte, sa clause 2 amendee le 2026-08-13 (s10) : un geste ne suffit plus, il doit avoir renouvele le tirage |
| [ADR-0028](ADR-0028-outiller-le-pli-le-contenu-au-dessus-du-pli-reste-non-gardable.md) | Outiller la mesure du pli par un parametre de hauteur, et declarer non gardable le contenu au-dessus du pli | s08 | 2026-08-11 | Accepte |
| [ADR-0029](ADR-0029-toute-colonne-que-le-contenu-peut-produire-porte-son-decalage.md) | Faire porter un decalage declare a toute colonne que le contenu peut produire, et laisser la borne de duree a la clause qui la garde deja | s08 | 2026-08-11 | Accepte |
| [ADR-0030](ADR-0030-un-habillage-entoure-sans-rogner-et-toute-animation-finit-sur-son-etat-de-repos.md) | Interdire a un habillage de rogner un ancetre de bouton et d'animer autre chose que ce qu'on presse, et exiger que la derniere image d'une animation soit l'etat de repos | s08 | 2026-08-11 | Accepte, un de ses refus renverse par ADR-0033 |
| [ADR-0031](ADR-0031-un-ecran-ne-gouverne-jamais-l-apparence-d-un-autre.md) | Refuser tout selecteur maison qui reunit des jetons de deux ecrans que le serveur ne rend jamais ensemble, et fermer ainsi le canal de feuille de la condition C4 | s08 | 2026-08-11 | Accepte |
| [ADR-0032](ADR-0032-deux-items-d-un-meme-paquet-ne-presentent-jamais-le-meme-ecran.md) | Refuser que deux items d'un meme paquet fini presentent le meme ecran, et fermer ainsi le trou de morsure d'ADR-0023 | s09 | 2026-08-12 | Accepte |
| [ADR-0033](ADR-0033-un-habillage-ne-change-pas-d-etat-sous-le-doigt-de-l-enfant.md) | Interdire a tout habillage de declarer une transition ou une animation et d'etre conditionne par une pseudo-classe interactive, et renverser le refus d'ADR-0030 sur la moitie non animee de C3 | s10 | 2026-08-12 | Accepte |
| [ADR-0034](ADR-0034-un-corpus-d-ecrans-exerce-les-deux-verdicts.md) | Exiger que tout corpus d'ecrans exerce les deux verdicts, et lire leurs marqueurs dans le gabarit plutot que dans le test | s10 | 2026-08-12 | Accepte |
| [ADR-0035](ADR-0035-aucun-texte-rendu-ne-vit-dans-un-sous-arbre-masque.md) | Refuser tout texte rendu a l'interieur d'un sous-arbre `aria-hidden`, et accrocher le masque au decor plutot qu'au conteneur | s10 | 2026-08-12 | Accepte |
| [ADR-0036](ADR-0036-une-image-maison-entre-par-son-chemin-un-garde-fou-ne-lit-pas-un-binaire-comme-du-texte.md) | Faire entrer une image maison par son chemin litteral et non par une empreinte, deriver le perimetre d'une reference de ressource de l'attribut, et retirer les binaires du garde-fou de chemins | s10 | 2026-08-13 | Accepte |
| [ADR-0037](ADR-0037-une-dependance-de-test-n-est-pas-une-ressource-servie.md) | Distinguer une dependance de test d'une ressource servie, et refuser tout octet servi qui n'est pas vendorise | s10 | 2026-08-14 | Accepte |
| [ADR-0038](ADR-0038-aucun-arret-de-tabulation-destructeur-a-une-tabulation-d-un-point-de-depart.md) | Interdire tout arret de tabulation destructeur a une seule tabulation d'un point de depart que le document autorise | s10 | 2026-08-14 | Accepte, amende le 2026-08-14 (s11) ; sa regle est portee par le lot de `marc-dev` |
| [ADR-0039](ADR-0039-un-critere-se-derive-des-que-la-promesse-est-universelle.md) | Deriver le critere d'un garde-fou des que sa promesse est universelle, et faire passer toute enumeration du cote autorise | s12 | 2026-08-14 | Accepte, amende le 2026-08-17 (s14, `ADR-0045`) : sa clause 1 ne disait pas de quelle source le calcul a le droit de tirer ; non-preuve 9 ajoutee |
| [ADR-0040](ADR-0040-un-point-de-focus-declare-n-est-jamais-destructeur-l-ecran-de-confirmation-n-est-pas-exempte.md) | Interdire qu'un point de focus declare soit lui-meme destructeur, et refuser d'exempter l'ecran de confirmation de la clause 2 d'ADR-0038 | s12 | 2026-08-14 | Accepte |
| [ADR-0041](ADR-0041-la-valeur-d-une-proposition-est-derivee-de-ce-qu-elle-ecrit.md) | Deriver la valeur comparable d'une proposition de ce qu'elle ecrit, et refuser qu'un garde-fou juge un paquet sur une partie de ses items | s13 | 2026-08-15 | Accepte, amende le 2026-08-15 (s13) : la lecture d'une somme est ratifiee (cas 2 de la decision) et la clause 5 passe de l'unite au reste de lecture ; deux notes datees du 2026-08-16 (repartition de `cm1-grands-nombres` corrigee, non-preuve 3 mesuree) |
| [ADR-0042](ADR-0042-un-arret-remplacant-est-un-second-predicat-orthogonal-du-destructeur.md) | Ajouter un second predicat orthogonal du destructeur, "remplacant", et en tirer la contrainte de forme de l'ecran de S13-6 | s13 | 2026-08-15 | Accepte, **sa regle n'a jamais ete ecrite et son porteur a disparu** : amende le 2026-08-17 (s14), dette explicite ouverte avec son declencheur ; chiffre corrige le 2026-08-15 (sept jeux au catalogue, il y en a onze) |
| [ADR-0043](ADR-0043-un-point-d-entree-permanent-porte-un-troisieme-role-et-le-mot-unique-reste-aux-sorties.md) | Ouvrir un troisieme role de bouton pour un point d'entree permanent, l'admettre du cote domine de la saillance, et laisser le mot unique aux seules sorties | s14 | 2026-08-17 | Accepte, sa regle ecrite dans le meme commit (`LActionNeDeclarePasMoinsQueLaSortieTest`, deux jeux fautifs neufs) |
| [ADR-0044](ADR-0044-le-choix-des-jeux-montres-vit-derriere-le-port-le-point-d-entree-est-une-route.md) | Loger le choix des jeux montres derriere le port entrant, faire du point d'entree une route et non un parametre, et refuser tout etat de filtre dans l'en-tete permanent | s14 | 2026-08-17 | Accepte, ses deux regles ecrites dans le meme commit, dont la **29e regle ArchUnit** du depot |
| [ADR-0045](ADR-0045-un-critere-derive-de-l-artefact-juge-n-en-juge-que-la-coherence-interne.md) | Limiter un critere derive de l'artefact qu'il juge a la coherence interne de cet artefact, recolter les invariants internes du catalogue et router la justesse de l'association a la porte pedagogique | s14 | 2026-08-17 | Accepte, sa regle ecrite dans le meme commit (`AucuneEntreeDuCatalogueNEnDoubleUneAutreTest`, trois catalogues fautifs) ; pas une regle ArchUnit, le compte reste a 29 |
| [ADR-0046](ADR-0046-un-etat-interactif-tiers-oblige-la-feuille-maison-a-le-declarer.md) | Obliger la feuille maison a declarer tout etat interactif que la feuille tierce declare sur un pressable, et renverser la garantie de specificite d'ADR-0014 | s15 | 2026-08-17 | Accepte, sa regle ecrite dans le meme commit (`AucunEtatDePressableNEstAbandonneAuTiersTest`, sept cas, trois jeux fautifs) ; amende `ADR-0014` (point 5 et une limite nommee) ; pas une regle ArchUnit, le compte reste a 29 ; **dette de production de 32 exigences sous cliquet exact**, correctif du a un lot |

## Amendements

Un ADR accepte n'est pas fige : il est amende quand le depot montre qu'il dit faux, et l'amendement
est date dans le corps de l'ADR. Ceux du sprint 0 :

| ADR | Date | Ce que l'amendement corrige |
|---|---|---|
| ADR-0001 | 2026-08-04 | ses deux regles n'avaient pas de contre-exemple (comble par `yanis-qa`) ; piege de la couche declaree vide |
| ADR-0002 | 2026-08-04 | la purete de `application` n'avait pas de contre-exemple ; nuance sur les primitifs |
| ADR-0003 | 2026-08-04 | perimetre exact de l'exigence de contre-exemple, deux dispenses motivees, deux exceptions d'ecriture |
| ADR-0003 | 2026-08-04 | (premier amendement) `hasMessageContaining` sur la classe fautive, un `AssertionError` ne suffit pas |
| ADR-0004 | 2026-08-04 | trois de ses sept regles n'avaient pas de contre-exemple, dont l'isolation entre mecaniques |
| ADR-0005 | 2026-08-04 | correction de `itemExiste`, puis contre-exemples des quatre regles |
| ADR-0006 | 2026-08-04 | **sa regle centrale ne mordait pas** : primitifs vus dans `java.lang`, arguments generiques ignores. Regle reecrite |
| ADR-0007 | 2026-08-04 | sa regle ArchUnit n'avait pas de contre-exemple |
| ADR-0008 | 2026-08-04 | la regle d'assertion web n'avait pas de contre-exemple ; comment contre-exemplifier une regle qui juge du code de test |
| ADR-0009 | 2026-08-05 | la plage de commits ne resistait pas a une base reecrite et inatteignable apres un push force (`bad object`) ; ajout de `commit-atteignable.sh` et de son repli, avant la construction de la plage |
| ADR-0010 | 2026-08-05 | **le garde-fou a mordu son propre auteur** : verifie avant que `verifier-chemins.sh` soit suivi, il n'avait jamais ete juge par lui-meme ; une fois suivi, son propre commentaire d'en-tete contenait un vrai segment reel. Commentaire corrige, pas la regle |
| ADR-0008 | 2026-08-05 | ajout d'un renvoi vers ADR-0011 : le fragment d'erreur est du rendu serveur comme les autres, deja couvert par les regles existantes sans les modifier |
| ADR-0003 | 2026-08-05 | **son propre mecanisme enumerait son perimetre** : l'inventaire des regles contre-exemplifiees etait tenu a la main a chaque cloture, et `LesReglesMordentTest` avait vingt et une methodes ecrites une par une. Rien n'obligeait la vingt-quatrieme regle a recevoir son contre-exemple. Recensement rendu executable par ADR-0013 |
| ADR-0014 | 2026-08-06 | **sa garantie centrale ne mordait pas sur la vraie bibliotheque** : `declareEnImportant` cherchait `font-size:` par sous-chaine et tombait sur `--bs-btn-font-size:`, le motif de nommage de tous les composants Bootstrap 5. Trouve par `yanis-qa`, corrige par ADR-0015. Son jeu fautif ecrivait `font-size:1rem!important` en clair, ce que Bootstrap n'ecrit jamais |
| ADR-0014 | 2026-08-06 | **une de ses limites nommees ne gardait rien** : la puce "le garde-fou lit la classe statique du gabarit, pas la classe calculee" documentait un vert silencieux au lieu de le fermer. Mesure : retirer `.sortie-pleine` de `recreo.css` laissait la suite verte. Ferme par ADR-0015 |
| ADR-0011 | 2026-08-06 | **son point 3 etait faux, et il a cache un defaut de production** : il affirmait qu'une exception non nommee resterait "rare et visible". `org.apache.tomcat.util.http.InvalidParameterException` herite d'`IllegalStateException` (verifie par `javap`), etait donc attrapee par le gestionnaire de la session perdue, et journalisee nulle part - 21 lignes de journal, toutes de demarrage. Nommer deux types n'est pas nommer deux situations. Corrige par ADR-0018 ; son ecran et son statut 200 restent intacts |
| ADR-0013 | 2026-08-06 | **les donnees d'entree d'un test font partie de son perimetre**, cinquieme occurrence de la meme famille. `PartieHttpTest` joue une partie complete contre un catalogue ecrit a la main dont les propositions sont `"1"`, `"2"`, `"3"` ; `CatalogueChargeableIT` parcourt le vrai catalogue mais n'appelle jamais `jouer`. Le chemin etait couvert, les valeurs qui l'empruntent ne l'etaient pas. Obligation 1 etendue ; l'encodage de cette occurrence est le garde-fou de contenu de `lea-dev`, la generalisation reste une doctrine de revue et le dit |
| ADR-0016 | 2026-08-07 | **sa dette etait fermee, son plafond ne l'etait pas** : `DETTE_DE_CONTENU` etait deja `Set.of()` mais `PLAFOND_DE_DETTE` etait reste a 2, laissant deux places pour exempter en silence un paquet neuf sans indice. Le commentaire du champ appelait ce plafond "la trace de cette fermeture" ; c'etait faux. Plafond porte a 0. Ecart signale par le `relecteur-pedagogique`, pas trouve par moi |
| ADR-0017 | 2026-08-07 | **une de ses limites nommees s'est ouverte le sprint suivant** : son point 5 disait "un ecran est un fichier de gabarit" et "rien ne l'empeche demain". Demain, c'etait S6-1 - l'en-tete permanent pose un lien `data-role="sortie"` hors de la zone echangee par htmx, donc affiche par-dessus les boutons d'action de `fragments/`, sans qu'aucun couple ne les confronte. Un ecran est desormais la zone `hx-target` plus ce qui lui survit ; la cible est lue dans les gabarits, pas ecrite dans le test, et plusieurs cibles distinctes sont un echec. Verifie et pas suppose : en revenant a l'ancienne semantique, le jeu fautif redevient vert |
| ADR-0008 | 2026-08-10 | **sa liste de mots interdits couvrait le verdict et pas le tirage** : un rouleau ecrit en JavaScript n'a besoin d'aucun des onze mots refuses, il lui suffit de relire `.propositions` et de decider laquelle arrive dans quelle colonne. `proposition` et `colonne` rejoignent la liste (ADR-0026). Morsure mesuree par mutation reelle de `recreo.js` : la premiere ligne qu'ecrirait un rouleau client passait vert avant l'amendement |
| ADR-0017 | 2026-08-10 | pas une correction mais une **confirmation mesuree** : l'arrivee du levier et des colonnes ne l'oblige a rien. Le levier est `.demarrer` ou `.encore`, deja `data-role="action"` ; la proposition reste `.btn.proposition`. L'ensemble ferme `{action, sortie}` n'a pas a s'ouvrir et les trois epinglages de couples restent verts sans qu'une ligne change (ADR-0026, regle 4) |
| ADR-0026 | 2026-08-10 | **sa section 2 keyait le mouvement sur la mauvaise propriete** : "une animation demarre quand l'element entre dans le document" est exact, mais un element entre aussi dans le document quand le navigateur charge une page entiere, et `accueil.html` rend la manche en clair sur la branche de reprise de S1-4. Un enfant qui recharge sa page verrait les colonnes tourner sans avoir rien touche. Troisieme occurrence de ma propre famille de faute, apres ADR-0022 et la limite 5 d'ADR-0017 : une propriete structurelle prise pour la propriete qui compte. Trouve par `nadia-prof` en lisant les gabarits, cause et correction par ADR-0027 |
| ADR-0026 | 2026-08-10 | **deux de ses chiffres de rendu etaient lus dans les angles arrondis** : colonne annoncee a 99 px et ecart a 18 px, mesures sur une ligne prise a 6 px du bord haut. A mi-hauteur, la colonne fait **101 px** et l'ecart **16 px** exactement. Aucune conclusion ne bouge, mais le plancher d'ecart de S3-5 est tenu **a l'egalite exacte** : il n'y a aucune marge de 2 px a recuperer, ce que le chiffre annonce laissait croire. Trouve par `nadia-prof`, remesure par un critere different |
| ADR-0026 | 2026-08-10 | **sa clause "la duree n'est pas gardable" etait juste et a cesse de l'etre** : elle valait tant qu'aucun seuil n'existait. `nadia-prof` en donne trois (0,3 s a 1,2 s, decalage 0,25 s au plus), et la clause passe de son jugement a un automate. Ce qui lui reste est ce qu'une feuille de style ne dit pas : la courbe, le rebond, le lien percu avec le geste (ADR-0027 decision 4) |
| ADR-0027 | 2026-08-10 | **sa clause 6 ne voyait aucun des retards de production** : un decalage par colonne ne peut pas vivre dans un raccourci `animation` partage, il s'ecrit dans une regle par colonne en `animation-delay` - une propriete que la population des animations ne collectait pas. Mon jeu fautif `decalage-trop-grand` ecrivait le retard **dans le raccourci**, la forme que j'imaginais et non celle que le monde ecrit : troisieme occurrence de la lecon de s05 sur les fixtures de la main de l'auteur. Morsure mesuree sur la production de `lea-dev` : `animation-delay: 3s` passait vert sur 149 cas, rend maintenant deux defauts (le retard et la somme duree + retard) |
| ADR-0027 | 2026-08-10 | **son "jamais un autre mot" ne tenait pas la decoupe geometrique** : je l'annoncais gardee sur "les deux seules formes qu'un rouleau reel peut prendre", et S7-1 en a livre une troisieme en confinant le voile par `clip-path`. La meme propriete deplacee du pseudo-element vers le bouton retranche une partie du mot du serveur, et sur `cm1-homophones` transforme `sont` en `son`, c'est-a-dire la bonne reponse en la fausse. Un caractere separait la production du defaut et rien ne le tenait : vert sur 149 cas. Clause 8 ajoutee, avec le jeu fautif corrige qui prouve que la forme de S7-1 reste verte |
| ADR-0009 | 2026-08-12 | **"l'enveloppe est prouvee une fois a la main" laissait son cablage hors de portee, et c'est un defaut de cablage qui s'est produit** : `mesurer-rendu.sh` convertissait le conteneur et le profil, et passait le PNG de sortie brut - un chemin relatif rendait "aucune capture (code 0)" et n'ecrivait rien. Trouve par `nadia-prof`. Une enveloppe ne touche pas le poste d'un seul bloc : quand son collaborateur est designe par une variable d'environnement (`CHROME`), son cablage est fabricable et doit etre teste. Le faux navigateur reproduit le silence constate du vrai plutot que le comportement du shell, sans quoi il serait vert sur le script fautif. 23 a 36 cas, morsure mesuree sur la mutation exacte du defaut ; les neuf cas de fonction pure y restent verts |
| ADR-0025 | 2026-08-12 | **l'outil de mesure que toute la squad utilise avait un mode de defaillance silencieux** : chemin de PNG de sortie rendu absolu puis converti comme les deux autres chemins, dossier de sortie absent refuse explicitement. Aucune mesure deja citee dans le depot ne change ; geometrie remesuree a la main apres correction. Doctrine portee par l'amendement du meme jour a ADR-0009 |
| ADR-0034 | 2026-08-12 | **sa clause disait "les deux verdicts" et c'etait deja insuffisant, le jour meme** : le verdict n'a un ecran a lui que parce que l'ecran de retour existe. Des que la bonne reponse charge la question suivante, l'ecran d'une reponse fausse est un ecran de manche qui ne porte aucun des deux marqueurs, et la clause resterait **verte** sur les seules fins de partie. Le perimetre passe au **produit** verdict x statut de session, quatre couples totaux par construction. Morsure mesuree : le couple faux + TERMINEE n'avait **jamais** ete exerce, meme apres la correction du matin |
| ADR-0035 | 2026-08-12 | **il etait trop large d'une clause, et c'est `nadia-prof` qui le montre** (sa reserve H) : en interdisant tout texte masque, il rendait impossible le seul usage correct d'`aria-hidden` et forcait le decor dans l'oreille de l'enfant. Un decor textuel se declare desormais par `data-decor` **sur l'element du texte**, et la clause symetrique - tout decor declare vit sous un masque - empeche la reserve d'etre une porte. Plafond epingle a zero. Amende le jour meme plutot que reporte, parce que la cinquieme structure qu'elle **prefere** est le premier membre de cette population : l'usage n'etait pas anticipe, il etait demande |
| ADR-0012 | 2026-08-05 | **sa section executable nommait le mauvais paquet** : un contre-exemple range dans `domain.content.contreexemple` aurait ete invisible pour la regle qu'il doit faire echouer. Paquet corrige, contre-exemple et test de non-vacuite ecrits ; et la derniere ligne de son tableau de gardes de comportement, seule des quatre a etre restee sur le papier, est honoree dans `CatalogueChargeableIT` |
| ADR-0027 | 2026-08-13 | **sa clause 2 forcait le defaut qu'elle croyait interdire, et elle exigeait ce defaut sans exception** : "tout ecran de jeu echange par htmx declare `par-un-geste`" etait juste tant qu'un echange htmx renouvelait toujours la question. Depuis le cycle de manche de S10, trois etats rendent le meme tirage - echec avec des essais restants, entree en revelation, appui a cote apres la revelation - et les colonnes y rebalaient. `nadia-prof` le refuse (condition M10) sur un motif mesure : le mouvement dit que la machine distribue alors que rien n'a ete distribue, et le prix est jusqu'a 18 s d'attente pour l'enfant qui se trompe le plus. **Cause : je n'avais jamais ecrit le sujet de `data-arrivee`.** C'est le tirage que les colonnes montrent, pas la reponse HTTP - quatrieme occurrence de ma famille de faute, apres ADR-0022, la limite 5 d'ADR-0017 et la section 2 d'ADR-0026. Clause devenue un "si et seulement si", condition calculee dans le point de passage unique et non a la route fautive, donc aucune route exemptee. Morsure : la production d'avant l'amendement rend **2 echecs sur 10 cas** et la clause nomme les trois etats ; le troisieme n'etait pas dans le corpus et y entre. Le vert silencieux de la lecture - une marque d'essai introuvable annulerait tout l'amendement - est ferme par un jeu fautif que **mon propre garde-fou de balayage a exige avant la revue** |
| ADR-0014 | 2026-08-13 | **sa garantie hors ligne ne regardait aucun gabarit, depuis s06** : la branche gabarits selectionnait `link[th\:href]` et `script[th\:src]`, et sur la version de jsoup que ce build utilise reellement (1.23.1, verifiee par `dependency:build-classpath`) la forme echappee du deux-points ne selectionne **zero element**. Les cinq references de production etant toutes ecrites en Thymeleaf, aucune n'etait verifiee : ni distante, ni absente. Mesure sur `2d34a15` : l'icone d'en-tete pointee vers un CDN **et** la feuille vendorisee pointee vers un fichier inexistant laissent les 12 cas verts. Son jeu fautif `gabarit-sur-cdn` ecrivait un `href` en attribut simple, forme que la production n'ecrit nulle part - sixieme occurrence de la lecon de s05, et la premiere qui coute une garantie entiere et non une clause. Perimetre derive de l'attribut et selectionne par `hasAttr`, plus une clause anti-vacuite ancree sur le texte du gabarit (ADR-0036) |
| ADR-0010 | 2026-08-13 | **son garde-fou lisait les binaires comme du texte, et inventait des fautes** : la forme a lettre de lecteur tient en quatre octets, que les octets compresses d'une image contiennent tot ou tard. Mesure sur 21 PNG de 72 ko a 500 ko : **4 refuses**, sur un motif sans aucun rapport avec un chemin ; `favicon.ico` y echappait par sa seule petitesse. Le jour ou la premiere illustration entre, la CI avait environ une chance sur cinq de rougir a tort, et le tirage se refaisait a chaque image. `grep -I` retire les binaires par leur contenu et non par une liste d'extensions. Deuxieme fois que cet ADR mord son propre auteur : mon commentaire d'explication du correctif citait la sequence fautive en clair (ADR-0036) |
| ADR-0022 | 2026-08-14 | **son contexte citait un chiffre que sa propre decision avait perime, et la comparaison qu'il portait opposait deux ecrans differents.** Le "102 x 53 px" du lien d'en-tete avait ete mesure sur le libelle "Accueil", que le **point 3 de cet ADR** remplace par "Les jeux", plus large : remesure par `nadia-prof`, **109 x 53 px**. Le chiffre etait donc faux avant l'acceptation de l'ADR et a survecu un sprint et demi, dans trois documents. Au-dela du chiffre : la phrase opposait `.lien-accueil` a un aplat de 117 x 56 px qui est `.sortie-pleine`, rendu par le seul ecran de fin de partie - deux elements qui ne s'affichent jamais ensemble, alors que sur une manche les deux candidats a la sortie (`.lien-accueil` et `.retour-accueil`) ont **la meme boite par construction**, memes composantes declarees et meme libelle depuis S6-9. **Il n'y avait aucun ecart de taille a opposer** ; `nadia-prof` le corrige contre son propre argument de 2026-08-07. Verifie de mon cote par lecture des composantes de `recreo.css`, pas par capture : la hauteur est derivable (53,2 px, et 56 px pour l'aplat), la largeur absolue **ne l'est pas** - aucun fichier maison ne declare de `font-family`, elle vient de la police systeme via la bibliotheque vendorisee. Aucun point de la decision, aucun garde-fou et aucune consequence ne raisonnaient sur une taille : la correction s'arrete au contexte, et la suite reste verte a 415 tests. **Cause nommee : j'avais recopie un chiffre sans sa methode** - ni le libelle, ni l'ecran, ni sa date par rapport au renommage que le meme ADR decidait |
| ADR-0038 | 2026-08-14 | **sa clause de concordance interdisait le remede que sa propre decision benissait, et deux de ses chiffres decrivaient un defaut plus etroit que le reel.** Le point 3 exigeait qu'un arret destructeur porte `data-role="sortie"` **et reciproquement** ; depuis la confirmation avant abandon, "Les jeux" reste une sortie du point de vue de l'enfant et sa requete ne detruit plus rien - tenir la clause aurait demande de remettre la destruction dans une requete unique, c'est-a-dire de retablir le defaut. **Cause : j'avais confondu ce que `data-role` declare (une destination pour l'enfant, `ADR-0017` point 1) avec l'effet d'une requete sur la session.** La concordance etait un **moyen** de ne pas se laisser desamorcer par un renommage ; le rejeu reel de la requete par `marc-dev` ferme ce desamorcage sans lire l'attribut - mesure : `sortie` remplace par `action` sur le bouton qui detruit, les deux clauses restent vertes. Clause retiree, dette nommee (une requete destructrice **neuve** declaree `action` passerait verte ; declencheur d'encodage : la deuxieme requete destructrice de l'application). Chiffres corriges : le garde-fou casse sur du materiau reel rougit sur **cinq** ecrans en clause 1 et un en clause 2, la ou j'avais ecrit deux et `nadia-prof` un. Quatre des huit non-preuves corrigees, dont la 4 qui etait **fausse** (la souris sur "Les jeux" ne detruit plus rien non plus). Morsure de la clause 1 apres le remede, mesuree et non supposee : les deux boutons de l'ecran de confirmation echanges de place, elle rougit |
| ADR-0013 | 2026-08-14 | **la case "critere enumere" de son tableau etait trop indulgente, et la phrase que son obligation 1 en tirait etait fausse** : une liste de fautes a reprocher n'a pas "pour mode de defaillance un rouge, pas un vert" des que la promesse du garde-fou est universelle. Mesure : `RessourcesTiercesTest` promettait qu'aucun JavaScript de bibliotheque d'interface n'entre, refusait un seul nom, et un `alpine.min.js` reel correctement trace est entre en laissant ses **seize tests verts**. Le vert silencieux etait bien la, du cote critere - la case que le tableau reservait au perimetre. Ce qui separe les cas est la **polarite** de la liste (refus = vert par defaut, autorisation = rouge par defaut), pas la nature de ses membres. Clause ajoutee et recensement executable portes par ADR-0039 ; les trois obligations sont inchangees |
| ADR-0014 | 2026-08-14 | **une de ses limites nommees ne gardait rien, et sa consolation etait fausse** (deuxieme fois pour cet ADR, apres la classe calculee de s03). La puce sur `JAVASCRIPT_TIERS_REFUSE` disait qu'un fichier renomme "echapperait au motif" mais pas au reste, "puisqu'il faudrait le vendoriser, donc le montrer en diff". Il echappait au garde-fou **entier**, et un diff est une relecture, c'est-a-dire un souhait selon l'axiome du depot. Critere remplace par une liste d'**autorisation** ou une entree designe une origine declaree et une empreinte, jamais un nom de fichier (ADR-0039) |
| ADR-0001 | 2026-08-14 | **son declencheur de creation d'`adapter-persistence` est ferme** : l'utilisatrice tranche le 2026-08-14 ("je ne souhaite pas persister une partie, ce n'est pas le but de recreo"), `camille-po` l'acte en `DEC-0028`. Le premier point de "Ce qui me ferait changer d'avis" - une story qui demande de conserver la progression d'un enfant entre deux sessions - ne peut plus se produire. **La decision du sprint 0 est inchangee**, et une phrase de l'ADR reste vivante a dessein : le point 2 de sa decision parle de "la premiere story qui a besoin **d'ecrire quelque part**", plus large que conserver une partie, donc non couvert par la parole de l'utilisatrice - l'etendre serait la lui faire dire. Rien a encoder, et c'est ecrit : `layeredArchitecture` ne peut pas interdire la creation d'un module, seulement gouverner les couches declarees. Ce qui garde cet abandon est la revue et `DEC-0028` |
| ADR-0038 | 2026-08-14 | **ses deux clauses jugeaient l'arret qu'une tabulation atteint, jamais le point de depart lui-meme** : un point de focus declare **sur** un arret destructeur les laissait toutes les deux vertes, alors qu'Entree seule y detruit la partie, sans aucune tabulation. Mesure sur du materiau reel : `data-focus-cible="true"` pose sur le bouton destructeur de `fragments/confirmation.html` laisse les six tests du garde-fou **et les 222 du module** verts. Le trou n'avait aucun objet avant l'ecran de confirmation - aucun point de focus ne pouvait etre destructeur - et il en a un depuis s11. Ferme par la clause 3 d'`ADR-0040`, encodee dans le meme fichier de test. Deux precisions dans le meme geste : `recreo.js` pose `tabindex="-1"` **sur le point de focus**, donc le document servi et le document vivant n'ont pas le meme `A[0]` des que ce point est lui-meme un arret ; et la clause 2 n'est **ni amendee ni exemptee** pour l'ecran de confirmation, son rouge y etant vrai |
| ADR-0014 | 2026-08-14 | **son point 1 parlait de dependances, pas d'octets servis, et son refus du webjar n'etait ecrit que dans ses alternatives** - donc rien ne l'appliquait. Pris a la lettre, ce point se lisait comme s'il gouvernait toute dependance Maven, y compris un outil de test que personne ne sert : c'est ce qui a rendu l'arbitrage de `nashorn-core` impossible a ranger au sprint 10. Perimetre dit (l'octet servi), et refus du webjar desormais encode par `ADR-0037` sur le livrable produit - `RessourcesTiercesTest` ne pouvait pas le voir, son perimetre descendant de l'arbre `static/` d'un module ou un webjar n'atterrit jamais |
| ADR-0013 | 2026-08-15 | **il adossait une clause en vigueur a un document qui n'a pas existe pendant cinq sprints, et il faut le dire au lieu de le republier une cinquieme fois.** La phrase "le plafond descendra parce que chaque cloture republie l'inventaire dans `confirmation-archunit.md`" nommait comme mecanisme de descente un geste humain. Fait, avec sa methode : `git ls-files 'squad/sprints/*/confirmation-archunit.md'` rend **huit** fichiers sur treize sprints clos ; manquent s04, puis s09 a s12, quatre d'affilee. C'etait donc un souhait au sens exact de l'axiome du depot. **Substitution executable, une ligne** : `hasSizeLessThanOrEqualTo(PLAFOND)` devient `hasSize(PLAFOND)` - un plafond qui majore la dette est une **place libre**, et une place libre laisse entrer en silence le garde-fou non eprouve suivant, ce qui fait de l'exemption le perimetre enumere deguise que l'ADR affirme qu'elle n'est pas. Meme geste sur `PLAFOND_DE_CRITERES_ENUMERES` (ADR-0039). Morsure mesuree sur le depot reel : plafond porte de 2 a 3 avec la dette laissee a 2 - l'etat exact qu'autorisait l'assertion precedente - **1 echec sur 10**, `laDetteNePeutNiGrandirNiPourrir`. **Ce qui n'est toujours pas garanti est dit** : aucune assertion ne peut exiger qu'une dette soit payee ; la descente reste un choix, elle n'est plus adossee a un document. Le fichier est republie le meme jour, non pour tenir la clause mais parce qu'il porte le seul inventaire des cinq cliquets du depot - etat mesure : jeu nul sur les cinq. Aucun test ajoute, le compte reste a **28 regles** et **464 tests** |
| ADR-0041 | 2026-08-15 | **sa decision enumerait deux cas de lecture la ou le depot en ecrivait trois** : `lea-dev` avait ajoute la lecture du total d'une somme (`40 000 + 700 + 5` vaut 40 705) en refusant explicitement d'en decider la doctrine a ma place. **Ratifiee**, cas 2 de la decision, au meme rang que la duree. Le motif est mesure, pas esthetique : contenu reel de `marc-dev` (`066f857`) depose en production, `mvn -B -pl adapters/adapter-content -am test`, la lecture desactivee a la main dans `valeurDe` - `cm1-grands-nombres` tombe de **14 items juges a 7**, ses sept items "decomposer" se lisant tous trois a la meme valeur. Refuser aurait donc rendu invisible au garde-fou de rang la forme de distracteur que `nadia-prof` **exige**, ce qui est le fait 1 de cet ADR une troisieme fois. Sa mesure a elle est verifiee et non crue : sonde jetable sur les douze paquets de production, executee avec puis sans la lecture, **douze lignes identiques au chiffre pres**. Trois non-preuves neuves (8 a 10) : le seul operateur `+`, la somme mal formee sans valeur, la decomposition non canonique - cette derniere restant du ressort de `nadia-prof`. Aucun test ajoute, le compte reste a **28 regles** et **486 tests** |
| ADR-0041 | 2026-08-15 | **sa clause 5 exigeait l'identite d'un texte la ou elle voulait dire l'identite d'une unite**, et elle etait verte par coincidence. Elle comparait ce qui suit le premier nombre, lu par un motif ecrit **a cote** de la lecture de valeur - le montage que la meme page interdit explicitement quatre paragraphes plus haut : *"deux lectures qui derivent l'une de l'autre finissent par diverger"*. Elles ont diverge des que la lecture a su lire une somme : sur `600 000 + 40` la lecture consomme tout, le motif d'a cote rendait `+ 40`. Tant que les distracteurs de decomposition ne variaient que leur premier terme le residu etait identique **par hasard** ; le contenu corrige de `marc-dev` fait varier un terme suivant et la clause a declenche un **faux positif d'unite sur un paquet qui n'ecrit aucune unite** - **1 echec sur 7**, les sept items "decomposer" nommes, seul rouge restant de son lot. Trouve par `lea-dev` en verifiant, et laisse intact parce que c'etait ma clause. **Correction** : la lecture rend desormais, avec la valeur, **ce qu'elle n'a pas consomme** ; la clause exige que les trois propositions laissent le **meme reste**. `kg` face a `g` rougit toujours, l'unite n'est toujours ni lue ni convertie, et rien n'est enumere - le reste est derive de la lecture, l'ensemble des restes reels est mesure sur le corpus (`ADR-0039`). Le mot "unite" quitte le nom de la clause : un reste non vide dit "la lecture n'a pas tout lu", ce qui est plus large. Quatre morsures sur du materiau reel, dans les deux sens : M3 rejouee sur `cm1-mesures` (`"4000 cm"` en `"40 m"`) rougit avec le message, la lecture mutee a reste toujours vide rougit **2 fois sur 8**, le contenu de `marc-dev` est **vert** avec la clause amendee et **rouge** avec l'ancienne. La fixture de ma main est doublee d'une **mutation derivee de chaque item reel du domaine**. Non-preuves neuves 11 a 14, dont la plus couteuse : **plus la lecture avale, moins la clause peut parler** - tout cas ajoute a `lireValeur` devra mesurer ce que la clause cesse de voir. Un test ajoute, **28 regles** et **487 tests** |

| ADR-0042 | 2026-08-15 | **un chiffre faux, aucune conclusion touchee, et c'est la seule raison de l'ecrire.** L'ADR annoncait "sept jeux" au catalogue reel a trois endroits ; il y en a **onze**. Methode : entrees `- id:` sous la cle `jeux:` de `adapters/adapter-content/src/main/resources/catalogue.yaml`, comptees sur l'arbre du commit cite - 7 en `a6f2275` (s10), 8 en `4f0290d`, 9 en `d9e390c` (s12), 11 depuis `ab1d11e` (s13), donc 11 le jour ou l'ADR a ete ecrit. "Sept" etait la taille du catalogue en s10, reportee de sprint en sprint sans remesure. Ecart signale par `ines-design`. Aucune clause, aucune mutation, aucune alternative n'en depend : les clauses portent sur des positions dans l'ordre du document, jamais sur un effectif, et M1 a M5 sont mesurees sur des catalogues de test a quatre boutons. Ce qui etait faux est la **taille du defaut rapporte** - onze boutons remplacants annonces comme sept - pas ce qui en est conclu. Le troisieme emploi, dans la liste de ce que la contrainte laisse libre, **perd son chiffre** au lieu d'en recevoir un juste : il aurait pourri au jeu suivant. Aucun test ajoute, le compte reste a **28 regles** et **487 tests** |

| ADR-0041 | 2026-08-16 | **une de ses deux lignes de mesure decrivait un contenu que la mesure n'avait pas lu, et sa non-preuve 3 etait fausse le jour ou elle a ete ecrite.** Ecart signale par `marc-dev`, qui mesurait 3/6/5 la ou l'ADR ecrivait 5/4/5. **Son chiffre tient** : sur l'arbre fusionne `4000bab`, JDK 21.0.10, sonde jetable non commitee appelant le chargeur reel et `valeurDe`, `cm1-grands-nombres` rend **14 items juges, 3/6/5, max 42,9 %**, et la meme execution reproduit les deux autres chiffres de l'ADR (`cm1-mesures` 13/4/7, `cp-additions-jusqu-a-10` 38,5 %). Cause : le 5/4/5 est la repartition du contenu **d'avant** la reprise de `marc-dev`, celui que `nadia-prof` a REFUSE - reproduit a l'identique en remettant le fichier de `c6b7745` en production. Le contenu de `066f857` que la ligne nomme n'avait pas ete depose. Les deux lignes du tableau portent donc sur deux contenus differents, ce qui etait invisible parce que sur le contenu refuse la lecture de somme ne change **rien** (14 items dans les deux sens) ; sur l'arbre fusionne le contraste reel est **14 (3/6/5) contre 7 (2/3/2)**, et la ratification est inchangee. Aucun garde-fou touche : 42,9 % reste sous le seuil de 50 %. **Lecon neuve** : les trois familles precedentes portaient sur le code mesure, celle-ci sur le **contenu** mesure - deposer un fichier est un geste sans trace, une mesure cite l'arbre qu'elle a lu, pas un fichier recopie depuis une autre branche. **Non-preuve 3** ("une duree et un nombre nu passent") : mesuree au lieu d'etre deduite, l'ancienne clause rougissait sur `["1 h 30", "90", "45 min"]`, la clause amendee non - la phrase etait donc fausse en `780e638` et l'amendement l'a rendue vraie. Symetrique cherche et borne : les deux clauses evaluees cote a cote sur tout le contenu chargeable du module, **32 divergences, toutes "ancienne rouge, nouvelle verte", aucune dans l'autre sens**, et chacune est un faux positif de l'ancienne. Aucun test ajoute, le compte reste a **28 regles** et **487 tests** |

| ADR-0022 | 2026-08-17 | **la story de s14 ne contredit aucune de ses cinq clauses, et la clause executable qui tombe n'est pas dans cet ADR.** `DEC-0032` et `FICHE-barre-entree-niveau.md` attribuent a `ADR-0022` la propriete "un ecran de jeu ne porte qu'une seule sortie visible" ; verification clause par clause : le marqueur (1), la forme contractuelle du masquage (2), le libelle "Les jeux" (3), `sortie-pleine` (4) et la derivation de l'appariement depuis les masquages (5) **survivent tous**, et deux d'entre eux gouvernent activement `S14-1` - le point 3 **interdit** de declarer une puce de niveau `sortie` (mesure : 1 echec, `leLienDeMenuEtLaSortieDeLaZoneDeContenuPortentLeMemeMot`), et le point 5 **retrouve la population de production** que cet ADR disait momentanement vide en 2026-08-07, sans qu'une ligne change. La clause qui tombe est `EnTeteEtMenuTest.leMenuDeLEnTeteContientExactementUneEntreeVersLAccueil`, `page.select("header button, header a[href]").hasSize(1)`, qui releve de `DEC-0012` : un **comptage fixe**, c'est-a-dire la taille du depot au jour ou il a ete ecrit, a remplacer par la propriete derivee que l'avis de faisabilite de s14 specifie. Mesure du 2026-08-17, deux puces posees dans l'en-tete avec la boite chiffree par `ines-design` : **3 echecs sur les 233 tests de `adapter-web`**, dont celui-la ; aucun n'est un garde-fou de cet ADR. Aucun test ajoute par cet amendement |
| ADR-0022 | 2026-08-17 (second) | **le sujet du masquage disparait avec `S14-2` ; l'ADR n'est ni faux, ni remplace, ni sans objet - et une des deux proprietes qu'il gardait se retrouve orpheline.** `DEC-0033` retire le lien "Les jeux" de l'en-tete, donc `a.lien-accueil`, donc la regle `body:has(.ecran-de-jeu) .lien-accueil { display: none }`. Deux garde-fous independants refusent de garder la regle sans son element : celui de cet ADR ("une regle qui masque une classe absente est decorative") et celui d'`ADR-0031` (un selecteur qui ne peut plus jamais s'appliquer). Etat des cinq clauses apres `S14-2` : le point **2** perd toute instance en production, mecanisme exact et **eprouve sur deux jeux fautifs** ; les points **1**, **3** et **4** gardent la leur (les quatre fragments portent toujours le marqueur, `sortie-pleine` et `retour-accueil` portent toujours "Les jeux", garde-fou vert et non vacuant) ; le point **5** garde son mecanisme de traversee de frontiere - les puces de `S14-1` sont permanentes et masquees par rien - et perd sa dependance au masquage, la liste des masquages servis etant vide. **Amendement de constat**, ni ADR neuf, ni changement de statut : aucune clause n'est devenue fausse, aucun ADR posterieur ne remplace celui-ci, et `DEC-0033` retire un element et non une decision. C'est la doctrine que cet ADR s'etait donnee a lui-meme en s06 pour le mecanisme d'`ADR-0017`, appliquee ici a ses frais. Declencheur de reouverture ecrit : le prochain habillage permanent dont la visibilite doit dependre de l'ecran - le couple declaratif et son garde-fou sont prets. **La consequence refusee** : le marqueur du point 1 garde sa population et perd le seul garde-fou qui l'exigeait, alors qu'il a un consommateur vivant (`LEcranDeJeuDeclareCommentIlEstArriveTest`, `ADR-0027`, qui derive sa population par `select(".ecran-de-jeu")` avec une non-vacuite globale) - un cinquieme fragment sans marqueur sortirait de ce perimetre en silence. La propriete est re-portee, decouplee du masquage, comme **condition de cloture de `S14-2`** et non comme regle due par un lot futur ; enonce, emplacement et preuve de morsure dans `squad/sprints/s14-points-d-entree-par-niveau/confirmation-archunit.md`, section 6.4. Un test ajoute par cette condition : **505 -> 506**, 29 regles inchangees |
| ADR-0022 | 2026-08-17 (troisieme) | **ce n'est pas un constat de plus : la clause 2 change de forme, parce que sa forme d'origine obligeait a amender cet ADR a chaque fois que `nadia-prof` change d'avis - elle l'a change deux fois en un jour.** Declencheur : son refus du Done de s14, mesure par capture, **quatre** boites pressables sur l'ecran de confirmation au lieu de deux et **trois** sur l'ecran d'erreur au lieu d'une. Le masquage retrouve donc des sujets, et il en retrouve **deux ecrans sur quatre** - ce que la forme d'origine ne savait pas dire, puisqu'elle presupposait sans l'ecrire *un* habillage, *un* marqueur, et **tous** les ecrans inseres. Trois changements, aucun sur la forme du selecteur, qui reste identique caractere pour caractere : (1) un masquage keye un marqueur d'etat quelconque et non `ecran-de-jeu` ; (2) il peut viser un **sous-ensemble** d'ecrans - le garde-fou exige que le marqueur soit pose par **au moins un** ecran insere et l'habillage par au moins un element permanent, la liste des ecrans devenant une **donnee du gabarit** ; (3) l'assertion `masquages(...).isNotEmpty()` est **retiree** - un garde-fou ne doit pas exiger que son mecanisme soit employe, seulement qu'il le soit des deux cotes quand il l'est. **C'est cette assertion, et rien d'autre, qui a produit toute la cascade du sprint** : rouge quand `S14-2` a retire le dernier masquage, retrait force du test, clause du marqueur emportee, clause 1 orpheline. Diagnostic sur l'oscillation : ce n'est pas la clause qui oscillait mais sa population, et une population qui suit le produit est saine ; ce qui etait mal forme est une clause dont l'**objet** est un etat que seul un arbitrage pedagogique decide. Apres reforme, une reversion de `nadia-prof` coute **un attribut de classe dans un gabarit** et zero fichier de garde-fou - le test applique desormais a toute clause dont l'objet depend d'un jugement humain. `.ecran-de-jeu` ne change pas de statut : le selecteur revient sur un **marqueur neuf**, celui-la n'est toujours nomme par aucun selecteur qui style, donc la migration vers un attribut reste differee et sort renforcee. Regles executables enumerees en section 7.2 de `squad/sprints/s14-points-d-entree-par-niveau/confirmation-archunit.md`, **conditions de cloture du lot en vol** ; si le lot ferme sans elles, c'est cet amendement qui est annule et non une dette qui s'ouvre |
| ADR-0022 | 2026-08-18 (note, pas un amendement) | **sa premisse est renversee par l'utilisatrice et aucune de ses clauses ne bouge - c'est la mesure du troisieme amendement, prise sur le geste suivant.** L'utilisatrice tranche que la sortie vit dans l'en-tete y compris pendant une partie, renversement complet de l'arbitrage de `nadia-prof` du 2026-08-07 rapporte dans le contexte de cet ADR ; elle est l'autorite au-dessus de la squad et le refus reste lisible dans la fiche. La population de masquage change pour la **cinquieme fois** et la bascule coute **trois regles CSS et deux attributs de classe** - zero clause retiree ou ajoutee, zero fichier de garde-fou touche sur ce mecanisme, l'appariement de saillance d'`ADR-0017` se recomposant seul depuis les masquages servis (point 5). Le **point 3** est confirme par le renversement lui-meme : toutes les sorties portent toujours "Les jeux" ou qu'elles vivent, la clause portant sur le mot et jamais sur l'ecran qui le porte - une clause bien formee survit au jugement qui l'a fait naitre. Marqueur neuf ("cet ecran porte sa propre sortie", verifiable contre le document, a la difference d'`ecran-exclusif` qui porte un jugement), trois regles de masquage, deux clauses dues au lot et releve des sources qui perdent leur sujet : `squad/sprints/s15-la-sortie-vit-dans-l-en-tete/exigences-theo-sortie-en-tete.md`. Aucun test ajoute par cette note |
| ADR-0042 | 2026-08-17 | **sa regle n'existe dans aucun fichier Java, et le lot qui devait l'ecrire a ete annule.** Fait mesure sur `e30566b` : `git grep estRemplacant` et `grep -rn "remplacant" --include=*.java` rendent **zero occurrence** dans les cinq modules ; les clauses 1 a 3 de `AucunArretDeTabulationNEstDestructeurTest` appellent toujours `estDestructeur(...)` seul. Cause : cet ADR confiait sa regle "au lot de S13-6", et `DEC-0032` section 4 acte que S13-6 ne sera pas developpee - le porteur a disparu avec la story, sans que `RAPPORT.md` de s13 ni `DEC-0032` ne le comptent. C'est **la dette 1 de la version precedente** (une abstraction documentee partout, jamais implementee) en train de se reproduire sur la production de `theo-archi`. Verifie aussi : `S14-1` ne fait apparaitre **aucun** arret remplacant - la confirmation ne porte pas de bouton de demarrage, l'accueil filtre ne se rend que sans partie en cours - donc le predicat n'y trouverait pas son objet. **Decision : dette explicite ouverte plutot qu'une troisieme promesse**, avec un declencheur observable dans un diff (le premier gabarit insere qui pose un `hx-post` vers `/parties/{jeuId}` sur un ecran rendu en partie en cours) et un cout en ordre de grandeur (une dizaine de lignes pour le predicat, l'essentiel etant la preuve de morsure de la forme M3). Motif du refus d'ecrire maintenant : la mesure M1 de l'ADR lui-meme dit que le predicat serait vert sur une population vide, et son obligation 3 refuse d'appeler garde-fou une clause livree sans son objet. Aucun test ajoute, aucune clause retiree |
| ADR-0014 | 2026-08-17 (troisieme) | **son point 5 fonde toute la doctrine de rendu du depot sur une hypothese fausse, et la limite qui devait couvrir ce cas l'ecartait sur un fait faux.** Le point 5 tire de l'ordre de chargement que "une regle maison de meme specificite l'emporte sur la regle tierce, donc que les planchers exiges par `nadia-prof` sont ceux que l'enfant voit" ; l'hypothese de meme specificite ne tient pas des qu'une regle tierce porte une pseudo-classe. **Defaut reel, vu par l'utilisatrice en jouant l'increment de s14 et par aucun des 510 tests : les boutons de l'en-tete disparaissent au survol** - `.btn:hover` (0,2,0) l'emporte sur `.chip-niveau` (0,1,0) et lui donne texte sombre et bordure transparente sur une bande sombre. La limite "la specificite CSS n'est pas calculee" existait, et n'a rien garde : son illustration (`#id .btn`) a fait passer pour exotique un cas qui est la norme, et sa conclusion "Bootstrap n'en contient pas" est fausse au sens qui compte - **139 selecteurs a pseudo-classe interactive sur 2 959** dans la feuille vendorisee, ceux de `.btn` s'appliquant aux **huit** boutons de l'application. C'est la faute que ce depot s'est deja reprochee deux fois (nommer un trou ne le garde pas). Le point 5 devient une condition **partielle** parmi trois - ordre de chargement, pas de `!important` tiers, **et** declaration des etats. La decision et sa regle vivent dans `ADR-0046` ; aucune autre clause d'`ADR-0014` n'est touchee |
| ADR-0022 | 2026-08-17 (verification) | **la reforme de la clause 2 tenue, mesuree plutot qu'affirmee.** `DEC-0034` fait revenir "Les jeux" dans l'en-tete, donc `.lien-accueil` et son masquage : quatrieme changement de population en un jour, c'est-a-dire exactement le cas pour lequel le troisieme amendement a ete ecrit. Mutation du depot reel a `5b50000` dans la forme d'avant `S14-2` (`cc68304`), `verify` apres `clean`, mutation annulee : **zero fichier de garde-fou de cet ADR ne bouge**, `LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest` reste vert 6 sur 6 avec un masquage qui reapparait sur un marqueur devenu inutilise, coexistant avec celui de `S14-2`. Verts aussi sans qu'une ligne bouge : `UnEcranNeGouvernePasLApparenceDUnAutreTest`, `HabillageDesEcransTest`, `AucunArretDeTabulationNEstDestructeurTest`, `LActionNeDeclarePasMoinsQueLaSortieTest`, `TailleDesTextesEtBoutonsTest`. **Ce que le retour coute quand meme, et qui n'est pas cet ADR** : 2 echecs dans `EnTeteEtMenuTest` sur les deux assertions qui encodent `DEC-0033`/`BARRE-7`, rouge correct dont le retrait revient a `camille-po` avec `nadia-prof` ; plus, depuis `ADR-0046`, un troisieme dans le cliquet d'etats interactifs. Aucune clause changee, aucun test ajoute ni retire |
| ADR-0039 | 2026-08-17 | **sa clause 1 autorise "une propriete calculee de l'objet" sans dire de quelle source le calcul a le droit de tirer**, et le depot a montre le jour meme ce que ce manque coute. Le garde-fou de catalogue livre en s14 derive le paquet attendu de `catalogue.yaml`, donc l'artefact juge a toujours raison : toute mauvaise association ecrite dans le catalogue lui est invisible. **Mesure : le catalogue de production mute pour que le jeu "Les mesures" serve les homophones, `verify` entier vert, 0 echec.** L'amendement ne retire rien - une propriete calculee reste une des deux formes admises - il borne ce dont elle se credite : la **coherence interne** de l'artefact, jamais la correspondance a une intention qui vit ailleurs. Non-preuve 9 ajoutee, avec ses chiffres ; la decision et sa regle vivent dans `ADR-0045`. Aucune clause affaiblie, aucun test retire |

## Lecture rapide

Les dix ADR du sprint 0 forment un socle indissociable, dans cet ordre de lecture :

1. **ADR-0001** dit ou vit le code (cinq modules) ;
2. **ADR-0002** dit ce que le coeur n'a pas le droit de connaitre (liste blanche, pas liste noire) ;
3. **ADR-0003** dit comment on sait qu'une regle mord (contre-exemple obligatoire) - c'est lui qui
   donne leur valeur aux huit autres ;
4. **ADR-0004** tient le principe directeur du produit : mecanique et contenu s'ignorent, dans les
   deux sens ;
5. **ADR-0005**, **ADR-0006**, **ADR-0007** tranchent trois dettes nommees de la version
   precedente : l'abstraction fantome, la difficulte arithmetique imposee a toutes les matieres,
   les neuf chargeurs ;
6. **ADR-0008** ramene le comportement de jeu la ou il est testable ;
7. **ADR-0009** applique l'exigence d'ADR-0003 aux garde-fous qui ne sont pas en Java ;
8. **ADR-0010** applique la forme d'ADR-0009 a un nouveau motif : aucun chemin de poste de travail
   ni compte utilisateur dans un fichier suivi.

Sprint 1 en ajoute deux, tous deux nes du refus pedagogique de `bandit-manchot-additions-cp` :

9. **ADR-0011** ferme le trou qu'ADR-0008 ne couvrait pas : le chemin d'erreur, pas seulement le
   chemin nominal, doit rendre du HTML francais ;
10. **ADR-0012** tranche un point qu'ADR-0006 avait explicitement laisse ouvert en le nommant a
    l'avance : la longueur d'une partie vit dans l'entree de catalogue, jamais dans le contenu.

Sprint 2 en ajoute un, ne de deux mesures de `nadia-prof` sur un garde-fou vert qui ne gardait
rien :

11. **ADR-0013** generalise ADR-0003 au-dela d'ArchUnit et nomme la propriete que les deux
    occurrences partageaient : un garde-fou qui **enumere** son perimetre ne verifie que ce qu'on a
    pense a lui donner. Le perimetre se derive, le critere peut s'enumerer, et la preuve de morsure
    se fait sur un objet neuf, pas sur la mutation d'un objet deja connu.

Sprint 3 en ajoute un, ne d'une demande de l'utilisateur - passer le rendu de base sur une
bibliotheque CSS embarquee :

12. **ADR-0014** dit par quel mecanisme une bibliotheque tierce entre (vendorisation tracee par
    empreinte, jamais un webjar ni un CDN) et repond a la question que cette entree pose a
    ADR-0013 : de quelle source un garde-fou derive-t-il son perimetre quand le CSS servi n'est plus
    ecrit ici. La reponse n'est pas unique - un garde-fou de style juge une declaration et derive de
    la **provenance** du fichier ; un garde-fou pedagogique juge un **element rendu** et derive du
    balisage, le CSS n'etant pour lui qu'une matiere. D'ou la regle centrale : une garantie
    pedagogique est toujours portee par une regle maison ;
13. **ADR-0015** tranche les deux dettes que s03 avait nommees sans les fermer, et corrige la faute
    qu'ADR-0013 ne savait pas encore attraper : deriver correctement, mais **de la mauvaise source**.
    Un garde-fou qui juge un element rendu derive de tous les etats que cet element peut prendre, pas
    du seul etat ecrit en dur ; et un garde-fou dont le nom promet une propriete generale sur un
    fichier nomme est supprime, pas retreci.

Sprint 5 en ajoute quatre : les deux premiers nes du cinquieme motif de refus de "Le bon son", le
troisieme d'un defaut de production reproduit sur le jar, le quatrieme d'un symptome signale par
l'utilisatrice en jouant :

14. **ADR-0016** applique la doctrine d'ADR-0013 a une population que le depot n'avait encore jamais
    derivee, le **contenu**. La mecanique promet une aide depuis S3-2 ; un type de contenu entier
    etait incapable de la fournir, en silence, parce que rien ne confrontait la promesse a ce que le
    contenu sait donner. Le perimetre du garde-fou derive du repertoire de contenu et non du
    catalogue - DEC-0009 venait justement d'en sortir le paquet fautif ;
15. **ADR-0017** ferme le trou que `nadia-prof` a nomme apres trois occurrences identiques : aucun
    garde-fou ne comparait deux selecteurs **entre eux** sur un meme ecran. Il derive une population
    de **paires** - un role inerte dans le balisage, un appariement qui ne confronte que des etats
    capables de s'afficher ensemble - et il se prouve sur les trois etats reels du depot, deux qui
    doivent rougir et un, accepte par `nadia-prof`, qui doit rester vert. Il apporte aussi la
    premiere section "ce que cette regle ne couvre pas" ecrite pour etre lue par elle : une regle
    declarative ne voit ni la largeur d'un libelle, ni une couleur, ni une position ;
16. **ADR-0018** nait d'un **defaut de production reproduit sur le jar livre**, et non d'une revue de
    code : une reponse accentuee faisait planter la manche, et 200 tests verts n'ont rien vu. Il
    corrige l'ADR qui aurait du l'empecher, ADR-0011, ne lui-meme d'un refus pedagogique. Il nomme la
    faute qu'ADR-0011 avait commise sans le savoir - un `@ExceptionHandler` attrape un **type**, donc
    tout son sous-arbre, alors que l'ADR raisonnait sur une **situation**. Une exception du conteneur
    Tomcat, sous-classe d'`IllegalStateException`, empruntait ainsi le canal reserve a la session
    perdue, et n'etait journalisee nulle part. Sa reponse tient en une phrase : l'enfant voit le meme
    ecran, le journal distingue les deux causes. Il apporte la premiere preuve de morsure du depot
    sur un **type d'exception neuf**, qu'aucun gestionnaire ne nomme, et il repond par oui a une
    question ouverte : un garde-fou qui verifie qu'une exception imprevue laisse une trace est
    atteignable, a condition de la faire lever pour de vrai au lieu de l'analyser statiquement ;
17. **ADR-0019** nait du symptome que l'utilisatrice avait signale en jouant - "le score ne
    s'incremente pas toujours" - reproduit par `yanis-qa` a 14 pertes sur 40. Deux defauts distincts
    partagent le meme declencheur, le double appui, et le meme cycle lire-calculer-ecrire sur la
    session HTTP : une course qui perd un point gagne, et un coup rejoue sur une partie terminee qui
    rend l'ecran d'erreur. Sa conclusion qui commande tout le reste est que **corriger le premier
    seul aggrave le second** - le verrou rend systematique l'ecran d'erreur qui n'etait
    qu'occasionnel. Il apporte aussi la premiere extension de la doctrine d'ADR-0013 au
    **parallelisme** : un test qui n'envoie qu'une requete a la fois a un perimetre qui exclut la
    concurrence, et un test de course a une seule tentative serait vert deux fois sur trois sur un
    depot casse, donc decoratif.

Sprint 6 en ajoute six, tous nes d'obstacles ou d'arbitrages recus pendant le sprint, et tous
portant sur la meme faute de fond - **un automate vert qui ne regardait pas le bon objet** :

18. **ADR-0020** repond a la question que ni les tests de controleur ni les tests d'integration ne
    posaient : quel est l'objet a juger quand un ecran est assemble par htmx dans le navigateur ? Ni
    le fragment seul, ni la page avant echange, mais le **document assemble** - et les deux defauts
    qu'il ferme vivaient tous les deux dans cet ecart. Il apporte deux choses au depot. D'abord la
    demonstration qu'un defaut signale comme unique pouvait avoir **deux chemins** : l'identifiant
    duplique venait du fragment qui reprenait l'identifiant de sa zone, **et** du gabarit qui se
    reinjectait dans lui-meme, le second n'ayant jamais ete nomme par personne. Ensuite une reponse
    ecrite a une question d'atteignabilite : le chemin de l'ecran mort n'existe pas en un seul onglet,
    il existe des qu'un ecran deja rendu survit a un changement d'etat de la session - donc
    l'inatteignabilite reposait sur une hypothese de comportement d'utilisateur, que rien ne peut
    garder. Trois lignes valent mieux qu'une hypothese non gardable ;
19. **ADR-0021** remplit un silence d'ADR-0003, qui disait ce qu'un contre-exemple doit faire sans
    jamais dire ce qu'il n'a pas le droit de faire. Six faux depots ecrits pour etre refuses par un
    automate s'etaient mis a s'executer dans le produit sous test, dont un qui rendait l'ecran
    d'erreur a la place du gestionnaire reel : l'inversion complete de la doctrine. Il tranche en
    attaquant la cause - un contre-exemple ne porte aucun stereotype Spring, sa faute vit dans la
    structure que sa regle examine - plutot que le symptome, et il refuse pour cela les deux voies
    que la story proposait (resserrer le scan, deplacer les contre-exemples), l'une parce qu'elle
    fabrique un perimetre enumere, l'autre parce que l'emplacement d'un contre-exemple fait sa
    valeur. Il apporte au depot le premier couple de garde-fous **explicitement non recouvrants** :
    une regle ArchUnit qui voit une cause sans demarrer de contexte, un `@SpringBootTest` qui voit
    une propriete qu'aucune analyse statique n'atteint ;
20. **ADR-0022** nait d'un arbitrage de `nadia-prof` qui **refuse la forme** d'une story tout en
    confirmant son principe, et il tranche les deux questions techniques qu'elle m'a rendues
    explicitement. La premiere - comment un element d'un en-tete permanent, que le serveur ne rend
    jamais, cesse d'etre visible sur certains ecrans sans JavaScript - se resout par un couple
    declaratif : le serveur pose un marqueur d'etat dans la seule zone qu'il rend, la feuille maison
    porte le masquage, et l'element reste present dans le document comme `DEC-0012` l'exige. La
    seconde est la plus instructive pour la suite du depot, parce que c'est **moi** qui avais
    fabrique le defaut la veille : mon extension d'ADR-0017 confrontait un bouton hors zone aux
    boutons de tous les ecrans, en confondant *survivre a un echange* et *etre visible*. Cet ADR
    corrige donc un amendement du meme jour, supprime l'epinglage qui affirmait un co-affichage
    disparu, et garde le mecanisme sous-jacent en disant franchement que sa population de production
    est momentanement vide - un mecanisme juste et eprouve sur objet neuf n'est pas decoratif, mais
    l'epinglage qui pretendait que la production l'exerce l'etait. **Son contexte est amende le
    2026-08-14** : le chiffre de boite qu'il citait etait perime par son propre point 3, et la
    comparaison de tailles qu'il portait opposait deux elements de deux ecrans differents. La decision
    ne bouge pas d'une ligne, aucun garde-fou ne raisonnait sur une taille ;
21. **ADR-0023** ferme un trou que j'avais moi-meme nomme sans le refermer, et il le ferme au bon
    endroit : la propriete "un paquet offre plus d'items que la partie n'a de manches" etait verifiee
    par deux methodes qui nommaient chacune leur paquet dans leur titre, donc par un perimetre
    enumere - la faute qu'ADR-0013 interdit nommement. Le sprint en a fourni la demonstration : une
    entree de catalogue neuve a du recevoir sa propre methode, ecrite a la main par son auteur. Le
    perimetre devient le **catalogue**, la seule source qui porte `nombreDeManches` (ADR-0012), et
    l'inegalite est stricte parce que l'egalite est le second des deux defauts - non pas la partie qui
    s'arrete avant l'heure, mais celle qui ne varie jamais. Il apporte au depot la premiere **relecture
    adverse d'inegalite executee** : le jeu fautif porte l'egalite exacte, et remplacer `>` par `>=`
    fait echouer le contre-exemple. C'est la lecon du plafond de dette d'ADR-0016 encodee au lieu
    d'etre racontee ;
22. **ADR-0024** tranche la decision que le lot "Le bon son" attendait, et la lecture du code y a
    corrige mon inclination : je penchais pour un porteur par un argument de semantique, la raison
    reelle est structurelle - apres `jouer`, la session ne tient plus l'item joue, le verdict en est la
    seule trace, et un accesseur separe aurait exige un champ parallele a tenir en phase, la forme
    exacte du defaut d'ADR-0016. Il apporte deux choses au depot. D'abord une **frontiere de matiere**
    devenue executable : le texte generique traverse le domaine, la graphie qui le justifie reste dans
    le paquet qui la declare, et un automate refuse desormais tout mot de matiere sur les types que
    toutes les matieres traversent - la dette 2 de la version precedente rendue impossible dans son
    sens inverse. Ensuite un garde-fou dont les **deux cles se gardent l'une l'autre** : puisque le
    chargeur ignore toujours en silence une cle inconnue, c'est le croisement qui rend la faute de
    frappe rouge, et l'ADR dit franchement quelle dette il laisse ouverte et quel second usage la
    fermerait ;
23. **ADR-0025** est le seul du depot dont la decision principale est un **refus de garder**, et c'est
    ce qui le rend utile a relire. Un obstacle de mesure - le plancher de largeur de Chrome headless -
    avait fabrique un symptome qui n'existait pas, et la question routee etait de savoir si le trou de
    `ViewportEtLargeurTelephoneTest` (une population faite de largeurs declarees, ou un debordement de
    flex n'apparait jamais) etait gardable. Il le coupe en deux. La propriete de **rendu** est declaree
    non gardable par un automate de ce depot et confiee a `nadia-prof`, avec les trois raisons chiffrees
    d'ecarter aujourd'hui le protocole DevTools et le declencheur qui rouvrirait la question ; la cause
    racine, elle, est fermee - une mesure dont la commande n'est ecrite nulle part n'est pas rejouable,
    donc la commande entre au depot et **refuse elle-meme** les deux invocations trompeuses etablies. Il
    apporte au depot deux choses qui valent au-dela de son sujet : la trace ecrite d'un garde-fou
    **refuse avec son chiffre** (une regle sur le repli des rangees flex serait rouge sur trois blocs de
    production le jour de son ecriture, donc reclamerait de changer la mise en page servie a un enfant
    pour satisfaire un test), et le constat que deux juges purs ecrits en s06 n'etaient lances par aucun
    automate - la CI balaie desormais `outils/*-test.sh`.

Sprint 7 en ajoute deux, le second corrigeant le premier le jour meme (cet intertitre manquait :
les deux ADR de s07 etaient ranges sous le paragraphe de s06, corrige a l'ecriture d'ADR-0028) :

24. **ADR-0026** est saisi de la dette n°1 reproduite - une mecanique nommee "bandit manchot" qui rend
    un questionnaire a choix multiple - et il tranche d'abord une frontiere : **une animation retarde ce
    que l'enfant voit, elle ne determine rien**, la ligne se tracant sur une propriete verifiable du
    document servi, qui reste jouable sans CSS et sans JavaScript. Il apporte trois choses qui valent
    au-dela de son sujet. La premiere est **une decision moins chere apres lecture du code qu'avant** :
    je cherchais une nouvelle route pour le levier et l'ilot JavaScript d'`ADR-0008` a ouvrir, alors
    qu'une animation CSS demarre a l'insertion de l'element et que le bouton qui ouvre une manche existe
    deja dans les deux branches que `camille-po` peut trancher - cout architectural nul dans les deux
    cas. La deuxieme est un **vert silencieux ferme et prouve par execution** : la regle d'ecart entre
    deux boutons de reponse ne lisait que `gap`, et `gap: 1rem; column-gap: 0.5rem` la faisait passer
    en lisant 1,0rem, ce que le rejeu de l'ancienne lecture sur le jeu fautif a confirme - l'axe que le
    doigt de l'enfant franchit des que les propositions sont cote a cote etait le seul non garde. La
    troisieme est un **epinglage de population vide assume** : deux de ses quatre clauses ne portent
    encore sur rien, et plutot qu'un commentaire, un test affirme que le compte d'animations de
    production vaut exactement zero et dit quoi faire le jour ou ce n'est plus vrai. Il refuse enfin un
    garde-fou tentant avec la mesure qui le refute (`oreille`, 7 caracteres, tient dans une colonne de
    99 px ; `mangez`, 6 caracteres, n'y tient pas : le budget est une largeur de glyphes, pas un nombre
    de caracteres) et remonte a `camille-po` une contradiction entre `DEC-0014` et la mise en page en
    rouleaux, plutot que de la trancher a sa place ;
25. **ADR-0027** corrige `ADR-0026` du sprint precedent - le meme jour - sur le defaut que
    `nadia-prof` a trouve **en lisant les gabarits**, pas en jouant : le mecanisme keyait le mouvement
    sur "l'element entre dans le document", et un element entre aussi dans le document quand le
    navigateur charge une page, ce que la reprise silencieuse de S1-4 fait sans aucun geste. Il vaut
    d'etre relu pour quatre choses. La premiere est un aveu utile : c'est la **troisieme occurrence de
    la meme famille de faute de ma main** - une propriete structurelle prise pour la propriete qui
    compte - apres `ADR-0022` et la limite 5 d'`ADR-0017`, et l'ADR l'inscrit comme telle plutot que
    comme un oubli. La deuxieme est une contrainte et non un choix : le document assemble est identique
    sur les deux chemins, donc **aucun selecteur CSS ne peut les distinguer** et seul le serveur peut
    poser la difference. La troisieme est le seul endroit de ce chantier ou une derivation totale
    s'obtient du **compilateur** plutot que d'un test - le point de passage de l'ecran de jeu est
    unique, la declaration y est un parametre, une route neuve qui l'oublierait ne compile pas. La
    quatrieme est un mouvement de frontiere dans le bon sens : la duree du defilement, qu'`ADR-0026`
    declarait non gardable, **cesse de l'etre le jour ou son destinataire donne un chiffre**, et trois
    des seuils de `nadia-prof` passent de son jugement a un automate. Il note enfin qu'un garde-fou d'un
    autre lot l'a corrige avant la revue - une classe posee sans regle servie est refusee, ce qui a
    fait choisir un attribut plutot qu'une regle CSS decorative - et il rend explicitement a
    `nadia-prof` le residu qu'aucun automate n'atteint, dont un mot peint dans une image.

Sprint 8 en ajoute quatre : les deux premiers nes des obstacles restes ouverts a la cloture de s07, le
troisieme de la faisabilite du cabinet de la machine, le quatrieme de la dette de test que ce
troisieme avait nommee sans la fermer :

26. **ADR-0028** tranche le mecanisme de garde de `DEC-0016` en deux moities opposees, et c'est ce
    couple qui vaut d'etre relu : la hauteur devient un **parametre** de la commande de mesure, et
    "la reponse est au-dessus du pli" est **declaree non gardable** par un automate de ce depot.
    C'est le second refus de garder du depot, ecrit sur le patron d'`ADR-0025` dont aucune des trois
    raisons n'a change en un sprint, et il montre ce que coute de ne fermer que la moitie qui plait :
    `yanis-qa` avait du mesurer le pli a la main a la porte technique de s07 parce que la clause
    "non gardable" avait ete donnee sans l'outil. Il apporte au depot une mesure que personne ne
    pouvait obtenir avant - sur l'item le plus long de `cm1-homophones` avec son indice affiche, a
    un viewport reel de 360 x 640, les deux boutons de reponse sont a `y = 717` a `y = 790`, donc
    entierement sous le pli, et absents de la capture. Le defaut que `nadia-prof` avait mesure a la
    main est desormais reproductible par une commande versionnee ; le verdict reste le sien, et
    l'ADR ne le prononce pas. Il note aussi la limite neuve qu'un outil de capture ne peut pas
    franchir seul : un ecran de manche s'obtient par un POST ;
27. **ADR-0029** ferme le trou du decalage inter-colonnes cumulatif, et il vaut d'etre relu pour la
    forme de la solution : **une pince de deux clauses dont aucune ne recopie le seuil de l'autre**.
    La borne de 250 ms de `nadia-prof` restait gardee la ou elle l'etait deja ; ce qu'aucune
    population ne collectait, c'est **le nombre de colonnes que le contenu peut produire**. Une
    cinquieme colonne sans sa regle fait rougir la clause neuve, une cinquieme colonne obtenue en
    prolongeant le pas de 0,08 s fait rougir l'ancienne - les deux machoires mesurees par mutation
    reelle des fichiers de production, pas racontees. Le seul chemin vert est donc de **revoir** le
    decalage, decision de forme qui repart a `ines-design` sous arbitrage de `nadia-prof`. Il
    apporte deux choses au-dela de son sujet : un perimetre **deliberement plus large que le
    catalogue**, avec la raison chiffree qui le distingue du choix inverse d'`ADR-0023` (le catalogue
    ne sert que trois colonnes, la quatrieme vit dans un paquet hors catalogue dont les deux
    conditions de remise sont remplies), et un **epinglage refuse avec son motif** - figer le
    plafond de colonnes rougirait sur l'arrivee legitime d'un contenu, pas sur ce qui manque.
28. **ADR-0030** juge la faisabilite du cabinet de la machine et vaut d'etre relu pour la lecon de sa
    cinquieme section, qui depasse le chantier : **une propriete de rendu peut etre gardable et
    l'egalite de chaines ne pas etre cette propriete.** `inset(100% 0 0 0)` et `inset(0 0 100% 0)` sont
    deux ecritures differentes de "rien de visible" ; une comparaison textuelle aurait rougi sur la
    correction juste, mesure a l'appui (deux cas en echec, dont la production corrigee), et la seule
    facon de la verdir aurait ete de reecrire ce que voit l'enfant pour satisfaire l'automate. La
    clause compare donc la geometrie declaree, et elle attrape la famille entiere du defaut que seul le
    releve image par image de `nadia-prof` avait vu en s07 - une animation qui rend la main sur un autre
    etat que celui qui la suit - alors que trois de mes clauses passaient dessus sans regarder **ou**
    l'animation finit. Il apporte deux autres choses : un refus tranche sur un fait du depot plutot que
    sur un principe (l'anneau de focus clavier est une **ombre portee**, la bibliotheque vendorisee
    declarant `outline: 0` sur `.btn:focus-visible`, donc tout rognage d'un ancetre le supprime - ce qui
    rend `overflow: hidden` inacceptable sur le cabinet et **etablit** le defaut que je disais non
    etabli en s07) ; et une regle ecrite **avant** l'implementation, dont la population d'ancetres est
    derivee des gabarits pour que le cabinet y entre sans que personne y pense. Il refuse enfin une
    clause tentante avec le chiffre qui la refute : interdire un `font-size` sur un ancetre de bouton ne
    protege rien, les quinze blocs qui declarent une taille la declarent tous en `rem`, unite relative
    a la racine et non a l'ancetre.
29. **ADR-0031** ferme le canal de feuille de la condition C4 qu'`ADR-0030` avait refuse d'ecrire, et
    il vaut d'etre relu pour la facon dont le refus est leve : non pas en nommant enfin `.cabinet`
    puisque la classe existe, mais en changeant de propriete. Ce qui est juge n'est plus "cette
    classe-la est-elle conditionnee au verdict" mais **"ce selecteur peut-il s'appliquer"** - la zone
    `hx-target` recoit un ecran a la fois, donc un selecteur qui melange le verdict d'un ecran et
    l'habillage d'un autre est soit mort, soit l'intention que C4 interdit. Aucun nom de classe n'est
    ecrit dans le test, et les deux fragments apparaissent **derives** dans le message d'echec. Il
    apporte trois choses au-dela de son sujet : une alternative ecartee avec sa mesure (la forme
    enumeree de S8-3 serait **verte** sur `body:has(.est-juste) .cabinet-bandeau`, faute d'y trouver
    ses sous-chaines - et elle reste pourtant juste la ou elle est, sur un couplage intra-ecran
    qu'aucune structure ne tranche) ; **deux limites mesurees plutot que supposees**, dont un vert
    assume - le verdict peut encore colorer la coquille permanente, et le canal du gabarit reste
    ouvert, donc la dette C4 est dite **reduite et non fermee** ; et une clause de plus qui n'est
    **pas** une regle ArchUnit, le compte restant a 28 avec sa methode.
30. **ADR-0032** ne ferme aucun defaut livre : il ferme un **trou de morsure dans une regle deja
    acceptee**, et c'est pour cela qu'il vaut d'etre relu. `ADR-0023` exige plus d'items que de
    manches et son garde-fou compte des `ItemId` ; ces identifiants sont derives de la **position**
    dans le YAML, donc deux items rigoureusement identiques comptent pour deux et un paquet trop court
    se verdit par des copies. La pression n'est pas imaginee, elle est calculee : les quatre entrees
    de catalogue declarent dix manches, et "Complements a 10" n'admet que onze items canoniques, neuf
    sans les cas degeneres. Il apporte trois choses au-dela de son sujet : un critere **volontairement
    plus faible que le plus evident** (l'ecran, pas l'enonce) parce que le critere fort aurait refuse
    une forme legitime de la competence de comparaison - et la verification que le critere fort
    n'aurait rien attrape de plus sur les 397 items reels du depot ; une **exclusion de perimetre
    prouvee necessaire par mutation** plutot que concedee (juger les paquets combinatoires rend la
    clause rouge sur le paquet d'additions reellement servi) ; et une alternative ecartee dont le motif
    est un defaut pire que celui qu'elle corrige (deriver l'`ItemId` du contenu ferait disparaitre un
    item **en silence** dans `ListContentSource`). Ce n'est pas une regle ArchUnit, le compte reste a
    28 avec sa methode.
31. **ADR-0033** est le premier `ADR` de ce depot qui **renverse un refus ecrit par son auteur dans un
    `ADR` anterieur**, et c'est pour cela qu'il porte un numero au lieu d'etre un amendement :
    `ADR-0030` refusait de garder la moitie non animee de la condition C3 au motif qu'elle "n'a
    aujourd'hui aucune population dans la feuille maison", alors qu'il acceptait sa propre clause de
    rognage en ecrivant l'inverse a deux pages d'ecart. La population d'une interdiction n'est pas
    l'ensemble de ses violations mais celui des sujets qu'elle gouverne, et cet ensemble valait deja 33
    regles. Il apporte trois choses au-dela de son sujet : un perimetre derive **par complementaire**
    (habillage = classes posees moins classes pressables), dont la limite est nommee comme jouant dans
    le mauvais sens - la surcollecte Thymeleaf y **retranche** du controle au lieu d'y ajouter, un jeton
    mesure aujourd'hui ; une **exclusion mesuree et cliquetee** (`transform` reste autorise, parce que
    l'inclure rougirait la croix du signal faux, et un test epingle les deux faits dont l'exclusion
    depend) ; et un **trou nomme sans etre ferme** - les cinq clauses de mouvement d'`ADR-0026`,
    `ADR-0027` et `ADR-0030` ne voient rien de ce qui est ecrit sous `prefers-reduced-motion`, ce que
    cet `ADR` ferme pour l'habillage seulement et laisse ouvert pour le pressable, en dette explicite.
    Ce n'est pas une regle ArchUnit, le compte reste a 28 avec sa methode.
32. **ADR-0034** ne corrige aucune clause : il corrige un **corpus**, et c'est pour cela qu'il vaut
    d'etre relu. `ADR-0020` et `ADR-0027` derivent leur perimetre par route, obligation 1 d'`ADR-0013`
    satisfaite, et `POST /parties/{jeuId}/coups` y figurait ; mais cette route rend **deux etats** et
    les deux corpus n'en jouaient qu'un - les deux coups qu'ils envoient sont les bonnes reponses des
    deux items du jeu de test. Sept clauses ne voyaient donc jamais l'ecran d'une reponse fausse.
    Mesure des deux cotes du meme octet de production : un paragraphe vide ajoute sur la seule branche
    fausse passait **vert sur 13 tests** et rend maintenant un rouge qui nomme l'ecran. Il apporte
    trois choses au-dela de son sujet : la variante de defaut que je n'avais pas nommee en huit ADR -
    **un perimetre derive de la bonne source peut rester aveugle a une dimension entiere** ; une
    clause de refus d'ecriture qui va contre l'usage (les deux marqueurs sont lus dans le gabarit, et
    la lecture echoue plutot que de rendre une liste vide, sans quoi la clause serait verte a vide) ;
    et le fait que `LesGardeFousSontMisALEpreuveTest` a attrape **son propre auteur** en train
    d'ajouter une lecture de production sans jeu fautif, dans le lot ou il reprochait a deux corpus
    d'etre aveugles. Ce n'est pas une regle ArchUnit, le compte reste a 28 avec sa methode.
33. **ADR-0035** est le premier garde-fou de ce depot **ecrit avant son objet**, et le premier dont le
    contre-exemple **n'est pas de la main de son auteur** : c'est le balisage qu'`ines-design` a
    reellement propose (`proposition-ines-meuble.md`, section 2.2), repris tel quel. Il vaut d'etre relu
    pour la forme du defaut qu'il nomme : `aria-hidden` etait pose sur un **conteneur** parce que, le
    jour ou il a ete ecrit, tout son contenu etait decoratif - une declaration qui decrit un **etat**
    la ou elle devrait decrire une **intention**, donc une declaration qui devient fausse sans qu'aucune
    ligne ne change de sens. Meme famille que le plafond survivant a sa dette d'`ADR-0016`. Il apporte
    deux choses au-dela de son sujet : il gouverne **egalement** les quatre structures que `nadia-prof`
    peut choisir, donc il n'attend pas son arbitrage et ne le prejuge pas ; et il refuse d'imposer une
    forme (interdire `aria-hidden` sur un conteneur) pour ne refuser que le defaut (du texte inaudible),
    parce que la forme aurait ete fausse en general. Ce n'est pas une regle ArchUnit, le compte reste a
    28 avec sa methode.
34. **ADR-0036** est le seul `ADR` de ce depot ecrit pour dire qu'une decision **n'avait pas besoin de
    lui**, et il vaut d'etre relu pour ce que la mesure a trouve a la place. La question posee etait
    "quel `ADR` autorise les fichiers image", et la reponse est : aucun, rien ne les interdisait, une
    image binaire est servie et affichee depuis s06 (`favicon.ico`, posee par un `img`). En le
    mesurant, deux garde-fous se sont reveles faux dans des directions opposees - l'un ne regardait
    rien (`ADR-0014` sur les gabarits, **zero element** selectionne parce que jsoup 1.23.1 ne comprend
    pas le deux-points echappe, et une fixture ecrite en attribut simple le cachait), l'autre
    inventait des fautes (`ADR-0010`, 4 PNG refuses sur 21 pour une sequence de quatre octets). Il
    apporte trois choses au-dela de son sujet : la premiere occurrence ou **la lecon de s05 sur les
    fixtures coute une garantie entiere** et non une clause ; un refus de clause **motive et mesure**
    - pas de plafond de poids, parce qu'un nombre absolu est une cible produit, qu'un plafond relatif
    ferait dependre le verdict d'une image du poids d'une bibliotheque, et qu'un cliquet doit valoir
    exactement la dette, qui vaut 1 014 octets et interdirait donc la premiere illustration ; et une
    propriete **pedagogique** qui cesse d'etre structurelle (la condition C5 de `nadia-prof`) et qu'un
    critere deja ecrit mais jamais contre-exemplifie garde desormais pour de bon. Ce ne sont pas des
    regles ArchUnit, le compte reste a **28**. Methode de ce compte : declarations `ArchRule <nom> =`
    sous `src/test/java` des six modules du pom agregateur, ce que recense
    `LesGardeFousSontMisALEpreuveTest.toutesLesRegles` ; un balayage plus large de `src/test` en rend
    30, les deux de plus etant celles du faux depot de contre-exemple, qui ne sont pas des regles de ce
    depot.
35. **ADR-0037** repond a une question de gouvernance - "ou est ecrit l'arbitrage qui autorise
    `nashorn-core` ?" - et vaut d'etre relu pour ce que la verification a trouve a cote. La lecture de
    `yanis-qa` etait exacte : dependance de portee `test`, absente des 43 archives du livrable, hors du
    perimetre d'`ADR-0014`. Mais en verifiant **le texte** d'`ADR-0014` plutot que la conclusion, son
    point 1 parle de *dependances* et non d'octets servis, et son refus du webjar vit dans ses
    alternatives, sans regle - donc **rien n'empechait un webjar d'entrer**, et le garde-fou de
    provenance ne pouvait pas le voir : son perimetre descend de l'arbre `static/` d'un module, ou un
    webjar n'atterrit jamais. Il apporte deux choses au-dela de son sujet : une decision qui declare
    explicitement que sa **moitie permissive n'a rien a encoder** (rien n'interdit une dependance de
    test, donc rien ne le verifie) et n'encode que sa moitie restrictive ; et un garde-fou dont l'objet
    est **le livrable produit** et non le pom ni le classpath de test - les deux formes que j'ai
    ecartees sur mesure, la premiere parce que la portee effective d'`archunit-junit5` n'est pas dans
    le pom du module qui la declare, la seconde parce que sa population contiendrait les outils de test
    que cet `ADR` declare libres. Ce n'est pas une regle ArchUnit - une analyse de bytecode ne voit pas
    une entree d'archive - le compte reste a **28** avec sa methode ; les tests passent de 403 a 406.
36. **ADR-0038** est le premier `ADR` de ce depot qui **reprend a un automate une propriete qu'on
    croyait reservee a `nadia-prof`**, et il vaut d'etre relu pour la forme de l'enonce plus que pour
    le sujet. Son motif est le seul defaut bloquant du sprint 10, leve par `DEC-0026` : la partie est
    detruite en deux touches **si et seulement si** la reponse revelee occupe la derniere position de
    l'ordre de tabulation. Mesure faite sur le document assemble rendu par le controleur reel, et non
    sur le gabarit : le document porte **deux** sorties, celle de l'en-tete permanent n'etant pas un
    arret de tabulation parce que la feuille la masque sur un ecran de jeu - la conclusion de
    `yanis-qa` est juste, sa demonstration incomplete. Il apporte trois choses au-dela de son sujet :
    un enonce qui parle du **role de l'element atteint** et jamais d'un rang, ce qui lui permet de
    distinguer un remede d'un deplacement du defaut (deplacer le bouton apres les propositions est
    refuse) ; un critere de destruction fonde sur l'**effet mesure** de la requete declaree et non sur
    `data-role`, parce que la clause de non-vacuite d'`ADR-0017` est globale et qu'un simple changement
    d'attribut rendrait la regle verte ; et une liste explicite de huit choses que son vert **ne
    prouvera pas**, dont la premiere est que le focus aille la ou le serveur le declare - ce qui est
    pourquoi ses deux clauses sont ecrites separement. Il n'encode rien lui-meme : la regle est due par
    le lot de s11, et son jeu fautif est l'etat de la production du jour, deja rouge sur deux ecrans.
    Ce n'est pas une regle ArchUnit, le compte reste a **28** avec sa methode.
    **Amende le 2026-08-14, une fois la regle livree** : le "deja rouge sur deux ecrans" de la phrase
    precedente etait faux - **cinq** en clause 1, un en clause 2 - et sa clause de concordance
    (`data-role="sortie"` si et seulement si la requete detruit) est **retiree**, parce qu'elle
    interdisait le remede que sa propre decision benissait. Ce qui vaut d'etre relu de cet ADR n'est
    donc plus seulement la forme de l'enonce, c'est aussi le piege du **moyen pris pour la propriete** :
    la concordance servait a empecher le desamorcage d'un critere fonde sur l'attribut, et un critere
    qui ne lit pas l'attribut la rend inutile. Detail et mesures dans le tableau ci-dessus.

37. **ADR-0039** est le premier `ADR` de ce depot qui **corrige une case du tableau d'`ADR-0013`**,
    c'est-a-dire la doctrine de garde-fou elle-meme et non l'un de ses cas. Sa phrase utile ne parle
    ni de perimetre ni de critere mais de **polarite** : une liste de refus est verte par defaut, une
    liste d'autorisation est rouge par defaut, et une promesse universelle ne peut pas etre tenue par
    la premiere. Elle vaut d'etre relue pour trois raisons. D'abord parce qu'elle nait d'un **trou
    vivant** rejoue avant correction et non d'un raisonnement : un `alpine.min.js` reel,
    correctement trace, entrait dans le depot en laissant seize tests verts, dont celui qui promettait
    de le refuser. Ensuite parce qu'elle **refute la distinction qu'on lui proposait** - categorie qui
    generalise contre incident clos - en montrant que `"bootstrap"` generalise autant que `"addition"`
    et que `"addition"` echoue autant sur l'objet suivant, ce qui range
    `AucunVocabulaireDeMatiereTest` dans la meme famille et le laisse enumere pour une raison de
    **portee** (son complement demande une decision produit) et non de nature. Enfin parce qu'elle
    **trace une limite pour rester applicable** : l'obligation ne vaut que si le complement du critere
    contient un objet que le depot peut acquerir sans decision qui rouvre le garde-fou - sans quoi
    tout critere, etant enumere a un niveau ou a un autre, serait fautif et la clause serait ignoree.
    Les deux sondes syntaxiques qui auraient pu remplacer l'aveu sont ecartees **par des chiffres**
    (l'une ne trouve pas le cas vivant, l'autre rend vingt faux rouges sur vingt-et-un cas), et la
    faiblesse qui reste est nommee sans fard : le recensement compte les criteres enumeres qui se
    declarent, jamais ceux qui se taisent - la faiblesse exacte qu'`ADR-0013` reprochait a
    l'alternative de l'annotation. Ce n'est pas une regle ArchUnit, le compte reste a **28** avec sa
    methode ; les trois garde-fous qu'il porte sont des tests JUnit, et les neuf tests qu'il ajoute
    portent la suite de 416 a **425**.

38. **ADR-0040** est le premier `ADR` de ce depot qui **refuse une exemption qu'on lui demandait de
    formuler**, et c'est pour cela qu'il vaut d'etre relu. La question posee etait bien construite :
    la clause 2 d'`ADR-0038` interdit qu'une tabulation depuis un point de focus atteigne un arret
    destructeur, et l'ecran de confirmation est precisement l'ecran dont la destruction est legitime -
    faut-il l'exempter, et a quelle condition ? La reponse est non, pour un motif **mesure et non
    doctrinal** : `recreo.js` pose `tabindex="-1"` sur le point de focus, donc focaliser le bouton sur
    d'un ecran a deux boutons retire ce bouton de l'ordre vivant et fait du destructeur le seul arret
    restant. Le rouge de la clause 2 n'est pas le prix d'un remede, c'est le refus d'un mauvais remede,
    et **aucune formulation d'exemption ne survit a ce test** - la plus seduisante ("l'ecran dont la
    destruction est le second geste delibere") exempte tout le corpus, l'ecran de revelation compris,
    ou le defaut est ne. Il apporte trois choses au-dela de son sujet : une **clause 3** qui juge le
    point de depart lui-meme (Entree seule) la ou les deux autres jugent l'arret d'apres, nee d'un trou
    reel - le focus pose sur le bouton destructeur laissait 222 tests verts ; une **derivation qui
    reduit le choix sans le faire** - les trois clauses eliminent toutes les formes sauf une, un point
    de focus sans requete place avant les deux boutons, et laissent a `nadia-prof` ce qui lui revient ;
    et une **epreuve de morsure qui sert d'anti-vacuite a toute la famille** - elle exige qu'il existe
    encore un ecran a partie en cours offrant un arret destructeur, faute de quoi les trois clauses ne
    gardent plus rien. Ce qu'il n'encode pas est dit : rien n'oblige encore un ecran a **declarer** un
    point de focus, cette quatrieme clause serait rouge des sa premiere execution et appartient au lot
    qui corrigera l'ecran. Ce n'est pas une regle ArchUnit, le compte reste a **28** avec sa methode ;
    les deux tests qu'il ajoute portent la suite de 425 a **427**.

39. **ADR-0041** est ne d'un garde-fou **vert et faux pendant deux sprints**, et c'est cette
    circonstance qui le rend utile a relire plutot que sa decision, qui tient en trois lignes. La
    valeur d'une proposition etait lue par un motif ecrit en passant - le premier groupe de chiffres -
    et personne n'avait ecrit ce qu'une valeur devait etre : il n'y avait donc rien a contredire. Deux
    faits mesures cette semaine, et chacun est une facon differente d'etre vert a tort. Sur des grands
    nombres a separateur d'espace, douze items dont la bonne reponse est **toujours** la plus grande se
    lisent comme une repartition parfaitement uniforme (33,3 %) ; sur des durees, six items faussement
    classes "la plus petite" compensent **exactement** six items justes par hasard, et le taux tombe a
    **50,0 % pile** contre un seuil compare strictement - un seul item deplace d'une moitie a l'autre
    aurait rendu 58,3 % et un rouge. Un vert obtenu par compensation exacte entre deux erreurs opposees
    ne se distingue en rien, dans un rapport de revue, d'un vert obtenu par une propriete tenue. La
    decision acte la lecture livree par `lea-dev` - duree en minutes d'abord, sinon nombre entier
    separateurs retires, sinon rien - et ferme les **deux angles morts** que le mot "sinon rien"
    ouvrait : un paquet juge sur une partie de ses items doit declarer le nombre exact d'items ecartes
    (`cm1-perimetre`, 2 sur 22, et le taux de 90,0 % qu'on lui cite porte donc sur 20), et les trois
    propositions d'un item portent la meme unite, la lecture ne lisant que le nombre. La morsure de la
    seconde clause est mesuree sur du materiau reel et le resultat depasse l'attente : un distracteur de
    `cm1-mesures` reecrit dans une unite equivalente (`"4000 cm"` en `"40 m"`) **fait basculer** le rang
    classe de cet item, et les douze tests du garde-fou de rang restent verts. Il porte aussi une
    **mesure ratee, ecrite parce qu'elle a failli entrer dans l'ADR** : deux executions sans `-am` ont
    rendu un chiffre perime qui figurait deja dans le depot, donc credible. Ce n'est pas une regle
    ArchUnit, le compte reste a **28** avec sa methode ; les sept tests qu'il ajoute portent la suite
    de 468 a **475**.

40. **ADR-0042** est le premier `ADR` de ce depot qui **refute par la mesure le remede que son propre
    auteur avait annonce**, et c'est la seule raison de le relire. Mon avis de faisabilite S13-6
    proposait un second predicat "remplacant" defini par la comparaison du `jeuId` : la session porte
    une partie **d'un autre jeu**. Cable dans les clauses et mesure sur la forme la plus dangereuse de
    l'ecran a venir - le catalogue en tete du document, donc un bouton de demarrage en `A[0]` - ce
    predicat rend **11 tests, 0 echec**. Il est vert sur la position qui compte, parce que le premier
    bouton d'un catalogue est celui du jeu en cours, et redemarrer son propre jeu n'est pas changer de
    jeu. Le predicat retenu ajoute une seconde comparaison, le **recul du compte de manches
    resolues**, et rougit alors en nommant l'ecran et le bouton. Trois autres formes d'elargissement
    ont ete essayees et chacune est ecartee par un chiffre, jamais par un raisonnement : "la partie
    n'est plus le meme objet" rougit sur **sept ecrans legitimes**, c'est-a-dire sur le jeu lui-meme,
    puisque presser une proposition remplace l'objet de session ; "la partie n'a pas avance" rougit
    sur le second appui de la meme mauvaise reponse, le compteur d'essais expose etant un ensemble ;
    la fusion des deux predicats en un seul a la meme extension mais rend un message qui ne dit pas
    quel geste a eu lieu. Il **corrige au passage un argument faux de son propre avis** - la fin
    naturelle d'une partie etait deja comptee parmi les destructions, avant tout elargissement, et le
    garde-fou n'est vert que parce que cet arret n'occupe aucune des deux positions jugees ; c'est
    devenu une dette nommee avec son declencheur. Ce qu'il apporte au-dela du predicat est la
    **contrainte de forme ecrite pour `ines-design` avant qu'elle ne dessine**, en termes de ce qui
    reste possible : un point de focus sans requete, place avant un retour vers la partie qui doit
    etre le premier arret du document, les boutons de demarrage apres lui - et la precision que
    l'ordre du document n'est pas l'ordre visuel, donc que la mise en page reste libre. Sa regle
    n'est pas ecrite ici, elle est due par le lot de S13-6, et l'ADR nomme le vert facile qui
    l'attend : le recensement de mise a l'epreuve restera vert sans que la clause neuve soit
    eprouvee, la classe ayant deja son contre-exemple. Ce n'est pas une regle ArchUnit, le compte
    reste a **28** avec sa methode ; aucun test n'est ajoute par ce lot, la suite reste a **475**.

41. **ADR-0043** et **ADR-0044**, du sprint 14, se lisent ensemble : ce sont les deux moities du
    contrat de la barre d'entree par niveau, l'une sur ce que le balisage declare, l'autre sur ou vit
    la selection. Ils partagent surtout un point de methode que la suite de ce depot devrait garder :
    **leurs regles sont ecrites dans le commit qui les accepte**, et pas confiees a un lot futur. Le
    motif n'est pas doctrinal, il est mesure le meme jour - `ADR-0042` avait confie sa regle "au lot de
    S13-6", `DEC-0032` a annule ce lot, et la regle n'existait dans aucun fichier Java trois sprints
    plus tard. `ADR-0043` tranche la question que `ines-design` avait routee (quelle valeur de
    `data-role` pour une puce de niveau) en essayant les deux valeurs disponibles sur le depot reel
    plutot qu'en raisonnant : `sortie` fait echouer la clause de libelle unique d'`ADR-0022`, `action`
    fait echouer la saillance d'`ADR-0017` avec **16 ecarts sur quatre ecrans** - les deux clauses
    tirent en sens contraire, et c'est ce qui rend le troisieme role obligatoire plutot que commode.
    `ADR-0044` ajoute la **29e regle ArchUnit** du depot, la premiere depuis s06 (2026-08-07,
    `ADR-0019`) : aucun adaptateur de
    rendu ne lit le descripteur de contenu, mesuree mordante sur la production mutee (le filtre par
    niveau ecrit dans `PartieController`, 1 echec nommant la methode et la ligne). Il nomme lui-meme
    le chemin qu'ArchUnit ne peut pas voir - un `th:if` dans un gabarit - et le ferme par une seconde
    clause dans `adapter-web`, parce qu'une regle qui ferme une porte sur deux ne ferme rien. Les deux
    ADR ensemble portent la suite de **489 a 495 tests** et les regles de **28 a 29**, methodes dans
    `squad/sprints/s14-points-d-entree-par-niveau/confirmation-archunit.md`.

42. **ADR-0045**, du sprint 14, est ecrit **contre une recommandation de son propre auteur**. La
    section 8.1 de l'avis de faisabilite de s14 prescrivait d'inverser le sens de derivation d'un
    garde-fou de catalogue et concluait "pas d'ADR" ; `lea-dev` a livre le mecanisme et signale que la
    derivation **retire une propriete** que les cinq methodes remplacees tenaient - une mauvaise
    association ecrite dans le catalogue. Cette conclusion etait fausse, et pas pour une raison de
    forme : un mecanisme qui echange une couverture contre une autre est une decision, pas
    l'application d'un `ADR` deja accepte. L'ADR tranche avec le chiffre des deux couvertures, sur un
    espace de mutations enonce (11 entrees x 11 autres paquets = **121 editions d'un seul champ**) :
    les cinq methodes figees en attrapaient **55**, la clause derivee **0**, la clause derivee plus les
    invariants internes du catalogue **110**. Il etablit au passage la limite generale que la clause 1
    d'`ADR-0039` ne disait pas - **un critere derive de l'artefact juge ne juge que la coherence interne
    de cet artefact** - et il refuse deux remedes par des chiffres plutot que par du gout : un champ de
    niveau redondant dans l'entree n'attraperait que **66 mutations sur 121** en etant aveugle au cas
    reellement signale, et une convention de nommage rougirait des son ecriture sur **2 des 11 entrees**
    reelles. Il refuse aussi un **quatrieme recensement a aveu** dans `LesGardeFousSontMisALEpreuveTest`,
    sur une population mesuree de **2** garde-fous qui declarent deja leur trou en prose, en citant la
    non-preuve 1 d'`ADR-0039` : un aveu ne mord que sur celui qui le porte. Ce qu'il ouvre en dette,
    mesure et non suppose : le recensement de mise a l'epreuve reconnait un balayage de production au
    litteral `src/main/`, donc **12 des 107 sources de test lui sont invisibles**, dont deux vrais
    balayages derives du catalogue - elargir naivement donnerait **11 rouges dont 10 faux**. Ce n'est
    pas une regle ArchUnit et l'ADR dit pourquoi (l'objet juge est un YAML de ressources) : le compte
    reste a **29 regles**, la suite passe de **502 a 506 tests**.
