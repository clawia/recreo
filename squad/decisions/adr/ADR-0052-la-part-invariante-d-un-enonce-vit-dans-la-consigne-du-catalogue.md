# ADR-0052 - La part invariante d'un enonce vit dans la consigne de l'entree de catalogue, et la consigne devient porteuse

- **Statut** : Accepte, sa regle ecrite dans le meme commit
  (`ToutEcranEchangeEstUnDocumentJouableTest`, une clause, deux morsures) ; **amende le 2026-08-20
  (s17)** : son illustration de la designation du mot cible devient neutre, `SYN-6` rendant la marque
  porteuse de sens. **La decision et la regle ne changent pas.**
- **Sprint** : s17. **Date** : 2026-08-19. **Auteur** : theo-archi.
- **Arbre mesure** : `round-two` a `39b3c42` (SHA releve sur la branche, pas repris d'un message).
- **Route par** : `nadia-prof` (`FICHE-bandit-manchot-synonymes-cm1.md`, Done pedagogique du
  2026-08-19 sur `S17-3`, **REFUSE**, reserve `SYN-5`) puis `camille-po` (`DEC-0039`, cinquieme
  amendement) : *ou la clause invariante peut-elle vivre sans code de domaine neuf ?*
- **Complete** : `ADR-0007` (un seul chargeur pilote par un schema), `ADR-0012` (ce qui vit dans
  l'entree de catalogue), `ADR-0020` (l'unite de verification est le document assemble).

## Contexte

`nadia-prof` a refuse `S17-3` sur un rendu, **contenu credite** : les vingt items sont justes, les
vingt indices aussi, et zero sur vingt nomme la reponse. A 360 x 640 la derniere proposition tombe
**63 a 159 px sous le pli sur 20 items sur 20**. Le chiffre qui rend le refus non negociable vient du
dossier : le plus **court** enonce du paquet fait **101 caracteres**, contre **97** pour le plus long
de celui que `DEC-0024` a retire du catalogue pour ce motif exact.

Elle a isole le levier et refuse de choisir a notre place : une **clause invariante** de 60 a 66
caracteres, repetee a l'identique dans les vingt enonces, qui occupe **3 a 4 des 5 a 7 lignes
rendues** - *"Dans cette phrase, quel mot peut remplacer X sans changer le sens ?"*. `camille-po` a
ecarte le seuil de longueur (`DEC-0016` l'interdit nommement) et la sortie de perimetre, et route le
mecanisme ici.

**Ce que la lecture du paquet servi ajoute, et qui decide de la forme.** La clause n'est pas
invariante au sens litteral : elle enchasse le mot cible. Sur `cm1-synonymes.yaml`, les vingt enonces
s'ecrivent `<phrase>. Dans cette phrase, quel mot peut remplacer "<mot>" sans changer le sens ?` -
soit une tete fixe de **41 caracteres**, le mot cible **repete** (il est deja dans la phrase), et une
queue fixe de **23 caracteres**. **64 caracteres strictement identiques d'un item a l'autre**, et un
mot qui, lui, doit rester dans l'item.

## Decision

1. **La part strictement invariante d'un enonce vit dans le champ `consigne` de l'entree de
   catalogue.** Ce champ existe deja, il est **obligatoire** (`YamlContentPackLoader` echoue sans
   lui), il est deja porte jusqu'a la vue (`GameDefinition.consigne` -> modele -> `.consigne`) et
   deja rendu par `fragments/manche.html`. **Aucune ligne de code de production n'est ecrite pour
   cela, aucun champ n'est ajoute au schema de contenu, aucun type de contenu neuf n'est declare.**
2. **Le mot cible, lui, reste dans l'item.** Il varie d'un item a l'autre ; le sortir de l'enonce
   demanderait un champ neuf, donc une composante de plus sur `Question`, donc le mecanisme complet
   d'`ADR-0050` - pour un mot qui est deja ecrit dans la phrase.
3. **Aucune cle neuve n'est ajoutee au paquet pour ce besoin.** Une cle que la fabrique ne lit pas
   est ignoree **sans un mot** (dette nommee par `ADR-0024`) : elle chargerait, passerait tous les
   garde-fous de contenu, et l'enfant ne verrait rien. C'est mesure et ecrit dans l'avis de mecanisme
   du texte-support (s17).
4. **La consigne cesse d'etre un ornement : elle devient porteuse.** Tant qu'elle ne disait qu'une
   generalite ("Combien ca fait ?"), la perdre coutait peu. Des lors qu'elle peut porter la question,
   un ecran qui montrerait l'enonce d'un item **sans** elle laisserait l'enfant devant une phrase et
   trois mots, sans rien qui dise quoi en faire. C'est cette consequence-la qui est encodee.

## Regle executable

`adapters/adapter-web/src/test/java/com/recreo/adapter/web/ToutEcranEchangeEstUnDocumentJouableTest.java`

> **Aucun ecran ne montre l'enonce d'un item sans la consigne de son jeu.**

```java
static Set<String> enoncesSansConsigne(Document document) {
    Set<String> defauts = new TreeSet<>();
    for (Element enonce : document.select(".enonce")) {
        if (enonce.text().isBlank()) {
            continue;
        }
        boolean consigneLisible = document.select(".consigne").stream()
                .anyMatch(consigne -> !consigne.text().isBlank());
        if (!consigneLisible) {
            defauts.add(enonce.outerHtml());
        }
    }
    return defauts;
}
```

- **Ce qu'elle interdit reellement** : qu'un lot gagne des pixels verticaux en retirant la consigne de
  l'ecran de manche. La tentation est **installee par ce refus meme** - le paquet deborde le pli, et
  la consigne etait le seul bloc de texte de cet ecran qu'aucun garde-fou ne retenait. Depuis cette
  decision, la retirer ne raccourcit pas l'ecran : elle le rend **muet**.
- **Perimetre derive** (`ADR-0013` obligation 1) : le corpus d'ecrans vient des routes du controleur
  (`RequestMappingHandlerMapping`), jamais d'une liste ; la clause juge le **document assemble**
  (`ADR-0020`), pas le gabarit - un gabarit correct rendu dans une coquille qui l'ampute produirait le
  meme ecran mutile.
- **Non-vacuite** (`ADR-0003`) : si aucun ecran du corpus ne rend d'enonce, la clause echoue au lieu
  de passer verte a vide.
- **Deux morsures, sur la sortie reelle du serveur, mutee** - jamais un gabarit ecrit de ma main :
  la consigne **retiree** du document assemble, et la consigne **videe** (la balise reste, le modele
  ne la nourrit pas - la forme exacte de l'ecran mort d'`ADR-0020`, qu'une suppression franche ne
  couvre pas).

**Ce n'est pas une regle ArchUnit** : l'objet juge est un document HTML rendu. Le compte de 29 ne
bouge pas.

## Ce que cette decision ne fait pas, et qui doit etre lu avant le reste

**Elle n'apporte aucun garde-fou a `SYN-5`.** Aucun test de ce depot ne mesure une hauteur rendue
(`ADR-0025`, `ADR-0028`), aucun ne le fera tant qu'aucun outil ne pilote un navigateur, et cette
capacite a quitte le perimetre de la squad (`DEC-0039`, section 3). `nadia-prof` reste **seule garde**
de `SYN-5`, exactement comme elle l'ecrit. Un correctif de mecanisme n'est pas un garde-fou, et la
clause ci-dessus ne dit rien de la hauteur : elle dit qu'un texte necessaire n'a pas disparu.

**Elle ne garantit pas non plus que le deplacement suffise.** Ce qui est derivable des chiffres deja
publies, et rien de plus : la clause quitte `.enonce` (2rem, interligne 48 px) pour `.consigne`
(1,25rem, interligne 30 px) - les deux valeurs sont d'`ines-design` et de `nadia-prof`, pas de moi. A
nombre de lignes **inchange**, le meme texte coute donc 18 px de moins par ligne, soit **54 a 72 px**
sur les 3 a 4 lignes mesurees ; a la hausse, une police plus petite fait tenir plus de caracteres par
ligne, donc moins de lignes, mais **de combien n'est pas derivable** - aucune feuille de ce depot ne
declare de `font-family`. Le deficit mesure est de **63 a 159 px**. Le plancher de gain ne couvre donc
meme pas le meilleur cas, et le plafond n'est pas calculable ici. **Ce mecanisme rend `SYN-5`
atteignable, il ne la prononce pas** : seule une mesure de rendu sur les vingt items le dira.

## Amendement du 2026-08-20 (s17) - l'illustration devient neutre, le mecanisme ne bouge pas

**Ce qui a change autour de cet `ADR`.** `nadia-prof` a **accepte** la designation generique - le motif
etant dans le texte de sa propre condition, qui disait *"le mot souligne"* et non un mot choisi pour sa
forme - et a ouvert `SYN-6` (bloquante) : *le mot cible porte une marque qui est une ponctuation
francaise, et la consigne le designe par cette marque exacte*. Son motif est structurel et je le
reprends : tant que la clause vivait dans chaque item, la marque etait **redondante** - la clause
nommait le mot juste apres ; une fois la clause partie dans la consigne, **la marque est la seule
chose qui dit de quel mot on parle**. Elle passe de detail de forme a porteuse de sens.

**Correction du dossier, parce qu'un `ADR` mal cite se propage.** Le fait mesure est adresse a *"la
formulation que `ADR-0052` cite en exemple"*, citee comme *"le mot en gras"*. **Cette formulation n'est
pas dans cet `ADR`** : mesure sur `eb3543b`, le mot "gras" y apparait **une seule fois**, dans la
description typographique du deplacement (*"2rem gras -> 1,25rem normal"*), qui decrit les deux blocs
et non la designation du mot. L'illustration que cet `ADR` portait est *"le mot entre guillemets"* - la
meme formulation que `SYN-6` rappelle elle-meme comme celle des items. **Cela ne retire rien a la
mesure**, qui vaut telle quelle et que je verifie ci-dessous ; cela deplace seulement ce qui est
corrige : l'illustration de cet `ADR` n'etait pas fausse, elle etait **ambigue** - elle nommait par un
seul mot une marque dont une variante est refusee et une autre acceptable - et c'est cette ambiguite
que cet amendement leve.

**Les trois faits, re-mesures par moi sur `eb3543b` plutot que repris** (lecture de la feuille et du
gabarit servis) :

| Fait | Mesure |
|---|---|
| `.enonce` est en gras **sur tout le bloc** | `.enonce { font-size: 2rem; font-weight: bold; margin-bottom: 0.25rem }` - la phrase entiere est grasse, "en gras" ne designe donc rien |
| l'italique n'est pas dans le vocabulaire du depot | `font-style` : **0 occurrence** dans `static/css/recreo.css` |
| aucun balisage ne peut venir du contenu | `<p class="enonce" th:text="${enonce}">`, et **0 occurrence** de `th:utext` dans les gabarits : `th:text` echappe, donc un `<b>` ecrit dans le YAML s'afficherait **litteralement**, en toutes lettres, sous les yeux de l'enfant |

**Ce que ces trois faits font a cet `ADR` : ils confirment son point 3, ils ne le contredisent pas.**
Le point 3 decide qu'aucune cle neuve n'entre dans le paquet et que le schema **ne porte aucun
balisage** ; la troisieme ligne du tableau en est la demonstration executable, et les deux premieres
ferment par la mesure la branche typographique que le point 3 fermait par principe. **La marque ne peut
donc etre qu'un caractere a l'interieur de la chaine.** C'est une consequence du mecanisme, pas une
correction du mecanisme.

**Ce qui change dans le texte de cet `ADR`** : l'illustration de la premiere consequence ne nomme plus
de marque. Elle nomme la **contrainte** - la marque est un caractere, elle est une ponctuation
francaise, et la consigne la designe par ce caractere exact - et laisse la marque a `ines-design` qui
la propose et a `nadia-prof` qui la juge. **Je ne la choisis pas**, et je ne la choisis pas davantage
en la citant en exemple : c'est precisement ainsi qu'une illustration devient une decision par
accident.

### Le mecanisme lui-meme ne bouge pas, et voici pourquoi

La decision porte sur **ou vit un texte** : la part strictement invariante dans la `consigne` de
l'entree de catalogue, la part variable dans l'item. La marque du mot cible est **dans la part
variable** - elle vit dans la phrase, avec le mot. Quelle qu'elle soit, elle traverse exactement le
meme chemin qu'aujourd'hui : `items[].enonce` -> `QuestionContentSourceFactory` -> `Question.enonce`
-> modele -> `th:text`. **Aucun des quatre points de la decision ne depend de la marque retenue** : ni
le lieu de la part invariante, ni le maintien du mot dans l'item, ni le refus d'une cle neuve, ni le
caractere porteur de la consigne. `SYN-6` contraint le **contenu** de la consigne (elle doit nommer la
marque exacte) et le **contenu** de l'item (il doit la porter) ; elle ne contraint aucun mecanisme.

**Une seule chose serait tombee** : si la designation avait du passer par une marque **typographique**,
il aurait fallu un balisage dans le contenu, donc une cle neuve ou un second champ - et le point 3
serait devenu intenable. La mesure de `nadia-prof` ecarte cette branche ; elle ne l'ouvre pas.

### Faut-il un garde-fou sur le choix de la marque ? Non, et je dis ce que ce non laisse ouvert

**Ce n'est pas gardable, pour deux raisons distinctes.**

1. **Le predicat porterait sur de la prose francaise.** Pour rougir sur une consigne qui designe une
   marque que la feuille ne rend pas, il faudrait reconnaitre les mots qui nomment une marque -
   "gras", "italique", "souligne", "en couleur". C'est une **liste de mots ecrite a la main sous une
   promesse universelle**, ce qu'`ADR-0039` refuse, et elle serait contournee par la premiere
   formulation qu'elle n'aurait pas prevue ("le mot ecrit autrement"). Un garde-fou qui compte
   l'antidote parmi les malades est la faute mesuree en s05 ; celui-la ne verrait meme pas la maladie.
2. **La propriete reelle est rendue, pas declarative.** `SYN-6` n'exige pas qu'un mot figure dans une
   consigne : elle exige **qu'un enfant sache de quel mot on parle**, et sa verification le dit -
   consigne masquee, montrer l'item a un tiers, lui demander de pointer le mot. Aucun automate de ce
   depot ne mesure cela, et `nadia-prof` l'ecrit elle-meme : *"je reste seule garde de `SYN-5` et de
   `SYN-6`"*, et *"`SYN-6` sur le rendu, pas sur le fichier"*. C'est la troisieme fois de ce sprint que
   je conclus ainsi, et la conclusion n'est pas moins vraie parce qu'elle se repete.

**Ce qui est deja garde sur ce chemin, et ce qui ne l'est pas** - dit pour que personne ne croie ni
trop ni trop peu :

- **garde** : un caractere non-ASCII d'une **proposition** traverse la chaine reelle jusqu'au serveur
  embarque, par une vraie requete HTTP, pour **chaque** proposition non-ASCII des paquets servis
  (`PropositionsAccentueesJouablesIT`, perimetre derive du contenu reel). C'est le defaut de s05, et il
  est ferme ;
- **garde** : l'unicite de graphie de la matiere et l'inventaire des chaines accentuees exposees par le
  port entrant (`UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest`,
  `TexteAccentueExposeParLePortEntrantTest`) ;
- **non garde** : qu'un caractere donne d'un **enonce** soit **visible** a 360 px, en 2 rem gras, dans
  la phrase. C'est le point 4 de la liste de mesures de `nadia-prof`, et c'est elle qui le tient.

**Ce que `lea-dev` peut tenir pour acquis sans attendre personne**, puisqu'elle ecrit la consigne et le
plus long des vingt items - trois faits de mecanisme, aucun choix de forme :

1. **la marque est un caractere dans la chaine** : ni gras, ni italique, ni balise - le schema ne porte
   aucun balisage et `th:text` echappe, donc un `<b>` s'afficherait en toutes lettres a l'enfant ;
2. **la consigne vit dans `catalogue.yaml`**, champ `consigne` de l'entree, obligatoire, rendu une fois
   par ecran **avant** l'enonce - aucun code a ecrire pour cela ;
3. **l'encodage n'est pas un obstacle** : les caracteres non-ASCII traversent deja la chaine, et le
   garde-fou qui le prouve est derive du contenu reel, donc il couvrira son paquet le jour ou il sera
   servi. **Le choix de la marque appartient a `ines-design` et a `nadia-prof`** ; ce qui precede dit ce
   qui est possible, jamais ce qui est bon.

**Aucune regle neuve n'accompagne cet amendement**, et c'est un choix ecrit plutot qu'un silence : la
clause de cet `ADR` - aucun ecran ne montre l'enonce d'un item sans la consigne de son jeu - garde
exactement la meme propriete apres `SYN-6` qu'avant, puisqu'elle ne dit rien du **contenu** des deux
blocs. Le compte des regles ArchUnit reste a **29**.

## Consequences

- **La question migre de l'item vers le jeu.** Consequence de contenu, pas de mecanisme : la consigne
  ne peut plus nommer le mot cible (elle est ecrite une fois pour vingt items) et doit le designer
  **par la marque que ce mot porte dans la phrase** - une marque qui est un **caractere** de la chaine,
  qui est une ponctuation francaise, et que la consigne nomme par ce caractere exact.
  *(Amendement du 2026-08-20 : cette phrase citait une marque en exemple ; l'exemple est retire.
  `SYN-6` a rendu la marque porteuse de sens - elle est la seule chose qui dit de quel mot on parle une
  fois la clause partie - et une illustration devient une decision par accident. La marque est a
  `ines-design`, son jugement a `nadia-prof` ; la designation generique elle-meme est **acceptee** par
  `nadia-prof` le 2026-08-20.)* **Est-ce encore `SYN-1` ?** Ce n'etait pas ma question : `SYN-1` est de
  `nadia-prof`, et elle a repondu oui.
- **L'entree de catalogue doit exister**, ce qui est de toute facon le second motif du refus : sans
  elle, aucun enfant n'atteint le jeu. La consigne est un champ **obligatoire** de cette entree : le
  cout de cette decision au-dela de ce qui etait deja du est donc **nul**.
- **Le gain n'est pas une suppression de repetition a l'ecran, et il faut le dire pour que la mesure
  a venir ne decoive pas.** La consigne et l'enonce sont rendus **une fois chacun par ecran** : sur un
  ecran donne, la clause s'affiche autant de fois avant qu'apres. Ce qui change est **la typographie**
  (2rem gras -> 1,25rem normal) et **le lieu d'ecriture** (une fois dans le catalogue au lieu de vingt
  fois dans le paquet). La formule "deja rendu une fois par ecran plutot que repete par item" decrit
  la repetition dans le **fichier**, pas a l'ecran.
- **Un benefice qui n'etait pas demande** : la clause ne peut plus deriver d'un item a l'autre. Vingt
  copies d'une meme phrase sont vingt occasions d'en modifier une seule.
- **Ce que la clause neuve rend plus rigide** : un ecran futur qui montrerait un enonce sans consigne -
  par exemple un ecran de revision qui reprendrait un item hors de son jeu - sera rouge. C'est voulu :
  un item sorti de son jeu perd sa question.

## Alternatives considerees

1. **Un champ neuf par item** (`clauseInvariante:` dans le YAML). Ecartee : elle ferait vingt copies
   de plus, pas moins ; et une cle que la fabrique ne lit pas est ignoree en silence, donc elle
   demanderait tout le mecanisme d'`ADR-0050` - composante sur `Question`, accesseur de mecanique,
   attribut de modele, bloc de gabarit - pour rendre au meme endroit un texte que la consigne rend
   deja.
2. **Un champ au niveau du paquet** (`consigne:` dans le fichier de contenu). Ecartee : le paquet ne
   porte aujourd'hui **aucun** texte destine a l'ecran hors des items, et `ADR-0012` a deja tranche que
   ce qui gouverne une partie vit dans l'entree de catalogue. Deux endroits pour la meme chose est la
   premiere marche vers deux facons de decrire un jeu.
3. **Un `type` de contenu neuf** (`question-sur-phrase`). Ecartee sans hesitation : c'est la premiere
   marche vers les neuf familles de chargeurs de la version precedente (dette 3 de `CLAUDE.md`), pour
   un gain nul - la difference est un texte, pas une structure.
4. **Raccourcir les enonces sous un seuil de caracteres.** Ecartee par `camille-po` avant moi
   (`DEC-0016`), et je n'y reviens pas : un raisonnement par lettres manque son pire cas reel.
5. **Ne rien ecrire et laisser la story revenir avec un contenu plus court.** Ecartee : le contenu est
   credite par `nadia-prof`, et le refaire plus court sans deplacer la clause reduirait la phrase de
   contexte, c'est-a-dire exactement ce que `SYN-1` exige de garder.
