# ADR-0065 - Un mot lu par un enfant est juge sur un lexique exterieur au depot, et la classe des homographes reste portee a la main

- **Statut** : Accepte
- **Date** : 2026-08-30
- **Sprint** : s23
- **Auteur** : theo-archi
- **Demandeur** : `camille-po` (`DEC-0056`, section "Ce que je route"), sur une propriete formulee
  par `nadia-prof`

## Contexte

`DEC-0056` a etabli que la porte de l'accentuation du texte lu par l'enfant appartient au
`relecteur-pedagogique` depuis l'amendement du 2026-08-04 a `DEC-0002`, et que cette porte n'a
jamais eu d'automate. Le cout mesure de cette absence : le 2026-08-29, les vingt-cinq indices de
`cm1-multiplications-divisions-10-100.yaml` n'etaient pas accentues, la CI etait verte, le lot etait
livre, et le defaut n'a ete trouve que par la lecture a l'oeil de `nadia-prof` - un lot perdu pour
quatre chaines.

`nadia-prof` en fait une condition chiffree de sa capacite : quatre a cinq paquets par sprint contre
trois, **a condition que l'accentuation soit gardee par un automate**. Elle formule la propriete
ainsi : *"aucune chaine lue par un enfant ne contient un mot francais qui prendrait un accent et ne
le porte pas"*, et note qu'a la difference de `COMP-2` elle se formule sans jugement et se teste sur
un lexique.

Le probleme reel n'est donc pas "faut-il un garde-fou" - il est **d'ou vient le lexique**. Deux
familles etaient possibles, et la mesure a tranche entre elles.

### Ce que la mesure a etabli, avec sa methode

Perimetre de mesure : les 24 paquets de `adapters/adapter-content/src/main/resources/contenu/`,
champs servis a l'enfant uniquement (`enonce`, `bonneReponse`, `distracteurs`, `indice`,
`explication`, `texteSupport`), lus par le chargeur reel, chaque paquet enumere lu en entier
(`ADR-0064`). Etat de l'arbre : `890732f` (cloture de s22). 3 026 chaines servies, 1 331 mots
distincts, dont 224 portent au moins un accent.

1. **Un critere derive du corpus juge ne juge que sa coherence interne** (`ADR-0045`), et voici le
   chiffre qui le rend concret : **213 des 274 formes accentuees distinctes, soit 78 pour cent,
   n'apparaissent que dans un seul paquet** (meme methode, mesure sur l'arbre de `be63ce3^` pour
   inclure le paquet fautif). Un critere qui comparerait les paquets entre eux serait donc aveugle
   au vocabulaire neuf - c'est-a-dire exactement au vocabulaire d'un paquet neuf, les quatre a cinq
   par sprint que cette decision doit rendre possibles.
2. **Sur le defaut reel, ce critere aurait tenu au hasard.** Toujours sur `be63ce3^` : la forme
   accentuee de "zeros" n'existait que dans `cm1-grands-nombres`, et celle de "ecrire" que dans
   `cm1-comprehension`, un paquet livre le meme sprint. Retirer l'un ou l'autre du catalogue rendait
   la detection muette.
3. Un recoupement contre un corpus **independant** - les 61 paquets de `contenu-repris/`, issus de
   `main` - ne trouve aucun defaut dans le contenu servi actuel, mais ne prouve pas grand-chose : ce
   corpus ne porte que 287 formes accentuees distinctes.

## Decision

**Juger chaque mot du texte lu par un enfant sur un lexique morphologique du francais exterieur au
depot, et non sur le depot lui-meme.**

1. Un mot servi est **fautif** quand il n'est pas une forme du francais **et** qu'une autre facon
   d'accentuer les memes lettres, elle, en est une. Le critere porte sur le *squelette* du mot -
   ses lettres, tout signe diacritique retire - ce qui couvre l'accent manquant comme l'accent
   errone, pour le meme prix.
2. Le lexique est `org.languagetool:french-pos-dict:0.7` (CC BY-SA 4.0, 1,4 Mio, 503 915 formes
   flechies distinctes), declare en **portee `test`** : il ne rejoint aucun artefact livre, et la
   licence de la donnee ne gouverne donc pas celle du produit.
3. Le perimetre est tout ce qu'un enfant lit et que `adapter-content` sert : le texte des items, et
   les six champs du catalogue qu'une vue affiche (`titre`, `consigne`, et les quatre du
   descripteur). Les identifiants de jeu et de paquet en sont **exclus par un motif ecrit** : ils ne
   sont jamais affiches comme du texte et les conventions de redaction du depot les veulent en
   ASCII - `bandit-manchot-perimetre-cm1` y serait un faux rouge permanent.
4. **La classe des homographes est declaree non gardee, et reste a la main de `nadia-prof`.**

## Ce que ce garde-fou ne verra pas

C'est la partie de cet `ADR` que `nadia-prof` doit lire avant de compter dessus, parce que sa borne
de capacite en depend.

1. **Un mot qui, sans son accent, est lui aussi un mot francais.** "a" et "a accent grave", "ou" et
   "ou accent grave", "la" et "la accent grave", "des" et "des accent grave", "sur" et "sur accent
   circonflexe", "du" et "du accent circonflexe", "cote" et ses trois soeurs, tous les couples
   present/participe passe ("il termine" et "il a termine"). Trancher demande la syntaxe de la
   phrase, qu'aucun lexique ne porte. **Mesure : 232 des 1 331 mots distincts du contenu servi, soit
   17 pour cent, tombent dans cette classe.** Elle contient la faute d'accentuation la plus
   frequente du francais, et **elle contient le quatrieme mot du defaut de s22** : le garde-fou
   aurait nomme "ecrire", "zero" et "zeros", **jamais "a droite"**. Un test dedie
   (`laFauteDAccentSurUnMotQuiExisteSansAccentResteInvisible`) grave ce trou plutot que de le
   laisser se decouvrir : il echoue le jour ou quelqu'un croirait l'avoir ferme.
2. **Un mot absent du lexique et sans soeur accentuee** n'est pas juge. Ce sont les prenoms
   inventes, les onomatopees, les fragments d'elision. Mesure : 13 formes distinctes aujourd'hui
   ("kofi", "malo", "elio", les fragments de "aujourd'hui", les terminaisons verbales servies telles
   quelles par les paquets de conjugaison). Aucune n'est fautive, mais rien ici ne le prouve.
3. **Le texte pose par un gabarit** reste au perimetre de `TexteAccentueDesGabaritsTest`
   (`adapter-web`), inchange.
4. **La couverture du lexique est un pari, pas une preuve.** Un mot francais rare que le lexique ne
   porterait pas et dont le squelette porterait une soeur accentuee produirait un **faux rouge**.
   Mesure : zero sur les 1 331 mots distincts du contenu servi au 2026-08-30. Le mode de defaillance
   est bruyant, jamais silencieux - c'est le bon sens pour un garde-fou.

## Regle executable

`adapters/adapter-content/src/test/java/com/recreo/adapter/content/AucunMotServiNeSEcarteDeSonAccentTest.java`,
avec son lexique dans `LexiqueFrancais.java` (meme repertoire).

Le critere, tel qu'il s'ecrit :

```java
static List<String> defautsDe(String origine, String valeur, LexiqueFrancais lexique) {
    List<String> defauts = new ArrayList<>();
    for (String mot : motsDe(valeur)) {
        String minuscule = mot.toLowerCase();
        if (lexique.estUnMotFrancais(minuscule)) {
            continue;
        }
        Set<String> soeurs = lexique.formesDuMemeSquelette(minuscule);
        if (soeurs.isEmpty()) {
            continue;
        }
        defauts.add(...);
    }
    return defauts;
}
```

**Ce n'est pas une regle ArchUnit** : l'objet juge est le contenu rendu par le chargeur reel, pas un
graphe de types. Le compte de regles ArchUnit du depot est inchange. Le mecanisme automatique qui la
garde est la porte technique elle-meme - `mvn verify` sur le reacteur complet.

**Trois clauses** :

1. `chaqueMotServiEnProductionPorteSonAccent` - le critere sur le contenu reel et le catalogue reel.
2. `leDefautReelDeS22FaitEchouerLeGardeFou` - la morsure.
3. `laFauteDAccentSurUnMotQuiExisteSansAccentResteInvisible` - le trou, grave.

### La morsure est prouvee sur du materiau reel, pas sur une fixture

Le contre-exemple n'est pas de ma main : c'est
`adapters/adapter-content/src/test/resources/contreexemple/AucunMotServiNeSEcarteDeSonAccentTest/accent-manquant/contenu/cm1-multiplications-divisions-10-100.yaml`,
**le fichier tel qu'il etait a `be63ce3^`**, l'etat exact de l'arbre la veille du refus de
`nadia-prof`. C'est le defaut qui a reellement traverse une CI verte. La lecon de s05 - une fixture
ecrite par l'auteur d'une regle ressemble a ce qu'il imagine, pas a ce que le monde ecrit - vaut
d'etre payee quand l'historique porte deja le materiau.

## Consequences

### Bonnes

- La porte de `DEC-0002` amende s'exerce **a chaque `verify`** et non une fois par sprint, ce qui
  etait litteralement le motif de l'amendement du 2026-08-04.
- La condition de capacite de `nadia-prof` est tenue pour la part qui la coutait le plus cher : le
  balayage exhaustif d'un paquet neuf, mot a mot, sur une population de plusieurs centaines de
  chaines.
- Le catalogue entre au perimetre. `TexteAccentueExposeParLePortEntrantTest` compare ses chaines
  **par egalite** : il interdit qu'une chaine change en silence, il ne dit rien de son accentuation
  - un titre neuf ajoute a l'inventaire dans le meme commit y passe non accentue. Ce trou-la se
  ferme ici.

### Mauvaises

- **Une dependance tierce de plus dans le build**, et la premiere qui apporte de la *donnee* plutot
  que du code. Elle est en portee `test`, mais elle allonge la chaine de resolution : un `verify`
  suppose desormais que Maven puisse resoudre 1,4 Mio de plus.
- **Le lexique est une autorite exterieure au depot.** Le depot ne peut pas prouver qu'il a raison,
  et une montee de version pourrait changer un verdict sans qu'aucun fichier du depot ne bouge. La
  version est epinglee (`0.7`) dans le `dependencyManagement` du pom parent ; c'est le seul remede
  simple, et il ne va pas jusqu'a l'empreinte que `ADR-0014` impose a une feuille de style servie.
- **17 pour cent du vocabulaire reste juge a l'oeil**, et le chiffre monte mecaniquement avec la
  part de texte narratif dans le contenu.
- Le lexique se charge une fois par execution : 0,95 s pour les trois clauses, mesure sur JDK 21.0.10.

### Ce qui me ferait changer d'avis plus tard

- Si `nadia-prof` mesurait que les fautes qui traversent sont majoritairement dans la classe des
  homographes, ce garde-fou couterait sa dependance sans rendre sa borne de capacite : il faudrait
  alors soit un analyseur syntaxique - beaucoup plus de pieces mobiles - soit renoncer.
- Si le lexique produisait un faux rouge sur un mot que `nadia-prof` valide, la reponse n'est pas
  une liste d'exemptions ecrite a la main : c'est de changer de lexique, ou de reduire le perimetre
  avec un motif ecrit.

## Alternatives considerees

1. **Un critere derive du corpus** (une forme non accentuee dont une soeur accentuee existe ailleurs
   dans le contenu servi). Ecartee sur chiffre : 78 pour cent des formes accentuees ne vivent que
   dans un paquet, et sur le defaut reel de s22 la detection tenait a un seul paquet voisin. Ce
   serait une regle verte le jour ou elle compte, c'est-a-dire decorative.
2. **Un inventaire des mots non accentues autorises**, sur le modele de
   `TexteAccentueExposeParLePortEntrantTest`. Total sur son complement, donc conforme a `ADR-0039`.
   Ecartee pour deux motifs : il faudrait valider a la main les 1 107 formes non accentuees
   existantes avant que l'inventaire ne prouve quoi que ce soit, et surtout **l'entree dans
   l'inventaire est accordee par la meme main que celle qui ecrit le paquet** - celui qui ecrit
   "zero" ajoute "zero" a l'inventaire pour passer au vert. Un garde-fou qu'on desarme en le
   nourrissant.
3. **Une liste de formes non accentuees frequentes** ("ecrire", "zero", "eleve"...). Ecartee : c'est
   un critere bati sur des noms d'incidents clos, la faute que s11 a payee et que `ADR-0039` nomme.
   Elle echoue par omission, en silence.
4. **`org.languagetool:language-fr` en entier** (le moteur de correction, pas seulement le lexique).
   Ecartee : il tire `languagetool-core` et sa fermeture transitive pour repondre a une question que
   1,4 Mio de donnee suffit a trancher. Le principe de la piece mobile la moins nombreuse.
5. **Un dictionnaire commite dans le depot.** Ecartee : plusieurs mebioctets de donnee dont le depot
   devrait porter la provenance et la licence, pour le meme service qu'une dependance epinglee.

## Impact

- `pom.xml` (parent) : propriete `french-pos-dict.version` et entree de `dependencyManagement`.
- `adapters/adapter-content/pom.xml` : dependance en portee `test`.
- `adapters/adapter-content/src/test/java/com/recreo/adapter/content/LexiqueFrancais.java` : cree.
- `adapters/adapter-content/src/test/java/com/recreo/adapter/content/AucunMotServiNeSEcarteDeSonAccentTest.java` : cree.
- `adapters/.../contreexemple/AucunMotServiNeSEcarteDeSonAccentTest/accent-manquant/` : cree, contenu
  repris de l'historique a `be63ce3^`.
- `LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest` : le record `TexteServi` passe de prive a
  visible dans le paquet, pour que le perimetre servi ne soit pas ecrit deux fois. Aucun changement
  de critere, aucun changement de verdict.
- `squad/decisions/adr/ADR-index.md` : ligne `ADR-0065` ajoutee.
