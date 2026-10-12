# DEC-0119 - Accueil par matiere : la vue par defaut d'une classe choisie passe de V2 a V1 (le choix de la matiere d'abord)

- **Type** : decision de produit (vue par defaut, criteres d'acceptation, condition de fusion)
- **Date** : 2026-10-12
- **Auteur** : `camille-po`
- **Sprint** : hors sprint (chantier ouvert par `DEC-0117`, qui amende la decision 1 de sa section 2 ; aucun sprint ouvert)
- **Competence programme visee** : transverse - pas de contenu pedagogique. Aucune ligne de `MATRICE-couverture.md`
  ne change.
- **Statut** : **Acceptee** sur les mots de l'utilisatrice (section 1). **Non appliquee** : le code est inchange, la
  story `HS-ACCUEIL-DEFAUT-V1` (section 6) attend un proprietaire de `sofia-sm`. **Condition de fusion : l'audit de
  `nadia-prof` precede la fusion sur `round-two`** (`DEC-0117` section 11, section 5 ci-dessous).
- **Lien** : `DEC-0117` (sections 2 et 11) ; `DEC-0118` ; `DEC-0028` ; `ADR-0105` ; `ADR-0106` ;
  `squad/conformite/ARBITRAGE-PREALABLE-accueil-par-matiere.md` (N4) ;
  `squad/conformite/FICHE-accueil-par-matiere.md` ; `docs/backlog/backlog.md`, section "Chantier hors sprint : accueil
  par matiere", stories `HS-ACCUEIL-DEFAUT-V1` et `HS-ACCUEIL-MESURE-DEFAUT`.

## 1. La demande, mots exacts

Utilisatrice, 2026-10-12, mots recus directement dans la session par l'agent qui m'a lance, qui les atteste (forme 2 de
`DEC-0116` A.2) : *"par defaut apres avoir choisi sa classe montre le choix de la matiere"*.

**Le sens que je retiens, et pourquoi** : le "choix de la matiere" est l'etage 1 de V1 (une tuile par matiere, `DEC-0117`
section 2) ; c'est le seul ecran du depot qui demande une matiere. V2 ne la demande pas : elle la nomme au-dessus de cartes
deja toutes affichees. Si elle voulait autre chose, c'est une nouvelle demande, pas une lecture de celle-ci.

## 2. Ce que le depot dit aujourd'hui, lu avant d'ecrire

- `VueDeLAccueil.PAR_DEFAUT = V2` ; `EtatDePartieEnSession.vue()` rend cette valeur quand la session n'a rien ecrit ;
  `PartieController.accueilDuNiveau` choisit l'ecran `v1-etage1` ou `v2` sur ce seul resultat. Une seule valeur porte la
  vue par defaut.
- Le gabarit `accueil.html` porte trois boutons de bascule, dont le libelle dit la destination : en V2 **"Par matière"**
  (vers V1), a l'etage 1 de V1 **"Tout voir"** (vers V2), a l'etage 2 **"Par matière"** (retour a l'etage 1, fleche).
  Ces libelles sont arbitres par `nadia-prof` ; **je n'en change aucun**.
- `ADR-0105` decision 2 dit *"Valeur absente = vue par defaut"* sans nommer la vue ; son contexte la nomme (*"vue par
  defaut la seconde"*), comme description de la demande du 2026-10-11.
- Tests et outil qui ecrivent "V2 par defaut" : `AccueilParMatiereTest` (*"la vue par defaut est V2"*),
  `VueDeLAccueilEnSessionTest` (`PAR_DEFAUT` egal a `V2`), `outils/mesurer-accueil.py` (charge `/niveaux/<niveau>` en
  session neuve et traite ce qu'il y trouve comme V2). Constat, pas decision : le perimetre exact des tests a changer est
  a `lea-dev` et `yanis-qa`.

## 3. Decision

1. **La vue par defaut d'une session qui n'a rien choisi est V1** : apres le choix de la classe, l'enfant voit l'etage 1
   (une tuile par matiere du niveau). Ceci **amende la decision 1 de `DEC-0117` section 2** (*"La vue par defaut est
   V2"*) ; la section 2 n'est pas reecrite, elle est annotee.
2. **V2 reste accessible** par le bouton de bascule. Le bouton, ses trois libelles et la regle "la route porte la vue
   visee" (`ADR-0105` decision 2) ne changent pas.
3. **Le choix reste en session HTTP** et disparait avec elle (`DEC-0028`). Rien n'est persiste.
4. **`/` ne change pas** : il demande la classe d'abord (`Q-U1`, option (c)), sans liste de jeux.
5. **Rien dans le catalogue, les paquets ou la matrice ne change.** Les 146 jeux restent atteignables dans les deux vues.

## 4. Criteres d'acceptation

Mesures sur le jar, a 744 x 551 (`DEC-0040`), trois niveaux, avec la methode de `DEC-0117` section 4 (une carte ou une
tuile est visible si `top` >= 0 et `bottom` <= `innerHeight`). Les nombres sont derives du catalogue servi, jamais ecrits
dans un test. Chaque critere dit ce qu'il donne **aujourd'hui** (jar de `round-two`, `c274a2db`, par lecture du code et des
tests ; je n'ai lance ni test ni jar pour cette decision).

**A. Ce que voit l'enfant juste apres le choix de sa classe.**

- A1. Etant donne une requete sans cookie de session, quand elle charge `/niveaux/CP` (puis `/niveaux/CE1`, `/niveaux/CM1`),
  alors la page porte une tuile par matiere distincte du niveau (CP 8, CE1 2, CM1 8 au 2026-10-11, derives), chacune avec
  son libelle court en texte, **0 carte de jeu** (`button.demarrer`) et **0 bandeau de matiere**. **Aujourd'hui** : V2, donc
  0 tuile et des cartes : **rouge**.
- A2. Meme page : toutes les tuiles sont entierement visibles sans defiler. **Aujourd'hui** : sans objet sur la page par
  defaut ; la mesure de `rapport-qualite.md` donne 8 / 2 / 8 sur l'etage 1 atteint par la bascule, donc **vert sur
  l'etage 1, plancher de non-regression** et non correctif.
- A3. Meme page : le nombre de jeux atteignables en sommant les etages 2 de toutes les tuiles est celui du niveau (CP 58,
  CE1 30, CM1 58 au 2026-10-12), aucune tuile n'a 0 jeu. **Aujourd'hui** : tenu en V1 (`HS-ACCUEIL-V1`, "Rien d'orphelin") ;
  vert, plancher.
- A4. Etant donne un appui sur une tuile, alors l'etage 2 de la matiere est rendu, avec son libelle et ses cartes, dont
  `min(n, 6)` entierement visibles. **Aujourd'hui** : vert (`HS-ACCUEIL-V1`), plancher.

**B. Le bouton de bascule, dans chaque sens.**

- B1. Etant donne la page de A1, alors elle porte **un seul** bouton de bascule dans la zone de l'accueil (aucun dans
  l'en-tete) et son texte est exactement **"Tout voir"**. **Aujourd'hui** : la page par defaut porte "Par matière" :
  **rouge**.
- B2. Etant donne un appui sur "Tout voir", alors la page rendue est V2 du meme niveau (bandeaux, cartes de tout le
  niveau), la puce de classe active est la meme avant et apres, et le texte du bouton est exactement **"Par matière"**.
  **Aujourd'hui** : le comportement existe, dans l'autre ordre ; la paire de libelles est inchangee : vert pour la paire,
  rouge pour l'etat de depart.
- B3. Etant donne V2 puis un appui sur "Par matière", alors la page rendue est l'etage 1 de V1 du meme niveau, bouton
  "Tout voir". Etant donne l'etage 2, alors le bouton "Par matière" ramene a l'etage 1 sans quitter l'accueil.
  **Aujourd'hui** : vert.
- B4. Le bouton est absent d'un ecran de manche, de fin de partie et de confirmation, et ni lui ni une tuile ne porte le
  mot "Les jeux" (`BARRE-5`). **Aujourd'hui** : vert (`APM-7`), plancher.

**C. Le retour depuis un jeu.**

- C1. Etant donne une session neuve **sans aucun appui sur le bouton**, une partie lancee depuis un etage 2, quand
  l'enfant appuie sur `Les jeux` (sur une manche : puis sur `Les jeux` de l'ecran de confirmation ; en fin de partie : un
  seul appui), alors l'ecran rendu est le choix de la classe : le texte *"Choisis ta classe."*, aucune carte, aucune tuile,
  aucun bandeau, aucun bouton de vue. Quand elle appuie ensuite sur une puce de classe, **quelle que soit la classe**, alors
  la page rendue est l'**etage 1 de V1**. **Aujourd'hui** : la puce rend V2 : **rouge** (la moitie "choix de la classe"
  est verte, `APM-8`, `APM-9`).
- C2. Etant donne une session qui a appuye sur "Tout voir" (V2) puis une partie, alors apres `Les jeux` et une puce de
  classe, la page rendue est V2, bouton "Par matière". **Aujourd'hui** : vert (`APM-9`, vue retenue), plancher ; c'est le
  garde-fou contre une implementation qui remettrait la vue par defaut apres chaque partie.
- C3. Etant donne une partie en cours, une puce de classe pressee ou la bascule envoyee, alors la confirmation *"Si tu
  pars, ta partie s'arrête."* est rendue et la partie reste en cours. **Aujourd'hui** : vert (`APM-11`), plancher.

**D. La session.**

- D1. Etant donne une requete sans cookie de session, alors V1. Etant donne une session invalidee ou expiree, alors V1.
  **Aujourd'hui** : V2 : **rouge**.
- D2. Etant donne une session qui a bascule en V2, quand elle charge `/niveaux/CE1`, alors la page est en V2 ; etant donne
  une session qui a bascule en V2 puis de nouveau en V1, alors V1. **Aujourd'hui** : vert (la route ecrit la vue
  qu'elle porte, `ADR-0105` decision 2, et la session la relit) ; plancher, et critere contre une implementation qui
  ne retiendrait plus le choix explicite de V2 une fois V1 devenue la valeur absente.
- D3. Rien de persiste : les reponses de `/niveaux/CP` avant et apres une bascule n'ont pour seul `Set-Cookie` que
  `JSESSIONID`, sans `Max-Age` ni `Expires` ; le JS ne contient ni `localStorage`, ni `sessionStorage`, ni
  `document.cookie`. **Aujourd'hui** : vert (`DEC-0028`), plancher.
- D4. `abandonner()` n'efface pas la vue choisie (`ADR-0105`). **Aujourd'hui** : vert, plancher.

**E. Hors perimetre, inchange.** `/` rend *"Choisis ta classe."* sans liste (`Q-U1`). Completude : l'ensemble des
identifiants atteints dans chaque vue est egal a celui du catalogue pour le niveau (`HS-ACCUEIL-COMPLETUDE`).

## 5. Condition de fusion : l'audit de `nadia-prof` precede la fusion

Regle de `DEC-0117` section 11, sur les mots de l'utilisatrice (*"non nadia passe avant la fusion comme ca on peut re
corrgier si besoin"*). Pour ce changement : **rien n'est fusionne sur `round-two` tant que `nadia-prof` n'a pas rendu son
verdict sur la branche de l'implementation, jouee sur le jar.** Un `REFUSE` se corrige par une story corrective avant
fusion.

Le Done pedagogique existant (`VALIDE SOUS RESERVE`, arbre `def23ead` augmente d'`APM-12`) a ete prononce sur une vue par
defaut V2. **Je ne dis pas qu'il tombe, ni qu'il tient** : c'est le jugement de `nadia-prof`. Je ne touche pas le bloc de
validation de `FICHE-accueil-par-matiere.md`.

## 6. Routage : une decision qui change le code a un proprietaire

- **A `sofia-sm`** : affecter les deux stories de la section du backlog. `HS-ACCUEIL-DEFAUT-V1` (developpeur au choix de
  `sofia-sm`, apres l'avis de `theo-archi` sur les points ci-dessous) ; `HS-ACCUEIL-MESURE-DEFAUT` (`yanis-qa` : l'outil
  `outils/mesurer-accueil.py` lit la page par defaut comme V2). Verser l'entree au journal : fait par moi, ci-dessous.
  Capacite de `nadia-prof` : je n'ecris aucun prix (`PAR-10`).
- **A `theo-archi`** : `ADR-0105` (contexte : *"vue par defaut la seconde"*) dit-il V2 par defaut ou decrit-il seulement la
  demande ? Sa decision 2 (*"Valeur absente = vue par defaut"*) ne nomme pas la vue. A lui de dire s'il amende, annote
  ou confirme ; et si le changement de la valeur par defaut appelle une regle. Je ne tranche pas.
- **A `nadia-prof`** : (a) la V1 par defaut change-t-elle son verdict ? (b) sa condition `N4` (*"V2 par defaut :
  ACCEPTEE"*, arbitrage prealable) et sa lecture de `S15-5` portaient sur V2 ; sa raison pour O2 (*"A la vue par defaut, la
  coche montrerait un etat que l'enfant n'a pas produit"*) vaut-elle pour l'etage 1 ? (c) "8 me suffit" (`audit-nadia.md`
  P8) a ete dit pour l'ecran **que l'enfant voit en premier** ; vaut-il pour V2 atteinte par un bouton ? (d) a 390 px,
  l'etage 1 demande du defilement (4 tuiles sur 8, `rapport-qualite.md` E3) : c'est maintenant la page par defaut ; elle
  dit si cela change quelque chose. Je ne fixe aucune valeur a 390 px (aucune mesure ne m'y autorise).

## 7. `DEC-0118` relue : ce qui devient plancher, ce qui change

Annote dans `DEC-0118` (section 8), non reecrite.

- **Ce qui ne change pas** : la valeur **8** cartes visibles a 744 x 551 en V2, sa mesure (8 / 8 / 8), son motif
  (`nadia-prof` P8), son statut (plancher de non-regression, deja vert) et l'absence de seuil en code.
- **Ce qui change** : le seuil ne porte plus sur "la vue par defaut" mais sur **V2 atteinte par "Tout voir"**. Le mot "vue
  par defaut" de son titre et de sa section 1 est lu comme "V2". La commande de rejeu `--seuil-v2 8` doit charger V2 par la
  bascule (`HS-ACCUEIL-MESURE-DEFAUT`), sinon elle mesure l'etage 1.
- **Ce qui devient le critere de la vue par defaut** : A2 (toutes les tuiles visibles) et A4 (`min(n, 6)`), deja
  verts sur l'etage 1 : planchers, ecrits comme tels, pas comme objectifs atteints.
- **Report** : ce n'est pas un report. Le seuil de 8 n'est pas baisse ; aucun sujet n'est reporte une seconde fois.

## 8. Limites

Ne prononce aucun Done. Ne touche ni catalogue, ni paquet, ni matrice, ni code, ni le bloc de validation de la fiche.
Je n'ai lance ni test ni jar : les mentions "vert" et "rouge" sont des lectures du code, des tests et des rapports
cites, a rejouer par `yanis-qa`.

## Impact

- `squad/decisions/produit/DEC-0119-...md` (ce fichier) : cree ; `DEC-index.md` : ligne ajoutee et annotation.
- `DEC-0117` : section 12 (annotation, statut `Acceptee` inchange). `DEC-0118` : section 8 (annotation).
- `docs/backlog/backlog.md` : stories `HS-ACCUEIL-DEFAUT-V1` et `HS-ACCUEIL-MESURE-DEFAUT`, annotations de
  `HS-ACCUEIL-V2` et `HS-ACCUEIL-BASCULE`.
- `squad/conformite/FICHE-accueil-par-matiere.md` : criteres de `camille-po` seulement.
- `squad/rapports/JOURNAL-gouvernance-hors-sprint.md` : entree du 2026-10-12.
- `domain/`, `application/`, `adapters/`, `bootstrap/`, catalogue, paquets, matrice : **inchanges**.
