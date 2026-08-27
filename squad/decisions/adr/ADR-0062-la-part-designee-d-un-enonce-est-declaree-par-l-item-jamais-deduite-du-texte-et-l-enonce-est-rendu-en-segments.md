# ADR-0062 - La part designee d'un enonce est declaree par l'item, jamais deduite du texte, et l'enonce est rendu en segments dont un seul est le cas ordinaire

- **Statut** : Accepte. **Sa regle etait ecrite ici et rouge le 2026-08-25, jour de cet ADR** :
  elle jugeait des objets qui n'existaient pas encore, et son declencheur etait le commit qui livre
  `S20-1`. Meme forme que `R-A` et `R-B` d'`ADR-0058`.
  **Amendement du 2026-08-27 (`theo-archi`, cloture s20)** : `S20-1` est fusionne et **les deux
  clauses sont vertes** sur `round-two` a `9ccbb1d` -
  `LaPartDesigneeDUnItemServiEstMarqueeALEcranTest` 6 tests et
  `UnePartDesigneeApparaitUneSeuleFoisDansSonEnonceTest` 5 tests, 0 echec, lus dans les blocs
  `Results:` d'un `bash outils/mvn-jdk21.sh -B clean verify` a la racine (694 tests au total).
  La phrase d'origine est datee plutot que reecrite : elle decrivait l'etat d'un arbre, et un tel
  enonce se perime avec l'arbre. Mesure et methode dans
  `squad/sprints/s20-mot-cible-designe-et-conception-de-la-saisie/confirmation-archunit.md`.
- **Sprint** : s20. **Date** : 2026-08-25. **Auteur** : theo-archi.
- **Story** : `S20-1` (`docs/backlog/backlog.md`), geste 1, route a `theo-archi` par `DEC-0047`.
- **Ne decide pas** : la regle CSS, les canaux visuels, la levee de `NAT-5`, la portee produit du
  renfort. `ines-design` propose, `nadia-prof` tranche, `camille-po` a deja arbitre la portee.

## Contexte

`NAT-5` est la derniere reserve bloquante de `bandit-manchot-nature-mot-cp`, hors catalogue depuis
`DEC-0045`. `nadia-prof` exige que le mot a classer soit designe autrement que par les guillemets
francais seuls, et jamais par la seule couleur, parce que la designation **change a chaque manche**
la ou la consigne, elle, est constante.

**Le fait technique, verifie au rendu servi par `ines-design` et re-verifie ici a la source.**
`PartieController.mancheModele` pose `modele.addAttribute("enonce", session.enonceManche().orElse(""))`,
et `fragments/manche.html` rend `<p class="enonce" tabindex="-1" th:text="${enonce}">`. Le releve
d'`ines-design` sur l'application servie donne `nbNoeuds : 1 (type 3 = noeud texte)`. Aucune regle
CSS ne peut viser une sous-chaine d'un noeud texte : ce n'est pas une limite d'aptitude, c'est ce
que le document rendu contient. **Marquer le mot exige donc une balise, donc une donnee, donc un
changement du contrat de contenu** - pas une feuille de style de plus.

**Le contournement qui existe, et pourquoi il est ferme.** Le gabarit partage pourrait parser les
guillemets francais et marquer ce qu'il trouve entre eux. `ines-design` le refuse parce que le
gabarit generique connaitrait alors une convention de contenu que les jeux sans mot cible n'ont
jamais. Ce motif est juste, et il en existe un second qui suffit seul : **`cm1-synonymes` et
`cm1-antonymes` portent la meme convention de guillemets**, mesuree par `ines-design` en y injectant
`.mot-designe`. Un gabarit qui deduit marquerait donc les trois paquets, alors que `DEC-0047` decide
que le renfort entre sur `cp-nature-mot` **seul** ce sprint, sur une regression mesuree a 640 px sur
`cm1-antonymes`. Tenir cette portee avec la deduction demanderait un aiguillage par nom de paquet
dans le code de rendu - la forme que `ADR-0061` vient de sanctionner le 2026-08-24 sous le nom de
recopie d'une association du catalogue. **Le contournement ne peut pas livrer ce que la story
demande** ; ce n'est pas un arbitrage de gout entre deux formes propres.

**La contrainte que `camille-po` pose au contrat** : un jeu marque et deux jeux non marques, servis
par le meme gabarit, sans que le non-marque soit un cas particulier bricole.

## Decision

### 1. La part designee est **declaree par l'item**, dans une composante et une seule

`Question` gagne **une** composante facultative, `Optional<String> partDesignee`, et rien d'autre.
La chaine `enonce` **ne change ni de type ni de valeur** : elle reste la phrase entiere, guillemets
compris.

Trois precisions qui font partie de la decision :

- **Facultative veut dire "l'item n'a rien designe", pas "il manque une valeur".** C'est la
  formulation deja ratifiee pour `Verdict.explication` par `ADR-0024` : *"`Optional.empty()` n'est
  pas un manque : c'est la reponse normale pour un item que le contenu n'a pas juge digne
  d'explication."* Douze paquets ne declarent rien et ne portent aucune valeur factice - c'est la
  dette 2 de la version precedente que cette forme evite, et c'est le point sur lequel je refuse le
  triplet (voir Alternatives).
- **Le nom ne privilegie aucune matiere.** `motDesigne` designerait un mot, c'est-a-dire du
  francais ; une mecanique qui designe une portion d'un enonce peut un jour designer un nombre.
  `partDesignee` est neutre, et il ne collide pas avec "partie", qui nomme deja une session de jeu
  dans ce depot. `AucunVocabulaireDeMatiereTest` ne le refuse pas : sa liste porte `addition`,
  `soustraction`, `multiplication`, `operande`, `somme`, `calcul`, `syllabe`, `conjugaison`,
  `fraction`, `graphie`, `phoneme`, `nasale`, et aucune de ces sous-chaines n'y figure. **Verifie,
  pas suppose.**
- **La commodite de schema vit dans `adapter-content`, pas dans le domaine**, sur le patron deja en
  service pour `indicesNommentLaReponse` : une cle facultative par item, lue par
  `QuestionContentSourceFactory`.

### 2. L'enonce est **rendu en segments**, et le segment unique est le cas ordinaire

`PartieController` cesse d'exposer `enonce` comme une chaine et expose une **suite de segments**,
chacun portant son texte et le fait d'etre designe ou non. Le gabarit fait une boucle unique, avec
une classe conditionnelle.

- Un item qui ne declare rien rend **un segment, non designe** : la phrase telle qu'aujourd'hui.
- Un item qui declare une part rend **trois segments** - avant, designe, apres - et le milieu porte
  la marque.

**Ce que cette forme achete, et c'est le point** : le gabarit n'a **pas de branche**, le jeu non
marque n'est **pas un cas particulier**, et aucun paquet ne remplit de champ vide. Le nombre de
segments est un fait de donnee, jamais un aiguillage de code.

**Le type des segments vit dans `adapter-web`**, jamais dans le domaine : c'est une forme de rendu,
et le domaine n'a aucun usage constate d'un enonce decoupe. Trois lignes de derivation dans le
controleur valent mieux qu'un type de domaine que personne d'autre n'utilise.

### 3. Le decoupage est une **derivation**, et il est non ambigu par contrainte de contenu

Le controleur decoupe `enonce` autour de `partDesignee`. Cette derivation n'est pas un parcours de
convention : elle cherche une **valeur declaree**, pas une ponctuation. Elle a un mode de
defaillance et un seul - une part declaree qui apparait **plusieurs fois** dans la phrase, ou
**pas du tout** : le mauvais mot serait marque, ou aucun, et la CI serait verte. Ce mode de
defaillance est ferme par la clause 2 de la regle ci-dessous, pas par la vigilance.

### 4. La part designee entre dans **l'identite d'ecran**

`AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest` devient **rouge** des que `Question` gagne une
composante : `chaqueComposanteDUnItemEstClasseeDansLIdentiteDEcranOuHorsDElle` compare l'ensemble
des composantes classees a l'ensemble des composantes portees. C'est le cliquet d'`ADR-0050`, il
fait exactement son travail, et le lot repond dans le meme commit.

**La reponse est "dans l'identite", et ce n'est pas un reflexe.** Deux items de meme enonce et de
memes propositions qui designent deux mots differents - *"La maitresse ecrit au tableau"* en
designant `maitresse`, puis en designant `ecrit` - sont **deux ecrans differents** et deux questions
differentes. Les classer hors de l'identite ferait refuser la seconde comme une copie de la
premiere.

## Regle executable

**Ce n'est pas une regle ArchUnit, et ce n'est pas par defaut de rigueur.** ArchUnit modelise des
types Java et leurs relations ; les objets juges ici sont **le document HTML servi** et **le contenu
servi par le chargeur reel**. C'est le quatrieme motif distinct de cette famille, apres la
declaration de feuille de style (`ADR-0059`), la chaine produite par le chargement d'une ressource
(`ADR-0060`) et la valeur d'une constante compilee (`ADR-0061`). Le mecanisme automatique est un
garde-fou de balayage, dans le module ou vit son objet.

### Clause 1 - la marque suit la declaration, dans les deux sens

**Fichier du** : `bootstrap/src/test/java/com/recreo/bootstrap/rendu/LaPartDesigneeDUnItemServiEstMarqueeALEcranTest.java`,
sur le modele de `LaConsigneDUnJeuServiAtteintLEcranDeMancheTest` et de
`LExplicationDUnItemReelAtteintLEcranDeRetourTest`, qui traversent deja la chaine reelle jusqu'au
document rendu.

> **Dans le document servi d'une manche, un element porte la marque de designation si et seulement
> si l'item declare une part designee, et le texte de cet element est exactement la valeur
> declaree.**

Les deux moities, et ce que chacune interdit :

- **Presente si declaree, et egale a la declaration.** Interdit un marquage qui deborde (les
  guillemets pris dans la marque), qui tombe a cote, ou qui n'arrive pas jusqu'au document.
- **Absente si non declaree, partout ailleurs.** **C'est cette moitie qui ferme le contournement**
  refuse en contexte : un gabarit qui deduirait la marque des guillemets marquerait les items de
  `cm1-synonymes` et de `cm1-antonymes`, qui ne declarent rien - rouge. Sans cette moitie, la regle
  serait decorative : elle constaterait que ce qui est marque est bien marque, et ne dirait rien de
  ce qui l'est a tort.

**Perimetre derive** (`ADR-0013` obligation 1) : les entrees de `catalogue.yaml` et les items rendus
par le chargeur reel, jamais une liste de paquets ecrite dans le test. **Aucun nom de paquet
n'apparait dans ce fichier** - ni `cp-nature-mot`, ni les deux paquets CM1 que `DEC-0047` exclut du
renfort. C'est la lecon d'`ADR-0061`, appliquee au moment ou la tentation existe.

**Non-vacuite** (`ADR-0051`) : si **aucun** item servi ne declare de part designee, la clause
n'examine aucun objet et **echoue** au lieu de passer verte a vide. C'est ce qui la rend rouge
aujourd'hui, et ce qui la rendra rouge de nouveau le jour ou le dernier paquet marque disparaitrait
du catalogue.

### Clause 2 - une part declaree apparait exactement une fois dans son enonce

**Fichier du** : `adapters/adapter-content/src/test/java/com/recreo/adapter/content/UnePartDesigneeApparaitUneSeuleFoisDansSonEnonceTest.java`,
meme famille et meme forme de perimetre que les garde-fous de contenu existants.

> **Pour chaque item servi qui declare une part designee, cette valeur apparait exactement une fois
> dans son enonce.**

**Ce qu'elle interdit reellement** : la seule defaillance silencieuse que la derivation de la
decision 3 peut produire. Zero occurrence marque le mauvais endroit ou rien du tout ; deux
occurrences marquent la premiere, qui n'est pas forcement celle que le contenu visait, et l'enfant
classe un autre mot que celui qu'on lui demande. Dans les deux cas la CI est verte et seul un
humain qui joue les seize items le voit - c'est-a-dire la porte de `nadia-prof`, transformee en
relecture manuelle de ce qu'un test sait faire.

**Perimetre derive** : le repertoire de contenu, jamais une liste. **Non-vacuite** : zero item
declarant est un echec, meme clause qu'au-dessus.

### Mises a l'epreuve dues avec le lot (`ADR-0013` obligation 3)

Sur du materiau reel, jamais sur une fixture de ma main - la lecon de s05 :

1. **La marque retiree du gabarit** : clause 1 rouge sur la premiere moitie.
2. **La marque posee sur un item qui ne declare rien** - la mutation qui simule exactement le
   contournement refuse : clause 1 rouge sur la seconde moitie. **Sans cette morsure, rien ne
   prouve que la seconde moitie mord.**
3. **La marque posee sur `partDesignee` guillemets compris** : clause 1 rouge sur l'egalite de
   texte, la ou une assertion de simple presence serait restee verte.
4. **Un item reel du paquet marque, mute pour declarer une part absente de sa phrase, puis pour en
   declarer une qui y figure deux fois** : clause 2 rouge dans les deux cas.
5. **Le paquet marque retire du catalogue** : les deux clauses rouges par non-vacuite.

### Ce que ces deux clauses **ne** font pas, et je le dis avant qu'on me le demande

- **Elles n'interdisent pas d'etendre le marquage a `cm1-synonymes` ou `cm1-antonymes`.** Une regle
  qui l'interdirait devrait nommer ces deux paquets, ce qu'`ADR-0061` refuse. Ce qu'elles font est
  autre chose et vaut mieux : elles rendent l'extension **impossible autrement que par une
  declaration explicite dans le contenu**, donc visible dans un diff, donc opposable a la porte
  pedagogique. La portee decidee par `DEC-0047` reste une decision produit, gardee par la relecture
  et non par un automate - et c'est le bon endroit pour elle.
- **Elles ne mesurent aucune position rendue.** Qu'un item bascule a deux lignes, ou passe sous le
  pli, releve de la famille `ADR-0054` a `ADR-0059` et de la mesure de `nadia-prof`. La clause de
  `S20-1` sur les 0 items sur 16 a 744 x 551 n'est pas tenue par ces deux clauses-ci.
- **Elles ne disent rien de la forme visuelle.** Deux canaux non colores, la couleur de fond, la
  taille : `ines-design` propose, `nadia-prof` tranche, aucun automate n'en juge.

## Consequences

**Bonnes.**

- Le gabarit partage reste ignorant de toute convention de contenu, et il le reste **de facon
  verifiee** et non par discipline.
- Un paquet neuf qui veut designer une part n'ecrit **aucune ligne de Java** : une cle dans son
  YAML.
- Le jeu non marque n'a aucun cout : pas de champ, pas de valeur vide, pas de branche.

**Mauvaises, et elles sont reelles.**

- **Une composante de plus sur `Question`**, type traverse par tous les paquets. Je l'assume parce
  que la designation est **par item** et ne peut donc vivre ailleurs, mais c'est bien un champ que
  douze paquets portent sans l'employer. La difference avec la dette 2 de la version precedente est
  qu'ils ne le **remplissent** pas : `Optional.empty()` n'est pas une valeur factice. C'est une
  difference reelle, pas une nuance de vocabulaire - un `minOperand: 0` de francais devait etre
  ecrit par un humain dans un fichier.
- **`Question` gagne un troisieme constructeur de commodite**, et c'est la forme exacte du defaut
  d'`ADR-0016`. Je ne pretends pas que ce soit propre : c'est le prix de ne pas rouvrir les seize
  sites de construction des douze autres paquets dans un sprint qui ne les concerne pas. **Dette
  nommee ici, pas decouverte plus tard.**
- **Le cliquet d'`ADR-0050` rougit** et coute une ligne de classement dans le lot. C'est le systeme
  qui fonctionne, pas un obstacle.
- **Le controleur porte trois lignes de derivation de plus.** Elles ne sont couvertes par aucune
  regle d'architecture ; elles le sont par la clause 1, sur le document servi.

**Ce qui me ferait changer d'avis.** Un second usage reellement constate d'un enonce structure -
deux parts designees dans une meme phrase, ou une part designee dans une proposition et non dans
l'enonce. Le jour ou il existe, la composante devient une liste et le type de segment descend
peut-etre dans le domaine. **Je ne l'ouvre pas d'avance** : un usage imagine ne justifie pas une
piece mobile.

## Alternatives considerees

1. **Trois composantes `(avant, partDesignee, apres)`, la forme que le critere de `S20-1`
   suggere.** Ecartee. Douze paquets devraient porter deux composantes vides, le gabarit devrait
   tester la vacuite du milieu, et **le jeu non marque deviendrait le cas particulier** - exactement
   ce que `camille-po` demande d'eviter. C'est aussi la forme la plus proche de la dette 2. Note :
   `ines-design` **ne propose pas** cette forme ; son document (`e5a8360`) propose un `<span>` et
   route la forme de la donnee.
2. **Changer le type de `Question.enonce()` en une suite de segments.** Ecartee, et c'est
   l'alternative la plus serieuse : elle supprime toute derivation et rend le segment unique
   naturel jusque dans le domaine. **Cout mesure**, methode comprise : **48 fichiers `.java` hors contre-exemples citent `enonce`**
   (51 avec les trois sources de contre-exemple, `target/` exclu, balayage du worktree a `c48c0e6`),
   dont une trentaine de garde-fous de contenu qui le lisent comme une chaine
   (`LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest`, `AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest`,
   les tests de paquet). Le compilateur les attraperait tous - le mode de defaillance serait bruyant,
   pas silencieux, et c'est ce qui rend cette option defendable. Elle est ecartee parce qu'elle fait
   payer a douze paquets et a trente garde-fous une propriete dont un seul jeu a l'usage
   aujourd'hui. **Ce qui la ferait revenir** : un second jeu qui designe, ou un garde-fou qui aurait
   besoin de lire la structure et non le texte.
3. **Parser les guillemets dans le gabarit partage.** Ecartee pour les deux motifs du contexte, dont
   le second est dirimant : elle ne peut pas honorer la portee decidee par `DEC-0047` sans un
   aiguillage par nom de paquet.
4. **Parser les guillemets dans `adapter-content` plutot que dans le gabarit.** Ecartee, et c'est le
   piege de cette famille : deplacer la deduction ne la supprime pas. La convention de contenu
   serait toujours encodee dans du code, elle s'appliquerait toujours aux trois paquets, et elle
   serait seulement moins visible.
5. **Un attribut de donnee sur `.enonce` que le CSS exploiterait sans balise interieure.** Ecartee
   sur un fait, pas sur un gout : CSS ne sait pas selectionner une sous-chaine d'un noeud texte,
   quel que soit l'attribut porte par son parent. C'est la mesure d'`ines-design`, et elle ferme
   cette famille entiere.

## Mesure de reference, methode comprise

Prise sur `s20-theo` a `c48c0e6`, arbre propre, avant ce lot :

- `bash outils/mvn-jdk21.sh -B clean verify` a la racine - **BUILD SUCCESS, 668 tests, 0 echec**
  (48 `domain` + 13 `application` + 328 `adapter-web` + 181 `adapter-content` + 92 `bootstrap`
  surefire + 6 `failsafe`). Pas de `-am` a citer : le reacteur est complet par construction. Machine
  virtuelle des tests : JDK **21.0.10**, celui de la toolchain declaree, lu dans le journal du build.
- **Compte des regles ArchUnit : 30 regles reelles, inchange par ce lot** - il n'ajoute aucune
  `ArchRule`. Methode : balayage des sources `.java` du worktree, `target/` exclu ; 35 lignes portent
  `ArchRule `, dont 3 dans le faux depot de contre-exemple de `LesGardeFousSontMisALEpreuveTest` et 2
  dans ce meme fichier qui ne sont pas des declarations de champ.
- **Ce lot n'ajoute aucun test et n'en fait passer aucun** : ses deux clauses sont dues avec le code
  de `S20-1` et rouges aujourd'hui. Le compte de 668 est donc celui d'avant **et** d'apres.
