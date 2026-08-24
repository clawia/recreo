# S19 - fusion des lots 7 a 9 sur round-two

Suite de `fusion-lots-1-a-6.md`. Toujours pas une ceremonie de review : ce fichier cloture le
developpement du sprint (les trois stories restantes sont livrees), mais la porte technique, la
relecture, l'audit pedagogique et la review elle-meme restent a faire.

## Verification de provenance

Les trois lots verifies par `squad/outils/sofia-sm/verifier-provenance-lots.sh` : tete annoncee
confirmee, ancetre commun avec `round-two` trouve pour les trois.

- `s19-nadia` (`8e2005b`) : c'est exactement le commit que j'avais moi-meme repere comme non
  fusionne a l'issue de la passe precedente. Remis explicitement cette fois.
- `s19-marc` (`087b078`) : resynchronise par `merge --ff-only` sur `7be73a2` (round-two juste
  apres la fusion du sixieme lot, ancetre confirme de la tete actuelle) avant travail. Un seul
  commit neuf par-dessus.
- `s19-lea-2` (`99d0a75`) : branche neuve, l'ancien worktree `s19-lea` a ete retire. Meme point de
  fork que `s19-marc` (`7be73a2`), un seul commit neuf par-dessus.

Aucun chemin absolu ni tiret cadratin trouve dans les lignes ajoutees par les trois lots (recherche
sur le diff complet contre `round-two`, ou contre la tete deja fusionnee pour `s19-nadia`).

## Ordre de fusion et resultat

| Ordre | Branche | Commit fusionne | Merge sur round-two | Conflit |
|---|---|---|---|---|
| 7 | `s19-nadia` | `8e2005b` | `d65c52f` | aucun |
| 8 | `s19-marc` | `087b078` | `179d55f` | aucun |
| 9 | `s19-lea-2` | `99d0a75` | `a500fea` | oui, prevu |

## Resolution du conflit (marc-dev / lea-dev)

Le recoupement annonce s'est confirme sur les trois fichiers habituels :

- `catalogue.yaml` et `TexteAccentueExposeParLePortEntrantTest.java` se sont auto-fusionnes sans
  intervention (les deux apports sont presents, verifie apres coup).
- `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.java` : conflit reel, resolu en
  conservant les deux entrees de `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` (`cm1-antonymes` puis
  `cp-nature-mot`), neuf paires au total dans le `Map.of`, toujours sous la limite de dix.

Aucun apport ecarte.

## Porte technique

`mvn clean verify` sur le reacteur complet, six modules, `BUILD SUCCESS`.

**Compte de tests, methode de reference, mesure par moi sur l'arbre fusionne : 657.**

Base avant cette passe (fin de `fusion-lots-1-a-6.md`, verifiee dans ce meme fichier) : **643**.
Deux lots ajoutent du code de test :

| Lot | Delta annonce | Verifie par moi (methode) |
|---|---|---|
| `s19-marc` | +5 | 5 methodes `@Test` dans `PaquetCm1AntonymesTest` (fichier neuf) |
| `s19-lea-2` | +9 | 9 methodes `@Test` dans `PaquetCpNatureMotTest` (fichier neuf) |

643 + 5 + 9 = 657. L'arithmetique de la coordinatrice tenait ; verifiee, pas relayee.

## Deux points verses au dossier, sans trancher a leur place

**Defaut d'encodage sur un espace insecable, invisible a la porte technique.** `marc-dev` a
trouve un heredoc bash ayant ecrit six caracteres ASCII litteraux la ou un espace insecable etait
voulu, sans qu'aucun test ni build ne le signale - decouvert par comparaison octet par octet
contre `cm1-synonymes.yaml`. `lea-dev` a verifie ses propres espaces insecables par code point.
Verification independante faite sur l'arbre fusionne : `cm1-antonymes.yaml` porte 34 occurrences
de l'espace insecable reel (U+00A0), `cp-nature-mot.yaml` en porte 32, du meme ordre que les 32 de
`cm1-synonymes.yaml` pris comme reference ; aucun motif de l'artefact ASCII litteral trouve dans
les deux fichiers neufs. **Je route a `theo-archi`** : deux agents ont converge indépendamment
vers la meme methode de detection (comparaison d'octets/code points) pour un defaut qu'aucune
porte existante ne voit ; c'est le signal d'un garde-fou manquant, pas seulement une vigilance
individuelle a saluer. Matiere de retro dans tous les cas.

**Lecture large d'une condition de `nadia-prof`, signalee plutot que taisee.** `lea-dev` indique
avoir applique une lecture large d'une consigne ambigue sur `cp-nature-mot` (tous les indices en
semantique pur, y compris ou la regle n'exigeait peut-etre pas ce choix). Je ne tranche pas :
c'est a `nadia-prof` de juger a l'audit, avec cette declaration explicite comme piece du dossier.
Je la nomme ici pour qu'elle n'ait pas a la deviner en jouant l'increment.

## Etat de round-two apres cette fusion

`round-two` local est a `a500fea`, non pousse (`origin/round-two` reste a `c3d3d47`). Neuf lots
fusionnes depuis le debut du sprint, build vert, 657 tests. Cote developpement, le sprint est
complet : `S19-1` a `S19-4` sont livrees (`S19-5` l'etait deja), `S19-6a`/`S19-6b` restent
renvoyees a s20 par `DEC-0043`. Reste la porte technique de `yanis-qa`, la relecture, l'audit
pedagogique de `nadia-prof` et la cloture.
