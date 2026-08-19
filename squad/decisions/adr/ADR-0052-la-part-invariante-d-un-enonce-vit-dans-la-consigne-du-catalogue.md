# ADR-0052 - La part invariante d'un enonce vit dans la consigne de l'entree de catalogue, et la consigne devient porteuse

- **Statut** : Accepte, sa regle ecrite dans le meme commit
  (`ToutEcranEchangeEstUnDocumentJouableTest`, une clause, deux morsures).
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

## Consequences

- **La question migre de l'item vers le jeu.** Consequence de contenu, pas de mecanisme : la consigne
  ne peut plus nommer le mot cible (elle est ecrite une fois pour vingt items) et doit le designer
  autrement - *"le mot entre guillemets"*, par exemple. **Est-ce encore `SYN-1` ?** Ce n'est pas ma
  question : `SYN-1` est de `nadia-prof`, et cette formulation lui est routee avec sa reserve deja
  ouverte sur la marque du mot cible (le guillemet droit n'est pas une ponctuation francaise). Si
  elle refuse la designation generique, ce mecanisme tombe et il faut revenir vers elle, pas vers le
  code.
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
