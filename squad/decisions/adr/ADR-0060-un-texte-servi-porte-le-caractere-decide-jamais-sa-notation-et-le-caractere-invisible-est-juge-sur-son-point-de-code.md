# ADR-0060 - Un texte servi porte le caractere decide, jamais sa notation, et un caractere invisible se juge sur son point de code

- **Statut** : Accepte
- **Sprint** : s19
- **Date** : 2026-08-24
- **Auteur** : theo-archi
- **Arbre juge** : `round-two` a `51301d7`, worktree dedie `s19-theo-3`, lignee verifiee
  (`git merge-base --is-ancestor`)
- **Mesures** : toolchain JDK 21.0.10, `snakeyaml` 2.6 (version lue dans `BOOT-INF/lib/` du jar
  construit par `mvn -B clean verify`, jamais recopiee d'un pom)

## Contexte

`marc-dev` a ecrit a la main, via un heredoc, la notation d'un espace insecable - la sequence
d'echappement de six caracteres ASCII - la ou il voulait le caractere. Rien ne l'a signale : le YAML
reste valide, la compilation passe, les tests passent, le paquet se charge. Il l'a trouve en
comparant octet par octet contre `cm1-synonymes.yaml`, `lea-dev` a verifie les siens par point de
code, `sofia-sm` a confirme apres coup que les deux paquets livres sont propres. **Le fait qui
compte : rien dans ce depot ne l'aurait vu si les developpeuses ne l'avaient pas cherche.**

### Ce que la mesure etablit, et qui n'etait su de personne

Avant de decider quoi que ce soit, j'ai mesure ce que le parseur fait reellement de cette sequence,
plutot que de le supposer. Methode, dans le meme paragraphe que le resultat : un fichier YAML ecrit
octet par octet portant la meme sequence dans quatre styles de scalaire, charge par `snakeyaml`
2.6 - la version que le livrable embarque - sur le JDK 21.0.10 de la toolchain, chaque valeur rendue
en points de code. Le programme de sonde n'est pas commite : il ne juge aucun artefact du depot, il
etablit un fait sur une bibliotheque, et sa methode est ecrite ici pour etre rejouable.

| Style du scalaire | Ce que le parseur rend | Ce que l'enfant lit |
|---|---|---|
| guillemets doubles (`"..."`) | `U+00A0` | le bon caractere |
| apostrophes (`'...'`) | `U+005C U+0075 U+0030 U+0030 U+0061 U+0030` | la notation, six caracteres |
| nu (sans marque) | idem | la notation, six caracteres |
| sequence inconnue en guillemets doubles | **echec du chargement** | rien, le build est rouge |

**Consequence pour le cas de s19, et je l'ecris parce qu'elle contredit ce que la cloture affirmait
: les dix-sept items de `cm1-antonymes.yaml` sont ecrits en guillemets doubles, comme les seize de
`cp-nature-mot.yaml` et comme tous les paquets servis. Dans ce style, la sequence est resolue par le
parseur, et le texte servi a l'enfant aurait ete juste.** L'etat fautif n'a jamais ete commite - un
seul commit touche chacun de ces deux fichiers (`087b078`, `99d0a75`) - je ne peux donc pas juger le
fichier intermediaire ; ce que j'affirme porte sur le style, qui est le meme dans le fichier livre et
dans tout paquet servi. La correction n'etait pas inutile pour autant : ecrire la notation dans une
donnee est une faute qui survit a un changement de style et a un changement de parseur. Mais le
defaut **silencieux** n'etait pas la ou on l'a cherche.

### Ou il est reellement

Il est dans le membre invisible de la meme famille. `SYN-9` (`nadia-prof`, s17, condition
**bloquante**) puis `ANT-5` (arbitrage prealable de s19) exigent que **les espaces a l'interieur des
guillemets francais soient insecables**. Un espace ordinaire y est indistinguable a l'oeil, dans le
fichier comme a l'ecran : il ne se manifeste que le jour ou le navigateur coupe la ligne entre le
guillemet et le mot marque. Cette condition pedagogique a ete posee deux fois, elle s'applique
aujourd'hui a trois paquets servis - `cm1-synonymes`, `cm1-antonymes`, `cp-nature-mot` - et **aucun
automate ne la verifiait**. C'est la definition meme de ce que ce depot sanctionne : une exigence
tenue par la vigilance.

La notation non resolue est le membre **bruyant** de la famille (un adulte qui joue la voit a
l'ecran) ; l'espace secable en est le membre **silencieux** (personne ne la voit, jamais). Les deux
ont la meme cause - le texte servi n'est pas fait des caracteres decides - et la meme cecite :
`TexteAccentueExposeParLePortEntrantTest` compare des chaines litterales ecrites a la main et ne
couvre que le catalogue et les descripteurs, jamais le texte d'un item.

## Decision

**Juger le texte servi a l'enfant sur ses points de code, item par item, par un garde-fou qui derive
son perimetre du repertoire de contenu et qui tire ses items par la chaine reelle.** Deux clauses :

1. **Aucun texte servi ne porte la notation d'un caractere a la place du caractere.** Trois formes
   refusees, chacune enoncee comme une grammaire entiere et non comme le nom d'un incident clos : la
   **contre-barre** (toute contre-barre, quelle que soit la lettre qui la suit), l'**entite de
   caractere HTML** (nommee ou numerique), la **notation de point de code** (`U+` suivi de son
   hexadecimal).
2. **A l'interieur d'un guillemet francais, l'espace est insecable.** Un guillemet ouvrant est suivi
   de `U+00A0`, un guillemet fermant en est precede. C'est `SYN-9` et `ANT-5`, encodees telles
   quelles - je n'y decide rien de pedagogique, `nadia-prof` a decide deux fois.

**L'objet juge est le texte servi, jamais les octets du fichier.** C'est un choix, et il a deux
motifs verifiables. D'abord une notation resolue par le parseur ne parvient jamais a l'enfant : la
signaler serait un faux rouge. Ensuite les octets du fichier portent des commentaires - la ligne 22
de `cm1-synonymes.yaml` en est un, et elle contient un guillemet francais entoure d'espaces
ordinaires - qu'aucun enfant ne lira jamais : les juger serait un second faux rouge, sur le paquet
qui a justement servi de reference a la comparaison octet par octet de `marc-dev`.

**Perimetre : le texte des items servis.** Le titre, la consigne et le descripteur en sont exclus
pour un motif verifiable et non par commodite : `TexteAccentueExposeParLePortEntrantTest` les
inventorie chaine par chaine et les compare **par egalite** (`ADR-0039`), si bien qu'une notation y
serait deja rouge aujourd'hui. Le texte d'un item n'est inventorie nulle part, et c'est exactement la
que le defaut est ne.

## Regle executable

**Fichier** :
`adapters/adapter-content/src/test/java/com/recreo/adapter/content/LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest.java`.

Ce n'est pas une regle ArchUnit, et ce n'en est pas une par defaut de rigueur : ArchUnit juge des
types Java, et l'objet ici est une chaine de caracteres produite par le chargement d'une ressource.
Le precedent existe et est deja acte - `AucunVocabulaireDeMatiereTest` lit des sources parce
qu'ArchUnit ne voit pas les litteraux, et les garde-fous de balayage d'`adapter-content` lisent des
paquets. Le mecanisme automatique est un test du module qui possede l'artefact, execute par
`mvn verify` comme n'importe quel autre.

> **Clause 1.** Aucun texte servi par un item d'un paquet du repertoire de contenu ne contient de
> contre-barre, d'entite de caractere HTML, ni de notation de point de code.
>
> **Clause 2.** Dans tout texte servi, un guillemet francais ouvrant est immediatement suivi de
> `U+00A0` et un guillemet francais fermant est immediatement precede de `U+00A0`.
>
> **Clause 3 (`ADR-0051`).** Si aucun texte servi ne porte de guillemet francais, la clause 2
> n'examine aucun objet : elle le declare par un rouge plutot que de passer verte.

```java
private static final Pattern CONTRE_BARRE = Pattern.compile("\\\\");
private static final Pattern ENTITE_HTML =
        Pattern.compile("&(?:#[0-9]{1,7}|#[xX][0-9A-Fa-f]{1,6}|[A-Za-z][A-Za-z0-9]{1,31});");
private static final Pattern POINT_DE_CODE = Pattern.compile("[uU]\\+[0-9A-Fa-f]{4,6}");

static void verifierQueChaqueTexteServiPorteSesCaracteres(Path racine, String racineClasspath) {
    List<TexteServi> textes = textesServis(racine, racineClasspath);
    assertThat(textes)
            .as("ce garde-fou ne prouve rien s'il ne trouve aucun texte servi sous %s", racine)
            .isNotEmpty();
    // clause 1 : toute occurrence d'une des trois notations, avec les points de code du texte servi
    // clause 2 : parcours des caracteres, voisin immediat de chaque guillemet francais
    // clause 3 : guillemetsExamines doit etre strictement positif
}
```

Le corps complet, ses messages et sa Javadoc sont dans le fichier ; ils ne sont pas recopies ici pour
qu'ils ne puissent pas y pourrir a moitie.

### Pourquoi la contre-barre entiere, et pas la liste des sequences

Un critere bati sur les lettres qui suivent la contre-barre rejouerait la faute etablie en s11 avec
`yanis-qa` : un critere enumere construit sur des noms d'incidents clos ne garde rien. Une sequence
inconnue est aussi fautive qu'une sequence connue. Le criterium est donc la contre-barre elle-meme.
Cout accepte : aucun contenu ne pourra porter de contre-barre sans amender cet ADR. Aucun n'en porte
aujourd'hui - balayage de `adapters/adapter-content/src/main/resources/contenu/*.yaml`, 17 fichiers,
zero occurrence.

### La morsure

Quatre jeux fautifs commites sous
`adapters/adapter-content/src/test/resources/contreexemple/LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest/`,
aucun inscrit au catalogue, aucun servi (`ADR-0013`, obligation 3) :

- `notation/` - l'indice d'un item est ecrit dans un scalaire **a apostrophes**, le seul style ou la
  sequence survit au parseur, donc la forme exacte du defaut sous sa seule variante dangereuse. Le
  paquet porte par ailleurs un item conforme a la clause 2, pour que ce rouge ne puisse pas etre
  confondu avec celui de la clause 3 ;
- `espace-secable/` - les guillemets francais y encadrent des espaces ordinaires ;
- `sans-guillemet/` - les deux premieres clauses sont vertes et la clause 3 mord ;
- `corrige/` - les memes paquets corriges, verts, pour que la morsure prouve le critere et non le
  chargement.

**Morsure prouvee aussi sur du materiau reel**, parce qu'une fixture ecrite par l'auteur d'une regle
ressemble a ce qu'il imagine (constat de s05) : mutation d'un seul octet dans
`cm1-antonymes.yaml` - un `U+00A0` apres un guillemet ouvrant remplace par `U+0020` - puis
`bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test -Dtest=LeTexteServiPorte...` ;
**1 echec sur 5**, sur `chaqueTexteServiEnProductionPorteSesCaracteres`, message de la clause 2.
Mutation annulee, `git status` propre sur le fichier avant commit.

## Consequences

- **Le prochain paquet a guillemets francais dont un espace serait secable est rouge en CI**, au lieu
  d'atteindre l'enfant. C'est le seul defaut de cette famille qu'aucune porte, y compris la porte
  pedagogique, n'aurait attrape.
- **Une notation ecrite dans un scalaire a apostrophes ou nu est rouge**, quelle que soit la
  sequence. Une notation ecrite dans un scalaire a guillemets doubles reste **verte**, parce que le
  parseur la resout et que l'enfant lit le bon caractere : ce garde-fou ne remplace pas la relecture
  du fichier, il garde le texte servi.
- **Mauvaise consequence assumee 1** : la clause 1 interdit la contre-barre a tout contenu futur. Un
  contenu de mathematiques ou d'informatique qui en aurait besoin devra amender cet ADR. Je prefere
  ce cout a un critere enumere.
- **Mauvaise consequence assumee 2** : la clause 2 ne dit rien des autres espaces insecables de la
  typographie francaise - avant un point d'interrogation, un deux-points, un point-virgule. Le depot
  est aujourd'hui **incoherent** sur ce point : `cm1-antonymes` et `cp-nature-mot` en portent, les
  onze autres paquets a enonces interrogatifs n'en portent aucun (comptage des occurrences de
  `U+00A0` par fichier, 2026-08-24 : 34, 32 et 32 pour les trois paquets a guillemets, 89 pour
  `cm1-grands-nombres` qui s'en sert comme separateur de milliers, 0 pour les treize autres).
  **Je ne tranche pas cette incoherence : c'est une decision de typographie destinee a un enfant,
  donc de `nadia-prof` et de `camille-po`, jamais la mienne.** Je l'ecris pour qu'elle soit routee
  plutot que decouverte.
- **Mauvaise consequence assumee 3** : le garde-fou tire 60 items par paquet avec une graine fixe,
  comme ses onze voisins. Un paquet combinatoire dont un item fautif ne sortirait jamais dans ces 60
  tirages passerait vert. Aucun paquet a guillemets n'est combinatoire aujourd'hui.
- **Le compte de garde-fous de balayage d'`adapter-content` passe de 11 a 12.** Methode, dans le meme
  paragraphe : `grep -l 'src/main/' *.java` sous
  `adapters/adapter-content/src/test/java/com/recreo/adapter/content`, c'est-a-dire exactement le
  critere par lequel `LesGardeFousSontMisALEpreuveTest` reconnait un garde-fou de balayage ; 12
  fichiers apres ce lot, dont `ProvenanceDesClassesAmontEstLeDepotTest` qui existe a l'identique dans
  les quatre modules. Le compte de regles ArchUnit reste a **30** : cet ADR n'en ajoute aucune.

## Ce qui me ferait changer d'avis

- Un contenu qui a besoin d'une contre-barre : la clause 1 devient une liste de sequences
  d'echappement plutot que la contre-barre entiere, et le cout de l'enumeration est alors assume par
  ecrit.
- Une decision de `nadia-prof` etendant l'espace insecable aux autres signes doubles : la clause 2
  s'etend dans le meme geste, et sa non-vacuite se compte alors sur chaque signe.
- Un second parseur, ou un `snakeyaml` majeur : la table de la section Contexte est remesuree, pas
  recopiee.

## Alternatives considerees

1. **Juger les octets du fichier plutot que le texte servi.** Ecartee : deux faux rouges demontres
   (le commentaire de `cm1-synonymes.yaml`, et toute notation resolue par le parseur). Un garde-fou
   qui rougit sur un objet que l'enfant ne lit pas apprend a ses lecteurs a le contourner.
2. **Detecter la seule sequence de l'incident.** Ecartee : c'est la faute de s11, un critere bati sur
   le nom d'un incident clos. Elle n'aurait rien attrape du membre silencieux de la famille, qui est
   le seul defaut reellement invisible.
3. **Interdire la sequence a la source, par un garde-fou sur les fichiers YAML avant chargement.**
   Ecartee pour le meme motif que 1, et parce qu'elle deplace le probleme : le style de scalaire
   deciderait de la gravite, et un garde-fou dont le verdict depend d'une subtilite de syntaxe est un
   garde-fou qu'on apprend a ne plus lire.
4. **Etendre `TexteAccentueExposeParLePortEntrantTest` au texte des items.** Ecartee : ce test compare
   par egalite a un inventaire ecrit a la main. Y faire entrer les enonces, propositions et indices
   de dix-sept paquets recopierait plusieurs milliers de caracteres de contenu dans un fichier de
   test, ou ils pourriraient a chaque arbitrage de `nadia-prof`. Une propriete se derive, un
   inventaire se recopie.
5. **Ne rien ecrire, au motif que le defaut de s19 n'a jamais atteint l'enfant.** Ecartee, et c'est
   l'alternative qui meritait le plus d'etre examinee puisque la mesure de la section Contexte la
   rend defendable. Elle tombe sur un fait independant : `SYN-9` est une condition bloquante posee
   par la seule autorite pedagogique du produit, appliquee a trois paquets, et tenue par rien d'autre
   que la vigilance de trois personnes. La famille du garde-fou annonce mais absent est la septieme
   dette listee dans `CLAUDE.md`, et ce depot l'a sanctionnee a plusieurs reprises depuis - je ne
   cite pas de compte, n'en ayant pas etabli la methode.
