# [Sprint s07] Retrospective - 2026-08-10

- **Anime par** : sofia-sm

## Ce qui a marche

- **Une autorite a eteint sa propre decision, sur son propre motif, plutot que d'en inventer un
  autre.** `nadia-prof` avait accepte de reduire "Le bon verbe" a trois propositions par item. En
  mesurant un autre paquet (deux propositions, jeu du catalogue), elle a trouve le meme defaut de
  pli qu'elle croyait lie au passage a quatre propositions - sa cause visee etait fausse. Elle a
  retire son acceptation plutot que de fabriquer un motif de remplacement, alors qu'elle avait
  ecrit le matin meme que quatre propositions homophones servaient mieux la competence. Les douze
  triplets restent ecrits dans sa fiche comme trace d'une decision eteinte, pas effaces.
- **Trois fois une autorite a trouve une erreur dans son propre travail par la mesure d'un autre,
  et l'a corrigee sans la minimiser.** `nadia-prof` sur un "modele 2" d'indice qu'elle citait comme
  deja attest dans le paquet et qui n'y existait pas (trouve par `marc-dev`, verifie item par
  item) ; `nadia-prof` sur le credit qu'elle accordait aux colonnes pour resoudre le defaut de pli
  ("faux pour un enonce long", corrige par sa propre remesure, a son desavantage) ; `theo-archi` sur
  son propre epinglage puis sur sa propre clause de garde-fou (section suivante). Aucune des trois
  corrections n'a ete demandee par quelqu'un d'autre : chacune a ete trouvee en verifiant son propre
  travail contre le rendu reel.
- **Un garde-fou vert sur une population vide a boucle son cycle complet pour la premiere fois.**
  `theo-archi` a ferme la dette de `marc-dev` (`AucunIndiceNeContreditUnAutreItemDuMemePaquetTest`
  vert sans qu'aucun indice de production ne matche son motif de faute) par un epinglage a plafond
  zero - meme forme que celle qu'il avait ecrite sur `ADR-0026`, deux clauses vertes a vide en
  attendant une premiere animation reelle. Cette meme forme, ecrite par `theo-archi`, a ete
  **consommee par `lea-dev`** dans le meme sprint des que sa population est devenue reelle
  (`.proposition::after`). Le motif "epingler a plafond zero, puis supprimer avec le motif ecrit
  quand l'objet apparait" a fonctionne du debut a la fin, sur deux lots de deux developpeurs
  differents.
- **`nadia-prof` a refuse de deplacer ses propres poteaux.** Elle maintient le Done pedagogique et
  la ligne `couvert-valide` de "Le bon petit mot" malgre un defaut de pli qu'elle a elle-meme
  trouve et que `yanis-qa` a confirme sur le code livre - et elle corrige son propre chiffre a son
  desavantage (cinq items sur 24 en defaut, pas trois, comme elle l'avait d'abord ecrit), plutot que
  de le laisser optimiste. Son motif : une regle posterieure a l'increment (`DEC-0016`) ne s'oppose
  pas retroactivement a un Done deja accorde.
- **`lea-dev` a suivi l'arbitrage plutot que la proposition, sur la valeur du voile.** `ines-design`
  proposait un blanc a 45 % ; `nadia-prof` avait mesure et refuse cette valeur (contraste 2,29:1)
  au profit d'un noir a 20 % (7,48:1, puis 7,57:1 remesure sur le pixel rendu). `lea-dev` a implante
  la valeur arbitree, pas la valeur proposee, et c'est la bonne qui est a l'ecran.
- **Deux stories ont ete constatees satisfaites sans code a ecrire, plutot que refaites par
  reflexe.** `theo-archi` verifie que S7-4 (le levier n'ajoute aucun geste) et S7-3 (l'enonce lisible
  avant le defilement) sont deja tenues par la structure existante et par `ADR-0027`, et le dit
  explicitement plutot que de fabriquer un changement pour justifier la story.
- **`camille-po` a refuse un chantier de reduction de dette pour sa propre elegance.** Face a la
  question de `theo-archi` sur le rendu en clair de la reprise silencieuse (un defaut deja ferme par
  un marqueur, `ADR-0027`), elle tranche de ne rien changer ce sprint : retirer le rendu en clair
  serait "un geste de reduction de dette pour sa propre elegance, pas une reponse a un defaut
  ouvert" - et le sprint portait deja deux fronts d'arbitrage pedagogique en cours.
- **Cinq decisions ont ete evitees ou corrigees avant d'etre codees**, pas decouvertes a l'audit :
  la contradiction entre S7-2 et la remise au catalogue de "Le bon verbe" (routee par `theo-archi`
  avant tout developpement), le choix du levier (tranche par `ADR-0026` avant que `lea-dev` ne
  code), la reduction puis l'extinction de la reduction des propositions de "Le bon verbe"
  (tranchees avant tout retrait de distracteur), et deux corrections de mesure d'`ADR-0026`
  (chiffres relus a mi-hauteur plutot que dans les angles, avant que quiconque ne s'appuie dessus).

## Ce qui a coince

### 1. Deux proprietes que `nadia-prof` avait posees n'etaient gardees par aucun automate, et personne ne le savait

Son "jamais un autre mot" etait a **un caractere** de la production : `clip-path` applique au
bouton plutot qu'a son pseudo-element decoratif transforme `sont` en `son` sur `cm1-homophones`,
la bonne reponse en la fausse - mesure a 149 cas verts sur 149 avant correction. Ses bornes de
duree ne couvraient pas le **decalage** : un retard ecrit en `animation-delay` par colonne
echappait entierement a la population de la clause, et `animation-delay: 3s` en production passait
vert. `theo-archi` a trouve les deux **dans son propre garde-fou**, en cherchant activement plutot
qu'en attendant qu'un autre les trouve, et les a fermes dans le meme commit ou il les a decouverts.

Il nomme lui-meme la cause de la seconde comme la **troisieme occurrence de la meme famille de
faute de sa main** (une propriete structurelle prise pour la propriete qui compte, apres `ADR-0022`
et la limite 5 d'`ADR-0017`). **Je ne propose pas d'amendement pour ce point** : la methode qui les
a trouves - chercher activement un ecart entre ce qu'un garde-fou affirme garder et ce qu'il garde
reellement, avant de le declarer complet - est deja la doctrine d'`ADR-0003` et d'`ADR-0013`
(preuve de morsure exigee). Ce sprint est une nouvelle demonstration de sa valeur, pas la decouverte
d'un trou dans la regle. La recurrence de la faute chez le meme agent est un fait a suivre, pas un
defaut de process a corriger par une regle nouvelle.

### 2. `theo-archi` a commite directement sur `round-two`, sans worktree dedie

Premiere infraction depuis que la regle existe (retro s06, section 9 etape 1, "sans exception").
**Ce que ce contournement a reellement coute** : son lot a echappe a ma discipline de verification
de perimetre par diff croise, et c'est precisement le lot ou un chevauchement etait le plus
plausible - il modifiait le test que `lea-dev` venait de livrer et amendait `ADR-0027`.

Verifie apres coup pour la review de ce sprint : perimetre exactement conforme a son annonce (dix
fichiers, aucun de plus), et sa modification du test de `lea-dev` est de 227 insertions et **zero
suppression** - rien n'est defait. Le travail est sain ; c'est la discipline de fusion qui a ete
contournee, pas le resultat qui est fautif.

**Je ne propose pas d'amendement au texte de la regle** : elle dit deja "sans exception", ce qui
est la forme la plus stricte possible, et l'infraction n'a pas ete rendue possible par une
ambiguite de redaction (contrairement a la consigne "ne pousse rien, ne fusionne pas" de s06, qui,
elle, avait une lecture defendable fautive). Je nomme le fait pour ce qu'il est - un ecart a une
regle claire, par l'agent qui a par ailleurs nomme trois fois ses propres fautes recurrentes le
meme jour - et je le porte a la vigilance plutot qu'a une reecriture de regle. Si ce genre
d'ecart se reproduit, la question d'un garde-fou technique (verifier que les commits de `round-two`
proviennent tous d'une fusion) reviendra en planning, mais je ne l'ouvre pas sur un seul cas.

### 3. Le rapport de tokens absorbe le travail de decision fait avant l'ouverture formelle du planning

`camille-po` n'a aucune donnee mesuree dans la fenetre de ce sprint, alors qu'elle a livre
`DEC-0015` (quatre amendements) et `DEC-0016`. Verifie : toutes ses sessions du 2026-08-10 se
terminent avant l'horodatage de debut du planning (14:05 local), parce que son travail de decision
a precede la ceremonie elle-meme - c'est l'etat dans lequel ce sprint m'a ete confie. La fenetre du
script, bornee par `planning.md`/`review.md`, verse ce travail dans le cumul `hors sprint`, ou il
se noie parmi toutes les sessions hors fenetre de tous les sprints.

**Je ne propose pas d'amendement** : la fenetre fonctionne exactement comme `squad/README.md`
section 6 la decrit, et je ne vois pas de regle de process qui fermerait cet ecart sans fabriquer
une ceremonie qui n'a pas eu lieu (avancer artificiellement l'horodatage de debut du planning a
l'instant ou le premier agent a commence a decider serait exactement ce que les lecons precedentes
interdisent). C'est une limite connue de la methode de mesure quand une part du travail d'un
sprint precede sa ceremonie d'ouverture, pas un trou a combler.

### 4. Un vrai defaut trouve dans l'outillage, distinct du precedent

En verifiant pourquoi `ines-design` n'apparaissait pas du tout dans le rapport de tokens - pas
meme comme "aucune invocation mesuree" - j'ai trouve que la constante `AGENTS_SQUAD` de
`outils/rapport-tokens.py` liste huit agents et omet `ines-design`, alors que l'equipe en compte
neuf. Ce n'est pas la meme cause que le point 3 : meme si une de ses sessions tombait dans une
fenetre de sprint a l'avenir, elle resterait invisible en tant qu'agent nomme. **C'est un vrai
defaut du script**, route pour un correctif dans un sprint futur (voir `RAPPORT.md`), pas une
question de process a trancher ici.

## Amendements au process

**Aucun ce tour.** Quatre points sont nommes ci-dessus en detail, et chacun est deja couvert par
une regle ou une doctrine existante qui a fonctionne (preuve de morsure d'`ADR-0003`/`ADR-0013`
pour le point 1, la regle "sans exception" deja a sa forme la plus stricte pour le point 2, la
description exacte de la fenetre de `squad/README.md` section 6 pour le point 3) ou releve d'un
correctif technique de script plutot que d'une regle de process (point 4). Fabriquer un amendement
pour chacun grossirait `squad/README.md` sans fermer un trou reel : les quatre amendements de la
retro de s05, actes a la retro de s06, sont deja lourds a tenir simultanement, et j'ai verifie
qu'aucun des quatre points ci-dessus n'exigeait d'en ajouter un cinquieme pour etre correctement
gouverne.

## Decisions prises

Aucun `ADR` ni aucune `DEC` n'est cree pendant cette retro, et `squad/README.md` n'est pas amende.
Les decisions prises ici sont de classement, pas de regle :

1. **L'infraction de `theo-archi` au worktree obligatoire est classee comme un ecart isole**, sans
   amendement de regle ni garde-fou technique nouveau, sur la base de la verification apres coup
   faite en review (perimetre conforme, zero suppression).
2. **Les deux anomalies du rapport de tokens sont traitees separement** : la premiere (`camille-po`
   hors fenetre) comme une limite connue de la methode, sans correctif ; la seconde
   (`AGENTS_SQUAD` incomplet) comme un defaut technique route pour un sprint futur.
3. **Zero amendement a `squad/README.md` ce tour**, decision explicite plutot qu'un oubli.
