# ADR-0031 - Un ecran ne gouverne jamais l'apparence d'un autre

- **Statut** : Accepte
- **Date** : 2026-08-11
- **Auteur** : theo-archi
- **Sprint** : s08 (apres la fusion des lots)

## Contexte

`ADR-0030` a laisse la condition **C4** de `nadia-prof` - *"la machine ne dit jamais le resultat : le
cabinet est rigoureusement identique apres une bonne et apres une mauvaise reponse"* - comme une dette
de test, avec un motif de refus explicite : la seule forme alors disponible aurait enumere
`.cabinet`, `.signal.est-juste` et `.signal.est-faux`, c'est-a-dire un perimetre enumere (`ADR-0013`)
et, pire, des noms cites dans un test alors qu'aucun fichier du depot ne les portait - la dette n°1
de `CLAUDE.md`. Le planning de s08 a inscrit cette dette comme **non fermee**, a rouvrir des que S8-1
serait livre.

S8-1 est livre et fusionne (`12555fd`). `.cabinet`, `.cabinet-bandeau` et `.cabinet-socle` existent
dans `recreo.css` et dans `fragments/manche.html`. Le motif de refus est leve : la moitie du refus qui
tenait a l'absence du code n'existe plus, mais **l'autre moitie tient toujours** - une clause qui
enumere trois noms reste une clause qui enumere trois noms. Cet `ADR` ecrit donc C4 sous une forme qui
n'en cite aucun.

J'ai lu ce que `lea-dev` a reellement livre avant d'ecrire, et deux constats en decoulent :

1. `CabinetDeLaMachineTest` garde deja, cote structure, ce qu'`ADR-0030` avait promis par la contrainte
   1.d : `leCabinetNAtteintNiLEcranDeRetourNiLAccueil` verifie que le mot `cabinet` n'apparait ni dans
   `fragments/retour.html` ni dans `accueil.html`. Cette partie de la dette est donc deja fermee, par
   un autre que moi, et je ne la reecris pas.
2. Ce qui reste ouvert est le canal que le planning nommait : **une regle de la feuille qui
   conditionne l'habillage au verdict**. `lea-dev` l'a garde pour `.explication` (S8-3) par deux
   sous-chaines - `.signal.est-juste .explication` et `.est-juste .explication`. C'est la bonne forme
   **la** (voir la decision, point 3), et ce n'est pas une forme transposable au cabinet : elle echoue
   par omission des que le selecteur s'ecrit autrement, et ce depot ecrit deja `body:has(...)`.

## Decision

### 1. La propriete : aucun selecteur maison ne reunit des jetons que le serveur ne peut jamais rendre ensemble

**Un jeton** est ce qu'un selecteur nomme et ce qu'un gabarit pose : une classe, ou la valeur d'un
attribut. La zone `hx-target` recoit **un ecran a la fois** ; les jetons de deux ecrans inseres ne
coexistent donc jamais dans un document, et ceux de la coquille permanente coexistent avec ceux de
chaque ecran. Un selecteur qui melange les jetons de deux ecrans exclusifs ne peut ni s'appliquer ni
etre innocent : il est soit du code mort, soit la trace d'une intention que la structure interdit.
**Les deux valent un rouge.**

C'est C4 sans nommer C4 : `est-juste` est un jeton de `fragments/retour.html`, `cabinet-bandeau` un
jeton de `fragments/manche.html`, et
`body:has(.est-juste) .cabinet-bandeau { background-color: gold }` - la tentation que `nadia-prof`
nomme, *"dans une machine a sous, l'etat de la machine est le resultat"* - devient rouge sans qu'aucun
de ces deux noms soit ecrit dans un test.

### 2. La propriete est la co-occurrence, pas la conditionnalite

J'ai d'abord concu la clause autour du **marqueur conditionnel** : relever les classes qu'un
`th:class` ou un `th:classappend` pose dans une seule branche de son ternaire, puis interdire qu'elles
gouvernent une classe d'un autre gabarit. Je l'ecarte au profit de la co-occurrence, pour trois
raisons mesurables :

- elle exigeait de decouper les branches d'une expression Thymeleaf, avec un piege deja vu : dans
  `${enSuite} ? 'btn retour-accueil' : 'btn sortie-pleine'`, un decoupage naif fait de `btn` une
  classe conditionnelle, donc rougit des regles legitimes. Trois pieces mobiles pour le meme resultat ;
- la co-occurrence **attrape strictement plus** : elle refuse aussi un selecteur qui melange deux
  ecrans sans qu'aucun marqueur conditionnel n'y soit ;
- elle n'a besoin d'aucune notion de verdict. Le verdict de ce depot est un marqueur conditionnel
  aujourd'hui ; il pourrait etre demain un attribut, comme `data-arrivee` l'est depuis `ADR-0027`. Une
  clause keyee sur le mecanisme des classes aurait manque celle-la - *un perimetre enumere par types
  echoue par inclusion autant que par omission*.

### 3. Ce que je ne prends pas, et pourquoi la clause enumeree de S8-3 a raison de rester

**Le couplage du verdict a un element du meme ecran n'est pas jugeable par une structure, et ne doit
pas l'etre.** `.signal.est-juste .icone-signal` est la coche que l'enfant attend ; rien ne la
distingue structurellement d'une regle qui colorerait l'explication du meme ecran. Ce qui separe les
deux est pedagogique, pas structurel. La neutralite de `.explication` reste donc gardee **nommement**
par `CabinetDeLaMachineTest.lExplicationResteNeutreQuelQueSoitLeVerdict`, et cette enumeration-la est
correcte : elle enumere la ou aucune structure ne tranche. Je ne la remplace pas et je ne la duplique
pas.

**L'identite des pixels reste a `nadia-prof`** (`ADR-0025`). Aucun automate de ce depot ne rend une
page ; C4 telle qu'elle est verifiee - deux captures du meme item - n'est pas dans mon perimetre et ne
le devient pas.

### 4. Deux limites mesurees, avec leur declencheur

Aucune des deux n'est une hypothese : chacune a ete mise a l'epreuve par une mutation reelle de la
feuille livree, puis annulee.

**a. Le verdict peut encore colorer la coquille permanente.**
`body:has(.est-juste) .entete { background-color: #2e7d32 }` **reste vert** (mesure : mutation
appliquee sur `recreo.css`, les trois cas de la clause passent). C'est coherent avec la propriete -
l'en-tete permanent et l'ecran de retour coexistent reellement dans le document, donc ce selecteur
peut s'appliquer - et c'est neanmoins un canal par lequel une partie de l'interface dirait le
resultat. Je ne l'interdis pas ici parce que ce serait une autre propriete que la co-occurrence, et
qu'aucune population ne l'objective aujourd'hui : **zero regle maison ne nomme a la fois un marqueur
d'ecran conditionnel et un jeton permanent**. Declencheur ecrit : le jour ou une regle maison
conditionne un element permanent a un marqueur d'ecran, cette question se rouvre avec un objet reel a
controler.

**b. Le verdict peut encore passer par le gabarit plutot que par la feuille.**
Un `th:classappend` pose sur le cabinet a partir d'une variable de modele
(`${dernierCoupJuste} ? 'cabinet-gagnant' : ''`) produirait une classe de `manche.html` stylee par une
regle de `manche.html` : la co-occurrence est satisfaite, la clause est verte. Fermer ce canal
demanderait de savoir **quelle variable de modele porte un verdict**, ce qu'aucune structure ne dit -
une variable neuve du controleur ne se declare nulle part comme "verdict". La dette C4 est donc
**reduite, pas fermee**, et c'est ecrit tel quel dans le planning et dans le rapport de sprint plutot
que presente comme une fermeture.

## Regle executable

`adapters/adapter-web/src/test/java/com/recreo/adapter/web/UnEcranNeGouvernePasLApparenceDUnAutreTest.java`

Trois cas : la production, un depot fictif fautif, son jumeau corrige.

**Perimetre derive.** Les ecrans viennent du repertoire de gabarits ; la coquille est le gabarit qui
contient la zone `hx-target`, tout autre gabarit est un ecran insere - **la derivation d'`ADR-0022`,
partagee plutot que recopiee** : `documents` et `zoneEchangee` deviennent visibles dans le paquet, et
`classesDuSelecteur` de `LAnimationRetardeMaisNeDetermineRienTest` de meme, comme `Regle` et
`reglesMaison` l'etaient devenues pour `ADR-0030`. Un raffinement de plus resserre la clause : **le
contenu ecrit dans la zone de la coquille n'est pas permanent** - un echange htmx l'efface - c'est
donc un ecran de plus et non un jeton que tous les documents porteraient. Aucun nom de classe, de
fragment ni de marqueur n'est ecrit dans le fichier.

**Derivation totale.** Aucun gabarit, aucune zone, plusieurs zones, aucune coquille, plusieurs
coquilles, aucune regle maison, ou aucune regle nommant deux jetons sont des **echecs**, jamais des
cas ignores. Un jeton qu'aucun ecran ne peut poser est un echec lui aussi. Seuls les **litteraux**
d'une expression Thymeleaf sont lus, jamais un mot d'expression calculee : lire au mot pres ferait
passer un nom de variable de modele pour un jeton posable, ce qui affaiblirait la clause au lieu de la
renforcer.

**Non-vacuite, avec sa methode (`ADR-0003`).** La clause ne juge que les selecteurs nommant **au
moins deux** jetons, et refuse de passer si cette population est vide. Mesure sur la feuille livree :
**17 regles maison** nomment au moins deux jetons et **4 documents** sont derives - le contenu de
`#jeu` dans `accueil.html`, puis `fragments/erreur.html`, `fragments/manche.html`,
`fragments/retour.html` (methode : sonde jetable non commitee sur la taille des deux populations, dans
ce worktree, apres `clean`). Les 17 passent pour une raison reelle : chacune reste dans un seul ecran,
ou croise un jeton permanent. Ce point n'est pas rhetorique - j'ai constate ce sprint sur `ADR-0029`
qu'un garde-fou lisant le classpath peut etre vert du premier coup sur une **population vide**.

### Preuve de morsure - trois mutations reelles, chacune annulee

`git status` confirme qu'aucune ne subsiste.

| Mutation de `recreo.css` | Attendu | Mesure |
|---|---|---|
| `body:has(.est-juste) .cabinet-bandeau { background-color: gold }` - la tentation de la machine a sous, ecrite avec la technique que le depot emploie deja pour le masquage d'`ADR-0022` | rouge | **rouge** : "ce selecteur nomme la classe "est-juste" que seul(s) [templates/fragments/retour.html] peut poser, puis la classe "cabinet-bandeau" que seul(s) [templates/fragments/manche.html] peut poser". Les deux fragments sont **derives**, pas ecrits dans le test |
| `.signal.est-faux ~ .cabinet { border-color: #c62828 }` - la meme faute avec un autre combinateur | rouge | **rouge**, message nommant les trois jetons et leurs ecrans. Ce qui est juge est l'ensemble des jetons, jamais la forme du selecteur : la famille entiere est prise, pas le cas |
| `body:has(.est-juste) .entete { background-color: #2e7d32 }` - le verdict colore la coquille permanente | vert, et c'est une limite | **vert** (3 cas passants). Limite 4.a ci-dessus, mesuree plutot que supposee |

**Mise a l'epreuve sur objets neufs (`ADR-0013` obligation 3)**, deux jeux d'artefacts, un depot
fictif qui ne porte aucun nom du depot reel - c'est ce qui prouve que le perimetre est derive :

- `verdict-qui-colore-la-machine` : une coquille, un ecran de tirage dont l'habillage entoure les
  colonnes, un ecran de bilan dont le marqueur conditionnel dit la partie gagnee, et **une** regle qui
  colore le bandeau de l'habillage du tirage depuis le marqueur du bilan ;
- `verdict-sur-son-ecran` : le meme, a cette seule regle pres. L'habillage, le marqueur conditionnel
  et les deux regles qui colorent le pictogramme du bilan **restent tous en place**. Sans ce cas, la
  clause pourrait etre une interdiction du decor ou du signal juste/faux deguisee en clause de C4, et
  aucun jeu fautif ne le dirait.

### Ce n'est pas une regle ArchUnit, et le compte ne bouge pas

**28 regles ArchUnit, inchange.** Cette clause n'est pas une `ArchRule` : ArchUnit lit du bytecode, et
ni une feuille de style ni un gabarit n'y sont visibles. Methode identique a celle d'`ADR-0030` :
`grep` de la forme `ArchRule <nom> =` dans les `src/test/java` des cinq modules de code declares au
pom agregateur, execute dans ce worktree seul - **28**, dont **27** portees par une annotation
`@ArchTest`, la 28e executee par un `.check()` explicite. Le contre-exemple d'ArchUnit qui vit sous
`bootstrap/src/test/resources/` n'est pas compte : il n'est pas sous `src/test/java`.

## Consequences

- La dette C4 nommee par `ADR-0030` et inscrite au planning de s08 est **reduite et non fermee** : le
  canal de la feuille est garde, le canal du gabarit et celui de la coquille permanente sont nommes
  avec leur declencheur. La verification par captures reste a `nadia-prof`, comme avant.
- `mvn verify` refuse desormais un selecteur qui melange deux ecrans exclusifs, y compris quand
  l'auteur le sait mort. **Mauvaise consequence assumee** : un jour, quelqu'un voudra ecrire une regle
  provisoirement inapplicable - pour un ecran qui n'existe pas encore - et devra soit livrer l'ecran
  dans le meme commit, soit ne pas ecrire la regle. Je considere que c'est le bon ordre, mais c'est une
  contrainte qui n'etait pas demandee.
- Trois derivations deviennent visibles dans le paquet plutot que recopiees une troisieme fois. Le
  couplage entre garde-fous augmente d'autant : un changement de la derivation de la zone echangee
  touche desormais deux clauses au lieu d'une. C'est le prix, et il est plus faible que celui d'un
  quatrieme decoupage de selecteurs CSS dans le meme module.
- Un habillage neuf, un ecran neuf ou un marqueur neuf entrent dans le controle le jour ou le gabarit
  les pose, sans que ce fichier change - comme le cabinet est entre tout seul dans la population de
  rognage d'`ADR-0030`.
- Rien de ce qui est servi a un enfant ne change : cet `ADR` n'ajoute pas un octet a `recreo.css` ni
  aux gabarits.

## Alternatives considerees

**Enumerer `.cabinet` et les deux classes de verdict, comme la clause de S8-3 le fait pour
`.explication`.** Ecartee pour le cabinet, et la raison est mesurable plutot que doctrinale : cette
forme n'attrape que les sous-chaines qu'elle nomme, or `body:has(.est-juste) .cabinet-bandeau` n'en
contient aucune. Elle serait verte sur la faute exacte que C4 decrit. Elle reste juste **la ou elle
est**, sur un couplage intra-ecran qu'aucune structure ne peut trancher (decision, point 3).

**Deriver les marqueurs conditionnels et interdire qu'ils gouvernent un autre gabarit.** Ecartee :
plus de pieces mobiles, un piege de decoupage de branches deja identifie (`btn` present dans les deux
branches d'un ternaire), et une portee **plus faible** que la co-occurrence sur tout ce qui n'est pas
une classe conditionnelle. Elle aurait en revanche attrape la limite 4.a ; c'est ce que son
declencheur rouvrira si une population apparait.

**Attendre le rapport de `yanis-qa` ou le releve de `nadia-prof`.** Ecartee : C4 est une propriete de
structure autant qu'une propriete de pixels, et la moitie de structure etait sans garde depuis deux
sprints. Une clause qui arrive apres la revue arrive apres ce qu'elle aurait attrape.

**Ecrire la clause comme une regle ArchUnit pour "faire une 29e regle".** Ecartee, et je le note parce
que la question m'a ete posee : une feuille de style n'est pas du bytecode. Compter une clause CSS
parmi les regles ArchUnit rendrait le chiffre faux, et *tout chiffre porte sa methode dans le meme
paragraphe*.

## Verification

`mvn -B clean verify`, sur un JDK **21.0.10** designe par `JAVA_HOME` le temps de la commande (le
`java` du `PATH` de ce poste est un JRE 8 ; constat rendu par `bash outils/verifier-jdk.sh`) :
**BUILD SUCCESS**, les six modules du pom agregateur en SUCCESS.

**Chiffres et methode dans le meme paragraphe.** Comptage valide **apres un `clean`**, lu sur les
lignes `Results:` du journal, module par module : `domain` 43, `application` 7, `adapter-web` 173,
`adapter-content` 39, `bootstrap` 77 en surefire et 3 en failsafe. Total **342 tests**, contre **339**
sur `round-two` a `533967d` : les 3 de plus sont les trois cas de la clause de cet `ADR`. Regles
ArchUnit : **28, inchange** (methode ci-dessus).

## Amendement du 2026-10-16 (s57, `theo-archi`) - le sujet hors du message, garde de `C1'` point 1

**Contexte.** `C1'` (`avis-theo-poste-8-art-cp-10.md` de s57, 2.2) remplace `C1` pour l'ecran de manche : *"Une regle dont le sujet est
**hors** de `.message-jeu` ne nomme ni `.message-jeu`, ni `.signal`, ni `.est-juste`, ni `.est-faux`, ni `data-oeuvre-revelee`, y compris
dans un `:has(...)`."* Elle n'avait que ma lecture du diff pour garde, et cette lecture a manque une fois (`E3`). `TP4`
(`avis-theo-palier-2.md` 2.4) l'a mesure : `.ecran-de-jeu:has(.est-faux):not(.ecran-deux-oeuvres) .propositions { row-gap: 0; }` - le
meuble se compacte apres une erreur - passait tout `adapter-web`. Story : `S57-GARDE-C1-SUJET-HORS-MESSAGE`.

**Ce que cet amendement change dans la decision 3.** *"Le couplage du verdict a un element du meme ecran n'est pas jugeable par une
structure"* reste vrai de la coche et de l'explication, qui vivent toutes deux dans le message. Il ne l'est pas d'une regle dont le
**sujet** est hors du message : la structure distingue alors le sujet du message, et c'est ce que `C1'` point 1 juge.

**Decision.** Refuser toute regle maison dont le sujet est hors du message d'un ecran et dont le selecteur nomme, `:has(...)` compris, un
jeton que ce document ne pose que dans le message.

1. **Le message d'un ecran est sa region d'annonce** (`role="status"`), derivee de chaque gabarit. C'est le seul nom que la clause ecrit,
   et c'est un role ARIA, pas un nom du depot. Ses jetons sont ceux de son sommet et de ses descendants ; ceux qu'il est seul a poser
   sont ses jetons moins ceux du reste du document, coquille permanente comprise (la composition de la decision 1, partagee).
2. **Le sujet est lu compose par compose sur la chaine principale**, hors parentheses et hors crochets. Un compose qui nomme un jeton que
   le document ne pose que hors du message est dehors ; un jeton que seuls les descendants du message posent le place dedans ; un jeton
   propre au sommet, au sommet. Sans jeton decisif, un compose herite : descendant ou enfant de ce qui est dans le message ou a son
   sommet, il est dedans ; frere de ce qui est dedans, dedans ; frere du sommet, dehors. Le premier compose sans jeton decisif est tenu
   pour dehors.
3. **Une regle n'est jugee que contre les documents ou elle peut s'appliquer** : ceux qui posent tous les jetons qu'elle nomme.
4. **Derivation totale** : aucune region d'annonce dans aucun gabarit, ou aucune regle qui nomme un jeton propre a un message, sont des
   echecs.

**Regle executable.** Pas une `ArchRule` : l'objet est une feuille de style et des gabarits. Elle vit dans le fichier de la regle de cet
`ADR`, `adapters/adapter-web/src/test/java/com/recreo/adapter/web/UnEcranNeGouvernePasLApparenceDUnAutreTest.java`, ecrite dans le
meme commit que cet amendement, quatre tests de plus :

- `aucuneRegleDontLeSujetEstHorsDuMessageNeNommeCeQueSeulLeMessagePose` : la production, verte ;
- `chaqueClasseDuMessageNommeeDepuisUnSujetHorsDeLuiFaitEchouerLeGardeFou` : sur les gabarits et la feuille reels, pour chaque message
  et chaque classe qu'il est seul a poser, la regle `.<premiere classe propre au dehors>:has(.<classe>)` ajoutee aux regles de
  production rougit en citant son selecteur ;
- `uneClasseNeuveDuMessageEntreDansLeControleSansQueCeFichierChange` : une classe qu'aucun gabarit ne pose, ajoutee en memoire a chaque
  region d'annonce, entre dans le controle (`ADR-0013`, obligations 1 et 3 : l'objet neuf est cette classe, absente de la production) ;
- `sansAucuneRegionDeMessageLaClauseEchoueAuLieuDePasserAVide`.

**Populations, avec leur methode.** Sonde jetable non commitee, dans une copie de l'arbre de ce commit, une seule classe lancee : 252
regles maison (une par selecteur d'une liste a virgules), **3 messages** derives (`fragments/designation.html`, `fragments/manche.html`,
`fragments/saisie.html`), **54 paires** regle-document jugees (la regle s'applique au document et nomme un jeton propre a son message),
toutes a sujet dans le message. **29 regles fautives** derivees par le deuxieme test (8, 10 et 11 classes propres aux trois messages).

**Preuve de morsure, sur la feuille reelle.** Copie de l'arbre de ce commit sous mon repertoire de travail, `recreo.css` (et
`manche.html` pour `G5`) mute puis restaure par copie de reference, verifie identique. Commande : `bash outils/mvn-jdk21.sh -B -pl
adapters/adapter-web -am test -Dtest=UnEcranNeGouvernePasLApparenceDUnAutreTest -Dsurefire.failIfNoSpecifiedTests=false`, perimetre
`adapter-web` et ses amonts, une classe, 7 tests, JDK 21.0.10 (`java.version` du rapport Surefire). Ajouts en fin de feuille :

| Code | Mutation | Mesure |
|---|---|---|
| `M0` | aucune | 0 echec |
| `G1` | la regle de `TP4`, sous `@media (min-width: 720px)` | **1 echec**, le message cite `.ecran-de-jeu:has(.est-faux):not(.ecran-deux-oeuvres) .propositions` |
| `G2a` a `G2c` | la meme, avec `.message-jeu`, `.signal`, `.est-juste` dans le `:has(...)` | **1 echec** chacune, le message cite le selecteur |
| `G3` | la meme, avec `[data-oeuvre-revelee=true]` | 0 echec : **non garde**, ci-dessous |
| `G4` | le texte de `candidat-r3.css` ajoute a la feuille | 0 echec |
| `G5` | une classe `jeton-neuf` sur la region d'annonce de `manche.html`, et la regle de `G1` avec ce jeton | **1 echec**, aucun fichier de test edite |
| `G6` | la regle de `TP4` sans `@media` ni exclusion de l'ecran a deux oeuvres | **1 echec** (elle passe le test d'epinglage de la forme : `M4` de ma lecture de p2-7) |
| `G7` | `body:has(.est-juste) .entete { background-color: #2e7d32 }` (limite 4.a) | **1 echec**, le message nomme les trois ecrans a message |
| `G8` | `.signal.est-faux ~ .cabinet { border-color: #c62828 }` | **1 echec** |
| `G9` | `.message-jeu:has(.est-faux) .indice { margin-top: 0; }` (`C1'` point 2) | 0 echec : **non garde**, ci-dessous |
| `G10` | `.zone-jeu:has(> .ecran-de-jeu .est-faux) { padding-top: 0; }` | **1 echec** : le sujet est lu hors du `:has(> ...)`, la ou `LAnimationRetardeMaisNeDetermineRienTest.sujet` couperait dedans |

La feuille telle qu'elle est servie reste verte, avec `.signal.est-juste .icone-signal` (ligne 1569, sujet dans le message) et
`.propositions-oeuvres:has(.proposition.deja-essayee)` (ligne 1817, `O1`, aucun jeton du message).

**Ce que cette regle ne garde pas, nomme.**

- **`data-oeuvre-revelee`**, que `C1'` point 1 nomme, n'est **pas garde** par cette regle. Il est pose dans le meuble, sur la case, et
  non dans le message ; et la feuille le lit legitimement depuis un sujet hors du message,
  `.propositions-oeuvres .proposition[data-oeuvre-revelee=true] img` (ligne 1843, la marque de la bonne oeuvre, `ADR-0104`). Une regle
  derivee du message ne le voit pas, et une regle qui l'interdirait hors du message refuserait cette ligne. Sa lecture reste au diff,
  par moi, avant la fusion (`TP6`). `G3` le mesure.
- **Les points 2 et 3 de `C1'`** (la disposition du message ne lit aucun etat ; aucun element ne s'etend sur la rangee du message et une
  rangee au-dessus) : `G9` passe. Lecture au diff.
- **Un ecran dont le message perdrait sa region d'annonce** sort du controle sans rouge, tant qu'un autre ecran la garde.
- **La limite 4.b** (le verdict passe par le gabarit) est inchangee. **La limite 4.a** est **reduite** : un verdict qui colore la
  coquille depuis un ecran a message rougit (`G7`) ; depuis `fragments/retour.html`, qui pose `.signal` sans region d'annonce, il reste
  vert.
- **Sur-detection possible, son mode de defaillance est un rouge.** Une valeur d'attribut que seul un message pose est un jeton propre :
  `true` (`aria-hidden`) l'est dans `designation.html` et `saisie.html`. Un selecteur `[...=true]` a sujet hors du message, applicable a
  l'un de ces documents, rougirait ; la feuille n'en ecrit aucun.

**Compte ArchUnit inchange** : 41 `static final ArchRule`, 38 `@ArchTest`, 16 fichiers (methode d'`ADR-0104` : `git grep -hcE` des deux
motifs sur les `*.java` suivis, sommes par `awk`), sur `9c271e14` et sur ce commit.

**Consequences.** Bonne : la regle de `TP4` et toute sa famille rougissent, sur tous les ecrans a message, et un jeton neuf du message
entre seul dans le controle. Mauvaises : une regle qui voudrait faire de la place au message dans le meuble, ou au-dessus de lui, est
refusee meme quand l'intention est de disposition ; c'est ce que `C1'` interdit, et le contourner demande d'amender `C1'` avec
`nadia-prof`, pas ce test. La clause depend d'un role ARIA : retirer `role="status"` d'un message pour une raison d'accessibilite sort
cet ecran du controle.

**Alternatives considerees.** **Enumerer les cinq noms de `C1'`** : ecartee, perimetre enumere (`ADR-0013`) ; `G5` passerait, et
`data-oeuvre-revelee` y ferait rougir la ligne 1843. **Deriver le message par sa condition d'affichage** (`th:if` sur la variable du
message) : ecartee, elle ecrit un nom de variable de modele dans le test, et la decision 1 refuse deja de lire les expressions
calculees. **Reutiliser `sujet` de `LAnimationRetardeMaisNeDetermineRienTest`** : ecartee, il coupe a l'interieur des parentheses
(`G10`).

**Ce qui me ferait changer d'avis** : une seconde region d'annonce hors du message (un score annonce, par exemple), qui ferait du score un
jeton du message ; ou un message qui ne serait plus une region d'annonce. La derivation du message serait alors a reprendre.
