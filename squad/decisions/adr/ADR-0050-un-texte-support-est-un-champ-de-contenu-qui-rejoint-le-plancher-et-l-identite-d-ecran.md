# ADR-0050 - Un texte-support est un champ de contenu : il rejoint le plancher de lisibilite et l'identite d'ecran, il ne cree pas de mecanique

- **Statut** : Accepte, ses deux regles ecrites dans le meme commit
  (`TailleDesTextesEtBoutonsTest`, `AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest`,
  5 methodes neuves dont 3 morsures).
- **Sprint** : s17. **Date** : 2026-08-19. **Auteur** : theo-archi.
- **Route par** : `DEC-0039`, amendement du 2026-08-19, section 2 et "Ce que je route" - *"mecanisme
  de ce style neuf (une regle CSS pour un champ, pas une mecanique de jeu neuve)"*.

## Contexte

`COMP-5` (arbitrage prealable de `nadia-prof`, `ARBITRAGE-PREALABLE-s17-francais-cm1-et-resurrection.md`,
section 1.4) est une condition bloquante de `S17-2` : le texte-support recoit un style distinct de
celui de l'enonce, plus petit. `camille-po` en a tire une premiere lecture dans `DEC-0039` - *"ce
n'est pas une mecanique neuve (aucun code de jeu ne change, une seule regle de style s'ajoute pour
un champ)"* - et m'a route l'etablissement du mecanisme. `ines-design` a propose la forme
(`proposition-ines-texte-support-comp5.md`, branche `s17-ines-comp5`) et m'a route nommement un
point : son budget vertical s'appuie sur une mesure faite sans bloc de question separe sous le
texte-support, alors que la disposition reelle en ajoute un.

**Ce que la chaine coute reellement, mesure sur l'arbre `26c7d8b`.** Un texte porte par la donnee de
contenu et rendu par le gabarit traverse **cinq fichiers dans quatre modules** :
`QuestionContentSourceFactory` (adapter-content) qui lit la cle, `Question` (domain/content) qui la
porte, `BanditManchotSession` (domain/mechanics) qui l'expose comme elle expose deja `enonceManche()`,
`PartieController` (adapter-web) qui la met au modele, `fragments/manche.html` plus `recreo.css`
(adapter-web) qui la rendent. La formule *"aucun code de jeu ne change"* est donc **fausse a la
lettre** : la mecanique change d'une methode. Sa conclusion tient neanmoins, et pour une raison qui
n'est pas une appreciation - voir la decision.

**Le critere qui dit ce qu'est une mecanique neuve existe deja et il est executable.** `ADR-0004`,
clauses 6 et 7 (`DecouplageMecaniqueContenuTest`) : une mecanique est une classe qui implemente
`GameMechanic` et qui vit dans son propre paquet sous `com.recreo.domain.mechanics.<mecanique>`, et
deux mecaniques ne dependent pas l'une de l'autre. La question *"mecanique neuve ou champ de
contenu ?"* n'a donc pas a etre tranchee au jugement : elle se lit.

**Deux trous mesures pendant l'instruction, et ce sont eux qui rendent cet ADR du.** Aucun des deux
n'est une hypothese sur l'avenir : tous deux sont vrais sur l'arbre d'aujourd'hui.

1. **Le plancher de lisibilite de 1,25rem ne garde aucun texte qui ne soit pas un bouton ou l'un de
   sept selecteurs nommes a la main.** `TailleDesTextesEtBoutonsTest` le tient sur tout bouton
   reellement rendu (perimetre derive des gabarits) et, par
   `aucunTexteNePasseSousLaTailleDeLaConsigne`, sur `.score`, `.indice`, `.fin-de-partie`,
   `.erreur`, `.pas-de-jeu`, `.message-reprise`, `.explication` - **sept noms ecrits a la main sous
   une promesse universelle**, la forme exacte que `ADR-0039` refuse. Methode du releve : lecture des
   **20** declarations `font-size` de `static/css/recreo.css` (unique feuille maison, 20 debuts de
   declaration reels, aucune occurrence parasite dans une variable), confrontees a cette liste et a
   la population de boutons rendus. **Cinq regles ne sont lues par aucun garde-fou** :
   `.signal .verdict` - le juste/faux que l'enfant lit apres chaque reponse -, `.filtre-niveau`,
   `.question-confirmation`, `.cabinet-enseigne`, `.chip-niveau`. `.texte-support` serait la sixieme,
   et le seul chiffre gardable de la proposition d'`ines-design` (1,25rem) ne serait garde par rien.
2. **L'identite d'ecran d'`ADR-0032` est une enumeration de champs, pas une description de
   situation.** `AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest` compare le couple `enonce` +
   jeu de propositions. C'etait exact le jour ou il a ete ecrit. Le jour ou un troisieme texte est
   rendu sur le meme ecran, deux items poses sur **deux textes differents**, dont la question et les
   propositions coincident ("Que ressent le personnage ?" avec les memes trois choix - une forme
   ordinaire sur vingt items de comprehension), sont refuses comme une copie. C'est un **faux rouge**,
   donc visible, mais son message nomme une cause fausse et pousse a reecrire du contenu qui n'a rien.
   C'est ma propre lecon : *un perimetre enumere par types echoue par inclusion autant que par
   omission*.

## Decision

1. **Le texte-support entre comme une composante optionnelle de `Question`, du meme genre que
   `indice` (`ADR-0016`) et `explication` (`ADR-0024`)** : le contenu l'ecrit, la mecanique la
   transporte sans jamais la lire, le gabarit la rend. **Aucune permission de plus sur `Item`**
   (`ADR-0004` : une deuxieme forme d'item n'est ajoutee que par la mecanique qui en a besoin),
   **aucune classe implementant `GameMechanic`**, **aucun paquet neuf sous `domain/mechanics`**.
   `S17-2` reste une story de contenu.
2. **Le champ est porte par l'item, jamais par le paquet, et jamais par un niveau de regroupement
   neuf dans le schema.** Motif structurel, pas de commodite : `ListContentSource.next` tire les
   items **sans remise et dans un ordre melange**, et `BanditManchotSession` remelange les
   propositions a chaque manche. Deux items poses sur le meme texte ne sont donc jamais montres l'un
   apres l'autre de facon garantie. Un item doit se suffire a lui-meme ; un regroupement `textes:`
   au-dessus d'`items:` promettrait un ordre que le tirage ne tient pas. Le texte est donc **recopie**
   dans chaque item qui le porte.
3. **Un champ optionnel reste optionnel.** Aucun paquet qui n'en porte pas n'ecrit quoi que ce soit -
   ni cle vide, ni valeur factice. C'est la dette 2 de `CLAUDE.md` (une configuration taillee pour une
   matiere que toutes les autres remplissent de faux) et elle ne se rejoue pas ici.
4. **Toute taille de texte declaree par la feuille maison reste au-dessus du plancher de lisibilite
   de 1,25rem.** Le plancher est de `nadia-prof` et n'est pas neuf ; ce qui est neuf est que son
   perimetre cesse d'etre une liste de noms.
5. **Toute composante de `Question` est classee : dans l'identite d'ecran, ou hors d'elle avec son
   motif.** Si l'ecran de manche la montre au moment ou il se presente, `ecranDe` la lit **dans le
   meme commit** que la composante.
6. **Dans le commit qui fait entrer la composante, un test prouve qu'elle atteint l'ecran sur un item
   reel, en traversant la frontiere HTTP**, sur le modele exact de
   `LExplicationDUnItemReelAtteintLEcranDeRetourTest` (`ADR-0024`, meme motif d'emplacement : ce test
   vit dans `bootstrap`, seul module qui voie a la fois le contenu reel et la couche web). Ce point
   n'est pas decoratif : `QuestionContentSourceFactory` **ignore sans un mot toute cle qu'elle ne
   connait pas** (dette nommee et non fermee par `ADR-0024`). Une cle `texteSupport:` ecrite dans un
   paquet avant que la fabrique ne la lise laisse le build **entierement vert** et l'enfant devant un
   ecran sans texte.

## Regle executable

### Clause 1 - aucune taille declaree ne passe sous le plancher

`adapters/adapter-web/src/test/java/com/recreo/adapter/web/TailleDesTextesEtBoutonsTest.java`

```java
static void verifierQuAucuneTailleDeclareeNePasseSousLePlancher(Path racine) {
    List<RegleEcrite> regles = reglesEcritesMaison(racine);
    assertThat(regles).as("aucune regle maison relevee sous %s : ce garde-fou ne prouve rien", racine)
            .isNotEmpty();

    List<String> taillesRelevees = new ArrayList<>();
    for (RegleEcrite regle : regles) {
        for (int position : declarationsDe(regle.corps(), "font-size")) {
            String valeur = valeurDeclaree(regle.corps(), position, "font-size");
            taillesRelevees.add(regle.selecteur() + " -> " + valeur);
            assertThat(valeur).as(/* un plancher illisible est un plancher absent */).endsWith("rem");
            double taille = Double.parseDouble(valeur.substring(0, valeur.length() - "rem".length()));
            assertThat(taille).as(/* ... */).isGreaterThanOrEqualTo(PLANCHER_DE_LISIBILITE_REM);
        }
    }
    assertThat(taillesRelevees).as("ADR-0003 : ...").isNotEmpty();
}
```

- **Perimetre derive** (`ADR-0013` obligation 1) : `reglesEcritesMaison` balaie toute feuille de
  `static/` hors `static/vendor/` (`ADR-0014`), regles nichees dans une regle-@ comprises. Aucun
  selecteur n'est nomme. Une regle neuve y entre sans que personne y pense - c'est le seul point.
- **Derivation totale** (obligation 2) : une taille exprimee autrement qu'en rem (variable, `calc()`)
  est un **defaut**, jamais un cas ignore ; `declarationsDe` releve **toutes** les declarations d'un
  corps de regle et non la premiere, pour qu'une seconde ne se cache pas derriere une premiere.
- **Morsure** (obligation 3) : `uneTailleDeTexteAbaisseeHorsDesSeptSelecteursNommesFaitEchouerLeGardeFou`,
  sur **la feuille servie reelle mutee** dans un repertoire temporaire - `.signal .verdict` passe de
  1,25rem a 0,9rem. Le test verifie **dans le meme geste** que la clause enumeree reste verte sur
  cette feuille mutee : c'est la mesure exacte de ce que la clause neuve ajoute, et pas une
  affirmation. Aucune fixture ecrite de ma main : une feuille que j'ecrirais ressemblerait a ce que
  j'imagine (lecon d'`ADR-0015`, posture d'`ADR-0020`).
- **Ce qu'elle ne fait pas** : elle lit des **declarations**, jamais des pixels. `ADR-0025` et
  `ADR-0028` valent ici comme ailleurs. Elle ne distingue pas non plus un texte lu d'un signe
  decoratif - cette difference-la, aucun garde-fou ne la voit (`ADR-0035`).
- `aucunTexteNePasseSousLaTailleDeLaConsigne` **reste**, requalifie en epinglage dans sa Javadoc :
  meme resolution qu'`ADR-0015` et qu'`ADR-0039`, la propriete generale demenage dans un balayage
  derive, l'epinglage nomme reste nomme.

### Clause 2 - aucune composante d'un item n'echappe au classement

`adapters/adapter-content/src/test/java/com/recreo/adapter/content/AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest.java`

```java
static void verifierQueChaqueComposanteDUnItemEstClassee(
        Set<String> dansLIdentite, Set<String> horsIdentite) {
    Set<String> communes = new TreeSet<>(dansLIdentite);
    communes.retainAll(horsIdentite);
    assertThat(communes).as("...").isEmpty();

    Set<String> classees = new TreeSet<>(dansLIdentite);
    classees.addAll(horsIdentite);
    Set<String> portees = Arrays.stream(Question.class.getRecordComponents())
            .map(RecordComponent::getName)
            .collect(TreeSet::new, Set::add, Set::addAll);

    assertThat(classees).as("ADR-0050 : ...").isEqualTo(portees);
}
```

- **Perimetre derive** : les composantes viennent de `Question.class.getRecordComponents()`, jamais
  d'une liste. Une composante de plus rend ce test **rouge** tant que personne n'a ecrit si l'ecran
  la montre ; un classement qui survit a son champ rougit aussi.
- **Morsure** : deux methodes, sur **le vrai record** avec un classement mute -
  `uneComposanteQueLeClassementNeNommePasFaitEchouerLeGardeFou` (un nom retire, la forme exacte de
  l'oubli a venir) et `unClassementQuiSurvitASonChampFaitEchouerLeGardeFou` (un nom en trop). Muter
  le classement plutot qu'inventer un faux record garde l'objet juge identique a celui de la
  production.
- **Ce qu'elle n'empeche pas, dit sans l'habiller** : elle ne verifie pas qu'`ecranDe` lit vraiment
  les composantes classees a l'ecran - le gabarit vit dans un autre module, et deduire "cette
  composante est rendue" d'un nom d'attribut de modele serait une devinette. Un auteur qui classerait
  a tort une composante rendue **hors** de l'identite la verrait verte. Il aurait alors ecrit une
  affirmation fausse et signee, la ou aujourd'hui l'omission ne laisse aucune trace : c'est
  exactement, et seulement, ce que cette clause achete.

### Ce qui n'est pas encode ici, et par quoi c'est deja garde

- **Le caractere optionnel du champ (decision 3)** : garde par
  `ChaqueEntreeDuCatalogueChargeBienSonPaquetTest`, dont la population est
  `YamlContentPackLoader.games()`, derivee du catalogue reel. Rendre la cle obligatoire dans la
  fabrique fait echouer le chargement des **11 entrees** du catalogue et des **12 paquets** servis.
  Aucune regle neuve n'est due : celle-ci mord deja.
- **Le paragraphe vide** qu'un `th:text` non nourri produirait sur les jeux qui ne portent pas de
  texte-support : garde par `ToutEcranEchangeEstUnDocumentJouableTest.aucunEcranNeRendUnParagrapheVide`,
  perimetre derive des routes du controleur (`ADR-0020`). Un bloc rendu sans `th:if` y est rouge.
- **La classe `.texte-support` sans regle de style** : garde par
  `HabillageDesEcransTest.aucuneClassePoseeSurUnElementNEstOrpheline`, perimetre derive des gabarits.
- **La decision 6** (le champ atteint l'ecran sur un item reel) est **due dans le commit qui fait
  entrer la composante**, pas ici : ecrite aujourd'hui, elle serait rouge, puisqu'elle nomme un champ
  qui n'existe pas. La clause 2 est ce qui l'empeche d'etre oubliee : elle rougit au moment precis ou
  la composante apparait, et son message renvoie ici.

**Aucune de ces deux clauses n'est une regle ArchUnit** - l'objet juge est une declaration CSS pour
l'une, la forme d'un record pour l'autre. Le compte des regles ArchUnit du depot ne bouge pas.

## Consequences

- **Toute police maison sous 1,25rem devient impossible sans arbitrage**, y compris sur un element
  decoratif ou une mention discrete. C'est un cout reel et assume : la difference entre un texte lu et
  un signe decoratif n'est pas lisible par un garde-fou, et le plancher se defait par une decision de
  `nadia-prof`, comme la seule entaille jamais accordee (1,875rem sous le repli a quatre colonnes),
  jamais par une ligne de feuille.
- **La morsure de la clause 1 depend d'une regle nommee de la feuille servie** (`.signal .verdict`).
  Si cette regle est renommee ou supprimee, le test rougit sur *"la mutation doit porter sur une
  regle que la feuille servie porte vraiment"* - un rouge exact, qui demande de choisir une autre
  mutation, pas un faux vert.
- **Le classement de la clause 2 est une affirmation humaine.** Elle transforme un oubli silencieux
  en ligne de diff a relire ; elle ne transforme pas une erreur de jugement en rouge.
- **`S17-2` reste une story de contenu** et n'entre pas dans le decompte des chantiers
  d'architecture. Le cout de developpement du champ n'est pas nul pour autant : cinq fichiers, quatre
  modules, plus le test de la decision 6. Ce chiffre est a la disposition de `sofia-sm` pour le
  planning ; il n'appartient pas a cet ADR d'en tirer une priorite.
- **Le texte est recopie dans chaque item qui le porte** (decision 2). Sur vingt items poses sur
  quelques textes, c'est de la duplication visible dans le YAML. Elle est le prix de l'independance du
  tirage, et elle est deja gardee contre son pire effet :
  `AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest` refuse deux ecrans identiques, la clause 2 lui
  donne les moyens de continuer a le faire quand l'ecran porte trois textes.

**Ce qui me ferait changer d'avis plus tard.** Si une quatrieme composante de texte apparait sur
`Question`, le classement de la clause 2 devient l'endroit ou une vraie decision se cache derriere une
liste ; il faudra alors deriver "cette composante est rendue" du gabarit lui-meme, ce qui n'est
possible que depuis `bootstrap`, seul module qui voie les deux cotes - le meme motif d'emplacement que
`LExplicationDUnItemReelAtteintLEcranDeRetourTest`. Je ne le fais pas aujourd'hui pour trois
composantes classees et une quatrieme annoncee : ce serait une abstraction repondant a un usage que
j'imagine.

## Alternatives considerees

1. **Ne rien ecrire du tout - le mecanisme est evident et les garde-fous existants suffisent.**
   Ecartee par les deux trous mesures du contexte, qui sont vrais aujourd'hui et n'attendent pas
   `S17-2` : cinq regles de taille lues par personne, et une identite d'ecran qui rendra un verdict
   faux des que l'ecran portera trois textes.
2. **Fondre le texte-support dans l'`enonce` par concatenation.** Zero ligne de code, zero champ. Mais
   les deux textes portent alors le meme style, `COMP-5` devient impossible a satisfaire, et un texte
   de plusieurs lignes s'affiche a 2rem en gras - exactement la cause pour laquelle `DEC-0024` a
   retire un jeu du catalogue. Ecartee.
3. **Un niveau de regroupement dans le schema (`textes:` puis `items:`).** Ecartee : le tirage est
   sans remise et melange, un regroupement promettrait un ordre que la mecanique ne tient pas, et il
   ajouterait une imbrication au chargeur unique d'`ADR-0007` pour deux fichiers. Trois lignes
   semblables valent mieux qu'une abstraction prematuree.
4. **Un texte-support porte par le paquet.** Ecartee : vingt items sur un seul texte ne sont pas la
   competence visee ; c'est un arbitrage pedagogique, mais il tombe aussi structurellement, un paquet
   ne portant aujourd'hui aucun texte destine a l'enfant.
5. **Encoder un plafond de longueur du texte-support** (nombre de mots, de caracteres, de lignes).
   Ecartee sans hesitation : `DEC-0016` l'interdit nommement, `ADR-0028` l'a deja refusee au motif
   qu'une telle regle serait rouge sur un contenu que `nadia-prof` a valide, et la mesure d'`ines-design`
   ne fournit **aucun** intervalle continu - deux points, pas une courbe.
6. **Deriver l'identite d'ecran de toutes les composantes du record.** Tentant, et faux dans le
   mauvais sens : `indice` et `explication` y entreraient, et deux items presentant le meme ecran mais
   portant deux indices differents redeviendraient legitimes. Une derivation qui **desserre** un
   garde-fou est pire que l'enumeration qu'elle remplace.
7. **Allonger la liste des sept selecteurs de `.texte-support`.** Ecartee : c'est la reponse qui a
   deja echoue deux fois dans ce depot (`ADR-0015`, `ADR-0039`). La sixieme classe non lue serait
   oubliee exactement comme les cinq premieres.
