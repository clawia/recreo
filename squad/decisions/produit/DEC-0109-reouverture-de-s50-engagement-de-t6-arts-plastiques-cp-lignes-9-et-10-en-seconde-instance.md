# DEC-0109 - Reouverture de s50 : `T6` (arts plastiques CP, lignes `ART-CP-09` et `ART-CP-10`)
# entre au perimetre, en seconde instance de `nadia-prof`, pour lever le rouge qui bloquait
# la porte technique de l'ensemble du sprint

- **Type** : decision d'ouverture de perimetre (perimetre, chiffrage, routage), suite directe de
  `DEC-0108` section 9.
- **Date** : 2026-10-04
- **Auteur** : `camille-po`
- **Sprint** : s50 (reouvert - aucun `RAPPORT.md` ni `retro.md` n'existe pour s50 : la cloture
  nommee en titre de la section 9 de `DEC-0108` est la mesure qui a trouve le rouge de `build`,
  pas une cloture de ceremonie executee. Verifie au disque, pas suppose :
  `squad/sprints/s50-francais-ce1-sourcing-bloque-par-cloudflare/` ne contient que `planning.md`,
  `rapport-qualite.md` et `confirmation-archunit.md`).
- **Competence programme visee** : `ART-CP-09` (*"Decrire une oeuvre : dire ce qu'on y voit
  (sujet, couleurs, formes, materiaux)"*) et `ART-CP-10` (*"Reconnaitre quelques oeuvres deja
  rencontrees et les rapprocher les unes des autres"*),
  `squad/programme/arts-plastiques/competences-cp.md` lignes 45-46.
- **Statut** : Acceptee.
- **Lien** : `DEC-0108` (section 9, amendement de cloture qui nomme le rouge bloquant) ;
  `ADR-0082` (section "Le cout et le decoupage en taches", tache `T6` ; section 7, dette assumee) ;
  `ADR-0073` (clauses 3, 5, 6) ; `squad/corpus/CHOIX-oeuvres-candidates-T5.md` (sections 3, 5, 8) ;
  `squad/corpus/oeuvres-domaine-public.yaml` (commit `56b6b77c`) ;
  `squad/conformite/ARBITRAGE-PREALABLE-s28-ce-qui-ferme-une-ligne-et-le-prix-des-quatre-lots.md`
  (section 4.3, bareme) ; `squad/decisions/produit/DEC-0095-...md` (ligne `S39-OUTILS`, precedent de
  tarif non confirme sur arts plastiques) ; `squad/conformite/ARBITRAGE-PREALABLE-s29-le-prix-des-
  quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md` (section 13, `PAR-2`, `PAR-8`, `PAR-10`).

---

## 0. Fait verifie avant toute decision : les 28 oeuvres sont au registre et sur disque

`56b6b77c` ([THEO], s50) ajoute les trois fichiers manquants (`oeuvre-chambre`,
`oeuvre-rue-sous-la-pluie`, `oeuvre-grande-jatte`) a `squad/corpus/oeuvres-domaine-public.yaml`.
Verifie par moi, pas repris de confiance :

- **28 entrees** dans le registre (`grep -c identifiant:` rend 28), **28 fichiers `.jpg`** sous
  `adapters/adapter-web/src/main/resources/static/oeuvres/`, un fichier par entree, aucun
  manquant.
- Les **8 identifiants** que `CHOIX-oeuvres-candidates-T5.md` section 8.4 assigne a `ART-CP-09`
  (`oeuvre-hippopotame`, `oeuvre-moissonneurs`, `oeuvre-chambre`, `oeuvre-quatre-saisons`,
  `oeuvre-grande-vague`, `oeuvre-marche-aux-chevaux`, `oeuvre-rue-sous-la-pluie`,
  `oeuvre-femme-au-luth`) sont tous les huit dans le registre.
- Les **3 identifiants** que `ART-CP-10` ajoute (`oeuvre-champ-de-ble`,
  `oeuvre-averse-sur-le-pont`, `oeuvre-jeune-femme-aiguiere`) y sont egalement.
- Ce commit **ne ferme rien par lui-meme** : `LIllustrationDeContenuResoutEtAucuneNeDortTest`
  reste a 2 echecs sur 11 apres ce commit, parce qu'aucun paquet reel ne declare encore un
  identifiant `oeuvre-` (verifie par `theo-archi`, `confirmation-archunit.md`, commit `ce72dc03`,
  et confirme par moi en relisant ce meme fichier). **`T6` reste donc, par la propre definition de
  `DEC-0108` section 1, un vrai developpement de contenu - pas un geste de registre.**

## 1. Decision - `T6` entre au perimetre de s50, perimetre : les deux lignes ensemble, un seul paquet

**J'engage `ART-CP-09` et `ART-CP-10` dans un seul lot, un seul paquet.** Pas `ART-CP-09` seule :
`CHOIX-oeuvres-candidates-T5.md` section 5 est explicite - *"Deja rencontrees impose de reutiliser
le vivier de la ligne 9"* - `ART-CP-10` n'est pas une ligne independante, elle est une seconde
lecture du meme vivier plus trois oeuvres. Separer les deux ferait jouer deux paquets sur le meme
corpus pour un seul motif de calendrier, ce que rien ne justifie : le cout (section 2) est
identique a engager une ligne ou les deux, parce que l'unite de tarif est le lot de matiere et de
niveau, pas la ligne (`ARBITRAGE-PREALABLE-s28`, section 4.1 : *"le nombre de fichiers ne compte
pas"*).

**Vivier retenu** : les 11 identifiants de la section 0, strictement ceux de
`CHOIX-oeuvres-candidates-T5.md` section 8.4 (version corrigee du 2026-10-03, qui remplace
`oeuvre-laitiere` par `oeuvre-femme-au-luth`). Je ne rouvre aucun choix d'oeuvre : c'est le travail
deja fait de `nadia-prof`, verifie contre le registre reel par moi dans cette section meme.

## 2. Tarif : 2,0, sur le tarif deja publie par `nadia-prof` et applique par `theo-archi`, pas sur le
## precedent du francais CE1

**Je n'applique pas le tarif de `FRA-CE1-02`/`FRA-CE1-16` (1,0 chacune).** Ce tarif vaut parce que
`nadia-prof` est la source du referentiel de francais CE1 qu'elle a ecrit elle-meme (`DEC-0103`,
repris par `DEC-0107` section 3). **Elle n'a jamais declare etre la source des arts plastiques.**
`ADR-0082` ("Le cout et le decoupage en taches") l'ecrit comme une hypothese non confirmee, avec sa
propre reserve : *"l'hypothese 1,5 peut etre 1,0 si elle se declare source des arts plastiques"* -
non tranchee a ce jour, verifie par moi en cherchant toute declaration ulterieure (aucune trouvee).
Le precedent le plus proche et le plus recent est `S39-OUTILS` (`ART-CP-11`, outils du travail
plastique) : `DEC-0095` l'inscrit a **1,5, "arts plastiques non source, tarif non confirme par elle
sur cette ligne precise"**, et la ligne a ferme `couvert-valide` a ce tarif sans que `nadia-prof`
ne le conteste (`MATRICE-couverture.md` ligne 2056). C'est le tarif que je retiens par defaut pour
toute la matiere, faute de declaration contraire.

| Element | Prix | Motif |
|---|---|---|
| Lot arts plastiques CP, non source | 1,5 | `ARBITRAGE-PREALABLE-s28` section 4.3 ; precedent `S39-OUTILS` / `DEC-0095` |
| Supplement lot illustre (`REC-1`) | 0,5 | `ARBITRAGE-PREALABLE-s28` section 4.3 ; `ADR-0082` l'applique deja a ce meme lot |
| **Total** | **2,0** | |

**Je ne decide pas si 1,5 doit redevenir 1,0.** C'est une declaration de `nadia-prof`, pas une
arithmetique que je peux lui substituer. Si elle se declare source des arts plastiques au moment du
Done, le tarif de ce lot baisse a 1,5 retroactivement sur ce point, et je le noterai alors - je
n'anticipe pas une declaration qu'elle n'a pas faite.

**Nombre d'items : jusqu'a 20, le prix ne descend pas en dessous.** `ARBITRAGE-PREALABLE-s28`
section 4.1 : *"le prix ne descend pas sous vingt items"*. Le vivier (11 oeuvres, au moins un item
par oeuvre pour `ART-CP-09`, au moins un item par paire pour `ART-CP-10`) tient largement sous ce
plafond ; engager les deux lignes ensemble ne coute donc rien de plus qu'engager `ART-CP-09` seule
au même plancher - c'est la seconde raison, outre celle de la section 1, de ne pas les separer.

## 3. Instance : une seconde instance B, dediee, 2,0 / 5,0, et pas un remaniement de l'instance A

**Je n'ajoute pas `T6` a l'instance deja engagee par `DEC-0108`.** Elle est a 3,5 / 5,0, marge 1,5
(`DEC-0108` section 5, inchangee, verifiee de nouveau ici). `2,0 > 1,5` : ajouter `T6` a cette
instance porterait son total a 5,5, **au-dela du plafond individuel dur de 5,0** (`PAR-8`,
`ARBITRAGE-PREALABLE-s29`, section 13 : *"le plafond individuel reste 5,0 par instance"*). Ce
plafond est dur, pas une borne a negocier au cas par cas : je ne le franchis pas.

**J'ai considere et j'ecarte le remaniement** (retirer une des trois corrections a 0,5 - `GRC-CE1-
R1`, `SOU-CE1-R1` ou `DIV-CE1-R1` - pour liberer exactement la marge necessaire, 3,0 + 2,0 = 5,0
pile). Je ne le fais pas : cela porterait l'instance A a zero marge, exactement le defaut que
`DEC-0098` section 6 (repris par `DEC-0101` section 6) nomme comme cout reel et non hypothetique -
*"deja coute deux refus sur le meme lot"*. Et cela retarderait une correction deja instruite, deja
verifiee non dupliquee (`DEC-0108` section 4), au profit d'un contenu entierement neuf dont le
risque pedagogique est superieur (section 5 ci-dessous) : c'est l'ordre inverse de prudence.

**Je retiens donc une seconde instance B, dediee a `T6` seul, 2,0 / 5,0, marge 3,0.**

- **`PAR-2` est tenu** (*"une matiere et un niveau ne sont jamais partages, ni dans un sprint ni
  entre deux lots du meme sprint"*, `ARBITRAGE-PREALABLE-s29` section 13) : l'instance A ne porte
  que francais CE1 et mathematiques CE1 ; l'instance B ne porte que arts plastiques CP. Aucun
  partage.
- **Le motif n'est pas la couverture marginale, c'est la levee d'un blocage de sprint entier.**
  `DEC-0108` section 9 a deja etabli que le rouge de `build` bloque la porte technique des cinq
  autres stories de s50, qui sont sinon propres. Le cout habituel d'une seconde instance -
  *"deux paires d'yeux, pas un double controle"* (`DEC-0077` section 8.5, repris par `DEC-0107`
  section 4) - vise une dilution du controle croise entre deux lots de meme nature pedagogique ;
  ici les deux instances travaillent sur des domaines entierement disjoints (francais/
  mathematiques CE1 contre arts plastiques CP) et n'auraient jamais partage de controle croise de
  toute facon, que l'instance soit une ou deux. Le cout reel qui reste est celui que `DEC-0077`
  section 8.5 a mesure en s29 - une collision d'espace de noms entre deux instances qui n'ecrivent
  pas au meme endroit - et il ne s'applique pas ici : `T6` est le seul lot a toucher
  `squad/corpus/` et un paquet neuf, l'instance A ne touche ni l'un ni l'autre.
- **Marge de 3,0 sur l'instance B**, confortable pour un premier contenu sur un corpus neuf
  (section 5) : un rejeu borne par `PAR-10` (0,5 par paquet) tiendrait large.
- **L'instruction recue le 2026-10-03** (*"jusqu'a 4 instances de nadia-prof si besoin"*, citee par
  `DEC-0108` section 6) reste le plafond disponible invoque ici ; je n'ouvre qu'une deuxieme
  instance, pas une troisieme ni une quatrieme.

**Total du sprint reouvert** : instance A 3,5/5,0 (inchangee) + instance B 2,0/5,0 = **5,5 sur deux
instances independamment sous leur plafond individuel**. Aucune des deux ne depasse 5,0 ; je ne
negocie donc aucune borne totale de sprint (le palier a 8,0 de `PAR-8` etait une negociation
specifique a s29, pas une regle permanente).

## 4. Ce que ce document ne resout pas, et que je route explicitement a `nadia-prof` avant son Done

`ADR-0082` lui a route cinq points le 2026-09-05 ("Ce que je route, sans le decider", a
`nadia-prof`) ; **trois d'entre eux touchent directement ce lot et je n'ai trouve, en cherchant,
aucune reponse ecrite depuis** :

1. **`REC-1` a `REC-4` transposent-elles identiquement a une reproduction ?** (`ADR-0082` point 3).
   Non tranche. Je ne le tranche pas a sa place ; le developpeur et elle le verifient au moment de
   l'audit, comme pour tout premier usage d'une condition sur un contenu reel.
2. **Une oeuvre reproduite peut porter du texte visible** (signature, inscription, cartouche).
   `oeuvre-grande-vague` (Hokusai) porte typiquement un cartouche de titre en japonais, une
   caracteristique connue du type d'estampe retenu - a verifier sur le fichier reel, pas affirme
   ici de memoire. `ADR-0082` point 4 demande si c'est recevable pour un enfant de CP. **Non
   tranche.** Je demande que le developpeur signale ce point precis a `nadia-prof` avant d'ecrire
   le paquet entier, pour eviter un refus tardif sur un travail déjà fait - sur le modele de "je
   choisis sur image" deja suivi pour les lignes "a choisir" du vivier.
3. **Le prix de la verification d'attribution**, qu'elle s'est declaree seule garde sans qu'il
   figure dans son tarif (`ADR-0082` point 5). Je ne l'ajoute pas au tarif de la section 2 de ma
   propre initiative ; si elle le demande au moment du Done, je le chiffre alors.

Aucun de ces trois points ne m'autorise a retarder le developpement du paquet : ce sont des
conditions de son audit, pas des prealables bloquant l'ecriture. Mais les laisser non ecrits ici
reviendrait a les laisser disparaitre une seconde fois depuis le 2026-09-05 - je refuse ce
silence.

## 5. Criteres d'acceptation testables (story de backlog)

Ecrits dans `docs/backlog/backlog.md`, section "Backlog - sprints suivants", a la suite des trois
corrections de s50. Reference : `S50-ART`.

## 6. Mecanique et forme d'item : aucune de neuve, routage de l'avis de faisabilite

**Rien a trancher techniquement.** `ADR-0082` decision 5 l'a deja etabli et je le verifie : la
mecanique `bandit-manchot` et la forme d'item `question` sont reutilisees sans une ligne de code
change ; les deux cles de contenu `illustration` / `illustrationTexteAlternatif` existent depuis
`ADR-0073` (s25) ; leur resolution vers `static/oeuvres/` et la preuve de licence existent depuis
`ADR-0082` (T2 a T4, deja closes - `DEC-0108` section 1). **Je n'ai identifie aucun besoin de
mecanique neuve ni de forme d'item neuve** (pas de `ZoneADesigner` : l'enfant choisit toujours
parmi des propositions en texte, le geste de reponse ne change pas, critere `ADR-0058` deja
applique par `ADR-0082` decision 5).

Je route neanmoins l'avis de faisabilite a `theo-archi`, comme pour les cinq autres stories de s50,
pour confirmation formelle - en particulier que le paquet neuf fait passer
`LIllustrationDeContenuResoutEtAucuneNeDortTest` et
`ChaqueReproductionServiePorteLaPreuveDeLOeuvreEtCelleDeSaPhotographieTest` au vert, et que `mvn -B
clean verify` sur le reacteur complet rend `BUILD SUCCESS` - ce qui leve le rouge nomme par
`DEC-0108` section 9 pour l'ensemble du sprint, cinq autres stories comprises.

## 7. Ce que je decide, recapitule

1. `T6` entre au perimetre de s50 (reouvert, section "Sprint" ci-dessus) : les deux lignes
   `ART-CP-09` et `ART-CP-10` ensemble, un seul paquet, le vivier de 11 oeuvres deja verifie au
   registre (section 0).
2. Tarif : **2,0** (1,5 non source + 0,5 lot illustre), sur le bareme publie de `nadia-prof` et le
   precedent `S39-OUTILS`/`DEC-0095`, pas sur le precedent du francais CE1.
3. **Seconde instance B de `nadia-prof`, dediee, 2,0/5,0, marge 3,0.** L'instance A reste a 3,5/5,0,
   marge 1,5, inchangee depuis `DEC-0108`.
4. Trois points routes a `nadia-prof` (section 4), non tranches par ce document, a traiter au
   moment de son audit - un seul (le cartouche de texte sur l'estampe) a signaler au developpeur
   avant l'ecriture complete du paquet.
5. Criteres d'acceptation testables ecrits en story de backlog (`S50-ART`, section 5).
6. Aucune mecanique neuve, aucune forme d'item neuve (section 6). Avis de faisabilite technique
   route a `theo-archi`, avec le critere explicite de levee du rouge de `build` pour l'ensemble du
   sprint.
7. Developpeur a affecter nominativement par `sofia-sm`.

## 8. Ce que cette decision ne fait pas

- Elle ne deplace aucun statut de `squad/programme/MATRICE-couverture.md` : `ART-CP-09` et
  `ART-CP-10` restent `non couvert` jusqu'au Done pedagogique de l'instance B.
- Elle ne choisit aucune oeuvre, aucun critere pedagogique de contenu, aucune forme de distracteur :
  travail entier de `nadia-prof` et du developpeur affecte.
- Elle ne tranche aucun des trois points routes en section 4.
- Elle ne rend aucun avis de faisabilite technique : travail de `theo-archi`.
- Elle ne prononce aucun Done, technique ou pedagogique.
- Elle ne revient pas sur l'acceptation du commit de sourcing ni sur le perimetre deja engage de
  `DEC-0108` (sections 1 a 8, qui tiennent).

## 9. Amendement du 2026-10-04 : Q3 de l'avis de faisabilite (`theo-archi`, `squad/rapports/avis-faisabilite-s50-art-cp.md`, commit `4ea72382`) - les 17 reproductions hors vivier sortent de l'arbre, restaurables a l'octet, pas d'`ADR`

**Le trou chiffre par `theo-archi`, reverifie par moi au disque avant de trancher.**
`squad/corpus/oeuvres-domaine-public.yaml` porte 28 entrees (`grep -c "identifiant: oeuvre-"`,
2026-10-04). Le vivier retenu en section 1 ci-dessus (11 identifiants, section 0) en couvre 11. Les
17 autres - `oeuvre-licorne-captive`, `oeuvre-grande-jatte`, `oeuvre-autoportrait-leyster`,
`oeuvre-autoportrait-deux-eleves`, `oeuvre-rhinoceros`, `oeuvre-belles-heures`, `oeuvre-vitrail`,
`oeuvre-fresque-romaine`, `oeuvre-vase-ming`, `oeuvre-vase-shang`, `oeuvre-carreau-iznik`,
`oeuvre-bateau-egyptien`, `oeuvre-amphore-grecque`, `oeuvre-amerique-ancienne`,
`oeuvre-photo-pyramides`, `oeuvre-photo-notre-dame`, `oeuvre-photo-acropole` - sont le vivier de
`ART-CM1-07`, `ART-CM1-08` et des quatre lignes `HDA-CM1-*` (`CHOIX-oeuvres-candidates-T5.md`,
section 5 et 8.4), `T7`/`T8` d'`ADR-0082`, hors perimetre de s50. Le paquet `S50-ART` ne les
declarerait pas : `LIllustrationDeContenuResoutEtAucuneNeDortTest` resterait rouge sur ces 17 noms
apres l'ecriture complete du paquet, mesure par `theo-archi` en simulation et confirme par la
lecture de la clause elle-meme (boucle sur **chaque** reproduction, pas "au moins une").

**Ce que je verifie moi-meme avant de choisir, sans reprendre le chiffrage de confiance** : le cout
reel d'O1 depend de si les 17 sont deja verifiees ou non. Lu dans
`squad/rapports/sourcing-oeuvres-s49-verification.md`, section 2 (tableau oeuvre par oeuvre) et
section 4 (remplacantes) : les 17 identifiants y figurent tous, chacun avec un verdict **retenue**
(licence `CC0-1.0` confirmee programmatiquement contre l'API ou le jeu de donnees ouvert de
l'institution, auteur, date, numero d'inventaire) - y compris `oeuvre-grande-jatte` (bloquee
uniquement par Cloudflare pour le telechargement du fichier, licence deja verifiee section 3.1, et
le fichier est entre depuis par `56b6b77c`) et `oeuvre-photo-notre-dame` / `oeuvre-photo-acropole`
(remplacantes verifiees en section 4 apres le premier passage). **Aucun des 17 ne porte une
verification restant a faire.** Le cout d'O1 est donc exactement celui que `theo-archi` nomme sans
le trancher : un cout de mecanique Git pur (retrait puis `git checkout <sha> --` au moment de
`T7`/`T8`, meme blob, aucun travail de verification perdu ni a refaire).

**Ma decision : O1.** Le commit qui livre `S50-ART` retire du registre et du disque les 17 fichiers
et entrees hors vivier ; ils sont restaures a l'octet depuis l'historique Git (`56b6b77c` et les
commits anterieurs du chantier de sourcing) le jour ou `T7` ou `T8` s'ouvre reellement. Motifs :

- **Le cout que je redoutais avant verification (travail de verification perdu) n'existe pas** : je
  viens de le verifier au disque, pas de le supposer. O2 n'achete donc aucune garantie que n'achete
  pas deja l'historique Git.
- **O2 contredit le motif meme pour lequel j'ai engage `T6` en seconde instance** (section 6 :
  *"ce qui leve le rouge [...] pour l'ensemble du sprint"*). O2 demande un `ADR` prealable
  (amendement d'`ADR-0073` clause 2) avant que le paquet ne puisse meme s'ecrire, et laisse `build`
  rouge plus longtemps - exactement le cout que `DEC-0108` section 9 vient de nommer comme bloquant
  pour les cinq autres stories du sprint. Je ne rouvre pas ce rouge que je viens de decider de
  fermer.
- **Zero piece mobile nouvelle** : O1 ne touche aucune regle, aucun garde-fou, aucun `ADR`. C'est la
  clause 2 d'`ADR-0073` telle qu'ecrite (*"toute reproduction posee a son entree, toute entree
  designe un fichier qui existe"*) qui ferme, sans amendement.

**Ce que j'accepte explicitement par ce choix, en reponse a la reserve que `theo-archi` nomme avant
de me la router** : O1 retire de l'arbre, jusqu'a `T7`/`T8`, une partie du livrable du chantier de
sourcing que j'ai accepte en `DEC-0108` section 1 (25 oeuvres verifiees, puis 28 apres `56b6b77c`).
Je l'accepte parce que rien n'est perdu - l'historique Git porte les 17 fichiers et leurs entrees a
l'identique - et parce que la seule alternative (O2) couterait un `ADR` et un rouge plus long pour
une garantie que la verification au disque, ci-dessus, rend sans objet.

**Correction du chiffrage de `DEC-0109` section 6, et de `DEC-0108` section 1, sur la lecture de la
clause (Q6 de l'avis).** Les deux ecrivaient que le rouge de `build` se fermerait "des qu'un paquet
reel declare un identifiant `oeuvre-`", au singulier. **C'est inexact**, verifie par `theo-archi`
dans le code du garde-fou et reverifie par moi dans cette section : la clause porte sur **chaque**
reproduction servie, pas sur l'existence d'au moins une declaration. Le sixieme critere de la story
`S50-ART` (*"`BUILD SUCCESS`, zero echec"*) n'est donc pas tenu par le paquet seul : il demande, en
plus de l'ecriture du paquet, A1 (redimensionnement des trois fichiers de l'Art Institute of
Chicago et relevement du cliquet, route a `theo-archi`) et A2 (le retrait O1 decide ici, route au
developpeur affecte).

**Ce que ca change pour le developpeur qui ecrira `S50-ART`** : le commit du paquet, en plus de ce
qu'il touche deja (section 7, avis de `theo-archi`, point 8), retire les 17 entrees hors vivier de
`squad/corpus/oeuvres-domaine-public.yaml` et leurs 17 fichiers sous `static/oeuvres/`, et ramene
`REPRODUCTIONS_SERVIES` et `POIDS_TOTAL_MAXIMUM` aux 11 fichiers du vivier, mesures apres A1 (pas
estimes). Les deux garde-fous de l'avis (section 0) sont a relancer apres ce retrait, pas seulement
apres l'ecriture du contenu.

**Ce que cet amendement ne fait pas.** Il ne tranche aucune des questions pedagogiques routees a
`nadia-prof` (Q1, Q2, Q4, Q5 de l'avis). Il ne prononce aucun Done. Il n'effectue pas lui-meme le
retrait des fichiers : c'est le developpeur affecte par `sofia-sm` qui l'execute, dans le commit du
paquet (A2).
