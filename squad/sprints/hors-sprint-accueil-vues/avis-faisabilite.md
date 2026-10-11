# [Hors sprint] Accueil en deux vues - avis de faisabilite de theo-archi

- **Date** : 2026-10-11 17:18 (UTC+02:00), lu par `date`.
- **Auteur** : theo-archi. Branche `theo-accueil`, worktree dedie, base `8ac33440` (tip de
  `round-two`). `bash outils/verifier-lignee.sh` sort 0, `git status --short` vide avant ecriture.
- **Emplacement** : `camille-po` n'avait fixe aucun dossier de chantier au moment de l'ecriture (son
  worktree `camille-accueil` ne porte aucun commit au-dessus de `8ac33440`, lu par `git log
  round-two..HEAD`). Ce fichier est donc au chemin par defaut ; il s'aligne a la fusion sur le dossier
  qu'elle aura fixe.
- **JDK des mesures** : 21.0.10, propriete `java.version` des rapports `TEST-*.xml` (`ADR-0071`).
  Enveloppe `bash outils/mvn-jdk21.sh`.
- **Ce commit porte** : cet avis, `ADR-0105` et ses quatre regles (F5, section 4 bis, et la regle 4 citee en section 6). Aucun code de
  production, aucun gabarit, aucune feuille de style.

## 1. Verdict

**Faisable moyennant deux ADR** : `ADR-0105`, ecrit dans ce commit parce qu'il ne depend d'aucun
arbitrage, et un second, a ecrire apres l'arbitrage de `nadia-prof`, pour la correspondance matiere
vers libelle court et pictogramme (section 4). Sept questions de produit et de pedagogie (section 8)
doivent etre tranchees avant les gabarits ; aucune ne bloque les postes `P1` et `P2` (section 7).

## 2. Les chiffres du catalogue servi, methode comprise

Methode : script jetable (scratchpad, non commite) qui lit les entrees `- id:` de
`adapters/adapter-content/src/main/resources/catalogue.yaml`, joint chaque `paquet:` au paquet de
`contenu/` qui declare cet `id`, et lit `matiere` et `niveau` dans son `descripteur`. Arbre
`8ac33440`. Exclus : les paquets de `contenu/` sans entree de catalogue (183 fichiers, 147 servis).

- **147 jeux** : CP 59, CE1 30, CM1 58. Ce sont les chiffres du besoin, reverifies.
- **11 matieres distinctes**, toutes accentuees et d'une seule graphie (`ADR-0049` tient). Par
  niveau : **CP 8, CE1 2, CM1 8**.
- **Trois groupes d'un seul jeu** : CM1 *Histoire des arts* 1, CM1 *Langues vivantes (anglais)* 1,
  CP *Langues vivantes (anglais)* 1 ; et deux groupes de deux (CP *Education musicale*, CM1
  *Education physique et sportive*). En vue a deux etages, ces tuiles ouvrent un second etage d'une
  ou deux cartes.
- **Longueur des valeurs** : de 8 caracteres (*Francais*) a 30 (*Education physique et sportive*),
  26 pour *Langues vivantes (anglais)*. C'est le declencheur qu'`ADR-0049` point 2 avait ecrit
  d'avance pour ouvrir un libelle distinct de la valeur : il est realise, constate et non anticipe.

## 3. Ou lit-on la matiere d'un jeu sans casser Clean Architecture

**F1. La donnee est deja portee jusqu'a `application`.** `GameDefinition.descripteur()` rend un
`ContentDescriptor(matiere, niveau, domaine, competence)`, rempli par l'unique chargeur
(`ADR-0007`). `ContentCatalogPort.games()` rend tout. **Aucun port sortant neuf n'est necessaire pour
grouper.**

**F2. L'adaptateur web ne peut pas la lire, et c'est voulu.** `LeChoixDesJeuxResteDerriereLePortTest`
(ArchUnit) refuse toute dependance d'`adapter-web` ou de `bootstrap` vers `ContentDescriptor`, et
`LeChoixDesJeuxNeVitPasDansLaVueTest` refuse qu'un gabarit nomme `descripteur`. Grouper dans
`PartieController` ou dans `accueil.html` est donc rouge aujourd'hui, sans regle neuve. `ADR-0044`
l'avait ecrit : *"la bonne reponse ne sera pas d'assouplir la regle mais de preparer la valeur du cote
application"*.

**F3. Ce qu'il faut ajouter au port entrant.** Le groupement par matiere vit dans `PartieService`.
Forme recommandee, a confirmer par le lot :

- une methode qui rend, pour un niveau, la liste ordonnee des groupes (une matiere et ses jeux). Elle
  sert la vue groupee et le premier etage de la vue a deux etages, qui n'en montre que les en-tetes ;
- une methode qui rend les jeux d'un niveau et d'une matiere designee par son segment d'URL, pour le
  second etage.

Le cas "aucun niveau" depend de `Q1` ; s'il existe, c'est une methode de plus, jamais un parametre
`null` (`ADR-0044`, decision 1). Le port passera de cinq a sept methodes, peut-etre huit : c'est le
cas qu'`ADR-0044` nommait (*"une troisieme facon de selectionner"*). **Je ne le juge pas encore
absurde** : trois facons, chacune liee a un ecran constate. Une quatrieme ferait passer a un point
d'entree unique prenant un critere de catalogue.

**F4. Le record.** Le groupe est un record d'`application.catalog` qui porte la matiere telle que le
paquet la declare, puis, apres le second ADR, le libelle court, le pictogramme et le segment. Il
contient des `GameDefinition` ; `adapter-web` ne lit jamais leur descripteur (F2 continue de mordre).

**F5. Le trou que le lot aurait ouvert, ferme dans ce commit.** `TexteAccentueExposeParLePortEntrantTest`
lisait deux methodes nommees du port. Une methode neuve aurait expose des libelles lus par l'enfant
sous `th:text` sans entrer dans l'inventaire. `ADR-0105`, regle 1.

**Tests ArchUnit** : aucune regle de plus n'est necessaire pour la lecture de la matiere ; les deux de
F2 tiennent. La regle ArchUnit de ce commit porte sur le choix de vue (section 4 bis).

## 4. Route, etat et partie en cours

**F6. Le choix de vue vit dans la session, par le seul porteur.** `EtatDePartieEnSession` gagne une
lecture et une ecriture de la vue ; `AccesALEtatDeSessionTest` est inchange et continue de refuser
tout autre acces a la session. La route d'ecriture **porte la vue visee** (`POST /vue/{vue}`, et
`POST /niveaux/{niveau}/vue/{vue}` depuis une liste filtree) : pas de bascule calculee depuis l'etat
courant, donc pas de cycle lire-calculer-ecrire, et un double appui rend deux fois le meme ecran.
`abandonner()` n'efface pas la vue. Valeur absente : la vue groupee, par defaut. L'enumeration des
deux vues vit dans `adapter-web` : ce sont deux presentations de l'adaptateur, pas une donnee de
contenu, et `ADR-0006` ne s'y applique pas.

**F7. htmx ou navigation complete pour le bouton : cela depend de sa place, que propose
`ines-design`.**

- **Bouton dans la zone echangee (`#jeu`)** - recommande, le moins de pieces : `hx-post`, cible
  `#jeu`, reponse `accueil :: zoneAccueil` et `HX-Push-Url` vers l'adresse canonique (precedent
  d'`ADR-0048`). `ToutEcranEchangeEstUnDocumentJouableTest.zoneEchangee` exige une seule cible
  `hx-target` dans la coquille : le bouton vise `#jeu` ou le garde-fou rougit.
- **Bouton dans l'en-tete** : un echange htmx ne l'atteint pas (`ADR-0044`, fait 4), donc son etat
  presse serait faux des le premier echange. Il faudrait une navigation complete par
  `POST` puis redirection `303`, et une route qui ne rend pas d'ecran heurte la clause d'`ADR-0020`
  *"chaque route du controleur rend un ecran"* : amendement d'`ADR-0020` et d'`ADR-0038` a la cle.

**F8. Le second etage est une route et une navigation complete.** `GET /niveaux/{niveau}/matieres/{segment}`,
tuiles en liens (`ADR-0044` point 2 : une route, jamais un parametre ; meme cout assume que les puces
de niveau, point 3 amende). Le segment est une donnee sans accent, jamais un calcul (`ADR-0049`
point 4) : il vit dans la correspondance de la section 5.

**F9. Partie en cours.** `GET /` rend la reprise de manche quand une partie est en session : aucune
liste n'est rendue, donc le bouton de vue n'est pas a l'ecran. Pour les routes neuves, le patron
existant suffit et je le recommande : le second etage pendant une partie rend la confirmation
directe, comme `GET /niveaux/{niveau}`, avec son `POST .../confirmer` qui garde le contexte ; la
route de vue ecrit la vue et **n'abandonne jamais une partie**, elle rend ce que rendrait la route de
contexte. Le comportement vu par l'enfant est a confirmer (`Q3`).

**F10. Toute route neuve rougit deux garde-fous jusqu'a ce qu'un ecran l'exerce.**
`ToutEcranEchangeEstUnDocumentJouableTest.chaqueRouteDuControleurEstExerceeParCeGardeFou` et son
homologue d'`AucunArretDeTabulationNEstDestructeurTest` comparent par egalite les routes declarees aux
routes exercees. Quatre routes neuves au moins (deux de vue, le second etage, sa confirmation), chacune
avec et sans partie en cours : c'est du cout de lot, voulu par `ADR-0038`.

**F11. La phrase de filtre.** Tout fragment rendu sous un niveau porte `.filtre-niveau`, sans quoi la
puce active perd sa couleur (`S16-6`, `LApparenceActiveNExisteQueQuandLEcranMontreLeFiltreTest`).
Les routes de vue et de second etage sont concernees.

### 4 bis. Rien n'est persiste : ce qui le garde

`ADR-0105`, regles 2 et 3 : le seul cookie du produit reste le marqueur de partie (regle ArchUnit,
exemption de deux methodes nommees), et le JavaScript maison n'ecrit dans aucun stockage du
navigateur (`cookie`, `indexedDB`, `caches.` rejoignent `localStorage` et `sessionStorage`). C'est le
chemin le moins cher d'une bascule, et rien ne le refusait.

## 5. Ou vit la correspondance matiere vers libelle court et pictogramme

**Recommandation : un fichier de donnees unique**, `matieres.yaml`, a cote de `catalogue.yaml` dans
`adapter-content`, **une ligne par matiere servie** : la matiere exacte du paquet (cle de jointure),
le libelle court, le pictogramme, le segment d'URL. **L'ordre des lignes est l'ordre d'affichage des
groupes**, ecrit une fois. Lu par l'unique chargeur (`ADR-0007`, dette 3), expose par une methode de
`ContentCatalogPort`, joint aux groupes dans `PartieService`.

Ecartees : un champ par paquet (147 copies d'une valeur de matiere, la dette 2 et `ADR-0049` point
2) ; une table dans le gabarit, le controleur ou la feuille (`ADR-0006`, `ADR-0049` point 3) ; une
enumeration Java (`ADR-0006`).

**Le garde-fou que le second ADR devra porter**, avec sa regle dans le meme commit :

1. chaque matiere servie a exactement une ligne, et chaque ligne nomme une matiere servie - le
   plafond vaut la dette ; jointure exacte, avec un message qui nomme la ligne quand elle ne differe
   que par le repliage d'`ADR-0049` (accent, casse, blanc) ;
2. libelle court, pictogramme et segment uniques chacun ;
3. segment de la forme `[a-z0-9-]+` ;
4. libelle court accentue et inventorie : il passe par le port entrant, donc par la regle 1
   d'`ADR-0105` ;
5. selon la forme du pictogramme (`Q7`) : un fichier sert de pictogramme -> il existe sous `static/`
   et entre sous un cliquet de poids, patron d'`ADR-0073` ; un caractere -> regime de `DEC-0002`,
   juge par `nadia-prof`, rien d'automatisable sur le sens ;
6. aucun gabarit ni la feuille maison ne nomme une matiere ou un segment : la population
   d'`AucunGabaritNiFeuilleMaisonNeNommeUnContenuParticulierTest` s'etend aux matieres et aux
   segments. C'est ce qui refuse une couleur par matiere ecrite en CSS (`.matiere-francais`), le
   chemin le moins cher si `ines-design` propose des couleurs : une couleur par matiere, si elle est
   retenue, devient une colonne de ce fichier.

**Pourquoi je ne l'ecris pas aujourd'hui** : les valeurs sont de `nadia-prof`, la forme du
pictogramme fixe la clause 5, et un garde-fou ecrit sans le fichier serait vert a vide ou rouge des
sa naissance. L'ADR amendera `ADR-0049` (point 2 : le declencheur est realise ; point 4 : le segment
vit dans la ligne de matiere, pas dans chaque paquet).

## 6. Garde-fous que le changement traverse

**Methode** : recherche des tests d'`adapter-web` qui lisent `templates` ou `recreo.css`
(`grep -l`), puis lecture des clauses citees ci-dessous. **Aucun candidat n'existe encore, donc aucun
n'a ete passe devant eux** : chaque candidat d'`ines-design` y passe avant d'etre retenu (`squad/README.md`,
section 9, amendement de s18). Lus : les clauses que je cite. Reperes par recherche et non lus en
entier : les autres classes nommees.

| Garde-fou | Ce que le changement y rencontre |
|---|---|
| `ToutEcranEchangeEstUnDocumentJouableTest`, `AucunArretDeTabulationNEstDestructeurTest` | chaque route neuve exercee, avec et sans partie (F10) ; une seule cible `hx-target` (F7) |
| `LeChoixDesJeuxNeVitPasDansLaVueTest`, `LeChoixDesJeuxResteDerriereLePortTest` | le groupement vient du port (F2) |
| `TexteAccentueExposeParLePortEntrantTest` | chaque methode neuve du port lue, chaque libelle court inventorie (`ADR-0105` regle 1) |
| `TexteAccentueDesGabaritsTest` | tout texte litteral neuf (libelle du bouton, en-tetes ecrits en dur) accentue |
| `TailleDesTextesEtBoutonsTest`, `LActionNeDeclarePasMoinsQueLaSortieTest`, `LHabillageNeRogneJamaisUnElementPressableTest`, `LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest` | une tuile en lien n'y entre que par un `href` litteral (`ADR-0105` regle 4) ; plancher de taille, saillance face aux actions, aucun rognage d'un pressable par une carte |
| `LHabillageResteImmobileTest` | carte, en-tete de groupe et tuile non pressable sont de l'habillage : ni `:hover`, ni `transition` |
| `LActionNeDeclarePasMoinsQueLaSortieTest` (roles) | le bouton de vue doit declarer un role de la liste fermee ; `entree` est defini pour un habillage permanent (`ADR-0043`). S'il vit dans la zone, son role est a trancher par un amendement d'`ADR-0043`, cote domine |
| `AucunEtatDePressableNEstAbandonneAuTiersTest`, `UnPressableAppuyeConserveSonContrasteEtSonAnneauDeFocusTest` | toute classe neuve de pressable declare ses etats face a `.btn` de la bibliotheque |
| `CabinetDeLaMachineTest` | `.demarrer` garde sa forme ronde et son ombre ; une carte l'entoure, elle ne la remplace pas |
| `LaLargeurDemandeeParUneRangeeEstEpingleeTest`, `LaLargeurOffreeAUneRangeeEstUneFonctionEpingleeTest`, `UnPlafondMaisonNAffamePasLaRangeeTest`, `ViewportEtLargeurTelephoneTest` | une grille de cartes ou de tuiles est une rangee neuve ; a mesurer a 360 px |
| `PartieHttpTest` (`bootstrap`) | `GET /` doit porter `button.demarrer` : vrai en vue groupee par defaut, faux si le premier etage devenait la vue par defaut |
| `EnTeteEtMenuTest` | seulement si le bouton va dans l'en-tete |

**Non gardable, et dit** : qu'un en-tete collant ne masque pas la carte qui recoit le focus clavier.
Aucun garde-fou ne lit `position: sticky` (recherche sur `src/test/java` et `recreo.css` : zero
occurrence) et la geometrie du focus n'est mesurable qu'au rendu (`outils/mesurer-rendu.sh`,
`ADR-0025`). Mesure d'`ines-design`, jugement de `nadia-prof`.

## 7. Decoupage et ordre

Estimations en commits et en volume, **jugees et non mesurees**.

**Avant tout gabarit** :

- **A1 `camille-po`** : la `DEC` du chantier, qui transcrit la demande et porte les reponses a `Q1`
  a `Q6` ;
- **A2 `ines-design`** : la proposition (cartes, tuiles, en-tetes collants, place et libelle du
  bouton, libelles courts, forme du pictogramme), passee devant les garde-fous de la section 6 ;
- **A3 `nadia-prof`** : arbitrage prealable des libelles courts, des pictogrammes et du texte du
  bouton ;
- **A4 `theo-archi`** : le second ADR et son garde-fou (section 5), apres `A3`.

**Postes** :

| Poste | Agent | Contenu | Depend de | Estimation |
|---|---|---|---|---|
| `P1` | `marc-dev` | groupement par matiere dans `PartieService`, methodes du port, record de groupe, tests de `PartieService` ; lecture par l'inventaire (regle 1) | rien | 1 commit, ~200 lignes dont tests |
| `P2` | `lea-dev` | vue en session dans `EtatDePartieEnSession`, enumeration des vues, tests du porteur (defaut, ecriture idempotente, survie a `abandonner()`) | rien | 1 commit, ~120 lignes |
| `P3` | `marc-dev` | `matieres.yaml`, lecture par le chargeur unique, methode de `ContentCatalogPort`, jointure ; ordre des groupes pris dans le fichier | `A3`, `A4`, `P1` | 1 commit, ~250 lignes |
| `P4` | `lea-dev` | routes de vue, second etage et sa confirmation ; corpus des deux garde-fous de routes ; gabarits des deux vues, feuille de style | `A1`, `A2`, `A3`, `P2`, `P3` | 2 a 3 commits, le plus gros poste |
| `P5` | `yanis-qa` | criteres traduits en tests, tirage de mutation sur les regles d'`ADR-0105` et du second ADR | `P4` | - |

Ce qui precede l'arbitrage de `nadia-prof` : `P1` et `P2`, sans risque de reprise. `P3` attend les
valeurs ; `P4` attend la proposition, l'arbitrage et la `DEC`. Le Done pedagogique reste a
`nadia-prof`, apres avoir joue l'increment.

## 8. Questions de produit et de pedagogie, routees a `sofia-sm`

Je ne les tranche pas.

- **Q1** - L'accueil sans niveau (`GET /`, premier chargement, "Les jeux") : la vue groupee y mettrait
  147 cartes de trois niveaux sous 11 en-tetes, et depuis `ADR-0078` aucun titre ne dit son niveau.
  Melanger, regrouper par niveau puis par matiere, ou demander le niveau d'abord ?
- **Q2** - "Les jeux" en vue a deux etages : retour au premier etage de quel niveau ? Le motif de
  `BARRE-7` (un bouton qui rend tantot le meme ecran, tantot un autre) se pose de nouveau.
- **Q3** - Appui sur une tuile pendant une partie : la meme confirmation que la puce de niveau (F9) ?
- **Q4** - Un groupe d'un seul jeu (section 2) : un second etage a une carte, ou autre chose ?
- **Q5** - L'ordre des matieres dans les deux vues (il s'ecrit une fois, dans le fichier de la
  section 5).
- **Q6** - Le bouton de vue s'adresse-t-il a l'enfant ou a l'adulte, et que dit-il ?
- **Q7** - Forme du pictogramme (fichier ou caractere, `DEC-0002`) et couleur par matiere ou non.

## 9. Jobs de `.github/workflows/ci.yml` lances pour ce commit

Sur l'arbre de ce commit, avant le commit, depuis ce worktree :

- **`build`** : `bash outils/mvn-jdk21.sh -B clean verify` sur la racine, sans `-pl` : **BUILD
  SUCCESS**, code de sortie 0. **2412 tests, 0 echec, 0 erreur, 0 ignore** : somme des six blocs
  `Results:` du reacteur (120, 18, 445, 1470, 353, 6), egale a la somme des attributs `tests` des
  rapports XML de Surefire et Failsafe. `java.version` de tous les rapports : 21.0.10.
- **`chemins`** : `verifier-chemins-test.sh` sort 0, puis `git ls-files` (fichiers neufs ajoutes a
  l'index par `git add -N`) passe a `verifier-chemins.sh` : sortie 0.
- **`outils`** : la boucle du job sur ses trois motifs, 10 juges (5, 2, 3), 0 echec. L'interpreteur
  Python est designe a la main : sur ce poste, `python3` n'est pas un interpreteur.
- **`prefixes`** : `verifier-prefixes-test.sh` et `commit-atteignable-test.sh` sortent 0 ; le sujet de
  ce commit passe a `verifier-prefixes.sh` sort 0. Le job distant juge une plage que seul un push
  definit : non lance, et aucun push n'est fait.

Le dernier paragraphe de cette section a ete ecrit apres la mesure ; les garde-fous qui lisent les
`.md` (`ToutesLesReferencesDeDecisionResolventTest`, `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`)
ont ete relances sur le fichier final, section 9 bis.

### 9 bis. Relance sur le fichier final

`bash outils/mvn-jdk21.sh -B -q -o -pl bootstrap -am test
-Dtest='ToutesLesReferencesDeDecisionResolventTest,ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest'` :
code de sortie 0, 3 tests et 10 tests, 0 echec (rapports XML). Perimetre : `bootstrap` et ses amonts,
pas le depot ; c'est la seule partie du depot que l'ajout de la section 9 pouvait faire rougir. Job
`chemins` relance : sortie 0.

## 10. Apres l'arbitrage de `nadia-prof` : `ADR-0106`, et le decoupage de P3 et P4

- **Date** : 2026-10-11, lue par `date` : 2026-10-11 18:42 (UTC+02:00).
- **Branche** : `theo-accueil-matieres`, base `9f03ff77` (tip de `round-two`, qui porte P1, P2 et
  l'arbitrage `APM-1` a `APM-11`).
- **Ce commit porte** : `ADR-0106`, ses regles (une ArchUnit, sept clauses JUnit), la donnee
  `matieres.yaml`, les dix pictogrammes acceptes, `ContentCatalogPort.matieres()` et sa lecture par le
  chargeur, l'ordre et l'absence de repli dans `PartieService.matieresDuNiveau`, la correction du bean de
  `ContenuConfiguration`, et les amendements d'`ADR-0049` et d'`ADR-0043`. Aucun gabarit, aucune feuille,
  aucune route.

### 10.1 Ce que j'ai tranche, et ou c'est ecrit

| Question | Reponse | Ou |
|---|---|---|
| Ou vit la correspondance (`Q-T1`) | une ligne par matiere dans `matieres.yaml`, lue par le chargeur unique, exposee par `ContentCatalogPort.matieres()` | `ADR-0106` decision 1 |
| Ordre (`Q-T2`) | le rang de la ligne ; aucun repli pour une matiere sans ligne | `ADR-0106` decisions 1 et 2 |
| Segment au port (`E2` de `marc-dev`) | oui : `groupeDeLaMatiere(niveau, segment)`, `Optional<GroupeDeJeux>`, remplace `jeuxDeLaMatiere` | `ADR-0106` decision 5 |
| `data-role` | tuile `action`, bouton de vue `entree` dans ses trois etats ; `ADR-0043` decision 1 amendee | `ADR-0106` decision 6 |
| Place du bouton | `#jeu`, jamais l'en-tete | `ADR-0106` decision 7 |
| Route de vue | `POST /niveaux/{niveau}/vue/{vue}` : ecrit la vue, n'abandonne jamais ; sans partie rend la zone du niveau et pousse `/niveaux/{niveau}` ; avec partie rend la confirmation de la puce ; vue inconnue 404 | `ADR-0106` decision 8 |

### 10.2 P3 - `marc-dev`

Depend de : ce commit. Ne depend pas de `Q-U1`, ni du pictogramme de Maths.

1. `GroupeDeJeux(Matiere matiere, List<GameDefinition> jeux)` : la ligne entiere au lieu de sa chaine.
2. `groupeDeLaMatiere(String niveau, String segment)` rend `Optional<GroupeDeJeux>` et remplace
   `jeuxDeLaMatiere` ; segment inconnu ou matiere absente du niveau : `Optional.empty()`.
3. `TexteAccentueExposeParLePortEntrantTest` : la methode neuve dans `METHODES_DU_PORT_LUES_PAR_L_INVENTAIRE`
   et appelee pour chaque groupe ; les chaines neuves exposees (six libelles qui different de la matiere,
   onze noms de pictogramme, vingt-deux teintes, onze segments) dans l'inventaire, chacune avec son motif
   (`APM-1`, N1, N2, N6).
4. Tests de `PartieService` : la resolution par segment (trouve, segment inconnu, matiere d'un autre niveau),
   et une mutation qui compare la matiere au lieu du segment.

Estimation jugee : un commit, environ 200 lignes dont tests.

### 10.3 P4 - `lea-dev`

Depend de : P3 ; le pictogramme de Maths refait par `ines-design` et accepte par `nadia-prof` (`APM-2`,
routage de son arbitrage : *"rien avant le pictogramme de Maths rejuge et le second ADR"*) ; la teinte de
Histoire des arts, si elle arrive avec lui. P2 et `DEC-0117` sont la.

1. Routes de `ADR-0106` decision 8, chacune exercee avec et sans partie dans les corpus de
   `ToutEcranEchangeEstUnDocumentJouableTest` et d'`AucunArretDeTabulationNEstDestructeurTest` (F10) ; le
   catalogue de test de `PartieControllerTest` recoit des lignes de matiere.
2. Gabarits de V2, de l'etage 1 et de l'etage 2 dans `accueil.html`, la liste plate gardee pour `/` ;
   `.filtre-niveau` sur toute zone rendue sous un niveau (F11) ; tuile `data-role="action"`, bouton de vue
   `data-role="entree"`, `href` litteral sur tout lien (`ADR-0105` regle 4).
3. Teinte par `th:style` sur la tuile et le bandeau seulement (`--teinte-fond`, `--teinte-contour`), lue par
   `var()` dans `recreo.css` ; pictogramme `<img alt="">` a cote de son libelle.
4. Le cliquet `aucunPictogrammeDeMatiereNEstEncoreServi` rougit : il est remplace dans le meme commit par
   l'exigence d'au moins un pictogramme sur un ecran de V2, de l'etage 1 et de l'etage 2.
5. Le pictogramme de Maths depose, son empreinte dans `LePictogrammeDeMatiereEstUnFichierArbitreTest.ARBITRES`,
   son nom retire d'`EN_ATTENTE`, avec la reference de l'arbitrage ; meme geste pour la teinte et
   `TEINTES_GRISES_EN_ATTENTE`.
6. Tests de route qui gardent `APM-7` a `APM-11` : bouton de vue absent de l'en-tete et de tout ecran de jeu,
   jamais "Les jeux" ; la session ne recoit que la vue (`APM-8`, `EtatDePartieEnSessionTest`) ; "Les jeux"
   ne rend jamais un etage 2 ; toute tuile pointe vers `/niveaux/{niveau}/matieres/{segment}`, verifie sur
   une matiere d'un seul jeu ; tuile et etage 2 pendant une partie rendent la confirmation de la puce, et la
   route de vue n'abandonne jamais.

Estimation jugee : deux a trois commits, le plus gros poste.

### 10.4 Ce qui attend `Q-U1`, et rien d'autre

Tout ce qui touche `/`, `/retour` et `/retour/confirmer` : la page sans niveau, la destination de "Les
jeux", une route de vue sans niveau, et le critere "Tenu en session" d'`HS-ACCUEIL-BASCULE` (contradiction
relevee par `nadia-prof`, section 1 de son arbitrage, routee a `camille-po`). Si l'utilisatrice retient
l'option (c) de `nadia-prof`, aucune liste n'est rendue sur `/` et aucune route de vue sans niveau n'est
necessaire. P3 et P4 tels que decrits ci-dessus n'en dependent pas : ils ne touchent pas ces trois routes.

### 10.5 Jobs de `.github/workflows/ci.yml` lances pour ce commit

Sur l'arbre de ce commit, avant le commit, depuis ce worktree.

- **`build`** : `bash outils/mvn-jdk21.sh -B clean verify` sur la racine, sans `-pl` : **BUILD SUCCESS**, code
  de sortie 0. **2461 tests, 0 echec, 0 erreur, 0 ignore** : somme des six blocs `Results:` du reacteur (120,
  28, 459, 1483, 365, 6), egale a la somme des attributs `tests` des rapports XML de Surefire et Failsafe.
  `java.version` de tous les rapports : 21.0.10. Le jar `bootstrap/target/recreo.jar` est produit ; il n'a pas
  ete lance, ce commit ne change aucun ecran.
- **`chemins`** : `verifier-chemins-test.sh` sort 0 ; `git ls-files` (fichiers neufs ajoutes a l'index) passe
  a `verifier-chemins.sh` : sortie 0.
- **`outils`** : la boucle du job sur ses trois motifs, 10 juges (5, 2, 3), 0 echec ; l'interpreteur Python
  est designe a la main.
- **`prefixes`** : `verifier-prefixes-test.sh` et `commit-atteignable-test.sh` sortent 0 ; le sujet de ce
  commit passe a `verifier-prefixes.sh` sort 0. La plage distante n'existe qu'au push : non lance, aucun push.

Cette section est ecrite apres la mesure ; les deux garde-fous qui lisent les `.md`
(`ToutesLesReferencesDeDecisionResolventTest`, `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`) et le
job `chemins` sont relances sur le fichier final, section 10.6.

### 10.6 Relance sur le fichier final

`bash outils/mvn-jdk21.sh -B -q -o -pl bootstrap -am test
-Dtest='ToutesLesReferencesDeDecisionResolventTest,ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest'` :
code de sortie 0, 3 tests et 10 tests, 0 echec (rapports XML). Perimetre : `bootstrap` et ses amonts, pas le
depot ; c'est la seule partie du depot que les sections 10.5 et 10.6 pouvaient faire rougir. Job `chemins`
relance : sortie 0.
