> **Note de sofia-sm, 2026-09-01.** Ce fichier est le rapport de `yanis-qa`, repris tel quel depuis
> `s26-yanis` (commit `0fbf7b0`), **sans fusionner cette branche** : ses deux commits portent des
> tests rouges par construction (les deux defauts qu'ils documentent), et ce n'est pas sa version
> qui entre sur `round-two` - les correctifs sont entres par les branches de `theo-archi`
> (`s26-theo-reco`, `f671649`) et de `marc-dev` (`s26-marc`, `87e6b4e`). Le contenu ci-dessous
> decrit donc un etat **anterieur** aux deux correctifs, sur l'arbre `s26-theo-reco` a `87963a2` :
> il reste lisible avec sa date, pour la meme raison qu'un refus pedagogique leve ne s'efface pas.
> Voir la section "Confirmation ArchUnit" de `review.md` pour l'etat apres correctif.

---

# [Sprint s26] Rapport qualite - `yanis-qa`

- **Date** : 2026-09-01. **Auteur** : `yanis-qa`.
- **Arbre juge** : `s26-theo-reco` a `87963a2`, worktree dedie. **Toutes les mesures de la
  section 1 sont faites sur cet arbre pristine**, sans aucune modification. Les deux tests rouges
  de la section 3 vivent sur une branche separee, `s26-yanis` (`77e2385`), qui part de `87963a2` -
  `git merge-base --is-ancestor s26-theo-reco s26-yanis` confirme la filiation. Ils ne sont **pas**
  fusionnes sur `s26-theo-reco` : ce n'est pas a moi de decider comment les fermer.

> **Ce rapport ne prononce que le Done technique, et il ne le prononce pas seul : la CI le juge, je
> le mesure et je le rapporte.** Le Done pedagogique appartient a `nadia-prof` seule. Je n'ai jugé
> aucun contenu ici, uniquement des garde-fous et leur perimetre.

---

## 1. La porte technique, mesuree par moi

### 1.1 La commande et son verdict

```
bash outils/mvn-jdk21.sh -B clean verify
```

a la racine du reacteur, sur `s26-theo-reco` a `87963a2` sans aucune modification, aucun `-pl`.

> **BUILD SUCCESS.**

| Module | Tests | Echecs | Erreurs | Ignores |
|---|---:|---:|---:|---:|
| `recreo-domain` | 82 | 0 | 0 | 0 |
| `recreo-application` | 13 | 0 | 0 | 0 |
| `recreo-adapter-web` | 377 | 0 | 0 | 0 |
| `recreo-adapter-content` | 451 | 0 | 0 | 0 |
| `recreo-bootstrap` (surefire) | 150 | 0 | 0 | 0 |
| `recreo-bootstrap` (failsafe) | 6 | 0 | 0 | 0 |
| **Total** | **1 079** | **0** | **0** | **0** |

Methode : somme de l'attribut `tests` des blocs `Results:` de chaque module apres un `clean`
complet, reacteur entier. **Ce chiffre confirme celui annonce (1 079, 0 echec)**, mesure ici de
maniere independante, sur ma propre commande, mon propre worktree. **JDK 21.0.10**, confirme deux
fois : par la toolchain resolue (`JDK[.../jdk-21.0.10]`) et par la ligne que chaque contexte Spring
imprime lui-meme au demarrage (`Starting ... using Java 21.0.10`) - la version que la machine
virtuelle des tests rapporte, pas seulement celle que l'entree de toolchain declare (`ADR-0071`).

**Aucun test instable observe** sur cette execution : un seul passage, pas de relance, rien ne
justifierait une seconde execution pour "confirmer" un vert - ce n'est pas la methode de ce depot.

### 1.2 Ce que ce chiffre ne dit pas

Un compte de tests n'est pas une mesure de qualite. Quatre classes du reacteur portent a la fois
`@ArchTest` et `@Test` (fait deja constate par ce depot) ; je ne recompte pas ce chiffre ici, je le
rappelle pour qu'il ne soit pas lu comme un score. Le reste de ce rapport porte sur ce que ce vert
ne prouve pas, comme demande.

---

## 2. Mise a l'epreuve des garde-fous neufs de s26

Methode : mutation sur materiau reel (`ADR-0013`), jamais sur une fixture ecrite pour l'occasion
quand un objet reel existe. Pour chaque garde-fou nomme comme suspect, j'ai lu son code, puis
casse la propriete qu'il pretend garder pour verifier qu'il rougit.

### 2.1 `VOI-14` (ses deux points) - tient

`AucunTexteAVoixHauteNEnonceUnePropositionDeSonItemTest` porte douze contre-tests derives
directement du cahier des charges de `nadia-prof` (section 16.6 de son addendum), plus deux
morsures sur des items **reels** de `cp-soustractions` et `cp-comparer-nombres` (`ADR-0076`,
generalisation aux chiffres et repli sur l'inclusion de sous-chaine pour les propositions sans
jeton). J'ai relu chaque contre-exemple et chaque morsure : ils couvrent le point 1 (choix,
propositions numeriques et alphabetiques, y compris le cas du chiffre a l'interieur d'un nombre
plus long, qui doit rester vert) et le point 2 (saisie, epellation sous forme virgules et
tirets). Le fichier nomme lui-meme sa limite plutot que la cacher : les paquets de mathematiques a
signes (`<`, `>`, `=`) ne declarent aucune voix aujourd'hui, et le premier qui en declarerait une
retomberait sur le repli strict, deja teste. Je n'ai pas trouve de mutation qui passe au travers.

### 2.2 L'appariement `texteAVoixHaute` / `consigneAdulte` cote contenu - tient sur son perimetre

`LeTexteAVoixHauteEtLaConsigneAdulteSeDeclarentEnsembleTest` lit les cles brutes du YAML (jamais le
chargeur), sur le catalogue servi et sur quatre contre-exemples fabriques (voix sans consigne,
consigne sans voix, chacun avec son corrige, plus un paquet sain). J'ai relu les quatre : les deux
sens de la fuite sont couverts, la clause de non-vacuite est presente. **Ce garde-fou tient
exactement ce qu'il dit tenir** - une propriete sur `catalogue.yaml`. Voir section 3.1 pour ce
qu'il ne tient pas au-dela de ce perimetre.

### 2.3 L'amendement d'identite d'`ADR-0032` (`ADR-0075` decision 3) - tient

`AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest` inclut desormais `texteAVoixHaute` dans le
couple qui definit "le meme ecran". Deux contre-tests symetriques verifient les deux sens : un
paquet ou seul `texteAVoixHaute` change entre deux items de meme enonce et memes propositions doit
rester vert (question et saisie), et la classification par reflexion sur les composantes d'`Item`
(section citee plus haut dans le fichier) refuse le silence si une composante nouvelle apparaissait
sans etre classee. Je n'ai pas trouve de mutation qui passe au travers.

### 2.4 La clause 4 de traversee (`ADR-0075`) - **ne tient pas au niveau du gabarit**

Voir section 3.1 : c'est le premier des deux defauts trouves.

### 2.5 Les trois porteurs de JavaScript d'`ADR-0074` - tiennent, un quatrieme ne tient pas

J'ai rejoue les trois morsures du fichier (`<script>` en ligne, attribut gestionnaire sous ses
trois ecritures, URL `javascript:`) sur le gabarit reel : les trois rougissent, comme documente
dans l'`ADR`. J'ai ensuite cherche un quatrieme porteur que le navigateur executerait et que la
regle ne nomme pas. Voir section 3.2 : c'est le second defaut trouve.

---

## 3. Les deux defauts trouves, mutation sur materiau reel

Chacun est encode en un test rouge, commite sur `s26-yanis` (`77e2385`), pas sur `s26-theo-reco`.
Le fichier de commit et son message donnent la trace complete ; ce qui suit en est le resume avec
la commande exacte et son resultat.

### 3.1 La traversee ne prouve pas que le bloc-adulte ne peut pas atteindre l'ecran a moitie

**La question posee explicitement dans ma mission.** `LeBlocAdulteDUnItemReelAtteintLEcranDeMancheTest`
(`ADR-0075` clause 4) prouve que, **sur le catalogue reel d'aujourd'hui**, chaque item d'un paquet
a voix rend son stimulus et sa mise en garde ensemble. Elle ne prouve pas que le gabarit **ne
peut pas** rendre l'un sans l'autre, parce que ses deux morsures mutent un `Document` jsoup deja
rendu (`ecranReel.clone()`), jamais le rendu du gabarit avec un modele different.

**Ce que la lecture du gabarit montre** (`fragments/manche.html`) :

```html
<div class="bloc-adulte" tabindex="-1" th:if="${texteAVoixHaute != null}">
    ...
    <p class="stimulus-a-lire" th:text="${texteAVoixHaute}"></p>
    <p class="consigne-adulte" th:if="${consigneAdulte != null}" th:text="${consigneAdulte}"></p>
</div>
```

Deux `th:if` **independants**. Rien au niveau du gabarit ou du controleur ne les lie : le
controleur transmet les deux valeurs telles quelles (son propre commentaire le dit -
*"ce controleur ne fait que transmettre ce qu'il recoit, jamais l'un sans l'autre non plus"*),
et il n'existe qu'un seul garde-fou qui empeche la divergence, `LeTexteAVoixHauteEtLaConsigneAdulteSeDeclarentEnsembleTest`
(section 2.2) - un garde-fou qui lit un fichier YAML, pas un document rendu, et qui ne serait
d'aucun secours si une future source de contenu, un bug de session, ou un changement de
`PartieController` faisait diverger les deux.

**Mutation, sur l'infrastructure de test reelle du gabarit** (`LeBlocAdulteEstUnSeulObjetContiguATousLesEtatsTest`,
qui rend `fragments/manche.html` via le meme moteur Thymeleaf que la production) : rendre le
fragment avec `texteAVoixHaute="chat"` et sans `consigneAdulte`.

```
bash outils/mvn-jdk21.sh -B -o -pl adapters/adapter-web -am test \
    -Dtest=LeBlocAdulteEstUnSeulObjetContiguATousLesEtatsTest,FrontieresDuJavaScriptTest \
    -Dsurefire.failIfNoSpecifiedTests=false
```

Perimetre : le module `adapter-web` et ses amonts, `-am` porte, jamais le depot entier.

**Resultat, observe** : `.bloc-adulte` se rend (1 element), avec `.stimulus-a-lire` = "chat" et
**zero** `.consigne-adulte`. Le stimulus atteint l'ecran sans la mise en garde que `nadia-prof`
exige d'y trouver toujours attachee (`VOI-12`). Aucun test existant, avant mon commit, ne pilotait
cette combinaison exacte : `LeBlocAdulteEstUnSeulObjetContiguATousLesEtatsTest` ne passe jamais les
deux parametres separement (sauf le cas "aucun des deux", qui reste vide comme attendu).

**Reponse a la question posee : non, le chemin modele n'est pas ferme.** Il tient uniquement parce
que le contenu reel d'aujourd'hui respecte l'appariement, garanti par un garde-fou qui regarde un
autre objet (un fichier YAML) que celui ou le defaut se produirait (un document rendu).

Test ajoute, rouge : `unTexteAVoixHauteSansConsigneAdulteNAtteintJamaisLEcranEnEtatIncomplet`
(`LeBlocAdulteEstUnSeulObjetContiguATousLesEtatsTest.java`).

### 3.2 Un quatrieme porteur de JavaScript, invisible a la clause 3 d'`ADR-0074`

**La question posee explicitement dans ma mission.** La clause 3 d'`ADR-0074` refuse une URL de
schema `javascript:` avec ce test :

```java
attribut.getValue().stripLeading().toLowerCase(Locale.ROOT).startsWith("javascript:")
```

`stripLeading()` ne retire que l'espace **en tete** de la chaine. Le WHATWG URL Standard (l'analyse
d'URL qu'applique tout navigateur) retire toute tabulation et tout retour a la ligne ASCII
**partout dans la chaine**, pas seulement en tete, avant d'en lire le schema. Un attribut
`href="ja⟨TAB⟩vascript:sessionStorage.clear()"` est donc normalise par le navigateur en
`javascript:sessionStorage.clear()` et s'execute au clic - identique au cas que la clause 3 refuse
deja, avec un seul caractere de difference que la comparaison de chaine ne neutralise pas.

**Mutation, sur le gabarit reel**, meme patron que la morsure existante de la clause 3
(`uneUrlDeSchemaJavascriptDansUnGabaritFaitEchouerLeGardeFou`), un seul caractere different :

```
bash outils/mvn-jdk21.sh -B -o -pl adapters/adapter-web -am test \
    -Dtest=FrontieresDuJavaScriptTest,LeBlocAdulteEstUnSeulObjetContiguATousLesEtatsTest \
    -Dsurefire.failIfNoSpecifiedTests=false
```

`href="ja<TAB>vascript:sessionStorage.clear()"` pose dans une copie d'`accueil.html`, a cote du
`<script defer th:src="...">` existant. **Resultat, observe** : `verifierQuAucunGabaritNePorteDeJavaScript`
ne leve aucune erreur - vert malgre le porteur. Aucun des treize mots de `INTERDITS_JS` n'entre en
jeu ici (la clause 3 ne les regarde pas), et aucun des trois porteurs nommes par `ADR-0074` ne
couvre ce cas : ce n'est ni le texte d'un `<script>`, ni un attribut gestionnaire d'evenement,
c'est une variante du troisieme porteur (URL de schema) que la comparaison de chaine ne reconnait
pas comme le meme schema.

**Reponse a la question posee : oui, un quatrieme se trouve** - pas un porteur distinct des trois
au sens de la section 3 d'`ADR-0074` (le texte d'un `<script>`, un attribut gestionnaire, une URL
`javascript:`), mais une **forme du troisieme** que la comparaison de chaine manque parce qu'elle
ne normalise que l'espace de tete. Le motif est le meme que celui qu'`ADR-0074` cite lui-meme
contre une liste de vocabulaire (*"une liste de mots attrape ce qu'elle nomme"*) : ici, une
comparaison de prefixe attrape le prefixe qu'elle compare, pas ce qu'un navigateur reduit au meme
prefixe avant de l'executer.

Test ajoute, rouge : `uneTabulationALInterieurDuSchemaJavascriptEchappeAuGardeFou`
(`FrontieresDuJavaScriptTest.java`).

### 3.3 Ce que je n'ai pas trouve, et ou j'ai cherche

- **`VOI-14`, l'appariement de contenu, l'amendement d'identite d'`ADR-0032`** (sections 2.1 a
  2.3) : lus en detail, mutes ou verifies sur leurs contre-tests existants, rien n'a rougi au-dela
  de ce que ces fichiers documentent deja eux-memes comme limite assumee (le repli des paquets de
  mathematiques a signes pour `VOI-14`, nomme et route par leurs auteurs).
- **Un cinquieme porteur de JavaScript** : j'ai considere un `<iframe srcdoc="...">` portant un
  `<script>` dans son attribut (invisible a une lecture jsoup qui ne reparse pas le contenu d'un
  attribut comme un document), et une URL `data:text/html,<script>...` sur un attribut `src`.
  Aucun des deux gabarits servis n'utilise `iframe` ni `object` : mesure du 2026-09-01,
  `grep -rn "<iframe\|<object" adapters/adapter-web/src/main/resources/templates/` rend **aucune
  occurrence**. Je ne l'ai donc pas mis a l'epreuve par mutation du materiau reel (`ADR-0013`
  l'interdirait sur une fixture que j'inventerais) ; je le nomme comme un chemin non audite plutot
  que de le compter comme un defaut.

---

## 4. Ce que ce rapport ne fait pas

- Il ne juge aucun contenu pedagogique : c'est `nadia-prof`, et elle passe apres moi.
- Il ne tranche ni le remede a la section 3.1 (fusionner les deux `th:if`, ou faire echouer tot au
  controleur si l'un est present sans l'autre) ni celui de la section 3.2 (retirer les caracteres
  de controle avant de comparer le schema, pas seulement en tete). Les deux sont des choix de
  gabarit ou de forme de clause, routes a `sofia-sm` et `theo-archi`.
- Il ne compte aucun test comme une mesure de qualite en soi (section 1.2).

## 5. Verdict

**Porte technique : verte, mesuree par moi sur `s26-theo-reco` a `87963a2`** -
`bash outils/mvn-jdk21.sh -B clean verify`, 1 079 tests, 0 echec, 0 erreur, BUILD SUCCESS, JDK
21.0.10 confirme par la machine virtuelle des tests.

**Deux obstacles qualite ouverts, encodes en tests rouges sur `s26-yanis` (`77e2385`), pas sur
l'arbre juge** : la traversee du bloc-adulte n'est fermee que par un garde-fou qui regarde le
mauvais objet (section 3.1), et la clause 3 d'`ADR-0074` a une forme de bypass par caractere de
controle (section 3.2). Aucun des deux n'est un defaut de contenu ; aucun des deux n'affecte le
vert de la section 1, qui reste un vert honnete sur ce que ces garde-fous couvrent aujourd'hui -
pas sur ce qu'ils devraient couvrir.
