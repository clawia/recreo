# ADR-0094 - Le paquet declare ou se lit le libelle de ses zones, et aucun gabarit ne nomme un contenu particulier

- **Statut** : **`Accepte`** le 2026-09-21 (s36), apres deux etapes ecrites le meme jour.
  `Propose` a la redaction : *"il ne passera `Accepte` que quand les quatre relais de sa decision 4
  seront dans l'arbre et que sa regle executable y sera avec ses quatre morsures"*. **Maintenu
  `Propose` a la confirmation de la porte technique** : les quatre relais etaient dans l'arbre et
  corrects, **la regle executable n'y etait pas**. `Accepte` maintenant que les deux conditions
  qu'il s'etait posees sont tenues - la regle de la decision 6 est dans l'arbre avec ses morsures,
  et l'ecart de la decision 5 est tranche. Voir *Etat de l'acceptation* en fin de document.
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
- **Arbres mesures** : worktree `s36-theo`, ouvert depuis `s36-sofia` a `364ce0dd`, pour la
  redaction ; worktree `s36-theo-regle`, ouvert depuis `s36-sofia` a `5d7f2549`, pour l'ecriture de
  la regle et l'acceptation. Chaque chiffre porte sa methode dans le meme paragraphe que lui, et
  nomme celui des deux arbres sur lequel il a ete mesure.

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

### 5. Le nom accessible porte le libelle du paquet dans les deux formes

*Cette decision est amendee le 2026-09-21, a l'acceptation, par l'auteur de sa premiere redaction.
Sa forme d'origine est conservee sous l'amendement, parce que l'ecart qu'elle a ouvert est le fait
le plus instructif de cet `ADR`.*

**Ce qui est tenu est le nom accessible du bouton de zone, jamais la presence d'un attribut.** Il
vaut le libelle que le paquet declare, dans les deux formes, et les deux formes l'obtiennent
differemment : quand le bouton porte son libelle comme texte (`dans-la-boite`), le nom accessible
**est** ce texte et aucun attribut n'est ajoute ; quand il ne le porte pas (`hors-de-la-boite`),
`aria-label` porte `zone.libelle()`. Le gabarit ecrit donc
`aria-label=${libelleDeZoneHorsDeLaBoite} ? ${zone.libelle()} : null`.

**La regle qui tient cette decision existe deja et n'est pas ecrite pour l'occasion** : la clause 2
d'`ADR-0093`, qui resout le nom accessible (`aria-labelledby`, sinon `aria-label`, sinon le texte)
sur le **document reellement servi** de **chaque** jeu de designation du catalogue reel, et le
compare au libelle que le paquet declare pour la zone dont le bouton porte l'identifiant. Elle
couvre les deux branches de la condition, parce qu'elle juge le document et non le gabarit, et sa
morsure 4 mute un libelle declare du paquet `hors-de-la-boite` et exige le rouge.

**Pourquoi la forme inconditionnelle est ecartee, et ce n'est pas parce que le code l'etait.** Sa
justification tenait en trois mots - *"par construction"* - c'est-a-dire la forme de preuve que ce
depot a deja disqualifiee deux fois au profit de la mesure de l'artefact reel (`ADR-0071` sur la
version d'un JDK, `ADR-0092` sur la largeur d'un libelle). Ce que *"par construction"* promettait,
un garde-fou le verifie sur le document servi, ce qui est strictement plus fort : il resterait rouge
le jour ou le texte du bouton cesserait d'etre le libelle. Le prix de la forme inconditionnelle,
lui, est reel - un attribut de plus sur `cp-designation-frise-de-la-journee`, ecran deja accorde par
`nadia-prof`, pour zero changement du nom annonce.

**Ce qui n'est pas le motif de cet amendement, et qui doit etre dit** : le repli conditionnel etait
ecrit dans la *Seconde mauvaise consequence* comme la reponse a un refus de `nadia-prof` qui **n'a
pas eu lieu** - aucun verdict de ce genre n'existe dans `squad/` pour s36. Le declencheur ecrit
n'est donc pas survenu, et cet amendement ne s'en reclame pas. Il tranche sur le fond, et il perd
quelque chose qu'il nomme : l'independance du nom accessible vis-a-vis du texte visible n'est plus
obtenue par construction, elle est obtenue par un garde-fou. **Ce qui me ferait revenir a la forme
inconditionnelle** : que la clause 2 d'`ADR-0093` cesse de s'executer sur le document servi de
chaque jeu de designation, ou qu'une forme rende le texte du bouton different du libelle sans le
declarer `hors-de-la-boite`.

*Forme d'origine, 2026-09-21 : "`aria-label` porte **toujours** `zone.libelle()`, que le libelle
soit dans la boite ou non. Seul le noeud de texte du bouton est conditionnel."* Elle a ete
implementee sous sa forme de repli sans que le declencheur du repli soit survenu, et l'ecart a
vecu de l'implementation a la confirmation de porte technique - une journee, parce qu'une
confirmation de porte a relu la decision et le gabarit cote a cote. C'est le mode de defaillance
que cet `ADR` a lui-meme nomme ailleurs : un texte et un code qui disent deux choses differentes,
et rien d'executable entre les deux pour le dire. Ici, il n'y en avait rien parce que **la decision
5 n'etait pas encodable en l'etat** : elle nommait un attribut, pas une propriete. L'amendement la
reecrit en propriete, et la propriete a sa regle.

### 6. Ce que le gabarit n'a pas le droit de faire, et c'est la regle executable

**Aucun gabarit et aucune regle de la feuille maison ne nomme litteralement un jeu, un paquet ou une
illustration du catalogue.** C'est la propriete que la section 2.5 demandait de tenir, et c'est la
seule de cet `ADR` qui interdise quelque chose qu'on aurait envie de faire.

*Amendement du 2026-09-21, a l'ecriture de la regle.* Le perimetre est **toute la production des
modules declares**, et pas seulement les gabarits et la feuille : les scripts maison
(`static/js/`) et les `.java` de production des cinq modules y entrent. Motif mesure, pas presume :
l'extension est **gratuite** - 175 identifiants derives, 69 fichiers, 12 occurrences litterales
toutes en commentaire, **0 hors commentaire** - et strictement plus forte, un identifiant de jeu
cable dans `domain` etant le meme defaut une couche plus bas. La feuille et le JavaScript tiers
(`static/vendor/`) restent hors perimetre : ils ne sont pas ecrits ici, et `ADR-0046` les garde
autrement.

---

## Regle executable

**Ce n'est pas une `ArchRule`** : les objets juges sont des fichiers de gabarit, une feuille de
style et des paquets de contenu charges, pas des classes. **Aucune `ArchRule` n'est ajoutee par cet
`ADR`** ; le compte du depot reste celui du dernier comptage mesure, **33 au 2026-09-11**
(`squad/sprints/s35-fermer-ce-qui-n-a-pas-recu-le-go-pedagogique/confirmation-archunit.md`). **Je
ne l'ai pas recompte aujourd'hui.**

*Correction du 2026-09-21, a la confirmation de la porte technique, par l'auteur de la phrase
ci-dessus.* La methode donnee pour le **33** - *"motif `static final ArchRule` [...], `/target/`
exclu et le faux module de contre-exemple exclu"* - **n'est pas celle qui produit 33** : le 33 de
s35 compte les occurrences de l'annotation `@ArchTest`. Recompte le 2026-09-21 sur `s36-sofia` a
`1d361786` : `@ArchTest` = **33**, inchange par s36 ; `static final ArchRule` moins le faux module
= **34**, l'ecart de 1 etant une `ArchRule` evaluee par des `@Test` ordinaires
(`ScanDeComposantsTest`, dans l'arbre depuis s06). Le detail et les deux comptes sont en section 2
de `squad/sprints/s36-ouverture-ce1-mathematiques-seule-cm2-differe/confirmation-archunit.md`.

### Le garde-fou

`bootstrap/src/test/java/com/recreo/archi/AucunGabaritNiFeuilleMaisonNeNommeUnContenuParticulierTest.java`

`bootstrap` est le seul module qui voie a la fois `adapter-content` et `adapter-web` - meme motif
que pour la clause 2 d'`ADR-0093`.

**Perimetre derive, jamais enumere** (`ADR-0013`). Les modules viennent du pom agregateur, par
`LesGardeFousSontMisALEpreuveTest.modulesDeclares` - deux notions de *"quels modules existent"*
divergeraient un jour. Les identifiants juges sont lus sur le contenu servi lui-meme, par
SnakeYAML, la bibliotheque avec laquelle le chargeur de production lit ces memes fichiers :

- l'`id` de chaque entree de catalogue, et son `paquet` ;
- l'`id` de chaque paquet servi ;
- la valeur `illustration` de chaque item de chaque paquet servi.

*Ecart assume avec la redaction du 2026-09-21 matin, qui annoncait reutiliser
`AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest.repertoiresDeContenu`.* Ce recensement-la ne
rend que les repertoires `contenu/` ; **le catalogue n'y est pas** - il vit un cran au-dessus, a la
racine des ressources du module - et l'avoir reutilise tel quel aurait donne un garde-fou qui ne
derive **aucun identifiant de jeu**, c'est-a-dire vert sur `jeuId == 'designation-plan-de-la-classe'`,
l'exemple meme que cet `ADR` cite comme interdit. La derivation retenue balaie la racine de
ressources de production de chaque module declare, dont `contenu/` est un sous-arbre : elle est un
**sur-ensemble** de celle qui etait annoncee, elle partage avec elle la seule source de *"quels
modules existent"*, et le catalogue s'y reconnait **a sa forme** (une cle `jeux` portant une liste)
plutot qu'a son nom de fichier - le jour ou il demenage, le garde-fou le suit au lieu de juger sur
zero identifiant de jeu.

Le materiau juge est, pour chaque module declare : les `.html` de `src/main/resources/templates/`,
les `.css` de `src/main/resources/static/css/`, les `.js` de `src/main/resources/static/js/` et les
`.java` de `src/main/java/`.

**Les commentaires sont exclus, et ce n'est pas une commodite : c'est une mesure.** Sur l'arbre de
ce jour, perimetre etendu compris : **175 identifiants derives, 69 fichiers dans le perimetre, 12
occurrences litterales - et les 12 sont dans des commentaires**, citations de fiches de conformite
ou de paquets d'exemple (`AdditionsBorneesContentSource` cite `bandit-manchot-additions-cp`,
`recreo.css` en cite sept dans ses commentaires de section). **Hors commentaire : 0.** Un
juge naif par sous-chaine serait donc **rouge a sa premiere execution, pour douze motifs tous
legitimes** - c'est exactement la lecon de s05, le `!important` cherche par sous-chaine dans un nom
de variable, et elle a ete payee ici en mesurant sur le materiau reel avant d'ecrire plutot qu'en
fabriquant une fixture. Le juge analyse donc le gabarit par Jsoup et n'inspecte que les noeuds de
texte, les noms d'attributs et les valeurs d'attributs ; il retire les `/* ... */` de la feuille et
les commentaires de bloc et de ligne du Java et du script - par un automate a etats, jamais par une
expression reguliere, pour le motif deja mesure dans
`AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest` : une adresse `http://` dans une chaine ferait
disparaitre la fin de sa ligne, et le garde-fou deviendrait vert sur un identifiant ecrit juste
apres.

*Methode des deux chiffres, dans le meme paragraphe qu'eux.* **175** : identifiants distincts
derives de tous les `.yaml` sous `src/main/resources` des cinq modules declares, un identifiant
n'etant retenu qu'une fois, sous le genre du premier fichier qui le nomme - 74 identifiants de jeu,
74 paquets nommes par une entree de catalogue, 27 illustrations d'items ; le genre *"paquet servi
qu'aucune entree ne reference"* est vide ce jour. **69** : fichiers du materiau juge sur le meme
arbre - 7 gabarits, 1 feuille maison, 1 script maison, 60 `.java` de production. Mesure sur le worktree
`s36-theo-regle`, ouvert depuis `s36-sofia` a `5d7f2549`, `target/` hors perimetre par construction
(le balayage part de `src/main/`), tests de tous les modules hors perimetre.

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

### Les quatre morsures dues (`ADR-0013`, obligation 3), et les trois qui s'y sont ajoutees

Les quatre annoncees, ecrites et executees le 2026-09-21. Aucun identifiant n'est ecrit dans le
fichier de test, pas meme dans une morsure : chacune lit le sien sur le contenu servi, donc le jour
ou un paquet est renomme, les morsures jugent le nom neuf sans qu'on les touche.

1. **Rouge** quand un gabarit reel recoit `th:if="${illustration == '...'}"`, l'illustration etant
   **lue dans un paquet servi**
   (`unGabaritQuiNommeUneIllustrationServieFaitEchouerLeGardeFou`).
2. **Rouge** quand la feuille maison recoit une regle dont le selecteur porte l'identifiant d'un jeu
   servi, lu dans le catalogue reel
   (`uneFeuilleMaisonDontLeSelecteurPorteUnJeuServiFaitEchouerLeGardeFou`).
3. **Rouge** quand une source de production compare un identifiant de jeu servi a un litteral
   (`uneSourceDeProductionQuiCompareUnJeuServiAUnLitteralFaitEchouerLeGardeFou`).
4. **La population est non vide, des deux cotes** (`ADR-0003`), et **les deux clauses mordent**
   (`unePopulationVideDUnCoteOuDeLAutreFaitEchouerLeGardeFou`). Sans cela, un recensement qui ne lit
   rien serait vert par accident - et c'est le mode de defaillance le plus probable de ce garde-fou,
   puisqu'il lit des fichiers par leur chemin.

Les morsures 1 a 3 mutent le materiau reel **en memoire** : la population de production est lue sur
disque, une source y est remplacee par elle-meme plus le texte fautif, et le juge - pur, il ne lit
ni disque ni classpath - doit nommer le chemin mute et l'identifiant. Rien n'est ecrit sur disque,
donc rien ne pourrit.

Trois morsures s'y sont ajoutees a l'ecriture, qu'aucune des quatre ne couvrait :

5. **Le jeu fautif commite** (`ADR-0013`, obligation 3) prouve la chaine disque -> SnakeYAML ->
   Jsoup -> juge, que les mutations en memoire ne prouvent pas. Son gabarit nomme une illustration
   dans un `th:if` et un paquet **dans un commentaire** ; sa feuille nomme un jeu dans son
   selecteur. Le message doit nommer les deux premiers et **jamais** le paquet cite en commentaire -
   sans quoi ce garde-fou serait la faute de s05 recommencee
   (`leJeuFautifCommiteEstRefuseEtSonCommentaireNeLEstPas`).
6. **Le corrige du meme jeu fautif est vert** (`leCorrigeDuMemeJeuFautifEstVert`). Une morsure qui
   ne montre que le rouge ne dit pas si le juge sait dire non : elle dit qu'il dit oui a tout.
7. **Les genres d'identifiants sur lesquels les morsures s'appuient sont derives du contenu servi.**
   Sans cette clause, un genre devenu vide ferait lever les morsures 1 a 3 au lieu de les faire
   juger. Elle a mordu a l'ecriture : ecrite d'abord sur les **quatre** genres, elle a echoue, parce
   qu'un identifiant n'est retenu qu'une fois et que le catalogue est lu avant les paquets - les 74
   paquets servis sont retenus comme *"paquet d'une entree de catalogue"*, et le genre *"paquet
   servi qu'aucune entree ne reference"* est vide. Il n'est pas retire pour autant : un paquet
   retire du catalogue mais laisse sur disque reste juge, ce que `ChaquePaquetPorteUnIndiceTest` a
   etabli en s05.

**Une huitieme epreuve, hors suite parce qu'elle mute le disque.** Les mutations en memoire prouvent
le juge ; elles ne prouvent pas que le garde-fou tel qu'il tourne en CI voit un vrai fichier fautif.
`th:if="${illustration == 'plan-classe'}"` a donc ete ecrit **dans
`fragments/designation.html` sur disque**, la suite lancee - rouge, avec le message nommant le
fichier, `plan-classe`, et son origine `cp-plan-de-la-classe.yaml` - puis le fichier restaure par
`git checkout --` et la suite relancee - verte, 8 tests. Le resultat est reporte dans
`squad/sprints/s36-ouverture-ce1-mathematiques-seule-cm2-differe/confirmation-archunit.md`.

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
- ~~**Seconde mauvaise consequence** : `frise-de-la-journee`, deja accorde, recoit un `aria-label`
  qu'il n'avait pas. Le nom annonce ne change pas (meme chaine), mais le document rendu change, et
  un ecran deja valide est donc touche par un diff qu'il n'a pas demande. Je le nomme plutot que de
  le glisser : `nadia-prof` en est informee, et si elle refuse, la section Decision 5 se replie sur
  un `aria-label` conditionnel, au prix d'une seconde condition dans le gabarit.~~
  **Consequence eteinte le 2026-09-21 par l'amendement de la decision 5** : le document rendu de
  `frise-de-la-journee` est inchange au caractere pres, aucun ecran accorde n'est touche. Le prix
  paye a la place est celui qui etait annonce ici : une seconde condition dans le gabarit, et un nom
  accessible tenu par un garde-fou (clause 2 d'`ADR-0093`, sur le document servi) plutot que par
  construction. Barree et non effacee : c'est cette phrase qui a arme le repli, et elle documente
  comment un repli prevu pour un declencheur peut etre implemente sans lui.
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

## Etat de l'acceptation - 2026-09-21, confirmation de la porte technique de s36

Verifie sur l'arbre fusionne `s36-sofia` a `1d361786`, worktree dedie `s36-theo-porte`.

### Ce qui est dans l'arbre : les quatre relais, tous les quatre

| Relais (decision 4) | Etat |
|---|---|
| 1. `DesignationContentSourceFactory` | **present** : cle lue au niveau du paquet, defaut `DANS_LA_BOITE`, valeur inconnue qui arrete le chargement en nommant la cle et la valeur, valeur posee sur chaque item |
| 2. `ZoneADesigner` | **present** : composante ajoutee, constructeur de commodite conservant l'arite (`ADR-0016`), aucun site de construction retouche |
| 3. `DesignationSession.emplacementDuLibelleDeZoneManche()` | **present** : transport strict, aucune lecture par la mecanique, aucun verdict change |
| 4. `PartieController` | **present** : un seul attribut booleen de modele, l'enum n'entre pas dans le gabarit |

`EmplacementDuLibelleDeZone` est dans `domain/content`, `application` n'est pas touche, aucun port
n'est ajoute. Sur les relais, rien a redire.

### Ce qui n'est pas dans l'arbre : la regle executable

`bootstrap/src/test/java/com/recreo/archi/AucunGabaritNiFeuilleMaisonNeNommeUnContenuParticulierTest.java`
**n'existe pas**. La recherche de ce nom dans tout l'arbre ne le trouve que dans le present `ADR`
et dans `ADR-index.md` - c'est-a-dire uniquement la ou il est promis. Ses quatre morsures dues
n'existent donc pas non plus.

**C'est la seule chose que cet `ADR` interdise.** Les quatre relais transportent une valeur, ils
n'empechent personne de rien. Un `ADR` dont seule la partie qui n'interdit rien est implementee
n'est pas un `ADR` accepte : c'est la dette 1 de `CLAUDE.md` - une abstraction documentee partout
et absente du code - en train de se reformer.

**La propriete tient aujourd'hui, et ce n'est pas un motif d'accepter.** Balayage a la main sur cet
arbre, avec sa methode : 101 identifiants derives du contenu servi (extraction plus grossiere que
celle que cet `ADR` prescrit, donc **minorant**), 17 fichiers du perimetre, **0 occurrence
litterale hors commentaire**. Un constat a la main un jour donne n'est pas un garde-fou.

### Un second ecart, entre la decision 5 et le gabarit servi

La decision 5 ecrit que `aria-label` porte **toujours** `zone.libelle()`. Le gabarit de l'arbre
porte `aria-label=${libelleDeZoneHorsDeLaBoite} ? ${zone.libelle()} : null`, c'est-a-dire le repli
conditionnel que la *Seconde mauvaise consequence* reservait au cas ou `nadia-prof` refuserait que
`frise-de-la-journee` recoive un attribut neuf. **Aucun verdict de `nadia-prof` sur ce point
n'existe dans `squad/` pour s36** : le declencheur ecrit du repli n'est pas survenu.

Consequence technique : aucune - sur un paquet `dans-la-boite`, le nom accessible retombe sur le
texte du bouton, qui est le libelle, et la clause 2 d'`ADR-0093` reste verte, verifiee sur le
catalogue reel. Consequence de gouvernance : le gabarit et cet `ADR` disent deux choses
differentes. **Je ne reecris pas la decision 5 pour la faire coincider avec le code apres coup** -
ce serait exactement le geste que l'alternative (f) ci-dessus ecarte. L'ecart est a trancher quand
cet `ADR` reviendra en acceptation, et les deux formes restent ouvertes.

### Les deux conditions qui restent

1. Le garde-fou de la decision 6 dans l'arbre, avec ses quatre morsures dues.
2. L'ecart de la decision 5 tranche, dans un sens ou dans l'autre.

---

## Acceptation - 2026-09-21, meme jour, apres les deux conditions

Worktree dedie `s36-theo-regle`, ouvert depuis `s36-sofia` a `5d7f2549`, lignee verifiee.

### Condition 1 : la regle executable est dans l'arbre

`bootstrap/src/test/java/com/recreo/archi/AucunGabaritNiFeuilleMaisonNeNommeUnContenuParticulierTest.java`,
avec son jeu fautif commite sous
`bootstrap/src/test/resources/contreexemple/AucunGabaritNiFeuilleMaisonNeNommeUnContenuParticulierTest/`
(un fautif, un corrige). **Ce n'est pas une `ArchRule`** : les objets juges sont des gabarits, une
feuille, un script et des sources - pas des classes chargees par ArchUnit - et une `ArchRule` ne
sait pas lire un `.html`. Le compte d'`@ArchTest` du depot est donc **inchange : 33**, meme methode
et meme compte qu'au 2026-09-21 matin
(`squad/sprints/s36-ouverture-ce1-mathematiques-seule-cm2-differe/confirmation-archunit.md`,
section 2) - occurrences de l'annotation `@ArchTest` dans les sources de test des modules declares,
`target/` et faux module de contre-exemple exclus. Ce fichier ajoute **8 tests JUnit**, pas une
regle ArchUnit.

`bootstrap` reste le module d'accueil, pour le motif d'origine : c'est le seul qui voie a la fois
`adapter-content` et `adapter-web`, donc le seul qui puisse confronter le contenu servi aux
gabarits qui le rendent.

### Condition 2 : l'ecart de la decision 5 est tranche

Tranche **en amendant la decision 5**, qui est reecrite ci-dessus en propriete (*le nom accessible
porte le libelle*) au lieu d'un attribut (*`aria-label` porte toujours le libelle*), et dont la
regle executable est la clause 2 d'`ADR-0093`, deja dans l'arbre et deja mordante sur le document
servi des deux formes. Le gabarit n'est pas modifie ; **aucun changement de code n'est route a
`lea-dev`**.

Ce que ce choix concede, puisque le contraire etait defendable : la forme inconditionnelle aurait
rendu le nom accessible independant du texte visible par construction, et elle etait la lettre de
la decision d'origine. Elle est ecartee parce que son gain est deja obtenu par un garde-fou qui
juge le document reellement servi - une preuve plus forte que *"par construction"* - et que son
prix, un attribut de plus sur un ecran deja accorde par `nadia-prof`, n'est paye par personne.
**Ce qui me ferait revenir en arriere est ecrit dans la decision 5 elle-meme.**

### La porte technique, avec les commandes

- `mvn -B clean verify` sur la racine du worktree, via l'enveloppe de toolchain : **BUILD SUCCESS**,
  six modules, **1495 tests, 0 echec** (1487 avant ce lot, plus les 8 de ce garde-fou). Version
  rapportee par la machine virtuelle qui a resolu la toolchain, et non par l'entree qui la declare
  (`ADR-0071`) : **21.0.10**.
- Les trois autres jobs de `.github/workflows/ci.yml` - `prefixes`, `chemins`, `juges purs` - sont
  lances sur ce diff et reportes dans
  `squad/sprints/s36-ouverture-ce1-mathematiques-seule-cm2-differe/confirmation-archunit.md`. Un
  agent qui annonce la porte technique nomme les jobs qu'il a lances (`CLAUDE.md`, amendement du
  2026-09-08) ; `mvn verify` n'est que le contenu du job `build`.

### Ce que cette acceptation ne prononce pas

Aucun Done pedagogique, aucune ligne de matrice fermee, aucune forme choisie. Un vert sur cet `ADR`
dit que le gabarit ne connait aucun contenu particulier ; il ne dit rien de ce que l'enfant lit.
`ZON-15` et la justesse de la declaration `hors-de-la-boite` restent tenues par l'oeil de
`nadia-prof`, comme la premiere *mauvaise consequence* le nomme.

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
