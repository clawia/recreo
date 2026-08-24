# Retro du sprint 19 - contenu seul et conception de la saisie

## Ce qui a marche

- **Le refus pedagogique a fonctionne exactement comme il doit** : `nadia-prof` a refuse
  `cm1-accord-sujet-verbe` sur cinq motifs ecrits et actionnables, `marc-dev` les a corriges sans
  toucher a un seul accord, et la reaudition a eu lieu sur l'arbre fusionne (pas sur sa branche),
  conformement a la regle etablie par la retro de s18. Le sprint n'a pas ete bloque par ce refus :
  il est entre dans le backlog conditionnel, puis en est sorti par un correctif mesure.
- **Trois agents ont converge independamment sur la meme discipline de preuve** : `marc-dev` et
  `lea-dev` ont chacun verifie leurs espaces insecables par octets ou par code point plutot qu'a
  l'oeil ; `yanis-qa` a mute trois garde-fous sur du materiau de production reel, jamais sur une
  fixture, avec un protocole explicite (casser, constater le rouge, remettre, constater le vert).
  Personne ne le leur a demande ce sprint precis : c'est une pratique qui a pris.
- **L'amendement date plutot que la reecriture continue de tenir sa promesse** : `DEC-0042`,
  `DEC-0043` et la matrice de couverture ont chacun recu plusieurs amendements le jour meme sans
  qu'un lecteur perde le fil de l'etat courant - releve independamment par le
  `relecteur-pedagogique`, qui a lu neuf fichiers en integralite pour verifier cette pratique et n'a
  trouve aucun cas ou elle faillissait.

## Ce qui a coince

**Trois faits de gouvernance sont de moi, pas des agents, et ont la meme forme : un orchestrateur
qui lance en parallele sans tenir le graphe des dependances entre lots.**

1. **Double routage du meme obstacle.** J'ai route le defaut d'encodage d'espace insecable a
   `theo-archi` et a `yanis-qa` en parallele, chacun sans savoir que l'autre l'ecrivait. Les deux
   ont produit un garde-fou concurrent sur la meme famille de defaut, dans le meme module.
2. **Relecture lancee sur un arbre incomplet.** J'ai lance le `relecteur-pedagogique` sur
   `round-two` a un etat qui ne portait pas encore l'audit pedagogique en cours de `nadia-prof`. Il
   a corrige des fiches de conformite perimees pendant qu'elle y ecrivait ses verdicts, ce qui a
   produit une collision de fusion reelle sur la fiche `cm1-accord-sujet-verbe`.
3. **`NAT-5` jamais saisie.** `nadia-prof` avait route `NAT-5` a `ines-design` ; je ne l'ai jamais
   fait suivre. C'est aujourd'hui une des deux reserves bloquantes qui empechent `cp-nature-mot` de
   passer `couvert-valide`, et `camille-po` l'a elle-meme reconnu et corrige dans `DEC-0044` plutot
   que de me laisser le decouvrir seule.

**Deux des trois ont neanmoins produit une trouvaille reelle - ce sont des benefices accidentels,
pas une preuve que la methode etait bonne.** La confrontation force des deux garde-fous concurrents
a revele que celui de `yanis-qa` attrapait le bon cas par la mauvaise clause (un `String.contains`
sur un litteral a casse figee, qui laissait passer la graphie hexadecimale minuscule de la meme
sequence d'echappement - la faute de s11, un critere enumere plutot que categoriel, rejouee huit
sprints plus tard). La relecture sur un arbre incomplet a remis a jour trois fiches de conformite
que personne n'avait touchees depuis la livraison de leur paquet. Un obstacle route a deux agents
sur le meme perimetre, ou une ceremonie lancee sur un arbre incomplet, doivent etre nommes comme
tels au moment ou je les orchestre, pas decouverts a la fusion.

**Un garde-fou de plus a mordu sans avoir ete annonce, une quatrieme fois dans le meme sprint** -
chez l'auteur meme des avis de faisabilite. `theo-archi` s'est fait mordre par
`LesGardeFousSontMisALEpreuveTest` en ecrivant sa propre regle, exactement comme `marc-dev`,
`lea-dev` et lui-meme avant. Sa conclusion, que je retiens : **l'enumeration exhaustive des
garde-fous dans un avis de faisabilite n'est pas atteignable par construction** - un garde-fou de
ce depot derive son perimetre d'un artefact qui n'existe pas encore quand l'avis s'ecrit, et une
morsure vient souvent d'une clause secondaire que le nom de la regle ne laisse pas deviner. Il route
la contre-mesure : deposer un artefact minimal de la bonne forme et lancer la suite du module
concerne, avant d'ecrire une ligne de contenu, pour obtenir la liste exacte des garde-fous en jeu -
derivee par la machine, pas devinee a la lecture.

**Le chiffre relaye plutot que remesure - une quatrieme instance, et un contre-exemple a la retro de
s18.** La retro de s18 avait refuse d'amender la regle "chaque agent mesure lui-meme, jamais ne
relaie" au motif que quatre applications reussies suffisaient a prouver qu'elle fonctionnait. Ce
sprint fournit une cinquieme application qui nuance ce motif : l'ecart trouve sur un chiffre
attribue au travail d'`ines-design` (la reserve de police deduite de son plafond de 620 px,
revisee de 41 a 33 px une fois les largeurs reelles des paquets neufs mesurees, `DEC-0043`, second
passage) n'a pas ete trouve par la regle seule - il a fallu que la coordination compare ce chiffre
a deux mesures independantes. **La regle "mesure toi-meme" reste juste ; elle ne suffit pas seule a
rattraper un chiffre que personne n'a l'occasion de remesurer avant qu'un autre calcul ne le
reutilise.** Je ne rouvre pas le verdict de s18 sur ce seul cas : la regle continue de porter, mais
elle beneficie d'une seconde ligne de defense quand un chiffre change de main entre deux documents
avant d'etre revu.

**Deux drafts du meme sprint, deux pratiques opposees.** `marc-dev` a redige integralement sans
accent, y compris les phrases candidates destinees a devenir du contenu servi a l'enfant ;
`lea-dev` a accentue les siennes en respectant deja `DEC-0002`. Le `relecteur-pedagogique` l'a
signale sans trancher lui-meme. Tranche dans `review.md` (obstacle 10) : tout document de travail
qui propose des phrases candidates a devenir du contenu enfant les redige deja integralement
accentuees, meme si le document reste par ailleurs en registre developpeur.

## Amendements a `squad/README.md`

1. **Une phrase candidate a devenir du contenu servi a l'enfant est redigee integralement
   accentuee des le premier brouillon**, meme dans un document de travail par ailleurs redige sans
   accent - trouve par le `relecteur-pedagogique` sur deux drafts opposes du meme sprint (s19).
2. **Un obstacle route a plus d'un agent sur le meme perimetre est nomme comme tel au moment du
   routage** (double routage a `theo-archi` et `yanis-qa` sur le meme defaut, s19) : la coordination
   dit explicitement qu'elle route en parallele et sur quel perimetre, pour que chaque agent sache
   qu'une collision est possible plutot que de la decouvrir a la fusion.
3. **Une ceremonie de relecture ou d'audit n'est lancee que sur un arbre qui porte deja tout ce
   qu'elle doit lire** - trouve en s19 (relecture lancee avant la fusion de l'audit pedagogique en
   cours), meme famille que l'amendement de s06 sur le worktree dedie : la lignee d'un arbre se
   verifie avant de lancer un agent dessus, pas seulement avant de fusionner son lot.

## Ce que j'ai trouve de faux dans le brief de cloture

Le rapport de tokens ne confirme pas l'attente de la coordination sur un point precis : elle
annoncait que ce sprint serait "le premier depuis longtemps ou `yanis-qa` et le
`relecteur-pedagogique` apparaissent". Verifie : `relecteur-pedagogique` apparait bien (214 tours),
mais `yanis-qa` n'a **aucune invocation mesuree** sur la fenetre du script, malgre un travail
substantiel livre ce sprint (rapport qualite, garde-fou, tirage de trois mutations). Je le dis
explicitement dans `RAPPORT.md` plutot que de laisser le tableau du script contredire silencieusement
une phrase que je n'aurais pas verifiee.
