# ADR-0051 - Un garde-fou qui n'examine aucun de ses objets le declare ; sur du texte, l'ordre des propositions n'est pas gardable sans arbitrage pedagogique

- **Statut** : Accepte, sa regle ecrite dans le meme commit
  (`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`, une clause, quatre morsures).
- **Sprint** : s17. **Date** : 2026-08-19. **Auteur** : theo-archi.
- **Route par** : `yanis-qa`, rapport qualite de s17, point 1, sur un constat de `lea-dev`. Le
  garde-fou en cause est l'un des trois que j'avais nommes au planning comme "a mesurer sur du contenu
  neuf, verdict non predictible par lecture" (avis de faisabilite de s17, section 2). La mesure a ete
  faite ; elle est negative.
- **Complete** : `ADR-0032` (l'ecran d'un item), `ADR-0041` (la valeur d'une proposition est derivee
  de ce qu'elle ecrit), `ADR-0013` (un garde-fou derive son perimetre et se prouve mordant),
  `ADR-0039` (un critere se derive des que la promesse est universelle).

## Contexte

`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` est ne d'un **refus pedagogique** : sur
`cm1-tables-multiplication`, un enfant qui pressait toujours la plus grande des trois propositions
gagnait 64,6 % des manches sans lire un enonce (`nadia-prof`, 2026-08-14). Le garde-fou classe la
bonne reponse d'un item par sa **valeur numerique lue** et refuse qu'une strategie a rang constant
depasse la majorite.

**Le defaut.** `verifierQueLeRangNEstPasPredictible` porte `if (repartition.total() == 0) continue;`.
Un paquet dont **aucun** item ne rend trois valeurs comparables traverse donc ce garde-fou sans etre
juge, et **rien ne le dit**. `lea-dev` l'a trouve sur un paquet de synonymes neuf ; `yanis-qa` l'a
verifie et a etendu la mesure au contenu deja servi.

**Ma propre mesure, avec sa methode, parce que je ne reprends pas un chiffre sur parole.** La clause
ecrite dans ce lot a ete executee **avant** que sa table de declaration ne soit remplie : c'est le
garde-fou lui-meme, sur les 12 paquets de `adapters/adapter-content/src/main/resources/contenu/` et
par le chargeur reel, qui a rendu la liste (`bash outils/mvn-jdk21.sh -pl adapters/adapter-content -am
test -Dtest=...#aucunPaquetNEstEcarteEnSilenceDuCalculDeRang`, JDK 21.0.10, `-am` porte parce que la
mesure est partielle) :

| Paquet ecarte en totalite | Items tires | Items classes | Motif verifie a la source |
|---|---|---|---|
| `cm1-conjugaison-present` | 25 | 0 | **25 items sur 25** portent trois distracteurs, donc quatre propositions |
| `cm1-homophones` | 24 | 0 | **24 items sur 24** portent un seul distracteur, donc deux propositions |
| `cp-comparer-nombres` | 24 | 0 | 24 sur 24 proposent `<`, `>`, `=` : deux distracteurs, aucune valeur numerique |
| `cp-le-bon-son` | 15 | 0 | 15 sur 15 proposent des graphies de phonemes |

**4 paquets sur 12, 88 items, zero contribution.** Les motifs sont verifies fichier par fichier
(comptage des cles `distracteurs` de chaque paquet servi, repartition du nombre d'entrees par item) et
non deduits du seul total nul : les deux premiers sortent du domaine par le **nombre de propositions**,
les deux suivants par leur **nature**. Le constat de `yanis-qa` est confirme a l'identique, paquet par
paquet et item par item.

**Ce qui rend ce silence different d'une exclusion legitime, et c'est tout l'objet de cet ADR.**
L'exclusion **d'un item** est deja declaree en prose par ce garde-fou (`ADR-0013` obligation 2 dans sa
propre Javadoc : "un item dont une proposition n'est pas numerique est hors du domaine de cette
propriete, et non un objet ignore par erreur") et, chez son frere
`LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest`, elle est meme **executable**
(`PAQUETS_A_ITEMS_ECARTES`, qui rougit sur une declaration perimee ou manquante). L'exclusion **d'un
paquet entier** n'avait rien, et c'est la seule qui ne laisse **aucune trace** : un paquet
partiellement examine produit encore un taux qu'on peut lire, un paquet entierement ecarte ne produit
rien du tout. C'est la famille de defaut que ce depot sanctionne depuis s10 - un garde-fou vert qui n'a
jamais rencontre l'objet qu'il pretend garder - avec la circonstance aggravante que ce vert **occupe la
place** d'une preuve absente.

## Decision

1. **Tout paquet servi dont aucun item n'entre dans le calcul de rang est declare, avec son motif
   ecrit.** Un paquet hors du domaine et non declare est **rouge**.
2. **La declaration ne peut pas pourrir, dans les trois sens** : un paquet declare qui contribue des
   items est rouge (declaration perimee) ; un nom qui ne designe plus aucun paquet est rouge
   (declaration fantome) ; un motif vide est rouge (une declaration sans motif ne declare rien).
3. **Cette table n'a pas de plafond**, contrairement aux deux dettes du meme fichier. Un plafond
   dirait qu'un paquet textuel de plus est une degradation, alors que c'est le programme scolaire qui
   le demande : le francais et le questionnement du monde ne se posent pas en chiffres. Ce que la
   table mesure n'est pas une dette a rembourser, c'est **la part du contenu servi sur laquelle cette
   propriete n'est gardee par rien** - aujourd'hui 4 paquets sur 12.
4. **La propriete n'est pas etendue au texte, et c'est declare non gardable en l'etat.** Motif :
   sur des propositions textuelles il n'existe **aucun ordre intrinseque**. Toute strategie qu'on
   encoderait - la proposition la plus longue, la premiere par ordre alphabetique, celle qui reprend
   un mot de l'enonce - serait une **hypothese sur ce qu'un enfant de CP ou de CM1 fait devant trois
   mots**. La propriete d'origine ne vient pas d'un raisonnement d'ingenieur : elle vient d'un taux
   que `nadia-prof` a mesure en jouant. Encoder une strategie sur du texte de ma seule autorite
   imposerait une contrainte de contenu que personne n'a demandee, et la ferait rougir sur du contenu
   pedagogiquement valide - exactement ce qu'`ADR-0028` a deja refuse pour un plafond de longueur.
5. **La question qui debloquerait un garde-fou sur du texte est routee a `nadia-prof`**, en une seule
   phrase : *devant trois propositions textuelles, existe-t-il une strategie qu'un enfant de ce niveau
   exploite sans lire l'enonce, et laquelle ?* Le jour ou elle nomme un taux et une strategie, cet
   ADR est complete par un second calcul, sur le meme modele que la majorite et le seuil minimal - un
   chiffre d'elle, pas de moi.

## Regle executable

Fichier :
`adapters/adapter-content/src/test/java/com/recreo/adapter/content/LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.java`

```java
static void verifierQuAucunPaquetNEstEcarteEnSilence(Path racine, String racineClasspath,
                                                      Map<String, String> declares) {
    List<String> paquets = paquets(racine);
    assertThat(paquets).as("...").isNotEmpty();

    List<String> ecartesEnSilence = new ArrayList<>();
    List<String> declarationsPerimees = new ArrayList<>();
    for (String paquet : paquets) {
        List<Question> items = itemsTires(new YamlContentPackLoader(racineClasspath).chargerPaquet(paquet));
        int classes = repartitionDe(items).total();
        boolean declare = declares.containsKey(paquet);
        if (classes == 0 && !declare) { ecartesEnSilence.add(/* ... */); }
        if (classes > 0 && declare) { declarationsPerimees.add(/* ... */); }
    }
    List<String> declarationsFantomes = declares.keySet().stream()
            .filter(paquet -> !paquets.contains(paquet)) /* ... */ .toList();
    declares.forEach((paquet, motif) -> assertThat(motif).as("...").isNotBlank());
    /* trois assertions d'emptiness, une par famille */
}
```

**Ce qu'elle interdit reellement.** Qu'un paquet servi sorte du controle sans qu'une ligne du depot le
dise. Elle **ne garde pas** la propriete sur ces paquets - elle ne la comble pas, elle la rend
**comptable**. C'est ecrit dans sa Javadoc avant ce qu'elle fait, pour qu'aucun lecteur ne prenne la
declaration pour une preuve.

**Obligations d'`ADR-0013`, les trois.**

1. **perimetre derive** : les paquets viennent du balayage de `contenu/`, jamais d'une liste, et leurs
   items viennent du **chargeur reel** - la meme derivation que la clause de majorite qu'elle complete.
   La seule chose ecrite a la main est la **declaration**, c'est-a-dire l'objet juge ;
2. **derivation totale** : les quatre familles de defaut sont des echecs, jamais des cas ignores. En
   particulier la declaration fantome, que la boucle sur les paquets ne peut pas voir par construction
   (un nom absent du repertoire n'entre jamais dans la boucle) et qui a donc sa propre lecture ;
3. **mise a l'epreuve, quatre morsures, toutes sur le corpus reel** - c'est la **declaration** qui est
   mutee, pas un paquet invente, parce que la declaration est l'objet juge :
   - un nom retire : `unPaquetHorsDuDomaineNonDeclareFaitEchouerLeGardeFou`. C'est la forme exacte de
     ce que `lea-dev` vient de vivre ;
   - un paquet reellement juge, declare a tort (`cm1-tables-multiplication`) :
     `unPaquetDeclareAtortHorsDuDomaineFaitEchouerLeGardeFou` ;
   - un nom qui ne designe aucun paquet : `uneDeclarationQuiSurvitASonPaquetFaitEchouerLeGardeFou` ;
   - un motif blanc : `uneDeclarationSansMotifFaitEchouerLeGardeFou`.

**Mesure** : `bash outils/mvn-jdk21.sh -pl adapters/adapter-content -am test
-Dtest=LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`, JDK 21.0.10 - **20 tests, 0 echec**,
contre **15** avant ce lot. `-am` porte parce que la mesure est partielle (`CLAUDE.md`, prerequis de
build). La porte technique complete est dans l'avis de ce lot.

**Ce n'est pas une regle ArchUnit** - l'objet juge est une table de declaration confrontee au contenu
servi - et **le compte de 29 ne bouge pas**.

## Consequences

- **Le jour ou un paquet textuel neuf est servi, le build est rouge jusqu'a ce que quelqu'un le
  declare.** C'est le comportement voulu, et c'est un cout reel qui tombera sur la fusion d'un autre
  lot : `cm1-synonymes` (branche `s17-lea`, non fusionnee) est exactement ce cas. Le message d'echec
  nomme la table, le motif attendu et les deux issues possibles ; il ne laisse pas deviner. **Route a
  `sofia-sm`** pour que la fusion de ce lot-la porte la ligne de declaration dans le meme commit, comme
  n'importe quel garde-fou de contenu deja en place.
- **Declarer n'est pas garder.** Sur ces 4 paquets, la propriete reste gardee par **rien**, et la table
  le rend visible au lieu de le dissimuler. Quiconque lit ce fichier voit desormais la taille du trou ;
  personne ne peut plus croire que le vert de ce garde-fou couvre tout le contenu servi.
- **Un motif peut etre faux.** La clause exige qu'il existe et qu'il ne soit pas vide, jamais qu'il
  soit juste. Elle transforme une omission silencieuse en **affirmation signee**, relue comme n'importe
  quelle ligne de diff - c'est tout ce qu'elle achete, et c'est le meme achat qu'`ADR-0050` clause 2.
- **La table grandira**, et sa croissance est un signal a lire, pas un echec a masquer : plus le
  programme scolaire couvert s'eloigne du calcul, plus la part du contenu hors de cette propriete
  augmente. C'est un argument pour la question routee en decision 5, pas contre la table.

**Ce qui me ferait changer d'avis.** Si `nadia-prof` nomme une strategie textuelle et un taux, la
decision 4 tombe et un second calcul entre dans ce meme garde-fou. Si, au contraire, la table atteint
la moitie du contenu servi sans qu'aucune strategie textuelle n'ait ete nommee, alors ce garde-fou
garde une propriete minoritaire sous un nom general et il faudra le renommer - `ADR-0039`, la meme
resolution que pour les sept selecteurs d'`ADR-0050`.

## Alternatives considerees

1. **Ne rien faire, ou l'ecrire seulement en prose dans la Javadoc.** Ecartee : une prose ne rougit
   pas. Le garde-fou frere a deja tranche ce point pour son propre cas partiel avec une table
   executable ; la meme situation, en pire (exclusion totale), ne peut pas se contenter de moins.
2. **Encoder une strategie textuelle de ma propre autorite** (la proposition la plus longue, l'ordre
   alphabetique). Ecartee : c'est une affirmation sur ce qu'un enfant fait, donc hors de mon mandat,
   et elle ferait rougir du contenu que `nadia-prof` a valide. La propriete d'origine vient d'un taux
   mesure en jouant, pas d'un raisonnement de conception.
3. **Faire echouer tout paquet hors du domaine, sans declaration possible.** Ecartee : 4 des 12
   paquets servis seraient rouges pour un contenu qui ne viole rien. Un rouge que l'auteur ne peut pas
   resoudre par une correction legitime finit toujours neutralise - et il l'aurait ete a raison.
4. **Ranger ces paquets dans `DETTE_DE_RANG`.** Ecartee : une dette nomme une **violation** exemptee,
   et ces paquets ne violent rien. Le melange casserait en plus le garde-fou de peremption de la dette,
   qui exige de chacun de ses membres qu'il **depasse encore le seuil**.
5. **Garder l'appariement de mots entre l'enonce et la bonne reponse**, la seule strategie textuelle
   que `nadia-prof` ait deja nommee comme disqualifiante (`COMP-1` et `COMP-2` de son arbitrage de
   s17 : des items "resolubles par appariement de mots avec le texte"). Ecartee **pour ce garde-fou-ci**,
   avec la condition qui la rendrait due : cette strategie n'a de sens que sur une question posee **sur
   un texte**, elle n'en a aucun sur des synonymes, ou la bonne reponse ne partage par construction
   aucun mot avec l'enonce. Elle appartient donc a la story de comprehension - sortie du sprint 17 par
   `camille-po` - et non a une propriete qui traverse toutes les matieres. L'y coller ferait exactement
   ce que la decision 4 refuse : une regle generale nee d'un cas particulier.
