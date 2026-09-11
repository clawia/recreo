# ADR-0091 - Le texte alternatif d'un item de designation ne nomme aucune de ses zones, et son indice ne porte aucun jeton distinctif du libelle de sa bonne zone

- **Statut** : **`Propose`**. Ses deux regles sont **ecrites ci-dessous et dues avec le lot de
  `S35-QLM14-FRISE`**. Elles ne sont pas dans l'arbre a la date de redaction, et cet `ADR` ne
  passera `Accepte` que quand elles y seront, avec leurs jeux fautifs. **La clause 1 est rouge sur
  le contenu servi aujourd'hui** : c'est elle qui paie `FRI-R1`, et une regle ecrite le jour ou
  elle gene est une regle qu'on negocie.
- **Sprint** : s35. **Date** : 2026-09-11. **Auteur** : `theo-archi`.
- **Route par** : `nadia-prof`, `verdicts-nadia-instance-B.md` de s34, section 4 - `FRI-R1`,
  bloquante, refus de `QLM-CP-14` au premier passage. Et par
  `squad/sprints/s34-dernier-sprint-du-plan-2-palier-2-engage/RAPPORT.md`, *"Encore ouverts"*.
- **Prolonge** : `ADR-0076` (designer n'est pas contenir - son operateur et **son tokeniseur**
  sont reutilises tels quels), `ADR-0080` (garde-fou 5, qui tient le meme genre de propriete sur
  l'enonce), `ADR-0013` (un garde-fou derive son perimetre et se prouve mordant), `ADR-0051` (pas
  de regle a population vide).
- **Ce que cet `ADR` ne fait pas** : il ne prononce aucun Done, il n'ecrit aucun texte alternatif
  et aucun indice, il ne juge aucun dessin, il **n'amende pas `ZON-10`** (les libelles de zone
  restent positionnels, `nadia-prof` l'a tranche dans le meme verdict), et il ne ferme aucune ligne
  de matrice.
- **Arbre mesure** : `round-two` a `60e1c836`, worktree dedie. **Toutes les mesures de cet `ADR`
  sont statiques** - lectures de fichiers et decoupages en jetons faits a la main sur le materiau
  reel. **Aucun build n'a ete lance** ; aucun chiffre ci-dessous n'est un compte de tests.

---

## Contexte

### 1. Le fait, et il est de `nadia-prof`

Verdict du 2026-09-11, `FRI-R1`, bloquante :

> *"Les deux dessins servent un texte alternatif qui liste leurs moments dans l'ordre de la frise,
> et les libelles de zone sont des rangs. Les deux ensemble forment une table de correspondance
> complete."* Neuf items sur onze se gagnent entierement sans regarder le dessin, et sur l'item 1
> l'indice donne le libelle de la bonne zone mot pour mot. *"Un texte alternatif honnete dit ce que
> l'image est, pas ce qu'elle contient dans l'ordre."*

### 2. La mesure, avec sa methode dans le meme paragraphe

Lecture de `adapters/adapter-content/src/main/resources/contenu/cp-frise-de-la-journee.yaml`, le
seul paquet de type `designation` servi, le 2026-09-11. Item 1 :

| Terme | Valeur servie |
|---|---|
| `enonce` | *"Montre le lever."* |
| `illustrationTexteAlternatif` | *"Frise de la journee d'ecole, six moments sur un axe - le lever, le petit-dejeuner, l'ecole du matin, le dejeuner, l'ecole de l'apres-midi, le gouter."* |
| identifiants des zones | `lever`, `petit-dejeuner`, `ecole-matin`, `dejeuner`, `ecole-apres-midi`, `gouter` |
| libelles des zones | *"le premier"* a *"le sixieme"* |
| `indice` | *"C'est le tout premier moment de la journee."* |

**Le chemin de fuite, ecrit comme un enchainement plutot que comme un soupcon** : l'enonce nomme un
moment ; le texte alternatif dit a quel rang ce moment se trouve ; le libelle d'une zone **est** un
rang. L'enfant qui lit les trois textes n'a jamais besoin du dessin. **Aucun des trois textes n'est
fautif seul** - c'est leur composition qui gagne la manche, et c'est pour cela que le defaut a
traverse un sprint entier sans qu'aucun garde-fou ne s'en apercoive.

### 3. Trois textes peuvent se substituer au dessin ; le depot n'en garde qu'un

| Terme de `ZoneADesigner` | Compare a quoi, aujourd'hui | Par quoi |
|---|---|---|
| `enonce` | au libelle de la bonne zone | `AucunEnonceDeDesignationNeNommeLeLibelleDeSaBonneZoneTest` (garde-fou 5 d'`ADR-0080`) |
| `indice` | **a rien** | - |
| `illustrationTexteAlternatif` | **a rien** | - |

C'est la meme forme de trou qu'`ADR-0076` a ferme sur un autre couple : une propriete tenue sur un
terme et laissee ouverte sur les deux autres. Le texte alternatif est **obligatoire** sur un item de
designation (invariant du constructeur compact de `ZoneADesigner`, `ADR-0080` decision 1) : il
existe sur **tous** les items, donc sa population n'est jamais vide, donc rien n'excusait de ne pas
le juger.

---

## Decision

### Clause 1 - le texte alternatif d'un item de designation ne nomme aucune de ses zones

**L'ancre est l'identifiant de la zone, pas son libelle.** Motif : les libelles sont positionnels
par `ZON-10` (*"le premier"*, *"le deuxieme"*), ils ne nomment donc pas ce que le dessin montre ;
l'identifiant, lui, est le seul terme du contenu qui nomme la chose. `lever`, `petit-dejeuner`,
`ecole-matin` disent ce que chaque zone **est**, et c'est ce qu'un texte alternatif honnete ne doit
pas enumerer.

**L'operateur est celui d'`ADR-0076`** - decoupage en mots, **jamais sous-chaine** - et son
tokeniseur est **reutilise tel quel**, pas recrit a cote : une zone est *nommee* quand les jetons de
son identifiant apparaissent comme **suite contigue** de jetons du texte alternatif.

**Etat sur le materiau reel, par lecture** (le tokeniseur decoupe sur tout caractere non
alphanumerique et replie accents et casse - `petit-dejeuner` rend `[petit, dejeuner]`) :

| Zone (identifiant) | Jetons | Nommee dans le texte alternatif de l'item 1 ? |
|---|---|---|
| `lever` | `[lever]` | **oui** - *"le lever"* |
| `petit-dejeuner` | `[petit, dejeuner]` | **oui** - *"le petit-dejeuner"* |
| `dejeuner` | `[dejeuner]` | **oui** - *"le dejeuner"* |
| `gouter` | `[gouter]` | **oui** - *"le gouter"* |
| `ecole-matin` | `[ecole, matin]` | non, au sens contigu - *"l'ecole du matin"* insere `du` |
| `ecole-apres-midi` | `[ecole, apres, midi]` | non, au sens contigu - meme motif |

**Quatre zones sur six, sur le seul paquet servi : la clause est rouge aujourd'hui.** Le chiffre
exact appartient au garde-fou le jour ou il s'execute ; ce que cette lecture etablit, c'est qu'il
**mord sur le materiau reel** et non sur une fixture de ma main - la lecon de s05, appliquee a ma
propre regle.

**Vert sur un texte alternatif honnete, verifie avant d'etre affirme** : *"Frise de la journee
d'ecole, six moments de gauche a droite."* ne nomme aucune des six zones au sens de cet operateur
(`lever`, `dejeuner`, `gouter` absents ; `[ecole, matin]` non contigu). **La clause n'interdit donc
pas de dire ce que l'image est** - elle interdit d'en enumerer le contenu. C'est la phrase de
`nadia-prof`, rendue executable.

### Clause 2 - l'indice ne porte aucun jeton distinctif du libelle de sa bonne zone

**Un jeton distinctif est derive, jamais liste** : c'est un jeton du libelle de la bonne zone
qu'**aucun autre libelle du meme item** ne porte.

Le motif de cette definition est mesure, pas esthetique. Les libelles positionnels partagent leurs
mots-outils : les six libelles de l'item 1 portent tous `le`. Une clause qui comparerait les jetons
bruts declarerait fautif tout indice contenant `le`, c'est-a-dire tous. Une clause qui exigerait la
suite contigue du libelle entier **ne mordrait pas le defaut reel** : *"C'est le tout premier
moment"* ne contient pas `[le, premier]` de facon contigue (`tout` s'interpose). **Les deux
formulations evidentes echouent, chacune par un bout** - et la seule qui reste juste se derive du
jeu des libelles de l'item, sans qu'aucune liste de mots-outils soit ecrite par moi. C'est ce qui
ferme la faute de s05 par le perimetre plutot que par la chance.

**Etat sur le materiau reel, par lecture** :

| Item | Libelle de la bonne zone | Jeton distinctif | Indice servi | Verdict |
|---|---|---|---|---|
| 1 | *"le premier"* | `premier` | *"C'est le tout **premier** moment de la journee."* | **rouge** |
| 8 | *"le premier"* | `premier` | *"C'est le repas du soir."* | vert |
| 10 | *"le deuxieme, en bas"* | `bas` | *"Ce n'est ni avant, ni apres - cherche ce qui se passe au meme moment."* | vert |
| 11 | *"le deuxieme, en haut"* | `haut` | idem | vert |

**La clause ne rougit que la ou `nadia-prof` a mis le doigt**, et elle reste verte sur les trois
autres items qu'elle n'a pas mis en cause. C'est la propriete qu'on demande a une regle : mordre le
defaut et laisser passer le contenu valide.

### Ce que ces deux clauses ne gardent pas, ecrit avant qu'on le reproche

1. **L'identifiant d'une zone est choisi par l'auteur du paquet.** Des identifiants opaques
   (`z1`...`z6`) passeraient la clause 1 tout en rendant le paquet illisible. La regle ferme la
   porte qu'on a vu franchir ; elle ne ferme pas celle-la, et le message d'echec le dit. La
   contrepartie est ailleurs et elle existe : un identifiant opaque saute aux yeux a la relecture
   du `yaml`, un texte alternatif qui enumere ne sautait aux yeux de personne.
2. **La composition des trois textes reste un jugement.** Un texte alternatif qui dirait *"une
   frise du matin au soir"* et un enonce qui dirait *"montre le moment du reveil"* fuiraient
   ensemble sans qu'aucun recouvrement lexical n'existe. C'est la limite que `ZON-2` nomme deja
   pour le garde-fou 5, dans les memes termes : elle reste entierement a la relecture de
   `nadia-prof`, item par item, et **ces clauses n'y pretendent pas**.
3. **La mesure de `nadia-prof` - *"moins de neuf items sur onze se resolvent sans regarder le
   dessin"* - n'est pas encodable et n'est pas encodee.** Elle suppose de jouer. Ces clauses
   retirent deux des trois chemins par lesquels un item se gagne image effacee ; elles ne comptent
   pas les items gagnes.

### Ce que je n'etends pas aujourd'hui, et ce qui me ferait changer d'avis

L'operateur du jeton distinctif est **strictement plus fort** que celui du garde-fou 5, et il
serait vert sur les onze enonces servis (verifie : l'item 1 a pour jeton distinctif `premier`,
absent de *"Montre le lever."* ; l'item 5 a `deuxieme`, absent de *"Montre ce qui vient juste avant
l'ecole du matin."*). **Je ne durcis pourtant pas le garde-fou 5** : aucun defaut n'a ete mesure sur
un enonce, et renforcer une regle sur un usage que j'imagine est exactement ce que `CLAUDE.md`
refuse.

**Le declencheur, ecrit maintenant pour qu'il soit un declencheur et pas une revision** : le premier
enonce qui nomme le libelle de sa bonne zone **autrement que mot pour mot** et que `nadia-prof`
releve. Ce jour-la, le garde-fou 5 adopte cet operateur, en une ligne, et ce paragraphe est son
motif ecrit d'avance.

---

## Regle executable ArchUnit

**Aucune de ces deux clauses n'est une `ArchRule`, et le motif est le meme que pour les six
garde-fous d'`ADR-0080`** : ArchUnit modelise des types Java et leurs relations. Les objets juges
ici sont **des chaines de caracteres d'items charges** - aucun n'est un type. Elles sont donc
tenues par deux garde-fous derives, au sens d'`ADR-0013`, dans le module qui charge le contenu.

### Garde-fou 1 - `AucunTexteAlternatifDeDesignationNeNommeSesZonesTest`

**Fichier** :
`adapters/adapter-content/src/test/java/com/recreo/adapter/content/AucunTexteAlternatifDeDesignationNeNommeSesZonesTest.java`
**Jeu fautif** :
`adapters/adapter-content/src/test/resources/contreexemple/AucunTexteAlternatifDeDesignationNeNommeSesZonesTest/`
(un paquet dont le texte alternatif enumere ses zones, et un paquet sain dont le texte alternatif
dit ce que l'image est - ce second cas **doit rester vert**, sans quoi la regle refuserait tout
texte alternatif).

```java
static List<String> verifierQuAucunTexteAlternatifNeNommeSesZones(String paquetId,
                                                                  List<ZoneADesigner> items) {
    List<String> defauts = new ArrayList<>();
    for (ZoneADesigner item : items) {
        List<String> jetonsDuTexte = AucunTexteAVoixHauteNEnonceUnePropositionDeSonItemTest
                .jetons(item.illustrationTexteAlternatif());
        for (Zone zone : item.zones()) {
            List<String> jetonsDeLIdentifiant =
                    AucunTexteAVoixHauteNEnonceUnePropositionDeSonItemTest.jetons(zone.id());
            if (AucunTexteAVoixHauteNEnonceUnePropositionDeSonItemTest
                    .contientCommeJetonsContigus(jetonsDeLIdentifiant, jetonsDuTexte)) {
                defauts.add("paquet " + paquetId + ", item " + item.id().value()
                        + " : le texte alternatif nomme la zone \"" + zone.id() + "\". Un texte"
                        + " alternatif dit ce que l'image EST, pas ce qu'elle contient : enumerer"
                        + " les zones rend l'item jouable image effacee (ADR-0091 clause 1,"
                        + " FRI-R1)");
            }
        }
    }
    return defauts;
}
```

**Perimetre derive** (`ADR-0013` obligation 1) : les items viennent de la chaine reelle
(`YamlContentPackLoader`), les paquets de type `designation` de
`src/main/resources`, jamais d'une liste de noms. **Derivation totale** (obligation 2) : chaque item
de designation est examine, chaque zone de chaque item est comparee, aucune exemption.
**Non-vacuite** (`ADR-0051`) : `assertThat(paquets).isNotEmpty()`, comme ses trois voisins.

**Ce qu'elle interdit reellement** : la contrefacon par texte alternatif, c'est-a-dire la seule
forme par laquelle un item de designation se gagne **sans regarder le dessin et sans que l'enonce ni
l'indice ne fautent**. Ce n'est pas une precaution : c'est la transposition d'un defaut mesure sur
onze items, qui a coute un refus bloquant et un sprint.

### Garde-fou 2 - `AucunIndiceDeDesignationNeNommeLeLibelleDeSaBonneZoneTest`

**Fichier** :
`adapters/adapter-content/src/test/java/com/recreo/adapter/content/AucunIndiceDeDesignationNeNommeLeLibelleDeSaBonneZoneTest.java`
**Jeu fautif** : meme forme, dans son propre repertoire de contre-exemple, **avec un cas vert
obligatoire** - un indice qui porte le jeton distinctif d'une **autre** zone que la bonne doit
rester vert, sans quoi la regle interdirait les indices relationnels.

```java
static List<String> verifierQuAucunIndiceNeNommeLeLibelleDeSaBonneZone(String paquetId,
                                                                       List<ZoneADesigner> items) {
    List<String> defauts = new ArrayList<>();
    for (ZoneADesigner item : items) {
        if (item.indice().isEmpty()) {
            continue;
        }
        Zone bonneZone = item.zones().stream()
                .filter(zone -> zone.id().equals(item.bonneZone()))
                .findFirst()
                .orElseThrow();
        Set<String> jetonsDesAutresLibelles = item.zones().stream()
                .filter(zone -> !zone.id().equals(item.bonneZone()))
                .flatMap(zone -> AucunTexteAVoixHauteNEnonceUnePropositionDeSonItemTest
                        .jetons(zone.libelle()).stream())
                .collect(Collectors.toSet());
        List<String> jetonsDeLIndice = AucunTexteAVoixHauteNEnonceUnePropositionDeSonItemTest
                .jetons(item.indice().orElseThrow());
        for (String jeton : AucunTexteAVoixHauteNEnonceUnePropositionDeSonItemTest
                .jetons(bonneZone.libelle())) {
            if (!jetonsDesAutresLibelles.contains(jeton) && jetonsDeLIndice.contains(jeton)) {
                defauts.add("paquet " + paquetId + ", item " + item.id().value()
                        + " : l'indice porte \"" + jeton + "\", jeton distinctif du libelle de sa"
                        + " bonne zone (\"" + bonneZone.libelle() + "\"). L'indice designe alors le"
                        + " bouton a presser au lieu d'aider a lire le dessin (ADR-0091 clause 2,"
                        + " FRI-R1)");
            }
        }
    }
    return defauts;
}
```

**Le jeu des autres libelles est la population de reference, et il est derive de l'item lui-meme** :
aucune liste de mots-outils n'est ecrite dans ce depot par ma main. **Le cas degenere, nomme plutot
que laisse croire couvert** : un item dont toutes les zones portent le meme libelle n'a aucun jeton
distinctif, et cette regle est alors muette sur lui. Un tel item est injouable pour une autre raison
- deux boutons que l'enfant ne peut pas distinguer -, et **aucun garde-fou du depot ne l'interdit a
ce jour**. Ce n'est pas a cette clause de le faire, et je ne la fais pas passer pour plus qu'elle
n'est.

**Mise a l'epreuve** (`ADR-0013` obligation 3) : le jeu fautif commite **et** la mutation du
materiau reel - l'indice de l'item 1 de `cp-frise-de-la-journee` remis dans son etat du 2026-09-11
doit faire rougir le garde-fou, et cette morsure-la se fait sur du materiau reel, pas sur une
fixture.

### Due avec le lot de `S35-QLM14-FRISE`, et pas avant

Les deux garde-fous ont **une population non vide des aujourd'hui** (un paquet de designation servi,
onze items) : `ADR-0051` ne s'oppose pas a leur ecriture immediate. Ce qui s'y oppose est plus
simple : **la clause 1 est rouge sur le contenu servi**, et l'ecrire hors du lot qui corrige le
contenu mettrait la CI au rouge pour tous les autres lots de s35. Ils sont donc ecrits **dans le
meme diff** que les deux textes alternatifs corriges et l'indice de l'item 1 - ce qui est aussi la
regle de `CLAUDE.md` : les tests sont ecrits dans le meme commit que le code.

---

## Consequences

**Bonnes** :

- La moitie opposable de `FRI-R1` cesse de dependre d'un audit. Un texte alternatif qui enumere ses
  zones devient rouge **le jour ou il est ecrit**, sur tout paquet de designation, present et futur.
- Le troisieme terme d'un item de designation entre enfin dans un controle, apres l'enonce
  (garde-fou 5) et l'indice (clause 2). Les trois textes sont couverts, et aucun ne l'est a moitie.
- Le cout pour un auteur est nul quand il ecrit un texte alternatif honnete - verifie sur une
  formulation de remplacement avant d'etre affirme.

**Mauvaises, et elles sont reelles** :

- **Un texte alternatif honnete est moins informatif pour un enfant qui ne voit pas l'image.**
  `ADR-0080` section 7.2 avait deja tranche que la designation n'est **pas** jouable sans voir
  l'image et qu'aucun libelle ne repare cela ; cette clause rend cet arbitrage visible dans le
  contenu. Ce n'est pas un effet de bord de la regle, c'est une propriete de la mecanique, et elle
  etait deja decidee.
- **Deux garde-fous de plus a maintenir**, et deux jeux fautifs. Le compte des garde-fous de contenu
  de type designation passe de 3 a 5.
- **La clause 1 depend d'un choix d'auteur** (l'identifiant de zone) : elle est contournable par un
  identifiant opaque. Nomme, non garde, rendu a la relecture.

---

## Alternatives considerees

**a) Comparer le texte alternatif a l'enonce plutot qu'aux identifiants de zone.** C'est l'ancre la
plus directe - l'enonce nomme ce que l'enfant cherche - et **elle est contournable par personne**,
contrairement a l'identifiant. **Ecartee sur mesure, pas sur principe** : sur un texte alternatif de
remplacement legitime (*"Frise de la journee de l'ecole, six moments"*), la suite de jetons
`[l, ecole]` de l'enonce *"Montre ce qui vient juste avant l'ecole du matin."* s'y retrouve
contigue. La regle rougirait un texte alternatif honnete, c'est-a-dire produirait un faux rouge sur
une formulation legitime - ce qu'`ADR-0086` a mesure comme plus couteux que le defaut qu'il
attrape. L'ancre par identifiant n'a pas ce defaut.

**b) Interdire les libelles positionnels et rendre les libelles semantiques.** La table de
correspondance tomberait d'un coup : un libelle *"le lever"* ne se deduit d'aucun rang.
**Ecartee parce que ce n'est pas ma decision** : `ZON-10` est une condition de `nadia-prof`, et elle
a explicitement ecrit dans le meme verdict que *"ce ne sont pas les libelles qui fautent seuls"* et
que `ZON-10` n'est pas amendee. Un `ADR` qui renverserait une condition pedagogique au motif qu'elle
simplifierait une regle serait un Done pedagogique prononce par un architecte.

**c) Compter, comme `nadia-prof`, les items gagnables image effacee, et poser un plafond.**
**Ecartee** : cela demande de jouer et de decider ce que *"gagnable"* veut dire - un jugement
pedagogique. Le plafond serait un nombre choisi par moi sur un seul paquet de onze items ; c'est un
chiffre sans methode deguise en garde-fou.

**d) Une seule regle au lieu de deux, qui jugerait *"les trois textes ensemble"*.** **Ecartee** :
les deux clauses ont des ancres differentes (identifiants de zone contre libelle de la bonne zone) et
des populations differentes (tous les items contre les items a indice). Les fondre donnerait un
message d'echec qui ne dit pas lequel des deux defauts a ete trouve - et un garde-fou dont le message
ne nomme pas la faute fait perdre a celui qui le lit le temps qu'il pretend gagner.
