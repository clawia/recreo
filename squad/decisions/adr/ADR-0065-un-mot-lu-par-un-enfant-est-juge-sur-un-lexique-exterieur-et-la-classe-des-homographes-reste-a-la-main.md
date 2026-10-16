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

## Amendement du 2026-10-06 (s51, `theo-archi`) - A1, le distracteur sans cedille dans la forme de `GRL-CE1-2`

Le texte ci-dessus est inchange. Cet amendement ajoute une admission et deux clauses.

**Ce qui a joue.** `GRL-CE1-1` et `GRL-CE1-2` (`squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md`,
section 5.13, ligne `FRA-CE1-22`) imposent des items en `c`/`ç` dont le distracteur est le meme mot sans
cedille : *un garçon* / *un garcon*. `LexiqueFrancais.squelette` retire la cedille comme tout signe
diacritique, *garcon* n'est pas au lexique et *garçon* l'est sous le meme squelette : le critere de la
decision 1 refuse ce distracteur, et le paquet de la ligne est rouge par construction. Mesure de
l'avis de faisabilite de s51 (section 1), sur une sonde de 12 items dont 4 en `ç` : 4 defauts, un par
item en `ç`, et 0 defaut quand ces quatre items sont remplaces par des mots sans cedille.

**Decision.** Un distracteur est admis sans etre juge si et seulement si, **compare a la bonne
reponse du meme item** :

1. les deux chaines ont la meme longueur apres normalisation NFC ;
2. elles different en au moins une position ;
3. a chaque position ou elles different, la bonne reponse porte `ç` (ou `Ç`) et le distracteur `c`
   (ou `C`).

Hors des positions de la cedille, un distracteur admis est donc la bonne reponse lettre pour lettre,
et la bonne reponse est jugee pour son propre compte : l'admission ne soustrait au critere aucun
autre mot.

Ce qui reste rouge, chaque cas etant une clause de mise a l'epreuve : le mot sans cedille dans un
enonce, un indice, une explication ou une bonne reponse ; un distracteur dont la bonne reponse ne
porte pas la forme marquee ; deux propositions qui different par autre chose que la cedille (*un
garçon* / *des garcons*) ; **toute autre marque** (*un zéro* / *un zero* reste refuse).

**Pourquoi la cedille seule, et pas "la forme marquee".** L'avis de s51 proposait *la forme
marquee*. Je retiens la forme la plus etroite : la seule regle qui demande aujourd'hui un mot
francais prive de sa marque est `GRL-CE1-2`, et sa seule marque est la cedille. Etendre l'admission
aux accents ouvrirait au paquet suivant la porte que cet `ADR` a ete ecrit pour fermer - *zero* pour
*zéro* dans un distracteur dont la bonne reponse est accentuee - sans qu'aucune decision
pedagogique ne l'ait demande.

**Ce que l'admission ne derive pas d'une liste.** Elle ne nomme aucun mot et aucun paquet : elle est
derivee de la forme de l'item. Le rapprochement du distracteur et de sa bonne reponse tient a
l'ordre et au nom des champs que produit `LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest.textesServis`
(la bonne reponse precede ses distracteurs, meme paquet, meme enonce). S'il cessait de tenir, aucun
distracteur ne serait plus admis et le garde-fou rougirait : le mode de defaillance est bruyant.

### Regle executable

`adapters/adapter-content/src/test/java/com/recreo/adapter/content/AucunMotServiNeSEcarteDeSonAccentTest.java`,
fonction pure `neDiffereQueParLaCedille(distracteur, bonneReponse)`, appelee dans
`verifierQueChaqueMotServiPorteSonAccent` pour toute chaine portant la bonne reponse de son item.
Les trois clauses de la decision restent ; deux s'ajoutent :

4. `leDistracteurSansCedilleNEstAdmisQueDansLaFormeDeGrlCe1Deux` - sur le paquet
   `src/test/resources/contreexemple/AucunMotServiNeSEcarteDeSonAccentTest/cedille/`, l'item 1 est
   l'exemple de `GRL-CE1-2` tel que `nadia-prof` l'a ecrit et n'est pas nomme ; les items 2 a 6
   sortent chacun de cette forme d'une seule facon et chacun est nomme.
5. `laFormeDeGrlCe1DeuxSeJugeSurLesDeuxPropositionsEntieres` - la fonction pure, y compris une bonne
   reponse en NFD.

**Morsure mesuree**, `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test
-Dtest=AucunMotServiNeSEcarteDeSonAccentTest -Dsurefire.failIfNoSpecifiedTests=false`, perimetre :
le module `adapter-content` et ses amonts, `-am` porte, l'enveloppe rapporte 21.0.10 :

| Etat du test | Resultat |
|---|---|
| tel que commite | 5 tests, 0 echec |
| mutation : tout distracteur admis des que sa bonne reponse est connue | 5 tests, 1 echec, clause 4 (l'item 2 n'est plus nomme) |
| mutation : aucune admission | 5 tests, 1 echec, clause 4 (l'item 1 est nomme) ; clause 1 verte |

La derniere ligne dit aussi qu'**aucun distracteur du contenu servi au 2026-10-06 ne depend de
l'admission** : sans elle, le contenu de production reste sans defaut.

**Ce n'est pas une regle ArchUnit**, pour le motif de la decision : l'objet juge est le contenu rendu
par le chargeur reel. Le compte de regles ArchUnit du depot est inchange.

### Consequences

- **Bonne** : la ligne `FRA-CE1-22` devient ecrivable dans la forme que `GRL-CE1-2` demande, sans
  table d'exemption ni cliquet.
- **Mauvaise** : un distracteur sans cedille est desormais servi a un enfant de CE1, par
  construction. C'est l'objet meme de l'item, et c'est a `nadia-prof` de dire si cette forme tient sa
  condition ; la porte technique ne le tranche pas.
- **Mauvaise** : le rapprochement depend de l'ordre de production d'un autre test. Le defaut serait
  bruyant, pas silencieux, mais il couplerait deux garde-fous.

### Ce qui me ferait changer d'avis plus tard

- Une regle pedagogique ecrite qui demande un distracteur prive d'une **autre** marque : elle
  appellerait un nouvel amendement, nommant cette marque, et non l'elargissement de celui-ci a toute
  marque.
- Le refus par `nadia-prof` de la forme *un garçon* / *un garcon* au profit d'une autre forme d'item
  (*gar_on*, propositions `c` / `ç`) : l'admission deviendrait sans objet, et cet amendement serait a
  retirer avec ses deux clauses plutot qu'a laisser dormir.

## Amendement du 2026-10-13 (s57, `theo-archi`) - A2, deux instances de francais CE1 en parallele (A et A')

Le texte ci-dessus est inchange, critere, perimetre et cinq clauses compris. Cet amendement repond a la
story `S57-ADR-0065-DEUX-INSTANCES` et a la condition 2 de `nadia-prof`
(`squad/conformite/ARBITRAGE-PREALABLE-s57-criteres-de-la-version-cp-ce1-cm1.md`, section 4.2) : *"Aucune
des deux instances n'amende `ADR-0065` ni le garde-fou de l'accent : un besoin se route a `theo-archi`."*

### Etat du depot que cet amendement suppose

- `round-two` a `9d101ea0` ; le garde-fou tel que `c2802a32` l'a laisse (cinq clauses, admission A1) ;
  `org.languagetool:french-pos-dict:0.7`.
- Le decoupage de `DEC-0120` section 1 : A porte le groupe X (`-04`, `-05`, `-06`, `-07`, `-11`), A' le
  groupe Y (`-08`, `-09`, `-10`, `-15`, `-24`). **A et A' n'ecrivent jamais le meme paquet** (condition 1
  de `nadia-prof`) : la ressource qu'elles partagent n'est pas un paquet, c'est ce garde-fou.
- Aucun worktree d'un lot de A' n'est ouvert. Cet amendement est fusionne avant le premier (condition 2).

### Ce que la mesure a etabli, avec sa methode

1. **Le garde-fou ne porte aucune liste de mots.** Lu sur le fichier : le critere ne nomme aucun mot ;
   les seuls mots ecrits dans le test sont les assertions des clauses 2, 4 et 5 sur leurs contre-exemples
   et leurs chaines en memoire (*ecrire*, *zero*, *zeros*, *garcon*, *garcons*). Une instance ajoute son
   vocabulaire en ajoutant un paquet, et le lexique le juge : **aucun fichier du garde-fou n'a a changer
   pour cela.** La formulation du backlog (*"la meme liste de mots du meme test"*) ne decrit donc pas
   l'objet partage.
2. **Mesure sur l'historique.** `git diff --name-status --diff-filter=A c2802a32 9d101ea0` sur
   `adapters/adapter-content/src/main/resources/contenu/` : 31 paquets `ce1-*.yaml` ajoutes (francais et
   mathematiques confondus). `git log --full-history --no-merges c2802a32..9d101ea0` sur les quatre chemins
   du point 1 de la decision : un seul commit, `02ba9e80`, qui est A1 lui-meme commite une seconde fois sur
   la branche d'une instance a s51 (meme `git patch-id --stable` que `c2802a32`, `61478649...`, recalcule
   ce jour). Depuis l'origine (`--full-history --no-merges`, historique atteignable depuis `9d101ea0`) :
   quatre commits, `f3848460` `[THEO]`, `acfecb32` `[SOF]`, `c2802a32` et `02ba9e80` `[THEO]`.
3. **Ce sur quoi deux instances peuvent entrer en collision, c'est donc une admission**, pas un mot : un
   lot dont la forme demande un mot que le critere refuse par construction. Deux cas ecrits dans le depot :
   la cedille de `GRL-CE1-2` (A1), et les formes regularisees d'*aller* au singulier (`PRS-CE1-3` (b),
   avis de faisabilite de s52, section 1.2, non retenues). Et un cas de modification du perimetre par un
   autre agent que le proprietaire : `acfecb32` (s24, lot 5b) a borne les chaines jugees a celles que
   l'enfant lit.

### Decision (A2)

1. **Aucune instance ne modifie le garde-fou.** Ni `nadia-prof`, ni le developpeur d'un lot de A ou de A',
   ni aucun autre agent que `theo-archi`, ne modifie : ce fichier d'`ADR` ;
   `AucunMotServiNeSEcarteDeSonAccentTest.java` ; `LexiqueFrancais.java` ;
   `src/test/resources/contreexemple/AucunMotServiNeSEcarteDeSonAccentTest/` (les trois derniers sous
   `adapters/adapter-content/`) ; la version de `french-pos-dict` dans les deux `pom.xml`.
2. **Un lot rouge sur `chaqueMotServiEnProductionPorteSonAccent` s'arrete.** L'instance ecrit le besoin
   (paquet, item, mot refuse, forme de l'item qui l'exige) et le route a `theo-archi`. Son lot attend ;
   les lots de l'autre instance n'attendent pas.
3. **Un amendement a la fois.** `theo-archi` l'ecrit sur un worktree propre issu de `round-two`, jamais sur
   la branche d'une instance, et `sofia-sm` le fusionne sur `round-two`. Un second besoin, de l'une ou de
   l'autre instance, part de `round-two` apres cette fusion. L'admission se derive de la forme de l'item,
   jamais d'une liste de mots (alternative 2 et amendement A1 ci-dessus).
4. **Une instance recoit un amendement en fusionnant `round-two` dans sa branche**, jamais en le
   recommitant : c'est la duplication de s51.
5. **Un faux rouge du lexique** (un mot francais rare absent du lexique) suit la meme route ; la reponse
   reste celle de "Ce qui me ferait changer d'avis" : changer de lexique ou reduire le perimetre avec un
   motif ecrit, jamais une liste d'exemptions.
6. **La classe des homographes reste a la main de `nadia-prof`**, chaque instance pour ses lots.

### Regle executable

**Ce n'est pas une regle ArchUnit** : les objets sont des fichiers et l'auteur d'un commit, pas un graphe
de types. Le compte de regles ArchUnit est inchange : 41 `static final ArchRule`, 38 `@ArchTest`, methode
d'`ADR-0104` (`git grep` des deux motifs sur les `*.java` suivis a `9d101ea0`) ; cet amendement ne touche
aucun fichier Java.

**Ce qui s'execute deja :**

- **Le point 2 est tenu par la clause 1 elle-meme**, dans le job `build` : un lot qui a besoin d'une
  admission ne peut pas etre vert sans elle. L'attente est imposee par le rouge, pas par la discipline.
- **Le point 3 est signale, pas empeche** : deux amendements differents ecrits en parallele touchent la
  meme methode (`verifierQueChaqueMotServiPorteSonAccent`) et la fin de la meme classe, et la fusion du
  second entre en conflit. Un amendement identique commite deux fois n'entre pas en conflit (s51) : sans
  consequence de contenu, et sans automate.

**Le point 1 : controle par chemin et prefixe, dans le job `prefixes`.** Le commit `7a34366c` de cet
amendement le proposait sans l'ecrire (`.github/scripts/` et `ci.yml` etaient hors du perimetre de la
story) et ouvrait une dette. Ecrit dans le commit suivant de la meme branche, sur autorisation de
l'utilisatrice du 2026-10-13. **La dette est payee** ; ce qui reste est nomme plus bas.

- **Script** : `.github/scripts/verifier-garde-fou-accent.sh <plage>`. Il refuse tout commit de la plage
  qui touche un chemin garde sans le prefixe `[THEO]` en tete de sujet. Chemins gardes : ce fichier,
  `AucunMotServiNeSEcarteDeSonAccentTest.java`, `LexiqueFrancais.java`, le repertoire des
  contre-exemples, `adapters/adapter-content/pom.xml` **entier**, et le `pom.xml` parent par
  `git log -G 'french-pos-dict'`.
- **Etape** : `.github/workflows/ci.yml`, job `prefixes`, deux etapes apres les prefixes : le test, puis
  le controle sur `BASE..SHA`, ou `SHA^!` sans base comparable.
- **Test** : `.github/scripts/verifier-garde-fou-accent-test.sh` (`ADR-0009`), 21 cas. Un depot jetable,
  un commit juge par cas : chaque chemin garde mord, deux voisins ne mordent pas (une autre ligne du pom
  parent, un paquet de contenu). Puis l'historique reel : `acfecb32^!` refuse, `c2802a32^!` et
  `02ba9e80^!` acceptes ; un commit absent est un echec, jamais un cas saute.

**Quatre ecarts a la forme proposee par `7a34366c`**, chacun trouve en l'ecrivant :

1. **Le pom d'`adapter-content` est garde entier.** `-G 'french-pos-dict'` ne voit pas une ligne
   `<version>` ajoutee dans le bloc de la dependance, qui ne nomme pas le lexique. Ce fichier n'a ete
   touche que par deux commits, tous deux `[THEO]` (`git log --format='%h %s' -- adapters/adapter-content/pom.xml` :
   `d2005d2c`, `f3848460`) : le garder entier n'exclut personne. Le pom parent reste garde par `-G`,
   pour le motif que la proposition donnait.
2. **Les fusions ne sont pas exclues.** La simplification d'historique de `git log -- <chemins>` ecarte
   une fusion identique a l'un de ses parents sur ces chemins ; elle ne garde que celle qui y ecrit autre
   chose, une resolution de conflit, qui est alors jugee comme un commit. `--no-merges` la laissait
   passer. Sur l'historique reel, aucune fusion n'est retenue (`git log --merges HEAD -- <chemins>` ne
   rend rien) : le changement n'exclut personne.
3. **Sans base comparable, la plage est `SHA^!`, et le script refuse toute plage qui commence par un
   tiret** (code 2). La plage `-1` de l'etape des prefixes, recopiee ici, aurait juge
   `git log -1 -- <chemins>` : le dernier commit qui a touche ces chemins, pas le dernier commit.
4. **Un chemin garde absent de `HEAD` rend le controle rouge.** Sans cela, un renommage le rendrait muet.

**La morsure, prouvee par mutation du script** (`sed` sur une copie, le test relance, la copie restauree ;
chaque mutation a modifie au moins une ligne et le test a lance 21 cas) : sept mutations, sept rouges,
chacune sur le cas qu'elle vise et sur lui seul. Retirer le repertoire des contre-exemples, accepter
`[THEO]` hors de la tete du sujet, retirer le pom d'`adapter-content`, remettre `--no-merges`, neutraliser
le controle d'existence, retirer la garde du tiret, casser le motif du pom parent.

**Ce qu'il interdit reellement, mesure sur l'historique.** `verifier-garde-fou-accent.sh HEAD` sur
l'historique atteignable depuis la tete de cet amendement : refuse **`acfecb32`** (`[SOF]`, s24, lot 5b)
et lui seul. Sur une plage qui le couvre, **`02ba9e80` n'est pas juge** : la simplification le cache,
sa modification etant celle de `c2802a32` ; juge seul (`02ba9e80^!`), il est accepte.

**Ce qui reste sans automate :**

- **Le point 4** : `02ba9e80`, un amendement de ma main recommite sur la branche d'une instance, porte
  `[THEO]` et passe. Rien ne lie un commit a sa branche.
- **Le prefixe est declaratif.** Un autre agent qui ecrirait `[THEO]` passerait ; le controle tient
  contre `acfecb32`, un agent qui signe de son propre prefixe, pas contre une usurpation.
- **Une resolution de conflit sur la ligne du lexique du pom parent** : `-G` ne lit pas le diff d'une
  fusion.

La verification a la main par `sofia-sm` que ce paragraphe exigeait jusqu'a la fusion du controle
(`git diff --stat round-two...<branche> -- <chemins>`) cesse d'etre due quand cette branche est fusionnee
sur `round-two`.

**Pourquoi le test n'est pas modifie**, alors que la story demandait l'amendement et le test dans un seul
commit : rien dans `AucunMotServiNeSEcarteDeSonAccentTest` n'est propre a une instance (mesure, point 1).
Une clause ajoutee pour l'occasion n'interdirait rien de ce que la decision interdit : celui qui modifierait
le garde-fou modifierait aussi cette clause. Elle serait decorative.

### Consequences

- **Bonne** : aucun lot de francais CE1 n'a a toucher le garde-fou pour ajouter son vocabulaire ; les 31
  paquets de CE1 ajoutes depuis A1 l'ont fait sans le toucher.
- **Mauvaise** : un lot de A' qui demande une admission attend un amendement, une fusion, puis une fusion
  de `round-two` dans sa branche. Le delai depend de l'heure de fusion, a `sofia-sm`.
- **Mauvaise** : le point 1 interdit desormais ce que `acfecb32` a fait a s24. Un developpeur qui veut
  borner ou etendre ce garde-fou passe par `theo-archi`.
- **Mauvaise** : le job `prefixes` refuse un commit d'un autre agent sur ces chemins, y compris une
  correction juste. Elle passe par `theo-archi`.

### Ce qui me ferait changer d'avis plus tard

- Un troisieme besoin d'admission dans un meme sprint : la forme des items de francais CE1 demanderait
  souvent un mot hors du francais, et une admission par amendement serait trop lente.
- Un conflit de fusion sur ce garde-fou qui ne viendrait pas de deux amendements : d'autres mains le
  touchent malgre le controle, et le prefixe declaratif ne suffit plus.

### Impact

- Commit `7a34366c` : ce fichier, et la ligne `ADR-0065` de `ADR-index.md`.
- Commit suivant : `.github/scripts/verifier-garde-fou-accent.sh`, son test, deux etapes du job
  `prefixes` de `.github/workflows/ci.yml`, et ce paragraphe. Aucun fichier Java ; compte ArchUnit
  inchange.

## Amendement du 2026-10-16 (s57, `theo-archi`) - A3, un affixe cite avec son trait d'union n'est pas un mot

Le texte ci-dessus est inchange, A1 et A2 compris. Cet amendement repond au besoin route par `K10` (A2,
point 2) : `squad/sprints/s57-version-cp-ce1-cm1-trois-instances/rapport-sofia-dev-palier-2.md`, lot
`S57-BR-FRA-CE1-24`, commit `1febd011`. Il est ecrit sur un worktree issu de `round-two` a `4004fab7`
(A2, point 3) ; aucune autre branche ne modifie le garde-fou (`git diff --name-only round-two...<branche>`
sur ce fichier et sur le test, pour chaque branche locale `*theo*` : vide).

### Contexte

Le lot branche `ce1-familles-de-mots` au catalogue. Son `descripteur.competence`, copie mot pour mot de la
ligne `FRA-CE1-24` du referentiel, devient servi : *"... repérer un préfixe ou un suffixe fréquent (re-,
dé-, -eur...)."*. Le critere le refuse : `motsDe` coupe au trait d'union, `re` n'est pas une forme du
lexique, `ré` (la note) en est une du meme squelette. **Le texte est juste** : `re-` est la graphie du
prefixe. **C'est le critere qui se trompe d'objet** : il juge un affixe comme un mot, sur un lexique qui ne
porte que des mots. `dé-` et `-eur` passaient par hasard (`dé` est un mot, `eur` n'a pas de soeur).

**Mesure, sur materiau reel**, copie de l'arbre de `1febd011` (`git archive`), `bash outils/mvn-jdk21.sh
-B -pl adapters/adapter-content -am test`, perimetre `adapter-content` et ses amonts, JDK 21.0.10 :
`BUILD FAILURE`, 1591 tests, **1 echec, un seul defaut** (*"le mot "re" n'est pas une forme du
francais"*). Le meme arbre avec le test de cet amendement : `BUILD SUCCESS`, 1593 tests, 0 echec. Sur
`round-two` (`4004fab7`), le garde-fou etait vert avant et reste vert apres : **A3 ne change aujourd'hui
qu'un verdict, celui de `re`**.

### Decision (A3)

1. **Une suite de lettres collee a un trait d'union en bord de mot est un affixe cite, pas un mot**, et
   `motsDe` ne la rend pas. Bord de mot : le trait d'union precede la suite et n'est pas lui-meme precede
   d'une lettre (`-eur`), ou il la suit et n'est pas suivi d'une lettre (`re-`, `dé-`). L'admission se
   derive de la forme du texte, jamais d'une liste (A2, point 3).
2. **Un trait d'union entre deux lettres lie deux mots, qui restent juges** (`peut-être`, `re-ecrire`).
3. **L'affixe cite est declare non garde**, comme la classe des homographes : son accentuation reste a la
   lecture de `nadia-prof`. Le lexique ne pouvait pas la juger de toute facon : il ne porte pas d'affixes.
4. Le libelle du paquet n'est pas reecrit, et `camille-po` comme `nadia-prof` n'ont rien a trancher pour
   ce lot.

### Regle executable

**Ce n'est pas une regle ArchUnit**, pour le motif ecrit en tete de cet `ADR`. Compte inchange : 41
`static final ArchRule`, 38 `@ArchTest`, 16 fichiers (methode d'`ADR-0104`, `git grep` des deux motifs sur
les `*.java` de `4004fab7`) ; le fichier Java modifie n'en porte aucune.

`AucunMotServiNeSEcarteDeSonAccentTest`, `motsDe` et `estUnAffixeCite`, plus deux clauses (7 au total) :

- `unAffixeCiteAvecSonTraitDUnionNEstPasJugeCommeUnMot` : le libelle reel, recopie de la ligne 22 du
  paquet a `4004fab7`, ne rend aucun defaut ; le meme sans le trait d'union de `re` rend exactement le
  defaut `re` ; le meme avec `re-ecrire` rend `re` et `ecrire`.
- `unMotColleAUnTraitDUnionEnBordResteInvisible` : le prix, grave (voir Consequences).

**Morsure, par mutation du test sur une copie** (`4004fab7` plus ce test, la classe seule lancee, copie
restauree, `cmp` identique) : admission neutralisee (`return false`), 2 echecs sur 7, les deux clauses
neuves ; condition de bord retiree devant, 1 echec (`re-ecrire`) ; condition de bord retiree derriere,
1 echec (`re-ecrire`). Trois mutations, trois rouges, chacune sur la clause qu'elle vise.

### Consequences

- **Bonne** : le lot `-24` passe sans toucher son texte. Toute citation d'affixe a venir avec son trait
  d'union (le paquet porte 10 items d'affixe, `FM2`) passe sans amendement.
- **Mauvaise, gravee dans un test** : un mot reel colle a un trait d'union en bord de mot n'est plus juge.
  `"Il faut -ecrire."` et `"Il faut ecrire- vite."` passent, `"Il faut ecrire."` reste rouge. Cette
  forme n'apparait dans le texte servi que par une faute de frappe ; elle rejoint la lecture de
  `nadia-prof`.
- **Mauvaise** : `-24` attend la fusion de cet amendement sur `round-two`, puis une fusion de `round-two`
  dans sa branche (A2, point 4), a `sofia-sm`.

**Dette ouverte, sans lien avec le verdict de `re`** : la decision 3 du texte d'origine dit juger *"les
six champs du catalogue qu'une vue affiche (`titre`, `consigne`, et les quatre du descripteur)"*. Mesure
sur `4004fab7` : `git grep -n "competence()" -- '*/src/main/*'` ne rend rien, et les seules lectures du
descripteur hors de `domain` et `adapter-content` sont `niveau()` et `matiere()` dans `PartieService`.
**`domaine` et `competence` ne sont lus par aucune vue** : le perimetre les inclut par enumeration, sans
qu'une vue les affiche. C'est une inclusion de trop, qui ne coute que des faux rouges ; je ne la retire
pas ici, parce que le retrait de `competence` aurait rendu ce lot vert en laissant le critere faux sur un
champ affiche. Le traitement juste est un perimetre derive de ce que les vues lisent ; je l'ecris le jour
ou un second faux rouge vient d'un champ non affiche, ou si une vue se met a afficher l'un des deux.

### Alternatives considerees

- **Reecrire le libelle** (`re-` devient autre chose) : corrige un texte juste pour satisfaire un faux
  rouge, et deplace la decision vers `camille-po` et `nadia-prof` sans motif pedagogique. Ecartee.
- **Retirer `competence` du perimetre** : motif reel (aucune vue ne l'affiche), mais le critere resterait
  faux sur tout champ affiche qui cite un affixe. Ecartee, dette ouverte ci-dessus.
- **Admettre `re` par liste** : interdit par A2 point 5 et par "Ce qui me ferait changer d'avis".

### Ce qui me ferait changer d'avis plus tard

- Une faute reelle d'accent trouvee par `nadia-prof` dans la forme `mot-` ou `-mot` : la borne serait trop
  large, et l'admission devrait exiger une ponctuation ou une parenthese autour de l'affixe.
- Un affixe cite sans trait d'union (*"le prefixe re"*) : la forme ne le voit pas, il resterait rouge, et
  ce serait un nouveau besoin route par la meme voie.

### Impact

- Ce fichier, la ligne `ADR-0065` de `ADR-index.md`, `AucunMotServiNeSEcarteDeSonAccentTest.java`. Commit
  `[THEO]` (job `prefixes`, controle du proprietaire du garde-fou).
