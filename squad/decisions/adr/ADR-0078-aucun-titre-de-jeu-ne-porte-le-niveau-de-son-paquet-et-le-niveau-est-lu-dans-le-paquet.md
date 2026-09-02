# ADR-0078 - Aucun titre de jeu ne porte le niveau de son paquet, et le niveau est lu dans le paquet plutot qu'ecrit dans la regle

- **Statut** : Accepte. **Sa regle est ecrite, verte et mise a l'epreuve dans le meme commit** -
  `adapters/adapter-content/src/test/java/com/recreo/adapter/content/AucunTitreDeJeuNePorteSonNiveauTest.java`,
  **7 tests** : une clause sur les quarante entrees du catalogue servi, le cliquet de la dette,
  quatre morsures dont trois mutent le catalogue reel en memoire, et une morsure sur le jeu fautif
  commite.
- **Sprint** : s27 (planning). **Date** : 2026-09-02. **Auteur** : `theo-archi`.
- **Arbre mesure** : worktree `s27-theo`, branche partie de `round-two` a `9294632`. **JDK
  21.0.10**, celui que la machine virtuelle des tests rapporte d'elle-meme (`ADR-0071`). Enveloppe :
  `bash outils/mvn-jdk21.sh`.
- **Encode une decision pedagogique deja prise ; il n'en propose pas une variante.** Le titre retenu
  est *"Les petites additions"*, arbitre par `nadia-prof` le 2026-09-02 apres releve des quarante
  titres servis. **Je ne choisis aucun titre et je n'en jugerai jamais un.** **Ce commit ne
  l'applique pas** : une seconde clause pedagogique s'y oppose, section *Ce qui bloque la
  correction*.
- **Prolonge** : `ADR-0013` (perimetre derive, garde-fou mis a l'epreuve), `ADR-0045` (ce que le
  catalogue peut se dire a lui-meme), `ADR-0051` (un garde-fou qui n'examine rien le declare).
- **Recommande par `camille-po`**, qui a trouve le defaut apres un retour de l'utilisatrice.

---

## Contexte

### Le fait

L'utilisatrice a joue le produit et releve que *"Calcul CP"* nomme une classe. `camille-po` a
compte : **c'est le seul des quarante titres du catalogue a le faire**, et le defaut a traverse
**26 sprints** sans qu'aucun test ni aucun agent le voie - il a ete trouve devant l'ecran.

`nadia-prof` l'a tranche le 2026-09-02, et elle trouve **deux** defauts la ou un seul avait ete
nomme : le niveau, *"un enfant n'a pas besoin qu'on lui rappelle sa classe pour jouer"*, et la
sous-specification - *calcul* couvre l'addition **et** la soustraction, alors que le jeu ne fait que
des additions et que le jeu frere s'appelle *"Les petites soustractions"*. **Le second defaut n'est
pas encodable et ne le sera pas** : juger qu'un titre promet plus large que son contenu demande de
lire le contenu et de juger. C'est le premier seul qui devient une regle.

### La question posee, et elle est la bonne

Une regle generale pour **un** cas est-elle de la sur-ingenierie ? Ce serait vrai si la population
etait figee. Elle ne l'est pas : **le catalogue grandit a chaque sprint**, rien dans le schema d'une
entree ne contraint `titre`, et le seul filet contre la meme faute au prochain jeu est qu'un
relecteur y pense. Ce qui est garde n'est pas la correction d'aujourd'hui - elle est faite en une
ligne - c'est **l'ecriture d'une entree neuve**. Le cout est de sept tests et d'aucune abstraction
de production.

---

## Decision

1. **Aucun titre d'entree de catalogue ne contient le niveau declare par le paquet qu'elle designe.**
2. **Le niveau n'est pas ecrit dans la regle.** Il est lu dans `descripteur.niveau` du paquet, et les
   paquets sont indexes par l'identifiant qu'ils **declarent**, jamais par leur nom de fichier. Un
   niveau neuf est garde le jour ou il entre, sans qu'une ligne du test bouge.
3. **Un paquet qui ne declare aucun niveau est rouge.** C'est le seul mode de defaillance qui
   rendrait la regle verte sur les quarante entrees a la fois - une cle de descripteur renommee - et
   un garde-fou qui ne trouve plus ce qu'il compare le dit au lieu de passer.
4. **Le titre du jeu d'additions n'est pas corrige dans ce commit**, et la regle entre avec une
   **exemption nommee, plafonnee a exactement une entree**. Le motif est en section suivante : la
   correction arbitree est bloquee par une seconde clause pedagogique, et resoudre cette
   contradiction n'est pas a moi.
5. **L'exemption ne peut ni grandir, ni pourrir.** Le plafond vaut exactement sa taille, et chaque
   nom exempte doit designer une entree qui existe **et qui est encore fautive** : le jour ou le
   titre est corrige, le garde-fou rougit et force la sortie de l'exemption dans le meme commit.

---

## Ce qui bloque la correction, et pourquoi je m'arrete la

La correction arbitree - *"Les petites additions"* - **a ete appliquee, mesuree, puis retiree**. Elle
rend `CatalogueChargeableIT` rouge :

```
[S1-8 : le libelle d'accueil ne reintroduit pas le grapheme 'ti' prononce /si/ de l'ancien titre
'Additions CP']
Expecting actual: "Les petites additions" not to contain: "ti"
```

`S1-8` est une clause de `nadia-prof` de **s01**, dont le motif est ecrit dans
`squad/conformite/FICHE-bandit-manchot-additions-cp.md` : *"'Additions CP', libelle du bouton
d'accueil, contient le grapheme 'ti' prononce /si/, qui n'est pas une acquisition de CP
(reconnaissance globale seulement)"*. **Le mot *additions* porte ce grapheme.** Le titre arbitre le
2026-09-02 contredit donc une clause de la meme autrice ecrite vingt-six sprints plus tot.

**Trois faits mesures, poses sans conclusion parce que la conclusion n'est pas a moi.**

1. **L'assertion de `S1-8` est plus large que la clause qu'elle cite.** La clause vise le grapheme
   *"ti"* **prononce /si/** ; l'assertion refuse la sous-chaine *"ti"*, quelle que soit sa
   prononciation. Dans *"Les petites additions"*, elle rougit **deux fois** : sur *additions*, ou le
   grapheme est bien /si/, et sur *petites*, ou il se prononce /ti/ et ou la clause n'a rien a dire.
2. **Sa population est un identifiant de jeu ecrit en dur**, et deux titres servis et valides
   portent le meme grapheme /si/ sans qu'aucune assertion ne les voie : **"Les petites
   soustractions"** et **"Les tables de multiplication"**. La clause vaut donc, telle qu'encodee, sur
   un jeu et pas sur ses voisins.
3. C'est la **troisieme occurrence** en un jour de la famille de defaut qu'`ADR-0077` traite : un
   garde-fou plus large que sa clause sur la population qu'il nomme, et absent sur le reste de la
   population que la clause couvre.

**Ce que je ne fais pas** : retrecir `S1-8`. Contrairement a `FIG-3` et `FIG-4`, ou `nadia-prof` a
elle-meme relu sa clause et dit ce qu'un garde-fou devait executer, **personne n'a rouvert `S1-8`**.
Le retrecir sur ma lecture reviendrait a decider a sa place si *"petites"* est acceptable et si
*"soustractions"* aurait du l'etre - c'est-a-dire a trancher du pedagogique. **Route a `sofia-sm`
pour `nadia-prof`.** Trois issues possibles, et aucune n'est la mienne : le titre change, `S1-8` est
relue et retrecie, ou `S1-8` est reconduite telle quelle et l'arbitrage du titre est repris.

**Ce que ce commit fait a la place** : il installe la regle avec une exemption d'exactement une
entree, de sorte que **toute entree neuve est gardee des aujourd'hui**, et que le jour ou le titre
est corrige, l'exemption devenue fausse fait rougir le depot au lieu de survivre a sa cause.

---

## Regle executable

**Ce n'est pas une `ArchRule`** : la propriete porte sur des ressources servies, pas sur des types.
C'est le regime de garde-fou de balayage d'`ADR-0013`.

```java
if (entree.niveau() == null || entree.niveau().isBlank()) {
    fautes.add(entree.id() + " : le paquet que cette entree designe ne declare aucun"
            + " niveau. La regle n'a alors rien a comparer au titre, et un garde-fou qui"
            + " ne trouve plus ce qu'il compare le dit au lieu de passer (ADR-0051)");
    continue;
}
if (entree.titre().toLowerCase(Locale.ROOT)
        .contains(entree.niveau().toLowerCase(Locale.ROOT))) {
    fautes.add(entree.id() + " : le titre \"" + entree.titre() + "\" porte le niveau \""
            + entree.niveau() + "\" que son paquet declare. [...]");
}
```

| Morsure | Ce qu'elle mute | Verdict |
|---|---|---|
| `unTitreAuquelOnAccoleSonNiveauFaitEchouerLeGardeFou` | le geste exact qui a produit *"Calcul CP"*, sur la premiere entree reelle | rouge |
| `unNiveauEnMinusculesDansUnTitreFaitEchouerLeGardeFou` | la meme faute en minuscules | rouge |
| `unPaquetQuiNeDeclarePlusSonNiveauFaitEchouerLeGardeFou` | le niveau disparait du paquet | rouge |
| `unCatalogueSansAucuneEntreeFaitEchouerLeGardeFou` | population vide | rouge, `ADR-0051` |
| `leJeuFautifCommiteEstRefuseSurSaSeuleEntreeFautive` | le jeu fautif commite, **deux entrees du meme niveau et une seule faute** | rouge sur la fautive, et le message **ne nomme pas** la saine |
| `laDetteDeTitreNeGrandditPasEtChaqueNomExempteDesigneEncoreUneEntreeFautive` | le cliquet de l'exemption | vert tant que la dette vaut 1 et que le nom exempte designe une entree **encore fautive** |

---

## Consequences

- **Ce que la regle interdit reellement** : accoler une classe a un titre de jeu, au moment ou une
  entree neuve est ecrite. C'est le geste qui s'est produit, et le seul que le materiau offre.
- **Ce qu'elle ne voit pas, ecrit plutot que laisse trouver** : un titre qui ecrirait le niveau
  autrement que le descripteur - *"cours preparatoire"*, *"niveau 1"* - passerait. L'attraper
  demanderait un vocabulaire ecrit a la main, c'est-a-dire un critere enumere sous une promesse
  universelle, ce qu'`ADR-0039` fait declarer. Je prefere une regle etroite et honnete a une regle
  large qui aurait besoin d'un aveu.
- **Elle ne juge aucun titre.** Registre, longueur, vocabulaire, paire que deux titres forment pour
  un enfant qui parcourt une liste : `nadia-prof` et `camille-po`, jamais ce fichier.
- **Elle ne se substitue pas au second defaut trouve par `nadia-prof`** - un titre qui promet plus
  large que son contenu - qui reste hors de portee d'un automate et le restera.
- **Elle voisine une exemption enumeree sans la contredire** :
  `TexteAccentueExposeParLePortEntrantTest` ecrit *"CP"* et *"CM1"* en clair pour les dispenser de
  son inventaire d'accents, et le declare comme critere enumere. Ce fichier-ci ne les ecrit pas ;
  les deux garde-fous parlent des memes chaines par deux chemins differents, et c'est voulu.
- **Ce qui me ferait changer d'avis** : si un niveau au nom plus long entrait et qu'un titre
  legitime le contenait par accident, la comparaison passerait a une frontiere de mot. Je ne l'ecris
  pas aujourd'hui : aucun titre du catalogue ne s'en approche, et ce serait du code pour un cas
  imagine.

---

## Alternatives considerees

**Attendre l'arbitrage de `S1-8` et n'ecrire la regle qu'ensuite.** Ecartee : la regle est prete et
verte, et l'attente laisserait les entrees neuves du sprint sans garde pour une raison qui ne les
concerne pas. L'exemption nommee coute une ligne et un cliquet ; elle rend la regle utile
immediatement sans rien absoudre.

**Ne rien encoder et compter sur la relecture.** Ecartee : c'est exactement le regime qui a laisse
passer le defaut pendant 26 sprints. Une regle non executable n'est pas une regle.

**Ecrire les niveaux connus dans le test** (`CP`, `CM1`). Ecartee : une liste ecrite a la main est
verte par defaut sur tout niveau qu'elle ne nomme pas, et le jour ou un CE1 entre - une decision de
`camille-po` et de `nadia-prof` - la regle serait muette sans que rien ne le dise. Le descripteur du
paquet dit deja le niveau ; il n'y a aucune raison de le recopier.

**Deriver le niveau du suffixe de l'identifiant de jeu** (`...-cp`, `...-cm1`). Ecartee : c'est une
convention de nommage que rien ne tient, et un jeu nomme autrement sortirait de la regle en silence.

**Etendre la regle a la sous-specification d'un titre.** Ecartee : elle demande de lire le contenu et
de juger. Ce serait une regle qui pretend garder ce qu'elle ne peut pas voir, la famille de defaut
que ce depot sanctionne.
