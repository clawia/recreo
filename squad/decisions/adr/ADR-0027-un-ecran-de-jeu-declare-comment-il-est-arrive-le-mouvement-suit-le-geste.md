# ADR-0027 - Faire declarer par le serveur comment un ecran de jeu est arrive, et n'autoriser un mouvement que sur un geste de l'enfant

- **Statut** : Accepte
- **Date** : 2026-08-10
- **Auteur** : theo-archi
- **Sprint** : s07
- **Amende** : `ADR-0026` section 2
- **Amende le 2026-08-13 (s10)** : sa clause 2 exigeait qu'un ecran echange par htmx declare le geste
  **sans exception**. Elle etait juste tant qu'un echange htmx renouvelait toujours la question, et le
  cycle de manche de S10 l'a rendue fausse sur trois etats. Elle devient un "si et seulement si", et le
  sujet du marqueur - le tirage, pas la reponse HTTP - est enfin nomme (condition M10 de `nadia-prof`,
  acceptee par avance : "je refuse, et j'accepte le cout de l'amendement")

## Contexte

`nadia-prof` valide le mecanisme d'`ADR-0026` et me route un defaut qu'il contient (arbitrage
prealable du 2026-08-10, `squad/conformite/FICHE-bandit-manchot-additions-cp.md`, section 6). Elle
decrit le symptome et ne prescrit pas la cause. La voici.

### Le defaut

`accueil.html` rend le fragment de manche en clair, sans htmx et sans aucun geste, sur la branche de
reprise silencieuse de S1-4 :

```
<div th:if="${reprise}">
    <div th:replace="~{fragments/manche :: manche}"></div>
</div>
```

Un enfant qui recharge sa page pendant que sa session est valide verrait donc les colonnes tourner
sans avoir rien touche. `nadia-prof` l'interdit nommement, et c'est une de ses quatre conditions non
negociables : le message qu'un ecran doit porter est que l'enfant agit, pas que la machine tourne
toute seule. Si l'enfant apprend que les colonnes tournent sans lui, l'argument qui sauve la
metaphore tombe - c'est elle qui l'ecrit, et l'argument est le sien.

### La cause reelle, et elle est dans mon propre texte

`ADR-0026` section 2 dit : "une animation CSS demarre quand l'element qui la porte entre dans le
document. htmx insere le fragment de manche dans `#jeu`. Une declaration `animation` sur les colonnes
suffit donc."

La premiere phrase est exacte. La troisieme est fausse, et la deuxieme explique pourquoi je ne l'ai
pas vu : **j'ai pris "entrer dans le document" pour la propriete qui m'interessait, alors que la
propriete qui m'interesse est "avoir ete demande par un geste".** Ce sont deux choses differentes,
parce qu'un element entre dans le document de deux facons :

1. par un echange htmx, qui est toujours la reponse a un appui de l'enfant ;
2. par l'analyse d'une page entiere que le navigateur charge, ce qu'aucun appui dans l'application ne
   declenche - une navigation, un rechargement, un signet.

J'ai confondu une propriete **structurelle** avec la propriete **qui compte**, et j'ai ecrit
"insertion" en pensant "htmx" parce que htmx etait le seul chemin que j'avais sous les yeux. C'est
exactement la famille de faute que ce depot a deja actee deux fois de ma main : `ADR-0022` a du
corriger "hors de la zone echangee" pris pour "visible sur tous les ecrans", et la limite 5
d'`ADR-0017` disait "un ecran est un fichier de gabarit" jusqu'a ce qu'un en-tete permanent la
demente. Troisieme occurrence, meme forme, et je l'inscris comme telle plutot que comme un oubli.

### Pourquoi le CSS ne peut pas rattraper cela, et pourquoi ce n'est pas un choix

Une fois le fragment insere, **le document assemble est exactement le meme dans les deux cas** : meme
arbre, memes classes, meme place dans `<main id="jeu">`. Aucun selecteur CSS ne peut donc distinguer
les deux chemins, et `:has()` n'y change rien. La distinction n'a **aucune existence possible** si le
serveur ne la pose pas. Ce n'est pas une preference de conception, c'est une contrainte, et elle
determine seule la forme de la solution.

## Decision

### 1. Le serveur declare, sur chaque ecran de jeu qu'il rend, comment cet ecran est arrive

`fragments/manche.html` porte desormais un second marqueur d'etat, a cote de celui d'`ADR-0022` :

```
<div th:fragment="manche" class="ecran-de-jeu"
     th:attr="data-arrivee=${arriveeParUnGeste} ? 'par-un-geste' : 'sans-geste'">
```

Les deux marqueurs ne disent pas la meme chose, et c'est pourquoi il en faut deux : `ecran-de-jeu`
dit **ou** l'enfant est, `data-arrivee` dit **comment** il y est arrive.

**Un attribut et non une classe.** J'ai ecrit la classe d'abord, et un garde-fou existant l'a
refusee : `HabillageDesEcransTest.aucuneClassePoseeSurUnElementNEstOrpheline` exige que toute classe
posee sur un element soit nommee par une regle servie. Comme aucune animation n'existe encore, la
satisfaire aurait demande une regle CSS qui ne style rien - decorative, et refusee ici comme partout
ailleurs dans ce depot. Le refus etait juste, et il pointe l'idiome que le depot possede deja :
`ADR-0017` a choisi `data-role` precisement parce que ce qu'il declare n'est pas un habillage. Un
selecteur d'attribut, `[data-arrivee=par-un-geste]`, s'accroche aussi bien le jour ou une animation
existe. **Ce garde-fou m'a corrige avant la revue, et c'est la troisieme fois que celui d'un autre
lot le fait ; je le note plutot que de presenter le choix final comme le premier.**

### 2. La declaration est un parametre, pour que le compilateur rende la derivation totale

`PartieController.mancheModele` est le **seul point de passage** de l'ecran de jeu - sa Javadoc le
disait deja, `ADR-0020` s'y appuie. Il recoit donc un parametre `boolean arriveeParUnGeste`, et non un
attribut de modele pose a cote :

| Appelant | Ce que l'enfant a fait | Valeur |
|---|---|---|
| `accueil()`, branche de reprise | rien : le navigateur a charge une page | `SANS_GESTE` |
| `demarrer()`, `POST /parties/{jeuId}` | presse le titre d'un jeu | `PAR_UN_GESTE` |
| `continuer()`, `GET /parties/{jeuId}/manche` | presse "Encore !" | `PAR_UN_GESTE` |

**Un parametre parce que le compilateur oblige alors chaque point d'appel a se prononcer**, ceux
d'aujourd'hui comme ceux de demain. Une route neuve qui rendrait l'ecran de jeu ne peut pas oublier de
declarer son arrivee : elle ne compile pas. C'est la meme raison qui a fait mettre le controle de fin
de partie dans ce point de passage unique plutot que dans chaque route (`ADR-0020`), et c'est le seul
endroit du chantier ou une derivation totale s'obtient du compilateur plutot que d'un test.

**Amendement du 2026-08-13 : un geste ne suffit pas, et le tableau ci-dessus etait complet sans etre
juste.** Il enumere correctement les trois points d'appel de s07, et c'est bien la valeur que chacun
declare. Ce qui etait faux est la phrase implicite qui les relie - "un geste, donc un mouvement" -
tenable tant qu'un echange htmx renouvelait toujours la question, et fausse depuis le cycle de manche
de S10. `POST /parties/{jeuId}/coups` rend maintenant un ecran de manche sur **cinq** etats, dont
trois laissent l'enfant sur la meme question : un echec avec des essais restants, l'entree en
revelation, un appui a cote apres la revelation. Sur ces trois-la, un geste a bien eu lieu et rien
n'est arrive.

Le motif du refus est celui de `nadia-prof` (condition M10 de sa fiche, `FICHE-bandit-manchot-
additions-cp.md`), et il est mesure plutot qu'esthetique : elle a valide le defilement au motif que la
machine **distribue** les reponses, ce qui est la phrase qui sauve la metaphore ; rejoue sur des
propositions inchangees, le mouvement dit que quelque chose a ete distribue alors que rien ne l'a ete,
un enfant de CP croit que la question a change et relit tout, et le prix est de 0,6 s par defilement,
jusqu'a trois essais par question, dix questions - **jusqu'a 18 s d'attente ajoutee pour l'enfant qui
se trompe le plus**. Elle a accepte cet amendement par avance et dans les memes mots : "je refuse, et
j'accepte le cout de l'amendement".

**Ce qui change : le sujet du marqueur, enfin nomme.** `data-arrivee` repond a la question "comment
cet ecran est-il arrive ?", et je n'avais jamais ecrit **ce qui** arrive. La reponse est **le tirage
que les colonnes montrent**, pas la reponse HTTP. Sur un echec, la reponse HTTP arrive et le tirage
n'arrive pas : rien n'est distribue, et le marqueur reste ferme. Cette precision est du meme genre que
celle qui a fonde cet ADR - une propriete prise pour une autre, faute d'avoir ecrit de quoi on parle -
et c'est la quatrieme occurrence de ma famille de faute, apres `ADR-0022`, la limite 5 d'`ADR-0017` et
la section 2 d'`ADR-0026`.

**Ou la condition est calculee, et pourquoi pas au point d'appel.** `marc-dev` proposait
`arriveeParUnGeste = session.propositionsEssayees().isEmpty()` dans `jouer()`. La valeur est juste ; le
placement, non - il exempterait une route de la regle generale, et la route suivante qui rendrait
l'ecran de jeu declarerait de nouveau un geste nu. La condition est donc le **produit de deux
facteurs**, et chacun vit la ou il est connu :

| Facteur | Qui le connait | Ou il est ecrit |
|---|---|---|
| un geste de l'enfant a demande cette reponse | le point d'appel, lui seul | parametre `gesteDeLEnfant`, une valeur par appelant, le compilateur exige qu'elle soit donnee |
| ce geste a fait arriver un tirage neuf | la session de la mecanique, la meme pour tous les appelants | `mancheModele`, en un seul endroit : `gesteDeLEnfant && fraiche` |

Le second facteur est **la valeur que ce point de passage calculait deja** pour le message et le point
de focus (`fraiche`, M12 et M13) : l'amendement n'ajoute aucun etat, aucun champ, aucun parametre. Il
ajoute un `&&`. Le mode de defaillance ne change pas de sens : un facteur oublie ferme le marqueur et
rend l'immobilite, jamais un mouvement non demande.

**Ce que je n'ai pas renomme, et le cout de ce choix.** Les deux valeurs restent `par-un-geste` et
`sans-geste`. Elles nomment la cause qui autorise le mouvement, pas la condition complete, et
quelqu'un les lira comme "cette reponse vient d'un appui". Les renommer toucherait 6 declarations de
`recreo.css`, 2 lectures de `LAnimationRetardeMaisNeDetermineRienTest`, et **16 jeux fautifs de trois
autres lots** dont ce n'est pas le sujet, pour ne rien changer a ce que l'automate interdit. Le prix du
malentendu est un commentaire de gabarit ; le prix du renommage est un chantier transverse. Ce qui me
ferait changer d'avis : un troisieme etat d'arrivee, qui obligerait de toute facon a nommer les chemins
au lieu de les compter (deja ecrit plus bas).

**Le mode de defaillance est l'immobilite, jamais un mouvement non demande.** Un marqueur oublie, mal
ecrit, ou une future version d'htmx qui changerait de comportement : dans tous ces cas le selecteur ne
correspond a rien et rien ne bouge. C'est le bon sens de defaillance, et c'est ce qui rend ce
mecanisme preferable a la lecture d'un en-tete `HX-Request` (voir alternatives).

### 3. Aucune animation n'est atteignable hors du marqueur de geste

Toute regle maison qui anime quelque chose nomme `[data-arrivee=par-un-geste]` dans son selecteur.
`ADR-0026` regle 1 est donc etendu : une animation qui ne le nomme pas tournerait sur la reprise, et
c'est le defaut lui-meme.

**Consequence que j'ai verifiee plutot qu'esperee** : le marqueur n'est pose que sur l'ecran de
manche, jamais sur `fragments/retour.html`. **Aucune animation ne peut donc atteindre l'ecran de
retour, ecran de fin de partie compris**, ce qui satisfait structurellement l'acquis 6 de
`nadia-prof` - aucun levier, aucune colonne, aucun decor, aucune animation sur cet ecran. Je nomme
tout de suite la limite de cette bonne nouvelle : elle cesse d'etre structurelle le jour ou quelqu'un
pose le marqueur sur l'ecran de retour, ce qui serait legitime en soi puisque cet ecran arrive
toujours par un geste. **A cet instant l'acquis 6 redevient garde par `nadia-prof` seule**, et le
garde-fou de saillance n'a par construction rien a comparer sur un ecran ou un seul bouton s'affiche.
Personne ne doit croire cet acquis garde pour toujours.

### 4. La duree du defilement cesse d'etre non gardable, parce qu'elle a maintenant un chiffre

`ADR-0026` a declare la duree non gardable et l'a confiee a `nadia-prof`. **C'etait juste tant
qu'aucun seuil n'existait, et ca ne l'est plus** : son arbitrage en donne trois. Une clause devient
gardable le jour ou son destinataire donne un nombre, et c'est le mouvement le plus utile de cet ADR.

| Ce qu'elle fixe | Valeur | Garde |
|---|---|---|
| duree de l'appui a l'arret de la derniere colonne | **0,3 s a 1,2 s** | automate, clause 6 |
| valeur conseillee | 0,6 s | conseil, non garde |
| decalage entre premiere et derniere colonne, compris dans le budget | **0,25 s au plus** | automate, clause 6 |
| rebond, oscillation, arret brutal, stroboscope au-dela de 3/s | refuses | **`nadia-prof` seule** |

Ce qui reste a `nadia-prof` est ce qu'une feuille de style ne dit pas : la courbe percue, le rebond
obtenu par une suite d'images cles, et le lien percu entre le geste et le mouvement. Ce qui passe a
l'automate est ce qu'elle a chiffre.

### 5. "Jamais un autre mot" : gardee sur ses deux formes realisables, le residu lui reste

Sa question directe, et voici la reponse en trois parties.

**Non, `ADR-0026` ne couvrait pas cette propriete.** Sa clause 3 interdit un etat permanent qui rend
une proposition inactionnable ; elle ne dit rien du texte affiche.

**Amendement du 2026-08-10, porte technique de s07 : "les deux seules formes" en etaient trois, et
c'est le lot de `lea-dev` qui a livre la troisieme.** Le texte ci-dessous disait "les deux seules
formes qu'un rouleau reel peut prendre" et enumerait un leurre rendu par le serveur et un mot
fabrique par `content`. S7-1 a retenu une troisieme voie que je n'avais pas nommee : le voile est
confine par `clip-path`. La propriete ne fabrique aucun texte - la clause 7 ne la voit donc pas -
mais appliquee non plus au voile et a l'element qui **porte** le mot, elle en retranche une partie,
et **un mot retranche peut etre un autre mot**. Ce n'est pas une hypothese : sur `cm1-homophones`,
dont la section "mesure" d'`ADR-0026` releve les deux propositions `sont` et `son`, une decoupe
laterale rend la bonne reponse identique a la fausse. Un seul caractere separait la production de ce
defaut, le `::after` du selecteur, et rien ne le tenait - mesure par mutation reelle de `recreo.css`
au commit `d53181b` : **149 cas verts sur 149**. La clause 8 de la regle 2 le ferme, et
l'enumeration "deux formes" ci-dessous devient **trois**. Le residu du dernier paragraphe, lui, ne
bouge pas.

**Oui, elle est gardable sur les trois formes qu'un rouleau reel peut prendre**, et les trois sont
desormais gardees :

1. **un mot leurre rendu par le serveur** - la bande de faces intermediaires, la forme classique. Ce
   sont des elements que le serveur envoie dans le conteneur de propositions. La clause verifie, sur la
   sortie reelle du serveur, que **tout texte porte a l'interieur de `.propositions` est l'une des
   propositions**. Une bande de leurres la fait rougir ;
2. **un mot fabrique par la feuille de style** - `content` sur un pseudo-element, la seule propriete
   CSS qui produit du texte. La clause refuse toute valeur de `content` autre que la chaine vide ou
   `none`. La chaine vide reste autorisee parce que c'est de la decoration pure et que les deux barres
   de la croix du signal faux l'emploient deja : ce sont ces deux regles qui rendent la clause non
   vide des aujourd'hui.

3. **un mot retranche par une decoupe geometrique** - `clip-path` sur l'element qui porte le texte,
   la forme que S7-1 a rendue atteignable en confinant le voile par cette propriete. La clause 8
   refuse `clip-path` sur toute regle maison permanente qui nomme une classe d'action et dont le
   **sujet** - son dernier compose - n'est pas un pseudo-element decoratif `::before` ou `::after`.
   Une boite generee a cote du texte n'est pas le texte : la forme de S7-1 reste autorisee, et un jeu
   fautif corrige le prouve.

Une quatrieme forme, un mot produit par du JavaScript, etait deja fermee par `ADR-0026` regle 2
(`proposition` et `colonne` interdits dans le JavaScript maison).

**Le residu, et il reste a `nadia-prof`** : un mot **peint dans une image** (fond `background-image`,
SVG en `data:`, sprite vendorise) echappe aux trois clauses, et la question de savoir si un mot reste
lisible **pendant** qu'il glisse est une propriete du rendu qu'aucun automate de ce depot ne produit
(`ADR-0025`). Je ne l'elargis pas a la presence du texte dans une image : ce serait une regle contre
un defaut que le depot n'a pas etabli, et elle interdirait au passage tout pictogramme legitime.

**Ce que la clause 8 ne ferme pas, dit avant qu'on le decouvre.** Elle ne porte que sur `clip-path`,
la propriete que S7-1 a fait entrer. Deux voisines restent ouvertes, chacune pour un motif ecrit :
`overflow: hidden` sur une proposition, parce qu'`ADR-0026` accorde nommement a `ines-design` la
latitude d'une fenetre a `overflow: hidden` avec une bande qui glisse, et la fermer ici retirerait une
latitude que j'ai donnee ; et une largeur ou un `text-overflow` qui tronquerait le mot, qui est la
famille de debordement qu'`ADR-0025` a declaree non gardable parce qu'elle se joue en pixels rendus.
Le declencheur qui rouvrirait la premiere : le jour ou une proposition porte `overflow: hidden` sans
etre un habillage, la latitude et la clause se contredisent et c'est la latitude qui doit ceder.

Sa liste des huit choses dont elle reste seule garde en comptait **six** apres cet ADR : ses points
1 (partiellement : la courbe et le rebond, plus la duree), 2, 3, 4 (partiellement : le residu en
image), 5 (partiellement : le residu en image), 6 et 7. Son point 8 - "le mouvement ne se declenche
jamais sans geste" - **lui est rendu par un automate**, et c'est l'objet principal de cet ADR.

## Regles executables

### Regle 1 - `LEcranDeJeuDeclareCommentIlEstArriveTest`, la moitie serveur

Fichier :
`adapters/adapter-web/src/test/java/com/recreo/adapter/web/LEcranDeJeuDeclareCommentIlEstArriveTest.java`

`@WebMvcTest` sur la sortie reelle du serveur, corpus produit en jouant une partie de bout en bout.
Perimetre derive : les routes viennent de `RequestMappingHandlerMapping` comme dans
`ToutEcranEchangeEstUnDocumentJouableTest`, et **ce qui distingue un document complet d'un fragment
est lu dans la reponse elle-meme** - un `<!doctype` et un element `<html>` - jamais dans une liste de
routes ecrite dans le test. C'est le seul critere qui ne se perime pas : un fragment est par
definition ce que htmx echange, un document complet est par definition ce que le navigateur charge.

Cinq clauses, dont **quatre ont une population de production non vide aujourd'hui** :

1. **aucun ecran de jeu arrivant dans un document complet ne declare `par-un-geste`**, et il declare
   bien `sans-geste` plutot que rien ;
2. **tout ecran de jeu echange par htmx declare `par-un-geste`**, et le marqueur ne prend qu'une des
   deux valeurs connues. Sans cette clause, ne jamais poser le marqueur rendrait la clause 1 verte a
   vide et aucun rouleau ne tournerait jamais : les deux clauses se tiennent par les deux bouts ;
3. **chaque route du controleur est exercee** ;
4. **le corpus porte les deux populations**, et les documents complets sont ceux du navigateur et eux
   seuls ;
5. **epinglage du chemin fautif** : la reprise rend bien un ecran de jeu dans un document complet. Si
   `camille-po` decide que la reprise ne rend plus la manche en clair, ce test devient rouge et son
   message dit que la clause 1 perd son objet - le defaut aurait disparu, et cet ADR devrait etre
   repris plutot que laisse vert a vide.

Plus la clause "jamais un autre mot", versant serveur : **aucun texte autre que les propositions
n'entre dans le conteneur de propositions.**

#### Amendement du 2026-08-13 : la clause 2 devient un "si et seulement si", et la clause 1 en devient un cas

**La formulation retenue.** *Un ecran de jeu declare `par-un-geste` si et seulement si un geste de
l'enfant a fait arriver le tirage que ses colonnes montrent.* Les deux clauses ne sont plus deux
regles differentes mais **les deux populations d'un meme calcul**, ecrit une seule fois
(`arriveeAttendue(echangeParHtmx, tirageNeuf)`) : la clause 1 est la population ou le premier facteur
est faux, la clause 2 celle ou les deux sont vrais. Deux calculs libres de diverger laisseraient un
ecran juge par ni l'un ni l'autre, ce qu'`ADR-0033` point 3 a deja etabli sur un autre couple de
clauses.

**Ce que la clause interdit, et je l'ai verifie plutot qu'espere** : les trois etats que la condition
M10 nomme - echec avec des essais restants, entree en revelation, appui a cote apres la revelation -
sont refuses s'ils declarent le marqueur, et **le troisieme n'etait pas dans le corpus** ; il y entre.
Sans lui, une correction qui n'aurait ferme que les deux premiers passait verte, et c'est la lecon
qu'`ADR-0034` a apprise a ce depot il y a deux jours - un perimetre derive par route ne dit rien des
**etats** qu'une route rend.

**Comment "le tirage est neuf" est lu, et pourquoi pas autrement.** La tentation etait de comparer
l'ecran a celui d'avant : deux echanges qui rendent les memes colonnes n'ont rien renouvele. **Cette
lecture est impossible, et c'est un fait et non une preference** : deux manches d'un meme item rendent
un enonce et des propositions identiques, ce que `jeu-repete` fait exactement, donc la comparaison ne
distingue pas une question neuve d'une question inchangee. Ce qui distingue les deux est la **trace
d'un essai deja fait sur ce tirage** - la marque que le serveur pose sur une proposition deja pressee
(M9). Elle est lue **dans le gabarit** et non ecrite dans le test (`ADR-0015`), sur l'attribut qui
choisit un litteral selon `${propositionsEssayees}`.

**Le vert silencieux que cette lecture ouvrirait, et sa fermeture.** Si la marque devient introuvable
- renommee, calculee dans le modele, retiree - tout ecran passerait pour un tirage neuf, le "si et
seulement si" redeviendrait le "tout echange declare le geste" d'avant, et **aucun test ne rougirait**.
La lecture echoue donc au lieu de rendre une marque vide, et un jeu fautif l'etablit
(`marque-d-essai-sans-litteral`). C'est la contrainte que mon propre garde-fou de balayage m'a imposee
avant la revue : en lisant un fichier de `src/main/`, cette classe est entree dans la population
d'`ADR-0013` et devait un jeu fautif. **Quatrieme fois que le garde-fou d'un autre lot, ou le mien,
me corrige avant la revue, et je le note plutot que de presenter le resultat comme la premiere
ecriture.**

**Preuves de morsure de l'amendement, sur la production reelle et sur des objets neufs.**

| Mise a l'epreuve | Attendu | Mesure |
|---|---|---|
| **mutation de la production** : `mancheModele` repose `gesteDeLEnfant` seul, c'est-a-dire l'etat du depot la veille de cet amendement | la clause 2 rouge, sur les trois etats | **2 echecs sur 10 cas**. La clause 2 nomme les trois : "manche apres une reponse fausse", "manche en revelation", "appui a cote apres la revelation", chacun avec le motif de M10 et la valeur exigee |
| **objet neuf, sens "seulement si"** : l'ecran d'un echec tel que le serveur le rend, dont le seul marqueur est mute a `par-un-geste` | un ecart, et son jumeau corrige vert | rouge, "l'enfant a deja essaye une proposition de ce tirage" ; la meme sortie non mutee rend **zero** ecart |
| **objet neuf, mode de defaillance d'origine** : l'ecran de la reprise silencieuse, mute a `par-un-geste` | un ecart, sur son propre motif et jamais celui de M10 | rouge, "Le navigateur a charge une page entiere" ; la reprise non mutee rend **zero** ecart. **C'est la mesure qui etablit que l'amendement n'a pas rouvert le defaut d'origine** |
| **jeu fautif** `marque-d-essai-sans-litteral` : la marque calculee dans le modele | la lecture refuse au lieu de rendre une marque introuvable | rouge, "la marque d'un essai deja fait se lit dans" |

Les deux mises a l'epreuve sur objet neuf mutent **la sortie reelle du serveur** et non une fixture
ecrite de ma main : la lecon de s05 s'applique a mon propre amendement comme aux autres.

**Ce que cette clause ne garde toujours pas.** Qu'un mouvement soit **percu** comme lie au geste reste
a `nadia-prof` (deja ecrit plus bas). Et le marqueur ne dit rien de ce qui **change** sur l'ecran d'un
echec : le message apparait, et c'en est justement le signal - `nadia-prof` l'ecrit elle-meme dans son
"ce que je n'exige pas". Cette clause ne rend donc pas l'ecran d'un echec immobile en tout, et ce
n'est pas un manque.

**Preuves de morsure, par trois mutations reelles de la production.** Chacune relancee, production
restauree et suite reverifiee verte apres coup.

| Mutation | Attendu | Mesure |
|---|---|---|
| `accueil()` passe `PAR_UN_GESTE` : le defaut, reintroduit tel quel | clause 1 rouge | **2 echecs sur 6 cas**. Message : "sur \"accueil pendant une partie en cours (reprise silencieuse)\" (GET /), le serveur rend un ecran de jeu dans un document complet [...] et il declare `data-arrivee="par-un-geste"`. Les colonnes tourneraient devant un enfant qui a seulement recharge sa page" |
| polarite du marqueur inversee dans le gabarit (relecture adverse) | les deux clauses rouges, chacune de son cote | **3 echecs sur 6 cas** : la clause 1 sur la reprise, la clause 2 sur les deux echanges htmx. C'est la mutation qui prouve que les deux bouts sont tenus - une seule des deux clauses aurait laisse passer l'inversion |
| le marqueur disparait du gabarit | l'ecran muet est refuse, pas ignore | **3 echecs sur 6 cas**, dont "cet ecran de jeu ne declare pas comment il est arrive" et "declare `data-arrivee="<aucune>"` au lieu de `par-un-geste`" |

La deuxieme est celle qui compte le plus. Elle etablit qu'aucune des deux clauses ne suffit seule, ce
qu'aucun cas nominal ne dit : la clause 1 est verte sur un depot ou rien ne bouge jamais, la clause 2
est verte sur un depot ou tout bouge tout le temps.

### Regle 2 - `LAnimationRetardeMaisNeDetermineRienTest` etendu, la moitie CSS

Trois clauses s'ajoutent aux quatre d'`ADR-0026`.

- **Clause 5** : toute animation nomme `[data-arrivee=par-un-geste]` dans son selecteur.
- **Clause 6** : duree entre 0,3 s et 1,2 s, retard au plus 0,25 s, duree plus retard au plus 1,2 s.
  Une regle qui anime sans declarer de duree lisible est un echec et non un cas ignore : on ne
  confronte pas a une barre un chiffre qu'on ne lit pas.
- **Clause 7** : aucune regle maison ne declare `content` avec autre chose que la chaine vide ou
  `none`.
- **Clause 8, ajoutee par l'amendement du 2026-08-10** : aucune regle maison permanente ne declare
  `clip-path` sur une classe d'action dont le sujet du selecteur n'est pas `::before` ou `::after`.
  Seule la forme a deux deux-points est reconnue comme pseudo-element : une forme heritee a un seul
  deux-points est refusee plutot que lue charitablement, comme la forme litterale du repli
  (`ADR-0026` clause 2) et pour la meme raison.

**Deux corrections apportees a cette regle le 2026-08-10, a la porte technique de s07, sur mon propre
garde-fou.** Aucune n'a ete trouvee par relecture de mon texte : les deux l'ont ete en mutant la
production reelle que `lea-dev` a livree, ce que ma propre lecon de s05 prescrit et que mes jeux
fautifs ne faisaient pas.

| Ce qui ne mordait pas | Pourquoi | Ce qui le ferme, et sa morsure mesuree |
|---|---|---|
| **La clause 6 ne voyait pas les retards de production.** Un decalage **par colonne** ne peut pas vivre dans un raccourci `animation` partage : il s'ecrit dans une regle par colonne, en `animation-delay`. Ces regles ne declarent pas `animation:`, donc n'entraient pas dans la population des animations | Mon jeu fautif `decalage-trop-grand` ecrit son retard **dans le raccourci** - la forme que j'imaginais, pas celle que le monde ecrit. Troisieme occurrence de la lecon de s05 sur les fixtures de la main de l'auteur | `verifierLesRetardsEcritsAPart` lit tout `animation-delay` d'une feuille maison hors `@keyframes` et hors repli, confronte chacun a 0,25 s, et confronte **duree la plus longue + retard le plus long** au plafond de 1,2 s - une borne superieure, le doute jouant dans le sens du controle. Morsure : `animation-delay: 3s` sur la quatrieme colonne de `recreo.css` passait **vert sur 149 cas** ; il rend maintenant **2 defauts** dans la meme assertion, le retard (`3.0s`) et la somme (`3.6s`) |
| **La clause "jamais un autre mot" ne tenait pas la decoupe geometrique.** `clip-path` sur `.proposition` au lieu de `.proposition::after` retranche une partie du mot du serveur | La clause 7 ne lit que `content`, et la clause 3 n'enumere que quatre proprietes d'inactionnabilite. Une decoupe ne cache pas le bouton et ne fabrique aucun texte : elle passait entre les deux | Clause 8. Morsure : `.proposition { clip-path: inset(0 45% 0 0) }` ajoute a `recreo.css` passait **vert sur 149 cas** ; il rougit maintenant en nommant la classe, le sujet du selecteur et la consequence sur `sont`/`son`. Le jeu fautif corrige `voile-rogne-sans-toucher-le-texte` prouve que la forme de S7-1 reste verte |

Trois jeux fautifs neufs sous
`adapters/adapter-web/src/test/resources/contreexemple/LAnimationRetardeMaisNeDetermineRienTest/` :
`retard-ecrit-a-part` (clause 6 rouge sur le decalage), `texte-de-l-action-rogne` (clause 8 rouge) et
`voile-rogne-sans-toucher-le-texte` (clause 8 verte sur la forme retenue).

Les clauses 5 et 6 partagent la population des clauses 1 et 2 d'`ADR-0026`, **vide en production
aujourd'hui**, et donc couvertes par l'epinglage `aucuneAnimationNEstEncoreDeclareeEnProduction` qui
existe deja et qui affirme que ce compte vaut exactement zero. **La clause 7, elle, a une population
non vide des aujourd'hui** : deux regles maison declarent `content`, les deux barres de la croix du
signal faux, et une assertion l'exige explicitement pour que la clause ne passe pas a vide.

**Ce paragraphe a cesse d'etre vrai, et je le date plutot que de le corriger en silence** (constat du
2026-08-13). La population des clauses 5 et 6 n'est plus vide : `recreo.css` porte **5 declarations de
mouvement** en production, mesurees en collectant toute regle maison qui declare `animation`,
`animation-name`, `animation-delay` ou `transition`, commentaires retires. **Les 5 sont scopees sous
`[data-arrivee=par-un-geste]`**, et l'epinglage cite ci-dessus a ete supprime par le lot qui les a
livrees (`confirmation-archunit.md` de s07, point verifie). Cette mesure est ce qui rend l'amendement
de la clause 2 non theorique : le rebalayage refuse par M10 est celui de ces declarations-la.

**Preuves de morsure, cinq jeux fautifs neufs plus une relecture adverse** :

| Jeu fautif | Attendu | Mesure |
|---|---|---|
| `animation-sans-marqueur-de-geste` : animation correcte par ailleurs, sans le marqueur | clause 5 rouge | rouge, "ne nomme pas `[data-arrivee=par-un-geste]`" |
| `animation-trop-longue` : 1,5 s, refusee nommement par `nadia-prof` | clause 6 rouge sur le plafond | rouge, "au-dela du plafond de 1.2s" |
| `animation-trop-courte` : 0,2 s | clause 6 rouge sur le plancher | rouge, "sous le plancher de 0.3s" |
| `decalage-trop-grand` : 0,6 s de duree, 0,7 s de retard | clause 6 rouge sur le decalage | rouge, "au-dela du decalage maximal de 0.25s" |
| `feuille-qui-fabrique-du-texte` : `content: "son"` sur une face | clause 7 rouge | rouge, "cette regle fabrique du texte" |
| **relecture adverse** : `animation-avec-repli`, qui declare 0,6 s, la valeur que `nadia-prof` conseille | **vert** | vert. Sans ce cas, une inegalite au mauvais sens rejetterait la seule valeur qu'elle recommande et aucun jeu fautif ne le dirait |

Le dernier cas est le pendant exact de ce qu'`ADR-0025` a appris a ce depot : une barre dont on ne
verifie que le mauvais cote est une interdiction deguisee.

### Ce qui n'est garde par aucun automate, avec son destinataire

- **Que l'ecran obtenu sous reduction de mouvement soit lisible et jouable.** La clause 2 d'`ADR-0026`
  garde la **forme** litterale du repli, pas son resultat. `nadia-prof` le dit elle-meme (son point 4
  de la liste de reverification) et je le confirme : c'est sa part.
- **Le rebond, la courbe, le stroboscope, et le lien percu entre le geste et le mouvement.**
  `nadia-prof`.
- **Un mot peint dans une image**, et la lisibilite d'un mot pendant qu'il glisse. `nadia-prof`.
- **Qu'aucun decor de machine n'atterrisse sur l'ecran de fin de partie** une fois que le marqueur y
  serait pose. `nadia-prof`, et la section 3 dit a quel instant precis cela redevient vrai.
- **Que la reprise silencieuse reste un comportement souhaitable.** Ce n'est pas une question
  technique : `camille-po`. Si elle decidait que la reprise ne rend plus l'ecran de jeu en clair, le
  defaut de cet ADR disparaitrait a la racine, et la clause 5 de la regle 1 le dirait.

### Pourquoi rien de tout cela n'est une `ArchRule`

ArchUnit lit du bytecode. Ni un attribut de gabarit rendu, ni une declaration CSS, ni une reponse HTTP
n'y sont visibles. **Le compte de regles ArchUnit du depot est inchange a 28**, methode dans la section
Verification.

## Consequences

- Le mouvement suit le geste, et cela se verifie sur la sortie reelle du serveur plutot que sur une
  intention ecrite dans un ADR.
- La duree du defilement quitte la colonne "non gardable" et entre dans la CI, avec les trois chiffres
  de `nadia-prof`. C'est un bon echange : elle garde le jugement, l'automate garde le seuil.
- **Le depot gagne un idiome reutilisable** : un ecran rendu declare non seulement ou l'enfant est
  (`ADR-0022`) mais comment il y est arrive. Toute propriete future qui depend du chemin d'arrivee -
  et il y en aura - a maintenant un endroit.
- **Mauvaise consequence assumee, la premiere** : deux marqueurs sur le meme element, dont les noms se
  ressemblent. Quelqu'un confondra `ecran-de-jeu` et `data-arrivee`. Le commentaire du gabarit dit la
  difference en deux phrases, et c'est tout ce que je peux faire.

Consequences ajoutees par l'amendement du 2026-08-13 :

- **La clause 2 ne peut plus etre lue sans lire la marque de M9.** Ce garde-fou et la mecanique de
  reessai sont desormais couples par un fait de rendu : si `nadia-prof` ou `camille-po` retirent la
  marque des propositions deja essayees, la lecture rougit et cet amendement doit etre repris. C'est un
  couplage reel, assume, et epingle par un jeu fautif plutot que laisse a la memoire de quelqu'un.
- **Mauvaise consequence assumee** : les valeurs du marqueur, `par-un-geste` et `sans-geste`, nomment
  desormais moins que ce qu'elles decident. Un ecran d'echec **est** arrive par un geste et declare
  `sans-geste`. Le cout du renommage est ecrit en section 2 ; je prefere un nom etroit a un chantier
  transverse, et je le note plutot que de le laisser decouvrir.
- **Un bon effet non cherche** : le message d'echec, lui, apparait toujours. La clause ne rend pas
  l'ecran de reessai immobile en tout, ce qui est exactement la frontiere que `nadia-prof` trace dans
  son "ce que je n'exige pas". Il n'y a donc rien a arbitrer de ce cote.
- **Mauvaise consequence assumee, la deuxieme** : la clause 5 interdit aujourd'hui toute animation sur
  l'ecran de retour, y compris une apparition de la coche que `nadia-prof` n'a jamais refusee. C'est
  plus strict que ce qu'elle demande. Le prix est faible et la sortie est ecrite : poser le marqueur
  sur cet ecran, en sachant que l'acquis 6 lui revient alors entierement.
- **Mauvaise consequence assumee, la troisieme** : `mancheModele` prend un cinquieme parametre, et une
  signature a cinq parametres est le debut d'un mauvais chemin. Je le prefere a un attribut de modele
  pose a cote, parce que le compilateur est le seul mecanisme qui rende cette derivation totale sans
  test. Ce qui me ferait changer d'avis : un sixieme parametre du meme genre, qui serait le signe qu'un
  objet "contexte de rendu" est du.
- La duree lisible est celle du raccourci `animation` ou des proprietes explicites. Une duree calculee
  par une variable CSS ne se lit pas, et la clause 6 la refuse plutot que de l'ignorer. C'est brutal
  et voulu, comme la liste de mots d'`ADR-0008`.

## Alternatives considerees

- **Lire l'en-tete `HX-Request` que htmx envoie sur chaque requete.** Techniquement juste et sans
  changement de gabarit. Ecartee pour deux raisons : elle fait dependre une garantie pedagogique d'un
  detail de comportement d'une bibliotheque tierce, ce qu'`ADR-0014` point 6 refuse nommement ; et
  elle est moins totale que le parametre, parce que rien n'oblige une route neuve a la consulter. Le
  parametre a un avantage qu'aucun en-tete n'a : **le compilateur refuse l'oubli.**
- **Faire poser la classe par `recreo.js` sur `htmx:afterSwap`.** Le fichier ecoute deja cet
  evenement, et `ADR-0008` autorise nommement le basculement d'une classe. Ecartee : la propriete "le
  mouvement suit le geste" serait alors detenue par un fichier que la CI ne juge que par son poids et
  ses mots interdits, alors qu'elle est parfaitement calculable sur le serveur. C'est l'inverse de la
  doctrine d'`ADR-0008`, dans l'ADR qui la prolonge.
- **Utiliser les classes que htmx pose pendant un echange** (`htmx-added`, `htmx-settling`). Meme
  objection tierce que l'en-tete, en pire : ce sont des details d'implementation d'une version
  vendorisee, et le mode de defaillance d'un changement serait un mouvement non demande plutot qu'une
  immobilite.
- **Supprimer le rendu en clair de la manche sur la branche de reprise.** C'est la solution qui
  supprime le defaut a la racine, et elle ne m'appartient pas : la reprise silencieuse est un
  comportement produit de S1-4. Routee a `camille-po`, avec la clause 5 de la regle 1 comme detecteur
  du jour ou elle disparaitrait.
- **Un marqueur negatif** (`arrivee-sans-geste` seul, l'animation etant l'etat par defaut). Ecartee
  sur le mode de defaillance : oublier un marqueur negatif produit un mouvement que l'enfant n'a pas
  demande, c'est-a-dire le defaut. Oublier le marqueur positif produit l'immobilite. Le doute doit
  toujours jouer du cote de l'immobilite.
- **Ecrire la duree comme une clause de revue plutot qu'un test.** Ecartee : `nadia-prof` a donne trois
  chiffres, et laisser un chiffre a la revue quand un automate peut le lire est exactement ce que ce
  role ne fait pas.

## Ce qui me ferait changer d'avis

- **`camille-po` retire le rendu en clair de la manche sur la reprise.** Le defaut disparait, la
  clause 1 perd sa population, et cet ADR doit etre repris - probablement pour garder le marqueur, qui
  reste utile, mais sans pretendre garder un defaut qui n'existe plus.
- **Un troisieme chemin d'arrivee** qui ne soit ni un echange htmx ni un chargement de page (un
  `hx-trigger="load"`, une redirection). Le booleen deviendrait insuffisant et il faudrait nommer les
  chemins, pas seulement les compter.
- **`nadia-prof` constate en jouant que sa barre de duree est mal placee.** Les trois constantes
  bougent avec son arbitrage, jamais sans lui, et elles sont a un seul endroit du code pour ca.
- **Un besoin d'animation sur l'ecran de retour** valide par elle : le marqueur s'y pose, et l'acquis
  6 lui revient entierement. Ecrit d'avance pour que ce ne soit pas une surprise.

## Verification

`mvn -B clean verify` sur un JDK 21.0.10 designe par `JAVA_HOME` le temps de la commande, dans le
worktree dedie `theo-s07-machine-a-sous` descendant de `round-two` (`outils/verifier-lignee.sh` vert).
Constat rendu par `outils/verifier-jdk.sh` : `java` du `PATH` 1.8.0_202, JDK de compilation 21.0.10,
`JAVA_HOME` vide hors de la commande, toolchains non lu par Maven.

**Resultat : BUILD SUCCESS**, les six modules du pom agregateur en SUCCESS.

**Compte de cas de test et sa methode.** Perimetre : les rapports XML de `*/target/surefire-reports/`
et `*/target/failsafe-reports/`, **apres un `clean`**, somme de l'attribut `tests` de chaque
`<testsuite>`. XML et non texte, parce que deux moteurs JUnit tournent dans ce depot et que le rapport
texte n'en garde qu'un.

**Compte de regles ArchUnit et sa methode.** `git ls-files '*.java'`, critere `static final ArchRule`.
Comptage brut 30, moins les 2 declarations du faux module de test-ressources de
`LesGardeFousSontMisALEpreuveTest`, qui n'est jamais compile comme regle du depot. Reste **28**,
inchange : cet ADR n'ajoute ni ne retire aucune `ArchRule`. Un balayage du disque apres `verify` en
rendrait 32, les copies de `target/` : c'est ce perimetre-la qu'il faut, et pas un autre.

**Amendement du 2026-08-10.** Les deux corrections de la regle 2 sont verifiees sur l'arbre fusionne
du sprint, `round-two` a `d53181b`, plus le commit qui les porte. Chiffres et methode dans
`squad/sprints/s07-machine-a-sous/confirmation-archunit.md` : `adapter-web` passe de **149 a 153** cas
(un cas de production pour la clause 8, trois cas de morsure), le total du depot de **310 a 314**, et le
compte de regles ArchUnit reste **28** - cet amendement n'en ajoute aucune, pour la raison ecrite
ci-dessus.

**Mesure de rendu.** Les deux chiffres corriges d'`ADR-0026` (101 px de colonne, 16 px d'ecart) sont
remesures a mi-hauteur de la boite sur la capture produite par `outils/mesurer-rendu.sh 360 <url>
<png>`, par un critere different de celui de `nadia-prof` - suites de pixels non-fond plutot qu'aplats
de couleur d'accent - et les deux methodes concordent. La geometrie declaree les confirme a la
decimale : 336 px moins 2 x 16 px font 304 px, soit 101,33 px par colonne.

## Verification de l'amendement du 2026-08-13

`mvn -B clean verify` sur un JDK 21.0.10 designe par `JAVA_HOME` le temps de la commande, dans le
worktree dedie `s10-theo-mouvement` descendant de `round-two` a `7032624`. Constat rendu par
`outils/verifier-jdk.sh` : `java` du `PATH` 1.8.0_202, JDK de compilation 21.0.10, `JAVA_HOME` vide
hors de la commande, toolchains present sous un nom non lu par Maven.

**Resultat : BUILD SUCCESS**, les six modules du pom agregateur en SUCCESS. **Avec une exclusion, et
elle est nommee** : `-Dsurefire.excludesFile` sur `**/PaquetsFrancaisS04Test.java`. Ce test
d'`adapter-content` ne termine pas sur `round-two` - il boucle contre une manche revelee et epuise la
memoire - et sa correction vit sur une branche non fusionnee. **Sur `round-two` tel quel, la suite
complete est donc rouge, et je ne conclus pas l'inverse.** L'exclusion est un fichier de perimetre et
non `-Dtest` : mesure a l'appui, `-Dtest=!PaquetsFrancaisS04Test` remplace les inclusions par defaut de
surefire, tire les classes `*IT` dans la phase `test` - donc avant `package` - et fait echouer
`JarRecreoIT` sur un jar absent. C'est un artefact de l'instrument, pas un defaut du depot.

| Mesure | Valeur | Perimetre |
|---|---|---|
| `round-two` a `7032624`, meme exclusion | **373** cas, BUILD SUCCESS | reference |
| cette branche, meme exclusion | **376** cas, BUILD SUCCESS | +3, tous dans `adapter-web` (192 a 195) |
| `adapter-content` avec la correction de `marc-dev` (`2b910e9`), sans exclusion | 49 a **63** cas | le test exclu en vaut **14** |
| suite complete reconstituee | **387** sur `round-two` + `2b910e9`, **390** avec ce lot | somme des deux mesures |

Le 387 reconstitue est exactement le chiffre que `marc-dev` annonce sur sa branche : deux methodes
independantes concordent, ce qui est la seule raison de citer une somme plutot qu'une seule mesure.

**Methode du compte de cas** : rapports XML de `*/target/surefire-reports/` et
`*/target/failsafe-reports/`, apres un `clean`, somme de l'attribut `tests` de chaque `<testsuite>`.
XML et non texte, pour la raison ecrite plus haut. Les trois cas de `failsafe` de `bootstrap` sont
compris dans les totaux.

**Compte de regles ArchUnit** : **28**, inchange. Meme methode que ci-dessus - `git ls-files '*.java'`,
critere `static final ArchRule`, comptage brut 30 moins les 2 declarations du faux module de
test-ressources. Cet amendement n'ajoute aucune `ArchRule` : rien de ce qu'il garde n'est visible dans
du bytecode.

**Mesure de rendu, sur le jar empaquete** (`ADR-0025` : une mesure de rendu porte sa commande). La
propriete a etablir est que les colonnes ne rebalaient pas sur un echec avec des essais restants. Elle
est mesuree en deux faits, chacun par sa commande, et la conclusion est leur produit.

1. **Ce que le serveur envoie reellement.** `mvn -B clean verify` produit `bootstrap/target/recreo.jar`
   (20,6 Mo) ; l'application est lancee par `java -jar bootstrap/target/recreo.jar --server.port=<port>`
   et une vraie session HTTP est jouee sur `bandit-manchot-additions-cp` - `GET /`, puis
   `POST /parties/{jeuId}`, puis `POST /parties/{jeuId}/coups` avec une proposition fausse calculee a
   partir de l'enonce rendu. Releve :

   | Ecran | `data-arrivee` |
   |---|---|
   | manche neuve, demandee par un appui (enonce "7 + 3") | `par-un-geste` |
   | apres un echec avec des essais restants, message "Ce n'est pas ca. Essaie encore !" | **`sans-geste`** |
   | reprise silencieuse, `GET /` sur la meme session | `sans-geste` |

   Le bloc `.propositions` passe de 1202 a 1214 octets, et le diff porte sur **la seule proposition
   pressee** : `deja-essayee` ajoutee (M9) et `data-focus-cible` passe a `true` (M13). Meme nombre de
   propositions, meme ordre, memes valeurs - M8 tenue sur la sortie du serveur.

2. **Ce que la feuille peut faire de ce marqueur.** Les declarations de mouvement de `recreo.css` sont
   collectees en retirant les commentaires puis en gardant toute regle maison qui declare `animation`,
   `animation-name`, `animation-delay` ou `transition`. Il y en a **5**, et **les 5 sont scopees sous
   `[data-arrivee=par-un-geste]`** (liste dans la section de la regle 2 ci-dessus).

Un ecran qui declare `sans-geste` ne satisfait aucune des 5 : **aucune colonne ne peut bouger sur un
reessai**, et le critere de `nadia-prof` - "les pixels des colonnes sont identiques entre l'ecran
d'avant l'appui et l'ecran de reessai" - est tenu par construction plutot que par reglage.

**Ce que cette mesure n'etablit pas, et il faut le dire ici.** Ce n'est pas une capture. Une capture
fixe ne peut pas trancher cette propriete : `outils/mesurer-rendu.sh` prend une URL et fixe son budget
de temps virtuel a 4000 ms, donc il ne sait ni enchainer les deux `POST` d'un reessai, ni echantillonner
une image pendant que l'animation court - et apres 0,6 s l'ecran est au repos **que l'animation ait
tourne ou non**. Une capture aurait donc ete verte dans les deux cas, avant comme apres cet amendement,
et l'aurait ete a tort. Le releve image par image reste a `nadia-prof` (`ADR-0025`).
