# ADR-0084 - La reponse d'une fin de partie voyage avec la question qu'elle commente : l'enonce est une composante obligatoire du verdict

- **Statut** : Accepte
- **Date** : 2026-09-06
- **Auteur** : theo-archi
- **Sprint** : ecrit pendant la passe de faisabilite de s30, avant l'ouverture du sprint. La story
  qui le porte dans un sprint appartient a `camille-po` ; ce qui est tranche ici est structurel.
- **Route par** : `nadia-prof`, reverification du 2026-09-06
  (`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, section "Reverification du 2026-09-06",
  point 3), reprise par `docs/backlog/backlog.md`, section "Story a ecrire pour s30 - `REV-3`". Elle
  decrit le symptome et refuse de prescrire la solution : la cause est structurelle et hors de sa
  main. La story dit explicitement qu'elle **ne choisit pas la forme technique** - recomposer
  l'enonce complet, n'en montrer qu'un extrait, ou changer le modele expose a la vue.

## Contexte

### Le fait

L'ecran de fin de partie affiche, quand la derniere manche a ete resolue par revelation :

> Tu n'as pas trouve.
> La bonne reponse : non

Rien, sur cet ecran, ne dit a quelle question `non` repond. Ni la question, ni l'enonce, ni le
texte-support. L'enfant a joue plusieurs manches depuis le debut de la partie ; il lit une valeur
qui ne commente rien de visible. Le meme ecran donne *"La bonne reponse : 9"* sur un paquet
d'arithmetique.

### Ce que ce defaut n'est pas

Il n'est **pas** un effet d'`ADR-0083`, et je le dis parce que la coincidence de date invite a le
croire. Avant s29 le meme ecran disait *"Non, c'est 8."* : la reponse y etait deja seule au monde,
enchassee dans une phrase qui ne portait pas davantage la question. `ADR-0083` a corrige la
**forme** de la phrase ; il n'a ni cree ni aggrave l'orphelinat de la reponse. `nadia-prof` l'ecrit
elle-meme.

### Pourquoi la reponse est orpheline, et c'est une cause et non une negligence

A l'instant ou cet ecran est rendu, **la manche n'existe plus**. `BanditManchotSession.resoudre`
construit le verdict, puis termine la partie en posant `mancheCourante = null` ;
`SaisieSession.resoudre` fait le meme geste. Le seul objet qui survive a la resolution d'une manche
est le **verdict** - c'est exactement ce qu'`ADR-0024` avait etabli en y logeant l'explication, et
ce qu'`ADR-0058` avait repete en y logeant la chaine jugee et la forme de reference.

Le controleur ne pouvait donc pas afficher la question : il ne l'avait plus. Ce n'est pas un oubli
de gabarit, c'est un manque dans le contrat que le domaine expose a l'adapter.

### Pourquoi rien ne le mordait

Aucun garde-fou du depot n'exige qu'un ecran servi porte **deux champs du meme item**. Ceux de
rendu verifient qu'un champ atteint son ecran - la consigne
(`LaConsigneDUnJeuServiAtteintLEcranDeMancheTest`), le texte-support, l'illustration, le
bloc-adulte - ou qu'un texte de mecanique reste invariant (`ADR-0083`). Un **appariement** est
d'une autre nature : il ne se casse pas par une absence, il se casse par une **association fausse**,
et une association fausse ressemble a un ecran complet.

## Decision

**Un verdict porte l'enonce de la manche qu'il ferme, obligatoirement, et l'ecran de fin de partie
rend cet enonce entier dans son propre element, a cote de la reponse et jamais dedans.**

Trois clauses, a l'imperatif.

1. **`enonce` est une composante obligatoire de tout verdict de mecanique servie.** Ni
   `Optional`, ni constructeur de commodite qui la laisse tomber : tout item du contrat de contenu
   porte un enonce, et le compilateur oblige chaque site de construction a le decider. C'est la
   forme exacte du remede qu'`ADR-0024` avait deja applique a l'explication apres le defaut
   d'`ADR-0016`.
2. **L'enonce est transporte entier, jamais tronque, jamais recompose.** Le verdict le transporte,
   le controleur le pose au modele, le gabarit le rend seul et entier dans un element - `ADR-0083`
   clause 1 vaut ici comme ailleurs.
3. **Sur l'ecran de fin de partie, la question et la reponse qu'elle commente sont dans le meme
   bloc.** Le lien entre les deux est structurel - un parent commun -, jamais une proximite de mise
   en page.

### Pourquoi ces trois-la, et pas les deux autres formes que la story laissait ouvertes

- **"Recomposer l'enonce complet"** n'a pas d'objet : il n'y a rien a recomposer. A cet instant le
  domaine n'a plus la manche, et lui demander de la garder en plus du verdict reintroduirait le
  champ parallele qu'`ADR-0024` a nomme comme le defaut d'`ADR-0016` - deux etats a tenir en phase
  a chacune des constructions de session.
- **"N'en montrer qu'un extrait"** demande une politique de troncature : une longueur, une regle de
  coupe, une decision sur ce qu'on garde d'une question de comprehension. C'est du code qui juge un
  texte lu par un enfant, donc une decision qui ne m'appartient pas, et un mode de defaillance
  silencieux - une question coupee au mauvais endroit reste affichee et cesse d'etre une question.
  Ecartee comme decision ; si `nadia-prof` juge un jour qu'un enonce long encombre cet ecran, la
  troncature se decidera avec elle, sur un enonce reel, et la structure posee ici ne changera pas.
- **"Changer le modele expose a la vue"** est ce qui se passe, mais ce n'est pas une alternative :
  le modele ne peut exposer que ce que le controleur possede, et le controleur ne possede que le
  verdict. C'est la meme decision, vue d'un cran plus haut.

### Ce que la decision produit dans le code, et rien de plus

| Fichier | Changement |
|---|---|
| `domain/.../banditmanchot/Verdict.java` | quatrieme composante `String enonce`, obligatoire |
| `domain/.../saisie/SaisieVerdict.java` | sixieme composante `String enonce`, obligatoire |
| `domain/.../banditmanchot/BanditManchotSession.java` | `resoudre` passe `mancheCourante.enonce()` |
| `domain/.../saisie/SaisieSession.java` | `resoudre` passe `mancheCourante.enonce()` |
| `adapters/adapter-web/.../PartieController.java` | `enonceRevele` pose au modele dans les deux ecrans de fin de partie |
| `adapters/adapter-web/.../fragments/retour.html` | un `<p class="question-rappelee">` avant `.reponse-revelee`, sous la meme condition `th:unless="${juste}"` |
| `adapters/adapter-web/.../css/recreo.css` | une regle `.question-rappelee`, valeurs recopiees a l'identique de `.reponse-revelee` |

**Aucun octet de contenu n'est touche. Aucun mot neuf n'est lu par un enfant** : l'element ne porte
que la valeur du champ `enonce`, sans intitule. C'est delibere - un intitule (*"La question :"*)
est un texte servi, donc une decision de `nadia-prof` au titre de `M12`, et l'ajouter plus tard
coute une ligne de gabarit et une entree d'inventaire dans `TexteAccentueDesGabaritsTest`.

### `DesignationVerdict` n'est pas modifie, et ce n'est pas un oubli

La mecanique de designation n'a aujourd'hui **aucun ecran servi** : `PartieController` ne descend
que vers `BanditManchotSession` et `SaisieSession`, et une session de designation y leve
`PartieIndisponible`. Modifier son verdict pour un ecran qui n'existe pas serait une abstraction
ecrite pour un usage imagine. Ce qui remplace l'anticipation est un **rouge date du jour ou la
question se posera** : la population du garde-fou ci-dessous **echoue** sur une forme d'item
qu'aucun ecran de fin de partie ne sait jouer - `ZoneADesigner` aujourd'hui. Le jour ou un paquet de
designation entre au catalogue, l'appariement de son ecran de fin de partie se decide ici, en rouge,
plutot que de passer vert par omission.

## Regle executable

**Fichier** :
`bootstrap/src/test/java/com/recreo/bootstrap/rendu/AucuneReponseDeFinDePartieNEstOrphelineDeSaQuestionTest.java`

Ce n'est **pas une `ArchRule`**, et le motif est celui d'`ADR-0083` section "Regle executable",
mesure et non repris de confiance : la propriete porte sur le **texte servi a l'enfant** et sur la
**structure du document rendu**, pas sur une dependance entre types. Une `ArchRule` ne lit ni un
gabarit Thymeleaf, ni la valeur d'un champ de record a l'execution. Elle aurait pu interdire un nom
de methode, c'est-a-dire rien. C'est un test JUnit qui echoue dans `mvn verify` comme n'importe quel
autre.

### Le critere

Sur chaque ecran de fin de partie reellement servi :

1. un element hors bouton porte la **reponse** seule et entiere (elle l'etait deja depuis
   `ADR-0083` clause 2, et ce garde-fou en depend explicitement - il le dit dans son grief) ;
2. un autre element porte la **question de cette manche-la**, seule et entiere, et les deux
   elements ont **le meme parent** ;
3. **aucun** element ne porte la question d'une **autre** manche de la meme partie.

La clause 3 est ce qui distingue ce garde-fou d'une assertion de presence. Un ecran qui afficherait
systematiquement la question de la premiere manche serait complet, bien mis en page, et faux.

### La population, avec sa methode

**Une partie de deux manches par paquet servi, jouee mal jusqu'au bout sur les deux manches.** Les
jeux viennent du chargeur de production (`new YamlContentPackLoader().games()`, l'appel meme que
`ContenuConfiguration` fait) ; chaque paquet devient une partie de deux manches sur ses deux
premiers items, en gardant son entree de catalogue - titre, consigne, consigne adulte, mecanique,
descripteur - et la clause `M21` de sa source. Aucun paquet, aucun item, aucun texte, **aucune
classe CSS** n'est nomme dans le critere : il cherche des valeurs, pas des selecteurs, pour ne pas
epingler le correctif du jour.

**Mesure** : **54 ecrans de fin de partie, un par paquet servi**, soit 54 parties de deux manches
et 108 items reellement joues. Mesuree sur le reacteur complet, `mvn -B clean verify` a la racine,
machine virtuelle des tests **JDK 21.0.10** (version rapportee par le JDK que la toolchain resout,
`ADR-0071`).

**Pourquoi deux manches et non une.** Sur une partie d'une seule manche, la clause 3 est
indemontrable : il n'existe aucune autre question a laquelle la reponse pourrait etre appariee par
erreur, et le garde-fou serait vert sur un produit fautif. C'est la forme exacte de la regle
decorative que ce depot sanctionne.

**Pourquoi deux items par paquet et non tous.** Le garde-fou voisin d'`ADR-0083` parcourt les 1256
items servis et coute 12 secondes pour cela ; celui-ci n'en avait pas besoin, et je prefere le dire
que de payer par symetrie. L'element qui porte la question ne depend d'**aucun champ facultatif
d'item** - seulement de `juste` - et `enonce` est une composante **obligatoire** du contrat de
contenu, donc jamais absente. La part qui depend reellement de l'item est la **valeur** appariee, et
c'est elle qui est verifiee sur deux items reels de chacun des 54 paquets.

### Les trois obligations d'`ADR-0013`

- **Perimetre derive** : un paquet neuf entre dans la population le jour ou il entre au catalogue.
- **Derivation totale** : une forme d'item qu'aucun ecran de fin de partie ne sait jouer fait
  echouer la construction de la population, avec un message qui dit pourquoi.
- **Mise a l'epreuve** : deux morsures, toutes deux sur la **sortie reelle du serveur**.

### La preuve de la morsure, mesuree et non affirmee

1. **Morsure permanente, dans la suite.** `uneReponseSansSaQuestionFaitRougirCeGardeFou` retire de
   chaque ecran reellement rendu l'element qui porte la question - c'est l'etat exact du produit
   avant ce lot - et exige le rouge. `uneQuestionAppariAUneAutreMancheFaitRougirCeGardeFou` y met
   la question de la **premiere** manche a la place de celle de la derniere et exige le rouge : la
   mutation ne retire rien, elle ment.
2. **Morsure sur le produit d'avant, mesuree.** Le gabarit remis dans son etat d'avant ce lot (la
   ligne `question-rappelee` supprimee, rien d'autre), le garde-fou seul relance sur
   `-pl bootstrap -am` rend **54 griefs sur 54 ecrans**, un par paquet servi, chacun nommant son
   jeu - par exemple `bandit-manchot-additions-cp--deux-manches : l'ecran de fin de partie nomme la
   reponse "1" sans porter la question de la manche qu'elle commente ("0 + 1")`. Le rouge tombe sur
   des paquets reels que ce fichier n'enumere pas.

### Le rouge qu'un garde-fou existant a produit sur le premier jet, et ce qu'il a change

Premier jet du correctif : l'element portait la classe `.enonce`, celle que la question porte deja
sur l'ecran de manche - le choix qui n'ajoute aucune regle CSS. **`ADR-0052` clause 1 l'a arrete**
(`ToutEcranEchangeEstUnDocumentJouableTest.aucunEcranNeMontreUnEnonceSansLaConsigneDeSonJeu`) : tout
ecran qui rend un `.enonce` doit porter la consigne de son jeu, sans quoi *"l'enfant lit une phrase
et trois mots, sans rien qui dise quoi en faire"*.

Cette clause a raison **sur l'ecran de manche**, et elle n'a pas d'objet ici. Sur l'ecran de fin de
partie, l'enfant n'a plus rien a faire de cette phrase : la seule action de l'ecran est d'en sortir.
Ce que l'element porte n'est donc pas un **enonce a jouer**, c'est une **question rappelee**, et la
classe le dit - `.question-rappelee`. La solution alternative aurait ete d'ajouter la consigne du
jeu a l'ecran de fin de partie : un bloc de texte de plus sur un ecran terminal, c'est-a-dire une
decision de produit prise pour satisfaire un garde-fou, exactement le geste que ce depot appelle
defaire un correctif juste.

**Je l'ecris plutot que de le taire, parce que le geste ressemble a une contrefacon** : changer de
classe pour sortir du perimetre d'une regle est aussi ce que ferait quelqu'un de mal intentionne. Ce
qui distingue les deux cas est verifiable et je le pose ici comme declencheur : `ADR-0052` garde
l'ecran ou l'enfant doit **agir** sur l'enonce, et sa population contient toujours des ecrans qui
rendent un `.enonce` - son propre garde-fou d'`ADR-0003` l'exige et resterait rouge si quelqu'un
vidait la classe de son contenu. Le jour ou un ecran **jouable** porterait une question sous un
autre nom que `.enonce`, c'est `ADR-0052` qu'il faudrait rouvrir, et cette section est ce qui
permettra de le voir.

## Consequences

**Bonnes.**

- Les 54 paquets servis, et tous ceux a venir, apparient leur derniere reponse a sa question **sans
  toucher un octet de contenu**.
- Les deux mecaniques servies se comportent de la meme facon sur cet ecran, comme depuis `ADR-0083`
  sur l'ecran de revelation.
- Le depot acquiert son premier garde-fou d'**appariement** : une famille de defaut qui ne se
  manifeste par aucune absence, et qu'aucune regle existante ne pouvait voir.
- Une composante obligatoire de plus sur le verdict rend impossible, au compilateur, d'ajouter une
  mecanique servie dont le verdict oublierait l'enonce.

**Mauvaises, et je ne les adoucis pas.**

- **La question s'affiche sans intitule**, et personne n'a juge que c'est lisible pour un enfant de
  six ans. C'est un arbitrage de `nadia-prof` que je lui route ; ma decision est structurelle et
  reste vraie quel que soit son verdict sur les mots.
- **Le texte-support n'est pas transporte.** Sur un item de comprehension (`ADR-0050`), la question
  rappelee peut renvoyer a un texte que cet ecran ne porte pas - *"Que ressent Lea ?"* sans le
  paragraphe qui le disait. Le cout de l'ajouter est connu et faible (une composante de plus sur les
  deux verdicts, un bloc de plus dans le gabarit) ; je ne l'ajoute pas sans qu'un besoin soit
  constate, et je route le constat a `nadia-prof`, qui joue les ecrans.
- **Une classe CSS de plus** (`.question-rappelee`), dont les trois valeurs sont recopiees a
  l'identique de `.reponse-revelee`. Toute regle neuve elargit la surface que les garde-fous
  d'habillage doivent couvrir ; celle-ci est sous les planchers existants et y est verifiee
  (`TailleDesTextesEtBoutonsTest`, vert).
- **Le poids visuel de cette question n'a pas ete propose par `ines-design`.** J'ai repris des
  valeurs existantes plutot que d'en inventer, ce qui est le choix le moins engageant, pas le choix
  juste. Route.
- **Le garde-fou coute un contexte Spring de plus** et environ 9 secondes (54 parties, 486 echanges
  HTTP). Mesure sur `-pl bootstrap -am`, JDK 21.0.10.

## Alternatives considerees

### 1. Garder la manche resolue sur la session, a cote du verdict - ecartee

La session conserverait `derniereMancheResolue` en plus de `dernierVerdict`.

**Ecartee, et le motif est deja ecrit dans ce depot.** C'est le champ parallele qu'`ADR-0024`
identifie comme la forme exacte du defaut d'`ADR-0016` : deux etats a tenir en phase a chacune des
constructions de session - il y en a quatre par mecanique - dont l'un peut etre oublie sans qu'aucun
compilateur ne le dise. Le verdict est deja l'objet qui survit a la resolution d'une manche ; lui
ajouter une composante obligatoire ferme le cas au compilateur, la ou un champ de session le laisse
a la vigilance.

### 2. Faire porter l'appariement par une liste de manches jouees - ecartee

La session accumulerait tous ses verdicts, et l'ecran de fin de partie listerait les manches
manquees avec leurs questions.

**Ecartee, et c'est la decision la plus proche d'un refus de perimetre.** Ce serait un **ecran
different** - un bilan de partie -, pas la reparation d'un appariement. Le critere d'acceptation de
`REV-3` parle de l'ecran tel qu'il existe, et ce que l'ecran de fin de partie doit montrer a un
enfant de CP qui vient de perdre est une decision de `nadia-prof` et de `camille-po`, pas la mienne.
Techniquement, elle coute une composante de session de plus, un gabarit de boucle, et elle ouvre
immediatement la question de la longueur de la liste sur une partie de dix manches. Si le produit la
veut un jour, la structure posee ici est celle sur laquelle elle se construit : le verdict porte
deja son appariement.

### 3. Montrer un extrait de l'enonce - ecartee

Voir plus haut : une politique de troncature est du code qui juge un texte lu par un enfant, et son
mode de defaillance est silencieux.

### 4. Ajouter la consigne du jeu a l'ecran de fin de partie pour garder la classe `.enonce` -
### ecartee

Voir "Le rouge qu'un garde-fou existant a produit sur le premier jet". Elle fait prendre une
decision de produit - un bloc de texte de plus sur un ecran terminal - pour satisfaire une regle
dont l'objet est un autre ecran.

## Ce que je route, et a qui

| Fait | A qui | Pourquoi ce n'est pas a moi |
|---|---|---|
| **La question s'affiche sans intitule.** Faut-il un mot devant (*"La question :"*), et lequel ? | `nadia-prof` | `M12` : les textes exacts lui appartiennent. Le correctif est livre et jouable, sa condition de rejeu |
| **Le texte-support n'est pas rappele** sur cet ecran : une question de comprehension peut renvoyer a un paragraphe absent | `nadia-prof` | Le besoin se constate en jouant, pas en lisant le code. Cout connu et faible si elle le demande |
| **Le poids visuel de la question rappelee** : valeurs reprises de `.reponse-revelee`, non proposees | `ines-design` | Une proposition d'apparence n'est pas de mon ressort |
| **La story de s30 qui porte ce lot**, son rang et son affectation | `camille-po`, `sofia-sm` | Je ne priorise pas et je n'affecte personne |
| **Le Done pedagogique de `REV-3`** : rejeu sur les six paquets de la reverification de `REV-1` | `nadia-prof` | Je ne prononce jamais le Done pedagogique |

## Renvoi du 2026-09-08 (s32) - la section "`DesignationVerdict` n'est pas modifie" a change de condition

La section ci-dessus decide de ne pas toucher `DesignationVerdict`, et son motif est ecrit : aucun
ecran de designation n'existait, et *"modifier son verdict pour un ecran qui n'existe pas serait une
abstraction ecrite pour un usage imagine"*. **Ce motif etait juste et il est perime.**

Ce qui a change le 2026-09-08 : l'utilisatrice finance un lot de contenu de designation, et
`nadia-prof` ecrit que ce verdict sans enonce est **bloquant** - *"Je ne valide aucun lot de
designation avant."* `ADR-0088` decide donc que `DesignationVerdict` porte son enonce, et **etend
`REV-3` a toute classe qui juge une manche** par une clause ArchUnit, plutot que de la redecider
mecanique par mecanique : ce qui a manque pendant quatre sprints n'est pas la decision, c'est ce qui
allait la rappeler.

Ce qui reste ecrit ici sans changement : la propriete de `REV-3`, son garde-fou de rendu, et le
mecanisme que cette section-la annoncait pour le jour ou la question se poserait - *"la population du
garde-fou echoue sur une forme d'item qu'aucun ecran de fin de partie ne sait jouer"*. **Ce mecanisme
a tenu** : c'est encore lui qui rendra rouge l'ecran de fin de partie de designation le jour de T6.

## Renvoi du 2026-10-14 (s57) - l'ecran de fin d'un jeu a reponse en image ne pose plus la question rappelee

`ADR-0104`, amendement du 2026-10-14 : sur l'ecran de fin d'un jeu dont la bonne reponse est une reproduction, ni la reponse, ni la question
rappelee, ni l'explication ne sont posees (voie (b) de `RDO-CP-R2`, choisie par `nadia-prof`). La clause 3 n'y a plus d'objet. Les clauses
1 et 2 ne changent pas : le verdict porte toujours l'enonce. Regle executable : celle de l'amendement d'`ADR-0104`.
