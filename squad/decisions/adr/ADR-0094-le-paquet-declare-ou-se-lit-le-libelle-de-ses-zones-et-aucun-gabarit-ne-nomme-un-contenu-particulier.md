# ADR-0094 - Le paquet declare ou se lit le libelle de ses zones, et aucun gabarit ne nomme un contenu particulier

- **Statut** : **`Propose`** le 2026-09-21 (s36). Il ne passera `Accepte` que quand les quatre
  relais de sa decision 4 seront dans l'arbre **et** que sa regle executable y sera avec ses quatre
  morsures.
- **Sprint** : s36. **Date** : 2026-09-21. **Auteur** : `theo-archi`.
- **Route par** : la section 2.5 de
  `squad/sprints/s36-ouverture-ce1-mathematiques-seule-cm2-differe/proposition-ines-s36-barre-trois-puces-et-libelle-externe-qlm11.md`
  - *"Il manque donc une donnee au modele que le controleur transmet a `fragments/designation.html`
  [...] Je ne l'ecris pas : c'est une donnee de controleur, hors de mon mandat"*. Et par
  `S36-QLM11-PLANCLASSE` (`docs/backlog/backlog.md`, section "Sprint 36").
- **Prolonge** : `ADR-0004` (les deux contrats fermes, et la clause 3 qui autorise une mecanique a
  voir le contrat de contenu), `ADR-0006` (le contrat de contenu ne porte que du texte),
  `ADR-0069` (un fait de contenu se porte par un type, jamais par un booleen), `ADR-0080` (la
  mecanique de designation, et l'ordre declare des zones), `ADR-0092` (la lisibilite du libelle
  n'est pas gardable par une arithmetique), `ADR-0093` (le nom accessible resolu), `ADR-0016`
  (constructeur de commodite plutot qu'un site de construction retouche), `ADR-0005` (une
  abstraction existe dans le code ou elle est abandonnee par ecrit).
- **Ce que cet `ADR` ne fait pas** : il ne choisit **aucune forme**. Un numero, une legende, un
  renvoi, leur place et leur taille appartiennent a `ines-design` qui propose et a `nadia-prof` qui
  tranche. Il ne prononce aucun Done, ne ferme aucune ligne de matrice, ne touche aucun contenu
  pedagogique, n'amende ni `ZON-15` ni `ZON-16`, et ne rouvre ni `ADR-0017` ni `ADR-0050`.
- **Arbre mesure** : worktree `s36-theo`, ouvert depuis `s36-sofia` a `364ce0dd`. Les chiffres de
  la section *Regle executable* sont mesures sur cet arbre, avec leur methode ecrite a cote.

---

## Contexte

### 1. Le probleme, tel que la forme proposee le pose

`fragments/designation.html` est le gabarit **de la mecanique**, partage par tous les paquets de
designation. `cp-designation-frise-de-la-journee` est accorde par `nadia-prof` avec son libelle
**dans** la boite du bouton ; `cp-plan-de-la-classe` est refuse a s35 parce que le sien n'y tient
pas, avec un deficit d'environ 92 px sur la zone la plus contrainte (`ADR-0093`, contexte section
2).

Toute forme qui sort le libelle de la boite doit donc s'appliquer **a certains paquets et pas a
d'autres**. Et le gabarit ne peut pas savoir lesquels : il ne connait que ce que le modele lui
donne. Ecrire `th:if="${illustration == 'plan-classe'}"` reglerait la journee et couterait le
principe directeur du produit - une mecanique generique qui connait un contenu particulier n'est
plus generique, elle est un jeu deguise en mecanique.

`ines-design` a nomme ce manque sans l'ecrire, ce qui est exactement son mandat. Cet `ADR` l'ecrit.

### 2. Pourquoi ce n'est pas un booleen, et ce n'est pas une preference

La section 2.5 propose *"un booleen ou une classe CSS calculee en amont"*. **Le booleen est refuse
par un garde-fou deja dans l'arbre** : `DifficulteTest.leContratDeContenuNePorteQueDuTexte`
(`ADR-0006`) n'admet, sur un champ d'instance d'un type de `com.recreo.domain.content`, que
`java.lang.String`, les collections de `java.util`, les types du contrat lui-meme, et - depuis
l'exemption nommee du 2026-09-04 - le `int` d'un cadre en pourcentage. Un `boolean` n'est aucun des
quatre. C'est la meme porte qu'`ADR-0069` a franchie en remplacant `boolean formeExacteExigee` par
`ExigenceDeForme`, et sa morsure est dans l'arbre sous le nom `ModeDeSaisieEnBooleenContreExemple`.

La **classe CSS calculee en amont** est refusee pour un autre motif, qui est celui du mandat : une
classe CSS est une forme. La faire declarer par un paquet de contenu ferait choisir la forme par
l'auteur du contenu, et rendrait le renommage d'une classe de feuille impossible sans toucher les
paquets.

### 3. Le precedent existe, et il est integralement reutilisable

`ExigenceDeForme` traverse deja exactement le meme chemin, du YAML jusqu'a une decision d'affichage
dans `PartieController` : cle facultative au niveau du paquet, defaut qui reproduit le comportement
d'avant, valeur inconnue qui **arrete le chargement** en nommant la cle et la valeur, type porte
par le record d'item, expose par la session, converti en decision de vue par l'adapter. Rien de ce
qui suit n'est invente ; tout est la transposition d'un chemin qui sert deja en production.

---

## Decision

### 1. Le paquet declare **ou se lit** le libelle de ses zones

Cle facultative, **au niveau du paquet** et a ce niveau seulement :

```yaml
emplacementDuLibelleDeZone: hors-de-la-boite
```

Deux valeurs, et rien d'autre : `dans-la-boite`, `hors-de-la-boite`. **Absente, elle vaut
`dans-la-boite`** - c'est-a-dire le comportement d'aujourd'hui, au caractere pres dans le document
rendu. Aucun paquet existant n'est touche, et aucun paquet futur ne remplit une valeur factice
(dette 2 de `CLAUDE.md`). Une valeur ecrite mais inconnue **arrete le chargement** en nommant la
cle et la valeur, jamais un repli silencieux sur le defaut : une faute de frappe rendrait sinon un
paquet dans une forme que son auteur croit avoir changee, et personne ne le verrait.

**Pas de surcharge au niveau de l'item**, a la difference d'`exigenceDeForme`. Aucun paquet n'en
demande : sur les deux paquets de designation servis, tous les items d'un paquet partagent la meme
illustration et les memes cadres. Une surcharge par item serait une abstraction ecrite pour un
usage imagine. **Ce qui me ferait changer d'avis est ecrit en bas de cet `ADR`.**

### 2. Le type est un enum du contrat de contenu

```java
package com.recreo.domain.content;

/**
 * Ou se lit le libelle d'une zone designable : dans la boite du bouton, ou hors d'elle.
 * ADR-0094. Un fait declare par le paquet, jamais une forme - ou le libelle est ECRIT a l'ecran,
 * s'il y a une legende, un numero ou un renvoi, appartient a ines-design et a nadia-prof.
 * ADR-0069 : un type, jamais un booleen - leContratDeContenuNePorteQueDuTexte le refuserait.
 */
public enum EmplacementDuLibelleDeZone {

    /** Le libelle est le texte du bouton de la zone. Defaut de tout paquet qui ne declare rien. */
    DANS_LA_BOITE,

    /** Le libelle se lit ailleurs sur l'ecran ; le bouton ne le porte pas comme texte. */
    HORS_DE_LA_BOITE
}
```

**Le nom dit ou le libelle se lit, jamais comment l'ecran s'y prend** - meme discipline que la
Javadoc d'`ExigenceDeForme` (*"le nom dit ce que l'exercice exige, jamais comment une mecanique s'y
prend pour comparer"*) et meme garde-fou de nommage qu'`ADR-0015`. `HORS_DE_LA_BOITE` ne promet ni
legende, ni numero, ni classe CSS : il dit que le bouton ne porte pas son libelle comme texte, ce
qui est exactement le fait dont le gabarit a besoin.

### 3. Ce fait appartient au contenu, et pas au systeme

Le systeme **ne le calcule pas**. Il serait tentant de deriver *"le libelle ne tient pas dans le
cadre"* d'une arithmetique sur `CadreDeZone` et la longueur du libelle : `ADR-0092` decision 2 a
deja refuse cette derivation, et son motif vaut ici sans un mot de changement - la police servie
est celle de l'appareil, aucune avance par caractere n'existe dans ce depot, et un seuil epingle
serait un **faux vert** sur toute machine plus large que celle de mesure.

C'est donc une **declaration**, portee par qui a regarde le dessin. Sa justesse est jugee par l'oeil
de `nadia-prof`, comme `ZON-15`, et cet `ADR` ne pretend pas l'encoder.

### 4. Les quatre relais, et aucun de plus

1. **`adapters/adapter-content/.../DesignationContentSourceFactory`** lit la cle sur le corps du
   paquet, applique le defaut, refuse une valeur inconnue. Patron `exigenceDe` de
   `SaisieContentSourceFactory`, recopie et non reinvente. La valeur du paquet est posee sur
   **chaque** item construit - meme geste que `exigenceDuPaquet` sur `ReponseAEcrire`.
2. **`domain/content/ZoneADesigner`** recoit une composante
   `EmplacementDuLibelleDeZone emplacementDuLibelleDeZone`. **Un constructeur de commodite
   (`ADR-0016`) conserve l'arite actuelle** et remplit `DANS_LA_BOITE` : aucun site de construction
   existant n'est retouche, ce qui est la dette 6 de `CLAUDE.md` evitee a la source.
3. **`domain/mechanics/designation/DesignationSession`** expose
   `Optional<EmplacementDuLibelleDeZone> emplacementDuLibelleDeZoneManche()`, sur le patron exact de
   `illustrationManche()`. **Transport strict** : la mecanique ne lit pas cette valeur, ne s'en sert
   pour aucun verdict, ne change aucune boucle de partie. Precedent : `SaisieSession
   .exigenceDeFormeManche()`, qui fait deja voyager un enum de contenu vers l'adapter sans que la
   mecanique en decide quoi que ce soit.
4. **`adapters/adapter-web/.../PartieController.mancheDeDesignation`** convertit l'enum en **un
   seul attribut de modele booleen**, sur le patron de `chaineJugeeMontreeALaReussite` :

   ```java
   modele.addAttribute("libelleDeZoneHorsDeLaBoite",
           session.emplacementDuLibelleDeZoneManche()
                   .orElse(EmplacementDuLibelleDeZone.DANS_LA_BOITE)
                   == EmplacementDuLibelleDeZone.HORS_DE_LA_BOITE);
   ```

**L'enum n'entre jamais dans le modele, donc jamais dans le gabarit.** Le motif n'est pas
esthetique : une expression Thymeleaf qui comparerait `${emplacement} == 'HORS_DE_LA_BOITE'`
ecrirait un jeton du contrat de contenu dans une vue, le comparerait par chaine, et resterait verte
le jour ou l'enum serait renomme. Le booleen de modele, lui, est un **fait de vue** calcule par la
seule couche qui a le droit de connaitre les deux cotes.

**Aucun port n'est ajoute**, ni entrant ni sortant : la valeur voyage dans la session, qui traverse
deja `application` par `GameSession`. **`application` n'est pas touche.**

### 5. Le nom accessible est rendu dans les deux formes, et il ne depend pas de la forme

`aria-label` porte **toujours** `zone.libelle()`, que le libelle soit dans la boite ou non. Seul le
noeud de texte du bouton est conditionnel.

Ce choix a un prix - un attribut de plus sur un ecran deja accorde
(`cp-designation-frise-de-la-journee`) - et un gain qui le paie : sous le calcul reel du nom
accessible, `aria-label` l'emporte sur le texte du sous-arbre, donc **le nom annonce est le libelle
du paquet sur les deux formes, par construction**, et la clause 2 d'`ADR-0093` est tenue sans
dependre de la forme choisie. Le document rendu pour `frise-de-la-journee` change d'un attribut ;
le nom annonce a l'enfant ne change pas d'un caractere, les deux chaines etant la meme.

### 6. Ce que le gabarit n'a pas le droit de faire, et c'est la regle executable

**Aucun gabarit et aucune regle de la feuille maison ne nomme litteralement un jeu, un paquet ou une
illustration du catalogue.** C'est la propriete que la section 2.5 demandait de tenir, et c'est la
seule de cet `ADR` qui interdise quelque chose qu'on aurait envie de faire.

---

## Regle executable

**Ce n'est pas une `ArchRule`** : les objets juges sont des fichiers de gabarit, une feuille de
style et des paquets de contenu charges, pas des classes. **Aucune `ArchRule` n'est ajoutee par cet
`ADR`** ; le compte du depot reste celui du dernier comptage mesure, **33 au 2026-09-11** (methode :
motif `static final ArchRule` sur les arbres de sources, `/target/` exclu et le faux module de
contre-exemple exclu -
`squad/sprints/s35-fermer-ce-qui-n-a-pas-recu-le-go-pedagogique/confirmation-archunit.md`). **Je ne
l'ai pas recompte aujourd'hui.**

### Le garde-fou

`bootstrap/src/test/java/com/recreo/archi/AucunGabaritNiFeuilleMaisonNeNommeUnContenuParticulierTest.java`

`bootstrap` est le seul module qui voie a la fois `adapter-content` et `adapter-web` - meme motif
que pour la clause 2 d'`ADR-0093`.

**Perimetre derive, jamais enumere** (`ADR-0013`). Les identifiants juges sont lus sur le contenu
servi, par le recensement **existant**
(`AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest.repertoiresDeContenu`, deja reutilise par les
deux cliquets de designation - un second recensement du meme objet divergerait un jour) :

- l'`id` de chaque entree de `catalogue.yaml`, et son `paquet` ;
- l'`id` de chaque paquet servi ;
- la valeur `illustration` de chaque item de chaque paquet servi.

Le materiau juge est l'ensemble des `.html` de
`adapters/adapter-web/src/main/resources/templates/`, la feuille
`adapters/adapter-web/src/main/resources/static/css/recreo.css`, et les `.java` de
`adapters/adapter-web/src/main/java/`.

**Les commentaires sont exclus, et ce n'est pas une commodite : c'est une mesure.** Sur l'arbre de
ce jour, **175 identifiants derives, 17 fichiers dans le perimetre, 15 occurrences litterales - et
les 15 sont dans des commentaires**, toutes des citations de fiches de conformite
(`FICHE-bandit-manchot-additions-cp.md` porte l'identifiant de jeu `bandit-manchot-additions-cp`).
**Hors commentaire : 0.** Un juge naif par sous-chaine serait donc **rouge a sa premiere execution,
pour quinze motifs tous legitimes** - c'est exactement la lecon de s05, le `!important` cherche par
sous-chaine dans un nom de variable, et elle a ete payee ici en mesurant avant d'ecrire plutot
qu'en fabriquant une fixture. Le juge analyse donc le gabarit par Jsoup et n'inspecte que les noeuds
de texte, les noms d'attributs et les valeurs d'attributs ; il retire les `/* ... */` de la feuille
et les commentaires de bloc et de ligne du Java.

### Ce que la regle interdit reellement

- `th:if="${illustration == 'plan-classe'}"` dans le fragment generique de designation ;
- `th:classappend="${jeuId == 'designation-plan-de-la-classe'} ? 'legende-externe' : ''"` ;
- `.illustration-plan-classe { ... }` ou toute regle de feuille dont le selecteur porte
  l'identifiant d'un jeu, d'un paquet ou d'une illustration ;
- `if (jeuId.equals("designation-plan-de-la-classe"))` dans `PartieController`, qui est le meme
  defaut d'une couche plus haut.

Ce qu'elle **ne peut pas** interdire, et c'est ecrit : un contournement qui n'emploie pas
l'identifiant - un paquet reconnu par le nombre de ses zones, par son texte alternatif, par son
niveau. Le perimetre de cette regle est la **forme litterale** du couplage, pas toutes ses formes
possibles. Elle ferme celle qui se presente, qui est aussi la seule qu'on ecrit sans y penser.

### Les quatre morsures dues (`ADR-0013`, obligation 3)

1. **Rouge** quand `fragments/designation.html` recoit
   `th:if="${illustration == 'plan-classe'}"`, `plan-classe` etant **lu dans
   `cp-plan-de-la-classe.yaml`** et jamais ecrit dans le test.
2. **Rouge** quand `recreo.css` recoit une regle dont le selecteur porte l'identifiant d'un jeu
   servi.
3. **Rouge** quand `PartieController` compare `jeuId` a l'identifiant litteral d'un jeu servi.
4. **La population est non vide, des deux cotes** (`ADR-0003`) : le nombre d'identifiants derives
   et le nombre de fichiers du perimetre sont tous deux assertes non nuls. Sans cela, un
   recensement qui ne lit rien serait vert par accident - et c'est le mode de defaillance le plus
   probable de ce garde-fou, puisqu'il lit des fichiers par leur chemin.

### Ce qui est deja garde par des regles existantes, et que je n'ajoute donc pas

Une regle ecrite ici serait decorative :

- **le type n'est pas un booleen** : `DifficulteTest.leContratDeContenuNePorteQueDuTexte`, morsure
  `ModeDeSaisieEnBooleenContreExemple` ;
- **le contrat de contenu reste ferme** : `DecouplageMecaniqueContenuTest.leContratDeContenuEstFerme`
  (un enum y est admis) ;
- **la mecanique ne voit que les contrats** :
  `DecouplageMecaniqueContenuTest.uneMecaniqueNeVoitQueLesContrats`, dont la liste blanche contient
  deja `com.recreo.domain.content` ; et `uneMecaniqueIgnoreLaMatiere`, que cette valeur ne concerne
  pas, `EmplacementDuLibelleDeZone` n'etant pas `ContentDescriptor` ;
- **la cle YAML est lue et non ignoree** :
  `AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest` fait echouer le build tant qu'aucune source
  ne nomme `emplacementDuLibelleDeZone` - la cle et son lecteur atterrissent donc dans le meme diff,
  ce qui est exactement la garantie voulue ;
- **aucune fuite de framework** : `PureteDesCouchesTest` et `ArchitectureEnCouchesTest`. L'enum est
  un type du JDK et du domaine, la conversion en booleen de modele vit dans `adapter-web`, et
  `application` n'est pas touche.

---

## Consequences

- **Le gabarit de designation reste generique, et la propriete est executable** plutot que
  recommandee. Un paquet futur dont les cadres sont trop etroits declare la meme cle et ne demande
  aucune ligne de code.
- **Trois pieces mobiles de plus** : un enum, une composante de record, un accesseur de session. Le
  quatrieme relais (le booleen de modele) n'est pas une piece mobile, c'est une ligne.
- **Mauvaise consequence, nommee** : la justesse de la declaration n'est gardee par rien. Un paquet
  peut declarer `hors-de-la-boite` sans en avoir besoin, ou `dans-la-boite` avec des cadres ou le
  libelle deborde - et la CI reste verte. C'est le meme angle mort que `ZON-15`, pour la meme cause
  (`ADR-0092` decision 2), et il reste tenu par l'oeil de `nadia-prof`. **Un vert sur cet `ADR`
  n'est pas un Done pedagogique.**
- **Seconde mauvaise consequence** : `frise-de-la-journee`, deja accorde, recoit un `aria-label`
  qu'il n'avait pas. Le nom annonce ne change pas (meme chaine), mais le document rendu change, et
  un ecran deja valide est donc touche par un diff qu'il n'a pas demande. Je le nomme plutot que de
  le glisser : `nadia-prof` en est informee, et si elle refuse, la section Decision 5 se replie sur
  un `aria-label` conditionnel, au prix d'une seconde condition dans le gabarit.
- **Un cout de forme que cet `ADR` ne paie pas, et qui doit etre su** : la numerotation par compteur
  CSS proposee en section 2.2 de la proposition d'`ines-design` est **refusee par un garde-fou deja
  dans l'arbre** - clause 7 de `LAnimationRetardeMaisNeDetermineRienTest` (`ADR-0027`), qui rougit
  sur toute regle maison declarant `content` avec une valeur autre que `""`, `''` ou `none`, y
  compris sur un pseudo-element. `content: counter(numero-zone)` en est une. Le motif de cette
  clause est pedagogique et date (`nadia-prof`, arbitrage du 2026-08-10) : *ce que l'enfant lit
  vient du serveur, jamais d'une feuille de style*. **Un numero de repere doit donc etre rendu par
  le serveur**, ce qui est possible sans donnee neuve - l'ordre des zones est deja celui que le
  contenu declare (`ADR-0080` decision 3), donc le rang d'iteration du gabarit suffit. Le choix de
  montrer un numero, et sa forme, restent a `ines-design` et a `nadia-prof` ; ce qui est ferme, et
  ferme par un garde-fou et non par moi, c'est qu'il ne vienne pas de la feuille.

---

## Alternatives considerees

**a) Ne rien porter par le contenu, et faire choisir le gabarit sur l'identifiant du jeu.** Ecartee :
c'est la definition du couplage que le produit interdit, et la regle executable de cet `ADR` la
rend rouge. Elle merite d'etre nommee parce qu'elle est la moins chere a ecrire et la seule qu'on
ecrit par reflexe.

**b) Porter le fait par l'entree de catalogue plutot que par le paquet.** Ecartee : le catalogue
declare ce qu'est une partie (titre, consigne, mecanique, nombre de manches), pas ce qu'est un
dessin. Deux entrees de catalogue peuvent servir le meme paquet ; elles auraient alors le droit de
se contredire sur une propriete du dessin, et rien ne les en empecherait.

**c) Porter le fait sur `Zone` plutot que sur l'item.** Ecartee : une zone dont le libelle est dans
la boite a cote d'une zone dont le libelle est dehors, sur le meme dessin, est un ecran que
personne ne demande et que la forme proposee ne sait pas rendre (une legende numerotee suppose que
toutes les zones y soient). Ce serait un degre de liberte offert a un usage imagine.

**d) Deriver le fait d'une arithmetique sur `CadreDeZone` et la longueur du libelle.** Ecartee par
`ADR-0092` decision 2, et pas par preference : la police servie est celle de l'appareil, et le seuil
serait un faux vert sur toute machine plus large que celle de mesure.

**e) Un enum de forme (`AVEC_LEGENDE_NUMEROTEE`, ...) plutot qu'un enum de fait.** Ecartee : il
ferait choisir la forme par l'auteur du contenu, et il gelerait dans les paquets une decision qui
appartient a `nadia-prof`. Le jour ou la forme change, il faudrait editer les paquets.

**f) Ne pas ecrire d'`ADR` et laisser le lot d'implementation poser la donnee.** Ecartee pour le
motif habituel de ce depot, avec une aggravation : la donnee traverse **quatre couches**, et la
question *"pourquoi le domaine porte-t-il une valeur que seul le gabarit lit ?"* se reposera. Sans
cet `ADR`, la reponse serait perdue, et le raccourci de l'alternative (a) serait reintroduit comme
une simplification.

---

## Ce qui me ferait changer d'avis

- **Si un paquet de designation demande un jour deux dessins de geometrie differente dans le meme
  paquet**, la surcharge au niveau de l'item cesse d'etre un usage imagine et devient un usage
  constate : la cle est alors lue aux deux niveaux, celle de l'item l'emportant, exactement comme
  `exigenceDeForme`. Le patron est deja ecrit, il n'y a rien a concevoir ce jour-la.
- **Si `nadia-prof` refuse toute sortie du libelle hors du bouton**, cet `ADR` entier n'a plus
  d'objet : l'enum n'aurait qu'une valeur utilisee, et une enumeration a une valeur est une
  abstraction fantome. Elle se **retire** (`ADR-0005`), elle ne se garde pas par prudence. La regle
  executable de la section 6, elle, survit dans tous les cas : elle ne depend d'aucune forme et
  garde le principe directeur du produit.
- **Si une seconde mecanique porte un jour un fait de contenu du meme genre** (un contenu qui dit
  ou se lit quelque chose plutot que ce qu'il dit), le patron des quatre relais cesse d'etre propre
  a la designation et devient un critere transverse - et c'est alors un `ADR` de plus, pas un
  elargissement discret de celui-ci.
