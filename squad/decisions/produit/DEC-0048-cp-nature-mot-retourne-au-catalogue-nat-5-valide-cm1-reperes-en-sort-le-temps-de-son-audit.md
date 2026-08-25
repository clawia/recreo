# DEC-0048 - `cp-nature-mot` retourne au catalogue (`NAT-5` VALIDE) ; `cm1-reperes` en sort le temps
# de son premier audit ; le trou de gouvernance de `DEC-0045` est nomme et ferme

- **Type** : execution de decision (retour au catalogue), mouvement de matrice, trace d'un trou de
  gouvernance et de sa fermeture
- **Date** : 2026-08-25
- **Auteur** : camille-po
- **Sprint** : s20
- **Competence programme visee** : francais, CP (cycle 2), "Distinguer les principaux types de mots
  (nom, verbe) dans une phrase tres simple" (section 3) ; transverse - pas de contenu pedagogique
  (sections 4 et 5).
- **Statut** : Acceptee

## Contexte

`nadia-prof` a reaudite `bandit-manchot-nature-mot-cp` sur l'increment livre de `S20-1`
(`round-two` a `f4e0808`, branche `hs-nadia-arbitrages`, commit `6f03de8`, non fusionnee) et
prononce **VALIDE**. `NAT-9` etait deja tombee le matin meme sur l'arbitrage de l'accompagnement
durable du CP ; `NAT-5` tombe ce soir sur mesure. La condition de retour posee par `DEC-0045`,
section 2, est remplie et le geste m'appartient.

En construisant son catalogue d'audit, elle a par ailleurs trouve que `bandit-manchot-reperes-cm1`
(livre par `S20-2`, fusionne a `f4e0808`) est expose dans `catalogue.yaml` alors que le bloc de
validation de sa fiche est vide - aucun audit, aucun verdict. Elle route ce fait sans y toucher.

## 1. Verification personnelle du verdict, avant de faire bouger quoi que ce soit

Je ne fais bouger aucune ligne sur la foi d'un titre de commit (`squad/README.md`, section 9,
etape 6) : j'ai lu la fiche entiere, pas seulement son dernier verdict.

- **Le critere juge est le bon.** `nadia-prof` a refonde `NAT-5` le matin meme sur l'asymetrie
  tache constante / designation qui change a chaque manche (dix interventions d'adulte par
  partie) ; c'est ce critere-la, et pas le critere d'origine deja affaibli, qu'elle mesure ce soir.
  Le libelle de `NAT-5` n'a pas bouge entre les deux dates - confirme, mot pour mot : le mot cible
  est designe autrement que par les guillemets seuls, et jamais par la couleur seule.
- **La mesure est complete et honnete sur sa propre limite.** Cinq parties, 71 lectures, seize
  items sur seize vus a la reference, au repos et apres un echec provoque. Marqueur present 16/16,
  mot marque identique au mot entre guillemets 16/16 (verifie a la main, elle le nomme elle-meme
  comme une propriete qu'aucun test ne garde), part designee presente exactement une fois 16/16,
  marqueur encore present apres un echec 14/14, defilement nul sur les 71 lectures.
- **Le cout sur les dix-sept autres jeux est prouve, pas seulement mesure.** Sa premiere mesure
  etait fausse (compteur d'entrees, pas de lancements) et elle le dit elle-meme avant que je n'aie
  a le trouver - dix-huit jeux relances, soixante-douze manches, zero des dix-sept autres ne rend le
  marqueur, et le delta CSS entier est dix-sept lignes ajoutees, zero supprimee, un seul selecteur.
  Une preuve structurelle (aucun autre enonce ne contient de span) au-dessus d'un releve.
- **Elle nomme elle-meme la part de son annonce qui etait fausse plutot que de la laisser
  passer.** La boite n'est pas un canal non colore comme on le lui avait presente (contraste 1,04:1,
  disparait en achromatopsie) ; elle le mesure, le dit, et explique pourquoi sa condition reste
  neanmoins satisfaite (l'agrandissement de 15 % survit seul a la suppression de toute couleur). Je
  retiens son raisonnement : `NAT-5` demandait qu'un canal survive a la couleur seule, pas que tous
  les canaux annonces le fassent.
- **La methode de mesure ne touche pas au depot.** Jar construit depuis l'arbre tel quel, catalogue
  substitue dans une copie du jar hors du depot, `git status` vide avant et apres. L'objet audite
  est l'artefact tel qu'il sera une fois le jeu rendu au catalogue - le bon objet, verifie plutot que
  suppose.

**Ma lecture confirme le verdict : VALIDE, aucune reserve bloquante restante.** `NAT-1` a `NAT-4`
etaient deja tenues ou retirees au 2026-08-24 ; `NAT-9` est tombee ce matin ; `NAT-5` tombe ce soir.
`NAT-10`, `NAT-11` restent ouvertes, non bloquantes, inchangees. `NAT-12`, neuve et non bloquante,
est posee sur la boite (contraste de luminance nul) sans etre retenue contre l'increment - motif de
`nadia-prof` que je retiens : une condition ne se durcit pas apres coup sur une propriete plus fine
que celle qu'elle demandait, sous peine de transformer la porte en caprice.

## 2. `cp-nature-mot` retourne au catalogue

La condition de `DEC-0045`, section 2, est remplie : `NAT-5` et `NAT-9` sont traitees, `nadia-prof`
a reaudite l'increment et ne nomme plus aucune reserve bloquante. L'entree
`id: bandit-manchot-nature-mot-cp` revient dans
`adapters/adapter-content/src/main/resources/catalogue.yaml`, exactement dans la forme que le
commit de retrait de `DEC-0045` en avait retiree - meme mecanisme que le retrait lui-meme, execution
dans le meme geste que cette decision et non comme une story a planifier. Je ne modifie pas
`catalogue.yaml` moi-meme : il vit sous `adapters/`, hors de mon perimetre d'ecriture. Action routee
a `sofia-sm`.

## 3. Mouvement de matrice - perimetre exact de ce `couvert-valide`

**"Distinguer les principaux types de mots (nom, verbe) dans une phrase tres simple"** (francais
CP) passe `en cours` -> `couvert-valide`.

**Ce que ce statut recouvre** : le paquet complet de seize items, la designation du mot cible sans
recours a la couleur seule, la mise en page a la reference (744 x 551, zero item sous le pli, marge
minimale 38,62 px), le cout nul verifie sur les dix-sept autres jeux du catalogue.
**Ce que ce statut ne recouvre pas, nomme pour que personne ne le croie couvert** (dans les mots de
`nadia-prof`, repris sans les affaiblir) :

- **`NAT-10`, `NAT-11`** - deux observations non bloquantes deja ouvertes au 2026-08-24, inchangees.
- **`NAT-12`, neuve, non bloquante** - la boite du marquage ne porte pratiquement aucun contraste de
  luminance (1,04:1 contre le fond) ; elle disparait en rendu monochrome, mais la designation
  survit par l'agrandissement seul, ce que `NAT-5` exigeait. Corrigible a cout nul si `ines-design`
  le propose (la regle porte un rayon d'angle sans bordure) ; je ne le lui demande pas ici, je nomme
  la piste.
- **Ce qu'un lecteur d'ecran annonce** de l'enonce desormais segmente en span par `ADR-0062` -
  angle mort qui n'existait pas avant `S20-1`, non observe faute d'outil dans cette squad, non
  qualifie de defaut faute d'element pour l'affirmer.
- **Toute largeur de viewport autre que 744 px.** La bande 576-719 px est sortie de la cible
  (`DEC-0040`, amendement du 2026-08-25) ; rien n'est mesure ni requis en dehors de la reference.

**Le chiffre, avec sa base.** Compte verifie ligne par ligne sur `squad/programme/MATRICE-
couverture.md` plutot que suppose : le denominateur atteignable reste **43** sur **47** (inchange,
aucune ligne `non couvrable en l'etat` ne bouge). Le compte de lignes `couvert-valide` passe de
**13** a **14**. **13/43 (30,2 %) devient 14/43 (32,6 %)** - un mouvement de couverture reel, le
premier depuis la cloture de s19 : un increment joue et audite, verdict VALIDE sans reserve
bloquante.

## 4. Le trou de gouvernance : `bandit-manchot-reperes-cm1` expose sans audit

### Le fait, verifie et non suppose

`bandit-manchot-reperes-cm1` porte une entree vivante dans `catalogue.yaml` a `f4e0808` (verifie :
`id: bandit-manchot-reperes-cm1`, ligne 99). `squad/conformite/FICHE-bandit-manchot-reperes-cm1.md`
porte un bloc de validation vide - verifie, aucun verdict, aucune date, la mention rappelant que ce
bloc reste vide tant qu'aucun increment n'a ete livre et joue est encore litteralement vraie a
cette date. `DEC-0045`, section 3, que j'ai ecrite il y a deux jours, dit en toutes lettres que
l'absence de tout audit sur l'increment servi signifie que le jeu ne doit pas etre expose. Le trou
que cette decision existait pour fermer est rouvert, sur un jeu different, en un sprint.

### Ce qui a manque, et ce n'est pas la regle elle-meme

**Le texte de `DEC-0045` couvre deja ce cas exact** : l'absence de tout audit sur l'increment servi
n'est pas une clause que j'ai oublie d'ecrire, elle est ecrite. Ce qui manque est ailleurs, et je le
nomme precisement plutot que de dire qu'il aurait fallu faire attention :

**J'ai nomme un seul des deux evenements qui declenchent la meme obligation.** `DEC-0045`, section
4, dit que quiconque ecrit ou fusionne un verdict de `nadia-prof` sait, sans arbitrage
supplementaire, que l'entree doit en sortir au meme geste. Cette phrase couvre le cas ou une fiche
existante recoit un verdict qui rend une reserve bloquante. **Elle ne couvre pas le cas ou une
entree neuve entre au catalogue avant qu'aucun verdict n'existe** - ce n'est pas le meme geste :
celui-ci est declenche par la fusion d'un lot de code (`sofia-sm` fusionnant `S20-2`), pas par la
fusion d'un verdict. Je n'ai jamais ecrit que ce second geste porte la meme obligation, alors que le
motif est identique dans les deux sens - une entree de catalogue ne doit refleter que ce que
`nadia-prof` a effectivement juge, qu'elle le juge favorablement (l'y faire entrer) ou
defavorablement (l'en faire sortir).
**Le second angle mort tient au moment ou je place le controle.** J'avais ecrit que `sofia-sm`
verifierait en dernier ressort a la revue de cloture (`DEC-0045`, section 4) - un filet de secours,
pas le controle principal. Un jeu peut donc rester expose sans audit pendant tout un sprint avant
que quiconque ne le remarque - exactement ce qui vient de se produire, trouve par `nadia-prof` en
construisant un catalogue d'audit pour un autre jeu, pas par un controle du depot. C'est la meme
famille que la dette qu'`ADR-0061` nomme deja, non comme une hypothese mais comme un fait qui vient
de se confirmer une seconde fois : la regle generale de `DEC-0045` section 3 n'est pas encodee,
faute de forme lisible par machine dans les fiches.

### Ce que je fais maintenant, sans attendre le verdict de `cm1-reperes`

**J'applique ma propre regle immediatement, et ce n'est pas un jugement sur le contenu.**
`nadia-prof` audite `cm1-reperes` en parallele de ce document ; je ne prejuge ni du sens ni de la
date de son verdict. Mais la condition qui retire une entree du catalogue n'est pas que le contenu
est mauvais - c'est qu'aucun verdict n'existe - et cette condition est vraie au moment ou j'ecris
ceci, independamment de tout jugement de fond. Retenir l'exposition parce qu'un audit favorable est
probable ou imminent serait exactement le genre d'exception de circonstance que je me suis
interdite a moi-meme (`DEC-0044`, section 1 : je ne durcis pas ma barre quand elle me coute, je ne
l'assouplis pas non plus quand la coincidence m'arrange).

**L'entree `id: bandit-manchot-reperes-cm1` sort de `catalogue.yaml`**, meme mecanisme que tout
retrait deja pratique sur ce produit (absence d'entree, sans drapeau), execution routee a
`sofia-sm` dans le meme geste que cette decision. **Le paquet de contenu reste au depot, inchange** :
ce n'est pas un retrait de contenu, c'est un retrait d'exposition en attendant un premier verdict.
**Le retour est automatique des que `nadia-prof` rend un verdict qui n'est pas un REFUSE** - je ne
pose aucune troisieme condition, je n'invente rien de plus que ce que `DEC-0045` dit deja.

### Ce que je route pour que ce trou-la ne se rouvre pas une troisieme fois

- **`sofia-sm`** : retirer `bandit-manchot-reperes-cm1` de `catalogue.yaml` dans le meme geste que
  la fusion de cette decision (section 4) et y ajouter `bandit-manchot-nature-mot-cp` (section 2) -
  deux editions dans un seul commit, pas deux gestes separes. Je lui route par ailleurs, sans
  trancher la forme, le renforcement du controle : le point de verification qu'une entree neuve de
  catalogue a bien un verdict de `nadia-prof` derriere elle doit exister a chaque fusion qui touche
  `catalogue.yaml`, pas seulement a la revue de cloture de sprint - c'est un choix de ceremonie qui
  lui appartient (`squad/README.md`, section 9).
- **`theo-archi`** et **`nadia-prof`** : la dette nommee par `ADR-0061` (une forme lisible par
  machine pour l'etat d'audit d'une fiche) vient de produire son second incident reel en deux
  jours. Je ne la reinstruit pas moi-meme ici - elle est deja routee - mais je nomme que son cout
  d'inaction vient de doubler.
- **`nadia-prof`** : rien de plus sur `cp-nature-mot`, son verdict est acquis. Continuer l'audit de
  `cm1-reperes` deja engage, a son rythme.

## Ce que je ne fais pas

- Je ne prejuge ni ne devine le verdict de `nadia-prof` sur `cm1-reperes` : le retrait de son
  entree est une consequence mecanique de l'absence de verdict, pas un jugement de contenu.
- Je ne modifie pas `catalogue.yaml` moi-meme : les deux editions sont routees a `sofia-sm`.
- Je ne fais bouger aucune ligne de matrice pour `cm1-reperes` : elle reste `en cours`, inchangee.
- Je n'ecris aucune regle executable ni ne choisis sa forme : routee a `theo-archi`.
- Je n'amende pas `squad/README.md` moi-meme : le choix de ceremonie appartient a `sofia-sm`.

## Impact

- `squad/decisions/produit/DEC-0048-...md` (ce fichier) : cree.
- `squad/decisions/produit/DEC-index.md` : ligne `DEC-0048` ajoutee ; note ajoutee sous la ligne
  `DEC-0045`.
- `squad/decisions/produit/DEC-0045-...md` : note datee ajoutee, renvoyant ici pour le detail du
  trou de gouvernance et sa fermeture.
- `adapters/adapter-content/src/main/resources/catalogue.yaml` : `bandit-manchot-nature-mot-cp`
  ajoutee, `bandit-manchot-reperes-cm1` retiree - execution par `sofia-sm` dans le meme geste que la
  fusion de cette decision.
- `squad/programme/MATRICE-couverture.md` : ligne "Distinguer les principaux types de mots"
  `en cours` -> `couvert-valide` ; synthese chiffree mise a jour, 14/43 (32,6 %).
- `docs/backlog/backlog.md` : `S20-1` mise a jour (Done pedagogique acquis, geste 3 execute).
- Aucun fichier sous `domain/`, `application/`, `adapters/` (hors le catalogue route) ni
  `bootstrap/` n'est modifie par cette decision.
