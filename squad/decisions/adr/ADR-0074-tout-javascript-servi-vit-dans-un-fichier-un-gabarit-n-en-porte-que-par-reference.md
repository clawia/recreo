# ADR-0074 - Tout JavaScript servi a l'enfant vit dans un fichier sous `static/` ; un gabarit n'en porte que par reference

- **Statut** : Accepte. **Sa regle est ecrite, verte et mise a l'epreuve dans le meme commit** -
  `adapters/adapter-web/src/test/java/com/recreo/adapter/web/FrontieresDuJavaScriptTest.java`,
  **4 tests neufs** : une clause verte sur les six gabarits reels, trois morsures qui mutent le
  gabarit reel et non une fixture, dont l'une rejoue trois ecritures distinctes.
- **Sprint** : s26 (planning). **Date** : 2026-09-01. **Auteur** : `theo-archi`.
- **Arbre mesure** : worktree `s26-theo`, branche partie de `round-two` a `153df5a`, puis fusionnee
  avec `round-two` a `7934d37` avant la mesure de cloture. **JDK 21.0.10**, celui que la machine
  virtuelle des tests rapporte d'elle-meme et non celui qu'une entree de toolchain declare
  (`ADR-0071`). Enveloppe : `bash outils/mvn-jdk21.sh`.
- **Ferme la dette rangee au rang 3 de `DEC-0067`**, routee a `theo-archi` par `camille-po` a s25
  puis a s26 - *"deuxieme sprint consecutif de routage"*. C'est la porte de la **dette n. 4 de
  `CLAUDE.md`**.
- **Prolonge** : `ADR-0008` (le comportement de jeu vit sur le serveur), `ADR-0013` (perimetre
  derive, garde-fou mis a l'epreuve), `ADR-0020` (l'unite de verification d'un ecran est le document
  assemble), `ADR-0051` (un garde-fou qui n'examine aucun objet le declare), `ADR-0056` (un ancrage
  de mutation est unique).
- **Ne decide aucun contenu, aucune pedagogie, aucune apparence.**

---

## Contexte

### Le fait, remesure par moi plutot que repris

`yanis-qa` mesure a s25, et `camille-po` range au rang 3 de `DEC-0067` section 5 :

| Mutation | Ce qu'elle pose dans le gabarit servi a l'enfant | Classes rouges |
|---|---|---|
| `M1` - un attribut `onclick` sur le bouton de proposition | rejeu de `C7` de s24 | **0 sur 1 018 tests** |
| `M7` - un element `<script>` de dix lignes qui ecoute le clic, lit `sessionStorage` et tient un `score` | neuf a s25 | **0 sur 1 018 tests** |

**Je l'ai remesure au lieu de le recopier**, parce qu'un chiffre repris d'un autre document du depot
se verifie contre sa source primaire avant d'etre reecrit (`squad/README.md`, section 4, amendement
s17). Mesure du 2026-09-01, worktree `s26-theo` a `153df5a`,
`bash outils/mvn-jdk21.sh -B -o clean test` sur la **racine** - reacteur complet, donc la question
du `-am` ne se pose pas -, JDK **21.0.10** rapporte par la machine virtuelle des tests :

| Etat | domain | application | adapter-web | adapter-content | bootstrap | Total | Echecs |
|---|---|---|---|---|---|---|---|
| Reference | 80 | 13 | 364 | 410 | 145 | **1 012** | **1** |
| **`M7` pose dans `fragments/manche.html`** | 80 | 13 | 364 | 410 | 145 | **1 012** | **1** |

*Methode du total : somme de l'attribut `tests` de chaque rapport XML de surefire, apres un `clean`
complet - jamais la somme des lignes `Tests run:` des rapports `.txt`, qui sous-comptent quand une
classe melange deux moteurs (`squad/README.md`, section 4, amendement s11). L'unique echec est le
meme dans les deux colonnes et il est anterieur a cette mutation :
`ToutesLesReferencesDeDecisionResolventTest` (`ADR-0066`), `CLAUDE.md` a `153df5a` citant
`DEC-0068` qui ne vivait pas encore sur cette branche ; `sofia-sm` l'a repare a `7934d37`.*
**Ecart imputable a `M7` : zero, sur les cinq modules.**

**Le chiffre de `yanis-qa` est donc confirme sur un autre arbre, a une autre date, par un autre
instrument.** Mon total est plus bas de six unites parce que le reacteur a change entre les deux
dates : ce n'est pas un desaccord, ce sont deux arbres.

### Ce que la mesure dit exactement, et ou est le trou

`sessionStorage` et `score` sont **deux des treize mots** de
`FrontieresDuJavaScriptTest.INTERDITS_JS`. Ils sont dans un fichier servi a l'enfant, et rien ne
rougit. **La liste n'a pas failli : son perimetre ne la porte pas jusque-la.**

Les quatre clauses d'`ADR-0008` alors en place lisent :

| Clause | Perimetre reel |
|---|---|
| budget de 4 096 octets et 100 lignes | `static/**.js` hors `vendor/` |
| vocabulaire de decision de jeu | `static/**.js` hors `vendor/` |
| le script ne rend rien focalisable | `static/**.js` hors `vendor/` |
| aucun marqueur de correction | les gabarits - **la seule qui les voie** |

**Trois clauses sur quatre comptent des octets, des lignes et des sous-chaines dans un fichier.**
Un gabarit n'entrait dans le perimetre que par la quatrieme, qui cherche quatre noms d'attributs de
correction. Le comportement de jeu pose ailleurs que dans un fichier `.js` n'est vu par personne.

**C'est la porte de la dette n. 4 de `CLAUDE.md`** - *"le comportement des mecaniques loge dans le
JavaScript client : 692 lignes qu'aucun test ne couvrait, derriere huit classes Java de 55 lignes
qui ne faisaient que deleguer"*. La version precedente n'a pas commence par un fichier de 692
lignes ; elle a commence par un bloc.

### Le fait qui rend la fermeture pressante maintenant

s25 a livre **deux gabarits neufs et deux chaines de rendu** (`camille-po`, `DEC-0067` section 5,
rang 3). s26 en demande davantage : un texte destine a un adulte, sur un ecran a deux destinataires
(`DEC-0068`, condition `VOI-12` de `nadia-prof`). **La surface ou un bloc peut entrer grandit dans
le sprint meme ou la dette entre a son troisieme rang.**

---

## Decision

### 1. Un gabarit ne porte de JavaScript que **par reference**

> **Tout JavaScript servi a l'enfant vit dans un fichier sous `static/`. Un gabarit n'en porte que
> par reference : un element `<script>` qui designe un fichier. Tout autre porteur est refuse.**

Le fichier ainsi designe retombe sous le budget et sous la liste de vocabulaire d'`ADR-0008`, qui
sont ecrits pour un fichier et qui gardent bien un fichier.

### 2. Le remede n'est **pas** d'etendre la liste de vocabulaire aux gabarits, et le motif se mesure

Une liste de mots interdits attrape **ce qu'elle nomme**. Un bloc en ligne peut tenir un etat de
jeu sans ecrire un seul des treize mots :

```js
var n = 0; b.addEventListener('click', function () { n++; });
```

Aucun de `fetch(`, `XMLHttpRequest`, `JSON.parse`, `JSON.stringify`, `localStorage`,
`sessionStorage`, `eval(`, `score`, `correct`, `bonneReponse`, `reponse`, `proposition`, `colonne`.
**Etendre la liste aurait produit une clause verte sur le premier auteur qui n'ecrit pas mes mots**,
tout en se presentant comme fermant la dette - la famille de defaut que ce depot sanctionne depuis
s05.

**Ce qui est ferme ici est le porteur, pas le vocabulaire.** Un porteur refuse n'a ni budget a
regler ni liste a tenir a jour : il n'y a rien qui puisse pourrir.

### 3. Les trois porteurs sont derives de ce que le navigateur execute, jamais d'une liste d'incidents

| Porteur | Ce qu'il est | D'ou il est derive |
|---|---|---|
| le **texte** d'un `<script>` sans `src` ni `th:src` | du JavaScript que le document porte lui-meme | la definition de l'element |
| un **attribut qui lie du JavaScript a un evenement** | le mecanisme de `M1` et de `C7` | le nom de l'attribut, par une **propriete** |
| une **URL de schema `javascript:`** | le troisieme porteur qu'un document HTML offre | le schema d'URL |

**Le second est le seul qui demande un critere de nom, et ce critere est une propriete, jamais une
enumeration** : un nom local qui commence par `on` suivi de lettres, ou qui commence par `hx-on`.
Une liste de gestionnaires (`onclick`, `onchange`, ...) echouerait par omission au premier evenement
qu'elle n'aurait pas prevu - *"un perimetre enumere par types echoue par inclusion autant que par
omission"*.

**Le prefixe de gabarit (`th:`, `data-th-`) est retire avant le test**, sans quoi `th:onclick` -
que Thymeleaf traite comme n'importe quel attribut - passerait.

**La moitie `hx-on` n'est pas une precaution que j'imagine, elle est mesuree sur la bibliotheque
telle qu'elle est vendorisee.**

```
grep -o 'hx-on[a-z:*-]*' adapters/adapter-web/src/main/resources/static/vendor/htmx.min.js | sort -u
```

rend `hx-on`, `hx-on-` et `hx-on:` sur la copie du depot - htmx 2.0.10, `vendor/provenance.txt`,
empreinte `71ea6718...` (mesure du 2026-09-01). **Un auteur peut donc ecrire aujourd'hui
`hx-on:click="..."` et loger une decision de jeu dans le document servi.** C'est la lecon de s05
appliquee a moi-meme : la detection d'un `!important` y cherchait une sous-chaine qu'un nom de
variable de la bibliotheque contenait, et la regle passait sa propre epreuve en echouant dans le
monde. Ici le materiau interroge est la bibliotheque reelle, pas mon souvenir de sa documentation.

### 4. Ce que cette regle ne garde pas, et le dit

- **Un porteur introduit par le serveur a l'execution.** La clause lit la **source** des gabarits.
  Un modele qui injecterait du HTML non echappe (`th:utext`) porterait un `<script>` que cette
  lecture ne voit pas. Mesure du 2026-09-01,
  `grep -rn "utext\|th:inline" adapters/adapter-web/src/main/resources/templates/` : **aucune
  occurrence**. Le canal n'existe pas aujourd'hui ; il n'est pas garde, et c'est ecrit ici plutot
  que suppose ferme.
- **La bibliotheque tierce.** `static/vendor/` reste hors perimetre, comme pour le budget : son
  integrite est gardee par l'empreinte SHA-256 d'`ADR-0014`, pas par une lecture de son contenu.
- **Ce qu'un fichier `.js` maison fait.** Inchange : le budget et la liste d'`ADR-0008` ne prouvent
  pas qu'un fichier de 4 096 octets ne decide rien - ils prouvent qu'il n'ecrit pas treize mots.
  **Cette regle-ci ne change rien a cette limite** et ne pretend pas la fermer.

---

## Regle executable

Fichier : `adapters/adapter-web/src/test/java/com/recreo/adapter/web/FrontieresDuJavaScriptTest.java`.

**Pourquoi dans cette classe et pas dans une classe neuve** : la frontiere d'`ADR-0008` a un seul
endroit ou on la lit. Trois de ses clauses y comptent un fichier, une y lit les gabarits ;
celle-ci complete la quatrieme. Une classe de plus aurait laisse deux endroits ou chercher ce que
le JavaScript a le droit de faire.

Le perimetre est **derive** : les gabarits viennent du balayage de
`src/main/resources/templates`, jamais d'une liste ; les elements et les attributs viennent du
document **analyse** par jsoup, jamais d'une expression reguliere - `AssertionsWebTest` l'interdit
dans ce module (`ADR-0008`).

```java
private static boolean lieDuJavaScriptAUnEvenement(String nomLocal) {
    if (nomLocal.startsWith("hx-on")) {
        return true;
    }
    if (!nomLocal.startsWith("on") || nomLocal.length() <= 2) {
        return false;
    }
    return nomLocal.chars().skip(2).allMatch(lettre -> lettre >= 'a' && lettre <= 'z');
}

static void verifierQuAucunGabaritNePorteDeJavaScript(Path racineDesGabarits) throws IOException {
    List<Path> gabarits = fichiers(racineDesGabarits, ".html").toList();
    assertThat(gabarits)
            .as("ADR-0003 : cette regle ne prouve rien si aucun gabarit n'est passe au crible")
            .isNotEmpty();

    int elementsExamines = 0;
    int attributsExamines = 0;
    List<String> porteurs = new ArrayList<>();
    for (Path gabarit : gabarits) {
        Document document = Jsoup.parse(Files.readString(gabarit), "", Parser.htmlParser());
        for (Element element : document.getAllElements()) {
            elementsExamines++;
            if ("script".equals(element.normalName())
                    && !element.data().isBlank()
                    && !element.hasAttr("src") && !element.hasAttr("th:src")) {
                porteurs.add(gabarit + " : un element <script> porte du JavaScript en ligne");
            }
            for (Attribute attribut : element.attributes()) {
                attributsExamines++;
                if (lieDuJavaScriptAUnEvenement(nomLocal(attribut.getKey()))) {
                    porteurs.add(gabarit + " : l'attribut " + attribut.getKey()
                            + " lie du JavaScript a un evenement");
                }
                if (attribut.getValue().stripLeading().toLowerCase(Locale.ROOT)
                        .startsWith("javascript:")) {
                    porteurs.add(gabarit + " : l'attribut " + attribut.getKey()
                            + " porte une URL de schema javascript:");
                }
            }
        }
    }
    // deux clauses de non-vacuite (ADR-0051), puis :
    assertThat(porteurs).as("ADR-0074 : ...").isEmpty();
}
```

**Deux clauses de non-vacuite plutot qu'une** (`ADR-0051`) : le compte d'elements **et** le compte
d'attributs. La moitie du critere porte sur des noms d'attributs, et une lecture qui rendrait des
elements sans jamais lire un attribut laisserait cette moitie verte sans avoir rien examine.

### La preuve de morsure - sur le materiau reel, jamais sur une fixture

`squad/README.md`, section 9, etape 4 : *"il est vert ne suffit jamais comme preuve de morsure"*.
Les trois morsures **mutent une copie des gabarits reels**, sur le patron deja employe dans cette
classe pour `ADR-0057`, par `muterUneSeuleFois` qui refuse un ancrage non unique (`ADR-0056`).

| Morsure | Geste mute sur le gabarit reel | Resultat |
|---|---|---|
| `unBlocDeScriptEnLigneDansUnGabaritFaitEchouerLeGardeFou` | `M7` recopie tel quel dans `fragments/manche.html` | **rouge**, message nommant `manche.html` |
| `unAttributGestionnaireDEvenementDansUnGabaritFaitEchouerLeGardeFou` | `onclick`, `th:onclick`, `hx-on:click` poses sur le bouton de proposition reel | **rouge trois fois**, un message par ecriture |
| `uneUrlDeSchemaJavascriptDansUnGabaritFaitEchouerLeGardeFou` | `href="javascript:sessionStorage.clear()"` dans `accueil.html` | **rouge** |

**Et la morsure qui compte le plus est celle qui a ete faite sur l'arbre de production, pas sur une
copie.** `M7` pose dans `adapters/adapter-web/src/main/resources/templates/fragments/manche.html`,
puis
`bash outils/mvn-jdk21.sh -B -o -pl adapters/adapter-web -am test -Dtest=FrontieresDuJavaScriptTest` :
**9 tests, 1 echec**, message *"`src\main\resources\templates\fragments\manche.html` : un element
`<script>` porte du JavaScript en ligne"*. Le mutant retire, meme commande : **9 tests, 0 echec**.
*Perimetre : le module `adapter-web` et ses amonts, `-am` porte, jamais le depot. La premiere
tentative sans `-am` a echoue a la resolution de `recreo-application` depuis le depot local de
l'utilisateur - la demonstration, sur mon propre lot, de ce que `CLAUDE.md` decrit.*

**Le geste que la mesure de s25 avait trouve invisible est desormais rouge, et il l'est sur
l'arbre.**

---

## Consequences

- **Un ilot JavaScript de mecanique devient impossible par inadvertance.** `ADR-0008` nomme
  l'echappatoire : un ilot ouvert par un `ADR` nomme. Cette regle ne la ferme pas, elle rend son
  ouverture **visible** - il faudra amender ce fichier-ci, dans un diff que quelqu'un relit.
- **Le cout pour un auteur de gabarit est nul aujourd'hui et non nul demain.** Les six gabarits
  servis passent sans une modification ; un futur besoin d'agrement devra passer par `recreo.js`,
  donc par le budget. **C'est le point : le budget redevient la seule porte.**
- **Ce que je paie et que je nomme** : la clause lit la source du gabarit, pas le document assemble.
  `ADR-0020` etablit que l'unite de verification d'un ecran est le document assemble ; ici, l'objet
  garde est **ce qu'un auteur ecrit**, et c'est le fichier source qui le porte. Le canal de
  l'injection a l'execution est nomme en section 4 comme non garde, avec sa mesure.
- **`FrontieresDuJavaScriptTest` reste dans `DETTE_DE_BALAYAGE`, et le plafond de 2 ne bouge pas.**
  Ce que la dette y nomme est l'absence d'un jeu fautif **commite** ; ce lot ajoute trois morsures
  par mutation du materiau reel, qui valent mieux qu'une fixture mais ne sont pas ce que la dette
  nomme. **Un plafond qu'on baisse en changeant ce qu'il compte n'est pas un plafond baisse**, et je
  ne l'abaisse pas au motif que mon propre lot me le permettrait.

## Alternatives considerees

1. **Etendre `INTERDITS_JS` aux gabarits.** Ecartee : section 2, avec le contre-exemple de trois
   lignes qui echappe aux treize mots. Elle aurait produit une regle verte sur le premier auteur qui
   n'ecrit pas mes mots, en se presentant comme fermant la dette.
2. **Un budget d'octets pour le JavaScript en ligne**, sur le modele du budget de fichier. Ecartee :
   un budget autorise un peu, donc autorise le premier bloc, et la version precedente n'a pas
   commence par 692 lignes. Un porteur refuse n'a pas de seuil a discuter.
3. **Interdire l'element `<script>` dans les gabarits, sans exception.** Ecartee : `accueil.html`
   doit charger htmx et `recreo.js`. La distinction reference / contenu est exactement la frontiere
   utile, et elle se lit sur l'element.
4. **Verifier le document assemble** par un test d'integration Spring qui appelle les routes.
   Ecartee **pour cette clause**, pas en general : le defaut nait sous la main d'un auteur, dans un
   fichier source ; la regle doit rougir dans le module ou il ecrit, pas trois modules plus loin. La
   part que cette lecture ne voit pas est nommee en section 4 plutot que couverte par un second
   mecanisme dont la population serait la meme.
5. **Une regle ArchUnit sur le bytecode.** Sans objet : ni un gabarit ni un fichier `.js` n'est
   compile. Ce garde-fou lit des artefacts servis, et `ADR-0013` gouverne cette famille.

## Ce que cet `ADR` ne decide pas

- **Aucun contenu, aucune pedagogie, aucune apparence.** Ce qu'un ecran montre, ce qu'un texte dit
  et a qui il s'adresse ne sont pas ici.
- **Il ne rouvre aucun Done** : les six gabarits servis passent sans modification, mesure ci-dessus.
- **Il ne price aucun sprint** et ne range aucune dette : le rang appartient a `camille-po`.
