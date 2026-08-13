# ADR-0036 - Une image maison entre par son chemin, jamais par son empreinte ; et un garde-fou ne lit pas un binaire comme du texte

- **Statut** : Accepte
- **Date** : 2026-08-13
- **Auteur** : theo-archi
- **Sprint** : s10 (l'utilisatrice ouvre le depot aux fichiers image)
- **Ne remplace rien.** **Amende `ADR-0010`** (perimetre du garde-fou de chemins de poste de
  travail) et **`ADR-0014`** (perimetre de la garantie hors ligne cote gabarits).

## Contexte

L'utilisatrice juge que le meuble d'arcade livre en s10 est tres loin des deux images de reference
qu'elle avait fournies - un meuble illustre, fronton peint, visage, enseigne, rouleaux - et que
travailler des formes CSS ne l'en rapprochera pas. Elle a decide d'ouvrir le depot aux fichiers
image. La question posee a l'architecture etait : quel `ADR` autorise cela, et sous quelles clauses.

**La question etait mal posee, et c'est la premiere chose que cet `ADR` doit ecrire.** Il a ete
repete pendant ce sprint que "le depot interdit tout fichier image". C'est faux, et l'etait deja
avant ce sprint :

- `mvn -B verify` passe **vert et complet** avec un PNG de 221 ko depose sous
  `static/images/` et appele par `background-image: url('/images/fronton.png')` dans
  `recreo.css` - 390 tests, aucun echec, mesure sur JDK 21 ;
- il passe aussi vert avec `<img th:src="@{/images/fronton.png}">` dans `fragments/manche.html` ;
- et il y a **deja** une image binaire servie et affichee dans ce depot depuis S6-1 :
  `favicon.ico`, 1 014 octets, posee dans l'en-tete par
  `<img th:src="@{/favicon.ico}" alt="" class="icone-entete">`. Sa recoloration a meme son propre
  garde-fou (`LeFondDeLIconePorteLaCouleurDuBandeauTest`).

Il n'y a donc **jamais eu d'interdit sur les images maison**, et aucune clause d'autorisation n'est
a ecrire. Ce que la mesure a trouve, en revanche, est autre chose, et c'est plus grave que la
question de depart.

### Fait 1 - la garantie hors ligne d'`ADR-0014` ne regardait aucun gabarit

`RessourcesTiercesTest.verifierAucuneRessourceExterne` promet que rien de ce que l'application
demande au navigateur n'est cherche sur le reseau, et que chaque cible existe comme fichier de
`static/`. Sa branche gabarits selectionnait
`link[href], link[th\:href], script[src], script[th\:src]`.

Mesure sur la version de jsoup que ce build utilise reellement (**1.23.1**, resolue depuis le depot
Maven local ; verifiee par `dependency:build-classpath` et non par lecture du POM) :
`script[th\:src]` selectionne **zero element**, alors que `attr("th:src")` rend bien la valeur. La
forme echappee du deux-points n'est pas comprise par l'analyseur de selecteurs de cette version.

Or les cinq references de ressource de la production sont **toutes** ecrites en Thymeleaf
(`th:href`, `th:src`). Cette branche ne regardait donc **rien du tout**. Mesure, et non deduction :
sur le garde-fou tel qu'il etait a `2d34a15`, en remplacant dans `accueil.html` l'icone d'en-tete par
`<img src="https://cdn.example.org/icone.png">` **et** la feuille vendorisee par
`th:href="@{/vendor/inexistante.css}"`, les 12 cas de la classe restent **verts**. Ni la ressource
distante ni le fichier absent n'etaient vus.

Le jeu fautif de cette clause, `gabarit-sur-cdn`, ecrit `href="https://..."` en attribut simple :
il prouvait le critere sur une forme que la production n'ecrit nulle part. C'est la lecon de s05,
sixieme occurrence : **une fixture ecrite par l'auteur d'une regle ressemble a ce qu'il imagine, pas
a ce que le monde ecrit.** Cette fois elle a coute la totalite d'une garantie, pas une clause.

### Fait 2 - le garde-fou de chemins de poste de travail refuse une image sur un coup de des

`verifier-chemins.sh` lit **tous** les fichiers suivis, y compris les binaires. Sa forme
`DRIVE_WIN` - lettre, deux-points, antislash ou slash, caractere alphanumerique - est une sequence
de quatre octets. Les octets compresses d'une image en contiennent tot ou tard une, sans qu'aucun de
ces octets ait le moindre rapport avec un chemin.

Mesure sur 21 PNG fabriques pour l'occasion, de 72 ko a 500 ko : **4 sont refuses**, dont le premier
fronton d'essai, qui en portait deux (offsets 102745 et 105396). `favicon.ico` y echappe par sa
seule petitesse, 1 014 octets. Autrement dit : le jour ou le fronton entre, la CI a environ une
chance sur cinq de rougir sur un motif qui n'a aucun rapport avec l'image, et ce tirage se refait a
chaque image ajoutee.

C'est le mode de defaillance le plus couteux qui existe pour un garde-fou : il ne rate pas une
faute, il en invente une. Une squad qui rencontre cela deux fois desactive le garde-fou.

### Fait 3 - la condition C5 cesse d'etre structurelle

La condition C5 de `nadia-prof` exige que le jeu d'images du cabinet ne depende **ni de l'item
tire, ni du numero de la colonne, ni de la reponse attendue**. Tant que le decor etait dessine par
des regles statiques de `recreo.css`, cette propriete etait tenue **par construction** : une regle
CSS ne sait pas quel item a ete tire. Une image designee par un attribut de gabarit, elle, peut
varier. Mesure : `<img th:src="@{'/images/' + ${jeuId} + '.png'}">` passe **vert** sur les 390 tests
de `2d34a15`.

Le critere qui l'aurait refuse existait pourtant depuis `ADR-0014` - `cheminDeGabarit` refuse une
expression calculee - mais il vivait derriere le selecteur du fait 1, et **n'avait aucun jeu
fautif** : il n'avait donc jamais rien refuse.

## Decision

### 1. Une image dessinee pour ce projet vit sous `static/`, et est designee par un chemin litteral

Aucune autorisation a accorder, elle existe deja. Ce que cet `ADR` fixe est la forme :
`background-image: url('/images/<nom>')` dans une feuille maison, ou un attribut `src` / `th:src`
dont la valeur est un chemin **ecrit en clair**. Jamais un chemin construit par concatenation ou par
expression.

### 2. Aucune empreinte pour une image maison, et le motif est ecrit

`ADR-0014` exige une ligne de provenance avec empreinte SHA-256 pour tout fichier de
`static/vendor/`. Cette exigence est **refusee** pour une image dessinee ici, et pas par
commodite : l'empreinte d'une ressource vendorisee prouve que **la copie vaut encore l'original
publie ailleurs**. Une image maison n'a pas d'original ailleurs. Son empreinte ne serait egale qu'a
elle-meme, et l'assertion se lirait "ce fichier a bien le condensat ecrit a cote de lui" - vraie
par construction, fausse seulement quand quelqu'un retouche legitimement l'image et oublie de
recopier le condensat. Elle transformerait un travail normal en corvee et ne protegerait rien.

Ce qui compte pour une image maison n'est pas son integrite mais son **origine juridique** : une
illustration tierce glissee dans `static/images/` sans licence est un vrai risque. Aucune empreinte
ne le detecte, et aucun automate non plus - on ne lit pas la provenance dans les octets d'un PNG.
Cette verification-la reste humaine, en revue de diff. Elle est **nommee comme telle** ci-dessous
dans "ce que cet `ADR` ne garantit pas", et non deguisee en clause.

### 3. Le perimetre d'une reference de ressource est un attribut, pas une liste de balises

`src` et `th:src` designent toujours un fichier que le navigateur va chercher, sur n'importe quel
element - image, script, cadre, son, video. `href` n'en designe un que sur `link` : sur `a` il
designe une route de l'application, qui n'est pas un fichier de `static/`.

Ce critere remplace la liste de balises, qui avait deja echoue par omission en laissant dehors
l'unique `img` de la production. Et il est applique **par presence d'attribut en Java**, pas par
selecteur CSS, parce que le fait 1 montre que le selecteur ne sait pas lire le deux-points de
Thymeleaf dans cette version de jsoup.

### 4. Un garde-fou de contenu redactionnel ne lit pas un binaire

`verifier-chemins.sh` retire les fichiers binaires de son perimetre, et les retire **par leur
contenu** - `grep -I`, qui les reconnait a leurs octets nuls - jamais par une liste d'extensions,
qui echouerait par omission au premier `.woff` ou `.pdf`. Ce que ce garde-fou protege est un depot
lisible et portable ; cela n'existe pas dans les octets d'une image.

### 5. Ce que cet `ADR` ne decide pas : le poids

Il n'y a **aucun plafond de poids** dans les clauses ci-dessous, et c'est un refus motive, pas un
oubli.

Reference mesuree, avec sa methode. Perimetre : tous les fichiers de
`adapters/adapter-web/src/main/resources/static/`, tailles sur disque, aucune exclusion, a
`2d34a15`. La compression de transport n'est pas prise en compte.

| Fichier | Octets |
|---|---|
| `vendor/bootstrap.min.css` | 232 111 |
| `vendor/htmx.min.js` | 51 238 |
| `css/recreo.css` | 36 321 |
| `vendor/provenance.txt` | 1 475 |
| `favicon.ico` | 1 014 |
| `js/recreo.js` | 597 |
| **Total servi** | **322 756** |

L'ecran de jeu en charge **321 281** : tout sauf `vendor/provenance.txt`, qui est servi par le
serveur mais qu'aucune page ne demande. La seule image du depot pese 1 014 octets.

Je n'ecris pas de plafond parce que je ne peux pas en justifier un :

- **un nombre absolu** (256 ko, 100 ko) serait invente. "Quel poids est acceptable sur le telephone
  d'un enfant, dans une classe, sur le reseau de l'ecole" est une cible **produit et pedagogique** :
  elle appartient a `sofia-sm` et a `nadia-prof`, pas a moi ;
- **un plafond relatif** ("aucune image ne pese plus que les feuilles et scripts du meme ecran")
  ferait dependre le verdict sur une image du poids de `bootstrap.min.css`. Un garde-fou qui rougit
  sur une image parce qu'une bibliotheque a maigri est exactement la faute que le fait 2 decrit ;
- **un cliquet** est le bon instrument, et son plafond doit valoir **exactement la taille de la
  dette**. La dette d'images vaut aujourd'hui 1 014 octets : un cliquet ecrit maintenant
  interdirait la premiere illustration. Il ne peut etre pose que par le commit qui la fait entrer,
  avec le poids reellement retenu.

**Dette explicite, et non clause** : le commit qui livre la premiere image illustree pose le cliquet
de poids, sur la valeur qu'il installe, apres que `sofia-sm` ait fixe la cible. Tant que ce cliquet
n'existe pas, rien dans ce depot n'empeche une illustration de 4 Mo. C'est ecrit ici pour que
personne ne croie le contraire.

## Regle executable

Fichier : `adapters/adapter-web/src/test/java/com/recreo/adapter/web/RessourcesTiercesTest.java`

```java
/** Ce qu'un gabarit ecrit quand il demande une ressource par une expression Thymeleaf. */
private static final List<String> ATTRIBUTS_THYMELEAF = List.of("th:src=", "th:href=");

private static List<Element> referencesDeRessource(Document document) {
    List<Element> references = new ArrayList<>();
    for (Element element : document.getAllElements()) {
        boolean ressource = element.hasAttr("src") || element.hasAttr("th:src")
                || ("link".equals(element.normalName())
                    && (element.hasAttr("href") || element.hasAttr("th:href")));
        if (ressource) {
            references.add(element);
        }
    }
    return references;
}
```

Cette selection alimente les trois exigences deja ecrites par `ADR-0014`, qui deviennent pour la
premiere fois effectives sur les gabarits : la cible n'est pas distante, elle existe comme fichier
de `static/`, et elle est ecrite en chemin litteral. C'est cette troisieme exigence qui porte
desormais la condition C5.

Clause anti-vacuite, dans le meme fichier. Elle ne se prouve pas sur une fixture mais sur le
materiau reel, et son ancre est independante de ce qu'elle verifie - le **texte** du gabarit dit
qu'une reference Thymeleaf y est ecrite, la selection **DOM** doit donc en trouver au moins une :

```java
@Test
void aucuneReferenceEcriteEnThymeleafNEchappeALaSelection() {
    List<Path> gabaritsAvecThymeleaf = new ArrayList<>();
    for (Path gabarit : gabarits(RACINE_PRODUCTION)) {
        String source = lire(gabarit);
        if (ATTRIBUTS_THYMELEAF.stream().noneMatch(source::contains)) {
            continue;
        }
        gabaritsAvecThymeleaf.add(gabarit);
        assertThat(referencesDeRessource(Jsoup.parse(source, gabarit.toString())))
                .as("ADR-0036 : %s ecrit une reference de ressource en Thymeleaf, et la selection "
                        + "de ce garde-fou n'en voit aucune ; un selecteur qui ne comprend pas la "
                        + "forme reellement ecrite rend cette regle verte sans rien verifier",
                        gabarit)
                .isNotEmpty();
    }

    assertThat(gabaritsAvecThymeleaf)
            .as("ADR-0003 : cette clause ne prouve rien si aucun gabarit de production n'ecrit de "
                    + "reference de ressource en Thymeleaf")
            .isNotEmpty();
}
```

Fichier : `.github/scripts/verifier-chemins.sh`

```sh
trouvailles="$(grep -I -nEH "${REGEX}" "${FICHIERS[@]}" 2>/dev/null || true)"
```

### Preuves de morsure

Trois jeux fautifs neufs sous
`adapters/adapter-web/src/test/resources/contreexemple/RessourcesTiercesTest/`, chacun ecrit dans la
forme que la production ecrit reellement - un `img` sous `.cabinet-bandeau`, en Thymeleaf :

| Jeu fautif | Faute | Rouge attendu |
|---|---|---|
| `image-sur-cdn` | `<img src="https://cdn.example.org/arcade/fronton.png">` | "sur le reseau" |
| `image-absente` | `<img th:src="@{/images/fronton.png}">`, et `static/` ne porte pas ce fichier | "n'est servi par aucun fichier" |
| `image-calculee` | `<img th:src="@{'/images/fronton-' + ${enonce} + '.png'}">` | "expression calculee" |

`image-calculee` est le **premier** contre-exemple du refus des chemins calcules. Le critere
existait depuis `ADR-0014` et n'avait jamais rencontre de jeu fautif ; c'est lui qui porte C5.

Morsure sur du materiau que je n'ai pas ecrit, et dans les deux sens :

- **avant**, sur `2d34a15` : l'icone d'en-tete de production pointee vers un CDN **et** la feuille
  vendorisee pointee vers un fichier inexistant laissent les 12 cas **verts** ;
- **apres** : la meme mutation de l'icone d'en-tete rend un rouge qui nomme le fichier, la cible et
  le motif - `accueil.html va chercher https://cdn.example.org/icone.png sur le reseau`.

Pour `verifier-chemins.sh`, deux cas neufs dans `verifier-chemins-test.sh` portant **la meme chaine
de caracteres**, pour que la difference de verdict vienne du caractere binaire du fichier et non
d'un affaiblissement de la regex : un fichier a octets nuls contenant un vrai chemin de poste est
accepte, les memes octets sans octet nul restent refuses. Et la mesure sur materiau reel : les 20
PNG du lot, dont 3 etaient refuses avant, passent tous apres.

Ce garde-fou a d'ailleurs mordu **mon propre commentaire d'explication** de ce correctif, qui citait
la sequence fautive en clair. Deuxieme fois qu'`ADR-0010` mord son auteur ; le commentaire nomme
desormais la forme sans l'ecrire, comme `CLAUDE.md` le fait.

## Consequences

- **La garantie hors ligne d'`ADR-0014` existe pour la premiere fois sur les gabarits.** Cinq
  references de production sont desormais reellement verifiees, alors qu'aucune ne l'etait. C'est un
  gain net, et c'est aussi l'aveu que la ligne "aucune ressource externe a l'execution" du depot
  reposait sur rien depuis s06.
- **Un chemin de poste de travail cache dans un binaire n'est plus detecte.** Consequence assumee de
  `grep -I`. Ce n'est pas un renoncement de securite : `ADR-0010` protege la lisibilite et la
  portabilite du depot, pas ses secrets, qui vivent dans `.env` (jamais commite). Un chemin dans un
  binaire ne se lit dans aucune documentation et ne casse le build de personne.
- **Le poids reste non garde.** Nomme en dette au point 5. C'est le trou le plus large que laisse cet
  `ADR`.
- **La condition C5 devient executable, mais seulement sur sa moitie "gabarit".** Un chemin calcule
  est refuse. Rien n'empeche en revanche `recreo.css` d'ecrire deux `background-image` differentes
  sous deux selecteurs conditionnes a une classe que le serveur poserait selon l'item. Cette moitie
  reste tenue par les clauses existantes d'`ADR-0031` et d'`ADR-0033` sur ce que la feuille peut
  conditionner - je ne l'ai pas mesuree, et je ne pretends donc pas qu'elle est fermee.
- **Trois jeux fautifs de plus a maintenir**, et un fichier de test qui grossit encore. La classe
  passe de 12 a 16 cas.

## Alternatives considerees

- **Refuser les images binaires et n'accepter que du SVG.** Tentant : le SVG est du texte, donc
  lisible en diff, immune au fait 2, et sa provenance se lit vraiment. Mais decider que
  l'illustration sera vectorielle, c'est decider de quoi le meuble aura l'air - une decision de
  design et de produit qui appartient a `ines-design` et a `sofia-sm`, et l'utilisatrice a fourni
  des references peintes. Ecartee comme hors de mon perimetre, pas comme mauvaise.
- **Exiger une empreinte SHA-256 sur les images maison, par symetrie avec `ADR-0014`.** Ecartee :
  point 2 ci-dessus. La symetrie aurait ete une clause qui ne peut echouer que sur un oubli
  administratif.
- **Exclure les images du garde-fou de chemins par une liste d'extensions.** Ecartee : une liste
  d'extensions echoue par omission, `grep -I` derive le critere du contenu. Meme doctrine
  qu'`ADR-0013`.
- **Corriger le selecteur jsoup au lieu d'abandonner les selecteurs CSS** (echappement different,
  passage par `[^th:]`). Ecartee : `[^th:]` selectionne tout element portant un attribut prefixe
  `th:`, donc `th:text` et `th:each`, ce qui est un perimetre faux ; et un echappement qui marche
  dans une version de jsoup et pas dans une autre est precisement ce qui a produit le fait 1.
  `hasAttr` ne depend d'aucun analyseur.
- **Ecrire un plafond de poids maintenant, quelle que soit sa valeur.** Ecartee : point 5.

## Ce qui me ferait changer d'avis

- Si une image maison devait un jour etre **derivee** d'un original tiers (une retouche d'une
  illustration sous licence), l'empreinte redeviendrait utile - non pour l'integrite, mais pour
  savoir de quel original elle descend. Le point 2 serait alors a rouvrir, et la ligne de provenance
  a ecrire.
- Si `sofia-sm` fixe une cible de poids, le point 5 devient une clause dans le commit suivant, sans
  amendement de cet `ADR` : la dette y est deja nommee.
- Si une version future de jsoup comprend l'echappement du deux-points, le point 3 ne changerait pas
  pour autant. `hasAttr` est plus lisible qu'un selecteur echappe, et ne peut pas silencieusement
  cesser de selectionner.

## Ce que cet `ADR` ne garantit pas

- **Qu'une image de ce depot soit legitimement la notre.** Aucun automate ne lit une licence dans un
  PNG. Cela reste une verification humaine, en revue de diff, et c'est la seule exigence de cet
  `ADR` qui n'a pas de clause.
- **Qu'une image soit assez legere pour le telephone d'un enfant.** Point 5, dette ouverte.
- **Qu'une image soit belle, lisible, ni conforme aux conditions de `nadia-prof`.** Le meuble actuel
  est accepte par elle : 27 de ses 32 conditions sont tenues et les quatre defauts de forme qu'elle
  avait refuses sont fermes. Ce que l'utilisatrice rejette est la ressemblance aux references, pas la
  conformite. Une image ne dispense donc d'aucune de ses conditions - les planchers de lisibilite et
  de cible tactile, l'absence d'animation sur l'habillage, l'invariance du jeu d'images, l'absence de
  tout code de jeu d'argent continuent de s'appliquer telles quelles, et le Done pedagogique reste le
  sien.

## Verification

`mvn -B verify` sur JDK 21, depuis la racine du depot, sur la branche `s10-theo-images` :
**BUILD SUCCESS**.

Methode du compte, parce qu'un chiffre sans methode ne vaut rien : somme des lignes `Tests run:` des
six modules du reacteur telles que Maven les rend, surefire et failsafe additionnes, sans aucune
exclusion - 45 (`domain`) + 7 (`application`) + 199 (`adapter-web`) + 63 (`adapter-content`) + 77 +
3 (`bootstrap`, surefire puis failsafe) = **394**, contre 390 a `2d34a15`. Les quatre cas neufs sont
les trois jeux fautifs d'image et la clause anti-vacuite ; ils vivent tous dans `adapter-web`, qui
passe de 195 a 199.

Les deux cas neufs de `verifier-chemins-test.sh` ne sont pas dans ce compte : ce sont des cas de
script shell, que Maven ne voit pas et que la CI lance dans son propre job. `bash
.github/scripts/verifier-chemins-test.sh` : **22 cas** annonces `ok`, aucun echec, contre 20 a
`2d34a15`.

`git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' | bash
.github/scripts/verifier-chemins.sh` - la commande exacte de la CI, exclusion comprise : aucune
occurrence.
