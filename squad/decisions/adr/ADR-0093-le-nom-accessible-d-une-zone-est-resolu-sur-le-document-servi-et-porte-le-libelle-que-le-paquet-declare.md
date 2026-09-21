# ADR-0093 - Le nom accessible d'un bouton de zone est resolu sur le document servi, et il porte le libelle que le paquet declare

- **Statut** : **`Propose`** le 2026-09-21 (planning de s36). Il ne passera `Accepte` que quand ses
  deux clauses seront dans l'arbre, dans les deux fichiers nommes en section *Regle executable*,
  avec leurs quatre morsures.
- **Sprint** : s36. **Date** : 2026-09-21. **Auteur** : `theo-archi`.
- **Route par** : `S36-QLM11-PLANCLASSE` (`docs/backlog/backlog.md`, section "Sprint 36"), reprise
  de la ligne `QLM-CP-11` refusee a s35, et par la premiere des deux sorties que `nadia-prof`
  accepte mot pour mot dans `squad/conformite/FICHE-designation-plan-de-la-classe.md`, section 3 :
  *"que le libelle sorte de la boite du bouton - une legende a cote du plan, un renvoi, un libelle
  en marge - pourvu que l'enfant sache sans ambiguite quel bouton porte quel libelle, et que le
  bouton garde son nom accessible."*
- **Prolonge** : `ADR-0080` (garde-fou 1 - chaque zone est un bouton du formulaire de coup ; c'est
  la clause de nom accessible de ce garde-fou que cet `ADR` corrige), `ADR-0092` (la lisibilite du
  libelle n'est pas gardable par une arithmetique, et son mecanisme 1 est le second porteur de la
  clause corrigee), `ADR-0015` (un nom qui promet plus que son perimetre), `ADR-0013` (un garde-fou
  derive son perimetre et se prouve mordant), `ADR-0017` (comparer deux boutons d'un meme ecran -
  cite ici comme borne mesuree, **et non amende**).
- **Ce que cet `ADR` ne fait pas** : il ne choisit **aucune forme**. Ou vit le libelle, s'il y a une
  legende, un renvoi, un numero, une marge, appartient a `ines-design` qui propose et a
  `nadia-prof` qui tranche. Il ne prononce aucun Done, n'amende ni `ZON-15` ni `ZON-16`, ne rouvre
  ni le plancher de cible tactile d'`ADR-0080` ni le plancher de police d'`ADR-0050`, ne rouvre pas
  `ADR-0017`, ne ferme aucune ligne de matrice, et ne touche aucun contenu.
- **Arbre mesure** : `round-two` a `a8fcb000`, worktree dedie. **Toutes les mesures citees sont des
  lectures de source** - deux classes de test, une feuille de style, un gabarit, un paquet de
  contenu - et de l'arithmetique sur des pixels mesures par d'autres, nommes a chaque fois. Aucune
  mesure de rendu n'a ete refaite ici.

---

## Contexte

### 1. Le fait qui declenche cet `ADR` : la clause s'appelle *nom accessible* et juge un texte

Deux garde-fous portent la meme clause, ecrite deux fois parce qu'aucun `test-jar` ne publie l'une
hors de son module (motif deja ecrit dans `ADR-0092`, mecanisme 1) :

```java
// adapters/adapter-web/src/test/java/com/recreo/adapter/web/
//     ChaqueZoneDesignableEstUnBoutonDuFormulaireDeCoupTest.java  (ligne 97 au 2026-09-21)
// bootstrap/src/test/java/com/recreo/bootstrap/rendu/
//     LEcranDeMancheDuJeuDeDesignationReelEstServiTest.java       (ligne 130 au 2026-09-21)
if (bouton.text().isBlank()) {
    griefs.add("le bouton de zone " + bouton.outerHtml()
            + " ne porte aucun nom accessible (texte vide)");
}
```

Le grief nomme une propriete - *le bouton porte un nom accessible* - et le juge en verifie une
autre, plus etroite : *le bouton porte du texte*. Aujourd'hui les deux coincident, parce que
`fragments/designation.html` rend le libelle par `th:text="${zone.libelle()}"` a l'interieur du
bouton. Elles cessent de coincider des qu'un libelle sort de la boite : un bouton nomme par
`aria-label` ou par `aria-labelledby` **a** un nom accessible et **n'a pas** de texte. Les deux
garde-fous le declarent alors fautif.

`ADR-0015` a deja nomme cette famille, et `ADR-0092` l'a payee sur sa propre prescription en
renommant `PAQUETS_DE_DESIGNATION_AUDITES_A_L_OEIL`. La difference ici est que le nom ne se contente
pas de promettre trop : le juge **refuserait** la seule sortie que la porte pedagogique accepte.

### 2. Pourquoi cette sortie est la seule qui reste, et c'est une arithmetique, pas une preference

`nadia-prof` accepte deux sorties (meme fiche, section 3) : le libelle sort de la boite du bouton,
ou le pave de `.zone` est reduit sur une zone de designation. La seconde est bornee, et la borne est
deja mesuree dans ce depot :

- ce qu'un libelle positionnel replie sur deux lignes demande dans le pave de `.zone` : **109 x 88
  px** (`nadia-prof`, fiche, section 3) ;
- ce dont il dispose aujourd'hui : **12 px** de boite de contenu sur `armoire` et **19 px** sur
  `porte`, les 44 px de pave et de bordure partant avant le premier caractere (`ADR-0092`,
  decision 3, concordant avec les *"16 a 23 px"* de `nadia-prof` par un autre chemin) ;
- ce que le levier du pave peut rendre : **4,8 px**, et pas un de plus. `ADR-0017` borne ce pave par
  en dessous a 1,1rem - la plus large des sorties servies sur un ecran de jeu - et l'amendement du
  2026-09-11 a `ADR-0092` l'a mesure sur le reacteur : le pave ramene a 0,25rem rend
  `LActionNeDeclarePasMoinsQueLaSortieTest` rouge, six griefs sur `fragments/designation.html`.

**12 + 4,8 = 16,8 px contre 109 px demandes.** Le deficit reste de l'ordre de **92 px** sur la zone
la plus contrainte. Et la troisieme voie - agrandir les cadres - est fermee par la meme fiche : trois
des six elements d'un plan de classe sont des bandes minces par nature, et on ne donne pas 25 % de
hauteur au tableau sans mentir sur le plan.

Il reste donc une sortie, et elle est rouge aujourd'hui pour un motif qui n'a rien a voir avec la
propriete gardee.

### 3. Ce qu'un elargissement naif casserait, et qui explique la clause 2

Retirer la clause, ou la remplacer par *"texte ou `aria-label`"* sans rien d'autre, ouvrirait deux
degradations que le juge actuel interdit par accident :

- un bouton nomme autrement que par son libelle - la zone est renommee a l'insu du contenu, et
  `ADR-0091` (le texte alternatif ne nomme aucune zone) comme le garde-fou 5 d'`ADR-0080` (l'enonce
  ne nomme pas le libelle de sa bonne zone) deviennent contournables par le nom du bouton ;
- un `aria-labelledby` qui designe un identifiant absent du document : le nom accessible est alors
  vide a l'ecran de l'enfant, et le garde-fou est vert.

---

## Decision

### 1. Le nom accessible d'un bouton de zone est **resolu**, et trois formes seulement le portent

Sur le document servi, le nom accessible d'un bouton de zone est :

- le **texte** du bouton, s'il n'est pas vide ; sinon
- la valeur de son **`aria-label`**, si elle n'est pas vide ; sinon
- la concatenation des textes des elements que son **`aria-labelledby`** designe, **chaque**
  identifiant devant resoudre, dans le meme document, vers un element au texte non vide.

Un bouton qui ne porte aucune des trois est **rouge**, exactement comme aujourd'hui. Un
`aria-labelledby` dont un seul identifiant ne resout pas est **rouge** : c'est un nom accessible
vide qui se croit rempli.

### 2. Le nom accessible resolu **porte le libelle que le paquet declare pour cette zone**

Apres normalisation des espaces et de la casse, le nom accessible resolu **contient** le libelle que
le paquet declare pour la zone dont le bouton porte l'identifiant en `value`.

Ce que cette clause **interdit** : un bouton nomme autrement que par son libelle, un libelle
supprime du document, une legende qui renomme la zone, un bouton numerote sans nom.
Ce qu'elle **n'interdit pas**, et c'est deliberement une inclusion et non une egalite : qu'une forme
ajoute un prefixe ou un suffixe au nom - *"1. en haut, au centre"* passe. Le choix de cette forme
appartient a `ines-design`, et une egalite stricte la refuserait par avance.

La correspondance zone -> libelle est **derivee du paquet servi**, jamais ecrite dans le test : c'est
le meme recensement que celui des deux cliquets de designation, reutilise et non recrit.

### 3. Ce que cet `ADR` refuse d'affirmer

La condition entiere de `nadia-prof` est *"que l'enfant sache sans ambiguite quel bouton porte quel
libelle"*. **Cette moitie-la n'est pas encodee ici, et elle n'est pas encodable** : elle demande de
regarder un ecran. Ce qui est encode est la moitie opposable - le nom existe, et c'est le libelle du
contenu. Le reste reste l'oeil de `nadia-prof`, au meme titre que `ZON-15` depuis `ADR-0092`,
decision 2. Ecrit ici pour qu'un vert de CI ne soit pas lu comme un Done pedagogique.

---

## Regle executable ArchUnit

**Ce ne sont pas des `ArchRule`** : les objets juges sont des documents HTML rendus et des paquets
de contenu charges, pas des classes. Aucune `ArchRule` n'est ajoutee par cet `ADR` ; le compte du
depot est donc inchange par rapport au dernier compte mesure, **33** au 2026-09-11 (methode : motif
`static final ArchRule` sur les arbres de sources, `/target/` exclu et le faux module de
contre-exemple exclu -
`squad/sprints/s35-fermer-ce-qui-n-a-pas-recu-le-go-pedagogique/confirmation-archunit.md`). **Je ne l'ai pas
recompte aujourd'hui.**

### Clause 1 - dans les deux fichiers qui portent deja la clause

- `adapters/adapter-web/src/test/java/com/recreo/adapter/web/ChaqueZoneDesignableEstUnBoutonDuFormulaireDeCoupTest.java`
- `bootstrap/src/test/java/com/recreo/bootstrap/rendu/LEcranDeMancheDuJeuDeDesignationReelEstServiTest.java`

```java
/** ADR-0093 clause 1 : le nom accessible est resolu, il n'est plus assimile au texte du bouton. */
private static String nomAccessible(Element bouton, Document document) {
    if (!bouton.text().isBlank()) {
        return bouton.text();
    }
    if (!bouton.attr("aria-label").isBlank()) {
        return bouton.attr("aria-label");
    }
    String references = bouton.attr("aria-labelledby").trim();
    if (references.isBlank()) {
        return "";
    }
    StringBuilder nom = new StringBuilder();
    for (String identifiant : references.split("\\s+")) {
        Element cible = document.getElementById(identifiant);
        if (cible == null || cible.text().isBlank()) {
            return "";
        }
        nom.append(nom.isEmpty() ? "" : " ").append(cible.text());
    }
    return nom.toString();
}
```

Le grief devient *"le bouton de zone ... ne porte aucun nom accessible : ni texte, ni aria-label, ni
aria-labelledby resolvant vers un texte non vide"* - un message qui nomme ce qu'il juge.

### Clause 2 - dans le seul module qui voie les deux cotes

Dans `LEcranDeMancheDuJeuDeDesignationReelEstServiTest` uniquement : `bootstrap` est le seul module
qui voie a la fois `adapter-content` et `adapter-web`, et la Javadoc de cette classe le dit deja.
Le paquet de l'entree de catalogue jugee est lu par le recensement existant
(`AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest.repertoiresDeContenu`, deja reutilise par les
deux cliquets de designation), et non par un second recensement : deux recensements du meme objet
divergent un jour.

Pour chaque bouton de zone du document servi, `value` donne l'identifiant de zone ; le paquet donne
le libelle de cette zone pour l'item joue ; la comparaison est un `contains` sur les deux chaines
normalisees (espaces reduits, casse repliee).

### Les quatre morsures dues (`ADR-0013`, obligation 3)

1. **Un bouton sans aucun des trois porteurs est rouge.** La propriete d'origine ne s'affaiblit pas.
2. **Un bouton nomme par `aria-label` seul est vert sous le juge neuf, et rouge sous l'ancien.** La
   morsure porte les deux verdicts dans la meme assertion : sans cela, la clause 1 pourrait etre
   decorative sans que personne le voie.
3. **Un `aria-labelledby` qui designe un identifiant absent du document est rouge.**
4. **Un nom accessible qui ne porte pas le libelle declare est rouge**, sur **materiau reel** : le
   libelle d'une zone de `cp-plan-de-la-classe.yaml` mute en memoire, ou le nom du bouton mute dans
   le document servi - jamais une fixture ecrite de ma main, lecon de s05 rappelee par `ADR-0092`.

---

## Consequences

- **La sortie que `nadia-prof` accepte cesse d'etre rouge pour un motif qui n'est pas le sien.** Le
  lot de `S36-QLM11-PLANCLASSE` peut sortir le libelle du bouton sans negocier avec un garde-fou.
- **Le juge reste un sous-ensemble du calcul de nom accessible de HTML-AAM** : ni `title`, ni le
  texte alternatif d'une image imbriquee, ni un `<label>` associe ne sont resolus. C'est assume et
  ecrit : ce sont trois formes qu'aucun ecran de ce depot n'utilise sur un bouton. Le jour ou l'une
  d'elles apparait, cette clause ment - et c'est le declencheur d'un amendement, ecrit maintenant.
- **Deux pieces mobiles de plus** dans deux garde-fous : la resolution d'`aria-labelledby` et la
  lecture du libelle du paquet. La seconde ne coute pas un recensement de plus, elle reutilise
  celui qui existe.
- **Mauvaise consequence, nommee** : un vert sur ces deux clauses ne dit **rien** de la moitie de
  `ZON-15` qui compte pour l'enfant - savoir quel bouton porte quel libelle. Un ecran ou les six
  libelles sont corrects, resolus, et places de telle sorte que personne ne sache lequel va avec
  quel bouton, passe cette regle. `nadia-prof` reste le seul juge de cette moitie.
- **Si `ines-design` choisit finalement la seconde sortie** (le pave reduit dans sa borne de 4,8 px),
  la clause 1 n'interdit plus rien de ce qui se passe et la clause 2 continue de mordre. Ce cas est
  traite en *Ce qui me ferait changer d'avis*.

---

## Alternatives considerees

**a) Ne rien changer, et demander que le libelle reste dans la boite du bouton.** Ecartee par
l'arithmetique de la section 2 du contexte : 16,8 px disponibles contre 109 px demandes. Ce serait
demander a `nadia-prof` de renverser une condition qu'elle a mesuree sur six zones sur six.

**b) Amender `ADR-0017` pour laisser le pave de `.zone` descendre sous 1,1rem.** Ecartee avec son
chiffre : le gain total est de 4,8 px, et le deficit de 92 px. On affaiblirait une regle qui compare
deux boutons d'un meme ecran pour un gain qui ne resout rien.

**c) Implementer le calcul complet de nom accessible de HTML-AAM.** Ecartee pour aujourd'hui :
plus de pieces mobiles que de formes en jeu, et aucune des formes non couvertes n'existe sur un
bouton de ce depot. **Declencheur ecrit** : le jour ou un bouton de zone porte une image ou un
`title`, cette alternative redevient la bonne.

**d) Laisser le lot elargir le juge dans le diff d'implementation, sans `ADR`.** Ecartee : c'est
exactement le geste que ce depot sanctionne - un garde-fou dont le juge change sans decision ecrite.
Et le motif est plus fort ici qu'ailleurs : la clause **promettait deja** plus que ce qu'elle
jugeait, donc la corriger en silence laisserait la prochaine lecture croire que le nom accessible a
toujours ete verifie.

**e) Exiger l'egalite stricte entre le nom accessible et le libelle declare.** Ecartee : elle
refuserait par avance une forme legitime que `nadia-prof` n'a pas exclue - un numero de renvoi devant
le libelle - et produirait un faux rouge sur une proposition d'`ines-design` qui tient la propriete.
L'inclusion garde ce qui compte : le libelle du contenu est dans le nom.

---

## Ce qui me ferait changer d'avis

- **Si `nadia-prof` refuse toute sortie du libelle hors du bouton**, la clause 1 n'interdit plus rien
  de reel : elle devient decorative, et une clause decorative se **retire**, elle ne se garde pas par
  prudence. La clause 2, elle, reste utile dans tous les cas.
- **Si une seconde mecanique rend un bouton dont le nom vient d'ailleurs que de son texte**, les deux
  clauses cessent d'appartenir au garde-fou de designation et deviennent un critere transverse d'un
  ecran servi - et c'est alors un `ADR` de plus, pas un elargissement discret de celui-ci.
