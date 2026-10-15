# [Sprint s57] Tache de `marc-dev` : garde du distracteur egal au verbe cite (`F1` de `yanis-qa`)

- **Auteure** : `sofia-sm`, casquette Scrum Master, 2026-10-15 10:40 (UTC+02:00), lu par `date`. Arbre : `round-two` a `c468c8dd`.
- **Ce fichier est une consigne, pas du code.** Il ne modifie ni test ni paquet. Il affecte une tache de capacite a `marc-dev` (reaffectation entre les developpeurs : mon poste) et ecrit des criteres que `camille-po` reprend en story.
- **Source** : `releve-yanis-palier-1.md`, `F1` et section 3 (`yanis-qa` n'ecrit pas le code : ce palier lui interdit de modifier du code et la tache va a `marc-dev` par moi, section 3.3 du releve).

## 1. Le constat, relu sur le code

`PaquetCe1VerbeSujetTest.verifierLaPlaceDuSujetParRapportAuVerbe` (lignes 291 a 316) compte un distracteur `avant` par `rang < rangDuVerbe` (ligne 282, par `distracteursAvantLeVerbe`) et `apres` par `rang > rangDuVerbe` (ligne 310). Un distracteur au rang du verbe cite n'est ni l'un ni l'autre : il passe.

- **Avant le poste 3**, l'ancienne propriete 8 le refusait par effet de bord (rang non strictement superieur). **Apres** (`7dc40fb0`, fusionne `65d00d8d`), plus rien ne le refuse. Mutation `P5` de `yanis-qa` : sur l'item *"Elles rangent les jouets dans le sac."*, distracteurs `rangent` et `le sac` : **28 tests, 0 echec** sur `f95736b5` ; meme mutation sur `14d6cf47` : 22 tests, **1 echec**, `vsjCe12LeSujetEstAvantLeVerbeCiteEtLesDeuxDistracteursApres`.
- **Ce que l'amendement de la propriete 8 ne dit pas** : rien du rang egal. **Ce que la condition ecrite dit** : `VSJ-CE1-3`, `ARBITRAGE-PREALABLE-s56-instance-a-francais-ce1.md`, ligne 82, *(a) le groupe apres le verbe et (b) un autre nom de la phrase* : le verbe n'est ni l'un ni l'autre. Refuser le rang egal applique donc une condition deja ecrite ; ce n'est pas une regle neuve.
- **Portee** : aucun item actuel ne declenche le cas (15 items de sujet sur 24, aucun distracteur egal au verbe cite, script de poste de `yanis-qa`). C'est une garde qui n'existe plus, pas un defaut de contenu.

## 2. Affectation

| Champ | Valeur |
|---|---|
| Developpeur | **`marc-dev`**, nominatif : auteur du poste 3, qui a ecrit l'assertion affaiblie |
| Prefixe de commit | `[MARC]` |
| Branche, worktree | `s57-marc-vsj-garde-verbe`, `../recreo-s57-marc-vsj-garde-verbe`, issue du sommet de `round-two` fusionne ; `git log -1 --format=%h` dans son premier message, `bash outils/verifier-lignee.sh` sort 0 |
| Fichiers attendus | `adapters/adapter-content/src/test/java/com/recreo/adapter/content/PaquetCe1VerbeSujetTest.java` et `adapters/adapter-content/src/test/resources/contreexemple/PaquetCe1VerbeSujetTest/contenu/ce1-verbe-sujet-fautif.yaml`. Rien d'autre |
| Isolation | aucun jar, aucun port ; repertoire de poste `s57-p1-marc-dev-a` si un fichier hors depot est utile |
| Prix | aucun prix ecrit, hors `PAR` : un test, aucun texte lu par l'enfant ne change, aucun audit de `nadia-prof`. **Si `marc-dev` doit toucher un item du paquet, il s'arrete** et route a `nadia-prof` par moi |
| Rapport | `rapport-marc-dev-f1.md` dans ce dossier (le fichier `rapport-marc-dev.md` du poste 7 est sur une autre branche : un nom distinct evite un conflit) |

Ordre : apres le poste 7, qui est livre sur `s57-marc-fra-ce1-04` et attend sa fusion ; les deux ne touchent pas les memes fichiers. `lea-dev` (poste 8) ne touche pas ceux-ci non plus.

## 3. Criteres, proposes a `camille-po` pour la story

Aucune story du backlog ne porte `F1` : `grep -n "egal au verbe\|distracteur egal" docs/backlog/backlog.md` ne rend rien. **Tant que `camille-po` n'a pas ecrit la story, ces criteres sont ma proposition, pas un perimetre arbitre.**

1. **Refus.** Sur un item de sujet dont un distracteur est au meme rang que le verbe cite dans la phrase, la verification de la propriete 8 echoue. Le message nomme `VSJ-CE1-3`, l'enonce de l'item et le distracteur fautif, comme les messages voisins de la meme methode.
2. **Jeu fautif commite** (`ADR-0013`, obligation 3). Un item du fichier `ce1-verbe-sujet-fautif.yaml` porte un distracteur egal au verbe cite ; un test nomme sur le modele de `leJeuFautifCommiteEstRefuseSurUnePropositionHorsDeLaPhrase` (`assertThatThrownBy`, `AssertionError`, `hasMessageContaining("VSJ-CE1-3")` et le distracteur). Les autres tests qui lisent ce fichier fautif restent verts : l'item neuf ne fait pas rougir leur propriete.
3. **Morsure.** La mutation `P5` rejouee sur `ce1-verbe-sujet.yaml` : **rouge**, avec le nom du test qui echoue et le nombre de tests lances superieur a 0, puis le fichier restaure par `git checkout` (`git status` vide). Les deux nombres vont dans le rapport.
4. **Aucun affaiblissement.** `git diff` du test montre des lignes ajoutees et aucune assertion retiree ni assouplie ; les regles de l'amendement du 2026-10-14 (un distracteur avant permis, au plus un, virgule, indice de la place) ne changent pas.
5. **Paquet inchange.** `git diff --stat <base> HEAD -- adapters/adapter-content/src/main/resources/contenu/ce1-verbe-sujet.yaml` est vide, et le paquet reel passe la verification neuve (aucun item actuel ne declenche le cas).
6. **Porte.** `bash outils/mvn-jdk21.sh -B clean verify` depuis la racine, sans `-pl` : vert, total de tests et JDK que la machine virtuelle des tests rapporte. Les jobs `chemins`, `prefixes` et `outils` de `ci.yml` lances et nommes avec leur sortie ; un job non lance est dit non lance. Une mesure partielle porte `-am` et nomme son perimetre.

## 4. Ce que cette tache ne fait pas

- Ne touche pas `nombreDeManches: 10` de `ce1-phrases-types`, que `releve-yanis-palier-1.md` `F4` dit non garde : c'est a `camille-po` de dire si 10 est une exigence.
- Ne touche pas l'en-tete des neuf autres paquets que `releve-relecteur-palier-1.md` (section 3.4) route a `marc-dev` : tache separee, non engagee ici.
- Ne decide pas si la limite de l'indice de la place (`isNotEqualTo`, forme exacte) doit etre elargie : limite ecrite par l'amendement lui-meme.
- Ne juge pas *"de temps ou de lieu, jamais une personne"* : `nadia-prof`, a la lecture.

## Decisions prises

**Aucune `ADR` ni `DEC` n'est creee ou amendee par ce fichier.** Il affecte `F1` a `marc-dev` et propose six criteres a `camille-po`. Le statut du critere 1 comme application de `VSJ-CE1-3` est ma lecture du texte de l'arbitrage de s56 : `nadia-prof` la corrige si elle l'entend autrement.
