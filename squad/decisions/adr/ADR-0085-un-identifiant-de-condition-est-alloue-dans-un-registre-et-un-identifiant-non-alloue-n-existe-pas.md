# ADR-0085 - Un identifiant de condition est alloue dans un registre, et un identifiant ecrit sans etre alloue n'existe pas

- **Statut** : Accepte
- **Date** : 2026-09-07
- **Auteur** : theo-archi
- **Sprint** : s31
- **Route par** : `nadia-prof`, `PAR-5`, pose le 2026-09-05
  (`ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md`,
  section 13.3), re-route a s29 (`PASSE-TRANSVERSE-s29-les-proprietes-de-portee-depot.md`,
  section 4), a s30 (`PASSE-TRANSVERSE-s30-les-trois-familles-de-portee-depot.md`, section 3.3) et
  par `camille-po` (`DEC-0077`). **Troisieme routage consecutif.** Elle donne la propriete et refuse
  d'en prescrire la forme : *"je dis la propriete ; la forme est a `theo-archi`, et c'est exactement
  le geste qu'`ADR-0081` a fait pour les codes de ligne, pour la meme raison : une identite est
  ecrite ou elle n'existe pas"*.

## Contexte

### Le fait, et il est d'elle

Le 2026-09-03, deux instances de `nadia-prof` ecrivant dix referentiels ont **alloue le meme numero
a deux conditions**. La collision a ete trouvee, et le mode de sa decouverte est tout le sujet :

> *"Elle a ete trouvee parce que la seconde instance est allee ecrire dans le meme espace de noms et
> a vu le numero pris. Si les deux fichiers avaient ete ecrits par deux instances dont aucune ne
> lisait l'autre, personne ne l'aurait vue."*

Elle en tire la clause : **en mode serie, le registre c'est elle, dans sa tete ; en mode parallele,
il n'y a pas de registre.** Depuis, elle a refait la mesure **a la main deux sprints de suite**, et
les deux fois elle a conclu la meme chose : aucune collision creee, et *"cette mesure constate
qu'aucune collision n'a ete creee ; elle ne construit pas le registre"*.

### Le prix de ne pas l'ecrire, chiffre

`PAR-8` borne sa capacite d'audit a **8,0** au lieu de 9,0, dont **1,0 de retenue** sur la
parallelisation. Elle a nomme les deux conditions qui la levent : un second sprint parallele sans
defaut de couture, **et** ce registre. La premiere est acquise ; la seconde bloque seule. **Ecrire
ce fichier vaut +1,0 de budget d'audit par sprint, definitivement.** Aucun autre geste de ma charge
n'a ce rendement, et c'est pourquoi il passe avant tout le reste de s31.

### Le comptage, avec sa methode dans le meme paragraphe

**523 identifiants de condition, sur 83 prefixes, 14 889 occurrences dans 520 fichiers.** Mesure du
2026-09-07, worktree `s31-theo` a `2ef583ba`, JDK **21.0.10** - version rapportee par la machine
virtuelle des tests, jamais par l'entree de toolchain qui la designe (`ADR-0071`).

*Methode : extraction du motif `PREFIXE-[R]nombre` - prefixe de deux a cinq lettres majuscules,
numero d'un ou deux chiffres, non colle a un mot ni a un tiret - sur les 1 198 fichiers texte du
depot, les repertoires de sortie de build, les arbres de contre-exemple et les fichiers binaires
exclus ; puis retrait des prefixes `ADR` et `DEC` et de l'unique homonyme. Dedupliquee.*

**Ce chiffre n'est pas celui de `nadia-prof`, et l'ecart n'est pas une correction.** Elle a compte
**147 identifiants sur 30 prefixes** le 2026-09-05, sur *"les dix-huit referentiels et les
arbitrages prealables de s25 a s29"*. Le mien porte sur le depot entier et sur toute son histoire :
il ajoute les fiches de conformite, les arbitrages anterieurs a s25, le backlog, les rapports de
sprint, les chartes d'agent et le code. **Les deux chiffres sont justes sur leur perimetre.** Le
registre doit porter le plus large : une collision ne demande pas la permission d'etre dans le
perimetre de quelqu'un.

### Trois formes que j'ai essayees et qui ne tiennent pas

Ecrites parce que la forme retenue ne se comprend que contre elles.

1. **Deriver l'allocation d'un marqueur de definition deja present dans les textes.** C'etait la
   forme la moins couteuse - aucun fichier neuf, aucune saisie. Mesuree sur le materiau reel : le
   marqueur gras-tiret (`**\`PAR-5\` - ...**`) donne **un site de definition unique pour 291
   identifiants sur 524, plusieurs sites pour 103, et aucun pour 130**. Une derivation juste a 55 %
   n'est pas une derivation, c'est un generateur de faux rouges. **Forme abandonnee sur mesure, pas
   sur intuition.**
2. **Un registre par prefixe, portant le plus grand numero alloue.** Il tient en 83 lignes au lieu
   de 523 et il oblige a ouvrir le registre pour minter. **Il ne mord pas le defaut reel** : deux
   instances qui allouent toutes deux le numero suivant d'un meme prefixe, pour deux choses
   differentes, portent toutes deux le plafond de ce prefixe a la meme valeur ; la fusion des deux
   textes est identique, et rien ne rougit. Une regle qui n'interdirait pas la seule chose qu'elle
   vise est decorative.
3. **Un registre qui recopie le sens de chaque condition.** Il rendrait la collision lisible d'un
   coup d'oeil - et il creerait un second exemplaire du sens, qui rancirait au premier amendement.
   C'est ce qu'`ADR-0061` refuse. **Le registre porte l'identite, jamais le sens.**

## Decision

**Un identifiant de condition est alloue dans `squad/decisions/REGISTRE-identifiants.md`, une fois
et une seule ; un identifiant ecrit sans y avoir sa ligne n'existe pas.**

Quatre clauses, a l'imperatif. *Une cinquieme s'ajoute le 2026-09-08 - amendement en fin
de document.*

1. **Le registre n'alloue jamais deux fois le meme identifiant.** C'est la collision elle-meme, et
   c'est le seul point ou deux instances qui ne se lisent pas se rencontrent : chacune ajoute sa
   ligne, et le depot en porte deux.
2. **Tout identifiant de condition ecrit dans le depot est alloue par le registre.** C'est la
   friction qui rend l'allocation visible avant d'ecrire : pour ajouter sa ligne, il faut ouvrir le
   registre, et en l'ouvrant on voit ce qui est pris. **C'est le mecanisme meme qui a fait trouver
   la collision du 2026-09-03**, rendu obligatoire au lieu d'etre laisse au hasard.
3. **Le document qu'une ligne nomme ecrit encore l'identifiant qu'elle alloue.** Une allocation qui
   pointe vers un document muet est de la meme famille qu'une reference qui pointe vers rien
   (`ADR-0066`).
4. **Le registre ne porte aucun sens.** Une ligne dit *ou* l'identifiant est ecrit, jamais *ce
   qu'il veut dire*. Le sens vit dans le document qui pose la condition, et nulle part ailleurs.

**Ce que la decision ne fait pas, et il faut le lire avant de s'en servir.** Elle ne verifie pas
qu'un identifiant **designe** ce que sa ligne laisse croire : deux lignes distinctes peuvent porter
deux fois la meme condition sous deux noms, et la CI passera. C'est la meme frontiere qu'`ADR-0081`
trace pour les codes de ligne, et pour la meme raison - l'identite se tient, le sens se lit.

## Regle executable

**Fichier** :
`bootstrap/src/test/java/com/recreo/archi/ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest.java`

Ce n'est **pas une `ArchRule`**, et **le compte de regles `ArchRule` du depot est inchange, a 33.**
ArchUnit lit du bytecode ; un identifiant ecrit dans un texte de gouvernance n'y est pas une
propriete lisible. Le mecanisme est un balayage de sources, sur le precedent de
`ToutesLesReferencesDeDecisionResolventTest`, dont il reprend la lecture de perimetre plutot que
d'en tenir une seconde notion.

### Le perimetre est derive, y compris ses exclusions

Aucun identifiant, aucun prefixe et aucun fichier n'est nomme dans la source hors de son depot
fautif. Les quatre familles voisines sont ecartees **par leur forme** :

| Famille | Pourquoi la forme suffit |
|---|---|
| Code de ligne de referentiel (`HG-CM1-11`) | trois segments : le second n'est pas un numero, et le troisieme est precede d'un tiret que la borne refuse |
| Code de story (`S30-4`, `S26-DETTE-2`) | le prefixe porte un chiffre |
| Rang interne a un document (`M5`, `C4`, `V3`, `R1`) | une seule lettre de prefixe |
| Decision (`ADR-0085`, `DEC-0079`) | quatre chiffres ; et le prefixe est ecarte en plus, parce qu'`ADR-0066` alloue deja ces deux-la |

**Ces quatre lignes ne sont pas une affirmation : elles sont un test.**
`lesFormesVoisinesNeSontPasDesIdentifiantsDeCondition` les ecrit sous leur forme reelle et exige
qu'aucune ne corresponde. La lecon d'`ADR-0015` s'applique aux exclusions comme aux inclusions - un
perimetre qu'on croit derive et qui ne l'est pas est un perimetre faux.

**Une seule forme reste enumeree**, et elle vit dans le registre plutot que dans le Java :
`UTF-8`, homonyme d'encodage. Elle est sous cliquet - la clause 4 exige que **chaque homonyme
ecarte designe encore quelque chose de reel dans le depot** -, et son plafond vaut exactement un.

### La preuve de la morsure, mesuree et non affirmee

**Le garde-fou a trouve un defaut dans sa propre source a sa premiere execution.** Le fichier de
test ecrit les identifiants de son depot fautif dans ses assertions ; la clause 2 les a refuses,
avec raison.
Le trou est celui qu'a `ToutesLesReferencesDeDecisionResolventTest`, repris tel quel plutot que
reinvente : un fichier `<Nom>.java` peut ecrire les identifiants que porte **son propre** arbre
`contreexemple/<Nom>/`, et aucun autre. **C'est le troisieme garde-fou de balayage de ce depot a se
faire mordre par lui-meme le jour de sa naissance, et il faut le dire aussi clairement que le vert
qui suit.**

Le depot fautif (`bootstrap/src/test/resources/contreexemple/ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest/depot-fautif/`)
porte les quatre defauts a la fois : un identifiant ecrit sans etre alloue, un identifiant alloue
deux fois, une allocation qui nomme un document qui ne l'ecrit pas, un homonyme ecarte que plus rien
ne porte. Depuis l'amendement du 2026-09-08 il en porte un cinquieme, un compte courant que
sa propre table dement, et `unDepotFautifFaitEchouerLesCinqClauses` exige les cinq messages.

**Quatre tests, tous verts sur `2ef583ba` plus le present lot, JDK 21.0.10** ; le compte d'objets
examines est assere plutot qu'espere (`ADR-0051`) : plus de 10 000 occurrences et plus de 400
identifiants distincts.

## Consequences

**Bonnes.**

- La condition qui bloque la levee de la retenue de 1,0 de `nadia-prof` est remplie du cote
  technique. **La levee reste la sienne** : je remplis la condition, je ne prononce pas sa
  consequence.
- La mesure a la main qu'elle refait chaque sprint cesse d'etre necessaire.
- Deux instances qui ne se lisent pas se rencontrent desormais dans un fichier, a la fusion, et non
  six mois plus tard dans un texte ambigu.

**Mauvaises, et je les ecris avant qu'on les decouvre.**

- **523 lignes ecrites d'un coup.** C'est la dette accumulee sur trente sprints ; elle se paie une
  fois. Le fichier fait 588 lignes.
- **Toute condition neuve coute une ligne de registre, dans le meme commit.** C'est le prix de la
  propriete, et c'est deliberement une friction : une allocation qui ne coute rien n'est pas visible.
- **La colonne du document est choisie par une regle de priorite, pas par l'histoire.** Le registre
  nomme *un* document qui ecrit l'identifiant, choisi dans l'ordre arbitrage, passe transverse,
  fiche, referentiel, decision, rapport de sprint, backlog, code - l'ordre reel des lieux ou ce
  depot pose ses conditions. **Ce n'est pas une revendication de primaute** : la clause 3 tient que
  le lien existe, pas qu'il soit le premier. Un agent qui saurait mieux corrige la ligne, et la
  clause 3 continue de la tenir.
- **La clause 2 s'applique a tout le depot, y compris a un fichier qui ne fait que citer.** Une
  faute de frappe dans une citation - un numero voisin de celui qu'on visait - devient rouge. C'est
  voulu : c'est la meme famille de defaut que la reference qui pointe vers rien.
- **Elle contraint aussi la prose qui parle des identifiants**, et ce document en a fait les frais
  avant d'etre commite : sa premiere redaction epelait trois identifiants d'exemple et un numero
  hypothetique, dont aucun n'existe. **Un texte qui discute d'une allocation la decrit desormais au
  lieu de l'epeler.** C'est une gene reelle, elle porte sur quelques phrases par sprint, et elle est
  le revers exact de ce qui fait mordre la clause.

## Amendement du 2026-09-07 (s31, verification de la porte technique) - la cecite de branche, et pourquoi elle ne demande aucun dispositif

**Le fait, des le premier assemblage.** Ce registre a ete construit dans le worktree `s31-theo`.
`nadia-prof` a nomme quatre clauses le meme jour dans `s31-nadia-arbitrage`, une branche que ce
worktree **ne pouvait pas voir** - c'est exactement ce que la discipline de worktree dedie, par
ailleurs juste, garantit. A l'assemblage des trois branches, la clause 2 a **fait rougir la CI** sur
quatre identifiants ecrits sans allocation. `sofia-sm` a ferme le rouge par une allocation
mecanique : quatre lignes, aucun sens change, 527 identifiants.

**Enonce de la limite** : *un registre construit depuis un worktree ne voit pas ce qui s'ecrit en
parallele dans un autre.*

**Rien de plus n'est du, et le motif n'est pas la prudence.** Trois raisons, dans l'ordre ou elles
decident.

1. **La limite n'en est pas une, c'est la regle de gouvernance qui parle.** Le depot est la seule
   memoire partagee. Une branche non fusionnee n'est pas encore le depot. Demander a ce registre de
   voir une allocation non fusionnee, c'est lui demander d'etre vrai d'une chose qui, au sens de
   `CLAUDE.md`, **n'existe pas encore**. Le seul instant ou la question est bien posee est la
   fusion - et c'est la que la clause 2 mord.
2. **Le mode de defaillance est le bon.** Le rouge est bruyant, il arrive au plus tot possible, et
   il porte sur ce qui manque. **La propriete de `PAR-5` n'a jamais ete menacee** : la cecite
   produit une **ligne absente**, pas deux conditions sous un meme nom. Une ligne absente se remplit
   mecaniquement, et elle l'a ete.
3. **Tout dispositif qui la supprimerait couterait plus qu'elle.** Faire lire les branches soeurs a
   un garde-fou revient a balayer le travail non fusionne d'un autre agent, ce que l'amendement de
   `squad/README.md` interdit et ce que ma propre charte m'interdit. Une pre-allocation ceremonielle
   au planning couterait a **chaque** sprint pour eviter un defaut qui a coute **un commit
   mecanique**. Une abstraction se justifie sur un usage constate, pas sur un usage anticipe.

**Ce que je ne minimise pas, et qui est le vrai residu.** La cecite a produit ici le cas **bon
marche** - une ligne absente. Le cas que `PAR-5` vise vraiment est l'autre : deux instances allouant
**le meme identifiant a deux conditions differentes**. Il rougit aussi a la fusion, par la clause 1,
**mais son correctif n'est pas mecanique** - il faut renumeroter l'une des deux et reprendre toutes
ses citations. **Le registre rend ce cas visible ; il ne le rend pas bon marche, et personne ne doit
croire le contraire.**

> **Declencheur nomme, pour que ce ne soit pas a redemander** : la **premiere** collision de sens
> constatee a une fusion. Ce jour-la, et pas avant, le remede se chiffre - une reservation de plage
> de numeros par instance, ecrite au planning, une ligne. **Tant qu'elle n'a pas eu lieu, l'ecrire
> serait construire un dispositif contre un defaut imaginaire.**

## Amendement du 2026-09-08 (s31, cloture) - citer n'est pas moins qu'employer, et le compte du registre est verifie

Deux points routes par `sofia-sm` a l'assemblage de fin de s31, sur l'arbre ou les huit commits de
cloture sont reunis. Ils ajoutent une clause et un cliquet, et ils ne touchent pas la clause 2.

### 1. Le fait : la clause 2 a rougi sur une preuve, et la preuve etait juste

`yanis-qa` a mis ce garde-fou a l'epreuve **sur le depot reel**, comme sa charge le demande : il a
insere dans un paquet servi un identifiant fictif jamais alloue, constate le rouge, **cite le
message d'erreur verbatim** dans son rapport de qualite, restaure, reconstate le vert. Son rapport a
ete ecrit apres la mesure, dans un worktree que le garde-fou n'a jamais rebalaye. A l'assemblage, la
clause 2 a rougi sur **son rapport** : elle ne distingue pas un identifiant employe d'un identifiant
cite en preuve.

Le rouge etait juste et il porte sur le bon fichier. Ce qui manquait n'etait pas dans la clause.

### 2. Ce que je tranche : la clause 2 ne distingue pas, et elle ne distinguera pas

**Decision : citer un identifiant n'est pas moins que l'employer.** Aucun contexte textuel - bloc de
code, guillemets, voisinage du mot *identifiant*, section intitulee *mutation* - n'exempte quoi que
ce soit. La consequence ecrite dans cet ADR le 2026-09-07 (*"la clause 2 s'applique a tout le depot,
y compris a un fichier qui ne fait que citer"*) n'est pas revisee : elle est **la** propriete, et non
un effet de bord qu'on aurait supporte.

Le motif est mesure, pas prudentiel. Un bloc de code dans un rapport est exactement l'endroit ou une
faute de frappe sur un numero voisin - le defaut que `PAR-5` vise - **ne se remarque pas**. Exempter
ce contexte, c'est ouvrir le trou sous le seul endroit du depot qui ecrive des identifiants sans les
definir. Il n'y aurait alors plus de garde-fou : il y aurait un garde-fou, plus une convention
d'ecriture pour le contourner.

**La forme derivee que j'ai essayee, et pourquoi elle ne tient pas.** Elle etait tentante : les
identifiants qu'un arbre de contre-exemple porte sont fictifs par construction, il suffirait de les
exempter partout au lieu de les exempter dans leur seul test. Mesure sur l'arbre assemble : les
arbres de contre-exemple du depot portent **46 identifiants** de la forme reconnue, dont **41 sont
alloues** - ce sont de vraies conditions que les fixtures citent legitimement - et **5 ne le sont
pas**, exactement les fictifs. *Methode : extraction du meme motif `PREFIXE-[R]nombre` sur tous les
fichiers des repertoires nommes `contreexemple` du depot, prefixes `ADR` et `DEC` retires, croisee
avec les lignes d'allocation de la section 4 du registre ; arbre `s31-theo-cloture` a l'etat de ce
commit, JDK 21.0.10 rapporte par la machine virtuelle des tests (`ADR-0071`).* La regle derivee
s'ecrirait donc *"est fictif un identifiant qu'un contre-exemple porte et que le registre n'alloue
pas"* - et c'est **circulaire** : *non alloue* est precisement ce que la clause 2 teste. N'importe
qui pourrait desactiver la clause 2 sur son identifiant en l'ajoutant a une fixture. **Une exemption
qui s'auto-delivre est pire que le faux rouge qu'elle supprime.**

### 3. Ce que je fais a la place : une ligne d'ecart, sous un cliquet a plafond exact

C'est le remede que cet ADR avait deja nomme au chapitre *Ce qui me ferait changer d'avis* :
*"si la clause 2 produisait un faux rouge sur une forme legitime que je n'ai pas vue, la reponse est
une ligne d'homonyme dans le registre, pas un assouplissement de la forme"*. Il s'applique tel quel.

- **Le rapport de `yanis-qa` n'est pas touche.** Sa preuve reste verbatim, au caractere. Le depot
  exige qu'une preuve soit montree ; elle l'est.
- **La section 3 du registre gagne une ligne**, qui nomme l'identifiant fictif du depot fautif de ce
  garde-fou et dit pourquoi il est ecrit hors de sa propre source. L'ecart vaut **un identifiant** :
  pas une famille, pas un prefixe, pas un contexte.
- **La section 3 devient un cliquet a plafond exact.** Son plafond, `PLAFOND_D_HOMONYMES`, vit dans
  le fichier de test et vaut **2**. C'est le point qui compte : sans lui, la reponse la moins
  couteuse a n'importe quel rouge de clause 2 serait une ligne de plus dans un fichier de
  gouvernance, sans diff de code et sans decision. Avec lui, **chaque ecart de plus passe par le
  Java**.

**Les autres identifiants fictifs restent refuses hors de leur test**, et c'est mesure : seuls ceux
qui sont **cites hors de leur fixture et de leur propre source** ont une ligne. Le trou derive
d'`ADR-0066` couvre les autres, et cet amendement ne l'elargit pas.

### 4. La preuve que la version amendee mord encore - quatre mutations, dont deux sur du materiau reel

| Mutation | Attendu | Resultat |
|---|---|---|
| Un identifiant non alloue, insere **dans un bloc de code** du rapport de qualite reel, a cote de la citation exemptee | rouge sur la clause 2 | **ROUGE**, 1 echec, le message nomme le rapport et l'identifiant |
| La ligne d'ecart retiree de la section 3 du registre | rouge sur la clause 2 **et** sur le plafond | **ROUGE**, 2 echecs, dont *"Expected size: 2 but was: 1"* |
| Une troisieme ligne d'ecart ajoutee au registre seul, sans diff de code | rouge sur le plafond | **ROUGE**, 2 echecs, dont *"Expected size: 2 but was: 3"* - et la clause 4 rougit en effet de bord, l'ecart ne nommant rien de reel |
| Le compte courant du registre baisse d'une unite | rouge sur la clause 5 | **ROUGE**, 1 echec, le message oppose le nombre declare a celui de la table |

*Perimetre de ces quatre mesures : `mvn -pl bootstrap -am test` sur la seule classe
`ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`, six tests, arbre `s31-theo-cloture`, JDK
21.0.10 rapporte par la machine virtuelle des tests. Chaque mutation a ete restauree et le tree
verifie propre avant la suivante ; la mesure de la porte technique, elle, est le reacteur complet
depuis la racine.*

Un cinquieme cas est permanent plutot que mute : le depot fautif porte desormais un rapport de sprint
qui **ne fait que citer**, sous la forme reelle d'un rapport de qualite, un identifiant ni alloue ni
ecarte, et `uneCitationDansUnMessageDErreurNEstPasExemptee` exige qu'il soit refuse. Le jour ou
quelqu'un ajoute une exemption de contexte a la clause 2, ce test tombe.

### 5. Clause 5 : le registre declare son compte, et sa table le verifie

**Le second fait, de la meme famille.** L'arbre assemble porte cote a cote **trois** notes
d'allocation ecrites le meme jour : la mienne (527 identifiants, 87 prefixes), celle de `nadia-prof`
instance B (+2, prefixe neuf `RGA`), celle de son instance A (532 identifiants, 89 prefixes, prefixes
neufs `EMO` et `POL`). Chacune est **juste sur son perimetre** et porte sa methode ; aucune des deux
instances ne pouvait voir l'autre ; `sofia-sm` les a gardees verbatim au conflit de cherry-pick, avec
raison. **Le compte de l'arbre assemble n'etait donc ecrit nulle part.** C'est la forme (b) d'un
defaut de couture au sens de `nadia-prof` : un chiffre que ni l'une ni l'autre moitie ne peut
verifier depuis sa branche.

**Clause 5, a l'imperatif : le registre declare son compte courant sous une forme figee, et ce compte
egale celui de sa table.** Une seule ligne du fichier pretend dire l'etat courant ; les notes datees
qui l'entourent sont des mesures passees, chacune avec sa methode et son auteur, et **aucune n'est
retouchee** - une mesure datee ne pourrit pas, un etat courant si.

Ce que la clause interdit reellement, et c'est la question a se poser avant de l'ecrire : elle
interdit qu'un nombre survive a la table qu'il decrit. Ce defaut n'est pas hypothetique, il a eu lieu
**deux fois en deux jours** sur ce meme fichier - un compte de prefixes faux de quatre unites,
corrige par son auteur et signale independamment par `nadia-prof` ; puis le compte de l'arbre
assemble, absent. **Le mode de defaillance devient un rouge a la fusion, la ou les deux moities
existent**, et son correctif est un comptage. Aucune instance n'a besoin de lire la branche d'une
autre : c'est exactement la propriete que `PAR-5` demande.

**Le prix, ecrit.** Deux instances qui allouent en parallele toucheront la **meme ligne**, donc
produiront un conflit de fusion sur cette ligne, a chaque fois. C'est voulu, et c'est le meilleur des
modes disponibles : un conflit se voit et se resout mecaniquement, un nombre faux ne se voit pas.
J'ai ecarte la forme opposee - **supprimer tout compte courant du registre**, au motif qu'un second
exemplaire d'un fait rancit (`ADR-0061`) - parce qu'elle n'est pas encodable : on ne teste pas
l'absence d'un nombre dans de la prose sans un motif qui attraperait aussi les mesures datees, ce qui
est la faute mesuree en s05.

**Le compte de l'arbre assemble, avec sa methode.** **534 identifiants alloues, sur 90 prefixes
distincts**, et **zero doublon** - les 534 lignes de la table portent 534 identifiants distincts,
donc la clause 1 est verte sans exemption. *Methode : extraction des lignes de la table de la
section 4 du registre a la forme `| identifiant | document |`, denombrement des identifiants
distincts et des prefixes distincts ; arbre `s31-theo-cloture` a l'etat de ce commit. Le chiffre
recoupe les trois notes : 532 (instance A, sur l'etat qui precede l'allocation de l'instance B) + 2
(instance B) = 534, et 89 + 1 = 90.* Les deux instances etaient justes ; il ne manquait que
l'addition, et c'est desormais la CI qui la fait.

### 6. La cecite de branche ne demande toujours aucune clause, et ce sprint le confirme

`sofia-sm` demande si la limite structurelle nommee le 2026-09-07 - *un registre construit depuis un
worktree ne voit pas ce qui s'ecrit en parallele dans un autre* - demande une clause de plus. **Non,
et l'evenement de ce sprint est un argument de plus contre, pas pour.**

Deux instances de `nadia-prof` ont alloue en parallele, sans se voir, sur le meme fichier. Ce que la
cecite a produit : **aucune collision, aucune ligne manquante, aucun sens perdu**. Les deux ont
ouvert la table, verifie que leur prefixe etait libre, ecrit leur ligne et leur methode. Le seul
degat etait **un nombre agrege que ni l'une ni l'autre ne pouvait calculer** - et c'est ce que la
clause 5 attrape, a la fusion, sans qu'aucune branche ait besoin d'en lire une autre.

La limite reste donc **nommee et assumee**, avec le declencheur deja ecrit : la premiere collision de
sens constatee a une fusion. Elle n'a pas eu lieu ce sprint-ci non plus.

## Alternatives considerees

- **Ne rien ecrire et continuer la mesure a la main.** Ecartee : c'est le statu quo de trois
  sprints, il coute 1,0 de budget d'audit par sprint et une mesure manuelle repetee, et
  `nadia-prof` a ecrit deux fois qu'il ne construit pas le registre.
- **Un marqueur de definition pose dans chaque texte, sur le modele d'`ADR-0081`.** Ecartee sur
  mesure : le marqueur existant ne couvre que 55 % de la population, et le poser retroactivement
  demanderait de reecrire les arbitrages de `nadia-prof`, ce qui n'est pas mon geste.
- **Un registre par prefixe.** Ecartee : elle ne mord pas la collision, mesure faite au chapitre
  Contexte.
- **Un registre portant le sens de chaque condition.** Ecartee par `ADR-0061` : un second
  exemplaire du sens rancit.

## Impact

- `squad/decisions/REGISTRE-identifiants.md` (neuf)
- `bootstrap/src/test/java/com/recreo/archi/ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest.java`
  (neuf)
- `bootstrap/src/test/resources/contreexemple/ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest/`
  (neuf)
- `squad/decisions/adr/ADR-index.md`

## Ce qui me ferait changer d'avis plus tard

- **Si la clause 2 produisait un faux rouge sur une forme legitime que je n'ai pas vue**, la
  reponse est une ligne d'homonyme dans le registre, pas un assouplissement de la forme.
  *C'est arrive le 2026-09-08, et c'est exactement ce qui a ete fait - amendement, section 3.
  La ligne est desormais sous cliquet a plafond exact : la reponse reste disponible, elle
  n'est plus gratuite.*
- **Si le plafond de la section 3 devait monter plusieurs fois en quelques sprints**, l'ecart
  cesserait d'etre un cas et deviendrait une famille ; il faudrait alors chercher une forme
  derivee, ce que la section 2 de l'amendement n'a pas trouve pour un cas unique. Declencheur
  mesurable : un troisieme ecart.
- **Si un marqueur de definition devenait universel** - par exemple parce que `nadia-prof` adopte
  une forme fixe pour poser ses conditions -, la colonne du document deviendrait derivable et le
  registre pourrait maigrir. Le declencheur est mesurable : le marqueur couvre plus de 95 % de la
  population.
