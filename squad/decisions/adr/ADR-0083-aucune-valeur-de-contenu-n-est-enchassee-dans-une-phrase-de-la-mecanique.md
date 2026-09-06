# ADR-0083 - Aucune valeur de contenu n'est enchassee dans une phrase de la mecanique : les mots invariants vivent dans le gabarit, la valeur voyage seule

- **Statut** : Accepte
- **Date** : 2026-09-06
- **Auteur** : theo-archi
- **Sprint** : s29
- **Route par** : `nadia-prof`, `PASSE-TRANSVERSE-s29-les-proprietes-de-portee-depot.md`, section 1
  (`REV-1`), commit `ba3f603`. Elle decrit le symptome et refuse de prescrire la solution : *"la
  cause est dans le code de l'adapter web, et c'est `theo-archi` qui traite les causes
  techniques"*.

## Contexte

### Le fait

Le cadre de revelation - ce que l'enfant lit apres trois erreurs sur une manche - etait compose par
une **seule expression** de `PartieController` :

```java
message = "C'est " + reponse + ". Appuie sur " + reponse + ".";
```

Il se lit correctement quand la bonne reponse est un groupe nominal ou une valeur (*"C'est 5. Appuie
sur 5."*) et faux des qu'elle est autre chose :

> *"C'est ce n'est pas un etre vivant. Appuie sur ce n'est pas un etre vivant."*

`nadia-prof` l'a mesure sur le contenu servi entier : **fautif sur 12 items de 4 paquets**
(`cp-qlm-cycle-de-vie`, `cp-qlm-vivant-non-vivant`, `cm1-comprehension`,
`cm1-histoire-geographie`), **maladroit sur 29 items de 5 autres**, sur les **1056 items enumeres**
du depot. Trois d'entre eux verifies a l'ecran, par appels HTTP reels.

### Le probleme de fond, et il n'est pas une faute de frappe

**Une bonne reponse de contenu n'est pas toujours un groupe nominal.** Le depot en sert au moins
quatre formes : un groupe nominal (*"la langue"*), une valeur (*"5"*), un complement (*"en 1789"*),
une proposition entiere (*"ce n'est pas un etre vivant"*, *"il se nourrit"*). Un cadre qui enchasse
la reponse dans une phrase **choisit l'une de ces formes et se lit faux sur les autres**. Le defaut
n'est pas dans la phrase choisie ; il est dans le fait d'enchasser.

### Une seconde occurrence, que la passe n'avait pas cherchee

`retour.html`, l'ecran de fin de partie, portait `Non, c'est <span th:text="${bonneReponse}"></span>.`
- *"Non, c'est ce n'est pas un etre vivant."*, sur les douze memes items. `nadia-prof` avait ecrit
qu'elle n'avait cherche que l'ecran de revelation (section 7 de sa passe : *"il en existe peut-etre
d'autres du meme genre, produits par la rencontre d'un champ de contenu et d'un gabarit, et je ne
les ai pas cherches"*). Elle avait raison de le dire : **il y en avait une, a un ecran de la**.

**Comment elle a ete trouvee, parce que la methode vaut le fait.** Pas par une lecture attentive du
gabarit : par un **garde-fou existant qui a arrete le correctif**. `TexteAccentueDesGabaritsTest`
refuse tout texte de gabarit hors de son inventaire ; en y ajoutant les mots neufs, j'ai trouve
l'entree `"Non, c'est"` assortie du commentaire *"la reponse suit, posee par le modele"*. **Le
defaut y etait ecrit en toutes lettres depuis des sprints, dans un fichier de test, sans que
personne - moi compris - l'ait lu comme un defaut.** Il n'est devenu lisible qu'une fois le nom de
la famille pose. C'est le seul argument dont je dispose pour dire qu'un ADR sert a quelque chose
avant sa regle : il donne un nom a une famille, et un nom rend visible ce qui etait sous les yeux.

**Mesure du perimetre reel, methode incluse** : extraction du motif `+ ${` sur les quatre gabarits
de production, plus lecture des deux branches de `mancheModele`. Quatre compositions existent dans
le produit ; **deux seulement enchassent une valeur de contenu** - les deux ci-dessus, corrigees
ici. Les deux autres restent et sont saines : `'Score : ' + score.reussites()` place un nombre
apres un deux-points, et `'Tu as ecrit : ' + chaineJugee + '.'` enchasse **la chaine que l'enfant a
tapee**, jamais un champ de contenu. Une troisieme, `'Pour le ' + niveauActif` sur l'ecran
d'accueil, enchasse une valeur de catalogue derriere un article : elle est saine sur les deux
niveaux servis (`CP`, `CM1`) et ne le resterait pas sur un niveau au nom feminin ou pluriel. Elle
n'est pas corrigee ici - aucun defaut ne s'y realise aujourd'hui - et elle est **nommee comme
declencheur** : le jour ou un troisieme niveau entre au catalogue, cette phrase se relit.

### Pourquoi rien ne le mordait, et c'est la moitie la plus importante de cet ADR

Trois proprietes de ce defaut, chacune suffisante pour le rendre invisible, et qui etaient reunies :

1. **Il n'existe dans aucun fichier de contenu.** Aucun audit de paquet, aucun garde-fou de YAML,
   aucune relecture de contenu ne pouvait le voir. Il nait de la **rencontre** d'un champ de
   contenu et d'un gabarit - la famille que `nadia-prof` ajoute a sa portee D dans le meme
   document.
2. **Il n'apparait qu'a la troisieme erreur consecutive.** Aucun test du depot ne jouait mal
   jusqu'au bout sur du contenu reel. Ceux qui atteignent la revelation (`PartieControllerTest`)
   le font sur un catalogue de test dont la bonne reponse est le mot `"bonne"` - un groupe nominal,
   c'est-a-dire exactement la forme que le cadre supposait.
3. **Il touche des lignes fermees, dont des lignes vertes depuis plusieurs sprints.** Le Done
   pedagogique ne l'a pas rattrape parce qu'un audit qui joue proprement ne le rencontre jamais.

C'est le **produit** des trois qui l'a laisse traverser tous les sprints de contenu du depot. Et
c'est ce produit que la regle executable ci-dessous vise : **jouer mal, jusqu'au bout, sur la
population reelle des paquets servis.**

## Decision

**Le cadre d'un texte servi est un gabarit, jamais une concatenation : les mots invariants vivent
dans le gabarit, une valeur de contenu voyage seule dans le modele, et rien ne les reunit.**

Trois clauses, a l'imperatif.

1. **Aucune phrase servie a l'enfant n'enchasse une valeur de contenu.** Une valeur de contenu -
   bonne reponse, forme de reference, enonce, indice, explication, texte a voix haute - occupe
   **seule et entiere** le texte de son element. Les mots que la mecanique adresse a l'enfant
   vivent dans le gabarit et n'en sortent pas.
2. **La reponse revelee reste nommee.** Un cadre qui deviendrait invariant en cessant de nommer la
   reponse laisserait l'enfant devant trois boutons sans moyen de savoir lequel presser (`M5`) :
   un element qui n'est pas un bouton porte la reponse, seule et entiere, sur l'ecran de revelation
   comme sur l'ecran de fin d'une manche ratee.
3. **Ce que porte un emplacement ne depend pas de l'item.** La presence d'un bloc peut dependre de
   l'item - un bloc-adulte n'apparait que sur les items qui declarent une voix (`ADR-0075`) ; le
   **texte** d'un bloc present, lui, est le meme pour tous les items de sa mecanique.

Ce que la decision produit dans le code, et rien de plus :

| Ecran | Avant | Apres |
|---|---|---|
| Manche revelee (`manche.html`) | `.verdict` = `"C'est " + reponse + ". Appuie sur " + reponse + "."` | `.verdict` = `MESSAGE_REVELATION` (constante deja existante) ; `.reponse-revelee` = `"Appuie sur la bonne reponse :"` ; `.reponse-attendue` = la reponse, seule |
| Fin de partie (`retour.html`) | `Non, c'est <span>${bonneReponse}</span>.` | `.verdict` = `"Ce n'est pas ca."` ; `.reponse-revelee` = `"La bonne reponse :"` ; `.reponse-attendue` = la reponse, seule |

**Ce n'est pas une forme inventee pour l'occasion.** C'est celle que l'ecran de saisie porte depuis
s24 (`SAI-9b`, `ADR-0058`) : un verdict constant, puis `On ecrit :` suivi d'un `<span>` qui porte la
chaine brute. Sa Javadoc dit deja pourquoi, et le mot exact vaut d'etre repris ici : *"`.chaine-jugee`
est un element distinct et non une portion de la phrase de verdict : c'est ce qui rend `SEUIL-1` et
`SAI-2c` verifiables structurellement, la ou une chaine noyee dans un texte de modele ne le serait
pas."* La mecanique a boutons rejoint la mecanique de saisie ; aucune des deux n'invente.

**Ce que cette decision ne decide pas, et je m'arrete la ou ma frontiere passe.** Les trois textes
- `"Ce n'est pas ca."`, `"La bonne reponse :"`, `"Appuie sur la bonne reponse :"` - sont des
**candidats**, au sens exact ou les quatre textes de l'ecran de saisie le sont. `M12` dit *"les
textes exacts, accentues ; toute variante revient devant moi"*, et `nadia-prof` a chiffre d'avance
sa reverification a **0,25**, *"a condition que je puisse la voir a l'ecran"*. Ce que j'ai tranche
est **structurel** : plus aucune phrase n'enchasse la reponse. Ce qui reste a trancher est
**pedagogique** : quels mots, dans quel ordre. Les deux sont separables precisement parce que la
structure les separe - changer les mots ne demande plus de toucher a du code, et n'est plus
susceptible de recreer le defaut.

Les trois textes entrent dans l'inventaire de `TexteAccentueDesGabaritsTest` avec leur reference,
et `"Non, c'est"` en sort. Ce garde-fou est ce qui a **arrete le premier jet de ce correctif** :
un texte neuf lu par un enfant ne passe pas en silence. Il ne valide pas ces trois textes ; il
garantit que le prochain qui les changera le fera en clair.

## Regle executable

**Fichier** :
`bootstrap/src/test/java/com/recreo/bootstrap/rendu/AucunTexteServiNEnchasseLaReponseDansUnePhraseTest.java`

Ce n'est **pas une `ArchRule`**, et je dis pourquoi plutot que de forcer la forme. La propriete
porte sur le **texte servi a l'enfant**, pas sur une dependance entre types. Une `ArchRule` ne lit
ni le contenu d'un litteral, ni le resultat d'un `invokedynamic` de concatenation, ni un gabarit
Thymeleaf : elle aurait au mieux interdit un nom de methode, c'est-a-dire rien. C'est un test JUnit
qui echoue dans `mvn verify` comme n'importe quel autre, sur le modele etabli par `ADR-0013`
(section "Regle executable") et par `LaConsigneDUnJeuServiAtteintLEcranDeMancheTest`.

### Le critere

Tout noeud de texte servi est de l'un de ces trois genres :

- **la reponse revelee**, seule et entiere dans son element (et cet element existe : clause 2) ;
- **un extrait litteral** d'un champ de l'item ou de l'entree de catalogue ;
- **un mot de la mecanique**, et alors **l'emplacement qui le porte dit toujours la meme chose** sur
  toute la population.

Une phrase composee autour de la reponse n'est **aucun des trois** : elle n'est pas la reponse, elle
n'est ecrite dans aucun fichier, et l'emplacement qui la porte change de texte a chaque item.

### La population

**Un ecran par item reellement servi, joue jusqu'a la troisieme erreur, puis jusqu'a la fin de
partie.** Les jeux viennent du chargeur de production (`new YamlContentPackLoader().games()`,
l'appel meme que `ContenuConfiguration` fait) ; les items viennent du tirage de chaque
`ContentSource` reelle. Chaque item devient un jeu d'une seule manche qui garde l'entree de
catalogue de son paquet - c'est le seul moyen d'atteindre **tous** les items d'un paquet dont le
tirage est aleatoire, sans demander au domaine une API qu'il n'a pas a offrir.

**Mesure, avec sa methode** : 2512 ecrans, soit **1256 items x 2 ecrans**. Les 1256 items sont les
**1056 items enumeres** que `nadia-prof` a comptes, plus **200 tirages** du seul paquet
combinatoire du catalogue (`AdditionsBorneesContentSource`, qui ne s'epuise jamais et que le
garde-fou borne au meme budget que `PropositionsAccentueesJouablesIT`). Mesure a `ba3f603` plus le
present lot, sur le reacteur complet, JDK 21.

### Les trois obligations d'`ADR-0013`

- **Perimetre derive** : aucun paquet, aucun item, aucun texte n'est nomme dans le fichier. Un
  paquet neuf entre dans la population le jour ou il entre au catalogue.
- **Derivation totale** : chaque item produit deux ecrans, chaque noeud de texte de ces ecrans
  produit une assertion, et une forme d'item qu'aucun ecran servi ne sait jouer (`ZoneADesigner`
  aujourd'hui) fait **echouer** la construction de la population au lieu d'etre ecartee en silence.
- **Mise a l'epreuve** : trois morsures, dont deux sur la **sortie reelle du serveur**, mutee par
  l'expression fautive elle-meme, mot pour mot telle qu'elle vivait dans le produit jusqu'a s29.

### La preuve de la morsure, mesuree et non affirmee

Deux epreuves, et je donne les deux parce qu'elles ne prouvent pas la meme chose.

1. **Morsure permanente, dans la suite** : `leCadreQuiEnchasseLaReponseFaitRougirCeGardeFouSurDesItemsReels`
   remet le cadre d'avant s29 sur les 2512 ecrans reellement rendus et exige le rouge ;
   `unEcranQuiCesseDeNommerLaReponseFaitRougirCeGardeFou` retire l'element qui porte la reponse et
   exige le rouge. La seconde est necessaire : sans elle, un correctif qui supprimerait purement la
   reponse rendrait le cadre invariant et passerait vert.
2. **Morsure sur le produit d'avant, mesuree** : le code de production remis dans son etat de
   `ba3f603` (les trois fichiers restaures a l'identique), le garde-fou seul relance sur le reacteur
   `-pl bootstrap -am` rend **3150 griefs sur 2512 ecrans** - dont
   `bandit-manchot-additions-cp--item-0 (revelation) : aucun element hors bouton ne porte "1" seule
   et entiere` et `bandit-manchot / fin de partie | signal est-faux > p.verdict : cet emplacement
   dit autre chose selon l'item joue`. C'est la morsure qui compte : elle tombe sur des **items
   reels**, avec leur nom, et non sur une fixture de ma main - la lecon d'`ADR-0015`, que ce lot a
   d'ailleurs revecue (voir "Le faux rouge" ci-dessous).

### Le faux rouge que ce garde-fou a produit sur lui-meme, et ce qu'il a coute d'apprendre

Premiere ecriture du critere : *l'ensemble des mots d'un ecran est le meme sur toute la population*.
Resultat mesure a la premiere execution : **deux faux rouges**, sur les seize items de
`cp-voyelle-du-son` et sur les douze de `cp-mot-dicte`. Motif : ces ecrans portent en plus
l'etiquette `"Adulte, a voix haute :"`, parce que leurs items declarent un texte a prononcer
(`ADR-0075`) et que le bloc qui la porte est conditionnel.

**La presence d'un bloc depend legitimement de l'item ; c'est le texte d'un bloc present qui ne doit
pas en dependre.** La cle d'invariance est donc l'**emplacement** - classe du parent, balise et
classe de l'element -, derive du document rendu, jamais l'ecran entier. Un bloc qui apparait ou
disparait est libre ; un bloc qui parle autrement selon l'item est rouge.

Je l'ecris parce que c'est exactement la faute que ce depot sanctionne quand un autre la commet :
**un critere trop large pousse a defaire un correctif juste**. Ici il aurait pousse a retirer le
bloc-adulte des paquets a voix, c'est-a-dire a defaire `ADR-0075`. Il a ete trouve en une execution
parce que la population etait reelle ; sur une fixture de ma main, il serait passe vert.

### Ce que ce garde-fou ne garde pas

Ecrit plutot que tu, pour que personne ne le croie plus large qu'il n'est.

- **Il ne juge aucun texte.** Que `"Appuie sur la bonne reponse :"` soit la bonne formulation pour
  un enfant de six ans appartient a `nadia-prof` et a elle seule.
- **Il ne voit pas une composition dont les deux membres seraient invariants.** `'Tu as ecrit : '`
  suivi de la chaine tapee par l'**enfant** reste invariant ici, parce que le garde-fou tape
  toujours la meme chaine. Ce n'est pas une valeur de contenu, et ce n'est pas ce que cet ADR
  gouverne.
- **Il ne couvre que les deux ecrans ou la mecanique parle de la reponse.** L'accueil et l'ecran de
  niveau ne servent aucun item et sont hors de sa population ; c'est la ou vit
  `'Pour le ' + niveauActif`, nomme plus haut avec son declencheur.
- **Il accepte un texte qui est un extrait litteral de l'enonce.** C'est ce qui lui permet de juger
  les segments d'`ADR-0062` sans reimplementer leur decoupage. Une phrase composee qui serait, mot
  pour mot, une sous-chaine de l'enonce de son propre item lui echapperait : aucune des 1256 ne
  l'est, et je ne connais pas de mecanisme qui produirait ce cas.

## Consequences

**Bonnes.**

- Les 12 items fautifs et les 29 maladroits sont corriges **sans toucher un octet de contenu** : la
  correction est structurelle, elle vaut pour les 1256 items servis et pour tous ceux a venir.
- La reponse est lue **une fois** au lieu de deux. Sur les items de classe A, l'enfant lisait la
  meme proposition longue deux fois de suite au pire moment.
- Les deux mecaniques revelent desormais de la meme facon. Un enfant qui passe de l'une a l'autre
  n'a pas a reapprendre ou regarder.
- Une correction de formulation ne demande plus de toucher au code : les mots sont dans le gabarit,
  et le garde-fou verifie qu'ils y restent invariants.

**Mauvaises, et je ne les adoucis pas.**

- **Trois textes lus par un enfant changent sans que `nadia-prof` les ait lus.** C'est le prix du
  fait que le correctif doit etre **livre et jouable** pour qu'elle puisse le voir a l'ecran - sa
  propre condition. Ils sont marques candidats, sa reverification est chiffree a 0,25, et je la lui
  route. Si elle en refuse un, la structure ne bouge pas : seuls les mots changent.
- **`M12` est modifiee de fait sur trois fiches** qui l'enregistrent comme *"tenue"* avec les
  anciens textes cites au mot. Elles disent aujourd'hui autre chose que ce que le produit sert. Ce
  n'est pas a moi de les reecrire ; c'est route.
- **Deux classes CSS de plus** (`.reponse-revelee`, `.reponse-attendue`), avec deux regles de
  taille recopiees de la paire equivalente de l'ecran de saisie (1,25 rem et 2 rem). Toute regle
  neuve elargit la surface que les garde-fous d'habillage doivent couvrir ; celles-ci sont sous les
  planchers existants et y sont verifiees.
- **Le garde-fou coute 12 secondes** de plus a `mvn verify` (2512 ecrans, un contexte Spring
  partage). C'est le prix de la population reelle, et c'est ce qui distingue ce test de ceux qui
  etaient deja verts.

## Alternatives considerees

### 1. Contraindre la forme des bonnes reponses - ecartee

Exiger que toute bonne reponse soit un groupe nominal, et garder cette contrainte par un test de
contenu.

**Ecartee pour trois motifs, dont un rendhibitoire.** D'abord, `nadia-prof` l'a ecarte d'avance et
le motif lui appartient : *"`oui`, `non` et les infinitifs sont des reponses legitimes que je ne
retirerai pas d'un paquet de CP"*. Ensuite, ce serait **une decision de contenu**, donc hors de ma
frontiere : je l'aurais routee, pas prise. Enfin - et c'est le point technique - la contrainte
serait **inencodable sans un analyseur grammatical** : distinguer *"la langue"* de *"ce n'est pas un
etre vivant"* demande une analyse syntaxique du francais, et le seul lexique du depot
(`french-pos-dict`, `ADR-0065`) etiquette des mots, pas des propositions. Le garde-fou aurait ete
une liste de mots interdits en tete de reponse : un **critere enumere sous une promesse
universelle**, exactement la forme qu'`ADR-0039` a mesuree comme verte par defaut.

**Et le motif de fond** : cette voie fait payer au contenu le defaut d'un gabarit. Elle deplace la
contrainte du cote qui n'a rien fait de mal.

### 2. Faire declarer par le paquet la facon dont sa reponse s'enonce - ecartee

Une cle de contenu (`formeDeLaReponse: proposition | groupe-nominal | complement`) que la mecanique
lirait pour choisir son cadre.

**Ecartee.** C'est la **dette 2 de la version precedente**, a l'identique : une configuration
taillee pour une matiere que chaque paquet doit remplir. Cinquante-quatre paquets devraient declarer
une valeur pour permettre a un gabarit d'ecrire une phrase que **personne n'a besoin de lire**.
Elle ajoute un champ, un schema, un chargeur, un garde-fou de coherence et une valeur factice par
paquet - et elle demande de **toucher au contenu**, ce que mon perimetre m'interdit et ce qu'aucun
correctif structurel ne devrait exiger. La question qui la tue tient en une ligne : *quel probleme
resoudrait-elle que le deux-points ne resout pas ?* Aucun.

### 3. Reformuler la phrase composee - ecartee comme decision, retenue comme effet

*"C'est la reponse : X. Appuie dessus."*, en gardant une concatenation dans le controleur.

**Ecartee.** Elle repare les douze items d'aujourd'hui et **ne rend rien impossible** : la prochaine
phrase composee autour d'un champ de contenu entre exactement de la meme facon, et le garde-fou
qu'on pourrait ecrire par-dessus ne serait qu'un epinglage du texte du jour. C'est la reponse
evidente, et c'est celle qui laisse le defaut re-entrable. La regle de ce depot est explicite : une
correction repare douze items, un garde-fou empeche les douze suivants - et un garde-fou n'existe
que si la propriete est **structurelle**.

### 4. Ne montrer que le bouton mis en evidence, sans nommer la reponse - ecartee

Le cadre dirait *"Appuie sur le bouton en surbrillance"*, et le focus (`data-focus-cible`, deja
pose) ferait le reste.

**Ecartee sur deux motifs.** Elle transporte l'information par un **canal visuel seul**, ce que
`M11` clause 1 refuse comme principe general du produit (*"le signal non textuel accompagne le
texte, jamais la couleur seule"*), et elle laisse un enfant qui navigue au clavier ou au lecteur
d'ecran sans rien a lire. Et elle est **moins gardable** : une propriete d'anneau de focus se
verifie mal, la ou l'egalite d'un texte a une valeur se verifie exactement.

### 5. Encoder la regle en `ArchUnit` plutot qu'en test de rendu - ecartee

Interdire, dans `adapter-web`, toute concatenation entre un litteral et une valeur issue de la
session.

**Ecartee, et le motif est mesure.** En Java 21 une concatenation compile en
`invokedynamic makeConcatWithConstants` : les litteraux vivent dans les arguments de bootstrap du
site d'appel, qu'ArchUnit n'expose pas. La regle n'aurait pu porter que sur des noms de types ou de
methodes, c'est-a-dire sur rien de ce qui fait le defaut. Et elle n'aurait rien vu du **gabarit**,
ou vivait la seconde occurrence. Une regle qui ne peut pas voir la moitie de son objet est
decorative.

## Ce que je route, et a qui

| Fait | A qui | Pourquoi ce n'est pas a moi |
|---|---|---|
| **Les trois textes servis** - `"Ce n'est pas ca."`, `"La bonne reponse :"`, `"Appuie sur la bonne reponse :"` - sont des candidats non lus par elle | `nadia-prof` | `M12` : les textes exacts lui appartiennent. Sa reverification est chiffree d'avance a 0,25, et le correctif est livre et jouable, ce qu'elle posait en condition |
| **La seconde occurrence, sur l'ecran de fin de partie**, qu'elle n'avait pas cherchee : `"Non, c'est"` disparait de l'inventaire | `nadia-prof` | Le texte remplace etait le sien (`FICHE-bandit-manchot-additions-cp.md`). La structure est mienne, le mot est a elle |
| **`M12` est enregistree "tenue" sur trois fiches avec les anciens textes cites au mot** ; ces lignes decrivent un produit qui n'existe plus | `camille-po`, `nadia-prof` | Je ne reecris pas une fiche de conformite |
| **`'Pour le ' + niveauActif`** sur l'ecran d'accueil : saine sur `CP` et `CM1`, fausse sur un niveau au nom feminin ou pluriel | `camille-po` | Aucun defaut ne s'y realise ; le declencheur est l'entree d'un troisieme niveau au catalogue, une decision de produit |
| **`CONS-1`** - la consigne generique passee de 5 a 10 jeux en un sprint | `camille-po`, `nadia-prof` | Voir ci-dessous : c'est du contenu, et le garde-fou possible est faible |

### Sur `CONS-1`, puisqu'on me demande si un garde-fou pourrait le compter

**Oui pour le comptage, non pour la propriete, et la difference est celle qui rend une regle
decorative.**

Ce qu'un garde-fou peut faire aujourd'hui, sans arbitrage : deriver du catalogue reel la population
des consignes, et refuser qu'une meme consigne serve **plus de N entrees** - un cliquet exact au
sens d'`ADR-0067`, dont le plafond vaut exactement la dette du jour (**10**) et ne peut que
baisser. Il coute une trentaine de lignes, sa population derive de `catalogue.yaml`, et il aurait
transforme la tendance de `+5 par sprint` en un rouge au **onzieme** jeu. C'est reel, et c'est
faible : il ne dit rien des dix jeux d'aujourd'hui, et il compte une **repetition**, pas une
consigne qui ne travaille pas. Deux consignes generiques differentes le passeraient vert.

Ce qu'un garde-fou **ne peut pas** faire : verifier que *"la consigne nomme la tache de son jeu"*.
C'est la propriete que `nadia-prof` demande, et elle porte sur le rapport entre un texte et ce que
le jeu demande de faire - aucun automate du depot ne lit cela. Toute tentative retomberait sur une
liste de tournures interdites, c'est-a-dire sur un critere enumere sous une promesse universelle,
vert par defaut sur la onzieme formulation.

**Je ne l'ecris pas de ma propre initiative** : `CONS-1` n'est pas dans mon perimetre, le cliquet a
un cout de maintenance reel, et son plafond de 10 fige une dette que `camille-po` et `nadia-prof`
n'ont pas encore decide de traiter. Si l'une des deux le demande, il est ecrit dans le sprint ;
c'est leur decision, pas la mienne.
