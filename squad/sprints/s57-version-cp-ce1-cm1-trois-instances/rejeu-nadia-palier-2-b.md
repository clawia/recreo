# s57, palier 2, instance B - rejeu de `nadia-prof` : p2-5, `S57-OBS-SCI-N1-N2`

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : 2026-10-16, 15:47 (UTC+02:00), lu par `date`.
- **Arbre** : branche `s57-nadia-p2-b`, worktree dedie, partie de `round-two` a `e8d14ddb`, qui contient `69dd7631` et sa
  fusion `9cbf3571` (`git merge-base --is-ancestor`, sortie 0 pour les deux).
- **Jeu** : `bandit-manchot-sciences-extraire-information-cm1`, `SCI-CM1-20`.
- **Audit joue** : jar construit dans ce worktree et joue a l'ecran (captures regardees, pas seulement le DOM).

## Verdict

| Recommandation | Prise en compte | Motif |
|---|---|---|
| `N1` (enonce de l'item 12) | **oui** | Servi *"D'après ce texte, que permet ce transport de pollen à de nombreuses plantes ?"*, egal a mon texte (`reponse-nadia-q-palier-1.md` 4.1). L'enfant ne peut plus lire la question d'abord comme une affirmation. Reponse et distracteurs inchanges |
| `N2` (indices des items 9, 11, 12) | **oui** | Les trois mots cites sont entre chevrons, egaux a mes textes (4.2). A l'ecran, le chevron ne se separe jamais du mot au retour a la ligne. Chaque mot cite figure une fois dans la phrase designee |

Le Done pedagogique du jeu reste **VALIDE** ; `SCI-CM1-20` reste `couvert-valide`, non rouverte ; aucune ligne ne bouge.
Motif complet, criteres un par un et ce que j'ai vu a l'ecran :
`squad/conformite/FICHE-bandit-manchot-sciences-extraire-information-cm1.md`, rejeu du 2026-10-16.

## Ce que j'ai lance

- `bash outils/mvn-jdk21.sh -B clean package -DskipTests` : `BUILD SUCCESS`. Jar sur le port 18513 (libre avant), Java
  21.0.10, arrete par son PID. Chrome sans interface, port de debogage 19213, 744 x 551, arrete par son PID.
- Deux parties de dix manches ; les 12 items vus. Items 9, 11 et 12 : deux erreurs puis la bonne reponse.
- **Done technique non relance par moi** : je m'appuie sur `rapport-marc-dev-palier-2.md`, section 4 (jobs `build`,
  `chemins`, `prefixes`, `outils` nommes avec leur sortie pour p2-5).

## Observation, sans reserve ouverte

Apres une erreur, a 744 x 551, l'indice finit sous le pli sur l'item 12 (690 px) et sur l'item 9 (616 ou 690 px selon
l'ordre des propositions) ; au-dessus sur l'item 11 (542 px). Remettre dans la page les textes d'avant `69dd7631` donne les
memes cotes : **`N1` et `N2` n'en sont pas la cause**. Je n'ouvre pas de reserve : trois items sur douze, une seule largeur,
et la barre de ce rejeu est `N1` et `N2`. Detail dans la fiche.

## Ce que je n'ai pas joue

- la revelation (troisieme essai faux) ;
- les largeurs autres que 744 ;
- l'orthographe et la ponctuation : au `relecteur-pedagogique` (`releve-relecteur-palier-2.md` 3.1, correct) ;
- `RDO-CP-R3` : non engage, non joue.

## Ecart dans la commande recue

La commande de ce rejeu renvoyait a `reponse-nadia-q8.md` pour *"mes textes N1 et N3"*. Ce fichier porte d'autres reperes
`N1` a `N3`, sur `Q-T8` et `bandit-manchot-additions-cp`. Les textes de cette story sont dans `reponse-nadia-q-palier-1.md`,
4.1 et 4.2 ; c'est contre eux que j'ai compare.

## Ce dont je reste la seule garde

Un mot cite dans un indice ou un enonce de ce jeu est entre chevrons et figure dans la phrase designee : aucun test
n'epingle ces textes (criteres 4 et 5 de la story). La position de `.indice` : aucun test ne la tient.

**Tarif** : rejeu 0,25 (p2-5, instance B). Aucun identifiant de condition ou de reserve ecrit ni alloue.

**Signe** : `nadia-prof`, s57, palier 2, instance B, 2026-10-16.
