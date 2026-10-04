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

## 10. Amendement du 2026-10-04 : reponse a l'arbitrage prealable de `nadia-prof` sur `S50-ART` (`squad/conformite/ARBITRAGE-PREALABLE-s50-art-cp.md`) - perimetre reduit a `ART-CP-09` seule, vivier final a huit oeuvres, tarif inchange

**Ce qui m'est pose.** `nadia-prof` a rendu son arbitrage prealable (`dd98cfe8`) : `ART-CP-10` est
refusee sous forme texte (Q1 - rapprocher deux oeuvres exige de les voir en meme temps, une
mecanique neuve hors du perimetre deja chiffre de `T6`) ; `ART-CP-09` reste faisable sans le mot
"couleurs" dans son critere (Q4) ; elle me route deux points (section "Ce qui bloque encore", lignes
1 et 2) : reduire formellement le perimetre, et decider du sort des trois oeuvres propres a
`ART-CP-10`.

### 10.1 Perimetre - `S50-ART` ne porte plus que `ART-CP-09`

**`ART-CP-10` sort du perimetre engage par la section 1 ci-dessus.** Elle reste `non couvert`
(constructible, jamais `non couvrable en l'etat` - `nadia-prof` le redit elle-meme dans son
arbitrage). Motif : la mecanique qui montre deux oeuvres en meme temps n'existe pas et n'est pas
chiffree dans `T6` (`ADR-0082`) ; l'engager demanderait un `ADR` prealable de `theo-archi`, que je
ne demande pas aujourd'hui. Le besoin et les criteres d'acceptation de `S50-ART` sont corriges en
consequence dans `docs/backlog/backlog.md` (section 11 ci-dessous).

### 10.2 Tarif - inchange a 2,0, sur le meme principe que celui qui avait fait fusionner les deux lignes

**Je ne baisse pas le tarif.** La section 1 de ce document justifiait deja de ne pas separer les
deux lignes par le fait que *"le cout [...] est identique a engager une ligne ou les deux, parce que
l'unite de tarif est le lot de matiere et de niveau, pas la ligne"* (`ARBITRAGE-PREALABLE-s28`,
section 4.1 : *"le nombre de fichiers ne compte pas"* ; section 4.4, exemple verifie : le second lot
de questionner le monde CP ferme trois lignes au meme tarif qu'un lot qui n'en fermerait qu'une).
**Ce principe joue dans les deux sens** : une ligne en moins dans le lot ne baisse pas son prix, de
la meme maniere que deux lignes au lieu d'une ne l'augmentaient pas. Le lot reste un seul paquet
d'arts plastiques CP, jusqu'a vingt items, matiere non source : **1,5 + 0,5 (lot illustre, `REC-1`)
= 2,0**, inchange.

### 10.3 Sort des trois oeuvres propres a `ART-CP-10` - retirees avec les dix-sept, meme regime `O1`

**Fait verifie avant de trancher, pas repris de confiance.** `nadia-prof` admet les trois oeuvres
(`oeuvre-champ-de-ble`, `oeuvre-averse-sur-le-pont`, `oeuvre-jeune-femme-aiguiere`) dans le vivier
de `ART-CP-09` : elle le dit explicitement, c'est une permission pedagogique, pas une obligation -
*"les garder dans le paquet ou les retirer avec les dix-sept autres est le choix de `camille-po` ;
les deux sont recevables pour moi"*. J'ai verifie, dans `squad/corpus/CHOIX-oeuvres-candidates-T5.md`
(sections 5 et 8.4), si ces trois oeuvres servent une autre ligne que `ART-CP-10` :

- `oeuvre-averse-sur-le-pont` et `oeuvre-jeune-femme-aiguiere` ne figurent dans **aucune** autre
  ligne du tableau de repartition (`ART-CM1-07`, `ART-CM1-08`, `HDA-CM1-01` a `HDA-CM1-06`) : leur
  seul usage prevu etait la paire de `ART-CP-10`.
- `oeuvre-champ-de-ble` figure aussi dans `ART-CM1-07` et `HDA-CM1-02`, deux lignes de CM1 hors
  perimetre de s50 - **mais cela ne la distingue pas des dix-sept deja retirees sous `O1`** :
  `oeuvre-grande-jatte`, par exemple, figure elle aussi dans `ART-CM1-07` et a ete retiree quand meme
  (section 9 ci-dessus). Le critere d'`O1` n'a jamais ete "aucun usage futur ailleurs", il a ete "pas
  d'usage dans le paquet qui s'ecrit maintenant" : un usage a `T7` ou `T8` se sert alors, par le meme
  geste de restauration Git, que l'oeuvre ait dormi un sprint ou vingt.

**Ma decision : les trois oeuvres suivent le regime `O1` de la section 9, pas d'exception.** Je ne
les admets pas dans le vivier de `ART-CP-09`. Motifs :

1. **Le vivier de huit oeuvres d'`ART-CP-09` est deja complet par la conception meme de
   `nadia-prof`** : trois continents, du Moyen Empire egyptien a 1889, une artiste femme
   (`CHOIX-oeuvres-candidates-T5.md`, section 5, ligne `ART-CP-09`). Les trois oeuvres en question
   n'ont pas ete choisies pour completer cette ligne : elles ont ete choisies **pour la propriete de
   paire** qu'exige `ART-CP-10` (meme touche, meme maniere de dessiner l'eau, meme lumiere de
   fenetre) - une propriete sans objet des lors que la mecanique de rapprochement n'est pas engagee.
   Les ajouter gonflerait le vivier sans servir un besoin non couvert de la ligne.
2. **Traiter les trois a part des dix-sept creerait une exception non justifiee.** Rien ne distingue
   leur statut : comme les dix-sept, elles sont hors du paquet qui s'ecrit aujourd'hui, et comme pour
   `oeuvre-grande-jatte`, un usage futur dans une autre matiere ne les a pas sauvees de la retraite.
   Une regle qui ferait une exception pour trois noms sur vingt, au seul motif qu'ils figuraient
   dans un decompte provisoire avant que le perimetre ne change, ne serait pas une regle.
3. **Le cout de la retraite est nul**, deja mesure en section 9 pour les dix-sept : restauration a
   l'octet depuis l'historique Git (`56b6b77c` et les commits anterieurs du chantier de sourcing),
   aucune verification de licence perdue ni a refaire, aucun `ADR`, aucun garde-fou nouveau.

**Consequence chiffree.** Le commit qui livre `S50-ART` retire **vingt** fichiers et entrees (les
dix-sept de la section 9, plus ces trois) au lieu de dix-sept, et le registre comme le disque
reviennent a **huit** entrees apres ce commit - exactement le vivier d'`ART-CP-09`. `theo-archi`
recalcule `REPRODUCTIONS_SERVIES` et `POIDS_TOTAL_MAXIMUM` sur ces huit fichiers, comme la section 9
le demandait deja pour les dix-sept (la mecanique ne change pas, seul le compte change).

### 10.4 Marge de l'instance B - inchangee, 2,0/5,0, marge 3,0

Le tarif ne bouge pas (10.2) : la marge de l'instance B ouverte en section 3 reste **2,0/5,0, marge
3,0**. Une seule ligne au lieu de deux, un vivier de huit oeuvres au lieu de onze, ne changent rien
au prix du lot ni, par consequent, a cette marge. Je le verifie explicitement parce que
`nadia-prof` me le demandait implicitement en reduisant le perimetre, pas parce qu'un calcul
different etait plausible.

### 10.5 Matrice - motif de la ligne `ART-CP-10` corrige

`squad/programme/MATRICE-couverture.md` (ligne concernant `ART-CP-10`) est corrigee pour reprendre
le motif exact de `nadia-prof` (vue simultanee requise), a la place de l'ancien motif ("meme motif
que la ligne precedente") qu'elle a elle-meme qualifie d'inexact. Aucun statut ne bouge : la ligne
reste `non couvert`, aucun Done n'est prononce par ce document.

### 10.6 Ce que cet amendement ne fait pas

Il ne rouvre aucune question pedagogique deja tranchee par `nadia-prof` (le refus d'`ART-CP-10` en
texte tient, sa permission d'admettre les trois oeuvres dans le vivier d'`ART-CP-09` reste une
permission que je choisis de ne pas exercer, pour les motifs de 10.3). Il ne prononce aucun Done,
technique ou pedagogique. Il ne modifie aucun code, aucun test.

## 11. Amendement du 2026-10-04 : reponse au refus pedagogique de premier passage sur `S50-ART` (`squad/conformite/FICHE-bandit-manchot-decrire-une-oeuvre-cp.md`, section 10, point 2) - l'Arcimboldo reste au paquet, reecrit

**Ce qui m'est pose.** `nadia-prof` refuse le premier passage (`DEC-0109` section 3, `655b319b`),
quatre motifs, tous correctifs dans le seul fichier de contenu. Le motif 2 porte sur
`oeuvre-quatre-saisons` : la reponse attendue (*un visage*) ne se voit pas a 176 px, l'enfant la
trouve par elimination. Elle route deux options sans trancher (fiche section 10, point 2) :
reecrire l'item sous la forme *"avec quoi ce visage est-il fait ?"* (reponse : fruits et legumes,
visibles a 176 px - forme qu'elle a deja testee et qui passerait son audit, fiche section 3), ou
retirer l'oeuvre, ce qui romprait l'inegalite stricte d'`ADR-0023`
(`ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest`) et le critere "huit items, huit oeuvres" de
`S50-ART`, et demanderait de choisir entre baisser `nombreDeManches` a 6, remplacer l'oeuvre
(a repasser devant elle), ou ajouter un second item sur une oeuvre deja servie - avec un
recalcul des cliquets de poids et d'illustrations par `theo-archi` dans les trois cas.

**Ma decision : l'Arcimboldo reste au paquet, reecrit (option A du routage).** Aucune des trois
sous-options du retrait n'est engagee.

Motifs :

1. **Cout nul contre cout non nul.** La reecriture se fait dans le seul fichier
   `cp-decrire-une-oeuvre.yaml` deja en jeu pour les trois autres motifs du meme refus (fiche
   section 10, point 1) : un seul passage du developpeur, aucun fichier de poids ni de cliquet ne
   bouge, le vivier reste a huit oeuvres et le paquet a huit items pour sept manches. Le retrait
   n'est gratuit sur aucune de ses trois formes : les deux premieres chiffrent un geste de
   `theo-archi` (recalcul de `REPRODUCTIONS_SERVIES`, `POIDS_TOTAL_MAXIMUM`,
   `ITEMS_QUI_DECLARENT_UNE_ILLUSTRATION`) et, pour le remplacement, un second passage complet de
   `nadia-prof` sur une oeuvre neuve (fidelite, 176 px, `ART-5`) ; la troisieme change la forme du
   jeu (une oeuvre revue deux fois) sans qu'aucune des deux parties n'ait demande cette forme.
2. **Le retrait met en risque une propriete du vivier que `nadia-prof` elle-meme signale comme
   fragile.** Sa fiche (section 8) note que le vivier actuel tient `ART-5` "au plus juste", avec
   une seule artiste femme et l'Egyptienne et la Japonaise comme seuls representants hors Europe -
   et qu'"une oeuvre retiree ou remplacee ne doit pas oter l'egyptienne, la japonaise ni celle de
   Rosa Bonheur". L'Arcimboldo n'est aucune des trois, mais reduire le vivier a sept ou le
   completer d'une oeuvre non choisie pour cette ligne (section 10.3 ci-dessus : les candidates
   disponibles ont ete choisies pour une autre propriete, celle de la paire d'`ART-CP-10`, sans
   objet ici) rouvre une question de composition que l'option A ne pose pas.
3. **`nadia-prof` a deja teste la forme de remplacement et l'a jugee recevable.** Sa fiche
   (section 3) ecrit explicitement qu'un item demandant *"avec quoi ce visage est-il fait ?"*
   passerait son test de premiere reponse, et que ce n'est pas une devinette : rien ne permet de
   deviner qu'un visage est fait de fruits plutot que de pierres sans regarder l'image (`ART-4`).
   Elle ajoute (motif 4 de son refus) que cette forme sert alors *materiaux* et *formes* du
   libelle de competence, ce qui leve d'elle-meme une partie du motif 4 sans intervention de ma
   part.

**Ce que je ne tranche pas.** La recevabilite exacte de la reformulation reste au second passage de
`nadia-prof`, comme pour les trois autres motifs du meme refus (fiche section 10, point 1) : je ne
juge aucun contenu, je choisis uniquement de ne pas faire bouger le perimetre ni les cliquets.

**Consequence.** Le developpeur affecte au second passage reecrit l'item `oeuvre-quatre-saisons`
dans `cp-decrire-une-oeuvre.yaml`, en meme temps que les motifs 1, 3 et le point (d), sans solliciter
`theo-archi`. Le vivier reste a huit oeuvres, le paquet a huit items, `nombreDeManches` reste a 7.
Aucune ligne de matrice ne bouge (`ART-CP-09` reste `non couvert` jusqu'au Done pedagogique du
second passage).
