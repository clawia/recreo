# Retro du sprint 20 - mot cible designe et conception de la saisie

## Ce qui a marche

- **Un deuxieme refus pedagogique consecutif se resout dans le meme sprint.** Apres
  `cm1-accord-sujet-verbe` en s19, `cm1-reperes` est `REFUSE` (0 sur 16 au pli, `REP-4` non tenue)
  puis corrige par `lea-dev` sur le motif exact du refus, puis reaudite `VALIDE SOUS RESERVE` sur
  le paquet reellement servi. Le mecanisme continue de fonctionner sans bloquer le sprint : la
  ligne reste `en cours` le temps du correctif, pas de contournement.
- **`nadia-prof` a verifie sa propre barre au lieu de s'appuyer dessus.** Sa phrase de refus
  ("c'est la barre que j'applique depuis s17") s'est averee fausse pour l'indice une fois
  confrontee a une mesure du catalogue entier (8 jeux sur 17, 43 items sur 157 ne la tiennent pas
  non plus, dont deux plus bas que `cm1-reperes` corrige). Elle a mesure avant de maintenir un
  refus, ecrit qu'elle avait tort, et leve la partie de son propre refus qui deplacait le poteau.
- **La discipline de preuve par mutation sur materiau reel continue de tenir**, cinquieme sprint
  consecutif ou elle est appliquee sans exception : `yanis-qa` mute deux fois sur du contenu de
  production (`cp-nature-mot.yaml`, `cm1-reperes.yaml`), jamais sur une fixture ecrite pour
  l'occasion.
- **Un faux vert de conduite de mesure a ete instruit deux fois, independamment, par les deux
  agents concernes.** `theo-archi` decouvre sa propre commande fautive (`BUILD SUCCESS` sur zero
  test, motif sans le suffixe `Test`) et l'instruit ; `yanis-qa` la reproduit sans le croire sur
  parole, dans les deux sens (fautive et corrigee). Aucun des deux ne s'est arrete a l'aveu de
  l'autre.
- **Six lots fusionnes sans aucun conflit**, malgre un sequencement strict a quatre gestes sur
  `S20-1` (contrat, forme, reaudition, retour catalogue) et un arbitrage de perimetre en cours de
  route (la bande sort de la cible) : le sequencement pose au planning a tenu jusqu'au bout.

## Ce qui a coince

**Un faux vert de mesure, deja instruit mais qui aurait pu passer inapercu s'il n'avait pas ete
reproduit.** `theo-archi` a ecrit et cite une commande de mesure ArchUnit ciblee
(`-Dtest='com.recreo.archi.*'`, sans le suffixe `Test`) qui rendait `BUILD SUCCESS` en executant
zero test - `-Dsurefire.failIfNoSpecifiedTests=false`, necessaire a toute mesure ciblee legitime,
convertit silencieusement "zero test selectionne" en succes. Ni ArchUnit ni aucun plugin Maven
n'arrete ce cas : c'est une regle de lecture du journal (le compte de tests se lit dans le bloc
`Results:` avant que le vert soit cite), pas un defaut de code. `theo-archi` et `yanis-qa` l'ont
tous deux ecrite dans leurs propres documents, avec la meme formulation.

**Une barre pedagogique s'est presentee comme deja etablie sans l'avoir jamais ete confrontee au
reste du catalogue.** `nadia-prof` avait refuse `cm1-reperes` en partie sur le motif que le verdict
et l'indice doivent etre entierement au-dessus du pli - une regle qu'elle n'appliquait en realite a
aucun autre jeu servi (8 jeux sur 17 la contredisent deja). Elle l'a trouve elle-meme, pas moi ni un
autre agent, en mesurant plutot qu'en supposant.

**Une formulation prudente a ferme un levier qu'elle ne voulait pas fermer.** `nadia-prof` avait
ecrit, pour `cm1-reperes` : *"je ne demande pas de toucher aux seize phrases"*. `lea-dev` l'a lue
comme une interdiction et n'a pas touche au levier qui aurait regle `REP-9` (le verdict coupe en
deux sur les items a enonce de deux lignes). `nadia-prof` le releve elle-meme : *"je ne demandais
pas de les toucher ; je ne l'interdisais pas."* La lecture de `lea-dev` est defendable ; la
formulation en est la cause.

**Un en-tete de fiche de conformite est reste faux trois verdicts apres avoir cesse d'etre vrai.**
`FICHE-bandit-manchot-nature-mot-cp.md` portait "Done pedagogique non encore prononce" du
2026-08-24 au 2026-08-27, alors que le verdict `VALIDE` datait du 2026-08-25. Meme famille que la
derive documentaire deja rencontree sur `ADR-0062` et sa ligne d'index (une phrase qui decrit un
etat de l'arbre se perime avec l'arbre) - sauf que celle-ci vit dans le document le plus consulte
d'un jeu, son propre resume en tete de fiche.

**Deux agents ont invente independamment la meme paire de labels courts pour des objets
differents.** `camille-po` nomme `REP-7`/`REP-8` le 2026-08-25 (le pli, le cinquieme libelle) ;
`nadia-prof` reutilise `REP-7`/`REP-8` le 2026-08-27 pour deux reserves neuves et sans rapport
(prediction sans lecture, indice hors de vue). `camille-po` a signale la collision des qu'elle l'a
decouverte, sans renommer ses propres labels pour ne pas effacer la trace du correctif qu'ils ont
motive - mais la disambiguation n'a pas couvert `rapport-qualite.md`, ecrit avant que la seconde
paire n'existe. Ferme a cette review (tableau des obstacles de `review.md`, ligne 14) par un renvoi
que j'ai ajoute moi-meme, faute d'un agent mieux place pour le faire sans reecrire le constat d'un
autre.

## Amendements proposes a `squad/README.md`

Je ne les ecris pas moi-meme dans le fichier : je les propose, avec qui je pense devoir trancher
chacun, plutot que de les acter seule.

1. **Le compte de tests d'une mesure ciblee se lit dans le bloc `Results:` avant que le vert soit
   cite, jamais l'inverse** - a ajouter section 4, a cote de la methode de comptage deja
   obligatoire. Deja ecrit dans les memes termes par `theo-archi` (`confirmation-archunit.md`) et
   par `yanis-qa` (`rapport-qualite.md`, section 6), qui l'ont chacun instruit independamment sur
   le meme fait. Je propose de l'acter tel quel : **a confirmer par `theo-archi` et `yanis-qa`**,
   puisque la formulation est deja la leur et que je n'ai rien a y ajouter de mon cru.
2. **Une reserve pedagogique invoquee pour refuser un increment est confrontee au reste du
   catalogue avant d'etre presentee comme une barre deja en vigueur.** `nadia-prof` l'a fait
   elle-meme a la cloture de s20, apres coup ; je propose que ce balayage devienne la pratique
   normale avant qu'un refus ne cite une barre comme etablie. **A trancher par `nadia-prof`** :
   c'est sa propre discipline d'audit qui est en jeu, et le cout d'un balayage systematique du
   catalogue a chaque refus lui revient d'evaluer, pas a moi.
3. **Une consigne pedagogique qui borne un perimetre distingue explicitement "je ne demande pas"
   de "il est interdit de".** Trouve sur `cm1-reperes` : une phrase prudente de `nadia-prof` a
   ferme un levier qu'elle ne voulait pas fermer. **A trancher par `nadia-prof`**, sur la
   formulation de ses propres arbitrages, avec `relecteur-pedagogique` en second regard si elle le
   souhaite sur la forme.
4. **L'en-tete "Statut global" d'une fiche de conformite est mis a jour dans le meme commit que
   tout verdict qui le rend faux.** Trouve sur `FICHE-bandit-manchot-nature-mot-cp.md`, perime
   trois verdicts. **A trancher par `nadia-prof`**, qui tient ces fiches, avec `sofia-sm` en
   verification a chaque retour de jeu au catalogue puisque c'est deja le geste qu'elle execute a
   ce moment-la.

## Ce que j'ai verifie avant de clore cette retro

Le rapport de tokens (`RAPPORT-tokens.md`) est genere apres cette retro, a la meme cloture que
`RAPPORT.md` : je ne l'anticipe pas ici et je ne recopie aucun chiffre par avance.
