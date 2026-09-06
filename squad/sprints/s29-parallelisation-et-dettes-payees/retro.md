# Retro de s29 - parallelisation et dettes payees

## La couture a casse exactement comme elle avait ete nommee d'avance, et dans le sens qui la cache le mieux

`nadia-prof` avait ecrit, avant que le sprint ne soit joue : *"ca casse d'abord sur la couture, et
ce mode de defaillance n'existait pas au sprint dernier. Il est silencieux : rien ne rougit, la
ligne ferme, la propriete n'a ete verifiee par personne."* Les deux instances ont chacune annonce
huit lignes fermees pour le sprint - cinq pour l'Instance A, en comptant "avec les trois de
l'Instance B" -, et aucune des deux n'a jamais totalise neuf. `camille-po` l'a trouve en recomptant
ligne a ligne les dix-huit tableaux de la matrice plutot qu'en reprenant un total transmis, ce
qu'elle fait a chaque cloture depuis plusieurs sprints. La ligne manquante, `EMC-CP-06`, n'etait ni
un refus ni un verdict retire : elle avait ferme des le premier passage de l'Instance B, avec un
Done pedagogique prononce et une mention ecrite dans sa fiche des le 2026-09-05. L'Instance A la
citait dans son propre releve, sans jamais l'ajouter a son propre compte - chaque instance
connaissait exactement sa part, aucune ne portait la somme des deux.

**Ce qui merite d'etre dit, et que le chiffre seul ne dit pas** : le defaut est alle dans le sens
favorable. Un sprint qui se serait cru a sept lignes quand il en ferme neuf aurait ete corrige de
la meme facon, mais un sprint qui se croit a huit quand il en ferme sept aurait ete repere par
n'importe qui d'autre qui aurait cherche la neuvieme ligne du plafond annonce et ne l'aurait pas
trouvee. Ici, personne n'avait de raison de chercher un manque : le compte etait faux par exces
d'une unite, dans une direction ou rien n'alerte. **Une erreur d'addition qui sous-estime le travail
livre se voit d'elle-meme au premier controle contre un plafond annonce ; une erreur qui le
surestime ne se voit que si quelqu'un refait le calcul sans confiance dans le total qu'on lui donne.**
C'est exactement la methode que `camille-po` applique depuis plusieurs sprints - recompter les
dix-huit tableaux ligne a ligne, jamais reprendre un total - et c'est elle, et seulement elle, qui a
attrape ce defaut-la. Rien dans la structure du sprint ne l'aurait attrape autrement : les deux
instances avaient chacune une bonne raison de ne pas verifier le total de l'autre (*"deux paires
d'yeux sur des lots disjoints, ce sont deux paires d'yeux, pas un double controle"*, ecrit
d'avance par `nadia-prof` elle-meme), et aucune des deux n'avait mandat sur la matrice.

## La passe transverse a paye son prix des le premier essai, et elle a trouve un defaut que douze sprints de CI verte n'avaient pas vu

`REV-1` - la phrase composee par la mecanique autour de la bonne reponse, qui donnait *"C'est ce
n'est pas un etre vivant. Appuie sur ce n'est pas un etre vivant."* sur douze items de quatre
paquets - a traverse tous les sprints de contenu de ce depot depuis que la mecanique existe, sur des
lignes que `nadia-prof` a fermees elle-meme a plusieurs reprises. Le plus troublant n'est pas qu'il
existait, c'est **ou** il etait deja ecrit : `TexteAccentueDesGabaritsTest` portait, depuis des
sprints, l'entree `"Non, c'est"` avec le commentaire *"la reponse suit, posee par le modele"* - une
description exacte du defaut, en toutes lettres, dans un fichier de test que l'equipe relit a chaque
sprint. Personne, `theo-archi` compris au moment ou il l'a ecrit, ne l'avait lu comme un defaut.
Il n'est devenu lisible qu'une fois que la famille a recu un nom (`REV-1`), ce qui est exactement
l'argument que `theo-archi` donne lui-meme dans `ADR-0083` : *"c'est le seul argument dont je
dispose pour dire qu'un ADR sert a quelque chose avant sa regle."*

Et puis `theo-archi`, en corrigeant `REV-1`, a trouve `REV-2` en corrigeant le meme registre a un
ecran de distance - pas en le cherchant, en le rencontrant en train de reparer autre chose. Ce que
cela dit de la methode d'audit qui a precede : **jouer proprement, meme sur plusieurs sprints, ne
suffit pas a trouver ce genre de defaut.** `REV-1` n'apparait qu'a la troisieme erreur consecutive
sur une manche, et aucun test du depot ne jouait mal jusqu'au bout sur du contenu reel avant que
`nadia-prof` ne le fasse elle-meme dans sa passe transverse. Le garde-fou qui le mord maintenant
(`AucunTexteServiNEnchasseLaReponseDansUnePhraseTest`) joue les 1256 items reels jusqu'a leurs deux
ecrans terminaux ; rien de moins large ne l'aurait trouve. **La lecon n'est pas "il fallait un
garde-fou de plus tot" - personne n'avait de raison de chercher cette famille avant qu'elle ne soit
nommee -, elle est que la garantie qu'apporte une CI verte depuis douze sprints ne dit rien sur les
proprietes que personne n'a encore pense a nommer.**

## `yanis-qa` a trouve a s29 exactement ce qu'il avait trouve a s28, et la lecon n'a pas tenu

A s28, `yanis-qa` avait trouve qu'un paquet (`cp-qlm-corps-sens`) etendu de huit a quatorze items
n'avait recu aucune extension de son fichier de test de matiere. A s29, il a trouve que quatre
paquets entiers - les quatre paquets d'EMC, un sujet neuf pour ce depot - n'avaient recu **aucun**
fichier de test de matiere, ni a leur livraison ni a leur reprise apres refus. Meme defaut, un
sprint plus tard, sur quatre paquets au lieu d'un.

**Pourquoi la lecon n'a-t-elle pas tenu ?** Trois faits, aucun ne suffit seul mais leur reunion
explique ce qui s'est passe. D'abord, l'EMC est une matiere neuve avec des conditions inedites
(`EMC-1` a `EMC-10`), et la pression du calendrier de ce sprint portait sur *"sortir l'EMC en
premier, avant tout autre lot"* (demande explicite de `nadia-prof`, section 5.2 du planning) : la
priorite donnee au delai de livraison n'a laisse aucune place a la question du test qui l'accompagne.
Ensuite, les trois paquets d'EMC ont ete livres puis repris **par la meme developpeuse**, dans une
suite de commits ou chacun corrigeait le contenu du precedent sans qu'aucun d'eux n'ouvre le fichier
de test correspondant - la reprise a corrige le contenu refuse, pas l'absence structurelle que
personne n'avait signalee avant `yanis-qa`. Enfin, et c'est le point le plus genant : **le rapport
qualite de s28 nommait deja ce defaut precis avec son remede** ("un paquet dont le contenu change
recoit un fichier de test qui le mord"), et rien dans le planning de s29 ni dans les criteres
d'acceptation de `S29-4`/`S29-5` ne le reprenait comme une obligation explicite. La lecon a ete
ecrite, elle n'a pas ete transformee en critere verifiable avant que le lot ne soit livre. **Ce
n'est pas une question de volonte de `lea-dev`, qui a corrige les quatre fichiers en un seul commit
des que le defaut a ete signale** : c'est une question de savoir si une lecon tiree en review devient
un critere d'acceptation au planning suivant, ou si elle reste une phrase qu'on relit sans la
reappliquer. Ici, elle n'est pas devenue un critere.

## Quatre paquets sur cinq refuses au premier passage - un taux qui n'est evident dans aucun sens

Sur les cinq unites evaluees au premier passage de sprint (quatre paquets d'EMC, le rejeu de
`POS-R1`), quatre ont ete refusees. Ce n'est pas un mauvais signal en soi : chacun des quatre refus
porte un motif ecrit, precis, et verifiable - un classement binaire hors du sujet reel de la ligne,
une charge de lecture d'un autre niveau que son jumeau du meme jour, un mensonge confondu avec un
troisieme axe de reponse produisant deux reponses defendables, un correctif qui remplace un defaut
par un autre. Dans les quatre cas, la reprise a ete rapide (le jour meme pour trois des quatre) et
la seconde version a ete validee sans reserve nouvelle sur le fond qui avait motive le refus.

**Ce que ce taux dit, et ce qu'il ne dit pas.** Il ne dit pas que le travail livre etait mediocre :
l'EMC est une matiere neuve, sans aucun precedent dans ce depot, redigee par une developpeuse qui
n'a jamais eu de retour de `nadia-prof` sur ce type de contenu avant ce sprint - un taux de refus
eleve sur un premier contact avec une matiere neuve est attendu, pas anormal. Il ne dit pas non plus
que la marge de rejeu a bien fonctionne au sens ou elle aurait ete concue pour ce cas : la marge de
rejeu du sprint (1,0, jamais tiree avant ce sprint) n'a servi qu'a hauteur de 0,25, et les
corrections des quatre refus sont restees dans le plafond individuel de l'Instance B - la marge n'a
pas ete le levier qui a absorbe ce taux de refus, c'est le plafond individuel qui l'a fait, ce qui
etait deja son role avant que la marge n'existe. **Ce que ce taux dit surtout, c'est que livrer une
matiere neuve avec ses conditions inedites, en priorite et sans le filet d'une accumulation
anterieure sur laquelle s'appuyer, coute des refus - et que ce cout etait connu d'avance** (`nadia-
prof` l'avait ecrit : *"le lot d'EMC ... neuf conditions inedites qui n'ont jamais rencontre de
contenu reel"* etait le deuxieme mode de defaillance annonce, section 3 du planning).

## Trois incidents d'orchestration, et ils sont de moi

Le premier sprint a deux instances d'audit en parallele a demande une coordination de worktrees et
de commits que les sprints precedents, en serie, ne demandaient pas de la meme facon. Trois choses
ont mal tourne, et elles sont de mon fait, pas de celui des agents.

**Des mesures concurrentes sur un worktree partage ont donne des rouges intermittents et des
chiffres non fiables.** Quand deux mesures de reacteur tournent en meme temps sur le meme arbre de
travail, l'une peut lire un `target/` en cours de reconstruction par l'autre - le resultat n'est ni
un vrai rouge ni un vrai vert, c'est un artefact de collision. `yanis-qa` avait deja adopte de
lui-meme un worktree dedie depuis s28 (cree, mesure, detruit, sans jamais toucher au worktree
principal) : la bonne methode existait dans ce depot avant s29, et je ne l'ai pas imposee aux autres
agents qui mesurent en cours de sprint.

**Une consigne de mesure limitee au module a laisse passer un rouge que seul le reacteur complet
voyait.** Une mesure partielle avec `-pl` sans `-am` decrit un autre arbre que celui qu'on croit
mesurer - `CLAUDE.md` le dit depuis s14, avec son propre exemple chiffre - et j'ai laisse une
consigne de ce type circuler en cours de sprint sans la corriger a temps.

**J'ai deux fois embarque le travail en cours d'un agent dans le commit d'un autre.** C'est le
defaut le plus direct : un commit qui melange le travail de deux agents casse l'attribution par
tag (`[LEA]`, `[MARC]`, etc.) que la CI verifie et que ce depot utilise pour tracer qui a fait quoi,
et cela n'a rien a voir avec la qualite du travail lui-meme - c'est une erreur de sequencement de ma
part, dans le geste meme qui consiste a committer.

**Ce que j'en tire, comme point de process et non comme excuse.** Les trois incidents partagent une
cause : la coordination de deux flux de travail paralleles n'a pas ete traitee comme une discipline
explicite valable pour tous les agents, alors qu'un agent l'avait deja inventee de son cote. Pour
s30, si un sprint reprend cette forme, la regle de worktree dedie doit etre generalisee a l'ouverture
du planning plutot que laissee a l'initiative de chacun.

## Le cout du sprint a ete ecrit trois fois avant d'etre exact, et rien ne reliait le chiffre a ses composantes

Le chiffre de cout du sprint a change trois fois dans les memes documents : 9,25 a la cloture
(`b93ef31`), inchange dans deux relectures intermediaires, puis corrige a 9,50 seulement apres que
la reverification de `nadia-prof` sur `REV-1` a ajoute 0,25 - une reverification legitime, deja
chiffree d'avance, mais survenue apres que le total avait deja ete ecrit et diffuse dans trois
fichiers (`DEC-0077`, son index, la synthese de la matrice). Le relecteur a trouve l'ecart et l'a
signale plutot que de le corriger de sa propre initiative (une figure d'arbitrage n'est pas son
mandat) ; `camille-po` l'a corrige dans le commit de cloture.

**Ce que cet episode montre, au-dela du chiffre lui-meme** : aucun des trois documents qui portaient
9,25 puis 9,50 ne relie ce total a la liste des postes qui le composent d'une facon qu'un lecteur
puisse verifier sans retrouver les deux releves de verdicts et refaire l'addition lui-meme.
`DEC-0077` le fait le mieux (Instance A 4,25 + Instance B 5,25), mais seulement dans son dernier
amendement - les versions precedentes du meme document portaient deja un total sans le decomposer
aussi explicitement. Un cout de sprint qui n'est pas ecrit a cote de sa decomposition est un chiffre
qui peut se perimer sans que personne ne le remarque immediatement : c'est exactement ce qui s'est
passe ici, et ce n'est pas different, dans sa nature, de l'ecart de couture sur le compte de lignes
fermees traite plus haut - un total que personne ne recalcule contre ses composantes au moment ou
une de ces composantes change.

## Amendements a `squad/README.md`

Aucun amendement n'est propose ce sprint. La regle posee a la retro de s27 (aucun agent ne touche au
worktree ni a la branche d'un autre) tient toujours comme principe ; ce que ce sprint montre, c'est
qu'elle doit s'etendre a une discipline de mesure commune quand plusieurs agents travaillent en
parallele sur des lots disjoints, ce qui n'avait jamais ete le cas avant s29. Cette extension n'est
pas ecrite ici comme un amendement forme, faute d'un texte de regle stabilise a proposer : elle est
notee pour le planning de s30.

## Decisions prises (creees ou modifiees ce sprint)

| Reference | Titre | Type | Statut |
|---|---|---|---|
| `DEC-0075` | Corpus de reproductions d'oeuvres et droits d'usage | Produit | Amendee : statut passe a Acceptee (option B) par `DEC-0077` |
| `DEC-0077` | Strategie de s29 : couverture maximale, marge de rejeu posee, corpus du domaine public tranche | Produit | Acceptee, amendee trois fois (perimetre et parallelisation le 2026-09-05 ; cout reel et recompte de la matrice le 2026-09-06 ; cout corrige a 9,50 le 2026-09-06) |
| `DEC-0078` | `CONS-1` - la consigne generique cesse d'etre reprise par une entree neuve de catalogue | Produit | Acceptee |
| `ADR-0073` | Un item declare l'illustration qu'il montre par un identifiant, la preuve change de source | Architecture | Amendee (troisieme population d'images, `static/oeuvres/`) |
| `ADR-0082` | Une reproduction d'oeuvre est une troisieme population d'images, sa preuve est double | Architecture | Accepte |
| `ADR-0083` | Aucune valeur de contenu n'est enchassee dans une phrase de la mecanique | Architecture | Accepte |
