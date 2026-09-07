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

Quatre clauses, a l'imperatif.

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
ne porte. `unDepotFautifFaitEchouerLesQuatreClauses` exige les quatre messages.

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
- **Si un marqueur de definition devenait universel** - par exemple parce que `nadia-prof` adopte
  une forme fixe pour poser ses conditions -, la colonne du document deviendrait derivable et le
  registre pourrait maigrir. Le declencheur est mesurable : le marqueur couvre plus de 95 % de la
  population.
