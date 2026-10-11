# [Hors sprint] Accueil par matiere - rapport qualite de yanis-qa

- **Date** : 2026-10-11 20:55 (UTC+02:00), lue par `date`.
- **Arbre mesure** : `def23ead` (tip de `lea-accueil-p4`, base `96e05fa9`), branche `yanis-accueil-mesure`, plus deux fichiers de
  ma main (`outils/mesurer-accueil.py`, `outils/mesurer-accueil-test.py`) et ce rapport. `git status --short` ne montrait que ces
  fichiers.
- **JDK des mesures** : **21.0.10**, propriete `java.version` de tous les rapports `TEST-*.xml` de Surefire et Failsafe
  (`ADR-0071`). Enveloppe `bash outils/mvn-jdk21.sh`. Toute mesure partielle de Maven porte `-pl <module> -am`.
- **Ce rapport ne prononce aucun Done.** Il rapporte ce qui a ete lance ici. Le Done pedagogique est a `nadia-prof` seule. Les
  seuils de `HS-ACCUEIL-V1` et `HS-ACCUEIL-V2` sont des valeurs de `camille-po` : ce rapport les compare, il ne les juge pas.
- **Rien n'est corrige dans le code de production** : tout ce qui s'ecarte est remonte (section 6).

## 1. Verdict technique

La section 7 donne les jobs, avec la commande et le resultat de chacun, sur l'arbre qui porte ce rapport. Elle est ecrite apres
la mesure, parce que `build` lit les `.md` de `squad/`.

## 2. HS-ACCUEIL-MESURE : la commande, et ce qu'elle rend

### 2.1 La commande

`outils/mesurer-accueil.py` (Python 3, bibliotheque standard seulement) pilote un Chrome headless par le protocole de debogage
sur un serveur deja lance. Prerequis nommes : Python 3, un Chrome (PATH ou variable `CHROME`), une URL. Aucun chemin de poste n'est
ecrit. Elle n'est pas un garde-fou de CI : son juge pur l'est.

```
python outils/mesurer-accueil.py --url http://localhost:<port> [--largeur 744] [--hauteur 551] [--seuil-v2 10] [--seuil-etage2 6]
```

**Methode.** Fenetre emulee par `Emulation.setDeviceMetricsOverride` (facteur 1, sans mobile, barres de defilement masquees, comme
`outils/mesurer-rendu.sh`), session neuve par niveau. Releve de `getBoundingClientRect()` de chaque `button.demarrer`, bandeau
`.entete-matiere` et `a.tuile`. **Une carte est entierement visible si `top` >= 0, `bottom` <= `innerHeight`, `left` >= 0 et `right`
<= `innerWidth`.** Parcours par niveau : la vue par defaut ; appui sur `button.bascule` ; releve de l'etage 1 ; appui sur chaque
tuile (navigation complete), releve de l'etage 2, retour par le bouton ; retour a la vue par defaut. Les attendus sont lus **sur
disque** : `catalogue.yaml` joint par `paquet:` au `id:` de chaque fichier de `contenu/`, `niveau` et `matiere` du descripteur,
`matieres.yaml` pour l'ordre et les libelles. Aucun nombre n'est ecrit dans l'outil. Une matiere est "nommee" quand son libelle
court (ou la valeur du descripteur) est le texte entier d'un element visible de `#jeu` hors carte : un titre de jeu qui contient le
mot ne compte pas. Les nombres de cartes visibles sont rapportes, jamais juges, sauf `--seuil-v2` et `--seuil-etage2`.

**Le serveur** : jar de l'arbre, lance par `java -jar` sur un port dont `netstat -ano` ne montrait aucun processus a l'ecoute,
**PID lu dans le journal de demarrage de chaque lancement, arrete par ce seul PID** (`taskkill /PID`, jamais par nom d'image). Six
lancements (arbre de tete, base et arbre mute, chacun deux fois), chacun arrete. `netstat` en fin de serie : aucun port de la plage
utilisee a l'ecoute.

**Preuves de l'outil.**

1. **Il rend la mesure de depart de `DEC-0117` sur l'arbre de base.** `git archive 8ac33440` extrait hors du depot, jar construit
   par `bash outils/mvn-jdk21.sh -B -q -DskipTests package` sur la racine du reacteur, lance, mesure : **5 / 5 / 5 cartes
   visibles (CP, CE1, CM1), 0 matiere nommee, documents de 4216, 2244 et 4148 px**, les chiffres de la section 4 de `DEC-0117`
   a l'unite (le catalogue de l'arbre de base compte 147 jeux, CP 59).
2. **Il mord sur un arbre mute.** Deux mutations ensemble dans `accueil.html` (la derniere carte de chaque groupe de V2 retiree ;
   la premiere tuile de l'etage 1 retiree), jar reconstruit par `bash outils/mvn-jdk21.sh -B -q -DskipTests -pl bootstrap -am
   package`, fichier restaure (`git checkout`, `git status --short` ne montre plus que mes fichiers). Resultat : CP 58 cartes
   deviennent **50** (CE1 30 deviennent 28, CM1 58 deviennent 50), l'etage 1 passe de 8 tuiles a 7 (CE1 2 a 1), la somme des etages
   2 de CP passe de 58 a 46, et **la commande sort 1 avec 11 ecarts** qui nomment les jeux inatteignables (par exemple
   `bandit-manchot-synonymes-contraires-ce1`, `designation-symetrie-ce1`) et les bandeaux sans carte (CP : Anglais ; CM1 :
   Anglais, Histoire des arts).
3. **Son juge pur est eprouve par `outils/mesurer-accueil-test.py`** (32 tests, entrees fabriquees, aux bords : `bottom` pile sur
   le pli visible, un demi-pixel dessous non ; seuil inclusif ; `min(n, 6)` ; liste plate ; catalogue fabrique sur disque). **Dix
   mutations du juge, chacune appliquee a une copie exacte du fichier reel (motif trouve une fois, 32 tests lances, fichier
   restaure)** : `<=` du pli en `<`, seuil inclusif en exclusif, `min(n, seuil)` en `seuil`, doublons ignores, manquants ignores,
   nommage par sous-chaine, bandeau sans carte ignore, somme des etages 2 non jugee, tri des matieres retire, paquet introuvable
   ignore. **Dix rouges** (1 a 4 echecs chacune). Le job `outils` de la CI balaie `outils/*-test.py` : ce test y est lance sans
   modification du workflow.
4. **Stable** : trois lancements successifs a 744 px sur le meme jar rendent la meme sortie (empreinte identique) ; deux a 390 px
   idem.

**Deux defauts de ma premiere version, corriges avant les chiffres de ce rapport.** Bootstrap pose `scroll-behavior: smooth` : mon
premier releve du bandeau colle a lu la page en plein defilement (CP : `top` 281 au lieu de 187), et mon releve de l'etage 1 a 390
px comptait 6 tuiles visibles au lieu de 4 parce que la page n'etait pas revenue en haut. Le releve force maintenant un retour en
haut instantane et attend `scrollY === 0`. Les chiffres ci-dessous sont ceux de la version corrigee ; la mesure de l'arbre de base
et de l'arbre mute a ete rejouee avec elle.

### 2.2 A 744 x 551, jar de `def23ead`, trois niveaux (catalogue : 146 jeux, CP 58, CE1 30, CM1 58)

| Mesure | CP | CE1 | CM1 |
|---|---|---|---|
| **Base `8ac33440` (liste)** : cartes entierement visibles, matieres nommees, document | 5, 0 sur 8, 4216 px | 5, 0 sur 2, 2244 px | 5, 0 sur 8, 4148 px |
| V2 : cartes au total | 58 | 30 | 58 |
| V2 : cartes entierement visibles | **8** | **8** | **8** |
| V2 : bandeaux, matieres nommees | 8, 8 sur 8 | 2, 2 sur 2 | 8, 8 sur 8 |
| V2 : document, defilement necessaire | 2922 px, oui | 1390 px, oui | 2952 px, oui |
| V2 : variation du document contre la base | -30,7 % | -38,1 % | -28,8 % |
| V1 etage 1 : tuiles entierement visibles / tuiles, document | 8 / 8, 551 px | 2 / 2, 551 px | 8 / 8, 551 px |
| V1 etage 1 : defilement necessaire | non | non | non |
| V1 etage 2 : cartes (somme) | 58 | 30 | 58 |
| V1 etage 2 : colonnes | 2 (1 pour une carte) | 2 | 2 (1 pour une carte) |
| V1 etage 2 : etages dont toutes les cartes tiennent sans defiler | 4 sur 8 | 0 sur 2 | 4 sur 8 |
| V1 etage 2 : `min(n, 6)` cartes entierement visibles | tenu, 8 etages sur 8 | tenu, 2 sur 2 | tenu, 8 sur 8 |

Autres valeurs relevees a 744 px : le nom du premier bandeau est entierement visible (`top` 180, `bottom` 228, les trois niveaux) ;
**bandeau colle**, premiere carte du troisieme groupe au milieu de la fenetre (CP, CM1) : bandeau du troisieme groupe `top` 187,
`bottom` 235, `position: sticky`, **un seul** bandeau a `top` 0 (CE1 n'a que deux groupes : non mesure) ; "Les jeux" est visible **une
seule fois** sur chaque ecran mesure (V2, etage 1, chaque etage 2) ; hauteur minimale d'une carte de V2 : 56 px, d'une tuile
116 px (CE1) et 140 px (CP, CM1) ; pictogramme charge (`naturalWidth` > 0) pour tous les bandeaux et toutes les tuiles ; aucun
debordement horizontal ; deux chargements successifs du meme niveau rendent la meme suite de libelles de groupe (curl, deux
requetes sans cookie sur `/niveaux/CP`, empreinte identique), et l'ordre des tuiles est celui des bandeaux.

### 2.3 Par vue et par niveau, 390 et 992 px (hauteur 551 dans les deux cas pour que le pli reste comparable ; un telephone reel est plus haut)

| Largeur | Mesure | CP | CE1 | CM1 |
|---|---|---|---|---|
| 390 | **base** : cartes visibles, document | 4, 4364 px | 4, 2332 px | 4, 4266 px |
| 390 | V2 : cartes visibles, document (variation) | **3**, 4786 px (+9,7 %) | **3**, 2468 px (+5,8 %) | **3**, 4786 px (+12,2 %) |
| 390 | V1 etage 1 : tuiles visibles / 8, document | 4 / 8, 838 px, defilement | 2 / 2, 551 px | 4 / 8, 862 px, defilement |
| 390 | V1 etage 2 : colonnes, cartes visibles | 1 colonne, 3 (ou n si n < 3) | 1 colonne, 3 | 1 colonne, 3 (ou n si n < 3) |
| 992 | **base** : cartes visibles, document | 5, 4216 px | 5, 2244 px | 5, 4148 px |
| 992 | V2 : cartes visibles, document | **12**, 2370 px | **9**, 1238 px | **10**, 2626 px |
| 992 | V1 etage 1 : tuiles visibles, document | 8 / 8, 551 px | 2 / 2, 551 px | 8 / 8, 551 px |
| 992 | V1 etage 2 : colonnes, `min(n, 6)` | 3 colonnes (2 pour 2 cartes), tenu | 3, tenu | 3 (2 pour 2 cartes), tenu |

A 390 px la variation de longueur de V2 (+5,8 a +12,2 %) est dans la fourchette que les maquettes d'`ines-design` annoncaient
(+6 a +12 %, d'apres le rapport de `lea-dev`, non relu ici).

### 2.4 Chaque jeu du catalogue est atteignable depuis chaque vue

- **146 jeux atteints dans V2, 146 dans V1, 0 inatteignable, 0 en double, 0 jeu d'un autre niveau**, par niveau (CP 58, CE1 30,
  CM1 58). V2 : ensemble des identifiants lus dans `hx-post` des cartes. V1 : union des identifiants des etages 2, une tuile par
  matiere, aucun etage 2 vide. Les deux ensembles sont compares, par difference d'ensembles, a celui du catalogue lu sur disque
  (146 entrees de `catalogue.yaml`, niveau lu dans chaque paquet). Le retrait de `cp-rapprocher-deux-oeuvres` est constate : 58
  jeux en CP contre 59 sur l'arbre de base.
- **146 `POST /parties/{id}` rendent 200** (un par entree du catalogue, `HX-Request`, session neuve a chaque requete, aucune reponse
  ne contient le bouton de vue : CP 58, CE1 30, CM1 58). Mesure par un script jetable hors depot : l'outil ne la fait pas.

## 3. R6 et R8 de `lea-dev` 16.2 : constats

Jar de `def23ead`, lance sur un port libre, PID lu dans le journal. Aucune cause n'est supposee ici.

**R8 (b), `;jsessionid` dans l'adresse.** Premiere requete sans cookie (`curl -D`), puis la meme avec le cookie rendu :

```
GET /niveaux/CP sans cookie     -> HTTP/1.1 200 ; Set-Cookie: JSESSIONID=...; Path=/; HttpOnly
   occurrences de "jsessionid" dans le corps : 76
   href="/favicon.ico;jsessionid=..."  href="/css/recreo.css;jsessionid=..."  href="/niveaux/CP;jsessionid=..."
   hx-post="/niveaux/CP/vue/V1;jsessionid=..."  hx-post="/parties/bandit-manchot-le-bon-son-cp;jsessionid=..."
GET /niveaux/CP avec le cookie  -> HTTP/1.1 200 ; occurrences de "jsessionid" dans le corps : 0
```

Constat de `lea-dev` **confirme et plus large** : le defaut ne touche pas que le premier clic de puce, il touche **toute URL de la
premiere reponse d'une session** (feuilles, favicon, puces, tuiles, `hx-post` de chaque carte). **Il est anterieur a ce chantier** :
sur le jar de l'arbre de base, la meme mesure compte 30 / 58 / 59 URL portant `jsessionid` en CE1 / CM1 / CP sur la liste. Dans le
navigateur (Chrome headless, session neuve) : `GET /`, la puce CP porte `href="/niveaux/CP;jsessionid=..."`, **un clic y mene et
l'adresse lue est `http://localhost:<port>/niveaux/CP;jsessionid=<identifiant>`**, puis elle reste la meme apres le lancement d'un
jeu, la confirmation et le retour (etapes 2 a 5 ci-dessous). Le seul `Set-Cookie` de la reponse est `JSESSIONID` (rien de plus).
Ce que `ADR-0105` en dit n'est pas juge ici : route a `theo-archi`.

**R6 et R8 (a), apres `/retour/confirmer`.** Parcours dans Chrome (une session, 744 x 551), lecture de `location.href`, des puces
`.chip-niveau.est-actif`, de `.choix-niveau` et du nombre de bandeaux a chaque etape :

| Etape | `location.href` (fin) | Puce active | Ecran |
|---|---|---|---|
| 1. `GET /` session neuve | `/` | aucune | "Choisis ton niveau." |
| 2. clic sur la puce CP | `/niveaux/CP;jsessionid=...` | CP | "Pour le CP", 8 bandeaux, 58 cartes |
| 3. lancement d'un jeu | `/niveaux/CP;jsessionid=...` | CP | la manche |
| 4. "Les jeux" | `/niveaux/CP;jsessionid=...` | CP | confirmation : "Continuer la partie" (`/retour/annuler`), "Les jeux" (`/retour/confirmer`) |
| **5. `/retour/confirmer`** | **`/niveaux/CP;jsessionid=...`** | **CP** | **"Choisis ton niveau."**, aucune carte |
| 6. rechargement de l'adresse affichee | `/niveaux/CP;jsessionid=...` | CP | "Pour le CP", 8 bandeaux, 58 cartes |

Les en-tetes (`curl -D`, `HX-Request`, meme session) : `POST /retour/confirmer` rend 200 **sans `HX-Push-Url`** et pose
`Set-Cookie: recreo-partie-en-cours=; Expires=Thu, 01 Jan 1970 ...` ; `POST /retour` sans partie rend 200 avec `HX-Push-Url: /` ;
`POST /niveaux/CP/matieres/francais/confirmer` avec une partie en cours rend 200 sans `HX-Push-Url`. **R6 et R8 (a) sont donc
reproduits** : apres la confirmation, la barre d'adresse et la puce active disent le niveau CP pendant que l'ecran demande le
niveau, et un rechargement rend le niveau.

## 4. F1 a F9 du rapport qualite de s56 : lesquels touchent ce lot

Methode : relecture de la colonne "ce qui ne mord pas" de la section 5 du rapport de s56 contre le diff de `96e05fa9` a `def23ead`
(`recreo.css` 171 lignes ajoutees, `accueil.html`, un pictogramme). **Aucun des neuf n'est retourne ni ferme par ce lot.**

| F | Touche ce lot ? | Pourquoi |
|---|---|---|
| F1, F3 | non | cases et marque du jeu des oeuvres ; aucune regle de ce lot ne les nomme |
| F2 | **non, mais a surveiller** | regle sous `@media` echappant au garde-fou du plafond d'image : le lot ajoute dans `recreo.css` (grep sur le fichier entier) aucun `@media` ; les seuls sont ceux d'avant (lignes 277, 926, 1852). Le garde-fou n'est pas mis a l'epreuve par ce lot |
| F4, F5, F6 | non | choix de manche de REV-3, bornes de B5 et B7 : jeux, pas accueil |
| F7 | **non** | orthographe hors accents sur un texte de paquet : les onze libelles courts de `matieres.yaml` ne portent ni ligature ni `oe` (grep : 0) et sont epingles par l'inventaire (`TexteAccentueExposeParLePortEntrantTest`) ; "Choisis ton niveau." est epingle par `TexteAccentueDesGabaritsTest`. L'orthographe de ces textes reste a `relecteur-pedagogique` |
| F8 | non | l'enveloppe `outils/mvn-jdk21.sh` a ete utilisee, non modifiee |
| F9 | **analogue, pas identique** | F9 est un critere de backlog insatisfiable tel qu'ecrit. Ici le seuil de 10 cartes de `HS-ACCUEIL-V2` n'est pas atteint (section 6, E1) : `camille-po` l'avait annonce ("personne ne le baisse en silence"), ce n'est donc pas F9 mais la meme famille |

## 5. Mutations jouees sur ce lot (en plus de celles de la section 2.1)

Commande : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-web -am test -Dmaven.test.failure.ignore=true
-Dtest=AccueilParMatiereTest -Dsurefire.failIfNoSpecifiedTests=false` (perimetre : `domain`, `application`, `adapter-web` ;
`-am` present). Fichier restaure apres chaque mesure ; **25 tests lances** dans les deux cas (le motif a ete trouve une fois).

| # | Mutation de `accueil.html` | Resultat |
|---|---|---|
| MB01 | `groupes` du `li` de l'etage 1 prive de son premier groupe (une tuile retiree, `HS-ACCUEIL-COMPLETUDE`) | **5 echecs** : `basculerVersV1RendLEtageUnEtPousseLAdresseDuNiveau`, `chaqueTuilePointeVersLEtageDeuxDeSaMatiereEtNeLanceJamaisUnJeu`, `sommeDesEtagesDeuxEgaleLesJeuxDuNiveauEtAucuneTuileSansJeu`, `confirmerLeChangementDeNiveauRendLaVueRetenueDeLaSession`, `apresUnePartieLeNiveauChoisiSOuvreDansLaVueRetenue` |
| MB02 | `groupes` de la `section` de V2 prive de son premier groupe (un groupe retire) | **3 echecs** : `chaqueJeuDuNiveauParaitUneFoisDansLeGroupeDeSaMatiereEtAucunJeuD_unAutreNiveau`, `sansChoixLaVueEstV2UnBandeauParMatiereDansL_ordreDesLignesDeDonnee`, `unDoubleAppuiRendDeuxFoisLeMemeEcran` |

Les deux criteres de mutation de `HS-ACCUEIL-COMPLETUDE` (un groupe ou une tuile retire fait rougir un test) sont tenus **par un
catalogue de test**. Voir E2.

## 6. Ecarts

**Contre le rapport de `lea-dev` (section 16.4) : aucun ecart de chiffre.** Retrouves a l'unite : 8 cartes visibles dans les trois
niveaux en V2, 8 / 2 / 8 tuiles toutes visibles, sommes des etages 2 de 58 / 30 / 58, documents de 2922 / 1390 / 2952 px, bandeau
colle `top` 187 `bottom` 235, un seul bandeau a `top` 0, l'etage 1 tenant dans 551 px. Ce qui s'ajoute a son rapport : 390 et 992 px,
la completude des identifiants, les 146 `POST`, la commande commitee.

- **E1. `HS-ACCUEIL-V2`, defilement : le seuil de 10 cartes n'est pas atteint a 744 px, il vaut 8 dans les trois niveaux.** A
  992 px : CP 12, CE1 9, CM1 10 ; a 390 px : 3 (4 sur la liste de base). Le seuil est une valeur de `camille-po` (`DEC-0117`
  section 6, point 3 et le critere lui-meme : "personne ne le baisse en silence"). Route : `camille-po`, par `sofia-sm`.
- **E2. Aucun test Maven ne joue le catalogue reel dans les deux vues.** `grep -rn "niveaux/" bootstrap/src/test` ne rend qu'une ligne
  (`PartieHttpTest`, un `GET` sur un niveau nomme "Test") ; les tests de routes de `matieres/` et `vue/` vivent tous dans
  `adapter-web` sur `CatalogueDeTest`. Les criteres de `HS-ACCUEIL-COMPLETUDE` "un nouveau jeu servi est atteignable dans les deux
  vues sans edition d'un test" et "nombres derives du catalogue servi" (`ADR-0013`, perimetre derive) ne sont couverts, sur les 146
  jeux reels, **que par la commande de ce rapport, qui n'est pas dans `mvn verify`**. Un jeu perdu dans une vue a cause d'une
  donnee reelle (une matiere sans ligne, un segment en double) ne ferait rougir aucun test d'integration. Route : `sofia-sm` (je
  peux ecrire ce test dans `bootstrap` sur ordre ; je ne l'ai pas fait, il sort de la demande).
- **E3. 390 px, hors des criteres (qui sont a 744).** V2 montre 3 cartes visibles contre 4 pour la liste de base ; l'etage 2 passe a
  1 colonne ; l'etage 1 demande du defilement (4 tuiles sur 8 visibles). Ce n'est pas un ecart de critere : c'est une valeur que
  personne n'a fixee.
- **E4. `;jsessionid` dans les URL** (section 3) : anterieur au lot, plus large que le constat de `lea-dev`. Route : `theo-archi`.
- **E5. Adresse et puce active apres `/retour/confirmer`** (section 3) : reproduits. Route : `camille-po` pour savoir si c'est
  acceptable, `lea-dev` pour la correction eventuelle.
- **E6. Mesures de rendu et defilement lisse.** La feuille tierce de Bootstrap pose `scroll-behavior: smooth` : tout releve de
  cotes apres un defilement doit forcer un retour instantane. Mon premier releve l'avait oublie (section 2.1). Le releve de rendu de
  `lea-dev` donne les memes chiffres que le mien sur le bandeau colle, donc le sien ne l'avait pas subi.

## 7. Porte technique : jobs lances

Arbre : `ff86c571` (`def23ead` plus mon commit : l'outil, son test, la premiere version de ce rapport), depuis ce worktree, avant
la derniere edition de cette section. Les quatre jobs de `.github/workflows/ci.yml` sont nommes ; deux etapes distantes ne le sont pas.

| Job | Lance | Commande | Resultat |
|---|---|---|---|
| `build`, etape "Verify" | oui | `bash outils/mvn-jdk21.sh -B clean verify`, racine, sans `-pl` | **BUILD SUCCESS**, exit 0, 5 min 24 s. **2493 tests, 0 echec, 0 erreur, 0 ignore** : somme des six blocs `Results:` (120, 31, 485, 1484, 367, 6), egale a la somme des attributs `tests` des `TEST-*.xml` de Surefire et Failsafe. `java.version` de tous les rapports : 21.0.10. Delta de tests Maven de mon commit : **0** (base `def23ead`, meme total que celui de `lea-dev` a son dernier commit). Mon apport est de 32 tests Python, lances par le job `outils` |
| `build`, etape "Package du fat jar" | **non, telle quelle** | `mvn -B -pl bootstrap -am package` n'est pas lancee ; le jar est produit par `verify` et par `bash outils/mvn-jdk21.sh -B -q -DskipTests -pl bootstrap -am package` (section 2.1) | sans verdict sur l'etape |
| `chemins` | oui | `verifier-chemins-test.sh`, puis `git ls-files` (moins le test du garde-fou) passe a `verifier-chemins.sh` | deux exit 0 |
| `outils` | oui | les trois motifs de la CI un a un (l'interpreteur Python est designe par `python` : sur ce poste `python3` n'est pas un interpreteur) | **11 juges, 11 exit 0** (5, 2, 4), aucun motif vide ; le onzieme est `outils/mesurer-accueil-test.py` |
| `prefixes` | oui, localement | `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, puis `git log --no-merges --format=%s 96e05fa9..HEAD \| bash .github/scripts/verifier-prefixes.sh` (5 sujets, dont le mien) | trois exit 0. La plage `BASE..SHA` de GitHub n'existe qu'au push : non lancee, aucun push |

**Ce que je n'ai pas lance** : la plage distante de `prefixes`, l'etape de package telle qu'ecrite, tout ce qui est distant. Le verdict
de la CI est celui de la CI : personne ne l'a lue ici.

Relance sur le fichier final (cette section editee) : voir la derniere ligne de ce fichier.

## 8. Ce qui n'est pas verifie

- **V1.** Aucun clavier (la Tabulation, l'ordre du focus, un bandeau collant qui masquerait la carte focalisee) : `ines-design` et
  `nadia-prof`. Le garde-fou n'existe pas (`position: sticky` apparait maintenant ligne 2010 de `recreo.css`, apres le constat
  d'absence de l'avis de faisabilite) ; je n'en ai pas ecrit.
- **V2.** Lecteur d'ecran, Safari, tablette reelle, enfant : non joues. Hauteur de telephone reelle (844 px) : non mesuree.
- **V3.** La reconnaissance des pictogrammes, la lisibilite de "Par matiere" et "Tout voir" face a "Les jeux", le magenta lu comme
  "desactive" : `nadia-prof`. Je n'ai verifie que la presence, le chargement et l'absence de `alt` (mecaniquement, par les
  garde-fous existants).
- **V4.** Le temps de rendu, la charge, un second navigateur.
- **V5.** `R1`, `R2`, `R4`, `R7` de `lea-dev` (garde-fou de ressources tierces, lecture d'`APM-9`, texte "Choisis ton niveau.", critere
  "Tenu en session") : decisions, pas mesures ; je n'ai rien a y ajouter.
- **V6.** Le job distant de `prefixes` (plage `BASE..SHA`) et l'etape `Package du fat jar` du job `build` telle qu'ecrite
  (`mvn -B -pl bootstrap -am package`) : voir la section 7.
- **V7.** La vue en session apres expiration (critere "Perdu avec elle") et le critere "Rien de persiste" (`Set-Cookie`,
  `localStorage`) : je n'ai lu que l'en-tete `Set-Cookie` d'une reponse (section 3), pas les fichiers de `static/js`, et je n'ai pas
  invalide une session.

## 9. Relance sur le fichier final

`bash outils/mvn-jdk21.sh -B -q -o -pl bootstrap -am test -Dtest=ToutesLesReferencesDeDecisionResolventTest,ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest -Dsurefire.failIfNoSpecifiedTests=false` : exit 0, 3 tests et 10 tests, 0 echec, 0 erreur (rapports XML). Perimetre : `bootstrap` et ses amonts, pas le depot ; ce sont les deux garde-fous qui lisent les `.md`. Job `chemins` relance : exit 0. Lance sur le fichier tel qu il etait avant l ajout de cette section.
