# ADR-0070 - Aucune cle de schema d'un paquet servi n'est ignoree de tout le depot

- **Statut** : Accepte. **Son garde-fou est ecrit, vert et mis a l'epreuve dans le meme commit** -
  `bootstrap/src/test/java/com/recreo/archi/AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest.java`,
  4 tests : un cas vert sur le materiau reel et trois morsures, dont une par mutation du materiau
  reel et une sur un jeu fautif commite.
- **Sprint** : s24. **Date** : 2026-08-30. **Auteur** : theo-archi.
- **Arbre mesure** : worktree `s24-theo`, branche `round-two` a `ea546cf`, JDK **21.0.10** de la
  toolchain declaree, `bash outils/mvn-jdk21.sh`.
- **Ferme une dette nommee et laissee ouverte par `ADR-0024`**, reprise sans etre fermee par
  `ADR-0050` (clause 6), par l'avis de mecanisme de s17 et par l'avis de faisabilite de s22.
- **Prolonge** : `ADR-0007` (un seul chargeur pilote par le schema), `ADR-0013` (perimetre derive,
  garde-fou mis a l'epreuve), `ADR-0039` (une propriete calculee plutot qu'une enumeration),
  `ADR-0051` (un garde-fou qui n'examine aucun objet le declare).
- **Ne code aucune mecanique, ne prononce aucun Done, ne decide aucun contenu.**

## Contexte

`QuestionContentSourceFactory` **ignore sans un mot toute cle qu'elle ne connait pas**. Sa propre
Javadoc l'ecrit depuis `ADR-0024` : *"Une cle qu'elle ne connait pas est toujours ignoree sans un
mot - c'est la dette que l'ADR nomme et n'a pas fermee."*

**Le mode de defaillance n'est pas l'echec, c'est l'ecart muet.** Une cle ecrite dans un paquet que
la fabrique ne lit pas - parce qu'elle est mal orthographiee, ou parce qu'elle a ete ecrite d'apres
une conception que le code n'a pas encore rejointe - laisse le chargement reussir, la CI verte, et
l'enfant devant un ecran ampute de ce que l'auteur du paquet avait ecrit. Rien, nulle part, ne le
dit.

**Ce depot a paye trois fois le meme remede, et jamais la propriete.** A chaque champ de contenu
neuf, la reponse ecrite a ete *"un test qui traverse la frontiere HTTP pour ce champ-la"* :
`ADR-0050` clause 6 pour `texteSupport`, l'avis de mecanisme de s17, l'avis de faisabilite de s22
(section 4 de sa story de texte-support). Ces tests existent et ils sont bons -
`LeTexteSupportDUnItemReelAtteintLEcranDeMancheTest`,
`LExplicationDUnItemReelAtteintLEcranDeRetourTest`,
`LaPartDesigneeDUnItemServiEstMarqueeALEcranTest`. **Ils gardent chacun un champ ; aucun ne garde la
propriete.** Un champ neuf sans son test reste un vert silencieux, et c'est le lot qui doit y penser.

**Pourquoi maintenant, et pas a un sprint quelconque.** L'ouverture de la saisie multiplie les
occasions par trois d'un seul coup : une forme d'item neuve porte au moins une forme de reference,
une liste de formes acceptees et un mode de forme, toutes ecrites dans des paquets que
`nadia-prof` relit et qu'aucune fabrique ne lit encore le jour ou ils sont ecrits. **Le remede par
champ ne passe pas a l'echelle d'une forme d'item.** Et le pire cas est pire qu'un ecran vide : une
liste de formes acceptees perdue en route ne vide pas l'ecran, elle **refuse une reponse juste**, et
personne ne le voit.

## Decision

### 1. Une cle que personne ne nomme est un defaut, et c'est la propriete qui est gardee

**Toute cle de schema ecrite dans un paquet servi est nommee, comme litteral de chaine, par au moins
une source Java du depot.** Une cle que la fabrique lit satisfait la propriete ; une cle qu'un
garde-fou de contenu relit la satisfait aussi ; une cle que personne ne nomme fait echouer le build.

C'est deliberement **la propriete faible** : *personne ne nomme cette cle*, et non *cette cle est
lue au bon endroit*. La propriete forte demanderait de savoir ce qu'un programme fait d'une valeur,
ce qu'aucune machine de ce depot ne sait. La propriete faible attrape les deux fautes reellement
rencontrees - la cle mal orthographiee et la cle ecrite avant son champ - et c'est de celles-la
qu'il s'agit.

### 2. Le critere compare des chaines entieres, jamais des sous-chaines, et il ignore les commentaires

- **Chaines entieres.** `graphi` n'est pas `graphie`. Une detection par sous-chaine est la faute
  mesuree en s05 - la regle passait sa propre epreuve et echouait dans le monde - et elle est fermee
  ici par construction plutot que par vigilance.
- **Commentaires exclus.** Une cle nommee dans une Javadoc, ou dans un `ADR` recopie en commentaire,
  est **exactement** le cas de la cle ecrite avant son code. L'accepter viderait le garde-fou de son
  objet, et il faut le dire dans ce sens : la conception ecrite d'avance est le vecteur meme du
  defaut, pas une circonstance attenuante.
- **Les sources de test comptent, et c'est mesure et non concede.** Trois cles reellement servies
  aujourd'hui ne sont lues par aucune fabrique et sont relues par des garde-fous de contenu :
  `graphie` (`cp-le-bon-son`), `relationBonneReponse` et `relationsDistracteurs`
  (`cm1-mots-de-liaison`). **Mesure** : en restreignant le balayage aux sources de production, ces
  **trois** cles, et seulement ces trois, deviennent orphelines. Restreindre le perimetre ferait
  donc refuser trois paquets sains pour attraper une cle morte.

### 3. Ce qui n'est pas descendu, et pourquoi ce n'est pas un trou

Le garde-fou lit les cles de la racine du paquet, celles de chaque item, et celles des deux blocs
dont les sous-cles sont du schema (`descripteur`, `parametres`). Il ne descend pas dans
`relationsDistracteurs`, dont les sous-cles sont des mots de liaison ecrits par l'auteur : **ce sont
des valeurs, et les juger comme des cles reviendrait a juger le contenu**. La frontiere est celle du
schema, pas celle de la profondeur.

### 4. Ce que cette decision ne fait pas

- **Elle ne rend pas la fabrique stricte.** Refuser au chargement toute cle inconnue serait plus
  direct et **serait rouge aujourd'hui sur trois cles saines** (decision 2). Il faudrait alors une
  liste blanche de cles tolerees, qui pourrit exactement comme les listes que ce depot a deja
  sanctionnees. Le garde-fou obtient la meme propriete sans liste et sans exemption.
- **Elle n'ouvre aucune exemption, aucune dette, aucun plafond.** Il n'y en a pas besoin : le
  materiau reel passe, aujourd'hui, sans une seule exception.

## Regle executable

**Ce n'est pas une regle ArchUnit**, et je le dis plutot que de laisser le silence trancher :
ArchUnit modelise des types Java et leurs relations ; les objets juges ici sont **des cles d'un
fichier de ressource et des litteraux de chaine d'un fichier source**, dont aucun n'est un type.
C'est un garde-fou de balayage, cinquieme motif distinct de cette famille apres la declaration de
feuille de style (`ADR-0059`), la chaine issue du chargement d'une ressource (`ADR-0060`), la valeur
d'une constante compilee (`ADR-0061`) et le document HTML servi (`ADR-0062`).

**Fichier** :
`bootstrap/src/test/java/com/recreo/archi/AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest.java`.
**Jeu fautif** :
`bootstrap/src/test/resources/contreexemple/AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest/`.

> **Toute cle de schema d'un paquet servi est un litteral de chaine d'au moins une source Java du
> depot, commentaires exclus.**

**Perimetre derive, jamais enumere** (`ADR-0013` obligation 1) :

- les **modules** viennent du pom agregateur, par `LesGardeFousSontMisALEpreuveTest.modulesDeclares`
  - reutilisee et non reecrite, parce que deux notions de *"quels modules existent"* divergeraient un
  jour ;
- les **paquets** viennent du repertoire de contenu de chaque module qui en porte un, **jamais du
  catalogue** : un paquet retire du catalogue mais laisse sur disque reste juge, exactement pour le
  motif etabli par `ChaquePaquetPorteUnIndiceTest` en s05 ;
- les **cles** viennent de SnakeYAML, la bibliotheque avec laquelle le chargeur de production lit
  ces memes fichiers, jamais d'une seconde lecture ecrite a la main ;
- les **litteraux** viennent d'une analyse a quatre etats du fichier Java - code, litteral de
  chaine, litteral de caractere, commentaire. Une expression reguliere aurait suffi a l'oeil et
  aurait fait disparaitre la fin de toute ligne portant `http://` dans une chaine, donc rougir sur
  une cle saine.

**Le garde-fou se retire lui-meme du balayage des sources**, par son propre nom de fichier et non
par une liste, sur le patron de `LesGardeFousSontMisALEpreuveTest` : il ecrit necessairement
quelques cles en litteraux - celles de l'enveloppe de schema, celle de sa clause de non-vacuite - et
se porter garant de ce qu'il juge rendrait ces cles-la vertes sans qu'aucune fabrique ne les lise.

**Mesure du cas vert, methode comprise.** 27 paquets dans `adapters/adapter-content/src/main/
resources/contenu`, **23 cles de schema distinctes**, **193 fichiers `.java`** dans les arbres
`src/main/java` et `src/test/java` des cinq modules declares, **4 355 litteraux de chaine
distincts** : **zero cle orpheline**, sans une seule exemption. Les 23 cles sont `bonneReponse`,
`competence`, `descripteur`, `distracteurs`, `domaine`, `enonce`, `explication`, `graphie`, `id`,
`indice`, `indicesNommentLaReponse`, `items`, `matiere`, `niveau`, `nombreDePropositions`,
`parametres`, `partDesignee`, `relationBonneReponse`, `relationsDistracteurs`, `schema`,
`sommeMaximale`, `texteSupport`, `type`.

**Trois morsures** (`ADR-0013` obligation 3), et une par famille de defaut :

1. **La cle morte, par mutation du materiau reel.** Les paquets servis et les sources du depot, plus
   une cle injectee **en memoire** - rien n'est ecrit sur disque, donc rien ne pourrit le jour ou un
   champ neuf apparait. Le message doit nommer la cle **et** le paquet qui la porte.
2. **La cle nommee seulement dans un commentaire.** Le jeu fautif commite porte un paquet dont un
   item declare une cle qui n'existe que dans la Javadoc de sa fabrique, entre guillemets. Cette
   fabrique fautive nomme en litteraux **toutes les autres cles** de ce paquet : la morsure exige
   que le message designe la seule cle fautive et **pas** `bonneReponse`. C'est cette seconde moitie
   qui distingue *"le garde-fou rougit"* de *"le garde-fou rougit pour la bonne raison"*.
3. **Le perimetre vide** (`ADR-0051`). Un repertoire de contenu deplace rendrait le cas vert vert
   sur zero paquet ; la clause de non-vacuite echoue au lieu de passer, et elle est elle-meme
   mordue.

**Ce que ce garde-fou ne prouve pas**, ecrit avant qu'on me le reproche : qu'une cle citee quelque
part soit lue **au bon endroit**. Un litteral pose dans un test qui n'ouvre aucun paquet le
satisferait. Les tests de traversee par champ (`ADR-0050` clause 6 et ses deux voisins) restent donc
utiles et ne sont remplaces par rien ; ce qui change est qu'un champ **oublie** n'est plus silencieux.

**Compte des regles ArchUnit du depot : inchange a 30.** Ce lot n'ajoute aucun champ `ArchRule`.
Meme methode que dans `ADR-0069`.

**Mesure de l'execution** : `bash outils/mvn-jdk21.sh -B -o -pl bootstrap -am test
-Dtest=AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest -Dsurefire.failIfNoSpecifiedTests=false`
- **4 tests, 0 echec, BUILD SUCCESS**. Le `-am` est porte, le perimetre est `bootstrap` et ses
amonts.

## Consequences

- **Une cle de saisie ecrite dans un paquet avant que son champ existe arrete le build**, en nommant
  la cle et le paquet. C'est la reponse a la question que ce sprint pose, et c'est un garde-fou, pas
  une consigne.
- **Le cout d'un champ de contenu neuf monte d'un cran, et c'est voulu.** Ecrire la cle dans un
  paquet sans que rien ne la lise devient rouge : la fabrique et le paquet entrent dans le meme
  commit, ou le paquet attend. Mauvaise consequence assumee - un auteur de contenu qui prend de
  l'avance sur le code voit un rouge qu'il ne sait pas lever seul.
- **Une declaration d'auteur reste possible** - `graphie`, `relationBonneReponse` - a la condition
  qu'un garde-fou la relise. C'est exactement ce que `ADR-0024` avait decide pour `graphie`, et qui
  n'etait tenu par rien.
- **Ce garde-fou vit dans `bootstrap` et non dans `adapter-content`**, parce qu'il doit voir les
  sources de tous les modules. Il ne compile ni n'execute rien de ces modules : il les lit comme du
  texte, comme les trois recensements d'`ADR-0013` le font deja.
- **Une limite nommee d'avance** : l'analyse des sources ne traite pas le bloc de texte (`"""`). Le
  depot n'en contient aucun - mesure le 2026-08-30, zero occurrence dans les quatre arbres de
  modules - et le jour ou il en portera un, cette methode devra l'apprendre. Ecrit ici pour que ce
  soit un declencheur et non une decouverte.

## Alternatives considerees

1. **Rendre chaque fabrique stricte** - toute cle inconnue arrete le chargement. Ecartee sur mesure
   et non par gout : rouge aujourd'hui sur `graphie`, `relationBonneReponse` et
   `relationsDistracteurs`, donc adossee a une liste blanche de tolerances. Une liste blanche de
   cles est un perimetre enumere qui echoue par omission, la famille de defaut que ce depot a deja
   payee deux sprints.
2. **Deriver les cles acceptees des composantes du record d'item** (`Question.class
   .getRecordComponents()`), sur le patron deja en service dans
   `AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest`. Ecartee pour le meme motif : cette
   derivation est exacte pour les cles que la fabrique lit, et elle ne connait aucune des trois cles
   de declaration d'auteur. Elle repondrait a *"quelles cles la fabrique lit"*, pas a *"quelle cle
   personne ne lit"*.
3. **Un test de traversee par champ, une fois de plus.** Ecartee : c'est le remede deja ecrit trois
   fois, il garde un champ nomme et laisse le champ suivant a la vigilance. La saisie en ajoute
   trois d'un coup.
4. **Une regle ArchUnit sur les fabriques** - par exemple, toute fabrique doit declarer l'ensemble
   des cles qu'elle lit. Ecartee : elle deplace la liste dans le code sans dire si elle correspond a
   ce que les paquets ecrivent, et une declaration jamais confrontee au materiau est un souhait.

---

**Mesure de cloture de ce lot, methode comprise.** `bash outils/mvn-jdk21.sh -B clean verify` a la
racine du reacteur - **BUILD SUCCESS, 807 tests, 0 echec** (49 `domain` + 13 `application` +
332 `adapter-web` + 288 `adapter-content` + 119 `bootstrap` surefire + 6 `failsafe`), JDK 21.0.10 de
la toolchain declaree. Pas de `-am` a citer : le reacteur est complet par construction. Ce lot ajoute
**cinq tests** - les quatre d'`AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest` et la morsure
d'`ADR-0069` ; je n'ai pas rejoue le build de reference avant le lot, et le delta se lit dans le
diff plutot que dans un chiffre que je n'aurais pas mesure.
