# Avis de mecanisme - ou vit la clause invariante de `S17-3`, et la specificite dans `ADR-0046`

- **Auteur** : theo-archi. **Date** : 2026-08-19.
- **Branche** : `s17-theo-clause`, worktree dedie, forkee de **`round-two` a `39b3c42`** - SHA releve
  par `git rev-parse round-two` dans le depot, pas repris d'un message ; lignee verifiee par
  `outils/verifier-lignee.sh`.
- **Repond a** : `camille-po` (`DEC-0039`, cinquieme amendement, commit `7065394`, branche
  `s17-camille-s17-3-refus`, non fusionnee) sur la question posee au mot pres par `nadia-prof`
  (`FICHE-bandit-manchot-synonymes-cm1.md`, branche `s17-nadia-contenu`) ; et au troisieme trou
  d'`ADR-0046` que `marc-dev` me laisse (`S17-D`, commit `2773943`, fusionne).
- **Porte technique** : `bash outils/mvn-jdk21.sh -B clean verify`, reacteur complet, JDK 21.0.10 -
  **BUILD SUCCESS**, **581 tests**. Detail et methode en section 4.
- **Ce que je ne fais pas** : je ne juge aucune pedagogie, je ne tranche aucun perimetre, je ne
  relis pas les autres lots, et je n'ouvre pas `S17-C` (hors perimetre par arbitrage de `camille-po`).

## Verdict en une page

| Question | Verdict |
|---|---|
| 1. Ou peut vivre la clause invariante ? | **Dans le champ `consigne` de l'entree de catalogue. Il existe deja, il est obligatoire, il est deja rendu.** Zero ligne de production, zero champ de schema, zero type de contenu neuf. `ADR-0052` |
| 1 bis. Ce que cela ne donne pas | **Aucun garde-fou a `SYN-5`.** La hauteur rendue reste non gardable et `nadia-prof` reste seule garde. Le gain n'est **pas** une suppression de repetition a l'ecran : il est typographique |
| 2. La derivation d'`ADR-0046` doit-elle comparer une specificite ? | **Oui.** Elle verifiait un proxy - "la maison declare" - de ce qu'elle promet - "l'enfant voit la maison". Clause ecrite dans le meme commit, troisieme amendement d'`ADR-0046` |

## 1. La clause invariante : la consigne, et rien d'autre

### Ce que j'ai verifie a la source, et ce que cela ajoute au chiffre de `nadia-prof`

Lecture des vingt enonces de `cm1-synonymes.yaml` sur `39b3c42`. La clause **n'est pas invariante au
sens litteral** : elle enchasse le mot cible.

```
Lea est "contente" de son cadeau. Dans cette phrase, quel mot peut remplacer "contente" sans changer le sens ?
```

Decoupage : une **tete fixe de 41 caracteres** (`Dans cette phrase, quel mot peut remplacer `), le
**mot cible repete** - il est deja dans la phrase - et une **queue fixe de 23 caracteres**
(` sans changer le sens ?`). Soit **64 caracteres strictement identiques** d'un item a l'autre, dans
la fourchette de 60 a 66 que `nadia-prof` a mesuree, et un mot qui, lui, appartient a l'item.

Cette distinction decide de la forme de la reponse : **ce qui peut sortir de l'item est la partie
fixe, pas le mot.**

### La reponse : le champ `consigne` de l'entree de catalogue

Il existe deja, et tout le chemin existe deja avec lui :

| Etape | Etat sur `39b3c42` |
|---|---|
| `catalogue.yaml` | `consigne` est un champ **obligatoire** de chaque entree - `YamlContentPackLoader` echoue sans lui |
| `application` | `GameDefinition.consigne` |
| `adapter-web` | `PartieController` la met au modele, `EtatDePartieEnSession` la garde en session |
| gabarit | `fragments/manche.html` rend `<p class="consigne">`, une fois par ecran, au-dessus de l'enonce |
| feuille | `.consigne` : 1,25rem, poids normal |

**Cout en code de production : zero ligne.** Aucun champ ajoute au schema de contenu, aucun `type`
neuf, aucune composante de plus sur `Question`. **Cout au-dela de ce qui etait deja du : nul** -
l'entree de catalogue doit exister de toute facon, c'est le second motif du refus (*"ce jeu n'existe
pas pour un enfant"*), et la consigne en est un champ obligatoire.

**Les deux autres places possibles sont fermees, et pas par preference.**

- **Une cle neuve par item** : la fabrique `question` **ignore sans un mot** toute cle qu'elle ne
  connait pas (dette nommee par `ADR-0024`, mesuree et ecrite dans mon avis de mecanisme du
  texte-support). Une cle `clauseInvariante:` chargerait, passerait tous les garde-fous de contenu,
  laisserait la CI verte, et l'enfant ne verrait rien. La faire exister demanderait le mecanisme
  complet d'`ADR-0050` - cinq fichiers, quatre modules - **pour vingt copies d'un texte au lieu
  d'une**, c'est-a-dire l'inverse du besoin.
- **Un `type` de contenu neuf** : premiere marche vers les neuf familles de chargeurs de la version
  precedente, pour une difference qui est un texte et non une structure.

### Ce que ce mecanisme donne, et ce qu'il ne donne pas - dans cet ordre

**Il ne donne aucun garde-fou a `SYN-5`, et je l'ecris avant le reste parce que la question m'est
posee explicitement.** Aucun test de ce depot ne mesure une hauteur rendue (`ADR-0025`, `ADR-0028`),
aucun ne le fera tant qu'aucun outil ne pilote un navigateur, et cette capacite a quitte le perimetre
de la squad (`DEC-0039`, section 3). **`nadia-prof` reste seule garde de `SYN-5`**, exactement comme
elle l'ecrit dans sa fiche, et ma reponse ne change rien a cela. Je le dis d'autant plus nettement que
je viens d'ecrire deux fois ce sprint qu'une hauteur rendue n'est pas gardable : un troisieme rappel
vaut mieux qu'un silence qui laisserait croire qu'un correctif de mecanisme apporte une preuve.

**Il ne supprime pas une repetition a l'ecran, et cette nuance compte pour la mesure a venir.** La
consigne et l'enonce sont rendus **une fois chacun par ecran** : la clause s'affichera autant de fois
apres qu'avant. Ce qui change est **la typographie** (elle quitte `.enonce`, 2rem gras, pour
`.consigne`, 1,25rem normal) et **le lieu d'ecriture** (une fois dans le catalogue au lieu de vingt
fois dans le paquet). La formule "deja rendu une fois par ecran plutot que repete par item" decrit la
repetition dans le **fichier**, pas a l'ecran - je la corrige ici pour que personne n'attende du
deplacement un gain qu'il ne produit pas.

**Ce qui est derivable des chiffres deja publies, et rien de plus.** Interlignes cites par
`ines-design` et `nadia-prof` : 48 px pour `.enonce`, 30 px pour `.consigne`. A nombre de lignes
inchange, les 3 a 4 lignes de la clause coutent donc **54 a 72 px de moins**. Une police plus petite
fait tenir plus de caracteres par ligne, donc moins de lignes - **de combien n'est pas derivable**,
aucune feuille de ce depot ne declarant de `font-family` (`ADR-0025`). Le deficit mesure est de
**63 a 159 px**. **Le plancher du gain ne couvre donc meme pas le meilleur cas des vingt items, et le
plafond n'est pas calculable ici.** Ce n'est ni un feu vert ni un feu rouge : c'est un ordre de
grandeur qui dit que le mecanisme rend `SYN-5` **atteignable** sans la prononcer, et qu'une mesure de
rendu sur les vingt items reste indispensable avant toute entree au catalogue.

### Ce qui est gardable, et ce que j'encode

**Une seule chose, et elle est la contrepartie directe de la decision** : la consigne cesse d'etre un
ornement et devient **porteuse**. Un ecran qui montrerait l'enonce d'un item sans elle laisserait
l'enfant devant une phrase et trois mots, sans rien qui dise quoi en faire.
`ToutEcranEchangeEstUnDocumentJouableTest` porte desormais cette clause, sur le **document assemble**
(`ADR-0020`), perimetre derive des routes du controleur, avec **deux morsures sur la sortie reelle du
serveur mutee** : consigne retiree, consigne videe.

**Ce qu'elle interdit reellement** - le test que je m'impose avant d'ecrire une regle : qu'un lot
gagne des pixels verticaux en retirant la consigne de l'ecran de manche. La tentation est **installee
par ce refus meme** (le paquet deborde le pli de 63 a 159 px), et la consigne etait le seul bloc de
texte de cet ecran qu'aucun garde-fou ne retenait. Ce n'est pas une regle decorative : c'est le
premier endroit ou quelqu'un ira chercher des pixels.

**Ce que je refuse d'encoder, en le nommant.**

- **Une longueur de consigne**, ou n'importe quel seuil de caracteres : `DEC-0016` l'interdit,
  `camille-po` l'a deja ecarte, et `ADR-0028` a montre qu'une telle regle rougirait sur du contenu
  valide.
- **Une regle qui detecterait la clause invariante dans les items** (par exemple : aucun long
  fragment commun a tous les enonces d'un paquet). Tout seuil - "plus de N mots", "plus de X % de
  l'enonce" - serait **le mien**, sur une propriete de contenu dont `nadia-prof` est juge. Je note ce
  que couterait mon absence de regle : rien n'empechera un futur paquet de reintroduire une clause
  repetee dans ses items. C'est un **fait a savoir**, pas une regle a inventer.
- **Une regle "un paquet servi a une entree de catalogue"**, qui aurait attrape le second motif du
  refus (le jeu injouable). `ADR-0039` a deja tranche cette derivation **dans ce sens-la** et l'a
  refusee : un paquet retire du catalogue est la matiere de travail d'une story de retour
  (`DEC-0009`, `DEC-0024`), et ce sprint vient d'en produire un exemple de plus avec `S17-4`. La
  derivation inverse - chaque entree charge son paquet - existe et est verte. Je ne rouvre pas cette
  decision, et je la nomme pour que personne ne la repropose comme une evidence.

### Ce que je route, et qui n'est pas de moi

**A `nadia-prof`** : la consigne etant ecrite une fois pour vingt items, elle ne peut plus nommer le
mot cible et doit le designer autrement - *"le mot entre guillemets"*, par exemple. **Est-ce encore
`SYN-1` ?** `SYN-1` est sa condition ; si elle refuse la designation generique, ce mecanisme tombe et
il faut revenir vers elle, pas vers le code. Cette question rejoint sa reserve deja ouverte sur la
marque du mot cible (le guillemet droit n'est pas une ponctuation francaise) : les deux se decident
ensemble, puisque la designation generique s'appuie sur la marque.

**A `camille-po` et `sofia-sm`** : la reecriture des vingt enonces est du contenu, l'entree de
catalogue est due de toute facon, et la mesure de rendu qui prononce `SYN-5` appartient a
`nadia-prof`. Rien de tout cela n'est du code d'architecture.

## 2. `ADR-0046` : oui, la derivation doit comparer une specificite

### Le verdict, et son motif en une phrase

**Oui.** Le garde-fou promet que l'enfant voit la peinture de la feuille maison ; il verifiait que la
feuille maison **declare**. Declarer est un **proxy** de gagner, et le proxy est faux des qu'une regle
tierce est plus specifique - ce que `ADR-0014` point 5 dit **deja** depuis son premier amendement
("l'ordre de chargement ne joue qu'a specificite egale"). Une regle qui verifie un proxy de ce qu'elle
promet est un souhait avec un `assert` autour. C'est le **troisieme** trou de la meme famille dans la
meme derivation, et le premier qui porte non sur ce qu'elle ne voyait pas mais sur ce qu'elle
verifiait.

### La clause, et le chiffre qui a decide de sa forme

Pour chaque exigence **deja satisfaite** au sens de la clause de declaration, la specificite de la
regle maison qui la satisfait doit etre **au moins egale** a celle de la regle tierce la plus
specifique qui l'imposait. L'egalite suffit - la feuille maison est chargee apres la tierce, elle
gagne les egalites.

**L'asymetrie qui a failli me faire ecrire une clause fausse, et que je nomme parce qu'elle est le
fond du sujet.** La clause de declaration rattache une regle tierce a un bouton par son seul compose
du meme element, **sans verifier qu'un prefixe s'applique** (ancetre, frere, pseudo-classe
structurelle). Elle sur-collecte donc - et c'est **sans danger la-bas** : elle exige une declaration
de plus, ce qui ne casse rien. La meme sur-collecte est **fausse** pour une comparaison de
specificite : elle obligerait a battre des regles qui ne concourent jamais.

**Mesure, sur la production servie** : ecrite sans predicat d'applicabilite, la clause rend **14
motifs sur 8 pressables**, tous imputables a la seule regle tierce `.btn-check+.btn:hover` - qu'aucun
gabarit de ce depot ne peut declencher, `btn-check` n'y existant nulle part. **Ces 14 motifs auraient
fait prefixer huit selecteurs maison par un identifiant pour un adversaire imaginaire.** La clause
n'evalue donc la specificite que contre les regles tierces qui s'appliquent **reellement** au bouton
rendu, evaluees par le moteur de selection sur l'element du gabarit avec le jeu de classes de cet etat
rendu.

**Ce predicat n'entre pas dans la clause de declaration**, et ce n'est pas un oubli : l'y mettre
retirerait des exigences a une clause qui gagne a sur-collecter, et ferait tomber le cliquet de dette
qui vaut exactement son plafond. Affaiblir un garde-fou en passant, pour en corriger un autre, est la
faute que ce depot a deja sanctionnee.

**La lecture de specificite suit la specification pour les pseudo-classes fonctionnelles** :
`:is()`, `:not()`, `:has()` prennent la specificite de leur argument le plus specifique, `:where()`
vaut zero. Ce n'est pas un raffinement theorique : sans le traitement de `:not()`, la forme reelle
`:not(.btn-check)+.btn:active` vaudrait (0,2,0) au lieu de (0,3,0) et passerait pour perdante alors
qu'elle gagne - **la clause serait verte sur le defaut meme qu'elle ferme**. Une construction
illisible est un **echec**, jamais un cas ignore : une specificite maison surevaluee est un faux vert,
et refuser ce qu'on ne sait pas lire est la seule direction sure.

### La morsure, et ce qu'elle mesure

La feuille servie est copiee puis **remise dans sa forme d'avant `S17-D`** : l'ancetre `#jeu` ajoute
par `marc-dev` est retire, `.demarrer:active` redevient (0,2,0) face a `.btn:first-child:active`
(0,3,0) - l'etat exact de la feuille au moment ou `nadia-prof` a mesure 1,64:1. **Et voici la mesure
qui compte le plus dans ce lot : sur cette meme feuille mutee, la clause de declaration reste verte.**
La declaration est la, elle ne gagne pas ; sans la clause neuve, la CI ne voit rien. Aucune fixture
ecrite de ma main n'entre dans cette preuve.

**Etat de la production apres cette clause** : **57 exigences declarees** comparees, **toutes
satisfaites**. Le correctif de `marc-dev` tient donc au sens de la specificite, et pas seulement au
sens de la declaration.

### Ce que cet amendement ne ferme pas, avec son declencheur

- **Le prefixe d'ancetre d'une regle maison n'est pas verifie non plus.** `#jeu .demarrer:active`
  serait creditee a `.demarrer` meme si ce bouton vivait hors de `#jeu`. L'angle mort est **herite**
  de la clause de declaration, pas introduit ici - mais il devient plus expose depuis que l'ajout d'un
  ancetre est la sortie recommandee pour gagner de la specificite. **Dette explicite**, declencheur
  ecrit : le premier lot qui ajoute un ancetre a une regle d'etat dont le pressable peut etre rendu
  **hors** de cet ancetre. Motif de ne pas la fermer aujourd'hui : un ancetre peut etre rendu sous
  condition (`th:if`), et un predicat trop strict produirait des rouges sur des ancetres conditionnels
  legitimes - l'inverse du defaut qu'on corrige. **Verifie sur le cas du jour** : `.demarrer` vit bien
  sous `<main id="jeu">` dans `accueil.html`, le correctif de `marc-dev` est donc sain.
- **Les regles tierces nichees sous `@media` restent invisibles** a cette derivation : `blocsTiers`
  aplatit la feuille et rend la condition d'une regle-`@` comme une cle de selecteur. Limite heritee,
  avant comme apres cet amendement, nommee ici pour qu'elle ne se decouvre pas a la prochaine mesure.

**Ce que `marc-dev` a mesure et que je confirme structurellement** : ajouter une classe au **sujet**
maison (`.btn`) ferait de `btn` une classe connue de la feuille maison et changerait l'identite
calculee de **chaque** bouton du produit. Le message d'echec de la clause neuve nomme la sortie
correcte - un ancetre reel separe par un combinateur - pour que le prochain lot ne refasse pas cet
essai.

## 3. Ce que je refuse de trancher, nomme

- **La formulation de la consigne** (designation generique du mot cible) : `nadia-prof`, section 1.
- **La marque du mot cible** (guillemet droit) : `nadia-prof` pour la regle de francais,
  `ines-design` s'il s'agit d'un habillage. Je note seulement le fait technique : le schema de contenu
  ne porte **aucun balisage**, donc toute marque vit aujourd'hui dans le texte lui-meme.
- **Le degre sur quatre items** (`fatigue`/`epuise`) : `nadia-prof` l'a explicitement classe non
  bloquant et matiere de `S17-5`.
- **Un seuil qui detecterait une clause repetee dans les items** : je le refuse et j'ecris ce que ce
  refus laisse ouvert (section 1).
- **`S17-C`** : hors perimetre de s17 par arbitrage de `camille-po`. Je n'y touche pas.

## 4. Ce que ce lot mesure, avec sa methode

- **Porte technique** : `bash outils/mvn-jdk21.sh -B clean verify` a la racine du worktree,
  **BUILD SUCCESS**, reacteur complet (6 modules), JDK 21.0.10 (toolchain declaree). **581 tests** :
  48 (`domain`) + 13 (`application`) + 286 (`adapter-web`) + 141 (`adapter-content`) + 87
  (`bootstrap`, surefire) + 6 (`bootstrap`, failsafe). Comptes lus sur les six lignes de synthese de
  module du journal, jamais sur une somme de lignes par classe.
- **+7 de ce lot, mesures classe par classe** : **3** dans
  `ToutEcranEchangeEstUnDocumentJouableTest` (la clause de consigne et ses deux morsures, **10 -> 13**)
  et **4** dans `AucunEtatDePressableNEstAbandonneAuTiersTest` (la clause de specificite, sa morsure
  sur la feuille d'avant `S17-D`, l'epinglage des valeurs reelles des deux feuilles et le refus de
  l'illisible, **7 -> 11**). Les deux comptes de depart viennent d'executions ciblees de ce lot, pas
  d'une soustraction. **Le total de 574 annonce pour `39b3c42` n'est pas de moi et je ne l'ai pas
  remesure** ; il est coherent avec mes +7 (574 + 7 = 581), et c'est tout ce que j'en dis.
- **Mesures partielles** : toutes portent `-am` et nomment leur module a cote de leur chiffre.
- **Le compte des regles ArchUnit ne bouge pas** : il reste a **29**. Aucune des deux clauses n'en est
  une - l'objet juge est un document HTML rendu pour l'une, une feuille de style pour l'autre.
- **Mutations posees puis retirees** : la clause de non-vacuite de la specificite inversee pour lire
  la liste des exigences comparees (57), restauree et reverifiee ; la feuille servie mutee vit dans un
  repertoire temporaire, jamais dans l'arbre. `git status --short` est propre hors des fichiers de ce
  lot.
