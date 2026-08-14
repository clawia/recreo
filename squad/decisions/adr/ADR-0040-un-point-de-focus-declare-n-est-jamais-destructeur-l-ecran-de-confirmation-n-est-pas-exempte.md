# ADR-0040 - Interdire qu'un point de focus declare soit lui-meme destructeur, et refuser d'exempter l'ecran de confirmation de la clause 2

- **Statut** : Accepte
- **Date** : 2026-08-14
- **Auteur** : theo-archi
- **Sprint** : s12 (point route par `sofia-sm` apres la reverification de `DEC-0026`)
- **Regle executable** :
  `adapters/adapter-web/src/test/java/com/recreo/adapter/web/AucunArretDeTabulationNEstDestructeurTest.java`
  (methodes `aucunPointDeFocusDeclareNeDetruitLuiMemeUnePartieEnCours` et
  `unPointDeFocusDeplaceSurUnArretDestructeurFaitEchouerLaClause3`)

## Contexte

`nadia-prof` rend **VALIDE SOUS RESERVE** le 2026-08-14 sur la reverification de `DEC-0026` : 53
pressions de Tabulation puis Entree, cinq ecrans, sept jeux, **zero partie detruite**. Sa reserve N,
mesuree et nouvelle, porte sur l'ecran de confirmation lui-meme :

> Sur **69 arrivees**, **57 fois** la premiere Tabulation fait sortir le focus du document, **12
> fois** elle atteint "Continuer la partie". A l'arrivee, rien n'est focalise. Cinq fois sur six,
> l'enfant presse Tabulation, rien ne s'allume, Entree ne fait rien.

Elle demande une propriete et **ne choisit pas le moyen** : *"sur cet ecran, l'enfant doit savoir ou
il en est des son arrivee, et le nombre d'appuis pour atteindre un bouton donne doit etre le meme a
chaque fois."* Le moyen m'est route.

La question n'est pas de forme. Le gabarit `fragments/confirmation.html` porte deux arrets de
tabulation, dans cet ordre : `continuer-partie` (`POST /retour/annuler`, la partie survit) puis
`retour-accueil` (`POST /retour/confirmer`, **la seule requete destructrice de l'application**).
Declarer un point de focus sur cet ecran met donc en jeu la clause 2 d'`ADR-0038` - *pour tout point
de focus que le serveur peut declarer, l'arret qui le suit n'est pas destructeur*. D'ou la question
telle qu'elle m'arrive : **la clause 2 doit-elle exempter l'ecran dont la destruction est le second
geste delibere, et a quelle condition exactement ?**

### Ce que j'ai mesure avant de repondre

Trois mutations du seul `fragments/confirmation.html`, appliquees une a la fois, depot restaure
entre chacune. JDK 21.0.10, `mvn -B -o -pl adapters/adapter-web test` (perimetre de la mesure : les
222 tests de ce module avant ce lot - un garde-fou d'un autre module n'y serait pas visible). Le
verdict est lu dans le message d'echec, pas dans mes attentes.

| # | Mutation | Resultat mesure |
|---|---|---|
| M1 | point de focus declare sur **"Continuer la partie"** (le bouton sur) | **clause 2 rouge**, seule et nommement : *"sur \"demande de confirmation avant d'abandonner, la partie continue\" (POST /retour), l'arret qui suit le point de focus declare `<button class="btn retour-accueil">` \"Les jeux\" detruit la partie en cours"* |
| M2 | point de focus declare sur **"Les jeux"** (le bouton destructeur) | **les 6 tests du garde-fou verts, et les 222 du module verts** |
| M3 | point de focus declare sur la **phrase** `p.question-confirmation`, avant les deux boutons | **tout vert** |

La lecture qui m'etait transmise est donc exacte sur M1, et **M2 est le fait nouveau** : l'etat le
plus dangereux que cet ecran puisse prendre - Entree, sans aucune Tabulation, detruit la partie -
etait autorise par l'ensemble des garde-fous du depot.

### Pourquoi le rouge de M1 est vrai, et non une fausse alerte

C'est le point qui decide de tout, et il tient a une ligne de `recreo.js` que je n'avais jamais fait
entrer dans le raisonnement d'`ADR-0038` :

```js
pointDeFocus.setAttribute('tabindex', '-1');
pointDeFocus.focus();
```

Le script pose `tabindex="-1"` **sur le point de focus lui-meme** avant de le focaliser. Un
`tabindex` negatif retire l'element de l'ordre de tabulation (`ADR-0038`, point 1, definition d'un
arret). Sur l'ecran de confirmation, ou il n'y a que deux arrets, declarer le focus sur "Continuer la
partie" retire donc "Continuer la partie" de l'ordre vivant : **le seul arret restant est le bouton
destructeur**. Ce que `nadia-prof` mesure aujourd'hui a 0 fois sur 69 - la premiere Tabulation atteint
le destructeur - deviendrait le seul comportement possible.

Cette derniere phrase est **derivee du DOM vivant et de la specification, pas pressee au clavier** :
si un lot retient cette forme, c'est le protocole de `nadia-prof` qui le mesure, jamais moi. Ce que je
mesure, moi, c'est le rouge de M1, et il tombe pour la bonne raison.

**Consequence pour `ADR-0038`, a inscrire** : sa non-preuve 1 disait que la clause 2 suppose son point
de depart sans le verifier. Il y a plus precis, et c'est neuf : **le document servi et le document
vivant n'ont pas le meme `A[0]` des que le point de focus declare est lui-meme un arret**, puisque le
script l'en retire. Aucun ecran ne se trouve dans ce cas aujourd'hui - sur les manches, le point de
focus est l'enonce (qui n'est pas un arret) ou une proposition (qui n'est jamais `A[0]`). La forme M1
en aurait fait le premier.

## Decision

**1. Aucune exemption. La clause 2 d'`ADR-0038` s'applique a l'ecran de confirmation sans
modification.** Le motif n'est pas doctrinal, il est mesure : le rouge de M1 n'est pas le prix a payer
pour un remede, **c'est le remede qui est mauvais**. Une exemption, quelle que soit sa condition,
autoriserait sur cet ecran exactement l'etat que la clause 1 de M13 interdit partout ailleurs - une
Tabulation, une Entree, une partie perdue - et le ferait passer de 0 fois sur 69 a chaque fois.
Exempter reviendrait a ecrire une regle dont le vert authentifierait le defaut qu'elle a ete ecrite
pour fermer.

**2. Clause 3, ajoutee et encodee dans le meme geste : le point de focus que le serveur declare ne
detruit jamais lui-meme la partie en cours.** La clause 2 juge l'arret **d'apres** - Tabulation puis
Entree. Celle-ci juge le point de depart - **Entree seule**. Elle n'existait pas parce qu'aucun point
de focus ne pouvait etre destructeur : jusqu'a l'ecran de confirmation, la seule requete destructrice
de l'application etait portee par une sortie, qu'aucun gabarit ne designe comme point de focus. M2 est
le premier objet reel de cette clause, et il etait vert.

Un point de focus est juge des lors qu'il **declare une requete** (`hx-post`, `hx-get`, la soumission
d'un formulaire englobant, `href` - la lecture ordonnee d'`ADR-0038`, non-preuve 5). Un point de focus
qui n'en declare aucune - l'`.enonce` d'une manche neuve, une phrase - n'est pas juge : il n'y a rien
a activer.

**3. Les trois clauses ensemble disent une seule chose, et c'est ainsi qu'il faut les lire** : depuis
le point de depart que le serveur choisit, ni Entree seule (clause 3) ni une Tabulation puis Entree
(clause 2) ne detruit une partie en cours - et il en va de meme quand le serveur ne choisit aucun
point de depart (clause 1, l'arret ou la tabulation arrive depuis nulle part ou reboucle).

**4. Ce que l'ecran de confirmation doit devenir, et ce qui reste ouvert.** L'architecture ne choisit
pas le bouton qui recoit le focus - c'est un jugement de `nadia-prof`. Elle reduit en revanche le
choix a une seule famille de formes, et cette reduction est une **derivation**, pas une preference.
Soient les deux arrets de cet ecran, dans l'ordre du document, `[sur, destructeur]` :

- le focus sur `destructeur` est refuse par la **clause 3** (Entree suffit) ;
- le focus sur `sur` est refuse par la **clause 2** (l'arret d'apres est `destructeur`) ;
- un point de focus non focalisable place **apres** `sur` est refuse par la **clause 2**, pour la
  meme raison ;
- echanger les deux boutons pour eloigner le destructeur est refuse par la **clause 1** - mesure
  d'`ADR-0038` du 2026-08-14 : les deux boutons echanges de place, la clause 1 rougit sur cet ecran -
  et par les conditions de forme CONF-1 et CONF-2 de `nadia-prof`, qui ne sont pas de moi.

**Il reste donc exactement ceci : un point de focus qui ne declare aucune requete, place avant les
deux boutons dans l'ordre du document.** C'est la forme M3, mesuree verte sur les 224 tests du module.
Le mecanisme existe deja et ne demande aucune ligne de `recreo.js` : `data-focus` sur la racine du
fragment et `data-focus-cible="true"` sur l'element vise, comme `fragments/manche.html` le fait avec
son enonce. **Quel** element porte ce point de focus - la phrase arbitree, un titre, autre chose - et
ce que l'enfant doit y entendre appartiennent a `nadia-prof` et a `ines-design`.

**5. Ce que ce lot n'encode pas, et qui reste du.** Rien n'oblige encore un ecran a partie en cours a
**declarer** un point de focus : les trois clauses restent vertes sur un ecran inerte, qui est l'etat
du jour. Cette quatrieme clause - *tout ecran assemble ou une partie est en cours declare un point de
focus* - est derivable du meme corpus et **serait rouge des sa premiere execution**, sur le seul ecran
de confirmation. Je ne l'ecris donc pas ici : elle appartient au lot qui corrige l'ecran, dans le meme
commit que le correctif, exactement comme la clause 1 est venue avec le lot de s11. L'enonce est
ci-dessus, il n'y a rien a inventer. **En attendant ce lot, cette propriete n'est gardee par personne
d'autre que `nadia-prof`**, et sa reserve N est la seule trace executable qu'il en existe : c'est une
dette, elle est nommee, et son declencheur est la story que `camille-po` ecrira sur la reserve N.

## Regle executable

Fichier : `adapters/adapter-web/src/test/java/com/recreo/adapter/web/AucunArretDeTabulationNEstDestructeurTest.java`

```java
private static Optional<Element> pointDeFocusActivable(Document document) {
    return pointDeFocusDeclare(document).filter(point -> requeteDeclaree(point).isPresent());
}

@Test
void aucunPointDeFocusDeclareNeDetruitLuiMemeUnePartieEnCours() throws Exception {
    // pour tout ecran a partie en cours qui declare un point de focus porteur d'une requete :
    // rejeu de cette requete sur une session fraiche, echec si la partie n'y survit pas.
}

@Test
void unPointDeFocusDeplaceSurUnArretDestructeurFaitEchouerLaClause3() throws Exception {
    // population derivee : tout ecran a partie en cours offrant au moins un arret destructeur.
    // Le point de focus y est DEPLACE sur cet arret, puis la clause 3 doit rougir.
}
```

**Pourquoi ce n'est pas une regle ArchUnit** : ArchUnit juge des classes et leurs dependances ; la
propriete porte sur un document rendu et sur l'effet d'une requete sur une session. Le mecanisme
automatique est un test du module `adapter-web`, execute par `mvn verify` comme le reste. **Le compte
des regles ArchUnit reste a 28**, methode inchangee (declarations `ArchRule <nom> =` sous
`src/test/java` des modules du pom agregateur).

**Les trois obligations d'`ADR-0013`, sans dispense :**

1. **perimetre derive** - les ecrans viennent du corpus produit par de vraies requetes sur de vraies
   sessions, dont les routes sont lues dans `RequestMappingHandlerMapping` ; le point de focus vient
   des marqueurs poses par le gabarit (`ADR-0015`) ; le caractere destructeur vient de la requete
   rejouee, jamais d'un attribut ni d'une liste ecrite dans le test ;
2. **derivation totale** - un point de focus introuvable dans le document, deux elements qui se
   declarent cibles, un `tabindex` illisible restent des **echecs** herites des clauses 1 et 2. La
   clause 3 n'ignore qu'un seul cas, et il est trivialement vrai : un point de focus qui ne declare
   aucune requete ;
3. **mise a l'epreuve, sur du materiau reel et non sur une fixture de ma main** (lecon de s05) - le
   document que le serveur rend vraiment, avec son point de focus **deplace** sur un arret destructeur
   du meme ecran. Deplace et non ajoute : c'est ce qui rend l'epreuve encore valable le jour ou cet
   ecran declarera enfin un point de focus. La population n'est pas nommee, elle est derivee : tout
   ecran a partie en cours offrant au moins un arret destructeur.

**Morsure mesuree, methode comprise** : mutation M2 reappliquee une fois la clause 3 ecrite, meme
commande et meme JDK que ci-dessus, verdict lu dans le message d'echec. Resultat : **1 echec sur les 8
tests du garde-fou**, la clause 3, nommant l'ecran et le bouton - *"le point de focus declare
`<button class="btn retour-accueil">` \"Les jeux\" detruit lui-meme la partie en cours - Entree
suffit, sans Tabulation"*. Les clauses 1 et 2 restent vertes sous cette mutation : elles ne voient
pas ce defaut, ce qui est la raison d'ecrire la clause 3 separement plutot que d'elargir la clause 2.

**Anti-vacuite, et c'est le deuxieme service que rend l'epreuve** : elle exige que la population
"ecran a partie en cours offrant un arret destructeur" soit non vide. Le jour ou plus aucune requete
destructrice n'est atteignable depuis un ecran a partie en cours, les trois clauses ne gardent plus
aucun objet - et ce test le dit, au lieu de passer vert en silence. C'est la reponse executable au
vert facile qu'`ADR-0038` nommait sans l'outiller.

## Ce que cette clause ne prouve pas, nommement

1. **Que le focus va la ou le serveur le declare.** Inchange depuis `ADR-0038`, non-preuve 1 : le
   deplacement est fait par `recreo.js`, teste sur un faux DOM. La clause 3 suppose son point de
   depart comme la clause 2.
2. **Qu'Entree active reellement le point de focus.** Le critere retenu - *le point de focus declare
   une requete* - est **plus large que l'activation reelle d'un navigateur** : un element non
   activable au clavier qui declarerait une requete serait juge ici alors qu'Entree n'y declencherait
   rien. Plus strict que la realite, jamais plus lache ; je prefere ce sens-la.
3. **Qu'un point de focus pose sur une sortie soit refuse.** La clause 3 mesure un **effet**, pas un
   role. Le bouton "Les jeux" de `fragments/manche.html` porte `data-role="sortie"` et sa requete ne
   detruit plus rien : un point de focus pose sur lui serait **vert** ici, alors que la clause 3 de
   M13 - *"jamais la sortie, sur aucune des deux valeurs"* - le refuse. Cette clause-la est celle de
   `nadia-prof`, elle est ecrite dans un commentaire de `fragments/manche.html`, et **elle n'est
   encodee par rien**. Je ne l'encode pas par un critere qui lirait `data-role`, pour la raison
   qu'`ADR-0038` a deja payee : un critere qu'on desamorce en changeant un attribut n'est pas un
   critere. Dette nommee ; ce qui me la fera encoder est le jour ou un role declare cessera de
   concorder avec la destination percue par l'enfant.
4. **Que l'enfant s'en sorte.** Qu'une phrase soit lue par un lecteur d'ecran a l'arrivee, que deux
   appuis soient un nombre d'appuis acceptable, qu'un enfant de CP comprenne ou il est : reserve N et
   reserve F11 de `nadia-prof`, et aucun garde-fou de ce depot n'y touche.
5. **Que le nombre d'appuis soit stable.** La seconde moitie de la propriete demandee par la reserve N
   - *"le nombre d'appuis pour atteindre un bouton donne doit etre le meme a chaque fois"* - se deduit
   d'un point de focus declare, elle n'est pas mesuree par ce garde-fou, qui ne presse aucune touche.

## Consequences

- **Un remede evident est desormais interdit par la CI, et c'est le but** : declarer le focus sur le
  bouton sur d'un ecran dont le bouton suivant detruit. Le lot qui corrigera la reserve N ne pourra
  pas prendre ce chemin sans rougir, et le message nomme l'ecran et le bouton.
- **Le pire remede l'est aussi**, et il ne l'etait par rien : le focus sur le bouton destructeur.
- **Cout d'execution** : la clause 3 rejoue une requete de plus par ecran juge, et l'epreuve de
  morsure rejoue chaque arret de chaque ecran a partie en cours. Le garde-fou passe de 6 a 8 tests et
  de 8,7 a 9,7 s environ sur ce poste. C'est le prix du critere par effet mesure, deja assume par
  `ADR-0038`.
- **Mauvaise consequence assumee** : la clause 3 est plus stricte que le navigateur sur un element non
  activable (non-preuve 2). Si un jour une forme legitime tombe sous ce filet, c'est la clause qui
  devra distinguer l'activable, pas l'exception qui devra etre ecrite.
- **L'ecran reste inerte tant que le lot n'a pas eu lieu.** Aucune de ces trois clauses ne l'oblige a
  declarer un point de focus (point 5 de la decision). Ce que ce lot ferme, c'est l'ensemble des
  mauvaises facons de le rendre actif ; ce qu'il ne fait pas, c'est le rendre actif.

## Alternatives considerees

- **Exempter l'ecran de confirmation nommement.** Ecarte pour deux raisons, et la seconde suffirait.
  D'abord parce qu'une exemption par nom est une liste de refus - verte par defaut sur tout ce qu'elle
  ne nomme pas, la polarite exacte qu'`ADR-0039` vient d'interdire. Ensuite, et surtout, parce qu'elle
  autoriserait M1, dont le comportement reel est la destruction en une Tabulation.
- **Exempter par une propriete derivee : "l'ecran dont la destruction est le second geste
  delibere".** Ecarte parce que la derivation **exempte tout le corpus**. Chaque ecran a partie en
  cours est atteint par un geste delibere qui n'a rien detruit : l'ecran de revelation aussi, atteint
  en pressant une proposition. La propriete ne distingue pas l'ecran de confirmation de l'ecran ou le
  defaut est ne. Une condition qui n'exclut rien n'est pas une condition.
- **Exempter l'ecran qui n'offre aucun moyen d'avancer dans le jeu.** Ecarte : plus de pieces mobiles
  (il faudrait mesurer qu'une requete "laisse la partie identique", ce qu'`EtatDePartieEnSession` ne
  dit pas), pour une population d'un membre, et **le resultat serait encore d'autoriser M1**. Aucune
  formulation d'exemption ne survit a ce dernier test, parce que le probleme n'est pas le perimetre de
  la clause 2 : c'est que le remede qu'elle refuse est reellement mauvais.
- **Elargir la clause 2 au point de focus lui-meme au lieu d'ecrire une clause 3.** Ecarte : les deux
  ne parlent pas du meme geste (Entree seule contre Tabulation puis Entree), elles ne rougissent pas
  sur les memes ecrans, et une seule assertion melangeant les deux rendrait un message d'echec qui ne
  dirait pas quel geste detruit. Deux clauses separees ont ici la meme justification que la separation
  des clauses 1 et 2 dans `ADR-0038`.
- **Encoder tout de suite "tout ecran a partie en cours declare un point de focus".** Ecarte pour ce
  lot : elle serait rouge des sa premiere execution, et livrer une CI rouge a la cloture d'un sprint
  est le plus sur moyen de faire desactiver une regle (`ADR-0038`, meme arbitrage). Elle est due par le
  lot qui corrige l'ecran, avec son enonce ecrit au point 5.
- **Ne rien encoder et laisser la reserve N a `nadia-prof`.** Ecarte : M2 montre qu'un etat pire que
  celui qu'elle a mesure passait vert, et laisser a l'humaine ce qu'un automate peut tenir est la
  faute que ce depot a deja payee deux fois.

## Ce qui me ferait changer d'avis

- **Si `recreo.js` cessait de poser `tabindex="-1"` sur le point de focus**, la demonstration du rouge
  de M1 tombe : le bouton sur resterait dans l'ordre vivant et la premiere Tabulation depuis lui irait
  ailleurs selon le navigateur. La clause 2 resterait juste par prudence, mais son motif changerait, et
  je devrais le reecrire ici plutot que de le laisser croire mesure.
- **Si un deuxieme ecran de confirmation apparaissait** - une seconde requete destructrice dans
  l'application - la population des ecrans a arret destructeur cesserait d'etre d'un membre. C'est
  aussi le declencheur que j'avais inscrit dans `ADR-0038` pour encoder la concordance role/effet ;
  les deux dettes se leveraient dans le meme geste.
- **Si `nadia-prof` mesurait au clavier, sur la forme M3 livree, que la premiere Tabulation ne fait pas
  ce que je derive du document**, alors c'est ma modelisation de l'ordre de tabulation qui est fausse,
  et il faudrait cesser de la deduire d'un document pour la mesurer - donc rejoindre l'outillage de
  rendu d'`ADR-0025`, comme `ADR-0038` l'avait deja ecrit pour le masquage.
