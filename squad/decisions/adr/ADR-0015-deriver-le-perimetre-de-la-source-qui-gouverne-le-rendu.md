# ADR-0015 - Deriver le perimetre d'un garde-fou de la source qui gouverne le rendu, et supprimer celui dont le nom promet plus que son perimetre

- **Statut** : Accepte
- **Date** : 2026-08-06
- **Sprint** : s03 (redige hors ceremonie, a la cloture conjointe de s03 et s04)
- **Auteur** : theo-archi
- **Remplace** : rien. **Amende** : `ADR-0014` (une de ses limites nommees est fermee ici)

> **Note de perimetre.** Cet ADR tranche deux dettes de perimetre laissees ouvertes a la cloture de
> s03, et corrige au passage un troisieme defaut, de nature differente, trouve par `yanis-qa`
> pendant le meme cycle : une detection de `!important` aveugle au motif de nommage des variables de
> Bootstrap 5. Les trois defauts sont dans du code dont je suis l'auteur.

## Contexte

`ADR-0013` a nomme une propriete : un garde-fou qui **enumere** son perimetre ne verifie que ce
qu'on a pense a lui donner, et son mode de defaillance est un vert silencieux. Il a impose trois
obligations - deriver le perimetre, n'exempter aucun element derive, prouver la morsure sur un objet
neuf - et un recensement executable, `LesGardeFousSontMisALEpreuveTest`, qui refuse tout garde-fou de
balayage neuf sans jeu fautif.

Ce recensement attrape l'absence de preuve de morsure. Il n'attrape pas la faute plus fine :
**deriver correctement, mais de la mauvaise source**. Deux garde-fous du depot en sont porteurs, et
tous deux etaient nommes comme dette ouverte a la cloture de s03 sans que personne ne les tranche.

**Premiere dette - `TailleDesTextesEtBoutonsTest` derive de l'attribut `class` du gabarit source.**
`ADR-0014` a repris ce garde-fou : les gabarits balayes viennent du repertoire, les feuilles maison
viennent de tout `static/` hors `vendor/`. Les deux derivations sont bonnes. La troisieme ne l'est
pas : les classes d'un bouton sont lues dans son attribut `class`, alors que la source qui gouverne
le rendu est `th:class`, qui **remplace** cet attribut. Dans `fragments/retour.html` :

```html
<button type="button" class="btn retour-accueil"
        th:class="${enSuite} ? 'btn retour-accueil' : 'btn sortie-pleine'"
```

La classe `.sortie-pleine` - le bouton de sortie de fin de partie, la seule action possible restante,
exigee en aplat par `nadia-prof` (reserve 4 de `FICHE-bandit-manchot-additions-cp.md`) - n'est jamais
passee au crible. `ADR-0014` avait nomme cette limite dans sa section "ce que cette regle ne couvre
pas", en la qualifiant d'anterieure et de non aggravee. C'etait exact et insuffisant : nommer un trou
n'est pas le garder. `ADR-0013` interdit precisement ce raisonnement.

**Mesure sur l'arbre fusionne** (`4c62f60`), avant correction : retirer `.sortie-pleine` des deux
groupes de regles de `recreo.css`, ce qui fait retomber le bouton de fin de partie au defaut du
navigateur, laisse `TailleDesTextesEtBoutonsTest` **vert**.

**Seconde dette - `HabillageDesEcransTest.aucuneRessourceExterneNiPoliceTelechargeeALExecution` ne
lit que `recreo.css`.** Son nom promet une propriete de l'application entiere ; son perimetre est un
fichier ecrit en constante. Depuis `ADR-0014`, l'application sert aussi `static/vendor/`, et le
`static/css/` de demain pourra contenir une feuille de plus.

Ces deux garde-fous ne se ressemblent pas par hasard : la meme question les commande. **De quelle
source un garde-fou tire-t-il ce qu'il examine ?** Pour le premier, la source n'est pas l'attribut
ecrit, c'est l'ensemble des valeurs que cet attribut peut prendre au rendu. Pour le second, la source
n'est pas un fichier nomme, c'est l'arborescence servie.

## Decision

**1. Un garde-fou qui juge un element rendu derive son perimetre de tous les etats que cet element
peut prendre au rendu, pas du seul etat ecrit en dur dans la source.** Pour un gabarit Thymeleaf,
cela signifie l'attribut `class`, mais aussi chaque branche litterale de `th:class` et chaque ajout
litteral de `th:classappend`. Une exigence de `nadia-prof` doit tenir dans **chacun** de ces etats.

**2. Un etat que le garde-fou ne sait pas lire est refuse, jamais ignore.** Une classe entierement
calculee (`th:class="${classeDuBouton}"`) ou concatenee ne permet pas de prouver le plancher : elle
echoue. C'est la posture qu'`ADR-0014` avait deja retenue pour une ressource referencee par une
expression calculee (`RessourcesTiercesTest.cheminDeGabarit`), etendue ici a l'habillage. Sans elle,
il suffirait d'ecrire la classe en expression pour sortir du perimetre sans qu'aucun test ne bronche.

**3. Un garde-fou dont le nom promet une propriete generale et dont le perimetre nomme un fichier est
supprime, pas retreci.** Il n'est pas remplace par un garde-fou plus etroit mais honnete : il est
supprime au profit de celui, deja ecrit, dont le perimetre est derive. Deux garde-fous pour une meme
propriete, dont un aveugle, valent moins qu'un seul : le vert de l'aveugle se lit comme une couverture.
`aucuneRessourceExterneNiPoliceTelechargeeALExecution` disparait ;
`RessourcesTiercesTest.aucuneFeuilleNiAucunGabaritNeVaChercherQuoiQueCeSoitSurLeReseau` porte la
propriete, sur tout `static/` et sur tous les gabarits, avec deux jeux fautifs.

**4. Le reste de `HabillageDesEcransTest` continue de viser `recreo.css` par son nom, et c'est
legitime.** Ce garde-fou juge des **choix de style propres a ce depot** - une palette nommee, un
aplat de couleur sur tel bouton. Viser le fichier ou ces choix sont ecrits n'est pas une enumeration
de perimetre, c'est la designation de l'objet juge. La distinction avec le point 3 est celle-ci : ne
rien aller chercher sur le reseau n'est pas un choix de style, c'est une condition de deploiement qui
vaut pour tout octet servi, quelle qu'en soit l'origine.

## Regle executable

Ces deux garde-fous lisent des fichiers de ressources, pas du bytecode : ArchUnit ne peut pas les
porter, pour le motif deja etabli par `ADR-0009` et `ADR-0013`. Le mecanisme automatique qui les
garde est la suite Maven, executee par la CI a chaque poussee (`.github/workflows/ci.yml`, job
`build`, `mvn -B verify`), et le recensement d'`ADR-0013`
(`LesGardeFousSontMisALEpreuveTest.aucunGardeFouDeBalayageNEchappeALaMiseALEpreuve`) qui exige de
`TailleDesTextesEtBoutonsTest` un jeu fautif sous
`src/test/resources/contreexemple/TailleDesTextesEtBoutonsTest/`.

Fichier : `adapters/adapter-web/src/test/java/com/recreo/adapter/web/TailleDesTextesEtBoutonsTest.java`

```java
    /**
     * Un bouton et l'un des jeux de classes qu'il peut porter une fois rendu. Un bouton dont la
     * classe est conditionnelle en produit un par branche.
     */
    private record BoutonRendu(Element bouton, List<String> classes) {
    }

    private static List<List<String>> etatsDe(Element bouton) {
        List<List<String>> etats = new ArrayList<>();
        if (bouton.hasAttr(CLASSE_CALCULEE)) {
            for (String litteral : litteraux(bouton, CLASSE_CALCULEE)) {
                etats.add(new ArrayList<>(motsDe(litteral)));
            }
        } else {
            etats.add(new ArrayList<>(bouton.classNames()));
        }
        if (bouton.hasAttr(CLASSE_AJOUTEE)) {
            List<String> ajouts = litteraux(bouton, CLASSE_AJOUTEE).stream()
                    .flatMap(litteral -> motsDe(litteral).stream())
                    .toList();
            etats.forEach(etat -> etat.addAll(ajouts));
        }
        return etats;
    }
```

Et le refus de ce qui ne se lit pas, point 2 de la decision :

```java
    private static List<String> litteraux(Element element, String attribut) {
        String expression = element.attr(attribut);
        List<String> valeurs = new ArrayList<>();
        int position = 0;
        while ((position = expression.indexOf('\'', position)) >= 0) {
            int fin = expression.indexOf('\'', position + 1);
            if (fin < 0) {
                break;
            }
            valeurs.add(expression.substring(position + 1, fin).strip());
            position = fin + 1;
        }
        ...
        assertThat(valeurs)
                .as("%s : aucune classe litterale a lire dans cette expression ; une classe calculee "
                        + "ne peut pas etre confrontee au plancher exige par nadia-prof", origine)
                .isNotEmpty();
        assertThat(expression)
                .as("%s : une expression concatenee ne dit pas quelles classes seront rendues ; "
                        + "ecrire chaque etat en litteral", origine)
                .doesNotContain("+");
        return valeurs;
    }
```

`verifierPlancherDeLisibilite`, `verifierCibleTactile` et `verifierQueLeTiersNeDefaitPasLesPlanchers`
bouclent desormais sur les **etats rendus** et non plus sur les elements.

### Ce qui est prouve, mesure et non suppose

**Jeux fautifs** (`ADR-0013` obligation 3), deux de plus, chacun contenant un objet **absent de la
production** :

| Jeu fautif | Objet neuf | Rouge attendu |
|---|---|---|
| `TailleDesTextesEtBoutonsTest/branche-conditionnelle-sans-plancher` | un bouton dont l'attribut `class` est irreprochable et dont la seconde branche de `th:class` pose une classe qu'aucune regle maison ne declare | "aucune regle maison" |
| `TailleDesTextesEtBoutonsTest/classe-calculee` | un bouton dont la classe rendue est entierement calculee | "aucune classe litterale a lire" |

**Mutation de l'arbre reel**, executee dans un arbre de travail isole puis annulee, avant et apres
la correction. C'est la mesure qui donne son sens a cet ADR :

| Mutation du depot reel | Avant | Apres |
|---|---|---|
| `.sortie-pleine` retiree des deux groupes de regles de `recreo.css` (le bouton de fin de partie retombe au defaut du navigateur) | **vert** | **rouge**, `toutBoutonReellementRenduEstAuMoinsAUnRemVingtCinq` et `toutBoutonReellementRenduAUneCibleTactileDAuMoinsQuaranteQuatrePixels`, `.btn.sortie-pleine "Les jeux" (fragments/retour.html) : aucune regle maison ne s'applique a ce bouton` |

**Un troisieme defaut, trouve par `yanis-qa` pendant ce meme cycle, corrige ici.** Il n'est pas un
probleme de perimetre mais de lecture, et il defaisait la garantie centrale d'`ADR-0014` :
`declareEnImportant` cherchait la premiere occurrence litterale de `"font-size:"` dans le corps du
bloc. Bootstrap 5 nomme ses variables `--bs-<composant>-<propriete>`, donc
`--bs-btn-font-size:1rem` **contient** `font-size:` et etait trouve avant la vraie declaration
`font-size:var(--bs-btn-font-size)` du meme bloc. Le garde-fou lisait la valeur de la variable et ne
voyait jamais la declaration reelle, `!important` compris. C'est le motif de nommage de **tous** les
composants de la bibliotheque reellement vendorisee, pas un cas de figure invente.

La recherche exige desormais un **debut de declaration** - debut du corps, ou apres un `;` - et les
corps de blocs fusionnes sont separes par un `;` pour que la premiere declaration d'un corps ajoute
reste reconnaissable. Les trois lecteurs de propriete du fichier passent par le meme point d'entree,
non par gout de la factorisation mais parce que les trois portaient le meme defaut.

| Mutation du depot reel | Avant | Apres |
|---|---|---|
| `!important` ajoute sur la seule declaration `font-size` du bloc `.btn` de `vendor/bootstrap.min.css` (la mutation exacte de `yanis-qa`) | **vert** | **rouge**, `aucuneClasseTierceNeDefaitUnPlancherSurUnBoutonRendu` : `.btn.demarrer "Jouer" (accueil.html) : la classe tierce .btn impose font-size en !important et prend le pas sur la regle maison` |

Jeu fautif correspondant, sur objet neuf :
`TailleDesTextesEtBoutonsTest/plancher-defait-derriere-une-variable`, ou le plancher est defait
derriere une variable dont le nom contient celui de la propriete. L'ancien jeu fautif,
`plancher-defait-par-le-tiers`, ecrit `font-size:1rem!important` en clair - ce que Bootstrap ne fait
jamais. **C'est la lecon de ce defaut** : une fixture ecrite par l'auteur de la regle ressemble a ce
que l'auteur imagine, pas a ce que la bibliotheque ecrit. `ADR-0013` exige un objet neuf ; il faut
aussi que l'objet neuf ait la forme du monde reel.

**Mesure de la seconde dette**, meme methode : une feuille maison neuve `static/css/theme.css`
declarant un `@font-face` sur `fonts.gstatic.com` laisse `HabillageDesEcransTest` **vert** et fait
echouer `RessourcesTiercesTest.aucuneFeuilleNiAucunGabaritNeVaChercherQuoiQueCeSoitSurLeReseau`, avec
le message `css/theme.css va chercher https://fonts.gstatic.com/... sur le reseau`. La propriete est
donc entierement portee par le garde-fou derive : la suppression du garde-fou aveugle ne retire
aucune couverture, elle retire une illusion de couverture.

**Un test de non-vacuite du perimetre lui-meme.** Une correction de derivation peut etre defaite
silencieusement par une regression du mecanisme. `unEtatDeBoutonNeVenantQueDUnThClassEstPasseAuCrible`
exige que le perimetre releve contienne `sortie-pleine`, une classe qu'aucun attribut `class` ne
porte : le jour ou la derivation retombe sur le seul attribut ecrit, ce test devient rouge avant que
qui que ce soit n'ait a s'en apercevoir a l'ecran.

**Etat mesure du depot.** `mvn -B clean verify` sur JDK 21.0.10 : BUILD SUCCESS, **181 tests
unitaires et 2 tests d'integration, 0 echec** (38 `domain`, 7 `application`, 69 `adapter-web`, 10
`adapter-content`, 57 + 2 `bootstrap`). `adapter-web` passe de 66 a 69 : une methode supprimee,
quatre ajoutees.

## Consequences

- Le plancher de lisibilite et la cible tactile exiges par `nadia-prof` sont desormais verifies sur
  la sortie de fin de partie, qui n'etait couverte par rien depuis S3-6.
- **Un cout reel** : ecrire une classe de bouton en expression calculee devient impossible sans faire
  rougir la suite. C'est voulu, et c'est une contrainte qui pese sur `ines-design` et sur les
  developpeurs, pas sur moi. Le contournement est d'ecrire chaque etat en litteral, ce que le seul
  `th:class` du depot fait deja.
- L'extraction de litteraux ne comprend pas la grammaire de Thymeleaf : elle releve les chaines entre
  apostrophes et refuse la concatenation. C'est un analyseur pauvre, assume comme tel. Il couvre les
  formes reellement ecrites ici et echoue bruyamment sur le reste, ce qui est le bon sens de l'erreur.
- La couverture globale baisse d'une methode de test et l'illusion de couverture baisse davantage.
  Une lecture rapide de la suite pourrait conclure que `HabillageDesEcransTest` s'est appauvri.
- `HabillageDesEcransTest` reste dans `DETTE_DE_BALAYAGE` : il lit toujours un artefact de production
  et n'a toujours pas de jeu fautif. `PLAFOND_DE_DETTE` reste a **4**, il ne baisse pas ici. Le dire
  plutot que de laisser croire que cet ADR solde ce garde-fou.

## Alternatives considerees

**Rendre reellement les gabarits avec Thymeleaf et juger le HTML produit.** C'est la seule facon
d'etre exact - un moteur de rendu ne se trompe pas sur ce que `th:class` produit. Ecartee pour
maintenant : il faudrait construire un contexte de rendu par etat de jeu, donc enumerer les etats a
la main, donc reintroduire au niveau du contexte l'enumeration qu'on chasse au niveau des classes. Ce
qui me ferait changer d'avis : le jour ou une expression de classe cessera d'etre un simple
conditionnel entre deux litteraux, l'analyseur pauvre deviendra faux et il faudra ce moteur.

**Ne rien faire et laisser la limite nommee dans `ADR-0014`.** C'est ce qui avait ete fait, et c'est
exactement le raisonnement qu'`ADR-0013` a ecrit pour l'interdire. Une limite documentee dans un ADR
ne garde rien ; elle deplace seulement la responsabilite sur le lecteur suivant.

**Retrecir le nom du garde-fou aveugle plutot que le supprimer**
(`aucuneRessourceExterneDansRecreoCss`). Ecartee : le nom serait honnete mais la methode resterait
un doublon aveugle d'une methode derivee, et son vert continuerait de se lire comme une couverture.

**Prendre l'union des classes de toutes les branches au lieu d'un etat par branche.** C'est la
premiere version que j'ai ecrite, et elle ne mord pas : il suffit qu'**une** branche porte une classe
maison correcte pour que la branche fautive passe, puisque la regle n'exige qu'une regle maison
applicable. Le jeu fautif `branche-conditionnelle-sans-plancher` est precisement construit pour
distinguer les deux, et il reste vert sous l'union. Note ici parce que c'est le genre d'erreur qu'on
refait.

## Ce que cette regle ne couvre pas, dit avant qu'on le decouvre

- **Elle ne lit que `th:class` et `th:classappend`.** Thymeleaf offre d'autres chemins vers un
  attribut (`th:attr="class=..."`, `th:attrappend`). Aucun n'est utilise ici ; le jour ou l'un le
  sera, ce garde-fou sera muet dessus et rien ne le rappellera automatiquement.
- **`th:classappend` est traite en union, pas en produit cartesien.** Ses litteraux sont ajoutes a
  tous les etats plutot que combines etat par etat. Aucun bouton du depot n'en porte ; le jour ou l'un
  en portera un conditionnel, la combinaison exacte des classes ne sera pas celle qui est jugee.
- **Aucun moteur de rendu, aucun pixel mesure.** Inchange depuis `ADR-0014` : le seul verdict sur ce
  que l'enfant voit reste celui de `nadia-prof`.

## Amendement apporte a ADR-0014

La cinquieme puce de sa section "Ce que cette regle ne couvre pas" - *"Le garde-fou lit la classe
statique du gabarit, pas la classe calculee"* - **n'est plus vraie**. Elle est corrigee dans
`ADR-0014` par un renvoi vers le present ADR. Son texte citait par ailleurs
`th:class="${enSuite} ? 'retour-accueil' : 'sortie-pleine'"` : la forme rendue par `ines-design`
porte `btn` sur les deux branches, l'ADR ayant ete redige avant la mise en oeuvre.
